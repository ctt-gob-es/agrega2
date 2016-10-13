// license-header java merge-point
package es.pode.herramientaOffline.presentacion.configurarLdap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.offline.configuracion.ConfiguracionDao;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.herramientaOffline.presentacion.configurarLdap.ConfigurarLdapController
 */
public class ConfigurarLdapControllerImpl extends ConfigurarLdapController
{

    /**
     * @see es.pode.herramientaOffline.presentacion.configurarLdap.ConfigurarLdapController#configurarLdap(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.configurarLdap.ConfigurarLdapForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void configurarLdap(ActionMapping mapping, es.pode.herramientaOffline.presentacion.configurarLdap.ConfigurarLdapForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		
	    	// Introducimos validación del login para rechazar caracteres no alfanumericos
    		String pattern = "[\\w]*";
    		if(form.getLogin()!=null && !form.getLogin().matches(pattern)) {
    			throw new ValidatorException("{configurar.error.login}");
    		}
		    		
	        configurarPropiedad(ConfiguracionDao.EMPAQUETADOR, form.getEmpaquetador(),true);
	        configurarPropiedad(ConfiguracionDao.CATALOGADOR, form.getCatalogador(),true);
	        configurarPropiedad(ConfiguracionDao.NOMBRE, form.getNombre(),false);
	        configurarPropiedad(ConfiguracionDao.APELLIDOS, form.getApellidos(),false);
	        configurarPropiedad(ConfiguracionDao.USER, form.getLogin(),true);
	        configurarPropiedad(ConfiguracionDao.EMAIL, form.getMail(),false);
	        configurarPropiedad(ConfiguracionDao.IDIOMA, form.getIdioma(),true);
	        configurarPropiedad(ConfiguracionDao.VISUALIZADOR, form.getVisualizador(),true);
	        ConfiguracionDao.instance().save();
    	} catch (Exception e) {
			Logger.getLogger(ConfigurarLdapControllerImpl.class).debug(e);
			throw e;
		}
     }
    
    private void configurarPropiedad(String propiedad, String valor, boolean obligatoria) throws Exception {
    	if(obligatoria && (valor==null || "".equals(valor)))
    		throw new ValidatorException("{configurar.error.obligatorios}");
    	String valorNotNull = valor == null ? "" : valor;
    	
    	ConfiguracionDao.instance().setProperty(propiedad, valorNotNull);
    	
    }

	
	public void cargarDatos(ActionMapping mapping, CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		form.setIdioma(LdapUserDetailsUtils.getIdioma());
		form.setEmpaquetador(LdapUserDetailsUtils.getEmpaquetador());
		form.setCatalogador(LdapUserDetailsUtils.getCatalogador());
		form.setMail(LdapUserDetailsUtils.getMail());
		form.setLogin(LdapUserDetailsUtils.getLogin());
		form.setApellidos(ConfiguracionDao.instance().getProperty(ConfiguracionDao.APELLIDOS));
		form.setNombre(ConfiguracionDao.instance().getProperty(ConfiguracionDao.NOMBRE));
		LocalizacionIdiomaVO[] idiomas = I18n.getInstance().obtenerIdiomasPlataformaLocalizados(((java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		form.setIdiomas(java.util.Arrays.asList(idiomas));
		form.setVisualizador(LdapUserDetailsUtils.getVisualizador());
		
	}

}