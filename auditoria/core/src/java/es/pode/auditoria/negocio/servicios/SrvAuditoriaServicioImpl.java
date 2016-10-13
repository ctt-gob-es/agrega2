// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.servicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.xml.transform.stream.StreamSource;

import org.apache.axis.attachments.SourceDataSource;
import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.ParametroAuditoriaUsuariosVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioActivoVO;
import es.pode.auditoria.negocio.dominio.IdOdeCriteria;
import es.pode.auditoria.negocio.dominio.IdiomaOdeDesdeHastaCriteria;
import es.pode.auditoria.negocio.dominio.Operacion;
import es.pode.auditoria.negocio.dominio.PeticionFeed;
import es.pode.auditoria.negocio.dominio.TitulosOdePorUsuarioDesdeHastaCriteria;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonConRutaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonPathVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.indexador.negocio.servicios.auditoria.CoberturaVO;
import es.pode.indexador.negocio.servicios.auditoria.ParametroAuditIndexadorVO;
import es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO;
import es.pode.planificador.negocio.servicios.InformeCargaVO;
import es.pode.planificador.negocio.servicios.ParametrosVO;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO;
import es.pode.publicacion.negocio.servicios.DetalleTransicionVO;
import es.pode.publicacion.negocio.servicios.EstadoActividadVO;
import es.pode.publicacion.negocio.servicios.ODEsEstadoVO;
import es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO;
import es.pode.publicacion.negocio.servicios.PesoODEVO;
import es.pode.soporte.estadisticas.EstadisticasProperties;
import es.pode.soporte.estadisticas.EstadisticasPropertiesImpl;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.valoracion.negocio.servicios.auditoria.ParametroAuditValoracionVO;
import es.pode.valoracion.negocio.servicios.auditoria.ValoracionODEVO;

/**
 * @see es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio
 */

@SuppressWarnings("all")
public class SrvAuditoriaServicioImpl extends es.pode.auditoria.negocio.servicios.SrvAuditoriaServicioBase
{

	private static final String SQL_ANY = "%";
	private static Logger logger = Logger.getLogger(SrvAuditoriaServicioImpl.class);
	private java.util.Properties pAuditoriaProperties = null;
	private java.util.Properties pBirtProperties = null;
	private java.util.Properties pAgregaProperties = null;
	private JmsTemplate jmsTemplate;
	private final Integer nivelAgregacionCatalogo = 3;
	
	
	private String getPlantillasInformes() {
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		String plantillasInformes = "";
		try {
			plantillasInformes = this.getAgregaPropertyValue("informesDir");
		} catch (Exception e) {
			logger.error("Hubo un problema al obtener la propiedad informesDir del fichero agrega.properties");
		}
		return homeJboss+File.separator+plantillasInformes+File.separator;
	}
	
	
	/**
     * Registra en la Tarea ejecutada 
     * @param trabajo Trabajo que se va a registrar
     * @return identificador de la tarea que se registra
     * @throws Exception
     */
	protected java.lang.Long handleRegistrarTrabajoPlan(TareaEjecutadaPlanVO trabajo) throws java.lang.Exception
	{
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();

		if(logger.isDebugEnabled())logger.debug("Trabajo: " + trabajo.getTrabajo() + " grupotrabajo: " + trabajo.getGrupoTrabajo() + " Descripcion: " + trabajo.getDescripcion() + " Estado: " + trabajo.getEstado() + " Fecha inicio: " + trabajo.getFechaInicio());

		tarea.setTrabajo(trabajo.getTrabajo());
		tarea.setGrupoTrabajo(trabajo.getGrupoTrabajo());
		tarea.setDescripcion(trabajo.getDescripcion());
		tarea.setEstado(trabajo.getEstado());

		if (trabajo.getFechaInicio() != null)
		{
			GregorianCalendar fechaInicio = new GregorianCalendar();
			fechaInicio.setTime((trabajo.getFechaInicio()).getTime());
			tarea.setFechaInicio(fechaInicio);
		}

		if (trabajo.getFechaFin() != null)
		{
			GregorianCalendar fechaFin = new GregorianCalendar();
			fechaFin.setTime((trabajo.getFechaFin()).getTime());
			tarea.setFechaFin(fechaFin);
			if(logger.isDebugEnabled())logger.debug("Fecha fin: " + trabajo.getFechaInicio());
		}

		Long id = this.getSrvRegistroPlanificadorService().registrarTrabajo(tarea);

		return id;
	}

	/**
     * Registra la fecha en la que se ha finalizado la ejecucion de un trabajo 
     * y se comprueba si la ejecución ha sido o no correcta
     * @param trabajo TareaEjecutadaVO
     * @return identificador del trabajo
     * @throws Exception
     * @see es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio#registrarTrabajoFechaFinPlan(es.pode.auditoria.negocio.servicios.TareaEjecutadaPlanVO)
     */

	protected java.lang.Long handleRegistrarTrabajoFechaFinPlan(TareaEjecutadaPlanVO trabajo) throws java.lang.Exception
	{
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();

		if(logger.isDebugEnabled())logger.debug("Id: " + trabajo.getId() + " trabajo: " + trabajo.getTrabajo() + " grupotrabajo: " + trabajo.getGrupoTrabajo() + " Descripcion: " + trabajo.getDescripcion() + " Estado: " + trabajo.getEstado() + " Fecha inicio: "
				+ trabajo.getFechaInicio());

		tarea.setId(trabajo.getId());
		tarea.setTrabajo(trabajo.getTrabajo());
		tarea.setGrupoTrabajo(trabajo.getGrupoTrabajo());
		tarea.setDescripcion(trabajo.getDescripcion());
		tarea.setEstado(trabajo.getEstado());

		if (trabajo.getFechaInicio() != null)
		{
			GregorianCalendar fechaInicio = new GregorianCalendar();
			fechaInicio.setTime((trabajo.getFechaInicio()).getTime());
			tarea.setFechaInicio(fechaInicio);
		}

		if (trabajo.getFechaFin() != null)
		{
			GregorianCalendar fechaFin = new GregorianCalendar();
			fechaFin.setTime((trabajo.getFechaFin()).getTime());
			tarea.setFechaFin(fechaFin);
			if(logger.isDebugEnabled())logger.debug("Fecha fin: " + trabajo.getFechaInicio());
		} else
			if(logger.isDebugEnabled())logger.debug("La fecha fin no debería ser nula");

		Long id = this.getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(tarea);

		return id;
	}

	/**
     * Registra en la tabla correspodiente si ha ido o no bien la carga de las sub-tareas que estan
     * asociadas a una tarea en concreto 
     * @param trabajoHijo El sub-trabajo de un trabajo concreto
     * @return identificador del registro
     * @throws Exception
     */

	protected java.lang.Long handleRegistrarTrabajoHijoPlan(RegistroTareaEjecPlanVO trabajoHijo) throws java.lang.Exception
	{
		RegistroTareaEjecutadaVO tareaSub = new RegistroTareaEjecutadaVO();

		if(logger.isDebugEnabled())logger.debug("Trabajo hijo, descripcion: " + trabajoHijo.getDescripcion() + " Estado: " + trabajoHijo.getEstado() + " codigo: " + trabajoHijo.getCodigo() + " Fecha inicio: " + trabajoHijo.getFecha());

		tareaSub.setCodigo(trabajoHijo.getCodigo());
		tareaSub.setDescripcion(trabajoHijo.getDescripcion());
		tareaSub.setEstado(trabajoHijo.getEstado());

		if (trabajoHijo.getFecha() != null)
		{
			GregorianCalendar fecha = new GregorianCalendar();
			fecha.setTime((trabajoHijo.getFecha()).getTime());
			tareaSub.setFecha(fecha);
		}

		Long id = this.getSrvRegistroPlanificadorService().registrarTrabajoHijo(tareaSub);

		return id;
	}

	/**
	 * Realiza la operacion de cambio de estado a INTERRUMPIDO de los trabajos 
	 * que no finalizaron cuando el servidor de aplicaciones se ha parado inesperadamente
	 * @return filasModificadas Las filas modificadas
	 * @throws Exception
	 */
	protected java.lang.Long handleRegistrarTrabajoInterrPlan() throws java.lang.Exception
	{
		Long id = this.getSrvRegistroPlanificadorService().registrarTrabajoInterrumpido();

		if(logger.isDebugEnabled())logger.debug("Número de trabajos interrumpidos " + id);

		return id;
	}

	/**
	 * Registra la operacion realizada por el usuario
	 * @param operacion Operación realizada por el usuario
	 * @throws Exception
	 */

	protected void handleRegistrarOperacion(es.pode.auditoria.negocio.servicios.OperacionVO operacion) throws java.lang.Exception
	{
//		if(logger.isDebugEnabled())logger.debug("REGISTRAR OPERACION");
		this.enviarMensajeOperacion(operacion);

	}

