package es.pode.visualizador.presentacion.descargas;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class DescargaInfo implements Serializable {
	private String titulo;
	private String identificador;
	private String peso;
	private String ruta;
	private String descripcion;
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}
	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	/**
	 * @return the peso
	 */
	public String getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(String peso) {
		this.peso = peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Long peso, Locale locale) {
		String unidad = " bytes";
		if (peso > 1024) {
			peso = peso / 1024;
			unidad = " KB";
		}
		if (peso > 1024) {
			peso = peso / 1024;
			unidad = " MB";
		}
		NumberFormat format = NumberFormat.getInstance(locale);
		format.setMaximumFractionDigits(2);
		this.peso = format.format(peso) + unidad;
	}
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
