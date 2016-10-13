package es.pode.modificador.negocio.cambios;

import org.jdom.Element;

public class ComprobacionTermino extends CambioLomes {

	
	protected final boolean ejecutarAccion(Element[] termino) {
		boolean resultado = false;
		StringBuffer sb = generarMensajeComprobacion();
		if(termino!=null && termino.length>0) {
			sb.append(": Se han encontrado ").append(termino.length).append(" términos en el lom");
			logger.info(sb.toString());
			resultado = true;
		} else {
			sb.append(" No se han encontrado términos en el manifest.");
			logger.error(sb.toString());
			resultado = false;
		}
		return resultado;
	}

	private StringBuffer generarMensajeComprobacion() {
		StringBuffer sb = new StringBuffer();
		sb.append("Comprobacion del término ").append(terminoLom);
		if(valor!=null) {
			sb.append(" con valor ").append(valor);
			if(lenguaje!=null) sb.append(" y lenguaje " + lenguaje);
		}
		return sb;
	}


	
}
