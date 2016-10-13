package es.pode.publicacion.i18n;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceBase;
import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceImpl;
import es.pode.publicacion.negocio.soporte.I18nModuloPublicacion;

public class EstadosI18nManager implements Serializable {
	
    static final Logger logger = Logger.getLogger(EstadosI18nManager.class);	
	
	private static final long serialVersionUID = 1L;
	
	private static final String MILISEGUNDOS_CADUCA_CACHE_KEY = "idioma.cache.milisegundos.caducidad";
	
	private static long MILISEGUNDOS_CADUCA_CACHE_VALUE;
	
	private static String CACHE_NAME = "ESTADOS_CACHE";

	private CacheManager cacheManager;

	private static EstadosI18nManager me;
	
	static {
		try {
			me = new EstadosI18nManager();
			me.initEHCache();			 
			Properties props = new Properties();
			InputStream is = SrvPublicacionServiceBase.class.getResourceAsStream(SrvPublicacionServiceImpl.FILE_NAME_PROPERTIES);
			props.load(is);
			MILISEGUNDOS_CADUCA_CACHE_VALUE = Long.parseLong(props.getProperty(MILISEGUNDOS_CADUCA_CACHE_KEY));
		} catch(Exception e) {
			logger.error("Error inicializando clase de internacionalizacion de estados: "+ e.getMessage(), e);
		}
	}

	private EstadosI18nManager() throws Exception  {
	}

	static public synchronized EstadosI18nManager getInstance() {
		return me;
	}

	public Object getDescription(String code, String idioma) throws Exception {
		return ehCacheGet(generaClaveCache(code, idioma),CACHE_NAME);
	}
	
	/*
	 * Inicializar la cache supone leerse todos los ficheros de bundle del publicador con la informacion de los estados traducidos para todos los
	 * idiomas de la plataforma.
	 * */
	public void initEHCache(){
		try {
			cacheManager = CacheManager.getInstance();
			createCache(CACHE_NAME, MILISEGUNDOS_CADUCA_CACHE_VALUE);

//			Leemos todos los idiomas para los que ha datos traducidos para la plataforma
			String[] listaIdiomasPlataforma = {"es", "ca", "en", "eu","gl","va"};//I18n.getInstance().obtenerIdiomasPlataforma();
			if (listaIdiomasPlataforma == null || listaIdiomasPlataforma.length == 0)
			{
				logger.error("ERROR: Error intentando acceder a la lista de idiomas de la plataforma. Imposible continuar.");
				throw new Exception("ERROR: Error intentando acceder a la lista de idiomas de la plataforma. Imposible continuar.");
			}
//			Para cada idioma nos vamos a su bundle dentro del publicador y buscamos la traduccion del estado de turno al idioma de turno
			String[] listaEstados = SrvPublicacionServiceImpl.SECUENCIA_ESTADOS;
			if (listaEstados == null || listaEstados.length == 0)
			{
				logger.error("ERROR: Error intentando acceder a la lista de estados de los ODEs. Imposible continuar.");
				throw new Exception("ERROR: Error intentando acceder a la lista de estados de los ODEs. Imposible continuar.");
			}
			for (int i = 0; i < listaIdiomasPlataforma.length; i++) {
				for (int j = 0; j < listaEstados.length; j++) {
//					Para cada idioma y cada estado, tengo que acceder al fichero de bundle para tener su traduccion y meterlo en la cache
					String clave = generaClaveCache(listaEstados[j],listaIdiomasPlataforma[i]);// clave 
					String valor = I18nModuloPublicacion.getPropertyValueI18n(listaEstados[j],listaIdiomasPlataforma[i]);// esto me devuelve la etiqueta ya internacionalizada 
					ehCachePut(clave, 
								valor, 
								CACHE_NAME);
					if (logger.isInfoEnabled())
						logger.info("Insertamos en cache el valor: clave["+clave+"] valor["+valor+"]");
				}
			}
		} catch ( CacheException ce ) {
			logger.error(ce.getMessage(), ce);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
	public void createCache(String name, long  ttl) {
		Cache ehCache = new Cache( name,//name
				Integer.MAX_VALUE, //maxElementsInMemory
				true, //overflowToDisk
				true, //eternal
				ttl,//tiempo de vida de los elementos en la cache
				0 );//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
		try {
			cacheManager.addCache( ehCache );

		} catch ( CacheException ce ) {
			logger.error(ce.getMessage(), ce);
		}
	}

	public void ehCachePut(String key, Object object, String cacheName){

		cacheManager.getCache(cacheName).put( new Element( key, (Serializable)object ) );

	}

	public Object ehCacheGet(String key, String cacheName){
		Object result = null;
		try {
			Element element = cacheManager.getCache(cacheName).get( key );
			if( element != null )
				result = element.getValue();

		} catch ( CacheException ce ) {
			logger.error(ce.getMessage(), ce);
		}
		return result;
	}

	/*
	 * Este metodo genera la clave a partir de la cadena del estado y del idioma al que se quiere traducir para
	 * poder buscar la traduccion en la cache
	 * */
	private String generaClaveCache(String estado, String idioma) throws Exception
	{
//		Si lo tengo todo, pues no hay problema
		if (estado != null && !estado.equals("") && idioma != null && !idioma.equals(""))
			return estado+idioma;
//		Si el idioma es nulo o vacio, devuelvo el estado con el idioma por defecto de la plataforma que habra seguro traduccion
		else if (estado != null && !estado.equals("") && (idioma == null || idioma.equals("")))
		{
			String idiomaPorDefecto;
			try {
				idiomaPorDefecto = "es,ca,en,eu,gl,va";//I18n.getInstance().obtenerIdiomaDefectoPlataforma();	
			} catch (Exception e) {
				logger.error("ERROR: Error obteniendo idioma por defecto de la plataforma, no podemos continuar.");
				throw new Exception("ERROR: Error obteniendo idioma por defecto de la plataforma, no podemos continuar.");
			}
			
			return estado+idiomaPorDefecto;
		}
		logger.error("ERROR: Error generando clave para buscar en cache con estado["+estado+"] e idioma["+idioma+"]. No podemos continuar.");
		throw new Exception("ERROR: Error generando clave para buscar en cache con estado["+estado+"] e idioma["+idioma+"]. No podemos continuar.");
	}

}
