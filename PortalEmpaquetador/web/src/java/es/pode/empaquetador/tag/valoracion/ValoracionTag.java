package es.pode.empaquetador.tag.valoracion;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ValoracionTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int estrellasMaximas=5;
	private String valoracion=null;
	private String pagina=null;
	private String mensaje=null;
	
	private static Logger logger = Logger.getLogger(ValoracionTag.class);

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
	    		JspWriter out = pageContext.getOut();
	    		
	    		if(valoracion==null){  
	    			valoracion = "0";
	    			//Pinto estrellas vacias
	    			for(int i=0; i<estrellasMaximas; i++){
	    				out.println("<li><img src='/static/img/star_03.gif' alt='estrella 05' /></li>");
	    			}
	    		}
	    		else{
	    			//Dos posibles casos: Que se pinte en la pantalla de resultados o en la pantalla de mostrar detalle ode
	    			if(pagina.equals("detallar")){	    				
//	    				Pantalla de detallar
	    				out.println("<div class=\"valoracion\" >");
	    				String fichero="application-resources";
						String literal=this.getResource(mensaje, fichero);
						
	    				out.println("<span >" + literal + ":&nbsp;</span>");
	    				out.println("<ul id=\"res_val\">");	
	    				//Metodo que pinta las estrellas
	    				pintarEstrellas(valoracion);	
	    				out.println("</ul>");
	    				out.println("</div>");
	    				
	    			}else{
	    				//Pantalla de resultados
	    				out.println("<div class=\"valoracion\">");
	    				out.println("<ul >");
	    				//Metodo que pinta las estrellas
	    				pintarEstrellas(valoracion);		    			
	    				out.println("</ul >");
	    				out.println("</div>");
	    			}
	    			}//else
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			logger.error(e);
	    		}
	    		return SKIP_BODY;	
	    }
	    
	    private void pintarEstrellas(String valoracion)throws JspException{
	    	try{
	    		JspWriter out = pageContext.getOut();
	    		int contador=0;
	    		//    		Paso de la variable valoracion que es un string a float
	    		Float valoracionFloat=Float.valueOf(valoracion);
	    		int valoracionInt=valoracionFloat.intValue();
	    		Float valFloat=new Float(valoracionInt);
	    		for (int i= 0; i<valoracionInt;i++)
	    		{
	    			out.println("<li><img src='/static/img/star_01.gif' alt='estrella 01' /></li>");
					contador++;
	    		}
	    		if (valoracionFloat.floatValue()>valFloat.floatValue())
	    		{
	    			out.println("<li><img src='/static/img/star_02.gif' alt='estrella 04' /></li>");
					contador++;
	    		}
	    		if(contador != estrellasMaximas)
	    		{
	    			for(int j= contador; j<estrellasMaximas;j++)
	    			{
	    				out.println("<li><img src='/static/img/star_03.gif' alt='estrella 05' /></li>");
					
	    			}
	
	    		}
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			logger.error(e);
	    		}
	    	}
	   
	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
		
		public static String getResource (String key, String fichero)
	    {
            return getResourceByBundle(key,fichero);

	    }



	    private static String getResourceByBundle(String key,String baseName){
            String recurso = "";
            try{
               ResourceBundle theResourceBundle = ResourceBundle.getBundle(baseName);
               if (theResourceBundle!=null){
                           recurso = theResourceBundle.getString(key);
               }
            }catch (MissingResourceException mre){
            	recurso = key;
            }
            return recurso;

	    }
}