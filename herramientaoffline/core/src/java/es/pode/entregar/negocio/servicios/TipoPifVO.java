/**
 * TipoPifVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class TipoPifVO  implements java.io.Serializable {
    /* Identificador del ODE a exportar */
    private java.lang.String idODE;

    /* indica el tipo de formato que se quiere exportar. los posibles
     * valores posibles son:
     *                                 SCORM_2004
     *                                 SCORM_2004_SIN_SUBMANIFIESTO
     *                                 SCORM_12
     *                                 IMS_CP */
    private java.lang.String tipoPif;

    private java.lang.String idioma;

    public TipoPifVO() {
    }

    public TipoPifVO(
           java.lang.String idODE,
           java.lang.String tipoPif,
           java.lang.String idioma) {
           this.idODE = idODE;
           this.tipoPif = tipoPif;
           this.idioma = idioma;
    }


    /**
     * Gets the idODE value for this TipoPifVO.
     * 
     * @return idODE   * Identificador del ODE a exportar
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this TipoPifVO.
     * 
     * @param idODE   * Identificador del ODE a exportar
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }


    /**
     * Gets the tipoPif value for this TipoPifVO.
     * 
     * @return tipoPif   * indica el tipo de formato que se quiere exportar. los posibles
     * valores posibles son:
     *                                 SCORM_2004
     *                                 SCORM_2004_SIN_SUBMANIFIESTO
     *                                 SCORM_12
     *                                 IMS_CP
     */
    public java.lang.String getTipoPif() {
        return tipoPif;
    }


    /**
     * Sets the tipoPif value for this TipoPifVO.
     * 
     * @param tipoPif   * indica el tipo de formato que se quiere exportar. los posibles
     * valores posibles son:
     *                                 SCORM_2004
     *                                 SCORM_2004_SIN_SUBMANIFIESTO
     *                                 SCORM_12
     *                                 IMS_CP
     */
    public void setTipoPif(java.lang.String tipoPif) {
        this.tipoPif = tipoPif;
    }


    /**
     * Gets the idioma value for this TipoPifVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this TipoPifVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoPifVO)) return false;
        TipoPifVO other = (TipoPifVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE()))) &&
            ((this.tipoPif==null && other.getTipoPif()==null) || 
             (this.tipoPif!=null &&
              this.tipoPif.equals(other.getTipoPif()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma())));
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
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        if (getTipoPif() != null) {
            _hashCode += getTipoPif().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoPifVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "TipoPifVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPif");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "tipoPif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
