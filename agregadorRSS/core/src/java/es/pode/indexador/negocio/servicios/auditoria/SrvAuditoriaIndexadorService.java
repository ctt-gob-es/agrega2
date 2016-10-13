/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvAuditoriaIndexadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.auditoria;

public interface SrvAuditoriaIndexadorService extends java.rmi.Remote {

    /**
     * Este metodo recibe un recorrido de arbol curricular y devuelve
     * el recubrimiento del mismo que hacen los elementos que estan
     *                 indexados en el repositorio.
     */
    public es.pode.indexador.negocio.servicios.auditoria.CoberturaVO[] coberturaArbolCurricular(es.pode.indexador.negocio.servicios.auditoria.ParametroAuditIndexadorVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el nivel de cobertura de los elementos
     * indexados sobre todas las licencias.
     */
    public es.pode.indexador.negocio.servicios.auditoria.CoberturaVO[] coberturaLicencias(es.pode.indexador.negocio.servicios.auditoria.ParametroAuditIndexadorVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el nivel de cobertura que tienen los
     *                 elementos del indice sobre el nivel de agregacion.
     */
    public es.pode.indexador.negocio.servicios.auditoria.CoberturaVO[] coberturaNivelAgregacion(es.pode.indexador.negocio.servicios.auditoria.ParametroAuditIndexadorVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Metodo que devuelve las URLs de las imagenes pequenas de los
     * identificadores que le pasamos
     */
    public java.lang.String[] obtenerURLImagenPequena(java.lang.String idioma, java.lang.String[] identificadores) throws java.rmi.RemoteException;
}
