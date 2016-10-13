/**
 * 
 */
package es.pode.buscador.tag.checkbox;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * @author srecio y pcanales
 * 
 */
public class CheckBoxTag extends TagSupport {
	private static Logger logger = Logger.getLogger(CheckBoxTag.class);


	private String name = null;	
	private String id = null;	
	private String url = null;	
	private String titulo = null;	
	private String administrador = null;	
	private String numeroODE = null;
	private String nodoODE = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		try {					
			out.write("<table width=\"6%\" border=\"1\">" );
				out.write("<tr>" );
					//Escribo el número del ode
					out.write("<td class=\"numeracion\"><strong>" + numeroODE + "</strong></td>" );
					//Escribo el checkbox si el usuario es administrador
					if(administrador.equals("true") && (nodoODE==null || nodoODE.equals(""))){
						out.write("<td class=\"numeracion\"><strong><input type=\"checkbox\" id=\"" + id + "\" "	+ " name=\"" + name + "\" value=\"" + id + "\"/></strong></td>");
					}
				out.write("</tr>" );
			out.write("</table>" );
		} catch (Exception e) {
			logger.error("CheckBoxTag - doStartTag():Error al dibujar checkbox del elemento", e);
			throw new JspException("CheckBoxTag - doStartTag(): Error al dibujar checkbox del elemento", e);
		}
		return SKIP_BODY;	
	}

	public int doEndTag() throws JspException {
		return SKIP_BODY;
	}	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * @return the administrador
	 */
	public String getAdministrador() {
		return administrador;
	}
	/**
	 * @param administrador
	 *            the administrador to set
	 */
	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}
	public String getNumeroODE() {
		return numeroODE;
	}

	public void setNumeroODE(String numeroODE) {
		this.numeroODE = numeroODE;
	}
	
	public String getNodoODE() {
		return nodoODE;
	}

	public void setNodoODE(String nodoODE) {
		this.nodoODE = nodoODE;
	}
}
