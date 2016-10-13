/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TaxonConRutaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class TaxonConRutaVO  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String valorTax;

    private es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonPath;

    public TaxonConRutaVO() {
    }

    public TaxonConRutaVO(
           java.lang.String id,
           java.lang.String valorTax,
           es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonPath) {
           this.id = id;
           this.valorTax = valorTax;
           this.taxonPath = taxonPath;
    }


    /**
     * Gets the id value for this TaxonConRutaVO.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this TaxonConRutaVO.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the valorTax value for this TaxonConRutaVO.
     * 
     * @return valorTax
     */
    public java.lang.String getValorTax() {
        return valorTax;
    }


    /**
     * Sets the valorTax value for this TaxonConRutaVO.
     * 
     * @param valorTax
     */
    public void setValorTax(java.lang.String valorTax) {
        this.valorTax = valorTax;
    }


    /**
     * Gets the taxonPath value for this TaxonConRutaVO.
     * 
     * @return taxonPath
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] getTaxonPath() {
        return taxonPath;
    }


    /**
     * Sets the taxonPath value for this TaxonConRutaVO.
     * 
     * @param taxonPath
     */
    public void setTaxonPath(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonPath) {
        this.taxonPath = taxonPath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxonConRutaVO)) return false;
        TaxonConRutaVO other = (TaxonConRutaVO) obj;
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
            ((this.taxonPath==null && other.getTaxonPath()==null) || 
             (this.taxonPath!=null &&
              java.util.Arrays.equals(this.taxonPath, other.getTaxonPath())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxonConRutaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonConRutaVO"));
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
        elemField.setFieldName("taxonPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "taxonPath"));
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
