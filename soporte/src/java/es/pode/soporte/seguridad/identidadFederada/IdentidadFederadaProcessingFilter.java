/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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

package es.pode.soporte.seguridad.identidadFederada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;




public class IdentidadFederadaProcessingFilter extends AbstractProcessingFilter{ 

    //~ Static fields/initializers =====================================================================================

	private static Logger logger = Logger.getLogger(IdentidadFederadaProcessingFilter.class);
    private final static String ROLE_ANONYMOUS = "ANONYMOUS";
    private final static String ROLE_VISITANTE = "VISITANTE";
    private Class context = SecurityContextImpl.class;
	private static String PREFIJO_ROL_ACEGI="ROLE_";
	
    
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
    	
    	//Este filtro tendra que comprobar siempre si en el nodo del cual vien la peticion federada continua autenticado el usuario
    	//hay que validar los parametros que necesita
    	//nodoOrigen
    	//usuarioFederado: viene encriptado el usuario
    	//Se llamara al SrvIdentidadFederada del servicio adminUsuarios para comprobar si existe la sesión de acegi activa para el usuario que intenta hacer la operacion federada
    	//de esta forma se podrá hacer acciones federadas mientras que el usuario mantenga la sesión de acegi activa en el nodo origen.
    	//En el caso de que se realizen llamadas federadas (con parametro nodoOrigen y federado) y el usuario tenga un contexto de Acegi creado se ignoraran las peticiones federadas
    	//es decir siempre prevalecera el usuario del contexto de acegi de la maquina.
    	
    	
    	SecurityContext sc = null;
    	Authentication token = null;
    	boolean auntenticadoRoles = false;
    	HttpSession session = null;
    	HttpServletRequest httpRequest = null;
    	String usuarioFederado = null;
    	String nodoOrigen = null;
    	Object contextoSesion = null;
    	LdapUserDetailsAgrega.Essence user = null;
    //	UsuarioVO usuariovo = null;
    	GrantedAuthority[] permisosAnadir = null;
    	Collection resultadoNombres = null;
    	Collection resultado = null;
    	GrantedAuthority permisosUsuario[] = null;
    	Boolean tieneIdentidadFederada = null;
    
    	
    	//Lo primero que comprobará el filtro es si recibo los parametros usuarioFederado y nodoOrigen correspondientes a la identidad federada
    	
    	session = ((HttpServletRequest)request).getSession();
    	httpRequest = (HttpServletRequest)request;
    	//Recogida de parámetros
    	usuarioFederado = httpRequest.getParameter("federado");
    	nodoOrigen = httpRequest.getParameter("nodoOrigen");
    	if (logger.isDebugEnabled())
   			logger.debug("httpRequest usuarioFederado <"+usuarioFederado+">");
    	if (logger.isDebugEnabled())
   			logger.debug("httpRequest nodoOrigen <"+nodoOrigen+">");
    	contextoSesion = httpRequest.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
    	
