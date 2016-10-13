// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.modificarGrupo;

import org.displaytag.decorator.TableDecorator;

import es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGruposControllerImpl.RolVOCheck;

public class ChequearFilasGrupos extends TableDecorator 
{  
 
	public String getCheck() throws Exception 
	{  
		Object obj = this.getCurrentRowObject();  
		String checked = "";  
		String id = "";  
		String name = "";
		
		RolVOCheck vo = ((RolVOCheck) obj);
		
		//BasicDynaBean vo = ((BasicDynaBean) obj);  
		if (vo.getChecked().booleanValue() == true) 
		{  
		     checked = "checked";  
		}
		  
		id = vo.getId().toString();  
              
   
		return " <input type='checkbox' name='idRowSelectionAsArray' value='" + id + "' " + checked + " title='<bean:message key=\"usuarios.seleccione\"/>' /> ";  
    
	}  
}  