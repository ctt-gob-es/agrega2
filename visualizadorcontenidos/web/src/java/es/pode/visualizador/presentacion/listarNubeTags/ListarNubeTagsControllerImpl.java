/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.listarNubeTags;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.indexador.negocio.servicios.busqueda.PalabraClaveVO;
import es.pode.indexador.negocio.servicios.busqueda.ParamPalabrasClave;
import es.pode.indexador.negocio.servicios.busqueda.PrioridadPalabrasClaveVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.visualizador.presentacion.listarNubeTags.ListarNubeTagsController
 */
public class ListarNubeTagsControllerImpl extends ListarNubeTagsController
{


	private static Logger logger = Logger.getLogger(ListarNubeTagsControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.listarNubeTags.ListarNubeTagsController#cargarTags(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.listarNubeTags.CargarTagsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarTags(ActionMapping mapping, es.pode.visualizador.presentacion.listarNubeTags.CargarTagsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	PalabraClaveVO[] tags = new PalabraClaveVO[0];
    	try{
	    	ParamPalabrasClave paramBusq = new ParamPalabrasClave();
	        
	    	paramBusq.setIdiomaBusqueda(obtenerIdiomaBusqueda());
	    	paramBusq.setIdiomaNavegacion(((Locale) request.getSession()
					.getAttribute(ConstantesAgrega.DEFAULT_LOCALE))
					.getLanguage());
	    	
	    	String maximoTags = request.getSession().getServletContext().getInitParameter("max_listado_nube_tag");
	    	if (maximoTags == null || maximoTags.equals("")) maximoTags = "1000";
	    	paramBusq.setNumeroResultados(new Integer(maximoTags));
    		PrioridadPalabrasClaveVO prioridadPC = this.getSrvBuscadorService().obtenerPalabrasClave(paramBusq);
    		tags = prioridadPC.getPalabrasClave();
    	}catch(Exception e){
			logger.error("Error obteniendo todas las etiquetas de la nube tags",e);
    	}
        form.setTagsAsArray(tags);
     }

	private String obtenerIdiomaBusqueda() throws Exception{

        String idiomaBusqueda = "";
        if(LdapUserDetailsUtils.estaAutenticado()){
            try{
                 idiomaBusqueda=LdapUserDetailsUtils.getIdiomaBusqueda();
                 if (idiomaBusqueda == null) idiomaBusqueda = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            } catch (Exception e) {
                 logger.error("ListarNubeTagsController - obtenerIdiomaBusqueda: Error al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
                 idiomaBusqueda = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            }
        }else idiomaBusqueda = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            return idiomaBusqueda;
	}







}