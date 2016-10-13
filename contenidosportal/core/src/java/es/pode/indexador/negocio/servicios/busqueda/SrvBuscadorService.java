/**
 * SrvBuscadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public interface SrvBuscadorService extends java.rmi.Remote {

    /**
     * Permite una busqueda avanzada por distintos campos en el
     *                 repositorio de ODEs indexados
     *                 Retorna los documentos que cumplen con la busqueda.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 busquedaAvanzada(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Permite una busqueda avanzada por distintos campos en el
     *                 repositorio de ODEs indexados
     *                 Retorna los documentos que cumplen con la busqueda.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 busquedaAvanzadaNodos(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq, java.lang.String nodo) throws java.rmi.RemoteException;

    /**
     * Este metodo busca los ODEs indexados en todos los indices que
     * cumplan las condiciones de busqueda que le pasan como parametro.
     *                 Las fechas desde y hasta se interpretan inclusives
     * y en el caso
     *                 de no estar presentes, sin limite superior o inferior.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] busquedaHeadersRepositorio(es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo tiene que buscar los resultados que ajusten a los
     * parametros de busqueda. Ordena los resultados y los devuelve
     *                 convertidos en un texto LOM-ES.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosLOM_ESVO busquedaLOM_ES(es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Metodo para obtener la busqueda e introducirle la localizacion
     * de la ficha
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosLOM_ESVO busquedaLOM_ESVSQL(es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO paramBusqueda) throws java.rmi.RemoteException;

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
    public es.pode.indexador.negocio.servicios.busqueda.DocVO30 busquedaMEC(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException;

    /**
     * Este metodo busca el identificador en todo el repositorio del
     * indexador.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO busquedaMECRepositorio(java.lang.String idMEC) throws java.rmi.RemoteException;

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
    public es.pode.indexador.negocio.servicios.busqueda.DocMECSimpleVO busquedaMECSimple(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException;

    /**
     * Metodo que ejecuta una busqueda en todo el repositorio con
     * los
     *                 parametros de busqueda que le pasan.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] busquedaRepositorio(es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Permite la bÃºsqueda de cadenas de texto en el repositorio
     * de
     *                 ODEs indexados.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 busquedaSimple(es.pode.indexador.negocio.servicios.busqueda.ParamSimpleVO paramBusq) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.indexador.negocio.servicios.busqueda.DocVO30[] busquedaVariosMEC(es.pode.indexador.negocio.servicios.busqueda.ParamBuscarMecVO[] parametros) throws java.rmi.RemoteException;

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
    public es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO[] obtenerCatalogoRepositorioParam(java.lang.Integer nivelAgregacion) throws java.rmi.RemoteException;

    /**
     * Este metodo busca un ODE al azar de dentro del repositorio.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocVO30 obtenerODERandom() throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda en el Ã­ndice para devolver
     * la nube de tags.
     *                 Retorna el listado obtenido del Ã­ndice con el nÃºmero
     * de
     *                 resultados.
     */
    public es.pode.indexador.negocio.servicios.busqueda.PrioridadPalabrasClaveVO obtenerPalabrasClave(es.pode.indexador.negocio.servicios.busqueda.ParamPalabrasClave paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve todos los ODEs indexados en el repositorio
     * para todos los idiomas, pero solo devuelve los ODEs publicos.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO[] obtenerRepositorio(es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO paramRepositorio) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.indexador.negocio.servicios.busqueda.SugerenciasVO[] obtenerSugerencias(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramSug) throws java.rmi.RemoteException;

    /**
     * Obtiene el nÃºmero total de odes que existen en todos los
     *                 indices
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO obtenerTotalesRepositorio() throws java.rmi.RemoteException;

    /**
     * Obtiene el nÃºmero total de odes que existen en todos los
     *                 indices en un periodo de tiempo entre las fechas desde
     * y hasta.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO obtenerTotalesRepositorioFechas(es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO paramPeriodoVO) throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda que devuelve el nÃºmero
     * de
     *                 documentos asociados a un Ã¡rea curricular en funciÃ³n
     * de unos
     *                 parÃ¡metros que se le pasan.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO solicitudDocsCount(es.pode.indexador.negocio.servicios.busqueda.ParamDocsCountVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda que devuelve el nÃºmero
     * de
     *                 documentos asociados a un Ã¡rea curricular en funciÃ³n
     * de unos
     *                 parÃ¡metros que se le pasan.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO solicitudDocsCountIndiceRemoto(es.pode.indexador.negocio.servicios.busqueda.ParamDocsCountVO paramBusq, java.lang.String idNodo) throws java.rmi.RemoteException;

    /**
     * Este mÃ©todo realiza una bÃºsqueda que devuelve el nÃºmero
     * de
     *                 documentos asociados a un Ã¡rea curricular en funciÃ³n
     * de unos
     *                 parÃ¡metros que se le pasan.
     */
    public es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO solicitudDocsCountNodos(es.pode.indexador.negocio.servicios.busqueda.ParamDocsCountVO paramBusq, java.lang.String[] idNodo) throws java.rmi.RemoteException;
}
