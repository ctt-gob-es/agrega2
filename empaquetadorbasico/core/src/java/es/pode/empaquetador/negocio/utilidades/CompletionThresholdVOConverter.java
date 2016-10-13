/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.empaquetador.negocio.utilidades;

import java.math.BigDecimal;

import es.pode.empaquetador.negocio.servicio.CompletionThresholdVO;
import es.pode.parseadorXML.castor.CompletionThreshold;
import net.sf.dozer.util.mapping.converters.CustomConverter;

public class CompletionThresholdVOConverter implements CustomConverter{
	private static final BigDecimal FACTOR = new BigDecimal(Math.pow(10, 3));
	public Object convert(Object destination, Object source, Class destClass,
			Class sourceClass) {
		CompletionThresholdVO completion = null;

		if(source instanceof Object[]) {
			Object[] sourceArray = (Object[])source;
			for(int i=0;i<sourceArray.length;i++){
				if(sourceArray[i] instanceof CompletionThreshold) {
					CompletionThreshold completionSrc = (CompletionThreshold)sourceArray[i];					
					if(completionSrc.getContent() != null ) {
						completion = new CompletionThresholdVO();	
						completion.setContent(completionSrc.getContent().doubleValue());
					}
				}
			}
		}
		return completion;
	}

}
