// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientes.Eliminar;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Eliminar.EliminarController
 */
public class EliminarControllerImpl extends EliminarController
{

	//
	private final  String ORIGEN_DE_LA_LLAMADA = "origen_de_la_llamada_eliminar_gestorFlujo"; 
	private final  String IDS_ELIMINAR_GF = "ids_eliminar_gestorFlujo";
	private final  String TITULOS_ELIMINAR_GF = "titulos_eliminar_gestorFlujo";
	private Logger logger = Logger.getLogger(EliminarController.class);
	 



    /**
	 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Eliminar.EliminarController#cargarDatos(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPendientes.Eliminar.CargarDatosForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void cargarDatos(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPendientes.Eliminar.CargarDatosForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Guardamos en la sesi�n el origen de la llamada, para volver al sitio
		// correcto.
		request.getSession().setAttribute(ORIGEN_DE_LA_LLAMADA, ((EliminarCUFormImpl) form).getOrigen());

		try {
			String[] idODEs = ((EliminarCUFormImpl) form).getListODEs();
			if (idODEs != null && idODEs.length > 0) {
				// obtenemos los ids y los titulos de los odes que vamos a
				// eliminar,
				// que vienen concatenados en el mismo string

				String[] titulos = new String[idODEs.length];
				String[] ids = new String[idODEs.length];
				desconcatena(idODEs, ids, titulos);

				request.getSession().setAttribute(IDS_ELIMINAR_GF, ids);
				request.getSession().setAttribute(TITULOS_ELIMINAR_GF, titulos);
				form.setTitulos(titulos);
				form.setSeleccion("ALGUN_ODE_SELEC");
				logger.info("carga de datos eliminar correcta");

			} else {
				logger.warn("No se han seleccionado elementos a eliminar en objetos pendientes para catalogacion");
				form.setSeleccion("NINGUN_ODE_SELEC");
				saveErrorMessage(request, "gestorFlujo.error.eliminar.seleccione");
			}
		} catch (Exception ex) {
			logger.error("Excepcion cargando datos eliminar", ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
	}
    
    // Esta funcion nos ayuda a separar la cadena que le enviamos en titulo e
	// id, de esta
    // menera mejoramos el rendimiento de la aplicaci�n, no hace falta llamadas
	// a servicios
    private void desconcatena(String[] sInicial, String[] sIds, String[] sTitulos)
    {
    	for (int i=0;i<sInicial.length;i++)
    	{
    		sIds [i] = sInicial[i].substring(0,sInicial[i].indexOf(','));
    		sTitulos[i]  = sInicial[i].substring(sInicial[i].indexOf(',')+1);
    	}
    }




	public String analizaSelec(ActionMapping mapping, AnalizaSelecForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//asi de simple
		return(form.getSeleccion());		
	}


	public void eliminarODEs(ActionMapping mapping, EliminarODEsForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SrvPublicacionService publi = this.getSrvPublicacionService();
		
		ArrayList titulosEliminados = new ArrayList();
		String[] idsEliminar = (String[]) request.getSession().getAttribute(IDS_ELIMINAR_GF);
		String[] titulosEliminar = (String[]) request.getSession().getAttribute(TITULOS_ELIMINAR_GF);
		if (idsEliminar != null && idsEliminar.length > 0) {
			ResultadoOperacionVO resultado = new ResultadoOperacionVO();

			for (int i = 0; i < idsEliminar.length; i++) {
				try {
					resultado = publi.eliminar(idsEliminar[i], LdapUserDetailsUtils.getUsuario());
				} catch (Exception ex) {
					logger.error("Excepcion eliminando el ODE con IdODE[" + idsEliminar[i] + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]");
					resultado.setDescripcion("gestorFlujo.error.inesperado");
					resultado.setIdResultado("-1");
				}

				if (resultado.getIdResultado().equalsIgnoreCase("0.0")) {
					logger.info("Eliminado satisfactoriamente ODE: " + (idsEliminar[i]));
					titulosEliminados.add(titulosEliminar[i]);

				} else {
					logger.error("Error eliminando el ODE con IdODE[" + idsEliminar[i] + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]");
					saveErrorMessage(request, "gestorFlujo.error.eliminar.error", new String[] { titulosEliminar[i],
							resultado.getDescripcion() });
				}
			}
		} else {
			// esto es redundante, no deber�a entrar aqu� jam�s
			logger.warn("No se han seleccionado elementos a eliminar");
			saveErrorMessage(request, "gestorFlujo.error.eliminar.seleccione");
		}
		form.setTitulos(titulosEliminados);
	}




}