package es.pode.parseadorXML;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Properties;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.OAIPMHAgrega;

public class OaiPmhDao {
	private static Properties props = null;
	
    public static final int IDENTIFY = 0;
    public static final int LISTMETADATAFORMATS = 1;
    public static final int LISTSETS = 2;
    public static final int GETRECORD_DC = 3;
    public static final int GETRECORD_LOMES = 7;
    public static final int GETRECORD_LOM_IEEE = 9;
    public static final int LISTIDENTIFIERS = 4;
    public static final int LISTRECORDS_DC = 5;
    public static final int LISTRECORDS_LOMES = 8;
    public static final int LISTRECORDS_LOM_IEEE = 10;
    public static final int ERROR = 6;
    
	public String getProperty(String key) throws ParseadorException {
		if(props==null) {
			InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
			props = new Properties();
			try {
				props.load(is);
			} catch (Exception e) {
				throw new ParseadorException(e.getMessage(),e);
			}
		}
		String value = props.getProperty(key);
		return value;
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
	 * @param oaipmh
	 * @param oaipmhfile
	 */
	public void escribirOAIPMH_DC(OAIPMHAgrega oaipmh, Writer writer) throws ParseadorException {	
		try {
			Marshaller marshaller = new Marshaller(writer);
			marshaller.setNamespaceMapping("oai_dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
			marshaller.setNamespaceMapping("dc", "http://purl.org/dc/elements/1.1/");
			marshaller.setSchemaLocation("http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd " +
					"http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd");
			marshaller.setSuppressXSIType(true);
			marshaller.setEncoding(getEncoding());
			marshaller.setValidation(false);
			marshaller.marshal(oaipmh.getOaipmh());
			
		} catch (MarshalException e) {
			throw new ParseadorException(
					"Error de parseo al escribir el oaipmh", e);
		} catch (ValidationException e) {
			throw new ValidacionException(
					"Error de validacion al escribir el oaipmh", e);
		} catch (IOException e) {
			throw new ParseadorException(
					"No se pudo abrir el fichero oaipmh para su escritura", e);
		}
		
	}

	public void escribirOAIPMH_DC(OAIPMHAgrega oaipmh, String file)
	throws ParseadorException {
		File fFile = new File(file);
		escribirOAIPMH_DC(oaipmh, fFile);
	}
	
	public void escribirOAIPMH_DC(OAIPMHAgrega oaipmh, OutputStream out)
	throws ParseadorException {
		String encoding = getProperty("default.encoding");
		try {
			OutputStreamWriter osw = new OutputStreamWriter(out,encoding);
			escribirOAIPMH_DC(oaipmh, osw);
		} catch (UnsupportedEncodingException e) {
			throw new ParseadorException(e.getMessage(),e);
		}
	}
	
	public void escribirOAIPMH_DC(OAIPMHAgrega oaipmh, File file)
	throws ParseadorException {
		FileOutputStream fos = null;
		try {
			if(!file.exists()) file.createNewFile();
			else if(file.isDirectory()) {
				throw new ParseadorException("El path " + file.getPath() + " es una carpeta y no puede sobreescribirse como fichero");
			}
		} catch (IOException e) {
			throw new ParseadorException(e.getMessage(),e);
		}
		try {
			fos = new FileOutputStream(file);
			escribirOAIPMH_DC(oaipmh, fos);
		} catch (IOException e) {
			throw new ParseadorException(e.getMessage(),e);
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (Exception e) {
				// no hacer nada
			}
		}
	}
	

	public void escribirOAIPMH(OAIPMHAgrega oaipmh, Writer writer, int tipo) throws ParseadorException {
		escribirOAIPMH(oaipmh, writer, tipo, getProperty("default.encoding"));
	}
	
	
	//ESCRIBIR GENERICOS
	//Escribe los xml de la respuestas a cualquier tipo request de OAI-PMH (Identify,ListMetadataFormats,ListSets,ListIdentifiers,ListRecords,GetRecord)
	/**
	 * @param oaipmh
	 * @param oaipmhfile
	 */
	public void escribirOAIPMH(OAIPMHAgrega oaipmh, Writer writer, int tipo, String encoding) throws ParseadorException {	
		try {
			Marshaller marshaller = new Marshaller(writer);
			String schemas = "http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd";
			
			if(tipo == IDENTIFY){
				String nameSpace = getProperty("nameSpaceIdentify");
				String urlNameSpace = getProperty("urlNameSpaceIdentiy");
				marshaller.setNamespaceMapping(nameSpace, urlNameSpace);
				String schemaLocIdentify = getProperty("schemaLocIdentify");
				schemas = schemas + " " + schemaLocIdentify;
			}
			else if (tipo == GETRECORD_DC || tipo == LISTRECORDS_DC){
				String nameSpaceOAIDC = getProperty("nameSpaceOAIDC");
				String urlNameSpaceOAIDC = getProperty("urlNameSpaceOAIDC");
				marshaller.setNamespaceMapping(nameSpaceOAIDC, urlNameSpaceOAIDC);
				String schemaLocOAIDC = getProperty("schemaLocOAIDC");
				schemas = schemas + " " + schemaLocOAIDC;
				String nameSpaceDC = getProperty("nameSpaceDC");
				String urlNameSpaceDC =getProperty("urlNameSpaceDC");
				marshaller.setNamespaceMapping(nameSpaceDC, urlNameSpaceDC);
			}
			else if (tipo == GETRECORD_LOMES || tipo == LISTRECORDS_LOMES) {
				marshaller.setNamespaceMapping("lomes", "http://ltsc.ieee.org/xsd/LOM");
				marshaller.setSchemaLocation("http://ltsc.ieee.org/xsd/LOM lomCustom.xsd");
			} 
			else if (tipo == GETRECORD_LOM_IEEE || tipo == LISTRECORDS_LOM_IEEE) {
				marshaller.setNamespaceMapping("lom", "http://ltsc.ieee.org/xsd/LOM");
				marshaller.setSchemaLocation("http://ltsc.ieee.org/xsd/LOM lomCustom.xsd");
			}
		
			marshaller.setSchemaLocation(schemas);
			marshaller.setSuppressXSIType(true);
			marshaller.setEncoding(encoding);
			marshaller.setValidation(false);
			marshaller.marshal(oaipmh.getOaipmh());
			
		} catch (MarshalException e) {
			throw new ParseadorException(
					"Error de parseo al escribir el oaipmh", e);
		} catch (ValidationException e) {
			throw new ValidacionException(
					"Error de validacion al escribir el oaipmh", e);
		} catch (IOException e) {
			throw new ParseadorException(
					"No se pudo abrir el fichero oaipmh para su escritura", e);
		}
	}

	public void escribirOAIPMH(OAIPMHAgrega oaipmh, String file, int tipo)
	throws ParseadorException {
		File fFile = new File(file);
		escribirOAIPMH(oaipmh, fFile, tipo, getProperty("default.encoding"));
	}

	public void escribirOAIPMH(OAIPMHAgrega oaipmh, String file, int tipo, String encoding)
	throws ParseadorException {
		File fFile = new File(file);
		escribirOAIPMH(oaipmh, fFile, tipo, encoding);
	}

	public void escribirOAIPMH(OAIPMHAgrega oaipmh, OutputStream out, int tipo) throws ParseadorException {
		try {
			escribirOAIPMH(oaipmh, out, tipo, getProperty("default.encoding"));
		} catch (ParseadorException e) {
			throw new ParseadorException(e.getMessage(),e);
		}
	}
	
	public void escribirOAIPMH(OAIPMHAgrega oaipmh, OutputStream out, int tipo, String encoding)
	throws ParseadorException {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(out,encoding);
			escribirOAIPMH(oaipmh, osw, tipo, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new ParseadorException(e.getMessage(),e);
		}
	}

	public void escribirOAIPMH(OAIPMHAgrega oaipmh, File file, int tipo)
	throws ParseadorException {
		escribirOAIPMH(oaipmh, file, tipo, getProperty("default.encoding"));
	}
	
	public void escribirOAIPMH(OAIPMHAgrega oaipmh, File file, int tipo, String encoding)
	throws ParseadorException {
		FileOutputStream fos = null;
		try {
			if(!file.exists()) file.createNewFile();
			else if(file.isDirectory()) {
				throw new ParseadorException("El path " + file.getPath() + " es una carpeta y no puede sobreescribirse como fichero");
			}
		} catch (IOException e) {
			throw new ParseadorException(e.getMessage(),e);
		}
		try {
			fos = new FileOutputStream(file);
			escribirOAIPMH(oaipmh, fos, tipo, encoding);
		} catch (IOException e) {
			throw new ParseadorException(e.getMessage(),e);
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (Exception e) {
				// no hacer nada
			}
		}
	}
	
}
