/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.gestorFlujo.presentacion.albumes.envioAlbum;

import org.apache.log4j.Logger;
import org.displaytag.decorator.TableDecorator;

import es.pode.gestorFlujo.presentacion.albumes.envioAlbum.AlbumCheckVO;

public class ChequearAlbumOde extends TableDecorator 
{  
	private Logger logger = Logger.getLogger(ChequearAlbumOde.class);
	
	public String getCheck() throws Exception 
	{  
		Object obj = this.getCurrentRowObject(); 
		AlbumCheckVO albumCheck= ((AlbumCheckVO) obj);
		logger.debug("Marco el album [ "+ albumCheck.getTitulo()+"] para el usuario ["+ albumCheck.getUsuario()+"]");
		String estaChequeado = "";
		//si el album ya tiene el ode seleccionado, se lo indicamos al usuario
		if (albumCheck.getCheck().booleanValue()) 
			estaChequeado = "checked";
		logger.debug("El album: " +albumCheck.getTitulo()+ "esta chequeado: "+ estaChequeado);
		return " <input type='radio' name='idAlbumRowSelectionAsArray' value='" + albumCheck.getIdAlbum().toString() + "' "+estaChequeado+" title=''/> ";
	
	}  
}  