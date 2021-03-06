/**
 * CoberturaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.auditoria;


/**
 * Esta clase alberga toda la informacion que hace falta para saber
 * la cobertura de un identificador dentro del repositorio.
 */
public class CoberturaVO  implements java.io.Serializable {
    /* Identificador del atributo del que se quiere saber la cobertura. */
    private java.lang.String id;

    /* Numero de documentos existentes en el repositorio que cubren
     * este identificador. */
    private java.lang.Integer numDoc;

    public CoberturaVO() {
    }

    public CoberturaVO(
           java.lang.String id,
           java.lang.Integer numDoc) {
           this.id = id;
           this.numDoc = numDoc;
    }


    /**
     * Gets the id value for this CoberturaVO.
     * 
     * @return id   * Identificador del atributo del que se quiere saber la cobertura.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this CoberturaVO.
     * 
     * @param id   * Identificador del atributo del que se quiere saber la cobertura.
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the numDoc value for this CoberturaVO.
     * 
     * @return numDoc   * Numero de documentos existentes en el repositorio que cubren
     * este identificador.
     */
    public java.lang.Integer getNumDoc() {
        return numDoc;
    }


    /**
     * Sets the numDoc value for this CoberturaVO.
     * 
     * @param numDoc   * Numero de documentos existentes en el repositorio que cubren
     * este identificador.
     */
    public void setNumDoc(java.lang.Integer numDoc) {
        this.numDoc = numDoc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CoberturaVO)) return false;
        CoberturaVO other = (CoberturaVO) obj;
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
        new org.apache.axis.description.TypeDesc(CoberturaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "CoberturaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "numDoc"));
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
