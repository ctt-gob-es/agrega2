/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * NoticiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * Value Object que encapsula los datos correspondientes a la
 *                         noticia.
 */
public class NoticiaVO  implements java.io.Serializable {
    /* Identificador autogenerado por la base de datos. */
    private java.lang.Long id;

    /* Direccion URL de la fuente de la imagen. */
    private java.lang.String URLImagen;

    /* Entero que indica un valor constante sobre las posibles a
     *                                 lineaciones que se contemplan. */
    private java.lang.Integer alineamientoImg;

    /* Flag que permite distinguir entre noticias activas o inactivas. */
    private java.lang.Boolean activa;

    /* Fecha de publicacion de la noticia. */
    private java.util.Calendar fechaPublicacion;

    private java.lang.String autor;

    private java.util.Calendar fechaBaja;

    private java.util.Calendar fechaModificacion;

    private es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria;

    private es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO[] descripcionNoticia;

    public NoticiaVO() {
    }

    public NoticiaVO(
           java.lang.Long id,
           java.lang.String URLImagen,
           java.lang.Integer alineamientoImg,
           java.lang.Boolean activa,
           java.util.Calendar fechaPublicacion,
           java.lang.String autor,
           java.util.Calendar fechaBaja,
           java.util.Calendar fechaModificacion,
           es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria,
           es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO[] descripcionNoticia) {
           this.id = id;
           this.URLImagen = URLImagen;
           this.alineamientoImg = alineamientoImg;
           this.activa = activa;
           this.fechaPublicacion = fechaPublicacion;
           this.autor = autor;
           this.fechaBaja = fechaBaja;
           this.fechaModificacion = fechaModificacion;
           this.categoria = categoria;
           this.descripcionNoticia = descripcionNoticia;
    }


    /**
     * Gets the id value for this NoticiaVO.
     * 
     * @return id   * Identificador autogenerado por la base de datos.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this NoticiaVO.
     * 
     * @param id   * Identificador autogenerado por la base de datos.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the URLImagen value for this NoticiaVO.
     * 
     * @return URLImagen   * Direccion URL de la fuente de la imagen.
     */
    public java.lang.String getURLImagen() {
        return URLImagen;
    }


    /**
     * Sets the URLImagen value for this NoticiaVO.
     * 
     * @param URLImagen   * Direccion URL de la fuente de la imagen.
     */
    public void setURLImagen(java.lang.String URLImagen) {
        this.URLImagen = URLImagen;
    }


    /**
     * Gets the alineamientoImg value for this NoticiaVO.
     * 
     * @return alineamientoImg   * Entero que indica un valor constante sobre las posibles a
     *                                 lineaciones que se contemplan.
     */
    public java.lang.Integer getAlineamientoImg() {
        return alineamientoImg;
    }


    /**
     * Sets the alineamientoImg value for this NoticiaVO.
     * 
     * @param alineamientoImg   * Entero que indica un valor constante sobre las posibles a
     *                                 lineaciones que se contemplan.
     */
    public void setAlineamientoImg(java.lang.Integer alineamientoImg) {
        this.alineamientoImg = alineamientoImg;
    }


    /**
     * Gets the activa value for this NoticiaVO.
     * 
     * @return activa   * Flag que permite distinguir entre noticias activas o inactivas.
     */
    public java.lang.Boolean getActiva() {
        return activa;
    }


    /**
     * Sets the activa value for this NoticiaVO.
     * 
     * @param activa   * Flag que permite distinguir entre noticias activas o inactivas.
     */
    public void setActiva(java.lang.Boolean activa) {
        this.activa = activa;
    }


    /**
     * Gets the fechaPublicacion value for this NoticiaVO.
     * 
     * @return fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this NoticiaVO.
     * 
     * @param fechaPublicacion   * Fecha de publicacion de la noticia.
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the autor value for this NoticiaVO.
     * 
     * @return autor
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this NoticiaVO.
     * 
     * @param autor
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the fechaBaja value for this NoticiaVO.
     * 
     * @return fechaBaja
     */
    public java.util.Calendar getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this NoticiaVO.
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(java.util.Calendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the fechaModificacion value for this NoticiaVO.
     * 
     * @return fechaModificacion
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this NoticiaVO.
     * 
     * @param fechaModificacion
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the categoria value for this NoticiaVO.
     * 
     * @return categoria
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this NoticiaVO.
     * 
     * @param categoria
     */
    public void setCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the descripcionNoticia value for this NoticiaVO.
     * 
     * @return descripcionNoticia
     */
    public es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO[] getDescripcionNoticia() {
        return descripcionNoticia;
    }


    /**
     * Sets the descripcionNoticia value for this NoticiaVO.
     * 
     * @param descripcionNoticia
     */
    public void setDescripcionNoticia(es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO[] descripcionNoticia) {
        this.descripcionNoticia = descripcionNoticia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NoticiaVO)) return false;
        NoticiaVO other = (NoticiaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.URLImagen==null && other.getURLImagen()==null) || 
             (this.URLImagen!=null &&
              this.URLImagen.equals(other.getURLImagen()))) &&
            ((this.alineamientoImg==null && other.getAlineamientoImg()==null) || 
             (this.alineamientoImg!=null &&
              this.alineamientoImg.equals(other.getAlineamientoImg()))) &&
            ((this.activa==null && other.getActiva()==null) || 
             (this.activa!=null &&
              this.activa.equals(other.getActiva()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.fechaBaja==null && other.getFechaBaja()==null) || 
             (this.fechaBaja!=null &&
              this.fechaBaja.equals(other.getFechaBaja()))) &&
            ((this.fechaModificacion==null && other.getFechaModificacion()==null) || 
             (this.fechaModificacion!=null &&
              this.fechaModificacion.equals(other.getFechaModificacion()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.descripcionNoticia==null && other.getDescripcionNoticia()==null) || 
             (this.descripcionNoticia!=null &&
              java.util.Arrays.equals(this.descripcionNoticia, other.getDescripcionNoticia())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getURLImagen() != null) {
            _hashCode += getURLImagen().hashCode();
        }
        if (getAlineamientoImg() != null) {
            _hashCode += getAlineamientoImg().hashCode();
        }
        if (getActiva() != null) {
            _hashCode += getActiva().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getAutor() != null) {
            _hashCode += getAutor().hashCode();
        }
        if (getFechaBaja() != null) {
            _hashCode += getFechaBaja().hashCode();
        }
        if (getFechaModificacion() != null) {
            _hashCode += getFechaModificacion().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getDescripcionNoticia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescripcionNoticia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescripcionNoticia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NoticiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
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
        elemField.setFieldName("activa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "activa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
        elemField.setFieldName("fechaBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "fechaBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "fechaModificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionNoticia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "descripcionNoticia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "DescripcionNoticiaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
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
