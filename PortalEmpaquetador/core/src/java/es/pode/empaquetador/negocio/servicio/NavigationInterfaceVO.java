/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * NavigationInterfaceVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public class NavigationInterfaceVO  implements java.io.Serializable {
    private es.pode.empaquetador.negocio.servicio.HideLMSUIVO[] hideLMSUI;

    public NavigationInterfaceVO() {
    }

    public NavigationInterfaceVO(
           es.pode.empaquetador.negocio.servicio.HideLMSUIVO[] hideLMSUI) {
           this.hideLMSUI = hideLMSUI;
    }


    /**
     * Gets the hideLMSUI value for this NavigationInterfaceVO.
     * 
     * @return hideLMSUI
     */
    public es.pode.empaquetador.negocio.servicio.HideLMSUIVO[] getHideLMSUI() {
        return hideLMSUI;
    }


    /**
     * Sets the hideLMSUI value for this NavigationInterfaceVO.
     * 
     * @param hideLMSUI
     */
    public void setHideLMSUI(es.pode.empaquetador.negocio.servicio.HideLMSUIVO[] hideLMSUI) {
        this.hideLMSUI = hideLMSUI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NavigationInterfaceVO)) return false;
        NavigationInterfaceVO other = (NavigationInterfaceVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hideLMSUI==null && other.getHideLMSUI()==null) || 
             (this.hideLMSUI!=null &&
              java.util.Arrays.equals(this.hideLMSUI, other.getHideLMSUI())));
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
        if (getHideLMSUI() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHideLMSUI());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHideLMSUI(), i);
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
        new org.apache.axis.description.TypeDesc(NavigationInterfaceVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "NavigationInterfaceVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hideLMSUI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "hideLMSUI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "HideLMSUIVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
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
