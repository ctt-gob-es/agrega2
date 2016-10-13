/**
 * SrvAgregadorRssService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.agregador.negocio.agregador.servicio;

public interface SrvAgregadorRssService extends java.rmi.Remote {

    /**

     */
    public void crearXML(java.lang.String idTarea, long feedNum, java.lang.String idiomaNavegacion) throws java.rmi.RemoteException;

    /**

     */
    public void crearXMLPublico(java.lang.String idTarea, long feedNum, java.lang.String idiomaNavegacion) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.agregador.negocio.agregador.servicio.FeedVO[] devuelveFeeds() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.agregador.negocio.agregador.servicio.FeedVO[] devuelveFeedsGaleriaPortada(java.lang.String idiomaNavegacion) throws java.rmi.RemoteException;

    /**
     * Método que recoge un parámetro con todos los datos necesarios
     * para realizar una búsqueda. Tras la búsqueda genera un fichero
     *                 xml con los resultados obtenidos y lo devuelve en
     * un DataHandler
     *                 para poder visualizarlo.
     */
    public java.lang.String devuelveRssBusqueda(es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO parametrosBusqueda, java.lang.String tipoRSS, long feedNum) throws java.rmi.RemoteException;

    /**
     * Método que recoge un parámetro con todos los datos necesarios
     * para realizar una búsqueda. Tras la búsqueda genera un fichero
     *                 xml con los resultados obtenidos y lo devuelve en
     * un DataHandler
     *                 para poder visualizarlo.
     */
    public java.lang.String devuelveRssBusquedaPorTipoBuscador(es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO parametrosBusqueda, java.lang.String tipoRSS, long feedNum, java.lang.String tipoBuscador) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarFichero(es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO param) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerFeedWidgets(es.pode.agregador.negocio.agregador.servicio.ParamWidgetVO parametros) throws java.rmi.RemoteException;

    /**
     * Método utilizado para la compatibilidad de versión 1 a versión
     * 3. Pasamos el nombre que dabamos al xml en la versión 1, (por
     *                 ejemplo para notcias_rss.xml pasariamos noticias,
     * para
     *                 masDescargados_semana_rss.xml pasariamos masDescargados).
     */
    public java.lang.String obtenerIdCompatibilidad(java.lang.String name) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerXMLFeed(java.lang.String id, long feedNum, java.lang.String idiomaNavegacion) throws java.rmi.RemoteException;

    /**

     */
    public byte[] obtenerXMLFeedArrayByte() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerXMLFeedPublico(java.lang.String id, long feedNum, java.lang.String idiomaNavegacion) throws java.rmi.RemoteException;
}
