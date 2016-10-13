/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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