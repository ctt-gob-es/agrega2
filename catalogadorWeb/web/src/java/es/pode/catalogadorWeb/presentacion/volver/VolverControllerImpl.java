// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.volver;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.catalogadorWeb.presentacion.volver.VolverController
 */
public class VolverControllerImpl extends VolverController
{


	protected static Logger logger = Logger.getLogger(VolverControllerImpl.class); 

	private static final String HTTP ="http://";
	private static final String BARRA ="/";
	private static final String SEPARADOR =":";


    /**
     * @see es.pode.catalogadorWeb.presentacion.volver.VolverController#submitVolver(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.volver.SubmitVolverForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String submitVolver(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.volver.SubmitVolverForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String resAction = "";
    	//String idiomaLocale=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
        
        String accion=form.getAccion();
        if (datosResources.getString("volver.Cancelar").equals(accion)) {
              resAction = "Cancelar";
              
        } else if (datosResources.getString("volver.Si").equals(accion)) {
        	resAction = "Guardar";
        	
        } else if (datosResources.getString("volver.No").equals(accion)){
        	resAction = "Salir";
        }
        return resAction;
    }


    /**
     * @see es.pode.catalogadorWeb.presentacion.volver.VolverController#guardar(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.volver.GuardarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardar(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.volver.GuardarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
		String identificador= "";
		String usuario= "";
		String idioma= "";
		
		try {
			
			if (this.getCatalogadorAvSession(request) == null) {
				identificador = request.getParameter("identificador");
			} else {
				identificador = this.getCatalogadorAvSession(request)
						.getIdentificador();
			}
			usuario =LdapUserDetailsUtils.getUsuario(); 
			//Recogemos el idioma del navegador
			idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			LomAvanzadoVO lomAv= this.getCatalogadorAvSession(request).getMDSesion();
			
			if (lomAv == null){
				lomAv= new LomAvanzadoVO();
			}
			logger.debug("ASC - antes de entrar a generarMetadatos, IDENTIFICADOR " + identificador + " USUARIO " + usuario + " IDIOMA " + idioma);
	        //Para que no salte una excepcion por llamada al servicio, comprobamos si estos parametros no son null
			//si lo son quiere decir que los metodos q los inicializan devuelven null pq se ha perdido la sesion o hay algun
			//problema con ldap
			if ((identificador!=null) && (usuario!= null) && (idioma!= null)) {
	        	this.getSrvCatalogacionAvanzadaService().generarMetadatos(identificador, usuario, lomAv, idioma);
	        } else {
	        	throw new Exception();// invocamos la excepcion
	        }

	
		}catch (Exception e){
			logger.error("Error CatalogacionWeb VolverController, metodo guardar: " + identificador + "; " + usuario + "; " +idioma);
			//throw new java.lang.Exception("volver", e);
			saveErrorMessage(request, " Error CatalogacionWeb VolverController, metodo guardar: " + identificador + "; " + usuario + "; " +idioma );
		}
    }


    /**
     * @see es.pode.catalogadorWeb.presentacion.volver.VolverController#esCatalogadorAvanzado(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.volver.EsCatalogadorAvanzadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String esCatalogadorAvanzado(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.volver.EsCatalogadorAvanzadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tipoCatalogador=null;
    	try{
			if ("avanzado".equals(LdapUserDetailsUtils.getCatalogador())){
				tipoCatalogador="Avanzado";
				if (logger.isDebugEnabled()) 
					logger.debug("Estamos en Catalogador " + tipoCatalogador);
			}else if ("basico".equals(LdapUserDetailsUtils.getCatalogador())){
				tipoCatalogador="Basico";
				if (logger.isDebugEnabled()) 
					logger.debug("Estamos en Catalogador " + tipoCatalogador);
			}else {
				tipoCatalogador="Avanzado";
	    		saveErrorMessage(request, "Error. Por defecto vamos a catalogador Avanzado");
			}
				    		
		}catch (Exception e){
			tipoCatalogador="Avanzado";
			logger.error("Error al leer la sesi�n de usuario. Trabaja en Avanzado");
		}
		return tipoCatalogador;
    }


    /**
     * @see es.pode.catalogadorWeb.presentacion.volver.VolverController#borrarSesion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.volver.BorrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.volver.BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String returnUrl = this.getCatalogadorAvSession(request).getReturnURL();
    	String catalogadorDirecto = this.getCatalogadorAvSession(request).getCatalogadorDirecto();
    	String crearMetadato = this.getCatalogadorAvSession(request).getCrearMetadato();
    	String identificador = this.getCatalogadorAvSession(request).getIdentificador();
    	//	borramos datos de sesion
		request.getSession().removeAttribute(es.pode.catalogadorWeb.presentacion.CatalogadorAvSession.SESSION_KEY);

		String url = HTTP + LdapUserDetailsUtils.getHost();
		
		if(returnUrl==null){
			returnUrl = BARRA + request.getSession().getServletContext().getInitParameter("url_portada");
		}
		if (returnUrl!=null && !returnUrl.startsWith(BARRA)){
			returnUrl = BARRA + returnUrl;
		}
		if(LdapUserDetailsUtils.getSubdominio()!=null && 
		   !LdapUserDetailsUtils.getSubdominio().equals("") && 
		   !returnUrl.startsWith(LdapUserDetailsUtils.getSubdominio()))
		{
			returnUrl = LdapUserDetailsUtils.getSubdominio() + returnUrl;
		}
		url= url + returnUrl;
		//si hemos trabajado con catalogador Directo, vamos a poner en la url el par�metro de "Volver Catalogador"
		if ((catalogadorDirecto!=null) && (!catalogadorDirecto.equals(""))){
			url= url +  "?volverDirecto=VD" + "&identificador=" + identificador; // y ya no llamamos con catalogadorDirecto=CD					
		} else if (crearMetadato!=null && !crearMetadato.equals("")){
			//vamos a borrar el objeto de la hassh!!!!
			//this.getSrvCatalogacionAvanzadaService().eliminarObjLoms(identificadores);
			String [] lisIdentif= {identificador};
			this.getSrvCatalogacionAvanzadaService().eliminarObjLoms(lisIdentif);
		}
		logger.info("TENEMOS LA URL DE VUELTA: " + url);
		response.sendRedirect(url);//Redirigimos a Empaquetador
		
		
    }



}