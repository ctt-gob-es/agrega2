/**
 * DocumentosVO30.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.federada.servicios;


/**
 * Esta clase representa los resultados de una busqueda.
 */
public class DocumentosVO30  implements java.io.Serializable {
    private es.pode.buscar.negocio.federada.servicios.DocVO30[] resultados;

    /* Se trata de las sugerencias de busqueda en el caso de que no
     * haya resultados de busqueda para la busqueda invocada. Puede
     *                                 haber varias sugerencias. */
    private java.lang.String[] sugerencias;

    private java.lang.Integer numeroResultados;

    private int totalResultados;

    private java.lang.Integer numDocumentosIndice;

    private es.pode.buscar.negocio.federada.servicios.TaxonVO30[] tesauros;

    private java.lang.String versionComunidadSolicitada;

    public DocumentosVO30() {
    }

    public DocumentosVO30(
           es.pode.buscar.negocio.federada.servicios.DocVO30[] resultados,
           java.lang.String[] sugerencias,
           java.lang.Integer numeroResultados,
           int totalResultados,
           java.lang.Integer numDocumentosIndice,
           es.pode.buscar.negocio.federada.servicios.TaxonVO30[] tesauros,
           java.lang.String versionComunidadSolicitada) {
           this.resultados = resultados;
           this.sugerencias = sugerencias;
           this.numeroResultados = numeroResultados;
           this.totalResultados = totalResultados;
           this.numDocumentosIndice = numDocumentosIndice;
           this.tesauros = tesauros;
           this.versionComunidadSolicitada = versionComunidadSolicitada;
    }


    /**
     * Gets the resultados value for this DocumentosVO30.
     * 
     * @return resultados
     */
    public es.pode.buscar.negocio.federada.servicios.DocVO30[] getResultados() {
        return resultados;
    }


    /**
     * Sets the resultados value for this DocumentosVO30.
     * 
     * @param resultados
     */
    public void setResultados(es.pode.buscar.negocio.federada.servicios.DocVO30[] resultados) {
        this.resultados = resultados;
    }


    /**
     * Gets the sugerencias value for this DocumentosVO30.
     * 
     * @return sugerencias   * Se trata de las sugerencias de busqueda en el caso de que no
     * haya resultados de busqueda para la busqueda invocada. Puede
     *                                 haber varias sugerencias.
     */
    public java.lang.String[] getSugerencias() {
        return sugerencias;
    }


    /**
     * Sets the sugerencias value for this DocumentosVO30.
     * 
     * @param sugerencias   * Se trata de las sugerencias de busqueda en el caso de que no
     * haya resultados de busqueda para la busqueda invocada. Puede
     *                                 haber varias sugerencias.
     */
    public void setSugerencias(java.lang.String[] sugerencias) {
        this.sugerencias = sugerencias;
    }


    /**
     * Gets the numeroResultados value for this DocumentosVO30.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this DocumentosVO30.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the totalResultados value for this DocumentosVO30.
     * 
     * @return totalResultados
     */
    public int getTotalResultados() {
        return totalResultados;
    }


    /**
     * Sets the totalResultados value for this DocumentosVO30.
     * 
     * @param totalResultados
     */
    public void setTotalResultados(int totalResultados) {
        this.totalResultados = totalResultados;
    }


    /**
     * Gets the numDocumentosIndice value for this DocumentosVO30.
     * 
     * @return numDocumentosIndice
     */
    public java.lang.Integer getNumDocumentosIndice() {
        return numDocumentosIndice;
    }


    /**
     * Sets the numDocumentosIndice value for this DocumentosVO30.
     * 
     * @param numDocumentosIndice
     */
    public void setNumDocumentosIndice(java.lang.Integer numDocumentosIndice) {
        this.numDocumentosIndice = numDocumentosIndice;
    }


    /**
     * Gets the tesauros value for this DocumentosVO30.
     * 
     * @return tesauros
     */
    public es.pode.buscar.negocio.federada.servicios.TaxonVO30[] getTesauros() {
        return tesauros;
    }


    /**
     * Sets the tesauros value for this DocumentosVO30.
     * 
     * @param tesauros
     */
    public void setTesauros(es.pode.buscar.negocio.federada.servicios.TaxonVO30[] tesauros) {
        this.tesauros = tesauros;
    }


    /**
     * Gets the versionComunidadSolicitada value for this DocumentosVO30.
     * 
     * @return versionComunidadSolicitada
     */
    public java.lang.String getVersionComunidadSolicitada() {
        return versionComunidadSolicitada;
    }


    /**
     * Sets the versionComunidadSolicitada value for this DocumentosVO30.
     * 
     * @param versionComunidadSolicitada
     */
    public void setVersionComunidadSolicitada(java.lang.String versionComunidadSolicitada) {
        this.versionComunidadSolicitada = versionComunidadSolicitada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentosVO30)) return false;
        DocumentosVO30 other = (DocumentosVO30) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultados==null && other.getResultados()==null) || 
             (this.resultados!=null &&
              java.util.Arrays.equals(this.resultados, other.getResultados()))) &&
            ((this.sugerencias==null && other.getSugerencias()==null) || 
             (this.sugerencias!=null &&
              java.util.Arrays.equals(this.sugerencias, other.getSugerencias()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            this.totalResultados == other.getTotalResultados() &&
            ((this.numDocumentosIndice==null && other.getNumDocumentosIndice()==null) || 
             (this.numDocumentosIndice!=null &&
              this.numDocumentosIndice.equals(other.getNumDocumentosIndice()))) &&
            ((this.tesauros==null && other.getTesauros()==null) || 
             (this.tesauros!=null &&
              java.util.Arrays.equals(this.tesauros, other.getTesauros()))) &&
            ((this.versionComunidadSolicitada==null && other.getVersionComunidadSolicitada()==null) || 
             (this.versionComunidadSolicitada!=null &&
              this.versionComunidadSolicitada.equals(other.getVersionComunidadSolicitada())));
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
        if (getResultados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultados(), i);
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
        _hashCode += getTotalResultados();
        if (getNumDocumentosIndice() != null) {
            _hashCode += getNumDocumentosIndice().hashCode();
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
        if (getVersionComunidadSolicitada() != null) {
            _hashCode += getVersionComunidadSolicitada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentosVO30.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "DocumentosVO30"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "resultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "DocVO30"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sugerencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "sugerencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "totalResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numDocumentosIndice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "numDocumentosIndice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tesauros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "tesauros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "TaxonVO30"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionComunidadSolicitada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "versionComunidadSolicitada"));
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
