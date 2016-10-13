/**
 * SrvInformeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.informes.servicio;

public interface SrvInformeService extends java.rmi.Remote {

    /**

     */
    public es.pode.contenidos.negocio.informes.servicio.ValidaBajaInformeVO eliminarInforme(java.lang.String[] ficheros) throws java.rmi.RemoteException;

    /**
     * Metodo para eliminar informes federados
     */
    public es.pode.contenidos.negocio.informes.servicio.ValidaBajaInformeVO eliminarInformeFederado(java.lang.String[] ficheros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.informes.servicio.InformeVO[] listarInformes() throws java.rmi.RemoteException;

    /**
     * Metodo para listar los metodos federados
     */
    public es.pode.contenidos.negocio.informes.servicio.InformeVO[] listarInformesFederados() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.informes.servicio.InformeVO[] listarInformesMas() throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler recuperarInforme(java.lang.String fichero) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler recuperarInformeMas(java.lang.String fichero) throws java.rmi.RemoteException;

    /**
     * Metod para recuperar los informes federados
     */
    public javax.activation.DataHandler recuperarInformesFederados(java.lang.String fichero) throws java.rmi.RemoteException;
}
