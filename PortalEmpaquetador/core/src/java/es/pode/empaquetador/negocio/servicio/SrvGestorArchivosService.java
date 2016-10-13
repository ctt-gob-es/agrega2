/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvGestorArchivosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvGestorArchivosService extends java.rmi.Remote {

    /**

     */
    public java.lang.Boolean consultaCuota(java.lang.String identificador, java.lang.Long espacioLibre, java.lang.Long espacioOdeInicio) throws java.rmi.RemoteException;

    /**
     * Copia los ficheros / carpetas a la localizaci贸n especificada.
     */
    public void copiar(java.lang.String identificador, java.lang.String carpetaDestino, es.pode.empaquetador.negocio.servicio.ArchivoVO[] ficheros) throws java.rmi.RemoteException;

    /**
     * Copiar los ficheros / carpetas especificados a la carpeta de
     * destino deseada. A continuaci贸n elimina los ficheros / carpetas
     *                 de origen con una llamada a eliminar.
     */
    public void cortar(java.lang.String identificador, java.lang.String carpetaDestino, es.pode.empaquetador.negocio.servicio.ArchivoVO[] ficheros) throws java.rmi.RemoteException;

    /**
     * Almacena un fichero recibido como argumento (DataHandler) en
     * la
     *                 carpeta especificada dentro de la localizaci贸n del
     * ODE
     *                 identificado por identificador.
     */
    public void crearArchivo(java.lang.String identificador, java.lang.String carpetaPadre, es.pode.empaquetador.negocio.servicio.FicheroVO fichero) throws java.rmi.RemoteException;

    /**
     * Crea una carpeta en la localizaci贸n del ODE en que se est谩
     *                 trabajando.
     */
    public void crearCarpeta(java.lang.String identificador, java.lang.String carpetaPadre, java.lang.String nombreCarpeta) throws java.rmi.RemoteException;

    /**

     */
    public void crearVistaPrevia(es.pode.empaquetador.negocio.servicio.FicheroVO fichero, java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public void descomprimirArchivo(java.lang.String identificador, java.lang.String carpetaPadre, es.pode.empaquetador.negocio.servicio.FicheroVO archivo) throws java.rmi.RemoteException;

    /**
     * Elimina los ficheros / carpetas deseados.
     */
    public void eliminar(java.lang.String identificador, es.pode.empaquetador.negocio.servicio.ArchivoVO[] ficheros) throws java.rmi.RemoteException;

    /**
     * Elimina el fichero de backup del ODE en edici贸n.
     */
    public void eliminarBackup(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarVistaPrevia(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Genera un backup en el directorio temporal del localizador.
     * El
     *                 backup es un archivo comprimido (ZIP) que contiene
     * todos los
     *                 archivos del ODE en el momento en que se ha generado
     * el backup
     *                 (imsmanifest.xml, esquemas, submanifiestos y recursos).
     * Si
     *                 previamente existe un backup, se llama a eliminarBackup
     * para
     *                 reemplazarlo.
     */
    public void generarBackup(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Devuelve un listado de los contenidos de una carpeta.
     */
    public es.pode.empaquetador.negocio.servicio.ArchivoVO[] listar(java.lang.String identificador, java.lang.String carpetaPadre) throws java.rmi.RemoteException;

    /**
     * Devuelve un ArchivoVO representando la raiz de la localizaci贸n
     * del ODE. El objeto devuelto contiene la estructura en 谩rbol de
     *                 todos los archivos pertenecientes al ODE. Para ello,
     * examina la
     *                 raiz del ODE listando de forma recursiva cada carpeta
     * y
     *                 generando los ArchivoVO correspondientes.
     */
    public es.pode.empaquetador.negocio.servicio.ArchivoVO recuperarArbol(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String recuperarVistaPrevia(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Modifica el nombre del fichero o carpeta deseado.
     */
    public void renombrar(java.lang.String identificador, java.lang.String carpetaPadre, java.lang.String nombre, java.lang.String nuevoNombre) throws java.rmi.RemoteException;

    /**
     * Descomprime el contenido del fichero de backup a la localizaci贸n
     * del ODE. Previamente borra cualquier fichero / carpeta que est茅
     *                 en dicha localizaci贸n.
     */
    public void restaurarBackup(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean verficarExisteBackup(java.lang.String idOde) throws java.rmi.RemoteException;
}
