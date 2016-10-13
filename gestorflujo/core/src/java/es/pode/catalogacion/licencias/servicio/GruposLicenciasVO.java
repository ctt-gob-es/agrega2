/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * GruposLicenciasVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.licencias.servicio;

public class GruposLicenciasVO  implements java.io.Serializable {
    private java.lang.String grupoLicencias;

    private int ordenLimitacion;

    public GruposLicenciasVO() {
    }

    public GruposLicenciasVO(
           java.lang.String grupoLicencias,
           int ordenLimitacion) {
           this.grupoLicencias = grupoLicencias;
           this.ordenLimitacion = ordenLimitacion;
    }


    /**
     * Gets the grupoLicencias value for this GruposLicenciasVO.
     * 
     * @return grupoLicencias
     */
    public java.lang.String getGrupoLicencias() {
        return grupoLicencias;
    }


    /**
     * Sets the grupoLicencias value for this GruposLicenciasVO.
     * 
     * @param grupoLicencias
     */
    public void setGrupoLicencias(java.lang.String grupoLicencias) {
        this.grupoLicencias = grupoLicencias;
    }


    /**
     * Gets the ordenLimitacion value for this GruposLicenciasVO.
     * 
     * @return ordenLimitacion
     */
    public int getOrdenLimitacion() {
        return ordenLimitacion;
    }


    /**
     * Sets the ordenLimitacion value for this GruposLicenciasVO.
     * 
     * @param ordenLimitacion
     */
    public void setOrdenLimitacion(int ordenLimitacion) {
        this.ordenLimitacion = ordenLimitacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GruposLicenciasVO)) return false;
        GruposLicenciasVO other = (GruposLicenciasVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.grupoLicencias==null && other.getGrupoLicencias()==null) || 
             (this.grupoLicencias!=null &&
              this.grupoLicencias.equals(other.getGrupoLicencias()))) &&
            this.ordenLimitacion == other.getOrdenLimitacion();
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
        if (getGrupoLicencias() != null) {
            _hashCode += getGrupoLicencias().hashCode();
        }
        _hashCode += getOrdenLimitacion();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GruposLicenciasVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "GruposLicenciasVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoLicencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "grupoLicencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenLimitacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "ordenLimitacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
