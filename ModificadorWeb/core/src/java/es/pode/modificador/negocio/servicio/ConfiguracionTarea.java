/**
 * ConfiguracionTarea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class ConfiguracionTarea  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.Objects objetos;

    private es.pode.modificador.negocio.servicio.Changes cambios;

    public ConfiguracionTarea() {
    }

    public ConfiguracionTarea(
           es.pode.modificador.negocio.servicio.Objects objetos,
           es.pode.modificador.negocio.servicio.Changes cambios) {
           this.objetos = objetos;
           this.cambios = cambios;
    }


    /**
     * Gets the objetos value for this ConfiguracionTarea.
     * 
     * @return objetos
     */
    public es.pode.modificador.negocio.servicio.Objects getObjetos() {
        return objetos;
    }


    /**
     * Sets the objetos value for this ConfiguracionTarea.
     * 
     * @param objetos
     */
    public void setObjetos(es.pode.modificador.negocio.servicio.Objects objetos) {
        this.objetos = objetos;
    }


    /**
     * Gets the cambios value for this ConfiguracionTarea.
     * 
     * @return cambios
     */
    public es.pode.modificador.negocio.servicio.Changes getCambios() {
        return cambios;
    }


    /**
     * Sets the cambios value for this ConfiguracionTarea.
     * 
     * @param cambios
     */
    public void setCambios(es.pode.modificador.negocio.servicio.Changes cambios) {
        this.cambios = cambios;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfiguracionTarea)) return false;
        ConfiguracionTarea other = (ConfiguracionTarea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objetos==null && other.getObjetos()==null) || 
             (this.objetos!=null &&
              this.objetos.equals(other.getObjetos()))) &&
            ((this.cambios==null && other.getCambios()==null) || 
             (this.cambios!=null &&
              this.cambios.equals(other.getCambios())));
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
        if (getObjetos() != null) {
            _hashCode += getObjetos().hashCode();
        }
        if (getCambios() != null) {
            _hashCode += getCambios().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfiguracionTarea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ConfiguracionTarea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "objetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "Objects"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cambios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "cambios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "Changes"));
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
