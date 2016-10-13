// license-header java merge-point
package es.pode.buscador.presentacion.basico.previsualizar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.basico.previsualizar.PrevisualizarController
 */
public class PrevisualizarControllerImpl extends PrevisualizarController
{

	public static final String VIS_ADL = "VIS_ADL";
	public static final String VIS_AGREGA_LOGADO_CON_SEC = "VIS_AGREGA_LOGADO_CON_SEC";
	public static final String VIS_AGREGA_LOGADO_NO_SEC = "VIS_AGREGA_LOGADO_NO_SEC";
	public static final String VIS_AGREGA_NO_LOGADO = "VIS_AGREGA_NO_LOGADO";
	public static final String VIS_ODE_FEDERADO = "VIS_ODE_FEDERADO";
	
	private static Logger logger = Logger.getLogger(PrevisualizarController.class);

    /**
     * @see es.pode.buscador.presentacion.basico.previsualizar.PrevisualizarController#determinaVisualizador(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.previsualizar.DeterminaLogadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String determinaVisualizador(ActionMapping mapping, es.pode.buscador.presentacion.basico.previsualizar.DeterminaVisualizadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
		try {
			
			if(logger.isDebugEnabled())logger.debug("nodoDestino= ["+form.getNodoDestino()+"] estaLogado= ["+LdapUserDetailsUtils.estaAutenticado()+"]");
			if(form.getNodoDestino()!=null && !form.getNodoDestino().equals("")){//VISUALIZADOR FEDERADO
				return VIS_ODE_FEDERADO;
			}
			else{//VISUALIZADOR LOCAL
		        if(LdapUserDetailsUtils.estaAutenticado())
		        	if(LdapUserDetailsUtils.getVisualizador()!=null && LdapUserDetailsUtils.getVisualizador().equalsIgnoreCase("ADL")) 
		        		return VIS_ADL;
		        	else
		        		if (form.getSeleccionarSecuencia()!=null && form.getSeleccionarSecuencia())
		        			
		        			return VIS_AGREGA_LOGADO_CON_SEC;
		        		
		        		else
		        		
		        			return VIS_AGREGA_LOGADO_NO_SEC;
		        		
		        else
		        	if (((this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SECUENCIA_SIN_LOGAR)).equalsIgnoreCase("true"))&&(form.getSeleccionarSecuencia()!=null && form.getSeleccionarSecuencia()))
		        			
	        			return VIS_AGREGA_LOGADO_CON_SEC;
		        	else
		        	
		        		return VIS_AGREGA_NO_LOGADO;
		        	
			}
		}catch (Exception ex){
    		logger.error("Error determinando el tipo de visualizador - ",ex);
    	}
		return VIS_AGREGA_NO_LOGADO;
    }


	@Override
	public void previsualizarADL(ActionMapping mapping, PrevisualizarADLForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Usuario logado y con tipo visualizador [ADL]");
		String urlaVisualizar="http://"+LdapUserDetailsUtils.getHost()+ LdapUserDetailsUtils.getSubdominio() + "/";
		urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
		"?identificador=" + form.getIdentificadorODE();
		response.sendRedirect(urlaVisualizar);
	}

	@Override
	public void previsualizarLogadoSec(ActionMapping mapping, PrevisualizarLogadoSecForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String secuencia =form.getSecuencia();
		if(logger.isDebugEnabled())logger.debug("Usuario logado con tipo visualizador [agrega] y ODE con secuencia, seleccionamos visualización ---> " + secuencia);
		
		//formamos la url
		String urlaVisualizar="http://"+LdapUserDetailsUtils.getHost()+ LdapUserDetailsUtils.getSubdominio() + "/";
		if(logger.isDebugEnabled())logger.debug("el valor de urlaVisualizar " + urlaVisualizar);
		if ((secuencia!= null) && (!secuencia.equals("")) && (secuencia.equals("ConSecuencia"))){	
//			redireccion a visualizador con secuencia
			urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
			"?identificador=" + form.getIdentificadorODE() + "&secuencia=" + true+"&idioma="+form.getIdioma();
			
		} else{ 
//			redireccion a visualizador sin secuencia
			urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
			"?identificador=" + form.getIdentificadorODE() + "&secuencia=" + false + "&idioma="+form.getIdioma();
		}
		response.sendRedirect(urlaVisualizar);

	}


	@Override
	public void previsualizarNoLogado(ActionMapping mapping, PrevisualizarNoLogadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramIdentidadFederada = "";
		String urlaVisualizar="http://"+LdapUserDetailsUtils.getHost()+ LdapUserDetailsUtils.getSubdominio() + "/";
		if(form.getTieneIdentidadFederada()!=null && form.getTieneIdentidadFederada().booleanValue()){
			//logger.debug("ODE sin secuencia o usuario no logado");
			urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
			///"?identificador=" + form.getIdentificadorODE() + "&secuencia=" + (Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SECUENCIA_SIN_LOGAR).equals("true")?true:false) + "&idioma="+form.getIdioma();
			"?identificador=" + form.getIdentificadorODE() + "&secuencia=false" + "&idioma="+form.getIdioma();
			String usuario = LdapUserDetailsUtils.getUsuario();
			String usuarioEncriptado = EncriptacionUtiles.encriptar(usuario);
			paramIdentidadFederada = "&nodoOrigen=" + form.getNodoOrigen()+ "&federado=" + usuarioEncriptado;
			urlaVisualizar = urlaVisualizar + paramIdentidadFederada;
		}else{
			//logger.debug("ODE sin secuencia o usuario no logado");			
			urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_previsualizarCorta") +
			"/"+form.getIdioma()+"/"+form.getIdentificadorODE()+"/"+"false";
			
		}
		response.sendRedirect(urlaVisualizar);
	}


	@Override
	public void previsualizarFederado(ActionMapping mapping, PrevisualizarFederadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("ODE federado del nodo --> " + form.getNodoDestino());
		String urlaVisualizar="http://"+ form.getNodoDestino() + "/";
		urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
		"?idioma="+form.getIdioma()+"&identificador="+form.getIdentificadorODE()+"&secuencia="+false;
		
		
		if(LdapUserDetailsUtils.estaAutenticado()){
			String nodoOrigen = LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
			String usuarioEncriptado = EncriptacionUtiles.encriptar(LdapUserDetailsUtils.getUsuario());
			urlaVisualizar = urlaVisualizar + "&nodoOrigen="+ nodoOrigen +"&federado="+usuarioEncriptado;
		}
		
		response.sendRedirect(urlaVisualizar);
	}


	@Override
	public void previsualizarLogadoNoSec(ActionMapping mapping, PrevisualizarLogadoNoSecForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("ODE sin secuencia o usuario no logado");
		String urlaVisualizar="http://"+LdapUserDetailsUtils.getHost()+ LdapUserDetailsUtils.getSubdominio() + "/";
		
		urlaVisualizar = urlaVisualizar + request.getSession().getServletContext().getInitParameter("url_visualizador") +
		"?identificador=" + form.getIdentificadorODE() + "&secuencia=" + false +"&idioma="+form.getIdioma();
		response.sendRedirect(urlaVisualizar);
	}

}
