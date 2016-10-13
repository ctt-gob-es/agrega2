/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * LomESPrimarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;


/**
 * Esta clase modela los datos relevantes de ser indexados en el
 *                         cuerpo de un LOM-ES primario de un ODE.
 */
public class LomESPrimarioVO  implements java.io.Serializable {
    /* Titulo del ODE */
    private java.lang.String titulo;

    /* Recursos presentes en un ODE. Un ODE puede estar compuesto
     * de
     *                                 multiples elementos y estos ser de
     * diferentes tipos. */
    private java.lang.String[] recurso;

    /* Procesos cognitivos del ODE. */
    private java.lang.String[] procesosCognitivos;

    /* Palabras clave del LomESPrimario */
    private java.lang.String[] palabrasClave;

    /* Niveles educativos presentes en el ODE. */
    private java.lang.String[] nivelesEducativos;

    /* La licencia a la que esta sujeta el ODE. */
    private java.lang.String licencia;

    /* idioma de bÃºsqueda del ODE */
    private java.lang.String idioma;

    /* Formatos presentes en el ODE. */
    private java.lang.String[] formatos;

    /* Fecha en la que se publica el ODE */
    private java.util.Calendar fechaPublicacion;

    /* Edades de los usuarios a los que va dirigidos la accion lectiva
     * del ODE. */
    private java.lang.String[] edades;

    /* Destinatarios a los que va dirigido el ODE.  Un ODE puede tener
     * como destinatarios de la accion lectiva diferentes colectivos. */
    private java.lang.String[] destinatarios;

    /* Descripcion del ODE */
    private java.lang.String descripcion;

    /* Curso al que pertenece el ODE. */
    private java.lang.String curso;

    /* Los contextos presentes en el ODE. */
    private java.lang.String[] contextos;

    /* Autor del ODE. */
    private java.lang.String[] autor;

    /* TaxonomÃ­a a la que pertenece el ODE. Pueden existir varias
     * clasificaciones para un mismo ODE. */
    private java.lang.String[] taxonomia;

    /* Ambito del ODE. (Universal o de otro ambito) */
    private java.lang.String[] ambito;

    /* Nivel de Agregacion del ODE. */
    private java.lang.Integer nivelAgregacion;

    /* Ramas del tesauro ETB en los que esta catalogado el ODE */
    private java.lang.String[] tesauros;

    /* Fuentes sobre las que se relaciona el ODE. */
    private java.lang.String[] fuente;

    /* Relacion del ODE con otros contenidos. */
    private java.lang.String[] relacion;

    /* Publicador del ODE. */
    private java.lang.String[] publicador;

    /* Contribuidor al contenido del ODE. */
    private java.lang.String[] contribuidor;

    /* Alcance del contenido del ODE. */
    private java.lang.String[] alcance;

    /* Variable para recuperar las descripciones del ODE */
    private java.lang.String[] descripciones;

    /* Array con todos los tÃ©rminos pertencientes a rutas del Ã¡rbol
     * curricular(vigentes) con las que esta catolagado el ODE. */
    private java.lang.String[] literalesArbolCurricular;

    /* Contribuciones de personas y/o entidades al estado del ODE. */
    private es.pode.indexador.negocio.servicios.indexado.ContribucionVO[] contribuciones;

    public LomESPrimarioVO() {
    }

    public LomESPrimarioVO(
           java.lang.String titulo,
           java.lang.String[] recurso,
           java.lang.String[] procesosCognitivos,
           java.lang.String[] palabrasClave,
           java.lang.String[] nivelesEducativos,
           java.lang.String licencia,
           java.lang.String idioma,
           java.lang.String[] formatos,
           java.util.Calendar fechaPublicacion,
           java.lang.String[] edades,
           java.lang.String[] destinatarios,
           java.lang.String descripcion,
           java.lang.String curso,
           java.lang.String[] contextos,
           java.lang.String[] autor,
           java.lang.String[] taxonomia,
           java.lang.String[] ambito,
           java.lang.Integer nivelAgregacion,
           java.lang.String[] tesauros,
           java.lang.String[] fuente,
           java.lang.String[] relacion,
           java.lang.String[] publicador,
           java.lang.String[] contribuidor,
           java.lang.String[] alcance,
           java.lang.String[] descripciones,
           java.lang.String[] literalesArbolCurricular,
           es.pode.indexador.negocio.servicios.indexado.ContribucionVO[] contribuciones) {
           this.titulo = titulo;
           this.recurso = recurso;
           this.procesosCognitivos = procesosCognitivos;
           this.palabrasClave = palabrasClave;
           this.nivelesEducativos = nivelesEducativos;
           this.licencia = licencia;
           this.idioma = idioma;
           this.formatos = formatos;
           this.fechaPublicacion = fechaPublicacion;
           this.edades = edades;
           this.destinatarios = destinatarios;
           this.descripcion = descripcion;
           this.curso = curso;
           this.contextos = contextos;
           this.autor = autor;
           this.taxonomia = taxonomia;
           this.ambito = ambito;
           this.nivelAgregacion = nivelAgregacion;
           this.tesauros = tesauros;
           this.fuente = fuente;
           this.relacion = relacion;
           this.publicador = publicador;
           this.contribuidor = contribuidor;
           this.alcance = alcance;
           this.descripciones = descripciones;
           this.literalesArbolCurricular = literalesArbolCurricular;
           this.contribuciones = contribuciones;
    }


