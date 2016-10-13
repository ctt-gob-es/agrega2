// license-header java merge-point
package es.pode.tagging.presentacion.modificarTag;

import java.util.Locale;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.tagging.presentacion.TaggingSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.modificarTag.ModificarTagController
 */
public class ModificarTagControllerImpl extends ModificarTagController
{


	protected static Logger logger = Logger.getLogger(ModificarTagControllerImpl.class);



    /**
     * @see es.pode.tagging.presentacion.modificarTag.ModificarTagController#modificarTag(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.modificarTag.ModificarTagForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void modificarTag(ActionMapping mapping, es.pode.tagging.presentacion.modificarTag.ModificarTagForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	TaggingSession taggingSession =  this.getTaggingSession(request);
    	if(taggingSession!=null ){
	        String usuario = taggingSession.getUsuario();
	        String newTag = form.getNewTag();
	        String oldTag = form.getTag();
	        
	        if(!oldTag.equals(newTag.trim()))
	        {
	    		if(usuario ==null){		
	    			//Comprobamos si seguimos autenticados si seguimos autenticados
	    			//recuperamos el usuario de ldap y lo metemos en sesion
	    			logger.debug("OBTENIENDO USUARIO: " +usuario);
	    			try{
	    				usuario = LdapUserDetailsUtils.getUsuario();
	    				logger.debug("USUARIO CONOCIDO: " + usuario);
	    			}
	    			catch(Exception ad){
	    				logger.debug("USUARIO ANONIMO");
	    			}
	    			taggingSession.setUsuario(usuario);
	    			//*************************************************************
	    		}
		        
		        //El nuevo valor del tag no puede ser vacio
		        if (newTag !=null && !newTag.trim().equals(""))
		        	if(newTag.trim().split(" ").length>1){
		        		throw new ValidatorException("{tagging.modificarTag.soloUnaPalabra}");
		        	}
		        	else{
		        		this.getSrvTaggingServer().modificarTagDeUsuario(oldTag, newTag, usuario);
		        	}
		        else
					throw new ValidatorException("{tagging.modificarTag.exception}");
	        }
        }
     }







    /**
     * @see es.pode.tagging.presentacion.modificarTag.ModificarTagController#selectAction(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.modificarTag.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.tagging.presentacion.modificarTag.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String result = null;
		MessageResources resources = MessageResources.getMessageResources("application-resources");
		String action = form.getAccion();
		if(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"comun.aceptar").equals(action)) {
			result = "Aceptar";
		} else {
			result = "Cancelar";
		}

		return result;
    }









}