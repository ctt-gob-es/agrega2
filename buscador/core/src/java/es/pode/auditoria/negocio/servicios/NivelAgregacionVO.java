/**
 * NivelAgregacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Value Object con la informacion sobre la fecha de publicacion
 *                         del ODE y con otro VO que nos indicará el
 * nivel de agregación y
 *                         el numero de ODEs
 */
public class NivelAgregacionVO  implements java.io.Serializable {
    /* VO com la informacion del nivel de agregacion y el numero de
     * ODES */
    private es.pode.auditoria.negocio.servicios.NivelAgregacionPublicacion[] nivelAgregacionPublicacionVO;

    private java.lang.String nodo;

    public NivelAgregacionVO() {
    }

    public NivelAgregacionVO(
           es.pode.auditoria.negocio.servicios.NivelAgregacionPublicacion[] nivelAgregacionPublicacionVO,
           java.lang.String nodo) {
           this.nivelAgregacionPublicacionVO = nivelAgregacionPublicacionVO;
           this.nodo = nodo;
    }


    /**
     * Gets the nivelAgregacionPublicacionVO value for this NivelAgregacionVO.
     * 
     * @return nivelAgregacionPublicacionVO   * VO com la informacion del nivel de agregacion y el numero de
     * ODES
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionPublicacion[] getNivelAgregacionPublicacionVO() {
        return nivelAgregacionPublicacionVO;
    }


    /**
     * Sets the nivelAgregacionPublicacionVO value for this NivelAgregacionVO.
     * 
     * @param nivelAgregacionPublicacionVO   * VO com la informacion del nivel de agregacion y el numero de
     * ODES
     */
    public void setNivelAgregacionPublicacionVO(es.pode.auditoria.negocio.servicios.NivelAgregacionPublicacion[] nivelAgregacionPublicacionVO) {
        this.nivelAgregacionPublicacionVO = nivelAgregacionPublicacionVO;
    }


    /**
     * Gets the nodo value for this NivelAgregacionVO.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this NivelAgregacionVO.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NivelAgregacionVO)) return false;
        NivelAgregacionVO other = (NivelAgregacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nivelAgregacionPublicacionVO==null && other.getNivelAgregacionPublicacionVO()==null) || 
             (this.nivelAgregacionPublicacionVO!=null &&
              java.util.Arrays.equals(this.nivelAgregacionPublicacionVO, other.getNivelAgregacionPublicacionVO()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo())));
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
        if (getNivelAgregacionPublicacionVO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNivelAgregacionPublicacionVO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNivelAgregacionPublicacionVO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NivelAgregacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "NivelAgregacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacionPublicacionVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivelAgregacionPublicacionVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "NivelAgregacionPublicacion"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
