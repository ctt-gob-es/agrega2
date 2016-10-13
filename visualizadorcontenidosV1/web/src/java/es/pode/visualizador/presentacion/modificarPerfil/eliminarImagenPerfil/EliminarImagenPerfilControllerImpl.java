// license-header java merge-point
package es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenPerfilController
 */
public class EliminarImagenPerfilControllerImpl extends EliminarImagenPerfilController
{




	private static Logger logger = Logger.getLogger(EliminarImagenPerfilControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenPerfilController#eliminarImagen(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarImagen(ActionMapping mapping, es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	 String usuario=LdapUserDetailsUtils.getUsuario();
         Boolean vuelta = this.getSrvPerfilPublico().cambiarImagenPorElDefecto(usuario);
         
         	if(vuelta.equals(Boolean.TRUE)){
 				form.setResultado("OK");
 			}
 			else{
 				form.setResultado("FALLO");
 			}

    	}catch(Exception e){
    		logger.error("Error al eliminar la imagen del usuario");
    	}
     }









}