package es.pode.buscar.negocio.buscar.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

public class ThreadObjectSQIFactory implements PoolableObjectFactory{

	private Logger logger = Logger.getLogger(this.getClass());

	   public Object makeObject() {
	      return new MultiSearcherSQIThread();
	   }
	   
	   public void destroyObject(Object obj) {
		  logger.debug(" destroyObject...");
	      if (obj instanceof MultiSearcherSQIThread) {
	    	  MultiSearcherSQIThread rt = (MultiSearcherSQIThread) obj;
	          rt.reset();//Para el hilo
	      }
	   }
	   
	   public boolean validateObject(Object obj) {
		  logger.debug(" validateObject...");
	      if (obj instanceof MultiSearcherSQIThread) {
	    	  MultiSearcherSQIThread rt = (MultiSearcherSQIThread) obj;
	         if (rt.isRunning()) {
	            if (rt.getThreadGroup() == null) {
	               return false;
	            }
	            return true;
	         }
	      }
	      return true;
	   }
	   
	   public void activateObject(Object obj) {
		   logger.debug(" activateObject...");
	   }


	   public void passivateObject(Object obj) {
		   logger.debug(" passivateObject..." + obj);
	      if (obj instanceof MultiSearcherSQIThread) {
	    	  MultiSearcherSQIThread wt = (MultiSearcherSQIThread) obj;
	         wt.setResult(null); //Limpia el resultado de la ejecución
	      }
	   }
	}
