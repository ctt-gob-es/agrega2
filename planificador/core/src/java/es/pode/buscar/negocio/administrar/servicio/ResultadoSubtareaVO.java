/**
 * ResultadoSubtareaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class ResultadoSubtareaVO  implements java.io.Serializable {
    /* Nombre o descripcion de la subtarea */
    private java.lang.String subtarea;

    private java.lang.String resultadoSubtarea;

    public ResultadoSubtareaVO() {
    }

    public ResultadoSubtareaVO(
           java.lang.String subtarea,
           java.lang.String resultadoSubtarea) {
           this.subtarea = subtarea;
           this.resultadoSubtarea = resultadoSubtarea;
    }


    /**
     * Gets the subtarea value for this ResultadoSubtareaVO.
     * 
     * @return subtarea   * Nombre o descripcion de la subtarea
     */
    public java.lang.String getSubtarea() {
        return subtarea;
    }


    /**
     * Sets the subtarea value for this ResultadoSubtareaVO.
     * 
     * @param subtarea   * Nombre o descripcion de la subtarea
     */
    public void setSubtarea(java.lang.String subtarea) {
        this.subtarea = subtarea;
    }


    /**
     * Gets the resultadoSubtarea value for this ResultadoSubtareaVO.
     * 
     * @return resultadoSubtarea
     */
    public java.lang.String getResultadoSubtarea() {
        return resultadoSubtarea;
    }


    /**
     * Sets the resultadoSubtarea value for this ResultadoSubtareaVO.
     * 
     * @param resultadoSubtarea
     */
    public void setResultadoSubtarea(java.lang.String resultadoSubtarea) {
        this.resultadoSubtarea = resultadoSubtarea;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoSubtareaVO)) return false;
        ResultadoSubtareaVO other = (ResultadoSubtareaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subtarea==null && other.getSubtarea()==null) || 
             (this.subtarea!=null &&
              this.subtarea.equals(other.getSubtarea()))) &&
            ((this.resultadoSubtarea==null && other.getResultadoSubtarea()==null) || 
             (this.resultadoSubtarea!=null &&
              this.resultadoSubtarea.equals(other.getResultadoSubtarea())));
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
        if (getSubtarea() != null) {
            _hashCode += getSubtarea().hashCode();
        }
        if (getResultadoSubtarea() != null) {
            _hashCode += getResultadoSubtarea().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoSubtareaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ResultadoSubtareaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "subtarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoSubtarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "resultadoSubtarea"));
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
