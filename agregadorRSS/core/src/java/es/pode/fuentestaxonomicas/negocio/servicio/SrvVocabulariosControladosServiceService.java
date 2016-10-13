/**
 * SrvVocabulariosControladosServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvVocabulariosControladosServiceService extends javax.xml.rpc.Service {

/**
 * El servicio de vocabularios controlados es el encargado de
 *             gestionar los terminos restringidos por LOM-ES, realizando
 * las
 *             traducciones oportunas para conseguir la validez de los
 * esquemas, asi como para mostrar los vocabularios en el idioma
 *             indicado por el usuario.
 */
    public java.lang.String getSrvVocabulariosControladosServiceAddress();

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService getSrvVocabulariosControladosService() throws javax.xml.rpc.ServiceException;

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService getSrvVocabulariosControladosService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
