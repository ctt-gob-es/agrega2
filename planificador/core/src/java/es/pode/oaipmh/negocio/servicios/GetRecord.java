/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * GetRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class GetRecord  implements java.io.Serializable {
    private es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada;

    public GetRecord() {
    }

    public GetRecord(
           es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) {
           this.parametroLlamada = parametroLlamada;
    }


    /**
     * Gets the parametroLlamada value for this GetRecord.
     * 
     * @return parametroLlamada
     */
    public es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO getParametroLlamada() {
        return parametroLlamada;
    }


    /**
     * Sets the parametroLlamada value for this GetRecord.
     * 
     * @param parametroLlamada
     */
    public void setParametroLlamada(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) {
        this.parametroLlamada = parametroLlamada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRecord)) return false;
        GetRecord other = (GetRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametroLlamada==null && other.getParametroLlamada()==null) || 
             (this.parametroLlamada!=null &&
              this.parametroLlamada.equals(other.getParametroLlamada())));
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
        if (getParametroLlamada() != null) {
            _hashCode += getParametroLlamada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", ">getRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametroLlamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "parametroLlamada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ParametrosOaiPmhVO"));
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
