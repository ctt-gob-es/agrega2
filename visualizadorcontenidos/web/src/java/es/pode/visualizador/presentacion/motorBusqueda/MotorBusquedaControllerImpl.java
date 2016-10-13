// license-header java merge-point
package es.pode.visualizador.presentacion.motorBusqueda;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.motorBusqueda.MotorBusquedaController
 */
public class MotorBusquedaControllerImpl extends MotorBusquedaController
{

	private static Logger logger = Logger.getLogger(MotorBusquedaControllerImpl.class);
	private static final String COOKIENAME = "motorBusquedaCookie";
	private static final String VALORCOOKIENAME = "MOTORBUSQUEDA";


    /**
     * @see es.pode.visualizador.presentacion.motorBusqueda.MotorBusquedaController#anadirMotorBusqueda(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.motorBusqueda.AnadirMotorBusquedaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirMotorBusqueda(ActionMapping mapping, es.pode.visualizador.presentacion.motorBusqueda.AnadirMotorBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	 /** ------------------------------------------------------------------------------------------------------------------
         *  ----------------- GUARDAMOS LA COOKIE DE SI SE AÑADIO BUSCADORAGREGA COMO MOTOR DE BUSQUEDA -----------------------
         *  -------------------------------------------------------------------------------------------------------------------
         */
        logger.debug("anadirMotorBusqueda-- Guardamos la cookie que nos indica que el buscadorAgrega se ha añadido como motor de busqueda de nuestro navegador ["+VALORCOOKIENAME+"]");
        this.setCookieIdioma(response);
        
        /** ------------------------------------------------------------------------------------------------------------------
         *  ---------------------------------------- SE REDIRIGE A LA PAGINA DE PORTADA --------------------------------------
         *  ------------------------------------------------------------------------------------------------------------------
         */
        response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+request.getSession().getServletContext().getInitParameter("url_portada"));
    }

    private void setCookieIdioma(HttpServletResponse response)
    {
    	if (logger.isDebugEnabled()) logger.debug("anadirMotorBusqueda-- Se pone la cookie con nombre: ["+COOKIENAME+"]");
		Cookie cookie = new Cookie(COOKIENAME, VALORCOOKIENAME);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*30*600);
		response.addCookie(cookie);

		if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Despues de añadir la cookie " + COOKIENAME + ": " + cookie + " con valor: " + VALORCOOKIENAME + " al objeto response");
	}


}