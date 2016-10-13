/**
 * MDBasicosOblVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;


/**
 * MDBasicosOblVO, tipo de datos para metadatos basicos
 *                         obligatorios; sus atributos son los que deben
 * estar rellenos
 *                         obligatoriamente en los metadatos de tipo
 * LOM-ES
 */
public class MDBasicosOblVO  implements java.io.Serializable {
    private java.lang.String titulo;

    private java.lang.String idioma;

    private java.lang.String descripcion;

    private java.lang.String tipoRecurso;

    private java.lang.String contexto;

    private java.lang.String edad;

    private java.lang.String idiomaDest;

    private java.lang.String procesoCog;

    public MDBasicosOblVO() {
    }

    public MDBasicosOblVO(
           java.lang.String titulo,
           java.lang.String idioma,
           java.lang.String descripcion,
           java.lang.String tipoRecurso,
           java.lang.String contexto,
           java.lang.String edad,
           java.lang.String idiomaDest,
           java.lang.String procesoCog) {
           this.titulo = titulo;
           this.idioma = idioma;
           this.descripcion = descripcion;
           this.tipoRecurso = tipoRecurso;
           this.contexto = contexto;
           this.edad = edad;
           this.idiomaDest = idiomaDest;
           this.procesoCog = procesoCog;
    }


    /**
     * Gets the titulo value for this MDBasicosOblVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this MDBasicosOblVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the idioma value for this MDBasicosOblVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this MDBasicosOblVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the descripcion value for this MDBasicosOblVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this MDBasicosOblVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the tipoRecurso value for this MDBasicosOblVO.
     * 
     * @return tipoRecurso
     */
    public java.lang.String getTipoRecurso() {
        return tipoRecurso;
    }


    /**
     * Sets the tipoRecurso value for this MDBasicosOblVO.
     * 
     * @param tipoRecurso
     */
    public void setTipoRecurso(java.lang.String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    /**
     * Gets the contexto value for this MDBasicosOblVO.
     * 
     * @return contexto
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this MDBasicosOblVO.
     * 
     * @param contexto
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the edad value for this MDBasicosOblVO.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this MDBasicosOblVO.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the idiomaDest value for this MDBasicosOblVO.
     * 
     * @return idiomaDest
     */
    public java.lang.String getIdiomaDest() {
        return idiomaDest;
    }


    /**
     * Sets the idiomaDest value for this MDBasicosOblVO.
     * 
     * @param idiomaDest
     */
    public void setIdiomaDest(java.lang.String idiomaDest) {
        this.idiomaDest = idiomaDest;
    }


    /**
     * Gets the procesoCog value for this MDBasicosOblVO.
     * 
     * @return procesoCog
     */
    public java.lang.String getProcesoCog() {
        return procesoCog;
    }


    /**
     * Sets the procesoCog value for this MDBasicosOblVO.
     * 
     * @param procesoCog
     */
    public void setProcesoCog(java.lang.String procesoCog) {
        this.procesoCog = procesoCog;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDBasicosOblVO)) return false;
        MDBasicosOblVO other = (MDBasicosOblVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.tipoRecurso==null && other.getTipoRecurso()==null) || 
             (this.tipoRecurso!=null &&
              this.tipoRecurso.equals(other.getTipoRecurso()))) &&
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTipoRecurso() != null) {
            _hashCode += getTipoRecurso().hashCode();
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
        new org.apache.axis.description.TypeDesc(MDBasicosOblVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "MDBasicosOblVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "tipoRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "idiomaDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCog");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "procesoCog"));
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
