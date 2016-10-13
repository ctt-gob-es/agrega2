// license-header java merge-point
package es.pode.administracion.presentacion.faqs.faqs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.faqs.faqs.FaqsController
 */
public class FaqsControllerImpl extends FaqsController
{

	private static final String todas = "TODAS";
	private static Logger logger = Logger.getLogger(FaqsControllerImpl.class);

	private static final String VERFAQVO = "verFaq";
	private static final String CATEGORIAMODVO = "categoriaModificar";
	private static final String FAQS = "faqs";
	private final static String CATEGORIAVO = "categoriavo";	
	
    /**
     * @see es.pode.administracion.presentacion.faqs.faqs.FaqsController#cargarFaqs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.faqs.faqs.CargarFaqsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarFaqs(ActionMapping mapping, es.pode.administracion.presentacion.faqs.faqs.CargarFaqsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	 la primera categoria de la collection es todas y el resto son las categoriasVO
    	
    	    	try
    	    	{    
    	    		//Se elimina de la request los valores si estuvieran llenos
    	    		request.getSession().setAttribute(VERFAQVO, null);
    	    		request.getSession().setAttribute(CATEGORIAMODVO, null);
    	    		request.getSession().setAttribute(FAQS, null);
    	    		request.getSession().setAttribute(CATEGORIAVO, null);
    				
    				logger.debug("Recuperando faqs con idioma: "+form.getIdiomaSeleccionado()+", primero los idiomas.");
    				/**
    	    		 * **************************************************************************************************************************************
    	    		 * ****************************************** SE OBTIENEN LOS IDIOMAS TRADUCIBLES *******************************************************
    	    		 * **************************************************************************************************************************************
    	    		 * */
    				if (logger.isDebugEnabled()) logger.debug("Obtenemos los idiomas traducibles");
    				
    				String[] idiomasPlataforma = I18n.getInstance().obtenerIdiomasPlataforma();
    				if (logger.isDebugEnabled()) logger.debug("Hay ["+idiomasPlataforma.length+"] en la plataforma");		
    				
    				String idiomaLogado = LdapUserDetailsUtils.getIdioma();
    				String idiomaPrioritario = I18n.getInstance().obtenerIdiomaDefectoPlataforma();		
    				String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
    				if (logger.isDebugEnabled()) logger.debug("El idioma del usuario es ["+idiomaLogado+"], idioma prioritario de la plataforma es ["+idiomaPrioritario+"] y el secundario es ["+idiomaSecundario+"]");
  		
    				NoticiasControllerImpl noticiasController = new NoticiasControllerImpl();	
    				
    				//Se recuperan las categorias de las faqs de la base de datos
//    				CategoriaTraducidaVO[] categorias = this.getSrvFaqService().obtenerCategoriasTraducidas(noticiasController.
//    						devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));    				
    				
    				ArrayList<Object> primeroDeIdiomas = new ArrayList<Object>();
    				primeroDeIdiomas.add(FaqsControllerImpl.todas);    				
    				primeroDeIdiomas.add(idiomasPlataforma);
    				//Esto queda muy poco vistoso pero es bastante eficiente, el primer elemento sera todos y el siguiente 
    				// la colección en la que iterar
//    				form.setCategorias(primeroDeCat);
    				form.setIdiomas(primeroDeIdiomas);
    				
    				FaqTraducidaVO[] faqs = null;
    				logger.debug("Despues las faqs");
    				//			si no hay categoria seleccionada escogemos todas, que debiera ser el caso inicial    				
    				
    				if (form.getIdiomaSeleccionado() == null || FaqsControllerImpl.todas.equals(form.getIdiomaSeleccionado())) {
    					form.setIdiomaSeleccionado(FaqsControllerImpl.todas);
    					faqs = this.getSrvFaqService().obtenerFaqsTraducidas(noticiasController.
    						devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));     					
    				} 
    				else
    					faqs = this.getSrvFaqService().obtenerFaqsPorIdioma(form.getIdiomaSeleccionado());
    					
    		
    				for (int h = 0; h < faqs.length; h++) {
    					logger.debug("Iteración h="+h);
    					logger.debug("sustituimos los retorno de carro por <br/> en respuesta");
    					logger.debug("Faq "+h+" es : Posicion ="+faqs[h].getPosicion());
    					logger.debug("Pregunta ="+faqs[h].getPregunta());
    					logger.debug("IdCategoria ="+faqs[h].getIdCategoria());
    					logger.debug("Respuesta "+h+" es "+faqs[h].getRespuesta());
    					//sustituimos los retorno de carro por <br/> en respuesta
    					faqs[h].setRespuesta(faqs[h].getRespuesta().replaceAll("\n\r", "<br/>"));
    					faqs[h].setRespuesta(faqs[h].getRespuesta().replaceAll("\r\n", "<br/>"));
    					faqs[h].setRespuesta(faqs[h].getRespuesta().replaceAll("\n", "<br/>"));
    					faqs[h].setRespuesta(faqs[h].getRespuesta().replaceAll("\r", "<br/>"));
    		
    					logger.debug("sustituimos @,:,%,+,-,' por su codigo correspondiente en ASCII");
    					//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("@", "&#64"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("\\\"", "&#34"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll(":", "&#58"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("%", "&#37"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("\\+", "&#43"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("-", "&#45"));
    					faqs[h].setPregunta(faqs[h].getPregunta().replaceAll("'", "&#39"));
    				}
    		
    				// Poblando la tabla con el listado de faqs    	
    				logger.debug("Poblando la tabla con el listado de faqs");
    				form.setFaqsAsArray(faqs);
    	    	}
    	    	catch (Exception ex)
    	    	{
    	    		logger.error("Excepcion no controlada cargando los faqs: ",ex );
    	    		throw(ex);
    	    	}
    					
    }     

	public void getIds(ActionMapping mapping, GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		/**
		 * **************************************************************************************************************************************
		 * **************************** SE RECUPERAN LOS IDENTIFICADORES DE LA FAQ A ELIMINAR *********************************************
		 * **************************************************************************************************************************************
		 * */
		List<?> lista = ((FaqsEliminarFormImpl) form).getIdRowSelection();			
		if (lista == null)
		{
			logger.error("Debe seleccionar al menos una faq a eliminar");
			throw new ValidatorException("{errors.borrarFaq.idNulo}");
		}
		form.setIds(lista);
	}

}
