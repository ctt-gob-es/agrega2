/**
 * LocalizacionIdiomaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public class LocalizacionIdiomaVO  implements java.io.Serializable {
    /* Nombre del idioma traducido al idioma general. */
    private java.lang.String nombre;

    /* Identificador del localizador de idioma. */
    private java.lang.String idLocalizacion;

    public LocalizacionIdiomaVO() {
    }

    public LocalizacionIdiomaVO(
           java.lang.String nombre,
           java.lang.String idLocalizacion) {
           this.nombre = nombre;
           this.idLocalizacion = idLocalizacion;
    }


    /**
     * Gets the nombre value for this LocalizacionIdiomaVO.
     * 
     * @return nombre   * Nombre del idioma traducido al idioma general.
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this LocalizacionIdiomaVO.
     * 
     * @param nombre   * Nombre del idioma traducido al idioma general.
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the idLocalizacion value for this LocalizacionIdiomaVO.
     * 
     * @return idLocalizacion   * Identificador del localizador de idioma.
     */
    public java.lang.String getIdLocalizacion() {
        return idLocalizacion;
    }


    /**
     * Sets the idLocalizacion value for this LocalizacionIdiomaVO.
     * 
     * @param idLocalizacion   * Identificador del localizador de idioma.
     */
    public void setIdLocalizacion(java.lang.String idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LocalizacionIdiomaVO)) return false;
        LocalizacionIdiomaVO other = (LocalizacionIdiomaVO) obj;
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
            ((this.idLocalizacion==null && other.getIdLocalizacion()==null) || 
             (this.idLocalizacion!=null &&
              this.idLocalizacion.equals(other.getIdLocalizacion())));
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
        if (getIdLocalizacion() != null) {
            _hashCode += getIdLocalizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalizacionIdiomaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "LocalizacionIdiomaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLocalizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idLocalizacion"));
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
