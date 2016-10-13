/**
 * SrvBuscadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;

public interface SrvBuscadorService extends java.rmi.Remote {

    /**
     * Permite una bÃºsqueda avanzada por distintos campos en el
     *                 repositorio de ODEs indexados
     *                 Retorna los documentos que cumplen con la bÃºsqueda.
     */
    public es.pode.soporte.buscador.servicios.DocumentosVO30 busquedaAvanzada(es.pode.soporte.buscador.servicios.ParamAvanzadoVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo busca los ODEs indexados en todos los indices que
     * cumplan las condiciones de busqueda que le pasan como parametro.
     *                 Las fechas desde y hasta se interpretan inclusives
     * y en el caso
     *                 de no estar presentes, sin limite superior o inferior.
     */
    public es.pode.soporte.buscador.servicios.ResultadoHeaderVO[] busquedaHeadersRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo tiene que buscar los resultados que ajusten a los
     * parametros de busqueda. Ordena los resultados y los devuelve
     *                 convertidos en un texto LOM-ES.
     */
    public es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO busquedaLOM_ES(es.pode.soporte.buscador.servicios.QuerySimpleVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Metodo para obtener la busqueda e introducirle la localizacion
     * de la ficha
     */
    public es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO busquedaLOM_ESVSQL(es.pode.soporte.buscador.servicios.QuerySimpleVO paramBusqueda) throws java.rmi.RemoteException;

    /**
     * Este metodo realiza una busqueda utilizando el identificador
     * MEC
     *                 de un ODE como parametro de busqueda.
     *                 En el caso de encontrar un resultado, devuelve un
     * VO con la
     *                 coleccion de caracteristicas que describen el ODE.
     * En el caso de
     *                 no encontrar ningun resultado, no devuelve nada.
     */
    public es.pode.soporte.buscador.servicios.DocVO30 busquedaMEC(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException;

    /**
     * Este metodo busca el identificador en todo el repositorio del
     * indexador.
     */
    public es.pode.soporte.buscador.servicios.ResultadoRecordVO busquedaMECRepositorio(java.lang.String idMEC) throws java.rmi.RemoteException;

    /**
     * Este metodo realiza una busqueda utilizando el identificador
     * MEC
     *                 de un ODE como parametro de busqueda.
     *                 En el caso de encontrar un resultado, devuelve un
     * VO con la
     *                 coleccion de caracteristicas que describen el ODE.
     * En el caso de
     *                 no encontrar ningun resultado, no devuelve nada.
     *                 Esta versión sólo devuelve nivel de agregación, áreas
     * curriculares, tesauros y localizador
     */
    public es.pode.soporte.buscador.servicios.DocMECSimpleVO busquedaMECSimple(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException;

    /**
     * Metodo que ejecuta una busqueda en todo el repositorio con
     * los
     *                 parametros de busqueda que le pasan.
     */
    public es.pode.soporte.buscador.servicios.ResultadoRecordVO[] busquedaRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Permite la bÃºsqueda de cadenas de texto en el repositorio
     * de
     *                 ODEs indexados.
     */
    public es.pode.soporte.buscador.servicios.DocumentosVO30 busquedaSimple(es.pode.soporte.buscador.servicios.ParamSimpleVO paramBusq) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.buscador.servicios.DocVO30[] busquedaVariosMEC(es.pode.soporte.buscador.servicios.ParamBuscarMecVO[] parametros) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve la fecha del ODE mas viejo indexado en
     * el
     *                 repositorio.
     */
    public java.util.Calendar fechaInicioRepositorio() throws java.rmi.RemoteException;

    /**
     * Metodo para recoger los ODE que tienen el nivel de agregacion
     * mayor o igual el que le pasan.
     */
    public es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[] obtenerCatalogoRepositorioParam(java.lang.Integer nivelAgregacion) throws java.rmi.RemoteException;

    /**
     * Este metodo busca un ODE al azar de dentro del repositorio.
     */
    public es.pode.soporte.buscador.servicios.DocVO30 obtenerODERandom() throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda en el Ã­ndice para devolver
     * la nube de tags.
     *                 Retorna el listado obtenido del Ã­ndice con el nÃºmero
     * de
     *                 resultados.
     */
    public es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO obtenerPalabrasClave(es.pode.soporte.buscador.servicios.ParamPalabrasClave paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve todos los ODEs indexados en el repositorio
     * para todos los idiomas, pero solo devuelve los ODEs publicos.
     */
    public es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[] obtenerRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramRepositorio) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.buscador.servicios.SugerenciasVO[] obtenerSugerencias(es.pode.soporte.buscador.servicios.ParamAvanzadoVO paramSug) throws java.rmi.RemoteException;

    /**
     * Obtiene el nÃºmero total de odes que existen en todos los
     *                 indices
     */
    public es.pode.soporte.buscador.servicios.ResultadosCountVO obtenerTotalesRepositorio() throws java.rmi.RemoteException;

    /**
     * Obtiene el nÃºmero total de odes que existen en todos los
     *                 indices en un periodo de tiempo entre las fechas desde
     * y hasta.
     */
    public es.pode.soporte.buscador.servicios.ResultadosCountVO obtenerTotalesRepositorioFechas(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramPeriodoVO) throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda que devuelve el nÃºmero
     * de
     *                 documentos asociados a un Ã¡rea curricular en funciÃ³n
     * de unos
     *                 parÃ¡metros que se le pasan.
     */
    public es.pode.soporte.buscador.servicios.ResultadosCountVO solicitudDocsCount(es.pode.soporte.buscador.servicios.ParamDocsCountVO paramBusq) throws java.rmi.RemoteException;
}
