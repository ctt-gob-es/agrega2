/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ClassificationVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * ClassificationVO representa un subconjunto de los campos
 *                         correspondientes a la categoria Classification
 * de LOM-ES.
 *                         Contiene arbolesCurriculares.
 */
public class ClassificationVO  implements java.io.Serializable {
    /* Contiene los taxones de las taxonomias que sean "arbol
     *                                 curricular" en el metadato LOM-ES. */
    private es.pode.catalogacion.negocio.servicios.ArbolCurricularVO[] arbolesCurriculares;

    public ClassificationVO() {
    }

    public ClassificationVO(
           es.pode.catalogacion.negocio.servicios.ArbolCurricularVO[] arbolesCurriculares) {
           this.arbolesCurriculares = arbolesCurriculares;
    }


    /**
     * Gets the arbolesCurriculares value for this ClassificationVO.
     * 
     * @return arbolesCurriculares   * Contiene los taxones de las taxonomias que sean "arbol
     *                                 curricular" en el metadato LOM-ES.
     */
    public es.pode.catalogacion.negocio.servicios.ArbolCurricularVO[] getArbolesCurriculares() {
        return arbolesCurriculares;
    }


    /**
     * Sets the arbolesCurriculares value for this ClassificationVO.
     * 
     * @param arbolesCurriculares   * Contiene los taxones de las taxonomias que sean "arbol
     *                                 curricular" en el metadato LOM-ES.
     */
    public void setArbolesCurriculares(es.pode.catalogacion.negocio.servicios.ArbolCurricularVO[] arbolesCurriculares) {
        this.arbolesCurriculares = arbolesCurriculares;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClassificationVO)) return false;
        ClassificationVO other = (ClassificationVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arbolesCurriculares==null && other.getArbolesCurriculares()==null) || 
             (this.arbolesCurriculares!=null &&
              java.util.Arrays.equals(this.arbolesCurriculares, other.getArbolesCurriculares())));
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
        if (getArbolesCurriculares() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArbolesCurriculares());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArbolesCurriculares(), i);
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
        new org.apache.axis.description.TypeDesc(ClassificationVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ClassificationVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arbolesCurriculares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "arbolesCurriculares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArbolCurricularVO"));
        elemField.setNillable(false);
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
