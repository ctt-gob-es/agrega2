
package es.pode.soporte.elastic;

import java.util.ArrayList;
import java.util.List;

public class Source {
    
    private String idODE;
    private String estado;
    private String nodo;
    private String fechaRegistro;
    private String fechaPublicacion;
    private String fechaDespublicacion;
    private String titulo;
    private String ruta;
    
	public String getIdODE() {
		return idODE;
	}
	public void setIdODE(String idODE) {
		this.idODE = idODE;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNodo() {
		return nodo;
	}
	public void setNodo(String nodo) {
		this.nodo = nodo;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getFechaDespublicacion() {
		return fechaDespublicacion;
	}
	public void setFechaDespublicacion(String fechaDespublicacion) {
		this.fechaDespublicacion = fechaDespublicacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public String toJson() {
		StringBuffer contenido = new StringBuffer("{");
		contenido.append("\"idODE\" : \"").append(idODE).append("\",");
		contenido.append("\"estado\" : \"").append(estado).append("\",");
		contenido.append("\"nodo\" : \"").append(nodo).append("\",");
		contenido.append("\"fechaRegistro\" : \"").append(fechaRegistro).append("\",");
		contenido.append("\"fechaPublicacion\" : \"").append(fechaPublicacion).append("\",");
		contenido.append("\"fechaDespublicacion\" : \"").append(fechaDespublicacion).append("\",");
		contenido.append("\"ruta\" : \"").append(ruta).append("\"}");
		
		return contenido.toString();
	}
}
