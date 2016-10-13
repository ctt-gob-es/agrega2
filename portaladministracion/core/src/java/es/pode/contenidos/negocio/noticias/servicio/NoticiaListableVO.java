/**
 * NoticiaListableVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * Esta clase VO  alberga todos los atributos de una noticia
 *                         necesarios para presentar un listado de noticias.
 */
public class NoticiaListableVO  implements java.io.Serializable {
    /* Titulo de la noticia. */
    private java.lang.String titulo;

    /* Contenido de la noticia. */
    private java.lang.String resumen;

    /* ID de la noticia. */
    private java.lang.Long id;

    /* Fecha de publicacion de la noticia. */
    private java.util.Calendar fechaPublicacion;

    /* URL de la imagen de la noticia. */
    private java.lang.String URLImagen;

    /* Alineamiento de la imagen. */
    private java.lang.Integer alineamientoImg;

    /* Contenido de la noticia. */
    private java.lang.String cuerpo;

    private java.lang.Boolean activa;

    private es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria;

    public NoticiaListableVO() {
    }

    public NoticiaListableVO(
           java.lang.String titulo,
           java.lang.String resumen,
           java.lang.Long id,
           java.util.Calendar fechaPublicacion,
           java.lang.String URLImagen,
           java.lang.Integer alineamientoImg,
           java.lang.String cuerpo,
           java.lang.Boolean activa,
           es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria) {
           this.titulo = titulo;
           this.resumen = resumen;
           this.id = id;
           this.fechaPublicacion = fechaPublicacion;
           this.URLImagen = URLImagen;
           this.alineamientoImg = alineamientoImg;
           this.cuerpo = cuerpo;
           this.activa = activa;
           this.categoria = categoria;
    }


    /**
     * Gets the titulo value for this NoticiaListableVO.
     * 
     * @return titulo   * Titulo de la noticia.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this NoticiaListableVO.
     * 
     * @param titulo   * Titulo de la noticia.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the resumen value for this NoticiaListableVO.
     * 
     * @return resumen   * Contenido de la noticia.
     */
    public java.lang.String getResumen() {
        return resumen;
    }


    /**
     * Sets the resumen value for this NoticiaListableVO.
     * 
     * @param resumen   * Contenido de la noticia.
     */
    public void setResumen(java.lang.String resumen) {
        this.resumen = resumen;
    }


    /**
     * Gets the id value for this NoticiaListableVO.
     * 
     * @return id   * ID de la noticia.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this NoticiaListableVO.
     * 
     * @param id   * ID de la noticia.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the fechaPublicacion value for this NoticiaListableVO.
     * 
     * @return fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this NoticiaListableVO.
     * 
     * @param fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the URLImagen value for this NoticiaListableVO.
     * 
     * @return URLImagen   * URL de la imagen de la noticia.
     */
    public java.lang.String getURLImagen() {
        return URLImagen;
    }


    /**
     * Sets the URLImagen value for this NoticiaListableVO.
     * 
     * @param URLImagen   * URL de la imagen de la noticia.
     */
    public void setURLImagen(java.lang.String URLImagen) {
        this.URLImagen = URLImagen;
    }


    /**
     * Gets the alineamientoImg value for this NoticiaListableVO.
     * 
     * @return alineamientoImg   * Alineamiento de la imagen.
     */
    public java.lang.Integer getAlineamientoImg() {
        return alineamientoImg;
    }


    /**
     * Sets the alineamientoImg value for this NoticiaListableVO.
     * 
     * @param alineamientoImg   * Alineamiento de la imagen.
     */
    public void setAlineamientoImg(java.lang.Integer alineamientoImg) {
        this.alineamientoImg = alineamientoImg;
    }


    /**
     * Gets the cuerpo value for this NoticiaListableVO.
     * 
     * @return cuerpo   * Contenido de la noticia.
     */
    public java.lang.String getCuerpo() {
        return cuerpo;
    }


    /**
     * Sets the cuerpo value for this NoticiaListableVO.
     * 
     * @param cuerpo   * Contenido de la noticia.
     */
    public void setCuerpo(java.lang.String cuerpo) {
        this.cuerpo = cuerpo;
    }


    /**
     * Gets the activa value for this NoticiaListableVO.
     * 
     * @return activa
     */
    public java.lang.Boolean getActiva() {
        return activa;
    }


    /**
     * Sets the activa value for this NoticiaListableVO.
     * 
     * @param activa
     */
    public void setActiva(java.lang.Boolean activa) {
        this.activa = activa;
    }


    /**
     * Gets the categoria value for this NoticiaListableVO.
     * 
     * @return categoria
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaVO getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this NoticiaListableVO.
     * 
     * @param categoria
     */
    public void setCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaVO categoria) {
        this.categoria = categoria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NoticiaListableVO)) return false;
        NoticiaListableVO other = (NoticiaListableVO) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.URLImagen==null && other.getURLImagen()==null) || 
             (this.URLImagen!=null &&
              this.URLImagen.equals(other.getURLImagen()))) &&
            ((this.alineamientoImg==null && other.getAlineamientoImg()==null) || 
             (this.alineamientoImg!=null &&
              this.alineamientoImg.equals(other.getAlineamientoImg()))) &&
            ((this.cuerpo==null && other.getCuerpo()==null) || 
             (this.cuerpo!=null &&
              this.cuerpo.equals(other.getCuerpo()))) &&
            ((this.activa==null && other.getActiva()==null) || 
             (this.activa!=null &&
              this.activa.equals(other.getActiva()))) &&
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getURLImagen() != null) {
            _hashCode += getURLImagen().hashCode();
        }
        if (getAlineamientoImg() != null) {
            _hashCode += getAlineamientoImg().hashCode();
        }
        if (getCuerpo() != null) {
            _hashCode += getCuerpo().hashCode();
        }
        if (getActiva() != null) {
            _hashCode += getActiva().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NoticiaListableVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaListableVO"));
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
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("cuerpo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "cuerpo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "activa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
