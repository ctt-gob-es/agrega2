/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.share;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;



/**
 * @see es.pode.presentacion.share.ShareController
 */
public class ShareControllerImpl extends ShareController
{
	protected static Logger logger = Logger.getLogger(ShareControllerImpl.class);
	protected final static String URL_SEPARATOR = "/";
	protected final static String FICHA_URL_PREFIX = "ODE";
	protected final static String PREVISUALIZADOR_URL_PREFIX = "visualizar";


    public final void cargarDatos(
    		ActionMapping mapping, 
    		CargarDatosForm form,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	VisualizadorSession sesion=this.getVisualizadorSession(request);
    	
        String identificador =form.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	if(	!sesion.isVerShare())
    	{
    		logger.debug("El ODE no se puede visualizar porque el usuario no está logado ");
			request.setAttribute("codigo_error", "noautenticado"); 
    		throw new NoAutenticadoException("Usuario No Autenticado");
    	}
    	
    	odeSesion.setRetorno("Share");
    	
    	
    	 if(sesion.isVerFavorito())
	        {
				boolean esFavorito= false;
				try{
					esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(sesion.getIdentificador(), sesion.getUsuario()).booleanValue();
				}catch (Exception e) {
					logger.debug("error al acceder al servicio PerfilPublico ", e);
				}
				sesion.setFavorito(esFavorito);
	        }
    	
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(FICHA_URL_PREFIX);
		sb.append(URL_SEPARATOR);
		sb.append(odeSesion.getIdiomaCat());
		sb.append(URL_SEPARATOR);
		sb.append(identificador);
		sb.append(URL_SEPARATOR);
		sb.append(odeSesion.isSecuencia());
		
		form.setUrl(sb.toString());
    	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		valoración
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
				logger.debug("error al acceder al servicio de valoración " , e);
				odeSesion.setValoracion("0.0");
				odeSesion.setValorado(true);
			}
		}

		form.setDatosSalida(odeSesion.getOrganizaciones());
		form.setLocalizacion(odeSesion.getLocalizadorCont());
    	form.setIdSeleccionado(odeSesion.getIdSeleccionado());
    	form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
    	form.setIdentificador(identificador);
    	form.setTituloOde(odeSesion.getTituloOde());
    	form.setSecuencia(odeSesion.isSecuencia());
    	form.setIdiomaCat(odeSesion.getIdiomaCat());
     }
}