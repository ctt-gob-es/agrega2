package es.pode.modificador.negocio.cambios;

import org.jdom.Element;

public class EliminacionTermino extends CambioLomes {

	
	protected final boolean ejecutarAccion(Element[] termino) {

		boolean result = true;
		
		StringBuffer sb = generarMensaje("Eliminación");
		if(termino !=null && termino.length>0) {
			logger.info(sb.toString() + ": Se han encontrado "+termino.length+" para eliminar.");
			
			for(int i=0;i<termino.length;i++) {
				Element padre = termino[i].getParentElement();
				boolean borrado = padre.removeContent(termino[i]);
				if(borrado) {
					logger.info("Eliminado termino " + termino[i].getName() + " con valor " + termino[i].getValue());
				} else {
					result = false;
					logger.error("No se ha podido eliminar el término " + termino[i].getName() + " con valor " + termino[i].getValue());
				}
			}
			if(!result) {
				logger.error(sb.toString() + ": No se han podido eliminar todos los terminos encontrados");
			}
		} else {
			sb.append(": No se han encontrado términos para eliminar");
			logger.warn(sb.toString());
		}
		
		return result;
	}

	
	

}
