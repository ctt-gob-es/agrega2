/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * DocVO12.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Esta clase representa un documento como resultado unico de una
 *                         busqueda.
 */
public class DocVO12  implements java.io.Serializable {
    private java.lang.String descripcion;

    private java.lang.String titulo;

    /* Localizador del objeto digital. */
    private java.lang.String localizadorODE;

    /* Valoracion del ODE. */
    private java.lang.Float valoracion;

    /* Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso. */
    private java.lang.String[] tipoRecurso;

    /* Curso al que pertenece el ODE. */
    private java.lang.String curso;

    /* Area curricular a la que pertenece el ODE. */
    private java.lang.String[] areaCurricular;

    /* Licencias del ODE. */
    private java.lang.String[] licencias;

    /* Formatos del ODE. */
    private java.lang.String[] formato;

    /* Destinatarios del ODE. */
    private java.lang.String[] destinatarios;

    /* Idiomas que contempla la informacion del ODE. */
    private java.lang.String idioma;

    /* Ambito de aplicacion del ODE. */
    private java.lang.String[] ambito;

    private java.lang.Integer ranking;

    /* Se trata del identificador alfanumerico del ODE. */
    private java.lang.String identificadorODE;

    private java.lang.String nodo;

    /* URL de acceso a la imagen del ODE. */
    private java.lang.String imagen;

    private java.lang.Integer nivelAgregacion;

    private java.lang.String tamanio;

    /* Indica si el ode tiene secuencia o no. */
    private java.lang.Boolean conSinSecuencia;

    /* fecha de publicacion del ODE */
    private java.lang.String fechaPublicacion;

    /* hora en la que se ha publicado el ODE */
    private java.lang.String horaPublicacion;

    private java.lang.String[] autor;

    private es.pode.indexador.negocio.servicios.busqueda.ContribucionVO[] contribuciones;

    private java.lang.String[] palabrasClave;

    private java.lang.String[] orientacionDidactica;

    public DocVO12() {
    }

    public DocVO12(
           java.lang.String descripcion,
           java.lang.String titulo,
           java.lang.String localizadorODE,
           java.lang.Float valoracion,
           java.lang.String[] tipoRecurso,
           java.lang.String curso,
           java.lang.String[] areaCurricular,
           java.lang.String[] licencias,
           java.lang.String[] formato,
           java.lang.String[] destinatarios,
           java.lang.String idioma,
           java.lang.String[] ambito,
           java.lang.Integer ranking,
           java.lang.String identificadorODE,
           java.lang.String nodo,
           java.lang.String imagen,
           java.lang.Integer nivelAgregacion,
           java.lang.String tamanio,
           java.lang.Boolean conSinSecuencia,
           java.lang.String fechaPublicacion,
           java.lang.String horaPublicacion,
           java.lang.String[] autor,
           es.pode.indexador.negocio.servicios.busqueda.ContribucionVO[] contribuciones,
           java.lang.String[] palabrasClave,
           java.lang.String[] orientacionDidactica) {
           this.descripcion = descripcion;
           this.titulo = titulo;
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
           this.nodo = nodo;
           this.imagen = imagen;
           this.nivelAgregacion = nivelAgregacion;
           this.tamanio = tamanio;
           this.conSinSecuencia = conSinSecuencia;
           this.fechaPublicacion = fechaPublicacion;
           this.horaPublicacion = horaPublicacion;
           this.autor = autor;
           this.contribuciones = contribuciones;
           this.palabrasClave = palabrasClave;
           this.orientacionDidactica = orientacionDidactica;
    }


    /**
     * Gets the descripcion value for this DocVO12.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DocVO12.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the titulo value for this DocVO12.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DocVO12.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the localizadorODE value for this DocVO12.
     * 
     * @return localizadorODE   * Localizador del objeto digital.
     */
    public java.lang.String getLocalizadorODE() {
        return localizadorODE;
    }


    /**
     * Sets the localizadorODE value for this DocVO12.
     * 
     * @param localizadorODE   * Localizador del objeto digital.
     */
    public void setLocalizadorODE(java.lang.String localizadorODE) {
        this.localizadorODE = localizadorODE;
    }


