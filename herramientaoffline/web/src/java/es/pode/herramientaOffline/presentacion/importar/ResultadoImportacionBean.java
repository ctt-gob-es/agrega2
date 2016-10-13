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
