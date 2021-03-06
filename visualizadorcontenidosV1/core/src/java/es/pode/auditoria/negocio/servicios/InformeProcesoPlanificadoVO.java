/**
 * InformeProcesoPlanificadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public class InformeProcesoPlanificadoVO  implements java.io.Serializable {
    private java.lang.String nombre;

    private java.lang.String estado;

    /* Fecha de inicio de la tarea */
    private java.lang.String fechaInicio;

    /* Fecha de finalización de la tarea */
    private java.lang.String fechaFin;

    public InformeProcesoPlanificadoVO() {
    }

    public InformeProcesoPlanificadoVO(
           java.lang.String nombre,
           java.lang.String estado,
           java.lang.String fechaInicio,
           java.lang.String fechaFin) {
           this.nombre = nombre;
           this.estado = estado;
           this.fechaInicio = fechaInicio;
           this.fechaFin = fechaFin;
    }


    /**
     * Gets the nombre value for this InformeProcesoPlanificadoVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this InformeProcesoPlanificadoVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the estado value for this InformeProcesoPlanificadoVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this InformeProcesoPlanificadoVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechaInicio value for this InformeProcesoPlanificadoVO.
     * 
     * @return fechaInicio   * Fecha de inicio de la tarea
     */
    public java.lang.String getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this InformeProcesoPlanificadoVO.
     * 
     * @param fechaInicio   * Fecha de inicio de la tarea
     */
    public void setFechaInicio(java.lang.String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the fechaFin value for this InformeProcesoPlanificadoVO.
     * 
     * @return fechaFin   * Fecha de finalización de la tarea
     */
    public java.lang.String getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this InformeProcesoPlanificadoVO.
     * 
     * @param fechaFin   * Fecha de finalización de la tarea
     */
    public void setFechaFin(java.lang.String fechaFin) {
        this.fechaFin = fechaFin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeProcesoPlanificadoVO)) return false;
        InformeProcesoPlanificadoVO other = (InformeProcesoPlanificadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) || 
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) || 
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        if (getFechaFin() != null) {
            _hashCode += getFechaFin().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeProcesoPlanificadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeProcesoPlanificadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaFin"));
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
