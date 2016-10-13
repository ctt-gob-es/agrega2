// license-header java merge-point
package es.pode.empaquetador.presentacion.basico.principal;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.RecursoVO;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;

/**
 * @see es.pode.empaquetador.presentacion.basico.principal.PrincipalController
 */
public class PrincipalControllerImpl extends PrincipalController {
	private static Logger logger = Logger
			.getLogger(PrincipalControllerImpl.class);

	private GestorSesion gs = new GestorSesion();

	/**
	 * @see es.pode.empaquetador.presentacion.basico.principal.PrincipalController#recuperarDatos(org.apache.struts.action.ActionMapping,
	 *      es.pode.empaquetador.presentacion.basico.principal.RecuperarDatosForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void recuperarDatos(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.basico.principal.RecuperarDatosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * Refresco los objetos de sesion para poder acceder a nuevos grupos
		 * creados en Agregar
		 */
		//BORRAR
		logger.debug("Entra en recuperarDatos");
		gs.refrescarObjetosSesion(request);
		String idGrupo = form.getIdentifier();
		if (idGrupo == null) {
			logger.error("Identifier es null en el form");
			throw new Exception("Identifier es null en el form");
		}
		GrupoVO[] hijos = gs.buscarHijosIdCollection(request);
		GrupoVO seleccionado = null;
		for (int i = 0; i < hijos.length && seleccionado == null; i++) {
			if (idGrupo.equals(hijos[i].getIdentifier())) {
				seleccionado = hijos[i];
				if (logger.isDebugEnabled())
					logger
							.debug("Encontrado grupoVO seleccionado para principal");
			}
		}
		if (seleccionado == null) {
			logger.error("No se encuentra el grupo " + idGrupo);
			throw new Exception("No se encuentra el grupo " + idGrupo);
		}
		String idRecurso = seleccionado.getElementoReferenciado();
		if (idRecurso == null) {
			logger.error("El grupo " + idGrupo
					+ " no tiene elemento referenciado");
			throw new Exception("El grupo " + idGrupo
					+ " no tiene elemento referenciado");
		}
		RecursoVO recurso = gs.buscarRecurso(request, idRecurso);
		form.setPrincipal(recurso.getHref());
		form.setFicherosAsArray(recurso.getFileList());
		form.setIdRecurso(idRecurso);
		if (logger.isDebugEnabled())
			logger.debug("RecuperarDatos finalizado con salida : "
					+ form.getPrincipal() + " : " + form.getFicherosAsArray()
					+ " : " + form.getIdRecurso());
	}

	

	

	/**
	 * @see es.pode.empaquetador.presentacion.basico.principal.PrincipalController#fijarPrincipal(org.apache.struts.action.ActionMapping,
	 *      es.pode.empaquetador.presentacion.basico.principal.FijarPrincipalForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void fijarPrincipal(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.basico.principal.FijarPrincipalForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idRecurso = form.getIdRecurso();
		String principal = form.getPrincipal();
		String action = form.getAction();
		String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
		
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		
		if (logger.isDebugEnabled())
			logger
					.debug("fijarPrincipal iniciado con datos : form.getPrincipal() "
							+ form.getPrincipal()
							+ " : form.getIdRecurso() "
							+ form.getIdRecurso()
							+ " : form.getAction() "
							+ form.getAction());
		if (idRecurso == null || principal == null || action == null) {
			logger
					.error("Uno de los atributos del form es nulo : form.getPrincipal() "
							+ form.getPrincipal()
							+ " : form.getIdRecurso() "
							+ form.getIdRecurso()
							+ " : form.getAction() "
							+ form.getAction());
			throw new Exception(
					"Uno de los atributos del form es nulo : form.getPrincipal() "
							+ form.getPrincipal() + " : form.getIdRecurso() "
							+ form.getIdRecurso() + " : form.getAction() "
							+ form.getAction());
		}
		
		if(action.equalsIgnoreCase(i18n.getString("gestor_basico.principal.aceptar"))) {
			RecursoVO recurso = gs.buscarRecurso(request, idRecurso);
			if (!principal.equals(recurso.getHref())) {
				recurso.setHref(principal);
				this.getSrvGestorManifestService().modificarRecurso(idOde, recurso);
				if(logger.isDebugEnabled()) logger.debug("Principal de recurso " + idRecurso + " modificado a " + principal);
			} else {
				if(logger.isDebugEnabled()) logger.debug("No llamo al servicio: se ha seleccionado el mismo principal que ya habia");
			}
		}
	}

}