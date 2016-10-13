/**
 * SrvEntregarServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public interface SrvEntregarServiceService extends javax.xml.rpc.Service {

/**

 */
    public java.lang.String getSrvEntregarServiceAddress();

    public es.pode.entregar.negocio.servicios.SrvEntregarService getSrvEntregarService() throws javax.xml.rpc.ServiceException;

    public es.pode.entregar.negocio.servicios.SrvEntregarService getSrvEntregarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
