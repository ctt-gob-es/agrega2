package es.pode.empaquetador.presentacion.decorators;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;

public class ListarAreaCurricularColumDecorator implements DisplaytagColumnDecorator {

	/* (non-Javadoc)
	 * @see org.displaytag.decorator.ColumnDecorator#decorate(java.lang.Object)
	 */
	public Object decorate(Object arg0, javax.servlet.jsp.PageContext pageContext, org.displaytag.properties.MediaTypeEnum media) throws DecoratorException {
		
//		Obtenemos el area curricular de la fila que estamos tratando. Este valor contiene un string[] con todas las 
//		areas curriculares.
//		Suponemos que el objeto que le pasan es ya el objeto que tengo que decorar, en este caso un string[]
		return concatenaArrayString((String[]) arg0);
	}
	
	private String concatenaArrayString(String[] string)
	{
		String salida = "";
		if (string!=null && string.length >0) {
			String[] array = string;
	    	for (int i = 0; i < array.length; i++) {
	    		if (i == 0)
	    			salida = array[0] + ((array.length > 1)?", ":"");
	    		else if ((i + 1) == array.length)
	    			salida = salida + array[i];
	    		else
	    			salida= salida + ", " + array[i];
			}
		}
		return salida;
	}
}



