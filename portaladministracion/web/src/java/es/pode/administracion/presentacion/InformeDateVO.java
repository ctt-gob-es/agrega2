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
