/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.entregar.negocio.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.transform.stream.StreamSource;
//import org.apache.commons.validator.Form;

import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;


import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.xslt.Transformador;
import es.agrega.soporte.xslt.TransformadorSaxonImpl;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.entregar.negocio.dominio.CacheFicheros;
import es.pode.entregar.negocio.dominio.CacheFicherosDao;
import es.pode.entregar.negocio.dominio.IdentificadorCriteria;
import es.pode.entregar.negocio.dominio.RutaTemporalCriteria;
import es.pode.entregar.negocio.utils.GeneradorHtml;
import es.pode.entregar.negocio.utils.OrganizationStringComparator;
import es.pode.entregar.servicio.dominio.EntregarDozerDao;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Item;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.Organization;
import es.pode.parseadorXML.castor.Resource;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.serializar.SerializadorObjetos;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.uuid.PodeUUIDGenerator;
import es.pode.soporte.validador.TipoOde;
import es.pode.validador.negocio.servicio.SrvValidadorService;
import es.pode.validador.negocio.servicio.ValidaVO;

/**
 * @see es.pode.entregar.negocio.servicios.SrvEntregarService
 */

public class SrvEntregarServiceImpl extends
		es.pode.entregar.negocio.servicios.SrvEntregarServiceBase {
 	
	private static final long TiempoBloqueoZip =300000;
	private static final String BARRA=File.separator;
	private static final String TEMPLATE_IMSCP2SCORM2004 ="IMSCP-SCORM2004-Manifest.xsl";
	private static final String TEMPLATE_IMSCP2SCORM12 ="IMSCPtoSCORM12.xsl";
	private static final String TEMPLATE_SCORM122SCORM2004 ="SCORM12-SCORM2004-Manifest.xsl";
	private static final String TEMPLATE_SCORM122ISMCP ="SCORM12toIMSCP.xsl";
	private static final String TEMPLATE_SCORM20042SCORM12="toSCORM12.xsl" ; //scorm 12 a scorm 2004
	private static final String TEMPLATE_SCORM20042ISMCP="toIMSCP.xsl";
	private static final String XSLT_PDF_PATH = "uploads/xslt/informe_metadata_pdf.xsl";
	
	
	protected static final Logger logger = Logger.getLogger(SrvEntregarServiceImpl.class);

	private HashMap<String, Manifest> tablaHash = new HashMap<String, Manifest>(5);
	private Properties prop= null;
	public static final String MANIFEST_NAME = "imsmanifest.xml";
	private static final String PREFIJO_EMPAQUETADOR= "EMP";
	
	
	
	/**
	 * Metodo que permite realizar tareas de inicializacion como eliminar
	 * ficheros temporales creados por este modulo y eliminar registros en base de 
	 * datos.
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception
	{
		limpiarTemporales();
	}

	/**
	 * método privado que recupera propiedades del modulo.
	 * 
	 * @param sKey String con el key de la propiedad
	 * @return String valor de la propiedad
	 * @throws IOException
	 */
	private String getPropertyValue(
			String sKey) 
	throws IOException 
	{
		InputStream fIsProperties = this.getClass().getResourceAsStream("/entregar.properties");
		if (this.prop == null) {
			prop = new java.util.Properties();
			prop.load(fIsProperties);
		}
		fIsProperties.close();
		if (logger.isDebugEnabled())logger.debug("getPropertyValue: Propiedad recuperada: <" + sKey + ">: <"+ prop.getProperty(sKey)+">");
		return prop.getProperty(sKey);
	}

	
	/**
	 * Metodo privado que elimina ficheros temporales y vacía la base de datos.
	 * 
	 * @throws Exception
	 */
	private void limpiarTemporales()
	throws Exception
	{
		// por compatibilidad hacia atras.. elimino todos los temporales que pudieran existir de 
		// versiones anteriores de la aplicación.
		File carpetaTaller = new File( this.getPropertyValue("tallerPathBase"));
		String nombreCarpetaTemp = getPropertyValue("nombre.temporal"); 
		String[] listaCarpetasUsuarios= carpetaTaller.list();
		// la carpeta taller tiene una estructura de  la siguiente manera:
		// uploads/taller/[usr]/[fecha]/temp
		for (int i = 0; listaCarpetasUsuarios!=null && i < listaCarpetasUsuarios.length; i++) 
		{
			File carpetaUsuario= new File( new StringBuilder(carpetaTaller.getCanonicalPath()).append(BARRA).append(listaCarpetasUsuarios[i]).toString() );
			String[] listaCarpetaFechas= carpetaUsuario.list();
			for (int j = 0; listaCarpetaFechas!=null && j < listaCarpetaFechas.length; j++) {
				File temp= new File(new StringBuilder(carpetaUsuario.getCanonicalPath()).append(BARRA).append(listaCarpetaFechas[j]).append(nombreCarpetaTemp).toString());
				if(temp.exists())
				{
					if(logger.isDebugEnabled())logger.debug("se elimina el fichero <" + temp.getCanonicalPath()+">");
					UtilesFicheros.eliminar(temp);
				}
			}
		}
		
		// ficheros de carpetas publicadas..
		File carpetaRepositorio = new File( this.getPropertyValue("publicacionPathBase"));
		String[] listaCarpetasRepo= carpetaRepositorio.list();
		// la carpeta repositorio tiene una estructura de  la siguiente manera:
		// uploads/repositorio/[fecha]/[codigo]/temp
		for (int i = 0; listaCarpetasRepo!=null && i < listaCarpetasRepo.length; i++) 
		{
			File carpetaUsuario= new File( new StringBuilder(carpetaRepositorio.getCanonicalPath()).append(BARRA).append(listaCarpetasRepo[i]).toString() );
			String[] listaCarpeta = carpetaUsuario.list();
			for (int j = 0; listaCarpeta!=null && j < listaCarpeta.length; j++) {
				File temp= new File(new StringBuilder(carpetaUsuario.getCanonicalPath()).append(BARRA).append(listaCarpeta[j]).append(nombreCarpetaTemp).toString());
				if(temp.exists())
				{
					if(logger.isDebugEnabled())logger.debug("se elimina el fichero <" + temp.getCanonicalPath()+">");
					UtilesFicheros.eliminar(temp);
				}
			}
			
		}
		
		// desde la versión 1.2 los temporales se generan en la carpeta uploads/export/[identificador]/[tipo]/[titulo].zip
		String pathTemp = getPropertyValue("export.PathBase");
		File carpetaExport= new File(pathTemp);
		UtilesFicheros.eliminar(carpetaExport);
		carpetaExport.mkdir();
		
		// inicializo la tabla que lista los ficheros temporales..
		if(this.getCacheFicherosDao()!=null)
		{
			Collection<CacheFicheros>  todos = this.getCacheFicherosDao().loadAll();
			this.getCacheFicherosDao().remove(todos);
		}
		
	}

	
	/**
	 * Método privado que gestiona los ficheros temporales creados por diferentes
	 * métodos de este módulo. Elimina ficheros cuando la cantidad de ficheros temporales excede
	 * la cantidad indicada en las propiedades de la plataforma y tiene mas tiempo que el
	 * indicado en las propiedades de la plataforma.
	 * 
	 *  
	 * @throws Exception
	 */
	private synchronized void generarEspacio()
	throws Exception
	{
		try {
			
			// listo las entradas ordenadas por la ultima modificacion, los mas viejos primero
			Calendar fechaLimite= new GregorianCalendar();
			int tiempo = Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty("export.cache.tiempo")); 
			fechaLimite.add(GregorianCalendar.HOUR, -tiempo ); 
			
			StringBuilder hql =
			new StringBuilder( " from es.pode.entregar.negocio.dominio.CacheFicheros as f");
					hql.append(" where " );
					hql.append(" f.bloqueado=0 AND " );
					hql.append(" :fechaLimite > f.fecha_modificacion " );
					hql.append(" order by f.fecha_modificacion, f.publicado" );
			
			List<CacheFicheros> todos = this.getCacheFicherosDao().listarOrdenadosPorFecha(CacheFicherosDao.TRANSFORM_NONE , hql.toString() , fechaLimite);
			List<CacheFicheros> listaEliminar= new ArrayList<CacheFicheros>();
			
			// obtengo el tamaño maximo de la cache del agregaProperties
			int maxLength = Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty("export.cache.size")); 
			// si supera el tamaño maximo comienzo a eliminar
			if(todos.size()>0 && maxLength<todos.size())
			{
				if(logger.isDebugEnabled())logger.debug("existen " + todos.size() + " ficheros en la cache.");
				// elimino ficheros y entradas en la cache hasta que el tamaño sea menor al tamaño maximo
				for (int i = 0; i < todos.size() ; i++)
				{
					listaEliminar.add(todos.get(i));
					File eliminar= new File(todos.get(i).getRuta_fichero());
					UtilesFicheros.eliminar(eliminar);
				}
				this.getCacheFicherosDao().remove(listaEliminar);
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}
	
	
	
	/**
	 * Este metodo recibe un identificador de un ODE.
	 * Devuelve el paquete PIF con todos los elementos que contiene el ODE.
	 * Para entregar el paquete, el metodo tiene que localizar la informacion referente al ODE 
	 * en disco (invocar al localizador), despues tiene que validar la 
	 * informacion (invocar al validador) para despues generar el paquete.
	 * 
	 * @param identificador Identificador del ODE del que se requiere el paquete.
	 * @return PaquetePifVO Fichero conteniendo el ODE en formato PIF.
	 */

	protected PaquetePifDriVO handleGenerarPaquetePIF(
			String identificador)
	throws java.lang.Exception 
	{
		PaquetePifDriVO paquetePif = new PaquetePifDriVO();
		// inicializo un DataHandler porque es la salida.
		DataHandler dh = null;
		// localizador

		try {
	//		logger.debug("Llammos al servicio de localización");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			String pathComprimir = localizadorService.consultaLocalizador(identificador).getPath();
			if(logger.isDebugEnabled())logger.debug("El path comprimir es = " + pathComprimir);
			SrvValidadorService validadorService = this.getSrvValidadorService();
			if(logger.isDebugEnabled())logger.debug("Se valida el fichero");
			ValidaVO valid = validadorService.obtenerValidacionBin(pathComprimir);
			CBValidoVO valida = new CBValidoVO();
			valida.setEsValidoManifest(valid.getEsValidoManifest());
			valida.setResultadoValidacion(valid.getResultadoValidacion());
			valida.setRutaManifest(valid.getRutaManifest());
			paquetePif.setResultadoValidacion(valida);//añadimos el resultado de validacion al VO que se devuelve
			
			if(logger.isDebugEnabled())logger.debug("Despues de validar = <" + valid.getEsValidoManifest()+">");
			if (valid.getEsValidoManifest().booleanValue()) 
			{
				if (odeConRecursoDirectamenteDescargable(identificador))
				{
					// Si es un recurso directamente descargable no es necesario comprimir
					logger.debug("Es un ODE con un único recurso descargable. Se descarga el fichero");

					String rutaRecurso = handleObtenerRecursoUnicoDelODE(identificador);

					File exportar= new File(rutaRecurso);
					if (exportar.exists())
					{
						logger.debug("Se devuelve el DataHandler a ese fichero"); 
						dh = new DataHandler(new FileDataSource(exportar));
						paquetePif.setPaquetePIF(dh);//añadimos un datahandler, con el contenido del ODE, al VO que se devuelve
					}
				}
				else
				{
					logger.debug("No es un ODE con un único recurso descargable. Se descarga como zip");
									
					String carpetaExport = getPropertyValue("export.PathBase")+ identificador+ BARRA + TipoPIFCstEnum.SCORM_04.toString();
					File carpetaExportFile=new File(carpetaExport);
					if(!carpetaExportFile.exists())
						carpetaExportFile.mkdirs();
	
					int publicado= 0;
					if(pathComprimir.indexOf(getPropertyValue("publicacionUrlBase")) !=-1)
						publicado= 1;
	
					File exportar= new File(carpetaExport + BARRA + identificador + ".zip");
					
					String pathZip= exportar.getCanonicalPath().replace("\\", "/"); 
					logger.debug("Vamos a generar el zip en el directorio = " + pathZip);
					try {
						while(bloquearZip(pathZip , publicado )) // pregunto si está siendo utilizado
						{
							if(logger.isDebugEnabled())logger.debug("el fichero zip no es accesible. Comienza la espera ... " + (new Date()).toString() );
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								if(logger.isDebugEnabled())logger.warn("SrvEntregarServiceImpl.generarPaquetePifTipoPif: error en la espera cuando se generaba el fichero zip");
							}
							if(logger.isDebugEnabled())logger.debug("acabó la espera ... " + (new Date()).toString() + " intento bloquear el fichero zip.");
						}
						
						boolean existe= exportar.exists();
						boolean viejo=false;
						if(existe && (publicado==1))
						{
							Date ahora= new Date();
							long diferencia = ahora.getTime() - exportar.lastModified() ;
							long limiteTiempo;
							try {
								String tiempoStr=AgregaPropertiesImpl.getInstance().getProperty("tiempolimite");
								if(logger.isDebugEnabled())logger.debug("tiempo limite: " + tiempoStr);
								limiteTiempo= Long.parseLong(tiempoStr);
							} catch (RuntimeException e) {
								if(logger.isDebugEnabled())logger.warn("No existe definido el parametro tiempolimite en el fichero agrega.properties; se define como tiempolimite por defecto un tiempo de 7 dias");
								limiteTiempo=604800000;
							}
							if(diferencia > limiteTiempo  )
							{
								viejo=true;
							}
						}
	
						if(!existe || ( existe && (publicado==0)) || (existe && (publicado==1) && viejo) )
						{
	//						logger.debug("Si el zip existe lo elimino.");
							if(exportar.exists())
								exportar.delete();
	
							this.getZipDao().comprimir(pathZip,pathComprimir);
						}else
						{
							logger.debug("\n\n el fichero zip ya existe y no es necesario volver a generarlo... se devuelve el existente..\n\n");
						}
	
						dh = new DataHandler(new FileDataSource(exportar));
						exportar= null;
						generarEspacio();
						desbloquearZip(pathZip);
						
						logger.debug("Devolvemos VO paquetePif");
						paquetePif.setPaquetePIF(dh);//añadimos un datahandler, con el contenido del ODE, al VO que se devuelve 
						return paquetePif;
					}catch (Exception e) {
						logger.error("Error en SrvEntregarServiceImpl:GenerarPaquetePif - " , e);
						throw e;
					}

				} // else
			} // if
		}// try

		catch (Exception e) {
			logger.error("Excepcion - ",e);
			throw e;
		}
		logger.debug("Se devuelve VO paquetePif");
		paquetePif.setPaquetePIF(dh);
		return paquetePif;
	}
	
	

	/**
	 * Este metodo se comporta como el homonimo de la clase pero permite configurar el formato 
	 * del fichero PIF que se devuelve entre la coleccion de formatos que se soportan por la clase TiposPIF.
	 * Toma como parametro de entrada un objeto TipoPifVO que contiene 
	 * el identificador del ODE y el tipo de fichero PIF 
	 * que desea a la salida de entre los posibles por la clase TipoPIF.
	 * Devuelve el ODE el formato PIF con el tipo escogido.
	 * 
	 * @param tipoPifVO Value Object que contiene el Id del ODE y el tipoPif
	 * @return PaquetePifVO  Fichero conteniendo el ODE en el formato especificado.
	 */
	protected PaquetePifVO handleGenerarPaquetePIFTipoPIF(TipoPifVO tipoPifVO) throws Exception 
	{
		PaquetePifVO paquetePif;
		File exportar=null;
		File ficheroZip=null;
		String pathZip= "";
		File zipDir=null;
		
		
		logger.info("identificador:  <" + tipoPifVO.getIdODE()+">");
		logger.info("tipoPif:  <" + tipoPifVO.getTipoPif()+">");
		
		try {
			logger.debug("chequeo si el tipoPif es válido");
			TipoPIFCst.fromString(tipoPifVO.getTipoPif());
			//Validacion del ode original
			logger.debug("Llamamos al servicio de localización");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			String pathODE = localizadorService.consultaLocalizador(tipoPifVO.getIdODE()).getPath();
			int publicado= 0;
			if(pathODE.indexOf(getPropertyValue("publicacionUrlBase")) !=-1)
				publicado= 1;
				
				
//				opciones
//				1.- el fichero no existe... lo tengo que crear si o si
//				2.- el fichero existe.. 
//					2.1 - origen = publicados..
//						2.1.1.- fichero viejo.. lo elimino y vuelvo a generar
//						2.1.2.- fichero no es tan viejo.. devuelvo el que ya existe
//					2.2.- origen= personales
//						2.2.1.- elimino el fichero y vuelvo a generarlo

				paquetePif=this.validarODE(pathODE);
				
				CBValidoVO valid = paquetePif.getResultadoValidacion();
				if (!valid.getEsValidoManifest().booleanValue()) {
					logger.info("El ODE no es válido, se envía el zip y los mensajes de validación..");
				}
					
					if(logger.isDebugEnabled()) logger.debug("creo fichero zip y carpeta temporal");
					
					zipDir=new File(getPropertyValue("export.PathBase")+ tipoPifVO.getIdODE()+ BARRA + tipoPifVO.getTipoPif());
					
					if(!zipDir.exists())
						zipDir.mkdirs();

					if(!zipDir.canWrite())
					{
						logger.error("\n\n el directorio donde deben crearse los ficheros zip no se puede escribir. comuniquese con el administrador\n\n");
						throw new Exception("El directorio donde se generan los ficheros a exportar no existe o no tiene permisos de escritura.. comuniquese con el administrador..");
					}

					
					pathZip= zipDir.getCanonicalPath() + obtenerNombreZip(pathODE, tipoPifVO.getIdioma(),tipoPifVO.getIdODE(), tipoPifVO.getTipoPif());
					ficheroZip= new File(pathZip);
					pathZip= ficheroZip.getCanonicalPath().replace("\\", "/");
					
					while(bloquearZip(pathZip , publicado )) // pregunto si está siendo utilizado
					{
						logger.debug("el fichero zip no es accesible.. comienza la espera .... " + (new Date()).toString() );
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							logger.debug("SrvEntregarServiceImpl.generarPaquetePifTipoPif: error en la espera cuando se generaba el fichero zip");
						}
						logger.debug("acabó la espera .... " + (new Date()).toString() + " intento bloquear el fichero zip.");
					}
				
				boolean existe= ficheroZip.exists();
				boolean viejo=false;
				if(existe && (publicado==1))
				{
					Date ahora= new Date();
					long diferencia = ahora.getTime() - ficheroZip.lastModified() ;
					long limiteTiempo;
					try {
						String tiempoStr=AgregaPropertiesImpl.getInstance().getProperty("tiempolimite");
						if(logger.isDebugEnabled())logger.debug("tiempo limite: " + tiempoStr);
						limiteTiempo= Long.parseLong(tiempoStr);
					} catch (RuntimeException e) {
						if(logger.isDebugEnabled())logger.debug("no existe definido el parametro tiempolimite en el fichero agrega.properties; se define como tiempolimite por defecto un tiempo de 7 dias");
						limiteTiempo=604800000;
					}
					if(diferencia > limiteTiempo  )
					{
						viejo=true;
					}
				}

				if(!existe || ( existe && (publicado==0)) || (existe && (publicado==1) && viejo) )
				{
					//logger.debug("si el zip existe lo elimino");
					if(ficheroZip.exists())
						ficheroZip.delete();
					ficheroZip=null;
					
					String carpetaExport= zipDir.getAbsolutePath() + BARRA + "exp-" +tipoPifVO.getIdODE();
					//crear la carpeta temporal....	
					exportar= new File(carpetaExport);
					carpetaExport= exportar.getCanonicalPath().replace("\\", "/");
					
					if(!exportar.exists())
					{
						if(logger.isDebugEnabled()) logger.debug("no existe la carpeta temporal... creo una vacia ");
						exportar.mkdirs();
					}else{
						if(logger.isDebugEnabled()) logger.debug("existe una carpeta temporal... la vacio");
						UtilesFicheros.eliminar(exportar);
					}
					
					this.crearTransformacion(tipoPifVO.getTipoPif().toString(), carpetaExport, pathODE, pathZip,tipoPifVO.getIdODE());
				}else
				{
					//logger.debug("\n\n el fichero zip ya existe y no es necesario volver a generarlo.Se devuelve el existente..\n\n");
				}

				
				generarEspacio();
				desbloquearZip(pathZip);

				int inicio = pathZip.indexOf(getPropertyValue("export.UrlBase")+ tipoPifVO.getIdODE());
				if(inicio<0)
					inicio=0;
				
				
				paquetePif.setHref(pathZip.substring( inicio, pathZip.length()));
				if(paquetePif.getHref()!=null)
					logger.debug("path del ode: <" + paquetePif.getHref()+">");


		}catch (Exception e) {
			if(exportar!=null && exportar.exists())
				UtilesFicheros.eliminar(exportar);
			if(pathZip!=null && !pathZip.equals(""))
			{
				desbloquearZip(pathZip);
				if(ficheroZip==null)
					ficheroZip = new File(pathZip);
				
				if(ficheroZip.exists())
					ficheroZip.delete();
				
				ficheroZip=null;
			}
			logger.error("Excepcion generica - " , e);
			throw e;
		}
		logger.debug("Devolvemos VO paquetePif");
		return paquetePif;

	}

	
	private Manifest aplanarManifest(String identificador, String path) throws Exception
	{
		String sRuta="";
		Manifest manifest= null;
		if(identificador!=null){
			
			manifest= new Manifest();
			logger.debug("Llamamos al servicio de localización");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			
			if(logger.isDebugEnabled())logger.debug("Llammos al servicio de localización con el identificador = " + identificador);
			LocalizadorVO localVo = localizadorService.consultaLocalizador(identificador);
			
			StringBuffer sbPath = new StringBuffer();
			sbPath.append(localVo.getPath()).append("/").append(MANIFEST_NAME);
			if(logger.isDebugEnabled())logger.debug("El objeto se ecuentra en la ruta = " + sbPath);
	//		String localizacion = localVo.getUrl();
			sRuta = sbPath.toString().replace('\\', '/');
	
			if(logger.isDebugEnabled())logger.debug("Miro el fichero en la ruta = " + sRuta);
		}else if(identificador==null && path!=null){
			String ruta=path+"/"+MANIFEST_NAME;
			sRuta=ruta.replace('\\', '/');
		}
		File rutaManifest = new File(sRuta);
		logger.debug("Parseamos el objeto");
		if (rutaManifest.exists()) {
			manifest = this.getScormDao().parsearODELazy(rutaManifest);
		}
		logger.debug("Objeto parseado");		
		
		procesarManifest(manifest , true,null);

		return manifest;
	}
	

	private void procesarManifest(Manifest manifest, boolean resetearIDs, HashMap<String, Item> aplanados)
	throws java.lang.Exception 
	{
		ManifestAgrega ma = new ManifestAgrega(manifest);
		
//		aplanarOrganizaciones(manifest);

		/*
		 * Cambios en empaquetador movidos a entregar: Aplanamos todos los
		 * recursos de los submanifiestos y despues los submanifiestos.
		 */
		if(resetearIDs) aplanarRecursosDeSubmanifiestos(manifest);
		
		aplanarSubmanifiestos(manifest, aplanados);
		if(resetearIDs) {
			ma.resetearIds();
		}
	}
	private void aplanarRecursosDeSubmanifiestos(Manifest manifest) {
		String base = null;
		if(logger.isDebugEnabled()) logger.debug("Aplanando recursos de submanifiestos");
		for(int i=0;i<manifest.getManifestCount();i++) {
			// Aplano el submanifiesto
			base =  manifest.getManifest(i).getBase()==null?"":manifest.getManifest(i).getBase();
			aplanarRecursosDeSubmanifiestos(manifest.getManifest(i));
			if(manifest.getManifest(i).getResources()!=null){
				for(int j=0;j<manifest.getManifest(i).getResources().getResourceCount();j++) {
					Resource resource = manifest.getManifest(i).getResources().getResource(j);
					resource = aplanaRecurso(resource,base);
					manifest.getResources().addResource(resource);
				}
			}
		}
	}
	private Resource aplanaRecurso(Resource res, String base) {
		if (res.getHref() != null) {
			// Compruebo si href apunta a un file (sino, es URL)
			for (int i = 0; res.getFile() != null && i < res.getFile().length; i++) {
				if (res.getFile(i).getHref().equals(res.getHref())) {
					if (logger.isDebugEnabled())
						logger.debug("Aplanado href de recurso "
								+ res.getIdentifier());
					res.setHref(base + res.getHref());
					break;
				}
			}
		}
		// Aplano los File
		for (int i = 0; res.getFile() != null && i < res.getFile().length; i++) {
			if (logger.isDebugEnabled())
				logger.debug("Aplanado file de recurso " + res.getIdentifier()
						+ " : " + res.getFile(i).getHref() + " por " + base
						+ res.getFile(i).getHref());
			res.getFile(i).setHref(base + res.getFile(i).getHref());
		}
		return res;
	}
	
	private static Organization getOrganizacionPrincipal(Manifest manifest) {
		String principalId = manifest.getOrganizations().getDefault();
		int principalIndex = Arrays.binarySearch(manifest.getOrganizations()
				.getOrganization(), principalId, new OrganizationStringComparator());
		Organization principal = manifest.getOrganizations().getOrganization(
				principalIndex);
		return principal;
	}	
	

	private void aplanarSubmanifiestos(Manifest manifest, HashMap<String, Item> aplanados) throws Exception {
		if(aplanados==null) aplanados = new HashMap<String, Item>(10);
		Organization org = getOrganizacionPrincipal(manifest);
		if (org.getItemCount() > 0)
			aplanaSubmanifiestoEnItems(org.getItem(), manifest, aplanados);
		// Elimino los submanifiestos una vez aplanados
		manifest.setManifest(new Manifest[] {});
	}

	private void aplanaSubmanifiestoEnItems(Item[] items, Manifest manifest,
			HashMap<String, Item> aplanados) throws Exception {
		ManifestAgrega ma = new ManifestAgrega(manifest);
		for (int i = 0; i < items.length; i++) {
			if (items[i].getItemCount() > 0) {
				/*
				 * Me interno primero en los hijos ya que cuando se aplane el
				 * submanifiesto, el item pasara a tener nuevos hijos.
				 */
				if (logger.isDebugEnabled())
					logger.debug("Iterando en los hijos de <"
							+ items[i].getIdentifier()+">");
				aplanaSubmanifiestoEnItems(items[i].getItem(), manifest,
						aplanados);
			}
			// Comprueba idRef
			if (items[i].getIdentifierref() != null) {
				Object obj = ma.obtenerElemento(items[i].getIdentifierref());
				if (obj instanceof Manifest || aplanados.containsKey(items[i].getIdentifierref())) {
					if (logger.isDebugEnabled())
						logger.debug("Submanifiesto <"
								+ items[i].getIdentifierref()
								+ "> referenciado por <"
								+ items[i].getIdentifier()+">");
					
					Item submanifiestoAplanado = null;
					if (aplanados.containsKey(items[i].getIdentifierref())) {
						/*
						 * Para evitar keys duplicadas al terminar de aplanar,
						 * debo clonar el Item de la cache, de lo contrario, al
						 * resetear IDs se generan IDs iguales para multiples
						 * elementos (referencias).
						 */
						Item tmp = aplanados.get(items[i].getIdentifierref());
						submanifiestoAplanado = (Item) getBeanMapper().map(tmp,
								Item.class);
						if (logger.isDebugEnabled())
							logger.debug("Submanifiesto <"
									+ items[i].getIdentifierref()
									+ "> recuperado de la cache de aplanados");
					} else {
						Manifest submanifiesto = (Manifest) obj;
						if (logger.isDebugEnabled())
							logger.debug("Procesando submanifiesto <"
									+ items[i].getIdentifierref()
									+ "> antes de aplanar");
						procesarManifest(submanifiesto,false,aplanados);
						if (logger.isDebugEnabled())
							logger.debug("Aplanando submanifiesto");
						submanifiestoAplanado = aplanarSubmanifest((Manifest) obj);
						if (logger.isDebugEnabled())
							logger.debug("Introduciendo submanifiesto aplanado en la cache aplanados");
						aplanados.put(submanifiesto.getIdentifier(),
								submanifiestoAplanado);
						if (logger.isDebugEnabled())
							logger.debug("Aplanando los recursos del submanifiesto");
//						aplanarRecursos(manifest, submanifiesto);
					}
					if (logger.isDebugEnabled())
						logger.debug("Insertando submanifiesto aplanado <"
								+ items[i].getIdentifierref() + "> en Item <"
								+ items[i].getIdentifier()+">");

					/*
					 * Cambios del C27: Se ha solicitado que al previsualizar
					 * items que referencian a submanifiestos se imite la
					 * estructura creada por los LMSs clasicos: el item que
					 * referencia hereda el título de la organización principal
					 * del submanifiesto y pierde el propio.
					 * El importar de empaquetador básico debe reproducir esta estructura.
					 */
//					items[i].addItem(0, submanifiestoAplanado);
					items[i].setTitle(submanifiestoAplanado.getTitle());
					ArrayList<Item> listaTmp = new ArrayList<Item>(); 
					listaTmp.addAll(Arrays.asList(submanifiestoAplanado.getItem()));
					if(items[i].getItemCount()>0) listaTmp.addAll(Arrays.asList(items[i].getItem()));
					items[i].setItem(listaTmp.toArray(new Item[listaTmp.size()]));
					
					items[i].setIdentifierref(null);
				}
			}

		}
	}

	

	private Item aplanarSubmanifest(Manifest subManifest)
	throws java.lang.Exception 
	{
		Item itemManifest = new Item();
		// Id del Item = Id de la organizacion principal
		itemManifest.setIdentifier(PodeUUIDGenerator.getItemUUID(String
				.valueOf(java.lang.Math.random())));
		itemManifest.setItem(getOrganizacionPrincipal(subManifest).getItem());
		itemManifest.setTitle(obtenerTituloSubmanifiesto(subManifest,
				getOrganizacionPrincipal(subManifest).getTitle()));
		
		return itemManifest;
	}

