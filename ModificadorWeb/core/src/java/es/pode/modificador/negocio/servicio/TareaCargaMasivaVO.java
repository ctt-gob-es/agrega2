/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaCargaMasivaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;


/**
 * Objeto con la descripcion de una tarea de carga de ODEs.
 */
public class TareaCargaMasivaVO  implements java.io.Serializable {
    /* Identificador de la tarea en el modulo planificador. */
    private java.lang.Long id;

    /* Nombre de lote asociado a la tarea de carga. */
    private java.lang.String nombreLote;

    /* Descripcion de la tarea de carga masiva. */
    private java.lang.String descripcion;

    /* Descripcion de la tarea de carga masiva. */
    private java.lang.String descripcionTarea;

    /* Fecha de finalizacion de la carga. */
    private java.util.Calendar fechaFin;

    public TareaCargaMasivaVO() {
    }

    public TareaCargaMasivaVO(
           java.lang.Long id,
           java.lang.String nombreLote,
           java.lang.String descripcion,
           java.lang.String descripcionTarea,
           java.util.Calendar fechaFin) {
           this.id = id;
           this.nombreLote = nombreLote;
           this.descripcion = descripcion;
           this.descripcionTarea = descripcionTarea;
           this.fechaFin = fechaFin;
    }


    /**
     * Gets the id value for this TareaCargaMasivaVO.
     * 
     * @return id   * Identificador de la tarea en el modulo planificador.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TareaCargaMasivaVO.
     * 
     * @param id   * Identificador de la tarea en el modulo planificador.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombreLote value for this TareaCargaMasivaVO.
     * 
     * @return nombreLote   * Nombre de lote asociado a la tarea de carga.
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this TareaCargaMasivaVO.
     * 
     * @param nombreLote   * Nombre de lote asociado a la tarea de carga.
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the descripcion value for this TareaCargaMasivaVO.
     * 
     * @return descripcion   * Descripcion de la tarea de carga masiva.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this TareaCargaMasivaVO.
     * 
     * @param descripcion   * Descripcion de la tarea de carga masiva.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the descripcionTarea value for this TareaCargaMasivaVO.
     * 
     * @return descripcionTarea   * Descripcion de la tarea de carga masiva.
     */
    public java.lang.String getDescripcionTarea() {
        return descripcionTarea;
    }


    /**
     * Sets the descripcionTarea value for this TareaCargaMasivaVO.
     * 
     * @param descripcionTarea   * Descripcion de la tarea de carga masiva.
     */
    public void setDescripcionTarea(java.lang.String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }


    /**
     * Gets the fechaFin value for this TareaCargaMasivaVO.
     * 
     * @return fechaFin   * Fecha de finalizacion de la carga.
     */
    public java.util.Calendar getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this TareaCargaMasivaVO.
     * 
     * @param fechaFin   * Fecha de finalizacion de la carga.
     */
    public void setFechaFin(java.util.Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaCargaMasivaVO)) return false;
        TareaCargaMasivaVO other = (TareaCargaMasivaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.descripcionTarea==null && other.getDescripcionTarea()==null) || 
             (this.descripcionTarea!=null &&
              this.descripcionTarea.equals(other.getDescripcionTarea()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) || 
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getDescripcionTarea() != null) {
            _hashCode += getDescripcionTarea().hashCode();
        }
        if (getFechaFin() != null) {
            _hashCode += getFechaFin().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaCargaMasivaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TareaCargaMasivaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreLote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "nombreLote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "descripcionTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "fechaFin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
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
