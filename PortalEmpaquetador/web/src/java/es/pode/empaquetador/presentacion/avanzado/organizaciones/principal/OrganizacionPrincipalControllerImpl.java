/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.avanzado.organizaciones.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.avanzado.organizaciones.principal.OrganizacionPrincipalController
 */
public class OrganizacionPrincipalControllerImpl extends OrganizacionPrincipalController
{

	
    public final void recuperarDatos(ActionMapping mapping, es.pode.empaquetador.presentacion.avanzado.organizaciones.principal.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	 EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
    	 List subman = sesEmpaq.getSubmanifestPath();
    	 
    	 if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
     		//inicializamos session
     		this.getEmpaquetadorSession(request).setMensajeAsistente("");
     	 }
         OdeVO ultimo=(OdeVO) subman.get(subman.size()-1);
         String principal = ultimo.getOrganizacionPrincipal();
         OrganizacionVO[] organizacion = ultimo.getOrganizaciones();
         
         List organizaciones=Arrays.asList(organizacion);
    	 form.setOrganizaciones(organizaciones);
    	 form.setIdentifier(principal);
    }
    
    
    public final void fijarOrganizacionPrincipal(ActionMapping mapping, es.pode.empaquetador.presentacion.avanzado.organizaciones.principal.FijarOrganizacionPrincipalForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String action = form.getAction();
		if (action.equals(i18n.getString("portal_empaquetado_gestorOrganizaciones.principalAceptar"))) 
		{
	    	String idOrganizacion = form.getIdentifier();
	    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
	        List subman = sesEmpaq.getSubmanifestPath();
	    	
	        if(subman!=null && subman.size()>0)
	        {
		        OdeVO ultimo=(OdeVO) subman.get(subman.size()-1);
		        Integer index = subman.size()-1;
		        String identificador=index==0?sesEmpaq.getIdLocalizador():ultimo.getIdentifier();
		        
		      
		    	this.getSrvGestorManifestService().fijarOrganizacionPorDefecto(identificador, idOrganizacion);
		    	// se modifica la organización principal
		    	sesEmpaq.setModificado(true);
	        }
	        else
	        {
	        	throw new ValidatorException("{portal_empaquetado.exception}");
	        }
	     }
    }
    
}