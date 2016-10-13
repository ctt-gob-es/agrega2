/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SugerenciasBusquedaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class SugerenciasBusquedaVO  implements java.io.Serializable {
    private java.lang.String sugerencia;

    private java.lang.Integer numOdes;

    public SugerenciasBusquedaVO() {
    }

    public SugerenciasBusquedaVO(
           java.lang.String sugerencia,
           java.lang.Integer numOdes) {
           this.sugerencia = sugerencia;
           this.numOdes = numOdes;
    }


    /**
     * Gets the sugerencia value for this SugerenciasBusquedaVO.
     * 
     * @return sugerencia
     */
    public java.lang.String getSugerencia() {
        return sugerencia;
    }


    /**
     * Sets the sugerencia value for this SugerenciasBusquedaVO.
     * 
     * @param sugerencia
     */
    public void setSugerencia(java.lang.String sugerencia) {
        this.sugerencia = sugerencia;
    }


    /**
     * Gets the numOdes value for this SugerenciasBusquedaVO.
     * 
     * @return numOdes
     */
    public java.lang.Integer getNumOdes() {
        return numOdes;
    }


    /**
     * Sets the numOdes value for this SugerenciasBusquedaVO.
     * 
     * @param numOdes
     */
    public void setNumOdes(java.lang.Integer numOdes) {
        this.numOdes = numOdes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SugerenciasBusquedaVO)) return false;
        SugerenciasBusquedaVO other = (SugerenciasBusquedaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sugerencia==null && other.getSugerencia()==null) || 
             (this.sugerencia!=null &&
              this.sugerencia.equals(other.getSugerencia()))) &&
            ((this.numOdes==null && other.getNumOdes()==null) || 
             (this.numOdes!=null &&
              this.numOdes.equals(other.getNumOdes())));
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
        if (getSugerencia() != null) {
            _hashCode += getSugerencia().hashCode();
        }
        if (getNumOdes() != null) {
            _hashCode += getNumOdes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SugerenciasBusquedaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "SugerenciasBusquedaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sugerencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "sugerencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
