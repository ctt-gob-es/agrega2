package es.pode.modificador.negocio.cambios;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.validador.negocio.servicio.ErrorParseoVO;
import es.pode.validador.negocio.servicio.SrvValidadorService;
import es.pode.validador.negocio.servicio.ValidaVO;

public class ValidacionManifest implements Cambio {

	private Logger logger = Logger.getLogger(this.getClass());

	
	public boolean ejecutar(Manifest manifest, Appender informe, String path) {
		boolean result = false;
		if(logger.isDebugEnabled()) logger.debug("A�adiendo appender para generar fichero de traza");
		logger.addAppender(informe);
		SrvValidadorService validador = null;
		try {
			validador = getSrvValidadorService();
		
			logger.info("Iniciando validacion de " + path);
			ValidaVO resultado = validador.validarCargaOde(path);
			if(resultado.getEsValidoManifest().booleanValue()) {
				logger.info("La validaci�n del ode " + path + " se ha terminado con �xito");
				result = true;
			} else {
				logger.error("Se han encontrado errores de validaci�n : " + resultado.getResultadoValidacion());
				if(resultado.getErrores()!=null && resultado.getErrores().length>0) {
					String errores = formatearErrores(resultado.getErrores());
					logger.error("El detalle de los errores es:\n" + errores);
				}
			}
		} catch (ServiceException e) {
			logger.fatal("No se ha podido obtener una referencia al servicio de validaci�n");
		} catch (Exception e) {
			logger.fatal("Error inesperado al validar " + path);
			if(logger.isDebugEnabled()) logger.debug(e);
		} finally {
			// Quito el appender del logger
			logger.removeAppender(informe);
		}
		return result;
	}

	/**
	 * 
	 * Genera un mensaje de error con el fomato:
	 * 
	 * L=#, C=# : xerces error message
	 * 
	 * @param errores
	 * @return
	 */
	private String formatearErrores(ErrorParseoVO[] errores) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<errores.length;i++) {
			sb.append("linea=").append(errores[i].getLinea().intValue()).append(
					", columna=").append(errores[i].getColumna().intValue()).append(
					" : ").append(errores[i].getMensaje()).append("\n");
		}
		return sb.toString();
	}
	
	protected final es.pode.validador.negocio.servicio.SrvValidadorService getSrvValidadorService()
			throws java.lang.Exception {
		String srvValidadorServiceFile = "importedServices.properties";
		java.io.InputStream srvValidadorServiceInputStream = SrvHerramientaModificacionBase.class
				.getClassLoader().getResourceAsStream(srvValidadorServiceFile);
		java.util.Properties srvValidadorServiceProperties = new java.util.Properties();
		srvValidadorServiceProperties.load(srvValidadorServiceInputStream);
		String srvValidadorServiceEndPointAddress = "";
		srvValidadorServiceEndPointAddress = (String) srvValidadorServiceProperties
				.get("srvValidadorServicePort");
		logger.debug("srvValidadorServiceEndPointAddress del fichero --> "
				+ srvValidadorServiceEndPointAddress);
		es.pode.validador.negocio.servicio.SrvValidadorServiceService srvValidadorService = new es.pode.validador.negocio.servicio.SrvValidadorServiceServiceLocator();
		if (srvValidadorServiceEndPointAddress.length() > 0)
			((es.pode.validador.negocio.servicio.SrvValidadorServiceServiceLocator) srvValidadorService)
					.setSrvValidadorServiceEndpointAddress(srvValidadorServiceEndPointAddress);
		es.pode.validador.negocio.servicio.SrvValidadorService port = srvValidadorService
				.getSrvValidadorService();
		return port;
	}
	
}
