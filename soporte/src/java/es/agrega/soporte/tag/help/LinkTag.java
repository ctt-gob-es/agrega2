package es.agrega.soporte.tag.help;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.agrega.soporte.filter.HelpFilter;
import es.agrega.soporte.help.HelpLink;
import es.pode.soporte.constantes.ConstantesAgrega;


public class LinkTag extends TagSupport 
{
	
	private static final String OFFLINE_HELP_URL="/help/Manual_de_Usuario.pdf";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title = null;
	private String id = null;
	private String url = null;
	private String i18nMessage = null;
	private String target = null;
	private String attrProperty = null;
	
	public static String PROPERTIES_FILE_NAME ="application-resources";
	
/**	
 * <a href="/${initParam.url_ayuda }" title="Ayuda" id="ayuda"
		target="blank"><bean:message key="cabecera.ayuda" /></a>
**/
	public int doStartTag() throws JspException {
	      try {
	    	  JspWriter out = pageContext.getOut();
	    	  out.print("<a href='" + this.getUrl() + "'");
	    	  if (this.isWritable(this.getTitle()))
	    		  out.print(" title ='" + this.getTitle()+ "'");
	    	  if (this.isWritable(this.getId()))
	    		  out.print(" id='" + this.getId() + "'");
	    	  if (this.isWritable(this.target))
	    		  out.print(" target ='"+ this.getTarget() +"'>");
	    	  out.print(this.getI18nMessage());
	      } catch (Exception ex) {
	         throw new JspTagException("LinkTag: " + 
	            ex.getMessage());
	      }
	      return SKIP_BODY;
	   }
	
	   public int doEndTag() throws JspException{
		      try {
		    	  JspWriter out = pageContext.getOut();
		    	  out.println("</a>");
		      } catch (Exception ex) {
		         throw new JspTagException("LinkTag: " + 
		            ex.getMessage());
		      }
	      return EVAL_PAGE;
	   }

	public String getUrl() 
	{
		if(DecisorOffline.esOffline()) {
			return OFFLINE_HELP_URL;
		}
		if (this.getAttrProperty() != null && !this.getAttrProperty().equals(""))
			this.setUrl(HelpLink.getInstance().getRequestAttribute(pageContext.getRequest(), this.getAttrProperty()));
		else
			this.setUrl(HelpLink.getInstance().getRequestAttribute(pageContext.getRequest(), HelpFilter.HELP_URL_KEY));
		return url;
	}

	public void setUrl(String sLink) 
	{
		if (sLink != null && !sLink.equals(""))
		{
			// Si el link es una URL completa tiene http:// se deja como est�.
			String regexURL = "^(http|https|ftp):\\/\\/.*";
			if (sLink.matches(regexURL))
					this.url = sLink;
			else if (sLink.indexOf('/') != 0)
				this.url = "/"+sLink;
			else 
				this.url = sLink;
		}
	}

	public String getAttrProperty() {
		return attrProperty;
	}

	public void setAttrProperty(String attrProperty) {
		this.attrProperty = attrProperty;
	}

	public String getI18nMessage() {
		if (this.i18nMessage != null && !this.i18nMessage.equals(""))
		{
			ResourceBundle resource = ResourceBundle.getBundle(LinkTag.PROPERTIES_FILE_NAME, (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			this.i18nMessage = resource.getString(this.i18nMessage);
		}
		return this.i18nMessage;
	}

	public void setI18nMessage(String key) {
		i18nMessage = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private boolean isWritable(Object obj)
	{
		return (obj != null && !obj.equals(""));
	}

}
