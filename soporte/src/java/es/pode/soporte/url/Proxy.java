/**
 * 
 */
package es.pode.soporte.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.utiles.base64.Base64Coder;

/**
 * @author dgonzalezd
 *
 */
public class Proxy {

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
}
