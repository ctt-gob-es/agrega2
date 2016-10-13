/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TareaEjecutadaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaEjecutadaVO  implements java.io.Serializable {
    /* Descripcion de una tarea ejecutada (trabajo) */
    private java.lang.String descripcion;

    private java.lang.String trabajo;

    private java.lang.String grupoTrabajo;

    private java.util.Calendar fechaInicio;

    private java.lang.Long id;

    private java.util.Calendar fechaFin;

    private java.lang.String usuario;

    private java.lang.String estado;

    private java.lang.String fechaBaja;

    private java.lang.String descripcionTarea;

    private java.lang.String nombreLote;

    private java.lang.String pathCarga;

    private java.lang.String tipoTarea;

    private java.lang.String pathCargaOK;

    private java.lang.String pathCargaKO;

    private es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] registroTareaEjecutadaVOs;

    private es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] registroTareaCargaEjecutadaVOs;

    public TareaEjecutadaVO() {
    }

    public TareaEjecutadaVO(
           java.lang.String descripcion,
           java.lang.String trabajo,
           java.lang.String grupoTrabajo,
           java.util.Calendar fechaInicio,
           java.lang.Long id,
           java.util.Calendar fechaFin,
           java.lang.String usuario,
           java.lang.String estado,
           java.lang.String fechaBaja,
           java.lang.String descripcionTarea,
           java.lang.String nombreLote,
           java.lang.String pathCarga,
           java.lang.String tipoTarea,
           java.lang.String pathCargaOK,
           java.lang.String pathCargaKO,
           es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] registroTareaEjecutadaVOs,
           es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] registroTareaCargaEjecutadaVOs) {
           this.descripcion = descripcion;
           this.trabajo = trabajo;
           this.grupoTrabajo = grupoTrabajo;
           this.fechaInicio = fechaInicio;
           this.id = id;
           this.fechaFin = fechaFin;
           this.usuario = usuario;
           this.estado = estado;
           this.fechaBaja = fechaBaja;
           this.descripcionTarea = descripcionTarea;
           this.nombreLote = nombreLote;
           this.pathCarga = pathCarga;
           this.tipoTarea = tipoTarea;
           this.pathCargaOK = pathCargaOK;
           this.pathCargaKO = pathCargaKO;
           this.registroTareaEjecutadaVOs = registroTareaEjecutadaVOs;
           this.registroTareaCargaEjecutadaVOs = registroTareaCargaEjecutadaVOs;
    }


    /**
     * Gets the descripcion value for this TareaEjecutadaVO.
     * 
     * @return descripcion   * Descripcion de una tarea ejecutada (trabajo)
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this TareaEjecutadaVO.
     * 
     * @param descripcion   * Descripcion de una tarea ejecutada (trabajo)
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the trabajo value for this TareaEjecutadaVO.
     * 
     * @return trabajo
     */
    public java.lang.String getTrabajo() {
        return trabajo;
    }


    /**
     * Sets the trabajo value for this TareaEjecutadaVO.
     * 
     * @param trabajo
     */
    public void setTrabajo(java.lang.String trabajo) {
        this.trabajo = trabajo;
    }


    /**
     * Gets the grupoTrabajo value for this TareaEjecutadaVO.
     * 
     * @return grupoTrabajo
     */
    public java.lang.String getGrupoTrabajo() {
        return grupoTrabajo;
    }


    /**
     * Sets the grupoTrabajo value for this TareaEjecutadaVO.
     * 
     * @param grupoTrabajo
     */
    public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
        this.grupoTrabajo = grupoTrabajo;
    }


    /**
     * Gets the fechaInicio value for this TareaEjecutadaVO.
     * 
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this TareaEjecutadaVO.
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the id value for this TareaEjecutadaVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TareaEjecutadaVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the fechaFin value for this TareaEjecutadaVO.
     * 
     * @return fechaFin
     */
    public java.util.Calendar getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this TareaEjecutadaVO.
     * 
     * @param fechaFin
     */
    public void setFechaFin(java.util.Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }


    /**
     * Gets the usuario value for this TareaEjecutadaVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this TareaEjecutadaVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the estado value for this TareaEjecutadaVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this TareaEjecutadaVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechaBaja value for this TareaEjecutadaVO.
     * 
     * @return fechaBaja
     */
    public java.lang.String getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this TareaEjecutadaVO.
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(java.lang.String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the descripcionTarea value for this TareaEjecutadaVO.
     * 
     * @return descripcionTarea
     */
    public java.lang.String getDescripcionTarea() {
        return descripcionTarea;
    }


    /**
     * Sets the descripcionTarea value for this TareaEjecutadaVO.
     * 
     * @param descripcionTarea
     */
    public void setDescripcionTarea(java.lang.String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }


    /**
     * Gets the nombreLote value for this TareaEjecutadaVO.
     * 
     * @return nombreLote
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this TareaEjecutadaVO.
     * 
     * @param nombreLote
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the pathCarga value for this TareaEjecutadaVO.
     * 
     * @return pathCarga
     */
    public java.lang.String getPathCarga() {
        return pathCarga;
    }


    /**
     * Sets the pathCarga value for this TareaEjecutadaVO.
     * 
     * @param pathCarga
     */
    public void setPathCarga(java.lang.String pathCarga) {
        this.pathCarga = pathCarga;
    }


    /**
     * Gets the tipoTarea value for this TareaEjecutadaVO.
     * 
     * @return tipoTarea
     */
    public java.lang.String getTipoTarea() {
        return tipoTarea;
    }


    /**
     * Sets the tipoTarea value for this TareaEjecutadaVO.
     * 
     * @param tipoTarea
     */
    public void setTipoTarea(java.lang.String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }


    /**
     * Gets the pathCargaOK value for this TareaEjecutadaVO.
     * 
     * @return pathCargaOK
     */
    public java.lang.String getPathCargaOK() {
        return pathCargaOK;
    }


    /**
     * Sets the pathCargaOK value for this TareaEjecutadaVO.
     * 
     * @param pathCargaOK
     */
    public void setPathCargaOK(java.lang.String pathCargaOK) {
        this.pathCargaOK = pathCargaOK;
    }


    /**
     * Gets the pathCargaKO value for this TareaEjecutadaVO.
     * 
     * @return pathCargaKO
     */
    public java.lang.String getPathCargaKO() {
        return pathCargaKO;
    }


    /**
     * Sets the pathCargaKO value for this TareaEjecutadaVO.
     * 
     * @param pathCargaKO
     */
    public void setPathCargaKO(java.lang.String pathCargaKO) {
        this.pathCargaKO = pathCargaKO;
    }


    /**
     * Gets the registroTareaEjecutadaVOs value for this TareaEjecutadaVO.
     * 
     * @return registroTareaEjecutadaVOs
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] getRegistroTareaEjecutadaVOs() {
        return registroTareaEjecutadaVOs;
    }


    /**
     * Sets the registroTareaEjecutadaVOs value for this TareaEjecutadaVO.
     * 
     * @param registroTareaEjecutadaVOs
     */
    public void setRegistroTareaEjecutadaVOs(es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] registroTareaEjecutadaVOs) {
        this.registroTareaEjecutadaVOs = registroTareaEjecutadaVOs;
    }


    /**
     * Gets the registroTareaCargaEjecutadaVOs value for this TareaEjecutadaVO.
     * 
     * @return registroTareaCargaEjecutadaVOs
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] getRegistroTareaCargaEjecutadaVOs() {
        return registroTareaCargaEjecutadaVOs;
    }


    /**
     * Sets the registroTareaCargaEjecutadaVOs value for this TareaEjecutadaVO.
     * 
     * @param registroTareaCargaEjecutadaVOs
     */
    public void setRegistroTareaCargaEjecutadaVOs(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] registroTareaCargaEjecutadaVOs) {
        this.registroTareaCargaEjecutadaVOs = registroTareaCargaEjecutadaVOs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaEjecutadaVO)) return false;
        TareaEjecutadaVO other = (TareaEjecutadaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.trabajo==null && other.getTrabajo()==null) || 
             (this.trabajo!=null &&
              this.trabajo.equals(other.getTrabajo()))) &&
            ((this.grupoTrabajo==null && other.getGrupoTrabajo()==null) || 
             (this.grupoTrabajo!=null &&
              this.grupoTrabajo.equals(other.getGrupoTrabajo()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) || 
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fechaBaja==null && other.getFechaBaja()==null) || 
             (this.fechaBaja!=null &&
              this.fechaBaja.equals(other.getFechaBaja()))) &&
            ((this.descripcionTarea==null && other.getDescripcionTarea()==null) || 
             (this.descripcionTarea!=null &&
              this.descripcionTarea.equals(other.getDescripcionTarea()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.pathCarga==null && other.getPathCarga()==null) || 
             (this.pathCarga!=null &&
              this.pathCarga.equals(other.getPathCarga()))) &&
            ((this.tipoTarea==null && other.getTipoTarea()==null) || 
             (this.tipoTarea!=null &&
              this.tipoTarea.equals(other.getTipoTarea()))) &&
            ((this.pathCargaOK==null && other.getPathCargaOK()==null) || 
             (this.pathCargaOK!=null &&
              this.pathCargaOK.equals(other.getPathCargaOK()))) &&
            ((this.pathCargaKO==null && other.getPathCargaKO()==null) || 
             (this.pathCargaKO!=null &&
              this.pathCargaKO.equals(other.getPathCargaKO()))) &&
            ((this.registroTareaEjecutadaVOs==null && other.getRegistroTareaEjecutadaVOs()==null) || 
             (this.registroTareaEjecutadaVOs!=null &&
              java.util.Arrays.equals(this.registroTareaEjecutadaVOs, other.getRegistroTareaEjecutadaVOs()))) &&
            ((this.registroTareaCargaEjecutadaVOs==null && other.getRegistroTareaCargaEjecutadaVOs()==null) || 
             (this.registroTareaCargaEjecutadaVOs!=null &&
              java.util.Arrays.equals(this.registroTareaCargaEjecutadaVOs, other.getRegistroTareaCargaEjecutadaVOs())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTrabajo() != null) {
            _hashCode += getTrabajo().hashCode();
        }
        if (getGrupoTrabajo() != null) {
            _hashCode += getGrupoTrabajo().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getFechaFin() != null) {
            _hashCode += getFechaFin().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFechaBaja() != null) {
            _hashCode += getFechaBaja().hashCode();
        }
        if (getDescripcionTarea() != null) {
            _hashCode += getDescripcionTarea().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getPathCarga() != null) {
            _hashCode += getPathCarga().hashCode();
        }
        if (getTipoTarea() != null) {
            _hashCode += getTipoTarea().hashCode();
        }
        if (getPathCargaOK() != null) {
            _hashCode += getPathCargaOK().hashCode();
        }
        if (getPathCargaKO() != null) {
            _hashCode += getPathCargaKO().hashCode();
        }
        if (getRegistroTareaEjecutadaVOs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegistroTareaEjecutadaVOs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegistroTareaEjecutadaVOs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRegistroTareaCargaEjecutadaVOs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegistroTareaCargaEjecutadaVOs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegistroTareaCargaEjecutadaVOs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaEjecutadaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcion"));
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
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaFin"));
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
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcionTarea"));
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
        elemField.setFieldName("pathCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCarga"));
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
        elemField.setFieldName("pathCargaOK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCargaOK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathCargaKO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCargaKO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroTareaEjecutadaVOs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "registroTareaEjecutadaVOs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaEjecutadaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroTareaCargaEjecutadaVOs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "registroTareaCargaEjecutadaVOs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaCargaEjecutadaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
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
