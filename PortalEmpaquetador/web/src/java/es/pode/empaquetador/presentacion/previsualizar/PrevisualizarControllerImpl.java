// license-header java merge-point
package es.pode.empaquetador.presentacion.previsualizar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.previsualizar.PrevisualizarController
 */
public class PrevisualizarControllerImpl extends PrevisualizarController
{
	private static Logger logger = Logger.getLogger(PrevisualizarControllerImpl.class);






    /**
     * @see es.pode.empaquetador.presentacion.previsualizar.PrevisualizarController#previsualizar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.previsualizar.PrevisualizarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void previsualizar(
    		ActionMapping mapping, 
    		PrevisualizarForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		
		String tipoVisualizador = form.getTipoVisualizador();
//		String action = form.getAction();
		if(logger.isDebugEnabled()) logger.debug("tipoVisualizador = " + tipoVisualizador );
		
		OdeVO ode = this.getEmpaquetadorSession(request).getOde();
		String id_temp = this.getSrvGestorManifestService().previsualizarOde(getEmpaquetadorSession(request).getIdLocalizador());
		String id = "";
		if(id_temp != null && id_temp.startsWith("EMP")){
			id = id_temp.substring(3);
		}
		else{
			id = id_temp;
		}
		//String id = this.getSrvEmpaquetadorBasicoService().previsualizarOde(ode);
		String url = request.getSession().getServletContext().getInitParameter(
				"url_visualizador");
		String llamada = null;
		if(tipoVisualizador==null) {// No ha pasado por la pantalla de selección de visualizador por no tener secuencia
			llamada = url + "?identificador=" + id + "&usuario=" + ode.getUsuario() + "&secuencia=false&comunidadAgrega=false"; // Llamo al previsualizador sin secuencia
		} else if(StringUtils.equalsIgnoreCase("adl", tipoVisualizador)) {//ADL
			url = request.getSession().getServletContext().getInitParameter("url_visualizador_adl");
			llamada = url + "?identificador=" + getEmpaquetadorSession(request).getIdLocalizador(); 
		} else {//Agrega
			llamada = url + "?identificador=" + id + "&usuario=" + ode.getUsuario() + "&secuencia=true&comunidadAgrega=false";
		}
		
		// URLs tipo host/agrega
		llamada = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+llamada;
		//20081106 dgonzalezd: Añado parámetro comunidadAgrega=false
		logger.info("Llamando a previsualizador con URL = " + llamada);
		response.sendRedirect(llamada);
     }







    /**
     * @see es.pode.empaquetador.presentacion.previsualizar.PrevisualizarController#tieneSecuencia(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.previsualizar.TieneSecuenciaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tieneSecuencia(
    		ActionMapping mapping, 
    		TieneSecuenciaForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		Boolean conSecuencia = this.getEmpaquetadorSession(request).getOde().getConSecuencia();
		String result = "no";
		if(conSecuencia) result = "si";
		if(logger.isDebugEnabled()) logger.debug("ODE " + getEmpaquetadorSession(request).getIdLocalizador() + (conSecuencia?" si ":" no ") + " tiene secuencia");
		return result;
    }







    /**
     * @see es.pode.empaquetador.presentacion.previsualizar.PrevisualizarController#tipoVisualizador(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.previsualizar.TipoVisualizadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void tipoVisualizador(
    		ActionMapping mapping, 
    		TipoVisualizadorForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
        form.setTipoVisualizador(LdapUserDetailsUtils.getVisualizador());
        logger.debug("tipo visualizador: " + form.getTipoVisualizador());
    }









}