/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.reputacion.tag.valoracion;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.pode.reputacion.presentacion.insertar.InsertarComentarioControllerImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;

import org.apache.log4j.Logger;

public class ValoracionTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(ValoracionTag.class);
	private static final long serialVersionUID = 1L;
	private static final int estrellasMaximas=5;
	private static final String MENOS_UNO = "-1.0";
	
	private final static String SIN_VALORAR = "introducir.comentarios.ode.sin.valoracion";	
	
	private String valoracion = null;	
	private String mensaje = null;
	

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
		    		JspWriter out = pageContext.getOut();
		    		
		    		out.println("<div class=\"valoracion\" >");
    				String fichero="application-resources";
    				Locale locale = null;
    				try {
    					locale = devuelveLocale();
    				} catch (Exception e) {
    					logger.error(e);
    				}
					String literal=this.getResource(mensaje, fichero, locale);
					String sinValorar=this.getResource(SIN_VALORAR, fichero, locale);
					
    				out.println("<span >" + literal + ":&nbsp;</span>");
    				
    				//Se comprueba si se introdujo valoracion
    				if(MENOS_UNO.equals(valoracion)) {
		    			out.println(sinValorar);
					}
    				else{
		    			out.println("<ul>");				    		
					    		if(valoracion==null){  
					    			valoracion = "0";
					    			//Pinto estrellas vacias
					    			for(int i=0; i<estrellasMaximas; i++){
					    				out.println("<img src='/static/img/star_03.gif' alt='estrella 05' />");
					    			}
					    		}				    		
					    		else{		    				    			
				    	    		int contador=0;
				    	    		//    		Paso de la variable valoracion que es un string a float
				    	    		Float valoracionFloat=Float.valueOf(valoracion);
				    	    		int valoracionInt=valoracionFloat.intValue();
				    	    		Float valFloat=new Float(valoracionInt);
				    	    		for (int i= 0; i<valoracionInt;i++)
				    	    		{
				    	    			out.println("<img src='/static/img/star_01.gif' alt='estrella 01' />");
				    					contador++;
				    	    		}
				    	    		if (valoracionFloat.floatValue()>valFloat.floatValue())
				    	    		{
				    	    			out.println("<img src='/static/img/star_02.gif' alt='estrella 04' />");
				    					contador++;
				    	    		}
				    	    		if(contador != estrellasMaximas)
				    	    		{
				    	    			for(int j= contador; j<estrellasMaximas;j++)
				    	    			{
				    	    				out.println("<img src='/static/img/star_03.gif' alt='estrella 05' />");
				    					
				    	    			}
				    	
				    	    		}
					    				    				
					    		}
    				
				    		
			    		out.println("</ul>");
    				}
    				out.println("</div>");   
	    			
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
	
//		MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
		public static String getResource(String key, String baseName, Locale locale){
	        String recurso = "";
	        ResourceBundle theResourceBundle = getResource(baseName,locale);
	        try{
	        	if (theResourceBundle!=null){
	               recurso = theResourceBundle.getString(key);
	           }
	        }catch (MissingResourceException mre){
	        	recurso = key;
	        }
	        return recurso;
	    }
		
		public static ResourceBundle getResource(String baseName, Locale locale){
	        try{
	        	return ResourceBundle.getBundle(baseName,locale);
	            
	        }catch (MissingResourceException mre){
	        		locale = new Locale("","");
	        		return ResourceBundle.getBundle(baseName,locale);
	             
	        }
	    }
		
		private Locale devuelveLocale() throws Exception
		{
			Locale locale=null;
			if (pageContext.getRequest() instanceof HttpServletRequest) {
				locale = (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
				
			}
			else{ 	    					
				try {
					locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
				} catch (Exception e) {								
					logger.error(e);
				}
			}
			return locale;
		}
		
}