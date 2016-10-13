/**
 * EstructuraVdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;


/**
 * Value Object que describe una taxonomía. indica el vocabName,
 *                         vocabIdentifier, idioma y un listado con 
 * los taxones del primer
 *                         nivel de los existentes en la taxonomía.
 */
public class EstructuraVdexVO  implements java.io.Serializable {
    private java.lang.String vocabName;

    private java.lang.String vocabIdentifier;

    private java.lang.String idioma;

    private es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxones;

    public EstructuraVdexVO() {
    }

    public EstructuraVdexVO(
           java.lang.String vocabName,
           java.lang.String vocabIdentifier,
           java.lang.String idioma,
           es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxones) {
           this.vocabName = vocabName;
           this.vocabIdentifier = vocabIdentifier;
           this.idioma = idioma;
           this.taxones = taxones;
    }


    /**
     * Gets the vocabName value for this EstructuraVdexVO.
     * 
     * @return vocabName
     */
    public java.lang.String getVocabName() {
        return vocabName;
    }


    /**
     * Sets the vocabName value for this EstructuraVdexVO.
     * 
     * @param vocabName
     */
    public void setVocabName(java.lang.String vocabName) {
        this.vocabName = vocabName;
    }


    /**
     * Gets the vocabIdentifier value for this EstructuraVdexVO.
     * 
     * @return vocabIdentifier
     */
    public java.lang.String getVocabIdentifier() {
        return vocabIdentifier;
    }


    /**
     * Sets the vocabIdentifier value for this EstructuraVdexVO.
     * 
     * @param vocabIdentifier
     */
    public void setVocabIdentifier(java.lang.String vocabIdentifier) {
        this.vocabIdentifier = vocabIdentifier;
    }


    /**
     * Gets the idioma value for this EstructuraVdexVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this EstructuraVdexVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the taxones value for this EstructuraVdexVO.
     * 
     * @return taxones
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] getTaxones() {
        return taxones;
    }


    /**
     * Sets the taxones value for this EstructuraVdexVO.
     * 
     * @param taxones
     */
    public void setTaxones(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxones) {
        this.taxones = taxones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstructuraVdexVO)) return false;
        EstructuraVdexVO other = (EstructuraVdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.vocabName==null && other.getVocabName()==null) || 
             (this.vocabName!=null &&
              this.vocabName.equals(other.getVocabName()))) &&
            ((this.vocabIdentifier==null && other.getVocabIdentifier()==null) || 
             (this.vocabIdentifier!=null &&
              this.vocabIdentifier.equals(other.getVocabIdentifier()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.taxones==null && other.getTaxones()==null) || 
             (this.taxones!=null &&
              java.util.Arrays.equals(this.taxones, other.getTaxones())));
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
        if (getVocabName() != null) {
            _hashCode += getVocabName().hashCode();
        }
        if (getVocabIdentifier() != null) {
            _hashCode += getVocabIdentifier().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getTaxones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstructuraVdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "EstructuraVdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "vocabName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "vocabIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "taxones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
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
