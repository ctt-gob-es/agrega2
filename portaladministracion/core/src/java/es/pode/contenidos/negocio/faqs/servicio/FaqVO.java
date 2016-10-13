/**
 * FaqVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.faqs.servicio;


/**
 * Value Object que encapsula los datos necesarios para ofrecer un
 * listado de preguntas frecuentes.
 */
public class FaqVO  implements java.io.Serializable {
    /* ID de la entity FAQ */
    private java.lang.Long id;

    /* Fecha de última modificacion. */
    private java.util.Calendar fechaModificacion;

    private java.util.Calendar fechaBaja;

    private java.util.Calendar fechaPublicacion;

    private es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoria;

    private es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO[] descripcionFaq;

    public FaqVO() {
    }

    public FaqVO(
           java.lang.Long id,
           java.util.Calendar fechaModificacion,
           java.util.Calendar fechaBaja,
           java.util.Calendar fechaPublicacion,
           es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoria,
           es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO[] descripcionFaq) {
           this.id = id;
           this.fechaModificacion = fechaModificacion;
           this.fechaBaja = fechaBaja;
           this.fechaPublicacion = fechaPublicacion;
           this.categoria = categoria;
           this.descripcionFaq = descripcionFaq;
    }


    /**
     * Gets the id value for this FaqVO.
     * 
     * @return id   * ID de la entity FAQ
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this FaqVO.
     * 
     * @param id   * ID de la entity FAQ
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the fechaModificacion value for this FaqVO.
     * 
     * @return fechaModificacion   * Fecha de última modificacion.
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this FaqVO.
     * 
     * @param fechaModificacion   * Fecha de última modificacion.
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the fechaBaja value for this FaqVO.
     * 
     * @return fechaBaja
     */
    public java.util.Calendar getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this FaqVO.
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(java.util.Calendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the fechaPublicacion value for this FaqVO.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this FaqVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the categoria value for this FaqVO.
     * 
     * @return categoria
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this FaqVO.
     * 
     * @param categoria
     */
    public void setCategoria(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the descripcionFaq value for this FaqVO.
     * 
     * @return descripcionFaq
     */
    public es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO[] getDescripcionFaq() {
        return descripcionFaq;
    }


    /**
     * Sets the descripcionFaq value for this FaqVO.
     * 
     * @param descripcionFaq
     */
    public void setDescripcionFaq(es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO[] descripcionFaq) {
        this.descripcionFaq = descripcionFaq;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FaqVO)) return false;
        FaqVO other = (FaqVO) obj;
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
            ((this.fechaModificacion==null && other.getFechaModificacion()==null) || 
             (this.fechaModificacion!=null &&
              this.fechaModificacion.equals(other.getFechaModificacion()))) &&
            ((this.fechaBaja==null && other.getFechaBaja()==null) || 
             (this.fechaBaja!=null &&
              this.fechaBaja.equals(other.getFechaBaja()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.descripcionFaq==null && other.getDescripcionFaq()==null) || 
             (this.descripcionFaq!=null &&
              java.util.Arrays.equals(this.descripcionFaq, other.getDescripcionFaq())));
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
        if (getFechaModificacion() != null) {
            _hashCode += getFechaModificacion().hashCode();
        }
        if (getFechaBaja() != null) {
            _hashCode += getFechaBaja().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getDescripcionFaq() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescripcionFaq());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescripcionFaq(), i);
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
        new org.apache.axis.description.TypeDesc(FaqVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "FaqVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "fechaModificacion"));
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
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "CategoriaFaqVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionFaq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "descripcionFaq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "DescripcionFaqVO"));
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
