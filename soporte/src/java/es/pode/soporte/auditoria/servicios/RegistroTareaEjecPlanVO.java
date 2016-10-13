/**
 * RegistroTareaEjecPlanVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;

public class RegistroTareaEjecPlanVO  implements java.io.Serializable {
    /* Descripción de la subtarea del trabajo ejecutado */
    private java.lang.String descripcion;

    /* Fecha de ejecución de la subtarea ejecutada */
    private java.util.Calendar fecha;

    /* Identificador de la subtarea */
    private java.lang.Long id;

    /* Estado de la subtarea ejecutada */
    private java.lang.String estado;

    /* Codigo de respuesta de la validación del ODE */
    private java.lang.String codigo;

    public RegistroTareaEjecPlanVO() {
    }

    public RegistroTareaEjecPlanVO(
           java.lang.String descripcion,
           java.util.Calendar fecha,
           java.lang.Long id,
           java.lang.String estado,
           java.lang.String codigo) {
           this.descripcion = descripcion;
           this.fecha = fecha;
           this.id = id;
           this.estado = estado;
           this.codigo = codigo;
    }


    /**
     * Gets the descripcion value for this RegistroTareaEjecPlanVO.
     * 
     * @return descripcion   * Descripción de la subtarea del trabajo ejecutado
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this RegistroTareaEjecPlanVO.
     * 
     * @param descripcion   * Descripción de la subtarea del trabajo ejecutado
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fecha value for this RegistroTareaEjecPlanVO.
     * 
     * @return fecha   * Fecha de ejecución de la subtarea ejecutada
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this RegistroTareaEjecPlanVO.
     * 
     * @param fecha   * Fecha de ejecución de la subtarea ejecutada
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the id value for this RegistroTareaEjecPlanVO.
     * 
     * @return id   * Identificador de la subtarea
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this RegistroTareaEjecPlanVO.
     * 
     * @param id   * Identificador de la subtarea
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the estado value for this RegistroTareaEjecPlanVO.
     * 
     * @return estado   * Estado de la subtarea ejecutada
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RegistroTareaEjecPlanVO.
     * 
     * @param estado   * Estado de la subtarea ejecutada
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the codigo value for this RegistroTareaEjecPlanVO.
     * 
     * @return codigo   * Codigo de respuesta de la validación del ODE
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this RegistroTareaEjecPlanVO.
     * 
     * @param codigo   * Codigo de respuesta de la validación del ODE
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistroTareaEjecPlanVO)) return false;
        RegistroTareaEjecPlanVO other = (RegistroTareaEjecPlanVO) obj;
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
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo())));
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
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistroTareaEjecPlanVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "RegistroTareaEjecPlanVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "codigo"));
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
