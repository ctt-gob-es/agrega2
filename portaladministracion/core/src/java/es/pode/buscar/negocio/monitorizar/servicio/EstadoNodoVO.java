/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * EstadoNodoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.monitorizar.servicio;

public class EstadoNodoVO  implements java.io.Serializable {
    private java.lang.String nodo;

    private java.lang.String estado;

    private java.lang.String nombreServicio;

    private java.lang.String descripcionServicio;

    private java.lang.String urlWS;

    public EstadoNodoVO() {
    }

    public EstadoNodoVO(
           java.lang.String nodo,
           java.lang.String estado,
           java.lang.String nombreServicio,
           java.lang.String descripcionServicio,
           java.lang.String urlWS) {
           this.nodo = nodo;
           this.estado = estado;
           this.nombreServicio = nombreServicio;
           this.descripcionServicio = descripcionServicio;
           this.urlWS = urlWS;
    }


    /**
     * Gets the nodo value for this EstadoNodoVO.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this EstadoNodoVO.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the estado value for this EstadoNodoVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this EstadoNodoVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the nombreServicio value for this EstadoNodoVO.
     * 
     * @return nombreServicio
     */
    public java.lang.String getNombreServicio() {
        return nombreServicio;
    }


    /**
     * Sets the nombreServicio value for this EstadoNodoVO.
     * 
     * @param nombreServicio
     */
    public void setNombreServicio(java.lang.String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }


    /**
     * Gets the descripcionServicio value for this EstadoNodoVO.
     * 
     * @return descripcionServicio
     */
    public java.lang.String getDescripcionServicio() {
        return descripcionServicio;
    }


    /**
     * Sets the descripcionServicio value for this EstadoNodoVO.
     * 
     * @param descripcionServicio
     */
    public void setDescripcionServicio(java.lang.String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }


    /**
     * Gets the urlWS value for this EstadoNodoVO.
     * 
     * @return urlWS
     */
    public java.lang.String getUrlWS() {
        return urlWS;
    }


    /**
     * Sets the urlWS value for this EstadoNodoVO.
     * 
     * @param urlWS
     */
    public void setUrlWS(java.lang.String urlWS) {
        this.urlWS = urlWS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstadoNodoVO)) return false;
        EstadoNodoVO other = (EstadoNodoVO) obj;
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
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.nombreServicio==null && other.getNombreServicio()==null) || 
             (this.nombreServicio!=null &&
              this.nombreServicio.equals(other.getNombreServicio()))) &&
            ((this.descripcionServicio==null && other.getDescripcionServicio()==null) || 
             (this.descripcionServicio!=null &&
              this.descripcionServicio.equals(other.getDescripcionServicio()))) &&
            ((this.urlWS==null && other.getUrlWS()==null) || 
             (this.urlWS!=null &&
              this.urlWS.equals(other.getUrlWS())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getNombreServicio() != null) {
            _hashCode += getNombreServicio().hashCode();
        }
        if (getDescripcionServicio() != null) {
            _hashCode += getDescripcionServicio().hashCode();
        }
        if (getUrlWS() != null) {
            _hashCode += getUrlWS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstadoNodoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "EstadoNodoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "nombreServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "descripcionServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlWS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.monitorizar.negocio.buscar.pode.es", "urlWS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
