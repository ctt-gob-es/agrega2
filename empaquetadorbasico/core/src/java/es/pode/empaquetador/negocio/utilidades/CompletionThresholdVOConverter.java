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
