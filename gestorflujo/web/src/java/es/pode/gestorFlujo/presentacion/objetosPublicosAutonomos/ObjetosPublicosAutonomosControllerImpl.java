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