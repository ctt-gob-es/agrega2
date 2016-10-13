/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.adminusuarios.negocio.comun;


public class Person {
	
	private String usuario;
	private String nombreApellidos;
	private String hasedPasswd;

	public void setUsuario(String string) {
		this.usuario=string;
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public void setNombreApellidos(String string) {
		this.nombreApellidos=string;
	}

	public String getNombreApellidos() {
		return this.nombreApellidos;
	}

	public void setHasedPasswd(String string) {
		this.hasedPasswd=string;
	}

	public String getHasedPasswd() {
		return this.hasedPasswd;
	}
}
