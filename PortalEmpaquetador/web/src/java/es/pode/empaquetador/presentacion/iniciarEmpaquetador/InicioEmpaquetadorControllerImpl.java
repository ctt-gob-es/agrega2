/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.iniciarEmpaquetador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.CatalogacionBean;
import es.pode.empaquetador.presentacion.DecisorOffline;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.empaquetador.presentacion.avanzado.organizaciones.crear.CrearOrganizacionSession;
import es.pode.empaquetador.presentacion.avanzado.organizaciones.elementos.crearelemento.CrearElementoSession;
import es.pode.empaquetador.presentacion.avanzado.recursos.crear.CrearRecursoAvanzadoSession;
import es.pode.empaquetador.presentacion.avanzado.recursos.crear.archivos.CrearRecursoArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController
 */
public class InicioEmpaquetadorControllerImpl extends InicioEmpaquetadorController
{
    private static Logger logger = Logger.getLogger(InicioEmpaquetadorControllerImpl.class);

    private GestorSesion gs = new GestorSesion();
    
	/**
     * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#esEdicion(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.EsEdicionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String esEdicion(ActionMapping mapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.EsEdicionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ident=form.getIdentificador();
    	if(ident==null)	{
 			return "Crear";    		
    	}
		form.setIdentificador(ident);
		// si el catalogador Directo tiene algo, 
		if ((this.getEmpaquetadorSession(request).getCatalogadorDirecto()!=null) && (!this.getEmpaquetadorSession(request).getCatalogadorDirecto().equals(""))){
			return "Catalogador Directo";
		} else if ((this.getEmpaquetadorSession(request).getVolverDirecto()!=null) && (!this.getEmpaquetadorSession(request).getVolverDirecto().equals(""))){
			return "Volver Directo";
		} else {
			return "Editar";
		}
    }

    /**
     * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#crearOde(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.CrearOdeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearOde(ActionMapping mapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.CrearOdeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       if(logger.isDebugEnabled()) logger.debug("Intentando recuperar el login del usuario...");
		String sUser = gs.obtenerUsuario(request);
		if(logger.isDebugEnabled()) logger.debug("Llamando al servicio para iniciarNuevoODE con parametro user = " + sUser + "; titulo = " + form.getTitulo() + "; idioma = " + ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		OdeVO odeCreado = this.getSrvGestorManifestService().iniciarNuevoODE(sUser, form.getTitulo(), ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		try {
			if(logger.isDebugEnabled()) logger.debug("Generando backup del conjunto de archivos inicial");
			this.getSrvGestorArchivosService().generarBackup(odeCreado.getIdentifier());
		} catch (Exception e) {
			logger.error("Fallo en la generacion del backup",e);
		}
		form.setOde(odeCreado);
		getEmpaquetadorSession(request).setIdLocalizador(odeCreado.getIdentifier());
//		if(DecisorOffline.esOffline())
//			this.getEmpaquetadorSession(request).setUrlCerrar(this.getEmpaquetadorSession(request).getUrlCerrar() + "?idODE="+odeCreado.getIdentifier());
		this.getEmpaquetadorSession(request).setGuardadoPrimeraVez(false);
     }

    /**
	 * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#editarOde(org.apache.struts.action.ActionMapping,
	 *      es.pode.empaquetador.presentacion.iniciarEmpaquetador.EditarOdeForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
    public final void editarOde(
			ActionMapping mapping,
			es.pode.empaquetador.presentacion.iniciarEmpaquetador.EditarOdeForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ident = form.getIdentificador();
		try {
			String tipoEmpaquetador = tipoEmpaquetador(request);
			OdeVO odeEditado = null;
			if("Avanzado".equalsIgnoreCase(tipoEmpaquetador)) {
				odeEditado = this.getSrvGestorManifestService().editarODE(ident);
			} else {
				odeEditado = this.getSrvEmpaquetadorBasicoService().editarOde(ident);
			}
			form.setOde(odeEditado);
		} catch (Exception e) {
			logger.error("Error en la edicion del ODE", e);
		}
		getEmpaquetadorSession(request).setIdLocalizador(ident);
		this.getEmpaquetadorSession(request).setGuardadoPrimeraVez(true);
		//this.getEmpaquetadorSession(request).setModificado(true);
	}

    
    /**
	 * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#crearSesion(org.apache.struts.action.ActionMapping,
	 *      es.pode.empaquetador.presentacion.iniciarEmpaquetador.CrearSesionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
    public final void crearSesion(ActionMapping mapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.CrearSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	OdeVO ode = form.getOde();
    	EmpaquetadorSession ses = this.getEmpaquetadorSession(request);
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
     
    	ses.setOde(ode);
    	ses.setVistaCarpeta(Boolean.TRUE);
    	ses.setAccion("Normal");
    	ses.setModoPegar(false);
    	
    	sesArch.setAccion("Normal");
    	sesArch.setModoPegar(false);
    	
    	//le introduzco la organizacion principal
    	List idCollection=new ArrayList();
       	String idOrgPrincipal = ode.getOrganizacionPrincipal();
    	
       	if(idOrgPrincipal!=null)
    	{
       		OrganizacionVO[] orgs= ode.getOrganizaciones();
       		if(orgs!=null && orgs.length>0)
       		{
       			for(int i=0;i<orgs.length ; i++)
       			{
       				if(orgs[i].getIdentifier().equals(idOrgPrincipal))
       				{
       					idCollection.add(orgs[i]);
       				}
       			}
       		}
       	}
    	ses.setIdCollection(idCollection);
	
    	//le introduzco la ruta de los submanifiestos
    	List submanifestPath =new ArrayList();
    	submanifestPath.add(ode);	
    	ses.setSubmanifestPath(submanifestPath);
    	ses.setPendientesCatalogacion(new ArrayList());
    	ses.setPortapapeles(new ArrayList());
    	ses.setEsOffline(DecisorOffline.esOffline());
    	ses.setMensajeCompatibilidadLicencias(null);
    	ses.setMostradoMensajeCompatibilidadLicencia(true);
     }

    /**
     * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#esAvanzado(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.EsAvanzadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String esAvanzado(ActionMapping mapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.EsAvanzadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tipoSesion=null;

    	try{
    		tipoSesion = tipoEmpaquetador(request);

    	}catch (Exception e){
    		tipoSesion="Avanzado";
//    		saveErrorMessage(request, "Error al leer la sesión. Por defecto el usuario trabaja en la Avanzada");
    		logger.error("Error al leer la sesión de usuario. Trabaja en Avanzado");
    	}
    	this.getEmpaquetadorSession(request).setTipoEmpaquetador(tipoSesion);
    	return tipoSesion;
    }

    
	private String tipoEmpaquetador(HttpServletRequest request) {
		String tipoSesion = null;
		if ("avanzado".equals(LdapUserDetailsUtils.getEmpaquetador())){
			tipoSesion="Avanzado";
			if (logger.isDebugEnabled()) 
				logger.debug("La sesión en la que está trabajando el usuario es la " + tipoSesion);
		}else if ("basico".equals(LdapUserDetailsUtils.getEmpaquetador())){
			tipoSesion="Basico";
			if (logger.isDebugEnabled()) 
				logger.debug("La sesión en la que está trabajando el usuario es la " + tipoSesion);
		} else {
			tipoSesion="Avanzado";
			saveErrorMessage(request, "Error al leer la sesión. Por defecto el usuario trabaja en la Avanzada");
		}
		return tipoSesion;
	}

	
    /**
     * @see es.pode.empaquetador.presentacion.iniciarEmpaquetador.InicioEmpaquetadorController#compruebaSesion(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.CompruebaSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void compruebaSesion(ActionMapping mapping, es.pode.empaquetador.presentacion.iniciarEmpaquetador.CompruebaSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String ident=request.getParameter("identificador");
    	String urlCerrar = request.getParameter("urlCerrar");
    	String espacioLibre = request.getParameter("espacioLibre");
    	String espacioOde = request.getParameter("espacioODE");
    	String tituloOde = request.getParameter("titulo");
    	//parametro de catalogación directa
    	String catalogadorDirecto = request.getParameter("catalogadorDirecto");
    	logger.debug("METODO COMPRUEBASESSION CATALOGADOR DIRECTO ES " + catalogadorDirecto);
    	
    	String volverDirecto= request.getParameter("volverDirecto");
    	logger.debug("METODO COMPRUEBASESSION VOLVER DIRECTO ES " + volverDirecto + " pendientesCatalogacion "+ this.getEmpaquetadorSession(request).getPendientesCatalogacion());
    	
    	if(urlCerrar==null) {
    		if(DecisorOffline.esOffline()){
    			urlCerrar = "/".concat(request.getSession().getServletContext().getInitParameter("url_OfflinePersonales"));
    			this.getEmpaquetadorSession(request).setEsOffline(true);
    		}else{
    			urlCerrar = "/".concat(request.getSession().getServletContext().getInitParameter("url_gestorFlujo"));
    			this.getEmpaquetadorSession(request).setEsOffline(false);
    		}
    	}
    	form.setIdentificador(ident);
    	//compruebo que no hay nada creado y en el caso en que haya, lo borro
    	
    	if(this.getCrearElementoSession(request)!=null)
    	{
    		request.getSession().removeAttribute(CrearElementoSession.SESSION_KEY);
    	}
    	if(this.getCrearOrganizacionSession(request)!=null)
    	{
    		request.getSession().removeAttribute(CrearOrganizacionSession.SESSION_KEY);
    	}
    	if(this.getCrearRecursoAvanzadoSession(request)!=null)
    	{
    		request.getSession().removeAttribute(CrearRecursoAvanzadoSession.SESSION_KEY);
    	}
    	//La sesión del empaquetador no la borramos si volverDirecto no es null, ya que podríamos haber cambiado datos de la catalogación, y
    	//deben refrescarse desde pendientesCatalogacion
    	if	((this.getEmpaquetadorSession(request)!=null) && (volverDirecto==null || volverDirecto.equals("")))//solo borra si no es volverDirecto!!!
    	{
    		request.getSession().removeAttribute(EmpaquetadorSession.SESSION_KEY);    		
    	}
    	if((this.getGestorArchivosSession(request)!=null))	
    	{
    		request.getSession().removeAttribute(GestorArchivosSession.SESSION_KEY);
    	}
    	// Genero el objeto de sesio y le meto la url necesaria para cerrar el empaqueator
    	if(logger.isDebugEnabled()) logger.debug("Url de cerrar : " + urlCerrar);
    	this.getEmpaquetadorSession(request).setUrlCerrar(urlCerrar);
    	this.getEmpaquetadorSession(request).setEspacioLibre(espacioLibre);
    	this.getEmpaquetadorSession(request).setEspacioOde(espacioOde);
    	//si el titulo no es vacio, lo metemos en el parametro de catalogadorDirecto
    	if (tituloOde!=null) {
    		this.getEmpaquetadorSession(request).setCatalogadorDirecto(tituloOde);
    	} else {
    		this.getEmpaquetadorSession(request).setCatalogadorDirecto(catalogadorDirecto);
    	}
    	this.getEmpaquetadorSession(request).setVolverDirecto(volverDirecto);
    	// la session del empaquetador ha sido inicializada; necesitamos el asistente activo en principio, lo inicializamos aqui
    	this.getEmpaquetadorSession(request).setAsistenteAyuda(true);
    	//inicializamos el valor de modificado; nos ayudará en el asistente
    	this.getEmpaquetadorSession(request).setModificado(false);
	}
    
    
    private void borrarSesion (HttpServletRequest request) {
		if(this.getCrearElementoSession(request)!=null)
			request.getSession().removeAttribute(CrearElementoSession.SESSION_KEY);
		if(this.getEmpaquetadorSession(request)!=null)
			request.getSession().removeAttribute(EmpaquetadorSession.SESSION_KEY);
		if(this.getCrearRecursoArchivosSession(request)!=null)
			request.getSession().removeAttribute(CrearRecursoArchivosSession.SESSION_KEY);
		if(this.getCrearRecursoAvanzadoSession(request)!=null)
			request.getSession().removeAttribute(CrearRecursoAvanzadoSession.SESSION_KEY);
		if(this.getCrearOrganizacionSession(request)!=null)
			request.getSession().removeAttribute(CrearOrganizacionSession.SESSION_KEY);	
    }


	public void redirect(ActionMapping mapping, RedirectForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String urlCerrar = this.getEmpaquetadorSession(request).getUrlCerrar();
		if(urlCerrar==null ) urlCerrar = "/" + request.getSession().getServletContext().getInitParameter("url_gestorFlujo");
		// URLs tipo host/agrega
		urlCerrar="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+ (urlCerrar.startsWith("/")?"":"/") +urlCerrar;
		this.removeEmpaquetadorSession(request);
		this.removeGestorArchivosSession(request);
		logger.debug("Redirigiendo a " + urlCerrar);
		borrarSesion(request);		
		response.sendRedirect(urlCerrar);
	}


	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String result = "Cancelar";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String aceptar = I18n.getInstance().getResource("iniciarEmpaquetador.aceptar", "application-resources", loc);
		
		if(logger.isDebugEnabled()) logger.debug("Action = " + form.getAction());
		if(aceptar!=null &&form.getAction()!=null&& aceptar.equals(form.getAction())) {
			if(form.getTitulo()==null || "".equals(form.getTitulo().trim())) {
				throw new ValidatorException("{iniciarEmpaquetador.titulo.error}");
			}
			result = "Aceptar";
		}
		return result;
	}


	public void crearLomesInicial(ActionMapping mapping, CrearLomesInicialForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String titulo = form.getTitulo();
		String identificador = form.getIdentificador();
		String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		logger.debug("Datos recibidos : titulo = " + titulo + " : identificador = " + identificador + " : idioma = " + idioma);
		OdeVO ode = this.getSrvGestorManifestService().generarMetadatoInicial(titulo, idioma, identificador);
		form.setOde(ode);
	}


	public String selectActionEditar(ActionMapping mapping, SelectActionEditarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "Cancelar";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String aceptar = I18n.getInstance().getResource("iniciarEmpaquetador.aceptar", "application-resources", loc);
		if(aceptar.equals(form.getAction())) {
			// Validaciones
			if(form.getTitulo()==null || "".equals(form.getTitulo().trim())) {
				throw new ValidatorException("{iniciarEmpaquetador.titulo.error}");
			}
			result = "Aceptar";
		}
		return result;
	}


	public boolean tieneLomes(ActionMapping mapping, TieneLomesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OdeVO ode = form.getOde();
		boolean result =false;
		if(ode!=null && ode.getLom() !=null) result = true;
		if(logger.isDebugEnabled()) logger.debug("Tiene Lomes? " + result);
		return result;
	}

	@Override
	public void catalogadorDirecto(ActionMapping mapping, CatalogadorDirectoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Generamos el ode de forma ficticia
		String identificador = form.getIdentificador();
		String sUser = gs.obtenerUsuario(request);
		String idioma= ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		
		////////////Parte correspondiente a la Edicion!!
		if(logger.isDebugEnabled()) logger.debug("Parte correspondiente a Editar!!");
		OdeVO odeEditado = null;
		try {
			String tipoEmpaquetador = tipoEmpaquetador(request);
			if("Avanzado".equalsIgnoreCase(tipoEmpaquetador)) {
				odeEditado = this.getSrvGestorManifestService().editarODE(identificador);
			} else {
				odeEditado = this.getSrvEmpaquetadorBasicoService().editarOde(identificador);
			}
			form.setOde(odeEditado);
		} catch (Exception e) {
			logger.error("Error en la edicion del ODE", e);
		}
		
		//////////////Creamos la session		
		EmpaquetadorSession ses = this.getEmpaquetadorSession(request);
    	ses.setIdLocalizador(identificador);
    	ses.setGuardadoPrimeraVez(true);
    	ses.setAsistenteAyuda(true);
		ses.setOde(odeEditado);
		ses.setEsOffline(DecisorOffline.esOffline());
		
		// Comprobamos si tiene Lomes
		boolean result =false;
		if(odeEditado!=null && odeEditado.getLom() !=null) result = true;
		if(logger.isDebugEnabled()) logger.debug("Tiene Lomes? " + result);
		// Fin comprobacion si tiene Lomes
		// Si no lo tiene --> generamos uno Inicial
		if (!result) {
			//////////// Este valor OJO, Cambiar despues??????
			String titulo= ses.getCatalogadorDirecto();//"Nuevo Ode Catalogación Directa";			
			OdeVO ode = this.getSrvGestorManifestService().generarMetadatoInicial(titulo, idioma, identificador);
			ses.setOde(ode);
		}
		//Creamos Session con todos los datos que tenemos
		GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    		
    	//le introduzco la organizacion principal
    	List idCollection=new ArrayList();
    	OdeVO odeCreado = ses.getOde();
       	String idOrgPrincipal = odeCreado.getOrganizacionPrincipal();
       	if(idOrgPrincipal!=null)
    	{
       		OrganizacionVO[] orgs= odeCreado.getOrganizaciones();
       		if(orgs!=null && orgs.length>0)
       		{
       			for(int i=0;i<orgs.length ; i++)
       			{
       				if(orgs[i].getIdentifier().equals(idOrgPrincipal))
       				{
       					idCollection.add(orgs[i]);
       				}
       			}
       		}
       	}
    	ses.setIdCollection(idCollection);
	
    	//le introduzco la ruta de los submanifiestos
    	List submanifestPath =new ArrayList();
    	submanifestPath.add(odeCreado);	
    	ses.setSubmanifestPath(submanifestPath);
    	ses.setPendientesCatalogacion(new ArrayList());
    	ses.setPortapapeles(new ArrayList());
    	ses.setEsOffline(DecisorOffline.esOffline());
    	// Fin, parte creacion de Session
    	
    	////////////////////////////////Ahora montamos la Url de Catalogador y le pasamos el parámetro de "Catalogador Avanzado"
    	//URLs tipo host/agrega
    	String url = request.getSession().getServletContext().getInitParameter("url_catalogador");
    	//String inicioEmp="/PortalEmpaquetador/InicioEmpaquetador/InicioEmpaquetador.do";
    	String inicioEmp= request.getSession().getServletContext().getInitParameter("url_objetos"); //PortalEmpaquetador/InicioEmpaquetador/InicioEmpaquetador.do
    	
    	url = ( url + "?idioma=" + idioma + "&usuario=" + sUser
		+ "&identificador=" + identificador + "&returnURL=" + inicioEmp +"&catalogadorDirecto=CD");
		//Tenemos que añadirle 2 parámetros más; 1º el que indicará al catalogador que es una catalogacion directa
    	
    	logger.debug("EL VALOR DE LA URL PARA CATALOGADOR DIRECTO ES " + url);
    	//Preparamos la catalogacion
    	if(logger.isDebugEnabled()) logger.debug("Llamando al servicio para preparar la catalogacion con identificador = " + identificador + " , idElemento = " + identificador + " , href = "  + null);
		this.getSrvGestorManifestService().prepararCatalogacion(identificador,identificador, null);
		//registramos como pendiente de catalogacion
		CatalogacionBean bean = new CatalogacionBean();
		bean.setIdentifier(identificador);
		bean.setHref(null);
		List pendCat = ses.getPendientesCatalogacion();
		pendCat.add(bean);
		ses.setPendientesCatalogacion(pendCat);

		/*
		 * Session Timeout: Para evitar que caduque la sesion del empaquetador
		 * mientras se trabaja con el catalogador variamos el time-out de la
		 * sesion. En el punto de retorno se restaura al valor normal.
		 */
		request.getSession().setAttribute("timeout.empaquetador", new Integer(request.getSession().getMaxInactiveInterval()));
		logger.debug("Timeout previo al cambio = " + request.getSession().getMaxInactiveInterval() + "s");
		String timeoutExtendido = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_EXTENDIDO);
		logger.debug("Modificando el timeout del empaquetador a " + timeoutExtendido);
		request.getSession().setMaxInactiveInterval(Integer.parseInt(timeoutExtendido));
		// Concatenamos host y subdominio para URLs tipo http://host/agrega
		url = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+url;
		if(logger.isDebugEnabled()) logger.debug("Redirigiendo a url " + url);
		
		response.sendRedirect(url);
		
		if(logger.isDebugEnabled()) logger.debug("Fin metódo Catalogador Directo, vamos a Catalogador ");
	}

	@Override
	public void volverCatalogadorDirecto(ActionMapping mapping, VolverCatalogadorDirectoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.debug("ENTRAMOS EN VOLVER DIRECTO con identificador" + form.getIdentificador());
		logger.debug("PARAMETRO DE VOLVER DIRECTO DE SESSION " + this.getEmpaquetadorSession(request).getVolverDirecto());
		//Tenemos que reproducir lo que haría el CU Salir cuando guardamos
		//1.- Guardamos los datos
		//2.- Cerramos Session
		EmpaquetadorSession sesEmp = this.getEmpaquetadorSession(request);
		String url = sesEmp.getUrlCerrar();
		url="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+(url.startsWith("/")?"":"/")+url;
		String idOde = form.getIdentificador();
		
		gs.comprobarPendientesCatalogacion(request);
		
		//Si no existe un backup del ODE quiere decir que este ODE ha sido editado desde dos 
		//sitios diferentes y uno ha acabado antes eliminando el backup
		//Si no hay backup no guardamos cambios.
		if (this.getSrvGestorArchivosService().verficarExisteBackup(form.getIdentificador())) {
			logger.debug("VAMOS A GUARDAR EL MANIFEST CON id ODE = " + idOde);
			this.getSrvGestorManifestService().guardarManifiesto(idOde, false);
		}	
		
		if(!sesEmp.isGuardadoPrimeraVez()) {
			sesEmp.setGuardadoPrimeraVez(true);
			if(logger.isDebugEnabled()) logger.debug("Cambiado flag guardadoPrimeraVez");
		}

		List submanifestPath = sesEmp.getSubmanifestPath();
	    String identificador="";
	    OdeVO elemento=null;
	    if ((submanifestPath!=null) &&(submanifestPath.size()>1))
	    {
	    	for(int i=submanifestPath.size()-1;i>0;i--)
	    	{
	    		elemento=(OdeVO) submanifestPath.get(i);
	    		identificador=elemento.getIdentifier();
	    		this.getSrvGestorManifestService().descargarManifest(identificador);
	    	}
	    }
		
		this.getSrvGestorManifestService().terminarEdicion(idOde);
		if(!this.getEmpaquetadorSession(request).isGuardadoPrimeraVez()) {
			if(logger.isDebugEnabled()) logger.debug("No se ha guardado este ODE por primera vez. Elimino el ODE de la tabla de publicacion");
			try {
				this.getSrvGestorManifestService().eliminarODE(idOde);
			} catch (Exception e) {
				logger.error("No se pudo eliminar " + sesEmp.getIdLocalizador(),e);
			}
		}
		logger.debug("BORRAMOS LAS SESSIONES ");
		borrarSesion(request);		
		logger.debug("VAMOS A REDIRIGIR A " + url);
		response.sendRedirect(url);
	}

	
	@Override
	public Boolean odeEnEdicion(ActionMapping mapping, OdeEnEdicionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id=form.getIdentificador();
		if(id==null || id.isEmpty()) return false;
		
    	String volverDirecto = request.getParameter("volverDirecto");
    	if(volverDirecto==null || volverDirecto.isEmpty()) {
    		//Si existe un backup del ODE quiere decir que ya esta siendo editado
    		return this.getSrvGestorArchivosService().verficarExisteBackup(id);
    	}
    	//Si volver directo contiene algo quiere decir que el ODE los estamos editando nosotros mismos
    	//y que estamos saliendo de su edicion por lo que no tenemos que controlar si se esta editando o no.
    	//Al salir de la edicion se eliminaran los datos de la cache y el backup del ODE

    	return false;
	}

	
	@Override
	public String selectActionSobreescribirCambios(ActionMapping mapping,
			SelectActionSobreescribirCambiosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		

		String result = "Cancelar";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String aceptar = I18n.getInstance().getResource("iniciarEmpaquetador.aceptar", "application-resources", loc);
		
		if(logger.isDebugEnabled()) logger.debug("Action = " + form.getAction());
		
		if(aceptar!=null && form.getAction()!=null && aceptar.equals(form.getAction())) {
			if(form.getIdentificador()==null || form.getIdentificador().isEmpty()) {
				logger.error("Error al obtener el identificador del ODE");
				return result;
			}
			result = "Aceptar";
		}
		return result;		
	}

}