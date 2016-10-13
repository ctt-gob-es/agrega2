/**
 * InformeEstadoOdesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * Almacena información del número de odes que se encuentran en un
 * determinado estado entre dos fechas concretas.
 */
public class InformeEstadoOdesVO  implements java.io.Serializable {
    private java.lang.String estadoFinal;

    private int cantidad;

    private java.lang.String estadoTransicion;

    public InformeEstadoOdesVO() {
    }

    public InformeEstadoOdesVO(
           java.lang.String estadoFinal,
           int cantidad,
           java.lang.String estadoTransicion) {
           this.estadoFinal = estadoFinal;
           this.cantidad = cantidad;
           this.estadoTransicion = estadoTransicion;
    }


    /**
     * Gets the estadoFinal value for this InformeEstadoOdesVO.
     * 
     * @return estadoFinal
     */
    public java.lang.String getEstadoFinal() {
        return estadoFinal;
    }


    /**
     * Sets the estadoFinal value for this InformeEstadoOdesVO.
     * 
     * @param estadoFinal
     */
    public void setEstadoFinal(java.lang.String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }


    /**
     * Gets the cantidad value for this InformeEstadoOdesVO.
     * 
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }


    /**
     * Sets the cantidad value for this InformeEstadoOdesVO.
     * 
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    /**
     * Gets the estadoTransicion value for this InformeEstadoOdesVO.
     * 
     * @return estadoTransicion
     */
    public java.lang.String getEstadoTransicion() {
        return estadoTransicion;
    }


    /**
     * Sets the estadoTransicion value for this InformeEstadoOdesVO.
     * 
     * @param estadoTransicion
     */
    public void setEstadoTransicion(java.lang.String estadoTransicion) {
        this.estadoTransicion = estadoTransicion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeEstadoOdesVO)) return false;
        InformeEstadoOdesVO other = (InformeEstadoOdesVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estadoFinal==null && other.getEstadoFinal()==null) || 
             (this.estadoFinal!=null &&
              this.estadoFinal.equals(other.getEstadoFinal()))) &&
            this.cantidad == other.getCantidad() &&
            ((this.estadoTransicion==null && other.getEstadoTransicion()==null) || 
             (this.estadoTransicion!=null &&
              this.estadoTransicion.equals(other.getEstadoTransicion())));
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
        if (getEstadoFinal() != null) {
            _hashCode += getEstadoFinal().hashCode();
        }
        _hashCode += getCantidad();
        if (getEstadoTransicion() != null) {
            _hashCode += getEstadoTransicion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeEstadoOdesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "InformeEstadoOdesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "estadoFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "cantidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoTransicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "estadoTransicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
