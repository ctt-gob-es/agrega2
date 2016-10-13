/**
 * ODEsEstadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;


/**
 * Esta clase almacena la informacion de cierto numero con respecto
 * a un estado de los posibles dentro del ciclo de vida de la
 *                         publicacion de un ODE.
 */
public class ODEsEstadoVO  implements java.io.Serializable {
    /* Numero de ODEs. */
    private java.lang.Long cantidad;

    /* Estado posible dentro del ciclo de vida de un ODE. */
    private java.lang.String estado;

    public ODEsEstadoVO() {
    }

    public ODEsEstadoVO(
           java.lang.Long cantidad,
           java.lang.String estado) {
           this.cantidad = cantidad;
           this.estado = estado;
    }


    /**
     * Gets the cantidad value for this ODEsEstadoVO.
     * 
     * @return cantidad   * Numero de ODEs.
     */
    public java.lang.Long getCantidad() {
        return cantidad;
    }


    /**
     * Sets the cantidad value for this ODEsEstadoVO.
     * 
     * @param cantidad   * Numero de ODEs.
     */
    public void setCantidad(java.lang.Long cantidad) {
        this.cantidad = cantidad;
    }


    /**
     * Gets the estado value for this ODEsEstadoVO.
     * 
     * @return estado   * Estado posible dentro del ciclo de vida de un ODE.
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this ODEsEstadoVO.
     * 
     * @param estado   * Estado posible dentro del ciclo de vida de un ODE.
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ODEsEstadoVO)) return false;
        ODEsEstadoVO other = (ODEsEstadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cantidad==null && other.getCantidad()==null) || 
             (this.cantidad!=null &&
              this.cantidad.equals(other.getCantidad()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado())));
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
        if (getCantidad() != null) {
            _hashCode += getCantidad().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ODEsEstadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "cantidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
