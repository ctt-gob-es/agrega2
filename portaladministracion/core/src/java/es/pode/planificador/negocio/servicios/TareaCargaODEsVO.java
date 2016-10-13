/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaCargaODEsVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaCargaODEsVO  implements java.io.Serializable {
    private java.lang.String trabajo;

    private java.lang.String grupoTrabajo;

    private java.lang.String trigger;

    private java.lang.String grupoTrigger;

    private java.lang.String cron;

    private java.util.Calendar fechaInicio;

    private java.lang.String periodicidad;

    private java.lang.String pathODE;

    private java.lang.String pathODEsCargados;

    private java.lang.String pathODEsNoCargados;

    private java.lang.String usuario;

    private java.lang.String tipoTarea;

    /* Mensaje cuando se publica un ODE */
    private java.lang.String msgPublicado;

    /* Mensaje cuando no se publica un ODE */
    private java.lang.String msgNoPublicado;

    private java.lang.String msgDescripcionTrabajo;

    /* Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error. */
    private java.lang.String error;

    private java.lang.String sobrescribir;

    /* Nombre del lote al que pertenecen los odes cargados */
    private java.lang.String nombreLote;

    /* Descripción que introduce el usuario para describir con más
     * detalle la tarea */
    private java.lang.String descripcionTarea;

    public TareaCargaODEsVO() {
    }

    public TareaCargaODEsVO(
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.lang.String trigger,
           java.lang.String grupoTrigger,
           java.lang.String cron,
           java.util.Calendar fechaInicio,
           java.lang.String periodicidad,
           java.lang.String pathODE,
           java.lang.String pathODEsCargados,
           java.lang.String pathODEsNoCargados,
           java.lang.String usuario,
           java.lang.String tipoTarea,
           java.lang.String msgPublicado,
           java.lang.String msgNoPublicado,
           java.lang.String msgDescripcionTrabajo,
           java.lang.String error,
           java.lang.String sobrescribir,
           java.lang.String nombreLote,
           java.lang.String descripcionTarea) {
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.trigger = trigger;
           this.grupoTrigger = grupoTrigger;
           this.cron = cron;
           this.fechaInicio = fechaInicio;
           this.periodicidad = periodicidad;
           this.pathODE = pathODE;
           this.pathODEsCargados = pathODEsCargados;
           this.pathODEsNoCargados = pathODEsNoCargados;
           this.usuario = usuario;
           this.tipoTarea = tipoTarea;
           this.msgPublicado = msgPublicado;
           this.msgNoPublicado = msgNoPublicado;
           this.msgDescripcionTrabajo = msgDescripcionTrabajo;
           this.error = error;
           this.sobrescribir = sobrescribir;
           this.nombreLote = nombreLote;
           this.descripcionTarea = descripcionTarea;
    }


    /**
     * Gets the trabajo value for this TareaCargaODEsVO.
     * 
     * @return trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaCargaODEsVO.
     * 
     * @param trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaCargaODEsVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaCargaODEsVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the trigger value for this TareaCargaODEsVO.
     * 
     * @return trigger
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaCargaODEsVO.
     * 
     * @param trigger
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the grupoTrigger value for this TareaCargaODEsVO.
     * 
     * @return grupoTrigger
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaCargaODEsVO.
     * 
     * @param grupoTrigger
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the cron value for this TareaCargaODEsVO.
     * 
     * @return cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaCargaODEsVO.
     * 
     * @param cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }


    /**
     * Gets the fechaInicio value for this TareaCargaODEsVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaCargaODEsVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the periodicidad value for this TareaCargaODEsVO.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaCargaODEsVO.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the pathODE value for this TareaCargaODEsVO.
     * 
     * @return pathODE
     */
    public java.lang.String getPathODE() {
        return pathODE;
    }


    /**
     * Sets the pathODE value for this TareaCargaODEsVO.
     * 
     * @param pathODE
     */
    public void setPathODE(java.lang.String pathODE) {
        this.pathODE = pathODE;
    }


    /**
     * Gets the pathODEsCargados value for this TareaCargaODEsVO.
     * 
     * @return pathODEsCargados
     */
    public java.lang.String getPathODEsCargados() {
        return pathODEsCargados;
    }


    /**
     * Sets the pathODEsCargados value for this TareaCargaODEsVO.
     * 
     * @param pathODEsCargados
     */
    public void setPathODEsCargados(java.lang.String pathODEsCargados) {
        this.pathODEsCargados = pathODEsCargados;
    }


    /**
     * Gets the pathODEsNoCargados value for this TareaCargaODEsVO.
     * 
     * @return pathODEsNoCargados
     */
    public java.lang.String getPathODEsNoCargados() {
        return pathODEsNoCargados;
    }


    /**
     * Sets the pathODEsNoCargados value for this TareaCargaODEsVO.
     * 
     * @param pathODEsNoCargados
     */
    public void setPathODEsNoCargados(java.lang.String pathODEsNoCargados) {
        this.pathODEsNoCargados = pathODEsNoCargados;
    }


    /**
     * Gets the usuario value for this TareaCargaODEsVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaCargaODEsVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the tipoTarea value for this TareaCargaODEsVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaCargaODEsVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the msgPublicado value for this TareaCargaODEsVO.
     * 
     * @return msgPublicado   * Mensaje cuando se publica un ODE
     */
    public java.lang.String getMsgPublicado() {
        return msgPublicado;
    }


    /**
     * Sets the msgPublicado value for this TareaCargaODEsVO.
     * 
     * @param msgPublicado   * Mensaje cuando se publica un ODE
     */
    public void setMsgPublicado(java.lang.String msgPublicado) {
        this.msgPublicado = msgPublicado;
    }


    /**
     * Gets the msgNoPublicado value for this TareaCargaODEsVO.
     * 
     * @return msgNoPublicado   * Mensaje cuando no se publica un ODE
     */
    public java.lang.String getMsgNoPublicado() {
        return msgNoPublicado;
    }


    /**
     * Sets the msgNoPublicado value for this TareaCargaODEsVO.
     * 
     * @param msgNoPublicado   * Mensaje cuando no se publica un ODE
     */
    public void setMsgNoPublicado(java.lang.String msgNoPublicado) {
        this.msgNoPublicado = msgNoPublicado;
    }


    /**
     * Gets the msgDescripcionTrabajo value for this TareaCargaODEsVO.
     * 
     * @return msgDescripcionTrabajo
     */
    public java.lang.String getMsgDescripcionTrabajo() {
        return msgDescripcionTrabajo;
    }


    /**
     * Sets the msgDescripcionTrabajo value for this TareaCargaODEsVO.
     * 
     * @param msgDescripcionTrabajo
     */
    public void setMsgDescripcionTrabajo(java.lang.String msgDescripcionTrabajo) {
        this.msgDescripcionTrabajo = msgDescripcionTrabajo;
    }


    /**
     * Gets the error value for this TareaCargaODEsVO.
     * 
     * @return error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TareaCargaODEsVO.
     * 
     * @param error   * Campo identificativo del error. Sólo tendrá algún valor si
     * se ha
     *                                 producido algún error.
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the sobrescribir value for this TareaCargaODEsVO.
     * 
     * @return sobrescribir
     */
    public java.lang.String getSobrescribir() {
        return sobrescribir;
    }


    /**
     * Sets the sobrescribir value for this TareaCargaODEsVO.
     * 
     * @param sobrescribir
     */
    public void setSobrescribir(java.lang.String sobrescribir) {
        this.sobrescribir = sobrescribir;
    }


    /**
     * Gets the nombreLote value for this TareaCargaODEsVO.
     * 
     * @return nombreLote   * Nombre del lote al que pertenecen los odes cargados
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this TareaCargaODEsVO.
     * 
     * @param nombreLote   * Nombre del lote al que pertenecen los odes cargados
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the descripcionTarea value for this TareaCargaODEsVO.
     * 
     * @return descripcionTarea   * Descripción que introduce el usuario para describir con más
     * detalle la tarea
     */
    public java.lang.String getDescripcionTarea() {
        return descripcionTarea;
    }


    /**
     * Sets the descripcionTarea value for this TareaCargaODEsVO.
     * 
     * @param descripcionTarea   * Descripción que introduce el usuario para describir con más
     * detalle la tarea
     */
    public void setDescripcionTarea(java.lang.String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaCargaODEsVO)) return false;
        TareaCargaODEsVO other = (TareaCargaODEsVO) obj;
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
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.periodicidad==null && other.getPeriodicidad()==null) || 
             (this.periodicidad!=null &&
              this.periodicidad.equals(other.getPeriodicidad()))) &&
            ((this.pathODE==null && other.getPathODE()==null) || 
             (this.pathODE!=null &&
              this.pathODE.equals(other.getPathODE()))) &&
            ((this.pathODEsCargados==null && other.getPathODEsCargados()==null) || 
             (this.pathODEsCargados!=null &&
              this.pathODEsCargados.equals(other.getPathODEsCargados()))) &&
            ((this.pathODEsNoCargados==null && other.getPathODEsNoCargados()==null) || 
             (this.pathODEsNoCargados!=null &&
              this.pathODEsNoCargados.equals(other.getPathODEsNoCargados()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.msgPublicado==null && other.getMsgPublicado()==null) || 
             (this.msgPublicado!=null &&
              this.msgPublicado.equals(other.getMsgPublicado()))) &&
            ((this.msgNoPublicado==null && other.getMsgNoPublicado()==null) || 
             (this.msgNoPublicado!=null &&
              this.msgNoPublicado.equals(other.getMsgNoPublicado()))) &&
            ((this.msgDescripcionTrabajo==null && other.getMsgDescripcionTrabajo()==null) || 
             (this.msgDescripcionTrabajo!=null &&
              this.msgDescripcionTrabajo.equals(other.getMsgDescripcionTrabajo()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.sobrescribir==null && other.getSobrescribir()==null) || 
             (this.sobrescribir!=null &&
              this.sobrescribir.equals(other.getSobrescribir()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.descripcionTarea==null && other.getDescripcionTarea()==null) || 
             (this.descripcionTarea!=null &&
              this.descripcionTarea.equals(other.getDescripcionTarea())));
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
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getPathODE() != null) {
            _hashCode += getPathODE().hashCode();
        }
        if (getPathODEsCargados() != null) {
            _hashCode += getPathODEsCargados().hashCode();
        }
        if (getPathODEsNoCargados() != null) {
            _hashCode += getPathODEsNoCargados().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getMsgPublicado() != null) {
            _hashCode += getMsgPublicado().hashCode();
        }
        if (getMsgNoPublicado() != null) {
            _hashCode += getMsgNoPublicado().hashCode();
        }
        if (getMsgDescripcionTrabajo() != null) {
            _hashCode += getMsgDescripcionTrabajo().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getSobrescribir() != null) {
            _hashCode += getSobrescribir().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getDescripcionTarea() != null) {
            _hashCode += getDescripcionTarea().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaCargaODEsVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"));
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
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodicidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "periodicidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathODEsCargados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathODEsCargados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathODEsNoCargados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathODEsNoCargados"));
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
        elemField.setFieldName("tipoTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tipoTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgPublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgPublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgNoPublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgNoPublicado"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sobrescribir");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "sobrescribir"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreLote");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreLote"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcionTarea"));
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
