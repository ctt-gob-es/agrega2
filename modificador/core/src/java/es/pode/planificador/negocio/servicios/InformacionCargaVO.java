/**
 * InformacionCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class InformacionCargaVO  implements java.io.Serializable {
    /* El path donde se encuentran los odes de la carpeta */
    private java.lang.String pathOde;

    /* Número de odes que se encuentran en la carpeta en estado
     *                                 publicado */
    private java.lang.Long numOdes;

    /* Array que contiene todos los identificadores de los odes que
     * se
     *                                 encuentran en la carpeta en estado
     * publicado */
    private java.lang.String[] identificadores;

    /* Indica si existe algún ode dentro de la carpeta que no este
     * despublicado */
    private java.lang.Boolean despublicado;

    public InformacionCargaVO() {
    }

    public InformacionCargaVO(
           java.lang.String pathOde,
           java.lang.Long numOdes,
           java.lang.String[] identificadores,
           java.lang.Boolean despublicado) {
           this.pathOde = pathOde;
           this.numOdes = numOdes;
           this.identificadores = identificadores;
           this.despublicado = despublicado;
    }


    /**
     * Gets the pathOde value for this InformacionCargaVO.
     * 
     * @return pathOde   * El path donde se encuentran los odes de la carpeta
     */
    public java.lang.String getPathOde() {
        return pathOde;
    }


    /**
     * Sets the pathOde value for this InformacionCargaVO.
     * 
     * @param pathOde   * El path donde se encuentran los odes de la carpeta
     */
    public void setPathOde(java.lang.String pathOde) {
        this.pathOde = pathOde;
    }


    /**
     * Gets the numOdes value for this InformacionCargaVO.
     * 
     * @return numOdes   * Número de odes que se encuentran en la carpeta en estado
     *                                 publicado
     */
    public java.lang.Long getNumOdes() {
        return numOdes;
    }


    /**
     * Sets the numOdes value for this InformacionCargaVO.
     * 
     * @param numOdes   * Número de odes que se encuentran en la carpeta en estado
     *                                 publicado
     */
    public void setNumOdes(java.lang.Long numOdes) {
        this.numOdes = numOdes;
    }


    /**
     * Gets the identificadores value for this InformacionCargaVO.
     * 
     * @return identificadores   * Array que contiene todos los identificadores de los odes que
     * se
     *                                 encuentran en la carpeta en estado
     * publicado
     */
    public java.lang.String[] getIdentificadores() {
        return identificadores;
    }


    /**
     * Sets the identificadores value for this InformacionCargaVO.
     * 
     * @param identificadores   * Array que contiene todos los identificadores de los odes que
     * se
     *                                 encuentran en la carpeta en estado
     * publicado
     */
    public void setIdentificadores(java.lang.String[] identificadores) {
        this.identificadores = identificadores;
    }


    /**
     * Gets the despublicado value for this InformacionCargaVO.
     * 
     * @return despublicado   * Indica si existe algún ode dentro de la carpeta que no este
     * despublicado
     */
    public java.lang.Boolean getDespublicado() {
        return despublicado;
    }


    /**
     * Sets the despublicado value for this InformacionCargaVO.
     * 
     * @param despublicado   * Indica si existe algún ode dentro de la carpeta que no este
     * despublicado
     */
    public void setDespublicado(java.lang.Boolean despublicado) {
        this.despublicado = despublicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformacionCargaVO)) return false;
        InformacionCargaVO other = (InformacionCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pathOde==null && other.getPathOde()==null) || 
             (this.pathOde!=null &&
              this.pathOde.equals(other.getPathOde()))) &&
            ((this.numOdes==null && other.getNumOdes()==null) || 
             (this.numOdes!=null &&
              this.numOdes.equals(other.getNumOdes()))) &&
            ((this.identificadores==null && other.getIdentificadores()==null) || 
             (this.identificadores!=null &&
              java.util.Arrays.equals(this.identificadores, other.getIdentificadores()))) &&
            ((this.despublicado==null && other.getDespublicado()==null) || 
             (this.despublicado!=null &&
              this.despublicado.equals(other.getDespublicado())));
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
        if (getPathOde() != null) {
            _hashCode += getPathOde().hashCode();
        }
        if (getNumOdes() != null) {
            _hashCode += getNumOdes().hashCode();
        }
        if (getIdentificadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificadores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDespublicado() != null) {
            _hashCode += getDespublicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformacionCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "InformacionCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "numOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "identificadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("despublicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "despublicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
