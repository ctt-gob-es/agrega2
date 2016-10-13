// license-header java merge-point
package es.pode.empaquetador.presentacion.salir;


import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.ValidaVO;
import es.pode.empaquetador.presentacion.DecisorOffline;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.avanzado.organizaciones.crear.CrearOrganizacionSession;
import es.pode.empaquetador.presentacion.avanzado.organizaciones.elementos.crearelemento.CrearElementoSession;
import es.pode.empaquetador.presentacion.avanzado.recursos.crear.CrearRecursoAvanzadoSession;
import es.pode.empaquetador.presentacion.avanzado.recursos.crear.archivos.CrearRecursoArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.salir.SalirController
 */
public class SalirControllerImpl extends SalirController {

	private static Logger logger = Logger.getLogger(SalirControllerImpl.class);
	
	private static GestorSesion gs = new GestorSesion();
	
	public final void cerrarSesion(ActionMapping mapping,es.pode.empaquetador.presentacion.salir.CerrarSesionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
		String url = sesEmpaq.getUrlCerrar();
		
		/*
		 * URLs tipo host/agrega
		 */
		url="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+(url.startsWith("/")?"":"/")+url;
		
		/*
		 * Eliminado en Agrega Offline v1.1. Conservo codigo para identificar posibles fallos.
		 * 
		 * fjespino
		 */
//		 if(DecisorOffline.esOffline()) {
//			 
//			 String parametros = "?idODE="+this.getEmpaquetadorSession(request).getOde().getIdentifier() + "&guardar="+form.getGuardar();
//			 url = url + parametros;
//		 }
				
		List submanifestPath = sesEmpaq.getSubmanifestPath();
    	
    	String identificador="";
    	OdeVO elemento=null;
    	if(submanifestPath.size()>1)
    	{
    		for(int i=submanifestPath.size()-1;i>0;i--)
    		{
    			elemento=(OdeVO) submanifestPath.get(i);
    			identificador=elemento.getIdentifier();
    			this.getSrvGestorManifestService().descargarManifest(identificador);
    		}
    	}
		
		this.getSrvGestorManifestService().terminarEdicion(sesEmpaq.getIdLocalizador());
		if(!this.getEmpaquetadorSession(request).isGuardadoPrimeraVez()) {
			if(logger.isDebugEnabled()) logger.debug("No se ha guardado este ODE por primera vez. Elimino el ODE de la tabla de publicacion");
			try {
				this.getSrvGestorManifestService().eliminarODE(sesEmpaq.getIdLocalizador());
			} catch (Exception e) {
				logger.error("No se pudo eliminar " + sesEmpaq.getIdLocalizador(),e);
			}
		}
		
		if(this.getCrearElementoSession(request)!=null)
			request.getSession().removeAttribute(CrearElementoSession.SESSION_KEY);
		
		if(this.getEmpaquetadorSession(request)!=null)
			request.getSession().removeAttribute(EmpaquetadorSession.SESSION_KEY);
		
		if(this.getCrearRecursoArchivosSession(request)!=null)
			request.getSession().removeAttribute(CrearRecursoArchivosSession.SESSION_KEY);
		
		if(this.getCrearRecursoAvanzadoSession(request)!=null)
			request.getSession().removeAttribute(CrearRecursoAvanzadoSession.SESSION_KEY);
		 
		if(this.getCrearOrganizacionSession(request)!=null)
			request.getSession().removeAttribute(CrearOrganizacionSession.SESSION_KEY);
			
		if(logger.isDebugEnabled()) logger.debug("Saliendo a " + url);
		response.sendRedirect(url);
		
	}


