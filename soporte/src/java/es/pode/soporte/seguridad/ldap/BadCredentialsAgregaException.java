package es.pode.soporte.seguridad.ldap;



import org.acegisecurity.BadCredentialsException;


public class BadCredentialsAgregaException extends BadCredentialsException 
{
	  public BadCredentialsAgregaException() {
		  super("Bad Credentiasl Agrega Exception");
	}
}