/**
 * SrvBuscadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.federada.servicios;


public interface SrvBuscarFederadaImportService extends java.rmi.Remote {

    /**
     * @param parametros 
     * @return  
     * @throws java.rmi.RemoteException 

     */
	public es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 busquedaFederada30(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException;
	
	/**
	 * @param parametros 
	 * @return 
	 * @throws java.rmi.RemoteException 

     */ 
	public es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO solicitarDocsCountArbolCurricular30(es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30 parametros) throws java.rmi.RemoteException;
	
    /**
     * @return 
     * @throws java.rmi.RemoteException 

     */
    public boolean estoyActivo() throws java.rmi.RemoteException;
    
    /**
     * @return 
     * @throws java.rmi.RemoteException 

     */
    public String obtenerIdentificadorNodo() throws java.rmi.RemoteException;
    
    /**
     * @return 
     * @throws java.rmi.RemoteException 

     */
    public String devolverVersionNodo() throws java.rmi.RemoteException;
}
