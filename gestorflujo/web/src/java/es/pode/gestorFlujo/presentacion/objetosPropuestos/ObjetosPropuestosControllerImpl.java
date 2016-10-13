/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPropuestos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionVOComparator;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 *  * @see es.pode.gestorFlujo.presentacion.objetosPropuestos.ObjetosPropuestosController
 */
public class ObjetosPropuestosControllerImpl extends ObjetosPropuestosController {

	private static final long serialVersionUID = 1L;

	protected final static String URL_SEPARATOR = "/";
	protected final static String URL_IMAGEN ="static/img/azul/";
	private Logger logger = Logger.getLogger(ObjetosPropuestosController.class);

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPropuestos.ObjetosPropuestosController#cargarODESPropuestos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPropuestos.CargarODESPropuestosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void cargarODESPropuestos(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPropuestos.CargarODESPropuestosForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		SrvPublicacionService publi = this.getSrvPublicacionService();
		if(logger.isDebugEnabled())
		logger.debug("Cargando odes propuestos catalogación y publicación para: " + LdapUserDetailsUtils.getUsuario());
		
		try {
			
			int recorrido = publi.obtenODEsPropuestosPorUsuario(LdapUserDetailsUtils.getUsuario()).length;
			String urlImagen = crearUrlImagen();
			form.setUrlImagen(urlImagen);
//			Abro la sesion para recoger el espacio libre que tiene el usuario.
			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
			form.setEspacioLibre(sesion.getEspacioLibre());
			if (recorrido > 0) {
				TransicionVO []  odesPropuestos = publi.obtenODEsPropuestosPorUsuario(LdapUserDetailsUtils.getUsuario());
				Arrays.sort(odesPropuestos, new TransicionVOComparator());
//				UtilesODEs.quicksort(odesPropuestos, 0, odesPropuestos.length-1);
				form.setListaODESAsArray(odesPropuestos);
				recorrido = publi.obtenODESPropuestosCatalogacionPorUsuario(LdapUserDetailsUtils.getUsuario()).length;
				if (recorrido > 0) {
					ArrayList collect = new ArrayList(form.getListaODES());
					for (int i = 0; i < recorrido; i++) {
						TransicionVO trVO = publi.obtenODESPropuestosCatalogacionPorUsuario(LdapUserDetailsUtils
								.getUsuario())[i];
						collect.add(trVO);
					}
					form.setListaODES(collect);
				}
			} else
				form.setListaODESAsArray(publi.obtenODESPropuestosCatalogacionPorUsuario(LdapUserDetailsUtils
						.getUsuario()));
			
		} catch (Exception ex) {
			logger.error("Error inesperado cargando objetos propuestos. ", ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
	}
	
	
	public String crearUrlImagen() throws RemoteException, Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append("caja_propuesto.jpg");
		return sb.toString();
	}
}