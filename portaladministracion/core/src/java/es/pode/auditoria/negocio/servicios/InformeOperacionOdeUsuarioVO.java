/**
 * InformeOperacionOdeUsuarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Almacena la información de las operaciones que han realizado
 *                         cada uno de los usuarios con los odes
 */
public class InformeOperacionOdeUsuarioVO  implements java.io.Serializable {
    private java.lang.String usuario;

    private java.lang.String operacion;

    /* Quizas este parámetro sobrará */
    private int numOdes;

    private java.lang.String[] titulo;

    private java.lang.String estado;

    public InformeOperacionOdeUsuarioVO() {
    }

    public InformeOperacionOdeUsuarioVO(
           java.lang.String usuario,
           java.lang.String operacion,
           int numOdes,
           java.lang.String[] titulo,
           java.lang.String estado) {
           this.usuario = usuario;
           this.operacion = operacion;
           this.numOdes = numOdes;
           this.titulo = titulo;
           this.estado = estado;
    }


    /**
     * Gets the usuario value for this InformeOperacionOdeUsuarioVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this InformeOperacionOdeUsuarioVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the operacion value for this InformeOperacionOdeUsuarioVO.
     * 
     * @return operacion
     */
    public java.lang.String getOperacion() {
        return operacion;
    }


    /**
     * Sets the operacion value for this InformeOperacionOdeUsuarioVO.
     * 
     * @param operacion
     */
    public void setOperacion(java.lang.String operacion) {
        this.operacion = operacion;
    }


    /**
     * Gets the numOdes value for this InformeOperacionOdeUsuarioVO.
     * 
     * @return numOdes   * Quizas este parámetro sobrará
     */
    public int getNumOdes() {
        return numOdes;
    }


    /**
     * Sets the numOdes value for this InformeOperacionOdeUsuarioVO.
     * 
     * @param numOdes   * Quizas este parámetro sobrará
     */
    public void setNumOdes(int numOdes) {
        this.numOdes = numOdes;
    }


    /**
     * Gets the titulo value for this InformeOperacionOdeUsuarioVO.
     * 
     * @return titulo
     */
    public java.lang.String[] getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this InformeOperacionOdeUsuarioVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String[] titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the estado value for this InformeOperacionOdeUsuarioVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this InformeOperacionOdeUsuarioVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InformeOperacionOdeUsuarioVO)) return false;
        InformeOperacionOdeUsuarioVO other = (InformeOperacionOdeUsuarioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.operacion==null && other.getOperacion()==null) || 
             (this.operacion!=null &&
              this.operacion.equals(other.getOperacion()))) &&
            this.numOdes == other.getNumOdes() &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              java.util.Arrays.equals(this.titulo, other.getTitulo()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado())));
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
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getOperacion() != null) {
            _hashCode += getOperacion().hashCode();
        }
        _hashCode += getNumOdes();
        if (getTitulo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTitulo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTitulo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InformeOperacionOdeUsuarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "InformeOperacionOdeUsuarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "operacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numOdes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "numOdes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "estado"));
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
