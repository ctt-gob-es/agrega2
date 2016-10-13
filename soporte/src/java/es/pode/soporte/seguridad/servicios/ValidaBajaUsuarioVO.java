/**
 * ValidaBajaUsuarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.seguridad.servicios;

public class ValidaBajaUsuarioVO  implements java.io.Serializable {
    private java.lang.String descripcion;

    private java.lang.Integer numDeleted;

    private es.pode.soporte.seguridad.servicios.UsuarioVO[] itemsDeleted;

    public ValidaBajaUsuarioVO() {
    }

    public ValidaBajaUsuarioVO(
           java.lang.String descripcion,
           java.lang.Integer numDeleted,
           es.pode.soporte.seguridad.servicios.UsuarioVO[] itemsDeleted) {
           this.descripcion = descripcion;
           this.numDeleted = numDeleted;
           this.itemsDeleted = itemsDeleted;
    }


    /**
     * Gets the descripcion value for this ValidaBajaUsuarioVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ValidaBajaUsuarioVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the numDeleted value for this ValidaBajaUsuarioVO.
     * 
     * @return numDeleted
     */
    public java.lang.Integer getNumDeleted() {
        return numDeleted;
    }


    /**
     * Sets the numDeleted value for this ValidaBajaUsuarioVO.
     * 
     * @param numDeleted
     */
    public void setNumDeleted(java.lang.Integer numDeleted) {
        this.numDeleted = numDeleted;
    }


    /**
     * Gets the itemsDeleted value for this ValidaBajaUsuarioVO.
     * 
     * @return itemsDeleted
     */
    public es.pode.soporte.seguridad.servicios.UsuarioVO[] getItemsDeleted() {
        return itemsDeleted;
    }


    /**
     * Sets the itemsDeleted value for this ValidaBajaUsuarioVO.
     * 
     * @param itemsDeleted
     */
    public void setItemsDeleted(es.pode.soporte.seguridad.servicios.UsuarioVO[] itemsDeleted) {
        this.itemsDeleted = itemsDeleted;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaBajaUsuarioVO)) return false;
        ValidaBajaUsuarioVO other = (ValidaBajaUsuarioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.numDeleted==null && other.getNumDeleted()==null) || 
             (this.numDeleted!=null &&
              this.numDeleted.equals(other.getNumDeleted()))) &&
            ((this.itemsDeleted==null && other.getItemsDeleted()==null) || 
             (this.itemsDeleted!=null &&
              java.util.Arrays.equals(this.itemsDeleted, other.getItemsDeleted())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getNumDeleted() != null) {
            _hashCode += getNumDeleted().hashCode();
        }
        if (getItemsDeleted() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItemsDeleted());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItemsDeleted(), i);
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
        new org.apache.axis.description.TypeDesc(ValidaBajaUsuarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "ValidaBajaUsuarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDeleted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "numDeleted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemsDeleted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "itemsDeleted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "UsuarioVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "item"));
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
