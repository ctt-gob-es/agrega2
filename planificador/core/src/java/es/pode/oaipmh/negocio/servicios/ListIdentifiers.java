/**
 * ListIdentifiers.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class ListIdentifiers  implements java.io.Serializable {
    private es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada;

    public ListIdentifiers() {
    }

    public ListIdentifiers(
           es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) {
           this.parametroLlamada = parametroLlamada;
    }


    /**
     * Gets the parametroLlamada value for this ListIdentifiers.
     * 
     * @return parametroLlamada
     */
    public es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO getParametroLlamada() {
        return parametroLlamada;
    }


    /**
     * Sets the parametroLlamada value for this ListIdentifiers.
     * 
     * @param parametroLlamada
     */
    public void setParametroLlamada(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) {
        this.parametroLlamada = parametroLlamada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListIdentifiers)) return false;
        ListIdentifiers other = (ListIdentifiers) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametroLlamada==null && other.getParametroLlamada()==null) || 
             (this.parametroLlamada!=null &&
              this.parametroLlamada.equals(other.getParametroLlamada())));
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
        if (getParametroLlamada() != null) {
            _hashCode += getParametroLlamada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListIdentifiers.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", ">listIdentifiers"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametroLlamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "parametroLlamada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ParametrosOaiPmhVO"));
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
