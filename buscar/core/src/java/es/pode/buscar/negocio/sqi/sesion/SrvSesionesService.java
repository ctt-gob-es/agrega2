/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvSesionesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.sqi.sesion;

public interface SrvSesionesService extends java.rmi.Remote {

    /**
     * Este metodo crea una sesion sin usuario definido o con usuario
     * anonimo.
     *                 Devuelve el identificador de lla sesion creada.
     */
    public java.lang.String createAnonymousSession() throws java.rmi.RemoteException;

    /**
     * Este metodo crea una nueva sesion asociada al usuario.
     *                 Devuelve el identificador de la sesion creada.
     *                 Chequea contra el autenticador la identidad del usuario
     * que
     *                 intenta crear una conexion.
     */
    public java.lang.String createSession(java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Este metodo borra una sesion del sistema.
     *                 Toma como parametro el identificador de la sesion
     * que se quiere
     *                 eliminar.
     */
    public void destroySession(java.lang.String sessionID) throws java.rmi.RemoteException;
}
