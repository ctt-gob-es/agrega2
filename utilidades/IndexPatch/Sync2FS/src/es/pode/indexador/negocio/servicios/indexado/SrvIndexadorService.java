/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvIndexadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;

public interface SrvIndexadorService extends java.rmi.Remote {

    /**
     * Elimina todos los odes correspondientes al idioma que estoy
     * pasando como parÃ¡metro.
     */
    public void borrarOdesPorIdioma(java.lang.String idioma, int num) throws java.rmi.RemoteException;

    /**
     * Elimina todos los odes correspondientes al idioma que estoy
     * pasando como parÃ¡metro.
     */
    public void borrarPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Eliminacion de ODEs de los indices.
     *                 Se devuelve un array con el exito o fracaso de la
     * regeneracion
     *                 para cada ode.
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] eliminarODE(java.lang.String[] idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo indexa un array de ODEs para todos los idiomas
     *                 disponibles.
     *                 Se devuelve un array con el exito o fracaso de la
     * regeneracion
     *                 para cada ode.
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] indexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException;

    /**

     */
    public void optimizarIndice(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Se regenera el indice con la lista de ODEs que se le pasa.
     * Se
     *                 borra el indice del idioma indicado y se puebla con
     * los ODEs
     *                 suministrados.
     *                 Se devuelve un array con el exito o fracaso de la
     * regeneracion
     *                 para cada ode.
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndice(java.lang.String idioma, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException;

    /**
     * Este metodo regenera todos los indices del repositorio con
     * los
     *                 ODEs que se le pasan en el momento. Toma como parametro
     * un
     *                 identificador de tarea donde escribe el resultado
     * de la
     *                 ejecucion. Cada ODE se introduce en el indice del
     * idioma al que
     *                 pertenece.
     *                 Se devuelve un array con el exito o fracaso de la
     * regeneracion
     *                 para cada ode.
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndices(java.lang.Long idTarea, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException;

    /**
     * Este metodo reindexa un array de ODEs para todos los idiomas
     * disponibles.
     *                 Se devuelve un array con el exito o fracaso de la
     * regeneracion
     *                 para cada ode.
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] reindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException;

    /**

     */
    public boolean sincronizarIndiceCompass() throws java.rmi.RemoteException;
}