private String obtenerTituloSubmanifiesto(Manifest submanifiesto,
	String porDefecto) throws Exception 
	{
		String resultado = null;
		ManifestAgrega ma = new ManifestAgrega(submanifiesto);
		Lom lomes = ma.obtenerLom(submanifiesto.getIdentifier(), null);
		if (lomes == null) {
			resultado = porDefecto;
		} else {
			LomAgrega la = new LomAgrega(lomes);
			String titulo = la.getGeneralAgrega().getTitulo(
					la.getMetaMetadataAgrega().getIdioma());
			if (titulo == null)
				resultado = porDefecto;
			else
				resultado = titulo;
		}
		return resultado;
	}


	/**
	 * Metodo obtenerObjManifest
	 * El Empaquetador, llamara a este metodo del servicio para guardar el manifest en memoria;
	 * El método cogerá el objeto serializado lo deserializará obtendrá un manifest y lo metará en memoria (HashMap)
	 * Devolvera un string que sera el identificador este identificador estara formado con el id del 
	 * manifest (manifest.getIdentifier()) y el prefijo EMP
	 * 
	 * @param fichManifest Objeto Manifest serializado.
	 * @param usuario usuario.
	 * @return String el identificador.
	 */
	protected String handleCargarObjManifest(String usuario,
			DataHandler fichManifest) throws Exception {

		String identificador = null;
		if (fichManifest.getContentType().equals(
				"application/x-java-serialized-object")) {
			// Deserializamos el objeto a un Manifest
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			fichManifest.writeTo(baos);
//			Object tmp = deserializar(baos.toByteArray());
			Manifest manifest = null;
			SerializadorObjetos so= new SerializadorObjetos();
			Object tmp= so.deserializarObjeto(fichManifest);
			if(logger.isDebugEnabled())logger.debug("ESTO ES LO QUE SE RECUPERA COMO MANIFEST: <" + tmp+">");
			if (tmp instanceof Manifest)
				manifest = (Manifest) tmp;
			else {
				Logger.getLogger(this.getClass()).error(
						"El objeto recibido no es un Manifest");
				throw new Exception("El objeto recibido no es un Manifest");
			}

			identificador = PREFIJO_EMPAQUETADOR + manifest.getIdentifier();
			if (tablaHash == null)
				tablaHash = new HashMap<String, Manifest>(5);
			tablaHash.put(identificador, manifest);
		} else
			throw new Exception("No existe objeto para cargar");

		return identificador;
	}
	
	
	

