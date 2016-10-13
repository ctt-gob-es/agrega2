/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.sitemap.negocio.servicios;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.sitemapProperties.SitemapPropertiesImpl;
import es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


public class SrvSitemapServiceImpl extends es.pode.sitemap.negocio.servicios.SrvSitemapServiceBase
{	

	private static Logger logger = Logger.getLogger(SrvSitemapServiceImpl.class);
	private static final int paginacion = 1000;	
	
	 /**
     * Genera el fichero sitemap_index y todos los ficheros sitemap con las url de las fichas del repositorio
     * Este método será invocado desde una tarea planificada. Comprobará que al finalizar el método de creación de ficheros exiten xml en el directorio
     * en caso contrario volverá a copiar los del directorio de backup
     * @param idTarea Identificador de la tarea
     * @return Boolean true si ha ido bien, false en caso contrario
     * @throws Exception
     * 
     */
    protected Boolean handleGenerarSitemapXml(Long idTarea)
        throws java.lang.Exception
    {
    	Boolean vuelta=false;
    	try{
	    	File directorioSiteMap,directorioSiteMapBackup,directorioSiteMapPortada = null;
	    	File[] listadoFicheros = null;
	    	File[] listadoFicherosBackup = null;
	    	boolean generadosFicherosSitemap = false;
	    	logger.info("GenerarSitemapXML");
	    	directorioSiteMap = new File(this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP)+this.getPropertyValue(SitemapPropertiesImpl.SEPARADOR));
	    	logger.debug("directorioSiteMap "+directorioSiteMap);
	    	directorioSiteMapBackup = new File(this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_BACKUP_SITEMAP)+this.getPropertyValue(SitemapPropertiesImpl.SEPARADOR));
	    	logger.debug("directorioSiteMapBackup "+directorioSiteMapBackup);
	    	directorioSiteMapPortada = new File(this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP_PORTADA)+this.getPropertyValue(SitemapPropertiesImpl.SEPARADOR));
	    	//Borro las carpetas del directorio backup
	    	logger.debug("Borro las carpetas del directorio backup");
	    	listadoFicherosBackup =directorioSiteMapBackup.listFiles();
	    	
	    	for(int i=0;i<listadoFicherosBackup.length;i++)
	    	{
	    		File ficheroBackup = listadoFicherosBackup[i];
	    		logger.debug("ficheroBackup "+ficheroBackup);
	    		if(!(ficheroBackup.isDirectory()))
	    		{
	    			UtilesFicheros.eliminar(ficheroBackup);
	    			logger.debug("Eliminaria "+ficheroBackup);
	    		}
	    	}
	    	
	    	logger.debug("Despues de eliminar el directorio backup");
	    	//Hacemos la copia de seguridad de los ficheros del directorio sitemaps
	    	listadoFicheros = directorioSiteMap.listFiles();
	    	logger.debug("Hacemos una copia de seguridad de los ficheros sitemap");
	       	for(int i=0;i<listadoFicheros.length;i++)
	    	{
	       		
	    		if((!listadoFicheros[i].isDirectory()))
	    		{
	    			logger.debug("Tenemos ficheros sitemap en el directorio - Se realiza la copia "+listadoFicheros[i]);
	        		UtilesFicheros.copiar(listadoFicheros[i], directorioSiteMapBackup);
	        		logger.debug("Hecha la copia de seguridad de los ficheros sitemap");
	        		//Borro el directorio sitemap
	        		UtilesFicheros.eliminar(listadoFicheros[i]);
	        		logger.debug("Despues de eliminar los ficheros de la carpeta urlSiteMap");
	    		}
	    	}
	        	
	       	//Copio el fichero sitemapPortada.xml al directorio urlSiteMap
	       	logger.debug("Copio el fichero sitemapPortada.xml en el directorio urlSiteMap");
	        UtilesFicheros.copiar(directorioSiteMapPortada,directorioSiteMap);
	        es.pode.sitemap.negocio.servicios.RepositorioVO[] repositorioVO = this.obtenerContenidoFichero();
	    	logger.debug("Despues de obtener el array de VO repositorioVO "+repositorioVO);
	    	this.generarFicheroSitemap(repositorioVO);
	    	//Chequeamos si se han generado ficheros en el directorio urlSiteMap, en caso contrario volvemos a copiar los del directorio backup
	    	logger.debug("Chequeamos si existen ficheros sitemap en el directorio urlSitemap, en caso contrario se copian los backup");
	    	listadoFicheros = directorioSiteMap.listFiles();
	    	for(int i=0;i<listadoFicheros.length;i++)
	    	{
	    		if((!(listadoFicheros[i].isDirectory()))&&(listadoFicheros[i].getName().equalsIgnoreCase(this.getPropertyValue(SitemapPropertiesImpl.NOMBRE_FICHERO_SITEMAP)+"1."+this.getPropertyValue(SitemapPropertiesImpl.EXTENSION_FICHEROS_SITEMAP))))
	    		{
	    			generadosFicherosSitemap = true;
	    		}
	    	}
	    	if(!generadosFicherosSitemap) //Solo aparece el fichero sitemapPortada.xml o ninguno
	    	{
	    		if(directorioSiteMapBackup.listFiles().length > 1)
	    		{
	    			logger.debug("No tenemos ficheros sitemap en el directorio pero si en el backup");
	        		UtilesFicheros.copiar(directorioSiteMapBackup,directorioSiteMap);
	        		logger.debug("Hecha la copia de seguridad de los ficheros sitemap");
	    		}else
	    		{
	    			logger.debug("No tenemos ficheros sitemap tampoco en el backup genero el index con el de la portada");
	    			this.generaSitemapIndex();
	        		logger.debug("Generado el index con el fichero portada");
	    		}
	    		
	    	}else
	    	{
	    		logger.debug("Generamos el fichero index de sitemap");
	        	this.generaSitemapIndex();
	    	}
	    	
	    	logger.info("Se han generado los ficheros sitemap");
	    	vuelta=Boolean.TRUE;
    	}catch(Exception e){
    		logger.error("No se han podido generar los archivos Sitempas "+e);
    		vuelta=Boolean.FALSE;
    	}
    	return vuelta;
    }
    
    /**
     * Llama al indexador para obtener el contenido del repositorio
     * Calcula las url de las fichas
     */
    //
    private es.pode.sitemap.negocio.servicios.RepositorioVO[] obtenerContenidoFichero()
    {
    	es.pode.sitemap.negocio.servicios.RepositorioVO[] repositorioVO = null;
    	logger.debug("GenerarFicheroSitemap");
    	es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO resultadoRepositorioVO[] = null;
        ParamPeriodoRepositorioVO paramPeriodoRepositorio = null;
        es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO resultadoRepositorioTemporal[] = null;
        String hostNodo = "";
    	try
    	{
    		Integer numeroElementosIndice = getSrvBuscadorService().obtenerTotalesRepositorio().getDocumentosCount();
    		logger.debug("Numero de elementos del indice "+numeroElementosIndice);
    		if(numeroElementosIndice == null || numeroElementosIndice.intValue() == 0)
            {
                logger.debug("No tenemos elementos en el indice devolvemos null");
            } else
            	if(numeroElementosIndice.intValue() <= paginacion)
            {
            		logger.debug("Necesitaremos una unica llamada al indexador para obtener el sitemaps");
                    paramPeriodoRepositorio = new ParamPeriodoRepositorioVO();
                    paramPeriodoRepositorio.setRegistroFinal(numeroElementosIndice);
                    paramPeriodoRepositorio.setRegistroInicial(new Integer(0));
                    resultadoRepositorioVO = getSrvBuscadorService().obtenerRepositorio(paramPeriodoRepositorio);
                    logger.debug((new StringBuilder()).append("Tamanio de paramPeriodoRepositorio sin necesidad de paginacion ").append(resultadoRepositorioVO.length).toString());
                    
                    if(resultadoRepositorioVO != null)
                    {
                        logger.debug((new StringBuilder()).append("El contenido del repositorio tiene un tamanio ").append(resultadoRepositorioVO.length).toString());
                        repositorioVO = new RepositorioVO[resultadoRepositorioVO.length];
                        hostNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);//host
                        for(int i = 0; i < resultadoRepositorioVO.length; i++)
                        {
                            repositorioVO[i] = new RepositorioVO();
                            getBeanMapper().map(resultadoRepositorioVO[i], repositorioVO[i],"DEF_MAPPING_RESULTADOREPOSITORIOVO_REPOSITORIOVO");
                            logger.debug("Mapeo del VO del indexador al VO de sitemaps");
                           // String url = (new StringBuilder()).append(getPropertyValue("protocoloHttp")).append(hostNodo).append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)).append(getPropertyValue("buscadorFicha")).append(repositorioVO[i].getIdioma()).append(getPropertyValue("separador")).append(repositorioVO[i].getIdentificador()).toString();
                            String url = ODEPublico.urlFichaODEPublicado(repositorioVO[i].getIdentificador(), repositorioVO[i].getIdioma());
                            repositorioVO[i].setUrlFicha(url);
                        }
                        logger.debug("Obtenidos todos los VO con informacion del repositorio");

                    }
                } else
                {
            		double division = (numeroElementosIndice.intValue() / paginacion);
            		 logger.debug("La division "+division);
                	double floor= java.lang.Math.floor(division);
                	logger.debug("El suelo "+floor);
                	int numeroPaginas=new Integer((int) floor);
//                    int numeroPaginas = numeroElementosIndice.intValue()/paginacion;
                    logger.debug("Numero de paginas "+numeroPaginas);
                    int registroInicial = 0;
                    int registroFinal = 0;
                    int j = 0;
                    repositorioVO = new RepositorioVO[numeroElementosIndice.intValue()];
                    for(int i = 1; i <= numeroPaginas; i++)
                    {
                        registroInicial = (i-1)*paginacion;
                        registroFinal = (i*paginacion)-1;
                        paramPeriodoRepositorio = new ParamPeriodoRepositorioVO();
                        paramPeriodoRepositorio.setRegistroFinal(new Integer(registroFinal));
                        paramPeriodoRepositorio.setRegistroInicial(new Integer(registroInicial));
                        logger.debug("paramPeriodoRepositorio.getRegistroInicial() "+paramPeriodoRepositorio.getRegistroInicial());
                        logger.debug("paramPeriodoRepositorio.getRegistroFinal() "+paramPeriodoRepositorio.getRegistroFinal());
                        resultadoRepositorioTemporal = getSrvBuscadorService().obtenerRepositorio(paramPeriodoRepositorio);
                        
                        if(!(resultadoRepositorioTemporal == null))
                        {
                           
//                        hostNodo = AgregaPropertiesImpl.getInstance().getProperty("host");
                        for(int k = 0; k < resultadoRepositorioTemporal.length; k++)
                        {
                            repositorioVO[j] = new RepositorioVO();
                           
                           // getBeanMapper().map(resultadoRepositorioTemporal[k], repositorioVO[j],"DEF_MAPPING_RESULTADOREPOSITORIOVO_REPOSITORIOVO");
                           if(resultadoRepositorioTemporal[k] == null)
                            {
                            	logger.debug(" resultadoRepositorioTemporal[k] es null ");
                            }else
                            {
                            	 repositorioVO[j] = new RepositorioVO();
                                 repositorioVO[j].setFechaPublicacion(resultadoRepositorioTemporal[k].getFechaPublicacion());
                                 repositorioVO[j].setIdentificador(resultadoRepositorioTemporal[k].getIdentificador());
                                 repositorioVO[j].setIdioma(resultadoRepositorioTemporal[k].getIdioma());
                                 repositorioVO[j].setNivelAgregacion(resultadoRepositorioTemporal[k].getNivelAgregacion());
                                 repositorioVO[j].setObjetivos(resultadoRepositorioTemporal[k].getObjetivos());
                               //  String url = (new StringBuilder()).append(getPropertyValue("protocoloHttp")).append(hostNodo).append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)).append(getPropertyValue("buscadorFicha")).append(repositorioVO[j].getIdioma()).append(getPropertyValue("separador")).append(repositorioVO[j].getIdentificador()).toString();
                                 String url = ODEPublico.urlFichaODEPublicado(repositorioVO[j].getIdentificador(), repositorioVO[j].getIdioma());
                                 repositorioVO[j].setUrlFicha(url);
                            }
                           
                            j++;
                           
                        }
                        }

                    }
                    logger.debug("Obtenidos el array resultado de la llamada al indexador "+repositorioVO.length);
                    logger.info("Tenemos que hacer la llamada de "+(registroFinal+1)+ " a "+numeroElementosIndice.intValue());
                    if(registroFinal < numeroElementosIndice.intValue())
                    {
                        logger.debug("Calculo al ultima pagina desde "+(registroFinal+1)+" a "+numeroElementosIndice.intValue());
                        paramPeriodoRepositorio = new ParamPeriodoRepositorioVO();
                        paramPeriodoRepositorio.setRegistroFinal(numeroElementosIndice);
                        paramPeriodoRepositorio.setRegistroInicial(new Integer(registroFinal+1));
                        resultadoRepositorioTemporal = getSrvBuscadorService().obtenerRepositorio(paramPeriodoRepositorio);
                        
                        if(resultadoRepositorioTemporal != null)
                        {
                        	logger.debug("Nos devuelven "+resultadoRepositorioTemporal.length+" resultados");
//                            hostNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
                            for(int k = 0; k < resultadoRepositorioTemporal.length; k++)
                            {
                                repositorioVO[j] = new RepositorioVO();
                                repositorioVO[j].setFechaPublicacion(resultadoRepositorioTemporal[k].getFechaPublicacion());
                                repositorioVO[j].setIdentificador(resultadoRepositorioTemporal[k].getIdentificador());
                                repositorioVO[j].setIdioma(resultadoRepositorioTemporal[k].getIdioma());
                                repositorioVO[j].setNivelAgregacion(resultadoRepositorioTemporal[k].getNivelAgregacion());
                                repositorioVO[j].setObjetivos(resultadoRepositorioTemporal[k].getObjetivos());
                              ///getBeanMapper().map(resultadoRepositorioTemporal[k], repositorioVO[j]);
                               // String url = (new StringBuilder()).append(getPropertyValue("protocoloHttp")).append(hostNodo).append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)).append(getPropertyValue("buscadorFicha")).append(repositorioVO[j].getIdioma()).append(getPropertyValue("separador")).append(repositorioVO[j].getIdentificador()).toString();
                                String url = ODEPublico.urlFichaODEPublicado(repositorioVO[j].getIdentificador(), repositorioVO[j].getIdioma());
                                repositorioVO[j].setUrlFicha(url);
                                j++;
                            }

                        }
                    } else
                    {
                        logger.debug("Ya no tengo que calcular mas paginas");
                    }
                }
            }
            catch(Exception e)
            {
                logger.error((new StringBuilder()).append("Exception al obtener el repositorio ").append(e).toString());
            }
            logger.debug("El numero total de objetos devueltos es  "+repositorioVO.length);
            return repositorioVO;
        }
    
    /**
     * Genera el xml con el contenido del repositorio. Tendremos que hacer una copia en otro directorio de los xml anteriores
     * @throws Exception
     */
    
    private void generarFicheroSitemap(es.pode.sitemap.negocio.servicios.RepositorioVO[] repositorioVO) throws Exception
    {
    	String resultadoXML = "";
    	FileWriter fileSitemap = null;
    	String urlFicheroSitemap = "";
    	String cabeceraFichero = "";
    	String pieFichero = "";
    	File ficheroDestino = null;
    	int contadorNombreFichero = 1;
    	int contadorNumeroEntradas = 0;
    	logger.debug("GenerarFicheroSitemap");
    	if(repositorioVO == null)
    	{
    		logger.debug("No disponemos de informacion del repositorio");
    	}else
    	{
    		logger.debug("generado VO con el repositorio "+repositorioVO.length);
    		logger.debug("Recorremos el vo para genera el xml");
    		cabeceraFichero = "<?xml version='1.0' encoding='UTF-8'?>"+ "\n" + "<urlset xmlns='" +this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP)+"'"+ " xmlns:xsi='"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_XSI)+"'"+" xsi:schemaLocation='"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_LOCATION)+"'>" + "\n";
    		pieFichero = "</urlset>"+ "\n";
    		
    		urlFicheroSitemap = this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP)+ this.getPropertyValue(SitemapPropertiesImpl.NOMBRE_FICHERO_SITEMAP)+ (new Integer(contadorNombreFichero)).toString()+"."+this.getPropertyValue(SitemapPropertiesImpl.EXTENSION_FICHEROS_SITEMAP);
    		ficheroDestino = new File(urlFicheroSitemap);
    		fileSitemap = new FileWriter(ficheroDestino,true);
    		fileSitemap.write(cabeceraFichero);
    		fileSitemap.close();
    		for(int i=0;i<repositorioVO.length;i++)
    		{
    			fileSitemap = new FileWriter(ficheroDestino,true);
    			if(repositorioVO[i] == null)
    			{
    				logger.debug("no hay informacion disponible del repositorio ");
    			}else
    			{
	    		
	    			resultadoXML = "";
	    			resultadoXML = resultadoXML + "<url>" + "\n";
	    			resultadoXML = resultadoXML + "<loc>"+repositorioVO[i].getUrlFicha()+"</loc>" + "\n";
	    			resultadoXML = resultadoXML + "<lastmod>"+obtenerFechaModificacion(repositorioVO[i].getFechaPublicacion())+"</lastmod>" + "\n";
	    			resultadoXML = resultadoXML + "<changefreq>"+this.getPropertyValue(SitemapPropertiesImpl.PERIODICIDAD)+"</changefreq>" + "\n";
	    			resultadoXML = resultadoXML + "<priority>"+obtenerPrioridad(repositorioVO[i].getNivelAgregacion())+"</priority>" + "\n";
	    			resultadoXML = resultadoXML + "</url>"+ "\n";
	    			fileSitemap.write(resultadoXML);
	    			contadorNumeroEntradas ++;
	    			Integer tamainoMax=new Integer(this.getPropertyValue(SitemapPropertiesImpl.NUMERO_ENTRADAS_XML)).intValue();
	    			if(contadorNumeroEntradas >= tamainoMax)
	    			{
	    				logger.debug("supera el tamanio maximo, "+tamainoMax+", "+contadorNumeroEntradas);
	    				contadorNumeroEntradas = 0;
	    				fileSitemap.write(pieFichero);
	    				fileSitemap.close();
	    				contadorNombreFichero ++;
	    				logger.debug("Vamos sumando al contador");
	    				logger.debug("El directorio es "+this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP));
	    				logger.debug("El nombre es "+this.getPropertyValue(SitemapPropertiesImpl.NOMBRE_FICHERO_SITEMAP));
	    				logger.debug("Numero de fichero es "+(new Integer(contadorNombreFichero)).toString());
	    				logger.debug("Y la extensión es "+this.getPropertyValue(SitemapPropertiesImpl.EXTENSION_FICHEROS_SITEMAP));
	    				urlFicheroSitemap = this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP)+ this.getPropertyValue(SitemapPropertiesImpl.NOMBRE_FICHERO_SITEMAP)+ (new Integer(contadorNombreFichero)).toString()+"."+this.getPropertyValue(SitemapPropertiesImpl.EXTENSION_FICHEROS_SITEMAP);
	    				logger.debug("NUEVO FICHERO SITEMAP "+urlFicheroSitemap);
	    				ficheroDestino = new File(urlFicheroSitemap);
	    				fileSitemap = new FileWriter(ficheroDestino,true);
	    				logger.debug("se ha creado el FileWriter");
	    				fileSitemap.write(cabeceraFichero);
	    				
	    				
	    			}
       		
    			}
    		fileSitemap.close();
    		
    		}
    		fileSitemap = new FileWriter(ficheroDestino,true);
    		fileSitemap.write(pieFichero);
    		fileSitemap.close();
    		logger.debug("Se ha escrito el fichero");
    		
    	}
    }
    
    /**
	 * Obtiene el valor almacenado en el fichero properties sitemap.propterties de una determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws Exception
	 */
	private String getPropertyValue(String sKey) throws IOException
	{
	/*
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/sitemap.properties");
		if (this.pSitemapProperties == null)
		{
			pSitemapProperties = new java.util.Properties();
			pSitemapProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pSitemapProperties.getProperty(sKey);
		*/
		return SitemapPropertiesImpl.getInstance().getProperty(sKey);
	}
	
	/**
	 * Obtiene la prioridad asociada al nivel de agregación que se pasa por parámetro
	 * las correspondencias se especifican en el fichero sitemap.properties 
	 * @param  nivelAgregacion cadena con el nivel de agregación
	 * @return String
	 * @throws Exception
	 */
	private String obtenerPrioridad(String nivelAgregacion) throws Exception
	{
		String prioridad = "";
		logger.debug("Periodicidad");
		prioridad = this.getPropertyValue(nivelAgregacion);
		return prioridad;
	}
	
	/**
	 * Genera el fichero sitemapIndex con los ficheros sitemap generados
	 * @throws Exception
	 */
	private void generaSitemapIndex() throws Exception
	{
		File ficherosSitemap = null; //Directorio donde se guardan los sitemap
		File[] listaFicherosSitemap = null;
		FileWriter fileSitemapIndex = null; //Fichero sitemap-index.xml donde se almacenarán todos los
		File ficheroDestino = null;
		Date fechaModificacion = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String cadenaModificacion = "";
		File ficherosIndex = null;
		logger.debug("Generamos el fichero index del sitemap");
		//Recorremos el directorio sitemap para ver los ficheros que aparecen
		ficherosSitemap = new File(this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP));
		logger.debug("[GENERARFICHEROSITEMAPINDEX] ficherosSitemap "+ficherosSitemap);
		listaFicherosSitemap = ficherosSitemap.listFiles();
		logger.debug("[GENERARFICHEROSITEMAPINDEX]listaFicherosSitemap.length "+listaFicherosSitemap.length);
		ficheroDestino = new File(this.getPropertyValue(SitemapPropertiesImpl.DIRECTORIO_FICHEROS_SITEMAP)+ this.getPropertyValue(SitemapPropertiesImpl.NOMBRE_FICHERO_SITEMAP_INDEX)+ "."+this.getPropertyValue(SitemapPropertiesImpl.EXTENSION_FICHEROS_SITEMAP));
		logger.debug("[GENERARFICHEROSITEMAPINDEX] ficheroDestino "+ficheroDestino);
		fileSitemapIndex = new FileWriter(ficheroDestino,true);
		logger.debug("esquema-sitemap-index"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_INDEX));
		logger.debug("esquema-sitemap-index"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_XSI_INDEX));
		fileSitemapIndex.write("<?xml version='1.0' encoding='UTF-8'?>"+ "\n" + "<sitemapindex xmlns='" +this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_INDEX)+"'"+ " xmlns:xsi='"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_XSI_INDEX)+"'"+" xsi:schemaLocation='"+this.getPropertyValue(SitemapPropertiesImpl.ESQUEMA_SITEMAP_LOCATION_INDEX)+"'>" + "\n");
		String hostNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);//host
		for(int i=0;i<listaFicherosSitemap.length;i++)
		{
			ficherosIndex = listaFicherosSitemap[i];
			logger.debug("ficherosIndex.getName() "+ficherosIndex.getName());
			if((!(ficherosIndex.isDirectory()))&&((ficherosIndex.getName().indexOf("robots") == -1)))
			{
				fileSitemapIndex.write("<sitemap>"+"\n"+"<loc>"+this.getPropertyValue(SitemapPropertiesImpl.PROTOCOLO_HTTP) + hostNodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+this.getPropertyValue(SitemapPropertiesImpl.SEPARADOR)+listaFicherosSitemap[i].getName()+"</loc>"+"\n");
				fechaModificacion = new Date(listaFicherosSitemap[i].lastModified());
				cadenaModificacion = simpleDateFormat.format(fechaModificacion);
				fileSitemapIndex.write("<lastmod>"+cadenaModificacion);
				fileSitemapIndex.write("</lastmod>"+"\n");
				fileSitemapIndex.write("</sitemap>"+"\n");
			}
		}
		fileSitemapIndex.write("</sitemapindex>");
		fileSitemapIndex.close(); 
		
	}
	
	/**
	 * Obtiene la fecha de modificación en formato 
	 * @throws Exception
	 */
	private String obtenerFechaModificacion(String fecha)
	{
		String resultado = "";
		if(!(fecha == null))
		{
			String anio, mes, dia = "";
			anio = fecha.substring(0, 4);
			mes = fecha.substring(4, 6);
			dia = fecha.substring(6, 8);
			resultado = anio + "-" + mes + "-" + dia;
		}
		return resultado;
	}
}