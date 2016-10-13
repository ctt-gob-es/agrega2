/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvTaggingServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.tagging.negocio.servicios;

public interface SrvTaggingServer extends java.rmi.Remote {

    /**

     */
    public boolean eliminarTags(java.lang.String[] tagsABorrar) throws java.rmi.RemoteException;

    /**

     */
    public boolean eliminarTagsDeOdes(java.lang.String[] idsOde) throws java.rmi.RemoteException;

    /**

     */
    public boolean eliminarTagsDeUsuario(java.lang.String[] tagsABorrar, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**

     */
    public void introducirTags(java.lang.String idOde, java.lang.String titulo, java.lang.String idiomaCat, java.lang.String[] tags, java.lang.String idUsuario, java.lang.String nodo) throws java.rmi.RemoteException;

    /**

     */
    public boolean modificarTag(java.lang.String oldTag, java.lang.String newTag) throws java.rmi.RemoteException;

    /**

     */
    public boolean modificarTagDeUsuario(java.lang.String oldTag, java.lang.String newTag, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.tagging.negocio.servicios.TaggingVO[] obtenerOdesDeTag(java.lang.String tag) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.tagging.negocio.servicios.TaggingVO[] obtenerOdesDeTagYUsuario(java.lang.String tag, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerTagsDeOde(java.lang.String idOde) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.tagging.negocio.servicios.TagVO[] obtenerTagsDeUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.tagging.negocio.servicios.TaggingVO[] obtenerTagsLikeLetra(java.lang.String letra) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.tagging.negocio.servicios.TagVO[] obtenerTodosTags() throws java.rmi.RemoteException;
}
