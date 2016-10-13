// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.monitorizar.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.monitorizar.dominio.EstadoNodoVO;
import es.pode.buscar.negocio.monitorizar.pool.MultiSearcher;


/**
 * @see es.pode.buscar.negocio.monitorizar.servicio.SrvMonitorizarService
 */

public class SrvMonitorizarServiceImpl
    extends es.pode.buscar.negocio.monitorizar.servicio.SrvMonitorizarServiceBase
{
	private static Logger log = Logger.getLogger(SrvMonitorizarServiceImpl.class);
	private Properties props = null;
	
	/**
	 * Obtiene el estado de los servicios asociados a los nodos
	 * 
	 * 
	 * @throws Exception
	 */
	
	public SrvMonitorizarServiceImpl(){
		super();
		InputStream is = null;
		try {
	//		Configuramos la clase de propiedades
			is = this.getClass().getResourceAsStream("/buscar.properties");
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			log.error("ERROR: fichero no encontrado: buscar.properties",e);
			throw new RuntimeException(e);
		} finally {
			if( is != null ) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(e);
				}
				
			}
		}
	}
	/**
	 * Obtiene el estado de los servicios asociados a los nodos
	 * 
	 * 
	 * @return EstadoNodoVO[] Array de VOs que contienen el estado de los nodos
	 * @throws Exception
	 */
    protected es.pode.buscar.negocio.monitorizar.dominio.EstadoNodoVO[] handleObtenerEstadoNodos()
        throws java.lang.Exception
    {
    	NodoVO[] nodos = this.getSrvNodoService().listarNodos();
    	EstadoNodoVO[] estados = MultiSearcher.search(nodos, Integer.parseInt(props.getProperty("monitorizar_maxNumObjPool")),Integer.parseInt(props.getProperty("monitorizar_maxWaitThread")),Integer.parseInt(props.getProperty("monitorizar_maxWaitPool")));
        return estados;
    }

}