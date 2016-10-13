package es.pode.soporte.seguridad.ldap;

/*
 * Clase que almacena información de los usuarios registrados en el Ldap 
 */

public class PersonLdap
{
	private String nombre = "";
	private String apellidos = "";
	private String email = "";
	private String usuario = "";
	private String nif = "";
	
	
	public String getApellidos()
	{
		return apellidos;
	}
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getNif()
	{
		return nif;
	}
	public void setNif(String nif)
	{
		this.nif = nif;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getUsuario()
	{
		return usuario;
	}
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}
	
}
