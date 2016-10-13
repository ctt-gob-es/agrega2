/**
 * ParametrosBusquedaSQIVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * Esta clase almacena todos los parametros de busqueda que se
 *                         pueden configurar en una busqueda desde el
 * modulo de DRI-SQI.
 */
public class ParametrosBusquedaSQIVO  implements java.io.Serializable {
    /* Se trata de la consulta que se realiza desde el modulo de
     *                                 DRI-SQI. */
    private java.lang.String query;

    /* Limitacion del numero maximo de resultados que se pueden recoger
     * para una query.
     *                                 El valor por defecto es 100.
     *                                 Los valores permitidos van desde 0
     * o mas.
     *                                 Si el valor es 0, indica que no esta
     * limitado el numero de
     *                                 resultados devueltos por una query. */
    private java.lang.Integer maxResultados;

    /* Se trata del indice del primer resultado en devuelto en el
     * que
     *                                 se esta interesado. */
    private java.lang.Integer startResult;

    /* Numero de resultados devueltos en un result set como resultado
     * de la ejecucion de una query.
     *                                 Puede ocurrir que la ejecucion de
     * una query devuelva mas
     *                                 resultados de los que necesite, con
     * este parametro configuramos
     *                                 la cantidad de resultados que vamos
     * a devolver por cada result
     *                                 set.
     *                                 El valor por defecto son 25.
     *                                 Los valores validos van desde 0 o
     * mas.
     *                                 Si el valor es 0, una consulta esta
     * pidiendo todos los
     *                                 resultados devueltos. */
    private java.lang.Integer resultadosDevueltos;

    /* Especifica el vocabulario que se esta utilizando para realizar
     * la consulta. */
    private java.lang.String vocabularioConsulta;

    /* Idioma en el que se esta realizando la busqueda. */
    private java.lang.String idiomaBusqueda;

    public ParametrosBusquedaSQIVO() {
    }

