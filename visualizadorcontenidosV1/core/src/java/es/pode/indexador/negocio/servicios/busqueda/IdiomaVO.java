/**
 * IdiomaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public class IdiomaVO  implements java.io.Serializable {
    /* Nombre largo del idioma. */
    private java.lang.String nombre;

    /* Identificador del idioma. */
    private java.lang.String idIdioma;

    private es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO[] idiomasBuscables;

    public IdiomaVO() {
    }

    public IdiomaVO(
           java.lang.String nombre,
           java.lang.String idIdioma,
           es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO[] idiomasBuscables) {
           this.nombre = nombre;
           this.idIdioma = idIdioma;
           this.idiomasBuscables = idiomasBuscables;
    }


    /**
     * Gets the nombre value for this IdiomaVO.
     * 
     * @return nombre   * Nombre largo del idioma.
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this IdiomaVO.
     * 
     * @param nombre   * Nombre largo del idioma.
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the idIdioma value for this IdiomaVO.
     * 
     * @return idIdioma   * Identificador del idioma.
     */
    public java.lang.String getIdIdioma() {
        return idIdioma;
    }


    /**
     * Sets the idIdioma value for this IdiomaVO.
     * 
     * @param idIdioma   * Identificador del idioma.
     */
    public void setIdIdioma(java.lang.String idIdioma) {
        this.idIdioma = idIdioma;
    }


    /**
     * Gets the idiomasBuscables value for this IdiomaVO.
     * 
     * @return idiomasBuscables
     */
    public es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO[] getIdiomasBuscables() {
        return idiomasBuscables;
    }


    /**
     * Sets the idiomasBuscables value for this IdiomaVO.
     * 
     * @param idiomasBuscables
     */
    public void setIdiomasBuscables(es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO[] idiomasBuscables) {
        this.idiomasBuscables = idiomasBuscables;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdiomaVO)) return false;
        IdiomaVO other = (IdiomaVO) obj;
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
            ((this.idIdioma==null && other.getIdIdioma()==null) || 
             (this.idIdioma!=null &&
              this.idIdioma.equals(other.getIdIdioma()))) &&
            ((this.idiomasBuscables==null && other.getIdiomasBuscables()==null) || 
             (this.idiomasBuscables!=null &&
              java.util.Arrays.equals(this.idiomasBuscables, other.getIdiomasBuscables())));
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
        if (getIdIdioma() != null) {
            _hashCode += getIdIdioma().hashCode();
        }
        if (getIdiomasBuscables() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdiomasBuscables());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdiomasBuscables(), i);
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
        new org.apache.axis.description.TypeDesc(IdiomaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "IdiomaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIdioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idIdioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomasBuscables");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idiomasBuscables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "LocalizacionIdiomaVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
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
