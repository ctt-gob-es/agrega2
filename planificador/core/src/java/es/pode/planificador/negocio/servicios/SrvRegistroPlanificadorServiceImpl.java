// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.servicios;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.pode.planificador.negocio.dominio.IdentificadoresCarpetasCriteria;
import es.pode.planificador.negocio.dominio.OdesPorIdMecYTareaCriteria;
import es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada;
import es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDao;
import es.pode.planificador.negocio.dominio.RegistroTareaEjecutada;
import es.pode.planificador.negocio.dominio.TareaEjecutada;
import es.pode.planificador.negocio.dominio.TareaEjecutadaDao;
import es.pode.planificador.negocio.dominio.TareaTrabajoFFinCriteria;
import es.pode.soporte.constantes.ConstantesAgrega;

/**
 * Servicios asociados a la planificación de las tareas
 * 
 * 	Trigger: Es la programación de la tarea.
 *  Trabajo: Es la tarea planificada para ejecutarse.
 *  
 *  Una tarea engloba la programación (trigger) y el trabajo asociado.
 */


public class SrvRegistroPlanificadorServiceImpl
    extends es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceBase
{

	private static Logger log = Logger.getLogger(SrvRegistroPlanificadorServiceImpl.class);
	public static final String FILE_NAME_PROPERTIES = "/planificador.properties";
	private static Properties props = null;
    
	/**
     * Registra en la tabla de Tareaejecutada la tarea ejecuta
     * @param trabajo Trabajo que se va a registrar
     * @return identificador de la tarea que se registra
     * @throws Exception
     */

    protected Long handleRegistrarTrabajo(TareaEjecutadaVO trabajo)
        throws java.lang.Exception
    {
		TareaEjecutada tareaCreada = null;

		try
		{
			TareaEjecutada tareaEjecutadaEntity = this.getTareaEjecutadaDao().fromTareaEjecutadaVO(trabajo);
			
			if (trabajo.getFechaInicio() == null)
				tareaEjecutadaEntity.setFechaInicio(new GregorianCalendar());
			else
				tareaEjecutadaEntity.setFechaInicio(trabajo.getFechaInicio());
			
			if (trabajo.getDescripcion() != null && !trabajo.getDescripcion().equals(""))
				tareaEjecutadaEntity.setDescripcion(trabajo.getDescripcion());
			else
				tareaEjecutadaEntity.setDescripcion(" ");
			
			if(trabajo.getUsuario() != null)
				tareaEjecutadaEntity.setUsuario(trabajo.getUsuario());
			
			if (trabajo.getGrupoTrabajo() != null && !trabajo.getGrupoTrabajo().equals(""))
				tareaEjecutadaEntity.setGrupoTrabajo(trabajo.getGrupoTrabajo());
			else
			{
				log.error("Error: No se puede registrar la tarea porque el grupo de trabajo es nulo");
				return null;
			}
			
			if (trabajo.getTrabajo() != null && !trabajo.getTrabajo().equals(""))
				tareaEjecutadaEntity.setTrabajo(trabajo.getTrabajo());
			else
			{
				log.error("Error: No se puede registrar la tarea porque el trabajo es nulo");
				return null;
			}
			//Nuevos atributos que no serán nulos en el caso de que la  tarea sea la carga masiva
			tareaEjecutadaEntity.setDescripcionTarea(trabajo.getDescripcionTarea());
			tareaEjecutadaEntity.setNombreLote(trabajo.getNombreLote());
			tareaEjecutadaEntity.setPathCarga(trabajo.getPathCarga());
			tareaEjecutadaEntity.setTipoTarea(trabajo.getTipoTarea());
			tareaEjecutadaEntity.setPathCargaKO(trabajo.getPathCargaKO());
			tareaEjecutadaEntity.setPathCargaOK(trabajo.getPathCargaOK());
			log("Tarea ejecutada: " + tareaEjecutadaEntity);
			tareaCreada = this.getTareaEjecutadaDao().create(tareaEjecutadaEntity);			
		}
		catch (Exception e)
		{
			log.error(e);
    		RegistrarTrabajoException excepcion = new RegistrarTrabajoException("Error: No se ha podido registrar el trabajo. ", e);    		
    		throw excepcion;
		}

		return tareaCreada.getId();    
    }

    
    /**
     * Registra en la tabla correspodiente si ha ido o no bien la carga de las sub-tareas que estan
     * asociadas a una tarea en concreto 
     * @param trabajoHijo El sub-trabajo de un trabajo concreto
     * @return identificador del registro
     * @throws Exception
     */

    protected Long handleRegistrarTrabajoHijo(RegistroTareaEjecutadaVO trabajoHijo)
        throws java.lang.Exception
    {    	
    	RegistroTareaEjecutada registroEntity = null;
    	TareaEjecutada tareaEjecutadaEntity = null;
    	
		try
		{			
			registroEntity = this.getRegistroTareaEjecutadaDao().fromRegistroTareaEjecutadaVO(trabajoHijo);
			log("Tarea derivada: " + tareaEjecutadaEntity);
			this.getRegistroTareaEjecutadaDao().create(registroEntity);
		}
		catch (Exception e)
		{
			log.error("Error: No se pudo realizar el registro del trabajo dependiente. " + e);
			throw e;
		}

    	return registroEntity.getId();
    }
    
    /**
     * Se registra la fecha en la que se ha finalizado la ejecucion de un trabajo 
     * y se comprueba si la ejecución ha sido o no correcta
     * @param trabajoEjecutado Un TareaEjecutadaVO
     * @return identificador del trabajo
     * @throws Exception
     */

	protected Long handleRegistrarTrabajoFechaFin(TareaEjecutadaVO trabajoEjecutado) 
			throws Exception 
	{
		TareaEjecutada tareaEntity = null;
		
    	try
    	{
    		tareaEntity= this.getTareaEjecutadaDao().load(trabajoEjecutado.getId());
    		tareaEntity.setFechaFin(trabajoEjecutado.getFechaFin());
    		tareaEntity.setEstado(trabajoEjecutado.getEstado());
    		tareaEntity.setDescripcionTarea(trabajoEjecutado.getDescripcionTarea());
    		tareaEntity.setNombreLote(trabajoEjecutado.getNombreLote());
    		tareaEntity.setPathCarga(trabajoEjecutado.getPathCarga());
    		tareaEntity.setTipoTarea(trabajoEjecutado.getTipoTarea());
    		tareaEntity.setPathCargaKO(trabajoEjecutado.getPathCargaKO());
    		tareaEntity.setPathCargaOK(trabajoEjecutado.getPathCargaOK());
    		log("Tarea actualizada: " + tareaEntity);
    		this.getTareaEjecutadaDao().update(tareaEntity);
		}
    	catch (Exception e)
    	{
    		log.error(e);
    		throw e;
    	}
    	
		return tareaEntity.getId();
	}
	
	
	private void log (Object obj)
	{
		if (log.isDebugEnabled())
			log.debug(obj);
	}

	/**
	 * Realiza la operacion de cambio de estado a INTERRUMPIDO de los trabajos 
	 * que no finalizaron cuando el servidor de aplicaciones se ha parado inesperadamente
	 * @return filasModificadas Las filas modificadas
	 * @throws Exception
	*/
	protected Long handleRegistrarTrabajoInterrumpido() throws Exception 
	{
		List listaTareas = null;
		Long filasModificadas = null;
		Collection<TareaEjecutada> listaTareaEntity = new ArrayList<TareaEjecutada>();
		
		try
		{
    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
    		TareaTrabajoFFinCriteria criterios = new TareaTrabajoFFinCriteria();
    		criterios.setFechaFin(null);
    		
    		listaTareas = tareaEjecutadaDao.buscarTareaByTrabajoAndFFin(
    				tareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO, criterios);

			Iterator it = listaTareas.iterator();
	    		
	    	while (it.hasNext())
	    	{
    			TareaEjecutadaVO tareaVO = (TareaEjecutadaVO) it.next();	
				TareaEjecutada tarea = tareaEjecutadaDao.load(tareaVO.getId());
    			tarea.setFechaFin(new GregorianCalendar());
    			tarea.setEstado(ConstantesAgrega.TRABAJO_INTERRUMPIDO);
				listaTareaEntity.add(tarea);				
			}
	    	
	    	log("Tareas interrumpidas: " + listaTareaEntity.size());   	
	    	tareaEjecutadaDao.update(listaTareaEntity);
		}
		catch (Exception e)
		{
			log.error(e);
			throw e;
		}
		
		filasModificadas = new Long(listaTareaEntity.size());
		
		return filasModificadas;
	}


	/**
	 * @param registroTareaCarga
	 * @return Long
	 * @throws Exception
	 */
	protected Long handleRegistroTrabajoTareaCarga(RegistroTareaCargaEjecutadaVO[] registroTareaCarga) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	 /**
     * Registra en la tabla correspodiente si ha ido o no bien la carga de las sub-tareas que estan
     * asociadas a una tarea de carga en concreto 
     * @param trabajoHijoCarga El sub-trabajo de un trabajo de carga concreto
     * @return filasModificadas El número de filas modificadas
     * @throws Exception
     */
	protected Long handleRegistrarTrabajoHijoCarga(RegistroTareaCargaEjecutadaVO[] trabajoHijoCarga) throws Exception {
		RegistroTareaCargaEjecutada registroTareaEntity = null;
    	TareaEjecutada tareaEjecutadaEntity = null;
    	Collection<RegistroTareaCargaEjecutada> coleccionEntyties=new ArrayList<RegistroTareaCargaEjecutada>();
    	
		try
		{			
			
			for(int i=0;i<trabajoHijoCarga.length;i++){
				registroTareaEntity = this.getRegistroTareaCargaEjecutadaDao().fromRegistroTareaCargaEjecutadaVO(trabajoHijoCarga[i]);
				log("Tarea derivada: " + tareaEjecutadaEntity);
				coleccionEntyties.add(registroTareaEntity);
			}
			this.getRegistroTareaCargaEjecutadaDao().create(coleccionEntyties);
			
		}
		catch (Exception e)
		{
			log.error("Error: No se pudo realizar el registro del trabajo de carga dependiente. " + e);
			throw e;
		}
		Long filasModificadas = new Long(coleccionEntyties.size());
    	return filasModificadas;
	}


	 /**
     * Obtiene los registros de los odes que siguen publicados y que tengan rutas
     * @param idTarea Identificador de la tarea
     * @return List Devuelve una lista de odes que tengan rutas asociadas
     * @throws Exception
     */
	protected Long handleObtenerRegistrosConRutas(Long idTarea)
			throws Exception {
		Long listaRegistros=null;
		try{
			RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
			logger.info("Hacemos la llamada con el criteria para el identificador "+idTarea);
			listaRegistros=registroTareaCarga.buscarRegistrosConRuta(idTarea);
			logger.info("Recogemos una lista con "+listaRegistros+" carpetas");
		}catch (Exception e) {
			logger.error("Error al recuperar los registros "+e);
		}
		return listaRegistros;
	}


	/**
     * Obtiene los registros de los odes que siguen publicados y en grupos de pathOdes
     * @param idTarea Identificador de la tarea
     * @return InformacionCargaVO[] Devuelve el VO relleno de los datos correspondientes al registro agrupado por lo pahtOde distintos
     * @throws Exception
     */
	protected InformacionCargaVO[] handleObtenerCarpetasDeRegistro(Long idTarea)
			throws Exception {
		InformacionCargaVO[] informacion=null;

		try{
			RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
			logger.info("Hacemos la llamada para el identificador "+idTarea);
			List listaCarpetas=registroTareaCarga.buscarCarpetasRegistro(idTarea);
			logger.info("Recogemos "+listaCarpetas.size()+" carpetas distintas");
			Iterator i=listaCarpetas.iterator();
			int k=0;
			informacion=new InformacionCargaVO[listaCarpetas.size()];
			ArrayList<InformacionCargaVO> listaInformaciones=new ArrayList<InformacionCargaVO>();
			ArrayList<String> listaIdent=new ArrayList<String>();
			while(i.hasNext()){
				String pathOde =(String) i.next();
				if(pathOde==null || (pathOde!=null && pathOde.equals(""))){
					IdentificadoresCarpetasCriteria criteria=new IdentificadoresCarpetasCriteria();
					criteria.setIdTarea(idTarea);
					criteria.setPathOde(pathOde);
					criteria.setEstado("OK");
//					criteria.setPathOdeDespublicado(Boolean.FALSE);
					logger.info("Obtenemos la lista de identificadores que son parte de la carpeta "+pathOde);
					List listaIdentificadores =registroTareaCarga.obtenerIdentificadoresOdes(registroTareaCarga.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, criteria);
					if(logger.isDebugEnabled()) logger.debug("Obtenemos "+listaIdentificadores.size()+" odes para la carpeta "+pathOde);
					for(int j=0;j<listaIdentificadores.size();j++){
						listaIdent.add(((RegistroTareaCargaEjecutadaVO)listaIdentificadores.get(j)).getId_mec());
					}

				}else{
					InformacionCargaVO infor=new InformacionCargaVO();
					infor.setPathOde(pathOde);
					IdentificadoresCarpetasCriteria criteria=new IdentificadoresCarpetasCriteria();
					criteria.setIdTarea(idTarea);
					criteria.setPathOde(pathOde);
					criteria.setEstado("OK");
//					criteria.setPathOdeDespublicado(Boolean.FALSE);
					logger.info("Obtenemos la lista de identificadores que son parte de la carpeta "+pathOde);
					List listaIdentificadores =registroTareaCarga.obtenerIdentificadoresOdes(registroTareaCarga.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, criteria);
					if(logger.isDebugEnabled()) logger.debug("Obtenemos "+listaIdentificadores.size()+" odes para la carpeta "+pathOde);
					String[] lista=new String[listaIdentificadores.size()];
					for(int j=0;j<listaIdentificadores.size();j++){
						lista[0]=((RegistroTareaCargaEjecutadaVO)listaIdentificadores.get(j)).getId_mec();
					}
					infor.setNumOdes((long)(listaIdentificadores.size()));
					infor.setIdentificadores(lista);
					listaInformaciones.add(infor);
					k++;
				}
				
			}if(listaIdent.size()>0){//Tenemos carpetas nulas y/o ""
				logger.info("Recogemos los identificadores que no son parte de ninguna carpeta  (null o '') y los metemos en un VO");
				InformacionCargaVO infor=new InformacionCargaVO();
				infor.setPathOde(SrvPlanificadorServiceImpl.getPropertyValue("sinCarpeta"));
				infor.setNumOdes((long)(listaIdent.size()));
				String[] listado=listaIdent.toArray(new String[0]);
				infor.setIdentificadores(listado);
				listaInformaciones.add(infor);
			}
			informacion=listaInformaciones.toArray(new InformacionCargaVO[0]);
			
			logger.info("Recogemos los valores "+Arrays.toString(informacion));
		}catch(Exception e){
			logger.error("Error al recuperar los datos de las carpetas de la tarea",e);
		}
		return informacion;
	}
	
//	private String getPropertyValue(String sKey) {
//		String sReturn = "";
//		try {
//			if (props == null) {
//				InputStream fIsSpringProperties = SrvPlanificadorServiceBase.class
//						.getResourceAsStream(FILE_NAME_PROPERTIES);
//				props = new java.util.Properties();
//				props.load(fIsSpringProperties);
//			}
//			sReturn = props.getProperty(sKey);
//			log("propiedad obtenida: " + sReturn.toString());
//		} catch (IOException e) {
//			log.warn("Excepcion intentando obtener propiedad [" + sKey
//					+ "] del fichero de propiedades del planificador[" + e.getCause() + "]");
//		}
//		// devolvemos la propiedad
//		return sReturn;
//	}
	
	/**
     * Registra en la tabla correspodiente si ha ido o no bien la carga de las sub-tareas que estan
     * asociadas a una tarea en concreto 
     * @param registros El sub-trabajo de un trabajo concreto
     * @return identificador del registro
     * @throws Exception
     */

    protected Long[] handleRegistrarTrabajoHijos(RegistroTareaEjecutadaVO[] registros)
        throws java.lang.Exception
    {    	
    	RegistroTareaEjecutada registroEntity = null;
    	TareaEjecutada tareaEjecutadaEntity = null;
    	Long[] identificadores=new Long[registros.length];
    	
		try
		{
			for(int i=0;i<registros.length;i++){
				registroEntity = this.getRegistroTareaEjecutadaDao().fromRegistroTareaEjecutadaVO(registros[i]);
				log("Tarea derivada: " + tareaEjecutadaEntity);
				RegistroTareaEjecutada vuelta = this.getRegistroTareaEjecutadaDao().create(registroEntity);
				identificadores[i]=vuelta.getId();
			}
		}
		catch (Exception e)
		{
			log.error("Error: No se pudo realizar el registro del trabajo dependiente. " + e);
			throw e;
		}

    	return identificadores;
    }


    /**
     * Actualiza en la tabla de registro_tarea_carga_ejectuada los odes que han sido despublicados insertando un true en la columna pathOdeDespublicado
     * @param identificadores El array de identificadores que se han despublicado
     * @param idTarea el identificador de la tarea al que pertenecen los odes despublicados
     * @throws Exception
     */
	protected void handleActualizarTrabajoTareaCarga(String[] identificadores, Long idTarea)
			throws Exception {
		try
		{
			OdesPorIdMecYTareaCriteria criteria=new OdesPorIdMecYTareaCriteria();
			criteria.setId_Tarea(idTarea);
			criteria.setIdentificadores(identificadores);
			Collection<RegistroTareaCargaEjecutada> coleccion=new ArrayList<RegistroTareaCargaEjecutada>();
			List<RegistroTareaCargaEjecutada> obtenidos=this.getRegistroTareaCargaEjecutadaDao().obtenerOdesPorIdMec(criteria);
			for(int i=0;i< obtenidos.size();i++){
				RegistroTareaCargaEjecutada registro= (obtenidos.get(i));
				registro.setPathOdeDespublicado(Boolean.TRUE);
				coleccion.add(registro);
			}
			this.getRegistroTareaCargaEjecutadaDao().update(coleccion);
		}catch (Exception e)
			{
				log.error("Error: No se pudo realizar la actualizacion de las entradas en la base de datos. " + e);
				throw e;
			}

	}

}