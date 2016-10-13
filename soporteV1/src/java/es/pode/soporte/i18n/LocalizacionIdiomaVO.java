package es.pode.soporte.i18n;

/**
 * 
 */
public class LocalizacionIdiomaVO
    implements java.io.Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -660749601558127657L;

	public LocalizacionIdiomaVO()
    {
    }

    public LocalizacionIdiomaVO(
           java.lang.String nombre,
           java.lang.String idLocalizacion) {
           this.nombre = nombre;
           this.idLocalizacion = idLocalizacion;
    }
    
    private java.lang.String nombre;  /*etiqueta traducida del idioma*/
    private java.lang.String idLocalizacion; /*etiqueta del idioma internacional*/

	public java.lang.String getIdLocalizacion() {
		return idLocalizacion;
	}
	public void setIdLocalizacion(java.lang.String idLocalizacion) {
		this.idLocalizacion = idLocalizacion;
	}
	public java.lang.String getNombre() {
		return nombre;
	}
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

}