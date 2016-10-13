/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvSyncService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvSyncService extends java.rmi.Remote {

    /**

     */
    public java.lang.String[] descargarODEs(es.pode.publicacion.negocio.servicios.ODESyncVO[] odes, java.lang.String clave, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ODESyncVO[] obtenerODEs(java.lang.String usuario, java.lang.String clave) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ResultadoOperacionVO subirODE(java.lang.String usuario, java.lang.String clave, javax.activation.DataHandler pif, java.lang.String titulo, java.lang.String idioma, java.lang.String identificador) throws java.rmi.RemoteException;
}