	/**
	 * Obtiene un array de InformeOperacionVO con las operaciones realizadas por los visitantes: ficha ODE, previsualización y descarga en un rango de fechas
	 * @param  parametroInformeVO Value Object con los parámetros que necesita el método de generación del informe
	 * @return InformeOperacionVO[] Array de Value Objects con la información de las operaciones y el número de veces que han sido realizadas
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeOperacionVO[] handleInformeOperacionesRealizadas(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.lang.Exception
	{
//		logger.info("informeOperacionesRealizadas");
		InformeOperacionVO[] informeOperacionVO = null;
		String idioma = parametroInformeVO.getIdioma();
		if (idioma == null)
		{
			
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		Locale locale = new Locale(idioma);
		if (parametroInformeVO == null)
		{
			logger.warn("Error calculando operaciones realizadas. No hay parametros.");
			throw new Exception("Error calculando operaciones realizadas. No hay parametros.");
		}

		//Validamos las fechas
		
		if(!(this.validarFechas(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
			
		String[] operacionesString = obtenerOperaciones();
		

		if (operacionesString == null)
		{
			logger.warn("Error calculando operaciones realizadas. No se pueden obtener las operaciones a contabilizar.");
			throw new Exception("Error calculando operaciones realizadas. No se pueden obtener las operaciones a contabilizar.");
		}
//		if(logger.isDebugEnabled())logger.debug("Las operaciones disponibles en la plataforma:");
		for (int i = 0; i < operacionesString.length; i++)
		{
//			if(logger.isDebugEnabled())logger.debug("El valor de operacionesString " + operacionesString[i]);
		}
		informeOperacionVO = new InformeOperacionVO[operacionesString.length];
		Long numOperacion = null;
		try{
			for (int i = 0; i < operacionesString.length; i++)
			{
				informeOperacionVO[i] = new InformeOperacionVO();
	
				String tipo_operacion = operacionesString[i];
				
				if(logger.isDebugEnabled())logger.debug("tipo_operacion: <" + tipo_operacion+">");
				if (tipo_operacion.indexOf("buscar") != -1)
				{
					try
					{
						numOperacion = this.getBusquedaDao().listarNumBusquedasDesdeHasta(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta(), tipo_operacion);
						if(logger.isDebugEnabled())logger.debug("Es una busqueda <" + tipo_operacion + "> numOperacion <" + numOperacion+">");
					}catch(Exception e)
					{
						logger.error("Se produce una excepcion en el informe de las operaciones realizadas - ",e);
					}
				} else
				{
//					if(logger.isDebugEnabled())logger.debug("Calculo el numero de operaciones realizadas");
					numOperacion = this.getOperacionDao().obtenerNumOperacionesDesdeHasta(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta(), tipo_operacion);
					if(logger.isDebugEnabled())logger.debug("Es una operacion <" + tipo_operacion + "> numOperacion <" + numOperacion+">");
				}
				if (tipo_operacion.equals("ficha_embed")){
					informeOperacionVO[i].setOperacion(this.getFicherRecursos(locale).getString("ficha"));
				}else{
					informeOperacionVO[i].setOperacion(this.getFicherRecursos(locale).getString(tipo_operacion));
				}
				informeOperacionVO[i].setTotalOperacion(numOperacion.intValue());
			}
		}catch(Exception e){
			logger.error("Se produce una excepcion en el informe de las operaciones realizadas - ",e);
		}
		if (informeOperacionVO == null)
		{
			logger.warn("Error obteniendo las operaciones realizadas");
			throw new Exception("Error calculando operaciones realizadas. No se ha obtenido ningun resultado.");
		}

		return informeOperacionVO;
	}

	/**
	 * Obtiene un array de InformeNivelAgregacionVO con los odes que hay por nivel de agregación
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeNivelAgregacionVO[] Array de Value Objects con la información de los odes de cada uno de los niveles de agregación
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeNivelAgregacionVO[] handleInformeNivelAgregacion(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
		InformeNivelAgregacionVO[] informeNivelAgregacionVO = null;
//		logger.info("informeNivelAgregacion");
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando odes por nivel de agregacion. No hay parametros.");
			throw new Exception("Error calculando odes por nivel de agregacion. No hay parametros.");
		}
		
		//Validamos las fechas
		
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		ParametroAuditIndexadorVO parametroAuditIndexadorVO = new ParametroAuditIndexadorVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditIndexadorVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditIndexadorVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros del servicio: " + parametroAuditIndexadorVO.getFechaDesde()+" "+ parametroAuditIndexadorVO.getFechaHasta());
		CoberturaVO[] coberturaVO = this.getSrvAuditoriaIndexadorService().coberturaNivelAgregacion(parametroAuditIndexadorVO);
		if (coberturaVO == null)
		{
			logger.warn("coberturaVO es null " + coberturaVO);
		} else
		{
			informeNivelAgregacionVO = new InformeNivelAgregacionVO[coberturaVO.length];
			if(logger.isDebugEnabled())logger.debug("El array de resultados (InformeNivelAgregacionVO[]) tiene un tamanio de " + informeNivelAgregacionVO.length);
			for (int i = 0; i < coberturaVO.length; i++)
			{
				informeNivelAgregacionVO[i] = new InformeNivelAgregacionVO();
				this.getBeanMapper().map(coberturaVO[i], informeNivelAgregacionVO[i]);
			}
			if(logger.isDebugEnabled())logger.debug("devuelvo el VO informeNivelAgregacionVO ");
			if (informeNivelAgregacionVO == null)
			{
				logger.warn("Error calculando los odes por nivel de agregacion");
				throw new Exception("Error calculando los odes por nivel de agregacion. No se ha obtenido ningun resultado.");
			}
		}
		return informeNivelAgregacionVO;
	}

	/**
	 * Obtiene un array de InformeCoberturaCurricularVO con los odes que hay en cada uno de los nodos del árbol curricular
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeCoberturaCurricularVO[] Array de Value Objects con la información de los odes de cada una de las ramas del árbol curricular
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeCoberturaCurricularVO[] handleInformeCoberturaCurricular(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{

		InformeCoberturaCurricularVO[] informeCoberturaCurricularVO = null;
//		logger.info("informeCoberturaCurricular");
		String idioma = parametrosInformeVO.getIdioma();
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		if (parametrosInformeVO == null) {
			logger.warn("Error calculando cobertura por arbol curricular. No hay parametros.");
			throw new Exception(
					"Error calculando cobertura por arbol curricular. No hay parametros.");
		}
		
		//Validamos las fechas
		
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		ParametroAuditIndexadorVO parametroAuditIndexadorVO = new ParametroAuditIndexadorVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditIndexadorVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditIndexadorVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditIndexadorVO.getFechaDesde() + " FechaFin : " + parametroAuditIndexadorVO.getFechaHasta());
		ArbolCurricularVO[] arbolCurricular = this.obtenerArbolCurricular(idioma);
		if (arbolCurricular == null)
		{
			logger.warn("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
			throw new Exception("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
		}else
		{
			if(logger.isDebugEnabled())logger.debug("El arbolCurricular obtenido es " + arbolCurricular.length);
		}
		String[] idNodos = this.obtenerIdArbolCurricular(arbolCurricular);
		if(logger.isDebugEnabled())logger.debug("El arbos curricular tiene " + idNodos.length+" nodos");
		if(logger.isDebugEnabled())logger.debug("El primer nodo es :" + idNodos[0]);
		parametroAuditIndexadorVO.setIdNodo(idNodos);
		CoberturaVO[] coberturaVO = this.getSrvAuditoriaIndexadorService().coberturaArbolCurricular(parametroAuditIndexadorVO);
		if(logger.isDebugEnabled())logger.debug("se ha obtenido el VO  coberturaVO" + coberturaVO);
		if(coberturaVO == null)
		{
			logger.warn("El VO coberturaVO es null");
			throw new Exception("Error obteniendo el informe de la cobertura por arbol curricular");
		}else
		{
			informeCoberturaCurricularVO = new InformeCoberturaCurricularVO[coberturaVO.length];
			for (int i = 0; i < coberturaVO.length; i++)
			{
				informeCoberturaCurricularVO[i] = new InformeCoberturaCurricularVO();
				this.getBeanMapper().map(coberturaVO[i], informeCoberturaCurricularVO[i]);
				informeCoberturaCurricularVO[i].setCoberturaCurricular(arbolCurricular[i].getIdNodo() + " " + arbolCurricular[i].getNombreNodo());
			}
			if (informeCoberturaCurricularVO == null) {
				logger.warn("Error obteniendo el informe de la cobertura por arbol curricular");
				throw new Exception(
						"Error obteniendo el informe de la cobertura por arbol curricular");
			} else {
				if(logger.isDebugEnabled())logger.debug("El VO informeCoberturaCurricularVO tiene un tamanio "
						+ informeCoberturaCurricularVO.length);
			}
		}
		return informeCoberturaCurricularVO;
	}

	/**
	 * Obtiene un array de InformeTerminoBusquedaVO con los terminos más buscados en un periodo de tiempo concreto
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeTerminoBusquedaVO[] Value Object con la información de los términos más buscados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeTerminoBusquedaVO[] handleInformeTerminosBusqueda(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
		
//		logger.info("Informe de los terminos mas buscados");
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando terminos mas buscados. No hay parametros.");
			throw new Exception("Error calculando terminos mas buscados. No hay parametros.");
		}
		Calendar fechaDesde = parametrosInformeVO.getFechaDesde();
		Calendar fechaHasta = parametrosInformeVO.getFechaHasta();
		int numMaxRes = parametrosInformeVO.getRango();

		if (numMaxRes == 0)
		{
			logger.warn("Error calculando terminos mas buscados. El numero de resulados a devolver es nulo o 0.");
			throw new Exception("Error calculando terminos mas buscados El numero de resulados a devolver es nulo o 0.");
		}
		
		//Validamos las fechas
		
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
	
		String[] listaResultante = (String[]) this.getBusquedaDao().listarTerminosBuscadosDesdeHasta(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta()).toArray(new String[0]);
		if (listaResultante == null || listaResultante.length == 0)
		{
			if(logger.isDebugEnabled())logger.debug("Calculando terminos mas buscados. No existen terminos buscados dentro del periodo desde[" + fechaDesde + "]->hasta[" + fechaHasta + "]");
			return null;
		}
		if(logger.isDebugEnabled())logger.debug("El resultado es " + listaResultante.length);
		HashMap mapaValores = new HashMap();
		Long numeroBusquedas = null;
		for (int i = 0; i < listaResultante.length; i++)
		{
			try
			{
				if (!listaResultante[i].equals(""))//solo nos interesan los identificadores con contenido
				{
					numeroBusquedas = this.getBusquedaDao().obtenerNumTerminosBuscados(fechaDesde, fechaHasta, listaResultante[i]);
					if (numeroBusquedas != null)
						mapaValores.put(listaResultante[i], numeroBusquedas);
				}
			} catch (Exception e)
			{
				logger.error("Calculando terminos mas buscados. Error obteniendo numero de busquedas para el termino[" + listaResultante[i] + "]. Continuamos el calculo.",e);
			}
		}
		if (mapaValores.isEmpty())
		{
			logger.warn("Error calculando contenidos mas valorados. No hay valoraciones disponibles para [" + listaResultante.length + "] ODEs!! desde[" + fechaDesde + "]->hasta[" + fechaHasta + "]");
			return null;
		}
		//Construimos el array de valores que devolveremos sin ordenar
		InformeTerminoBusquedaVO[] arrayTemp = new InformeTerminoBusquedaVO[mapaValores.size()];
		//Recogemos la lista de ids 
		if(logger.isDebugEnabled())logger.debug("mapaValores.size() "+mapaValores.size());
		String[] ids = (String[]) mapaValores.keySet().toArray(new String[0]);
		for (int i = 0; i < ids.length; i++)
		{
			Integer valor = Integer.parseInt(((Long)mapaValores.get(ids[i])).toString());
			arrayTemp[i] = new InformeTerminoBusquedaVO(ids[i], valor.intValue());
		}
		//Con los odes que hemos calculado tenemos que ordenarlos de mayor a menor y devolver solo la cantidad pedida.
		arrayTemp = ordenaDescendente(arrayTemp);
		if (arrayTemp.length <= numMaxRes)
			return arrayTemp;
		InformeTerminoBusquedaVO[] devuelto = new InformeTerminoBusquedaVO[numMaxRes];
		for (int i = 0; i < numMaxRes; i++)
		{
			devuelto[i] = arrayTemp[i];
		}
		return devuelto;

	}

	/**
	 * Obtiene un array de InformeOperacionOdeUsuarioVO con el estado de todos los odes de la plataforma para un usuario concreto en un rango de fechas determinado
	 * @param  parametroInformeVO Clase con los parámetros que necesita el método
	 * @return InformeOperacionOdeUsuarioVO[] Value Object con la información de los odes de un usuario
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeOdeUsuarioVO[] handleInformeOdeUsuario(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.lang.Exception
	{
//		logger.info("informe OdeUsuario");
		InformeOdeUsuarioVO[] informeOperacionOdeUsuarioVO = null;
		String idioma = parametroInformeVO.getIdioma();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		format.setLenient(false);
		
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		//Locale locale = new Locale(idioma);
		if(logger.isDebugEnabled())logger.debug(" fechaDesde " + parametroInformeVO.getFechaDesde());
		if(logger.isDebugEnabled())logger.debug(" fechaHasta " + parametroInformeVO.getFechaHasta());
		if (parametroInformeVO == null)
		{
			logger.warn("Error calculando las operaciones realizadas por un usuario. No hay parametros.");
			throw new Exception("Error calculando las operaciones realizadas por un usuario. No hay parametros.");
		}
		//Validamos las fechas
		
		if(!(this.validarFechas(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
	
		ParametroAuditPublicacionVO parametroAuditPublicacionVO = new ParametroAuditPublicacionVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametroInformeVO.getFechaDesde()).getTime());
		parametroAuditPublicacionVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametroInformeVO.getFechaHasta()).getTime());
		parametroAuditPublicacionVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditPublicacionVO.getFechaDesde() + " FechaFin : " + parametroAuditPublicacionVO.getFechaHasta());
		parametroAuditPublicacionVO.setIdioma(idioma);
		parametroAuditPublicacionVO.setIdUsuario(parametroInformeVO.getIdUsuario());
		if(logger.isDebugEnabled())logger.debug("parametroAuditPublicacionVO.getIdUsuario() " + parametroAuditPublicacionVO.getIdUsuario());
		EstadoActividadVO[] estadoActividadVO = this.getSrvAuditaPublicacionService().odesPorUsuario(parametroAuditPublicacionVO);
		//Deberíamos sacar los titulos
		if (!(estadoActividadVO == null))
		{
			if(logger.isDebugEnabled())logger.debug("El resultado de la llamada a SrvAuditaPublicacionService().odesPorEstados es " + estadoActividadVO.length);

		}
		//Sacamos el listado de operaciones para realizar las consultas
		int numInformesOperacion = 0;
		for (int j = 0; j < estadoActividadVO.length; j++)
		{
			if (estadoActividadVO[j].getDetalleTransicionVOs().length == 0)
			{
				numInformesOperacion = numInformesOperacion + 1;
			} else
			{
				numInformesOperacion = numInformesOperacion + estadoActividadVO[j].getDetalleTransicionVOs().length;
			}
		}
		String[] estados = new String[estadoActividadVO.length];
		Vector titulosTotal = new Vector();
		InformeOdeUsuarioVO informeTemp;
		InformeOdeUsuarioVO[] informeTempArray;
		for (int i = 0; i < estadoActividadVO.length; i++)
		{
			estados[i] = estadoActividadVO[i].getEstado();
			DetalleTransicionVO[] detalleTransiccionVO = estadoActividadVO[i].getDetalleTransicionVOs();
			if (detalleTransiccionVO.length == 0)
			{
				if(logger.isDebugEnabled())logger.debug("sin titulos");
				informeTemp = new InformeOdeUsuarioVO();
				informeTemp.setEstado(estados[i]);
				informeTemp.setFechaTransiccion(null);
				informeTemp.setTitulo("");
				titulosTotal.add(informeTemp);
			} else
			{
				if(logger.isDebugEnabled())logger.debug("con titulos");
				informeTempArray = new InformeOdeUsuarioVO[detalleTransiccionVO.length];
				for (int j = 0; j < detalleTransiccionVO.length; j++)
				{
					informeTempArray[j] = new InformeOdeUsuarioVO();
					informeTempArray[j].setEstado(estados[i]);
					String fechaT = "";
					fechaT = format.format((detalleTransiccionVO[j].getFechaTransicion()).getTime());
					informeTempArray[j].setFechaTransiccion(fechaT);
					informeTempArray[j].setTitulo(detalleTransiccionVO[j].getTitulo());
					titulosTotal.add(informeTempArray[j]);
				}
			}

		}
		if(logger.isDebugEnabled())logger.debug("titulosTotal.size() -> " + titulosTotal.size());
		//informeOperacionOdeUsuarioVOTemp = (InformeOdeUsuarioVO[])titulosTotal.toArray(new InformeOdeUsuarioVO[0]);
		//Sacamos las operaciones realizadas por el usuario

		informeOperacionOdeUsuarioVO = new InformeOdeUsuarioVO[titulosTotal.size()];
		for (int p = 0; p < titulosTotal.size(); p++)
		{
			informeOperacionOdeUsuarioVO[p] = new InformeOdeUsuarioVO();
			informeOperacionOdeUsuarioVO[p] = (InformeOdeUsuarioVO) titulosTotal.elementAt(p);

		}

		return informeOperacionOdeUsuarioVO;
	}

	/**
	 * Obtiene un array de InformeOperacionOdeUsuarioVO con el estado de todos los odes de la plataforma para un usuario concreto en un rango de fechas determinado
	 * @param  parametroInformeVO Clase con los parámetros que necesita el método
	 * @return InformeOperacionOdeUsuarioVO[] Value Object con la información de los odes de un usuario
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeOdeUsuarioVO[] handleInformeOdeUsuarioOperacion(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametroInformeVO) throws java.lang.Exception
	{
//		logger.info("informe OdeUsuario");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		format.setLenient(false);
		InformeOdeUsuarioVO[] informeOperacionOdeUsuarioVO = null;
		String[] operactionesString = null;
		String idioma = parametroInformeVO.getIdioma();
		Vector titulosTotal = new Vector();
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		Locale locale = new Locale(idioma);
		if(logger.isDebugEnabled())logger.debug(" fechaDesde " + parametroInformeVO.getFechaDesde());
		if(logger.isDebugEnabled())logger.debug(" fechaHasta " + parametroInformeVO.getFechaHasta());
		if (parametroInformeVO == null)
		{
			logger.warn("Error calculando las operaciones realizadas por un usuario. No hay parametros.");
			throw new Exception("Error calculando las operaciones realizadas por un usuario. No hay parametros.");
		}
		//Validamos las fechas
		
		if(!(this.validarFechas(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametroInformeVO.getFechaDesde()).getTime());
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametroInformeVO.getFechaHasta()).getTime());
		operactionesString = this.obtenerOperaciones();
		if (operactionesString == null)
		{
			logger.warn("Error calculando las operaciones realizadas por el usuario. La lista de operaciones es null");
			throw new Error("Error calculando las operaciones realizadas por el usuario. La lista de operaciones es null");
		}
		InformeOdeUsuarioVO informeOperacion = null;
		if(logger.isDebugEnabled())logger.debug("usuario[" + parametroInformeVO.getIdUsuario() + "]");
		for (int j = 0; j < operactionesString.length; j++)
		{
			String operacion = operactionesString[j];
			if(logger.isDebugEnabled())logger.debug("La operacion es[" + operacion + "]");
			if (operacion.indexOf("buscar") == -1)
			{

				List operacionesList = this.getOperacionDao().obtenerIdOdeOperUsuario(parametroInformeVO.getFechaDesde(), parametroInformeVO.getFechaHasta(), parametroInformeVO.getIdUsuario(), operacion);
				if(logger.isDebugEnabled())logger.debug("operacionesList.size() -> " + operacionesList.size());
				Operacion[] operacionesArray = (Operacion[]) operacionesList.toArray(new Operacion[0]);
				if ((operacionesArray == null) || (operacionesArray.length == 0))
				{
					if(logger.isDebugEnabled())logger.debug("No hay registros para esa operacion");
					informeOperacion = new InformeOdeUsuarioVO();
					informeOperacion.setEstado(this.getFicherRecursos(locale).getString(operactionesString[j]));
					informeOperacion.setFechaTransiccion(null);
					informeOperacion.setTitulo("");
					titulosTotal.add(informeOperacion);
				} else
				{
					if(logger.isDebugEnabled())logger.debug("Hay registros para esa operacion");
					String[] idOdes = new String[operacionesArray.length];
					for (int k = 0; k < idOdes.length; k++)
					{
						idOdes[k] = operacionesArray[k].getIdOde();
					}
					DetallePublicadoODEVO[] titulosIdOdes = (this.getSrvAuditaPublicacionService().titulosODEsPorID(idOdes));
					for (int k = 0; k < operacionesArray.length; k++)
					{
						informeOperacion = new InformeOdeUsuarioVO();
						informeOperacion.setEstado(this.getFicherRecursos(locale).getString(operactionesString[j]));
						String fechaO = "";
						fechaO = format.format((operacionesArray[k].getFecha()));
						informeOperacion.setFechaTransiccion(fechaO);
						informeOperacion.setTitulo(titulosIdOdes[k].getTitulo());
						titulosTotal.add(informeOperacion);
					}
				}
			} else
			{
				if(logger.isDebugEnabled())logger.debug("es una busqueda no hago nada");
			}

		}

		if(logger.isDebugEnabled())logger.debug("titulosTotal.size() -> " + titulosTotal.size());
		informeOperacionOdeUsuarioVO = new InformeOdeUsuarioVO[titulosTotal.size()];
		for (int p = 0; p < titulosTotal.size(); p++)
		{
			informeOperacionOdeUsuarioVO[p] = new InformeOdeUsuarioVO();
			informeOperacionOdeUsuarioVO[p] = (InformeOdeUsuarioVO) titulosTotal.elementAt(p);

		}
		
		return informeOperacionOdeUsuarioVO;
	}

	/**
	 * Obtiene un array de InformeOperacionOdeUsuarioVO con el estado de todos los odes de la plataforma para un usuario concreto en un rango de fechas determinado
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeOperacionOdeUsuarioVO[] VO con la información de los odes de un usuario
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeOperacionUsuarioVO[] handleInformeOperacionUsuario(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		if(logger.isDebugEnabled())logger.debug("informeOperacionUsuario");
		InformeOperacionUsuarioVO[] informeOperacionUsuarioVO = null;
		String[] operactionesString = null;
		TitulosOdePorUsuarioDesdeHastaCriteria titulosOdePorUsuarioDesdeHastaCriteria = null;
		operactionesString = this.obtenerOperaciones();
		if (operactionesString == null)
		{
			logger.warn("Error calculando las operaciones realizadas por el usuario. La lista de operaciones es null");
			throw new Error("Error calculando las operaciones realizadas por el usuario. La lista de operaciones es null");
		}

		InformeOperacionUsuarioVO informeOperacion = null;
		Vector titulosTotal = new Vector();

		for (int j = 0; j < operactionesString.length; j++)
		{
			String operacion = operactionesString[j];
			if(logger.isDebugEnabled())logger.debug("La operacion es " + operacion);
			titulosOdePorUsuarioDesdeHastaCriteria = new TitulosOdePorUsuarioDesdeHastaCriteria();
			titulosOdePorUsuarioDesdeHastaCriteria.setFechaDesde(parametrosInformeVO.getFechaDesde());
			titulosOdePorUsuarioDesdeHastaCriteria.setFechaHasta(parametrosInformeVO.getFechaHasta());
			titulosOdePorUsuarioDesdeHastaCriteria.setUsuario(parametrosInformeVO.getIdUsuario());
			titulosOdePorUsuarioDesdeHastaCriteria.setOperacion(operactionesString[j]);
			List operacionesList = this.getOperacionDao().obtenerOdesUsuarioDesdeHasta(titulosOdePorUsuarioDesdeHastaCriteria);
			OperacionVO[] operacionesArray = (OperacionVO[]) operacionesList.toArray(new OperacionVO[0]);
			if ((operacionesArray == null) || (operacionesArray.length == 0))
			{
				if(logger.isDebugEnabled())logger.debug("No hay registros para esa operacion");
				informeOperacion = new InformeOperacionUsuarioVO();
				informeOperacion.setOperacion(operacion);
				informeOperacion.setFechaTransiccion(null);
				informeOperacion.setTitulo("");
				titulosTotal.add(informeOperacion);
			} else
			{
				if(logger.isDebugEnabled())logger.debug("Hay registros para esa operacion");
				String[] idOdes = new String[operacionesArray.length];
				for (int k = 0; k < idOdes.length; k++)
				{
					idOdes[k] = operacionesArray[k].getIdOde();
				}
				DetallePublicadoODEVO[] titulosIdOdes = (this.getSrvAuditaPublicacionService().titulosODEsPorID(idOdes));
				if(logger.isDebugEnabled())logger.debug("De publicacion obtengo <" + titulosIdOdes.length+"> arrays de titulos.");
				for (int k = 0; k < operacionesArray.length; k++)
				{
					informeOperacion = new InformeOperacionUsuarioVO();
					informeOperacion.setOperacion(operacion);
					informeOperacion.setFechaTransiccion(operacionesArray[k].getFecha());
					informeOperacion.setTitulo(titulosIdOdes[k].getTitulo());
					titulosTotal.add(informeOperacion);
				}
			}

		}

		informeOperacionUsuarioVO = (InformeOperacionUsuarioVO[]) titulosTotal.toArray(new InformeOdeUsuarioVO[0]);
		return informeOperacionUsuarioVO;
	}

	/**
	 * Obtiene un array de InformeEstadoOdesVO con el estado de todos los odes de la plataforma en un rango de fechas determinado.
	 * El estado en el que se encuentra los odes será el estado final de los mismos
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeEstadoOdesVO[] Array con el número de odes que se encuentran en cada estado 
	 * @throws Exception
	 */

