/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.ejecutadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import es.pode.modificador.negocio.servicio.EstadosTarea;
import es.pode.modificador.negocio.servicio.ListadoTareasModificacionVO;
import es.pode.modificador.negocio.servicio.ModificacionVO;
import es.pode.modificador.negocio.servicio.ResultadoModificacionVO;
import es.pode.modificador.presentacion.DecisorOffline;
import es.pode.modificador.presentacion.informes.tarea.ConstantesInforme;
import es.pode.soporte.constantes.ConstantesAgrega;





/**
 * @see es.pode.modificador.presentacion.ejecutadas.ModificacionesEjecutadasController
 */
public class ModificacionesEjecutadasControllerImpl extends ModificacionesEjecutadasController
{ 

	private final static String MENSAJE_ERROR_DETALLES = "modificacionesPendientes.msgErrorEliminar";
	private final static String MENSAJE_EXITO_DETALLE = "modificacionesPendientes.msgExitoEliminar";
	private final static String MENSAJE_ERROR_DETALLES_REPROGRAMAR="modificacionesPendientes.msgErrorReprogramar";
	private final static String MENSAJE_EXITO_DETALLE_REPROGRAMAR = "modificacionesPendientes.msgExitoReprogramar";
	private final static String MENSAJE_ERROR_DETALLES_APROBAR="modificacionesPendientes.msgErrorAprobar";
	private final static String MENSAJE_EXITO_DETALLE_APROBAR= "modificacionesPendientes.msgExitoAprobar";

	private static final Logger logger =Logger.getLogger(ModificacionesEjecutadasControllerImpl.class);


