/**
 * SrvConfigPortalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.configuracionPortal.servicio;

public interface SrvConfigPortalService extends javax.xml.rpc.Service {

/**

 */
    public java.lang.String getSrvConfigPortalAddress();

    public es.pode.contenidos.negocio.configuracionPortal.servicio.SrvConfigPortal getSrvConfigPortal() throws javax.xml.rpc.ServiceException;

    public es.pode.contenidos.negocio.configuracionPortal.servicio.SrvConfigPortal getSrvConfigPortal(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
