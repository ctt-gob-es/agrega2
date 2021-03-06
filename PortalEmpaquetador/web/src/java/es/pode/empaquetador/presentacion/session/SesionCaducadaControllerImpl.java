// license-header java merge-point
package es.pode.empaquetador.presentacion.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.presentacion.DecisorOffline;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.session.SesionCaducadaController
 */
public class SesionCaducadaControllerImpl extends SesionCaducadaController
{

	private static Logger logger = Logger.getLogger(SesionCaducadaControllerImpl.class);

    /**
     * @see es.pode.empaquetador.presentacion.session.SesionCaducadaController#redirect(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.session.RedirectForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void redirect(ActionMapping mapping, es.pode.empaquetador.presentacion.session.RedirectForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	////debido a la identidad federada ya no se borran las cookies 
//    	Borramos las cookies
//    	Cookie c = new Cookie("JSESSIONID", null);
//		c.setPath("/");
//		logger.debug("despues de fijar el path " + c.getPath());
//		c.setMaxAge(0);
//		response.addCookie(c);
//		logger.debug("despues de a�adir la cookie a la response");
//		
//		Cookie cookieAutenticated = new Cookie("AUTENTICADO", null);
//		cookieAutenticated.setPath("/");
//		logger.debug("despues de fijar el path " + cookieAutenticated.getPath());
//		cookieAutenticated.setMaxAge(0);
//		response.addCookie(cookieAutenticated);
//		logger.debug("despues de a�adir la cookie autenticado a la response");
//		logger.debug("ASC- Probando Cookies ");
//		Cookie cookieOpenId = new Cookie("OPENID", null);
//		cookieOpenId.setPath("/");
//		logger.debug("despues de fijar el path " + cookieOpenId.getPath());
//		cookieOpenId.setMaxAge(0);
//		response.addCookie(cookieOpenId);
//		Cookie[] cokis= request.getCookies();
//		
//		if (cokis!=null)  {
//			for (int ci=0; ci<cokis.length; ci++) {
//				Cookie unaCokkie= cokis[ci];
//				logger.debug("cookie " + ci + " :  ");
//				logger.debug("Nombre Cookie " + unaCokkie.getName() + " path " + unaCokkie.getPath());
//				logger.debug("maxAge " + unaCokkie.getMaxAge() + " dominio " + unaCokkie.getDomain() + " valor " + unaCokkie.getValue() + " comment " + unaCokkie.getComment());
//			}
//		}
//		String urlInicioPortal="";
    	//urlInicioPortal=request.getSession().getServletContext().getInitParameter("url_portaladministracion"); //portaladministracion/
//    	response.sendRedirect("../../" + urlInicioPortal);//Redirigimos a Inicio Portal
		boolean estamosOffline=false;
		estamosOffline= DecisorOffline.esOffline();
		if (!estamosOffline) {
			//Para Borrar las Cookies del Cas, se ha hecho una modificacion en �l generando un controller y jsp auxiliar
			//desde aqui, tenemos que llamar al nuevo controller del cas, para ello debemos recuperar del fichero authbackend.properties
			//su url, es en el unico fichero en que aparece; http://cas.desarrollo.agrega.indra.es/cas + /logoutAux
			//este controller ira a la jsp que redirige a su vez al portaladministracion
//	    	ResourceBundle authback=ResourceBundle.getBundle("authbackend");
//			String cas_url= authback.getString("cas.http.url");
//	    	response.sendRedirect(cas_url + "/logoutAux"); //http://cas.desarrollo.agrega.indra.es/cas
			String urlOnline=request.getSession().getServletContext().getInitParameter("url_portaladministracion");
		    response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/" +  urlOnline);
		} else {
			String urlOffline= request.getSession().getServletContext().getInitParameter("url_Offline");
		    response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/" + urlOffline);	
    }
    	//URL OFFLINE /herramientaoffline o  /herramientaoffline/InicialCU/InicialCU.do 

    }

}
