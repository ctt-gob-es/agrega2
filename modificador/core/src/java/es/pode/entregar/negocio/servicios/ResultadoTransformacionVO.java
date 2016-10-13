/**
 * ResultadoTransformacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class ResultadoTransformacionVO  implements java.io.Serializable {
    private es.pode.entregar.negocio.servicios.CodigoResultadoTransformacion codigo;

    private java.lang.String pathOrigen;

    private java.lang.String mensaje;

    private es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion;

    public ResultadoTransformacionVO() {
    }

    public ResultadoTransformacionVO(
           es.pode.entregar.negocio.servicios.CodigoResultadoTransformacion codigo,
           java.lang.String pathOrigen,
           java.lang.String mensaje,
           es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion) {
           this.codigo = codigo;
           this.pathOrigen = pathOrigen;
           this.mensaje = mensaje;
           this.resultadoValidacion = resultadoValidacion;
    }


    /**
     * Gets the codigo value for this ResultadoTransformacionVO.
     * 
     * @return codigo
     */
    public es.pode.entregar.negocio.servicios.CodigoResultadoTransformacion getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this ResultadoTransformacionVO.
     * 
     * @param codigo
     */
    public void setCodigo(es.pode.entregar.negocio.servicios.CodigoResultadoTransformacion codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the pathOrigen value for this ResultadoTransformacionVO.
     * 
     * @return pathOrigen
     */
    public java.lang.String getPathOrigen() {
        return pathOrigen;
    }


    /**
     * Sets the pathOrigen value for this ResultadoTransformacionVO.
     * 
     * @param pathOrigen
     */
    public void setPathOrigen(java.lang.String pathOrigen) {
        this.pathOrigen = pathOrigen;
    }


    /**
     * Gets the mensaje value for this ResultadoTransformacionVO.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ResultadoTransformacionVO.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the resultadoValidacion value for this ResultadoTransformacionVO.
     * 
     * @return resultadoValidacion
     */
    public es.pode.entregar.negocio.servicios.CBValidoVO getResultadoValidacion() {
        return resultadoValidacion;
    }


    /**
     * Sets the resultadoValidacion value for this ResultadoTransformacionVO.
     * 
     * @param resultadoValidacion
     */
    public void setResultadoValidacion(es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoTransformacionVO)) return false;
        ResultadoTransformacionVO other = (ResultadoTransformacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.pathOrigen==null && other.getPathOrigen()==null) || 
             (this.pathOrigen!=null &&
              this.pathOrigen.equals(other.getPathOrigen()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            ((this.resultadoValidacion==null && other.getResultadoValidacion()==null) || 
             (this.resultadoValidacion!=null &&
              this.resultadoValidacion.equals(other.getResultadoValidacion())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getPathOrigen() != null) {
            _hashCode += getPathOrigen().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        if (getResultadoValidacion() != null) {
            _hashCode += getResultadoValidacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoTransformacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ResultadoTransformacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "CodigoResultadoTransformacion"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "pathOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "resultadoValidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "CBValidoVO"));
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
