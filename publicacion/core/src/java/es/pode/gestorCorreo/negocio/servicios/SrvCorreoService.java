/**
 * SrvCorreoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;

public interface SrvCorreoService extends javax.xml.rpc.Service {

/**
 * Servicio encargado del envío de correos en la plataforma Agrega.
 * Tendrá tantas operaciones como tipos de correo se envíen.
 */
    public java.lang.String getSrvCorreoAddress();

    public es.pode.gestorCorreo.negocio.servicios.SrvCorreo getSrvCorreo() throws javax.xml.rpc.ServiceException;

    public es.pode.gestorCorreo.negocio.servicios.SrvCorreo getSrvCorreo(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
