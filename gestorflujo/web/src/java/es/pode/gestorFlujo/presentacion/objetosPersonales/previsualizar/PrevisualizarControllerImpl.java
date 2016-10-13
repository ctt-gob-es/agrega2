// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.PrevisualizarController
 */
public class PrevisualizarControllerImpl extends PrevisualizarController
{

	private static Logger logger = Logger.getLogger(PrevisualizarControllerImpl.class);

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.PrevisualizarController#tieneSecuencia(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.TieneSecuenciaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final boolean tieneSecuencia(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.TieneSecuenciaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String idOde = form.getIdOde();
    	
    	try {
			Boolean secuencia = this.getSrvEntregarService().tieneSecuencia(
					idOde);
			return secuencia.booleanValue();
		} catch (Exception e) {
			logger.error("Error al consultar secuencia: "+e);
			return false;
		}
    }

    
    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.PrevisualizarController#previsualizar(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.PrevisualizarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void previsualizar(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.previsualizar.PrevisualizarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String tipoVisualizador = form.getTipoVisualizador();
		if(logger.isDebugEnabled()) logger.debug("tipoVisualizador = " + tipoVisualizador );
		
        String comunidadAgrega = form.getComunidadAgrega();
		if(logger.isDebugEnabled()) logger.debug("ComunidadAgrega = " + comunidadAgrega );
		
		String id =form.getIdOde();
		if(logger.isDebugEnabled()) logger.debug("id = " + id );

		String url = request.getSession().getServletContext().getInitParameter("url_visualizador");
		String llamada = null;
		if(tipoVisualizador==null) {// No ha pasado por la pantalla de selección de visualizador por no tener secuencia
			llamada = url + "?identificador=" + id + "&secuencia=false&comunidadAgrega="+comunidadAgrega;
		} else if(StringUtils.equalsIgnoreCase("adl", tipoVisualizador)) {//ADL
			url = request.getSession().getServletContext().getInitParameter("url_visualizador_adl");
			llamada = url + "?identificador=" + id; 
		} else {//Agrega
			llamada = url + "?identificador=" + id + "&secuencia=true&comunidadAgrega="+comunidadAgrega;
		}
	
		
		// URLs tipo host/agrega
//		llamada = "http://localhost:8080/" + llamada;
		llamada = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+llamada;
		
		logger.info("Llamando a previsualizador con URL = " + llamada);
		response.sendRedirect(llamada);
     }



	public void tipoVisualizador(
			ActionMapping mapping,
			TipoVisualizadorForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{
		form.setTipoVisualizador(LdapUserDetailsUtils.getVisualizador());
		logger.debug("tipoVisualizador " + form.getTipoVisualizador());
	}



}