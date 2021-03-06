package es.pode.auditoria.negocio.servicios.pool;

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO;
import es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaImportService;
import es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaServicioServiceLocator;
//import es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO;
//import es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaImportService;
//import es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaServicioServiceLocator;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * A thread subclass for searching a single searchable 
 */
public class MultiSearcherThread extends Thread {

	private Logger logger = Logger.getLogger(this.getClass());
	private Exception ex;
	private Object result;
	private String node;
	private String serverPort;
	private String urlService;
	private Object params;
	private Object syncObject;
	private boolean done;
	boolean running;
	private boolean stopped;
	private java.util.Properties pAuditoriaProperties = null;
	
	public MultiSearcherThread() {
	    
	}
/**
 * M�todo de ejecuci�n del hilo.
 * @param node El nodo de la federaci�n sobre el que se ejecuta.
 * @param port el puerto del nodo.
 * @param urlService La URL del servicio al que se llama.
 * @param params Par�metros necesarios para hacer la llamada.
 * @param synObj 
 */
	public synchronized void execute(String node, String port, String urlService, Object params, Object synObj) {
        if(logger.isDebugEnabled()) logger.debug("Se ejecuta el hilo");
		this.node = node;
		this.serverPort = port;
		this.urlService = urlService;
		this.params = params;
		this.syncObject = synObj;
		this.done = false;
       	logger.debug("done false: Id: " + this.toString() + " direccion: " + this.getAdress());
		if (!running) { //Si es la primera ejecuci�n, lanza el hilo
            if(logger.isDebugEnabled())
                logger.debug("Primera vez que se ejecuta el hilo. Id: " + this.toString());
	        this.setDaemon(false);
	        this.start();
		} else { // Ya ten�amos un hilo corriendo, as� que lo despertamos
            if(logger.isDebugEnabled())
                logger.debug("No es la primera vez que se ejecuta el hilo notifyAll. Id: " + this.toString());
			this.notifyAll();
		}
	}
	
	private void execute() throws Exception{
		logger.debug("Metodo execute");
		
		es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaServicioService srvAuditoriaFederadaService = new SrvAuditoriaFederadaServicioServiceLocator();
		((SrvAuditoriaFederadaServicioServiceLocator)srvAuditoriaFederadaService).setSrvAuditoriaFederadaServicioAddress(this.getAdress());
		SrvAuditoriaFederadaImportService port = srvAuditoriaFederadaService.getSrvAuditoriaFederadaServicio();
		logger.debug( "#### srvAuditoriaFederadaEndPointAddress del fichero --> " + srvAuditoriaFederadaService.getSrvAuditoriaFederadaServicioAddress());
		if(((ParametrosInformeFederadoVO)this.params).getTipoInforme().equalsIgnoreCase("nivelAgregacionFederada"))
		{
			this.result = port.nivelAgregacionFederadoLocal((ParametrosInformeFederadoVO)this.params);
			
		}else if(((ParametrosInformeFederadoVO)this.params).getTipoInforme().equalsIgnoreCase("coberturaCurricularFederada"))
		{
			this.result = port.coberturaFederadoLocal((ParametrosInformeFederadoVO)this.params);
		}else if(((ParametrosInformeFederadoVO)this.params).getTipoInforme().equalsIgnoreCase("odesIdiomaFederada"))
		{
			this.result = port.informeOdesIdiomaLocal((ParametrosInformeFederadoVO)this.params);
		}else if(((ParametrosInformeFederadoVO)this.params).getTipoInforme().equalsIgnoreCase("odesPublicadosFederada"))
		{
			this.result = port.odesPublicadosFederadoLocal((ParametrosInformeFederadoVO)this.params);
		}
		logger.debug("El resultado de la ejecucion "+this.result);
		
        
	}

	/**
	 * M�todo de ejecuci�n del hilo.
	 */
	public void run(){
		running = true;
	    while (!stopped) { // El hilo sigue corriendo.
	    	if (done) { // We are waiting for a task now.
	    		synchronized (this) {
	    			try {
                        if(logger.isDebugEnabled())
	    				    logger.debug("Wait until we get a task to execute. Id: " + this.toString());
	    				this.wait(); //Wait until we get a task to execute.
	    			} catch (InterruptedException e) {
	    				stopped = true;
	    				logger.error("Run InterruptedException =" + this.getAdress(), e);
	    			}
	            }
	         } else { //There is a task....let us execute it.
	    		try {
                    if(logger.isDebugEnabled())
	    			    logger.debug("Se ejecuta Id: " + this.toString());
	        		 execute();
	        	 } catch (Exception e) {
	        		 logger.error("#### Run exception ="+this.getAdress() ,e);
	        	 } finally {
	        		 reset();
	        	 }
	         }
	    }
	}
	
	
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/auditoria.properties");
		if (this.pAuditoriaProperties == null)
		{
			pAuditoriaProperties = new java.util.Properties();
			pAuditoriaProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pAuditoriaProperties.getProperty(sKey);
	}
	
	/**
	 * M�todo para poner a null los par�metros de ejecuci�n del hilo.
	 */
	public void reset() {
        if(logger.isDebugEnabled())
		    logger.debug("reset()");
		this.done = true;
		this.node = null;
		this.serverPort = null;
		this.urlService = null;
		this.params = null;
		this.syncObject = null;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

    public Object getResult() {
    	return result;
	}
    
	  
	public Exception getException() {
		return ex;
	}

	public Object getMethodParams() {
		return params;
	}

	public void setMethodParams(Object params) {
		this.params = params;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String port) {
		this.serverPort = port;
	}

	public Object getSyncObject() {
		return syncObject;
	}

	public void setSyncObject(Object syncObject) {
		this.syncObject = syncObject;
	}

	public String getUrlService() {
		return urlService;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}
	
	private String getAdress() {
		if(this.serverPort!=null && !this.serverPort.trim().equals(""))return "http://"+this.node+((this.serverPort!=null && !this.serverPort.equals(""))?":"+this.serverPort:"")+((this.node.equals("localhost"))?LdapUserDetailsUtils.getSubdominioServices():"")+this.urlService;
		else return "http://"+this.node+this.urlService;
		
		
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
}