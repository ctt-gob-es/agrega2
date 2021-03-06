/**
 * InformeTerminoBusquedaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Almacena la información relevante del informe de términos de
 *                         búsqueda
 */
public class InformeTerminoBusquedaVO  implements java.io.Serializable {
    private java.lang.String terminoBuscado;

    private int numVeces;

    public InformeTerminoBusquedaVO() {
    }

    public InformeTerminoBusquedaVO(
           java.lang.String terminoBuscado,
           int numVeces) {
           this.terminoBuscado = terminoBuscado;
           this.numVeces = numVeces;
    }


    /**
     * Gets the terminoBuscado value for this InformeTerminoBusquedaVO.
     * 
     * @return terminoBuscado
     */
    public java.lang.String getTerminoBuscado() {
        return terminoBuscado;
    }


    /**
     * Sets the terminoBuscado value for this InformeTerminoBusquedaVO.
     * 
     * @param terminoBuscado
     */
    public void setTerminoBuscado(java.lang.String terminoBuscado) {
        this.terminoBuscado = terminoBuscado;
    }


    /**
     * Gets the numVeces value for this InformeTerminoBusquedaVO.
     * 
     * @return numVeces
     */
    public int getNumVeces() {
        return numVeces;
    }


    /**
     * Sets the numVeces value for this InformeTerminoBusquedaVO.
     * 
     * @param numVeces
     */
    public void setNumVeces(int numVeces) {
        this.numVeces = numVeces;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeTerminoBusquedaVO)) return false;
        InformeTerminoBusquedaVO other = (InformeTerminoBusquedaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.terminoBuscado==null && other.getTerminoBuscado()==null) || 
             (this.terminoBuscado!=null &&
              this.terminoBuscado.equals(other.getTerminoBuscado()))) &&
            this.numVeces == other.getNumVeces();
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
        if (getTerminoBuscado() != null) {
            _hashCode += getTerminoBuscado().hashCode();
        }
        _hashCode += getNumVeces();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeTerminoBusquedaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeTerminoBusquedaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminoBuscado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "terminoBuscado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numVeces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "numVeces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