    /**
     * Gets the valoracion value for this DocVO12.
     * 
     * @return valoracion   * Valoracion del ODE.
     */
    public java.lang.Float getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this DocVO12.
     * 
     * @param valoracion   * Valoracion del ODE.
     */
    public void setValoracion(java.lang.Float valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the tipoRecurso value for this DocVO12.
     * 
     * @return tipoRecurso   * Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso.
     */
    public java.lang.String[] getTipoRecurso() {
        return tipoRecurso;
    }


    /**
     * Sets the tipoRecurso value for this DocVO12.
     * 
     * @param tipoRecurso   * Tipo de recurso del ODE. En cada entrada del array hay un tipo
     * de recurso.
     */
    public void setTipoRecurso(java.lang.String[] tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }


    /**
     * Gets the curso value for this DocVO12.
     * 
     * @return curso   * Curso al que pertenece el ODE.
     */
    public java.lang.String getCurso() {
        return curso;
    }


    /**
     * Sets the curso value for this DocVO12.
     * 
     * @param curso   * Curso al que pertenece el ODE.
     */
    public void setCurso(java.lang.String curso) {
        this.curso = curso;
    }


    /**
     * Gets the areaCurricular value for this DocVO12.
     * 
     * @return areaCurricular   * Area curricular a la que pertenece el ODE.
     */
    public java.lang.String[] getAreaCurricular() {
        return areaCurricular;
    }


    /**
     * Sets the areaCurricular value for this DocVO12.
     * 
     * @param areaCurricular   * Area curricular a la que pertenece el ODE.
     */
    public void setAreaCurricular(java.lang.String[] areaCurricular) {
        this.areaCurricular = areaCurricular;
    }


    /**
     * Gets the licencias value for this DocVO12.
     * 
     * @return licencias   * Licencias del ODE.
     */
    public java.lang.String[] getLicencias() {
        return licencias;
    }


    /**
     * Sets the licencias value for this DocVO12.
     * 
     * @param licencias   * Licencias del ODE.
     */
    public void setLicencias(java.lang.String[] licencias) {
        this.licencias = licencias;
    }


    /**
     * Gets the formato value for this DocVO12.
     * 
     * @return formato   * Formatos del ODE.
     */
    public java.lang.String[] getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this DocVO12.
     * 
     * @param formato   * Formatos del ODE.
     */
    public void setFormato(java.lang.String[] formato) {
        this.formato = formato;
    }


    /**
     * Gets the destinatarios value for this DocVO12.
     * 
     * @return destinatarios   * Destinatarios del ODE.
     */
    public java.lang.String[] getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this DocVO12.
     * 
     * @param destinatarios   * Destinatarios del ODE.
     */
    public void setDestinatarios(java.lang.String[] destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the idioma value for this DocVO12.
     * 
     * @return idioma   * Idiomas que contempla la informacion del ODE.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this DocVO12.
     * 
     * @param idioma   * Idiomas que contempla la informacion del ODE.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the ambito value for this DocVO12.
     * 
     * @return ambito   * Ambito de aplicacion del ODE.
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this DocVO12.
     * 
     * @param ambito   * Ambito de aplicacion del ODE.
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the ranking value for this DocVO12.
     * 
     * @return ranking
     */
    public java.lang.Integer getRanking() {
        return ranking;
    }


    /**
     * Sets the ranking value for this DocVO12.
     * 
     * @param ranking
     */
    public void setRanking(java.lang.Integer ranking) {
        this.ranking = ranking;
    }


    /**
     * Gets the identificadorODE value for this DocVO12.
     * 
     * @return identificadorODE   * Se trata del identificador alfanumerico del ODE.
     */
    public java.lang.String getIdentificadorODE() {
        return identificadorODE;
    }


    /**
     * Sets the identificadorODE value for this DocVO12.
     * 
     * @param identificadorODE   * Se trata del identificador alfanumerico del ODE.
     */
    public void setIdentificadorODE(java.lang.String identificadorODE) {
        this.identificadorODE = identificadorODE;
    }


    /**
     * Gets the nodo value for this DocVO12.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this DocVO12.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the imagen value for this DocVO12.
     * 
     * @return imagen   * URL de acceso a la imagen del ODE.
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this DocVO12.
     * 
     * @param imagen   * URL de acceso a la imagen del ODE.
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the nivelAgregacion value for this DocVO12.
     * 
     * @return nivelAgregacion
     */
    public java.lang.Integer getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this DocVO12.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.Integer nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the tamanio value for this DocVO12.
     * 
     * @return tamanio
     */
    public java.lang.String getTamanio() {
        return tamanio;
    }


    /**
     * Sets the tamanio value for this DocVO12.
     * 
     * @param tamanio
     */
    public void setTamanio(java.lang.String tamanio) {
        this.tamanio = tamanio;
    }


    /**
     * Gets the conSinSecuencia value for this DocVO12.
     * 
     * @return conSinSecuencia   * Indica si el ode tiene secuencia o no.
     */
    public java.lang.Boolean getConSinSecuencia() {
        return conSinSecuencia;
    }


    /**
     * Sets the conSinSecuencia value for this DocVO12.
     * 
     * @param conSinSecuencia   * Indica si el ode tiene secuencia o no.
     */
    public void setConSinSecuencia(java.lang.Boolean conSinSecuencia) {
        this.conSinSecuencia = conSinSecuencia;
    }


    /**
     * Gets the fechaPublicacion value for this DocVO12.
     * 
     * @return fechaPublicacion   * fecha de publicacion del ODE
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this DocVO12.
     * 
     * @param fechaPublicacion   * fecha de publicacion del ODE
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the horaPublicacion value for this DocVO12.
     * 
     * @return horaPublicacion   * hora en la que se ha publicado el ODE
     */
    public java.lang.String getHoraPublicacion() {
        return horaPublicacion;
    }


    /**
     * Sets the horaPublicacion value for this DocVO12.
     * 
     * @param horaPublicacion   * hora en la que se ha publicado el ODE
     */
    public void setHoraPublicacion(java.lang.String horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }


    /**
     * Gets the autor value for this DocVO12.
     * 
     * @return autor
     */
    public java.lang.String[] getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this DocVO12.
     * 
     * @param autor
     */
    public void setAutor(java.lang.String[] autor) {
        this.autor = autor;
    }


    /**
     * Gets the contribuciones value for this DocVO12.
     * 
     * @return contribuciones
     */
    public es.pode.indexador.negocio.servicios.busqueda.ContribucionVO[] getContribuciones() {
        return contribuciones;
    }


    /**
     * Sets the contribuciones value for this DocVO12.
     * 
     * @param contribuciones
     */
    public void setContribuciones(es.pode.indexador.negocio.servicios.busqueda.ContribucionVO[] contribuciones) {
        this.contribuciones = contribuciones;
    }


    /**
     * Gets the palabrasClave value for this DocVO12.
     * 
     * @return palabrasClave
     */
    public java.lang.String[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this DocVO12.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(java.lang.String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the orientacionDidactica value for this DocVO12.
     * 
     * @return orientacionDidactica
     */
    public java.lang.String[] getOrientacionDidactica() {
        return orientacionDidactica;
    }


    /**
     * Sets the orientacionDidactica value for this DocVO12.
     * 
     * @param orientacionDidactica
     */
    public void setOrientacionDidactica(java.lang.String[] orientacionDidactica) {
        this.orientacionDidactica = orientacionDidactica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocVO12)) return false;
        DocVO12 other = (DocVO12) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
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
              java.util.Arrays.equals(this.areaCurricular, other.getAreaCurricular()))) &&
            ((this.licencias==null && other.getLicencias()==null) || 
             (this.licencias!=null &&
              java.util.Arrays.equals(this.licencias, other.getLicencias()))) &&
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
            ((this.ranking==null && other.getRanking()==null) || 
             (this.ranking!=null &&
              this.ranking.equals(other.getRanking()))) &&
            ((this.identificadorODE==null && other.getIdentificadorODE()==null) || 
             (this.identificadorODE!=null &&
              this.identificadorODE.equals(other.getIdentificadorODE()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
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
            ((this.contribuciones==null && other.getContribuciones()==null) || 
             (this.contribuciones!=null &&
              java.util.Arrays.equals(this.contribuciones, other.getContribuciones()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave()))) &&
            ((this.orientacionDidactica==null && other.getOrientacionDidactica()==null) || 
             (this.orientacionDidactica!=null &&
              java.util.Arrays.equals(this.orientacionDidactica, other.getOrientacionDidactica())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
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
        if (getRanking() != null) {
            _hashCode += getRanking().hashCode();
        }
        if (getIdentificadorODE() != null) {
            _hashCode += getIdentificadorODE().hashCode();
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocVO12.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "DocVO12"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "localizadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "float"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "tipoRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "curso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "areaCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "licencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ranking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ranking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "identificadorODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamanio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "tamanio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conSinSecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "conSinSecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "horaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "contribuciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ContribucionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orientacionDidactica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "orientacionDidactica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
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