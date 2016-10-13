/**
 * ContribucionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class ContribucionVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.SourceValueVO tipo;

    private es.pode.catalogacion.negocio.servicios.FechaVO fecha;

    private es.pode.catalogacion.negocio.servicios.EntidadVO[] entidades;

    public ContribucionVO() {
    }

    public ContribucionVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO tipo,
           es.pode.catalogacion.negocio.servicios.FechaVO fecha,
           es.pode.catalogacion.negocio.servicios.EntidadVO[] entidades) {
           this.tipo = tipo;
           this.fecha = fecha;
           this.entidades = entidades;
    }


    /**
     * Gets the tipo value for this ContribucionVO.
     * 
     * @return tipo
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this ContribucionVO.
     * 
     * @param tipo
     */
    public void setTipo(es.pode.catalogacion.negocio.servicios.SourceValueVO tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the fecha value for this ContribucionVO.
     * 
     * @return fecha
     */
    public es.pode.catalogacion.negocio.servicios.FechaVO getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ContribucionVO.
     * 
     * @param fecha
     */
    public void setFecha(es.pode.catalogacion.negocio.servicios.FechaVO fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the entidades value for this ContribucionVO.
     * 
     * @return entidades
     */
    public es.pode.catalogacion.negocio.servicios.EntidadVO[] getEntidades() {
        return entidades;
    }


    /**
     * Sets the entidades value for this ContribucionVO.
     * 
     * @param entidades
     */
    public void setEntidades(es.pode.catalogacion.negocio.servicios.EntidadVO[] entidades) {
        this.entidades = entidades;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContribucionVO)) return false;
        ContribucionVO other = (ContribucionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
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
        new org.apache.axis.description.TypeDesc(ContribucionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ContribucionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FechaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "entidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntidadVO"));
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
