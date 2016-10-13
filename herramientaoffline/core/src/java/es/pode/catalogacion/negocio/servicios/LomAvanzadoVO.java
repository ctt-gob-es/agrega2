/**
 * LomAvanzadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class LomAvanzadoVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.AvGeneralVO general;

    private es.pode.catalogacion.negocio.servicios.AvEducationalVO[] educational;

    private es.pode.catalogacion.negocio.servicios.AvLifeCycleVO lifeCycle;

    private es.pode.catalogacion.negocio.servicios.AvMetametadataVO metaMetadata;

    private es.pode.catalogacion.negocio.servicios.AvTechnicalVO technical;

    private es.pode.catalogacion.negocio.servicios.AvRightsVO derechos;

    private es.pode.catalogacion.negocio.servicios.AvRelationVO[] relaciones;

    private es.pode.catalogacion.negocio.servicios.AvAnnotationVO[] anotaciones;

    private es.pode.catalogacion.negocio.servicios.AvClassificationVO[] clasificaciones;

    public LomAvanzadoVO() {
    }

    public LomAvanzadoVO(
           es.pode.catalogacion.negocio.servicios.AvGeneralVO general,
           es.pode.catalogacion.negocio.servicios.AvEducationalVO[] educational,
           es.pode.catalogacion.negocio.servicios.AvLifeCycleVO lifeCycle,
           es.pode.catalogacion.negocio.servicios.AvMetametadataVO metaMetadata,
           es.pode.catalogacion.negocio.servicios.AvTechnicalVO technical,
           es.pode.catalogacion.negocio.servicios.AvRightsVO derechos,
           es.pode.catalogacion.negocio.servicios.AvRelationVO[] relaciones,
           es.pode.catalogacion.negocio.servicios.AvAnnotationVO[] anotaciones,
           es.pode.catalogacion.negocio.servicios.AvClassificationVO[] clasificaciones) {
           this.general = general;
           this.educational = educational;
           this.lifeCycle = lifeCycle;
           this.metaMetadata = metaMetadata;
           this.technical = technical;
           this.derechos = derechos;
           this.relaciones = relaciones;
           this.anotaciones = anotaciones;
           this.clasificaciones = clasificaciones;
    }


    /**
     * Gets the general value for this LomAvanzadoVO.
     * 
     * @return general
     */
    public es.pode.catalogacion.negocio.servicios.AvGeneralVO getGeneral() {
        return general;
    }


    /**
     * Sets the general value for this LomAvanzadoVO.
     * 
     * @param general
     */
    public void setGeneral(es.pode.catalogacion.negocio.servicios.AvGeneralVO general) {
        this.general = general;
    }


    /**
     * Gets the educational value for this LomAvanzadoVO.
     * 
     * @return educational
     */
    public es.pode.catalogacion.negocio.servicios.AvEducationalVO[] getEducational() {
        return educational;
    }


    /**
     * Sets the educational value for this LomAvanzadoVO.
     * 
     * @param educational
     */
    public void setEducational(es.pode.catalogacion.negocio.servicios.AvEducationalVO[] educational) {
        this.educational = educational;
    }


    /**
     * Gets the lifeCycle value for this LomAvanzadoVO.
     * 
     * @return lifeCycle
     */
    public es.pode.catalogacion.negocio.servicios.AvLifeCycleVO getLifeCycle() {
        return lifeCycle;
    }


    /**
     * Sets the lifeCycle value for this LomAvanzadoVO.
     * 
     * @param lifeCycle
     */
    public void setLifeCycle(es.pode.catalogacion.negocio.servicios.AvLifeCycleVO lifeCycle) {
        this.lifeCycle = lifeCycle;
    }


    /**
     * Gets the metaMetadata value for this LomAvanzadoVO.
     * 
     * @return metaMetadata
     */
    public es.pode.catalogacion.negocio.servicios.AvMetametadataVO getMetaMetadata() {
        return metaMetadata;
    }


    /**
     * Sets the metaMetadata value for this LomAvanzadoVO.
     * 
     * @param metaMetadata
     */
    public void setMetaMetadata(es.pode.catalogacion.negocio.servicios.AvMetametadataVO metaMetadata) {
        this.metaMetadata = metaMetadata;
    }


    /**
     * Gets the technical value for this LomAvanzadoVO.
     * 
     * @return technical
     */
    public es.pode.catalogacion.negocio.servicios.AvTechnicalVO getTechnical() {
        return technical;
    }


    /**
     * Sets the technical value for this LomAvanzadoVO.
     * 
     * @param technical
     */
    public void setTechnical(es.pode.catalogacion.negocio.servicios.AvTechnicalVO technical) {
        this.technical = technical;
    }


    /**
     * Gets the derechos value for this LomAvanzadoVO.
     * 
     * @return derechos
     */
    public es.pode.catalogacion.negocio.servicios.AvRightsVO getDerechos() {
        return derechos;
    }


    /**
     * Sets the derechos value for this LomAvanzadoVO.
     * 
     * @param derechos
     */
    public void setDerechos(es.pode.catalogacion.negocio.servicios.AvRightsVO derechos) {
        this.derechos = derechos;
    }


    /**
     * Gets the relaciones value for this LomAvanzadoVO.
     * 
     * @return relaciones
     */
    public es.pode.catalogacion.negocio.servicios.AvRelationVO[] getRelaciones() {
        return relaciones;
    }


    /**
     * Sets the relaciones value for this LomAvanzadoVO.
     * 
     * @param relaciones
     */
    public void setRelaciones(es.pode.catalogacion.negocio.servicios.AvRelationVO[] relaciones) {
        this.relaciones = relaciones;
    }


    /**
     * Gets the anotaciones value for this LomAvanzadoVO.
     * 
     * @return anotaciones
     */
    public es.pode.catalogacion.negocio.servicios.AvAnnotationVO[] getAnotaciones() {
        return anotaciones;
    }


    /**
     * Sets the anotaciones value for this LomAvanzadoVO.
     * 
     * @param anotaciones
     */
    public void setAnotaciones(es.pode.catalogacion.negocio.servicios.AvAnnotationVO[] anotaciones) {
        this.anotaciones = anotaciones;
    }


    /**
     * Gets the clasificaciones value for this LomAvanzadoVO.
     * 
     * @return clasificaciones
     */
    public es.pode.catalogacion.negocio.servicios.AvClassificationVO[] getClasificaciones() {
        return clasificaciones;
    }


    /**
     * Sets the clasificaciones value for this LomAvanzadoVO.
     * 
     * @param clasificaciones
     */
    public void setClasificaciones(es.pode.catalogacion.negocio.servicios.AvClassificationVO[] clasificaciones) {
        this.clasificaciones = clasificaciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LomAvanzadoVO)) return false;
        LomAvanzadoVO other = (LomAvanzadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.general==null && other.getGeneral()==null) || 
             (this.general!=null &&
              this.general.equals(other.getGeneral()))) &&
            ((this.educational==null && other.getEducational()==null) || 
             (this.educational!=null &&
              java.util.Arrays.equals(this.educational, other.getEducational()))) &&
            ((this.lifeCycle==null && other.getLifeCycle()==null) || 
             (this.lifeCycle!=null &&
              this.lifeCycle.equals(other.getLifeCycle()))) &&
            ((this.metaMetadata==null && other.getMetaMetadata()==null) || 
             (this.metaMetadata!=null &&
              this.metaMetadata.equals(other.getMetaMetadata()))) &&
            ((this.technical==null && other.getTechnical()==null) || 
             (this.technical!=null &&
              this.technical.equals(other.getTechnical()))) &&
            ((this.derechos==null && other.getDerechos()==null) || 
             (this.derechos!=null &&
              this.derechos.equals(other.getDerechos()))) &&
            ((this.relaciones==null && other.getRelaciones()==null) || 
             (this.relaciones!=null &&
              java.util.Arrays.equals(this.relaciones, other.getRelaciones()))) &&
            ((this.anotaciones==null && other.getAnotaciones()==null) || 
             (this.anotaciones!=null &&
              java.util.Arrays.equals(this.anotaciones, other.getAnotaciones()))) &&
            ((this.clasificaciones==null && other.getClasificaciones()==null) || 
             (this.clasificaciones!=null &&
              java.util.Arrays.equals(this.clasificaciones, other.getClasificaciones())));
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
        if (getGeneral() != null) {
            _hashCode += getGeneral().hashCode();
        }
        if (getEducational() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEducational());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEducational(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLifeCycle() != null) {
            _hashCode += getLifeCycle().hashCode();
        }
        if (getMetaMetadata() != null) {
            _hashCode += getMetaMetadata().hashCode();
        }
        if (getTechnical() != null) {
            _hashCode += getTechnical().hashCode();
        }
        if (getDerechos() != null) {
            _hashCode += getDerechos().hashCode();
        }
        if (getRelaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRelaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAnotaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAnotaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAnotaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getClasificaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClasificaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClasificaciones(), i);
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
        new org.apache.axis.description.TypeDesc(LomAvanzadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "general"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvGeneralVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("educational");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "educational"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvEducationalVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifeCycle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lifeCycle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvLifeCycleVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metaMetadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "metaMetadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvMetametadataVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technical");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "technical"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvTechnicalVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "derechos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRightsVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "relaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRelationVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anotaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "anotaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvAnnotationVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clasificaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "clasificaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvClassificationVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
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
