/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ValidaBajaNodoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class ValidaBajaNodoVO  implements java.io.Serializable {
    private es.pode.buscar.negocio.administrar.servicio.NodoVO[] nodosBorrados;

    private java.lang.String descripcionBaja;

    public ValidaBajaNodoVO() {
    }

    public ValidaBajaNodoVO(
           es.pode.buscar.negocio.administrar.servicio.NodoVO[] nodosBorrados,
           java.lang.String descripcionBaja) {
           this.nodosBorrados = nodosBorrados;
           this.descripcionBaja = descripcionBaja;
    }


    /**
     * Gets the nodosBorrados value for this ValidaBajaNodoVO.
     * 
     * @return nodosBorrados
     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO[] getNodosBorrados() {
        return nodosBorrados;
    }


    /**
     * Sets the nodosBorrados value for this ValidaBajaNodoVO.
     * 
     * @param nodosBorrados
     */
    public void setNodosBorrados(es.pode.buscar.negocio.administrar.servicio.NodoVO[] nodosBorrados) {
        this.nodosBorrados = nodosBorrados;
    }


    /**
     * Gets the descripcionBaja value for this ValidaBajaNodoVO.
     * 
     * @return descripcionBaja
     */
    public java.lang.String getDescripcionBaja() {
        return descripcionBaja;
    }


    /**
     * Sets the descripcionBaja value for this ValidaBajaNodoVO.
     * 
     * @param descripcionBaja
     */
    public void setDescripcionBaja(java.lang.String descripcionBaja) {
        this.descripcionBaja = descripcionBaja;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaBajaNodoVO)) return false;
        ValidaBajaNodoVO other = (ValidaBajaNodoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nodosBorrados==null && other.getNodosBorrados()==null) || 
             (this.nodosBorrados!=null &&
              java.util.Arrays.equals(this.nodosBorrados, other.getNodosBorrados()))) &&
            ((this.descripcionBaja==null && other.getDescripcionBaja()==null) || 
             (this.descripcionBaja!=null &&
              this.descripcionBaja.equals(other.getDescripcionBaja())));
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
        if (getNodosBorrados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNodosBorrados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNodosBorrados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescripcionBaja() != null) {
            _hashCode += getDescripcionBaja().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaBajaNodoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ValidaBajaNodoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodosBorrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "nodosBorrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "descripcionBaja"));
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
