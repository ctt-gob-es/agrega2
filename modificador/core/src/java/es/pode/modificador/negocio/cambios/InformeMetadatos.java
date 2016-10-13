/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.modificador.negocio.cambios;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import es.agrega.soporte.xslt.TransformadorSaxonImpl;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Manifest;

/**
 * @author dgonzalezd
 *
 */
public class InformeMetadatos implements Cambio, InformeGeneral {

	private Logger logger = Logger.getLogger(this.getClass());
	private static final String MANIFEST_NAME="imsmanifest.xml";
	private static final String XSLT_PATH = "uploads/xslt/informe_metadata.xsl";
	
	/* (non-Javadoc)
	 * @see es.pode.modificador.negocio.cambios.Cambio#ejecutar(es.pode.parseadorXML.castor.Manifest, org.apache.log4j.Appender, java.lang.String)
	 */
	public boolean ejecutar(final Manifest manifest, Appender informe, String path) {
		boolean result = false;
		if(logger.isDebugEnabled()) logger.debug("Añadiendo appender para generar fichero de traza");
		logger.addAppender(informe);
		try {
			
			File fileXLST = new File(XSLT_PATH);
			if(!fileXLST.exists()) {
				logger.error("No se encontró fichero "+XSLT_PATH);
				return false;
			}
			
			//Aplicamos la transformación al ODE
			logger.info("Extrayendo metadatos de "+manifest.getIdentifier());
			TransformadorSaxonImpl transformacion = new TransformadorSaxonImpl();
			
//			/* Las pipedStreams usan internamente un buffer circular que no hay que
//			 * gestionar directamente, pero las operaciones de lectura y escritura
//			 * deben estar en threads diferentes para evitar bloqueos. 
//			 * */
//			final PipedInputStream in = new PipedInputStream();
//			final PipedOutputStream out = new PipedOutputStream(in);
//			new Thread(
//					new Runnable() {
//						public void run(){
//							SCORM2004Dao scormDao = new SCORM2004Dao();
//							try {
//								scormDao.escribirODE(manifest, out);
//							} catch (ParseadorException e) {
//								logger.error("Error al parsear el Manifest:",e);
//							}
//						}
//					}
//			).start();
			
			StringWriter sw= new StringWriter();
			SCORM2004Dao scormDao = new SCORM2004Dao();
            scormDao.escribirODE(manifest, sw);
            StringReader sr= new StringReader(sw.toString());
//            String identificador=new LomAgrega(new ManifestAgrega(manifest).obtenerLom(manifest.getIdentifier(), null)).getGeneralAgrega().getPrimerIdentificador();
            result = transformacion.transformar(XSLT_PATH, new StreamSource(sr), path/*+identificador+REPORT_EXT*/);
			
//			transformacion.transformar("uploads/xslt/informe_metadata.xsl", new StreamSource(in), path+REPORT_EXT);
			
//			//Leemos transformacion
//    		StringBuffer buffer = new StringBuffer();
//    		BufferedReader reader = new BufferedReader(new FileReader(path+REPORT_EXT));
//    		String linea;
//    		do {
//    			linea=reader.readLine();
//    			buffer.append(linea);
//			} while (linea!=null);
//    		//y la devolvemos como String
//			logger.info(buffer.toString());
			
		} catch (Exception e) {
			logger.error("No se ha podido recuperar los metadatos del manifiesto " + manifest.getIdentifier(),e);
			return false;
		} finally {
			// Quito el appender del logger
			logger.removeAppender(informe);
		}
		return result;
	}

	public boolean generar(ODE[] odes, Appender informe, String path) {
		boolean result = false;
		logger.addAppender(informe);

		//TODO Cabecera
		logger.info("cabecera");		
		
		String rutaManifest;
		try {
			for (int i = 0; i < odes.length; i++) {
				if(odes[i].getPathTaller().endsWith("/"))
				{
					rutaManifest=odes[i].getPathTaller() + MANIFEST_NAME;
				}
				else
				{
					rutaManifest=odes[i].getPathTaller() + "/" + MANIFEST_NAME;
				}
				
				logger.info("<li><a href='"+rutaManifest+"'>"+odes[i].getTitulo()+"</a></li>");
				
			}
			logger.info("cierre");
		} catch (Exception e) {
			logger.error("Error al generar ODE: "+e);
		} finally {
			//Cierre
			logger.removeAppender(informe);
		}
		result=true;
		return result;
	}

}
