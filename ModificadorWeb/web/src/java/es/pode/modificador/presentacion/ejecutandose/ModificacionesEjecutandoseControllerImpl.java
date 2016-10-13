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