/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvGeneracionDinamicaServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.generacionDinamica.servicio;

public interface SrvGeneracionDinamicaServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa la gestion de los contenidos dinamicos
 * que se generan en la plataforma.
 */
    public java.lang.String getSrvGeneracionDinamicaServiceAddress();

    public es.pode.contenidos.negocio.generacionDinamica.servicio.SrvGeneracionDinamicaService getSrvGeneracionDinamicaService() throws javax.xml.rpc.ServiceException;

    public es.pode.contenidos.negocio.generacionDinamica.servicio.SrvGeneracionDinamicaService getSrvGeneracionDinamicaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
