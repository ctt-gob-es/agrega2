// license-header java merge-point
package es.pode.administracion.presentacion.faqs.verFaq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO;
import es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO;
import es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO;
import es.pode.contenidos.negocio.faqs.servicio.FaqVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.administracion.presentacion.faqs.verFaq.VerFaqController
 */
public class VerFaqControllerImpl extends VerFaqController
{

	private static Logger logger = Logger.getLogger(VerFaqControllerImpl.class);

	private final static String VERFAQVO = "verFaqvo";		

    /**
     * @see es.pode.administracion.presentacion.faqs.verFaq.VerFaqController#obtenerFaqs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.faqs.verFaq.ObtenerFaqsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerFaqs(ActionMapping mapping, es.pode.administracion.presentacion.faqs.verFaq.ObtenerFaqsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
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
		
			// Rellena el combo de categorias			
			NoticiasControllerImpl noticiasController = new NoticiasControllerImpl();	
			Collection categorias = Arrays.asList(this.getSrvFaqService().obtenerCategoriasTraducidas(noticiasController.
					devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario)));
			form.setCategoriaBackingList(categorias, "id", "nombre");	
	    	
	//      Se obtiene los datos de la faq
	    	if (logger.isDebugEnabled()) logger.debug("Se obtiene los datos de la faq con identificador ["+form.getId()+"]");
	    	FaqVO faq = this.getSrvFaqService().consultaFaq(form.getId());
	    	request.getSession().setAttribute(VERFAQVO, faq);	    		    	
	    	form.setCategoria(faq.getCategoria().getId());	
	    	
    	} catch (Exception e)
    	{
    		logger.error("Error obteniendo la faq a ver");
    		throw new ValidatorException("{error.obteniendo.faq.ver}");	    		
    	}
    	
     }

    /**
     * @see es.pode.administracion.presentacion.faqs.verFaq.VerFaqController#verPasoDos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.faqs.verFaq.VerPasoDosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void verPasoDos(ActionMapping mapping, es.pode.administracion.presentacion.faqs.verFaq.VerPasoDosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
	    	logger.debug("Se recupera el vo de la faq de la request");
	        FaqVO faq = (FaqVO)request.getSession().getAttribute(VERFAQVO);        
	
	        ArrayList nombreCategoria = new ArrayList();
	    	ArrayList idiomas = new ArrayList();
	    	ArrayList idiomasAlta = new ArrayList();
	    	ArrayList titulo = new ArrayList();
	    	ArrayList descripcion = new ArrayList();
	    	ArrayList valuePosicion = new ArrayList();
	    	
	        CategoriaFaqVO categoria = faq.getCategoria();
	        DescripcionFaqVO[] descripcionesFaq = faq.getDescripcionFaq();
	        for (int j = 0; descripcionesFaq != null && j < descripcionesFaq.length; j++)
	        {
	        	if (descripcionesFaq[j].getRespuesta() != null && !("").equals(descripcionesFaq[j].getRespuesta()))
	        	{
	        		titulo.add(descripcionesFaq[j].getPregunta());
	        		descripcion.add(descripcionesFaq[j].getRespuesta());
	        		valuePosicion.add(descripcionesFaq[j].getPosicion().toString());
	        		idiomasAlta.add(descripcionesFaq[j].getIdioma());
	        	}
	        }
	        form.setTitulo(titulo);
	        form.setDescripcion(descripcion);	        
	        form.setIdiomasAlta(idiomasAlta);
	        form.setValuePosicion(valuePosicion);
	    	
	//	Se almacena los datos
	    	if (categoria != null)
	    	{
	    		CategoriaIdiomaFaqVO[] categoriasIdiomas = categoria.getCategoriaIdioma();
	    		for (int i = 0; categoriasIdiomas != null && i < categoriasIdiomas.length; i++)
	    		{
	    			if (categoriasIdiomas[i].getNombre() != null && !("").equals(categoriasIdiomas[i].getNombre()))
	    			{
	    				nombreCategoria.add(categoriasIdiomas[i].getNombre());
	    				idiomas.add(categoriasIdiomas[i].getIdioma());
	    			}    			
	    		}
	    	} 
	    	
	    	form.setIdiomas(idiomas);
	    	form.setNombreCategoria(nombreCategoria);
	        
	        request.getSession().setAttribute(VERFAQVO, null);
	        
    	} catch (Exception e)
    	{
    		logger.error("Error obteniendo la faq a ver");
    		throw new ValidatorException("{error.obteniendo.faq.ver}");	    
    	}
     }

}