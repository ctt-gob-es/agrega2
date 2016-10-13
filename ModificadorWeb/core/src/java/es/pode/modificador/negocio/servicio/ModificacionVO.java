/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ModificacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class ModificacionVO  implements java.io.Serializable {
    private java.lang.String nombre;

    private java.lang.Long idModificacion;

    private es.pode.modificador.negocio.servicio.EstadosTarea resultado;

    private java.lang.String idPlanificador;

    private java.util.Calendar fechaEjecucion;

    /* Mensaje de error asociado a la modificacion. Se representaran
     * con c√≥digos para facilitar su internacionalizacion. */
    private java.lang.String msgError;

    private java.lang.String user;

    public ModificacionVO() {
    }

    public ModificacionVO(
           java.lang.String nombre,
           java.lang.Long idModificacion,
           es.pode.modificador.negocio.servicio.EstadosTarea resultado,
           java.lang.String idPlanificador,
           java.util.Calendar fechaEjecucion,
           java.lang.String msgError,
           java.lang.String user) {
           this.nombre = nombre;
           this.idModificacion = idModificacion;
           this.resultado = resultado;
           this.idPlanificador = idPlanificador;
           this.fechaEjecucion = fechaEjecucion;
           this.msgError = msgError;
           this.user = user;
    }


    /**
     * Gets the nombre value for this ModificacionVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ModificacionVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the idModificacion value for this ModificacionVO.
     * 
     * @return idModificacion
     */
    public java.lang.Long getIdModificacion() {
        return idModificacion;
    }


    /**
     * Sets the idModificacion value for this ModificacionVO.
     * 
     * @param idModificacion
     */
    public void setIdModificacion(java.lang.Long idModificacion) {
        this.idModificacion = idModificacion;
    }


    /**
     * Gets the resultado value for this ModificacionVO.
     * 
     * @return resultado
     */
    public es.pode.modificador.negocio.servicio.EstadosTarea getResultado() {
        return resultado;
    }


    /**
     * Sets the resultado value for this ModificacionVO.
     * 
     * @param resultado
     */
    public void setResultado(es.pode.modificador.negocio.servicio.EstadosTarea resultado) {
        this.resultado = resultado;
    }


    /**
     * Gets the idPlanificador value for this ModificacionVO.
     * 
     * @return idPlanificador
     */
    public java.lang.String getIdPlanificador() {
        return idPlanificador;
    }


    /**
     * Sets the idPlanificador value for this ModificacionVO.
     * 
     * @param idPlanificador
     */
    public void setIdPlanificador(java.lang.String idPlanificador) {
        this.idPlanificador = idPlanificador;
    }


    /**
     * Gets the fechaEjecucion value for this ModificacionVO.
     * 
     * @return fechaEjecucion
     */
    public java.util.Calendar getFechaEjecucion() {
        return fechaEjecucion;
    }


    /**
     * Sets the fechaEjecucion value for this ModificacionVO.
     * 
     * @param fechaEjecucion
     */
    public void setFechaEjecucion(java.util.Calendar fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }


    /**
     * Gets the msgError value for this ModificacionVO.
     * 
     * @return msgError   * Mensaje de error asociado a la modificacion. Se representaran
     * con c√≥digos para facilitar su internacionalizacion.
     */
    public java.lang.String getMsgError() {
        return msgError;
    }


    /**
     * Sets the msgError value for this ModificacionVO.
     * 
     * @param msgError   * Mensaje de error asociado a la modificacion. Se representaran
     * con c√≥digos para facilitar su internacionalizacion.
     */
    public void setMsgError(java.lang.String msgError) {
        this.msgError = msgError;
    }


    /**
     * Gets the user value for this ModificacionVO.
     * 
     * @return user
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this ModificacionVO.
     * 
     * @param user
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModificacionVO)) return false;
        ModificacionVO other = (ModificacionVO) obj;
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
            ((this.idModificacion==null && other.getIdModificacion()==null) || 
             (this.idModificacion!=null &&
              this.idModificacion.equals(other.getIdModificacion()))) &&
            ((this.resultado==null && other.getResultado()==null) || 
             (this.resultado!=null &&
              this.resultado.equals(other.getResultado()))) &&
            ((this.idPlanificador==null && other.getIdPlanificador()==null) || 
             (this.idPlanificador!=null &&
              this.idPlanificador.equals(other.getIdPlanificador()))) &&
            ((this.fechaEjecucion==null && other.getFechaEjecucion()==null) || 
             (this.fechaEjecucion!=null &&
              this.fechaEjecucion.equals(other.getFechaEjecucion()))) &&
            ((this.msgError==null && other.getMsgError()==null) || 
             (this.msgError!=null &&
              this.msgError.equals(other.getMsgError()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser())));
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
        if (getIdModificacion() != null) {
            _hashCode += getIdModificacion().hashCode();
        }
        if (getResultado() != null) {
            _hashCode += getResultado().hashCode();
        }
        if (getIdPlanificador() != null) {
            _hashCode += getIdPlanificador().hashCode();
        }
        if (getFechaEjecucion() != null) {
            _hashCode += getFechaEjecucion().hashCode();
        }
        if (getMsgError() != null) {
            _hashCode += getMsgError().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ModificacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ModificacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idModificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "resultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "EstadosTarea"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlanificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idPlanificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEjecucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "fechaEjecucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "msgError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "user"));
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
