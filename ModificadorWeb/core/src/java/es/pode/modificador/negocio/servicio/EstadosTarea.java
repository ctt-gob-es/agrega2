/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * EstadosTarea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class EstadosTarea implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EstadosTarea(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _FINALIZADA = "FINALIZADA";
    public static final java.lang.String _ERROR = "ERROR";
    public static final java.lang.String _RESTAURADA = "RESTAURADA";
    public static final java.lang.String _CONFIGURADA = "CONFIGURADA";
    public static final java.lang.String _WARNING = "WARNING";
    public static final java.lang.String _RUNNING = "RUNNING";
    public static final java.lang.String _PENDIENTE = "PENDIENTE";
    public static final EstadosTarea FINALIZADA = new EstadosTarea(_FINALIZADA);
    public static final EstadosTarea ERROR = new EstadosTarea(_ERROR);
    public static final EstadosTarea RESTAURADA = new EstadosTarea(_RESTAURADA);
    public static final EstadosTarea CONFIGURADA = new EstadosTarea(_CONFIGURADA);
    public static final EstadosTarea WARNING = new EstadosTarea(_WARNING);
    public static final EstadosTarea RUNNING = new EstadosTarea(_RUNNING);
    public static final EstadosTarea PENDIENTE = new EstadosTarea(_PENDIENTE);
    public java.lang.String getValue() { return _value_;}
    public static EstadosTarea fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EstadosTarea enumeration = (EstadosTarea)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EstadosTarea fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EstadosTarea.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "EstadosTarea"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
