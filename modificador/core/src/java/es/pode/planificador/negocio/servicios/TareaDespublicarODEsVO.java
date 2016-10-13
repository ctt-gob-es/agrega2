/**
 * TareaDespublicarODEsVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaDespublicarODEsVO  implements java.io.Serializable {
    private java.lang.String trabajo;

    private java.lang.String grupoTrabajo;

    private java.lang.String trigger;

    private java.lang.String grupoTrigger;

    private java.lang.String cron;

    private java.util.Calendar fechaInicio;

    private java.lang.String periodicidad;

    /* Campo en donde irán los identificadores leidos del fichero */
    private java.lang.String[] identificadores;

    private java.lang.String usuario;

    private java.lang.String tipoTarea;

    private java.lang.String msgDespublicado;

    private java.lang.String msgNoDespublicado;

    private java.lang.String msgDescripcion;

    private java.lang.String error;

    public TareaDespublicarODEsVO() {
    }

    public TareaDespublicarODEsVO(
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.lang.String trigger,
           java.lang.String grupoTrigger,
           java.lang.String cron,
           java.util.Calendar fechaInicio,
           java.lang.String periodicidad,
           java.lang.String[] identificadores,
           java.lang.String usuario,
           java.lang.String tipoTarea,
           java.lang.String msgDespublicado,
           java.lang.String msgNoDespublicado,
           java.lang.String msgDescripcion,
           java.lang.String error) {
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.trigger = trigger;
           this.grupoTrigger = grupoTrigger;
           this.cron = cron;
           this.fechaInicio = fechaInicio;
           this.periodicidad = periodicidad;
           this.identificadores = identificadores;
           this.usuario = usuario;
           this.tipoTarea = tipoTarea;
           this.msgDespublicado = msgDespublicado;
           this.msgNoDespublicado = msgNoDespublicado;
           this.msgDescripcion = msgDescripcion;
           this.error = error;
    }


    /**
     * Gets the trabajo value for this TareaDespublicarODEsVO.
     * 
     * @return trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaDespublicarODEsVO.
     * 
     * @param trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaDespublicarODEsVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaDespublicarODEsVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the trigger value for this TareaDespublicarODEsVO.
     * 
     * @return trigger
     */
    public java.lang.String getTrigger() {
        return trigger;
    }


    /**
     * Sets the trigger value for this TareaDespublicarODEsVO.
     * 
     * @param trigger
     */
    public void setTrigger(java.lang.String trigger) {
        this.trigger = trigger;
    }


    /**
     * Gets the grupoTrigger value for this TareaDespublicarODEsVO.
     * 
     * @return grupoTrigger
     */
    public java.lang.String getGrupoTrigger() {
        return grupoTrigger;
    }


    /**
     * Sets the grupoTrigger value for this TareaDespublicarODEsVO.
     * 
     * @param grupoTrigger
     */
    public void setGrupoTrigger(java.lang.String grupoTrigger) {
        this.grupoTrigger = grupoTrigger;
    }


    /**
     * Gets the cron value for this TareaDespublicarODEsVO.
     * 
     * @return cron
     */
    public java.lang.String getCron() {
        return cron;
    }


    /**
     * Sets the cron value for this TareaDespublicarODEsVO.
     * 
     * @param cron
     */
    public void setCron(java.lang.String cron) {
        this.cron = cron;
    }


    /**
     * Gets the fechaInicio value for this TareaDespublicarODEsVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaDespublicarODEsVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the periodicidad value for this TareaDespublicarODEsVO.
     * 
     * @return periodicidad
     */
    public java.lang.String getPeriodicidad() {
        return periodicidad;
    }


    /**
     * Sets the periodicidad value for this TareaDespublicarODEsVO.
     * 
     * @param periodicidad
     */
    public void setPeriodicidad(java.lang.String periodicidad) {
        this.periodicidad = periodicidad;
    }


    /**
     * Gets the identificadores value for this TareaDespublicarODEsVO.
     * 
     * @return identificadores   * Campo en donde irán los identificadores leidos del fichero
     */
    public java.lang.String[] getIdentificadores() {
        return identificadores;
    }


    /**
     * Sets the identificadores value for this TareaDespublicarODEsVO.
     * 
     * @param identificadores   * Campo en donde irán los identificadores leidos del fichero
     */
    public void setIdentificadores(java.lang.String[] identificadores) {
        this.identificadores = identificadores;
    }


    /**
     * Gets the usuario value for this TareaDespublicarODEsVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaDespublicarODEsVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the tipoTarea value for this TareaDespublicarODEsVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaDespublicarODEsVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the msgDespublicado value for this TareaDespublicarODEsVO.
     * 
     * @return msgDespublicado
     */
    public java.lang.String getMsgDespublicado() {
        return msgDespublicado;
    }


    /**
     * Sets the msgDespublicado value for this TareaDespublicarODEsVO.
     * 
     * @param msgDespublicado
     */
    public void setMsgDespublicado(java.lang.String msgDespublicado) {
        this.msgDespublicado = msgDespublicado;
    }


    /**
     * Gets the msgNoDespublicado value for this TareaDespublicarODEsVO.
     * 
     * @return msgNoDespublicado
     */
    public java.lang.String getMsgNoDespublicado() {
        return msgNoDespublicado;
    }


    /**
     * Sets the msgNoDespublicado value for this TareaDespublicarODEsVO.
     * 
     * @param msgNoDespublicado
     */
    public void setMsgNoDespublicado(java.lang.String msgNoDespublicado) {
        this.msgNoDespublicado = msgNoDespublicado;
    }


    /**
     * Gets the msgDescripcion value for this TareaDespublicarODEsVO.
     * 
     * @return msgDescripcion
     */
    public java.lang.String getMsgDescripcion() {
        return msgDescripcion;
    }


    /**
     * Sets the msgDescripcion value for this TareaDespublicarODEsVO.
     * 
     * @param msgDescripcion
     */
    public void setMsgDescripcion(java.lang.String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }


    /**
     * Gets the error value for this TareaDespublicarODEsVO.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TareaDespublicarODEsVO.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaDespublicarODEsVO)) return false;
        TareaDespublicarODEsVO other = (TareaDespublicarODEsVO) obj;
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
            ((this.identificadores==null && other.getIdentificadores()==null) || 
             (this.identificadores!=null &&
              java.util.Arrays.equals(this.identificadores, other.getIdentificadores()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.msgDespublicado==null && other.getMsgDespublicado()==null) || 
             (this.msgDespublicado!=null &&
              this.msgDespublicado.equals(other.getMsgDespublicado()))) &&
            ((this.msgNoDespublicado==null && other.getMsgNoDespublicado()==null) || 
             (this.msgNoDespublicado!=null &&
              this.msgNoDespublicado.equals(other.getMsgNoDespublicado()))) &&
            ((this.msgDescripcion==null && other.getMsgDescripcion()==null) || 
             (this.msgDescripcion!=null &&
              this.msgDescripcion.equals(other.getMsgDescripcion()))) &&
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
        if (getIdentificadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificadores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getMsgDespublicado() != null) {
            _hashCode += getMsgDespublicado().hashCode();
        }
        if (getMsgNoDespublicado() != null) {
            _hashCode += getMsgNoDespublicado().hashCode();
        }
        if (getMsgDescripcion() != null) {
            _hashCode += getMsgDescripcion().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaDespublicarODEsVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaDespublicarODEsVO"));
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
        elemField.setFieldName("identificadores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "identificadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
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
        elemField.setFieldName("msgDespublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgDespublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgNoDespublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgNoDespublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgDescripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "msgDescripcion"));
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
