/**
 * InformeLicenciasOdesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * Almacena información de los odes,  publicados en un rango de
 *                         fechas, que hay por cada una de las licencias
 */
public class InformeLicenciasOdesVO  implements java.io.Serializable {
    private java.lang.String licencia;

    private int numOdes;

    public InformeLicenciasOdesVO() {
    }

    public InformeLicenciasOdesVO(
           java.lang.String licencia,
           int numOdes) {
           this.licencia = licencia;
           this.numOdes = numOdes;
    }


    /**
     * Gets the licencia value for this InformeLicenciasOdesVO.
     * 
     * @return licencia
     */
    public java.lang.String getLicencia() {
        return licencia;
    }


    /**
     * Sets the licencia value for this InformeLicenciasOdesVO.
     * 
     * @param licencia
     */
    public void setLicencia(java.lang.String licencia) {
        this.licencia = licencia;
    }


    /**
     * Gets the numOdes value for this InformeLicenciasOdesVO.
     * 
     * @return numOdes
     */
    public int getNumOdes() {
        return numOdes;
    }


    /**
     * Sets the numOdes value for this InformeLicenciasOdesVO.
     * 
     * @param numOdes
     */
    public void setNumOdes(int numOdes) {
        this.numOdes = numOdes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeLicenciasOdesVO)) return false;
        InformeLicenciasOdesVO other = (InformeLicenciasOdesVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.licencia==null && other.getLicencia()==null) || 
             (this.licencia!=null &&
              this.licencia.equals(other.getLicencia()))) &&
            this.numOdes == other.getNumOdes();
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
        if (getLicencia() != null) {
            _hashCode += getLicencia().hashCode();
        }
        _hashCode += getNumOdes();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeLicenciasOdesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "InformeLicenciasOdesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "licencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "numOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
