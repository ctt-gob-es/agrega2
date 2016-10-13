package es.agrega.soporte.tag.i18nProperties;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.agrega.soporte.tag.cabecera.CabeceraTag;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;


public class I18nPropertiesTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String property = null;
	private static Logger logger = Logger.getLogger(I18nPropertiesTag.class);
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
	    		JspWriter out = pageContext.getOut();
	    		String idiomaNavegacion = this.getProperty();
	    		try
	    		{
	    			LocalizacionIdiomaVO[] localizaciones = I18n.getInstance().obtenerIdiomasBuscablesLocalizados(idiomaNavegacion);
	    			for(int i=0;i<localizaciones.length;i++)
	    			{
	    				String idLocalizacion = localizaciones[i].getIdLocalizacion();
	    				String nombre = localizaciones[i].getNombre();
	    				out.println("<option value='"+idLocalizacion+"'>"+nombre+"</option>");
	    			}
	    		}catch(Exception e)
	    		{
	    			out.println("<option value='es'><spring:message code='castellano'/></option>");
		    		out.println("<option value='ca'><spring:message code='catalan'/></option>");
		    		out.println("<option value='eu'><spring:message code='euskera'/></option>");
		    		out.println("<option value='en'><spring:message code='ingles'/></option>");
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
		
		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}
}