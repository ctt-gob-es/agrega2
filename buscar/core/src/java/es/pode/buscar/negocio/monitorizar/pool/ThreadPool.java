package es.pode.buscar.negocio.monitorizar.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class ThreadPool extends GenericObjectPool {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private Properties props = null;
	
	   //First constructor.
	   public ThreadPool(ThreadObjectFactory objFactory) {
	      super(objFactory);
			InputStream is = null;
			try {
		//		Configuramos la clase de propiedades
				is = this.getClass().getResourceAsStream("/monitorizar.properties");
				props = new Properties();
				props.load(is);
				this.setMaxWait(Integer.parseInt(props.getProperty("maxWait")));
				this.setMinIdle(Integer.parseInt(props.getProperty("minIdle")));// Minimum number of objects allowed in the pool before the evictor thread spawns new objects
				this.setMaxIdle(Integer.parseInt(props.getProperty("maxIdle"))); // Maximum idle threads.
			    this.setMaxActive(Integer.parseInt(props.getProperty("maxActive"))); // Maximum active threads.
			    this.setTimeBetweenEvictionRunsMillis(Long.parseLong(props.getProperty("timeBetweenEvictionRunsMillis")));//Sets the number of milliseconds to sleep between runs of the idle object evictor thread.
			    //this.setMinEvictableIdleTimeMillis(Long.parseLong(props.getProperty("minEvictableIdleTimeMillis"))); //The minimum amount of time an object may sit idle in the pool before it is eligible for eviction by the idle object evictor
			    this.setTestOnBorrow(true); // Check if the thread is still valid.
			    // this.setMaxWait(3000);  Wait 3 second till a thread is available
			} catch (IOException e) {
				logger.error("ERROR: fichero no encontrado: buscar.properties",e);
				throw new RuntimeException(e);
			} finally {
				if( is != null ) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e);
					}
					
				}
			}
	   }

	   //Second constructor.
	   public ThreadPool(ThreadObjectFactory objFactory, GenericObjectPool.Config config) {
	      super(objFactory, config);
	   }


	   public Object borrowObject() throws Exception {
		   logger.debug(" borrowing object..");
	      return super.borrowObject();
	   }
	   
	   public synchronized MultiSearcherThread[] borrowObjects(int num, int max) {
		   num = returnNumObjs(num, max);
		   MultiSearcherThread[] rtArr = new MultiSearcherThread[num];
	       for (int i = 0; i < num; i++) {
	    	   MultiSearcherThread rt; 
	    	   try {
	    		   rt = (MultiSearcherThread) borrowObject();
	    		   rtArr[i] = rt;
	    	   } catch (Exception e) {
	    		   logger.error(" borrowObjects failed.. ", e);
	    	   }
	      }
	      return rtArr;
	   }
	   
	   public int returnNumObjs(int num, int max) {
		   int numObjPool = super.getMaxActive() - super.getNumActive();
		   if(num > numObjPool) num = numObjPool;
		   if(num > max) num = max;
		   logger.debug(" returning NumObjs.." + num);
		   return num;
	   }

	   public void returnObject(Object obj) throws Exception {
		   logger.debug(" returning object.." + obj);
	       super.returnObject(obj);
	   }
	}
