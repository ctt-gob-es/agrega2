/**
 * Change.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class Change  implements java.io.Serializable {
    private es.pode.modificador.negocio.servicio.CambioTypes type;

    private java.lang.String lomTerm;

    private java.lang.String value;

    private java.lang.Boolean regExp;

    private java.lang.String newValue;

    private java.lang.String language;

    private java.lang.Boolean replaceAll;

    private java.lang.Boolean mainLomOnly;

    private es.pode.modificador.negocio.servicio.EspecialTermTypes termType;

    private java.lang.String idLomTerm;

    private java.lang.String idTaxonomia;

    private es.pode.modificador.negocio.servicio.TransformacionTypes exportFormat;

    private java.lang.String exportPath;

    private java.lang.String purpose;

    private java.lang.String source;

    public Change() {
    }

    public Change(
           es.pode.modificador.negocio.servicio.CambioTypes type,
           java.lang.String lomTerm,
           java.lang.String value,
           java.lang.Boolean regExp,
           java.lang.String newValue,
           java.lang.String language,
           java.lang.Boolean replaceAll,
           java.lang.Boolean mainLomOnly,
           es.pode.modificador.negocio.servicio.EspecialTermTypes termType,
           java.lang.String idLomTerm,
           java.lang.String idTaxonomia,
           es.pode.modificador.negocio.servicio.TransformacionTypes exportFormat,
           java.lang.String exportPath,
           java.lang.String purpose,
           java.lang.String source) {
           this.type = type;
           this.lomTerm = lomTerm;
           this.value = value;
           this.regExp = regExp;
           this.newValue = newValue;
           this.language = language;
           this.replaceAll = replaceAll;
           this.mainLomOnly = mainLomOnly;
           this.termType = termType;
           this.idLomTerm = idLomTerm;
           this.idTaxonomia = idTaxonomia;
           this.exportFormat = exportFormat;
           this.exportPath = exportPath;
           this.purpose = purpose;
           this.source = source;
    }


    /**
     * Gets the type value for this Change.
     * 
     * @return type
     */
    public es.pode.modificador.negocio.servicio.CambioTypes getType() {
        return type;
    }


    /**
     * Sets the type value for this Change.
     * 
     * @param type
     */
    public void setType(es.pode.modificador.negocio.servicio.CambioTypes type) {
        this.type = type;
    }


    /**
     * Gets the lomTerm value for this Change.
     * 
     * @return lomTerm
     */
    public java.lang.String getLomTerm() {
        return lomTerm;
    }


    /**
     * Sets the lomTerm value for this Change.
     * 
     * @param lomTerm
     */
    public void setLomTerm(java.lang.String lomTerm) {
        this.lomTerm = lomTerm;
    }


    /**
     * Gets the value value for this Change.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this Change.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the regExp value for this Change.
     * 
     * @return regExp
     */
    public java.lang.Boolean getRegExp() {
        return regExp;
    }


    /**
     * Sets the regExp value for this Change.
     * 
     * @param regExp
     */
    public void setRegExp(java.lang.Boolean regExp) {
        this.regExp = regExp;
    }


    /**
     * Gets the newValue value for this Change.
     * 
     * @return newValue
     */
    public java.lang.String getNewValue() {
        return newValue;
    }


    /**
     * Sets the newValue value for this Change.
     * 
     * @param newValue
     */
    public void setNewValue(java.lang.String newValue) {
        this.newValue = newValue;
    }


    /**
     * Gets the language value for this Change.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this Change.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the replaceAll value for this Change.
     * 
     * @return replaceAll
     */
    public java.lang.Boolean getReplaceAll() {
        return replaceAll;
    }


    /**
     * Sets the replaceAll value for this Change.
     * 
     * @param replaceAll
     */
    public void setReplaceAll(java.lang.Boolean replaceAll) {
        this.replaceAll = replaceAll;
    }


    /**
     * Gets the mainLomOnly value for this Change.
     * 
     * @return mainLomOnly
     */
    public java.lang.Boolean getMainLomOnly() {
        return mainLomOnly;
    }


    /**
     * Sets the mainLomOnly value for this Change.
     * 
     * @param mainLomOnly
     */
    public void setMainLomOnly(java.lang.Boolean mainLomOnly) {
        this.mainLomOnly = mainLomOnly;
    }


    /**
     * Gets the termType value for this Change.
     * 
     * @return termType
     */
    public es.pode.modificador.negocio.servicio.EspecialTermTypes getTermType() {
        return termType;
    }


    /**
     * Sets the termType value for this Change.
     * 
     * @param termType
     */
    public void setTermType(es.pode.modificador.negocio.servicio.EspecialTermTypes termType) {
        this.termType = termType;
    }


    /**
     * Gets the idLomTerm value for this Change.
     * 
     * @return idLomTerm
     */
    public java.lang.String getIdLomTerm() {
        return idLomTerm;
    }


    /**
     * Sets the idLomTerm value for this Change.
     * 
     * @param idLomTerm
     */
    public void setIdLomTerm(java.lang.String idLomTerm) {
        this.idLomTerm = idLomTerm;
    }


    /**
     * Gets the idTaxonomia value for this Change.
     * 
     * @return idTaxonomia
     */
    public java.lang.String getIdTaxonomia() {
        return idTaxonomia;
    }


    /**
     * Sets the idTaxonomia value for this Change.
     * 
     * @param idTaxonomia
     */
    public void setIdTaxonomia(java.lang.String idTaxonomia) {
        this.idTaxonomia = idTaxonomia;
    }


    /**
     * Gets the exportFormat value for this Change.
     * 
     * @return exportFormat
     */
    public es.pode.modificador.negocio.servicio.TransformacionTypes getExportFormat() {
        return exportFormat;
    }


    /**
     * Sets the exportFormat value for this Change.
     * 
     * @param exportFormat
     */
    public void setExportFormat(es.pode.modificador.negocio.servicio.TransformacionTypes exportFormat) {
        this.exportFormat = exportFormat;
    }


    /**
     * Gets the exportPath value for this Change.
     * 
     * @return exportPath
     */
    public java.lang.String getExportPath() {
        return exportPath;
    }


    /**
     * Sets the exportPath value for this Change.
     * 
     * @param exportPath
     */
    public void setExportPath(java.lang.String exportPath) {
        this.exportPath = exportPath;
    }


    /**
     * Gets the purpose value for this Change.
     * 
     * @return purpose
     */
    public java.lang.String getPurpose() {
        return purpose;
    }


    /**
     * Sets the purpose value for this Change.
     * 
     * @param purpose
     */
    public void setPurpose(java.lang.String purpose) {
        this.purpose = purpose;
    }


    /**
     * Gets the source value for this Change.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this Change.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Change)) return false;
        Change other = (Change) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.lomTerm==null && other.getLomTerm()==null) || 
             (this.lomTerm!=null &&
              this.lomTerm.equals(other.getLomTerm()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.regExp==null && other.getRegExp()==null) || 
             (this.regExp!=null &&
              this.regExp.equals(other.getRegExp()))) &&
            ((this.newValue==null && other.getNewValue()==null) || 
             (this.newValue!=null &&
              this.newValue.equals(other.getNewValue()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.replaceAll==null && other.getReplaceAll()==null) || 
             (this.replaceAll!=null &&
              this.replaceAll.equals(other.getReplaceAll()))) &&
            ((this.mainLomOnly==null && other.getMainLomOnly()==null) || 
             (this.mainLomOnly!=null &&
              this.mainLomOnly.equals(other.getMainLomOnly()))) &&
            ((this.termType==null && other.getTermType()==null) || 
             (this.termType!=null &&
              this.termType.equals(other.getTermType()))) &&
            ((this.idLomTerm==null && other.getIdLomTerm()==null) || 
             (this.idLomTerm!=null &&
              this.idLomTerm.equals(other.getIdLomTerm()))) &&
            ((this.idTaxonomia==null && other.getIdTaxonomia()==null) || 
             (this.idTaxonomia!=null &&
              this.idTaxonomia.equals(other.getIdTaxonomia()))) &&
            ((this.exportFormat==null && other.getExportFormat()==null) || 
             (this.exportFormat!=null &&
              this.exportFormat.equals(other.getExportFormat()))) &&
            ((this.exportPath==null && other.getExportPath()==null) || 
             (this.exportPath!=null &&
              this.exportPath.equals(other.getExportPath()))) &&
            ((this.purpose==null && other.getPurpose()==null) || 
             (this.purpose!=null &&
              this.purpose.equals(other.getPurpose()))) &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getLomTerm() != null) {
            _hashCode += getLomTerm().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getRegExp() != null) {
            _hashCode += getRegExp().hashCode();
        }
        if (getNewValue() != null) {
            _hashCode += getNewValue().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getReplaceAll() != null) {
            _hashCode += getReplaceAll().hashCode();
        }
        if (getMainLomOnly() != null) {
            _hashCode += getMainLomOnly().hashCode();
        }
        if (getTermType() != null) {
            _hashCode += getTermType().hashCode();
        }
        if (getIdLomTerm() != null) {
            _hashCode += getIdLomTerm().hashCode();
        }
        if (getIdTaxonomia() != null) {
            _hashCode += getIdTaxonomia().hashCode();
        }
        if (getExportFormat() != null) {
            _hashCode += getExportFormat().hashCode();
        }
        if (getExportPath() != null) {
            _hashCode += getExportPath().hashCode();
        }
        if (getPurpose() != null) {
            _hashCode += getPurpose().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Change.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "Change"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "CambioTypes"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lomTerm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "lomTerm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regExp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "regExp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "newValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replaceAll");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "replaceAll"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mainLomOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "mainLomOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "termType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "EspecialTermTypes"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idLomTerm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idLomTerm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTaxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idTaxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "exportFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TransformacionTypes"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "exportPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "purpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "source"));
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
