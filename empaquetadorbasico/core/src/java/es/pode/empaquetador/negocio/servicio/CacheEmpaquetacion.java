/**
 * 
 */
package es.pode.empaquetador.negocio.servicio;

import java.util.HashMap;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.Manifest;

//import org.apache.log4j.Logger;
//
//import es.pode.parseadorXML.castor.Manifest;

/**
 * @author dgonzalezd
 *
 */
public class CacheEmpaquetacion {
	
	
	private static Logger logger = Logger.getLogger(CacheEmpaquetacion.class);
	private HashMap<String, Object> cacheEmpaquetacion = new HashMap<String, Object>(10);
	
//	private static Logger logger = Logger.getLogger(CacheEmpaquetacion.class);

	/**
	 * Recupera objeto de la caché
	 * @param identificador String identificador del objeto a recuperar
	 * @return Objeto recuperado, null si no se encontró
	 */
	public Object get(String identificador) {
		return cacheEmpaquetacion.get(identificador);
	}
	
	
	/**
	 * Elimina objeto de la caché
	 * @param identificador String identificador del objeto a eliminar
	 * @return Objeto eliminado, null si no se encontró
	 */
	public Object remove(String identificador) {
		return cacheEmpaquetacion.remove(identificador);
	}
	
	/**
	 * Añadir objeto a la caché
	 * @param key String identificador del objeto a añadir
	 * @param value Objeto a añadir
	 * @return Objeto previamente asociado al identificador, null si no había
	 */
	public Object put(String key, Object value) {
		return cacheEmpaquetacion.put(key, value);
	}
	
/* Al final no le veo mucho sentido tenerlo aquí, pues me parece no muy ligado
 * a esta clase. En los métodos de SrvGestorManifestServiceImpl que se llama a
 * métodos de esta clase se llama a este método, pero no en todos los métodos 
 * que lo llaman se usan métodos de esta clase. 
 */
	public Manifest comprobarManifest(String identificador)
			throws java.lang.Exception {
		Manifest manifest = null;
		Object obj = get(identificador);
		
		if (obj instanceof es.pode.parseadorXML.castor.Manifest) {
			logger.debug("El objeto " + obj + " es de tipo manifest");
			manifest = (Manifest) obj;
		} else if (obj != null) {
			logger.error("El objeto " + obj + " no es de tipo manifest");
			throw new AlmacenamientoException("El objeto no es de tipo manifest");
		}
		return manifest;

	}
}
