/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvValoracionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios;

public interface SrvValoracionService extends java.rmi.Remote {

    /**
     * Este metodo permite consultar la valoracion para un contenido
     * digital concreto del que se indica el identificador.
     */
    public java.lang.Float consultarValoracion(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo elimina los comentarios correspondientes a los
     *                 identificadores que le pasan.
     */
    public java.lang.String eliminarComentarios(java.lang.Long[] id) throws java.rmi.RemoteException;

    /**
     * Este metodo elimina todos los comentarios asociados al contenido
     * digital identificado por el identificador que se le pasa.
     *                 Devuelve un codigo con el resultado de la operacion.
     */
    public java.lang.String eliminarTodosComentarios(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo introduce un comentario asociado al contenido
     *                 digital identificado por el identificador que le pasan.
     * Devuelve un codigo con el resultado de la operacion.
     */
    public java.lang.String introducirComentario(java.lang.String idODE, java.lang.String titulo, java.lang.String comentario, java.lang.Float valoracion, java.lang.String idiomaODE) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve la lista de comentarios asociados al
     *                 contenido digital identificado por el identificador
     * que le
     *                 pasan.
     *                 Los devuelve ordenados por fecha de insercion, de
     * mas antiguo a
     *                 mas reciente.
     */
    public es.pode.valoracion.negocio.servicios.ComentarioVO[] obtenerComentarios(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo permite obtener el ultimo comentario introducido
     * (el
     *                 mas reciente por fecha) para el contenido digital
     * identificado
     *                 por el identificador que se le pasa.
     */
    public es.pode.valoracion.negocio.servicios.ComentarioVO obtenerUltimoComentario(java.lang.String idODE) throws java.rmi.RemoteException;
}
