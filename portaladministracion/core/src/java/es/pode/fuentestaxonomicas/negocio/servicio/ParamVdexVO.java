/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParamVdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class ParamVdexVO  implements java.io.Serializable {
    private javax.activation.DataHandler ficheroXml;

    private java.lang.String nombreFichero;

    public ParamVdexVO() {
    }

    public ParamVdexVO(
           javax.activation.DataHandler ficheroXml,
           java.lang.String nombreFichero) {
           this.ficheroXml = ficheroXml;
           this.nombreFichero = nombreFichero;
    }


    /**
     * Gets the ficheroXml value for this ParamVdexVO.
     * 
     * @return ficheroXml
     */
    public javax.activation.DataHandler getFicheroXml() {
        return ficheroXml;
    }


    /**
     * Sets the ficheroXml value for this ParamVdexVO.
     * 
     * @param ficheroXml
     */
    public void setFicheroXml(javax.activation.DataHandler ficheroXml) {
        this.ficheroXml = ficheroXml;
    }


    /**
     * Gets the nombreFichero value for this ParamVdexVO.
     * 
     * @return nombreFichero
     */
    public java.lang.String getNombreFichero() {
        return nombreFichero;
    }


    /**
     * Sets the nombreFichero value for this ParamVdexVO.
     * 
     * @param nombreFichero
     */
    public void setNombreFichero(java.lang.String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamVdexVO)) return false;
        ParamVdexVO other = (ParamVdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ficheroXml==null && other.getFicheroXml()==null) || 
             (this.ficheroXml!=null &&
              this.ficheroXml.equals(other.getFicheroXml()))) &&
            ((this.nombreFichero==null && other.getNombreFichero()==null) || 
             (this.nombreFichero!=null &&
              this.nombreFichero.equals(other.getNombreFichero())));
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
        if (getFicheroXml() != null) {
            _hashCode += getFicheroXml().hashCode();
        }
        if (getNombreFichero() != null) {
            _hashCode += getNombreFichero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamVdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ParamVdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ficheroXml");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ficheroXml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreFichero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nombreFichero"));
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
