/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * AvGeneralVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class AvGeneralVO  implements java.io.Serializable {
    /* Esta formada por source y value, pero el source sera por defecto
     * LOM-ESv1.0
     *                                 con lo que definimos la entrada para
     * value solo */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO estructura;

    /* Nivel de agregacion esta compuesto por source y value pero
     * la
     *                                 etiqueta source sera por defecto LOM-ESv1.0 */
    private es.pode.catalogacion.negocio.servicios.SourceValueVO nivelAgregacion;

    private es.pode.catalogacion.negocio.servicios.TituloVO titulo;

    private es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores;

    private es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones;

    private es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas;

    private es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave;

    private es.pode.catalogacion.negocio.servicios.AmbitoVO[] ambitos;

    public AvGeneralVO() {
    }

    public AvGeneralVO(
           es.pode.catalogacion.negocio.servicios.SourceValueVO estructura,
           es.pode.catalogacion.negocio.servicios.SourceValueVO nivelAgregacion,
           es.pode.catalogacion.negocio.servicios.TituloVO titulo,
           es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores,
           es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones,
           es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas,
           es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave,
           es.pode.catalogacion.negocio.servicios.AmbitoVO[] ambitos) {
           this.estructura = estructura;
           this.nivelAgregacion = nivelAgregacion;
           this.titulo = titulo;
           this.identificadores = identificadores;
           this.descripciones = descripciones;
           this.idiomas = idiomas;
           this.palabrasClave = palabrasClave;
           this.ambitos = ambitos;
    }


    /**
     * Gets the estructura value for this AvGeneralVO.
     * 
     * @return estructura   * Esta formada por source y value, pero el source sera por defecto
     * LOM-ESv1.0
     *                                 con lo que definimos la entrada para
     * value solo
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getEstructura() {
        return estructura;
    }


    /**
     * Sets the estructura value for this AvGeneralVO.
     * 
     * @param estructura   * Esta formada por source y value, pero el source sera por defecto
     * LOM-ESv1.0
     *                                 con lo que definimos la entrada para
     * value solo
     */
    public void setEstructura(es.pode.catalogacion.negocio.servicios.SourceValueVO estructura) {
        this.estructura = estructura;
    }


    /**
     * Gets the nivelAgregacion value for this AvGeneralVO.
     * 
     * @return nivelAgregacion   * Nivel de agregacion esta compuesto por source y value pero
     * la
     *                                 etiqueta source sera por defecto LOM-ESv1.0
     */
    public es.pode.catalogacion.negocio.servicios.SourceValueVO getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this AvGeneralVO.
     * 
     * @param nivelAgregacion   * Nivel de agregacion esta compuesto por source y value pero
     * la
     *                                 etiqueta source sera por defecto LOM-ESv1.0
     */
    public void setNivelAgregacion(es.pode.catalogacion.negocio.servicios.SourceValueVO nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the titulo value for this AvGeneralVO.
     * 
     * @return titulo
     */
    public es.pode.catalogacion.negocio.servicios.TituloVO getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this AvGeneralVO.
     * 
     * @param titulo
     */
    public void setTitulo(es.pode.catalogacion.negocio.servicios.TituloVO titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the identificadores value for this AvGeneralVO.
     * 
     * @return identificadores
     */
    public es.pode.catalogacion.negocio.servicios.IdentificadorVO[] getIdentificadores() {
        return identificadores;
    }


    /**
     * Sets the identificadores value for this AvGeneralVO.
     * 
     * @param identificadores
     */
    public void setIdentificadores(es.pode.catalogacion.negocio.servicios.IdentificadorVO[] identificadores) {
        this.identificadores = identificadores;
    }


    /**
     * Gets the descripciones value for this AvGeneralVO.
     * 
     * @return descripciones
     */
    public es.pode.catalogacion.negocio.servicios.DescripcionVO[] getDescripciones() {
        return descripciones;
    }


    /**
     * Sets the descripciones value for this AvGeneralVO.
     * 
     * @param descripciones
     */
    public void setDescripciones(es.pode.catalogacion.negocio.servicios.DescripcionVO[] descripciones) {
        this.descripciones = descripciones;
    }


    /**
     * Gets the idiomas value for this AvGeneralVO.
     * 
     * @return idiomas
     */
    public es.pode.catalogacion.negocio.servicios.IdiomaVO[] getIdiomas() {
        return idiomas;
    }


    /**
     * Sets the idiomas value for this AvGeneralVO.
     * 
     * @param idiomas
     */
    public void setIdiomas(es.pode.catalogacion.negocio.servicios.IdiomaVO[] idiomas) {
        this.idiomas = idiomas;
    }


    /**
     * Gets the palabrasClave value for this AvGeneralVO.
     * 
     * @return palabrasClave
     */
    public es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this AvGeneralVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(es.pode.catalogacion.negocio.servicios.PalabraClaveVO[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the ambitos value for this AvGeneralVO.
     * 
     * @return ambitos
     */
    public es.pode.catalogacion.negocio.servicios.AmbitoVO[] getAmbitos() {
        return ambitos;
    }


    /**
     * Sets the ambitos value for this AvGeneralVO.
     * 
     * @param ambitos
     */
    public void setAmbitos(es.pode.catalogacion.negocio.servicios.AmbitoVO[] ambitos) {
        this.ambitos = ambitos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AvGeneralVO)) return false;
        AvGeneralVO other = (AvGeneralVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estructura==null && other.getEstructura()==null) || 
             (this.estructura!=null &&
              this.estructura.equals(other.getEstructura()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.identificadores==null && other.getIdentificadores()==null) || 
             (this.identificadores!=null &&
              java.util.Arrays.equals(this.identificadores, other.getIdentificadores()))) &&
            ((this.descripciones==null && other.getDescripciones()==null) || 
             (this.descripciones!=null &&
              java.util.Arrays.equals(this.descripciones, other.getDescripciones()))) &&
            ((this.idiomas==null && other.getIdiomas()==null) || 
             (this.idiomas!=null &&
              java.util.Arrays.equals(this.idiomas, other.getIdiomas()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave()))) &&
            ((this.ambitos==null && other.getAmbitos()==null) || 
             (this.ambitos!=null &&
              java.util.Arrays.equals(this.ambitos, other.getAmbitos())));
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
        if (getEstructura() != null) {
            _hashCode += getEstructura().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getIdentificadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificadores(), i);
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
        if (getPalabrasClave() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPalabrasClave());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPalabrasClave(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAmbitos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAmbitos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAmbitos(), i);
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
        new org.apache.axis.description.TypeDesc(AvGeneralVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvGeneralVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estructura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "estructura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TituloVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO"));
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
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PalabraClaveVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambitos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ambitos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AmbitoVO"));
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
