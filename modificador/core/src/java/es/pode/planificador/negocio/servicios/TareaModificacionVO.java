/**
 * TareaModificacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaModificacionVO  implements java.io.Serializable {
    /* Nombre descriptivo de la tarea */
    private java.lang.String trabajo;

    private java.lang.String usuario;

    /* Fecha de inicio del trabajo / trigger */
    private java.util.Calendar fechaInicio;

    /* Identificador de la tarea de modificación proporcionado por
     * el
     *                                 modulo 'Herramienta de modificación' */
    private java.lang.Long idModificacion;

    public TareaModificacionVO() {
    }

    public TareaModificacionVO(
           java.lang.String trabajo,
           java.lang.String usuario,
           java.util.Calendar fechaInicio,
           java.lang.Long idModificacion) {
           this.trabajo = trabajo;
           this.usuario = usuario;
           this.fechaInicio = fechaInicio;
           this.idModificacion = idModificacion;
    }


    /**
     * Gets the trabajo value for this TareaModificacionVO.
     * 
     * @return trabajo   * Nombre descriptivo de la tarea
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaModificacionVO.
     * 
     * @param trabajo   * Nombre descriptivo de la tarea
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the usuario value for this TareaModificacionVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaModificacionVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the fechaInicio value for this TareaModificacionVO.
     * 
     * @return fechaInicio   * Fecha de inicio del trabajo / trigger
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaModificacionVO.
     * 
     * @param fechaInicio   * Fecha de inicio del trabajo / trigger
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the idModificacion value for this TareaModificacionVO.
     * 
     * @return idModificacion   * Identificador de la tarea de modificación proporcionado por
     * el
     *                                 modulo 'Herramienta de modificación'
     */
    public java.lang.Long getIdModificacion() {
        return idModificacion;
    }


    /**
     * Sets the idModificacion value for this TareaModificacionVO.
     * 
     * @param idModificacion   * Identificador de la tarea de modificación proporcionado por
     * el
     *                                 modulo 'Herramienta de modificación'
     */
    public void setIdModificacion(java.lang.Long idModificacion) {
        this.idModificacion = idModificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaModificacionVO)) return false;
        TareaModificacionVO other = (TareaModificacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trabajo==null && other.getTrabajo()==null) || 
             (this.trabajo!=null &&
              this.trabajo.equals(other.getTrabajo()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.idModificacion==null && other.getIdModificacion()==null) || 
             (this.idModificacion!=null &&
              this.idModificacion.equals(other.getIdModificacion())));
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
        if (getTrabajo() != null) {
            _hashCode += getTrabajo().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getIdModificacion() != null) {
            _hashCode += getIdModificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaModificacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trabajo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idModificacion"));
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
