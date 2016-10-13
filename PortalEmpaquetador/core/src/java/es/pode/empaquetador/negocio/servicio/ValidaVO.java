/**
 * ValidaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Objeto que contiene los resultados de una llamada al servicio de
 * validación. Es una copia del ValidaVO devuelto por dicho
 *                         servicio.
 */
public class ValidaVO  implements java.io.Serializable {
    /* Indica si la validacion ha sido superada. */
    private java.lang.Boolean esValidoManifest;

    /* Contiene los códigos de los mensajes devueltos por Validacion
     * separados por ';'. */
    private java.lang.String resultadoValidacion;

    /* Ruta en la que se encuentra almacenado el imsmanifest.xml */
    private java.lang.String rutaManifest;

    public ValidaVO() {
    }

    public ValidaVO(
           java.lang.Boolean esValidoManifest,
           java.lang.String resultadoValidacion,
           java.lang.String rutaManifest) {
           this.esValidoManifest = esValidoManifest;
           this.resultadoValidacion = resultadoValidacion;
           this.rutaManifest = rutaManifest;
    }


    /**
     * Gets the esValidoManifest value for this ValidaVO.
     * 
     * @return esValidoManifest   * Indica si la validacion ha sido superada.
     */
    public java.lang.Boolean getEsValidoManifest() {
        return esValidoManifest;
    }


    /**
     * Sets the esValidoManifest value for this ValidaVO.
     * 
     * @param esValidoManifest   * Indica si la validacion ha sido superada.
     */
    public void setEsValidoManifest(java.lang.Boolean esValidoManifest) {
        this.esValidoManifest = esValidoManifest;
    }


    /**
     * Gets the resultadoValidacion value for this ValidaVO.
     * 
     * @return resultadoValidacion   * Contiene los códigos de los mensajes devueltos por Validacion
     * separados por ';'.
     */
    public java.lang.String getResultadoValidacion() {
        return resultadoValidacion;
    }


    /**
     * Sets the resultadoValidacion value for this ValidaVO.
     * 
     * @param resultadoValidacion   * Contiene los códigos de los mensajes devueltos por Validacion
     * separados por ';'.
     */
    public void setResultadoValidacion(java.lang.String resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }


    /**
     * Gets the rutaManifest value for this ValidaVO.
     * 
     * @return rutaManifest   * Ruta en la que se encuentra almacenado el imsmanifest.xml
     */
    public java.lang.String getRutaManifest() {
        return rutaManifest;
    }


    /**
     * Sets the rutaManifest value for this ValidaVO.
     * 
     * @param rutaManifest   * Ruta en la que se encuentra almacenado el imsmanifest.xml
     */
    public void setRutaManifest(java.lang.String rutaManifest) {
        this.rutaManifest = rutaManifest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaVO)) return false;
        ValidaVO other = (ValidaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.esValidoManifest==null && other.getEsValidoManifest()==null) || 
             (this.esValidoManifest!=null &&
              this.esValidoManifest.equals(other.getEsValidoManifest()))) &&
            ((this.resultadoValidacion==null && other.getResultadoValidacion()==null) || 
             (this.resultadoValidacion!=null &&
              this.resultadoValidacion.equals(other.getResultadoValidacion()))) &&
            ((this.rutaManifest==null && other.getRutaManifest()==null) || 
             (this.rutaManifest!=null &&
              this.rutaManifest.equals(other.getRutaManifest())));
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
        if (getEsValidoManifest() != null) {
            _hashCode += getEsValidoManifest().hashCode();
        }
        if (getResultadoValidacion() != null) {
            _hashCode += getResultadoValidacion().hashCode();
        }
        if (getRutaManifest() != null) {
            _hashCode += getRutaManifest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ValidaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esValidoManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esValidoManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "resultadoValidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "rutaManifest"));
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
