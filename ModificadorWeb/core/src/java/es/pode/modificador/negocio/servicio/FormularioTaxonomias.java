/**
 * FormularioTaxonomias.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class FormularioTaxonomias  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.LabelValueVO[] idiomas;

    private es.pode.modificador.negocio.servicio.LabelValueVO[] taxonomias;

    private es.pode.modificador.negocio.servicio.LabelValueVO[] proposito;

    public FormularioTaxonomias() {
    }

    public FormularioTaxonomias(
           es.pode.modificador.negocio.servicio.LabelValueVO[] idiomas,
           es.pode.modificador.negocio.servicio.LabelValueVO[] taxonomias,
           es.pode.modificador.negocio.servicio.LabelValueVO[] proposito) {
           this.idiomas = idiomas;
           this.taxonomias = taxonomias;
           this.proposito = proposito;
    }


    /**
     * Gets the idiomas value for this FormularioTaxonomias.
     * 
     * @return idiomas
     */
    public es.pode.modificador.negocio.servicio.LabelValueVO[] getIdiomas() {
        return idiomas;
    }


    /**
     * Sets the idiomas value for this FormularioTaxonomias.
     * 
     * @param idiomas
     */
    public void setIdiomas(es.pode.modificador.negocio.servicio.LabelValueVO[] idiomas) {
        this.idiomas = idiomas;
    }


    /**
     * Gets the taxonomias value for this FormularioTaxonomias.
     * 
     * @return taxonomias
     */
    public es.pode.modificador.negocio.servicio.LabelValueVO[] getTaxonomias() {
        return taxonomias;
    }


    /**
     * Sets the taxonomias value for this FormularioTaxonomias.
     * 
     * @param taxonomias
     */
    public void setTaxonomias(es.pode.modificador.negocio.servicio.LabelValueVO[] taxonomias) {
        this.taxonomias = taxonomias;
    }


    /**
     * Gets the proposito value for this FormularioTaxonomias.
     * 
     * @return proposito
     */
    public es.pode.modificador.negocio.servicio.LabelValueVO[] getProposito() {
        return proposito;
    }


    /**
     * Sets the proposito value for this FormularioTaxonomias.
     * 
     * @param proposito
     */
    public void setProposito(es.pode.modificador.negocio.servicio.LabelValueVO[] proposito) {
        this.proposito = proposito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FormularioTaxonomias)) return false;
        FormularioTaxonomias other = (FormularioTaxonomias) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idiomas==null && other.getIdiomas()==null) || 
             (this.idiomas!=null &&
              java.util.Arrays.equals(this.idiomas, other.getIdiomas()))) &&
            ((this.taxonomias==null && other.getTaxonomias()==null) || 
             (this.taxonomias!=null &&
              java.util.Arrays.equals(this.taxonomias, other.getTaxonomias()))) &&
            ((this.proposito==null && other.getProposito()==null) || 
             (this.proposito!=null &&
              java.util.Arrays.equals(this.proposito, other.getProposito())));
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
        if (getIdiomas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdiomas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdiomas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTaxonomias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxonomias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxonomias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProposito() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProposito());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProposito(), i);
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
        new org.apache.axis.description.TypeDesc(FormularioTaxonomias.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "FormularioTaxonomias"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idiomas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "LabelValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "taxonomias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "LabelValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proposito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "proposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "LabelValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
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
