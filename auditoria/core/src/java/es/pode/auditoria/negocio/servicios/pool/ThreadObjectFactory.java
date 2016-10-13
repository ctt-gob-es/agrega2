/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.auditoria.negocio.servicios.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

public class ThreadObjectFactory implements PoolableObjectFactory{

	private Logger logger = Logger.getLogger(this.getClass());

	   public Object makeObject() {
	      return new MultiSearcherThread();
	   }
	   
	   public void destroyObject(Object obj) {
		  logger.debug(" destroyObject...");
	      if (obj instanceof MultiSearcherThread) {
	    	  MultiSearcherThread rt = (MultiSearcherThread) obj;
	          rt.reset();//Para el hilo
	      }
	   }
	   
	   public boolean validateObject(Object obj) {
		  logger.debug(" validateObject...");
	      if (obj instanceof MultiSearcherThread) {
	    	  MultiSearcherThread rt = (MultiSearcherThread) obj;
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
	      if (obj instanceof MultiSearcherThread) {
	    	  MultiSearcherThread wt = (MultiSearcherThread) obj;
	         wt.setResult(null); //Limpia el resultado de la ejecución
	      }
	   }
	}
