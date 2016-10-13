/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.estadisticas;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.auditoria.negocio.servicios.NumeroOperacionesVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.estadisticas.EstadisticasProperties;
import es.pode.soporte.estadisticas.EstadisticasPropertiesImpl;



/**
 * @see es.pode.presentacion.estadisticas.EstadisticasController
 */
public class EstadisticasControllerImpl extends EstadisticasController
{


	protected static Logger logger = Logger.getLogger(EstadisticasControllerImpl.class);
	
	

    /**
     * @see es.pode.presentacion.estadisticas.EstadisticasController#cargarDatos(org.apache.struts.action.ActionMapping, es.pode.presentacion.estadisticas.CargarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatos(
    		ActionMapping mapping, 
    		CargarDatosForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	VisualizadorSession sesion=this.getVisualizadorSession(request);
    	
        String identificador =sesion.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	if(	!sesion.isVerEstadisticas())
    	{
    		logger.error("El ODE no se puede visualizar porque el usuario no está logado ");
			request.setAttribute("codigo_error", "noautenticado"); 
    		throw new NoAutenticadoException("Usuario No Autenticado");
    	}
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////			RETORNO			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	odeSesion.setRetorno("Estadisticas");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////ESTADISTICAS DEL ODE		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try{
			NumeroOperacionesVO[] operaciones = this.getSrvAuditoriaServicio().obtenNumeroOperaciones(identificador);
			String[] descargas = EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_DESCARGA).split(",");
			int numDescargas=0;
			for (int i=0;i<operaciones.length;i++){
				for(int j=0;j<descargas.length;j++){
					if (operaciones[i].getOperacion().equals(descargas[j])){
						numDescargas = numDescargas + operaciones[i].getNumeroOperaciones();
						logger.debug("DetallarControllerImpl - buscarDetalleODE: La estadística de "+descargas[j]+" es "+operaciones[i].getNumeroOperaciones());
					}
				}
				if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_VISTO))){
					Integer veces= operaciones[i].getNumeroOperaciones();
					form.setEstVecesConsultado(veces.toString());
				}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_PREVISUALIZADO))){
					Integer veces= operaciones[i].getNumeroOperaciones();
					form.setEstVecesPrevisualizado(veces.toString());
				}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_ENVIO))){
					Integer veces= operaciones[i].getNumeroOperaciones();
					form.setEstVecesEnviado(veces.toString());
				}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_EMBED))){
        			Integer veces= operaciones[i].getNumeroOperaciones();
	        		form.setEstVecesEmbed(veces.toString());
	        	}

			}
			form.setEstVecesDescargado(String.valueOf(numDescargas));
			logger.debug("DetallarControllerImpl - buscarDetalleODE Estadísticas: Descargado ["+numDescargas+"] Visto ["+form.getEstVecesConsultado()+"] Previsualizado ["+form.getEstVecesPrevisualizado()+"] Enviado ["+form.getEstVecesEnviado()+"]");
		}catch(Exception ex){
			form.setEstVecesConsultado("");
			form.setEstVecesDescargado("");
			form.setEstVecesEnviado("");
			form.setEstVecesPrevisualizado("");
			form.setEstVecesEmbed("");
			logger.error("VisualizadorImpl - ERROR: Error al obtener la estadística del ODE para el identificador de ode=["+sesion.getIdentificador()+"]",ex);
		}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////// CARGA DESTACADO		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
	        if(sesion.isVerFavorito())
	        {
				boolean esFavorito= false;
				try{
					esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(identificador, sesion.getUsuario());
				}catch (Exception e) {
					logger.error("error al acceder al servicio PerfilPublico ", e);
				}
				sesion.setFavorito(esFavorito);
	        }
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			
			if(sesion.isVerValorar())
			{
				String usuario="";
				if(sesion.isIdentidadFederada())
				{
		    		URL urlServer= new URL(sesion.getNodoOrigen());
					usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
				}else
				{
					usuario= sesion.getUsuario();
				}
				
				try{
					odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usuario, identificador, odeSesion.getIdiomaCat()));
					odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(identificador));
				}catch (Exception e) {
					logger.error("error al acceder al servicio de valoración " , e);
					odeSesion.setValoracion("0.0");
					odeSesion.setValorado(true);
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//				CARGAR VALORES EN EL FORMULARIO PARA MOSTRAR EL MENU
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			form.setDatosSalida(odeSesion.getOrganizaciones());
			form.setLocalizacion(odeSesion.getLocalizadorCont());
	    	form.setIdSeleccionado(odeSesion.getIdSeleccionado());
	    	form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
	    	form.setUsuario(sesion.getUsuario());
	    	form.setIdentificador(identificador);
	    	form.setTituloOde(odeSesion.getTituloOde());
	    	form.setSecuencia(odeSesion.isSecuencia());
	    	form.setIdiomaCat(odeSesion.getIdiomaCat());
	    	form.setRetorno(odeSesion.getRetorno());
     }
}