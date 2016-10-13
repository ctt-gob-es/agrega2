package es.agrega.soporte.tag.integracionLdap;

import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

public class IntegracionLdapTag extends BodyTagSupport {
	
	private static Logger logger = Logger.getLogger(IntegracionLdapTag.class); 
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	
	    try{
	    	
    		JspWriter out = pageContext.getOut();
    		ResourceBundle authback=ResourceBundle.getBundle("authbackend");
			String cas_url= authback.getString("cas.http.url");
			out.println(cas_url + "/logoutLdap");
    	    
    	     }catch (Exception e){
    	    	 logger.error("ERROR al llamar al servicio SrvGestionSqiService para obtener los nodos SQI de la tabla  "+e);
    	       
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
