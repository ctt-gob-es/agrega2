package es.pode.modificador.negocio.cambios;

public class ODE {
	private boolean publicado = false;
	private String pathOriginal = null;
	private String pathTaller = null;
	private String backup = null;
	private String traza = null;
	private boolean comprimido = false;
	private String titulo = null;
	
	/**
	 * @return the backup
	 */
	public String getBackup() {
		return backup;
	}
	/**
	 * @param backup the backup to set
	 */
	public void setBackup(String backup) {
		this.backup = backup;
	}
	/**
	 * @return the comprimido
	 */
	public boolean isComprimido() {
		return comprimido;
	}
	/**
	 * @param comprimido the comprimido to set
	 */
	public void setComprimido(boolean comprimido) {
		this.comprimido = comprimido;
	}
	/**
	 * @return the pathOriginal
	 */
	public String getPathOriginal() {
		return pathOriginal;
	}
	/**
	 * @param pathOriginal the pathOriginal to set
	 */
	public void setPathOriginal(String pathOriginal) {
		this.pathOriginal = pathOriginal;
	}
	/**
	 * @return the pathTaller
	 */
	public String getPathTaller() {
		return pathTaller;
	}
	/**
	 * @param pathTaller the pathTaller to set
	 */
	public void setPathTaller(String pathTaller) {
		this.pathTaller = pathTaller;
	}
	/**
	 * @return the publicado
	 */
	public boolean isPublicado() {
		return publicado;
	}
	/**
	 * @param publicado the publicado to set
	 */
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(this.getClass().toString());
		sb.append(" : pathOriginal=").append(pathOriginal).append(
				" : pathTaller=").append(pathTaller).append(" : backup=")
				.append(backup).append(" : comprimido=").append(comprimido)
				.append(" : publicado=").append(publicado);
		return sb.toString();
	}
	/**
	 * @return the traza
	 */
	public String getTraza()
	{
		return traza;
	}
	/**
	 * @param traza the traza to set
	 */
	public void setTraza(String traza)
	{
		this.traza = traza;
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
}
