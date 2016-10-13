/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoPublicoDeUsuarioController
 */
public class DesasociarGrupoPublicoDeUsuarioControllerImpl extends DesasociarGrupoPublicoDeUsuarioController
{


	private static Logger logger = Logger.getLogger(DesasociarGrupoPublicoDeUsuarioControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoPublicoDeUsuarioController#desasociarGrupo(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void desasociarGrupo(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
 	       Long id=form.getId();
 	       Long[] identificadores=new Long[1];
 	       identificadores[0]=id;
 	       String nombre=form.getNombre();
 	       String usuario=LdapUserDetailsUtils.getUsuario();
 	       ResultadoOperacionVO resultado = this.getSrvPerfilPublico().desasociarGrupoDeUsuario(id, usuario);

 	       	Boolean texto=resultado.getResultado();
 	       	if(texto.equals(Boolean.TRUE)){
 					form.setResultado("OK");
 				}
 				else{
 					form.setResultado("FALLO");
 				}
     	}catch(Exception e){
     		logger.error("Error al desasociar el grupo publico");
     	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoPublicoDeUsuarioController#obtenerAccion(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerAccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerAccion(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerAccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String accion="Cancelar";
	   	 try{
		     	// nothing to be done for this operation, there are no properties that can be set
		     	String actionAceptar=form.getAccionAceptar();
		     	String actionCancelar=form.getAccionCancelar();
		     	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

	            ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);

		     	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarios.aceptar")))){
		     		accion="Aceptar";
		     	}else if((actionCancelar!=null && !actionCancelar.equals("")&& actionCancelar.equals(i18n.getString("usuarios.cancelar")) )){
		     		accion="Cancelar";
		     	}
		     	logger.info("La accion que recibimos es "+accion);
	   	 }catch(Exception e){
	   		 logger.error("Error al obtener la accion "+accion);
	   	 }
	        return accion;
    }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoPublicoDeUsuarioController#obtenerGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
 	       Long id=form.getId();
 	       String nombre=form.getNombre();
 	       logger.info("El id que recibimos es "+id+" y el nombre del grupo "+nombre);
     	}catch(Exception e){
     		logger.error("Error al obtener el grupo publico");
     	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.DesasociarGrupoPublicoDeUsuarioController#obtenerVuelta(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerVueltaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerVuelta(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.desasociarGrupoPublicoDeUsuario.ObtenerVueltaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String vuelta=form.getVuelta();
    	String nombre=form.getNombre();
		String volver="Lista";
		if(vuelta!=null && !vuelta.equals("") && vuelta.equals("Itinerario")){
			volver="Itinerario";
		}
		return volver;
    }









}