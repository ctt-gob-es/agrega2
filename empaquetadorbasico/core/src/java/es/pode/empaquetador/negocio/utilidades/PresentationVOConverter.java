package es.pode.empaquetador.negocio.utilidades;

import java.util.ArrayList;

import net.sf.dozer.util.mapping.converters.CustomConverter;
import es.pode.empaquetador.negocio.servicio.HideLMSUIVO;
import es.pode.empaquetador.negocio.servicio.NavigationInterfaceVO;
import es.pode.empaquetador.negocio.servicio.PresentationVO;
import es.pode.parseadorXML.castor.NavigationInterface;
import es.pode.parseadorXML.castor.Presentation;
import es.pode.parseadorXML.castor.types.HideLMSUIType;

public class PresentationVOConverter implements CustomConverter{

	public Object convert(Object destination, Object source, Class destClass,
			Class sourceClass) {
		PresentationVO presentation = null;
		
		if(source instanceof Object[]) {
			Object[] sourceArray = (Object[])source;
			for(int i=0;i<sourceArray.length;i++){
				if(sourceArray[i] instanceof Presentation) {
					Presentation presentationSrc = (Presentation)sourceArray[i];					
					if(presentationSrc.getNavigationInterface() != null ) {
						NavigationInterface navig = presentationSrc.getNavigationInterface();
						HideLMSUIType[] hide = navig.getHideLMSUI();
						if(hide!=null && hide.length>0){
							ArrayList listHide = new ArrayList();
							for(int j= 0; j< hide.length;j++){
								if(hide[j]!=null){
									HideLMSUIVO hideVO = new HideLMSUIVO();
									hideVO.setContent(hide[j].toString());
									listHide.add(hideVO);
								}
							}
							HideLMSUIVO[] hideLMSUI = (HideLMSUIVO[]) listHide.toArray(new HideLMSUIVO[listHide.size()]);
							NavigationInterfaceVO navigationInterface  = new NavigationInterfaceVO();
							navigationInterface.setHideLMSUI(hideLMSUI);
							presentation = new PresentationVO();
							presentation.setNavigationInterface(navigationInterface);
						}
					}
				}
			}
		}
		return presentation;
	}

}
