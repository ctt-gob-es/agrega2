/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvCatalogacionAvanzadaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public interface SrvCatalogacionAvanzadaService extends java.rmi.Remote {

    /**

     */
    public java.lang.String cargarObjLom(java.lang.String identificador, java.lang.String usuario, javax.activation.DataHandler lom) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler exportarLomes(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAvanzado, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler exportarLomesDesdeLom(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma, javax.activation.DataHandler lom) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO generarMetadatos(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAvanzado, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO importarLomes(javax.activation.DataHandler ficheroLomes) throws java.rmi.RemoteException;

    /**
     * Realmente haria falta este metodo? no vamos a ver todo el Lom
     * a
     *                 la vez; lo veremos, modificaremos y/o aÃ±adiremos de
     * categoria en
     *                 categoria, pero podria interesarnos
     */
    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO obtenerLomAvanzado(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.negocio.servicios.IdLomVO[] obtenerObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException;
}
