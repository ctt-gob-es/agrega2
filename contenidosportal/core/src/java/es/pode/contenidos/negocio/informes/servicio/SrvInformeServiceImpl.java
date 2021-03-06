package es.pode.contenidos.negocio.informes.servicio;

// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;



/**
 * @see es.pode.contenidos.negocio.informes.servicio.SrvInformeService
 */

public class SrvInformeServiceImpl
    extends es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceBase
{

	private static Logger log = Logger.getLogger(SrvInformeServiceImpl.class);
	private static String pathDir = null;
	private static String pathFederadoDir = null;
	private static String pathInformesMasDir = null;
	

	
	public SrvInformeServiceImpl(){
		super();
		try {
			pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES);
		
		pathFederadoDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_FEDERADOS);
		pathInformesMasDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_MAS);
		} catch (Exception e) {
			log.error("Fallo al recuperar el servicio SrvPropiedadService",e);
		}}

	/**
	 * Devuelve un listado con los informes generados por el sistema
	 * 
	 * @return listaInformes InformeVO[] con los datos de los informes
	 * @throws Exception
	 */
    protected es.pode.contenidos.negocio.informes.servicio.InformeVO[] handleListarInformes()
        throws java.lang.Exception
    {
		File fpathDir = new File(pathDir);
		InformeVO[] listaInformes = null;
		try{
			if (!fpathDir.exists()) {
				log.error("Error: El directorio " + pathDir + " no existe");
			} else {
	
				// Recorremos el directorio donde estan los informes
				File[] arrayList = fpathDir.listFiles();
				List ficheros = new ArrayList();
				File ficheroTemp = null;
	
				// Por cada fichero encontrado creamos el VO con su nombre y la fecha
				if(arrayList!=null){
					for (int i = 0; i < arrayList.length; i++) {
						ficheroTemp = arrayList[i];
						InformeVO informeVO = new InformeVO();
						informeVO.setNombre(ficheroTemp.getName());
						
						long timestamp = ficheroTemp.lastModified();
						Date fecha = new Date(timestamp);
						informeVO.setFecha(fecha);
						ficheros.add(informeVO);
					}
				}
				listaInformes = (InformeVO[]) ficheros.toArray(new InformeVO[0]);
			}
		} catch (Exception e){
			log.error("se ha producido una excepci�n al intentar listar los informes: " + e);
			throw e;
		}
		return listaInformes;
    }

    
    /**
	 * Obtiene la lista de los informes p�blicos (masDescargado, masPrevisualizado, masMostrado, masValorado, masPesado) 
	 * generados autom�ticamente mediante una tarea programada
	 * @return Array de InformeVO
	 * @throws Exception
	 */
       
    protected es.pode.contenidos.negocio.informes.servicio.InformeVO[] handleListarInformesMas()
    throws java.lang.Exception {
   	
    log.debug("ListarInformesMas");
    
	File fpathDir = new File( pathInformesMasDir);
	log.debug("fpathDir "+fpathDir);
	InformeVO[] listaInformes = null;
	if (!fpathDir.exists() || !fpathDir.canRead() || !fpathDir.isDirectory()) {
		log.error("Error: El directorio " + pathInformesMasDir + " no existe, o no tiene permisos de lectura, o no es un directorio.");
		return null;
	}
	
	try{
		log.debug("el path '"+pathInformesMasDir+"' existe");
		// Recorremos el directorio donde estan los informes
		File[] arrayList = fpathDir.listFiles();
		List ficherosDiarios = new ArrayList();
		List ficherosSemanales = new ArrayList();
		List ficherosTotales = new ArrayList();
		File ficheroTemp = null;

		// Por cada fichero encontrado creamos el VO con su nombre y la fecha
		for (int i = 0; i < arrayList.length; i++) {
			ficheroTemp = arrayList[i];
			InformeVO informeVO = new InformeVO();
			informeVO.setNombre(ficheroTemp.getName());
		
			log.debug("informeVO.getNombre() "+informeVO.getNombre());
			
			long timestamp = ficheroTemp.lastModified();
			Date fecha = new Date(timestamp);
			informeVO.setFecha(fecha);
			
			if(informeVO.getNombre().indexOf("-") > 0)
				ficherosSemanales.add(informeVO);
			else
				ficherosDiarios.add(informeVO);			
		}
		Iterator iteratorDiarios = ficherosDiarios.iterator();
		while(iteratorDiarios.hasNext())
			ficherosTotales.add(iteratorDiarios.next());
		
		log.debug("ficherosTotales.size()"+ficherosTotales.size());
		log.debug("A�ado los ficheros diarios");
		
		Iterator iteratorSemanales = ficherosSemanales.iterator();
		while(iteratorSemanales.hasNext())
			ficherosTotales.add(iteratorSemanales.next());
		
		log.debug("ficherosTotales.size()"+ficherosTotales.size());
		log.debug("A�ado los ficheros semanales");
		
		listaInformes = (InformeVO[]) ficherosTotales.toArray(new InformeVO[0]);
		
	} catch (Exception e){
		log.error("se ha producido una excepci�n al intentar listar los informes 'Mas': " + e);
		return null;
	}
	log.debug("listaInformes.length "+listaInformes.length);
	return listaInformes;
}
    
	/**
	 * Elimina del sistema los informes seleccionados
	 * 
	 * @param	ficheros
	 * 					String[] nombres de los informes a eliminar
	 * @return validaBajaInformeVO ValidaBajaInformeVO contiene el resultado del 
	 * proceso de baja y los nombres de los informes borrados. 
	 * @throws Exception
	 */
    protected es.pode.contenidos.negocio.informes.servicio.ValidaBajaInformeVO handleEliminarInforme(java.lang.String[] ficheros)
        throws java.lang.Exception
    {
		List ficherosBorrados = new ArrayList();
		ValidaBajaInformeVO validaBajaInformeVO = new ValidaBajaInformeVO();
		File fichero = null;
		String ruta = pathDir; 
		if (!pathDir.endsWith(fichero.separator)){
			ruta = ruta + fichero.separator;
		}
			
		try{
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					fichero = new File(ruta + ficheros[i]);
					if (fichero.delete()){
						ficherosBorrados.add(ficheros[i]);
					} else {
						log.error("no se ha podido eliminar el siguiente informe: " + ficheros[i]);
					}
				}
				validaBajaInformeVO.setDescripcionBaja("informesBorrados.OK");
			} else {
				validaBajaInformeVO.setDescripcionBaja("informesBorrados.FALLO");
			}
		} catch (Exception e){
			log.error("se ha producido una excepci�n al borrar los informes :" +e);
			validaBajaInformeVO.setDescripcionBaja("informesBorrados.FALLO");
		}
		validaBajaInformeVO.setInformesBorrados((String[])ficherosBorrados.toArray(new String[0]));
		return validaBajaInformeVO;
    }

	/**
	 * Recupera el contenido de un informe para poder ser visualizado.
	 * 
	 * @param	fichero
	 * 					String nombre del informe a recuperar
	 * @return dataHandler DataHandler con el contenido del informe
	 * @throws Exception
	 */
    protected javax.activation.DataHandler handleRecuperarInforme(java.lang.String fichero)
        throws java.lang.Exception
    {
		File file = null;
		String ruta = pathDir; 
		if (!pathDir.endsWith(file.separator)){
			ruta = ruta + file.separator;
		} 
		file = new File(ruta + fichero);
		
		if (!file.exists())
		{
			log.error("El fichero " + file.getAbsolutePath() + "  ha sido borrado o movido");
			throw new Exception ("el fichero ha sido borrado o movido");
		}
		
		FileDataSource fileDS = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDS);
		
		return dataHandler;
    }

    
    /**
	 * Devuelve el contenido del informe del tipo 'Mas' cuyo nombre se pasa como par�metro 
	 * @param  fichero 
	 * 		Nombre del fichero que se quiere mostrar
	 * @return DataHandler
	 * @throws Exception
	 */
    protected javax.activation.DataHandler handleRecuperarInformeMas(java.lang.String fichero)
        throws java.lang.Exception
    {
		File file = null;
		String ruta = pathInformesMasDir; 
		if (!pathInformesMasDir.endsWith(file.separator)){
			ruta = ruta + file.separator;
		} 
		file = new File(ruta + fichero);
		
		if (!file.exists())
		{
			log.error("El fichero 'Mas' " + file.getAbsolutePath() + "  ha sido borrado o movido");
			throw new Exception ("el fichero ha sido borrado o movido");
		}
		
		FileDataSource fileDS = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDS);
		
		return dataHandler;
    }
    


	/**
	 * Elimina del sistema los informes federados seleccionados
	 * 
	 * @param	ficheros
	 * 					String[] nombres de los informes federados a eliminar
	 * @return validaBajaInformeVO ValidaBajaInformeVO contiene el resultado del 
	 * proceso de baja y los nombres de los informes federados borrados. 
	 * @throws Exception
	 */
	protected ValidaBajaInformeVO handleEliminarInformeFederado(String[] ficheros) throws Exception {
		List ficherosBorrados = new ArrayList();
		ValidaBajaInformeVO validaBajaInformeVO = new ValidaBajaInformeVO();
		File fichero = null;
		String ruta = pathFederadoDir; 
		if (!pathFederadoDir.endsWith(fichero.separator)){
			ruta = ruta + fichero.separator;
		}
			
		try{
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					fichero = new File(ruta + ficheros[i]);
					if (fichero.delete()){
						ficherosBorrados.add(ficheros[i]);
					} else {
						log.error("no se ha podido eliminar el siguiente informe: " + ficheros[i]);
					}
				}
				validaBajaInformeVO.setDescripcionBaja("informesBorrados.OK");
			} else {
				validaBajaInformeVO.setDescripcionBaja("informesBorrados.FALLO");
			}
		} catch (Exception e){
			log.error("se ha producido una excepci�n al borrar los informes :" +e);
			validaBajaInformeVO.setDescripcionBaja("informesBorrados.FALLO");
		}
		validaBajaInformeVO.setInformesBorrados((String[])ficherosBorrados.toArray(new String[0]));
		return validaBajaInformeVO;
	}

	/**
	 * Devuelve un listado con los informes federados generados por el sistema
	 * 
	 * @return listaInformes InformeVO[] con los datos de los informes federados
	 * @throws Exception
	 */
	protected InformeVO[] handleListarInformesFederados() throws Exception {
		File fpathDir = new File(pathFederadoDir);
		InformeVO[] listaInformes = null;
		try{
			if (!fpathDir.exists()) {
				log.error("Error: El directorio " + pathFederadoDir + " no existe");
			} else {
	
				// Recorremos el directorio donde estan los informes
				File[] arrayList = fpathDir.listFiles();
				List ficheros = new ArrayList();
				File ficheroTemp = null;
	
				// Por cada fichero encontrado creamos el VO con su nombre y la fecha
				if(arrayList!=null){
					for (int i = 0; i < arrayList.length; i++) {
						ficheroTemp = arrayList[i];
						InformeVO informeVO = new InformeVO();
						informeVO.setNombre(ficheroTemp.getName());
						
						long timestamp = ficheroTemp.lastModified();
						Date fecha = new Date(timestamp);
						informeVO.setFecha(fecha);
						ficheros.add(informeVO);
					}
				}
				listaInformes = (InformeVO[]) ficheros.toArray(new InformeVO[0]);
			}
		} catch (Exception e){
			log.error("se ha producido una excepci�n al intentar listar los informes federados: " + e);
			throw e;
		}
		return listaInformes;
	}

	/**
	 * Recupera el contenido de un informe federado para poder ser visualizado.
	 * 
	 * @param	fichero
	 * 					String nombre del informe federado a recuperar
	 * @return dataHandler DataHandler con el contenido del informe federado
	 * @throws Exception
	 */
	protected DataHandler handleRecuperarInformesFederados(String fichero) throws Exception {
		File file = null;
		String ruta = pathFederadoDir; 
		if (!pathFederadoDir.endsWith(file.separator)){
			ruta = ruta + file.separator;
		} 
		file = new File(ruta + fichero);
		
		if (!file.exists())
		{
			log.error("El fichero " + file.getAbsolutePath() + "  ha sido borrado o movido");
			throw new Exception ("el fichero ha sido borrado o movido");
		}
		
		FileDataSource fileDS = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDS);
		
		return dataHandler;
	}
}