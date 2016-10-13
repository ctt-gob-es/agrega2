// license-header java merge-point
package es.pode.administracion.presentacion.categoriasFaqs.verCategoriaFaq;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO;
import es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO;


/**
 * @see es.pode.administracion.presentacion.categoriasFaqs.verCategoriaFaq.VerCategoriaFaqController
 */
public class VerCategoriaFaqControllerImpl extends VerCategoriaFaqController
{

	private static Logger logger = Logger.getLogger(VerCategoriaFaqControllerImpl.class);	
	private static final String ERRORVERCATEGORIA = "errors.categoria.ver";

    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.verCategoriaFaq.VerCategoriaFaqController#obtenerCategoria(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.verCategoriaFaq.ObtenerCategoriaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCategoria(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.verCategoriaFaq.ObtenerCategoriaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	    try
		{
	    	ArrayList nombreCategoria = new ArrayList();
	    	ArrayList idiomas = new ArrayList();
	    	
	    	if (logger.isDebugEnabled()) logger.debug("Se obtiene la categoria con identificador ["+form.getId()+"]");
	    	CategoriaFaqVO categoriaFaq = this.getSrvFaqService().obtenerCategoria(form.getId());	    	
	    	
	//    	Se almacena los datos
	    	if (categoriaFaq != null)
	    	{
	    		CategoriaIdiomaFaqVO[] categoriasIdiomas = categoriaFaq.getCategoriaIdioma();	    		
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
		} catch (Exception e)
		{
			logger.error("Error recuperando la categoria faq");
			saveErrorMessage(request, ERRORVERCATEGORIA);
		}
    
 }


}