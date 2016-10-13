/**
 * SrvTaggingServerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.tagging.negocio.servicios;

public interface SrvTaggingServerService extends javax.xml.rpc.Service {

/**

 */
    public java.lang.String getSrvTaggingServerAddress();

    public es.pode.tagging.negocio.servicios.SrvTaggingServer getSrvTaggingServer() throws javax.xml.rpc.ServiceException;

    public es.pode.tagging.negocio.servicios.SrvTaggingServer getSrvTaggingServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
