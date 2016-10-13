package es.pode.empaquetador.tag.elementos;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.RecursoVO;
import es.pode.soporte.constantes.ConstantesAgrega;

public class PrincipalTag extends TagSupport {

	private GrupoVO grupo = null;
	private String url = null;
	private String i18nKey = null;
	private RecursoVO[] recursos = null;
	private static Logger logger = Logger.getLogger(PrincipalTag.class);
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return SKIP_BODY;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String idRef = grupo.getElementoReferenciado();
		JspWriter out = pageContext.getOut();
		String begin = "<td valign=\"top\" class=\"ejec\"><span class=\"oculto\">-</span>";
		String end = "</td>";
		String outText = begin + end;
		try {
			logger.debug("idRef es " + idRef);
			if(idRef!=null)  {
				RecursoVO recurso = null;
				for(int i=0;i<recursos.length && recurso==null;i++) {
					if(idRef.equals(recursos[i].getIdentifier())) {
						recurso=recursos[i];
					}
				}
				logger.debug("Recurso es " + recurso);
				if(recurso!=null)  {
					logger.debug("recurso fileList es " + recurso.getFileList());
					if(recurso.getFileList()!=null && recurso.getFileList().length>1) {
						Locale locale = (Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
						ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
						String linkText = i18n.getString(i18nKey);
						outText = begin + "<a href=\"" + url + "?identifier=" + grupo.getIdentifier() +"\">" + linkText + "</a>" + end;
					}
				}
			}
			out.write(outText);
		} catch (IOException e) {
			logger.error("Error dibujando PrincipalTag",e);
			throw new JspException("Error dibujando PrincipalTag",e);
		} catch (Exception e) {
			logger.error("Error dibujando PrincipalTag",e);
			throw new JspException("Error dibujando PrincipalTag",e);
		}
		return super.SKIP_BODY;
	}

	/**
	 * @return the action
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param action the action to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the grupo
	 */
	public GrupoVO getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(GrupoVO grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the i18nKey
	 */
	public String getI18nKey() {
		return i18nKey;
	}

	/**
	 * @param key the i18nKey to set
	 */
	public void setI18nKey(String key) {
		i18nKey = key;
	}

	/**
	 * @return the recursos
	 */
	public RecursoVO[] getRecursos() {
		return recursos;
	}

	/**
	 * @param recursos the recursos to set
	 */
	public void setRecursos(RecursoVO[] recursos) {
		this.recursos = recursos;
	}

}
