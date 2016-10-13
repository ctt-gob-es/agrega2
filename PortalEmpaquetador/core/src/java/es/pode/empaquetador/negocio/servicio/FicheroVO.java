/**
 * FicheroVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Clase que encapsula un fichero subido desde la capa de
 *                         presentacion.
 */
public class FicheroVO  implements java.io.Serializable {
    /* Nombre de archivo (sin ruta). */
    private java.lang.String nombre;

    /* Tipo MIME del archivo. */
    private java.lang.String tipoMime;

    /* Datahandler que contiene los datos del fichero. */
    private javax.activation.DataHandler datos;

    /* Ruta relativa en la que se ha de almacenar el fichero. */
    private java.lang.String ruta;

    public FicheroVO() {
    }

    public FicheroVO(
           java.lang.String nombre,
           java.lang.String tipoMime,
           javax.activation.DataHandler datos,
           java.lang.String ruta) {
           this.nombre = nombre;
           this.tipoMime = tipoMime;
           this.datos = datos;
           this.ruta = ruta;
    }


    /**
     * Gets the nombre value for this FicheroVO.
     * 
     * @return nombre   * Nombre de archivo (sin ruta).
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this FicheroVO.
     * 
     * @param nombre   * Nombre de archivo (sin ruta).
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tipoMime value for this FicheroVO.
     * 
     * @return tipoMime   * Tipo MIME del archivo.
     */
    public java.lang.String getTipoMime() {
        return tipoMime;
    }


    /**
     * Sets the tipoMime value for this FicheroVO.
     * 
     * @param tipoMime   * Tipo MIME del archivo.
     */
    public void setTipoMime(java.lang.String tipoMime) {
        this.tipoMime = tipoMime;
    }


    /**
     * Gets the datos value for this FicheroVO.
     * 
     * @return datos   * Datahandler que contiene los datos del fichero.
     */
    public javax.activation.DataHandler getDatos() {
        return datos;
    }


    /**
     * Sets the datos value for this FicheroVO.
     * 
     * @param datos   * Datahandler que contiene los datos del fichero.
     */
    public void setDatos(javax.activation.DataHandler datos) {
        this.datos = datos;
    }


    /**
     * Gets the ruta value for this FicheroVO.
     * 
     * @return ruta   * Ruta relativa en la que se ha de almacenar el fichero.
     */
    public java.lang.String getRuta() {
        return ruta;
    }


    /**
     * Sets the ruta value for this FicheroVO.
     * 
     * @param ruta   * Ruta relativa en la que se ha de almacenar el fichero.
     */
    public void setRuta(java.lang.String ruta) {
        this.ruta = ruta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FicheroVO)) return false;
        FicheroVO other = (FicheroVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tipoMime==null && other.getTipoMime()==null) || 
             (this.tipoMime!=null &&
              this.tipoMime.equals(other.getTipoMime()))) &&
            ((this.datos==null && other.getDatos()==null) || 
             (this.datos!=null &&
              this.datos.equals(other.getDatos()))) &&
            ((this.ruta==null && other.getRuta()==null) || 
             (this.ruta!=null &&
              this.ruta.equals(other.getRuta())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTipoMime() != null) {
            _hashCode += getTipoMime().hashCode();
        }
        if (getDatos() != null) {
            _hashCode += getDatos().hashCode();
        }
        if (getRuta() != null) {
            _hashCode += getRuta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FicheroVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "FicheroVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoMime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "tipoMime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "datos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ruta"));
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
