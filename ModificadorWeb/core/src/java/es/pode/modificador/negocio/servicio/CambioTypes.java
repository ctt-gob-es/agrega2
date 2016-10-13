/**
 * CambioTypes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class CambioTypes implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CambioTypes(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _validation = "validation";
    public static final java.lang.String _modification = "modification";
    public static final java.lang.String _addition = "addition";
    public static final java.lang.String _check = "check";
    public static final java.lang.String _removal = "removal";
    public static final java.lang.String _export = "export";
    public static final java.lang.String _transformation = "transformation";
    public static final java.lang.String _metadata = "metadata";
    public static final CambioTypes validation = new CambioTypes(_validation);
    public static final CambioTypes modification = new CambioTypes(_modification);
    public static final CambioTypes addition = new CambioTypes(_addition);
    public static final CambioTypes check = new CambioTypes(_check);
    public static final CambioTypes removal = new CambioTypes(_removal);
    public static final CambioTypes export = new CambioTypes(_export);
    public static final CambioTypes transformation = new CambioTypes(_transformation);
    public static final CambioTypes metadata = new CambioTypes(_metadata);
    public java.lang.String getValue() { return _value_;}
    public static CambioTypes fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CambioTypes enumeration = (CambioTypes)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CambioTypes fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CambioTypes.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "CambioTypes"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
