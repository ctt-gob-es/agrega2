/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.pode.soporte.seguridad.openId.ui.openid;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.ui.AbstractProcessingFilter;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.seguridad.ldap.BadCredentialsAgregaException;
import es.pode.soporte.seguridad.ldap.LdapAuthenticationException;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega;
import es.pode.soporte.seguridad.ldap.WrapperSrvAdminUsuarios;
import es.pode.soporte.seguridad.servicios.UsuarioVO;




public class PreviousProcessingFilter extends AbstractProcessingFilter{ 
//implements Filter { 
//extends AbstractProcessingFilter {
    //~ Static fields/initializers =====================================================================================

	private static Logger logger = Logger.getLogger(PreviousProcessingFilter.class);
    private static final String nombreCookieOpenId = "OPENID";
    private final static String ROLE_ANONYMOUS = "ANONYMOUS";
    private Class context = SecurityContextImpl.class;
    private static final String nombreCookieModificarPerfil = "MODIFICAR_PERFIL";

	private static final String sesionModificarPerfil = "perfilModificado";
	
	private static final String nombreDesconexion = "DESCONEXION";

	private static final String valorCookieDesconexion = "si";
	
   private static final String nombreCookieAuntenticado = "AUTENTICADO";
	
	private static final String valorCookieAuntenticado = "SI";
	private static final String sesionDesconexion = "desconexion";
	
    
    //~ Instance fields ================================================================================================

    

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
    }

    /**
	 * Filtra todas las peticiones de validación de openId
	 * @param request ServletRequest 
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException, ServletException
	 */
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
{
    	HttpSession session = ((HttpServletRequest)request).getSession();
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	SecurityContext sc = null;
    	boolean auntenticadoRoles = false;
    	 if (logger.isDebugEnabled())
  			logger.debug("nombreCookieOpenId: <"+this.getCookieValor(httpRequest, nombreCookieOpenId) + ">");
    	    Object contextoSesion = httpRequest.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");

    	  auntenticadoRoles = this.estaAutenticado((SecurityContext)contextoSesion);
    	  //El usuario tiene el contexto de Acegi creado con el rol visitante y el anónimo, si intenta acceder directamente a alguna url que necesite seguridad
    	  //le saldra la pagina de acceso prohibido
    	   //// auntenticadoRoles = this.estaAutenticado((SecurityContext)contextoSesion, httpRequest);
    	    if(!auntenticadoRoles)
    	    {
    	    	String valorCookie = this.getCookieValor(httpRequest, nombreCookieOpenId);
    	    	if(!(valorCookie == null) && !(valorCookie == "") && !(valorCookie == " "))
    	    	{
    	    		this.actualizaCookie((HttpServletRequest)request, (HttpServletResponse)response, nombreCookieOpenId);
    	    		LdapUserDetailsAgrega.Essence user = null;
    	   		    Authentication token = null;
    	   		    try
    	   		    {
    	   		    	String valorCifradoCookie = this.getCookieValor(httpRequest, nombreCookieOpenId);
    	   		    	if (logger.isDebugEnabled())
    	   		    		logger.debug("Valor cifrado cookie: <"+valorCifradoCookie+">");
    	   		    	if ((valorCifradoCookie.length()%4)!=0)
    	   		    	{
    	   		    		valorCifradoCookie +="=";
    	   		    		if (logger.isDebugEnabled())
    	   		    		logger.debug("Valor cifrado cookie correcto: <"+valorCifradoCookie +">");
    	   		    	}
    	   		    	
    	   		    valorCookie = EncriptacionUtiles.desencriptar(valorCifradoCookie);
    	   			try
    	   			{
    	   				//Se comprueba si la url de OpenId del usuario tiene una barra al final (el proveedor de OpenId lo puede devolver así), en ese caso se eliminaría
    	   				//antes de enviárselo a adminUsuarios
    	   				if((valorCookie.substring(valorCookie.length() -1)).equalsIgnoreCase("/"))
    	   				{
    	   				 valorCookie = valorCookie.substring(0,valorCookie.length() - 1);
    	   				}
    	   				
    	   			   UsuarioVO usuario = this.obtenerUsuario(valorCookie);

    	   				user = new LdapUserDetailsAgrega.Essence();							
    	   				user.setUsername(usuario.getUsuario());
    	   				user.setDatosUsuario(usuario.getUsuario());
    	   				
    	   				// Añadimos el rol anónimo 
    	   				GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
    	   				permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
    	   		
    	   				// Obtenemos los roles del usuario */
    	   				GrantedAuthority permisosUsuario[] = null;
    	   				permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario.getUsuario(), null);
    	   				
    	   				// Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario 
    	   			 //Añado los roles al Essence
    	   				user.setAuthorities(permisosUsuario, usuario.getUsuario(), null);
    	   				if (logger.isDebugEnabled())
    	   					logger.debug("permisosUsuario <"+Arrays.toString(permisosUsuario)+">");
    	   				token = new UsernamePasswordAuthenticationToken(user.createUserDetails(), null, permisosUsuario);
    	   				//Creamos un nuevo contexto para añadirlo el nuevo token
    	   				sc = (SecurityContext) this.context.newInstance();
    	   				sc.setAuthentication(token);
    	   				SecurityContextHolder.setContext(sc);
    	   				
    	   				if (logger.isDebugEnabled())
         	   	 			logger.debug("SecurityContext: <"+sc+">");
    	   				
    	   			}
    	   			catch (Exception e)
    	   			{
    	   				logger.error("Error al autenticar y añadir la seguridad al proceso: - " + e);
    	   				
    	   				
    	   			}
    	   			httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",sc);
    	   			SecurityContextHolder.setContext(sc);
    	    		chain.doFilter(request, response);
    	    		return;
    	   		    }catch(Exception e)
    	   		    {
    	   		    	logger.error("Exception al desencriptar - "+e);
    	   		    }
    	    	}else
    	    	{
        	    	 chain.doFilter(request, response);
        	    	 return;
    	    	}
    	    	 
    	    }else
    	    {
    	    	GrantedAuthority permisos[] = (((SecurityContext)contextoSesion).getAuthentication()).getAuthorities();
    	    	
    	    	if((permisos.length == 2)&&(permisos[1].getAuthority() == "errorNeedAttribute"))
    	    	{
    	    		SecurityContextHolder.getContext().setAuthentication(null); 
    	    		logger.debug("usuario dado de alta en ldap pero no en BD. Le faltan algunos datos en ldap - Eliminamos el contexto de seguridad");
    	    		httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());
    	    		logger.debug("lo metemos en la sesion");
    	    		
    	    		throw new LdapAuthenticationException();
    	    	}
				if((permisos.length == 2)&&(permisos[1].getAuthority() == "userAlreadyExist"))
    	    	{
    	    		SecurityContextHolder.getContext().setAuthentication(null); 
    	    		logger.debug("usuario en BD con mismos datos que el que se intenta registrar - Eliminamos el contexto de seguridad");
    	    		httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());
    	    		logger.debug("lo metemos en la sesion");
    	    		
    	    		throw new BadCredentialsAgregaException();
    	    	}
				//Compruebo si tiene cookie openid, en ese caso vuelvo a actualizar el timeout
    	    	String valorCookie = this.getCookieValor(httpRequest, nombreCookieOpenId);
    	    	if(!(valorCookie == null) && !(valorCookie == "") && !(valorCookie == " "))
    	    	{
    	    		this.actualizaCookie((HttpServletRequest)request, (HttpServletResponse)response, nombreCookieOpenId);
    	    	}
    	    	//Compruebo si tenemos la cookie DESCONEXION
    	    	String valorCookieDesconexion = this.getCookieValor(httpRequest, nombreDesconexion);
    	    	String cookieAuntenticado = this.getCookieValor((HttpServletRequest) request, nombreCookieAuntenticado);
    	    	String desconexion = (String)(httpRequest.getSession()).getAttribute(sesionDesconexion);
    	    	if(!(valorCookieDesconexion == null) && !(valorCookieDesconexion == "") && !(valorCookieDesconexion == " ") && !(valorCookieDesconexion.equals(desconexion)) && ((cookieAuntenticado == null)||!(valorCookieAuntenticado.equals(cookieAuntenticado))))
    	    	{
    	    		logger.debug("Tenemos cookie de desconexion pero no la de autenticado, limpiamos contexto");
    	    		this.modificacionContextoAcegiDesconexion(httpRequest);
    	    		//Introducimos en sesion una variable con el valor de la cookie de desconexión para que no se vuelva a ejcutar el anterior codigo
    	    		(httpRequest.getSession()).setAttribute(sesionDesconexion, valorCookieDesconexion);
    	    		
    	    	}
    	    	String cookieModificarPerfil = this.getCookieValor((HttpServletRequest) request, nombreCookieModificarPerfil);
    			String modficarPerfil = (String)(httpRequest.getSession()).getAttribute(sesionModificarPerfil);
    			if (cookieModificarPerfil != null && cookieModificarPerfil != "" && (!(cookieModificarPerfil.equals(modficarPerfil)))) // Existe la cookie de modificar perfil
    			{
//    				if (log.isDebugEnabled())
//         	 			log.debug("Modifico el contexto " + cookieModificarPerfil);
//   				if (log.isDebugEnabled())
//         	 			log.debug("ModificarPerfile vale " + modficarPerfil);
    				/* Modificación del contexto de Acegi con el nuevo perfil */
    				modificacionContextoAcegi(contextoSesion,httpRequest);
    				//Se anyade un valor a la sesion para no actualizarlo mas veces
    				(httpRequest.getSession()).setAttribute(sesionModificarPerfil, cookieModificarPerfil);
    			}
    	    	 chain.doFilter(request, response);
    	    	 return;
    	    }
    	     	   
}


    protected String buildReturnToUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public String getDefaultFilterProcessesUrl() {
        return "/j_spring_openid_security_check";
    }

    protected boolean isAuthenticated(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (auth != null) && auth.isAuthenticated();
    }

   
    public int getOrder() {
      //  return FilterChainOrder.OPENID_PROCESSING_FILTER;
     // Devuelve el orden del filtro, es el primero de todos
    	return 1;
    }
 
	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * Devuelve el valor de la cookie de OpenId
	 * @param ServletRequest 
	 * @param ServletResponse
	 * @param nombreCookie
	*/	
	private String getCookieValor(HttpServletRequest request, String nombreCookie) {
		Cookie cookie = null;
		String valor = null;

		cookie = getCookie(nombreCookie, request.getCookies());
		valor = cookie.getValue();
//		 if (log.isDebugEnabled())
//	 			log.debug("Valor cookie:" + valor);
		return valor;
	}
	
	/**
	 * Actualiza el timeout de la cookie de OpenId
	 * @param ServletRequest 
	 * @param ServletResponse
	 * @param nombreCookie
	 * @throws IOException
	*/
	private void actualizaCookie(HttpServletRequest request, HttpServletResponse response, String nombreCookie) throws IOException
	{
		Cookie cookie = null;

		cookie = getCookie(nombreCookie, request.getCookies());
		int caducidadCookie = (new Integer(this.getAgregaPropertyValue(AgregaProperties.TIMEOUTCOOKIEOPENID))).intValue();
//		if (log.isDebugEnabled())
//	 			log.debug("caducidadCookie "+caducidadCookie);
		cookie.setMaxAge(caducidadCookie);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/** 
	 * Recupera la información almacenada en una cookie
	 * @param name nombre de la cookie
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
	 * Recupera el usuario cuyo url de OpenId se pasa como parámetro
	 * @param openidUrl url de OpenId
	 * @return UsuarioVO
	 */
	private UsuarioVO obtenerUsuario(String openidUrl) {
		try {

			UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuarioOpenId(openidUrl);
			return datosUsuario;
		} catch (Exception e) {
			logger.error("se produce la excepcion - " + e);
			return null;
		}

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest arg0) throws AuthenticationException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * Recupera el valor de la propiedad que se pasa por parámetro del fichero de agrega.properties
	 * @param sKey
	 * @return String
	 */
	private String getAgregaPropertyValue(String sKey) throws IOException {

		// se devuelve la propiedad

		return AgregaPropertiesImpl.getInstance().getProperty(sKey);
	}
	 
	 private boolean estaAutenticado(SecurityContext ctx)
	    {

	        boolean correctCredentials = false;
	        if(ctx != null && 
	        ctx.getAuthentication() != null && 
	        ctx.getAuthentication().getPrincipal() != null && 
	        ctx.getAuthentication().getPrincipal() instanceof UserDetails)
	        	correctCredentials = true;
        	else
        		correctCredentials = false;
	        
	        return correctCredentials;
	    }
	 
	 
//	 private boolean estaAutenticado(SecurityContext ctx, HttpServletRequest request)
//	    {
//		 if (log.isDebugEnabled())
//				log.debug("estaAutenticado ");
//	        boolean correctCredentials = false;
//	        SecurityContext sc = null;
//	        if(ctx != null && 
//	        ctx.getAuthentication() != null && 
//	        ctx.getAuthentication().getPrincipal() != null && 
//	        ctx.getAuthentication().getPrincipal() instanceof UserDetails)
//	        {
//	        	if((request.getParameter("federado") == null) && (request.getParameter("nodoOrigen") == null))
//	        	{
//	        	log.debug("Vemos los roles del usuario");
//	           	GrantedAuthority[] autorities = (ctx.getAuthentication()).getAuthorities();
//	        	log.debug("autorities "+Arrays.toString(autorities));
//	        	correctCredentials=true;
//	        	for(int i=0; i< autorities.length ; i++)
//	        	{
//	        		if((autorities[i].getAuthority()).equalsIgnoreCase("ROLE_VISITANTE"))
//	        		{
//	        			try
//	        			{
//	        			correctCredentials=false;
//	        			//Limpiamos el contexto de seguridad antes de pasar al siguiente filtro
//	        			sc = (SecurityContext) this.context.newInstance();
//    	   				sc.setAuthentication(null);
//    	   				SecurityContextHolder.setContext(sc);
//    	   				if (log.isDebugEnabled())
//         	   	 			log.debug("sc [ "+sc+"]");
//    	   				request.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",sc);
//    	   				SecurityContextHolder.setContext(sc);
//	        			}catch(Exception e)
//	        			{
//	        				log.error("Error al eliminar el contexto de acegi "+e);
//	        			}
//    	   				
//    	   			}
//    	   		}
//	           	}else
//	        	{
//	        		correctCredentials=true;
//	        	}
//	        }	
//	        else
//	        	correctCredentials = false;
//	        
//	        return correctCredentials;
//	   
//}
	 
	 private void modificacionContextoAcegi(Object contexto,HttpServletRequest request) {

		 try
		 {
			 SecurityContext context = (SecurityContext)contexto;
			 String usuario = ((LdapUserDetailsAgrega)(context.getAuthentication().getPrincipal())).getLogin();
			 if (logger.isDebugEnabled())
				 logger.debug("el valor del usuario es " + usuario);
			 GrantedAuthority[] ldapGranted = ((LdapUserDetailsAgrega)(context.getAuthentication().getPrincipal())).getAuthorities();
			 Object creden = context.getAuthentication().getCredentials();
			 LdapUserDetailsAgrega.Essence user = new LdapUserDetailsAgrega.Essence((LdapUserDetailsAgrega) context.getAuthentication().getPrincipal());
			 //Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos del usuario
			 user.setDatosUsuario(usuario);
			 Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), creden, ldapGranted);
			 //modifico el contexto
			 SecurityContextHolder.getContext().setAuthentication(currentAuth);
			 request.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());
		 }catch(Exception e)
		 {
			 logger.error("Excepcion al modificar el contexto - "+e);
		 }
	}
	 
	private void modificacionContextoAcegiDesconexion(HttpServletRequest request) {

		String usuario = "";
		logger.debug("el valor del usuario es " + usuario);
		GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
		permisosAnadir[0] = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
		// GrantedAuthority[] ldapGranted = LdapUserDetailsUtils.getRoles();
		LdapUserDetailsAgrega.Essence user = null;
		user = new LdapUserDetailsAgrega.Essence();
		// Se crea un nuevo objeto LdapUserDetailsAgrega para machacar los datos
		// del usuario
		user.setUsername(usuario);
		Authentication currentAuth = new UsernamePasswordAuthenticationToken(user, null, permisosAnadir);
		request.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());

	}

	
}
