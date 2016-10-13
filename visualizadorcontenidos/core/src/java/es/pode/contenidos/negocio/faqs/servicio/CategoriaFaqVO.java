/**
 * CategoriaFaqVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.faqs.servicio;


/**
 * La categoria de la faq
 */
public class CategoriaFaqVO  implements java.io.Serializable {
    /* El identificador de la categoria de la faq */
    private java.lang.Long id;

    /* el nombre de la categoria de la faq */
    private java.lang.String nombre;

    private java.util.Calendar fechaAlta;

    private java.util.Calendar fechaBaja;

    private java.util.Calendar fechaModificacion;

    private es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO[] categoriaIdioma;

    public CategoriaFaqVO() {
    }

    public CategoriaFaqVO(
           java.lang.Long id,
           java.lang.String nombre,
           java.util.Calendar fechaAlta,
           java.util.Calendar fechaBaja,
           java.util.Calendar fechaModificacion,
           es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO[] categoriaIdioma) {
           this.id = id;
           this.nombre = nombre;
           this.fechaAlta = fechaAlta;
           this.fechaBaja = fechaBaja;
           this.fechaModificacion = fechaModificacion;
           this.categoriaIdioma = categoriaIdioma;
    }


    /**
     * Gets the id value for this CategoriaFaqVO.
     * 
     * @return id   * El identificador de la categoria de la faq
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this CategoriaFaqVO.
     * 
     * @param id   * El identificador de la categoria de la faq
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this CategoriaFaqVO.
     * 
     * @return nombre   * el nombre de la categoria de la faq
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this CategoriaFaqVO.
     * 
     * @param nombre   * el nombre de la categoria de la faq
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the fechaAlta value for this CategoriaFaqVO.
     * 
     * @return fechaAlta
     */
    public java.util.Calendar getFechaAlta() {
        return fechaAlta;
    }


    /**
     * Sets the fechaAlta value for this CategoriaFaqVO.
     * 
     * @param fechaAlta
     */
    public void setFechaAlta(java.util.Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    /**
     * Gets the fechaBaja value for this CategoriaFaqVO.
     * 
     * @return fechaBaja
     */
    public java.util.Calendar getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this CategoriaFaqVO.
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(java.util.Calendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the fechaModificacion value for this CategoriaFaqVO.
     * 
     * @return fechaModificacion
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this CategoriaFaqVO.
     * 
     * @param fechaModificacion
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the categoriaIdioma value for this CategoriaFaqVO.
     * 
     * @return categoriaIdioma
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO[] getCategoriaIdioma() {
        return categoriaIdioma;
    }


    /**
     * Sets the categoriaIdioma value for this CategoriaFaqVO.
     * 
     * @param categoriaIdioma
     */
    public void setCategoriaIdioma(es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO[] categoriaIdioma) {
        this.categoriaIdioma = categoriaIdioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CategoriaFaqVO)) return false;
        CategoriaFaqVO other = (CategoriaFaqVO) obj;
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
            ((this.fechaAlta==null && other.getFechaAlta()==null) || 
             (this.fechaAlta!=null &&
              this.fechaAlta.equals(other.getFechaAlta()))) &&
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
        if (getFechaAlta() != null) {
            _hashCode += getFechaAlta().hashCode();
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
        new org.apache.axis.description.TypeDesc(CategoriaFaqVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "CategoriaFaqVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "fechaAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "fechaBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "fechaModificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaIdioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "categoriaIdioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "CategoriaIdiomaFaqVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "item"));
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
