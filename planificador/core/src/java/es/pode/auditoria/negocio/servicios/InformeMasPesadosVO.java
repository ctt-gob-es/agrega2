/**
 * InformeMasPesadosVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public class InformeMasPesadosVO  implements java.io.Serializable {
    private java.lang.String tituloOde;

    private long tamanio;

    private java.lang.String idioma;

    private java.lang.String idODE;

    public InformeMasPesadosVO() {
    }

    public InformeMasPesadosVO(
           java.lang.String tituloOde,
           long tamanio,
           java.lang.String idioma,
           java.lang.String idODE) {
           this.tituloOde = tituloOde;
           this.tamanio = tamanio;
           this.idioma = idioma;
           this.idODE = idODE;
    }


    /**
     * Gets the tituloOde value for this InformeMasPesadosVO.
     * 
     * @return tituloOde
     */
    public java.lang.String getTituloOde() {
        return tituloOde;
    }


    /**
     * Sets the tituloOde value for this InformeMasPesadosVO.
     * 
     * @param tituloOde
     */
    public void setTituloOde(java.lang.String tituloOde) {
        this.tituloOde = tituloOde;
    }


    /**
     * Gets the tamanio value for this InformeMasPesadosVO.
     * 
     * @return tamanio
     */
    public long getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this InformeMasPesadosVO.
     * 
     * @param tamanio
     */
    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }


    /**
     * Gets the idioma value for this InformeMasPesadosVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this InformeMasPesadosVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the idODE value for this InformeMasPesadosVO.
     * 
     * @return idODE
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this InformeMasPesadosVO.
     * 
     * @param idODE
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeMasPesadosVO)) return false;
        InformeMasPesadosVO other = (InformeMasPesadosVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tituloOde==null && other.getTituloOde()==null) || 
             (this.tituloOde!=null &&
              this.tituloOde.equals(other.getTituloOde()))) &&
            this.tamanio == other.getTamanio() &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE())));
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
        if (getTituloOde() != null) {
            _hashCode += getTituloOde().hashCode();
        }
        _hashCode += new Long(getTamanio()).hashCode();
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeMasPesadosVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeMasPesadosVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "tituloOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "tamanio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idODE"));
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
