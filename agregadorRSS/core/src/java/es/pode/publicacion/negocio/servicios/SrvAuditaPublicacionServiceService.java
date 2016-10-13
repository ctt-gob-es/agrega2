/**
 * SrvAuditaPublicacionServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvAuditaPublicacionServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa toda la funcionalidad de auditoria del
 * servico de publicacion. Es la puerta de entrada a la informacion
 *             estadistica relevante que se puede generar en el modulo
 * de
 *             publicacion.
 */
    public java.lang.String getSrvAuditaPublicacionServiceAddress();

    public es.pode.publicacion.negocio.servicios.SrvAuditaPublicacionService getSrvAuditaPublicacionService() throws javax.xml.rpc.ServiceException;

    public es.pode.publicacion.negocio.servicios.SrvAuditaPublicacionService getSrvAuditaPublicacionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
