/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioCorreoVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.publicarAutonomosController
 */
public class publicarAutonomosControllerImpl extends publicarAutonomosController
{
	private Logger logger = Logger.getLogger(publicarAutonomosControllerImpl.class);
	private final String splitter = ";";
	private final String SIN_ERRORES = "0.0";
	/**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.publicarAutonomos#proponerODEPublicarAutonomo(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.ProponerODEPublicarAutonomoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void proponerODEPublicarAutonomo(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.ProponerODEPublicarAutonomoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ResultadoOperacionVO resultado = null;
		if(logger.isDebugEnabled()) logger.debug("Publicando autonomamente un ODE con titulo: " + form.getTitulo() + "e identificador: "+ form.getIdODE());
		
		if(form.getSeleccion()==null || form.getSeleccion().equals("off")){
			//Primero validar  que se aceptan las condiciones legales
			logger.warn("No se ha seleccionado la aceptacion de las condiciones de publicacion autonoma["+form.getSeleccion()+"] con identificador ["
					+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");
			throw new ValidatorException("{gestorFlujo.legal.obligatorio}"); 
		}
		if((form.getComentarios()!= null)||!(form.getComentarios().trim().length()>0)){
//			Ponemos longitud de 2500 pero en el mensaje 2000, para que el usuario no se sienta estafado con falta de caracteres. 
			if(form.getComentarios().length()>2500){
				logger.warn("Longitud de comentario no válida proponiendo para la publicación autónoma el ODE con IdODE["
						+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario()+ "] y titulo[" + form.getTitulo());
				throw new ValidatorException("{gestorFlujo.comentario.long}");
			}
		}

		try {
			resultado = this.getSrvPublicacionService().publicarAutonomo(form.getIdODE(), LdapUserDetailsUtils.getUsuario(), form.getTitulo(), form.getComentarios());
			//Enviamos un correo a aquellos usuarios que me tienen como contacto, informando de la publicación del ODE
			
		} catch (Exception ex) {
			logger.error("ERROR (excepción) publicando autonomamente el ODE con IdODE[" + form.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: ", ex);
			throw new ValidatorException("{gestorFlujo.excepcion.publicar.publicar}");
			}

		if (!resultado.getIdResultado().equals(SIN_ERRORES)) {
			logger.error("Error proponiendo para catalogación el ODE con IdODE[" + form.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]. Error:["
					+ resultado.getDescripcion() + "]");

			form.setMensajes(resultado.getDescripcion().substring(0,resultado.getDescripcion().length()-1).split(splitter));

		} else {
			logger.info("Publicación autónoma correcta: ODE con IdODE[" + form.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");
			try {
				CorreoOdeVO correo = new CorreoOdeVO();
				//Lista de usuarios, que me tienen como contacto en su lista.
				UsuarioCorreoVO[] usuariosCorreo = this.getSrvPerfilPublico().listarUsuariosConContacto(LdapUserDetailsUtils.getUsuario());
				if(usuariosCorreo!=null && !(usuariosCorreo.length>0)){
					logger.debug("Hay " +usuariosCorreo.length+ " contactos del usuario: "+ LdapUserDetailsUtils.getUsuario());
					//Recojo en un String[] los mails de los usuariosCorreo
					String[] mailUsuarios = new String[usuariosCorreo.length];
					for (int i = 0; i < usuariosCorreo.length; i++) {
						mailUsuarios[i]=usuariosCorreo[i].getEmail();
					}
					correo.setTo(mailUsuarios);
				}
				correo.setHrefLogo(ImagenesAgrega.urlHrefLogo()); 
				correo.setTituloOde(form.getTitulo());
				correo.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
				correo.setUrlFicha(ODEPublico.urlPrevisualizaODEPublicadoAutonomo(form.getIdODE(), LdapUserDetailsUtils.getIdioma()));
				//TODO revisar la URL de la imagen
				correo.setUrlImagen(ImagenesAgrega.urlImagenDefectoAutopublicado()); 
				correo.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
				correo.setUsuario(LdapUserDetailsUtils.getUsuario());
				this.getSrvCorreo().contactoAutopublicaODE(correo);
				
			} catch (Exception e) {
				logger.error("ERROR (excepción) enviando el correo a los usuarios q tienen " + LdapUserDetailsUtils.getUsuario() + "]como contacto, al publicar autonomamente el ode ["
						+ form.getIdODE()+ "] de titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: ", e);
				//Mando un mensaje de exito, porque el ode si se ha autopublicado de forma correcta, y el usuario no tiene porque saber que hay 
				//otros usuarios que le tienen como contacto, el problema ha sido envio de correo, no publicación autónoma.
				throw new ValidatorException("{gestorFlujo.publicarAutonomo.exito}");
			}
			String[] textoVacio=new String[0]; //Pasamos el mensaje de error vacio.
			form.setMensajes(textoVacio);
		}	
	}


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.publicarAutonomos#analizaCheck(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.AnalizaCheckForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String analizaCheck(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.AnalizaCheckForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	return(form.getSeleccion());
    }


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.publicarAutonomos#cargaFormProponerPubliAutonoma(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.CargaFormProponerPubliAutonomaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFormProponerPubliAutonoma(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.publicarAutonomos.CargaFormProponerPubliAutonomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
     }


	
	public String comprobarOrigen(ActionMapping mapping, ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
		Long idAlbum = sesion.getIdAlbum();
		String retorno = sesion.getRetorno();
		
		
		logger.debug("El origen es ["+ retorno +"], y el idAlbum ["+ idAlbum +"]");
		if(retorno != null && retorno.equals(ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES)){
			retorno = ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES;
		}
		if(retorno!= null && retorno.equals(ListarAlbumControllerImpl.ODES_POR_ALBUM)){
			retorno = ListarAlbumControllerImpl.ODES_POR_ALBUM;
		}
		logger.debug("Creamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
	}


}