// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.bajaGrupo;


import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.adminusuarios.negocio.servicios.ValidaBajaGrupoVO;


/**
 * @see es.pode.administracion.presentacion.adminusuarios.bajaGrupo.BajaController
 */
public class BajaControllerImpl extends BajaController {

	private static final long serialVersionUID = 5088024140120860130L;
	private static Logger log = Logger.getLogger(BajaControllerImpl.class);

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.bajaGrupo.BajaController#bajaGrupo(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.bajaGrupo.BajaGrupoForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void bajaGrupo(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.bajaGrupo.BajaGrupoForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
    	
		String listaId = request.getParameter("listaId");
		if(log.isDebugEnabled())log.debug("los ids de usuario que se quieren eliminar son " + listaId);
		Object[] objeto = listaId.split(" ");
		ResourceBundle ficheroRecursos = null;
		try {
		    
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		    ValidaBajaGrupoVO validaBaja = srvAdminUsuariosService.bajaGrupo(obtenerIds(objeto));
		    Locale locale = request.getLocale();
		    ficheroRecursos = this.getFicherRecursos(locale);
		    form.setDescripcionBaja(ficheroRecursos.getString(validaBaja.getDescripcion()));
		    form.setGruposDeleted(validaBaja.getItemsDeleted());
		} catch (Exception e) {
		    log.error("Se ha producido un error al eliminar el usuario " + e);
		    throw new ValidatorException("{errors.borrarUsuario}");
		}

    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.bajaGrupo.BajaController#obtenerIdiomas(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.bajaGrupo.ObtenerIdiomasForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void obtenerIdiomas(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.bajaGrupo.ObtenerIdiomasForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {


    	try {

    		SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();

    		Iterator<String> iter = (form.getIds()).iterator();
    		GrupoVO[] grupos = new GrupoVO[form.getIds().size()];
    		int i = 0;
    		StringBuilder listaId = new StringBuilder();
    		while (iter.hasNext()) {

    			Long id = Long.parseLong(iter.next());
    			listaId.append(id.toString() + " ");

    			GrupoVO grupoVO = srvAdminUsuariosService.descripcionGrupo(id);
    			grupos[i] = grupoVO;
    			i++;
    		}
    		form.setListaId(listaId.toString().toString().trim());
    		form.setGrupos(grupos);
    		if ((listaId.length() == 0) || (grupos.length == 0)) {
    			saveErrorMessage(request, "errors.borrarGrupo");
    		}

    	} catch (Exception e) {
    		log.error("Error: " + e);
    		throw new ValidatorException("{errors.borrarGrupo}");
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

}