/**
 * SrvBuscadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public interface SrvBuscadorService extends java.rmi.Remote {

    /**

     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO busquedaAvanzada(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo tiene que buscar los resultados que ajusten a los
     * parametros de busqueda. Ordena los resultados y los devuelve
     *                 convertidos en un texto LOM-ES.
     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosLOM_ESVO busquedaLOM_ES(es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO paramBusq) throws java.rmi.RemoteException;

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
    public es.pode.indexador.negocio.servicios.busqueda.DocVO busquedaMEC(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO busquedaSimple(es.pode.indexador.negocio.servicios.busqueda.ParamSimpleVO paramBusq) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve una lista de todos los idiomas de
     *                 navegacion que se contemplan en la aplicacion para
     * la busqueda
     *                 (de los que existen listas de idiomas traducidas).
     */
    public es.pode.indexador.negocio.servicios.busqueda.IdiomaVO[] obtenerIdiomasBusqueda() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve para un idioma dado la lista de idiomas
     * de
     *                 busqueda relacionada a dicho idioma traducida.
     */
    public es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO[] obtenerIdiomasLocalizados(java.lang.String idIdioma) throws java.rmi.RemoteException;
}
