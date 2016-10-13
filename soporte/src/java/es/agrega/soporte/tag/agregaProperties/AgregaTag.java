/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.tag.agregaProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class AgregaTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String property = null;

	public int doStartTag() throws JspException {
	      try {
	    	  JspWriter out = pageContext.getOut();
	    	  //Vamos a comprobar si la propiedad enviada es registroCas, según sea su valor
	    	  //sacaremos o no el texto para el registro
//	    	  if(this.getProperty().equalsIgnoreCase("registroCas"))
//	    	  {
//	    		  if(properties.getProperty("registroCas").equalsIgnoreCase("no"))
//	    		  {
//	    			  out.print("<!--");
//	    		  }
//	    	  }else
//	    	  {
//	    		 if(this.getProperty().equalsIgnoreCase("registroCasfinal"))
//	    		 {
//	    			 if(properties.getProperty("registroCas").equalsIgnoreCase("no"))
//		    		  {
//	    				 out.print("-->");
//		    		  }
//	    		 }else
//	    		 {
//	    			 if(this.getProperty().equalsIgnoreCase("idSinRegistro"))
//	    			 {
//	    				 if(properties.getProperty("registroCas").equalsIgnoreCase("no"))
//	   	    		  	{
//	    					 out.print("id='a_f_1_sin'"); 
//	   	    		  	}
//	    				 
//	    			 }else
//	    			 {
//	    				 if(this.getProperty().equalsIgnoreCase("idFiltro"))
//	    				 {
//	    					 if(properties.getProperty("registroCas").equalsIgnoreCase("no"))
//	    		    		  {
//	    						 out.print("id='acceso_filtro_sin'");
//	    		    		  }
//	    					 
//	    				 }else
//	    				 {
	    					 out.print( AgregaPropertiesImpl.getInstance().getProperty(this.getProperty())); 
//	    				 }
//	    			 }
//	    		 }
//	    	  }
	  	    	
	      } catch (Exception ex) {
	         throw new JspTagException("AgregaPropertiesTag: " + 
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