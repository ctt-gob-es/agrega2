package es.pode.buscar.negocio.buscar.pool;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaImportService;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceServiceLocator;

/**
 * A thread subclass for searching a single searchable 
 */
public class MultiSearcherThread extends Thread {

	private Logger logger = Logger.getLogger(this.getClass());
	private Exception ex;
	private Object result;
	private String urlESB;
	private String url;
	private Object params;
	private Object syncObject;
	private boolean done;
	boolean running;
	private boolean stopped;
  
	public MultiSearcherThread() {
	    
	}

	 /**
	 * Método de ejecución del hilo.
	 * @param urlDestino La URL del servicio al que redirige el esb.
	 * @param urlDestinoESB La URL del esb.
	 * @param params Parámetros necesarios para hacer la llamada.
	 * @param synObj Objeto de sincronizacion entre hilos
	 */
	public synchronized void execute(String urlDestino, String urlDestinoESB, Object params, Object synObj) {
        if(logger.isDebugEnabled()) logger.debug("Se ejecuta el hilo");
		this.params = params;
		this.syncObject = synObj;
		this.url = urlDestino;
		this.done = false;
		this.urlESB = urlDestinoESB;
        if(logger.isDebugEnabled())
            logger.debug("done false: Id: " + this.toString() + " direccion: " + this.params);
		if (!running) { //Si es la primera ejecución, lanza el hilo
            if(logger.isDebugEnabled())
                logger.debug("Primera vez que se ejecuta el hilo. Id: " + this.toString());
	        this.setDaemon(false);
	        this.start();
		} else { // Ya teníamos un hilo corriendo, así que lo despertamos
            if(logger.isDebugEnabled())
                logger.debug("No es la primera vez que se ejecuta el hilo notifyAll. Id: " + this.toString());
			this.notifyAll();
		}
	}
	
	private void execute() throws Exception{
		es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceService srvBuscarFederadaService = new SrvBuscarFederadaServiceServiceLocator();
	    ((SrvBuscarFederadaServiceServiceLocator)srvBuscarFederadaService).setSrvBuscarFederadaServiceAddress(this.urlESB);
	    SrvBuscarFederadaImportService port = srvBuscarFederadaService.getSrvBuscarFederadaService();
        if(logger.isDebugEnabled())
	        logger.debug(this.url+" #### srvBuscarFederadaEndPointAddress del fichero --> " + srvBuscarFederadaService.getSrvBuscarFederadaServiceAddress());
	    if(this.params==null){ 
	    	this.result = port.devolverVersionNodo();
	    }else if(ParametrosBusquedaAvanzadaVO30.class.equals(this.params.getClass())){
	    	this.result = port.busquedaFederada30((ParametrosBusquedaAvanzadaVO30)this.params);
	    }else if(ParametrosDocsCountVO30.class.equals(this.params.getClass())){
	    	this.result = port.solicitarDocsCountArbolCurricular30((ParametrosDocsCountVO30)this.params);
	    }
        if(logger.isDebugEnabled())
            logger.debug(this.url+" #### Execution Result OK= " + result);
	}
	
	/**
	 * Método de ejecución del hilo.
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
	    				logger.error("Run InterruptedException =" + this.url, e);
	    			}
	            }
	         } else { //There is a task....let us execute it.
	    		try {
                    if(logger.isDebugEnabled())
	    			    logger.debug("Se ejecuta Id: " + this.toString());
	        		 execute();
	        	 } catch (Exception e) {
	        		 logger.error("#### Run exception ="+this.url ,e);
	        	 } finally {
	        		 reset();
	        	 }
	         }
	    }
	}
	
 	/**
	 * Método para poner a null los parámetros de ejecución del hilo.
	 */
	public void reset() {
        if(logger.isDebugEnabled())
		    logger.debug("reset()");
		this.done = true;
		this.params = null;
		this.syncObject = null;
		this.url=null;
		this.urlESB=null;
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

	public void resetAll() {
		this.reset();
		this.ex=null;
		this.result=null;
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

	public Object getSyncObject() {
		return syncObject;
	}

	public void setSyncObject(Object syncObject) {
		this.syncObject = syncObject;
	}
}
