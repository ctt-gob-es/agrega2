/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.acceso;

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.i18n.I18n;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @see es.pode.visualizador.presentacion.acceso.AccesoController
 */
public class AccesoControllerTomcatImpl extends AccesoController {

	private static Logger logger = Logger.getLogger(AccesoControllerTomcatImpl.class);
	private java.util.Properties pSpringProperties = null;
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";
	private static final String nombreDesconexion = "DESCONEXION";

	/**
	 * @see es.pode.visualizador.presentacion.acceso.AccesoController#validarAcceso(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.acceso.ValidarAccesoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void validarAcceso(ActionMapping mapping,
			es.pode.visualizador.presentacion.acceso.ValidarAccesoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Validamos el usuario");
		String urlCas = this.getPropertyValue("cas.http.url");
		if(logger.isDebugEnabled())logger.debug("urlCas "+urlCas);
		String idioma = "";	
		String cookieIdioma = this.getCookieValor(request, NOMBRECOOKIE);
		//Se comprueba la cookie
		if(cookieIdioma != null && !cookieIdioma.equals(""))
		{
			idioma = cookieIdioma;
		}else
		{
			if(logger.isDebugEnabled())logger.debug("Cojo el idioma del nodo ");
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("El idioma del cas "+idioma);
		
		
		// Coger URL portal adm. de contex-param : "/portaladministracion/PortadaAdministracion/PortadaAdministracion.do"
		String urlTotal = urlCas+"/login?locale="+idioma+"&service=http://"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+"/" + request.getSession().getServletContext().getInitParameter("url_portada_admin");
		
		//Codigo para tomcat
		Cookie cookie = new Cookie(nombreDesconexion, "si");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//codigo para tomcat
		
		
		response.sendRedirect(urlTotal);
	}

	
	 private String getPropertyValue(String sKey) throws IOException {
	    	
			InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/authbackend.properties");
			if (this.pSpringProperties == null) {
			    pSpringProperties = new java.util.Properties();
			    pSpringProperties.load(fIsSpringProperties);
			}

			// devolvemos la propiedad
			return pSpringProperties.getProperty(sKey);
	    }
	 
	 /** 
		 * Se recupera el valor almacenado de una determinada cookie
		 * @param request Objeto request
		 * @param nombreCookie Nombre de la cookie a recuperar
		 */
		private String getCookieValor(HttpServletRequest request, String nombreCookie) {
			Cookie cookie = null;
			String valor = null;

			if (logger.isDebugEnabled()) logger.debug("Se coge la cookie " + NOMBRECOOKIE);
			cookie = getCookie(nombreCookie, request.getCookies());
			valor = cookie.getValue();
			if (logger.isDebugEnabled()) logger.debug("Valor cookie:" + valor);
			return valor;
		}
		
		/** 
		 * Recupera la información almacenada en una cookie
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


}