	protected es.pode.auditoria.negocio.servicios.InformeEstadoOdesVO[] handleInformeEstadoOdes(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe estado odes");
		InformeEstadoOdesVO[] informeEstadoOdesVO = null;
		ParametroAuditPublicacionVO parametroAuditPublicacionVO = new ParametroAuditPublicacionVO();
		String idioma = parametrosInformeVO.getIdioma();
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando los estados de los odes. No hay parametros.");
			throw new Exception("Error calculando los estados de los odes. No hay parametros.");
		}
		parametrosInformeVO.setIdioma(idioma);
		
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
	
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditPublicacionVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditPublicacionVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditPublicacionVO.getFechaDesde() + " FechaFin : " + parametroAuditPublicacionVO.getFechaHasta());
		parametroAuditPublicacionVO.setIdioma(idioma);
		ODEsEstadoVO[] odesEstadoVO = this.getSrvAuditaPublicacionService().odesPorEstados(parametroAuditPublicacionVO); //Estado final
		if (!(odesEstadoVO == null))
		{
			if(logger.isDebugEnabled())logger.debug("El resultado de la llamada a SrvAuditaPublicacionService().odesPorEstados es " + odesEstadoVO.length);
			informeEstadoOdesVO = new InformeEstadoOdesVO[odesEstadoVO.length];
			if(logger.isDebugEnabled())logger.debug("Mapeamos el resultado de SrvAuditaPublicacionService(): odesPorEstados al resultado de  SrvAuditoriaServie.EstadoOdes: informeEstadoOdesVO");
			for (int i = 0; i < odesEstadoVO.length; i++)
			{
				informeEstadoOdesVO[i] = new InformeEstadoOdesVO();
				informeEstadoOdesVO[i].setCantidad((odesEstadoVO[i].getCantidad()).intValue());
				informeEstadoOdesVO[i].setEstadoFinal(odesEstadoVO[i].getEstado());
				if(logger.isDebugEnabled())logger.debug("[odesPorEstado] estado - Cantidad " + informeEstadoOdesVO[i].getEstadoFinal() + "-" + informeEstadoOdesVO[i].getCantidad());
				
			}

			if (informeEstadoOdesVO == null)
			{
				logger.warn("Error calculando los estados de los odes. informeEstadoOdesVO es null.");
				throw new Exception("Error calculando los estados de los odes. informeEstadoOdesVO es null.");
			}
		} else
		{
			if(logger.isDebugEnabled())logger.debug("No se devuelve ningún dato para el informe");
		}
		return informeEstadoOdesVO;
	}

	/**
	 * Obtiene un array de InformeEstadoOdesVO con la transición entre estados de todos los odes de la plataforma en un rango de fechas determinado.
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeEstadoOdesVO[] Value Object con el número de odes de cada transición entre estados 
	 * @throws Exception
	 */

	protected es.pode.auditoria.negocio.servicios.InformeEstadoOdesVO[] handleInformeEstadoOdesTransicciones(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Estado Odes Transicciones");
		InformeEstadoOdesVO[] informeEstadoOdesVO = null;
		ParametroAuditPublicacionVO parametroAuditPublicacionVO = new ParametroAuditPublicacionVO();
		String idioma = parametrosInformeVO.getIdioma();
		
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando los estados de los odes. No hay parametros.");
			throw new Exception("Error calculando los estados de los odes. No hay parametros.");
		}
		parametrosInformeVO.setIdioma(idioma);
		
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditPublicacionVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditPublicacionVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditPublicacionVO.getFechaDesde() + " FechaFin : " + parametroAuditPublicacionVO.getFechaHasta());
		parametroAuditPublicacionVO.setIdioma(idioma);
		ODEsEstadoVO[] odesTransicionVO = this.getSrvAuditaPublicacionService().actividadTransiciones(parametroAuditPublicacionVO);
		if (odesTransicionVO != null)
		{
			informeEstadoOdesVO = new InformeEstadoOdesVO[odesTransicionVO.length];
			for (int j = 0; j < odesTransicionVO.length; j++)
			{
				informeEstadoOdesVO[j] = new InformeEstadoOdesVO();
				informeEstadoOdesVO[j].setCantidad((odesTransicionVO[j].getCantidad()).intValue());
				informeEstadoOdesVO[j].setEstadoTransicion(odesTransicionVO[j].getEstado());
				if(logger.isDebugEnabled())logger.debug("[odesPorTransiccion] estado - Cantidad " + informeEstadoOdesVO[j].getEstadoFinal() + "-" + informeEstadoOdesVO[j].getCantidad());
			}

		} else
		{
			logger.warn("El resultado del informe es null");
		}
		return informeEstadoOdesVO;
	}

	/**
	 * Registra los terminos que han sido buscados en la plataforma Agrega
	 * @param  busqueda Término buscado
	 * @throws Exception
	 */
	protected void handleRegistrarBusqueda(es.pode.auditoria.negocio.servicios.BusquedaVO busqueda) throws java.lang.Exception
	{
		if(logger.isDebugEnabled())logger.debug("REGISTRAR BUSQUEDA");
		this.enviarMensajeBusqueda(busqueda);

	}

	/**
	 * Obtiene un array de InformeLicenciasOdesVO con la información de los odes que hay publicados en la plataforma para cada una de las licencias 
	 * @param  parametrosInformeVO Value Object con los parámetros que necesita el servicio que obtiene los datos del informe: fechaDesde, fechaHasta y licencias
	 * @return InformeLicenciasOdesVO[] 
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeLicenciasOdesVO[] handleInformeLicenciasOdes(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{

//		logger.info("Informe Licencias Ode");
		InformeLicenciasOdesVO[] informeLicenciasOdesVO = null;
		String idioma = parametrosInformeVO.getIdioma();
		if (idioma == null)
		{
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		}
		if(logger.isDebugEnabled())logger.debug("idioma " + idioma);
		if (parametrosInformeVO == null)
		{
			logger.error("Error calculando los odes por licencia. No hay parametros.");
			throw new Exception("Error calculando los odes por licencia. No hay parametros.");
		}
		
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
			
		if (parametrosInformeVO.getIdioma() == null)
		{
			logger.warn("Error calculando los odes por licencia. El idioma esta vacio.");
			throw new Exception("Error calculando los odes por licencia. El idioma esta vacio.");
		}
		String[] licenciasIdiomaSelected = this.obtenerLicencias(idioma);
		if (licenciasIdiomaSelected == null)
		{
			logger.warn("Error al obtener los odes por licencias. El listado de licencias es null");
			throw new Exception("Error al obtener los odes por licencias. El listado de licencias es null");
		}
		String[] licenciasIngles = this.obtenerLicencias("en");
		if (licenciasIngles == null)
		{
			logger.warn("Error al obtener los odes por licencias. El listado de licencias en ingles es null");
			throw new Exception("Error al obtener los odes por licencias. El listado de licencias en ingles es null");
		}
		if(logger.isDebugEnabled())logger.debug("Se obtienen las siguientes licencias : " + licenciasIdiomaSelected.length);
		if(logger.isDebugEnabled())logger.debug("La primera licencia es  " + licenciasIngles[1]);
		ParametroAuditIndexadorVO parametroAuditIndexador = new ParametroAuditIndexadorVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditIndexador.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditIndexador.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditIndexador.getFechaDesde() + " FechaFin : " + parametroAuditIndexador.getFechaHasta());
		parametroAuditIndexador.setIdLicencias(licenciasIngles);
		CoberturaVO[] coberturaVO = this.getSrvAuditoriaIndexadorService().coberturaLicencias(parametroAuditIndexador);
		if(logger.isDebugEnabled())logger.debug("Se obtiene el array de CoberturaVO[] " + coberturaVO);
		
		if(coberturaVO == null)
		{
			logger.warn("CoberturaVO es null");
			throw new Exception("Error al obtener los odes por licencias. El resultado es null");
		}
		informeLicenciasOdesVO = new InformeLicenciasOdesVO[coberturaVO.length];
		for (int i = 0; i < coberturaVO.length; i++)
		{
			informeLicenciasOdesVO[i] = new InformeLicenciasOdesVO();
			informeLicenciasOdesVO[i].setLicencia(licenciasIdiomaSelected[i]);
			informeLicenciasOdesVO[i].setNumOdes((coberturaVO[i].getNumDoc()).intValue());


		}
		if(logger.isDebugEnabled())logger.debug("El array de informes obtenido es el siguiente " + informeLicenciasOdesVO.length);
		
		if (informeLicenciasOdesVO == null)
		{
			logger.warn("Error al obtener los odes por licencias. informeLicenciasOdesVO es null");
			throw new Exception("Error al obtener los odes por licencias. El resultado es null");
		}
		return informeLicenciasOdesVO;
	}

	/**
	 * Devuelve los usuarios que se encuentran activos entre dos fechas concretas
	 * @param  parametrosInformeVO Value Object con las fechas, para las que se quiere obtener los usuarios activos, como atributos
	 * @return InformeUsuariosVO[] Array de Value Object con toda la información de cada uno de los usuarios activos
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeUsuariosVO[] handleInformeUsuarios(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe Usuarios");
		InformeUsuariosVO[] informeUsuariosVO = null;
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando usuarios. No hay parametros.");
			throw new Exception("Error calculando usuarios. No hay parametros.");
		}
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		ParametroAuditoriaUsuariosVO parametroAuditoriaUsuariosVO = new ParametroAuditoriaUsuariosVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditoriaUsuariosVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditoriaUsuariosVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los usuarios activos: FechaInicio " + parametroAuditoriaUsuariosVO.getFechaDesde() + " FechaFin : " + parametroAuditoriaUsuariosVO.getFechaHasta());
		UsuarioActivoVO[] usuariosActivos = this.getSrvAdminUsuariosService().usuariosActivos(parametroAuditoriaUsuariosVO);
		if (!(usuariosActivos == null))
		{
//			if(logger.isDebugEnabled())logger.debug("El resultado de la llamada a SrvAdminUsuariosService().usuariosActivos es " + usuariosActivos.length);
			informeUsuariosVO = new InformeUsuariosVO[usuariosActivos.length];
//			if(logger.isDebugEnabled())logger.debug("Mapeamos el resultado de SrvAdminUsuariosService().usuariosActivos: usuariosActivos al resultado de  SrvAuditoriaServie.InformeUsuarios: InformeUsuarioVO");
			if(logger.isDebugEnabled())logger.debug("Idioma "+parametrosInformeVO.getIdioma());
			ResourceBundle resource = this.getFicherRecursos(new Locale(parametrosInformeVO.getIdioma()));
			if(logger.isDebugEnabled())logger.debug("resource "+resource);
			for (int i = 0; i < usuariosActivos.length; i++)
			{
				//Calculamos el nombre completo y la cadena de los grupos separados por comas
				informeUsuariosVO[i] = new InformeUsuariosVO();
				String nombreCompleto = usuariosActivos[i].getNombre() + " " + usuariosActivos[i].getApellido1() + " " + usuariosActivos[i].getApellido2();
				informeUsuariosVO[i].setNombreCompleto(nombreCompleto);
				informeUsuariosVO[i].setEmail(usuariosActivos[i].getEmail());
				informeUsuariosVO[i].setNif(usuariosActivos[i].getNIF());
				informeUsuariosVO[i].setLogin(usuariosActivos[i].getLogin());
				informeUsuariosVO[i].setIdiomaBusqueda(this.getEtiqueta(usuariosActivos[i].getIdiomaBusqueda(),parametrosInformeVO.getIdioma()));
				informeUsuariosVO[i].setIdiomaAplicacion(this.getEtiqueta(usuariosActivos[i].getIdiomaAplicacion(),parametrosInformeVO.getIdioma()));
				informeUsuariosVO[i].setTipoEmpaquetador(resource.getString(usuariosActivos[i].getTipoEmpaquetador()));
				informeUsuariosVO[i].setGrupos(usuariosActivos[i].getGrupos());
				informeUsuariosVO[i].setPermisos(usuariosActivos[i].getPermisos());
			}
			if (informeUsuariosVO == null)
			{
				logger.warn("Error calculando usuarios. informeUsuariosVO es null.");
				throw new Exception("Error calculando usuarios. informeUsuariosVO es null.");
			}
			if(logger.isDebugEnabled())logger.debug("informeUsuarios "+informeUsuariosVO.length);
		}
		
		return informeUsuariosVO;
	}

	/**
	 * Obtiene los procesos planificados y su estado entre dos fechas concretas
	 * @param  parametrosInformeVO Value Object con las fechas, para las que se quiere obtener los procesos planificados
	 * @return InformeProcesoPlanificadoVO[] Array de Value Object con toda la información de cada uno de los procesos planificados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeProcesoPlanificadoVO[] handleInformeProcesosPlanificados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe procesos planificados");
		InformeProcesoPlanificadoVO[] informeProcesoPlanificadoVO = null;
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando los procesos planificados. No hay parametros.");
			throw new Exception("Error calculando los procesos planificados. No hay parametros.");
		}
		
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		ParametrosVO param = new ParametrosVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		param.setFechaDesde(gcDesde);
		param.setFechaHasta(gcHasta);
		TareaEjecutadaVO[] tareaEjecutada = this.getSrvAuditaPlanificadorService().obtenerTrabajosEjecutadosDesdeHasta(param);
		if ((tareaEjecutada == null) || (tareaEjecutada.length == 0))
		{
			if(logger.isDebugEnabled())logger.debug("No se devuelve ninguna tarea para ese periodo");
		} else
		{
			if(logger.isDebugEnabled())logger.debug("tareaEjecutadaVO " + tareaEjecutada);
			informeProcesoPlanificadoVO = new InformeProcesoPlanificadoVO[tareaEjecutada.length];
			for (int i = 0; i < tareaEjecutada.length; i++)
			{
				informeProcesoPlanificadoVO[i] = new InformeProcesoPlanificadoVO();
				informeProcesoPlanificadoVO[i].setEstado(tareaEjecutada[i].getEstado());
				Calendar fechaHasta = tareaEjecutada[i].getFechaFin();
				Calendar fechaDesde = tareaEjecutada[i].getFechaInicio();
				if (fechaHasta == null)
				{
					informeProcesoPlanificadoVO[i].setFechaFin("");
				} else
				{
					String fechaHastaS = (new Date(fechaHasta.getTimeInMillis())).toString();
					informeProcesoPlanificadoVO[i].setFechaFin(fechaHastaS);
				}
				String fechaDesdeS = new Date(fechaDesde.getTimeInMillis()).toString();
				informeProcesoPlanificadoVO[i].setFechaInicio(fechaDesdeS);
				informeProcesoPlanificadoVO[i].setNombre(tareaEjecutada[i].getDescripcion());
			}
		}
		return informeProcesoPlanificadoVO;
	}

	/**
	 * Devuelve los odes más valorados en el rango de fechas concreto
	 * @param  parametrosInformeVO Value Object con las fechas, para las que se quiere obtener y el número de odes más valorados
	 * @return InformeMasValoradoVO[] Array de Value Objects con toda la información de cada uno de los odes más valorados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeMasValoradoVO[] handleInformeMasValorado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe Mas Valorado");
		InformeMasValoradoVO[] informeMasValoradoVO = null;
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando odes mas valorados. No hay parametros.");
			throw new Exception("Error calculando odes mas valorados. No hay parametros.");
		}
		if (parametrosInformeVO.getRango() == 0)
		{
			logger.warn("Error calculando odes mas valorados. El parametro rango es 0.");
			throw new Exception("Error calculando odes mas valorados. El parametro rango es 0.");
		}
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
			
		ParametroAuditValoracionVO parametroAuditValoracionVO = new ParametroAuditValoracionVO();
		if(logger.isDebugEnabled())logger.debug("parametroAuditValoracionVO");
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditValoracionVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditValoracionVO.setFechaHasta(gcHasta);
		parametroAuditValoracionVO.setRango(parametrosInformeVO.getRango());
		ValoracionODEVO[] valoracionODEVO = this.getSrvAuditoriaValoracionService().contenidosMasValorados(parametroAuditValoracionVO);
		if (valoracionODEVO == null)
		{
			logger.warn("El servicio de valoracion para las fechas " + parametrosInformeVO.getFechaDesde() + " " + parametrosInformeVO.getFechaHasta() + " devuelve null");
		} else
		{
			//if(logger.isDebugEnabled())logger.debug("valoracionODEVO.length "+valoracionODEVO.length);
			informeMasValoradoVO = new InformeMasValoradoVO[valoracionODEVO.length];
			if(logger.isDebugEnabled())logger.debug("Despues de obtener informeMasValoradoVO "+valoracionODEVO.length);
			String[] idOdes = new String[valoracionODEVO.length];
			for (int j = 0; j < valoracionODEVO.length; j++)
			{
				idOdes[j] = valoracionODEVO[j].getIdODE();
//				if(logger.isDebugEnabled())logger.debug("idOdes de " + j + " vale " + idOdes[j]);

			}
			//Obtendre los titulos de los odes cuyo id se devuelve en este array
			DetallePublicadoODEVO[] detallePublicadoOdeVO = this.getSrvAuditaPublicacionService().titulosODEsPorID(idOdes);
			if(logger.isDebugEnabled())logger.debug("Obtenemos el array con los titulos de los odes detallePublicadoOdeVO.length " + detallePublicadoOdeVO.length);
			String[] tituloOdes = new String[detallePublicadoOdeVO.length];
			for (int k = 0; k < detallePublicadoOdeVO.length; k++)
			{
				tituloOdes[k] = detallePublicadoOdeVO[k].getTitulo();
			}
			for (int i = 0; i < valoracionODEVO.length; i++)
			{
				informeMasValoradoVO[i] = new InformeMasValoradoVO();
				informeMasValoradoVO[i].setTitulo(tituloOdes[i]);
				informeMasValoradoVO[i].setValoracion((valoracionODEVO[i].getValoracion()));
				informeMasValoradoVO[i].setIdOde(idOdes[i]);
				informeMasValoradoVO[i].setIdiomaODE(valoracionODEVO[i].getIdiomaODE());
				
			}
			if (informeMasValoradoVO == null)
			{
				logger.warn("Error calculando odes mas valorados. informeMasValoradoVO.");
				throw new Exception("Error calculando odes mas valorados. informeMasValoradoVO.");
			}
		}
		return informeMasValoradoVO;
	}

	/**
	 * Devuelve los odes más mostrados en el rango de fechas que se le pasa por
	 * parámetro independientemente de si se han mostrado desde navegador o
	 * desde un embed
	 * 
	 * @param parametrosInformeVO
	 *            Clase con los parámetros que necesita el método
	 * @return InformeMasValoradoVO[] Array de Value Objects con toda la
	 *         información de cada uno de los odes más mostrados
	 * @throws Exception
	 */
	@Override
	protected es.pode.auditoria.negocio.servicios.InformeMasVO[] handleInformeMasMostrado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("INFORME mas Mostrados generico");
		String operacion = this.getPropertyValue("mostrado") + SQL_ANY;
		return informeMasMostrado(parametrosInformeVO, operacion);
	}
	
	/**
	 * Devuelve los odes más mostrados en el rango de fechas que se le pasa por
	 * parámetro. Este recuento excluye las operaciones de mostrar desde un embed.
	 * 
	 * @param parametrosInformeVO
	 *            Clase con los parámetros que necesita el método
	 * @return InformeMasValoradoVO[] Array de Value Objects con toda la
	 *         información de cada uno de los odes más mostrados
	 * @throws Exception
	 */
	@Override
	protected InformeMasVO[] handleInformeMasMostradoBrowser(
			ParametrosInformeVO parametrosInformeVO) throws Exception {
//		logger.info("INFORME mas Mostrados desde navegador");
		String operacion = this.getPropertyValue("mostrado");
		return informeMasMostrado(parametrosInformeVO, operacion);
	}

	/**
	 * Devuelve los odes más mostrados en el rango de fechas que se le pasa por
	 * parámetro. Este recuento solo incluye las operaciones de mostrar ODE
	 * realizadas desde un embed de Agrega.
	 * 
	 * @param parametrosInformeVO
	 *            Clase con los parámetros que necesita el método
	 * @return InformeMasValoradoVO[] Array de Value Objects con toda la
	 *         información de cada uno de los odes más mostrados
	 * @throws Exception
	 */
	@Override
	protected InformeMasVO[] handleInformeMasMostradoEmbed(
			ParametrosInformeVO parametrosInformeVO) throws Exception {
//		logger.info("INFORME mas Mostrados desde embed");
		String operacion = this.getPropertyValue("mostrado_embed");
		return informeMasMostrado(parametrosInformeVO, operacion);
	}

	private InformeMasVO[] informeMasMostrado(ParametrosInformeVO parametrosInformeVO, String query) throws Exception {
		InformeMasVO[] resultado = this.obtenerMasVO(query,parametrosInformeVO);
		if(resultado == null)
		{
			if(logger.isDebugEnabled())logger.debug("No existen odes mas mostrados en el periodo de tiempo ");
		}else
		{
			if(logger.isDebugEnabled())logger.debug("Devolvemos los siguientes odes <"+resultado.length+">");
		}
		return resultado;
	}
	
	/**
	 * Devuelve los odes más previsualizados en el rango de fechas
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeMasValoradoVO[] Array de Value Objects con toda la información de cada uno de los odes más previsualizados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeMasVO[] handleInformeMasPrevisualizados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe mas previsualizado");
		String operacion = this.getPropertyValue("previsualizado");
		InformeMasVO[] resultado = this.obtenerMasVO(operacion,parametrosInformeVO);
		if(resultado == null)
		{
			if(logger.isDebugEnabled())logger.debug("No existen odes mas mostrados en el periodo de tiempo.");
		}else
		{
			if(logger.isDebugEnabled())logger.debug("Devolvemos los siguientes odes <"+resultado.length+">");
		}
		return resultado;
		
	
	}


	/**
	 * Devuelve los odes más descargados en el rango de fechas que se le pasa por parámetro
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeMasVO[] Array de Value Objects con toda la información de cada uno de los odes más descargados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeMasVO[] handleInformeMasDescargados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
	//		logger.info("Informe mas previsualizado");
		String operacion = this.getPropertyValue("descargado") + SQL_ANY;
		InformeMasVO[] resultado = this.obtenerMasVO(operacion,parametrosInformeVO);
		if(resultado == null)
		{
			if(logger.isDebugEnabled())logger.debug("No existen odes mas mostrados en el periodo de tiempo ");
		}else
		{
			if(logger.isDebugEnabled())logger.debug("Devolvemos los siguientes odes "+resultado.length);
		}
		return resultado;
			
	
	}
	/**
	 * Devuelve los odes más enviados en el rango de fechas que se le pasa por parámetro
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeMasVO[] Array de Value Objects con toda la información de cada uno de los odes más descargados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeMasVO[] handleInformeMasEnviado(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
	
//		logger.info("Informe mas enviados");
		String operacion = this.getPropertyValue("enviado");
		InformeMasVO[] resultado = this.obtenerMasVO(operacion,parametrosInformeVO);
		if(resultado == null)
		{
			if(logger.isDebugEnabled())logger.debug("No existen odes mas mostrados en el periodo de tiempo ");
		}else
		{
			if(logger.isDebugEnabled())logger.debug("Devolvemos los siguientes odes "+resultado.length);
		}
		return resultado;
		
	}

	/**
	 * Obtiene un array de InformeMasPesadosVO con los odes que tienen mayor tamaño en la plataforma en un rango de fechas concreto
	 * @param  parametrosInformeVO Clase con los parámetros que necesita el método
	 * @return InformeMasPesadosVO[] VO con la información de los odes más pesados
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.InformeMasPesadosVO[] handleInformeMasPesados(es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws java.lang.Exception
	{
//		logger.info("Informe mas pesados");
		InformeMasPesadosVO[] informeMasPesadosVO = null;
		if (parametrosInformeVO == null)
		{
			logger.warn("Error calculando odes mas pesados. No hay parametros.");
			throw new Exception("Error calculando odes mas pesados. No hay parametros.");
		}
		if (parametrosInformeVO.getRango() == 0)
		{
			logger.warn("Error calculando odes mas pesados. El parametro rango es 0.");
			throw new Exception("Error calculando odes mas pesados. El parametro rango es 0.");
		}
		//Validamos las fechas
		if(!(this.validarFechas(parametrosInformeVO.getFechaDesde(), parametrosInformeVO.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		
		ParametroAuditPublicacionVO parametroAuditPublicacionVO = new ParametroAuditPublicacionVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		gcDesde.setTime((parametrosInformeVO.getFechaDesde()).getTime());
		parametroAuditPublicacionVO.setFechaDesde(gcDesde);
		GregorianCalendar gcHasta = new GregorianCalendar();
		gcHasta.setTime((parametrosInformeVO.getFechaHasta()).getTime());
		parametroAuditPublicacionVO.setFechaHasta(gcHasta);
		if(logger.isDebugEnabled())logger.debug("Parametros de busqueda de los odes por estado: FechaInicio " + parametroAuditPublicacionVO.getFechaDesde() + " FechaFin : " + parametroAuditPublicacionVO.getFechaHasta());
		parametroAuditPublicacionVO.setRango(parametrosInformeVO.getRango());
		PesoODEVO[] pesoOdeVO = this.getSrvAuditaPublicacionService().dimensionesODEsPublicados(parametroAuditPublicacionVO);

		if ((pesoOdeVO == null))
		{
			logger.warn("No se devuelve ningun resultado");

		} else
		{
			if(logger.isDebugEnabled())logger.debug("El resultado de la llamada a SrvAuditaPublicacionService().odesPorEstados es " + pesoOdeVO.length);
			informeMasPesadosVO = new InformeMasPesadosVO[pesoOdeVO.length];
//			if(logger.isDebugEnabled())logger.debug("Mapeamos el resultado de SrvAuditaPublicacionService(): dimensionesODEsPublicados al resultado de  SrvAuditoriaServie.dimensionesODEsPublicados: InformeMasPesadosVO");
			for (int i = 0; i < pesoOdeVO.length; i++)
			{
				informeMasPesadosVO[i] = new InformeMasPesadosVO();
				this.getBeanMapper().map(pesoOdeVO[i], informeMasPesadosVO[i]);
				
			}

		}
		return informeMasPesadosVO;
	}
	
	
	/**
	 * Obtiene un array de InformeRepositorioVO con todos los odes del repositorio
	 * @param  idioma Idioma en el que se presentará el catálogo
	 * @param fechaActualizacion representa la fecha a partir de la cual lo ODEs se marcan como si fueran nuevos en el informe
	 * @return InformeRepositorioVO[] Array de Value Objects con la información del repositorio. Se utilizará para el informe del catálogo
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.RepositorioVO[] handleRepositorio(String idioma, String fechaActualizacion) throws java.lang.Exception
	{
		RepositorioVO[] resultado = null;
		RepositorioVO[] resultadoTemp = null;
		Vector odesDuplicados = new Vector();
		RepositorioVO repositorioVO = null;
		String[] niveles = null;
		
		Calendar fechaAct=null;
		//HashMap en el que se almacenarán los identificadores del árbol y el nombre del árbol
		HashMap arbolCurricular = new HashMap();

		try
		{
			
		
			//Paso la fecha de Actualizacion de String a Calendar
			if (fechaActualizacion!=null && fechaActualizacion.length()>0){
				fechaAct=Calendar.getInstance();
				SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = null;
				try {
					fecha = formatoDeFecha.parse(fechaActualizacion);
				} catch (Exception ex) {
					logger.error("No se ha podido parsear la fecha de Actualizacion - ", ex);
				}
				fechaAct.setTime(fecha);
			}
	
	
			//Vector en el que se almacenan los identificadores de los nodos de segundo nivel
			//Este vector será el utilizado para hacer la llamada a fuentes taxonómicas para obtener el nombre del nodo del árbol
			Vector identificadorNodos = new Vector();
	//		String idioma = ""; //Idioma en el que se presentará el catálogo, será el idioma por defecto de la plataforma
			
			//Array de identificadores del área curricular, se añadirá a la ficha
			String[] idenAreaCurricular = null;
			ArrayList listaIdenAreaCurricular = new ArrayList();
			
			String[] idenNodos = null;
			
	//		logger.info("repositorio!!!!");
			if(logger.isDebugEnabled())logger.debug("idioma "+idioma);
			
			ResultadoRepositorioVO[] resultadoRepositorio = this.getSrvBuscadorService().obtenerCatalogoRepositorioParam(this.nivelAgregacionCatalogo);
			
			if(logger.isDebugEnabled())logger.debug("resultadoRepositorio "+resultadoRepositorio);
			Locale local=new Locale(idioma);
			if(logger.isDebugEnabled())logger.debug("El contenido del repositorio es "+resultadoRepositorio);
			if(!(resultadoRepositorio == null))
			{
				resultadoTemp = new RepositorioVO[resultadoRepositorio.length];
				//Esta es la url en donde irá la imagen que marca a un ODE como nuevo
				StringBuffer url = new StringBuffer();
				url.append(AgregaPropertiesImpl.getInstance().getUrlNodo()).append("/").append("static/img/091125_indra_agrega_icon_1-5cm.jpg");
				
				if(logger.isDebugEnabled())logger.debug("El resultado del indice "+resultadoRepositorio.length);
				if(logger.isDebugEnabled())logger.debug("Mapeo cada uno de los VO del buscador");
				//En el caso de que un ODE este repetido en varios nodos del árbol se repetirán los objetos en el array resultado
				/////Sacamos los valores obtenidos del indexador
					
			for (int i = 0; i < resultadoRepositorio.length; i++)
			{		
				resultadoTemp[i] = new RepositorioVO();
				resultadoTemp[i].setDescripcion(resultadoRepositorio[i].getDescripcion());
				resultadoTemp[i].setEdad(resultadoRepositorio[i].getEdad());
				resultadoTemp[i].setTitulo(resultadoRepositorio[i].getTitulo().trim());
				resultadoTemp[i].setFechaPublicacion(resultadoRepositorio[i].getFechaPublicacion());
				//Paso la fecha de publicación de String a Calendar
				Calendar fechaPubli=null;
				if (resultadoTemp[i].getFechaPublicacion()!=null && resultadoTemp[i].getFechaPublicacion().length()>0){
					fechaPubli=Calendar.getInstance();
					SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
					Date fecha2 = null;
					try {
					    fecha2 = formato.parse(resultadoTemp[i].getFechaPublicacion());
					} catch (Exception ex) {
						logger.error("No se ha podido parsear la fecha de Publicacion - ", ex);
		
					}
					fechaPubli.setTime(fecha2);
				}
				if (fechaPubli!=null && fechaAct!=null){
					if (fechaPubli.after(fechaAct) || fechaPubli.equals(fechaAct)){
						resultadoTemp[i].setUrlNew(url.toString());
					}
				}else{
					resultadoTemp[i].setUrlNew("");
				}
				
				
	//			logger.debug("Vamos a traducir el idioma");
				
				
				resultadoTemp[i].setIdiomaTexto((this.getFicherRecursos(local).getString(resultadoRepositorio[i].getIdiomaTexto())));
				if(logger.isDebugEnabled())logger.debug("El idoma traducido  al idioma correspondiente es <"+ idioma+ "> , <"+resultadoTemp[i].getIdiomaTexto()+">");
				String urlImagen = resultadoRepositorio[i].getUrlImagen();
	//			if(logger.isDebugEnabled())logger.debug("Imagen de la posicion <"+i+"> es <"+urlImagen+">");
				if((!(urlImagen == null))&&(!(urlImagen == ""))&&(!(urlImagen == " ")))
				{
	//				if(logger.isDebugEnabled())logger.debug("La url de la imagen pequeña esta indexada");
					int indiceExtension = urlImagen.indexOf(".png");
					if(!(indiceExtension == -1))
					{
						resultadoTemp[i].setUrlImagen(urlImagen.substring(0, indiceExtension)+"_captured.jpg");
					}else
					{
	//					if(logger.isDebugEnabled())logger.debug("La imagen pequeña no es extensión .png");
						resultadoTemp[i].setUrlImagen(resultadoRepositorio[i].getUrlImagen());
					}
				}else
				{
	//				if(logger.isDebugEnabled())logger.debug("No esta indexada la imagen pequeña");
					resultadoTemp[i].setUrlImagen(resultadoRepositorio[i].getUrlImagen());
				}
				resultadoTemp[i].setIdioma(resultadoRepositorio[i].getIdioma());
				resultadoTemp[i].setIdentificador(resultadoRepositorio[i].getIdentificador());
				String[] objetivos = resultadoRepositorio[i].getObjetivos();
				
				//Recogemos los objetivos
				String objetivo = "";
				for(int j=0;j<objetivos.length;j++)
				{
					objetivo = objetivo + objetivos[j] + " ";
				}
				resultadoTemp[i].setObjetivos(objetivo);
	//			if(logger.isDebugEnabled())logger.debug("añadidos los objetivos");
				
				String[] areasCurriculares = resultadoRepositorio[i].getAreaCurricularPath();
	//			if(logger.isDebugEnabled())logger.debug("Añadidos las areasCurriculares.length "+areasCurriculares.length);
			  
	//			if(logger.isDebugEnabled())logger.debug("Asignamos las areas curriculares a la ficha del ode");
				resultadoTemp[i].setAreaCurriculares(areasCurriculares);
				
				if((!(areasCurriculares == null))&&(areasCurriculares.length > 0))
				{
	//				if(logger.isDebugEnabled())logger.debug("areasCurriculares[0]"+areasCurriculares[0]);
					if((areasCurriculares[0].equalsIgnoreCase(""))||(areasCurriculares[0].equalsIgnoreCase(" ")))
					{
	//					if(logger.isDebugEnabled())logger.debug("El ode no tiene area curricular le asigno una ");
						resultadoTemp[i].setNivel1Cobertura("999999");
					}else
					{
						niveles = areasCurriculares[0].split("/");
	//					if(logger.isDebugEnabled())logger.debug("niveles.length "+niveles.length);
						resultadoTemp[i].setNivel1Cobertura(niveles[0]);
	//					if(logger.isDebugEnabled())logger.debug("El nodo del primer nivel "+niveles[0]);
										
						if(niveles.length > 2)
						{
							resultadoTemp[i].setNivel2Cobertura(niveles[1]);
	//						if(logger.isDebugEnabled())logger.debug("El nodo del segundo nivel "+niveles[1]);
							resultadoTemp[i].setNivel3Cobertura(niveles[2]);
	//						if(logger.isDebugEnabled())logger.debug("El nodo del tercer nivel "+niveles[2]);
							//Añado al vector los identificadores de los nodos de segundo nivel
							identificadorNodos.add(niveles[1]);
							identificadorNodos.add(niveles[2]);
						}else
						{
							if(niveles.length == 2)
							{
								resultadoTemp[i].setNivel2Cobertura(niveles[1]);
	//							if(logger.isDebugEnabled())logger.debug("El nodo del segundo nivel "+niveles[1]);
								identificadorNodos.add(niveles[1]);
							}else
							{
								identificadorNodos.add(niveles[0]);
							}
						}
					}
				}else /////////nuevo
				{
					resultadoTemp[i].setNivel1Cobertura("999999");
				}
				
				if(areasCurriculares.length > 1)
				{
	//				logger.info("Tenemos mas de una area curricular");
					//El contenido se encuentra en varias ramas
					//Creo un nuevo objeto con cada una de las ramas en las que se encuentra
					for(int j=0;j<areasCurriculares.length -1;j++)
					{
						try
						{
						repositorioVO = new RepositorioVO();
						repositorioVO.setDescripcion(resultadoTemp[i].getDescripcion());
						repositorioVO.setEdad(resultadoTemp[i].getEdad());
						repositorioVO.setTitulo(resultadoTemp[i].getTitulo());
						repositorioVO.setIdiomaTexto(resultadoTemp[i].getIdiomaTexto());
						repositorioVO.setFechaPublicacion(resultadoTemp[i].getFechaPublicacion());
						urlImagen = resultadoTemp[i].getUrlImagen();
						if(logger.isDebugEnabled())logger.debug("Imagen de la posicion "+j+" "+urlImagen);
						if((!(urlImagen == null))&&(!(urlImagen == ""))&&(!(urlImagen == " ")))
						{
	//						if(logger.isDebugEnabled())logger.debug("La url de la imagen pequeña esta indexada");
							int indiceExtension = urlImagen.indexOf(".png");
							if(!(indiceExtension == -1))
							{
								repositorioVO.setUrlImagen(urlImagen.substring(0, indiceExtension)+"_captured.jpg");
							}else
							{
	//							if(logger.isDebugEnabled())logger.debug("La imagen pequeña no es extensión .png");
								repositorioVO.setUrlImagen(resultadoTemp[i].getUrlImagen());
							}
						}else
						{
	//						if(logger.isDebugEnabled())logger.debug("No esta indexada la imagen pequeña");
							repositorioVO.setUrlImagen(resultadoTemp[i].getUrlImagen());
						}
						if (fechaPubli!=null && fechaAct!=null){
							if (fechaPubli.after(fechaAct) || fechaPubli.equals(fechaAct)){
								repositorioVO.setUrlNew(resultadoTemp[i].getUrlNew());
							}
						}else{
							repositorioVO.setUrlNew(resultadoTemp[i].getUrlNew());
						}
						//repositorioVO.setUrlImagen(resultadoTemp[i].getUrlImagen());
						repositorioVO.setIdioma(resultadoTemp[i].getIdioma());
						repositorioVO.setIdentificador(resultadoTemp[i].getIdentificador());
						repositorioVO.setObjetivos(resultadoTemp[i].getObjetivos());
						niveles = areasCurriculares[j+1].split("/");
						repositorioVO.setNivel1Cobertura(niveles[0]);
	//					if(logger.isDebugEnabled())logger.debug("El nodo del primer nivel "+niveles[0]);
						repositorioVO.setAreaCurriculares(resultadoTemp[i].getAreaCurriculares());
						if(niveles.length > 2)
						{
							repositorioVO.setNivel2Cobertura(niveles[1]);
							identificadorNodos.add(niveles[1]);
							repositorioVO.setNivel3Cobertura(niveles[2]);
							identificadorNodos.add(niveles[2]);
						}else
						{
							if(niveles.length == 2)
							{
								repositorioVO.setNivel2Cobertura(niveles[1]);
								identificadorNodos.add(niveles[1]);
							}else
							{
								identificadorNodos.add(niveles[0]);
							}
						}
						//Añado al vector los identificadores de los nodos de segundo nivel y los del primer nivel si no tiene de segundo nivel
						
	//					if(logger.isDebugEnabled())logger.debug("El nodo del segundo nivel "+niveles[1]);
						//Se añade el VO con el nuevo nodo del árbol curricular
						odesDuplicados.add(repositorioVO);
						}catch(Exception e)
						{
							logger.error("[catalogo]exception al obtener las siguientes areas curriculares ",e);
						}
					}
				}
				
				//Recorremos el array de areas curriculares para obtener el array de identificadores para la ficha
				for(int j=0;j < areasCurriculares.length ; j++)
				{
					String[] areasTemp = areasCurriculares[j].split("/");
					for(int k=0;k<areasTemp.length;k++)
					{
						listaIdenAreaCurricular.add(areasTemp[k]);
	//					if(logger.isDebugEnabled())logger.debug("Identificador de areaCurricular "+areasTemp[k]);
					}
					
					//La lista listaIdenAreaCurricular se usará para llamar a fuentesTaxonómicas y obtener todos los identificadores de los nodos para la ficha
				}
				
			}
				
			
				
			
			//Añadimos al array resultado los elementos del vector con los odes duplicados
			
			if(odesDuplicados.size() > 0)
			{
				if(logger.isDebugEnabled())logger.debug("Existen odes que se encuentran en varias ramas");
				if(logger.isDebugEnabled())logger.debug("Se añaden los vo duplicados al array de VO de resultado");
				RepositorioVO[] repositorioDuplicado = (RepositorioVO[])odesDuplicados.toArray(new RepositorioVO[0]);
				resultado = new RepositorioVO[resultadoTemp.length+repositorioDuplicado.length];
				System.arraycopy(resultadoTemp, 0, resultado, 0, resultadoTemp.length);
				System.arraycopy(repositorioDuplicado, 0, resultado, resultadoTemp.length, repositorioDuplicado.length);
	//			if(logger.isDebugEnabled())logger.debug("Se copia el array con todos los odes al array resultado");
				
				
			}else
			{
				if(logger.isDebugEnabled())logger.debug("No existen odes que se encuentran en varias ramas");
				resultado = new RepositorioVO[resultadoTemp.length];
				resultado = resultadoTemp;
			}
			//Obtengo el nombre de cada una de las ramas del nodo llamando a fuentes taxonómicas
			//Obtengo el array con los identificadores de los nodos y llamo a fuentes taxonómicas
	//		logger.info("Obtengo los identificadores de de los nodos");
			if(logger.isDebugEnabled())logger.debug("listaIdenAreaCurricular.size "+listaIdenAreaCurricular.size());
			idenNodos = new String[listaIdenAreaCurricular.size()];
			if(logger.isDebugEnabled())logger.debug("idenNodos "+idenNodos.length);
			idenNodos = (String[])listaIdenAreaCurricular.toArray(new String[0]);
			if(logger.isDebugEnabled())logger.debug("idNodos.length "+idenNodos.length);
	//		String idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			if(logger.isDebugEnabled())logger.debug("Obtener Catalogo el idioma  es "+idioma);
			
			TaxonPathVO[] taxonPathVO = this.getSrvTaxonomiaService().obtenerVariosTaxonPaths(idenNodos, this.getPropertyValue("nombreTaxonomia"), idioma);
			
			if(logger.isDebugEnabled())logger.debug("El tamanio del array del taxonPath es <"+taxonPathVO.length+">");
			//Recorremos el array resultado para poblar el hashmap con los identificadores y sus nombres
			for(int j=0;j<taxonPathVO.length;j++)
			{
				TaxonVO[] taxonVO = taxonPathVO[j].getDatos();
				for(int k=0;k<taxonVO.length;k++)
				{
					String identificador = taxonVO[k].getId();
					String nombreNodo = taxonVO[k].getValorTax();
					arbolCurricular.put(identificador, nombreNodo);
				}
			}
				
	//		if(logger.isDebugEnabled())logger.debug("Se han obtenido los hashmap con los nodos del arbol traducidos "+arbolCurricular.size());
			
			
			//  Únicamente vamos a llamar a fuentes taxonómicas con todos los identificadores de los nodos
			  
			
					
			
			//Recorremos el array de RepositorioVO para asinar los nombres de los nodos del árbol
			
			if(logger.isDebugEnabled())logger.debug("resultado tiene longitud de <"+resultado.length+">");
			for(int i=0;i<resultado.length;i++)
			{
	//			if(logger.isDebugEnabled())logger.debug("resultado[i].getNivel1Cobertura() "+resultado[i].getNivel1Cobertura());
	//			if(logger.isDebugEnabled())logger.debug("resultado[i].getNivel2Cobertura() "+resultado[i].getNivel2Cobertura());
	//			if(logger.isDebugEnabled())logger.debug("resultado[i].getNivel3Cobertura() "+resultado[i].getNivel3Cobertura());
				if(!(resultado[i].getNivel1Cobertura() == null))
				{
				if(!(resultado[i].getNivel1Cobertura().equals("999999")))
				{
					if(!(arbolCurricular.get(resultado[i].getNivel1Cobertura()) == null))
					{
						resultado[i].setNivel1Cobertura(resultado[i].getNivel1Cobertura()+"   "+(String)arbolCurricular.get(resultado[i].getNivel1Cobertura()));
					}else
					{
						resultado[i].setNivel1Cobertura(resultado[i].getNivel1Cobertura());
					}
				}else
				{
					resultado[i].setNivel1Cobertura((this.getFicherRecursos(local).getString("general")));
					logger.debug("Traducido general al idioma correspondiente, "+ idioma+" es "+ resultado[i].getNivel1Cobertura());
				}
			}
				if(!(resultado[i].getNivel2Cobertura() == null))
				{
					if(!(arbolCurricular.get(resultado[i].getNivel2Cobertura()) == null))
					{
						resultado[i].setNivel2Cobertura(resultado[i].getNivel2Cobertura()+"   "+(String)arbolCurricular.get(resultado[i].getNivel2Cobertura()));
					}else
					{
						resultado[i].setNivel2Cobertura(resultado[i].getNivel2Cobertura());
					}
					
				}else
				{
					resultado[i].setNivel2Cobertura("");
					
				}
				if(!(resultado[i].getNivel3Cobertura() == null))
				{
					if(!(arbolCurricular.get(resultado[i].getNivel3Cobertura()) == null))
					{
						resultado[i].setNivel3Cobertura(resultado[i].getNivel3Cobertura()+"   "+(String)arbolCurricular.get(resultado[i].getNivel3Cobertura()));
					}else
					{
						resultado[i].setNivel3Cobertura(resultado[i].getNivel3Cobertura());
					}
					
				}else
				{
					resultado[i].setNivel3Cobertura("");
	//				
				}
				
	//			if(logger.isDebugEnabled())logger.debug("resultado[i].getAreaCurriculares().lenth "+resultado[i].getAreaCurriculares().length);
				//Obtenemos las áreas curriculares traducidas que se añadirá en la ficha
				idenAreaCurricular = new String[resultado[i].getAreaCurriculares().length];
				String nombreAreaCurricular = "";
				for (int k=0; k < resultado[i].getAreaCurriculares().length; k++)
				{
	//				if(logger.isDebugEnabled())logger.debug("resultado[i].getAreaCurriculares())[k] "+k+" "+(resultado[i].getAreaCurriculares())[k]);
					String[] areasC = ((resultado[i].getAreaCurriculares())[k]).split("/") ;
					
					String areaTemporal = "";
					String areaT = "";
					for(int l = 0;l < areasC.length ; l++)
					{
						areaTemporal = "";
						
						if(l == areasC.length-1)
						{
							
							if(!(arbolCurricular.get(areasC[l]) == null))
							{
			
								areaTemporal = (String)arbolCurricular.get(areasC[l]);
								
							}
							
						}else
						{
							if(!(arbolCurricular.get(areasC[l]) == null))
							{
								areaTemporal = (String)arbolCurricular.get(areasC[l]) + "/";
								
							}
							
						}
						areaT = areaT + areaTemporal;
					}
					
					idenAreaCurricular[k] = areaT;
					nombreAreaCurricular = nombreAreaCurricular + "- " +idenAreaCurricular[k]+"<br>";
				}
				resultado[i].setAreaCurriculares(idenAreaCurricular);
				
				if(nombreAreaCurricular.equalsIgnoreCase("- <br>"))
				{
					resultado[i].setNombreAreaCurricular("");
				}else
				{
					resultado[i].setNombreAreaCurricular(nombreAreaCurricular);
				}
				
			
			//Comprobamos los valores de Nivel1Cobertura, Nivel2Cobertura y Nivel3Cobertura para asegurarnos que el menu
			//lateral se ve correctamente
				/*
				if((resultado[i].getNivel2Cobertura() == "")&&(resultado[i].getNivel3Cobertura() == ""))
				{
					resultado[i].setNivel3Cobertura(resultado[i].getNivel1Cobertura());
					resultado[i].setNivel1Cobertura("");
				}else
				{
					if((resultado[i].getNivel3Cobertura() == "")&&!(resultado[i].getNivel2Cobertura() == ""))
					{
						resultado[i].setNivel3Cobertura(resultado[i].getNivel2Cobertura());
						resultado[i].setNivel2Cobertura(resultado[i].getNivel1Cobertura());
						resultado[i].setNivel1Cobertura("");
					}else
					{
						if(!(resultado[i].getNivel3Cobertura() == "")&&(resultado[i].getNivel2Cobertura() == ""))
						{
							resultado[i].setNivel2Cobertura(resultado[i].getNivel1Cobertura());
							resultado[i].setNivel1Cobertura("");
						}
					}
				}
				*/
				
			}
			
			
			
			if(logger.isDebugEnabled())logger.debug("resultado "+resultado.length);
			}else
			{
				logger.info("No existe ningun ode de nivel de agregacion mayor que 3");
			}
		}catch (Exception e) {
			logger.error("Error al obtener el informe de catálogo :" + e.getMessage());
			
		}
		
		return resultado;
	}
	/**
	 * Almacena en base de datos las búsquedas realizadas por los usuarios
	 * @param  busqueda Value Object con la búsqueda realizada
	 * @throws Exception
	 */
	protected void handleAlmacenarBusquedaBD(BusquedaVO busqueda) throws java.lang.Exception
	{

//		if(logger.isDebugEnabled())logger.debug("SRVAUDITORIASERVICIO AlmacenarBusquedaBD busqueda " + busqueda);
		try
		{
			this.getBusquedaDao().create(this.getBusquedaDao().fromBusquedaVO(busqueda));

		} catch (Exception e)
		{
			logger.error("Se produce un error al registrar el termino de busqueda " + e);

		}
	}

	/**
	 * Almacena en base de datos las operaciones realizadas por los usuarios
	 * @param  operacion Value Object con la operación realizada
	 * @throws Exception
	 */

	protected void handleAlmacenarOperacionBD(OperacionVO operacion) throws java.lang.Exception
	{

//		if(logger.isDebugEnabled())logger.debug("AlmacenarOperacionBD");
		try
		{
			operacion.setDespublicado(0);
			if(logger.isDebugEnabled())logger.debug("Actualizado valor despublicado");
			this.getOperacionDao().create(this.getOperacionDao().fromOperacionVO(operacion));

		} catch (Exception e)
		{
			logger.error("Se produce un error al registrar el termino de busqueda " + e);

		}
	}

	/**
	 * Obtiene los informes que se encuentran registrados en la plataforma
	 * @return String[] Array con los identificadores de los informes
	 * @throws Exception
	 */
	protected String[] handleObtenerInformes() throws java.lang.Exception
	{
		
		
		String[] informes = null;
//		if(logger.isDebugEnabled())logger.debug("Obtener informes");
		String descInformes = this.getPropertyValue("informes");
		informes = descInformes.split(",");
		return informes;
		
				

	}

	/**
	 * Crea un informe con los datos que se pasan como parámetro
	 * @param  parametrosCrearInformeInforme Value Object con los parámetros que necesita el método para crear el informe
	 * @return DataHandler con la información del informe
	 * @throws Exception
	 */

	protected javax.activation.DataHandler handleCrearInforme(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametrosCrearInformeInforme) throws java.lang.Exception
	{
		
		
		IRunAndRenderTask task = null;
		String fechaDesde = "";
		String fechaHasta = "";
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String rangoFechas = "";
        
		//Validamos el VO con los parámetros del informe
		if(!(this.validarParametrosInforme(parametrosCrearInformeInforme).booleanValue()))
		{
			logger.warn("El vo con los parametros no es correcto");
			throw new Exception("parametrosCrearInformeInforme es incorrecto o no se ha seleccionado ningun informe");
		}
		
		
		String informe = parametrosCrearInformeInforme.getNombreInforme();
		fechaDesde = this.obtenerFecha(parametrosCrearInformeInforme, "desde");
		fechaHasta = this.obtenerFecha(parametrosCrearInformeInforme, "hasta");
		
		rangoFechas = this.obtenerRangoFechas(parametrosCrearInformeInforme);
		if(logger.isDebugEnabled())logger.debug("rangoFechas: <" + rangoFechas+"> fecha desde <" + fechaDesde+"> hasta <" + fechaHasta+">. Informe <" + informe+">");
		
		//Calculamos las fechas:

		try
		{
			String nombreFichero = getPlantillasInformes() + informe + ".rptdesign";
			if(logger.isDebugEnabled())logger.debug("nombreFichero -> " + nombreFichero);
			//Saco según el tipo de informe los parámetros que le voy a asignar
			task = this.levantarPlataforma(nombreFichero);
			
			//Sacamos la fecha con el TimeZone
			String offSetDesde = this.getOffSetDate(parametrosCrearInformeInforme.getAnioDesde(), parametrosCrearInformeInforme.getMesDesde(), parametrosCrearInformeInforme.getDiaDesde());
			String offSetHasta = this.getOffSetDate(parametrosCrearInformeInforme.getAnioHasta(), parametrosCrearInformeInforme.getMesHasta(), parametrosCrearInformeInforme.getDiaHasta());
			task.setParameterValue("RP_fechaHasta", fechaHasta + "T23:59:59+0" + offSetHasta + ":00");
			task.setParameterValue("RP_fechaDesde", fechaDesde + "T00:00:00+0" + offSetDesde + ":00");
			task.setParameterValue("RP_rangoFechas", rangoFechas);
			String idioma = LdapUserDetailsUtils.getIdioma();
			if(logger.isDebugEnabled())logger.debug("RANGO -> " + parametrosCrearInformeInforme.getRango());
			if (idioma == null)
			{
				idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			task.setParameterValue("RP_Idioma", idioma);
			if (informe.equalsIgnoreCase("estadoOdes") || informe.equalsIgnoreCase("operacionesRealizadas") || informe.equalsIgnoreCase("nivelAgregacion") || informe.equalsIgnoreCase("coberturaCurricular") || informe.equalsIgnoreCase("odesLicencias")
					|| informe.equalsIgnoreCase("usuarios") || informe.equalsIgnoreCase("procesosPlanificados"))
			{
				if(logger.isDebugEnabled())logger.debug("El informe solicitado solo necesita las fechas");
				
			} else
			{
				if (informe.equalsIgnoreCase("terminosBusqueda") || informe.equalsIgnoreCase("masValorado") || informe.equalsIgnoreCase("masMostrado") || informe.equalsIgnoreCase("masPrevisualizado") || informe.equalsIgnoreCase("masVisualizado")
						|| informe.equalsIgnoreCase("masDescargado") || informe.equalsIgnoreCase("tamanio"))
				{
					if(logger.isDebugEnabled())logger.debug("El informe solicitado necesita fechas y rango");
					task.setParameterValue("RP_Rango", parametrosCrearInformeInforme.getRango());

				} else
				{
					if (informe.equalsIgnoreCase("odesUsuario"))
					{
						if(logger.isDebugEnabled())logger.debug("El informe seleccionado necesita fechas y usuario");
						task.setParameterValue("RP_Usuario", parametrosCrearInformeInforme.getUsuario());

					}

				}
				//Para los informas 'Mas' vamos a añadir el parámetro con el nodo y la url de la ficha para crear los enlaces
				if (informe.equalsIgnoreCase("masMostrado") || informe.equalsIgnoreCase("masPrevisualizado") || informe.equalsIgnoreCase("masVisualizado")
						|| informe.equalsIgnoreCase("masDescargado") || informe.equalsIgnoreCase("tamanio") || informe.equalsIgnoreCase("masValorado"))
				{
					if(logger.isDebugEnabled())logger.debug("El informe contiene enlaces a la ficha");
					
					String urlNodo =  this.getAgregaPropertyValue("host");
					String urlFicha = this.getPropertyValue("urlFicha");
					task.setParameterValue("RP_UrlFicha", "http://"+urlNodo+LdapUserDetailsUtils.getSubdominio() + urlFicha);

				}
			}

			String formato = parametrosCrearInformeInforme.getFormato();
			if(logger.isDebugEnabled())logger.debug("formato -> " + formato);
			if (formato == null)
			{
				logger.warn("El formato introducido es null");
				throw new Exception("El formato del informe es null");
			}
			if (formato.equalsIgnoreCase("html"))
			{
				//Añado el locale correspondiente al idioma de navegacion
				Locale locale = new Locale(idioma);
				task.setLocale(locale);
				HTMLRenderOption options = new HTMLRenderOption();
				options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_HTML);
				options.setHtmlPagination(true);
				options.setImageHandler(new HTMLServerImageHandler());
				options.setImageDirectory(this.getAgregaPropertyValue("imgBirtDir"));
				if(logger.isDebugEnabled())logger.debug("options.getImageDirectory() " + options.getImageDirectory());
				options.setBaseImageURL(AgregaPropertiesImpl.getInstance().getUrlNodo()+this.getAgregaPropertyValue("staticImgDir"));
				if(logger.isDebugEnabled())logger.debug("options.getBaseImageURL() " + options.getBaseImageURL());
				options.setOutputStream(response);
				task.setRenderOption(options);
				
			} else
			{
				if (formato.equalsIgnoreCase("pdf"))
				{
					if(logger.isDebugEnabled())logger.debug("el formato del informe es pdf");
					Locale locale = new Locale(idioma);
					task.setLocale(locale);
					PDFRenderOption options = new PDFRenderOption();
					options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
					options.setOutputStream(response);
					task.setRenderOption(options);
					

				} else
				{
					if (formato.equalsIgnoreCase("excel"))
					{
						if(logger.isDebugEnabled())logger.debug("el formato del informe es excel");
						Locale locale = new Locale(idioma);
						task.setLocale(locale);
						IRenderOption options = new RenderOption();
						options.setOutputFormat("xls");
						options.setOutputStream(response);
						task.setRenderOption(options);
						
					} else
					{
						logger.warn("El formato introducido no es correcto");
						throw new Exception("El formato del informe no es correcto");
					}
				}
			}

			task.run();
			DataHandler dataHandler = new DataHandler(new SourceDataSource(null, "text/html", new StreamSource(new ByteArrayInputStream(response.toByteArray()))));
			task.close();
			//No se elimina la plataforma
			/////  engine.destroy();
			/////  if(logger.isDebugEnabled())logger.debug("engine.destroy");
			///// Platform.shutdown();
			////  if(logger.isDebugEnabled())logger.debug("Platform.shutdown()");
			return dataHandler;

		} catch (Exception e)
		{
			logger.error("Error al crear el informe ",e);
			task.close();
			////  engine.destroy();
			////  Platform.shutdown();
			throw new Exception(e);
		}
		

	}

	/**
	 * Genera y guarda en un directorio un informe con los datos que se pasan como parámetro
	 * @param  parametroCrearInformeVO Value Object con los parámetros que necesita el método para crear y guardar el informe
	 * @throws Exception
	 */
	public void handleCrearGuardarInforme(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO) throws Exception
	{

		//El idioma en el que se mostrarán las etiquetas del informe será el idioma del que ha planificad
		//la tarea de ejecución del informe
		IRunAndRenderTask task = null;
		//String MIMETYPE_OCTET_STREAM = "application/octet-stream";
		String fechaDesde = "";
		String fechaHasta = "";
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String informesDir = "";
		String rangoFechas = "";
		
		
//		Validamos el VO con los parámetros del informe
		if(!(this.validarParametrosInforme(parametroCrearInformeVO).booleanValue()))
		{
			logger.warn("El vo con los parametros no es correcto");
			throw new Exception("parametrosCrearInformeInforme es incorrecto o no se ha seleccionado ningun informe");
		}
	
		String informe = parametroCrearInformeVO.getNombreInforme();
		
		//Calculamos las fechas:
		try
		{
			informesDir = this.getAgregaPropertyValue("destinoInformesDir");
			fechaDesde = this.obtenerFecha(parametroCrearInformeVO, "desde");
			fechaHasta = this.obtenerFecha(parametroCrearInformeVO, "hasta");
		
			rangoFechas = this.obtenerRangoFechas(parametroCrearInformeVO);
			if(logger.isDebugEnabled())logger.debug("rangoFechas: <" + rangoFechas+"> fecha desde <" + fechaDesde+"> hasta <" + fechaHasta+">. Informe <" + informe+">. InformesDir <" + informesDir+">");
						
			String nombreFichero = getPlantillasInformes() + informe + ".rptdesign";
			task = this.levantarPlataforma(nombreFichero);
			String offSetDesde = this.getOffSetDate(parametroCrearInformeVO.getAnioDesde(), parametroCrearInformeVO.getMesDesde(), parametroCrearInformeVO.getDiaDesde());
			String offSetHasta = this.getOffSetDate(parametroCrearInformeVO.getAnioHasta(), parametroCrearInformeVO.getMesHasta(), parametroCrearInformeVO.getDiaHasta());
			task.setParameterValue("RP_fechaHasta", fechaHasta + "T23:59:59+0" + offSetHasta + ":00");
			task.setParameterValue("RP_fechaDesde", fechaDesde + "T00:00:00+0" + offSetDesde + ":00");
			task.setParameterValue("RP_rangoFechas", rangoFechas);
			String idioma = LdapUserDetailsUtils.getIdioma();
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			if (idioma == null)
			{
				if(logger.isDebugEnabled())logger.debug("Se recoge el idioma de la plataforma");
				idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}
			task.setParameterValue("RP_Idioma", idioma);
			if (informe.equalsIgnoreCase("terminosBusqueda") || informe.equalsIgnoreCase("masValorado") || informe.equalsIgnoreCase("masMostrado") || informe.equalsIgnoreCase("masPrevisualizado") || informe.equalsIgnoreCase("masVisualizado")
						|| informe.equalsIgnoreCase("masDescargado") || informe.equalsIgnoreCase("tamanio"))
			{
				if(logger.isDebugEnabled())logger.debug("El informe solicitado necesita fechas y rango");
				task.setParameterValue("RP_Rango", parametroCrearInformeVO.getRango());
				if(logger.isDebugEnabled())logger.debug("El informe contiene enlaces a la ficha");
				String urlNodo =  this.getAgregaPropertyValue("host");
				String urlFicha = this.getPropertyValue("urlFicha");
				task.setParameterValue("RP_UrlFicha", "http://"+urlNodo+LdapUserDetailsUtils.getSubdominio() + urlFicha);
			} else
			{
				if (informe.equalsIgnoreCase("odesUsuario"))
				{
					if(logger.isDebugEnabled())logger.debug("El informe seleccionado necesita fechas y usuario");
					task.setParameterValue("RP_Usuario", parametroCrearInformeVO.getUsuario());
				}

				}
					
			
			String formato = parametroCrearInformeVO.getFormato();
			if(logger.isDebugEnabled())logger.debug("formato -> " + formato);
			if (formato == null)
			{
				logger.warn("El formato introducido es null");
				throw new Exception("El formato del informe es null");
			}
			if (formato.equalsIgnoreCase("html"))
			{
				if(logger.isDebugEnabled())logger.debug("formato es html");
				Locale locale = new Locale(idioma);
				task.setLocale(locale);
				if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
				HTMLRenderOption options = null;
				
				//Comprobamos si ya existe un fichero con el mismo nombre, en caso de que exista le añado System.curren
				String nombreInforme = this.getAgregaPropertyValue(informe) + "-" + fechaDesde + ".html";

				if (estaNomInforme(nombreInforme, informesDir).booleanValue())
				{
					if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fechaDesde + "-" + System.currentTimeMillis() + ".html";
					
				} else
				{
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fechaDesde + ".html";
				
				}
								
				options = this.obtenerRenderOptions(nombreInforme);
				task.setRenderOption(options);
				
			} else
			{
				if (formato.equalsIgnoreCase("pdf"))
				{
					if(logger.isDebugEnabled())logger.debug("formato pdf");
					Locale locale = new Locale(idioma);
					task.setLocale(locale);
					if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
					PDFRenderOption options = new PDFRenderOption();
					options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
					String nombreInforme = this.getAgregaPropertyValue(informe) + "-" + fechaDesde + ".pdf";
					if (estaNomInforme(nombreInforme, informesDir).booleanValue())
					{
						if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
						nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fechaDesde + "-" + System.currentTimeMillis() + ".pdf";
						options.setOutputFileName(nombreInforme);
					} else
					{
						options.setOutputFileName(informesDir + this.getAgregaPropertyValue(informe) + "-" + fechaDesde + ".pdf");
					}
					options.setOutputStream(response);
					task.setRenderOption(options);
					

				} else
				{
					if (formato.equalsIgnoreCase("excel"))
					{
						if(logger.isDebugEnabled())logger.debug("formato excel");
						Locale locale = new Locale(idioma);
						task.setLocale(locale);
						if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
						IRenderOption options = new RenderOption();
						options.setOutputFormat("xls");
						String nombreInforme = informe + "-" + fechaDesde + ".xls";
						if (estaNomInforme(nombreInforme, informesDir).booleanValue())
						{
							if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
							nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fechaDesde + "-" + System.currentTimeMillis() + ".xls";
						} else
						{
							options.setOutputFileName(informesDir + nombreInforme);
						}
						options.setOutputStream(response);
						task.setRenderOption(options);
						
					} else
					{
						logger.error("[CrearGuardarInforme] El formato introducido no es correcto");
						throw new Exception("El formato del informe no es correcto");
					}
				}
			}

			task.run();
		//	DataHandler dataHandler = new DataHandler(new SourceDataSource(null, MIMETYPE_OCTET_STREAM, new StreamSource(new ByteArrayInputStream(response.toByteArray()))));
			task.close();
			//// engine.destroy();
			//// if(logger.isDebugEnabled())logger.debug("[CrearGuardarInforme] engine.destroy");
			//// Platform.shutdown();
			

		} catch (Exception e)
		{
			if(logger.isDebugEnabled())logger.debug("Error al crear el informe " + e);
			task.close();
			//// engine.destroy();
			//// Platform.shutdown();
			throw new Exception(e);
		}

	}
	
	
	
	/**
	 * Genera y guarda en un directorio un informe federado con los datos que se pasan como parámetro
	 * @param  parametroCrearInformeVO Value Object con los parámetros que necesita el método para crear y guardar el informe federado
	 * @throws Exception
	 */
	public void handleCrearGuardarInformeFederado(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO) throws Exception
	{

//		if(logger.isDebugEnabled())logger.debug("entramos en el metodo crearGuardarInformeFederado de auditoria");
		//El idioma en el que se mostrarán las etiquetas del informe será el idioma del que ha planificado la tarea de ejecución del informe
		IRunAndRenderTask task = null;
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String informesDir = "";
		String fecha = "";
		String informe = parametroCrearInformeVO.getNombreInforme();
		
		Calendar fechaCalendar = new GregorianCalendar();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		fecha = formatoFecha.format(fechaCalendar.getTime());
		
		
		if(logger.isDebugEnabled())logger.debug("la fecha publicacion del informe federado es: " + fecha);
		
		try
		{
			informesDir = this.getAgregaPropertyValue("destinoInformesFederadosDir");
			
			if(logger.isDebugEnabled())logger.debug("informe -> " + informe);
			if(logger.isDebugEnabled())logger.debug("informesDir -> " + informesDir);
			
			String nombreFichero = getPlantillasInformes() + informe + ".rptdesign";
			task = this.levantarPlataforma(nombreFichero);
			
			String rangoFechas=null;
			String urlImagenLogo=null;
			String rpFechaNivelAgregacion=null;
			String mes =null;
			//String idioma ="";
			String idioma = LdapUserDetailsUtils.getIdioma();
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			if (idioma == null || idioma.isEmpty())
			{
				if(logger.isDebugEnabled())logger.debug("Se recoge el idioma de la plataforma");
				idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}

			//Fecha Actual
			Calendar fechaAct = new GregorianCalendar();
			Date dateAct = fechaAct.getTime();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
			String fechaActual = sdf.format(dateAct);
			
			//Fecha de inicio del informe
//			if(logger.isDebugEnabled())logger.debug("Fecha de inicio del informe");
			if (parametroCrearInformeVO.getFechaNivelAgregacion()!=null){
				Date dateIni = parametroCrearInformeVO.getFechaNivelAgregacion().getTime();
				if(logger.isDebugEnabled())logger.debug("Fecha de inicio del informe <"+dateIni+">");
				String fechaInicio = sdf.format(dateIni);
				rangoFechas=fechaInicio+" - "+fechaActual;
				task.setParameterValue("RP_Idioma", idioma);
				
				//Calculamos la fecha de nivel de agregacion a partir de la cual se obtendrá la información de los odes existentes por cada nivel de agregación
				
				String offSetNivelAgregacion = this.getOffSetDate(Integer.toString(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.YEAR)), Integer.toString(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.MONTH)), "10");
//				if(logger.isDebugEnabled())logger.debug("offSetNivelAgregacion "+offSetNivelAgregacion);
				if (parametroCrearInformeVO.getFechaNivelAgregacion()!=null){
					mes = String.valueOf(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.MONTH)+1);
				}
				if (mes.length()==1){
					mes="0"+mes;
				}
