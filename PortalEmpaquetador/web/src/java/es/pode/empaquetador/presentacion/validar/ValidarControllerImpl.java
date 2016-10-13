/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.validar;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.ValidaVO;
import es.pode.empaquetador.presentacion.GestorSesion;



/**
 * @see es.pode.empaquetador.presentacion.validar.ValidarController
 */
public class ValidarControllerImpl extends ValidarController
{
	private static Logger logger = Logger.getLogger(ValidarControllerImpl.class);
	private static GestorSesion gs = new GestorSesion();
	private static String EMPAQUETADOR_AVANZADO = "Avanzado";
	private static String EMPAQUETADOR_BASICO = "Basico";


	@Override
	public String esAvanzado(ActionMapping mapping, EsAvanzadoForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		String resultado = null;		
		if(gs.esAvanzado()) resultado=EMPAQUETADOR_AVANZADO;
		else resultado=EMPAQUETADOR_BASICO;

		logger.debug("Tipo de empaquetador detectado: " + resultado);
		return resultado;
	}
	

	/*
     * Metodo que valida la catalogacion y la licencia de un ODE.
     * @see es.pode.empaquetador.presentacion.validar.ValidarController#validar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.validar.ValidarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	@Override
	public void validar(ActionMapping mapping, ValidarForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//Inicializamos
		form.setMensajesErrorCatalogacion(null);	
		form.setHayErrorCatalogacion(false);
		form.setMensajesErrorLicencias("");
		form.setHayErrorLicencias(false);
		form.setHayAlgunError(false);
		
    	OdeVO ode=this.getEmpaquetadorSession(request).getOde();
    	
    	//Validamos catalogacion
    	ValidaVO validacionMan=null;
		try {
			//Creamos un backup del manifiesto y guardamos cambios
			this.getSrvGestorManifestService().guardarManifiesto(getEmpaquetadorSession(request).getIdLocalizador(), true);
			validacionMan = this.getSrvGestorManifestService().validar(getEmpaquetadorSession(request).getIdLocalizador(), "COMPLETA");			
		} catch (Exception e) {
			//Sobreescribimos el manifiesto con su backup (rollback)
			this.getSrvGestorManifestService().restaurarManifiesto(getEmpaquetadorSession(request).getIdLocalizador());
			logger.error("Error al validar el ode " + ode.getIdentifier(),e);
		}
		
		//Cogemos el mensaje de la validacion de catalogacion
		if (validacionMan.getEsValidoManifest()==false) {
			String mensajesString = validacionMan.getResultadoValidacion();
			String[] mensajes = mensajesString.split(";");		
			form.setMensajesErrorCatalogacion(mensajes);
			form.setHayErrorCatalogacion(true);				
		} 
		
		//Validamos licencias
    	ValidarLicenciasVO validacionLic=null;
		try {
			//Ajustamos la URL. Esto no deberia ser necesario. Lo es debido a que 
			//dependiendo del empaquetador y de si es un ODE nuevo o se intenta modificar
			//uno preesxistente la URL se ajusta de diferentes formas en otras partes del 
			//codigo.
			String urlLocalizador=ode.getLocalizadorURL();
			if(!ode.getLocalizadorURL().startsWith("uploads")) 
				urlLocalizador="uploads"+ode.getLocalizadorURL();
		
			validacionLic = this.getSrvGruposLicencias().validarLicencias(urlLocalizador);
			this.getSrvGestorManifestService().restaurarManifiesto(getEmpaquetadorSession(request).getIdLocalizador());
		} catch (Exception e) {
			//Sobreescribimos el manifiesto con su backup (en caso de que exista)
			this.getSrvGestorManifestService().restaurarManifiesto(getEmpaquetadorSession(request).getIdLocalizador());
			logger.error("Error detectado al validar licencias del ode " + ode.getIdentifier(),e);
		}

		//Cogemos el mensaje de la validacion de catalogacion
    	//Este mensaje contendra la licencia que provoca conflictos con las demas
		if (validacionLic.isResultado()==false) {
			form.setMensajesErrorLicencias(validacionLic.getLicenciaAdicional());
			form.setHayErrorLicencias(true);
		} 

		form.setHayAlgunError(form.getHayErrorLicencias()||form.getHayErrorCatalogacion());
	}

}