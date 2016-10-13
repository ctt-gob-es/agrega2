/**
 * ResultadoRecordVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Clase de objeto de valor que almacena las caracteristicas
 *                         extraibles de cada registro del repositorio.
 * Esta directamente
 *                         relacionado con el formato Dublin Core y los
 * valores almacenados
 *                         en el indice de la plataforma.
 *                         En este VO no estamos recogiendo la siguiente
 * información:
 *                         creator, contributor, subject, source y descripcion
 */
public class ResultadoRecordVO  implements java.io.Serializable {
    /* Título del ode */
    private java.lang.String titulo;

    /* Fecha publicación del recurso dentro del repositorio. */
    private java.lang.String fecha;

    /* Idioma del registro. */
    private java.lang.String idioma;

    /* Tipo del registro: texto, video,.... */
    private java.lang.String[] tipo;

    /* Autores del recurso dentro del repositorio. */
    private java.lang.String[] autores;

    /* Ambito del registro. */
    private java.lang.String[] ambito;

    /* Descripcion del recurso. */
    private java.lang.String descripcion;

    /* Derechos del registro dentro del repositorio. */
    private java.lang.String[] derechos;

    /* Identificador del recurso dentro del repositorio. */
    private java.lang.String[] identificador;

    /* Fornatos de los que esta compuesto el recurso. */
    private java.lang.String[] formatos;

    /* Tema del registro (palabras clave del ODE). */
    private java.lang.String[] tema;

    private java.lang.String[] relacion;

    private java.lang.String[] publicador;

    private java.lang.String[] fuente;

    private java.lang.String[] contribuidor;

    /* Identificador del repositorio con la sintaxis: scheme ":"
     *                                 namespace-identifier ":" local-identifier. */
    private java.lang.String idRepositorio;

    public ResultadoRecordVO() {
    }

    public ResultadoRecordVO(
           java.lang.String titulo,
           java.lang.String fecha,
           java.lang.String idioma,
           java.lang.String[] tipo,
           java.lang.String[] autores,
           java.lang.String[] ambito,
           java.lang.String descripcion,
           java.lang.String[] derechos,
           java.lang.String[] identificador,
           java.lang.String[] formatos,
           java.lang.String[] tema,
           java.lang.String[] relacion,
           java.lang.String[] publicador,
           java.lang.String[] fuente,
           java.lang.String[] contribuidor,
           java.lang.String idRepositorio) {
           this.titulo = titulo;
           this.fecha = fecha;
           this.idioma = idioma;
           this.tipo = tipo;
           this.autores = autores;
           this.ambito = ambito;
           this.descripcion = descripcion;
           this.derechos = derechos;
           this.identificador = identificador;
           this.formatos = formatos;
           this.tema = tema;
           this.relacion = relacion;
           this.publicador = publicador;
           this.fuente = fuente;
           this.contribuidor = contribuidor;
           this.idRepositorio = idRepositorio;
    }


