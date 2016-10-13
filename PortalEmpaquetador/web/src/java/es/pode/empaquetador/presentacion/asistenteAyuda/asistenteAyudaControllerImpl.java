// license-header java merge-point
package es.pode.empaquetador.presentacion.asistenteAyuda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.asistenteAyuda.asistenteAyudaController
 */
public class asistenteAyudaControllerImpl extends asistenteAyudaController
{
	private static Logger logger = Logger.getLogger(asistenteAyudaControllerImpl.class);
    /**
     * @see es.pode.empaquetador.presentacion.asistenteAyuda.asistenteAyudaController#activarDesactivar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.asistenteAyuda.ActivarDesactivarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void activarDesactivar(ActionMapping mapping, es.pode.empaquetador.presentacion.asistenteAyuda.ActivarDesactivarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String returnURL=request.getParameter("returnURL");
      //si está activado se desactiva; si está desactivado se activa
    	if (this.getEmpaquetadorSession(request).isAsistenteAyuda()){
    		//está activo y lo vams a desactivar
    		this.getEmpaquetadorSession(request).setAsistenteAyuda(false);    	
    	} else {
    		//está desactivado y lo vams a activar
    		this.getEmpaquetadorSession(request).setAsistenteAyuda(true);
    	}
    	this.getEmpaquetadorSession(request).setMensajeAsistente("");
    	//la url de vuelta... la formamos como en guardar
    	 if(returnURL==null || returnURL.trim().equals("")) {
         	String redirect = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+mapping.findForward("gestor.archivos").getPath();
         	if(logger.isDebugEnabled()) logger.debug("ReturnURL es null en guardar. Redirijo a " +redirect);
         	response.sendRedirect(redirect);
         } else {
 			/*
 			 * Concateno host y subdominio para URLs tipo http://host/agrega:
 			 * returnURL se genera a partir de una html:rewrite, no es necesario
 			 * incluir el subdominio, porque de haberlo, ya esta inluido.
 			 */
         	returnURL="http://"+LdapUserDetailsUtils.getHost()+returnURL;
         	if(logger.isDebugEnabled()) logger.debug("ReturnURL es " + returnURL);
         	response.sendRedirect(returnURL);
         }
     }



}