/**
 * SrvAuditoriaValoracionServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios.auditoria;

public interface SrvAuditoriaValoracionServiceService extends javax.xml.rpc.Service {

/**
 * Esta clase implementa la parte de auditoria del servicio de
 *             valoracion. Alberga los metodos publicos que permiten
 * extraer
 *             informacion estadistica de la valoracion de los elementos
 * de la
 *             plataforma.
 */
    public java.lang.String getSrvAuditoriaValoracionServiceAddress();

    public es.pode.valoracion.negocio.servicios.auditoria.SrvAuditoriaValoracionService getSrvAuditoriaValoracionService() throws javax.xml.rpc.ServiceException;

    public es.pode.valoracion.negocio.servicios.auditoria.SrvAuditoriaValoracionService getSrvAuditoriaValoracionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
