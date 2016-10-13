/**
 * ArchivoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Value Object representado un fichero o carpeta en la
 *                         localización del ODE.
 */
public class ArchivoVO  implements java.io.Serializable {
    /* Nombre del fichero o carpeta. */
    private java.lang.String nombre;

    /* Distingue entre ficheros y carpetas: true = fichero, false
     * =
     *                                 carpeta. */
    private java.lang.Boolean esFichero;

    /* URL que da acceso al fichero. */
    private java.lang.String href;

    /* Especifica la ruta relativa a la localizacion del ODE donde
     * se
     *                                 encuentra el fichero / carpeta. */
    private java.lang.String carpetaPadre;

    /* Tipo MIME del fichero, en caso de que este ArchivoVO se
     *                                 corresponda con un fichero. */
    private java.lang.String mime;

    /* True si fichero está en lista de protegidos */
    private java.lang.Boolean esProtegido;

    private es.pode.empaquetador.negocio.servicio.ArchivoVO[] contenidos;

    public ArchivoVO() {
    }

    public ArchivoVO(
           java.lang.String nombre,
           java.lang.Boolean esFichero,
           java.lang.String href,
           java.lang.String carpetaPadre,
           java.lang.String mime,
           java.lang.Boolean esProtegido,
           es.pode.empaquetador.negocio.servicio.ArchivoVO[] contenidos) {
           this.nombre = nombre;
           this.esFichero = esFichero;
           this.href = href;
           this.carpetaPadre = carpetaPadre;
           this.mime = mime;
           this.esProtegido = esProtegido;
           this.contenidos = contenidos;
    }


    /**
     * Gets the nombre value for this ArchivoVO.
     * 
     * @return nombre   * Nombre del fichero o carpeta.
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ArchivoVO.
     * 
     * @param nombre   * Nombre del fichero o carpeta.
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the esFichero value for this ArchivoVO.
     * 
     * @return esFichero   * Distingue entre ficheros y carpetas: true = fichero, false
     * =
     *                                 carpeta.
     */
    public java.lang.Boolean getEsFichero() {
        return esFichero;
    }


    /**
     * Sets the esFichero value for this ArchivoVO.
     * 
     * @param esFichero   * Distingue entre ficheros y carpetas: true = fichero, false
     * =
     *                                 carpeta.
     */
    public void setEsFichero(java.lang.Boolean esFichero) {
        this.esFichero = esFichero;
    }


    /**
     * Gets the href value for this ArchivoVO.
     * 
     * @return href   * URL que da acceso al fichero.
     */
    public java.lang.String getHref() {
        return href;
    }


    /**
     * Sets the href value for this ArchivoVO.
     * 
     * @param href   * URL que da acceso al fichero.
     */
    public void setHref(java.lang.String href) {
        this.href = href;
    }


    /**
     * Gets the carpetaPadre value for this ArchivoVO.
     * 
     * @return carpetaPadre   * Especifica la ruta relativa a la localizacion del ODE donde
     * se
     *                                 encuentra el fichero / carpeta.
     */
    public java.lang.String getCarpetaPadre() {
        return carpetaPadre;
    }


    /**
     * Sets the carpetaPadre value for this ArchivoVO.
     * 
     * @param carpetaPadre   * Especifica la ruta relativa a la localizacion del ODE donde
     * se
     *                                 encuentra el fichero / carpeta.
     */
    public void setCarpetaPadre(java.lang.String carpetaPadre) {
        this.carpetaPadre = carpetaPadre;
    }


    /**
     * Gets the mime value for this ArchivoVO.
     * 
     * @return mime   * Tipo MIME del fichero, en caso de que este ArchivoVO se
     *                                 corresponda con un fichero.
     */
    public java.lang.String getMime() {
        return mime;
    }


    /**
     * Sets the mime value for this ArchivoVO.
     * 
     * @param mime   * Tipo MIME del fichero, en caso de que este ArchivoVO se
     *                                 corresponda con un fichero.
     */
    public void setMime(java.lang.String mime) {
        this.mime = mime;
    }


    /**
     * Gets the esProtegido value for this ArchivoVO.
     * 
     * @return esProtegido   * True si fichero está en lista de protegidos
     */
    public java.lang.Boolean getEsProtegido() {
        return esProtegido;
    }


    /**
     * Sets the esProtegido value for this ArchivoVO.
     * 
     * @param esProtegido   * True si fichero está en lista de protegidos
     */
    public void setEsProtegido(java.lang.Boolean esProtegido) {
        this.esProtegido = esProtegido;
    }


    /**
     * Gets the contenidos value for this ArchivoVO.
     * 
     * @return contenidos
     */
    public es.pode.empaquetador.negocio.servicio.ArchivoVO[] getContenidos() {
        return contenidos;
    }


    /**
     * Sets the contenidos value for this ArchivoVO.
     * 
     * @param contenidos
     */
    public void setContenidos(es.pode.empaquetador.negocio.servicio.ArchivoVO[] contenidos) {
        this.contenidos = contenidos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArchivoVO)) return false;
        ArchivoVO other = (ArchivoVO) obj;
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
            ((this.esFichero==null && other.getEsFichero()==null) || 
             (this.esFichero!=null &&
              this.esFichero.equals(other.getEsFichero()))) &&
            ((this.href==null && other.getHref()==null) || 
             (this.href!=null &&
              this.href.equals(other.getHref()))) &&
            ((this.carpetaPadre==null && other.getCarpetaPadre()==null) || 
             (this.carpetaPadre!=null &&
              this.carpetaPadre.equals(other.getCarpetaPadre()))) &&
            ((this.mime==null && other.getMime()==null) || 
             (this.mime!=null &&
              this.mime.equals(other.getMime()))) &&
            ((this.esProtegido==null && other.getEsProtegido()==null) || 
             (this.esProtegido!=null &&
              this.esProtegido.equals(other.getEsProtegido()))) &&
            ((this.contenidos==null && other.getContenidos()==null) || 
             (this.contenidos!=null &&
              java.util.Arrays.equals(this.contenidos, other.getContenidos())));
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
        if (getEsFichero() != null) {
            _hashCode += getEsFichero().hashCode();
        }
        if (getHref() != null) {
            _hashCode += getHref().hashCode();
        }
        if (getCarpetaPadre() != null) {
            _hashCode += getCarpetaPadre().hashCode();
        }
        if (getMime() != null) {
            _hashCode += getMime().hashCode();
        }
        if (getEsProtegido() != null) {
            _hashCode += getEsProtegido().hashCode();
        }
        if (getContenidos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContenidos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContenidos(), i);
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
        new org.apache.axis.description.TypeDesc(ArchivoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ArchivoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esFichero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esFichero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("href");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "href"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carpetaPadre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "carpetaPadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "mime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esProtegido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esProtegido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contenidos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "contenidos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ArchivoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
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
