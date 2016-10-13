/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo;

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
 * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.DesasociarOdeDeGrupoController
 */
public class DesasociarOdeDeGrupoControllerImpl extends DesasociarOdeDeGrupoController
{



	private static Logger logger = Logger.getLogger(DesasociarOdeDeGrupoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.DesasociarOdeDeGrupoController#desasociarOde(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.DesasociarOdeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void desasociarOde(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.DesasociarOdeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	String usuario=LdapUserDetailsUtils.getUsuario();
	    	String id_mec=form.getId_mec();
	    	String titulo=form.getTitulo();
	    	String[] ids=new String[1];
	    	ids[0]=id_mec;
	    	String[] titulos=new String[1];
	    	titulos[0]=titulo;
	    	String nombre=form.getNombre();
	    	logger.info("Vamos a desasociar del grupo "+nombre+" el ode "+titulo);
	        ResultadoOperacionVO[] vuelta = this.getSrvPerfilPublico().desasociarOdeDeGrupo(ids, nombre, usuario, titulos);
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
    	}catch(Exception e){
    		logger.error("Error al desasociar el ode");
    	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.DesasociarOdeDeGrupoController#obtenerOde(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.ObtenerOdeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOde(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.desasociarOdeDeGrupo.ObtenerOdeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	String id_mec=form.getId_mec();
	    	String titulo=form.getTitulo();
	    	String nombre=form.getNombre();
	    	logger.info("El identificador que hemos recibido es "+id_mec+" y el titulo "+titulo+
	    			" para el grupo "+nombre);
    	}catch(Exception e){
    		logger.error("Error al obtener los datos del ode");
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
	     	}else if((actionCancelar!=null && !actionCancelar.equals("")&& actionCancelar.equals(i18n.getString("usuarios.cancelar")) )){
	     		accion="Cancelar";
	     	}
	     	logger.info("La accion que recibimos es "+accion);
   	 }catch(Exception e){
   		 logger.error("Error al obtener la accion "+accion);
   	 }
        return accion;
	}









}