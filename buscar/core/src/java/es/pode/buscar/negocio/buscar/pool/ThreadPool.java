package es.pode.buscar.negocio.buscar.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class ThreadPool extends GenericObjectPool {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private static Properties props = null;
	private static boolean informe = false;
	
	   //First constructor.
	   public ThreadPool(PoolableObjectFactory objFactory) {
	      super(objFactory);
			InputStream is = null;
			try {
				if(props==null){
//					Configuramos la clase de propiedades
					is = this.getClass().getResourceAsStream("/buscar.properties");
					Properties properties = new Properties();
					properties.load(is);
					props=properties;
				}
				this.setMaxWait(Integer.parseInt(props.getProperty("maxWait")));
				this.setMinIdle(Integer.parseInt(props.getProperty("minIdle")));// Minimum number of objects allowed in the pool before the evictor thread spawns new objects
				this.setMaxIdle(Integer.parseInt(props.getProperty("maxIdle"))); // Maximum idle threads.
			    this.setMaxActive(Integer.parseInt(props.getProperty("maxActive"))); // Maximum active threads.
			    this.setTimeBetweenEvictionRunsMillis(Long.parseLong(props.getProperty("timeBetweenEvictionRunsMillis")));//Sets the number of milliseconds to sleep between runs of the idle object evictor thread.
			    //this.setMinEvictableIdleTimeMillis(Long.parseLong(props.getProperty("minEvictableIdleTimeMillis"))); //The minimum amount of time an object may sit idle in the pool before it is eligible for eviction by the idle object evictor
			    if (!props.getProperty("testOnBorrow").equals("false"))
			    	this.setTestOnBorrow(true);
			    else
			    	this.setTestOnBorrow(false);
			    
			    if (!props.getProperty("testOnReturn").equals("false"))
			    	this.setTestOnReturn(true);
			    else
			    	this.setTestOnReturn(false);

			    // this.setMaxWait(3000);  Wait 3 second till a thread is available				
				this.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_GROW); // A "when exhausted action" type indicating that when the pool is exhausted (i.e., the maximum number of active objects has been reached), the borrowObject()  method should simply create a new object anyway. 
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
            if(logger.isDebugEnabled()) logger.debug(" borrowing object..");
	      return super.borrowObject();
	   }
	   
	   public synchronized Object[] borrowObjects(int num, int max) {
		   num = returnNumObjs(num, max);
		   Object[] rtArr = new Object[num];
	       for (int i = 0; i < num; i++) {
	    	   Object rt; 
	    	   try {
	    		   rt = borrowObject();
	    		   rtArr[i] = rt;
	    	   } catch (Exception e) {
	    		   logger.error(" borrowObjects failed.. ", e);
	    	   }
	      }
	      return rtArr;
	   }
	   
	   public synchronized Object[] borrowObjects(int num) {
		   Object[] rtArr = new Object[num];
	       for (int i = 0; i < num; i++) {
	    	   Object rt; 
	    	   try {
	    		   rt = borrowObject();
	    		   rtArr[i] = rt;
	    	   } catch (Exception e) {
	    		   logger.error(" borrowObjects failed.. ", e);
	    	   }
	      }
	      return rtArr;
	   }
	   
	   public int returnNumObjs(int num, int max) {
           if(logger.isDebugEnabled())
		        logger.debug("super.getMaxActive() ["+super.getMaxActive()+"] - super.getNumActive()["+super.getNumActive()+"]="+(super.getMaxActive() - super.getNumActive())+"|| n�mero pedidos="+num+" y n�mero m�ximo por petici�n="+max);
		   int numObjPool = super.getMaxActive() - super.getNumActive();
		   if(num > numObjPool) num = numObjPool;
		   if(num > max) num = max;
		   if(num==0 && logger.isDebugEnabled() && !informe){
			   informe=true;
               if(logger.isDebugEnabled()) {
                   logger.debug("Informe pool:");
                   logger.debug("super.getMaxIdle()="+super.getMaxIdle());
                   logger.debug("super.getMaxWait()="+super.getMaxWait());
                   logger.debug("super.getMinEvictableIdleTimeMillis()="+super.getMinEvictableIdleTimeMillis());
                   logger.debug("super.getMinIdle()="+super.getMinIdle());
                   logger.debug("super.getNumIdle()="+super.getNumIdle());
                   logger.debug("super.getNumTestsPerEvictionRun()="+super.getNumTestsPerEvictionRun());
                   logger.debug("super.getTimeBetweenEvictionRunsMillis()="+super.getTimeBetweenEvictionRunsMillis());
                   
                   logger.debug("super.getTestOnBorrow()="+super.getTestOnBorrow());
                   logger.debug("super.getTestOnReturn()="+super.getTestOnReturn());
                   logger.debug("super.getTestWhileIdle()="+super.getTestWhileIdle());
                   
                   logger.debug("super.getMaxIdle()="+super.getMaxIdle());
                   logger.debug("super.getMaxWait()="+super.getMaxWait());
                   logger.debug("super.getMinEvictableIdleTimeMillis()="+super.getMinEvictableIdleTimeMillis());
                   logger.debug("super.getMinIdle()="+super.getMinIdle());
                   logger.debug("super.getNumIdle()="+super.getNumIdle());
                   logger.debug("super.getNumTestsPerEvictionRun()="+super.getNumTestsPerEvictionRun());
                   logger.debug("super.getTimeBetweenEvictionRunsMillis()="+super.getTimeBetweenEvictionRunsMillis());
               }
		   }
           if(logger.isDebugEnabled()) 
		        logger.debug(" returning NumObjs.." + num);
		   return num;
	   }

	   public void returnObject(Object obj) throws Exception {
           if(logger.isDebugEnabled()) 
		        logger.debug(" returning object.." + obj);
	       super.returnObject(obj);
	   }
	}
