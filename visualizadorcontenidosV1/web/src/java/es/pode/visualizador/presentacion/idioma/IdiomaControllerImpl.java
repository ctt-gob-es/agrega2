// license-header java merge-point
package es.pode.visualizador.presentacion.idioma;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.visualizador.presentacion.Idioma.IdiomaController
 */
public class IdiomaControllerImpl extends IdiomaController
{

	private static Logger logger = Logger.getLogger(IdiomaControllerImpl.class);
	
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";




    /**
     * @see es.pode.visualizador.presentacion.Idioma.IdiomaController#cambiarIdioma(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.Idioma.CambiarIdiomaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cambiarIdioma(ActionMapping mapping, es.pode.visualizador.presentacion.idioma.CambiarIdiomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {        
        /** ------------------------------------------------------------------------------------------------------------------
         *  -------------------------- GUARDAMOS LA COOKIE CON EL NUEVO IDIOMA -----------------------------------------------
         *  ------------------------------------------------------------------------------------------------------------------
         */
        logger.debug("cambiarIdioma-- Guardamos la cookie con el idioma que se nos pasa ["+form.getIdioma()+"]");
        this.setCookieIdioma(response, form.getIdioma());
        
        /** ------------------------------------------------------------------------------------------------------------------
         *  --------------------------------------- SE REALIZA EL SENDREDIRECT -----------------------------------------------
         *  ------------------------------------------------------------------------------------------------------------------
         */
        
        request.getSession().setAttribute(ConstantesAgrega.DEFAULT_LOCALE, new Locale(form.getIdioma()));
        
        if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO)!=null && AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO).equals("true"))response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+request.getSession().getServletContext().getInitParameter("url_formularioAvanzado"));
        else response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+request.getSession().getServletContext().getInitParameter("url_portada"));
     }
    
    
    private void setCookieIdioma(HttpServletResponse response, String valorCookie)
    {
    	if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Se pone la cookie con nombre: ["+NOMBRECOOKIE+"]");
		Cookie cookie = new Cookie(NOMBRECOOKIE, valorCookie);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*30*600);
		response.addCookie(cookie);

		if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Despues de a�adir la cookie " + NOMBRECOOKIE + ": " + cookie + " con valor: " + valorCookie + " al objeto response");
	}









}