/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ValidaBajaInformeCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * VO para recoger la informaci贸n de la eliminaci贸n de informes de
 * carga
 */
public class ValidaBajaInformeCargaVO  implements java.io.Serializable {
    /* Descripci贸n de c贸mo ha ido la baja, OK o KO */
    private java.lang.String descripcionBaja;

    /* Lista de los informes de carga borrados */
    private java.lang.String[] informesBorrados;

    public ValidaBajaInformeCargaVO() {
    }

    public ValidaBajaInformeCargaVO(
           java.lang.String descripcionBaja,
           java.lang.String[] informesBorrados) {
           this.descripcionBaja = descripcionBaja;
           this.informesBorrados = informesBorrados;
    }


    /**
     * Gets the descripcionBaja value for this ValidaBajaInformeCargaVO.
     * 
     * @return descripcionBaja   * Descripci贸n de c贸mo ha ido la baja, OK o KO
     */
    public java.lang.String getDescripcionBaja() {
        return descripcionBaja;
    }


    /**
     * Sets the descripcionBaja value for this ValidaBajaInformeCargaVO.
     * 
     * @param descripcionBaja   * Descripci贸n de c贸mo ha ido la baja, OK o KO
     */
    public void setDescripcionBaja(java.lang.String descripcionBaja) {
        this.descripcionBaja = descripcionBaja;
    }


    /**
     * Gets the informesBorrados value for this ValidaBajaInformeCargaVO.
     * 
     * @return informesBorrados   * Lista de los informes de carga borrados
     */
    public java.lang.String[] getInformesBorrados() {
        return informesBorrados;
    }


    /**
     * Sets the informesBorrados value for this ValidaBajaInformeCargaVO.
     * 
     * @param informesBorrados   * Lista de los informes de carga borrados
     */
    public void setInformesBorrados(java.lang.String[] informesBorrados) {
        this.informesBorrados = informesBorrados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaBajaInformeCargaVO)) return false;
        ValidaBajaInformeCargaVO other = (ValidaBajaInformeCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionBaja==null && other.getDescripcionBaja()==null) || 
             (this.descripcionBaja!=null &&
              this.descripcionBaja.equals(other.getDescripcionBaja()))) &&
            ((this.informesBorrados==null && other.getInformesBorrados()==null) || 
             (this.informesBorrados!=null &&
              java.util.Arrays.equals(this.informesBorrados, other.getInformesBorrados())));
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
        if (getDescripcionBaja() != null) {
            _hashCode += getDescripcionBaja().hashCode();
        }
        if (getInformesBorrados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInformesBorrados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInformesBorrados(), i);
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
        new org.apache.axis.description.TypeDesc(ValidaBajaInformeCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ValidaBajaInformeCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcionBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informesBorrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "informesBorrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
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
