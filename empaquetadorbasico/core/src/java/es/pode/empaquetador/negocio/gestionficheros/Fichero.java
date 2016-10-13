/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.empaquetador.negocio.gestionficheros;

import java.io.File;

import javax.activation.DataHandler;

public class Fichero {

	private File fichero;
	private String nombre;
	private Long size;
	private DataHandler datos;
	private String ruta;
	
	public DataHandler getDatos() {
		return datos;
	}
	public void setDatos(DataHandler datos) {
		this.datos = datos;
	}
	public File getFichero() {
		return fichero;
	}
	public void setFichero(File fichero) {
		this.fichero = fichero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