//	private Object deserializar(byte[] entrada) 
//	throws Exception 
//	{
//		ByteArrayInputStream bs = new ByteArrayInputStream(entrada);
//		ObjectInputStream is = new ObjectInputStream(bs);
//		Object salida = null;
//		try {
//			salida = is.readObject();
//		} catch (Exception e) {
//			logger.error("Ha fallado al deserializar el objeto");
//		}
//		is.close();
//		return salida;
//	}

	 /**
	 * ObtenerOrganizaciones: Método que obtiene todas las organizaciones que se encuentren en el 
	 * Manifest del objeto educativo que se desea visualizar. 
	 * Al obtener las organizaciones, se obtienen también los ítems que las componen y 
	 * los recursos de los cuales consta el objeto educativo, además de los submanifiestos
	 * para los cuales realizamos un tratamiento específico haciendo la transformación de sus
	 * organizaciones en items adaptando su estructura.
	 * 
	 * @param argObtenerOrganizaciones objeto que empaqueta el id del ode, el usuario y el idioma
	 * @return ManifestVO Devuelve el tipo ManifestVO con los datos para ese identificador y usuario
	 */
	protected ManifestVO handleObtenerOrganizaciones(
			ArgObtOrganizacionesVO argObtenerOrganizaciones) 
	throws ObtenerOrganizacionesException
	{
		ManifestVO manifestVo =null;
		try{
			String identificador= argObtenerOrganizaciones.getIdentificador();
			String idioma= argObtenerOrganizaciones.getIdiomaTitulo();
			
			String prefijo = "";
			if(identificador!=null && identificador.length()>= PREFIJO_EMPAQUETADOR.length())
				prefijo =  identificador.substring(0, PREFIJO_EMPAQUETADOR.length());
	
			Manifest manifestFile = null;
			String localizacion = null;
			// si es distinto a EMP no tiene hashmap
			logger.debug("Llammos al servicio de localización");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			
			if (!PREFIJO_EMPAQUETADOR.equals(prefijo))
			{
				logger.debug("Llamamos al servicio de localización con el identificador = " + identificador);
				LocalizadorVO localVo = localizadorService.consultaLocalizador(identificador);
				
				StringBuffer sbPath = new StringBuffer();
				sbPath.append(localVo.getPath());
				sbPath.append(BARRA).append(MANIFEST_NAME);
				
				logger.debug("El objeto se ecuentra en la ruta = " + sbPath);
				localizacion = localVo.getUrl();
				String sRuta = sbPath.toString().replace('\\', '/');
	
				logger.debug("Miro el fichero en la ruta = " + sRuta);
				File rutaManifest = new File(sRuta);
				logger.debug("Parseamos el objeto");
				if (rutaManifest.exists()) {
					manifestFile = this.getScormDao().parsearODELazy(rutaManifest);
				}
				logger.debug("Objeto parseado");
			}
			else// prefijo.equals("EMP")
			{
				manifestFile = tablaHash.get(identificador);
				String sIdentificador = identificador.substring(prefijo.length(),
						identificador.length());
				if(logger.isDebugEnabled())logger.info("Identificador = <" + sIdentificador+">");
				LocalizadorVO localVo = localizadorService
						.consultaLocalizador(sIdentificador);
				localizacion = localVo.getUrl();
			}
			
	//		Añadimos el Titulo del ODE o en su Defecto "Nuevo ODE"
			if(manifestFile!=null)
			{
				ManifestAgrega mA = new ManifestAgrega(manifestFile);
				String tituloOde=null;
				
				Lom lomes = mA.obtenerLom(manifestFile.getIdentifier(), null);
				if(lomes!=null)
				{
					LomAgrega lom = new LomAgrega(lomes); 
					tituloOde= lom.getGeneralAgrega().getTitulo(idioma);
					if(tituloOde == null) tituloOde = lom.getGeneralAgrega().getTitulo(null);
				}
		
				EntregarDozerDao entregarDao = this.getEntregarDozerDao();
				manifestVo = entregarDao.transformarManifest2VO(manifestFile);
				manifestVo.setLocalizacionURL(localizacion);
				if ((tituloOde!=null) && (!tituloOde.equals(""))) {
					manifestVo.setTitulo(tituloOde);
				} else {
					manifestVo.setTitulo("Nuevo ODE");
				}
			}
		
		}catch (Exception e) {
			logger.error("Excepcion generica - " , e);
			throw new ObtenerOrganizacionesException("Error en el metodo SrvEntregarServiceImpl:obtenerOrganizaciones ", e);
		}
		return manifestVo;
	}

	/**
	 * Este metodo devuelve un array de Strings con todos los tipos de formatos PIF que 
	 * maneja el modulo de Entregar. Los propios Strings son identificadores para ulteriores 
	 * llamadas de peticion de conversion de formato de ODEs.
	 * 
	 * @return String[]: Array de Strings con con los tipos de formatos PIF soportados.
	 */
	protected String[] handleObtenerTiposPIF() throws Exception {
		return (String[])TipoPIFCst.literals().toArray(new String[0]);
	}



	protected boolean handleEstoyActivo() throws Exception {

		return true;
	}


	private String obtenerNombreZip(
			String pathOde  , 
			String idioma, 
			String identificador, 
			String tipo)
	{
		String resultado= BARRA + identificador + ".zip";
		
		try {
			String tituloOde="ODE";
			if(identificador!=null)
				tituloOde= identificador;
			
			String sRuta =pathOde.replace('\\', '/') + BARRA + MANIFEST_NAME ; 

			if(logger.isDebugEnabled())logger.debug("Miro el fichero en la ruta = " + sRuta);
			File rutaManifest = new File(sRuta);
			logger.debug("Parseamos el objeto");
			Manifest manifestFile=null;
			if (rutaManifest.exists()) {
				manifestFile = this.getScormDao().parsearODELazy(rutaManifest);
				logger.debug("Objeto parseado");
			}
			
			ManifestAgrega mA =null;
			if(manifestFile!=null)
			{
				mA = new ManifestAgrega(manifestFile);
			}
				
			Lom lomes = null;
			if(mA!=null)
				lomes = mA.obtenerLom(manifestFile.getIdentifier(), null);
				
			if(lomes!=null)
			{
				LomAgrega lom = new LomAgrega(lomes); 
				tituloOde= lom.getGeneralAgrega().getTitulo(idioma);
				if(tituloOde == null) tituloOde = lom.getGeneralAgrega().getTitulo(null);
				if(logger.isDebugEnabled())logger.debug("el titulo del ode será: <" + tituloOde +">");
			}else
			{
				tituloOde=mA.getManifest().getIdentifier();
				if(logger.isDebugEnabled())logger.debug("el ODE no tiene lomes. Se utilizará el identificador para formar el nombre del zip: <" + tituloOde +">");
			}
			
			String tituloEnc = tituloOde.replace(' ', '_');
			tituloEnc = tituloEnc.replace('?', '_').replace('¿', '_').replace('/', '_').replace('[', '_').replace(']', '_').replace('@', '_');
			tituloEnc = tituloEnc.replace('ñ', 'n').replace('Ñ', 'N').replace(':', '_').replace('!', '_').replace('¡', '_').replace('º', 'o') ;
			tituloEnc = convertNonAscii(tituloEnc);
			
			if(tipo==null || tipo.equals(""))
				tipo="";
			else 
				tipo="-" + tipo;
			
			resultado = "/" + tituloEnc + tipo + ".zip";
			logger.debug("nombre del zip: " + resultado);
		} catch (ParseadorException e) {
			logger.error("Error!!!! - error al parsear el ODE "+pathOde+" - " , e);
		} catch (Exception e) {
			logger.error("Error!!!! - error generico - " , e);
		}
		return resultado;
	}
	

	
	
	private String convertNonAscii(String s) 
	{
		final String PLAIN_ASCII =
		      "AaEeIiOoUu"    // grave
		    + "AaEeIiOoUuYy"  // acute
		    + "AaEeIiOoUuYy"  // circumflex
		    + "AaOo"          // tilde
		    + "AaEeIiOoUuYy"  // umlaut
		    + "Aa"            // ring
		    + "Cc"            // cedilla
		    ;

		final String UNICODE =
		 "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
		+"\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
		+"\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
		+"\u00C3\u00E3\u00D5\u00F5"
		+"\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
		+"\u00C5\u00E5"                                                             
		+"\u00C7\u00E7" ;
		
		
	       StringBuffer sb = new StringBuffer();
	       int n = s.length();
	       for (int i = 0; i < n; i++) {
	          char c = s.charAt(i);
	          int pos = UNICODE.indexOf(c);
	          if (pos > -1){
	              sb.append(PLAIN_ASCII.charAt(pos));
	          }
	          else {
	              sb.append(c);
	          }
	       }
	       return sb.toString();
	    }

	
	
	
	private synchronized boolean bloquearZip (
			String pathZip ,
			int publicado) throws Exception
	{
		boolean esperar= true; 
		// 20022013 Trabajo con el hashCode del pathZip porque en algunos casos es mayor que 255 que es el tamaño de la columna en MySql si el 
		// 			juego de caracteres de la bd es 255.
		String hashCodePathZip = String.valueOf(pathZip.hashCode());
		try{		
			// instancio un criteria para buscar si el fichero existe en la cache
			RutaTemporalCriteria criteria= new RutaTemporalCriteria(hashCodePathZip);
			List<CacheFicheros> lista = this.getCacheFicherosDao().listarRutaTemporales(CacheFicherosDao.TRANSFORM_NONE, criteria);
			
			if(lista.size()>0)
			{
				// si existe el fichero en la cache, actualizo la fecha de modificacion para que pasen al final de la lista a eliminar
				for (int i = 0; i < lista.size(); i++) {
					CacheFicheros fichero= lista.get(i);
					if(logger.isDebugEnabled())logger.debug("actualizo el fichero: " + fichero.getRuta_fichero() + " con path : " + hashCodePathZip);
					// compruebo que el zip no permanezca bloqueado por mas de 5 minutos para 
					// evitar que se quede bloqueado eternamente.. si lleva mas de 5 minutos
					// bloqueado, lo desbloqueo
					long dif= (new Date()).getTime() - fichero.getFecha_modificacion().getTime().getTime() ;
					if(logger.isDebugEnabled())logger.debug("bloqueado: " + fichero.getBloqueado() + " TiempoBloqueoZip: " + TiempoBloqueoZip + " diferencia: " + dif);
					
					if(fichero.getBloqueado()==1)
					{			
						if( TiempoBloqueoZip < dif) 
						{
							fichero.setBloqueado(0);
							this.getCacheFicherosDao().update(fichero);
						}
					}else{
						fichero.setBloqueado(1);
						this.getCacheFicherosDao().update(fichero);
						esperar= false;
					}
				}
			}else
			{
				// no existe entrada en la cache, por lo tanto la creo
				if(logger.isDebugEnabled())logger.debug("creo una nueva entrada para el fichero: " + pathZip + " con path : " + hashCodePathZip);
				this.getCacheFicherosDao().create(hashCodePathZip, new GregorianCalendar(), 1, publicado);
				esperar=false;
			}
		}catch (Exception e) {
			logger.error("error al bloquear el fichero temporal "+pathZip+" (hashcode "+hashCodePathZip+"): ", e);
			throw e;
		}
		return esperar;
	}
	
	private synchronized  void desbloquearZip(String pathZip)
	{
		String hashCodePathZip="";
		
		try{
			// 20022013 Trabajo con el hashCode del pathZip porque en algunos casos es mayor que 255 que es el tamaño de la columna en MySql si el 
			// 			juego de caracteres de la bd es 255.
			hashCodePathZip = String.valueOf(pathZip.hashCode());
			RutaTemporalCriteria criteria= new RutaTemporalCriteria(hashCodePathZip);
			List<CacheFicheros> lista = this.getCacheFicherosDao().listarRutaTemporales( CacheFicherosDao.TRANSFORM_NONE , criteria);
			
			if(lista.size()>0)
			{
				// si existe el fichero en la cache, actualizo la fecha de modificacion para que pasen al final de la lista a eliminar
				for (CacheFicheros fichero : lista) {
					fichero.setBloqueado(0);
//					this.getCacheFicherosDao().update(fichero);
				}
				this.getCacheFicherosDao().update(lista);
			}
		}catch (Exception e) {
			logger.error("error al desbloquear el fichero temporal "+pathZip+" (hashcode "+hashCodePathZip+"): ", e);
		}
	}
 

	 /**
	 * Obtiene la localización física de un ODE.
	 * @param identificador identificador del ODE que se quiere localizar
	 * @return LocalizadorAdlVO Devuelve el tipo LocalizadorAdlVO con los datos de localización del ODE
	 */
	protected LocalizadorAdlVO handleLocalizacionPaquetePIF(String identificador) throws Exception {
		LocalizadorAdlVO localAdl = null;
		try {
			logger.debug("Llammos al servicio de localización");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			
			if(logger.isDebugEnabled())logger.debug("Llammos al servicio de localización con el identificador = " + identificador);
			LocalizadorVO localvo = localizadorService.consultaLocalizador(identificador);
			localAdl=new LocalizadorAdlVO();
			localAdl.setPath(localvo.getPath());
			localAdl.setUrl(localvo.getUrl());
			localAdl.setIdentificador(localvo.getIdentificador());
			localAdl.setIdUsuario(localvo.getIdentificador());
			
		}catch (Exception e) {
			logger.error("Error!!!! - excepcion generica", e);
		}
		
		return localAdl;
	}

	/**
	 * Realiza una transformación del ODE contenido en pathOrigen al formato
	 * especificado por tipoPIF. El resultado se deja comprimido en la carpeta
	 * pathDestino.
	 * 
	 * @return VO con los datos del paquete generado (URL de descarga del paquete)
	 * 
	 * @see es.pode.entregar.negocio.servicios.SrvEntregarServiceBase#handleTransformarODE(java.lang.String,
	 *      java.lang.String, es.pode.entregar.negocio.servicios.TipoPIFCst)
	 */

	protected PaquetePifVO handleTransformarODE(
			String pathOrigen,
			String pathDestino, 
			TipoPIFCst tipoPIF)
	throws Exception
	{
		PaquetePifVO paquetePif=null;
		File exportar= new File(pathDestino);
		String carpetaZip= exportar.getCanonicalPath().replace('\\', '/');//Carpeta donde se va a volcar todo

		try{
			if(!exportar.exists())
			{
				if(logger.isDebugEnabled()) logger.debug("no existe la carpeta temporal... creo una vacia ");
				boolean creado=exportar.mkdirs();
				if(!creado){
					logger.warn("Error al crear la carpeta <"+exportar+">");
					CBValidoVO resultadoValidacion=new CBValidoVO();
					resultadoValidacion.setResultadoValidacion("Error al crear la carpeta "+exportar);
					resultadoValidacion.setEsValidoManifest(false);
					paquetePif.setResultadoValidacion(resultadoValidacion);
				}else{
					if(!exportar.canWrite()){
						logger.warn("La carpeta <"+exportar+"> no tiene peromisos de escritura");
						CBValidoVO resultadoValidacion=new CBValidoVO();
						resultadoValidacion.setResultadoValidacion("La carpeta "+exportar+" no tiene peromisos de escritura");
						resultadoValidacion.setEsValidoManifest(false);
						paquetePif.setResultadoValidacion(resultadoValidacion);
					}
				}
			}else{
				if(logger.isDebugEnabled()) logger.debug("existe una carpeta temporal... la vacio");
				if(!exportar.canWrite()){
					logger.warn("La carpeta <"+exportar+"> no tiene peromisos de escritura");
					CBValidoVO resultadoValidacion=new CBValidoVO();
					resultadoValidacion.setResultadoValidacion("La carpeta "+exportar+" no tiene peromisos de escritura");
					resultadoValidacion.setEsValidoManifest(false);
					paquetePif.setResultadoValidacion(resultadoValidacion);
				}
			}
		}catch (Exception e) {
			logger.error("Error en la creación/vacío de la carpeta temporal - ", e);
			throw e;
		}

		paquetePif=this.validarODE(pathOrigen);
		File carpetaTemporal=null;

		
		CBValidoVO valid = paquetePif.getResultadoValidacion();
		if (!valid.getEsValidoManifest().booleanValue()) {
			logger.info("El ODE no es válido, se envía el zip y los mensajes de validación..");
		}
			
		if(logger.isDebugEnabled()) logger.debug("creo fichero zip y carpeta temporal");
		 String property = "java.io.tmpdir";   
	     String tempDir = System.getProperty(property);

	     Date fechaActual = new Date();
	     SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
	     String cadenaFecha = formato.format(fechaActual);

	     carpetaTemporal=new File(tempDir+ getPropertyValue("agrega")+ cadenaFecha);

	     if(!carpetaTemporal.exists())
	    	 carpetaTemporal.mkdirs();


	     if(!carpetaTemporal.canWrite())
		{
			logger.error("\n\n el directorio donde deben crearse los ficheros zip no se puede escribir. comuniquese con el administrador\n\n");
			throw new Exception("El directorio donde se generan los ficheros a exportar no existe o no tiene permisos de escritura.. comuniquese con el administrador..");
		}

	    String pathDestinoZip=carpetaZip + obtenerNombreZip(pathOrigen, null,null, null);
	    File ficheroDestinoZip=new File(pathDestinoZip);
		
	    pathDestinoZip=ficheroDestinoZip.getCanonicalPath().replace('\\', '/');
		
	    File pathFile=new File(pathDestinoZip);
		if(!pathFile.exists()){
	   
			this.crearTransformacion(tipoPIF.toString(), carpetaTemporal.getCanonicalPath(), pathOrigen, pathDestinoZip,null);
			paquetePif.setHref(pathDestino);

			if(paquetePif.getHref()!=null)
				logger.debug("path del ode: <" + paquetePif.getHref()+">");
		}else{
			logger.warn("El ode que se quiere exportar ya existe en la carpeta destino");
			CBValidoVO validacion=new CBValidoVO();
			validacion.setResultadoValidacion("En la carpeta de  destino "+carpetaZip+" exite un ode con el mismo nombre que el que se quiere exportar al formato "+tipoPIF.toString());
			validacion.setEsValidoManifest(false);
			paquetePif.setResultadoValidacion(validacion);
		}
			
		return paquetePif;
	}

	protected Boolean handleTieneSecuencia(
			String idOde) 
	throws Exception {
		Boolean resultado = Boolean.FALSE;
		try{
			LocalizadorVO localizador = this.getSrvLocalizadorService()
			.consultaLocalizador(idOde);
			
			File ruta = new File(localizador.getPath() + BARRA + MANIFEST_NAME);
			Manifest manifest = null;
			if(ruta.exists() && ruta.isFile()){
				
				manifest = this.getScormDao().parsearODELazy(ruta);
	    	
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
	    	
				if(manifestAgrega.tieneSecuencias())
					resultado = Boolean.TRUE;
			}	
		}catch (Exception e) {
			logger.error("No se ha podido obtener el manifest - ", e);
		}
		
    	return resultado;
	}
	
	
	
	private PaquetePifVO validarODE(
			String pathOrigen)
	throws Exception
	{
		
			PaquetePifVO paquetePif=new PaquetePifVO();

		//Validamos el ODEO
			logger.debug("El path del Ode a comprimir es = <" + pathOrigen+">");
			try{
			SrvValidadorService validadorService = this.getSrvValidadorService();
			logger.debug("Validamos el fichero manifest");
			ValidaVO valid = validadorService.obtenerValidacionBin(pathOrigen);
			
			CBValidoVO valida = new CBValidoVO();
			valida.setEsValidoManifest(valid.getEsValidoManifest());
			valida.setResultadoValidacion(valid.getResultadoValidacion());
			valida.setRutaManifest(valid.getRutaManifest());
			
			paquetePif.setResultadoValidacion(valida);
			
			logger.debug("\nResultado validacion = <" + valid.getResultadoValidacion()+">");
			logger.debug("Despues de validar = <" + valid.getEsValidoManifest()+">");
			
			return paquetePif;
		}catch(Exception e){
			logger.error("Error al validar el ode con path <" +pathOrigen+"> - ",e );
			throw e;
		}
	}
	
	private void crearTransformacion(
			String tipoPif,
			String carpetaExport,
			String pathODE,
			String pathZip,
			String idODE)
	throws Exception
	{
		
		String[] listaEsquemas;
		try{
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//				tipo SCORM 2004 SIN SUBMANIFEST
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(tipoPif.equals(TipoPIFCst.SCORM_04_SIN_SUBMANIF.toString()))
			{
				if(logger.isDebugEnabled()) logger.debug("se ha seleccionado Scorm04SinSubmanif. Genero la carpeta que llevara el nuevo manifest");
				StringBuffer nuevoManifest= new StringBuffer(carpetaExport);
				nuevoManifest.append(BARRA).append(MANIFEST_NAME);
				File fileManifest =new File(nuevoManifest.toString());
				
				try{
					this.getScormDao().escribirODE(aplanarManifest(idODE,pathODE), fileManifest );
				}catch(ParseadorException e){
					logger.warn("Error al escribir/parsear el manifest - ", e);
				}catch(Exception e){
					logger.warn("Error al aplanar el manifest - ",e);
				}
				
				fileManifest = null;
				
				listaEsquemas= new String[1];
				listaEsquemas[0]=MANIFEST_NAME;
				
				if(logger.isDebugEnabled()) logger.debug("genero el zip sin el manifest");
				this.getZipDao().comprimir(pathZip, pathODE, listaEsquemas);
				if(logger.isDebugEnabled()) logger.debug("agrego al zip el manifest generado");
				this.getZipDao().comprimir(pathZip, carpetaExport);
			}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//				tipo SCORM 1.2 E IMSCP
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else if(tipoPif.equals(TipoPIFCst.SCORM_12.toString() ) || 
					tipoPif.equals(TipoPIFCst.IMS_CP.toString()))
			{
				if(logger.isDebugEnabled()) logger.debug("el tipo pif no es SCORM2004, por lo que voy a transformar el manifest");
				String xslt="";
				String schemas="";
				String result="";
				String xmlOrigen="";

				if(tipoPif.equals(TipoPIFCst.SCORM_12.toString()))
				{
					if(logger.isDebugEnabled()) logger.debug("se ha seleccionado SCORM_12 .. voy a modificar el manifest");
					xslt=getPropertyValue("transformacion.scorm12.xslt");
					if(logger.isDebugEnabled()) logger.debug("xslt a utilizar.. " + xslt);
					//copiar los esquemas del scorm1.2
					schemas= getPropertyValue("transformacion.scorm12.schema");
					if(logger.isDebugEnabled()) logger.debug("agrego al zip los ficheros del esquema de SCORM1.2");
					
				}else if(tipoPif.equals(TipoPIFCst.IMS_CP.toString()))
				{
					if(logger.isDebugEnabled()) logger.debug("se ha seleccionado IMS_CP .. voy a modificar el manifest");
					xslt=getPropertyValue("transformacion.imscp.xslt");
					if(logger.isDebugEnabled()) logger.debug("xslt a utilizar.. " + xslt);
					schemas= getPropertyValue("transformacion.imscp.schema");
					if(logger.isDebugEnabled()) logger.debug("agrego al zip los ficheros del esquema de ISC_MP");
				}

				result=carpetaExport+ BARRA +MANIFEST_NAME;
				xmlOrigen= pathODE+ BARRA +MANIFEST_NAME;
					
				Transformador tr= new TransformadorSaxonImpl();
				if(tr.transformar(xslt , xmlOrigen , result) )
				{
					
					if(logger.isDebugEnabled()) logger.debug("genero el zip sin los esquemas del scrom2004");
					listaEsquemas= getPropertyValue("esquemasSCORM2004").split(",");
					this.getZipDao().comprimir(pathZip, pathODE, listaEsquemas);
					this.getZipDao().comprimir(pathZip, schemas);

					
					if(logger.isDebugEnabled()) logger.debug("agrego al zip el manifest generado");
					this.getZipDao().comprimir(pathZip, carpetaExport);
				}else
				{
					throw new Exception("Error al intentar convertir manifest a SCORM 1.2 o IMS-CP");
				}
			}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//				HTML y CONTENIDOS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else if(tipoPif.equals(TipoPIFCst.HTML.toString()) ||
					  tipoPif.equals(TipoPIFCst.CONTENIDOS.toString()))
			{
				if(logger.isDebugEnabled()) logger.debug("genero el zip sin los esquemas del scorm2004");
				
				List<String> lista= new ArrayList<String>();
				lista.add(MANIFEST_NAME);
				lista.addAll(Arrays.asList(getPropertyValue("esquemasSCORM2004").split(",")));
				lista.addAll(Arrays.asList(getPropertyValue("esquemasLomes").split(",")));
				listaEsquemas =lista.toArray(new String[0]);
				
				this.getZipDao().comprimir(pathZip+ "/contenido", pathODE, listaEsquemas);
			
				GeneradorHtml gen= new GeneradorHtml();
				if(tipoPif.equals(TipoPIFCst.HTML.toString()))
				{
					if(logger.isDebugEnabled()) logger.debug("agrego al zip la carpeta de estilos static");
					this.getZipDao().comprimir(pathZip + BARRA +  "contenido" + BARRA + "static", getPropertyValue("carpeta.static"));
					
					if(logger.isDebugEnabled()) logger.debug("creo los ficheros html necesarios para el index");
					gen.generarIndexCompleto(pathODE, carpetaExport);
				}
				if(logger.isDebugEnabled()) logger.debug("creo la carpeta Catalogacion y genero los metadatos");
				gen.obtenerLOMES(pathODE, carpetaExport);
				if(logger.isDebugEnabled()) logger.debug("agrego al zip los ficheros generados");
				this.getZipDao().comprimir(pathZip, carpetaExport);
			}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//				tipo SCORM 2004
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else // si el tipoPif no es reconocido.. se devuelve por defecto un paquete del tipo SCORM 2004
			{
				if(logger.isDebugEnabled()) logger.debug("se ha seleccionado Scorm04 ");
				//agrego todos los ficheros al zip
				this.getZipDao().comprimir(pathZip, pathODE);
				
			}
			File exportar= new File(carpetaExport);
			if(exportar.exists())
				UtilesFicheros.eliminar(exportar);
		}catch (Exception e) {
			logger.error("Error al hacer la transformacion - ", e);
			throw e;
		}
	}


	/**
	 * Este metodo permite eliminar ficheros creados durante el intercambio de 
	 * paquetes creados por el modulo Entregar con otros modulos de la 
	 * plataforma.
	 * 
	 * @param identificadores array de String indicando identificadores de ODEs
	 */
	protected void handleEliminarTemporales(
			String[] identificadores)
	throws Exception 
	{
		String pathTemp = getPropertyValue("export.PathBase");
		
		for (int i = 0; i < identificadores.length; i++) {
			logger.debug("Se va a eliminar el fichero: <" + pathTemp+ identificadores[i]+">");
			File fichero= new File(pathTemp+ identificadores[i]);
			if(fichero.exists())
				UtilesFicheros.eliminar(fichero );
			
			IdentificadorCriteria criteria= new IdentificadorCriteria(identificadores[i]);
			List<CacheFicherosDao> lista = this.getCacheFicherosDao().obtenerFicherosPorIdentificador(criteria);
			if (lista.size()>0) {
				this.getCacheFicherosDao().remove(lista);
			}
		}
		

	}

	@Override
	protected ResultadoTransformacionVO[] handleTransformarFormatosODEs(
			ArgTransformarOdesVO argumentoTransformarODEs) 
	throws Exception 
	{
		List<ResultadoTransformacionVO> resultados= new ArrayList<ResultadoTransformacionVO>();
		String pathOrigen= argumentoTransformarODEs.getPathOrigen().replace("\\", "/");
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//chequear que las transformaciones estén permitidas...
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		CBValidoVO validacion= new CBValidoVO();
		try{
			SrvValidadorService validadorService = this.getSrvValidadorService();
			logger.debug("Validamos el fichero manifest");
			ValidaVO valid = validadorService.obtenerValidacionBin(pathOrigen);
			validacion.setEsValidoManifest(valid.getEsValidoManifest());
			validacion.setResultadoValidacion(valid.getResultadoValidacion());
			validacion.setRutaManifest(valid.getRutaManifest());
			
			logger.debug("\nResultado validacion = <" + valid.getResultadoValidacion()+">");
			logger.debug("Despues de validar = <" + valid.getEsValidoManifest()+">");
		
		}catch(Exception e){
			logger.error("Error al validar el ode con path <" +pathOrigen+"> - " ,e);
			validacion.setEsValidoManifest(Boolean.FALSE);
			validacion.setResultadoValidacion("Error inesperado mientras se accedia al servicio de validacion");
			validacion.setRutaManifest(pathOrigen);
		}

		if(!validacion.getEsValidoManifest().booleanValue())
		{
			ResultadoTransformacionVO res= new ResultadoTransformacionVO();
			res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_VALIDACION);
			res.setPathOrigen(argumentoTransformarODEs.getPathOrigen());
			res.setMensaje("El path origen: " + pathOrigen + " No es válido ");
			res.setResultadoValidacion(validacion);
			resultados.add(res);
			return resultados.toArray(new ResultadoTransformacionVO[0]);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//verifico que el directorio origen exista y no esté vacío
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File origen= new File(pathOrigen);
		if(!origen.exists() || !origen.isDirectory())
		{
			ResultadoTransformacionVO res= new ResultadoTransformacionVO();
			res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ORIGEN_NO_EXISTE);
			res.setPathOrigen(argumentoTransformarODEs.getPathOrigen());
			res.setMensaje("El paht origen: " + pathOrigen + " no existe o está vacío ");
			res.setResultadoValidacion(validacion);
			resultados.add(res);
			return resultados.toArray(new ResultadoTransformacionVO[0]);
		}else if(origen.list().length== 0)
		{
			ResultadoTransformacionVO res= new ResultadoTransformacionVO();
			res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ORIGEN_NO_EXISTE);
			res.setPathOrigen(argumentoTransformarODEs.getPathOrigen());
			res.setMensaje("El paht origen: " + pathOrigen + " no existe o está vacío ");
			res.setResultadoValidacion(validacion);
			resultados.add(res);
			return resultados.toArray(new ResultadoTransformacionVO[0]);
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		File destino= new File(argumentoTransformarODEs.getPathDestino());
		if(destino.exists() && !destino.canWrite())
		{
			
			ResultadoTransformacionVO res= new ResultadoTransformacionVO();
			res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_DESTINO_NO_ACCESIBLE);
			res.setPathOrigen(argumentoTransformarODEs.getPathOrigen());
			res.setMensaje("El paht destino: " + argumentoTransformarODEs.getPathDestino() + " no es accesible ");
			res.setResultadoValidacion(validacion);
			resultados.add(res);
			return resultados.toArray(new ResultadoTransformacionVO[0]);
		}else if(!destino.exists())
		{
			destino.mkdirs();
		}

		String carpetaTemp= argumentoTransformarODEs.getPathDestino() + "/temp";
		File carpetaTempFile= new File(carpetaTemp);
		carpetaTempFile.mkdirs();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//		File[] listaOrigen= origen.listFiles();
		TipoOde tipoOde= new TipoOde();
		
		//esquemas xsd a incluir en el zip destino
		String esquemasDestino="";
		if(TipoPIFCst.IMS_CP.equals(argumentoTransformarODEs.getTipoPifDestino()))
		{
			esquemasDestino = getPropertyValue("transformacion.scorm12.schema");
		}else if(TipoPIFCst.SCORM_12.equals(argumentoTransformarODEs.getTipoPifDestino()))
		{ 
			esquemasDestino = getPropertyValue("transformacion.imscp.schema");
		}else
		{
			esquemasDestino = getPropertyValue("carpeta.schemas");
		}
		
		String[] listaFiltro;
		String listaFiltroScorm2004	= MANIFEST_NAME + "," + getPropertyValue("esquemasSCORM2004");
		String listaFiltroScorm12	= MANIFEST_NAME + "," + getPropertyValue("esquemasSCORM12"); 
		String listaFiltroImscp		= MANIFEST_NAME + "," + getPropertyValue("esquemasIMSCP"); 
		
