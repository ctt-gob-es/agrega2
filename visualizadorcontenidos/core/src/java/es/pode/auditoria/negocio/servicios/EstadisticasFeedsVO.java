/**
 * EstadisticasFeedsVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public class EstadisticasFeedsVO  implements java.io.Serializable {
    /* Identificador del feed. */
    private java.lang.String idFeed;

    /* Recuento de las peticiones obtenidas para el feed. */
    private java.lang.Long count;

    /* Formato del Feed (1-RSS, 2-Atom) */
    private java.lang.String formatoFeed;

    /* Periodo del feed (1-semanal, 2-mensual, 3-anual). Solo se aplica
     * a algunos canales. */
    private java.lang.String periodo;

    public EstadisticasFeedsVO() {
    }

    public EstadisticasFeedsVO(
           java.lang.String idFeed,
           java.lang.Long count,
           java.lang.String formatoFeed,
           java.lang.String periodo) {
           this.idFeed = idFeed;
           this.count = count;
           this.formatoFeed = formatoFeed;
           this.periodo = periodo;
    }


    /**
     * Gets the idFeed value for this EstadisticasFeedsVO.
     * 
     * @return idFeed   * Identificador del feed.
     */
    public java.lang.String getIdFeed() {
        return idFeed;
    }


    /**
     * Sets the idFeed value for this EstadisticasFeedsVO.
     * 
     * @param idFeed   * Identificador del feed.
     */
    public void setIdFeed(java.lang.String idFeed) {
        this.idFeed = idFeed;
    }


    /**
     * Gets the count value for this EstadisticasFeedsVO.
     * 
     * @return count   * Recuento de las peticiones obtenidas para el feed.
     */
    public java.lang.Long getCount() {
        return count;
    }


    /**
     * Sets the count value for this EstadisticasFeedsVO.
     * 
     * @param count   * Recuento de las peticiones obtenidas para el feed.
     */
    public void setCount(java.lang.Long count) {
        this.count = count;
    }


    /**
     * Gets the formatoFeed value for this EstadisticasFeedsVO.
     * 
     * @return formatoFeed   * Formato del Feed (1-RSS, 2-Atom)
     */
    public java.lang.String getFormatoFeed() {
        return formatoFeed;
    }


    /**
     * Sets the formatoFeed value for this EstadisticasFeedsVO.
     * 
     * @param formatoFeed   * Formato del Feed (1-RSS, 2-Atom)
     */
    public void setFormatoFeed(java.lang.String formatoFeed) {
        this.formatoFeed = formatoFeed;
    }


    /**
     * Gets the periodo value for this EstadisticasFeedsVO.
     * 
     * @return periodo   * Periodo del feed (1-semanal, 2-mensual, 3-anual). Solo se aplica
     * a algunos canales.
     */
    public java.lang.String getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this EstadisticasFeedsVO.
     * 
     * @param periodo   * Periodo del feed (1-semanal, 2-mensual, 3-anual). Solo se aplica
     * a algunos canales.
     */
    public void setPeriodo(java.lang.String periodo) {
        this.periodo = periodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstadisticasFeedsVO)) return false;
        EstadisticasFeedsVO other = (EstadisticasFeedsVO) obj;
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
            ((this.count==null && other.getCount()==null) || 
             (this.count!=null &&
              this.count.equals(other.getCount()))) &&
            ((this.formatoFeed==null && other.getFormatoFeed()==null) || 
             (this.formatoFeed!=null &&
              this.formatoFeed.equals(other.getFormatoFeed()))) &&
            ((this.periodo==null && other.getPeriodo()==null) || 
             (this.periodo!=null &&
              this.periodo.equals(other.getPeriodo())));
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
        if (getCount() != null) {
            _hashCode += getCount().hashCode();
        }
        if (getFormatoFeed() != null) {
            _hashCode += getFormatoFeed().hashCode();
        }
        if (getPeriodo() != null) {
            _hashCode += getPeriodo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstadisticasFeedsVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "EstadisticasFeedsVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idFeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoFeed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "formatoFeed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "periodo"));
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
