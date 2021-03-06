/**
 * PeticionFeedVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public class PeticionFeedVO  implements java.io.Serializable {
    /* Identificador del feed que ha recibido la peticion. */
    private java.lang.String idFeed;

    /* Formato del Feed al que se ha realizado la peticion. Si se
     *                                 recuperan estadisticas independientes
     * de formato, el valor de
     *                                 este atributo es null. */
    private java.lang.String formatoFeed;

    /* Periodo del Feed (solo se aplica a algunos canales). Si se
     *                                 recuperan estadisticas independientes
     * de este paremetro, el
     *                                 valor es null. */
    private java.lang.String periodo;

    /* Fecha en que se intercepta la peticion. */
    private java.util.Calendar fecha;

    /* Idioma en que se realiza la peticion (si aplica) */
    private java.lang.String idioma;

    public PeticionFeedVO() {
    }

    public PeticionFeedVO(
           java.lang.String idFeed,
           java.lang.String formatoFeed,
           java.lang.String periodo,
           java.util.Calendar fecha,
           java.lang.String idioma) {
           this.idFeed = idFeed;
           this.formatoFeed = formatoFeed;
           this.periodo = periodo;
           this.fecha = fecha;
           this.idioma = idioma;
    }


    /**
     * Gets the idFeed value for this PeticionFeedVO.
     * 
     * @return idFeed   * Identificador del feed que ha recibido la peticion.
     */
    public java.lang.String getIdFeed() {
        return idFeed;
    }


    /**
     * Sets the idFeed value for this PeticionFeedVO.
     * 
     * @param idFeed   * Identificador del feed que ha recibido la peticion.
     */
    public void setIdFeed(java.lang.String idFeed) {
        this.idFeed = idFeed;
    }


    /**
     * Gets the formatoFeed value for this PeticionFeedVO.
     * 
     * @return formatoFeed   * Formato del Feed al que se ha realizado la peticion. Si se
     *                                 recuperan estadisticas independientes
     * de formato, el valor de
     *                                 este atributo es null.
     */
    public java.lang.String getFormatoFeed() {
        return formatoFeed;
    }


    /**
     * Sets the formatoFeed value for this PeticionFeedVO.
     * 
     * @param formatoFeed   * Formato del Feed al que se ha realizado la peticion. Si se
     *                                 recuperan estadisticas independientes
     * de formato, el valor de
     *                                 este atributo es null.
     */
    public void setFormatoFeed(java.lang.String formatoFeed) {
        this.formatoFeed = formatoFeed;
    }


    /**
     * Gets the periodo value for this PeticionFeedVO.
     * 
     * @return periodo   * Periodo del Feed (solo se aplica a algunos canales). Si se
     *                                 recuperan estadisticas independientes
     * de este paremetro, el
     *                                 valor es null.
     */
    public java.lang.String getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this PeticionFeedVO.
     * 
     * @param periodo   * Periodo del Feed (solo se aplica a algunos canales). Si se
     *                                 recuperan estadisticas independientes
     * de este paremetro, el
     *                                 valor es null.
     */
    public void setPeriodo(java.lang.String periodo) {
        this.periodo = periodo;
    }


    /**
     * Gets the fecha value for this PeticionFeedVO.
     * 
     * @return fecha   * Fecha en que se intercepta la peticion.
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this PeticionFeedVO.
     * 
     * @param fecha   * Fecha en que se intercepta la peticion.
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the idioma value for this PeticionFeedVO.
     * 
     * @return idioma   * Idioma en que se realiza la peticion (si aplica)
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this PeticionFeedVO.
     * 
     * @param idioma   * Idioma en que se realiza la peticion (si aplica)
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PeticionFeedVO)) return false;
        PeticionFeedVO other = (PeticionFeedVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idFeed==null && other.getIdFeed()==null) || 
             (this.idFeed!=null &&
              this.idFeed.equals(other.getIdFeed()))) &&
            ((this.formatoFeed==null && other.getFormatoFeed()==null) || 
             (this.formatoFeed!=null &&
              this.formatoFeed.equals(other.getFormatoFeed()))) &&
            ((this.periodo==null && other.getPeriodo()==null) || 
             (this.periodo!=null &&
              this.periodo.equals(other.getPeriodo()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
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
        if (getIdFeed() != null) {
            _hashCode += getIdFeed().hashCode();
        }
        if (getFormatoFeed() != null) {
            _hashCode += getFormatoFeed().hashCode();
        }
        if (getPeriodo() != null) {
            _hashCode += getPeriodo().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PeticionFeedVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "PeticionFeedVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idFeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoFeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "formatoFeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "periodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
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
