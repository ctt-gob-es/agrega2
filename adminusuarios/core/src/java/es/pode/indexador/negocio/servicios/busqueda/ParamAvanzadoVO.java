/**
 * ParamAvanzadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * VO que alberga los parametros que acepta una busqueda avanzada.
 */
public class ParamAvanzadoVO  implements java.io.Serializable {
    /* Area curricular del ODE. */
    private java.lang.String areaCurricular;

    /* Asignatura del ODE. */
    private java.lang.String asignatura;

    /* Autor del ODE. */
    private java.lang.String autor;

    /* Ciclo educativo del ODE. */
    private java.lang.String cicloEducativo;

    /* Comunidades en las que se busca el ODE. */
    private java.lang.String comunidades;

    /* Se trata de la consulta del modulo de SQI. */
    private java.lang.String consulta;

    /* Contexto del ODE. */
    private java.lang.String contexto;

    /* Curso del ODE. */
    private java.lang.String curso;

    /* Descripcion del ODE. */
    private java.lang.String descripcion;

    /* Edad del ODE. */
    private java.lang.Integer edad;

    /* Etapa educativa del ODE. */
    private java.lang.String etapaEducativa;

    /* Fecha de publicacion del ODE. */
    private java.util.Calendar fechaPublicacion;

    /* Formato del ODE. */
    private java.lang.String formato;

    /* Idioma por el que se quiere configurar la busqueda. */
    private java.lang.String idioma;

    /* Numero de resultados maximo que se quieren devolver. */
    private java.lang.Integer numeroResultados;

    /* Proceso cognitivo del ODE. */
    private java.lang.String procesoCognitivo;

    /* Recurso del ODE. */
    private java.lang.String recurso;

    /* Con secuencia o sin secuencia. */
    private java.lang.Boolean secuencia;

    /* El nivel educativo del ODE. */
    private java.lang.String nivelEducativo;

    /* Titulo del ODE. */
    private java.lang.String titulo;

    /* Valoracion del ODE dentro de la plataforma. */
    private java.lang.Integer valoracion;

    /* Iddioma con el que el usuario navega por la aplicacion. */
    private java.lang.String idiomaNavegacion;

    /* Se trata de las palabras clave por las que se quiere buscar. */
    private java.lang.String palabrasClave;

    public ParamAvanzadoVO() {
    }

