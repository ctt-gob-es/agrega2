/**
 * ResultadoHeaderVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Clase de objeto de valor que contiene la informacion de un
 *                         header de un registro dentro del repositorio.
 */
public class ResultadoHeaderVO  implements java.io.Serializable {
    /* Identificador del repositorio OAI-PMH con la sintaxis: scheme
     * ":" namespace-identifier ":" local-identifier. */
    private java.lang.String identificador;

    private java.lang.String fecha;

    /* Array con los identificadores de los conjuntos en los que se
     * encuentra el registro */
    private java.lang.String[] identificadorConjunto;

    /* Identificador de la siguiente página del listado de
     *                                 identificadores. */
    private java.lang.String codigoPaginacion;

    public ResultadoHeaderVO() {
    }

    public ResultadoHeaderVO(
           java.lang.String identificador,
           java.lang.String fecha,
           java.lang.String[] identificadorConjunto,
           java.lang.String codigoPaginacion) {
           this.identificador = identificador;
           this.fecha = fecha;
           this.identificadorConjunto = identificadorConjunto;
           this.codigoPaginacion = codigoPaginacion;
    }


    /**
     * Gets the identificador value for this ResultadoHeaderVO.
     * 
     * @return identificador   * Identificador del repositorio OAI-PMH con la sintaxis: scheme
     * ":" namespace-identifier ":" local-identifier.
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ResultadoHeaderVO.
     * 
     * @param identificador   * Identificador del repositorio OAI-PMH con la sintaxis: scheme
     * ":" namespace-identifier ":" local-identifier.
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the fecha value for this ResultadoHeaderVO.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ResultadoHeaderVO.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the identificadorConjunto value for this ResultadoHeaderVO.
     * 
     * @return identificadorConjunto   * Array con los identificadores de los conjuntos en los que se
     * encuentra el registro
     */
    public java.lang.String[] getIdentificadorConjunto() {
        return identificadorConjunto;
    }


    /**
     * Sets the identificadorConjunto value for this ResultadoHeaderVO.
     * 
     * @param identificadorConjunto   * Array con los identificadores de los conjuntos en los que se
     * encuentra el registro
     */
    public void setIdentificadorConjunto(java.lang.String[] identificadorConjunto) {
        this.identificadorConjunto = identificadorConjunto;
    }


    /**
     * Gets the codigoPaginacion value for this ResultadoHeaderVO.
     * 
     * @return codigoPaginacion   * Identificador de la siguiente página del listado de
     *                                 identificadores.
     */
    public java.lang.String getCodigoPaginacion() {
        return codigoPaginacion;
    }


    /**
     * Sets the codigoPaginacion value for this ResultadoHeaderVO.
     * 
     * @param codigoPaginacion   * Identificador de la siguiente página del listado de
     *                                 identificadores.
     */
    public void setCodigoPaginacion(java.lang.String codigoPaginacion) {
        this.codigoPaginacion = codigoPaginacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoHeaderVO)) return false;
        ResultadoHeaderVO other = (ResultadoHeaderVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.identificadorConjunto==null && other.getIdentificadorConjunto()==null) || 
             (this.identificadorConjunto!=null &&
              java.util.Arrays.equals(this.identificadorConjunto, other.getIdentificadorConjunto()))) &&
            ((this.codigoPaginacion==null && other.getCodigoPaginacion()==null) || 
             (this.codigoPaginacion!=null &&
              this.codigoPaginacion.equals(other.getCodigoPaginacion())));
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
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getIdentificadorConjunto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificadorConjunto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificadorConjunto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodigoPaginacion() != null) {
            _hashCode += getCodigoPaginacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoHeaderVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoHeaderVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorConjunto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificadorConjunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPaginacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "codigoPaginacion"));
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
