/**
 * SrvBuscarFederadaServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.federada.servicios;

public interface SrvBuscarFederadaServiceService extends javax.xml.rpc.Service {

/**
 * @return String

 */
    public java.lang.String getSrvBuscarFederadaServiceAddress();

    public es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaImportService getSrvBuscarFederadaService() throws javax.xml.rpc.ServiceException;

    public SrvBuscarFederadaImportService getSrvBuscarFederadaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
