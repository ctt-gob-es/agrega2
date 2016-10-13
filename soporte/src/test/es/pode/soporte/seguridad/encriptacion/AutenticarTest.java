/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.encriptacion;


import junit.framework.TestCase;
//import org.acegisecurity.userdetails.UserDetails;
//import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.support.DistinguishedName;
import org.springframework.ldap.support.LdapContextSource;
//import com.sun.jndi.ldap.ctl.ResponseControlFactory;



public class AutenticarTest  extends TestCase 
{
//	protected static final String FILE_NAME_PROPERTIES = "/menu.properties";
//	protected static final String ROLE_ANONYMOUS = MenuController.getPropertyValue("anonimo",MenuController.FILE_NAME_PROPERTIES);
//	protected static final String ROLE_EMPAQUETADOR = MenuController.getPropertyValue("empaquetador",MenuController.FILE_NAME_PROPERTIES);
//	protected static final String ROLE_ADMINISTRADOR = MenuController.getPropertyValue("administrador",MenuController.FILE_NAME_PROPERTIES);
	private static Log log = LogFactory.getLog(Autenticar.class);
	private final static String ROLE_ANONYMOUS = "ANONYMOUS";
	private static java.util.Properties pSpringProperties = null;
	

	
   
	
	 /**
     * Se comprueba si el usuario y la clave son correctas 
     *
     * @param usuario El usuario y la clave que se desea validar  
     *
     * @return boolean true: Si es correcto 
     * 		           false: Si no es correcto
     */
	
	public static boolean testValidarUsuarioClaveLdap() 
	{
		String usuario = "administrador";
		String password = "1";
		System.out.println("Validar usuario-clave ldap");
		System.out.println("usuario "+usuario);
		System.out.println("password "+password);
		System.out.println("LdapServiceDao::checkPassword()");
		// Construction du DN
		String dnValue = "";
		String urlValue = "";
		int indexDn = 0;
		String userSearchValue = "";
		String[] userValues = null;
		
		userSearchValue = "ou=usuarios";
		dnValue = "ldap://pruebas:389/dc=desarrollo,dc=agrega,dc=indra,dc=es";
		indexDn = dnValue.lastIndexOf("/");
		dnValue = dnValue.substring(indexDn+1, dnValue.length());
		System.out.println("dnValue "+dnValue);
		DistinguishedName dn = new DistinguishedName(dnValue);
		System.out.println("dn "+dn);
		if(!(userSearchValue == null) && !(userSearchValue == "") )
		{
			System.out.println("obtenemos el valor de userSearchValue");
			userValues = userSearchValue.split("=");
			System.out.println("userValues "+userValues.length);
			if(userValues.length >= 2)
			{
				System.out.println("userValues[0] "+userValues[0]);
				System.out.println("userValues[1] "+userValues[1]);
				dn.add(userValues[0], userValues[1]);
			}
		}
		dn.add("cn", usuario);
		System.out.println("cn.append ");
		// Connexion manuelle
		LdapContextSource ctxSource = new LdapContextSource();
		ctxSource.setUrl("ldap://172.22.145.116:389/dc=desarrollo,dc=agrega,dc=indra,dc=es");
		System.out.println("ctxSource.setUrl");
		ctxSource.setUserName(dn.encode());
		System.out.println("ctxSource.setUserName");
		ctxSource.setPassword(password);
		System.out.println("ctxSource.setPassword");
		ctxSource.setPooled(false);
		System.out.println("ctxSource.setPooled");
		
		try {
		    ctxSource.afterPropertiesSet();
		    System.out.println("ctxSource.afterPropertiesSet");
		    ctxSource.getReadWriteContext();
		    System.out.println("ctxSource.getReadWriteContext");
		    System.out.println("Resultado true");
		    return true;
		}
		catch(Exception e) {
			System.out.println("e "+e);
			System.out.println("Resultado false");
			return false;
		    
		}
		
		
	}
		
}
