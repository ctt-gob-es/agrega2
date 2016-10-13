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
