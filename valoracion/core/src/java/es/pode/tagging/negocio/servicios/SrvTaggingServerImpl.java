/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.tagging.negocio.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.pode.tagging.negocio.dominio.ComprobarTagCriteria;
import es.pode.tagging.negocio.dominio.IdUsuarioTagCriteria;
import es.pode.tagging.negocio.dominio.TagCriteria;
import es.pode.tagging.negocio.dominio.Tagging;
import es.pode.tagging.negocio.dominio.TaggingDao;
import es.pode.tagging.negocio.dominio.TagsDeOdeCriteria;


/**
 * @see es.pode.tagging.negocio.servicios.SrvTaggingServer
 */

public class SrvTaggingServerImpl
    extends es.pode.tagging.negocio.servicios.SrvTaggingServerBase
{

	/**
     * Obtiene las etiquetas utilizadas por un usuario dado.
     * @param idUsuario El identificador del usuario a consultar.     
     * @return 	se retorna un array que contiene principalmente las etiquetas de un usuario
     * @throws Exception
     * 
     */
    protected TagVO[] handleObtenerTagsDeUsuario(java.lang.String idUsuario)
        throws java.lang.Exception
    {
    	if(logger.isDebugEnabled()) logger.debug("Obteniendo los tags asociados al usuario con identificador ["+idUsuario+"]");
    	//Creamos el criterio por el que buscar los tags
    	
    	
    	List tagsLista= this.getTaggingDao().obtenerTagsDistintosDeUsuario(this.getTaggingDao().TRANSFORM_TAGVO, idUsuario);
    	TagVO[] tags=(TagVO[])tagsLista.toArray(new TagVO[tagsLista.size()]);
    	
    	if(tags!=null && tags.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tags.length+"] tags");
    		return (tags);
    	}
    	else
    	{
    		logger.debug("No hay tags en la base de datos de Agrega");
//    		return (new String[0]);
    		return (new TagVO[0]);
    	}
    	
    }

    /**
     * Obtiene los odes que un usuario a taggeado con una etiqueta concreta.
     * @param tag La etiqueta que el usuario ha utilizado para etiquetar el ODE.
     * @param idUsuario El identificador del usuario para el cual consultar los ODEs.     
     * @return 	se retorna un array que contiene principalmente las odes de un usuario etiquetados por una etiqueta concreta
     * @throws Exception
     * 
     */
    protected es.pode.tagging.negocio.servicios.TaggingVO[] handleObtenerOdesDeTagYUsuario(java.lang.String tag, java.lang.String idUsuario)
        throws java.lang.Exception
    {
    	if(logger.isDebugEnabled()) logger.debug("Obteniendo los odes asociados al usuario con identificador ["+idUsuario+"] y al tag ["+tag+"]");
    	//Creamos el criterio por el que buscar los tags
    	
    	IdUsuarioTagCriteria criterio = new IdUsuarioTagCriteria();
    	criterio.setIdUsuario(idUsuario);
    	criterio.setTag(tag);
    	
    	//Buscamos los tags asociados a un usuario	
    	List odesLista= this.getTaggingDao().obtenerOdesPorUsuarioYTag(this.getTaggingDao().TRANSFORM_TAGGINGVO, criterio);
    	TaggingVO[] tagsVO=(TaggingVO[])odesLista.toArray(new TaggingVO[0]);
    	
    	if(tagsVO!=null && tagsVO.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tagsVO.length+"] odes");
    		return (tagsVO);
    	}
    	else
    	{
    		logger.debug("No hay odes en la base de datos para el usuario ["+idUsuario+"]y al tag ["+tag+"]");
    		return (new TaggingVO[0]);
    	}
    }

    /**
     * Obtiene todas las etiquetas utilizadas en el nodo de Agrega.     
     * @return 	se retorna un array que contiene principalmente todas las etiquetas utilizadas en el nodo de Agrega
     * @throws Exception
     * 
     */
    protected TagVO[] handleObtenerTodosTags()
        throws java.lang.Exception
    {
    	if (logger.isDebugEnabled()) logger.debug("Buscando todos los tags de Agrega");
    	
    	List tagsLista = this.getTaggingDao().obtenerTodosTagsDistintos(this.getTaggingDao().TRANSFORM_TAGVO);
    	TagVO[] tags=(TagVO[])tagsLista.toArray(new TagVO[tagsLista.size()]);
    	
    	if(tags!=null && tags.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tags.length+"] tags");
    		return (tags);
    	}
    	else
    	{
    		logger.debug("No hay tags en la base de datos de Agrega");
    		return (new TagVO[0]);
//    		return (new String[0]);
    	}
    	
    }

    /**
     * Obtiene los odes asociados a una etiqueta cualquiera de la plataforma.
     * @param tag La etiqueta para la cual se quieren consultar los ODEs asociados.  
     * @return 	se retorna un array que contiene principalmente las odes etiquetados por una etiqueta concreta
     * @throws Exception
     * 
     */
    protected es.pode.tagging.negocio.servicios.TaggingVO[] handleObtenerOdesDeTag(java.lang.String tag)
        throws java.lang.Exception
    {
    	if(logger.isDebugEnabled()) logger.debug("Obteniendo los odes asociados al tag ["+tag+"]");
    	//Creamos el criterio por el que buscar los tags
    	
    	TagCriteria criterio = new TagCriteria();
    	criterio.setTag(tag);
    	
    	//Buscamos los odes asociados a un tag
    	List odesLista= this.getTaggingDao().obtenerOdesPorTag(this.getTaggingDao().TRANSFORM_TAGGINGVO, criterio);
    	TaggingVO[] tagsVO=(TaggingVO[])odesLista.toArray(new TaggingVO[0]);
    	
    	if(tagsVO!=null && tagsVO.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tagsVO.length+"] odes");
    		return (tagsVO);
    	}
    	else
    	{
    		logger.debug("No hay odes en la base de datos para el tag ["+tag+"]");
    		return (new TaggingVO[0]);
    	}
    }


    /**
     * Introduce en la base de datos para un usuario una o varias entradas que contiene el ODE etiquetado y su etiqueta.
     * @param idOde El identificador del ODE que ha sido etiquetado.
     * @param titulo El título del ODE que ha sido etiquetado.
     * @param idiomaCat El idioma de catalogación del ODE etiquetado.
     * @param tags El listado de etiquetas asociadas al ODE.
     * @param idUsuario El identificador del usuario que ha etiquetado el ODE.   
     * @throws Exception
     * 
     */
	protected void handleIntroducirTags(
			String idOde, 
			String titulo, 
			String idiomaCat, 
			String[] tags, 
			String idUsuario, 
			String nodo) 
	throws Exception 
	{
		if (logger.isDebugEnabled()) logger.debug("Introducimos los tags con valores idODE ["+idOde+"], idUsuario ["+idUsuario+"]");
		try
		{
			if(nodo!=null && !nodo.equals("") )
			{
				if( ! this.getSrvIdentidadFederadaService().isAutenticated(idUsuario).booleanValue() )
				{
					logger.error("El usuario federado " + idUsuario + " no está autenticado en este nodo " );
					throw new UsuarioNoAutenticadoException();
				}
				
				
			}
			TaggingDao taggingDao = this.getTaggingDao();
			for (int i= 0; i< tags.length; i++){
				String tag= tags[i];
				
				ComprobarTagCriteria criteria = new ComprobarTagCriteria();
				criteria.setIdOde(idOde);
				criteria.setTag(tag);
				criteria.setIdUsuario(idUsuario);
				List resultado =  this.getTaggingDao().obtenerTag(criteria);
				
				if(resultado == null || (resultado != null && resultado.size()<= 0)){
					//si el tag no habia sido introducido por el usuario para el ode indicado lo creamos   
					Tagging tagging = taggingDao.create(idOde, titulo, idiomaCat, tag, idUsuario, nodo);
					logger.debug("Se ha insertado el tag [" +tag+ "]");
				}
				else{//si ya existia el tag para el ode y el usuario indicado no hacemos nada.
					logger.debug("El tag "+ tag + " ya ha sido asignado al ODE " + idOde + " por el usuario " + idUsuario);
				}
			}
	
			
		} catch (Exception e)
		{
			logger.error("Error al insertar el comentario", e);
		}
		
	}

	/**
     * Modifica una etiqueta.
     * @param oldTag La etiqueta que el usuario administrador va a modificar.
     * @param newTag El nuevo nombre de la etiqueta que el administrador va a introducir.     
     * @return 	se retorna 'true' si la modificación se ha realizado correctamente y 'false' si no se ha podido modificar
     * @throws Exception
     * 
     */
	protected boolean handleModificarTag(String oldTag, String newTag) throws Exception {

		boolean modificado= false;

		try{
			TaggingVO[] taggingVO= this.obtenerOdesDeTag(oldTag);
			for (int i= 0; i < taggingVO.length; i++){
				
				Tagging tagging= this.getTaggingDao().load(taggingVO[i].getId());
				taggingVO[i].setTag(newTag);
				
				String idOde = tagging.getIdOde();
				String idUsuario = tagging.getIdUsuario();
				ComprobarTagCriteria criteria = new ComprobarTagCriteria();
				criteria.setIdOde(idOde);
				criteria.setTag(newTag);
				criteria.setIdUsuario(idUsuario);
				List resultado =  this.getTaggingDao().obtenerTag(criteria);
				
				if(resultado == null || (resultado != null && resultado.size()<= 0)){
					//si el nuevo valor del tag no existe para el ode y el usuario actualizamos el valor de la fila
					this.getTaggingDao().fromTaggingVO(taggingVO[i], tagging);
					this.getTaggingDao().update(tagging);	
					modificado= true;
				}
				else if (!oldTag.equals(newTag.trim())){//si existe eliminamos la fila pues si la actualizaramos tendriamos valores duplicados
					this.getTaggingDao().remove(tagging);
					modificado= true;
				}
			}
			
		}
		catch (Exception e){
			logger.error("Error al modificar el tag "+ oldTag, e);
			modificado= false;
		}
		
		return modificado;
	}

	/**
     * Modifica una etiqueta de un usuario concreto.
     * @param oldTag La etiqueta que un usuario va a modificar.
     * @param newTag El nuevo nombre de la etiqueta que el usuario va a introducir.
     * @param idUsuario El identificador del usuario propietario de la etiqueta a modificar.
     * @return 	se retorna 'true' si la modificación se ha realizado correctamente y 'false' si no se ha podido modificar
     * @throws Exception
     * 
     */
	protected boolean handleModificarTagDeUsuario(String oldTag, String newTag, String idUsuario) throws Exception {
		
		boolean modificado= false;

		try{
			TaggingVO[] taggingVO= this.obtenerOdesDeTagYUsuario(oldTag, idUsuario);
			for (int i= 0; i< taggingVO.length; i++){
				
			
				Tagging tagging= this.getTaggingDao().load(taggingVO[i].getId());
				taggingVO[i].setTag(newTag);
				
				String idOde = tagging.getIdOde();
				ComprobarTagCriteria criteria = new ComprobarTagCriteria();
				criteria.setIdOde(idOde);
				criteria.setTag(newTag);
				criteria.setIdUsuario(idUsuario);
				List resultado =  this.getTaggingDao().obtenerTag(criteria);
				
				if(resultado == null || (resultado != null && resultado.size()<= 0)){
					//si el nuevo valor del tag no existe para el ode y el usuario actualizamos el valor de la fila
					this.getTaggingDao().fromTaggingVO(taggingVO[i], tagging);
					this.getTaggingDao().update(tagging);	
				}
				else if (!oldTag.equals(newTag.trim())){//si existe eliminamos la fila pues si la actualizaramos tendriamos valores duplicados
					this.getTaggingDao().remove(tagging);
				}
			}
			modificado= true;
			
		}
		catch (Exception e){
			logger.error("Error al modificar el tag "+ oldTag, e);
			modificado= false;
		}
		
		return modificado;
	}

	/**
     * Elimina una o varias etiquetas del nodo.
     * @param tagsABorrar Lista de etiquetas a eliminar por el administrador.
     * @return 	se retorna 'true' si el borrado se ha realizado correctamente y 'false' si no se ha podido realizar
     * @throws Exception
     * 
     */
	protected boolean handleEliminarTags(String[] tagsABorrar) throws Exception {

		boolean borrado= false;
		
		if (logger.isDebugEnabled()) logger.debug("Se van a eliminar ["+tagsABorrar.length+"] tags"); 
		try {
			for(int i=0; tagsABorrar != null && i<tagsABorrar.length; i++){	
				Tagging tag;

				TaggingVO[] taggingV0= this.obtenerOdesDeTag(tagsABorrar[i]);
					
				for (int j= 0; j<taggingV0.length; j++){
					tag= this.getTaggingDao().load(taggingV0[j].getId());
						
					if (tag != null){
						logger.debug("Comprobacion de que tag es distinto de null");						
							
						//Eliminamos el tag				
						try {
							this.getTaggingDao().remove(tag.getId());
							borrado= true;
							logger.debug("Tag tiene un valor de ["+tag+"]");
							logger.debug("Tag tiene un valor de identificador de id ["+tag.getId()+"]");
						} catch (Exception e) {
							logger.error("Error eliminando el tag con id["+tag.getId()+"]");
							borrado= false;
						}
							
					}
				}			
			}	
		}catch (Exception e) {
				logger.error("Error al recuperar de la base de datos el tag");	
				borrado= false;
		}

		return borrado;
	}

	/**
     * Elimina una o varias etiquetas de un usuario concreto.
     * @param tagsABorrar Lista de etiquetas a eliminar de un usuario concreto.
     * @param idUsuario El identificador del usuario que va a realizar el borrado de sus etiquetas
     * @return 	se retorna 'true' si el borrado se ha realizado correctamente y 'false' si no se ha podido realizar
     * @throws Exception
     * 
     */
	protected boolean handleEliminarTagsDeUsuario(String[] tagsABorrar, String idUsuario) throws Exception {
		boolean borrado= false;
		
		if (logger.isDebugEnabled()) logger.debug("Se van a eliminar ["+tagsABorrar.length+"] tags"); 
		try {
			for(int i=0; tagsABorrar != null && i<tagsABorrar.length; i++){	
				Tagging tag;

				TaggingVO[] taggingV0= this.obtenerOdesDeTagYUsuario(tagsABorrar[i], idUsuario);
					
				for (int j= 0; j<taggingV0.length; j++){
					tag= this.getTaggingDao().load(taggingV0[j].getId());
						
					if (tag != null){
						logger.debug("Comprobacion de que tag es distinto de null");						
							
						//Eliminamos el tag				
						try {
							this.getTaggingDao().remove(tag.getId());
							borrado= true;
							logger.debug("Tag tiene un valor de ["+tag+"]");
							logger.debug("Tag tiene un valor de identificador de id ["+tag.getId()+"]");
						} catch (Exception e) {
							logger.error("Error eliminando el tag con id["+tag.getId()+"]");
							borrado= false;
						}
							
					}
				}			
			}	
		}catch (Exception e) {
				logger.error("Error al recuperar de la base de datos el tag");	
				borrado= false;
		}

		return borrado;
	}

	/**
     * Obtiene la lista de etiquetas asociadas a un ODE concreto.
     * @param idOde Identificador del ODE para el cual se quieren obtener las etiquetas.
     * @return 	se retorna una lista de etiquetas asociadas al ODE deseado
     * @throws Exception
     * 
     */
	protected String[] handleObtenerTagsDeOde(String idOde) throws Exception {
		
		if(logger.isDebugEnabled()) logger.debug("Obteniendo los tags asociados al ODE con identificador ["+idOde+"]");
   	
    	
    	List tagsLista= this.getTaggingDao().obtenerTagsDistintosDeOde(this.getTaggingDao().TRANSFORM_NONE, idOde);
    	String[] tags=(String[])tagsLista.toArray(new String[tagsLista.size()]);
    	
    	if(tags!=null && tags.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tags.length+"] tags");
    		return (tags);
    	}
    	else
    	{
    		logger.debug("No hay tags en la base de datos de Agrega");
    		return (new String[0]);
    	}
	}

	@Override
	protected boolean handleEliminarTagsDeOdes(String[] idsOde) throws Exception {
		
		boolean borrado= true;
		
		if (logger.isDebugEnabled()) logger.debug("Se van a eliminar los tags de ["+idsOde.length+"] ODES"); 
		try {
			for(int i=0; idsOde != null && i<idsOde.length; i++){	
				Tagging tag;

				String idOde = idsOde[i];
				
				//obtenemos los las filas de la tabla que queremos borrar de este ODE 
				if(logger.isDebugEnabled()) logger.debug("Obteniendo los tag asociados al ode ["+idOde+"]");
		    	//Creamos el criterio por el que buscar los tags
		    	
		    	TagsDeOdeCriteria criterio = new TagsDeOdeCriteria();
		    	criterio.setIdOde(idOde);
		    			    	
		    	//Buscamos los tags asociados al ode	
		    	List odesLista= this.getTaggingDao().obtenerTodosTagsDeOde(this.getTaggingDao().TRANSFORM_TAGGINGVO, criterio);
		    	TaggingVO[] taggingVO=(TaggingVO[])odesLista.toArray(new TaggingVO[0]);
		    	
		    	Collection tags = new ArrayList();
				for (int j= 0; j<taggingVO.length; j++){
					tag= this.getTaggingDao().load(taggingVO[j].getId());
						
					if (tag != null){
						logger.debug("Comprobacion de que tag es distinto de null");
						tags.add(tag);
					}
					
//					if (tag != null){
//						logger.debug("Comprobacion de que tag es distinto de null");						
//							
//						//Eliminamos el tag				
//						try {
//							Collection c =null;
//							this.getTaggingDao().remove(tag.getId());
//							logger.debug("Tag tiene un valor de ["+tag.getId()+"]");
//							logger.debug("Tag tiene un valor de identificador de id ["+tag.getId()+"]");
//						} catch (Exception e) {
//							logger.error("Error eliminando el tag con id["+tag.getId()+"]");
//							borrado= false;
//						}			
//					}
				}
				
				//Eliminamos el tag				
				try {
					this.getTaggingDao().remove(tags);
				} catch (Exception e) {
					logger.error("Error eliminando tags de ode " + idOde );
					borrado= false;
				}	
				
				
				
				
			}
		}catch (Exception e) {
				logger.error("Error al recuperar de la base de datos el tag");	
				borrado= false;
		}
	return borrado;
	
			}

 protected TaggingVO[] handleObtenerTagsLikeLetra(String letra) throws Exception {

	 if (logger.isDebugEnabled()) logger.debug("Buscando todos los tags que coincidan con la letra " + letra);
    	
    	//List tagsLista = this.getTaggingDao().obtenerTodosTagsDistintos(this.getTaggingDao().TRANSFORM_TAGVO);
	 	List tagsLista = this.getTaggingDao().obtenerTagsconLetra(letra);
	 	TaggingVO[] tags=(TaggingVO[])tagsLista.toArray(new TaggingVO[tagsLista.size()]);
    	
    	if(tags!=null && tags.length>0)
    	{
    		if(logger.isDebugEnabled()) logger.debug("Se han obtenido ["+tags.length+"] tags");
    		return (tags);
    	}
    	else
    	{
    		logger.debug("No hay tags en la base de datos de Agrega");
    		return (new TaggingVO[0]);
    	}
    	
	}
	

}