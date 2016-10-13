/**
 * NivelAgregacionPublicacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public class NivelAgregacionPublicacion  implements java.io.Serializable {
    /* Fecha en la que se publicó el ode */
    private java.util.Calendar fechaPublicacion;

    private es.pode.auditoria.negocio.servicios.NivelAgregacionContenido[] nivelAgregacionContenidoVO;

    public NivelAgregacionPublicacion() {
    }

    public NivelAgregacionPublicacion(
           java.util.Calendar fechaPublicacion,
           es.pode.auditoria.negocio.servicios.NivelAgregacionContenido[] nivelAgregacionContenidoVO) {
           this.fechaPublicacion = fechaPublicacion;
           this.nivelAgregacionContenidoVO = nivelAgregacionContenidoVO;
    }


    /**
     * Gets the fechaPublicacion value for this NivelAgregacionPublicacion.
     * 
     * @return fechaPublicacion   * Fecha en la que se publicó el ode
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this NivelAgregacionPublicacion.
     * 
     * @param fechaPublicacion   * Fecha en la que se publicó el ode
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the nivelAgregacionContenidoVO value for this NivelAgregacionPublicacion.
     * 
     * @return nivelAgregacionContenidoVO
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionContenido[] getNivelAgregacionContenidoVO() {
        return nivelAgregacionContenidoVO;
    }


    /**
     * Sets the nivelAgregacionContenidoVO value for this NivelAgregacionPublicacion.
     * 
     * @param nivelAgregacionContenidoVO
     */
    public void setNivelAgregacionContenidoVO(es.pode.auditoria.negocio.servicios.NivelAgregacionContenido[] nivelAgregacionContenidoVO) {
        this.nivelAgregacionContenidoVO = nivelAgregacionContenidoVO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NivelAgregacionPublicacion)) return false;
        NivelAgregacionPublicacion other = (NivelAgregacionPublicacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.nivelAgregacionContenidoVO==null && other.getNivelAgregacionContenidoVO()==null) || 
             (this.nivelAgregacionContenidoVO!=null &&
              java.util.Arrays.equals(this.nivelAgregacionContenidoVO, other.getNivelAgregacionContenidoVO())));
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
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getNivelAgregacionContenidoVO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNivelAgregacionContenidoVO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNivelAgregacionContenidoVO(), i);
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
        new org.apache.axis.description.TypeDesc(NivelAgregacionPublicacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "NivelAgregacionPublicacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacionContenidoVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivelAgregacionContenidoVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "NivelAgregacionContenido"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "item"));
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
