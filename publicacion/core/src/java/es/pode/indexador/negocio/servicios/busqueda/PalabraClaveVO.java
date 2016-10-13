/**
 * PalabraClaveVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * VO que alberga el resultado de la solicitud para la nube de
 *                         tags, devolviendo la palabra clave que se
 * ha solicitado junto
 *                         con su ranking (nÃºmero de veces encontrada
 * entre las keyword
 *                         del Ã­ndice).
 */
public class PalabraClaveVO  implements java.io.Serializable {
    private java.lang.Integer ranking;

    private java.lang.String palabraClave;

    public PalabraClaveVO() {
    }

    public PalabraClaveVO(
           java.lang.Integer ranking,
           java.lang.String palabraClave) {
           this.ranking = ranking;
           this.palabraClave = palabraClave;
    }


    /**
     * Gets the ranking value for this PalabraClaveVO.
     * 
     * @return ranking
     */
    public java.lang.Integer getRanking() {
        return ranking;
    }


    /**
     * Sets the ranking value for this PalabraClaveVO.
     * 
     * @param ranking
     */
    public void setRanking(java.lang.Integer ranking) {
        this.ranking = ranking;
    }


    /**
     * Gets the palabraClave value for this PalabraClaveVO.
     * 
     * @return palabraClave
     */
    public java.lang.String getPalabraClave() {
        return palabraClave;
    }


    /**
     * Sets the palabraClave value for this PalabraClaveVO.
     * 
     * @param palabraClave
     */
    public void setPalabraClave(java.lang.String palabraClave) {
        this.palabraClave = palabraClave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PalabraClaveVO)) return false;
        PalabraClaveVO other = (PalabraClaveVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ranking==null && other.getRanking()==null) || 
             (this.ranking!=null &&
              this.ranking.equals(other.getRanking()))) &&
            ((this.palabraClave==null && other.getPalabraClave()==null) || 
             (this.palabraClave!=null &&
              this.palabraClave.equals(other.getPalabraClave())));
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
        if (getRanking() != null) {
            _hashCode += getRanking().hashCode();
        }
        if (getPalabraClave() != null) {
            _hashCode += getPalabraClave().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PalabraClaveVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "PalabraClaveVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ranking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ranking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabraClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "palabraClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
