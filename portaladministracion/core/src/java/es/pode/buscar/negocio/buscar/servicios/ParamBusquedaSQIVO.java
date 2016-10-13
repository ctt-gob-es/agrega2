/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParamBusquedaSQIVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ParamBusquedaSQIVO  implements java.io.Serializable {
    /* Palabras a buscar */
    private java.lang.String palabrasClave;

    private java.lang.Integer origenPagina;

    private java.lang.Integer numeroResultados;

    private java.lang.Integer resultadoPorPagina;

    public ParamBusquedaSQIVO() {
    }

    public ParamBusquedaSQIVO(
           java.lang.String palabrasClave,
           java.lang.Integer origenPagina,
           java.lang.Integer numeroResultados,
           java.lang.Integer resultadoPorPagina) {
           this.palabrasClave = palabrasClave;
           this.origenPagina = origenPagina;
           this.numeroResultados = numeroResultados;
           this.resultadoPorPagina = resultadoPorPagina;
    }


    /**
     * Gets the palabrasClave value for this ParamBusquedaSQIVO.
     * 
     * @return palabrasClave   * Palabras a buscar
     */
    public java.lang.String getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this ParamBusquedaSQIVO.
     * 
     * @param palabrasClave   * Palabras a buscar
     */
    public void setPalabrasClave(java.lang.String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the origenPagina value for this ParamBusquedaSQIVO.
     * 
     * @return origenPagina
     */
    public java.lang.Integer getOrigenPagina() {
        return origenPagina;
    }


    /**
     * Sets the origenPagina value for this ParamBusquedaSQIVO.
     * 
     * @param origenPagina
     */
    public void setOrigenPagina(java.lang.Integer origenPagina) {
        this.origenPagina = origenPagina;
    }


    /**
     * Gets the numeroResultados value for this ParamBusquedaSQIVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParamBusquedaSQIVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the resultadoPorPagina value for this ParamBusquedaSQIVO.
     * 
     * @return resultadoPorPagina
     */
    public java.lang.Integer getResultadoPorPagina() {
        return resultadoPorPagina;
    }


    /**
     * Sets the resultadoPorPagina value for this ParamBusquedaSQIVO.
     * 
     * @param resultadoPorPagina
     */
    public void setResultadoPorPagina(java.lang.Integer resultadoPorPagina) {
        this.resultadoPorPagina = resultadoPorPagina;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamBusquedaSQIVO)) return false;
        ParamBusquedaSQIVO other = (ParamBusquedaSQIVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              this.palabrasClave.equals(other.getPalabrasClave()))) &&
            ((this.origenPagina==null && other.getOrigenPagina()==null) || 
             (this.origenPagina!=null &&
              this.origenPagina.equals(other.getOrigenPagina()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.resultadoPorPagina==null && other.getResultadoPorPagina()==null) || 
             (this.resultadoPorPagina!=null &&
              this.resultadoPorPagina.equals(other.getResultadoPorPagina())));
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
        if (getPalabrasClave() != null) {
            _hashCode += getPalabrasClave().hashCode();
        }
        if (getOrigenPagina() != null) {
            _hashCode += getOrigenPagina().hashCode();
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getResultadoPorPagina() != null) {
            _hashCode += getResultadoPorPagina().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamBusquedaSQIVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParamBusquedaSQIVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "origenPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoPorPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadoPorPagina"));
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
