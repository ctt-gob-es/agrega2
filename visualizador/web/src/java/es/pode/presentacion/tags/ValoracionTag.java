package es.pode.presentacion.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

public class ValoracionTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int estrellasMaximas=5;	
	
	private static Logger logger = Logger.getLogger(ValoracionTag.class);	
	
	private final static String FICHERO = "application-resources";
	
	private boolean valorado = false;
	private String action;
	private String retorno;
	private String idioma;
	private Locale locale;
	private String identificador;
	
	private String valoracion;
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
	    		//TODO
	    		logger.debug("Retorno vale "+retorno);
	    		
	    		JspWriter out = pageContext.getOut();
	    		StringBuilder sb= new StringBuilder();
	    		
	    		
	    		if(valorado){
		    		sb.append("<li class='valorado' >");
	    			sb.append("<span id='valor'>").append(getResource("menu.valorar.valoracion")).append(":&nbsp;</span>");
	    			sb.append("		<ul id='valorado_estrellas'>");
	    			double valorDouble = Double.parseDouble(valoracion);
	    			int valorInt= (int) valorDouble ;
	    			double decimal =  valorDouble - valorInt;
	    			
	    			for(int i=0; i< estrellasMaximas ; i++)
	    			{
	    				if(valorInt>i) //pinto estrella completa
	    				{
	    					sb.append("<li><span title='").append(valoracion).append("'>").append(i).append("</span></li>");
	    				}else if (valorInt+1 <= i ) // pinto estrella vacia
	    				{
	    					sb.append("<li><span class='est_blanca' title='").append(valoracion).append("'>").append(i).append("</span></li>");
	    				}else
	    				{
	    					if(decimal>=0.0 && decimal<=0.3)
		    					sb.append("<li><span class='est_blanca' title='").append(valoracion).append("'>").append(i).append("</span></li>");
	    					else if(decimal>0.3 && decimal<=0.8)
		    					sb.append("<li><span class='est_media' title='").append(valoracion).append("'>").append(i).append("</span></li>");
	    					else
		    					sb.append("<li><span title='").append(valoracion).append("'>").append(i).append("</span></li>");
	    				}
	    			}
	    			sb.append("		</ul>");
		    		sb.append("</li>");
	    		}else{
		    		sb.append("<li class='valorar' >");
	    			sb.append("<span>").append(getResource("menu.valorar")).append(": </span>");
	    			sb.append("		<ul class='valorar-estrellas'>");
   					sb.append("			<li style='width: 60%;' class='rating-actual'>" + getResource("menu.valorar.title.0")  + "</li>");
					sb.append("			<li><a href='" + action + "?valoracion=1&amp;retorno=" + retorno + "&amp;identificador="+identificador+"' title='" + getResource("menu.valorar.title.1") + "' class='uno-estrella'>1</a></li>");
	    			sb.append("			<li><a href='" + action + "?valoracion=2&amp;retorno=" + retorno + "&amp;identificador="+identificador+ "' title='" + getResource("menu.valorar.title.2") + "' class='dos-estrellas'>2</a></li>");
	    			sb.append("			<li><a href='" + action + "?valoracion=3&amp;retorno=" + retorno + "&amp;identificador="+identificador+ "' title='" + getResource("menu.valorar.title.3") + "' class='tres-estrellas'>3</a></li>");
	    			sb.append("			<li><a href='" + action + "?valoracion=4&amp;retorno=" + retorno + "&amp;identificador="+identificador+ "' title='" + getResource("menu.valorar.title.4") + "' class='cuatro-estrellas'>4</a></li>");
	    			sb.append("			<li><a href='" + action + "?valoracion=5&amp;retorno=" + retorno + "&amp;identificador="+identificador+ "' title='" + getResource("menu.valorar.title.5") + "' class='cinco-estrellas'>5</a></li>");
	    			sb.append("		</ul>");
		    		sb.append("</li>");
	    		}
	    		
	    		out.println(sb.toString());
	    		
	    		

	    		} catch (IOException e) {
	    			logger.error("ValoracionTag Error pintando los valores de las estrellas de valoracion",e);	    			
	    		} catch (Exception e) {
	    			logger.error("ValoracionTag Error pintando los valores de las estrellas de valoracion",e);	    			
	    		}
	    		return SKIP_BODY;	
	    }
	    

	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
		
		private String getResource(String key ){
	        String recurso = "";
	        try{
	        	ResourceBundle theResourceBundle = getResource();
	        	if (theResourceBundle!=null){
	               recurso = theResourceBundle.getString(key);
	           }
	        }catch (MissingResourceException mre){
	        	recurso = key;
	        }
	        return recurso;
	    }
		
		
		private ResourceBundle getResource(){
	        try{
	        	return ResourceBundle.getBundle(FICHERO ,locale);
	            
	        }catch (MissingResourceException mre){
        		locale = new Locale("","");
        		return ResourceBundle.getBundle(FICHERO ,locale);
	        }catch (Exception e){    			
	    		logger.error("ValoracionTag - getResource: Error obteniendo traducción",e);
	    		return null;
    		}
	    }
		
		
		public boolean isValorado() {
			return valorado;
		}

		public void setValorado(boolean valorado) {
			this.valorado = valorado;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getRetorno() {
			return retorno;
		}

		public void setRetorno(String retorno) {
			this.retorno = retorno;
		}

		public String getIdioma() {
			return idioma;
		}

		public void setIdioma(String idioma) {
			this.idioma = idioma;
			locale = new Locale(idioma);
		}

		public String getValoracion() {
			return valoracion;
		}

		public void setValoracion(String valoracion) {
			this.valoracion = valoracion;
		}
		
		public String getIdentificador() {
			return identificador;
		}

		public void setIdentificador(String identificador) {
			this.identificador = identificador;
		}
}