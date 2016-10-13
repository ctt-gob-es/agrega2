package es.pode.buscar.negocio.buscar.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class EHCacheConfig {
	
	private Properties props = null;
	private CacheManager cacheManager;
	private Cache hits;
	private Cache hitsLength;
	private Cache hitsSQI;
	private Cache hitsSQIAriadne;
	private Cache hitsSugBusq;
	private Logger logger = Logger.getLogger(this.getClass());
	
	//Se agrega un parametro al metodo EHCacheConfig para evitar usar el servicio de SrvPropiedadService en su clase
	public EHCacheConfig(String segundosCaducidadHit){
		
		InputStream is = null;
		try {
	//		Configuramos la clase de propiedades
			is = this.getClass().getResourceAsStream("/buscar.properties");
			props = new Properties();
			props.load(is);
	//		Configuramos la cache de hits
			cacheManager= CacheManager.create();
			hits = new Cache("hits", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(segundosCaducidadHit).longValue(),//tiempo de vida de los elementos en la cache
					new Long(segundosCaducidadHit).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(hits);
			
			hitsLength = new Cache("hitsLength", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(segundosCaducidadHit).longValue(),//tiempo de vida de los elementos en la cache
					new Long(segundosCaducidadHit).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(hitsLength);
			
			hitsSQI = new Cache("hitsSQI", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(segundosCaducidadHit).longValue(),//tiempo de vida de los elementos en la cache
					new Long(segundosCaducidadHit).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(hitsSQI);
			
			hitsSQIAriadne = new Cache("hitsSQIAriadne", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(segundosCaducidadHit).longValue(),//tiempo de vida de los elementos en la cache
					new Long(segundosCaducidadHit).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(hitsSQIAriadne);
			
			hitsSugBusq = new Cache("hitsSugBusq", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(segundosCaducidadHit).longValue(),//tiempo de vida de los elementos en la cache
					new Long(segundosCaducidadHit).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(hitsSugBusq);
			
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

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public Cache getHits() {
		return hits;
	}

	public void setHits(Cache hits) {
		this.hits = hits;
	}

	public Cache getHitsLength() {
		return hitsLength;
	}

	public void setHitsLength(Cache hitsLength) {
		this.hitsLength = hitsLength;
	}
	
	public Cache getHitsSQI() {
		return hitsSQI;
	}

	public void setHitsSQI(Cache hitsSQI) {
		this.hitsSQI = hitsSQI;
	}
	
	public boolean removeHitsCache() throws Exception {
		try{
			getHits().removeAll();
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarHitCache: ERROR: no se ha podido eliminar los hits de cache.",e);
			logger.error(exc);
			throw exc;
		}
		return true;
	}
	
	public boolean removeHitsLenghtCache() throws Exception {
		try{
			getHitsLength().removeAll();
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarHitCache: ERROR: no se ha podido eliminar los hits de cache taxonómica.",e);
			logger.error(exc);
			throw exc;
		}
		return true;
	}
	
	public boolean removeHitsSQICache() throws Exception {
		try{
			getHitsSQI().removeAll();
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarCacheSQI: ERROR: no se ha podido eliminar los hits de cache SQI.",e);
			logger.error(exc);
			throw exc;
		}
		return true;
	}
	
	public boolean removeHitsSQIAriadneCache() throws Exception {
		try{
			getHitsSQIAriadne().removeAll();
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarCacheSQIAriadne: ERROR: no se ha podido eliminar los hits de cache SQIAriadne.",e);
			logger.error(exc);
			throw exc;
		}
		return true;
	}
	
	public boolean removeHitsSugBusq() throws Exception {
		try{
			getHitsSugBusq().removeAll();
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarCacheSugBusq: ERROR: no se ha podido eliminar los hits de cache SugBusq.",e);
			logger.error(exc);
			throw exc;
		}
		return true;
	}
	
	public boolean removeHitCache(String hit) throws Exception {
		boolean idBorrado=false;
		try{
			if (getHits().isElementInMemory(hit)){
				if (logger.isDebugEnabled()){
					logger.debug("SrvBuscarServiceImpl - borrarHitCache: El ODE con identificador: " + hit+ "esta en cache");
				}
				idBorrado=getHits().remove(hit);
				if (logger.isDebugEnabled()){
					logger.debug("SrvBuscarServiceImpl - borrarHitCache: El ODE con identificador: " + hit+ "ha sido eliminado de cache correctamente");
				}
			}else{
				if (logger.isDebugEnabled()){
					logger.debug("SrvBuscarServiceImpl - borrarHitCache: El ODE con identificador: " + hit+ "no se encuentra en cache");
				}
			}
		}catch (Exception e){
			Exception exc = new Exception("SrvBuscarServiceImpl - borrarHitCache: ERROR: no se ha podido eliminar de cache el ODE con identificador: " + hit);
			logger.error(exc);
			throw exc;
		}
		return idBorrado;
	}

	public Cache getHitsSQIAriadne() {
		return hitsSQIAriadne;
	}

	public void setHitsSQIAriadne(Cache hitsSQIAriadne) {
		this.hitsSQIAriadne = hitsSQIAriadne;
	}

	public Cache getHitsSugBusq() {
		return hitsSugBusq;
	}

	public void setHitsSugBusq(Cache hitsSugBusq) {
		this.hitsSugBusq = hitsSugBusq;
	}
}
