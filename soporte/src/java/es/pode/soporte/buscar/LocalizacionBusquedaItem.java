package es.pode.soporte.buscar;

public class LocalizacionBusquedaItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173139597787100993L;

	private String tipoBusqueda; /*Indica el valor de la seleccion de la localizacion de la busqueda*/

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}


	
}
