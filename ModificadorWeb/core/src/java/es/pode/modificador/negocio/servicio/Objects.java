/**
 * Objects.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class Objects  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.ODE[] objetos;

    private es.pode.modificador.negocio.servicio.Folder[] paths;

    public Objects() {
    }

    public Objects(
           es.pode.modificador.negocio.servicio.ODE[] objetos,
           es.pode.modificador.negocio.servicio.Folder[] paths) {
           this.objetos = objetos;
           this.paths = paths;
    }


    /**
     * Gets the objetos value for this Objects.
     * 
     * @return objetos
     */
    public es.pode.modificador.negocio.servicio.ODE[] getObjetos() {
        return objetos;
    }


    /**
     * Sets the objetos value for this Objects.
     * 
     * @param objetos
     */
    public void setObjetos(es.pode.modificador.negocio.servicio.ODE[] objetos) {
        this.objetos = objetos;
    }


    /**
     * Gets the paths value for this Objects.
     * 
     * @return paths
     */
    public es.pode.modificador.negocio.servicio.Folder[] getPaths() {
        return paths;
    }


    /**
     * Sets the paths value for this Objects.
     * 
     * @param paths
     */
    public void setPaths(es.pode.modificador.negocio.servicio.Folder[] paths) {
        this.paths = paths;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Objects)) return false;
        Objects other = (Objects) obj;
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
              java.util.Arrays.equals(this.objetos, other.getObjetos()))) &&
            ((this.paths==null && other.getPaths()==null) || 
             (this.paths!=null &&
              java.util.Arrays.equals(this.paths, other.getPaths())));
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
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjetos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjetos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaths() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaths());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaths(), i);
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
        new org.apache.axis.description.TypeDesc(Objects.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "Objects"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "objetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ODE"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paths");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "paths"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "Folder"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
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
