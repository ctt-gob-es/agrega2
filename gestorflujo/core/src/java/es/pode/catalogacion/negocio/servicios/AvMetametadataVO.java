/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvMetametadataVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvMetametadataVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.IdiomaVO idioma;

    private es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores;

    private es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones;

    private es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO[] esquemas;

    public AvMetametadataVO() {
    }

    public AvMetametadataVO(
           es.pode.catalogacion.negocio.servicios.IdiomaVO idioma,
           es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores,
           es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones,
           es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO[] esquemas) {
           this.idioma = idioma;
           this.identificadores = identificadores;
           this.contribuciones = contribuciones;
           this.esquemas = esquemas;
    }


    /**
     * Gets the idioma value for this AvMetametadataVO.
     * 
     * @return idioma
     */
    public es.pode.catalogacion.negocio.servicios.IdiomaVO getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this AvMetametadataVO.
     * 
     * @param idioma
     */
    public void setIdioma(es.pode.catalogacion.negocio.servicios.IdiomaVO idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the identificadores value for this AvMetametadataVO.
     * 
     * @return identificadores
     */
    public es.pode.catalogacion.negocio.servicios.IdentificadorVO[] getIdentificadores() {
        return identificadores;
    }


    /**
     * Sets the identificadores value for this AvMetametadataVO.
     * 
     * @param identificadores
     */
    public void setIdentificadores(es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores) {
        this.identificadores = identificadores;
    }


    /**
     * Gets the contribuciones value for this AvMetametadataVO.
     * 
     * @return contribuciones
     */
    public es.pode.catalogacion.negocio.servicios.ContribucionVO[] getContribuciones() {
        return contribuciones;
    }


    /**
     * Sets the contribuciones value for this AvMetametadataVO.
     * 
     * @param contribuciones
     */
    public void setContribuciones(es.pode.catalogacion.negocio.servicios.ContribucionVO[] contribuciones) {
        this.contribuciones = contribuciones;
    }


    /**
     * Gets the esquemas value for this AvMetametadataVO.
     * 
     * @return esquemas
     */
    public es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO[] getEsquemas() {
        return esquemas;
    }


    /**
     * Sets the esquemas value for this AvMetametadataVO.
     * 
     * @param esquemas
     */
    public void setEsquemas(es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO[] esquemas) {
        this.esquemas = esquemas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvMetametadataVO)) return false;
        AvMetametadataVO other = (AvMetametadataVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.identificadores==null && other.getIdentificadores()==null) || 
             (this.identificadores!=null &&
              java.util.Arrays.equals(this.identificadores, other.getIdentificadores()))) &&
            ((this.contribuciones==null && other.getContribuciones()==null) || 
             (this.contribuciones!=null &&
              java.util.Arrays.equals(this.contribuciones, other.getContribuciones()))) &&
            ((this.esquemas==null && other.getEsquemas()==null) || 
             (this.esquemas!=null &&
              java.util.Arrays.equals(this.esquemas, other.getEsquemas())));
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
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getIdentificadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificadores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getEsquemas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEsquemas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEsquemas(), i);
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
        new org.apache.axis.description.TypeDesc(AvMetametadataVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvMetametadataVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdiomaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "contribuciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ContribucionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esquemas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "esquemas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EsquemaDeMetadatosVO"));
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
