// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.add.otro;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.add.otro.AddTerminoController
 */
public class AddTerminoControllerImpl extends AddTerminoController
{
	
	private static final Logger logger = Logger.getLogger(AddTerminoControllerImpl.class);

    public void recuperarDatos(ActionMapping mapping, RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
		if(this.getCambioSession(request).getValor()==null) {
			// Recupero la plantilla para insertar un nuevo termino LOM-ES
			String plantilla = this.getSrvHerramientaModificacion().recuperarPlantillaLomes(this.getCambioSession(request).getIdLomTerm());
			if(logger.isDebugEnabled()) logger.debug("Plantilla lomes recuperada:\n" + plantilla);
			form.setValor(plantilla);
		} else {
			form.setValor(this.getCambioSession(request).getValor());
		}
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");

	}

	/**
     * @see es.pode.modificador.presentacion.configurar.cambios.add.otro.AddTerminoController#guardarDatos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.add.otro.GuardarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardarDatos(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.add.otro.GuardarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
		if (form.getValor()!=null)
		{
			
			this.getCambioSession(request).setValorNuevo(form.getValor());
			
		} 
    	
     }

	public void borrarSesion(ActionMapping mapping, BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		this.removeCambioSession(request);
		
	}

	public void recuperarIdTermino(ActionMapping mapping, RecuperarIdTerminoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String IdLomTerm = this.getCambioSession(request).getIdLomTerm();
		form.setIdTermino(IdLomTerm);
		
	}

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String origen = form.getAction();
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		if (origen.equals(i18n.getString("otroAddTermino.aceptar")) )
		{
			return "Aceptar";
		}
		else if (origen.equals(i18n.getString("otroAddTermino.cancelar")) )
		{
			return "Cancelar";
		}
		else
		{
			return "Volver";
		}
	}

}