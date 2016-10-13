/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.catalogacion.soporte;

import java.io.Serializable;

import es.pode.catalogacion.negocio.servicios.SourceValueVO;

public class Contribucion implements Serializable {

	private SourceValueVO rol;
	private Entidad[] entidades;
	private Fecha fecha;
	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Entidad[] getEntidades() {
		return entidades;
	}
	public void setEntidades(Entidad[] entidades) {
		this.entidades = entidades;
	}
	public SourceValueVO getRol() {
		return rol;
	}
	public void setRol(SourceValueVO rol) {
		this.rol = rol;
	}
}
