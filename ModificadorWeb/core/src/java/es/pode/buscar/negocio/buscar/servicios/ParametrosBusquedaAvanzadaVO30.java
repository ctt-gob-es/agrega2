/**
 * ParametrosBusquedaAvanzadaVO30.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;


/**
 * Esta clase encapsula todos los parametros configurables para la
 * realizacion de una busqueda en el servicio de Buscar.
 */
public class ParametrosBusquedaAvanzadaVO30  implements java.io.Serializable {
    /* Valoracion del ODE dentro de la plataforma. */
    private java.lang.String valoracion;

    /* Titulo del ODE. */
    private java.lang.String titulo;

    /* Con secuencia o sin secuencia. */
    private java.lang.Boolean secuencia;

    /* Recurso del ODE. */
    private java.lang.String recurso;

    /* Proceso cognitivo del ODE. */
    private java.lang.String procesoCognitivo;

    /* Se trata de las palabras clave por las que se quiere buscar. */
    private java.lang.String palabrasClave;

    /* Indice del conjunto total de resultados a partir del cual se
     * quieren obtener un conjunto de resultados. */
    private java.lang.Integer origenPagina;

    /* Numero de resultados maximo que se quieren devolver. */
    private java.lang.Integer numeroResultados;

    /* El taxonomia del ODE. */
    private java.lang.String[] taxonomia;

    /* Se trata del idioma con el que el usuario navega por la
     *                                 aplicacion. */
    private java.lang.String idiomaNavegacion;

    /* Idioma por el que se quiere configurar la busqueda. */
    private java.lang.String idiomaBusqueda;

    /* El identificador de la busqueda en el caso de que el cliente
     * que
     *                                 invoca la misma ya la haya realizado
     * anteriormente y solo quiera
     *                                 paginar sobre los resultados de la
     * misma. */
    private java.lang.String idBusqueda;

    /* Formato del ODE. */
    private java.lang.String[] formato;

    /* Fecha de publicacion del ODE. */
    private java.lang.String fechaPublicacion;

    /* Edad de los destinatarios de la accion lectiva del ODE. */
    private java.lang.String edad;

    /* Descripcion del ODE. */
    private java.lang.String descripcion;

    /* Contexto del ODE. */
    private java.lang.String contexto;

    /* Comunidades en las que se busca el ODE. */
    private java.lang.String[] comunidadesSeleccionadas;

    /* Autor del ODE. */
    private java.lang.String autor;

    /* El numero de resultados por pagina en el que se esta interesado. */
    private java.lang.Integer resultadosPorPagina;

    private java.lang.String comunidadPeticion;

    private java.lang.String busquedaSimpleAvanzada;

    private java.lang.String[] nivelAgregacion;

    private java.lang.String destinatarios;

    private java.lang.String keyword;

    private java.lang.String ambito;

    private java.lang.String identificador;

    /* La url de el nodo destino de la busqueda federada */
    private java.lang.String comunidadDestino;

    /* La version del nodo destino de la peticion de busqueda federada */
    private java.lang.String versionComunidadDestino;

    public ParametrosBusquedaAvanzadaVO30() {
    }

    public ParametrosBusquedaAvanzadaVO30(
           java.lang.String valoracion,
           java.lang.String titulo,
           java.lang.Boolean secuencia,
           java.lang.String recurso,
           java.lang.String procesoCognitivo,
           java.lang.String palabrasClave,
           java.lang.Integer origenPagina,
           java.lang.Integer numeroResultados,
           java.lang.String[] taxonomia,
           java.lang.String idiomaNavegacion,
           java.lang.String idiomaBusqueda,
           java.lang.String idBusqueda,
           java.lang.String[] formato,
           java.lang.String fechaPublicacion,
           java.lang.String edad,
           java.lang.String descripcion,
           java.lang.String contexto,
           java.lang.String[] comunidadesSeleccionadas,
           java.lang.String autor,
           java.lang.Integer resultadosPorPagina,
           java.lang.String comunidadPeticion,
           java.lang.String busquedaSimpleAvanzada,
           java.lang.String[] nivelAgregacion,
           java.lang.String destinatarios,
           java.lang.String keyword,
           java.lang.String ambito,
           java.lang.String identificador,
           java.lang.String comunidadDestino,
           java.lang.String versionComunidadDestino) {
           this.valoracion = valoracion;
           this.titulo = titulo;
           this.secuencia = secuencia;
           this.recurso = recurso;
           this.procesoCognitivo = procesoCognitivo;
           this.palabrasClave = palabrasClave;
           this.origenPagina = origenPagina;
           this.numeroResultados = numeroResultados;
           this.taxonomia = taxonomia;
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
           this.comunidadPeticion = comunidadPeticion;
           this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
           this.nivelAgregacion = nivelAgregacion;
           this.destinatarios = destinatarios;
           this.keyword = keyword;
           this.ambito = ambito;
           this.identificador = identificador;
           this.comunidadDestino = comunidadDestino;
           this.versionComunidadDestino = versionComunidadDestino;
    }


