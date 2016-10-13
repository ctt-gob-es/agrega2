/**
 * 
 */
package es.pode.presentacion.utils;

import java.util.HashMap;

import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;

/**
 * @author dgonzalezd
 *
 */
public class OdeSessionUtils {

	/**
	 * Da una instancia de OdeSession asociada al identificador dado, exista previamente o no
	 * @param sesion Sesión de usuario
	 * @param identificador Identificador con el que se asocia OdeSession
	 * @return instancia de OdeSession, ya ligada a la sesión
	 */
	public static OdeSession getOdeSession(VisualizadorSession sesion, String identificador) {
		HashMap<String, OdeSession> map = (HashMap<String, OdeSession>) sesion.getMap();
		if(map==null) {
			map=new HashMap<String, OdeSession>();
			sesion.setMap(map);
		}
		OdeSession odeSesion = map.get(identificador);
		if (odeSesion==null) {
			odeSesion = new OdeSession();
			map.put(sesion.getIdentificador(), odeSesion);
		}
		return odeSesion;
	}
	
	/**
	 * Elimina instancia de OdeSession
	 * @param sesion Sesión de usuario
	 * @param identificador Identificador con el que se asocia OdeSession
	 */
	public static void removeOdeSession(VisualizadorSession sesion, String identificador) {
		HashMap<String, OdeSession> map = (HashMap<String, OdeSession>) sesion.getMap();
		if(map!=null) {
			map.remove(identificador);
		}
	}
	
	/**
	 * Da número de instancias de OdeSession asociadas a la sesión
	 * @param sesion Sesión de usuario
	 * @return Número de instancias
	 */
	public static int countOdeSession(VisualizadorSession sesion) {
		HashMap<String, OdeSession> map = (HashMap<String, OdeSession>) sesion.getMap();
		if(map!=null)
			return map.size();
		return 0;
	}
}
