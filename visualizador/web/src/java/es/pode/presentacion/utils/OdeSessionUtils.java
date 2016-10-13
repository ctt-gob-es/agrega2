/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