//		for (int i = 0; i < listaOrigen.length; i++) {
			String rutaManifest =origen.getCanonicalPath() + BARRA + MANIFEST_NAME ;
			rutaManifest = rutaManifest.replace("\\", "/");
			
			tipoOde.obtenerTipoOde(rutaManifest); 
			String tipoOrigen = tipoOde.getTipo();
			if(tipoOrigen!=null)
			{
				String path = rutaManifest;
				String pathCarpetaODE= origen.getCanonicalPath().replace("\\", "/");
				logger.debug("nombreCarpetaODE: <" + pathCarpetaODE+">");

				String nombreCarpetaTemp = pathCarpetaODE.substring( pathCarpetaODE.lastIndexOf(BARRA));
				logger.debug("nombreCarpetaTemp: <" + nombreCarpetaTemp+">");
				
				String pathFicheroZip=argumentoTransformarODEs.getPathDestino()+ nombreCarpetaTemp + ".zip";
				pathFicheroZip= pathFicheroZip.replace("\\", "/");
				logger.debug("path fichero zip: <" + pathFicheroZip+">");
				File ficheroZip= new File(pathFicheroZip);
				if(ficheroZip.exists())
				{
					logger.debug("el fichero existe, lo elimino: <" + pathFicheroZip+">");
					ficheroZip.delete();
				}
				
				ficheroZip=null;
				
				if( (ConstantesAgrega.SCORM_04.equals(tipoOrigen)  &&  TipoPIFCst.SCORM_04.equals(argumentoTransformarODEs.getTipoPifDestino())) ||
					(ConstantesAgrega.SCORM_12.equals(tipoOrigen)  &&  TipoPIFCst.SCORM_12.equals(argumentoTransformarODEs.getTipoPifDestino())) ||
					(ConstantesAgrega.IMS_CP.equals(tipoOrigen)  &&  TipoPIFCst.IMS_CP.equals(argumentoTransformarODEs.getTipoPifDestino())) )
				{
					// si el origen y el destino es del mismo tipo, simplemente copio el contenido de la carpeta en el zip.
					
					try{
						this.getZipDao().comprimir(pathFicheroZip, pathCarpetaODE);
						
						ResultadoTransformacionVO res= new ResultadoTransformacionVO();
						res.setCodigo(CodigoResultadoTransformacionEnum.TRANSFORMACION_EXITOSA);
						res.setPathOrigen(path);
						res.setMensaje("zip generado con éxito " + pathFicheroZip );
						resultados.add(res);
					
					}catch (Exception e) {
						logger.error("error al crear el zip: - ",e);
						ficheroZip= new File(pathFicheroZip);
						ficheroZip.delete();
						ficheroZip=null;
						
						ResultadoTransformacionVO res= new ResultadoTransformacionVO();
						res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ZIP_MAL_FORMADO);
						res.setPathOrigen(path);
						res.setMensaje("no se pudo generar el fichero zip " + pathFicheroZip );
						res.setResultadoValidacion(validacion);
						resultados.add(res);
						
					}
					

				}else{
					logger.debug("el fichero <" + rutaManifest + "> es del tipo <"  + tipoOrigen + "> va a ser trasnformado al tipo <" +argumentoTransformarODEs.getTipoPifDestino()+">");
					//selecciono el template
						String template ="";
						if(	ConstantesAgrega.IMS_CP.equals(tipoOrigen)){
							listaFiltro = listaFiltroImscp.split(",");
							
							if(TipoPIFCst.SCORM_04.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template= TEMPLATE_IMSCP2SCORM2004 ;
							}
							else if (TipoPIFCst.SCORM_12.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template= TEMPLATE_IMSCP2SCORM12 ;  //imscp a scorm 12
							}
							else if(TipoPIFCst.SCORM_04_SIN_SUBMANIF.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template= TEMPLATE_IMSCP2SCORM2004 ;
							}
						}
						else if(ConstantesAgrega.SCORM_12.equals(tipoOrigen) )
						{
							listaFiltro = listaFiltroScorm12.split(",");
							if(TipoPIFCst.SCORM_04.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template=TEMPLATE_SCORM122SCORM2004 ; 
							}
							else if(TipoPIFCst.IMS_CP.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template=TEMPLATE_SCORM122ISMCP;
							}
							else if(TipoPIFCst.SCORM_04_SIN_SUBMANIF.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template=TEMPLATE_SCORM122SCORM2004 ;
							}
						}
						else
						{
							listaFiltro = listaFiltroScorm2004.split(",");
							logger.debug("origen: <" + tipoOrigen + ">  destino: <" + argumentoTransformarODEs.getTipoPifDestino().toString()+">");
							if(TipoPIFCst.SCORM_12.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template=TEMPLATE_SCORM20042SCORM12 ; //scorm 12 a scorm 2004
							}
							else if(TipoPIFCst.IMS_CP.equals(argumentoTransformarODEs.getTipoPifDestino() ) )
							{
								template=TEMPLATE_SCORM20042ISMCP;
							}
						}
					
							
							
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						String pathTemplate= getPropertyValue("carpeta.xslt") + BARRA + template;
						if(logger.isDebugEnabled())logger.debug("path template: <" + pathTemplate+">");
						
						// creo las carpetas donde voy a depositar el manifest transformado.
						String pathCarpetaTempODE= carpetaTemp + nombreCarpetaTemp;
						if(logger.isDebugEnabled())logger.debug("path carpeta temp ODE: <" + pathCarpetaTempODE+">");
						File carpetaTempOde= new File(pathCarpetaTempODE);
						carpetaTempOde.mkdirs();
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						// genero el fichero zip filtrando los ficheros que no quiero meter.
						if(TipoPIFCst.IMS_CP.equals(argumentoTransformarODEs.getTipoPifDestino()) || 
						   TipoPIFCst.SCORM_12.equals(argumentoTransformarODEs.getTipoPifDestino()) || 
						   TipoPIFCst.SCORM_04.equals(argumentoTransformarODEs.getTipoPifDestino()) )
						{
							try{
								this.getZipDao().comprimir(pathFicheroZip, pathCarpetaODE, listaFiltro);
				
								String result= pathCarpetaTempODE + BARRA + MANIFEST_NAME ;
								if(logger.isDebugEnabled())logger.debug("fichero resultado: <" + result+">");
								Transformador tr= new TransformadorSaxonImpl();
								if(tr.transformar(pathTemplate , path , result) )
								{
									
									if(logger.isDebugEnabled()) logger.debug("agrego al zip el manifest generado");
									this.getZipDao().comprimir(pathFicheroZip, pathCarpetaTempODE);
								}
								
								this.getZipDao().comprimir(pathFicheroZip, esquemasDestino);
	
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.TRANSFORMACION_EXITOSA);
								res.setPathOrigen(path);
								res.setMensaje("zip generado con éxito " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
							
							}catch (Exception e) {
								logger.error("error al crear el zip: - ",e);
								ficheroZip= new File(pathFicheroZip);
								ficheroZip.delete();
								ficheroZip=null;
								
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ZIP_MAL_FORMADO);
								res.setPathOrigen(path);
								res.setMensaje("no se pudo generar el fichero zip - " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
								
							}
						}else if(TipoPIFCst.SCORM_04_SIN_SUBMANIF.equals(argumentoTransformarODEs.getTipoPifDestino()))
						{
							// debería convertir el ode directamente si el origen es Scorm2004.. 
							// su fuera scorm12 o imscp habría que cambiar primero a scorm2004 antes de convertirlo
							

							try{
								
								String resultTemp2004= null;
								if(!ConstantesAgrega.SCORM_04.equals(tipoOde.getTipo()))
								{
									resultTemp2004= pathCarpetaTempODE + BARRA + "2004" ;
									File carpetaTransitoria= new File(resultTemp2004);
									if (!carpetaTransitoria.exists())
										carpetaTransitoria.mkdirs();
									carpetaTransitoria=null;
									
									String resultTemp2004Manifest=  resultTemp2004 + BARRA  + MANIFEST_NAME  ;

									Transformador tr= new TransformadorSaxonImpl();
									tr.transformar(pathTemplate , path , resultTemp2004Manifest);
								}
	 							
								StringBuffer nuevoManifest= new StringBuffer(pathCarpetaTempODE);
								nuevoManifest.append(BARRA).append(MANIFEST_NAME);
								File fileManifest =new File(nuevoManifest.toString());
								if(resultTemp2004!=null  )
								{
									this.getScormDao().escribirODE(aplanarManifest(null, resultTemp2004), fileManifest );
									UtilesFicheros.eliminar(new File(resultTemp2004) );
								}else
								{
									this.getScormDao().escribirODE(aplanarManifest(null, pathCarpetaODE), fileManifest );
								}
								

								this.getZipDao().comprimir(pathFicheroZip, pathCarpetaODE, listaFiltro);
								this.getZipDao().comprimir(pathFicheroZip, pathCarpetaTempODE);
								
								this.getZipDao().comprimir(pathFicheroZip, esquemasDestino);
								
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.TRANSFORMACION_EXITOSA);
								res.setPathOrigen(path);
								res.setMensaje("zip generado con éxito " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
							
							}catch (Exception e) {
								logger.error("error al crear el zip: - ",e);
								ficheroZip= new File(pathFicheroZip);
								ficheroZip.delete();
								ficheroZip=null;
								
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ZIP_MAL_FORMADO);
								res.setPathOrigen(path);
								res.setMensaje("no se pudo generar el fichero zip " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
								
							}
							
						}else
						{
							try{
								GeneradorHtml gen= new GeneradorHtml();
								if(ConstantesAgrega.SCORM_12.equals(tipoOrigen))
									gen.setFormato(GeneradorHtml.SCORM_12);
								else if(ConstantesAgrega.IMS_CP.equals(tipoOrigen))
									gen.setFormato(GeneradorHtml.IMSCP);
								

								
								this.getZipDao().comprimir(pathFicheroZip+ BARRA +  "contenido", pathCarpetaODE, listaFiltro);
								
								if(argumentoTransformarODEs.getTipoPifDestino().equals(TipoPIFCst.HTML))
								{
									this.getZipDao().comprimir(pathFicheroZip + BARRA +  "contenido" + BARRA + "static", getPropertyValue("carpeta.static"));
									gen.generarIndexCompleto(pathCarpetaODE, pathCarpetaTempODE);
								}
								
								gen.obtenerLOMES(pathCarpetaODE, pathCarpetaTempODE);
								this.getZipDao().comprimir(pathFicheroZip, pathCarpetaTempODE);
								
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.TRANSFORMACION_EXITOSA);
								res.setPathOrigen(path);
								res.setMensaje("zip generado con éxito " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
							}catch (Exception e) {
								logger.error("error al crear el zip: - ",e);
								ficheroZip= new File(pathFicheroZip);
								ficheroZip.delete();
								ficheroZip=null;
								
								ResultadoTransformacionVO res= new ResultadoTransformacionVO();
								res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_ZIP_MAL_FORMADO);
								res.setPathOrigen(path);
								res.setMensaje("no se pudo generar el fichero zip " + pathFicheroZip );
								res.setResultadoValidacion(validacion);
								resultados.add(res);
							}
						}
						
					}
				
				}else{
					ResultadoTransformacionVO res= new ResultadoTransformacionVO();
					res.setCodigo(CodigoResultadoTransformacionEnum.ERROR_TIPO_ORIGEN_NO_PERMITIDO);
					res.setPathOrigen(rutaManifest);
					res.setMensaje("El fichero origen " + rutaManifest + " no puede ser transformado.");
					res.setResultadoValidacion(validacion);
					resultados.add(res);
				}
