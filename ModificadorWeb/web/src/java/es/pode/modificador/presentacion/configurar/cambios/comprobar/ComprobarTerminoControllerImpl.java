// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.comprobar;

import java.util.ResourceBundle;

import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.FormularioModificarVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarTerminoController
 */
public class ComprobarTerminoControllerImpl extends ComprobarTerminoController
{


    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarTerminoController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String origen = form.getAction();
		String result="";
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		// Introduzco en el objeto de sesion la opcion elegida
		
		
		if (origen.equals(i18n.getString("comprobarTermino.aceptar")) )
		{
			
			result="Aceptar";
			
		} 
		else if (origen.equals(i18n.getString("comprobarTermino.volver"))) 
		{
			result = "Volver";
		} 
		else if (origen.equals(i18n.getString("comprobarTermino.cancelar"))) 
		{
			result = "Cancelar";
		} 
		

         return result;
    }


    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarTerminoController#comprobar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void comprobar(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	this.getCambioSession(request).setAlcance(form.getAlcance());
    	this.getCambioSession(request).setExprReg(form.getExprReg());
    	this.getCambioSession(request).setIdioma(form.getIdioma());
    	this.getCambioSession(request).setValor(form.getValor());     
 
     }


    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.comprobar.ComprobarTerminoController#recuperarFormulario(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.RecuperarFormularioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarFormulario(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.comprobar.RecuperarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	FormularioModificarVO formulario = this.getSrvHerramientaModificacion().obtenerFormularioModificar(this.getCambioSession(request).getIdLomTerm());
    	
    	if(this.getCambioSession(request).getAlcance()==null)
    	{
    		form.setAlcance(Boolean.TRUE);
    	}
    	else
    	{
    		form.setAlcance(this.getCambioSession(request).getAlcance());		
    	}
        
    	if(this.getCambioSession(request).getExprReg()==null)
    	{
    		form.setExprReg(Boolean.FALSE);
    	}
    	else
    	{
    		form.setExprReg(this.getCambioSession(request).getExprReg());
    	}
    	
        form.setFormulario(formulario);
        form.setIdioma(this.getCambioSession(request).getIdioma());
        form.setValor(this.getCambioSession(request).getValor());
        form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");

     }


	public void borrarSesion(ActionMapping mapping, BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		this.removeCambioSession(request);
		
	}


	public void recuperaIdTermino(ActionMapping mapping, RecuperaIdTerminoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String IdLomTerm = this.getCambioSession(request).getIdLomTerm();
		form.setIdTermino(IdLomTerm);
		
	}









}