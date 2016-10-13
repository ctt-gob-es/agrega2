/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * EstadoActividadVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;


/**
 * Esta clase almacena la actividad de un determinado estado.
 */
public class EstadoActividadVO  implements java.io.Serializable {
    /* Se trata del estado. */
    private java.lang.String estado;

    private es.pode.publicacion.negocio.servicios.DetalleTransicionVO[] detalleTransicionVOs;

    public EstadoActividadVO() {
    }

    public EstadoActividadVO(
           java.lang.String estado,
           es.pode.publicacion.negocio.servicios.DetalleTransicionVO[] detalleTransicionVOs) {
           this.estado = estado;
           this.detalleTransicionVOs = detalleTransicionVOs;
    }


    /**
     * Gets the estado value for this EstadoActividadVO.
     * 
     * @return estado   * Se trata del estado.
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this EstadoActividadVO.
     * 
     * @param estado   * Se trata del estado.
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the detalleTransicionVOs value for this EstadoActividadVO.
     * 
     * @return detalleTransicionVOs
     */
    public es.pode.publicacion.negocio.servicios.DetalleTransicionVO[] getDetalleTransicionVOs() {
        return detalleTransicionVOs;
    }


    /**
     * Sets the detalleTransicionVOs value for this EstadoActividadVO.
     * 
     * @param detalleTransicionVOs
     */
    public void setDetalleTransicionVOs(es.pode.publicacion.negocio.servicios.DetalleTransicionVO[] detalleTransicionVOs) {
        this.detalleTransicionVOs = detalleTransicionVOs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstadoActividadVO)) return false;
        EstadoActividadVO other = (EstadoActividadVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.detalleTransicionVOs==null && other.getDetalleTransicionVOs()==null) || 
             (this.detalleTransicionVOs!=null &&
              java.util.Arrays.equals(this.detalleTransicionVOs, other.getDetalleTransicionVOs())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getDetalleTransicionVOs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetalleTransicionVOs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetalleTransicionVOs(), i);
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
        new org.apache.axis.description.TypeDesc(EstadoActividadVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "EstadoActividadVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalleTransicionVOs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "detalleTransicionVOs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetalleTransicionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
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
