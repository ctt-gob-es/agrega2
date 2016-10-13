// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.ProponerController
 */
public class ProponerControllerImpl extends ProponerController
{

	private final String SPLITTER = ";";
	private Logger logger = Logger.getLogger(ProponerControllerImpl.class);

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.ProponerController#proponerODEPublicacion(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.ProponerODEPublicacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void proponerODEPublicacion(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.ProponerODEPublicacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    

    		ResultadoOperacionVO resultado;
    		if(logger.isDebugEnabled())
    		logger.debug("Proponiendo para publicacion ODE con titulo: " + form.getTitulo() + " idODE: " + form.getIdODE());
    		//El comentario no es obligatorio!!!!!!!!! Y el publicacion no da ningún error si no lleva comentarios
//    		if (form.getComentarios() == null && form.getComentarios().trim().length()==0) {
//    			logger.debug("Sin comentarios");
//    			form.setComentarios("");
//    		}
			// ponemos 2500 pero en el mensaje 2000 para que el usuario no se
			// sienta estafado con falta de caracteres
			if ((form.getComentarios().length() < 2500)) {
				try {
					
					resultado = this.getSrvPublicacionService().proponerPublicacion(form.getIdODE(),
							LdapUserDetailsUtils.getUsuario(), form.getComentarios(), form.getTitulo());
				} catch (Exception ex) {
					logger.error("ERROR (excepción) proponiendo el ODE con IdODE[" + form.getIdODE() + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
							+ "] y titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: " ,ex);
					throw new ValidatorException("{gestorFlujo.excepcion.proponer.proponer}");
				}

				if (!resultado.getIdResultado().equals("0.0")) {
					logger.error("Error proponiendo para publicacion el ODE con IdODE[" + form.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");

					// throw new ValidatorException(resultado.getDescripcion());
//    					saveErrorMessage(request, "gestorFlujo.error.proponer.validar", new String[] { form.getTitulo(),
//    							resultado.getDescripcion() });
					form.setMensajes(resultado.getDescripcion().substring(0,resultado.getDescripcion().length()-1).split(SPLITTER));

				} else {
					logger.info("Propuesto correctamente: ODE con IdODE[" + form.getIdODE() + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
							+ "] y titulo[" + form.getTitulo() + "]");
					String[] textoVacio=new String[0];
					form.setMensajes(textoVacio);
					// saveSuccessMessage(request,
					// "gestorFlujo.error.proponer.validar",new
					// String[]{form.getTitulo(),resultado.getDescripcion()});
				}
			} else {
				logger.warn("Longitud de comentario no válida al proponer el ODE con IdODE[" + form.getIdODE()
						+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
						+ "] y titulo[" + form.getTitulo() + "]; longitud: " + form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
			}
    		

    	}

    



    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.ProponerController#cargaFormularioProponer(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.CargaFormularioProponerForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFormularioProponer(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.proponer.CargaFormularioProponerForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    
     }









}