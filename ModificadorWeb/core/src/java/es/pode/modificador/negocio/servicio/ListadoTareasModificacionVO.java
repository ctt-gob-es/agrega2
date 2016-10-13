/**
 * ListadoTareasModificacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;


/**
 * Value Object con las tareas ejecutadas y las pendientes de
 *                         finalización
 */
public class ListadoTareasModificacionVO  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.ModificacionVO[] pendientes;

    private es.pode.modificador.negocio.servicio.ModificacionVO[] ejecutadas;

    public ListadoTareasModificacionVO() {
    }

    public ListadoTareasModificacionVO(
           es.pode.modificador.negocio.servicio.ModificacionVO[] pendientes,
           es.pode.modificador.negocio.servicio.ModificacionVO[] ejecutadas) {
           this.pendientes = pendientes;
           this.ejecutadas = ejecutadas;
    }


    /**
     * Gets the pendientes value for this ListadoTareasModificacionVO.
     * 
     * @return pendientes
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] getPendientes() {
        return pendientes;
    }


    /**
     * Sets the pendientes value for this ListadoTareasModificacionVO.
     * 
     * @param pendientes
     */
    public void setPendientes(es.pode.modificador.negocio.servicio.ModificacionVO[] pendientes) {
        this.pendientes = pendientes;
    }


    /**
     * Gets the ejecutadas value for this ListadoTareasModificacionVO.
     * 
     * @return ejecutadas
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] getEjecutadas() {
        return ejecutadas;
    }


    /**
     * Sets the ejecutadas value for this ListadoTareasModificacionVO.
     * 
     * @param ejecutadas
     */
    public void setEjecutadas(es.pode.modificador.negocio.servicio.ModificacionVO[] ejecutadas) {
        this.ejecutadas = ejecutadas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListadoTareasModificacionVO)) return false;
        ListadoTareasModificacionVO other = (ListadoTareasModificacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pendientes==null && other.getPendientes()==null) || 
             (this.pendientes!=null &&
              java.util.Arrays.equals(this.pendientes, other.getPendientes()))) &&
            ((this.ejecutadas==null && other.getEjecutadas()==null) || 
             (this.ejecutadas!=null &&
              java.util.Arrays.equals(this.ejecutadas, other.getEjecutadas())));
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
        if (getPendientes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPendientes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPendientes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEjecutadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEjecutadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEjecutadas(), i);
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
        new org.apache.axis.description.TypeDesc(ListadoTareasModificacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ListadoTareasModificacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pendientes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "pendientes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ModificacionVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ejecutadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ejecutadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ModificacionVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
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
