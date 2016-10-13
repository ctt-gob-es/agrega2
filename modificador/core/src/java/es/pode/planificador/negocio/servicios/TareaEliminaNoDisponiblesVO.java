/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaEliminaNoDisponiblesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * Value Object que almacena toda la informacion de una tarea que
 *                         tiene como proposito eliminar los ODEs No
 * Publicados.
 */
public class TareaEliminaNoDisponiblesVO  implements java.io.Serializable {
    /* Nombre del trabajo */
    private java.lang.String trabajo;

    /* Usuario que crea la tarea. */
    private java.lang.String usuario;

    private java.lang.String trigger;

    private java.lang.String tipoTarea;

    /* Identificador del repositorio de ínices que se va a indexar */
    private java.lang.String repositorioIndices;

    private java.lang.String periodicidad;

    private java.lang.String msgEliminado;

    private java.lang.String msgNoEliminado;

    private java.lang.String msgDescripcionTrabajo;

    private java.lang.String grupoTrigger;

    private java.lang.String grupoTrabajo;

    private java.util.Calendar fechaInicio;

    /* Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error. */
    private java.lang.String error;

    private java.lang.String cron;

    /* Fecha a partir de la cual se quieren eliminar los ODEs no
     *                                 disponibles. */
    private java.util.Calendar fechaDesde;

    /* Fecha hasta la que se quiere borrar objetos no disponibles. */
    private java.util.Calendar fechaHasta;

    public TareaEliminaNoDisponiblesVO() {
    }

