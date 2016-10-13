/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * DocVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Esta clase representa un documento como resultado unico de una
 *                         busqueda.
 */
public class DocVO  implements java.io.Serializable {
    private java.lang.String titulo;

    private java.lang.String descripcion;

    /* Localizador del objeto digital. */
    private java.lang.String localizadorODE;

    /* Valoracion del ODE. */
    private java.lang.Integer valoracion;

    /* Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso. */
    private java.lang.String[] tipoRecurso;

    /* Curso al que pertenece el ODE. */
    private java.lang.String curso;

    /* Area curricular a la que pertenece el ODE. */
    private java.lang.String areaCurricular;

    /* Licencias del ODE. */
    private java.lang.String[] licencias;

    /* Formato del ODE. */
    private java.lang.String formato;

    /* Destinatarios del ODE. */
    private java.lang.String[] destinatarios;

    /* Idiomas que contempla la informacion del ODE. */
    private java.lang.String[] idioma;

    /* Ambito de aplicacion del ODE. */
    private java.lang.String[] ambito;

    private java.lang.Integer ranking;

    /* Se trata del identificador alfanumerico del ODE. */
    private java.lang.String identificadorODE;

    public DocVO() {
    }

    public DocVO(
           java.lang.String titulo,
           java.lang.String descripcion,
           java.lang.String localizadorODE,
           java.lang.Integer valoracion,
           java.lang.String[] tipoRecurso,
           java.lang.String curso,
           java.lang.String areaCurricular,
           java.lang.String[] licencias,
           java.lang.String formato,
           java.lang.String[] destinatarios,
           java.lang.String[] idioma,
           java.lang.String[] ambito,
           java.lang.Integer ranking,
           java.lang.String identificadorODE) {
           this.titulo = titulo;
           this.descripcion = descripcion;
           this.localizadorODE = localizadorODE;
           this.valoracion = valoracion;
           this.tipoRecurso = tipoRecurso;
           this.curso = curso;
           this.areaCurricular = areaCurricular;
           this.licencias = licencias;
           this.formato = formato;
           this.destinatarios = destinatarios;
           this.idioma = idioma;
           this.ambito = ambito;
           this.ranking = ranking;
           this.identificadorODE = identificadorODE;
    }


    /**
     * Gets the titulo value for this DocVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DocVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the descripcion value for this DocVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DocVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the localizadorODE value for this DocVO.
     * 
     * @return localizadorODE   * Localizador del objeto digital.
     */
    public java.lang.String getLocalizadorODE() {
        return localizadorODE;
    }


    /**
     * Sets the localizadorODE value for this DocVO.
     * 
     * @param localizadorODE   * Localizador del objeto digital.
     */
    public void setLocalizadorODE(java.lang.String localizadorODE) {
        this.localizadorODE = localizadorODE;
    }


