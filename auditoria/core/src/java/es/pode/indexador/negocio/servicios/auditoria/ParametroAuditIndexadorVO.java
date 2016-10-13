/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametroAuditIndexadorVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.auditoria;


/**
 * Esta clase alberta todos los parametros necesarios para la
 *                         ejecucion de los metodos de auditoria.
 */
public class ParametroAuditIndexadorVO  implements java.io.Serializable {
    /* Fecha desde del periodo en el que se esta interesado. */
    private java.util.Calendar fechaDesde;

    /* Fecha hasta del periodo en el que se esta interesado. */
    private java.util.Calendar fechaHasta;

    /* Lista de identificadores de los nodos del arbol curricular
     * del
     *                                 que se quiere calcular la cobertura. */
    private java.lang.String[] idNodo;

    /* Lista de licencias que se quieren buscar. */
    private java.lang.String[] idLicencias;

    public ParametroAuditIndexadorVO() {
    }

    public ParametroAuditIndexadorVO(
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.String[] idNodo,
           java.lang.String[] idLicencias) {
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.idNodo = idNodo;
           this.idLicencias = idLicencias;
    }


    /**
     * Gets the fechaDesde value for this ParametroAuditIndexadorVO.
     * 
     * @return fechaDesde   * Fecha desde del periodo en el que se esta interesado.
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this ParametroAuditIndexadorVO.
     * 
     * @param fechaDesde   * Fecha desde del periodo en el que se esta interesado.
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this ParametroAuditIndexadorVO.
     * 
     * @return fechaHasta   * Fecha hasta del periodo en el que se esta interesado.
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this ParametroAuditIndexadorVO.
     * 
     * @param fechaHasta   * Fecha hasta del periodo en el que se esta interesado.
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the idNodo value for this ParametroAuditIndexadorVO.
     * 
     * @return idNodo   * Lista de identificadores de los nodos del arbol curricular
     * del
     *                                 que se quiere calcular la cobertura.
     */
    public java.lang.String[] getIdNodo() {
        return idNodo;
    }


    /**
     * Sets the idNodo value for this ParametroAuditIndexadorVO.
     * 
     * @param idNodo   * Lista de identificadores de los nodos del arbol curricular
     * del
     *                                 que se quiere calcular la cobertura.
     */
    public void setIdNodo(java.lang.String[] idNodo) {
        this.idNodo = idNodo;
    }


    /**
     * Gets the idLicencias value for this ParametroAuditIndexadorVO.
     * 
     * @return idLicencias   * Lista de licencias que se quieren buscar.
     */
    public java.lang.String[] getIdLicencias() {
        return idLicencias;
    }


    /**
     * Sets the idLicencias value for this ParametroAuditIndexadorVO.
     * 
     * @param idLicencias   * Lista de licencias que se quieren buscar.
     */
    public void setIdLicencias(java.lang.String[] idLicencias) {
        this.idLicencias = idLicencias;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametroAuditIndexadorVO)) return false;
        ParametroAuditIndexadorVO other = (ParametroAuditIndexadorVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.idNodo==null && other.getIdNodo()==null) || 
             (this.idNodo!=null &&
              java.util.Arrays.equals(this.idNodo, other.getIdNodo()))) &&
            ((this.idLicencias==null && other.getIdLicencias()==null) || 
             (this.idLicencias!=null &&
              java.util.Arrays.equals(this.idLicencias, other.getIdLicencias())));
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
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getIdNodo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdNodo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdNodo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdLicencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdLicencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdLicencias(), i);
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
        new org.apache.axis.description.TypeDesc(ParametroAuditIndexadorVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "ParametroAuditIndexadorVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "fechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "fechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "idNodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLicencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "idLicencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "item"));
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
