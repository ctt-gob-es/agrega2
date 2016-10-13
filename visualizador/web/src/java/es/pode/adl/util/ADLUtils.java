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
		 * El directorio raíz para ADL es siempre el mismo. A partir de el, ADL
		 * se crea sus propios subdirectorios. Lo creo una sola vez y almaceno
		 * la ruta de forma estática.
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