    /**
     * Gets the valoracion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public java.lang.String getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param valoracion   * Valoracion del ODE dentro de la plataforma.
     */
    public void setValoracion(java.lang.String valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the titulo value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return titulo   * Titulo del ODE.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param titulo   * Titulo del ODE.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the secuencia value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return secuencia   * Con secuencia o sin secuencia.
     */
    public java.lang.Boolean getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param secuencia   * Con secuencia o sin secuencia.
     */
    public void setSecuencia(java.lang.Boolean secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the recurso value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return recurso   * Recurso del ODE.
     */
    public java.lang.String getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param recurso   * Recurso del ODE.
     */
    public void setRecurso(java.lang.String recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the procesoCognitivo value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return procesoCognitivo   * Proceso cognitivo del ODE.
     */
    public java.lang.String getProcesoCognitivo() {
        return procesoCognitivo;
    }


    /**
     * Sets the procesoCognitivo value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param procesoCognitivo   * Proceso cognitivo del ODE.
     */
    public void setProcesoCognitivo(java.lang.String procesoCognitivo) {
        this.procesoCognitivo = procesoCognitivo;
    }


    /**
     * Gets the palabrasClave value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return palabrasClave   * Se trata de las palabras clave por las que se quiere buscar.
     */
    public java.lang.String getPalabrasClave() {
        return palabrasClave;
    }


    /**
     * Sets the palabrasClave value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param palabrasClave   * Se trata de las palabras clave por las que se quiere buscar.
     */
    public void setPalabrasClave(java.lang.String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }


    /**
     * Gets the origenPagina value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return origenPagina   * Indice del conjunto total de resultados a partir del cual se
     * quieren obtener un conjunto de resultados.
     */
    public java.lang.Integer getOrigenPagina() {
        return origenPagina;
    }


    /**
     * Sets the origenPagina value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param origenPagina   * Indice del conjunto total de resultados a partir del cual se
     * quieren obtener un conjunto de resultados.
     */
    public void setOrigenPagina(java.lang.Integer origenPagina) {
        this.origenPagina = origenPagina;
    }


    /**
     * Gets the numeroResultados value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return numeroResultados   * Numero de resultados maximo que se quieren devolver.
     */
    public java.lang.Integer getNumeroResultados() {
        return numeroResultados;
    }


    /**
     * Sets the numeroResultados value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param numeroResultados   * Numero de resultados maximo que se quieren devolver.
     */
    public void setNumeroResultados(java.lang.Integer numeroResultados) {
        this.numeroResultados = numeroResultados;
    }


    /**
     * Gets the taxonomia value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return taxonomia   * El taxonomia del ODE.
     */
    public java.lang.String[] getTaxonomia() {
        return taxonomia;
    }


    /**
     * Sets the taxonomia value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param taxonomia   * El taxonomia del ODE.
     */
    public void setTaxonomia(java.lang.String[] taxonomia) {
        this.taxonomia = taxonomia;
    }


    /**
     * Gets the idiomaNavegacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return idiomaNavegacion   * Se trata del idioma con el que el usuario navega por la
     *                                 aplicacion.
     */
    public java.lang.String getIdiomaNavegacion() {
        return idiomaNavegacion;
    }


    /**
     * Sets the idiomaNavegacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param idiomaNavegacion   * Se trata del idioma con el que el usuario navega por la
     *                                 aplicacion.
     */
    public void setIdiomaNavegacion(java.lang.String idiomaNavegacion) {
        this.idiomaNavegacion = idiomaNavegacion;
    }


    /**
     * Gets the idiomaBusqueda value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return idiomaBusqueda   * Idioma por el que se quiere configurar la busqueda.
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param idiomaBusqueda   * Idioma por el que se quiere configurar la busqueda.
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the idBusqueda value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return idBusqueda   * El identificador de la busqueda en el caso de que el cliente
     * que
     *                                 invoca la misma ya la haya realizado
     * anteriormente y solo quiera
     *                                 paginar sobre los resultados de la
     * misma.
     */
    public java.lang.String getIdBusqueda() {
        return idBusqueda;
    }


    /**
     * Sets the idBusqueda value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param idBusqueda   * El identificador de la busqueda en el caso de que el cliente
     * que
     *                                 invoca la misma ya la haya realizado
     * anteriormente y solo quiera
     *                                 paginar sobre los resultados de la
     * misma.
     */
    public void setIdBusqueda(java.lang.String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }


    /**
     * Gets the formato value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return formato   * Formato del ODE.
     */
    public java.lang.String[] getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param formato   * Formato del ODE.
     */
    public void setFormato(java.lang.String[] formato) {
        this.formato = formato;
    }


    /**
     * Gets the fechaPublicacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return fechaPublicacion   * Fecha de publicacion del ODE.
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param fechaPublicacion   * Fecha de publicacion del ODE.
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the edad value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return edad   * Edad de los destinatarios de la accion lectiva del ODE.
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param edad   * Edad de los destinatarios de la accion lectiva del ODE.
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the descripcion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return descripcion   * Descripcion del ODE.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param descripcion   * Descripcion del ODE.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the contexto value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return contexto   * Contexto del ODE.
     */
    public java.lang.String getContexto() {
        return contexto;
    }


    /**
     * Sets the contexto value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param contexto   * Contexto del ODE.
     */
    public void setContexto(java.lang.String contexto) {
        this.contexto = contexto;
    }


    /**
     * Gets the comunidadesSeleccionadas value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return comunidadesSeleccionadas   * Comunidades en las que se busca el ODE.
     */
    public java.lang.String[] getComunidadesSeleccionadas() {
        return comunidadesSeleccionadas;
    }


    /**
     * Sets the comunidadesSeleccionadas value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param comunidadesSeleccionadas   * Comunidades en las que se busca el ODE.
     */
    public void setComunidadesSeleccionadas(java.lang.String[] comunidadesSeleccionadas) {
        this.comunidadesSeleccionadas = comunidadesSeleccionadas;
    }


    /**
     * Gets the autor value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return autor   * Autor del ODE.
     */
    public java.lang.String getAutor() {
        return autor;
    }


    /**
     * Sets the autor value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param autor   * Autor del ODE.
     */
    public void setAutor(java.lang.String autor) {
        this.autor = autor;
    }


    /**
     * Gets the resultadosPorPagina value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return resultadosPorPagina   * El numero de resultados por pagina en el que se esta interesado.
     */
    public java.lang.Integer getResultadosPorPagina() {
        return resultadosPorPagina;
    }


    /**
     * Sets the resultadosPorPagina value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param resultadosPorPagina   * El numero de resultados por pagina en el que se esta interesado.
     */
    public void setResultadosPorPagina(java.lang.Integer resultadosPorPagina) {
        this.resultadosPorPagina = resultadosPorPagina;
    }


    /**
     * Gets the comunidadPeticion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return comunidadPeticion
     */
    public java.lang.String getComunidadPeticion() {
        return comunidadPeticion;
    }


    /**
     * Sets the comunidadPeticion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param comunidadPeticion
     */
    public void setComunidadPeticion(java.lang.String comunidadPeticion) {
        this.comunidadPeticion = comunidadPeticion;
    }


    /**
     * Gets the busquedaSimpleAvanzada value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return busquedaSimpleAvanzada
     */
    public java.lang.String getBusquedaSimpleAvanzada() {
        return busquedaSimpleAvanzada;
    }


    /**
     * Sets the busquedaSimpleAvanzada value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param busquedaSimpleAvanzada
     */
    public void setBusquedaSimpleAvanzada(java.lang.String busquedaSimpleAvanzada) {
        this.busquedaSimpleAvanzada = busquedaSimpleAvanzada;
    }


    /**
     * Gets the nivelAgregacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return nivelAgregacion
     */
    public java.lang.String[] getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.String[] nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the destinatarios value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return destinatarios
     */
    public java.lang.String getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(java.lang.String destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the keyword value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return keyword
     */
    public java.lang.String getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param keyword
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the ambito value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return ambito
     */
    public java.lang.String getAmbito() {
        return ambito;
    }


    /**
     * Sets the ambito value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param ambito
     */
    public void setAmbito(java.lang.String ambito) {
        this.ambito = ambito;
    }


    /**
     * Gets the identificador value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return identificador
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param identificador
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the comunidadDestino value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return comunidadDestino   * La url de el nodo destino de la busqueda federada
     */
    public java.lang.String getComunidadDestino() {
        return comunidadDestino;
    }


    /**
     * Sets the comunidadDestino value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param comunidadDestino   * La url de el nodo destino de la busqueda federada
     */
    public void setComunidadDestino(java.lang.String comunidadDestino) {
        this.comunidadDestino = comunidadDestino;
    }


    /**
     * Gets the versionComunidadDestino value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @return versionComunidadDestino   * La version del nodo destino de la peticion de busqueda federada
     */
    public java.lang.String getVersionComunidadDestino() {
        return versionComunidadDestino;
    }


    /**
     * Sets the versionComunidadDestino value for this ParametrosBusquedaAvanzadaVO30.
     * 
     * @param versionComunidadDestino   * La version del nodo destino de la peticion de busqueda federada
     */
    public void setVersionComunidadDestino(java.lang.String versionComunidadDestino) {
        this.versionComunidadDestino = versionComunidadDestino;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBusquedaAvanzadaVO30)) return false;
        ParametrosBusquedaAvanzadaVO30 other = (ParametrosBusquedaAvanzadaVO30) obj;
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
            ((this.taxonomia==null && other.getTaxonomia()==null) || 
             (this.taxonomia!=null &&
              java.util.Arrays.equals(this.taxonomia, other.getTaxonomia()))) &&
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
            ((this.comunidadesSeleccionadas==null && other.getComunidadesSeleccionadas()==null) || 
             (this.comunidadesSeleccionadas!=null &&
              java.util.Arrays.equals(this.comunidadesSeleccionadas, other.getComunidadesSeleccionadas()))) &&
            ((this.autor==null && other.getAutor()==null) || 
             (this.autor!=null &&
              this.autor.equals(other.getAutor()))) &&
            ((this.resultadosPorPagina==null && other.getResultadosPorPagina()==null) || 
             (this.resultadosPorPagina!=null &&
              this.resultadosPorPagina.equals(other.getResultadosPorPagina()))) &&
            ((this.comunidadPeticion==null && other.getComunidadPeticion()==null) || 
             (this.comunidadPeticion!=null &&
              this.comunidadPeticion.equals(other.getComunidadPeticion()))) &&
            ((this.busquedaSimpleAvanzada==null && other.getBusquedaSimpleAvanzada()==null) || 
             (this.busquedaSimpleAvanzada!=null &&
              this.busquedaSimpleAvanzada.equals(other.getBusquedaSimpleAvanzada()))) &&
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
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.comunidadDestino==null && other.getComunidadDestino()==null) || 
             (this.comunidadDestino!=null &&
              this.comunidadDestino.equals(other.getComunidadDestino()))) &&
            ((this.versionComunidadDestino==null && other.getVersionComunidadDestino()==null) || 
             (this.versionComunidadDestino!=null &&
              this.versionComunidadDestino.equals(other.getVersionComunidadDestino())));
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
        if (getIdBusqueda() != null) {
            _hashCode += getIdBusqueda().hashCode();
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
        if (getComunidadPeticion() != null) {
            _hashCode += getComunidadPeticion().hashCode();
        }
        if (getBusquedaSimpleAvanzada() != null) {
            _hashCode += getBusquedaSimpleAvanzada().hashCode();
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
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getComunidadDestino() != null) {
            _hashCode += getComunidadDestino().hashCode();
        }
        if (getVersionComunidadDestino() != null) {
            _hashCode += getVersionComunidadDestino().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBusquedaAvanzadaVO30.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaAvanzadaVO30"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesoCognitivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "procesoCognitivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "palabrasClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "origenPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroResultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numeroResultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxonomia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "taxonomia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaNavegacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaNavegacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "idBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "contexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesSeleccionadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesSeleccionadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "autor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosPorPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "resultadosPorPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadPeticion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadPeticion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaSimpleAvanzada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "busquedaSimpleAvanzada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ambito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ambito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionComunidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "versionComunidadDestino"));
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
