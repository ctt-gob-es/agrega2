// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.eliminar;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.FormularioModificarVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarTerminoController
 */
public class EliminarTerminoControllerImpl extends EliminarTerminoController
{



    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarTerminoController#eliminar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminar(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
       	this.getCambioSession(request).setAlcance(form.getAlcance());
    	this.getCambioSession(request).setExprReg(form.getExprReg());
    	this.getCambioSession(request).setIdioma(form.getIdioma());
    	this.getCambioSession(request).setValor(form.getValor());     
      
     }


    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarTerminoController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	String origen = form.getAction();
    	
		String result="";
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		// Introduzco en el objeto de sesion la opcion elegida
		
		
		if (origen.equals(i18n.getString("eliminarTermino.aceptar")) )
		{
			
			result="Aceptar";
			
		} 
		else if (origen.equals(i18n.getString("eliminarTermino.volver"))) 
		{
			result = "Volver";
		} 
		else if (origen.equals(i18n.getString("eliminarTermino.cancelar"))) 
		{
			result = "Cancelar";
		} 
		

         return result;
    }


    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.eliminar.EliminarTerminoController#recuperarFormulario(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.RecuperarFormularioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarFormulario(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.eliminar.RecuperarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	FormularioModificarVO formulario = this.getSrvHerramientaModificacion().obtenerFormularioModificar(this.getCambioSession(request).getIdLomTerm());

    	if(this.getCambioSession(request).getAlcance()==null )
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
      
    	   form.setIdioma(this.getCambioSession(request).getIdioma());
    	   form.setValor(this.getCambioSession(request).getValor());     
    	   form.setFormularioModificar(formulario);
    	   form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");

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









}