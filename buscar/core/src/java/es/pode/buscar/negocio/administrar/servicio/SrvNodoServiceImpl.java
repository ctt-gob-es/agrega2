/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.administrar.servicio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.auditoria.negocio.servicios.InformeLicenciasOdesVO;
import es.pode.auditoria.negocio.servicios.InformeOperacionVO;
import es.pode.auditoria.negocio.servicios.InformeTerminoBusquedaVO;
import es.pode.auditoria.negocio.servicios.ParametrosInformeVO;
import es.pode.buscar.negocio.administrar.dominio.Ccaa;
import es.pode.buscar.negocio.administrar.dominio.CcaaDao;
import es.pode.buscar.negocio.administrar.dominio.Nodo;
import es.pode.buscar.negocio.administrar.dominio.NodoDao;
import es.pode.buscar.negocio.administrar.dominio.ServicioDao;
import es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica;
import es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega;
import es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica;
import es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaTotal;
import es.pode.buscar.negocio.administrar.nodo.castor.NodoConf;
import es.pode.buscar.negocio.buscar.dominio.EstadisticaImpl;
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.gestorCorreo.negocio.servicios.SrvCorreo;
import es.pode.indexador.negocio.servicios.busqueda.ParamDocsCountVO;
import es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO;
import es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.Proxy;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.zip.TrueZipDaoImpl;



/**
 * @see es.pode.buscar.negocio.administrar.servicio.SrvNodoService
 */

