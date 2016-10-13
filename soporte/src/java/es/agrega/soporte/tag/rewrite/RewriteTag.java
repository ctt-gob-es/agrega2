/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.rewrite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class RewriteTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RewriteTag.class);
	private String url=null;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		JspWriter out = pageContext.getOut();
	    		if (logger.isDebugEnabled()) logger.debug("LdapUserDetailsUtils.getSubdominio() es ["+LdapUserDetailsUtils.getSubdominio()+"]");
	    		try {
	    			URL urlObj = new URL(getUrl());
	    			logger.debug("La URL " + url + " es absoluta");
				    // Si es una URL completa, la imprimo tal cual
	    			out.print(getUrl());
	    		} catch (MalformedURLException e) {
					logger.debug("La URL " + url + " es relativa");
					// Si no, le concateno el subdominio en caso de que exista
					String urlRewritten = null;
					if(LdapUserDetailsUtils.getSubdominio()==null || "".equals(LdapUserDetailsUtils.getSubdominio())) {
						urlRewritten = getUrl();
					} else {
						urlRewritten = LdapUserDetailsUtils.getSubdominio();
						if(getUrl().startsWith("/")) urlRewritten = urlRewritten + getUrl();
						else urlRewritten = urlRewritten + "/" + getUrl();
					}
					if(!urlRewritten.startsWith("/")) urlRewritten = "/".concat(urlRewritten);
					else if(urlRewritten.startsWith("^/{2,}")) {
						urlRewritten = urlRewritten.replaceFirst("/{2,}", "/");
						if(logger.isDebugEnabled()) logger.debug("Trailing de las // iniciales");
					}
					if(logger.isDebugEnabled()) logger.debug("URL " + getUrl() + " reescrita a " + urlRewritten);
	    			out.print(urlRewritten);
				}
    		} catch (IOException e) {
    			logger.error(e);
    		}
    		return SKIP_BODY;	
	    }
	   
	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
}