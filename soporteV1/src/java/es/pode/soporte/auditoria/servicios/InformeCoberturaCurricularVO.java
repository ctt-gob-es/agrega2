/**
 * InformeCoberturaCurricularVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * Almacena información para el informe de cobertura curricular
 */
public class InformeCoberturaCurricularVO  implements java.io.Serializable {
    private java.lang.String coberturaCurricular;

    private int totalOdes;

    public InformeCoberturaCurricularVO() {
    }

    public InformeCoberturaCurricularVO(
           java.lang.String coberturaCurricular,
           int totalOdes) {
           this.coberturaCurricular = coberturaCurricular;
           this.totalOdes = totalOdes;
    }


    /**
     * Gets the coberturaCurricular value for this InformeCoberturaCurricularVO.
     * 
     * @return coberturaCurricular
     */
    public java.lang.String getCoberturaCurricular() {
        return coberturaCurricular;
    }


    /**
     * Sets the coberturaCurricular value for this InformeCoberturaCurricularVO.
     * 
     * @param coberturaCurricular
     */
    public void setCoberturaCurricular(java.lang.String coberturaCurricular) {
        this.coberturaCurricular = coberturaCurricular;
    }


    /**
     * Gets the totalOdes value for this InformeCoberturaCurricularVO.
     * 
     * @return totalOdes
     */
    public int getTotalOdes() {
        return totalOdes;
    }


    /**
     * Sets the totalOdes value for this InformeCoberturaCurricularVO.
     * 
     * @param totalOdes
     */
    public void setTotalOdes(int totalOdes) {
        this.totalOdes = totalOdes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeCoberturaCurricularVO)) return false;
        InformeCoberturaCurricularVO other = (InformeCoberturaCurricularVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coberturaCurricular==null && other.getCoberturaCurricular()==null) || 
             (this.coberturaCurricular!=null &&
              this.coberturaCurricular.equals(other.getCoberturaCurricular()))) &&
            this.totalOdes == other.getTotalOdes();
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
        if (getCoberturaCurricular() != null) {
            _hashCode += getCoberturaCurricular().hashCode();
        }
        _hashCode += getTotalOdes();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeCoberturaCurricularVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "InformeCoberturaCurricularVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "coberturaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "totalOdes"));
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
