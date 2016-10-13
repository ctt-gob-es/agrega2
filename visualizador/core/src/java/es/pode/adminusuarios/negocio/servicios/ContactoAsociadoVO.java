/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ContactoAsociadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class ContactoAsociadoVO  implements java.io.Serializable {
    private java.lang.String usuarioContacto;

    private java.lang.String imagenUsuario;

    private java.lang.Boolean esAsociadoUsuario;

    private java.lang.String descripcionUsuario;

    private java.lang.Long id;

    public ContactoAsociadoVO() {
    }

    public ContactoAsociadoVO(
           java.lang.String usuarioContacto,
           java.lang.String imagenUsuario,
           java.lang.Boolean esAsociadoUsuario,
           java.lang.String descripcionUsuario,
           java.lang.Long id) {
           this.usuarioContacto = usuarioContacto;
           this.imagenUsuario = imagenUsuario;
           this.esAsociadoUsuario = esAsociadoUsuario;
           this.descripcionUsuario = descripcionUsuario;
           this.id = id;
    }


    /**
     * Gets the usuarioContacto value for this ContactoAsociadoVO.
     * 
     * @return usuarioContacto
     */
    public java.lang.String getUsuarioContacto() {
        return usuarioContacto;
    }


    /**
     * Sets the usuarioContacto value for this ContactoAsociadoVO.
     * 
     * @param usuarioContacto
     */
    public void setUsuarioContacto(java.lang.String usuarioContacto) {
        this.usuarioContacto = usuarioContacto;
    }


    /**
     * Gets the imagenUsuario value for this ContactoAsociadoVO.
     * 
     * @return imagenUsuario
     */
    public java.lang.String getImagenUsuario() {
        return imagenUsuario;
    }


    /**
     * Sets the imagenUsuario value for this ContactoAsociadoVO.
     * 
     * @param imagenUsuario
     */
    public void setImagenUsuario(java.lang.String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }


    /**
     * Gets the esAsociadoUsuario value for this ContactoAsociadoVO.
     * 
     * @return esAsociadoUsuario
     */
    public java.lang.Boolean getEsAsociadoUsuario() {
        return esAsociadoUsuario;
    }


    /**
     * Sets the esAsociadoUsuario value for this ContactoAsociadoVO.
     * 
     * @param esAsociadoUsuario
     */
    public void setEsAsociadoUsuario(java.lang.Boolean esAsociadoUsuario) {
        this.esAsociadoUsuario = esAsociadoUsuario;
    }


    /**
     * Gets the descripcionUsuario value for this ContactoAsociadoVO.
     * 
     * @return descripcionUsuario
     */
    public java.lang.String getDescripcionUsuario() {
        return descripcionUsuario;
    }


    /**
     * Sets the descripcionUsuario value for this ContactoAsociadoVO.
     * 
     * @param descripcionUsuario
     */
    public void setDescripcionUsuario(java.lang.String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }


    /**
     * Gets the id value for this ContactoAsociadoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ContactoAsociadoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContactoAsociadoVO)) return false;
        ContactoAsociadoVO other = (ContactoAsociadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usuarioContacto==null && other.getUsuarioContacto()==null) || 
             (this.usuarioContacto!=null &&
              this.usuarioContacto.equals(other.getUsuarioContacto()))) &&
            ((this.imagenUsuario==null && other.getImagenUsuario()==null) || 
             (this.imagenUsuario!=null &&
              this.imagenUsuario.equals(other.getImagenUsuario()))) &&
            ((this.esAsociadoUsuario==null && other.getEsAsociadoUsuario()==null) || 
             (this.esAsociadoUsuario!=null &&
              this.esAsociadoUsuario.equals(other.getEsAsociadoUsuario()))) &&
            ((this.descripcionUsuario==null && other.getDescripcionUsuario()==null) || 
             (this.descripcionUsuario!=null &&
              this.descripcionUsuario.equals(other.getDescripcionUsuario()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getUsuarioContacto() != null) {
            _hashCode += getUsuarioContacto().hashCode();
        }
        if (getImagenUsuario() != null) {
            _hashCode += getImagenUsuario().hashCode();
        }
        if (getEsAsociadoUsuario() != null) {
            _hashCode += getEsAsociadoUsuario().hashCode();
        }
        if (getDescripcionUsuario() != null) {
            _hashCode += getDescripcionUsuario().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContactoAsociadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "ContactoAsociadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioContacto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "usuarioContacto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagenUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "imagenUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esAsociadoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "esAsociadoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "descripcionUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
