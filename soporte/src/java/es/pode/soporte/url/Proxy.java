/**
 * 
 */
package es.pode.soporte.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.elastic.BuscadorMetadatosFederados;
import es.pode.soporte.utiles.base64.Base64Coder;

/**
 * @author dgonzalezd
 *
 */
public class Proxy {

	private static Logger logger = Logger.getLogger(Proxy.class);
	
	static public InputStream getInputStream(URL url) throws IOException {
		char[] encoded;
		InputStream in;
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USAPROXY).equals("true")){
		System.setProperty("http.proxyHost",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOSTPROXY));
		System.setProperty("http.proxyPort",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORTPROXY));
		String claveProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CLAVEPROXY);
		String usuarioProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USUARIOPROXY);
		
		URLConnection feedUrlConnection=url.openConnection();
		
		if (!usuarioProxy.equals("")&&!claveProxy.equals("")) {
			encoded = Base64Coder.encode((usuarioProxy + ":" + claveProxy)
					.getBytes());
			feedUrlConnection.setRequestProperty("Proxy-Authorization",
					"Basic " + new String(encoded));
		}
		
		in=feedUrlConnection.getInputStream();
		
		}else{
			in = url.openStream();
		}
		return in;
	}
	
	static public InputStream getInputStream(URL url, int timeout) throws IOException {
		char[] encoded;
		InputStream in;
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USAPROXY).equals("true")){
		System.setProperty("http.proxyHost",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOSTPROXY));
		System.setProperty("http.proxyPort",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORTPROXY));
		String claveProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CLAVEPROXY);
		String usuarioProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USUARIOPROXY);
		
		URLConnection feedUrlConnection=url.openConnection();
		feedUrlConnection.setReadTimeout(timeout);
		
		if (!usuarioProxy.equals("")&&!claveProxy.equals("")) {
			encoded = Base64Coder.encode((usuarioProxy + ":" + claveProxy)
					.getBytes());
			feedUrlConnection.setRequestProperty("Proxy-Authorization",
					"Basic " + new String(encoded));
		}
		
		in=feedUrlConnection.getInputStream();
		
		
		
		}else{
			in = url.openStream();
		}
		return in;
	}
	
	static public URLConnection getURLConnection(URL url) throws IOException {
		char[] encoded;		
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USAPROXY).equals("true")){
			System.setProperty("http.proxyHost",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOSTPROXY));
			System.setProperty("http.proxyPort",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORTPROXY));
			String claveProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CLAVEPROXY);
			String usuarioProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USUARIOPROXY);
			
			URLConnection feedUrlConnection=url.openConnection();
			
			if (!usuarioProxy.equals("")&&!claveProxy.equals("")) {
				encoded = Base64Coder.encode((usuarioProxy + ":" + claveProxy)
						.getBytes());
				feedUrlConnection.setRequestProperty("Proxy-Authorization",
						"Basic " + new String(encoded));
			}
			return feedUrlConnection;
		}
		return null;
	}
		
	public static InputStream postUrlConnection(String url, String body) {
		logger.debug("Va a realizar petición post a : " + url);
		InputStream in = null;
		try
		{
			URL urlES = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlES.openConnection();
			conn.setRequestMethod("POST");

			conn.setDoOutput(true);

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			writer.write(body);
			writer.flush();			
			
			writer.close();
			in = conn.getInputStream();
			
		}catch (Exception e) {
			logger.error("Error al realizar petición POST : " , e);
		}
		return in;
	}		
}