    /**
     * @see es.pode.modificador.presentacion.ejecutadas.ModificacionesEjecutadasController#recuperarModificaciones(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.ejecutadas.RecuperarModificacionesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarModificaciones(ActionMapping mapping, es.pode.modificador.presentacion.ejecutadas.RecuperarModificacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	request.getSession().setAttribute("offline", new Boolean(DecisorOffline.esOffline()));
    	ListadoTareasModificacionVO modificacionesEjecutadas = this.getSrvHerramientaModificacion().obtenerTodasModificaciones();
    	ModificacionVO[] ejecutadas = modificacionesEjecutadas.getEjecutadas();
    	ModificacionVO[] pendientes = modificacionesEjecutadas.getPendientes();
		List modificacionesPendientesList=new ArrayList();
		List modificacionesEjecutadasList=new ArrayList();
		for(int i=0; i<ejecutadas.length;i++)
		{
			modificacionesEjecutadasList.add(i, ejecutadas[i]);
		}
		for(int i=0; i<pendientes.length;i++)
		{
			modificacionesPendientesList.add(i, pendientes[i]);
		}
		if(logger.isDebugEnabled())logger.debug("las modificaciones se han recuperado con éxito");
		form.setEjecutadas(modificacionesEjecutadasList);
		form.setPendientes(modificacionesPendientesList);
		form.setOffline(DecisorOffline.esOffline());
	}

    
    public final void eliminarModificaciones(ActionMapping mapping, es.pode.modificador.presentacion.ejecutadas.EliminarModificacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

		Long[] identificadores=form.getIdModificaciones();
    	Boolean resultadoEliminacion=this.getSrvHerramientaModificacion().eliminarModificacion(identificadores);
    	if(resultadoEliminacion.equals(Boolean.FALSE))
    	{
    		saveErrorMessage(request, MENSAJE_ERROR_DETALLES);
    		
    	}
    	else if (resultadoEliminacion.equals(Boolean.TRUE)) 
    	{
    		saveSuccessMessage(request, MENSAJE_EXITO_DETALLE);
		}
    }


	public void recuperarInforme(ActionMapping mapping, RecuperarInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String idModificacion=form.getIdModificacion();
		Long idModificacionLong=Long.valueOf(idModificacion);
		ModificacionVO modificacionVO = this.getSrvHerramientaModificacion().obtenerModificacion(idModificacionLong);
		String nombreTarea=modificacionVO.getNombre();
		EstadosTarea status = modificacionVO.getResultado();
		String EstadoTarea = status.getValue();
		ResultadoModificacionVO[] resultadosModificacion = this.getSrvHerramientaModificacion().obtenerResultadoModificacion(idModificacionLong);
		List odes= new ArrayList();
		this.getInformeSession(request).setIdModificacion(idModificacionLong);
		
		for(int i=0; i<resultadosModificacion.length;i++)
		{
			//Si Titulo es vacio, le pongo ultimo token path
			ResultadoModificacionVO resultado = resultadosModificacion[i];
			if(resultado.getTitulo()==null||resultado.getTitulo().equals("")) {
				String path = resultado.getPathOriginal();
				if(path.lastIndexOf('/')==path.length()-1) {
					path=path.substring(0,path.lastIndexOf('/'));
				}
				String nombre= path.substring(path.lastIndexOf('/')+1);
				resultado.setTitulo(nombre);
			}
			odes.add(i, resultado);
			
		}
		
		this.getInformeSession(request).setNombreTarea(nombreTarea);
		this.getInformeSession(request).setResultadoTarea(EstadoTarea);
		this.getInformeSession(request).setDescResultado(modificacionVO.getMsgError());
		this.getInformeSession(request).setOdes(odes);
		this.getInformeSession(request).setOrigen(ConstantesInforme.Ejecutadas);
		
	}


	public String submitConfirmacion(ActionMapping mapping, SubmitConfirmacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String result = null;
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String accion = form.getAction();
    	if (accion.equals(i18n.getString("tareasEjecutadas.eliminar.confirmacion.aceptar")))
    	{
    		result = "Aceptar";
    	} else {
    		result = "Cancelar";
    	}
    	form.setIdModificaciones((Long[])request.getSession().getAttribute("idModificaciones"));
    	request.getSession().removeAttribute("idModificaciones");
    	
    	return result;
	}


	public void submitEliminar(ActionMapping mapping, SubmitEliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String accion = form.getAction();
    	Long[] identificadores = null;
    	logger.debug("accion vale "+accion);
    	logger.debug("i18n "+i18n.getString("modificacionesPendientes.eliminar"));
    	logger.debug("i18n2 "+i18n.getString("tareasEjecutadas.replanificar"));
    	if (accion.equals(i18n.getString("modificacionesPendientes.eliminar")))
    	{
    		logger.debug("Elimino");
    		
    		if(form.getIdModificacionRowSelection()==null || form.getIdModificacionRowSelection().size()==0)
    		{
    			throw new ValidatorException("{modificacionesPendientes.elementoSeleccionado.exception}");	
    		}
    		// Transformo a Long[]
    		String[] ids = form.getIdModificacionRowSelectionAsArray();
    		identificadores = new Long[ids.length];
    		for(int i=0;i<ids.length;i++) {
    			identificadores[i] = Long.valueOf(ids[i]);
    		}
    		//form.setIdentificadores(lista);
    		form.setIdModificaciones(identificadores);
    	}else if (accion.equals(i18n.getString("tareasEjecutadas.replanificar")))
    	{
    		logger.debug("Reprogramo");
    		if(form.getIdModificacionRowSelection()==null || form.getIdModificacionRowSelection().size()==0)
    		{
    			throw new ValidatorException("{tareasEjecutadas.replanificar.error}");	
    		}
    		String[] ids = form.getIdModificacionRowSelectionAsArray();
    		identificadores = new Long[ids.length];
    		for(int i=0;i<ids.length;i++) {
    			identificadores[i] = Long.valueOf(ids[i]);
    		}
    		//form.setIdentificadores(lista);
    		form.setIdModificaciones(identificadores);
    	}else if (accion.equals(i18n.getString("tareasEjecutadas.aprobar")))
    	{
    		logger.debug("aprobamos");
    		if(form.getIdModificacionRowSelection()==null || form.getIdModificacionRowSelection().size()==0)
    		{
    			throw new ValidatorException("{tareasEjecutadas.aprobar.error}");	
    		}
    		String[] ids = form.getIdModificacionRowSelectionAsArray();
    		identificadores = new Long[ids.length];
    		for(int i=0;i<ids.length;i++) {
    			identificadores[i] = Long.valueOf(ids[i]);
    		}
    		//form.setIdentificadores(lista);
    		form.setIdModificaciones(identificadores);
    	}
		
		request.getSession().setAttribute("idModificaciones", identificadores);
	   

	}


	@Override
	public void replanificarModificaciones(ActionMapping mapping, ReplanificarModificacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.debug("replanificarModificaciones "+Arrays.toString(form.getIdModificaciones()));
		Long[] identificadores=form.getIdModificaciones();
		//Identificadores de las tareas a replanificar
		logger.debug("identificadores[0] "+identificadores[0]);
		Boolean vuelta = this.getSrvHerramientaModificacion().replanificarModificaciones(identificadores);
		logger.debug("Se han replanificado las siguientes tareas de modificacion "+identificadores.length);
		if(vuelta.equals(Boolean.FALSE))
    	{
    		saveErrorMessage(request, MENSAJE_ERROR_DETALLES_REPROGRAMAR);
    		
    	}
    	else if (vuelta.equals(Boolean.TRUE)) 
    	{
    		saveSuccessMessage(request, MENSAJE_EXITO_DETALLE_REPROGRAMAR);
		}
		//Llamaremos a un método del servicio HerramientaModificacion para eliminar los backups y los registros asociados 
		//de la tabla RESULTADO_MODIFICACION
		/*
    	Boolean resultadoEliminacion=this.getSrvHerramientaModificacion().eliminarModificacion(identificadores);
    	if(resultadoEliminacion.equals(Boolean.FALSE))
    	{
    		saveErrorMessage(request, MENSAJE_ERROR_DETALLES);
    		
    	}
    	else if (resultadoEliminacion.equals(Boolean.TRUE)) 
    	{
    		saveSuccessMessage(request, MENSAJE_EXITO_DETALLE);
		}
		*/
		
	}



	public String submitConfirmacionReplanificar(ActionMapping mapping, SubmitConfirmacionReplanificarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String result = null;
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String accion = form.getAction();
    	logger.debug("submitConfirmacionReplanificar "+accion);
    	if (accion.equals(i18n.getString("tareasEjecutadas.eliminar.confirmacion.aceptar")))
    	{
    		result = "Aceptar";
    	} else {
    		result = "Cancelar";
    	}
    	form.setIdModificaciones((Long[])request.getSession().getAttribute("idModificaciones"));
    	request.getSession().removeAttribute("idModificaciones");
    	logger.debug("result "+result);
    	return result;
	}


	@Override
	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.debug("selectAction");
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		logger.debug("form.getAction() "+form.getAction());
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{modificacionesPendientes.exception}");
		}
			
		else if (actionSubmit.equals(i18n.getString("tareasEjecutadas.replanificar")) )
		{
			result = "Replanificar";
		} 
		else if (actionSubmit.equals(i18n.getString("modificacionesPendientes.eliminar")))
		{
//			 Para no perderla en la pantalla de confirmacion, la meto en sesion.
    		result = "Eliminar";
		} 
	    return result;
	}


	@Override
	public String selectActionPendientes(ActionMapping mapping, SelectActionPendientesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("selectActionPendientes");
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		logger.debug("form.getAction() "+form.getAction());
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{modificacionesPendientes.exception}");
		}
			
		else if (actionSubmit.equals(i18n.getString("tareasEjecutadas.aprobar")) )
		{
			result = "Aprobar";
		} 
		else if (actionSubmit.equals(i18n.getString("modificacionesPendientes.eliminar")))
		{
//			 Para no perderla en la pantalla de confirmacion, la meto en sesion.
    		result = "Eliminar";
		} 
	    return result;
	}


	@Override
	public void aprobarModificaciones(ActionMapping mapping, AprobarModificacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.debug("aprobarModificaciones "+Arrays.toString(form.getIdModificaciones()));
		Long[] identificadores=form.getIdModificaciones();
		//Identificadores de las tareas a replanificar
		logger.debug("identificadores[0] "+identificadores[0]);
		Boolean vuelta = this.getSrvHerramientaModificacion().finalizarModificaciones(identificadores);
		if(vuelta.equals(Boolean.FALSE))
    	{
    		saveErrorMessage(request, MENSAJE_ERROR_DETALLES_APROBAR);
    		
    	}
    	else if (vuelta.equals(Boolean.TRUE)) 
    	{
    		saveSuccessMessage(request, MENSAJE_EXITO_DETALLE_APROBAR);
		}
		logger.debug("Se han aprobado las siguientes tareas de modificacion "+identificadores.length);
		
	}


	@Override
	public String submitConfimacionAprobar(ActionMapping mapping, SubmitConfimacionAprobarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = null;
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String accion = form.getAction();
    	if (accion.equals(i18n.getString("tareasEjecutadas.eliminar.confirmacion.aceptar")))
    	{
    		result = "Aceptar";
    	} else {
    		result = "Cancelar";
    	}
    	form.setIdModificaciones((Long[])request.getSession().getAttribute("idModificaciones"));
    	request.getSession().removeAttribute("idModificaciones");
    	
    	return result;
	}

}