/**
 * SrvCatalogacionBasicaServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public interface SrvCatalogacionBasicaServiceService extends javax.xml.rpc.Service {

/**
 * Servicio encargado de transformar un metadato LOM-ES a un
 *             subconjunto de este llamado LomBasico, recogiendo unicamente
 * los
 *             campos necesarios para utilizar el catalogador basico.
 */
    public java.lang.String getSrvCatalogacionBasicaServiceAddress();

    public es.pode.catalogacion.negocio.servicios.SrvCatalogacionBasicaService getSrvCatalogacionBasicaService() throws javax.xml.rpc.ServiceException;

    public es.pode.catalogacion.negocio.servicios.SrvCatalogacionBasicaService getSrvCatalogacionBasicaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