    	if((nodoOrigen == null)||(usuarioFederado == null))
    	{
    		if (logger.isDebugEnabled())
       			logger.debug("No es identidad federada");
    		chain.doFilter(request, response);
    		return;
    	}
		//Chequeamos si esta activa la propiedad IDENTIDAD_FEDERADA del agrega.properties
		if(((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IDENTIDAD_FEDERADA))).equalsIgnoreCase("true"))
		{
			//Vamos a comprobar si tiene un contexto de seguridad creado con roles que no sean visitantes, este caso se puede dar si realmente esta 
			//autenticado en el nodo destino de la busqueda federada
			if(!this.estaAutenticado((SecurityContext)contextoSesion))
			{
				logger.debug("Sin contexto de seguridad creado");
			//Desencriptamos el usuarioFederado
			try
			{
				usuarioFederado = EncriptacionUtiles.desencriptar(usuarioFederado);
				logger.debug("Usuario desencriptado <"+usuarioFederado+">");
			}catch(Exception e)
			{
				logger.error("Se ha producido un error al desencriptar el usuario - ",e);
				//Si no podemos desencriptar el usuario no considero que sea identidad federado paso al siguiente filtro
				chain.doFilter(request, response);
		    	return;
			}
			if (logger.isDebugEnabled())
				logger.debug("Operacion federada");
			
			if (logger.isDebugEnabled())
				logger.debug("contextoSesion vale <"+contextoSesion+">");
			auntenticadoRoles = this.estaAutenticadoVisitante((SecurityContext)contextoSesion);
			if (logger.isDebugEnabled())
				logger.debug("auntenticadoRoles <"+auntenticadoRoles+">");
			if(!auntenticadoRoles)
			{
				//Llamamos al nodo origen para comprobar si ese usuario tiene una sesion activa en el otro nodo
				tieneIdentidadFederada = WrapperSrvIdentidadFederada.tieneIdentidadFederada(usuarioFederado, nodoOrigen);
					    		
				if(tieneIdentidadFederada)
					{
					if (logger.isDebugEnabled())
		       			logger.debug("Generamos el contexto de seguridad con el rol visitante para el usuario <"+usuarioFederado+">");
					try
					{
						
						user = new LdapUserDetailsAgrega.Essence();	
						user.setUsername(usuarioFederado);
			   			//Asigno algunos atributos del usuario que luego van a ser utilizado por el resto de módulos
			   			//como el idioma, idiomaNavegacion.
					    				
			   			user.setDatosUsuarioIdentidadFederada(usuarioFederado,nodoOrigen);
			   			permisosAnadir = new GrantedAuthority[1];
		   				permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
		   			
		   				
		   				if (logger.isDebugEnabled())
		           			logger.debug("Se recuperan los roles del usuario en la BBDD");
		   				resultadoNombres = new ArrayList();
		   				resultado = new ArrayList();		
				        resultadoNombres.add(ROLE_VISITANTE);
				        
				        // Se añaden los roles pasados por el usuario 
						if (permisosAnadir.length > 0)
						{
							for (int i = 0; i < permisosAnadir.length; i++)
							{
								resultadoNombres.add(permisosAnadir[i].getAuthority());
//								if (logger.isDebugEnabled())
//			               			logger.debug("Roles añadidos: <" + permisosAnadir[i].getAuthority()+">");
							}
						}
						
						// Reseteamos el contexto de Acegi			
						SecurityContextHolder.getContext().setAuthentication(null);
						
						if (resultadoNombres != null && !resultadoNombres.isEmpty())
						{
							String nombre = null;
							GrantedAuthority ga = null;
							
							for(Iterator it = resultadoNombres.iterator(); it.hasNext(); )
							{
								nombre = (String)it.next();
								ga = new GrantedAuthorityImpl(PREFIJO_ROL_ACEGI + nombre.toUpperCase());
//								if (logger.isDebugEnabled())
//			               			logger.debug("ROL añadido: " + PREFIJO_ROL_ACEGI + nombre.toUpperCase());
								resultado.add(ga);
							}
						}
						
		   				permisosUsuario = (GrantedAuthority[])resultado.toArray(new GrantedAuthority[resultado.size()]);
		   				
					 //Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario 
		   			 //Añado los roles al Essence
		   				user.setAuthoritiesIdentidadFederada(permisosUsuario, usuarioFederado, null);
		   				if (logger.isDebugEnabled())
		   					logger.debug("permisosUsuario <"+Arrays.toString(permisosUsuario)+">");
		   				token = new UsernamePasswordAuthenticationToken(user.createUserDetails(), null, permisosUsuario);
		   				//Creamos un nuevo contexto para añadirlo el nuevo token
		   				sc = (SecurityContext) this.context.newInstance();
		   				sc.setAuthentication(token);
		   				SecurityContextHolder.setContext(sc);
		   				
		   				if (logger.isDebugEnabled())
		    	   	 			logger.debug("securitycontext <"+sc+">");
		   			httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",sc);
		   			SecurityContextHolder.setContext(sc);
		    		chain.doFilter(request, response);
		    		return;
						
					}catch(Exception e)
					{
						logger.error("Error al generar el contexto de seguridad - ",e);
						logger.error("Response <"+response+">");
						chain.doFilter(request, response);
		   	    		return;
					}
				}
				logger.warn("El usuario <"+usuarioFederado+"> no esta autenticado en el nodo <"+nodoOrigen+">");
				//TODO Si tenía el contexto visitante creado habrá que eliminarlo
				//if(LdapUserDetailsUtils.tieneRolVisitante())
				if(LdapUserDetailsUtils.tieneIdentidadFederada((SecurityContext)contextoSesion,(HttpServletRequest)request))
				{
					try
					{
						logger.debug("El usuario <"+usuarioFederado+"> no esta autenticado en el nodo <"+nodoOrigen+">");
						//limpiamos el contexto de acegi y proseguimos con filtro
						SecurityContextHolder.getContext().setAuthentication(null); 
						logger.debug("Eliminamos el contexto de seguridad");
						httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());
					}catch(Exception e)
					{
						logger.error("Error al eliminar el contexto - "+e);
					}
				}
				chain.doFilter(request, response);
				return;
			}
			//Si esta autenticado y con roles y además los roles asociados son ANONIMO y VISITANTE tendre que validar si el usuario todavia tiene sesion activa
			//en el otro nodo, en caso afirmativo continuare con los filtros, en caso contrario limpiaré el contexto.
//			if (logger.isDebugEnabled())
//				logger.debug("Esta autenticado y tiene roles");
			if(LdapUserDetailsUtils.tieneIdentidadFederada((SecurityContext)contextoSesion,(HttpServletRequest)request))
			{
				if (logger.isDebugEnabled())
					logger.debug("Esta autenticado con rol_visitante");
				//Compruebo si tiene sesion activa en el nodo origen
				//Si la tiene continuo con la ejecución de los filtros
				//si no la tiene limpio el contexto de acegi
				
				tieneIdentidadFederada = WrapperSrvIdentidadFederada.tieneIdentidadFederada(usuarioFederado, nodoOrigen);
				if(tieneIdentidadFederada)
				{
					logger.debug("El usuario <"+usuarioFederado+"> esta autenticado en el nodo <"+nodoOrigen+">");
					chain.doFilter(request, response);
					return;
				}
				logger.debug("El usuario <"+usuarioFederado+"> no esta autenticado en el nodo <"+nodoOrigen+">");
				//limpiamos el contexto de acegi y proseguimos con filtro
				SecurityContextHolder.getContext().setAuthentication(null); 
				logger.debug("Eliminamos el contexto de seguridad");
				httpRequest.getSession().setAttribute("ACEGI_SECURITY_CONTEXT",SecurityContextHolder.getContext());
//	        	    		Continuo con la ejecucion de los filtros
				chain.doFilter(request, response);
				return;
				
			}
			if (logger.isDebugEnabled())
				logger.debug("Esta autenticado sin identidad federada");
			//Continuo con la ejecucion de los filtros
			chain.doFilter(request, response);
			return;
		}
			logger.debug("Tiene el contexto de seguridad creado");
