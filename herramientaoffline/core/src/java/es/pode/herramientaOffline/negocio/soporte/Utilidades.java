/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.herramientaOffline.negocio.soporte;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import es.pode.contenidos.negocio.descargas.servicio.SrvDescargas;
import es.pode.contenidos.negocio.descargas.servicio.SrvDescargasServiceLocator;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.localizador.negocio.servicios.SrvLocalizadorServiceServiceLocator;
import es.pode.publicacion.negocio.servicios.SrvSyncService;
import es.pode.publicacion.negocio.servicios.SrvSyncServiceServiceLocator;

/**
 * @author jaborrero
 *
 */
public class Utilidades {
	private static Logger logger = Logger.getLogger(Utilidades.class);

	public static final String HTTP = "http://";
	private static final String DESCARGAS = "contenidosportal-F1/services/SrvDescargas";
	private static final String SYNC = "publicacion-1/services/SrvSyncService";
	public static final String BARRA = "/";
	
	private static final String FILE_NAME_PROPERTIES = "/hoffline.properties";
	private static Properties props = null;
	
	static public SrvDescargas obtenerConexionSrvDescargas(String url, String puerto) throws MalformedURLException, ConexionException{
		SrvDescargas servicio =null;
		URL urlDescargas=null;
		try{
			SrvDescargasServiceLocator locator = new SrvDescargasServiceLocator();
			
			urlDescargas=new URL(componerUrlPuerto(url, puerto));
			
			//termino de completar la dirección del servicio
			urlDescargas=new URL(urlDescargas+BARRA+DESCARGAS);
			logger.debug("Generado la URL del nodo "+urlDescargas);
			
			servicio= locator.getSrvDescargas(urlDescargas);
		}catch(MalformedURLException e){
			logger.error("La url está mal formada " +urlDescargas);
			throw new MalformedURLException ("La url está mal formada" +urlDescargas);
		}catch(ServiceException ex){
			logger.error("Error al llamar al servicio Descargas con url " +url);
			throw new ConexionException ("Error al generar el servicio Descargas con url" +urlDescargas, ex);
		}
		return servicio;
		
	}
	public static SrvSyncService obtenerConexionSrvSync(String url, String puerto) throws MalformedURLException, ConexionException{
		SrvSyncService servicio =null;
		URL urlSync=null;
		try{
			SrvSyncServiceServiceLocator locator = new SrvSyncServiceServiceLocator();
			urlSync = new URL(componerUrlPuerto(url,puerto));
			logger.debug("Hemos obtenido la composicion de la URL "+urlSync);
			
//			if(url.toLowerCase().startsWith(Utilidades.HTTP.toLowerCase())){
				urlSync=new URL(urlSync+Utilidades.BARRA+SYNC);
//			}else{
//				urlSync=new URL(Utilidades.HTTP+urlSync+Utilidades.BARRA+SYNC);
//			}
				
			logger.debug("Generado la URL del nodo "+urlSync);
			servicio= locator.getSrvSyncService(urlSync);
		}catch(MalformedURLException e){
			logger.error("La url está mal formada " +urlSync);
			throw new MalformedURLException ("La url está mal formada" +urlSync);
		}catch(ServiceException ex){
			logger.error("Error al llamar al servicio Sync con url " +url);
			throw new ConexionException ("Error al generar el servicio Sync con url" +urlSync, ex);
		}
		return servicio;
		
	}
	
