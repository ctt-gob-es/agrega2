/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.util;

import java.io.File;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

public class CalculoMD5 {
	
	private static final es.pode.parseadorXML.SCORM2004Dao parseador = new es.pode.parseadorXML.SCORM2004Dao();
	private static final String MANIFEST_NAME = "imsmanifest.xml";
	private static Logger logger = Logger.getLogger(CalculoMD5.class);
	/*
	 * Este metodo calcula el codigo MD5 del path del ODE que le pasan.
	 * El path tiene que direccionar el directorio donde se encuentra el fichero imsmanifest.
	 * */
	public static String calculaMD5DePath(String pathODE)
	{
		try {
			File fileManifest = new File(pathODE, MANIFEST_NAME); // fichero del manifest
			Manifest imsmanifest = parseador.parsearODEEager(fileManifest); //manifiesto parseado
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			// obtenemos los recursos del ODE
			// primero las url's, ordenadas
			String[] urls = manAgrega.obtenerURLsODE(true);
			// después los ficheros, ordenados
			String[] ficheros = manAgrega.obtenerFicherosODE(true);
			// ahora generamos el string del MD5 a partir de esta info.
			String md5Ficheros = UtilesFicheros.obtenerMD5Files(imsmanifest.getManifestBasePath(), ficheros);
			String md5URL = UtilesFicheros.obtenerMD5SumFromString(urls);
			// concatenamos los dos string y calculo el MD5
			return UtilesFicheros.obtenerMD5SumFromString(md5URL+md5Ficheros);
		} catch (ParseadorException e) {
			logger.error("Excepcion creando MD5 de path["+pathODE+"]. Error al parsear el manifest. Continuamos y devolvemos md5=\"\". - ", e);
			return "";
		} catch (Exception e) {
			logger.error("Excepcion creando MD5 de path["+pathODE+"]. Error calculando MD5. Continuamos y devolvemos md5=\"\". - ",e);
			return "";
		}
	}
}
