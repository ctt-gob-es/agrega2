package es.agrega.soporte.tag.portada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class PortadaTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BARRA = "/";	
	private static final String HTTP = "http";
	private static final String DOSPUNTOS = ":";
	private static final String COMILLASIMPLE = "'";
	

	public int doStartTag() throws JspException {
	      try {

	    	  JspWriter out = pageContext.getOut();
	    	  String urlImagenPortada= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGEN_PORTADA);
	    	  String alt="Imagen Portada";
	    	//imagen horizontal
            /* int ancho=806;
             int alto=248;*/
        	 String url = HTTP + DOSPUNTOS + BARRA + BARRA + ((HttpServletRequest) pageContext.getRequest()).getServerName()+LdapUserDetailsUtils.getSubdominio()+BARRA+urlImagenPortada;
	    	 if(urlImagenPortada!=null)
	    	 {
	    	  out.println("<img src=\""+url+"\" alt=\"" + alt + "\">");
	    	 }
	  	    	
	      } catch (Exception ex) {
	         throw new JspTagException("PortadaTag: " + 
	            ex.getMessage());
	      }
	      return SKIP_BODY;
	   }
	   
 public int doEndTag() throws JspException{
		return SKIP_BODY; 
	}

}
