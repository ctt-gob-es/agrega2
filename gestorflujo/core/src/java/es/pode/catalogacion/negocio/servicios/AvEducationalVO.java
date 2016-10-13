/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvEducationalVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvEducationalVO  implements java.io.Serializable {
    /* Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO tipoDeInteractividad;

    /* Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO nivelInteractividad;

    /* Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO densidadSemantica;

    /* Compuesto por source y value; source por defecto LOM-ESv1.0,
     * nos
     *                                 quedamos con value y lo definimos
     * como String */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO dificultad;

    private es.pode.catalogacion.negocio.servicios.DuracionVO tiempoAprendizaje;

    private es.pode.catalogacion.negocio.servicios.RangoEdadVO[] rangoedades;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones;

    private es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO[] tiposrecursoedu;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO[] destinatarios;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO[] contextos;

    private es.pode.catalogacion.negocio.servicios.SourceValueVO[] procesoscognitivos;

    public AvEducationalVO() {
    }

    public AvEducationalVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO tipoDeInteractividad,
           es.pode.catalogacion.negocio.servicios.SourceValueVO nivelInteractividad,
           es.pode.catalogacion.negocio.servicios.SourceValueVO densidadSemantica,
           es.pode.catalogacion.negocio.servicios.SourceValueVO dificultad,
           es.pode.catalogacion.negocio.servicios.DuracionVO tiempoAprendizaje,
           es.pode.catalogacion.negocio.servicios.RangoEdadVO[] rangoedades,
           es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones,
           es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas,
           es.pode.catalogacion.negocio.servicios.SourceValueVO[] tiposrecursoedu,
           es.pode.catalogacion.negocio.servicios.SourceValueVO[] destinatarios,
           es.pode.catalogacion.negocio.servicios.SourceValueVO[] contextos,
           es.pode.catalogacion.negocio.servicios.SourceValueVO[] procesoscognitivos) {
           this.tipoDeInteractividad = tipoDeInteractividad;
           this.nivelInteractividad = nivelInteractividad;
           this.densidadSemantica = densidadSemantica;
           this.dificultad = dificultad;
           this.tiempoAprendizaje = tiempoAprendizaje;
           this.rangoedades = rangoedades;
           this.descripciones = descripciones;
           this.idiomas = idiomas;
           this.tiposrecursoedu = tiposrecursoedu;
           this.destinatarios = destinatarios;
           this.contextos = contextos;
           this.procesoscognitivos = procesoscognitivos;
    }


    /**
     * Gets the tipoDeInteractividad value for this AvEducationalVO.
     * 
     * @return tipoDeInteractividad   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getTipoDeInteractividad() {
        return tipoDeInteractividad;
    }


    /**
     * Sets the tipoDeInteractividad value for this AvEducationalVO.
     * 
     * @param tipoDeInteractividad   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public void setTipoDeInteractividad(es.pode.catalogacion.negocio.servicios.SourceValueVO tipoDeInteractividad) {
        this.tipoDeInteractividad = tipoDeInteractividad;
    }


    /**
     * Gets the nivelInteractividad value for this AvEducationalVO.
     * 
     * @return nivelInteractividad   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getNivelInteractividad() {
        return nivelInteractividad;
    }


    /**
     * Sets the nivelInteractividad value for this AvEducationalVO.
     * 
     * @param nivelInteractividad   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public void setNivelInteractividad(es.pode.catalogacion.negocio.servicios.SourceValueVO nivelInteractividad) {
        this.nivelInteractividad = nivelInteractividad;
    }


    /**
     * Gets the densidadSemantica value for this AvEducationalVO.
     * 
     * @return densidadSemantica   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getDensidadSemantica() {
        return densidadSemantica;
    }


    /**
     * Sets the densidadSemantica value for this AvEducationalVO.
     * 
     * @param densidadSemantica   * Esta formado por source y value, source por defecto LOM-ESv1.0,
     * el valor que introducimos es value como un String
     */
    public void setDensidadSemantica(es.pode.catalogacion.negocio.servicios.SourceValueVO densidadSemantica) {
        this.densidadSemantica = densidadSemantica;
    }


    /**
     * Gets the dificultad value for this AvEducationalVO.
     * 
     * @return dificultad   * Compuesto por source y value; source por defecto LOM-ESv1.0,
     * nos
     *                                 quedamos con value y lo definimos
     * como String
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getDificultad() {
        return dificultad;
    }


    /**
     * Sets the dificultad value for this AvEducationalVO.
     * 
     * @param dificultad   * Compuesto por source y value; source por defecto LOM-ESv1.0,
     * nos
     *                                 quedamos con value y lo definimos
     * como String
     */
    public void setDificultad(es.pode.catalogacion.negocio.servicios.SourceValueVO dificultad) {
        this.dificultad = dificultad;
    }


    /**
     * Gets the tiempoAprendizaje value for this AvEducationalVO.
     * 
     * @return tiempoAprendizaje
     */
    public es.pode.catalogacion.negocio.servicios.DuracionVO getTiempoAprendizaje() {
        return tiempoAprendizaje;
    }


    /**
     * Sets the tiempoAprendizaje value for this AvEducationalVO.
     * 
     * @param tiempoAprendizaje
     */
    public void setTiempoAprendizaje(es.pode.catalogacion.negocio.servicios.DuracionVO tiempoAprendizaje) {
        this.tiempoAprendizaje = tiempoAprendizaje;
    }


    /**
     * Gets the rangoedades value for this AvEducationalVO.
     * 
     * @return rangoedades
     */
    public es.pode.catalogacion.negocio.servicios.RangoEdadVO[] getRangoedades() {
        return rangoedades;
    }


    /**
     * Sets the rangoedades value for this AvEducationalVO.
     * 
     * @param rangoedades
     */
    public void setRangoedades(es.pode.catalogacion.negocio.servicios.RangoEdadVO[] rangoedades) {
        this.rangoedades = rangoedades;
    }


    /**
     * Gets the descripciones value for this AvEducationalVO.
     * 
     * @return descripciones
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO[] getDescripciones() {
        return descripciones;
    }


    /**
     * Sets the descripciones value for this AvEducationalVO.
     * 
     * @param descripciones
     */
    public void setDescripciones(es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones) {
        this.descripciones = descripciones;
    }


    /**
     * Gets the idiomas value for this AvEducationalVO.
     * 
     * @return idiomas
     */
    public es.pode.catalogacion.negocio.servicios.IdiomaVO[] getIdiomas() {
        return idiomas;
    }


    /**
     * Sets the idiomas value for this AvEducationalVO.
     * 
     * @param idiomas
     */
    public void setIdiomas(es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas) {
        this.idiomas = idiomas;
    }


    /**
     * Gets the tiposrecursoedu value for this AvEducationalVO.
     * 
     * @return tiposrecursoedu
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO[] getTiposrecursoedu() {
        return tiposrecursoedu;
    }


    /**
     * Sets the tiposrecursoedu value for this AvEducationalVO.
     * 
     * @param tiposrecursoedu
     */
    public void setTiposrecursoedu(es.pode.catalogacion.negocio.servicios.SourceValueVO[] tiposrecursoedu) {
        this.tiposrecursoedu = tiposrecursoedu;
    }


    /**
     * Gets the destinatarios value for this AvEducationalVO.
     * 
     * @return destinatarios
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO[] getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this AvEducationalVO.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(es.pode.catalogacion.negocio.servicios.SourceValueVO[] destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the contextos value for this AvEducationalVO.
     * 
     * @return contextos
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO[] getContextos() {
        return contextos;
    }


    /**
     * Sets the contextos value for this AvEducationalVO.
     * 
     * @param contextos
     */
    public void setContextos(es.pode.catalogacion.negocio.servicios.SourceValueVO[] contextos) {
        this.contextos = contextos;
    }


    /**
     * Gets the procesoscognitivos value for this AvEducationalVO.
     * 
     * @return procesoscognitivos
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO[] getProcesoscognitivos() {
        return procesoscognitivos;
    }


    /**
     * Sets the procesoscognitivos value for this AvEducationalVO.
     * 
     * @param procesoscognitivos
     */
    public void setProcesoscognitivos(es.pode.catalogacion.negocio.servicios.SourceValueVO[] procesoscognitivos) {
        this.procesoscognitivos = procesoscognitivos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvEducationalVO)) return false;
        AvEducationalVO other = (AvEducationalVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoDeInteractividad==null && other.getTipoDeInteractividad()==null) || 
             (this.tipoDeInteractividad!=null &&
              this.tipoDeInteractividad.equals(other.getTipoDeInteractividad()))) &&
            ((this.nivelInteractividad==null && other.getNivelInteractividad()==null) || 
             (this.nivelInteractividad!=null &&
              this.nivelInteractividad.equals(other.getNivelInteractividad()))) &&
            ((this.densidadSemantica==null && other.getDensidadSemantica()==null) || 
             (this.densidadSemantica!=null &&
              this.densidadSemantica.equals(other.getDensidadSemantica()))) &&
            ((this.dificultad==null && other.getDificultad()==null) || 
             (this.dificultad!=null &&
              this.dificultad.equals(other.getDificultad()))) &&
            ((this.tiempoAprendizaje==null && other.getTiempoAprendizaje()==null) || 
             (this.tiempoAprendizaje!=null &&
              this.tiempoAprendizaje.equals(other.getTiempoAprendizaje()))) &&
            ((this.rangoedades==null && other.getRangoedades()==null) || 
             (this.rangoedades!=null &&
              java.util.Arrays.equals(this.rangoedades, other.getRangoedades()))) &&
            ((this.descripciones==null && other.getDescripciones()==null) || 
             (this.descripciones!=null &&
              java.util.Arrays.equals(this.descripciones, other.getDescripciones()))) &&
            ((this.idiomas==null && other.getIdiomas()==null) || 
             (this.idiomas!=null &&
              java.util.Arrays.equals(this.idiomas, other.getIdiomas()))) &&
            ((this.tiposrecursoedu==null && other.getTiposrecursoedu()==null) || 
             (this.tiposrecursoedu!=null &&
              java.util.Arrays.equals(this.tiposrecursoedu, other.getTiposrecursoedu()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              java.util.Arrays.equals(this.destinatarios, other.getDestinatarios()))) &&
            ((this.contextos==null && other.getContextos()==null) || 
             (this.contextos!=null &&
              java.util.Arrays.equals(this.contextos, other.getContextos()))) &&
            ((this.procesoscognitivos==null && other.getProcesoscognitivos()==null) || 
             (this.procesoscognitivos!=null &&
              java.util.Arrays.equals(this.procesoscognitivos, other.getProcesoscognitivos())));
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
        if (getTipoDeInteractividad() != null) {
            _hashCode += getTipoDeInteractividad().hashCode();
        }
        if (getNivelInteractividad() != null) {
            _hashCode += getNivelInteractividad().hashCode();
        }
        if (getDensidadSemantica() != null) {
            _hashCode += getDensidadSemantica().hashCode();
        }
        if (getDificultad() != null) {
            _hashCode += getDificultad().hashCode();
        }
        if (getTiempoAprendizaje() != null) {
            _hashCode += getTiempoAprendizaje().hashCode();
        }
        if (getRangoedades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRangoedades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRangoedades(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescripciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescripciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescripciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdiomas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdiomas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdiomas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTiposrecursoedu() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiposrecursoedu());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiposrecursoedu(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDestinatarios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestinatarios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestinatarios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContextos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContextos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContextos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProcesoscognitivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProcesoscognitivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProcesoscognitivos(), i);
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
        new org.apache.axis.description.TypeDesc(AvEducationalVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvEducationalVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDeInteractividad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tipoDeInteractividad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelInteractividad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "nivelInteractividad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("densidadSemantica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "densidadSemantica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dificultad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "dificultad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiempoAprendizaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tiempoAprendizaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DuracionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rangoedades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "rangoedades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RangoEdadVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "descripciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idiomas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdiomaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiposrecursoedu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tiposrecursoedu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "contextos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoscognitivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "procesoscognitivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
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
