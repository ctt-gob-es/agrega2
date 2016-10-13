/**
 * TaxonVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;


/**
 * Representa un taxon particular dentro de una taxonomia. Esta
 *                         compuesto por un identificador, un valor y
 * un campo para
 *                         identificar si el taxon es hoja dentro de
 * la taxonomia.
 */
public class TaxonVO  implements java.io.Serializable {
    /* Identificador del taxon dentro de la taxonomia a la que
     *                                 pertenece. */
    private java.lang.String id;

    /* Valor del taxon en la taxonomia a la que pertenece. */
    private java.lang.String valorTax;

    /* Flag que indica si el taxon tiene descendientes en la taxonomia
     * a la que pertenece. */
    private java.lang.Boolean esHoja;

    private java.lang.String vocabName;

    private java.lang.String tipoRelacion;

    public TaxonVO() {
    }

    public TaxonVO(
           java.lang.String id,
           java.lang.String valorTax,
           java.lang.Boolean esHoja,
           java.lang.String vocabName,
           java.lang.String tipoRelacion) {
           this.id = id;
           this.valorTax = valorTax;
           this.esHoja = esHoja;
           this.vocabName = vocabName;
           this.tipoRelacion = tipoRelacion;
    }


    /**
     * Gets the id value for this TaxonVO.
     * 
     * @return id   * Identificador del taxon dentro de la taxonomia a la que
     *                                 pertenece.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this TaxonVO.
     * 
     * @param id   * Identificador del taxon dentro de la taxonomia a la que
     *                                 pertenece.
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the valorTax value for this TaxonVO.
     * 
     * @return valorTax   * Valor del taxon en la taxonomia a la que pertenece.
     */
    public java.lang.String getValorTax() {
        return valorTax;
    }


    /**
     * Sets the valorTax value for this TaxonVO.
     * 
     * @param valorTax   * Valor del taxon en la taxonomia a la que pertenece.
     */
    public void setValorTax(java.lang.String valorTax) {
        this.valorTax = valorTax;
    }


    /**
     * Gets the esHoja value for this TaxonVO.
     * 
     * @return esHoja   * Flag que indica si el taxon tiene descendientes en la taxonomia
     * a la que pertenece.
     */
    public java.lang.Boolean getEsHoja() {
        return esHoja;
    }


    /**
     * Sets the esHoja value for this TaxonVO.
     * 
     * @param esHoja   * Flag que indica si el taxon tiene descendientes en la taxonomia
     * a la que pertenece.
     */
    public void setEsHoja(java.lang.Boolean esHoja) {
        this.esHoja = esHoja;
    }


    /**
     * Gets the vocabName value for this TaxonVO.
     * 
     * @return vocabName
     */
    public java.lang.String getVocabName() {
        return vocabName;
    }


    /**
     * Sets the vocabName value for this TaxonVO.
     * 
     * @param vocabName
     */
    public void setVocabName(java.lang.String vocabName) {
        this.vocabName = vocabName;
    }


    /**
     * Gets the tipoRelacion value for this TaxonVO.
     * 
     * @return tipoRelacion
     */
    public java.lang.String getTipoRelacion() {
        return tipoRelacion;
    }


    /**
     * Sets the tipoRelacion value for this TaxonVO.
     * 
     * @param tipoRelacion
     */
    public void setTipoRelacion(java.lang.String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
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
            ((this.valorTax==null && other.getValorTax()==null) || 
             (this.valorTax!=null &&
              this.valorTax.equals(other.getValorTax()))) &&
            ((this.esHoja==null && other.getEsHoja()==null) || 
             (this.esHoja!=null &&
              this.esHoja.equals(other.getEsHoja()))) &&
            ((this.vocabName==null && other.getVocabName()==null) || 
             (this.vocabName!=null &&
              this.vocabName.equals(other.getVocabName()))) &&
            ((this.tipoRelacion==null && other.getTipoRelacion()==null) || 
             (this.tipoRelacion!=null &&
              this.tipoRelacion.equals(other.getTipoRelacion())));
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
        if (getVocabName() != null) {
            _hashCode += getVocabName().hashCode();
        }
        if (getTipoRelacion() != null) {
            _hashCode += getTipoRelacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxonVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "valorTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esHoja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "esHoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "vocabName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRelacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "tipoRelacion"));
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
