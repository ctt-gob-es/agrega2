/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.SQI;


/**
 * @see es.pode.dri.negocio.servicios.SQI.SrvSQIAsincronoService
 */

public class SrvSQIAsincronoServiceImpl
    extends es.pode.dri.negocio.servicios.SQI.SrvSQIAsincronoServiceBase
{
	
	
	/**
	 * Permite realizar ejecuciones de queries en modo asincrono, de manera que el API devuelve 
	 * los resultados de la query cuando termine de ejecutar la busqueda.
	 * Est� en sintonia con setSourceLocation, el cual se tiene que ejecutar antes para suministar 
	 * la localizacion donde se van a devolver los resutlados de la query.
     * @param targetSessionID Identificador de la sesion sobre la que se realiza la operacion.   
     * @param queryStatement Sentencia a ejecutar en modo asincrono.
     * @param queryID Identificador de la query que se va a ejecutar. 
     * Este identifiacador es un valor suministrado por el cliente del API que lo utiliza para identificar su consulta.
     * @throws Exception
     *      
     */
    protected void handleAsynchronousQuery(java.lang.String targetSessionID, java.lang.String queryStatement, java.lang.String queryID)
        throws java.lang.Exception
    {
        //@todo implement protected void handleAsynchronousQuery(java.lang.String targetSessionID, java.lang.String queryStatement, java.lang.String queryID)
        throw new java.lang.UnsupportedOperationException("es.pode.dri.negocio.servicios.SQI.SrvSQIAsincronoService.handleAsynchronousQuery(java.lang.String targetSessionID, java.lang.String queryStatement, java.lang.String queryID) Not implemented!");
    }

}