/**
 * NuevoFaqVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.faqs.servicio;


/**
 * Value Object que encapsula los datos necesarios para la creacion
 * de una nueva FAQ.
 */
public class NuevoFaqVO  implements java.io.Serializable {
    /* Texto de la pregunta. */
    private java.lang.String pregunta;

    /* Respuesta a la pregunta. */
    private java.lang.String respuesta;

    /* Posicion asignada a la pregunta. */
    private java.lang.Integer posicion;

    /* Idioma de la FAQ en el codigo ISO639 */
    private java.lang.String idioma;

    private es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoriaFaq;

    public NuevoFaqVO() {
    }

    public NuevoFaqVO(
           java.lang.String pregunta,
           java.lang.String respuesta,
           java.lang.Integer posicion,
           java.lang.String idioma,
           es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoriaFaq) {
           this.pregunta = pregunta;
           this.respuesta = respuesta;
           this.posicion = posicion;
           this.idioma = idioma;
           this.categoriaFaq = categoriaFaq;
    }


    /**
     * Gets the pregunta value for this NuevoFaqVO.
     * 
     * @return pregunta   * Texto de la pregunta.
     */
    public java.lang.String getPregunta() {
        return pregunta;
    }


    /**
     * Sets the pregunta value for this NuevoFaqVO.
     * 
     * @param pregunta   * Texto de la pregunta.
     */
    public void setPregunta(java.lang.String pregunta) {
        this.pregunta = pregunta;
    }


    /**
     * Gets the respuesta value for this NuevoFaqVO.
     * 
     * @return respuesta   * Respuesta a la pregunta.
     */
    public java.lang.String getRespuesta() {
        return respuesta;
    }


    /**
     * Sets the respuesta value for this NuevoFaqVO.
     * 
     * @param respuesta   * Respuesta a la pregunta.
     */
    public void setRespuesta(java.lang.String respuesta) {
        this.respuesta = respuesta;
    }


    /**
     * Gets the posicion value for this NuevoFaqVO.
     * 
     * @return posicion   * Posicion asignada a la pregunta.
     */
    public java.lang.Integer getPosicion() {
        return posicion;
    }


    /**
     * Sets the posicion value for this NuevoFaqVO.
     * 
     * @param posicion   * Posicion asignada a la pregunta.
     */
    public void setPosicion(java.lang.Integer posicion) {
        this.posicion = posicion;
    }


    /**
     * Gets the idioma value for this NuevoFaqVO.
     * 
     * @return idioma   * Idioma de la FAQ en el codigo ISO639
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this NuevoFaqVO.
     * 
     * @param idioma   * Idioma de la FAQ en el codigo ISO639
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the categoriaFaq value for this NuevoFaqVO.
     * 
     * @return categoriaFaq
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO getCategoriaFaq() {
        return categoriaFaq;
    }


    /**
     * Sets the categoriaFaq value for this NuevoFaqVO.
     * 
     * @param categoriaFaq
     */
    public void setCategoriaFaq(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoriaFaq) {
        this.categoriaFaq = categoriaFaq;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NuevoFaqVO)) return false;
        NuevoFaqVO other = (NuevoFaqVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pregunta==null && other.getPregunta()==null) || 
             (this.pregunta!=null &&
              this.pregunta.equals(other.getPregunta()))) &&
            ((this.respuesta==null && other.getRespuesta()==null) || 
             (this.respuesta!=null &&
              this.respuesta.equals(other.getRespuesta()))) &&
            ((this.posicion==null && other.getPosicion()==null) || 
             (this.posicion!=null &&
              this.posicion.equals(other.getPosicion()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.categoriaFaq==null && other.getCategoriaFaq()==null) || 
             (this.categoriaFaq!=null &&
              this.categoriaFaq.equals(other.getCategoriaFaq())));
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
        if (getPregunta() != null) {
            _hashCode += getPregunta().hashCode();
        }
        if (getRespuesta() != null) {
            _hashCode += getRespuesta().hashCode();
        }
        if (getPosicion() != null) {
            _hashCode += getPosicion().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getCategoriaFaq() != null) {
            _hashCode += getCategoriaFaq().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NuevoFaqVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "NuevoFaqVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pregunta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "pregunta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "posicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaFaq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "categoriaFaq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.faqs.negocio.contenidos.pode.es", "CategoriaFaqVO"));
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
