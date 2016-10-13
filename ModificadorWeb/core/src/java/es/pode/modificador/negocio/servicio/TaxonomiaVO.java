/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TaxonomiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;


/**
 * Bean que encapsula la informacion necesaria para la navegación
 *                         por arbol curricular / ETB
 */
public class TaxonomiaVO  implements java.io.Serializable {
    private java.lang.String nombreTaxonomia;

    private java.lang.String idioma;

    /* Nombre de las taxonomias disponibles (nombreTaxonomía es la
     * seleccionada de este conjunto). */
    private java.lang.String[] taxonomias;

    private es.pode.modificador.negocio.servicio.TaxonVO[] taxonPath;

    private es.pode.modificador.negocio.servicio.TaxonVO[] hijos;

    private es.pode.modificador.negocio.servicio.JerarquiaVO[] jerarquia;

    public TaxonomiaVO() {
    }

    public TaxonomiaVO(
           java.lang.String nombreTaxonomia,
           java.lang.String idioma,
           java.lang.String[] taxonomias,
           es.pode.modificador.negocio.servicio.TaxonVO[] taxonPath,
           es.pode.modificador.negocio.servicio.TaxonVO[] hijos,
           es.pode.modificador.negocio.servicio.JerarquiaVO[] jerarquia) {
           this.nombreTaxonomia = nombreTaxonomia;
           this.idioma = idioma;
           this.taxonomias = taxonomias;
           this.taxonPath = taxonPath;
           this.hijos = hijos;
           this.jerarquia = jerarquia;
    }


    /**
     * Gets the nombreTaxonomia value for this TaxonomiaVO.
     * 
     * @return nombreTaxonomia
     */
    public java.lang.String getNombreTaxonomia() {
        return nombreTaxonomia;
    }


    /**
     * Sets the nombreTaxonomia value for this TaxonomiaVO.
     * 
     * @param nombreTaxonomia
     */
    public void setNombreTaxonomia(java.lang.String nombreTaxonomia) {
        this.nombreTaxonomia = nombreTaxonomia;
    }


    /**
     * Gets the idioma value for this TaxonomiaVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this TaxonomiaVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the taxonomias value for this TaxonomiaVO.
     * 
     * @return taxonomias   * Nombre de las taxonomias disponibles (nombreTaxonomía es la
     * seleccionada de este conjunto).
     */
    public java.lang.String[] getTaxonomias() {
        return taxonomias;
    }


    /**
     * Sets the taxonomias value for this TaxonomiaVO.
     * 
     * @param taxonomias   * Nombre de las taxonomias disponibles (nombreTaxonomía es la
     * seleccionada de este conjunto).
     */
    public void setTaxonomias(java.lang.String[] taxonomias) {
        this.taxonomias = taxonomias;
    }


    /**
     * Gets the taxonPath value for this TaxonomiaVO.
     * 
     * @return taxonPath
     */
    public es.pode.modificador.negocio.servicio.TaxonVO[] getTaxonPath() {
        return taxonPath;
    }


    /**
     * Sets the taxonPath value for this TaxonomiaVO.
     * 
     * @param taxonPath
     */
    public void setTaxonPath(es.pode.modificador.negocio.servicio.TaxonVO[] taxonPath) {
        this.taxonPath = taxonPath;
    }


    /**
     * Gets the hijos value for this TaxonomiaVO.
     * 
     * @return hijos
     */
    public es.pode.modificador.negocio.servicio.TaxonVO[] getHijos() {
        return hijos;
    }


    /**
     * Sets the hijos value for this TaxonomiaVO.
     * 
     * @param hijos
     */
    public void setHijos(es.pode.modificador.negocio.servicio.TaxonVO[] hijos) {
        this.hijos = hijos;
    }


    /**
     * Gets the jerarquia value for this TaxonomiaVO.
     * 
     * @return jerarquia
     */
    public es.pode.modificador.negocio.servicio.JerarquiaVO[] getJerarquia() {
        return jerarquia;
    }


    /**
     * Sets the jerarquia value for this TaxonomiaVO.
     * 
     * @param jerarquia
     */
    public void setJerarquia(es.pode.modificador.negocio.servicio.JerarquiaVO[] jerarquia) {
        this.jerarquia = jerarquia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxonomiaVO)) return false;
        TaxonomiaVO other = (TaxonomiaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreTaxonomia==null && other.getNombreTaxonomia()==null) || 
             (this.nombreTaxonomia!=null &&
              this.nombreTaxonomia.equals(other.getNombreTaxonomia()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.taxonomias==null && other.getTaxonomias()==null) || 
             (this.taxonomias!=null &&
              java.util.Arrays.equals(this.taxonomias, other.getTaxonomias()))) &&
            ((this.taxonPath==null && other.getTaxonPath()==null) || 
             (this.taxonPath!=null &&
              java.util.Arrays.equals(this.taxonPath, other.getTaxonPath()))) &&
            ((this.hijos==null && other.getHijos()==null) || 
             (this.hijos!=null &&
              java.util.Arrays.equals(this.hijos, other.getHijos()))) &&
            ((this.jerarquia==null && other.getJerarquia()==null) || 
             (this.jerarquia!=null &&
              java.util.Arrays.equals(this.jerarquia, other.getJerarquia())));
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
        if (getNombreTaxonomia() != null) {
            _hashCode += getNombreTaxonomia().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
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
        if (getTaxonPath() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxonPath());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxonPath(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHijos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHijos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHijos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getJerarquia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJerarquia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJerarquia(), i);
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
        new org.apache.axis.description.TypeDesc(TaxonomiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TaxonomiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTaxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "nombreTaxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "taxonomias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "taxonPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TaxonVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hijos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "hijos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TaxonVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jerarquia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "jerarquia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "JerarquiaVO"));
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
