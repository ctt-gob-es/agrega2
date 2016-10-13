/**
 * ListMetadataFormatVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Value Object con todos los posibles valores de metadatos que
 *                         devuelve el repositorio
 */
public class ListMetadataFormatVO  implements java.io.Serializable {
    /* Nombre del metadato */
    private java.lang.String nombreMetadato;

    /* Url del esquema (xsd) del metadato de la respuesta. En nuestro
     * caso se devolverá en formato dublin core */
    private java.lang.String esquema;

    /* Url con el espacio de nombres de los metadatos en los que se
     * devolverá el contenido del repositorio */
    private java.lang.String espacioNombres;

    public ListMetadataFormatVO() {
    }

    public ListMetadataFormatVO(
           java.lang.String nombreMetadato,
           java.lang.String esquema,
           java.lang.String espacioNombres) {
           this.nombreMetadato = nombreMetadato;
           this.esquema = esquema;
           this.espacioNombres = espacioNombres;
    }


    /**
     * Gets the nombreMetadato value for this ListMetadataFormatVO.
     * 
     * @return nombreMetadato   * Nombre del metadato
     */
    public java.lang.String getNombreMetadato() {
        return nombreMetadato;
    }


    /**
     * Sets the nombreMetadato value for this ListMetadataFormatVO.
     * 
     * @param nombreMetadato   * Nombre del metadato
     */
    public void setNombreMetadato(java.lang.String nombreMetadato) {
        this.nombreMetadato = nombreMetadato;
    }


    /**
     * Gets the esquema value for this ListMetadataFormatVO.
     * 
     * @return esquema   * Url del esquema (xsd) del metadato de la respuesta. En nuestro
     * caso se devolverá en formato dublin core
     */
    public java.lang.String getEsquema() {
        return esquema;
    }


    /**
     * Sets the esquema value for this ListMetadataFormatVO.
     * 
     * @param esquema   * Url del esquema (xsd) del metadato de la respuesta. En nuestro
     * caso se devolverá en formato dublin core
     */
    public void setEsquema(java.lang.String esquema) {
        this.esquema = esquema;
    }


    /**
     * Gets the espacioNombres value for this ListMetadataFormatVO.
     * 
     * @return espacioNombres   * Url con el espacio de nombres de los metadatos en los que se
     * devolverá el contenido del repositorio
     */
    public java.lang.String getEspacioNombres() {
        return espacioNombres;
    }


    /**
     * Sets the espacioNombres value for this ListMetadataFormatVO.
     * 
     * @param espacioNombres   * Url con el espacio de nombres de los metadatos en los que se
     * devolverá el contenido del repositorio
     */
    public void setEspacioNombres(java.lang.String espacioNombres) {
        this.espacioNombres = espacioNombres;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListMetadataFormatVO)) return false;
        ListMetadataFormatVO other = (ListMetadataFormatVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreMetadato==null && other.getNombreMetadato()==null) || 
             (this.nombreMetadato!=null &&
              this.nombreMetadato.equals(other.getNombreMetadato()))) &&
            ((this.esquema==null && other.getEsquema()==null) || 
             (this.esquema!=null &&
              this.esquema.equals(other.getEsquema()))) &&
            ((this.espacioNombres==null && other.getEspacioNombres()==null) || 
             (this.espacioNombres!=null &&
              this.espacioNombres.equals(other.getEspacioNombres())));
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
        if (getNombreMetadato() != null) {
            _hashCode += getNombreMetadato().hashCode();
        }
        if (getEsquema() != null) {
            _hashCode += getEsquema().hashCode();
        }
        if (getEspacioNombres() != null) {
            _hashCode += getEspacioNombres().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListMetadataFormatVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ListMetadataFormatVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreMetadato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "nombreMetadato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esquema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "esquema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("espacioNombres");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "espacioNombres"));
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
