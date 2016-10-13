package es.agrega.soporte.sitemapProperties;

public interface SitemapProperties 
{
	public static final String PROPERTIES_FILE_NAME = "generacionContenidos"; 

	
	public static final String PERIODICIDAD 									= "periodicidad";
	
	public static final String NUMERO_ENTRADAS_XML 								= "numeroEntradas";
	
	public static final String NOMBRE_FICHERO_SITEMAP 							= "ficheroSiteMap";
	public static final String NOMBRE_FICHERO_SITEMAP_INDEX 					= "ficheroSiteMapIndex";
	public static final String EXTENSION_FICHEROS_SITEMAP 						= "extensionFicheroSiteMap";
	
	public static final String ESQUEMA_SITEMAP									= "schemaSitemap";
	public static final String ESQUEMA_SITEMAP_XSI								= "schemaSitemapXsi";
	public static final String ESQUEMA_SITEMAP_LOCATION							= "schemaSitemapLocation";
	 
	public static final String DIRECTORIO_FICHEROS_SITEMAP						= "urlSiteMap";
	public static final String DIRECTORIO_FICHEROS_BACKUP_SITEMAP				= "urlSiteMapBackup";
	public static final String DIRECTORIO_FICHEROS_SITEMAP_PORTADA				= "urlSiteMapPortada";
	
	public static final String PROTOCOLO_HTTP									= "protocoloHttp";
	public static final String URL_FICHA_ODE									= "buscadorFicha";
	public static final String SEPARADOR										= "separador";
		
	public static final String NIVEL_AGREGACION_1								= "1";
	public static final String NIVEL_AGREGACION_2								= "2";
	public static final String NIVEL_AGREGACION_3								= "3";
	public static final String NIVEL_AGREGACION_4								= "4";
	
	public static final String HORATAREA 										= "horaTarea";
	public static final String MINUTOTAREA 										= "minutoTarea";
	public static final String SEGUNDOTAREA 									= "segundoTarea";
	
	public static final String HORATAREAODE 									= "horaTareaOde";
	public static final String MINUTOTAREAODE									= "minutoTareaOde";
	public static final String SEGUNDOTAREAODE									= "segundoTareaOde";
	public static final String PERIODICIDADODE									= "periocidadOdePortada";
	public static final String PERIODICIDADTAREA								= "periodicidadTarea";
	
	public static final String ESQUEMA_SITEMAP_INDEX							= "schemaSitemapIndex";
	public static final String ESQUEMA_SITEMAP_XSI_INDEX						= "schemaSitemapIndexXsi";
	public static final String ESQUEMA_SITEMAP_LOCATION_INDEX					= "schemaSitemapIndexLocation";
	
	
	
	
	
	
	public String getProperty(String sKey);
	
	

}
