/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CorreoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;


/**
 * Value Object que almacena datos comunes a los distintos tipos de
 * correos que se envían en la plataforma.
 */
public class CorreoVO  implements java.io.Serializable {
    /* Destinatarios a los que se envía el correo */
    private java.lang.String[] to;

    /* Usuario que envía el correo */
    private java.lang.String from;

    /* Idioma en el que se va a enviar el correo */
    private java.lang.String idiomaCorreo;

    public CorreoVO() {
    }

    public CorreoVO(
           java.lang.String[] to,
           java.lang.String from,
           java.lang.String idiomaCorreo) {
           this.to = to;
           this.from = from;
           this.idiomaCorreo = idiomaCorreo;
    }


    /**
     * Gets the to value for this CorreoVO.
     * 
     * @return to   * Destinatarios a los que se envía el correo
     */
    public java.lang.String[] getTo() {
        return to;
    }


    /**
     * Sets the to value for this CorreoVO.
     * 
     * @param to   * Destinatarios a los que se envía el correo
     */
    public void setTo(java.lang.String[] to) {
        this.to = to;
    }


    /**
     * Gets the from value for this CorreoVO.
     * 
     * @return from   * Usuario que envía el correo
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this CorreoVO.
     * 
     * @param from   * Usuario que envía el correo
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the idiomaCorreo value for this CorreoVO.
     * 
     * @return idiomaCorreo   * Idioma en el que se va a enviar el correo
     */
    public java.lang.String getIdiomaCorreo() {
        return idiomaCorreo;
    }


    /**
     * Sets the idiomaCorreo value for this CorreoVO.
     * 
     * @param idiomaCorreo   * Idioma en el que se va a enviar el correo
     */
    public void setIdiomaCorreo(java.lang.String idiomaCorreo) {
        this.idiomaCorreo = idiomaCorreo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CorreoVO)) return false;
        CorreoVO other = (CorreoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              java.util.Arrays.equals(this.to, other.getTo()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.idiomaCorreo==null && other.getIdiomaCorreo()==null) || 
             (this.idiomaCorreo!=null &&
              this.idiomaCorreo.equals(other.getIdiomaCorreo())));
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
        if (getTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getIdiomaCorreo() != null) {
            _hashCode += getIdiomaCorreo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CorreoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaCorreo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "idiomaCorreo"));
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