    /**
     * Gets the titulo value for this ResultadoRecordVO.
     * 
     * @return titulo   * Título del ode
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ResultadoRecordVO.
     * 
     * @param titulo   * Título del ode
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the fecha value for this ResultadoRecordVO.
     * 
     * @return fecha   * Fecha publicación del recurso dentro del repositorio.
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ResultadoRecordVO.
     * 
     * @param fecha   * Fecha publicación del recurso dentro del repositorio.
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the idioma value for this ResultadoRecordVO.
     * 
     * @return idioma   * Idioma del registro.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ResultadoRecordVO.
     * 
     * @param idioma   * Idioma del registro.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the tipo value for this ResultadoRecordVO.
     * 
     * @return tipo   * Tipo del registro: texto, video,....
     */
    public java.lang.String[] getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this ResultadoRecordVO.
     * 
     * @param tipo   * Tipo del registro: texto, video,....
     */
    public void setTipo(java.lang.String[] tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the autores value for this ResultadoRecordVO.
     * 
     * @return autores   * Autores del recurso dentro del repositorio.
     */
    public java.lang.String[] getAutores() {
        return autores;
    }


    /**
     * Sets the autores value for this ResultadoRecordVO.
     * 
     * @param autores   * Autores del recurso dentro del repositorio.
     */
    public void setAutores(java.lang.String[] autores) {
        this.autores = autores;
    }


    /**
     * Gets the ambito value for this ResultadoRecordVO.
     * 
     * @return ambito   * Ambito del registro.
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this ResultadoRecordVO.
     * 
     * @param ambito   * Ambito del registro.
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the descripcion value for this ResultadoRecordVO.
     * 
     * @return descripcion   * Descripcion del recurso.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ResultadoRecordVO.
     * 
     * @param descripcion   * Descripcion del recurso.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the derechos value for this ResultadoRecordVO.
     * 
     * @return derechos   * Derechos del registro dentro del repositorio.
     */
    public java.lang.String[] getDerechos() {
        return derechos;
    }


    /**
     * Sets the derechos value for this ResultadoRecordVO.
     * 
     * @param derechos   * Derechos del registro dentro del repositorio.
     */
    public void setDerechos(java.lang.String[] derechos) {
        this.derechos = derechos;
    }


    /**
     * Gets the identificador value for this ResultadoRecordVO.
     * 
     * @return identificador   * Identificador del recurso dentro del repositorio.
     */
    public java.lang.String[] getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ResultadoRecordVO.
     * 
     * @param identificador   * Identificador del recurso dentro del repositorio.
     */
    public void setIdentificador(java.lang.String[] identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the formatos value for this ResultadoRecordVO.
     * 
     * @return formatos   * Fornatos de los que esta compuesto el recurso.
     */
    public java.lang.String[] getFormatos() {
        return formatos;
    }


    /**
     * Sets the formatos value for this ResultadoRecordVO.
     * 
     * @param formatos   * Fornatos de los que esta compuesto el recurso.
     */
    public void setFormatos(java.lang.String[] formatos) {
        this.formatos = formatos;
    }


    /**
     * Gets the tema value for this ResultadoRecordVO.
     * 
     * @return tema   * Tema del registro (palabras clave del ODE).
     */
    public java.lang.String[] getTema() {
        return tema;
    }


    /**
     * Sets the tema value for this ResultadoRecordVO.
     * 
     * @param tema   * Tema del registro (palabras clave del ODE).
     */
    public void setTema(java.lang.String[] tema) {
        this.tema = tema;
    }


    /**
     * Gets the relacion value for this ResultadoRecordVO.
     * 
     * @return relacion
     */
    public java.lang.String[] getRelacion() {
        return relacion;
    }


    /**
     * Sets the relacion value for this ResultadoRecordVO.
     * 
     * @param relacion
     */
    public void setRelacion(java.lang.String[] relacion) {
        this.relacion = relacion;
    }


    /**
     * Gets the publicador value for this ResultadoRecordVO.
     * 
     * @return publicador
     */
    public java.lang.String[] getPublicador() {
        return publicador;
    }


    /**
     * Sets the publicador value for this ResultadoRecordVO.
     * 
     * @param publicador
     */
    public void setPublicador(java.lang.String[] publicador) {
        this.publicador = publicador;
    }


    /**
     * Gets the fuente value for this ResultadoRecordVO.
     * 
     * @return fuente
     */
    public java.lang.String[] getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this ResultadoRecordVO.
     * 
     * @param fuente
     */
    public void setFuente(java.lang.String[] fuente) {
        this.fuente = fuente;
    }


    /**
     * Gets the contribuidor value for this ResultadoRecordVO.
     * 
     * @return contribuidor
     */
    public java.lang.String[] getContribuidor() {
        return contribuidor;
    }


    /**
     * Sets the contribuidor value for this ResultadoRecordVO.
     * 
     * @param contribuidor
     */
    public void setContribuidor(java.lang.String[] contribuidor) {
        this.contribuidor = contribuidor;
    }


    /**
     * Gets the idRepositorio value for this ResultadoRecordVO.
     * 
     * @return idRepositorio   * Identificador del repositorio con la sintaxis: scheme ":"
     *                                 namespace-identifier ":" local-identifier.
     */
    public java.lang.String getIdRepositorio() {
        return idRepositorio;
    }


    /**
     * Sets the idRepositorio value for this ResultadoRecordVO.
     * 
     * @param idRepositorio   * Identificador del repositorio con la sintaxis: scheme ":"
     *                                 namespace-identifier ":" local-identifier.
     */
    public void setIdRepositorio(java.lang.String idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoRecordVO)) return false;
        ResultadoRecordVO other = (ResultadoRecordVO) obj;
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
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              java.util.Arrays.equals(this.tipo, other.getTipo()))) &&
            ((this.autores==null && other.getAutores()==null) || 
             (this.autores!=null &&
              java.util.Arrays.equals(this.autores, other.getAutores()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              java.util.Arrays.equals(this.ambito, other.getAmbito()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.derechos==null && other.getDerechos()==null) || 
             (this.derechos!=null &&
              java.util.Arrays.equals(this.derechos, other.getDerechos()))) &&
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              java.util.Arrays.equals(this.identificador, other.getIdentificador()))) &&
            ((this.formatos==null && other.getFormatos()==null) || 
             (this.formatos!=null &&
              java.util.Arrays.equals(this.formatos, other.getFormatos()))) &&
            ((this.tema==null && other.getTema()==null) || 
             (this.tema!=null &&
              java.util.Arrays.equals(this.tema, other.getTema()))) &&
            ((this.relacion==null && other.getRelacion()==null) || 
             (this.relacion!=null &&
              java.util.Arrays.equals(this.relacion, other.getRelacion()))) &&
            ((this.publicador==null && other.getPublicador()==null) || 
             (this.publicador!=null &&
              java.util.Arrays.equals(this.publicador, other.getPublicador()))) &&
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              java.util.Arrays.equals(this.fuente, other.getFuente()))) &&
            ((this.contribuidor==null && other.getContribuidor()==null) || 
             (this.contribuidor!=null &&
              java.util.Arrays.equals(this.contribuidor, other.getContribuidor()))) &&
            ((this.idRepositorio==null && other.getIdRepositorio()==null) || 
             (this.idRepositorio!=null &&
              this.idRepositorio.equals(other.getIdRepositorio())));
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
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getTipo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTipo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTipo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutores(), i);
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getDerechos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDerechos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDerechos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdentificador() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentificador());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentificador(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getTema() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTema());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTema(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRelacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRelacion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPublicador() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPublicador());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPublicador(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFuente() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFuente());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFuente(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContribuidor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContribuidor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContribuidor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdRepositorio() != null) {
            _hashCode += getIdRepositorio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoRecordVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ResultadoRecordVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "autores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "derechos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "formatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "tema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "relacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "publicador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fuente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuidor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "contribuidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRepositorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "idRepositorio"));
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
