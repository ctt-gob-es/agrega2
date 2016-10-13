/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.adl.util;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * 
 * Clase de utilidades para el RTE ADL integrado en visualizador.
 * 
 * @author fjespino
 *
 */
public class ADLUtils {
	
	private static String USER_DIR=null;

	public static final String getADLUserDir() {

		/*
		 * El directorio ra�z para ADL es siempre el mismo. A partir de el, ADL
		 * se crea sus propios subdirectorios. Lo creo una sola vez y almaceno
		 * la ruta de forma est�tica.
		 */
		if (USER_DIR==null) {
			String dir = System.getProperty("java.io.tmpdir");
			if(dir!=null && !dir.endsWith(java.io.File.separator)) dir = dir+java.io.File.separator;
			if(dir!=null) dir = dir + "agrega-adlscorm";
			USER_DIR=dir;
			File ruta_userdir = new File(USER_DIR);
			if (!ruta_userdir.exists()) {
				ruta_userdir.mkdir();
			}
			ruta_userdir=null;
		}
		Logger.getLogger(ADLUtils.class).debug("Ruta para ficheros ADL = " + USER_DIR);
		return USER_DIR;
	}
	
}
