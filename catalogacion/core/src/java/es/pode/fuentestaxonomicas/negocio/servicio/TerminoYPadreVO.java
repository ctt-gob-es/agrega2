/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * TerminoYPadreVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;


/**
 * ValueObject que representa un termino de un vocabulario
 *                         controlado de LOM-ES. Consta de los atributos
 * idVocabulario,
 *                         nomTermino, idiomaTermino e idTermino.
 */
public class TerminoYPadreVO  implements java.io.Serializable {
    /* Identificador de uno de los vocabularios controlados dentro
     * del
     *                                 fichero xml expresado en vdex que
     * contiene todos los
     *                                 vocabularios de LOM-ES. Se corresponde
     * con el identificador dado
     *                                 en la especificacion LOM-ES para dicho
     * vocabulario. */
    private java.lang.String idVocabulario;

    /* Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino. */
    private java.lang.String nomTermino;

    /* Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino. */
    private java.lang.String idiomaTermino;

    /* Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES. */
    private java.lang.String idTermino;

    public TerminoYPadreVO() {
    }

    public TerminoYPadreVO(
           java.lang.String idVocabulario,
           java.lang.String nomTermino,
           java.lang.String idiomaTermino,
           java.lang.String idTermino) {
           this.idVocabulario = idVocabulario;
           this.nomTermino = nomTermino;
           this.idiomaTermino = idiomaTermino;
           this.idTermino = idTermino;
    }


    /**
     * Gets the idVocabulario value for this TerminoYPadreVO.
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
     * Sets the idVocabulario value for this TerminoYPadreVO.
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
     * Gets the nomTermino value for this TerminoYPadreVO.
     * 
     * @return nomTermino   * Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino.
     */
    public java.lang.String getNomTermino() {
        return nomTermino;
    }


    /**
     * Sets the nomTermino value for this TerminoYPadreVO.
     * 
     * @param nomTermino   * Valor de un termino de un vocabulario controlado de LOM-ES
     *                                 traducido al idioma indicado en el
     * atributo idiomtaTermino.
     */
    public void setNomTermino(java.lang.String nomTermino) {
        this.nomTermino = nomTermino;
    }


    /**
     * Gets the idiomaTermino value for this TerminoYPadreVO.
     * 
     * @return idiomaTermino   * Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino.
     */
    public java.lang.String getIdiomaTermino() {
        return idiomaTermino;
    }


    /**
     * Sets the idiomaTermino value for this TerminoYPadreVO.
     * 
     * @param idiomaTermino   * Idioma en el que se encuentra traducido el termino recogido
     * en
     *                                 el atributo nomTermino.
     */
    public void setIdiomaTermino(java.lang.String idiomaTermino) {
        this.idiomaTermino = idiomaTermino;
    }


    /**
     * Gets the idTermino value for this TerminoYPadreVO.
     * 
     * @return idTermino   * Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES.
     */
    public java.lang.String getIdTermino() {
        return idTermino;
    }


    /**
     * Sets the idTermino value for this TerminoYPadreVO.
     * 
     * @param idTermino   * Identificador del termino perteneciente a un vocabulario
     *                                 controlado de LOM-ES.
     */
    public void setIdTermino(java.lang.String idTermino) {
        this.idTermino = idTermino;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TerminoYPadreVO)) return false;
        TerminoYPadreVO other = (TerminoYPadreVO) obj;
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
            ((this.nomTermino==null && other.getNomTermino()==null) || 
             (this.nomTermino!=null &&
              this.nomTermino.equals(other.getNomTermino()))) &&
            ((this.idiomaTermino==null && other.getIdiomaTermino()==null) || 
             (this.idiomaTermino!=null &&
              this.idiomaTermino.equals(other.getIdiomaTermino()))) &&
            ((this.idTermino==null && other.getIdTermino()==null) || 
             (this.idTermino!=null &&
              this.idTermino.equals(other.getIdTermino())));
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
        if (getNomTermino() != null) {
            _hashCode += getNomTermino().hashCode();
        }
        if (getIdiomaTermino() != null) {
            _hashCode += getIdiomaTermino().hashCode();
        }
        if (getIdTermino() != null) {
            _hashCode += getIdTermino().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TerminoYPadreVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TerminoYPadreVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVocabulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idVocabulario"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idTermino"));
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
