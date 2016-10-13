/**
 * ValidaBajaInformeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.informes.servicio;

public class ValidaBajaInformeVO  implements java.io.Serializable {
    private java.lang.String descripcionBaja;

    private java.lang.String[] informesBorrados;

    public ValidaBajaInformeVO() {
    }

    public ValidaBajaInformeVO(
           java.lang.String descripcionBaja,
           java.lang.String[] informesBorrados) {
           this.descripcionBaja = descripcionBaja;
           this.informesBorrados = informesBorrados;
    }


    /**
     * Gets the descripcionBaja value for this ValidaBajaInformeVO.
     * 
     * @return descripcionBaja
     */
    public java.lang.String getDescripcionBaja() {
        return descripcionBaja;
    }


    /**
     * Sets the descripcionBaja value for this ValidaBajaInformeVO.
     * 
     * @param descripcionBaja
     */
    public void setDescripcionBaja(java.lang.String descripcionBaja) {
        this.descripcionBaja = descripcionBaja;
    }


    /**
     * Gets the informesBorrados value for this ValidaBajaInformeVO.
     * 
     * @return informesBorrados
     */
    public java.lang.String[] getInformesBorrados() {
        return informesBorrados;
    }


    /**
     * Sets the informesBorrados value for this ValidaBajaInformeVO.
     * 
     * @param informesBorrados
     */
    public void setInformesBorrados(java.lang.String[] informesBorrados) {
        this.informesBorrados = informesBorrados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaBajaInformeVO)) return false;
        ValidaBajaInformeVO other = (ValidaBajaInformeVO) obj;
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
            ((this.informesBorrados==null && other.getInformesBorrados()==null) || 
             (this.informesBorrados!=null &&
              java.util.Arrays.equals(this.informesBorrados, other.getInformesBorrados())));
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
        if (getInformesBorrados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInformesBorrados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInformesBorrados(), i);
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
        new org.apache.axis.description.TypeDesc(ValidaBajaInformeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "ValidaBajaInformeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "descripcionBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informesBorrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "InformesBorrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "item"));
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
