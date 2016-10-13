/**
 * AnalizaArchivoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public class AnalizaArchivoVO  implements java.io.Serializable {
    private java.lang.String tipoArchivo;

    private java.lang.String[] mensajes;

    public AnalizaArchivoVO() {
    }

    public AnalizaArchivoVO(
           java.lang.String tipoArchivo,
           java.lang.String[] mensajes) {
           this.tipoArchivo = tipoArchivo;
           this.mensajes = mensajes;
    }


    /**
     * Gets the tipoArchivo value for this AnalizaArchivoVO.
     * 
     * @return tipoArchivo
     */
    public java.lang.String getTipoArchivo() {
        return tipoArchivo;
    }


    /**
     * Sets the tipoArchivo value for this AnalizaArchivoVO.
     * 
     * @param tipoArchivo
     */
    public void setTipoArchivo(java.lang.String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }


    /**
     * Gets the mensajes value for this AnalizaArchivoVO.
     * 
     * @return mensajes
     */
    public java.lang.String[] getMensajes() {
        return mensajes;
    }


    /**
     * Sets the mensajes value for this AnalizaArchivoVO.
     * 
     * @param mensajes
     */
    public void setMensajes(java.lang.String[] mensajes) {
        this.mensajes = mensajes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AnalizaArchivoVO)) return false;
        AnalizaArchivoVO other = (AnalizaArchivoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoArchivo==null && other.getTipoArchivo()==null) || 
             (this.tipoArchivo!=null &&
              this.tipoArchivo.equals(other.getTipoArchivo()))) &&
            ((this.mensajes==null && other.getMensajes()==null) || 
             (this.mensajes!=null &&
              java.util.Arrays.equals(this.mensajes, other.getMensajes())));
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
        if (getTipoArchivo() != null) {
            _hashCode += getTipoArchivo().hashCode();
        }
        if (getMensajes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMensajes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMensajes(), i);
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
        new org.apache.axis.description.TypeDesc(AnalizaArchivoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "AnalizaArchivoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoArchivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "tipoArchivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "mensajes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
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
