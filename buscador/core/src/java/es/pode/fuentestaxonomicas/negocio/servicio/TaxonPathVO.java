/**
 * TaxonPathVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class TaxonPathVO  implements java.io.Serializable {
    private es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] datos;

    public TaxonPathVO() {
    }

    public TaxonPathVO(
           es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] datos) {
           this.datos = datos;
    }


    /**
     * Gets the datos value for this TaxonPathVO.
     * 
     * @return datos
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] getDatos() {
        return datos;
    }


    /**
     * Sets the datos value for this TaxonPathVO.
     * 
     * @param datos
     */
    public void setDatos(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] datos) {
        this.datos = datos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxonPathVO)) return false;
        TaxonPathVO other = (TaxonPathVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datos==null && other.getDatos()==null) || 
             (this.datos!=null &&
              java.util.Arrays.equals(this.datos, other.getDatos())));
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
        if (getDatos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatos(), i);
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
        new org.apache.axis.description.TypeDesc(TaxonPathVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonPathVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "datos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
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
