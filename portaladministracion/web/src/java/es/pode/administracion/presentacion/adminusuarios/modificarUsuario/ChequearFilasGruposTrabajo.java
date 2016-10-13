package es.pode.administracion.presentacion.adminusuarios.modificarUsuario;

import org.displaytag.decorator.TableDecorator;

import es.pode.administracion.presentacion.adminusuarios.modificarUsuario.ModificarUsuarioControllerImpl.GrupoTrabajoVOCheck;


public class ChequearFilasGruposTrabajo extends TableDecorator 
{  
 
	public String getCheck() throws Exception 
	{  
		Object obj = this.getCurrentRowObject();  
		String checked = "";  
		String id = "";  
		String name = "";
		String usuario = ""; //Si es Administrador todos los check aparecerán deshabilitados
		
		GrupoTrabajoVOCheck vo = ((GrupoTrabajoVOCheck) obj);
		
		//BasicDynaBean vo = ((BasicDynaBean) obj);  
		if (vo.getChecked().booleanValue() == true) 
		{  
		     checked = "checked";  
		}
		  
		id = vo.getId().toString();  
        usuario =  vo.getLoginUsuario().toString();
        
       // if(usuario.equalsIgnoreCase("administrador"))
        //{
        //	return " <input DISABLED type='checkbox' name='identificadorRowSelectionAsArray' value='" + id + "' " + checked + " title='' /> ";
       // }else
       // {
        	return " <input type='checkbox' name='identificadorRowSelectionAsArray' value='" + id + "' " + checked + " title='' /> ";
        //}
		
          
    
	}  
}  