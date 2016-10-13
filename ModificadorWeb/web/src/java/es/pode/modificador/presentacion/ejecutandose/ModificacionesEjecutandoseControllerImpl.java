/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.ejecutandose;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.ModificacionVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.modificador.presentacion.ejecutandose.ModificacionesEjecutandoseController
 */
public class ModificacionesEjecutandoseControllerImpl extends ModificacionesEjecutandoseController
{






    /**
     * @see es.pode.modificador.presentacion.ejecutandose.ModificacionesEjecutandoseController#recuperarModificaciones(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.ejecutandose.RecuperarModificacionesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarModificaciones(ActionMapping mapping, es.pode.modificador.presentacion.ejecutandose.RecuperarModificacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ModificacionVO[] modificacionesEnEjecucion = this.getSrvHerramientaModificacion().obtenerModificacionesEnEjecucion();
		List modificacionesPendientesList=new ArrayList();
		for(int i=0; i<modificacionesEnEjecucion.length;i++)
		{
			modificacionesPendientesList.add(i, modificacionesEnEjecucion[i]);
		}
		form.setModificaciones(modificacionesPendientesList);
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");

     }



}