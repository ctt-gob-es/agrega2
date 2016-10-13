/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.agregar.personales;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO;
import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.negocio.servicio.TransicionVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.agregar.MetodosComunes;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController
 */
public class AgregarPersonalesControllerImpl extends AgregarPersonalesController
{

	private static Logger logger = Logger.getLogger(AgregarPersonalesControllerImpl.class);
	
	private GestorSesion gestorSesion =  new GestorSesion();
	
	/**
	 * @return the gestorSesion
	 */
	public GestorSesion getGestorSesion() {
		return gestorSesion;
	}


	/**
	 * @param gestorSesion the gestorSesion to set
	 */
	public void setGestorSesion(GestorSesion gestorSesion) {
		this.gestorSesion = gestorSesion;
	}
	
    /**
     * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.personales.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarDatos(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.personales.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String user=this.gestorSesion.obtenerUsuario(request);
    	String isCompartidos = request.getParameter("compartidos");
    	TransicionVO[] objetosPersonales=null;
    	if(isCompartidos==null || this.getEmpaquetadorSession(request).getEsOffline()){
    		form.setCompartidos("false");
    		objetosPersonales=this.getSrvGestorManifestService().obtenerObjetosPersonales(user);
    	}else{
    		form.setCompartidos("true");
    		objetosPersonales=this.getSrvGestorManifestService().obtenerObjetosCompartidos();
    	}
    	List objetosPersonalesReturn=new ArrayList();
    	try{
    		boolean encontrado=false;
    		for (int i=0; i<objetosPersonales.length && !encontrado;i++){
    			if (!objetosPersonales[i].getIdODE().equals(this.getEmpaquetadorSession(request).getIdLocalizador())){
    				objetosPersonalesReturn.add(objetosPersonales[i]);
    			}
    		}
    		logger.debug("Cargado el array de objetos personales con " + objetosPersonales.length+" elementos");
    		
    		if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
        		//inicializamos session
        		this.getEmpaquetadorSession(request).setMensajeAsistente("");
        	}
    		
    	}catch (Exception e){
    		objetosPersonalesReturn=new ArrayList();
    		saveErrorMessage(request, i18n.getString("portalempaquetado.avanzado.subsistemas.agregarpersonales.recuperardatos"));
    	}
    	form.setPersonales(objetosPersonalesReturn);
     }

    /**
     * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController#submit(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.personales.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.personales.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
    	String accion = form.getAction();
    	String identificador = form.getIdODE();
    	
    	if (accion.equals(i18n.getString("portal_empaquetado_gestorSubman_agregarLocal.aceptar"))) {
    		if((identificador==null)||(identificador.equals("")))
    			throw new ValidatorException("{exportar.subirArchivo}");
    	}
    }

    
    /**
     * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController#selectAction(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.personales.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.personales.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		/*
		 * Metodo de decision para el action. Analiza los parametros
		 * actionSubmit (value de los botones submit) para redirigir al caso de
		 * uso correspondiente. El actionSubmit llegara internacionalizado, por
		 * lo que es necesario acceder al ResouceBundle para obtener el valor
		 * correcto en la comparacion.
		 */

		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);

		if (form.getAction() == (null)) {
			throw new ValidatorException("{portal_empaquetado.exception}");
		}

		else if (actionSubmit
				.equals(i18n
						.getString("portal_empaquetado_gestorSubman_agregarLocal.aceptar"))) {
			result = "Aceptar";
		} else if (actionSubmit
				.equals(i18n
						.getString("portal_empaquetado_gestorSubman_agregarLocal.cancelar"))) {
			result = "Cancelar";
		} else {
			Logger.getLogger(this.getClass()).error(
					"El valor del submit no es correcto (actionSubmit = "
							+ actionSubmit + ");");
		}
		return result;
	}

    
    /**
     * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController#agregar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.personales.AgregarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void agregar(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.personales.AgregarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String idAgregar = form.getIdODE();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
        EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
        List subman = sesEmpaq.getSubmanifestPath();
        String identificador = sesEmpaq.getIdLocalizador();
        List idCollection = sesEmpaq.getIdCollection();

        Object ultimoIdCollection;
        String identificadorUltimo = "";
        
        if (idCollection.size() > 0) {
			ultimoIdCollection = idCollection.get(idCollection.size() - 1);
			
			if (idCollection.size() == 1) {
				OrganizacionVO organizacion = (OrganizacionVO) ultimoIdCollection;
				identificadorUltimo = organizacion.getIdentifier();
			} else if (idCollection.size() > 1) {
				GrupoVO grupo = (GrupoVO) ultimoIdCollection;
				identificadorUltimo = grupo.getIdentifier();
			}
		}
        
        String tipoEmpaquetador=sesEmpaq.getTipoEmpaquetador();
        try {
        	if (tipoEmpaquetador.equals("Avanzado")) {
        		
        		if (logger.isDebugEnabled())logger.debug("Se esta usando el empaquetador avanzado");
        		String submanifiesto="";
	    		if (subman.size()==1) {
	    			submanifiesto=getSrvGestorManifestService().agregarManifiestoRepositorio(identificador, idAgregar, null);
	    		} else if (subman.size()>1) {
	    			OdeVO ultimo=(OdeVO) subman.get(subman.size()-1);
	    			String submanifestId=ultimo.getIdentifier();
	    			submanifiesto=getSrvGestorManifestService().agregarManifiestoRepositorio(identificador, idAgregar, submanifestId);
	    		}
	    		
		   		//Y ahora miramos las licencias
		   		getSrvGestorManifestService().guardarManifiesto(identificador, false);
		   		logger.debug("Comparamos licencias de "+sesEmpaq.getOde().getLocalizadorURL()+" y de "+sesEmpaq.getOde().getLocalizadorURL()+"/"+submanifiesto);
		   		ValidarLicenciasVO validarlicencias=getSrvGruposLicencias().cambiaLicenciaCompatibleRuta(sesEmpaq.getOde().getLocalizadorURL(), sesEmpaq.getOde().getLocalizadorURL()+"/"+submanifiesto, LdapUserDetailsUtils.getUsuario(), LdapUserDetailsUtils.getIdioma());
		   		String mensaje="";
		   		if(validarlicencias.isNecesitaCambio()) {
		   			if(validarlicencias.isResultado()) {
						mensaje = i18n.getString("licencia.cambiadaA")+ validarlicencias.getLicenciaAdicional();
					} else {
						mensaje = i18n.getString("licencia.noCompatible");
					}
					logger.debug("Como hay necesidad de cambio, damos mensaje "+mensaje);
					sesEmpaq.setMensajeCompatibilidadLicencias(mensaje);
					sesEmpaq.setMostradoMensajeCompatibilidadLicencia(false);
					
					if(validarlicencias.isResultado()) {
						OdeVO odeEditado = this.getSrvGestorManifestService().editarODE(identificador);
						MetodosComunes.refrescaSesion(sesEmpaq, odeEditado);
				       	setEmpaquetadorSession(request, sesEmpaq);
					}
		   		}
		   		
	        } else if (tipoEmpaquetador.equals("Basico")) {
	        	
        		if (logger.isDebugEnabled())logger.debug("Se esta usando el empaquetador basico");
        		this.getSrvEmpaquetadorBasicoService().agregarRepositorio(identificador, idAgregar, identificadorUltimo);
        	
		   		//Y ahora miramos las licencias
		   		getSrvGestorManifestService().guardarManifiesto(identificador, false);
		   		logger.debug("Comparamos licencias de "+sesEmpaq.getOde().getLocalizadorURL()+" y de id="+idAgregar);
		   		ValidarLicenciasVO validarlicencias=getSrvGruposLicencias().cambiaLicenciaCompatibleRuta(sesEmpaq.getOde().getLocalizadorURL(), idAgregar, LdapUserDetailsUtils.getUsuario(), LdapUserDetailsUtils.getIdioma());
		   		String mensaje="";
		   		if(validarlicencias.isNecesitaCambio()) {
		   			if(validarlicencias.isResultado()) {
						mensaje = i18n.getString("licencia.cambiadaA")+ validarlicencias.getLicenciaAdicional();
					} else {
						mensaje = i18n.getString("licencia.noCompatible");
					}
					logger.debug("Como hay necesidad de cambio, damos mensaje "+mensaje);
					sesEmpaq.setMensajeCompatibilidadLicencias(mensaje);
					sesEmpaq.setMostradoMensajeCompatibilidadLicencia(false);
					
					if(validarlicencias.isResultado()) {
						OdeVO odeEditado = this.getSrvEmpaquetadorBasicoService().editarOde(identificador); 
						MetodosComunes.refrescaSesion(sesEmpaq, odeEditado);
				       	setEmpaquetadorSession(request, sesEmpaq);
					}
		   		}
	        }
        	//modificación de ode
        	sesEmpaq.setModificado(true);
        	
        } catch (Exception ex) {
 			logger.error("Ocurrio un error al agregar contenido al ODE " + identificador, ex);
 			throw new ValidatorException("{portalempaquetado.avanzado.submanifiesto.error.agregarLocal}");
        }
	}

    
    /**
     * @see es.pode.empaquetador.presentacion.agregar.personales.AgregarPersonalesController#tipoEmpaquetador(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.personales.TipoEmpaquetadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoEmpaquetador(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.personales.TipoEmpaquetadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return gestorSesion.tipoEmpaquetador(this.getEmpaquetadorSession(request));
    }

}