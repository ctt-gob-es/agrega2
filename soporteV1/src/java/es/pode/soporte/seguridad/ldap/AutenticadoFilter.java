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
import javax.servlet.http.HttpSession;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

/** 
 * Clase filtro para marcar al usuario que ha sido autenticado en algún módulo 
 * y para actualizar el perfil del usuario en la sesión activa.
 * 
 * @author jsimon
 */

public class AutenticadoFilter implements Filter {
	private static Logger log = Logger.getLogger(AutenticadoFilter.class);

	private static final String nombreCookieAutenticado = "AUTENTICADO";
	
	private static final String nombreCookieOpenId = "OPENID";

	private static final String valorCookieAutenticado = "SI";

	private static final String nombreCookieModificarPerfil = "MODIFICAR_PERFIL";

	private static final String sesionModificarPerfil = "perfilModificado";
	
	private static final String nombrePaginaInvalidarSesion = "invalidarsesion.jsp";

	
	public AutenticadoFilter() {
	}

	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void destroy() {
	}

	/** 
	 * Filtrado para marcar los usuarios que han sido autenticado en algún módulo. Primero se comprobará si existe la cookie de OpenId
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
	 *  5- Tiene rol visitante y no tiene cookie: No se pone la cookie de cas  
	 * @param request Objeto request
	 * @param response Objeto response
	 * @param chain Objeto filtro al que se devuelve el control
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AuthenticationException {
				
		HttpServletRequest reqHttp = (HttpServletRequest) request;
		// 20130903 Si la url solicitada es la página de invalidar sesión no hacemos nada en el filtro
		//			porque provocaba que se volviese a crear la cookie de Autenticado a SI.
		if (!reqHttp.getRequestURL().toString().endsWith(nombrePaginaInvalidarSesion))
		{
			boolean autenticadoRoles = LdapUserDetailsUtils.estaAutenticado();
			String cookieAutenticado = this.getCookieValor((HttpServletRequest) request, nombreCookieAutenticado);
			//Se comprueba si el usuario tiene identidad federada
			boolean tieneIdentidadFedera = LdapUserDetailsUtils.tieneRolVisitante();
			final HttpSession sesion = ((HttpServletRequest) request).getSession();
	//		logger.info("AutenticadoFilter");
			//Comprobamos si existe la cookie OpenId
			String cookieOpenId = this.getCookieValor((HttpServletRequest) request, nombreCookieOpenId);
			if((cookieOpenId == null)||(cookieOpenId == "")||(cookieOpenId== " "))
			{
			//No nos hemos logado como OpenId se comprobará la cookie AUTENTICADO y los roles
				
			if(!autenticadoRoles && valorCookieAutenticado.equals(cookieAutenticado) && tieneIdentidadFedera)
			{
				log.debug("Elimino el contexto de seguridad y lanzamos el accessdenied");
				//TODO COMPROBAR
				//Renovamos cookie
				setCookieAutenticado((HttpServletResponse) response);
				/////SecurityContextHolder.getContext().setAuthentication(null);
				generacionContextoAcegi((HttpServletRequest) request);
				//throw new AccessDeniedException("Autenticado en otro módulo y sin roles");
			}else
			{
				if(!autenticadoRoles && valorCookieAutenticado.equals(cookieAutenticado) && !tieneIdentidadFedera)
				{
					log.debug("No tiene roles y si tiene la cookie de autenticado. Se lanza la excepción");
					//TODO COMPROBAR
					//Renovamos cookie
					setCookieAutenticado((HttpServletResponse) response);
					//Limpio 
					throw new AccessDeniedException("Autenticado en otro módulo y sin roles");
				}else if (autenticadoRoles && !valorCookieAutenticado.equals(cookieAutenticado)) /* 4- Tiene Roles y no tiene cookie */
				{
	//				comprobamos si tiene el rol visitante, en ese caso no se marcaria la cookie de autenticado
					//Creo que ya no es necesario comprobar si es rol visitante
					log.debug("Comprobamos los roles del usuario");
					GrantedAuthority[] roles = LdapUserDetailsUtils.getRoles();
					log.debug("roles!!! "+Arrays.toString(roles));
					log.debug("roles.length "+roles.length);
					
						log.debug("Tiene roles y no tiene la cookie de autenticado. Se pone la cookie");
						setCookieAutenticado((HttpServletResponse) response);
					/*}*/
				}else if (!autenticadoRoles && !valorCookieAutenticado.equals(cookieAutenticado)) /* 1- No tiene Roles y no tiene cookie */
				{
					log.debug("No tiene Roles y no tiene cookie ");
				}else if (autenticadoRoles && valorCookieAutenticado.equals(cookieAutenticado)) /* 2- Tiene Roles y tiene cookie */
				{
					log.debug("Tiene Roles y tiene cookie");
					//TODO COMPROBAR
					//Renovamos cookie
					setCookieAutenticado((HttpServletResponse) response);
					
					/* Se comprueba si se ha modificado el perfil para actualizar el contexto de seguridad 
					 * Para actualizar el perfil: 
					 * 1- Tiene que existir la cookie 
					 * 2- Y el valor de la cookie tiene que ser igual a la variable perfilModificado de la sesión 
					 * */
					String cookieModificarPerfil = this.getCookieValor((HttpServletRequest) request, nombreCookieModificarPerfil);
					String modficarPerfil = (String) sesion.getAttribute(sesionModificarPerfil);
					if (cookieModificarPerfil != null && cookieModificarPerfil != "" && (!(cookieModificarPerfil.equals(modficarPerfil)))) // Existe la cookie de modificar perfil
					{
						log.debug("El perfil se ha modificado en esta sesión. cookieModificarPerfil: " + cookieModificarPerfil);
						log.debug("ModificarPerfile vale " + modficarPerfil);
						/* Modificación del contexto de Acegi con el nuevo perfil */
						modificacionContextoAcegi();
						log.debug("Se añade un valor a la sesion para no actualizarlo más veces");
						sesion.setAttribute(sesionModificarPerfil, cookieModificarPerfil);
					}
				}
			}
			}else
			{
				log.debug("Tenemos la cookie de openid no hacemos nada");
			}
		}
		log.debug("Antes de hacer el chain.doFilter");
		chain.doFilter(request, response);
	}

	private void setCookieAutenticado(HttpServletResponse response) {
		log.debug("COOKIE: Se pone la cookie " + nombreCookieAutenticado);
		Cookie cookie = new Cookie(nombreCookieAutenticado, valorCookieAutenticado);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_AUTENTICADO)));
		response.addCookie(cookie);

		log.debug("Despues de añadir la cookie " + nombreCookieAutenticado + ": " + cookie + " con valor: " + valorCookieAutenticado + " al objeto response");
	}

	/** 
	 * Se recupera el valor almacenado de una determinada cookie
	 * @param request Objeto request
	 * @param nombreCookie Nombre de la cookie a recuperar
	 */
	private String getCookieValor(HttpServletRequest request, String nombreCookie) {
		Cookie cookie = null;
		String valor = null;

		log.debug("Se coge la cookie " + nombreCookie);
		cookie = getCookie(nombreCookie, request.getCookies());
		valor = cookie.getValue();
		log.debug("Valor cookie:" + valor);
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

	/**
	 * Modificación el contexto de seguridad de Acegi con los datos nuevos del usuario 
	 * 
	 */

	private void modificacionContextoAcegi() {

		String usuario = LdapUserDetailsUtils.getUsuario();
		GrantedAuthority[] ldapGranted = LdapUserDetailsUtils.getRoles();
		Object creden = SecurityContextHolder.getContext().getAuthentication().getCredentials();
		LdapUserDetailsAgrega.Essence user = new LdapUserDetailsAgrega.Essence((LdapUserDetailsAgrega) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		log.debug("Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos del usuario");
		user.setDatosUsuario(usuario);
		Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), creden, ldapGranted);
		log.debug("currentAuth " + currentAuth);
		SecurityContextHolder.getContext().setAuthentication(currentAuth);
	}
	
	private void generacionContextoAcegi(HttpServletRequest request) {
		//Este metodo es utilizado para la modificacion del contexto de acegi cuando venimos del caso de que tenemos identidad federado
		log.debug("Obtencion del contexto de acegi con los nuevo roles");
		LdapUserDetailsAgrega.Essence user = null;
		Authentication token = null;
		SecurityContext sc = null;
		try
		{
			String usuario = LdapUserDetailsUtils.getUsuario();
			log.debug("usuario "+usuario);
			user = new LdapUserDetailsAgrega.Essence();							
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);
							
			if (log.isDebugEnabled())
	   	 		log.debug("Se añade el rol anónimo");
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
   			permisosAnadir[0] = new GrantedAuthorityImpl("ANONYMOUS");
   			if (log.isDebugEnabled())
   				log.debug("Se recuperan los roles del usuario en la BBDD");
   		
   				// Obtenemos los roles del usuario */
   			GrantedAuthority permisosUsuario[] = null;
   			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, null);
   				
   				// Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario 
   			 //Añado los roles al Essence
   			user.setAuthorities(permisosUsuario, usuario, null);
   			if (log.isDebugEnabled())
   					log.debug("permisosUsuario "+Arrays.toString(permisosUsuario));
   			token = new UsernamePasswordAuthenticationToken(user.createUserDetails(), null, permisosUsuario);
   				//Creamos un nuevo contexto para añadirlo el nuevo token
   			SecurityContextHolder.getContext().setAuthentication(token);
   		//	request.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",sc);
		}catch(Exception e)
		{
			log.error("Error al generar el contexto de acegi ",e);
		}
	}
	

	private void log(Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}
}