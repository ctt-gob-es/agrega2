/**
 * NivelAgregacionContenido.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Value Object con la información de los odes que tienen asociado
 * cada nivel de agregación
 */
public class NivelAgregacionContenido  implements java.io.Serializable {
    /* Identificador del nivel de agregación */
    private java.lang.String id;

    /* Número de odes que tienen asociado ese nivel de agregación */
    private java.lang.Integer numDoc;

    public NivelAgregacionContenido() {
    }

    public NivelAgregacionContenido(
           java.lang.String id,
           java.lang.Integer numDoc) {
           this.id = id;
           this.numDoc = numDoc;
    }


    /**
     * Gets the id value for this NivelAgregacionContenido.
     * 
     * @return id   * Identificador del nivel de agregación
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this NivelAgregacionContenido.
     * 
     * @param id   * Identificador del nivel de agregación
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the numDoc value for this NivelAgregacionContenido.
     * 
     * @return numDoc   * Número de odes que tienen asociado ese nivel de agregación
     */
    public java.lang.Integer getNumDoc() {
        return numDoc;
    }


    /**
     * Sets the numDoc value for this NivelAgregacionContenido.
     * 
     * @param numDoc   * Número de odes que tienen asociado ese nivel de agregación
     */
    public void setNumDoc(java.lang.Integer numDoc) {
        this.numDoc = numDoc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NivelAgregacionContenido)) return false;
        NivelAgregacionContenido other = (NivelAgregacionContenido) obj;
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
            ((this.numDoc==null && other.getNumDoc()==null) || 
             (this.numDoc!=null &&
              this.numDoc.equals(other.getNumDoc())));
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
        if (getNumDoc() != null) {
            _hashCode += getNumDoc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NivelAgregacionContenido.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "NivelAgregacionContenido"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "numDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
