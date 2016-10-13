/**
 * DescripcionNoticiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * Describe campos de  la noticia correspondiente al idioma.
 */
public class DescripcionNoticiaVO  implements java.io.Serializable {
    /* Titulo de la noticia. */
    private java.lang.String titulo;

    /* Resumen de la noticia. */
    private java.lang.String resumen;

    /* Cuerpo de la noticia. */
    private java.lang.String cuerpo;

    /* Idioma de la descripción de la noticia. */
    private java.lang.String idioma;

    /* Identificador autogenerado por la base de datos. */
    private java.lang.Long id;

    public DescripcionNoticiaVO() {
    }

    public DescripcionNoticiaVO(
           java.lang.String titulo,
           java.lang.String resumen,
           java.lang.String cuerpo,
           java.lang.String idioma,
           java.lang.Long id) {
           this.titulo = titulo;
           this.resumen = resumen;
           this.cuerpo = cuerpo;
           this.idioma = idioma;
           this.id = id;
    }


    /**
     * Gets the titulo value for this DescripcionNoticiaVO.
     * 
     * @return titulo   * Titulo de la noticia.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DescripcionNoticiaVO.
     * 
     * @param titulo   * Titulo de la noticia.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the resumen value for this DescripcionNoticiaVO.
     * 
     * @return resumen   * Resumen de la noticia.
     */
    public java.lang.String getResumen() {
        return resumen;
    }


    /**
     * Sets the resumen value for this DescripcionNoticiaVO.
     * 
     * @param resumen   * Resumen de la noticia.
     */
    public void setResumen(java.lang.String resumen) {
        this.resumen = resumen;
    }


    /**
     * Gets the cuerpo value for this DescripcionNoticiaVO.
     * 
     * @return cuerpo   * Cuerpo de la noticia.
     */
    public java.lang.String getCuerpo() {
        return cuerpo;
    }


    /**
     * Sets the cuerpo value for this DescripcionNoticiaVO.
     * 
     * @param cuerpo   * Cuerpo de la noticia.
     */
    public void setCuerpo(java.lang.String cuerpo) {
        this.cuerpo = cuerpo;
    }


    /**
     * Gets the idioma value for this DescripcionNoticiaVO.
     * 
     * @return idioma   * Idioma de la descripción de la noticia.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this DescripcionNoticiaVO.
     * 
     * @param idioma   * Idioma de la descripción de la noticia.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the id value for this DescripcionNoticiaVO.
     * 
     * @return id   * Identificador autogenerado por la base de datos.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this DescripcionNoticiaVO.
     * 
     * @param id   * Identificador autogenerado por la base de datos.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DescripcionNoticiaVO)) return false;
        DescripcionNoticiaVO other = (DescripcionNoticiaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.resumen==null && other.getResumen()==null) || 
             (this.resumen!=null &&
              this.resumen.equals(other.getResumen()))) &&
            ((this.cuerpo==null && other.getCuerpo()==null) || 
             (this.cuerpo!=null &&
              this.cuerpo.equals(other.getCuerpo()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getResumen() != null) {
            _hashCode += getResumen().hashCode();
        }
        if (getCuerpo() != null) {
            _hashCode += getCuerpo().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DescripcionNoticiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "DescripcionNoticiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resumen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "resumen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuerpo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "cuerpo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
