/**
 * SrvOaiPmhServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public interface SrvOaiPmhServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que implementa el servicio OAI. Implementará todos los
 * métodos que ofrece el protocolo oai-pmh
 */
    public java.lang.String getSrvOaiPmhServiceAddress();

    public es.pode.oaipmh.negocio.servicios.SrvOaiPmhService getSrvOaiPmhService() throws javax.xml.rpc.ServiceException;

    public es.pode.oaipmh.negocio.servicios.SrvOaiPmhService getSrvOaiPmhService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
