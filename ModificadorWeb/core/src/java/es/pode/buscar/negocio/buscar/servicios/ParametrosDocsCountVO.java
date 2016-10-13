/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametrosDocsCountVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * ParÃ¡metros para solicitar el nÃºmero de documentos para un Ã¡rea
 *                         curricular
 */
public class ParametrosDocsCountVO  implements java.io.Serializable {
    private java.lang.String idiomaBusqueda;

    private java.lang.String[] taxonomia;

    private java.lang.String idiomaNavegacion;

    private java.lang.String tipoBusqueda;

    private java.lang.String comunidadPeticion;

    private java.lang.String comunidadDestino;

    private java.lang.String versionComunidadDestino;

    public ParametrosDocsCountVO() {
    }

    public ParametrosDocsCountVO(
           java.lang.String idiomaBusqueda,
           java.lang.String[] taxonomia,
           java.lang.String idiomaNavegacion,
           java.lang.String tipoBusqueda,
           java.lang.String comunidadPeticion,
           java.lang.String comunidadDestino,
           java.lang.String versionComunidadDestino) {
           this.idiomaBusqueda = idiomaBusqueda;
           this.taxonomia = taxonomia;
           this.idiomaNavegacion = idiomaNavegacion;
           this.tipoBusqueda = tipoBusqueda;
           this.comunidadPeticion = comunidadPeticion;
           this.comunidadDestino = comunidadDestino;
           this.versionComunidadDestino = versionComunidadDestino;
    }


    /**
     * Gets the idiomaBusqueda value for this ParametrosDocsCountVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParametrosDocsCountVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the taxonomia value for this ParametrosDocsCountVO.
     * 
     * @return taxonomia
     */
    public java.lang.String[] getTaxonomia() {
        return taxonomia;
    }


    /**
     * Sets the taxonomia value for this ParametrosDocsCountVO.
     * 
     * @param taxonomia
     */
    public void setTaxonomia(java.lang.String[] taxonomia) {
        this.taxonomia = taxonomia;
    }


    /**
     * Gets the idiomaNavegacion value for this ParametrosDocsCountVO.
     * 
     * @return idiomaNavegacion
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParametrosDocsCountVO.
     * 
     * @param idiomaNavegacion
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the tipoBusqueda value for this ParametrosDocsCountVO.
     * 
     * @return tipoBusqueda
     */
    public java.lang.String getTipoBusqueda() {
        return tipoBusqueda;
    }


    /**
     * Sets the tipoBusqueda value for this ParametrosDocsCountVO.
     * 
     * @param tipoBusqueda
     */
    public void setTipoBusqueda(java.lang.String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }


    /**
     * Gets the comunidadPeticion value for this ParametrosDocsCountVO.
     * 
     * @return comunidadPeticion
     */
    public java.lang.String getComunidadPeticion() {
        return comunidadPeticion;
    }


    /**
     * Sets the comunidadPeticion value for this ParametrosDocsCountVO.
     * 
     * @param comunidadPeticion
     */
    public void setComunidadPeticion(java.lang.String comunidadPeticion) {
        this.comunidadPeticion = comunidadPeticion;
    }


    /**
     * Gets the comunidadDestino value for this ParametrosDocsCountVO.
     * 
     * @return comunidadDestino
     */
    public java.lang.String getComunidadDestino() {
        return comunidadDestino;
    }


    /**
     * Sets the comunidadDestino value for this ParametrosDocsCountVO.
     * 
     * @param comunidadDestino
     */
    public void setComunidadDestino(java.lang.String comunidadDestino) {
        this.comunidadDestino = comunidadDestino;
    }


    /**
     * Gets the versionComunidadDestino value for this ParametrosDocsCountVO.
     * 
     * @return versionComunidadDestino
     */
    public java.lang.String getVersionComunidadDestino() {
        return versionComunidadDestino;
    }


    /**
     * Sets the versionComunidadDestino value for this ParametrosDocsCountVO.
     * 
     * @param versionComunidadDestino
     */
    public void setVersionComunidadDestino(java.lang.String versionComunidadDestino) {
        this.versionComunidadDestino = versionComunidadDestino;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDocsCountVO)) return false;
        ParametrosDocsCountVO other = (ParametrosDocsCountVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda()))) &&
            ((this.taxonomia==null && other.getTaxonomia()==null) || 
             (this.taxonomia!=null &&
              java.util.Arrays.equals(this.taxonomia, other.getTaxonomia()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.tipoBusqueda==null && other.getTipoBusqueda()==null) || 
             (this.tipoBusqueda!=null &&
              this.tipoBusqueda.equals(other.getTipoBusqueda()))) &&
            ((this.comunidadPeticion==null && other.getComunidadPeticion()==null) || 
             (this.comunidadPeticion!=null &&
              this.comunidadPeticion.equals(other.getComunidadPeticion()))) &&
            ((this.comunidadDestino==null && other.getComunidadDestino()==null) || 
             (this.comunidadDestino!=null &&
              this.comunidadDestino.equals(other.getComunidadDestino()))) &&
            ((this.versionComunidadDestino==null && other.getVersionComunidadDestino()==null) || 
             (this.versionComunidadDestino!=null &&
              this.versionComunidadDestino.equals(other.getVersionComunidadDestino())));
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
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        if (getTaxonomia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxonomia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxonomia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getTipoBusqueda() != null) {
            _hashCode += getTipoBusqueda().hashCode();
        }
        if (getComunidadPeticion() != null) {
            _hashCode += getComunidadPeticion().hashCode();
        }
        if (getComunidadDestino() != null) {
            _hashCode += getComunidadDestino().hashCode();
        }
        if (getVersionComunidadDestino() != null) {
            _hashCode += getVersionComunidadDestino().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosDocsCountVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosDocsCountVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "taxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tipoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadPeticion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadPeticion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionComunidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "versionComunidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
