/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ValoracionODEVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios.auditoria;


/**
 * Esta clase almacena los parametros que indican la valoracion de
 * un ODE.
 */
public class ValoracionODEVO  implements java.io.Serializable {
    /* Identifiacador del ODE valorado. */
    private java.lang.String idODE;

    /* Valoracion del ODE. */
    private java.lang.Float valoracion;

    /* Idioma del ODE */
    private java.lang.String idiomaODE;

    public ValoracionODEVO() {
    }

    public ValoracionODEVO(
           java.lang.String idODE,
           java.lang.Float valoracion,
           java.lang.String idiomaODE) {
           this.idODE = idODE;
           this.valoracion = valoracion;
           this.idiomaODE = idiomaODE;
    }


    /**
     * Gets the idODE value for this ValoracionODEVO.
     * 
     * @return idODE   * Identifiacador del ODE valorado.
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this ValoracionODEVO.
     * 
     * @param idODE   * Identifiacador del ODE valorado.
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }


    /**
     * Gets the valoracion value for this ValoracionODEVO.
     * 
     * @return valoracion   * Valoracion del ODE.
     */
    public java.lang.Float getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ValoracionODEVO.
     * 
     * @param valoracion   * Valoracion del ODE.
     */
    public void setValoracion(java.lang.Float valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the idiomaODE value for this ValoracionODEVO.
     * 
     * @return idiomaODE   * Idioma del ODE
     */
    public java.lang.String getIdiomaODE() {
        return idiomaODE;
    }


    /**
     * Sets the idiomaODE value for this ValoracionODEVO.
     * 
     * @param idiomaODE   * Idioma del ODE
     */
    public void setIdiomaODE(java.lang.String idiomaODE) {
        this.idiomaODE = idiomaODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValoracionODEVO)) return false;
        ValoracionODEVO other = (ValoracionODEVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.idiomaODE==null && other.getIdiomaODE()==null) || 
             (this.idiomaODE!=null &&
              this.idiomaODE.equals(other.getIdiomaODE())));
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
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getIdiomaODE() != null) {
            _hashCode += getIdiomaODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValoracionODEVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "ValoracionODEVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "idODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "idiomaODE"));
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
