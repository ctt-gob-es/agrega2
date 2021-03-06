// license-header java merge-point
package es.pode.presentacion.cerrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.utils.OdeSessionUtils;



/**
 * @see es.pode.presentacion.cerrar.CerrarController
 */
public class CerrarControllerImpl extends CerrarController
{
	protected static Logger logger = Logger.getLogger(CerrarControllerImpl.class);
    /**
     * @see es.pode.presentacion.cerrar.CerrarController#borrarSesion(org.apache.struts.action.ActionMapping, es.pode.presentacion.cerrar.BorrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(ActionMapping mapping, es.pode.presentacion.cerrar.BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	VisualizadorSession sesion = getVisualizadorSession(request);
        String identificador = form.getIdentificador();
        if(identificador!=null) {
        	OdeSessionUtils.removeOdeSession(sesion, identificador);
        }
        
        if(OdeSessionUtils.countOdeSession(sesion)==0) {
        	removeVisualizadorSession(request);
        }
        
     }
}