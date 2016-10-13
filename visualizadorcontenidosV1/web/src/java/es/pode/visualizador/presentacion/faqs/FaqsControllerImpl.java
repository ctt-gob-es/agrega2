/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.faqs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;


import es.pode.contenidos.negocio.faqs.servicio.CategoriaTraducidaVO;
import es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.visualizador.comun.ListaOrdenadaFaq;
/**
 * @see es.pode.visualizador.presentacion.faqs.FaqsController
 */
public class FaqsControllerImpl extends FaqsController
{
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(FaqsControllerImpl.class);
	// TODO esto debe desparecer para i18nrse
	private final String todas = "TODAS";

	/**
     * @see es.pode.visualizador.presentacion.faqs.FaqsController#cargaFaqs(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.faqs.CargaFaqsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFaqs(ActionMapping mapping, es.pode.visualizador.presentacion.faqs.CargaFaqsForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// la primera categoria de la collection es todas y el resto son las categoriasVO

    	try
    	{   		
    		
		logger.debug("Recuperando faqs en idioma: " + request.getLocale().getLanguage() + " y categoria: "
				+ form.getCategoriaSeleccionada() + ", primero las categorias.");
		String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		FaqTraducidaVO[] faqsArray;
		ArrayList primeroDeCat = new ArrayList();
		CategoriaTraducidaVO todas = new CategoriaTraducidaVO();
		todas.setId(new Long(-1));
		todas.setNombre(this.todas);
		primeroDeCat.add(todas);
		primeroDeCat.add(this.getSrvFaqService().obtenCategoriasFaqs(idioma));
		//Esto queda muy poco vistoso pero es bastante eficiente, el primer elemento sera todos y el siguiente 
		// la colecci�n en la que iterar
		form.setCategorias(primeroDeCat);

		logger.debug("Despues las faqs");
		//			si no hay categoria seleccionada escogemos todas, que debiera ser el caso inicial
		if (form.getCategoriaSeleccionada() == null || form.getCategoriaSeleccionada().equals(new Long(-1))) {
			form.setCategoriaSeleccionada(new Long(-1));
			faqsArray = this.getSrvFaqService().obtenerFaqsPorIdioma(idioma);
		} else
			faqsArray = this.getSrvFaqService().obtenFaqsPorIdiomaCategoria(idioma,
					form.getCategoriaSeleccionada());
		
		// Si no existen faqs en ese idioma se muestran las de castellano por defecto
		if (faqsArray==null || faqsArray.length==0)
		{
			logger.warn("No hay faqs en el idioma :" + idioma +". Se obtendr�n las faqs en castellano");
			idioma ="es";
    		// Cargamos las faqs en castellano 
    		primeroDeCat.add(this.getSrvFaqService().obtenCategoriasFaqs(idioma));
    		form.setCategorias(primeroDeCat);

    		
    		//			si no hay categoria seleccionada escogemos todas, que debiera ser el caso inicial
    		if (form.getCategoriaSeleccionada() == null || form.getCategoriaSeleccionada().equals(new Long(-1))) {
    			form.setCategoriaSeleccionada(new Long(-1));
    			faqsArray = this.getSrvFaqService().obtenerFaqsPorIdioma(idioma);
    		}
    		else{
    			faqsArray = this.getSrvFaqService().obtenFaqsPorIdiomaCategoria(idioma,
    					form.getCategoriaSeleccionada());
    		}
    	}
		
		Collections.sort(Arrays.asList(faqsArray), new ListaOrdenadaFaq());
		
		for (int h = 0; h < faqsArray.length; h++) {
			//sustituimos los retorno de carro por <br/> en respuesta
			faqsArray[h].setRespuesta(faqsArray[h].getRespuesta().replaceAll("\n\r", "<br/>"));
			faqsArray[h].setRespuesta(faqsArray[h].getRespuesta().replaceAll("\r\n", "<br/>"));
			faqsArray[h].setRespuesta(faqsArray[h].getRespuesta().replaceAll("\n", "<br/>"));
			faqsArray[h].setRespuesta(faqsArray[h].getRespuesta().replaceAll("\r", "<br/>"));

			//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("@", "&#64"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("\\\"", "&#34"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll(":", "&#58"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("%", "&#37"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("\\+", "&#43"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("-", "&#45"));
			faqsArray[h].setPregunta(faqsArray[h].getPregunta().replaceAll("'", "&#39"));
		}

		// Poblando la tabla con el listado de faqs    	
		form.setFaqs(faqsArray);
    	}
    	catch (Exception ex)
    	{
    		logger.error("Excepcion no controlada cargando los faqs: " + ex );
    		throw(ex);
    	}

	}

   

}