/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvCorreoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;

public interface SrvCorreoService extends javax.xml.rpc.Service {

/**
 * Servicio encargado del env√≠o de correos en la plataforma Agrega.
 * Tendr√° tantas operaciones como tipos de correo se env√≠en.
 */
    public java.lang.String getSrvCorreoAddress();

    public es.pode.gestorCorreo.negocio.servicios.SrvCorreo getSrvCorreo() throws javax.xml.rpc.ServiceException;

    public es.pode.gestorCorreo.negocio.servicios.SrvCorreo getSrvCorreo(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
