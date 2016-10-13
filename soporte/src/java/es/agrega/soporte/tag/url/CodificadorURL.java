package es.agrega.soporte.tag.url;

import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class CodificadorURL extends TagSupport {

	private String url;
	private static Logger logger = Logger.getLogger(CodificadorURL.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2210056531767189553L;

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		String encoding = super.pageContext.getResponse().getCharacterEncoding();
		String finalUrl = url;
		if(encoding!=null && url!=null) {
			try {
				String encodedSlash = URLEncoder.encode("/", encoding);
				String encodedColon = URLEncoder.encode(":", encoding);
				finalUrl = URLEncoder.encode(url, encoding);
				finalUrl = finalUrl.replaceAll(encodedSlash, "/");
				finalUrl = finalUrl.replaceAll("\\+", "%20");
				finalUrl = finalUrl.replaceAll(encodedColon, ":");
				logger.debug("Url " + url + " codificada como " + finalUrl);
			} catch (Exception e) {
				logger.error("No se pudo codificar la url " + url + ". " + e.getLocalizedMessage()+" - ",e);
			}
		}
		JspWriter out = pageContext.getOut();
		try {
			out.write(finalUrl);
		} catch (Exception e) {
			logger.error("Error de escritura de JSP",e);
		}
		return SKIP_BODY;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
