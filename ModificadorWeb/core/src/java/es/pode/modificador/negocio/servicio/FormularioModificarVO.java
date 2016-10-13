/**
 * FormularioModificarVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;


/**
 * Bean con los datos necesarios para pintar un formulario de
 *                         modificar termino lomes.
 */
public class FormularioModificarVO  implements java.io.Serializable {
    /* Termino LOM-ES identificador por el índice (p.e. termino idioma
     * del general = 1.3) */
    private java.lang.String idTermino;

    /* Termino lomes identificador por los nombres de las etiquetas
     * (valor que se configurara en la configuracion de la tarea) - por
     *                                 ejemplo: lom.general.language */
    private java.lang.String nombreTermino;

    private java.lang.Boolean esCombo;

    /* En caso de que esCombo=true, se cargan en este array las
     *                                 opciones posibles para el idTermino
     * que se desea modificar. */
    private java.lang.String[] opcionesCombo;

    /* Indica si el campo es un langstring (el formulario de lenguaje
     * tiene que estar activado). */
    private java.lang.Boolean langString;

    private java.lang.Boolean esFinal;

    public FormularioModificarVO() {
    }

    public FormularioModificarVO(
           java.lang.String idTermino,
           java.lang.String nombreTermino,
           java.lang.Boolean esCombo,
           java.lang.String[] opcionesCombo,
           java.lang.Boolean langString,
           java.lang.Boolean esFinal) {
           this.idTermino = idTermino;
           this.nombreTermino = nombreTermino;
           this.esCombo = esCombo;
           this.opcionesCombo = opcionesCombo;
           this.langString = langString;
           this.esFinal = esFinal;
    }


    /**
     * Gets the idTermino value for this FormularioModificarVO.
     * 
     * @return idTermino   * Termino LOM-ES identificador por el índice (p.e. termino idioma
     * del general = 1.3)
     */
    public java.lang.String getIdTermino() {
        return idTermino;
    }


    /**
     * Sets the idTermino value for this FormularioModificarVO.
     * 
     * @param idTermino   * Termino LOM-ES identificador por el índice (p.e. termino idioma
     * del general = 1.3)
     */
    public void setIdTermino(java.lang.String idTermino) {
        this.idTermino = idTermino;
    }


    /**
     * Gets the nombreTermino value for this FormularioModificarVO.
     * 
     * @return nombreTermino   * Termino lomes identificador por los nombres de las etiquetas
     * (valor que se configurara en la configuracion de la tarea) - por
     *                                 ejemplo: lom.general.language
     */
    public java.lang.String getNombreTermino() {
        return nombreTermino;
    }


    /**
     * Sets the nombreTermino value for this FormularioModificarVO.
     * 
     * @param nombreTermino   * Termino lomes identificador por los nombres de las etiquetas
     * (valor que se configurara en la configuracion de la tarea) - por
     *                                 ejemplo: lom.general.language
     */
    public void setNombreTermino(java.lang.String nombreTermino) {
        this.nombreTermino = nombreTermino;
    }


    /**
     * Gets the esCombo value for this FormularioModificarVO.
     * 
     * @return esCombo
     */
    public java.lang.Boolean getEsCombo() {
        return esCombo;
    }


    /**
     * Sets the esCombo value for this FormularioModificarVO.
     * 
     * @param esCombo
     */
    public void setEsCombo(java.lang.Boolean esCombo) {
        this.esCombo = esCombo;
    }


    /**
     * Gets the opcionesCombo value for this FormularioModificarVO.
     * 
     * @return opcionesCombo   * En caso de que esCombo=true, se cargan en este array las
     *                                 opciones posibles para el idTermino
     * que se desea modificar.
     */
    public java.lang.String[] getOpcionesCombo() {
        return opcionesCombo;
    }


    /**
     * Sets the opcionesCombo value for this FormularioModificarVO.
     * 
     * @param opcionesCombo   * En caso de que esCombo=true, se cargan en este array las
     *                                 opciones posibles para el idTermino
     * que se desea modificar.
     */
    public void setOpcionesCombo(java.lang.String[] opcionesCombo) {
        this.opcionesCombo = opcionesCombo;
    }


    /**
     * Gets the langString value for this FormularioModificarVO.
     * 
     * @return langString   * Indica si el campo es un langstring (el formulario de lenguaje
     * tiene que estar activado).
     */
    public java.lang.Boolean getLangString() {
        return langString;
    }


    /**
     * Sets the langString value for this FormularioModificarVO.
     * 
     * @param langString   * Indica si el campo es un langstring (el formulario de lenguaje
     * tiene que estar activado).
     */
    public void setLangString(java.lang.Boolean langString) {
        this.langString = langString;
    }


    /**
     * Gets the esFinal value for this FormularioModificarVO.
     * 
     * @return esFinal
     */
    public java.lang.Boolean getEsFinal() {
        return esFinal;
    }


    /**
     * Sets the esFinal value for this FormularioModificarVO.
     * 
     * @param esFinal
     */
    public void setEsFinal(java.lang.Boolean esFinal) {
        this.esFinal = esFinal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FormularioModificarVO)) return false;
        FormularioModificarVO other = (FormularioModificarVO) obj;
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
            ((this.nombreTermino==null && other.getNombreTermino()==null) || 
             (this.nombreTermino!=null &&
              this.nombreTermino.equals(other.getNombreTermino()))) &&
            ((this.esCombo==null && other.getEsCombo()==null) || 
             (this.esCombo!=null &&
              this.esCombo.equals(other.getEsCombo()))) &&
            ((this.opcionesCombo==null && other.getOpcionesCombo()==null) || 
             (this.opcionesCombo!=null &&
              java.util.Arrays.equals(this.opcionesCombo, other.getOpcionesCombo()))) &&
            ((this.langString==null && other.getLangString()==null) || 
             (this.langString!=null &&
              this.langString.equals(other.getLangString()))) &&
            ((this.esFinal==null && other.getEsFinal()==null) || 
             (this.esFinal!=null &&
              this.esFinal.equals(other.getEsFinal())));
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
        if (getNombreTermino() != null) {
            _hashCode += getNombreTermino().hashCode();
        }
        if (getEsCombo() != null) {
            _hashCode += getEsCombo().hashCode();
        }
        if (getOpcionesCombo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOpcionesCombo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOpcionesCombo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLangString() != null) {
            _hashCode += getLangString().hashCode();
        }
        if (getEsFinal() != null) {
            _hashCode += getEsFinal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FormularioModificarVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "FormularioModificarVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "nombreTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esCombo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "esCombo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opcionesCombo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "opcionesCombo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("langString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "langString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "esFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
