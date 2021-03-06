// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.UsuarioVO;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.ListarUsuariosPendientesController
 */
public class ListarUsuariosPendientesControllerImpl extends ListarUsuariosPendientesController
{

	private static Logger log = Logger.getLogger(ListarUsuariosPendientesControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.ListarUsuariosPendientesController#obtenerUsuariosPendientes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.ObtenerUsuariosPendientesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerUsuariosPendientes(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.ObtenerUsuariosPendientesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
    	// el idioma ser� el que tenga en el atributo idioma del usuario
    	// que esta en sesion.
    	try {
    	   
    	    UsuarioVO[] usuarioVO = this.getSrvAdminUsuariosService().listarUsuariosPendientes();
    	    if(log.isDebugEnabled())log.debug("Obtenemos los usuarios pendientes de dar de alta");
    	    form.setUsuariosAsArray(usuarioVO);
    	} catch (Exception e) {
    	    log.error("Error: " + e);
    	    throw new ValidatorException("{errors.listarUsuariosPendientes}");
    	}
     }







    /**
     * @see es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.ListarUsuariosPendientesController#getIdsPendientes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.GetIdsPendientesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getIdsPendientes(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.listarUsuariosPendientes.GetIdsPendientesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    	    List lista = ((FormularioListadoUsuariosPendientesBajaFormImpl) form).getIdRowSelection();
    	    if (lista == null) {

    		throw new ValidatorException("{errors.borrarUsuario.idNulo}");
    	    } else {

    		Long[] usuariosAdmin = this.getSrvAdminUsuariosService().obtenerUsuariosAdministrador();

    		List gruposAdminList = Arrays.asList(usuariosAdmin);

    		int num_Admin = usuariosAdmin.length;
    		int usuarioDeleted = 0;
    		Iterator iter = lista.iterator();
    		while (iter.hasNext()) {
    		    Long identificador = new Long((String) iter.next());
    		    if (gruposAdminList.contains(identificador)) {
    			usuarioDeleted = usuarioDeleted + 1;
    		    }
    		}

    		if (usuarioDeleted == num_Admin) {
    			if(log.isDebugEnabled())log.debug("Se intenta eliminar todos los usuarios que son administrador no se permite");
    		    throw new ValidatorException("{errors.borrarUsuario.todosUsuariosAdmin}");
    		}
    		form.setIds(lista);
    	    }
    	} catch (ValidatorException validator) {
    	    log.error("Se ha producido la siguiente exception " + validator);
    	    throw validator;
    	} catch (Exception e) {
    	    log.error("Se produce una excepcion ", e);
    	    throw new ValidatorException("{errors.borrarUsuario}");
    	}
     }









}