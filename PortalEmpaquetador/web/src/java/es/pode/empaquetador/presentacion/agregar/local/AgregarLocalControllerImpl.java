/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.agregar.local;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO;
import es.pode.empaquetador.negocio.servicio.AgregarVO;
import es.pode.empaquetador.negocio.servicio.FicheroVO;
import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.negocio.servicio.RecursoVO;
import es.pode.empaquetador.presentacion.DecisorOffline;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.agregar.MetodosComunes;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.agregar.local.AgregarLocalController
 */
public class AgregarLocalControllerImpl extends AgregarLocalController
{

	private static Logger logger = Logger.getLogger(AgregarLocalControllerImpl.class);
	private GestorSesion gs = new GestorSesion();
	
    /**
     * @see es.pode.empaquetador.presentacion.agregar.local.AgregarLocalController#agregar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.local.AgregarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void agregar(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.local.AgregarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	FormFile archivos = form.getArchivo();
        
        //convierto el FormFile en un DataHandler
        InternetHeaders ih = new InternetHeaders();
//		MimeBodyPart mbp = null;
//		DataSource source = null;
		DataHandler dFichero = null;
		MimeBodyPart mbp = new MimeBodyPart(ih, archivos.getFileData());
		DataSource source = new MimePartDataSource(mbp);
		dFichero = new DataHandler(source);
		FicheroVO fichero= new FicheroVO();
		fichero.setDatos(dFichero);
		fichero.setNombre(archivos.getFileName());
		fichero.setTipoMime(archivos.getContentType());
		
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		///////////////////////////
		
       EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
       if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
   			//inicializamos session
   			this.getEmpaquetadorSession(request).setMensajeAsistente("");
   	   }
       
       List subman = sesEmpaq.getSubmanifestPath();
   	
       String identificador=sesEmpaq.getIdLocalizador();
       
       List idCollection = sesEmpaq.getIdCollection();
       
       String identificadorUltimo="";
	   
       if(idCollection!=null && idCollection.size()>0)
	   {
       Object ultimoIdCollection = idCollection.get(idCollection.size()-1);

		if(idCollection.size()==1)
    	{
  	    		
    		OrganizacionVO organizacion = (OrganizacionVO)ultimoIdCollection;
    		identificadorUltimo=organizacion.getIdentifier();
    	}
    	else if(idCollection.size()>1)
    	{
    		GrupoVO grupo = (GrupoVO)ultimoIdCollection;
    		identificadorUltimo=grupo.getIdentifier();
    	}
	   }
       String tipoEmpaquetador=sesEmpaq.getTipoEmpaquetador();
       try
       {
    	 if (tipoEmpaquetador.equals("Avanzado"))
    	 {
    		 String submanifiesto="";
	   		if (subman.size()==1)
	   		{
	   			submanifiesto=getSrvGestorManifestService().agregarManifiestoZIP(identificador, dFichero, null);
	   		}
	   		else if (subman.size()>1)
	   		{
	   			OdeVO ultimo=(OdeVO) subman.get(subman.size()-1);
	   			String submanifestId=ultimo.getIdentifier();
	   			submanifiesto=getSrvGestorManifestService().agregarManifiestoZIP(identificador, dFichero, submanifestId);
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
	     }
  
    	 else if (tipoEmpaquetador.equals("Basico"))
    	 { 
	   		if (logger.isDebugEnabled())logger.debug("Estamos en el básico");
	   		AgregarVO agregarVO = getSrvEmpaquetadorBasicoService().agregarLocal(identificador, fichero, identificadorUltimo, LdapUserDetailsUtils.getUsuario(),LdapUserDetailsUtils.getIdioma());
//	   		this.getSrvGestorManifestService().guardarManifiesto(identificador, false);
	   		
	   		//Hacer algo con resultado
	   		String mensaje="";
	   		if (agregarVO.isNecesitaCambio()) {
				if (agregarVO.isResultado()) {
					mensaje = i18n.getString("licencia.cambiadaA")+ agregarVO.getLicenciaAdicional();
				} else {
					mensaje = i18n.getString("licencia.noCompatible");
				}
				logger.debug("Como hay necesidad de cambio, damos mensaje "+mensaje);
				sesEmpaq.setMensajeCompatibilidadLicencias(mensaje);
				sesEmpaq.setMostradoMensajeCompatibilidadLicencia(false);
				if (agregarVO.isResultado()) {
					OdeVO odeEditado = this.getSrvEmpaquetadorBasicoService().editarOde(identificador);
			     
					MetodosComunes.refrescaSesion(sesEmpaq, odeEditado);
			       	
			       	setEmpaquetadorSession(request, sesEmpaq);
				}
			}
    	 }
    	 //se ha agregado submanifest
    	 sesEmpaq.setModificado(true);
       }
       catch (Exception ex) 
       {
			if (logger.isDebugEnabled())
				logger.error("Ocurrio un error al validar ODE "
						+ identificador,ex);
			throw new ValidatorException("{portalempaquetado.avanzado.submanifiesto.error.agregarLocal}");
       }
     }



    /**
     * @see es.pode.empaquetador.presentacion.agregar.local.AgregarLocalController#submit(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.local.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.local.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
    	String accion = form.getAction();
    	FormFile archivos = form.getArchivo();
    	if (accion.equals(i18n.getString("portal_empaquetado_gestorSubman_agregarLocal.aceptar")))
    	{
    		if((archivos==null)||(archivos.getFileSize()==0))
    		{
    			throw new ValidatorException("{exportar.subirArchivo}");	
    		}
    		
    	}
     }

    /**
     * @see es.pode.empaquetador.presentacion.agregar.local.AgregarLocalController#selectAction(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.local.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.local.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	/*
		 * Metodo de decision para el action. Analiza los parametros
		 * actionSubmit (value de los botones submit) para redirigir al caso de uso
		 * correspondiente. El actionSubmit llegara
		 * internacionalizado, por lo que es necesario acceder al ResouceBundle
		 * para obtener el valor correcto en la comparacion.
		 */
    	
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{portal_empaquetado.exception}");
		}
			
		else if (actionSubmit.equals(i18n.getString("portal_empaquetado_gestorSubman_agregarLocal.aceptar")) )
		{
			result = "Aceptar";
		} 
		else if (actionSubmit.equals(i18n.getString("portal_empaquetado_gestorSubman_agregarLocal.cancelar"))) 
		{
			// En la version offline, se vuelve a CancelarOfflineAvanzado o CancelarOfflineBasico
			if(DecisorOffline.esOffline()) {
				if("avanzado".equalsIgnoreCase(gs.tipoEmpaquetador(this.getEmpaquetadorSession(request)))) {
					result = "CancelarOfflineAvanzado";
				} else {
					result = "CancelarOfflineBasico";
				}
			} else {
				result = "Cancelar";
			}
		} 
		
		Logger.getLogger(this.getClass()).error("El valor del submit no es correcto (actionSubmit = "
							+ actionSubmit + ";");
       
         return result;
    }

    /**
     * @see es.pode.empaquetador.presentacion.agregar.local.AgregarLocalController#tipoEmpaquetador(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.local.TipoEmpaquetadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoEmpaquetador(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.local.TipoEmpaquetadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	return gs.tipoEmpaquetador(this.getEmpaquetadorSession(request));
    }

	public String compruebaPrincipal(ActionMapping mapping, CompruebaPrincipalForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultado = "No";
		// Comprueba nuevo grupo creado para ver si necesita seleccion de
		// archivo principal
		gs.refrescarObjetosSesion(request);
		GrupoVO[] hijos = gs.buscarHijosIdCollection(request);
		// El ultimo hijo es el creado al agregar
		if(hijos!=null && hijos.length>0) {
			GrupoVO nuevo = hijos[hijos.length-1];
			String idRecurso = nuevo.getElementoReferenciado();
			try {
				if(idRecurso!=null) {
					RecursoVO recurso = gs.buscarRecurso(request, idRecurso);
					if(recurso.getFileList()!=null && recurso.getFileList().length>1) {
						logger.debug("El grupo " + nuevo.getIdentifier() + " necesita un archivo principal");
						resultado="Si";
						form.setIdentifier(nuevo.getIdentifier());
					}
				} else {
					logger.debug("idRecurso es null");
				}
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			logger.error("No se ha podido recuperar los hijos del ultimo de idCollection");
		}
		return resultado;
	}

}