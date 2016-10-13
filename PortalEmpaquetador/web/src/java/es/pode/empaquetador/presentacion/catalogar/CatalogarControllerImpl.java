// license-header java merge-point
package es.pode.empaquetador.presentacion.catalogar;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.empaquetador.presentacion.CatalogacionBean;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.empaquetador.presentacion.catalogar.CatalogarController
 */
public class CatalogarControllerImpl extends CatalogarController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private GestorSesion gs = new GestorSesion();
	
	public final void catalogar(ActionMapping mapping,
			es.pode.empaquetador.presentacion.catalogar.CatalogarForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);

		List submanifestPath = sesEmpaq.getSubmanifestPath();
		
		String identificador = sesEmpaq.getIdLocalizador();

		String idElemento = "";
		String href = form.getHref();
		if (form.getHref() != null) {
			idElemento = form.getIdentifier().concat(form.getHref());
		} else {
			idElemento = form.getIdentifier();
		}
		String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		String usuario = gs.obtenerUsuario(request);
		if(logger.isDebugEnabled()) logger.debug("Usuario recuperado " + usuario);
		String url = request.getSession().getServletContext().getInitParameter(
				"url_catalogador");
		
		url = ( url + "?idioma=" + idioma + "&usuario=" + usuario
				+ "&identificador=" + idElemento);
	
		if(form.getReturnURL()!=null && !form.getReturnURL().equals(""))
		{
			url= url.concat("&returnURL="+form.getReturnURL());
		}
		if(logger.isDebugEnabled()) logger.debug("Llamando al servicio para preparar la catalogacion con identificador = " + identificador + " , idElemento = " + form.getIdentifier() + " , href = "  + href);
		this.getSrvGestorManifestService().prepararCatalogacion(identificador,
				form.getIdentifier(), href);

		/*
		 * Registramos en la lista de pendientes de Asignacion de Lom un bean
		 * con identifier y href del elemento catalogado
		 */
		if(logger.isDebugEnabled()) logger.debug("Registrando en la sesion un bean con los datos del objeto catalogado");
		CatalogacionBean bean = new CatalogacionBean();
		bean.setIdentifier(form.getIdentifier());
		bean.setHref(href);
		sesEmpaq.getPendientesCatalogacion().add(bean);

		/*
		 * Session Timeout: Para evitar que caduque la sesion del empaquetador
		 * mientras se trabaja con el catalogador variamos el time-out de la
		 * sesion. En el punto de retorno se restaura al valor normal.
		 */
		request.getSession().setAttribute("timeout.empaquetador", new Integer(request.getSession().getMaxInactiveInterval()));
		logger.debug("Timeout previo al cambio = " + request.getSession().getMaxInactiveInterval() + "s");
		String timeoutExtendido = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_EXTENDIDO);
		logger.debug("Modificando el timeout del empaquetador a " + timeoutExtendido);
		request.getSession().setMaxInactiveInterval(Integer.parseInt(timeoutExtendido));
		// Concatenamos host y subdominio para URLs tipo http://host/agrega
		url = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+url;
		if(logger.isDebugEnabled()) logger.debug("Redirigiendo a " + url);
		response.sendRedirect(url);
	}

}