/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.CambioTypes;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.ConfigurarCambiosController
 */
public class ConfigurarCambiosControllerImpl extends ConfigurarCambiosController
{
	private static final String VACIA = "";
	private final static Logger logger = Logger.getLogger(ConfigurarCambiosControllerImpl.class);
    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.ConfigurarCambiosController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String VOLVER = "Volver";
    	
    	String origen = form.getAction();
    	String opcion=form.getOption();
		String result=VACIA;
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		// Introduzco en el objeto de sesion la opcion elegida
		if(logger.isDebugEnabled()) logger.debug("Opcion elegida " + opcion);
		this.getCambioSession(request).setTipo(opcion);
		if (origen==null)
		{
//			throw new ValidatorException("{configurarCambios.exception}");
			result=VOLVER;
		}
			
		else if (origen.equals(i18n.getString("configurarCambiosController.continuar")) )
		{
			if(opcion.equals(CambioTypes._addition))
			{
				result = "Add";
			}
			else if(opcion.equals(CambioTypes._validation))
			{
				result="Validador";
			}
			else if (opcion.equals(CambioTypes._removal)) 
			{
				result="Eliminar";
			}
			else if (opcion.equals(CambioTypes._check)) 
			{
				result="Comprobar";
			}
			else if (opcion.equals(CambioTypes._modification)) 
			{
				result="Modificar";
			}
			else if(opcion.equals(CambioTypes._transformation))
			{
				result="Transformation";
			} 
			else if (opcion.equals(CambioTypes._metadata))
			{
				result="Metadatos";
			}
			form.setIdTermino(this.getCambioSession(request).getIdLomTerm());
		} 
		else //if (origen.equals(i18n.getString("configurarCambiosController.cancelar"))) 
		{
			result = VOLVER;
		} 
		

         return result;
    }







	public void iniciarCambioSession(ActionMapping mapping, IniciarCambioSessionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String[] tipos=new String[7];
		tipos[0]=CambioTypes._addition;
		tipos[1]=CambioTypes._removal;
		tipos[2]=CambioTypes._modification;
		tipos[3]=CambioTypes._check;
		tipos[4]=CambioTypes._validation;
		tipos[5]=CambioTypes._transformation;
		tipos[6]=CambioTypes._metadata;
		
		form.setTipos(tipos);
		form.setOption(this.getCambioSession(request).getTipo());
		if(form.getOption()==null||form.getOption().equals(VACIA)) {
			form.setOption(tipos[0]);
		}
        form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
        
        this.getCambioSession(request).setValorNuevo(null);
        this.getCambioSession(request).setLomTerm(null);
        this.getCambioSession(request).setIdLomTerm(null);
        this.getCambioSession(request).setPathDestino(null);
        this.getCambioSession(request).setTipoTransformacion(null);
	}







	public void destruirSesion(ActionMapping mapping, DestruirSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		this.removeCambioSession(request);
	}









}