/**
 * TerminoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;


/**
 * Termino de un vocabulario controlado de LOM-ES. Se compone de:
 *                         idTermino, nomTermino e idiomaTermino.
 */
public class TerminoVO  implements java.io.Serializable {
    /* Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES. */
    private java.lang.String idTermino;

    /* Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino. */
    private java.lang.String nomTermino;

    /* Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino. */
    private java.lang.String idiomaTermino;

    public TerminoVO() {
    }

    public TerminoVO(
           java.lang.String idTermino,
           java.lang.String nomTermino,
           java.lang.String idiomaTermino) {
           this.idTermino = idTermino;
           this.nomTermino = nomTermino;
           this.idiomaTermino = idiomaTermino;
    }


    /**
     * Gets the idTermino value for this TerminoVO.
     * 
     * @return idTermino   * Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES.
     */
    public java.lang.String getIdTermino() {
        return idTermino;
    }


    /**
     * Sets the idTermino value for this TerminoVO.
     * 
     * @param idTermino   * Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES.
     */
    public void setIdTermino(java.lang.String idTermino) {
        this.idTermino = idTermino;
    }


    /**
     * Gets the nomTermino value for this TerminoVO.
     * 
     * @return nomTermino   * Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino.
     */
    public java.lang.String getNomTermino() {
        return nomTermino;
    }


    /**
     * Sets the nomTermino value for this TerminoVO.
     * 
     * @param nomTermino   * Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino.
     */
    public void setNomTermino(java.lang.String nomTermino) {
        this.nomTermino = nomTermino;
    }


    /**
     * Gets the idiomaTermino value for this TerminoVO.
     * 
     * @return idiomaTermino   * Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino.
     */
    public java.lang.String getIdiomaTermino() {
        return idiomaTermino;
    }


    /**
     * Sets the idiomaTermino value for this TerminoVO.
     * 
     * @param idiomaTermino   * Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino.
     */
    public void setIdiomaTermino(java.lang.String idiomaTermino) {
        this.idiomaTermino = idiomaTermino;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TerminoVO)) return false;
        TerminoVO other = (TerminoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTermino==null && other.getIdTermino()==null) || 
             (this.idTermino!=null &&
              this.idTermino.equals(other.getIdTermino()))) &&
            ((this.nomTermino==null && other.getNomTermino()==null) || 
             (this.nomTermino!=null &&
              this.nomTermino.equals(other.getNomTermino()))) &&
            ((this.idiomaTermino==null && other.getIdiomaTermino()==null) || 
             (this.idiomaTermino!=null &&
              this.idiomaTermino.equals(other.getIdiomaTermino())));
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
        if (getIdTermino() != null) {
            _hashCode += getIdTermino().hashCode();
        }
        if (getNomTermino() != null) {
            _hashCode += getNomTermino().hashCode();
        }
        if (getIdiomaTermino() != null) {
            _hashCode += getIdiomaTermino().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TerminoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TerminoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idiomaTermino"));
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
