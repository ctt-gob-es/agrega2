/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.serverProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import es.agrega.soporte.serverProperties.DependentServerProperties;
import es.agrega.soporte.serverProperties.DependentServerPropertiesItf;

/**
 * 
 * @author dgonzalezd
 * @deprecated
 */
public class DependentServerPropertiesTag extends TagSupport
{
	private String property = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9033947508450275700L;

	public int doStartTag() throws JspException {
	      try {
	    	  DependentServerPropertiesItf properties = DependentServerProperties.getInstance();	    	  
	    	  JspWriter out = pageContext.getOut();
	    	  out.print(properties.getProperty(this.getProperty()));
	      } catch (Exception ex) {
	         throw new JspTagException("DependentServerPropertiesTag: " + 
	            ex.getMessage());
	      }
	      return SKIP_BODY;
	   }
	   
   public int doEndTag() throws JspException{
		return SKIP_BODY; 
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}


