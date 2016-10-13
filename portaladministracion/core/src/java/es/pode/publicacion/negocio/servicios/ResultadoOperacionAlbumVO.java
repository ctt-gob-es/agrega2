/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ResultadoOperacionAlbumVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public class ResultadoOperacionAlbumVO  implements java.io.Serializable {
    /* Identificador alfanumerico del tipo de incidencia que se ha
     * detectado y que se reporta */
    private java.lang.String idResultado;

    /* Descripcion del resultado de la operacion que se ha realizado.
     * Se devuelve internacionalizada al idioma en el que se realiza la
     *                                 petici√≥n. */
    private java.lang.String descripcion;

    /* Album del que se reporta el resultado de la operacion. */
    private es.pode.publicacion.negocio.servicios.AlbumVO album;

    public ResultadoOperacionAlbumVO() {
    }

    public ResultadoOperacionAlbumVO(
           java.lang.String idResultado,
           java.lang.String descripcion,
           es.pode.publicacion.negocio.servicios.AlbumVO album) {
           this.idResultado = idResultado;
           this.descripcion = descripcion;
           this.album = album;
    }


    /**
     * Gets the idResultado value for this ResultadoOperacionAlbumVO.
     * 
     * @return idResultado   * Identificador alfanumerico del tipo de incidencia que se ha
     * detectado y que se reporta
     */
    public java.lang.String getIdResultado() {
        return idResultado;
    }


    /**
     * Sets the idResultado value for this ResultadoOperacionAlbumVO.
     * 
     * @param idResultado   * Identificador alfanumerico del tipo de incidencia que se ha
     * detectado y que se reporta
     */
    public void setIdResultado(java.lang.String idResultado) {
        this.idResultado = idResultado;
    }


    /**
     * Gets the descripcion value for this ResultadoOperacionAlbumVO.
     * 
     * @return descripcion   * Descripcion del resultado de la operacion que se ha realizado.
     * Se devuelve internacionalizada al idioma en el que se realiza la
     *                                 petici√≥n.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ResultadoOperacionAlbumVO.
     * 
     * @param descripcion   * Descripcion del resultado de la operacion que se ha realizado.
     * Se devuelve internacionalizada al idioma en el que se realiza la
     *                                 petici√≥n.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the album value for this ResultadoOperacionAlbumVO.
     * 
     * @return album   * Album del que se reporta el resultado de la operacion.
     */
    public es.pode.publicacion.negocio.servicios.AlbumVO getAlbum() {
        return album;
    }


    /**
     * Sets the album value for this ResultadoOperacionAlbumVO.
     * 
     * @param album   * Album del que se reporta el resultado de la operacion.
     */
    public void setAlbum(es.pode.publicacion.negocio.servicios.AlbumVO album) {
        this.album = album;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoOperacionAlbumVO)) return false;
        ResultadoOperacionAlbumVO other = (ResultadoOperacionAlbumVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idResultado==null && other.getIdResultado()==null) || 
             (this.idResultado!=null &&
              this.idResultado.equals(other.getIdResultado()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.album==null && other.getAlbum()==null) || 
             (this.album!=null &&
              this.album.equals(other.getAlbum())));
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
        if (getIdResultado() != null) {
            _hashCode += getIdResultado().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getAlbum() != null) {
            _hashCode += getAlbum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoOperacionAlbumVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ResultadoOperacionAlbumVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "idResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("album");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "album"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "AlbumVO"));
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
