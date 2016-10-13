/**
 * InformeOdesIdiomaFederadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con la información de los odes publicados en cada idioma para
 * cada nodo
 */
public class InformeOdesIdiomaFederadoVO  implements java.io.Serializable {
    /* Nombre del nodo */
    private java.lang.String nodo;

    private es.pode.auditoria.negocio.servicios.OdesPorIdioma[] odesIdioma;

    public InformeOdesIdiomaFederadoVO() {
    }

    public InformeOdesIdiomaFederadoVO(
           java.lang.String nodo,
           es.pode.auditoria.negocio.servicios.OdesPorIdioma[] odesIdioma) {
           this.nodo = nodo;
           this.odesIdioma = odesIdioma;
    }


    /**
     * Gets the nodo value for this InformeOdesIdiomaFederadoVO.
     * 
     * @return nodo   * Nombre del nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this InformeOdesIdiomaFederadoVO.
     * 
     * @param nodo   * Nombre del nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the odesIdioma value for this InformeOdesIdiomaFederadoVO.
     * 
     * @return odesIdioma
     */
    public es.pode.auditoria.negocio.servicios.OdesPorIdioma[] getOdesIdioma() {
        return odesIdioma;
    }


    /**
     * Sets the odesIdioma value for this InformeOdesIdiomaFederadoVO.
     * 
     * @param odesIdioma
     */
    public void setOdesIdioma(es.pode.auditoria.negocio.servicios.OdesPorIdioma[] odesIdioma) {
        this.odesIdioma = odesIdioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeOdesIdiomaFederadoVO)) return false;
        InformeOdesIdiomaFederadoVO other = (InformeOdesIdiomaFederadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
            ((this.odesIdioma==null && other.getOdesIdioma()==null) || 
             (this.odesIdioma!=null &&
              java.util.Arrays.equals(this.odesIdioma, other.getOdesIdioma())));
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
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        if (getOdesIdioma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOdesIdioma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOdesIdioma(), i);
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
        new org.apache.axis.description.TypeDesc(InformeOdesIdiomaFederadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeOdesIdiomaFederadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("odesIdioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "odesIdioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "OdesPorIdioma"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "item"));
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
