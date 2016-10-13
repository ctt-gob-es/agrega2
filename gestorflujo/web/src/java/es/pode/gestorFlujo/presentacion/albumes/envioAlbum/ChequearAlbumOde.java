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