/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ParametrosBusquedaAgregadorVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.agregador.negocio.agregador.servicio;


/**
 * ParÃ¡metro con todos los atributos necesarios para poder realizar
 * una bÃºsqueda
 */
public class ParametrosBusquedaAgregadorVO  implements java.io.Serializable {
    private java.lang.String valoracion;

    private java.lang.String titulo;

    private java.lang.Boolean secuencia;

    private java.lang.String recurso;

    private java.lang.String procesoCognitivo;

    private java.lang.String palabrasClave;

    private java.lang.Integer origenPagina;

    private java.lang.Integer numeroResultados;

    private java.lang.String enlaceTaxSelec;

    private java.lang.String idiomaNavegacion;

    private java.lang.String idiomaBusqueda;

    private java.lang.String idBusqueda;

    private java.lang.String formato;

    private java.lang.String fechaPublicacion;

    private java.lang.String edad;

    private java.lang.String descripcion;

    private java.lang.String contexto;

    private java.lang.String[] comunidadesSeleccionadas;

    private java.lang.String autor;

    private java.lang.Integer resultadosPorPagina;

    private java.lang.String nivelAgregacion;

    private java.lang.String arbolCurricularVigente;

    private java.lang.String destinatarios;

    private java.lang.String keyword;

    private java.lang.String busquedaSimpleAvanzada;

    private java.lang.String idTesauro;

    /* indica el tipo de Busqueda que se ha realizado (local, federada,
     * personalizada...) */
    private java.lang.String tipoBusqueda;

    /* nodo local */
    private java.lang.String comunidadPeticion;

    private java.lang.String[] taxonomia;

    private java.lang.String enlaceComunidadesSeleccionadas;

    private java.lang.String idODE;

    public ParametrosBusquedaAgregadorVO() {
    }

    public ParametrosBusquedaAgregadorVO(
           java.lang.String valoracion,
           java.lang.String titulo,
           java.lang.Boolean secuencia,
           java.lang.String recurso,
           java.lang.String procesoCognitivo,
           java.lang.String palabrasClave,
           java.lang.Integer origenPagina,
           java.lang.Integer numeroResultados,
           java.lang.String enlaceTaxSelec,
           java.lang.String idiomaNavegacion,
           java.lang.String idiomaBusqueda,
           java.lang.String idBusqueda,
           java.lang.String formato,
           java.lang.String fechaPublicacion,
           java.lang.String edad,
           java.lang.String descripcion,
           java.lang.String contexto,
           java.lang.String[] comunidadesSeleccionadas,
           java.lang.String autor,
           java.lang.Integer resultadosPorPagina,
           java.lang.String nivelAgregacion,
           java.lang.String arbolCurricularVigente,
           java.lang.String destinatarios,
           java.lang.String keyword,
           java.lang.String busquedaSimpleAvanzada,
           java.lang.String idTesauro,
           java.lang.String tipoBusqueda,
           java.lang.String comunidadPeticion,
           java.lang.String[] taxonomia,
           java.lang.String enlaceComunidadesSeleccionadas,
           java.lang.String idODE) {
           this.valoracion = valoracion;
           this.titulo = titulo;
           this.secuencia = secuencia;
           this.recurso = recurso;
           this.procesoCognitivo = procesoCognitivo;
           this.palabrasClave = palabrasClave;
           this.origenPagina = origenPagina;
           this.numeroResultados = numeroResultados;
           this.enlaceTaxSelec = enlaceTaxSelec;
           this.idiomaNavegacion = idiomaNavegacion;
           this.idiomaBusqueda = idiomaBusqueda;
           this.idBusqueda = idBusqueda;
           this.formato = formato;
           this.fechaPublicacion = fechaPublicacion;
           this.edad = edad;
           this.descripcion = descripcion;
           this.contexto = contexto;
           this.comunidadesSeleccionadas = comunidadesSeleccionadas;
           this.autor = autor;
           this.resultadosPorPagina = resultadosPorPagina;
           this.nivelAgregacion = nivelAgregacion;
           this.arbolCurricularVigente = arbolCurricularVigente;
           this.destinatarios = destinatarios;
           this.keyword = keyword;
           this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
           this.idTesauro = idTesauro;
           this.tipoBusqueda = tipoBusqueda;
           this.comunidadPeticion = comunidadPeticion;
           this.taxonomia = taxonomia;
           this.enlaceComunidadesSeleccionadas = enlaceComunidadesSeleccionadas;
           this.idODE = idODE;
    }


