/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * DescripcionOaiIdentifierVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Clase de objeto de valor que contiene la descripcion de como se
 * genera el identificador OAI del repositorio.
 */
public class DescripcionOaiIdentifierVO  implements java.io.Serializable {
    /* Esquema del identificador OAI. */
    private java.lang.String esquema;

    /* Delimitador del identificador OAI (:) */
    private java.lang.String delimitador;

    /* Ejemplo del identificador OAI. */
    private java.lang.String ejemploIdentificador;

    /* Identificador del repositorio (agrega.es) */
    private java.lang.String identificadorRepositorio;

    public DescripcionOaiIdentifierVO() {
    }

    public DescripcionOaiIdentifierVO(
           java.lang.String esquema,
           java.lang.String delimitador,
           java.lang.String ejemploIdentificador,
           java.lang.String identificadorRepositorio) {
           this.esquema = esquema;
           this.delimitador = delimitador;
           this.ejemploIdentificador = ejemploIdentificador;
           this.identificadorRepositorio = identificadorRepositorio;
    }


    /**
     * Gets the esquema value for this DescripcionOaiIdentifierVO.
     * 
     * @return esquema   * Esquema del identificador OAI.
     */
    public java.lang.String getEsquema() {
        return esquema;
    }


    /**
     * Sets the esquema value for this DescripcionOaiIdentifierVO.
     * 
     * @param esquema   * Esquema del identificador OAI.
     */
    public void setEsquema(java.lang.String esquema) {
        this.esquema = esquema;
    }


    /**
     * Gets the delimitador value for this DescripcionOaiIdentifierVO.
     * 
     * @return delimitador   * Delimitador del identificador OAI (:)
     */
    public java.lang.String getDelimitador() {
        return delimitador;
    }


    /**
     * Sets the delimitador value for this DescripcionOaiIdentifierVO.
     * 
     * @param delimitador   * Delimitador del identificador OAI (:)
     */
    public void setDelimitador(java.lang.String delimitador) {
        this.delimitador = delimitador;
    }


    /**
     * Gets the ejemploIdentificador value for this DescripcionOaiIdentifierVO.
     * 
     * @return ejemploIdentificador   * Ejemplo del identificador OAI.
     */
    public java.lang.String getEjemploIdentificador() {
        return ejemploIdentificador;
    }


    /**
     * Sets the ejemploIdentificador value for this DescripcionOaiIdentifierVO.
     * 
     * @param ejemploIdentificador   * Ejemplo del identificador OAI.
     */
    public void setEjemploIdentificador(java.lang.String ejemploIdentificador) {
        this.ejemploIdentificador = ejemploIdentificador;
    }


    /**
     * Gets the identificadorRepositorio value for this DescripcionOaiIdentifierVO.
     * 
     * @return identificadorRepositorio   * Identificador del repositorio (agrega.es)
     */
    public java.lang.String getIdentificadorRepositorio() {
        return identificadorRepositorio;
    }


    /**
     * Sets the identificadorRepositorio value for this DescripcionOaiIdentifierVO.
     * 
     * @param identificadorRepositorio   * Identificador del repositorio (agrega.es)
     */
    public void setIdentificadorRepositorio(java.lang.String identificadorRepositorio) {
        this.identificadorRepositorio = identificadorRepositorio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescripcionOaiIdentifierVO)) return false;
        DescripcionOaiIdentifierVO other = (DescripcionOaiIdentifierVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.esquema==null && other.getEsquema()==null) || 
             (this.esquema!=null &&
              this.esquema.equals(other.getEsquema()))) &&
            ((this.delimitador==null && other.getDelimitador()==null) || 
             (this.delimitador!=null &&
              this.delimitador.equals(other.getDelimitador()))) &&
            ((this.ejemploIdentificador==null && other.getEjemploIdentificador()==null) || 
             (this.ejemploIdentificador!=null &&
              this.ejemploIdentificador.equals(other.getEjemploIdentificador()))) &&
            ((this.identificadorRepositorio==null && other.getIdentificadorRepositorio()==null) || 
             (this.identificadorRepositorio!=null &&
              this.identificadorRepositorio.equals(other.getIdentificadorRepositorio())));
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
        if (getEsquema() != null) {
            _hashCode += getEsquema().hashCode();
        }
        if (getDelimitador() != null) {
            _hashCode += getDelimitador().hashCode();
        }
        if (getEjemploIdentificador() != null) {
            _hashCode += getEjemploIdentificador().hashCode();
        }
        if (getIdentificadorRepositorio() != null) {
            _hashCode += getIdentificadorRepositorio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescripcionOaiIdentifierVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "DescripcionOaiIdentifierVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esquema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "esquema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delimitador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "delimitador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ejemploIdentificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ejemploIdentificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorRepositorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificadorRepositorio"));
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
