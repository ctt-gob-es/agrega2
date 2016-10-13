package es.agrega.soporte.tag.googleAnalytic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;



public class GoogleAnalyticTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public int doStartTag() throws JspException {
	      try {
	    	  JspWriter out = pageContext.getOut();
	    	  Integer googleAnalytic = ConfiguracionPortalImpl.getInstance().getGoogleAnalytic();
	    	  
	    	  if(googleAnalytic.longValue()==1)
	    	  {
	    		  String codGa=ConfiguracionPortalImpl.getInstance().getCodGa();
	    		  out.println(codGa);
	    	  }
	    	
	  	    	
	      } catch (Exception ex) {
	         throw new JspTagException("GoogleAnalyticTag: " + 
	            ex.getMessage());
	      }
	      return SKIP_BODY;
	   }
	   
 public int doEndTag() throws JspException{
		return SKIP_BODY; 
	}


}
