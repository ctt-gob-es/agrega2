/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.DesasociarUsuarioDeGrupoController
 */
public class DesasociarUsuarioDeGrupoControllerImpl extends DesasociarUsuarioDeGrupoController
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DesasociarUsuarioDeGrupoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.DesasociarUsuarioDeGrupoController#desasociarUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.DesasociarUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void desasociarUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.DesasociarUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String usuario=form.getUsuario();
        String[] usuarios=new String[1];
        usuarios[0]=usuario;
        String nombre=form.getNombre();
        ResultadoOperacionVO[] vuelta = this.getSrvPerfilPublico().eliminarUsuarioDeGrupo(usuarios, nombre);
        if(vuelta!=null && vuelta.length>0){
        	ResultadoOperacionVO resultado=vuelta[0];
        	Boolean texto=resultado.getResultado();
        	if(texto.equals(Boolean.TRUE)){
				form.setResultado("OK");
			}
			else{
				form.setResultado("FALLO");
			}
        }
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.DesasociarUsuarioDeGrupoController#obtenerUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.ObtenerUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarUsuarioDeGrupo.ObtenerUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	String usuario=form.getUsuario();
	    	String nombre=form.getNombre();
	    	logger.info("El usuario que hemos recibido es "+usuario+" para eliminarlo del grupo "+nombre);
    	}catch(Exception e){
    		logger.error("Error al obtener los datos del usuario");
    	}
     }





	public String obtenerAccion(ActionMapping mapping, ObtenerAccionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String accion="Cancelar";
	   	 try{
		     	// nothing to be done for this operation, there are no properties that can be set
		     	String actionAceptar=form.getAccionAceptar();
		     	String actionCancelar=form.getAccionCancelar();
		     	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

	            ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		     	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarios.aceptar")))){
		     		accion="Aceptar";
		     	}else if((actionCancelar!=null && !actionCancelar.equals("") && actionAceptar.equals(i18n.getString("usuarios.cancelar")))){
		     		accion="Cancelar";
		     	}
		     	logger.info("La accion que recibimos es "+accion);
	   	 }catch(Exception e){
	   		 logger.error("Error al obtener la accion "+accion);
	   	 }
	        return accion;
	}









}