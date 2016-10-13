/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point 
/** 
 * This is only generated once! It will never be overwritten. 
 * You can (and have to!) safely modify it by hand. 
 */ 
package es.pode.agregador.negocio.agregador.servicio; 
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndImageImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.FavoritoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.agregador.comun.FechaComparator;
import es.pode.agregador.comun.NumVecesComparator;
import es.pode.auditoria.negocio.servicios.InformeMasVO;
import es.pode.auditoria.negocio.servicios.ParametrosInformeVO;
import es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO;
import es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO;
import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.contenidos.negocio.noticias.servicio.SrvNoticiasService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.publicacion.negocio.servicios.OdePublicadoVO;
import es.pode.soporte.buscador.TraduccionesBuscador;
import es.pode.soporte.buscar.BuscarController;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.url.Proxy;
import es.pode.soporte.utiles.base64.Base64Coder;

/** 
 * @see es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssService 
 */ 
 
 
public class SrvAgregadorRssServiceImpl 

 extends es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceBase 
{ 
	private java.util.Properties pAgregadorRSSProperties = null; 
	private String feedHost = LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio(); 
//	private String feedPath = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS_PATH);
//	private String feedPathResultadosBusqueda = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS_PATH) + (feedPath.endsWith("/")?"":"/")+"temp/";
//	private String rss = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS);
	
	private String http = "http://";
	private String rssPath = "";
	private String IMAGEN_AMPLIADA= "_captured.jpg";
	private String IMAGEN_MINIMIZADA= "png"; 
	private SyndFeed feed = new SyndFeedImpl(); 
	private HashMap mapaIdiomas = new HashMap(); // mapa que alberga los bunlde para cada idioma.
	private final String PUNTO = "\\."; 
	public final String UNIVERSAL = "universal";
	private final String NIV_EDU_SEPARATOR = "/";
	private final String TIPO_RSS="RSS";
	private final String TIPO_ATOM="ATOM";
	public final String TAX_ASTERISCO = "\\*";
	public final String TAX_DOLAR = "\\$";
	private HashMap hashMapVersiones=null;
	private final int TIMEOUT_DEFAULT = 4000;// milisegundos
	private final long FEED_NUM_MAX = 300;//Número máximo de feed permitidos por xml
	private final long FEED_NUM_MIN_FEDERADO = 20; //Número mínimo de feed que se piden a cada nodo en los feeds federados 
	private final String CONTEXTO_BUSCADOR_PUBLICO = "buscador2";
	private final String CONTEXTO_BUSCADOR_PRIVADO = "buscador";
	private final String FEED_PRIVADO = "privado";
	private final String FEED_PUBLICO = "publico";
	private final String ENCODING_XML_RSS = "ISO-8859-1";
	
	
	/**
	 * Este metodo genera el feed indicado para la parte privada de agrega
	 * @param idTarea identificador del feed
	 * @param feedNum Número de odes en el feed
	 * @param idiomaNavegacion Idioma en el que se devuelve el feed
	 * @throws Exception
	 */ 
	protected void handleCrearXML(String idTarea,long feedNum,
			String idiomaNavegacion) throws java.lang.Exception {
		crearXML(idTarea,feedNum,idiomaNavegacion,FEED_PRIVADO);
	}
 

	/**
	 * Este metodo genera el feed indicado para la parte publica de agrega
	 * @param idTarea identificador del feed
	 * @param feedNum Número de odes en el feed
	 * @param idiomaNavegacion Idioma en el que se devuelve el feed
	 * @throws Exception
	 */ 
	@Override
	protected void handleCrearXMLPublico(String idTarea, long feedNum,
			String idiomaNavegacion) throws Exception {
		crearXML(idTarea,feedNum,idiomaNavegacion,FEED_PUBLICO);
	}
	

	/**
	 * Este metodo genera el feed indicado
	 * @param idTarea identificador del feed
	 * @param feedNum Número de odes en el feed
	 * @param idiomaNavegacion Idioma en el que se devuelve el feed
	 * @param tipoFeed Tipo de feed a generar (FEED_PUBLICO o FEED_PRIVADO)
	 * @throws Exception
	 */ 
	private void crearXML(String idTarea,long feedNum,String idiomaNavegacion,String tipoUrl) 
throws java.lang.Exception 
 
 {
		feedNum = (feedNum > FEED_NUM_MAX)? FEED_NUM_MAX:feedNum;
		InformeMasVO[] masDescargados = null;
		InformeMasVO[] masMostrados = null;
		InformeMasVO[] masPrevisualizados = null; 
		InformeMasVO[] masEnviados = null; 
		feed = new SyndFeedImpl();
 try { 
 //Datos comunes para todos los xml 
 //Creamos el feed 
 	feed.setEncoding(ENCODING_XML_RSS);
 	feed.setAuthor("Agrega");
	 feed.setPublishedDate(new Date());
 
 	try{ 
	 //SERVICIO AUDITORIA 
 		//parámetros necesarios para hacer la llamada al servicio de auditoría 
 SrvAuditoriaServicio srvAuditoriaServicio = this.getSrvAuditoriaServicio(); 
 ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO(); 
	 	parametrosInformeVO.setRango(new Integer(Long.valueOf(feedNum).intValue())); 
	 	parametrosInformeVO.setFechaHasta(Calendar.getInstance()); 
	 	Calendar calendar_anio = Calendar.getInstance(); 
 		calendar_anio.add(Calendar.DATE, -365); 
	 	Calendar calendar_semana = Calendar.getInstance(); 
	 	calendar_semana.add(Calendar.DATE, -7); 
	 	Calendar calendar_mes = Calendar.getInstance(); 
	 	calendar_mes.add(Calendar.DATE, -30); 
	 	 
 			String tipoFeed=TIPO_RSS;
 			 
	 		 	
	 	if(idTarea.equalsIgnoreCase("11.1")||idTarea.equalsIgnoreCase("11.2")){
	 		
		 		try{ 
			 	//DESCARGAS 
//		 			if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - crearXML: generando feed descargas..."); 
		 			DescargaVO[] descargas = this.getSrvDescargas().obtenerDescargasActivas(); 
		 			DescDescargaVO[] descs=null;
		 			
		 			if(logger.isDebugEnabled())logger.debug("Recuperadas "+descargas.length!=null?descargas.length:0+" descargas");
		 			ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
		 			if (descargas!=null&&descargas.length>0){
		 				descs = getSrvDescargas()
		 						.obtenerDescDescargasIdioma(descargas, idiomaNavegacion);
		 				if(logger.isDebugEnabled())logger.debug("Recuperadas " + descs.length + " descripciones de descargas"); 			
		 			}
	
		 			if(idTarea.equalsIgnoreCase("11.2"))tipoFeed =TIPO_ATOM; 
		 			crearXMLdescargas(descs,descargas,"descargas.title", getPropertyValue("descargas.url"), 
		 					traduceEtiqueta("descargas.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,idTarea,tipoUrl);
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de noticias: " , e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("0.1")||idTarea.equalsIgnoreCase("0.2")){
	 
		 		try{ 
			 	//NOTICIAS 
//		 			if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - crearXML: generando feed noticias..."); 
		 			NoticiaTraducidaVO[] noticias = this.obtenerNoticias(); 
		 			if(idTarea.equalsIgnoreCase("0.2")) tipoFeed =TIPO_ATOM; 
		 		
	 				crearXMLnoticias(noticias,"noticias.title", getPropertyValue("noticias.url"), 
	 						traduceEtiqueta("noticias.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,idTarea,tipoUrl); 
		 		}
		 		catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de noticias: " , e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("1.1")||idTarea.equalsIgnoreCase("1.2")){
 
		 		try{ 
			 	//SERVICIO AUDITAPUBLICACION
			 	OdePublicadoVO[] ultimosOdes = this.getSrvAuditaPublicacionService().obtenerUltimosOdesPublicados(Long.valueOf(feedNum).intValue());
			 	if(idTarea.equalsIgnoreCase("1.2")) tipoFeed =TIPO_ATOM;
			 	
		 		crearXMLauditoria(ultimosOdes,"ultimosOdes.title", getPropertyValue("ultimosOdes.url"), 
		 				traduceEtiqueta("ultimosOdes.descripcion",idiomaNavegacion),tipoFeed ,idiomaNavegacion,feedNum,idTarea,tipoUrl); 
			 	 
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de los últimos ODEs publicados: " + e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("2.1")||idTarea.equalsIgnoreCase("2.2")){
 
		 		try{ 
			 	//SERVICIO AUDITAPUBLICACION
	 			if(idTarea.equalsIgnoreCase("2.2")) tipoFeed=TIPO_ATOM;
		 				 			
		 			crearXMLauditoriaFederada("1.1", "ultimosOdes_Federado.title",getPropertyValue("ultimosOdes_Federado.url"),
		 					traduceEtiqueta("ultimosOdes_Federado.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,idTarea);
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de los últimos ODEs federados publicados: " + e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("3.1.3")||idTarea.equalsIgnoreCase("3.2.3")){
		 		try{ 
			 	//MAS DESCARGADOS 
 
		 			parametrosInformeVO.setFechaDesde(calendar_anio); 
			 	masDescargados = srvAuditoriaServicio.informeMasDescargados(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("3.2.3")) tipoFeed=TIPO_ATOM;
		 					 
			 	crearXMLauditoria(masDescargados,"masDescargados_anio.title", getPropertyValue("masDescargados.url"), 
			 			traduceEtiqueta("masDescargados_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"3",tipoUrl); 
			 	
			 	} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más descargados AÑO: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("4.1.3")||idTarea.equalsIgnoreCase("4.2.3")){
	 		
		 		try{ 
			 	//MAS DESCARGADOS 
 
		 			if(idTarea.equalsIgnoreCase("4.2.3")) tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("3.1.3", "masDescargados_Federado_anio.title",getPropertyValue("masDescargados_Federado.url"),
		 					traduceEtiqueta("masDescargados_Federado_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"4");	
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más descargados AÑO: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("3.1.1")||idTarea.equalsIgnoreCase("3.2.1")){
	 		 
		 		try{ 
		 			if(logger.isDebugEnabled())logger.debug("generando feed odes mas descargados SEMANA..."); 
			 	parametrosInformeVO.setFechaDesde(calendar_semana); 
			 	masDescargados = srvAuditoriaServicio.informeMasDescargados(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("3.2.1")) tipoFeed=TIPO_ATOM;
		 			 		
			 	crearXMLauditoria(masDescargados,"masDescargados_semana.title", getPropertyValue("masDescargados.url"), 
			 			traduceEtiqueta("masDescargados_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"3",tipoUrl);
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más descargados SEMANA: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("4.1.1")||idTarea.equalsIgnoreCase("4.2.1")){
 
		 		try{ 
		 			if(idTarea.equalsIgnoreCase("4.2.1")) tipoFeed=TIPO_ATOM;
	 			
		 			crearXMLauditoriaFederada("3.1.1", "masDescargados_Federado_semana.title",getPropertyValue("masDescargados_Federado.url"), 
		 					traduceEtiqueta("masDescargados_Federado_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"4"); 
	 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más descargados SEMANA: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("3.1.2")||idTarea.equalsIgnoreCase("3.2.2")){
	 		 
		 		try{ 

			 	parametrosInformeVO.setFechaDesde(calendar_mes);
			 	masDescargados = srvAuditoriaServicio.informeMasDescargados(parametrosInformeVO);
			 	if(idTarea.equalsIgnoreCase("3.2.2")) tipoFeed=TIPO_ATOM;
		 					 
			 	crearXMLauditoria(masDescargados,"masDescargados_mes.title", getPropertyValue("masDescargados.url"), 
			 			traduceEtiqueta("masDescargados_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"3",tipoUrl); 
			 	
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más descargados MES: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("4.1.2")||idTarea.equalsIgnoreCase("4.2.2")){
 
		 		try{ 
		 			if(idTarea.equalsIgnoreCase("4.2.2")) tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("3.1.2", "masDescargados_Federado_mes.title",getPropertyValue("masDescargados_Federado.url"), 
		 					traduceEtiqueta("masDescargados_Federado_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"4");
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más descargados MES: ", e); 
		 		}
	 	}
	 		 
	 	else if(idTarea.equalsIgnoreCase("5.1.3")||idTarea.equalsIgnoreCase("5.2.3")){
	 		
		 		try{ 
			 	//MAS VISTOS 
			 	parametrosInformeVO.setFechaDesde(calendar_anio); 
			 	masMostrados = srvAuditoriaServicio.informeMasMostrado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("5.2.3")) tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masMostrados,"masMostrados_anio.title", getPropertyValue("masMostrados.url"),
			 			traduceEtiqueta("masMostrados_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"5",tipoUrl);
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más vistos AÑO: ", e); 
		 		} 	
	 	}
	 	else if(idTarea.equalsIgnoreCase("6.1.3")||idTarea.equalsIgnoreCase("6.2.3")){
 
		 		try{ 
			 	//MAS VISTOS 
		 			if(idTarea.equalsIgnoreCase("6.2.3")) tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("5.1.3", "masMostrados_Federado_anio.title",getPropertyValue("masMostrados_Federado.url"), 
		 					traduceEtiqueta("masMostrados_Federado_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"6"); 
		 		
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más vistos AÑO: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("5.1.1")||idTarea.equalsIgnoreCase("5.2.1")){
	 		 
		 		try{ 
			 	parametrosInformeVO.setFechaDesde(calendar_semana); 
			 	masMostrados = srvAuditoriaServicio.informeMasMostrado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("5.2.1")) tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masMostrados,"masMostrados_semana.title", getPropertyValue("masMostrados.url"), 
			 			traduceEtiqueta("masMostrados_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"5",tipoUrl); 
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más vistos SEMANA: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("6.1.1")||idTarea.equalsIgnoreCase("6.2.1")){
 
		 		try{ 
		 			if(idTarea.equalsIgnoreCase("6.2.1")) tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("5.1.1", "masMostrados_Federado_semana.title",getPropertyValue("masMostrados_Federado.url"), 
		 					traduceEtiqueta("masMostrados_Federado_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"6");
		 			
		 			} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más vistos SEMANA: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("5.1.2")||idTarea.equalsIgnoreCase("5.2.2")){
	 		
		 		try{ 
			 	parametrosInformeVO.setFechaDesde(calendar_mes); 
			 	masMostrados = srvAuditoriaServicio.informeMasMostrado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("5.2.2")) tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masMostrados,"masMostrados_mes.title", getPropertyValue("masMostrados.url"), 
			 			traduceEtiqueta("masMostrados_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"5",tipoUrl); 	
		 				
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más vistos MES: ", e); 
		 		} 
	 	}
	 	else if(idTarea.equalsIgnoreCase("6.1.2")||idTarea.equalsIgnoreCase("6.2.2")){
	 		
		 		try{ 
		 			if(idTarea.equalsIgnoreCase("6.2.2"))tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("5.1.2", "masMostrados_Federado_mes.title",getPropertyValue("masMostrados_Federado.url"), 
		 					traduceEtiqueta("masMostrados_Federado_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"6");
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más vistos MES: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("7.1.3")||idTarea.equalsIgnoreCase("7.2.3")){
	 			
		 		try{ 
			 	//MAS PREVISUALIZADOS 
			 	parametrosInformeVO.setFechaDesde(calendar_anio); 
			 	masPrevisualizados = srvAuditoriaServicio.informeMasPrevisualizados(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("7.2.3"))tipoFeed=TIPO_ATOM;
		 			 
			 	crearXMLauditoria(masPrevisualizados,"masPrevisualizados_anio.title", getPropertyValue("masPrevisualizados.url"), 
			 			traduceEtiqueta("masPrevisualizados_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"7",tipoUrl);
			 	
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más previsualizados AÑO: ", e); 
		 		}
	 	}
	 		else if(idTarea.equalsIgnoreCase("8.1.3")||idTarea.equalsIgnoreCase("8.2.3")){
 
		 		try{ 
			 	//MAS PREVISUALIZADOS 
		 			if(idTarea.equalsIgnoreCase("8.2.3"))tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("7.1.3", "masPrevisualizados_Federado_anio.title",getPropertyValue("masPrevisualizados_Federado.url"),
		 					traduceEtiqueta("masPrevisualizados_Federado_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"8");
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más previsualizados AÑO: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("7.1.1")||idTarea.equalsIgnoreCase("7.2.1")){
	 		 
		 		try{ 
		 			parametrosInformeVO.setFechaDesde(calendar_semana); 
			 	masPrevisualizados = srvAuditoriaServicio.informeMasPrevisualizados(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("7.2.1")) tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masPrevisualizados,"masPrevisualizados_semana.title", getPropertyValue("masPrevisualizados.url"), 
			 			traduceEtiqueta("masPrevisualizados_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"7",tipoUrl); 
		 		
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más previsualizados SEMANA: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("8.1.1")||idTarea.equalsIgnoreCase("8.2.1")){
 
		 		try{ 
//		 			if(logger.isDebugEnabled())logger.debug("generando feed odes federados mas previsualizados SEMANA...");
		 			if(idTarea.equalsIgnoreCase("8.2.1"))tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("7.1.1", "masPrevisualizados_Federado_semana.title",getPropertyValue("masPrevisualizados_Federado.url"), 
		 					traduceEtiqueta("masPrevisualizados_Federado_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"8"); 
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más previsualizados SEMANA: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("7.1.2")||idTarea.equalsIgnoreCase("7.2.2")){
	 		 
		 		try{ 
//		 			if(logger.isDebugEnabled())logger.debug("generando feed odes mas previsualizados MES..."); 
			 	parametrosInformeVO.setFechaDesde(calendar_mes); 
			 	masPrevisualizados = srvAuditoriaServicio.informeMasPrevisualizados(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("7.2.2"))tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masPrevisualizados,"masPrevisualizados_mes.title", getPropertyValue("masPrevisualizados.url"), 
			 			traduceEtiqueta("masPrevisualizados_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"7",tipoUrl); 
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más previsualizados MES: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("8.1.2")||idTarea.equalsIgnoreCase("8.2.2")){
	 
		 		try{ 
//						if(logger.isDebugEnabled())logger.debug("generando feed odes federados mas previsualizados MES...");
						if(idTarea.equalsIgnoreCase("8.2.2"))tipoFeed=TIPO_ATOM;
		 			
						crearXMLauditoriaFederada("7.1.2", "masPrevisualizados_Federado_mes.title",getPropertyValue("masPrevisualizados_Federado.url"), 
								traduceEtiqueta("masPrevisualizados_Federado_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"8"); 	
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más previsualizados MES: ", e); 
		 		} 
	 	} 
	 		else if(idTarea.equalsIgnoreCase("9.1.3")||idTarea.equalsIgnoreCase("9.2.3")){ 
	 		 
		 		try{ 
	//		 	//MAS ENVIADOS 
			 	parametrosInformeVO.setFechaDesde(calendar_anio); 
			 	masEnviados = srvAuditoriaServicio.informeMasEnviado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("9.2.3"))tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masEnviados,"masEnviados_anio.title", getPropertyValue("masEnviados.url"), 
			 			traduceEtiqueta("masEnviados_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"9",tipoUrl); 
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más enviados AÑO: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("10.1.3")||idTarea.equalsIgnoreCase("10.2.3")){ 
 
		 		try{ 
	//		 	//MAS ENVIADOS 
		 			if(idTarea.equalsIgnoreCase("10.2.3"))tipoFeed=TIPO_ATOM;
		 				
		 			crearXMLauditoriaFederada("9.1.3", "masEnviados_Federado_anio.title",getPropertyValue("masEnviados_Federado.url"), 
		 					traduceEtiqueta("masEnviados_Federado_anio.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"10"); 
		 		
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más enviados AÑO: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("9.1.1")||idTarea.equalsIgnoreCase("9.2.1")){ 
	 		 
		 		try{ 
//		 			if(logger.isDebugEnabled())logger.debug("generando feed odes mas enviados SEMANA con idiomaNavegacion="+idiomaNavegacion);
		 			parametrosInformeVO.setFechaDesde(calendar_semana); 
			 	masEnviados = srvAuditoriaServicio.informeMasEnviado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("9.2.1"))tipoFeed=TIPO_ATOM;
		 			
			 	crearXMLauditoria(masEnviados,"masEnviados_semana.title", getPropertyValue("masEnviados.url"), 
			 			traduceEtiqueta("masEnviados_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"9",tipoUrl); 
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más enviados SEMANA: ", e); 
		 		}
	 	}
	 		else if(idTarea.equalsIgnoreCase("10.1.1")||idTarea.equalsIgnoreCase("10.2.1")){ 
 
		 		try{ 
//		 			if(logger.isDebugEnabled())logger.debug("generando feed odes federados mas enviados SEMANA..."); 
		 			if(idTarea.equalsIgnoreCase("10.2.1"))tipoFeed=TIPO_ATOM;
		 			
		 			crearXMLauditoriaFederada("9.1.1", "masEnviados_Federado_semana.title",getPropertyValue("masEnviados_Federado.url"), 
		 					traduceEtiqueta("masEnviados_Federado_semana.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"10"); 	
		 			
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más enviados SEMANA: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("9.1.2")||idTarea.equalsIgnoreCase("9.2.2")){ 
	 		 
		 		try{ 
//		 			if(logger.isDebugEnabled())logger.debug("generando feed odes mas enviados MES..."); 
			 	parametrosInformeVO.setFechaDesde(calendar_mes); 
			 	masEnviados = srvAuditoriaServicio.informeMasEnviado(parametrosInformeVO); 
			 	if(idTarea.equalsIgnoreCase("9.2.2"))tipoFeed=TIPO_ATOM;
			 		
			 	crearXMLauditoria(masEnviados,"masEnviados_mes.title", getPropertyValue("masEnviados.url"),
			 			traduceEtiqueta("masEnviados_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"9",tipoUrl); 
		 			
					} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs más enviados MES: ", e); 
		 		} 
	 	}
	 		else if(idTarea.equalsIgnoreCase("10.1.2")||idTarea.equalsIgnoreCase("10.2.2")){ 
	 
		 		try{ 
//						if(logger.isDebugEnabled())logger.debug("generando feed odes federados mas enviados MES...");
						if(idTarea.equalsIgnoreCase("10.2.2"))tipoFeed=TIPO_ATOM;
		 			
						crearXMLauditoriaFederada("9.1.2", "masEnviados_Federado_mes.title",getPropertyValue("masEnviados_Federado.url"), 
								traduceEtiqueta("masEnviados_Federado_mes.descripcion",idiomaNavegacion),tipoFeed,idiomaNavegacion,feedNum,"10"); 
		 		
		 		} catch (Exception e){ 
		 			logger.error("Se ha producido un error al recuperar los datos de ODEs federados más enviados MES: ", e); 
		 		}
	 	}

 	} catch (Exception e){ 
 		logger.error("Se ha producido un error --> " + e); 
 		throw e; 
 	} 
 
 } 
 catch (IOException ex) { 
 		logger.error("Se ha producido un error al acceder a los ficheros de configuración --> " + ex); 
 		throw ex; 
 } 
 
 catch (Exception ex) { 
 		logger.error("Se ha producido un error --> " + ex); 
 		throw ex; 
 } 

 
 } 
	 
	private NoticiaTraducidaVO[] obtenerNoticias() throws Exception{ 
		 
		SrvNoticiasService srNoticiasService = this.getSrvNoticiasService(); 
		 
//		OBTENEMOS LOS IDIOMAS TRADUCIBLES 
		String[] idiomasPlataforma = I18n.getInstance().obtenerIdiomasPlataforma(); 
		if(logger.isDebugEnabled())logger.debug("Hay ["+idiomasPlataforma.length+"] en la plataforma"); 
		String idiomaPrioritario = I18n.getInstance().obtenerIdiomaDefectoPlataforma(); 
		String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
		idiomaSecundario=idiomaSecundario==null?"":idiomaSecundario;
		if(logger.isDebugEnabled())logger.debug("idioma prioritario de la plataforma es ["+idiomaPrioritario+"] y el secundario ["+idiomaSecundario+"]"); 
 
 //OBTENEMOS LAS NOTICIAS 
		NoticiaTraducidaVO[] noticiasTraducidas = srNoticiasService.obtenerNoticiasTraducidas(devuelveIdiomasTraducibles(idiomasPlataforma, idiomaPrioritario, idiomaSecundario)); 
 
		int cont=0; 
		List noticiasActivas = new ArrayList(); 
		for (int i=0;i<noticiasTraducidas.length;i++){ 
			if (noticiasTraducidas[i]!=null && noticiasTraducidas[i].getActiva()){ 
				noticiasActivas.add(noticiasTraducidas[i]); 
				cont=cont +1; 
			} 
		} 
		 
		NoticiaTraducidaVO[] noticias = new NoticiaTraducidaVO[noticiasActivas.size()]; 
		for (int i=0;i<noticiasActivas.size();i++){ 
			noticias[i] = (NoticiaTraducidaVO) noticiasActivas.get(i);			 
		} 
		 
		 
		return noticias; 
		 
	} 
	 
	 
	private String[] devuelveIdiomasTraducibles(String[] idiomas, String idiomaPrioritario, String idiomaSecundario) throws Exception { 
		ArrayList idiomasList = new ArrayList(); 
 
		if (idiomaPrioritario.equals(idiomaSecundario)){ 
			idiomasList.add(idiomaPrioritario); 
		}else{ 
			idiomasList.add(idiomaPrioritario); 
			idiomasList.add(idiomaSecundario); 
		} 
 String[] idiomasArray = devuelveArraySinIdiomasPrioritarios(idiomas, idiomasList); 
 for (int i = 0;idiomasArray != null && i < idiomasArray.length; i++) 
 	idiomasList.add(idiomasArray[i]); 
 
 return (String[])idiomasList.toArray(new String[0]); 
 } 
 
 
 
 private String[] devuelveArraySinIdiomasPrioritarios (String[] idiomas, ArrayList listaIdiomas) throws Exception { 
 	ArrayList listaAux = new ArrayList(); 
 	listaAux = devuelveArraySinString(idiomas, (String)listaIdiomas.get(0)); 
 	for (int i = 1; i < listaIdiomas.size(); i++) { 
 		listaAux = devuelveArraySinString((String[])listaAux.toArray(new String[0]), (String)listaIdiomas.get(i)); 
 	} 
 	 
 	return (String[])listaAux.toArray(new String[0]); 
 } 
 
 
 
 private ArrayList devuelveArraySinString (String[] idiomas, String stringBorrar) throws Exception { 
 	ArrayList idiomasReturn = new ArrayList(); 
 	for (int i = 0; idiomas != null && i < idiomas.length; i++)	{ 
 		if (!(idiomas[i].equals(stringBorrar))) 
 			idiomasReturn.add(idiomas[i]); 
 	} 
 
 	return idiomasReturn; 
 } 
 
 
	 
 
	/** 
	 * Crea los xmls correspondientes a los informes de auditoría formato RSS y ATOM 
	 * @param 	Object[] odes: array de odes que formarán el feed 
	 * 			String titulo: titulo del feed 
	 * 			String link: link del feed 
	 * 			String descripcion: descripción del feed 
	 * 			String tipoFeed: tipo de feed RSS o ATOM 
	 * 			String idioma: idioma de navegación 
 	 * 			long feedNum: número de odes por feed 
 	 * 			String idTarea: identificador del feed que vamos a generar 
	 * @return void 
	 * @throws Exception 
	 */ 
	private void crearXMLauditoria(Object[] odes, String titulo, String link,
			String descripcion, String tipoFeed, String idioma, long feedNum,
			String idTarea, String tipoUrl) throws Exception {
		String idODE;
		String idiomaODE;
		String numVeces = "";

		Date fecha = new Date();

		try {
			if (logger.isDebugEnabled())
				logger.debug("se comienza a crear el feed... " + titulo);
			feed.setLink("http://"
					+ feedHost
					+ formatearMensaje(link, new String[] { idTarea,
							String.valueOf(feedNum), idioma }));
			feed.setAuthor("Agrega");
			feed.setPublishedDate(new Date());

			List entries = new ArrayList();

			SyndEntry entry;
			SyndImageImpl image = new SyndImageImpl();
			SyndContent imagenItem;

			MetadatoBasicoVO[] metadatosOdes = null;
			if (odes != null) {

				ParametroMetadatoVO[] parametros = new ParametroMetadatoVO[odes.length];
				for (int i = 0; i < odes.length; i++) {
					if (odes instanceof InformeMasVO[]) {
						idODE = ((InformeMasVO) odes[i]).getIdOde();
						idiomaODE = ((InformeMasVO) odes[i]).getIdioma();
						if (idiomaODE == null || idiomaODE.equals(""))
							if (logger.isDebugEnabled())
								logger.debug("No tiene idioma!!!");
						numVeces = numVeces.valueOf(((InformeMasVO) odes[i])
								.getNumVeces());
					} else {
						idODE = ((OdePublicadoVO) odes[i]).getIdODE();
						idiomaODE = ((OdePublicadoVO) odes[i]).getIdioma();
						fecha = ((OdePublicadoVO) odes[i]).getFecha().getTime();
					}
					ParametroMetadatoVO paramMetadato = new ParametroMetadatoVO();
					paramMetadato.setIdentificadorODE(idODE);
					paramMetadato.setIdioma(idiomaODE);
					paramMetadato.setBusquedaSimpleAvanzada("");

					parametros[i] = paramMetadato;

					if (logger.isDebugEnabled())
						logger
								.debug("Se lanza getSrvBuscarService.solicitarMetadato con los siguientes datos: idODE = <"
										+ idODE + ">");
					if (logger.isDebugEnabled())
						logger.debug("idioma = <" + idioma + "> numVeces = <"
								+ numVeces + "> fecha = <" + fecha + ">");
				}
				// long ini = System.currentTimeMillis();
				try {
					metadatosOdes = this.getSrvBuscarService()
							.solicitarMetadatosOdes(parametros);

					// long finmeta = System.currentTimeMillis();
					// logger.info("Tiempo obteniendo metadatos: " +
					// (finmeta-ini));
					// logger.info("Hemos obtenidos metadatos de [" +
					// metadatosOdes.length +"] odes de los [" +
					// parametros.length
					// +"] pedidos");
					// long iniciofeed = System.currentTimeMillis();
					for (int i = 0; i < metadatosOdes.length; i++) {
						entry = new SyndEntryImpl();
						try {

							if (metadatosOdes[i] != null) {
								if (logger.isDebugEnabled())
									logger
											.debug("tenemos todos los datos necesarios del ODE con identificador: <"
													+ metadatosOdes[i]
															.getIdentificadorODE()
													+ ">, idioma: <"
													+ metadatosOdes[i]
															.getIdioma() + ">");
								entry.setTitle(metadatosOdes[i].getTitulo());
								entry.setLink(formarURLodes(tipoUrl)
										+ "/"
										+ metadatosOdes[i].getIdioma()
										+ "/"
										+ metadatosOdes[i]
												.getIdentificadorODE());

								Date fechayHoraPublicacion = null;
								if (titulo.equals("ultimosOdes.title")) {
									SimpleDateFormat formatoFechayHora = new SimpleDateFormat(
											"yyyyMMdd" + " " + "HHmmss");
									fechayHoraPublicacion = formatoFechayHora
											.parse(metadatosOdes[i]
													.getFechaPublicacion()
													+ " "
													+ metadatosOdes[i]
															.getHoraPublicacion());
									entry.setUpdatedDate(fechayHoraPublicacion);
									entry
											.setPublishedDate(fechayHoraPublicacion);
								}

								if (odes instanceof InformeMasVO[]) {
									entry.setUri(numVeces);
								} else {
									entry
											.setPublishedDate(fechayHoraPublicacion);
									entry.setUri("0");
								}

								if (logger.isDebugEnabled())
									logger.debug("Se crea la imagen");
								image.setUrl(http + feedHost
										+ "/static/img/logo_agrega_red.gif");
								image.setLink(http + feedHost);
								image.setTitle("");

								imagenItem = new SyndContentImpl();
								imagenItem.setType("html");
								imagenItem.setMode("escaped");
								if (metadatosOdes[i].getImagen() != null
										&& !metadatosOdes[i].getImagen()
												.equals("")) {

									String fdf = comprobarImagen(feedHost,
											metadatosOdes[i].getImagen());
									String fdfamplia = "";
									if (fdf
											.equals(metadatosOdes[i]
													.getImagen()))
										fdfamplia = ampliarImagen(metadatosOdes[i]
												.getImagen());
									else
										fdfamplia = fdf;
									// imagenItem.setValue("<p img class='centrado'><a href='"
									// + http + feedHost +
									// ampliarImagen(metaBasicoVO.getImagen()) +
									// "'target='_blank'><img src='" + http +
									// feedHost + metaBasicoVO.getImagen() +
									// "'/></a></p>" +
									// metaBasicoVO.getDescripcion());
									imagenItem
											.setValue("<p img class='centrado'><a href='"
													+ http
													+ feedHost
													+ fdfamplia
													+ "'target='_blank'><img src='"
													+ http
													+ feedHost
													+ fdf
													+ "'/></a></p>"
													+ metadatosOdes[i]
															.getDescripcion());
									if (logger.isDebugEnabled())
										logger.debug("se inserta la imagen");
								} else {
									String imagenPorDefecto = feedHost
											+ "/"
											+ AgregaPropertiesImpl
													.getInstance()
													.getProperty(
															AgregaProperties.URL_IMAGEN_DEFECTO_100x100);
									imagenItem
											.setValue("<p img class='centrado'><a href='"
													+ http
													+ imagenPorDefecto
													+ "'target='_blank'><img src='"
													+ http
													+ imagenPorDefecto
													+ "'/></a></p>"
													+ metadatosOdes[i]
															.getDescripcion());
									if (logger.isDebugEnabled())
										logger.debug("no tiene imagen");
								}

								List contenido = new ArrayList();
								contenido.add(imagenItem);
								entry.setContents(contenido);
								entries.add(entry);
							} else {
								throw new Exception(
										"el resultado de la búsqueda es null");
							}

						} catch (Exception e) {
							logger
									.error(
											"No hemos podido recuperar los datos del ODE-ode erroneo: ",
											e);
						}

					}
				} catch (Exception e) {
					logger
							.error(
									"No hemos podido recuperar los datos de los ODEs: ",
									e);
				}
			} else {
				logger
						.info("No hay datos para generar el feed ATOM: "
								+ titulo);
				entry = new SyndEntryImpl();
				// if(logger.isDebugEnabled())logger.debug("No hay items para mostrar");
				entry.setTitle(traduceEtiqueta("no_hay_items", idioma));
				entry.setUri("");

				image.setUrl(http + feedHost
						+ "/static/img/logo_agrega_red.gif");
				image.setLink(http + feedHost);
				image.setTitle("");

				imagenItem = new SyndContentImpl();
				imagenItem.setType("html");
				imagenItem.setMode("escaped");
				imagenItem
						.setValue("<p img class='centrado'><a href='"
								+ http
								+ feedHost
								+ "/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png' target='_blank'><img src='"
								+ http
								+ feedHost
								+ "/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png'/></a></p>");

				List contenido = new ArrayList();
				contenido.add(imagenItem);
				entry.setContents(contenido);

				entries.add(entry);

			}
			// Escribimos en el fichero pero comprobamos previamente que la
			// auditoria ha ido bien
			if (entries.size() == 0) {
				logger
						.info("No hay datos para generar el feed ATOM: "
								+ titulo);
				entry = new SyndEntryImpl();
				// if(logger.isDebugEnabled())logger.debug("No hay items para mostrar");
				entry.setTitle(traduceEtiqueta("no_hay_items", idioma));
				entry.setUri("");

				image.setUrl(http + feedHost
						+ "/static/img/logo_agrega_red.gif");
				image.setLink(http + feedHost);
				image.setTitle("");

				imagenItem = new SyndContentImpl();
				imagenItem.setType("html");
				imagenItem.setMode("escaped");
				imagenItem
						.setValue("<p img class='centrado'><a href='"
								+ http
								+ feedHost
								+ "/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png' target='_blank'><img src='"
								+ http
								+ feedHost
								+ "/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png'/></a></p>");

				List contenido = new ArrayList();
				contenido.add(imagenItem);
				entry.setContents(contenido);

				entries.add(entry);

			}
			// if(logger.isDebugEnabled())logger.debug("comenzamos a generar los ficheros...");
			feed.setEntries(entries);
			feed.setImage(image);
			feed.setDescription(descripcion);

			feed.setTitle(traduceEtiqueta(titulo, idioma)
					+ getPropertyValue("textoRSS"));
			feed.setFeedType(getPropertyValue("feedTypeRSS"));

			if (tipoFeed != null && tipoFeed.equals(TIPO_ATOM)) {
				// ATOM
				feed.setTitle(traduceEtiqueta(titulo, idioma)
						+ getPropertyValue("textoATOM"));
				feed.setFeedType(getPropertyValue("feedTypeAtom"));
				if (logger.isDebugEnabled())
					logger
							.debug("SrvAgregadorRssServiceImpl - crearXMLauditoria: generando correctamente feed: "
									+ titulo);
			}

		} catch (Exception e) {
			logger.error(
					"[CREARXMLauditoria] Se ha producido un error al crear el fichero: "
							+ titulo + "--> ", e);
		}
	}
 
 
 
 private String comprobarImagen(String servidor, String imagen) throws Exception {
//	 Intentamos recoger la imagen del ODE.
	 try{
		 InputStreamReader in = new InputStreamReader(Proxy.getInputStream(new URL("http://"+servidor+imagen)));
		 in.close();
		 return imagen;
	 }catch(Exception ex){
//		 Ha sido imposible recoger la imagen del ODE, vamos a intentar recoger la de por defecto del nodo.
		 logger.warn("Problema al comprobar la imagen del ODE ["+ex.getMessage()+"]. Continuo intentandolo con la imagen por defecto del nodo.");
		 
	 }
//	 Intentamos recoger la imagen por defecto del nodo
	 try {
		 return "/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_100x100);	
	} catch (Exception e) {
	// No hemos podido recoger la imagen por defecto del nodo. Salimos con excepcion
		logger.error("Error intentando recoger la imagen por defecto del nodo para un ODE. Error:", e);
		throw new Exception("Error intentando recoger la imagen por defecto del nodo para un ODE. Error:" + e.getMessage());
	}
	 
 }
 
 /** 
	 * Genera a partir de la URL de la imagen pequeña la URL de la imagen ampliada 
	 * @param String con la URL de la imagen pequeña 
	 * @return String con la url ampliada que se abrirá en otra ventana 
	 */ 
 private String ampliarImagen (String url){
 	if(url!=null && !("").equals(url)){ 
			StringBuilder urlImagenReturn = new StringBuilder(); 
			String[] urlImagenArray = url.split(PUNTO); 
			for(int i=0; urlImagenArray!=null && i<urlImagenArray.length; i++){ 
				if(urlImagenArray[i].equals(IMAGEN_MINIMIZADA)) 
					urlImagenArray[i] = IMAGEN_AMPLIADA; 
				urlImagenReturn.append(urlImagenArray[i]); 
			} 
			return(urlImagenReturn.toString().trim()); 
		} 
 	return ""; 
 } 
 
 
	/** 
	 * Crea los xmls correspondientes a los informes de auditoría formato RSS y ATOM de forma federada 
	 * @param 	String idFeedLocal: identificador del feed local ha recuperar 
	 * 			String titulo: titulo del feed 
	 * 			String link: link del feed 
	 * 			String descripcion: descripción del feed 
	 * 			String tipoFeed: tipo de feed RSS o ATOM 
	 * 			String idioma: idioma de navegación 
 	 * 			long feedNum: número de odes por feed 
 	 * 			String idTarea: identificador del feed que vamos a generar 
	 * @return void 
	 * @throws Exception 
	 */
 private void crearXMLauditoriaFederada (String idFeedLocal,String titulo,String link, String descripcion, String tipoFeed,String idioma,long feedNum,String idTarea) throws Exception { 
 	 	 
 try{
 	String fileName=getPropertyValue(idFeedLocal);
 		//obtenemos nodo de los que vamos a obtener feed
 		NodoVO[] nodosDB=this.getSrvNodoService().listarNodos(); 
 		NodoVO[] nodos; 
 		if (nodosDB!=null){ 
 			nodos=new NodoVO[nodosDB.length+1]; 
 		}else{ 
 			nodos=new NodoVO[1]; 
 		} 
 		NodoVO nodoLocal=new NodoVO(); 
 		nodoLocal.setUrl(feedHost); 
 		nodos[0]=nodoLocal; 
 		if(logger.isDebugEnabled())logger.debug("Recuperadas comunidades federadas."); 
 		for (int i=1; i<nodos.length; i++){ 
 			nodos[i]=nodosDB[i-1]; 
 		} 
 
 		long feedNumPorNodo=(feedNum/nodos.length)>FEED_NUM_MIN_FEDERADO?(feedNum/nodos.length):FEED_NUM_MIN_FEDERADO;
			
			//Configuramos datos del proxy si lo hubiera
 		String encoded ="";
 		//TODO ¿Y si hay proxy pero no usuario/clave?
			if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USAPROXY).equals("true")){
 			System.setProperty("http.proxyHost",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOSTPROXY));
 			System.setProperty("http.proxyPort",AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORTPROXY));
 			String claveProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CLAVEPROXY);
 			String usuarioProxy = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USUARIOPROXY);
 			
 			encoded =new String(Base64Coder.encode((usuarioProxy+":"+claveProxy).getBytes())); 		
			}
			
			//Obtenemos el tiempo limite de conexión con un nodo
			String sTimeout = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS_FEDERADO_TIMEOUT);
			int timeout = 0;
			try{
				timeout =Integer.parseInt(sTimeout);
			}catch(NumberFormatException e){
				logger.error("La propiedad "+ AgregaProperties.RSS_FEDERADO_TIMEOUT + " no es númerica. Cogemos timeout por defecto " +TIMEOUT_DEFAULT +" milisegundos",e);
				timeout = TIMEOUT_DEFAULT;
			}
			
 		List todosEntries = new ArrayList(); 
			URL feedUrl = null;
 		URLConnection feedUrlConnection=null;
 		XmlReader xmlFeed = null;
 		String rss = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS);
 		for (int i=0; i<nodos.length; i++){ 
 		try{ 
	 		rssPath=nodos[i].getUrl(); 
	 		SyndFeedInput input = new SyndFeedInput(); 
	 		
	 		if(hashMapVersiones==null || ! hashMapVersiones.containsKey(nodos[i].getUrl())){
		 		feedUrl = new URL(http + rssPath+"/visualizadorcontenidos2/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do?idFeed="+idFeedLocal+"&feedNum="+feedNumPorNodo+"&idiomaNavegacion="+idioma);
		 			 		
	 			feedUrlConnection= feedUrl.openConnection();
	 			feedUrlConnection.setConnectTimeout(timeout);
	 			if(!"".equals(encoded))//Si existe proxy nos autenticamos
	 				feedUrlConnection.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
	 				 			
	 			
	 			try{
	 				xmlFeed = new XmlReader(feedUrlConnection);
	 			}catch (FileNotFoundException e){
	 				logger.error("No se encuentra el fichero en: "+ feedUrl.toString()+ ". Probamos con versión 1 --> "+http + rssPath+ rss +"/"+ fileName);
	 				feedUrl = new URL(http + rssPath+ rss +"/"+ fileName);
	 				feedUrlConnection = feedUrl.openConnection();
	 				feedUrlConnection.setConnectTimeout(timeout);
	 				if(!"".equals(encoded))//Si existe proxy nos autenticamos
	 					feedUrlConnection.setRequestProperty("Proxy-Authorization", "Basic " + encoded);	
	 				xmlFeed = new XmlReader(feedUrlConnection);
	 				if(hashMapVersiones == null) hashMapVersiones = new HashMap();
	 				hashMapVersiones.put(nodos[i].getUrl(), "1");//Versión 1 de RSS (Accedemos directamente al .xml)
		 		}
	 		}else{
	 			if(logger.isDebugEnabled())logger.debug("Version 1 de obtención de feed. Accedemos directamente al archivo --> " + fileName);
	 			feedUrl = new URL(http + rssPath+ rss +"/"+ fileName);
	 			feedUrlConnection = feedUrl.openConnection();
 				feedUrlConnection.setConnectTimeout(timeout);
 				if(!"".equals(encoded))//Si existe proxy nos autenticamos
 					feedUrlConnection.setRequestProperty("Proxy-Authorization", "Basic " + encoded);	
 				xmlFeed = new XmlReader(feedUrlConnection); 
	 		}

 			SyndFeed feedPrivado = input.build(xmlFeed);
 			
	 		List entries = feedPrivado.getEntries(); 
	 		if(logger.isDebugEnabled())logger.debug("******* Fichero procesado,hemos obtenido el feed ["+titulo+"] del nodo: " + feedUrl.getHost()+feedUrl.getPath()+"********");
 
	 		for (int j=0;j<entries.size();j++){ 
	 			todosEntries.add(entries.get(j)); 
	 		} 
 		}catch (Exception e){
 			logger.error("[CREARXMLauditoriaFederada1] Se ha producido un error al leer el fichero: " + feedUrl.getHost()+ feedUrl.getPath()+ " con titulo: " + titulo + " --> ", e); 
 		} 
 
 	} 
 
// 	aqui hay que se ordena la lista y luego nos quedamos con los primeros diez elementos 
// 	que son los que hay que escribir en el fichero 
 	 
 	if ((((SyndEntryImpl)todosEntries.get(0)).getUri())!= null && !(((SyndEntryImpl)todosEntries.get(0)).getUri().equals(""))
 			&& !(((SyndEntryImpl)todosEntries.get(0)).getUri().equals("0"))){ 
 		Collections.sort(todosEntries, new NumVecesComparator()); 
 		if(logger.isDebugEnabled())logger.debug("Ordenando lista odes más..."); 
 	}else{ 
 		Collections.sort(todosEntries, new FechaComparator()); 
 		if(logger.isDebugEnabled())logger.debug("Ordenando lista últimos odes publicados."); 
 	} 
 	List listaOrdenada=new ArrayList(); 
// 	if (todosEntries.size()==10){ 
// 		listaOrdenada=todosEntries; 
// 	}else{ 
// 		for (int i=0;i<todosEntries.size()&& i<10;i++){ 
// 			listaOrdenada.add(todosEntries.get(i)); 
// 		} 
// 	} 
 	
 		for (int i=0, j=0;i<todosEntries.size()&& j<feedNum;i++){ 
 			SyndEntry entry = (SyndEntry)todosEntries.get(i); 
 			if (!entry.getTitle().equals(traduceEtiqueta("no_hay_items",idioma))){ 
 				listaOrdenada.add(todosEntries.get(i)); 
 				j++; 
 			} 
 		} 
 	 
 	SyndImageImpl image = new SyndImageImpl(); 
 	image.setUrl(http + feedHost +"/static/img/logo_agrega_red.gif"); 
 image.setLink(http + feedHost); 
 image.setTitle(""); 
 	 
// 	escribimos el fichero xml con los datos de la lista 
// 	if(logger.isDebugEnabled())logger.debug("Comienza a crear el fichero xml"); 
	 feed.setLink("http://" + feedHost + formatearMensaje(link, new String[]{idTarea,String.valueOf(feedNum),idioma})); 
	 feed.setDescription(descripcion); 
 	feed.setImage(image); 
 	feed.setAuthor("Agrega");
	 feed.setPublishedDate(new Date()); 
//	 Escribimos en el fichero 
//	 if(logger.isDebugEnabled())logger.debug("comenzamos a generar los ficheros..."); 
	 feed.setEntries(listaOrdenada); 
	 //RSS 
	 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoRSS")); 
 feed.setFeedType(getPropertyValue("feedTypeRSS"));
 
 if(tipoFeed!=null && tipoFeed.equals(TIPO_ATOM)){
	 //ATOM 
 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoATOM")); 
 	feed.setFeedType(getPropertyValue("feedTypeAtom")); 
 	if(logger.isDebugEnabled())logger.debug("Generando correctamente feed: " + titulo); 
 }
 
 }catch (Exception e){ 
 	logger.error("[CREARXMLauditoriaFederada] Se ha producido un error al crear el fichero: " + titulo + " --> ", e); 
 } 
 } 
 
 
	/** 
	 * Crea los xmls correspondientes a las descargas formato RSS y ATOM 
	 * @param 	DescDescargaVO[] descargas: array de descripción de descargas que formarán el feed
	 * DescargaVO[] descargaFecha: array de descargas que formarán el feed 
	 * 			String titulo: titulo del feed 
	 * 			String link: link del feed 
	 * 			String descripcion: descripción del feed 
	 * 			String tipoFeed: tipo de feed RSS o ATOM 
	 * 			String idioma: idioma de navegación 
 	 * 			long feedNum: número de odes por feed 
 	 * 			String idTarea: identificador del feed que vamos a generar 
 	 * 			String tipoUrl: indica si las urls a generar son de la parte publica o de la privada 
	 * @return void 
	 * @throws Exception 
	 */ 
 private void crearXMLdescargas (DescDescargaVO[] descargas,DescargaVO[] descargaFecha, String titulo, String link, String descripcion, String tipoFeed,String idioma,long feedNum,String idTarea,String tipoUrl) throws Exception { 
 	 
 try{ 
			if(logger.isDebugEnabled())logger.debug("Se comienza a crear el feed... " + titulo); 
 	feed.setLink("http://"+ feedHost + formatearMensaje(link, new String[]{idTarea,String.valueOf(feedNum),idioma})); 
	 feed.setDescription(descripcion); 
	 
	 
	 List entries = new ArrayList(); 
	 SyndEntry entry; 
//	 SyndContent description; 
	 SyndContent imagenItem; 
	 DescDescargaVO descarga; 
	 
	 int totalDescargas=descargas!=null?descargas.length:0;
	 
	 if(totalDescargas>feedNum)
	 {
	 	totalDescargas=(int)feedNum;
	 }
	 
	 
	 for (int i=0; i<totalDescargas;i++){ 
	 	descarga = descargas[i]; 
	 entry = new SyndEntryImpl(); 
	 entry.setTitle(descarga.getTitulo()); 
	 entry.setLink(formarURLdescargas(tipoUrl)+descargaFecha[i].getIdentificador()+"&titulo="+descarga.getTitulo()); 
	 entry.setPublishedDate(descargaFecha[i].getFecha().getTime()); 
	 entry.setUpdatedDate(descargaFecha[i].getFecha().getTime()); 

 
	 imagenItem = new SyndContentImpl(); 
	 imagenItem.setType("html"); 
	 imagenItem.setMode("escaped"); 
	 imagenItem.setValue(descarga.getDescripcion());
	 
	 
	 List contenido = new ArrayList(); 
	 contenido.add(imagenItem); 
	 entry.setContents(contenido); 
	 
	 entries.add(entry); 
	 } 
	 
	 SyndImageImpl image = new SyndImageImpl(); 
	 image.setUrl(http + feedHost + "/static/img/logo_agrega_red.gif"); 
 image.setLink(http + feedHost); 
 image.setTitle(""); 

	 
	 //Escribimos en el fichero 
	 feed.setEntries(entries);
	 feed.setAuthor("Agrega");
	 feed.setPublishedDate(new Date());
	 feed.setImage(image); 
	 //RSS 
	 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoRSS")); 
 feed.setFeedType(getPropertyValue("feedTypeRSS")); 
 
 if(tipoFeed!=null && tipoFeed.equals(TIPO_ATOM)){
 	 //ATOM 
 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoATOM")); 
 	feed.setFeedType(getPropertyValue("feedTypeAtom")); 
 	if(logger.isDebugEnabled())logger.debug("Generando correctamente feed: <" + titulo+">");
 }
 
 }catch (Exception e){ 
 	logger.error("[CREARXMLdescargas] Se ha producido un error al crear el fichero: " + titulo + "--> ", e); 
 } 
 } 
 /** 
	 * Crea los xmls correspondientes a las noticias formato RSS y ATOM 
	 * @param 	NoticiaTraducidaVO[] noticias: array de noticias que formarán el feed 
	 * 			String titulo: titulo del feed 
	 * 			String link: link del feed 
	 * 			String descripcion: descripción del feed 
	 * 			String tipoFeed: tipo de feed RSS o ATOM 
	 * 			String idioma: idioma de navegación 
 	 * 			long feedNum: número de odes por feed 
 	 * 			String idTarea: identificador del feed que vamos a generar 
 	 * 			String tipoUrl: indica si las urls a generar son de la parte publica o de la privada
	 * @return void 
	 * @throws Exception 
	 */ 
 private void crearXMLnoticias (NoticiaTraducidaVO[] noticias, String titulo, String link, String descripcion, String tipoFeed,String idioma,long feedNum,String idTarea, String tipoUrl) throws Exception { 
 	 
 try{ 
			if(logger.isDebugEnabled())logger.debug("Se comienza a crear el feed... " + titulo); 
 	feed.setLink("http://"+ feedHost + formatearMensaje(link, new String[]{idTarea,String.valueOf(feedNum),idioma})); 
	 feed.setDescription(descripcion); 
	 
	 
	 List entries = new ArrayList(); 
	 SyndEntry entry; 
//	 SyndContent description; 
	 SyndContent imagenItem; 
	 NoticiaTraducidaVO noticia; 

	 int totalNoticias=noticias.length;
	 
	 if(totalNoticias>feedNum)
	 {
	 	totalNoticias=(int)feedNum;
	 }
	 
	 for (int i=0; i<totalNoticias;i++){ 
	 	noticia = noticias[i]; 
	 entry = new SyndEntryImpl(); 
	 entry.setTitle(noticia.getTitulo()); 
	 entry.setLink(formarURLnoticias(tipoUrl)+noticia.getIdNoticia()); 
	 entry.setPublishedDate(noticia.getFechaPublicacion().getTime()); 
	 entry.setUpdatedDate(noticia.getFechaPublicacion().getTime()); 
 
	 imagenItem = new SyndContentImpl(); 
	 imagenItem.setType("html"); 
	 imagenItem.setMode("escaped"); 
	 
	 if (noticia.getURLImagen()!=null && !noticia.getURLImagen().equals("") && !noticia.getURLImagen().equals("http://localhost")){ 
	 	String url=http + feedHost + noticia.getURLImagen(); 
	 	try{ 
	 	URL urlImagen= new URL(url.substring(0,url.lastIndexOf("/")+1)+codificarURL(url.substring(url.lastIndexOf("/")+1))); 
	 	BufferedImage image = ImageIO.read(urlImagen); 
		 int w = image.getWidth(); //if you want... 
		 int h = image.getHeight();//if you want... 
		 if (w<300 && h<300){ 
		 	// imagen en su tamaño 
		 	imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost + noticia.getURLImagen() + "'target='_blank'><img src='" + http + feedHost + noticia.getURLImagen() +"' width='" + w + "' height='" + h + "'/></a></p>" + noticia.getResumen()); 
		 }else if (w>h){ //imagen horizontal 
		 		int ancho=300; 
		 		int altura=(ancho*h)/w; 
		 		imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost + noticia.getURLImagen() + "'target='_blank'><img src='" + http + feedHost + noticia.getURLImagen() +"' width='" + ancho + "' height='" + altura + "'/></a></p>" + noticia.getResumen()); 
		 	}else if (h>w){ //imagen vertical 
		 		int altura=300; 
		 		int ancho=(altura*w)/h; 
		 		imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost + noticia.getURLImagen() + "'target='_blank'><img src='" + http + feedHost + noticia.getURLImagen() +"' width='" + ancho + "' height='" + altura + "'/></a></p>" + noticia.getResumen()); 
		 	}else if (h==w){ // imagen cuadrada 
		 		int ancho=250; 
		 		int altura=ancho; 
		 		imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost + noticia.getURLImagen() + "'target='_blank'><img src='" + http + feedHost + noticia.getURLImagen() +"' width='" + ancho + "' height='" + altura + "'/></a></p>" + noticia.getResumen()); 
		 	} 
	 	}catch(Exception e){ 
	 		logger.error("Error al buscar la imagen", e); 
	 	} 
	 }else{ 
	 	imagenItem.setValue(noticia.getResumen()); 
	 } 
	 List contenido = new ArrayList(); 
	 contenido.add(imagenItem); 
	 entry.setContents(contenido); 
	 
	 entries.add(entry); 
	 } 
	 
	 SyndImageImpl image = new SyndImageImpl(); 
	 image.setUrl(http + feedHost + "/static/img/logo_agrega_red.gif"); 
 image.setLink(http + feedHost); 
 image.setTitle(""); 
	 
	 //Escribimos en el fichero 
	 feed.setEntries(entries);
	 feed.setAuthor("Agrega");
	 feed.setPublishedDate(new Date());
	 feed.setImage(image); 
	 //RSS 
	 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoRSS")); 
 feed.setFeedType(getPropertyValue("feedTypeRSS")); 
 
 if(tipoFeed!=null && tipoFeed.equals(TIPO_ATOM))
 {
	 //ATOM 
	 	feed.setTitle(traduceEtiqueta(titulo,idioma) + getPropertyValue("textoATOM")); 
 feed.setFeedType(getPropertyValue("feedTypeAtom"));
 }
 if(logger.isDebugEnabled())logger.debug("Generando correctamente feed: <" + titulo+">");
 
 }catch (Exception e){ 
 	logger.error("[CREARXMLnoticias] Se ha producido un error al crear el fichero: " + titulo + "--> ", e); 
 } 
 }
 
 
 /** 
	 * Elimina los caracteres que puedan ser problematicos a la hora de formar la URL 
	 * @return String con la url 
	 * @throws Exception 
	 */ 
 private String formarURLodes (String tipoUrl)throws Exception { 
	 if (tipoUrl.equalsIgnoreCase(FEED_PRIVADO))
		 return "http://"+ feedHost + "/ODE";
	 return "http://"+ feedHost + "/ODE2";
 } 
 
 
 /** 
	 * Forma la parte común de la url para poder visualizar el ODE. 
	 * @param String url: Url a codificar 
	 * @return String con la url 
	 * @throws Exception 
	 */ 
 private String codificarURL (String url) { 
 	url=url.replaceAll("%", "%25"); 
 	url=url.replaceAll("\\$", "%26"); 
 	url=url.replaceAll(";", "%3B"); 
 	url=url.replaceAll("\\?", "%3F"); 
 	url=url.replaceAll("/", "%2F"); 
 	url=url.replaceAll(":", "%3A"); 
 	url=url.replaceAll("#", "%23"); 
 	url=url.replaceAll("&", "%24"); 
 	url=url.replaceAll("=", "%3D"); 
 	url=url.replaceAll("\\+", "%2B"); 
 	url=url.replaceAll(",", "%2C"); 
 	url=url.replaceAll(" ", "%20"); 
 	url=url.replaceAll("<", "%3C"); 
 	url=url.replaceAll(">", "%3E"); 
 	url=url.replaceAll("~", "%7E"); 
 	return url; 
 } 
 
 
 /** 
	 * Forma la parte común del nombre fichero para poder visualizar el ODE. 
	 * @param String nombreFichero: nombreFichero a codificar 
	 * @return String con el nombreFichero 
	 * @throws Exception 
	 */ 
 private String codificarNombreFichero (String nombreFichero) { 
 	nombreFichero=nombreFichero.replaceAll("\\?", "%3F"); 
 	nombreFichero=nombreFichero.replaceAll("/", "%2F"); 
 	nombreFichero=nombreFichero.replaceAll(":", "%3A"); 
 	nombreFichero=nombreFichero.replaceAll("<", "%3C"); 
 	nombreFichero=nombreFichero.replaceAll(">", "%3E"); 
 	nombreFichero=nombreFichero.replaceAll("\\*", "%8E"); 
 	nombreFichero=nombreFichero.replaceAll("\\|", "%7C"); 
 	nombreFichero=nombreFichero.replaceAll("\"", "%22"); 
 	nombreFichero=nombreFichero.replaceAll("\\.", "%2E"); 
 	nombreFichero=nombreFichero.replaceAll("\\\\", "%5C"); 
 	nombreFichero=nombreFichero.replaceAll(" ", "20%"); 
 	return nombreFichero; 
 } 
 
 /** 
	 * Forma la parte común de la url para poder visualizar la noticia. 
	 * @return String con la url 
	 * @throws Exception 
	 */ 
 private String formarURLnoticias (String tipoUrl)throws Exception { 
	 if (tipoUrl.equalsIgnoreCase(FEED_PRIVADO))
		 return "http://"+ feedHost + "/visualizadorcontenidos/Portada/NoticiasVer.do?id="; 
	 return "http://"+ feedHost + "/visualizadorcontenidos2/Portada/NoticiasVer.do?id="; 
 } 
 
 /** 
	 * Forma la parte común de la url para poder visualizar la descarga. 
	 * @return String con la url 
	 * @throws Exception 
	 */ 
 private String formarURLdescargas (String tipoUrl)throws Exception { 
	 if (tipoUrl.equalsIgnoreCase(FEED_PRIVADO))
		 return "http://"+ feedHost + "/visualizadorcontenidos/Descargas/DescargasDescarga.do?identificador="; 
	 return "http://"+ feedHost + "/visualizadorcontenidos2/Descargas/DescargasDescarga.do?identificador="; 
 } 
 
 /** 
	 * Obtiene el valor almacenado en el fichero properties auditoria de una determinada cadena 
	 * @param sKey cadena de la que se quiere obtener el valor 
	 * @return String 
	 * @throws Exception 
	 */ 
	private String getPropertyValue(String sKey) throws IOException 
	{ 
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/agregadorRSS.properties"); 
		if (this.pAgregadorRSSProperties == null) 
		{ 
			pAgregadorRSSProperties = new java.util.Properties(); 
			pAgregadorRSSProperties.load(fIsSpringProperties); 
		} 
		fIsSpringProperties.close(); 
 
		// devolvemos la propiedad 
		return pAgregadorRSSProperties.getProperty(sKey); 
	} 
	 
	 
	 
	public static String formatearMensaje(String message,String [] args) 
	{ 
 	MessageFormat formatter = new MessageFormat(""); 
 if (args!=null && args.length>0) 
 { 
 		formatter.applyPattern(message); 
 message = formatter.format((args)); 
 } 
 return message; 
 } 
 
 
	/** 
	 * Devuelve la información relativa a los feeds relacionados con los ODEs 
	 * 
	 * @return feedVO FeedsVO[] que contiene la información de los feeds 
	 * @throws Exception 
	 */ 
	protected es.pode.agregador.negocio.agregador.servicio.FeedVO[] handleDevuelveFeeds() throws Exception { 
//		if(logger.isDebugEnabled())logger.debug("recuperando los datos de los feeds... ");
		String feeds = getPropertyValue("feeds");
		String[] aFeeds=feeds.split(",");
		FeedVO[] feedsVO = new FeedVO[aFeeds.length];
		for(int i=0;i<aFeeds.length;i++){
			FeedVO feedVO = new FeedVO(); 
	 	feedVO.setTitulo(getPropertyValue(aFeeds[i]+".title")); 
	 	feedVO.setDescripcion(""); 
	 	feedVO.setUrl(feedHost + getPropertyValue(aFeeds[i]+".url")); 
	 	feedVO.setPath(feedHost + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS) + "/" + getPropertyValue(aFeeds[i]+".fileName"));
	 	feedVO.setPeriodicidad(getPropertyValue(aFeeds[i]+".periodicidad")); 
	 	feedVO.setId(getPropertyValue(aFeeds[i]+".id")); 
	 	feedsVO[i]=feedVO;
		}
		if(logger.isDebugEnabled())logger.debug("Se han recuperado los datos de los feeds correctamente... ");

		return feedsVO; 
	} 
	 
 
	 
	/** 
	 * Este metodo recibe un parametro con los datos necesarios para realizar la busqueda 
	 * Devuelve un DataHandler con los resultados de la búsqueda. 
	 * 
	 * @param parametrosBusqueda ParametrosBusquedaAgregadorVO : Parametro con los datos para realizar la busqueda. 
	 * @param tipoRSS String : Tipo de feed ATOM o RSS.
	 * @param feedNum long: Número de odes en el feed. 
	 * @return String: Contiene la informacion recogida del xml generado tras realizar la busqueda 
	 */ 
	protected String handleDevuelveRssBusqueda(ParametrosBusquedaAgregadorVO parametrosBusqueda, String tipoRSS,long feedNum) throws Exception { 
		return handleDevuelveRssBusquedaPorTipoBuscador(parametrosBusqueda, tipoRSS, feedNum, CONTEXTO_BUSCADOR_PRIVADO);
	} 


	/** 
	 * Este metodo recibe un parametro con los datos necesarios para realizar la busqueda 
	 * Devuelve un DataHandler con los resultados de la búsqueda. 
	 * 
	 * @param parametrosBusqueda ParametrosBusquedaAgregadorVO : Parametro con los datos para realizar la busqueda. 
	 * @param tipoRSS String : Tipo de feed ATOM o RSS.
	 * @param feedNum long: Número de odes en el feed. 
	 * @param tipoBuscador String: Determina si el buscador usado sera el publico o el privado. 
	 * @return String: Contiene la informacion recogida del xml generado tras realizar la busqueda 
	 */ 
	@Override
	protected String handleDevuelveRssBusquedaPorTipoBuscador(
			ParametrosBusquedaAgregadorVO parametrosBusqueda, String tipoRSS,
			long feedNum, String tipoBuscador) throws Exception {
		
		String buscador="buscador";
		if(tipoBuscador.contentEquals(CONTEXTO_BUSCADOR_PUBLICO))
			buscador="buscador2"; //ajustamos a buscador publico
		
		feedNum = (feedNum > FEED_NUM_MAX)? FEED_NUM_MAX:feedNum; 
		 
		String dia= ""; 
		String mes= ""; 
		String año= ""; 
		String urlBusqueda = "/buscador/BuscarAvanzadoCU/BuscarAvanzadoCU.do?"+ 
							"buscContenido="+URLEncoder.encode(parametrosBusqueda.getPalabrasClave(), "ISO-8859-1")+"&idioma="+parametrosBusqueda.getIdiomaBusqueda()+ 
							"&formato="+parametrosBusqueda.getFormato()+"&idODE="+parametrosBusqueda.getIdODE()+"&recurso="+parametrosBusqueda.getRecurso()+"&procesoCognitivo="+ 
							parametrosBusqueda.getProcesoCognitivo()+"&contexto="+parametrosBusqueda.getContexto()+"&edad="+ 
							URLEncoder.encode(parametrosBusqueda.getEdad(), "ISO-8859-1")+"&autor="+URLEncoder.encode(parametrosBusqueda.getAutor(), "ISO-8859-1")+ 
							"&diaPublic="+ dia +
							"&mesPublic="+mes+
							"&anyoPublic="+año+
							"&c_s_secuencia="+((parametrosBusqueda.getSecuencia()!=null)?parametrosBusqueda.getSecuencia():"")+ "&valoracion="+ 
							parametrosBusqueda.getValoracion().replaceAll("-", " ")+"&enlaceTaxSelec="+((parametrosBusqueda.getEnlaceTaxSelec()!=null)?parametrosBusqueda.getEnlaceTaxSelec():"")+ 
							"&enlaceComuSelec="+parametrosBusqueda.getEnlaceComunidadesSeleccionadas() 
					//		"&enlaceComuSelec="+((parametrosBusqueda.getTipoBusqueda()!=null && parametrosBusqueda.getTipoBusqueda().equals("01"))?parametrosBusqueda.getComunidadesSeleccionadas():"")
							+"&idTesauro="+parametrosBusqueda.getIdTesauro()+ 
							"&nivelAgreg="+parametrosBusqueda.getNivelAgregacion()+"&destinatarios="+ 
							parametrosBusqueda.getDestinatarios()+"&keyword="+URLEncoder.encode(parametrosBusqueda.getKeyword(),"ISO-8859-1")+"&tipoBusqueda="+ 
							parametrosBusqueda.getTipoBusqueda()
							+"&busqSimpleAvanz=BUSCAR_SIMPLE"; 
		
//		mapeo el VO que recibo a ParametrosBusquedaAvanzadaVO para llamar al buscador 
		ParametrosBusquedaAvanzadaVO30 paramBusq = mapearParametrosBusqueda(parametrosBusqueda,Long.valueOf(feedNum).intValue(),Long.valueOf(FEED_NUM_MAX).intValue()); 
		ValoresBusquedaVO[] valoresBusqueda=null; 
		 
		try{ 
			ResultadoBusquedaVO resultadosBusqueda = this.getSrvBuscarService().buscarAvanzado(paramBusq); 
			valoresBusqueda = new ValoresBusquedaVO[resultadosBusqueda.getResultadoBusqueda().length]; 
			valoresBusqueda = resultadosBusqueda.getResultadoBusqueda(); 
			if(logger.isDebugEnabled())logger.debug("Se han encontrado "+valoresBusqueda.length + " resultados"); 
		}catch (Exception e){ 
			logger.error("Error al realizar la busqueda", e); 
		} 
		String xmlFeedBusqueda=""; 
		try{ 
			crearXMLBusqueda(valoresBusqueda, urlBusqueda, obtenerDescripcionRSS(parametrosBusqueda), 
					parametrosBusqueda.getIdiomaBusqueda(), parametrosBusqueda.getIdiomaNavegacion(),tipoRSS,feedNum,tipoBuscador); 
			SyndFeedOutput output = new SyndFeedOutput(); 
			xmlFeedBusqueda = output.outputString(feed); 
//			if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - devuelveRssBusqueda: Se ha creado correctamente el xml con los resultados de lo busqueda"); 
		}catch (Exception e){ 
			logger.error("Se ha producido un error al generar el xml con los resultados de la busqueda",e); 
		} 

		if(logger.isDebugEnabled())logger.debug("Devolvemos el xml en un DataHandler"); 
		return xmlFeedBusqueda; 
	}
	 
	 
	private String obtenerDescripcionRSS (ParametrosBusquedaAgregadorVO parametrosBusqueda){ 
		 
		String descripcion = ""; 
		try{ 
			descripcion = traduceEtiqueta("textoResultadoBusqueda",parametrosBusqueda.getIdiomaNavegacion()) + " "; 
			descripcion = descripcion + parametrosBusqueda.getPalabrasClave() + " " + 
			traduceEtiqueta("textoParametrosBusqueda",parametrosBusqueda.getIdiomaNavegacion()) + " " 
			+ ((parametrosBusqueda.getIdiomaBusqueda()!=null && !parametrosBusqueda.getIdiomaBusqueda().equals(""))? traduceEtiqueta("idioma",parametrosBusqueda.getIdiomaNavegacion()) + ": " + I18n.getInstance().obtenerIdiomaTraducido(parametrosBusqueda.getIdiomaBusqueda(), parametrosBusqueda.getIdiomaNavegacion()):"") 
//			+ ((parametrosBusqueda.getAreaCurricular()!=null && !parametrosBusqueda.getAreaCurricular().equals(""))? "; "+ traduceEtiqueta("areaCurricular",parametrosBusqueda.getIdiomaNavegacion()) + ": " + convertirStringAreaCurricular(parametrosBusqueda.getAreaCurricular(), parametrosBusqueda.getIdiomaNavegacion()):"") 
			+ ((parametrosBusqueda.getEnlaceTaxSelec()!=null && !parametrosBusqueda.getEnlaceTaxSelec().equals(""))? "; "+ tranformarEnlaceTaxSelec(parametrosBusqueda.getEnlaceTaxSelec(),parametrosBusqueda.getIdiomaNavegacion()):"")
			+ ((parametrosBusqueda.getNivelAgregacion()!=null && !parametrosBusqueda.getNivelAgregacion().equals(""))? "; "+ traduceEtiqueta("nivelAgregacion",parametrosBusqueda.getIdiomaNavegacion()) + ": " + nivelAgregacion(parametrosBusqueda.getNivelAgregacion(), parametrosBusqueda.getIdiomaNavegacion()):"") 
			+ ((parametrosBusqueda.getFormato()!=null && !parametrosBusqueda.getFormato().equals(""))? "; "+ traduceEtiqueta("tipoFormato",parametrosBusqueda.getIdiomaNavegacion()) + ": " + formato (parametrosBusqueda.getFormato(), parametrosBusqueda.getIdiomaNavegacion()):"")
			+ ((parametrosBusqueda.getIdODE()!=null && !parametrosBusqueda.getIdODE().equals(""))? "; "+ traduceEtiqueta("idODE",parametrosBusqueda.getIdiomaNavegacion()) + ": " + parametrosBusqueda.getIdODE():"") 
			+ ((parametrosBusqueda.getRecurso()!=null && !parametrosBusqueda.getRecurso().equals(""))? "; "+ traduceEtiqueta("recurso",parametrosBusqueda.getIdiomaNavegacion()) + ": " + traducirVocabulario (parametrosBusqueda.getRecurso(), BuscarController.getPropertyValue("recurso"), parametrosBusqueda.getIdiomaNavegacion()): "") 
			+ ((parametrosBusqueda.getProcesoCognitivo()!=null && !parametrosBusqueda.getProcesoCognitivo().equals(""))? ", "+ traduceEtiqueta("procesoCognitivo",parametrosBusqueda.getIdiomaNavegacion()) + ": " + traducirVocabulario (parametrosBusqueda.getProcesoCognitivo(), BuscarController.getPropertyValue("procesoCognitivo"), parametrosBusqueda.getIdiomaNavegacion()): "") 
			+ ((parametrosBusqueda.getContexto()!=null && !parametrosBusqueda.getContexto().equals(""))? "; "+ traduceEtiqueta("contexto",parametrosBusqueda.getIdiomaNavegacion()) + ": " + traducirVocabulario (parametrosBusqueda.getContexto(), BuscarController.getPropertyValue("contexto"), parametrosBusqueda.getIdiomaNavegacion()): "") 
			+ ((parametrosBusqueda.getEdad()!=null && !parametrosBusqueda.getEdad().equals(""))? "; "+ traduceEtiqueta("edad",parametrosBusqueda.getIdiomaNavegacion()) + ": " + parametrosBusqueda.getEdad():"") 
			+ ((parametrosBusqueda.getAutor()!=null && !parametrosBusqueda.getAutor().equals(""))? "; "+ traduceEtiqueta("autor",parametrosBusqueda.getIdiomaNavegacion()) + ": " + parametrosBusqueda.getAutor():"") 
			+ ((parametrosBusqueda.getFechaPublicacion()!=null && !parametrosBusqueda.getFechaPublicacion().equals(""))? "; "+ traduceEtiqueta("fechaPublicacion",parametrosBusqueda.getIdiomaNavegacion()) + ": " + formatoFecha (parametrosBusqueda.getFechaPublicacion(), "", "", ""):"") 
//			+ ((parametrosBusqueda.getSecuencia()!=null && !parametrosBusqueda.getSecuencia().equals(""))? "; "+ traduceEtiqueta("secuenciacion",parametrosBusqueda.getIdiomaNavegacion()) + ": " + secuenciacion (parametrosBusqueda.getSecuencia(), parametrosBusqueda.getIdiomaNavegacion()):"") 
			+ ((parametrosBusqueda.getSecuencia()!=null)? "; "+ traduceEtiqueta("secuenciacion",parametrosBusqueda.getIdiomaNavegacion()) + ": " + secuenciacion (parametrosBusqueda.getSecuencia(), parametrosBusqueda.getIdiomaNavegacion()):"")
			+ ((parametrosBusqueda.getValoracion()!=null && !parametrosBusqueda.getValoracion().equals(""))? "; "+ traduceEtiqueta("valoracion",parametrosBusqueda.getIdiomaNavegacion()) + ": " + parametrosBusqueda.getValoracion():"") 
			+ ((parametrosBusqueda.getDestinatarios()!=null && !parametrosBusqueda.getDestinatarios().equals(""))? "; "+ traduceEtiqueta("destinatarios",parametrosBusqueda.getIdiomaNavegacion()) + ": " + traducirVocabulario (parametrosBusqueda.getDestinatarios(), BuscarController.getPropertyValue("destinatarios"), parametrosBusqueda.getIdiomaNavegacion()): "") 
			+ ((parametrosBusqueda.getIdTesauro()!=null && !parametrosBusqueda.getIdTesauro().equals(""))? "; "+ traduceEtiqueta("tesauro",parametrosBusqueda.getIdiomaNavegacion()) + ": " + this.getSrvTesaurosServices().obtenerTextosDeIds(new String[]{parametrosBusqueda.getIdTesauro()}, parametrosBusqueda.getIdiomaNavegacion(), this.getPropertyValue("nombreFichTesauros"))[0].getValorTax():"") 
			+ ((parametrosBusqueda.getComunidadesSeleccionadas()!=null && parametrosBusqueda.getComunidadesSeleccionadas().length>0)? "; "+ traduceEtiqueta("comunidadesSeleccionadas",parametrosBusqueda.getIdiomaNavegacion()) + ": " + obtenerComunidades(parametrosBusqueda.getComunidadesSeleccionadas(),parametrosBusqueda.getIdiomaNavegacion()):""); 
		} catch (Exception e){ 
			logger.error("Error al recuperar la descripción del fichero",e); 
		} 
		if(logger.isDebugEnabled())logger.debug("Descripcion del RSS= <"+descripcion+">");
		return descripcion; 
	} 
	 
	 
	private String secuenciacion (Boolean secuencia, String idioma){ 
		if (secuencia) 
			return TraduccionesBuscador.getInstance().traduceEtiqueta("configurar.avanzado.c_s_secuencia.texto.conSecuencia", idioma);
		return TraduccionesBuscador.getInstance().traduceEtiqueta("configurar.avanzado.c_s_secuencia.texto.sinSecuencia", idioma); 
	} 
	 
	private String traducirVocabulario (String termino, String idVocabulario, String idioma){ 
		try { 
		 
		TerminoYPadreVO[] terminoypadre = new TerminoYPadreVO[1]; 
		terminoypadre[0]= new TerminoYPadreVO(); 
		terminoypadre[0].setIdiomaTermino(BuscarController.getPropertyValue("idioma.terminos")); 
		terminoypadre[0].setNomTermino(termino); 
		terminoypadre[0].setIdVocabulario(idVocabulario); 
		terminoypadre[0].setIdTermino(""); 
		terminoypadre = this.getSrvVocabulariosControladosService().obtenerIdTermino(terminoypadre); 
		return this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(new String[]{terminoypadre[0].getIdTermino()}, idioma)[0].getNomTermino(); 
		} catch (Exception e){ 
			logger.error("error al recuperar la descripción del fichero - ",e); 
			return ""; 
		} 
	} 
	 
	private String formatoFecha (String fecha, String dia, String mes, String año){ 
		String resultado = ""; 
		if (fecha == null || fecha.length()<=0 || fecha.equals("***")) 
			return resultado; 
		if (fecha.charAt(0)=='*'){ 
			//no tiene año 
			fecha = fecha.substring(1, fecha.length()); 
			resultado = "*"; 
			año=""; 
		} else { 
			año = fecha.substring(0, 4); 
			resultado = año; 
			fecha = fecha.substring(4, fecha.length()); 
		} 
 
		if (fecha.charAt(0)=='*'){ 
			//no tiene mes 
			fecha = fecha.substring(1, fecha.length()); 
			resultado = "*/" + resultado; 
			mes = ""; 
		} else { 
			mes = fecha.substring(0, 2); 
			resultado = mes + "/" + resultado; 
			fecha = fecha.substring(2, fecha.length()); 
	} 
 
		if (fecha.charAt(0)=='*'){ 
			//no tiene dia 
			resultado = "*/" + resultado; 
			dia = ""; 
		} else { 
			dia = fecha.substring(0, 2); 
			resultado = dia + "/" + resultado; 
		} 
 
		return resultado; 
	} 
	 
	private String nivelAgregacion (String[] nivel, String idioma){ 
		if(logger.isDebugEnabled())logger.debug("nivel de Agregación: " + nivel); 
		String traducciones="";
		
		for(int i=0;i<nivel.length;i++)
		{if(!nivel[i].equalsIgnoreCase(""))
		{
			if(i+1==nivel.length)
				traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta(BuscarController.getPropertyValue("avanzado.agregacion." + nivel[i]), idioma);
			else
		traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta(BuscarController.getPropertyValue("avanzado.agregacion." + nivel[i]), idioma)+"-";
		}
		}
		
		return traducciones;
	} 
	 
	private String formato (String[] formato, String idioma){ 
		if(logger.isDebugEnabled())logger.debug("formato: " + formato); 
		String traducciones="";
		for(int i=0;i<formato.length;i++)
			
		{
			if (formato[i].equals("application/*")) traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta("listar.odecu.mostrar.resultados.consulta.vo.aplicacion", idioma)+"-"; 
			else if (formato[i].equals("image/*")) traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta("listar.odecu.mostrar.resultados.consulta.vo.imagen", idioma)+"-"; 
			else if (formato[i].equals("audio/*")) traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta("listar.odecu.mostrar.resultados.consulta.vo.audio", idioma)+"-"; 
			else if (formato[i].equals("text/*")) traducciones= traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta("listar.odecu.mostrar.resultados.consulta.vo.texto", idioma)+"-"; 
			else if (formato[i].equals("video/*")) traducciones=traducciones+ TraduccionesBuscador.getInstance().traduceEtiqueta("listar.odecu.mostrar.resultados.consulta.vo.video", idioma)+"-"; 
		}
	 
		return traducciones;
	} 
	 
 private String obtenerComunidades(String[] comunidadesSeleccionadas,String idiomaNavegacion) throws RemoteException, Exception{		 
		if(logger.isDebugEnabled())logger.debug("obtener Comunidades: " + array2String(comunidadesSeleccionadas)); 
		if (comunidadesSeleccionadas[0].equals("0")){ 
			return "busqueda federada"; 
		} 
 
//		String resultado = ""; 
//		for(int i=0; i < comunidadesSeleccionadas.length;i++){ 
//			resultado = resultado + this.getSrvNodoService().obtenerNodo(new Long(comunidadesSeleccionadas[i])).getNodo(); 
//			resultado = resultado + ", "; 
//		}

		String resultado = "";
		StringBuilder resultadoParcial = new StringBuilder(resultado);
		
		for(int i=0; i < comunidadesSeleccionadas.length;i++){ 
			resultadoParcial.append(this.getSrvNodoService().obtenerNodo(new Long(comunidadesSeleccionadas[i])).getNodo()); 
			resultadoParcial.append(", "); 
		}
		resultado=resultadoParcial.toString();
		
		if(!resultado.trim().equals("")) resultado = resultado.substring(0, resultado.length()-2)+ " " +traduceEtiqueta("textoConcatenador",idiomaNavegacion) + " ";
		resultado+= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON);
		return resultado;
	}

	 
// Este metodo recibe el identificador del nivel educativo que se ha elegido y 
// extrae toda la ruta hasta el padre y la convierte a string con el texto ordenado 
// desde el padre hasta el hijo traducido al idioma que se le diga. 
// private String convertirStringAreaCurricular(String areaCurricular, String idioma) throws Exception{ 
//		if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - entra en areaCurricular"); 
//	 List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(areaCurricular, this.getPropertyValue("nombreAreaCurricularTax"), idioma)); 
//	 Collections.reverse(rutaPadrevo); 
//	 TaxonVO[] taxVORutaPadre = (TaxonVO[])rutaPadrevo.toArray(); 
//	 String rutaPadre[] = new String[taxVORutaPadre.length]; 
//	 for (int i = 0; i < taxVORutaPadre.length; i++) rutaPadre[i]= taxVORutaPadre[i].getValorTax(); 
//	 return array2String(rutaPadre); 
// } 
 
 private String convertirStringRutaTax(String idUltTaxon,String idTaxonomia, String idioma) throws Exception{ 
 	List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(idUltTaxon,idTaxonomia,idioma)); 
		Collections.reverse(rutaPadrevo); 
		TaxonVO[] taxVORutaPadre = (TaxonVO[])rutaPadrevo.toArray(); 
		String rutaPadre[] = new String[taxVORutaPadre.length]; 
		for (int i = 0; i < taxVORutaPadre.length; i++) rutaPadre[i]= taxVORutaPadre[i].getValorTax(); 
		return array2String(rutaPadre); 
 } 
 
 private String array2String(String[] array) { 
//		String str = ""; 
//		if (array != null && array.length > 0) { 
//			for (int i = 0; i < array.length; i++) { 
//				str+=array[i]; 
//				if ((i + 1) < array.length) str+= NIV_EDU_SEPARATOR; 
//			} 
//		} 
//		return str;
		StringBuilder str = new StringBuilder(); 
		if (array != null && array.length > 0) { 
			for (int i = 0; i < array.length; i++) { 
				str.append(array[i]); 
				if ((i + 1) < array.length) str.append(NIV_EDU_SEPARATOR); 
			} 
		} 
		return str.toString();
	}
 
 
	private String tranformarEnlaceTaxSelec(String enlaceTaxSelec,String idioma) throws Exception{
		
		
		String[] rutasTax = enlaceTaxSelec.split(TAX_ASTERISCO);
		
		StringBuffer bufferResult= new StringBuffer("");
		for(int i = 0; i < rutasTax.length; i ++){
			String[] partesRuta = rutasTax[i].split(TAX_DOLAR);
			
			String vocabIdentifier = partesRuta[0];
			String ultTaxon = partesRuta[1];
//			obtenemos el vocabName de la taxonomia a partir del vocabIdentifier
			String vocabName = this.getSrvTaxonomiaService().obtenerVocabName(vocabIdentifier, idioma);

			//obtenemos la ruta a partir del ultimo taxon de dicha ruta
			bufferResult.append(traduceEtiqueta("taxonomia",idioma)).append(": ").append(vocabName).append(" ").append(traduceEtiqueta("ruta",idioma)).append(": ");
			String sRuta = this.convertirStringRutaTax(ultTaxon,vocabIdentifier, idioma);

			if(i < rutasTax.length-1) bufferResult.append(sRuta).append("; ");
			else bufferResult.append(sRuta);
		}
		return bufferResult.toString();
	}
 
 
	/** 
	 * Este metodo recibe un VO del Agregador para mapear a uno del Buscar 
	 * Devuelve el VO mapeado para poder llamar al servicio Buscar 
	 * 
	 * @param ParametrosBusquedaAgregadorVO parametrosBusqueda: Parametro con los datos para realizar la busqueda. 
	 * @return ParametrosBusquedaAvanzadaVO: Parametro mapeado con la misma informacion del que recibimos 
	 */ 
	private ParametrosBusquedaAvanzadaVO30 mapearParametrosBusqueda (ParametrosBusquedaAgregadorVO parametrosBusqueda,int feedNum,int maxFeedNum){ 
		ParametrosBusquedaAvanzadaVO30 paramBusq = new ParametrosBusquedaAvanzadaVO30(); 
//		paramBusq.setArbolCurricularVigente(parametrosBusqueda.getArbolCurricularVigente()); 
//		paramBusq.setAreaCurricular(parametrosBusqueda.getAreaCurricular()); 
		paramBusq.setIdentificador(parametrosBusqueda.getIdODE());
		paramBusq.setAutor(parametrosBusqueda.getAutor()); 
		paramBusq.setComunidadesSeleccionadas(parametrosBusqueda.getComunidadesSeleccionadas()); 
		paramBusq.setContexto(parametrosBusqueda.getContexto()); 
		paramBusq.setDescripcion(parametrosBusqueda.getDescripcion()); 
		paramBusq.setDestinatarios(parametrosBusqueda.getDestinatarios()); 
		paramBusq.setEdad(parametrosBusqueda.getEdad()); 
		paramBusq.setFechaPublicacion(parametrosBusqueda.getFechaPublicacion()); 
		paramBusq.setFormato(parametrosBusqueda.getFormato()); //Provisional 
		paramBusq.setIdBusqueda(parametrosBusqueda.getIdBusqueda()); 
		paramBusq.setIdiomaBusqueda(parametrosBusqueda.getIdiomaBusqueda()); 
		paramBusq.setIdiomaNavegacion(parametrosBusqueda.getIdiomaNavegacion()); 
		paramBusq.setKeyword(parametrosBusqueda.getKeyword()); 
		paramBusq.setNivelAgregacion(parametrosBusqueda.getNivelAgregacion());//Provisional 
		paramBusq.setNumeroResultados((maxFeedNum!=0)?maxFeedNum:parametrosBusqueda.getNumeroResultados()); 
		paramBusq.setOrigenPagina(parametrosBusqueda.getOrigenPagina()); 
		paramBusq.setPalabrasClave(parametrosBusqueda.getPalabrasClave()); 
		paramBusq.setProcesoCognitivo(parametrosBusqueda.getProcesoCognitivo()); 
		paramBusq.setRecurso(parametrosBusqueda.getRecurso()); 
		paramBusq.setResultadosPorPagina((feedNum!=0)?feedNum:parametrosBusqueda.getResultadosPorPagina()); 
		paramBusq.setSecuencia(parametrosBusqueda.getSecuencia()); 
		paramBusq.setTitulo(parametrosBusqueda.getTitulo()); 
		paramBusq.setValoracion(parametrosBusqueda.getValoracion().replaceAll("-", " ")); 
		paramBusq.setBusquedaSimpleAvanzada(parametrosBusqueda.getBusquedaSimpleAvanzada()); 
//		paramBusq.setIdTesauro(parametrosBusqueda.getIdTesauro()); 
		paramBusq.setComunidadPeticion(parametrosBusqueda.getComunidadPeticion()); 
		paramBusq.setTaxonomia(parametrosBusqueda.getTaxonomia());		 
		return paramBusq; 
	} 
	 
 
	private boolean esVisualizable (String[] ambito){ 

		boolean visualizable = false; 

		//		for (int j = 0; j < ambito.length && !visualizable; j++) { 
		//			if(ambito!=null && ambito[0]!=null && (ambito[0].equals(UNIVERSAL) || ambito[j].trim().equals(DependentServerProperties.getInstance().getProperty(IDENTIFICADOR_NODO).toString()))){ 
		//				visualizable=true;
		//			}
		//		}
		
		String serv_ID;
		try {
			serv_ID = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
			if (ambito!=null) {
				for (int j = 0; j < ambito.length && !visualizable; j++) {
					if (ambito[0] != null&& (ambito[0].equals(UNIVERSAL) || ambito[j].trim().equals(serv_ID))) {
						visualizable = true;
					}
				}
			}		
			} catch (Exception e) {
				logger.error("Error generico - ",e);
			}
			return visualizable; 
	}	 
	 
	/** 
	 * Crea los xmls correspondientes a los informes de auditoría formato RSS y ATOM 
	 * @param 	ValoresBusquedaVO[] valoresBusqueda: array de odes que formarán el feed 
	 * 			String link: link del feed 
	 * 			String descripcion: descripción del feed 
	 * 			String idioma: idioma de búsqueda 
	 * 			String idiomaNavegacion: idioma de navegación 
	 * 			String tipoRSS: tipo de feed RSS o ATOM 
 	 * 			long feedNum: número de odes por feed 
	 * @return void 
	 * @throws Exception 
	 */ 
 private void crearXMLBusqueda (ValoresBusquedaVO[] valoresBusqueda, String link, String descripcion, String idioma,String idiomaNavegacion,String tipoRSS,long feedNum, String tipoBuscador) throws Exception { 
 	 
 try{ 
 	feed = new SyndFeedImpl();
 	feed.setEncoding("ISO-8859-1"); 
			if(logger.isDebugEnabled())logger.debug("Comienza a crear el feed de la busqueda realizada --> " + link);
	 feed.setLink("http://" + feedHost + link); 
	 feed.setAuthor("Agrega");
	 feed.setPublishedDate(new Date());
	 List entries = new ArrayList(); 
 
	 SyndEntry entry; 
	 SyndImageImpl image = new SyndImageImpl(); 
 
	 SyndContent imagenItem; 
	 int total=valoresBusqueda.length;
	 
	 if(total>feedNum)
	 	total=(int)feedNum;
 
//	 DocVO docVO = null; 
	 if (valoresBusqueda != null && valoresBusqueda.length>0){ 
		 
		String enlaceODE="ODE";
		if(tipoBuscador.contentEquals(CONTEXTO_BUSCADOR_PUBLICO))
			enlaceODE="ODE2"; //ajustamos a ficha de ODE publica
		 
		 for (int i=0; i<total;i++){ 
		 	entry = new SyndEntryImpl(); 
		 	try{ 
		 		if(logger.isDebugEnabled())logger.debug("Se tienen todos los datos necesarios del ODE"); 
		 		entry.setTitle(valoresBusqueda[i].getTitulo()); 
		 		if (esVisualizable(valoresBusqueda[i].getAmbito())){ 
		 			if (valoresBusqueda[i].getNodo()!=null){ 
		 				entry.setLink(http + valoresBusqueda[i].getNodo() + "/" + enlaceODE + "/" + idioma + "/" +valoresBusqueda[i].getId()); 
		 			}else{ 
		 				entry.setLink(http + feedHost + "/" + enlaceODE + "/" + idioma + "/" +valoresBusqueda[i].getId()); 
		 			} 
		 		} 
		 		entry.setUpdatedDate(new Date()); 
		 		try{ 
		 			SimpleDateFormat formatoFechayHora = new SimpleDateFormat("yyyyMMdd" + " " + "HHmmss"); 
		 			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd"); 
		 			if (valoresBusqueda[i].getHoraPublicacion()!=null && !valoresBusqueda[i].getHoraPublicacion().equals("")){ 
		 				Date fechayHoraPublicacion = formatoFechayHora.parse(valoresBusqueda[i].getFechaPublicacion() + " " + valoresBusqueda[i].getHoraPublicacion()); 
		 				entry.setPublishedDate(fechayHoraPublicacion); 
		 				if(logger.isDebugEnabled())logger.debug("Fecha y hora de publicacion del ODE cargada correctamente"); 
		 			}else{ 
		 				Date fechaPublicacion = formatoFecha.parse(valoresBusqueda[i].getFechaPublicacion()); 
		 				entry.setPublishedDate(fechaPublicacion); 
		 				if(logger.isDebugEnabled())logger.debug("Fecha de publicación del ODE cargada correctamente"); 
		 			} 
			 		 
			 		 
		 		}catch (Exception e){ 
		 			logger.error("Error al cargar la fecha o la hora del ODE", e); 
		 		} 
		 		 
//		 		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd" + " " + "HHmmss");		 				 		 
//		 		Date fechaPublicacion = formatoFecha.parse(valoresBusqueda[i].getFechaPublicacion() + " " + valoresBusqueda[i].getHoraPublicacion()); 
//		 		entry.setPublishedDate(fechaPublicacion); 
		 		 
		 		 
		 		if (valoresBusqueda[i].getNodo()!=null){ 
		 			image.setUrl(http + valoresBusqueda[i].getNodo() + "/static/img/logo_agrega_red.gif"); 
			 		image.setLink(http + valoresBusqueda[i].getNodo()); 
		 		}else{ 
		 			image.setUrl(http + feedHost + "/static/img/logo_agrega_red.gif"); 
		 			image.setLink(http + feedHost); 
		 		} 
		 		image.setTitle(""); 
			 
		 		imagenItem = new SyndContentImpl(); 
		 		imagenItem.setType("html"); 
		 		imagenItem.setMode("escaped"); 
		 		if (valoresBusqueda[i].getUrlImagen()!=null && !valoresBusqueda[i].getUrlImagen().equals("")){ 
		 			if (valoresBusqueda[i].getNodo()!=null){ 
		 				imagenItem.setValue("<p img class='centrado'><a href='" + http + valoresBusqueda[i].getNodo() + ampliarImagen(valoresBusqueda[i].getUrlImagen()) + "'target='_blank'><img src='" + http + valoresBusqueda[i].getNodo() + valoresBusqueda[i].getUrlImagen() + "'/></a></p>" + valoresBusqueda[i].getDescripcion()); 
		 			}else{ 
		 				imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost + ampliarImagen(valoresBusqueda[i].getUrlImagen()) + "'target='_blank'><img src='" + http + feedHost + valoresBusqueda[i].getUrlImagen() + "'/></a></p>" + valoresBusqueda[i].getDescripcion()); 
		 			} 
		 		}else{ 
			 	String imagenPorDefecto=feedHost+"/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_100x100);
			 	imagenItem.setValue("<p img class='centrado'><a href='" + http + imagenPorDefecto + "'target='_blank'><img src='" + http + imagenPorDefecto + "'/></a></p>" + valoresBusqueda[i].getDescripcion()); 
			 	if(logger.isDebugEnabled())logger.debug("no tiene imagen"); 
		 		} 

		 		
		 		List contenido = new ArrayList(); 
		 		contenido.add(imagenItem); 
		 		entry.setContents(contenido); 
			 			 			 
		 	} catch (Exception e){ 
		 		logger.warn("No se han podido recuperar los datos del ODE"); 
			 entry.setTitle("Item erróneo"); 
			 entry.setLink(""); 
			 entry.setUpdatedDate(new Date()); 
			 entry.setPublishedDate(new Date()); 
 
			 image.setUrl(http + feedHost +"/static/img/logo_agrega_red.gif"); 
			 image.setLink(http + feedHost); 
			 image.setTitle(""); 
			 
			 imagenItem = new SyndContentImpl(); 
			 imagenItem.setType("html"); 
			 imagenItem.setMode("escaped"); 
			 imagenItem.setValue("<p img class='centrado'><a href='" + http + feedHost +"/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png' target='_blank'><img src='" + http + feedHost +"/galeriaimg/es_20080729_1_9173013/es_20080729_1_9173013.png'/></a></p>" + "No se han podido recuperar los datos necesarios para la formación de este item...");
			 		 
			 List contenido = new ArrayList(); 
			 contenido.add(imagenItem); 
			 entry.setContents(contenido); 
			 
		 	} 
		 	 	 
		 entries.add(entry); 
		 } 
		 
		 //Escribimos en el fichero 
		 
		 if(logger.isDebugEnabled())logger.debug("comenzamos a generar los ficheros..."); 
		 feed.setEntries(entries); 
		 feed.setImage(image); 
		 feed.setDescription(descripcion); 
		 if(tipoRSS.equals(TIPO_RSS)){
			 //RSS 
			 	feed.setTitle(traduceEtiqueta("resultadosBusqueda.title_rss",idiomaNavegacion));		 	 
		 feed.setFeedType(getPropertyValue("feedTypeRSS")); 
//			 if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - crearXMLBusqueda: generando RSS: "); 
		 }else{
		 	feed.setTitle(traduceEtiqueta("resultadosBusqueda.title_atom",idiomaNavegacion)); 
		 	feed.setFeedType(getPropertyValue("feedTypeAtom"));
//		 	if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - crearXMLBusqueda: generando ATOM: ");
	 } 
 
	 } else { 
				logger.info("No hay datos para generar el feed."); 
	 } 
 }catch (Exception e){ 
 	logger.error("Se ha producido un error al crear el feed de búsqueda - ", e); 
 } 
 } 
 
 /** 
	 * Este metodo recibe un VO del Agregador para mapear a uno del Buscar, 
	 * genera el titulo del fichero a eliminar y lo elimina 
	 * 
	 * @param param ParametrosBusquedaAgregadorVO parametrosBusqueda: Parametro con los datos para realizar la busqueda. 
	 */ 
	protected void handleEliminarFichero(ParametrosBusquedaAgregadorVO param) throws Exception { 
		 
		ParametrosBusquedaAvanzadaVO30 paramBusq = mapearParametrosBusqueda(param,0,0); 
		String tituloFichero =""; 
		String tituloFicheroCodificado =""; 
 
//		pongo nombre al fichero que voy a eliminar 
		try{ 
			tituloFichero = this.getSrvBuscarService().generadorIdentificadorAvanzado(paramBusq); 
			tituloFicheroCodificado = codificarNombreFichero(tituloFichero); 
			if(logger.isDebugEnabled())logger.debug("El identificador para el fichero xml a generar es " + tituloFicheroCodificado); 
		}catch (Exception e){ 
			logger.error("Error al generar el nombre de fichero con identificador unico",e); 
		} 
		String feedPath = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS_PATH); 
		String rutaFichero=feedPath + (feedPath.endsWith("/")?"":"/")+"temp/"+ tituloFicheroCodificado + "_rss.xml"; 
		File fichero = new File(rutaFichero); 
		 
//		elimino el fichero xml despues de guardar sus datos en el DataHandler		 
		try{ 
			if (fichero.exists()){ 
				fichero.delete(); 
				if(logger.isDebugEnabled())logger.debug("Se ha eliminado correctamente el fichero xml"); 
			} 
			 
		}catch (Exception e){ 
			logger.error("Se ha producido un error al eliminar el fichero xml"); 
		}
	} 

	private ResourceBundle getBundleFile(String idioma)
	{
		ResourceBundle fichero = null;
		if ((fichero = (ResourceBundle)mapaIdiomas.get(idioma)) == null) // si no existe, lo intentamos cargar
			fichero = addBundleFile(idioma);
		return fichero;
	}
	
	/*
	 * Añade un bundle para ese idioma a la coleccion de bundles y lo devuelve
	 * */
	private ResourceBundle addBundleFile(String idioma)
	{
		// Chequeamos que exista el fichero de bundle para el idioma dado.
		// Si no existe el fichero de properties para el idioma, no lo añadimos
		ResourceBundle fichero = null;
		try {
			fichero = ResourceBundle.getBundle("application-resources", new Locale(idioma));
		} catch (RuntimeException e) {
			// No existe un resource bundle para un idioma dado
			logger.warn("Excepcion intentando buscar el fichero de bundle para el idioma ["+idioma+"] - ["+e.getCause()+"]");
			return null;
		}
		if (fichero == null)
			return fichero;
		
		mapaIdiomas.put(idioma, fichero);
		return fichero;
	}
	
	/**
	 * Este metodo traduce la etiqueta que le pasan al idioma que se le suministra
	 * @param etiqueta tag que se quiere traducir
	 * @param idioma codigo de idioma ISO-639 en el que se quiere obtener la traduccion
	 * @return vuelta
	 */
	public String traduceEtiqueta(String etiqueta, String idioma)
	{
		
		ResourceBundle fichero = getBundleFile(idioma);
		if (fichero != null)
			return fichero.getString(etiqueta);
		else
			return "";
	}

	/**
	 * Este metodo genera el feed indicado para la parte privada de agrega
	 * @param id identificador del feed
	 * @param feedNum Número de odes en el feed
	 * @param idiomaNavegacion Idioma en el que se devuelve el feed
	 * @return String Devuelve el xml del feed solicitado
	 * @throws Exception
	 */
	protected String handleObtenerXMLFeed(String id,long feedNum,
			String idiomaNavegacion) throws Exception {
		return obtenerXMLFeed(id,feedNum,idiomaNavegacion,FEED_PRIVADO);
	}
	
	/**
	 * Este metodo genera el feed indicado para la parte publica de agrega
	 * @param id identificador del feed
	 * @param feedNum Número de odes en el feed
	 * @param idiomaNavegacion Idioma en el que se devuelve el feed
	 * @return String Devuelve el xml del feed solicitado
	 * @throws Exception
	 */
	@Override
	protected String handleObtenerXMLFeedPublico(String id, long feedNum,
			String idiomaNavegacion) throws Exception {
		return obtenerXMLFeed(id,feedNum,idiomaNavegacion,FEED_PUBLICO);
	}
	

	private String obtenerXMLFeed(String id,long feedNum,String idiomaNavegacion,String tipoUrl) throws Exception {
		//		long inicio = System.currentTimeMillis();
		if(logger.isDebugEnabled())
			logger.debug("Obteniendo XML para idFeed["+id+"], feedNum ["+feedNum+"] e idiomaNavegacion ["+idiomaNavegacion +"]");
		idiomaNavegacion = (idiomaNavegacion == null || "".equals(idiomaNavegacion))?I18n.getInstance().obtenerIdiomaDefectoPlataforma():idiomaNavegacion;
		feedNum = (feedNum>0)?feedNum:new Long(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FEED_DEFAULT)).longValue();	

		crearXML(id,feedNum,idiomaNavegacion,tipoUrl);

		SyndFeedOutput output = new SyndFeedOutput(); 
		String xmlFeed = output.outputString(feed);

		return xmlFeed; 
	}
	
	
	/**
	 * Este metodo genera el feed indicado
	 * @param idiomaNavegacion Idioma en el que se devuelve los titulos de los feed
	 * @return FeedVO[] Devuelve los feed utilizados en la galeria de odes 
	 * @throws Exception
	 */
	protected FeedVO[] handleDevuelveFeedsGaleriaPortada(String idiomaNavegacion) throws Exception {
//		if(logger.isDebugEnabled())logger.debug("SrvAgregadorRssServiceImpl - devuelveFeeds: recuperando los datos de los feeds... ");
		String feeds = getPropertyValue("feeds_ids_rss_galeria_portada");
		String[] aFeeds=feeds.split("-");
		FeedVO[] feedsVO = new FeedVO[aFeeds.length];
		for(int i=0;i<aFeeds.length;i++){
			FeedVO feedVO = new FeedVO(); 
	 	feedVO.setTitulo(traduceEtiqueta(getPropertyValue(aFeeds[i])+".title", idiomaNavegacion)); 
	 	feedVO.setId(aFeeds[i]); 
	 	feedsVO[i]=feedVO;
		}
		if(logger.isDebugEnabled())logger.debug("Se han recuperado los datos de los feeds correctamente.");
 
		return feedsVO; 	 
	}
	/**
	 * Obtiene un array de bytes del XML feed
	 * @return byte[] Array de byte del fichero
	 * @throws Exception
	 */
	protected byte[] handleObtenerXMLFeedArrayByte() throws Exception {
		File file = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.RSS_PATH)+"ultimosOdes_Federado_atom.xml");
		byte[] fileBArray = new byte[(int)file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(fileBArray);
		fis.close();
		return fileBArray;
	}
	
	
	public class DescargaInfo {
		private String titulo;
		private String identificador;
		private String peso;
		private String ruta;
		private String descripcion;
		
		/**
		 * @return the titulo
		 */
		public String getTitulo() {
			return titulo;
		}
		/**
		 * @param titulo the titulo to set
		 */
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		/**
		 * @return the identificador
		 */
		public String getIdentificador() {
			return identificador;
		}
		/**
		 * @param identificador the identificador to set
		 */
		public void setIdentificador(String identificador) {
			this.identificador = identificador;
		}

		/**
		 * @return the ruta
		 */
		public String getRuta() {
			return ruta;
		}
		/**
		 * @param ruta the ruta to set
		 */
		public void setRuta(String ruta) {
			this.ruta = ruta;
		}
		/**
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}
		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
	}


	/**
	 * Este metodo genera el xml utilizado para cargar los widgets de usuario y grupo
	 * @param parametros ParamWidgetVO 
	 * @return String Devuelve un xml con odes por usuario o grupo 
	 * @throws Exception
	 */
	protected String handleObtenerFeedWidgets(ParamWidgetVO parametros) throws Exception {
		String xmlFeed ="";
		String idODE="";
		String idiomaODE=""; 
		String titulo=""; 
		String autor="";
		List entries = new ArrayList(); 
		SyndEntry entry; 
		SyndImageImpl image = new SyndImageImpl(); 
		SyndContent imagenItem; 
		try{
			Object[] odes = null;
			Object[] favoritos = null;
			if(parametros.getUsuario()!=null && !"".equals(parametros.getUsuario())){
				logger.info("Vamos crear xml para widget del usuario: <" +parametros.getUsuario()+">");
				UsuarioPublicoVO usuarioPublico = this.getSrvPerfilPublico().obtenerUsuarioPublico(parametros.getUsuario());
				if(usuarioPublico.getMostrarObjetos()){
					odes = this.getSrvPublicacionService().obtenODEsPublicadoUsuario(parametros.getUsuario());
					if(logger.isDebugEnabled())logger.debug("El usuario quiere mostrar sus odes publicados, tiene "+odes.length+" odes publicados");
				}
				if(usuarioPublico.getMostrarFavoritos()){
					favoritos = this.getSrvPerfilPublico().listarFavoritosUsuario(parametros.getUsuario());
					if(logger.isDebugEnabled())logger.debug("El usuario quiere mostrar sus odes favoritos, tiene "+favoritos.length+" odes favoritos");
				}
				autor=parametros.getUsuario();
			}
			else if(parametros.getGrupo()!=null && !"".equals(parametros.getGrupo())){
				logger.info("Vamos a crear xml para widget del grupo: <" +parametros.getGrupo()+">");
				odes = this.getSrvPerfilPublico().listarOdesDeGrupo(parametros.getGrupo());
				autor=parametros.getGrupo();
			}
	
			String numOdes = parametros.getNumOdes();
			int numOdesInt = Integer.parseInt(numOdes);
			
			logger.info("Número de ODEs que obtenidos: " + odes!=null?odes.length:"0" + " Se van mostrar: " + numOdesInt);
			
			if (odes != null){ 
				for (int i=0; i<odes.length && i<numOdesInt;i++){
					if (odes instanceof OdePublicadoVO[]){//widgets usuario 
						idODE= ((OdePublicadoVO)odes[i]).getIdODE();
						idiomaODE =((OdePublicadoVO)odes[i]).getIdioma();
						titulo = ((OdePublicadoVO)odes[i]).getTitulo();
					} else{//widgets grupo
						idODE= ((OdeGrupoVO)odes[i]).getId_mec();
						idiomaODE =((OdeGrupoVO)odes[i]).getIdioma();
						titulo = ((OdeGrupoVO)odes[i]).getTitulo();
					} 
					entry = new SyndEntryImpl(); 
		 if(logger.isDebugEnabled())logger.debug("Tenemos todos los datos necesarios del ODE con identificador: <" + idODE + ">, idioma: <" + idiomaODE + "> titulo: <" +titulo+">"); 
		 entry.setTitle(titulo); 
		 entry.setLink(ODEPublico.urlFichaODEPublicado(idODE, idiomaODE)+"/embed"); 
 
		 imagenItem = new SyndContentImpl(); 
		 imagenItem.setType("html"); 
		 imagenItem.setMode("escaped"); 
//		 String imagenODE = ImagenODE.urlImagenODE(idODE);
//		 String imagenODEGrande = ImagenODE.urlImagenGrandeODE(idODE);
		 String imagenODEMediana = ImagenODE.urlImagenMedianaODE(idODE);
		 imagenItem.setValue(imagenODEMediana); 
		 
		 List contenido = new ArrayList(); 
		 contenido.add(imagenItem); 
		 entry.setContents(contenido);
		 entries.add(entry); 
 			} 
			} 
			//Concateno los favoritos
			if (favoritos != null){ 
				for (int j=0; j<favoritos.length && j<numOdesInt;j++){
					idODE= ((FavoritoVO)favoritos[j]).getId_mec();
					idiomaODE =((FavoritoVO)favoritos[j]).getIdioma();
					titulo = ((FavoritoVO)favoritos[j]).getTitulo()+" (Destacado)";
					entry = new SyndEntryImpl(); 
		 if(logger.isDebugEnabled())logger.debug("favoritos: Tenemos todos los datos necesarios del ODE con identificador: <" + idODE + ">, idioma: <" + idiomaODE + "> titulo: <" +titulo+">"); 
		 entry.setTitle(titulo); 
		 entry.setLink(ODEPublico.urlFichaODEPublicado(idODE, idiomaODE)); 
 
		 imagenItem = new SyndContentImpl(); 
		 imagenItem.setType("html"); 
		 imagenItem.setMode("escaped"); 
//		 String imagenODE = ImagenODE.urlImagenODE(idODE);
//		 String imagenODEGrande = ImagenODE.urlImagenGrandeODE(idODE);
		 String imagenODEMediana = ImagenODE.urlImagenMedianaODE(idODE);
		 imagenItem.setValue(imagenODEMediana); 
		 
		 List contenido = new ArrayList(); 
		 contenido.add(imagenItem); 
		 entry.setContents(contenido);
		 entries.add(entry); 
 			} 
			} 
		}catch(Exception e){
			logger.error("Error obteniendo xml para widgets ",e);
			
		}finally{//Devolvemos el rss vacio(sin "entries o item") cuando no hay odes o cuando hay alguna excepción
			if(logger.isDebugEnabled())logger.debug("Se comienza a crear el feed: <" + titulo+">"); 
			feed = new SyndFeedImpl();
			feed.setEncoding(ENCODING_XML_RSS);
			feed.setLink(http + feedHost);//obligatorio
			feed.setAuthor(autor);
			feed.setPublishedDate(new Date());
			feed.setEntries(entries); 
 feed.setDescription("Widget"); //obligatorio
		 feed.setTitle("Widget"); 
			image.setUrl(http + feedHost + "/static/img/logo_agrega_red.gif"); 
			image.setLink(http + feedHost); 
			image.setTitle(""); 
			feed.setImage(image); 
			feed.setFeedType(getPropertyValue("feedTypeRSS")); 
			SyndFeedOutput output = new SyndFeedOutput(); 
			xmlFeed = output.outputString(feed);
		}
		return xmlFeed; 
	}

	/**
	 * Método utilizado para la compatibilidad de versión 1 a versión 3. 
	 * Pasamos el nombre que dabamos al xml en la versión 1, 
	 * (por ejemplo para notcias_rss.xml pasariamos noticias, 
	 * para masDescargados_semana_rss.xml pasariamos masDescargados)
	 * @param name Nombre que le dabamos en la version 1 al fichero xml sin periodicidad y sin tipo de feed (noticias,masDescargados)
	 * @return String Id del feed (noticias = 0, masDescargados = 3) o vacío si el nombre no es correcto.
	 * @throws Exception
	 */ 
	protected String handleObtenerIdCompatibilidad(String name) throws Exception {
		String id = getPropertyValue(name+".id");
		return id!=null?id:"";
	}

}