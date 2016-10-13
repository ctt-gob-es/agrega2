/**
 * ValoresBusquedaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ValoresBusquedaVO  implements java.io.Serializable {
    /* Valoracion del ODE dentro de la plataforma. */
    private java.lang.Float valoracion;

    /* Titulo del ODE. */
    private java.lang.String titulo;

    /* Tipo de recurso. Hay un tipo de recurso por cada entrada del
     * array. */
    private java.lang.String[] tipoRecurso;

    /* Valor del ranking dentro de la busqueda realizada. */
    private java.lang.Integer ranking;

    /* El taxonomia del ODE. */
    private java.lang.String[] areaCurricular;

    /* Curso al que pertenece el ODE. */
    private java.lang.String curso;

    /* Identificador alfanumerico del ODE. */
    private java.lang.String id;

    private java.lang.String numeroODE;

    private java.lang.String urlImagen;

    private java.lang.String nodo;

    private java.lang.Integer nivelAgregacion;

    private java.lang.String[] formato;

    private java.lang.String nivelAgregacionTexto;

    private java.lang.String[] ambito;

    private java.lang.Boolean esVisualizable;

    private java.lang.String descripcion;

    private java.lang.String fechaPublicacion;

    /* hora de publicación del ODE */
    private java.lang.String horaPublicacion;

    private java.lang.Boolean conSecuencia;

    public ValoresBusquedaVO() {
    }

    public ValoresBusquedaVO(
           java.lang.Float valoracion,
           java.lang.String titulo,
           java.lang.String[] tipoRecurso,
           java.lang.Integer ranking,
           java.lang.String[] areaCurricular,
           java.lang.String curso,
           java.lang.String id,
           java.lang.String numeroODE,
           java.lang.String urlImagen,
           java.lang.String nodo,
           java.lang.Integer nivelAgregacion,
           java.lang.String[] formato,
           java.lang.String nivelAgregacionTexto,
           java.lang.String[] ambito,
           java.lang.Boolean esVisualizable,
           java.lang.String descripcion,
           java.lang.String fechaPublicacion,
           java.lang.String horaPublicacion,
           java.lang.Boolean conSecuencia) {
           this.valoracion = valoracion;
           this.titulo = titulo;
           this.tipoRecurso = tipoRecurso;
           this.ranking = ranking;
           this.areaCurricular = areaCurricular;
           this.curso = curso;
           this.id = id;
           this.numeroODE = numeroODE;
           this.urlImagen = urlImagen;
           this.nodo = nodo;
           this.nivelAgregacion = nivelAgregacion;
           this.formato = formato;
           this.nivelAgregacionTexto = nivelAgregacionTexto;
           this.ambito = ambito;
           this.esVisualizable = esVisualizable;
           this.descripcion = descripcion;
           this.fechaPublicacion = fechaPublicacion;
           this.horaPublicacion = horaPublicacion;
           this.conSecuencia = conSecuencia;
    }


    /**
     * Gets the valoracion value for this ValoresBusquedaVO.
     * 
     * @return valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public java.lang.Float getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ValoresBusquedaVO.
     * 
     * @param valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public void setValoracion(java.lang.Float valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the titulo value for this ValoresBusquedaVO.
     * 
     * @return titulo   * Titulo del ODE.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ValoresBusquedaVO.
     * 
     * @param titulo   * Titulo del ODE.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the tipoRecurso value for this ValoresBusquedaVO.
     * 
     * @return tipoRecurso   * Tipo de recurso. Hay un tipo de recurso por cada entrada del
     * array.
     */
    public java.lang.String[] getTipoRecurso() {
        return tipoRecurso;
    }


    /**
     * Sets the tipoRecurso value for this ValoresBusquedaVO.
     * 
     * @param tipoRecurso   * Tipo de recurso. Hay un tipo de recurso por cada entrada del
     * array.
     */
    public void setTipoRecurso(java.lang.String[] tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    /**
     * Gets the ranking value for this ValoresBusquedaVO.
     * 
     * @return ranking   * Valor del ranking dentro de la busqueda realizada.
     */
    public java.lang.Integer getRanking() {
        return ranking;
    }


    /**
     * Sets the ranking value for this ValoresBusquedaVO.
     * 
     * @param ranking   * Valor del ranking dentro de la busqueda realizada.
     */
    public void setRanking(java.lang.Integer ranking) {
        this.ranking = ranking;
    }


    /**
     * Gets the areaCurricular value for this ValoresBusquedaVO.
     * 
     * @return areaCurricular   * El taxonomia del ODE.
     */
    public java.lang.String[] getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this ValoresBusquedaVO.
     * 
     * @param areaCurricular   * El taxonomia del ODE.
     */
    public void setAreaCurricular(java.lang.String[] areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the curso value for this ValoresBusquedaVO.
     * 
     * @return curso   * Curso al que pertenece el ODE.
     */
    public java.lang.String getCurso() {
        return curso;
    }


    /**
     * Sets the curso value for this ValoresBusquedaVO.
     * 
     * @param curso   * Curso al que pertenece el ODE.
     */
    public void setCurso(java.lang.String curso) {
        this.curso = curso;
    }


    /**
     * Gets the id value for this ValoresBusquedaVO.
     * 
     * @return id   * Identificador alfanumerico del ODE.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ValoresBusquedaVO.
     * 
     * @param id   * Identificador alfanumerico del ODE.
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the numeroODE value for this ValoresBusquedaVO.
     * 
     * @return numeroODE
     */
    public java.lang.String getNumeroODE() {
        return numeroODE;
    }


    /**
     * Sets the numeroODE value for this ValoresBusquedaVO.
     * 
     * @param numeroODE
     */
    public void setNumeroODE(java.lang.String numeroODE) {
        this.numeroODE = numeroODE;
    }


    /**
     * Gets the urlImagen value for this ValoresBusquedaVO.
     * 
     * @return urlImagen
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this ValoresBusquedaVO.
     * 
     * @param urlImagen
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /**
     * Gets the nodo value for this ValoresBusquedaVO.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this ValoresBusquedaVO.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the nivelAgregacion value for this ValoresBusquedaVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.Integer getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ValoresBusquedaVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.Integer nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the formato value for this ValoresBusquedaVO.
     * 
     * @return formato
     */
    public java.lang.String[] getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ValoresBusquedaVO.
     * 
     * @param formato
     */
    public void setFormato(java.lang.String[] formato) {
        this.formato = formato;
    }


    /**
     * Gets the nivelAgregacionTexto value for this ValoresBusquedaVO.
     * 
     * @return nivelAgregacionTexto
     */
    public java.lang.String getNivelAgregacionTexto() {
        return nivelAgregacionTexto;
    }


    /**
     * Sets the nivelAgregacionTexto value for this ValoresBusquedaVO.
     * 
     * @param nivelAgregacionTexto
     */
    public void setNivelAgregacionTexto(java.lang.String nivelAgregacionTexto) {
        this.nivelAgregacionTexto = nivelAgregacionTexto;
    }


    /**
     * Gets the ambito value for this ValoresBusquedaVO.
     * 
     * @return ambito
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this ValoresBusquedaVO.
     * 
     * @param ambito
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the esVisualizable value for this ValoresBusquedaVO.
     * 
     * @return esVisualizable
     */
    public java.lang.Boolean getEsVisualizable() {
        return esVisualizable;
    }


    /**
     * Sets the esVisualizable value for this ValoresBusquedaVO.
     * 
     * @param esVisualizable
     */
    public void setEsVisualizable(java.lang.Boolean esVisualizable) {
        this.esVisualizable = esVisualizable;
    }


    /**
     * Gets the descripcion value for this ValoresBusquedaVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ValoresBusquedaVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaPublicacion value for this ValoresBusquedaVO.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ValoresBusquedaVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the horaPublicacion value for this ValoresBusquedaVO.
     * 
     * @return horaPublicacion   * hora de publicación del ODE
     */
    public java.lang.String getHoraPublicacion() {
        return horaPublicacion;
    }


    /**
     * Sets the horaPublicacion value for this ValoresBusquedaVO.
     * 
     * @param horaPublicacion   * hora de publicación del ODE
     */
    public void setHoraPublicacion(java.lang.String horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }


    /**
     * Gets the conSecuencia value for this ValoresBusquedaVO.
     * 
     * @return conSecuencia
     */
    public java.lang.Boolean getConSecuencia() {
        return conSecuencia;
    }


    /**
     * Sets the conSecuencia value for this ValoresBusquedaVO.
     * 
     * @param conSecuencia
     */
    public void setConSecuencia(java.lang.Boolean conSecuencia) {
        this.conSecuencia = conSecuencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValoresBusquedaVO)) return false;
        ValoresBusquedaVO other = (ValoresBusquedaVO) obj;
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
            ((this.tipoRecurso==null && other.getTipoRecurso()==null) || 
             (this.tipoRecurso!=null &&
              java.util.Arrays.equals(this.tipoRecurso, other.getTipoRecurso()))) &&
            ((this.ranking==null && other.getRanking()==null) || 
             (this.ranking!=null &&
              this.ranking.equals(other.getRanking()))) &&
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              java.util.Arrays.equals(this.areaCurricular, other.getAreaCurricular()))) &&
            ((this.curso==null && other.getCurso()==null) || 
             (this.curso!=null &&
              this.curso.equals(other.getCurso()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.numeroODE==null && other.getNumeroODE()==null) || 
             (this.numeroODE!=null &&
              this.numeroODE.equals(other.getNumeroODE()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              java.util.Arrays.equals(this.formato, other.getFormato()))) &&
            ((this.nivelAgregacionTexto==null && other.getNivelAgregacionTexto()==null) || 
             (this.nivelAgregacionTexto!=null &&
              this.nivelAgregacionTexto.equals(other.getNivelAgregacionTexto()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              java.util.Arrays.equals(this.ambito, other.getAmbito()))) &&
            ((this.esVisualizable==null && other.getEsVisualizable()==null) || 
             (this.esVisualizable!=null &&
              this.esVisualizable.equals(other.getEsVisualizable()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.horaPublicacion==null && other.getHoraPublicacion()==null) || 
             (this.horaPublicacion!=null &&
              this.horaPublicacion.equals(other.getHoraPublicacion()))) &&
            ((this.conSecuencia==null && other.getConSecuencia()==null) || 
             (this.conSecuencia!=null &&
              this.conSecuencia.equals(other.getConSecuencia())));
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
        if (getRanking() != null) {
            _hashCode += getRanking().hashCode();
        }
        if (getAreaCurricular() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAreaCurricular());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAreaCurricular(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurso() != null) {
            _hashCode += getCurso().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNumeroODE() != null) {
            _hashCode += getNumeroODE().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
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
        if (getNivelAgregacionTexto() != null) {
            _hashCode += getNivelAgregacionTexto().hashCode();
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
        if (getEsVisualizable() != null) {
            _hashCode += getEsVisualizable().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getHoraPublicacion() != null) {
            _hashCode += getHoraPublicacion().hashCode();
        }
        if (getConSecuencia() != null) {
            _hashCode += getConSecuencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValoresBusquedaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ValoresBusquedaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tipoRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ranking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ranking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "curso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "urlImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacionTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "nivelAgregacionTexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esVisualizable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "esVisualizable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "horaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conSecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "conSecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
