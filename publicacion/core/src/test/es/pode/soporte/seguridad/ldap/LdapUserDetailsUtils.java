package es.pode.soporte.seguridad.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.servicios.UsuarioVO;

/**
 * Clase de utilidades para el user details propio
 * */
public class LdapUserDetailsUtils {
	
	private static String HOST; //la url y puerto vendrá en la variable admin.ws.host
	private static String PUERTO;
	private static String SUBDOMINIO;
	private static String HOST_SERVICES; //la url y puerto vendrá en la variable admin.ws.host
	private static String PUERTO_SERVICES;
	private static String SUBDOMINIO_SERVICES;
	private static Logger log = Logger.getLogger(LdapUserDetailsUtils.class);
	private static  String CATALOGADOR_AVANZADO="avanzado";
	
	static{
		Properties prop=new Properties();
		ClassLoader loader = LdapUserDetailsUtils.class.getClassLoader();
		InputStream in = loader.getResourceAsStream("agrega.properties");
		try {
			prop.load(in);
			HOST = prop.getProperty("admin.host");
			PUERTO = prop.getProperty("admin.puerto");
			SUBDOMINIO = prop.getProperty("admin.subdominio");
			if(SUBDOMINIO==null)SUBDOMINIO="";
			HOST_SERVICES = prop.getProperty("admin.ws.host");
			PUERTO_SERVICES = prop.getProperty("admin.ws.puerto");
			SUBDOMINIO_SERVICES = prop.getProperty("admin.ws.subdominio");
		} catch (IOException e) {
			log.error(e);
			if(HOST==null)SUBDOMINIO="localhost";
			if(PUERTO==null)SUBDOMINIO="8080";
			if(SUBDOMINIO==null)SUBDOMINIO="";
			if(HOST_SERVICES==null)SUBDOMINIO="localhost";
			if(PUERTO_SERVICES==null)SUBDOMINIO="8080";
			if(SUBDOMINIO_SERVICES==null)SUBDOMINIO="";
		}		
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
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails ud = (UserDetails)auth.getPrincipal();
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
		return ((LdapUserDetailsAgrega)ud).getCuota();
	}

	/**
	 * Se informa si esta o no logado
	 * 
	 * @return boolean true si esta autenticado y false de lo contrario
	 **/ 	
	public static boolean estaAutenticado(){		 
        try {
        
        	if (SecurityContextHolder.getContext() != null && 
        		SecurityContextHolder.getContext().getAuthentication() != null && 
        		SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null && 
        		SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails)
        		return true;
        	else
        		return false;
        }
        catch (Exception e) {
        	log.error("Error: " + e);
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
        				return true;
        			}
        		}
            }
            return false;
        }
        catch (Exception e) {
        	log.error("Error: " + e);
        	return false;
        }
	}

}