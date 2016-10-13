/**
 * SrvIdentidadFederadaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.seguridad.servicios;

public interface SrvIdentidadFederadaService extends java.rmi.Remote {

    /**
     * @param usuarioLogin 
     * @return Boolean
     * @throws java.rmi.RemoteException 

     */
    public java.lang.Boolean addUserSession(java.lang.String usuarioLogin) throws java.rmi.RemoteException;

    /**
     * @param loginUsuario 
     * @return Boolean
     * @throws java.rmi.RemoteException 

     */
    public java.lang.Boolean deleteUserSession(java.lang.String loginUsuario) throws java.rmi.RemoteException;

    /**
     * @param usuario 
     * @return Boolean
     * @throws java.rmi.RemoteException 

     */
    public java.lang.Boolean isAutenticated(java.lang.String usuario) throws java.rmi.RemoteException;
}
