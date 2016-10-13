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
