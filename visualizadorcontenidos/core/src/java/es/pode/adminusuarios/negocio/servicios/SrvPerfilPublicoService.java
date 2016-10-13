/**
 * SrvPerfilPublicoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public interface SrvPerfilPublicoService extends javax.xml.rpc.Service {

/**
 * Servicio para la gestion del perfil publico de un usuario de
 *             Agrega
 */
    public java.lang.String getSrvPerfilPublicoAddress();

    public es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico getSrvPerfilPublico() throws javax.xml.rpc.ServiceException;

    public es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico getSrvPerfilPublico(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
