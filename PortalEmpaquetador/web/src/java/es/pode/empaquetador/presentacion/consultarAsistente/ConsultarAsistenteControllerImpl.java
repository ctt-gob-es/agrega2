/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.consultarAsistente;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.presentacion.AsistenteEmpaquetador;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.consultarAsistente.ConsultarAsistenteController
 */
public class ConsultarAsistenteControllerImpl extends ConsultarAsistenteController
{
	private static Logger logger = Logger.getLogger(ConsultarAsistenteControllerImpl.class);
	private static final String APPLICATION_RESOURCES = "application-resources";
	
    /**
     * @see es.pode.empaquetador.presentacion.consultarAsistente.ConsultarAsistenteController#consultarAsistente(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.consultarAsistente.ConsultarAsistenteForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consultarAsistente(ActionMapping mapping, es.pode.empaquetador.presentacion.consultarAsistente.ConsultarAsistenteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	todos los datos de comprobaciones se cogen de la session
		EmpaquetadorSession empSes= this.getEmpaquetadorSession(request);
		AsistenteEmpaquetador asBasico = new AsistenteEmpaquetador();
		String returnURL = form.getReturnURL();
        String identificador = this.getEmpaquetadorSession(request).getIdLocalizador();
        
		String resultadoAsistente="";
		if (empSes!=null && "Avanzado".equals(empSes.getTipoEmpaquetador())){
//			Actualizamos los objetos de session
			 GestorSesion gestorSesion =  new GestorSesion();
			gestorSesion.refrescarObjetosSesion(request);
			empSes= this.getEmpaquetadorSession(request);
			GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
			resultadoAsistente=asBasico.asistenteAvanzado(empSes, sesArch);
		} else {
			empSes= this.getEmpaquetadorSession(request);
			GestorArchivosSession sesArch = this.getGestorArchivosSession(request);			
			resultadoAsistente=asBasico.asistenteBasico(empSes, sesArch);			
		}
		ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_RESOURCES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));		
		
		form.setConsultaAsistente(datosResources.getString(resultadoAsistente)!=null?datosResources.getString(resultadoAsistente):"");
		empSes.setMensajeAsistente(datosResources.getString(resultadoAsistente)!=null?datosResources.getString(resultadoAsistente):"");
		
//		la url de vuelta... la formamos como en guardar
   	 	if(returnURL==null || returnURL.trim().equals("")) {
        	String redirect = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+mapping.findForward("gestor.archivos").getPath()+ "?asistente=si";
        	if(logger.isDebugEnabled()) logger.debug("ReturnURL es null en guardar. Redirijo a " +redirect);
        	response.sendRedirect(redirect);
        } else {
			/*
			 * Concateno host y subdominio para URLs tipo http://host/agrega:
			 * returnURL se genera a partir de una html:rewrite, no es necesario
			 * incluir el subdominio, porque de haberlo, ya esta inluido.
			 */
        	returnURL="http://"+LdapUserDetailsUtils.getHost()+returnURL;
        	if (returnURL.contains("?")){
        		returnURL = returnURL + "&asistente=si";
        	} else {
        		returnURL = returnURL + "?asistente=si";
        	}
        	if(logger.isDebugEnabled()) logger.debug("ReturnURL es " + returnURL);
        	response.sendRedirect(returnURL);
     }
  }

}