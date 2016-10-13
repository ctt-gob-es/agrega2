/**
 * DocumentosVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Esta clase representa los resultados de una busqueda.
 */
public class DocumentosVO  implements java.io.Serializable {
    private es.pode.indexador.negocio.servicios.busqueda.DocVO[] resultados;

    private java.lang.String sugerencias;

    public DocumentosVO() {
    }

    public DocumentosVO(
           es.pode.indexador.negocio.servicios.busqueda.DocVO[] resultados,
           java.lang.String sugerencias) {
           this.resultados = resultados;
           this.sugerencias = sugerencias;
    }


    /**
     * Gets the resultados value for this DocumentosVO.
     * 
     * @return resultados
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocVO[] getResultados() {
        return resultados;
    }


    /**
     * Sets the resultados value for this DocumentosVO.
     * 
     * @param resultados
     */
    public void setResultados(es.pode.indexador.negocio.servicios.busqueda.DocVO[] resultados) {
        this.resultados = resultados;
    }


    /**
     * Gets the sugerencias value for this DocumentosVO.
     * 
     * @return sugerencias
     */
    public java.lang.String getSugerencias() {
        return sugerencias;
    }


    /**
     * Sets the sugerencias value for this DocumentosVO.
     * 
     * @param sugerencias
     */
    public void setSugerencias(java.lang.String sugerencias) {
        this.sugerencias = sugerencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentosVO)) return false;
        DocumentosVO other = (DocumentosVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultados==null && other.getResultados()==null) || 
             (this.resultados!=null &&
              java.util.Arrays.equals(this.resultados, other.getResultados()))) &&
            ((this.sugerencias==null && other.getSugerencias()==null) || 
             (this.sugerencias!=null &&
              this.sugerencias.equals(other.getSugerencias())));
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
        if (getResultados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSugerencias() != null) {
            _hashCode += getSugerencias().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentosVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "DocumentosVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "resultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "DocVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sugerencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "sugerencias"));
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
