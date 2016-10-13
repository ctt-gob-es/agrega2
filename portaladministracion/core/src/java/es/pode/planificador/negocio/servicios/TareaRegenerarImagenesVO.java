/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaRegenerarImagenesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaRegenerarImagenesVO  implements java.io.Serializable {
    private java.lang.String periodicidad;

    private java.util.Calendar fechaInicio;

    private java.lang.String usuario;

    private java.lang.String tipoTarea;

    private java.lang.String trabajo;

    private java.lang.String grupoTrabajo;

    private java.lang.String trigger;

    private java.lang.String grupoTrigger;

    private java.lang.String cron;

    public TareaRegenerarImagenesVO() {
    }

    public TareaRegenerarImagenesVO(
           java.lang.String periodicidad,
           java.util.Calendar fechaInicio,
           java.lang.String usuario,
           java.lang.String tipoTarea,
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.lang.String trigger,
           java.lang.String grupoTrigger,
           java.lang.String cron) {
           this.periodicidad = periodicidad;
           this.fechaInicio = fechaInicio;
           this.usuario = usuario;
           this.tipoTarea = tipoTarea;
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.trigger = trigger;
           this.grupoTrigger = grupoTrigger;
           this.cron = cron;
    }


    /**
     * Gets the periodicidad value for this TareaRegenerarImagenesVO.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaRegenerarImagenesVO.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the fechaInicio value for this TareaRegenerarImagenesVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaRegenerarImagenesVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the usuario value for this TareaRegenerarImagenesVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaRegenerarImagenesVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the tipoTarea value for this TareaRegenerarImagenesVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaRegenerarImagenesVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the trabajo value for this TareaRegenerarImagenesVO.
     * 
     * @return trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaRegenerarImagenesVO.
     * 
     * @param trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaRegenerarImagenesVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaRegenerarImagenesVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the trigger value for this TareaRegenerarImagenesVO.
     * 
     * @return trigger
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaRegenerarImagenesVO.
     * 
     * @param trigger
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the grupoTrigger value for this TareaRegenerarImagenesVO.
     * 
     * @return grupoTrigger
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaRegenerarImagenesVO.
     * 
     * @param grupoTrigger
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the cron value for this TareaRegenerarImagenesVO.
     * 
     * @return cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaRegenerarImagenesVO.
     * 
     * @param cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaRegenerarImagenesVO)) return false;
        TareaRegenerarImagenesVO other = (TareaRegenerarImagenesVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.periodicidad==null && other.getPeriodicidad()==null) || 
             (this.periodicidad!=null &&
              this.periodicidad.equals(other.getPeriodicidad()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.trabajo==null && other.getTrabajo()==null) || 
             (this.trabajo!=null &&
              this.trabajo.equals(other.getTrabajo()))) &&
            ((this.grupoTrabajo==null && other.getGrupoTrabajo()==null) || 
             (this.grupoTrabajo!=null &&
              this.grupoTrabajo.equals(other.getGrupoTrabajo()))) &&
            ((this.trigger==null && other.getTrigger()==null) || 
             (this.trigger!=null &&
              this.trigger.equals(other.getTrigger()))) &&
            ((this.grupoTrigger==null && other.getGrupoTrigger()==null) || 
             (this.grupoTrigger!=null &&
              this.grupoTrigger.equals(other.getGrupoTrigger()))) &&
            ((this.cron==null && other.getCron()==null) || 
             (this.cron!=null &&
              this.cron.equals(other.getCron())));
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
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getTrabajo() != null) {
            _hashCode += getTrabajo().hashCode();
        }
        if (getGrupoTrabajo() != null) {
            _hashCode += getGrupoTrabajo().hashCode();
        }
        if (getTrigger() != null) {
            _hashCode += getTrigger().hashCode();
        }
        if (getGrupoTrigger() != null) {
            _hashCode += getGrupoTrigger().hashCode();
        }
        if (getCron() != null) {
            _hashCode += getCron().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaRegenerarImagenesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodicidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "periodicidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tipoTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trabajo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoTrabajo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "grupoTrabajo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trigger");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trigger"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoTrigger");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "grupoTrigger"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cron");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "cron"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
