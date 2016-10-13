/**
 * NoticiaModificaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * Value object que encapsula los datos necesarios para la
 *                         modificacion de una noticia existente en base
 * de datos.
 */
public class NoticiaModificaVO  implements java.io.Serializable {
    /* Titulo de la noticia. */
    private java.lang.String titulo;

    /* Resumen de la noticia. */
    private java.lang.String resumen;

    /* Contenido de la noticia. */
    private java.lang.String cuerpo;

    /* URL a la imagen de la noticia. */
    private java.lang.String URLImagen;

    /* Alineamiento de la imagen. */
    private java.lang.Integer alineamientoImg;

    /* Fecha de publicacion de la noticia. */
    private java.util.Calendar fechaPublicacion;

    /* Flag que distingue entre noticias activas o inactivas. */
    private java.lang.Boolean activa;

    /* ID de la noticia en base de datos. */
    private java.lang.Long id;

    /* Idioma en codigo ISO639. */
    private java.lang.String idioma;

    private es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria;

    public NoticiaModificaVO() {
    }

    public NoticiaModificaVO(
           java.lang.String titulo,
           java.lang.String resumen,
           java.lang.String cuerpo,
           java.lang.String URLImagen,
           java.lang.Integer alineamientoImg,
           java.util.Calendar fechaPublicacion,
           java.lang.Boolean activa,
           java.lang.Long id,
           java.lang.String idioma,
           es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria) {
           this.titulo = titulo;
           this.resumen = resumen;
           this.cuerpo = cuerpo;
           this.URLImagen = URLImagen;
           this.alineamientoImg = alineamientoImg;
           this.fechaPublicacion = fechaPublicacion;
           this.activa = activa;
           this.id = id;
           this.idioma = idioma;
           this.categoria = categoria;
    }


    /**
     * Gets the titulo value for this NoticiaModificaVO.
     * 
     * @return titulo   * Titulo de la noticia.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this NoticiaModificaVO.
     * 
     * @param titulo   * Titulo de la noticia.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the resumen value for this NoticiaModificaVO.
     * 
     * @return resumen   * Resumen de la noticia.
     */
    public java.lang.String getResumen() {
        return resumen;
    }


    /**
     * Sets the resumen value for this NoticiaModificaVO.
     * 
     * @param resumen   * Resumen de la noticia.
     */
    public void setResumen(java.lang.String resumen) {
        this.resumen = resumen;
    }


    /**
     * Gets the cuerpo value for this NoticiaModificaVO.
     * 
     * @return cuerpo   * Contenido de la noticia.
     */
    public java.lang.String getCuerpo() {
        return cuerpo;
    }


    /**
     * Sets the cuerpo value for this NoticiaModificaVO.
     * 
     * @param cuerpo   * Contenido de la noticia.
     */
    public void setCuerpo(java.lang.String cuerpo) {
        this.cuerpo = cuerpo;
    }


    /**
     * Gets the URLImagen value for this NoticiaModificaVO.
     * 
     * @return URLImagen   * URL a la imagen de la noticia.
     */
    public java.lang.String getURLImagen() {
        return URLImagen;
    }


    /**
     * Sets the URLImagen value for this NoticiaModificaVO.
     * 
     * @param URLImagen   * URL a la imagen de la noticia.
     */
    public void setURLImagen(java.lang.String URLImagen) {
        this.URLImagen = URLImagen;
    }


    /**
     * Gets the alineamientoImg value for this NoticiaModificaVO.
     * 
     * @return alineamientoImg   * Alineamiento de la imagen.
     */
    public java.lang.Integer getAlineamientoImg() {
        return alineamientoImg;
    }


    /**
     * Sets the alineamientoImg value for this NoticiaModificaVO.
     * 
     * @param alineamientoImg   * Alineamiento de la imagen.
     */
    public void setAlineamientoImg(java.lang.Integer alineamientoImg) {
        this.alineamientoImg = alineamientoImg;
    }


    /**
     * Gets the fechaPublicacion value for this NoticiaModificaVO.
     * 
     * @return fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this NoticiaModificaVO.
     * 
     * @param fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the activa value for this NoticiaModificaVO.
     * 
     * @return activa   * Flag que distingue entre noticias activas o inactivas.
     */
    public java.lang.Boolean getActiva() {
        return activa;
    }


    /**
     * Sets the activa value for this NoticiaModificaVO.
     * 
     * @param activa   * Flag que distingue entre noticias activas o inactivas.
     */
    public void setActiva(java.lang.Boolean activa) {
        this.activa = activa;
    }


    /**
     * Gets the id value for this NoticiaModificaVO.
     * 
     * @return id   * ID de la noticia en base de datos.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this NoticiaModificaVO.
     * 
     * @param id   * ID de la noticia en base de datos.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the idioma value for this NoticiaModificaVO.
     * 
     * @return idioma   * Idioma en codigo ISO639.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this NoticiaModificaVO.
     * 
     * @param idioma   * Idioma en codigo ISO639.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the categoria value for this NoticiaModificaVO.
     * 
     * @return categoria
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaVO getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this NoticiaModificaVO.
     * 
     * @param categoria
     */
    public void setCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria) {
        this.categoria = categoria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NoticiaModificaVO)) return false;
        NoticiaModificaVO other = (NoticiaModificaVO) obj;
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
            ((this.URLImagen==null && other.getURLImagen()==null) || 
             (this.URLImagen!=null &&
              this.URLImagen.equals(other.getURLImagen()))) &&
            ((this.alineamientoImg==null && other.getAlineamientoImg()==null) || 
             (this.alineamientoImg!=null &&
              this.alineamientoImg.equals(other.getAlineamientoImg()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.activa==null && other.getActiva()==null) || 
             (this.activa!=null &&
              this.activa.equals(other.getActiva()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria())));
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
        if (getURLImagen() != null) {
            _hashCode += getURLImagen().hashCode();
        }
        if (getAlineamientoImg() != null) {
            _hashCode += getAlineamientoImg().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getActiva() != null) {
            _hashCode += getActiva().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NoticiaModificaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaModificaVO"));
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
        elemField.setFieldName("URLImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "URLImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alineamientoImg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "alineamientoImg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "activa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
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
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaVO"));
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
