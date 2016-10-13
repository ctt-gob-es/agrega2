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
public class AccesoControllerImpl extends AccesoController {

	private static Logger log = Logger.getLogger(AccesoControllerImpl.class);
	private java.util.Properties pSpringProperties = null;
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";

	/**
	 * @see es.pode.visualizador.presentacion.acceso.AccesoController#validarAcceso(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.acceso.ValidarAccesoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void validarAcceso(ActionMapping mapping,
			es.pode.visualizador.presentacion.acceso.ValidarAccesoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log("Validamos el usuario");
		String urlCas = this.getPropertyValue("cas.http.url");
		log("urlCas "+urlCas);
		String idioma = "";	
		String cookieIdioma = this.getCookieValor(request, NOMBRECOOKIE);
		//Se comprueba la cookie
		if(cookieIdioma != null && !cookieIdioma.equals(""))
		{
			idioma = cookieIdioma;
		}else
		{
			log("Cojo el idioma del nodo ");
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		log("El idioma del cas "+idioma);
		
		// Redirigir a   "/portaladministracion/PortadaAdministracion/PortadaAdministracion.do  partir de parametro definido en web.xml"
		String urlTotal = urlCas+"/login?locale="+idioma+"&service=http://"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+ "/" + request.getSession().getServletContext().getInitParameter("url_portada_admin");
		response.sendRedirect(urlTotal);
	}

	private void log(Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
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

			if (log.isDebugEnabled()) log.debug("Se coge la cookie " + NOMBRECOOKIE);
			cookie = getCookie(nombreCookie, request.getCookies());
			valor = cookie.getValue();
			if (log.isDebugEnabled()) log.debug("Valor cookie:" + valor);
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