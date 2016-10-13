/**
 * VdexVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class VdexVO  implements java.io.Serializable {
    private java.lang.String nombre;

    private java.lang.String codigoError;

    private java.lang.String sufijo;

    private java.lang.String[] erroresParseo;

    private java.lang.String vocabNameDuplicado;

    public VdexVO() {
    }

    public VdexVO(
           java.lang.String nombre,
           java.lang.String codigoError,
           java.lang.String sufijo,
           java.lang.String[] erroresParseo,
           java.lang.String vocabNameDuplicado) {
           this.nombre = nombre;
           this.codigoError = codigoError;
           this.sufijo = sufijo;
           this.erroresParseo = erroresParseo;
           this.vocabNameDuplicado = vocabNameDuplicado;
    }


    /**
     * Gets the nombre value for this VdexVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this VdexVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the codigoError value for this VdexVO.
     * 
     * @return codigoError
     */
    public java.lang.String getCodigoError() {
        return codigoError;
    }


    /**
     * Sets the codigoError value for this VdexVO.
     * 
     * @param codigoError
     */
    public void setCodigoError(java.lang.String codigoError) {
        this.codigoError = codigoError;
    }


    /**
     * Gets the sufijo value for this VdexVO.
     * 
     * @return sufijo
     */
    public java.lang.String getSufijo() {
        return sufijo;
    }


    /**
     * Sets the sufijo value for this VdexVO.
     * 
     * @param sufijo
     */
    public void setSufijo(java.lang.String sufijo) {
        this.sufijo = sufijo;
    }


    /**
     * Gets the erroresParseo value for this VdexVO.
     * 
     * @return erroresParseo
     */
    public java.lang.String[] getErroresParseo() {
        return erroresParseo;
    }


    /**
     * Sets the erroresParseo value for this VdexVO.
     * 
     * @param erroresParseo
     */
    public void setErroresParseo(java.lang.String[] erroresParseo) {
        this.erroresParseo = erroresParseo;
    }


    /**
     * Gets the vocabNameDuplicado value for this VdexVO.
     * 
     * @return vocabNameDuplicado
     */
    public java.lang.String getVocabNameDuplicado() {
        return vocabNameDuplicado;
    }


    /**
     * Sets the vocabNameDuplicado value for this VdexVO.
     * 
     * @param vocabNameDuplicado
     */
    public void setVocabNameDuplicado(java.lang.String vocabNameDuplicado) {
        this.vocabNameDuplicado = vocabNameDuplicado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VdexVO)) return false;
        VdexVO other = (VdexVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.codigoError==null && other.getCodigoError()==null) || 
             (this.codigoError!=null &&
              this.codigoError.equals(other.getCodigoError()))) &&
            ((this.sufijo==null && other.getSufijo()==null) || 
             (this.sufijo!=null &&
              this.sufijo.equals(other.getSufijo()))) &&
            ((this.erroresParseo==null && other.getErroresParseo()==null) || 
             (this.erroresParseo!=null &&
              java.util.Arrays.equals(this.erroresParseo, other.getErroresParseo()))) &&
            ((this.vocabNameDuplicado==null && other.getVocabNameDuplicado()==null) || 
             (this.vocabNameDuplicado!=null &&
              this.vocabNameDuplicado.equals(other.getVocabNameDuplicado())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getCodigoError() != null) {
            _hashCode += getCodigoError().hashCode();
        }
        if (getSufijo() != null) {
            _hashCode += getSufijo().hashCode();
        }
        if (getErroresParseo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErroresParseo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErroresParseo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVocabNameDuplicado() != null) {
            _hashCode += getVocabNameDuplicado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VdexVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "VdexVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "codigoError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sufijo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "sufijo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("erroresParseo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "erroresParseo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabNameDuplicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "vocabNameDuplicado"));
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
