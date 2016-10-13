/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvEntregarService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public interface SrvEntregarService extends java.rmi.Remote {

    /**
     * Metodo obtenerObjManifest
     *                 El Empaquetador, llamara a este metodo del servicio
     * para guardar
     *                 el manifest en memoria;
     *                 El método cogerá el objeto serializado lo deserializará
     * obtendrá
     *                 un manifest y lo metará en memoria (HashMap)
     *                 Devolvera un string que sera el identificador
     *                 este identificador estara formado con el id del manifest
     * (manifest.getIdentifier()) y el prefijo EMP
     */
    public java.lang.String cargarObjManifest(java.lang.String usuario, javax.activation.DataHandler fichManifest) throws java.rmi.RemoteException;

    /**
     * este método se usa para saber si un servicio está levantado
     * o
     *                 no.
     */
    public boolean estoyActivo() throws java.rmi.RemoteException;

    /**
     * Este metodo recibe un identificador de un ODE.
     *                 Devuelve el paquete PIF con todos los elementos que
     * contiene el
     *                 ODE y el resultado de la validación todo ello en un
     * PaquetePifVO.
     *                 Para entregar el paquete, el metodo tiene que localizar
     * la
     *                 informacion referente al ODE en disco (invocar al
     * localizador),
     *                 despues tiene que validar la informacion (invocar
     * al validador)
     *                 para despues generar el paquete.
     */
    public es.pode.entregar.negocio.servicios.PaquetePifDriVO generarPaquetePIF(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Este metodo se comporta como el homonimo de la clase pero
     *                 permite configurar el formato del fichero PIF que
     * se devuelve
     *                 entre la coleccion de formatos que se soportan por
     * la clase
     *                 TiposPIF.
     *                 Toma como parametro de entrada el identificador del
     * ODE y el
     *                 tipo de fichero PIF que desea a la salida de entre
     * los posibles
     *                 por la clase TipoPIF.
     *                 Devuelve el ODE el formato PIF con el tipo escogido
     * y el
     *                 resultado de la validación todo ello en un PaquetePifVO.
     */
    public es.pode.entregar.negocio.servicios.PaquetePifVO generarPaquetePIFTipoPIF(es.pode.entregar.negocio.servicios.TipoPifVO tipoPifVO) throws java.rmi.RemoteException;

    /**
     * metodo obtenerOrganizaciones
     *                 dependiendo del valor del prefijo del identificador,
     * tendremos
     *                 que hacer:
     *                 1.- si el valor es EMPAQUETADOR, tendremos que buscar
     * el
     *                 manifest en la HashMap que tendremos cargada en memoria,
     * la
     *                 trataremos y devolveremos el array de tipo OrganizacionVO[]
     * 2.- si el valor es BUSCADOR, tendremos que recuperar el
     *                 manifest. Para ello, llamaremos al metodo consultaLocalizador
     * con el id y en uno de los atributos del LocalizadorVO (ruta)
     *                 obtendremos la ruta donde estara nuestro fichero manifest.xml;
     * una vez tengamos el fichero, se parseara con la libreria
     *                 (generada por Castor) y trataremos los datos devolviendo
     * un
     *                 array OrganizacionVO[]
     */
    public es.pode.entregar.negocio.servicios.ManifestVO obtenerOrganizaciones(es.pode.entregar.negocio.servicios.ArgObtOrganizacionesVO argObtenerOrganizaciones) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve un array de Strings con todos los tipos
     * de
     *                 formatos PIF que maneja el modulo de Entregar. Los
     * propios
     *                 Strings son identificadores para ulteriores llamadas
     * de peticion
     *                 de conversion de formato de ODEs.
     */
    public java.lang.String[] obtenerTiposPIF() throws java.rmi.RemoteException;
}
