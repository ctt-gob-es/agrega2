/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvEstructurasEducativasService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvEstructurasEducativasService extends java.rmi.Remote {

    /**

     */
    public java.lang.Boolean chequearIdiomaArbolCurricular(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean chequearIdiomaTesauro(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public int[] eliminarArbolesCurriculares(java.lang.String[] nombres) throws java.rmi.RemoteException;

    /**

     */
    public int[] eliminarBackups(java.lang.String[] nombres) throws java.rmi.RemoteException;

    /**

     */
    public int[] eliminarTesauros(java.lang.String[] nombres) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler exportarVdex(java.lang.String nombre, es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex tipoVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarArbolCurricular() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarBackups() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarTesauros() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerIdsConVocabIdentifier(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] subirArbolesCurriculares(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] arboles) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] subirBackups(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] listaVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] subirTesauros(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] tesauros) throws java.rmi.RemoteException;
}