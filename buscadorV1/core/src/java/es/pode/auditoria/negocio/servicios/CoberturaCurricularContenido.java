/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CoberturaCurricularContenido.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con el número de odes que hay en cada rama del árbol
 *                         curricular
 */
public class CoberturaCurricularContenido  implements java.io.Serializable {
    /* Identificador del árbol curricular */
    private java.lang.String coberturaCurricular;

    /* Número de odes existentes en la rama del árbol curricular */
    private long totalOdes;

    public CoberturaCurricularContenido() {
    }

    public CoberturaCurricularContenido(
           java.lang.String coberturaCurricular,
           long totalOdes) {
           this.coberturaCurricular = coberturaCurricular;
           this.totalOdes = totalOdes;
    }


    /**
     * Gets the coberturaCurricular value for this CoberturaCurricularContenido.
     * 
     * @return coberturaCurricular   * Identificador del árbol curricular
     */
    public java.lang.String getCoberturaCurricular() {
        return coberturaCurricular;
    }


    /**
     * Sets the coberturaCurricular value for this CoberturaCurricularContenido.
     * 
     * @param coberturaCurricular   * Identificador del árbol curricular
     */
    public void setCoberturaCurricular(java.lang.String coberturaCurricular) {
        this.coberturaCurricular = coberturaCurricular;
    }


    /**
     * Gets the totalOdes value for this CoberturaCurricularContenido.
     * 
     * @return totalOdes   * Número de odes existentes en la rama del árbol curricular
     */
    public long getTotalOdes() {
        return totalOdes;
    }


    /**
     * Sets the totalOdes value for this CoberturaCurricularContenido.
     * 
     * @param totalOdes   * Número de odes existentes en la rama del árbol curricular
     */
    public void setTotalOdes(long totalOdes) {
        this.totalOdes = totalOdes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CoberturaCurricularContenido)) return false;
        CoberturaCurricularContenido other = (CoberturaCurricularContenido) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coberturaCurricular==null && other.getCoberturaCurricular()==null) || 
             (this.coberturaCurricular!=null &&
              this.coberturaCurricular.equals(other.getCoberturaCurricular()))) &&
            this.totalOdes == other.getTotalOdes();
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
        if (getCoberturaCurricular() != null) {
            _hashCode += getCoberturaCurricular().hashCode();
        }
        _hashCode += new Long(getTotalOdes()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CoberturaCurricularContenido.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "CoberturaCurricularContenido"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "coberturaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "totalOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
