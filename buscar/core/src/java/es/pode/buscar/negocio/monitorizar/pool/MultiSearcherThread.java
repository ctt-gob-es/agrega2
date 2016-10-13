package es.pode.buscar.negocio.monitorizar.pool;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

/**
 * A thread subclass for searching a single searchable 
 */
public class MultiSearcherThread extends Thread {

	private Logger logger = Logger.getLogger(this.getClass());
	private Exception ex;
	private String result;
	private String urlService;
	private boolean done;
	boolean running;
	private boolean stopped;
  
	public MultiSearcherThread() {
	    
	}

	 /**
	 * Método de ejecución del hilo.
	 * @param urlService La URL del servicio al que se llama.
	 */
	public synchronized void execute(String urlService) {
		this.urlService = urlService;
		this.done = false;
		if (!running) { //Si es la primera ejecución, lanza el hilo
	        this.setDaemon(false);
	        this.start();
		} else { // Ya teníamos un hilo corriendo, así que lo despertamos
			this.notifyAll();
		}
	}
	
	private void execute() throws Exception{	
    	
//        		miramos si está levantado
            Service service = new Service();
            Call call = null;
            try {
                call = (Call) service.createCall();    
        		call.setTargetEndpointAddress( new java.net.URL(this.urlService) );           
        		Object res =  call.invoke("estoyActivo",null);  
        		this.result = "estadoServicio.ACTIVO";
        		logger.info("SERVICIO ACTIVO: " + this.urlService);
            } catch (Exception e) {
            	this.result = "estadoServicio.INACTIVO";
        		logger.info("SERVICIO INACTIVO: " + this.urlService + " --> " +e);
            	
            }
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
	    				this.wait(); //Wait until we get a task to execute.
	    			} catch (InterruptedException e) {
	    				stopped = true;
	    				logger.error("Run InterruptedException =", e);
	    			}
	            }
	         } else { //There is a task....let us execute it.
	        	 try {
	        		 execute();
	        	 } catch (Exception e) {
	        		 logger.error("#### Run exception :"+ e.toString());
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
		this.done = true;
		this.urlService = null;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

   public Exception getException() {
		return ex;
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

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public String getUrlService() {
		return urlService;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