    /**
     * Gets the titulo value for this LomESPrimarioVO.
     * 
     * @return titulo   * Titulo del ODE
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this LomESPrimarioVO.
     * 
     * @param titulo   * Titulo del ODE
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the recurso value for this LomESPrimarioVO.
     * 
     * @return recurso   * Recursos presentes en un ODE. Un ODE puede estar compuesto
     * de
     *                                 multiples elementos y estos ser de
     * diferentes tipos.
     */
    public java.lang.String[] getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this LomESPrimarioVO.
     * 
     * @param recurso   * Recursos presentes en un ODE. Un ODE puede estar compuesto
     * de
     *                                 multiples elementos y estos ser de
     * diferentes tipos.
     */
    public void setRecurso(java.lang.String[] recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the procesosCognitivos value for this LomESPrimarioVO.
     * 
     * @return procesosCognitivos   * Procesos cognitivos del ODE.
     */
    public java.lang.String[] getProcesosCognitivos() {
        return procesosCognitivos;
    }


    /**
     * Sets the procesosCognitivos value for this LomESPrimarioVO.
     * 
     * @param procesosCognitivos   * Procesos cognitivos del ODE.
     */
    public void setProcesosCognitivos(java.lang.String[] procesosCognitivos) {
        this.procesosCognitivos = procesosCognitivos;
    }


    /**
     * Gets the palabrasClave value for this LomESPrimarioVO.
     * 
     * @return palabrasClave   * Palabras clave del LomESPrimario
     */
    public java.lang.String[] getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this LomESPrimarioVO.
     * 
     * @param palabrasClave   * Palabras clave del LomESPrimario
     */
    public void setPalabrasClave(java.lang.String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the nivelesEducativos value for this LomESPrimarioVO.
     * 
     * @return nivelesEducativos   * Niveles educativos presentes en el ODE.
     */
    public java.lang.String[] getNivelesEducativos() {
        return nivelesEducativos;
    }


    /**
     * Sets the nivelesEducativos value for this LomESPrimarioVO.
     * 
     * @param nivelesEducativos   * Niveles educativos presentes en el ODE.
     */
    public void setNivelesEducativos(java.lang.String[] nivelesEducativos) {
        this.nivelesEducativos = nivelesEducativos;
    }


    /**
     * Gets the licencia value for this LomESPrimarioVO.
     * 
     * @return licencia   * La licencia a la que esta sujeta el ODE.
     */
    public java.lang.String getLicencia() {
        return licencia;
    }


    /**
     * Sets the licencia value for this LomESPrimarioVO.
     * 
     * @param licencia   * La licencia a la que esta sujeta el ODE.
     */
    public void setLicencia(java.lang.String licencia) {
        this.licencia = licencia;
    }


    /**
     * Gets the idioma value for this LomESPrimarioVO.
     * 
     * @return idioma   * idioma de bÃºsqueda del ODE
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this LomESPrimarioVO.
     * 
     * @param idioma   * idioma de bÃºsqueda del ODE
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the formatos value for this LomESPrimarioVO.
     * 
     * @return formatos   * Formatos presentes en el ODE.
     */
    public java.lang.String[] getFormatos() {
        return formatos;
    }


    /**
     * Sets the formatos value for this LomESPrimarioVO.
     * 
     * @param formatos   * Formatos presentes en el ODE.
     */
    public void setFormatos(java.lang.String[] formatos) {
        this.formatos = formatos;
    }


    /**
     * Gets the fechaPublicacion value for this LomESPrimarioVO.
     * 
     * @return fechaPublicacion   * Fecha en la que se publica el ODE
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this LomESPrimarioVO.
     * 
     * @param fechaPublicacion   * Fecha en la que se publica el ODE
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the edades value for this LomESPrimarioVO.
     * 
     * @return edades   * Edades de los usuarios a los que va dirigidos la accion lectiva
     * del ODE.
     */
    public java.lang.String[] getEdades() {
        return edades;
    }


    /**
     * Sets the edades value for this LomESPrimarioVO.
     * 
     * @param edades   * Edades de los usuarios a los que va dirigidos la accion lectiva
     * del ODE.
     */
    public void setEdades(java.lang.String[] edades) {
        this.edades = edades;
    }


    /**
     * Gets the destinatarios value for this LomESPrimarioVO.
     * 
     * @return destinatarios   * Destinatarios a los que va dirigido el ODE.  Un ODE puede tener
     * como destinatarios de la accion lectiva diferentes colectivos.
     */
    public java.lang.String[] getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this LomESPrimarioVO.
     * 
     * @param destinatarios   * Destinatarios a los que va dirigido el ODE.  Un ODE puede tener
     * como destinatarios de la accion lectiva diferentes colectivos.
     */
    public void setDestinatarios(java.lang.String[] destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the descripcion value for this LomESPrimarioVO.
     * 
     * @return descripcion   * Descripcion del ODE
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this LomESPrimarioVO.
     * 
     * @param descripcion   * Descripcion del ODE
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the curso value for this LomESPrimarioVO.
     * 
     * @return curso   * Curso al que pertenece el ODE.
     */
    public java.lang.String getCurso() {
        return curso;
    }


    /**
     * Sets the curso value for this LomESPrimarioVO.
     * 
     * @param curso   * Curso al que pertenece el ODE.
     */
    public void setCurso(java.lang.String curso) {
        this.curso = curso;
    }


    /**
     * Gets the contextos value for this LomESPrimarioVO.
     * 
     * @return contextos   * Los contextos presentes en el ODE.
     */
    public java.lang.String[] getContextos() {
        return contextos;
    }


    /**
     * Sets the contextos value for this LomESPrimarioVO.
     * 
     * @param contextos   * Los contextos presentes en el ODE.
     */
    public void setContextos(java.lang.String[] contextos) {
        this.contextos = contextos;
    }


    /**
     * Gets the autor value for this LomESPrimarioVO.
     * 
     * @return autor   * Autor del ODE.
     */
    public java.lang.String[] getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this LomESPrimarioVO.
     * 
     * @param autor   * Autor del ODE.
     */
    public void setAutor(java.lang.String[] autor) {
        this.autor = autor;
    }


    /**
     * Gets the taxonomia value for this LomESPrimarioVO.
     * 
     * @return taxonomia   * TaxonomÃ­a a la que pertenece el ODE. Pueden existir varias
     * clasificaciones para un mismo ODE.
     */
    public java.lang.String[] getTaxonomia() {
        return taxonomia;
    }


    /**
     * Sets the taxonomia value for this LomESPrimarioVO.
     * 
     * @param taxonomia   * TaxonomÃ­a a la que pertenece el ODE. Pueden existir varias
     * clasificaciones para un mismo ODE.
     */
    public void setTaxonomia(java.lang.String[] taxonomia) {
        this.taxonomia = taxonomia;
    }


    /**
     * Gets the ambito value for this LomESPrimarioVO.
     * 
     * @return ambito   * Ambito del ODE. (Universal o de otro ambito)
     */
    public java.lang.String[] getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this LomESPrimarioVO.
     * 
     * @param ambito   * Ambito del ODE. (Universal o de otro ambito)
     */
    public void setAmbito(java.lang.String[] ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the nivelAgregacion value for this LomESPrimarioVO.
     * 
     * @return nivelAgregacion   * Nivel de Agregacion del ODE.
     */
    public java.lang.Integer getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this LomESPrimarioVO.
     * 
     * @param nivelAgregacion   * Nivel de Agregacion del ODE.
     */
    public void setNivelAgregacion(java.lang.Integer nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the tesauros value for this LomESPrimarioVO.
     * 
     * @return tesauros   * Ramas del tesauro ETB en los que esta catalogado el ODE
     */
    public java.lang.String[] getTesauros() {
        return tesauros;
    }


    /**
     * Sets the tesauros value for this LomESPrimarioVO.
     * 
     * @param tesauros   * Ramas del tesauro ETB en los que esta catalogado el ODE
     */
    public void setTesauros(java.lang.String[] tesauros) {
        this.tesauros = tesauros;
    }


    /**
     * Gets the fuente value for this LomESPrimarioVO.
     * 
     * @return fuente   * Fuentes sobre las que se relaciona el ODE.
     */
    public java.lang.String[] getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this LomESPrimarioVO.
     * 
     * @param fuente   * Fuentes sobre las que se relaciona el ODE.
     */
    public void setFuente(java.lang.String[] fuente) {
        this.fuente = fuente;
    }


    /**
     * Gets the relacion value for this LomESPrimarioVO.
     * 
     * @return relacion   * Relacion del ODE con otros contenidos.
     */
    public java.lang.String[] getRelacion() {
        return relacion;
    }


    /**
     * Sets the relacion value for this LomESPrimarioVO.
     * 
     * @param relacion   * Relacion del ODE con otros contenidos.
     */
    public void setRelacion(java.lang.String[] relacion) {
        this.relacion = relacion;
    }


    /**
     * Gets the publicador value for this LomESPrimarioVO.
     * 
     * @return publicador   * Publicador del ODE.
     */
    public java.lang.String[] getPublicador() {
        return publicador;
    }


    /**
     * Sets the publicador value for this LomESPrimarioVO.
     * 
     * @param publicador   * Publicador del ODE.
     */
    public void setPublicador(java.lang.String[] publicador) {
        this.publicador = publicador;
    }


    /**
     * Gets the contribuidor value for this LomESPrimarioVO.
     * 
     * @return contribuidor   * Contribuidor al contenido del ODE.
     */
    public java.lang.String[] getContribuidor() {
        return contribuidor;
    }


    /**
     * Sets the contribuidor value for this LomESPrimarioVO.
     * 
     * @param contribuidor   * Contribuidor al contenido del ODE.
     */
    public void setContribuidor(java.lang.String[] contribuidor) {
        this.contribuidor = contribuidor;
    }


    /**
     * Gets the alcance value for this LomESPrimarioVO.
     * 
     * @return alcance   * Alcance del contenido del ODE.
     */
    public java.lang.String[] getAlcance() {
        return alcance;
    }


    /**
     * Sets the alcance value for this LomESPrimarioVO.
     * 
     * @param alcance   * Alcance del contenido del ODE.
     */
    public void setAlcance(java.lang.String[] alcance) {
        this.alcance = alcance;
    }


    /**
     * Gets the descripciones value for this LomESPrimarioVO.
     * 
     * @return descripciones   * Variable para recuperar las descripciones del ODE
     */
    public java.lang.String[] getDescripciones() {
        return descripciones;
    }


    /**
     * Sets the descripciones value for this LomESPrimarioVO.
     * 
     * @param descripciones   * Variable para recuperar las descripciones del ODE
     */
    public void setDescripciones(java.lang.String[] descripciones) {
        this.descripciones = descripciones;
    }


    /**
     * Gets the literalesArbolCurricular value for this LomESPrimarioVO.
     * 
     * @return literalesArbolCurricular   * Array con todos los tÃ©rminos pertencientes a rutas del Ã¡rbol
     * curricular(vigentes) con las que esta catolagado el ODE.
     */
    public java.lang.String[] getLiteralesArbolCurricular() {
        return literalesArbolCurricular;
    }


    /**
     * Sets the literalesArbolCurricular value for this LomESPrimarioVO.
     * 
     * @param literalesArbolCurricular   * Array con todos los tÃ©rminos pertencientes a rutas del Ã¡rbol
     * curricular(vigentes) con las que esta catolagado el ODE.
     */
    public void setLiteralesArbolCurricular(java.lang.String[] literalesArbolCurricular) {
        this.literalesArbolCurricular = literalesArbolCurricular;
    }


    /**
     * Gets the contribuciones value for this LomESPrimarioVO.
     * 
     * @return contribuciones   * Contribuciones de personas y/o entidades al estado del ODE.
     */
    public es.pode.indexador.negocio.servicios.indexado.ContribucionVO[] getContribuciones() {
        return contribuciones;
    }


    /**
     * Sets the contribuciones value for this LomESPrimarioVO.
     * 
     * @param contribuciones   * Contribuciones de personas y/o entidades al estado del ODE.
     */
    public void setContribuciones(es.pode.indexador.negocio.servicios.indexado.ContribucionVO[] contribuciones) {
        this.contribuciones = contribuciones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LomESPrimarioVO)) return false;
        LomESPrimarioVO other = (LomESPrimarioVO) obj;
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
            ((this.recurso==null && other.getRecurso()==null) || 
             (this.recurso!=null &&
              java.util.Arrays.equals(this.recurso, other.getRecurso()))) &&
            ((this.procesosCognitivos==null && other.getProcesosCognitivos()==null) || 
             (this.procesosCognitivos!=null &&
              java.util.Arrays.equals(this.procesosCognitivos, other.getProcesosCognitivos()))) &&
            ((this.palabrasClave==null && other.getPalabrasClave()==null) || 
             (this.palabrasClave!=null &&
              java.util.Arrays.equals(this.palabrasClave, other.getPalabrasClave()))) &&
            ((this.nivelesEducativos==null && other.getNivelesEducativos()==null) || 
             (this.nivelesEducativos!=null &&
              java.util.Arrays.equals(this.nivelesEducativos, other.getNivelesEducativos()))) &&
            ((this.licencia==null && other.getLicencia()==null) || 
             (this.licencia!=null &&
              this.licencia.equals(other.getLicencia()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.formatos==null && other.getFormatos()==null) || 
             (this.formatos!=null &&
              java.util.Arrays.equals(this.formatos, other.getFormatos()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.edades==null && other.getEdades()==null) || 
             (this.edades!=null &&
              java.util.Arrays.equals(this.edades, other.getEdades()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              java.util.Arrays.equals(this.destinatarios, other.getDestinatarios()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.curso==null && other.getCurso()==null) || 
             (this.curso!=null &&
              this.curso.equals(other.getCurso()))) &&
            ((this.contextos==null && other.getContextos()==null) || 
             (this.contextos!=null &&
              java.util.Arrays.equals(this.contextos, other.getContextos()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              java.util.Arrays.equals(this.autor, other.getAutor()))) &&
            ((this.taxonomia==null && other.getTaxonomia()==null) || 
             (this.taxonomia!=null &&
              java.util.Arrays.equals(this.taxonomia, other.getTaxonomia()))) &&
            ((this.ambito==null && other.getAmbito()==null) || 
             (this.ambito!=null &&
              java.util.Arrays.equals(this.ambito, other.getAmbito()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.tesauros==null && other.getTesauros()==null) || 
             (this.tesauros!=null &&
              java.util.Arrays.equals(this.tesauros, other.getTesauros()))) &&
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              java.util.Arrays.equals(this.fuente, other.getFuente()))) &&
            ((this.relacion==null && other.getRelacion()==null) || 
             (this.relacion!=null &&
              java.util.Arrays.equals(this.relacion, other.getRelacion()))) &&
            ((this.publicador==null && other.getPublicador()==null) || 
             (this.publicador!=null &&
              java.util.Arrays.equals(this.publicador, other.getPublicador()))) &&
            ((this.contribuidor==null && other.getContribuidor()==null) || 
             (this.contribuidor!=null &&
              java.util.Arrays.equals(this.contribuidor, other.getContribuidor()))) &&
            ((this.alcance==null && other.getAlcance()==null) || 
             (this.alcance!=null &&
              java.util.Arrays.equals(this.alcance, other.getAlcance()))) &&
            ((this.descripciones==null && other.getDescripciones()==null) || 
             (this.descripciones!=null &&
              java.util.Arrays.equals(this.descripciones, other.getDescripciones()))) &&
            ((this.literalesArbolCurricular==null && other.getLiteralesArbolCurricular()==null) || 
             (this.literalesArbolCurricular!=null &&
              java.util.Arrays.equals(this.literalesArbolCurricular, other.getLiteralesArbolCurricular()))) &&
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getRecurso() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecurso());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecurso(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProcesosCognitivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProcesosCognitivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProcesosCognitivos(), i);
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
        if (getNivelesEducativos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNivelesEducativos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNivelesEducativos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLicencia() != null) {
            _hashCode += getLicencia().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
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
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getEdades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEdades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEdades(), i);
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getCurso() != null) {
            _hashCode += getCurso().hashCode();
        }
        if (getContextos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContextos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContextos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getTesauros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTesauros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTesauros(), i);
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
        if (getAlcance() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAlcance());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAlcance(), i);
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
        if (getLiteralesArbolCurricular() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLiteralesArbolCurricular());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLiteralesArbolCurricular(), i);
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
        new org.apache.axis.description.TypeDesc(LomESPrimarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESPrimarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesosCognitivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "procesosCognitivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelesEducativos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "nivelesEducativos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "licencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "formatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "edades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "curso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "contextos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "taxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tesauros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "tesauros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "fuente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "relacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "publicador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuidor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "contribuidor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alcance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "alcance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "descripciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("literalesArbolCurricular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "literalesArbolCurricular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribuciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "contribuciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ContribucionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
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
