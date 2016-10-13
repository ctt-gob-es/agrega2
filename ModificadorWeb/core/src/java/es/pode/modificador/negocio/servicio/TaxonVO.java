/**
 * TaxonVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class TaxonVO  implements java.io.Serializable {
    private java.lang.String valorTax;

    private java.lang.String id;

    private java.lang.Boolean esHoja;

    private java.lang.String tipoRelacion;

    private java.lang.String vocabName;

    public TaxonVO() {
    }

    public TaxonVO(
           java.lang.String valorTax,
           java.lang.String id,
           java.lang.Boolean esHoja,
           java.lang.String tipoRelacion,
           java.lang.String vocabName) {
           this.valorTax = valorTax;
           this.id = id;
           this.esHoja = esHoja;
           this.tipoRelacion = tipoRelacion;
           this.vocabName = vocabName;
    }


    /**
     * Gets the valorTax value for this TaxonVO.
     * 
     * @return valorTax
     */
    public java.lang.String getValorTax() {
        return valorTax;
    }


    /**
     * Sets the valorTax value for this TaxonVO.
     * 
     * @param valorTax
     */
    public void setValorTax(java.lang.String valorTax) {
        this.valorTax = valorTax;
    }


    /**
     * Gets the id value for this TaxonVO.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this TaxonVO.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the esHoja value for this TaxonVO.
     * 
     * @return esHoja
     */
    public java.lang.Boolean getEsHoja() {
        return esHoja;
    }


    /**
     * Sets the esHoja value for this TaxonVO.
     * 
     * @param esHoja
     */
    public void setEsHoja(java.lang.Boolean esHoja) {
        this.esHoja = esHoja;
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
            ((this.valorTax==null && other.getValorTax()==null) || 
             (this.valorTax!=null &&
              this.valorTax.equals(other.getValorTax()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.esHoja==null && other.getEsHoja()==null) || 
             (this.esHoja!=null &&
              this.esHoja.equals(other.getEsHoja()))) &&
            ((this.tipoRelacion==null && other.getTipoRelacion()==null) || 
             (this.tipoRelacion!=null &&
              this.tipoRelacion.equals(other.getTipoRelacion()))) &&
            ((this.vocabName==null && other.getVocabName()==null) || 
             (this.vocabName!=null &&
              this.vocabName.equals(other.getVocabName())));
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
        if (getValorTax() != null) {
            _hashCode += getValorTax().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getEsHoja() != null) {
            _hashCode += getEsHoja().hashCode();
        }
        if (getTipoRelacion() != null) {
            _hashCode += getTipoRelacion().hashCode();
        }
        if (getVocabName() != null) {
            _hashCode += getVocabName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxonVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TaxonVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "valorTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esHoja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "esHoja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRelacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "tipoRelacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "vocabName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
