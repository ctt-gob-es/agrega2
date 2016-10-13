/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CategoriaIdiomaNoticiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * VO que encapsula la información de la categoría en el idioma
 *                         correspondiente.
 */
public class CategoriaIdiomaNoticiaVO  implements java.io.Serializable {
    /* Identificador autogenerado por la base de datos. */
    private java.lang.Long id;

    /* Nombre de la categoría en el idioma correspondiente. */
    private java.lang.String nombreCategoria;

    /* Idioma de la descripción de la categoría. */
    private java.lang.String idioma;

    public CategoriaIdiomaNoticiaVO() {
    }

    public CategoriaIdiomaNoticiaVO(
           java.lang.Long id,
           java.lang.String nombreCategoria,
           java.lang.String idioma) {
           this.id = id;
           this.nombreCategoria = nombreCategoria;
           this.idioma = idioma;
    }


    /**
     * Gets the id value for this CategoriaIdiomaNoticiaVO.
     * 
     * @return id   * Identificador autogenerado por la base de datos.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this CategoriaIdiomaNoticiaVO.
     * 
     * @param id   * Identificador autogenerado por la base de datos.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombreCategoria value for this CategoriaIdiomaNoticiaVO.
     * 
     * @return nombreCategoria   * Nombre de la categoría en el idioma correspondiente.
     */
    public java.lang.String getNombreCategoria() {
        return nombreCategoria;
    }


    /**
     * Sets the nombreCategoria value for this CategoriaIdiomaNoticiaVO.
     * 
     * @param nombreCategoria   * Nombre de la categoría en el idioma correspondiente.
     */
    public void setNombreCategoria(java.lang.String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


    /**
     * Gets the idioma value for this CategoriaIdiomaNoticiaVO.
     * 
     * @return idioma   * Idioma de la descripción de la categoría.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this CategoriaIdiomaNoticiaVO.
     * 
     * @param idioma   * Idioma de la descripción de la categoría.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CategoriaIdiomaNoticiaVO)) return false;
        CategoriaIdiomaNoticiaVO other = (CategoriaIdiomaNoticiaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombreCategoria==null && other.getNombreCategoria()==null) || 
             (this.nombreCategoria!=null &&
              this.nombreCategoria.equals(other.getNombreCategoria()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombreCategoria() != null) {
            _hashCode += getNombreCategoria().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CategoriaIdiomaNoticiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaIdiomaNoticiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "nombreCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"));
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
