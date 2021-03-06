/**
 * QuerySimpleVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Parametros de una query simple.
 */
public class QuerySimpleVO  implements java.io.Serializable {
    /* Texto de la query */
    private java.lang.String query;

    /* Lenguaje de busqueda (SQI o Lucene) */
    private java.lang.String lenguajeQuery;

    /* idioma de bÃºsqueda del ODE */
    private java.lang.String idioma;

    private java.lang.Integer numResultados;

    public QuerySimpleVO() {
    }

    public QuerySimpleVO(
           java.lang.String query,
           java.lang.String lenguajeQuery,
           java.lang.String idioma,
           java.lang.Integer numResultados) {
           this.query = query;
           this.lenguajeQuery = lenguajeQuery;
           this.idioma = idioma;
           this.numResultados = numResultados;
    }


    /**
     * Gets the query value for this QuerySimpleVO.
     * 
     * @return query   * Texto de la query
     */
    public java.lang.String getQuery() {
        return query;
    }


    /**
     * Sets the query value for this QuerySimpleVO.
     * 
     * @param query   * Texto de la query
     */
    public void setQuery(java.lang.String query) {
        this.query = query;
    }


    /**
     * Gets the lenguajeQuery value for this QuerySimpleVO.
     * 
     * @return lenguajeQuery   * Lenguaje de busqueda (SQI o Lucene)
     */
    public java.lang.String getLenguajeQuery() {
        return lenguajeQuery;
    }


    /**
     * Sets the lenguajeQuery value for this QuerySimpleVO.
     * 
     * @param lenguajeQuery   * Lenguaje de busqueda (SQI o Lucene)
     */
    public void setLenguajeQuery(java.lang.String lenguajeQuery) {
        this.lenguajeQuery = lenguajeQuery;
    }


    /**
     * Gets the idioma value for this QuerySimpleVO.
     * 
     * @return idioma   * idioma de bÃºsqueda del ODE
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this QuerySimpleVO.
     * 
     * @param idioma   * idioma de bÃºsqueda del ODE
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the numResultados value for this QuerySimpleVO.
     * 
     * @return numResultados
     */
    public java.lang.Integer getNumResultados() {
        return numResultados;
    }


    /**
     * Sets the numResultados value for this QuerySimpleVO.
     * 
     * @param numResultados
     */
    public void setNumResultados(java.lang.Integer numResultados) {
        this.numResultados = numResultados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QuerySimpleVO)) return false;
        QuerySimpleVO other = (QuerySimpleVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.lenguajeQuery==null && other.getLenguajeQuery()==null) || 
             (this.lenguajeQuery!=null &&
              this.lenguajeQuery.equals(other.getLenguajeQuery()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.numResultados==null && other.getNumResultados()==null) || 
             (this.numResultados!=null &&
              this.numResultados.equals(other.getNumResultados())));
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
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getLenguajeQuery() != null) {
            _hashCode += getLenguajeQuery().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getNumResultados() != null) {
            _hashCode += getNumResultados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QuerySimpleVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "QuerySimpleVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lenguajeQuery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "lenguajeQuery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "numResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
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
