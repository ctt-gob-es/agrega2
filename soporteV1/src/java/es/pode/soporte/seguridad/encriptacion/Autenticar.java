package es.pode.soporte.seguridad.encriptacion;

import java.io.IOException;
import java.io.InputStream;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
//import org.acegisecurity.userdetails.UserDetails;
//import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.ldap.LdapTemplate;
import org.springframework.ldap.support.DistinguishedName;
import org.springframework.ldap.support.LdapContextSource;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega;
import es.pode.soporte.seguridad.ldap.WrapperSrvAdminUsuarios;
import es.pode.soporte.seguridad.servicios.UsuarioVO;
//import org.springframework.metadata.Attributes;

//import es.pode.soporte.seguridad.encriptacion.Autenticar;

public class Autenticar 
{
//	protected static final String FILE_NAME_PROPERTIES = "/menu.properties";
//	protected static final String ROLE_ANONYMOUS = MenuController.getPropertyValue("anonimo",MenuController.FILE_NAME_PROPERTIES);
//	protected static final String ROLE_EMPAQUETADOR = MenuController.getPropertyValue("empaquetador",MenuController.FILE_NAME_PROPERTIES);
//	protected static final String ROLE_ADMINISTRADOR = MenuController.getPropertyValue("administrador",MenuController.FILE_NAME_PROPERTIES);
	private static Logger log = Logger.getLogger(Autenticar.class);
	private final static String ROLE_ANONYMOUS = "ANONYMOUS";
	private static java.util.Properties pSpringProperties = null;
	

	
    /**
     * Se añade en el contexto de seguridad el usuario y los datos asociados a él
     *
     * @deprecated Use {@link #cargarContextoSeguridad(String usuario)}
     *
     * @param usuario El usuario que se va a añadir al contexto  
     *
     * @return boolean true: Si se ha añadido la seguridad 
     * 		           false: Si no se ha podido añadir la seguridad 
     */
	public static boolean anadirSeguridad(String usuario)
	{
		String clave = null;
		LdapUserDetailsAgrega.Essence user = null;
		try
		{			
			if (log.isDebugEnabled()) 
				log.debug("Se obtienen los datos del usuario: " + usuario);		
			
			UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(usuario);
			//clave = EncriptacionUtiles.desencriptar(datosUsuario.getClaveEncriptada());
			
			if (log.isDebugEnabled()) 
				log.debug("Se prepara el UserDetails");
			
			user = new LdapUserDetailsAgrega.Essence();							
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);
			user.setPassword(clave);
						
			if (log.isDebugEnabled()) 
				log.debug("Se añade el rol anónimo");
			
			/* Añadimos el rol anónimo */
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
			if (log.isDebugEnabled()) 
				log.debug("Se recuperan los roles del usuario en la BBDD");
	
			/* Obtenemos los roles del usuario */
			GrantedAuthority permisosUsuario[] = null;
			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, clave);
			
			if (log.isDebugEnabled()) 
				log.debug("Se le añade la seguridad al proceso");
			