//				if (new Integer(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.MONTH)+1)<10){
					rpFechaNivelAgregacion = (parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.YEAR))+"-"+mes+"-"+"10";
//				}else{
//					rpFechaNivelAgregacion = (new Integer(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.YEAR))).toString()+"-"+(new Integer(parametroCrearInformeVO.getFechaNivelAgregacion().get(Calendar.MONTH)+1)).toString()+"-"+"10";
//				}
				
//				if(logger.isDebugEnabled())logger.debug("rpFechaNivelAgregacion "+rpFechaNivelAgregacion);
				rpFechaNivelAgregacion = rpFechaNivelAgregacion+"T09:30:47-00:00";
				if(logger.isDebugEnabled())logger.debug("Asigno al parametro RP_fechaNivelAgregacion "+ rpFechaNivelAgregacion);
				task.setParameterValue("RP_fechaNivelAgregacion", rpFechaNivelAgregacion);
			}
			
			urlImagenLogo = ImagenesAgrega.urlImagenLogoAgrega();
			task.setParameterValue("RP_rangoFechas", rangoFechas);
			task.setParameterValue("RP_logoAgrega", urlImagenLogo);			
			
			String formato = parametroCrearInformeVO.getFormato();
			if(logger.isDebugEnabled())logger.debug("formato -> " + formato);
			if(logger.isDebugEnabled())logger.debug("ejecucion informe");
			if (formato == null)
			{
				logger.warn("El formato introducido es null");
				throw new Exception("El formato del informe es null");
			}
			Locale locale = new Locale(idioma);
			task.setLocale(locale);
			if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
			
			if (formato.equalsIgnoreCase("html"))
			{
				if(logger.isDebugEnabled())logger.debug("formato es html");
				HTMLRenderOption options = null;
				//Comprobamos si ya existe un fichero con el mismo nombre, en caso de que exista le añado System.curren
				String nombreInforme = this.getAgregaPropertyValue(informe) + "-" + fecha + ".html";

				if (estaNomInforme(nombreInforme, informesDir).booleanValue())
				{
					if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fecha + "-" + System.currentTimeMillis() + ".html";
					
				} else
				{
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fecha + ".html";
				
				}
								
				options = this.obtenerRenderOptions(nombreInforme);
				if(logger.isDebugEnabled())logger.debug("ejecuto el informe");
				task.setRenderOption(options);
				
			} else if (formato.equalsIgnoreCase("pdf")) {
				
				if(logger.isDebugEnabled())logger.debug("formato pdf");
				PDFRenderOption options = new PDFRenderOption();
				options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
				String nombreInforme = this.getAgregaPropertyValue(informe) + "-" + fecha + ".pdf";
				if (estaNomInforme(nombreInforme, informesDir).booleanValue())
				{
					if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fecha + "-" + System.currentTimeMillis() + ".pdf";
					options.setOutputFileName(nombreInforme);
				} else
				{
					options.setOutputFileName(informesDir + this.getAgregaPropertyValue(informe) + "-" + fecha + ".pdf");
				}
				options.setOutputStream(response);
				task.setRenderOption(options);
					

			} else if (formato.equalsIgnoreCase("excel")) {
				
				if(logger.isDebugEnabled())logger.debug("formato excel");
				IRenderOption options = new RenderOption();
				options.setOutputFormat("xls");
				String nombreInforme = this.getAgregaPropertyValue(informe) + "-" + fecha + ".xls";
				if (estaNomInforme(nombreInforme, informesDir).booleanValue())
				{
					if(logger.isDebugEnabled())logger.debug("Ya existe un fichero con el mismo nombre añado el timestamp");
					nombreInforme = informesDir + this.getAgregaPropertyValue(informe) + "-" + fecha + "-" + System.currentTimeMillis() + ".xls";
					options.setOutputFileName(nombreInforme);
				} else
				{
					options.setOutputFileName(informesDir + nombreInforme);
				}
				options.setOutputStream(response);
				task.setRenderOption(options);
					
			} else {
				logger.warn("El formato introducido no es correcto");
				throw new Exception("El formato del informe no es correcto");
			}
			
			task.run();
			task.close();
			
		} catch (Exception e) {
			logger.error("Error al crear el informe - ", e);
			task.close();
			throw new Exception(e);
		}
	}
	
			
	/**
	 * Genera y guarda en un directorio los informes 'Mas' generados para la portada. Se  generarán los siguientes informes
	 * 'Mas':
	 *  Uno cuya fecha_inicio y fecha_fin será el día anterior a la ejecución del informe
	 *  Otro cuya fecha_inicio = ayer - x días donde x aparecerá en el fichero agrega.properties 
	 * @throws Exception
	 */
	public void handleCrearInformesPortada() throws Exception
	{

		//El idioma en el que se mostrarán las etiquetas del informe será el idioma del nodo
	
		IRunAndRenderTask task = null;
		HTMLRenderOption options = null;
		String fDesde = "";
		String fHasta = "";
		//ByteArrayOutputStream response = new ByteArrayOutputStream();
		String[] informesMas = null;
		String informe = "";
		Calendar fechaDesde = null;
		Calendar fechaHasta = null;
		String diaDesde = "";
		String mesDesde = "";
		String diaHasta = "";
		String mesHasta = "";
		String idioma = ""; //Idioma en el que se crearan los informes
		String descInformes = ""; //Informes de tipo 'Mas'
		String rangoFechas = ""; //parámetro de los informes
		String nombreFichero = ""; //Nombre del fichero 'Mas'
		String offSetDesde = "";
		String offSetHasta = "";
		//Atributos que se recogerán de ficheros properties
		String informesMasDir = "";
		String urlNodo =  "";
		String urlFicha = "";
		String rango = "";
		String diasAnterioresInformesPortada = "";
		String nombreInformesPortada1 = "";
		String nombreInformesMas = "";
		Locale locale = null;
		
		
		//Se cogerá del fichero auditoria.properties los informes de tipo Mas que se van a generar
		//Calculamos las fechas:
		try
		{
			//Cogemos los atributos de los ficheros properties
			informesMasDir = this.getAgregaPropertyValue("destinoInformesMasDir"); //Aquí se guardarán todos los informes mas
			urlNodo =  this.getAgregaPropertyValue("host");
			urlFicha = this.getPropertyValue("urlFicha");
			rango = this.getAgregaPropertyValue("rangoInformesPortada");
			nombreInformesPortada1 = this.getAgregaPropertyValue("nombreInformesPortadaSemanales");
		//	nombreInformesPortada2 = this.getAgregaPropertyValue("dias");
			diasAnterioresInformesPortada = this.getAgregaPropertyValue("diasAnterioresInformesPortada");
			if(logger.isDebugEnabled())logger.debug("informesDir " + informesMasDir);
			
			idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			if (idioma == null)
			{
				if(logger.isDebugEnabled())logger.debug("Se coge por defecto es");
				idioma = "es";
			}
					
			descInformes = this.getPropertyValue("informesMas");
			informesMas = descInformes.split(",");
				
			
			for(int i=0;i<informesMas.length;i++)
			{
				//Para cada informe 'Mas' calcularemos el informe del dia anterior y el de hace x días, siendo x una constante
				//que aparecerá en el agrega.properties para que lo pueda cambiar el administrador del nodo
				informe = informesMas[i];
				nombreFichero = getPlantillasInformes() + informe + ".rptdesign";
				//Saco según el tipo de informe los parámetros que le voy a asignar
//				Preparamos la plataforma
				task = this.levantarPlataforma(nombreFichero);
				task.setParameterValue("RP_Idioma", idioma);
//				if(logger.isDebugEnabled())logger.debug("Se añade el rango");
				task.setParameterValue("RP_Rango",Integer.parseInt(rango));
//				if(logger.isDebugEnabled())logger.debug("Sacamos los informes 'Mas' que aparecerán en la portada");
//				Para los informas 'Mas' vamos a añadir el parámetro con el nodo y la url de la ficha para crear los enlaces
//				if(logger.isDebugEnabled())logger.debug("El informe contiene enlaces a la ficha");
				task.setParameterValue("RP_UrlFicha", "http://"+urlNodo+LdapUserDetailsUtils.getSubdominio()+urlFicha);
				//Obtenemos el informe
				if(logger.isDebugEnabled())logger.debug("formato es html");
				locale = new Locale(idioma);
				task.setLocale(locale);
				if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
				
				//Obtengo el HTMLRenderOption
				nombreInformesMas = informesMasDir + this.getAgregaPropertyValue(informe) + ".html";
				//nombreInformesMas = informe;
				if(logger.isDebugEnabled())logger.debug("nombreInformesMas diario "+nombreInformesMas);
				//options = this.obtenerRenderOptions(nombreInformesMas);
				//task.setRenderOption(options);
				
				//Calculamos los informes del día anterior	
				if(logger.isDebugEnabled())logger.debug("Se calculan los informes del dia anterior");
				fechaDesde = Calendar.getInstance();
				fechaDesde.add(Calendar.DATE, -1);
				if(logger.isDebugEnabled())logger.debug("fechaDesde "+fechaDesde);
				diaDesde = Integer.toString(fechaDesde.get(Calendar.DAY_OF_MONTH));
				if(diaDesde.length() == 1)
				{
						diaDesde = "0" + diaDesde;
				}
				mesDesde = Integer.toString((fechaDesde.get(Calendar.MONTH))+1 );
				if(mesDesde.length() == 1)
				{
						mesDesde = "0" + mesDesde;
				}
				fDesde = fechaDesde.get(Calendar.YEAR)+"-"+ mesDesde +"-"+ diaDesde;
				fechaHasta = Calendar.getInstance();
				fechaHasta.add(Calendar.DATE, -1);
				if(logger.isDebugEnabled())logger.debug("fechaDesde "+fechaHasta);
				diaHasta = Integer.toString(fechaHasta.get(Calendar.DAY_OF_MONTH));
				if(diaHasta.length() == 1)
				{
						diaHasta = "0" + diaHasta;
				}
				mesHasta = Integer.toString((fechaHasta.get(Calendar.MONTH))+1);
				if(mesHasta.length() == 1)
				{
						mesHasta = "0" + mesHasta;
				}
				fHasta = fechaHasta.get(Calendar.YEAR)+"-"+ mesHasta +"-"+ diaHasta;
				if(logger.isDebugEnabled())logger.debug("fDesde "+fDesde);
				if(logger.isDebugEnabled())logger.debug("fHasta "+fHasta);
				rangoFechas = fHasta+"-"+fDesde;
				if(logger.isDebugEnabled())logger.debug("rangoFechas ->" + rangoFechas);
				offSetDesde = this.getOffSetDate(Integer.toString(fechaDesde.get(Calendar.YEAR)), mesDesde, diaDesde);
				offSetHasta = this.getOffSetDate(Integer.toString(fechaHasta.get(Calendar.YEAR)), mesHasta, diaHasta);
				task.setParameterValue("RP_fechaHasta", fHasta + "T23:59:59+0" + offSetHasta + ":00");
				task.setParameterValue("RP_fechaDesde", fDesde + "T00:00:00+0" + offSetDesde + ":00");
				task.setParameterValue("RP_rangoFechas", rangoFechas);
				
				options = this.obtenerRenderOptions(nombreInformesMas);
					
				
				task.setRenderOption(options);
				
				task.run();
				
				//Calculamos los informes diasAnterioresInformesPortada
				
				if(logger.isDebugEnabled())logger.debug("Calculamos los informes de hace "+diasAnterioresInformesPortada);
				fechaDesde = Calendar.getInstance();
				fechaDesde.add(Calendar.DATE, - Integer.parseInt(diasAnterioresInformesPortada));
				if(logger.isDebugEnabled())logger.debug("fechaDesde "+fechaDesde);
				diaDesde = Integer.toString(fechaDesde.get(Calendar.DAY_OF_MONTH));
				if(diaDesde.length() == 1)
				{
						diaDesde = "0" + diaDesde;
				}
				mesDesde = Integer.toString((fechaDesde.get(Calendar.MONTH))+1 );
				if(mesDesde.length() == 1)
				{
						mesDesde = "0" + mesDesde;
				}
				fDesde = fechaDesde.get(Calendar.YEAR)+"-"+ mesDesde +"-"+ diaDesde;
				fechaHasta = Calendar.getInstance();
				fechaHasta.add(Calendar.DATE, -1);
				if(logger.isDebugEnabled())logger.debug("fechaDesde "+fechaHasta);
				diaHasta = Integer.toString(fechaHasta.get(Calendar.DAY_OF_MONTH));
				if(diaHasta.length() == 1)
				{
						diaHasta = "0" + diaHasta;
				}
				mesHasta = Integer.toString((fechaHasta.get(Calendar.MONTH))+1);
				if(mesHasta.length() == 1)
				{
						mesHasta = "0" + mesHasta;
				}
				fHasta = fechaHasta.get(Calendar.YEAR)+"-"+ mesHasta +"-"+ diaHasta;
				if(logger.isDebugEnabled())logger.debug("fecha desde <"+fDesde+"> hasta <"+fHasta+">");
				rangoFechas = fHasta+"-"+fDesde;
				if(logger.isDebugEnabled())logger.debug("rangoFechas ->" + rangoFechas);
				offSetDesde = this.getOffSetDate(Integer.toString(fechaDesde.get(Calendar.YEAR)), mesDesde, diaDesde);
				offSetHasta = this.getOffSetDate(Integer.toString(fechaHasta.get(Calendar.YEAR)), mesHasta, diaHasta);
				task.setParameterValue("RP_fechaHasta", fHasta + "T23:59:59+0" + offSetHasta + ":00");
				task.setParameterValue("RP_fechaDesde", fDesde + "T00:00:00+0" + offSetDesde + ":00");
				task.setParameterValue("RP_rangoFechas", rangoFechas);
				
				//Obtenemos el HTMLRenderOptions
				nombreInformesMas =  informesMasDir + this.getAgregaPropertyValue(informe) +nombreInformesPortada1+".html";
				if(logger.isDebugEnabled())logger.debug("nombreInformesMas semanal "+nombreInformesMas);
				options = this.obtenerRenderOptions(nombreInformesMas);
					
				
				task.setRenderOption(options);
				
				task.run();
				
				task.close();
				
				
			}
			//// engine.destroy();
			//// if(logger.isDebugEnabled())logger.debug("[CrearGuardarInforme] engine.destroy");
			//// Platform.shutdown();
			

		} catch (Exception e)
		{
			logger.error("Error al crear el informe -", e);
			task.close();
			//// engine.destroy();
			//// Platform.shutdown();
			throw new Exception(e);
		}

	}

	/**
	 * Genera y guarda en un directorio el informe con todos los contenidos del repositorio
	 * @param idioma Idioma en el que se crearan los informes
	 * @param fechaActualizacion representa la fecha a partir de la cual lo ODEs se marcan como si fueran nuevos en el informe
	 * @throws Exception
	 */
	public void handleCrearInformeRepositorio(String idioma, Calendar fechaActualizacion) throws Exception
	{
//		logger.info("Crear informe Repositorio");
		//El idioma en el que se mostrarán las etiquetas del informe será el idioma del nodo
		IRunAndRenderTask task = null;
		PDFRenderOption options = null;
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String informe = "";
//		String idioma = ""; //Idioma en el que se crearan los informes
		//Atributos que se recogerán de ficheros properties
		String destinoInformeCatalogo = "";
		String nombreInforme = "";
		Locale locale = null;
		String fechaAct = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (fechaActualizacion != null) {
			fechaAct = sdf.format(fechaActualizacion.getTime());
		} else{
			fechaAct="";
		}
		
		
		//Se cogerá del fichero auditoria.properties los informes de tipo Mas que se van a generar
		//Calculamos las fechas:
		try
		{
		
			//El idioma será por defecto el castellano
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			informe = getPlantillasInformes() + "repositorio.rptdesign";
			//El destino del informe del catálogo se especificará en el agrega.properties
			destinoInformeCatalogo = this.getAgregaPropertyValue("destinoInformesDir");
			task = this.levantarPlataforma(informe);
			locale = new Locale(idioma);
			task.setLocale(locale);
			if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
			String urlNodo =  this.getAgregaPropertyValue("host");
			String urlFicha = this.getPropertyValue("urlFicha");
			task.setParameterValue("RP_UrlFicha", "http://"+urlNodo+LdapUserDetailsUtils.getSubdominio()+urlFicha);
			task.setParameterValue("RP_Idioma", idioma);
			task.setParameterValue("RP_FechaActualizacion", fechaAct);
			options = new PDFRenderOption();
			options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_PDF);
			nombreInforme = destinoInformeCatalogo+ this.getAgregaPropertyValue("informeCatalogo")+"_"+idioma+".pdf";
			options.setOutputFileName(nombreInforme);
			options.setOutputStream(response);
			task.setRenderOption(options);
			task.run();
//			if(logger.isDebugEnabled())logger.debug("#################");
			if(logger.isDebugEnabled())logger.debug("Informe de Catálogo creado");
		
			//// engine.destroy();
			//// if(logger.isDebugEnabled())logger.debug("[CrearGuardarInforme] engine.destroy");
			//// Platform.shutdown();
			

		} catch (Exception e)
		{
			logger.error("Error al crear el informe ", e);
			task.close();
			//// engine.destroy();
			//// Platform.shutdown();
			throw new Exception(e);
		}

	}
	
	
	/**
	 * Obtiene un array de InformeRepositorioVO con todos los odes del repositorio
	 * @return InformeRepositorioVO[] Array de Value Objects con la información del repositorio
	 * @param  nivelAgregacion Integer con el nivel de Agregación
	 * @throws Exception
	 */
	protected es.pode.auditoria.negocio.servicios.RepositorioVO[] handleRepositorioNivelAgregacion(Integer nivelAgregacion) throws java.lang.Exception
	{
		RepositorioVO[] resultado = null;
//		logger.info("RepositorioNivelAgregacion");
		try
		{
			nivelAgregacion.intValue();
		}catch(Exception e)
		{
			logger.error("El nivel agregacion es erroneo",e);
			nivelAgregacion = 1;
		}
		if((nivelAgregacion == null)||(nivelAgregacion == 0))
		{
			nivelAgregacion = 1;
		}
		ResultadoRepositorioVO[] resultadoRepositorio = this.getSrvBuscadorService().obtenerCatalogoRepositorioParam(nivelAgregacion);
		if(logger.isDebugEnabled())logger.debug("El contenido del repositorio es "+resultadoRepositorio.length);
		resultado = new RepositorioVO[resultadoRepositorio.length];
//		if(logger.isDebugEnabled())logger.debug("Mapeo cada uno de los VO del buscador");
		for (int i = 0; i < resultadoRepositorio.length; i++)
		{
			resultado[i] = new RepositorioVO();
			resultado[i].setDescripcion(resultadoRepositorio[i].getDescripcion());
			resultado[i].setEdad(resultadoRepositorio[i].getEdad());
			resultado[i].setTitulo(resultadoRepositorio[i].getTitulo());
			resultado[i].setIdiomaTexto(resultadoRepositorio[i].getIdiomaTexto());
			resultado[i].setNivelAgregacion(resultadoRepositorio[i].getNivelAgregacion());
			resultado[i].setIdioma(resultadoRepositorio[i].getIdioma());
			resultado[i].setIdentificador(resultadoRepositorio[i].getIdentificador());
			resultado[i].setFechaPublicacion(resultadoRepositorio[i].getFechaPublicacion());
			String[] objetivos = resultadoRepositorio[i].getObjetivos();
			String objetivo = "";
			for(int j=0;j<objetivos.length;j++)
			{
				objetivo = objetivo + objetivos[j] + " ";
			}
			resultado[i].setObjetivos(objetivo);
			
			
		}
		
		return resultado;
	}
	
	
	/**
	 * Genera y guarda en un directorio el informe con todos los contenidos del repositorio
	 * @param nivelAgregacion Integer con el nivel de Agregación
	 * @throws Exception
	 */
	public void handleCrearInformeOdesCargados(Integer nivelAgregacion) throws Exception
	{
//		logger.info("Crear informe Odes Cargados");
		//El idioma en el que se mostrarán las etiquetas del informe será el idioma del nodo
		IRunAndRenderTask task = null;		
		IRenderOption options = null;
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String informe = "";
		String idioma = ""; //Idioma en el que se crearan los informes
		//Atributos que se recogerán de ficheros properties
		String destinoInformeCatalogo = "";
		String nombreInforme = "";
		Locale locale = null;
		
		try
		{
			nivelAgregacion.intValue();
		}catch(Exception e)
		{
			logger.error("El nivel agregacion es erroneo",e);
			nivelAgregacion = 1;
		}
		if((nivelAgregacion == null)||(nivelAgregacion ==0))
		{
			nivelAgregacion = 1;
		}
		
		//Se cogerá del fichero auditoria.properties los informes de tipo Mas que se van a generar
		//Calculamos las fechas:
		try
		{
				
			//El idioma será por defecto el castellano
			idioma = "es";
			if(logger.isDebugEnabled())logger.debug("idioma -> " + idioma);
			informe = getPlantillasInformes() + "odesCargados.rptdesign";
			//El destino del informe del catálogo se especificará en el agrega.properties
			destinoInformeCatalogo = this.getAgregaPropertyValue("destinoInformesDir");
			//Saco según el tipo de informe los parámetros que le voy a asignar
			//Preparamos la plataforma
			task = this.levantarPlataforma(informe);
			locale = new Locale(idioma);
			task.setLocale(locale);
			if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
			task.setParameterValue("RP_NivelAgregacion", nivelAgregacion);
			nombreInforme = destinoInformeCatalogo+ "OdesCargados.xls";
			if(logger.isDebugEnabled())logger.debug("formato excel");
			options = new RenderOption();
			options.setOutputFormat("xls");
			options.setOutputFileName(nombreInforme);
			options.setOutputStream(response);
			task.setRenderOption(options);
			task.run();
		
			//// engine.destroy();
			//// if(logger.isDebugEnabled())logger.debug("[CrearGuardarInforme] engine.destroy");
			//// Platform.shutdown();
			

		} catch (Exception e)
		{
			logger.error("Error al crear el informe ", e);
			task.close();
			//// engine.destroy();
			//// Platform.shutdown();
			throw new Exception(e);
		}

	}
	
	
	/**
	 * Obtiene un array con todas las operaciones de la plataforma que son necesarias auditar 
	 * @return String[] 
	 * @throws Exception
	 */

	private String[] obtenerOperaciones() throws java.lang.Exception
	{
		String[] operaciones = null;
		String descOperaciones = this.getPropertyValue("operaciones");
		logger.info("descOperaciones vale <" + descOperaciones+">");
		operaciones = descOperaciones.split(",");

		return operaciones;

	}

	/**
	 * Obtiene el valor del offset para calcular la fecha en formato Calendar a partir de un año, un mes y un día 
	 * @param  anio 
	 * @param  mes
	 * @param  dia
	 * @return String
	 */

	private String getOffSetDate(String anio, String mes, String dia)
	{
		String offSet = "";
		TimeZone tz = TimeZone.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(tz);
		calendar.set(Calendar.YEAR, Integer.parseInt(anio));
		calendar.set(Calendar.MONTH, Integer.parseInt(mes));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
		int zOffset = calendar.get(Calendar.ZONE_OFFSET) / 3600000;
		int dOffset = calendar.get(Calendar.DST_OFFSET) / 3600000;
		offSet = Integer.toString(zOffset + dOffset);
		return offSet;
	}

	/**
	 * Asigna un valor al objeto jmsTemplate para acceder a la cola jms 
	 * @param  jmsTemplate 
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate)
	{

		this.jmsTemplate = jmsTemplate;
	}
	/**
	 * Obtiene una instancia del objeto jmsTemplate para acceder a la cola jms 
	 * @return  jmsTemplate 
	 */
	public JmsTemplate getJmsTemplate()
	{

		return this.jmsTemplate;
	}


	/**
	 * Obtiene el valor almacenado en el fichero properties auditoria de una determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws IOException
	 */
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/auditoria.properties");
		if (this.pAuditoriaProperties == null)
		{
			pAuditoriaProperties = new java.util.Properties();
			pAuditoriaProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pAuditoriaProperties.getProperty(sKey);
	}

	/**
	 * Obtiene el valor almacenado en el fichero properties agrega de determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws IOException
	 */
	private String getAgregaPropertyValue(String sKey) throws IOException
	{
		AgregaProperties properties = AgregaPropertiesImpl.getInstance();
		// devolvemos la propiedad
		return properties.getProperty(sKey);
	}

	/**
	 * Almacena en la cola jms un Value Object con la operación realizada 
	 * @param  operacion Value Objet con los datos de la operación que se va a encolar
	 */
	private void enviarMensajeOperacion(
			final es.pode.auditoria.negocio.servicios.OperacionVO operacion) {
		// if(logger.isDebugEnabled())logger.debug("enviarMensajeOperacion");
		this.jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return new SimpleMessageConverter().toMessage(operacion,
						session);
			}
		});
		if(logger.isDebugEnabled())logger.debug("Se ha creado un mensaje con el objeto operacion");
	}

	/**
	 * Almacena en la cola jms un Value Object con la búsqueda realizada 
	 * @param  busqueda Value Objet con los datos de la búsqueda que se va a encolar
	 */
	private void enviarMensajeBusqueda(final es.pode.auditoria.negocio.servicios.BusquedaVO busqueda)
	{
//		if(logger.isDebugEnabled())logger.debug("enviarMensajeBusqueda");
		this.jmsTemplate.send(new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				return new SimpleMessageConverter().toMessage(busqueda, session);
			}
		});

	}

	/**
	 * Obtiene el fichero donde se encuentran los textos internacionalizados 
	 * @return ResourceBundle
	 * @param  locale 
	 */
	private ResourceBundle getFicherRecursos(Locale locale)
	{

		ResourceBundle ficheroRecursos = null;
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
	}

	/**
	 * Obtiene el array con los términos buscados según el número de veces que han sido buscados 
	 * @param  array Array con los Value Object de las búsquedas que serán ordenadas
	 * @return InformeTerminoBusquedaVO[] Array con los Value Object ordenados por número de búsquedas
	 */
	private InformeTerminoBusquedaVO[] ordenaDescendente(InformeTerminoBusquedaVO[] array)
	{
		InformeTerminoBusquedaVO tmp;
		int i, j, pos_max;
		int N = array.length;
		for (i = 0; i < N - 1; i++)
		{
			//Mayor elemento del vector
			pos_max = i;
			for (j = i + 1; j < N; j++)
			{
				if (array[j].getNumVeces() > array[pos_max].getNumVeces())
					pos_max = j;
			}
			//coloca el maximo en la posicion i
			tmp = array[i];
			array[i] = array[pos_max];
			array[pos_max] = tmp;
		}
		return array;
	}

	/**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return InformeMasVO[] Array con los Value Object ordenados
	 */
	private InformeMasVO[] ordenaDescendente(InformeMasVO[] array)
	{
		if(logger.isDebugEnabled())logger.debug("OrdenaDescendente");
		InformeMasVO tmp;
		int i, j, pos_max;
		int N = array.length;
		for (i = 0; i < N - 1; i++)
		{
			//Mayor elemento del vector
			pos_max = i;
			for (j = i + 1; j < N; j++)
			{
			
			
				if (array[j].getNumVeces() > array[pos_max].getNumVeces())
					pos_max = j;
				
			}
			//coloca el maximo en la posicion i
			tmp = array[i];
			array[i] = array[pos_max];
			array[pos_max] = tmp;
		}
		return array;
	}
	
	/**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return InformeMasVO[] Array con los Value Object ordenados
	 */
	private InformeMasVO[] ordenaDescendente(InformeMasVO[] array, int numResultados)
	{
		if(logger.isDebugEnabled())logger.debug("OrdenaDescendente.Inicio Num Resultados :" + numResultados);
		
		SortedMap<String, InformeMasVO> mTerminos = new TreeMap<String, InformeMasVO>(java.util.Collections.reverseOrder());

		for (int i = 0; i < array.length; i++) {			
			mTerminos.put(rellenarCeros(array[i].getNumVeces()+"")+array[i].getIdOde(), array[i]);
		}
		
		if(logger.isDebugEnabled())logger.debug("NumElementos totales : " + mTerminos.size());
		
		Iterator<String> iterator = mTerminos.keySet().iterator();
		int cont =0;
		
		
		// 17012012 
		// Se corrige incidencia en la ordenación que provocaba fallo si no había al menos 20 elementos
		// Se devuelve un array de nElementos donde nElementos es el menor del máximo de la página y el número de objetos que hay 
		int nElementos = numResultados;
		if (mTerminos.size()<nElementos)
			nElementos=mTerminos.size();
		
		InformeMasVO[] aResult = new InformeMasVO[nElementos];
		
		if(logger.isDebugEnabled())logger.debug("Obtenemos los : " +numResultados + " máximos");
		while (iterator.hasNext() && cont < numResultados) {
			
			Object key = iterator.next();
			
			aResult[cont]= mTerminos.get(key);
		
			cont ++;
		}
		if(logger.isDebugEnabled())logger.debug("OrdenaDescendente.Final");		
		return aResult;
	}	

	/**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return InformeMasVO[] Array con los Value Object ordenados
	 */
	private String rellenarCeros(String veces)
	{	
		String salida="";
		
		for (int i = 0; i < (6 - veces.length()); i++) {
			salida=salida.concat("0");
		}

		salida=salida.concat(veces);
		
		return salida;
	}
	/**
	 * Obtiene el array con los títulos de los odes a partir de un array de identificadores 
	 * @param  detallePublicadoODEVO Array con los Value Object de las búsquedas que serán ordenadas
	 * @return String[] Array con los títulos de los odes
	 */
	private String[] getTitulosOde(DetallePublicadoODEVO[] detallePublicadoODEVO)
	{
		String[] resultado = null;
		resultado = new String[detallePublicadoODEVO.length];

		for (int i = 0; i < detallePublicadoODEVO.length; i++)
		{
			
			resultado[i] = detallePublicadoODEVO[i].getTitulo();
		}
		return resultado;
	}

	/**
	 * Obtiene un array de ArbolCurricularVO con la información del árbol curricular: el identificador y el nombre del nodo 
	 * @param  idioma del árbol curricular
	 * @return ArbolCurricularVO[] 
	 * @throws Exception
	 */
	private es.pode.auditoria.negocio.servicios.ArbolCurricularVO[] obtenerArbolCurricular(java.lang.String idioma) throws java.lang.Exception
	{
		SrvTaxonomiaService srvTaxonomiaService = this.getSrvTaxonomiaService();
		if(logger.isDebugEnabled())logger.debug("Nombre taxonomia " + this.getPropertyValue("nombreTaxonomia"));
		TaxonConRutaVO[] taxonConRutaVO = srvTaxonomiaService.obtenerTaxonomiaCompletaPreorden(this.getPropertyValue("nombreTaxonomia"), idioma);
		if(logger.isDebugEnabled())logger.debug("taxonConRutaVO vale " + taxonConRutaVO.length);
		ArbolCurricularVO[] arbolCurricularVO = new ArbolCurricularVO[taxonConRutaVO.length - 1];
		if(logger.isDebugEnabled())logger.debug("tamanio arbolCurricularVO <" + arbolCurricularVO.length+">");
		if (!(taxonConRutaVO == null))
		{

			for (int i = 0; i < taxonConRutaVO.length - 1; i++)
			{
				arbolCurricularVO[i] = new ArbolCurricularVO();
				TaxonConRutaVO taxon = taxonConRutaVO[i + 1];
				arbolCurricularVO[i].setIdNodo(taxon.getId());
				arbolCurricularVO[i].setNombreNodo(taxon.getValorTax());

			}
		}
		if(logger.isDebugEnabled())logger.debug("devuelvo tamanio arbolCurricularVO <" + arbolCurricularVO.length+">");
		return arbolCurricularVO;
	}

	/**
	 * Obtiene un array de String  con los identificadores de los nodos del árbol curricular 
	 * @param  arbolCurricular ArbolCurricularVO[]
	 * @return String[] 
	 */
	private String[] obtenerIdArbolCurricular(ArbolCurricularVO[] arbolCurricular)
	{
//		if(logger.isDebugEnabled())logger.debug("obtenerIdArbolCurricular ");
		String[] idNodos = null;
		idNodos = new String[arbolCurricular.length];
		for (int i = 0; i < arbolCurricular.length; i++)
		{
			idNodos[i] = arbolCurricular[i].getIdNodo();
		}
		if(logger.isDebugEnabled())logger.debug("El tamanio del array de id nodos  es " + idNodos.length);
		return idNodos;
	}

	/**
	 * Obtiene un array de String  con los identificadores de las licencias disponibles en la plataforma 
	 * @param  idioma idioma de las licencias
	 * @return String[] 
	 * @throws Exception
	 */
	private java.lang.String[] obtenerLicencias(String idioma) throws java.lang.Exception
	{

		String[] identificador = new String[1];
		identificador[0] = this.getPropertyValue("licencia");
		VocabularioVO[] vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(identificador, idioma);
		TerminoVO[] termino = vocabulario[0].getTerminos();
		String[] resultado = new String[termino.length];
		for (int i = 0; i < termino.length; i++)
		{
			resultado[i] = termino[i].getNomTermino();
			if(logger.isDebugEnabled())logger.debug("resultado[i] " + resultado[i]);
		}
		return resultado;
	}

	/**
	 * Comprueba si existe ya un archivo en el directorio con el mismo nombre 
	 * @param  nombreInforme nombre del informe
	 * @param  dirInformes directorio donde se comprobará la existencia del fichero
	 * @return Boolean 
	 */
	private Boolean estaNomInforme(String nombreInforme, String dirInformes)
	{
		Boolean resultado = Boolean.FALSE;
		try
		{
			File dir = new File(dirInformes);
			if (dir.isDirectory())
			{
				if (!dir.exists())
				{
					logger.warn("El directorio no existe ");

				}
				//tomamos los ficheros contenidos en la URL dada
				String[] archivos = dir.list();
				//agregamos cada fichero en una lista
				for (int i = 0; i < archivos.length; i++)
				{
					if (archivos[i].equalsIgnoreCase(nombreInforme))
					{
						if(logger.isDebugEnabled())logger.debug("Ya existe otro fichero con el mismo nombre");
						resultado = Boolean.TRUE;
						break;
					}
				}

			}
		} catch (Exception e)
		{
			logger.error("Se produce una excepcion al buscar el nombre del fichero ", e);
		}
		if(logger.isDebugEnabled())logger.debug("El resultado enviado es " + resultado);
		return resultado;
	}
	/**
	 * Coge una etiqueta del fihcero I18n para la traduccion
	 * @param etiqueta que se quiere traducir
	 * @param idioma Idioma para la traducción
	 * @return etiqueta traducida
	 */
	
	private String getEtiqueta(String etiqueta,String idioma)
	{
		String resultado = null;
		resultado = I18n.getInstance().traduceEtiqueta(etiqueta, idioma);
		if(logger.isDebugEnabled())logger.debug("resultado "+resultado);
		return resultado;
	}
	
	
	/**
	 * Comprueba si el VO con los parámetros es correcto 
	 * @param  parametroCrearInformeVO VO con los parámetros
	 * @return Boolean con el resultado de la validacion
	 */
	private Boolean validarParametrosInforme(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO)
	{
		Boolean resultado = true;
		if (parametroCrearInformeVO == null)
		{
			logger.warn("No se han pasado parameros para crear el informe, parametros nulos");
			resultado = false;
		}else if (parametroCrearInformeVO.getAnioDesde() == null || parametroCrearInformeVO.getMesDesde() == null || parametroCrearInformeVO.getDiaDesde() == null || parametroCrearInformeVO.getAnioHasta() == null
				|| parametroCrearInformeVO.getMesHasta() == null || parametroCrearInformeVO.getDiaHasta() == null)
			{
				logger.warn("Error creando los informes alguna de las fechas es null");
				resultado = false; 
			}else 
			{
				String informe = parametroCrearInformeVO.getNombreInforme();
				if (informe == null)
				{
					logger.warn("Error al obtener el nombre del informe");
					resultado = false;
				}
			}
		
		return resultado;
	}
	
	/**
	 * Obtiene el rango de fechas para los que se calculará el informe 
	 * @param  parametroCrearInformeVO VO con los parámetros
	 * @return String con el rango de fechas
	 */
	private String obtenerRangoFechas(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO)
	{
		String resultado = "";
		resultado = parametroCrearInformeVO.getDiaDesde() + "/" + parametroCrearInformeVO.getMesDesde() + "/" + parametroCrearInformeVO.getAnioDesde() + " - " + parametroCrearInformeVO.getDiaHasta() + "/"
		+ parametroCrearInformeVO.getMesHasta() + "/" + parametroCrearInformeVO.getAnioHasta();
		return resultado;
	}
	
	/**
	 * Obtiene la fecha de inicio o de fin de generación del informe 
	 * @param  parametroCrearInformeVO VO con los parámetros
	 * @param  tipo tipo de fecha que se desea obtener: desde o hasta
	 * @return String con la fecha
	 */
	
	private String obtenerFecha(es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO parametroCrearInformeVO, String tipo)
	{
		String resultado = "";
		if(tipo.equalsIgnoreCase("desde"))
		{
			resultado = parametroCrearInformeVO.getAnioDesde() + "-" + parametroCrearInformeVO.getMesDesde() + "-" + parametroCrearInformeVO.getDiaDesde();
		}else
		{
			resultado = parametroCrearInformeVO.getAnioHasta() + "-" + parametroCrearInformeVO.getMesHasta() + "-" + parametroCrearInformeVO.getDiaHasta();
		}
		
		return resultado;
	}
	
	/**
	 * Levanta la plataforma de birt para la generación de informes 
	 * @param  informe informe que se quiere generar
	 * @return IRunAndRenderTask
	 * @throws Exception
	 */
	
	private IRunAndRenderTask levantarPlataforma(String informe)throws Exception
	{
		IRunAndRenderTask task = null;
		EngineConfig config = null;
		IReportEngine engine = null;
		IReportRunnable design = null;
		IReportEngineFactory factory = null;
		config = new EngineConfig();
		config.setBIRTHome(this.getAgregaPropertyValue("birtDir"));
//		if(logger.isDebugEnabled())logger.debug("config.getBIRTHome() " + config.getBIRTHome());
		config.setResourcePath(getPlantillasInformes());
//		if(logger.isDebugEnabled())logger.debug("config.getResourcePath() "+config.getResourcePath());
		Platform.startup(config);
		factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		engine = factory.createReportEngine(config);
		engine.changeLogLevel(Level.ALL);
		design = engine.openReportDesign(informe);
		task = engine.createRunAndRenderTask(design);
		return task;
	}
	
	/**
	 * Obtiene HTMLRenderOptions para la ejecución del informe 
	 * @param  nombreInforme nombre del fichero con el informe
	 * @return HTMLRenderOption
	 * @throws Exception
	 */
	
	private HTMLRenderOption obtenerRenderOptions(String nombreInforme) throws Exception
	{
		
		HTMLRenderOption options = null;
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String urlNodo = AgregaPropertiesImpl.getInstance().getUrlNodo();
		options = new HTMLRenderOption();
		options.setOutputFormat(IRenderOption.OUTPUT_FORMAT_HTML);
		options.setImageHandler(new HTMLServerImageHandler());
		options.setImageDirectory(this.getAgregaPropertyValue("imgBirtDir"));
		
//		if(logger.isDebugEnabled())logger.debug("options.getImageDirectory() " + options.getImageDirectory());
		options.setBaseImageURL(urlNodo + this.getAgregaPropertyValue("staticImgDir"));
//		if(logger.isDebugEnabled())logger.debug("options.getBaseImageURL() " + options.getBaseImageURL());
		//Comprobamos si ya existe un fichero con el mismo nombre, en caso de que exista le añado System.curren
		options.setOutputFileName(nombreInforme);
		options.setHtmlPagination(true);
		options.setOutputStream(response);
		return options;
	}
	
	/**
	 * Comprueba si la fechas son correctas 
	 * @param  nombreInforme nombre del fichero con el informe
	 * @return Boolean
	 */
	
	private Boolean validarFechas(Calendar fechaDesde, Calendar fechaHasta)
	{
		
		Boolean resultado = true;
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error calculando odes mas mostrados. Fechas desde [" + fechaDesde == null ? null : fechaDesde + "] y hasta [" + fechaHasta == null ? null : fechaHasta + "] vacias.");
			resultado = false;
			
		}else if(fechaDesde.after(fechaHasta))
		{
			logger.warn("Error calculando odes mas mostrados. Fechas desde [" + fechaDesde + "] y hasta [" + fechaHasta + "] incoherentes.");
			resultado = false;
			
		}
		return resultado;
	}
	
	/**
	 * Obtiene el array de VO con la información de los odes mas del tipo de operación que se pasa por parámetro
	 * @param	tipoOperacion tipo de operación 
	 * @param	parametrosInformeVO VO con los parámetros del informe
	 * @return Boolean
	 * @throws Exception
	 */
	
	private InformeMasVO[] obtenerMasVO(String tipoOperacion,es.pode.auditoria.negocio.servicios.ParametrosInformeVO parametrosInformeVO) throws Exception
	{
			//Metodo privado para la obtencion de los informes de tipo mas (previsualizado, descargado, mostrado...)
			String[] listaIdOdesMasMostrado = null;
			String[] titulosOde = null;
			InformeMasVO[] devuelto = null;
			String[] listaIdOde = null;
			Long numeroVecesMostrado = null;
			InformeMasVO[] arrayTemp = null;
			
			if (parametrosInformeVO == null)
			{
				logger.warn("Error calculando odes mas mostrados. No hay parametros.");
				throw new Exception("Error calculando odes mas mostrados. No hay parametros.");
			}
			Calendar fechaDesde = parametrosInformeVO.getFechaDesde();
			Calendar fechaHasta = parametrosInformeVO.getFechaHasta();
			int numMaxRes = parametrosInformeVO.getRango();
			IdiomaOdeDesdeHastaCriteria idiomaOdeDesdeHastaCriteria = null;
			if (numMaxRes == 0)
			{
				logger.warn("Error calculando odes mas mostrados. El numero de resultados a devolver es nulo o 0.");
				throw new Exception("Error calculando odes mas mostrados. El numero de resultados a devolver es nulo o 0.");
			}
			if(!(this.validarFechas(fechaDesde, fechaHasta)).booleanValue())
			{
				logger.warn("Las fechas no son correctas");
				throw new Exception("Error calculando odes mas mostrados. Fechas desde[" + fechaDesde == null ? null : fechaDesde + "] y hasta[" + fechaHasta == null ? null : fechaHasta + "] erroneas.");
			}
			
			//Obtengo la lista de ids y los idiomas
			
			
			listaIdOde = (String[]) this.getOperacionDao().obtenerIdOdesOperacionDesdeHasta(fechaDesde, fechaHasta, tipoOperacion).toArray(new String[0]);
			 
			
			if (listaIdOde == null || listaIdOde.length == 0)
			{
				if(logger.isDebugEnabled())logger.debug("Calculando odes para el informe de tipo mas. No existen odes mas dentro del periodo desde[" + fechaDesde + "]->hasta[" + fechaHasta + "]");
				return null;
			}else
			{
				if(logger.isDebugEnabled())logger.debug("Tamanio de la lista de odes "+listaIdOde.length);
			}
			
			
			arrayTemp = new InformeMasVO[listaIdOde.length];
			//Obtenemos el numero de veces que realizado una operacion un id_mec en concreto
			for (int i = 0; i < listaIdOde.length; i++)
			{
				try
				{
						arrayTemp[i] = new InformeMasVO();
						numeroVecesMostrado = this.getOperacionDao().obtenerNumIdOdesOperacion(fechaDesde, fechaHasta, listaIdOde[i], tipoOperacion);
						if(logger.isDebugEnabled())logger.debug("idOde <"+ listaIdOde[i]+ "> numeroVecesMostrado <"+numeroVecesMostrado+">");
						arrayTemp[i].setNumVeces(numeroVecesMostrado.intValue());
						arrayTemp[i].setIdOde(listaIdOde[i]);
				
				} catch (Exception e)
				{
					logger.error("Calculando odes mas mostrados se produce un ERROR obteniendo numero de veces que ha sido mostrado para el ode[" + listaIdOde[i] + "]. Continuamos el calculo. - ",e);
				}
			}
			// 23102012 Modificamos la forma de ordenar los resultados de búsqueda
			if(logger.isDebugEnabled())logger.debug("Se ordena el listado de forma descendente");
			devuelto = ordenaDescendente(arrayTemp,numMaxRes);
			if(logger.isDebugEnabled())logger.debug("Se ha ordenado el listado de forma descendente");
						
			//Obtengo los idOdes para sacar el titulo
			listaIdOdesMasMostrado = new String[devuelto.length];
			for(int l=0;l<devuelto.length;l++)
			{
				listaIdOdesMasMostrado[l] = devuelto[l].getIdOde();
			}
//			if(logger.isDebugEnabled())logger.debug("Se obtienen los titulos de los odes del publicador");
			titulosOde = this.getTitulosOde(this.getSrvAuditaPublicacionService().titulosODEsPorID(listaIdOdesMasMostrado));
			if(logger.isDebugEnabled())logger.debug("titulosOde "+titulosOde);
			//Obtengo el idioma y el titulo de cada uno de los identificadores que se van a devolver
			for(int j=0;j<devuelto.length;j++)
			{
				idiomaOdeDesdeHastaCriteria = new IdiomaOdeDesdeHastaCriteria();
				idiomaOdeDesdeHastaCriteria.setFechaDesde(fechaDesde);
				idiomaOdeDesdeHastaCriteria.setFechaHasta(fechaHasta);
				idiomaOdeDesdeHastaCriteria.setIdOde(devuelto[j].getIdOde());
				List list = this.getOperacionDao().obtenerIdiomaIdOde(idiomaOdeDesdeHastaCriteria);
				if(list == null)
				{
					if(logger.isDebugEnabled())logger.debug("Marco el idioma vacio");
					devuelto[j].setIdioma("");
				}else
				{
				if(list.size() == 1)
				{
					if(logger.isDebugEnabled())logger.debug("La lista tiene tamanio 1");
					Operacion operacionVO = (Operacion)list.iterator().next();
					if(logger.isDebugEnabled())logger.debug("operacionVO.getIdioma() "+operacionVO.getIdioma());
					devuelto[j].setIdioma(operacionVO.getIdioma());
				}else
				{
					if(logger.isDebugEnabled())logger.debug("tenemos mas de un elemento");
					Operacion[] operacionesArray = (Operacion[]) list.toArray(new Operacion[0]);
					if(operacionesArray[0].getIdioma() == null)
					{
						if(logger.isDebugEnabled())logger.debug("el idioma lo establezco vacio");
						devuelto[j].setIdioma("");
					}else
					{
						
						devuelto[j].setIdioma(operacionesArray[0].getIdioma());
					}
					
					
				}
				
				}
				devuelto[j].setTituloOde(titulosOde[j]);
			}
/////////////////////////////////////////////////////
			InputStream is = null;
	   	 	Properties prop = new Properties();
			is = this.getClass().getResourceAsStream("/auditoria.properties");
			prop.load(is);
			String idiomas=prop.getProperty("idiomas");
			String[] listIdiomas=idiomas.split(",");
			List listaCatalan=new ArrayList();
			List listaIngles=new ArrayList();
			List listaCastellano=new ArrayList();
			List listaEuskera=new ArrayList();
			List listaGallego=new ArrayList();
			List listaValenciano=new ArrayList();
			List listaSin=new ArrayList();
			for(int i=0;i<devuelto.length;i++){
				String idioma=devuelto[i].getIdioma();
				
				if(listIdiomas[0].equals(idioma)){//ca				
					listaCatalan.add(devuelto[i].getIdOde());
				}else if(listIdiomas[1].equals(idioma)){//en				
					listaIngles.add(devuelto[i].getIdOde());
				}else if(listIdiomas[2].equals(idioma)){//es				
					listaCastellano.add(devuelto[i].getIdOde());
				}else if(listIdiomas[3].equals(idioma)){//eu				
					listaEuskera.add(devuelto[i].getIdOde());
				}else if(listIdiomas[4].equals(idioma)){//gl				
					listaGallego.add(devuelto[i].getIdOde());
				}else if(listIdiomas[5].equals(idioma)){//va				
					listaValenciano.add(devuelto[i].getIdOde());
				}else{//Cuando no traiga idioma
					listaSin.add(devuelto[i].getIdOde());
				}
			}
			HashMap hash=new HashMap();
			if(listaCatalan!=null && listaCatalan. size()>0){
				String[] arrayCatalan=(String[])listaCatalan.toArray(new String[listaCatalan.size()]);
				String[] catalan = this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[0],arrayCatalan);
				for(int i=0;i<arrayCatalan.length;i++){
					String id=listaCatalan.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El catalan <"+i+"> con identificador: [ "+id+" ] y su url: < "+catalan[i]+">");
					hash.put(id, catalan[i]);
				}
			}
			if(listaIngles!=null && listaIngles.size()>0){
				String[] arrayIngles=(String[])listaIngles.toArray(new String[listaIngles.size()]);
				String[] ingles=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[1],arrayIngles);			
				for(int i=0;i<arrayIngles.length;i++){
					String id=listaIngles.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El ingles <"+i+"> con identificador: [ "+id+" ] y su url: <"+ingles[i]+">");
					hash.put(id, ingles[i]);
				}
			}
			if(listaCastellano!=null && listaCastellano.size()>0){
				String[] arrayCastellano=(String[])listaCastellano.toArray(new String[listaCastellano.size()]);
				String[] castellano=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[2],arrayCastellano);			
				for(int i=0;i<arrayCastellano.length;i++){
					String id=listaCastellano.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El castellano <"+i+"> con identificador: [ "+id+" ] y su url: <"+castellano[i]+">");
					hash.put(id, castellano[i]);
				}
			}
			if(listaEuskera!=null && listaEuskera.size()>0){
				String[] arrayEuskera=(String[])listaEuskera.toArray(new String[listaEuskera.size()]);
				String[] euskera=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[3],arrayEuskera);
				for(int i=0;i<arrayEuskera.length;i++){
					String id=listaEuskera.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El euskera <"+i+"> con identificador: [ "+id+" ] y su url: <"+euskera[i]+">");
					hash.put(id, euskera[i]);
				}
			}
			if(listaGallego!=null && listaGallego.size()>0){
				String[] arrayGallego=(String[])listaGallego.toArray(new String[listaGallego.size()]);
				String[] gallego=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[4],arrayGallego);
				for(int i=0;i<arrayGallego.length;i++){
					String id=listaGallego.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El gallego <"+i+"> con identificador: [ "+id+" ] y su url: <"+gallego[i]+">");
					hash.put(id, gallego[i]);
				}
			}
			if(listaValenciano!=null && listaValenciano.size()>0){
				String[] arrayValenciano=(String[])listaValenciano.toArray(new String[listaValenciano.size()]);
				String[] valenciano=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(listIdiomas[5],arrayValenciano);
				for(int i=0;i<arrayValenciano.length;i++){
					String id=listaValenciano.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("El valenciano <"+i+"> con identificador: [ "+id+" ] y su url: <"+valenciano[i]+">");
					hash.put(id, valenciano[i]);
				}
			}
			if(listaSin!=null && listaSin.size()>0){
				String[] arraySin=(String[])listaSin.toArray(new String[listaSin.size()]);
				String[] sin=this.getSrvAuditoriaIndexadorService().obtenerURLImagenPequena(null,arraySin);
				for(int i=0;i<arraySin.length;i++){
					String id=listaSin.get(i).toString();
					if(logger.isDebugEnabled())logger.debug("Sin idioma <"+i+"> con identificador: [ "+id+" ] y su url: <"+sin[i]+">");
					hash.put(id, sin[i]);
				}
			}
			String urlNodo =  this.getAgregaPropertyValue("host");
