/**
 * SrvAlbumService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvAlbumService extends java.rmi.Remote {

    /**
     * Este método da de alta un album a un usuario.
     */
    public es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO altaAlbum(java.lang.String titulo, java.lang.String descripcion, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Este método asocia un ODE en estado de creación o rechazado
     * a un
     *                 album. El usuario que lo crea y el creador del album
     * tienen que
     *                 ser el mismo.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO asociarODEAlbum(java.lang.String idODE, java.lang.String usuario, java.lang.Long idAlbum) throws java.rmi.RemoteException;

    /**
     * Este método da de baja un album de un usuario.
     */
    public es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO bajaAlbum(java.lang.Long idAlbum) throws java.rmi.RemoteException;

    /**
     * Este método desasocia un ODE de la asociación con su album.
     * Devolvemos el ODE sin el album.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO desasociarODEAlbum(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Elimina los Albumes de los usuarios pasados como parámetro
     */
    public java.lang.Boolean eliminarAlbumesUsuarios(java.lang.String[] usuarios) throws java.rmi.RemoteException;

    /**
     * Este método modifica un album de un usuario.
     */
    public es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO modificaAlbum(java.lang.Long idAlbum, java.lang.String titulo, java.lang.String descripcion) throws java.rmi.RemoteException;

    /**
     * Este método devuelve los álbumes creados por el usuairo dado.
     */
    public es.pode.publicacion.negocio.servicios.AlbumVO[] obtenAlbumesPorUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el album que esta asociado al identificador
     * que le pasan. Si el album no existe, se devuelve null.
     */
    public es.pode.publicacion.negocio.servicios.AlbumVO obtenerAlbumPorId(java.lang.Long idAlbum) throws java.rmi.RemoteException;

    /**
     * Este método devuelve los ODEs que creados o rechazados que
     * están
     *                 en el album que le pasan.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenOdesPorAlbum(java.lang.Long idAlbum) throws java.rmi.RemoteException;

    /**
     * Este metodo recupera los ODEs que estando en estado CREADO
     * o
     *                 RECHAZADO están en la carpeta de objetos personales
     * del usuairo
     *                 que le pasan y no están clasificados dentro de ningún
     * album.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenOdesSinAlbum(java.lang.String usuario) throws java.rmi.RemoteException;
}
