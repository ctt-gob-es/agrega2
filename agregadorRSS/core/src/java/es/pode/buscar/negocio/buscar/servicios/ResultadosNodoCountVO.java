/**
 * ResultadosNodoCountVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * Número de documentos para un área curricular.
 */
public class ResultadosNodoCountVO  implements java.io.Serializable {
    private java.lang.Integer numeroResultados;

    private java.lang.Integer[] conteo;

    public ResultadosNodoCountVO() {
    }

    public ResultadosNodoCountVO(
           java.lang.Integer numeroResultados,
           java.lang.Integer[] conteo) {
           this.numeroResultados = numeroResultados;
           this.conteo = conteo;
    }


    /**
     * Gets the numeroResultados value for this ResultadosNodoCountVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ResultadosNodoCountVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the conteo value for this ResultadosNodoCountVO.
     * 
     * @return conteo
     */
    public java.lang.Integer[] getConteo() {
        return conteo;
    }


    /**
     * Sets the conteo value for this ResultadosNodoCountVO.
     * 
     * @param conteo
     */
    public void setConteo(java.lang.Integer[] conteo) {
        this.conteo = conteo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosNodoCountVO)) return false;
        ResultadosNodoCountVO other = (ResultadosNodoCountVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
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
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
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
        new org.apache.axis.description.TypeDesc(ResultadosNodoCountVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosNodoCountVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conteo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "conteo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
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
