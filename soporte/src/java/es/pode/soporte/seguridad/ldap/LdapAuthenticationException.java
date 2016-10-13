package es.pode.soporte.seguridad.ldap;



import org.acegisecurity.AuthenticationException;


public class LdapAuthenticationException extends AuthenticationException 
{
	  public LdapAuthenticationException() {
		  super("Ldap Authentication Exception");
	}
}