// license-header java merge-point
package es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.faqs.servicio.CategoriaTraducidaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.CategoriasFaqController
 */
public class CategoriasFaqControllerImpl extends CategoriasFaqController
{


	private static Logger logger = Logger.getLogger(CategoriasFaqControllerImpl.class);	

	private final static String CATEGORIAFAQVO = "categoriaFaqvo";
	private final static String CATEGORIASFAQS = "categoriaFaqvo";
	private final static String FAQSCATEGORIAS = "categoriaFaqvo";
	

    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.CategoriasFaqController#obtenerCategoriasFaqs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.ObtenerCategoriasFaqsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCategoriasFaqs(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.ObtenerCategoriasFaqsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
//    		Se elimina de la request los valores
    		request.getSession().setAttribute(CATEGORIAFAQVO, null);
    		request.getSession().setAttribute(CATEGORIASFAQS, null);
			request.getSession().setAttribute(FAQSCATEGORIAS, null);
    		
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
			
			/**
			 * **************************************************************************************************************************************
			 * ******************************************* SE RECUPERAN LAS CATEGORIAS **************************************************************
			 * **************************************************************************************************************************************
			 * */
			NoticiasControllerImpl noticiasController = new NoticiasControllerImpl();	
			CategoriaTraducidaVO[] categoriasFaq = this.getSrvFaqService().obtenerCategoriasTraducidas(noticiasController.devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));			
			if (logger.isDebugEnabled()) logger.debug("Se han recuperado ["+categoriasFaq.length+"] categorias faqs");
			
			for (int i = 0; i < categoriasFaq.length; i++)
			{
	//			sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII			
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("@","&#64"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("\\\"","&#34"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll(":","&#58"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("%","&#37"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("\\+","&#43"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("-","&#45"));
				categoriasFaq[i].setNombre(categoriasFaq[i].getNombre().replaceAll("'","&#39"));
			}   
			
			/**
			 * **************************************************************************************************************************************
			 * ************************************* SE INTRODUCEN LOS DATOS EN EL FORMULARIO *******************************************************
			 * **************************************************************************************************************************************
			 * */
			form.setCategoriasAsArray(categoriasFaq);	    
	    	
    	} catch (Exception e)
    	{
    		logger.error("Error recuperando las categorias de las faqs", e);
    		throw new ValidatorException("{errors.categorias.borrar.cargar}");    		
    	}

     }

    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.CategoriasFaqController#getIds(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getIds(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.categoriasFaqs.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	/**
		 * **************************************************************************************************************************************
		 * **************************** SE RECUPERAN LOS IDENTIFICADORES DE LA CATEGORIA A ELIMINAR *********************************************
		 * **************************************************************************************************************************************
		 * */    	
		List lista = ((CategoriasFaqsEliminarFormImpl) form).getIdRowSelection();	
		if (lista == null)
		{
			logger.error("Debe seleccionar al menos una categoria a eliminar");
			throw new ValidatorException("{errors.borrarCategoria.idNulo}");
		}
		else
		{
			form.setIds(lista);
		}
     }

}