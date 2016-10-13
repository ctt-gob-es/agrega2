package es.pode.administracion.tag.flash;

import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;

public class FlashTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(FlashTag.class);
	private static final String BARRA = "/";
	private static final String HTTP = "http";
	private static final String DOSPUNTOS = ":";
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
	    		JspWriter out = pageContext.getOut();
	    		ServletRequest request = pageContext.getRequest();
	    		
	    		String idRss=ConfiguracionPortalImpl.getInstance().getIdRssGaleriaPortada();
	    		Integer numOdes=ConfiguracionPortalImpl.getInstance().getNumOdesGaleria();

	    		Locale locale = null;
					locale = devuelveLocale();
					
				String idioma=locale.getLanguage();
				
				AgregaProperties properties = AgregaPropertiesImpl.getInstance();	  
				
				String host=properties.getProperty(AgregaProperties.HOST);
				String subdominio=properties.getProperty(AgregaProperties.SUBDOMINIO);
				
			
				if(!idRss.equalsIgnoreCase("0.0"))
				{
				out.println("<script type=\"text/javascript\">");
				String URL=HTTP+DOSPUNTOS+BARRA+BARRA+host+subdominio+"/visualizadorcontenidos/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do";
				String scrollerAgrega=subdominio+"/static/flash/scrollerAgrega/scrollerAgrega.swf";
				out.println("	swfobject.embedSWF(\""+scrollerAgrega+"\", \"el_flash\", \"660\", \"185\", \"9.0.0\",false,false,{wmode:\"transparent\",base:\".\",allownetworking:\"all\",allowscriptaccess:\"always\",flashvars: \"urlFeed="+URL+"%3FidFeed%3D"+idRss+"%26feedNum="+numOdes+"\"},false);");
				out.println("</script>");
				out.println("<div id=\"el_flash\"></div>");
				
				}
	    	}
	    	catch (Exception e){
				 logger.error("Error",e);
			}
			
			return SKIP_BODY;	
	    }
	    
	    private Locale devuelveLocale() throws Exception
		{
			Locale locale=null;
			if (pageContext.getRequest() instanceof HttpServletRequest) {
				locale = (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
				
			}
			else{ 	    					
				try {
					locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
				} catch (Exception e) {	
					logger.error("Error recuperando el locale de la request",e);					
				}
			}
			return locale;
		}
	
	
	

}
