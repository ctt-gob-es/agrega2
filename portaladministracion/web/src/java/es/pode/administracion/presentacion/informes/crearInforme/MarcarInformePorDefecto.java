package es.pode.administracion.presentacion.informes.crearInforme;

import org.displaytag.decorator.TableDecorator;

import es.pode.administracion.presentacion.informes.crearInforme.CrearInformeControllerImpl.InformesAuditoria_VO;

public class MarcarInformePorDefecto extends TableDecorator {

		public String getCheck() throws Exception 
		{  
			Object obj = this.getCurrentRowObject();  
			String checked = "";  
			
			InformesAuditoria_VO vo = ((InformesAuditoria_VO) obj);
			
			//BasicDynaBean vo = ((BasicDynaBean) obj);  
			if (vo.getCodigo().equals("estadoOdes"))
				checked = "checked"; 
	        return " <input type='radio' name='informe' value='" + vo.getCodigo() + "' " + checked + " title='' /> ";
	    
		}  
	
}
