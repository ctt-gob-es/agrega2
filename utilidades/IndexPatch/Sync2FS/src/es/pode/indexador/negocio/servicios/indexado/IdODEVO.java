/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * IdODEVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;


/**
 * Esta clase modela los datos relevantes de ser indexados del ODE,
 * y las relaciones con LomESPrimarioVO y LomEsSecundarioVO
 */
public class IdODEVO  implements java.io.Serializable {
    /* Localizador del ODE */
    private java.lang.String localizadorODE;

    /* Valoracion del ODE una vez que se ha publicado */
    private java.lang.Float valoracion;

    /* Con secuencia o sin secuencia. */
    private java.lang.Boolean secuencia;

    /* URL a la imagen del ODE. */
    private java.lang.String imgFile;

    /* Tamanio del ODE en disco. */
    private java.lang.Float tamanio;

    /* Identificador del ODE */
    private java.lang.String idODE;

    private es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[] catalogacionSecundaria;

    private es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO catalogacionPrimaria;

    public IdODEVO() {
    }

    public IdODEVO(
           java.lang.String localizadorODE,
           java.lang.Float valoracion,
           java.lang.Boolean secuencia,
           java.lang.String imgFile,
           java.lang.Float tamanio,
           java.lang.String idODE,
           es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[] catalogacionSecundaria,
           es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO catalogacionPrimaria) {
           this.localizadorODE = localizadorODE;
           this.valoracion = valoracion;
           this.secuencia = secuencia;
           this.imgFile = imgFile;
           this.tamanio = tamanio;
           this.idODE = idODE;
           this.catalogacionSecundaria = catalogacionSecundaria;
           this.catalogacionPrimaria = catalogacionPrimaria;
    }


    /**
     * Gets the localizadorODE value for this IdODEVO.
     * 
     * @return localizadorODE   * Localizador del ODE
     */
    public java.lang.String getLocalizadorODE() {
        return localizadorODE;
    }


    /**
     * Sets the localizadorODE value for this IdODEVO.
     * 
     * @param localizadorODE   * Localizador del ODE
     */
    public void setLocalizadorODE(java.lang.String localizadorODE) {
        this.localizadorODE = localizadorODE;
    }


    /**
     * Gets the valoracion value for this IdODEVO.
     * 
     * @return valoracion   * Valoracion del ODE una vez que se ha publicado
     */
    public java.lang.Float getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this IdODEVO.
     * 
     * @param valoracion   * Valoracion del ODE una vez que se ha publicado
     */
    public void setValoracion(java.lang.Float valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the secuencia value for this IdODEVO.
     * 
     * @return secuencia   * Con secuencia o sin secuencia.
     */
    public java.lang.Boolean getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this IdODEVO.
     * 
     * @param secuencia   * Con secuencia o sin secuencia.
     */
    public void setSecuencia(java.lang.Boolean secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the imgFile value for this IdODEVO.
     * 
     * @return imgFile   * URL a la imagen del ODE.
     */
    public java.lang.String getImgFile() {
        return imgFile;
    }


    /**
     * Sets the imgFile value for this IdODEVO.
     * 
     * @param imgFile   * URL a la imagen del ODE.
     */
    public void setImgFile(java.lang.String imgFile) {
        this.imgFile = imgFile;
    }


    /**
     * Gets the tamanio value for this IdODEVO.
     * 
     * @return tamanio   * Tamanio del ODE en disco.
     */
    public java.lang.Float getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this IdODEVO.
     * 
     * @param tamanio   * Tamanio del ODE en disco.
     */
    public void setTamanio(java.lang.Float tamanio) {
        this.tamanio = tamanio;
    }


    /**
     * Gets the idODE value for this IdODEVO.
     * 
     * @return idODE   * Identificador del ODE
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this IdODEVO.
     * 
     * @param idODE   * Identificador del ODE
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }


    /**
     * Gets the catalogacionSecundaria value for this IdODEVO.
     * 
     * @return catalogacionSecundaria
     */
    public es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[] getCatalogacionSecundaria() {
        return catalogacionSecundaria;
    }


    /**
     * Sets the catalogacionSecundaria value for this IdODEVO.
     * 
     * @param catalogacionSecundaria
     */
    public void setCatalogacionSecundaria(es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[] catalogacionSecundaria) {
        this.catalogacionSecundaria = catalogacionSecundaria;
    }


    /**
     * Gets the catalogacionPrimaria value for this IdODEVO.
     * 
     * @return catalogacionPrimaria
     */
    public es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO getCatalogacionPrimaria() {
        return catalogacionPrimaria;
    }


    /**
     * Sets the catalogacionPrimaria value for this IdODEVO.
     * 
     * @param catalogacionPrimaria
     */
    public void setCatalogacionPrimaria(es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO catalogacionPrimaria) {
        this.catalogacionPrimaria = catalogacionPrimaria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdODEVO)) return false;
        IdODEVO other = (IdODEVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.localizadorODE==null && other.getLocalizadorODE()==null) || 
             (this.localizadorODE!=null &&
              this.localizadorODE.equals(other.getLocalizadorODE()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.secuencia==null && other.getSecuencia()==null) || 
             (this.secuencia!=null &&
              this.secuencia.equals(other.getSecuencia()))) &&
            ((this.imgFile==null && other.getImgFile()==null) || 
             (this.imgFile!=null &&
              this.imgFile.equals(other.getImgFile()))) &&
            ((this.tamanio==null && other.getTamanio()==null) || 
             (this.tamanio!=null &&
              this.tamanio.equals(other.getTamanio()))) &&
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE()))) &&
            ((this.catalogacionSecundaria==null && other.getCatalogacionSecundaria()==null) || 
             (this.catalogacionSecundaria!=null &&
              java.util.Arrays.equals(this.catalogacionSecundaria, other.getCatalogacionSecundaria()))) &&
            ((this.catalogacionPrimaria==null && other.getCatalogacionPrimaria()==null) || 
             (this.catalogacionPrimaria!=null &&
              this.catalogacionPrimaria.equals(other.getCatalogacionPrimaria())));
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
        if (getLocalizadorODE() != null) {
            _hashCode += getLocalizadorODE().hashCode();
        }
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getSecuencia() != null) {
            _hashCode += getSecuencia().hashCode();
        }
        if (getImgFile() != null) {
            _hashCode += getImgFile().hashCode();
        }
        if (getTamanio() != null) {
            _hashCode += getTamanio().hashCode();
        }
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        if (getCatalogacionSecundaria() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCatalogacionSecundaria());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCatalogacionSecundaria(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCatalogacionPrimaria() != null) {
            _hashCode += getCatalogacionPrimaria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdODEVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IdODEVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "localizadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imgFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "imgFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "tamanio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogacionSecundaria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "catalogacionSecundaria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESSecundarioVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogacionPrimaria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "catalogacionPrimaria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESPrimarioVO"));
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