			/* Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario */
			Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), clave, permisosUsuario);
			SecurityContextHolder.getContext().setAuthentication(currentAuth);
		}
		catch (Exception e)
		{
			log.error("Error al autenticar y añadir la seguridad al proceso", e);
			return false;
		}
		
		return true;
	}
	
    /**
     * Se añade en el contexto de seguridad el usuario y los datos asociados a él sin la clave de usuario
     *
     * @param usuario El usuario que se va a añadir al contexto  
     *
     * @return boolean true: Si se han añadido los datos al contexto de seguridad 
     * 		           false: Si no se ha podido añadir los datos al contexto de seguridad 
     */
	public static boolean cargarContextoSeguridad(String usuario)
	{
		LdapUserDetailsAgrega.Essence user = null;
		try
		{			
			if (log.isDebugEnabled()) 
				log.debug("Se obtienen los datos del usuario: " + usuario);		
			
			UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(usuario);
			
			if (log.isDebugEnabled()) 
				log.debug("Se prepara el UserDetails");
			
			user = new LdapUserDetailsAgrega.Essence();							
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);
						
			if (log.isDebugEnabled()) 
				log.debug("Se añade el rol anónimo");
			
			/* Añadimos el rol anónimo */
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
			if (log.isDebugEnabled()) 
				log.debug("Se recuperan los roles del usuario en la BBDD");
	
			/* Obtenemos los roles del usuario */
			GrantedAuthority permisosUsuario[] = null;
			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, null);
			
			if (log.isDebugEnabled()) 
				log.debug("Se le añade la seguridad al proceso");
			
			/* Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario */
			Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), null, permisosUsuario);
			SecurityContextHolder.getContext().setAuthentication(currentAuth);
		}
		catch (Exception e)
		{
			log.error("Error al autenticar y añadir la seguridad al proceso", e);
			return false;
		}
		
		return true;
	}
	
    /**
     * Se comprueba si el usuario y la clave son correctas y 
     * si es así se añade el usuario en el contexto de seguridad y los datos asociados a él
     *
     * @deprecated Use {@link #cargarContextoSeguridad(String usuario)}
     * 
     * @param usuario El usuario que se va a añadir al contexto  
     * @param claveEncriptada La clave del usuario
     *
     * @return boolean true: Si se ha añadido la seguridad 
     * 		           false: Si no se ha podido añadir la seguridad 
     */
	public static boolean anadirSeguridad(String usuario, String claveEncriptada) {
		String clave = null;
		LdapUserDetailsAgrega.Essence user = null;
		try
		{
			GrantedAuthority permisosUsuario[] = null;
//			permisosUsuario=obtenerRolesUsuario(usuario,clave);
			user = new LdapUserDetailsAgrega.Essence();			
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);			
			user.setPassword(clave);
						
			if (log.isDebugEnabled()) 
				log.debug("Se añade el rol anónimo");
			
			/* Añadimos el rol anónimo */
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
			if (log.isDebugEnabled()) 
				log.debug("Se recuperan los roles del usuario en la BBDD");
	
			/* Obtenemos los roles del usuario */
			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, clave);
			
			if (log.isDebugEnabled()) 
				log.debug("Se le añade la seguridad al proceso");
			
			/* Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario */
			Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), clave, permisosUsuario);
			SecurityContextHolder.getContext().setAuthentication(currentAuth);
		}
		catch (Exception e) {
			log.error("Error al autenticar y añadir la seguridad al proceso", e);
			return false;
		}
		
		return true;
	}
	
    /**
     * Se comprueba si el usuario y la claveEncriptada son correctas 
     *
     * @deprecated No usar. La clave encriptada no debe residir en el base de datos.
     *
     * @param usuario El usuario que se va a añadir al contexto  
     * @param claveEncriptada La clave encriptada del usuario
     *
     * @return boolean true: Si es válido 
     * 		           false: Si no es válido
     */
	public static boolean validarUsuarioClave(String usuario, String claveEncriptada)
	{
		try
		{
			if (log.isDebugEnabled()) 
				log.debug("Se obtienen los datos del usuario " + usuario);		
			
			UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(usuario);
			
			/* Validamos si la clave es correcta */
			/*
			if (!claveEncriptada.equals(datosUsuario.getClaveEncriptada())) {
				log.info("Clave incorrecta. Usuario: " + usuario);
				return false;				
			}
			*/
		}
		catch (Exception e)
		{
			log.error("Error en el proceso de autenticación", e);
			return false;
		}
		
		return true;
	}
	
    /**
     * Se comprueba si el usuario y la clave son correctas y 
     * si es así se añade el usuario en el contexto de seguridad y los datos asociados a él
     *
     * @deprecated No usar. La clave encriptada no debe residir en el base de datos.
     *
     * @param usuario El usuario que se va a añadir al contexto  
     * @param claveEncriptada La clave encriptada del usuario
     *
     * @return boolean true: Si se ha añadido la seguridad 
     * 		           false: Si no se ha podido añadir la seguridad 
     */
	public static boolean anadirSeguridadEncript(String usuario, String claveEncriptada)
	{
		String clave = null;
		LdapUserDetailsAgrega.Essence user = null;
		try
		{
			if (log.isDebugEnabled()) 
				log.debug("Se obtienen los datos del usuario " + usuario);		
						
			/* Validamos si la clave es correcta */
			/*
			if (!validarUsuarioClave(usuario, claveEncriptada)) {
				log.debug("Clave incorrecta. No se añade el usuario: " + usuario + " al contexto");
				return false;				
			}
*/
			clave = EncriptacionUtiles.desencriptar(claveEncriptada);
			user = new LdapUserDetailsAgrega.Essence();			
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);			
			user.setPassword(clave);
						
			if (log.isDebugEnabled()) 
				log.debug("Se añade el rol anónimo");
			
			/* Añadimos el rol anónimo */
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
			if (log.isDebugEnabled()) 
				log.debug("Se recuperan los roles del usuario en la BBDD");
	
			/* Obtenemos los roles del usuario */
			GrantedAuthority permisosUsuario[] = null;
			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, clave);
			
			if (log.isDebugEnabled()) 
				log.debug("Se le añade la seguridad al proceso");
			
			/* Auntenticamos el usuario y lo ponemos en el contexto de seguridad del usuario */
			Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), clave, permisosUsuario);
			SecurityContextHolder.getContext().setAuthentication(currentAuth);
		}
		catch (Exception e)
		{
			log.error("Error al autenticar y añadir la seguridad al proceso", e);
			return false;
		}
		
		return true;
	}

    /**
     * Se comprueba si el usuario y la claveEncriptada son correctas 
     *
     * @param usuario El usuario que se va a añadir al contexto  
     *
     * @return boolean true: Si es existe 
     * 		           false: Si no es válido
     */
	public static boolean validarUsuario(String usuario) {
		
		try {
			if (log.isDebugEnabled()) 
				log.debug("Se obtienen los datos del usuario " + usuario);		
			
			UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(usuario);
			
			if (datosUsuario == null || datosUsuario.getUsuario() == null) {
				log.info("El usuario: " + usuario + " no existe en la BBDD");
				return false;
			}
		}
		catch (Exception e)
		{
			log.error("Error en el proceso de autenticación", e);
			return false;
		}
		
		return true;
	}
	
	 /**
     * Se comprueba si el usuario la clave y el conjunto de roles son correctas 
     *
     * @param usuario El usuario, clave y listado de roles del que que se va a comprobar si son correctos  
     *
     * @return	<table> 
     * 			<tr><td>0</td> <td>Si es correcto </td></tr>
     * 		   	<tr><td>1</td> <td>Si no está autenticado en la plataforma</td></tr>
     * 		   	<tr><td>2</td> <td>Si no tiene los permisos indicados</td></tr>
     * 			</table>
     */
	
	public static Integer validarUsuarioRoles(String usuario, String clave, String[] roles){
		Integer validar=0;
		try{
			boolean validacion=validarUsuarioClaveLdap(usuario, clave);
			log.debug("La validacion nos devuelve "+validacion);
			if(validacion){
				GrantedAuthority permisosUsuario[] = null;
				permisosUsuario=obtenerRolesUsuario(usuario, clave);
				log.debug("Los permisos del usuario son de longitud "+ permisosUsuario.length);
				log.debug("Los roles necesarios son de longitud "+ roles.length);
				for(int i=0;i<roles.length;i++){					
					boolean encontrado=false;
					for(int j=0;j<permisosUsuario.length && !encontrado;j++){
						log.debug("Los roles que queremos son "+ roles[i].toUpperCase()+" cuando i es " +i);
						GrantedAuthority permisoImpl[] = new GrantedAuthority[1];
						permisoImpl[0] = new GrantedAuthorityImpl(permisosUsuario[j].getAuthority());
						String permiso=permisoImpl[0].getAuthority();
						log.debug("Los permisos que tenemos son "+ permiso+ "cuando j es "+j);
						if( permiso.equals(roles[i].toUpperCase())){
							encontrado=true;
						}
	
					}
					if(!encontrado){
						Integer noPermiso=2;
						log.debug("Devolvemos el error de no tiene permiso especificado");
						return noPermiso;
					}
				}
			}else{
				log.info("El usuario no está autenticado en la plataforma");
				Integer noLogado=1;
				return noLogado;
			}
			
		}catch(Exception e){
			log.error("El usuario no está dado de alta en la plataforma o no tiene los roles necesarios para ejecutar esa acción " +e);
		}
		log.debug("Devolvemos el valor "+validar+" en validarUsuariosRoles");
		return validar;
	}
	
	 /**
     * Se comprueba si el usuario y la clave son correctas 
     *
     * @param usuario El usuario y la clave que se desea validar  
     *
     * @return boolean true: Si es correcto 
     * 		           false: Si no es correcto
     */
	
	public static boolean validarUsuarioClaveLdap(String usuario, String password) throws Exception
	{
		log.debug("Validar usuario-clave ldap");
		log.debug("LdapServiceDao::checkPassword()");
		String dnValue = "";
		String urlValue = "";
		String userSearchValue = "";
		int indexDn = 0;
		String[] userValues = null;
		
//		 Construction du DN
        //Obtenemos el valor de dn
        try {
	        dnValue = getPropertyValue("ibuilder.security.ldap.url");	
	        urlValue = getPropertyValue("ibuilder.security.ldap.url");	
	        userSearchValue = getPropertyValue("ibuilder.security.ldap.userSearchBase");
		        
	        indexDn = dnValue.lastIndexOf("/");	
	        dnValue = dnValue.substring(indexDn+1, dnValue.length());	
	        log.debug("dnValue "+dnValue);	
	        DistinguishedName dn = new DistinguishedName(dnValue);	
	        //Chequeamos si userSearchValue es nulo	
	        log.debug("dn "+dn);	
	        if(!(userSearchValue == null) && !(userSearchValue == "") )	
	        {
	              log.debug("obtenemos el valor de userSearchValue");
	              userValues = userSearchValue.split("=");	
	              log.debug("userValues "+userValues.length);	
	              if(userValues.length >= 2)	
	              {	
	                    log.debug("userValues[0] "+userValues[0]);	
	                    log.debug("userValues[1] "+userValues[1]);	
	                    dn.add(userValues[0], userValues[1]);	
	              }	
	        }	        
	
	        dn.add("cn", usuario);	
	        LdapContextSource ctxSource = new LdapContextSource();	
	        log.debug("ctxSource "+ctxSource);	
	        ctxSource.setUrl(urlValue);	
	        ctxSource.setUserName(dn.encode());	
	        ctxSource.setPassword(password);	
	        ctxSource.setPooled(false);

            ctxSource.afterPropertiesSet();
            ctxSource.getReadWriteContext();
            log.debug("Resultado true");
            return true;
        }
        catch(Exception e) {
              log.error("e "+e);
              log.debug("Resultado false");
              return false;

        }


	}

	
	
	private static GrantedAuthority[] obtenerRolesUsuario(String usuario, String claveEncriptada){
		
	
		String clave = null;
		LdapUserDetailsAgrega.Essence user = null;
		GrantedAuthority permisosUsuario[] = null;
		try
		{
			user = new LdapUserDetailsAgrega.Essence();			
			user.setUsername(usuario);
			user.setDatosUsuario(usuario);			
			user.setPassword(clave);
						
			if (log.isDebugEnabled()) 
				log.debug("Se añade el rol anónimo");
			
			/* Añadimos el rol anónimo */
			GrantedAuthority permisosAnadir[] = new GrantedAuthority[1];
			permisosAnadir[0] = new GrantedAuthorityImpl(ROLE_ANONYMOUS);
			if (log.isDebugEnabled()) 
				log.debug("Se recuperan los roles del usuario en la BBDD");
	
			/* Obtenemos los roles del usuario */
			
			permisosUsuario = WrapperSrvAdminUsuarios.obtenerAnadirRoles(permisosAnadir, usuario, clave);
			
		}catch(Exception e){
			log.error("Error al obtener los roles del usaurio " +e);
		}
		return permisosUsuario;
	}
	
	

	private static LdapTemplate getLdapHandler() throws Exception
	{
		LdapTemplate ldapTemplate = null;
		try
		{
			Resource resource = new ClassPathResource("springldap.xml");
			if(log.isDebugEnabled())log.debug("Despues de obtener el resource " + resource.getFilename());
			BeanFactory factory = new XmlBeanFactory(resource);
			if(log.isDebugEnabled())log.debug("Despues de obtener el factory " + factory);
			ldapTemplate = (LdapTemplate) factory.getBean("ldapTemplate");
			if(log.isDebugEnabled())log.debug("Despues de obtener el bean ldapTemplate " + ldapTemplate);
			return ldapTemplate;
		} catch (Exception e)
		{
			log.error("Se produce la siguiente excepcion " + e);
			throw e;
		}
	}
	
	
	
	private static String getPropertyValue(String key) throws IOException {
				
		InputStreamSource resource = new ClassPathResource("authbackend.properties");
		InputStream fIsSpringProperties = resource.getInputStream();
		if (pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		return pSpringProperties.getProperty(key);
		
	}
	
	
}
