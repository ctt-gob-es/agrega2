/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.agrega.soporte.tag.idiomasBanderas;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import org.apache.log4j.Logger;

public class IdiomasBanderasTag extends BodyTagSupport {
	private static Logger logger = Logger.getLogger(IdiomasBanderasTag.class);
	
	
	private String urlIdiomasBanderas = null;	
	private static final String INTERROGACION = "?";	
	private static final String TEXTOIDIOMA = "idioma";	
	private static final String IGUAL = "=";	
	private static final String BARRA = "/";	
	
	private static final long serialVersionUID = 1L;	
	
	public String getUrlIdiomasBanderas() {
		return urlIdiomasBanderas;
	}
	public void setUrlIdiomasBanderas(String urlIdiomasBanderas) {
		this.urlIdiomasBanderas = urlIdiomasBanderas;
	}


	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try
	    	{
	    		JspWriter out = pageContext.getOut();
	    		
	    		//Solo se pintan las banderas si el usuario esta logado
	    		if(!(LdapUserDetailsUtils.estaAutenticado()))
	    		{	    		
		    		logger.debug("El valor de la urlIdiomasBanderas es ["+urlIdiomasBanderas+"]");
		    		
		    		String urlIdiomaEn = "";
		    		String urlIdiomaVa = "";
					String urlIdiomaEu = "";
					String urlIdiomaGl = "";
					String urlIdiomaCa = "";
					String urlIdiomaEs = "";
					try {
						urlIdiomaEn = devuelveUrlBandera(urlIdiomasBanderas, "en");
						urlIdiomaVa = devuelveUrlBandera(urlIdiomasBanderas, "va");
						urlIdiomaEu = devuelveUrlBandera(urlIdiomasBanderas, "eu");
						urlIdiomaGl = devuelveUrlBandera(urlIdiomasBanderas, "gl");
						urlIdiomaCa = devuelveUrlBandera(urlIdiomasBanderas, "ca");
						urlIdiomaEs = devuelveUrlBandera(urlIdiomasBanderas, "es");
					} catch (Exception e) {
						logger.error("Error obteniendo la url del idioma", e);						
					}
					
					// Version con banderas. Las imagenes de las banderas estan en el proyecto static
					 		    			
					if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO)!=null && AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO).equals("true"))
						out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEn + "\" title=\"English\"  id=\"idi_ingles\"><span>Idioma: English</span></a>&nbsp;</li>");
					else
						out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEn + "\" title=\"English\"  id=\"idi_ingles\"><span>Idioma: English</span></a>&nbsp;|</li>");
		    		out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaVa + "\" title=\"Valenciá\"  id=\"idi_valenciano\"><span>Idioma: Valenciá</span></a></li>");
		    		out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEu + "\" title=\"Euskera\"  id=\"idi_vasco\"><span>Idioma: Euskera</span></a></li>");
		    		out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaGl + "\" title=\"Galego\"  id=\"idi_gallego\"><span>Idioma: Galego</span></a></li>");
		    		out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaCa + "\" title=\"Catalá\"  id=\"idi_catalan\"><span>Idioma: Catalá</span></a></li>");
		    		out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEs + "\" title=\"Castellano\"  id=\"idi_castellano\"><span>Idioma:Castellano</span></a></li>");
						    	
					
					//Version sin banderas; solo texto
		    		/*
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEs + "\" title=\"Castellano\" >Bienvenido</a></li>");
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaCa + "\" title=\"Catalá\" >Benvingut (C)</a></li>");
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaVa + "\" title=\"Valenciá\" >Benvingut (V)</a></li>");
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEu + "\" title=\"Euskera\" >Ongi Etorri</a></li>");
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaGl + "\" title=\"Galego\" >Benvido</a></li>");
					out.println("<li class=\"idiomas\"><a href=\"" + urlIdiomaEn + "\" title=\"English\" >Welcome</a>&nbsp;</li>");
					*/
	    		}	    		
	    	} catch (IOException e)	{    			
    			logger.error("Error en el tag de las banderas de los idiomas", e);
    		}
	    	return SKIP_BODY;	
	    }
	   
	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
		
	private String devuelveUrlBandera (String urlIdiomasBanderas, String codigoBandera) throws Exception
	{
		// Esta URL debe incluir subdominio en caso de que exista. El host lo obvio, ya que el navegador lo compone a partir de la pagina actual.
		String subdominio = null;
		String result = BARRA + urlIdiomasBanderas + INTERROGACION + TEXTOIDIOMA + IGUAL + codigoBandera; 
		subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		if(subdominio!=null && !"".equals(subdominio)) {
			if(subdominio.trim().substring(0).startsWith("${")) {
				logger.warn("A la variable <" + AgregaProperties.SUBDOMINIO + "> definida en el fichero properties <" + AgregaProperties.PROPERTIES_FILE_NAME + "> no se le asigno un valor valido.");
				logger.warn("Esto puede ser debido a que no se ejecuto el instalador de Agrega.");
			} else {
				result = subdominio + result;
				if(logger.isDebugEnabled()) logger.debug("URL de bandera = " + result);
			}
		}
		return result;
	}		
}