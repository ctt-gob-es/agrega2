/**
 * ParametrosInformeFederadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con la información necesaria para la ejecución de los
 *                         informes federados
 */
public class ParametrosInformeFederadoVO  implements java.io.Serializable {
    /* Fecha de inicio a partir de la cual se generará el informe */
    private java.util.Calendar fechaDesde;

    /* Fecha hasta la cual se calculará el informe */
    private java.util.Calendar fechaHasta;

    /* Tipo de informe federado que se ejecutará */
    private java.lang.String tipoInforme;

    /* Valor del rango de elementos que se van a generar */
    private java.lang.String rango;

    /* Idioma en el que se mostrará el contenido del informe */
    private java.lang.String idioma;

    /* Identificador del usuario del que se quiere obtener la
     *                                 información del informe */
    private java.lang.String idUsuario;

    private java.util.Calendar fechaNivelAgregacion;

    public ParametrosInformeFederadoVO() {
    }

    public ParametrosInformeFederadoVO(
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.String tipoInforme,
           java.lang.String rango,
           java.lang.String idioma,
           java.lang.String idUsuario,
           java.util.Calendar fechaNivelAgregacion) {
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.tipoInforme = tipoInforme;
           this.rango = rango;
           this.idioma = idioma;
           this.idUsuario = idUsuario;
           this.fechaNivelAgregacion = fechaNivelAgregacion;
    }


    /**
     * Gets the fechaDesde value for this ParametrosInformeFederadoVO.
     * 
     * @return fechaDesde   * Fecha de inicio a partir de la cual se generará el informe
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this ParametrosInformeFederadoVO.
     * 
     * @param fechaDesde   * Fecha de inicio a partir de la cual se generará el informe
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this ParametrosInformeFederadoVO.
     * 
     * @return fechaHasta   * Fecha hasta la cual se calculará el informe
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this ParametrosInformeFederadoVO.
     * 
     * @param fechaHasta   * Fecha hasta la cual se calculará el informe
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the tipoInforme value for this ParametrosInformeFederadoVO.
     * 
     * @return tipoInforme   * Tipo de informe federado que se ejecutará
     */
    public java.lang.String getTipoInforme() {
        return tipoInforme;
    }


    /**
     * Sets the tipoInforme value for this ParametrosInformeFederadoVO.
     * 
     * @param tipoInforme   * Tipo de informe federado que se ejecutará
     */
    public void setTipoInforme(java.lang.String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }


    /**
     * Gets the rango value for this ParametrosInformeFederadoVO.
     * 
     * @return rango   * Valor del rango de elementos que se van a generar
     */
    public java.lang.String getRango() {
        return rango;
    }


    /**
     * Sets the rango value for this ParametrosInformeFederadoVO.
     * 
     * @param rango   * Valor del rango de elementos que se van a generar
     */
    public void setRango(java.lang.String rango) {
        this.rango = rango;
    }


    /**
     * Gets the idioma value for this ParametrosInformeFederadoVO.
     * 
     * @return idioma   * Idioma en el que se mostrará el contenido del informe
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ParametrosInformeFederadoVO.
     * 
     * @param idioma   * Idioma en el que se mostrará el contenido del informe
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the idUsuario value for this ParametrosInformeFederadoVO.
     * 
     * @return idUsuario   * Identificador del usuario del que se quiere obtener la
     *                                 información del informe
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this ParametrosInformeFederadoVO.
     * 
     * @param idUsuario   * Identificador del usuario del que se quiere obtener la
     *                                 información del informe
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the fechaNivelAgregacion value for this ParametrosInformeFederadoVO.
     * 
     * @return fechaNivelAgregacion
     */
    public java.util.Calendar getFechaNivelAgregacion() {
        return fechaNivelAgregacion;
    }


    /**
     * Sets the fechaNivelAgregacion value for this ParametrosInformeFederadoVO.
     * 
     * @param fechaNivelAgregacion
     */
    public void setFechaNivelAgregacion(java.util.Calendar fechaNivelAgregacion) {
        this.fechaNivelAgregacion = fechaNivelAgregacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosInformeFederadoVO)) return false;
        ParametrosInformeFederadoVO other = (ParametrosInformeFederadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.tipoInforme==null && other.getTipoInforme()==null) || 
             (this.tipoInforme!=null &&
              this.tipoInforme.equals(other.getTipoInforme()))) &&
            ((this.rango==null && other.getRango()==null) || 
             (this.rango!=null &&
              this.rango.equals(other.getRango()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.fechaNivelAgregacion==null && other.getFechaNivelAgregacion()==null) || 
             (this.fechaNivelAgregacion!=null &&
              this.fechaNivelAgregacion.equals(other.getFechaNivelAgregacion())));
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
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getTipoInforme() != null) {
            _hashCode += getTipoInforme().hashCode();
        }
        if (getRango() != null) {
            _hashCode += getRango().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getFechaNivelAgregacion() != null) {
            _hashCode += getFechaNivelAgregacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosInformeFederadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "ParametrosInformeFederadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "tipoInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rango");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "rango"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaNivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