public class SrvNodoServiceImpl
extends es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceBase
{

	private static org.apache.log4j.Logger logger = Logger.getLogger(SrvNodoServiceImpl.class);
	private java.util.Properties pBuscarProperties = null;
	private static final String defaultEcoding = "UTF-8";
	private static final String NOMBRE_FICHERO_ESTADISTICAS = "estadisticasAgrega";
	private static final String EXTENSION_FICHERO_ESTADISTICAS = ".xml";
	private static final String NOMBRE_FICHERO_ESTADISTICAS_TOTALES = "estadisticasAgregaTotales";
	private static final String NODO_ESTADISTICAS_TOTALES = "Estadisticas Totales";
	private static final int NUMERO_TERMINOS_POR_BLOQUE = 5;
	private static final int POSICION_INICIO_TERMINOS = 32;
	private static final int BLOQUE_TERMINOS_TOTAL = 1;
	private static final int BLOQUE_TERMINOS_ANIO = 2;
	private static final int BLOQUE_TERMINOS_MES = 3;
	private static final int BLOQUE_TERMINOS_SEMANA = 4;
	private static final int BLOQUE_TERMINOS_DIA = 5;
	private static SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyyMMdd");
	private SrvPropiedadService prop = null;
	
	private static final String lit_descargado="descargado";
	private static final String lit_descargado_SCORM_2004="descargado_SCORM_2004";
	private static final String lit_ficha="ficha"; 
	private static final String lit_descargado_IMS_CP="descargado_IMS_CP";
	private static final String lit_buscar_local="buscar_local";
	private static final String lit_descargado_SCORM_2004_SIN_SUBMANIFIESTO="descargado_SCORM_2004_SIN_SUBMANIFIESTO";        
	private static final String lit_buscar_federada="buscar_federada";
	private static final String lit_descargado_SCORM_12="descargado_SCORM_12";
	private static final String lit_buscarSQI="buscarSQI"; 
	private static final String lit_previsualizado="previsualizado";
	
	private static final String INCIDENCIA_ZIP_OBSOLETO="INCIDENCIA_ZIP_OBSOLETO";
	private static final String INCIDENCIA_FICHERO_OBSOLETO="INCIDENCIAFICHERO_OBSOLETO";
	private static final String INCIDENCIA_ACTIVIDAD_NULA="INCIDENCIA_ACTIVIDAD_NULA";
	private static final String INCIDENCIA_ACTIVIDAD_NEGATIVA="INCIDENCIA_ACTIVIDAD_NEGATIVA";
	private static final String INCIDENCIA_DATO_ESTADISTICO_INCORRECTO="INCIDENCIA_DATO_ESTADISTICO_INCORRECTO";
	private static final int CODIGO_ESTADISTICA_BUSQUEDAS = 1;
	
	
	/**
	 * Metodo para dar de alta un nodo. 
	 * 
	 * @param nodo
	 *                nodoVO con los datos del nodo que se dara de alta
	 * @return void
	 * @throws Exception
	 */
	protected Long handleCrearNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo)
	throws java.lang.Exception
	{
		String idNodo = "";
		Long codigo_devuelto;
		try{
			//Asociamos todos los servicios al nodo
			ServicioVO[] servicios = obtenerServicios();
			nodo.setServicio(servicios);

			Nodo nodoEntity = this.getNodoDao().fromNodoVO(nodo);
			nodoEntity.setFechaAlta(new Date());
			if (nodo.getCcaa()!=null){
				Ccaa ccaa = obtenerCCAA (nodo.getCcaa());
				nodoEntity.setCcaa(ccaa);
			}

			//    	idNodo = obtenerIdentificador(nodo);	
			idNodo=nodo.getIdNodo();

			//Introducimos el identificador del nodo en el vo
			//	    nodoEntity.setIdNodo(idNodo);

			//Introducir en la BD el nodo creado
			logger.debug("Se introduce el nodo en la base de datos con idNodo <" + idNodo +">");
			Nodo nodoVuelta=this.getNodoDao().create(nodoEntity);
			logger.debug("Los valores del nodo de vuelta son: IdNodo <" +nodoVuelta.getIdNodo()+">, la url <"+nodoVuelta.getUrl()+">, la fecha de alta <"+nodoVuelta.getFechaAlta().toString()+">");
			codigo_devuelto=nodoVuelta.getId();
			logger.debug("El identificador que devolvemos es <"+codigo_devuelto+">");
		}catch (InterruptedException Ie){
			codigo_devuelto = -1L;
			logger.warn("Error al insertar el nodo. Se devuelve codigo de error <"+codigo_devuelto+"> - " +Ie.getCause());
			logger.debug("",Ie);

		} catch (Exception e){
			logger.error("Error al insertar el nodo ", e);
			throw new Exception("Error al insertar el nodo ", e);
		}

		return codigo_devuelto;
	}

	/**
	 * Metodo para obtener los datos de una ccaa
	 * 
	 * @param ccaa
	 *                ccaaVO con el id de la ccaa que se quiere recuperar
	 * @return Ccaa con los datos de la ccaa
	 * @throws Exception
	 */

	private Ccaa obtenerCCAA (CcaaVO ccaa) throws Exception{
		return this.getCcaaDao().load(ccaa.getId());
	}

	/**
	 * Metodo para modificar un nodo. Actualiza los datos del nodo.
	 * 
	 * @param nodo
	 *                nodoVO con los datos del nodo que se modificará
	 * @return void
	 * @throws Exception
	 */
	protected Long handleModificarNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo)
	throws java.lang.Exception
	{

		Long codigo_devuelto;

		try{
			//		String idNodo=obtenerIdentificador(nodo);
			String idNodo = nodo.getIdNodo();

			Nodo nodoEntity = getNodoDao().load(nodo.getId());
			if (nodo.getCcaa()!=null){
				Ccaa ccaa = obtenerCCAA (nodo.getCcaa());
				nodoEntity.setCcaa(ccaa);
				nodo.setCcaa(getCcaaDao().toCcaaVO(ccaa));
			}
			nodoEntity.setNodo(nodo.getNodo());
			nodoEntity.setPuerto(nodo.getPuerto());
			nodoEntity.setUrl(nodo.getUrl());
			nodoEntity.setUrlWS(nodo.getUrlWS());

			//Hacemos la modificación si hemos podido conectar con el nodo
			//Introducimos el identificador del nodo en el vo
			nodoEntity.setIdNodo(idNodo);
			codigo_devuelto =nodo.getId();
			logger.debug("El identificador del nodo modificado es <"+codigo_devuelto+">");
			//actualizamos los datos del nodo
			this.getNodoDao().update(nodoEntity);	  

		} catch (InterruptedException Ie){
			codigo_devuelto = -1L;
			logger.error("Error al modificar el nodo. Devolvemos codigo <"+codigo_devuelto+">"+ Ie.getCause());
			logger.debug("",Ie);

		} catch (Exception e){
			logger.error("Error al modificar el nodo - ", e);
			throw new Exception("Error al modificar el nodo, ", e);
		}

		return codigo_devuelto;
	}


	/**
	 * Metodo para eliminar un nodo.
	 * 
	 * @param ids
	 *                Long[] con los identificadores de los nodos que 
	 *                serán eliminados
	 * @return ValidaBajaNodoVO que contiene el resultado de la eliminación
	 * así como los nodos que han sido eliminados.
	 * @throws Exception
	 */
	protected es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO handleEliminarNodo(java.lang.Long[] ids)
	throws java.lang.Exception
	{
		ValidaBajaNodoVO validaBajaNodoVO = new ValidaBajaNodoVO();
		try{
			int nodosBorrados = 0;
			List<NodoVO> itemsBorrados = new ArrayList<NodoVO>();
			Nodo nodoBorrado;
			for (int i=0; i<ids.length;i++){
				nodoBorrado = this.getNodoDao().load(ids[i]);
				this.getNodoDao().remove(ids[i]);
				if(logger.isDebugEnabled())logger.debug("Nodo eliminado con id: " + ids[i]);
				nodosBorrados ++;
				itemsBorrados.add(this.getNodoDao().toNodoVO(nodoBorrado));
			}
			validaBajaNodoVO.setNodosBorrados(itemsBorrados.toArray(new NodoVO[0]));
			if (nodosBorrados == ids.length){
				validaBajaNodoVO.setDescripcionBaja("ok.borrarNodos");
			} else {
				validaBajaNodoVO.setDescripcionBaja("fallo.borrarNodos");
			}
		} catch (Exception e){
			logger.error("Error al eliminar el nodo - ", e);
			throw e;
		}
		return validaBajaNodoVO;
	}

	/**
	 * Metodo para recuperar los datos de un nodo.
	 * 
	 * @param id
	 *                Long con el identificador del nodo.
	 * @return NodoVO con los datos del nodo que se ha recuperado
	 * @throws Exception
	 */
	protected es.pode.buscar.negocio.administrar.servicio.NodoVO handleObtenerNodo(java.lang.Long id)
	throws java.lang.Exception
	{
		NodoVO nodoVO = (NodoVO)this.getNodoDao().load(NodoDao.TRANSFORM_NODOVO, id);
		if(nodoVO==null) {
			nodoVO = new NodoVO();
			nodoVO.setNodo("");
			nodoVO.setFechaAlta(null);
			nodoVO.setUrl("");
			nodoVO.setIdNodo("");
			nodoVO.setPuerto("");
			nodoVO.setUrlWS("");
			nodoVO.setId(-1L);
		}
		return nodoVO;
	}

	/**
	 * Metodo para recuperar un listado de los nodos almacenados en el sistema
	 * 
	 * @return NodoVO[] array con los nodos recuperados
	 * @throws Exception
	 */
	protected NodoVO[] handleListarNodos() throws Exception {
		try {
			NodoVO[] nodos = (NodoVO[]) this.getNodoDao().listarNodos(
					NodoDao.TRANSFORM_NODOVO).toArray(new NodoVO[0]);

//			if (logger.isDebugEnabled()) {		
//				for (int i = 0; i < nodos.length; i++) {
//					logger.debug("Nodo: " + nodos[i].getNodo());
//					logger.debug("URL: " + nodos[i].getUrl());				
//					logger.debug("puerto: " + nodos[i].getPuerto());
//					logger.debug("idNodo: " + nodos[i].getIdNodo());
//				}
//			}
			return nodos;

		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/**
	 * Metodo para recuperar los datos de una ccaa
	 * 
	 * @param id
	 *                Long con el identificador de la ccaa
	 * @return CcaaVO con los datos de la ccaa.
	 * @throws Exception
	 */
	protected CcaaVO handleObtenerCCAA(Long id) throws Exception {
		return (CcaaVO)this.getCcaaDao().load(CcaaDao.TRANSFORM_CCAAVO,id);
	}

	/**
	 * Metodo que devuelve un listado de las ccaa.
	 * 
	 * @return CcaaVO[] con los datos de las ccaa.
	 * @throws Exception
	 */
	protected CcaaVO[] handleObtenerCCAAs() throws Exception {

		CcaaVO[] ccaa = (CcaaVO[]) this.getCcaaDao().obtenerCCAAs(
				CcaaDao.TRANSFORM_CCAAVO).toArray(new CcaaVO[0]);

//		if (logger.isDebugEnabled()) {		
//			for (int i = 0; i < ccaa.length; i++) {
//				logger.debug("ID: " + ccaa[i].getId());
//				logger.debug("Código: " + ccaa[i].getCodigo());				
//				logger.debug("Descripción: " + ccaa[i].getDescripcion());
//			}
//		}
		return ccaa;
	}

	/**
	 * Metodo para recuperar los datos de un servicio.
	 * 
	 * @param id
	 *                Long con el identificador del servicio.
	 * @return ServicioVO con los datos del servicio.
	 * @throws Exception
	 */
	protected ServicioVO handleObtenerServicio(Long id) throws Exception {
		return (ServicioVO)this.getServicioDao().load(ServicioDao.TRANSFORM_SERVICIOVO, id);
	}

	/**
	 * Metodo para recuperar los servicios almacenados en el sistema.
	 * 
	 * @return ServicioVO[] con los datos de los servicios.
	 * @throws Exception
	 */
	protected ServicioVO[] handleObtenerServicios() throws Exception {
		return (ServicioVO[])this.getServicioDao().obtenerServicios(ServicioDao.TRANSFORM_SERVICIOVO).toArray(new ServicioVO[0]);
	}

	/**
	 * Obtiene el valor almacenado en el fichero properties auditoria de una determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws Exception
	 */
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/buscar.properties");
		if (this.pBuscarProperties == null)
		{
			pBuscarProperties = new java.util.Properties();
			pBuscarProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pBuscarProperties.getProperty(sKey);
	}

	/**
	 * Metodo para comprobar si ya existe un nodo dado de alta en el sistema con los mismos valores de url y 
	 * url del WebService.
	 * 
	 * @param url String. url del nodo
	 * @param urlWS String. url del webService
	 * @return Boolean
	 * @throws Exception
	 */
	protected Boolean handleEstaDadoAlta(String url, String urlWS)
	throws java.lang.Exception
	{
		Boolean resultado = false;
		//    	Comprobamos si la url y la urlWS ya esta dado de alta en la BD
		NodosCriteria criterios = new NodosCriteria();
		criterios.setUrl(url);
		criterios.setUrlWS(urlWS);
		if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de Nodo para hacer la comprobacion");
		this.getNodoDao();
		List<?> listaNodos = this.getNodoDao().ListarNodosSemejantes(NodoDao.TRANSFORM_NODOVO, criterios);

		if((!(listaNodos == null))&&(listaNodos.size() > 0))
		{
			if(logger.isDebugEnabled())logger.debug("Existe algun nodo con los mismos valores de url "+listaNodos.size());
			resultado = true;
		} 
		if(logger.isDebugEnabled())logger.debug("¿Esta dado de alta? "+resultado);
		return resultado;
	}

	/**
	 * Metodo que comprueba si un nodo ya creado tiene un nombre de nodo que se intenta dar de alta.
	 * 
	 * @param nombreNodo String. Nombre del nodo
	 * 
	 * @return Boolean se devuelve true si existe un nodo con ese nombre y false si no existe
	 * @throws java.lang.Exception
	 */
	protected Boolean handleExisteNombreNodo(String nombreNodo)
	throws java.lang.Exception
	{
		Boolean resultado = false;

		//Comprobamos si nombre de nodo ya esta dado de alta en la BD
		NombresNodosCriteria criterios = new NombresNodosCriteria();
		criterios.setNodo(nombreNodo);

		if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de NombreNodo ["+nombreNodo+"] para hacer la comprobacion");
		List<?> listaNodos = this.getNodoDao().ListarNodosNombresSemejantes(criterios);
		if(logger.isDebugEnabled())logger.debug("listar nodos = " + listaNodos+", con un tamanio de " + listaNodos.size());

		if(listaNodos != null && listaNodos.size() != 0) 
			resultado = true;
		
		return resultado;
	}

	@Override
	protected NodoVO handleObtenerNodoID(String id) throws Exception {

		IdNodoCriteria criteria = new IdNodoCriteria();
		criteria.setId_nodo(id);

		List<Nodo> listaNodos = getNodoDao().listarNodosId(criteria);

		if(listaNodos!=null && listaNodos.size()!=0) {
			return getNodoDao().toNodoVO(listaNodos.get(0));
		}

		NodoVO nodoVO = new NodoVO();
		nodoVO.setNodo("");
		nodoVO.setFechaAlta(null);
		nodoVO.setUrl("");
		nodoVO.setIdNodo("");
		nodoVO.setPuerto("");
		nodoVO.setUrlWS("");
		nodoVO.setId(-1L);
		return nodoVO;
	}

	@Override
	protected Boolean handleActualizarIndices() throws Exception {
		
		//Bajamos Zips de web
		String url=ObtieneSrvPropiedad().get(AgregaProperties.INDEX_SERVER_URL);
		if(url.isEmpty()) {
			logger.warn("La propiedad <"+AgregaProperties.INDEX_SERVER_URL+"> del fichero properties esta en blanco. Debe dar la url donde se encuentra el fichero nodos.zip en el servidor de indices.");
			return false;
		}
		String rutaLocal = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS);
		if(rutaLocal.isEmpty()) {
			logger.warn("La propiedad <"+AgregaProperties.INDICES_REMOTOS+"> del fichero properties esta en blanco. Debe dar la ruta del nodo local donde se descargara y descomprimira el fichero de indices.");
			return false;
		}

		String fullUrl="";
		String port = ObtieneSrvPropiedad().get(AgregaProperties.INDEX_SERVER_PORT);
		if(port.isEmpty()) {
			logger.warn("La propiedad <"+AgregaProperties.INDEX_SERVER_PORT+"> del fichero properties esta en blanco.");
			fullUrl=url+"/";
		} else { 
			//Metemos en la url el puerto que nos hayan especificado
			String urlParts[] = url.split("/");
			
			//Revisamos si en la url se especifica protocolo
			int indexPort; 
			if(url.contains("://")) indexPort=2;
			else indexPort=0;
			
			for (int i=0; i<urlParts.length; i++) {
				if(i==indexPort) fullUrl=fullUrl.concat(urlParts[i]+":"+port+"/");
				else fullUrl=fullUrl.concat(urlParts[i]+"/");
			}
		}
		
		File temporal = new File(rutaLocal+"/nodos.zip");
		OutputStream out=null;
		InputStream is=null;

		if(logger.isDebugEnabled()) 
			logger.debug("Descargaremos <"+fullUrl+"> nodos.zip en <"+temporal+">");
		

		try {

			is=new BufferedInputStream(Proxy.getInputStream(new URL(fullUrl+"nodos.zip")));
			
    			out = new BufferedOutputStream(new FileOutputStream(temporal));
    			byte[] buffer = new byte[1024];
    			int numRead;
    			long numWritten = 0;
    			while ((numRead = is.read(buffer)) != -1) {
    				out.write(buffer, 0, numRead);
    				numWritten += numRead;
    			}
			
			//
			UtilesFicheros.copiar(is, out);
			
		} catch (Exception e) {
			logger.error("Error en descarga del fichero con los indices: - "+e);
			return false;			
		} finally {
			if(out!=null) out.close();
			if(is!=null) is.close();
		}
		if(logger.isDebugEnabled()) 
			logger.debug("Descomprimimos '"+temporal.getAbsolutePath()+"' en '"+rutaLocal+"' y borramos '"+temporal+"'");
		
		TrueZipDaoImpl.getInstance().descomprimir(temporal.getAbsolutePath(), rutaLocal);
		UtilesFicheros.eliminar(temporal);

		//Descomprimimos en ruta de índices remotos
		File rutaIndicesRemotos = new File(rutaLocal);
		String[] zips = rutaIndicesRemotos.list();
		
		//Eliminamos todos los nodos registrados en el sistema
		if(!handleEliminarTodosLosNodos()){ 
			logger.error("Error al eliminar los nodos registrados con anterioridad.");
			return false;
		}
		
		boolean huboAlgunError=false;
		
		for (int i = 0; i < zips.length; i++) {
			String zip = zips[i];
			
			if(zip.endsWith(".zip")) {
				if(logger.isDebugEnabled()) logger.debug("Zip de indices a procesar: <"+zip+">");
				
				//creamos ruta destino descompresion
				File rutatemp = new File(rutaLocal+"/"+zip.replace(".zip", ""));
				if(rutatemp.exists()) {
					if(logger.isDebugEnabled()) logger.debug("Eliminamos los indices antiguos en <"+rutatemp.getAbsolutePath()+">");
					UtilesFicheros.eliminar(rutatemp);
				}
				if(!rutatemp.mkdirs()) {

					if(logger.isDebugEnabled()) logger.debug("No se pudo crear ruta <"+rutatemp.getAbsolutePath()+
							"> y por lo tanto los indices del nodo correspondiente no se podran añadir al sistema.");
					// 20140220 Se comenta porque es un falso positivo. Realmente después al descomprimir el zip crea el directorio
					// En Galicia y en PRE INTEF esta validación fallaba aleatoriamente.
					//huboAlgunError=true;
					//return false;
				} else {
					if(logger.isDebugEnabled()) logger.debug("Creada ruta <"+rutatemp.getAbsolutePath()+">");
				}
				
				TrueZipDaoImpl.getInstance().descomprimir(rutaLocal+"/"+zip, rutatemp.getAbsolutePath());
				UtilesFicheros.eliminar(new File(rutaLocal+"/"+zip));
				
				//Parseamos XML
				Unmarshaller unmarshaller = new Unmarshaller(NodoConf.class);
				unmarshaller.setValidation(false);
	
				String nombreFicEstadisticasNodo = "";
				for (String nombreFicheroEst : rutatemp.list()) {
					if (nombreFicheroEst.startsWith(NOMBRE_FICHERO_ESTADISTICAS) && nombreFicheroEst.endsWith(EXTENSION_FICHERO_ESTADISTICAS)) {
						File estFile = new File(rutatemp.getAbsolutePath()+ "/" +nombreFicheroEst);
						UtilesFicheros.copiar(estFile, new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_ESTADISTICAS)));
						nombreFicEstadisticasNodo=nombreFicheroEst;
					}
				}
				
				File fichero = new File(rutatemp.getAbsolutePath()+"/"+getPropertyValue("nodoConf.fichero"));
				FileInputStream fis=null;
				InputStreamReader isr = null;
				NodoConf nodoConf=null;
				try {
					fis = new FileInputStream(fichero);
					isr = new InputStreamReader(fis);
					nodoConf=(NodoConf)unmarshaller.unmarshal(isr);
				} catch (Exception e) {
					logger.error("Error en parseo a XML de configuración de nodo: - " +e.getCause());
					logger.error("",e);
					huboAlgunError=true;
					//return false;
				} finally {
					if(fis!=null) fis.close();
					if(isr!=null) isr.close();
				}
				//Dar de alta cada nodo si no es nodo propio!
				if(nodoConf!=null && !nodoConf.getIdNodo().equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID))) {
					NodoVO nodoVO = new NodoVO();
					nodoVO.setNodo(nodoConf.getCcaa());
					nodoVO.setFechaAlta(formatoFecha.parse(nodoConf.getFechaAlta()));
					nodoVO.setUrl(nodoConf.getUrl());
					nodoVO.setIdNodo(nodoConf.getIdNodo());
					nodoVO.setPuerto(nodoConf.getPuerto());
					nodoVO.setUrlWS(nodoConf.getUrlWS());
					
					if(handleCrearNodo(nodoVO)==-1L) {
						logger.error("Error dando de alta/modificando el nodo <"+nodoConf.getCcaa()+"> con id <"+nodoConf.getIdNodo()+">");
						huboAlgunError=true;
						//return false;
					}
					
					// Verificamos si la fecha informada en el nodoConf.xml es actual. Si es obsoleta enviamos mail de alarma					
					Date fechaFichero = formatoFecha.parse(nodoConf.getFechaAlta());
					String fechaHoyAux = formatoFecha.format(new Date());					
					Date fechaHoy = formatoFecha.parse(fechaHoyAux);

					// Lanzamos correo de alarma de fichero obsoleto
					if (fechaFichero.before(fechaHoy)) {
						// Obtenemos el nombre del nodo del nombre del fichero porque el nodoConf.xml no lo incluye
						logger.error("El fichero de índices es obsoleto");
						nombreFicEstadisticasNodo = nombreFicEstadisticasNodo.replace(NOMBRE_FICHERO_ESTADISTICAS,"");
						nombreFicEstadisticasNodo = nombreFicEstadisticasNodo.replace(EXTENSION_FICHERO_ESTADISTICAS,"");
						nombreFicEstadisticasNodo = nombreFicEstadisticasNodo.replace(".","");													
						envioCorreoAlarma(nombreFicEstadisticasNodo, INCIDENCIA_ZIP_OBSOLETO);
					}
				}
			}
		}
		if (huboAlgunError) return false;
		return true;
	}

	
	private static String getHomeServidor() {
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");
		} catch (Exception e) {
			return "";
		}
		return homeJboss+File.separator;
	}
	
	
	@Override
	protected Boolean handleSubirIndices() throws Exception {

		//Copiamos a temp carpeta índices local
		File uploadFolder=new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDEX_UPLOAD_PATH)+"/temp");
		if(!uploadFolder.mkdirs()) {
			logger.warn("No se pudo crear la ruta temporal <" + uploadFolder.getPath() +">");
			return false;
		}

		String pathIndicesCompass=getHomeServidor()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INDICE);
		File indices = new File(pathIndicesCompass);
		logger.debug("Se usaran los indices de "+indices.getAbsolutePath());
		UtilesFicheros.copiar(indices, uploadFolder);

		//Crear XML de conf de este nodo en carpeta temp
		NodoConf nodoConf = new NodoConf();
		nodoConf.setIdNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID));
		nodoConf.setCcaa(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON));
		nodoConf.setFechaAlta(formatoFecha.format(new Date()));
		nodoConf.setPuerto(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PUERTO));
		String alternativo=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST_ALTERNATIVO);
		
		// Obtenemos el subdominio para que si tiene valor se concatena al host
		String subdominio=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		
		if (subdominio!=null && !subdominio.equals(""))
			subdominio="\\"+subdominio;
		else
			subdominio="";
		
		if(alternativo!=null&&!alternativo.equals(""))
		{
			logger.debug("Se usa la URL del nodo alternativo");
			nodoConf.setUrl(alternativo+subdominio);
		} else {
			nodoConf.setUrl(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+subdominio);
		}
		nodoConf.setUrlWS(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST));

		File ficheroNodoConf=new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDEX_UPLOAD_PATH)+"/temp/"+getPropertyValue("nodoConf.fichero"));
		
		FileOutputStream fos = new FileOutputStream(ficheroNodoConf);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		try {
			Marshaller marshaller = new Marshaller(osw);
			marshaller.setEncoding(defaultEcoding);
			marshaller.marshal(nodoConf);

		} catch (Exception e) {
			logger.error("Error en parseo a XML de configuración de nodo: - ",e);
			return false;
		} finally {
			if(osw!=null) osw.close();
			if(fos!=null) fos.close();
		}

		File ficheroEstadisiticas = new File(AgregaPropertiesImpl.getInstance()
				.getProperty(AgregaProperties.PATH_ESTADISTICAS)
				+ "/"
				+ NOMBRE_FICHERO_ESTADISTICAS
				+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO)
				+ EXTENSION_FICHERO_ESTADISTICAS);
		logger.debug("ficheroEstadisiticas es :" + ficheroEstadisiticas.getAbsolutePath() + " y uploadFolder es " + uploadFolder.getAbsolutePath());
		UtilesFicheros.copiar(ficheroEstadisiticas, uploadFolder);
		
		//Comprimimos carpeta
		File zipFile=new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDEX_UPLOAD_PATH)+"/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID)+".zip");
		//Si existe, lo borramos
		if(zipFile.exists()) zipFile.delete();
		
		TrueZipDaoImpl.getInstance().comprimir(zipFile.getCanonicalPath(), uploadFolder.getCanonicalPath());
		//copiamos ZIP a ruta para upload
		UtilesFicheros.copiar(zipFile, uploadFolder);
		UtilesFicheros.eliminar(uploadFolder);
		return true;
	}

	@Override
	protected Boolean handleEliminarTodosLosNodos() throws Exception {
		NodoVO[] nodos = handleListarNodos();
		Long[] listaIds= new Long[nodos.length];
		
		for(int i=0; i<nodos.length; i++)
			listaIds[i]=nodos[i].getId();
			
		ValidaBajaNodoVO validaBajaNodoVO = handleEliminarNodo(listaIds);
		ValidaBajaNodoVO validador = new ValidaBajaNodoVO();
		validador.setDescripcionBaja("ok.borrarNodos");
		
		if (validador.getDescripcionBaja().contentEquals(validaBajaNodoVO.getDescripcionBaja()))
			return true;
		return false;
	}

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}

	protected Boolean handleCrearEstadisticasLocales() throws Exception{

//		logger.debug("crearFicheroEstadisticas begins");
		
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		
		File fichero = new File(AgregaPropertiesImpl.getInstance()
				.getProperty(AgregaProperties.PATH_ESTADISTICAS)
				+ "/"
				+ NOMBRE_FICHERO_ESTADISTICAS
				+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO)
				+ EXTENSION_FICHERO_ESTADISTICAS);
		
		fos = new FileOutputStream(fichero);
		osw = new OutputStreamWriter(fos);
		
		String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		
		EstadisticasAgrega estadisticasAgrega = new EstadisticasAgrega();
		estadisticasAgrega.setNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO));
		estadisticasAgrega.setFecha(sdf.format(date));
		
		// ACTIVIDAD
		try {
			if (logger.isDebugEnabled())
				logger.debug("Procesando estadísticas locales de actividad");

			GregorianCalendar todosLosTiempos = new GregorianCalendar(1970, 1, 1, 0, 0);
			ParametrosInformeVO parametrosInformeActividad = new ParametrosInformeVO();
			parametrosInformeActividad.setFechaDesde(todosLosTiempos);			
			parametrosInformeActividad.setFechaHasta(java.util.Calendar.getInstance());
			
			int descargas = 0;
			int busquedas = 0;
			int fichasAccedidas = 0;
			int odesPrevisualizados = 0;
			
			InformeOperacionVO[] informesOperaciones = this.getSrvAuditoriaServicio().informeOperacionesRealizadas(parametrosInformeActividad);
										
			// 19022013 
			// Los datos del informe vienen con los literales en el idioma de la plataforma
			// por eso hay que realizar las búsquedas de manera "internacionalizada"
			String idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();					
			
			for (InformeOperacionVO informeOperacionVO : informesOperaciones) {			
				if (informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_descargado))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_descargado_IMS_CP))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_descargado_SCORM_12))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_descargado_SCORM_2004))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_descargado_SCORM_2004_SIN_SUBMANIFIESTO))						
						) {								
					descargas += informeOperacionVO.getTotalOperacion();
				} else if (informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_buscar_federada))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_buscar_local))
						|| informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_buscarSQI))
						) {
					busquedas += informeOperacionVO.getTotalOperacion();
				} else if (informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_ficha))) {
					fichasAccedidas += informeOperacionVO.getTotalOperacion();		
				} else if (informeOperacionVO.getOperacion().equalsIgnoreCase(this.getPropertyValue(idioma+"_"+lit_previsualizado))) {
					odesPrevisualizados += informeOperacionVO.getTotalOperacion();
				}
			}
			estadisticasAgrega.addEstadistica(covert2estadistica("01", "Busquedas", String.valueOf(busquedas), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("02", "Descargas", String.valueOf(descargas), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("03", "Fichas Accedidas", String.valueOf(fichasAccedidas), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("04", "Odes Previsualizados", String.valueOf(odesPrevisualizados), "0"));
			
			if (logger.isDebugEnabled())
				logger.debug("Termina de procesar estadísticas locales de actividad");

		} catch (Exception ex) {
			logger.warn("No se ha podido obtener las estadisticas de ACTIVIDAD" + ex.getCause());
			estadisticasAgrega.addEstadistica(covert2estadistica("01", "Busquedas", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("02", "Descargas", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("03", "Fichas Accedidas", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("04", "Odes Previsualizados", "0", "0"));
		}

		// COBERTURA CURRICULAR
		try {
			
			if (logger.isDebugEnabled())
				logger.debug("Procesando estadísticas locales de cobertura curricular");
			
			String[] idiomasIndices = I18n.getInstance().obtenerIdiomasBuscables();
			
			String idiomaBusqueda = "";
			
			ParamDocsCountVO paramBusq;
			ResultadosCountVO resultadosCount; 
			
			SrvBuscadorService srvBuscador = this.getSrvBuscadorService();
			
			//String idiomaBusqueda = obtenerIdiomaBusqueda();
			String[] areasCurriculares ={"ACLOE2006/1", "ACLOE2006/2", "ACLOE2006/3", "ACLOE2006/4", "ACLOE2006/5", "ACLOE2006/6", "ACLOE2006/7"};
			
			String[] idNodo ={AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID)};
			
			Hashtable<String, Integer> htRes = new Hashtable<String, Integer>();
			
			
			for (int i = 0; i < idiomasIndices.length; i++) {
				idiomaBusqueda = idiomasIndices[i];
				if (logger.isDebugEnabled())
					logger.debug("Procesando idioma : " + idiomaBusqueda);
				paramBusq = new ParamDocsCountVO(areasCurriculares, idiomaBusqueda);
	    		resultadosCount = srvBuscador.solicitudDocsCountNodos(paramBusq, idNodo);
	    		
	    		Integer numActual=0;
	    		
	    		// Sumamos la estadística 05 de todos los índices 
	    		if (htRes.containsKey("05"))
	    		{
	    			numActual=htRes.get("05");
	    			numActual+=resultadosCount.getConteo()[3];
	    			htRes.put("05", numActual);
	    		}else
	    		{	    		
	    			numActual=resultadosCount.getConteo()[3];
	    			htRes.put("05", numActual);	    			
	    		}

	    		// Sumamos la estadística 06 de todos los índices 
	    		if (htRes.containsKey("06"))
	    		{
	    			numActual=htRes.get("06");
	    			numActual+=resultadosCount.getConteo()[0];
	    			htRes.put("06", numActual);
	    		}else
	    		{	    		
	    			numActual=resultadosCount.getConteo()[0];
	    			htRes.put("06", numActual);	    			
	    		}

	    		// Sumamos la estadística 07 de todos los índices 
	    		if (htRes.containsKey("07"))
	    		{
	    			numActual=htRes.get("07");
	    			numActual+=resultadosCount.getConteo()[1];
	    			htRes.put("07", numActual);
	    		}else
	    		{	    		
	    			numActual=resultadosCount.getConteo()[1];
	    			htRes.put("07", numActual);	    			
	    		}

	    		// Sumamos la estadística 08 de todos los índices 
	    		if (htRes.containsKey("08"))
	    		{	    		
	    			numActual=htRes.get("08");
	    			numActual+=resultadosCount.getConteo()[2];
	    			htRes.put("08", numActual);
	    		}else
	    		{	    		
	    			numActual=resultadosCount.getConteo()[2];
	    			htRes.put("08", numActual);	    			
	    		}

	    		// Sumamos la estadística 09 de todos los índices 
	    		if (htRes.containsKey("09"))
	    		{
	    			numActual=htRes.get("09");
	    			numActual+=resultadosCount.getConteo()[5];
	    			htRes.put("09", numActual);
	    		}else
	    		{	    		
	    			numActual=resultadosCount.getConteo()[5];
	    			htRes.put("09", numActual);	    			
	    		}

	    		// Sumamos la estadística 05 de todos los índices 
	    		if (htRes.containsKey("10"))
	    		{	    		
	    			numActual=htRes.get("10");
	    			numActual+=resultadosCount.getConteo()[6];
	    			htRes.put("10", numActual);
	    		}else
	    		{
	    			numActual=resultadosCount.getConteo()[6];
	    			htRes.put("10", numActual);	    			
	    		}

	    		// Sumamos la estadística 11 de todos los índices 
	    		if (htRes.containsKey("11"))
	    		{
	    			numActual=htRes.get("11");
	    			numActual+=resultadosCount.getConteo()[4];
	    			htRes.put("11", numActual);
	    		}else
	    		{	    	
	    			numActual=resultadosCount.getConteo()[4];
	    			htRes.put("11", numActual);	    			
	    		}

			}			
						
    		estadisticasAgrega.addEstadistica(covert2estadistica("05", "Bachillerato", String.valueOf(htRes.get("05")), "0"));
    		estadisticasAgrega.addEstadistica(covert2estadistica("06", "Educacion Infantil", String.valueOf(htRes.get("06")), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("07", "Educacion Primaria", String.valueOf(htRes.get("07")), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("08", "Educacion Secundaria Obligatoria", String.valueOf(htRes.get("08")), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("09", "Enseñanzas Artisticas", String.valueOf(htRes.get("09")), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("10", "Enseñanza Oficial Idiomas", String.valueOf(htRes.get("10")), "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("11", "Formacion Profesional", String.valueOf(htRes.get("11")), "0"));
			
			if (logger.isDebugEnabled())
				logger.debug("Termina de procesar estadísticas locales de cobertura curricular");			
			
		} catch (Exception ex) {
			logger.warn("No se ha podido obtener las estadisticas de COBERTURA CURRICULAR" + ex.getCause());
			estadisticasAgrega.addEstadistica(covert2estadistica("05", "Bachillerato", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("06", "Educacion Infantil", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("07", "Educacion Primaria", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("08", "Educacion Secundaria Obligatoria", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("09", "Enseñanzas Artisticas", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("10", "Enseñanza Oficial Idiomas", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("11", "Formacion Profesional", "0", "0"));
		}
		
		// LICENCIAS
		try {
			if (logger.isDebugEnabled())
				logger.debug("Procesando estadísticas locales de licencias");

			Calendar todosLosTiempos = Calendar.getInstance();
			todosLosTiempos.setTime(new Date(0));
			
			ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
			parametrosInformeVO.setFechaDesde(todosLosTiempos);
			parametrosInformeVO.setFechaHasta(Calendar.getInstance());
			parametrosInformeVO.setIdioma(obtenerIdiomaBusqueda());
			
			InformeLicenciasOdesVO[] informesLicencias = this.getSrvAuditoriaServicio().informeLicenciasOdes(parametrosInformeVO);
			
			int i = 12;
			for (InformeLicenciasOdesVO informe : informesLicencias) {
				logger.debug(informe.getLicencia() + ": " + informe.getNumOdes());
				estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(i), informe.getLicencia(), String.valueOf(informe.getNumOdes()), "0"));
				i++;
			}
			
			if (logger.isDebugEnabled())
				logger.debug("Termina de procesar estadísticas locales de licencias");

		} catch (Exception ex) {
			estadisticasAgrega.addEstadistica(covert2estadistica("12", "licencia propietaria", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("13", "licencia libre EUPL", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("14", "licencia libre GPL", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("15", "licencia libre dual GPL y EUPL", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("16", "otras licencias libres", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("17", "dominio público", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("18", "no corresponde", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("19", "licencia propietaria intelectual", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("20", "creative commons: reconocimiento", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("21", "creative commons: reconocimiento - sin obra derivada", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("22", "creative commons: reconocimiento - sin obra derivada - no comercial", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("23", "creative commons: reconocimiento - no comercial", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("24", "creative commons: reconocimiento - no comercial - compartir igual", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("25", "creative commons: reconocimiento - compartir igual", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("26", "licencia GFDL", "0", "0"));
			
		}
		
		// ODES
		try {
			if (logger.isDebugEnabled())
				logger.debug("Procesando estadísticas locales de odes");

			ResultadosCountVO resultadosCountVO = this.getSrvBuscadorService().obtenerTotalesRepositorio();
			estadisticasAgrega.addEstadistica(covert2estadistica("27", "NumLocalCursos", String.valueOf(resultadosCountVO.getConteo()[3]),"0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("28", "NumLocalMediosIntegrados", String.valueOf(resultadosCountVO.getConteo()[0]),"0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("29", "NumLocalObjetos", resultadosCountVO.getDocumentosCount().toString(),"0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("30", "NumLocalObjetosAprendizaje", String.valueOf(resultadosCountVO.getConteo()[1]),"0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("31", "NumLocalSecuencias", String.valueOf(resultadosCountVO.getConteo()[2]), "0"));
			
			if (logger.isDebugEnabled())
				logger.debug("Termina de procesar estadísticas locales de odes");

		}catch (Exception ex) { 
			logger.warn("No se ha podido obtener los valores de las estadisticas de ODES locales" + ex.getCause());
			estadisticasAgrega.addEstadistica(covert2estadistica("27", "NumLocalCursos", "0","0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("28", "NumLocalMediosIntegrados", "0","0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("29", "NumLocalObjetos", "0","0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("30", "NumLocalObjetosAprendizaje", "0", "0"));
			estadisticasAgrega.addEstadistica(covert2estadistica("31", "NumLocalSecuencias", "0","0"));
		}		
		
		// TERMINOS TODOS LOS TIEMPOS
		try {
			Calendar todosLosTiempos = Calendar.getInstance();
            todosLosTiempos.setTime(new Date(0));
			ParametrosInformeVO parametrosInformeTerminosTotal = new ParametrosInformeVO();
			parametrosInformeTerminosTotal.setFechaDesde(todosLosTiempos);			
			parametrosInformeTerminosTotal.setFechaHasta(Calendar.getInstance());
			parametrosInformeTerminosTotal.setRango(5);
			
			agregarEstaditsticasTerminos(this.getSrvAuditoriaServicio().informeTerminosBusqueda(parametrosInformeTerminosTotal), BLOQUE_TERMINOS_TOTAL, estadisticasAgrega);
		}catch (Exception ex) {
			logger.warn("Error creando las estadisticas locales de los terminos mas buscados totales" + ex.getCause());
		}
		
		// TERMINOS AÑO
		try{
			Calendar anioEnCurso = Calendar.getInstance();
			anioEnCurso.set(Calendar.HOUR, -12);
			anioEnCurso.set(Calendar.MINUTE, 0);
			anioEnCurso.set(Calendar.SECOND, 0);
			anioEnCurso.set(Calendar.MONTH, 0);
			anioEnCurso.set(Calendar.DATE, 1);
			ParametrosInformeVO parametrosInformeTerminosAnio = new ParametrosInformeVO();
			parametrosInformeTerminosAnio.setFechaDesde(anioEnCurso);			
			parametrosInformeTerminosAnio.setFechaHasta(Calendar.getInstance());
			parametrosInformeTerminosAnio.setRango(5);

			agregarEstaditsticasTerminos(this.getSrvAuditoriaServicio().informeTerminosBusqueda(parametrosInformeTerminosAnio), BLOQUE_TERMINOS_ANIO, estadisticasAgrega);
		}catch (Exception ex) {
			logger.warn("Error creando las estadisticas locales de los terminos mas buscados totales"+ ex.getCause());
			logger.debug("",ex);
		}
		
		// TERMINOS MES
		try {
			Calendar mesEnCurso = Calendar.getInstance();
			mesEnCurso.set(Calendar.HOUR, -12);
			mesEnCurso.set(Calendar.MINUTE, 0);
			mesEnCurso.set(Calendar.SECOND, 0);
			mesEnCurso.set(Calendar.DATE, 1);
			ParametrosInformeVO parametrosInformeTerminosMes = new ParametrosInformeVO();
			parametrosInformeTerminosMes.setFechaDesde(mesEnCurso);			
			parametrosInformeTerminosMes.setFechaHasta(Calendar.getInstance());
			parametrosInformeTerminosMes.setRango(5);

			agregarEstaditsticasTerminos(this.getSrvAuditoriaServicio().informeTerminosBusqueda(parametrosInformeTerminosMes), BLOQUE_TERMINOS_MES, estadisticasAgrega);
		} catch (Exception ex) {
			logger.warn("Error creando las estadisticas locales de los terminos mas buscados del mes"+ ex.getCause());
		}	
		
		// TERMINOS SEMANA
		try {
			Calendar todosLosTiempos = Calendar.getInstance();
            todosLosTiempos.setTime(new Date(0));
			Calendar semanaEnCurso = Calendar.getInstance();
			semanaEnCurso.set(Calendar.HOUR, -12);
			semanaEnCurso.set(Calendar.MINUTE, 0);
			semanaEnCurso.set(Calendar.SECOND, 0);
			semanaEnCurso.add(Calendar.DATE, - (todosLosTiempos.get(Calendar.DAY_OF_WEEK)-2));
			ParametrosInformeVO parametrosInformeTerminosSemana = new ParametrosInformeVO();
			parametrosInformeTerminosSemana.setFechaDesde(semanaEnCurso);			
			parametrosInformeTerminosSemana.setFechaHasta(Calendar.getInstance());
			parametrosInformeTerminosSemana.setRango(5);

			agregarEstaditsticasTerminos(this.getSrvAuditoriaServicio().informeTerminosBusqueda(parametrosInformeTerminosSemana), BLOQUE_TERMINOS_SEMANA, estadisticasAgrega);
		} catch (Exception ex) {
			logger.warn("Error creando las estadisticas locales de los terminos mas buscados de a semana"+ ex.getCause());
		}
		
		// TERMINOS DIA
		try {
	        Calendar inicioDiaActual = Calendar.getInstance();
	        inicioDiaActual.add(Calendar.DATE, -1);
	        inicioDiaActual.set(Calendar.AM_PM, 0);
	        inicioDiaActual.set(Calendar.HOUR, 0);
	        inicioDiaActual.set(Calendar.MINUTE, 0);
	        inicioDiaActual.set(Calendar.SECOND, 0);
	        
	        Calendar finDiaActual = Calendar.getInstance();
	        finDiaActual.add(Calendar.DATE,- 1);
	        finDiaActual.set(Calendar.AM_PM, 1);
	        finDiaActual.set(Calendar.HOUR, 11);
	        finDiaActual.set(Calendar.MINUTE, 59);
	        finDiaActual.set(Calendar.SECOND, 59);
	        
			ParametrosInformeVO parametrosInformeTerminosDia = new ParametrosInformeVO();
			parametrosInformeTerminosDia.setFechaDesde(inicioDiaActual);			
			parametrosInformeTerminosDia.setFechaHasta(finDiaActual);
			parametrosInformeTerminosDia.setRango(5);

			agregarEstaditsticasTerminos(this.getSrvAuditoriaServicio().informeTerminosBusqueda(parametrosInformeTerminosDia), BLOQUE_TERMINOS_DIA, estadisticasAgrega);
		} catch (Exception e) {
			logger.warn("Error creando las estadisticas locales de los terminos mas buscados diarios"+ e.getCause());
		} 
		
		Set<Integer> estPresentes = new HashSet<Integer>();
		for(Estadistica est : estadisticasAgrega.getEstadistica()){
			estPresentes.add(Integer.valueOf(est.getCodigo()));
		}
		
		for(int k=1; k<52; k++) {
			if(!estPresentes.contains(k)) {
				estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(k), "", "0","0"));
			}
		}
		
		estadisticasSinVacios(estadisticasAgrega);
		
		Marshaller marshaller = new Marshaller(osw);
		marshaller.setEncoding("iso-8859-1");
		marshaller.marshal(estadisticasAgrega);
		try {
			if (osw != null)
				osw.close();
			if (fos != null)
				fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		logger.debug("crearFicheroEstadisticas ends");
		return true;
	}

	protected Boolean handleCrearEstadisticasTotales() throws Exception {
		
//		logger.debug("crearFicheroEstadisticasTotales begins");
		
		String[] comunidades;
		int totales[] = new int [POSICION_INICIO_TERMINOS];
		Map<String, Integer> masBuscadosTotal = new HashMap<String, Integer>();
		Map<String, Integer> masBuscadosAnio = new HashMap<String, Integer>();
		Map<String, Integer> masBuscadosMes = new HashMap<String, Integer>();
		Map<String, Integer> masBuscadosSemana = new HashMap<String, Integer>();
		Map<String, Map<String,Integer>> masBuscadosTotalAux = new HashMap<String, Map<String,Integer>>();
		Map<String, Map<String,Integer>> masBuscadosAnioAux = new HashMap<String, Map<String,Integer>>();
		Map<String, Map<String,Integer>> masBuscadosMesAux = new HashMap<String, Map<String,Integer>>();
		Map<String, Map<String,Integer>> masBuscadosSemanaAux = new HashMap<String, Map<String,Integer>>();
		Map<String, ArrayList<NodoEstadistica>> nodosEstadisticaTotalMap = new HashMap<String, ArrayList<NodoEstadistica>>();
		
		File directorio = null;
		File estTot = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;		
		
		try {
			directorio = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_ESTADISTICAS));
		} catch (Exception ex) {
			logger.error("No existe el dicrectorio que deberia contener los ficheros de estadisticas locales");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de estadisticas locales");
		}
		
		estTot=new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_ESTADISTICAS)+ "/" + NOMBRE_FICHERO_ESTADISTICAS_TOTALES +EXTENSION_FICHERO_ESTADISTICAS);
		if (estTot.exists()) {
			if(estTot.delete()) {
				logger.info("Se ha borrado el fichero de est totales");
			} else {
				logger.info("No se ha borrado el fichero de est totales");
			}
			
		}
		
		int numFicEstadisticasNodo = 0;
		
		
		
		if (directorio.list() != null) {
			
			for (String nomFile : directorio.list()) { 
				
				if (nomFile.startsWith(NOMBRE_FICHERO_ESTADISTICAS) 
						&& nomFile.endsWith(EXTENSION_FICHERO_ESTADISTICAS)
						&& !nomFile.equals(NOMBRE_FICHERO_ESTADISTICAS_TOTALES + EXTENSION_FICHERO_ESTADISTICAS)){
					numFicEstadisticasNodo++;
				}
			}

			Vector<String> vFicheros = new Vector<String>();
			
			File[] aFicheros = directorio.listFiles();
			for(int i = 0; i < aFicheros.length; i++) {
				vFicheros.add(aFicheros[i].toString());
            }
			
			Collections.sort(vFicheros, String.CASE_INSENSITIVE_ORDER);
						
			comunidades = new String[numFicEstadisticasNodo];			
			
			int i = 0;
			for (String nombreFicheroEst : vFicheros) {

				if (nombreFicheroEst.contains(NOMBRE_FICHERO_ESTADISTICAS) 
						&& nombreFicheroEst.endsWith(EXTENSION_FICHERO_ESTADISTICAS)
						&& !nombreFicheroEst.contains(NOMBRE_FICHERO_ESTADISTICAS_TOTALES + EXTENSION_FICHERO_ESTADISTICAS)){
					
					Unmarshaller unmarshaller = new Unmarshaller(EstadisticasAgrega.class);
					unmarshaller.setValidation(false);
					EstadisticasAgrega estadisticasAgrega = null;
					
					try {
						File ficheroTmpLectura = new File(nombreFicheroEst);
						fis = new FileInputStream(ficheroTmpLectura);
						isr = new InputStreamReader(fis);
						estadisticasAgrega = (EstadisticasAgrega) unmarshaller.unmarshal(isr);	
					} catch (Exception e) {
						logger.error("No se ha podido parsear correctamente el fichero de estadisticas locales " + nombreFicheroEst);
						throw new Exception("No se ha podido parsear correctamente el fichero de estadisticas locales " + nombreFicheroEst);
					} finally {
						try {
							if (fis != null)
								fis.close();
							if (isr != null)
								isr.close();
						} catch (Exception e) {
							logger.warn("No se puede cerrar los stream de lectura del fichero xml de estadisticas: " + nombreFicheroEst + " "+ e.getCause());
							
						}
					}
					
					try {
						
						String dateFormat = "dd/MM/yyyy";
						SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
						Date fechaFichero = sdf.parse(estadisticasAgrega.getFecha());
						String fechaHoyAux = sdf.format(new Date());
						Date fechaHoy = sdf.parse(fechaHoyAux);

						String sNombreNodo = estadisticasAgrega.getNodo();
						// Si la fecha del fichero es anterior a hoy lo marcamos para informar al usuario								
						if (fechaFichero.before(fechaHoy)) {
 							
							// Lanzamos correo de alarma de fichero obsoleto
							envioCorreoAlarma(sNombreNodo, INCIDENCIA_FICHERO_OBSOLETO);
							
							//sNombreNodo+=" (*)";
							//htNodos.put(estadisticasAgrega.getNodo(),	sNombreNodo);
						}
//						else
//						{
//							htNodos.put(estadisticasAgrega.getNodo(),	sNombreNodo);
//						}
						
						comunidades[i] = estadisticasAgrega.getNodo();
						
						for (Estadistica estadistica : estadisticasAgrega.getEstadistica()) {
							int codigoEstAct = Integer.valueOf(estadistica.getCodigo());
							
							if (codigoEstAct < POSICION_INICIO_TERMINOS){
						
								String valorDatoEstadistico=estadistica.getValor();
								
								if (valorDatoEstadistico==null ||valorDatoEstadistico.equals("") ||valorDatoEstadistico.equals("null"))
								{
									envioCorreoAlarma(estadisticasAgrega.getNodo(), INCIDENCIA_DATO_ESTADISTICO_INCORRECTO);
									valorDatoEstadistico="0";
								}
								
								totales[codigoEstAct-1] = totales[codigoEstAct-1] + Integer.valueOf(valorDatoEstadistico);
	
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								
								// Se verifica si el valor informado en Búsquedas es 0. 
								// Si es 0 se envía correo de alarma
								if ((CODIGO_ESTADISTICA_BUSQUEDAS==codigoEstAct) && (estadistica.getValor().equals("0")))
								{
									// Lanzamos correo de alarma de actividad nula
									envioCorreoAlarma(estadisticasAgrega.getNodo(), INCIDENCIA_ACTIVIDAD_NULA);
								}
								
								nodoEstadistica.setNombreNodoEstadistica(sNombreNodo);
								nodoEstadistica.setValorNodoEstadistica(valorDatoEstadistico);
							
								if (nodosEstadisticaTotalMap.get(String.valueOf(codigoEstAct)) != null) {
									ArrayList<NodoEstadistica> nodosEstadisticaTotalSetTmp = nodosEstadisticaTotalMap.get(String.valueOf(codigoEstAct));
									nodosEstadisticaTotalSetTmp.add(nodoEstadistica);
									nodosEstadisticaTotalMap.put(String.valueOf(codigoEstAct), nodosEstadisticaTotalSetTmp);
								} else {
									ArrayList<NodoEstadistica> nodosEstadisticaTotalSetTmp = new ArrayList<NodoEstadistica>();
									nodosEstadisticaTotalSetTmp.add(nodoEstadistica);
									nodosEstadisticaTotalMap.put(String.valueOf(codigoEstAct), nodosEstadisticaTotalSetTmp);
								}
							}
							if (codigoEstAct>=POSICION_INICIO_TERMINOS 
									&& codigoEstAct<(POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)
									&& estadistica.getDescripcion() != null
									&& !estadistica.getDescripcion().equals("")) {
								if (!masBuscadosTotal.containsKey(estadistica.getDescripcion())) {
									masBuscadosTotal.put(estadistica.getDescripcion(), Integer.valueOf(estadistica.getValor()));
									
									Map<String,Integer> valoresPorComunidad = new HashMap<String, Integer>();
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosTotalAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								} else {
									int valorTotalAux = masBuscadosTotal.get(estadistica.getDescripcion());
									valorTotalAux += Integer.valueOf(estadistica.getValor());
									masBuscadosTotal.put(estadistica.getDescripcion(), valorTotalAux);
									
									Map<String,Integer> valoresPorComunidad = masBuscadosTotalAux.get(estadistica.getDescripcion());
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosTotalAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								}
							}
							if (codigoEstAct>=(POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)
									&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))
									&& estadistica.getDescripcion() != null
									&& !estadistica.getDescripcion().equals("")) {
								if (!masBuscadosAnio.containsKey(estadistica.getDescripcion())) {
									masBuscadosAnio.put(estadistica.getDescripcion(), Integer.valueOf(estadistica.getValor()));
									
									Map<String,Integer> valoresPorComunidad = new HashMap<String, Integer>();
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosAnioAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								} else {
									int valorTotalAux = masBuscadosAnio.get(estadistica.getDescripcion());
									valorTotalAux += Integer.valueOf(estadistica.getValor());
									masBuscadosAnio.put(estadistica.getDescripcion(), valorTotalAux);
									
									Map<String,Integer> valoresPorComunidad = masBuscadosAnioAux.get(estadistica.getDescripcion());
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosAnioAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								}
							}
							if (codigoEstAct>=(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO)) 
									&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))
									&& estadistica.getDescripcion() != null
									&& !estadistica.getDescripcion().equals("")) {
								if (!masBuscadosMes.containsKey(estadistica.getDescripcion())) {
									masBuscadosMes.put(estadistica.getDescripcion(), Integer.valueOf(estadistica.getValor()));
									
									Map<String,Integer> valoresPorComunidad = new HashMap<String, Integer>();
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosMesAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								} else {
									int valorTotalAux = masBuscadosMes.get(estadistica.getDescripcion());
									valorTotalAux += Integer.valueOf(estadistica.getValor());
									masBuscadosMes.put(estadistica.getDescripcion(), valorTotalAux);
									
									Map<String,Integer> valoresPorComunidad = masBuscadosMesAux.get(estadistica.getDescripcion());
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosMesAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								}
							}
							if (codigoEstAct>=(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))
									&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_SEMANA))
									&& estadistica.getDescripcion() != null
									&& !estadistica.getDescripcion().equals("")) {
								if (!masBuscadosSemana.containsKey(estadistica.getDescripcion())) {
									masBuscadosSemana.put(estadistica.getDescripcion(), Integer.valueOf(estadistica.getValor()));
									
									Map<String,Integer> valoresPorComunidad = new HashMap<String, Integer>();
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosSemanaAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								} else {
									int valorTotalAux = masBuscadosSemana.get(estadistica.getDescripcion());
									valorTotalAux += Integer.valueOf(estadistica.getValor());
									masBuscadosSemana.put(estadistica.getDescripcion(), valorTotalAux);
									
									Map<String,Integer> valoresPorComunidad = masBuscadosSemanaAux.get(estadistica.getDescripcion());
									valoresPorComunidad.put(sNombreNodo, Integer.valueOf(estadistica.getValor()));
									masBuscadosSemanaAux.put(estadistica.getDescripcion(), valoresPorComunidad);
								}
							}								
							if (codigoEstAct>(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))
									&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_DIA))
									&& estadistica.getDescripcion() != null
									&& !estadistica.getDescripcion().equals("")) {

								if (!fechaFichero.before(fechaHoy)) {
									EstadisticaImpl estadisticaAux = new EstadisticaImpl();
									estadisticaAux.setCodigo(estadistica.getCodigo());
									estadisticaAux.setDescripcion(estadistica.getDescripcion());
									estadisticaAux.setFechaAlta(fechaHoy);
									estadisticaAux.setNodo(estadisticasAgrega.getNodo());
									estadisticaAux.setValor(Integer.valueOf(estadistica.getValor()));
									this.getEstadisticaDao().create(estadisticaAux);						
								} else {
									logger.warn("El fichero de estadísticas locales del nodo " + estadisticasAgrega.getNodo() + " está desactualizado");
								}
							}							
						}
					} catch (Exception ex) {
						logger.error("Error calculando los totales " + ex.getMessage());
						throw new Exception("Error calculando los totales mientras se sumaban los datos del fichero " + nombreFicheroEst);
					}
						
					try {
						volcarEstadisticasBBDD (estadisticasAgrega);
					} catch (Exception ex) {
						logger.error("Excepcion volcando estadisticas" + ex.getMessage());
						
						//NO LANZO EXCEPCION PARA QUE COMPLETE EL FICHERO DE ESTADISTICAS TOTALES Y SE PUEDA MOSTRAR LAS ESTADISTICAS DE LA PARTE PUBLICA
					}
					
					i++;					
				}

			}
		} else {
			logger.error("El directorio de estadisticas no contiene las estadisticas locales de cada nodo");
			throw new Exception ("El directorio de estadisticas no contiene las estadisticas locales de cada nodo"); 
		}
		
		try {
			
			// Obtenemos los nodos para marcar los obsoletos
			Hashtable<String, String> htNodos = new Hashtable<String, String>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fecHastaDia = sdf.parse(sdf.format(new Date()));
			
			Collection<Object> estadisticasUltimoDia = this.getEstadisticaDao().obtenerEstadisticasActividadPorFechas(fecHastaDia, fecHastaDia);

			for (Object object : estadisticasUltimoDia) {	
				Object[] estadistica = (Object[])object;
				String codigoEst = (String) estadistica[0];
				String nodo = (String) estadistica[1];
				Integer valorDato = (Integer) estadistica[2];	
				
				String nodoOrig = (String) estadistica[1];
				
				if (codigoEst.equals("01") && valorDato.intValue()==0)
				{
					nodo+=" (*)";
					htNodos.put(nodoOrig,nodo);

				}else if (codigoEst.equals("01")) 
				{
					htNodos.put(nodoOrig,nodo);
				}
				
				
			}
						
			
			File ficheroEstLocales = new File(AgregaPropertiesImpl.getInstance().getProperty(
					AgregaProperties.PATH_ESTADISTICAS)
					+ "/"
					+ NOMBRE_FICHERO_ESTADISTICAS
					+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO)
					+ EXTENSION_FICHERO_ESTADISTICAS);
			File ficheroEstTotales = new File(AgregaPropertiesImpl.getInstance()
					.getProperty(AgregaProperties.PATH_ESTADISTICAS)
					+ "/"
					+ NOMBRE_FICHERO_ESTADISTICAS_TOTALES
					+ EXTENSION_FICHERO_ESTADISTICAS);

			fis = new FileInputStream(ficheroEstLocales);
			isr = new InputStreamReader(fis);
			fos = new FileOutputStream(ficheroEstTotales);
			osw = new OutputStreamWriter(fos);
			
			Date date = new Date();
	
			Unmarshaller unmarshaller = new Unmarshaller(EstadisticasAgrega.class);
			unmarshaller.setValidation(false);
			EstadisticasAgrega estadisticasAgrega = (EstadisticasAgrega) unmarshaller.unmarshal(isr);
			
			String[] TerminosMasBuscadosTotalesOrdenados = obtenerLosMasBuscados(masBuscadosTotal);
			String[] TerminosMasBuscadosAnioOrdenados = obtenerLosMasBuscados(masBuscadosAnio);
			String[] TerminosMasBuscadosMesOrdenados = obtenerLosMasBuscados(masBuscadosMes);
			String[] TerminosMasBuscadosSemanaOrdenados = obtenerLosMasBuscados(masBuscadosSemana);
			
			for (Estadistica estadistica : estadisticasAgrega.getEstadistica()) {
				int codigoEstAct = Integer.valueOf(estadistica.getCodigo());
				if (codigoEstAct < POSICION_INICIO_TERMINOS) {

					estadistica.setValorTotal(String.valueOf(totales[codigoEstAct - 1]));
					NodosEstadisticaTotal nodosEstadisticaTotal = new NodosEstadisticaTotal();
					for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotalMap.get(String.valueOf(codigoEstAct))) {
						nodoEstadistica.setNombreNodoEstadistica(htNodos.get(nodoEstadistica.getNombreNodoEstadistica()));
						nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
					}
					//estadistica.setNodosEstadisticaTotal(completarEstadisticasNodos(nodosEstadisticaTotal));
					estadistica.setNodosEstadisticaTotal(nodosEstadisticaTotal);
				}
				if (codigoEstAct>=POSICION_INICIO_TERMINOS 
						&& codigoEstAct<(POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)) {

					NodosEstadisticaTotal nodosEstadisticaTotal = new NodosEstadisticaTotal();
					if (!TerminosMasBuscadosTotalesOrdenados[codigoEstAct - POSICION_INICIO_TERMINOS].equals("")
							&&TerminosMasBuscadosTotalesOrdenados[codigoEstAct - POSICION_INICIO_TERMINOS] != null) {
						estadistica.setDescripcion(TerminosMasBuscadosTotalesOrdenados[codigoEstAct - POSICION_INICIO_TERMINOS]);
						estadistica.setValorTotal(String.valueOf(masBuscadosTotal.get(TerminosMasBuscadosTotalesOrdenados[codigoEstAct - POSICION_INICIO_TERMINOS])));
						if (masBuscadosTotalAux.get(estadistica.getDescripcion()).containsKey(estadisticasAgrega.getNodo())) {
							estadistica.setValor(masBuscadosTotalAux.get(estadistica.getDescripcion()).get(estadisticasAgrega.getNodo()).toString());
						} else {
							estadistica.setValor("0");
						}
						
						for (String comunidad : comunidades) {
							if (masBuscadosTotalAux.get(estadistica.getDescripcion()).containsKey(comunidad)){
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica(masBuscadosTotalAux.get(estadistica.getDescripcion()).get(comunidad).toString());
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							} else {
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica("0");
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							}
						}
					}
					
					estadistica.setNodosEstadisticaTotal(nodosEstadisticaTotal);
					//estadistica.setNodosEstadisticaTotal(completarEstadisticasNodos(nodosEstadisticaTotal));
				} else if (codigoEstAct>=(POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)
						&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))) {

					NodosEstadisticaTotal nodosEstadisticaTotal = new NodosEstadisticaTotal();
					if (!TerminosMasBuscadosAnioOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)].equals("")
							&& TerminosMasBuscadosAnioOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)] != null) {	
					estadistica.setDescripcion(TerminosMasBuscadosAnioOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)]);
						estadistica.setValorTotal(String.valueOf(masBuscadosAnio.get(TerminosMasBuscadosAnioOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+NUMERO_TERMINOS_POR_BLOQUE)])));
						if (masBuscadosAnioAux.get(estadistica.getDescripcion()).containsKey(estadisticasAgrega.getNodo())) {
							estadistica.setValor(masBuscadosAnioAux.get(estadistica.getDescripcion()).get(estadisticasAgrega.getNodo()).toString());
						} else {
							estadistica.setValor("0");
						}
						
						for (String comunidad : comunidades) {
							if (masBuscadosAnioAux.get(estadistica.getDescripcion()).containsKey(comunidad)){
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica(masBuscadosAnioAux.get(estadistica.getDescripcion()).get(comunidad).toString());
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							} else {
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica("0");
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							}
						}
					}
						
					estadistica.setNodosEstadisticaTotal(nodosEstadisticaTotal);
					//estadistica.setNodosEstadisticaTotal(completarEstadisticasNodos(nodosEstadisticaTotal));
				} else if (codigoEstAct>=(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO)) 
						&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))) {

					NodosEstadisticaTotal nodosEstadisticaTotal = new NodosEstadisticaTotal();
					if (!TerminosMasBuscadosMesOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))].equals("")
							&& TerminosMasBuscadosMesOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))] != null) {
						estadistica.setDescripcion(TerminosMasBuscadosMesOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))]);
						estadistica.setValorTotal(String.valueOf(masBuscadosMes.get(TerminosMasBuscadosMesOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_ANIO))])));
						if (masBuscadosMesAux.get(estadistica.getDescripcion()).containsKey(estadisticasAgrega.getNodo())) {
							estadistica.setValor(masBuscadosMesAux.get(estadistica.getDescripcion()).get(estadisticasAgrega.getNodo()).toString());
						} else {
							estadistica.setValor("0");
						}
						
						for (String comunidad : comunidades) {
							if (masBuscadosMesAux.get(estadistica.getDescripcion()).containsKey(comunidad)){
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica(masBuscadosMesAux.get(estadistica.getDescripcion()).get(comunidad).toString());
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							} else {
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica("0");
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							}
						}
					}	
						
					//estadistica.setNodosEstadisticaTotal(completarEstadisticasNodos(nodosEstadisticaTotal));
					estadistica.setNodosEstadisticaTotal(nodosEstadisticaTotal);
				} else if (codigoEstAct>=(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))
						&& codigoEstAct<(POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_SEMANA))) {

					NodosEstadisticaTotal nodosEstadisticaTotal = new NodosEstadisticaTotal();
					if (!TerminosMasBuscadosSemanaOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))].equals("")
							&& TerminosMasBuscadosSemanaOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))] != null) {
						estadistica.setDescripcion(TerminosMasBuscadosSemanaOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))]);
						estadistica.setValorTotal(String.valueOf(masBuscadosSemana.get(TerminosMasBuscadosSemanaOrdenados[codigoEstAct - (POSICION_INICIO_TERMINOS+(NUMERO_TERMINOS_POR_BLOQUE*BLOQUE_TERMINOS_MES))])));
						if (masBuscadosSemanaAux.get(estadistica.getDescripcion()).containsKey(estadisticasAgrega.getNodo())) {
							estadistica.setValor(masBuscadosSemanaAux.get(estadistica.getDescripcion()).get(estadisticasAgrega.getNodo()).toString());
						} else {
							estadistica.setValor("0");
						}
						
						for (String comunidad : comunidades) {
							if (masBuscadosSemanaAux.get(estadistica.getDescripcion()).containsKey(comunidad)){
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica(masBuscadosSemanaAux.get(estadistica.getDescripcion()).get(comunidad).toString());
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							} else {
								NodoEstadistica nodoEstadistica = new NodoEstadistica();
								nodoEstadistica.setNombreNodoEstadistica(htNodos.get(comunidad));
								nodoEstadistica.setValorNodoEstadistica("0");
								nodosEstadisticaTotal.addNodoEstadistica(nodoEstadistica);
							}
						}
					}
					
					estadistica.setNodosEstadisticaTotal(nodosEstadisticaTotal);
					//estadistica.setNodosEstadisticaTotal(completarEstadisticasNodos(nodosEstadisticaTotal));
				}
			}

			estadisticasAgrega.setNodo(NODO_ESTADISTICAS_TOTALES);
			estadisticasAgrega.setFecha(sdf.format(date));

			Marshaller marshaller = new Marshaller(osw);
			marshaller.setEncoding("iso-8859-1");
			marshaller.marshal(estadisticasAgrega);

		} catch (Exception ex) {
			logger.error("No se ha podido rellenar el fichero de estadisticas local con los valores totales." + ex.getMessage());
		} finally {
			try {
				if (osw != null)
					osw.close();
				if (fos != null)
					fos.close();
				if (fis != null)
					fis.close();
				if (isr != null)
					isr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		logger.debug("crearFicheroEstadisticasTotales ends");
		
		return true;
	}
	
	private Estadistica covert2estadistica (String codigo, String descripcion, String valor, String valorTotal) {
		Estadistica estadistica = new Estadistica();
		estadistica.setCodigo((codigo == null || codigo.equals("")) ? "" : codigo);
		estadistica.setDescripcion((descripcion == null || descripcion.equals("")) ? "" : descripcion);
		estadistica.setValor((valor == null || valor.equals("")) ? "" : valor);
		estadistica.setValorTotal((valorTotal == null || valorTotal.equals("")) ? "" : valorTotal);
		return estadistica;
	}

	private String[] obtenerLosMasBuscados (Map<String,Integer> terminosMasBuscadosMap) {
		String maxEntry = "";
		String[] arrayDeTerminosPorOrden = new String[5];
		Set<String> conjAuxDeYaBuscados = new HashSet<String>();
		
		for (int i = 0; i <5; i++) {
			for (String termino : terminosMasBuscadosMap.keySet()) {
			    if ((maxEntry == "" || terminosMasBuscadosMap.get(termino).compareTo(terminosMasBuscadosMap.get(maxEntry)) > 0)
			    		&& !conjAuxDeYaBuscados.contains(termino))
			    {
			        maxEntry = termino;
			    }
			}
			conjAuxDeYaBuscados.add(maxEntry);
			arrayDeTerminosPorOrden[i] = maxEntry;
			maxEntry = "";
		}
		
		return arrayDeTerminosPorOrden;
	}

	private void volcarEstadisticasBBDD (EstadisticasAgrega estadisticasAgrega) throws Exception {
		
//		logger.debug("volcarEstadisticasBBDD begins");
		
		java.util.Collection estadisticasAcumuladas = null;
		Date fechaDesde = new Date(0);
		Date fechaHasta = new Date();
		Map<String,Integer> mapaEstadisticasAcumuladasPorNodo = new HashMap<String, Integer>();		
		
		try {
			estadisticasAcumuladas = this.getEstadisticaDao().obtenerEstadisticasAgrupadasPorFechasYNodo(fechaDesde, fechaHasta, estadisticasAgrega.getNodo());
			
			for (Object object : estadisticasAcumuladas) {
				Object[] estadistica = (Object[])object;
				mapaEstadisticasAcumuladasPorNodo.put((String)estadistica[0], ((Integer)estadistica[1]).intValue());
			}
		} catch (Exception ex) {
			throw new Exception("No se han podido recuperar las estadisticas acumuladas de los 25 primeros elementos");
		}
		
		try {
			for (Estadistica estadistica : estadisticasAgrega.getEstadistica()) {
				if (Integer.valueOf(estadistica.getCodigo()) < POSICION_INICIO_TERMINOS) {
					
					int valorDiario = 0;

					if (mapaEstadisticasAcumuladasPorNodo.get(estadistica.getCodigo()) != null) {
						valorDiario = (Integer.valueOf(estadistica.getValor()) - mapaEstadisticasAcumuladasPorNodo.get(estadistica.getCodigo()));
					} else {
						valorDiario = Integer.valueOf(estadistica.getValor());
					}
					// 20130517 Se comenta para permitir que se inserte negativos (despublicaciones)
					//valorDiario = (valorDiario > 0) ? valorDiario : 0;

					// Verificamos si se van a insertar números negativos en la estadística de búsqueda.
					// Si es así, lanzamos correo de alarma de actividad negativa						
					if (estadistica.getCodigo().equals("1") && valorDiario<0)
					{				
						envioCorreoAlarma(estadisticasAgrega.getNodo(), INCIDENCIA_ACTIVIDAD_NEGATIVA);
					}
					
					String dateFormat = "dd/MM/yyyy";
					SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
					Date fechaFichero = sdf.parse(estadisticasAgrega.getFecha());
					String fechaHoyAux = sdf.format(new Date());
					Date fechaHoy = sdf.parse(fechaHoyAux);
					
					EstadisticaImpl estadisticaAux = new EstadisticaImpl();
					estadisticaAux.setCodigo(estadistica.getCodigo());
					estadisticaAux.setDescripcion(estadistica.getDescripcion());
					estadisticaAux.setFechaAlta(fechaHoy);
					estadisticaAux.setNodo(estadisticasAgrega.getNodo());
					
					if (!fechaFichero.before(fechaHoy)) {
						estadisticaAux.setValor(valorDiario);

					} else {
						logger.warn("El fichero de estadísticas locales del nodo " + estadisticasAgrega.getNodo() + " está desactualizado. Se insertará valor 0");
						estadisticaAux.setValor(0);
					}
					this.getEstadisticaDao().create(estadisticaAux);
				}
			}
		} catch (Exception ex) {
			throw new Exception("No se ha podido volcar a BBDD las estadisticas diarias de los 25 primeros elementos");
		}
//		logger.debug("volcarEstadisticasBBDD ends");
	}

	private String obtenerIdiomaBusqueda() throws Exception {
		String idiomaNavegacion = "";
		if (LdapUserDetailsUtils.estaAutenticado()) {
			try {
				idiomaNavegacion = LdapUserDetailsUtils.getIdiomaBusqueda();
				if (idiomaNavegacion == null)
					idiomaNavegacion = I18n.getInstance()
							.obtenerIdiomaDefectoPlataforma();
			} catch (Exception e) {
				logger.warn("BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades."+e.getCause());
				logger.debug("",e);
				idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}
		} else
			idiomaNavegacion = I18n.getInstance()
					.obtenerIdiomaDefectoPlataforma();
		return idiomaNavegacion;
	}

	private void agregarEstaditsticasTerminos(InformeTerminoBusquedaVO[] informeTerminosBusqueda, int bloque, EstadisticasAgrega estadisticasAgrega) {
		int posicion;
		int limitePosicion;
					
		switch (bloque) {
			case BLOQUE_TERMINOS_TOTAL:
				posicion = POSICION_INICIO_TERMINOS;
				limitePosicion = posicion + NUMERO_TERMINOS_POR_BLOQUE;
				
				if (informeTerminosBusqueda != null) {
					for (InformeTerminoBusquedaVO informeTermino : informeTerminosBusqueda) {
						if (posicion < limitePosicion) {
							estadisticasAgrega.addEstadistica(
									covert2estadistica(String.valueOf(posicion), informeTermino.getTerminoBuscado(), String.valueOf(informeTermino.getNumVeces()), "0"));
						} else {
							break;
						}
						posicion++;
					}
					
					if (informeTerminosBusqueda.length < NUMERO_TERMINOS_POR_BLOQUE) {
						for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE - informeTerminosBusqueda.length; j++) {
							estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
						}
					}
				} else {
					for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE; j++) {
						estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
					}
				}
				break;
				
			case BLOQUE_TERMINOS_ANIO:
				posicion = POSICION_INICIO_TERMINOS + NUMERO_TERMINOS_POR_BLOQUE;
				limitePosicion = posicion + NUMERO_TERMINOS_POR_BLOQUE;
				
				if (informeTerminosBusqueda != null) {
					for (InformeTerminoBusquedaVO informeTermino : informeTerminosBusqueda) {
						if (posicion < limitePosicion) {
							estadisticasAgrega.addEstadistica(
									covert2estadistica(String.valueOf(posicion), informeTermino.getTerminoBuscado(), String.valueOf(informeTermino.getNumVeces()), "0"));
						} else {
							break;
						}
						posicion++;
					}
					
					if (informeTerminosBusqueda.length < NUMERO_TERMINOS_POR_BLOQUE) {
						for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE - informeTerminosBusqueda.length; j++) {
							estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
						}
					}
				} else {
					for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE; j++) {
						estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
					}
				}
				break;
				
			case BLOQUE_TERMINOS_MES:
				posicion = POSICION_INICIO_TERMINOS + (2 * NUMERO_TERMINOS_POR_BLOQUE);
				limitePosicion = posicion + NUMERO_TERMINOS_POR_BLOQUE;
				
				if (informeTerminosBusqueda != null) {
					for (InformeTerminoBusquedaVO informeTermino : informeTerminosBusqueda) {
						if (posicion < limitePosicion) {
							estadisticasAgrega.addEstadistica(
									covert2estadistica(String.valueOf(posicion), informeTermino.getTerminoBuscado(), String.valueOf(informeTermino.getNumVeces()), "0"));
						} else {
							break;
						}
						posicion++;
					}
					
					if (informeTerminosBusqueda.length < NUMERO_TERMINOS_POR_BLOQUE) {
						for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE - informeTerminosBusqueda.length; j++) {
							estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
						}
					}
				} else {
					for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE; j++) {
						estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
					}
				}
				break;
			
			case BLOQUE_TERMINOS_SEMANA:
				posicion = POSICION_INICIO_TERMINOS + (3 * NUMERO_TERMINOS_POR_BLOQUE);
				limitePosicion = posicion + NUMERO_TERMINOS_POR_BLOQUE;
				
				if (informeTerminosBusqueda != null) {
					for (InformeTerminoBusquedaVO informeTermino : informeTerminosBusqueda) {
						if (posicion < limitePosicion) {
							estadisticasAgrega.addEstadistica(
									covert2estadistica(String.valueOf(posicion), informeTermino.getTerminoBuscado(), String.valueOf(informeTermino.getNumVeces()), "0"));
						} else {
							break;
						}
						posicion++;
					}
					
					if (informeTerminosBusqueda.length < NUMERO_TERMINOS_POR_BLOQUE) {
						for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE - informeTerminosBusqueda.length; j++) {
							estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
						}
					}
				} else {
					for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE; j++) {
						estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
					}
				}
				break;
			
			case BLOQUE_TERMINOS_DIA:
				posicion = POSICION_INICIO_TERMINOS + (4 * NUMERO_TERMINOS_POR_BLOQUE);
				limitePosicion = posicion + NUMERO_TERMINOS_POR_BLOQUE;
				
				if (informeTerminosBusqueda != null) {
					for (InformeTerminoBusquedaVO informeTermino : informeTerminosBusqueda) {
						if (posicion < limitePosicion) {
							estadisticasAgrega.addEstadistica(
									covert2estadistica(String.valueOf(posicion), informeTermino.getTerminoBuscado(), String.valueOf(informeTermino.getNumVeces()), "0"));
						} else {
							break;
						}
						posicion++;
					}
					
					if (informeTerminosBusqueda.length < NUMERO_TERMINOS_POR_BLOQUE) {
						for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE - informeTerminosBusqueda.length; j++) {
							estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
						}
					}
				} else {
					for (int j=0; j < NUMERO_TERMINOS_POR_BLOQUE; j++) {
						estadisticasAgrega.addEstadistica(covert2estadistica(String.valueOf(posicion+j),"","0","0"));
					}
				}
				break;
			
			default:
				logger.warn("Nunca debio entrar en este bloque de terminos ams buscados");
		}
	}
	
	private void estadisticasSinVacios (EstadisticasAgrega estadisticasAgrega) {
		for (Estadistica estadistica : estadisticasAgrega.getEstadistica()) {
			if(estadistica.getValor().equals("")) {estadistica.setValor("0");}
			if(estadistica.getValorTotal().equals("")) {estadistica.setValorTotal("0");}
		}
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
	 * Obtiene la lista de direcciones de correo del administrador del nodo	
	 * @return String
	 * @param  String Identificador del nodo
	 */
	private void envioCorreoAlarma(String nodo, String tipoIncidencia)
	{							

		try {
			if (logger.isDebugEnabled())
				logger.debug("Envio de correo de alarma en nodo : " + nodo + " por : "+ tipoIncidencia);
			
			String envioCorreoAlarmaActivo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ACTIVO_ENVIO_CORREOS_INCIDENCIA_COMUNICACION);
			
			if (envioCorreoAlarmaActivo!=null && envioCorreoAlarmaActivo.equals("true"))
			{
				logger.debug("Se envía correo de alarma");
				SrvCorreo srvCorreo = this.getSrvCorreo();		
				srvCorreo.enviarCorreoIncidencia(getCorreosAdministradorNodo(nodo), getCorreosAdministradorNodoINTEF(), tipoIncidencia);
			}else
				logger.info("El envío de correo de alrma está inactivo");
			
		}catch (Exception e) {
			logger.error("Error al enviar correo de alarma " + tipoIncidencia +" : ",e); 
		}		
	}
	/**
	 * Obtiene la lista de direcciones de correo del administrador del nodo	
	 * @return String
	 * @param  String Identificador del nodo
	 */
	private String getCorreosAdministradorNodo(String nodo)
	{
		String listaCorreosAdminNodo = "";

		String sCorreosAdminNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CORREO_INCIDENCIA_COMUNICACION_NODO + "_" + nodo);		

		if (sCorreosAdminNodo!=null && !sCorreosAdminNodo.equals(""))
			listaCorreosAdminNodo= sCorreosAdminNodo;
				
		if (logger.isDebugEnabled())
			logger.debug("Devuelve los correos del nodo :" + nodo + " : " + listaCorreosAdminNodo);
		return listaCorreosAdminNodo;
	}
	
	/**
	 * Obtiene la lista de direcciones de correo del administrador del nodo	del INTEF
	 * @return String
	 */
	private String getCorreosAdministradorNodoINTEF()
	{
		String listaCorreosAdminNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CORREO_INCIDENCIA_COMUNICACION_NODO_MECD);	
		if (logger.isDebugEnabled())
			logger.debug("Devuelve los correos del INTEF: " + listaCorreosAdminNodo);
		return listaCorreosAdminNodo;
	}	
}