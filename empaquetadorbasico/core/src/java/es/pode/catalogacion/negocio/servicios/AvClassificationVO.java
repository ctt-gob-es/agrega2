/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvClassificationVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvClassificationVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.SourceValueVO proposito;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion;

    private es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO[] rutasTaxonomicas;

    private es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave;

    public AvClassificationVO() {
    }

    public AvClassificationVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO proposito,
           es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion,
           es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO[] rutasTaxonomicas,
           es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave) {
           this.proposito = proposito;
           this.descripcion = descripcion;
           this.rutasTaxonomicas = rutasTaxonomicas;
           this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the proposito value for this AvClassificationVO.
     * 
     * @return proposito
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getProposito() {
        return proposito;
    }


    /**
     * Sets the proposito value for this AvClassificationVO.
     * 
     * @param proposito
     */
    public void setProposito(es.pode.catalogacion.negocio.servicios.SourceValueVO proposito) {
        this.proposito = proposito;
    }


    /**
     * Gets the descripcion value for this AvClassificationVO.
     * 
     * @return descripcion
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this AvClassificationVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(es.pode.catalogacion.negocio.servicios.DescripcionVO descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the rutasTaxonomicas value for this AvClassificationVO.
     * 
     * @return rutasTaxonomicas
     */
    public es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO[] getRutasTaxonomicas() {
        return rutasTaxonomicas;
    }


    /**
     * Sets the rutasTaxonomicas value for this AvClassificationVO.
     * 
     * @param rutasTaxonomicas
     */
    public void setRutasTaxonomicas(es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO[] rutasTaxonomicas) {
        this.rutasTaxonomicas = rutasTaxonomicas;
    }


    /**
     * Gets the palabrasClave value for this AvClassificationVO.
     * 
     * @return palabrasClave
     */
    public es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this AvClassificationVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvClassificationVO)) return false;
        AvClassificationVO other = (AvClassificationVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.proposito==null && other.getProposito()==null) || 
             (this.proposito!=null &&
              this.proposito.equals(other.getProposito()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.rutasTaxonomicas==null && other.getRutasTaxonomicas()==null) || 
             (this.rutasTaxonomicas!=null &&
              java.util.Arrays.equals(this.rutasTaxonomicas, other.getRutasTaxonomicas()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave())));
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
        if (getProposito() != null) {
            _hashCode += getProposito().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getRutasTaxonomicas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRutasTaxonomicas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRutasTaxonomicas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPalabrasClave() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPalabrasClave());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPalabrasClave(), i);
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
        new org.apache.axis.description.TypeDesc(AvClassificationVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvClassificationVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proposito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "proposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutasTaxonomicas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "rutasTaxonomicas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RutaTaxonomicaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PalabraClaveVO"));
        elemField.setNillable(true);
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
