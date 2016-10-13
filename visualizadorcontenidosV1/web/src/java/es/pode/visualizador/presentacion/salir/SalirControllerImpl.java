// license-header java merge-point
package es.pode.visualizador.presentacion.salir;

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.visualizador.presentacion.portada.SessionCounter;

/**
 * @see es.pode.visualizador.presentacion.salir.SalirController
 */
public class SalirControllerImpl extends SalirController {

	private static Logger logger = Logger.getLogger(SalirControllerImpl.class);

	private java.util.Properties pSpringProperties = null;
	private java.util.Properties pSpringPropertiesAuth = null;
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";
	private static final String nombreCookieOpenId = "OPENID";


	/**
	 * @see es.pode.visualizador.presentacion.salir.SalirController#salir(org.apache.struts.action.ActionMapping,
	 *      es.pode.visualizador.presentacion.salir.SalirForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void salir(ActionMapping mapping, es.pode.visualizador.presentacion.salir.SalirForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "";
		
		//La eliminación de cookies debe ser común
		Cookie c = new Cookie("JSESSIONID", null);
		c.setPath("/");
		c.setMaxAge(0);
		response.addCookie(c);
		if(logger.isDebugEnabled())logger.debug("añado a la response la cookie JSESSIONID");
		Cookie cookieAutenticated = new Cookie("AUTENTICADO", null);
		cookieAutenticated.setPath("/");
		cookieAutenticated.setMaxAge(0);
		response.addCookie(cookieAutenticated);
		if(logger.isDebugEnabled())logger.debug("añado a la response la cookie AUTENTICADO");
		//Eliminacion de la cookie de modificacion
		Cookie cookiesPerfil = new Cookie("MODIFICAR_PERFIL", null);
		cookiesPerfil.setPath("/");
		cookiesPerfil.setMaxAge(0);
		response.addCookie(cookiesPerfil);
		if(logger.isDebugEnabled())logger.debug("despues de añadir la cookie cookiesModificarPerfil");
		
		//Eliminamos la cookie de openid
		Cookie cookiesOpenId = new Cookie(nombreCookieOpenId, null);
		cookiesOpenId.setPath("/");
		cookiesOpenId.setMaxAge(0);
		response.addCookie(cookiesOpenId);
		if(logger.isDebugEnabled())logger.debug("despues de añadir la cookie cookiesOpenId");
		
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO_TALLER)).equalsIgnoreCase("true"))
		{
			
			url=this.getAuthbackenPropertyValue("cas.http.url")+ "/logoutTaller";
		}else
		{
//			Cookie c = new Cookie("JSESSIONID", null);
//			c.setPath("/");
//			c.setMaxAge(0);
//			response.addCookie(c);
//			if(log.isDebugEnabled())logger.debug("añado a la response la cookie JSESSIONID");
//			Cookie cookieAutenticated = new Cookie("AUTENTICADO", null);
//			cookieAutenticated.setPath("/");
//			cookieAutenticated.setMaxAge(0);
//			response.addCookie(cookieAutenticated);
//			if(log.isDebugEnabled())logger.debug("añado a la response la cookie AUTENTICADO");
//			//Eliminacion de la cookie de modificacion
//			Cookie cookiesPerfil = new Cookie("MODIFICAR_PERFIL", null);
//			cookiesPerfil.setPath("/");
//			cookiesPerfil.setMaxAge(0);
//			response.addCookie(cookiesPerfil);
//			if(log.isDebugEnabled())logger.debug("despues de añadir la cookie cookiesModificarPerfil");
//			
//			//Eliminamos la cookie de openid
//			Cookie cookiesOpenId = new Cookie(nombreCookieOpenId, null);
//			cookiesOpenId.setPath("/");
//			cookiesOpenId.setMaxAge(0);
//			response.addCookie(cookiesOpenId);
//			if(log.isDebugEnabled())logger.debug("despues de añadir la cookie cookiesOpenId");
			
			/////response.sendRedirect("http://" + request.getServerName() + this.getPropertyValue("carServerLogout")+"?locale="+LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())logger.debug("cas.http.url "+this.getAuthbackenPropertyValue("cas.http.url"));
			
			String idioma = null;
			if(LdapUserDetailsUtils.estaAutenticado())
			{
				if(logger.isDebugEnabled())logger.debug("Cogemos el idioma del ldapuserdetails");
				idioma = LdapUserDetailsUtils.getIdioma();
			}
			String urlLogout = "";
//			if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO_TALLER)).equalsIgnoreCase("true"))
//			{
//				urlLogout = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGOUT_NODO_TALLER);
//			}else
//			{
				urlLogout = "http://"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+"/visualizadorcontenidos2/Portada/Portada.do";
//			}
			url = this.getAuthbackenPropertyValue("cas.http.url")+ "/logout?locale="+idioma+"&urlLogout="+urlLogout;
			SessionCounter.usuarioDesconectado();
			
			}
		if(logger.isDebugEnabled())logger.debug("url de salida "+url);
		//Eliminamos el usuario del registro de sesiones
		this.getSrvIdentidadFederadaService().deleteUserSession(LdapUserDetailsUtils.getUsuario());
		response.sendRedirect(url);
		
	}

	
	private String getAuthbackenPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/authbackend.properties");
		if (this.pSpringPropertiesAuth == null) {
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
	 * @param nombre nombre de la cookie
	 * @param cookies cookies
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