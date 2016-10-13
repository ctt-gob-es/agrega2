/**
 * CategoriaNoticiaTraducidaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * vo que tiene los datos de la categoria noticia traducidos al
 *                         idioma correspondiente.
 */
public class CategoriaNoticiaTraducidaVO  implements java.io.Serializable {
    /* Identificador de la categoria. */
    private java.lang.Long idCategoriaNoticia;

    /* Identificador de la categoria traducida al idioma
     *                                 correspondiente. */
    private java.lang.Long idCategoriaIdiomaNoticia;

    /* Idioma al que se va a traducir la categoría. */
    private java.lang.String idioma;

    /* Nombre de la categoría traducido al idioma correspondiente. */
    private java.lang.String nombreCategoria;

    public CategoriaNoticiaTraducidaVO() {
    }

    public CategoriaNoticiaTraducidaVO(
           java.lang.Long idCategoriaNoticia,
           java.lang.Long idCategoriaIdiomaNoticia,
           java.lang.String idioma,
           java.lang.String nombreCategoria) {
           this.idCategoriaNoticia = idCategoriaNoticia;
           this.idCategoriaIdiomaNoticia = idCategoriaIdiomaNoticia;
           this.idioma = idioma;
           this.nombreCategoria = nombreCategoria;
    }


    /**
     * Gets the idCategoriaNoticia value for this CategoriaNoticiaTraducidaVO.
     * 
     * @return idCategoriaNoticia   * Identificador de la categoria.
     */
    public java.lang.Long getIdCategoriaNoticia() {
        return idCategoriaNoticia;
    }


    /**
     * Sets the idCategoriaNoticia value for this CategoriaNoticiaTraducidaVO.
     * 
     * @param idCategoriaNoticia   * Identificador de la categoria.
     */
    public void setIdCategoriaNoticia(java.lang.Long idCategoriaNoticia) {
        this.idCategoriaNoticia = idCategoriaNoticia;
    }


    /**
     * Gets the idCategoriaIdiomaNoticia value for this CategoriaNoticiaTraducidaVO.
     * 
     * @return idCategoriaIdiomaNoticia   * Identificador de la categoria traducida al idioma
     *                                 correspondiente.
     */
    public java.lang.Long getIdCategoriaIdiomaNoticia() {
        return idCategoriaIdiomaNoticia;
    }


    /**
     * Sets the idCategoriaIdiomaNoticia value for this CategoriaNoticiaTraducidaVO.
     * 
     * @param idCategoriaIdiomaNoticia   * Identificador de la categoria traducida al idioma
     *                                 correspondiente.
     */
    public void setIdCategoriaIdiomaNoticia(java.lang.Long idCategoriaIdiomaNoticia) {
        this.idCategoriaIdiomaNoticia = idCategoriaIdiomaNoticia;
    }


    /**
     * Gets the idioma value for this CategoriaNoticiaTraducidaVO.
     * 
     * @return idioma   * Idioma al que se va a traducir la categoría.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this CategoriaNoticiaTraducidaVO.
     * 
     * @param idioma   * Idioma al que se va a traducir la categoría.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the nombreCategoria value for this CategoriaNoticiaTraducidaVO.
     * 
     * @return nombreCategoria   * Nombre de la categoría traducido al idioma correspondiente.
     */
    public java.lang.String getNombreCategoria() {
        return nombreCategoria;
    }


    /**
     * Sets the nombreCategoria value for this CategoriaNoticiaTraducidaVO.
     * 
     * @param nombreCategoria   * Nombre de la categoría traducido al idioma correspondiente.
     */
    public void setNombreCategoria(java.lang.String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CategoriaNoticiaTraducidaVO)) return false;
        CategoriaNoticiaTraducidaVO other = (CategoriaNoticiaTraducidaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idCategoriaNoticia==null && other.getIdCategoriaNoticia()==null) || 
             (this.idCategoriaNoticia!=null &&
              this.idCategoriaNoticia.equals(other.getIdCategoriaNoticia()))) &&
            ((this.idCategoriaIdiomaNoticia==null && other.getIdCategoriaIdiomaNoticia()==null) || 
             (this.idCategoriaIdiomaNoticia!=null &&
              this.idCategoriaIdiomaNoticia.equals(other.getIdCategoriaIdiomaNoticia()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.nombreCategoria==null && other.getNombreCategoria()==null) || 
             (this.nombreCategoria!=null &&
              this.nombreCategoria.equals(other.getNombreCategoria())));
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
        if (getIdCategoriaNoticia() != null) {
            _hashCode += getIdCategoriaNoticia().hashCode();
        }
        if (getIdCategoriaIdiomaNoticia() != null) {
            _hashCode += getIdCategoriaIdiomaNoticia().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getNombreCategoria() != null) {
            _hashCode += getNombreCategoria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CategoriaNoticiaTraducidaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaTraducidaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaNoticia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idCategoriaNoticia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaIdiomaNoticia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idCategoriaIdiomaNoticia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "nombreCategoria"));
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
