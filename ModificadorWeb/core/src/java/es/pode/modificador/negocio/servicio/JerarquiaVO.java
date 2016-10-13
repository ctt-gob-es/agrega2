/**
 * JerarquiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class JerarquiaVO  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.TaxonVO[] jerarquia;

    public JerarquiaVO() {
    }

    public JerarquiaVO(
           es.pode.modificador.negocio.servicio.TaxonVO[] jerarquia) {
           this.jerarquia = jerarquia;
    }


    /**
     * Gets the jerarquia value for this JerarquiaVO.
     * 
     * @return jerarquia
     */
    public es.pode.modificador.negocio.servicio.TaxonVO[] getJerarquia() {
        return jerarquia;
    }


    /**
     * Sets the jerarquia value for this JerarquiaVO.
     * 
     * @param jerarquia
     */
    public void setJerarquia(es.pode.modificador.negocio.servicio.TaxonVO[] jerarquia) {
        this.jerarquia = jerarquia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JerarquiaVO)) return false;
        JerarquiaVO other = (JerarquiaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.jerarquia==null && other.getJerarquia()==null) || 
             (this.jerarquia!=null &&
              java.util.Arrays.equals(this.jerarquia, other.getJerarquia())));
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
        if (getJerarquia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJerarquia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJerarquia(), i);
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
        new org.apache.axis.description.TypeDesc(JerarquiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "JerarquiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jerarquia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "jerarquia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TaxonVO"));
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
