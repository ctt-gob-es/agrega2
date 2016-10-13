/**
 * AvTechnicalVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvTechnicalVO  implements java.io.Serializable {
    private es.pode.catalogacion.negocio.servicios.TamanioVO tamanio;

    private es.pode.catalogacion.negocio.servicios.PautasInstalacionVO pautasInstalacion;

    private es.pode.catalogacion.negocio.servicios.OtrosRequisitosVO otrosRequisitos;

    private es.pode.catalogacion.negocio.servicios.DuracionVO duracion;

    private es.pode.catalogacion.negocio.servicios.FormatoVO[] formatos;

    private es.pode.catalogacion.negocio.servicios.LocalizacionVO[] localizaciones;

    private es.pode.catalogacion.negocio.servicios.RequisitoVO[] requisitos;

    public AvTechnicalVO() {
    }

    public AvTechnicalVO(
           es.pode.catalogacion.negocio.servicios.TamanioVO tamanio,
           es.pode.catalogacion.negocio.servicios.PautasInstalacionVO pautasInstalacion,
           es.pode.catalogacion.negocio.servicios.OtrosRequisitosVO otrosRequisitos,
           es.pode.catalogacion.negocio.servicios.DuracionVO duracion,
           es.pode.catalogacion.negocio.servicios.FormatoVO[] formatos,
           es.pode.catalogacion.negocio.servicios.LocalizacionVO[] localizaciones,
           es.pode.catalogacion.negocio.servicios.RequisitoVO[] requisitos) {
           this.tamanio = tamanio;
           this.pautasInstalacion = pautasInstalacion;
           this.otrosRequisitos = otrosRequisitos;
           this.duracion = duracion;
           this.formatos = formatos;
           this.localizaciones = localizaciones;
           this.requisitos = requisitos;
    }


    /**
     * Gets the tamanio value for this AvTechnicalVO.
     * 
     * @return tamanio
     */
    public es.pode.catalogacion.negocio.servicios.TamanioVO getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this AvTechnicalVO.
     * 
     * @param tamanio
     */
    public void setTamanio(es.pode.catalogacion.negocio.servicios.TamanioVO tamanio) {
        this.tamanio = tamanio;
    }


    /**
     * Gets the pautasInstalacion value for this AvTechnicalVO.
     * 
     * @return pautasInstalacion
     */
    public es.pode.catalogacion.negocio.servicios.PautasInstalacionVO getPautasInstalacion() {
        return pautasInstalacion;
    }


    /**
     * Sets the pautasInstalacion value for this AvTechnicalVO.
     * 
     * @param pautasInstalacion
     */
    public void setPautasInstalacion(es.pode.catalogacion.negocio.servicios.PautasInstalacionVO pautasInstalacion) {
        this.pautasInstalacion = pautasInstalacion;
    }


    /**
     * Gets the otrosRequisitos value for this AvTechnicalVO.
     * 
     * @return otrosRequisitos
     */
    public es.pode.catalogacion.negocio.servicios.OtrosRequisitosVO getOtrosRequisitos() {
        return otrosRequisitos;
    }


    /**
     * Sets the otrosRequisitos value for this AvTechnicalVO.
     * 
     * @param otrosRequisitos
     */
    public void setOtrosRequisitos(es.pode.catalogacion.negocio.servicios.OtrosRequisitosVO otrosRequisitos) {
        this.otrosRequisitos = otrosRequisitos;
    }


    /**
     * Gets the duracion value for this AvTechnicalVO.
     * 
     * @return duracion
     */
    public es.pode.catalogacion.negocio.servicios.DuracionVO getDuracion() {
        return duracion;
    }


    /**
     * Sets the duracion value for this AvTechnicalVO.
     * 
     * @param duracion
     */
    public void setDuracion(es.pode.catalogacion.negocio.servicios.DuracionVO duracion) {
        this.duracion = duracion;
    }


    /**
     * Gets the formatos value for this AvTechnicalVO.
     * 
     * @return formatos
     */
    public es.pode.catalogacion.negocio.servicios.FormatoVO[] getFormatos() {
        return formatos;
    }


    /**
     * Sets the formatos value for this AvTechnicalVO.
     * 
     * @param formatos
     */
    public void setFormatos(es.pode.catalogacion.negocio.servicios.FormatoVO[] formatos) {
        this.formatos = formatos;
    }


    /**
     * Gets the localizaciones value for this AvTechnicalVO.
     * 
     * @return localizaciones
     */
    public es.pode.catalogacion.negocio.servicios.LocalizacionVO[] getLocalizaciones() {
        return localizaciones;
    }


    /**
     * Sets the localizaciones value for this AvTechnicalVO.
     * 
     * @param localizaciones
     */
    public void setLocalizaciones(es.pode.catalogacion.negocio.servicios.LocalizacionVO[] localizaciones) {
        this.localizaciones = localizaciones;
    }


    /**
     * Gets the requisitos value for this AvTechnicalVO.
     * 
     * @return requisitos
     */
    public es.pode.catalogacion.negocio.servicios.RequisitoVO[] getRequisitos() {
        return requisitos;
    }


    /**
     * Sets the requisitos value for this AvTechnicalVO.
     * 
     * @param requisitos
     */
    public void setRequisitos(es.pode.catalogacion.negocio.servicios.RequisitoVO[] requisitos) {
        this.requisitos = requisitos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvTechnicalVO)) return false;
        AvTechnicalVO other = (AvTechnicalVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tamanio==null && other.getTamanio()==null) || 
             (this.tamanio!=null &&
              this.tamanio.equals(other.getTamanio()))) &&
            ((this.pautasInstalacion==null && other.getPautasInstalacion()==null) || 
             (this.pautasInstalacion!=null &&
              this.pautasInstalacion.equals(other.getPautasInstalacion()))) &&
            ((this.otrosRequisitos==null && other.getOtrosRequisitos()==null) || 
             (this.otrosRequisitos!=null &&
              this.otrosRequisitos.equals(other.getOtrosRequisitos()))) &&
            ((this.duracion==null && other.getDuracion()==null) || 
             (this.duracion!=null &&
              this.duracion.equals(other.getDuracion()))) &&
            ((this.formatos==null && other.getFormatos()==null) || 
             (this.formatos!=null &&
              java.util.Arrays.equals(this.formatos, other.getFormatos()))) &&
            ((this.localizaciones==null && other.getLocalizaciones()==null) || 
             (this.localizaciones!=null &&
              java.util.Arrays.equals(this.localizaciones, other.getLocalizaciones()))) &&
            ((this.requisitos==null && other.getRequisitos()==null) || 
             (this.requisitos!=null &&
              java.util.Arrays.equals(this.requisitos, other.getRequisitos())));
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
        if (getTamanio() != null) {
            _hashCode += getTamanio().hashCode();
        }
        if (getPautasInstalacion() != null) {
            _hashCode += getPautasInstalacion().hashCode();
        }
        if (getOtrosRequisitos() != null) {
            _hashCode += getOtrosRequisitos().hashCode();
        }
        if (getDuracion() != null) {
            _hashCode += getDuracion().hashCode();
        }
        if (getFormatos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormatos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFormatos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLocalizaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLocalizaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLocalizaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRequisitos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequisitos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequisitos(), i);
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
        new org.apache.axis.description.TypeDesc(AvTechnicalVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvTechnicalVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tamanio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TamanioVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pautasInstalacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "pautasInstalacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PautasInstalacionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otrosRequisitos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "otrosRequisitos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "OtrosRequisitosVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "duracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DuracionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "formatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FormatoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "localizaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LocalizacionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requisitos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "requisitos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RequisitoVO"));
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
