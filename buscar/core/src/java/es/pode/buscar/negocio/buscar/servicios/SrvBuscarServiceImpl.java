// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.buscar.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.exolab.castor.xml.Unmarshaller;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.auditoria.negocio.servicios.InformeMasVO;
import es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica;
import es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega;
import es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica;
import es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaTotal;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.buscar.negocio.buscar.cache.EHCacheConfig;
import es.pode.buscar.negocio.buscar.comparators.DocFechaPublicacionComparator;
import es.pode.buscar.negocio.buscar.comparators.DocRankingComparator;
import es.pode.buscar.negocio.buscar.comparators.TaxonNombreComparator;
import es.pode.buscar.negocio.buscar.pool.MultiSearcher;
import es.pode.buscar.negocio.buscar.pool.MultiSearcherSQI;
import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.indexador.negocio.servicios.busqueda.DocMECSimpleVO;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.indexador.negocio.servicios.busqueda.DocumentosLOM_ESVO;
import es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30;
import es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO;
import es.pode.indexador.negocio.servicios.busqueda.ParamBuscarMecVO;
import es.pode.indexador.negocio.servicios.busqueda.ParamDocsCountVO;
import es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO;
import es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO;
import es.pode.indexador.negocio.servicios.busqueda.SugerenciasVO;
import es.pode.indexador.negocio.servicios.busqueda.TaxonVO30;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.utiles.string.UtilesString;

/**
 * @see SrvBuscarService
 */


public class SrvBuscarServiceImpl
    extends es.pode.buscar.negocio.buscar.servicios.SrvBuscarServiceBase
{

//	private java.util.Properties pSpringProperties = null;
//	private static final String ASTERISCO = "*";
//	private static final String INTERROGACION = "?";
//	private static String esbHost=null;
//	private static String esbPort=null;
//	private static String esbSubdominio=null;
//	private static int maxWaitThread;
//	private static int maxWaitPool;
	
	static EHCacheConfig cacheConfig = null;

	public static final String BUSCARRSS = "BUSCARRSS";
	
	private SrvPropiedadService prop = null;
	private static final String AMBITO_UNIVERSAL = "universal";
	private Logger logger = Logger.getLogger(SrvBuscarServiceImpl.class);
	private static Properties props = null;
	private static final String BUSCARRELACIONADOSAC="BUSCARRELACIONADOSAC";//BUSQUEDA DE ODES RELACIONADOS POR ARBOL CURRICULAR
	private static final String CACHE_RESET ="agregaadmin cachereset";
	private static final String CACHE_RESET_SQI ="agregaadmin cachesqireset";
	private static final String CACHE_RESET_FEDERADA ="agregaadmin cachefederadareset";
	private static final int ORIGEN_PAGINA_DEFAULT = 1;
	private static final int RESULTADOS_PAGINA_DEFAULT = 10;
	private static final Integer NUMERO_RESULTADOS_DEFAULT = 100000;
	private static final String SEPARADOR_PARAM_IDENTIFICADOR = "|";
	private static final String SEPARADOR_VALOR_IDENTIFICADOR = "-";

	private static final String NOMBRE_FICHERO_ESTADISTICAS_TOTALES = "estadisticasAgregaTotales";
	private static final String EXTENSION_FICHERO_ESTADISTICAS = ".xml";

	private static final int NUM_ESTADISTICAS_ACTIVIDAD = 4;
	private static final int NUM_ESTADISTICAS_COBERTURA_CURRICULAR = 7;
	private static final int NUM_ESTADISTICAS_LICENCIAS = 15;
	private static final int NUM_ESTADISTICAS_ODES = 5;
	private static final int NUM_ESTADISTICAS_TERMINOS = 5;
	
	private static String jbossHost=null;
	private static String jbossPort=null;
	private static String jbossSubdominio=null;
	private static String srvFederada=null;
	private static String segundosCaducidadHit=null;
	private static long timeMillisBorradoHits=0;
	private static int maxTesauros;
	private static int maxSugerencias;
	private static String serverId=null;
	private static String serverOn=null;
	
	private static final String[] COLUMNAS_EXCEL = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U"};
	
	public SrvBuscarServiceImpl(){
		super();
		//Se agrega un parametro al metodo EHCacheConfig para evitar usar el servicio de SrvPropiedadService en su clase
		try {
			segundosCaducidadHit=this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SEGUNDOSCADUCIDADHIT);
		} catch (Exception e1) {
			logger.warn("Excepcion generica - "+e1.getCause());
			logger.debug("",e1);
		}
		if(cacheConfig == null) cacheConfig = new EHCacheConfig(segundosCaducidadHit);
		if(props == null){
			InputStream is = null;
			try {
		//		Configuramos la clase de propiedades
				is = this.getClass().getResourceAsStream("/buscar.properties");
				Properties properties = new Properties();
				properties.load(is);
				srvFederada=properties.getProperty("srvFederada");
//				esbSubdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO_ESB);//se elimina SUBDOMINIO_ESB = "subdominio.esb" por falta de uso y aparecer solamente aqui comentado.
//				esbPort = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORT_ESB);
//				esbHost = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST_ESB);
				jbossHost = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST_JBOSS);
				jbossPort = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PUERTO_JBOSS);
				jbossSubdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO_JBOSS);
				segundosCaducidadHit=this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SEGUNDOSCADUCIDADHIT);