    /**
     * Gets the valoracion value for this DocVO.
     * 
     * @return valoracion   * Valoracion del ODE.
     */
    public java.lang.Integer getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this DocVO.
     * 
     * @param valoracion   * Valoracion del ODE.
     */
    public void setValoracion(java.lang.Integer valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the tipoRecurso value for this DocVO.
     * 
     * @return tipoRecurso   * Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso.
     */
    public java.lang.String[] getTipoRecurso() {
        return tipoRecurso;
    }


    /**
     * Sets the tipoRecurso value for this DocVO.
     * 
     * @param tipoRecurso   * Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso.
     */
    public void setTipoRecurso(java.lang.String[] tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    /**
     * Gets the curso value for this DocVO.
     * 
     * @return curso   * Curso al que pertenece el ODE.
     */
    public java.lang.String getCurso() {
        return curso;
    }


    /**
     * Sets the curso value for this DocVO.
     * 
     * @param curso   * Curso al que pertenece el ODE.
     */
    public void setCurso(java.lang.String curso) {
        this.curso = curso;
    }


    /**
     * Gets the areaCurricular value for this DocVO.
     * 
     * @return areaCurricular   * Area curricular a la que pertenece el ODE.
     */
    public java.lang.String getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this DocVO.
     * 
     * @param areaCurricular   * Area curricular a la que pertenece el ODE.
     */
    public void setAreaCurricular(java.lang.String areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the licencias value for this DocVO.
     * 
     * @return licencias   * Licencias del ODE.
     */
    public java.lang.String[] getLicencias() {
        return licencias;
    }


    /**
     * Sets the licencias value for this DocVO.
     * 
     * @param licencias   * Licencias del ODE.
     */
    public void setLicencias(java.lang.String[] licencias) {
        this.licencias = licencias;
    }


    /**
     * Gets the formato value for this DocVO.
     * 
     * @return formato   * Formato del ODE.
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this DocVO.
     * 
     * @param formato   * Formato del ODE.
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the destinatarios value for this DocVO.
     * 
     * @return destinatarios   * Destinatarios del ODE.
     */
    public java.lang.String[] getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this DocVO.
     * 
     * @param destinatarios   * Destinatarios del ODE.
     */
    public void setDestinatarios(java.lang.String[] destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the idioma value for this DocVO.
     * 
     * @return idioma   * Idiomas que contempla la informacion del ODE.
     */
    public java.lang.String[] getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this DocVO.
     * 
     * @param idioma   * Idiomas que contempla la informacion del ODE.
     */
    public void setIdioma(java.lang.String[] idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the ambito value for this DocVO.
     * 
     * @return ambito   * Ambito de aplicacion del ODE.
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this DocVO.
     * 
     * @param ambito   * Ambito de aplicacion del ODE.
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the ranking value for this DocVO.
     * 
     * @return ranking
     */
    public java.lang.Integer getRanking() {
        return ranking;
    }


    /**
     * Sets the ranking value for this DocVO.
     * 
     * @param ranking
     */
    public void setRanking(java.lang.Integer ranking) {
        this.ranking = ranking;
    }


    /**
     * Gets the identificadorODE value for this DocVO.
     * 
     * @return identificadorODE   * Se trata del identificador alfanumerico del ODE.
     */
    public java.lang.String getIdentificadorODE() {
        return identificadorODE;
    }


    /**
     * Sets the identificadorODE value for this DocVO.
     * 
     * @param identificadorODE   * Se trata del identificador alfanumerico del ODE.
     */
    public void setIdentificadorODE(java.lang.String identificadorODE) {
        this.identificadorODE = identificadorODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocVO)) return false;
        DocVO other = (DocVO) obj;
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
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.localizadorODE==null && other.getLocalizadorODE()==null) || 
             (this.localizadorODE!=null &&
              this.localizadorODE.equals(other.getLocalizadorODE()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.tipoRecurso==null && other.getTipoRecurso()==null) || 
             (this.tipoRecurso!=null &&
              java.util.Arrays.equals(this.tipoRecurso, other.getTipoRecurso()))) &&
            ((this.curso==null && other.getCurso()==null) || 
             (this.curso!=null &&
              this.curso.equals(other.getCurso()))) &&
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              this.areaCurricular.equals(other.getAreaCurricular()))) &&
            ((this.licencias==null && other.getLicencias()==null) || 
             (this.licencias!=null &&
              java.util.Arrays.equals(this.licencias, other.getLicencias()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              java.util.Arrays.equals(this.destinatarios, other.getDestinatarios()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              java.util.Arrays.equals(this.idioma, other.getIdioma()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              java.util.Arrays.equals(this.ambito, other.getAmbito()))) &&
            ((this.ranking==null && other.getRanking()==null) || 
             (this.ranking!=null &&
              this.ranking.equals(other.getRanking()))) &&
            ((this.identificadorODE==null && other.getIdentificadorODE()==null) || 
             (this.identificadorODE!=null &&
              this.identificadorODE.equals(other.getIdentificadorODE())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getLocalizadorODE() != null) {
            _hashCode += getLocalizadorODE().hashCode();
        }
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getTipoRecurso() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipoRecurso());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipoRecurso(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurso() != null) {
            _hashCode += getCurso().hashCode();
        }
        if (getAreaCurricular() != null) {
            _hashCode += getAreaCurricular().hashCode();
        }
        if (getLicencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLicencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLicencias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
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
        if (getIdioma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdioma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdioma(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAmbito() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAmbito());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAmbito(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRanking() != null) {
            _hashCode += getRanking().hashCode();
        }
        if (getIdentificadorODE() != null) {
            _hashCode += getIdentificadorODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "DocVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "localizadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "tipoRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "curso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "licencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ranking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ranking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "identificadorODE"));
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
