// license-header java merge-point
package es.pode.administracion.presentacion.categorias.categorias;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


public class CategoriasControllerImpl extends CategoriasController
{


	private static Logger logger = Logger.getLogger(CategoriasControllerImpl.class);	

	private final static String CATEGORIAVO = "categoriavo";
	private final static String CATEGORIASNOTICIAS = "categorias";	
	private final static String NOTICIASCATEGORIAS = "noticiasCategorias";
	
    /**
     * @see es.pode.administracion.presentacion.categorias.categorias.CategoriasController#obtenerCategoriasNoticias(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categorias.categorias.ObtenerCategoriasNoticiasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCategoriasNoticias(ActionMapping mapping, es.pode.administracion.presentacion.categorias.categorias.ObtenerCategoriasNoticiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		//Se elimina de la request los valores
    		request.getSession().setAttribute(CATEGORIAVO, null);
    		request.getSession().setAttribute(CATEGORIASNOTICIAS, null);
			request.getSession().setAttribute(NOTICIASCATEGORIAS, null);
    		
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
			CategoriaNoticiaTraducidaVO[] categorias = this.getSrvNoticiasService().obtenerCategoriasTraducidas(noticiasController.devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));
			if (logger.isDebugEnabled()) logger.debug("Se han recuperado ["+categorias.length+"] categorias noticias");
			
			for (int i = 0; i < categorias.length; i++)
			{
	//			sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII			
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("@","&#64"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("\\\"","&#34"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll(":","&#58"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("%","&#37"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("\\+","&#43"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("-","&#45"));
				categorias[i].setNombreCategoria(categorias[i].getNombreCategoria().replaceAll("'","&#39"));
			}   
			
			/**
			 * **************************************************************************************************************************************
			 * ************************************* SE INTRODUCEN LOS DATOS EN EL FORMULARIO *******************************************************
			 * **************************************************************************************************************************************
			 * */
	    	form.setCategoriasNoticiasAsArray(categorias);
	    	
    	} catch (Exception e)
    	{
    		logger.error("Error recuperando las categorias de las noticias", e);
    		throw new ValidatorException("{errors.categorias.borrar.cargar}");    		
    	}

    }

	public void getIds(ActionMapping mapping, GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		/**
		 * **************************************************************************************************************************************
		 * **************************** SE RECUPERAN LOS IDENTIFICADORES DE LA CATEGORIA A ELIMINAR *********************************************
		 * **************************************************************************************************************************************
		 * */
		List lista = ((CategoriasNoticiasEliminarFormImpl) form).getIdCategoriaNoticiaRowSelection();		
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