/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvLifeCycleVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvLifeCycleVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.VersionVO version;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO estado;

    private es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones;

    public AvLifeCycleVO() {
    }

    public AvLifeCycleVO(
           es.pode.catalogacion.negocio.servicios.VersionVO version,
           es.pode.catalogacion.negocio.servicios.SourceValueVO estado,
           es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones) {
           this.version = version;
           this.estado = estado;
           this.contribuciones = contribuciones;
    }


    /**
     * Gets the version value for this AvLifeCycleVO.
     * 
     * @return version
     */
    public es.pode.catalogacion.negocio.servicios.VersionVO getVersion() {
        return version;
    }


    /**
     * Sets the version value for this AvLifeCycleVO.
     * 
     * @param version
     */
    public void setVersion(es.pode.catalogacion.negocio.servicios.VersionVO version) {
        this.version = version;
    }


    /**
     * Gets the estado value for this AvLifeCycleVO.
     * 
     * @return estado
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this AvLifeCycleVO.
     * 
     * @param estado
     */
    public void setEstado(es.pode.catalogacion.negocio.servicios.SourceValueVO estado) {
        this.estado = estado;
    }


    /**
     * Gets the contribuciones value for this AvLifeCycleVO.
     * 
     * @return contribuciones
     */
    public es.pode.catalogacion.negocio.servicios.ContribucionVO[] getContribuciones() {
        return contribuciones;
    }


    /**
     * Sets the contribuciones value for this AvLifeCycleVO.
     * 
     * @param contribuciones
     */
    public void setContribuciones(es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones) {
        this.contribuciones = contribuciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvLifeCycleVO)) return false;
        AvLifeCycleVO other = (AvLifeCycleVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.contribuciones==null && other.getContribuciones()==null) || 
             (this.contribuciones!=null &&
              java.util.Arrays.equals(this.contribuciones, other.getContribuciones())));
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
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getContribuciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContribuciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContribuciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AvLifeCycleVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvLifeCycleVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "contribuciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ContribucionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