//				maxWaitThread=Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_THREAD));
//				maxWaitPool=Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_POOL));
				maxTesauros=Integer.parseInt(properties.getProperty("numero_tesauros"));
				maxSugerencias = Integer.parseInt(properties.getProperty("numero_sugerencias"));
				serverId = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
				serverOn = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON);
				props=properties;
			} catch (IOException e) {
				logger.error("ERROR: fichero no encontrado: buscar.properties - ",e);
				throw new RuntimeException(e);
			} catch (Exception e) {
				logger.warn("Escepcion probable desde obtencion de variable AgregaPropertiesImpl.getInstance().getProperty(agregaProperties.##) - ");
				logger.debug("",e);
			} finally {
				if( is != null ) {
					try {
						is.close();
					} catch (IOException e) {
						logger.warn("IOerror - "+ e.getCause());
						logger.debug("", e);
					}
					
				}
			}
		}
	}
	

    /**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarService#buscarAvanzado(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO)
	 * @param parametros es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30 Este VO alberga los parametros que configuran la busqueda avanzada
	 * @return es.pode.buscar.negocio.servicios.ResultadoBusquedaVO Esta clase representa los resultados de una busqueda encontrados en el repositorio de ODE's
	 */

    protected es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO handleBuscarAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros)
        throws java.lang.Exception
    {	
    	
    	if(timeMillisBorradoHits==0)timeMillisBorradoHits=System.currentTimeMillis();
    	else if((timeMillisBorradoHits+(500*Integer.parseInt(segundosCaducidadHit)))<System.currentTimeMillis()){

    		//if(cacheConfig.getHits().)cacheConfig.getHits().evictExpiredElements();
    		timeMillisBorradoHits=System.currentTimeMillis();
    	}

    	if(parametros.getPalabrasClave()!=null && parametros.getPalabrasClave().equals(CACHE_RESET)){
    		cacheConfig.removeHitsCache();
    		cacheConfig.removeHitsLenghtCache();
    	}
    	if(parametros.getPalabrasClave()!=null && parametros.getPalabrasClave().equals(CACHE_RESET_SQI)){
    		cacheConfig.removeHitsSQICache();
    		cacheConfig.removeHitsSQIAriadneCache();
    	}
    	if(parametros.getPalabrasClave()!=null && parametros.getPalabrasClave().equals(CACHE_RESET_FEDERADA)){
    		MultiSearcher.resetHashFederacion();
    	}
    	if(parametros.getPalabrasClave()!=null && (parametros.getPalabrasClave().equals(CACHE_RESET)||
    			parametros.getPalabrasClave().equals(CACHE_RESET_SQI)||
    			parametros.getPalabrasClave().equals(CACHE_RESET_FEDERADA))){
        	return new ResultadoBusquedaVO(new ValoresBusquedaVO[]{},null,0,"",0,null);
    	}
    	
    	if(parametros.getNumeroResultados()== null || parametros.getNumeroResultados() == 0) parametros.setNumeroResultados(NUMERO_RESULTADOS_DEFAULT);
    	//Con los valores de la busqueda genero el id de cache que tendre y miro a ver si existe ya en la cache.
    	String idCache = UtilesString.removeAccents(this.handleGeneradorIdentificadorAvanzado(parametros).toLowerCase());

    	//Busco en la cache si esta busqueda ya se realizo. Si tengo exito, la devuelvo, si no realizamos la busqueda.
    	Element elemento = cacheConfig.getHits().get(idCache);
    	HitCacheVO resultadoCache = null; //este obj alberga los resultados en la cache
    	//Se ha producido un fallo de cache, tengo que ir a buscar los elementos nuevos
    	
    	if (elemento == null || parametros.getNumeroResultados()==-1) {

    		logger.debug("SrvBuscarServiceImpl - handleBuscarAvanzado: Fallo de cache con idBusqueda["+idCache+"]. Se procede a buscar.");

        	DocumentosVO30 resultados = new DocumentosVO30();
        	
        	//Si no se define idiomas, tomamos los de la plataforma
        	if(parametros.getIdiomaBusqueda()==null) {
        		parametros.setIdiomaBusqueda(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
        	}
        	if(parametros.getIdiomaNavegacion()==null) {
        		parametros.setIdiomaNavegacion(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
        	}

			String[] idComunidades = parametros.getComunidadesSeleccionadas();
        	
        	if(parametros.getFormato()!=null&&parametros.getFormato().length>0&&parametros.getNivelAgregacion()!=null&&
        			(Arrays.asList(parametros.getNivelAgregacion()).contains("2")||
        			Arrays.asList(parametros.getNivelAgregacion()).contains("3")||
        			Arrays.asList(parametros.getNivelAgregacion()).contains("4"))) {
        		
            	//Separar en dos juegos de parámetros
        		ParametrosBusquedaAvanzadaVO30 param1 = new ParametrosBusquedaAvanzadaVO30(parametros);
        		param1.setNivelAgregacion(new String[]{"1"});

        		DocumentosVO30 resultados1=obtieneResultados(param1);        		
        		
        		ParametrosBusquedaAvanzadaVO30 param2 = new ParametrosBusquedaAvanzadaVO30(parametros);
        		param2.setFormato(new String[]{});
 		
    			int numNiveles=parametros.getNivelAgregacion().length;
    			if (Arrays.asList(parametros.getNivelAgregacion()).contains("1"))
    				numNiveles--;
    			
    			String [] niveles = new String[numNiveles];
    			int i=0;
    			if (Arrays.asList(parametros.getNivelAgregacion()).contains("2")) {
    				niveles[i]="2";
    				i++;
    			}
    			if (Arrays.asList(parametros.getNivelAgregacion()).contains("3")) {
    				niveles[i]="3";
    				i++;
    			}
    			if (Arrays.asList(parametros.getNivelAgregacion()).contains("4")) {
    				niveles[i]="4";
    				i++;
    			}
    			
    			param2.setNivelAgregacion(niveles);

        		DocumentosVO30 resultados2=obtieneResultados(param2);
        		
            	//Mezclar resultados
        		if (resultados1!=null && resultados2!=null)        		
        			resultados=combinaResultados(new DocumentosVO30[]{resultados1, resultados2});
        		else if (resultados1!=null) resultados=resultados1;
        		else if (resultados2!=null) resultados=resultados2;
            	
        	} else {        		
        		resultados = obtieneResultados(parametros);
        	}
           				       	
        	//Si es una busqueda local no aplicamos la eliminacion de repetidos
			if(idComunidades!=null && idComunidades.length!=0) {
	    	
	    		String [] sugerencias = resultados.getSugerencias();
        		resultados = ordenarResultados(Arrays.asList(resultados));
        		resultados.setSugerencias(sugerencias);
        	}

			//Cuando tengamos la busqueda federada, habra que realizar la busqueda federada y combinar los resultados.
			//Si la busqueda no da resultados no cacheo la busqueda. Devuelvo las sugerencias si las hay
        	ResultadoBusquedaVO failHit = new ResultadoBusquedaVO();
        	
        	if ( resultados==null || resultados.getSugerencias() == null || resultados.getSugerencias().length==0)
        		//No hay sugerencias, pero devolvemos un resultado vacio.
        		failHit.setSugerencias(new String[]{});
        	else failHit.setSugerencias(resultados.getSugerencias());
        	
        	if ( resultados==null || resultados.getTesauros() == null || resultados.getTesauros().length==0)
        		//No hay sugerencias, pero devolvemos un resultado vacio.
        		failHit.setTesauros(new ResultadosTaxonVO[]{});
        	else {
        		ResultadosTaxonVO[] tesauros= new ResultadosTaxonVO[resultados.getTesauros().length];
        		for (int i=0;i<resultados.getTesauros().length;i++){
        			if(resultados.getTesauros()[i]!=null){
	        			ResultadosTaxonVO tesauro = new ResultadosTaxonVO();
	        			tesauro.setIdentificador(resultados.getTesauros()[i].getIdentificador());
	        			tesauro.setNombre(resultados.getTesauros()[i].getNombre());
	        			tesauros[i] = tesauro;
        			}
        		}
        		failHit.setTesauros(tesauros);        		
        	}
        	
        	if (resultados == null || resultados.getResultados() == null || resultados.getResultados().length == 0){

        		// Si no hay nada que mostrar, devuelvo un resultado vacio
        		failHit.setResultadoBusqueda(new ValoresBusquedaVO[]{});
        		failHit.setNumeroResultados(0);
				failHit.setTotalResultados(0); 
				failHit.setIdBusqueda("");
				return failHit;
        	}
        	
			//La busqueda da resultados, cacheo la busqueda.

			try {
        		//Creamos el hit de cache y le asociamos los documentos de hit. Tambien le decimos el identificador del hit
				HitCacheVO res = creaHitCache(idCache,parametros.getPalabrasClave(),resultados);
				resultadoCache = res;
				
				cacheConfig.getHits().put(new Element(idCache, res));
				
			} catch (Exception e) {
				Exception exc = new Exception("SrvBuscarServiceImpl - handleBuscarAvanzado: ERROR: generando hit de cache con idiomaNavegacion["+parametros.getIdiomaNavegacion()+"], numeroResultados["+parametros.getNumeroResultados()+"], Taxonomia["+Arrays.toString(parametros.getTaxonomia())+"] e idiomaBusqueda["+parametros.getIdiomaBusqueda()+"] y ["+Arrays.toString(resultados.getResultados())+"]resultados encontrados en Indexador/Buscador.", e);
				logger.error(exc);
				throw exc;
			}
    	} else {  
    		//Se han encontrado elementos en la cache
			logger.debug("Hit de cache con idBusqueda <"+idCache+">");
    		resultadoCache = (HitCacheVO)elemento.getObjectValue();
    	}

    	//Devolvemos los resultados de la pagina
    	if(parametros.getBusquedaSimpleAvanzada()!=null && parametros.getBusquedaSimpleAvanzada().equals(BUSCARRSS))
    		return generaPaginaResultadosRSS(parametros, idCache, resultadoCache, resultadoCache.getResultadoBusquedaWeb());
    	if(parametros.getBusquedaSimpleAvanzada()!=null && parametros.getBusquedaSimpleAvanzada().equals(BUSCARRELACIONADOSAC))
    		return new ResultadoBusquedaVO(resultadoCache.getResultadoBusquedaWeb(),
    				resultadoCache.getSugerencias(), 
    				resultadoCache.getNumeroResultados(), 
    				resultadoCache.getIdCache(),
    				resultadoCache.getTotalResultados(),
    				resultadoCache.getTesauros()
    				);
    
    	return generaPaginaResultadosBusqueda(parametros, idCache, resultadoCache, resultadoCache.getResultadoBusquedaWeb());
    }
		
    
	private DocumentosVO30 combinaResultados(DocumentosVO30[] resultadosArray) {
		DocumentosVO30 resultados=new DocumentosVO30();
		
		ArrayList<DocVO30> resultadosParciales = new ArrayList<DocVO30>();
		ArrayList<String> sugerenciasParciales = new ArrayList<String>();
		ArrayList<TaxonVO30> tesaurosParciales = new ArrayList<TaxonVO30>();
		int numeroResultadosParcial = 0;
		int totalResultadosParcial = 0;
		int numDocumentosIndiceParcial = 0;
		
		for (int i = 0; i < resultadosArray.length; i++) {
			if (resultadosArray[i]!=null) {
				
				if (resultadosArray[i].getResultados()!=null) {
					resultadosParciales.addAll(Arrays.asList(resultadosArray[i].getResultados()));
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene resultados");
				
				if (resultadosArray[i].getSugerencias()!=null) {
					sugerenciasParciales.addAll(Arrays.asList(resultadosArray[i].getSugerencias()));
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene sugerencias");
				
				if (resultadosArray[i].getTesauros()!=null) {
					tesaurosParciales.addAll(Arrays.asList(resultadosArray[i].getTesauros()));
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene tesauros");
				
				if (resultadosArray[i].getNumDocumentosIndice()!=null) {
					numDocumentosIndiceParcial += resultadosArray[i].getNumDocumentosIndice();
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene NumDocumentosIndice");
				
				if (resultadosArray[i].getNumeroResultados()!=null) {
					numeroResultadosParcial += resultadosArray[i].getNumeroResultados();
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene NumeroResultados");
				
				if (resultadosArray[i].getTotalResultados()!=null) {
					totalResultadosParcial += resultadosArray[i].getTotalResultados();
				}// else if (logger.isDebugEnabled())logger.debug("Resultado parcial "+i+" no tiene TotalResultados");
			}
		}
		
		if (resultadosParciales!=null) 
			resultados.setResultados(resultadosParciales.toArray(new DocVO30[] {}));
		
		if (sugerenciasParciales!=null) 
			resultados.setSugerencias(sugerenciasParciales.toArray(new String[] {}));
		
		if (tesaurosParciales!=null) 
			resultados.setTesauros(tesaurosParciales.toArray(new TaxonVO30[] {}));
		
		resultados.setVersionComunidadSolicitada("3");
		resultados.setNumeroResultados(numeroResultadosParcial);
		resultados.setTotalResultados(totalResultadosParcial);
		resultados.setNumDocumentosIndice(numDocumentosIndiceParcial);

		return resultados;
	}

	
	private DocumentosVO30 obtieneResultados(
			es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros)
	throws Exception {
		DocumentosVO30 resultados;
		//Curiosa forma de determinar qué llamada hacer...
		String[] idComunidades = parametros.getComunidadesSeleccionadas();		

		if (logger.isDebugEnabled())
		{
			logger.debug("Busqueda obtieneResultados :"  + parametros.getBusquedaSimpleAvanzada());		
			logger.debug("Busqueda obtieneResultados :"  + idComunidades);
			if (idComunidades!=null)
				logger.debug("Busqueda obtieneResultados :"  + idComunidades.length);
		}
		
		if(idComunidades!=null && idComunidades.length!=0) {			
			// 26062012 Tenemos que detectar si ha seleccionado el nodo local
			// Si tiene un valor con -1 es que ha solicitado el local					
			boolean bLocalSeleccionado = false;
			for (int i = 0; i < idComunidades.length; i++) {
				if (logger.isDebugEnabled())
				{
					logger.debug("Busqueda obtieneResultados Comunidad :"  + idComunidades[i]);
				}
				if (idComunidades[i].equals("-1"))
					bLocalSeleccionado = true;
			}			
			
			NodoVO[] comunidad = null;

			int tamComunidades = 0;				

			
			//Este if se hace para que si se llama al servicio de busqueda desde fuera y se desean obtener
			//los resultados de una busqueda en todas las comunidades baste con indicar "todas"
			if (idComunidades[0].equalsIgnoreCase("todas")) {
				//Obtenemos todas las comunidades y marcamos la busqueda en el nodo local
				bLocalSeleccionado = true;
   	 			comunidad = this.getSrvNodoService().listarNodos();
   	 			
   				tamComunidades = comunidad.length +1;				   				   				

			} else {
				//Se buscara solo en las comunidades seleccionadas
   	 			comunidad=obtenerComunidades(idComunidades).toArray(new NodoVO[]{});

   				//			CRM:RESERVAMOS UN ESPACIO EXTRA PARA LOS RESULTADOS DE LA BUSQUEDA LOCAL
   				// 26062012 Solo reservamos espacio si realmente ha seleccionado el local
   	 			tamComunidades = idComunidades.length;				
   				if (bLocalSeleccionado)
   					tamComunidades++;

			}
		
				
			DocumentosVO30[] resultadosParciales = new DocumentosVO30[tamComunidades];

			for (int i = 0; i < comunidad.length; i++) {
				try {
					//				 CRM: INTENTA BUSCAR EL IDENTIFICADOR DEL NODO LOCAL EN LA LISTA DE NODOS EXTERNOS, ESTO ES ERRONEO PUESTO QUE NO SE VA A ENCONTRAR NUNCA.
					//				SE SOLUCIONA MAS ABAJO, LLAMANDO A LA BUSQUEDA LOCAL, SIEMPRE.
					//				if(comunidad[i].getIdNodo().equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID))) { 
					////				if (parametros.getComunidadPeticion().equals(LdapUserDetailsUtils.getHost())){
					//					 resultadosParciales[i]=(handleBusquedaAvanzadaLocal(parametros));
					//					 logger.debug("CRM: En obtieneResultados. Haciendo busqueda local con ["+resultadosParciales[i].getNumeroResultados()+"]hits porque he encontrado el indice local["+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID)+"] en la lista de remotos");
					//				} else {
					if (logger.isDebugEnabled())
						logger.debug("Va a procesar la comunidad : " + i + " con id :" +comunidad[i].getIdNodo());
																
						resultadosParciales[i] = handleBusquedaAvanzadaIndiceRemoto(parametros,
							comunidad[i].getIdNodo());
						DocVO30[] res = resultadosParciales[i].getResultados();
						for (int j = 0; j < res.length; j++) {
							res[j].setNodo(comunidad[i].getUrl());
						}
						resultadosParciales[i].setResultados(res);
					
					//				}
				} catch (Exception e) {
					logger.warn("Error al consultar indices locales del nodo <"+comunidad[i].getIdNodo()+"> - "+e.getCause());
					logger.debug("",e);
					resultadosParciales[i]=null;
				}
			}
			

			//			CRM: REALIZAMOS LA BUSQUEDA LOCAL, LO QUE IMPLICA QUE CUALQUIER BUSQUEDA FEDERADA CON MAS DE UN NODO IMPEPINABLEMENTE INCLUYE LOS RESULTADOS LOCALES
			//			Y ESTAMOS SEGUROS DE QUE ESTO ES FUNCIONALMENTE CORRECTO.

			// resultadosParciales[idComunidades.length] = this.handleBusquedaAvanzadaLocal(parametros);
			// 26062012 Si se ha seleccionado el nodo local realizamos la búsqueda en local.			
			if (bLocalSeleccionado) 
			{
				if (logger.isDebugEnabled())
					logger.debug("BUSQUEDA obtieneResultados. Incluye nodo local");
				resultadosParciales[idComunidades.length] = this.handleBusquedaAvanzadaLocal(parametros);
			}

			resultados=combinaResultados(resultadosParciales);

		} else {
			resultados = this.handleBusquedaAvanzadaLocal(parametros);

			if(resultados!=null && resultados.getNumeroResultados() > 0){
				String[] sugerencias = resultados.getSugerencias();
				TaxonVO30[] tesauros = resultados.getTesauros();
				//TODO Revisar cómo se ordena, puede que meta latencia
				//Saco a zona común
				//	        		resultados = ordenarResultados(Arrays.asList(resultados));

				resultados.setTesauros(tesauros);
				resultados.setSugerencias(sugerencias);
			} 
		}
		return resultados;
	}
    
	
    /**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleBusquedaAvanzadaLocal(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30)
     * @param parametros ParametrosBusquedaAvanzadaVO30 Este VO alberga los parametros que configuran la busqueda avanzada
     * @return DocumentosVO30 Representa los resultados de la busqueda avanzada en el nodo local
     */
	protected DocumentosVO30 handleBusquedaAvanzadaLocal(ParametrosBusquedaAvanzadaVO30 parametros) throws Exception {
		return handleBusquedaAvanzadaIndiceRemoto(parametros, "");
	}
	
	
	/**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleBusquedaFederadaLocal(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30)
     * @param  parametros ParametrosBusquedaAvanzadaVO30 Este VO alberga los parametros que configuran la busqueda avanzada
     * @return DocumentosVO30 Representa los resultados de la busqueda avanzada en todos los nodos
     */
	//TODO BORRAR?, no se usa
	protected DocumentosVO30 handleBusquedaFederadaLocal(ParametrosBusquedaAvanzadaVO30 parametros) throws Exception
	{

		List <DocumentosVO30>documentos= new ArrayList<DocumentosVO30>();
		List <String>sugerencias=new ArrayList<String>();
		HashMap <String,TaxonVO30> tesauros = new HashMap<String,TaxonVO30>();
		boolean existenResultados=false;
		List<NodoVO> comunidades=obtenerComunidades(parametros.getComunidadesSeleccionadas());

		NodoVO nodoComunidadPeticion=new NodoVO();
		nodoComunidadPeticion.setNodo("localhost");
		nodoComunidadPeticion.setUrlWS(jbossHost);
		nodoComunidadPeticion.setUrl(jbossHost);
		nodoComunidadPeticion.setPuerto(jbossPort+jbossSubdominio);
		if (comunidades.size()>0) {
			//Pone como primera comunidad la de que parte la petición
			comunidades.add(comunidades.get(0));
		}
		comunidades.set(0, nodoComunidadPeticion);
		if(parametros.getNumeroResultados()!=null && parametros.getNumeroResultados()!=-1)parametros.setNumeroResultados(parametros.getNumeroResultados()/(comunidades.size()<5?5:comunidades.size()));
		DocumentosVO30[] documents=null;
		ArrayList<DocumentosVO30> docs = new ArrayList<DocumentosVO30>();
		for (Iterator<NodoVO> iterator = comunidades.iterator(); iterator.hasNext();) {
			NodoVO nodoVO = iterator.next();
			//índice local se coge por método local
			DocumentosVO30 docsNodo;
			if (nodoVO.getIdNodo()!=null||!nodoVO.getIdNodo().equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID))) {
				docsNodo = getSrvBuscarFederadaService()
						.busquedaFederada30IndiceLocal(parametros,
								nodoVO.getIdNodo());
			} else {
				docsNodo = handleBusquedaAvanzadaLocal(parametros);
			}
			if (docsNodo!=null) {
				docs.add(docsNodo);
			}
		}
		documents=docs.toArray(new DocumentosVO30[]{});

		if (documents!=null) {
			//		DocumentosVO30[] documents = MultiSearcher.searchDocuments(parametros, comunidades,"http://"+SrvBuscarServiceImpl.jbossHost+":"+SrvBuscarServiceImpl.jbossPort+SrvBuscarServiceImpl.jbossSubdominio+srvFederada,maxWaitThread,maxWaitPool,srvFederada);
			for (int i = 0; i < documents.length; i++) {
				if (documents[i] != null && documents[i].getResultados() != null
						&& documents[i].getResultados().length > 0) {
					
					existenResultados = true;
					documentos.add(documents[i]);
				}
				if (documents[i] != null && documents[i].getSugerencias() != null
						&& documents[i].getSugerencias().length > 0) {
					
					for (int j = 0; j < documents[i].getSugerencias().length
					&& maxSugerencias >= 0; j++) {
						if (!sugerencias.contains(documents[i].getSugerencias()[j])) {
							sugerencias.add(documents[i].getSugerencias()[j]);
						}
					}
				}
				if (documents[i] != null && documents[i].getTesauros() != null
						&& documents[i].getTesauros().length > 0) {
					
					for (int j = 0; j < documents[i].getTesauros().length
					&& maxTesauros >= 0; j++) {
						if (!tesauros.containsKey(documents[i].getTesauros()[j]
						                                                     .getNombre())) {
							tesauros.put(documents[i].getTesauros()[j].getNombre(),
									documents[i].getTesauros()[j]);
						}
					}
				}
			}
		}

		DocumentosVO30 documentosSugerenciasTesauros= new DocumentosVO30();
		if((documentos!=null) && (documentos.size()>0) && existenResultados){       	
			
			DocumentosVO30 resultadosOrdenados= ordenarResultados(documentos);
		        	
			documentosSugerenciasTesauros.setNumDocumentosIndice(resultadosOrdenados.getNumDocumentosIndice());
			documentosSugerenciasTesauros.setNumeroResultados(resultadosOrdenados.getNumeroResultados());
			documentosSugerenciasTesauros.setResultados(resultadosOrdenados.getResultados());
			documentosSugerenciasTesauros.setTotalResultados(resultadosOrdenados.getTotalResultados());
		}
		if(sugerencias!=null && sugerencias.size()>0){
			Collections.sort(sugerencias);
			documentosSugerenciasTesauros.setSugerencias(sugerencias.toArray(new String[0]));
		}else documentosSugerenciasTesauros.setSugerencias(new String[]{});
		if(tesauros!=null && tesauros.size()>0){
			List <TaxonVO30>tesaurosOrdenados = new ArrayList<TaxonVO30>(tesauros.values());
			Collections.sort(tesaurosOrdenados,new TaxonNombreComparator());
			documentosSugerenciasTesauros.setTesauros(tesaurosOrdenados.toArray(new TaxonVO30[0]));
		}else documentosSugerenciasTesauros.setTesauros(new es.pode.indexador.negocio.servicios.busqueda.TaxonVO30[]{});
		return documentosSugerenciasTesauros;
	}

	/**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleBuscarSQI()
     * @param consulta Parametros de busqueda SQI
     * @return ResultadoBusquedaVO Numero de documentos resultantes de la busqueda por SQI
     */
	protected ResultadoBusquedaSQIVO handleBuscarSQI(ParamBusquedaSQIVO consulta) throws Exception {
//		logger.debug("Buscando en cache con idBusqueda <"+consulta.getPalabrasClave()+">");
//    	Busco en la cache si esta busqueda ya se realizo. Si tengo exito, la devuelvo, si no realizamos la busqueda.   	
    	Element elemento = cacheConfig.getHitsSQI().get(consulta.getPalabrasClave());
    	ResultadoBusquedaSQIVO resultadoCache = null; //este obj alberga los resultados en la cache
//    	Se ha producido un fallo de cache, tengo que ir a buscar los elementos nuevos
    	if (elemento == null){
			List <LomEsVO>documentos= new ArrayList<LomEsVO>();
			boolean existenResultados=false;
	//		Obtenemos los nodos SQI
			NodoSQIVO[] nodosSQI=this.getSrvGestionSqiService().obtenerNodosSQI();
	//		Realizamos la búsqueda SQI invocando al gestor de hilos con todos los parametros.
	//		TODO: revisar la recoleccion y el ordenamiento de los resultados devueltos.
			//Se agrega el parametro AgregaProperties.IMAGENES_NODOS_SQI_URL al metodo MultiSearcherSQI para evitar usar el SrvPropiedadService en su clase
			String imgNodosSqiUrl = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_NODOS_SQI_URL);
			String maxWaitThread = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_THREAD_SQI);
			String maxWaitPool = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_POOL_SQI);
			LomEsVO[] documents = MultiSearcherSQI.searchDocuments(consulta, Arrays.asList(nodosSQI),Integer.parseInt(maxWaitThread),Integer.parseInt(maxWaitPool),imgNodosSqiUrl);

			for(int i = 0; i < documents.length ; i++ ){
				if(documents[i]!=null && documents[i].getTitulo()!=null && documents[i].getDescripcion()!=null){
					existenResultados = true;
					documentos.add(documents[i]);
				}
			}		

		  	if((documentos!=null) && (documentos.size()>0) && existenResultados){
	//	  		Habrá que ordenar los resultados de los difetentes nodos SQI.
		  		resultadoCache= ordenarResultadosSQI(documentos);
		  		cacheConfig.getHitsSQI().put(new Element(consulta.getPalabrasClave(), resultadoCache));
		  	}else{
		  		return new ResultadoBusquedaSQIVO(new LomEsVO[0],0);
		  	}
    	}else{
    		logger.debug("Hit de cache con idBusqueda <"+consulta.getPalabrasClave()+">");
    		resultadoCache = (ResultadoBusquedaSQIVO)elemento.getObjectValue();
    	}
    	// Resolvemos la paginacion. Devolvemos los elementos de la pagina.
    	int pagina = consulta.getOrigenPagina()==null || consulta.getOrigenPagina().intValue()== 0?ORIGEN_PAGINA_DEFAULT:consulta.getOrigenPagina().intValue();
		int resPorPagina = consulta.getResultadoPorPagina()==null || consulta.getOrigenPagina().intValue()== 0?RESULTADOS_PAGINA_DEFAULT:consulta.getResultadoPorPagina().intValue();
//    	Devolvemos los resultados de la pagina
    	return generaPaginaResultadosBusquedaSQI(resultadoCache, pagina, resPorPagina);
	}

	/**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleSolicitarDocsCountArbolCurricular(ParametrosDocsCountVO)
     * @param parametros ParametrosDocsCountVO30 Este VO alberga los parametros necesarios para realizar el calculo del numero de documentos resultantes en la busqueda por arbol curricular
     * @return ResultadosNodoCountVO Numero de documentos resultantes de la busqueda por arbol curricular
     */
	
	protected ResultadosNodoCountVO handleSolicitarDocsCount(ParametrosDocsCountVO30 parametros) throws Exception {
//  	 Con los valores de la busqueda genero el id de cache que tendre y miro a ver si existe ya en la cache.
    	String idCache = parametros.getIdiomaBusqueda() + SEPARADOR_PARAM_IDENTIFICADOR + 
    					 parametros.getIdiomaNavegacion() + SEPARADOR_PARAM_IDENTIFICADOR +
    					 parametros.getTipoBusqueda() + SEPARADOR_PARAM_IDENTIFICADOR +
    					 array2String(parametros.getTaxonomia());
    	
//    	logger.debug("CRM: buscando docs count con parametros =>"+this.print(parametros));
    	
//    	Busco en la cache si esta busqueda ya se realizo. Si tengo exito, la devuelvo, si no realizamos la busqueda.
    	Element elemento = cacheConfig.getHitsLength().get(idCache);
    	ResultadosNodoCountVO resultado = null; //este obj alberga los resultados en la cache
//    	Se ha producido un fallo de cache, tengo que ir a buscar los elementos nuevos
    	
    	if (elemento == null) {
//    		if (logger.isDebugEnabled())logger.debug("Fallo de cache con idBusqueda["+idCache+"]. Se procede a buscar.");
////    		Realizamos la busqueda local siempre 
//    		resultado = this.handleSolicitudDocsCountLocal(parametros);
////    		logger.debug("CRM: despues de busqueda docs count local. Resultados => "+ print(resultado));
//    		//y la remota según el caso
//    		if (parametros.getTipoBusqueda()!=null && (parametros.getTipoBusqueda().equals("05") || parametros.getTipoBusqueda().equals("07"))){
//        		NodoVO[] comunidad=getSrvNodoService().listarNodos();
//        		logger.debug("CRM: hacemos busqueda docs count federada por ["+comunidad.length+"]nodos");
//        		for (int i = 0; i < comunidad.length; i++) {
//        			ResultadosNodoCountVO temp = handleSolicitudDocsCountIndiceRemoto(parametros, comunidad[i].getIdNodo());
////        			logger.debug("CRM: despues de busqueda docs count federada nodo["+comunidad[i].getIdNodo()+"]. Resultados => "+ print(temp));
//        			if(resultado!=null){
//        				resultado.setNumeroResultados(resultado.getNumeroResultados()+temp.getNumeroResultados());
//        				Integer[] conteo=resultado.getConteo();
//        				Integer[] conteoTemp=temp.getConteo();
//        				for (int j = 0; j < conteo.length; j++) {
//							conteo[j]+=conteoTemp[j];
//						}
//        				resultado.setConteo(conteo);
//        			} else {
//        				resultado=temp;
//        			}
//				}
//    		}
////        	Si la busqueda no da resultados no cacheo la busqueda. Devuelvo las sugerencias si las hay
//    		if (logger.isDebugEnabled())logger.debug("Cacheamos resultados obtenidos con idBusqueda["+idCache+"]");
//    		
//        	cacheConfig.getHitsLength().put(new Element(idCache, resultado));
//        	if (logger.isDebugEnabled())logger.debug("Cacheados [1] documentos con idBusqueda["+idCache+"]");
    		
    		try {
    			logger.debug("Fallo de cache con idBusqueda <"+idCache+">. Se procede a buscar.");
	//    		Realizamos la busqueda local siempre
	    		String[] idNodo=null;
	    		if (parametros.getTipoBusqueda()!=null && (parametros.getTipoBusqueda().equals("05") || parametros.getTipoBusqueda().equals("07"))){
	    			NodoVO[] comunidad=getSrvNodoService().listarNodos();
	    			idNodo=new String[comunidad.length];
	    			for (int i = 0; i < comunidad.length; i++) {
						idNodo[i] = comunidad[i].getIdNodo();
					}
	    		}
	    		ParamDocsCountVO paramBusq = new ParamDocsCountVO(parametros.getTaxonomia(),parametros.getIdiomaBusqueda());
	    		ResultadosCountVO resultadoTemp = getSrvBuscadorService().solicitudDocsCountNodos(paramBusq, idNodo);
	    		    		
	    		resultado = new ResultadosNodoCountVO(resultadoTemp.getDocumentosCount(),resultadoTemp.getConteo());
	  		
	    		//Si la busqueda no da resultados no cacheo la busqueda. Devuelvo las sugerencias si las hay
	    		logger.debug("Cacheamos resultados obtenidos con idBusqueda["+idCache+"]");
	    		
	        	cacheConfig.getHitsLength().put(new Element(idCache, resultado));
	        	logger.debug("Cacheados un (1) documento con idBusqueda <"+idCache+">");
	        	
    		} catch (Exception e) {
    			logger.error(e);
    			return new ResultadosNodoCountVO();
    		}
    		
    	} else {
    		//se han encontrado elementos en la cache
    		logger.debug("Hit de cache con idBusqueda <"+idCache+">");
    		resultado = (ResultadosNodoCountVO)elemento.getObjectValue();
    	}
    	return resultado;
	}

	/**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleSolicitudDocsCountArbolLocal(ParametrosDocsCountVO30)
     * @param parametros ParametrosDocsCountVO30 Este VO alberga los parametros necesarios para realizar el calculo del numero de documentos resultantes en la busqueda local por arbol curricular
     * @return ResultadosNodoCountVO Numero de documentos resultantes de la busqueda por arbol curricular en local
     */
	
	protected ResultadosNodoCountVO handleSolicitudDocsCountLocal(ParametrosDocsCountVO30 parametros) throws Exception {
		try{
			if (logger.isDebugEnabled())logger.debug("SrvBuscarServiceImpl - handleSolicitudDocsCountLocal: Inicio.");
			ParamDocsCountVO paramBusq = new ParamDocsCountVO(parametros.getTaxonomia(),parametros.getIdiomaBusqueda());
			ResultadosCountVO resultado = this.getSrvBuscadorService().solicitudDocsCount(paramBusq);
			return new ResultadosNodoCountVO(resultado.getDocumentosCount(),resultado.getConteo());
		} 
		catch (Exception e) 
		{
			Exception exc = new Exception("SrvBuscarServiceImpl - handleSolicitudDocsCountLocal ERROR: invocacion al servicio solicitudDocsCountLocal de Indexados/Buscador con idiomaNavegacion["+parametros.getIdiomaNavegacion()+"], idNodo["+Arrays.toString(parametros.getTaxonomia())+"]", e);
			logger.error(exc);
			throw exc;
		}
	}
	
	/**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleSolicitudDocsCountArbolFederada(ParametrosDocsCountVO30)
     * @param parametros ParametrosDocsCountVO30 Este VO alberga los parametros necesarios para realizar el calculo del numero de documentos resultantes en la busqueda en todos los nodos por arbol curricular
     * @return ResultadosNodoCountVO Numero de documentos resultantes de la busqueda por arbol curricular en todos los nodos
     */
	protected ResultadosNodoCountVO handleSolicitudDocsCountFederada(ParametrosDocsCountVO30 parametros) throws Exception {

		NodoVO[] nodosDB=this.getSrvNodoService().listarNodos();
		NodoVO[] nodos=new NodoVO[nodosDB.length+1];
		
		NodoVO nodoLocal=new NodoVO();
		nodoLocal.setNodo("localhost");
		nodoLocal.setUrlWS(jbossHost);
		nodoLocal.setUrl(jbossHost);
		nodoLocal.setPuerto(jbossPort+jbossSubdominio);
		nodos[0]=nodoLocal;
		for (int i=1; i<nodos.length; i++){
			nodos[i]=nodosDB[i-1];
		}
		int numeroResultados=0;
		//TODO Ahora que no apunta al ESB puede hacerse mucho más fácil, esto es sólo para que compile
		ResultadosNodoCountVO[] documents = MultiSearcher.searchDocsCountArbolCurricularFederada(parametros, nodos, "http://"+SrvBuscarServiceImpl.jbossHost+":"+SrvBuscarServiceImpl.jbossPort+SrvBuscarServiceImpl.jbossSubdominio+srvFederada,Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_THREAD)),Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.MAX_WAIT_POOL)),srvFederada);
		Integer[] conteo=new Integer[parametros.getTaxonomia().length];
		for (int i=0;i<documents.length;i++){
			try{
				//if no null de i
				if (documents[i]!=null){
				    numeroResultados = numeroResultados + documents[i].getNumeroResultados();
				    for (int j=0;j<documents[i].getConteo().length;j++){
				    	conteo[j]=(conteo[j]==null)?documents[i].getConteo()[j]:conteo[j]+documents[i].getConteo()[j];
				    }
				}
	  		}catch (Exception e){
				logger.warn("llamada al servicio:"+"http://"+ nodos[i].getUrl() +":"+ nodos[i].getPuerto() +srvFederada+"ha fallado - "+ e.getCause());
				logger.debug("", e);
			}
		}
		Integer sumaResultados= numeroResultados;
		ResultadosNodoCountVO totalResultados = new ResultadosNodoCountVO();
		totalResultados.setNumeroResultados(sumaResultados);
		totalResultados.setConteo(conteo);

		return totalResultados;
	}
	
    /**
     * @see es.pode.buscar.negocio.servicios.SrvBuscarService#generadorIdentificadorAvanzado(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30)
     * @param parametrosAvanzada es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30 Este VO alberga los parametros que configuran la busqueda avanzada
     * @return java.lang.String Identificaodor para la cache a partir de los parametros de una busqueda avanzada
     */
    
    protected java.lang.String handleGeneradorIdentificadorAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametrosAvanzada)
        throws java.lang.Exception
    {

    	String comunidades="";
    	if (parametrosAvanzada.getComunidadesSeleccionadas()!=null){
    		for (int i=0; i<parametrosAvanzada.getComunidadesSeleccionadas().length;i++){
    			comunidades += parametrosAvanzada.getComunidadesSeleccionadas()[i]!=null?parametrosAvanzada.getComunidadesSeleccionadas()[i]+(i<(parametrosAvanzada.getComunidadesSeleccionadas().length-1)?SEPARADOR_VALOR_IDENTIFICADOR:""):"";
    		}
    	}
        return 	""+
        (parametrosAvanzada.getAutor() != null?parametrosAvanzada.getAutor():"") + SEPARADOR_PARAM_IDENTIFICADOR +
         comunidades + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getContexto() != null?parametrosAvanzada.getContexto():"") + SEPARADOR_PARAM_IDENTIFICADOR + 
        (parametrosAvanzada.getDescripcion() != null?parametrosAvanzada.getDescripcion():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getIdentificador() != null?parametrosAvanzada.getIdentificador():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getEdad() != null?parametrosAvanzada.getEdad():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getFechaPublicacion() != null?parametrosAvanzada.getFechaPublicacion():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getFormato() != null?array2String(parametrosAvanzada.getFormato()):"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getKeyword() != null?parametrosAvanzada.getKeyword():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getIdiomaBusqueda() != null?parametrosAvanzada.getIdiomaBusqueda():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getNivelAgregacion() != null?array2String(parametrosAvanzada.getNivelAgregacion()):"") + SEPARADOR_PARAM_IDENTIFICADOR +
        // 15062012 EJFENTE Se incluye la licencia en el identificador de cache
        (parametrosAvanzada.getLicencias() != null?array2String(parametrosAvanzada.getLicencias()):"") + SEPARADOR_PARAM_IDENTIFICADOR +        
        (parametrosAvanzada.getIdiomaNavegacion() != null?parametrosAvanzada.getIdiomaNavegacion():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getTaxonomia() != null?array2String(parametrosAvanzada.getTaxonomia()):"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getPalabrasClave() != null?parametrosAvanzada.getPalabrasClave():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getProcesoCognitivo() != null?parametrosAvanzada.getProcesoCognitivo():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getRecurso() != null?parametrosAvanzada.getRecurso():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getSecuencia() != null?parametrosAvanzada.getSecuencia().toString():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getTitulo() != null?parametrosAvanzada.getTitulo():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getValoracion() != null?parametrosAvanzada.getValoracion().toString():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getAmbito() != null?parametrosAvanzada.getAmbito().toString():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getNumeroResultados()!= null?parametrosAvanzada.getNumeroResultados().toString():"") + SEPARADOR_PARAM_IDENTIFICADOR +
        (parametrosAvanzada.getBusquedaSimpleAvanzada() != null && (parametrosAvanzada.getBusquedaSimpleAvanzada().equals(BUSCARRSS) || parametrosAvanzada.getBusquedaSimpleAvanzada().equals(BUSCARRELACIONADOSAC))?parametrosAvanzada.getBusquedaSimpleAvanzada():"");
    }
    
    
    private java.lang.String generadorIdentificadorSQIAriadne(QuerySimpleVO parametrosSQI, String idioma)
    throws java.lang.Exception
    {

	    return 	""+
	    (parametrosSQI.getQuery() != null?parametrosSQI.getQuery()+SEPARADOR_PARAM_IDENTIFICADOR:"") + (idioma !=null? idioma:"");
	    
    }
    
    /**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarServiceBase#handleBuscarLomEs(es.pode.buscar.negocio.servicios.ParametrosBusquedaSQIVO)
	 * @param parametros ParametrosBusquedaSQIVO Este VO alberga los parametros que configuran la busqueda
	 * @return ResultadoBusquedaSQIVO Esta clase representa los resultados de una busqueda en el formato necesario para el módulo SQI
	 */
    
    protected ResultadoBusquedaSQIVO handleBuscarLomEs(ParametrosBusquedaSQIVO parametros) throws Exception {
    	try {
//    		if (logger.isDebugEnabled())logger.debug("SrvBuscarServiceImpl - handleBuscarLomEs: Inicio con los parametros vocabularioConsulta:["+parametros.getVocabularioConsulta()+"]," +
//   				"resultadosDevueltos:["+parametros.getResultadosDevueltos()+"], la query:"+parametros.getQuery()+"] y el maximo de resultados"+parametros.getMaxResultados());
    		QuerySimpleVO paramLom= new QuerySimpleVO();
    		
    		paramLom.setLenguajeQuery(parametros.getVocabularioConsulta());
    		paramLom.setNumResultados(parametros.getResultadosDevueltos());
    		paramLom.setQuery(parametros.getQuery());
    		String[] idiomas=I18n.getInstance().obtenerIdiomasBuscables();
    		if(parametros.getResultadosDevueltos().intValue()>0 && parametros.getResultadosDevueltos().intValue()>parametros.getMaxResultados().intValue()){
    			parametros.setResultadosDevueltos(parametros.getMaxResultados().intValue());//Si el numero de devueltos es mas grande lo cambiamos por los resultados maximos		
    		}//No debería entrar nunca, esto lo controlamos en el servicio
    			
    		ResultadoBusquedaSQIVO paramLom_ResultadosBusqSQIVO= new ResultadoBusquedaSQIVO();
    		boolean sobrePasar=false;
    		int contador=0;
    		ArrayList<LomEsVO> array=new ArrayList<LomEsVO>();
    		int maximo=0;
    		int grupos=0;
    		int max=0;
    		int resta=0;
    		LomEsVO[] loms=null;
    		if(parametros.getResultadosDevueltos().intValue()>=0){//Si hay resultados devueltos
    			logger.debug("SrvBuscarServiceImpl - handleBuscarLomEs Hay resultados devueltos: <"+parametros.getResultadosDevueltos().intValue()+">");
    			if(parametros.getStartResult()>0){//Empezamos a contar en una posicion que no es el 1
    				//if (logger.isDebugEnabled())logger.debug("Empezamos por:["+parametros.getStartResult().intValue()+"]");
    				maximo = parametros.getMaxResultados()+(parametros.getStartResult());// Si quiero empezar del 3 y 15 resultados, necesito hasta 18
    				//if (logger.isDebugEnabled())logger.debug("Maximos:["+maximo+"]");
    				grupos=parametros.getResultadosDevueltos()+(parametros.getStartResult());// Si quiero empezar del 3 y 15 resultados, necesito hasta 18
    				//if (logger.isDebugEnabled())logger.debug("Paginado:["+grupos+"]");
    			}else{
    				maximo=parametros.getMaxResultados();//Necesito 15
    				//if (logger.isDebugEnabled())logger.debug("Empezamos por, sin inicio:["+maximo+"]");
    				grupos=parametros.getResultadosDevueltos();//Necesito 15
    				//if (logger.isDebugEnabled())logger.debug("Paginado, sin inicio:["+grupos+"]");
    			}
    		}else{//Sin resultados devueltos
    			logger.debug("Sin resultados devueltos");
    			//TODO ¿? En el otro caso es 0??!!
    			if(parametros.getStartResult()>1){//Empezamos a contar en una posicion que no es el 1
    				//if (logger.isDebugEnabled())logger.debug("Empezamos por:["+parametros.getStartResult().intValue()+"]");
    				maximo = parametros.getMaxResultados()+(parametros.getStartResult());// Si quiero empezar del 3 y 15 resultados, necesito hasta 18
    				//if (logger.isDebugEnabled())logger.debug("Maximos:["+maximo+"]");
    			}else{
    				maximo=parametros.getMaxResultados();//Necesito 15
    				//if (logger.isDebugEnabled())logger.debug("Maximos, sin inicio:["+maximo+"]");
    			}
    		}
    		if(grupos>0){//Si hay grupos, nos quedamos con este número ( que será mas pequeño que el siguiente
    			max=grupos;
    			if (logger.isDebugEnabled())logger.debug("Hay grupos, nos quedamos con ese maximo: <"+max+">");
    		}else{//Sin grupos
    			max=maximo;
    			if (logger.isDebugEnabled())logger.debug("No hay grupo, maximo: <"+max+">");
    		}
    		resta=parametros.getMaxResultados()-(parametros.getStartResult());
    		if( resta>0){
	    		for(int i=0;i<idiomas.length && !sobrePasar ;i++){
	    			paramLom.setIdioma(idiomas[i]);
	    			logger.debug("llamada con el idioma: <"+idiomas[i]+">");
	    			
	    			String idCache = generadorIdentificadorSQIAriadne(paramLom, idiomas[i]);
	    			DocumentosLOM_ESVO doc =null;
	    			Element elemento = cacheConfig.getHitsSQIAriadne().get(idCache);
	    	    	if (elemento == null){
	    	    		logger.debug("No existe en la cache, se hace la busqueda");
	    	    		doc= this.getSrvBuscadorService().busquedaLOM_ESVSQL(paramLom);
	    	    		if(doc.getResultados()!=null) 
	    	    			logger.debug("Obtenemos un elemento de <"+ doc.getResultados().length+"> posiciones");
	    	    		if(doc.getResultados()!=null && doc.getResultados().length>0){
	    	    			cacheConfig.getHitsSQIAriadne().put(new Element(idCache, doc));
	    	    		}
	    	    	}else{
	    	    		logger.debug("Hit de cache con idBusqueda <"+paramLom.getQuery()+"> e idioma <"+ idiomas[i]+">");
	    	    		doc = (DocumentosLOM_ESVO)elemento.getObjectValue();
	    	    	}
	    			if (doc.getResultados()!=null && doc.getResultados().length >0)
	        		{
	    				if(contador>=max){//Si en esta vuelta nos pasamos
	    					
	    					logger.debug("el contador es mayor que el maximo: <"+max+">");
	    					contador=doc.getResultados().length;
	    					logger.debug("tenemos elementos <"+ contador+"> y ya salimos");
	    					sobrePasar=true;
	    					int n=0;
	    					for(int j=0;j<contador;j++){
		    					LomEsVO temp = new LomEsVO();
			    				temp.setLomES(doc.getResultados()[n].getDocumento());
			    				temp.setRanking(doc.getResultados()[n].getRanking());
			    				n++;
			    				array.add(temp);
	    					}
	    				}else{
	    					int n=0;
	    					
	    					logger.debug("Tenemos elementos desde <"+ contador +"> a <"+contador+doc.getResultados().length+">");
	    					for(int j=contador;j<contador+doc.getResultados().length;j++){//Si no nos pasamos
	    						
		    					LomEsVO temp = new LomEsVO();
			    				temp.setLomES(doc.getResultados()[n].getDocumento());
			    				temp.setRanking(doc.getResultados()[n].getRanking());
			    				n++;
			    				array.add(temp);
	    					}
	    					contador=contador+doc.getResultados().length;
	    					if (logger.isDebugEnabled())logger.debug("Tenemos elementos hasta <"+contador+">");
	    					if(contador>=max){//Si nos pasamos
	    						
	    						if (logger.isDebugEnabled())logger.debug("Nos pasamos: <"+max+">");
	    						sobrePasar=true;
	    						if (logger.isDebugEnabled())logger.debug("Tenemos elementos hasta <"+ contador+"> salimos");
	    					}
	    				}
	    				
	        		}
	    			
	    		}
	
	    		if(contador<=max){
	    			max=contador;//Para que si tenemos 5 elementos no intente cargar 95 en el array de VO
	    			logger.debug("El numero de pedido es mayor que el numero de resultados a devolver, <"+max+"> - <"+contador+">");
	    		}
	    		logger.debug("Generamos un array de <"+(max)+"> posiciones, el array mide <"+array.size()+"> y lo queremos a partir de <"+(parametros.getStartResult().intValue())+">");
	//    		int mr=parametros.getMaxResultados().intValue()-(parametros.getStartResult().intValue()-1);
	    		
				if(max-(parametros.getStartResult().intValue())>0 ){//Mas grandes que 25
					if(logger.isDebugEnabled()) logger.debug("max-(parametros.getStartResult().intValue()-1) es positivo, el array de 0 a <"+(max-(parametros.getStartResult().intValue()))+">");
	//				if(logger.isDebugEnabled()) logger.debug("Nos faltan para devolver  ["+(mr)+"] elementos, para llegar al maximo1");
					int v=0;
					loms=new LomEsVO[max-(parametros.getStartResult().intValue())];
					for(int g=(parametros.getStartResult().intValue());g<max ;g++){
						loms[v]=array.get(g);
						v++;
					}
				}else{
					logger.debug("max-(parametros.getStartResult().intValue()-1) es negativo o 0, <"+(max-(parametros.getStartResult().intValue()))+">, el array de max");
					loms=new LomEsVO[max];
					int v=0;
	//				if(logger.isDebugEnabled()) logger.debug("Nos faltan para devolver  ["+(mr)+"] elementos, para llegar al maximo2");
					if(array.size()>=max+(parametros.getStartResult().intValue()) ){
						for(int g=parametros.getStartResult().intValue();g<max+(parametros.getStartResult().intValue());g++){
							loms[v]=array.get(g);
							v++;
						}
					}
					else{
						loms=null;;
					}
				}
	//			if(logger.isDebugEnabled()) logger.debug("Nos faltan para devolver  ["+(mr)+"] elementos, para llegar al maximo3");
				if(loms!=null &&loms.length>0){
					if (logger.isDebugEnabled())logger.debug("Devolvemos loms de longitud <"+loms.length+">");
				}
    		}else{
//    			logger.debug("Hemos llegado al maximo");
    			loms=null;
    			max=parametros.getMaxResultados();
    		}
			paramLom_ResultadosBusqSQIVO.setNumeroResultados(max);
			paramLom_ResultadosBusqSQIVO.setResultadoBusqueda(loms);

	    	
	    	return paramLom_ResultadosBusqSQIVO;
	    } 
	    catch (Exception e) 
	    {
	    	Exception exc = new Exception("ERROR: invocacion al servicio de Indexador/Buscador ", e);
	    	logger.error(exc);
	    	throw exc;
	    }
	}
    
    /**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarService#solicitarMetadato(es.pode.buscar.negocio.servicios.ParametroMetadatoVO)
	 * @param  parametros es.pode.buscar.negocio.servicios.ParametroMetadatoVO Este VO alberga los parametros necesarios para consultar los metadatos de un ODE dado
	 * @return es.pode.buscar.negocio.servicios.MetadatoBasicoVO Esta clase representa la meta informacion de un ODE
	 */
    
    protected es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO handleSolicitarMetadato(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO parametros)
        throws java.lang.Exception
    {
    	try {
    		logger.debug("Buscando metadatos para MEC <"+parametros.getIdentificadorODE()+"> en el idioma <"+parametros.getIdioma()+">");
			DocVO30 documentoBusqueda = this.getSrvBuscadorService().busquedaMEC(parametros.getIdentificadorODE(), parametros.getIdioma());
			if (documentoBusqueda != null){
				if (logger.isDebugEnabled()){
					//logger.debug("Encontrados metadatos para MEC <"+parametros.getIdentificadorODE()+"> en el idioma <"+parametros.getIdioma()+"]");
					logger.debug(" MetadatoBasico= Licencias: <"+Arrays.toString(documentoBusqueda.getLicencias())+"> Titulo: <"+documentoBusqueda.getTitulo()+"> Descripcion: <"+documentoBusqueda.getDescripcion()+"> Formato: <"+Arrays.toString(documentoBusqueda.getFormato())+" Destinatarios-"+Arrays.toString(documentoBusqueda.getDestinatarios())+" Idioma-"+documentoBusqueda.getIdioma()+" Ambito-"+Arrays.toString(documentoBusqueda.getAmbito())+" Valoracion-"+documentoBusqueda.getValoracion()+" LocalizadorODE-"+documentoBusqueda.getLocalizadorODE()+" IdentificadorODE-"+documentoBusqueda.getIdentificadorODE()+" Imagen-"+documentoBusqueda.getImagen()+" NivelAgregacion-"+documentoBusqueda.getNivelAgregacion()+"> Tamanio: <"+documentoBusqueda.getTamanio()+"> conSinSecuencia: <"+documentoBusqueda.getConSinSecuencia()+">");
				}
				NodoVO[] nodos = this.getSrvNodoService().listarNodos();
								
				return this.mapearDocVO30ToMetadatoBasicoVO(documentoBusqueda, nodos); 
				
			}
			logger.warn("Error buscando metadatos para MEC <"+parametros.getIdentificadorODE()+"> en el idioma <"+parametros.getIdioma()+">. No hay resultados disponibles.");
			throw new Exception("SrvBuscarServiceImpl - handleSolicitarMetadato:Error buscando metadatos para MEC["+parametros.getIdentificadorODE()+"] en el idioma["+parametros.getIdioma()+"]. No hay resultados disponibles.");
		} catch (RuntimeException e) {
			logger.error(e);
			throw new Exception("SrvBuscarServiceImpl - handleSolicitarMetadato:Excepcion buscando metadatos para MEC["+parametros.getIdentificadorODE()+"] en el idioma["+parametros.getIdioma()+"].",e);
		}
    }
	
    private List<NodoVO> obtenerComunidades(String[] indicesComunidades) throws Exception{
		List<NodoVO> comunidades=new ArrayList<NodoVO>();
   	 	for(int i=0; i<indicesComunidades.length;i++){
   	 		if(!indicesComunidades[i].equals("0") && !indicesComunidades[i].equals("-1")){
		   	 	NodoVO nodo = this.getSrvNodoService().obtenerNodo(Long.parseLong(indicesComunidades[i]));
		   	    if (nodo!=null) {
					String puerto = "";
					if (nodo.getPuerto() != null)
						puerto = nodo.getPuerto();
					nodo.setPuerto(puerto);
					comunidades.add(nodo);
				} else {
					logger.info("No existe el nodo con identificador <"+indicesComunidades[i]+">");
				}
   	 		}
   	 	}
		return comunidades;
	}
    
	private HitCacheVO creaHitCache(String idBusqueda, String palabrasClave, DocumentosVO30 resultados) throws java.lang.Exception {
		Date ahora = new Date();
		TaxonVO30[] tesauros=null;
		if (resultados.getTesauros()!= null){
			tesauros=resultados.getTesauros();
		}else tesauros=new TaxonVO30[0];
			HitCacheVO hitCache = new HitCacheVO(1L,
				palabrasClave,
				resultados.getNumeroResultados(),
				"",
				new Timestamp(ahora.getTime() + Long.parseLong(segundosCaducidadHit)),
				new Timestamp(ahora.getTime()),
//				DateManager.timestampToCalendar(new Timestamp(ahora.getTime() + Long.parseLong(props.getProperty("segundosCaducidadHit")))),
//				DateManager.timestampToCalendar(new Timestamp(ahora.getTime())),
				"",
				idBusqueda,mapHitDocVO302ValoresBusquedaVO(resultados.getResultados()),
				resultados.getTotalResultados(),
				resultados.getSugerencias(),
				mapHitTaxonVO2ResultadosTaxonVO(tesauros)
				);
		return hitCache;
	}
	
	private ResultadoBusquedaSQIVO ordenarResultadosSQI(List<LomEsVO> documentos)
	{
//		TODO: implementar un algoritmo de ordenado. Esta por decidir que algoritmo vamos a utilizar.
		return new ResultadoBusquedaSQIVO(documentos.toArray(new LomEsVO[0]),documentos.size());
	}
	
	private DocumentosVO30 ordenarResultados(List<DocumentosVO30> documentos) throws NumberFormatException, IllegalArgumentException {
		
        int totalResultados = 0, numeroResultados = 0, numDocumentosIndice=0;
        HashMap <String,DocVO30> eliminarRepetidos = new HashMap<String,DocVO30>();
        
//        CRM: RECORREMOS LOS NODOS
        for(int i=0; i<documentos.size(); i++){
        	DocumentosVO30 doc=documentos.get(i);
        	int resultadosRepetidos=0;
        	//if(documentos.size()==1 || (doc.getResultados()[0].getNodo()==null && resultadoMayor.getResultados()[0].getNodo()==null) || (doc.getResultados()[0].getNodo()==null && resultadoMayor.getResultados()[0].getNodo()!=null) || (doc.getResultados()[0].getNodo()!=null && resultadoMayor.getResultados()[0].getNodo()==null) || !doc.getResultados()[0].getNodo().equals(resultadoMayor.getResultados()[0].getNodo())){
        	DocVO30[] resultados = (documentos.get(i)).getResultados();
//        	CRM: RECORREMOS LOS RESULTADOS DE CADA NODO
			for(int j=0; j<resultados.length; j++){
				if (resultados[j]!=null) {
//					CRM: NO ESTAMOS COMPROBANDO LOS REPETIDOS, POR LO QUE SI HAY REPES, LOS MOSTRAREMOS
					if(eliminarRepetidos.containsKey(resultados[j].getIdentificadorODE())){
						resultadosRepetidos++; //} // CRM: ESTE INCREMENTO DE RESULTADOS REPETIDOS, SI NO SE HACE, CUANDO MOSTRAMOS LOS RESULTADOS EN DESARROLLO EL CONTADOR NO SE COMPORTA BIEN (NI IDEA DE PORQUE)
						//TODO ¿Por qué? ¿Qué quiere decir que nodo sea null? -> probablemente que pertenece al nodo local;
						//esto serviria para mostrar preferentemente los ODEs locales ante casos en los que otros nodos tengan el mismo ODE
						if(resultados[j].getNodo()==null) eliminarRepetidos.put(resultados[j].getIdentificadorODE(),resultados[j]);
					}else eliminarRepetidos.put(resultados[j].getIdentificadorODE(),resultados[j]);
					
//					CRM: NO COMPROBAMOS LOS REPES, SEGUN RECORREMOS RESULTADOS, LOS INTRODUCIMOS EN LOS VALIDOS (EL NOMBRE DE LA VARIABLE NO ES MUY FELIZ, PARECE QUE HACEMOS LO CONTRARIO)
//					eliminarRepetidos.put(resultados[j].getIdentificadorODE(),resultados[j]);
				}
			}
			//TODO ¿Y no hay forma más sencilla de hacer esto?
        	totalResultados = totalResultados + doc.getTotalResultados()-resultadosRepetidos;
			numeroResultados = numeroResultados + doc.getNumeroResultados()-resultadosRepetidos;
			numDocumentosIndice=numDocumentosIndice+doc.getNumDocumentosIndice();
        }
        List <DocVO30>documentosOrdenados = new ArrayList<DocVO30>(eliminarRepetidos.values());
        Collections.sort(documentosOrdenados,new DocRankingComparator());
	    DocumentosVO30 documento= new DocumentosVO30();
	    documento.setResultados(documentosOrdenados.toArray(new DocVO30[0]));
	    documento.setNumeroResultados(numeroResultados);
	    documento.setNumDocumentosIndice(numDocumentosIndice);
	    documento.setTotalResultados(totalResultados);

	    return documento; 
	}

	
	private ResultadoBusquedaVO generaPaginaResultadosRSS(ParametrosBusquedaAvanzadaVO30 parametros,String idCache, HitCacheVO resultadoCache, ValoresBusquedaVO[] temp) {
    	List <ValoresBusquedaVO>documentosOrdenados = Arrays.asList(temp);
        Collections.sort(documentosOrdenados,new DocFechaPublicacionComparator());
        int resultadosPorPagina = parametros.getResultadosPorPagina()==null || parametros.getResultadosPorPagina().intValue() ==0?RESULTADOS_PAGINA_DEFAULT:parametros.getResultadosPorPagina();
    		ValoresBusquedaVO valoresBusquedaReturn[] = new ValoresBusquedaVO[(resultadosPorPagina>documentosOrdenados.size())?documentosOrdenados.size():resultadosPorPagina];

    		for (int i = 0; i < valoresBusquedaReturn.length; i++) {
    			//    		logger.debug("Empaquetando resulado de cache["+(i+desde)+"] como posicion en la pagina ["+i+"] con contenido?["+(temp[i+desde]!=null)+"].");
    			valoresBusquedaReturn[i] = temp[i];
    			valoresBusquedaReturn[i].setUrlImagen(valoresBusquedaReturn[i].getUrlImagen());
    			//valoresBusquedaReturn[i].setUrlImagen(valoresBusquedaReturn[i].getUrlImagen());
    		}
    		if (logger.isDebugEnabled())logger.debug("SrvBuscarServiceImpl - generaPaginaResultadosBusquedaRSS: Devolvemos ["+valoresBusquedaReturn.length+"] resultados correspondientes a la búsqueda RSS con idBusqueda["+idCache+"]");
    		return new ResultadoBusquedaVO(valoresBusquedaReturn,
    				resultadoCache.getSugerencias(), 
    				new Integer(String.valueOf(resultadoCache.getNumeroResultados())), 
    				resultadoCache.getIdCache(),
    				new Integer(String.valueOf(resultadoCache.getTotalResultados())),
    				resultadoCache.getTesauros()
    				);
    }
	
	private ResultadoBusquedaVO generaPaginaResultadosBusqueda(ParametrosBusquedaAvanzadaVO30 parametros, String idCache, HitCacheVO resultadoCache, ValoresBusquedaVO[] temp) {		
		int pagina = parametros.getOrigenPagina()==null || parametros.getOrigenPagina() == 0?ORIGEN_PAGINA_DEFAULT:parametros.getOrigenPagina();
		int resultadosPorPagina = parametros.getResultadosPorPagina()==null || parametros.getResultadosPorPagina() == 0?RESULTADOS_PAGINA_DEFAULT:((parametros.getResultadosPorPagina().intValue()==-1)?resultadoCache.getNumeroResultados():parametros.getResultadosPorPagina());
		if (logger.isDebugEnabled())logger.debug("Paginamos <"+resultadoCache.getResultadoBusquedaWeb().length+"> resultados de cache sobre la pagina <"+pagina+"> con idBusqueda <"+idCache+"> con <"+resultadosPorPagina+"> resultados por pagina.");
		int desde;
		int hasta;
		//    	Tenemos que tener cuidaddo con la siguiente situacion: un usuario busca X y quiere la pagina n. Pasado el rato
		//    	(en el que caduca su busqueda) el usuario pulsa la pagina n+1, eso dispara una nueva busqueda, y puede que se
		//    	hayan modificado el numero de resultados posibles con lo que puede que la pagina que se requiere ya no sea
		//    	disponible, con lo que hay que tener cuidado con los extremos.
		int paginasTotal = temp.length / resultadosPorPagina;
		if ((temp.length % resultadosPorPagina) > 0) // Hay resto de dividir entre los resultados por pagina, hay una pagina no completa mas
			paginasTotal++;
		if (pagina > paginasTotal) // Si me piden una pagina que se sale del rango maximo, le doy la ultima pagina
			pagina = paginasTotal;
		else if (pagina < 1) // Si me piden una pagina ilegal, muestro la primera
			pagina = 1;

		if (pagina == 1) {  // me piden la primera pagina
			desde = 0;
			hasta = (resultadosPorPagina > temp.length) ? temp.length - 1
					: resultadosPorPagina - 1;
		} else if (pagina == paginasTotal) { // me piden la ultima pagina
			hasta = temp.length - 1;
			desde = (resultadosPorPagina * (pagina - 1));
		} else { // me piden una pagina intermedia
			desde = resultadosPorPagina * (pagina - 1);
			hasta = resultadosPorPagina * pagina - 1;
		}
		//    	logger.debug("Devolvemos ["+(hasta-desde+1)+"] valores de cache sobre la pagina ["+pagina+"] con idBusqueda["+idCache+"] desde el hit ["+desde+"] hasta el hit["+hasta+"].");
		ValoresBusquedaVO valoresBusquedaReturn[] = new ValoresBusquedaVO[hasta- desde + 1];

		for (int i = 0; i < valoresBusquedaReturn.length; i++) {
			//    		logger.debug("Empaquetando resulado de cache["+(i+desde)+"] como posicion en la pagina ["+i+"] con contenido?["+(temp[i+desde]!=null)+"].");
			valoresBusquedaReturn[i] = temp[i+desde];
			valoresBusquedaReturn[i].setUrlImagen(valoresBusquedaReturn[i].getUrlImagen());
		}
		if (logger.isDebugEnabled())logger.debug("SrvBuscarServiceImpl - generaPaginaResultadosBusqueda: Devolvemos ["+(hasta-desde+1)+"] resultados correspondientes a la pagina ["+pagina+"] con idBusqueda["+idCache+"]");
		return new ResultadoBusquedaVO(valoresBusquedaReturn,
				resultadoCache.getSugerencias(), 
				new Integer(String.valueOf(resultadoCache.getNumeroResultados())), 
				resultadoCache.getIdCache(),
				new Integer(String.valueOf(resultadoCache.getTotalResultados())),
				resultadoCache.getTesauros()
				);
	}

	private ResultadoBusquedaSQIVO generaPaginaResultadosBusquedaSQI(ResultadoBusquedaSQIVO resultadoCache, int pagina, int resPorPagina) {		
		if (logger.isDebugEnabled())logger.debug("Paginamos <"+resultadoCache.getResultadoBusqueda().length+"> resultados de cache sobre la pagina <"+pagina+"> con <"+resPorPagina+"> resultados por pagina.");
		int desde;
		int hasta;
		//    	Tenemos que tener cuidaddo con la siguiente situacion: un usuario busca X y quiere la pagina n. Pasado el rato
		//    	(en el que caduca su busqueda) el usuario pulsa la pagina n+1, eso dispara una nueva busqueda, y puede que se
		//    	hayan modificado el numero de resultados posibles con lo que puede que la pagina que se requiere ya no sea
		//    	disponible, con lo que hay que tener cuidado con los extremos.
		int paginasTotal = resultadoCache.getResultadoBusqueda().length / resPorPagina;
		if ((resultadoCache.getResultadoBusqueda().length % resPorPagina) > 0) // Hay resto de dividir entre los resultados por pagina, hay una pagina no completa mas
			paginasTotal++;
		if (pagina > paginasTotal) // Si me piden una pagina que se sale del rango maximo, le doy la ultima pagina
			pagina = paginasTotal;
		else if (pagina < 1) // Si me piden una pagina ilegal, muestro la primera
			pagina = 1;

		if (pagina == 1) {  // me piden la primera pagina
			desde = 0;
			hasta = (resPorPagina > resultadoCache.getResultadoBusqueda().length) ? resultadoCache.getResultadoBusqueda().length - 1
					: resPorPagina - 1;
		} else if (pagina == paginasTotal) { // me piden la ultima pagina
			hasta = resultadoCache.getResultadoBusqueda().length - 1;
			desde = (resPorPagina * (pagina - 1));
		} else { // me piden una pagina intermedia
			desde = resPorPagina * (pagina - 1);
			hasta = resPorPagina * pagina - 1;
		}
		//    	logger.debug("Devolvemos ["+(hasta-desde+1)+"] valores de cache sobre la pagina ["+pagina+"] con idBusqueda["+idCache+"] desde el hit ["+desde+"] hasta el hit["+hasta+"].");
		LomEsVO[] valoresBusquedaReturn = new LomEsVO[hasta- desde + 1];

		for (int i = 0; i < valoresBusquedaReturn.length; i++) {
			//    		logger.debug("Empaquetando resulado de cache["+(i+desde)+"] como posicion en la pagina ["+i+"] con contenido?["+(temp[i+desde]!=null)+"].");
			valoresBusquedaReturn[i] = resultadoCache.getResultadoBusqueda()[i+desde];
		}
		if (logger.isDebugEnabled())logger.debug("Devolvemos <"+(hasta-desde+1)+"> resultados correspondientes a la pagina <"+pagina+">");
		return new ResultadoBusquedaSQIVO(valoresBusquedaReturn,
				resultadoCache.getResultadoBusqueda().length);
	}
	
	
	
	private ResultadosTaxonVO[] mapHitTaxonVO2ResultadosTaxonVO(TaxonVO30[] temp){
		ResultadosTaxonVO[] actual = new ResultadosTaxonVO[temp.length];
//		Mapeamos con Dozer
		for(int i=0; i < temp.length;i++){
			if(temp[i]!=null){
				ResultadosTaxonVO actualVO = new ResultadosTaxonVO();
				this.getBeanMapper().map(temp[i], actualVO);
				actualVO.setIdentificador(temp[i].getIdentificador());
				actualVO.setNombre(temp[i].getNombre());
				actual[i]=actualVO;
			}
		}
		return actual;
	}
	
	private ValoresBusquedaVO[] mapHitDocVO302ValoresBusquedaVO(DocVO30[] temp){
		ValoresBusquedaVO[] actual = new ValoresBusquedaVO[temp.length];
		new ArrayList<Object>();
//		Mapeamos con Dozer
		for(int i=0; i < temp.length;i++){
			ValoresBusquedaVO actualVO = new ValoresBusquedaVO();
			this.getBeanMapper().map(temp[i], actualVO);
			actualVO.setId(temp[i].getIdentificadorODE());
			actualVO.setUrlImagen(temp[i].getImagen());
			actualVO.setNodo(temp[i].getNodo());
			actualVO.setAreaCurricular(temp[i].getAreaCurricular());
			actualVO.setFormato(temp[i].getFormato());
			actualVO.setNivelAgregacion(temp[i].getNivelAgregacion());
			actualVO.setAmbito(temp[i].getAmbito());
			actualVO.setFechaPublicacion(temp[i].getFechaPublicacion());
			actualVO.setHoraPublicacion(temp[i].getHoraPublicacion());
			actualVO.setDescripcion(temp[i].getDescripcion());
			actualVO.setConSecuencia(temp[i].getConSinSecuencia());
			actual[i]=actualVO;
		}
		return actual;
	}

	private DocVO30[] mapValoresBusquedaVO2DocVO30(ValoresBusquedaVO[] temp){

		DocVO30[] actual = new DocVO30[temp.length];
//		Mapeamos con Dozer
		for(int i=0; i < temp.length;i++){
			DocVO30 actualVO = new DocVO30();
			this.getBeanMapper().map(temp[i], actualVO);
			actualVO.setIdentificadorODE(temp[i].getId());
			actualVO.setImagen(temp[i].getUrlImagen());
			actualVO.setNodo(temp[i].getNodo());
			actualVO.setAreaCurricular(temp[i].getAreaCurricular());
			actualVO.setFormato(temp[i].getFormato());
			actualVO.setNivelAgregacion(temp[i].getNivelAgregacion());
			actualVO.setAmbito(temp[i].getAmbito());
			actualVO.setFechaPublicacion(temp[i].getFechaPublicacion());
			actualVO.setHoraPublicacion(temp[i].getHoraPublicacion());
			actualVO.setDescripcion(temp[i].getDescripcion());
			actualVO.setConSinSecuencia(temp[i].getConSecuencia());
			actual[i]=actualVO;
		}
		return actual;
	}
	
	private String array2String(String[] array) {
		StringBuilder str = new StringBuilder();
		if (array != null && array.length > 0) for (int i = 0; i < array.length; i++)str.append(array[i] + (i<(array.length-1)?SEPARADOR_VALOR_IDENTIFICADOR:""));
		return str.toString();
	}

	/**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarService#buscarAvanzado(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30)
	 * @param parametros ParametrosBusquedaAvanzadaVO30 Parametros necesarios para obtener sugerencias de búsqueda.
	 * @return SugerenciasBusquedaVO[] Array con las sugerencias de búsqueda obtenidas y el número de objetos que contienen dicha sugerencias. 
	 */
	protected SugerenciasBusquedaVO[] handleSugerirBusqueda(ParametrosBusquedaAvanzadaVO30 parametros) throws Exception {
		long inicio=System.currentTimeMillis();	
		SugerenciasBusquedaVO[] sugBusqueda = new SugerenciasBusquedaVO[0];
//   	Con los valores de la busqueda genero el id de cache que tendre y miro a ver si existe ya en la cache.
    	String idCache = UtilesString.removeAccents(this.handleGeneradorIdentificadorAvanzado(parametros).toLowerCase());
    	if (logger.isDebugEnabled())logger.debug("Obteniendo sugerencias de búsqueda en cache con id <"+idCache+"> <"+ parametros+">");
//    	Busco en la cache si esta busqueda ya se realizo. Si tengo exito, la devuelvo, si no realizamos la busqueda.   	
    	Element elemento = cacheConfig.getHitsSugBusq().get(idCache);
    	
    	if (elemento == null){//Se ha producido un fallo de cache, tengo que ir a buscar los elementos nuevos
    		logger.debug("Fallo de cache con idBusqueda <"+idCache+">. Se procede a obtener sugerencias.");

//    		Obtenemos sugerencias de busqueda.
			ParamAvanzadoVO paramSug = new ParamAvanzadoVO();
			paramSug.setNumeroResultados(parametros.getNumeroResultados());
			paramSug.setPalabrasClave(parametros.getPalabrasClave());
			paramSug.setIdiomaBusqueda(parametros.getIdiomaBusqueda());
			paramSug.setIdiomaNavegacion(parametros.getIdiomaNavegacion());
			paramSug.setBusquedaSimpleAvanzada(parametros.getBusquedaSimpleAvanzada());
			
        	SugerenciasVO[] sugBusq = this.getSrvBuscadorService().obtenerSugerencias(paramSug);

//		   	La busqueda da resultados, cacheo la busqueda.
        	if(sugBusq!=null && sugBusq.length>0){
	    		try{
	            	sugBusqueda = new SugerenciasBusquedaVO[sugBusq.length];
	            	long inicioMapeo = System.currentTimeMillis();
	            	
	            	for(int i = 0; i < sugBusq.length;i++){
	            		sugBusqueda[i] = (SugerenciasBusquedaVO) this.getBeanMapper().map(sugBusq[i],SugerenciasBusquedaVO.class,"DEF_MAPPING_SUGERENCIASVO_SUGERENCIASBUSQUEDAVO");
	            	}
	            	long fin = System.currentTimeMillis();
	            	logger.debug("Tiempo de mapeo de SugerenciasVO a SugerenciasBusquedaVO <" + ((fin - inicioMapeo)/1000)+"> segundos <"+(fin-inicioMapeo)+"> milisegundos");
//	    			Metemos las sugerencias de busqueda en la cache asociandole un identificador.
	    			cacheConfig.getHitsSugBusq().put(new Element(idCache, sugBusqueda));
	    			if (logger.isDebugEnabled())logger.debug("Cacheadas <"+sugBusqueda.length+"> sugerencias de búsqueda con id <"+idCache+">");
	        		
	    		}catch (Exception e){
	    			Exception exc = new Exception("ERROR: generando hit de cache con id["+idCache+"], número de resultados obtenidos "+ sugBusq.length);
	    			logger.error(exc, e);
	    			throw exc;
	    		}
        	}
    	}else{
//    		Se han encontrado elementos en la cache
    		logger.debug("Hit de cache con id <"+idCache+">");
    		sugBusqueda = (SugerenciasBusquedaVO[])elemento.getObjectValue();
    	}
    	long fin=System.currentTimeMillis();
    	long tiempo=(fin-inicio)/1000;
//    	logger.debug("Sugerencias de busqueda <" + sugBusqueda.length +"> en <" +tiempo+"> segundos(inicio <"+inicio+">, fin<"+fin + ">)--->"+(fin-inicio));
    	
//    	Devolvemos los resultados 
		return sugBusqueda;
	}

	   /**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarService#solicitarMetadatosOdes(es.pode.buscar.negocio.servicios.ParametroMetadatoVO[])
	 * @param  parametros es.pode.buscar.negocio.servicios.ParametroMetadatoVO[] Este array VO alberga los parametros necesarios para consultar los metadatos de unos odes dados
	 * @return es.pode.buscar.negocio.servicios.MetadatoBasicoVO[] Array de esta clase que representa la meta informacion de un ODE
	 */
	protected MetadatoBasicoVO[] handleSolicitarMetadatosOdes(ParametroMetadatoVO[] parametros) throws Exception {

    	try {
    		MetadatoBasicoVO[] resultados =null;
    		NodoVO[] nodos = this.getSrvNodoService().listarNodos();
    		
    		ParamBuscarMecVO[] param = new ParamBuscarMecVO[parametros.length];
    		for(int i=0; i< parametros.length;i++){
    			ParamBuscarMecVO paramBuscarMec = new ParamBuscarMecVO();
    			paramBuscarMec.setIdentificadorMEC(parametros[i].getIdentificadorODE());
    			paramBuscarMec.setIdioma(parametros[i].getIdioma());
    			param[i]=paramBuscarMec;
    		}
    		
    		DocVO30[] documentos = this.getSrvBuscadorService().busquedaVariosMEC(param);
			if (documentos != null){

				resultados = new MetadatoBasicoVO[documentos.length];
				for(int i = 0; i< documentos.length;i++){
					resultados[i] = mapearDocVO30ToMetadatoBasicoVO(documentos[i],nodos);
				}
				return resultados;
			}
			logger.debug("Buscando metadatos para varios MEC. No hay resultados disponibles.");
			throw new Exception("SrvBuscarServiceImpl - handleSolicitarMetadatosOdes:Error buscando metadatos para varios MEC. No hay resultados disponibles.");
		} catch (RuntimeException e) {
			throw new Exception("Excepcion buscando metadatos para varios MEC.",e);
		}
    
	}
	
	
	private MetadatoBasicoVO mapearDocVO30ToMetadatoBasicoVO(DocVO30 documentoBusqueda,NodoVO[] nodos){
		String[] ambitosTraducidos = null;
		//TODO Creo que era en la primera posición donde iba el ámbito universal si es que va. Hay que comprobar
		if(documentoBusqueda.getAmbito()!=null && documentoBusqueda.getAmbito().length>0 && !documentoBusqueda.getAmbito()[0].equals(AMBITO_UNIVERSAL)){
			String[] ambitos = documentoBusqueda.getAmbito();
			ambitosTraducidos = new String[ambitos.length];
			
			if(nodos!=null){
				for(int j= 0; j<ambitos.length;j++){
					boolean encontrado=false;
					for(int i= 0; i<nodos.length;i++){
						if(nodos[i].getIdNodo().equals(ambitos[j])){
							ambitosTraducidos[j]=nodos[i].getNodo();
							encontrado=true;
//						}else if(DependentServerProperties.getInstance().getProperty(IDENTIFICADOR_NODO).equals(ambitos[j])){
						}else if(serverId.equals(ambitos[j])){
							ambitosTraducidos[j]=serverOn; //DependentServerProperties.getInstance().getServerOn();
							encontrado=true;
						}
					}
					if(!encontrado){
						ambitosTraducidos[j]=ambitos[j];
					}
				}
			}
		}
		
		ContribucionOdeVO[] contribuciones = null;
		if(documentoBusqueda.getContribuciones()!=null && documentoBusqueda.getContribuciones().length>0){
			contribuciones= new ContribucionOdeVO[documentoBusqueda.getContribuciones().length];
			for(int i=0; i< contribuciones.length;i++){
				ContribucionOdeVO contribucion = (ContribucionOdeVO) this.getBeanMapper().map(documentoBusqueda.getContribuciones()[i], ContribucionOdeVO.class);
				contribuciones[i] = contribucion;
			}
		}
		return new MetadatoBasicoVO(documentoBusqueda.getLicencias(),
				documentoBusqueda.getTitulo(),
				documentoBusqueda.getDescripcion(),
				documentoBusqueda.getFormato(),
				documentoBusqueda.getDestinatarios(),
				documentoBusqueda.getIdioma(),
				(ambitosTraducidos!=null)?ambitosTraducidos:documentoBusqueda.getAmbito(),
				documentoBusqueda.getValoracion(),
				documentoBusqueda.getLocalizadorODE(),
				documentoBusqueda.getIdentificadorODE(),
				documentoBusqueda.getImagen(),
				documentoBusqueda.getNivelAgregacion(),
				documentoBusqueda.getTamanio(),
				documentoBusqueda.getConSinSecuencia(),
				documentoBusqueda.getFechaPublicacion(),
				documentoBusqueda.getHoraPublicacion(),
				documentoBusqueda.getAutor(),
				documentoBusqueda.getAreaCurricular(),
				documentoBusqueda.getPalabrasClave(),
				documentoBusqueda.getTipoRecurso(),
				documentoBusqueda.getOrientacionDidactica(),
				contribuciones);
	}

    /**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarService#solicitarMetadatosOaiOre(es.pode.buscar.negocio.servicios.ParametroMetadatoVO)
	 * @param parametros es.pode.buscar.negocio.servicios.ParametroMetadatoVO Este VO alberga los parametros que configuran la búsqueda de los metadatos solicitados 
	 * @return es.pode.buscar.negocio.servicios.MetadatoOiaOreVO Esta clase representa los resultados metadatos necesarios para hacer llamadas OaiOre
	 */
	protected MetadatoOiaOreVO handleSolicitarMetadatosOaiOre(ParametroMetadatoVO parametros) throws Exception {
		try{		
			MetadatoOiaOreVO resultado = new MetadatoOiaOreVO();
			DocMECSimpleVO docSimpleVO = this.getSrvBuscadorService().busquedaMECSimple(parametros.getIdentificadorODE(), parametros.getIdioma());
			if(docSimpleVO!=null){
				resultado.setLocalizador(docSimpleVO.getLocalizadorODE());
				resultado.setNivelAgregacion(docSimpleVO.getNivelAgregacion());
				resultado.setAreaCurricular(docSimpleVO.getAreaCurricular());
				resultado.setTesauros(docSimpleVO.getTesauro());
			}else{
				logger.debug("Buscando metadatos (OAIORE) para MEC <"+parametros.getIdentificadorODE()+"] en el idioma <"+parametros.getIdioma()+">. No hay resultados disponibles.");
				throw new Exception("Buscando metadatos (OAIORE) para MEC <"+parametros.getIdentificadorODE()+"> en el idioma <"+parametros.getIdioma()+">. No hay resultados disponibles.");
			}
				
			return resultado;
		}catch(Exception e){
			logger.error("Error buscando metadatos (OAIORE) para MEC["+parametros.getIdentificadorODE()+"] en el idioma["+parametros.getIdioma()+"].",e);
			throw new Exception("Excepcion buscando metadatos (OAIORE) para MEC <"+parametros.getIdentificadorODE()+"> en el idioma <"+parametros.getIdioma()+">",e);
		
		}

	}

	
	@Override
	protected DocumentosVO30 handleBusquedaAvanzadaIndiceRemoto(
			ParametrosBusquedaAvanzadaVO30 parametros, String idNodo)
			throws Exception {
		
//		Configuramos la llamada al indexador/buscador con los parametros de busqueda.
	
		try {
			ParamAvanzadoVO paramBusq = new ParamAvanzadoVO();
			paramBusq.setIdiomaNavegacion(parametros.getIdiomaNavegacion());
			paramBusq.setNumeroResultados(parametros.getNumeroResultados());
			paramBusq.setPalabrasClave(parametros.getPalabrasClave());
			paramBusq.setIdiomaBusqueda(parametros.getIdiomaBusqueda());
			paramBusq.setTitulo(parametros.getTitulo());
			paramBusq.setDescripcion(parametros.getDescripcion());
			paramBusq.setAutor(parametros.getAutor());
			paramBusq.setContexto(parametros.getContexto());
			paramBusq.setAmbito(parametros.getAmbito());
			paramBusq.setEdad(parametros.getEdad());
			paramBusq.setFechaPublicacion(parametros.getFechaPublicacion());
			paramBusq.setFormato(parametros.getFormato());
			paramBusq.setTaxonomia(parametros.getTaxonomia());
			paramBusq.setProcesoCognitivo(parametros.getProcesoCognitivo());
			paramBusq.setRecurso(parametros.getRecurso());
			paramBusq.setKeyword(parametros.getKeyword());
			paramBusq.setSecuencia(parametros.getSecuencia());
			paramBusq.setValoracion(parametros.getValoracion());
			paramBusq.setDestinatarios(parametros.getDestinatarios());
			paramBusq.setNivelAgregacion(parametros.getNivelAgregacion());
			paramBusq.setBusquedaSimpleAvanzada(parametros.getBusquedaSimpleAvanzada());
			paramBusq.setIdentificadorODE(parametros.getIdentificador());
			
			if(parametros.getLicencias()!=null && parametros.getLicencias().length>0)
			{
		    	if (logger.isDebugEnabled()) logger.debug("Incluyendo licencia como parámetro de búsqueda : " +parametros.getLicencias()[0]);
				paramBusq.setLicencias(parametros.getLicencias()[0]);
			}

			
			// Si llegan comunidades seleccionadas es porque se llega aqui desde una búsqueda federada
			if(parametros!=null && parametros.getComunidadesSeleccionadas() != null && parametros.getComunidadesSeleccionadas().length>0){
				
		    	String idCache = handleGeneradorIdentificadorAvanzado(parametros);
		    	parametros.setComunidadesSeleccionadas(null);
		    	logger.debug("Buscando en cache con idBusqueda <"+idCache+">");
//		    	Busco en la cache si esta busqueda ya se realizo. Si tengo exito, la devuelvo, si no realizamos la busqueda.   	
		    	Element elemento = cacheConfig.getHits().get(idCache);
		    	
		    	if(elemento!=null){
			    	HitCacheVO resultadoCache = null; //este obj alberga los resultados en la cache
			    	logger.debug("Hit de cache con idBusqueda <"+idCache+">");
		    		resultadoCache = (HitCacheVO)elemento.getObjectValue();
		    		return new DocumentosVO30(mapValoresBusquedaVO2DocVO30(resultadoCache.getResultadoBusquedaWeb()),new String[0],resultadoCache.getNumeroResultados(),resultadoCache.getTotalResultados(),0,new es.pode.indexador.negocio.servicios.busqueda.TaxonVO30 [0],null);
		    	}
	    	}else parametros.setComunidadesSeleccionadas(null);
			
			DocumentosVO30 resultados=null;
			if (idNodo == null || idNodo.equals("")) 
				resultados = getSrvBuscadorService().busquedaAvanzada(paramBusq);
			else 
				resultados = getSrvBuscadorService().busquedaAvanzadaNodos(paramBusq, idNodo);
			
			return resultados;
			
		} catch (Exception e){
//			Exception exc = new Exception("ERROR: invocacion al servicio busquedaAvanzada de Indexados/Buscador con idiomaNavegacion["+parametros.getIdiomaNavegacion()+"], numeroResultados["+parametros.getNumeroResultados()+"], palabrasClave["+parametros.getPalabrasClave()+"] e idiomaBusqueda["+parametros.getIdiomaBusqueda()+"]", e);
			logger.error(e);
			return null;
		}
	}

	@Override
	protected ResultadosNodoCountVO handleSolicitudDocsCountIndiceRemoto(
			ParametrosDocsCountVO30 parametros, String idNodo) throws Exception {
		try{
			ParamDocsCountVO paramBusq = new ParamDocsCountVO(parametros.getTaxonomia(),parametros.getIdiomaBusqueda());
			ResultadosCountVO resultado = this.getSrvBuscadorService().solicitudDocsCountIndiceRemoto(paramBusq,idNodo);
			return new ResultadosNodoCountVO(resultado.getDocumentosCount(),resultado.getConteo());
		} 
		catch (Exception e) 
		{
			Exception exc = new Exception("ERROR: invocacion al servicio solicitudDocsCountArbolCurricular de Indexados/Buscador con idiomaNavegacion["+parametros.getIdiomaNavegacion()+"], idNodo["+Arrays.toString(parametros.getTaxonomia())+"]", e);
			logger.error(exc);
			throw exc;
		}
	}

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}

	protected boolean handleBorrarHitCache() throws Exception {
		return cacheConfig.removeHitsCache();
	}

	
	@Override
	protected ResultadosEstadisticasVO handleObtenerEstadisticas() throws Exception {
		logger.debug("handleObtenerEstadisticas begins");

		Unmarshaller unmarshaller = new Unmarshaller(EstadisticasAgrega.class);
		unmarshaller.setValidation(false);

		File fichero = new File(AgregaPropertiesImpl.getInstance().getProperty(
				AgregaProperties.PATH_ESTADISTICAS)
				+ "//"
				+ NOMBRE_FICHERO_ESTADISTICAS_TOTALES
				+ EXTENSION_FICHERO_ESTADISTICAS);

		FileInputStream fis = null;
		InputStreamReader isr = null;
		EstadisticasAgrega estAgrega = null;
		ResultadosEstadisticasVO resultadosEstadisticasVO = new ResultadosEstadisticasVO();

		try {
			fis = new FileInputStream(fichero);
			isr = new InputStreamReader(fis);
			estAgrega = (EstadisticasAgrega) unmarshaller.unmarshal(isr);
			
			String[] terminosMasBuscadosTotales = new String[5];
			int[] terminosMasBuscadosNumLocales = new int[5];
			int[] terminosMasBuscadosNumTotales = new int[5];
			
			String[] terminosMasBuscadosAnoTotales = new String[5];
			int[] terminosMasBuscadosAnoNumLocales = new int[5];
			int[] terminosMasBuscadosAnoNumTotales = new int[5];			

			String[] terminosMasBuscadosMesTotales = new String[5];
			int[] terminosMasBuscadosMesNumLocales = new int[5];
			int[] terminosMasBuscadosMesNumTotales = new int[5];
			
			String[] terminosMasBuscadosSemanaTotales = new String[5];
			int[] terminosMasBuscadosSemanaNumLocales = new int[5];
			int[] terminosMasBuscadosSemanaNumTotales = new int[5];
			
			for (Estadistica estadistica : estAgrega.getEstadistica()) {
				switch (Integer.valueOf(estadistica.getCodigo())) {
				case 1:
					resultadosEstadisticasVO.setBusquedasLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setBusquedasTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesBusquedas(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesBusquedasValores(valorNodosEstadistica);
					}
					break;
				case 2:
					resultadosEstadisticasVO.setDescargas(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setDescargasTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesDescargas(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesDescargasValores(valorNodosEstadistica);
					}
					break;
				case 3:
					resultadosEstadisticasVO.setFichasAccedidasLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setFichasAccedidasTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesFichasAccedidas(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesFichasAccedidasValores(valorNodosEstadistica);
					}
					break;
				case 4:
					resultadosEstadisticasVO.setOdesPrevisualizadosLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setOdesPrevisualizadosTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesOdesPrevisualizados(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesOdesPrevisualizadosValores(valorNodosEstadistica);
					}
					break;
				case 5:
					resultadosEstadisticasVO.setBachilleratoLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setBachilleratoTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesBachillerato(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesBachilleratoValores(valorNodosEstadistica);
					}
					break;
				case 6:
					resultadosEstadisticasVO.setEducacionInfantilLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setEducacionInfantilTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesEducacionInfantil(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesEducacionInfantilValores(valorNodosEstadistica);
					}
					break;
				case 7:
					resultadosEstadisticasVO.setEducacionPrimariaLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setEducacionPrimariaTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesEducacionPrimaria(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesEducacionPrimariaValores(valorNodosEstadistica);
					}
					break;
				case 8:
					resultadosEstadisticasVO.setEduSecundariaObligatoriaLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setEduSecundariaObligatoriaTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesEduSecundariaObligatoria(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesEduSecundariaObligatoriaValores(valorNodosEstadistica);
					}
					break;
				case 9:
					resultadosEstadisticasVO.setEnsArtisticasLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setEnsArtisticasTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesEnsArtisticas(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesEnsArtisticasValores(valorNodosEstadistica);
					}
					break;
				case 10:
					resultadosEstadisticasVO.setEnsOficialIdiomasLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setEnsOficialIdiomasTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesEnsOficialIdiomas(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesEnsOficialIdiomasValores(valorNodosEstadistica);
					}
					break;
				case 11:
					resultadosEstadisticasVO.setFormacionProfesionalLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setFormacionProfesionalTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesFormacionProfesional(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesFormacionProfesionalValores(valorNodosEstadistica);
					}
					break;
				case 12:
					resultadosEstadisticasVO.setLicenciaPropietariaLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setLicenciaPropietariaTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesLicenciaPropietaria(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesLicenciaPropietariaValores(valorNodosEstadistica);
					}
					break;
				case 13:
					resultadosEstadisticasVO.setLicenciaLibreEUPLLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setLicenciaLibreEUPLTotlaes(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesLicenciaLibreEUPL(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesLicenciaLibreEUPLValores(valorNodosEstadistica);
					}
					break;
				case 14:
					resultadosEstadisticasVO.setLicenciaLibreGPLLoclaes(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setLicenciaLibreGPLTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesLicenciaLibreGPL(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesLicenciaLibreGPLValores(valorNodosEstadistica);
					}
					break;
				case 15:
					resultadosEstadisticasVO.setLicenciaLibreDualLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setLicenciaLibreDualTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesLicenciaLibreDual(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesLicenciaLibreDualValores(valorNodosEstadistica);
					}
					break;
				case 16:
					resultadosEstadisticasVO.setOtrasLicenciasLibresLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setOtrasLicenciasLibresTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesOtrasLicenciasLibres(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesOtrasLicenciasLibresValores(valorNodosEstadistica);
					}
					break;
				case 17:
					resultadosEstadisticasVO.setDominioPublicoLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setDominioPublicoTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesDominioPublico(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesDominioPublicoValores(valorNodosEstadistica);
					}
					break;
				case 18:
					resultadosEstadisticasVO.setNoCorrespondeLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNoCorrespondeTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesNoCorresponde(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesNoCorrespondeValores(valorNodosEstadistica);
					}
					break;
				case 19:
					resultadosEstadisticasVO.setLicenciaPropietariaIntelectualLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setLicenciaPropietariaIntelectualTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesLicenciaPropietariaIntelectual(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesLicenciaPropietariaIntelectualValores(valorNodosEstadistica);
					}
					break;
				case 20:
					resultadosEstadisticasVO.setCCRReconocimientoLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimiento(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoValores(valorNodosEstadistica);
					}
					break;
				case 21:
					resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivada(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaValores(valorNodosEstadistica);
					}
					break;
				case 22:
					resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaNoComercialLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaNoComercialTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercial(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores(valorNodosEstadistica);
					}
					break;
				case 23:
					resultadosEstadisticasVO.setCCRReconocimientoNoComercialLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoNoComercialTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercial(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialValores(valorNodosEstadistica);
					}
					break;
				case 24:
					resultadosEstadisticasVO.setCCRReconocimientoNoComercialCompartirIgualLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoNoComercialCompartirIgualTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgual(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgualValores(valorNodosEstadistica);
					}
					break;
				case 25:
					resultadosEstadisticasVO.setCCRReconocimientoCompartirIgualLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setCCRReconocimientoCompartirIgualTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoCompartirIgual(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCCRReconocimientoCompartirIgualValores(valorNodosEstadistica);
					}
					break;
				case 26:
					resultadosEstadisticasVO.setGFDLLocales(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setGFDLTotales(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesGFDL(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesGFDLValores(valorNodosEstadistica);
					}
					break;		
				case 27:
					resultadosEstadisticasVO.setNumLocalCursos(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNumTotalCursos(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesCursos(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesCursosValores(valorNodosEstadistica);
					}
					break;
				case 28:
					resultadosEstadisticasVO.setNumLocalMediosIntegrados(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNumTotalMediosIntegrados(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMediosIntegrados(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMediosIntegradosValores(valorNodosEstadistica);
					}
					break;
				case 29:
					resultadosEstadisticasVO.setNumLocalObjetos(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNumTotalObjetos(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesObjetos(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesObjetosValores(valorNodosEstadistica);
					}
					break;
				case 30:
					resultadosEstadisticasVO.setNumLocalObjetosAprendizaje(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNumTotalObjetosAprendizaje(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesObjAprendizaje(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesObjAprendizajeValores(valorNodosEstadistica);
					}
					break;
				case 31:
					resultadosEstadisticasVO.setNumLocalSecuencias(Integer.valueOf(estadistica.getValor()));
					resultadosEstadisticasVO.setNumTotalSecuencias(Integer.valueOf(estadistica.getValorTotal()));
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesSecuencias(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesSecuenciasValores(valorNodosEstadistica);
					}
					break;
				case 32:
					terminosMasBuscadosTotales[0] = estadistica.getDescripcion();
					terminosMasBuscadosNumLocales[0] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosNumTotales[0] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalPrimero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalPrimeroValores(valorNodosEstadistica);
					}
					break;
				case 33:
					terminosMasBuscadosTotales[1] = estadistica.getDescripcion();
					terminosMasBuscadosNumLocales[1] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosNumTotales[1] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalSegundo(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalSegundoValores(valorNodosEstadistica);
					}
					break;
				case 34:
					terminosMasBuscadosTotales[2] = estadistica.getDescripcion();
					terminosMasBuscadosNumLocales[2] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosNumTotales[2] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalTercero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalTerceroValores(valorNodosEstadistica);
					}
					break;
				case 35:
					terminosMasBuscadosTotales[3] = estadistica.getDescripcion();
					terminosMasBuscadosNumLocales[3] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosNumTotales[3] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalCuarto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalCuartoValores(valorNodosEstadistica);
					}
					break;
				case 36:
					terminosMasBuscadosTotales[4] = estadistica.getDescripcion();
					terminosMasBuscadosNumLocales[4] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosNumTotales[4] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalQuinto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoTotalQuintoValores(valorNodosEstadistica);
					}
					break;
				case 37:
					terminosMasBuscadosAnoTotales[0] = estadistica.getDescripcion();
					terminosMasBuscadosAnoNumLocales[0] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosAnoNumTotales[0] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioPrimero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioPrimeroValores(valorNodosEstadistica);
					}
					break;
				case 38:
					terminosMasBuscadosAnoTotales[1] = estadistica.getDescripcion();
					terminosMasBuscadosAnoNumLocales[1] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosAnoNumTotales[1] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioSegundo(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioSegundoValores(valorNodosEstadistica);
					}
					break;
				case 39:
					terminosMasBuscadosAnoTotales[2] = estadistica.getDescripcion();
					terminosMasBuscadosAnoNumLocales[2] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosAnoNumTotales[2] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioTercero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioTerceroValores(valorNodosEstadistica);
					}
					break;
				case 40:
					terminosMasBuscadosAnoTotales[3] = estadistica.getDescripcion();
					terminosMasBuscadosAnoNumLocales[3] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosAnoNumTotales[3] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioCuarto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioCuartoValores(valorNodosEstadistica);
					}
					break;
				case 41:
					terminosMasBuscadosAnoTotales[4] = estadistica.getDescripcion();
					terminosMasBuscadosAnoNumLocales[4] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosAnoNumTotales[4] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioQuinto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoAnioQuintoValores(valorNodosEstadistica);
					}
					break;
				case 42:
					terminosMasBuscadosMesTotales[0] = estadistica.getDescripcion();
					terminosMasBuscadosMesNumLocales[0] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosMesNumTotales[0] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesPrimero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesPrimeroValores(valorNodosEstadistica);
					}
					break;
				case 43:
					terminosMasBuscadosMesTotales[1] = estadistica.getDescripcion();
					terminosMasBuscadosMesNumLocales[1] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosMesNumTotales[1] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesSegundo(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesSegundoValores(valorNodosEstadistica);
					}
					break;
				case 44:
					terminosMasBuscadosMesTotales[2] = estadistica.getDescripcion();
					terminosMasBuscadosMesNumLocales[2] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosMesNumTotales[2] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesTercero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesTerceroValores(valorNodosEstadistica);
					}
					break;
				case 45:
					terminosMasBuscadosMesTotales[3] = estadistica.getDescripcion();
					terminosMasBuscadosMesNumLocales[3] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosMesNumTotales[3] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesCuarto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesCuartoValores(valorNodosEstadistica);
					}
					break;
				case 46:
					terminosMasBuscadosMesTotales[4] = estadistica.getDescripcion();
					terminosMasBuscadosMesNumLocales[4] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosMesNumTotales[4] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesQuinto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoMesQuintoValores(valorNodosEstadistica);
					}
					break;
				case 47:
					terminosMasBuscadosSemanaTotales[0] = estadistica.getDescripcion();
					terminosMasBuscadosSemanaNumLocales[0] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosSemanaNumTotales[0] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaPrimero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaPrimeroValores(valorNodosEstadistica);
					}
					break;
				case 48:
					terminosMasBuscadosSemanaTotales[1] = estadistica.getDescripcion();
					terminosMasBuscadosSemanaNumLocales[1] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosSemanaNumTotales[1] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaSegundo(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaSegundoValores(valorNodosEstadistica);
					}
					break;
				case 49:
					terminosMasBuscadosSemanaTotales[2] = estadistica.getDescripcion();
					terminosMasBuscadosSemanaNumLocales[2] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosSemanaNumTotales[2] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaTercero(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaTerceroValores(valorNodosEstadistica);
					}
					break;
				case 50:
					terminosMasBuscadosSemanaTotales[3] = estadistica.getDescripcion();
					terminosMasBuscadosSemanaNumLocales[3] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosSemanaNumTotales[3] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaCuarto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaCuartoValores(valorNodosEstadistica);
					}
					break;
				case 51:
					terminosMasBuscadosSemanaTotales[4] = estadistica.getDescripcion();
					terminosMasBuscadosSemanaNumLocales[4] = Integer.valueOf(estadistica.getValor());
					terminosMasBuscadosSemanaNumTotales[4] = Integer.valueOf(estadistica.getValorTotal());
					if (estadistica.getNodosEstadisticaTotal() != null) {
						NodosEstadisticaTotal nodosEstadisticaTotal = estadistica.getNodosEstadisticaTotal();
						int arraysLength = nodosEstadisticaTotal.getNodoEstadistica().length;
						String[] nombreNodosEstadistica = new String [arraysLength];
						int[] valorNodosEstadistica = new int [arraysLength];
						int i = 0;
						for (NodoEstadistica nodoEstadistica : nodosEstadisticaTotal.getNodoEstadistica()) {
							nombreNodosEstadistica[i] = nodoEstadistica.getNombreNodoEstadistica();
							valorNodosEstadistica [i] = Integer.valueOf(nodoEstadistica.getValorNodoEstadistica());
							i++;
						}
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaQuinto(nombreNodosEstadistica);
						resultadosEstadisticasVO.setComunidadesMasBuscadoSemanaQuintoValores(valorNodosEstadistica);
					}
					break;
				default:
				}
			}
			resultadosEstadisticasVO.setTerminosMasBuscadosTotales(terminosMasBuscadosTotales);
			resultadosEstadisticasVO.setTerminosMasBuscadosNumLocales(terminosMasBuscadosNumLocales);
			resultadosEstadisticasVO.setTerminosMasBuscadosNumTotales(terminosMasBuscadosNumTotales);
			
			resultadosEstadisticasVO.setTerminosMasBuscadosAnoTotales(terminosMasBuscadosAnoTotales);
			resultadosEstadisticasVO.setTerminosMasBuscadosAnoNumLocales(terminosMasBuscadosAnoNumLocales);
			resultadosEstadisticasVO.setTerminosMasBuscadosAnoNumTotales(terminosMasBuscadosAnoNumTotales);
			
			resultadosEstadisticasVO.setTerminosMasBuscadosMesTotales(terminosMasBuscadosMesTotales);
			resultadosEstadisticasVO.setTerminosMasBuscadosMesNumLocales(terminosMasBuscadosMesNumLocales);
			resultadosEstadisticasVO.setTerminosMasBuscadosMesNumTotales(terminosMasBuscadosMesNumTotales);
			
			resultadosEstadisticasVO.setTerminosMasBuscadosSemanaTotales(terminosMasBuscadosSemanaTotales);
			resultadosEstadisticasVO.setTerminosMasBuscadosSemanaNumLocales(terminosMasBuscadosSemanaNumLocales);
			resultadosEstadisticasVO.setTerminosMasBuscadosSemanaNumTotales(terminosMasBuscadosSemanaNumTotales);
		} catch (Exception e) {
			logger.error("Error en parseo a XML de configuración de nodo: " + e.getMessage());
			logger.debug("",e);
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (isr != null)
					isr.close();
			} catch (Exception e) {
				logger.warn("No se puede cerrar los stream de lectura del fichero xml de estadisticas"+ e.getCause());
				logger.debug("",e);
			}
		}
