/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ValidarLicenciasVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.licencias.servicio;

public class ValidarLicenciasVO  implements java.io.Serializable {
    private boolean resultado;

    private java.lang.String licenciaAdicional;

    private java.lang.String identificadorElemento;

    public ValidarLicenciasVO() {
    }

    public ValidarLicenciasVO(
           boolean resultado,
           java.lang.String licenciaAdicional,
           java.lang.String identificadorElemento) {
           this.resultado = resultado;
           this.licenciaAdicional = licenciaAdicional;
           this.identificadorElemento = identificadorElemento;
    }


    /**
     * Gets the resultado value for this ValidarLicenciasVO.
     * 
     * @return resultado
     */
    public boolean isResultado() {
        return resultado;
    }


    /**
     * Sets the resultado value for this ValidarLicenciasVO.
     * 
     * @param resultado
     */
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }


    /**
     * Gets the licenciaAdicional value for this ValidarLicenciasVO.
     * 
     * @return licenciaAdicional
     */
    public java.lang.String getLicenciaAdicional() {
        return licenciaAdicional;
    }


    /**
     * Sets the licenciaAdicional value for this ValidarLicenciasVO.
     * 
     * @param licenciaAdicional
     */
    public void setLicenciaAdicional(java.lang.String licenciaAdicional) {
        this.licenciaAdicional = licenciaAdicional;
    }


    /**
     * Gets the identificadorElemento value for this ValidarLicenciasVO.
     * 
     * @return identificadorElemento
     */
    public java.lang.String getIdentificadorElemento() {
        return identificadorElemento;
    }


    /**
     * Sets the identificadorElemento value for this ValidarLicenciasVO.
     * 
     * @param identificadorElemento
     */
    public void setIdentificadorElemento(java.lang.String identificadorElemento) {
        this.identificadorElemento = identificadorElemento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarLicenciasVO)) return false;
        ValidarLicenciasVO other = (ValidarLicenciasVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.resultado == other.isResultado() &&
            ((this.licenciaAdicional==null && other.getLicenciaAdicional()==null) || 
             (this.licenciaAdicional!=null &&
              this.licenciaAdicional.equals(other.getLicenciaAdicional()))) &&
            ((this.identificadorElemento==null && other.getIdentificadorElemento()==null) || 
             (this.identificadorElemento!=null &&
              this.identificadorElemento.equals(other.getIdentificadorElemento())));
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
        _hashCode += (isResultado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLicenciaAdicional() != null) {
            _hashCode += getLicenciaAdicional().hashCode();
        }
        if (getIdentificadorElemento() != null) {
            _hashCode += getIdentificadorElemento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarLicenciasVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "ValidarLicenciasVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "resultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenciaAdicional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "licenciaAdicional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorElemento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.licencias.catalogacion.pode.es", "identificadorElemento"));
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
