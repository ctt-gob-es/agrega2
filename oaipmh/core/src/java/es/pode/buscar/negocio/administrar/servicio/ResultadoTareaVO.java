/**
 * ResultadoTareaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class ResultadoTareaVO  implements java.io.Serializable {
    private es.pode.buscar.negocio.administrar.servicio.ResultadoSubtareaVO[] resultadosSubtareas;

    private java.lang.String resultadoGlobal;

    public ResultadoTareaVO() {
    }

    public ResultadoTareaVO(
           es.pode.buscar.negocio.administrar.servicio.ResultadoSubtareaVO[] resultadosSubtareas,
           java.lang.String resultadoGlobal) {
           this.resultadosSubtareas = resultadosSubtareas;
           this.resultadoGlobal = resultadoGlobal;
    }


    /**
     * Gets the resultadosSubtareas value for this ResultadoTareaVO.
     * 
     * @return resultadosSubtareas
     */
    public es.pode.buscar.negocio.administrar.servicio.ResultadoSubtareaVO[] getResultadosSubtareas() {
        return resultadosSubtareas;
    }


    /**
     * Sets the resultadosSubtareas value for this ResultadoTareaVO.
     * 
     * @param resultadosSubtareas
     */
    public void setResultadosSubtareas(es.pode.buscar.negocio.administrar.servicio.ResultadoSubtareaVO[] resultadosSubtareas) {
        this.resultadosSubtareas = resultadosSubtareas;
    }


    /**
     * Gets the resultadoGlobal value for this ResultadoTareaVO.
     * 
     * @return resultadoGlobal
     */
    public java.lang.String getResultadoGlobal() {
        return resultadoGlobal;
    }


    /**
     * Sets the resultadoGlobal value for this ResultadoTareaVO.
     * 
     * @param resultadoGlobal
     */
    public void setResultadoGlobal(java.lang.String resultadoGlobal) {
        this.resultadoGlobal = resultadoGlobal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoTareaVO)) return false;
        ResultadoTareaVO other = (ResultadoTareaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadosSubtareas==null && other.getResultadosSubtareas()==null) || 
             (this.resultadosSubtareas!=null &&
              java.util.Arrays.equals(this.resultadosSubtareas, other.getResultadosSubtareas()))) &&
            ((this.resultadoGlobal==null && other.getResultadoGlobal()==null) || 
             (this.resultadoGlobal!=null &&
              this.resultadoGlobal.equals(other.getResultadoGlobal())));
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
        if (getResultadosSubtareas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadosSubtareas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadosSubtareas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResultadoGlobal() != null) {
            _hashCode += getResultadoGlobal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoTareaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ResultadoTareaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosSubtareas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "resultadosSubtareas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ResultadoSubtareaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoGlobal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "resultadoGlobal"));
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
