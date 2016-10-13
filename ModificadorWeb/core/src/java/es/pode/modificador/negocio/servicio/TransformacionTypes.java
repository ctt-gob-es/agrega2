/**
 * TransformacionTypes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class TransformacionTypes implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TransformacionTypes(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _SCORM2004 = "SCORM2004";
    public static final java.lang.String _SCORM2004SS = "SCORM2004SS";
    public static final java.lang.String _SCORM12 = "SCORM12";
    public static final java.lang.String _IMSCP = "IMSCP";
    public static final java.lang.String _HTML = "HTML";
    public static final java.lang.String _CONTENIDOS = "CONTENIDOS";
    public static final TransformacionTypes SCORM2004 = new TransformacionTypes(_SCORM2004);
    public static final TransformacionTypes SCORM2004SS = new TransformacionTypes(_SCORM2004SS);
    public static final TransformacionTypes SCORM12 = new TransformacionTypes(_SCORM12);
    public static final TransformacionTypes IMSCP = new TransformacionTypes(_IMSCP);
    public static final TransformacionTypes HTML = new TransformacionTypes(_HTML);
    public static final TransformacionTypes CONTENIDOS = new TransformacionTypes(_CONTENIDOS);
    public java.lang.String getValue() { return _value_;}
    public static TransformacionTypes fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TransformacionTypes enumeration = (TransformacionTypes)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static TransformacionTypes fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TransformacionTypes.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TransformacionTypes"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
