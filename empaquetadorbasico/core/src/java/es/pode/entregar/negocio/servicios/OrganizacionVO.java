/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * OrganizacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class OrganizacionVO  implements java.io.Serializable {
    private java.lang.String idOrg;

    private java.lang.String tituloOrg;

    private es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaOrg;

    private java.lang.Boolean clicable;

    private java.lang.String idPadre;

    private es.pode.entregar.negocio.servicios.ItemVO[] items;

    public OrganizacionVO() {
    }

    public OrganizacionVO(
           java.lang.String idOrg,
           java.lang.String tituloOrg,
           es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaOrg,
           java.lang.Boolean clicable,
           java.lang.String idPadre,
           es.pode.entregar.negocio.servicios.ItemVO[] items) {
           this.idOrg = idOrg;
           this.tituloOrg = tituloOrg;
           this.secuenciaOrg = secuenciaOrg;
           this.clicable = clicable;
           this.idPadre = idPadre;
           this.items = items;
    }


    /**
     * Gets the idOrg value for this OrganizacionVO.
     * 
     * @return idOrg
     */
    public java.lang.String getIdOrg() {
        return idOrg;
    }


    /**
     * Sets the idOrg value for this OrganizacionVO.
     * 
     * @param idOrg
     */
    public void setIdOrg(java.lang.String idOrg) {
        this.idOrg = idOrg;
    }


    /**
     * Gets the tituloOrg value for this OrganizacionVO.
     * 
     * @return tituloOrg
     */
    public java.lang.String getTituloOrg() {
        return tituloOrg;
    }


    /**
     * Sets the tituloOrg value for this OrganizacionVO.
     * 
     * @param tituloOrg
     */
    public void setTituloOrg(java.lang.String tituloOrg) {
        this.tituloOrg = tituloOrg;
    }


    /**
     * Gets the secuenciaOrg value for this OrganizacionVO.
     * 
     * @return secuenciaOrg
     */
    public es.pode.entregar.negocio.servicios.SecuenciaVO getSecuenciaOrg() {
        return secuenciaOrg;
    }


    /**
     * Sets the secuenciaOrg value for this OrganizacionVO.
     * 
     * @param secuenciaOrg
     */
    public void setSecuenciaOrg(es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaOrg) {
        this.secuenciaOrg = secuenciaOrg;
    }


    /**
     * Gets the clicable value for this OrganizacionVO.
     * 
     * @return clicable
     */
    public java.lang.Boolean getClicable() {
        return clicable;
    }


    /**
     * Sets the clicable value for this OrganizacionVO.
     * 
     * @param clicable
     */
    public void setClicable(java.lang.Boolean clicable) {
        this.clicable = clicable;
    }


    /**
     * Gets the idPadre value for this OrganizacionVO.
     * 
     * @return idPadre
     */
    public java.lang.String getIdPadre() {
        return idPadre;
    }


    /**
     * Sets the idPadre value for this OrganizacionVO.
     * 
     * @param idPadre
     */
    public void setIdPadre(java.lang.String idPadre) {
        this.idPadre = idPadre;
    }


    /**
     * Gets the items value for this OrganizacionVO.
     * 
     * @return items
     */
    public es.pode.entregar.negocio.servicios.ItemVO[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this OrganizacionVO.
     * 
     * @param items
     */
    public void setItems(es.pode.entregar.negocio.servicios.ItemVO[] items) {
        this.items = items;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrganizacionVO)) return false;
        OrganizacionVO other = (OrganizacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idOrg==null && other.getIdOrg()==null) || 
             (this.idOrg!=null &&
              this.idOrg.equals(other.getIdOrg()))) &&
            ((this.tituloOrg==null && other.getTituloOrg()==null) || 
             (this.tituloOrg!=null &&
              this.tituloOrg.equals(other.getTituloOrg()))) &&
            ((this.secuenciaOrg==null && other.getSecuenciaOrg()==null) || 
             (this.secuenciaOrg!=null &&
              this.secuenciaOrg.equals(other.getSecuenciaOrg()))) &&
            ((this.clicable==null && other.getClicable()==null) || 
             (this.clicable!=null &&
              this.clicable.equals(other.getClicable()))) &&
            ((this.idPadre==null && other.getIdPadre()==null) || 
             (this.idPadre!=null &&
              this.idPadre.equals(other.getIdPadre()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems())));
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
        if (getIdOrg() != null) {
            _hashCode += getIdOrg().hashCode();
        }
        if (getTituloOrg() != null) {
            _hashCode += getTituloOrg().hashCode();
        }
        if (getSecuenciaOrg() != null) {
            _hashCode += getSecuenciaOrg().hashCode();
        }
        if (getClicable() != null) {
            _hashCode += getClicable().hashCode();
        }
        if (getIdPadre() != null) {
            _hashCode += getIdPadre().hashCode();
        }
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
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
        new org.apache.axis.description.TypeDesc(OrganizacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "OrganizacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "tituloOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuenciaOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "secuenciaOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "SecuenciaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clicable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "clicable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPadre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idPadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ItemVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "item"));
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
