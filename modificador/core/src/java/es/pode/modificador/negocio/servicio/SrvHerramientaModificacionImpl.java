/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.modificador.negocio.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.modificador.ServiceLocator;
import es.pode.modificador.negocio.cambios.CodigosMensajesError;
import es.pode.modificador.negocio.cambios.FactoriaCambios;
import es.pode.modificador.negocio.cambios.ODE;
import es.pode.modificador.negocio.cambios.TareaCambios;
import es.pode.modificador.negocio.cambios.configuracion.AdditionTemplateComposer;
import es.pode.modificador.negocio.cambios.configuracion.ConfiguracionDao;
import es.pode.modificador.negocio.cambios.configuracion.ConfiguracionDaoException;
import es.pode.modificador.negocio.cambios.configuracion.GeneradorTaxonomias;
import es.pode.modificador.negocio.cambios.configuracion.ModificadorProperties;
import es.pode.modificador.negocio.cambios.configuracion.castor.ModificationTask;
import es.pode.modificador.negocio.dominio.Modificacion;
import es.pode.modificador.negocio.dominio.ModificacionDao;
import es.pode.modificador.negocio.dominio.ModificacionEstadoCriteria;
import es.pode.modificador.negocio.dominio.ModificacionOdeCriteria;
import es.pode.modificador.negocio.dominio.ResultadoModificacion;
import es.pode.modificador.negocio.dominio.ResultadoModificacionCriteria;
import es.pode.modificador.negocio.dominio.ResultadoModificacionDao;
import es.pode.modificador.negocio.dominio.ResultadoModificacionPorEstadoCriteria;
import es.pode.modificador.negocio.servicio.vo.CambioTypes;
import es.pode.modificador.negocio.servicio.vo.CambioTypesEnum;
import es.pode.modificador.negocio.servicio.vo.Change;
import es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea;
import es.pode.modificador.negocio.servicio.vo.FormularioModificarVO;
import es.pode.modificador.negocio.servicio.vo.FormularioTaxonomias;
import es.pode.modificador.negocio.servicio.vo.LabelValueVO;
import es.pode.modificador.negocio.utilidades.EstadosTarea;
import es.pode.planificador.negocio.servicios.OdeCargaVO;
import es.pode.planificador.negocio.servicios.TareaEjecutadaExplotacionVO;
import es.pode.planificador.negocio.servicios.TareaModificacionVO;
import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.publicacion.negocio.servicios.ReindexarODEResultadoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.zip.ZipDao;


/**
 * @see es.pode.modificador.negocio.servicio.SrvHerramientaModificacion
 */

