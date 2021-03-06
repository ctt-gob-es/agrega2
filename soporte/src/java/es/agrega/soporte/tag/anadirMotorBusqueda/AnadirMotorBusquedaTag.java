package es.agrega.soporte.tag.anadirMotorBusqueda;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.UnhandledException;

import org.apache.log4j.Logger;

import es.agrega.soporte.http.BrowserDetector;
import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class AnadirMotorBusquedaTag extends BodyTagSupport {
	
	private static Logger logger = Logger.getLogger(AnadirMotorBusquedaTag.class);	

	private static final String COOKIENAME = "motorBusquedaCookie";	
	
	private static final String FICHERO = "application-resources";
	private static final String LITERALTEXTO = "motor.busqueda.texto";
	private static final String BARRA = "/";	
	private static final String HTTP = "http";
	private static final String DOSPUNTOS = ":";
	private static final String COMILLASIMPLE = "'";
	private static final String LITERALBOTONACEPTAR = "motor.busqueda.boton.aceptar";
	private static final String LITERALBOTONCANCELAR = "motor.busqueda.boton.cancelar";	
	
	private static final String MOZILLA = "Mozilla";
	private static final String IEXPLORER = "MSIE";
	
	private String urlMotorBusqueda = null;
	private String navegador = null;
	
	private static final long serialVersionUID = 1L;	
	
	public String getUrlMotorBusqueda() {
		return urlMotorBusqueda;
	}
	public void setUrlMotorBusqueda(String urlMotorBusqueda) {
		this.urlMotorBusqueda = urlMotorBusqueda;
	}
	
	public String getNavegador() {
		return navegador;
	}
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {	    		
	    		
//	    		<!--  INICIO WIDGET   -->
//	    		<div class="widget">
//		    		<div class="capaflotante">
//			    		<span>Ejemplo de buscador</span>
//			    		<a href="#" class="buscar" title="Aceptar">Aceptar</a>
//			    		<a href="#" class="cancel" >Cancelar</a>
//			    		<br  class="oculto" />
//		    		</div>
//		    		<p>A�adir agrega a los motores de b�squeda de su navegador</p>
//		    		<br  class="oculto" />
//	    		</div>
//	    		<!--  FIN WIDGET   -->
//	    		<!--  FIN WIDGET   -->
	    		
	    		//Validamos el navegador
	    		String nameNavegator = "";
	    		float versionNavegator = 0;	    		
	    		BrowserDetector browser = new BrowserDetector(((HttpServletRequest)pageContext.getRequest()));	    		
	    			nameNavegator = browser.getBrowserName();
	    			versionNavegator = browser.getBrowserVersion();
	    		if (logger.isDebugEnabled()) logger.debug("El navegador es ["+nameNavegator+"] y su version es ["+versionNavegator+"]");
	    		
	    		JspWriter out = pageContext.getOut();	   
	    		String url = HTTP + DOSPUNTOS + BARRA + BARRA + LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+BARRA + urlMotorBusqueda;	    		
	    		String onClickAceptarPrevio = COMILLASIMPLE + HTTP + DOSPUNTOS + BARRA + BARRA + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio()+ "/searchPlugin/searchPlugin.xml" + COMILLASIMPLE;
	    		String onClickAceptar = "window.external.AddSearchProvider(" + onClickAceptarPrevio + ");document.getElementById('motorBusqueda').style.display='none';";
	    		String onClickCancel = "document.getElementById('motorBusqueda').style.display='none';";  		    		
	    		
	    		//Tratamiento de los textos internacionalizados	    		
				Locale locale = null;
				try {
					locale = devuelveLocale();
				} catch (Exception e1) {
					logger.error("Error obteniendo el locale", e1);					
				}
				String literalTexto = AnadirMotorBusquedaTag.getResource(LITERALTEXTO, FICHERO, locale);
				String literalBotonAceptar = AnadirMotorBusquedaTag.getResource(LITERALBOTONACEPTAR, FICHERO, locale);
				String literalBotonCancelar = AnadirMotorBusquedaTag.getResource(LITERALBOTONCANCELAR, FICHERO, locale);
					
	    		//Se hallan las cookies
	    		Cookie[] cookies = ((HttpServletRequest) pageContext.getRequest()).getCookies();
	    		Cookie cookieMotorBusqueda = getCookie(COOKIENAME, cookies);
	    		
	    		logger.debug("El valor de valorCookieMotor es ["+cookieMotorBusqueda.getValue()+"]");
	    		//Se pinta "a�adir buscador como motor de busqueda" si no habia sido a�adido previamente	    		
	    		
	    		//Para poder pintar el motor de busqueda el navegador tiene que ser firefox o internet explorer > 6
	    		
    		if(pintarMotorBusqueda(nameNavegator, versionNavegator, cookieMotorBusqueda.getValue()))
				{
				
				out.println("<!--  INICIO WIDGET   -->");	
				out.println("<!--  INICIO WIDGET   -->");	
				out.println("<div class=\"widget\" id=\"motorBusqueda\">");	    		
					out.println("<div class=\"capaflotante\">");
						out.println("<span>Ejemplo de buscador</span>");
						out.println("<a href=\"" + url + "\" onClick=\"" + onClickAceptar + "\" class=\"buscar\" title=" + literalBotonAceptar + ">"+ literalBotonAceptar +"</a>");
						out.println("<a href=\"" + url + "\" onClick=\"" + onClickCancel + "\"" +"class=\"cancel\" >"+ literalBotonCancelar +"</a>");
						out.println("<br  class=\"oculto\" />");
					out.println("</div>");
					out.println("<p>" + literalTexto + "</p>");
					out.println("<br  class=\"oculto\" />");
				out.println("</div>");
				out.println("<!--  FIN WIDGET   -->");
				out.println("<!--  FIN WIDGET   -->");
				}	
    		
	    		
	    	} catch (IOException e) {	    		
	    		logger.warn("Error en el tag de a�adir motor de busqueda",e);
	    	}
	    	return SKIP_BODY;	
	    }	    
	    /**
		 * doEndTag is called by the JSP container when the tag is closed
		 */
			public int doEndTag(){
				return SKIP_BODY; 
			}		
		
		/** 
		 * Recupera la informaci�n almacenada en una cookie
		 * @param nombre: nombre de la cookie
		 * @param cookies: cookies
		 */
		Cookie getCookie(String name, Cookie[] cookies) {
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals(name))
						return cookie;
				}
			}

			return new Cookie(name, "");
		}
		
		private boolean pintarMotorBusqueda (String nombreNavegador, float versionNavegador, String cookie) throws UnhandledException
		{
			boolean pintar = false;
			Integer plugginBusqueda = ConfiguracionPortalImpl.getInstance().getPlugginBusqueda();
			if (plugginBusqueda==1){
				
				try{			
				
					//El navegador es Internet explorer
					if (IEXPLORER.equals(nombreNavegador) && versionNavegador > 6.0 && "".equals(cookie)) pintar = true;
					//El navegador es mozilla
					else if (MOZILLA.equals(nombreNavegador) && "".equals(cookie)) pintar = true;				
				
				}catch (UnhandledException e)
				{
					logger.error("Error al intentar pintar el motor de busqueda",e);
				}
				return pintar;
			}
			return pintar;
			
		}
		
		public static String getResource(String key, String baseName, Locale locale){
	        String recurso = "";
	        ResourceBundle theResourceBundle = getResource(baseName,locale);
	        try{
	        	if (theResourceBundle!=null){
	               recurso = theResourceBundle.getString(key);
	           }
	        }catch (MissingResourceException mre){
	        	logger.error(mre);
	        	recurso = key;
	        }
	        return recurso;
	    }
		
		public static ResourceBundle getResource(String baseName, Locale locale){
	        try{
	        	return ResourceBundle.getBundle(baseName,locale);
	            
	        }catch (MissingResourceException mre){
	        	logger.error(mre);	
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
					logger.error("Error obteniendo el locale de la request",e);
				}
			}
			return locale;
		}
		
}