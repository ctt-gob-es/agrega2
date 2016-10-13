/**
 * PrioridadPalabrasClaveVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;


/**
 * VO que alberga un listado de PalabraClaveVO.
 */
public class PrioridadPalabrasClaveVO  implements java.io.Serializable {
    private es.pode.soporte.buscador.servicios.PalabraClaveVO[] palabrasClave;

    public PrioridadPalabrasClaveVO() {
    }

    public PrioridadPalabrasClaveVO(
           es.pode.soporte.buscador.servicios.PalabraClaveVO[] palabrasClave) {
           this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the palabrasClave value for this PrioridadPalabrasClaveVO.
     * 
     * @return palabrasClave
     */
    public es.pode.soporte.buscador.servicios.PalabraClaveVO[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this PrioridadPalabrasClaveVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(es.pode.soporte.buscador.servicios.PalabraClaveVO[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrioridadPalabrasClaveVO)) return false;
        PrioridadPalabrasClaveVO other = (PrioridadPalabrasClaveVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave())));
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
        if (getPalabrasClave() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPalabrasClave());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPalabrasClave(), i);
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
        new org.apache.axis.description.TypeDesc(PrioridadPalabrasClaveVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PrioridadPalabrasClaveVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PalabraClaveVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
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
