/**
 * SrvBuscadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public interface SrvBuscadorServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa la funcionalidad de busqueda de odes
 *             sobre los odes indexados.
 */
    public java.lang.String getSrvBuscadorServiceAddress();

    public es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getSrvBuscadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getSrvBuscadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
