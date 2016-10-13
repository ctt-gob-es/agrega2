// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.ruta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.Change;
import es.pode.modificador.negocio.servicio.Changes;
import es.pode.modificador.negocio.servicio.ConfiguracionTarea;
import es.pode.modificador.negocio.servicio.Folder;
import es.pode.modificador.negocio.servicio.ODE;
import es.pode.modificador.negocio.servicio.Objects;
import es.pode.modificador.presentacion.DecisorOffline;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.objetos.ruta.IndicarRutaController
 */
public class IndicarRutaControllerImpl extends IndicarRutaController
{
	private static final long serialVersionUID = 3716361791306390232L;

	/**
     * @see es.pode.modificador.presentacion.configurar.objetos.ruta.IndicarRutaController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.ruta.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.ruta.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
//		String botonCancelar=i18n.getString("comun.cancelar");
		// El punto de retorno es distinto para la version offline
		String cancelar= DecisorOffline.esOffline() ? "CancelarOffline" : "Cancelar";
		String continuar=i18n.getString("comun.aceptar");
		
		String action=form.getAction();
    	if(action==null) {
    		return cancelar;
    	}
    	
//    	if(action.equals(cancelar)) return cancelar;
//    	else 
    	if (action.equals(continuar)) return "Aceptar";

        return cancelar;
    }

    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.ruta.IndicarRutaController#a�adirObjeto(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.ruta.AAdirObjetoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void a�adirObjeto(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.ruta.AAdirObjetoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		if (form != null && form.getPath()!=null &&!(form.getPath().equals(""))) {
			String path = form.getPath();
			ConfiguracionTarea configuracion = getConfigurarModificacionSession(request).getConfiguracion();
			if(configuracion==null) {
				ConfiguracionTarea tarea = new ConfiguracionTarea();
				tarea.setObjetos(new Objects());
				tarea.setCambios(new Changes());
				tarea.getObjetos().setObjetos(new ODE[0]);
				tarea.getObjetos().setPaths(new Folder[0]);
				tarea.getCambios().setCambios(new Change[0]);
				getConfigurarModificacionSession(request).setConfiguracion(tarea);
				configuracion=tarea;
			}
			
//			Folder nuevosObjetos[];
			Folder objetos[]= null;
			
			if (configuracion.getObjetos()!=null && configuracion.getObjetos().getPaths() != null) {
				objetos=configuracion.getObjetos().getPaths();
			}
//				nuevosObjetos = new Folder[objetos.length + 1];
//			} else {
//				nuevosObjetos = new Folder[1];
//			}
			Folder nuevo = new Folder(path.trim(),null);

			if (objetos != null) {
			// Comprobamos si el objeto ya estaba en la lista.
				ArrayList listaObjetos = new ArrayList(Arrays.asList(objetos));
				if(!listaObjetos.contains(nuevo)) {
					//A�ado nuevo path a la lista
					listaObjetos.add(nuevo);
					configuracion.getObjetos().setPaths((Folder[])listaObjetos.toArray(new Folder[]{}));
				} else {
					throw new ValidatorException("{indicarRuta.repeated}");
				}
			}
		} else {
			throw new ValidatorException("{indicarRuta.empty}");
		}
	}

	public void recuperarIdioma(ActionMapping mapping, RecuperarIdiomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
	}
    
} 