//			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		//elimino las carpetas de temporales
		UtilesFicheros.eliminar(carpetaTempFile);
		carpetaTempFile= null;	
		
		
		return resultados.toArray(new ResultadoTransformacionVO[0]);
	}
	
	
	protected String handleGenerarPDF(String idOde) throws java.rmi.RemoteException {

		if(logger.isDebugEnabled()) logger.debug("SrvEntregarServiceImpl - generarPDF de "+idOde);
		
		String rutaFicheroPDF ="";
		String rutaFicheroHtmlPrevio = "";
		String rutaFicheroHtml ="";
		String rutaSalida ="";
		String rutaFicheroZip ="";
		try{
			String carpetaExport = getPropertyValue("export.PathBase")+ idOde+ BARRA + "/PDF";
			String carpetaExportHtml = getPropertyValue("export.PathBase")+ idOde+ BARRA + "/HTML";
			
			File carpetaExportFile=new File(carpetaExport);
			
			if(!carpetaExportFile.exists())
				carpetaExportFile.mkdirs();
			
			File carpetaExportHtmlFile=new File(carpetaExportHtml);
			
			if(!carpetaExportHtmlFile.exists())
				carpetaExportHtmlFile.mkdirs();

			rutaFicheroHtmlPrevio = carpetaExportHtml + BARRA + idOde + "_previo.html";
			rutaFicheroHtml = carpetaExportHtml + BARRA + idOde + ".html";
			
			rutaFicheroPDF = carpetaExport+ BARRA + idOde + ".pdf";
			rutaFicheroZip = getPropertyValue("export.PathBase")+ idOde + ".zip";

			
			// Obtenemos el localizador del ODE
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			String pathODE= localizadorService.consultaLocalizador(idOde).getPath();

			if(logger.isDebugEnabled()) logger.debug("SrvEntregarServiceImpl - el localizador es: "+pathODE);
			
			// Obtenemos el manifest del ODE
			Manifest manifest = this.parsearManifiesto(pathODE + File.separator + "imsmanifest.xml");						
			
			if(logger.isDebugEnabled()) logger.debug("Se ha parseado el manifest");
			
			generarHtmlMetadatos(manifest,rutaFicheroHtmlPrevio);
			
			if(logger.isDebugEnabled()) logger.debug("Se ha generado el html previo en : " + rutaFicheroHtmlPrevio);
						
			 // Limpiamos el html de {} por idiomas no informados
            limpiarHtml(rutaFicheroHtmlPrevio, rutaFicheroHtml);
            
			if(logger.isDebugEnabled()) logger.debug("Se ha generado el html definitivo en : " + rutaFicheroHtml);
                        
			generarPdfMetadatos(rutaFicheroHtml,rutaFicheroPDF);
			
			if(logger.isDebugEnabled()) logger.debug("Se ha generado el pdf en : " + rutaFicheroPDF);
			
            // Borramos los ficheros html intermedios
			File ficHtmlTemporal= new File(rutaFicheroHtmlPrevio);
            if (ficHtmlTemporal.exists())
            	ficHtmlTemporal.delete();

            File ficHtml= new File(rutaFicheroHtml);
            if (ficHtml.exists())
            	ficHtml.delete();

            File exportar= new File(rutaFicheroZip);
			
			String pathZip= exportar.getCanonicalPath().replace("\\", "/"); 
			if(logger.isDebugEnabled()) logger.debug("Generamos el zip en : " + pathZip);
			
			this.getZipDao().comprimir(pathZip,carpetaExport);
			
			int inicio = rutaFicheroPDF.indexOf(getPropertyValue("export.UrlBase"));
			if(inicio<0)
				inicio=0;
			
			
			rutaSalida=rutaFicheroZip.substring( inicio, rutaFicheroZip.length());
			
		}catch (Exception e){
		   logger.error("Error en SrvEntregarServiceImpl:GenerarPDF : " , e);		   
		}
		
		if(logger.isDebugEnabled()) logger.debug("SrvEntregarServiceImpl - generarPDF ruta fichero devuelto: "+rutaSalida);
      
		return rutaSalida;
	}
	

	@Override
	protected String handleObtenerManifest(String idOde) throws Exception {

		if(logger.isDebugEnabled()) logger.debug("SrvEntregarServiceImpl - obtener imsmanifest de "+idOde);
		
		String rutaManifest="";
		String carpetaExportXML="";
		String destino="";
		
		try{
			carpetaExportXML = getPropertyValue("export.PathBase")+idOde+File.separator+"XML";
			File carpetaDestino=new File(carpetaExportXML);
			if(!carpetaDestino.exists()) carpetaDestino.mkdirs();
						
			// Obtenemos el localizador del ODE
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			String pathODE= localizadorService.consultaLocalizador(idOde).getPath();
			if(logger.isDebugEnabled()) logger.debug("SrvEntregarServiceImpl - el localizador es: "+pathODE);
			
			String pathManifest = pathODE+File.separator+MANIFEST_NAME;
			destino = carpetaExportXML+File.separator+MANIFEST_NAME;
			UtilesFicheros.copiar(new File(pathManifest), new File(destino));
			
		} catch (Exception e){
		   logger.error("Error en obtenerManifest: " , e);	
		   return e.toString();
		}
		if(logger.isDebugEnabled()) logger.debug("Ruta del manifest devuelto: "+destino);
		return destino;
	}
	
	
	private Manifest parsearManifiesto(String localizador) throws Exception
	{
		Manifest manifest = null;
		try 
		{
			manifest = this.getScormDao().parsearODELazy(new File(localizador));
		} 
		catch (Exception e) 
		{
			logger.error("No se puede parsear el fichero; localizador"+localizador);
			throw e;
		}
		return manifest;
	}
	
	
	public boolean generarHtmlMetadatos(final Manifest manifest, String path) {

		boolean result = true;

		if(logger.isDebugEnabled()) logger.debug("generarHtmlMetadatos -> path " + path);
		
		try {
			
			File fileXLST = new File(XSLT_PDF_PATH);
			if(!fileXLST.exists()) {
				logger.error("No se encontró fichero "+XSLT_PDF_PATH);
				return false;
			}
			
			//Aplicamos la transformación al ODE
			if(logger.isDebugEnabled())  logger.info("Extrayendo metadatos de "+manifest.getIdentifier());
			TransformadorSaxonImpl transformacion = new TransformadorSaxonImpl();
        	StringWriter sw= new StringWriter();
			SCORM2004Dao scormDao = new SCORM2004Dao();
			scormDao.escribirODE(manifest, sw);
			if(logger.isDebugEnabled()) logger.info("El ScormDao es :"+scormDao);
            StringReader sr= new StringReader(sw.toString());
            result = transformacion.transformar(XSLT_PDF_PATH, new StreamSource(sr), path/*+identificador+REPORT_EXT*/);
            if(logger.isDebugEnabled()) logger.info("El resultado con la transformacion es :"+result);           
			
		} catch (Exception e) {
			logger.error("No se ha podido recuperar los metadatos del manifiesto " + manifest.getIdentifier(),e);
			return false;
		}
		return result;
	}
	
	public void limpiarHtml(String pathOrigen, String pathDestino)
			throws Exception {
		
		FileWriter fw = null;
		
		try {			
			
			BufferedReader bf = new BufferedReader(new FileReader(pathOrigen));
			fw = new FileWriter(pathDestino);
			String sCadena = "";
			String sTxtFic = "";
			while ((sCadena = bf.readLine()) != null) {
				sTxtFic += sCadena;
			}
			sTxtFic = sTxtFic.replace("()", "");			
			
			fw.write(sTxtFic);

		} catch (Exception e) {
			logger.error("Error al limpiar el html", e);
		} finally {
			try {
				fw.close();
			} catch (Exception e2) {
				logger.error("Error al cerrar el fichero");
			}
		}
	}

	public static void generarPdfMetadatos(String rutaFicheroHtml,
			String rutaFicheroPDF) throws Exception {
		
		if (logger.isDebugEnabled())
			logger.debug("generarPdfMetadatos. Generar : " + rutaFicheroPDF
					+ " a partir de : " + rutaFicheroHtml);

		OutputStream os = null;
		try {
			File inputFile = new File(rutaFicheroHtml);

			os = new FileOutputStream(new File(rutaFicheroPDF));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(inputFile);
			renderer.layout();

			renderer.createPDF(os);

		} catch (Exception e) {
			logger.error("Error ex " + e.getMessage());			
			throw e;
		} finally {
			try {

				if (os != null)
					os.close();

			} catch (Exception e) {
				logger.error("Error al cerrar el OS : " + e.getMessage());
				
			}
		}

	}
	

	/**
	 * Este metodo copia el recurso unico de un ODE y lo pone en una ruta accesible para 
	 * que sea descargable. El ODE debe tener un solo recurso. Se devuelve la ruta donde esta el recurso
	 */
	@Override
	protected String handleObtenerRecursoUnicoDelODE(String idOde)
			throws Exception {

		String rutaSalida ="";

		try{
			String carpetaExport = getPropertyValue("export.PathBase")+idOde+File.separator+"IMG";
			File carpetaExportFile=new File(carpetaExport);
			
			if(!carpetaExportFile.exists())
				carpetaExportFile.mkdirs();
			
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idOde);

			File manifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);			
			

			int numRecursos=manAgrega.getManifest().getResources().getResourceCount();
			if(numRecursos!=1) {
				logger.error("El ODE con id "+idOde+ "tiene mas de un recurso");
				return rutaSalida;
			}
			
			int numFicherosEnRecurso=manAgrega.getManifest().getResources().getResource(0).getFileCount();
			if(numFicherosEnRecurso!=1) {
				logger.error("El ODE con id "+idOde+ "tiene mas de un fichero en su unico recurso");
				return rutaSalida;
			}
			
			es.pode.parseadorXML.castor.File recurso = manAgrega.getManifest().getResources().getResource(0).getFile(0);
			//Ruta del recurso dentro del ODE
			String rutaRecurso=recurso.getHref();
			
			File recursoFisico = new File(localizadorNP.getPath()+File.separator+rutaRecurso);	
			String[] lista = rutaRecurso.split(File.separator);
			File recursoFisicoCopia = new File(carpetaExport+File.separator+lista[lista.length-1]);		
			
			copyFileUsingChannel(recursoFisico,recursoFisicoCopia);		
			rutaSalida=recursoFisicoCopia.getPath().replace("uploads", "");
			
		} catch (Exception e){
		   logger.error("Error: " , e);		   
		}
		
		return rutaSalida;
	}
	
	
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			sourceChannel.close();
			destChannel.close();
		}
	}

	
	/**
	 * Metodo que devuelve true si el ODE esta catalogado con nivel 
	 * de agregacion 1 y si tiene un unico recurso de tipo imagen
	 */
	@Override
	protected boolean handleOdeConRecursoDirectamenteDescargable(String idOde)
			throws Exception {
					
		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idOde);

		File manifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		ArrayList<Lom> listLomes = new ArrayList<Lom>(manAgrega.recuperarLomes());		
				
		if (listLomes==null || listLomes.size()==0)
			return false;

		LomAgrega lomAg = new LomAgrega(listLomes.get(0));
		GeneralAgrega generalAgrega = lomAg.getGeneralAgrega();
		if (generalAgrega != null) {
			try {
				int nivelDeAgregacion = Integer.parseInt(generalAgrega.getNivelDeAgregacion());
				if(nivelDeAgregacion!=1)
					return false;
			} catch (Exception e) {
				logger.error("ERROR: el campo nivel agregacion["+ generalAgrega.getNivelDeAgregacion()+ "] no es un entero valido.",e);
				return false;
			}
		}
		
		int numRecursos=manAgrega.getManifest().getResources().getResourceCount();
		if(numRecursos!=1) return false;
		
		int numFicherosEnRecurso=manAgrega.getManifest().getResources().getResource(0).getFileCount();
		if(numFicherosEnRecurso!=1) return false;
		
		es.pode.parseadorXML.castor.File recurso = manAgrega.getManifest().getResources().getResource(0).getFile(0);
		if(!esUnaImagen(recurso.getHref()))
			return false;
		return true;
	}
	
	
	/**
	 * Devuelve true si la ruta de un fichero es una imagen
	 */
	private boolean esUnaImagen(String rutaFichero) {
		if(rutaFichero.endsWith(".png") ||
			rutaFichero.endsWith(".PNG") ||
			rutaFichero.endsWith(".jpg") ||
			rutaFichero.endsWith(".JPG") ||
			rutaFichero.endsWith(".jpeg") ||
			rutaFichero.endsWith(".JPEG") ||
			rutaFichero.endsWith(".tiff") ||
			rutaFichero.endsWith(".TIFF") ||
			rutaFichero.endsWith(".gif") ||
			rutaFichero.endsWith(".GIF") ||
			rutaFichero.endsWith(".wmf") ||
			rutaFichero.endsWith(".WMF") ||
			rutaFichero.endsWith(".bmp") ||
			rutaFichero.endsWith(".BMP"))
			return true;
		return false;
	}
	
	/**
	 * Metodo que devuelve la ruta del recurso de un ODE si el ODE esta catalogado con nivel 
	 * de agregacion 1 y si tiene un unico recurso de tipo imagen
	 */	
	/*
	private String obtenerRecursoUnicoODE(String idOde)
			throws Exception {
		
		String rutaRecurso = "";
		
		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idOde);

		File manifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		ArrayList<Lom> listLomes = new ArrayList<Lom>(manAgrega.recuperarLomes());		
				
		if (listLomes==null || listLomes.size()==0)
			return rutaRecurso;

		LomAgrega lomAg = new LomAgrega(listLomes.get(0));
		GeneralAgrega generalAgrega = lomAg.getGeneralAgrega();
		if (generalAgrega != null) {
			try {
				int nivelDeAgregacion = Integer.parseInt(generalAgrega.getNivelDeAgregacion());
				if(nivelDeAgregacion!=1)
					return rutaRecurso;
			} catch (Exception e) {
				logger.error("ERROR: el campo nivel agregacion["+ generalAgrega.getNivelDeAgregacion()+ "] no es un entero valido.",e);
				return rutaRecurso;
			}
		}
		
		int numRecursos=manAgrega.getManifest().getResources().getResourceCount();
		if(numRecursos!=1) return rutaRecurso;
		
		int numFicherosEnRecurso=manAgrega.getManifest().getResources().getResource(0).getFileCount();
		if(numFicherosEnRecurso!=1) return rutaRecurso;
		
		es.pode.parseadorXML.castor.File recurso = manAgrega.getManifest().getResources().getResource(0).getFile(0);
		
		return localizadorNP.getPath()+ BARRA +recurso.getHref();
	}
	*/
 }
