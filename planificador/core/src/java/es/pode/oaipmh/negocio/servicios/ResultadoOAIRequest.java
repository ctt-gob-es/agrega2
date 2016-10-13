/**
 * ResultadoOAIRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public class ResultadoOAIRequest  implements java.io.Serializable {
    private java.lang.String errorCode;

    private es.pode.oaipmh.negocio.servicios.IdentifyVO identifyVO;

    private es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] listIdentifiers;

    private es.pode.oaipmh.negocio.servicios.ListMetadataFormatVO[] listMetadataFormat;

    private es.pode.oaipmh.negocio.servicios.SetVO[] listSets;

    private es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] listRecords;

    private es.pode.oaipmh.negocio.servicios.ResultadoRecordVO getRecord;

    /* Tipo de llamada que se realiza al protocolo */
    private java.lang.String verb;

    /* Descripción del error que se produce durante la validación
     * de la
     *                                 llamada al protocolo oai-pmh */
    private java.lang.String errorDescripcion;

    /* Valor del token de reanudacion en el caso de necesitarlo la
     * respuesta. */
    private es.pode.oaipmh.negocio.servicios.ReanudacionTokenVO reanudacionToken;

    private java.lang.Object getRecordLomes;

    private java.lang.Object[] listRecordsLomes;

    public ResultadoOAIRequest() {
    }

    public ResultadoOAIRequest(
           java.lang.String errorCode,
           es.pode.oaipmh.negocio.servicios.IdentifyVO identifyVO,
           es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] listIdentifiers,
           es.pode.oaipmh.negocio.servicios.ListMetadataFormatVO[] listMetadataFormat,
           es.pode.oaipmh.negocio.servicios.SetVO[] listSets,
           es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] listRecords,
           es.pode.oaipmh.negocio.servicios.ResultadoRecordVO getRecord,
           java.lang.String verb,
           java.lang.String errorDescripcion,
           es.pode.oaipmh.negocio.servicios.ReanudacionTokenVO reanudacionToken,
           java.lang.Object getRecordLomes,
           java.lang.Object[] listRecordsLomes) {
           this.errorCode = errorCode;
           this.identifyVO = identifyVO;
           this.listIdentifiers = listIdentifiers;
           this.listMetadataFormat = listMetadataFormat;
           this.listSets = listSets;
           this.listRecords = listRecords;
           this.getRecord = getRecord;
           this.verb = verb;
           this.errorDescripcion = errorDescripcion;
           this.reanudacionToken = reanudacionToken;
           this.getRecordLomes = getRecordLomes;
           this.listRecordsLomes = listRecordsLomes;
    }


    /**
     * Gets the errorCode value for this ResultadoOAIRequest.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ResultadoOAIRequest.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the identifyVO value for this ResultadoOAIRequest.
     * 
     * @return identifyVO
     */
    public es.pode.oaipmh.negocio.servicios.IdentifyVO getIdentifyVO() {
        return identifyVO;
    }


    /**
     * Sets the identifyVO value for this ResultadoOAIRequest.
     * 
     * @param identifyVO
     */
    public void setIdentifyVO(es.pode.oaipmh.negocio.servicios.IdentifyVO identifyVO) {
        this.identifyVO = identifyVO;
    }


    /**
     * Gets the listIdentifiers value for this ResultadoOAIRequest.
     * 
     * @return listIdentifiers
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] getListIdentifiers() {
        return listIdentifiers;
    }


    /**
     * Sets the listIdentifiers value for this ResultadoOAIRequest.
     * 
     * @param listIdentifiers
     */
    public void setListIdentifiers(es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] listIdentifiers) {
        this.listIdentifiers = listIdentifiers;
    }


    /**
     * Gets the listMetadataFormat value for this ResultadoOAIRequest.
     * 
     * @return listMetadataFormat
     */
    public es.pode.oaipmh.negocio.servicios.ListMetadataFormatVO[] getListMetadataFormat() {
        return listMetadataFormat;
    }


    /**
     * Sets the listMetadataFormat value for this ResultadoOAIRequest.
     * 
     * @param listMetadataFormat
     */
    public void setListMetadataFormat(es.pode.oaipmh.negocio.servicios.ListMetadataFormatVO[] listMetadataFormat) {
        this.listMetadataFormat = listMetadataFormat;
    }


    /**
     * Gets the listSets value for this ResultadoOAIRequest.
     * 
     * @return listSets
     */
    public es.pode.oaipmh.negocio.servicios.SetVO[] getListSets() {
        return listSets;
    }


    /**
     * Sets the listSets value for this ResultadoOAIRequest.
     * 
     * @param listSets
     */
    public void setListSets(es.pode.oaipmh.negocio.servicios.SetVO[] listSets) {
        this.listSets = listSets;
    }


    /**
     * Gets the listRecords value for this ResultadoOAIRequest.
     * 
     * @return listRecords
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] getListRecords() {
        return listRecords;
    }


    /**
     * Sets the listRecords value for this ResultadoOAIRequest.
     * 
     * @param listRecords
     */
    public void setListRecords(es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] listRecords) {
        this.listRecords = listRecords;
    }


    /**
     * Gets the getRecord value for this ResultadoOAIRequest.
     * 
     * @return getRecord
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoRecordVO getGetRecord() {
        return getRecord;
    }


    /**
     * Sets the getRecord value for this ResultadoOAIRequest.
     * 
     * @param getRecord
     */
    public void setGetRecord(es.pode.oaipmh.negocio.servicios.ResultadoRecordVO getRecord) {
        this.getRecord = getRecord;
    }


    /**
     * Gets the verb value for this ResultadoOAIRequest.
     * 
     * @return verb   * Tipo de llamada que se realiza al protocolo
     */
    public java.lang.String getVerb() {
        return verb;
    }


    /**
     * Sets the verb value for this ResultadoOAIRequest.
     * 
     * @param verb   * Tipo de llamada que se realiza al protocolo
     */
    public void setVerb(java.lang.String verb) {
        this.verb = verb;
    }


    /**
     * Gets the errorDescripcion value for this ResultadoOAIRequest.
     * 
     * @return errorDescripcion   * Descripción del error que se produce durante la validación
     * de la
     *                                 llamada al protocolo oai-pmh
     */
    public java.lang.String getErrorDescripcion() {
        return errorDescripcion;
    }


    /**
     * Sets the errorDescripcion value for this ResultadoOAIRequest.
     * 
     * @param errorDescripcion   * Descripción del error que se produce durante la validación
     * de la
     *                                 llamada al protocolo oai-pmh
     */
    public void setErrorDescripcion(java.lang.String errorDescripcion) {
        this.errorDescripcion = errorDescripcion;
    }


    /**
     * Gets the reanudacionToken value for this ResultadoOAIRequest.
     * 
     * @return reanudacionToken   * Valor del token de reanudacion en el caso de necesitarlo la
     * respuesta.
     */
    public es.pode.oaipmh.negocio.servicios.ReanudacionTokenVO getReanudacionToken() {
        return reanudacionToken;
    }


    /**
     * Sets the reanudacionToken value for this ResultadoOAIRequest.
     * 
     * @param reanudacionToken   * Valor del token de reanudacion en el caso de necesitarlo la
     * respuesta.
     */
    public void setReanudacionToken(es.pode.oaipmh.negocio.servicios.ReanudacionTokenVO reanudacionToken) {
        this.reanudacionToken = reanudacionToken;
    }


    /**
     * Gets the getRecordLomes value for this ResultadoOAIRequest.
     * 
     * @return getRecordLomes
     */
    public java.lang.Object getGetRecordLomes() {
        return getRecordLomes;
    }


    /**
     * Sets the getRecordLomes value for this ResultadoOAIRequest.
     * 
     * @param getRecordLomes
     */
    public void setGetRecordLomes(java.lang.Object getRecordLomes) {
        this.getRecordLomes = getRecordLomes;
    }


    /**
     * Gets the listRecordsLomes value for this ResultadoOAIRequest.
     * 
     * @return listRecordsLomes
     */
    public java.lang.Object[] getListRecordsLomes() {
        return listRecordsLomes;
    }


    /**
     * Sets the listRecordsLomes value for this ResultadoOAIRequest.
     * 
     * @param listRecordsLomes
     */
    public void setListRecordsLomes(java.lang.Object[] listRecordsLomes) {
        this.listRecordsLomes = listRecordsLomes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoOAIRequest)) return false;
        ResultadoOAIRequest other = (ResultadoOAIRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.identifyVO==null && other.getIdentifyVO()==null) || 
             (this.identifyVO!=null &&
              this.identifyVO.equals(other.getIdentifyVO()))) &&
            ((this.listIdentifiers==null && other.getListIdentifiers()==null) || 
             (this.listIdentifiers!=null &&
              java.util.Arrays.equals(this.listIdentifiers, other.getListIdentifiers()))) &&
            ((this.listMetadataFormat==null && other.getListMetadataFormat()==null) || 
             (this.listMetadataFormat!=null &&
              java.util.Arrays.equals(this.listMetadataFormat, other.getListMetadataFormat()))) &&
            ((this.listSets==null && other.getListSets()==null) || 
             (this.listSets!=null &&
              java.util.Arrays.equals(this.listSets, other.getListSets()))) &&
            ((this.listRecords==null && other.getListRecords()==null) || 
             (this.listRecords!=null &&
              java.util.Arrays.equals(this.listRecords, other.getListRecords()))) &&
            ((this.getRecord==null && other.getGetRecord()==null) || 
             (this.getRecord!=null &&
              this.getRecord.equals(other.getGetRecord()))) &&
            ((this.verb==null && other.getVerb()==null) || 
             (this.verb!=null &&
              this.verb.equals(other.getVerb()))) &&
            ((this.errorDescripcion==null && other.getErrorDescripcion()==null) || 
             (this.errorDescripcion!=null &&
              this.errorDescripcion.equals(other.getErrorDescripcion()))) &&
            ((this.reanudacionToken==null && other.getReanudacionToken()==null) || 
             (this.reanudacionToken!=null &&
              this.reanudacionToken.equals(other.getReanudacionToken()))) &&
            ((this.getRecordLomes==null && other.getGetRecordLomes()==null) || 
             (this.getRecordLomes!=null &&
              this.getRecordLomes.equals(other.getGetRecordLomes()))) &&
            ((this.listRecordsLomes==null && other.getListRecordsLomes()==null) || 
             (this.listRecordsLomes!=null &&
              java.util.Arrays.equals(this.listRecordsLomes, other.getListRecordsLomes())));
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
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getIdentifyVO() != null) {
            _hashCode += getIdentifyVO().hashCode();
        }
        if (getListIdentifiers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListIdentifiers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListIdentifiers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListMetadataFormat() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListMetadataFormat());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListMetadataFormat(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListSets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListSets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListSets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListRecords() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListRecords());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListRecords(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGetRecord() != null) {
            _hashCode += getGetRecord().hashCode();
        }
        if (getVerb() != null) {
            _hashCode += getVerb().hashCode();
        }
        if (getErrorDescripcion() != null) {
            _hashCode += getErrorDescripcion().hashCode();
        }
        if (getReanudacionToken() != null) {
            _hashCode += getReanudacionToken().hashCode();
        }
        if (getGetRecordLomes() != null) {
            _hashCode += getGetRecordLomes().hashCode();
        }
        if (getListRecordsLomes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListRecordsLomes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListRecordsLomes(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoOAIRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoOAIRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifyVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identifyVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "IdentifyVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listIdentifiers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listIdentifiers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoHeaderVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listMetadataFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listMetadataFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ListMetadataFormatVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listSets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listSets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "SetVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listRecords");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listRecords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoRecordVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "getRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoRecordVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "verb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "errorDescripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reanudacionToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "reanudacionToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ReanudacionTokenVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getRecordLomes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "getRecordLomes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listRecordsLomes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "listRecordsLomes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
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
