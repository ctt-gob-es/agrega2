/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * LomBasicoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * ValueObject que representa un subconjunto de los campos de
 *                         LOM-ES. Estos campos deben ser rellenos por
 * el usuario mediante
 *                         el catalogador basico.
 *                         Se compone de tres valueObjects:
 *                         - generalVO
 *                         - educationalVO
 *                         - classificationVO
 */
public class LomBasicoVO  implements java.io.Serializable {
    private java.lang.String idiomaMetameta;

    private es.pode.catalogacion.negocio.servicios.GeneralVO general;

    private es.pode.catalogacion.negocio.servicios.EducationalVO educational;

    private es.pode.catalogacion.negocio.servicios.ClassificationVO classification;

    public LomBasicoVO() {
    }

    public LomBasicoVO(
           java.lang.String idiomaMetameta,
           es.pode.catalogacion.negocio.servicios.GeneralVO general,
           es.pode.catalogacion.negocio.servicios.EducationalVO educational,
           es.pode.catalogacion.negocio.servicios.ClassificationVO classification) {
           this.idiomaMetameta = idiomaMetameta;
           this.general = general;
           this.educational = educational;
           this.classification = classification;
    }


    /**
     * Gets the idiomaMetameta value for this LomBasicoVO.
     * 
     * @return idiomaMetameta
     */
    public java.lang.String getIdiomaMetameta() {
        return idiomaMetameta;
    }


    /**
     * Sets the idiomaMetameta value for this LomBasicoVO.
     * 
     * @param idiomaMetameta
     */
    public void setIdiomaMetameta(java.lang.String idiomaMetameta) {
        this.idiomaMetameta = idiomaMetameta;
    }


    /**
     * Gets the general value for this LomBasicoVO.
     * 
     * @return general
     */
    public es.pode.catalogacion.negocio.servicios.GeneralVO getGeneral() {
        return general;
    }


    /**
     * Sets the general value for this LomBasicoVO.
     * 
     * @param general
     */
    public void setGeneral(es.pode.catalogacion.negocio.servicios.GeneralVO general) {
        this.general = general;
    }


    /**
     * Gets the educational value for this LomBasicoVO.
     * 
     * @return educational
     */
    public es.pode.catalogacion.negocio.servicios.EducationalVO getEducational() {
        return educational;
    }


    /**
     * Sets the educational value for this LomBasicoVO.
     * 
     * @param educational
     */
    public void setEducational(es.pode.catalogacion.negocio.servicios.EducationalVO educational) {
        this.educational = educational;
    }


    /**
     * Gets the classification value for this LomBasicoVO.
     * 
     * @return classification
     */
    public es.pode.catalogacion.negocio.servicios.ClassificationVO getClassification() {
        return classification;
    }


    /**
     * Sets the classification value for this LomBasicoVO.
     * 
     * @param classification
     */
    public void setClassification(es.pode.catalogacion.negocio.servicios.ClassificationVO classification) {
        this.classification = classification;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LomBasicoVO)) return false;
        LomBasicoVO other = (LomBasicoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idiomaMetameta==null && other.getIdiomaMetameta()==null) || 
             (this.idiomaMetameta!=null &&
              this.idiomaMetameta.equals(other.getIdiomaMetameta()))) &&
            ((this.general==null && other.getGeneral()==null) || 
             (this.general!=null &&
              this.general.equals(other.getGeneral()))) &&
            ((this.educational==null && other.getEducational()==null) || 
             (this.educational!=null &&
              this.educational.equals(other.getEducational()))) &&
            ((this.classification==null && other.getClassification()==null) || 
             (this.classification!=null &&
              this.classification.equals(other.getClassification())));
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
        if (getIdiomaMetameta() != null) {
            _hashCode += getIdiomaMetameta().hashCode();
        }
        if (getGeneral() != null) {
            _hashCode += getGeneral().hashCode();
        }
        if (getEducational() != null) {
            _hashCode += getEducational().hashCode();
        }
        if (getClassification() != null) {
            _hashCode += getClassification().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LomBasicoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomBasicoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaMetameta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idiomaMetameta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "general"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "GeneralVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("educational");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "educational"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EducationalVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "classification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ClassificationVO"));
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
