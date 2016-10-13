/**
 * EntidadVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;


/**
 * Persona y/o organización que ha contribuido en el ODE.
 */
public class EntidadVO  implements java.io.Serializable {
    /* Nombre del contribuidor */
    private java.lang.String nombre;

    /* Organización a la que pertenece el contribuidor */
    private java.lang.String organizacion;

    public EntidadVO() {
    }

    public EntidadVO(
           java.lang.String nombre,
           java.lang.String organizacion) {
           this.nombre = nombre;
           this.organizacion = organizacion;
    }


    /**
     * Gets the nombre value for this EntidadVO.
     * 
     * @return nombre   * Nombre del contribuidor
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this EntidadVO.
     * 
     * @param nombre   * Nombre del contribuidor
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the organizacion value for this EntidadVO.
     * 
     * @return organizacion   * Organización a la que pertenece el contribuidor
     */
    public java.lang.String getOrganizacion() {
        return organizacion;
    }


    /**
     * Sets the organizacion value for this EntidadVO.
     * 
     * @param organizacion   * Organización a la que pertenece el contribuidor
     */
    public void setOrganizacion(java.lang.String organizacion) {
        this.organizacion = organizacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntidadVO)) return false;
        EntidadVO other = (EntidadVO) obj;
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
            ((this.organizacion==null && other.getOrganizacion()==null) || 
             (this.organizacion!=null &&
              this.organizacion.equals(other.getOrganizacion())));
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
        if (getOrganizacion() != null) {
            _hashCode += getOrganizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntidadVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "EntidadVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "organizacion"));
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
