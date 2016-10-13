/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * InformeCoberturaFederadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con la información de los odes existentes en cada rama del
 *                         árbol curricular para cada nodo
 */
public class InformeCoberturaFederadoVO  implements java.io.Serializable {
    /* Identificador del nodo */
    private java.lang.String nodo;

    /* Nombre del nodo, será el campo que se mostrará en el informe */
    private es.pode.auditoria.negocio.servicios.CoberturaCurricularContenido[] coberturaCurricular;

    public InformeCoberturaFederadoVO() {
    }

    public InformeCoberturaFederadoVO(
           java.lang.String nodo,
           es.pode.auditoria.negocio.servicios.CoberturaCurricularContenido[] coberturaCurricular) {
           this.nodo = nodo;
           this.coberturaCurricular = coberturaCurricular;
    }


    /**
     * Gets the nodo value for this InformeCoberturaFederadoVO.
     * 
     * @return nodo   * Identificador del nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this InformeCoberturaFederadoVO.
     * 
     * @param nodo   * Identificador del nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the coberturaCurricular value for this InformeCoberturaFederadoVO.
     * 
     * @return coberturaCurricular   * Nombre del nodo, será el campo que se mostrará en el informe
     */
    public es.pode.auditoria.negocio.servicios.CoberturaCurricularContenido[] getCoberturaCurricular() {
        return coberturaCurricular;
    }


    /**
     * Sets the coberturaCurricular value for this InformeCoberturaFederadoVO.
     * 
     * @param coberturaCurricular   * Nombre del nodo, será el campo que se mostrará en el informe
     */
    public void setCoberturaCurricular(es.pode.auditoria.negocio.servicios.CoberturaCurricularContenido[] coberturaCurricular) {
        this.coberturaCurricular = coberturaCurricular;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeCoberturaFederadoVO)) return false;
        InformeCoberturaFederadoVO other = (InformeCoberturaFederadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
            ((this.coberturaCurricular==null && other.getCoberturaCurricular()==null) || 
             (this.coberturaCurricular!=null &&
              java.util.Arrays.equals(this.coberturaCurricular, other.getCoberturaCurricular())));
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
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        if (getCoberturaCurricular() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCoberturaCurricular());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCoberturaCurricular(), i);
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
        new org.apache.axis.description.TypeDesc(InformeCoberturaFederadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeCoberturaFederadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "coberturaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "CoberturaCurricularContenido"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "item"));
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
