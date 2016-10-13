/**
 * ResultadosCountVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;


/**
 * VO que alberga el resultado de la solicitud de la suma de
 *                         documentos para un nodo de arbol curricular.
 */
public class ResultadosCountVO  implements java.io.Serializable {
    private java.lang.Integer documentosCount;

    private java.lang.Integer[] conteo;

    public ResultadosCountVO() {
    }

    public ResultadosCountVO(
           java.lang.Integer documentosCount,
           java.lang.Integer[] conteo) {
           this.documentosCount = documentosCount;
           this.conteo = conteo;
    }


    /**
     * Gets the documentosCount value for this ResultadosCountVO.
     * 
     * @return documentosCount
     */
    public java.lang.Integer getDocumentosCount() {
        return documentosCount;
    }


    /**
     * Sets the documentosCount value for this ResultadosCountVO.
     * 
     * @param documentosCount
     */
    public void setDocumentosCount(java.lang.Integer documentosCount) {
        this.documentosCount = documentosCount;
    }


    /**
     * Gets the conteo value for this ResultadosCountVO.
     * 
     * @return conteo
     */
    public java.lang.Integer[] getConteo() {
        return conteo;
    }


    /**
     * Sets the conteo value for this ResultadosCountVO.
     * 
     * @param conteo
     */
    public void setConteo(java.lang.Integer[] conteo) {
        this.conteo = conteo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosCountVO)) return false;
        ResultadosCountVO other = (ResultadosCountVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentosCount==null && other.getDocumentosCount()==null) || 
             (this.documentosCount!=null &&
              this.documentosCount.equals(other.getDocumentosCount()))) &&
            ((this.conteo==null && other.getConteo()==null) || 
             (this.conteo!=null &&
              java.util.Arrays.equals(this.conteo, other.getConteo())));
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
        if (getDocumentosCount() != null) {
            _hashCode += getDocumentosCount().hashCode();
        }
        if (getConteo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConteo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConteo(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadosCountVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadosCountVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentosCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "documentosCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conteo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "conteo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
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
