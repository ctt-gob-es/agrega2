// license-header java merge-point
package es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.administracion.presentacion.Utils.IdsBean;
import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarCategoriaNoticiaController
 */
public class EliminarCategoriaNoticiaControllerImpl extends EliminarCategoriaNoticiaController
{

	Logger logger = Logger.getLogger(this.getClass());

	public final static String ERROR_ELIMINANDO_CATEGORIAS="errors.categorias.eliminar";
	public final static String ERROR_ELIMINANDO_CATEGORIAS_IDS="errors.categorias.borrar.ids";
	public final static String ERROR_CARGAR_CATEGORIAS="errors.categorias.borrar.cargar";

	private final static String CATEGORIASNOTICIAS = "categorias";	
	private final static String NOTICIASCATEGORIAS = "noticiasCategorias";
	
    /**
     * @see es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarCategoriaNoticiaController#eliminar(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminar(ActionMapping mapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
		{
    		/**
    		 * **************************************************************************************************************************************
    		 * ****************************************** ELIMINANDO LAS CATEGORIAS NOTICIAS ********************************************************
    		 * **************************************************************************************************************************************
    		 * */
			logger.debug("Eliminando categorias " + form.getCategoriasDeleted());			
			if (form.getListaIds() != null && !"".equals(form.getListaIds())) 
			{
							
				String[]listIDsStrings=form.getListaIds().split(" ");
				Long[] listIDs =new Long[listIDsStrings.length];
				for(int i=0; i<listIDsStrings.length;i++)
				{
					listIDs[i]=Long.valueOf(listIDsStrings[i]);
				}
				
				CategoriaNoticiaTraducidaVO[] categoriasAux = (CategoriaNoticiaTraducidaVO[])request.getSession().getAttribute(CATEGORIASNOTICIAS);
				form.setCategoriasDeletedAsArray(categoriasAux);
				if (logger.isDebugEnabled()) logger.debug("Se introduce en sesion las categorias");
				request.getSession().getAttribute(NOTICIASCATEGORIAS);
				for (int i = 0; listIDs != null && i < listIDs.length; i++)
				{
					if (logger.isDebugEnabled()) logger.debug("Se comprueba si hay noticias relacionadas con la categoria con id ["+listIDs[i]+"]");
					NoticiaTraducidaVO[] noticias = this.getSrvNoticiasService().obtenerNoticiasFromIdCategoria(listIDs[i], (String[])request.getSession().getAttribute(NOTICIASCATEGORIAS));
					if (noticias != null && noticias.length > 0)
					{
						if (logger.isDebugEnabled()) logger.debug("Hay ["+noticias.length+"] noticias relacionadas con la categoria");
						for (int j = 0; j < noticias.length; j++)
						{
							logger.debug("Se elimina la noticia con identificador ["+noticias[j].getIdNoticia()+"]");
							this.getSrvNoticiasService().eliminarNoticia(noticias[j].getIdNoticia());
						}
					}
					if (logger.isDebugEnabled()) logger.debug("Se elimina la categoria con identificador ["+listIDs[i]+"]");
					this.getSrvNoticiasService().eliminarCategoria(listIDs[i]);
				}							
				logger.debug("Categorias eliminadas");
				request.getSession().setAttribute(CATEGORIASNOTICIAS, null);
				request.getSession().setAttribute(NOTICIASCATEGORIAS, null);
			
			}
			else				
				saveErrorMessage(request, "ERROR_ELIMINANDO_NOTICIAS");
			
				
		}
		catch (Exception e) 
		{
			logger.error("EliminarControllerImpl - eliminar ERROR: Eliminando noticias con ids["+form.getListaIds()+"]");
			logger.error(e);
			saveErrorMessage(request, ERROR_ELIMINANDO_CATEGORIAS_IDS);
		}
     }

    /**
     * @see es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarCategoriaNoticiaController#obtenerCategorias(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.ObtenerCategoriasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCategorias(ActionMapping mapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.ObtenerCategoriasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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
	        	  
	 		    CategoriaNoticiaTraducidaVO[] categoriasAux = new CategoriaNoticiaTraducidaVO[listIDs.length];	        		
	        	
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
				form.setCategoriasNoticiasAsArray(categoriasAux);
				request.getSession().setAttribute(CATEGORIASNOTICIAS, categoriasAux);
				request.getSession().setAttribute(NOTICIASCATEGORIAS, noticiasController.devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario));
				
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
     * @see es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.EliminarCategoriaNoticiaController#validarSeleccion(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.ValidarSeleccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String validarSeleccion(ActionMapping mapping, es.pode.administracion.presentacion.categorias.eliminarCategoriaNoticia.ValidarSeleccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {        
    	return form.getSeleccionado();
    }

    private  CategoriaNoticiaTraducidaVO[] categoriasElim(Long[] listIDs, String[] idiomasTraducibles) throws RemoteException, Exception
	{    	
    	CategoriaNoticiaTraducidaVO[] categorias = this.getSrvNoticiasService().obtenerCategoriasTraducidas(idiomasTraducibles);
    	CategoriaNoticiaTraducidaVO[] categoriasTemp = new CategoriaNoticiaTraducidaVO[listIDs.length];
		for(int j=0;j<listIDs.length;j++)
		{
			for(int h=0;h<categorias.length;h++)
			{
				if(categorias[h].getIdCategoriaNoticia().equals(listIDs[j]))
				{
				
				//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("@","&#64"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("\\\"","&#34"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll(":","&#58"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("%","&#37"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("\\+","&#43"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("-","&#45"));
					categorias[h].setNombreCategoria(categorias[h].getNombreCategoria().replaceAll("'","&#39"));
					
					categoriasTemp[j]=categorias[h];
				}
			}
		}
		return categoriasTemp;
		
	}

}