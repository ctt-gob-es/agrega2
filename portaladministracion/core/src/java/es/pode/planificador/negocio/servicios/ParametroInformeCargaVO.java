/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametroInformeCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class ParametroInformeCargaVO  implements java.io.Serializable {
    private java.lang.String nombreFicheroInforme;

    private java.lang.String nombreInforme;

    private java.lang.String pathDestinoInforme;

    private es.pode.planificador.negocio.servicios.ParametrosCargaVO[] parametros;

    public ParametroInformeCargaVO() {
    }

    public ParametroInformeCargaVO(
           java.lang.String nombreFicheroInforme,
           java.lang.String nombreInforme,
           java.lang.String pathDestinoInforme,
           es.pode.planificador.negocio.servicios.ParametrosCargaVO[] parametros) {
           this.nombreFicheroInforme = nombreFicheroInforme;
           this.nombreInforme = nombreInforme;
           this.pathDestinoInforme = pathDestinoInforme;
           this.parametros = parametros;
    }


    /**
     * Gets the nombreFicheroInforme value for this ParametroInformeCargaVO.
     * 
     * @return nombreFicheroInforme
     */
    public java.lang.String getNombreFicheroInforme() {
        return nombreFicheroInforme;
    }


    /**
     * Sets the nombreFicheroInforme value for this ParametroInformeCargaVO.
     * 
     * @param nombreFicheroInforme
     */
    public void setNombreFicheroInforme(java.lang.String nombreFicheroInforme) {
        this.nombreFicheroInforme = nombreFicheroInforme;
    }


    /**
     * Gets the nombreInforme value for this ParametroInformeCargaVO.
     * 
     * @return nombreInforme
     */
    public java.lang.String getNombreInforme() {
        return nombreInforme;
    }


    /**
     * Sets the nombreInforme value for this ParametroInformeCargaVO.
     * 
     * @param nombreInforme
     */
    public void setNombreInforme(java.lang.String nombreInforme) {
        this.nombreInforme = nombreInforme;
    }


    /**
     * Gets the pathDestinoInforme value for this ParametroInformeCargaVO.
     * 
     * @return pathDestinoInforme
     */
    public java.lang.String getPathDestinoInforme() {
        return pathDestinoInforme;
    }


    /**
     * Sets the pathDestinoInforme value for this ParametroInformeCargaVO.
     * 
     * @param pathDestinoInforme
     */
    public void setPathDestinoInforme(java.lang.String pathDestinoInforme) {
        this.pathDestinoInforme = pathDestinoInforme;
    }


    /**
     * Gets the parametros value for this ParametroInformeCargaVO.
     * 
     * @return parametros
     */
    public es.pode.planificador.negocio.servicios.ParametrosCargaVO[] getParametros() {
        return parametros;
    }


    /**
     * Sets the parametros value for this ParametroInformeCargaVO.
     * 
     * @param parametros
     */
    public void setParametros(es.pode.planificador.negocio.servicios.ParametrosCargaVO[] parametros) {
        this.parametros = parametros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroInformeCargaVO)) return false;
        ParametroInformeCargaVO other = (ParametroInformeCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreFicheroInforme==null && other.getNombreFicheroInforme()==null) || 
             (this.nombreFicheroInforme!=null &&
              this.nombreFicheroInforme.equals(other.getNombreFicheroInforme()))) &&
            ((this.nombreInforme==null && other.getNombreInforme()==null) || 
             (this.nombreInforme!=null &&
              this.nombreInforme.equals(other.getNombreInforme()))) &&
            ((this.pathDestinoInforme==null && other.getPathDestinoInforme()==null) || 
             (this.pathDestinoInforme!=null &&
              this.pathDestinoInforme.equals(other.getPathDestinoInforme()))) &&
            ((this.parametros==null && other.getParametros()==null) || 
             (this.parametros!=null &&
              java.util.Arrays.equals(this.parametros, other.getParametros())));
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
        if (getNombreFicheroInforme() != null) {
            _hashCode += getNombreFicheroInforme().hashCode();
        }
        if (getNombreInforme() != null) {
            _hashCode += getNombreInforme().hashCode();
        }
        if (getPathDestinoInforme() != null) {
            _hashCode += getPathDestinoInforme().hashCode();
        }
        if (getParametros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametros(), i);
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
        new org.apache.axis.description.TypeDesc(ParametroInformeCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametroInformeCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreFicheroInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreFicheroInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathDestinoInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathDestinoInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "parametros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametrosCargaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
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
