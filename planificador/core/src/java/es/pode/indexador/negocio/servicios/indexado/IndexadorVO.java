/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * IndexadorVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;


/**
 * Este objeto de valor devuelve el exito o fracaso para cada ODE
 *                         de una operacion, ademas de su localizaciÃ³n
 */
public class IndexadorVO  implements java.io.Serializable {
    /* Localizador del ODE */
    private java.lang.String localizador;

    /* Devuelve un cÃ³digo numÃ©rico con el exito o fracaso de la
     *                                 operaciÃ³n */
    private int code;

    /* Mensaje que muestra el ODE con el exito o fracaso de la
     *                                 operacion. */
    private java.lang.String mensaje;

    public IndexadorVO() {
    }

    public IndexadorVO(
           java.lang.String localizador,
           int code,
           java.lang.String mensaje) {
           this.localizador = localizador;
           this.code = code;
           this.mensaje = mensaje;
    }


    /**
     * Gets the localizador value for this IndexadorVO.
     * 
     * @return localizador   * Localizador del ODE
     */
    public java.lang.String getLocalizador() {
        return localizador;
    }


    /**
     * Sets the localizador value for this IndexadorVO.
     * 
     * @param localizador   * Localizador del ODE
     */
    public void setLocalizador(java.lang.String localizador) {
        this.localizador = localizador;
    }


    /**
     * Gets the code value for this IndexadorVO.
     * 
     * @return code   * Devuelve un cÃ³digo numÃ©rico con el exito o fracaso de la
     *                                 operaciÃ³n
     */
    public int getCode() {
        return code;
    }


    /**
     * Sets the code value for this IndexadorVO.
     * 
     * @param code   * Devuelve un cÃ³digo numÃ©rico con el exito o fracaso de la
     *                                 operaciÃ³n
     */
    public void setCode(int code) {
        this.code = code;
    }


    /**
     * Gets the mensaje value for this IndexadorVO.
     * 
     * @return mensaje   * Mensaje que muestra el ODE con el exito o fracaso de la
     *                                 operacion.
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this IndexadorVO.
     * 
     * @param mensaje   * Mensaje que muestra el ODE con el exito o fracaso de la
     *                                 operacion.
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IndexadorVO)) return false;
        IndexadorVO other = (IndexadorVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.localizador==null && other.getLocalizador()==null) || 
             (this.localizador!=null &&
              this.localizador.equals(other.getLocalizador()))) &&
            this.code == other.getCode() &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        if (getLocalizador() != null) {
            _hashCode += getLocalizador().hashCode();
        }
        _hashCode += getCode();
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IndexadorVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IndexadorVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "localizador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "mensaje"));
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
