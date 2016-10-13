/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AgregadorORVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AgregadorORVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.SourceValueVO tipo;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO nombre;

    private es.pode.catalogacion.negocio.servicios.VersionMinVO versionMin;

    private es.pode.catalogacion.negocio.servicios.VersionMaxVO versionMax;

    public AgregadorORVO() {
    }

    public AgregadorORVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO tipo,
           es.pode.catalogacion.negocio.servicios.SourceValueVO nombre,
           es.pode.catalogacion.negocio.servicios.VersionMinVO versionMin,
           es.pode.catalogacion.negocio.servicios.VersionMaxVO versionMax) {
           this.tipo = tipo;
           this.nombre = nombre;
           this.versionMin = versionMin;
           this.versionMax = versionMax;
    }


    /**
     * Gets the tipo value for this AgregadorORVO.
     * 
     * @return tipo
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this AgregadorORVO.
     * 
     * @param tipo
     */
    public void setTipo(es.pode.catalogacion.negocio.servicios.SourceValueVO tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the nombre value for this AgregadorORVO.
     * 
     * @return nombre
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this AgregadorORVO.
     * 
     * @param nombre
     */
    public void setNombre(es.pode.catalogacion.negocio.servicios.SourceValueVO nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the versionMin value for this AgregadorORVO.
     * 
     * @return versionMin
     */
    public es.pode.catalogacion.negocio.servicios.VersionMinVO getVersionMin() {
        return versionMin;
    }


    /**
     * Sets the versionMin value for this AgregadorORVO.
     * 
     * @param versionMin
     */
    public void setVersionMin(es.pode.catalogacion.negocio.servicios.VersionMinVO versionMin) {
        this.versionMin = versionMin;
    }


    /**
     * Gets the versionMax value for this AgregadorORVO.
     * 
     * @return versionMax
     */
    public es.pode.catalogacion.negocio.servicios.VersionMaxVO getVersionMax() {
        return versionMax;
    }


    /**
     * Sets the versionMax value for this AgregadorORVO.
     * 
     * @param versionMax
     */
    public void setVersionMax(es.pode.catalogacion.negocio.servicios.VersionMaxVO versionMax) {
        this.versionMax = versionMax;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgregadorORVO)) return false;
        AgregadorORVO other = (AgregadorORVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.versionMin==null && other.getVersionMin()==null) || 
             (this.versionMin!=null &&
              this.versionMin.equals(other.getVersionMin()))) &&
            ((this.versionMax==null && other.getVersionMax()==null) || 
             (this.versionMax!=null &&
              this.versionMax.equals(other.getVersionMax())));
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getVersionMin() != null) {
            _hashCode += getVersionMin().hashCode();
        }
        if (getVersionMax() != null) {
            _hashCode += getVersionMax().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgregadorORVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AgregadorORVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionMin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "versionMin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionMinVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionMax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "versionMax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionMaxVO"));
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
