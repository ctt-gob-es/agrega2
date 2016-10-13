/**
 * ContribucionOdeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ContribucionOdeVO  implements java.io.Serializable {
    private java.lang.String tipoContribucion;

    private java.lang.String fecha;

    private es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO[] entidades;

    public ContribucionOdeVO() {
    }

    public ContribucionOdeVO(
           java.lang.String tipoContribucion,
           java.lang.String fecha,
           es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO[] entidades) {
           this.tipoContribucion = tipoContribucion;
           this.fecha = fecha;
           this.entidades = entidades;
    }


    /**
     * Gets the tipoContribucion value for this ContribucionOdeVO.
     * 
     * @return tipoContribucion
     */
    public java.lang.String getTipoContribucion() {
        return tipoContribucion;
    }


    /**
     * Sets the tipoContribucion value for this ContribucionOdeVO.
     * 
     * @param tipoContribucion
     */
    public void setTipoContribucion(java.lang.String tipoContribucion) {
        this.tipoContribucion = tipoContribucion;
    }


    /**
     * Gets the fecha value for this ContribucionOdeVO.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ContribucionOdeVO.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the entidades value for this ContribucionOdeVO.
     * 
     * @return entidades
     */
    public es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO[] getEntidades() {
        return entidades;
    }


    /**
     * Sets the entidades value for this ContribucionOdeVO.
     * 
     * @param entidades
     */
    public void setEntidades(es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO[] entidades) {
        this.entidades = entidades;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContribucionOdeVO)) return false;
        ContribucionOdeVO other = (ContribucionOdeVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoContribucion==null && other.getTipoContribucion()==null) || 
             (this.tipoContribucion!=null &&
              this.tipoContribucion.equals(other.getTipoContribucion()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.entidades==null && other.getEntidades()==null) || 
             (this.entidades!=null &&
              java.util.Arrays.equals(this.entidades, other.getEntidades())));
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
        if (getTipoContribucion() != null) {
            _hashCode += getTipoContribucion().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getEntidades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntidades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntidades(), i);
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
        new org.apache.axis.description.TypeDesc(ContribucionOdeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ContribucionOdeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoContribucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tipoContribucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "entidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "EntidadOdeVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
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
