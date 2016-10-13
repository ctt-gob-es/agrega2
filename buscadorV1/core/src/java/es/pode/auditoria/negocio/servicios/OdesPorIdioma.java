/**
 * OdesPorIdioma.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con el número de odes publicados en un idioma
 */
public class OdesPorIdioma  implements java.io.Serializable {
    /* Idioma en el que se obtendrá el número de odes */
    private java.lang.String idioma;

    /* Número de odes publicados en un idioma */
    private long numeroOdes;

    /* idioma traducido a traves de soporte del atributo idioma */
    private java.lang.String idiomaTraducido;

    public OdesPorIdioma() {
    }

    public OdesPorIdioma(
           java.lang.String idioma,
           long numeroOdes,
           java.lang.String idiomaTraducido) {
           this.idioma = idioma;
           this.numeroOdes = numeroOdes;
           this.idiomaTraducido = idiomaTraducido;
    }


    /**
     * Gets the idioma value for this OdesPorIdioma.
     * 
     * @return idioma   * Idioma en el que se obtendrá el número de odes
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this OdesPorIdioma.
     * 
     * @param idioma   * Idioma en el que se obtendrá el número de odes
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the numeroOdes value for this OdesPorIdioma.
     * 
     * @return numeroOdes   * Número de odes publicados en un idioma
     */
    public long getNumeroOdes() {
        return numeroOdes;
    }


    /**
     * Sets the numeroOdes value for this OdesPorIdioma.
     * 
     * @param numeroOdes   * Número de odes publicados en un idioma
     */
    public void setNumeroOdes(long numeroOdes) {
        this.numeroOdes = numeroOdes;
    }


    /**
     * Gets the idiomaTraducido value for this OdesPorIdioma.
     * 
     * @return idiomaTraducido   * idioma traducido a traves de soporte del atributo idioma
     */
    public java.lang.String getIdiomaTraducido() {
        return idiomaTraducido;
    }


    /**
     * Sets the idiomaTraducido value for this OdesPorIdioma.
     * 
     * @param idiomaTraducido   * idioma traducido a traves de soporte del atributo idioma
     */
    public void setIdiomaTraducido(java.lang.String idiomaTraducido) {
        this.idiomaTraducido = idiomaTraducido;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OdesPorIdioma)) return false;
        OdesPorIdioma other = (OdesPorIdioma) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            this.numeroOdes == other.getNumeroOdes() &&
            ((this.idiomaTraducido==null && other.getIdiomaTraducido()==null) || 
             (this.idiomaTraducido!=null &&
              this.idiomaTraducido.equals(other.getIdiomaTraducido())));
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
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        _hashCode += new Long(getNumeroOdes()).hashCode();
        if (getIdiomaTraducido() != null) {
            _hashCode += getIdiomaTraducido().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OdesPorIdioma.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "OdesPorIdioma"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "numeroOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaTraducido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idiomaTraducido"));
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
