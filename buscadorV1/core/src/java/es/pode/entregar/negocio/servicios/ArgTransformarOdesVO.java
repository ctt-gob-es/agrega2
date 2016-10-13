/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ArgTransformarOdesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class ArgTransformarOdesVO  implements java.io.Serializable {
    private java.lang.String pathOrigen;

    private java.lang.String pathDestino;

    private es.pode.entregar.negocio.servicios.TipoPIFCst tipoPifDestino;

    public ArgTransformarOdesVO() {
    }

    public ArgTransformarOdesVO(
           java.lang.String pathOrigen,
           java.lang.String pathDestino,
           es.pode.entregar.negocio.servicios.TipoPIFCst tipoPifDestino) {
           this.pathOrigen = pathOrigen;
           this.pathDestino = pathDestino;
           this.tipoPifDestino = tipoPifDestino;
    }


    /**
     * Gets the pathOrigen value for this ArgTransformarOdesVO.
     * 
     * @return pathOrigen
     */
    public java.lang.String getPathOrigen() {
        return pathOrigen;
    }


    /**
     * Sets the pathOrigen value for this ArgTransformarOdesVO.
     * 
     * @param pathOrigen
     */
    public void setPathOrigen(java.lang.String pathOrigen) {
        this.pathOrigen = pathOrigen;
    }


    /**
     * Gets the pathDestino value for this ArgTransformarOdesVO.
     * 
     * @return pathDestino
     */
    public java.lang.String getPathDestino() {
        return pathDestino;
    }


    /**
     * Sets the pathDestino value for this ArgTransformarOdesVO.
     * 
     * @param pathDestino
     */
    public void setPathDestino(java.lang.String pathDestino) {
        this.pathDestino = pathDestino;
    }


    /**
     * Gets the tipoPifDestino value for this ArgTransformarOdesVO.
     * 
     * @return tipoPifDestino
     */
    public es.pode.entregar.negocio.servicios.TipoPIFCst getTipoPifDestino() {
        return tipoPifDestino;
    }


    /**
     * Sets the tipoPifDestino value for this ArgTransformarOdesVO.
     * 
     * @param tipoPifDestino
     */
    public void setTipoPifDestino(es.pode.entregar.negocio.servicios.TipoPIFCst tipoPifDestino) {
        this.tipoPifDestino = tipoPifDestino;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArgTransformarOdesVO)) return false;
        ArgTransformarOdesVO other = (ArgTransformarOdesVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pathOrigen==null && other.getPathOrigen()==null) || 
             (this.pathOrigen!=null &&
              this.pathOrigen.equals(other.getPathOrigen()))) &&
            ((this.pathDestino==null && other.getPathDestino()==null) || 
             (this.pathDestino!=null &&
              this.pathDestino.equals(other.getPathDestino()))) &&
            ((this.tipoPifDestino==null && other.getTipoPifDestino()==null) || 
             (this.tipoPifDestino!=null &&
              this.tipoPifDestino.equals(other.getTipoPifDestino())));
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
        if (getPathOrigen() != null) {
            _hashCode += getPathOrigen().hashCode();
        }
        if (getPathDestino() != null) {
            _hashCode += getPathDestino().hashCode();
        }
        if (getTipoPifDestino() != null) {
            _hashCode += getTipoPifDestino().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArgTransformarOdesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ArgTransformarOdesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "pathOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "pathDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPifDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "tipoPifDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "TipoPIFCst"));
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
