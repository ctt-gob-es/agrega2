/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.modificador.negocio.servicio;

public class DecisorOffline {
	public static final String FILE = "/offline.properties";
	public static final String KEY = "offline";
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DecisorOffline.class);
	
	public static boolean esOffline() {
		boolean result = false;
		java.net.URL file = DecisorOffline.class.getResource(FILE);
		logger.debug("Hay fichero offline.properties? : " + (file!=null));
		if(file!=null) {
			java.util.Properties props = new java.util.Properties();
			try {
				props.load(file.openStream());
				String property = props.getProperty(KEY);
				if("true".equalsIgnoreCase(property)) result=true;
			} catch (Exception e) {
				logger.debug("No se ha podido cargar el fichero offline.properties",e);
			}
		}
		return result;
	}
}
