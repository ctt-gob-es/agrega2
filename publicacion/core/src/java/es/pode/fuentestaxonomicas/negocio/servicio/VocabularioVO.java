/**
 * VocabularioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;


/**
 * ValueObject que representa un vocabulario controlado de LOM-ES.
 * Esta compuesto de uno o mas objetos del tipo TerminoVO y consta
 *                         de los atributos idVocabulario, nomVocabulario
 * e idioma.
 */
public class VocabularioVO  implements java.io.Serializable {
    /* Identificador de uno de los vocabularios controlados dentro
     * del
     *                                 fichero xml expresado en vdex que
     * contiene todos los
     *                                 vocabularios de LOM-ES. Se corresponde
     * con el identificador dado
     *                                 en la especificacion LOM-ES para dicho
     * vocabulario. */
    private java.lang.String idVocabulario;

    /* Es el nombre del vocabulario controlado de LOM-ES representado
     * por este ValueObject. */
    private java.lang.String nomVocabulario;

    /* Idioma en el que se encuentran los terminos asociados al
     *                                 vocabulario controlado representado
     * por este ValueObject. */
    private java.lang.String idioma;

    private es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO[] terminos;

    public VocabularioVO() {
    }

    public VocabularioVO(
           java.lang.String idVocabulario,
           java.lang.String nomVocabulario,
           java.lang.String idioma,
           es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO[] terminos) {
           this.idVocabulario = idVocabulario;
           this.nomVocabulario = nomVocabulario;
           this.idioma = idioma;
           this.terminos = terminos;
    }


    /**
     * Gets the idVocabulario value for this VocabularioVO.
     * 
     * @return idVocabulario   * Identificador de uno de los vocabularios controlados dentro
     * del
     *                                 fichero xml expresado en vdex que
     * contiene todos los
     *                                 vocabularios de LOM-ES. Se corresponde
     * con el identificador dado
     *                                 en la especificacion LOM-ES para dicho
     * vocabulario.
     */
    public java.lang.String getIdVocabulario() {
        return idVocabulario;
    }


    /**
     * Sets the idVocabulario value for this VocabularioVO.
     * 
     * @param idVocabulario   * Identificador de uno de los vocabularios controlados dentro
     * del
     *                                 fichero xml expresado en vdex que
     * contiene todos los
     *                                 vocabularios de LOM-ES. Se corresponde
     * con el identificador dado
     *                                 en la especificacion LOM-ES para dicho
     * vocabulario.
     */
    public void setIdVocabulario(java.lang.String idVocabulario) {
        this.idVocabulario = idVocabulario;
    }


    /**
     * Gets the nomVocabulario value for this VocabularioVO.
     * 
     * @return nomVocabulario   * Es el nombre del vocabulario controlado de LOM-ES representado
     * por este ValueObject.
     */
    public java.lang.String getNomVocabulario() {
        return nomVocabulario;
    }


    /**
     * Sets the nomVocabulario value for this VocabularioVO.
     * 
     * @param nomVocabulario   * Es el nombre del vocabulario controlado de LOM-ES representado
     * por este ValueObject.
     */
    public void setNomVocabulario(java.lang.String nomVocabulario) {
        this.nomVocabulario = nomVocabulario;
    }


    /**
     * Gets the idioma value for this VocabularioVO.
     * 
     * @return idioma   * Idioma en el que se encuentran los terminos asociados al
     *                                 vocabulario controlado representado
     * por este ValueObject.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this VocabularioVO.
     * 
     * @param idioma   * Idioma en el que se encuentran los terminos asociados al
     *                                 vocabulario controlado representado
     * por este ValueObject.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the terminos value for this VocabularioVO.
     * 
     * @return terminos
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO[] getTerminos() {
        return terminos;
    }


    /**
     * Sets the terminos value for this VocabularioVO.
     * 
     * @param terminos
     */
    public void setTerminos(es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO[] terminos) {
        this.terminos = terminos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VocabularioVO)) return false;
        VocabularioVO other = (VocabularioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idVocabulario==null && other.getIdVocabulario()==null) || 
             (this.idVocabulario!=null &&
              this.idVocabulario.equals(other.getIdVocabulario()))) &&
            ((this.nomVocabulario==null && other.getNomVocabulario()==null) || 
             (this.nomVocabulario!=null &&
              this.nomVocabulario.equals(other.getNomVocabulario()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.terminos==null && other.getTerminos()==null) || 
             (this.terminos!=null &&
              java.util.Arrays.equals(this.terminos, other.getTerminos())));
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
        if (getIdVocabulario() != null) {
            _hashCode += getIdVocabulario().hashCode();
        }
        if (getNomVocabulario() != null) {
            _hashCode += getNomVocabulario().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getTerminos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTerminos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTerminos(), i);
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
        new org.apache.axis.description.TypeDesc(VocabularioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "VocabularioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVocabulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idVocabulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomVocabulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomVocabulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "terminos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TerminoVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
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
