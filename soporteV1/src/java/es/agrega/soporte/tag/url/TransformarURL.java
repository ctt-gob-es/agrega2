/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.url;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class TransformarURL {
	
	private static Logger logger = Logger.getLogger(TransformarURL.class);
	
	public static String transformaUrl(String encoding, String url) {
		String finalUrl = "";
		if(encoding!=null && url!=null) {
			try {
				String encodedSlash = URLEncoder.encode("/", encoding);
				String encodedColon = URLEncoder.encode(":", encoding);
				finalUrl = URLEncoder.encode(url, encoding);
				finalUrl = finalUrl.replaceAll(encodedSlash, "/");
				finalUrl = finalUrl.replaceAll("\\+", "%20");
				finalUrl = finalUrl.replaceAll(encodedColon, ":");
				logger.debug("Url " + url + " codificada como " + finalUrl);
			} catch (Exception e) {
				logger.error("No se pudo codificar la url " + url + ". " + e.getLocalizedMessage(),e);
			}
		}
		return finalUrl;
	}
}
