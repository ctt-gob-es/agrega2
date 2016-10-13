/**
 * RutaTaxonomicaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class RutaTaxonomicaVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.FuenteVO fuente;

    private es.pode.catalogacion.negocio.servicios.TaxonVO[] taxones;

    public RutaTaxonomicaVO() {
    }

    public RutaTaxonomicaVO(
           es.pode.catalogacion.negocio.servicios.FuenteVO fuente,
           es.pode.catalogacion.negocio.servicios.TaxonVO[] taxones) {
           this.fuente = fuente;
           this.taxones = taxones;
    }


    /**
     * Gets the fuente value for this RutaTaxonomicaVO.
     * 
     * @return fuente
     */
    public es.pode.catalogacion.negocio.servicios.FuenteVO getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this RutaTaxonomicaVO.
     * 
     * @param fuente
     */
    public void setFuente(es.pode.catalogacion.negocio.servicios.FuenteVO fuente) {
        this.fuente = fuente;
    }


    /**
     * Gets the taxones value for this RutaTaxonomicaVO.
     * 
     * @return taxones
     */
    public es.pode.catalogacion.negocio.servicios.TaxonVO[] getTaxones() {
        return taxones;
    }


    /**
     * Sets the taxones value for this RutaTaxonomicaVO.
     * 
     * @param taxones
     */
    public void setTaxones(es.pode.catalogacion.negocio.servicios.TaxonVO[] taxones) {
        this.taxones = taxones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RutaTaxonomicaVO)) return false;
        RutaTaxonomicaVO other = (RutaTaxonomicaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              this.fuente.equals(other.getFuente()))) &&
            ((this.taxones==null && other.getTaxones()==null) || 
             (this.taxones!=null &&
              java.util.Arrays.equals(this.taxones, other.getTaxones())));
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
        if (getFuente() != null) {
            _hashCode += getFuente().hashCode();
        }
        if (getTaxones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxones(), i);
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
        new org.apache.axis.description.TypeDesc(RutaTaxonomicaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RutaTaxonomicaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "fuente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FuenteVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "taxones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TaxonVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
