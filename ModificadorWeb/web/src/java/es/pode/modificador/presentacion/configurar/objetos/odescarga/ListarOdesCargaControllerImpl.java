// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.odescarga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.indexador.negocio.servicios.busqueda.DocVO12;
import es.pode.modificador.negocio.servicio.ODE;
import es.pode.modificador.negocio.servicio.OdeCargaMasivaVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController
 */
public class ListarOdesCargaControllerImpl extends ListarOdesCargaController
{

	private static final String APPLICATION_RESOURCES = "application-resources";

	private static String VACIA = "";
	
	private static final Logger logger = Logger
	.getLogger(ListarOdesCargaControllerImpl.class);



    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#submit(org.apache.struts.action.ActionMapping, 
     * es.pode.modificador.presentacion.configurar.objetos.odescarga.SubmitForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,
				locale);
		String action = form.getAction();
		if(action.equals(i18n.getString("comun.seleccionar")  )){
			List lista = form.getLocalizadorODERowSelection();
			if (lista != null) {
				form.setPaths(lista);
			}else{
				throw new ValidatorException("{resultadosBusqueda.seleccionar}");
			}
		}
     }



    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#guardarSeleccionados(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.GuardarSeleccionadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardarSeleccionados(ActionMapping mapping, 
    		es.pode.modificador.presentacion.configurar.objetos.odescarga.GuardarSeleccionadosForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		List seleccionados = getBusquedaOdesSession(request).getSeleccionados();
		BusquedaOdesSession sesion = getBusquedaOdesSession(request);
		sesion.setResultados(null);
		setBusquedaOdesSession(request, sesion);
		if (seleccionados!=null&&seleccionados.size() > 0) {
			List odes;
			if (getConfigurarModificacionSession(request).getConfiguracion()
					.getObjetos().getObjetos() != null) {
				odes = new ArrayList(Arrays.asList(getConfigurarModificacionSession(request)
						.getConfiguracion().getObjetos().getObjetos()));
			} else {
				odes = new ArrayList();
			}
			ODE nuevoOde;

			for (Iterator iter = seleccionados.iterator(); iter.hasNext();) {
				DocVO12 seleccionado = (DocVO12) iter.next();
				nuevoOde = new ODE();
				nuevoOde.setPublished(Boolean.TRUE);
				nuevoOde.setPath(seleccionado.getLocalizadorODE());
				nuevoOde.setTitulo(seleccionado.getTitulo());
				// Control de duplicados
				if (!odes.contains(nuevoOde))
					odes.add(nuevoOde);
			}

			getConfigurarModificacionSession(request).getConfiguracion()
					.getObjetos()
					.setObjetos((ODE[]) odes.toArray(new ODE[] {}));
		}
		this.removeBusquedaOdesSession(request);
	}


    /**
	 * Comprueba si la cadena dada tiene algo útil.
	 * 
	 * @param cadena
	 * @return
	 */
	private boolean vacia(String cadena) {
		return cadena == null || cadena.trim().equals(VACIA);
	}



    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#eliminar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.EliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminar(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.EliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

		String path = form.getPath();

		if (!vacia(path)) {
			BusquedaOdesSession sesion = getBusquedaOdesSession(request);
			List resultados = sesion.getResultados();
			List seleccionados = sesion.getSeleccionados();

			for (Iterator iterator = resultados.iterator(); iterator.hasNext();) {
				DocVO12 element = (DocVO12) iterator.next();
				if (element.getLocalizadorODE().equals(path)) {
					seleccionados.remove(element);
				}
			}
		} else {
			java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			ResourceBundle i18n = ResourceBundle.getBundle(
					APPLICATION_RESOURCES, locale);
			throw new ValidatorException(i18n
					.getString("resultadosObjetos.noHaySeleccion"));
		}
     }


    
    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#eliminarTodos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.EliminarTodosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarTodos(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.EliminarTodosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	getBusquedaOdesSession(request).setSeleccionados(null);
    	
    }
    

    
    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#borrarSesion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.BorrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	BusquedaOdesSession sesion=getBusquedaOdesSession(request);
