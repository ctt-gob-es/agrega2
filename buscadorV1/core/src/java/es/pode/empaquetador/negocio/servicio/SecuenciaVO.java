/**
 * SecuenciaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Clase que representa una secuencia IMS simplificada. Se
 *                         corresponde con la clase Sequencing.
 */
public class SecuenciaVO  implements java.io.Serializable {
    /* Modo de control de la secuencia. Se corresponde con el atributo
     * controlMode de la clase Sequencing. */
    private es.pode.empaquetador.negocio.servicio.ControlModeVO controlMode;

    public SecuenciaVO() {
    }

    public SecuenciaVO(
           es.pode.empaquetador.negocio.servicio.ControlModeVO controlMode) {
           this.controlMode = controlMode;
    }


    /**
     * Gets the controlMode value for this SecuenciaVO.
     * 
     * @return controlMode   * Modo de control de la secuencia. Se corresponde con el atributo
     * controlMode de la clase Sequencing.
     */
    public es.pode.empaquetador.negocio.servicio.ControlModeVO getControlMode() {
        return controlMode;
    }


    /**
     * Sets the controlMode value for this SecuenciaVO.
     * 
     * @param controlMode   * Modo de control de la secuencia. Se corresponde con el atributo
     * controlMode de la clase Sequencing.
     */
    public void setControlMode(es.pode.empaquetador.negocio.servicio.ControlModeVO controlMode) {
        this.controlMode = controlMode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SecuenciaVO)) return false;
        SecuenciaVO other = (SecuenciaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.controlMode==null && other.getControlMode()==null) || 
             (this.controlMode!=null &&
              this.controlMode.equals(other.getControlMode())));
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
        if (getControlMode() != null) {
            _hashCode += getControlMode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SecuenciaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SecuenciaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "controlMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ControlModeVO"));
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
