/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ReanudacionTokenVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Este objeto de valor simboliza un resumption token implementado
 * por el protocolo OAI-PMH
 */
public class ReanudacionTokenVO  implements java.io.Serializable {
    /* Fecha de expiracion del token de reanudacion. */
    private java.util.Calendar fechaExpiracion;

    /* Identificador del token. */
    private java.lang.String identificador;

    /* Cursor que indica el valor por el que comienza el resultado
     * respecto del total de resultados. */
    private java.lang.Integer cursor;

    /* Tamanio total de la consulta. */
    private java.lang.Integer tamanio;

    public ReanudacionTokenVO() {
    }

    public ReanudacionTokenVO(
           java.util.Calendar fechaExpiracion,
           java.lang.String identificador,
           java.lang.Integer cursor,
           java.lang.Integer tamanio) {
           this.fechaExpiracion = fechaExpiracion;
           this.identificador = identificador;
           this.cursor = cursor;
           this.tamanio = tamanio;
    }


    /**
     * Gets the fechaExpiracion value for this ReanudacionTokenVO.
     * 
     * @return fechaExpiracion   * Fecha de expiracion del token de reanudacion.
     */
    public java.util.Calendar getFechaExpiracion() {
        return fechaExpiracion;
    }


    /**
     * Sets the fechaExpiracion value for this ReanudacionTokenVO.
     * 
     * @param fechaExpiracion   * Fecha de expiracion del token de reanudacion.
     */
    public void setFechaExpiracion(java.util.Calendar fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }


    /**
     * Gets the identificador value for this ReanudacionTokenVO.
     * 
     * @return identificador   * Identificador del token.
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ReanudacionTokenVO.
     * 
     * @param identificador   * Identificador del token.
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the cursor value for this ReanudacionTokenVO.
     * 
     * @return cursor   * Cursor que indica el valor por el que comienza el resultado
     * respecto del total de resultados.
     */
    public java.lang.Integer getCursor() {
        return cursor;
    }


    /**
     * Sets the cursor value for this ReanudacionTokenVO.
     * 
     * @param cursor   * Cursor que indica el valor por el que comienza el resultado
     * respecto del total de resultados.
     */
    public void setCursor(java.lang.Integer cursor) {
        this.cursor = cursor;
    }


    /**
     * Gets the tamanio value for this ReanudacionTokenVO.
     * 
     * @return tamanio   * Tamanio total de la consulta.
     */
    public java.lang.Integer getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this ReanudacionTokenVO.
     * 
     * @param tamanio   * Tamanio total de la consulta.
     */
    public void setTamanio(java.lang.Integer tamanio) {
        this.tamanio = tamanio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReanudacionTokenVO)) return false;
        ReanudacionTokenVO other = (ReanudacionTokenVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaExpiracion==null && other.getFechaExpiracion()==null) || 
             (this.fechaExpiracion!=null &&
              this.fechaExpiracion.equals(other.getFechaExpiracion()))) &&
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.cursor==null && other.getCursor()==null) || 
             (this.cursor!=null &&
              this.cursor.equals(other.getCursor()))) &&
            ((this.tamanio==null && other.getTamanio()==null) || 
             (this.tamanio!=null &&
              this.tamanio.equals(other.getTamanio())));
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
        if (getFechaExpiracion() != null) {
            _hashCode += getFechaExpiracion().hashCode();
        }
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getCursor() != null) {
            _hashCode += getCursor().hashCode();
        }
        if (getTamanio() != null) {
            _hashCode += getTamanio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReanudacionTokenVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ReanudacionTokenVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaExpiracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fechaExpiracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cursor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "cursor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "tamanio"));
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
