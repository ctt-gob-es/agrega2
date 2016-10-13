/**
 * TareaEjecutadaExplotacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class TareaEjecutadaExplotacionVO  implements java.io.Serializable {
    private java.lang.String descripcionTarea;

    private java.util.Calendar fechaFin;

    private java.lang.Long id;

    private java.lang.String nombreLote;

    private java.lang.String pathCarga;

    private java.lang.String descripcion;

    /* Boolean que nos dice si existen carpetas */
    private java.lang.Boolean carpeta;

    /* Indica si la tarea de carga masiva tiene algún ode que no haya
     * sido despublicado */
    private java.lang.Boolean despublicado;

    public TareaEjecutadaExplotacionVO() {
    }

    public TareaEjecutadaExplotacionVO(
           java.lang.String descripcionTarea,
           java.util.Calendar fechaFin,
           java.lang.Long id,
           java.lang.String nombreLote,
           java.lang.String pathCarga,
           java.lang.String descripcion,
           java.lang.Boolean carpeta,
           java.lang.Boolean despublicado) {
           this.descripcionTarea = descripcionTarea;
           this.fechaFin = fechaFin;
           this.id = id;
           this.nombreLote = nombreLote;
           this.pathCarga = pathCarga;
           this.descripcion = descripcion;
           this.carpeta = carpeta;
           this.despublicado = despublicado;
    }


    /**
     * Gets the descripcionTarea value for this TareaEjecutadaExplotacionVO.
     * 
     * @return descripcionTarea
     */
    public java.lang.String getDescripcionTarea() {
        return descripcionTarea;
    }


    /**
     * Sets the descripcionTarea value for this TareaEjecutadaExplotacionVO.
     * 
     * @param descripcionTarea
     */
    public void setDescripcionTarea(java.lang.String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }


    /**
     * Gets the fechaFin value for this TareaEjecutadaExplotacionVO.
     * 
     * @return fechaFin
     */
    public java.util.Calendar getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this TareaEjecutadaExplotacionVO.
     * 
     * @param fechaFin
     */
    public void setFechaFin(java.util.Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }


    /**
     * Gets the id value for this TareaEjecutadaExplotacionVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TareaEjecutadaExplotacionVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombreLote value for this TareaEjecutadaExplotacionVO.
     * 
     * @return nombreLote
     */
    public java.lang.String getNombreLote() {
        return nombreLote;
    }


    /**
     * Sets the nombreLote value for this TareaEjecutadaExplotacionVO.
     * 
     * @param nombreLote
     */
    public void setNombreLote(java.lang.String nombreLote) {
        this.nombreLote = nombreLote;
    }


    /**
     * Gets the pathCarga value for this TareaEjecutadaExplotacionVO.
     * 
     * @return pathCarga
     */
    public java.lang.String getPathCarga() {
        return pathCarga;
    }


    /**
     * Sets the pathCarga value for this TareaEjecutadaExplotacionVO.
     * 
     * @param pathCarga
     */
    public void setPathCarga(java.lang.String pathCarga) {
        this.pathCarga = pathCarga;
    }


    /**
     * Gets the descripcion value for this TareaEjecutadaExplotacionVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this TareaEjecutadaExplotacionVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the carpeta value for this TareaEjecutadaExplotacionVO.
     * 
     * @return carpeta   * Boolean que nos dice si existen carpetas
     */
    public java.lang.Boolean getCarpeta() {
        return carpeta;
    }


    /**
     * Sets the carpeta value for this TareaEjecutadaExplotacionVO.
     * 
     * @param carpeta   * Boolean que nos dice si existen carpetas
     */
    public void setCarpeta(java.lang.Boolean carpeta) {
        this.carpeta = carpeta;
    }


    /**
     * Gets the despublicado value for this TareaEjecutadaExplotacionVO.
     * 
     * @return despublicado   * Indica si la tarea de carga masiva tiene algún ode que no haya
     * sido despublicado
     */
    public java.lang.Boolean getDespublicado() {
        return despublicado;
    }


    /**
     * Sets the despublicado value for this TareaEjecutadaExplotacionVO.
     * 
     * @param despublicado   * Indica si la tarea de carga masiva tiene algún ode que no haya
     * sido despublicado
     */
    public void setDespublicado(java.lang.Boolean despublicado) {
        this.despublicado = despublicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TareaEjecutadaExplotacionVO)) return false;
        TareaEjecutadaExplotacionVO other = (TareaEjecutadaExplotacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionTarea==null && other.getDescripcionTarea()==null) || 
             (this.descripcionTarea!=null &&
              this.descripcionTarea.equals(other.getDescripcionTarea()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) || 
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombreLote==null && other.getNombreLote()==null) || 
             (this.nombreLote!=null &&
              this.nombreLote.equals(other.getNombreLote()))) &&
            ((this.pathCarga==null && other.getPathCarga()==null) || 
             (this.pathCarga!=null &&
              this.pathCarga.equals(other.getPathCarga()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.carpeta==null && other.getCarpeta()==null) || 
             (this.carpeta!=null &&
              this.carpeta.equals(other.getCarpeta()))) &&
            ((this.despublicado==null && other.getDespublicado()==null) || 
             (this.despublicado!=null &&
              this.despublicado.equals(other.getDespublicado())));
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
        if (getDescripcionTarea() != null) {
            _hashCode += getDescripcionTarea().hashCode();
        }
        if (getFechaFin() != null) {
            _hashCode += getFechaFin().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombreLote() != null) {
            _hashCode += getNombreLote().hashCode();
        }
        if (getPathCarga() != null) {
            _hashCode += getPathCarga().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getCarpeta() != null) {
            _hashCode += getCarpeta().hashCode();
        }
        if (getDespublicado() != null) {
            _hashCode += getDespublicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TareaEjecutadaExplotacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaExplotacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionTarea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcionTarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaFin"));
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
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carpeta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "carpeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("despublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "despublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
