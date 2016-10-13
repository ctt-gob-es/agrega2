/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ResultadoEnvioCorreoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;


/**
 * Value Object con el resultado del envío del correo
 */
public class ResultadoEnvioCorreoVO  implements java.io.Serializable {
    /* Cadena con el resultado del envío, los valores posibles serán
     * OK
     *                                 o ERROR. */
    private java.lang.String resultado;

    /* Cadena en la que se describe con más detalle cuál ha sido el
     * resultado del envío del correo */
    private java.lang.String resultadoTexto;

    public ResultadoEnvioCorreoVO() {
    }

    public ResultadoEnvioCorreoVO(
           java.lang.String resultado,
           java.lang.String resultadoTexto) {
           this.resultado = resultado;
           this.resultadoTexto = resultadoTexto;
    }


    /**
     * Gets the resultado value for this ResultadoEnvioCorreoVO.
     * 
     * @return resultado   * Cadena con el resultado del envío, los valores posibles serán
     * OK
     *                                 o ERROR.
     */
    public java.lang.String getResultado() {
        return resultado;
    }


    /**
     * Sets the resultado value for this ResultadoEnvioCorreoVO.
     * 
     * @param resultado   * Cadena con el resultado del envío, los valores posibles serán
     * OK
     *                                 o ERROR.
     */
    public void setResultado(java.lang.String resultado) {
        this.resultado = resultado;
    }


    /**
     * Gets the resultadoTexto value for this ResultadoEnvioCorreoVO.
     * 
     * @return resultadoTexto   * Cadena en la que se describe con más detalle cuál ha sido el
     * resultado del envío del correo
     */
    public java.lang.String getResultadoTexto() {
        return resultadoTexto;
    }


    /**
     * Sets the resultadoTexto value for this ResultadoEnvioCorreoVO.
     * 
     * @param resultadoTexto   * Cadena en la que se describe con más detalle cuál ha sido el
     * resultado del envío del correo
     */
    public void setResultadoTexto(java.lang.String resultadoTexto) {
        this.resultadoTexto = resultadoTexto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoEnvioCorreoVO)) return false;
        ResultadoEnvioCorreoVO other = (ResultadoEnvioCorreoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultado==null && other.getResultado()==null) || 
             (this.resultado!=null &&
              this.resultado.equals(other.getResultado()))) &&
            ((this.resultadoTexto==null && other.getResultadoTexto()==null) || 
             (this.resultadoTexto!=null &&
              this.resultadoTexto.equals(other.getResultadoTexto())));
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
        if (getResultado() != null) {
            _hashCode += getResultado().hashCode();
        }
        if (getResultadoTexto() != null) {
            _hashCode += getResultadoTexto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoEnvioCorreoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "resultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "resultadoTexto"));
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