//			String urlNodo =DependentServerProperties.getInstance().getUrlNodo();
			String urlLocal="http://"+urlNodo+LdapUserDetailsUtils.getSubdominio();//O de DependServer?????
			for(int i=0;i<devuelto.length;i++){
				String identificador=(devuelto[i]).getIdOde();
				String foto=(String)(hash.get(identificador));
				String urlFoto=urlLocal+foto;
				devuelto[i].setUrlImagen(urlFoto);
				if(logger.isDebugEnabled())logger.debug("La url del identificador: [ "+foto+" ] es :["+urlFoto+"]");
			}		
			return devuelto;
		
	}
	/**
	 * Obtiene el valor almacenado en el fichero properties estadisticas de determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws IOException
	 */
	private String getEstadisticasPropertyValue(String sKey) throws IOException
	{
		EstadisticasProperties properties = EstadisticasPropertiesImpl.getInstance();
		// devolvemos la propiedad
		return properties.getProperty(sKey);
	}

	/**
     * Este metodo devuelve un array de NumeroOperacionesVO,cada uno de estos VOs tiene el identificador(la entrada del metodo), la operacion y
     * cuantas veces se ha realizado esa operacion para ese identificador. Como salida nos devuelve el array de todas las operaciones para ese identificador.
     * @param  identificador
	 * @return NumeroOperacionesVO[]
	 * @throws Exception
     * */
	

	protected NumeroOperacionesVO[] handleObtenNumeroOperaciones(String identificador) throws Exception {
		String operaciones = this.getEstadisticasPropertyValue("numero_operacion");
		String[] lOperaciones = operaciones.split(",");
		NumeroOperacionesVO[] numeroOperaciones=new NumeroOperacionesVO[lOperaciones.length];
//		logger.info("El identificador es: ["+identificador+"]");

		Long valor=null;
		for(int j=0;j< lOperaciones.length;j++){
			NumeroOperacionesVO numero=new NumeroOperacionesVO();
			String operacion=lOperaciones[j];
			valor = this.getOperacionDao().obtenerNumeroOperacion(identificador, operacion);
			if(logger.isDebugEnabled())logger.debug("La operacion requerida es: ["+operacion+"] y el valor que devuelve: ["+valor+"]");
			numero.setIdentificador(identificador);
			numero.setOperacion(operacion);
			numero.setNumeroOperaciones(valor.intValue());
			
			numeroOperaciones[j]=numero;
		}
		return numeroOperaciones;
	}

	/**
	 * Genera y guarda en un directorio un informe con los datos que se pasan como parámetro
	 * @param  parametroInformeGenerico Value Object con los parámetros que necesita el método para crear y guardar el informe
	 * @throws Exception
	 */
	protected void handleCrearAlmacenarInforme(ParametroInformeGenericoVO parametroInformeGenerico) throws Exception
	{
//		logger.info("Entrando en CrearAlmacenarInforme");
		
		//l a tarea de ejecución del informe
		IRunAndRenderTask task = null;
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		String informesDir = parametroInformeGenerico.getPathDestinoInforme();
		try
		{
			//Ponemos el path de destino del informe
			if(logger.isDebugEnabled())logger.debug("Path destino del informe " + informesDir);	
			StringBuffer nombreFichero= new StringBuffer();
			nombreFichero.append(getPlantillasInformes()).append(parametroInformeGenerico.getNombreInforme());
			if(logger.isDebugEnabled())logger.debug("El nombre del fichero es: " + nombreFichero);
			task = this.levantarPlataforma(nombreFichero.toString());
			if(logger.isDebugEnabled())logger.debug("Se ha levantado la plataforma");
			ParametroVO[] parametros = null;
			parametros=parametroInformeGenerico.getParametros();
			//Recorremos el array de VO en los que se almacena informacion de los parametros
			//que necesita este informe en concreto
			if(logger.isDebugEnabled())logger.debug("parametros "+parametros);
			for(int j=0;j<parametros.length;j++)
			{
				
				ParametroVO param = parametros[j];
				if(logger.isDebugEnabled())logger.debug("param.getNombreParametro() "+param.getNombreParametro());
				if(logger.isDebugEnabled())logger.debug("param.getValorAtributo() "+param.getValorAtributo());
				task.setParameterValue(param.getNombreParametro(),param.getValorAtributo());
			}
			//task.setParameterValue("RP_idTarea", parametros[0].getValorAtributo());
			if(logger.isDebugEnabled())logger.debug("Se coge el idioma de la plataforma");
			String idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			if (parametros.length ==0)
			{
				logger.warn("El informe solicitado necesita idTarea");
				throw new Exception("El informe necesita parametros para su ejecucion");
			} else
			{
				//Para añadir la fecha del sistema al nombre del fichero
				GregorianCalendar FechaActual = new GregorianCalendar();
				FechaActual.setTime( new java.util.Date() );
				String dia = Integer.toString(FechaActual.get(Calendar.DATE));
				String mes = Integer.toString(FechaActual.get(Calendar.MONTH)+1);
				String anho = Integer.toString(FechaActual.get(Calendar.YEAR));
				if (FechaActual.get(Calendar.DATE)<10){
					dia = "0"+FechaActual.get(Calendar.DATE);
				}
				if ( (FechaActual.get(Calendar.MONTH)+1)<10){
					mes = "0"+(FechaActual.get(Calendar.MONTH)+1); 
				}
				
				StringBuffer cadenaFecha = new StringBuffer();
				cadenaFecha.append(anho).append(mes).append(dia);
				
				if(logger.isDebugEnabled())logger.debug("formato del fichero excel");
				Locale locale = new Locale(idioma);
				task.setLocale(locale);
				if(logger.isDebugEnabled())logger.debug("El lenguage del locale "+task.getLocale().getLanguage());
				IRenderOption options = new RenderOption();
				options.setOutputFormat("xls");
				StringBuffer nombreInforme = new StringBuffer();
				nombreInforme.append(parametroInformeGenerico.getNombreFicheroInforme()).append("-").append(cadenaFecha).append(".xls");
				//En el caso de que exista un fichero con ese mismo nombre
				//añade System.currentTimeMillis();
				if (estaNomInforme(nombreInforme.toString(), informesDir).booleanValue())
				{
					logger.info("Ya existe un fichero con el mismo nombre añado el timestamp");
					StringBuffer nombreLargo =new StringBuffer();
					nombreLargo.append(parametroInformeGenerico.getNombreFicheroInforme()).append("-").append(cadenaFecha).append("-").append(System.currentTimeMillis()).append(".xls");
					nombreInforme=nombreLargo;
				} 
					options.setOutputFileName(informesDir + nombreInforme.toString());
				
				options.setOutputStream(response);
				task.setRenderOption(options);

					
					

			task.run();
			task.close();

			}
		} catch (Exception e)
		{
			logger.error("Error al crear el informe ", e);
			task.close();
			throw new Exception(e);
		}
		
	}

	/**
	 * Metodo que será llamado para obtener los datos pertenedcientes al informe
	 * @param  idTarea 
	 * @return cargaMasivaVO con los datos de la tarea
	 * @throws Exception
	 */
	protected InformeCargaMasivaVO handleInformeCargaMasiva(long idTarea) throws Exception {

//		logger.info("Obteniendo informes carga masiva con identificador "+ idTarea);
		InformeCargaVO informeCarga = new InformeCargaVO();
		InformeCargaMasivaVO informeCargaMasivaVO = new InformeCargaMasivaVO();
//		Obtenemos los datos de la tarea llamando a  auditaPlanificadorService.obtenerInformeCarga
		SrvAuditaPlanificadorService auditaPlanificadorService =this.getSrvAuditaPlanificadorService();
			
			try{
				
				informeCarga = auditaPlanificadorService.obtenerInformeCarga(idTarea);
				
			}catch (Exception e){
				logger.error("Error obteniendo los datos del informe - ",e);
			}
			try{
	//			Mapeo del VO padre
				if( informeCarga != null){
					informeCargaMasivaVO = (es.pode.auditoria.negocio.servicios.InformeCargaMasivaVO)this.getBeanMapper().map(informeCarga,
							es.pode.auditoria.negocio.servicios.InformeCargaMasivaVO.class, "DEF_MAPPING_INFORMECARGAVO_INFORMECARGAMASIVAVO");
				}
				//Rellenamos el VO para que al crear el informe no nos de error
				if(informeCargaMasivaVO.getRegistroCargaVO()==null || informeCargaMasivaVO.getRegistroCargaVO().length==0){
					RegistroCargaMasivaVO[] registros=new RegistroCargaMasivaVO[1];
					RegistroCargaMasivaVO registroVacio=new RegistroCargaMasivaVO();
					registroVacio.setDescripcion("");
					registroVacio.setEstado("");
					registroVacio.setFecha("");
					registroVacio.setNombreZip("");
					registroVacio.setPathZip("");
					registros[0]=registroVacio;
					informeCargaMasivaVO.setRegistroCargaVO(registros);
					
				}
				

			}catch (Exception e){
				logger.error("Error mapeando los datos del informe - ",e);
			}

			//Devolvemos un VO con todos los datos del informe
			return informeCargaMasivaVO;
	}

	/**
	 * <p>
	 * Recupera de base de datos las estadisticas de peticiones a feeds para el
	 * intervalo de fechas especificado. En caso de que se dejen las fechas a
	 * null, recupera las estadisticas totales.
	 * </p>
	 * 
	 * @param fechaDesde
	 *            Fecha comienzo del intervalo solicitado.
	 * @param fechaHasta
	 *            Fecha fin del intervalo solicitado.
	 * @return es.pode.auditoria.negocio.servicios.EstadisticasFeedsVO[]
	 * @throws Exception 
	 */
	@Override
	protected EstadisticasFeedsVO[] handleObtenerEstadisticasFeeds(Calendar fechaDesde, Calendar fechaHasta) throws Exception {
		EstadisticasFeedsVO[] result = null;
		Collection<Object> resultSet = null;
		if(logger.isDebugEnabled())logger.debug("Consulta de estadisticas de Feeds con intervalo de fechas  desde = " + (fechaDesde==null?"null":fechaDesde.getTime()) + ", hasta = " + (fechaHasta==null?"null":fechaHasta.getTime()) + ").");
		// Comprueba intervalo de fechas
		if(fechaDesde==null || fechaHasta==null) {
			// Busca estadisticas en todo el intervalo
			resultSet = this.getPeticionFeedDao().obtenerRecuentoFeedsFormato();
		} else if(fechaDesde.compareTo(fechaHasta) >0) {
			/* El intervalo de fechas es incorrecto (desde es mayor que hasta).
			/* Devuelvo un array vacio y genero un mensaje de error en el log
			 */
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			// Intervalo de fechas correcto
			resultSet = getPeticionFeedDao().obtenerRecuentoFeedsFormatoDesdeHasta(fechaDesde, fechaHasta);
		}
		/*
		 * Mapeo de resultados a VO
		 */
		if(resultSet==null) {
			if(logger.isDebugEnabled())logger.debug("No hay resultados en la base de datos (resultSet=null");
			result = new EstadisticasFeedsVO[]{};
		}else {
			if(logger.isDebugEnabled())logger.debug("handleObtenerEstadisticasFeeds: <" + resultSet.size() + "> resultados obtenidos");
			result = new EstadisticasFeedsVO[resultSet.size()];
			/*
			 * Estructura de los datos devueltos por la query:
			 * java.util.Collection<Object>{
			 *   Object[]{String idFeed, String formatoFeed, String periodo, Long count},
			 *   ...,
			 * }
			 */
			int i = 0;
			for (Object object : resultSet) {
				Object[] pair = (Object[])object;
				result[i]=new EstadisticasFeedsVO();
				result[i].setCount((Long)pair[2]);
				result[i].setIdFeed((String)pair[0]);
				result[i].setFormatoFeed((String)pair[1]);
				i++;
			}
		}
		logger.info("Numero de canales recuperados en las estadisticas >" + (result==null?0:result.length)+">");
		return result;
	}

	/**
	 * <p>
	 * Registra en la cola JMS de auditoria la intercepcion de una peticion a un
	 * feed RSS/Atom para su posterior registro en base de datos.
	 * </p>
	 * 
	 * @param peticionFeed
	 *            ValueObject con los datos requeridos para la pecicion (idFeed,
	 *            formato, periodo, fecha e idioma).
	 * @throws Exception 
	 */
	@Override
	protected void handleRegistrarPeticionFeed(PeticionFeedVO peticionFeed)
			throws Exception {
		logger.info("Enviando mensaje de intercepcion de peticion a feed");
		enviarMensajePeticionFeed(peticionFeed);
	}

	private void enviarMensajePeticionFeed(final PeticionFeedVO peticionFeed) {
		this.jmsTemplate.send(new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				return new SimpleMessageConverter().toMessage(peticionFeed, session);
			}
		});
	}
	
	/**
	 * <p>
	 * Registra una peticion a un canal RSS/Atom en la tabla de auditoria.
	 * </p>
	 * 
	 * @param peticionFeed
	 *            Objeto que encapsula los datos de la peticion interceptada.
	 * @return Long
	 * @throws Exception 
	 */
	@Override
	protected Long handleAlmacenarPeticionFeedBD(PeticionFeedVO peticionFeed)
			throws Exception {
		// TODO Auto-generated method stub
		Long result = null;
		if(logger.isDebugEnabled())logger.debug("Peticion a Feed recibida con parametros : idFeed = "
					+ peticionFeed.getIdFeed() + " ; formato = "
					+ peticionFeed.getFormatoFeed() + " ; periodo = "
					+ peticionFeed.getPeriodo() + "; fecha = "
					+ peticionFeed.getFecha().getTime() + " ; idioma = "
					+ peticionFeed.getIdioma());
		try {
			PeticionFeed entity = getPeticionFeedDao().create(
					getPeticionFeedDao().fromPeticionFeedVO(peticionFeed));
			result = entity.getId();
			if(logger.isDebugEnabled())logger.debug("Peticion registrada con id="+entity.getId());
		} catch (Exception e) {
			logger.error("Se ha producido un error en el registro de la peticion",e);
			throw e;
		}
		return result;
	}

/**
	 * Despublicar los odes cuyos identificadores coinciden con los incluidos en el array 
	 * pasado como parámetro
	 * 
	 * @param identificadores
	 * @throws Exception 
	 */
	@Override
	protected void handleDespublicarODEs(String[] identificadores) throws Exception {
		
		//Collection c = new ArrayList();
		Collection<Operacion> operaciones = null;
		
		String id = null;

		
		//obtiene los registros con identificadores coincidentes
		 try
		{
			 IdOdeCriteria criteria = new IdOdeCriteria(identificadores);
			operaciones = getOperacionDao().obtenerOperacionesIdOde(criteria);			
			if (operaciones!= null){
				if(logger.isDebugEnabled())logger.debug("Se han recuperado <" + operaciones.size() + "> operaciones de la base de datos");
				for (Operacion operacion : operaciones) {
					// se actualiza el campo despublicado en cada registro
					operacion.setDespublicado(1);
				}
				
				//se actualizan en la base de datos los registros modificados
				getOperacionDao().update(operaciones);
			}
		}catch (Exception e){
			logger.error("Error obteniendo los registros: ",e);
		}
	}

}