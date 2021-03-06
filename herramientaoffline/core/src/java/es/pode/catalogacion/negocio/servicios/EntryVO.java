/**
 * EntryVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class EntryVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.LangStringVO[] textos;

    public EntryVO() {
    }

    public EntryVO(
           es.pode.catalogacion.negocio.servicios.LangStringVO[] textos) {
           this.textos = textos;
    }


    /**
     * Gets the textos value for this EntryVO.
     * 
     * @return textos
     */
    public es.pode.catalogacion.negocio.servicios.LangStringVO[] getTextos() {
        return textos;
    }


    /**
     * Sets the textos value for this EntryVO.
     * 
     * @param textos
     */
    public void setTextos(es.pode.catalogacion.negocio.servicios.LangStringVO[] textos) {
        this.textos = textos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntryVO)) return false;
        EntryVO other = (EntryVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.textos==null && other.getTextos()==null) || 
             (this.textos!=null &&
              java.util.Arrays.equals(this.textos, other.getTextos())));
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
        if (getTextos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTextos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTextos(), i);
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
        new org.apache.axis.description.TypeDesc(EntryVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntryVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "textos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LangStringVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
