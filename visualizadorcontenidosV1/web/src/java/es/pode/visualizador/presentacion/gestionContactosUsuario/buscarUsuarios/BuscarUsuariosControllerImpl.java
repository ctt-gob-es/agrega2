/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionContactosUsuario.buscarUsuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.ContactoAsociadoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.BuscarGruposPublicosControllerImpl;



/**
 * @see es.pode.visualizador.presentacion.gestionContactosUsuario.buscarUsuarios.BuscarUsuariosController
 */
public class BuscarUsuariosControllerImpl extends BuscarUsuariosController
{

	private static Logger logger = Logger.getLogger(BuscarGruposPublicosControllerImpl.class);




    /**
     * @see es.pode.visualizador.presentacion.gestionContactosUsuario.buscarUsuarios.BuscarUsuariosController#obtenerUsuariosPorNombre(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionContactosUsuario.buscarUsuarios.ObtenerUsuariosPorNombreForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerUsuariosPorNombre(ActionMapping mapping, es.pode.visualizador.presentacion.gestionContactosUsuario.buscarUsuarios.ObtenerUsuariosPorNombreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String nombre=form.getTextoBusqueda();
    	String textoBuscado=form.getTextoBuscado();
    	if(nombre!=null && nombre.equals("")){
    		nombre=textoBuscado;
    	}
    	logger.info("Vamos a buscar usuarios de nombre "+nombre);
    	try{
    		String usuario=LdapUserDetailsUtils.getUsuario();
    		
    		 ContactoAsociadoVO[] usuariosBusqueda = this.getSrvPerfilPublico().buscarUsuariosPorNombre(usuario,nombre);
    		 if(usuariosBusqueda!=null){
	    		 for(int i=0;i<usuariosBusqueda.length;i++){
	  	    	   String imagenUsuario=usuariosBusqueda[i].getImagenUsuario();
	  	    	 if(imagenUsuario.equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
    				 usuariosBusqueda[i].setImagenUsuario(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
  		       	}else{
  		       		usuariosBusqueda[i].setImagenUsuario(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+imagenUsuario);
  		       	}
	  	       }
    		 }
    		form.setUsuariosAsArray(usuariosBusqueda);
    		form.setTextoBusqueda(null);
    		form.setUsuarioConsulta(usuario);
    		form.setTextoBuscado(nombre);
    	}catch(Exception e){
    		logger.error("Error: "+e);
    		throw new ValidatorException("{grupos.publicos.buscar.error}");
    	}
     }





	public void obtenerTextoUsuario(ActionMapping mapping,
			ObtenerTextoUsuarioForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String texto=form.getTextoBusqueda();
		if(texto==null || texto.equals("") || texto.length()==0){
			logger.error("Hay que introducir algún criterio de búsqueda");
			throw new ValidatorException("{grupos.error.grupo.vacio}");
		}else{
			form.setTextoBusqueda(texto);
		}
		
	}


//	public void getIds(ActionMapping mapping, GetIdsForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//		    List lista = ((ListarUsuariosEncontradosAnadirFormImpl) form).getIdsRowSelection();
//		    if (lista == null) {
//	
//		    	throw new ValidatorException("{errors.asociarContacto.idNulo}");
//		    } 
//		    form.setIdCollection(lista);
//       
//       } catch (ValidatorException e){
//    	   throw e;
//	    	   
//       } catch (Exception e){
//    	   
//    	   logger.error("Se ha producido un error al intentar recuperar los ids para añadir: " +e);
//    	   throw new ValidatorException ("{errors.asociarContacto.idError}");
//    	   
//	 }
//	}









}