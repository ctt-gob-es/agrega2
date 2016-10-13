/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametrosCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class ParametrosCargaVO  implements java.io.Serializable {
    private java.lang.String nombreParametro;

    private java.lang.String valorAtributo;

    public ParametrosCargaVO() {
    }

    public ParametrosCargaVO(
           java.lang.String nombreParametro,
           java.lang.String valorAtributo) {
           this.nombreParametro = nombreParametro;
           this.valorAtributo = valorAtributo;
    }


    /**
     * Gets the nombreParametro value for this ParametrosCargaVO.
     * 
     * @return nombreParametro
     */
    public java.lang.String getNombreParametro() {
        return nombreParametro;
    }


    /**
     * Sets the nombreParametro value for this ParametrosCargaVO.
     * 
     * @param nombreParametro
     */
    public void setNombreParametro(java.lang.String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }


    /**
     * Gets the valorAtributo value for this ParametrosCargaVO.
     * 
     * @return valorAtributo
     */
    public java.lang.String getValorAtributo() {
        return valorAtributo;
    }


    /**
     * Sets the valorAtributo value for this ParametrosCargaVO.
     * 
     * @param valorAtributo
     */
    public void setValorAtributo(java.lang.String valorAtributo) {
        this.valorAtributo = valorAtributo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCargaVO)) return false;
        ParametrosCargaVO other = (ParametrosCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreParametro==null && other.getNombreParametro()==null) || 
             (this.nombreParametro!=null &&
              this.nombreParametro.equals(other.getNombreParametro()))) &&
            ((this.valorAtributo==null && other.getValorAtributo()==null) || 
             (this.valorAtributo!=null &&
              this.valorAtributo.equals(other.getValorAtributo())));
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
        if (getNombreParametro() != null) {
            _hashCode += getNombreParametro().hashCode();
        }
        if (getValorAtributo() != null) {
            _hashCode += getValorAtributo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametrosCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreParametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreParametro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAtributo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "valorAtributo"));
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
