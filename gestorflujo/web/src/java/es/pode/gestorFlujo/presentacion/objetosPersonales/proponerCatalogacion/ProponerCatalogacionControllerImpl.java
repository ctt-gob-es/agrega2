// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.ProponerCatalogacionController
 */
public class ProponerCatalogacionControllerImpl extends ProponerCatalogacionController {

	private Logger logger = Logger.getLogger(ProponerCatalogacionControllerImpl.class);
	private final String splitter = ";";

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.ProponerCatalogacionController#proponerODECatalogacion(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.ProponerODECatalogacionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void proponerODECatalogacion(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.ProponerODECatalogacionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResultadoOperacionVO resultado = null;
		if(logger.isDebugEnabled())
		logger
				.debug("Proponiendo para catalogación ODE con titulo: " + form.getTitulo() + " idODE: "
						+ form.getIdODE());
		
		if (form.getComentarios() != null) {
			// ponemos 2500 pero en el mensaje 2000 para que el usuario no se
			// sienta estafado con falta de caracteres
			
			if ((form.getComentarios().trim().length() > 0) && (form.getComentarios().length() < 2500)) {
				if(form.getSeleccion()==null || form.getSeleccion().equals("off")){//Primero validar  que se aceptan las condiciones legales
					logger.warn("No se ha seleccionado la aceptacion de las condiciones legales ["+form.getSeleccion()+"] con identificador ["
							+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");
					throw new ValidatorException("{gestorFlujo.legal.obligatorio}");
				}
				try {
					resultado = this.getSrvPublicacionService().proponerCatalogacionNuevaVersion(form.getIdODE(),
							LdapUserDetailsUtils.getUsuario(), form.getComentarios(), form.getTitulo(),form.getSolicitarNuevaVersion());
//					Abro la sesion para recoger el espacio libre que tiene el usuario.
					logger.debug("Consulta de espacio libre");
					ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
					Long espacioLibre = sesion.getEspacioLibre();
//					Consulto el espacio que ocupaba el ODE que acabo de eliminar. 
					Long espacioODE = this.getSrvLocalizadorService().consultaEspacioLocalizador(form.getIdODE());
//					Se lo añado al espacio libre y se lo vuelvo a pasar a la sesion.
					espacioLibre = espacioLibre+espacioODE;
					sesion.setEspacioLibre(espacioLibre);
				} catch (Exception ex) {
					logger.error("ERROR (excepción) proponiendo para catalogación el ODE con IdODE[" + form.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]" + "\nEXCEPCION: ", ex);
					throw new ValidatorException("{gestorFlujo.excepcion.proponer.proponer}");
				}

				if (!resultado.getIdResultado().equals("0.0")) {
					logger.error("Error proponiendo para catalogación el ODE con IdODE[" + form.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]. Error:["
							+ resultado.getDescripcion() + "]");

					// throw new ValidatorException(resultado.getDescripcion());
//					saveErrorMessage(request, "gestorFlujo.error.proponer.validar", new String[] { form.getTitulo(),
//							resultado.getDescripcion() });
					form.setMensajes(resultado.getDescripcion().substring(0,resultado.getDescripcion().length()-1).split(splitter));

				} else {
					logger.info("Propuesto para catalogación correctamente: ODE con IdODE[" + form.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");
					String[] textoVacio=new String[0];
					form.setMensajes(textoVacio);
					// saveSuccessMessage(request,
					// "gestorFlujo.error.proponer.validar",new
					// String[]{form.getTitulo(),resultado.getDescripcion()});
				}
			} else {
				logger.warn("Longitud de comentario no válida proponiendo para catalogación el ODE con IdODE["
						+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
						+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]; longitud: "
						+ form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
			}
		} else {
			logger.warn("No se han introducido comentarios proponiendo para catalogación el ODE con IdODE["
					+ form.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + form.getTitulo() + "]");
			throw new ValidatorException("{gestorFlujo.comentario.obligatorio}");
		}
		
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.ProponerCatalogacionController#cargaFormularioProponerCatalogacion(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.CargaFormularioProponerCatalogacionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void cargaFormularioProponerCatalogacion(
			ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPersonales.proponerCatalogacion.CargaFormularioProponerCatalogacionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	}

	public String analizaCheck(ActionMapping mapping, AnalizaCheckForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return(form.getSeleccion());
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
		return retorno;
	}
	

	@Override
	public String revisarEstadoVersionado(ActionMapping mapping,
			RevisarEstadoVersionadoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (form.getEstaVersionandose()==true)
			return "ODE_VERSIONANDOSE";
		else
			return "ODE_NO_VERSIONANDOSE";
	}

	
	@Override
	public String ajustarParametrosModoPublicacion(ActionMapping mapping,
			AjustarParametrosModoPublicacionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		java.util.Locale locale = (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources", locale);
		String opcion = form.getAction();
		if (opcion.equals(i18n.getString("gestorFlujo.aceptar"))) 
			return "Aceptar";
		else
			return "Cancelar";
	}

	
}