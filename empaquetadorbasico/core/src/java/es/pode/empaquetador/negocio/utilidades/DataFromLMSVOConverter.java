package es.pode.empaquetador.negocio.utilidades;

import es.pode.empaquetador.negocio.servicio.DataFromLMSVO;
import es.pode.parseadorXML.castor.DataFromLMS;
import net.sf.dozer.util.mapping.converters.CustomConverter;

public class DataFromLMSVOConverter implements CustomConverter{

	public Object convert(Object destination, Object source, Class destClass,
			Class sourceClass) {
		DataFromLMSVO data = null;
		
		if(source instanceof Object[]) {
			Object[] sourceArray = (Object[])source;
			for(int i=0;i<sourceArray.length;i++){
				if(sourceArray[i] instanceof DataFromLMS) {
					DataFromLMS dataSrc = (DataFromLMS)sourceArray[i];					
					if(dataSrc.getContent() != null ) {
						data = new DataFromLMSVO();
						data.setContent(dataSrc.getContent());
					}
				}
			}
		}
		return data;
	}

}
