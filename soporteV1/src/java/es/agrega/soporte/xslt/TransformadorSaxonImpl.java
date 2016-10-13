package es.agrega.soporte.xslt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.TransformerFactoryImpl;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.tinytree.TinyBuilder;
import net.sf.saxon.trans.XPathException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 *	implementación de la interfaz Transformador.
 *	esta clase permite utilizar plantillas hechas con XSLT 2.0 y XPATH 2.0.
 *	utilizando el procesador SAXON.
 * 	
 * 	@author pllasso
 *
 */
public class TransformadorSaxonImpl implements Transformador {
	
	private static Logger logger = Logger.getLogger(TransformadorSaxonImpl.class);
	
	/**
	 * 
	 * @param rutaFicheroXSLT ruta al dichero xslt
	 * @param rutaFicheroOrigen ruta al fichero origen
	 * @param rutaFicheroDestino ruta al fichero destino
	 * 
	 */
	public boolean transformar(
			String rutaFicheroXSLT, 
			String rutaFicheroOrigen,
			String rutaFicheroDestino)
	throws Exception 
	{
		if(rutaFicheroXSLT==null || rutaFicheroXSLT.equals(""))
		{
			logger.info("el parametro rutaFicheroXSLT no puede ser vacio..");
			return false;
		}
		if(rutaFicheroOrigen==null || rutaFicheroOrigen.equals(""))
		{
			logger.info("el parametro rutaFicheroOrigen no puede ser vacio..");
			return false;
		}
		if(rutaFicheroDestino==null || rutaFicheroDestino.equals(""))
		{
			logger.info("el parametro rutaFicheroDestino no puede ser vacio..");
			return false;
		}
		
		boolean resultado= true;
		File xmlOrigen=null;
		xmlOrigen=new File(rutaFicheroOrigen);
		
		try {
			transformar(rutaFicheroXSLT, new StreamSource(xmlOrigen), rutaFicheroDestino);
		} catch (Exception e) {
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		}

		return resultado;
	}

	
	
	/**
	 * 
	 * @param rutaFicheroXSLT ruta al dichero xslt
	 * @param manifestSource Objeto del tipo StreamSource con el fichero xml origen
	 * @param rutaFicheroDestino ruta al fichero destino
	 * 
	 */
	public boolean transformar(
			String rutaFicheroXSLT, 
			Source manifestSource,
			String rutaFicheroDestino)
	throws Exception 
	{
		
		boolean resultado= true;
		File xslFile= null;
		FileOutputStream xmlResult= null;
		Result result = null;
		File destino= new File(rutaFicheroDestino);
		try {
			xslFile=new File(rutaFicheroXSLT);
			xmlResult=  new FileOutputStream(destino);
			result = new StreamResult(xmlResult);
			
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Configuration config = ((TransformerFactoryImpl)tfactory).getConfiguration();
			NodeInfo doc = config.buildDocument(new StreamSource(xslFile));
			
			Templates templates = tfactory.newTemplates(doc);

			Transformer transformer = templates.newTransformer();
			doc = config.buildDocument(manifestSource);

			TinyBuilder builder = new TinyBuilder();
			transformer.transform(doc, builder);

			// Serialize the output so we can see the transformation actually worked
			Transformer serializer = tfactory.newTransformer();
			serializer.setOutputProperty("indent", "yes");

			serializer.transform(builder.getCurrentRoot(), result);
		} catch (FileNotFoundException e) {
			if(destino.exists())
				destino.delete();
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		} catch (XPathException e) {
			if(destino.exists())
				destino.delete();
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		} catch (TransformerConfigurationException e) {
			if(destino.exists())
				destino.delete();
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		} catch (TransformerFactoryConfigurationError e) {
			if(destino.exists())
				destino.delete();
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		} catch (TransformerException e) {
			if(destino.exists())
				destino.delete();
			resultado= false;
			logger.error("error en la transformacion XSLT " , e);
		}
		finally
		{
			try{
				if(xmlResult!=null)
					xmlResult.close();

			}catch(Exception e){
				logger.error("error al cerrar el FileOutputStream", e);
			}
		}
		
		return resultado;
	}
}
