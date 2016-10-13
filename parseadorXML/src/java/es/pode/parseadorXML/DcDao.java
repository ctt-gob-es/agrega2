package es.pode.parseadorXML;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Properties;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import es.pode.parseadorXML.oai_dc.Dc;


	public class DcDao {
		private static Properties props = null;
		
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
		 * @param lom
		 * @param lomfile
		 */
		public void escribirDC(Dc dublinCore, Writer writer) throws ParseadorException {
			
			try {
				Marshaller marshaller = new Marshaller(writer);
				marshaller.setEncoding(getEncoding());
				marshaller.setNamespaceMapping("oai_dc", "http://www.openarchives.org/OAI/2.0/oai_dc/");
				marshaller.setNamespaceMapping("dc", "http://purl.org/dc/elements/1.1/");
				marshaller.setSchemaLocation("http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd");
				marshaller.setSuppressXSIType(true);
				marshaller.setValidation(false);
				marshaller.marshal(dublinCore);
				
			} catch (MarshalException e) {
				throw new ParseadorException(
						"Error de parseo al escribir el lom", e);
			} catch (ValidationException e) {
				throw new ValidacionException(
						"Error de validacion al escribir el lom", e);
			} catch (IOException e) {
				throw new ParseadorException(
						"No se pudo abrir el fichero lom-es para su escritura", e);
			}
			
		}

		public void escribirDC(Dc dublinCore, String file)
		throws ParseadorException {
			File fFile = new File(file);
			escribirDC(dublinCore, fFile);
		}
		
		public void escribirDC(Dc dublinCore, OutputStream out)
		throws ParseadorException {
			String encoding = getEncoding();
			try {
				OutputStreamWriter osw = new OutputStreamWriter(out,encoding);
				escribirDC(dublinCore, osw);
			} catch (UnsupportedEncodingException e) {
				throw new ParseadorException(e.getMessage(),e);
			}
		}
		
		public void escribirDC(Dc dublinCore, File file)
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
				escribirDC(dublinCore, fos);
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
		
		
public Dc parsearDC(InputSource is)
		throws ParseadorException {
	/*
	 * TODO: Implementar la funcionalidad EAGER.
	 */
	// Abrir fichero xml para su lectura.
	Dc dublinCore = null;

	
	try {
		Unmarshaller unmarshaller = new Unmarshaller(Dc.class);
		// Omito la validacion para evitar fallos de validacion de IDREF de
		// imsss
		unmarshaller.setValidation(false);
		dublinCore = (Dc) unmarshaller.unmarshal(is);

		
	} catch (MarshalException e) {
		throw new ParseadorException("Error en el parseo de Dublin Core", e);
	} catch (ValidationException e) {
		throw new ValidacionException(
				"Error de validacion en el parseo del Dublin Core", e);
	}
	return dublinCore;
}

public Dc parsearDC(File file)
throws ParseadorException {
	FileInputStream fis = null;
	Dc dublinCore = null;
	try {
		fis = new FileInputStream(file);
		dublinCore = parsearDC(fis);
	} catch (IOException e) {
		throw new ParseadorException(e.getMessage(),e);
	} finally {
		try {
			if(fis!=null) fis.close();
		} catch (Exception e) {
			// No hacer nada
		}
	}
	return dublinCore;
}

public Dc parsearDC(String xmlPath)
throws ParseadorException {
	File file = new File(xmlPath);
	Dc dublinCore = null;
	if(file.isFile() && "pruebaDC.xml".equals(file.getName())) {
		dublinCore = parsearDC(file);
	} else {
		throw new ParseadorException("El fichero " + xmlPath + " no existe o no es un PruebaDC.xml");
	}
	return dublinCore;
}

public Dc parsearDC(InputStream is)
throws ParseadorException {
	InputSource isrc = null;
	Dc dublinCore = null;
	isrc = new InputSource(is);
	dublinCore = parsearDC(isrc);
	return dublinCore;
}

public Dc parsearDC(Reader reader)
throws ParseadorException {
	InputSource isrc = null;
	Dc dublinCore = null;
	isrc = new InputSource(reader);
	dublinCore = parsearDC(isrc);
	return dublinCore;
}
		
		
		
		
}
