// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.modificarUsuario;

import org.displaytag.decorator.TableDecorator;

import es.pode.administracion.presentacion.adminusuarios.modificarUsuario.ModificarUsuarioControllerImpl.GrupoVOCheck;

public class ChequearFilasGrupos extends TableDecorator 
{  
 
	public String getCheck() throws Exception 
	{  
		Object obj = this.getCurrentRowObject();  
		String checked = "";  
		String id = "";  
		String name = "";
		String usuario = ""; //Si es Administrador todos los check aparecerán deshabilitados
		
		GrupoVOCheck vo = ((GrupoVOCheck) obj);
		
		//BasicDynaBean vo = ((BasicDynaBean) obj);  
		if (vo.getChecked().booleanValue() == true) 
		{  
		     checked = "checked";  
		}
		  
		id = vo.getId().toString();  
        usuario =  vo.getLoginUsuario().toString();
        
        if(usuario.equalsIgnoreCase("administrador"))
        {
        	return " <input DISABLED type='checkbox' name='idRowSelectionAsArray' value='" + id + "' " + checked + " title='' /> ";
        }else
        {
        	return " <input type='checkbox' name='idRowSelectionAsArray' value='" + id + "' " + checked + " title='' /> ";
        }
		
          
    
	}  
}  