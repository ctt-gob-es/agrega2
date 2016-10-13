/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.empaquetador.negocio.utilidades;

import es.pode.empaquetador.negocio.servicio.TimeLimitActionVO;
import es.pode.parseadorXML.castor.TimeLimitAction;
import net.sf.dozer.util.mapping.converters.CustomConverter;

public class TimeLimitActionVOConverter implements CustomConverter{

	public Object convert(Object destination, Object source, Class destClass,
			Class sourceClass) {

		TimeLimitActionVO timeLimit = null;
		
		if(source instanceof Object[]) {
			Object[] sourceArray = (Object[])source;
			for(int i=0;i<sourceArray.length;i++){
				if(sourceArray[i] instanceof TimeLimitAction) {
					TimeLimitAction timeSrc = (TimeLimitAction)sourceArray[i];					
					if(timeSrc.getContent() != null ) {
						timeLimit = new TimeLimitActionVO();
						timeLimit.setContent(timeSrc.getContent());
					}
				}
			}
		}
		return timeLimit;
	}

}
