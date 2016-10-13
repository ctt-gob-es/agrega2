/**
 * ManifestVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class ManifestVO  implements java.io.Serializable {
    /* Array de organizaciones */
    private es.pode.entregar.negocio.servicios.OrganizacionVO[] organizaciones;

    /* Titulo del ODE */
    private java.lang.String titulo;

    /* Contiene la localizacion del ODE, para su posterior
     *                                 representacion visual */
    private java.lang.String localizacionURL;

    public ManifestVO() {
    }

    public ManifestVO(
           es.pode.entregar.negocio.servicios.OrganizacionVO[] organizaciones,
           java.lang.String titulo,
           java.lang.String localizacionURL) {
           this.organizaciones = organizaciones;
           this.titulo = titulo;
           this.localizacionURL = localizacionURL;
    }


    /**
     * Gets the organizaciones value for this ManifestVO.
     * 
     * @return organizaciones   * Array de organizaciones
     */
    public es.pode.entregar.negocio.servicios.OrganizacionVO[] getOrganizaciones() {
        return organizaciones;
    }


    /**
     * Sets the organizaciones value for this ManifestVO.
     * 
     * @param organizaciones   * Array de organizaciones
     */
    public void setOrganizaciones(es.pode.entregar.negocio.servicios.OrganizacionVO[] organizaciones) {
        this.organizaciones = organizaciones;
    }


    /**
     * Gets the titulo value for this ManifestVO.
     * 
     * @return titulo   * Titulo del ODE
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ManifestVO.
     * 
     * @param titulo   * Titulo del ODE
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the localizacionURL value for this ManifestVO.
     * 
     * @return localizacionURL   * Contiene la localizacion del ODE, para su posterior
     *                                 representacion visual
     */
    public java.lang.String getLocalizacionURL() {
        return localizacionURL;
    }


    /**
     * Sets the localizacionURL value for this ManifestVO.
     * 
     * @param localizacionURL   * Contiene la localizacion del ODE, para su posterior
     *                                 representacion visual
     */
    public void setLocalizacionURL(java.lang.String localizacionURL) {
        this.localizacionURL = localizacionURL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManifestVO)) return false;
        ManifestVO other = (ManifestVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.organizaciones==null && other.getOrganizaciones()==null) || 
             (this.organizaciones!=null &&
              java.util.Arrays.equals(this.organizaciones, other.getOrganizaciones()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.localizacionURL==null && other.getLocalizacionURL()==null) || 
             (this.localizacionURL!=null &&
              this.localizacionURL.equals(other.getLocalizacionURL())));
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
        if (getOrganizaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrganizaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrganizaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getLocalizacionURL() != null) {
            _hashCode += getLocalizacionURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManifestVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ManifestVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "organizaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "OrganizacionVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizacionURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "localizacionURL"));
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
