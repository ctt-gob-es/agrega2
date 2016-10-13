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