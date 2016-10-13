/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * NoticiaTraducidaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;

public class NoticiaTraducidaVO  implements java.io.Serializable {
    private java.lang.Long idNoticia;

    private java.lang.String URLImagen;

    private java.lang.Integer alineamientoImg;

    private java.util.Calendar fechaPublicacion;

    private java.lang.String autor;

    private java.lang.String titulo;

    private java.lang.String resumen;

    private java.lang.String cuerpo;

    private java.lang.String idiomas;

    private java.lang.String categoria;

    private java.lang.Long idCategoria;

    /* Indica si la noticia est√° activa o no. */
    private java.lang.Boolean activa;

    public NoticiaTraducidaVO() {
    }

    public NoticiaTraducidaVO(
           java.lang.Long idNoticia,
           java.lang.String URLImagen,
           java.lang.Integer alineamientoImg,
           java.util.Calendar fechaPublicacion,
           java.lang.String autor,
           java.lang.String titulo,
           java.lang.String resumen,
           java.lang.String cuerpo,
           java.lang.String idiomas,
           java.lang.String categoria,
           java.lang.Long idCategoria,
           java.lang.Boolean activa) {
           this.idNoticia = idNoticia;
           this.URLImagen = URLImagen;
           this.alineamientoImg = alineamientoImg;
           this.fechaPublicacion = fechaPublicacion;
           this.autor = autor;
           this.titulo = titulo;
           this.resumen = resumen;
           this.cuerpo = cuerpo;
           this.idiomas = idiomas;
           this.categoria = categoria;
           this.idCategoria = idCategoria;
           this.activa = activa;
    }


    /**
     * Gets the idNoticia value for this NoticiaTraducidaVO.
     * 
     * @return idNoticia
     */
    public java.lang.Long getIdNoticia() {
        return idNoticia;
    }


    /**
     * Sets the idNoticia value for this NoticiaTraducidaVO.
     * 
     * @param idNoticia
     */
    public void setIdNoticia(java.lang.Long idNoticia) {
        this.idNoticia = idNoticia;
    }


    /**
     * Gets the URLImagen value for this NoticiaTraducidaVO.
     * 
     * @return URLImagen
     */
    public java.lang.String getURLImagen() {
        return URLImagen;
    }


    /**
     * Sets the URLImagen value for this NoticiaTraducidaVO.
     * 
     * @param URLImagen
     */
    public void setURLImagen(java.lang.String URLImagen) {
        this.URLImagen = URLImagen;
    }


    /**
     * Gets the alineamientoImg value for this NoticiaTraducidaVO.
     * 
     * @return alineamientoImg
     */
    public java.lang.Integer getAlineamientoImg() {
        return alineamientoImg;
    }


    /**
     * Sets the alineamientoImg value for this NoticiaTraducidaVO.
     * 
     * @param alineamientoImg
     */
    public void setAlineamientoImg(java.lang.Integer alineamientoImg) {
        this.alineamientoImg = alineamientoImg;
    }


    /**
     * Gets the fechaPublicacion value for this NoticiaTraducidaVO.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this NoticiaTraducidaVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the autor value for this NoticiaTraducidaVO.
     * 
     * @return autor
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this NoticiaTraducidaVO.
     * 
     * @param autor
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the titulo value for this NoticiaTraducidaVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this NoticiaTraducidaVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the resumen value for this NoticiaTraducidaVO.
     * 
     * @return resumen
     */
    public java.lang.String getResumen() {
        return resumen;
    }


    /**
     * Sets the resumen value for this NoticiaTraducidaVO.
     * 
     * @param resumen
     */
    public void setResumen(java.lang.String resumen) {
        this.resumen = resumen;
    }


    /**
     * Gets the cuerpo value for this NoticiaTraducidaVO.
     * 
     * @return cuerpo
     */
    public java.lang.String getCuerpo() {
        return cuerpo;
    }


    /**
     * Sets the cuerpo value for this NoticiaTraducidaVO.
     * 
     * @param cuerpo
     */
    public void setCuerpo(java.lang.String cuerpo) {
        this.cuerpo = cuerpo;
    }


    /**
     * Gets the idiomas value for this NoticiaTraducidaVO.
     * 
     * @return idiomas
     */
    public java.lang.String getIdiomas() {
        return idiomas;
    }


    /**
     * Sets the idiomas value for this NoticiaTraducidaVO.
     * 
     * @param idiomas
     */
    public void setIdiomas(java.lang.String idiomas) {
        this.idiomas = idiomas;
    }


    /**
     * Gets the categoria value for this NoticiaTraducidaVO.
     * 
     * @return categoria
     */
    public java.lang.String getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this NoticiaTraducidaVO.
     * 
     * @param categoria
     */
    public void setCategoria(java.lang.String categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the idCategoria value for this NoticiaTraducidaVO.
     * 
     * @return idCategoria
     */
    public java.lang.Long getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this NoticiaTraducidaVO.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(java.lang.Long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the activa value for this NoticiaTraducidaVO.
     * 
     * @return activa   * Indica si la noticia est√° activa o no.
     */
    public java.lang.Boolean getActiva() {
        return activa;
    }


    /**
     * Sets the activa value for this NoticiaTraducidaVO.
     * 
     * @param activa   * Indica si la noticia est√° activa o no.
     */
    public void setActiva(java.lang.Boolean activa) {
        this.activa = activa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NoticiaTraducidaVO)) return false;
        NoticiaTraducidaVO other = (NoticiaTraducidaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idNoticia==null && other.getIdNoticia()==null) || 
             (this.idNoticia!=null &&
              this.idNoticia.equals(other.getIdNoticia()))) &&
            ((this.URLImagen==null && other.getURLImagen()==null) || 
             (this.URLImagen!=null &&
              this.URLImagen.equals(other.getURLImagen()))) &&
            ((this.alineamientoImg==null && other.getAlineamientoImg()==null) || 
             (this.alineamientoImg!=null &&
              this.alineamientoImg.equals(other.getAlineamientoImg()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.resumen==null && other.getResumen()==null) || 
             (this.resumen!=null &&
              this.resumen.equals(other.getResumen()))) &&
            ((this.cuerpo==null && other.getCuerpo()==null) || 
             (this.cuerpo!=null &&
              this.cuerpo.equals(other.getCuerpo()))) &&
            ((this.idiomas==null && other.getIdiomas()==null) || 
             (this.idiomas!=null &&
              this.idiomas.equals(other.getIdiomas()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.idCategoria==null && other.getIdCategoria()==null) || 
             (this.idCategoria!=null &&
              this.idCategoria.equals(other.getIdCategoria()))) &&
            ((this.activa==null && other.getActiva()==null) || 
             (this.activa!=null &&
              this.activa.equals(other.getActiva())));
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
        if (getIdNoticia() != null) {
            _hashCode += getIdNoticia().hashCode();
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
        if (getAutor() != null) {
            _hashCode += getAutor().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getResumen() != null) {
            _hashCode += getResumen().hashCode();
        }
        if (getCuerpo() != null) {
            _hashCode += getCuerpo().hashCode();
        }
        if (getIdiomas() != null) {
            _hashCode += getIdiomas().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getIdCategoria() != null) {
            _hashCode += getIdCategoria().hashCode();
        }
        if (getActiva() != null) {
            _hashCode += getActiva().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NoticiaTraducidaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaTraducidaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNoticia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idNoticia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("idiomas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idiomas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "activa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
