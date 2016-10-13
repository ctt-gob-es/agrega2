package es.pode.buscador.presentacion.basico.listar;


import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;

public class ListarAreaCurricularColumDecorator implements DisplaytagColumnDecorator {

	
	private final static String SLIDE = "/";
	private final static String SPACE = " ";
	private final static int LONGITUD_AREA_CURRICULAR = 250;
	
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
		StringBuilder salida = new StringBuilder();
		salida.append("- ");
		StringBuilder areaCurricular = new StringBuilder();
		
		if (string!=null && string.length >0) {
	    	for (int i = 0; i < string.length; i++) {
	    		String[] areas = string[i].split(SLIDE);
	    		for (int k = 0; k < areas.length; k++) {
		    		String[] array = areas[k].split(SPACE);
		    		for(int j = 0; j < array.length; j++) {
		    			int primero=j;
		    			if ((areaCurricular + array[j]+ SPACE).length()>LONGITUD_AREA_CURRICULAR){
		    				salida.append(areaCurricular);
		    				salida.append("<BR>");
		    				areaCurricular = new StringBuilder();
		    				areaCurricular.append(array[j]);
		    				areaCurricular.append(SPACE);
		    				
		    			}else{
		    				if (primero==0){
		    					areaCurricular.append(array [j]);
		    				}else{
		    					areaCurricular.append(SPACE);
		    					areaCurricular.append(array [j]);
		    				}
		    			}		    			
		    		}
		    		if (k != areas.length-1){
		    			if ((areaCurricular +SLIDE).length() >LONGITUD_AREA_CURRICULAR){
		    				salida.append(areaCurricular);
		    				salida.append("<BR>");
		    				areaCurricular = new StringBuilder();
		    				areaCurricular.append(SLIDE);
		    			}else{
		    				areaCurricular.append(SLIDE);
		    			}
		    		}
	    		}
	    		if (i != string.length-1){
	    			salida.append(areaCurricular);
    				salida.append("<BR>");
    				areaCurricular = new StringBuilder();
    				areaCurricular.append("- ");
	    		}
	    	}
	    	if (!areaCurricular.toString().equals("- ")){
	    		salida.append(areaCurricular);
	    	}
		}
		if (salida.toString().equals("- ")){
			salida.delete(0, salida.length()-1);
			salida.append("");
		}
		return salida.toString();
	}

}






