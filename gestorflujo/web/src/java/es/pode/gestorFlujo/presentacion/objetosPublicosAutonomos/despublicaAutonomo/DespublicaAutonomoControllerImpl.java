/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.despublicaAutonomo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.despublicaAutonomos.DespublicaAutonomosController
 */
public class DespublicaAutonomoControllerImpl extends DespublicaAutonomoController
{
	private Logger logger = Logger.getLogger(DespublicaAutonomoController.class);


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.despublicaAutonomos.DespublicaAutonomosControllerr#despublicarAutonomamente(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicosAutonomos.despublicaAutonomos.DespublicarAutonomamenteForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	public final void despublicaAutonomo(ActionMapping mapping, DespublicaAutonomoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO recordar que tengo que mandar un mail al dueño cuando despublico un ODE suyo.
    	ResultadoOperacionVO resultado;
    	logger.info("Despublicando el ODE :" + form.getIdODE() + " de titulo: " + form.getTitulo() + "y usuario" + form.getIdUsuario());

    	if (form.getComentarios() != null) {
    		// ponemos 2500 pero en el mensaje 2000 para que el usuario no se sienta estafado con falta de caracteres
    		if ((form.getComentarios().trim().length() > 0) && (form.getComentarios().length() < 2500)) {
    			try {
    				resultado = this.getSrvPublicacionService().rechazar(form.getIdODE(), form.getIdUsuario(), form.getComentarios(), form.getTitulo());
    			} catch (Exception ex) {
    				logger.error("ERROR (excepción) despublicando autonomamente el ODE con IdODE[" + form.getIdODE() + "] usuario["
    						+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
    						+ "] y titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: " + ex.getMessage());
    				throw new ValidatorException("{gestorFlujo.excepcion.despublicar.despublicar}");
    			}
    			if (!resultado.getIdResultado().equals("0.0")) {
    				logger.error("Error despublicando autonomamente el ODE con IdODE[" + form.getIdODE() + "] usuario["
    						+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
    						+ "] y titulo[" + form.getTitulo() + "]");

    				saveErrorMessage(request, "gestorFlujo.error.despublicar", new String[] { form.getTitulo(),
    						resultado.getDescripcion() });

    			} else {
    				//TODO llamar al Servicio de enviarCorreo, y enviarle un correo al dueño del ODE
    				logger.info("Despublicado correctamente: ODE con IdODE[" + form.getIdODE() + "] usuario["
    						+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
    						+ "] y titulo[" + form.getTitulo() + "]");
    			}
    		}else {
    				logger.warn("Longitud de comentario no válida en la despublicación autónomadel ODE con IdODE["
    						+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario() + "] titulo[" + form.getTitulo() + "]y longitud: "
    						+ form.getComentarios().length());
    				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
    			}
    		} else {
    			logger.warn("No se han introducido comentarios en la despublicación autónoma del ODE con IdODE["
    					+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario()+ "] y titulo[" + form.getTitulo() + "]");
    			throw new ValidatorException("{gestorFlujo.comentario.obligatorio}");
    		}
    	}









}