/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * InformeCargaMasivaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * Value Object con la informaci贸n que se mostrar谩 en el informe de
 * carga masiva
 */
public class InformeCargaMasivaVO  implements java.io.Serializable {
    /* Descripci贸n de la tarea de carga masiva */
    private java.lang.String descripcionCarga;

    /* Nombre del lote que se carga en la tarea de carga masiva */
    private java.lang.String nombreLote;

    /* Nombre de la tarea de carga masiva */
    private java.lang.String nombreTarea;

    /* Path donde se encuentran los odes a cargar */
    private java.lang.String pathCarga;

    /* Informaci贸n de cada uno de los odes cargados en la tarea de
     * carga masiva */
    private es.pode.soporte.auditoria.servicios.RegistroCargaMasivaVO[] registroCargaVO;

    /* Path donde se encuentran los odes cargados correctamente */
    private java.lang.String pathCarga_OK;

    /* Path donde se encuentran los odes cargados correctamente */
    private java.lang.String pathCarga_KO;

    public InformeCargaMasivaVO() {
    }

    public InformeCargaMasivaVO(
           java.lang.String descripcionCarga,
           java.lang.String nombreLote,
           java.lang.String nombreTarea,
           java.lang.String pathCarga,
           es.pode.soporte.auditoria.servicios.RegistroCargaMasivaVO[] registroCargaVO,
           java.lang.String pathCarga_OK,
           java.lang.String pathCarga_KO) {
           this.descripcionCarga = descripcionCarga;
           this.nombreLote = nombreLote;
           this.nombreTarea = nombreTarea;
           this.pathCarga = pathCarga;
           this.registroCargaVO = registroCargaVO;
           this.pathCarga_OK = pathCarga_OK;
           this.pathCarga_KO = pathCarga_KO;
    }


    /**
     * Gets the descripcionCarga value for this InformeCargaMasivaVO.
     * 
     * @return descripcionCarga   * Descripci贸n de la tarea de carga masiva
     */
    public java.lang.String getDescripcionCarga() {
        return descripcionCarga;
    }


    /**
     * Sets the descripcionCarga value for this InformeCargaMasivaVO.
     * 
     * @param descripcionCarga   * Descripci贸n de la tarea de carga masiva
     */
    public void setDescripcionCarga(java.lang.String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
    }


    /**
     * Gets the nombreLote value for this InformeCargaMasivaVO.
     * 
     * @return nombreLote   * Nombre del lote que se carga en la tarea de carga masiva
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this InformeCargaMasivaVO.
     * 
     * @param nombreLote   * Nombre del lote que se carga en la tarea de carga masiva
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the nombreTarea value for this InformeCargaMasivaVO.
     * 
     * @return nombreTarea   * Nombre de la tarea de carga masiva
     */
    public java.lang.String getNombreTarea() {
        return nombreTarea;
    }


    /**
     * Sets the nombreTarea value for this InformeCargaMasivaVO.
     * 
     * @param nombreTarea   * Nombre de la tarea de carga masiva
     */
    public void setNombreTarea(java.lang.String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }


    /**
     * Gets the pathCarga value for this InformeCargaMasivaVO.
     * 
     * @return pathCarga   * Path donde se encuentran los odes a cargar
     */
    public java.lang.String getPathCarga() {
        return pathCarga;
    }


    /**
     * Sets the pathCarga value for this InformeCargaMasivaVO.
     * 
     * @param pathCarga   * Path donde se encuentran los odes a cargar
     */
    public void setPathCarga(java.lang.String pathCarga) {
        this.pathCarga = pathCarga;
    }


    /**
     * Gets the registroCargaVO value for this InformeCargaMasivaVO.
     * 
     * @return registroCargaVO   * Informaci贸n de cada uno de los odes cargados en la tarea de
     * carga masiva
     */
    public es.pode.soporte.auditoria.servicios.RegistroCargaMasivaVO[] getRegistroCargaVO() {
        return registroCargaVO;
    }


    /**
     * Sets the registroCargaVO value for this InformeCargaMasivaVO.
     * 
     * @param registroCargaVO   * Informaci贸n de cada uno de los odes cargados en la tarea de
     * carga masiva
     */
    public void setRegistroCargaVO(es.pode.soporte.auditoria.servicios.RegistroCargaMasivaVO[] registroCargaVO) {
        this.registroCargaVO = registroCargaVO;
    }


    /**
     * Gets the pathCarga_OK value for this InformeCargaMasivaVO.
     * 
     * @return pathCarga_OK   * Path donde se encuentran los odes cargados correctamente
     */
    public java.lang.String getPathCarga_OK() {
        return pathCarga_OK;
    }


    /**
     * Sets the pathCarga_OK value for this InformeCargaMasivaVO.
     * 
     * @param pathCarga_OK   * Path donde se encuentran los odes cargados correctamente
     */
    public void setPathCarga_OK(java.lang.String pathCarga_OK) {
        this.pathCarga_OK = pathCarga_OK;
    }


    /**
     * Gets the pathCarga_KO value for this InformeCargaMasivaVO.
     * 
     * @return pathCarga_KO   * Path donde se encuentran los odes cargados correctamente
     */
    public java.lang.String getPathCarga_KO() {
        return pathCarga_KO;
    }


    /**
     * Sets the pathCarga_KO value for this InformeCargaMasivaVO.
     * 
     * @param pathCarga_KO   * Path donde se encuentran los odes cargados correctamente
     */
    public void setPathCarga_KO(java.lang.String pathCarga_KO) {
        this.pathCarga_KO = pathCarga_KO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeCargaMasivaVO)) return false;
        InformeCargaMasivaVO other = (InformeCargaMasivaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionCarga==null && other.getDescripcionCarga()==null) || 
             (this.descripcionCarga!=null &&
              this.descripcionCarga.equals(other.getDescripcionCarga()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.nombreTarea==null && other.getNombreTarea()==null) || 
             (this.nombreTarea!=null &&
              this.nombreTarea.equals(other.getNombreTarea()))) &&
            ((this.pathCarga==null && other.getPathCarga()==null) || 
             (this.pathCarga!=null &&
              this.pathCarga.equals(other.getPathCarga()))) &&
            ((this.registroCargaVO==null && other.getRegistroCargaVO()==null) || 
             (this.registroCargaVO!=null &&
              java.util.Arrays.equals(this.registroCargaVO, other.getRegistroCargaVO()))) &&
            ((this.pathCarga_OK==null && other.getPathCarga_OK()==null) || 
             (this.pathCarga_OK!=null &&
              this.pathCarga_OK.equals(other.getPathCarga_OK()))) &&
            ((this.pathCarga_KO==null && other.getPathCarga_KO()==null) || 
             (this.pathCarga_KO!=null &&
              this.pathCarga_KO.equals(other.getPathCarga_KO())));
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
        if (getDescripcionCarga() != null) {
            _hashCode += getDescripcionCarga().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getNombreTarea() != null) {
            _hashCode += getNombreTarea().hashCode();
        }
        if (getPathCarga() != null) {
            _hashCode += getPathCarga().hashCode();
        }
        if (getRegistroCargaVO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegistroCargaVO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegistroCargaVO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPathCarga_OK() != null) {
            _hashCode += getPathCarga_OK().hashCode();
        }
        if (getPathCarga_KO() != null) {
            _hashCode += getPathCarga_KO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeCargaMasivaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "InformeCargaMasivaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "descripcionCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreLote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "nombreLote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "nombreTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "pathCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroCargaVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "registroCargaVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "RegistroCargaMasivaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCarga_OK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "pathCarga_OK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCarga_KO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "pathCarga_KO"));
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
