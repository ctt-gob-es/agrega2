/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaInformesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaInformesVO  implements java.io.Serializable {
    /* Nombre de la tarea creada */
    private java.lang.String trabajo;

    /* Nombre del grupo del trabajo si viene a nulo se le asigna uno
     * por defecto */
    private java.lang.String grupoTrabajo;

    /* Nombre del disparador */
    private java.lang.String trigger;

    /* Nombre del grupo del disparador. Si viene a nulo se le asigna
     * uno por defecto. */
    private java.lang.String grupoTrigger;

    /* Cron */
    private java.lang.String cron;

    /* Fecha de inicio de la tarea */
    private java.util.Calendar fechaInicio;

    /* Periodicidad */
    private java.lang.String periodicidad;

    /* Usuario que crea la tarea */
    private java.lang.String usuario;

    /* Identificador de la tarea */
    private java.lang.String tipoTarea;

    /* Mensaje de la generaci贸n del informe */
    private java.lang.String msgInforme;

    /* Mensaje de no generaci贸n del informe */
    private java.lang.String msgNoInforme;

    /* Mensaje de descripci贸n de la generaci贸n del informe */
    private java.lang.String msgDescripcionTrabajo;

    /* Identificador del tipo de informe */
    private java.lang.String informe;

    /* Fecha de inico del informe */
    private java.util.Calendar fechaDesde;

    /* Fecha de finalizaci贸n del informe */
    private java.util.Calendar fechaHasta;

    /* Algunos informes se muestran por usuario */
    private java.lang.String usuarioInforme;

    /* Algunos informes se les especifica un rango */
    private java.lang.Integer rango;

    /* Formato de salida del informe */
    private java.lang.String formato;

    /* Error */
    private java.lang.String error;

    public TareaInformesVO() {
    }

    public TareaInformesVO(
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.lang.String trigger,
           java.lang.String grupoTrigger,
           java.lang.String cron,
           java.util.Calendar fechaInicio,
           java.lang.String periodicidad,
           java.lang.String usuario,
           java.lang.String tipoTarea,
           java.lang.String msgInforme,
           java.lang.String msgNoInforme,
           java.lang.String msgDescripcionTrabajo,
           java.lang.String informe,
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.String usuarioInforme,
           java.lang.Integer rango,
           java.lang.String formato,
           java.lang.String error) {
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.trigger = trigger;
           this.grupoTrigger = grupoTrigger;
           this.cron = cron;
           this.fechaInicio = fechaInicio;
           this.periodicidad = periodicidad;
           this.usuario = usuario;
           this.tipoTarea = tipoTarea;
           this.msgInforme = msgInforme;
           this.msgNoInforme = msgNoInforme;
           this.msgDescripcionTrabajo = msgDescripcionTrabajo;
           this.informe = informe;
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.usuarioInforme = usuarioInforme;
           this.rango = rango;
           this.formato = formato;
           this.error = error;
    }


    /**
     * Gets the trabajo value for this TareaInformesVO.
     * 
     * @return trabajo   * Nombre de la tarea creada
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaInformesVO.
     * 
     * @param trabajo   * Nombre de la tarea creada
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaInformesVO.
     * 
     * @return grupoTrabajo   * Nombre del grupo del trabajo si viene a nulo se le asigna uno
     * por defecto
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaInformesVO.
     * 
     * @param grupoTrabajo   * Nombre del grupo del trabajo si viene a nulo se le asigna uno
     * por defecto
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the trigger value for this TareaInformesVO.
     * 
     * @return trigger   * Nombre del disparador
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaInformesVO.
     * 
     * @param trigger   * Nombre del disparador
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the grupoTrigger value for this TareaInformesVO.
     * 
     * @return grupoTrigger   * Nombre del grupo del disparador. Si viene a nulo se le asigna
     * uno por defecto.
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaInformesVO.
     * 
     * @param grupoTrigger   * Nombre del grupo del disparador. Si viene a nulo se le asigna
     * uno por defecto.
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the cron value for this TareaInformesVO.
     * 
     * @return cron   * Cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaInformesVO.
     * 
     * @param cron   * Cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }


    /**
     * Gets the fechaInicio value for this TareaInformesVO.
     * 
     * @return fechaInicio   * Fecha de inicio de la tarea
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaInformesVO.
     * 
     * @param fechaInicio   * Fecha de inicio de la tarea
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the periodicidad value for this TareaInformesVO.
     * 
     * @return periodicidad   * Periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaInformesVO.
     * 
     * @param periodicidad   * Periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the usuario value for this TareaInformesVO.
     * 
     * @return usuario   * Usuario que crea la tarea
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaInformesVO.
     * 
     * @param usuario   * Usuario que crea la tarea
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the tipoTarea value for this TareaInformesVO.
     * 
     * @return tipoTarea   * Identificador de la tarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaInformesVO.
     * 
     * @param tipoTarea   * Identificador de la tarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the msgInforme value for this TareaInformesVO.
     * 
     * @return msgInforme   * Mensaje de la generaci贸n del informe
     */
    public java.lang.String getMsgInforme() {
        return msgInforme;
    }


    /**
     * Sets the msgInforme value for this TareaInformesVO.
     * 
     * @param msgInforme   * Mensaje de la generaci贸n del informe
     */
    public void setMsgInforme(java.lang.String msgInforme) {
        this.msgInforme = msgInforme;
    }


    /**
     * Gets the msgNoInforme value for this TareaInformesVO.
     * 
     * @return msgNoInforme   * Mensaje de no generaci贸n del informe
     */
    public java.lang.String getMsgNoInforme() {
        return msgNoInforme;
    }


    /**
     * Sets the msgNoInforme value for this TareaInformesVO.
     * 
     * @param msgNoInforme   * Mensaje de no generaci贸n del informe
     */
    public void setMsgNoInforme(java.lang.String msgNoInforme) {
        this.msgNoInforme = msgNoInforme;
    }


    /**
     * Gets the msgDescripcionTrabajo value for this TareaInformesVO.
     * 
     * @return msgDescripcionTrabajo   * Mensaje de descripci贸n de la generaci贸n del informe
     */
    public java.lang.String getMsgDescripcionTrabajo() {
        return msgDescripcionTrabajo;
    }


    /**
     * Sets the msgDescripcionTrabajo value for this TareaInformesVO.
     * 
     * @param msgDescripcionTrabajo   * Mensaje de descripci贸n de la generaci贸n del informe
     */
    public void setMsgDescripcionTrabajo(java.lang.String msgDescripcionTrabajo) {
        this.msgDescripcionTrabajo = msgDescripcionTrabajo;
    }


    /**
     * Gets the informe value for this TareaInformesVO.
     * 
     * @return informe   * Identificador del tipo de informe
     */
    public java.lang.String getInforme() {
        return informe;
    }


    /**
     * Sets the informe value for this TareaInformesVO.
     * 
     * @param informe   * Identificador del tipo de informe
     */
    public void setInforme(java.lang.String informe) {
        this.informe = informe;
    }


    /**
     * Gets the fechaDesde value for this TareaInformesVO.
     * 
     * @return fechaDesde   * Fecha de inico del informe
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this TareaInformesVO.
     * 
     * @param fechaDesde   * Fecha de inico del informe
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this TareaInformesVO.
     * 
     * @return fechaHasta   * Fecha de finalizaci贸n del informe
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this TareaInformesVO.
     * 
     * @param fechaHasta   * Fecha de finalizaci贸n del informe
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the usuarioInforme value for this TareaInformesVO.
     * 
     * @return usuarioInforme   * Algunos informes se muestran por usuario
     */
    public java.lang.String getUsuarioInforme() {
        return usuarioInforme;
    }


    /**
     * Sets the usuarioInforme value for this TareaInformesVO.
     * 
     * @param usuarioInforme   * Algunos informes se muestran por usuario
     */
    public void setUsuarioInforme(java.lang.String usuarioInforme) {
        this.usuarioInforme = usuarioInforme;
    }


    /**
     * Gets the rango value for this TareaInformesVO.
     * 
     * @return rango   * Algunos informes se les especifica un rango
     */
    public java.lang.Integer getRango() {
        return rango;
    }


    /**
     * Sets the rango value for this TareaInformesVO.
     * 
     * @param rango   * Algunos informes se les especifica un rango
     */
    public void setRango(java.lang.Integer rango) {
        this.rango = rango;
    }


    /**
     * Gets the formato value for this TareaInformesVO.
     * 
     * @return formato   * Formato de salida del informe
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this TareaInformesVO.
     * 
     * @param formato   * Formato de salida del informe
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the error value for this TareaInformesVO.
     * 
     * @return error   * Error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TareaInformesVO.
     * 
     * @param error   * Error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaInformesVO)) return false;
        TareaInformesVO other = (TareaInformesVO) obj;
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
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.msgInforme==null && other.getMsgInforme()==null) || 
             (this.msgInforme!=null &&
              this.msgInforme.equals(other.getMsgInforme()))) &&
            ((this.msgNoInforme==null && other.getMsgNoInforme()==null) || 
             (this.msgNoInforme!=null &&
              this.msgNoInforme.equals(other.getMsgNoInforme()))) &&
            ((this.msgDescripcionTrabajo==null && other.getMsgDescripcionTrabajo()==null) || 
             (this.msgDescripcionTrabajo!=null &&
              this.msgDescripcionTrabajo.equals(other.getMsgDescripcionTrabajo()))) &&
            ((this.informe==null && other.getInforme()==null) || 
             (this.informe!=null &&
              this.informe.equals(other.getInforme()))) &&
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.usuarioInforme==null && other.getUsuarioInforme()==null) || 
             (this.usuarioInforme!=null &&
              this.usuarioInforme.equals(other.getUsuarioInforme()))) &&
            ((this.rango==null && other.getRango()==null) || 
             (this.rango!=null &&
              this.rango.equals(other.getRango()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
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
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getPeriodicidad() != null) {
            _hashCode += getPeriodicidad().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getMsgInforme() != null) {
            _hashCode += getMsgInforme().hashCode();
        }
        if (getMsgNoInforme() != null) {
            _hashCode += getMsgNoInforme().hashCode();
        }
        if (getMsgDescripcionTrabajo() != null) {
            _hashCode += getMsgDescripcionTrabajo().hashCode();
        }
        if (getInforme() != null) {
            _hashCode += getInforme().hashCode();
        }
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getUsuarioInforme() != null) {
            _hashCode += getUsuarioInforme().hashCode();
        }
        if (getRango() != null) {
            _hashCode += getRango().hashCode();
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaInformesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
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
        elemField.setFieldName("msgInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgNoInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgNoInforme"));
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
        elemField.setFieldName("informe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "informe"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "usuarioInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rango");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "rango"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "formato"));
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
