/**
 * ParametrosOaiPmhVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Value Object de la capa de servicio con los parámetros de la
 *                         petición oai-pmh. Este objeto tiene la mayoría
 * de los atributos
 *                         del VO ParametrosRequestVO. Entre estos dos
 * objetos se hará un
 *                         mapeo de dozer
 */
public class ParametrosOaiPmhVO  implements java.io.Serializable {
    /* Identificador del registro dentro del repositorio */
    private java.lang.String identificador;

    /* Tipo de metadato en el que se quiere que se devuelva la
     *                                 respuesta a la petición. En nuestro
     * caso será dublin core */
    private java.lang.String prefijoMetadato;

    /* Fecha de inicio desde la que se quiere obtener la información
     * de
     *                                 los registros */
    private java.util.Calendar fechaDesde;

    /* Fecha fin hasta la que se quiere obtener la información del
     * repositorio */
    private java.util.Calendar fechaHasta;

    /* Identificador del conjunto sobre el que se quiere obtener la
     * información */
    private java.lang.String identificadorConjunto;

    /* Identificador de la página que se quiere obtener */
    private java.lang.String codigoPaginacion;

    public ParametrosOaiPmhVO() {
    }

    public ParametrosOaiPmhVO(
           java.lang.String identificador,
           java.lang.String prefijoMetadato,
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.String identificadorConjunto,
           java.lang.String codigoPaginacion) {
           this.identificador = identificador;
           this.prefijoMetadato = prefijoMetadato;
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.identificadorConjunto = identificadorConjunto;
           this.codigoPaginacion = codigoPaginacion;
    }


    /**
     * Gets the identificador value for this ParametrosOaiPmhVO.
     * 
     * @return identificador   * Identificador del registro dentro del repositorio
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ParametrosOaiPmhVO.
     * 
     * @param identificador   * Identificador del registro dentro del repositorio
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the prefijoMetadato value for this ParametrosOaiPmhVO.
     * 
     * @return prefijoMetadato   * Tipo de metadato en el que se quiere que se devuelva la
     *                                 respuesta a la petición. En nuestro
     * caso será dublin core
     */
    public java.lang.String getPrefijoMetadato() {
        return prefijoMetadato;
    }


    /**
     * Sets the prefijoMetadato value for this ParametrosOaiPmhVO.
     * 
     * @param prefijoMetadato   * Tipo de metadato en el que se quiere que se devuelva la
     *                                 respuesta a la petición. En nuestro
     * caso será dublin core
     */
    public void setPrefijoMetadato(java.lang.String prefijoMetadato) {
        this.prefijoMetadato = prefijoMetadato;
    }


    /**
     * Gets the fechaDesde value for this ParametrosOaiPmhVO.
     * 
     * @return fechaDesde   * Fecha de inicio desde la que se quiere obtener la información
     * de
     *                                 los registros
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this ParametrosOaiPmhVO.
     * 
     * @param fechaDesde   * Fecha de inicio desde la que se quiere obtener la información
     * de
     *                                 los registros
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this ParametrosOaiPmhVO.
     * 
     * @return fechaHasta   * Fecha fin hasta la que se quiere obtener la información del
     * repositorio
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this ParametrosOaiPmhVO.
     * 
     * @param fechaHasta   * Fecha fin hasta la que se quiere obtener la información del
     * repositorio
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the identificadorConjunto value for this ParametrosOaiPmhVO.
     * 
     * @return identificadorConjunto   * Identificador del conjunto sobre el que se quiere obtener la
     * información
     */
    public java.lang.String getIdentificadorConjunto() {
        return identificadorConjunto;
    }


    /**
     * Sets the identificadorConjunto value for this ParametrosOaiPmhVO.
     * 
     * @param identificadorConjunto   * Identificador del conjunto sobre el que se quiere obtener la
     * información
     */
    public void setIdentificadorConjunto(java.lang.String identificadorConjunto) {
        this.identificadorConjunto = identificadorConjunto;
    }


    /**
     * Gets the codigoPaginacion value for this ParametrosOaiPmhVO.
     * 
     * @return codigoPaginacion   * Identificador de la página que se quiere obtener
     */
    public java.lang.String getCodigoPaginacion() {
        return codigoPaginacion;
    }


    /**
     * Sets the codigoPaginacion value for this ParametrosOaiPmhVO.
     * 
     * @param codigoPaginacion   * Identificador de la página que se quiere obtener
     */
    public void setCodigoPaginacion(java.lang.String codigoPaginacion) {
        this.codigoPaginacion = codigoPaginacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosOaiPmhVO)) return false;
        ParametrosOaiPmhVO other = (ParametrosOaiPmhVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.prefijoMetadato==null && other.getPrefijoMetadato()==null) || 
             (this.prefijoMetadato!=null &&
              this.prefijoMetadato.equals(other.getPrefijoMetadato()))) &&
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.identificadorConjunto==null && other.getIdentificadorConjunto()==null) || 
             (this.identificadorConjunto!=null &&
              this.identificadorConjunto.equals(other.getIdentificadorConjunto()))) &&
            ((this.codigoPaginacion==null && other.getCodigoPaginacion()==null) || 
             (this.codigoPaginacion!=null &&
              this.codigoPaginacion.equals(other.getCodigoPaginacion())));
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
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getPrefijoMetadato() != null) {
            _hashCode += getPrefijoMetadato().hashCode();
        }
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getIdentificadorConjunto() != null) {
            _hashCode += getIdentificadorConjunto().hashCode();
        }
        if (getCodigoPaginacion() != null) {
            _hashCode += getCodigoPaginacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosOaiPmhVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ParametrosOaiPmhVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefijoMetadato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "prefijoMetadato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorConjunto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificadorConjunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPaginacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "codigoPaginacion"));
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
