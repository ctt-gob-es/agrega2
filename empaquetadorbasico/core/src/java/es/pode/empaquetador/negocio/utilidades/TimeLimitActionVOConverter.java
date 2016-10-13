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
