/**
 * CategoriaNoticiaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;


/**
 * VO que encapsula la información de una categoría de la noticias.
 */
public class CategoriaNoticiaVO  implements java.io.Serializable {
    /* Identificador autogenerado por la base de datos. */
    private java.lang.Long id;

    /* Nombre de la categoría. */
    private java.lang.String nombre;

    /* Fecha de creación de la categoría noticia. */
    private java.util.Calendar fechaPublicacion;

    /* Fecha de baja de la categoría noticia. */
    private java.util.Calendar fechaBaja;

    /* Fecha de modificación de la categoría noticia. */
    private java.util.Calendar fechaModificacion;

    private es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO[] categoriaIdioma;

    public CategoriaNoticiaVO() {
    }

    public CategoriaNoticiaVO(
           java.lang.Long id,
           java.lang.String nombre,
           java.util.Calendar fechaPublicacion,
           java.util.Calendar fechaBaja,
           java.util.Calendar fechaModificacion,
           es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO[] categoriaIdioma) {
           this.id = id;
           this.nombre = nombre;
           this.fechaPublicacion = fechaPublicacion;
           this.fechaBaja = fechaBaja;
           this.fechaModificacion = fechaModificacion;
           this.categoriaIdioma = categoriaIdioma;
    }


    /**
     * Gets the id value for this CategoriaNoticiaVO.
     * 
     * @return id   * Identificador autogenerado por la base de datos.
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this CategoriaNoticiaVO.
     * 
     * @param id   * Identificador autogenerado por la base de datos.
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this CategoriaNoticiaVO.
     * 
     * @return nombre   * Nombre de la categoría.
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this CategoriaNoticiaVO.
     * 
     * @param nombre   * Nombre de la categoría.
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the fechaPublicacion value for this CategoriaNoticiaVO.
     * 
     * @return fechaPublicacion   * Fecha de creación de la categoría noticia.
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this CategoriaNoticiaVO.
     * 
     * @param fechaPublicacion   * Fecha de creación de la categoría noticia.
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the fechaBaja value for this CategoriaNoticiaVO.
     * 
     * @return fechaBaja   * Fecha de baja de la categoría noticia.
     */
    public java.util.Calendar getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this CategoriaNoticiaVO.
     * 
     * @param fechaBaja   * Fecha de baja de la categoría noticia.
     */
    public void setFechaBaja(java.util.Calendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the fechaModificacion value for this CategoriaNoticiaVO.
     * 
     * @return fechaModificacion   * Fecha de modificación de la categoría noticia.
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this CategoriaNoticiaVO.
     * 
     * @param fechaModificacion   * Fecha de modificación de la categoría noticia.
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the categoriaIdioma value for this CategoriaNoticiaVO.
     * 
     * @return categoriaIdioma
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO[] getCategoriaIdioma() {
        return categoriaIdioma;
    }


    /**
     * Sets the categoriaIdioma value for this CategoriaNoticiaVO.
     * 
     * @param categoriaIdioma
     */
    public void setCategoriaIdioma(es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO[] categoriaIdioma) {
        this.categoriaIdioma = categoriaIdioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CategoriaNoticiaVO)) return false;
        CategoriaNoticiaVO other = (CategoriaNoticiaVO) obj;
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
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.fechaBaja==null && other.getFechaBaja()==null) || 
             (this.fechaBaja!=null &&
              this.fechaBaja.equals(other.getFechaBaja()))) &&
            ((this.fechaModificacion==null && other.getFechaModificacion()==null) || 
             (this.fechaModificacion!=null &&
              this.fechaModificacion.equals(other.getFechaModificacion()))) &&
            ((this.categoriaIdioma==null && other.getCategoriaIdioma()==null) || 
             (this.categoriaIdioma!=null &&
              java.util.Arrays.equals(this.categoriaIdioma, other.getCategoriaIdioma())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getFechaBaja() != null) {
            _hashCode += getFechaBaja().hashCode();
        }
        if (getFechaModificacion() != null) {
            _hashCode += getFechaModificacion().hashCode();
        }
        if (getCategoriaIdioma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCategoriaIdioma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCategoriaIdioma(), i);
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
        new org.apache.axis.description.TypeDesc(CategoriaNoticiaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("categoriaIdioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoriaIdioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaIdiomaNoticiaVO"));
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
