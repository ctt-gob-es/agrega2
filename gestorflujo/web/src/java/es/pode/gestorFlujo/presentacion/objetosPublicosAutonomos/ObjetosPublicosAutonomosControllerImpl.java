/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.ObjetosPublicosAutonomosController
 */
	public class ObjetosPublicosAutonomosControllerImpl extends ObjetosPublicosAutonomosController
	{
		private Logger logger = Logger.getLogger(ObjetosPublicosAutonomosController.class);



    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.ObjetosPublicosAutonomosController#cargaTodosPublicadosAutonomos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.CargaTodosPublicadosAutonomosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void cargaTodosPublicosAutonomos(ActionMapping mapping, CargaTodosPublicosAutonomosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	try {
			form.setListaODESAsArray(this.getSrvPublicacionService().obtenODEsPublicadosAutonomo());
			logger.info("Se han cargado "+ form.getListaODESAsArray().length + "ODES públicos autónomos de todos los usuarios del nodo");
			form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
		} catch (Exception ex) {
			logger.error("Error inesperado cargando objetos publicados autonomos del usuario: " + LdapUserDetailsUtils.getUsuario(), ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
    }
}