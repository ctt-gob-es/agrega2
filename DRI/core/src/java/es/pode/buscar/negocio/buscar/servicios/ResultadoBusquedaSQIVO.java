/**
 * ResultadoBusquedaSQIVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * Esta clase encapsula la informacion devuelta por el buscador en
 * el caso de realizarse una busqueda por SQI.
 */
public class ResultadoBusquedaSQIVO  implements java.io.Serializable {
    /* La lista de resultados fruto de la busqueda por SQI. */
    private es.pode.buscar.negocio.buscar.servicios.LomEsVO[] resultadoBusqueda;

    /* Numero de resultados fruto de la busqueda. */
    private java.lang.Integer numeroResultados;

    public ResultadoBusquedaSQIVO() {
    }

    public ResultadoBusquedaSQIVO(
           es.pode.buscar.negocio.buscar.servicios.LomEsVO[] resultadoBusqueda,
           java.lang.Integer numeroResultados) {
           this.resultadoBusqueda = resultadoBusqueda;
           this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the resultadoBusqueda value for this ResultadoBusquedaSQIVO.
     * 
     * @return resultadoBusqueda   * La lista de resultados fruto de la busqueda por SQI.
     */
    public es.pode.buscar.negocio.buscar.servicios.LomEsVO[] getResultadoBusqueda() {
        return resultadoBusqueda;
    }


    /**
     * Sets the resultadoBusqueda value for this ResultadoBusquedaSQIVO.
     * 
     * @param resultadoBusqueda   * La lista de resultados fruto de la busqueda por SQI.
     */
    public void setResultadoBusqueda(es.pode.buscar.negocio.buscar.servicios.LomEsVO[] resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }


    /**
     * Gets the numeroResultados value for this ResultadoBusquedaSQIVO.
     * 
     * @return numeroResultados   * Numero de resultados fruto de la busqueda.
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ResultadoBusquedaSQIVO.
     * 
     * @param numeroResultados   * Numero de resultados fruto de la busqueda.
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBusquedaSQIVO)) return false;
        ResultadoBusquedaSQIVO other = (ResultadoBusquedaSQIVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBusqueda==null && other.getResultadoBusqueda()==null) || 
             (this.resultadoBusqueda!=null &&
              java.util.Arrays.equals(this.resultadoBusqueda, other.getResultadoBusqueda()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados())));
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
        if (getResultadoBusqueda() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBusqueda());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBusqueda(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBusquedaSQIVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaSQIVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "LomEsVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
