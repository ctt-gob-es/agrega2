/**
 * CBTaxonVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * Representa un taxon de una taxonomia.
 */
public class CBTaxonVO  implements java.io.Serializable {
    /* Identificador del taxon dentro de la taxonomia. */
    private java.lang.String id;

    /* Nombre del taxon en la taxonomia. */
    private java.lang.String valorTax;

    /* Atributo para indicar si el taxon tiene descendientes dentro
     * la
     *                                 taxonomia o no. */
    private java.lang.Boolean esHoja;

    public CBTaxonVO() {
    }

    public CBTaxonVO(
           java.lang.String id,
           java.lang.String valorTax,
           java.lang.Boolean esHoja) {
           this.id = id;
           this.valorTax = valorTax;
           this.esHoja = esHoja;
    }


    /**
     * Gets the id value for this CBTaxonVO.
     * 
     * @return id   * Identificador del taxon dentro de la taxonomia.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this CBTaxonVO.
     * 
     * @param id   * Identificador del taxon dentro de la taxonomia.
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the valorTax value for this CBTaxonVO.
     * 
     * @return valorTax   * Nombre del taxon en la taxonomia.
     */
    public java.lang.String getValorTax() {
        return valorTax;
    }


    /**
     * Sets the valorTax value for this CBTaxonVO.
     * 
     * @param valorTax   * Nombre del taxon en la taxonomia.
     */
    public void setValorTax(java.lang.String valorTax) {
        this.valorTax = valorTax;
    }


    /**
     * Gets the esHoja value for this CBTaxonVO.
     * 
     * @return esHoja   * Atributo para indicar si el taxon tiene descendientes dentro
     * la
     *                                 taxonomia o no.
     */
    public java.lang.Boolean getEsHoja() {
        return esHoja;
    }


    /**
     * Sets the esHoja value for this CBTaxonVO.
     * 
     * @param esHoja   * Atributo para indicar si el taxon tiene descendientes dentro
     * la
     *                                 taxonomia o no.
     */
    public void setEsHoja(java.lang.Boolean esHoja) {
        this.esHoja = esHoja;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CBTaxonVO)) return false;
        CBTaxonVO other = (CBTaxonVO) obj;
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
            ((this.valorTax==null && other.getValorTax()==null) || 
             (this.valorTax!=null &&
              this.valorTax.equals(other.getValorTax()))) &&
            ((this.esHoja==null && other.getEsHoja()==null) || 
             (this.esHoja!=null &&
              this.esHoja.equals(other.getEsHoja())));
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
        if (getValorTax() != null) {
            _hashCode += getValorTax().hashCode();
        }
        if (getEsHoja() != null) {
            _hashCode += getEsHoja().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CBTaxonVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "CBTaxonVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "valorTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esHoja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "esHoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
