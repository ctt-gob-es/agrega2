/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ListIdentifiersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class ListIdentifiersResponse  implements java.io.Serializable {
    private es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listIdentifiersReturn;

    public ListIdentifiersResponse() {
    }

    public ListIdentifiersResponse(
           es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listIdentifiersReturn) {
           this.listIdentifiersReturn = listIdentifiersReturn;
    }


    /**
     * Gets the listIdentifiersReturn value for this ListIdentifiersResponse.
     * 
     * @return listIdentifiersReturn
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest getListIdentifiersReturn() {
        return listIdentifiersReturn;
    }


    /**
     * Sets the listIdentifiersReturn value for this ListIdentifiersResponse.
     * 
     * @param listIdentifiersReturn
     */
    public void setListIdentifiersReturn(es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listIdentifiersReturn) {
        this.listIdentifiersReturn = listIdentifiersReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListIdentifiersResponse)) return false;
        ListIdentifiersResponse other = (ListIdentifiersResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listIdentifiersReturn==null && other.getListIdentifiersReturn()==null) || 
             (this.listIdentifiersReturn!=null &&
              this.listIdentifiersReturn.equals(other.getListIdentifiersReturn())));
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
        if (getListIdentifiersReturn() != null) {
            _hashCode += getListIdentifiersReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListIdentifiersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", ">listIdentifiersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listIdentifiersReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listIdentifiersReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoOAIRequest"));
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
