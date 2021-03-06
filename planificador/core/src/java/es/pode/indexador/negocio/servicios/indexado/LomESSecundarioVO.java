/**
 * LomESSecundarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;


/**
 * Esta clase modela los datos relevantes de ser indexados en el
 *                         cuerpo de un LOM-ES secundario de un ODE.
 */
public class LomESSecundarioVO  implements java.io.Serializable {
    /* TÃ­tulo de un elemento concreto (item, recursos,..) dentro
     * del
     *                                 manifest */
    private java.lang.String titulo;

    /* Palabras Clave de un elemento concreto (item, recursos,....)
     * dentro del manifest */
    private java.lang.String[] palabrasClave;

    /* DescripciÃ³n de un elemento concreto (item, recursos,....)
     *                                 dentro del manifest */
    private java.lang.String descripcion;

    public LomESSecundarioVO() {
    }

    public LomESSecundarioVO(
           java.lang.String titulo,
           java.lang.String[] palabrasClave,
           java.lang.String descripcion) {
           this.titulo = titulo;
           this.palabrasClave = palabrasClave;
           this.descripcion = descripcion;
    }


    /**
     * Gets the titulo value for this LomESSecundarioVO.
     * 
     * @return titulo   * TÃ­tulo de un elemento concreto (item, recursos,..) dentro
     * del
     *                                 manifest
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this LomESSecundarioVO.
     * 
     * @param titulo   * TÃ­tulo de un elemento concreto (item, recursos,..) dentro
     * del
     *                                 manifest
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the palabrasClave value for this LomESSecundarioVO.
     * 
     * @return palabrasClave   * Palabras Clave de un elemento concreto (item, recursos,....)
     * dentro del manifest
     */
    public java.lang.String[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this LomESSecundarioVO.
     * 
     * @param palabrasClave   * Palabras Clave de un elemento concreto (item, recursos,....)
     * dentro del manifest
     */
    public void setPalabrasClave(java.lang.String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the descripcion value for this LomESSecundarioVO.
     * 
     * @return descripcion   * DescripciÃ³n de un elemento concreto (item, recursos,....)
     *                                 dentro del manifest
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this LomESSecundarioVO.
     * 
     * @param descripcion   * DescripciÃ³n de un elemento concreto (item, recursos,....)
     *                                 dentro del manifest
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LomESSecundarioVO)) return false;
        LomESSecundarioVO other = (LomESSecundarioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion())));
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getPalabrasClave() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPalabrasClave());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPalabrasClave(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LomESSecundarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESSecundarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "descripcion"));
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
