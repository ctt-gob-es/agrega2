// license-header java merge-point
package es.pode.administracion.presentacion.noticias.verNoticia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.noticias.noticias.NoticiasControllerImpl;
import es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO;
import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO;
import es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.noticias.verNoticia.VerNoticiaController
 */
public class VerNoticiaControllerImpl extends VerNoticiaController
{


	private static Logger logger = Logger.getLogger(VerNoticiaControllerImpl.class);

	private final static String VERNOTICIAVO = "verNoticiavo";			
	

    /**
     * @see es.pode.administracion.presentacion.noticias.verNoticia.VerNoticiaController#obtenerNoticias(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.noticias.verNoticia.ObtenerNoticiasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerNoticias(ActionMapping mapping, es.pode.administracion.presentacion.noticias.verNoticia.ObtenerNoticiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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
			Collection categorias = Arrays.asList(this.getSrvNoticiasService().obtenerCategoriasTraducidas(noticiasController.
					devuelveIdiomasTraducibles(idiomasPlataforma, idiomaLogado, idiomaPrioritario, idiomaSecundario)));
			form.setCategoriaBackingList(categorias, "idCategoriaNoticia", "nombreCategoria");	
	    	
	//      Se obtiene los datos de la noticia
	    	if (logger.isDebugEnabled()) logger.debug("Se obtiene los datos de la noticia con identificador ["+form.getIdNoticia()+"]");
	    	NoticiaVO noticia = this.getSrvNoticiasService().obtenerNoticia(form.getIdNoticia());
	    	request.getSession().setAttribute(VERNOTICIAVO, noticia);  	    	
	    	form.setEstado(noticia.getActiva());
	    	form.setAlineamiento(noticia.getAlineamientoImg());
	    	form.setCategoria(noticia.getCategoria().getId());	
	    	if (form.getActivarImagen() == null)
	    	{
	    		//No viene con valor
//	    		Tratamiento de la imagen	
	    		String sURLImagen = noticia.getURLImagen();	
	    		if (sURLImagen != null && sURLImagen.indexOf("/") != -1)
				{
					form.setActivarImagen(1);							
					String sToken[] = sURLImagen.split("/");
					form.setNombreImagen(sToken[sToken.length-1]);
								
				}
	    		else
	    			form.setActivarImagen(2);
	    		
	    	}    	
	    	
    	} catch (Exception e)
    	{
    		logger.error("Error obteniendo la noticia a ver");
    		throw new ValidatorException("{error.obteniendo.noticia.ver}");	    		
    	}
    	
        
     }

    /**
     * @see es.pode.administracion.presentacion.noticias.verNoticia.VerNoticiaController#verPasoDos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.noticias.verNoticia.VerPasoDosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void verPasoDos(ActionMapping mapping, es.pode.administracion.presentacion.noticias.verNoticia.VerPasoDosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
	    	logger.debug("Se recupera el vo de la noticia de la request");
	        NoticiaVO noticia = (NoticiaVO)request.getSession().getAttribute(VERNOTICIAVO);        
	
	        ArrayList nombreCategoria = new ArrayList();
	    	ArrayList idiomas = new ArrayList();
	    	ArrayList idiomasAlta = new ArrayList();
	    	ArrayList titulo = new ArrayList();
	    	ArrayList resumen = new ArrayList();
	    	ArrayList cuerpo = new ArrayList();
	        CategoriaNoticiaVO categoria = noticia.getCategoria();
	        DescripcionNoticiaVO[] descripcionesNoticia = noticia.getDescripcionNoticia();
	        for (int j = 0; descripcionesNoticia != null && j < descripcionesNoticia.length; j++)
	        {
	        	if (descripcionesNoticia[j].getCuerpo() != null && !("").equals(descripcionesNoticia[j].getCuerpo()))
	        	{
	        		titulo.add(descripcionesNoticia[j].getTitulo());
	        		resumen.add(descripcionesNoticia[j].getResumen());
	        		cuerpo.add(descripcionesNoticia[j].getCuerpo());
	        		idiomasAlta.add(descripcionesNoticia[j].getIdioma());
	        	}
	        }
	        form.setTitulo(titulo);
	        form.setResumen(resumen);
	        form.setCuerpo(cuerpo);
	        form.setIdiomasAlta(idiomasAlta);
	    	
	//	Se almacena los datos
	    	if (categoria != null)
	    	{
	    		CategoriaIdiomaNoticiaVO[] categoriasIdiomas = categoria.getCategoriaIdioma();
	    		for (int i = 0; categoriasIdiomas != null && i < categoriasIdiomas.length; i++)
	    		{
	    			if (categoriasIdiomas[i].getNombreCategoria() != null && !("").equals(categoriasIdiomas[i].getNombreCategoria()))
	    			{
	    				nombreCategoria.add(categoriasIdiomas[i].getNombreCategoria());
	    				idiomas.add(categoriasIdiomas[i].getIdioma());
	    			}    			
	    		}
	    	} 
	    	
	    	form.setIdiomas(idiomas);
	    	form.setNombreCategoria(nombreCategoria);
	        
	        request.getSession().setAttribute(VERNOTICIAVO, null);
	        
    	} catch (Exception e)
    	{
    		logger.error("Error obteniendo la noticia a ver");
    		throw new ValidatorException("{error.obteniendo.noticia.ver}");	    
    	}
     }

}