public class SrvHerramientaModificacionImpl
    extends es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase
{ 
	
	private static final String RUTA_PLANTILLAS = "/plantillas/";
	private static final String MODIFICADOR_PROPERTIES = "/modificador.properties";
	private static final String ZIP_FOLDER = "zip.folder";
	private static final String PROPOSITO = "proposito";
	private FactoriaCambios factoriaCambios;
	private ConfiguracionDao configuracionDao;
	private ModificadorProperties props = null;
	private org.springframework.jms.core.JmsTemplate jmsTemplate = null;
	private AdditionTemplateComposer template=null;
	private GeneradorTaxonomias generadorTaxonomias = null;
	private ZipDao zipDao = null;

	
	public static final String BASE_PATH="base.path";
	public static final String SIMULATION_PATH="simulacion.path";
	public static final String BACKUP_FOLDER="backup.folder";
	public static final String TALLER_FOLDER="taller.folder";
	public static final String REPORT_FOLDER="report.folder";
	public static final String CONFIGURACION_FILE_PREFIX="configuracion.file.prefix";
	public static final String CONFIGURACION_FILE_POSTFIX="configuracion.file.postfix";
	private static final String NO_MODIFICABLES = "terminos.noModificar";
	private static final String NO_ADD = "teminos.noAdd";
	private static final String VOCABULARIOS = "terminos.vocab";
	private static final String NOMBRETAX = "arbolCurricular";
	private static final String NOMBRETES = "nombreFichTesauro";
	private static final String LANGSTRING = "terminos.langstring";
	private static final String EUSKERA = "eu";
	private static final String INGLES = "en";
	private static final String SPACE = " ";
//	final int BUFFER_SIZE = 10000;
	// En caso de que el contexto de seguridad falle, usamos administrador. Esta
	// constante no debería usarse nunca en 'la vida real', ya que si la
	// seguridad no funciona, nunca se podra configurar una tarea con este
	// usuario. Esta constante se usa solo en pruebas de llamadas SOAP.
	private static final String DEFAULT_USER = "administrador";
	
	/**
	 * Obtiene los datos del termino que se desea modificar. Esto permite pintar el formulario de nuevo valor como campo de texto o combo según sea el termino a modificar un vocabulario controlado o no.
	 */
	
	public SrvHerramientaModificacionImpl() {
		super();
		try{
			limpiarTemporales();
		}catch(Exception e)
		{
			logger.error("error al inicializar el servicio SrvHerramientaModificacionImpl - " , e);
		}
	}
	
	protected FormularioModificarVO handleObtenerFormularioModificar(String idTermino) throws Exception 
	{
		FormularioModificarVO formulario= new FormularioModificarVO();
		formulario.setIdTermino(idTermino);
		String nombre=props.getProperty("lom."+idTermino);
		formulario.setNombreTermino(nombre);
		String[] vocabul=new String[1];
		VocabularioVO[] vocabularioVO=null;
		if(compruebaListaTerminos(idTermino, VOCABULARIOS))
		{
			formulario.setEsCombo(true);
			if(nombre.toLowerCase().equals("value".toLowerCase()))
			{
				int indicevocab=idTermino.lastIndexOf('.');
				String subVocab=idTermino.substring(0, indicevocab);
				vocabul[0]=subVocab;
				vocabularioVO=getSrvVocabulariosControladosService().obtenerCombos(vocabul, INGLES);
			}
			else
			{
				vocabul[0]=idTermino;
				vocabularioVO=getSrvVocabulariosControladosService().obtenerCombos(vocabul, INGLES);
			}
			String[] nombreVocabulario=new String[vocabularioVO[0].getTerminos().length];
			for(int i=0; i<vocabularioVO[0].getTerminos().length;i++)
			{
				nombreVocabulario[i]=vocabularioVO[0].getTerminos()[i].getNomTermino();
			}
			formulario.setOpcionesCombo(nombreVocabulario);
		}
		else
		{
			formulario.setEsCombo(false);
			formulario.setOpcionesCombo(new String[0]);
		}
		if(compruebaListaTerminos(idTermino, LANGSTRING))
		{
			formulario.setLangString(true);
		}
		else
		{
			formulario.setLangString(Boolean.FALSE);
		}
		// Compruebo si tiene hijos para saber si es un termino buscable por valor
		TerminoLomVO termino = this.handleNavegarLom(idTermino);
		if(termino.getHijos()== null || termino.getHijos().length==0) formulario.setEsFinal(Boolean.TRUE);
		else formulario.setEsFinal(Boolean.FALSE);
		return formulario;
	}
	
	/**
	 * Recupera una plantilla para añadir un nuevo termino lomes.
	 * @param terminoLomes Termino lomes que se desea añadir identificado por las etiquetas lomes separadas por puntos (p.e., lom.technical.format).
	 * @return Plantilla para añadir un nuevo termino lomes
	 */
	protected String handleRecuperarPlantillaLomes(String terminoLomes) throws Exception 
	{
		String path=AdditionTemplateComposer.TEMPLATE_FILE_PREFIX+terminoLomes+AdditionTemplateComposer.TEMPLATE_FILE_SUFIX;
		String fichero="";
		String parent=null;
		if(logger.isDebugEnabled()) logger.debug("Recuperando plantilla <" + path+">");
		if(this.getClass().getResource(RUTA_PLANTILLAS+path)==null)
		{
			if(logger.isDebugEnabled()) logger.debug("El fichero <" + path + "> no existe. Pruebo por nombre de etiqueta lomes");
			path=props.getProperty("lom."+terminoLomes)+AdditionTemplateComposer.TEMPLATE_FILE_SUFIX;
			//si es "value", debemos pasar como padre el valor anterior de path, para que la búsqueda de Vocabulario funcione
			if(path.startsWith("value")) {
				parent=terminoLomes.substring(0, terminoLomes.lastIndexOf("."));
				if(logger.isDebugEnabled())logger.debug("Se encontró etiqueta value, término padre es <"+parent+">");
			}
			
			if(logger.isDebugEnabled()) logger.debug("Recuperando plantilla <" + path+">");
			if(this.getClass().getResource(RUTA_PLANTILLAS+path)!=null)
			{
				if(logger.isDebugEnabled()) logger.debug("El fichero <" + path + "> existe");
				fichero = template.getTemplate(path,parent);

			}
		}
		else
		{
			if(logger.isDebugEnabled()) logger.debug("El fichero <" + path + "> existe");
			fichero = template.getTemplate(path,parent);

		}
		return fichero;
	}

	/**
     * Obtiene tipos de modificación
     * @return Tipos de modificación
     */
    protected CambioTypes handleObtenerTipos()
        throws java.lang.Exception
    {
    	return new CambioTypesEnum();
    }

    /**
     * Configura una modificación. Si no existía previamente la tarea, la crea.
     * @param idModificacion Identificador de la tarea
     * @param configuracion VO con los datos de configuracion de una tarea de modificacion.
     * @param nombre Nombre de la tarea
     * @return Identificador de la Tarea
     */
    @Override
    protected java.lang.Long handleConfigurar(java.lang.Long idModificacion, es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea configuracion,java.lang.String nombre)
        throws java.lang.Exception
    {
    	Long salida=null;
    	
    	if(idModificacion==null) {
    		//No existe Modificacion con este nombre
	    	String path=obtenerRuta(preparaNombre(nombre));
	    	File fichero = new File(path);
	    	if(fichero.exists()) {
	    		//Concatenar un numero hasta que la carpeta no exista
	    		int i=0;
	    		String tmp = null;
	    		do {
	    			i++;
	    			tmp = path + "_" + i;
	    			fichero = new File(tmp);
	    		} while(fichero.exists());
	    		path = tmp;
	    	}
	    	if(logger.isDebugEnabled()) logger.debug("Ruta para la tarea <" + nombre + "> : <" + path+">");
	    	String user=null;
			try {
				user = LdapUserDetailsUtils.getLogin();
			} catch (Exception e1) {
				if(logger.isDebugEnabled()) logger.debug(e1);
			}
//	    	Modificacion modif=getModificacionDao().create( nombre, path, EstadosTarea.CONFIGURADA, null,null,null,(user==null?DEFAULT_USER:user));
			
			Modificacion modif=getModificacionDao().create( nombre, path, EstadosTarea.CONFIGURADA, null,null,null,(user==null?DEFAULT_USER:user),Calendar.getInstance());
	    	salida = modif.getIdModificacion();
	    	String errorMsg="Error al crear rutas en ";
	    	try {
				if(!fichero.mkdirs()) {
					logger.warn(errorMsg+path);
					return salida;
				}
		    	guardarConfiguracion(configuracion, path);
			} catch (Exception e) {
				logger.error(errorMsg+path,e);
				return salida;
			}
			logger.info("Se creó Modificación <"+salida+"> en la ruta <"+path+">");
    	} else {
    		Modificacion modificacion=getModificacionDao().load(idModificacion);
    		try {
    			modificacion.setNombre(nombre);
    			modificacion.setFechaModificacion(Calendar.getInstance());
				guardarConfiguracion(configuracion, modificacion.getPath());
			} catch (Exception e) {
				logger.error("Error al actualizar configuracion en <"+modificacion.getPath()+"> - ", e);
				return salida;
			}
			logger.info("Se actualizo Modificacion: <"+salida+">");
    	}
        return salida;
    }

	/**
	 * @param configuracion
	 * @param path
	 * @throws ConfiguracionDaoException
	 */
	private void guardarConfiguracion(
			es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea configuracion,
			String path) throws ConfiguracionDaoException {
		ModificationTask task = (ModificationTask) getBeanMapper().map(
				configuracion, ModificationTask.class);
		String modificationPath = path + File.separator
				+ props.getProperty(CONFIGURACION_FILE_PREFIX)
				+ props.getProperty(CONFIGURACION_FILE_POSTFIX);
		getConfiguracionDao().guardarConfiguracion(task,
				modificationPath);
		logger.info("Se copio la configuracion en <" + modificationPath+">");
	}

    /**
     * Valida modificación
     * @param configuracion Configuracion de la tarea que se quiere validar
     * @return True indica que es una modificación válida
     */
    protected java.lang.Boolean handleValidar(ConfiguracionTarea configuracion)
        throws java.lang.Exception
    {
        //@todo implement protected java.lang.Boolean handleValidar(java.lang.Long idModificacion)
        return true;
    }

  
    /**
     * Obtiene modificaciones ya configuradas.
     * @return modificaciones ya configuradas.
     */
    protected es.pode.modificador.negocio.servicio.ModificacionVO[] handleObtenerModificacionesConfiguradas()
        throws java.lang.Exception
    {
    	EstadosTarea[] estados = new EstadosTarea[1];
    	estados[0]=EstadosTarea.CONFIGURADA;
        return obtenerModificacionesPorEstado(estados);
    }

    /**
     * Obtiene los detalles de la modificación
     * @param idModificacion Identificador de la tarea
     * @return detalles de la modificación
     */
    protected es.pode.modificador.negocio.servicio.ModificacionVO handleObtenerModificacion(java.lang.Long idModificacion)
        throws java.lang.Exception
    {
    	return (ModificacionVO)getModificacionDao().load(ModificacionDao.TRANSFORM_MODIFICACIONVO, idModificacion);
    }

    /**
     * Recupera la traza de una modificación, si existe.
     * @param idModificacion Identificador de la tarea
     * @param id Identificador de Ode
     * @return URI a la traza.
     */
    //testeado
    @Override
    protected es.pode.modificador.negocio.servicio.ResultadoModificacionVO handleRecuperarResultadoODE(java.lang.Long idModificacion, java.lang.Long id)
        throws java.lang.Exception
    {
    	ResultadoModificacionVO resultado = obtenerModificacionOde(idModificacion, id);
		String traza=resultado.getPathTraza();
		if(traza!=null) {
			resultado.setTraza(leeTraza(traza));
			logger.info("Se encontró traza en <"+traza+">");
		}
    	return resultado;
    }

	/**
	 * @param idModificacion
	 * @param idOde
	 * @return
	 */
	private ResultadoModificacionVO obtenerModificacionOde(java.lang.Long idModificacion, java.lang.Long id) {
		ResultadoModificacionCriteria cr = new ResultadoModificacionCriteria();
    	cr.setIdModificacion(idModificacion);
    	cr.setId(id);
    	Collection<?> salida =getResultadoModificacionDao().obtenerResultadosModificacion(ResultadoModificacionDao.TRANSFORM_RESULTADOMODIFICACIONVO,cr);
    	Iterator<?> iter = salida.iterator();
		ResultadoModificacionVO resultado = (ResultadoModificacionVO) iter.next();
		return resultado;
	}

    /**
     * Restaura backup de un ODE perteneciente a la modificación indicada
     * @param idModificacion Identificador de la tarea
     * @param id Identificador del ODE que se desea restaurar
     * @return True si la restauración tuvo éxito
     */
    @Override
    protected java.lang.Boolean handleRestaurar(java.lang.Long idModificacion, java.lang.Long id)
        throws java.lang.Exception
    {
    	Boolean salida= Boolean.FALSE;
    	ResultadoModificacionCriteria criteria = new ResultadoModificacionCriteria();
    	criteria.setIdModificacion(idModificacion);
    	criteria.setId(id);
    	Collection<?> resultados = getResultadoModificacionDao().obtenerResultadosModificacion(criteria);
    	if(resultados.size()>0) {
    		ResultadoModificacion resultado = (ResultadoModificacion)resultados.iterator().next();
	    	if(!resultado.getStatus().equals(EstadosTarea.RESTAURADA)) {
	    		try {
	    			String pathOriginal = resultado.getPathOriginal();
	    			String pathBackup = resultado.getPathBackup();
	    			if (pathBackup!=null&&!pathBackup.equals("")) {
						File fOriginal = new File(pathOriginal);
						File fBackup = new File(resultado.getPathBackup());
						if (getZipDao().esZip(pathOriginal)) {
							if (logger.isDebugEnabled())
								logger.debug("Copiando archivo de backup a localizacion original");
							UtilesFicheros.copiar(fBackup, fOriginal);
						} else if (fOriginal.isDirectory()) {
							if (logger.isDebugEnabled())
								logger.debug("Descomprimiendo archivo de backup en localizacion original");
							getZipDao().descomprimir(pathBackup, pathOriginal);
						} else {
							logger.warn("Error durante la restauracion del backup. No se ha podido detectar el formato del ODE original (comprimido | no comprimido)");
							throw new Exception(
									"Error durante la restauracion del backup. No se ha podido detectar el formato del ODE original (comprimido | no comprimido)");
						}
						if (resultado.getPublicado().booleanValue()) {
							logger.info("Backup restaurado: reindexo ODE <"
									+ resultado.getIdOde()+">");
							ReindexarODEResultadoVO[] reindexacion = this
									.getSrvPublicacionService()
									.reindexarODEsPublicados(
											new String[] { resultado.getIdOde() });
							if (reindexacion == null
									|| reindexacion.length == 0
									|| "0.0".equals(reindexacion[0]
											.getMensaje())) {
								logger.fatal("Fallo la reindexacion del ODE <" + resultado.getIdOde() + "> tras la restauracion del backup.");
							} else {
								logger.info("ODE <" + resultado.getIdOde() + "> reindexado correctamente");
							}
						}
						if(fBackup.delete()) logger.debug("Backup <" + resultado.getPathBackup() + "> eliminado");
						else {
							logger.warn("No se ha podido eliminar el fichero <" + resultado.getPathBackup()+">");
						}
					} else {
						logger.debug("No se encontro backup, damos por restaurada.");
					}
					resultado.setStatus(EstadosTarea.RESTAURADA);
					resultado.setMsgError(CodigosMensajesError.ODE_RESTAURADO);
					getResultadoModificacionDao().update(resultado);

					salida=Boolean.TRUE;
					logger.info("Se restauro el ODE <"+id+">");
				} catch (Exception e) {
					logger.error("No se pudo restaurar el Ode <"+id+"> debido a "+e.getMessage());
					if(logger.isDebugEnabled()) logger.debug(e);
				}
	    	}
    	}
        return salida;
    }

    /**
	 * Envía un mensaje a la cola JMS para que el servicio de tareas de
	 * modificación lance la tarea especificada. La modificacion cambia su estado en
	 * base de datos de CONFIGURADA a RUNNING, para indicar que la tarea esta en
	 * ejecución.
	 * @param idModificacion Identificador de la modificación
	 * @return false si ha habido un problema en el envío del mensaje.
	 */
    @Override
    protected java.lang.Boolean handleEjecutarModificacion(final java.lang.Long idModificacion)
        throws java.lang.Exception
    {
        
    	boolean offline = DecisorOffline.esOffline();
    	logger.debug("EsOffline = " + offline);
    	if(offline) {
    		try {
    			ServiceLocator.instance().getSrvTareasModificacionService().ejecutarTareaModificacion(idModificacion);
    		} catch (Exception e) {
				logger.debug(e);
				throw e;
			}
    	} else {
	    	logger.info("Enviando mensaje para ejecucion de tarea " + idModificacion);
	    	jmsTemplate.send(new MessageCreator(){
	
				public Message createMessage(Session arg0) throws JMSException {
					
					return new SimpleMessageConverter().toMessage( idModificacion, arg0);
				}
	    		
	    	});
	    	
	    	Modificacion mod = getModificacionDao().load(idModificacion);
	    	
	    	if(mod.getIdPlanificador()!=null) {
	    		logger.debug("Ejecucion bajo demanda. Elimino la tarea del planificador");
	    		handleDesprogramarTarea(idModificacion, mod.getIdPlanificador());
	    	}
    	}
        return Boolean.TRUE;
    }

    /**
     * Elimina la tarea de modificación indicada. Imposibilita restaurar los odes afectados
     * @param identificadores Identificadores de las tareas a eliminar
     * @return true si tuvo éxito
     */
    @Override
    protected java.lang.Boolean handleEliminarModificacion(java.lang.Long[] identificadores)
        throws java.lang.Exception
    {
    	Boolean salida=Boolean.TRUE;
    	for(int i=0;i<identificadores.length;i++) {
	    	try {
	    		
				//Elimina ruta
				Modificacion modificacion=getModificacionDao().load(identificadores[i]);
				File path = new File(modificacion.getPath());
				UtilesFicheros.eliminar(path);
//				if(!path.delete()) {
//					logger.warn("No se ha podido eliminar la carpeta " + path.getName() + " ("+path.getPath()+")");
//				}
				logger.info("Eliminada ruta <"+modificacion.getPath()+"> de Modificacion <"+identificadores[i]+">");
				// Comprueba si hay resultados modificacion asociados a la que se quiere borrar
				ResultadoModificacionCriteria criteria = new ResultadoModificacionCriteria();
				criteria.setIdModificacion(identificadores[i]);
				Collection<?> resultadosModificacion = getResultadoModificacionDao().obtenerResultadosModificacion(criteria);
				String idPlanificador = modificacion.getIdPlanificador();
				getResultadoModificacionDao().remove(resultadosModificacion);
				//Elimina instancia en BBDD
				getModificacionDao().remove(identificadores[i]);
				logger.info("Modificacion <"+identificadores[i]+"> eliminada de tablas");

				if(idPlanificador!=null) {
					TrabajoVO trabajo = new TrabajoVO();
					trabajo.setUsuario(LdapUserDetailsUtils.getUsuario());
					trabajo.setTrabajo(idPlanificador);
					if(logger.isDebugEnabled())logger.debug("Eliminando la tarea <" + idPlanificador + "> del planificador");
					getSrvPlanificadorService().eliminarTareas(new TrabajoVO[]{trabajo});
				}
				
			} catch (Exception e) {
				logger.error("No se pudo eliminar modificación <"+identificadores[i]+"> debido a <"+e.getMessage()+">");
				if(logger.isDebugEnabled()) logger.debug(e);
				// salida indica si hubo problemas con alguna de las entidades.
				// No se lanza excepcion para poder continuar con el resto.
				salida = Boolean.FALSE;
			}
    	}
        return salida;
    }

   

    /**
     * Obtiene modificaciones ya ejecutadas
     * @return Modificaciones ya ejecutadas
     */
    //testeado
    @Override
    protected es.pode.modificador.negocio.servicio.ModificacionVO[] handleObtenerModificacionesEjecutadas()
        throws java.lang.Exception
    {
    	EstadosTarea[] estados = new EstadosTarea[4];
    	estados[0]=EstadosTarea.FINALIZADA;
    	estados[1]=EstadosTarea.ERROR;
    	estados[2]=EstadosTarea.WARNING;
    	estados[3]=EstadosTarea.RESTAURADA;
    	return obtenerModificacionesPorEstado(estados);
    }

    /**
     * Obtiene el resultado de la modificación indicada
     * @param idModificacion Identificador de la Modificación
     * @return Resultado de la modificación indicada
     */
    @Override
    protected es.pode.modificador.negocio.servicio.ResultadoModificacionVO[] handleObtenerResultadoModificacion(java.lang.Long idModificacion)
        throws java.lang.Exception
    {
    	ResultadoModificacionCriteria cri = new ResultadoModificacionCriteria();
    	cri.setIdModificacion(idModificacion);
    	Collection<?> colResultados =getResultadoModificacionDao().obtenerResultadosModificacion(ResultadoModificacionDao.TRANSFORM_RESULTADOMODIFICACIONVO,cri);
    	
    	ResultadoModificacionVO[] arrayModificados=colResultados.toArray(new ResultadoModificacionVO[]{});
		Boolean descargable=this.esDestrutiva(arrayModificados);
	
		for(int i=0;i< arrayModificados.length;i++){
			arrayModificados[i].setEsDescargable(descargable);
		}

    	return arrayModificados;
    }

    /**
     * Crea una modificación a partir del XML de configuración
     * @param fichero DataHandler del fichero de configuracion (XML)
     * @return Modificación creada a partir del fichero de configuración
     */
    @Override
    protected es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea handleImportarModificacion(javax.activation.DataHandler fichero)
        throws java.lang.Exception
    {
    	InputStream source=fichero.getDataSource().getInputStream();
    	ModificationTask task = getConfiguracionDao().leerConfiguracion(source);
    	logger.debug("Configuración leida de XML");
        return (ConfiguracionTarea)getBeanMapper().map(task, ConfiguracionTarea.class);
    }

    /**
     * Genera un XML de configuracion a partir de la tarea configurada y lo serializa para su exportacion.
     * @param tarea Tarea a exportar
     * @return XML de configuracion
     */
    @Override
    protected javax.activation.DataHandler handleExportarModificacion(es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea tarea)
        throws java.lang.Exception
    {
    	ModificationTask task = (ModificationTask) getBeanMapper().map(tarea, ModificationTask.class);
    	File temp = File.createTempFile(props.getProperty(CONFIGURACION_FILE_PREFIX),props.getProperty(CONFIGURACION_FILE_POSTFIX));
    	temp.deleteOnExit();
    	logger.info("Creado fichero temporal <"+temp.getName()+">");
    	getConfiguracionDao().guardarConfiguracion(task, temp);
    	logger.info("Configuracion pasada a XML");
    	return new DataHandler(new FileDataSource(temp));
    }
    
    private es.pode.modificador.negocio.servicio.ModificacionVO[] obtenerModificacionesPorEstado(EstadosTarea[] estados)
    throws java.lang.Exception
    {
//    	Collection coleccion=getModificacionDao().obtenerModificacionesPorEstado(ModificacionDaoBase.TRANSFORM_MODIFICACIONVO,estados[0]);
    	ModificacionEstadoCriteria cri = new ModificacionEstadoCriteria();
    	cri.setFechaModificacion(null);
    	cri.setResultado(estados[0]);
    	Collection<?> coleccion=getModificacionDao().obtenerModificacionesPorEstado(ModificacionDao.TRANSFORM_MODIFICACIONVO,cri);
    	for (int i = 1; i < estados.length; i++) {
//    		coleccion.addAll(getModificacionDao().obtenerModificacionesPorEstado(ModificacionDaoBase.TRANSFORM_MODIFICACIONVO,estados[i]));
        	ModificacionEstadoCriteria criteria = new ModificacionEstadoCriteria();
        	criteria.setFechaModificacion(null);
        	criteria.setResultado(estados[i]);
    		coleccion.addAll(getModificacionDao().obtenerModificacionesPorEstado(ModificacionDao.TRANSFORM_MODIFICACIONVO,criteria));
		}
    	return coleccion.toArray(new ModificacionVO[]{});
    }
    
    private ODE[] seleccionaODEAlAzar(int numero,ODE[] originales) {
    	ODE[] odesSimulacion=new es.pode.modificador.negocio.cambios.ODE[numero];
		logger.info("Tomaremos <" + numero + "> ODEs al azar");
		HashSet<ODE> set = new HashSet<ODE>();
		int size = originales.length;
		for (int i = 0; i < numero; i++) {
			Random random = new Random();
			int num = random.nextInt(size);
			while(set.contains(originales[num])) {
//				num = (int)(Math.random() * size);
				num = random.nextInt(size);
			}
			set.add(originales[num]);
			logger.info("Tomamos el ODE <" + num+">");
			odesSimulacion[i]=originales[num];
		}
		return odesSimulacion;
    }
    
    /**
	 * Realiza una ejecución de unos pocos ODEs escogidos al azar entre los
	 * configurados para que se pueda obtener una pseudo-validación de los
	 * cambios configurados. 
	 * @param configuracion Configuración de la tarea que se desea simular.
	 * @param numeroOdes Número máximo de ODEs sobre los que se desea simular la tarea de modificación.
	 * @return Value object que contiene la información
	 * necesaria para mostrar los resultados por ODE y los textos de traza.
	 */
    @Override
    protected ModificacionSimuladaVO handleSimularModificacion(
			ConfiguracionTarea configuracion, Integer numeroOdes)
			throws Exception {
		ModificacionSimuladaVO salida = new ModificacionSimuladaVO();
		File parent = new File(props.getProperty(SIMULATION_PATH));
		Boolean exportado=false;
		File temporalExportacion=null;
		File[] listaTemporalesCreados=null;
		try {
			
			//En el caso de que se haya lanzado una simulación de exportación, creamos una carpeta temporal donde se almacenarán los odes exportados por la exportación, por cada cambio de exportacion que haya
    		
			
			Change[] cambiar = configuracion.getCambios().getCambios();
			logger.debug("Tenemos "+cambiar.length+" cambios");
			listaTemporalesCreados=new File[cambiar.length];
			for(int k=0;k<cambiar.length;k++){
				if(CambioTypes.EXPORT.equals(cambiar[k].getType()) || CambioTypes.TRANSFORMAR.equals(cambiar[k].getType())){
					logger.debug("El tipo de cambio es de exportacion en el k <"+k+">");
    				temporalExportacion = new File(System.getProperty("java.io.tmpdir") + "/" + "exportacionTemporal"+k);
    				logger.debug("La carpetaTemporal es <"+temporalExportacion.getPath()+">");
    				if(!temporalExportacion.exists()){
    					logger.debug("La carpeta temporal no existia, hay que crearla.");
    					boolean creado=temporalExportacion.mkdir();
    					if(!creado){
    						listaTemporalesCreados[k]=null;
    						logger.warn("Se produjo un error al crear la carpeta temporal");
    					}else{
    						listaTemporalesCreados[k]=temporalExportacion;
    						exportado=true;
    					}
    				}else{
    					listaTemporalesCreados[k]=temporalExportacion;
    					exportado=true;
    				}
					if(cambiar[k].getExportPath()!= null && cambiar[k].getExportPath().length()>0){
    					logger.debug("Cambiamos el path adonde vamos a mover los odes exportados <"+temporalExportacion.getPath()+">");
    					cambiar[k].setExportPath(temporalExportacion.getPath());
					}
				}else{
					listaTemporalesCreados[k]=null;
				}
			}
			
			ModificationTask task = (ModificationTask) getBeanMapper().map(
					configuracion, ModificationTask.class);
			
			
			if(!parent.exists()) parent.mkdirs();
			File temp = File.createTempFile(props.getProperty(CONFIGURACION_FILE_PREFIX),props.getProperty(CONFIGURACION_FILE_POSTFIX),
									parent);
			temp.deleteOnExit();
			logger.info("Creado fichero temporal <" + temp.getName()+">");
			getConfiguracionDao().guardarConfiguracion(task, temp);
			TareaCambios tc = factoriaCambios.generarTareaCambios(
					configuracion, props.getProperty(SIMULATION_PATH));
			
			if(numeroOdes.intValue() < tc.getOdes().length) {
				es.pode.modificador.negocio.cambios.ODE[] odesSimulacion = seleccionaODEAlAzar(numeroOdes.intValue(),tc.getOdes());
				tc.setOdes(odesSimulacion);
			}

			logger.info("Se generó tarea de cambios en <" + parent.getPath()+">");
			ResultadoModificacionVO[] resultados = tc.ejecutarTarea(true);
			logger.debug("Se simuló la tarea");
			//Asigno un ID único a cada ResultadoModificacion (en version no simulada seria el ID de base de datos
			for(int i=0;i<resultados.length;i++) resultados[i].setId(Long.valueOf(i));
			salida.setResultados(resultados);
			
			int finalizadas=0;
	    	
	    	for(int i=0; i<resultados.length && !EstadosTarea.ERROR.equals(salida.getResultado());i++)
	    	{
	    		if(resultados[i].getStatus().equals(EstadosTarea.WARNING))
	    		{
	    			if(logger.isDebugEnabled())logger.debug("Se ha encontrado un resultado con estatus warning: <"+ resultados[i].getMsgError()+">");
	    			salida.setResultado(EstadosTarea.WARNING);
	    			salida.setMsgError(CodigosMensajesError.TAREA_WARNING);
	    		}
	    		else if (resultados[i].getStatus().equals(EstadosTarea.ERROR)) 
	    		{
	    			if(logger.isDebugEnabled())logger.debug("Se ha encontrado un resultado con estatus error: <" + resultados[i].getMsgError()+">");
	    			salida.setResultado(EstadosTarea.ERROR);
	    			salida.setMsgError(CodigosMensajesError.TAREA_ERROR);
				}
	    		else if (resultados[i].getStatus().equals(EstadosTarea.FINALIZADA)) 
	    		{
	    			if(logger.isDebugEnabled())logger.debug("Se ha encontrado un resultado con estatus finalizado: <"+ resultados[i].getMsgError()+">");
	    			finalizadas=finalizadas+1;
	    		}
	    		resultados[i].setTraza(resultados[i].getPathTraza()!=null&&!resultados[i].getPathTraza().equals("")?leeTraza(resultados[i].getPathTraza()):"No se ha encontrado traza");
	    	}
	    	if(finalizadas==resultados.length)
	    	{
	    		salida.setResultado(EstadosTarea.FINALIZADA);
	    		salida.setMsgError(CodigosMensajesError.TAREA_EJECUTADA_CORRECTAMENTE);
	    	}
	    	if(finalizadas==0)
	    	{
	    		salida.setResultado(EstadosTarea.ERROR);
	    		salida.setMsgError(CodigosMensajesError.TAREA_ERROR);
	    	}
	    	//Si se ha generado la carpeta de exportación lo borramos
	    	if(exportado){
	    		for(int i=0;i<listaTemporalesCreados.length;i++){
	    			if(listaTemporalesCreados[i]!=null && !listaTemporalesCreados[i].getAbsolutePath().equals("")){
	    				UtilesFicheros.eliminar(listaTemporalesCreados[i]);
	    			}
	    		}
	    	}

		} catch (Exception e) {
			logger.error("Se produjo un error al simular la tarea", e);
			throw new Exception(e);
		} finally {
			if(logger.isDebugEnabled())logger.debug("Eliminando la carpeta de simulacion");
			UtilesFicheros.eliminar(parent);
			//Si se ha generado la carpeta de exportación lo borramos
			if(exportado){
				for(int i=0;i<listaTemporalesCreados.length;i++){
	    			if(listaTemporalesCreados[i]!=null && !listaTemporalesCreados[i].getAbsolutePath().equals("")){
	    				UtilesFicheros.eliminar(listaTemporalesCreados[i]);
	    			}
	    		}
	    	}
		}
		return salida;
	}
    
    /**
     * Obtiene modificaciones en ejecución
     * @return Modificaciones en ejecución
     */
    @Override
	protected ModificacionVO[] handleObtenerModificacionesEnEjecucion() throws Exception {
    	EstadosTarea[] estados = new EstadosTarea[1];
    	estados[0]=EstadosTarea.RUNNING;
        return obtenerModificacionesPorEstado(estados);
	}
	
    /**
     * Genera el path base de una Modificación. 
     * Seguirá la forma 'base.path'/yyyyMMdd/nombre
     * @param nombre Nombre de la modificación
     * @return Path base de la modificación
     */
    private String obtenerRuta(String nombre) {
    	//Ruta común
    	StringBuffer path= new StringBuffer(props.getProperty(BASE_PATH));
    	//Marca de fecha en aaaammdd
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	path.append(dateFormat.format(new Date()));
    	path.append("/");
    	//Nombre de la modificación
    	// Procesar nombre para quitarle
    	
    	path.append(nombre);
    	return path.toString();
    }
    
    public ConfiguracionDao getConfiguracionDao() {
		return configuracionDao;
	}

	public void setConfiguracionDao(ConfiguracionDao configuracionDao) {
		this.configuracionDao = configuracionDao;
	}

	public FactoriaCambios getFactoriaCambios() {
		return factoriaCambios;
	}

	public void setFactoriaCambios(FactoriaCambios factoriaCambios) {
		this.factoriaCambios = factoriaCambios;
	}
    
	public ModificadorProperties getProps() {
		return props;
	}

	public void setProps(ModificadorProperties props) {
		this.props = props;
	}

	/**
	 * Prepara el nombre dado para ser representable en un sistema de ficheros.
	 * @param nombre Nombre a chequear
	 * @return Nombre con los espacios cambiados por "_" y el resto de caracteres 
	 * extraños eliminados
	 */
	private String preparaNombre(String nombre) {
		String[] temp=nombre.replace(' ', '_').split("[\\.\\:\\;\\*\\@\\%\\^\\&\\(\\)\\[\\]\\{\\}\\\"\\'\\<\\>\\?\\+\\=\\/\\|ñáéíóúüÁÉÍÓÚÜ]");
		StringBuffer salida= new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			salida.append(temp[i]);
		}
		//Solucion para caso extremadamente raro: nombre que solo estaba compuesto por carateres extraños
		if("".equals(salida.toString())) return "unnamed";
		return salida.toString();
	}
	
	/**
	 * Lee el contenido del fichero de trazas
	 * @param path Ruta al fichero de trazas
	 * @return Texto del fichero de trazas
	 * @throws Exception Encapsula IOException original
	 */
	private String leeTraza(String path) throws Exception{
		String salida = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = in.readLine()) != null) {
				buffer.append(temp).append("\n");
			}
			in.close();
			salida = buffer.toString();
		} catch (IOException e) {
			logger.error("No se encontró traza en <"+path+"> debido a - "+e);
			throw new Exception(e);
		}
		return salida;
	}

	public org.springframework.jms.core.JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(org.springframework.jms.core.JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * Asigna los resultados dados a la tarea de modificación indicada
	 * @param idModificacion Identificador de la modificación
	 * @param resultados Lista de resultados
	 * @return True si tuvo éxito
	 */
	@Override
	protected Boolean handleRegistrarResultadosTarea(Long idModificacion, ResultadoModificacionVO[] resultados) throws Exception {
		return true;
	}

	/**
	 * Elimina del planificador la tarea de modificacion previamente programada.
	 * @param idModificacion Identificador de la modificación
	 * @param idPlanificador El identificador con el que el planificador conoce a la tarea de modificación que se quiere desprogramar. Si es null, el servicio realiza la consulta para recuperar la tarea identificada por idModificacion.
	 * @return True si tuvo éxito 
	 */
	@Override
	protected Boolean handleDesprogramarTarea(Long idModificacion, String idPlanificador) throws Exception {
		TrabajoVO trabajo = new TrabajoVO();
		trabajo.setUsuario(LdapUserDetailsUtils.getUsuario());
		trabajo.setTrabajo(idPlanificador);
		trabajo.setGrupoTrabajo(ConstantesAgrega.GRUPO_MODIFICADOR);
		try {
		getSrvPlanificadorService().eliminarTareas(new TrabajoVO[]{trabajo});
		} catch (Exception e) {
			logger.error("Error del planificador al eliminar la tarea idModificacion <"+ idModificacion + ">: " + e.getMessage());
			if(logger.isDebugEnabled()) logger.debug(e);
			throw e;
		}
		return Boolean.TRUE;
	}

	/**
	 * Genera el codigo XML correspondiente a un taxonPath del arbol curricular que se quiere insertar en un campo classification (soportados: arbolcurricular o ETB). Este codigo devuelto esta listo para incorporarse a una tarea de Añadir Termino Lomes como nuevoValor.
	 * @param taxonomia Taxonomía
	 * @return Codigo XML correspondiente a un taxonPath del arbol curricular
	 */
	@Override
	protected String handleGenerarArbolCurricular(TaxonomiaVO taxonomia) throws Exception 
	{
		String result = null;
		EstructuraVdexVO[] taxEntero = getSrvTaxonomiaService().obtenerTaxonomias(taxonomia.getIdioma());
		String nombreTaxonomia=obtenerNombreTaxonomiaOTesauro(taxEntero,taxonomia.getNombreTaxonomia());
		if(logger.isDebugEnabled())logger.debug("Obtenemos el nombre de la taxonomia <"+nombreTaxonomia+"> y lo insertamos en la taxonomia que nos venía");
		taxonomia.setNombreTaxonomia(nombreTaxonomia);
		result = getGeneradorTaxonomias().generarTaxonomia(taxonomia, GeneradorTaxonomias.ARBOL_CURRICULAR);
		if(logger.isDebugEnabled()) logger.debug("Arbol curricular generado:\n" + result);
		return result;
	}
	
	/**
	 * Genera el codigo XML correspondiente a un taxonPath del ETB que se quiere insertar en un campo classification (soportados: arbolcurricular o ETB). Este codigo devuelto esta listo para incorporarse a una tarea de Añadir Termino Lomes como nuevoValor.
	 * @param taxonomia Taxonomía
	 * @return codigo XML correspondiente
	 */
	@Override
	protected String handleGenerarETB(TaxonomiaVO taxonomia) throws Exception 
	{
		String result = null;
		EstructuraVdexVO[] tesEntero=getSrvTesaurosServices().obtenerTesauros(taxonomia.getIdioma());
		String nombreTesauro=obtenerNombreTaxonomiaOTesauro(tesEntero,taxonomia.getNombreTaxonomia());
		if(logger.isDebugEnabled())logger.debug("Obtenemos el nombre del tesauro "+nombreTesauro+" y lo insertamos en el tesauro que nos venía");
		taxonomia.setNombreTaxonomia(nombreTesauro);
		result = getGeneradorTaxonomias().generarTaxonomia(taxonomia, GeneradorTaxonomias.ETB);
		if(logger.isDebugEnabled()) logger.debug("Arbol curricular generado:\n" + result);
		return result;
	}

	private TaxonomiaVO comunArbolCurriYTesauros(String idNodo, String nombreTaxonomia, String idioma) throws Exception
	{
		TaxonomiaVO taxonomia=new TaxonomiaVO();
		taxonomia.setIdioma(idioma);
		taxonomia.setNombreTaxonomia(nombreTaxonomia);
		es.pode.modificador.negocio.servicio.TaxonVO[] hijos=null;
		es.pode.modificador.negocio.servicio.TaxonVO[] taxonPath =null;
		TaxonVO[] taxPath =null;
		if(idNodo==null)
		{
			TaxonVO[] arrayTaxones = getSrvTaxonomiaService().obtenerTaxonomia(nombreTaxonomia, idioma);
			//Si es arrayTaxones es null, cascaba
//			hijos=new es.pode.modificador.negocio.servicio.TaxonVO[arrayTaxones.length];
			if(arrayTaxones!=null && arrayTaxones.length>0)
			{
				hijos=new es.pode.modificador.negocio.servicio.TaxonVO[arrayTaxones.length];
				for(int i=0; i<arrayTaxones.length;i++)
				{
					hijos[i] = (es.pode.modificador.negocio.servicio.TaxonVO) getBeanMapper().map(arrayTaxones[i], es.pode.modificador.negocio.servicio.TaxonVO.class);
					
				}
				
			}
			taxonPath = new es.pode.modificador.negocio.servicio.TaxonVO[0];
		}
		else
		{
			taxPath = getSrvTaxonomiaService().obtenerTaxonPath(idNodo, nombreTaxonomia, idioma);
			taxonPath = new es.pode.modificador.negocio.servicio.TaxonVO[taxPath.length];

			for(int i=0;i<taxPath.length;i++)
			{
				
				taxonPath[i]=(es.pode.modificador.negocio.servicio.TaxonVO) getBeanMapper().map(taxPath[i], es.pode.modificador.negocio.servicio.TaxonVO.class);

			}
			es.pode.modificador.negocio.servicio.TaxonVO[] taxReves=new es.pode.modificador.negocio.servicio.TaxonVO[taxPath.length];
			for(int j=0;j<taxonPath.length;j++){//Nos devuelven el taxon path empezando del hijos hacia el padre, le tenemos que dar la vuelta pra poder escribirlo bien en el classification que generamos
				taxReves[j]=taxonPath[(taxonPath.length-j-1)];
			}
			for(int k=0;k<taxReves.length;k++){
				taxonPath[k]=taxReves[k];
			}
				TaxonVO[] hijos2 = getSrvTaxonomiaService().obtenerNodos(idNodo, nombreTaxonomia, idioma);
				
				if(hijos2!=null && hijos2.length>0){
					hijos=new es.pode.modificador.negocio.servicio.TaxonVO[hijos2.length];
					for(int i=0;i<hijos2.length;i++)
					{
						hijos[i]=(es.pode.modificador.negocio.servicio.TaxonVO) getBeanMapper().map(hijos2[i], es.pode.modificador.negocio.servicio.TaxonVO.class);
					}
				}else{
					if(logger.isDebugEnabled())logger.debug("No tenemos hijos");
					hijos=new es.pode.modificador.negocio.servicio.TaxonVO[0];
				}
			
		}
		taxonomia.setHijos(hijos);
		
		
		taxonomia.setTaxonPath(taxonPath);
//		VdexVO[] estructuraEducativas = getSrvEstructurasEducativasService().listarArbolCurricular();
//		List taxonomias= new ArrayList();
//		for(int i=0; i<estructuraEducativas.length;i++)
//		{
//			taxonomias.add(i, estructuraEducativas[i].getNombre());
//		}
//		
//		taxonomia.setTaxonomias((String[])taxonomias.toArray(new String[taxonomias.size()]));
		
		return taxonomia;
	}
	
	/**
	 * Realiza una consulta a fuentes taxonomicas para navegar por la taxonomia elegida (arbol curricular, etb)
	 * @param idNodo Si es null, recuperar el raiz.
	 * @param nombreTaxonomia Nombre de la taxonomia que se desea navegar
	 * @param idioma Idioma para la representacion de los nodos de la taxonomía.
	 * @return Taxonomía correspondiente
	 */
	@Override
	protected TaxonomiaVO handleNavegarTaxonomia(String idNodo, String nombreTaxonomia, String idioma) throws Exception 
	{

		if(nombreTaxonomia==null)
		{
			nombreTaxonomia=props.getProperty(NOMBRETAX);
		}
		TaxonomiaVO taxonomia=comunArbolCurriYTesauros(idNodo,nombreTaxonomia,idioma);
		return taxonomia;
	}

	/**
	 * Realiza una consulta a fuentes taxonomicas para navegar por la taxonomia elegida (en este caso, LOM)
	 * @param idTermino Identificador del termino lom en que se esta navegando. Si el termino es el raiz (lom), usar null.
	 * @return TérminoLom correspondiente
	 */
	@Override
	protected TerminoLomVO handleNavegarLom(String idTermino) throws Exception {
		TerminoLomVO result = new TerminoLomVO();
		result.setHijos(obtenerHijosTerminoLom(idTermino));
		if(idTermino == null) {
			if(logger.isDebugEnabled()) logger.debug("Recuperando termino raiz lom");
			result.setNombreTermino("lom");
			result.setModificable(Boolean.FALSE);
			result.setAniadir(Boolean.FALSE);
			result.setEliminable(Boolean.FALSE);
			result.setRutaTermino("lom");
		} else {
			if(logger.isDebugEnabled()) logger.debug("Recuperando termino " + idTermino);
			result.setIdTermino(idTermino);
			result.setNombreTermino(props.getProperty("lom."+idTermino));
			result.setRutaTermino(componerRutaLomes(idTermino));
			
			// Solo modificamos terminos finales: compruebo que el numero de hijos es 0
			result.setModificable(Boolean.valueOf(result.getHijos().length==0 && !compruebaListaTerminos(result.getIdTermino(), NO_MODIFICABLES)));
			result.setAniadir(Boolean.valueOf(!compruebaListaTerminos(result.getIdTermino(), NO_ADD)));
			result.setEliminable(Boolean.valueOf(!compruebaListaTerminos(result.getIdTermino(), NO_ADD)));
		}
		result.setPadres(recuperarPadres(idTermino));
		return result;
	}

	private TerminoLomVO[] recuperarPadres(String idTermino) {
		TerminoLomVO[] result = null;
		ArrayList<TerminoLomVO> lista = new ArrayList<TerminoLomVO>();
		TerminoLomVO termino = new TerminoLomVO();
		termino.setIdTermino(null);
		termino.setNombreTermino("lom");
		lista.add(termino);
		if(idTermino!=null) {
			String[] tokens = idTermino.split("\\.");
			String token = tokens[0];
			for(int i=0;i<tokens.length;i++) {
				if(i>0) token = token.concat(".").concat(tokens[i]);
				termino = new TerminoLomVO();
				termino.setIdTermino(token);
				termino.setNombreTermino(props.getProperty("lom.".concat(token)));
				lista.add(termino);
				
			}
		}
		result = lista.toArray(new TerminoLomVO[lista.size()]);
		return result;
	}
	
	private String componerRutaLomes(String idTermino) {
		String result = null;
		StringBuffer sb = new StringBuffer("lom.");
		String[] tokens = idTermino.split("\\.");
		String token = "";
		String termino = null;
		for(int i=0;i<tokens.length;i++) {
			token=token.concat(".").concat(tokens[i]);
			termino = props.getProperty("lom"+token);
			sb.append(termino);
			if(i<tokens.length-1) sb.append(".");
		}
		result = sb.toString();
		return result;
	}
	
	private TerminoLomVO[] obtenerHijosTerminoLom(String idTermino) {
		ArrayList<TerminoLomVO> result = new ArrayList<TerminoLomVO>();
		String key="lom";
		if(idTermino!=null) key = key.concat(".").concat(idTermino);
		int i=1;
		String hijo = props.getProperty(key+"."+i);
		String idHijos = (idTermino==null ? "" : idTermino.concat(".")); 
		while(hijo!=null) {
			TerminoLomVO hijoVO = new TerminoLomVO();
			hijoVO.setIdTermino(idHijos+i);
			hijoVO.setNombreTermino(hijo);
			hijoVO.setModificable(Boolean.valueOf(!compruebaListaTerminos(hijoVO.getIdTermino(), NO_MODIFICABLES)));
			hijoVO.setAniadir(Boolean.valueOf(!compruebaListaTerminos(hijoVO.getIdTermino(), NO_ADD)));
			hijoVO.setEliminable(Boolean.valueOf(!compruebaListaTerminos(hijoVO.getIdTermino(), NO_ADD)));
			result.add(hijoVO);
			// Prepara siguiente hijo
			i++;
			hijo = props.getProperty(key+"."+i);
		}
		
		return result.toArray(new TerminoLomVO[result.size()]);
	}
	
	
	
	private boolean compruebaListaTerminos(String idTermino, String lista) {
		String terminos = props.getProperty(lista);
		String[] terminosArray = splitLista(terminos);
		List<String> list = Arrays.asList(terminosArray);
		return (list.contains(idTermino));
	}
	
	private String[] splitLista(String terminos) {
		String[] result = null;
		if(terminos==null) {
			result = new String[0];
		} else {
			result = terminos.split(",");
		}
		return result;
	}

	/**
	 * Realiza una consulta a fuentes taxonomicas para navegar por la taxonomia elegida (en este caso, tesauro)
	 * @param idNodo Si es null, recuperar el raiz.
	 * @param nombreTaxonomia Nombre de la taxonomia que se desea navegar
	 * @param idioma Idioma para la representacion de los nodos de la taxonomía.
	 * @return Taxonomía correspondiente
	 */
	@Override
	protected TaxonomiaVO handleNavegarTesauro(String idNodo, String nombreTaxonomia, String idioma) throws Exception {
		if(nombreTaxonomia==null)
		{
			nombreTaxonomia=props.getProperty(NOMBRETES);
		}
		TaxonomiaVO taxonomia=new TaxonomiaVO();
		taxonomia.setIdioma(idioma);
		taxonomia.setNombreTaxonomia(nombreTaxonomia);
		// Recupero los terminos relacionados con idNodo
		
		// Casos: idNodo = null (recuperar raiz) | idNodo != null -> recuperar terminos relacionados
		// idNodo "0.0" solo tiene sentido en el portal, por lo que no entrará
		// como parámetro aunque puede salir en el TaxonomiaVO de resultado
		// (varias rutas disponibles)
		TaxonVO[] taxonesFT = null;
		if(idNodo==null) {
			// Recupero los hijos del nodo raiz
			taxonesFT= this.getSrvTesaurosServices().obtenerPrimerNivelTesauro(nombreTaxonomia, idioma);
			// Mapeo a los VOs propios del modificador
			
			// Dejo a null taxonPath y jerarquia
		} else {
			// Recupero los terminos relacionados con el elemento idNodo
			taxonesFT = this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(idNodo, nombreTaxonomia, idioma);
			// Recupero las posibles rutas padre
			JerarquiaVO[] jerarquia = this.getSrvTesaurosServices().obtenerJerarquia(idNodo, nombreTaxonomia, idioma);
			if(jerarquia.length>1){
				taxonomia.setJerarquia(transformarVOFT2MOD(jerarquia, es.pode.modificador.negocio.servicio.JerarquiaVO.class).toArray(new es.pode.modificador.negocio.servicio.JerarquiaVO[]{}));
				// Hay varias rutas disponibles para taxonPath: construyo el taxon "Varias rutas disponibles"
				es.pode.modificador.negocio.servicio.TaxonVO[] taxonPath = new es.pode.modificador.negocio.servicio.TaxonVO[2];
				es.pode.modificador.negocio.servicio.TaxonVO variasRutas = new es.pode.modificador.negocio.servicio.TaxonVO();
				// 0.0 identificador que uso para el elemento 'Varias rutas disponibles'
				variasRutas.setId("0.0");
				// En la capa de presentacion hay que traducir 0.0 por el mensaje correspondiente
				variasRutas.setValorTax("0.0");
				variasRutas.setEsHoja(Boolean.FALSE);
				taxonPath[0] = variasRutas;
				TaxonVO ultimoTaxonFT = jerarquia[0].getJerarquia()[jerarquia[0].getJerarquia().length-1];
				es.pode.modificador.negocio.servicio.TaxonVO ultimoTaxon = (es.pode.modificador.negocio.servicio.TaxonVO)getBeanMapper().map(ultimoTaxonFT, es.pode.modificador.negocio.servicio.TaxonVO.class);
				taxonPath[1] = ultimoTaxon;
				taxonomia.setTaxonPath(taxonPath);
			} else {
				// Solo hay una ruta padre: la inserto
				TaxonVO[] taxonPathFT = jerarquia[0].getJerarquia();				
				taxonomia.setTaxonPath(transformarVOFT2MOD(taxonPathFT, es.pode.modificador.negocio.servicio.TaxonVO.class).toArray(new es.pode.modificador.negocio.servicio.TaxonVO[]{}));
			}
		}
		taxonomia.setHijos(transformarVOFT2MOD(taxonesFT, es.pode.modificador.negocio.servicio.TaxonVO.class).toArray(new es.pode.modificador.negocio.servicio.TaxonVO[]{}));
		return taxonomia;
	}

	private Collection<Object> transformarVOFT2MOD(Object[] arrayFT, Class<?> destClass ) {
		ArrayList<Object> lista = new ArrayList<Object>();
		for(int i=0;i<arrayFT.length;i++) lista.add(getBeanMapper().map(arrayFT[i], destClass));
		return lista;
	}
	
	/**
	 * Programa en el planificador la ejecucion diferida de la tarea.
	 * @param idModificacion Identificador de modificación
	 * @param fechaEjecucion Fecha en la que se desea lanzar la tarea de modificación.
	 * @return True si tuvo éxito
	 */
	@Override
	protected Boolean handlePlanificarModificacion(Long idModificacion, Calendar fechaEjecucion) throws Exception {
		Boolean result = Boolean.TRUE;
		// Compruebo si la tarea esta planificada: recupero la entidad:
		Modificacion modificacion = null;
		TareaModificacionVO retorno = null;
		try {
			modificacion = getModificacionDao().load(idModificacion);
		} catch (Exception e) {
			logger.error("Error durante la recuperacion de la modificacion <" + idModificacion+">");
			throw e;
		}
		if(modificacion == null) {
			logger.error("La modificacion " + idModificacion + " no existe en base de datos");
			throw new Exception("La modificacion <" + idModificacion + "> no existe en base de datos");
		}
		TareaModificacionVO tarea = new TareaModificacionVO();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaEjecucion.getTime());
		tarea.setFechaInicio(calendar);
		tarea.setIdModificacion(idModificacion);
		tarea.setTrabajo(modificacion.getIdPlanificador());
		if(modificacion.getUser()==null) {
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		} else {
			tarea.setUsuario(modificacion.getUser());
		}
		try {
			if(modificacion.getIdPlanificador()==null) {
				// No estaba planificada
				if(logger.isDebugEnabled()) logger.debug("Creo la tarea en el planificador");
				retorno = this.getSrvPlanificadorService().crearTareaModificacion(tarea);
				
			} else {
				// Ya estaba planificada: modifico la fecha
				if(logger.isDebugEnabled()) logger.debug("Planifico la tarea en el planificador");
				retorno = this.getSrvPlanificadorService().modificarTareaModificacion(tarea);
			}
		} catch (Exception e) {
			logger.error("Error al registrar la tarea <" + idModificacion + "> en el planificador: <" + e.getMessage()+">");
			if(logger.isDebugEnabled()) logger.debug(e);
			throw e;
		}
		if(logger.isDebugEnabled()) logger.debug("Valores retornados por el planificador: trabajo = <" + retorno.getTrabajo() + "> fechaInicio = <" + tarea.getFechaInicio().getTime()+">");
		// Modifico el registro de la base de datos:
		modificacion.setFechaEjecucion(retorno.getFechaInicio());
		modificacion.setIdPlanificador(retorno.getTrabajo());
		try {
			getModificacionDao().update(modificacion);
		} catch (Exception e) {
			logger.error("Error al actualizar la base de datos con idModificacion = <" + idModificacion + "> y fecha de planificacion: <" + e.getMessage()+">");
			if(logger.isDebugEnabled()) logger.debug(e);
			throw e;
		}
		return result;
	}

	/**
	 * @return the template
	 */
	public AdditionTemplateComposer getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(AdditionTemplateComposer template) {
		this.template = template;
	}

	/**
	 * Carga la configuracion de una tarea de modificacion para su modificación.
	 * @param idModificacion Identificador de modificación
	 * @return configuracion de una tarea de modificacion
	 */
	@Override
	protected ConfiguracionTarea handleObtenerConfiguracionTarea(Long idModificacion) throws Exception {
		ConfiguracionTarea configuracion = null;
		try {
			Modificacion modificacion = getModificacionDao().load(idModificacion);
			String ficheroConfiguracion = modificacion.getPath() + "/"+ props.getProperty(CONFIGURACION_FILE_PREFIX) + props.getProperty(CONFIGURACION_FILE_POSTFIX);
			logger.debug("Recuperando la información de <" + ficheroConfiguracion+">");
			ModificationTask modTask = configuracionDao.leerConfiguracion(ficheroConfiguracion);
			configuracion = (ConfiguracionTarea)getBeanMapper().map(modTask, ConfiguracionTarea.class);
			configuracion.getCambios().setCambios(completarChange(configuracion.getCambios().getCambios()));
		} catch (Exception e) {
			logger.error("Mo se ha podido recuperar la configuracion de la tarea " + idModificacion);
			if(logger.isDebugEnabled()) logger.debug(e);
		}
		
		return configuracion;
	}

	/**
	 * Completa los atributos idLomTerm y idTaxonomia de cada cambio
	 * @param cambios
	 * @return
	 */
	private Change[] completarChange(Change[] cambios) {
		Change[] result = null;
		if(cambios!=null){
			result = new Change[cambios.length];
			for(int i=0;i<cambios.length;i++) {
				result[i] = cambios[i];
				String lomTerm = cambios[i].getLomTerm();
				result[i].setIdLomTerm(convertLomTerm2IdLomTerm(lomTerm));
				if(logger.isDebugEnabled()) logger.debug("idLomTerm: <" + result[i].getIdLomTerm()+">");
			}
		}
		return result;
	}
	
	private String convertLomTerm2IdLomTerm(String lomTerm) {
		String[] tokens = lomTerm.split("\\.");
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<tokens.length;i++) {
			int j=0;
			String value = null;
			do {
				j++;
				String key = "lom."+sb.toString()+j;
				value = props.getProperty(key);
				if(tokens[i].equals(value)) {
					sb.append(j);
					if(i<tokens.length-1) sb.append(".");
				}
			} while (value!=null && !tokens[i].equals(value));
		}
		return sb.length()>0 ? sb.toString(): null;
	}
	

	
