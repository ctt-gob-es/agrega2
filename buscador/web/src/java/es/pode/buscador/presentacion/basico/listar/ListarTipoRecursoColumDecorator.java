package es.pode.buscador.presentacion.basico.listar;

import java.util.ResourceBundle;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;

// Esta clase se utiliza para realizar el mapeo de valores obtenidos de la busqueda del campo TipoRecurso
// a imagenes representativas de cada resultado posible para este campo
public class ListarTipoRecursoColumDecorator implements DisplaytagColumnDecorator {
	
	
	private final static String SLIDE = "/";
	private final static String SPACE = " ";
	private final static String COMA = ",";
	private final static String PUNTO = ".";
	private final static String PUNTOS_SUSPENSIVOS = "...";
	private final static int LONGITUD_TIPO_RECURSO = 28;
	

	/* (non-Javadoc)
	 * @see org.displaytag.decorator.ColumnDecorator#decorate(java.lang.Object)
	 */
	public Object decorate(Object arg0, javax.servlet.jsp.PageContext pageContext, org.displaytag.properties.MediaTypeEnum media) throws DecoratorException {
		
//		Con este codigo recogemos del fichero de internacionalizacion la cadena internaiconalizada de "Mas informacion"
		java.util.Locale locale = pageContext.getRequest().getLocale();
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String textoIntenacional = i18n.getString("listar.odecu.mostrar.resultados.consulta.tablaResultadosBusqueda.masInformacion");
//		BasicDynaBean basicDynaBean = ((BasicDynaBean) this.getCurrentRowObject());
//		
//		Obtenemos el tipo de recurso de la fila que estamos tratando. Este valor contiene un string con todos los 
//		los tipos de recursos que hay asignados al ODE separados por "-"
//		Suponemos que el objeto que le pasan es ya el objeto que tengo que decorar, en este caso un string
		String[] tipoRecurso = (String[]) arg0;
		
//		String cabecera = "<a href=\"#\">&nbsp;<em>M�s informaci�n:</em> <br class=\"oculto\" /><span>";
	
//		String cabecera = "<a href=\"#\">&nbsp; <br class=\"oculto\" /><span>";
		String cabecera = "<span>";
		String cuerpo = "";
//		String cola = "</span></p></a>";
		String cola = "</span>";
//		Para cada tipo de recurso, escribimos una entrada "strong" con el texto tal cual.
		//for (int i = 0; i < tipoRecurso.length; i++) {
			//cuerpo+="<strong>"+tipoRecurso[i]+"</strong><br />";
		//}
		
		cuerpo=concatenaArrayStringMostrar(tipoRecurso);
		
		return cabecera + cuerpo + cola;
//		return cuerpo + cola;
	}
	
	
	public String concatenaArrayString(String[] tipoRecurso)
	{
		//String salida = "<span>"+"<strong>"+"";
		String salida = "<span>"+"";
		String areaCurricular = "";
		boolean primerLong=true;		
		
		if (tipoRecurso!=null && tipoRecurso.length >0) {
	    	for (int i = 0; i < tipoRecurso.length; i++) {
	    		String[] areas = tipoRecurso[i].split(SLIDE);
	    		primerLong=true;
	    		for (int k = 0; k < areas.length; k++) {
		    		String[] array = areas[k].split(SPACE);
		    		
		    		for(int j = 0; j < array.length; j++) {
		    			int primero=j;
		    			
		    			if ((areaCurricular + array[j]+ SPACE).length()>LONGITUD_TIPO_RECURSO){
		    				if (primerLong && i!=0){
		    					//salida +="<strong>"+ areaCurricular + "<BR>";
		    					salida +=areaCurricular + "<BR>";
		    					areaCurricular=array[j] +SPACE;
		    				}else{
		    					salida += areaCurricular + "<BR>";
		    					areaCurricular=array[j] +SPACE;
		    				}
		    				primerLong=false;
		    				
		    			}else{
		    				if (primero==0){
		    					areaCurricular    = areaCurricular + array [j];
		    				}else{
		    					areaCurricular = areaCurricular + SPACE + array [j];
		    				}
		    			}		    			
		    		}
		    		if (k != areas.length-1){
		    			if ((areaCurricular +SLIDE).length() >LONGITUD_TIPO_RECURSO){
		    				salida += areaCurricular + "<BR>";
		    				areaCurricular=SLIDE;
		    			}else{
		    				areaCurricular +=SLIDE;
		    			}
		    		}
	    		}
	    		if (i != tipoRecurso.length-1){
	    			if (i!=0 && primerLong){
	    				//salida +="<strong>"+ areaCurricular + "<BR>";
	    				salida +=areaCurricular + "<BR>";
	    				areaCurricular="";
	    			}else{
	    				salida += areaCurricular + "<BR>";
	    				areaCurricular="";
	    			}
	    		}
	    	}
	    	if (!areaCurricular.equals("")){
	    		if (tipoRecurso.length!=1 && primerLong){
	    			//salida += "<strong>"+areaCurricular;
	    			salida +=areaCurricular;
	    		}else{
	    			salida += areaCurricular;
	    		}
	    	}
		}
		if (salida.equals("<strong>"+"")){
			salida="";
		}
		return salida+"</span >";
	}
	
	
	private String concatenaArrayStringMostrar(String[] tipoRecurso)
	{
		StringBuilder salida = new StringBuilder("");
				
		if (tipoRecurso!=null && tipoRecurso.length >0) {
	    	for (int i = 0; i<2 && i< tipoRecurso.length; i++) {
	    		if (i!=0){
	    			salida.append(COMA+SPACE+ tipoRecurso[i]);
	    			if (i==1 && i!=tipoRecurso.length-1)
	    				salida.append(PUNTOS_SUSPENSIVOS);
	    			}else {
	    				salida.append(tipoRecurso[i]);
	    				if (i==1 && i!=tipoRecurso.length-1)
		    				salida.append(PUNTOS_SUSPENSIVOS);
	    				}
	    		if (i==tipoRecurso.length-1){
	    			salida.append(PUNTO + SPACE);
	    		}
	    	}
		}
		
		return salida.toString();
	}
	
}
