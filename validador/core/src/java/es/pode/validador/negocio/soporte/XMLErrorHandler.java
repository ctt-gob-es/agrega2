package es.pode.validador.negocio.soporte;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler implements ErrorHandler {

	public void error(SAXParseException exception) throws SAXException {
		throw exception;
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		throw exception;
		
	}

	public void warning(SAXParseException exception) throws SAXException {
		throw exception;
		
	}
}
