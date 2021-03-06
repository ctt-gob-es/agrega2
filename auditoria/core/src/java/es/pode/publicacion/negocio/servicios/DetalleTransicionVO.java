/**
 * DetalleTransicionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;


/**
 * Este value object contiene la informacion de detalle que se
 *                         necesita para una transicion.
 */
public class DetalleTransicionVO  implements java.io.Serializable {
    /* Titulo del ODE. */
    private java.lang.String titulo;

    /* Fecha en la que se realizo la transicion. */
    private java.util.Calendar fechaTransicion;

    public DetalleTransicionVO() {
    }

    public DetalleTransicionVO(
           java.lang.String titulo,
           java.util.Calendar fechaTransicion) {
           this.titulo = titulo;
           this.fechaTransicion = fechaTransicion;
    }


    /**
     * Gets the titulo value for this DetalleTransicionVO.
     * 
     * @return titulo   * Titulo del ODE.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DetalleTransicionVO.
     * 
     * @param titulo   * Titulo del ODE.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the fechaTransicion value for this DetalleTransicionVO.
     * 
     * @return fechaTransicion   * Fecha en la que se realizo la transicion.
     */
    public java.util.Calendar getFechaTransicion() {
        return fechaTransicion;
    }


    /**
     * Sets the fechaTransicion value for this DetalleTransicionVO.
     * 
     * @param fechaTransicion   * Fecha en la que se realizo la transicion.
     */
    public void setFechaTransicion(java.util.Calendar fechaTransicion) {
        this.fechaTransicion = fechaTransicion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetalleTransicionVO)) return false;
        DetalleTransicionVO other = (DetalleTransicionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.fechaTransicion==null && other.getFechaTransicion()==null) || 
             (this.fechaTransicion!=null &&
              this.fechaTransicion.equals(other.getFechaTransicion())));
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getFechaTransicion() != null) {
            _hashCode += getFechaTransicion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DetalleTransicionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetalleTransicionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaTransicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "fechaTransicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
