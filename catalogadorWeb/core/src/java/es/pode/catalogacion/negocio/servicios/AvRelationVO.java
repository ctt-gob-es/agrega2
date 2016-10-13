/**
 * AvRelationVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvRelationVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.SourceValueVO tipo;

    private es.pode.catalogacion.negocio.servicios.RecursoVO recurso;

    public AvRelationVO() {
    }

    public AvRelationVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO tipo,
           es.pode.catalogacion.negocio.servicios.RecursoVO recurso) {
           this.tipo = tipo;
           this.recurso = recurso;
    }


    /**
     * Gets the tipo value for this AvRelationVO.
     * 
     * @return tipo
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this AvRelationVO.
     * 
     * @param tipo
     */
    public void setTipo(es.pode.catalogacion.negocio.servicios.SourceValueVO tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the recurso value for this AvRelationVO.
     * 
     * @return recurso
     */
    public es.pode.catalogacion.negocio.servicios.RecursoVO getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this AvRelationVO.
     * 
     * @param recurso
     */
    public void setRecurso(es.pode.catalogacion.negocio.servicios.RecursoVO recurso) {
        this.recurso = recurso;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvRelationVO)) return false;
        AvRelationVO other = (AvRelationVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.recurso==null && other.getRecurso()==null) || 
             (this.recurso!=null &&
              this.recurso.equals(other.getRecurso())));
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getRecurso() != null) {
            _hashCode += getRecurso().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AvRelationVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRelationVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RecursoVO"));
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