    public ParametrosBusquedaSQIVO(
           java.lang.String query,
           java.lang.Integer maxResultados,
           java.lang.Integer startResult,
           java.lang.Integer resultadosDevueltos,
           java.lang.String vocabularioConsulta,
           java.lang.String idiomaBusqueda) {
           this.query = query;
           this.maxResultados = maxResultados;
           this.startResult = startResult;
           this.resultadosDevueltos = resultadosDevueltos;
           this.vocabularioConsulta = vocabularioConsulta;
           this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the query value for this ParametrosBusquedaSQIVO.
     * 
     * @return query   * Se trata de la consulta que se realiza desde el modulo de
     *                                 DRI-SQI.
     */
    public java.lang.String getQuery() {
        return query;
    }


    /**
     * Sets the query value for this ParametrosBusquedaSQIVO.
     * 
     * @param query   * Se trata de la consulta que se realiza desde el modulo de
     *                                 DRI-SQI.
     */
    public void setQuery(java.lang.String query) {
        this.query = query;
    }


    /**
     * Gets the maxResultados value for this ParametrosBusquedaSQIVO.
     * 
     * @return maxResultados   * Limitacion del numero maximo de resultados que se pueden recoger
     * para una query.
     *                                 El valor por defecto es 100.
     *                                 Los valores permitidos van desde 0
     * o mas.
     *                                 Si el valor es 0, indica que no esta
     * limitado el numero de
     *                                 resultados devueltos por una query.
     */
    public java.lang.Integer getMaxResultados() {
        return maxResultados;
    }


    /**
     * Sets the maxResultados value for this ParametrosBusquedaSQIVO.
     * 
     * @param maxResultados   * Limitacion del numero maximo de resultados que se pueden recoger
     * para una query.
     *                                 El valor por defecto es 100.
     *                                 Los valores permitidos van desde 0
     * o mas.
     *                                 Si el valor es 0, indica que no esta
     * limitado el numero de
     *                                 resultados devueltos por una query.
     */
    public void setMaxResultados(java.lang.Integer maxResultados) {
        this.maxResultados = maxResultados;
    }


    /**
     * Gets the startResult value for this ParametrosBusquedaSQIVO.
     * 
     * @return startResult   * Se trata del indice del primer resultado en devuelto en el
     * que
     *                                 se esta interesado.
     */
    public java.lang.Integer getStartResult() {
        return startResult;
    }


    /**
     * Sets the startResult value for this ParametrosBusquedaSQIVO.
     * 
     * @param startResult   * Se trata del indice del primer resultado en devuelto en el
     * que
     *                                 se esta interesado.
     */
    public void setStartResult(java.lang.Integer startResult) {
        this.startResult = startResult;
    }


    /**
     * Gets the resultadosDevueltos value for this ParametrosBusquedaSQIVO.
     * 
     * @return resultadosDevueltos   * Numero de resultados devueltos en un result set como resultado
     * de la ejecucion de una query.
     *                                 Puede ocurrir que la ejecucion de
     * una query devuelva mas
     *                                 resultados de los que necesite, con
     * este parametro configuramos
     *                                 la cantidad de resultados que vamos
     * a devolver por cada result
     *                                 set.
     *                                 El valor por defecto son 25.
     *                                 Los valores validos van desde 0 o
     * mas.
     *                                 Si el valor es 0, una consulta esta
     * pidiendo todos los
     *                                 resultados devueltos.
     */
    public java.lang.Integer getResultadosDevueltos() {
        return resultadosDevueltos;
    }


    /**
     * Sets the resultadosDevueltos value for this ParametrosBusquedaSQIVO.
     * 
     * @param resultadosDevueltos   * Numero de resultados devueltos en un result set como resultado
     * de la ejecucion de una query.
     *                                 Puede ocurrir que la ejecucion de
     * una query devuelva mas
     *                                 resultados de los que necesite, con
     * este parametro configuramos
     *                                 la cantidad de resultados que vamos
     * a devolver por cada result
     *                                 set.
     *                                 El valor por defecto son 25.
     *                                 Los valores validos van desde 0 o
     * mas.
     *                                 Si el valor es 0, una consulta esta
     * pidiendo todos los
     *                                 resultados devueltos.
     */
    public void setResultadosDevueltos(java.lang.Integer resultadosDevueltos) {
        this.resultadosDevueltos = resultadosDevueltos;
    }


    /**
     * Gets the vocabularioConsulta value for this ParametrosBusquedaSQIVO.
     * 
     * @return vocabularioConsulta   * Especifica el vocabulario que se esta utilizando para realizar
     * la consulta.
     */
    public java.lang.String getVocabularioConsulta() {
        return vocabularioConsulta;
    }


    /**
     * Sets the vocabularioConsulta value for this ParametrosBusquedaSQIVO.
     * 
     * @param vocabularioConsulta   * Especifica el vocabulario que se esta utilizando para realizar
     * la consulta.
     */
    public void setVocabularioConsulta(java.lang.String vocabularioConsulta) {
        this.vocabularioConsulta = vocabularioConsulta;
    }


    /**
     * Gets the idiomaBusqueda value for this ParametrosBusquedaSQIVO.
     * 
     * @return idiomaBusqueda   * Idioma en el que se esta realizando la busqueda.
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParametrosBusquedaSQIVO.
     * 
     * @param idiomaBusqueda   * Idioma en el que se esta realizando la busqueda.
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBusquedaSQIVO)) return false;
        ParametrosBusquedaSQIVO other = (ParametrosBusquedaSQIVO) obj;
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
            ((this.maxResultados==null && other.getMaxResultados()==null) || 
             (this.maxResultados!=null &&
              this.maxResultados.equals(other.getMaxResultados()))) &&
            ((this.startResult==null && other.getStartResult()==null) || 
             (this.startResult!=null &&
              this.startResult.equals(other.getStartResult()))) &&
            ((this.resultadosDevueltos==null && other.getResultadosDevueltos()==null) || 
             (this.resultadosDevueltos!=null &&
              this.resultadosDevueltos.equals(other.getResultadosDevueltos()))) &&
            ((this.vocabularioConsulta==null && other.getVocabularioConsulta()==null) || 
             (this.vocabularioConsulta!=null &&
              this.vocabularioConsulta.equals(other.getVocabularioConsulta()))) &&
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda())));
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
        if (getMaxResultados() != null) {
            _hashCode += getMaxResultados().hashCode();
        }
        if (getStartResult() != null) {
            _hashCode += getStartResult().hashCode();
        }
        if (getResultadosDevueltos() != null) {
            _hashCode += getResultadosDevueltos().hashCode();
        }
        if (getVocabularioConsulta() != null) {
            _hashCode += getVocabularioConsulta().hashCode();
        }
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBusquedaSQIVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaSQIVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "maxResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "startResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosDevueltos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadosDevueltos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabularioConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "vocabularioConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
