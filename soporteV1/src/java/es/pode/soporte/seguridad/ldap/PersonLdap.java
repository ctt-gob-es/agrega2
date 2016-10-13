/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.ldap;

/*
 * Clase que almacena informaci�n de los usuarios registrados en el Ldap 
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