    public ParamAvanzadoVO(
           java.lang.String areaCurricular,
           java.lang.String asignatura,
           java.lang.String autor,
           java.lang.String cicloEducativo,
           java.lang.String comunidades,
           java.lang.String consulta,
           java.lang.String contexto,
           java.lang.String curso,
           java.lang.String descripcion,
           java.lang.Integer edad,
           java.lang.String etapaEducativa,
           java.util.Calendar fechaPublicacion,
           java.lang.String formato,
           java.lang.String idioma,
           java.lang.Integer numeroResultados,
           java.lang.String procesoCognitivo,
           java.lang.String recurso,
           java.lang.Boolean secuencia,
           java.lang.String nivelEducativo,
           java.lang.String titulo,
           java.lang.Integer valoracion,
           java.lang.String idiomaNavegacion,
           java.lang.String palabrasClave) {
           this.areaCurricular = areaCurricular;
           this.asignatura = asignatura;
           this.autor = autor;
           this.cicloEducativo = cicloEducativo;
           this.comunidades = comunidades;
           this.consulta = consulta;
           this.contexto = contexto;
           this.curso = curso;
           this.descripcion = descripcion;
           this.edad = edad;
           this.etapaEducativa = etapaEducativa;
           this.fechaPublicacion = fechaPublicacion;
           this.formato = formato;
           this.idioma = idioma;
           this.numeroResultados = numeroResultados;
           this.procesoCognitivo = procesoCognitivo;
           this.recurso = recurso;
           this.secuencia = secuencia;
           this.nivelEducativo = nivelEducativo;
           this.titulo = titulo;
           this.valoracion = valoracion;
           this.idiomaNavegacion = idiomaNavegacion;
           this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the areaCurricular value for this ParamAvanzadoVO.
     * 
     * @return areaCurricular   * Area curricular del ODE.
     */
    public java.lang.String getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this ParamAvanzadoVO.
     * 
     * @param areaCurricular   * Area curricular del ODE.
     */
    public void setAreaCurricular(java.lang.String areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the asignatura value for this ParamAvanzadoVO.
     * 
     * @return asignatura   * Asignatura del ODE.
     */
    public java.lang.String getAsignatura() {
        return asignatura;
    }


    /**
     * Sets the asignatura value for this ParamAvanzadoVO.
     * 
     * @param asignatura   * Asignatura del ODE.
     */
    public void setAsignatura(java.lang.String asignatura) {
        this.asignatura = asignatura;
    }


    /**
     * Gets the autor value for this ParamAvanzadoVO.
     * 
     * @return autor   * Autor del ODE.
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this ParamAvanzadoVO.
     * 
     * @param autor   * Autor del ODE.
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the cicloEducativo value for this ParamAvanzadoVO.
     * 
     * @return cicloEducativo   * Ciclo educativo del ODE.
     */
    public java.lang.String getCicloEducativo() {
        return cicloEducativo;
    }


    /**
     * Sets the cicloEducativo value for this ParamAvanzadoVO.
     * 
     * @param cicloEducativo   * Ciclo educativo del ODE.
     */
    public void setCicloEducativo(java.lang.String cicloEducativo) {
        this.cicloEducativo = cicloEducativo;
    }


    /**
     * Gets the comunidades value for this ParamAvanzadoVO.
     * 
     * @return comunidades   * Comunidades en las que se busca el ODE.
     */
    public java.lang.String getComunidades() {
        return comunidades;
    }


    /**
     * Sets the comunidades value for this ParamAvanzadoVO.
     * 
     * @param comunidades   * Comunidades en las que se busca el ODE.
     */
    public void setComunidades(java.lang.String comunidades) {
        this.comunidades = comunidades;
    }


    /**
     * Gets the consulta value for this ParamAvanzadoVO.
     * 
     * @return consulta   * Se trata de la consulta del modulo de SQI.
     */
    public java.lang.String getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this ParamAvanzadoVO.
     * 
     * @param consulta   * Se trata de la consulta del modulo de SQI.
     */
    public void setConsulta(java.lang.String consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the contexto value for this ParamAvanzadoVO.
     * 
     * @return contexto   * Contexto del ODE.
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this ParamAvanzadoVO.
     * 
     * @param contexto   * Contexto del ODE.
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the curso value for this ParamAvanzadoVO.
     * 
     * @return curso   * Curso del ODE.
     */
    public java.lang.String getCurso() {
        return curso;
    }


    /**
     * Sets the curso value for this ParamAvanzadoVO.
     * 
     * @param curso   * Curso del ODE.
     */
    public void setCurso(java.lang.String curso) {
        this.curso = curso;
    }


    /**
     * Gets the descripcion value for this ParamAvanzadoVO.
     * 
     * @return descripcion   * Descripcion del ODE.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ParamAvanzadoVO.
     * 
     * @param descripcion   * Descripcion del ODE.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the edad value for this ParamAvanzadoVO.
     * 
     * @return edad   * Edad del ODE.
     */
    public java.lang.Integer getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ParamAvanzadoVO.
     * 
     * @param edad   * Edad del ODE.
     */
    public void setEdad(java.lang.Integer edad) {
        this.edad = edad;
    }


    /**
     * Gets the etapaEducativa value for this ParamAvanzadoVO.
     * 
     * @return etapaEducativa   * Etapa educativa del ODE.
     */
    public java.lang.String getEtapaEducativa() {
        return etapaEducativa;
    }


    /**
     * Sets the etapaEducativa value for this ParamAvanzadoVO.
     * 
     * @param etapaEducativa   * Etapa educativa del ODE.
     */
    public void setEtapaEducativa(java.lang.String etapaEducativa) {
        this.etapaEducativa = etapaEducativa;
    }


    /**
     * Gets the fechaPublicacion value for this ParamAvanzadoVO.
     * 
     * @return fechaPublicacion   * Fecha de publicacion del ODE.
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ParamAvanzadoVO.
     * 
     * @param fechaPublicacion   * Fecha de publicacion del ODE.
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the formato value for this ParamAvanzadoVO.
     * 
     * @return formato   * Formato del ODE.
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ParamAvanzadoVO.
     * 
     * @param formato   * Formato del ODE.
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the idioma value for this ParamAvanzadoVO.
     * 
     * @return idioma   * Idioma por el que se quiere configurar la busqueda.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ParamAvanzadoVO.
     * 
     * @param idioma   * Idioma por el que se quiere configurar la busqueda.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the numeroResultados value for this ParamAvanzadoVO.
     * 
     * @return numeroResultados   * Numero de resultados maximo que se quieren devolver.
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParamAvanzadoVO.
     * 
     * @param numeroResultados   * Numero de resultados maximo que se quieren devolver.
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the procesoCognitivo value for this ParamAvanzadoVO.
     * 
     * @return procesoCognitivo   * Proceso cognitivo del ODE.
     */
    public java.lang.String getProcesoCognitivo() {
        return procesoCognitivo;
    }


    /**
     * Sets the procesoCognitivo value for this ParamAvanzadoVO.
     * 
     * @param procesoCognitivo   * Proceso cognitivo del ODE.
     */
    public void setProcesoCognitivo(java.lang.String procesoCognitivo) {
        this.procesoCognitivo = procesoCognitivo;
    }


    /**
     * Gets the recurso value for this ParamAvanzadoVO.
     * 
     * @return recurso   * Recurso del ODE.
     */
    public java.lang.String getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this ParamAvanzadoVO.
     * 
     * @param recurso   * Recurso del ODE.
     */
    public void setRecurso(java.lang.String recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the secuencia value for this ParamAvanzadoVO.
     * 
     * @return secuencia   * Con secuencia o sin secuencia.
     */
    public java.lang.Boolean getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this ParamAvanzadoVO.
     * 
     * @param secuencia   * Con secuencia o sin secuencia.
     */
    public void setSecuencia(java.lang.Boolean secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the nivelEducativo value for this ParamAvanzadoVO.
     * 
     * @return nivelEducativo   * El nivel educativo del ODE.
     */
    public java.lang.String getNivelEducativo() {
        return nivelEducativo;
    }


    /**
     * Sets the nivelEducativo value for this ParamAvanzadoVO.
     * 
     * @param nivelEducativo   * El nivel educativo del ODE.
     */
    public void setNivelEducativo(java.lang.String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }


    /**
     * Gets the titulo value for this ParamAvanzadoVO.
     * 
     * @return titulo   * Titulo del ODE.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ParamAvanzadoVO.
     * 
     * @param titulo   * Titulo del ODE.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the valoracion value for this ParamAvanzadoVO.
     * 
     * @return valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public java.lang.Integer getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ParamAvanzadoVO.
     * 
     * @param valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public void setValoracion(java.lang.Integer valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the idiomaNavegacion value for this ParamAvanzadoVO.
     * 
     * @return idiomaNavegacion   * Iddioma con el que el usuario navega por la aplicacion.
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParamAvanzadoVO.
     * 
     * @param idiomaNavegacion   * Iddioma con el que el usuario navega por la aplicacion.
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the palabrasClave value for this ParamAvanzadoVO.
     * 
     * @return palabrasClave   * Se trata de las palabras clave por las que se quiere buscar.
     */
    public java.lang.String getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this ParamAvanzadoVO.
     * 
     * @param palabrasClave   * Se trata de las palabras clave por las que se quiere buscar.
     */
    public void setPalabrasClave(java.lang.String palabrasClave) {
        this.palabrasClave = palabrasClave;
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
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              this.areaCurricular.equals(other.getAreaCurricular()))) &&
            ((this.asignatura==null && other.getAsignatura()==null) || 
             (this.asignatura!=null &&
              this.asignatura.equals(other.getAsignatura()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.cicloEducativo==null && other.getCicloEducativo()==null) || 
             (this.cicloEducativo!=null &&
              this.cicloEducativo.equals(other.getCicloEducativo()))) &&
            ((this.comunidades==null && other.getComunidades()==null) || 
             (this.comunidades!=null &&
              this.comunidades.equals(other.getComunidades()))) &&
            ((this.consulta==null && other.getConsulta()==null) || 
             (this.consulta!=null &&
              this.consulta.equals(other.getConsulta()))) &&
            ((this.contexto==null && other.getContexto()==null) || 
             (this.contexto!=null &&
              this.contexto.equals(other.getContexto()))) &&
            ((this.curso==null && other.getCurso()==null) || 
             (this.curso!=null &&
              this.curso.equals(other.getCurso()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.etapaEducativa==null && other.getEtapaEducativa()==null) || 
             (this.etapaEducativa!=null &&
              this.etapaEducativa.equals(other.getEtapaEducativa()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.procesoCognitivo==null && other.getProcesoCognitivo()==null) || 
             (this.procesoCognitivo!=null &&
              this.procesoCognitivo.equals(other.getProcesoCognitivo()))) &&
            ((this.recurso==null && other.getRecurso()==null) || 
             (this.recurso!=null &&
              this.recurso.equals(other.getRecurso()))) &&
            ((this.secuencia==null && other.getSecuencia()==null) || 
             (this.secuencia!=null &&
              this.secuencia.equals(other.getSecuencia()))) &&
            ((this.nivelEducativo==null && other.getNivelEducativo()==null) || 
             (this.nivelEducativo!=null &&
              this.nivelEducativo.equals(other.getNivelEducativo()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              this.palabrasClave.equals(other.getPalabrasClave())));
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
        if (getAreaCurricular() != null) {
            _hashCode += getAreaCurricular().hashCode();
        }
        if (getAsignatura() != null) {
            _hashCode += getAsignatura().hashCode();
        }
        if (getAutor() != null) {
            _hashCode += getAutor().hashCode();
        }
        if (getCicloEducativo() != null) {
            _hashCode += getCicloEducativo().hashCode();
        }
        if (getComunidades() != null) {
            _hashCode += getComunidades().hashCode();
        }
        if (getConsulta() != null) {
            _hashCode += getConsulta().hashCode();
        }
        if (getContexto() != null) {
            _hashCode += getContexto().hashCode();
        }
        if (getCurso() != null) {
            _hashCode += getCurso().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getEtapaEducativa() != null) {
            _hashCode += getEtapaEducativa().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getProcesoCognitivo() != null) {
            _hashCode += getProcesoCognitivo().hashCode();
        }
        if (getRecurso() != null) {
            _hashCode += getRecurso().hashCode();
        }
        if (getSecuencia() != null) {
            _hashCode += getSecuencia().hashCode();
        }
        if (getNivelEducativo() != null) {
            _hashCode += getNivelEducativo().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getPalabrasClave() != null) {
            _hashCode += getPalabrasClave().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamAvanzadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ParamAvanzadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asignatura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "asignatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cicloEducativo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "cicloEducativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "comunidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "curso"));
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
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etapaEducativa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "etapaEducativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCognitivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "procesoCognitivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelEducativo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nivelEducativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "titulo"));
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
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "palabrasClave"));
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
