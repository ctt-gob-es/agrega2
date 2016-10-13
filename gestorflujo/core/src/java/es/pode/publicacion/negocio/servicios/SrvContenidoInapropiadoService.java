/**
 * SrvContenidoInapropiadoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvContenidoInapropiadoService extends java.rmi.Remote {

    /**

     */
    public boolean crearContenidoInapropiado(java.lang.String idOde, java.lang.String titulo, java.util.Calendar fecha, java.lang.String usuario, java.lang.String comentario, java.lang.String estado, java.lang.String idioma_cat) throws java.rmi.RemoteException;

    /**
     * Cuidado con este metodo provisional, posiblemente deba estar
     * en
     *                 srvpublicacion
     */
    public void despublicarContenidoInapropiado(java.lang.String idOde, java.lang.String idioma_cat) throws java.rmi.RemoteException;

    /**

     */
    public void despublicarContenidosInapropiados(java.lang.String[] ids, java.lang.String idoma_cat) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarContenidoInapropiado(java.lang.String idOde, java.lang.String fecha_inactividad, boolean estado_ci, java.lang.String estado, java.lang.String idioma_cat) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarContenidosInapropiados(es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO[] contenidos) throws java.rmi.RemoteException;

    /**

     */
    public boolean modificarEstadoContenidoInapropiado(java.lang.String idioma_cat, java.lang.String fecha_inactividad, boolean estado_ci, java.lang.String estado, java.lang.String idOde, java.lang.String estadoNuevo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO[] obtenerContenidoInapropiadodeOde(java.lang.String idOde, java.lang.String estado, boolean estado_ci, java.lang.String fecha_inactividad, java.lang.String idioma_cat) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO[] obtenerContenidosInapropiados() throws java.rmi.RemoteException;

    /**

     */
    public void rechazarContenidoInapropiado(java.lang.String idOde, java.lang.String idioma_cat, java.lang.String estado) throws java.rmi.RemoteException;
}
