// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.logs.servicio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;



/**
 * @see es.pode.contenidos.negocio.logs.servicio.SrvLogService
 */

public class SrvLogServiceImpl extends
		es.pode.contenidos.negocio.logs.servicio.SrvLogServiceBase {

	private static Logger logger = Logger.getLogger(SrvLogServiceImpl.class);
	private static String pathLogsDir = null;
	static java.util.Properties pLogsProperties = null;


	/**
	 * Devuelve un listado con los archivos de log generados por el sistema
	 * 
	 * @return listadoLog FileVO[] con los datos de los archivos de log
	 * @throws Exception
	 */
	protected es.pode.contenidos.negocio.logs.servicio.FileVO[] handleListarFicherosLog()
			throws java.lang.Exception {

		File fpathLogsDir = new File(pathLogsDir);
		FileVO[] listadoLog = null;
		try{
			if (!fpathLogsDir.exists()) {
				logger.debug("El directorio <" + pathLogsDir + "> no existe");
			} else {
	
				// Recorremos el directorio donde estan los ficheros de Log
				File[] arrayList = fpathLogsDir.listFiles();
				List ficheros = new ArrayList();
				File ficheroTemp = null;
				
				//creamos una lista con los ficheros para ordenarlos por nombre
				List nombres = new ArrayList();
				// Por cada fichero encontrado creamos el VO con su nombre, el path
				// y el tamaño en KB
				for (int i = 0; i < arrayList.length; i++) {
					try{
						ficheroTemp = arrayList[i];
						FileVO fileVO = new FileVO();
						fileVO.setNombre(ficheroTemp.getName());
						fileVO.setSize(ficheroTemp.length() / 1024);
						ficheros.add(fileVO);
						nombres.add(ficheroTemp.getName());
					} catch (Exception e){
						logger.error("Se ha producido un error con un fichero de log - " + e);
					}
				}
				Collections.sort(nombres);
				listadoLog = ordenarLista(nombres, ficheros);
			}
		} catch (Exception e){
			logger.error("se ha producido una excepción al intentar listar los ficheros de log - " + e);
			throw e;
		}
		return listadoLog;

	}
	
	/**
	 * Devuelve una lista de ficheros ordenada
	 * 
	 * @param	listaOrdenada
	 * 					lista ordenada con los nombres de los ficheros
	 * @param	listaDesordenada
	 * 					lista desordenada con los datos de los ficheros
	 * @return listadoLog FileVO[] en orden alfabético
	 * @throws Exception
	 */
	private FileVO[] ordenarLista (List listaOrdenada, List listaDesordenada) throws Exception{
		try{
		FileVO[] filesOrdenado = new FileVO[listaDesordenada.size()];
			int posicion=0;
			for (int i=0; i<listaDesordenada.size();i++){
				posicion = listaOrdenada.indexOf(((FileVO)listaDesordenada.get(i)).getNombre().toLowerCase());
				filesOrdenado[posicion]= (FileVO)listaDesordenada.get(i);
			}
			return filesOrdenado;
		} catch (Exception e){
			logger.error("Se ha producido un error al ordernar la lista de log - " + e);
			throw e;
		}

	}


	/**
	 * Elimina del sistema los ficheros de log seleccionados
	 * 
	 * @param	ficheros
	 * 					String[] nombres de los ficheros a eliminar
	 * @return validaBajaLogVO ValidaBajaLogVO contiene el resultado del 
	 * proceso de baja y los nombres de los ficheros borrados. 
	 * @throws Exception
	 */
	protected es.pode.contenidos.negocio.logs.servicio.ValidaBajaLogVO handleEliminarFicheroLog(
			String[] ficheros) throws java.lang.Exception {

		List ficherosBorrados = new ArrayList();
		ValidaBajaLogVO validaBajaLogVO = new ValidaBajaLogVO();
		File fichero = null;
		String ruta = pathLogsDir; 
		if (!pathLogsDir.endsWith(fichero.separator)){
			ruta = ruta + fichero.separator;
		}
			
		try{
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					fichero = new File(ruta + ficheros[i]);
					if (fichero.delete()){
						ficherosBorrados.add(ficheros[i]);
					} else {
						logger.warn("no se ha podido eliminar el siguiente fichero de log: " + ficheros[i]);
					}
				}
				validaBajaLogVO.setDescripcionBaja("ficherosBorrados.OK");
			} else {
				validaBajaLogVO.setDescripcionBaja("ficherosBorrados.FALLO");
			}
		} catch (Exception e){
			logger.error("se ha producido una excepcion al borrar los ficheros de log - " +e);
			validaBajaLogVO.setDescripcionBaja("ficherosBorrados.FALLO");
		}
		validaBajaLogVO.setLogsBorrados((String[])ficherosBorrados.toArray(new String[0]));
		return validaBajaLogVO;
	}
	
	public SrvLogServiceImpl(){
		super();
		try {
			pathLogsDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_LOGS);
		} catch (Exception e) {
			logger.error("Fallo a la hora de recoger el valor de la variable PATH_LOGS - ",e);
		}
	}
	
	
	/**
	 * Recupera el contenido de un fichero de log para poder ser visualizado.
	 * 
	 * @param	fichero
	 * 					String nombre del fichero de log a recuperar
	 * @return dataHandler DataHandler con el contenido del fichero de log
	 * @throws Exception
	 */
	protected DataHandler handleRecuperarFicheroLog(String fichero) throws Exception {
		
		File file = null;
		String ruta = pathLogsDir; 
		if (!pathLogsDir.endsWith(file.separator)){
			ruta = ruta + file.separator;
		} 
		file = new File(ruta + fichero);
		
		if (!file.exists())
		{
			logger.warn("El fichero <" + file.getAbsolutePath() + "> ha sido borrado o movido");
			throw new Exception ("el fichero ha sido borrado o movido");
		}
		
		FileDataSource fileDS = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDS);
		
		return dataHandler;
	}

}