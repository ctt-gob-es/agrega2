/**
 * ErrorParseoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;


/**
 * Objeto VO conteniendo los datos del mensaje de error devuelto en
 * el parseo contra esquema de los manifiestos.
 */
public class ErrorParseoVO  implements java.io.Serializable {
    private java.lang.Integer linea;

    private java.lang.Integer columna;

    private java.lang.String mensaje;

    public ErrorParseoVO() {
    }

    public ErrorParseoVO(
           java.lang.Integer linea,
           java.lang.Integer columna,
           java.lang.String mensaje) {
           this.linea = linea;
           this.columna = columna;
           this.mensaje = mensaje;
    }


    /**
     * Gets the linea value for this ErrorParseoVO.
     * 
     * @return linea
     */
    public java.lang.Integer getLinea() {
        return linea;
    }


    /**
     * Sets the linea value for this ErrorParseoVO.
     * 
     * @param linea
     */
    public void setLinea(java.lang.Integer linea) {
        this.linea = linea;
    }


    /**
     * Gets the columna value for this ErrorParseoVO.
     * 
     * @return columna
     */
    public java.lang.Integer getColumna() {
        return columna;
    }


    /**
     * Sets the columna value for this ErrorParseoVO.
     * 
     * @param columna
     */
    public void setColumna(java.lang.Integer columna) {
        this.columna = columna;
    }


    /**
     * Gets the mensaje value for this ErrorParseoVO.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ErrorParseoVO.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorParseoVO)) return false;
        ErrorParseoVO other = (ErrorParseoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.linea==null && other.getLinea()==null) || 
             (this.linea!=null &&
              this.linea.equals(other.getLinea()))) &&
            ((this.columna==null && other.getColumna()==null) || 
             (this.columna!=null &&
              this.columna.equals(other.getColumna()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        if (getLinea() != null) {
            _hashCode += getLinea().hashCode();
        }
        if (getColumna() != null) {
            _hashCode += getColumna().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorParseoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ErrorParseoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "linea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columna");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "columna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "mensaje"));
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
