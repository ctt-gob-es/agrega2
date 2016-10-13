/**
 * SrvSesionesServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.sqi.sesion;

public interface SrvSesionesServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa la administracion de las sesiones que
 *             hay que mantener en el DRI para dar soporte a la funcionalidad
 * de SQI.
 *             Se trata de un servicio local.
 */
    public java.lang.String getSrvSesionesServiceAddress();

    public es.pode.buscar.negocio.sqi.sesion.SrvSesionesService getSrvSesionesService() throws javax.xml.rpc.ServiceException;

    public es.pode.buscar.negocio.sqi.sesion.SrvSesionesService getSrvSesionesService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
