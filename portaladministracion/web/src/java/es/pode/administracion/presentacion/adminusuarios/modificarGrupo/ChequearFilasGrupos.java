/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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