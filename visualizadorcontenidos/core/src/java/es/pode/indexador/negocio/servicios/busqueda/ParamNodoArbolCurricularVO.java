/**
 * ParamNodoArbolCurricularVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * VO que alberga los parametros que acepta una busqueda por nodo
 *                         de arbol curricular.
 */
public class ParamNodoArbolCurricularVO  implements java.io.Serializable {
    private java.lang.String idiomaBusqueda;

    private java.lang.String areaCurricular;

    private java.lang.String idiomaNavegacion;

    private java.lang.Integer numeroResultados;

    public ParamNodoArbolCurricularVO() {
    }

    public ParamNodoArbolCurricularVO(
           java.lang.String idiomaBusqueda,
           java.lang.String areaCurricular,
           java.lang.String idiomaNavegacion,
           java.lang.Integer numeroResultados) {
           this.idiomaBusqueda = idiomaBusqueda;
           this.areaCurricular = areaCurricular;
           this.idiomaNavegacion = idiomaNavegacion;
           this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the idiomaBusqueda value for this ParamNodoArbolCurricularVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParamNodoArbolCurricularVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the areaCurricular value for this ParamNodoArbolCurricularVO.
     * 
     * @return areaCurricular
     */
    public java.lang.String getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this ParamNodoArbolCurricularVO.
     * 
     * @param areaCurricular
     */
    public void setAreaCurricular(java.lang.String areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the idiomaNavegacion value for this ParamNodoArbolCurricularVO.
     * 
     * @return idiomaNavegacion
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParamNodoArbolCurricularVO.
     * 
     * @param idiomaNavegacion
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the numeroResultados value for this ParamNodoArbolCurricularVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParamNodoArbolCurricularVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamNodoArbolCurricularVO)) return false;
        ParamNodoArbolCurricularVO other = (ParamNodoArbolCurricularVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda()))) &&
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              this.areaCurricular.equals(other.getAreaCurricular()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados())));
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
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        if (getAreaCurricular() != null) {
            _hashCode += getAreaCurricular().hashCode();
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamNodoArbolCurricularVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ParamNodoArbolCurricularVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
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
