/**
 * LocalizadorVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.localizador.negocio.servicios;

public class LocalizadorVO  implements java.io.Serializable {
    private java.lang.String path;

    private java.lang.String url;

    private java.lang.String mec;

    /* Se trata del identificador uuid del ODE. */
    private java.lang.String identificador;

    private java.lang.String idUsuario;

    /* Es el espacio que consume el ODE en el disco */
    private java.lang.Long consumoEspacio;

    public LocalizadorVO() {
    }

    public LocalizadorVO(
           java.lang.String path,
           java.lang.String url,
           java.lang.String mec,
           java.lang.String identificador,
           java.lang.String idUsuario,
           java.lang.Long consumoEspacio) {
           this.path = path;
           this.url = url;
           this.mec = mec;
           this.identificador = identificador;
           this.idUsuario = idUsuario;
           this.consumoEspacio = consumoEspacio;
    }


    /**
     * Gets the path value for this LocalizadorVO.
     * 
     * @return path
     */
    public java.lang.String getPath() {
        return path;
    }


    /**
     * Sets the path value for this LocalizadorVO.
     * 
     * @param path
     */
    public void setPath(java.lang.String path) {
        this.path = path;
    }


    /**
     * Gets the url value for this LocalizadorVO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this LocalizadorVO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the mec value for this LocalizadorVO.
     * 
     * @return mec
     */
    public java.lang.String getMec() {
        return mec;
    }


    /**
     * Sets the mec value for this LocalizadorVO.
     * 
     * @param mec
     */
    public void setMec(java.lang.String mec) {
        this.mec = mec;
    }


    /**
     * Gets the identificador value for this LocalizadorVO.
     * 
     * @return identificador   * Se trata del identificador uuid del ODE.
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this LocalizadorVO.
     * 
     * @param identificador   * Se trata del identificador uuid del ODE.
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the idUsuario value for this LocalizadorVO.
     * 
     * @return idUsuario
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this LocalizadorVO.
     * 
     * @param idUsuario
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the consumoEspacio value for this LocalizadorVO.
     * 
     * @return consumoEspacio   * Es el espacio que consume el ODE en el disco
     */
    public java.lang.Long getConsumoEspacio() {
        return consumoEspacio;
    }


    /**
     * Sets the consumoEspacio value for this LocalizadorVO.
     * 
     * @param consumoEspacio   * Es el espacio que consume el ODE en el disco
     */
    public void setConsumoEspacio(java.lang.Long consumoEspacio) {
        this.consumoEspacio = consumoEspacio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LocalizadorVO)) return false;
        LocalizadorVO other = (LocalizadorVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.path==null && other.getPath()==null) || 
             (this.path!=null &&
              this.path.equals(other.getPath()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.mec==null && other.getMec()==null) || 
             (this.mec!=null &&
              this.mec.equals(other.getMec()))) &&
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.consumoEspacio==null && other.getConsumoEspacio()==null) || 
             (this.consumoEspacio!=null &&
              this.consumoEspacio.equals(other.getConsumoEspacio())));
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
        if (getPath() != null) {
            _hashCode += getPath().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getMec() != null) {
            _hashCode += getMec().hashCode();
        }
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getConsumoEspacio() != null) {
            _hashCode += getConsumoEspacio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LocalizadorVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "LocalizadorVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("path");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "mec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consumoEspacio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.localizador.pode.es", "consumoEspacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
