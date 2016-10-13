/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.empaquetador.tag.elementos;

import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class CodificadorURL extends TagSupport {

	private String url;
	private static Logger logger = Logger.getLogger(CodificadorURL.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2210056531767189553L;

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		String encoding = super.pageContext.getResponse().getCharacterEncoding();
		String finalUrl = url;
		if(encoding!=null && url!=null) {
			try {
				String encodedSlash = URLEncoder.encode("/", encoding);
				finalUrl = URLEncoder.encode(url, encoding);
				finalUrl = finalUrl.replaceAll(encodedSlash, "/");
				finalUrl = finalUrl.replaceAll("\\+", "%20");
				logger.debug("Url " + url + " codificada como " + finalUrl);
			} catch (Exception e) {
				logger.error("No se pudo codificar la url " + url + ". " + e.getLocalizedMessage());
				if(logger.isDebugEnabled()) logger.debug(e);
			}
		}
		JspWriter out = pageContext.getOut();
		try {
			out.write(finalUrl);
		} catch (Exception e) {
			logger.error("Error de escritura de JSP",e);
		}
		return SKIP_BODY;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
