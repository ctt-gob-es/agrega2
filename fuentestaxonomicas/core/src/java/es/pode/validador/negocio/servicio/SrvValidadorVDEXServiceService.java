/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvValidadorVDEXServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public interface SrvValidadorVDEXServiceService extends javax.xml.rpc.Service {

/**
 * El servicio contiene metodos para las siguientes tareas:
 *             A) obtenerValidacionTesauro:
 *             recibe como parÃ¡metro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_thesaurus.xsd
 *             B) obtenerValidacionTaxonomia::
 *             recibe como parÃ¡metro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_hierarchical.xsd
 */
    public java.lang.String getSrvValidadorVDEXServiceAddress();

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService() throws javax.xml.rpc.ServiceException;

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
