/**
 * SrvOaiOreService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaiore.negocio.servicio;

public interface SrvOaiOreService extends javax.xml.rpc.Service {

/**

 */
    public java.lang.String getSrvOaiOreAddress();

    public es.pode.oaiore.negocio.servicio.SrvOaiOre getSrvOaiOre() throws javax.xml.rpc.ServiceException;

    public es.pode.oaiore.negocio.servicio.SrvOaiOre getSrvOaiOre(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