//	private String convertNewValue2IdTaxonomia(String newValue, EspecialTermTypes termType) {
//		return null;
//	}
	
	/**
	 * @return the generadorTaxonomias
	 */
	public GeneradorTaxonomias getGeneradorTaxonomias() {
		return generadorTaxonomias;
	}

	/**
	 * @param generadorTaxonomias the generadorTaxonomias to set
	 */
	public void setGeneradorTaxonomias(GeneradorTaxonomias generadorTaxonomias) {
		this.generadorTaxonomias = generadorTaxonomias;
	}

	/**
	 * @return the zipDao
	 */
	public ZipDao getZipDao() {
		return zipDao;
	}

	/**
	 * @param zipDao the zipDao to set
	 */
	public void setZipDao(ZipDao zipDao) {
		this.zipDao = zipDao;
	}

	/**
	 * Devuelve aquellas modificaciones que han modificado el ODE identificado.
	 * @param pathOriginal Ruta del ODE del que se quiere chequear si tiene modificaciones pendientes de validar
	 * @return modificaciones que han modificado el ODE identificado.
	 */
	@Override
	protected ModificacionVO[] handleChequearResultadosODE(String pathOriginal) throws Exception {
		ModificacionVO[] result = null;
		try {
			EstadosTarea[] estados = new EstadosTarea[]{EstadosTarea.ERROR,EstadosTarea.FINALIZADA,EstadosTarea.RUNNING,EstadosTarea.WARNING};
			/*
			 * Buscamos las modificaciones que pueden bloquear la modificación
			 * actual, la que afecta al ode presente en pathOriginal. Afectarán
			 * las modificaciones cuyo estado de cada ode sea uno de los
			 * indicados en el Array estados y cuya ruta de backup empiece por
			 * la ruta de trabajo del modificador, pues las tareas no
			 * destructivas no tiene ruta de backup.
			 */
			ModificacionOdeCriteria cri = new ModificacionOdeCriteria(pathOriginal,estados[0],props.getProperty(BASE_PATH));
	    	Collection<?> coleccion=getModificacionDao().obtenerModificacionesOde(ModificacionDao.TRANSFORM_MODIFICACIONVO,cri);
	    	for (int i = 1; i < estados.length; i++) {
	    		cri = new ModificacionOdeCriteria(pathOriginal,estados[i],props.getProperty(BASE_PATH));
	    		coleccion.addAll(getModificacionDao().obtenerModificacionesOde(ModificacionDao.TRANSFORM_MODIFICACIONVO,cri));
			}
			result = coleccion.toArray(new ModificacionVO[coleccion.size()]);
		} catch (Exception e) {
			logger.error("Error recuperando las modificaciones sobre el ODE " + pathOriginal + ": " + e.getMessage());
			if(logger.isDebugEnabled()) logger.debug(e);
		}
		return result;
	}

	/**
	 * Obtiene de fuentestaxonomicas las opciones para combos de taxonomias, proposito (9.1 de lomes) e idioma.
	 */
	@Override
	protected FormularioTaxonomias handleObtenerCombosTaxonomias(String idioma)
			throws Exception {
		InputStream is = null;
    	Properties prop = new Properties();
    	FormularioTaxonomias formularioTaxonomias=new FormularioTaxonomias();
    	try {
    		is = this.getClass().getResourceAsStream(MODIFICADOR_PROPERTIES);
 			prop.load(is);
			String[] l_id = { prop.getProperty("idiomaDestinatario"), prop.getProperty(PROPOSITO)}; 
			VocabularioVO[] vocaCombos = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idioma);
	//		vamos a ordenar vocabulario
			VocabularioVO[] vocabularioOrdenado = ordenarVocabulariosVO(vocaCombos);
			
			if ((vocabularioOrdenado!=null) && (vocabularioOrdenado.length>0)) {
				// Rellenamos combos
				for (int ic=0;ic<vocabularioOrdenado.length; ic++) {
					if (vocabularioOrdenado[ic].getIdVocabulario().equals(prop.getProperty(PROPOSITO))) {
						// 1.- Combo de Razon-Purpose 9.1
						TerminoVO[] terminosDest = vocabularioOrdenado[ic].getTerminos();
	
						// crear el ordena en un fichero de utilidades TerminoVO[] terminosOrdDest = ordena(terminosDest, idiomaLdap);
						LabelValueVO label=new LabelValueVO();
						label.setLabel("");
						label.setValue("");
						
//						TerminoVO termVacio = new TerminoVO();
//						termVacio.setIdiomaTermino("");
//						termVacio.setIdTermino("");
//						termVacio.setNomTermino("");
//						Collection idDest2 = Arrays.asList(terminosDest);
//						Collection idDest= new ArrayList();
//						idDest.add(termVacio); //Metemos el vacio para que nos salga en el combo ""
						LabelValueVO[] arrayLabel=new LabelValueVO[terminosDest.length+1];
						arrayLabel[0]=label;
						for(int i=0;i< terminosDest.length;i++){
							LabelValueVO labelNuevo=new LabelValueVO();
							labelNuevo.setValue(terminosDest[i].getIdTermino());
							labelNuevo.setLabel(terminosDest[i].getNomTermino());
							arrayLabel[i+1]=labelNuevo;
						}

						formularioTaxonomias.setProposito(arrayLabel);

					}//fin if
				}//fin for
				
			}//fin vocaCombos
			
				//3.- Combo de Source--> generado por nosotros
				//metodo de prueba
			
			EstructuraVdexVO[] taxonomias = this.getSrvTaxonomiaService().obtenerTaxonomias(idioma);
            EstructuraVdexVO[] tesauros = this.getSrvTesaurosServices().obtenerTesauros(idioma);
            int tamañoTax = taxonomias == null?0:taxonomias.length;
            int tamañoTes = tesauros == null?0:tesauros.length;
            int tamañoCombo = tamañoTax + tamañoTes;//tamaño es número de taxonomias mas número de tesauro
            

			LabelValueVO label=new LabelValueVO();
			label.setLabel("");
			label.setValue("");
			LabelValueVO[] arrayLabel=new LabelValueVO[tamañoCombo+1];
			arrayLabel[0]=label;
			
			if (taxonomias!=null) {
				for (int i = 0; i < tamañoTax; i++) {
					LabelValueVO labelNuevo = new LabelValueVO();
					labelNuevo.setValue(taxonomias[i].getVocabIdentifier());
					labelNuevo.setLabel(taxonomias[i].getVocabName());
					arrayLabel[i + 1] = labelNuevo;
				}
			}
			String literalTituloTesauro = prop.getProperty("Tesauro"+"_"+idioma);
			for(int i=0;i<tamañoTes;i++){
				 String vocabNameTes = tesauros[i].getVocabName();
				 LabelValueVO labelNuevo=new LabelValueVO();
                 if (EUSKERA.equals(idioma) || INGLES.equals(idioma))
                	 labelNuevo.setLabel(vocabNameTes + SPACE + literalTituloTesauro);
                 else 
                	 labelNuevo.setLabel(literalTituloTesauro + SPACE + vocabNameTes);
                 labelNuevo.setValue("TES_" + tesauros[i].getVocabIdentifier());
                 logger.debug("Tesauros y taxonomias+Insertamos el valor <"+labelNuevo.getValue()+"> y label <"+labelNuevo.getLabel()+">");
                 arrayLabel[tamañoTax+1+i]=labelNuevo;

			}
			formularioTaxonomias.setTaxonomias(arrayLabel);

    	}catch (Exception e) {
	    		logger.error("Error en handleObtenerCombosTaxonomias - ", e);
	    		//throw new java.lang.Exception("detalle.clasificacion", e);
	    		
	    		
	        } finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
	        }//fin finally	
		return formularioTaxonomias;
	}
	
