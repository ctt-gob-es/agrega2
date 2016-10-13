/**
 * 
 */
package es.pode.parseadorXML;

import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import es.pode.parseadorXML.rest.RSP;

/**
 * Clase para la generacion de codigo REST-XML de Agrega
 * 
 * @author fjespino
 *
 */
public class RestDao {

	private static Logger logger = Logger.getLogger(RestDao.class);
	
	private static Properties props = null;
	
	private static Mapping restMapping = null;

	/**
	 * Respuesta de error inesperado para el caso en que falle la escritura de
	 * XML. Debe actualizarse si se cambia el esquema de respuesta XML-REST
	 */
	private static final String ERROR_INESPERADO = "<rsp stat=\"" + RSP.STAT_ERROR + "\" error-code=\"0\" error-msg=\"Unexpected error\" />";
	
	private String getProperty(String key) throws ParseadorException {
		if(props==null) {
			InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
			props = new Properties();
			try {
				props.load(is);
				
			} catch (Exception e) {
				logger.error("Error al leer parseadorXML.properties : " + e.getMessage());
				if(logger.isDebugEnabled()) logger.debug(e);
				throw new ParseadorException(e.getMessage(),e);
			} finally {
				try {
				is.close();
				} catch (Exception e) {
					// No importa
				}
			}
		}
		String value = props.getProperty(key);
		return value;
	}
	
	public RestDao() {
		try {
			if(restMapping==null) init();
		} catch (Exception e) {
			logger.error("Error al inicializar RestDao",e);
		}
	}
	
	protected synchronized void init() throws Exception {
		if(restMapping==null) {
			restMapping = new Mapping();
			URL mappingFile = this.getClass().getResource("/castorRestMappings.xml");
			if(logger.isDebugEnabled()) logger.debug("Inicializando Mapper con " + ((mappingFile==null)?mappingFile:mappingFile.getPath()));
			restMapping.loadMapping(mappingFile);
		}
	}
	
	private String getEncoding() throws ParseadorException {

		String encoding="";
		/*
		//Tratamos de usar el encoding que se le haya indicado en el arranque de JBoss
		try {
    		encoding = System.getProperty("file.encoding");
		} catch (Exception e) {
			//Si no tratamos de usar el charset por defecto
			try {
				encoding = Charset.defaultCharset().toString();
			} catch (Exception ex) {
				encoding = getProperty("default.encoding");
			}
		}
		if(encoding.contentEquals(""))
		*/
			encoding = getProperty("default.encoding");
		
		return encoding;
	}
	
	/**
	 * Genera el XML-REST de respuesta a una peticion REST. El resultado se
	 * escribe en el stream proporcionado como parametro.
	 * 
	 * @param response Objeto POJO con la informacion a escribir en la respuesta.
	 * @param writer Stream en el que se escribe el XML generado.
	 * 
	 * @throws NullPointerException Cuando response o writer son nulos.
	 */
	public void escribirRespuestaRest(RSP response, Writer writer) {
		
		if(response==null) throw new NullPointerException("response");
		
		if(writer==null) throw new NullPointerException("writer");
		try {
			Marshaller marshaller = new Marshaller(writer);
			marshaller.setMapping(restMapping);
			marshaller.setEncoding(getEncoding());
			marshaller.marshal(response);
			writer.flush();
		} catch (Exception e) {
			logger.error(e);
			try {
			writer.append(ERROR_INESPERADO);
			writer.flush();
			} catch (Exception e2) {
				logger.error("Ha sido imposible escribir una respuesta en el objeto writer");
			}
		}
	}

	public RSP leerRespuestaRest(InputSource in) throws Exception {
		if(in==null) throw new NullPointerException("is");
		
		RSP rsp = null;
		Unmarshaller unmarshaller = new Unmarshaller(restMapping);
		Object obj = unmarshaller.unmarshal(in);
		if(obj instanceof RSP) {
			rsp = (RSP)obj;
		} else {
			logger.error("El XML leido no tiene <rsp> como nodo raiz o no es una respuesta REST");
		}
		return rsp;
	}
}