//        		Continuo con la ejecucion de los filtros
			chain.doFilter(request, response);
			return;
  	}
		logger.debug("Inactiva la propiedad IDENTIDAD_FEDERADA");
//    		Continuo con la ejecucion de los filtros
		chain.doFilter(request, response);
		return;
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
//		if (logger.isDebugEnabled())
//			logger.debug("[OpenIDAuthenticationProcessingFilter] destroy");

	}

	public void init(FilterConfig arg0) throws ServletException
	{
//		 if (logger.isDebugEnabled())
//	 			logger.debug("[IdentidadFederadaProcessingFilter] init");
		
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
//	 private String getAgregaPropertyValue(String sKey) throws IOException
//		{
//
//			AgregaProperties properties = AgregaPropertiesImpl.getInstance();
//			// devolvemos la propiedad
//			if (logger.isDebugEnabled())
//				logger.debug("properties.getProperty(sKey) <"+properties.getProperty(sKey)+">");
//			return properties.getProperty(sKey);
//		}
	 
	  /** 
		 * Comprueba si esta autenticado con un rol_visitante
		 * @param ctx
		 * @return boolean
		 */
	 
	 private boolean estaAutenticadoVisitante(SecurityContext ctx)
 {

		boolean resultado = false;
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		if (!(ctx == null)) {
			Authentication auth = ctx.getAuthentication();
			logger.debug("El objeto Authentication tiene un valor <" + auth + ">");
			if (!(auth == null)) {
				GrantedAuthority[] roles = auth.getAuthorities();
				logger.debug("roles <" + Arrays.toString(roles)+">");
				if (!(roles == null)) {
					for (int i = 0; i < roles.length; i++) {
						//logger.debug("(GrantedAuthority)roles[i] " + roles[i]);
						if (roles[i].getAuthority().equalsIgnoreCase(
								"ROLE_VISITANTE")) {
							logger.debug("Tenemos rol visitente");
							resultado = true;

						}
					}
				} else {
					logger.debug("No tenemos roles");
				}
			}
		}
//		logger.debug("resultado vale <" + resultado+">");
		return resultado;
	}
	 
	 
	 /** 
		 * Comprueba si esta autenticado con un rol que no sea visitante
		 * @param ctx
		 * @return boolean
		 */
	 
	 private boolean estaAutenticado(SecurityContext ctx)
	    {
//		 if (logger.isDebugEnabled())
//				logger.debug("esta Autenticado?");
		
		boolean resultado = true;
  		if(!(ctx == null))
  		{
		Authentication auth = ctx.getAuthentication();
  		if(!(auth == null))
  		{
  			   	GrantedAuthority[] roles = auth.getAuthorities();
	        	logger.debug("roles <"+Arrays.toString(roles)+">");
	       		if(!(roles == null))
	    		{
	    			for(int i=0;i<roles.length;i++)
	    			{
	    				//logger.debug("(GrantedAuthority)roles[i] <"+roles[i]+">");
	    				if(roles[i].getAuthority().equalsIgnoreCase("ROLE_VISITANTE"))
	    				{
	    					logger.debug("Tenemos rol visitente");
	    					resultado=false;
	    					
	    				}
	      			}
	    		}else
	    		{
	    			logger.debug("No tenemos roles");
	    		}
  		}else
  		{
  			logger.debug("auth es null");
  			resultado = false;
  		}
  		}else
  		{
  			resultado = false;
  		}
//		logger.debug("resultado vale <"+resultado+">");
		return resultado;
	    }
	

	public String getDefaultFilterProcessesUrl()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
}
