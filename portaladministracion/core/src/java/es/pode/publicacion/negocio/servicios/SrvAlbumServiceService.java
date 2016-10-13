/**
 * SrvAlbumServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvAlbumServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que alberga todos los métodos de gestión de los
 *             álbumes.
 */
    public java.lang.String getSrvAlbumServiceAddress();

    public es.pode.publicacion.negocio.servicios.SrvAlbumService getSrvAlbumService() throws javax.xml.rpc.ServiceException;

    public es.pode.publicacion.negocio.servicios.SrvAlbumService getSrvAlbumService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
