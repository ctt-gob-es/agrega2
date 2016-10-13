// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.servicios;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada;
import es.pode.planificador.negocio.dominio.TareaEjecutada;
import es.pode.planificador.negocio.dominio.TareaEjecutadaDao;
import es.pode.planificador.negocio.dominio.TrabajosEjecutadosDesdeHastaCriteria;


/**
 * @see es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService
 */

public class SrvAuditaPlanificadorServiceImpl
    extends es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceBase
{

	private static Logger log = Logger.getLogger(SrvAuditaPlanificadorServiceImpl.class);
	
	/** 
     * Se recuperan los trabajos ejecutados entre dos fechas
     * @param parametros Fechas entre las que recuperar los informes
     * @return     TareaEjecutadaVO[]  Array con las tareas ejecutadas entre las dos fechas
     * @throws Exception
     */

	protected TareaEjecutadaVO[] handleObtenerTrabajosEjecutadosDesdeHasta(ParametrosVO parametros) 
		throws Exception 
	{
    	TareaEjecutadaVO[] res = null;
    	
    	try {
    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();     		
        	TrabajosEjecutadosDesdeHastaCriteria criterios = new TrabajosEjecutadosDesdeHastaCriteria();
    		
    		criterios.setFechaDesde(parametros.getFechaDesde());
    		criterios.setFechaHasta(parametros.getFechaHasta());
        	
    		List listaTareaEjecutadas = tareaEjecutadaDao.buscarTrabajosDesdeHasta(
    				tareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO, criterios);
    		    		
    		res = (TareaEjecutadaVO[])listaTareaEjecutadas.toArray(new TareaEjecutadaVO[0]);    		    		
		}
    	catch (Exception e) {
    		log.error("Error: " + e);
    		throw e;
    	}
    	
        return res;
	}

	/**
     * Método devolverá la información que se va a mostrar en el informe
     * @param idTarea de la Tarea
     * @return InformeCargaVO VO con información de los objetos cargados
     * @throws Exception
     */

	protected InformeCargaVO handleObtenerInformeCarga(Long idTarea) throws Exception
	{
		logger.debug("Estamos entrando en ObtenerInformeCarga con identificador "+ idTarea);
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			log.info("Recuperando los datos del informe de carga masiva");
			int i=0;
			InformeCargaVO carga=new InformeCargaVO();
			TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
			
			//Se recogen de la BBDD los datos de la tarea a partir del idTarea
			TareaEjecutada tareaEjecutada = tareaEjecutadaDao.load(idTarea);
			
			//Recojo en una coleccion la lista con todas las tareas con ese idTarea
			
			
			Collection<RegistroTareaCargaEjecutada>  registros = tareaEjecutada.getRegistroTareaCargaEjecutadas();
			carga.setDescripcionCarga(tareaEjecutada.getDescripcionTarea());
			carga.setNombreLote(tareaEjecutada.getNombreLote());
			carga.setNombreTarea(tareaEjecutada.getDescripcion());
			carga.setPathCarga(tareaEjecutada.getPathCarga());
			carga.setPathCargaKO(tareaEjecutada.getPathCargaKO());
			carga.setPathCargaOK(tareaEjecutada.getPathCargaOK());
			RegistroCargaVO[] cargaMasivaVOs = new RegistroCargaVO[registros.size()];
//			Recorro la coleccion e inserto cada tarea en un array de RegistroCargaVO[]
			Iterator iterator = registros.iterator();
			
    	    while (iterator.hasNext()) {
    	    	RegistroCargaVO registro=new RegistroCargaVO();
    	    	RegistroTareaCargaEjecutada cargaVO = (RegistroTareaCargaEjecutada) iterator.next();
    	    	registro.setDescripcion(cargaVO.getDescripcion());
    	    	registro.setEstado(cargaVO.getEstado());
    	    	registro.setOdesSimilares(cargaVO.getOdesSimilares());
    	    	String fecha = "";
    	    	fecha = format.format((cargaVO.getFecha()).getTime());
    	    	registro.setFecha(fecha);
    	    	registro.setFormato(cargaVO.getFormato());
    	    	registro.setId_mec(cargaVO.getId_mec());
    	    	registro.setIdioma(cargaVO.getIdioma());
    	    	registro.setNivelAgregacion(cargaVO.getNivelAgregacion());
    	    	registro.setNombreZip(cargaVO.getNombreZip());
    	    	registro.setPathZip(cargaVO.getPathOde());
    	    	registro.setSobrescrito(cargaVO.getSobrescrito());
    	    	registro.setTitulo(cargaVO.getTitulo());
    	    	registro.setRutaTaxonomica(cargaVO.getRutaTaxonomica());
    	    	registro.setOdesSimilares(cargaVO.getOdesSimilares());
    			cargaMasivaVOs[i]=registro;
    			i++;
    	    }
    	    carga.setRegistroCarga(cargaMasivaVOs);
    	    
    	    log.info("Informe de carga masiva recuperado");
    	    return carga;   		
    	} catch (Exception e) {
    		log.error("Error: " + e);
    		throw e;
    	}
	}

}