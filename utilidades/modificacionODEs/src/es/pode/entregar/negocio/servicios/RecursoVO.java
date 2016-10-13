/**
 * RecursoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class RecursoVO  implements java.io.Serializable {
    private java.lang.String idRec;

    private java.lang.String hrefRec;

    public RecursoVO() {
    }

    public RecursoVO(
           java.lang.String idRec,
           java.lang.String hrefRec) {
           this.idRec = idRec;
           this.hrefRec = hrefRec;
    }


    /**
     * Gets the idRec value for this RecursoVO.
     * 
     * @return idRec
     */
    public java.lang.String getIdRec() {
        return idRec;
    }


    /**
     * Sets the idRec value for this RecursoVO.
     * 
     * @param idRec
     */
    public void setIdRec(java.lang.String idRec) {
        this.idRec = idRec;
    }


    /**
     * Gets the hrefRec value for this RecursoVO.
     * 
     * @return hrefRec
     */
    public java.lang.String getHrefRec() {
        return hrefRec;
    }


    /**
     * Sets the hrefRec value for this RecursoVO.
     * 
     * @param hrefRec
     */
    public void setHrefRec(java.lang.String hrefRec) {
        this.hrefRec = hrefRec;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecursoVO)) return false;
        RecursoVO other = (RecursoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idRec==null && other.getIdRec()==null) || 
             (this.idRec!=null &&
              this.idRec.equals(other.getIdRec()))) &&
            ((this.hrefRec==null && other.getHrefRec()==null) || 
             (this.hrefRec!=null &&
              this.hrefRec.equals(other.getHrefRec())));
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
        if (getIdRec() != null) {
            _hashCode += getIdRec().hashCode();
        }
        if (getHrefRec() != null) {
            _hashCode += getHrefRec().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecursoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "RecursoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idRec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hrefRec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "hrefRec"));
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
