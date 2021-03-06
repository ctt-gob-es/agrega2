/**
 * ParamVdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public class ParamVdexVO  implements java.io.Serializable {
    private javax.activation.DataHandler fichero;

    public ParamVdexVO() {
    }

    public ParamVdexVO(
           javax.activation.DataHandler fichero) {
           this.fichero = fichero;
    }


    /**
     * Gets the fichero value for this ParamVdexVO.
     * 
     * @return fichero
     */
    public javax.activation.DataHandler getFichero() {
        return fichero;
    }


    /**
     * Sets the fichero value for this ParamVdexVO.
     * 
     * @param fichero
     */
    public void setFichero(javax.activation.DataHandler fichero) {
        this.fichero = fichero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamVdexVO)) return false;
        ParamVdexVO other = (ParamVdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fichero==null && other.getFichero()==null) || 
             (this.fichero!=null &&
              this.fichero.equals(other.getFichero())));
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
        if (getFichero() != null) {
            _hashCode += getFichero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamVdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ParamVdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fichero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "fichero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
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
