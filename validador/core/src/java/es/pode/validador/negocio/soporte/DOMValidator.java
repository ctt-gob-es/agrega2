package es.pode.validador.negocio.soporte;

import org.w3c.dom.Node;

public interface DOMValidator {

	public void validate( Node rootNode ) throws Exception;
}
