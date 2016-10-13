/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ArbolCurricularVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * Representa una ruta de un arbol curricular.
 */
public class ArbolCurricularVO  implements java.io.Serializable {
    /* Indica la posicion entre taxonomias que ocupa el arbol
     *                                 curricular representado dentro del
     * metadato LOM-ES. */
    private java.lang.String idArbol;

    /* Contiene una lista de taxones que representan una ruta sobre
     * un
     *                                 arbol curricular. */
    private es.pode.catalogacion.negocio.servicios.CBTaxonVO[] taxones;

    public ArbolCurricularVO() {
    }

    public ArbolCurricularVO(
           java.lang.String idArbol,
           es.pode.catalogacion.negocio.servicios.CBTaxonVO[] taxones) {
           this.idArbol = idArbol;
           this.taxones = taxones;
    }


    /**
     * Gets the idArbol value for this ArbolCurricularVO.
     * 
     * @return idArbol   * Indica la posicion entre taxonomias que ocupa el arbol
     *                                 curricular representado dentro del
     * metadato LOM-ES.
     */
    public java.lang.String getIdArbol() {
        return idArbol;
    }


    /**
     * Sets the idArbol value for this ArbolCurricularVO.
     * 
     * @param idArbol   * Indica la posicion entre taxonomias que ocupa el arbol
     *                                 curricular representado dentro del
     * metadato LOM-ES.
     */
    public void setIdArbol(java.lang.String idArbol) {
        this.idArbol = idArbol;
    }


    /**
     * Gets the taxones value for this ArbolCurricularVO.
     * 
     * @return taxones   * Contiene una lista de taxones que representan una ruta sobre
     * un
     *                                 arbol curricular.
     */
    public es.pode.catalogacion.negocio.servicios.CBTaxonVO[] getTaxones() {
        return taxones;
    }


    /**
     * Sets the taxones value for this ArbolCurricularVO.
     * 
     * @param taxones   * Contiene una lista de taxones que representan una ruta sobre
     * un
     *                                 arbol curricular.
     */
    public void setTaxones(es.pode.catalogacion.negocio.servicios.CBTaxonVO[] taxones) {
        this.taxones = taxones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArbolCurricularVO)) return false;
        ArbolCurricularVO other = (ArbolCurricularVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idArbol==null && other.getIdArbol()==null) || 
             (this.idArbol!=null &&
              this.idArbol.equals(other.getIdArbol()))) &&
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
        if (getIdArbol() != null) {
            _hashCode += getIdArbol().hashCode();
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
        new org.apache.axis.description.TypeDesc(ArbolCurricularVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArbolCurricularVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idArbol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idArbol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "taxones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "CBTaxonVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
