/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CodigoResultadoTransformacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class CodigoResultadoTransformacion implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CodigoResultadoTransformacion(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _E_001 = "E_001";
    public static final java.lang.String _E_002 = "E_002";
    public static final java.lang.String _E_003 = "E_003";
    public static final java.lang.String _E_004 = "E_004";
    public static final java.lang.String _E_005 = "E_005";
    public static final java.lang.String _E_006 = "E_006";
    public static final java.lang.String _S_001 = "S_001";
    public static final CodigoResultadoTransformacion E_001 = new CodigoResultadoTransformacion(_E_001);
    public static final CodigoResultadoTransformacion E_002 = new CodigoResultadoTransformacion(_E_002);
    public static final CodigoResultadoTransformacion E_003 = new CodigoResultadoTransformacion(_E_003);
    public static final CodigoResultadoTransformacion E_004 = new CodigoResultadoTransformacion(_E_004);
    public static final CodigoResultadoTransformacion E_005 = new CodigoResultadoTransformacion(_E_005);
    public static final CodigoResultadoTransformacion E_006 = new CodigoResultadoTransformacion(_E_006);
    public static final CodigoResultadoTransformacion S_001 = new CodigoResultadoTransformacion(_S_001);
    public java.lang.String getValue() { return _value_;}
    public static CodigoResultadoTransformacion fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CodigoResultadoTransformacion enumeration = (CodigoResultadoTransformacion)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CodigoResultadoTransformacion fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodigoResultadoTransformacion.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "CodigoResultadoTransformacion"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
