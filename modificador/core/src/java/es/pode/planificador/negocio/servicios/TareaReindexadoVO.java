/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaReindexadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaReindexadoVO  implements java.io.Serializable {
    /* Nombre del trabajo */
    private java.lang.String trabajo;

    private java.lang.String grupoTrabajo;

    private java.lang.String trigger;

    private java.lang.String grupoTrigger;

    private java.lang.String cron;

    private java.lang.String periodicidad;

    private java.util.Calendar fechaInicio;

    private java.lang.String usuario;

    /* Identificador del repositorio de ínices que se va a indexar */
    private java.lang.String repositorioIndices;

    private java.lang.String tipoTarea;

    private java.lang.String msgReindexado;

    private java.lang.String msgNoReindexado;

    private java.lang.String msgDescripcionTrabajo;

    /* Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error. */
    private java.lang.String error;

    public TareaReindexadoVO() {
    }

    public TareaReindexadoVO(
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.lang.String trigger,
           java.lang.String grupoTrigger,
           java.lang.String cron,
           java.lang.String periodicidad,
           java.util.Calendar fechaInicio,
           java.lang.String usuario,
           java.lang.String repositorioIndices,
           java.lang.String tipoTarea,
           java.lang.String msgReindexado,
           java.lang.String msgNoReindexado,
           java.lang.String msgDescripcionTrabajo,
           java.lang.String error) {
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.trigger = trigger;
           this.grupoTrigger = grupoTrigger;
           this.cron = cron;
           this.periodicidad = periodicidad;
           this.fechaInicio = fechaInicio;
           this.usuario = usuario;
           this.repositorioIndices = repositorioIndices;
           this.tipoTarea = tipoTarea;
           this.msgReindexado = msgReindexado;
           this.msgNoReindexado = msgNoReindexado;
           this.msgDescripcionTrabajo = msgDescripcionTrabajo;
           this.error = error;
    }


    /**
     * Gets the trabajo value for this TareaReindexadoVO.
     * 
     * @return trabajo   * Nombre del trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaReindexadoVO.
     * 
     * @param trabajo   * Nombre del trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaReindexadoVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaReindexadoVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the trigger value for this TareaReindexadoVO.
     * 
     * @return trigger
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaReindexadoVO.
     * 
     * @param trigger
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the grupoTrigger value for this TareaReindexadoVO.
     * 
     * @return grupoTrigger
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaReindexadoVO.
     * 
     * @param grupoTrigger
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the cron value for this TareaReindexadoVO.
     * 
     * @return cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaReindexadoVO.
     * 
     * @param cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }


    /**
     * Gets the periodicidad value for this TareaReindexadoVO.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaReindexadoVO.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the fechaInicio value for this TareaReindexadoVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaReindexadoVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the usuario value for this TareaReindexadoVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaReindexadoVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the repositorioIndices value for this TareaReindexadoVO.
     * 
     * @return repositorioIndices   * Identificador del repositorio de ínices que se va a indexar
     */
    public java.lang.String getRepositorioIndices() {
        return repositorioIndices;
    }


    /**
     * Sets the repositorioIndices value for this TareaReindexadoVO.
     * 
     * @param repositorioIndices   * Identificador del repositorio de ínices que se va a indexar
     */
    public void setRepositorioIndices(java.lang.String repositorioIndices) {
        this.repositorioIndices = repositorioIndices;
    }


    /**
     * Gets the tipoTarea value for this TareaReindexadoVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaReindexadoVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the msgReindexado value for this TareaReindexadoVO.
     * 
     * @return msgReindexado
     */
    public java.lang.String getMsgReindexado() {
        return msgReindexado;
    }


    /**
     * Sets the msgReindexado value for this TareaReindexadoVO.
     * 
     * @param msgReindexado
     */
    public void setMsgReindexado(java.lang.String msgReindexado) {
        this.msgReindexado = msgReindexado;
    }


    /**
     * Gets the msgNoReindexado value for this TareaReindexadoVO.
     * 
     * @return msgNoReindexado
     */
    public java.lang.String getMsgNoReindexado() {
        return msgNoReindexado;
    }


    /**
     * Sets the msgNoReindexado value for this TareaReindexadoVO.
     * 
     * @param msgNoReindexado
     */
    public void setMsgNoReindexado(java.lang.String msgNoReindexado) {
        this.msgNoReindexado = msgNoReindexado;
    }


    /**
     * Gets the msgDescripcionTrabajo value for this TareaReindexadoVO.
     * 
     * @return msgDescripcionTrabajo
     */
    public java.lang.String getMsgDescripcionTrabajo() {
        return msgDescripcionTrabajo;
    }


    /**
     * Sets the msgDescripcionTrabajo value for this TareaReindexadoVO.
     * 
     * @param msgDescripcionTrabajo
     */
    public void setMsgDescripcionTrabajo(java.lang.String msgDescripcionTrabajo) {
        this.msgDescripcionTrabajo = msgDescripcionTrabajo;
    }


    /**
     * Gets the error value for this TareaReindexadoVO.
     * 
     * @return error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TareaReindexadoVO.
     * 
     * @param error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaReindexadoVO)) return false;
        TareaReindexadoVO other = (TareaReindexadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
              this.cron.equals(other.getCron()))) &&
            ((this.periodicidad==null && other.getPeriodicidad()==null) || 
             (this.periodicidad!=null &&
              this.periodicidad.equals(other.getPeriodicidad()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.repositorioIndices==null && other.getRepositorioIndices()==null) || 
             (this.repositorioIndices!=null &&
              this.repositorioIndices.equals(other.getRepositorioIndices()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.msgReindexado==null && other.getMsgReindexado()==null) || 
             (this.msgReindexado!=null &&
              this.msgReindexado.equals(other.getMsgReindexado()))) &&
            ((this.msgNoReindexado==null && other.getMsgNoReindexado()==null) || 
             (this.msgNoReindexado!=null &&
              this.msgNoReindexado.equals(other.getMsgNoReindexado()))) &&
            ((this.msgDescripcionTrabajo==null && other.getMsgDescripcionTrabajo()==null) || 
             (this.msgDescripcionTrabajo!=null &&
              this.msgDescripcionTrabajo.equals(other.getMsgDescripcionTrabajo()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError())));
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
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getRepositorioIndices() != null) {
            _hashCode += getRepositorioIndices().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getMsgReindexado() != null) {
            _hashCode += getMsgReindexado().hashCode();
        }
        if (getMsgNoReindexado() != null) {
            _hashCode += getMsgNoReindexado().hashCode();
        }
        if (getMsgDescripcionTrabajo() != null) {
            _hashCode += getMsgDescripcionTrabajo().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaReindexadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("repositorioIndices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "repositorioIndices"));
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
        elemField.setFieldName("msgReindexado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgReindexado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgNoReindexado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgNoReindexado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgDescripcionTrabajo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgDescripcionTrabajo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "error"));
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
