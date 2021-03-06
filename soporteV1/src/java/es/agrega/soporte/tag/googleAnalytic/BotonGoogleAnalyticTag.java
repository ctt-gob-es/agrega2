package es.agrega.soporte.tag.googleAnalytic;



import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;

public class BotonGoogleAnalyticTag extends BodyTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clase = null;
	
	private String name= null;
	
	private String type = null;
	
	private String value= null;
	
	private String nombreAnalytic =null;
	
	

	public int doStartTag() throws JspException {
	      try {
	    	
	    	  JspWriter out = pageContext.getOut();
	    	  Integer googleAnalytic = ConfiguracionPortalImpl.getInstance().getGoogleAnalytic();
	    	  String boton ="<input  ";
	    	  if(googleAnalytic.longValue()==1)
	    	  {
	    		  
	    			boton = boton + "onclick=\"javascript:pageTracker._trackPageview('"+nombreAnalytic+"')\" ";
	    		 
	    	  }
	    	  boton = boton + "\" class=\""+clase;
	    	  boton = boton + "\" name=\""+name;
	    	  boton = boton + "\" type=\""+type;
	    	  boton = boton + "\" value=\""+value;
	    	  boton = boton + "\" class=\""+clase+"\" name=\""+name+"\" type=\""+type+"\"+ value=\""+value+"\"/>";
	            
	       	    	 
	    	  out.println(boton);
	  	    	
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getNombreAnalytic() {
	return nombreAnalytic;
}

public void setNombreAnalytic(String nombreAnalytic) {
	this.nombreAnalytic = nombreAnalytic;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

}