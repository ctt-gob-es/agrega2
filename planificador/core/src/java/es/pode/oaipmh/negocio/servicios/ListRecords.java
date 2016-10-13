/**
 * ListRecords.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class ListRecords  implements java.io.Serializable {
    private es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada;

    public ListRecords() {
    }

    public ListRecords(
           es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada) {
           this.parametrosLlamada = parametrosLlamada;
    }


    /**
     * Gets the parametrosLlamada value for this ListRecords.
     * 
     * @return parametrosLlamada
     */
    public es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO getParametrosLlamada() {
        return parametrosLlamada;
    }


    /**
     * Sets the parametrosLlamada value for this ListRecords.
     * 
     * @param parametrosLlamada
     */
    public void setParametrosLlamada(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada) {
        this.parametrosLlamada = parametrosLlamada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListRecords)) return false;
        ListRecords other = (ListRecords) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosLlamada==null && other.getParametrosLlamada()==null) || 
             (this.parametrosLlamada!=null &&
              this.parametrosLlamada.equals(other.getParametrosLlamada())));
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
        if (getParametrosLlamada() != null) {
            _hashCode += getParametrosLlamada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListRecords.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", ">listRecords"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosLlamada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "parametrosLlamada"));
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
