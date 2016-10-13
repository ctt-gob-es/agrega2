/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.tagging.presentacion.listarAgregaTags;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.tagging.negocio.servicios.TagVO;
import es.pode.tagging.presentacion.TaggingSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.listarAgregaTags.ListarAgregaTagsController
 */
public class ListarAgregaTagsControllerImpl extends ListarAgregaTagsController
{

	public static final String NEUTRO = "neutro";
	public static final String LAYOUT_NEUTRO = "NEUTRO";
	public static final String TRUE = "true";

	protected static Logger logger = Logger.getLogger(ListarAgregaTagsControllerImpl.class); 

    /**
     * @see es.pode.tagging.presentacion.listarAgregaTags.ListarAgregaTagsController#obtenerAgregaTags(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.listarAgregaTags.ObtenerAgregaTagsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerAgregaTags(ActionMapping mapping, es.pode.tagging.presentacion.listarAgregaTags.ObtenerAgregaTagsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		TaggingSession taggingSession =  this.getTaggingSession(request);
		taggingSession.setTagPaginacion("");
    	if(taggingSession!=null){
    		String usuario = taggingSession.getUsuario();
    		logger.debug("Usuario: " + usuario);
    		if (usuario!=null) 
    			form.setEsAnonimo(false);// usuario conocido (esta autenticado)
    		else {
    			
    			//Comprobamos si seguimos autenticados si seguimos autenticados
    			//recuperamos el usuario de ldap y lo metemos en sesion
    			logger.debug("OBTENIENDO USUARIO: " +usuario);
    			try{
    				usuario = LdapUserDetailsUtils.getUsuario();
    				logger.debug("USUARIO CONOCIDO: " + usuario);
    			}
    			catch(Exception ad){
    				logger.debug("USUARIO ANONIMO");
    			}
    			taggingSession.setUsuario(usuario);
    			
    			if(usuario!=null)
    				form.setEsAnonimo(false);// usuario conocido (esta autenticado)
    			else
    				form.setEsAnonimo(true);// usuario anonimo
    		}
    		

    		
    		logger.debug("Obteniendo todos los tags de Agrega");
    		TagVO[] tags = this.getSrvTaggingServer().obtenerTodosTags();
    		form.setTagsAsArray(tags);
    	}else{
    		form.setEsAnonimo(true);// usuario anonimo
    	}
    	
    	//si la peticion viene del nodo neutro mostrarmos el layout-sinlateral(layout del nodo neutro)
    	if(this.TRUE.equals(AgregaPropertiesImpl.getInstance().getProperty(this.NEUTRO)))
    		form.setTipoLayoutBuscador(this.LAYOUT_NEUTRO);
     }









}