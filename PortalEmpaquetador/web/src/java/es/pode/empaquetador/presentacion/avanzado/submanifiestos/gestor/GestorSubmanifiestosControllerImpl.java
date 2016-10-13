// license-header java merge-point
package es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;

/**
 * @see es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.GestorSubmanifiestosController
 */
public class GestorSubmanifiestosControllerImpl extends
		GestorSubmanifiestosController {

	private static final String EXCEPTION = "{portal_empaquetado.exception}";
	private static final String DESAGREGAR = "portal_empaquetado_gestorSubman.desagregar";
	private static final String AGREGAR = "portal_empaquetado_gestorSubman.agregar";
	private static final String ELIMINAR = "portal_empaquetado_gestorSubman.eliminar";
	private static final String APPLICATION_RESOURCES = "application-resources";
	private GestorSesion gestorSesion = new GestorSesion();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @return the gestorSesion
	 */
	public GestorSesion getGestorSesion() {
		return gestorSesion;
	}

	/**
	 * @param gestorSesion
	 *            the gestorSesion to set
	 */
	public void setGestorSesion(GestorSesion gestorSesion) {
		this.gestorSesion = gestorSesion;
	}

	public final void recuperarDatos(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.RecuperarDatosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		gestorSesion.refrescarObjetosSesion(request);
		EmpaquetadorSession session = this.getEmpaquetadorSession(request);
		
		if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
    		//inicializamos session
    		this.getEmpaquetadorSession(request).setMensajeAsistente("");
    	}
		
		if (session.getSubmanifestPath() != null) {
			form.setBarraSubmanifiestos(session.getSubmanifestPath());
			OdeVO ode = (OdeVO) session.getSubmanifestPath().get(
					session.getSubmanifestPath().size() - 1);
			form.setSubmanifiestosAsArray(ode.getSubmanifiestos());
		}
		
		logger.debug("Mensaje compatibilidad licencias es "+session.getMensajeCompatibilidadLicencias());
		form.setMostradoMensajeCompatibilidadLicencia(true);
		if(!session.isMostradoMensajeCompatibilidadLicencia()) {
			logger.debug("No se ha mostrado el mensaje de compatibilidad de licencias");
			form.setMostradoMensajeCompatibilidadLicencia(false);
			session.setMostradoMensajeCompatibilidadLicencia(true);
		}
	}

	/**
	 * @see es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.GestorSubmanifiestosController#submit(org.apache.struts.action.ActionMapping,
	 *      es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.SubmitForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void submit(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.SubmitForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,
				locale);

		String accion = form.getAction();
		if ((accion.equals(i18n.getString(ELIMINAR)))|| (accion.equals(i18n.getString(DESAGREGAR)))) {
			if (form.getIdentifierRowSelection() != null
					&& form.getIdentifierRowSelection().size() > 0) {
				form.setIdentificadores(form.getIdentifierRowSelection());
			} else {
				throw new ValidatorException("{exportar.subirArchivo}");
			}
		}
	}

	public final java.lang.String selectAction(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.SelectActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * Metodo de decision para el action. Analiza los parametros
		 * actionSubmit (value de los botones submit) para redirigir al caso de
		 * uso correspondiente. El actionSubmit llegara internacionalizado, por
		 * lo que es necesario acceder al ResouceBundle para obtener el valor
		 * correcto en la comparacion.
		 */

		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,
				locale);
		
		if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
    		//inicializamos session
    		this.getEmpaquetadorSession(request).setMensajeAsistente("");
    	}
		
		if (form.getAction() == (null)) {
			throw new ValidatorException(EXCEPTION);
		}

		else if (actionSubmit.equals(i18n
				.getString(AGREGAR))) {
			result = "Agregar";
		} else if (actionSubmit.equals(i18n
				.getString(DESAGREGAR))) {
			result = "Desagregar";
		} else if (actionSubmit.equals(i18n
				.getString(ELIMINAR))) {
			result = "Eliminar";
		} else {
			logger.error("El valor del submit no es correcto (actionSubmit = "
						+ actionSubmit + ")");
		}

		return result;
	}

	public final void navegarSubmanifiesto(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.avanzado.submanifiestos.gestor.NavegarSubmanifiestoForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			boolean encontrado = false;
			String identificador = form.getIdentifier();
			EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
			List subman = sesEmpaq.getSubmanifestPath();

			// ultimo ODE
			OdeVO ode = (OdeVO) subman.get(subman.size() - 1);
			Integer index = subman.size() - 1;
			String identificadorOde = index==0?sesEmpaq.getIdLocalizador():ode.getIdentifier();

			// cojo los hijos del ultimo ODE
			OdeVO[] hijos = ode.getSubmanifiestos();
			// los recorro viendo si coinciden
			for (int i = 0; (encontrado == false && i < hijos.length); i++) {
				if (hijos[i].getIdentifier().equals(identificador)) {
					encontrado = true;
					Boolean nuevoOde = this
							.getSrvGestorManifestService()
							.crearReferenciaEnCache(identificadorOde, identificador);
					if (nuevoOde.booleanValue() == false) {
						throw new ValidatorException(
								"{portal_empaquetado_gestorSubman.exception}");
					} else {
						subman.add(hijos[i]);

					}
				}
			}
			if (encontrado == false) {
				// recorro el sesEmpaq.getSubmanifestPath();
				for (int i = 0; (i < subman.size() && !encontrado ); i++) {
					OdeVO submanifiesto = (OdeVO) subman.get(i);
					if (submanifiesto.getIdentifier().equals(identificador)) {
						// Voy a borrar a partir del identificador encontrado
						encontrado = true;

						for (int j = i + 1; j < subman.size(); j++) {
							OdeVO elemento = (OdeVO) subman.get(j);
							this.getSrvGestorManifestService().descargarManifest(
									elemento.getIdentifier());
							subman.remove(j);
						}

					}
				}
				sesEmpaq.setSubmanifestPath(subman);
			}
	}

//	@Override
//	public void consultarAsistente(ActionMapping mapping, ConsultarAsistenteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
////		Actualizamos los objetos de session
//		GestorSesion gestorSesion =  new GestorSesion();
//		gestorSesion.refrescarObjetosSesion(request);
//
//		EmpaquetadorSession empSes= this.getEmpaquetadorSession(request);
//		GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
//		
//		AsistenteEmpaquetador asBasico = new AsistenteEmpaquetador();
//		String resultadoAsistente=asBasico.asistenteAvanzado(empSes, sesArch);
//		form.setConsultaAsistente(resultadoAsistente!=null?resultadoAsistente:"");
//	}
//	
	
}
