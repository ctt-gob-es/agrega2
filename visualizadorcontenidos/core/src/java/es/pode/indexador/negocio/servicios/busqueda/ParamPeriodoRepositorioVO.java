/**
 * ParamPeriodoRepositorioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Este value object contiene los parametros de consulta de un
 *                         periodo de tiempo sobre el repositorio.
 */
public class ParamPeriodoRepositorioVO  implements java.io.Serializable {
    /* Fecha desde del periodo de tiempo en el que se esta interesado. */
    private java.util.Calendar desde;

    /* Fecha hasta del periodo de tiempo en el que estamos interesados. */
    private java.util.Calendar hasta;

    /* Primer registro que se tendrá en cuenta en el índice */
    private java.lang.Integer registroInicial;

    /* Ultimo registro que se tendrá en cuenta en el índice */
    private java.lang.Integer registroFinal;

    public ParamPeriodoRepositorioVO() {
    }

    public ParamPeriodoRepositorioVO(
           java.util.Calendar desde,
           java.util.Calendar hasta,
           java.lang.Integer registroInicial,
           java.lang.Integer registroFinal) {
           this.desde = desde;
           this.hasta = hasta;
           this.registroInicial = registroInicial;
           this.registroFinal = registroFinal;
    }


    /**
     * Gets the desde value for this ParamPeriodoRepositorioVO.
     * 
     * @return desde   * Fecha desde del periodo de tiempo en el que se esta interesado.
     */
    public java.util.Calendar getDesde() {
        return desde;
    }


    /**
     * Sets the desde value for this ParamPeriodoRepositorioVO.
     * 
     * @param desde   * Fecha desde del periodo de tiempo en el que se esta interesado.
     */
    public void setDesde(java.util.Calendar desde) {
        this.desde = desde;
    }


    /**
     * Gets the hasta value for this ParamPeriodoRepositorioVO.
     * 
     * @return hasta   * Fecha hasta del periodo de tiempo en el que estamos interesados.
     */
    public java.util.Calendar getHasta() {
        return hasta;
    }


    /**
     * Sets the hasta value for this ParamPeriodoRepositorioVO.
     * 
     * @param hasta   * Fecha hasta del periodo de tiempo en el que estamos interesados.
     */
    public void setHasta(java.util.Calendar hasta) {
        this.hasta = hasta;
    }


    /**
     * Gets the registroInicial value for this ParamPeriodoRepositorioVO.
     * 
     * @return registroInicial   * Primer registro que se tendrá en cuenta en el índice
     */
    public java.lang.Integer getRegistroInicial() {
        return registroInicial;
    }


    /**
     * Sets the registroInicial value for this ParamPeriodoRepositorioVO.
     * 
     * @param registroInicial   * Primer registro que se tendrá en cuenta en el índice
     */
    public void setRegistroInicial(java.lang.Integer registroInicial) {
        this.registroInicial = registroInicial;
    }


    /**
     * Gets the registroFinal value for this ParamPeriodoRepositorioVO.
     * 
     * @return registroFinal   * Ultimo registro que se tendrá en cuenta en el índice
     */
    public java.lang.Integer getRegistroFinal() {
        return registroFinal;
    }


    /**
     * Sets the registroFinal value for this ParamPeriodoRepositorioVO.
     * 
     * @param registroFinal   * Ultimo registro que se tendrá en cuenta en el índice
     */
    public void setRegistroFinal(java.lang.Integer registroFinal) {
        this.registroFinal = registroFinal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamPeriodoRepositorioVO)) return false;
        ParamPeriodoRepositorioVO other = (ParamPeriodoRepositorioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.desde==null && other.getDesde()==null) || 
             (this.desde!=null &&
              this.desde.equals(other.getDesde()))) &&
            ((this.hasta==null && other.getHasta()==null) || 
             (this.hasta!=null &&
              this.hasta.equals(other.getHasta()))) &&
            ((this.registroInicial==null && other.getRegistroInicial()==null) || 
             (this.registroInicial!=null &&
              this.registroInicial.equals(other.getRegistroInicial()))) &&
            ((this.registroFinal==null && other.getRegistroFinal()==null) || 
             (this.registroFinal!=null &&
              this.registroFinal.equals(other.getRegistroFinal())));
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
        if (getDesde() != null) {
            _hashCode += getDesde().hashCode();
        }
        if (getHasta() != null) {
            _hashCode += getHasta().hashCode();
        }
        if (getRegistroInicial() != null) {
            _hashCode += getRegistroInicial().hashCode();
        }
        if (getRegistroFinal() != null) {
            _hashCode += getRegistroFinal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamPeriodoRepositorioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ParamPeriodoRepositorioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "desde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "hasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "registroInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "registroFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
