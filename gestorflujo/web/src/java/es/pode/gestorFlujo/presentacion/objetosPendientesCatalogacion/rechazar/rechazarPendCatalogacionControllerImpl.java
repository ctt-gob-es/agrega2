/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.rechazarPendCatalogacionController
 */
public class rechazarPendCatalogacionControllerImpl extends rechazarPendCatalogacionController
{



	private Logger logger = Logger.getLogger(rechazarPendCatalogacionController.class);


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.rechazarPendCatalogacionController#rechazarODE(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.RechazarODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void rechazarODE(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.RechazarODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ResultadoOperacionVO resultado;
		logger.info("Rechazando ODE :" + form.getIdODE() + " de titulo: " + form.getTitulo());
		if (form.getComentarios() != null) {
			// ponemos 2500 pero en el mensaje 2000 para que el usuario no se
			// sienta estafado con falta de caracteres
			if ((form.getComentarios().trim().length() > 0) && (form.getComentarios().length() < 2500)) {
				if(logger.isDebugEnabled())
					logger.debug("Comentarios correctos rechazando ODE :" + form.getIdODE() + " de titulo: "
							+ form.getTitulo() + " Comentarios: " + form.getComentarios() + ";");
					String usuario=LdapUserDetailsUtils.getUsuario();
					String[] identificadoresCreadores=new String[1];
					identificadoresCreadores[0]=form.getIdODE();
					String[] creadores=this.getSrvPublicacionService().obtenerUsuariosCreacionDeIdentificadores(identificadoresCreadores);
					Boolean activo = this.getSrvAdminUsuariosService().estaActivo(creadores[0]);
					if(!activo){
						logger.info("El usuario que creó este ode, "+ creadores[0] +" está eliminado, vamos a eliminar el ode con identificador "+form.getIdODE());
						String[] identificadores=new String[1];
						identificadores[0]=form.getIdODE();
						Boolean vuelta = this.getSrvPublicacionService().eliminarIdODEForzado(identificadores);
						if(!vuelta){
							logger.error("No se ha podido eliminar el ode "+form.getIdODE()+" para un usuario eliminado");
						}
					}
					else{
						try {
							
							resultado = this.getSrvPublicacionService().rechazar(form.getIdODE(),
									usuario, form.getComentarios(), form.getTitulo());
							
						} catch (Exception ex) {
							logger.error("ERROR (excepción) rechazando el ODE con IdODE[" + form.getIdODE() + "] usuario["
									+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
									+ "] y titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: " + ex.getMessage());
							throw new ValidatorException("{gestorFlujo.excepcion.rechazar.rechazar}");
						}

						if (!resultado.getIdResultado().equals("0.0")) {
							logger.error("Error rechazando para publicacion el ODE con IdODE[" + form.getIdODE() + "] usuario["
									+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
									+ "] y titulo[" + form.getTitulo() + "]");
		
							saveErrorMessage(request, "gestorFlujo.error.rechazar", new String[] { form.getTitulo(),
									resultado.getDescripcion() });
		
						} else {
//							Una vez que he rechazado el ODE, mando el correo al usuario de creacion del ode
							//Busco al creador del ode, y obtengo sus datos para recoger su mail.
							UsuarioVO datosUsuarioCreacion = this.getSrvAdminUsuariosService().obtenerDatosUsuario(creadores[0]);
							if(datosUsuarioCreacion.getRecibirCorreoPublicacion()){
								CorreoOdeVO correoODE = new CorreoOdeVO();
								correoODE.setHrefLogo(ImagenesAgrega.urlHrefLogo());
								logger.info("HrefLogo ["+ correoODE.getHrefLogo() +"]");
								correoODE.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
								logger.info("UrlImagenLogo ["+ correoODE.getUrlImagenLogo() +"]");
								correoODE.setTituloOde(form.getTitulo());
								logger.info("Titulo ["+ correoODE.getTituloOde() +"]");
								correoODE.setComentario(form.getComentarios());
								logger.info("Comentario ["+ correoODE.getComentario() +"]");
								correoODE.setTo(new String[] {datosUsuarioCreacion.getEmail()});
								logger.info("TO [" + Arrays.toString(correoODE.getTo()) +"]");
								correoODE.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
								logger.info("From ["+ correoODE.getFrom()+"]");
								correoODE.setIdiomaCorreo(LdapUserDetailsUtils.getIdioma());
								logger.info("IdiomaCorreo [" + correoODE.getIdiomaCorreo() +"]");
								this.getSrvCorreo().rechazoODE(correoODE);
							}
							logger.info("Rechazado correctamente: ODE con IdODE[" + form.getIdODE() + "] usuario["
									+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
									+ "] y titulo[" + form.getTitulo() + "]");
						}
					}
			} else {
				logger.warn("Longitud de comentario no válida al rechazar el ODE con IdODE[" + form.getIdODE()
						+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
						+ "] y titulo[" + form.getTitulo() + "]; longitud: " + form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
			}
		} else {
			logger.warn("No se han introducido comentarios al rechazar el ODE con IdODE[" + form.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
					+ "] y titulo[" + form.getTitulo() + "]");
			throw new ValidatorException("{gestorFlujo.comentario.obligatorio}");
		}
	}







    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.rechazarPendCatalogacionController#cargaFormularioRechazar(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.CargaFormularioRechazarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFormularioRechazar(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientesCatalogacion.rechazar.CargaFormularioRechazarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    }

}