//		logger.debug("handleObtenerEstadisticas ends");
		return resultadosEstadisticasVO;
	}

	@Override
	protected ResultadosEstadisticasVO handleObtenerEstadisticasPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
//		logger.debug("handleObtenerEstadisticasPorFechas begins");
		
		ResultadosEstadisticasVO resultadosEstadisticasVO = new ResultadosEstadisticasVO();
		Map<Integer,Map<String,Integer>> comunidadesPorEstadisticaMap = new HashMap<Integer,Map<String,Integer>>();
		
		int[] totales;
		int[] locales;
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			java.util.Collection estadisticas = this.getEstadisticaDao().obtenerEstadisticasAgrupadasPorFechas(fechaDesde , fechaHasta);
			java.util.Collection terminosMasBuscados = this.getEstadisticaDao().obtenerEstadisticasTerminosPorFechas(fechaDesde, fechaHasta);
			
			if(estadisticas == null || estadisticas.isEmpty()) {
				logger.warn("No hay resultados en la base de datos (estadistica=null");
			}else if (estadisticas.size()%31 != 0) {
				logger.warn("No se ha recuperado correctamente la cantidad de estadisticas, o la BBDD esta corrupta");
			} else {
				totales = new int[estadisticas.size()];
				locales = new int[estadisticas.size()];
				
				for (Object object : estadisticas) {
					Object[] estadistica = (Object[])object;
					
					int codigo = Integer.valueOf((String)estadistica[0]);
					totales[codigo] += ((Long)estadistica[2]).intValue();
					
					Map<String,Integer> comunidadYValor = comunidadesPorEstadisticaMap.get(codigo);
					if (comunidadYValor == null || comunidadYValor.isEmpty()) {
						Map<String,Integer> comunidadYValorAux = new HashMap<String,Integer>();
						comunidadYValorAux.put((String)estadistica[1], ((Long)estadistica[2]).intValue());
						comunidadesPorEstadisticaMap.put(codigo, comunidadYValorAux);
					} else {
						comunidadYValor.put((String)estadistica[1], ((Long)estadistica[2]).intValue());
						comunidadesPorEstadisticaMap.put(codigo, comunidadYValor);
					}
					
					if(AgregaPropertiesImpl.getInstance().getProperty(
							AgregaProperties.NODO).equals((String)estadistica[1])) {
						locales[codigo] += ((Long)estadistica[2]).intValue();
					}
				}
				
				for (int j=1; j<=estadisticas.size(); j++) {
					switch (j) {
						case 1:
							resultadosEstadisticasVO.setBusquedasLocales(locales[j]);
							resultadosEstadisticasVO.setBusquedasTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesBusquedas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesBusquedasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 2:
							resultadosEstadisticasVO.setDescargas(locales[j]);
							resultadosEstadisticasVO.setDescargasTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesDescargas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesDescargasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 3:
							resultadosEstadisticasVO.setFichasAccedidasLocales(locales[j]);
							resultadosEstadisticasVO.setFichasAccedidasTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesFichasAccedidas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesFichasAccedidasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 4:
							resultadosEstadisticasVO.setOdesPrevisualizadosLocales(locales[j]);
							resultadosEstadisticasVO.setOdesPrevisualizadosTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesOdesPrevisualizados(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesOdesPrevisualizadosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 5:
							resultadosEstadisticasVO.setBachilleratoLocales(locales[j]);
							resultadosEstadisticasVO.setBachilleratoTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesBachillerato(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesBachilleratoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 6:
							resultadosEstadisticasVO.setEducacionInfantilLocales(locales[j]);
							resultadosEstadisticasVO.setEducacionInfantilTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesEducacionInfantil(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesEducacionInfantilValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 7:
							resultadosEstadisticasVO.setEducacionPrimariaLocales(locales[j]);
							resultadosEstadisticasVO.setEducacionPrimariaTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesEducacionPrimaria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesEducacionPrimariaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						case 8:
							resultadosEstadisticasVO.setEduSecundariaObligatoriaLocales(locales[j]);
							resultadosEstadisticasVO.setEduSecundariaObligatoriaTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesEduSecundariaObligatoria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesEduSecundariaObligatoriaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 9:
							resultadosEstadisticasVO.setEnsArtisticasLocales(locales[j]);
							resultadosEstadisticasVO.setEnsArtisticasTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesEnsArtisticas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesEnsArtisticasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 10:
							resultadosEstadisticasVO.setEnsOficialIdiomasLocales(locales[j]);
							resultadosEstadisticasVO.setEnsOficialIdiomasTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesEnsOficialIdiomas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesEnsOficialIdiomasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 11:
							resultadosEstadisticasVO.setFormacionProfesionalLocales(locales[j]);
							resultadosEstadisticasVO.setFormacionProfesionalTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesFormacionProfesional(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesFormacionProfesionalValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 12:
							resultadosEstadisticasVO.setLicenciaPropietariaLocales(locales[j]);
							resultadosEstadisticasVO.setLicenciaPropietariaTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesLicenciaPropietaria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesLicenciaPropietariaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 13:
							resultadosEstadisticasVO.setLicenciaLibreEUPLLocales(locales[j]);
							resultadosEstadisticasVO.setLicenciaLibreEUPLTotlaes(totales[j]);
							resultadosEstadisticasVO.setComunidadesLicenciaLibreEUPL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesLicenciaLibreEUPLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 14:
							resultadosEstadisticasVO.setLicenciaLibreGPLLoclaes(locales[j]);
							resultadosEstadisticasVO.setLicenciaLibreGPLTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesLicenciaLibreGPL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesLicenciaLibreGPLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 15:
							resultadosEstadisticasVO.setLicenciaLibreDualLocales(locales[j]);
							resultadosEstadisticasVO.setLicenciaLibreDualTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesLicenciaLibreDual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesLicenciaLibreDualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 16:
							resultadosEstadisticasVO.setOtrasLicenciasLibresLocales(locales[j]);
							resultadosEstadisticasVO.setOtrasLicenciasLibresTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesOtrasLicenciasLibres(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesOtrasLicenciasLibresValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 17:
							resultadosEstadisticasVO.setDominioPublicoLocales(locales[j]);
							resultadosEstadisticasVO.setDominioPublicoTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesDominioPublico(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesDominioPublicoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 18:
							resultadosEstadisticasVO.setNoCorrespondeLocales(locales[j]);
							resultadosEstadisticasVO.setNoCorrespondeTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesNoCorresponde(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesNoCorrespondeValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 19:
							resultadosEstadisticasVO.setLicenciaPropietariaIntelectualLocales(locales[j]);
							resultadosEstadisticasVO.setLicenciaPropietariaIntelectualTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesLicenciaPropietariaIntelectual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesLicenciaPropietariaIntelectualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 20:
							resultadosEstadisticasVO.setCCRReconocimientoLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimiento(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 21:
							resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivada(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 22:
							resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaNoComercialLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoSinObraDerivadaNoComercialTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercial(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 23:
							resultadosEstadisticasVO.setCCRReconocimientoNoComercialLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoNoComercialTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercial(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 24:
							resultadosEstadisticasVO.setCCRReconocimientoNoComercialCompartirIgualLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoNoComercialCompartirIgualTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 25:
							resultadosEstadisticasVO.setCCRReconocimientoCompartirIgualLocales(locales[j]);
							resultadosEstadisticasVO.setCCRReconocimientoCompartirIgualTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoCompartirIgual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCCRReconocimientoCompartirIgualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 26:
							resultadosEstadisticasVO.setGFDLLocales(locales[j]);
							resultadosEstadisticasVO.setGFDLTotales(totales[j]);
							resultadosEstadisticasVO.setComunidadesGFDL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesGFDLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 27:
							resultadosEstadisticasVO.setNumLocalCursos(locales[j]);
							resultadosEstadisticasVO.setNumTotalCursos(totales[j]);
							resultadosEstadisticasVO.setComunidadesCursos(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesCursosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 28:
							resultadosEstadisticasVO.setNumLocalMediosIntegrados(locales[j]);
							resultadosEstadisticasVO.setNumTotalMediosIntegrados(totales[j]);
							resultadosEstadisticasVO.setComunidadesMediosIntegrados(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesMediosIntegradosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 29:
							resultadosEstadisticasVO.setNumLocalObjetos(locales[j]);
							resultadosEstadisticasVO.setNumTotalObjetos(totales[j]);
							resultadosEstadisticasVO.setComunidadesObjetos(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesObjetosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 30:
							resultadosEstadisticasVO.setNumLocalObjetosAprendizaje(locales[j]);
							resultadosEstadisticasVO.setNumTotalObjetosAprendizaje(totales[j]);
							resultadosEstadisticasVO.setComunidadesObjAprendizaje(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesObjAprendizajeValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 31:
							resultadosEstadisticasVO.setNumLocalSecuencias(locales[j]);
							resultadosEstadisticasVO.setNumTotalSecuencias(totales[j]);
							resultadosEstadisticasVO.setComunidadesSecuencias(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasVO.setComunidadesSecuenciasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						default:
							logger.warn("No se han recuperado bien las primeras 31 estadisticas");
							return new ResultadosEstadisticasVO();
					}
				}
			}
			
			if(terminosMasBuscados == null || terminosMasBuscados.isEmpty()) {
				logger.warn("No se han recuperado correctamente los terminos mas buscados");
			} else {
				String[] terminosMasBuscadoslist = new String[5];
				int[] terminosMasBuscadosValores = new int[5];
				
				int i = 0;
				for (Object object : terminosMasBuscados) {
					if (i < 5) {
						Object[] termino = (Object[])object;
						terminosMasBuscadoslist[i] = ((String)termino[0]);
						terminosMasBuscadosValores[i] = ((Long)termino[1]).intValue();
						java.util.Collection comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta, (String)termino[0]);
						String[] listaComunidades = new String[comunidades.size()];
						int[] listaValoresPorComunidad = new int [comunidades.size()];
						int j = 0;
						for (Object object2 : comunidades) {
							Object[] comunidad = (Object[])object2;
							listaComunidades[j] = ((String)comunidad[0]);
							listaValoresPorComunidad[j] = ((Long)comunidad[1]).intValue();
							j++;
						}
						switch (i) {
							case 0:
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalPrimero(listaComunidades);
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalPrimeroValores(listaValoresPorComunidad);
								break;
							case 1:
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalSegundo(listaComunidades);
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalSegundoValores(listaValoresPorComunidad);
								break;
							case 2:
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalTercero(listaComunidades);
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalTerceroValores(listaValoresPorComunidad);
								break;
							case 3:
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalCuarto(listaComunidades);
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalCuartoValores(listaValoresPorComunidad);
								break;
							case 4:
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalQuinto(listaComunidades);
								resultadosEstadisticasVO.setComunidadesMasBuscadoTotalQuintoValores(listaValoresPorComunidad);
								break;
							default:
								logger.warn("Nunca debio entrar aqui...");
								break;
						}
						i++;
					} else {
						break;
					}
				}
				resultadosEstadisticasVO.setTerminosMasBuscadosTotales(terminosMasBuscadoslist);
				resultadosEstadisticasVO.setTerminosMasBuscadosNumTotales(terminosMasBuscadosValores);
				
			}
		}
//		logger.debug("handleObtenerEstadisticasPorFechas ends");
		return resultadosEstadisticasVO;
	}

	protected ResultadosEstadisticasActividadVO handleObtenerEstadisticasActividadPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("handleObtenerEstadisticasActividadPorFechas begins");


		
		ResultadosEstadisticasActividadVO resultadosEstadisticasActividadVO = new ResultadosEstadisticasActividadVO();
		Map<Integer,Map<String,Integer>> comunidadesPorEstadisticaMap = new HashMap<Integer,Map<String,Integer>>();		
				
		// Inicializamos las estadísticas
		int[] totales = {0,0,0,0};
		int[] locales =  {0,0,0,0};
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			// Obtenemos las estadisticas agrupadas por nodo y tipo
			java.util.Collection estadisticas = this.getEstadisticaDao().obtenerEstadisticasActividadPorFechas(fechaDesde , fechaHasta);
			if(estadisticas == null || estadisticas.isEmpty()) {
				logger.warn("No hay resultados de actividad en la base de datos (estadistica=null)");
			}else if (estadisticas.size()%NUM_ESTADISTICAS_ACTIVIDAD != 0) {
				logger.warn("No se ha recuperado correctamente la cantidad de estadisticas de actividad, o la BBDD esta corrupta");
			} else {
				int i = 0;		

				// 20130612 Nuevo desarrollo para saber si las estadísticas están actualizadas
				// Para eso, consultamos los datos para el último día del rango. Si el valor es 0 no está actualizado
				// Guardaremos los id de los nodos desactualizados en una lista para posteriormente marcarlos
				
				List<String> aNodosDesactualizados = new ArrayList<String>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fecHastaDia = sdf.parse(sdf.format(fechaHasta));
								
				Collection<Object> estadisticasUltimoDia = this.getEstadisticaDao().obtenerEstadisticasActividadPorFechas(fecHastaDia, fecHastaDia);

				for (Object object : estadisticasUltimoDia) {	
					Object[] estadistica = (Object[])object;
					String codigoEst = (String) estadistica[0];
					String nodo = (String) estadistica[1];
					Integer valorDato = (Integer) estadistica[2];										
					
					if (codigoEst.equals("01") && valorDato.intValue()==0)
					{
						aNodosDesactualizados.add(nodo);
					}
					
				}
				
				for (Object object : estadisticas) {	
					Object[] estadistica = (Object[])object;
					String nodoActual = (String)estadistica[1];
					if (aNodosDesactualizados.size()==0 || aNodosDesactualizados.contains(nodoActual))
					{
						if (logger.isDebugEnabled())
							logger.error("El nodo :"+ nodoActual +" tiene las estadísticas deasctualizadas.");
						nodoActual+=" (*)";
					}
					Map<String,Integer> comunidadYValor = comunidadesPorEstadisticaMap.get(i);
					if (comunidadYValor == null || comunidadYValor.isEmpty()) {
						Map<String,Integer> comunidadYValorAux = new TreeMap<String,Integer>();
						comunidadYValorAux.put(nodoActual, (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValorAux);
					} else {
						comunidadYValor.put(nodoActual, (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValor);
					}
					
					// Informamos las estadísticas del nodo local
					if(AgregaPropertiesImpl.getInstance().getProperty(
							AgregaProperties.NODO).equals((String)estadistica[1])) {						
						
						int iNumEstad = Integer.parseInt((String)estadistica[0])-1;						
						locales[iNumEstad] = ((Integer)estadistica[2]).intValue();
					}
					
					// Totalizamos las estadísticas según el tipo 01,02,03 o 04
					if (((String)estadistica[0]).equals("01"))
					{
						totales[0] += ((Integer)estadistica[2]).intValue();
					}
					if (((String)estadistica[0]).equals("02"))
					{
						totales[1] += ((Integer)estadistica[2]).intValue();
					}
					if (((String)estadistica[0]).equals("03"))
					{					
						totales[2] += ((Integer)estadistica[2]).intValue();
					}
					if (((String)estadistica[0]).equals("04"))
					{
						totales[3] += ((Integer)estadistica[2]).intValue();
					}

					i++;
					if (i == NUM_ESTADISTICAS_ACTIVIDAD) {
						i = 0;
					}
				}
				
			
				// Rellenamos el objeto de estadísticas
				for (int j=0; j<NUM_ESTADISTICAS_ACTIVIDAD; j++) {

					switch (j) {
						case 0:
							resultadosEstadisticasActividadVO.setBusquedasLocales(locales[j]);
							resultadosEstadisticasActividadVO.setBusquedasTotales(totales[j]);
							resultadosEstadisticasActividadVO.setComunidadesBusquedas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasActividadVO.setComunidadesBusquedasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 1:
							resultadosEstadisticasActividadVO.setDescargas(locales[j]);
							resultadosEstadisticasActividadVO.setDescargasTotales(totales[j]);
							resultadosEstadisticasActividadVO.setComunidadesDescargas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasActividadVO.setComunidadesDescargasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 2:
							resultadosEstadisticasActividadVO.setFichasAccedidasLocales(locales[j]);
							resultadosEstadisticasActividadVO.setFichasAccedidasTotales(totales[j]);
							resultadosEstadisticasActividadVO.setComunidadesFichasAccedidas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasActividadVO.setComunidadesFichasAccedidasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 3:
							resultadosEstadisticasActividadVO.setOdesPrevisualizadosLocales(locales[j]);
							resultadosEstadisticasActividadVO.setOdesPrevisualizadosTotales(totales[j]);
							resultadosEstadisticasActividadVO.setComunidadesOdesPrevisualizados(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasActividadVO.setComunidadesOdesPrevisualizadosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;							
						default:
							logger.warn("Nunca debio entrar aquí");
					}
				}
			}
		}
//		logger.debug("handleObtenerEstadisticasActividadPorFechas ends");
		return resultadosEstadisticasActividadVO;
	}
	
	protected ResultadosEstadisticasCoberturaCurricularVO handleObtenerEstadisticasCoberturaCurricularPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
//		logger.debug("handleObtenerEstadisticasCoberturaCurricularPorFechas begins");
		
		ResultadosEstadisticasCoberturaCurricularVO resultadosEstadisticasCoberturaCurricularVO = new ResultadosEstadisticasCoberturaCurricularVO();
		Map<Integer,Map<String,Integer>> comunidadesPorEstadisticaMap = new HashMap<Integer,Map<String,Integer>>();
		
		int[] totales = new int[NUM_ESTADISTICAS_COBERTURA_CURRICULAR];
		int[] locales = new int[NUM_ESTADISTICAS_COBERTURA_CURRICULAR];
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			java.util.Collection estadisticas = this.getEstadisticaDao().obtenerEstadisticasCoberturaCurricularPorFechas(fechaDesde, fechaHasta);
			if(estadisticas == null || estadisticas.isEmpty()) {
				logger.warn("No hay resultados de actividad en la base de datos (estadistica=null)");
			}else if (estadisticas.size()%NUM_ESTADISTICAS_COBERTURA_CURRICULAR != 0) {
				logger.warn("No se ha recuperado correctamente la cantidad de estadisticas de actividad, o la BBDD esta corrupta");
			} else {
				int i = 0;
				for (Object object : estadisticas) {
					Object[] estadistica = (Object[])object;
					totales[i] += ((Integer)estadistica[2]).intValue();
					Map<String,Integer> comunidadYValor = comunidadesPorEstadisticaMap.get(i);
					if (comunidadYValor == null || comunidadYValor.isEmpty()) {
						Map<String,Integer> comunidadYValorAux = new TreeMap<String,Integer>();
						comunidadYValorAux.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValorAux);
					} else {
						comunidadYValor.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValor);
					}
					if(AgregaPropertiesImpl.getInstance().getProperty(
							AgregaProperties.NODO).equals((String)estadistica[1])) {
						locales[i] += ((Integer)estadistica[2]).intValue();
					}
					i++;
					if (i == NUM_ESTADISTICAS_COBERTURA_CURRICULAR) {
						i = 0;
					}
				}
				
				for (int j=0; j<NUM_ESTADISTICAS_COBERTURA_CURRICULAR; j++) {
					switch (j) {
					case 0:
						resultadosEstadisticasCoberturaCurricularVO.setBachilleratoLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setBachilleratoTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesBachillerato(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesBachilleratoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 1:
						resultadosEstadisticasCoberturaCurricularVO.setEducacionInfantilLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setEducacionInfantilTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEducacionInfantil(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEducacionInfantilValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 2:
						resultadosEstadisticasCoberturaCurricularVO.setEducacionPrimariaLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setEducacionPrimariaTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEducacionPrimaria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEducacionPrimariaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 3:
						resultadosEstadisticasCoberturaCurricularVO.setEduSecundariaObligatoriaLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setEduSecundariaObligatoriaTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEduSecundariaObligatoria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEduSecundariaObligatoriaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 4:
						resultadosEstadisticasCoberturaCurricularVO.setEnsArtisticasLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setEnsArtisticasTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEnsArtisticas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEnsArtisticasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 5:
						resultadosEstadisticasCoberturaCurricularVO.setEnsOficialIdiomasLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setEnsOficialIdiomasTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEnsOficialIdiomas(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesEnsOficialIdiomasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;
					case 6:
						resultadosEstadisticasCoberturaCurricularVO.setFormacionProfesionalLocales(locales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setFormacionProfesionalTotales(totales[j]);
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesFormacionProfesional(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						resultadosEstadisticasCoberturaCurricularVO.setComunidadesFormacionProfesionalValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
						break;							
						default:
							logger.warn("Nunca debio entrar aquí");
					}
				}
			}
		}
//		logger.debug("handleObtenerEstadisticasCoberturaCurricularPorFechas ends");
		return resultadosEstadisticasCoberturaCurricularVO;
	}
	
	protected ResultadosEstadisticasLicenciasVO handleObtenerEstadisticasLicenciasPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
//		logger.debug("handleObtenerEstadisticasLicenciasPorFechas begins");
		
		ResultadosEstadisticasLicenciasVO resultadosEstadisticasLicenciasVO = new ResultadosEstadisticasLicenciasVO();
		Map<Integer,Map<String,Integer>> comunidadesPorEstadisticaMap = new HashMap<Integer,Map<String,Integer>>();
		
		int[] totales = new int[NUM_ESTADISTICAS_LICENCIAS];
		int[] locales = new int[NUM_ESTADISTICAS_LICENCIAS];
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			java.util.Collection estadisticas = this.getEstadisticaDao().obtenerEstadisticasLicenciasPorFechas(fechaDesde, fechaHasta);
			if(estadisticas == null || estadisticas.isEmpty()) {
				logger.warn("No hay resultados de actividad en la base de datos (estadistica=null)");
			}else if (estadisticas.size()%NUM_ESTADISTICAS_LICENCIAS != 0) {
				logger.warn("No se ha recuperado correctamente la cantidad de estadisticas de actividad, o la BBDD esta corrupta");
			} else {
				int i = 0;
				for (Object object : estadisticas) {
					Object[] estadistica = (Object[])object;
					totales[i] += ((Integer)estadistica[2]).intValue();
					Map<String,Integer> comunidadYValor = comunidadesPorEstadisticaMap.get(i);
					if (comunidadYValor == null || comunidadYValor.isEmpty()) {
						Map<String,Integer> comunidadYValorAux = new TreeMap<String,Integer>();
						comunidadYValorAux.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValorAux);
					} else {
						comunidadYValor.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValor);
					}
					if(AgregaPropertiesImpl.getInstance().getProperty(
							AgregaProperties.NODO).equals((String)estadistica[1])) {
						locales[i] += ((Integer)estadistica[2]).intValue();
					}
					i++;
					if (i == NUM_ESTADISTICAS_LICENCIAS) {
						i = 0;
					}
				}
				
				for (int j=0; j<NUM_ESTADISTICAS_LICENCIAS; j++) {
					switch (j) {
						case 0:
							resultadosEstadisticasLicenciasVO.setLicenciaPropietariaLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setLicenciaPropietariaTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaPropietaria(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaPropietariaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 1:
							resultadosEstadisticasLicenciasVO.setLicenciaLibreEUPLLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setLicenciaLibreEUPLTotlaes(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreEUPL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreEUPLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 2:
							resultadosEstadisticasLicenciasVO.setLicenciaLibreGPLLoclaes(locales[j]);
							resultadosEstadisticasLicenciasVO.setLicenciaLibreGPLTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreGPL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreGPLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 3:
							resultadosEstadisticasLicenciasVO.setLicenciaLibreDualLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setLicenciaLibreDualTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreDual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaLibreDualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 4:
							resultadosEstadisticasLicenciasVO.setOtrasLicenciasLibresLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setOtrasLicenciasLibresTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesOtrasLicenciasLibres(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesOtrasLicenciasLibresValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 5:
							resultadosEstadisticasLicenciasVO.setDominioPublicoLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setDominioPublicoTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesDominioPublico(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesDominioPublicoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 6:
							resultadosEstadisticasLicenciasVO.setNoCorrespondeLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setNoCorrespondeTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesNoCorresponde(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesNoCorrespondeValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 7:
							resultadosEstadisticasLicenciasVO.setLicenciaPropietariaIntelectualLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setLicenciaPropietariaIntelectualTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaPropietariaIntelectual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesLicenciaPropietariaIntelectualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 8:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimiento(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 9:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoSinObraDerivadaLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoSinObraDerivadaTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoSinObraDerivada(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoSinObraDerivadaValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 10:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoSinObraDerivadaNoComercialLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoSinObraDerivadaNoComercialTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercial(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 11:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoNoComercialLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoNoComercialTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoNoComercial(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoNoComercialValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 12:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoNoComercialCompartirIgualLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoNoComercialCompartirIgualTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoNoComercialCompartirIgualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 13:
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoCompartirIgualLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setCCRReconocimientoCompartirIgualTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoCompartirIgual(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesCCRReconocimientoCompartirIgualValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 14:
							resultadosEstadisticasLicenciasVO.setGFDLLocales(locales[j]);
							resultadosEstadisticasLicenciasVO.setGFDLTotales(totales[j]);
							resultadosEstadisticasLicenciasVO.setComunidadesGFDL(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasLicenciasVO.setComunidadesGFDLValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;	
						default:
							logger.warn("Nunca debio entrar aquí");
					}
				}
			}
		}
//		logger.debug("handleObtenerEstadisticasLicenciasPorFechas ends");
		return resultadosEstadisticasLicenciasVO;
	}
	
	protected ResultadosEstadisticasOdesVO handleObtenerEstadisticasOdesPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
//		logger.debug("handleObtenerEstadisticasOdesPorFechas begins");
		
		ResultadosEstadisticasOdesVO resultadosEstadisticasOdesVO = new ResultadosEstadisticasOdesVO();
		Map<Integer,Map<String,Integer>> comunidadesPorEstadisticaMap = new HashMap<Integer,Map<String,Integer>>();
		
		int[] totales = new int[NUM_ESTADISTICAS_ODES];
		int[] locales = new int[NUM_ESTADISTICAS_ODES];
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			java.util.Collection estadisticas = this.getEstadisticaDao().obtenerEstadisticasOdesPorFechas(fechaDesde, fechaHasta);
			if(estadisticas == null || estadisticas.isEmpty()) {
				logger.warn("No hay resultados de actividad en la base de datos (estadistica=null)");
			}else if (estadisticas.size()%NUM_ESTADISTICAS_ODES != 0) {
				logger.warn("No se ha recuperado correctamente la cantidad de estadisticas de actividad, o la BBDD esta corrupta");
			} else {
				int i = 0;
				for (Object object : estadisticas) {
					Object[] estadistica = (Object[])object;
					totales[i] += ((Integer)estadistica[2]).intValue();
					Map<String,Integer> comunidadYValor = comunidadesPorEstadisticaMap.get(i);
					if (comunidadYValor == null || comunidadYValor.isEmpty()) {
						Map<String,Integer> comunidadYValorAux = new TreeMap<String,Integer>();
						comunidadYValorAux.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValorAux);
					} else {
						comunidadYValor.put((String)estadistica[1], (Integer)estadistica[2]);
						comunidadesPorEstadisticaMap.put(i, comunidadYValor);
					}
					if(AgregaPropertiesImpl.getInstance().getProperty(
							AgregaProperties.NODO).equals((String)estadistica[1])) {
						locales[i] += ((Integer)estadistica[2]).intValue();
					}
					i++;
					if (i == NUM_ESTADISTICAS_ODES) {
						i = 0;
					}
				}
				
				for (int j=0; j<NUM_ESTADISTICAS_ODES; j++) {
					switch (j) {
						case 0:
							resultadosEstadisticasOdesVO.setNumLocalCursos(locales[j]);
							resultadosEstadisticasOdesVO.setNumTotalCursos(totales[j]);
							resultadosEstadisticasOdesVO.setComunidadesCursos(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasOdesVO.setComunidadesCursosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 1:
							resultadosEstadisticasOdesVO.setNumLocalMediosIntegrados(locales[j]);
							resultadosEstadisticasOdesVO.setNumTotalMediosIntegrados(totales[j]);
							resultadosEstadisticasOdesVO.setComunidadesMediosIntegrados(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasOdesVO.setComunidadesMediosIntegradosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 2:
							resultadosEstadisticasOdesVO.setNumLocalObjetos(locales[j]);
							resultadosEstadisticasOdesVO.setNumTotalObjetos(totales[j]);
							resultadosEstadisticasOdesVO.setComunidadesObjetos(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasOdesVO.setComunidadesObjetosValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 3:
							resultadosEstadisticasOdesVO.setNumLocalObjetosAprendizaje(locales[j]);
							resultadosEstadisticasOdesVO.setNumTotalObjetosAprendizaje(totales[j]);
							resultadosEstadisticasOdesVO.setComunidadesObjAprendizaje(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasOdesVO.setComunidadesObjAprendizajeValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						case 4:
							resultadosEstadisticasOdesVO.setNumLocalSecuencias(locales[j]);
							resultadosEstadisticasOdesVO.setNumTotalSecuencias(totales[j]);
							resultadosEstadisticasOdesVO.setComunidadesSecuencias(extraerComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							resultadosEstadisticasOdesVO.setComunidadesSecuenciasValores(extraerValoresComunidadesConDatos(comunidadesPorEstadisticaMap.get(j)));
							break;
						default:
							logger.warn("Nunca debio entrar aquí");
					}
				}
			}
		}
//		logger.debug("handleObtenerEstadisticasOdesPorFechas ends");
		return resultadosEstadisticasOdesVO;
	}
		
	protected ResultadosEstadisticasTerminosVO handleObtenerEstadisticasTerminosPorFechas(Date fechaDesde, Date fechaHasta) throws Exception {
//		logger.debug("handleObtenerEstadisticasTerminosPorFechas begins");
		ResultadosEstadisticasTerminosVO resultadosEstadisticasTerminosVO = new ResultadosEstadisticasTerminosVO();
		try
		{
		
		if(fechaDesde == null || fechaHasta == null) {
			logger.warn("No se ha introducido bien alguna fecha, fechaDesde = " + fechaDesde + ", fechaHasta = " + fechaHasta);
		} else if(fechaDesde.compareTo(fechaHasta) > 0) {
			logger.warn("Consulta con intervalo de fechas erroneo (desde = " + fechaDesde + ", hasta = " + fechaHasta + ")");
		} else {
			String nodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO);
			java.util.Collection terminosMasBuscados = this.getEstadisticaDao().obtenerEstadisticasTerminosPorFechas(fechaDesde, fechaHasta);
			//java.util.Collection terminosMasBuscados = this.getEstadisticaDao().obtenerEstadisticasTerminosPorFechasYNodo(fechaDesde, fechaHasta, nodo);
			if(terminosMasBuscados == null || terminosMasBuscados.isEmpty()) {
				logger.warn("No se han recuperado correctamente los terminos mas buscados");
			} else {
				String[] terminosMasBuscadoslist = new String[terminosMasBuscados.size()];
				int[] terminosMasBuscadosValores = new int[terminosMasBuscados.size()];
				
				int i = 0;
				
				String[] terminosMasBuscadoslOrdenados = null;
				
				// Obtenemos todos los resultados para ordenarlos
				for (Object object : terminosMasBuscados) {					
						Object[] termino = (Object[])object;
						terminosMasBuscadoslist[i] = ((String)termino[0]);
						terminosMasBuscadosValores[i] = ((Integer)termino[1]).intValue();						
						i++;
				}
				
				terminosMasBuscadoslOrdenados = ordenaDescendente(terminosMasBuscadoslist, terminosMasBuscadosValores, 5);				
				
				// Creamos los arrays de más buscados porque el anterior devuelve 5 string cantidad_termino
				String[] aTerminos=new String[5];
				int[] aValoresTerminos=new int[5];
				
				for (int j = 0; j < terminosMasBuscadoslOrdenados.length; j++) {					
					String sValorTermino = terminosMasBuscadoslOrdenados[j];					
					StringTokenizer stValorTermino = new StringTokenizer(sValorTermino,"_");
					aValoresTerminos[j]=Integer.parseInt(stValorTermino.nextToken());
					aTerminos[j]=stValorTermino.nextToken();					
				}
				
				resultadosEstadisticasTerminosVO.setTerminosMasBuscadosTotales(aTerminos);
				resultadosEstadisticasTerminosVO.setTerminosMasBuscadosNumTotales(aValoresTerminos);
				
				String[] listaComunidades = null;
				int[] listaValoresPorComunidad = null;
				int j = 0;

				java.util.Collection<String> listaNodosEstadisticas = getEstadisticaDao().obtenerNodosConEstadisticas(fechaDesde, fechaHasta);

				String[] listaNodos = new String[listaNodosEstadisticas.size()];
				int iNodo = 0;
				for (Iterator<String> iterator = listaNodosEstadisticas.iterator(); iterator
						.hasNext();) {					
					listaNodos[iNodo]= iterator.next();
					iNodo++;
				}
		
				
				
				// Obtenemos los datos para cada uno de los 5 más buscados
				java.util.Collection comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta,aTerminos[0]);
						
				listaComunidades = new String[comunidades.size()];
				listaValoresPorComunidad = new int [comunidades.size()];
												
				for (Object object2 : comunidades) {
					Object[] comunidad = (Object[])object2;
					listaComunidades[j] = ((String)comunidad[0]);
					listaValoresPorComunidad[j] = ((Integer)comunidad[1]).intValue();
					j++;
				}
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalPrimero(listaNodos);
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalPrimeroValores(obtenerArrayDatos(listaNodos,listaComunidades,listaValoresPorComunidad));

				// Segundo termino más buscado
				comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta, aTerminos[1]);
				listaComunidades = new String[comunidades.size()];
				listaValoresPorComunidad = new int [comunidades.size()];
				j = 0;
				for (Object object2 : comunidades) {
					Object[] comunidad = (Object[])object2;
					listaComunidades[j] = ((String)comunidad[0]);
					listaValoresPorComunidad[j] = ((Integer)comunidad[1]).intValue();
					j++;
				}
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalSegundo(listaNodos);
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalSegundoValores(obtenerArrayDatos(listaNodos,listaComunidades,listaValoresPorComunidad));

				// Tercero termino más buscado
				comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta, aTerminos[2]);
				
				listaComunidades = new String[comunidades.size()];
				listaValoresPorComunidad = new int [comunidades.size()];
				j = 0;								
				for (Object object2 : comunidades) {
					Object[] comunidad = (Object[])object2;
					listaComunidades[j] = ((String)comunidad[0]);
					listaValoresPorComunidad[j] = ((Integer)comunidad[1]).intValue();
					j++;
				}
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalTercero(listaNodos);
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalTerceroValores(obtenerArrayDatos(listaNodos,listaComunidades,listaValoresPorComunidad));

				// Cuarto termino más buscado
				comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta, aTerminos[3]);
				
				listaComunidades = new String[comunidades.size()];
				listaValoresPorComunidad = new int [comunidades.size()];
				j = 0;								
				for (Object object2 : comunidades) {
					Object[] comunidad = (Object[])object2;
					listaComunidades[j] = ((String)comunidad[0]);
					listaValoresPorComunidad[j] = ((Integer)comunidad[1]).intValue();
					j++;
				}
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalCuarto(listaNodos);
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalCuartoValores(obtenerArrayDatos(listaNodos,listaComunidades,listaValoresPorComunidad));

				// Quinto termino más buscado
				comunidades = this.getEstadisticaDao().obtenerComunidadesTerminosMasBuscados(fechaDesde, fechaHasta, aTerminos[4]);
				
				listaComunidades = new String[comunidades.size()];
				listaValoresPorComunidad = new int [comunidades.size()];
				j = 0;								
				for (Object object2 : comunidades) {
					Object[] comunidad = (Object[])object2;
					listaComunidades[j] = ((String)comunidad[0]);
					listaValoresPorComunidad[j] = ((Integer)comunidad[1]).intValue();
					j++;
				}
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalQuinto(listaNodos);
				resultadosEstadisticasTerminosVO.setComunidadesMasBuscadoTotalQuintoValores(obtenerArrayDatos(listaNodos,listaComunidades,listaValoresPorComunidad));


			}
			java.util.Collection terminosMasBuscadosLocales = this.getEstadisticaDao().obtenerEstadisticasTerminosPorFechasYNodo(fechaDesde, fechaHasta, nodo);
			
			if(terminosMasBuscadosLocales == null || terminosMasBuscadosLocales.isEmpty()) {
				logger.warn("No se han recuperado correctamente los terminos mas buscados locales");
			} else {
				String[] terminosMasBuscadosLocaleslist = new String[NUM_ESTADISTICAS_TERMINOS];
				int[] terminosMasBuscadosLocalesValores = new int[NUM_ESTADISTICAS_TERMINOS];
				
				int i = 0;
				for (Object object : terminosMasBuscadosLocales) {
					if (i < NUM_ESTADISTICAS_TERMINOS) {
						Object[] termino = (Object[])object;
						terminosMasBuscadosLocaleslist[i] = ((String)termino[0]);
						terminosMasBuscadosLocalesValores[i] = ((Integer)termino[1]).intValue();
						i++;
					} else {
						break;
					}
				}
				resultadosEstadisticasTerminosVO.setTerminosMasBuscadosLocales(terminosMasBuscadosLocaleslist);
				resultadosEstadisticasTerminosVO.setTerminosMasBuscadosNumLocales(terminosMasBuscadosLocalesValores);
			}
		}
		}catch (Exception e) {
			logger.error("handleObtenerEstadisticasTerminosPorFechas error al obtener los términos :" ,e);
			throw e;
		}
//		logger.debug("handleObtenerEstadisticasTerminosPorFechas ends");
		return resultadosEstadisticasTerminosVO;
	}
		
	protected String handleObtenerFicheroExcelEstadisticas(){ 

//		logger.debug("handleObtenerFicheroExcelEstadisticas inicio");
		
		String pathFicGenerado = "";
		
		try {
			
			// Se crea el libro
			HSSFWorkbook libro = new HSSFWorkbook();
	
			// Se crea una hoja dentro del libro
			HSSFSheet hoja = libro.createSheet();
	
			HSSFRow fila = null;
			HSSFCell celda = null;
	
			Unmarshaller unmarshaller = new Unmarshaller(EstadisticasAgrega.class);
			unmarshaller.setValidation(false);
	
			File fichero = new File(AgregaPropertiesImpl.getInstance().getProperty(
					AgregaProperties.PATH_ESTADISTICAS)
					+ "//"
					+ NOMBRE_FICHERO_ESTADISTICAS_TOTALES
					+ EXTENSION_FICHERO_ESTADISTICAS);
	
			logger.debug("Obtenemos fichero de estadísticas : " + fichero.getAbsolutePath());
			
			FileInputStream fis = null;
			InputStreamReader isr = null;
			EstadisticasAgrega estAgrega = null;


			fis = new FileInputStream(fichero);
			isr = new InputStreamReader(fis);
			estAgrega = (EstadisticasAgrega) unmarshaller.unmarshal(isr);

			short numFila = 0;
			short numColumna = 0;
			short tamFila0 = 10000;
			short tamRestoFilas = 2500;

			// Creamos la fila de encabezado
			fila = hoja.createRow(numFila);

			// La primera columna es la descripción de la estadística. La
			// creamos fija
			celda = fila.createCell((short) numColumna);
			celda.setCellValue("DESCRIPCION");

			hoja.setColumnWidth(numColumna, tamFila0);
			
			numColumna++;

			// Creamos una columna vacia para mostrar en ella los términos más buscados
			celda = fila.createCell((short) numColumna);
			celda.setCellValue("");
			
			hoja.setColumnWidth(numColumna, tamFila0);
			numColumna++;

			// Obtenemos los nombres de los nodos para el encabezado del resto
			// de columnas. Los obtenemos de la primera estadística
			NodosEstadisticaTotal estadNodos = estAgrega.getEstadistica()[0]
					.getNodosEstadisticaTotal();

			for (NodoEstadistica nodoEstadistica : estadNodos
					.getNodoEstadistica()) {
				
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(nodoEstadistica.getNombreNodoEstadistica());
				numColumna++;

			}


			numFila++;
			
			for (Estadistica estadistica : estAgrega.getEstadistica()) {

				int iCodEst = Integer.parseInt(estadistica.getCodigo());
				if ((iCodEst > 26 && iCodEst < 52) || iCodEst < 5) {					
					logger.debug("Estadistica : " + estadistica.getDescripcion());
					numColumna = 0;
					fila = hoja.createRow(numFila);
					// Se crea la celda de la descripcion					
					celda = fila.createCell((short) numColumna);
					// Si es >30 es estadística de términos más buscados, se deja vacia esta columna y se mete el término en la siguiente
					
					celda.setCellValue(props.getProperty("fichero.descarga.literal" + estadistica.getCodigo()));
					
					numColumna++;

					// Se crea la celda del término de búsqueda					
					celda = fila.createCell((short) numColumna);
					// Si es >30 es estadística de términos más buscados, se mete el término, sino se deja vacia
					if (iCodEst > 31) 
						celda.setCellValue(estadistica.getDescripcion());
					else
						celda.setCellValue("");

					
					numColumna++;
					
					estadNodos = estadistica.getNodosEstadisticaTotal();
					if (estadNodos != null) {
						
						for (NodoEstadistica nodoEstadistica : estadNodos
								.getNodoEstadistica()) {
							celda = fila.createCell((short) numColumna);
							celda.setCellValue(Integer.parseInt(nodoEstadistica
									.getValorNodoEstadistica()));
							numColumna++;

						}
					}

					//hoja.setColumnWidth(numFila, tamRestoFilas);
					numFila++;
				}
			}
			
			pathFicGenerado = AgregaPropertiesImpl.getInstance().getProperty(
					AgregaProperties.PATH_ESTADISTICAS)
					+ "//"
					+ "estadisticas.xls";
			
			logger.debug("Va a generar : " + pathFicGenerado);
			
			FileOutputStream ficSalida = new FileOutputStream(pathFicGenerado);
			libro.write(ficSalida);
			ficSalida.close();
									
			logger.debug("Lo ha generado");
			
		} catch (Exception e) {
			logger.warn("Error al obtener el fichero excel de estadisticas : " + e.getMessage());
			logger.debug("",e);
		}

//		logger.info("handleObtenerFicheroExcelEstadisticas fin");
		return pathFicGenerado.substring(pathFicGenerado.indexOf("/estadisticas"));
		
	}
	
	protected  java.lang.String handleObtenerFicheroExcelEstadisticasPorFechas(java.lang.String fechaHastaTerminos, java.lang.String fechaHastaOdes, java.lang.String fechaHastaLicencias, java.lang.String fechaHastaCoberturaCurricular, java.lang.String fechaHastaActividad, java.lang.String fechaDesdeTerminos, java.lang.String fechaDesdeOdes, java.lang.String fechaDesdeLicencias, java.lang.String fechaDesdeCoberturaCurricular, java.lang.String fechaDesdeActividad, int bloqueEstadistica) {		       

		if (logger.isDebugEnabled())
			logger.debug("handleObtenerFicheroExcelEstadisticasPorFechas inicio");		
		
		String pathFicGenerado = "";
		
		try {
			
			Date fecDefectoDesde = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2007");
			Date fecDefectoHasta = new Date();

			// Obtenemos los diferentes grupos de estadísticas
			ResultadosEstadisticasCoberturaCurricularVO estCobertura = handleObtenerEstadisticasCoberturaCurricularPorFechas(tranformarFecha(fechaDesdeCoberturaCurricular,fecDefectoDesde),tranformarFecha(fechaHastaCoberturaCurricular,fecDefectoHasta));
			ResultadosEstadisticasLicenciasVO estLicencias = handleObtenerEstadisticasLicenciasPorFechas(tranformarFecha(fechaDesdeLicencias,fecDefectoDesde),tranformarFecha(fechaHastaLicencias,fecDefectoHasta));			
			ResultadosEstadisticasOdesVO estOdes =  handleObtenerEstadisticasOdesPorFechas(tranformarFecha(fechaDesdeOdes,fecDefectoDesde), tranformarFecha(fechaHastaOdes,fecDefectoHasta));
			ResultadosEstadisticasActividadVO estActividad =  handleObtenerEstadisticasActividadPorFechas(tranformarFecha(fechaDesdeActividad,fecDefectoDesde), tranformarFecha(fechaHastaActividad,fecDefectoHasta));
			ResultadosEstadisticasTerminosVO estTerminos = handleObtenerEstadisticasTerminosPorFechas(tranformarFecha(fechaDesdeTerminos,fecDefectoDesde),tranformarFecha(fechaHastaTerminos,fecDefectoHasta));

			if (logger.isDebugEnabled())
				logger.debug("handleObtenerFicheroExcelEstadisticasPorFechas tenemos estadísticas");
			

			
			// Se crea el libro
			HSSFWorkbook libro = new HSSFWorkbook();

			// Se incluyen las hojas del libro
			crearHojaActividad(libro, estActividad,tranformarFecha(fechaDesdeActividad,fecDefectoDesde), tranformarFecha(fechaHastaActividad,fecDefectoHasta));
			crearHojaCoberturaCurricular(libro, estCobertura,tranformarFecha(fechaDesdeCoberturaCurricular,fecDefectoDesde), tranformarFecha(fechaHastaCoberturaCurricular,fecDefectoHasta));
			crearHojaODEs(libro, estOdes,tranformarFecha(fechaDesdeOdes,fecDefectoDesde), tranformarFecha(fechaHastaOdes,fecDefectoHasta));
			crearHojaLicencias(libro, estLicencias,tranformarFecha(fechaDesdeLicencias,fecDefectoDesde), tranformarFecha(fechaHastaLicencias,fecDefectoHasta));
			crearHojaTerminos(libro, estTerminos,tranformarFecha(fechaDesdeTerminos,fecDefectoDesde), tranformarFecha(fechaHastaTerminos,fecDefectoHasta));

					
			pathFicGenerado = AgregaPropertiesImpl.getInstance().getProperty(
					AgregaProperties.PATH_ESTADISTICAS)
					+ "//"
					+ "estadisticasAgrega.xls";
			
			if (logger.isDebugEnabled())
				logger.debug("handleObtenerFicheroExcelEstadisticasPorFechas vamos a generar el fichero : " + pathFicGenerado);
			
			FileOutputStream ficSalida = new FileOutputStream(pathFicGenerado);
			libro.write(ficSalida);
			ficSalida.close();												
			
		} catch (Exception e) {
			logger.warn("Error al obtener el fichero excel de estadisticas : " + e.getMessage());
			logger.debug("",e);
		}

		return pathFicGenerado.substring(pathFicGenerado.indexOf("/estadisticas"));
		
	}

	private void crearHojaLicencias(HSSFWorkbook libro,ResultadosEstadisticasLicenciasVO estLicencias, Date fechaDesde, Date fechaHasta )
	{
		// Se crea una hoja dentro del libro
		HSSFSheet hoja = libro.createSheet("Licencias");
				

		HSSFRow fila = null;
		HSSFCell celda = null;	
		
		short numFila = 0;
		short numColumna = 0;
		short tamFila0 = 10000;		

		// Creamos la fila de fecha DESDE
		fila = hoja.createRow(numFila);
		
		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA DESDE :    "+obtenerFechaTexto(fechaDesde));
		
		numFila++;
		// Creamos la fila de fecha HASTA
		fila = hoja.createRow(numFila);

		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA HASTA :    "+obtenerFechaTexto(fechaHasta));
		
		numFila++;
		numFila++;
		numFila++;

		// Creamos la fila de encabezado
		fila = hoja.createRow(numFila);

		// La primera columna es la descripción de la estadística. La
		// creamos fija
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("DESCRIPCION");

		hoja.setColumnWidth(numColumna, tamFila0);						
		
		numColumna++;
					
		// Obtenemos los nombres de los nodos para el encabezado del resto de columnas. 		
		for (int i = 0; i < estLicencias.getComunidadesCCRReconocimiento().length; i++) {
			String comunidad = estLicencias.getComunidadesCCRReconocimiento()[i];
			if (estLicencias.getComunidadesCCRReconocimiento()[i]!=null && !estLicencias.getComunidadesCCRReconocimiento()[i].equals(""))
			{
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(comunidad);
				numColumna++;
			}
		}
		
		int nNumCCAAs =numColumna;
		
		// Creamos la columna del total
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("TOTAL");
		
		numFila++;

		// Fila de LicenciasPropietaria
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasPropietaria"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesLicenciaPropietariaValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasEUPL
		fila = hoja.createRow(numFila);	
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasEUPL"));		
		rellenarDatosCCAA(  fila, estLicencias.getComunidadesLicenciaLibreEUPLValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasGPL
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasGPL"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesLicenciaLibreGPLValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasDual
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasDual"));
		rellenarDatosCCAA( fila,estLicencias.getComunidadesLicenciaLibreDualValores(),nNumCCAAs);
		numFila++;
		
		// Fila de LicenciasOtrasLicenciasLibres
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasOtrasLicenciasLibres"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesOtrasLicenciasLibresValores(),nNumCCAAs);
		numFila++;
		
		// Fila de LicenciasDomPublico
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasDomPublico"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesDominioPublicoValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasNoCorresponde
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasNoCorresponde"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesNoCorrespondeValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasPropietariaIntelec
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasPropietariaIntelec"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesLicenciaPropietariaIntelectualValores(),nNumCCAAs);
		numFila++;
		
		// Fila de LicenciasCCReconocimiento
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCReconocimiento"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoValores(),nNumCCAAs);
		numFila++;
		
		// Fila de LicenciasCCReconSinObraDer
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCReconSinObraDer"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoSinObraDerivadaValores(),nNumCCAAs);
		numFila++;
		
		// Fila de profesional
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCReconSinObraDerNoCom"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasCCReconNoCom
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCReconNoCom"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoNoComercialValores(),nNumCCAAs);
		numFila++;
		
		// Fila de LicenciasCCRecNoComCompIgua
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCRecNoComCompIgual"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoNoComercialCompartirIgualValores(),nNumCCAAs);
		numFila++;

		// Fila de LicenciasCCRecCompIgual
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasCCRecCompIgual"));
		rellenarDatosCCAA( fila, estLicencias.getComunidadesCCRReconocimientoCompartirIgualValores(),nNumCCAAs);
		numFila++;

		// LicenciasGFDL
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLicenciasGFDL"));				
		rellenarDatosCCAA( fila, estLicencias.getComunidadesGFDLValores(),nNumCCAAs);
		numFila++;
		
		// Fila de Totales
		fila = hoja.createRow(numFila);		
		fila = hoja.createRow(numFila+1);
		celda = fila.createCell((short) 0);
		celda.setCellValue("Totales");		
		rellenarFilaTotales(fila, estLicencias.getComunidadesGFDLValores(), nNumCCAAs, 15);

	}
	
	private void crearHojaTerminos(HSSFWorkbook libro,ResultadosEstadisticasTerminosVO estTerminos, Date fechaDesde, Date fechaHasta )
	{
		// Se crea una hoja dentro del libro
		HSSFSheet hoja = libro.createSheet("Términos");
				

		HSSFRow fila = null;
		HSSFCell celda = null;	
		
		short numFila = 0;
		short numColumna = 0;
		short tamFila0 = 10000;		

		// Creamos la fila de fecha DESDE
		fila = hoja.createRow(numFila);
		
		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA DESDE :    "+obtenerFechaTexto(fechaDesde));
		
		numFila++;
		// Creamos la fila de fecha HASTA
		fila = hoja.createRow(numFila);

		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA HASTA :    "+obtenerFechaTexto(fechaHasta));
		
		numFila++;
		numFila++;
		numFila++;

		// Creamos la fila de encabezado
		fila = hoja.createRow(numFila);

		// La primera columna es la descripción de la estadística. La
		// creamos fija
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("DESCRIPCION");

		hoja.setColumnWidth(numColumna, tamFila0);						
		
		numColumna++;
					
		// Obtenemos los nombres de los nodos para el encabezado del resto de columnas. 		
		for (int i = 0; i < estTerminos.getComunidadesMasBuscadoTotalPrimero().length; i++) {
			String comunidad = estTerminos.getComunidadesMasBuscadoTotalPrimero()[i];
			if (estTerminos.getComunidadesMasBuscadoTotalPrimero()[i]!=null && !estTerminos.getComunidadesMasBuscadoTotalPrimero()[i].equals(""))
			{
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(comunidad);
				numColumna++;
			}
		}
		
		int nNumCCAAs =numColumna;
		
		// Creamos la columna del total
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("TOTAL");
		
		numFila++;

		// Fila de primer término más buscado
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(estTerminos.getTerminosMasBuscadosTotales()[0]);
		rellenarDatosCCAA( fila, estTerminos.getComunidadesMasBuscadoTotalPrimeroValores(),nNumCCAAs);
		numFila++;

		// Fila de segundo término más buscado
		fila = hoja.createRow(numFila);	
		celda = fila.createCell((short) 0);		
		celda.setCellValue(estTerminos.getTerminosMasBuscadosTotales()[1]);		
		rellenarDatosCCAA(  fila, estTerminos.getComunidadesMasBuscadoTotalSegundoValores(),nNumCCAAs);
		numFila++;

		// Fila de tercero término más buscado
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(estTerminos.getTerminosMasBuscadosTotales()[2]);
		rellenarDatosCCAA( fila, estTerminos.getComunidadesMasBuscadoTotalTerceroValores(),nNumCCAAs);
		numFila++;

		// Fila de cuarto término más buscado
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(estTerminos.getTerminosMasBuscadosTotales()[3]);
		rellenarDatosCCAA( fila,estTerminos.getComunidadesMasBuscadoTotalCuartoValores(),nNumCCAAs);
		numFila++;
		
		// Fila de quinto término más buscado
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(estTerminos.getTerminosMasBuscadosTotales()[4]);
		rellenarDatosCCAA( fila, estTerminos.getComunidadesMasBuscadoTotalQuintoValores(),nNumCCAAs);

		numFila++;
		
		// Fila de Totales
		fila = hoja.createRow(numFila);		
		fila = hoja.createRow(numFila+1);
		celda = fila.createCell((short) 0);
		celda.setCellValue("Totales");		
		rellenarFilaTotales(fila, estTerminos.getComunidadesMasBuscadoTotalQuintoValores(), nNumCCAAs, 5);

	}	
	private void crearHojaCoberturaCurricular(HSSFWorkbook libro,ResultadosEstadisticasCoberturaCurricularVO estCobertura, Date fechaDesde, Date fechaHasta)
	{
		// Se crea una hoja dentro del libro
		HSSFSheet hoja = libro.createSheet("Cobertura Curricular");
				

		HSSFRow fila = null;
		HSSFCell celda = null;	
		
		short numFila = 0;
		short numColumna = 0;
		short tamFila0 = 10000;		

		// Creamos la fila de fecha DESDE
		fila = hoja.createRow(numFila);
		
		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA DESDE :    "+obtenerFechaTexto(fechaDesde));
		
		numFila++;
		// Creamos la fila de fecha HASTA
		fila = hoja.createRow(numFila);

		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA HASTA :    "+obtenerFechaTexto(fechaHasta));
		
		numFila++;
		numFila++;
		numFila++;

		// Creamos la fila de encabezado
		fila = hoja.createRow(numFila);

		// La primera columna es la descripción de la estadística. La
		// creamos fija
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("DESCRIPCION");

		hoja.setColumnWidth(numColumna, tamFila0);						
		
		numColumna++;
							
		// Obtenemos los nombres de los nodos para el encabezado del resto de columnas. 		
		for (int i = 0; i < estCobertura.getComunidadesBachillerato().length; i++) {
			String comunidad = estCobertura.getComunidadesBachillerato()[i];
			if (estCobertura.getComunidadesBachillerato()[i]!=null && !estCobertura.getComunidadesBachillerato()[i].equals(""))
			{
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(comunidad);
				numColumna++;
			}
		}
		
		int nNumCCAAs =numColumna;
		
		
		// Creamos la columna del total
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("TOTAL");
		
		numFila++;
			
		// Fila de infantil
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularInfantil"));
		rellenarDatosCCAA( fila,estCobertura.getComunidadesEducacionInfantilValores(),nNumCCAAs);
		numFila++;

		// Fila de primaria
		fila = hoja.createRow(numFila);	
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularPrimaria"));		
		rellenarDatosCCAA(  fila, estCobertura.getComunidadesEducacionPrimariaValores(),nNumCCAAs);
		numFila++;

		// Fila de sec Obligatoria
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularObligatoria"));
		rellenarDatosCCAA( fila, estCobertura.getComunidadesEduSecundariaObligatoriaValores(),nNumCCAAs);
		numFila++;

		// Creamos la fila bachillerato
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularBachillerato"));				
		rellenarDatosCCAA( fila, estCobertura.getComunidadesBachilleratoValores(),nNumCCAAs);
		numFila++;

		// Fila de profesional
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularProfesional"));
		rellenarDatosCCAA( fila, estCobertura.getComunidadesFormacionProfesionalValores(),nNumCCAAs);
		numFila++;
		
		// Fila de artisticas
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularArtisticas"));
		rellenarDatosCCAA( fila, estCobertura.getComunidadesEnsArtisticasValores(),nNumCCAAs);
		numFila++;

		// Fila de idiomas
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCobCurricularIdiomas"));
		rellenarDatosCCAA( fila, estCobertura.getComunidadesEnsOficialIdiomasValores(),nNumCCAAs);
		numFila++;

		// Fila de Totales
		fila = hoja.createRow(numFila);		
		fila = hoja.createRow(numFila+1);
		celda = fila.createCell((short) 0);
		celda.setCellValue("Totales");		
		rellenarFilaTotales(fila, estCobertura.getComunidadesEducacionInfantilValores(), nNumCCAAs, 6);

	}
	
	private void crearHojaActividad(HSSFWorkbook libro,ResultadosEstadisticasActividadVO estActividad,Date fechaDesde, Date fechaHasta)
	{
		// Se crea una hoja dentro del libro
		HSSFSheet hoja = libro.createSheet("Actividad");
				

		HSSFRow fila = null;
		HSSFCell celda = null;	
		
		short numFila = 0;
		short numColumna = 0;
		short tamFila0 = 10000;		

		// Creamos la fila de fecha DESDE
		fila = hoja.createRow(numFila);
		
		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA DESDE :    "+obtenerFechaTexto(fechaDesde));
		
		numFila++;
		// Creamos la fila de fecha HASTA
		fila = hoja.createRow(numFila);

		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA HASTA :    "+obtenerFechaTexto(fechaHasta));
		
		numFila++;
		numFila++;
		numFila++;

		// Creamos la fila de encabezado
		fila = hoja.createRow(numFila);

		// La primera columna es la descripción de la estadística. La
		// creamos fija
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("DESCRIPCION");

		hoja.setColumnWidth(numColumna, tamFila0);						
		
		numColumna++;

		// Obtenemos los nombres de los nodos para el encabezado del resto de columnas. 		
		for (int i = 0; i < estActividad.getComunidadesBusquedas().length; i++) {
			String comunidad = estActividad.getComunidadesBusquedas()[i];
			if (estActividad.getComunidadesBusquedas()[i]!=null && !estActividad.getComunidadesBusquedas()[i].equals(""))
			{
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(comunidad);
				numColumna++;
			}
		}
		
		int nNumCCAAs =numColumna;
						
		// Creamos la columna del total
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("TOTAL");
		
		numFila++;
		
		// Creamos la fila para cursos
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalBusquedas"));				
		rellenarDatosCCAA( fila, estActividad.getComunidadesBusquedasValores(),nNumCCAAs);
		numFila++;

		// Fila de secuancias dinámicas
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalDescargas"));
		rellenarDatosCCAA( fila, estActividad.getComunidadesDescargasValores(),nNumCCAAs);
		numFila++;

		// Fila de objetos aprendizaje
		fila = hoja.createRow(numFila);	
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalFichasAccedidas"));		
		rellenarDatosCCAA(  fila, estActividad.getComunidadesFichasAccedidasValores(),nNumCCAAs);
		numFila++;

		// Fila de medios integrados
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalPrevisualizaciones"));
		rellenarDatosCCAA( fila, estActividad.getComunidadesOdesPrevisualizadosValores(),nNumCCAAs);
			
		numFila++;
		// Fila de Totales
		fila = hoja.createRow(numFila);		
		fila = hoja.createRow(numFila+1);
		celda = fila.createCell((short) 0);
		celda.setCellValue("Totales");		
		rellenarFilaTotales(fila, estActividad.getComunidadesOdesPrevisualizadosValores(), nNumCCAAs, 4);
		
		numFila++;
		numFila++;
		numFila++;
		// Fila leyenda de datos no actualizados		
		fila = hoja.createRow(numFila);
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalLeyendaDatosNoActualizados"));				

	}
	
	private void crearHojaODEs(HSSFWorkbook libro,ResultadosEstadisticasOdesVO estOdes,Date fechaDesde, Date fechaHasta)
	{
		// Se crea una hoja dentro del libro
		HSSFSheet hoja = libro.createSheet("ODES");
				

		HSSFRow fila = null;
		HSSFCell celda = null;	
		
		short numFila = 0;
		short numColumna = 0;
		short tamFila0 = 10000;		
		
		// Creamos la fila de fecha DESDE
		fila = hoja.createRow(numFila);
		
		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA DESDE :    "+obtenerFechaTexto(fechaDesde));
		
		numFila++;
		// Creamos la fila de fecha HASTA
		fila = hoja.createRow(numFila);

		celda = fila.createCell((short) 0);
		celda.setCellValue("FECHA HASTA :    "+obtenerFechaTexto(fechaHasta));
		
		numFila++;
		numFila++;
		numFila++;

		// Creamos la fila de encabezado
		fila = hoja.createRow(numFila);

		// La primera columna es la descripción de la estadística. La
		// creamos fija
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("DESCRIPCION");

		hoja.setColumnWidth(numColumna, tamFila0);						
		
		numColumna++;
	
		// Obtenemos los nombres de los nodos para el encabezado del resto de columnas. 		
		for (int i = 0; i < estOdes.getComunidadesObjetos().length; i++) {
			String comunidad = estOdes.getComunidadesObjetos()[i];
			if (estOdes.getComunidadesObjetos()[i]!=null && !estOdes.getComunidadesObjetos()[i].equals(""))
			{
				celda = fila.createCell((short) numColumna);
				celda.setCellValue(comunidad);
				numColumna++;
			}
		}
		
		int nNumCCAAs =numColumna;	
		
		// Creamos la columna del total
		celda = fila.createCell((short) numColumna);
		celda.setCellValue("TOTAL");
		
		numFila++;
		
		// Fila de medios integrados
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalMediosIntegrados"));
		rellenarDatosCCAA( fila, estOdes.getComunidadesMediosIntegradosValores(),nNumCCAAs);
		numFila++;

		// Fila de objetos aprendizaje
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalObjetosAprendizaje"));		
		rellenarDatosCCAA(  fila, estOdes.getComunidadesObjAprendizajeValores(),nNumCCAAs);
		numFila++;
		

		// Fila de secuancias didacticas
		fila = hoja.createRow(numFila);		
		celda = fila.createCell((short) 0);		
		celda.setCellValue(props.getProperty("fichero.descarga.literalSecuenciasDidacticas"));
		rellenarDatosCCAA( fila, estOdes.getComunidadesSecuenciasValores(),nNumCCAAs);
		numFila++;

		// Creamos la fila para cursos
		fila = hoja.createRow(numFila);
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalCursos"));				
		rellenarDatosCCAA( fila, estOdes.getComunidadesCursosValores(),nNumCCAAs);
		numFila++;

		numFila++;
		// Creamos la fila para Secuencias
		fila = hoja.createRow(numFila);				
		celda = fila.createCell((short) 0);
		celda.setCellValue(props.getProperty("fichero.descarga.literalObjetos"));
		rellenarDatosCCAA( fila, estOdes.getComunidadesObjetosValores(),nNumCCAAs);					

	}
	
	private void rellenarDatosCCAA(HSSFRow fila,int[] estadNodos, int numCCAAs)
	{				
		HSSFCell celda = null;	
		int numColumna = 1;
		
		int nTotal=0;
		// Se meten datos de comunidades
		for (int j = 0; ((j < estadNodos.length) && (j<numCCAAs-1)); j++) {				
			celda = fila.createCell((short) numColumna);
			celda.setCellValue(estadNodos[j]);
			nTotal+=estadNodos[j];
			numColumna++;					
		}
		celda = fila.createCell((short) numColumna);
		celda.setCellValue(nTotal);
		
	
	}

	private void rellenarFilaTotales(HSSFRow fila,int[] estadNodos, int numCCAAs, int numFilas)
	{				
		HSSFCell celda = null;	
		int numColumna = 1;
		int nUltimaFila = numFilas + 6;
		
		int nTotal=0;
		// Se meten datos de comunidades
		for (int j = 0; ((j < estadNodos.length) && (j<numCCAAs-1)); j++) {				
			celda = fila.createCell((short) numColumna);
			celda.setCellValue(estadNodos[j]);
			celda.setCellFormula("SUM("+COLUMNAS_EXCEL[numColumna]+"6:"+COLUMNAS_EXCEL[numColumna]+nUltimaFila+")");
			numColumna++;					
		}
		celda = fila.createCell((short) numColumna);		
		celda.setCellFormula("SUM("+COLUMNAS_EXCEL[numColumna]+"6:"+COLUMNAS_EXCEL[numColumna]+nUltimaFila+")");
		
	
	}
	private int[] extraerValoresComunidadesConDatos(Map<String, Integer> map) {
		int[] valores = new int[map.size()];
		int i = 0;
		for (String comunidad : map.keySet()) {
			valores[i] = map.get(comunidad);
			i++;
		}
		return valores;
	}
	
	private String[] extraerComunidadesConDatos(Map<String, Integer> map) {

		String[] comunidades = new String[map.size()];
		int i = 0;
		
		for (String comunidad : map.keySet()) {
			comunidades[i] = comunidad;
			i++;
		}		
		return comunidades;
	}	
	
	private Date tranformarFecha(String sFecEntrada, Date fecDefecto) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Date fechaSalida = null;
		try {
			if (sFecEntrada!=null && !sFecEntrada.equals(""))
			fechaSalida = sdf.parse(sFecEntrada);			
			else
				fechaSalida=fecDefecto;
			
		} catch (Exception ex) {
			logger.warn("Error al obtener fecha para estadísticas. Fec entrada :" + sFecEntrada + ". Error : " + ex.getMessage());
			logger.debug("",ex);
		}
			
		return fechaSalida;
	}

	private String obtenerFechaTexto(Date fecEntrada) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		String sFechaSalida = "";
		
		try {
			if (fecEntrada!=null)
				sFechaSalida = sdf.format(fecEntrada);			
			
		} catch (Exception ex) {
			logger.warn("Error al obtener fecha en formato texto para estadísticas. Fec entrada :" + fecEntrada + ". Error : " + ex.getMessage());
			logger.debug("",ex);
		}

		return sFechaSalida;
	}
	
	private int[] obtenerArrayDatos(String[] listaComunidades, String[] listaNodos, int[] listaDatos)
	{
		int[] listaSalida = new int[listaComunidades.length];
				
		Hashtable<String, Integer> htDatos= new Hashtable<String, Integer>();
		
		for (int i = 0; i < listaComunidades.length; i++) {
			htDatos.put(listaComunidades[i], 0);
		}
		

		for (int i = 0; i < listaNodos.length; i++) {
			String nodo = listaNodos[i];
			htDatos.put(nodo, listaDatos[i]);			
		}
		
		for (int i = 0; i < listaComunidades.length; i++) {
			listaSalida[i]=htDatos.get(listaComunidades[i]);
		}		
		
		return listaSalida;
	}
	
	
	/**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return InformeMasVO[] Array con los Value Object ordenados
	 */
	private String[] ordenaDescendente(String[] terminosMasBuscadoslist ,int[] terminosMasBuscadosValores , int numResultados)
	{
		if(logger.isDebugEnabled())logger.debug("OrdenaDescendente.Inicio Num Resultados :" + numResultados);
		
		SortedMap<String, String> mTerminos = new TreeMap<String, String>(java.util.Collections.reverseOrder());

		for (int i = 0; i < terminosMasBuscadoslist.length; i++) {			
			mTerminos.put(rellenarCeros(terminosMasBuscadosValores[i]+"")+"_"+terminosMasBuscadoslist[i], terminosMasBuscadoslist[i]);
		}
		
		if(logger.isDebugEnabled())logger.debug("NumElementos totales : " + mTerminos.size());
		
		Iterator<String> iterator = mTerminos.keySet().iterator();
		int cont =0;
						
		int nElementos = numResultados;
		if (mTerminos.size()<nElementos)
			nElementos=mTerminos.size();
		
		String[] aSalida= new String[nElementos];
		
		if(logger.isDebugEnabled())logger.debug("Obtenemos los : " +numResultados + " máximos");
		while (iterator.hasNext() && cont < numResultados) {
			
			String key = iterator.next();
			
			aSalida[cont]= key;
		
			cont ++;
		}
		if(logger.isDebugEnabled())logger.debug("OrdenaDescendente.Final");		
		return aSalida;
	}	

	/**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return InformeMasVO[] Array con los Value Object ordenados
	 */
	private String rellenarCeros(String veces)
	{	
		String salida="";
		
		for (int i = 0; i < (8 - veces.length()); i++) {
			salida=salida.concat("0");
		}

		salida=salida.concat(veces);
		
		return salida;
	}
}
