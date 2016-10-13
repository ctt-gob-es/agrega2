/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParamAvanzadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;


/**
 * VO que alberga los parametros que acepta una busqueda avanzada.
 */
public class ParamAvanzadoVO  implements java.io.Serializable {
    private java.lang.String valoracion;

    private java.lang.String titulo;

    private java.lang.Boolean secuencia;

    private java.lang.String recurso;

    private java.lang.String procesoCognitivo;

    private java.lang.String palabrasClave;

    private java.lang.Integer numeroResultados;

    private java.lang.String[] taxonomia;

    private java.lang.String idiomaNavegacion;

    private java.lang.String idiomaBusqueda;

    private java.lang.String[] formato;

    private java.lang.String fechaPublicacion;

    private java.lang.String edad;

    private java.lang.String descripcion;

    private java.lang.String contexto;

    private java.lang.String autor;

    /* Identifica un ode univocamente */
    private java.lang.String identificadorODE;

    private java.lang.String fechaPublicacionRango;

    private java.lang.String[] nivelAgregacion;

    private java.lang.String destinatarios;

    private java.lang.String keyword;

    private java.lang.String ambito;

    private java.lang.String busquedaSimpleAvanzada;

    public ParamAvanzadoVO() {
    }

    public ParamAvanzadoVO(
           java.lang.String valoracion,
           java.lang.String titulo,
           java.lang.Boolean secuencia,
           java.lang.String recurso,
           java.lang.String procesoCognitivo,
           java.lang.String palabrasClave,
           java.lang.Integer numeroResultados,
           java.lang.String[] taxonomia,
           java.lang.String idiomaNavegacion,
           java.lang.String idiomaBusqueda,
           java.lang.String[] formato,
           java.lang.String fechaPublicacion,
           java.lang.String edad,
           java.lang.String descripcion,
           java.lang.String contexto,
           java.lang.String autor,
           java.lang.String identificadorODE,
           java.lang.String fechaPublicacionRango,
           java.lang.String[] nivelAgregacion,
           java.lang.String destinatarios,
           java.lang.String keyword,
           java.lang.String ambito,
           java.lang.String busquedaSimpleAvanzada) {
           this.valoracion = valoracion;
           this.titulo = titulo;
           this.secuencia = secuencia;
           this.recurso = recurso;
           this.procesoCognitivo = procesoCognitivo;
           this.palabrasClave = palabrasClave;
           this.numeroResultados = numeroResultados;
           this.taxonomia = taxonomia;
           this.idiomaNavegacion = idiomaNavegacion;
           this.idiomaBusqueda = idiomaBusqueda;
           this.formato = formato;
           this.fechaPublicacion = fechaPublicacion;
           this.edad = edad;
           this.descripcion = descripcion;
           this.contexto = contexto;
           this.autor = autor;
           this.identificadorODE = identificadorODE;
           this.fechaPublicacionRango = fechaPublicacionRango;
           this.nivelAgregacion = nivelAgregacion;
           this.destinatarios = destinatarios;
           this.keyword = keyword;
           this.ambito = ambito;
           this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
    }


