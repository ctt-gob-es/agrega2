/**
 * GrupoPublicoAsociadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class GrupoPublicoAsociadoVO  implements java.io.Serializable {
    private java.lang.String administrador;

    private java.lang.Boolean asociado;

    private java.lang.String descripcion;

    private java.util.Calendar fechaCreacion;

    private java.util.Calendar fechaModificacion;

    private java.lang.String imagenGrupo;

    private java.lang.String nombre;

    private java.lang.Long id;

    public GrupoPublicoAsociadoVO() {
    }

    public GrupoPublicoAsociadoVO(
           java.lang.String administrador,
           java.lang.Boolean asociado,
           java.lang.String descripcion,
           java.util.Calendar fechaCreacion,
           java.util.Calendar fechaModificacion,
           java.lang.String imagenGrupo,
           java.lang.String nombre,
           java.lang.Long id) {
           this.administrador = administrador;
           this.asociado = asociado;
           this.descripcion = descripcion;
           this.fechaCreacion = fechaCreacion;
           this.fechaModificacion = fechaModificacion;
           this.imagenGrupo = imagenGrupo;
           this.nombre = nombre;
           this.id = id;
    }


    /**
     * Gets the administrador value for this GrupoPublicoAsociadoVO.
     * 
     * @return administrador
     */
    public java.lang.String getAdministrador() {
        return administrador;
    }


    /**
     * Sets the administrador value for this GrupoPublicoAsociadoVO.
     * 
     * @param administrador
     */
    public void setAdministrador(java.lang.String administrador) {
        this.administrador = administrador;
    }


    /**
     * Gets the asociado value for this GrupoPublicoAsociadoVO.
     * 
     * @return asociado
     */
    public java.lang.Boolean getAsociado() {
        return asociado;
    }


    /**
     * Sets the asociado value for this GrupoPublicoAsociadoVO.
     * 
     * @param asociado
     */
    public void setAsociado(java.lang.Boolean asociado) {
        this.asociado = asociado;
    }


    /**
     * Gets the descripcion value for this GrupoPublicoAsociadoVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this GrupoPublicoAsociadoVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaCreacion value for this GrupoPublicoAsociadoVO.
     * 
     * @return fechaCreacion
     */
    public java.util.Calendar getFechaCreacion() {
        return fechaCreacion;
    }


    /**
     * Sets the fechaCreacion value for this GrupoPublicoAsociadoVO.
     * 
     * @param fechaCreacion
     */
    public void setFechaCreacion(java.util.Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    /**
     * Gets the fechaModificacion value for this GrupoPublicoAsociadoVO.
     * 
     * @return fechaModificacion
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this GrupoPublicoAsociadoVO.
     * 
     * @param fechaModificacion
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the imagenGrupo value for this GrupoPublicoAsociadoVO.
     * 
     * @return imagenGrupo
     */
    public java.lang.String getImagenGrupo() {
        return imagenGrupo;
    }


    /**
     * Sets the imagenGrupo value for this GrupoPublicoAsociadoVO.
     * 
     * @param imagenGrupo
     */
    public void setImagenGrupo(java.lang.String imagenGrupo) {
        this.imagenGrupo = imagenGrupo;
    }


    /**
     * Gets the nombre value for this GrupoPublicoAsociadoVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this GrupoPublicoAsociadoVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the id value for this GrupoPublicoAsociadoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this GrupoPublicoAsociadoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GrupoPublicoAsociadoVO)) return false;
        GrupoPublicoAsociadoVO other = (GrupoPublicoAsociadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.administrador==null && other.getAdministrador()==null) || 
             (this.administrador!=null &&
              this.administrador.equals(other.getAdministrador()))) &&
            ((this.asociado==null && other.getAsociado()==null) || 
             (this.asociado!=null &&
              this.asociado.equals(other.getAsociado()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaCreacion==null && other.getFechaCreacion()==null) || 
             (this.fechaCreacion!=null &&
              this.fechaCreacion.equals(other.getFechaCreacion()))) &&
            ((this.fechaModificacion==null && other.getFechaModificacion()==null) || 
             (this.fechaModificacion!=null &&
              this.fechaModificacion.equals(other.getFechaModificacion()))) &&
            ((this.imagenGrupo==null && other.getImagenGrupo()==null) || 
             (this.imagenGrupo!=null &&
              this.imagenGrupo.equals(other.getImagenGrupo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdministrador() != null) {
            _hashCode += getAdministrador().hashCode();
        }
        if (getAsociado() != null) {
            _hashCode += getAsociado().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getFechaCreacion() != null) {
            _hashCode += getFechaCreacion().hashCode();
        }
        if (getFechaModificacion() != null) {
            _hashCode += getFechaModificacion().hashCode();
        }
        if (getImagenGrupo() != null) {
            _hashCode += getImagenGrupo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GrupoPublicoAsociadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "GrupoPublicoAsociadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("administrador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "administrador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asociado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "asociado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCreacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaCreacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaModificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagenGrupo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "imagenGrupo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
