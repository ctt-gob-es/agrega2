/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ResultadoBusquedaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ResultadoBusquedaVO  implements java.io.Serializable {
    private es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO[] resultadoBusqueda;

    /* Las sugerencias que puede dar un resultado fallido de busqueda
     * pueden ser multiples. */
    private java.lang.String[] sugerencias;

    /* Numero de resultados que se han obtenido de la busqueda. */
    private java.lang.Integer numeroResultados;

    /* Se trata del identificador de la busqueda que se ha realizado.
     * Se utiliza para que las subsiguientes consultas sobre el
     *                                 buscador sobre la misma busqueda se
     * hagan directamente sobre la
     *                                 cache y no se realicen de nuevo. Tambien
     * se usa para la
     *                                 paginacion de los resultados. */
    private java.lang.String idBusqueda;

    private java.lang.Integer totalResultados;

    private es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO[] tesauros;

    public ResultadoBusquedaVO() {
    }

    public ResultadoBusquedaVO(
           es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO[] resultadoBusqueda,
           java.lang.String[] sugerencias,
           java.lang.Integer numeroResultados,
           java.lang.String idBusqueda,
           java.lang.Integer totalResultados,
           es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO[] tesauros) {
           this.resultadoBusqueda = resultadoBusqueda;
           this.sugerencias = sugerencias;
           this.numeroResultados = numeroResultados;
           this.idBusqueda = idBusqueda;
           this.totalResultados = totalResultados;
           this.tesauros = tesauros;
    }


    /**
     * Gets the resultadoBusqueda value for this ResultadoBusquedaVO.
     * 
     * @return resultadoBusqueda
     */
    public es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO[] getResultadoBusqueda() {
        return resultadoBusqueda;
    }


    /**
     * Sets the resultadoBusqueda value for this ResultadoBusquedaVO.
     * 
     * @param resultadoBusqueda
     */
    public void setResultadoBusqueda(es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO[] resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }


    /**
     * Gets the sugerencias value for this ResultadoBusquedaVO.
     * 
     * @return sugerencias   * Las sugerencias que puede dar un resultado fallido de busqueda
     * pueden ser multiples.
     */
    public java.lang.String[] getSugerencias() {
        return sugerencias;
    }


    /**
     * Sets the sugerencias value for this ResultadoBusquedaVO.
     * 
     * @param sugerencias   * Las sugerencias que puede dar un resultado fallido de busqueda
     * pueden ser multiples.
     */
    public void setSugerencias(java.lang.String[] sugerencias) {
        this.sugerencias = sugerencias;
    }


    /**
     * Gets the numeroResultados value for this ResultadoBusquedaVO.
     * 
     * @return numeroResultados   * Numero de resultados que se han obtenido de la busqueda.
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ResultadoBusquedaVO.
     * 
     * @param numeroResultados   * Numero de resultados que se han obtenido de la busqueda.
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the idBusqueda value for this ResultadoBusquedaVO.
     * 
     * @return idBusqueda   * Se trata del identificador de la busqueda que se ha realizado.
     * Se utiliza para que las subsiguientes consultas sobre el
     *                                 buscador sobre la misma busqueda se
     * hagan directamente sobre la
     *                                 cache y no se realicen de nuevo. Tambien
     * se usa para la
     *                                 paginacion de los resultados.
     */
    public java.lang.String getIdBusqueda() {
        return idBusqueda;
    }


    /**
     * Sets the idBusqueda value for this ResultadoBusquedaVO.
     * 
     * @param idBusqueda   * Se trata del identificador de la busqueda que se ha realizado.
     * Se utiliza para que las subsiguientes consultas sobre el
     *                                 buscador sobre la misma busqueda se
     * hagan directamente sobre la
     *                                 cache y no se realicen de nuevo. Tambien
     * se usa para la
     *                                 paginacion de los resultados.
     */
    public void setIdBusqueda(java.lang.String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }


    /**
     * Gets the totalResultados value for this ResultadoBusquedaVO.
     * 
     * @return totalResultados
     */
    public java.lang.Integer getTotalResultados() {
        return totalResultados;
    }


    /**
     * Sets the totalResultados value for this ResultadoBusquedaVO.
     * 
     * @param totalResultados
     */
    public void setTotalResultados(java.lang.Integer totalResultados) {
        this.totalResultados = totalResultados;
    }


    /**
     * Gets the tesauros value for this ResultadoBusquedaVO.
     * 
     * @return tesauros
     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO[] getTesauros() {
        return tesauros;
    }


    /**
     * Sets the tesauros value for this ResultadoBusquedaVO.
     * 
     * @param tesauros
     */
    public void setTesauros(es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO[] tesauros) {
        this.tesauros = tesauros;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBusquedaVO)) return false;
        ResultadoBusquedaVO other = (ResultadoBusquedaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBusqueda==null && other.getResultadoBusqueda()==null) || 
             (this.resultadoBusqueda!=null &&
              java.util.Arrays.equals(this.resultadoBusqueda, other.getResultadoBusqueda()))) &&
            ((this.sugerencias==null && other.getSugerencias()==null) || 
             (this.sugerencias!=null &&
              java.util.Arrays.equals(this.sugerencias, other.getSugerencias()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.idBusqueda==null && other.getIdBusqueda()==null) || 
             (this.idBusqueda!=null &&
              this.idBusqueda.equals(other.getIdBusqueda()))) &&
            ((this.totalResultados==null && other.getTotalResultados()==null) || 
             (this.totalResultados!=null &&
              this.totalResultados.equals(other.getTotalResultados()))) &&
            ((this.tesauros==null && other.getTesauros()==null) || 
             (this.tesauros!=null &&
              java.util.Arrays.equals(this.tesauros, other.getTesauros())));
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
        if (getResultadoBusqueda() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBusqueda());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBusqueda(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSugerencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSugerencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSugerencias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getIdBusqueda() != null) {
            _hashCode += getIdBusqueda().hashCode();
        }
        if (getTotalResultados() != null) {
            _hashCode += getTotalResultados().hashCode();
        }
        if (getTesauros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTesauros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTesauros(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBusquedaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ValoresBusquedaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sugerencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "sugerencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "totalResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tesauros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tesauros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosTaxonVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
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
