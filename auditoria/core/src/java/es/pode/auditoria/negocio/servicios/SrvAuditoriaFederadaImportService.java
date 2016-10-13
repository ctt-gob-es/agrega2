/**
 * SrvBuscadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


public interface SrvAuditoriaFederadaImportService extends java.rmi.Remote {

    /**
     * @param parametros 
     * @return NivelAgregacionVO[]
     * @throws java.rmi.RemoteException 

     */
	es.pode.auditoria.negocio.servicios.NivelAgregacionVO[] informeNivelAgregacionFederado(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
		
	/**
	 * @param parametros 
	 * @return NivelAgregacionVO
	 * @throws java.rmi.RemoteException 
     */
	es.pode.auditoria.negocio.servicios.NivelAgregacionVO nivelAgregacionFederadoLocal(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	

	es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO coberturaFederadoLocal(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	
	
	es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO[] informeCoberturaFederado(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	
	es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO[] informeOdesIdiomaFederado(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	
	es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO informeOdesIdiomaLocal(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	
	es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO[] informeOdesPublicadosFederado(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
	
	es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO odesPublicadosFederadoLocal(ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

}
