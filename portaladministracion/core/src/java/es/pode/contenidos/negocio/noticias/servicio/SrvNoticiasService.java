/**
 * SrvNoticiasService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;

public interface SrvNoticiasService extends java.rmi.Remote {

    /**
     * Almacena en el servidor la imagen suministrada y devuelve la
     * URL
     *                 que permite acceder a dicha imagen.
     */
    public java.lang.String almacenarImagenNoticia(es.pode.contenidos.negocio.noticias.servicio.ImagenVO imagen) throws java.rmi.RemoteException;

    /**
     * Crea una nueva categoría en la BD.
     */
    public java.lang.Long crearCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria) throws java.rmi.RemoteException;

    /**
     * Crea una nueva noticia en base de datos. El metodo devuelve
     * la
     *                 ID asignada.
     */
    public java.lang.Long crearNoticia(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO noticia) throws java.rmi.RemoteException;

    /**
     * Elimina la categoria con identificador id de la base de datos.
     */
    public void eliminarCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Elimina las categorías de la base de datos.
     */
    public void eliminarCategorias(java.lang.Long[] idList) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean eliminarImagenNoticia(java.lang.String imagenEliminarSt) throws java.rmi.RemoteException;

    /**
     * Elimina una noticia de la base de datos.
     */
    public void eliminarNoticia(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Elimina la coleccion de noticias especificadas en el array
     * de
     *                 IDs.
     */
    public void eliminarNoticias(java.lang.Long[] idList) throws java.rmi.RemoteException;

    /**

     */
    public void limpiarCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public void limpiarNoticia(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de noticias activas redactadas en el idioma
     * deseado y ordenadas por fecha de publicacion.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] listarNoticiasActivasPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Método que modifica una categoría a partir del vo.
     */
    public void modificarCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria) throws java.rmi.RemoteException;

    /**
     * Modifica una noticia existente.
     */
    public void modificarNoticia(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO noticia) throws java.rmi.RemoteException;

    /**
     * Obtiene una categoria identificada por si ID.
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO obtenerCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de las categorias existentes.
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[] obtenerCategorias() throws java.rmi.RemoteException;

    /**
     * Obtiene todas las categorías de las noticias que haya en la
     * base
     *                 de datos.
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[] obtenerCategoriasTraducidas(java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException;

    /**
     * Otiene la categoria traducida.
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO obtenerCategoriaTraducida(java.lang.Long id, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene una noticia (en formato VO modifable) identificada
     * por
     *                 su ID.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO obtenerNoticia(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de noticias ordenadas por fecha de
     *                 publicación.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[] obtenerNoticias() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[] obtenerNoticiasActivas(java.lang.Integer numResultados) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de las noticias activas redactadas en el
     * idioma deseado y ordenadas por fecha de publicacion.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasActivasPorIdiomayCategoria(java.lang.String idioma, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Este método obtiene todas las noticias asociadas a la categoría
     * con id.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasFromIdCategoria(java.lang.Long idCategoria, java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException;

    /**
     * Obtiene todas las noticias traducidas. Hay una prioridad de
     * traducción del idioma que lo marca el array de idiomas.
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasTraducidas(java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO obtenerNoticiaTraducida(java.lang.Long id, java.lang.String idioma) throws java.rmi.RemoteException;
}
