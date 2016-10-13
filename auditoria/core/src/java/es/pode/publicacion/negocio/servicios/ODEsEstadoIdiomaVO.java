/**
 * ODEsEstadoIdiomaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;


/**
 * Este objeto de valor contienen el numero de ODEs que estan en un
 * idioma por estado.
 */
public class ODEsEstadoIdiomaVO  implements java.io.Serializable {
    /* Numero de ODEs que estan en el estado e idioma del VO. */
    private java.lang.Long numODES;

    /* Codigo iso del idioma en el que estan los ODEs en el estado
     * dado. */
    private java.lang.String idioma;

    public ODEsEstadoIdiomaVO() {
    }

    public ODEsEstadoIdiomaVO(
           java.lang.Long numODES,
           java.lang.String idioma) {
           this.numODES = numODES;
           this.idioma = idioma;
    }


    /**
     * Gets the numODES value for this ODEsEstadoIdiomaVO.
     * 
     * @return numODES   * Numero de ODEs que estan en el estado e idioma del VO.
     */
    public java.lang.Long getNumODES() {
        return numODES;
    }


    /**
     * Sets the numODES value for this ODEsEstadoIdiomaVO.
     * 
     * @param numODES   * Numero de ODEs que estan en el estado e idioma del VO.
     */
    public void setNumODES(java.lang.Long numODES) {
        this.numODES = numODES;
    }


    /**
     * Gets the idioma value for this ODEsEstadoIdiomaVO.
     * 
     * @return idioma   * Codigo iso del idioma en el que estan los ODEs en el estado
     * dado.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ODEsEstadoIdiomaVO.
     * 
     * @param idioma   * Codigo iso del idioma en el que estan los ODEs en el estado
     * dado.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ODEsEstadoIdiomaVO)) return false;
        ODEsEstadoIdiomaVO other = (ODEsEstadoIdiomaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numODES==null && other.getNumODES()==null) || 
             (this.numODES!=null &&
              this.numODES.equals(other.getNumODES()))) &&
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
        if (getNumODES() != null) {
            _hashCode += getNumODES().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ODEsEstadoIdiomaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoIdiomaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numODES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "numODES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "idioma"));
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
