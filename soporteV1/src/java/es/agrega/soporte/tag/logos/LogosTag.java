/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.logos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.constantes.ConstantesAgrega;

public class LogosTag extends BodyTagSupport {

	private static final String DEFAULT_BUNDLE = "logos-resources"; 
	
	private static Map<String, String> cache = new HashMap<String, String>(2);
	
	private String bundleFile = null;
	
	private String htmlFile= null;
	
	private String language = null;
	
	private static Logger logger = null;
	
	private static final String EMPTY_STRING = "";

	public String getBundleFile() {
		return bundleFile;
	}
	public void setBundleFile(String bundleFile) {
		this.bundleFile = bundleFile;
	}
	public String getHtmlFile() {
		return htmlFile;
	}
	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language=language;
	}
	
	public LogosTag() {
		super();
		logger = Logger.getLogger(LogosTag.class);
	}
	
	/**
	 * Lee el fichero especificado por htmlFile y reemplaza las propiedades
	 * contenidas en el fichero bundleFile. El resultado es el HTML procesado
	 * para contener los mensajes internacionalizados del bundleFile.
	 */
	@Override
	public int doStartTag() throws JspException {
		
		if(bundleFile==null) {
			if(logger.isDebugEnabled()) logger.debug("Usando bundle por defecto: " + DEFAULT_BUNDLE);
			bundleFile = DEFAULT_BUNDLE;
		}
		
		try {
			JspWriter out = pageContext.getOut();
			String htmlCode = null;
			// Cargo el bundle de traducciones
			java.util.Locale locale = null;
			
			if(language==null) locale = (Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE); 
			else locale = new java.util.Locale(this.language);
			
			if(logger.isDebugEnabled()) logger.debug("Locale obtenido -> " + locale);
			
			htmlCode = obtenerTextoHTML(htmlFile, locale);	
			
			
			if(htmlCode!=null) out.println(htmlCode);
			
		} catch (Exception e) {
			logger.error("Error pintando el HTML de logos",e);
		}
		
		return SKIP_BODY;
	}
	
	
	private String obtenerTextoHTML(String htmlFile, Locale locale) throws Exception {
		
		String htmlKey = htmlFile + "_" + locale.getLanguage();
		String result = null;
		String cachedHTML = cache.get(htmlKey);
		ResourceBundle bundle = null;
		
		if(cachedHTML!=null) {
			result = cachedHTML;
			if(logger.isDebugEnabled()) logger.debug("HTML cacheado para " + htmlKey + " encontrado.");
		} else {
			if(logger.isDebugEnabled()) logger.debug("HTML no cacheado para " + htmlKey + ". Leo el fichero y lo proceso.");
			{
				bundle = ResourceBundle.getBundle(bundleFile, locale);
			}
			
			// Cargo el fichero HTML de pie de pagina
			java.io.File fHtmlFile = new java.io.File(htmlFile);
			if(logger.isDebugEnabled()) logger.debug("Fichero " + htmlFile + " exists = " + fHtmlFile.exists() + "; isFile = " + fHtmlFile.isFile() + "; path donde lo busca = " + fHtmlFile.getAbsolutePath());
			if(fHtmlFile.isFile()) {
				
				String rawHtmlCode = leeFichero(fHtmlFile);
				if(rawHtmlCode!=null) {
					if(bundle==null) {
						logger.error("No se ha podido leer el bundle de traducciones " + bundleFile);
					} else {
						// Obtengo todas la claves i18n del fichero de internacionalizacion de logos
						Enumeration<String> keys = bundle.getKeys();
						if(logger.isDebugEnabled()) logger.debug("Keys obtenidas de " + bundleFile + ": " + keys);
						
						while(keys.hasMoreElements()) {
							String key = keys.nextElement();
							String value = bundle.getString(key);
							if(logger.isDebugEnabled()) logger.debug("Reemplazando ${" + key + "} por '" + value + "'");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${"+key+"}\\E", value);
						}
					}
					// Sustitucion para logo de comunidad autónoma
					// propiedades urlConsejeriaNodo y ccaa
					String value = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_URLCONSEJERIANODO);
					
					// Si ULRConsejeriaNodo es nulo o vacio, ponemos # por defecto.
					if(value==null || value.trim().equals(EMPTY_STRING)) value = "#";
					// Si ULRConsejeriaNodo empieza por ${ quiere decir que no se ejecuto el script de instalacion
					else if(value.trim().substring(0).startsWith("${")) {
						logger.error("A la variable urlConsejeriaNodo definida en el fichero properties " + AgregaProperties.PROPERTIES_FILE_NAME + " no se le asigno un valor valido.");
						logger.error("Seguramente esto es debido a que no se ejecuto el instalador de Agrega.");
					} else {
						if(logger.isDebugEnabled()) logger.debug("Reemplazando ${urlConsejeria} por '" + value + "'");
						rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlConsejeria}\\E", value);
					}
						
					//Reemplazo del nombre de la comunidad autonoma
					value = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_CCAA);
					// Si ccaa empieza por ${ quiere decir que no se ejecuto el script de instalacion
					if(value.trim().substring(0).startsWith("${")) {
						logger.error("A la variable ccaa definida en el fichero properties " + AgregaProperties.PROPERTIES_FILE_NAME + " no se le asigno un valor valido.");
						logger.error("Seguramente esto es debido a que no se ejecuto el instalador de Agrega.");
					} else {
						if(logger.isDebugEnabled()) logger.debug("Reemplazando ${comunidad} por '" + value + "'");
						rawHtmlCode = rawHtmlCode.replaceAll("\\Q${comunidad}\\E", value);
					}
					
					// Reemplazo del atributo baseurl necesario para invocar el
					// flash de los logos del nodo neutro
					String baseUrl = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
					if(baseUrl==null) baseUrl = EMPTY_STRING;
					else if(baseUrl.trim().substring(0).startsWith("${")) {
						logger.error("A la variable " + AgregaProperties.SUBDOMINIO + " definida en el fichero properties " + AgregaProperties.PROPERTIES_FILE_NAME + " no se le asigno un valor valido.");
						logger.error("Seguramente esto es debido a que no se ejecuto el instalador de Agrega.");
					} else {
						if(logger.isDebugEnabled()) logger.debug("Reemplazando ${baseurl} por '" + baseUrl + "'");
						rawHtmlCode = rawHtmlCode.replaceAll("\\Q${baseurl}\\E", baseUrl);
					}
					
					result = rawHtmlCode;
					logger.debug("Resultado de procesar HTML de logos:\n"+result);
				} else { 
					logger.error("El fichero " + htmlFile + " no existe o no ha podido ser leido");
				}
				
			} else {
				logger.error("No se ha podido encontrar el HTML con los logos: " + htmlFile);
			}
			/*
			 * Cacheo el HTML parseado
			 */
			cache.put(htmlKey, result);
		}
		return result;
	}
	
	private String leeFichero(java.io.File fichero) throws Exception {
		String result = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(fichero));
		if(logger.isDebugEnabled()) logger.debug("Valor de reader : " + reader);
		if(reader!=null) {
			String line = reader.readLine();
			while(line!=null) {
				buffer.append(line).append("\n");
				line = reader.readLine();
			}
			if(buffer.length()>0) result = buffer.toString();
		}
		
		try {
			reader.close();
		} catch (Exception e) {
			logger.debug("No se ha podido cerrar el Stream a " + htmlFile,e);
		}
		
		return result;
	}
	
}
