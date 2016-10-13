// license-header java merge-point
package es.pode.modificador.presentacion.configurar.nombre;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.nombre.ConfigurarNombreController
 */
public class ConfigurarNombreControllerImpl extends ConfigurarNombreController
{

	private final static String MENSAJE_ERROR_DETALLES = "configurarNombre.msgError";
	private static final Logger logger = Logger.getLogger(ConfigurarNombreControllerImpl.class);


    /**
     * @see es.pode.modificador.presentacion.configurar.nombre.ConfigurarNombreController#guardarNombre(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.nombre.GuardarNombreForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardarNombre(ActionMapping mapping, es.pode.modificador.presentacion.configurar.nombre.GuardarNombreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
	    	if(form.getNombre()!=null && !form.getNombre().equals(""))
	    	{
	    		this.getConfigurarModificacionSession(request).setNombre(form.getNombre());
	    	}
	    	else
	    	{
	    		throw new ValidatorException("{configurarNombre.msgError}");
	    	}
     }

    /**
     * @see es.pode.modificador.presentacion.configurar.nombre.ConfigurarNombreController#cerrarSesion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.nombre.CerrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cerrarSesion(ActionMapping mapping, es.pode.modificador.presentacion.configurar.nombre.CerrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	this.removeConfigurarModificacionSession(request);
    }

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = null;
		String action = form.getAction();
		MessageResources resources = MessageResources.getMessageResources("application-resources");
		if(logger.isDebugEnabled()) logger.debug("Action del submit = " + action);
		if(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"nombre.continuar").equals(action)) {
			result ="Continuar";
		} else {
			result = "Cancelar";
		}
		return result;
	}

	public void recuperaNombre(ActionMapping mapping, RecuperaNombreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(this.getConfigurarModificacionSession(request)!= null && this.getConfigurarModificacionSession(request).getNombre()!=null) {
			form.setNombre(this.getConfigurarModificacionSession(request).getNombre());
		}
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
	}

}