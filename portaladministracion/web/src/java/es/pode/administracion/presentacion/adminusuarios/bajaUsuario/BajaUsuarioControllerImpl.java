// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.bajaUsuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ContactoVO;
import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.adminusuarios.negocio.servicios.ValidaBajaUsuarioVO;
import es.pode.publicacion.negocio.servicios.SrvAlbumService;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.adminusuarios.negocio.servicios.SrvPerfilPublicoService;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioController
 */
public class BajaUsuarioControllerImpl extends BajaUsuarioController {

	private static final long serialVersionUID = -8187243137070988635L;
	private static Logger log = Logger.getLogger(BajaUsuarioControllerImpl.class);

    /**
         * @see es.pode.admainistracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioController#obtenerIdiomas(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.bajaUsuario.ObtenerIdiomasForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void obtenerIdiomas(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.bajaUsuario.ObtenerIdiomasForm form,
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

    	} catch (Exception e) {
    		log.error("Error: " + e);
    		throw new ValidatorException("{errors.borrarUsuario}");
    	}

    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioController#bajaUsuario(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void bajaUsuario(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.bajaUsuario.BajaUsuarioForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	    
		String listaId = request.getParameter("listaId");
		if(log.isDebugEnabled())log.debug("los ids de usuario que se quieren eliminar son " + listaId);
		Object[] objeto = listaId.split(" ");
	
		ResourceBundle ficheroRecursos = null;
		try {
/*			
			ArrayList <UsuarioVO> usuariosABorrar= new ArrayList<UsuarioVO>();
			Long [] ids = obtenerIds(objeto);
			for (int i=0; i<objeto.length; i++) {
				UsuarioVO usuarioABorrar=this.getSrvAdminUsuariosService().descripcionUsuario(ids[i]);
				usuariosABorrar.add(usuarioABorrar);
			}

			// Eliminamos los contactos generados por otros usuarios hacia el/los usuario/s eliminado/s
			//Para ello sigo los siguientes pasos:
			//1- Obtenemos todos los usuaarios de la plataforma
			UsuarioVO[] usuarioVO = this.getSrvAdminUsuariosService().listarUsuarios();
			for(int i=0; i<usuarioVO.length; i++) {
				//2- Obtengo los contactos de cada usuario de la plataforma
				ContactoVO[] contactos = this.getSrvPerfilPublico().listarContactosDeAgenda(usuarioVO[i].getUsuario());
				for(int j=0; j<contactos.length; j++) {
					//3- Miro si algun contacto de los usuarios de la plataforma es uno de los usuarios que estan borrandose
					//y en caso de ser asi lo elimino como contacto
					for(int k=0; k<usuariosABorrar.size(); k++) {
						if (usuariosABorrar.get(k).getUsuario().equalsIgnoreCase(contactos[j].getUsuarioContacto())) {
				 	       Long[] idContactos=new Long[1];
				 	       idContactos[0]=contactos[j].getId();
				 	       ResultadoOperacionVO[] r = this.getSrvPerfilPublico().eliminarContactoDeAgenda(idContactos, usuarioVO[i].getUsuario());
				 	       
				 	       //Revisamos el resultado de la operacion
				 	       if(r!=null && r.length>0){
				 	    	   ResultadoOperacionVO resultado=r[0];
				 	    	   Boolean texto=resultado.getResultado();
				 	    	   if(texto.equals(Boolean.FALSE)){
				 	    		   log.error("Se ha producido un error al eliminar al usuario "+usuariosABorrar.get(k).getUsuario()+" como contacto del usuario"+usuarioVO[i].getUsuario());
				 	    		   throw new ValidatorException("{errors.borrarUsuario}");
				 	    	   }
				 	       } else {
			 	    		   	log.error("Se ha producido un error al eliminar al usuario "+usuariosABorrar.get(k).getUsuario()+" como contacto del usuario"+usuarioVO[i].getUsuario()+". Respuesta es null o vacia");
				 			    throw new ValidatorException("{errors.borrarUsuario}");
				 	       }
						}
					}
				}
			}
		    
			//Marcamos a los usuario a eliminar como dados de baja
		    //String login = LdapUserDetailsUtils.getLogin();
			
*/			
			
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		 //   UsuarioVO emailAdmin = srvAdminUsuariosService.obtenerDatosUsuario(login);
		    ValidaBajaUsuarioVO validaBaja = srvAdminUsuariosService.bajaUsuario(obtenerIds(objeto));
		    Locale locale = request.getLocale();
		    ficheroRecursos = this.getFicherRecursos(locale);
		    form.setUsuariosDeleted(validaBaja.getItemsDeleted());
		    form.setDescripcionBaja(ficheroRecursos.getString(validaBaja.getDescripcion()));
		    form.setUsuariosDeleted(validaBaja.getItemsDeleted());
/*
		    // Despues de eliminar (dar de baja) al usuario tenemos que eliminar mas datos relacionados
			if(validaBaja.getDescripcion().equals("ok.borrarUsuarios")){
				
				UsuarioVO[] usuariosBorrados=validaBaja.getItemsDeleted();
				String[] listaUsuarios=new String[usuariosBorrados.length];
				for(int i=0;i<usuariosBorrados.length;i++){
					String idUsuario=usuariosBorrados[i].getUsuario();
					listaUsuarios[i]=idUsuario;
				}

			    // Eliminamos los odes en estado creado o rechazado, es decir los que tenía en su carpeta personal, si la baja ha ido bien
			    // También tenemos que eliminar todos sus albumes si la eliminacion de usuario ha ido bien.
				SrvPublicacionService srvPublicacion = this.getSrvPublicacionService();
				Boolean vuelta = srvPublicacion.eliminarOdesUsuarios(listaUsuarios);
				log.debug("Hemos borrado todos los odes de todos los usuarios ? "+vuelta);
				SrvAlbumService srvAlbumes=this.getSrvAlbumService();
				Boolean eliminadosUsuarios=srvAlbumes.eliminarAlbumesUsuarios(listaUsuarios);
				log.debug("Hemos borrado todos los albumes de todos los usuarios ? "+eliminadosUsuarios);
				
			}
*/		   
		} catch (Exception e) {
		    log.error("Se ha producido un error al eliminar el usuario " + e);
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
		ResourceBundle ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
    }
}