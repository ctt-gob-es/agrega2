/**
 * MetadataVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public class MetadataVO  implements java.io.Serializable {
    private java.lang.String schema;

    private java.lang.String schemaVersion;

    public MetadataVO() {
    }

    public MetadataVO(
           java.lang.String schema,
           java.lang.String schemaVersion) {
           this.schema = schema;
           this.schemaVersion = schemaVersion;
    }


    /**
     * Gets the schema value for this MetadataVO.
     * 
     * @return schema
     */
    public java.lang.String getSchema() {
        return schema;
    }


    /**
     * Sets the schema value for this MetadataVO.
     * 
     * @param schema
     */
    public void setSchema(java.lang.String schema) {
        this.schema = schema;
    }


    /**
     * Gets the schemaVersion value for this MetadataVO.
     * 
     * @return schemaVersion
     */
    public java.lang.String getSchemaVersion() {
        return schemaVersion;
    }


    /**
     * Sets the schemaVersion value for this MetadataVO.
     * 
     * @param schemaVersion
     */
    public void setSchemaVersion(java.lang.String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MetadataVO)) return false;
        MetadataVO other = (MetadataVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.schema==null && other.getSchema()==null) || 
             (this.schema!=null &&
              this.schema.equals(other.getSchema()))) &&
            ((this.schemaVersion==null && other.getSchemaVersion()==null) || 
             (this.schemaVersion!=null &&
              this.schemaVersion.equals(other.getSchemaVersion())));
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
        if (getSchema() != null) {
            _hashCode += getSchema().hashCode();
        }
        if (getSchemaVersion() != null) {
            _hashCode += getSchemaVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MetadataVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "MetadataVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "schema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schemaVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "schemaVersion"));
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
