/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * EducationalVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;


/**
 * EducationalVO representa un subconjunto de los campos
 *                         correspondientes a la categoria Educational
 * de LOM-ES. Contiene
 *                         tipo, contexto, edad, idiomaDest y procesoCog.
 */
public class EducationalVO  implements java.io.Serializable {
    /* El primer tipo especifico de recurso educativo que aparece
     * en el
     *                                 metadato LOM-ES. */
    private java.lang.String tipo;

    /* Representa el entorno principal en el que se debe utilizar
     * el
     *                                 objeto digital. Se recoge en este
     * atributo el primer valor que
     *                                 aparece en el metadato LOM-ES para
     * el contexto. */
    private java.lang.String contexto;

    /* Edad del destinatario tipico. Se refiere a la edad de desarrollo
     * intelectual, en caso de que esta fuese distinta de la edad
     *                                 cronologica. */
    private java.lang.String edad;

    /* Idioma utilizado por el destinatario tipico del objeto
     *                                 educativo. */
    private java.lang.String idiomaDest;

    /* Representa la actividad provocada en el usuario de destino.
     * Es
     *                                 decir, el proceso cognitivo principal
     * implicado en el proceso de
     *                                 aprendizaje-evaluacion. */
    private java.lang.String procesoCog;

    public EducationalVO() {
    }

    public EducationalVO(
           java.lang.String tipo,
           java.lang.String contexto,
           java.lang.String edad,
           java.lang.String idiomaDest,
           java.lang.String procesoCog) {
           this.tipo = tipo;
           this.contexto = contexto;
           this.edad = edad;
           this.idiomaDest = idiomaDest;
           this.procesoCog = procesoCog;
    }


    /**
     * Gets the tipo value for this EducationalVO.
     * 
     * @return tipo   * El primer tipo especifico de recurso educativo que aparece
     * en el
     *                                 metadato LOM-ES.
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this EducationalVO.
     * 
     * @param tipo   * El primer tipo especifico de recurso educativo que aparece
     * en el
     *                                 metadato LOM-ES.
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the contexto value for this EducationalVO.
     * 
     * @return contexto   * Representa el entorno principal en el que se debe utilizar
     * el
     *                                 objeto digital. Se recoge en este
     * atributo el primer valor que
     *                                 aparece en el metadato LOM-ES para
     * el contexto.
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this EducationalVO.
     * 
     * @param contexto   * Representa el entorno principal en el que se debe utilizar
     * el
     *                                 objeto digital. Se recoge en este
     * atributo el primer valor que
     *                                 aparece en el metadato LOM-ES para
     * el contexto.
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the edad value for this EducationalVO.
     * 
     * @return edad   * Edad del destinatario tipico. Se refiere a la edad de desarrollo
     * intelectual, en caso de que esta fuese distinta de la edad
     *                                 cronologica.
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this EducationalVO.
     * 
     * @param edad   * Edad del destinatario tipico. Se refiere a la edad de desarrollo
     * intelectual, en caso de que esta fuese distinta de la edad
     *                                 cronologica.
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the idiomaDest value for this EducationalVO.
     * 
     * @return idiomaDest   * Idioma utilizado por el destinatario tipico del objeto
     *                                 educativo.
     */
    public java.lang.String getIdiomaDest() {
        return idiomaDest;
    }


    /**
     * Sets the idiomaDest value for this EducationalVO.
     * 
     * @param idiomaDest   * Idioma utilizado por el destinatario tipico del objeto
     *                                 educativo.
     */
    public void setIdiomaDest(java.lang.String idiomaDest) {
        this.idiomaDest = idiomaDest;
    }


    /**
     * Gets the procesoCog value for this EducationalVO.
     * 
     * @return procesoCog   * Representa la actividad provocada en el usuario de destino.
     * Es
     *                                 decir, el proceso cognitivo principal
     * implicado en el proceso de
     *                                 aprendizaje-evaluacion.
     */
    public java.lang.String getProcesoCog() {
        return procesoCog;
    }


    /**
     * Sets the procesoCog value for this EducationalVO.
     * 
     * @param procesoCog   * Representa la actividad provocada en el usuario de destino.
     * Es
     *                                 decir, el proceso cognitivo principal
     * implicado en el proceso de
     *                                 aprendizaje-evaluacion.
     */
    public void setProcesoCog(java.lang.String procesoCog) {
        this.procesoCog = procesoCog;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EducationalVO)) return false;
        EducationalVO other = (EducationalVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.contexto==null && other.getContexto()==null) || 
             (this.contexto!=null &&
              this.contexto.equals(other.getContexto()))) &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.idiomaDest==null && other.getIdiomaDest()==null) || 
             (this.idiomaDest!=null &&
              this.idiomaDest.equals(other.getIdiomaDest()))) &&
            ((this.procesoCog==null && other.getProcesoCog()==null) || 
             (this.procesoCog!=null &&
              this.procesoCog.equals(other.getProcesoCog())));
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getContexto() != null) {
            _hashCode += getContexto().hashCode();
        }
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getIdiomaDest() != null) {
            _hashCode += getIdiomaDest().hashCode();
        }
        if (getProcesoCog() != null) {
            _hashCode += getProcesoCog().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EducationalVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EducationalVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idiomaDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCog");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "procesoCog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