//	Metodo que ordena los combos con vocabulariosvo
	private VocabularioVO[]  ordenarVocabulariosVO(VocabularioVO[]vocabulario) 
	{
		
		String idioma="";
		if(vocabulario.length>0)
			idioma=vocabulario[0].getIdioma();
             
		for(int i=0; i < vocabulario.length; i++)
		{   
			for (int j= 0; j<vocabulario[i].getTerminos().length -1; j++){

		        for (int k= j+1; k<vocabulario[i].getTerminos().length; k++){
		        	
		             if (vocabulario[i].getTerminos()[j].getNomTermino().compareTo(vocabulario[i].getTerminos()[k].getNomTermino())>0 ){

		           		   TerminoVO auxTerm=new TerminoVO();

		                   String aux= vocabulario[i].getTerminos()[k].getNomTermino();
		                   String auxId=vocabulario[i].getTerminos()[k].getIdTermino();
		                   auxTerm.setIdiomaTermino(idioma);
		                   auxTerm.setIdTermino(auxId);
		                   auxTerm.setNomTermino(aux);
		                               
		                   vocabulario[i].getTerminos()[k]=vocabulario[i].getTerminos()[j];
		                   vocabulario[i].getTerminos()[j]=auxTerm;          
		             }
		        }
		    }
		}
		return vocabulario;
	}

	
	private String obtenerNombreTaxonomiaOTesauro(EstructuraVdexVO[] estructuras,String value ){
		boolean encontrado=false;
		if(logger.isDebugEnabled())logger.debug("Intentamos saber el label del identificador <"+value+">");
		String label="";
		for(int i=0;i< estructuras.length && !encontrado;i++){
			String identifier=estructuras[i].getVocabIdentifier();
			if(identifier.equals(value)){
				label=estructuras[i].getVocabName();
				encontrado=true;
			}
		}
		if(logger.isDebugEnabled())logger.debug("Devolvemos el label <"+label+">");
		return label;
	}

	/**
	 * Replanifica las tareas de modificación para que se vuelvan a ejecutar, las tareas replanificadas aparecerán
	 * en el listado de pendientes
	 * @param idModificaciones Identificadores de tareas de modificación que se desean reprogramar
	 * @return resultado de la replanificación de las tareas de modificación.
	 */
	protected Boolean handleReplanificarModificaciones(Long[] idModificaciones) throws Exception
	{
		Boolean salida=Boolean.TRUE;
		ArrayList<Modificacion> listaModificaciones = new ArrayList<Modificacion>();
    	for(int i=0;i<idModificaciones.length;i++) {
	    	try {
	    		logger.debug("Se replanifica la tarea "+idModificaciones[i]);
				//Modificamos el estado a CONFIGURADA (aparecerá en el listado de pendientes)
				Modificacion modificacion=getModificacionDao().load(idModificaciones[i]);
				if(logger.isDebugEnabled())logger.debug("Obtengo la modificacion");
				modificacion.setResultado(EstadosTarea.CONFIGURADA);
				listaModificaciones.add(modificacion);
				ResultadoModificacionCriteria criteria = new ResultadoModificacionCriteria();
	    		criteria.setIdModificacion(idModificaciones[i]);
	    		Collection<?> resultadosModificacion = getResultadoModificacionDao().obtenerResultadosModificacion(criteria);

	    		if(logger.isDebugEnabled())logger.debug("La longitud de los odes que recibimos es de "+resultadosModificacion.size());
	        	if(resultadosModificacion.size()>0) {
	        		Iterator<?> iter=resultadosModificacion.iterator();
	        		while(iter.hasNext()){
		        		ResultadoModificacion resultado = (ResultadoModificacion)iter.next();
		        		String pathTraza=resultado.getPathTraza();
		        		int indice=pathTraza.lastIndexOf("/");
		        		String path=pathTraza.substring(0, indice);
		        		File pathBorrar=new File(path);
		        		if(logger.isDebugEnabled())logger.debug("Borramos la carpeta <"+path+">");
	//	        		Eliminamos la carpeta taller/backup y report.log
		        		UtilesFicheros.eliminar(pathBorrar);
//						if(!pathBorrar.delete()) {
//							logger.warn("No se ha podido eliminar la carpeta " + path);
//						}	
	        		}
					
	        	}
	    		
	    		//eliminamos los resultados de la basa de datos
	    		getResultadoModificacionDao().remove(resultadosModificacion);
				
//				File path = new File(modificacion.getPath()+ "/" + getProperty("taller.folder"));
//				UtilesFicheros.eliminar(path);
//				if(!path.delete()) {
//					logger.warn("No se ha podido eliminar la carpeta " + modificacion.getPath()+ "/" + getProperty("taller.folder")+")");
//				}				
			} catch (Exception e) {
				logger.error("No se pudo replanificar la modificación "+idModificaciones[i]+" debido a "+e.getMessage());
				if(logger.isDebugEnabled()) logger.debug(e);
				// salida indica si hubo problemas con alguna de las entidades.
				// No se lanza excepcion para poder continuar con el resto.
				salida = Boolean.FALSE;
			}
    	}
    	getModificacionDao().update(listaModificaciones);
    	if(logger.isDebugEnabled()) logger.debug("Modificado lista de tareas de modificacion");
        return salida;
	}

	/**
	 * Pone a estado FINALIZADA las tareas de modificación que se pasan por parámetro y elimina de BD y de disco los backups de los odes que se modifican en esta tarea, de esta forma la tarea se puede volver a utilizar.
	 * @param idModificaciones Identificadores de tareas de modificación que se desean pasar a estado FINALIZADAS
	 * @return resultado de la replanificación de las tareas de modificación.
	 */
	protected Boolean handleFinalizarModificaciones(Long[] idModificaciones) throws Exception
	{
		Boolean salida=Boolean.TRUE;
//		ArrayList resultadoListaModificaciones = new ArrayList();
		ArrayList<Modificacion> listaModificaciones=new ArrayList<Modificacion>();
		Collection<?> resultadosModificacion = null;
//		ResultadoModificacionVO[] resultadosModificacionArray = null;
		Collection<ResultadoModificacion> resultadosModificados=new ArrayList<ResultadoModificacion>();
		for(int i=0;i<idModificaciones.length;i++) {
			boolean resultadoErroneo=false;
			boolean resultadoWarning=false;
	    	try {
	    		logger.info("Se finaliza la tarea <"+idModificaciones[i]+">");
	    		//Comprueba si hay resultados modificacion asociados a la tarea a la que se quiere borrar
	    		//En el caso de que hayan se borrará de BD el valor de PATH_BACKUP
	    		//También se borrará físicamente de disco
	    		ResultadoModificacionCriteria criteria = new ResultadoModificacionCriteria();
	    		criteria.setIdModificacion(idModificaciones[i]);
	    		resultadosModificacion = getResultadoModificacionDao().obtenerResultadosModificacion(criteria);
	    		
	    		
	    		if(logger.isDebugEnabled())logger.debug("La longitud de los odes que recibimos es de <"+resultadosModificacion.size()+">");
	    		
	        	if(resultadosModificacion.size()>0) {
	        		Iterator<?> iter=resultadosModificacion.iterator();
	        		while(iter.hasNext()){
		        		ResultadoModificacion resultado = (ResultadoModificacion)iter.next();
		        		File path=new File(resultado.getPathBackup());
		        		if(logger.isDebugEnabled())logger.debug("path a borrar <"+path+">");
		        		UtilesFicheros.eliminar(path);
//						if(!path.delete()) {
//							logger.warn("No se ha podido eliminar la carpeta " + resultadosModificacionArray[j].getPathBackup() + " ("+resultadosModificacionArray[j].getPathBackup()+")");
//						}
		        		resultado.setPathBackup(null);
		        		resultadosModificados.add(resultado);
		        		String resultadoTarea=resultado.getStatus().getValue();
		        		if(!resultadoErroneo && resultadoTarea.equals(EstadosTarea.ERROR.getValue())){
		        			resultadoErroneo=true;
		        		}
		        		if(!resultadoWarning && resultadoTarea.equals(EstadosTarea.WARNING.getValue())){
		        			logger.debug("Hemos encontrado un resultado en estado WARNING");
		        			resultadoWarning=true;
		        		}
		        		
	        		}
	        		if(logger.isDebugEnabled())logger.debug("actualizamos la base de datos de resultados");
	        		getResultadoModificacionDao().update(resultadosModificados);
	        	}
	    		
//	    		resultadosModificacionArray = (ResultadoModificacionVO[])resultadosModificacion.toArray(new ResultadoModificacionVO[]{});
//	    		resultadoListaModificaciones = new ArrayList();
//	    		logger.info("resultadosModificacionArray longitud "+resultadosModificacionArray.length);
//	    		for(int j=0;j<resultadosModificacionArray.length;j++)
//	    		{
//	    			File path = new File(resultadosModificacionArray[j].getPathBackup());
//					UtilesFicheros.eliminar(path);
////					if(!path.delete()) {
////						logger.warn("No se ha podido eliminar la carpeta " + resultadosModificacionArray[j].getPathBackup() + " ("+resultadosModificacionArray[j].getPathBackup()+")");
////					}
//	    			resultadosModificacionArray[j].setPathBackup(null);
////	    			EstadosTarea status=null;
////	    			status=EstadosTarea.FINALIZADA;;
////	    			resultadosModificacionArray[j].setStatus(status);
//	    			
//	    			resultadoListaModificaciones.add(resultadosModificacionArray[j]);
//	    			
//	    			
//	    		}
	    		
//	    		getResultadoModificacionDao().update(listaModificaciones);
//	    		if(logger.isDebugEnabled()) logger.debug("Eliminado el path backup de la tarea de modificacion "+idModificaciones[i]);
	    		
	    	} catch (Exception e) {
				logger.error("No se pudo finalizar modificación "+idModificaciones[i]+" debido a "+e.getMessage());
				if(logger.isDebugEnabled()) logger.debug(e);
				// salida indica si hubo problemas con alguna de las entidades.
				// No se lanza excepcion para poder continuar con el resto.
				salida = Boolean.FALSE;
			}
//	    	Modificamos el estado a CONFIGURADA (aparecerá en el listado de pendientes)
	    	Modificacion modificacion=getModificacionDao().load(idModificaciones[i]);
	    	if(resultadoErroneo){
				modificacion.setResultado(EstadosTarea.ERROR);
	    	}else{	
	    		if(resultadoWarning){
	    			modificacion.setResultado(EstadosTarea.WARNING);
	    		}else{
	    			modificacion.setResultado(EstadosTarea.FINALIZADA);
	    		}
	    	}
			listaModificaciones.add(modificacion);
			getModificacionDao().update(listaModificaciones);
	    	if(logger.isDebugEnabled()) logger.debug("Modificado lista de tareas de modificacion");
    	}
		if(logger.isDebugEnabled()) logger.debug("Se han finalizado todas las tareas de modificacion");
		
		 return salida;
		
	}

	/**
	 * Devuelve el contenido de un ode modificado por una tarea de modificación.
	 * @param idModificacion Identificadores de tareas de modificación que se desean pasar a estado FINALIZADAS
	 * @param idOde Identificador del ode
	 * @param titulo El titulo del ode
	 * @return resultado de la replanificación de las tareas de modificación.
	 */
	protected DataHandler handleDescargarModificacion(Long idModificacion,String idOde,String titulo) throws Exception
	{
		//Se creará un zip dentro de la carpeta uploads/modificador/temp/+path de la modificación
		//Durante el reinicio del modificador se eliminará esa carpeta temporal
		//La ruta de la carpeta temporal se localizará en el fichero modificador.properties
		//Se guardarán los zips en base.path+zip.folder+ModificacionVO.path, del path del modificador se recogerá a partir de uploads/modificador
		//Los zips se crearán únicamente si no se han descargado antes por lo que antes de su creación habrá que comprobar si ya existen esos zips
		
		

		
		//Cargamos la tarea de modificación cuyo identificador se pasa por parámetro

//		Modificacion modificacion=getModificacionDao().load(idModificacion);
		
		String pathTaller=null;
		File carpetaZipFile=null;
		File zipFile=null;
		String pathZip="";
		//Localizamos la carpeta donde se guardará el zip
//		String[] subDirPath = modificacion.getPath().split("/");
//		if(logger.isDebugEnabled()) logger.debug("subDirPath "+subDirPath[2]);
//		File carpetaZipFile=new File(getProperty("base.path")+getProperty("zip.folder")+ subDirPath[2] +"/"+idOde+"/"+titulo+".zip");
		
		DataHandler dataHandler=null;
		ResultadoModificacionCriteria criteria = new ResultadoModificacionCriteria();
    	criteria.setIdModificacion(idModificacion);
    	if(logger.isDebugEnabled())logger.debug("idModificacion <"+idModificacion+ "> y idOde <"+idOde+">");
    	Long id=new Long(idOde);

    	criteria.setId(id);
    	Collection<?> resultados = getResultadoModificacionDao().obtenerResultadosModificacion(criteria);
    	if(logger.isDebugEnabled())logger.debug("La longitud de los odes que recibimos es de "+resultados.size());
    	if(resultados.size()>0) {
    		ResultadoModificacion resultado = (ResultadoModificacion)resultados.iterator().next();
    		String pathTraza=resultado.getPathTraza();
    		int indice=pathTraza.lastIndexOf("/");
    		String path=pathTraza.substring(0, indice);
			pathTaller=path+"/"+getProperty("taller.folder");						
			logger.debug("El path del taller es <"+pathTaller+">");
			String[] pathSplit=path.split(getProperty(BASE_PATH));
			
			carpetaZipFile=new File(getProperty(BASE_PATH)+getProperty(ZIP_FOLDER)+pathSplit[1]);
			
			zipFile=new File(getProperty(BASE_PATH)+getProperty(ZIP_FOLDER)+pathSplit[1]+"/"+titulo+".zip");
			if(logger.isDebugEnabled()) logger.debug("carpeta donde se dejarán los zip <"+carpetaZipFile+">");
			if(logger.isDebugEnabled()) logger.debug("nombre del zip <"+titulo+">");
			if(!carpetaZipFile.exists())
			{
				if(logger.isDebugEnabled()) logger.debug("No existe el zip, comprimo la carpeta taller.");
				boolean creado=carpetaZipFile.mkdirs();
				if(!creado){
					logger.error("Error al crear la carpeta "+carpetaZipFile);
				}else{
					if(!carpetaZipFile.canWrite()){
						logger.error("La carpeta <"+carpetaZipFile+"> no tiene permisos de escritura.");
					}
				}
			}else{
				if(!carpetaZipFile.canWrite()){
					logger.error("La carpeta <"+carpetaZipFile+"> no tiene permisos de escritura.");
				}
			}
			try{
				pathZip= zipFile.getAbsolutePath().replace('\\', '/');
				//Obtenermos el path taller, que contiene lo que se va a comprimir
				if(logger.isDebugEnabled()) logger.debug("Vamos a generar el zip en el directorio = <" + pathZip+">");
				//El primer argumento será dónde se guarde el fichero zip y el segundo la carpeta de la que se recogerán los ficheros para crear el zip
				this.getZipDao().comprimir(pathZip,pathTaller);
			}catch(Exception e){
				logger.error("Error al comprimir el zip en la carpeta "+pathZip+ " desde "+pathTaller);
				if(logger.isDebugEnabled())logger.debug(e);
			}
			
			
	}
		try {
			
			FileDataSource fileDS = new FileDataSource(zipFile);
			dataHandler = new DataHandler(fileDS);
			
//			InputStream in = dataHandler.getInputStream();
//			if(logger.isDebugEnabled())logger.debug("recuperando el fichero "+ );
//			byte[] buffer = new byte[BUFFER_SIZE];
//			int count;
//			
//			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
//			{
//				if(logger.isDebugEnabled()) logger.debug("Obtenido el datahandler"+out.write(buffer, 0, count));
//			}
			
			
			if(logger.isDebugEnabled()) logger.debug("Obtenido el datahandler <"+dataHandler+">");
		}catch(Exception e)
		{
			logger.error("Error en la descarga - ",e);
		}
		return dataHandler;
	}

	/**
	 * Devuelve un listado con las tareas ejecutadas y las pendientes de finalización.
	 * @return listado de tareas ejecutadas y pendientes de finalización.
	 */
	protected ListadoTareasModificacionVO handleObtenerTodasModificaciones() throws Exception
	{
		ModificacionVO[] ejecutadas = null;
		ModificacionVO[] pendientes = null;
		EstadosTarea[] estados = null; 
		ListadoTareasModificacionVO resultado = null;
		
//		estados = new EstadosTarea[4];
//    	estados[0]=EstadosTarea.FINALIZADA;
//    	estados[1]=EstadosTarea.ERROR;
//    	estados[2]=EstadosTarea.WARNING;
//    	estados[3]=EstadosTarea.RESTAURADA;
//    	ejecutadas = obtenerModificacionesPorEstado(estados);

    	//Obtenemos las tareas ejecutadas
    	ejecutadas = handleObtenerModificacionesEjecutadas();
    	
    	//Obtenemos las tareas pendientes de finalización
    	
    	estados = new EstadosTarea[1];
    	estados[0]=EstadosTarea.PENDIENTE;
    	pendientes = obtenerModificacionesPorEstado(estados);
    	
    	resultado = new ListadoTareasModificacionVO();
    	resultado.setEjecutadas(ejecutadas);
    	resultado.setPendientes(pendientes);
		return resultado;
	}
	
	private String getProperty(String key) throws Exception
	{
		InputStream is = null;
    	Properties prop = new Properties();
    	String resultado = null;
		try {
			is = this.getClass().getResourceAsStream(MODIFICADOR_PROPERTIES);
			prop.load(is);
			resultado = prop.getProperty(key);
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
		}
		return resultado;
	}
	
	
	/**
	 * Elimina los zip de las descargas que se encuentran almacenados en la carpeta temporal (/uploads/modificador/temp/).
	*/
	private void limpiarTemporales()
	throws Exception
	{
		// Eliminamos los zips de las descargas que se encuentren dentro de la carpeta uploads/modificador/temp 
		logger.debug("Limpiar temporales");
		File carpetaZip = new File( this.getProperty(BASE_PATH) + this.getProperty(ZIP_FOLDER));
		if(logger.isDebugEnabled()) logger.debug("Carpeta con los ficheros zip temporales <"+carpetaZip+">");
		UtilesFicheros.eliminar(carpetaZip);
		if(logger.isDebugEnabled()) logger.debug("Eliminados zips temporales.");

	}

	/**
	 * Obtiene un listado de resultados de la modificación especificada
	 * filtrados por el / los estados que se le pasan como parámetros. Los
	 * estados posibles son FINALIZADA, ERROR, RESTAURADA y WARNING.
	 * 
	 * @param idModificacion Identificador de la tarea de modificación cuyos resultados se desean obtener.
	 * @param estados Array de estados que se desean filtrar.
	 * @return Array de VOs con los registros encontrados.
	 */
	@Override
	protected ResultadoModificacionVO[] handleObtenerModificacionesPorEstado(Long idModificacion, String[] estados) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Obtenemos las modificaciones por estado");
		ResultadoModificacionPorEstadoCriteria criteria=new ResultadoModificacionPorEstadoCriteria();
		Collection<?> coleccion=new ArrayList<Object>();
		for(int i=0;i<estados.length;i++){
			EstadosTarea estadoTarea = null;
			criteria.setIdModificacion(idModificacion);
			if(estados[i].equals("FINALIZADA"))
				estadoTarea=EstadosTarea.FINALIZADA;
			else if(estados[i].equals("ERROR"))
				estadoTarea=EstadosTarea.ERROR;
			else if(estados[i].equals("RESTAURADA"))
				estadoTarea=EstadosTarea.RESTAURADA;
			else
				estadoTarea=EstadosTarea.WARNING;
			if(logger.isDebugEnabled())logger.debug("Tenemos que recoger las modificaciones de identificador <"+idModificacion+"> y estado <"+estados[i]+">");
			criteria.setStatus(estadoTarea);
			coleccion.addAll(getResultadoModificacionDao().obtenerResultadosModificacionPorEstado(ResultadoModificacionDao.TRANSFORM_RESULTADOMODIFICACIONVO,criteria));
		}
		if(logger.isDebugEnabled())logger.debug("Hemos recogido las modificaciones de longitud " +coleccion.size());
		ResultadoModificacionVO[] arrayModificados=coleccion.toArray(new ResultadoModificacionVO[]{});
		Boolean descargable=this.esDestrutiva(arrayModificados);
	
		for(int i=0;i< arrayModificados.length;i++){
			arrayModificados[i].setEsDescargable(descargable);
		}
			
    	return arrayModificados;
			
	}

	private Boolean esDestrutiva(ResultadoModificacionVO[] arrayModificados) throws Exception{
		Boolean descargable=false;
		if(arrayModificados!=null && arrayModificados.length>0){
			ResultadoModificacionVO primerModificado = arrayModificados[0];
			String pathTraza=primerModificado.getPathTraza();
			int indice = pathTraza.lastIndexOf("/");
			String path=pathTraza.substring(0, indice);
			String pathTaller=path+"/"+getProperty(TALLER_FOLDER);
			if(logger.isDebugEnabled())logger.debug("El path del taller es <"+pathTaller+">");
			File archivo=new File(pathTaller);
			if(archivo.exists()){
				descargable=true;
			}
		}
		return descargable;
	}

	/**
	 * Obtiene un listado con informacion de los ODEs publicados por la carga de
	 * objetos deseada. En caso de que en la carga no se publicase con exito
	 * ningun objeto, se devuelve un array vacio.
	 * 
	 * @param idTarea
	 *            Identificador de la tarea de carga de ODEs del planificador.
	 *
	 * @return String[] con los identificadores de los ODES publicados en carga
	 *  
	 * @see es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase#handleObtenerOdesCargaMasiva(java.lang.Long)
	 */
	@Override
	protected OdeCargaMasivaVO[] handleObtenerOdesCargaMasiva(Long idTarea)
			throws Exception {
		
		OdeCargaMasivaVO[] resultado = null;
		
		try{
			// TODO planificador vacante rellenar el título
			logger.debug("Llamamos al servicio planificador con idTarea = <"+idTarea+">");
			OdeCargaVO[] odesCarga = this.getSrvPlanificadorService().obtenerODEsPublicadosEnCarga(idTarea);
			logger.debug("Recuperamos OdesCarga, con <"+odesCarga.length+"> elementos");
			resultado = new OdeCargaMasivaVO[odesCarga.length];
			String[] ids = new String[odesCarga.length];
			
			for (int i = 0; i < ids.length; i++) {
				if (odesCarga[i] != null) {
					logger.debug("OdesCarga [" + i + "] es " + odesCarga[i].toString()+">");
					ids[i] = odesCarga[i].getId();
				} else {
					logger.warn("odescarga " + i + " es NULL!!");
				}
			}
			logger.debug("Los IDs son <"+Arrays.toString(ids)+">");
			LocalizadorVO[] loc = getSrvLocalizadorService().buscarLocalizadoresPorId(ids);
			OdeCargaVO[] odeCarga = this.getSrvPlanificadorService().obtenerODEsPublicadosEnCarga(idTarea);
			
			if(logger.isDebugEnabled())logger.debug("El número de odes es: "+resultado.length);
			for (int i = 0; i < resultado.length; i++){
				
				resultado[i]= new OdeCargaMasivaVO();
				resultado[i].setIdOde(ids[i]);
				resultado[i].setLocalizador(loc[i].getPath());
				resultado[i].setTitulo(odeCarga[i].getTitulo());//obtener el título a mostrar en resultados
				resultado[i].setUrl(loc[i].getUrl());
			}
			
		}catch (Exception e){
			
			logger.error("Error en la obtención de los identificadores de los ODES publicados en carga - ",e);
		}
		return resultado;
	}

	/**
	 * Obtiene del planificador un listado de tareas de carga de ODEs. Los
	 * resultados obtenidos del planificador se mapean a un objeto de la clase
	 * es.pode.modificador.negocio.servicio.TareaCargaMasivaVO.
	 * 
	 * @return es.pode.modificador.negocio.servicio.OdeCargaMasivaVO[]
	 * @see es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase#handleObtenerOdesCargaMasiva(java.lang.Long)
	 */
	@Override
	protected TareaCargaMasivaVO[] handleObtenerTareasCargaMasiva()
			throws Exception {
		
		TareaEjecutadaExplotacionVO[] result = null;
		TareaCargaMasivaVO[] resultado = null;
		
		try{

			if(logger.isDebugEnabled())logger.debug("Obtenemos las tareas de carga masiva ejecutadas desde el planificador");
			result = this.getSrvPlanificadorService().obtenerTareasCargaEjecutadas();
			
		}catch (Exception e){
			
			logger.error("Error en la obtención de las tareas de carga masiva jecutadas desde el planificador - ",e);
		}
				
		if (result != null){
			
			if(logger.isDebugEnabled())logger.debug("El número de tareas de carga ejecutadas es: "+ result.length);
			resultado = new TareaCargaMasivaVO[result.length];
			for (int i = 0; i < result.length; i++){
				//mapeamos de TareaEjecutadaExplotacionVO[] a TareaCargaMasivaVO[]
				resultado[i] = new TareaCargaMasivaVO();
				resultado[i].setId(result[i].getId());
				resultado[i].setDescripcion(result[i].getDescripcion());
				resultado[i].setDescripcionTarea(result[i].getDescripcionTarea());
				resultado[i].setNombreLote(result[i].getNombreLote());
				resultado[i].setFechaFin(result[i].getFechaFin());
			}
		}
		
		if(logger.isDebugEnabled())logger.debug("Devolvemos las tareas de carga masiva como un array de TareaCargaMasivaVO");
		return resultado;
	}
	
	
	
	
}
