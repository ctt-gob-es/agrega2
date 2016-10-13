package es.pode.buscador.tag.valoracion;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class ValoracionTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int estrellasMaximas=5;	
	private static final float MENOS_UNO = -1;
	
	private static Logger logger = Logger.getLogger(ValoracionTag.class);	
	
	private final static String SIN_VALORAR = "mostrar.resultados.busqueda.sin.valorar";
	private final static String FICHERO = "application-resources";
	private final static String DETALLAR = "detallar";
	
	private String valoracion=null;
	private String pagina=null;
	private String mensaje=null;
	private String urlBaseParaVotar=null;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getUrlBaseParaVotar() {
		return urlBaseParaVotar;
	}

	public void setUrlBaseParaVotar(String urlBaseParaVotar) {
		this.urlBaseParaVotar = urlBaseParaVotar;
	}
	
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		JspWriter out = pageContext.getOut();
	    		ServletRequest request = pageContext.getRequest();
	    		if(valoracion==null || ("").equals(valoracion)) {  
	    			valoracion = "0";
	    			//Pinto estrellas vacias
	    			for(int i=0; i<estrellasMaximas; i++){
	    				if (DETALLAR.equals(pagina))
	    					out.println("<li><img src='http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/static/Agrega2/img/star_03.gif' alt='estrella 05' title='"+ valoracion +"' /></li>");
	    				else
	    					out.println("<img src='http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/static/Agrega2/img/star_03.gif' alt='estrella 05' title='"+ valoracion +"' />");
	    			}
	    		} else {	    			
	    			Locale locale = null;
					try {
						locale = devuelveLocale(request);
					} catch (Exception e1) {
						logger.error("Error recuperando el locale",e1);
					}	    				
					String literal=ValoracionTag.getResource(mensaje, FICHERO, locale);		
	    			//Dos posibles casos: Que se pinte en la pantalla de resultados o en la pantalla de mostrar detalle ode
//	    			if(pagina.equals("detallar")){	    				
////	    				Pantalla de detallar
//	    				out.println("<div class=\"valoracion\" id=\"valor_b\" >");
//	    				out.println("<span >" + literal + ":&nbsp;</span>");
//		    				out.println("<ul id=\"res_val\">");	
////		    				Metodo que calcula las estrellas
//		    				calcularEstrellas(valoracion,out,request, pagina);		    					
//		    				out.println("</ul>");
//	    				out.println("</div>");	    								    				
//	    			}else{
//	    				
//	    				Metodo que calcula las estrellas
    					calcularEstrellas(valoracion, out, request, pagina, urlBaseParaVotar);		    						    			
	    					
//	    			}
	    		}
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			logger.error("ValoracionTag Error pintando los valores de las estrellas de valoracion",e);	    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			logger.error("ValoracionTag Error pintando los valores de las estrellas de valoracion",e);	    			
    		}
    		return SKIP_BODY;	
	    }
	    
	    public void calcularEstrellas(String valoracion,JspWriter out, ServletRequest request, String pagina, String urlBaseParaVotar) throws JspException{	    	
	    	
	    	try
	    	{
		    	//Comprobamos que la valoracion es distinta de null � ""
	    		if(valoracion!= null && !("").equals(valoracion)){
	//        		Paso de la variable valoracion que es un string a float
		    		Float valoracionFloat=Float.valueOf(valoracion);
		    		int valoracionInt=valoracionFloat.intValue();
		    		Float valFloat=new Float(valoracionInt);
		    		float valorDecimal = valoracionFloat.floatValue() - valFloat.floatValue();	    		
		    		
		    		//Si el valor decimal de la valoracion esta entre 0.3 y 0.8 se pinta media estrella. Si es menor o igual de 0.3 se redondea 
		    		//para abajo y si es mayor o igual de 0.8 se redondea para arriba.
		    		if(valorDecimal<=0.3 && valorDecimal>0.0) pintarEstrellas(valoracionInt, false, 5-valoracionInt,valoracionFloat,out,request, pagina, urlBaseParaVotar);
		    		else if (valorDecimal<0.8 && valorDecimal>0.3) pintarEstrellas(valoracionInt, true, 4-valoracionInt,valoracionFloat,out,request, pagina, urlBaseParaVotar);
		    		else if (valorDecimal>=0.8) pintarEstrellas(valoracionInt+1, false, 5-(valoracionInt+1),valoracionFloat,out,request, pagina, urlBaseParaVotar);
		    		else{
		    			if(valorDecimal==0.0) pintarEstrellas(valoracionInt, false, 5-valoracionInt,valoracionFloat,out,request, pagina, urlBaseParaVotar);
		    			else pintarEstrellas(0, false, 5,valoracionFloat,out,request, pagina, urlBaseParaVotar);	    			
		    		}	    		
	    		}else	pintarEstrellas(0,false,5,0f,out,request, pagina, urlBaseParaVotar);  
	    	}catch (Exception e)
	    	{
	    		logger.error("ValoracionTag - calcularEstrellas: Error calculando las estrellas",e);
	    	}
    		
	    }
	    
	    
	    public void pintarEstrellas(int enteras, boolean medias, int vacias, Float valoracion, JspWriter out, ServletRequest request, String pagina, String urlBaseParaVotar) throws JspException{	    	
	       	try
	    	{
//	    		Se comprueba si se introdujo valoracion
				if(valoracion.floatValue()==MENOS_UNO) {
//					String fichero="application-resources";
//					Locale locale = null;
//					try {
//						locale = devuelveLocale(request);
//					} catch (Exception e1) {
//						logger.error("Error obteniendo el locale de la request",e1);
//					}
//					String sinValorar=ValoracionTag.getResource(SIN_VALORAR, fichero, locale);
//	    			out.println(sinValorar);
					enteras=0;
					medias=false;
					vacias=5;
					valoracion=0f;
				}
//				else
//				{
					String httpHome="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
					int i=0;
					
			    	//Se pinta las estrellas enteras
					//NOTA: si se cumple DETALLAR.equals(pagina) quiere decir que se pintan estrellas para votar, eoc solo se pintan para ver la puntucion media
			    	for(i=0; i<enteras; i++) {
			    		if (DETALLAR.equals(pagina)) {
			    			out.println("<a title='"+(i+1)+" estrellas' href='"+httpHome+urlBaseParaVotar+"&amp;valoracion="+(i+1)+"'>");
//			    			out.println("<li><img src='http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/static/img/star_01.gif' alt='estrella 01' title='"+ valoracion +"' /></li>");
			    			out.println("<img src='"+httpHome+"/static/Agrega2/img/star_01.gif' alt='estrella 0"+(i+1)+"' title='"+ (i+1) +"' />");
			    			out.println("</a>");
			    		} else {  			
			    			out.println("<a><img src='"+httpHome+"/static/Agrega2/img/star_01.gif' alt='estrella 0"+(i+1)+"' title='"+ valoracion +"' /></a>");
			    		}
			    	}
			    	
			    	//Se pinta las medias estrellas si fuera el caso
			    	if(medias) {
			    		if (DETALLAR.equals(pagina)) {
			    			out.println("<a title='"+i+" estrellas' href='"+httpHome+urlBaseParaVotar+"&amp;valoracion="+i+"'>");
//			    			out.println("<li><img src='http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/static/img/star_02.gif' alt='estrella 04' title='"+ valoracion +"' /></li>");
			    			out.println("<img src='"+httpHome+"/static/Agrega2/img/star_02.gif' alt='estrella 0"+i+"' title='"+ i +"' />");
			    			out.println("</a>");
			    		} else {		    		
			    			out.println("<a><img src='"+httpHome+"/static/Agrega2/img/star_02.gif' alt='estrella 0"+i+"' title='"+ valoracion +"' /></a>");
		    			}
			    	}
			    	
			    	//Se pinta las estrellas vacias
			    	for(int j=0; j<vacias; j++) {
			    		if (DETALLAR.equals(pagina)) {
			    			out.println("<a title='"+(i+j+1)+" estrellas' href='"+httpHome+urlBaseParaVotar+"&amp;valoracion="+(i+j+1)+"'>");
//			    			out.println("<li><img src='http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/static/img/star_03.gif' alt='estrella 05' title='"+ valoracion +"' /></li>");
		    				out.println("<img src='"+httpHome+"/static/Agrega2/img/star_03.gif' alt='estrella 0"+(i+j+1)+"' title='"+ (i+j+1) +"' />");
			    			out.println("</a>");
			    		} else {	
			    			out.println("<a><img src='"+httpHome+"/static/Agrega2/img/star_03.gif' alt='estrella 0"+(i+j+1)+"' title='"+ valoracion +"' /></a>");
			    		}
			    	}
//				}
			    	
	    	}catch (IOException e){    			
	    		logger.error("ValoracionTag -  pintarEstrellas: ",e);
    		}catch (Exception e){    			
	    		logger.error("ValoracionTag -  pintarEstrellas: ",e);
    		}
	    }	    

	    
	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
		
		public static String getResource(String key, String baseName, Locale locale){
	        String recurso = "";
	        try{
	        	ResourceBundle theResourceBundle = getResource(baseName,locale);
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
	        }catch (Exception e){    			
	    		logger.error("ValoracionTag - getResource: Error obteniendo traducci�n",e);
	    		return null;
    		}
	    }
		
		private Locale devuelveLocale(ServletRequest request) throws Exception
		{
			Locale locale=null;
			if (request instanceof HttpServletRequest) {
				locale = (Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
				
			}
			else{ 	    					
				try {
					locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
				} catch (Exception e) {								
					logger.error("ValoracionTag - devuelveLocale:Error obteniendo el locale de la request en el metodo devuelveLocale",e);
				}
			}
			return locale;
		}
}