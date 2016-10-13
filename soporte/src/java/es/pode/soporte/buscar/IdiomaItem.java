/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.buscar;

public class IdiomaItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173139597787100993L;

	private String idLocalizacion; /*Indica el valor de la etiqueta del idioma*/
	private String nombre; /*Indica el string traducido del idioma de la etiqueta*/
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdLocalizacion() {
		return idLocalizacion;
	}
	public void setIdLocalizacion(String idLocalizacion) {
		this.idLocalizacion = idLocalizacion;
	}
	
	
}