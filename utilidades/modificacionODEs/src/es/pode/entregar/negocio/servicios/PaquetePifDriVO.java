/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * PaquetePifDriVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class PaquetePifDriVO  implements java.io.Serializable {
    private es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion;

    private javax.activation.DataHandler paquetePIF;

    public PaquetePifDriVO() {
    }

    public PaquetePifDriVO(
           es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion,
           javax.activation.DataHandler paquetePIF) {
           this.resultadoValidacion = resultadoValidacion;
           this.paquetePIF = paquetePIF;
    }


    /**
     * Gets the resultadoValidacion value for this PaquetePifDriVO.
     * 
     * @return resultadoValidacion
     */
    public es.pode.entregar.negocio.servicios.CBValidoVO getResultadoValidacion() {
        return resultadoValidacion;
    }


    /**
     * Sets the resultadoValidacion value for this PaquetePifDriVO.
     * 
     * @param resultadoValidacion
     */
    public void setResultadoValidacion(es.pode.entregar.negocio.servicios.CBValidoVO resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }


    /**
     * Gets the paquetePIF value for this PaquetePifDriVO.
     * 
     * @return paquetePIF
     */
    public javax.activation.DataHandler getPaquetePIF() {
        return paquetePIF;
    }


    /**
     * Sets the paquetePIF value for this PaquetePifDriVO.
     * 
     * @param paquetePIF
     */
    public void setPaquetePIF(javax.activation.DataHandler paquetePIF) {
        this.paquetePIF = paquetePIF;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaquetePifDriVO)) return false;
        PaquetePifDriVO other = (PaquetePifDriVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoValidacion==null && other.getResultadoValidacion()==null) || 
             (this.resultadoValidacion!=null &&
              this.resultadoValidacion.equals(other.getResultadoValidacion()))) &&
            ((this.paquetePIF==null && other.getPaquetePIF()==null) || 
             (this.paquetePIF!=null &&
              this.paquetePIF.equals(other.getPaquetePIF())));
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
        if (getResultadoValidacion() != null) {
            _hashCode += getResultadoValidacion().hashCode();
        }
        if (getPaquetePIF() != null) {
            _hashCode += getPaquetePIF().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaquetePifDriVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "PaquetePifDriVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "resultadoValidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "CBValidoVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paquetePIF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "paquetePIF"));
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
