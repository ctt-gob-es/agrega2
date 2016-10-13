package es.pode.empaquetador.negocio.utilidades;

import java.util.ArrayList;

import net.sf.dozer.util.mapping.converters.CustomConverter;
import es.pode.empaquetador.negocio.servicio.ControlModeVO;
import es.pode.empaquetador.negocio.servicio.SecuenciaVO;
import es.pode.parseadorXML.castor.Sequencing;

public class SecuenciaVOConverter implements CustomConverter {
	
	
	
	public Object convert(Object destination, Object source, Class destClass,
			Class sourceClass) {
		SecuenciaVO[] dest = null;
		ArrayList<SecuenciaVO> list = new ArrayList<SecuenciaVO>();
		
		
		if(source instanceof Object[]) {
			Object[] sourceArray = (Object[])source;
			for(int i=0;i<sourceArray.length;i++){
				if(sourceArray[i] instanceof Sequencing) {
					SecuenciaVO seq = new SecuenciaVO();
					ControlModeVO control = new ControlModeVO();
					Sequencing seqSrc = (Sequencing)sourceArray[i];
					
					if(seqSrc.getControlMode() != null ) {
						control.setChoice(seqSrc.getControlMode().getChoice());
						control.setChoiceExit(seqSrc.getControlMode().getChoiceExit());
						control.setFlow(seqSrc.getControlMode().getFlow());
						control.setForwardOnly(seqSrc.getControlMode().getForwardOnly());
						seq.setControlMode(control);
						list.add(seq);
					}
				}
			}
			if(list.size()>0) dest = list.toArray(new SecuenciaVO[list.size()]);
			else dest=null;
		}
		return dest;
	}

}
