// license-header java merge-point
package es.pode.herramientaOffline.presentacion.personal;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.herramientaOffline.negocio.soporte.OdeVO;
import es.pode.parseadorXML.castor.Lom;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.serializar.SerializadorObjetos;



/**
 * @see es.pode.herramientaOffline.presentacion.personal.CarpetaPersonalController
 */
public class CarpetaPersonalControllerImpl extends CarpetaPersonalController
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7870511173027532255L;

	private static Logger logger = Logger.getLogger(CarpetaPersonalControllerImpl.class);
	
	/**
     * @see es.pode.herramientaOffline.presentacion.personal.CarpetaPersonalController#listarOdes(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.personal.ListarOdesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarOdes(ActionMapping mapping, es.pode.herramientaOffline.presentacion.personal.ListarOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	OdeVO[] odes = null;
    	try {
    	odes = getDescomprimeYvalida().listarOdes();
    	}catch (Exception e) {
    		logger.error(e);
			odes = new OdeVO[]{};
			saveErrorMessage(request, "HerramientaOffline.error.inesperado");
		}
    	
		// populating the table with a dummy list
		form.setOdes(Arrays.asList(odes));
		// Introduzco en sesion una url para volver al menú principal (urlCerrar). Esta url se lee de layout-offline.jsp
		request.getSession().setAttribute("urlCerrar", request.getContextPath() + mapping.findForward("inicial.cu").getPath());
     }

    /**
     * @see es.pode.herramientaOffline.presentacion.personal.CarpetaPersonalController#submit(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.personal.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.herramientaOffline.presentacion.personal.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        
        String eliminar = i18n.getString("CarpetaPersonal.boton.eliminar");
        String subir=i18n.getString("CarpetaPersonal.boton.subir");
        if(eliminar.equals(form.getAction())) {
        	// Validar que se ha elegido algun ODE
        	if(form.getIdOdeRowSelection()==null || form.getIdOdeRowSelection().size()==0) {
        		throw new ValidatorException("{CarpetaPersonal.error.seleccione}");
        	} else {
        		// Crear una coleccion con los identificadores e introducirla en el form
        		if(logger.isDebugEnabled()) logger.debug("Lista de identificadores a eliminar " + form.getIdOdeRowSelection());
        		form.setIdentificadores(form.getIdOdeRowSelection());
        	}
        }else if(subir.equals(form.getAction())){
//        	 Validar que se ha elegido algun ODE
        	if(form.getIdOdeRowSelection()==null || form.getIdOdeRowSelection().size()==0) {
        		throw new ValidatorException("{CarpetaPersonal.error.subir.seleccione}");
        	} else {
        		// Crear una coleccion con los identificadores e introducirla en el form
        		if(logger.isDebugEnabled()) logger.debug("Lista de identificadores a subir " + form.getIdOdeRowSelection());
        		form.setIdentificadores(form.getIdOdeRowSelection());
        	}
        }
    }

	@Override
	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        // Compruebo el action pulsado para redirigir la accion
        String result = null;
        if(logger.isDebugEnabled()) logger.debug("Action = " + form.getAction());
        if(i18n.getString("CarpetaPersonal.boton.eliminar").equals(form.getAction())) {
        	result = "eliminar";
        } else if (i18n.getString("CarpetaPersonal.boton.crear").equals(form.getAction())) {
        	// Redireccion al empaquetador para crear nuevo ODE
        	String url = "/"+request.getSession().getServletContext().getInitParameter("url_objetos");
        	url = url.concat("?urlCerrar="+request.getContextPath() + mapping.findForward("carpeta.personal").getPath());
        	logger.info("Abriendo el empaquetador con la url " + url);
        	result="crear";
        	response.sendRedirect(url);
        } else if(i18n.getString("CarpetaPersonal.boton.importar").equals(form.getAction())) {
        	result="importar";
        }else if(i18n.getString("CarpetaPersonal.boton.subir").equals(form.getAction())){
        	result="subir";
        }else if(i18n.getString("CarpetaPersonal.boton.crearMetadato").equals(form.getAction())){
        	result="crear metadato";
        }
        logger.debug("result devuelto en selectAction = " + result);
		return result;
	}

	@Override
	public void crearMetadato(ActionMapping mapping, CrearMetadatoForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			//Para crear Metadatos desde la carpeta personal, debemos generar un identificador ficticio que lo identifique
			logger.info("ASC - Otra vez el usuario es " + LdapUserDetailsUtils.getUsuario());
			//String idFicticio1=  es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
			String idFicticio = es.pode.soporte.uuid.PodeUUIDGenerator.getUUID(String.valueOf(System.currentTimeMillis()));
			//String idFicticio2 = podeUUIDGen.getUUID(String.valueOf(System.currentTimeMillis()));
			logger.info("ASC - el idFicticio que estamos generando es  --> " + idFicticio);
			//vemos el tipo de Perfil que tiene el usuario
			String idioma= ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			String tipoCatalogador="";
			//Creamos un Lom y lo serializamos		
			Lom lomInicial = new Lom();
			SerializadorObjetos lomSerializado = new SerializadorObjetos();
			DataHandler lomHandler= lomSerializado.serializarObjeto(lomInicial);
			
			if ("avanzado".equals(LdapUserDetailsUtils.getCatalogador())){
				logger.info("Vamos a trabajar con el Catalogador Avanzado");
				tipoCatalogador="Avanzado";
				this.getSrvCatalogacionAvanzadaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE AVANZADO CON IDENTIFICADOR " + idFicticio);
			}else if ("basico".equals(LdapUserDetailsUtils.getCatalogador())){
				logger.info("Vamos a trabajar con el tipo de Catalogador Básico");
				tipoCatalogador="Basico";
				this.getSrvCatalogacionBasicaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE BASICO  CON IDENTIFICADOR " + idFicticio);
			}else {
				logger.info("Vamos a trabajar por defecto con el Catalogador Avanzado");
				tipoCatalogador="Avanzado";
				this.getSrvCatalogacionAvanzadaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE AVANZADO POR DEFECTO CON IDENTIFICADOR " + idFicticio);
			}
		
			//REDIRIGIMOS AL CATALOGADOR WEB, CON OTRO PARAMETRO EN LA LLAMADA...
			// EL PARAMETRO ES CREARMETADATO
			//URLs tipo host/agrega
			if (!tipoCatalogador.equals("")){
				String url = request.getSession().getServletContext().getInitParameter("url_catalogador");
		    	String returnUrl= request.getSession().getServletContext().getInitParameter("url_OfflinePersonales");//request.getSession().getServletContext().getInitParameter("url_gestorFlujo"); //gestorFlujo/ObjetosPersonalesCU/ObjetosPersonalesCU.do
		    	
		    	url = ( url + "?idioma=" + idioma + "&usuario=" + LdapUserDetailsUtils.getUsuario()
				+ "&identificador=" + idFicticio + "&returnURL=" + returnUrl +"&crearMetadato=CM");
				//Tenemos que añadirle 2 parámetros más; 1º el que indicará al catalogador que es una catalogacion directa
		    
				url = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+url;
				logger.info("ASC - LA URL ES " + url);
				
				if(logger.isDebugEnabled()) logger.debug("Redirigiendo a url " + url);
				response.sendRedirect(url);		
				//tendremos que controlar las vueltas ahora desde el catalogadrWeb y ponerle otro parámetro
			}
		} catch (Exception e) {
			logger.error("Se ha producido un Error " + e);
			throw new ValidatorException("{gestor.flujo.error.obtener.personales}");
		}
		
	
		// TODO Auto-generated method stub
		
	}

}