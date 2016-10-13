/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.bajaUsuarioBusqueda;

import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.adminusuarios.negocio.servicios.ValidaBajaUsuarioVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioController
 */
public class BajaUsuarioBusquedaControllerImpl extends BajaUsuarioBusquedaController {

	private static final long serialVersionUID = -6103679000664333924L;
	private static Logger log = Logger.getLogger(BajaUsuarioBusquedaControllerImpl.class);

   
    public final void obtenerIdiomas(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.bajaUsuarioBusqueda.ObtenerIdiomasForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
		try {
	
		   
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		    Iterator<String> iter = (form.getIds()).iterator();
		    UsuarioVO[] usuarios = new UsuarioVO[form.getIds().size()];
		    int i = 0;
		    StringBuilder listaId = new StringBuilder();
		    while (iter.hasNext()) {
	
			Long id = Long.parseLong(iter.next());
			listaId.append(id.toString() + " ");
	
			UsuarioVO usuarioVO = srvAdminUsuariosService.descripcionUsuario(id);
			usuarios[i] = usuarioVO;
			i = i + 1;
		    }
		    form.setListaId(listaId.toString().trim());
		    form.setUsuarios(usuarios);
		    form.setUsuarioBusqueda(form.getUsuarioBusqueda());
	
		} catch (Exception e) {
		    log.error("Error: " + e);
		    throw new ValidatorException("{errors.borrarUsuario}");
		}

    }

   

    private Long[] obtenerIds(Object[] id) {
    	
		Long[] resultado = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
		    resultado[i] = Long.parseLong((String) id[i]);
		}
		return resultado;
    }

    private ResourceBundle getFicherRecursos(Locale locale) {
    	
		ResourceBundle ficheroRecursos = null;
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
    }

	

	
	public void bajaUsuario(ActionMapping mapping, BajaUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String listaId = request.getParameter("listaId");
		if(log.isDebugEnabled())log.debug("los ids de usuario que se quieren eliminar son " + listaId);
		Object[] objeto = listaId.split(" ");
		String usuarioBusqueda = request.getParameter("usuarioBusqueda");
		ResourceBundle ficheroRecursos = null;
		try {
		    
		    String login = LdapUserDetailsUtils.getLogin();
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		 //   UsuarioVO emailAdmin = srvAdminUsuariosService.obtenerDatosUsuario(login);
		    ValidaBajaUsuarioVO validaBaja = srvAdminUsuariosService.bajaUsuario(obtenerIds(objeto));
		    Locale locale = request.getLocale();
		    ficheroRecursos = this.getFicherRecursos(locale);
		    form.setUsuariosDeleted(validaBaja.getItemsDeleted());
		    form.setDescripcionBaja(ficheroRecursos.getString(validaBaja.getDescripcion()));
		    form.setUsuariosDeleted(validaBaja.getItemsDeleted());
		    
		    // Despues de eliminar el usuario tenemos que eliminar sus odes en estado creado o rechazado, es decir los que tenía en su carpeta personal, si la baja ha ido bien
		    
			if(validaBaja.getDescripcion().equals("ok.borrarUsuarios")){
				UsuarioVO[] usuariosBorrados=validaBaja.getItemsDeleted();
				String[] listaUsuarios=new String[usuariosBorrados.length];
				for(int i=0;i<usuariosBorrados.length;i++){
					String idUsuario=usuariosBorrados[i].getUsuario();
					listaUsuarios[i]=idUsuario;
				}
				
				SrvPublicacionService srvPublicacion = this.getSrvPublicacionService();
				Boolean vuelta = srvPublicacion.eliminarOdesUsuarios(listaUsuarios);
				log.debug("Hemos borrado todos los odes de todos los usuarios ? "+vuelta);
			}
			
			form.setUsuarioBusqueda(usuarioBusqueda);
		   
	
		} catch (Exception e) {
		    log.error("Se ha producido un error al eliminar el usuario " + e);
		    throw new ValidatorException("{errors.borrarUsuario}");
		}
		
	}
}