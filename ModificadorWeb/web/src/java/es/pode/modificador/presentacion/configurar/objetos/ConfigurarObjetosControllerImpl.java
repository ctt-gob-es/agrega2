// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos;

import java.util.ResourceBundle;

import org.apache.struts.action.ActionMapping;

import es.pode.modificador.presentacion.DecisorOffline;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.modificador.presentacion.configurar.objetos.ConfigurarObjetosController
 */
public class ConfigurarObjetosControllerImpl extends ConfigurarObjetosController
{

    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.ConfigurarObjetosController#selectOption(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.SelectOptionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectOption(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.SelectOptionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);

		String botonCancelar = i18n.getString("comun.cancelar");
		String botonRuta = i18n.getString("configurarObjeto.boton.ruta");
		String botonBuscar = i18n.getString("configurarObjeto.boton.buscar");
		String botonCargas = i18n.getString("configurarObjeto.boton.cargas");
		String cancelar = "Cancelar";
		if (form.getAction().equals(botonCancelar))
			return cancelar;
		String option = form.getOption();
		if (option == null) {
			return cancelar;
		}
		if (option.equals(botonRuta))
			return "Ruta";
		else if (option.equals(botonBuscar))
			return "Buscar";
		else if (option.equals(botonCargas))
			return "Cargas";

		return cancelar;
	}

	public void recuperarIdioma(ActionMapping mapping, RecuperarIdiomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");		
	}

	public boolean esOffline(ActionMapping mapping, EsOfflineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return DecisorOffline.esOffline();
	}

}