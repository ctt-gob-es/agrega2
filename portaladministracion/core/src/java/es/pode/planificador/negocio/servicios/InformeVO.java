/**
 * InformeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * Este VO se utilizará para guardar los datos de los informes que
 * se generan al hacer la carga masiva. Guardará los datos del los
 *                         ficheros, el nombre y la fecha de la última
 * modificación
 */
public class InformeVO  implements java.io.Serializable {
    /* Nombre del fichero del informe */
    private java.lang.String nombreFichero;

    /* La fecha de la última modificación */
    private java.util.Calendar fechaModificacion;

    public InformeVO() {
    }

    public InformeVO(
           java.lang.String nombreFichero,
           java.util.Calendar fechaModificacion) {
           this.nombreFichero = nombreFichero;
           this.fechaModificacion = fechaModificacion;
    }


    /**
     * Gets the nombreFichero value for this InformeVO.
     * 
     * @return nombreFichero   * Nombre del fichero del informe
     */
    public java.lang.String getNombreFichero() {
        return nombreFichero;
    }


    /**
     * Sets the nombreFichero value for this InformeVO.
     * 
     * @param nombreFichero   * Nombre del fichero del informe
     */
    public void setNombreFichero(java.lang.String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }


    /**
     * Gets the fechaModificacion value for this InformeVO.
     * 
     * @return fechaModificacion   * La fecha de la última modificación
     */
    public java.util.Calendar getFechaModificacion() {
        return fechaModificacion;
    }


    /**
     * Sets the fechaModificacion value for this InformeVO.
     * 
     * @param fechaModificacion   * La fecha de la última modificación
     */
    public void setFechaModificacion(java.util.Calendar fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeVO)) return false;
        InformeVO other = (InformeVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreFichero==null && other.getNombreFichero()==null) || 
             (this.nombreFichero!=null &&
              this.nombreFichero.equals(other.getNombreFichero()))) &&
            ((this.fechaModificacion==null && other.getFechaModificacion()==null) || 
             (this.fechaModificacion!=null &&
              this.fechaModificacion.equals(other.getFechaModificacion())));
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
        if (getNombreFichero() != null) {
            _hashCode += getNombreFichero().hashCode();
        }
        if (getFechaModificacion() != null) {
            _hashCode += getFechaModificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "InformeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreFichero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreFichero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaModificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
