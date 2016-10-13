/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.informes.ode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.modificador.presentacion.informes.tarea.InformeTareaControllerImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.informes.ode.InformeODEController
 */
public class InformeODEControllerImpl extends InformeODEController
{
	private static final Logger logger = Logger.getLogger(InformeTareaControllerImpl.class);

    /**
     * @see es.pode.modificador.presentacion.informes.ode.InformeODEController#obtenerDatos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.informes.ode.ObtenerDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatos(ActionMapping mapping, es.pode.modificador.presentacion.informes.ode.ObtenerDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		form.setResultadoTarea(form.getOde().getStatus().getValue());
	    	form.setDescResultado(form.getOde().getMsgError());
	    	form.setNombreTarea(this.getInformeSession(request).getNombreTarea());
//	    	form.setPathInforme("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+ form.getOde().getPathInforme().replaceFirst("uploads/modificador/informes", "informeMetadatos"));
	    	form.setPathInforme(form.getOde().getPathInforme());
	    	Logger.getLogger(this.getClass()).debug("el ode es :"+form.getOde());
	    	form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
    	}
    	catch (Exception e) 
    	{
    		logger.error("No se pudo obtener el ODE debido a "+e.getMessage());
    		throw new ValidatorException("{informeODE.exception}");
		}
    }

	@Override
	public void entregar(ActionMapping mapping, EntregarForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pathInforme = form.getPathInforme();
		if(!DecisorOffline.esOffline()) {
			pathInforme="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+ pathInforme.replaceFirst("uploads/modificador/informes", "informeMetadatos");
		}
		response.sendRedirect(pathInforme);
	}

}