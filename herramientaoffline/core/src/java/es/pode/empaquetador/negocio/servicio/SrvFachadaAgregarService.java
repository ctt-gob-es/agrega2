/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvFachadaAgregarService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvFachadaAgregarService extends java.rmi.Remote {

    /**

     */
    public void agregar(java.lang.String idOde, java.lang.String idDestino, java.lang.String tipoEmpaquetador, java.lang.String idAgregar) throws java.rmi.RemoteException;

    /**

     */
    public void agregarFederado(java.lang.String idODE, java.lang.String idDestino, java.lang.String tipoEmpaquetador, java.lang.String url) throws java.rmi.RemoteException;

    /**
     * Analiza el archivo dado y determina si es un RCP, un ODE
     *                 (devuelve "CA"), un archivo ZIP o un fichero individual.
     */
    public es.pode.empaquetador.negocio.servicio.AnalizaArchivoVO analizarArchivo(java.lang.String rutaArchivo) throws java.rmi.RemoteException;

    /**
     * Descarga el metadato visualizado de la memoria del catalogador.
     */
    public void descargarMetadato(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public void generarManifest(java.lang.String identificador, java.lang.String[] files, java.lang.String href, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public void generarManifestRCP(java.lang.String identificador, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Realiza las gestiones necesarias con el catalogador para poder
     * visualizar el metadato de un ODE publicado.
     */
    public java.lang.String prepararMetadatos(java.lang.String identificador) throws java.rmi.RemoteException;
}
