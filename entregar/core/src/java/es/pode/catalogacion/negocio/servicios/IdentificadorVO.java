/**
 * IdentificadorVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class IdentificadorVO  implements java.io.Serializable {
    private java.lang.String entrada;

    private java.lang.String catalogo;

    public IdentificadorVO() {
    }

    public IdentificadorVO(
           java.lang.String entrada,
           java.lang.String catalogo) {
           this.entrada = entrada;
           this.catalogo = catalogo;
    }


    /**
     * Gets the entrada value for this IdentificadorVO.
     * 
     * @return entrada
     */
    public java.lang.String getEntrada() {
        return entrada;
    }


    /**
     * Sets the entrada value for this IdentificadorVO.
     * 
     * @param entrada
     */
    public void setEntrada(java.lang.String entrada) {
        this.entrada = entrada;
    }


    /**
     * Gets the catalogo value for this IdentificadorVO.
     * 
     * @return catalogo
     */
    public java.lang.String getCatalogo() {
        return catalogo;
    }


    /**
     * Sets the catalogo value for this IdentificadorVO.
     * 
     * @param catalogo
     */
    public void setCatalogo(java.lang.String catalogo) {
        this.catalogo = catalogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdentificadorVO)) return false;
        IdentificadorVO other = (IdentificadorVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entrada==null && other.getEntrada()==null) || 
             (this.entrada!=null &&
              this.entrada.equals(other.getEntrada()))) &&
            ((this.catalogo==null && other.getCatalogo()==null) || 
             (this.catalogo!=null &&
              this.catalogo.equals(other.getCatalogo())));
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
        if (getEntrada() != null) {
            _hashCode += getEntrada().hashCode();
        }
        if (getCatalogo() != null) {
            _hashCode += getCatalogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdentificadorVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entrada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "entrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "catalogo"));
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