	public final boolean validacionOk(ActionMapping mapping,
			es.pode.empaquetador.presentacion.salir.ValidacionOkForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OdeVO ode = this.getEmpaquetadorSession(request).getOde();
		ValidaVO validacion= this.getEmpaquetadorSession(request).getValidacion();
		
		// Pasamos una validacion completa
		//ValidaVO validaVO = this.getSrvEmpaquetadorBasicoService().validar(ode,
			//	"COMPLETA");
		if(validacion==null)
		{
			validacion = this.getSrvGestorManifestService().validar(getEmpaquetadorSession(request).getIdLocalizador(), "BINARIA");
			this.getEmpaquetadorSession(request).setValidacion(validacion);
		}
		java.util.List mensajesError = null;
		if(validacion.getResultadoValidacion()!=null) {
			String[] mensajes = validacion.getResultadoValidacion().split(";");
			mensajesError = java.util.Arrays.asList(mensajes);
		}
		
		form.setMensajesError(mensajesError);
		return validacion.getEsValidoManifest().booleanValue();
	}

	
	public final void guardar(ActionMapping mapping,
			es.pode.empaquetador.presentacion.salir.GuardarForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
//    	if(validacion.getEsValidoManifest().booleanValue())
//		{
	    	String identificador = this.getEmpaquetadorSession(request).getIdLocalizador();
			if(logger.isDebugEnabled()) logger.debug("Guardando el ODE " + identificador);
			
        	if(DecisorOffline.esOffline()){
        		this.getSrvGestorManifestService().guardarManifiesto(identificador, false);        		
        	}else{
        		
        		String sEspacioLibre = this.getEmpaquetadorSession(request).getEspacioLibre();
        		String sEspacioOdeInicio = this.getEmpaquetadorSession(request).getEspacioOde();
        		if(logger.isDebugEnabled()) logger.debug("Espacio libre: " + sEspacioLibre + "Espacio ODE inicialmente:" + sEspacioOdeInicio);
        		
        		if(org.apache.commons.lang.StringUtils.isNumeric(sEspacioLibre) &&
        				org.apache.commons.lang.StringUtils.isNumeric(sEspacioOdeInicio)){
		        	
        			Long espacioLibre = new Long(sEspacioLibre);
		        	Long espacioOdeInicio = new Long(sEspacioOdeInicio);
		        	Boolean hayEspacio = this.getSrvGestorArchivosService().consultaCuota(identificador, espacioLibre, espacioOdeInicio);
		        	
		        	if(hayEspacio.booleanValue()) {
		        		if(this.getSrvGestorArchivosService().verficarExisteBackup(identificador))
		        			this.getSrvGestorManifestService().guardarManifiesto(identificador, false);
		        		else
		            		throw new ValidatorException("{gestor.avanzado.guardar.edicion.finalizada.en.otro.sitio}");
		        	}else{
		        		throw new ValidatorException("{gestor.avanzado.guardar.noEspacioEnCuota}");
		        	}
		        	
        		}else{
	        		if(this.getSrvGestorArchivosService().verficarExisteBackup(identificador))
	        			this.getSrvGestorManifestService().guardarManifiesto(identificador, false);
	        		else 
	            		throw new ValidatorException("{gestor.avanzado.guardar.edicion.finalizada.en.otro.sitio}");
	        	}
        	}
//			this.getSrvGestorManifestService().guardarManifiesto(identificador);
			if(!this.getEmpaquetadorSession(request).isGuardadoPrimeraVez()) {
				this.getEmpaquetadorSession(request).setGuardadoPrimeraVez(true);
				if(logger.isDebugEnabled()) logger.debug("Cambiado flag guardadoPrimeraVez");
			}
//		}
//    	this.getEmpaquetadorSession(request).setValidacion(validacion);
			
    	form.setGuardar(String.valueOf(true));
	}


	
	public final void restaurarBackup(ActionMapping mapping, RestaurarBackupForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String idElemento=getEmpaquetadorSession(request).getIdLocalizador();
		//Si hay otro sitio desde el que se esta editando el ODE es posible que no exista backup
		if(this.getSrvGestorArchivosService().verficarExisteBackup(idElemento))
			this.getSrvGestorArchivosService().restaurarBackup(idElemento);
		form.setGuardar(String.valueOf(false));
	}


	
	public final String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{portal_empaquetado.exception}");
		}
			
		else if (actionSubmit.equals(i18n.getString("empaquetador.presentacion.salir.BotonCancelar")) )
		{
			result = "Cancelar";
		} 
		else if (actionSubmit.equals(i18n.getString("empaquetador.presentacion.salir.BotonSi"))) 
		{
			result = "Guardar";
		} 
		else if (actionSubmit.equals(i18n.getString("empaquetador.presentacion.salir.BotonNo"))) 
		{
			result = "Salir";
		} 
		if (result==null) { 
		
			Logger.getLogger(this.getClass()).error("El valor del submit no es correcto (actionSubmit = "
							+ actionSubmit + ";");
		}
         return result;
	}


	public String esAvanzado(ActionMapping mapping, EsAvanzadoForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String resultado = null;

		if (logger.isDebugEnabled())
			logger.debug("Recupero datos de autenticacion");

		if (gs.esAvanzado())
			resultado = "Avanzado";
		else
			resultado = "Basico";

		if (logger.isDebugEnabled())
			logger.debug("Tipo de empaquetador detectado por validar : "
					+ resultado);
		return resultado;
	}

}