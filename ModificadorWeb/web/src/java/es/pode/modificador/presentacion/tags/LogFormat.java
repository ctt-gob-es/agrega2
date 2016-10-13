package es.pode.modificador.presentacion.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class LogFormat extends TagSupport {
	private String input = null;
	private static final Logger logger = Logger.getLogger(LogFormat.class);
	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return SKIP_BODY;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		// Reemplazo de retorno de carro
		String result = input.replaceAll("\\\n|\\\r\\\n", "<br/>");
		JspWriter out = pageContext.getOut();
		try {
			out.write(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			if(logger.isDebugEnabled()) logger.debug(e);
		}
		return SKIP_BODY;
	}
	
	
}
