/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ValidaVdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;


/**
 * tipo de datos que devuelven los metodos del servicio
 *                         SrvValidadorVDEXService.
 *                         contiene un Boolean llamado valido que indica
 * si el Tesauro o la
 *                         Taxonomia ha pasado la validación.
 */
public class ValidaVdexVO  implements java.io.Serializable {
    /* indica si el Tesauro o Taxonomia es válido */
    private java.lang.Boolean valido;

    private java.lang.String tipoVdex;

    private java.lang.String[] errores;

    private java.lang.String vocabName;

    private java.lang.String vocabIdentifier;

    public ValidaVdexVO() {
    }

    public ValidaVdexVO(
           java.lang.Boolean valido,
           java.lang.String tipoVdex,
           java.lang.String[] errores,
           java.lang.String vocabName,
           java.lang.String vocabIdentifier) {
           this.valido = valido;
           this.tipoVdex = tipoVdex;
           this.errores = errores;
           this.vocabName = vocabName;
           this.vocabIdentifier = vocabIdentifier;
    }


    /**
     * Gets the valido value for this ValidaVdexVO.
     * 
     * @return valido   * indica si el Tesauro o Taxonomia es válido
     */
    public java.lang.Boolean getValido() {
        return valido;
    }


    /**
     * Sets the valido value for this ValidaVdexVO.
     * 
     * @param valido   * indica si el Tesauro o Taxonomia es válido
     */
    public void setValido(java.lang.Boolean valido) {
        this.valido = valido;
    }


    /**
     * Gets the tipoVdex value for this ValidaVdexVO.
     * 
     * @return tipoVdex
     */
    public java.lang.String getTipoVdex() {
        return tipoVdex;
    }


    /**
     * Sets the tipoVdex value for this ValidaVdexVO.
     * 
     * @param tipoVdex
     */
    public void setTipoVdex(java.lang.String tipoVdex) {
        this.tipoVdex = tipoVdex;
    }


    /**
     * Gets the errores value for this ValidaVdexVO.
     * 
     * @return errores
     */
    public java.lang.String[] getErrores() {
        return errores;
    }


    /**
     * Sets the errores value for this ValidaVdexVO.
     * 
     * @param errores
     */
    public void setErrores(java.lang.String[] errores) {
        this.errores = errores;
    }


    /**
     * Gets the vocabName value for this ValidaVdexVO.
     * 
     * @return vocabName
     */
    public java.lang.String getVocabName() {
        return vocabName;
    }


    /**
     * Sets the vocabName value for this ValidaVdexVO.
     * 
     * @param vocabName
     */
    public void setVocabName(java.lang.String vocabName) {
        this.vocabName = vocabName;
    }


    /**
     * Gets the vocabIdentifier value for this ValidaVdexVO.
     * 
     * @return vocabIdentifier
     */
    public java.lang.String getVocabIdentifier() {
        return vocabIdentifier;
    }


    /**
     * Sets the vocabIdentifier value for this ValidaVdexVO.
     * 
     * @param vocabIdentifier
     */
    public void setVocabIdentifier(java.lang.String vocabIdentifier) {
        this.vocabIdentifier = vocabIdentifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaVdexVO)) return false;
        ValidaVdexVO other = (ValidaVdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valido==null && other.getValido()==null) || 
             (this.valido!=null &&
              this.valido.equals(other.getValido()))) &&
            ((this.tipoVdex==null && other.getTipoVdex()==null) || 
             (this.tipoVdex!=null &&
              this.tipoVdex.equals(other.getTipoVdex()))) &&
            ((this.errores==null && other.getErrores()==null) || 
             (this.errores!=null &&
              java.util.Arrays.equals(this.errores, other.getErrores()))) &&
            ((this.vocabName==null && other.getVocabName()==null) || 
             (this.vocabName!=null &&
              this.vocabName.equals(other.getVocabName()))) &&
            ((this.vocabIdentifier==null && other.getVocabIdentifier()==null) || 
             (this.vocabIdentifier!=null &&
              this.vocabIdentifier.equals(other.getVocabIdentifier())));
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
        if (getValido() != null) {
            _hashCode += getValido().hashCode();
        }
        if (getTipoVdex() != null) {
            _hashCode += getTipoVdex().hashCode();
        }
        if (getErrores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVocabName() != null) {
            _hashCode += getVocabName().hashCode();
        }
        if (getVocabIdentifier() != null) {
            _hashCode += getVocabIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaVdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "valido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoVdex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "tipoVdex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "errores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "vocabName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "vocabIdentifier"));
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
