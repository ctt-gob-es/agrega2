/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParamValidaVdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public class ParamValidaVdexVO  implements java.io.Serializable {

	private static final long serialVersionUID = 574978099021858136L;
	private javax.activation.DataHandler fichero;

    public ParamValidaVdexVO() {
    }

    public ParamValidaVdexVO(
           javax.activation.DataHandler fichero) {
           this.fichero = fichero;
    }


    /**
     * Gets the fichero value for this ParamValidaVdexVO.
     * 
     * @return fichero
     */
    public javax.activation.DataHandler getFichero() {
        return fichero;
    }


    /**
     * Sets the fichero value for this ParamValidaVdexVO.
     * 
     * @param fichero
     */
    public void setFichero(javax.activation.DataHandler fichero) {
        this.fichero = fichero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamValidaVdexVO)) return false;
        ParamValidaVdexVO other = (ParamValidaVdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fichero==null && other.getFichero()==null) || 
             (this.fichero!=null &&
              this.fichero.equals(other.getFichero())));
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
        if (getFichero() != null) {
            _hashCode += getFichero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamValidaVdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ParamValidaVdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fichero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "fichero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
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
