/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.parseadorXML.lomes.lomesAgrega;

public class RequisitoAgrega {

	private String tipo=null;
	private String nombre=null;
	private String versionMinima=null;
	private String versionMaxima=null;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getVersionMaxima() {
		return versionMaxima;
	}
	public void setVersionMaxima(String versionMaxima) {
		this.versionMaxima = versionMaxima;
	}
	public String getVersionMinima() {
		return versionMinima;
	}
	public void setVersionMinima(String versionMinima) {
		this.versionMinima = versionMinima;
	}
	
	
}
