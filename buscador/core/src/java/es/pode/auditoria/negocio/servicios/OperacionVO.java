/**
 * OperacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Información de las operaciones interesantes que se pueden
 *                         realizar en la plataforma. Contendrá el idOde,
 * títuloOde sobre
 *                         el que se ha realizado la operación, el usuario,
 * la operación
 *                         (visualización de la ficha, previsualización,
 * visualización,
 *                         descarga, búsqueda) y la fecha.
 */
public class OperacionVO  implements java.io.Serializable {
    private java.lang.String tituloOde;

    private java.lang.String idOde;

    private java.lang.String usuario;

    private java.util.Calendar fecha;

    private java.lang.String operacion;

    private java.lang.Long id;

    /* Idioma de búsqueda */
    private java.lang.String idioma;

    /* Indica si ha sido despublicado el id_ode. O significará que
     * no
     *                                 lo esta y 1 que si está despublicado */
    private java.lang.Integer despublicado;

    public OperacionVO() {
    }

    public OperacionVO(
           java.lang.String tituloOde,
           java.lang.String idOde,
           java.lang.String usuario,
           java.util.Calendar fecha,
           java.lang.String operacion,
           java.lang.Long id,
           java.lang.String idioma,
           java.lang.Integer despublicado) {
           this.tituloOde = tituloOde;
           this.idOde = idOde;
           this.usuario = usuario;
           this.fecha = fecha;
           this.operacion = operacion;
           this.id = id;
           this.idioma = idioma;
           this.despublicado = despublicado;
    }


    /**
     * Gets the tituloOde value for this OperacionVO.
     * 
     * @return tituloOde
     */
    public java.lang.String getTituloOde() {
        return tituloOde;
    }


    /**
     * Sets the tituloOde value for this OperacionVO.
     * 
     * @param tituloOde
     */
    public void setTituloOde(java.lang.String tituloOde) {
        this.tituloOde = tituloOde;
    }


    /**
     * Gets the idOde value for this OperacionVO.
     * 
     * @return idOde
     */
    public java.lang.String getIdOde() {
        return idOde;
    }


    /**
     * Sets the idOde value for this OperacionVO.
     * 
     * @param idOde
     */
    public void setIdOde(java.lang.String idOde) {
        this.idOde = idOde;
    }


    /**
     * Gets the usuario value for this OperacionVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this OperacionVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the fecha value for this OperacionVO.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this OperacionVO.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the operacion value for this OperacionVO.
     * 
     * @return operacion
     */
    public java.lang.String getOperacion() {
        return operacion;
    }


    /**
     * Sets the operacion value for this OperacionVO.
     * 
     * @param operacion
     */
    public void setOperacion(java.lang.String operacion) {
        this.operacion = operacion;
    }


    /**
     * Gets the id value for this OperacionVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this OperacionVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the idioma value for this OperacionVO.
     * 
     * @return idioma   * Idioma de búsqueda
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this OperacionVO.
     * 
     * @param idioma   * Idioma de búsqueda
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the despublicado value for this OperacionVO.
     * 
     * @return despublicado   * Indica si ha sido despublicado el id_ode. O significará que
     * no
     *                                 lo esta y 1 que si está despublicado
     */
    public java.lang.Integer getDespublicado() {
        return despublicado;
    }


    /**
     * Sets the despublicado value for this OperacionVO.
     * 
     * @param despublicado   * Indica si ha sido despublicado el id_ode. O significará que
     * no
     *                                 lo esta y 1 que si está despublicado
     */
    public void setDespublicado(java.lang.Integer despublicado) {
        this.despublicado = despublicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OperacionVO)) return false;
        OperacionVO other = (OperacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tituloOde==null && other.getTituloOde()==null) || 
             (this.tituloOde!=null &&
              this.tituloOde.equals(other.getTituloOde()))) &&
            ((this.idOde==null && other.getIdOde()==null) || 
             (this.idOde!=null &&
              this.idOde.equals(other.getIdOde()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.operacion==null && other.getOperacion()==null) || 
             (this.operacion!=null &&
              this.operacion.equals(other.getOperacion()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.despublicado==null && other.getDespublicado()==null) || 
             (this.despublicado!=null &&
              this.despublicado.equals(other.getDespublicado())));
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
        if (getTituloOde() != null) {
            _hashCode += getTituloOde().hashCode();
        }
        if (getIdOde() != null) {
            _hashCode += getIdOde().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getOperacion() != null) {
            _hashCode += getOperacion().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getDespublicado() != null) {
            _hashCode += getDespublicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OperacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "OperacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "tituloOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "operacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("despublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "despublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
