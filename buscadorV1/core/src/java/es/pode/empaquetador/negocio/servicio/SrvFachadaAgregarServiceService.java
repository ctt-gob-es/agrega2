/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvFachadaAgregarServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvFachadaAgregarServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que ofrece un único metodo para todos los posibles
 *             agregar que se ofrecen en el modulo de empaquetación.
 */
    public java.lang.String getSrvFachadaAgregarServiceAddress();

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService() throws javax.xml.rpc.ServiceException;

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
