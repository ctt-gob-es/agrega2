/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.modificador.presentacion.tags;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import es.pode.modificador.negocio.servicio.CambioTypes;
import es.pode.modificador.negocio.servicio.Change;
import es.pode.soporte.constantes.ConstantesAgrega;

public class Cambios extends TagSupport {
	private Change cambio = null;

	private Logger logger = Logger.getLogger(Cambios.class);
	/**
	 * @return the cambio
	 */
	public Change getCambio() {
		return cambio;
	}

	/**
	 * @param cambio
	 *            the cambio to set
	 */
	public void setCambio(Change cambio) {
		this.cambio = cambio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		MessageResources resources = MessageResources
				.getMessageResources("application-resources");
		
		StringBuffer sb = new StringBuffer();
		//TODO Tiene pinta de que con cariño se puede simplificar (y dinamizar!) esto 
		if (CambioTypes.addition.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.addition")).append(" ( ");
			if("otro".equals(cambio.getTermType().getValue())) {
				sb.append(cambio.getLomTerm());
			} else if("taxonomia".equals(cambio.getTermType().getValue())) {
				sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.arbol.curricular"));
			}
			sb.append(" ) ");
		} else if (CambioTypes.removal.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.removal"))
					.append(" ( ").append(
							resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.termino"))
					.append(cambio.getLomTerm());
			if (cambio.getValue() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.valor")).append(
						cambio.getValue());
			if (cambio.getLanguage() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.idioma")).append(
						cambio.getLanguage());
			sb.append(" ) ");
		} else if (CambioTypes.check.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.check")).append(" ( ")
					.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.termino"))
					.append(cambio.getLomTerm());
			if (cambio.getValue() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.valor")).append(
						cambio.getValue());
			if (cambio.getLanguage() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.idioma")).append(
						cambio.getLanguage());
			sb.append(" ) ");
		} else if (CambioTypes.modification.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.modification")).append(
					" ( ").append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.termino"))
					.append(cambio.getLomTerm());
			if (cambio.getValue() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.valor")).append(
						cambio.getValue());
			if (cambio.getLanguage() != null)
				sb.append(", ").append(
						resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.idioma")).append(
						cambio.getLanguage());
			sb.append(", ").append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.nuevo"))
					.append(cambio.getNewValue());
			sb.append(" ) ");
		} else if (CambioTypes.validation.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.validation"));
		}else if (CambioTypes.export.equals(cambio.getType())) {
			String texto="cambio."+cambio.getExportFormat().toString();
			logger.debug("El texto obtenidos es:"+texto);
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.transformacion")+" "+(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),texto)));
			logger.debug("El mensaje creado es:"+sb.toString());
		}else if (CambioTypes.transformation.equals(cambio.getType())) {
			String texto="cambio." + cambio.getExportFormat().toString();
			logger.debug("El texto obtenidos es:"+texto);
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.transformacion")+" "+(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),texto)));
			logger.debug("El mensaje creado es:"+sb.toString());
		}else if (CambioTypes.metadata.equals(cambio.getType())) {
			sb.append(resources.getMessage((Locale)pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"cambios.tag.metadata"));
		}

		try {
			JspWriter out = pageContext.getOut();
			out.write(sb.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY;
	}
}