//		sesion.setResultados(null);
//		sesion.setSeleccionados(null);
//		sesion.setId(null);
//		
//		setBusquedaOdesSession(request, sesion);
    	removeBusquedaOdesSession(request);
    }



    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#seleccionar(org.apache.struts.action.ActionMapping, 
     * es.pode.modificador.presentacion.configurar.objetos.odescarga.SeleccionarForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void seleccionar(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.SeleccionarForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Seleccionamos los marcados.");
    	List paths = form.getPaths();
		BusquedaOdesSession sesion = getBusquedaOdesSession(request);
		List resultados = sesion.getResultados();
		if (paths != null) {
			List actuales = sesion.getSeleccionados();
			if (actuales == null) {
				actuales=new ArrayList();
			}
			boolean alreadyAdded=false;
			for (Iterator iter = paths.iterator(); iter.hasNext();) {
				String path = (String) iter.next();
				for (Iterator iterator = resultados.iterator(); iterator
						.hasNext();) {
					DocVO12 element = (DocVO12) iterator.next();
					if (element.getLocalizadorODE().equals(path)) {
						if(!actuales.contains(element)) {
							actuales.add(element);
						} else {
							//Está repetido
							alreadyAdded=true;
						}
					}
				}
			}

			getBusquedaOdesSession(request).setSeleccionados(actuales);
			setBusquedaOdesSession(request, sesion);
			//Mensaje de ya existente
			if(alreadyAdded) {
				throw new ValidatorException("{resultadosBusqueda.repeated}");
			}
		}
     }


    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#seleccionarTodos(org.apache.struts.action.ActionMapping, 
     * es.pode.modificador.presentacion.configurar.objetos.odescarga.SeleccionarTodosForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void seleccionarTodos(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.SeleccionarTodosForm form, 
    	HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Seleccionamos todos.");
    	BusquedaOdesSession sesion = getBusquedaOdesSession(request);
		List resultados = sesion.getResultados();
		if (resultados != null) {
			List actuales = sesion.getSeleccionados();
			if (actuales != null) {
				for (Iterator iter = resultados.iterator(); iter.hasNext();) {
					DocVO12 este = (DocVO12) iter.next();
					// Evitamos duplicados					
					if (!actuales.contains(este))
						actuales.add(este);
				}
			} else {
				actuales = resultados;
			}
			getBusquedaOdesSession(request).setSeleccionados(actuales);
			setBusquedaOdesSession(request, sesion);
		}
    }

    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#buscar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.BuscarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscar(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.BuscarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		   	
		BusquedaOdesSession sesion = getBusquedaOdesSession(request);
		logger.debug("Recuperamos lista previa de resultados gracias a la id: "+sesion.getId());
		List resultados = sesion.getResultados();
		if (resultados == null) {
			if(logger.isDebugEnabled())logger.debug("No se encontraron resultados previos");
						
			try {
				
				//obtengo los odes de la carga
				OdeCargaMasivaVO[] resultado = getSrvHerramientaModificacion().obtenerOdesCargaMasiva(sesion.getId());
				
				
				resultados = new ArrayList();
				
				//si hemos obtenido resultados
				if (resultado != null) {
					DocVO12 docs[] = new DocVO12[resultado.length];
					for (int i = 0; i < docs.length; i++){
						
						//parseo cada ode y lo añado en la lista de resultados
						DocVO12 doc = new DocVO12();
						doc.setIdentificadorODE(resultado[i].getIdOde());
						doc.setTitulo(resultado[i].getTitulo());
						doc.setLocalizadorODE(resultado[i].getLocalizador().replaceAll("//", "/"));
						resultados.add(doc);
					}
				}else
					if(logger.isDebugEnabled()){
						logger.debug("Se recuperaron 0 resultados");
				// sesion.setResultados(Arrays.asList(docs));
						logger.debug("Guardamos los resultados en sesión");
					}
				
				sesion.setResultados(resultados);
				setBusquedaOdesSession(request, sesion);
			} catch (Exception e) {
				if(logger.isDebugEnabled())logger.debug("Capturada excepción: "+e);
				throw new ValidatorException("{resultadosBusqueda.excepcion}");
			}			
		}
		if(logger.isDebugEnabled())logger.debug("Pasamos lista de seleccionados a Form");
		form.setSeleccionados(sesion.getSeleccionados());
		// form.setResultados(Arrays.asList(docs));
		if(logger.isDebugEnabled())logger.debug("Pasamos lista de resultados a Form");
		form.setResultados(resultados);

		form.setIdiomaBuscadorBackingList(
				IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(
						LdapUserDetailsUtils.getIdioma()), "idLocalizacion",
				"nombre");
		
					
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.odescarga.ListarOdesCargaController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.odescarga.SelectActionForm, 
     * javax.servlet.http.HttpServletRequest, 
     * javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, 
    		es.pode.modificador.presentacion.configurar.objetos.odescarga.SelectActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,
				locale);

		String cancelar = "Cancelar";
		String seleccionar = "Seleccionar";
		String volver = "Volver";
		String selecTodos = "SeleccionarTodos";

		String action = form.getAction();
		if (action == null) {
			return volver;
		}
		
		logger.debug("Action es "+action);

		if (action.equals(i18n.getString("comun.cancelar")))
			return cancelar;
		else if (action.equals(i18n.getString("comun.seleccionar")))
			return seleccionar;
		else if (action.equals(i18n.getString("comun.seleccionarTodos")))
			return selecTodos;
		else if (action.equals(i18n.getString("comun.volver")))
			return volver;

		return volver;
    }


}