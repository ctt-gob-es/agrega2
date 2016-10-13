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
     * Este metodo permite consultar la valoracion y el numero de
     *                 valoraciones para un contenido digital concreto del
     * que se
     *                 indica el identificador.
     */
    public es.pode.valoracion.negocio.servicios.ValVO consultarValoracionYnumValoraciones(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo elimina los comentarios correspondientes a los
     *                 identificadores que le pasan.
     */
    public java.lang.String eliminarComentarios(java.lang.Long[] id) throws java.rmi.RemoteException;

    /**
     * Método que elimina todas las valoraciones realizadas al ode
     * con
     *                 identificador idODE. Devuelve un código del resultado
     * de la
     *                 acción.
     */
    public java.lang.String eliminarTodasValoraciones(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Método que elimina todos los comentarios relacionados con el
     * ode
     *                 con identificador idODE. Devuelve un código con el
     * resultado de
     *                 la acción.
     */
    public java.lang.String eliminarTodosComentarios(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo introduce un comentario asociado al contenido
     *                 digital identificado por el identificador que le pasan.
     */
    public void introducirComentario(java.lang.String idODE, java.lang.String comentario, java.lang.String idiomaODE, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Este método introduce un valoración correspondiente al ODE.
     */
    public void introducirValoracion(java.lang.Float valoracion, java.lang.String idODE, java.lang.String autor, java.lang.String idiomaODE) throws java.rmi.RemoteException;

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

     */
    public java.lang.Boolean tieneValoracion(java.lang.String autor, java.lang.String idOde, java.lang.String idioma) throws java.rmi.RemoteException;
}
