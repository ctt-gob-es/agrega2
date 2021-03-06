/**
 * MetadatoBasicoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class MetadatoBasicoVO  implements java.io.Serializable {
    /* Licencias del ODE. Una licencia por entrada en el array. */
    private java.lang.String[] licencias;

    /* Titulo del ODE */
    private java.lang.String titulo;

    /* Descripcion del ODE. */
    private java.lang.String descripcion;

    /* Formato del ODE. */
    private java.lang.String[] formato;

    /* Los destinatarios del ODE. Un destinatario por cada entrada
     * del
     *                                 array. */
    private java.lang.String[] destinatarios;

    /* Idioma del ODE. Un idioma por cada entrada del array */
    private java.lang.String idioma;

    /* Ambito de aplicacion del ODE. Un ambito por cada entrada del
     * string. */
    private java.lang.String[] ambito;

    /* Valoracion del ODE. */
    private java.lang.Float valoracion;

    /* Localizador del ODE. */
    private java.lang.String localizadorODE;

    /* Identificador alfanumerico de un ODE. */
    private java.lang.String identificadorODE;

    private java.lang.String imagen;

    private java.lang.Integer nivelAgregacion;

    private java.lang.String tamanio;

    /* Indica si el ode tiene secuencia o no. */
    private java.lang.Boolean conSinSecuencia;

    private java.lang.String fechaPublicacion;

    private java.lang.String horaPublicacion;

    /* Autores del ODE */
    private java.lang.String[] autor;

    private java.lang.String[] areaCurricular;

    private java.lang.String[] palabrasClave;

    private java.lang.String[] tipoRecurso;

    private java.lang.String[] orientacionDidactica;

    private es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO[] contribuciones;

    public MetadatoBasicoVO() {
    }

    public MetadatoBasicoVO(
           java.lang.String[] licencias,
           java.lang.String titulo,
           java.lang.String descripcion,
           java.lang.String[] formato,
           java.lang.String[] destinatarios,
           java.lang.String idioma,
           java.lang.String[] ambito,
           java.lang.Float valoracion,
           java.lang.String localizadorODE,
           java.lang.String identificadorODE,
           java.lang.String imagen,
           java.lang.Integer nivelAgregacion,
           java.lang.String tamanio,
           java.lang.Boolean conSinSecuencia,
           java.lang.String fechaPublicacion,
           java.lang.String horaPublicacion,
           java.lang.String[] autor,
           java.lang.String[] areaCurricular,
           java.lang.String[] palabrasClave,
           java.lang.String[] tipoRecurso,
           java.lang.String[] orientacionDidactica,
           es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO[] contribuciones) {
           this.licencias = licencias;
           this.titulo = titulo;
           this.descripcion = descripcion;
           this.formato = formato;
           this.destinatarios = destinatarios;
           this.idioma = idioma;
           this.ambito = ambito;
           this.valoracion = valoracion;
           this.localizadorODE = localizadorODE;
           this.identificadorODE = identificadorODE;
           this.imagen = imagen;
           this.nivelAgregacion = nivelAgregacion;
           this.tamanio = tamanio;
           this.conSinSecuencia = conSinSecuencia;
           this.fechaPublicacion = fechaPublicacion;
           this.horaPublicacion = horaPublicacion;
           this.autor = autor;
           this.areaCurricular = areaCurricular;
           this.palabrasClave = palabrasClave;
           this.tipoRecurso = tipoRecurso;
           this.orientacionDidactica = orientacionDidactica;
           this.contribuciones = contribuciones;
    }


    /**
     * Gets the licencias value for this MetadatoBasicoVO.
     * 
     * @return licencias   * Licencias del ODE. Una licencia por entrada en el array.
     */
    public java.lang.String[] getLicencias() {
        return licencias;
    }


    /**
     * Sets the licencias value for this MetadatoBasicoVO.
     * 
     * @param licencias   * Licencias del ODE. Una licencia por entrada en el array.
     */
    public void setLicencias(java.lang.String[] licencias) {
        this.licencias = licencias;
    }


    /**
     * Gets the titulo value for this MetadatoBasicoVO.
     * 
     * @return titulo   * Titulo del ODE
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this MetadatoBasicoVO.
     * 
     * @param titulo   * Titulo del ODE
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the descripcion value for this MetadatoBasicoVO.
     * 
     * @return descripcion   * Descripcion del ODE.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this MetadatoBasicoVO.
     * 
     * @param descripcion   * Descripcion del ODE.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the formato value for this MetadatoBasicoVO.
     * 
     * @return formato   * Formato del ODE.
     */
    public java.lang.String[] getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this MetadatoBasicoVO.
     * 
     * @param formato   * Formato del ODE.
     */
    public void setFormato(java.lang.String[] formato) {
        this.formato = formato;
    }


    /**
     * Gets the destinatarios value for this MetadatoBasicoVO.
     * 
     * @return destinatarios   * Los destinatarios del ODE. Un destinatario por cada entrada
     * del
     *                                 array.
     */
    public java.lang.String[] getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this MetadatoBasicoVO.
     * 
     * @param destinatarios   * Los destinatarios del ODE. Un destinatario por cada entrada
     * del
     *                                 array.
     */
    public void setDestinatarios(java.lang.String[] destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the idioma value for this MetadatoBasicoVO.
     * 
     * @return idioma   * Idioma del ODE. Un idioma por cada entrada del array
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this MetadatoBasicoVO.
     * 
     * @param idioma   * Idioma del ODE. Un idioma por cada entrada del array
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the ambito value for this MetadatoBasicoVO.
     * 
     * @return ambito   * Ambito de aplicacion del ODE. Un ambito por cada entrada del
     * string.
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this MetadatoBasicoVO.
     * 
     * @param ambito   * Ambito de aplicacion del ODE. Un ambito por cada entrada del
     * string.
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the valoracion value for this MetadatoBasicoVO.
     * 
     * @return valoracion   * Valoracion del ODE.
     */
    public java.lang.Float getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this MetadatoBasicoVO.
     * 
     * @param valoracion   * Valoracion del ODE.
     */
    public void setValoracion(java.lang.Float valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the localizadorODE value for this MetadatoBasicoVO.
     * 
     * @return localizadorODE   * Localizador del ODE.
     */
    public java.lang.String getLocalizadorODE() {
        return localizadorODE;
    }


    /**
     * Sets the localizadorODE value for this MetadatoBasicoVO.
     * 
     * @param localizadorODE   * Localizador del ODE.
     */
    public void setLocalizadorODE(java.lang.String localizadorODE) {
        this.localizadorODE = localizadorODE;
    }


    /**
     * Gets the identificadorODE value for this MetadatoBasicoVO.
     * 
     * @return identificadorODE   * Identificador alfanumerico de un ODE.
     */
    public java.lang.String getIdentificadorODE() {
        return identificadorODE;
    }


    /**
     * Sets the identificadorODE value for this MetadatoBasicoVO.
     * 
     * @param identificadorODE   * Identificador alfanumerico de un ODE.
     */
    public void setIdentificadorODE(java.lang.String identificadorODE) {
        this.identificadorODE = identificadorODE;
    }


    /**
     * Gets the imagen value for this MetadatoBasicoVO.
     * 
     * @return imagen
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this MetadatoBasicoVO.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the nivelAgregacion value for this MetadatoBasicoVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.Integer getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this MetadatoBasicoVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.Integer nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the tamanio value for this MetadatoBasicoVO.
     * 
     * @return tamanio
     */
    public java.lang.String getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this MetadatoBasicoVO.
     * 
     * @param tamanio
     */
    public void setTamanio(java.lang.String tamanio) {
        this.tamanio = tamanio;
    }


    /**
     * Gets the conSinSecuencia value for this MetadatoBasicoVO.
     * 
     * @return conSinSecuencia   * Indica si el ode tiene secuencia o no.
     */
    public java.lang.Boolean getConSinSecuencia() {
        return conSinSecuencia;
    }


    /**
     * Sets the conSinSecuencia value for this MetadatoBasicoVO.
     * 
     * @param conSinSecuencia   * Indica si el ode tiene secuencia o no.
     */
    public void setConSinSecuencia(java.lang.Boolean conSinSecuencia) {
        this.conSinSecuencia = conSinSecuencia;
    }


    /**
     * Gets the fechaPublicacion value for this MetadatoBasicoVO.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this MetadatoBasicoVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the horaPublicacion value for this MetadatoBasicoVO.
     * 
     * @return horaPublicacion
     */
    public java.lang.String getHoraPublicacion() {
        return horaPublicacion;
    }


    /**
     * Sets the horaPublicacion value for this MetadatoBasicoVO.
     * 
     * @param horaPublicacion
     */
    public void setHoraPublicacion(java.lang.String horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }


    /**
     * Gets the autor value for this MetadatoBasicoVO.
     * 
     * @return autor   * Autores del ODE
     */
    public java.lang.String[] getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this MetadatoBasicoVO.
     * 
     * @param autor   * Autores del ODE
     */
    public void setAutor(java.lang.String[] autor) {
        this.autor = autor;
    }


    /**
     * Gets the areaCurricular value for this MetadatoBasicoVO.
     * 
     * @return areaCurricular
     */
    public java.lang.String[] getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this MetadatoBasicoVO.
     * 
     * @param areaCurricular
     */
    public void setAreaCurricular(java.lang.String[] areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the palabrasClave value for this MetadatoBasicoVO.
     * 
     * @return palabrasClave
     */
    public java.lang.String[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this MetadatoBasicoVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(java.lang.String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the tipoRecurso value for this MetadatoBasicoVO.
     * 
     * @return tipoRecurso
     */
    public java.lang.String[] getTipoRecurso() {
        return tipoRecurso;
    }


    /**
     * Sets the tipoRecurso value for this MetadatoBasicoVO.
     * 
     * @param tipoRecurso
     */
    public void setTipoRecurso(java.lang.String[] tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    /**
     * Gets the orientacionDidactica value for this MetadatoBasicoVO.
     * 
     * @return orientacionDidactica
     */
    public java.lang.String[] getOrientacionDidactica() {
        return orientacionDidactica;
    }


    /**
     * Sets the orientacionDidactica value for this MetadatoBasicoVO.
     * 
     * @param orientacionDidactica
     */
    public void setOrientacionDidactica(java.lang.String[] orientacionDidactica) {
        this.orientacionDidactica = orientacionDidactica;
    }


    /**
     * Gets the contribuciones value for this MetadatoBasicoVO.
     * 
     * @return contribuciones
     */
    public es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO[] getContribuciones() {
        return contribuciones;
    }


    /**
     * Sets the contribuciones value for this MetadatoBasicoVO.
     * 
     * @param contribuciones
     */
    public void setContribuciones(es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO[] contribuciones) {
        this.contribuciones = contribuciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MetadatoBasicoVO)) return false;
        MetadatoBasicoVO other = (MetadatoBasicoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.licencias==null && other.getLicencias()==null) || 
             (this.licencias!=null &&
              java.util.Arrays.equals(this.licencias, other.getLicencias()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              java.util.Arrays.equals(this.formato, other.getFormato()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              java.util.Arrays.equals(this.destinatarios, other.getDestinatarios()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              java.util.Arrays.equals(this.ambito, other.getAmbito()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.localizadorODE==null && other.getLocalizadorODE()==null) || 
             (this.localizadorODE!=null &&
              this.localizadorODE.equals(other.getLocalizadorODE()))) &&
            ((this.identificadorODE==null && other.getIdentificadorODE()==null) || 
             (this.identificadorODE!=null &&
              this.identificadorODE.equals(other.getIdentificadorODE()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.tamanio==null && other.getTamanio()==null) || 
             (this.tamanio!=null &&
              this.tamanio.equals(other.getTamanio()))) &&
            ((this.conSinSecuencia==null && other.getConSinSecuencia()==null) || 
             (this.conSinSecuencia!=null &&
              this.conSinSecuencia.equals(other.getConSinSecuencia()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.horaPublicacion==null && other.getHoraPublicacion()==null) || 
             (this.horaPublicacion!=null &&
              this.horaPublicacion.equals(other.getHoraPublicacion()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              java.util.Arrays.equals(this.autor, other.getAutor()))) &&
            ((this.areaCurricular==null && other.getAreaCurricular()==null) || 
             (this.areaCurricular!=null &&
              java.util.Arrays.equals(this.areaCurricular, other.getAreaCurricular()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave()))) &&
            ((this.tipoRecurso==null && other.getTipoRecurso()==null) || 
             (this.tipoRecurso!=null &&
              java.util.Arrays.equals(this.tipoRecurso, other.getTipoRecurso()))) &&
            ((this.orientacionDidactica==null && other.getOrientacionDidactica()==null) || 
             (this.orientacionDidactica!=null &&
              java.util.Arrays.equals(this.orientacionDidactica, other.getOrientacionDidactica()))) &&
            ((this.contribuciones==null && other.getContribuciones()==null) || 
             (this.contribuciones!=null &&
              java.util.Arrays.equals(this.contribuciones, other.getContribuciones())));
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
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
            _hashCode += getIdioma().hashCode();
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
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getLocalizadorODE() != null) {
            _hashCode += getLocalizadorODE().hashCode();
        }
        if (getIdentificadorODE() != null) {
            _hashCode += getIdentificadorODE().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getTamanio() != null) {
            _hashCode += getTamanio().hashCode();
        }
        if (getConSinSecuencia() != null) {
            _hashCode += getConSinSecuencia().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getHoraPublicacion() != null) {
            _hashCode += getHoraPublicacion().hashCode();
        }
        if (getAutor() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutor());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutor(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getOrientacionDidactica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrientacionDidactica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrientacionDidactica(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContribuciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContribuciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContribuciones(), i);
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
        new org.apache.axis.description.TypeDesc(MetadatoBasicoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoBasicoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "licencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "localizadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "identificadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tamanio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conSinSecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "conSinSecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "tipoRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orientacionDidactica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "orientacionDidactica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "contribuciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ContribucionOdeVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
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
