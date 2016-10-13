/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.externo.google;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.visualizador.presentacion.descargas.DescargasControllerImpl.DescargaInfo;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;
import es.pode.visualizador.presentacion.portada.PortadaControllerImpl;

/**
 * @see es.pode.buscador.presentacion.externo.google.BusquedaGoogleController
 */
public class BusquedaGoogleControllerImpl extends BusquedaGoogleController
{
	private SrvPropiedadService prop = null;
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PortadaControllerImpl.class);

	@Override
	public void prepararConsulta(ActionMapping mapping,
			PrepararConsultaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String nodoBusquedaGlobal = "";
		String nodoBusquedaGlobalYahoo = "";
	   	String nodoBusquedaCCAA = "";
    	
    	// Creacion de la url de busqueda de todas las comunidades (concatenacion con 'OR')
	   	String googleTodosUrlAuxiliar = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.GOOGLE_NODOS_URL);
	   	Scanner scanner = new Scanner(googleTodosUrlAuxiliar);
	   	scanner.useDelimiter("OR");
	   	while (scanner.hasNext()){
	   		String urlProperty = scanner.next();
	   		try{
	   			urlProperty = urlProperty.substring(urlProperty.indexOf('{') + 1, urlProperty.indexOf('}'));
	   			String url = AgregaPropertiesImpl.getInstance().getProperty(urlProperty);
	   			if (url.equals(""))
	   				continue;
				if (nodoBusquedaGlobal.equals("")) {
					nodoBusquedaGlobal = " site:"+url;
					nodoBusquedaGlobalYahoo = "&vs="+url;
				} else {
					nodoBusquedaGlobal = nodoBusquedaGlobal + " OR site:" + url;
					nodoBusquedaGlobalYahoo = nodoBusquedaGlobalYahoo + "%2C+" + url;
				}
	   		}catch (Exception e){
	   			logger.error("BusquedaGoogleControllerImpl.prepararConsulta: Error parseando la url de busqueda de google para todos las CCAA",e);
	   			throw new Exception();
	   		}
	   	}
		nodoBusquedaCCAA = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		
   		// por ultimo agregamos a la lista el nodo local
		nodoBusquedaGlobal = nodoBusquedaGlobal + " OR site:" + nodoBusquedaCCAA;
		nodoBusquedaGlobalYahoo = nodoBusquedaGlobalYahoo + "%2C+" + nodoBusquedaCCAA;
		
		// Formacion de la url de busqueda para el nodo actual
	    form.setCodigoNodo(nodoBusquedaCCAA);
        form.setCodigoTodos(nodoBusquedaGlobal);
        form.setCodigoTodosYahoo(nodoBusquedaGlobalYahoo);
		
		logger.debug("nodoBusquedaCCAA: " + nodoBusquedaCCAA);
		logger.debug("nodoBusquedaGlobal: " + nodoBusquedaGlobal);
        
        
        //Obtenemos la lista de noticias
		try{		
			NoticiaTraducidaVO[]	arrayNoticias = this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
			
			if (arrayNoticias!=null && arrayNoticias.length>0) {
				
				int numNoticiasMostradas = Integer.parseInt(ObtieneSrvPropiedad().get(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
				ArrayList<NoticiaTraducidaVO> lista_noticias = new ArrayList<NoticiaTraducidaVO>();
//				NoticiaTraducidaVO[] array_noticias = new NoticiaTraducidaVO[numNoticiasMostradas];
				
				for(int h=0; h<numNoticiasMostradas && h<arrayNoticias.length; h++)
				{
					//sustituimos los retorno de carro por <br> en el resumen
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n\r", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r", "<br/>"));
					
					//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("@","&#64"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\\"","&#34"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll(":","&#58"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("%","&#37"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\+","&#43"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("-","&#45"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("'","&#39"));
					
					lista_noticias.add(arrayNoticias[h]);
				}
				//Cojo solo las que debo
				//int iTotal = Integer.parseInt(this.getPropertyValue("portada.noticias.total"));
				//form.setNumNoticias(iTotal);
				
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(lista_noticias.toArray(new NoticiaTraducidaVO[lista_noticias.size()]));
				form.setNoticiasAsArray(noticiaCodex);
				
				//logger.debug("Noticias de portada obtenidas correctamente("+iTotal+").");
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran "+numNoticiasMostradas+" noticias.");
			} else {	
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(arrayNoticias);
				form.setNoticiasAsArray(noticiaCodex);
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran 0 noticias.");
			}
			// Ficheros OPML
	        //String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			//form.setIdiomaNavegacion(idioma);
			
		} catch (Exception e) {
			logger.error("Excepcion inesperada obteniendo noticias: ", e);
			throw e;
		}
		

		//Obtenemos la lista de descargas
		try {
    		Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			String idioma = locale.getLanguage();
			logger.debug("Recuperado idioma :"+idioma);
			DescargaVO descargas[] = getSrvDescargas().obtenerDescargasActivas();
			logger.debug("Recuperadas "+descargas.length!=null?descargas.length:0+" descargas");
			ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
			
			if (descargas!=null && descargas.length>0) {	
				DescDescargaVO[] descs = getSrvDescargas().obtenerDescDescargasIdioma(descargas, idioma);
				logger.debug("Recuperadas " + descs.length + " descripciones de descargas");
				int numDescargasMostradas = Integer.parseInt(ObtieneSrvPropiedad().get(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
				
				for (int i = 0; i<numDescargasMostradas && i<descargas.length; i++) {
					DescargaInfo info = new DescargaInfo();
					info.setTitulo(descs[i]!=null&&descs[i].getTitulo()!=null?descs[i].getTitulo():"");
					info.setDescripcion(descs[i]!=null&&descs[i].getDescripcion()!=null?descs[i].getDescripcion():"");
					info.setIdentificador(descargas[i]!=null&&descargas[i].getIdentificador()!=null?descargas[i].getIdentificador().toString():"");
					info.setPeso(descargas[i]!=null&&descargas[i].getPeso()!=null?descargas[i].getPeso():0L,locale);
					info.setRuta(descargas[i]!=null&&descargas[i].getPath()!=null?request.getServerName()+"/"+descargas[i].getPath():"");
					listaDescargas.add(info);
				}
				form.setDescargas(listaDescargas);
				logger.debug("Lista de descargas de portada obtenidas correctamente. Se mostraran "+numDescargasMostradas+" descargas.");
			}
		} catch (Exception e) {
			logger.debug("Error al recuperar descargas.",e);
			throw new ValidatorException("{listaDescargas.error}");
		}
		
	}
	

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}

 }