/**
 * SrvGestorArchivosServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvGestorArchivosServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio web exporta la funcionalidad necesaria para que el
 * Gestor Avanzado puede crear, eliminar, copiar, cortar, pegar y
 *             modificar ficheros y carpetas y descomprimir archivos
 *             comprimidos.
 */
    public java.lang.String getSrvGestorArchivosServiceAddress();

    public es.pode.empaquetador.negocio.servicio.SrvGestorArchivosService getSrvGestorArchivosService() throws javax.xml.rpc.ServiceException;

    public es.pode.empaquetador.negocio.servicio.SrvGestorArchivosService getSrvGestorArchivosService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
