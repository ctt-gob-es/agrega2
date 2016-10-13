/**
 * TipoAtomOre.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaiore.negocio.servicio;

public class TipoAtomOre  implements java.io.Serializable {
    /* Nivel de agregacion */
    private java.lang.Integer nivelAgregacion;

    /* Area curricular */
    private java.lang.String[] areaCurricular;

    /* Tesauro */
    private java.lang.String[] tesauro;

    /* Tipo de informe:
     *                                 1 es nivel agregacion
     *                                 2 es area curricular
     *                                 3 es tesauro */
    private java.lang.Integer tipo;

    public TipoAtomOre() {
    }

    public TipoAtomOre(
           java.lang.Integer nivelAgregacion,
           java.lang.String[] areaCurricular,
           java.lang.String[] tesauro,
           java.lang.Integer tipo) {
           this.nivelAgregacion = nivelAgregacion;
           this.areaCurricular = areaCurricular;
           this.tesauro = tesauro;
           this.tipo = tipo;
    }


    /**
     * Gets the nivelAgregacion value for this TipoAtomOre.
     * 
     * @return nivelAgregacion   * Nivel de agregacion
     */
    public java.lang.Integer getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this TipoAtomOre.
     * 
     * @param nivelAgregacion   * Nivel de agregacion
     */
    public void setNivelAgregacion(java.lang.Integer nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the areaCurricular value for this TipoAtomOre.
     * 
     * @return areaCurricular   * Area curricular
     */
    public java.lang.String[] getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this TipoAtomOre.
     * 
     * @param areaCurricular   * Area curricular
     */
    public void setAreaCurricular(java.lang.String[] areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the tesauro value for this TipoAtomOre.
     * 
     * @return tesauro   * Tesauro
     */
    public java.lang.String[] getTesauro() {
        return tesauro;
    }


    /**
     * Sets the tesauro value for this TipoAtomOre.
     * 
     * @param tesauro   * Tesauro
     */
    public void setTesauro(java.lang.String[] tesauro) {
        this.tesauro = tesauro;
    }


    /**
     * Gets the tipo value for this TipoAtomOre.
     * 
     * @return tipo   * Tipo de informe:
     *                                 1 es nivel agregacion
     *                                 2 es area curricular
     *                                 3 es tesauro
     */
    public java.lang.Integer getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this TipoAtomOre.
     * 
     * @param tipo   * Tipo de informe:
     *                                 1 es nivel agregacion
     *                                 2 es area curricular
     *                                 3 es tesauro
     */
    public void setTipo(java.lang.Integer tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TipoAtomOre)) return false;
        TipoAtomOre other = (TipoAtomOre) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              java.util.Arrays.equals(this.areaCurricular, other.getAreaCurricular()))) &&
            ((this.tesauro==null && other.getTesauro()==null) || 
             (this.tesauro!=null &&
              java.util.Arrays.equals(this.tesauro, other.getTesauro()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
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
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getAreaCurricular() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAreaCurricular());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAreaCurricular(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTesauro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTesauro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTesauro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoAtomOre.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "TipoAtomOre"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tesauro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "tesauro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "tipo"));
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
