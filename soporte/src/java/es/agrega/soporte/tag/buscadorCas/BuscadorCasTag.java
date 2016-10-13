package es.agrega.soporte.tag.buscadorCas;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.soporte.nodosSQI.servicio.NodoSQIVO;
import es.pode.soporte.nodosSQI.servicio.SrvGestionSqiService;
import es.pode.soporte.nodosSQI.servicio.SrvGestionSqiServiceServiceLocator;

public class BuscadorCasTag extends BodyTagSupport {
	
	private static Logger logger = Logger.getLogger(BuscadorCasTag.class); 
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	
	    try{
	    	
    		SrvGestionSqiServiceServiceLocator serviceLocator = new SrvGestionSqiServiceServiceLocator();						
    		SrvGestionSqiService servicio = null;
    		JspWriter out = pageContext.getOut();
    		
    			/* Para la invocacion del ws con seguridad*/
    			servicio = serviceLocator.getSrvGestionSqiService();
    	                        
    	        NodoSQIVO[]	nodos = servicio.obtenerNodosSQI();
    	        if (logger.isDebugEnabled())logger.debug("Numero de nodos obtenidos: " + nodos.length);
    	        if(nodos!=null && nodos.length>0){
    	        	out.println("<input type='radio' name='tipoBusqueda' value='04' id='todo_agrega' class='botonradio'><label for='todo_agrega'>Otros</label>");
    	        }
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
