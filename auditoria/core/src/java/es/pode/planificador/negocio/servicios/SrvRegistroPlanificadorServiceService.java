/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvRegistroPlanificadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvRegistroPlanificadorServiceService extends javax.xml.rpc.Service {

/**
 * Servicio de auditorÃ­a. Nos permite registrar el resultado de las
 * ejecuciones de los trabajos y subtrabajos.
 */
    public java.lang.String getSrvRegistroPlanificadorServiceAddress();

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
