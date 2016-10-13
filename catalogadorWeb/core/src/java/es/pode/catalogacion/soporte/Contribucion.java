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
