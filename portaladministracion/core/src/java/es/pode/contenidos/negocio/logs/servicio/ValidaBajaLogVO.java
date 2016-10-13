/**
 * ValidaBajaLogVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.logs.servicio;

public class ValidaBajaLogVO  implements java.io.Serializable {
    private java.lang.String descripcionBaja;

    private java.lang.String[] logsBorrados;

    public ValidaBajaLogVO() {
    }

    public ValidaBajaLogVO(
           java.lang.String descripcionBaja,
           java.lang.String[] logsBorrados) {
           this.descripcionBaja = descripcionBaja;
           this.logsBorrados = logsBorrados;
    }


    /**
     * Gets the descripcionBaja value for this ValidaBajaLogVO.
     * 
     * @return descripcionBaja
     */
    public java.lang.String getDescripcionBaja() {
        return descripcionBaja;
    }


    /**
     * Sets the descripcionBaja value for this ValidaBajaLogVO.
     * 
     * @param descripcionBaja
     */
    public void setDescripcionBaja(java.lang.String descripcionBaja) {
        this.descripcionBaja = descripcionBaja;
    }


    /**
     * Gets the logsBorrados value for this ValidaBajaLogVO.
     * 
     * @return logsBorrados
     */
    public java.lang.String[] getLogsBorrados() {
        return logsBorrados;
    }


    /**
     * Sets the logsBorrados value for this ValidaBajaLogVO.
     * 
     * @param logsBorrados
     */
    public void setLogsBorrados(java.lang.String[] logsBorrados) {
        this.logsBorrados = logsBorrados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaBajaLogVO)) return false;
        ValidaBajaLogVO other = (ValidaBajaLogVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionBaja==null && other.getDescripcionBaja()==null) || 
             (this.descripcionBaja!=null &&
              this.descripcionBaja.equals(other.getDescripcionBaja()))) &&
            ((this.logsBorrados==null && other.getLogsBorrados()==null) || 
             (this.logsBorrados!=null &&
              java.util.Arrays.equals(this.logsBorrados, other.getLogsBorrados())));
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
        if (getDescripcionBaja() != null) {
            _hashCode += getDescripcionBaja().hashCode();
        }
        if (getLogsBorrados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLogsBorrados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLogsBorrados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaBajaLogVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "ValidaBajaLogVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "descripcionBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logsBorrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "logsBorrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "item"));
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