    public TareaEliminaNoDisponiblesVO(
           java.lang.String trabajo,
           java.lang.String usuario,
           java.lang.String trigger,
           java.lang.String tipoTarea,
           java.lang.String repositorioIndices,
           java.lang.String periodicidad,
           java.lang.String msgEliminado,
           java.lang.String msgNoEliminado,
           java.lang.String msgDescripcionTrabajo,
           java.lang.String grupoTrigger,
           java.lang.String grupoTrabajo,
           java.util.Calendar fechaInicio,
           java.lang.String error,
           java.lang.String cron,
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta) {
           this.trabajo = trabajo;
           this.usuario = usuario;
           this.trigger = trigger;
           this.tipoTarea = tipoTarea;
           this.repositorioIndices = repositorioIndices;
           this.periodicidad = periodicidad;
           this.msgEliminado = msgEliminado;
           this.msgNoEliminado = msgNoEliminado;
           this.msgDescripcionTrabajo = msgDescripcionTrabajo;
           this.grupoTrigger = grupoTrigger;
           this.grupoTrabajo = grupoTrabajo;
           this.fechaInicio = fechaInicio;
           this.error = error;
           this.cron = cron;
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the trabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return trabajo   * Nombre del trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param trabajo   * Nombre del trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the usuario value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return usuario   * Usuario que crea la tarea.
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param usuario   * Usuario que crea la tarea.
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the trigger value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return trigger
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param trigger
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the tipoTarea value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the repositorioIndices value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return repositorioIndices   * Identificador del repositorio de ínices que se va a indexar
     */
    public java.lang.String getRepositorioIndices() {
        return repositorioIndices;
    }


    /**
     * Sets the repositorioIndices value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param repositorioIndices   * Identificador del repositorio de ínices que se va a indexar
     */
    public void setRepositorioIndices(java.lang.String repositorioIndices) {
        this.repositorioIndices = repositorioIndices;
    }


    /**
     * Gets the periodicidad value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the msgEliminado value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return msgEliminado
     */
    public java.lang.String getMsgEliminado() {
        return msgEliminado;
    }


    /**
     * Sets the msgEliminado value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param msgEliminado
     */
    public void setMsgEliminado(java.lang.String msgEliminado) {
        this.msgEliminado = msgEliminado;
    }


    /**
     * Gets the msgNoEliminado value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return msgNoEliminado
     */
    public java.lang.String getMsgNoEliminado() {
        return msgNoEliminado;
    }


    /**
     * Sets the msgNoEliminado value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param msgNoEliminado
     */
    public void setMsgNoEliminado(java.lang.String msgNoEliminado) {
        this.msgNoEliminado = msgNoEliminado;
    }


    /**
     * Gets the msgDescripcionTrabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return msgDescripcionTrabajo
     */
    public java.lang.String getMsgDescripcionTrabajo() {
        return msgDescripcionTrabajo;
    }


    /**
     * Sets the msgDescripcionTrabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param msgDescripcionTrabajo
     */
    public void setMsgDescripcionTrabajo(java.lang.String msgDescripcionTrabajo) {
        this.msgDescripcionTrabajo = msgDescripcionTrabajo;
    }


    /**
     * Gets the grupoTrigger value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return grupoTrigger
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param grupoTrigger
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the grupoTrabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the fechaInicio value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the error value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the cron value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }


    /**
     * Gets the fechaDesde value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return fechaDesde   * Fecha a partir de la cual se quieren eliminar los ODEs no
     *                                 disponibles.
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param fechaDesde   * Fecha a partir de la cual se quieren eliminar los ODEs no
     *                                 disponibles.
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this TareaEliminaNoDisponiblesVO.
     * 
     * @return fechaHasta   * Fecha hasta la que se quiere borrar objetos no disponibles.
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this TareaEliminaNoDisponiblesVO.
     * 
     * @param fechaHasta   * Fecha hasta la que se quiere borrar objetos no disponibles.
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaEliminaNoDisponiblesVO)) return false;
        TareaEliminaNoDisponiblesVO other = (TareaEliminaNoDisponiblesVO) obj;
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
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.trigger==null && other.getTrigger()==null) || 
             (this.trigger!=null &&
              this.trigger.equals(other.getTrigger()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.repositorioIndices==null && other.getRepositorioIndices()==null) || 
             (this.repositorioIndices!=null &&
              this.repositorioIndices.equals(other.getRepositorioIndices()))) &&
            ((this.periodicidad==null && other.getPeriodicidad()==null) || 
             (this.periodicidad!=null &&
              this.periodicidad.equals(other.getPeriodicidad()))) &&
            ((this.msgEliminado==null && other.getMsgEliminado()==null) || 
             (this.msgEliminado!=null &&
              this.msgEliminado.equals(other.getMsgEliminado()))) &&
            ((this.msgNoEliminado==null && other.getMsgNoEliminado()==null) || 
             (this.msgNoEliminado!=null &&
              this.msgNoEliminado.equals(other.getMsgNoEliminado()))) &&
            ((this.msgDescripcionTrabajo==null && other.getMsgDescripcionTrabajo()==null) || 
             (this.msgDescripcionTrabajo!=null &&
              this.msgDescripcionTrabajo.equals(other.getMsgDescripcionTrabajo()))) &&
            ((this.grupoTrigger==null && other.getGrupoTrigger()==null) || 
             (this.grupoTrigger!=null &&
              this.grupoTrigger.equals(other.getGrupoTrigger()))) &&
            ((this.grupoTrabajo==null && other.getGrupoTrabajo()==null) || 
             (this.grupoTrabajo!=null &&
              this.grupoTrabajo.equals(other.getGrupoTrabajo()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.cron==null && other.getCron()==null) || 
             (this.cron!=null &&
              this.cron.equals(other.getCron()))) &&
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta())));
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
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getTrigger() != null) {
            _hashCode += getTrigger().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getRepositorioIndices() != null) {
            _hashCode += getRepositorioIndices().hashCode();
        }
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getMsgEliminado() != null) {
            _hashCode += getMsgEliminado().hashCode();
        }
        if (getMsgNoEliminado() != null) {
            _hashCode += getMsgNoEliminado().hashCode();
        }
        if (getMsgDescripcionTrabajo() != null) {
            _hashCode += getMsgDescripcionTrabajo().hashCode();
        }
        if (getGrupoTrigger() != null) {
            _hashCode += getGrupoTrigger().hashCode();
        }
        if (getGrupoTrabajo() != null) {
            _hashCode += getGrupoTrabajo().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getCron() != null) {
            _hashCode += getCron().hashCode();
        }
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaEliminaNoDisponiblesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trabajo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "usuario"));
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
        elemField.setFieldName("tipoTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tipoTarea"));
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
        elemField.setFieldName("periodicidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "periodicidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgEliminado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgEliminado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgNoEliminado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgNoEliminado"));
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
        elemField.setFieldName("grupoTrigger");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "grupoTrigger"));
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
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "error"));
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
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaHasta"));
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
