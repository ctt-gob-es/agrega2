/**
 * InformeMasVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * VO genérico que almacena información de los odes que han sido
 *                         más veces: mostrados, previsualizados, descargados
 * ....
 */
public class InformeMasVO  implements java.io.Serializable {
    /* Título del ode. Normalmente este campo no se podrá almacenar
     * en
     *                                 la BD porque no se recoge en los parámetros
     * que se interceptan */
    private java.lang.String tituloOde;

    /* Número de veces que ha sido el ode: mostrado, previsualizado
     * o
     *                                 descargado */
    private int numVeces;

    /* Identificador del ode */
    private java.lang.String idOde;

    /* Idioma de búsqueda. Se utilizará para obtener la ficha del
     * ode */
    private java.lang.String idioma;

    /* Es la url de donde se encuentra la imagen del ODE */
    private java.lang.String urlImagen;

    public InformeMasVO() {
    }

    public InformeMasVO(
           java.lang.String tituloOde,
           int numVeces,
           java.lang.String idOde,
           java.lang.String idioma,
           java.lang.String urlImagen) {
           this.tituloOde = tituloOde;
           this.numVeces = numVeces;
           this.idOde = idOde;
           this.idioma = idioma;
           this.urlImagen = urlImagen;
    }


    /**
     * Gets the tituloOde value for this InformeMasVO.
     * 
     * @return tituloOde   * Título del ode. Normalmente este campo no se podrá almacenar
     * en
     *                                 la BD porque no se recoge en los parámetros
     * que se interceptan
     */
    public java.lang.String getTituloOde() {
        return tituloOde;
    }


    /**
     * Sets the tituloOde value for this InformeMasVO.
     * 
     * @param tituloOde   * Título del ode. Normalmente este campo no se podrá almacenar
     * en
     *                                 la BD porque no se recoge en los parámetros
     * que se interceptan
     */
    public void setTituloOde(java.lang.String tituloOde) {
        this.tituloOde = tituloOde;
    }


    /**
     * Gets the numVeces value for this InformeMasVO.
     * 
     * @return numVeces   * Número de veces que ha sido el ode: mostrado, previsualizado
     * o
     *                                 descargado
     */
    public int getNumVeces() {
        return numVeces;
    }


    /**
     * Sets the numVeces value for this InformeMasVO.
     * 
     * @param numVeces   * Número de veces que ha sido el ode: mostrado, previsualizado
     * o
     *                                 descargado
     */
    public void setNumVeces(int numVeces) {
        this.numVeces = numVeces;
    }


    /**
     * Gets the idOde value for this InformeMasVO.
     * 
     * @return idOde   * Identificador del ode
     */
    public java.lang.String getIdOde() {
        return idOde;
    }


    /**
     * Sets the idOde value for this InformeMasVO.
     * 
     * @param idOde   * Identificador del ode
     */
    public void setIdOde(java.lang.String idOde) {
        this.idOde = idOde;
    }


    /**
     * Gets the idioma value for this InformeMasVO.
     * 
     * @return idioma   * Idioma de búsqueda. Se utilizará para obtener la ficha del
     * ode
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this InformeMasVO.
     * 
     * @param idioma   * Idioma de búsqueda. Se utilizará para obtener la ficha del
     * ode
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the urlImagen value for this InformeMasVO.
     * 
     * @return urlImagen   * Es la url de donde se encuentra la imagen del ODE
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this InformeMasVO.
     * 
     * @param urlImagen   * Es la url de donde se encuentra la imagen del ODE
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeMasVO)) return false;
        InformeMasVO other = (InformeMasVO) obj;
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
            this.numVeces == other.getNumVeces() &&
            ((this.idOde==null && other.getIdOde()==null) || 
             (this.idOde!=null &&
              this.idOde.equals(other.getIdOde()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen())));
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
        _hashCode += getNumVeces();
        if (getIdOde() != null) {
            _hashCode += getIdOde().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeMasVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "InformeMasVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "tituloOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numVeces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "numVeces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "idOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "urlImagen"));
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
