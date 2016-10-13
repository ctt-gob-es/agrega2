/**
 * SrvValoracionServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios;

public interface SrvValoracionServiceService extends javax.xml.rpc.Service {

/**
 * Clase que implementa el servicio de valoracion. Esta clase
 *             permite gestionar la valoracion de los contenidos digitales
 * de
 *             la plataforma, asi como los comentarios introducidos por
 * los
 *             usuarios de los ODEs del repositorio.
 */
    public java.lang.String getSrvValoracionServiceAddress();

    public es.pode.valoracion.negocio.servicios.SrvValoracionService getSrvValoracionService() throws javax.xml.rpc.ServiceException;

    public es.pode.valoracion.negocio.servicios.SrvValoracionService getSrvValoracionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
