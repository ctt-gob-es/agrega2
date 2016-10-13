/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ContribucionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;


/**
 * Contribuci贸n de personas y/o entidades al estado del ODE.
 */
public class ContribucionVO  implements java.io.Serializable {
    /* Tipo de contribuci贸n */
    private java.lang.String codTipoContribucion;

    /* Fecha de la contribuci贸n */
    private java.lang.String fecha;

    /* Entidades (persona u organizaciones) que han contribuido en
     * el
     *                                 ODE */
    private es.pode.soporte.buscador.servicios.EntidadVO[] entidades;

    public ContribucionVO() {
    }

    public ContribucionVO(
           java.lang.String codTipoContribucion,
           java.lang.String fecha,
           es.pode.soporte.buscador.servicios.EntidadVO[] entidades) {
           this.codTipoContribucion = codTipoContribucion;
           this.fecha = fecha;
           this.entidades = entidades;
    }


    /**
     * Gets the codTipoContribucion value for this ContribucionVO.
     * 
     * @return codTipoContribucion   * Tipo de contribuci贸n
     */
    public java.lang.String getCodTipoContribucion() {
        return codTipoContribucion;
    }


    /**
     * Sets the codTipoContribucion value for this ContribucionVO.
     * 
     * @param codTipoContribucion   * Tipo de contribuci贸n
     */
    public void setCodTipoContribucion(java.lang.String codTipoContribucion) {
        this.codTipoContribucion = codTipoContribucion;
    }


    /**
     * Gets the fecha value for this ContribucionVO.
     * 
     * @return fecha   * Fecha de la contribuci贸n
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ContribucionVO.
     * 
     * @param fecha   * Fecha de la contribuci贸n
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the entidades value for this ContribucionVO.
     * 
     * @return entidades   * Entidades (persona u organizaciones) que han contribuido en
     * el
     *                                 ODE
     */
    public es.pode.soporte.buscador.servicios.EntidadVO[] getEntidades() {
        return entidades;
    }


    /**
     * Sets the entidades value for this ContribucionVO.
     * 
     * @param entidades   * Entidades (persona u organizaciones) que han contribuido en
     * el
     *                                 ODE
     */
    public void setEntidades(es.pode.soporte.buscador.servicios.EntidadVO[] entidades) {
        this.entidades = entidades;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContribucionVO)) return false;
        ContribucionVO other = (ContribucionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codTipoContribucion==null && other.getCodTipoContribucion()==null) || 
             (this.codTipoContribucion!=null &&
              this.codTipoContribucion.equals(other.getCodTipoContribucion()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.entidades==null && other.getEntidades()==null) || 
             (this.entidades!=null &&
              java.util.Arrays.equals(this.entidades, other.getEntidades())));
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
        if (getCodTipoContribucion() != null) {
            _hashCode += getCodTipoContribucion().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getEntidades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntidades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntidades(), i);
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
        new org.apache.axis.description.TypeDesc(ContribucionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ContribucionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codTipoContribucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "codTipoContribucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "entidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "EntidadVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
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
