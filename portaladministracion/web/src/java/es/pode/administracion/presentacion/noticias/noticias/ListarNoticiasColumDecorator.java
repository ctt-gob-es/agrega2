/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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






