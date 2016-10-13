/**
 * ParametroVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;


/**
 * Value object con los atributos concretos de cada informe
 */
public class ParametroVO  implements java.io.Serializable {
    /* Nombre del parámetro que necesita el informe */
    private java.lang.String nombreParametro;

    /* Valor del atributo del informe */
    private java.lang.String valorAtributo;

    public ParametroVO() {
    }

    public ParametroVO(
           java.lang.String nombreParametro,
           java.lang.String valorAtributo) {
           this.nombreParametro = nombreParametro;
           this.valorAtributo = valorAtributo;
    }


    /**
     * Gets the nombreParametro value for this ParametroVO.
     * 
     * @return nombreParametro   * Nombre del parámetro que necesita el informe
     */
    public java.lang.String getNombreParametro() {
        return nombreParametro;
    }


    /**
     * Sets the nombreParametro value for this ParametroVO.
     * 
     * @param nombreParametro   * Nombre del parámetro que necesita el informe
     */
    public void setNombreParametro(java.lang.String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }


    /**
     * Gets the valorAtributo value for this ParametroVO.
     * 
     * @return valorAtributo   * Valor del atributo del informe
     */
    public java.lang.String getValorAtributo() {
        return valorAtributo;
    }


    /**
     * Sets the valorAtributo value for this ParametroVO.
     * 
     * @param valorAtributo   * Valor del atributo del informe
     */
    public void setValorAtributo(java.lang.String valorAtributo) {
        this.valorAtributo = valorAtributo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroVO)) return false;
        ParametroVO other = (ParametroVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreParametro==null && other.getNombreParametro()==null) || 
             (this.nombreParametro!=null &&
              this.nombreParametro.equals(other.getNombreParametro()))) &&
            ((this.valorAtributo==null && other.getValorAtributo()==null) || 
             (this.valorAtributo!=null &&
              this.valorAtributo.equals(other.getValorAtributo())));
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
        if (getNombreParametro() != null) {
            _hashCode += getNombreParametro().hashCode();
        }
        if (getValorAtributo() != null) {
            _hashCode += getValorAtributo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametroVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "ParametroVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreParametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "nombreParametro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAtributo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.auditoria.soporte.pode.es", "valorAtributo"));
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
