/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametroAuditValoracionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios.auditoria;


/**
 * Esta clase aberga los atributos necesarios para las operaciones
 * de auditoria del modulo de valoracion.
 */
public class ParametroAuditValoracionVO  implements java.io.Serializable {
    /* Se trata de la fecha desde del periodo de actividad en el que
     * se
     *                                 esta interesado. */
    private java.util.Calendar fechaDesde;

    /* Se trata de la fecha hasta del periodo de actividad en el que
     * se
     *                                 esta interesado. */
    private java.util.Calendar fechaHasta;

    /* Numero de elementos maximo que se quiere que devuelva la
     *                                 consulta. */
    private java.lang.Integer rango;

    public ParametroAuditValoracionVO() {
    }

    public ParametroAuditValoracionVO(
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.Integer rango) {
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.rango = rango;
    }


    /**
     * Gets the fechaDesde value for this ParametroAuditValoracionVO.
     * 
     * @return fechaDesde   * Se trata de la fecha desde del periodo de actividad en el que
     * se
     *                                 esta interesado.
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this ParametroAuditValoracionVO.
     * 
     * @param fechaDesde   * Se trata de la fecha desde del periodo de actividad en el que
     * se
     *                                 esta interesado.
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this ParametroAuditValoracionVO.
     * 
     * @return fechaHasta   * Se trata de la fecha hasta del periodo de actividad en el que
     * se
     *                                 esta interesado.
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this ParametroAuditValoracionVO.
     * 
     * @param fechaHasta   * Se trata de la fecha hasta del periodo de actividad en el que
     * se
     *                                 esta interesado.
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the rango value for this ParametroAuditValoracionVO.
     * 
     * @return rango   * Numero de elementos maximo que se quiere que devuelva la
     *                                 consulta.
     */
    public java.lang.Integer getRango() {
        return rango;
    }


    /**
     * Sets the rango value for this ParametroAuditValoracionVO.
     * 
     * @param rango   * Numero de elementos maximo que se quiere que devuelva la
     *                                 consulta.
     */
    public void setRango(java.lang.Integer rango) {
        this.rango = rango;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroAuditValoracionVO)) return false;
        ParametroAuditValoracionVO other = (ParametroAuditValoracionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.rango==null && other.getRango()==null) || 
             (this.rango!=null &&
              this.rango.equals(other.getRango())));
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
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getRango() != null) {
            _hashCode += getRango().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametroAuditValoracionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "ParametroAuditValoracionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "fechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "fechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rango");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.valoracion.pode.es", "rango"));
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
