/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.valorar;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.presentacion.valorar.ValorarController
 */
public class ValorarControllerImpl extends ValorarController
{

	protected static Logger logger = Logger.getLogger(ValorarControllerImpl.class);

    public final void valorar(
    		ActionMapping mapping, 
    		ValorarForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	try{
	    	VisualizadorSession sesion  = this.getVisualizadorSession(request);
	    	
	    	String identificador = form.getIdentificador();
	    	logger.debug("Retorno es "+form.getRetorno());

	    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);

	    	if(!sesion.isVerValorar())
	    	{
	    		logger.error("El ODE no se puede visualizar porque el usuario no está logado ");
				request.setAttribute("codigo_error", "noautenticado"); 
	    		throw new NoAutenticadoException("Usuario No Autenticado");
	    	}
	    	

	    	String usuario="";
	    	if(sesion.isIdentidadFederada())
			{
	    		URL urlServer= new URL(sesion.getNodoOrigen());
				usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
			}else
			{
				usuario = LdapUserDetailsUtils.getLogin();
			}
	    	
	    	this.getSrvValoracionService().introducirValoracion(Float.parseFloat(form.getValoracion()), identificador, usuario ,odeSesion.getIdiomaCat());
    	}catch (Exception e) {
    		logger.error("error en valorarController: " , e);
		}
    }




	@Override
	public String retorno(
			ActionMapping mapping, 
			RetornoForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception
	{
		return form.getRetorno();
	}




}