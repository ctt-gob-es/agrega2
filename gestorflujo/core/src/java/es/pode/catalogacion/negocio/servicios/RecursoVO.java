/**
 * RecursoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class RecursoVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.IdentificadorVO identificador;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones;

    public RecursoVO() {
    }

    public RecursoVO(
           es.pode.catalogacion.negocio.servicios.IdentificadorVO identificador,
           es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones) {
           this.identificador = identificador;
           this.descripciones = descripciones;
    }


    /**
     * Gets the identificador value for this RecursoVO.
     * 
     * @return identificador
     */
    public es.pode.catalogacion.negocio.servicios.IdentificadorVO getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this RecursoVO.
     * 
     * @param identificador
     */
    public void setIdentificador(es.pode.catalogacion.negocio.servicios.IdentificadorVO identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the descripciones value for this RecursoVO.
     * 
     * @return descripciones
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO[] getDescripciones() {
        return descripciones;
    }


    /**
     * Sets the descripciones value for this RecursoVO.
     * 
     * @param descripciones
     */
    public void setDescripciones(es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones) {
        this.descripciones = descripciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecursoVO)) return false;
        RecursoVO other = (RecursoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.descripciones==null && other.getDescripciones()==null) || 
             (this.descripciones!=null &&
              java.util.Arrays.equals(this.descripciones, other.getDescripciones())));
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
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getDescripciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescripciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescripciones(), i);
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
        new org.apache.axis.description.TypeDesc(RecursoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RecursoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "descripciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
