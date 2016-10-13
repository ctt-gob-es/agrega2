/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.areaCurricular;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.visualizador.presentacion.descargas.DescargaInfo;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController
 */
public class AreaCurricularControllerImpl extends AreaCurricularController{

	private static final long serialVersionUID = -8190956967834999990L;
	
	private java.util.Properties pSpringProperties = null;

	private static Logger logger = Logger.getLogger(AreaCurricularControllerImpl.class);
	
	public final static String MENSAJE_GENERICO_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";
	public final static String MENSAJE_NO_SELEC_TAXON="{taxonomias.noSelec.taxon}";
	
    /**
     * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController#obtenerAreasCurriculares(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.areaCurricular.ObtenerAreasCurricularesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTaxonomias(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.areaCurricular.ObtenerTaxonomiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	try {
    		if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias");
    		TaxonVO[] taxVO = null;
    		Object[] taxVORutaPadre = null;
    		String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    		
    		String taxonomia = form.getTaxonomias();//taxonomia seleccionada
    		
//  		Detectamos si se ha pulsado sobre un identificador de la lista de taxones desplegada
    		if (form.getId() == null || form.getId().equals("")){
    			if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias: Cargando taxones de vacio en busqueda avanzada.");
    			taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(taxonomia, idioma);
    			taxVORutaPadre = new TaxonVO[0];
    			if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias: Recuperados["+taxVO.length+"] taxones de vacio en busqueda avanzada.");
    		}else{
    			if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias: Cargando taxones del identificador["+form.getId()+"] en busqueda avanzada.");
    			taxVO = this.getSrvTaxonomiaService().obtenerNodos(form.getId(),taxonomia, idioma);
    			List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(form.getId(), taxonomia, idioma));
    			if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias: Recuperados["+taxVO.length+"] taxones de del identificador["+form.getId()+"] en busqueda avanzada.");
    			Collections.reverse(rutaPadrevo);
    			taxVORutaPadre = rutaPadrevo.toArray();
    		}
    		if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - obtenerTaxonomias: Cargados["+taxVO.length+"] taxones del identificador ["+(form.getId() == null?"null":form.getId())+"] en busqueda avanzada.");
			form.setNodosAsArray(taxVO);
			form.setRutaArbolAsArray(taxVORutaPadre);
			//se obtiene el nombre de la taxonomia seleccionada
			if(taxVO!=null && taxVO.length>0){
				String taxNombre = taxVO[0].getVocabName();
				form.setTaxNombre(taxNombre);
			}
			
			
    	} catch (Exception e) {
			logger.error("AreaCurricularControllerImpl - obtenerTaxonomias ERROR: Error cargando taxonomias de ["+this.getPropertyValue("nombreAreaCurricularTax")+"] en busqueda avanzada.", e);
			saveErrorMessage(request, MENSAJE_GENERICO_BUSQUEDA);
		}
    	
    	//Obtenemos la lista de noticias
		try{		
			NoticiaTraducidaVO[]	arrayNoticias = this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
			
			if (arrayNoticias!=null && arrayNoticias.length>0) {
				
				int numNoticiasMostradas = Integer.parseInt(this.getSrvPropiedadService().get(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
				NoticiaTraducidaVO[] array_noticias = new NoticiaTraducidaVO[numNoticiasMostradas];
				
				for(int h=0; h<numNoticiasMostradas && h < arrayNoticias.length; h++)
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
					
					array_noticias[h]=arrayNoticias[h];
				}
				//Cojo solo las que debo
				//int iTotal = Integer.parseInt(this.getPropertyValue("portada.noticias.total"));
				//form.setNumNoticias(iTotal);
				
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(array_noticias);
				form.setNoticiasAsArray(noticiaCodex);
				
				//logger.debug("Noticias de portada obtenidas correctamente("+iTotal+").");
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran "+numNoticiasMostradas+" noticias.");
			} else {	
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(arrayNoticias);
				form.setNoticiasAsArray(noticiaCodex);
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran 0 noticias.");
			}
			// Ficheros OPML
//	        String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
//			form.setIdiomaNavegacion(idioma);
			
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
				int numDescargasMostradas = Integer.parseInt(this.getSrvPropiedadService().get(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
				
				for (int i = 0; i<numDescargasMostradas && i<descargas.length && i<descs.length; i++) {
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

    /**
     * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController#volcarAreaCurricularSesion(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.areaCurricular.VolcarAreaCurricularSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void volcarTaxonomiaSesion(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.areaCurricular.VolcarTaxonomiaSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

    		
    		if(form.getTaxonSelec()!=null && !form.getTaxonSelec().trim().equals("")){
    	    	try{
	        		if(logger.isDebugEnabled())logger.debug("AreaCurricularControllerImpl - volcarTaxonomiaSesion");
	        		BuscarSession sesion = this.getBuscarSession(request);
	        		if ( sesion == null ) // si la sesion no existe (raro) la creamos y metemos el dato.
	        		sesion = new BuscarSession();

	        		//Metemos en la sesion las rutas taxonómicas seleccionadas hasta el momento
	        		//mas la que acabamos de seleccionar
		    		String newRuta = form.getTaxonomias() + "$" + form.getTaxonSelec();
		    		String rutas = sesion.getEnlaceTaxSelec()==null?"":sesion.getEnlaceTaxSelec();
		       		rutas = rutas.equals("")?newRuta:rutas + "*" + newRuta;
		       		sesion.setEnlaceTaxSelec(rutas);
	        	}catch (Exception e){
	        		logger.error("AreaCurricularControllerImpl - volcarTaxonomiaSesion ERROR: Error al volcar el area curricular en sesion", e);
	        	} 
       		}else{
       			throw new ValidatorException(MENSAJE_NO_SELEC_TAXON);
       		}

   	
     }

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador2.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

    }