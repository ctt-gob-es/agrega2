/**
 * TaxonVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class TaxonVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.IdVO id;

    private es.pode.catalogacion.negocio.servicios.EntryVO entry;

    public TaxonVO() {
    }

    public TaxonVO(
           es.pode.catalogacion.negocio.servicios.IdVO id,
           es.pode.catalogacion.negocio.servicios.EntryVO entry) {
           this.id = id;
           this.entry = entry;
    }


    /**
     * Gets the id value for this TaxonVO.
     * 
     * @return id
     */
    public es.pode.catalogacion.negocio.servicios.IdVO getId() {
        return id;
    }


    /**
     * Sets the id value for this TaxonVO.
     * 
     * @param id
     */
    public void setId(es.pode.catalogacion.negocio.servicios.IdVO id) {
        this.id = id;
    }


    /**
     * Gets the entry value for this TaxonVO.
     * 
     * @return entry
     */
    public es.pode.catalogacion.negocio.servicios.EntryVO getEntry() {
        return entry;
    }


    /**
     * Sets the entry value for this TaxonVO.
     * 
     * @param entry
     */
    public void setEntry(es.pode.catalogacion.negocio.servicios.EntryVO entry) {
        this.entry = entry;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxonVO)) return false;
        TaxonVO other = (TaxonVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.entry==null && other.getEntry()==null) || 
             (this.entry!=null &&
              this.entry.equals(other.getEntry())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getEntry() != null) {
            _hashCode += getEntry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxonVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TaxonVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "entry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntryVO"));
        elemField.setNillable(true);
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
