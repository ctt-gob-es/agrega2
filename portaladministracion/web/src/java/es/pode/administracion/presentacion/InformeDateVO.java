/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.administracion.presentacion;

public class InformeDateVO implements java.io.Serializable
{

public InformeDateVO()
{
}

/**
 * Constructor taking all properties.
 */
public InformeDateVO(
    java.lang.String nombre,
    java.util.Date fecha)

{
    this.nombre = nombre;
    this.fecha = fecha;
}

/**
 * Copies constructor from other InformeVO
 */
public InformeDateVO(InformeDateVO otherBean)
{
    if (otherBean != null)
    {
        this.nombre = otherBean.getNombre();
        this.fecha = otherBean.getFecha();
    }
}

    private java.lang.String nombre;

	public java.lang.String getNombre() {
		return nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}


    private java.util.Date fecha;

    /**
     * 
     */
    public java.util.Date getFecha()
    {
        return this.fecha;
    }

    public void setFecha(java.util.Date fecha)
    {
        this.fecha = fecha;
    }
}