	public static SrvLocalizadorService obtenerConexionSrvLocalizadorService(String url, String puerto) throws MalformedURLException, ConexionException{
		SrvLocalizadorService servicio =null;
		URL urlLocalizador=null;
		try{
			SrvLocalizadorServiceServiceLocator locator = new SrvLocalizadorServiceServiceLocator();
			
			//tratamos según el puerto
			if (!(puerto == null)){
				
				url = url + ":" + puerto;
			}
			
			if(url.toLowerCase().startsWith(Utilidades.HTTP.toLowerCase())){
				urlLocalizador=new URL(url+Utilidades.BARRA+SYNC);
			}else{
				urlLocalizador=new URL(Utilidades.HTTP+url+Utilidades.BARRA+SYNC);
			}
			logger.debug("Generado la URL del nodo "+urlLocalizador);
			servicio= locator.getSrvLocalizadorService(urlLocalizador);
		}catch(MalformedURLException e){
			logger.error("La url está mal formada " +urlLocalizador);
			throw new MalformedURLException ("La url está mal formada" +urlLocalizador);
		}catch(ServiceException ex){
			logger.error("Error al llamar al servicio Localizador con url " +url);
			throw new ConexionException ("Error al generar el servicio Localizador con url" +urlLocalizador, ex);
		}
		return servicio;
		
	}

	public static String componerUrlPuerto(String url, String puerto) {
		// Patron para matchear una URL con subdominio
		String patternURLSubdominio = "^((http://)|(https://))?[^/]*(/){1}[^/].*$";
		String result = null;
		if(url.matches(patternURLSubdominio)) {
			if(logger.isDebugEnabled()) logger.debug("La URL " + url + " es una URL con subdominio");
			// Descomponer la cadena de la URL para incorporar el puerto entre host y subdominio.
			String patternHost = "([^/\\.]*\\.)+([^/\\.]*[^:/])";
			Pattern patron = Pattern.compile(patternHost, Pattern.CASE_INSENSITIVE);
			Matcher matcher = patron.matcher(url);
			StringBuffer sb = new StringBuffer();
			if(matcher.find()) {
				String host = matcher.group();
				
				//tratamos según el puerto
				if (puerto == null){
					
					matcher.appendReplacement(sb, host);
				}else{
				
					matcher.appendReplacement(sb, host + ":" + puerto);
				}
				
				matcher.appendTail(sb);
			}
			result = sb.toString();
		} else {
			if(logger.isDebugEnabled()) logger.debug("La URL " + url + " es una URL sin subdominio");
			// En caso de que hayan metido una / al final, la elimino
			if(url.endsWith("/")) {
				url = url.substring(0, url.length()-1);
				logger.debug("Elimino la barra del final");
			}
			
			//tratamos según el puerto
			if (!(puerto == null)){
				
				result = url + ":" + puerto;
			}else{
				
				result = url;
			}
			
		}
		
//		//tratamos según el puerto
//		if ((puerto != null)&&!puerto.equals("")){
//			
//			result = url + ":" + puerto;
//		}else{
//			
//			result = url;
//		}
		logger.debug("Al componer la url tenemos "+result);
		//si no comienza por http:// o https://
		String patternURL = "^((http://)|(https://))";
		if (!url.matches(patternURL)){
			
			result = "http://" + result;
		}
		
		if(logger.isDebugEnabled()) logger.debug("URL y puerto : " + result);
		return result;
	}

	/** obtener el property* */
	public static String getPropertyValue(String sKey) {
		String sReturn = "";
		InputStream fIsSpringProperties=null;
		try {
			if (props == null) {
				fIsSpringProperties = DescomprimeYvalidaImpl.class.getResourceAsStream(FILE_NAME_PROPERTIES);
				Properties properties = new Properties();
				properties.load(fIsSpringProperties);
				props=properties;
			}
			sReturn = props.getProperty(sKey);
			DescomprimeYvalidaImpl.logger.debug("propiedad obtenida: " + sReturn);
		} catch (IOException e) {
			DescomprimeYvalidaImpl.logger.error(e);
		} finally {
			try {
				if (fIsSpringProperties!=null) {
					fIsSpringProperties.close();
				}
			} catch (IOException e) {
				DescomprimeYvalidaImpl.logger.error("No se pudo cerrar stream");
			}
		}
		// devolvemos la propiedad
		return sReturn;
	}

}
