package es.pode.soporte.seguridad.ldap;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.servicios.UsuarioPublicoVO;
import es.pode.soporte.seguridad.servicios.UsuarioVO;

/**
 * Clase de utilidades para el user details propio
 * */
public class LdapUserDetailsUtils {
	
	private static String HOST;
	private static String PUERTO;
	private static String SUBDOMINIO;
	private static String HOST_SERVICES;
	private static String PUERTO_SERVICES;
	private static String SUBDOMINIO_SERVICES;
	private static Logger log = Logger.getLogger(LdapUserDetailsUtils.class);
	private static String ROLE_VISITANTE = "ROLE_VISITANTE";
	private static  String CATALOGADOR_AVANZADO="avanzado";
	
	static{
		HOST = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		PUERTO = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PUERTO);
		SUBDOMINIO = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		if(SUBDOMINIO==null)SUBDOMINIO="";
		HOST_SERVICES = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		PUERTO_SERVICES = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PUERTO);
		SUBDOMINIO_SERVICES = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		if(SUBDOMINIO_SERVICES==null)SUBDOMINIO_SERVICES="";
	}
	
	public static String getHost()
	{
		return HOST;
	}
	
	public static String getPort()
	{
		return PUERTO;
	}
	
	public static String getSubdominio()
	{
		return SUBDOMINIO;
	}
	
	public static String getHostServices()
	{
		return HOST_SERVICES;
	}
	
	public static String getPortServices()
	{
		return PUERTO_SERVICES;
	}
	
	public static String getSubdominioServices()
	{
		return SUBDOMINIO_SERVICES;
	}
	
	/**
	 * Extrae del userDetails el idioma
	 * 
	 * @return string idioma
	 * */	
	public static String getIdioma(){
		String idioma=null;
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails ud = (UserDetails)auth.getPrincipal();	
			//En el caso de que integracionLdap=true y getIdioma sea null llamamos al método de WrapperSrvAdminUsuarios para obtener datosUsuario
			if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getIdioma() == null))
			{
				UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
				return usuario.getIdioma();
			}
			return ((LdapUserDetailsAgrega)ud).getIdioma();
		}catch (Exception e) {
			
			// Si se produce un error devolvemos el idioma por defecto de la plataforma
			try
			{
				idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}catch (Exception e1) {
				// Si se produce un error devolvemos por defecto es
				idioma = "es";
			}
		}
		return idioma;
		
	}		
	
	/**
	 * Extrae del userDetails el idioma predeterminado de búsqueda
	 * 
	 * @return string idioma de consulta
	 * */	
	public static String getIdiomaBusqueda(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud =(UserDetails)auth.getPrincipal();	
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getIdiomaBusqueda() == null))
		{
			UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
			return usuario.getIdiomaBusqueda();
		}
		return ((LdapUserDetailsAgrega)ud).getIdiomaBusqueda();
	}	
	
	/**
	 * Extra del userDetails el nombreCompleto del usuario
	 * 
	 * @return string nombre completo
	 * */
	public static String getNombreCompleto(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getNombreCompleto() == null))
		{
			UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
			return usuario.getNombre()+" "+usuario.getApellido1()+" "+usuario.getApellido2();
		}
		return ((LdapUserDetailsAgrega)ud).getNombreCompleto();
	}	
		
	/**
	 * Extrae del userDetails el nombre del usuario
	 * 
	 * @return string nombre del usuario
	 * */	
	public static String getUsuario(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud=(UserDetails)auth.getPrincipal();		
		return ud.getUsername();
	}		
	
	/**
	 * Extrae del userDetails el correo electrónico
	 * 
	 * @return string correo electronico
	 **/ 	
	public static String getMail(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();		
		return ((LdapUserDetailsAgrega)ud).getMail();
	}	
	
	/**
	 * Extrae del userDetails el empaquetador
	 * 
	 * @return string empaquetador
	 **/ 	
	public static String getEmpaquetador(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();	
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getEmpaquetador() == null))
		{
			UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
			return usuario.getTipoEmpaquetador();
		}
		return ((LdapUserDetailsAgrega)ud).getEmpaquetador();
	}
	
	/**
	 * Extrae del userDetails el login del usuario
	 * 
	 * @return string login del usuario
	 **/ 	
	public static String getLogin(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();		
		return ((LdapUserDetailsAgrega)ud).getLogin();
	}
	
	/**
	 * Extrae las autorizaciones de un usuario
	 * 
	 * @return GrantedAuthority[] permisos
	 **/ 	
	public static GrantedAuthority[] getRoles(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();
		return ((LdapUserDetailsAgrega)ud).getAuthorities();
	}
	
	/**
	 * Extrae el tipo de catalogador de un usuario
	 * 
	 * @return String catalogador
	 **/ 	
	public static String getCatalogador(){
		try{

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails ud = (UserDetails)auth.getPrincipal();
			if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getCatalogador() == null))
			{
				UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
				return usuario.getTipoCatalogador();
			}
			return ((LdapUserDetailsAgrega)ud).getCatalogador();

		}catch (Exception e) {
			return CATALOGADOR_AVANZADO;
		}
	}

	/**
	 * Extrae del userDetails la cuota de almacenamiento
	 * 
	 * @return Long cuota
	 **/ 	
	public static Long getCuota(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();	
		//En el caso de que se trate de un usuario registrado automaticamente la cuota del contexto puede ser nula
		//con lo que podremos tener problemas en el gestor de flujo, en ese caso accederemos a la BD y obtenemos la cuota
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getCuota() == null))
		{
			UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
			return usuario.getCuota();
		}
		return ((LdapUserDetailsAgrega)ud).getCuota();
	}

	/**
	 * Extrae del userDetails el tipo de visualizador
	 * 
	 * @return string visualizador
	 **/ 	
	public static String getVisualizador(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails)auth.getPrincipal();	
		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true")&&(((LdapUserDetailsAgrega)ud).getVisualizador() == null))
		{
			UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
			return usuario.getTipoVisualizador();
		}
		return ((LdapUserDetailsAgrega)ud).getVisualizador();
	}


	/**
	 * Se informa si esta o no logado
	 * 
	 * @return boolean true si esta autenticado y false de lo contrario
	 **/ 	
	public static boolean estaAutenticado(){	

		boolean resultado = true;
        try {
         	if (SecurityContextHolder.getContext() != null && 
        		SecurityContextHolder.getContext().getAuthentication() != null && 
        		SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null && 
        		SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails)
        	{
        		GrantedAuthority[] autorities = ((SecurityContextHolder.getContext().getAuthentication())).getAuthorities();
        		log.debug("Longitud de los permisos <"+autorities.length+">");
        		for(int i=0; i< autorities.length ; i++)
        		{
        			if((autorities[i].getAuthority()).equalsIgnoreCase(ROLE_VISITANTE))
        			{
        				return false;
        			}
        		}
        		log.debug("esta AUTENTICADO");
        		return true;
        	}
			return false;
        }
        catch (Exception e) {
        	log.error("Error al comprobar si esta autenticado - " + e);
        	return false;
        }
    }
	
	/**
	 * Se informa si el usuario es Administrador o no
	 * 
	 * @return boolean true si es administrador y false de lo contrario
	 **/ 	
	public static boolean esAdministrador(){
        try {

            if (estaAutenticado()){
            	GrantedAuthority[] roles = LdapUserDetailsUtils.getRoles();
        		for (int i=0; i<roles.length; i++){
        			GrantedAuthority rol = roles[i];
        			if (rol.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")){
        	        	log.debug("es ADMINISTRADOR");
        				return true;
        			}
        		}
            }
            return false;
        }
        catch (Exception e) {
        	log.error("Error al comprobar si es administrador - " + e);
        	return false;
        }
	}
	
	
	/**
	 * Se informa si el usuario es Publicador o no
	 * 
	 * @return boolean true si es publicador y false de lo contrario
	 **/ 	
	public static boolean esPublicador(){

		try {
            if (estaAutenticado()){
            	GrantedAuthority[] roles = LdapUserDetailsUtils.getRoles();
        		for (int i=0; i<roles.length; i++){
        			GrantedAuthority rol = roles[i];
        			if (rol.getAuthority().equalsIgnoreCase("ROLE_PUBLICADOR")){
        		        log.debug("es PUBLICADOR");
        				return true;
        			}
        		}
            }
            return false;
        }
        catch (Exception e) {
        	log.error("Error al comprobar si es publicador - " + e);
        	return false;
        }
	}
	/**
	 * Se informa si el usuario es Docente o no
	 * 
	 * @return boolean true si es docente y false de lo contrario
	 **/ 	
	public static boolean esDocente(){
        try {
            if (estaAutenticado()){
            	GrantedAuthority[] roles = LdapUserDetailsUtils.getRoles();
        		for (int i=0; i<roles.length; i++){
        			GrantedAuthority rol = roles[i];
        			if (rol.getAuthority().equalsIgnoreCase("ROLE_DOCENTE")){
        				log.debug("es DOCENTE");
        				return true;
        			}
        		}
            }
            return false;
        }
        catch (Exception e) {
        	log.error("Error al comprobar si es docente - " + e);
        	return false;
        }
	}

	
	/**
	 * Se informa si nos encontramos ante un caso de identidad federada 
	 * @param request 
	 * 
	 * @return boolean true si tiene identidad federada y false de lo contrario
	 **/ 	
	public static boolean tieneIdentidadFederada(HttpServletRequest request){
		boolean resultado = false;
        try {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	log.debug("[argumento HttpServletRequest]-> recojo Objeto Authentication del contexto: <"+auth+">");
        	//Chequeamos que le lleguen los parametros para comprobar si es una operacion de identidad federada
        	if(!(request.getParameter("nodoOrigen") == null)&&(!(request.getParameter("federado") == null)))
        	{
        		if(!(auth == null))
        		{
        		
	        	GrantedAuthority[] roles = auth.getAuthorities();
	    	//	UserDetails ud = (UserDetails)auth.getPrincipal();
	    	//	GrantedAuthority[] roles = ((LdapUserDetailsAgrega)ud).getAuthorities();
	    		if(!(roles == null))
	    		{
	    			for(int i=0;i<roles.length;i++)
	    			{
	    				if(roles[i].getAuthority().equalsIgnoreCase(ROLE_VISITANTE))
	    				{
	    					log.debug("es VISITANTE (federado)");
	    					resultado=true;
	    					
	    				}
	      			}
	    		}else
	    		{
	    			log.debug("No tenemos roles");
	    		}
        	}
        	}else
        	{
        		log.debug("request.getParameter('nodoOrigen) "+request.getParameter("nodoOrigen"));
        	}
    		log.debug("resultado vale <"+resultado+">");
    		return resultado;
        	
        }
        catch (Exception e) {
        	log.error("Error al comprobar si tiene identidad federada - " + e);
        	return false;
        }
    }
	
	/**
	 * Se informa si nos encontramos ante un caso de identidad federada 
	 * 
	 * @return boolean true si tiene identidad federada y false de lo contrario
	 **/ 	
	public static boolean tieneRolVisitante(){
		boolean resultado = false;
        try {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	log.debug("El objeto Authentication del contexto tiene un valor <"+auth+">");
        	//Chequeamos que le lleguen los parametros para comprobar si es una operacion de identidad federada
        	if(!(auth == null))
        	{
        		
	        	GrantedAuthority[] roles = auth.getAuthorities();
	    	//	UserDetails ud = (UserDetails)auth.getPrincipal();
	    	//	GrantedAuthority[] roles = ((LdapUserDetailsAgrega)ud).getAuthorities();
	    		if(!(roles == null))
	    		{
	    			for(int i=0;i<roles.length;i++)
	    			{
	    				if(roles[i].getAuthority().equalsIgnoreCase(ROLE_VISITANTE))
	    				{
	    					log.debug("es VISITANTE");
	    					resultado=true;
	    					
	    				}
	      			}
	    		}else
	    		{
	    			log.debug("No tenemos roles");
	    		}
        	}
        	
    		return resultado;
        	
        }
        catch (Exception e) {
        	log.error("Error al comprobar si tiene identidad federada - " + e);
        	return false;
        }
    }
	
	/**
	 * Se informa si nos encontramos ante un caso de identidad federada 
	 * @param contexto Contexto de seguridad
	 * @param request 
	 * 
	 * @return boolean true si tiene identidad federada y false de lo contrario
	 **/ 	
	public static boolean tieneIdentidadFederada(SecurityContext contexto,HttpServletRequest request){
		boolean resultado = false;
        try {
          	Authentication auth = contexto.getAuthentication();
        	log.debug("[argumentos SecurityContext y HttpServletRequest] El objeto Authentication tiene un valor '"+auth+"'");
        	if(!(request.getParameter("nodoOrigen") == null)&&(!(request.getParameter("federado") == null)))
        	{
        	if(!(auth == null))
        	{
        		
	        	GrantedAuthority[] roles = auth.getAuthorities();
	    	//	UserDetails ud = (UserDetails)auth.getPrincipal();
	    	//	GrantedAuthority[] roles = ((LdapUserDetailsAgrega)ud).getAuthorities();
	        	//TODO Comprobaremos que esta activa la propiedad identidadfederada en el agrega.properties
	    		if((!(roles == null))&&((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IDENTIDAD_FEDERADA)).equalsIgnoreCase("true")))
	    		{
	    			for(int i=0;i<roles.length;i++)
	    			{
	    				if(roles[i].getAuthority().equalsIgnoreCase(ROLE_VISITANTE))
	    				{
	    					log.debug("es VISITANTE (federado)");
	    					resultado=true;
	    					
	    				}
	      			}
	    		}else
	    		{
	    			log.debug("No tenemos roles");
	    		}
        	}
        	}
//    		log.debug("resultado si visitante <"+resultado+">");
    		return resultado;
        	
        }
        catch (Exception e) {
        	log.error("Error al comprobar si tiene identidad federada - " + e);
        	return false;
        }
    }
	
	/**
	 * Se informa si el usuario tiene perfil público o no
	 * 
	 * @return boolean true si es tiene parte pública y false de lo contrario
	 **/ 	
	public static boolean tienePerfilPublico(){
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PERFIL_PUBLICO).equals("true"))
		{
			try {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserDetails ud = (UserDetails)auth.getPrincipal();	
				UsuarioVO usuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(ud.getUsername());
				UsuarioVO usuarioConPerfilPublico = WrapperSrvAdminUsuarios.obtenerUsuario(usuario.getNIF());
				UsuarioPublicoVO usuarioPublico = usuarioConPerfilPublico.getUsuarioPublico();
				if(usuarioPublico!=null && usuarioPublico.getActivo()){
					return true;
				}
				return false;
			}
			catch (Exception e) {
				log.error("Error al obtener el perfil publico: " + e);
				return false;
			}
		}
		return false;
	}

}