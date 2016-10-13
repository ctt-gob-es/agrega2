/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.ldap;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.log4j.Logger;

/** 
 * Clase filtro para marcar al usuario que ha sido auntenticado en algún módulo 
 * y para actualizar el perfil del usuario en la sesión activa.
 * 
 * @author jsimon
 */

public class DesconexionFilter implements Filter {
	private static Logger logger = Logger.getLogger(DesconexionFilter.class);

	private static final String nombreCookieAuntenticado = "AUTENTICADO";
	
	private static final String nombreDesconexion = "DESCONEXION";

	private static final String valorCookieAuntenticado = "SI";

	private static final String valorCookieDesconexion = "si";
	
	private Class context = SecurityContextImpl.class;


	
	public DesconexionFilter() {
	}

	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void destroy() {
	}

	/** 
	 * Filtrado para marcar los usuarios que han sido auntenticado en algún módulo. Primero se comprobará si existe la cookie de OpenId
	 * en ese caso no se creará la cookie AUTENTICADO ni se comprobará si tiene roles
	 * Casos:
	 * 	1- No tiene Roles y no tiene cookie: Usuario anónimo -> No se hace nada 
	 * 	2- Tiene Roles y tiene cookie: Usuario autenticado y con los roles..
	 * 		1. No se ha modificado el perfil -> No se hace nada 
	 *      2. Si se ha modificado el perfil -> Se actualiza el contexto de seguridad
	 *      	Existe la cookie MODIFICAR_PERFIL 
	 *      	Y el valor de la cookie tiene que ser igual a la variable perfilModificado de la sesión  
	 *  	 
	 * 	3- No tiene Roles y tiene cookie: Usuario autenticado en otro módulo y no tiene los roles -> Se lanza la excepción para que vaya al CAS y luego carge los roles. 
	 *  4- Tiene Roles y no tiene cookie: Usuario que acaba de ser autenticado en el CAS -> Se le pone la cookie.    
	 * @param request Objeto request
	 * @param response Objeto response
	 * @param chain Objeto filtro al que se devuelve el control
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AuthenticationException {
		logger.info("DesconexionFilter");
		/*
		boolean auntenticadoRoles = false;
		if(!(LdapUserDetailsUtils == null))
		{
			auntenticadoRoles = LdapUserDetailsUtils.estaAutenticado();
		}
		*/
		String cookieAuntenticado = this.getCookieValor((HttpServletRequest) request, nombreCookieAuntenticado);
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		SecurityContext sc = null;
		Authentication token = null;
		if(logger.isDebugEnabled())logger.debug("cookieAuntenticado vale"+cookieAuntenticado);
		//Comprobamos si existe la cookie Desconexion
		String cookieDesconexion = this.getCookieValor((HttpServletRequest) request, nombreDesconexion);
		if(logger.isDebugEnabled())logger.debug("cookieDesconexion ["+cookieDesconexion+"]");
		if(!((cookieDesconexion == null)||(cookieDesconexion == "")||(cookieDesconexion== " ")))
		{
			if(logger.isDebugEnabled())logger.debug("Tenemos cookie desconexion");
			if((cookieAuntenticado == null)||!(valorCookieAuntenticado.equals(cookieAuntenticado)))
			{
				if(logger.isDebugEnabled())logger.debug("Limpiamos el contexto");
				SecurityContextHolder.clearContext();
			
			if(logger.isDebugEnabled())logger.debug("Modificamos el contexto de acegi");
			token = modificacionContextoAcegi();
			if(logger.isDebugEnabled())logger.debug("Obtenemos el token");
			try
			{
			sc = (SecurityContext) this.context.newInstance();
			if(logger.isDebugEnabled())logger.debug("sc "+sc);
			sc.setAuthentication(null);
			SecurityContextHolder.setContext(sc);
			httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",sc);
   			SecurityContextHolder.setContext(sc);
   			if(logger.isDebugEnabled())logger.debug("SecurityContextHolder.getContext() "+SecurityContextHolder.getContext());
   			  					
			}catch(Exception e)
			{
				logger.error("Error "+e);
				
			}
						
			}
		
		}
		chain.doFilter(request, response);
	}



	/** 
	 * Se recupera el valor almacenado de una determinada cookie
	 * @param request Objeto request
	 * @param nombreCookie Nombre de la cookie a recuperar
	 */
	private String getCookieValor(HttpServletRequest request, String nombreCookie) {
		Cookie cookie = null;
		String valor = null;

		if(logger.isDebugEnabled())logger.debug("Se coge la cookie " + nombreCookie);
		cookie = getCookie(nombreCookie, request.getCookies());
		if(cookie == null)
		{
			return null;
		}
		valor = cookie.getValue();
		if(logger.isDebugEnabled())logger.debug("Valor cookie:" + valor);
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

		return null;
	}

	
	Authentication modificacionContextoAcegi() {


//		if(logger.isDebugEnabled())logger.debug("Vamos a modificar el contexto de acegi");
		Authentication currentAuth = null;
		String usuario = "";
		if(SecurityContextHolder.getContext().getAuthentication() == null)
		{
			if(logger.isDebugEnabled())logger.debug("LdapUserDetailsUtils == null. Vamos a modificar el contexto de acegi. Usuario es <" + usuario+">");
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
			//GrantedAuthority[] ldapGranted = LdapUserDetailsUtils.getRoles();
			LdapUserDetailsAgrega.Essence user = null;
			user = new LdapUserDetailsAgrega.Essence();	
			if(logger.isDebugEnabled())logger.debug("Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos del usuario");
			user.setUsername(usuario);
			currentAuth = new UsernamePasswordAuthenticationToken(user, null, permisosAnadir);
		}else
		{
			if(logger.isDebugEnabled())logger.debug("LdapUserDetails distinto de null");
			if((!(SecurityContextHolder.getContext().getAuthentication() == null)))
			{
				usuario = LdapUserDetailsUtils.getUsuario();
			}
			if(logger.isDebugEnabled())logger.debug("el valor del usuario es " + usuario);
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
			Object creden = SecurityContextHolder.getContext().getAuthentication().getCredentials();
			LdapUserDetailsAgrega.Essence user = new LdapUserDetailsAgrega.Essence((LdapUserDetailsAgrega) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			logger.debug("Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos del usuario");
			user.setDatosUsuario(usuario);
			if(logger.isDebugEnabled())logger.debug("Se modifican los datos del usuario. Permisos del user " + Arrays.toString(user.getGrantedAuthorities()));
			currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), creden, permisosAnadir);
			if(logger.isDebugEnabled())logger.debug("currentAuth " + currentAuth);
			SecurityContextHolder.getContext().setAuthentication(currentAuth);
		}
			
		return currentAuth;
		/*
		log("Vamos a modificar el contexto de acegi");
		String usuario = "";
		log("el valor del usuario es " + usuario);
		GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
		permisosAnadir[0] = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
		//GrantedAuthority[] ldapGranted = LdapUserDetailsUtils.getRoles();
		LdapUserDetailsAgrega.Essence user = null;
		user = new LdapUserDetailsAgrega.Essence();	
		log("Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos del usuario");
		user.setUsername(usuario);
		Authentication currentAuth = new UsernamePasswordAuthenticationToken(user, null, permisosAnadir);
		
		return currentAuth;
		*/
		
		
		
	}
	
	private void setCookieAutenticado(HttpServletResponse response) {
		if(logger.isDebugEnabled())logger.debug("Se pone la cookie " + nombreCookieAuntenticado);
		Cookie cookie = new Cookie(nombreCookieAuntenticado, valorCookieAuntenticado);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);

		if(logger.isDebugEnabled())logger.debug("Despues de añadir la cookie <" + nombreCookieAuntenticado + ">: <" + cookie + "> con valor: <" + valorCookieAuntenticado + "> al objeto response");
		
	}
}