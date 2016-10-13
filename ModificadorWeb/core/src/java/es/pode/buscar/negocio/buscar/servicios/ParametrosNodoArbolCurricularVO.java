/**
 * ParametrosNodoArbolCurricularVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * Parámetros para la solicitud de documentos existentes en el
 *                         índice para un área curricular.
 */
public class ParametrosNodoArbolCurricularVO  implements java.io.Serializable {
    private java.lang.String idiomaBusqueda;

    private java.lang.String areaCurricular;

    private java.lang.Integer numeroResultados;

    private java.lang.String idiomaNavegacion;

    private java.lang.Integer origenPagina;

    private java.lang.Integer resultadoPagina;

    private java.lang.String idBusqueda;

    private java.lang.String tipoBusqueda;

    private java.lang.String comunidadPeticion;

    public ParametrosNodoArbolCurricularVO() {
    }

    public ParametrosNodoArbolCurricularVO(
           java.lang.String idiomaBusqueda,
           java.lang.String areaCurricular,
           java.lang.Integer numeroResultados,
           java.lang.String idiomaNavegacion,
           java.lang.Integer origenPagina,
           java.lang.Integer resultadoPagina,
           java.lang.String idBusqueda,
           java.lang.String tipoBusqueda,
           java.lang.String comunidadPeticion) {
           this.idiomaBusqueda = idiomaBusqueda;
           this.areaCurricular = areaCurricular;
           this.numeroResultados = numeroResultados;
           this.idiomaNavegacion = idiomaNavegacion;
           this.origenPagina = origenPagina;
           this.resultadoPagina = resultadoPagina;
           this.idBusqueda = idBusqueda;
           this.tipoBusqueda = tipoBusqueda;
           this.comunidadPeticion = comunidadPeticion;
    }


    /**
     * Gets the idiomaBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the areaCurricular value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return areaCurricular
     */
    public java.lang.String getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param areaCurricular
     */
    public void setAreaCurricular(java.lang.String areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the numeroResultados value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the idiomaNavegacion value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return idiomaNavegacion
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param idiomaNavegacion
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the origenPagina value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return origenPagina
     */
    public java.lang.Integer getOrigenPagina() {
        return origenPagina;
    }


    /**
     * Sets the origenPagina value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param origenPagina
     */
    public void setOrigenPagina(java.lang.Integer origenPagina) {
        this.origenPagina = origenPagina;
    }


    /**
     * Gets the resultadoPagina value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return resultadoPagina
     */
    public java.lang.Integer getResultadoPagina() {
        return resultadoPagina;
    }


    /**
     * Sets the resultadoPagina value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param resultadoPagina
     */
    public void setResultadoPagina(java.lang.Integer resultadoPagina) {
        this.resultadoPagina = resultadoPagina;
    }


    /**
     * Gets the idBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return idBusqueda
     */
    public java.lang.String getIdBusqueda() {
        return idBusqueda;
    }


    /**
     * Sets the idBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param idBusqueda
     */
    public void setIdBusqueda(java.lang.String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }


    /**
     * Gets the tipoBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return tipoBusqueda
     */
    public java.lang.String getTipoBusqueda() {
        return tipoBusqueda;
    }


    /**
     * Sets the tipoBusqueda value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param tipoBusqueda
     */
    public void setTipoBusqueda(java.lang.String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }


    /**
     * Gets the comunidadPeticion value for this ParametrosNodoArbolCurricularVO.
     * 
     * @return comunidadPeticion
     */
    public java.lang.String getComunidadPeticion() {
        return comunidadPeticion;
    }


    /**
     * Sets the comunidadPeticion value for this ParametrosNodoArbolCurricularVO.
     * 
     * @param comunidadPeticion
     */
    public void setComunidadPeticion(java.lang.String comunidadPeticion) {
        this.comunidadPeticion = comunidadPeticion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosNodoArbolCurricularVO)) return false;
        ParametrosNodoArbolCurricularVO other = (ParametrosNodoArbolCurricularVO) obj;
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
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.origenPagina==null && other.getOrigenPagina()==null) || 
             (this.origenPagina!=null &&
              this.origenPagina.equals(other.getOrigenPagina()))) &&
            ((this.resultadoPagina==null && other.getResultadoPagina()==null) || 
             (this.resultadoPagina!=null &&
              this.resultadoPagina.equals(other.getResultadoPagina()))) &&
            ((this.idBusqueda==null && other.getIdBusqueda()==null) || 
             (this.idBusqueda!=null &&
              this.idBusqueda.equals(other.getIdBusqueda()))) &&
            ((this.tipoBusqueda==null && other.getTipoBusqueda()==null) || 
             (this.tipoBusqueda!=null &&
              this.tipoBusqueda.equals(other.getTipoBusqueda()))) &&
            ((this.comunidadPeticion==null && other.getComunidadPeticion()==null) || 
             (this.comunidadPeticion!=null &&
              this.comunidadPeticion.equals(other.getComunidadPeticion())));
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
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getOrigenPagina() != null) {
            _hashCode += getOrigenPagina().hashCode();
        }
        if (getResultadoPagina() != null) {
            _hashCode += getResultadoPagina().hashCode();
        }
        if (getIdBusqueda() != null) {
            _hashCode += getIdBusqueda().hashCode();
        }
        if (getTipoBusqueda() != null) {
            _hashCode += getTipoBusqueda().hashCode();
        }
        if (getComunidadPeticion() != null) {
            _hashCode += getComunidadPeticion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosNodoArbolCurricularVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosNodoArbolCurricularVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "origenPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadoPagina"));
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
        elemField.setFieldName("tipoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tipoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadPeticion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadPeticion"));
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
