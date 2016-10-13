// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.buscar.servicios;

import org.apache.log4j.Logger;


/**
 * @see es.pode.buscar.negocio.buscar.servicios.SrvCacheService
 */

public class SrvCacheServiceImpl
    extends es.pode.buscar.negocio.buscar.servicios.SrvCacheServiceBase
{
	private Logger logger = Logger.getLogger(SrvCacheServiceImpl.class);
    /**
     * Servicio que se encarga de borrar la cache de las busquedas. Este servicio es util cuando
     * se des/publica algun ODE ya que al resetar al cache se fuerza a que las siguientes busquedas 
     * muestren el resultados actualizado.
     * 
	 * @see es.pode.buscar.negocio.buscar.servicios.SrvCacheService#borrarHitCache(java.lang.String)
	 * @return boolean Este parámetro contiene un flag con el resultado de la operación
	 */
	protected boolean handleBorrarHitCache() throws Exception {
//		//BORRAR
//		logger.debug("Borrando de cache");
//		if (ids!=null) {
//			//BORRAR
//			logger.debug("Borrando varios ids");
//			boolean resultado=true;
//			for (int i = 0; i < ids.length; i++) {
//				String id = ids[i];
//				//Hago AND con resultado para que resultado final sea false si alguna iteración falla
//				//pero lo hago después de la operación para que aunque resultado=false, haga la operación
//				resultado=SrvBuscarServiceImpl.cacheConfig.removeHitCache(id)&&resultado;
//			}
//			logger.debug("Se han borrado de cache los ids: "+Arrays.toString(ids));
//			return resultado;
//		}
		logger.debug("Borrando toda la cache");
		return SrvBuscarServiceImpl.cacheConfig.removeHitsCache();
	}

}