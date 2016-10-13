// license-header java merge-point
package es.pode.tagging.presentacion.adminTag.modificar;

import java.util.Locale;

import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.pode.soporte.constantes.ConstantesAgrega;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.adminTag.modificar.ModificarController
 */
public class ModificarControllerImpl extends ModificarController
{






    /**
     * @see es.pode.tagging.presentacion.adminTag.modificar.ModificarController#modificar(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.adminTag.modificar.ModificarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void modificar(ActionMapping mapping, es.pode.tagging.presentacion.adminTag.modificar.ModificarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String newTag = form.getNewTag();
        String oldTag = form.getTagOld();
        if(!oldTag.equals(newTag.trim()))
        {
        
	        //El nuevo valor del tag no puede ser vacio
	        if (newTag !=null && !newTag.trim().equals("")){
	        	if(newTag.trim().split(" ").length>1){
	        		throw new ValidatorException("{tagging.modificarTag.soloUnaPalabra}");
	        	}
	        	else{
	        		this.getSrvTaggingServer().modificarTag(oldTag, newTag);
	        	}
	        }
	        else
				throw new ValidatorException("{tagging.modificarTag.exception}");
        }
     }







    /**
     * @see es.pode.tagging.presentacion.adminTag.modificar.ModificarController#selectAction(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.adminTag.modificar.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.tagging.presentacion.adminTag.modificar.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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