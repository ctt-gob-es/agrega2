/**
 * ListRecordsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class ListRecordsResponse  implements java.io.Serializable {
    private es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listRecordsReturn;

    public ListRecordsResponse() {
    }

    public ListRecordsResponse(
           es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listRecordsReturn) {
           this.listRecordsReturn = listRecordsReturn;
    }


    /**
     * Gets the listRecordsReturn value for this ListRecordsResponse.
     * 
     * @return listRecordsReturn
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest getListRecordsReturn() {
        return listRecordsReturn;
    }


    /**
     * Sets the listRecordsReturn value for this ListRecordsResponse.
     * 
     * @param listRecordsReturn
     */
    public void setListRecordsReturn(es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listRecordsReturn) {
        this.listRecordsReturn = listRecordsReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListRecordsResponse)) return false;
        ListRecordsResponse other = (ListRecordsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listRecordsReturn==null && other.getListRecordsReturn()==null) || 
             (this.listRecordsReturn!=null &&
              this.listRecordsReturn.equals(other.getListRecordsReturn())));
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
        if (getListRecordsReturn() != null) {
            _hashCode += getListRecordsReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListRecordsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", ">listRecordsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listRecordsReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listRecordsReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoOAIRequest"));
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
