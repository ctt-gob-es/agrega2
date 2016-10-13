/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * IdLomVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * Representa un objeto Lom, es decir la representacion de un
 *                         metadato LOM-ES de un objeto educativo o de
 * un componente del
 *                         ODE. Es necesario representarlo como un DataHandler
 * para que
 *                         sirva de intercambio entre diferentes webservices.
 */
public class IdLomVO  implements java.io.Serializable {
    /* Identificador del propietario de dicho metadato LOM-Es. */
    private java.lang.String id;

    /* Representacion del metadato LOM-ES. */
    private javax.activation.DataHandler lomHandler;

    public IdLomVO() {
    }

    public IdLomVO(
           java.lang.String id,
           javax.activation.DataHandler lomHandler) {
           this.id = id;
           this.lomHandler = lomHandler;
    }


    /**
     * Gets the id value for this IdLomVO.
     * 
     * @return id   * Identificador del propietario de dicho metadato LOM-Es.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this IdLomVO.
     * 
     * @param id   * Identificador del propietario de dicho metadato LOM-Es.
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the lomHandler value for this IdLomVO.
     * 
     * @return lomHandler   * Representacion del metadato LOM-ES.
     */
    public javax.activation.DataHandler getLomHandler() {
        return lomHandler;
    }


    /**
     * Sets the lomHandler value for this IdLomVO.
     * 
     * @param lomHandler   * Representacion del metadato LOM-ES.
     */
    public void setLomHandler(javax.activation.DataHandler lomHandler) {
        this.lomHandler = lomHandler;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdLomVO)) return false;
        IdLomVO other = (IdLomVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.lomHandler==null && other.getLomHandler()==null) || 
             (this.lomHandler!=null &&
              this.lomHandler.equals(other.getLomHandler())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLomHandler() != null) {
            _hashCode += getLomHandler().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdLomVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdLomVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lomHandler");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lomHandler"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
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
