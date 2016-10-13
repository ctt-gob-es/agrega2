/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvAnnotationVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvAnnotationVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.EntidadVO entidad;

    private es.pode.catalogacion.negocio.servicios.FechaVO fecha;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion;

    public AvAnnotationVO() {
    }

    public AvAnnotationVO(
           es.pode.catalogacion.negocio.servicios.EntidadVO entidad,
           es.pode.catalogacion.negocio.servicios.FechaVO fecha,
           es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion) {
           this.entidad = entidad;
           this.fecha = fecha;
           this.descripcion = descripcion;
    }


    /**
     * Gets the entidad value for this AvAnnotationVO.
     * 
     * @return entidad
     */
    public es.pode.catalogacion.negocio.servicios.EntidadVO getEntidad() {
        return entidad;
    }


    /**
     * Sets the entidad value for this AvAnnotationVO.
     * 
     * @param entidad
     */
    public void setEntidad(es.pode.catalogacion.negocio.servicios.EntidadVO entidad) {
        this.entidad = entidad;
    }


    /**
     * Gets the fecha value for this AvAnnotationVO.
     * 
     * @return fecha
     */
    public es.pode.catalogacion.negocio.servicios.FechaVO getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this AvAnnotationVO.
     * 
     * @param fecha
     */
    public void setFecha(es.pode.catalogacion.negocio.servicios.FechaVO fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the descripcion value for this AvAnnotationVO.
     * 
     * @return descripcion
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this AvAnnotationVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion) {
        this.descripcion = descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvAnnotationVO)) return false;
        AvAnnotationVO other = (AvAnnotationVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entidad==null && other.getEntidad()==null) || 
             (this.entidad!=null &&
              this.entidad.equals(other.getEntidad()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion())));
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
        if (getEntidad() != null) {
            _hashCode += getEntidad().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AvAnnotationVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvAnnotationVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "entidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntidadVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FechaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO"));
        elemField.setNillable(true);
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
