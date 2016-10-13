/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.administracion.presentacion.Utils.IdsBean;
import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.faqs.servicio.CategoriaTraducidaVO;
import es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqController
 */
public class EliminarCategoriaFaqControllerImpl extends EliminarCategoriaFaqController
{

	Logger logger = Logger.getLogger(this.getClass());

	public final static String ERROR_ELIMINANDO_CATEGORIAS="errors.categorias.eliminar";
	public final static String ERROR_ELIMINANDO_CATEGORIAS_IDS="errors.categorias.borrar.ids";
	public final static String ERROR_CARGAR_CATEGORIAS="errors.categorias.borrar.cargar";

	private final static String CATEGORIASFAQS = "categorias";	
	private final static String FAQSCATEGORIAS = "faqsCategorias";


    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqController#eliminarCategoriaFaq(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarCategoriaFaq(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
		{
    		/**
    		 * **************************************************************************************************************************************
    		 * ****************************************** ELIMINANDO LAS CATEGORIAS NOTICIAS ********************************************************
    		 * **************************************************************************************************************************************
    		 * */
			logger.debug("Eliminando categorias " + form.getCategoriasBorradas());			
			if (form.getListaIds() != null && !"".equals(form.getListaIds())) 
			{
							
				String[]listIDsStrings=form.getListaIds().split(" ");
				Long[] listIDs =new Long[listIDsStrings.length];
				for(int i=0; i<listIDsStrings.length;i++)
				{
					listIDs[i]=Long.valueOf(listIDsStrings[i]);
				}
				
				CategoriaTraducidaVO[] categoriasAux = (CategoriaTraducidaVO[])request.getSession().getAttribute(CATEGORIASFAQS);
				form.setCategoriasBorradasAsArray(categoriasAux);
				
				if (logger.isDebugEnabled()) logger.debug("Se introduce en sesion las categorias");
				request.getSession().getAttribute(FAQSCATEGORIAS);
				for (int i = 0; listIDs != null && i < listIDs.length; i++)
				{
					if (logger.isDebugEnabled()) logger.debug("Se comprueba si hay faqs relacionadas con la categoria con id ["+listIDs[i]+"]");
					FaqTraducidaVO[] faqs = this.getSrvFaqService().obtenerFaqsFromIdCategoria(listIDs[i], (String[])request.getSession().getAttribute(FAQSCATEGORIAS));
					
					if (faqs != null && faqs.length > 0)
					{
						if (logger.isDebugEnabled()) logger.debug("Hay ["+faqs.length+"] faqs relacionadas con la categoria");
						for (int j = 0; j < faqs.length; j++)
						{
							logger.debug("Se elimina la faq con identificador ["+faqs[j].getId()+"]");
							this.getSrvFaqService().eliminarFaq(faqs[j].getId());							
						}
					}
					if (logger.isDebugEnabled()) logger.debug("Se elimina la categoria con identificador ["+listIDs[i]+"]");
					this.getSrvFaqService().eliminarCategoria(listIDs[i]);					
				}							
				logger.debug("Categorias eliminadas");
				request.getSession().setAttribute(CATEGORIASFAQS, null);
				request.getSession().setAttribute(FAQSCATEGORIAS, null);
			
			}
			else				
				saveErrorMessage(request, "ERROR_ELIMINANDO_NOTICIAS");
			
				
		}
		catch (Exception e) 
		{
			logger.error("EliminarControllerImpl - eliminar ERROR: Eliminando faqs con ids["+form.getListaIds()+"]");
			logger.error(e);
			saveErrorMessage(request, ERROR_ELIMINANDO_CATEGORIAS_IDS);
		}
     }

    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqController#obtenerCategorias(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.ObtenerCategoriasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCategorias(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.ObtenerCategoriasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
		{
    		if (logger.isDebugEnabled()) logger.debug("Obteniendo los identificadores de las categorias a eliminar");
			Object[] listIDs = form.getIdsAsArray();

	        if(listIDs==null || listIDs.length==0)
	        {
	        	
	        	form.setSeleccionado("SIN_SELECCION");
	        	saveErrorMessage(request, ERROR_ELIMINANDO_CATEGORIAS);//No se ha seleccionado ninguna categoria
	        	
	        }
	        else
	        {
	        	form.setSeleccionado("CON_SELECCION");	
	        	
	        	Iterator iter = (form.getIds()).iterator();	  		   
       
	        	IdsBean idsBean=Utils.ids2StringLong(iter, " ");
	        	  
	 		    CategoriaTraducidaVO[] categoriasAux = new CategoriaTraducidaVO[listIDs.length];	 		          		
	        	
	        	if (logger.isDebugEnabled()) logger.debug("Obtenemos los idiomas traducibles");
	    		
	    		String[] idiomasPlataforma = I18n.getInstance().obtenerIdiomasPlataforma();
	    		if (logger.isDebugEnabled()) logger.debug("Hay ["+idiomasPlataforma.length+"] en la plataforma");		
	    		
	    		String idiomaLogado = LdapUserDetailsUtils.getIdioma();
	    		String idiomaPrioritario = I18n.getInstance().obtenerIdiomaDefectoPlataforma();		
	    		String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
	    		if (logger.isDebugEnabled()) logger.debug("El idioma del usuario es ["+idiomaLogado+"], idioma prioritario de la plataforma es ["+idiomaPrioritario+"] y el secundario es ["+idiomaSecundario+"]");			
	    		
	    		NoticiasControllerImpl noticiasController = new NoticiasControllerImpl();	    		
	    		categoriasAux=categoriasElim(idsBean.getIDs().toArray(new Long[0]),noticiasController.devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario) );
				
			
		        form.setListaIds(idsBean.getIds().trim());	
		        form.setCategoriasFaqsAsArray(categoriasAux);				
				request.getSession().setAttribute(CATEGORIASFAQS, categoriasAux);
				request.getSession().setAttribute(FAQSCATEGORIAS, noticiasController.devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));
				
			  }
	        
		}
		catch (Exception e) 
		{
			logger.error("EliminarControllerImpl - obtenerIdiomas ERROR: Error al intentar cargar noticias");
			logger.error(e);
			saveErrorMessage(request, ERROR_CARGAR_CATEGORIAS);
		}
     }

    /**
     * @see es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.EliminarCategoriaFaqController#validarSeleccion(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.ValidarSeleccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String validarSeleccion(ActionMapping mapping, es.pode.administracion.presentacion.categoriasFaqs.eliminarCategoriaFaq.ValidarSeleccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	return form.getSeleccionado();
    }


    private  CategoriaTraducidaVO[] categoriasElim(Long[] listIDs, String[] idiomasTraducibles) throws RemoteException, Exception
	{    	
    	CategoriaTraducidaVO[] categorias = this.getSrvFaqService().obtenerCategoriasTraducidas(idiomasTraducibles);    	
    	CategoriaTraducidaVO[] categoriasTemp = new CategoriaTraducidaVO[listIDs.length];
		for(int j=0;j<listIDs.length;j++)
		{
			for(int h=0;h<categorias.length;h++)
			{
				if(categorias[h].getId().equals(listIDs[j]))
				{
				
				//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("@","&#64"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("\\\"","&#34"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll(":","&#58"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("%","&#37"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("\\+","&#43"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("-","&#45"));
					categorias[h].setNombre(categorias[h].getNombre().replaceAll("'","&#39"));
					
					categoriasTemp[j]=categorias[h];
				}
			}
		}
		return categoriasTemp;
		
	}


}