    /**
     * Gets the valoracion value for this ParamAvanzadoVO.
     * 
     * @return valoracion
     */
    public java.lang.String getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ParamAvanzadoVO.
     * 
     * @param valoracion
     */
    public void setValoracion(java.lang.String valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the titulo value for this ParamAvanzadoVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ParamAvanzadoVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the secuencia value for this ParamAvanzadoVO.
     * 
     * @return secuencia
     */
    public java.lang.Boolean getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this ParamAvanzadoVO.
     * 
     * @param secuencia
     */
    public void setSecuencia(java.lang.Boolean secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the recurso value for this ParamAvanzadoVO.
     * 
     * @return recurso
     */
    public java.lang.String getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this ParamAvanzadoVO.
     * 
     * @param recurso
     */
    public void setRecurso(java.lang.String recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the procesoCognitivo value for this ParamAvanzadoVO.
     * 
     * @return procesoCognitivo
     */
    public java.lang.String getProcesoCognitivo() {
        return procesoCognitivo;
    }


    /**
     * Sets the procesoCognitivo value for this ParamAvanzadoVO.
     * 
     * @param procesoCognitivo
     */
    public void setProcesoCognitivo(java.lang.String procesoCognitivo) {
        this.procesoCognitivo = procesoCognitivo;
    }


    /**
     * Gets the palabrasClave value for this ParamAvanzadoVO.
     * 
     * @return palabrasClave
     */
    public java.lang.String getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this ParamAvanzadoVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(java.lang.String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the numeroResultados value for this ParamAvanzadoVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParamAvanzadoVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the taxonomia value for this ParamAvanzadoVO.
     * 
     * @return taxonomia
     */
    public java.lang.String[] getTaxonomia() {
        return taxonomia;
    }


    /**
     * Sets the taxonomia value for this ParamAvanzadoVO.
     * 
     * @param taxonomia
     */
    public void setTaxonomia(java.lang.String[] taxonomia) {
        this.taxonomia = taxonomia;
    }


    /**
     * Gets the idiomaNavegacion value for this ParamAvanzadoVO.
     * 
     * @return idiomaNavegacion
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParamAvanzadoVO.
     * 
     * @param idiomaNavegacion
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the idiomaBusqueda value for this ParamAvanzadoVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParamAvanzadoVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the formato value for this ParamAvanzadoVO.
     * 
     * @return formato
     */
    public java.lang.String[] getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ParamAvanzadoVO.
     * 
     * @param formato
     */
    public void setFormato(java.lang.String[] formato) {
        this.formato = formato;
    }


    /**
     * Gets the fechaPublicacion value for this ParamAvanzadoVO.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ParamAvanzadoVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the edad value for this ParamAvanzadoVO.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ParamAvanzadoVO.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the descripcion value for this ParamAvanzadoVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ParamAvanzadoVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the contexto value for this ParamAvanzadoVO.
     * 
     * @return contexto
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this ParamAvanzadoVO.
     * 
     * @param contexto
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the autor value for this ParamAvanzadoVO.
     * 
     * @return autor
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this ParamAvanzadoVO.
     * 
     * @param autor
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the identificadorODE value for this ParamAvanzadoVO.
     * 
     * @return identificadorODE   * Identifica un ode univocamente
     */
    public java.lang.String getIdentificadorODE() {
        return identificadorODE;
    }


    /**
     * Sets the identificadorODE value for this ParamAvanzadoVO.
     * 
     * @param identificadorODE   * Identifica un ode univocamente
     */
    public void setIdentificadorODE(java.lang.String identificadorODE) {
        this.identificadorODE = identificadorODE;
    }


    /**
     * Gets the fechaPublicacionRango value for this ParamAvanzadoVO.
     * 
     * @return fechaPublicacionRango
     */
    public java.lang.String getFechaPublicacionRango() {
        return fechaPublicacionRango;
    }


    /**
     * Sets the fechaPublicacionRango value for this ParamAvanzadoVO.
     * 
     * @param fechaPublicacionRango
     */
    public void setFechaPublicacionRango(java.lang.String fechaPublicacionRango) {
        this.fechaPublicacionRango = fechaPublicacionRango;
    }


    /**
     * Gets the nivelAgregacion value for this ParamAvanzadoVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.String[] getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ParamAvanzadoVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.String[] nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the destinatarios value for this ParamAvanzadoVO.
     * 
     * @return destinatarios
     */
    public java.lang.String getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this ParamAvanzadoVO.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(java.lang.String destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the keyword value for this ParamAvanzadoVO.
     * 
     * @return keyword
     */
    public java.lang.String getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this ParamAvanzadoVO.
     * 
     * @param keyword
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the ambito value for this ParamAvanzadoVO.
     * 
     * @return ambito
     */
    public java.lang.String getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this ParamAvanzadoVO.
     * 
     * @param ambito
     */
    public void setAmbito(java.lang.String ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the busquedaSimpleAvanzada value for this ParamAvanzadoVO.
     * 
     * @return busquedaSimpleAvanzada
     */
    public java.lang.String getBusquedaSimpleAvanzada() {
        return busquedaSimpleAvanzada;
    }


    /**
     * Sets the busquedaSimpleAvanzada value for this ParamAvanzadoVO.
     * 
     * @param busquedaSimpleAvanzada
     */
    public void setBusquedaSimpleAvanzada(java.lang.String busquedaSimpleAvanzada) {
        this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamAvanzadoVO)) return false;
        ParamAvanzadoVO other = (ParamAvanzadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.secuencia==null && other.getSecuencia()==null) || 
             (this.secuencia!=null &&
              this.secuencia.equals(other.getSecuencia()))) &&
            ((this.recurso==null && other.getRecurso()==null) || 
             (this.recurso!=null &&
              this.recurso.equals(other.getRecurso()))) &&
            ((this.procesoCognitivo==null && other.getProcesoCognitivo()==null) || 
             (this.procesoCognitivo!=null &&
              this.procesoCognitivo.equals(other.getProcesoCognitivo()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              this.palabrasClave.equals(other.getPalabrasClave()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.taxonomia==null && other.getTaxonomia()==null) || 
             (this.taxonomia!=null &&
              java.util.Arrays.equals(this.taxonomia, other.getTaxonomia()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              java.util.Arrays.equals(this.formato, other.getFormato()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.contexto==null && other.getContexto()==null) || 
             (this.contexto!=null &&
              this.contexto.equals(other.getContexto()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.identificadorODE==null && other.getIdentificadorODE()==null) || 
             (this.identificadorODE!=null &&
              this.identificadorODE.equals(other.getIdentificadorODE()))) &&
            ((this.fechaPublicacionRango==null && other.getFechaPublicacionRango()==null) || 
             (this.fechaPublicacionRango!=null &&
              this.fechaPublicacionRango.equals(other.getFechaPublicacionRango()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              java.util.Arrays.equals(this.nivelAgregacion, other.getNivelAgregacion()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              this.destinatarios.equals(other.getDestinatarios()))) &&
            ((this.keyword==null && other.getKeyword()==null) || 
             (this.keyword!=null &&
              this.keyword.equals(other.getKeyword()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              this.ambito.equals(other.getAmbito()))) &&
            ((this.busquedaSimpleAvanzada==null && other.getBusquedaSimpleAvanzada()==null) || 
             (this.busquedaSimpleAvanzada!=null &&
              this.busquedaSimpleAvanzada.equals(other.getBusquedaSimpleAvanzada())));
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
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getSecuencia() != null) {
            _hashCode += getSecuencia().hashCode();
        }
        if (getRecurso() != null) {
            _hashCode += getRecurso().hashCode();
        }
        if (getProcesoCognitivo() != null) {
            _hashCode += getProcesoCognitivo().hashCode();
        }
        if (getPalabrasClave() != null) {
            _hashCode += getPalabrasClave().hashCode();
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getTaxonomia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxonomia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxonomia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        if (getFormato() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFormato());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFormato(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getContexto() != null) {
            _hashCode += getContexto().hashCode();
        }
        if (getAutor() != null) {
            _hashCode += getAutor().hashCode();
        }
        if (getIdentificadorODE() != null) {
            _hashCode += getIdentificadorODE().hashCode();
        }
        if (getFechaPublicacionRango() != null) {
            _hashCode += getFechaPublicacionRango().hashCode();
        }
        if (getNivelAgregacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNivelAgregacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNivelAgregacion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDestinatarios() != null) {
            _hashCode += getDestinatarios().hashCode();
        }
        if (getKeyword() != null) {
            _hashCode += getKeyword().hashCode();
        }
        if (getAmbito() != null) {
            _hashCode += getAmbito().hashCode();
        }
        if (getBusquedaSimpleAvanzada() != null) {
            _hashCode += getBusquedaSimpleAvanzada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamAvanzadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamAvanzadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCognitivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "procesoCognitivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "taxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "identificadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacionRango");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "fechaPublicacionRango"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaSimpleAvanzada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaSimpleAvanzada"));
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
