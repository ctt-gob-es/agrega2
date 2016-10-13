/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.herramientaOffline.presentacion.importar;

public class ResultadoImportacionBean {
	private String idOde=null;
	private Boolean valido = null;
	private String titulo= null;
	private String[] mensajes = null;
	/**
	 * @return the idOde
	 */
	public String getIdOde() {
		return idOde;
	}
	/**
	 * @param idOde the idOde to set
	 */
	public void setIdOde(String idOde) {
		this.idOde = idOde;
	}
	/**
	 * @return the mensajes
	 */
	public String[] getMensajes() {
		return mensajes;
	}
	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(String[] mensajes) {
		this.mensajes = mensajes;
	}
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
	 * @return the valido
	 */
	public Boolean getValido() {
		return valido;
	}
	/**
	 * @param valido the valido to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}
	
}
