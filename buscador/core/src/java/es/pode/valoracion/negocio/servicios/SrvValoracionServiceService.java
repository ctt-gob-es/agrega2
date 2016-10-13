/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvValoracionServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios;

public interface SrvValoracionServiceService extends javax.xml.rpc.Service {

/**
 * Clase que implementa el servicio de valoracion. Esta clase
 *             permite gestionar la valoracion de los contenidos digitales
 * de
 *             la plataforma, asi como los comentarios introducidos por
 * los
 *             usuarios de los ODEs del repositorio.
 */
    public java.lang.String getSrvValoracionServiceAddress();

    public es.pode.valoracion.negocio.servicios.SrvValoracionService getSrvValoracionService() throws javax.xml.rpc.ServiceException;

    public es.pode.valoracion.negocio.servicios.SrvValoracionService getSrvValoracionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
