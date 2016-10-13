/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
