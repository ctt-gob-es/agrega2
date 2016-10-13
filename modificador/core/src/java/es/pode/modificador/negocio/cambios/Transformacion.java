/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.modificador.negocio.cambios;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import es.pode.entregar.negocio.servicios.ArgTransformarOdesVO;
import es.pode.entregar.negocio.servicios.CodigoResultadoTransformacion;
import es.pode.entregar.negocio.servicios.ResultadoTransformacionVO;
import es.pode.entregar.negocio.servicios.TipoPIFCst;
import es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase;
import es.pode.parseadorXML.castor.Manifest;

public class Transformacion implements Cambio {
	
	private Logger logger = Logger.getLogger(Transformacion.class);
	private String tipoPIF = null;
	private String pathDestino = null;

	public Transformacion()
	{
		
	}
	
	public boolean ejecutar(
			Manifest manifest, 
			Appender informe, 
			String path)
	{

		String tipoPIF = this.tipoPIF;
		String pathDestino = this.pathDestino;
		logger.addAppender(informe);
		boolean accion=false;
		try
		{
			ArgTransformarOdesVO arg= new ArgTransformarOdesVO();
			arg.setPathOrigen(path);
			arg.setPathDestino(pathDestino);
			arg.setTipoPifDestino(conversionFormato(tipoPIF));
			
			logger.info("Iniciando transformación de un ode al tipo " + tipoPIF);
			ResultadoTransformacionVO[] vuelta = this.getSrvEntregarService().transformarFormatosODEs(arg);
			
			if(vuelta!=null)
			{
				for (int i = 0; i < vuelta.length; i++) {
					if(vuelta[i].getCodigo()!= CodigoResultadoTransformacion.S_001) // error!!
					{
						logger.error("error al transformar un ode: " + vuelta[i].getMensaje() );
						if(! vuelta[i].getResultadoValidacion().getEsValidoManifest().booleanValue())
						{
							logger.error("Resultado de validación: " + vuelta[i].getResultadoValidacion().getResultadoValidacion());
						}
					}else
					{
						accion= true;
						logger.info("Se ha transformado correctamente el ode al formato "+ tipoPIF +" a "+ pathDestino);
					}
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("No se ha podido exportar  el ode" + path,e);
		} finally {
			logger.removeAppender(informe);
		}
		return accion;
	}

    protected final es.pode.entregar.negocio.servicios.SrvEntregarService getSrvEntregarService()
	throws java.lang.Exception 
	{
		String srvEntregarServiceFile = "importedServices.properties";
		java.io.InputStream srvEntregarServiceInputStream = SrvHerramientaModificacionBase.class
				.getClassLoader().getResourceAsStream(srvEntregarServiceFile);
		java.util.Properties srvEntregarServiceProperties = new java.util.Properties();
		srvEntregarServiceProperties.load(srvEntregarServiceInputStream);
		String srvEntregarServiceEndPointAddress = "";
		srvEntregarServiceEndPointAddress = (String) srvEntregarServiceProperties.get("srvEntregarServicePort");
		logger.debug("srvEntregarServiceEndPointAddress del fichero --> "+ srvEntregarServiceEndPointAddress);
		es.pode.entregar.negocio.servicios.SrvEntregarServiceService srvEntregarService = new es.pode.entregar.negocio.servicios.SrvEntregarServiceServiceLocator();
		if (srvEntregarServiceEndPointAddress.length() > 0)
			((es.pode.entregar.negocio.servicios.SrvEntregarServiceServiceLocator) srvEntregarService).setSrvEntregarServiceEndpointAddress(srvEntregarServiceEndPointAddress);
		es.pode.entregar.negocio.servicios.SrvEntregarService port = srvEntregarService.getSrvEntregarService();
		return port;
	}

	public String getTipoPIF() {
		return tipoPIF;
	}

	public void setTipoPIF(String tipoPIF) {
		this.tipoPIF = tipoPIF;
	}

	public String getPathDestino() {
		return pathDestino;
	}

	public void setPathDestino(String pathDestino) {
		this.pathDestino = pathDestino;
	}

	
	private TipoPIFCst conversionFormato(String tipoPIF){
		TipoPIFCst vuelta= null;
		if(tipoPIF!=null && tipoPIF.length()>0){
			if(tipoPIF.equals("SCORM2004SS")){
				vuelta=TipoPIFCst.fromString("SCORM_2004_SIN_SUBMANIFIESTO");
			}else if(tipoPIF.equals("SCORM12")){
				vuelta=TipoPIFCst.fromString("SCORM_12");
			}else if(tipoPIF.equals("IMSCP")){
				vuelta=TipoPIFCst.fromString("IMS_CP");
			}else if(tipoPIF.equals("HTML")){
				vuelta=TipoPIFCst.fromString("HTML");
			}else if(tipoPIF.equals("CONTENIDOS")){
				vuelta=TipoPIFCst.fromString("CONTENIDOS");
			}else{
				vuelta=TipoPIFCst.fromString("SCORM_2004");
			}
		}
		return vuelta;
	}

}
