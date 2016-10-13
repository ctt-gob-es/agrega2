package es.pode.administracion.presentacion.noticias.noticias;

import java.util.Calendar;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;

public class ListarNoticiasColumDecorator implements DisplaytagColumnDecorator {

	
	private final static String SLIDE = "/";	
	
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.ColumnDecorator#decorate(java.lang.Object)
	 */
	
	
	public Object decorate(Object arg0, javax.servlet.jsp.PageContext pageContext, org.displaytag.properties.MediaTypeEnum media) throws DecoratorException {
		
//		Obtenemos el area curricular de la fila que estamos tratando. Este valor contiene un string[] con todas las 
//		areas curriculares.
//		Suponemos que el objeto que le pasan es ya el objeto que tengo que decorar, en este caso un string[]
		return concatenaArrayString((Calendar) arg0);
	}
	
	
	private String concatenaArrayString(Calendar fecha)
	{
		String salida = "";		
		System.out.println(fecha);
		
		salida=String.valueOf((fecha.get(Calendar.DAY_OF_MONTH)))+SLIDE;
		salida=salida+String.valueOf(fecha.get(Calendar.MONTH)+1)+SLIDE;
		salida=salida+fecha.get(Calendar.YEAR);
		
		return salida;
	}

}






