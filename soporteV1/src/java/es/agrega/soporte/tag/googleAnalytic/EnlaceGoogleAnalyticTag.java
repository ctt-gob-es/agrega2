/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.googleAnalytic;



import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;

public class EnlaceGoogleAnalyticTag extends BodyTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title = null;
	
	private String href= null;
	
	private String clase = null;
	
	private String textoEnlace= null;
	
	private String nombreAnalytic=null;
	
	private String target = null;

	public int doStartTag() throws JspException {
	      try {
	    	  JspWriter out = pageContext.getOut();
	    	  Integer googleAnalytic = ConfiguracionPortalImpl.getInstance().getGoogleAnalytic();
	    	  String enlace ="<a  ";
	    	  if(googleAnalytic.longValue()==1)
	    	  {
	    		  
	    			  enlace = enlace + "onclick=\"javascript:pageTracker._trackPageview('"+nombreAnalytic+"')\" ";
	    		 
	    	  }
	    	  enlace = enlace + "href=\""+href;
	    	  if(!(clase == null)&& !(clase == ""))
	    	  {
	    		  enlace = enlace + "\" class=\""+clase;
	    	  }
	    	  if(!(target == null)&& !(target == ""))
	    	  {
	    		  enlace = enlace + "\" target=\""+target;
	    	  }
	    	  if(!(title == null)&& !(title == ""))
	    	  {
	    		  enlace = enlace + "\" title=\""+title;
	    	  }
	    	  enlace = enlace + "\">"+textoEnlace+"</a>";
	    	  out.println(enlace);
	  	    	
	      } catch (Exception ex) {
	         throw new JspTagException("EnlaceGoogleAnalyticTag: " + 
	            ex.getMessage());
	      }
	      return SKIP_BODY;
	   }
	   
 public int doEndTag() throws JspException{
		return SKIP_BODY; 
	}

public String getClase() {
	return clase;
}

public void setClase(String clase) {
	this.clase = clase;
}

public String getHref() {
	return href;
}

public void setHref(String href) {
	this.href = href;
}

public String getNombreAnalytic() {
	return nombreAnalytic;
}

public void setNombreAnalytic(String nombreAnalytic) {
	this.nombreAnalytic = nombreAnalytic;
}

public String getTextoEnlace() {
	return textoEnlace;
}

public void setTextoEnlace(String textoEnlace) {
	this.textoEnlace = textoEnlace;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getTarget() {
	return target;
}

public void setTarget(String target) {
	this.target = target;
}

}