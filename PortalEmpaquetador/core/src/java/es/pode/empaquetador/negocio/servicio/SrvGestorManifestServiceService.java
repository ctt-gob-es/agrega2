/**
 * SrvGestorManifestServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvGestorManifestServiceService extends javax.xml.rpc.Service {

/**
 * Servicio web que gestiona las operaciones sobre Manifiestos
 *             SCORM2004 almacenando los cambios en una cache estatica
 * (HashMap).
 */
    public java.lang.String getSrvGestorManifestServiceAddress();

    public es.pode.empaquetador.negocio.servicio.SrvGestorManifestService getSrvGestorManifestService() throws javax.xml.rpc.ServiceException;

    public es.pode.empaquetador.negocio.servicio.SrvGestorManifestService getSrvGestorManifestService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
