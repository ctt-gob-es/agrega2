/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvRightsVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvRightsVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.SourceValueVO coste;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO derechosDeAutor;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion;

    private es.pode.catalogacion.negocio.servicios.AccesoVO acceso;

    public AvRightsVO() {
    }

    public AvRightsVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO coste,
           es.pode.catalogacion.negocio.servicios.SourceValueVO derechosDeAutor,
           es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion,
           es.pode.catalogacion.negocio.servicios.AccesoVO acceso) {
           this.coste = coste;
           this.derechosDeAutor = derechosDeAutor;
           this.descripcion = descripcion;
           this.acceso = acceso;
    }


    /**
     * Gets the coste value for this AvRightsVO.
     * 
     * @return coste
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getCoste() {
        return coste;
    }


    /**
     * Sets the coste value for this AvRightsVO.
     * 
     * @param coste
     */
    public void setCoste(es.pode.catalogacion.negocio.servicios.SourceValueVO coste) {
        this.coste = coste;
    }


    /**
     * Gets the derechosDeAutor value for this AvRightsVO.
     * 
     * @return derechosDeAutor
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getDerechosDeAutor() {
        return derechosDeAutor;
    }


    /**
     * Sets the derechosDeAutor value for this AvRightsVO.
     * 
     * @param derechosDeAutor
     */
    public void setDerechosDeAutor(es.pode.catalogacion.negocio.servicios.SourceValueVO derechosDeAutor) {
        this.derechosDeAutor = derechosDeAutor;
    }


    /**
     * Gets the descripcion value for this AvRightsVO.
     * 
     * @return descripcion
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this AvRightsVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the acceso value for this AvRightsVO.
     * 
     * @return acceso
     */
    public es.pode.catalogacion.negocio.servicios.AccesoVO getAcceso() {
        return acceso;
    }


    /**
     * Sets the acceso value for this AvRightsVO.
     * 
     * @param acceso
     */
    public void setAcceso(es.pode.catalogacion.negocio.servicios.AccesoVO acceso) {
        this.acceso = acceso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvRightsVO)) return false;
        AvRightsVO other = (AvRightsVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coste==null && other.getCoste()==null) || 
             (this.coste!=null &&
              this.coste.equals(other.getCoste()))) &&
            ((this.derechosDeAutor==null && other.getDerechosDeAutor()==null) || 
             (this.derechosDeAutor!=null &&
              this.derechosDeAutor.equals(other.getDerechosDeAutor()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.acceso==null && other.getAcceso()==null) || 
             (this.acceso!=null &&
              this.acceso.equals(other.getAcceso())));
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
        if (getCoste() != null) {
            _hashCode += getCoste().hashCode();
        }
        if (getDerechosDeAutor() != null) {
            _hashCode += getDerechosDeAutor().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getAcceso() != null) {
            _hashCode += getAcceso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AvRightsVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRightsVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coste");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "coste"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechosDeAutor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "derechosDeAutor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "acceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AccesoVO"));
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