    /**
     * Gets the valoracion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return valoracion
     */
    public java.lang.String getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param valoracion
     */
    public void setValoracion(java.lang.String valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the titulo value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the secuencia value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return secuencia
     */
    public java.lang.Boolean getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param secuencia
     */
    public void setSecuencia(java.lang.Boolean secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the recurso value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return recurso
     */
    public java.lang.String getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param recurso
     */
    public void setRecurso(java.lang.String recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the procesoCognitivo value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return procesoCognitivo
     */
    public java.lang.String getProcesoCognitivo() {
        return procesoCognitivo;
    }


    /**
     * Sets the procesoCognitivo value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param procesoCognitivo
     */
    public void setProcesoCognitivo(java.lang.String procesoCognitivo) {
        this.procesoCognitivo = procesoCognitivo;
    }


    /**
     * Gets the palabrasClave value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return palabrasClave
     */
    public java.lang.String getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param palabrasClave
     */
    public void setPalabrasClave(java.lang.String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the origenPagina value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return origenPagina
     */
    public java.lang.Integer getOrigenPagina() {
        return origenPagina;
    }


    /**
     * Sets the origenPagina value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param origenPagina
     */
    public void setOrigenPagina(java.lang.Integer origenPagina) {
        this.origenPagina = origenPagina;
    }


    /**
     * Gets the numeroResultados value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return numeroResultados
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param numeroResultados
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the enlaceTaxSelec value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return enlaceTaxSelec
     */
    public java.lang.String getEnlaceTaxSelec() {
        return enlaceTaxSelec;
    }


    /**
     * Sets the enlaceTaxSelec value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param enlaceTaxSelec
     */
    public void setEnlaceTaxSelec(java.lang.String enlaceTaxSelec) {
        this.enlaceTaxSelec = enlaceTaxSelec;
    }


    /**
     * Gets the idiomaNavegacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return idiomaNavegacion
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param idiomaNavegacion
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the idiomaBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the idBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return idBusqueda
     */
    public java.lang.String getIdBusqueda() {
        return idBusqueda;
    }


    /**
     * Sets the idBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param idBusqueda
     */
    public void setIdBusqueda(java.lang.String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }


    /**
     * Gets the formato value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return formato
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param formato
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the fechaPublicacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the edad value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the descripcion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the contexto value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return contexto
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param contexto
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the comunidadesSeleccionadas value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return comunidadesSeleccionadas
     */
    public java.lang.String[] getComunidadesSeleccionadas() {
        return comunidadesSeleccionadas;
    }


    /**
     * Sets the comunidadesSeleccionadas value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param comunidadesSeleccionadas
     */
    public void setComunidadesSeleccionadas(java.lang.String[] comunidadesSeleccionadas) {
        this.comunidadesSeleccionadas = comunidadesSeleccionadas;
    }


    /**
     * Gets the autor value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return autor
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param autor
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the resultadosPorPagina value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return resultadosPorPagina
     */
    public java.lang.Integer getResultadosPorPagina() {
        return resultadosPorPagina;
    }


    /**
     * Sets the resultadosPorPagina value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param resultadosPorPagina
     */
    public void setResultadosPorPagina(java.lang.Integer resultadosPorPagina) {
        this.resultadosPorPagina = resultadosPorPagina;
    }


    /**
     * Gets the nivelAgregacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the arbolCurricularVigente value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return arbolCurricularVigente
     */
    public java.lang.String getArbolCurricularVigente() {
        return arbolCurricularVigente;
    }


    /**
     * Sets the arbolCurricularVigente value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param arbolCurricularVigente
     */
    public void setArbolCurricularVigente(java.lang.String arbolCurricularVigente) {
        this.arbolCurricularVigente = arbolCurricularVigente;
    }


    /**
     * Gets the destinatarios value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return destinatarios
     */
    public java.lang.String getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(java.lang.String destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the keyword value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return keyword
     */
    public java.lang.String getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param keyword
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the busquedaSimpleAvanzada value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return busquedaSimpleAvanzada
     */
    public java.lang.String getBusquedaSimpleAvanzada() {
        return busquedaSimpleAvanzada;
    }


    /**
     * Sets the busquedaSimpleAvanzada value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param busquedaSimpleAvanzada
     */
    public void setBusquedaSimpleAvanzada(java.lang.String busquedaSimpleAvanzada) {
        this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
    }


    /**
     * Gets the idTesauro value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return idTesauro
     */
    public java.lang.String getIdTesauro() {
        return idTesauro;
    }


    /**
     * Sets the idTesauro value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param idTesauro
     */
    public void setIdTesauro(java.lang.String idTesauro) {
        this.idTesauro = idTesauro;
    }


    /**
     * Gets the tipoBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return tipoBusqueda   * indica el tipo de Busqueda que se ha realizado (local, federada,
     * personalizada...)
     */
    public java.lang.String getTipoBusqueda() {
        return tipoBusqueda;
    }


    /**
     * Sets the tipoBusqueda value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param tipoBusqueda   * indica el tipo de Busqueda que se ha realizado (local, federada,
     * personalizada...)
     */
    public void setTipoBusqueda(java.lang.String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }


    /**
     * Gets the comunidadPeticion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return comunidadPeticion   * nodo local
     */
    public java.lang.String getComunidadPeticion() {
        return comunidadPeticion;
    }


    /**
     * Sets the comunidadPeticion value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param comunidadPeticion   * nodo local
     */
    public void setComunidadPeticion(java.lang.String comunidadPeticion) {
        this.comunidadPeticion = comunidadPeticion;
    }


    /**
     * Gets the taxonomia value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return taxonomia
     */
    public java.lang.String[] getTaxonomia() {
        return taxonomia;
    }


    /**
     * Sets the taxonomia value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param taxonomia
     */
    public void setTaxonomia(java.lang.String[] taxonomia) {
        this.taxonomia = taxonomia;
    }


    /**
     * Gets the enlaceComunidadesSeleccionadas value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return enlaceComunidadesSeleccionadas
     */
    public java.lang.String getEnlaceComunidadesSeleccionadas() {
        return enlaceComunidadesSeleccionadas;
    }


    /**
     * Sets the enlaceComunidadesSeleccionadas value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param enlaceComunidadesSeleccionadas
     */
    public void setEnlaceComunidadesSeleccionadas(java.lang.String enlaceComunidadesSeleccionadas) {
        this.enlaceComunidadesSeleccionadas = enlaceComunidadesSeleccionadas;
    }


    /**
     * Gets the idODE value for this ParametrosBusquedaAgregadorVO.
     * 
     * @return idODE
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this ParametrosBusquedaAgregadorVO.
     * 
     * @param idODE
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBusquedaAgregadorVO)) return false;
        ParametrosBusquedaAgregadorVO other = (ParametrosBusquedaAgregadorVO) obj;
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
            ((this.origenPagina==null && other.getOrigenPagina()==null) || 
             (this.origenPagina!=null &&
              this.origenPagina.equals(other.getOrigenPagina()))) &&
            ((this.numeroResultados==null && other.getNumeroResultados()==null) || 
             (this.numeroResultados!=null &&
              this.numeroResultados.equals(other.getNumeroResultados()))) &&
            ((this.enlaceTaxSelec==null && other.getEnlaceTaxSelec()==null) || 
             (this.enlaceTaxSelec!=null &&
              this.enlaceTaxSelec.equals(other.getEnlaceTaxSelec()))) &&
            ((this.idiomaNavegacion==null && other.getIdiomaNavegacion()==null) || 
             (this.idiomaNavegacion!=null &&
              this.idiomaNavegacion.equals(other.getIdiomaNavegacion()))) &&
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda()))) &&
            ((this.idBusqueda==null && other.getIdBusqueda()==null) || 
             (this.idBusqueda!=null &&
              this.idBusqueda.equals(other.getIdBusqueda()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
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
            ((this.comunidadesSeleccionadas==null && other.getComunidadesSeleccionadas()==null) || 
             (this.comunidadesSeleccionadas!=null &&
              java.util.Arrays.equals(this.comunidadesSeleccionadas, other.getComunidadesSeleccionadas()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.resultadosPorPagina==null && other.getResultadosPorPagina()==null) || 
             (this.resultadosPorPagina!=null &&
              this.resultadosPorPagina.equals(other.getResultadosPorPagina()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.arbolCurricularVigente==null && other.getArbolCurricularVigente()==null) || 
             (this.arbolCurricularVigente!=null &&
              this.arbolCurricularVigente.equals(other.getArbolCurricularVigente()))) &&
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              this.destinatarios.equals(other.getDestinatarios()))) &&
            ((this.keyword==null && other.getKeyword()==null) || 
             (this.keyword!=null &&
              this.keyword.equals(other.getKeyword()))) &&
            ((this.busquedaSimpleAvanzada==null && other.getBusquedaSimpleAvanzada()==null) || 
             (this.busquedaSimpleAvanzada!=null &&
              this.busquedaSimpleAvanzada.equals(other.getBusquedaSimpleAvanzada()))) &&
            ((this.idTesauro==null && other.getIdTesauro()==null) || 
             (this.idTesauro!=null &&
              this.idTesauro.equals(other.getIdTesauro()))) &&
            ((this.tipoBusqueda==null && other.getTipoBusqueda()==null) || 
             (this.tipoBusqueda!=null &&
              this.tipoBusqueda.equals(other.getTipoBusqueda()))) &&
            ((this.comunidadPeticion==null && other.getComunidadPeticion()==null) || 
             (this.comunidadPeticion!=null &&
              this.comunidadPeticion.equals(other.getComunidadPeticion()))) &&
            ((this.taxonomia==null && other.getTaxonomia()==null) || 
             (this.taxonomia!=null &&
              java.util.Arrays.equals(this.taxonomia, other.getTaxonomia()))) &&
            ((this.enlaceComunidadesSeleccionadas==null && other.getEnlaceComunidadesSeleccionadas()==null) || 
             (this.enlaceComunidadesSeleccionadas!=null &&
              this.enlaceComunidadesSeleccionadas.equals(other.getEnlaceComunidadesSeleccionadas()))) &&
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE())));
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
        if (getOrigenPagina() != null) {
            _hashCode += getOrigenPagina().hashCode();
        }
        if (getNumeroResultados() != null) {
            _hashCode += getNumeroResultados().hashCode();
        }
        if (getEnlaceTaxSelec() != null) {
            _hashCode += getEnlaceTaxSelec().hashCode();
        }
        if (getIdiomaNavegacion() != null) {
            _hashCode += getIdiomaNavegacion().hashCode();
        }
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        if (getIdBusqueda() != null) {
            _hashCode += getIdBusqueda().hashCode();
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
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
        if (getComunidadesSeleccionadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesSeleccionadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesSeleccionadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutor() != null) {
            _hashCode += getAutor().hashCode();
        }
        if (getResultadosPorPagina() != null) {
            _hashCode += getResultadosPorPagina().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getArbolCurricularVigente() != null) {
            _hashCode += getArbolCurricularVigente().hashCode();
        }
        if (getDestinatarios() != null) {
            _hashCode += getDestinatarios().hashCode();
        }
        if (getKeyword() != null) {
            _hashCode += getKeyword().hashCode();
        }
        if (getBusquedaSimpleAvanzada() != null) {
            _hashCode += getBusquedaSimpleAvanzada().hashCode();
        }
        if (getIdTesauro() != null) {
            _hashCode += getIdTesauro().hashCode();
        }
        if (getTipoBusqueda() != null) {
            _hashCode += getTipoBusqueda().hashCode();
        }
        if (getComunidadPeticion() != null) {
            _hashCode += getComunidadPeticion().hashCode();
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
        if (getEnlaceComunidadesSeleccionadas() != null) {
            _hashCode += getEnlaceComunidadesSeleccionadas().hashCode();
        }
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBusquedaAgregadorVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "ParametrosBusquedaAgregadorVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCognitivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "procesoCognitivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "origenPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enlaceTaxSelec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "enlaceTaxSelec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "idBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesSeleccionadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "comunidadesSeleccionadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosPorPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "resultadosPorPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arbolCurricularVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "arbolCurricularVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaSimpleAvanzada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "busquedaSimpleAvanzada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTesauro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "idTesauro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "tipoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadPeticion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "comunidadPeticion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "taxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enlaceComunidadesSeleccionadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "enlaceComunidadesSeleccionadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "idODE"));
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
