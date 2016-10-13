/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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