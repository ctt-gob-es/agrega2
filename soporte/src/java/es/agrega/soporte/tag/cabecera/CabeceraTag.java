package es.agrega.soporte.tag.cabecera;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;


import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class CabeceraTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreCompleto=null;
	private static Logger logger = Logger.getLogger(CabeceraTag.class);
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		JspWriter out = pageContext.getOut();
	    		
	    		if(LdapUserDetailsUtils.estaAutenticado()){ 
	    			String nombreC = LdapUserDetailsUtils.getNombreCompleto();
	    			out.println("<li><span id='identificacion'>"+nombreC+",</span></li>");
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