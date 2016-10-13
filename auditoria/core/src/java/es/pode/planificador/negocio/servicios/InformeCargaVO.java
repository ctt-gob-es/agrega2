/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * InformeCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * Contiene informaci贸n de la tarea de carga masiva
 */
public class InformeCargaVO  implements java.io.Serializable {
    /* El nombre de la tarea */
    private java.lang.String nombreTarea;

    /* Es el path ra铆z donde se encuentran los ODEs */
    private java.lang.String pathCarga;

    /* La descripci贸n de la carga */
    private java.lang.String descripcionCarga;

    /* El nombre del lote de odes que se publican con esta tarea */
    private java.lang.String nombreLote;

    /* El registro de los odes que se han publicado o no con esta
     * tarea */
    private es.pode.planificador.negocio.servicios.RegistroCargaVO[] registroCarga;

    private java.lang.String pathCargaOK;

    private java.lang.String pathCargaKO;

    public InformeCargaVO() {
    }

    public InformeCargaVO(
           java.lang.String nombreTarea,
           java.lang.String pathCarga,
           java.lang.String descripcionCarga,
           java.lang.String nombreLote,
           es.pode.planificador.negocio.servicios.RegistroCargaVO[] registroCarga,
           java.lang.String pathCargaOK,
           java.lang.String pathCargaKO) {
           this.nombreTarea = nombreTarea;
           this.pathCarga = pathCarga;
           this.descripcionCarga = descripcionCarga;
           this.nombreLote = nombreLote;
           this.registroCarga = registroCarga;
           this.pathCargaOK = pathCargaOK;
           this.pathCargaKO = pathCargaKO;
    }


    /**
     * Gets the nombreTarea value for this InformeCargaVO.
     * 
     * @return nombreTarea   * El nombre de la tarea
     */
    public java.lang.String getNombreTarea() {
        return nombreTarea;
    }


    /**
     * Sets the nombreTarea value for this InformeCargaVO.
     * 
     * @param nombreTarea   * El nombre de la tarea
     */
    public void setNombreTarea(java.lang.String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }


    /**
     * Gets the pathCarga value for this InformeCargaVO.
     * 
     * @return pathCarga   * Es el path ra铆z donde se encuentran los ODEs
     */
    public java.lang.String getPathCarga() {
        return pathCarga;
    }


    /**
     * Sets the pathCarga value for this InformeCargaVO.
     * 
     * @param pathCarga   * Es el path ra铆z donde se encuentran los ODEs
     */
    public void setPathCarga(java.lang.String pathCarga) {
        this.pathCarga = pathCarga;
    }


    /**
     * Gets the descripcionCarga value for this InformeCargaVO.
     * 
     * @return descripcionCarga   * La descripci贸n de la carga
     */
    public java.lang.String getDescripcionCarga() {
        return descripcionCarga;
    }


    /**
     * Sets the descripcionCarga value for this InformeCargaVO.
     * 
     * @param descripcionCarga   * La descripci贸n de la carga
     */
    public void setDescripcionCarga(java.lang.String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
    }


    /**
     * Gets the nombreLote value for this InformeCargaVO.
     * 
     * @return nombreLote   * El nombre del lote de odes que se publican con esta tarea
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this InformeCargaVO.
     * 
     * @param nombreLote   * El nombre del lote de odes que se publican con esta tarea
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the registroCarga value for this InformeCargaVO.
     * 
     * @return registroCarga   * El registro de los odes que se han publicado o no con esta
     * tarea
     */
    public es.pode.planificador.negocio.servicios.RegistroCargaVO[] getRegistroCarga() {
        return registroCarga;
    }


    /**
     * Sets the registroCarga value for this InformeCargaVO.
     * 
     * @param registroCarga   * El registro de los odes que se han publicado o no con esta
     * tarea
     */
    public void setRegistroCarga(es.pode.planificador.negocio.servicios.RegistroCargaVO[] registroCarga) {
        this.registroCarga = registroCarga;
    }


    /**
     * Gets the pathCargaOK value for this InformeCargaVO.
     * 
     * @return pathCargaOK
     */
    public java.lang.String getPathCargaOK() {
        return pathCargaOK;
    }


    /**
     * Sets the pathCargaOK value for this InformeCargaVO.
     * 
     * @param pathCargaOK
     */
    public void setPathCargaOK(java.lang.String pathCargaOK) {
        this.pathCargaOK = pathCargaOK;
    }


    /**
     * Gets the pathCargaKO value for this InformeCargaVO.
     * 
     * @return pathCargaKO
     */
    public java.lang.String getPathCargaKO() {
        return pathCargaKO;
    }


    /**
     * Sets the pathCargaKO value for this InformeCargaVO.
     * 
     * @param pathCargaKO
     */
    public void setPathCargaKO(java.lang.String pathCargaKO) {
        this.pathCargaKO = pathCargaKO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeCargaVO)) return false;
        InformeCargaVO other = (InformeCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreTarea==null && other.getNombreTarea()==null) || 
             (this.nombreTarea!=null &&
              this.nombreTarea.equals(other.getNombreTarea()))) &&
            ((this.pathCarga==null && other.getPathCarga()==null) || 
             (this.pathCarga!=null &&
              this.pathCarga.equals(other.getPathCarga()))) &&
            ((this.descripcionCarga==null && other.getDescripcionCarga()==null) || 
             (this.descripcionCarga!=null &&
              this.descripcionCarga.equals(other.getDescripcionCarga()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.registroCarga==null && other.getRegistroCarga()==null) || 
             (this.registroCarga!=null &&
              java.util.Arrays.equals(this.registroCarga, other.getRegistroCarga()))) &&
            ((this.pathCargaOK==null && other.getPathCargaOK()==null) || 
             (this.pathCargaOK!=null &&
              this.pathCargaOK.equals(other.getPathCargaOK()))) &&
            ((this.pathCargaKO==null && other.getPathCargaKO()==null) || 
             (this.pathCargaKO!=null &&
              this.pathCargaKO.equals(other.getPathCargaKO())));
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
        if (getNombreTarea() != null) {
            _hashCode += getNombreTarea().hashCode();
        }
        if (getPathCarga() != null) {
            _hashCode += getPathCarga().hashCode();
        }
        if (getDescripcionCarga() != null) {
            _hashCode += getDescripcionCarga().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getRegistroCarga() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegistroCarga());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegistroCarga(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPathCargaOK() != null) {
            _hashCode += getPathCargaOK().hashCode();
        }
        if (getPathCargaKO() != null) {
            _hashCode += getPathCargaKO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "InformeCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcionCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreLote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreLote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "registroCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroCargaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCargaOK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCargaOK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCargaKO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCargaKO"));
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
