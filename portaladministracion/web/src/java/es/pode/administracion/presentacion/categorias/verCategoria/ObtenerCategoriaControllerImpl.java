/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.categorias.verCategoria;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO;
import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO;



/**
 * @see es.pode.administracion.presentacion.categorias.verCategoria.ObtenerCategoriaController
 */
public class ObtenerCategoriaControllerImpl extends ObtenerCategoriaController
{

	private static Logger logger = Logger.getLogger(ObtenerCategoriaControllerImpl.class);
	
	private static final String ERRORVERCATEGORIA = "errors.categoria.ver";


    /**
     * @see es.pode.administracion.presentacion.categorias.verCategoria.ObtenerCategoriaController#obtieneCategoria(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.categorias.verCategoria.ObtieneCategoriaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtieneCategoria(ActionMapping mapping, es.pode.administracion.presentacion.categorias.verCategoria.ObtieneCategoriaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
	    	ArrayList nombreCategoria = new ArrayList();
	    	ArrayList idiomas = new ArrayList();
	    	
	    	if (logger.isDebugEnabled()) logger.debug("Se obtiene la categoria con identificador ["+form.getIdCategoriaNoticia()+"]");
	    	CategoriaNoticiaVO categoria = this.getSrvNoticiasService().obtenerCategoria(form.getIdCategoriaNoticia());
	    	
	//    	Se almacena los datos
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
    	} catch (Exception e)
    	{
    		logger.error("Error recuperando la categoria noticia");
    		saveErrorMessage(request, ERRORVERCATEGORIA);
    	}
        
     }

}