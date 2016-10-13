/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * OdeConGruposVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class OdeConGruposVO  implements java.io.Serializable {
    /* El identificador  del ode */
    private java.lang.String id_mec;

    /* grupos publicos asociados a ese ode */
    private es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] gruposAsociados;

    public OdeConGruposVO() {
    }

    public OdeConGruposVO(
           java.lang.String id_mec,
           es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] gruposAsociados) {
           this.id_mec = id_mec;
           this.gruposAsociados = gruposAsociados;
    }


    /**
     * Gets the id_mec value for this OdeConGruposVO.
     * 
     * @return id_mec   * El identificador  del ode
     */
    public java.lang.String getId_mec() {
        return id_mec;
    }


    /**
     * Sets the id_mec value for this OdeConGruposVO.
     * 
     * @param id_mec   * El identificador  del ode
     */
    public void setId_mec(java.lang.String id_mec) {
        this.id_mec = id_mec;
    }


    /**
     * Gets the gruposAsociados value for this OdeConGruposVO.
     * 
     * @return gruposAsociados   * grupos publicos asociados a ese ode
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] getGruposAsociados() {
        return gruposAsociados;
    }


    /**
     * Sets the gruposAsociados value for this OdeConGruposVO.
     * 
     * @param gruposAsociados   * grupos publicos asociados a ese ode
     */
    public void setGruposAsociados(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] gruposAsociados) {
        this.gruposAsociados = gruposAsociados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OdeConGruposVO)) return false;
        OdeConGruposVO other = (OdeConGruposVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id_mec==null && other.getId_mec()==null) || 
             (this.id_mec!=null &&
              this.id_mec.equals(other.getId_mec()))) &&
            ((this.gruposAsociados==null && other.getGruposAsociados()==null) || 
             (this.gruposAsociados!=null &&
              java.util.Arrays.equals(this.gruposAsociados, other.getGruposAsociados())));
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
        if (getId_mec() != null) {
            _hashCode += getId_mec().hashCode();
        }
        if (getGruposAsociados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGruposAsociados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGruposAsociados(), i);
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
        new org.apache.axis.description.TypeDesc(OdeConGruposVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "OdeConGruposVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_mec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id_mec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gruposAsociados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "gruposAsociados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "GrupoPublicoVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "item"));
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
