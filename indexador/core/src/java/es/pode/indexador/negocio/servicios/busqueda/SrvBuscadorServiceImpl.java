/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
//license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */

package es.pode.indexador.negocio.servicios.busqueda;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import net.sf.dozer.util.mapping.MapperIF;

import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.ConstantScoreRangeQuery;
import org.apache.lucene.search.DefaultSimilarity;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.indexador.negocio.compass.GestorCompass;
import es.pode.indexador.negocio.dominio.IdentificadoresCriteria;
import es.pode.indexador.negocio.servicios.indexado.ContribucionVO;
import es.pode.indexador.negocio.servicios.indexado.IndexaODEException;
import es.pode.indexador.negocio.soporte.DocumentoIndexacion;
import es.pode.indexador.negocio.soporte.NumTermsArbol;
import es.pode.indexador.negocio.soporte.SugerenciasComparator;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.utiles.date.DateManager;
import es.pode.soporte.utiles.string.UtilesString;

/**
 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService
 */

public class SrvBuscadorServiceImpl extends
es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceBase {

	private java.util.Properties pSpringProperties = null;

	private static final String SEPARADOR_CLAVES = "&";
	private static final String BUSCARRSS = "BUSCARRSS";
	private static final String BUSCARRELACIONADOSAC="BUSCARRELACIONADOSAC";//BUSQUEDA DE ODES RELACIONADOS POR ARBOL CURRICULAR
	private static final String SEPARADOR_PHRASE = "\"";
	private static final String PREFIJO_CAMPO = "campo_";
	private static final String CAMPOS_LOWER_CASE = "campos_minusculas";
	private static final String CAMPOS_PHRASE_SIMPLE = "campos_phrase_simple";
	private static final String CAMPOS_PHRASE_AVANZADA = "campos_phrase_avanzada";
	private static final String CAMPOS_WILDCARD_AVANZADA = "campos_wildcard_avanzada";
	private static final String CAMPOS_RANGE_AVANZADA = "campos_range_avanzada";
	private static final String CAMPOS_TEXTO_LIBRE = "campos_texto_libre";
	private static final String CAMPOS_CLAVE_AVANZADA = "campos_clave_avanzada";
	private static final int NUMERO_SUGERENCIAS = 10;
	private static final int NUMERO_CAMPOS = 35;
	private static final String MAX = "5";
	private static final String PROTOCOLO_HTTP = "protocoloHttp";
	private static final String BUSCADOR_FICHA = "buscadorFicha";
	private static final int MAX_NIVEL_AGREGACION=4;
	private static final String SEPARADOR_TAX="/";
	private static final String EXP_REG_ESPACIOS_BLANCO="[\\s]+";//UNO O MÁS ESPACIO EN BLANCO
	private static final int NUM_SUG_BUSQ_DEFAULT =10;
	private static final String CAMPOS_LICENCIAS = "campos_licencias";
	private static final String CAMPOS_WILDCARD_LICENCIAS = "campos_wildcard_licencias";	
	private static final String CAMPOS_PHRASE_LICENCIAS = "campos_phrase_licencias";	

	private GestorCompass gestorCompass;
	public static Properties props = null;
	private MapperIF beanMapper = null;
	private static String campo_descripcion = null;
	private static String campo_titulo = null;
	private static String campo_idiomaBusqueda_compass = null;
	private static String campo_ambito = null;
	private static String campo_taxonomia_path=null;
	private static String campo_autor=null;
	private static String campo_destinatarios=null;
	private static String campo_formato=null;
	private static String campo_alcance=null;
	private static String campo_contribuidor=null;
	private static String campo_licencias=null;
	private static String campo_fechaPublicacion=null;
	private static String campo_nivelAgregacion=null;
	private static String campo_edad=null;
	private static String campo_descripcion_objetivo=null;
	private static String campo_imagen=null;
	private static String campo_palabrasClave=null;
	private static String campo_identificadorODE=null;
	private static String campo_recurso=null;
	private static String campo_publicador=null;
	private static String campo_relacion=null;
	private static String campo_fuente=null;
	private static String campo_localizador=null;
	private static String campo_horaPublicacion=null;
	private static String campo_valoracion=null;
	private static String campo_tamanio=null;
	private static String campo_secuencia=null;
	private static String campo_taxonomia=null;
	private static String campo_taxonomia_nodo=null;
	private static String campo_literalArbolC=null;
	private static String campo_contribucion=null;
	private static String arbolCurricularVigente=null;
	private static String nombre_tesauro=null;
	private static String id_tesauro=null;
	private static String camposAND=null;
	private static String camposOR=null;
	private static int numero_tesauros=0;
	private static int maxClauseCount=1024;
	private static int distanciaEntreCampos=0;
	private static ResultadosCountVO totalesRepositorio = null;
	private static PrioridadPalabrasClaveVO prioridadPalabrasClave = null;
	private static long startTotales = 0;
	private static long startPalabrasClave = 0;
	private static long startTesauro = 0;
	private static long startArbol = 0;
	private static int tiempoRefresco=0;
	private static String idiomaBusqueda=null;

	public MapperIF getBeanMapper() {
		return beanMapper;
	}

	public void setBeanMapper(MapperIF beanMapper) {
		this.beanMapper = beanMapper;
	}


	public SrvBuscadorServiceImpl() throws NumberFormatException, Exception {
		super();
		if(props==null){
			try {
				props = new Properties();
				props.load(this.getClass().getResourceAsStream(
						"/indexador.properties"));
				campo_descripcion=props.getProperty("campo_descripcion");
				campo_titulo=props.getProperty("campo_titulo");
				//				campo_idiomaBusqueda=props.getProperty("campo_idiomaBusqueda");
				campo_idiomaBusqueda_compass=props.getProperty("campo_idiomaBusqueda_compass");
				campo_ambito=props.getProperty("campo_ambito");
				campo_taxonomia_path=props.getProperty("campo_taxonomia_path");
				campo_taxonomia=props.getProperty("campo_taxonomia");
				campo_autor=props.getProperty("campo_autor");
				//				campo_curso=props.getProperty("campo_curso");
				campo_destinatarios=props.getProperty("campo_destinatarios");
				campo_formato=props.getProperty("campo_formato");
				campo_alcance=props.getProperty("campo_alcance");
				campo_contribuidor=props.getProperty("campo_contribuidor");
				campo_licencias=props.getProperty("campo_licencias");
				campo_fechaPublicacion=props.getProperty("campo_fechaPublicacion");
				campo_identificadorODE=props.getProperty("campo_identificadorODE");
				campo_nivelAgregacion=props.getProperty("campo_nivelAgregacion");
				campo_edad=props.getProperty("campo_edad");
				campo_descripcion_objetivo=props.getProperty("campo_descripcion_objetivo");
				campo_imagen=props.getProperty("campo_imagen");
				campo_palabrasClave=props.getProperty("campo_palabrasClave");
				campo_recurso=props.getProperty("campo_recurso");
				campo_publicador=props.getProperty("campo_publicador");
				campo_relacion=props.getProperty("campo_relacion");
				campo_fuente=props.getProperty("campo_fuente");
				campo_localizador=props.getProperty("campo_localizador");
				campo_horaPublicacion=props.getProperty("campo_horaPublicacion");
				campo_valoracion=props.getProperty("campo_valoracion");
				campo_tamanio=props.getProperty("campo_tamanio");
				campo_secuencia=props.getProperty("campo_secuencia");
				campo_taxonomia_nodo=props.getProperty("campo_taxonomia_nodo");
				campo_literalArbolC=props.getProperty("campo_literalArbolC");
				campo_contribucion=props.getProperty("campo_contribucion");
				camposAND=props.getProperty("campos_and_avanzada");
				camposOR=props.getProperty("campos_or_avanzada");
				numero_tesauros = Integer.parseInt(props.getProperty("numero_tesauros"));
				maxClauseCount=Integer.parseInt(props.getProperty("maxClauseCount"));
				distanciaEntreCampos=Integer.parseInt(props.getProperty("distancia_campos_frase"));
				startTotales = System.currentTimeMillis();
				startPalabrasClave = System.currentTimeMillis();
				startArbol = System.currentTimeMillis();
				startTesauro = System.currentTimeMillis();
				tiempoRefresco = Integer.parseInt(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIEMPO_REFRESCO));
			} catch (IOException e) {
				logger.error("ERROR: fichero no encontrado: indexador.properties. - ", e);
				throw new RuntimeException(e);
			}

		}


	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#busquedaSimple(es.pode.indexador.negocio.servicios.busqueda.ParamSimpleVO)
	 * @param paramBusq ParamSimpleVO Este VO alberga los parametros aceptados en la busqueda simple.
	 * @return DocumentosVO30 Esta clase representa los resultados de una busqueda.
	 * @deprecated 
	 */
	protected DocumentosVO30 handleBusquedaSimple(
			es.pode.indexador.negocio.servicios.busqueda.ParamSimpleVO paramBusq)
	throws java.lang.Exception {

		DocumentosVO30 respuesta = new DocumentosVO30();
		return respuesta;
	}	


	private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: <" + sKey + ">: <"+ pSpringProperties.getProperty(sKey) + ">");

		return pSpringProperties.getProperty(sKey);
	}


	private String[] cargarFormatoValue() throws IOException{

		String[] formatoArray = this.getPropertyValue("avanzado.formato").split(",");
		String[] formatoValue = {formatoArray[0], formatoArray[1], formatoArray[2], formatoArray[3], formatoArray[4]};
		return formatoValue;
	}


	private String[] cargarNivelAgregacionValue() throws IOException{

		String[] nivelAgregacionArray = this.getPropertyValue("avanzado.agregacion").split(",");
		return nivelAgregacionArray;
	}


	private boolean hasAnyFileFormat (String filter[]) {
		try {
			String[] formatoValue=new String[cargarFormatoValue().length];
			formatoValue=cargarFormatoValue();

			for(int i=0; i<formatoValue.length; i++)
				for(int j=0; j<filter.length; j++)
					if(filter[j].equals(formatoValue[i])) return true;

		} catch (IOException e) {
			logger.error(e);
		}
		return false;
	}


	private boolean hasAgregationLevelNot1 (String levels[]) {
		try {
			String[] nivelesAgregacionValue=new String[cargarNivelAgregacionValue().length];
			nivelesAgregacionValue=cargarNivelAgregacionValue();

			for(int i=0; i<nivelesAgregacionValue.length; i++)
				for(int j=0; j<levels.length; j++)
					if(levels[j].equals(nivelesAgregacionValue[i]) && !levels[j].equals("1")) 
						return true;

		} catch (IOException e) {
			logger.error(e);
		}
		return false;		
	}



	///METODO QUE SE IMPLEMENTO COMO POSIBLE SOLUCION AL FILTRO DE LA PAGINA DE RESULTADOS
	///DE AGREGA 2
	///ACTUALMENTE NO SE USA
	protected org.apache.lucene.search.Hits[] busquedaFiltroResultados(
			es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq,
			DisjunctionMaxQuery query)
	throws java.lang.Exception {

		Object[] hits=null;
		Object[] hitsA=null;
		Object[] hitsB=null;		
		String backupNivelAgreg[] = paramBusq.getNivelAgregacion();
		String backupFormato[] = paramBusq.getFormato();

		//Si se selecciono algun formato lanzamos la primera busqueda con los formatos elegidos y el nivel de agregacion 1
		if (paramBusq.getFormato()!=null && hasAnyFileFormat(paramBusq.getFormato())==true){

			String[] nivel=new String[1];
			nivel[0]="1";
			paramBusq.setNivelAgregacion(nivel);
			hitsA = new Hits[1];
			hitsA[0] = internaBusquedaAvanzada(paramBusq, query, "");
			paramBusq.setNivelAgregacion(backupNivelAgreg);
		}

		//Si se selecciono otro nivel de agregacion diferente al 1 lanzamos otra busqueda con el filtro de formato vacio y los niveles de agregacion elegidos
		if (paramBusq.getNivelAgregacion()!=null && hasAgregationLevelNot1(paramBusq.getNivelAgregacion())==true){

			String[] nivelesAgregacionValue=new String[cargarNivelAgregacionValue().length];
			nivelesAgregacionValue=cargarNivelAgregacionValue();

			int numNiveles=0;
			for(int i=0; i<nivelesAgregacionValue.length; i++)
				if(!nivelesAgregacionValue[i].equals("1")) 
					numNiveles++;

			String [] niveles = new String[numNiveles];
			for(int i=0, j=0; i<nivelesAgregacionValue.length; i++)
				if(!nivelesAgregacionValue[i].equals("1")) {
					niveles[j]=nivelesAgregacionValue[i];
					j++;
				}							

			paramBusq.setNivelAgregacion(niveles);
			paramBusq.setFormato(cargarFormatoValue());
			hitsB = new Hits[1];
			hitsB[0] = internaBusquedaAvanzada(paramBusq, query, "");
			paramBusq.setNivelAgregacion(backupNivelAgreg);
			paramBusq.setFormato(backupFormato);
		}

		//Unimos los dos resultados
		if (hitsA!=null && hitsB!=null) {
			hits = new Hits[2];
			hits[0]=hitsA;
			hits[1]=hitsB;

		} else if (hitsA!=null) 
			hits=hitsA;

		else if (hitsB!=null) 
			hits=hitsB;
		else {
			hits = new Hits[1];
			hits[0]=null;
		}
		return (Hits[]) hits;
	}


	/**
	 * Este manejador de servicio aparece como respuesta a una nueva de necesidad de agrega 2. 
	 * En el filtro de resultados se puede seleccionar filtro por nivel de agregacion (dos opciones;
	 * nivel 1 y/o el resto de niveles) y filtro por tipo de formato de fichero que solo se podra 
	 * aplicar a los resultados con nivel de agregacion 1.
	 * @param paramBusq
	 * @return
	 * @throws java.lang.Exception
	 */
	protected es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 handleBusquedaAvanzadaFiltroResultados(
			es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq,
			Boolean usoFiltroResultados)
	throws java.lang.Exception {


		//		Implementamos la busqueda avanzada.
		DocumentosVO30 respuesta = new DocumentosVO30();
		if (logger.isDebugEnabled())
			logger.debug("Parametros de la busqueda avanzada <"+ paramBusqAvanz2String(paramBusq) + ">");

		try{
			DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
			long start = System.currentTimeMillis();
			Object[] hits=null;
			boolean resultadoUnico = false;

			if (paramBusq.getPalabrasClave()!=null) {
				if(paramBusq.getPalabrasClave().trim().toLowerCase().equals(props.getProperty("agrega_admin") + " " + "portadareset")){
					totalesRepositorio=null;
					prioridadPalabrasClave=null;
				} else if (paramBusq.getPalabrasClave().trim().toLowerCase().startsWith(props.getProperty("agrega_admin") + " optimizar")){
					this.getGestorCompass().optimizarBorrados(null);
				} else if (paramBusq.getPalabrasClave().trim().toLowerCase().startsWith(props.getProperty("agrega_admin") + " ")){
					resultadoUnico = true;
					hits = new Hits[1];
					hits = this.internaBusquedaQuery(paramBusq, paramBusq.getPalabrasClave().toLowerCase().trim().substring((props.getProperty("agrega_admin") + " ").length()), query, false);
				} else if (paramBusq.getPalabrasClave().trim().toLowerCase().startsWith(props.getProperty("agrega_todos") + " ")){
					hits = this.internaBusquedaQuery(paramBusq, paramBusq.getPalabrasClave().toLowerCase().trim().substring((props.getProperty("agrega_todos") + " ").length()), query, true);
				} else {

					if (usoFiltroResultados!=null && usoFiltroResultados==true) {
						hits = busquedaFiltroResultados(paramBusq, query);
						if (hits.length==1) resultadoUnico = true;
						else resultadoUnico = false;

					} else {
						resultadoUnico = true;
						hits = new Hits[1];
						hits[0] = internaBusquedaAvanzada(paramBusq, query, "");
					}
				}
			}else{
				resultadoUnico = true;
				hits = new Hits[1];
				hits[0] = internaBusquedaAvanzada(paramBusq, query, "");
			}
			long end = System.currentTimeMillis();
			logger.debug("Se devuelven hits: <" + (hits!=null && hits.length>0 && hits[0]!=null?((Hits)hits[0]).length():"> Sin resultados"));

			if(paramBusq.getBusquedaSimpleAvanzada()!=null && !paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRSS) && !paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRELACIONADOSAC)){
				try{
					if ((paramBusq.getPalabrasClave() != null && !paramBusq.getPalabrasClave().equals(""))){
						List<String> claveBusqueda = this.obtenerPalabrasClave(paramBusq.getPalabrasClave().toLowerCase(),false);
						List <String>sugerencias=new ArrayList<String>();

						if (claveBusqueda!=null && claveBusqueda.size()>0){
							boolean suficientes=false;

							for (int i=0;i<claveBusqueda.size() && !suficientes;i++){
								if (!claveBusqueda.get(i).equals("")) {
									String[] suge = this.getGestorCompass().obtenerSugerencias(claveBusqueda.get(i), paramBusq.getIdiomaBusqueda(),NUMERO_SUGERENCIAS);
									if (suge!=null && suge.length>0) {

										for (int k=0;k<suge.length && sugerencias.size()<NUMERO_SUGERENCIAS;k++){
											boolean encontrado = false;

											for (int j=0;j<sugerencias.size() && !encontrado;j++)
												if(sugerencias.get(j).toString().equals(suge[k])) encontrado=true;

											if(!encontrado && validarPersonalizada(paramBusq)){
												Hits hitSugerencias=null;
												ParamAvanzadoVO paramBusqSug = paramBusq;
												paramBusqSug.setPalabrasClave(suge[k]);
												try{
													hitSugerencias = internaBusquedaAvanzada(paramBusqSug, query, "");
													if(hitSugerencias!=null && hitSugerencias.length()>0)sugerencias.add(suge[k]);
												}catch(Exception e){
													logger.error("Error solicitando comprobación sugerencia avanzada. Sugerencia= <"+suge[k]+">",e);
												}
											}else if(!encontrado && !validarPersonalizada(paramBusq)) sugerencias.add(suge[k]);
										}
									}
									if (sugerencias.size()==NUMERO_SUGERENCIAS) suficientes=true;
								}
							}
						}
						String[] cargarSugerencias =new String[]{};
						if (sugerencias !=null && sugerencias.size()>0){
							cargarSugerencias = new String [sugerencias.size()];
							for (int i=0;i<sugerencias.size();i++){
								cargarSugerencias[i]=sugerencias.get(i);
							}
						}       		
						respuesta.setSugerencias(cargarSugerencias);

					} else respuesta.setSugerencias(new String[] {});

				}catch(Exception e){
					logger.error("Error solicitando sugerencias para idioma: <"+paramBusq.getIdiomaBusqueda()+"> - ",e);
					respuesta.setSugerencias(new String[] {});
				}

				String nombreTesauroVig = getNombreTesauroVig();
				String idTesauroVig = getIdTesauroVig();

				try{
					TaxonVO30 [] cargarTesauros =new TaxonVO30[]{};
					if(paramBusq.getPalabrasClave()!=null && !paramBusq.getPalabrasClave().trim().equals("")){
						List<String> palabrasTesauro = this.obtenerPalabrasClave(paramBusq.getPalabrasClave().toLowerCase(),true);
						List <String>nombreTesauros= new ArrayList<String>();
						List <String>identificadorTesauros= new ArrayList<String>();        
						if (palabrasTesauro != null && palabrasTesauro.size()>0){
							int numeroTax=0;
							for (int i=0;i<palabrasTesauro.size() && (numeroTax<numero_tesauros) ;i++){
								TaxonVO[] taxones=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorTexto(palabrasTesauro.get(i), nombreTesauroVig, paramBusq.getIdiomaBusqueda());
								if(taxones !=null && taxones.length>0){
									String[] idTesauro=new String[taxones.length];
									for (int k=0;k<taxones.length;k++){
										idTesauro[k]=idTesauroVig+SEPARADOR_TAX+taxones[k].getId();
									}
									Integer[] tesauros = this.getGestorCompass().numTermsArbol(idTesauro, paramBusq.getIdiomaBusqueda(), campo_taxonomia);
									for (int k=0; k<taxones.length && (numeroTax<numero_tesauros) ;k++){
										if(idTesauro!=null && idTesauro.length!=0){
											for (int j=0;j<idTesauro.length;j++){
												if (idTesauro[j].equals(idTesauroVig+SEPARADOR_TAX+taxones[k].getId())){
													if(tesauros[j].intValue() > 0){
														nombreTesauros.add(taxones[k].getValorTax());
														identificadorTesauros.add(taxones[k].getId());
														numeroTax = numeroTax + 1;
													}
												}										
											}
										}	        					
									}
								}
							}
						}
						if (nombreTesauros !=null && nombreTesauros.size()>0){
							cargarTesauros = new TaxonVO30 [nombreTesauros.size()];
							for (int i=0;i<nombreTesauros.size();i++){
								cargarTesauros[i]=new TaxonVO30(identificadorTesauros.get(i).toString(), nombreTesauros.get(i).toString());
							}
						}
						respuesta.setTesauros(cargarTesauros);
					}else respuesta.setTesauros(new TaxonVO30[]{});
				}catch(Exception e){
					logger.error("Error obteniendo sugerencias de tesauro <"+nombreTesauroVig+"> con: <"+paramBusq.getPalabrasClave()+"> número de tesauros máximo solicitado= <"+numero_tesauros+"> e idioma <"+paramBusq.getIdiomaBusqueda()+"> - ",e);
					respuesta.setTesauros(new TaxonVO30[]{});
				}
			}

			if (hits == null || (resultadoUnico && hits[0]==null)) {

				respuesta.setTotalResultados(0);
				respuesta.setNumeroResultados(0);
				respuesta.setNumDocumentosIndice(0);
				respuesta.setResultados(new DocVO30[0]);
			} else{
				long start2 = System.currentTimeMillis();
				if(hits.length > 1){
					int totalResultados = 0;
					List<DocVO30> docsList = new ArrayList<DocVO30>();

					for(int i = 0; i < hits.length && docsList.size()<= paramBusq.getNumeroResultados(); i++){
						if(hits[i]!=null && ((Hits)hits[i]).length() > 0){
							totalResultados = totalResultados + ((Hits)hits[i]).length();
							DocVO30[] docs = this.getArrayDocsFromHits((Hits)hits[i],((((Hits)hits[i]).length()<paramBusq.getNumeroResultados().intValue()) || paramBusq.getNumeroResultados().intValue()==-1)?((Hits)hits[i]).length():paramBusq.getNumeroResultados().intValue());
							for(int j = 0; j < docs.length ; j++){
								docsList.add(docs[j]);
							}
						}
					}
				
					DocVO30[] docs = new DocVO30[docsList.size()];
					for(int i = 0; i < docs.length ; i++){
						docs[i] = docsList.get(i);
					}
					respuesta.setTotalResultados(totalResultados);
					respuesta.setResultados(docs);
					//respuesta.setTotalResultados(respuesta.getResultados().length);
				}else{
					respuesta.setTotalResultados(((Hits)hits[0]).length());
					respuesta.setResultados(this.getArrayDocsFromHits((Hits)hits[0],((((Hits)hits[0]).length()<paramBusq.getNumeroResultados().intValue()) || paramBusq.getNumeroResultados().intValue()==-1)?((Hits)hits[0]).length():paramBusq.getNumeroResultados().intValue()));
					//respuesta.setTotalResultados(respuesta.getResultados().length);
				}
				end = System.currentTimeMillis();

				respuesta.setNumDocumentosIndice(this.getGestorCompass().numDocs(paramBusq.getIdiomaBusqueda()));
				respuesta.setNumeroResultados(respuesta.getResultados().length);
			}

		}catch(Exception e){
			logger.error("Error en búsqueda: <"+paramBusq.getIdiomaBusqueda() + ">",e);
			throw new Exception("SrvBuscadorServiceImpl - handleBusquedaAvanzadaFiltroResultados: no se encuantra el índice con idioma:"+paramBusq.getIdiomaBusqueda());
		}

		return respuesta;	
	}


	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#busquedaAvanzada(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO)
	 * @param paramBusq ParamAvanzadoVO VO que alberga los parametros que acepta una busqueda avanzada.
	 * @return DocumentosVO30 Esta clase representa los resultados de una busqueda. 
	 */
	protected es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 handleBusquedaAvanzada(
			es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq)
	throws java.lang.Exception {

		return handleBusquedaAvanzadaFiltroResultados(paramBusq, false);
	}


	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#busquedaAvanzada(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO)
	 * @param paramBusq ParamAvanzadoVO VO que alberga los parametros que acepta una busqueda avanzada.
	 * @param nodo Identificador del nodo remoto
	 * @return DocumentosVO30 Esta clase representa los resultados de una busqueda. 
	 */
	@Override
	protected DocumentosVO30 handleBusquedaAvanzadaNodos(
			ParamAvanzadoVO paramBusq, String idnodo) throws Exception {

		//Implementamos la busqueda avanzada.
		DocumentosVO30 respuesta = new DocumentosVO30();
		try{

			DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
			long start = System.currentTimeMillis();
			Object[] hits=null;
			boolean resultadoUnico = false;

			resultadoUnico = true;
			hits = new Hits[1];
			hits[0] = internaBusquedaAvanzada(paramBusq, query, idnodo);

			long end = System.currentTimeMillis();
			if (logger.isDebugEnabled()){
				logger.debug("Se devuelven hits: " + (hits!=null && hits.length>0 && hits[0]!=null?((Hits)hits[0]).length():"Sin resultados"));
			}
			if(paramBusq.getBusquedaSimpleAvanzada()!=null && !paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRSS) && !paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRELACIONADOSAC)){
				try{
					/*if ((paramBusq.getPalabrasClave() != null && !paramBusq.getPalabrasClave().equals(""))){
						List<String> claveBusqueda = this.obtenerPalabrasClave(paramBusq.getPalabrasClave().toLowerCase(),false);
						List <String>sugerencias=new ArrayList<String>();
						if (claveBusqueda!=null && claveBusqueda.size()>0){
							boolean suficientes=false;
							for (int i=0;i<claveBusqueda.size() && !suficientes;i++){
								if (!claveBusqueda.get(i).equals("")){
										String[] suge = this.getGestorCompass().obtenerSugerencias(claveBusqueda.get(i), paramBusq.getIdiomaBusqueda(),NUMERO_SUGERENCIAS);
										if (suge!=null && suge.length>0){
											for (int k=0;k<suge.length && sugerencias.size()<NUMERO_SUGERENCIAS;k++){
												boolean encontrado = false;
												for (int j=0;j<sugerencias.size() && !encontrado;j++){
													if(sugerencias.get(j).toString().equals(suge[k])) encontrado=true;
												}
												if(!encontrado && validarPersonalizada(paramBusq)){
													Hits hitSugerencias=null;
													ParamAvanzadoVO paramBusqSug = paramBusq;
													paramBusqSug.setPalabrasClave(suge[k]);
													try{
														hitSugerencias = internaBusquedaAvanzada(paramBusqSug, query, "");
														if(hitSugerencias!=null && hitSugerencias.length()>0)sugerencias.add(suge[k]);
													}catch(Exception e){
									        			logger.error("Error solicitando comprobación sugerencia avanzada. Sugerencia="+suge[k],e);
									        		}
												}else if(!encontrado && !validarPersonalizada(paramBusq)) sugerencias.add(suge[k]);
											}
										}
									if (sugerencias.size()==NUMERO_SUGERENCIAS) suficientes=true;
								}
							}
						}
						String[] cargarSugerencias =new String[]{};
		        		if (sugerencias !=null && sugerencias.size()>0){
		        			cargarSugerencias = new String [sugerencias.size()];
		        			for (int i=0;i<sugerencias.size();i++){
		        				cargarSugerencias[i]=sugerencias.get(i);
		        			}
		        		}       		
						respuesta.setSugerencias(cargarSugerencias);
					} else */	respuesta.setSugerencias(new String[] {});
				}catch(Exception e){
					logger.error("Error solicitando sugerencias para idioma: <"+paramBusq.getIdiomaBusqueda()+">",e);
					respuesta.setSugerencias(new String[] {});
				}
				/*logger.debug("Vamos a obtener las sugerencias de tesauro.");
				String nombreTesauroVig = getNombreTesauroVig();
				String idTesauroVig = getIdTesauroVig();				
				try{
					TaxonVO30 [] cargarTesauros =new TaxonVO30[]{};
		    		if(paramBusq.getPalabrasClave()!=null && !paramBusq.getPalabrasClave().trim().equals("")){
		        		List<String> palabrasTesauro = this.obtenerPalabrasClave(paramBusq.getPalabrasClave().toLowerCase(),true);
		        		List <String>nombreTesauros= new ArrayList<String>();
		        		List <String>identificadorTesauros= new ArrayList<String>();        
		        		if (palabrasTesauro != null && palabrasTesauro.size()>0){
		        			int numeroTax=0;
		        			for (int i=0;i<palabrasTesauro.size() && (numeroTax<numero_tesauros) ;i++){
		        				TaxonVO[] taxones=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorTexto(palabrasTesauro.get(i), nombreTesauroVig, paramBusq.getIdiomaBusqueda());
		        				if(taxones !=null && taxones.length>0){
			        				String[] idTesauro=new String[taxones.length];
			        				for (int k=0;k<taxones.length;k++){
			        					idTesauro[k]=idTesauroVig+SEPARADOR_TAX+taxones[k].getId();
			        				}
			        				Integer[] tesauros = this.getGestorCompass().numTermsArbol(idTesauro, paramBusq.getIdiomaBusqueda(), campo_taxonomia);
			        				for (int k=0; k<taxones.length && (numeroTax<numero_tesauros) ;k++){
				        				if(idTesauro!=null && idTesauro.length!=0){
											for (int j=0;j<idTesauro.length;j++){
												if (idTesauro[j].equals(idTesauroVig+SEPARADOR_TAX+taxones[k].getId())){
													if(tesauros[j].intValue() > 0){
														nombreTesauros.add(taxones[k].getValorTax());
														identificadorTesauros.add(taxones[k].getId());
							        					numeroTax = numeroTax + 1;
													}
												}										
											}
										}	        					
			        				}
		        				}
		        			}
		        		}
	 	        		if (nombreTesauros !=null && nombreTesauros.size()>0){
		        			cargarTesauros = new TaxonVO30 [nombreTesauros.size()];
		        			for (int i=0;i<nombreTesauros.size();i++){
		        				cargarTesauros[i]=new TaxonVO30(identificadorTesauros.get(i).toString(), nombreTesauros.get(i).toString());
		        			}
		        		}
		        		respuesta.setTesauros(cargarTesauros);
		    		}else respuesta.setTesauros(new TaxonVO30[]{});
				}catch(Exception e){
	    			logger.error("Error obteniendo sugerencias de tesauro ["+nombreTesauroVig+"] con:"+paramBusq.getPalabrasClave()+" número de tesauros máximo solicitado="+numero_tesauros+" e idioma="+paramBusq.getIdiomaBusqueda(),e);
	    			respuesta.setTesauros(new TaxonVO30[]{});
	    		}*/
			}

			if (hits == null || (resultadoUnico && hits[0]==null)) {
				logger.debug("No hay resultados");
				respuesta.setTotalResultados(0);
				respuesta.setNumeroResultados(0);
				respuesta.setNumDocumentosIndice(0);
			} else{
				long start2 = System.currentTimeMillis();
				if(hits.length > 1){
					int totalResultados = 0;
					List<DocVO30> docsList = new ArrayList<DocVO30>();
					for(int i = 0; i < hits.length && docsList.size()<= paramBusq.getNumeroResultados().intValue(); i++){
						if(hits[i]!=null && ((Hits)hits[i]).length() > 0){
							totalResultados = totalResultados + ((Hits)hits[i]).length();
							DocVO30[] docs = this.getArrayDocsFromHits((Hits)hits[i],((((Hits)hits[i]).length()<paramBusq.getNumeroResultados().intValue()) || paramBusq.getNumeroResultados().intValue()==-1)?((Hits)hits[i]).length():paramBusq.getNumeroResultados().intValue());
							for(int j = 0; j < docs.length ; j++){
								docsList.add(docs[j]);
							}
						}
					}
					DocVO30[] docs = new DocVO30[docsList.size()];
					docs=docsList.toArray(docs);
					//					for(int i = 0; i < docs.length ; i++){
					//						docs[i] = docsList.get(i);
					//					}
					respuesta.setTotalResultados(totalResultados);
					respuesta.setResultados(docs);
					//respuesta.setTotalResultados(respuesta.getResultados().length);
				}else{
					respuesta.setTotalResultados(((Hits)hits[0]).length());
					respuesta.setResultados(this.getArrayDocsFromHits((Hits)hits[0],((((Hits)hits[0]).length()<paramBusq.getNumeroResultados().intValue()) || paramBusq.getNumeroResultados().intValue()==-1)?((Hits)hits[0]).length():paramBusq.getNumeroResultados().intValue()));
					//respuesta.setTotalResultados(respuesta.getResultados().length);
				}
				end = System.currentTimeMillis();

				//TODO
				//				respuesta.setNumDocumentosIndice(this.getGestorCompass().numDocs(paramBusq.getIdiomaBusqueda()));
				respuesta.setNumeroResultados(respuesta.getResultados().length);
			}

		}catch(Exception e){
			logger.error("error en búsqueda: <" + paramBusq.getIdiomaBusqueda() + ">",e);
			return respuesta;
		}

		//logger.debug("LUIS (indexador handleBusquedaAvanzadaNodos) long results="+respuesta.getResultados().length+", nodo="+idnodo);
		//logger.debug("LUIS (indexador handleBusquedaAvanzadaNodos) num results="+respuesta.getTotalResultados()+", nodo="+idnodo);

		return respuesta;
	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#busquedaLOM_ES(es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO)
	 * @param paramBusq QuerySimpleVO Parametros de una query simple.
	 * @return DocumentosLOM_ESVO Esta clase representa los documentos resultado de una busqueda por LOM_ES.
	 */
	protected es.pode.indexador.negocio.servicios.busqueda.DocumentosLOM_ESVO handleBusquedaLOM_ES(
			es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO paramBusq)
	throws java.lang.Exception {
		DocumentosLOM_ESVO respuesta = new DocumentosLOM_ESVO();
		DocLOM_ESVO[] resultados = null;
		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
		String queryLang = paramBusq.getLenguajeQuery();
		String idioma = paramBusq.getIdioma();
		String unparsedQuery = paramBusq.getQuery();
		Hits hits = null;
		logger.debug("Recibida query en lenguaje <"+queryLang+"> con contenido <"+unparsedQuery+">");
		// Discriminar SQI / Lucene
		if ("VSQL".equals(queryLang)) 
		{
			String[] terms = parsearVSQL(unparsedQuery);
			// Introduzco los terminos de busqueda en titulo, descripcion y
			// palabras clave
			logger.debug("Creando query a partir de terminos <"+Arrays.toString(terms)+">");
			for (int i = 0; i < terms.length; i++) 
			{
				query.add(getTermQuery(campo_titulo, terms[i]
				                                           .toLowerCase()));
				query.add(getTermQuery(campo_descripcion,
						terms[i].toLowerCase()));
				query.add(getTermQuery(campo_palabrasClave, terms[i]
				                                                  .toLowerCase()));
			}
		} 
		else if ("LQS".equals(queryLang)) 
		{
			logger.debug("Parseando query LUCENE_LANG: <" + unparsedQuery +">");
			QueryParser parser = new QueryParser(campo_titulo, new StandardAnalyzer());
			parser.setLowercaseExpandedTerms(true);
			Query lqsQuery = parser.parse(unparsedQuery);
			query.add(lqsQuery);
		}else{
			logger.error("Se ha recibido una query que no es SQI ni Lucene: <"+queryLang+">");
			throw new Exception("Se ha recibido una query que no es SQI ni Lucene: <"+queryLang+">");
		}

		// Preparando busqueda

		try{
			if (logger.isDebugEnabled())
				logger.debug("Consulta LOM_ES con query <"+query.toString()+">");
			hits = this.getGestorCompass().busqueda(query, idioma, null);
			logger.debug("Numero de hits obtenidos <"+(hits != null?hits.length():0)+">");
			if (hits != null && hits.length() > 0) 
			{
				resultados = new DocLOM_ESVO[hits.length()];
				logger.debug("Numero de hits <" + hits.length()+">");
				//con resultados <" + Arrays.toString(resultados)+">

				for (int i = 0; i < hits.length(); i++) 
				{
					DocLOM_ESVO hitToDoc = new DocLOM_ESVO();
					Document doc = hits.doc(i);
					String localizador = doc.get(campo_localizador);

					hitToDoc.setRanking(Float.floatToIntBits(hits.score(i)));

					Manifest manifest = this.parsearManifiesto(localizador+ File.separator + "imsmanifest.xml");
					Lom lom = null;

					if (manifest != null) 
					{
						//logger.debug("manifest!=null <" + manifest + ">");

						ManifestAgrega ma = new ManifestAgrega(manifest);
						lom = ma.obtenerLom(manifest.getIdentifier(), null);

						/*
						 * Este codigo seguramente es viejo: El mapeo de Lom a Lom
						 * entiendo que es innecesario: para escribir el lomes a un
						 * Write no es necesario tener un clon del objeto Lom.
						 * 
						 * La forma recomendada de obtener el Lom de un Manifest es
						 * mediante los metodos de la clase ManifestAgrega
						 * 
						 * fjespino
						 */
						//					lom = this.getLom(manifest.getMetadata(), manifest.getIdentifier());
						//					lomCastor = (es.pode.parseadorXML.lomes.Lom) beanMapper
						//							.map(lom, es.pode.parseadorXML.lomes.Lom.class);

						//					creo un writer para escribir los metadatos
						StringWriter sw= new StringWriter();
						//completo el writer
						this.getLomesDao().escribirLom(lom, sw);
						hitToDoc.setDocumento(sw.toString());
						resultados[i] = hitToDoc;
					}
					//logger.debug("manifest==null <" + manifest + ">");
				}
				respuesta.setResultados(resultados);
			}
		}catch(Exception e){
			logger.error("handleBusquedaLOM_ES con query: <"+query.toString() +">",e);
			throw new Exception("handleBusquedaLOM_ES con query:"+query.toString());
		}
		return respuesta;
	}

	/**
	 * Metodo copiado de SrvSQIService para parseo de VSQI. TODO Corregir para
	 * hacer un parseo XML mas fiable.
	 * 
	 * @param queryStatement query a parsear
	 * @return String[] Strings de términos para añadir a query
	 */
	private String[] parsearVSQL(String queryStatement) {
		String c = queryStatement.substring(13, queryStatement.length() - 14);
		ArrayList<String> stringList = new ArrayList<String>();
		while (c.startsWith("<term>")) {
			String term = c.substring(6, c.indexOf("</term>"));
			stringList.add(term);
			c = c.substring(13 + term.length());
		}
		return stringList.toArray(new String[stringList.size()]);
	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#handleBusquedaMEC(String identificadorMEC, String idiomaBusqueda)
	 * @param identificadorMEC identificador de archivo a buscar,
	 * @param  idiomaBusqueda idioma de búsqueda
	 * @return DocVO30 Esta clase representa los documentos resultado de una busqueda por LOM_ES.
	 */
	protected DocVO30 handleBusquedaMEC(String identificadorMEC, String idiomaBusqueda) throws Exception {

		//Si recibimos el idioma vacio buscaremos en todos los idiomas
		if(idiomaBusqueda.isEmpty()) idiomaBusqueda=null;
		
		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);

		Hits hits = this.internaBusquedaMEC(identificadorMEC, query, idiomaBusqueda);
		if (hits == null || (hits != null && hits.length() == 0)) {
			if (logger.isDebugEnabled())
				logger.debug("No hay resultados para una busqueda con MEC <"+identificadorMEC+">");
			return null;
		}
		if (logger.isDebugEnabled())
			logger.debug("Se obtienen <"+hits.length()+"> hits de una busqueda por MEC <"+identificadorMEC+">");
		DocVO30 docReturn = this.getVOFromLucene(hits.doc(0));

		if (docReturn != null)	return docReturn;
		return null;
	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceBase#handleSolicitudDocsCountArbolCurricular(ParamDocsCountVO)
	 * 
	 * @param paramBusq ParamDocsCountVO VO que alberga los parametros que acepta una solicitud de numero de documentos para el nodo de un arbol curricular.
	 * @return ResultadosCountVO VO que alberga el resultado de la solicitud de la suma de documentos para un nodo de arbol curricular.
	 */
	protected ResultadosCountVO handleSolicitudDocsCount(ParamDocsCountVO paramBusq) throws Exception {
		ResultadosCountVO resultado = new ResultadosCountVO();

		try{
			//			Integer[] conteo = this.getGestorCompass().numTermsArbol(paramBusq.getTaxonomia(),paramBusq.getIdiomaBusqueda(),campo_taxonomia);
			Integer[] conteo = this.getGestorCompass().numTermsArbolQuery(paramBusq.getTaxonomia(),paramBusq.getIdiomaBusqueda(),campo_taxonomia);
			int suma = 0;
			for(int i = 0 ; i < conteo.length; i++){
				if(conteo[i]==null) conteo[i]=0;
				//else suma = suma + (conteo[i]!=null?conteo[i]:0);
				else suma = suma + conteo[i];
			}
			resultado.setConteo(conteo);
			resultado.setDocumentosCount(suma);

		}catch(Exception e){
			logger.error("handleSolicitudDocsCount con idioma: <"+paramBusq.getIdiomaBusqueda() + "> Taxonomía: <"+array2String(paramBusq.getTaxonomia()) + ">",e);
			throw new Exception("handleSolicitudDocsCount con idioma: <"+paramBusq.getIdiomaBusqueda() + "> Taxonomía: <"+array2String(paramBusq.getTaxonomia()) + ">");
		}
		return resultado;
	}


	/**
	 * Este metodo busca los documentos indexados que cumplen estar dentro del rango de fechas dado y que tengan el valor de licencia dado.
	 * 
	 * @param licencia Licencia de la que se quiere saber el numero de documentos que la contiene.
	 * @param fechaDesde Fecha desde del periodo en el que se esta interesado.
	 * @param fechaHasta Fecha hasta del periodo en el que se esta interesado.
	 * @return ResultadosCountVO VO que alberga el resultado de la solicitud de la suma de documentos que cumplen la condicion.
	 */
	protected ResultadosCountVO handleBusquedaDocsLicenciaFecha(String licencia, Calendar fechaDesde, Calendar fechaHasta) throws Exception {
		if (licencia == null || licencia.equals(""))
		{
			logger.warn("Error buscando documentos con licencias. Licencia vacia o nula.");
			throw new Exception("Error buscando documentos con licencias. Licencia vacia o nula.");
		}
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error buscando documentos con licencias. Fechas desde <"+fechaDesde==null?null:fechaDesde.toString()+"> y hasta <"+fechaHasta==null?null:fechaHasta.toString()+"> vacias.");
			throw new Exception("Error buscando documentos con licencias. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
		}
		if (fechaDesde.after(fechaHasta))
		{
			logger.warn("Error buscando documentos con licencias. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error buscando documentos con licencias. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		Calendar antes = Calendar.getInstance();
		Calendar despues= Calendar.getInstance();
		antes.setTime(fechaDesde.getTime());
		despues.setTime(fechaHasta.getTime());
		String antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		String despuesS = ""+despues.get(Calendar.YEAR)+formatMonthMM((despues.get(Calendar.MONTH)+1))+formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		//aniadimos la query con el rango de fechas
		andQuery.add(getRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);
		//aniadimos la query con el id de la licencia
		//    	QueryParser parser = new QueryParser(props.getProperty(CAMPO_LICENCIA), new StandardAnalyzer());
		//    		// el standar analyzer utiliza el StandardTokenizer gracias al cual elimina de la licencia los caracteres extranios
		//    		// para ver que caracteres tiene en cuenta al tokenizar:http://lucene.zones.apache.org:8080/hudson/job/Lucene-Nightly/javadoc/org/apache/lucene/analysis/standard/StandardTokenizer.html
		//		Query licenciaQuery = parser.parse("\""+licencia+"\""); //tenemos en cuenta el texto entre comillas para que el parseo no se pierda.
		//			// hemos convertido en tokens las palabras de la licencia
		//    	andQuery.add(getPhraseQuery(props.getProperty(CAMPO_LICENCIA), Arrays.asList(licencia.split(" "))),BooleanClause.Occur.MUST);
		andQuery.add(getTermQuery(campo_licencias, licencia), BooleanClause.Occur.MUST);
		//		andQuery.add(licenciaQuery,BooleanClause.Occur.MUST);

		//		Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
		Hits hits = null;
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
		} catch (Exception e) {
			logger.error("Error buscando documentos con licencias. Error buscando en indice con query <"+andQuery.toString()+">.Causa: <" + e.getCause() + "> <"+e+">");
			throw new Exception("Error buscando documentos con licencias. Error buscando en indice con query["+andQuery.toString()+"].",e);
		}
		ResultadosCountVO resultado = new ResultadosCountVO();
		if (hits != null)
			resultado.setDocumentosCount(hits.length());
		else
			resultado.setDocumentosCount(0);
		return resultado;
	}

	/**
	 * Este metodo busca los documentos que tienen fecha entre las fechas desde y hasta dados y que tengan el campo de arbol curricular
	 *  nodo coincidente con el identificador de nodo que le pasan. 
	 * Esto devuelve el numero de documentos indexados que estan catalogados exactamente en ese nodo de arbol curricular.
	 * @param idNodo Identificador del nodo de arbol curricular del que se quiere averiguar el numero de documentos que tiene asociados.
	 * @param fechaDesde Fecha desde del periodo en el que se esta interesado.
	 * @param fechaHasta Fecha hasta del periodo en el que se esta interesado.
	 * @return ResultadosCountVO VO que alberga el resultado de la solicitud de la suma de documentos que cumplen la condicion.
	 */
	protected ResultadosCountVO handleBusquedaDocsNodoFechaArbolCurricular(String idNodo, Calendar fechaDesde, Calendar fechaHasta) throws Exception {
		ResultadosCountVO resultado = new ResultadosCountVO();
		if (idNodo == null || idNodo.equals(""))
		{
			logger.warn("Error buscando nodo de arbol curricular. Nodo vacio.");
			throw new Exception("Error buscando nodo de arbol curricular. Nodo vacio.");
		}
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
			throw new Exception("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
		}
		if (fechaDesde.after(fechaHasta))
		{
			logger.warn("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		Calendar antes = Calendar.getInstance();
		Calendar despues= Calendar.getInstance();
		antes.setTime(fechaDesde.getTime());
		despues.setTime(fechaHasta.getTime());
		//TODO Propuesta
		//java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyyMd");
		//formato.format(antes);
		//formato.format(despues);
		String antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		String despuesS = ""+despues.get(Calendar.YEAR)+formatMonthMM((despues.get(Calendar.MONTH)+1))+formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		//aniadimos la query con el rango de fechas
		andQuery.add(getRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);



		//aniadimos la query con el id del nodo

		///Con el indice de desarrollo salta a veces una nullPointer
		String idArbolCurricularVig = getIdArbolCurricularVig();
		try{
			String nodo = idArbolCurricularVig+SEPARADOR_TAX+idNodo;
			Query termQuery = getTermQuery(campo_taxonomia_nodo, nodo.toLowerCase());
			logger.debug("termQuery: <" + termQuery + ">");	
			andQuery.add(termQuery,BooleanClause.Occur.MUST);
		}catch(Exception e)
		{
			logger.error("Exception busqueda por arbol <" + idArbolCurricularVig +"> - <"+e+">");
		}

		//		Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
		Hits hits;
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);

			if (hits != null)
				resultado.setDocumentosCount(hits.length());
			else
				resultado.setDocumentosCount(0);

		} catch (Exception e) {
			logger.error("Error buscando nodo de arbol curricular. Error buscando en indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> - "+e);
			throw new Exception("Error buscando nodo de arbol curricular. Error buscando en indice con query["+andQuery.toString()+"].",e);
		}

		return resultado;
	}
	
	/**
	 * Este metodo busca los documentos indexados que cumplan estar en el nivel de agregación que le pasan dentro de las fechas desde y hasta.
	 * @param nivelAgregacion Valor de nivel de agregación.
	 * @param fechaDesde Fecha desde del periodo en el que se esta interesado.
	 * @param fechaHasta Fecha hasta del periodo en el que se esta interesado.
	 * @return ResultadosCountVO VO que alberga el resultado de la solicitud de la suma de documentos que cumplen la condicion.
	 */
	protected ResultadosCountVO handleBusquedaDocsRangoFechaNivelAgregacion(String nivelAgregacion, Calendar fechaDesde, Calendar fechaHasta) throws Exception {
		if (nivelAgregacion == null || nivelAgregacion.equals(""))
		{
			logger.warn("Error buscando documentos con rango de valoracion. Nivel de agregacion vacio.");
			throw new Exception("Error buscando documentos con rango de valoracion. Nivel de agregacion vacio.");
		}
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
			throw new Exception("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
		}
		if (fechaDesde.after(fechaHasta))
		{
			logger.warn("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		Calendar antes = Calendar.getInstance();
		Calendar despues= Calendar.getInstance();
		antes.setTime(fechaDesde.getTime());
		despues.setTime(fechaHasta.getTime());
		String antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		String despuesS = ""+despues.get(Calendar.YEAR)+formatMonthMM((despues.get(Calendar.MONTH)+1))+formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		//aniadimos la query con el rango de fechas
		andQuery.add(getRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);
		//aniadimos la query con la valoracion
		andQuery.add(getTermQuery(campo_nivelAgregacion, nivelAgregacion),BooleanClause.Occur.MUST);

		//		Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
		Hits hits;
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
		} catch (Exception e) {
			logger.error("Error buscando documentos con rango de valoracion. Error buscando en indice con query["+andQuery.toString()+"].["+e.getCause()+"]");
			throw new Exception("Error buscando documentos con rango de valoracion. Error buscando en indice con query["+andQuery.toString()+"].",e);
		}
		ResultadosCountVO resultado = new ResultadosCountVO();
		if (hits != null)
			resultado.setDocumentosCount(hits.length());
		else
			resultado.setDocumentosCount(0);
		return resultado;
	}

	/**
	 * Este metodo obtiene los palabras clave indexadas en el indice.
	 * @param paramBusq Parametros de búsqueda.
	 * @return PrioridadPalabrasClaveVO VO que alberga las palabras clave obtenidas y su ranking (número odes en los que aparece).
	 */
	protected PrioridadPalabrasClaveVO handleObtenerPalabrasClave(ParamPalabrasClave paramBusq) throws Exception {

		if((idiomaBusqueda !=null && !idiomaBusqueda.equals(paramBusq.getIdiomaBusqueda())) || //cambiamos idioma de busqueda
				(((prioridadPalabrasClave==null) || ((System.currentTimeMillis() - startPalabrasClave) >= tiempoRefresco)) && AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NUMERO_TAGS).equals(String.valueOf(paramBusq.getNumeroResultados())))){
			try{
				prioridadPalabrasClave = this.getGestorCompass().highFreqTerms(paramBusq.getNumeroResultados().intValue(), paramBusq.getIdiomaBusqueda(), campo_palabrasClave);
				idiomaBusqueda = paramBusq.getIdiomaBusqueda();
				startPalabrasClave=System.currentTimeMillis();

			}catch(java.lang.Exception e){
				Exception exc = new Exception("handleObtenerPalabrasClave ERROR: Con idioma="+paramBusq.getIdiomaBusqueda(), e);
				logger.error(exc);
				throw exc;
			}

		}else if(!AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NUMERO_TAGS).equals(String.valueOf(paramBusq.getNumeroResultados()))){
			try{

				return this.getGestorCompass().highFreqTerms(paramBusq.getNumeroResultados().intValue(), paramBusq.getIdiomaBusqueda(), campo_palabrasClave);

			}catch(java.lang.Exception e){
				Exception exc = new Exception("handleObtenerPalabrasClave ERROR: Con idioma="+paramBusq.getIdiomaBusqueda(), e);
				logger.error(exc);
				throw exc;
			}
		}		
		return prioridadPalabrasClave;
	}

	/**
	 * Este metodo busca los ODEs indexados en todos los indices que cumplan las condiciones de busqueda que le pasan como parametro.
	 * Las fechas desde y hasta se interpretan inclusives y en el caso de no estar presentes, sin limite superior o inferior.
	 * @param paramBusq Parametros de busqueda.
	 * @return ResultadoHeaderVO[] Array de VO's que albergan los resulados de la consulta en un contenedor de Headers.
	 */
	protected es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] handleBusquedaHeadersRepositorio(ParamPeriodoRepositorioVO paramBusq) throws Exception {

		if (paramBusq == null)
		{
			logger.warn("Error obteniendo las cabeceras del repositorio. No hay parametros de busqueda.");
			throw new Exception("Error obteniendo las cabeceras del repositorio. No hay parametros de busqueda.");
		}
		Date fechaDesde = (paramBusq.getDesde()!= null?paramBusq.getDesde().getTime():null);
		Date fechaHasta = (paramBusq.getHasta()!= null?paramBusq.getHasta().getTime():null);
		if (fechaDesde != null && fechaHasta != null && fechaDesde.after(fechaHasta))
		{
			logger.warn("Error obteniendo las cabeceras del repositorio. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error obteniendo las cabeceras del repositorio. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		//    	Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		//    	Configuramos la fecha desde
		String antesS = null;
		String despuesS = null;
		if (fechaDesde != null){
			Calendar antes = Calendar.getInstance();
			antes.setTime(fechaDesde);
			antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		}
		//    	Configuramos la fecha hasta
		if (fechaHasta != null) {
			Calendar despues = Calendar.getInstance();
			despues.setTime(fechaHasta);
			despuesS = "" + despues.get(Calendar.YEAR) + formatMonthMM((despues.get(Calendar.MONTH) + 1))
			+ formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		}

		//aniadimos la query con el rango de fechas
		andQuery.add(getConstantScoreRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);

		//Buscamos sobre todos los subindices la query formada anteriormente
		Hits hits;
		ResultadoHeaderVO[]  resultado = new ResultadoHeaderVO[0];
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
			if (hits != null && hits.length() > 0)
			{
				try {
					resultado = this.mapDocToHeaderStartEnd(hits,((paramBusq.getRegistroInicial()).intValue()),((paramBusq.getRegistroFinal()).intValue()));
				} catch (RuntimeException e) {
					logger.error("Error obteniendo las cabeceras del repositorio. Error mapeando resultados para el indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> - " + e);
					throw new Exception("Error obteniendo las cabeceras del repositorio. Error mapeando resultados para el indice con query["+andQuery.toString()+"].",e);
				}
			}

		} catch (Exception e) {
			logger.error("Error obteniendo las cabeceras del repositorio. Error buscando en el indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> - "+e);
			throw new Exception("Error obteniendo las cabeceras del repositorio. Error buscando en el indice con query["+andQuery.toString()+"].",e);
		}    		

		return resultado;
	}

	/**
	 * Este metodo busca el identificador en todo el repositorio del indexador.
	 * @param idMEC Identificador del ODE.
	 * @return DocVO30 VO con la informacion indexada de un ODE.
	 */

	protected es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO handleBusquedaMECRepositorio(String idMEC) throws Exception {

		//		Comprobamos el MEC
		if (idMEC == null || idMEC.equals(""))
		{
			logger.warn("Error buscando ODE por MEC en el repositorio. Identificador MEC vacio <"+idMEC+">");
			throw new Exception("Error buscando ODE por MEC en el repositorio. Identificador MEC vacio <"+idMEC+">");
		}

		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
		//    	Buscamos sobre todos los subindices para encontrar el ODE, para ella 
		//   	metemos null en el idiomaBusqueda
		Hits hits = this.internaBusquedaMEC(idMEC, query,null);
		ResultadoRecordVO resultado = null;	
		if (hits  != null && hits.length() > 0) {
			//  		Hay hit, mapeamos los resultados
			resultado = mapDocToRecord(hits)[0];	
		}

		return resultado;
	}

	/**
	 * Metodo que ejecuta una busqueda en todo el repositorio con los parametros de busqueda que le pasan.
	 * Las fechas desde y hasta se interpretan inclusives y en el caso de no estar presentes, sin limite superior o inferior.
	 * @param paramBusq Parametros de busqueda.
	 * @return ResultadoRecordVO[] Array de VO's que albergan los resulados de la consulta en un contenedor de Records.
	 */
	protected es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] handleBusquedaRepositorio(ParamPeriodoRepositorioVO paramBusq) throws Exception {
		if (paramBusq == null)
		{
			logger.warn("Error obteniendo los registros del repositorio. No hay parametros de busqueda.");
			throw new Exception("Error obteniendo los registros del repositorio. No hay parametros de busqueda.");
		}
		Date fechaDesde = (paramBusq.getDesde()!=null?paramBusq.getDesde().getTime():null);
		Date fechaHasta = (paramBusq.getHasta()!=null?paramBusq.getHasta().getTime():null);
		if (fechaDesde != null && fechaHasta != null && fechaDesde.after(fechaHasta))
		{
			logger.warn("Error obteniendo los registros del repositorio. Fechas desde <"+fechaDesde.toString()+"> hasta <"+fechaHasta.toString()+"> incoherentes.");
			throw new Exception("Error obteniendo los registros del repositorio. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		//    	Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(Integer.parseInt(props.getProperty("maxClauseCount")));
		//    	Configuramos la fecha desde
		String antesS = null;
		String despuesS = null;
		if (fechaDesde != null){
			Calendar antes = Calendar.getInstance();
			antes.setTime(fechaDesde);
			antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		}
		//    	Configuramos la fecha hasta
		if (fechaHasta != null) {
			Calendar despues = Calendar.getInstance();
			despues.setTime(fechaHasta);
			despuesS = "" + despues.get(Calendar.YEAR) + formatMonthMM((despues.get(Calendar.MONTH) + 1))
			+ formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		}

		//aniadimos la query con el rango de fechas
		andQuery.add(getConstantScoreRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);

		//Buscamos sobre todos los subindices la query formada anteriormente
		Hits hits;
		ResultadoRecordVO[] resultado = new ResultadoRecordVO[0];
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
			if (hits != null && hits.length() > 0)
			{
				logger.debug("hits: <"+hits.length() + ">");
				try {
					resultado = mapDocToRecordStartEnd(hits,((paramBusq.getRegistroInicial()).intValue()),((paramBusq.getRegistroFinal()).intValue()));
				} catch (RuntimeException e) {
					logger.error("Error obteniendo los registros del repositorio. Error mapeando resultados para el indice con query <"+andQuery.toString()+"> - <"+e.getCause()+"> - "+e);
					throw new Exception("Error obteniendo los registros del repositorio. Error mapeando resultados para el indice con query["+andQuery.toString()+"].",e);
				}
			}

		} catch (Exception e) {
			logger.error("Error obteniendo los registros del repositorio. Error buscando en el indice con query <"+andQuery.toString()+"> <"+e.getCause()+">" + e);
			throw new Exception("Error obteniendo los registros del repositorio. Error buscando en el indice con query["+andQuery.toString()+"].",e);
		}


		return resultado;
	}

	/* (non-Javadoc)
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceBase#handleFechaInicioRepositorio()
	 * @return Calendar Con la fecha de inicio.
	 * @throws Exception
	 */
	protected Calendar handleFechaInicioRepositorio() throws Exception {
		//		Hashtable fechas= new Hashtable();
		Hashtable fechas;

		String campoFechaIndice = campo_fechaPublicacion;

		//		Obtenemos todas las fechas de publicacion diferentes del repositorio
		fechas = this.getGestorCompass().obtenerFechasRepositorio(campoFechaIndice);

		//		Si hay fechas en el indice, las ordenamos
		String[] arrayFechas = null;
		if (!fechas.keySet().isEmpty())
		{
			arrayFechas = (String[])fechas.keySet().toArray(new String[0]);
			//			ordenamos las fechas de menor a mayor
			//TODO Revisar
			//			Arrays.sort(arrayFechas, new Comparator<String>() {
			//				@Override
			//				public int compare(String uno, String otro) {
			//					return -1*uno.compareTo(otro);
			//				}
			//			});
			Arrays.sort(arrayFechas);
			//			bubble(arrayFechas);
		}
		else
			logger.warn("Detectando fecha de inicio del repositorio. No hay fechas en el indice.");

		//		Si no hay fechas en todo el indice, no puedo dar una fecha!!
		if (arrayFechas == null || arrayFechas.length==0 || arrayFechas[0] == null)		
		{
			logger.warn("Error detectando fecha de inicio del repositorio. No hay fechas en el indices revisado.");
			throw new Exception("Error detectando fecha de inicio del repositorio. No hay fechas en el indices revisado.");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return DateManager.dateToCalendar(sdf.parse(arrayFechas[0]));
	}

	/**
	 * Este metodo busca un ODE al azar de dentro del repositorio.
	 * @return DocVO30 Detalle de un ODE indexado.
	 */
	protected DocVO30 handleObtenerODERandom() throws Exception {
		Random random = new Random(Calendar.getInstance().getTimeInMillis());
		Document doc=null;
		int numeroDocumentos = this.getGestorCompass().numDocs(null);
		logger.debug("El numero de documentos del indice es <"+numeroDocumentos + ">");
		//		Seleccionamos el documento que vamos a extraer
		if(numeroDocumentos>0){
			int intRandom = random.nextInt();
			int documentoSeleccionado = (intRandom<0?(intRandom*(-1)):intRandom)%numeroDocumentos;
			logger.info("Devuelto documento <"+documentoSeleccionado+"> de <"+numeroDocumentos+"> documentos totales indexados.");
			doc = this.getGestorCompass().obtenerDocumento(documentoSeleccionado);
		}
		else 
			logger.info("No hay documentos en el indice");

		if(doc!=null)
			return getVOFromLucene(doc, new DocVO30(), 0);
		return null;
	}

	/**
	 * Este metodo devuelve todos los ODEs indexados en el repositorio para todos los idiomas que cumplan los parametros de búsqueda.
	 * @param paramPeriodoRepositorio Parámetros de búsqueda
	 * @return ResultadoRepositorioVO[] Array de VO's que albergan los resulados del repositorio.
	 */
	protected ResultadoRepositorioVO[] handleObtenerRepositorio(ParamPeriodoRepositorioVO paramPeriodoRepositorio) throws Exception {

		Date fechaDesde = handleFechaInicioRepositorio().getTime();
		Date fechaHasta = Calendar.getInstance().getTime(); // la fecha de hoy como hasta

		//    	Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		//    	Configuramos la fecha desde
		String antesS = null;
		String despuesS = null;
		if (fechaDesde != null){
			Calendar antes = Calendar.getInstance();
			antes.setTime(fechaDesde);
			antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		}
		//    	Configuramos la fecha hasta
		if (fechaHasta != null) {
			Calendar despues = Calendar.getInstance();
			despues.setTime(fechaHasta);
			despuesS = "" + despues.get(Calendar.YEAR) + formatMonthMM((despues.get(Calendar.MONTH) + 1))
			+ formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		}
		//aniadimos la query con el rango de fechas
		andQuery.add(getConstantScoreRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);

		//Buscamos sobre todos los subindices la query formada anteriormente
		Hits hits = null;
		ResultadoRepositorioVO[] resultado = new ResultadoRepositorioVO[0];
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
			if (hits != null && hits.length() > 0)
			{
				try {
					resultado = mapDocToRepositorioStartEnd(hits, (paramPeriodoRepositorio.getRegistroInicial()).intValue(),(paramPeriodoRepositorio.getRegistroFinal()).intValue());
					logger.debug("Recibimos <"+resultado.length+"> documentos");
				} catch (RuntimeException e) {
					logger.error("Error recuperando repositorio. Error mapeando resultados para el indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> + "+e);
					throw new Exception("Error recuperando repositorio. Error mapeando resultados para el indice con query["+andQuery.toString()+"].",e);
				}
			}
			else
			{
				logger.info("No hay informacion de repositorio disponible para fechas <"+antesS+">-<"+despuesS+"> query <"+andQuery.toString()+">");
			}

		} catch (Exception e) {
			logger.error("Error recuperando repositorio. Error buscando en el indice con query <"+andQuery.toString()+"> <"+e.getCause()+">" + e);
		}

		return resultado;
	}

	/**
	 * Este metodo devuelve el número total de ODEs indexados en el repositorio para todos los idiomas.
	 * @return ResultadosCountVO VO que contiene el total de odes.
	 */
	protected ResultadosCountVO handleObtenerTotalesRepositorio() throws Exception {
		if((totalesRepositorio==null) || ((System.currentTimeMillis() - startTotales) >= tiempoRefresco)){

			Integer[] conteo = new Integer[MAX_NIVEL_AGREGACION];
			DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
			Hits hits = null;
			int totalNodo=0;
			es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq = new es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO();
			String[] idiomasBuscables = I18n.getInstance().obtenerIdiomasBuscables();
			for(int i = 0; i < idiomasBuscables.length; i++){
				try{
					paramBusq.setIdiomaBusqueda(idiomasBuscables[i]);
					paramBusq.setIdiomaNavegacion(idiomasBuscables[i]);
					for(int j = 1; MAX_NIVEL_AGREGACION >= j ; j++){
						//						paramBusq.setNivelAgregacion(String.valueOf(j));
						paramBusq.setNivelAgregacion(new String[]{String.valueOf(j)});
						hits = internaBusquedaAvanzada(paramBusq, query, "");
						int numNivel=hits!=null?hits.length():0;
						totalNodo+=numNivel;
						if(conteo[j-1]==null)conteo[j-1]=numNivel;
						else conteo[j-1]+=numNivel;
					}

				}catch(java.lang.Exception e){
					logger.error("SrvBuscarServiceImpl - handleObtenerTotalesRepositorio: No existe índice para el idioma = <"+idiomasBuscables[i]+">",e);
				}
			}
			int documentosCount = 0;
			for(int i = 0; i < conteo.length; i++){
				if(conteo[i]!=null)documentosCount = documentosCount + conteo[i];
			}
			totalesRepositorio = new ResultadosCountVO();
			totalesRepositorio.setConteo(conteo);
			totalesRepositorio.setDocumentosCount(totalNodo);
			startTotales = System.currentTimeMillis();
		}
		return totalesRepositorio;
	}

	/**
	 * Este metodo devuelve el número total de ODEs indexados en el repositorio para todos los idiomas que cumplan los parámetros de búsqueda.
	 * @param paramPeriodoRepositorio Parametros de búsqueda.
	 * @return ResultadosCountVO VO que contiene el total de odes.
	 */
	protected ResultadosCountVO handleObtenerTotalesRepositorioFechas(ParamPeriodoRepositorioVO paramPeriodoRepositorio) throws Exception {

		ResultadosCountVO resultado = new ResultadosCountVO();

		if (paramPeriodoRepositorio == null)
		{
			logger.warn("Error obteniendo los registros del repositorio. No hay parametros de busqueda.");
			throw new Exception("Error obteniendo el numero de registros del repositorio. No hay parametros de busqueda.");
		}
		Date fechaDesde = (paramPeriodoRepositorio.getDesde()!=null?paramPeriodoRepositorio.getDesde().getTime():null);
		Date fechaHasta = (paramPeriodoRepositorio.getHasta()!=null?paramPeriodoRepositorio.getHasta().getTime():null);
		if (fechaDesde != null && fechaHasta != null && fechaDesde.after(fechaHasta))
		{
			logger.warn("Error obteniendo los registros del repositorio. Fechas desde <"+fechaDesde.toString()+"> hasta <"+fechaHasta.toString()+"> incoherentes.");
			throw new Exception("Error obteniendo los registros del repositorio. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}

		//    	Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(Integer.parseInt(props.getProperty("maxClauseCount")));
		//    	Configuramos la fecha desde
		String antesS = null;
		String despuesS = null;
		if (fechaDesde != null){
			Calendar antes = Calendar.getInstance();
			antes.setTime(fechaDesde);
			antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		}
		//    	Configuramos la fecha hasta
		if (fechaHasta != null) {
			Calendar despues = Calendar.getInstance();
			despues.setTime(fechaHasta);
			despuesS = "" + despues.get(Calendar.YEAR) + formatMonthMM((despues.get(Calendar.MONTH) + 1))
			+ formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		}
		//aniadimos la query con el rango de fechas

		//		Nos recorremos todos los indices realizando la misma consulta y recopilando la informacion que saquemos de cada indice

		andQuery.add(getConstantScoreRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);

		//		Nos recorremos todos los indices realizando la misma consulta y recopilando la informacion que saquemos de cada indice
		String[] idiomasBuscables = I18n.getInstance().obtenerIdiomasBuscables();
		Integer[] conteo = new Integer[idiomasBuscables.length];
		for(int i = 0; i < idiomasBuscables.length; i++){

			Hits hits;

			try {
				hits = this.getGestorCompass().busqueda(andQuery, idiomasBuscables[i],null);
				if(!(hits == null))
				{
					conteo[i]=hits.length();
				}else
				{
					conteo[i]=0;
				}
			} catch (Exception e) {
				logger.error("Error obteniendo los registros del repositorio. Error buscando en el indice con query <"+andQuery.toString()+"> <"+e.getCause()+">" + e);
				conteo[i]=0;    			
			}
		}
		int documentosCount = 0;
		for(int i = 0; i < conteo.length; i++){
			if(conteo[i]!=null)documentosCount = documentosCount + conteo[i];
		}
		resultado.setConteo(conteo);
		resultado.setDocumentosCount(documentosCount);
		return resultado;

	}

	private Manifest parsearManifiesto(String localizador) throws IndexaODEException 
	{
		Manifest manifest = null;

		try 
		{
			manifest = this.getScormDao().parsearODELazy(new File(localizador));
		} 
		catch (Exception e1) 
		{
			logger.error("No se puede parsear el fichero; localizador <"+localizador+">",e1);
			throw new IndexaODEException(e1);
		}
		return manifest;
	}

	/**
	 * Este método verifica que un archivo exista
	 * @param f archivo
	 * @return boolean, si existe treu, si no false
	 */
	public static boolean exists(File f) {
		if (f.isDirectory()) return true;
		//	      if (f.exists()) {
		//	    	  return true;
		//	      }
		//		return false;
		return (f.exists());
	}

	//	Obtiene una query a partir del campo, valor de campo o palabra y tipo de configuración wildcard (simple/avanzada)
	private Query getQuery(Object palabra, String clave, String tipoBusqueda) {
		String[] camposAccesibles = props.getProperty(tipoBusqueda).split(SEPARADOR_CLAVES);
		if((tipoBusqueda.equals(CAMPOS_PHRASE_AVANZADA) || tipoBusqueda.equals(CAMPOS_PHRASE_SIMPLE)) && getPhraseAccess(camposAccesibles, clave)) return getPhraseQuery(props.getProperty(clave), (List)palabra);
		return (getWildcardAccess(camposAccesibles, clave) && (this.contains(palabra.toString(), "*") || this.contains(palabra.toString(), "?")))? getWildcardQuery(props.getProperty(clave), (getLowerCaseAccess(props.getProperty(CAMPOS_LOWER_CASE).split(SEPARADOR_CLAVES),clave))?palabra.toString().trim().toLowerCase():palabra.toString().trim()):getTermQuery(props.getProperty(clave), (getLowerCaseAccess(props.getProperty(CAMPOS_LOWER_CASE).split(SEPARADOR_CLAVES),clave))?palabra.toString().trim().toLowerCase():palabra.toString().trim());
	}
	
	//	Concede o no acceso de tipo wildcard al indice en función de configuracion (simple/avanzada)
	private boolean getWildcardAccess(String[] camposWildcard, String campoAcceder) {
		for(int i = 0 ; i < camposWildcard.length ; i++) if(camposWildcard[i].equals(campoAcceder)) return true;
		return false;
	}
	
	//  Comprueba si un campo del indice debe de ser añadido a la query como and u or, en función de configuración
	private boolean getAndOrAccess(String campoAcceder, boolean tipoAnd) {
		String[] campos = (tipoAnd)?camposAND.split(SEPARADOR_CLAVES):camposOR.split(SEPARADOR_CLAVES);
		for(int i = 0 ; i < campos.length ; i++) if(campos[i].equals(PREFIJO_CAMPO+campoAcceder)) return true;
		return false;
	}
	
	//	Concede o no acceso de tipo wildcard al indice en función de configuracion (simple/avanzada)
	private boolean getPhraseAccess(String[] camposPhrase, String campoAcceder) {
		for(int i = 0 ; i < camposPhrase.length ; i++) if(camposPhrase[i].equals(campoAcceder)) return true;
		return false;
	}

	//	Concede o no acceso de tipo wildcard al indice en función de configuracion (simple/avanzada)
	private boolean getLowerCaseAccess(String[] camposLowerCase, String campoAcceder) {
		for(int i = 0 ; i < camposLowerCase.length ; i++) if(camposLowerCase[i].equals(campoAcceder)) return true;
		return false;
	}

	//	Concede o no acceso de tipo wildcard al indice en función de configuracion (simple/avanzada)
	private boolean getRangeQueryAccess(String[] camposRange, String campoAcceder) {
		for(int i = 0 ; i < camposRange.length ; i++) if(camposRange[i].equals(campoAcceder)) return true;
		return false;
	}
	
	private boolean esTextoLibre(String[] camposTextoLibre, String campoAcceder) {
		for(int i = 0 ; i < camposTextoLibre.length ; i++) if(camposTextoLibre[i].equals(campoAcceder)) return true;
		return false;
	}
	
	//	Obtiene query simple	
	private Query getTermQuery(String f, String t) {
		return new TermQuery(new Term(f, t));
	}
	
	//	Obtiene phrase query	
	private Query getPhraseQuery(String f, List frase) {
		PhraseQuery phraseQuery = new PhraseQuery();
		for(int i = 0 ; i < frase.size(); i++)phraseQuery.add(new Term(f, frase.get(i).toString().trim().toLowerCase()));
		phraseQuery.setSlop(distanciaEntreCampos);
		return phraseQuery;
	}
	
	//	Obtiene range query. Se supone que el rango inferior y superior afectan al mismo campo (f)
	private Query getRangeQuery(String f, String rangoInf, String rangoSup)
	{
		RangeQuery rangeQuery = new RangeQuery(new Term(f,rangoInf),
				new Term(f,rangoSup),
				true);//queremos que sea inclusivo
		return rangeQuery;
	}
	
	//	Obtiene wildcard query
	private Query getWildcardQuery(String f, String t) {
		return new WildcardQuery(new Term(f, t));
	}

	//	Obtiene range query. Se supone que el rango inferior y superior afectan al mismo campo (f)
	private Query getConstantScoreRangeQuery(String f, String rangoInf, String rangoSup)
	{
		ConstantScoreRangeQuery rangeQuery = new ConstantScoreRangeQuery(f,
				rangoInf,
				rangoSup,
				true, //somos inclusivos con el rango inferior
				true); //somos inclusivos con el rango superior
		return rangeQuery;
	}

	private List<Object> devolverFrases(String palabrasClave, String idioma) {
		List<Object> retorno = new ArrayList<Object>();
		String[] stopWords = new String[0];
		try{
			stopWords = props.getProperty("stopWords." + idioma).trim().split(",");
		}catch(Exception ex){
			logger.error("ERROR: No existe StopWords para idioma= <" + idioma + ">", ex);
		}
		while(contains(palabrasClave,SEPARADOR_PHRASE) && contains(palabrasClave.substring(palabrasClave.indexOf(SEPARADOR_PHRASE)+1), SEPARADOR_PHRASE)){
			List<String> palabraPhrase = new ArrayList<String>();
			int indiceFinalFrase = palabrasClave.substring(palabrasClave.indexOf(SEPARADOR_PHRASE)+1).indexOf(SEPARADOR_PHRASE)+palabrasClave.indexOf(SEPARADOR_PHRASE)+2;
			String[] words = palabrasClave.substring(palabrasClave.indexOf(SEPARADOR_PHRASE)+1,indiceFinalFrase-1).trim().split(" ");
			for (int i = 0; i < words.length; i++) {
				if(!words[i].toString().trim().equals("") && !stopWord(words[i].trim(), stopWords)) palabraPhrase.add(words[i].trim());
			}
			if(palabraPhrase.size() > 1) {
				palabrasClave = palabrasClave.substring(0 , palabrasClave.indexOf(SEPARADOR_PHRASE)).trim() + " " + palabrasClave.substring(indiceFinalFrase).trim();
				retorno.add(palabraPhrase);
			}else palabrasClave = palabrasClave.substring(0 , palabrasClave.indexOf(SEPARADOR_PHRASE)).trim() + " " + words[0] + " " + palabrasClave.substring(indiceFinalFrase).trim();
		}
		Object[] palabras = (palabrasClave!=null && !palabrasClave.trim().equals(""))?palabrasClave.trim().replaceAll(SEPARADOR_PHRASE, " ").split(" "):new Object[0];
		for (int i = 0; i < palabras.length; i++) if(!palabras[i].toString().trim().equals("")) retorno.add(palabras[i].toString());
		return retorno;
	}

	private List<Object> devolverAnds(List<Object> palabrasClave) {
		List<Object> retorno = new ArrayList(), tempAnds = new ArrayList<Object>(), tempWords = new ArrayList<Object>();
		while(palabrasClave.size()>0){
			if(palabrasClave.size()>1 && palabrasClave.get(1).equals("+")){
				List<Object> tempAnd = new ArrayList<Object>();
				if(tempAnd==null || tempAnd.size()==0){
					tempAnd.add(palabrasClave.get(0));
					tempAnd.add(palabrasClave.get(2));
					for(int j = 0; j < 3; j++)palabrasClave.remove(0);
				}
				while(palabrasClave.size()>0 && palabrasClave.get(0).equals("+")){
					tempAnd.add(palabrasClave.get(1));
					for(int j = 0; j < 2; j++)palabrasClave.remove(0);
				}
				if(tempAnd!=null && tempAnd.size()>0) tempAnds.add(tempAnd);
			}else if(palabrasClave.size()==1 || !palabrasClave.get(1).equals("+")){
				tempWords.add(palabrasClave.get(0));
				palabrasClave.remove(0);
			}
		}
		retorno.add(tempAnds);
		retorno.add(tempWords);
		return retorno;
	}

	//	Concede o no acceso de tipo wildcard al indice en función de configuracion (simple/avanzada)
	private boolean stopWord(String campo, String[] stopWords) {
		for(int i = 0 ; i < stopWords.length ; i++) if(stopWords[i].equals(campo)) return true;
		return false;
	}	

	private Hits internaBusquedaAvanzada(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq,
			DisjunctionMaxQuery query, String idNodo)  throws Exception
			//	private Hits internaBusquedaAvanzada(IndexSearcher searcher, es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq,
			//			DisjunctionMaxQuery query)  throws Exception
			{
		Hits hits = null;
		//		Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
		try{			
			//		Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
			BooleanQuery andQuery = new BooleanQuery();
			//		Las palabras clave (texto libre que se pone en la cabecera de la pantalla de busqueda) sirve para buscar
			//		sobre los campos titulo, descripcion, keywords y arbol curricular
			//		Configuramos el valor de memoria.
			BooleanQuery.setMaxClauseCount(maxClauseCount);
			//		Separamos las posibles frases de las palabras sueltas

			if(paramBusq.getPalabrasClave() != null && !paramBusq.getPalabrasClave().trim().equals("")){
				// 11012013 Sustituimos el - por espacio en blanco porque es la forma de encontrar objetos en lucene que contengan -	
				String palabrasClave = paramBusq.getPalabrasClave().trim().replaceAll("-", " ");
				// Sustituimos el 's y el ´s de ingles por espacio en blanco porque es la forma de encontrar objetos en lucene que contengan apostrofe s
				palabrasClave = palabrasClave.replaceAll("'s", " ");
				palabrasClave = palabrasClave.replaceAll("´s", " ");
				List<Object> palabras = devolverFrases(palabrasClave, paramBusq.getIdiomaBusqueda());
				List<Object> ands = devolverAnds(palabras);
				String[] claves = props.getProperty(CAMPOS_CLAVE_AVANZADA).split(SEPARADOR_CLAVES);
				if(ands!=null){
					//					BooleanQuery andQueryClavePrincipal = new BooleanQuery();
					//					andQueryClavePrincipal.setMaxClauseCount(maxClauseCount);
					List andsList = (List) ands.get(0);
					List orList = (List) ands.get(1);
					BooleanQuery andQueryClave = new BooleanQuery();
					BooleanQuery.setMaxClauseCount(maxClauseCount);
					for(int k = 0; k < claves.length; k++) {
						for (int j = 0; j < andsList.size(); j++){
							BooleanQuery andQueryClaveInterna = new BooleanQuery();
							BooleanQuery.setMaxClauseCount(maxClauseCount);
							for (int i = 0; i < ((List)andsList.get(j)).size(); i++){
								andQueryClaveInterna.add((List.class.isInstance(((List)andsList.get(j)).get(i)))?getQuery(((List)andsList.get(j)).get(i),claves[k],CAMPOS_PHRASE_AVANZADA):getQuery(((List)andsList.get(j)).get(i).toString(),claves[k],CAMPOS_WILDCARD_AVANZADA),BooleanClause.Occur.MUST);
							}
							andQueryClave.add(andQueryClaveInterna,BooleanClause.Occur.MUST);
						}
					}
					for (int j = 0; j < orList.size(); j++){
						BooleanQuery andQueryClaveInterna = new BooleanQuery();
						BooleanQuery.setMaxClauseCount(maxClauseCount);
						for(int k = 0; k < claves.length; k++) {
							andQueryClaveInterna.add((List.class.isInstance(orList.get(j)))?getQuery(orList.get(j),claves[k],CAMPOS_PHRASE_AVANZADA):getQuery(orList.get(j).toString(),claves[k],CAMPOS_WILDCARD_AVANZADA),BooleanClause.Occur.SHOULD);
						}
						andQueryClave.add(andQueryClaveInterna,BooleanClause.Occur.MUST);
					}
					andQuery.add(andQueryClave,BooleanClause.Occur.MUST);

					//Query no se vuelve a usar hasta handleBusquedaAvanzado cuando quiere obtener sugerencias
					//Esto es logico ya que la variable query lleva consigo el operador fuzzy ~ de Lucene
					query.add(andQuery);
				}
			}		

			//		Ahora recorremos todos los parametros de la busqueda avanzada, introduciendo los valores de busqueda
			//		en la query de aquellos campos que esten presentes en los parametros de la consulta.
			PropertyDescriptor[] beanPDs = Introspector.getBeanInfo(paramBusq.getClass()).getPropertyDescriptors();
			for (int j = 0; j < beanPDs.length; j++) {
				if(props.getProperty(PREFIJO_CAMPO+beanPDs[j].getName())!=null && (getAndOrAccess(beanPDs[j].getName(),true) || getAndOrAccess(beanPDs[j].getName(),false))
						&& !props.getProperty(PREFIJO_CAMPO+beanPDs[j].getName()).equals(props.getProperty("campo_idiomaBusqueda"))){
					if(beanPDs[j].getReadMethod().invoke(paramBusq,new Object[0])!=null && !beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]).toString().trim().equals("")){
						//					Comprobar analyse para no tener un tratamiento especial para formato

						List<Object> palabras = new ArrayList<Object>();
						if(getRangeQueryAccess(props.getProperty(CAMPOS_RANGE_AVANZADA).split(SEPARADOR_CLAVES),PREFIJO_CAMPO+beanPDs[j].getName()) || esTextoLibre(props.getProperty(CAMPOS_TEXTO_LIBRE).split(SEPARADOR_CLAVES),PREFIJO_CAMPO+beanPDs[j].getName())) palabras = devolverFrases(beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]).toString().trim(), paramBusq.getIdiomaBusqueda());
						else if(campo_taxonomia.equals(beanPDs[j].getName())) {
							palabras = addTaxonomias((String[]) beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]),palabras,paramBusq.getBusquedaSimpleAvanzada());
						} else if(beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]).getClass().getSimpleName().equals("String[]")) {
							palabras = addFormatosNivelesAgregacion((String[]) beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]), palabras);
						} else { 
							palabras.add(beanPDs[j].getReadMethod().invoke(paramBusq, new Object[0]).toString().trim());
						}

						if(palabras.size()>0){
							//Añadimos las palabras a la query
							if(getRangeQueryAccess(props.getProperty(CAMPOS_RANGE_AVANZADA).split(SEPARADOR_CLAVES),PREFIJO_CAMPO+beanPDs[j].getName())) andQuery.add(getRangeQuery(props.getProperty(PREFIJO_CAMPO+beanPDs[j].getName()), palabras.get(0).toString(), palabras.get(1).toString()), BooleanClause.Occur.MUST);
							else{
								List<Object> ands = devolverAnds(palabras);//devuelve las palabras separadas por "+" en la primera posición de la lista ands (ands[0]=andsList) ,
								//si las palabras no están separadas por "+" las devuelve en la segunda posición de la lista ands (ands[1]=orList)
								List andsList = (List) ands.get(0);//palabras con "and"
								List orList = (List) ands.get(1);//palabras con "or"
								BooleanQuery andQueryClave = new BooleanQuery();
								BooleanQuery.setMaxClauseCount(maxClauseCount);
								for (int k = 0; k < andsList.size(); k++){
									BooleanQuery andQueryClaveInterna = new BooleanQuery();
									BooleanQuery.setMaxClauseCount(maxClauseCount);
									for (int i = 0; i < ((List)andsList.get(k)).size(); i++){
										andQueryClaveInterna.add((List.class.isInstance(((List)andsList.get(k)).get(i)))?getQuery(((List)andsList.get(k)).get(i),PREFIJO_CAMPO+beanPDs[j].getName(),CAMPOS_PHRASE_AVANZADA):getQuery(((List)andsList.get(k)).get(i).toString(),PREFIJO_CAMPO+beanPDs[j].getName(),CAMPOS_WILDCARD_AVANZADA),BooleanClause.Occur.MUST);
									}
									andQueryClave.add(andQueryClaveInterna,BooleanClause.Occur.SHOULD);
								}
								for (int k = 0; k < orList.size(); k++){
									andQueryClave.add((List.class.isInstance(orList.get(k)))?getQuery(orList.get(k),PREFIJO_CAMPO+beanPDs[j].getName(),CAMPOS_PHRASE_AVANZADA):getQuery(orList.get(k).toString(),PREFIJO_CAMPO+beanPDs[j].getName(),CAMPOS_WILDCARD_AVANZADA),BooleanClause.Occur.SHOULD);
								}
								if(getAndOrAccess(beanPDs[j].getName(),true)) andQuery.add(andQueryClave, BooleanClause.Occur.MUST);
								else if(getAndOrAccess(beanPDs[j].getName(),false)) andQuery.add(andQueryClave, BooleanClause.Occur.SHOULD);
							}
						}
					}
				}
			}

			// 07062012 ejfente
			// Se incluye la búsqueda por licencia
			if (logger.isDebugEnabled())
				logger.debug("Comprobando si filtra por licencia " + paramBusq.getLicencias());
			if(paramBusq.getLicencias() != null && !paramBusq.getLicencias().trim().equals("")){
				if (logger.isDebugEnabled())
					logger.debug("Incluyendo licencia en parámetros de búsqueda :" + paramBusq.getLicencias());
				List<Object> palabras = new ArrayList<Object>();				
				
				palabras.add(paramBusq.getLicencias().replace(" ", "?"));
				
				List<Object> ands = devolverAnds(palabras);
				String[] claves = props.getProperty(CAMPOS_LICENCIAS).split(SEPARADOR_CLAVES);
				if(ands!=null){
					List andsList = (List) ands.get(0);
					List orList = (List) ands.get(1);
					BooleanQuery andQueryClave = new BooleanQuery();
					BooleanQuery.setMaxClauseCount(maxClauseCount);
					for(int k = 0; k < claves.length; k++) {
						for (int j = 0; j < andsList.size(); j++){
							BooleanQuery andQueryClaveInterna = new BooleanQuery();
							BooleanQuery.setMaxClauseCount(maxClauseCount);
							for (int i = 0; i < ((List)andsList.get(j)).size(); i++){
								andQueryClaveInterna.add((List.class.isInstance(((List)andsList.get(j)).get(i)))?getQuery(((List)andsList.get(j)).get(i),claves[k],CAMPOS_PHRASE_LICENCIAS):getQuery(((List)andsList.get(j)).get(i).toString(),claves[k],CAMPOS_WILDCARD_LICENCIAS),BooleanClause.Occur.MUST);
							}
							andQueryClave.add(andQueryClaveInterna,BooleanClause.Occur.MUST);
						}
					}
					for (int j = 0; j < orList.size(); j++){
						BooleanQuery andQueryClaveInterna = new BooleanQuery();
						BooleanQuery.setMaxClauseCount(maxClauseCount);
						for(int k = 0; k < claves.length; k++) {
							andQueryClaveInterna.add((List.class.isInstance(orList.get(j)))?getQuery(orList.get(j),claves[k],CAMPOS_PHRASE_LICENCIAS):getQuery(orList.get(j).toString(),claves[k],CAMPOS_WILDCARD_LICENCIAS),BooleanClause.Occur.SHOULD);
						}
						andQueryClave.add(andQueryClaveInterna,BooleanClause.Occur.MUST);
					}
					andQuery.add(andQueryClave,BooleanClause.Occur.MUST);
					
					//Query no se vuelve a usar hasta handleBusquedaAvanzado cuando quiere obtener sugerencias
					//Esto es logico ya que la variable query lleva consigo el operador fuzzy ~ de Lucene
					query.add(andQuery);
				}
			}	
			
			// FORMA SIMPLE...VERIFICAR SI FUNCIONA
			// Se incluye la búsqueda por "licencias"
//			if(paramBusq.getLicencias() != null && !paramBusq.getLicencias().trim().equals("")){
//				BooleanQuery andQueryClave = new BooleanQuery();
//				BooleanQuery andQueryClaveInterna = new BooleanQuery();
//				andQueryClaveInterna.add(getQuery(paramBusq.getLicencias(),PREFIJO_CAMPO + "licencias",CAMPOS_WILDCARD_AVANZADA),BooleanClause.Occur.SHOULD);
//				andQueryClave.add(andQueryClaveInterna, BooleanClause.Occur.SHOULD);
//				andQuery.add(andQueryClave, BooleanClause.Occur.MUST);
//			}
			String campoOrdenacion =campo_nivelAgregacion;
			if(paramBusq.getBusquedaSimpleAvanzada()!=null && paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRSS))
				campoOrdenacion= campo_fechaPublicacion;
			else if(paramBusq.getBusquedaSimpleAvanzada()!=null && paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRELACIONADOSAC))
				campoOrdenacion= campo_valoracion;

			if (logger.isDebugEnabled())
				logger.debug("Cadena de búsqueda : " + andQuery);
			if (idNodo.equals("")) {
				hits = this.getGestorCompass().busqueda(andQuery,
						paramBusq.getIdiomaBusqueda(), campoOrdenacion);
			} else {
				IndexSearcher searcher=null;
				try {
					searcher= new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+idNodo+"/index/idioma_"+paramBusq.getIdiomaBusqueda()); //Ruta del índice
				} catch (Exception e) {
					logger.error("No se encuentra el índice del nodo <"+idNodo+"> con idioma <"+paramBusq.getIdiomaBusqueda()+">",e);
					if(searcher!=null)searcher.close();
					return hits;
				}
				hits=searcher.search(andQuery,(paramBusq.getBusquedaSimpleAvanzada()!=null && paramBusq.getBusquedaSimpleAvanzada().equals(BUSCARRSS))?new Sort(new SortField(campo_fechaPublicacion,SortField.STRING,true)):new Sort(new SortField(campo_nivelAgregacion,SortField.STRING,true)));
			}

		}catch(java.lang.Exception e){
			Exception exc = new Exception("internaBusquedaAvanzada ERROR: Con query= <"+paramBusq.getPalabrasClave()+"> e idioma <"+paramBusq.getIdiomaBusqueda()+">", e);
			logger.error(exc);
			throw exc;
		}
		return hits;
			}

	private Object[] internaBusquedaQuery (es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq,String palabrasClave, DisjunctionMaxQuery query, boolean buscarTodosIndices) throws Exception{
		Object hits[] = null;
		if(buscarTodosIndices){
			String[] idiomasBuscables = I18n.getInstance().obtenerIdiomasBuscables();
			hits = new Object[idiomasBuscables.length];
			Query queryPC = prepararBusqueda (palabrasClave, query);
			for(int i = 0; i < idiomasBuscables.length; i++){
				try{
					Hits hit = this.getGestorCompass().busqueda(queryPC,idiomasBuscables[i],null);
					hits[i] = hit;
				}catch(java.lang.Exception e){
					logger.error("ERROR: Indice para el idioma = <"+ idiomasBuscables[i]+">",e);

				}
			}
		}else{
			try{
				hits = new Object[1];
				hits[0] = this.getGestorCompass().busqueda(prepararBusqueda (palabrasClave, query), paramBusq.getIdiomaBusqueda(), null);
				logger.debug("Numero de resultado <" +  (hits[0]!=null?((Hits)hits[0]).length():0) +"> para la query <" +query+">");
			} catch (java.lang.Exception e) {
				Exception exc = new Exception("ERROR: buscando con query= <"+ paramBusq.getPalabrasClave() + "> e idioma <" + paramBusq.getIdiomaBusqueda() + ">", e);
				logger.error(exc);
				throw exc;
			}
		}
		return hits;
	}

	private Query prepararBusqueda (String palabrasClave, DisjunctionMaxQuery query) throws Exception{
		String[] arrayCamposParametro=new String[] {campo_alcance,campo_ambito,campo_autor,campo_secuencia,
				props.getProperty("campo_contexto"),campo_contribucion,campo_contribuidor,campo_descripcion,
				campo_descripcion_objetivo,campo_destinatarios,campo_edad,campo_fechaPublicacion,
				campo_formato,campo_fuente,campo_horaPublicacion,campo_identificadorODE,campo_imagen,
				campo_palabrasClave,campo_licencias,campo_literalArbolC,campo_localizador,campo_nivelAgregacion,
				props.getProperty("campo_procesoCognitivo"),campo_publicador,campo_relacion,"campo_description_secundario",
				props.getProperty("campo_keywords_secundario"),props.getProperty("campo_title_secundario"),campo_tamanio,campo_taxonomia,
				campo_taxonomia_nodo,campo_taxonomia_path,campo_recurso,campo_titulo,campo_valoracion};

		org.apache.lucene.search.BooleanClause.Occur[] requeridos=  new org.apache.lucene.search.BooleanClause.Occur[NUMERO_CAMPOS];
		for(int i=0;i<NUMERO_CAMPOS;i++){
			requeridos[i]=org.apache.lucene.search.BooleanClause.Occur.SHOULD;
		}
		Query lqsQuery = MultiFieldQueryParser.parse(palabrasClave, arrayCamposParametro, requeridos, new KeywordAnalyzer());
		query.add(lqsQuery);
		return query;
	}

	private Hits internaBusquedaMEC(
			String mec,
			DisjunctionMaxQuery query,
			String idiomaBusqueda) throws Exception {
		Hits hits = null;
		try{
			query.add(getTermQuery(campo_identificadorODE, mec));

			hits = this.getGestorCompass().busqueda(query, idiomaBusqueda, null);

		}catch(Exception e){
			logger.error("internaBusquedaMEC error con mec: <"+mec+"> query: <"+query.toString()+">",e);
			throw new Exception("internaBusquedaMEC error con mec="+mec+" query="+query.toString());
		}
		return hits;
	}

	private String paramBusqAvanz2String(es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO paramBusq)
	{
		String tira = "";
		tira+= 
			" Taxonomia["+array2String(paramBusq.getTaxonomia())+"]"+
			" Autor["+paramBusq.getAutor()+"]"+
			" Comunidades["+paramBusq.getAmbito()+"]"+
			" Contexto["+paramBusq.getContexto()+"]"+
			" Descripcion["+paramBusq.getDescripcion()+"]"+
			" Edad["+paramBusq.getEdad()+"]"+
			" FechaPublicacion["+paramBusq.getFechaPublicacion()+"]"+
			" FechaPublicacionRango["+paramBusq.getFechaPublicacionRango()+"]"+
			" Formato["+Arrays.toString(paramBusq.getFormato())+"]"+
			" IdentificadorODE["+paramBusq.getIdentificadorODE()+"]"+
			" Idioma["+paramBusq.getIdiomaBusqueda()+"]"+
			" IdiomaNavegacion["+paramBusq.getIdiomaNavegacion()+"]"+
			" NivelAgregacion["+Arrays.toString(paramBusq.getNivelAgregacion())+"]"+
			" NumeroResultados["+paramBusq.getNumeroResultados()+"]"+
			" PalabrasClave["+paramBusq.getPalabrasClave()+"]"+
			" ProcesoCognitivo["+paramBusq.getProcesoCognitivo()+"]"+
			" Recurso["+paramBusq.getRecurso()+"]"+
			" Secuencia["+paramBusq.getSecuencia()+"]"+
			" Titulo["+paramBusq.getTitulo()+"]"+
			" Valoracion["+paramBusq.getValoracion()+"]";
		return tira;

	}

	private DocVO30[] getArrayDocsFromHits(Hits hits, int numeroResultados) throws Exception {		
		if (hits!= null)
		{ 			
			long start = System.currentTimeMillis();
			DocVO30[] resultados = new DocVO30[numeroResultados];
			for (int i = 0; i < numeroResultados; i++) {
				resultados[i] = this.getVOFromLucene(hits.doc(i),new DocVO30(), Float.floatToIntBits(hits.score(i)));
			}
			long end = System.currentTimeMillis();

			return resultados;
		}
		return null;
	}

	private DocVO30 getVOFromLucene(Document doc) throws Exception
	{
		DocVO30 hitToDoc = new DocVO30();
		try{
			hitToDoc.setDescripcion((doc.get(campo_descripcion)!=null)?doc.get(campo_descripcion):"");
			hitToDoc.setTitulo((doc.get(campo_titulo)!=null)?doc.get(campo_titulo):"");
			String sValueIdioma = doc.get(campo_idiomaBusqueda_compass);
			hitToDoc.setIdioma((sValueIdioma!=null)?sValueIdioma:"");
			String sValuesAmbito[] = doc.getValues(campo_ambito);
			hitToDoc.setAmbito((sValuesAmbito!=null)?sValuesAmbito:new String[0]);
			String sAutor[] = doc.getValues(campo_autor);
			hitToDoc.setAutor((sAutor!=null)?sAutor:new String[0]);

			String idArbolCurricularVig = getIdArbolCurricularVig();
			String sTaxonomias[] = doc.getValues(campo_taxonomia_path);
			if(sTaxonomias!=null && sTaxonomias.length>0){
				List<String> areasCurriculares = new ArrayList<String>();
				for(int i=0;i<sTaxonomias.length;i++){
					if(idArbolCurricularVig.equals(sTaxonomias[i].split("/")[0])){
						areasCurriculares.add(sTaxonomias[i].substring(sTaxonomias[i].indexOf("/")+1));
					}
				}
				hitToDoc.setAreaCurricular((areasCurriculares!=null && areasCurriculares.size()>0)?areasCurriculares.toArray(new String[0]):new String[0]);
			}
			//			hitToDoc.setCurso((doc.get(campo_curso)==null)?"":doc.get(campo_curso));
			//			

			String sValuesDest[] = doc.getValues(campo_destinatarios);
			hitToDoc.setDestinatarios((sValuesDest!=null)?sValuesDest:new String[0]);
			hitToDoc.setFormato((doc.getValues(campo_formato)!=null)?doc.getValues(campo_formato):new String[0]);

			String sValuesLicencias[] = doc.getValues(campo_licencias);
			hitToDoc.setLicencias((sValuesLicencias!=null)?sValuesLicencias:new String[0]);

			String sValuesRecurso[] = doc.getValues(campo_recurso);
			hitToDoc.setTipoRecurso((sValuesRecurso!=null)?sValuesRecurso:new String[0]);
			hitToDoc.setValoracion((doc.get(campo_valoracion)!=null)?new Float(doc.get(campo_valoracion)):new Float(0));

			hitToDoc.setNivelAgregacion((doc.get(campo_nivelAgregacion)!=null)?Integer.parseInt(doc.get(campo_nivelAgregacion)):0);
			hitToDoc.setFechaPublicacion(doc.get(campo_fechaPublicacion));
			hitToDoc.setHoraPublicacion(doc.get(campo_horaPublicacion));
			hitToDoc.setIdentificadorODE(doc.get(campo_identificadorODE));
			hitToDoc.setImagen((doc.get(campo_imagen)!=null)?doc.get(campo_imagen):"");
			hitToDoc.setTamanio((doc.get(campo_tamanio)!=null)?doc.get(campo_tamanio):"");
			hitToDoc.setConSinSecuencia((doc.get(campo_secuencia)!=null)?Boolean.parseBoolean(doc.get(campo_secuencia)):false);

			ContribucionVO[] contribuciones = DocumentoIndexacion.obtenerContribucionVO(doc.getValues(campo_contribucion)!=null?doc.getValues(campo_contribucion):null);
			hitToDoc.setContribuciones(contribuciones);
			hitToDoc.setPalabrasClave(doc.getValues(campo_palabrasClave)!=null?doc.getValues(campo_palabrasClave):new String[0]);
			hitToDoc.setOrientacionDidactica(doc.getValues(campo_descripcion_objetivo)!=null?doc.getValues(campo_descripcion_objetivo):new String[0]);
		}
		catch (Exception ex){
			logger.error("Producido error - " + ex);
			throw ex;
		}
		return hitToDoc;
	}

	private DocVO30 getVOFromLucene(Document doc, DocVO30 hitToDoc, int ranking) throws Exception
	{
		try{
			hitToDoc.setRanking(ranking);
			hitToDoc.setLocalizadorODE(doc.get(campo_localizador));
			hitToDoc.setDescripcion((doc.get(campo_descripcion)!=null)?doc.get(campo_descripcion):"");
			hitToDoc.setTitulo((doc.get(campo_titulo)!=null)?doc.get(campo_titulo):"");
			String sValuesAmbito[] = doc.getValues(campo_ambito);
			hitToDoc.setAmbito((sValuesAmbito!=null)?sValuesAmbito:new String[0]);

			String idArbolCurricularVig = getIdArbolCurricularVig();
			String sTaxonomias[] = doc.getValues(campo_taxonomia_path);
			if(sTaxonomias!=null && sTaxonomias.length>0){
				List<String> areasCurriculares = new ArrayList<String>();
				for(int i=0;i<sTaxonomias.length;i++){
					if(idArbolCurricularVig.equals(sTaxonomias[i].split("/")[0])){
						areasCurriculares.add(sTaxonomias[i].substring(sTaxonomias[i].indexOf("/")+1));
					}
				}
				hitToDoc.setAreaCurricular((areasCurriculares!=null && areasCurriculares.size()>0)?areasCurriculares.toArray(new String[0]):new String[0]);
			}

			hitToDoc.setFormato((doc.getValues(campo_formato)!=null)?doc.getValues(campo_formato):new String[0]);

			String sValueIdioma = doc.get(campo_idiomaBusqueda_compass);
			hitToDoc.setIdioma((sValueIdioma!=null)?sValueIdioma:"");

			String sValuesRecurso[] = doc.getValues(campo_recurso);
			hitToDoc.setTipoRecurso((sValuesRecurso!=null)?sValuesRecurso:new String[0]);
			hitToDoc.setValoracion((doc.get(campo_valoracion)!=null)?new Float(doc.get(campo_valoracion)):new Float(0));

			hitToDoc.setNivelAgregacion((doc.get(campo_nivelAgregacion)!=null)?Integer.parseInt(doc.get(campo_nivelAgregacion)):0);
			hitToDoc.setFechaPublicacion(doc.get(campo_fechaPublicacion));
			hitToDoc.setHoraPublicacion(doc.get(campo_horaPublicacion));
			hitToDoc.setIdentificadorODE(doc.get(campo_identificadorODE));
			hitToDoc.setImagen((doc.get(campo_imagen)!=null)?doc.get(campo_imagen):"");
			hitToDoc.setTamanio((doc.get(campo_tamanio)!=null)?doc.get(campo_tamanio):"");
			hitToDoc.setConSinSecuencia((doc.get(campo_secuencia)!=null)?Boolean.valueOf(doc.get(campo_secuencia)):false);

		}
		catch (Exception ex){
			logger.error("Producido error - " + ex);
			throw ex;
		}
		return hitToDoc;
	}

	private ResultadoHeaderVO[] mapDocToHeader(Hits documentos) throws Exception
	{
		ResultadoHeaderVO[] resultados = new ResultadoHeaderVO[documentos.length()];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < documentos.length(); i++) {

			resultados[i] = new ResultadoHeaderVO();
			resultados[i].setFecha(DateManager.dateToCalendar(sdf.parse(documentos.doc(i).get(campo_fechaPublicacion))));
			resultados[i].setIdentificador(documentos.doc(i).get(campo_identificadorODE));
		}
		return resultados;
	}

	private ResultadoRecordVO[] mapDocToRecord(Hits documentos) throws Exception
	{
		ResultadoRecordVO[] resultados = new ResultadoRecordVO[documentos.length()];

		for (int i = 0; i < documentos.length(); i++) {

			resultados[i] = new ResultadoRecordVO();
			//			ALCANCE
			String sAmbito[] = documentos.doc(i).getValues(campo_alcance);
			resultados[i].setAmbito((sAmbito!=null)?sAmbito:new String[0]);
			//			AUTOR
			String sAutores[] = documentos.doc(i).getValues(campo_autor);
			resultados[i].setAutores((sAutores!=null)?sAutores:new String[0]);
			//			CONTRIBUIDOR
			String sContribuidor[] = documentos.doc(i).getValues(campo_contribuidor);
			resultados[i].setContribuidor((sContribuidor!=null)?sContribuidor:new String[0]);
			//			LICENCIAS
			String sDerechos[] = documentos.doc(i).getValues(campo_licencias);
			resultados[i].setDerechos((sDerechos!=null)?sDerechos:new String[0]);
			//			DESCRIPCION
			resultados[i].setDescripcion((documentos.doc(i).get(campo_descripcion)!=null)?
					documentos.doc(i).get(campo_descripcion):"");
			//			FECHA PUBLICACION
			resultados[i].setFecha((documentos.doc(i).get(campo_fechaPublicacion)!=null)?
					documentos.doc(i).get(campo_fechaPublicacion):"");
			//			FORMATO
			String sFormato[] = documentos.doc(i).getValues(campo_formato);
			resultados[i].setFormatos((sFormato!=null)?sFormato:new String[0]);
			//			FUENTE
			String sFuente[] = documentos.doc(i).getValues(campo_fuente);
			resultados[i].setFuente((sFuente!=null)?sFuente:new String[0]);
			//			IDENTIFICADOR
			resultados[i].setIdentificador(documentos.doc(i).get(campo_identificadorODE));
			//			IDIOMA
			resultados[i].setIdioma(documentos.doc(i).get(campo_idiomaBusqueda_compass));
			//			PUBLICADOR
			String sPublicador[] = documentos.doc(i).getValues(campo_publicador);
			resultados[i].setPublicador((sPublicador!=null)?sPublicador:new String[0]);
			//			RELACION
			String sRelacion[] = documentos.doc(i).getValues(campo_relacion);
			resultados[i].setRelacion((sRelacion!=null)?sRelacion:new String[0]);
			//			PALABAS CLAVE
			String sTema[] = documentos.doc(i).getValues(campo_palabrasClave);
			resultados[i].setTema((sTema!=null)?sTema:new String[0]);
			//			RECURSO
			String sTipo[] = documentos.doc(i).getValues(campo_recurso);
			resultados[i].setTipo((sTipo!=null)?sTipo:new String[0]);
			//			TITULO
			resultados[i].setTitulo(documentos.doc(i).get(campo_titulo));
		}
		return resultados;
	}

	private ResultadoRepositorioVO[] mapDocToRepositorio(Hits documentos) throws Exception
	{
		String urlLocal=AgregaPropertiesImpl.getInstance().getUrlNodo();
		String idArbolCurricularVig = getIdArbolCurricularVig();

		ResultadoRepositorioVO[] resultados = new ResultadoRepositorioVO[documentos.length()];
		for (int i = 0; i < documentos.length(); i++) {

			resultados[i] = new ResultadoRepositorioVO();
			//			FECHA PUBLICACION
			resultados[i].setFechaPublicacion(((documentos.doc(i).get(campo_fechaPublicacion)!=null)?
					documentos.doc(i).get(campo_fechaPublicacion):""));
			//			IDENTIFICADOR
			resultados[i].setIdentificador(((documentos.doc(i).get(campo_identificadorODE)!=null)?
					documentos.doc(i).get(campo_identificadorODE):""));

			//			IDIOMA
			resultados[i].setIdioma(((documentos.doc(i).get(campo_idiomaBusqueda_compass)!=null)?
					documentos.doc(i).get(campo_idiomaBusqueda_compass):""));
			//			NIVEL AGREGACION
			resultados[i].setNivelAgregacion(((documentos.doc(i).get(campo_nivelAgregacion)!=null)?
					documentos.doc(i).get(campo_nivelAgregacion):""));
			//			TITULO
			resultados[i].setTitulo(((documentos.doc(i).get(campo_titulo)!=null)?
					documentos.doc(i).get(campo_titulo):""));
			//			DESCRIPCION
			resultados[i].setDescripcion(((documentos.doc(i).get(campo_descripcion)!=null)?
					documentos.doc(i).get(campo_descripcion):""));
			//			EDAD
			resultados[i].setEdad(((documentos.doc(i).get(campo_edad)!=null)?
					documentos.doc(i).get(campo_edad):""));

			//			IDIOMA TRADUCIDO
			String idiomaTraducido = I18n.getInstance().obtenerIdiomaTraducido(resultados[i].getIdioma(), "es"); // estamos poniendo a capon el idioma de traduccion del idioma en el que esta indexado el ODE
			resultados[i].setIdiomaTexto(idiomaTraducido);
			//			DESCRIPCIONES OBJETIVO
			resultados[i].setObjetivos(((documentos.doc(i).getValues(campo_descripcion_objetivo )!=null)?
					documentos.doc(i).getValues(campo_descripcion_objetivo):new String[0]));

			//			URL de la imagen
			String urlImagen=((documentos.doc(i).get(campo_imagen)!=null)?
					documentos.doc(i).get(campo_imagen):"");
			if (urlImagen == null || urlImagen.equals("")) // si el ODE no tiene imagen cojo la de defecto de la plataforma
				urlImagen = "/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO); 

			//			PATH AREA CURRICULAR

			String sTaxonomias[] = documentos.doc(i).getValues(campo_taxonomia_path);
			if(sTaxonomias!=null && sTaxonomias.length>0){
				List<String> areasCurriculares = new ArrayList<String>();
				for(int j=0;j<sTaxonomias.length;j++){

					if(idArbolCurricularVig.equals(sTaxonomias[j].split("/")[0])){
						areasCurriculares.add(sTaxonomias[j].substring(sTaxonomias[j].indexOf("/")+1));
					}
				}
				resultados[i].setAreaCurricularPath((areasCurriculares!=null && areasCurriculares.size()>0)?areasCurriculares.toArray(new String[0]):new String[0]);
			}

			resultados[i].setUrlImagen(urlLocal + urlImagen);

		}
		return resultados;
	}

	private boolean contains (String word, String pattern){
		return (word.indexOf(pattern) != -1);
	}

	private String formatDayDD(int day)
	{
		if (day > 0 && day < 10)
			return "0"+day;
		else
			return ""+day;
	}

	private String formatMonthMM(int month)
	{
		if (month > 0 && month < 10)
			return "0"+month;
		else
			return ""+month;
	}

	private String replaceWords (String palabrasClave, char sustituir, char sustitucion){

		while (this.contains(palabrasClave, String.valueOf(sustituir))){
			palabrasClave=palabrasClave.replace(sustituir,sustitucion);
		}
		return palabrasClave;
	}

	private List<String> obtenerPalabrasClave (String palabrasClave, boolean tesauro){
		String palabrasClaveLimpias = this.replaceWords(this.replaceWords(this.replaceWords(this.replaceWords(palabrasClave, '*', ' '), '?', ' '), '+', ' '), '"', ' ');
		//		obtengo un array de palabras y espacios en blanco
		String [] palabrasYBlancos =  (palabrasClaveLimpias.trim().split(" "));
		//		elimino palabras con ?, *, y los espacios en blanco
		List<String> palabras = new ArrayList<String>();
		if(tesauro){
			palabras.add(palabrasClaveLimpias);
		}else{
			for (int i=0;i<palabrasYBlancos.length;i++){
				if (!palabrasYBlancos[i].equals(" ")){
					palabras.add(palabrasYBlancos[i]);
				}
			}
		}
		return palabras;
	}
	//	/**
	//	 * Metodo para ordenar un array dee Strings
	//	 * @param a
	//	 * 			El array 
	//	 */
	//	public void bubble(String [] a) 
	//	  {
	//	    for (int i=1; i<a.length;i++)
	//	    {
	//	        for(int j=0;j<a.length-1;j++)
	//	        {
	//	            if (a[j].compareTo(a[j+1]) >= 0)
	//	            {
	//	                String temp = a[j]; 
	//	                a[j]= a[j+1];
	//	                a[j+1]= temp;
	//	            }
	//	        }
	//	    } 
	//	  }

	private String array2String(Object[] array) {
		StringBuilder str = new StringBuilder("");
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				str.append(array[i].toString());
				if ((i + 1) < array.length) str.append(" ");
			}
		}
		return str.toString();
	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#handleObtenerCatalogoRepositorioParam(Integer)
	 * @param nivelAgregacion Nivel de agregacion minimo que tienen que cumplir los ODEs del catalogo que se genere.
	 * @return ResultadoRepositorioVO[] Fecha Devuelve un array de objetos de valor con información indexada de los ODEs.
	 */
	protected ResultadoRepositorioVO[] handleObtenerCatalogoRepositorioParam(Integer nivelAgregacion) throws Exception {

		String nivelAgregacionStr ="";
		if (nivelAgregacion == null)
		{
			throw new Exception("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Nivel de agregacion nulo.");
		}
		else
		{
			try {
				nivelAgregacionStr = Integer.toString(nivelAgregacion);
			} catch (Exception e) {
				logger.error("Error recuperando repositorio nivel agregacion <"+nivelAgregacion+">. Nivel de agregacion invalido.", e);
				throw new Exception("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Nivel de agregacion invalido.",e);
			}
		}

		//	    	Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		//			Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		//Recogemos las descripciones del objetivo y le metemos la lógica, debe tener el nivel de agregacion mayor o igual que el que me pasan
		andQuery.add(getConstantScoreRangeQuery(campo_nivelAgregacion,nivelAgregacionStr,MAX),BooleanClause.Occur.MUST);

		//    	Buscamos sobre todos los subindices la query formada anteriormente
		Hits hits = null;
		ResultadoRepositorioVO[] resultado = new ResultadoRepositorioVO[0];
		try {
			hits = this.getGestorCompass().busqueda(andQuery, null, null);
			if (hits != null && hits.length() > 0)
			{
				logger.debug("Recuperando repositorio nivel agregacion <"+nivelAgregacion+">. Informacion de repositorio disponible para query <"+andQuery.toString()+"> documentos <"+hits.length()+">");
				try {
					resultado = mapDocToRepositorio(hits);
				} catch (RuntimeException e) {
					logger.error("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Error mapeando resultados con query["+andQuery.toString()+"].["+e.getCause()+"]",e);
					throw new Exception("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Error mapeando resultados con query["+andQuery.toString()+"].",e);
				}
			}
			else
			{
				logger.debug("Recuperando repositorio nivel agregacion["+nivelAgregacion+"]. No hay informacion de repositorio disponible para query["+andQuery.toString()+"]");
			}
		} catch (Exception e) {
			logger.error("Error recuperando repositorio nivel agregacion <"+nivelAgregacion+">. Error buscando en el indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> + e");	    			
		}
		return resultado;	
	}

	/**
	 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService#busquedaLOM_ESVSQL(es.pode.indexador.negocio.servicios.busqueda.QuerySimpleVO)
	 * @param paramBusqueda QuerySimpleVO Parametros de una query simple.
	 * @return DocumentosLOM_ESVO Esta clase representa los documentos resultado de una busqueda por LOM_ES.
	 */
	protected DocumentosLOM_ESVO handleBusquedaLOM_ESVSQL(QuerySimpleVO paramBusqueda) throws Exception {
		DocumentosLOM_ESVO respuesta = new DocumentosLOM_ESVO();
		DocLOM_ESVO[] resultados = null;
		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
		String queryLang = paramBusqueda.getLenguajeQuery();
		String idioma = paramBusqueda.getIdioma();
		String unparsedQuery = paramBusqueda.getQuery();
		Hits hits = null;
		logger.debug("Se ha recibido una query en lenguaje <"+queryLang+"> con contenido <"+unparsedQuery+">");
		// Discriminar SQI / Lucene
		if ("VSQL".equals(queryLang)) 
		{
			String[] terms = parsearVSQL(unparsedQuery);
			// Introduzco los terminos de busqueda en titulo, descripcion y
			// palabras clave
			BooleanQuery query3 = new BooleanQuery();
			for (int i = 0; i < terms.length; i++) 
			{
				BooleanQuery query2 = new BooleanQuery();
				if(this.contains(terms[i], "*") || this.contains(terms[i], "?")){
					if (logger.isDebugEnabled())
						logger.debug("Esta query es una WildCardQUery");
					query2.add(getWildcardQuery(campo_titulo, terms[i].toLowerCase()),BooleanClause.Occur.SHOULD);
					query2.add(getWildcardQuery(campo_descripcion, terms[i].toLowerCase()),BooleanClause.Occur.SHOULD);
					query2.add(getWildcardQuery(campo_palabrasClave, terms[i].toLowerCase()),BooleanClause.Occur.SHOULD);
				}
				else{
					if (logger.isDebugEnabled())
						logger.debug("Es una query sin WildCardQuery");
					query2.add(getTermQuery(campo_titulo, terms[i].toLowerCase()),BooleanClause.Occur.SHOULD);
					query2.add(getTermQuery(campo_descripcion,
							terms[i].toLowerCase()),BooleanClause.Occur.SHOULD);
					query2.add(getTermQuery(campo_palabrasClave, terms[i]
					                                                   .toLowerCase()),BooleanClause.Occur.SHOULD);
				}
				query3.add(query2,BooleanClause.Occur.MUST);
			}
			query.add(query3);
			if (logger.isDebugEnabled())
				logger.debug("Consulta LOM_ES con query <"+query+"> a partir de SQUI lenguaje.");
		} 
		else{
			logger.warn("Se ha recibido una query que no es SQI ni Lucene: <"+queryLang+">");
			throw new Exception("Se ha recibido una query que no es SQI ni Lucene =>["+queryLang+"].");
		}
		//		 Realizamos la busqueda, de la query formada anteriomente, en el subindice del idioma indicado
		try{
			if (logger.isDebugEnabled())
				logger.debug("Consulta LOM_ES con query <"+query.toString()+">");
			hits = this.getGestorCompass().busqueda(query, idioma, null);
		}catch(Exception e){
			logger.error("Error con LenguajeQuery= <"+paramBusqueda.getLenguajeQuery()+"> Idioma= <"+paramBusqueda.getIdioma()+" Query="+paramBusqueda.getQuery()+" NumResultados= <"+paramBusqueda.getNumResultados() + ">",e);
			throw new Exception("Error con LenguajeQuery= <"+paramBusqueda.getLenguajeQuery()+"> Idioma=<"+paramBusqueda.getIdioma()+"> Query=<"+paramBusqueda.getQuery()+"> NumResultados=<"+paramBusqueda.getNumResultados()+">");
		}
		logger.debug("Numero de hits obtenidos <"+(hits != null?hits.length():0)+">");
		if (hits != null && hits.length() > 0) 
		{
			logger.debug("Numero de hits <" + hits.length()+">");
			resultados = new DocLOM_ESVO[hits.length()];

			for (int i = 0; i < hits.length(); i++) 
			{
				DocLOM_ESVO hitToDoc = new DocLOM_ESVO();
				Document doc = hits.doc(i);
				String localizador = doc.get(campo_localizador);
				String mec = doc.get(campo_identificadorODE);
				hitToDoc.setRanking(Float.floatToIntBits(hits.score(i)));

				Manifest manifest = this.parsearManifiesto(localizador+ File.separator + "imsmanifest.xml");
				Lom lom = null;

				if (manifest != null) 
				{
					ManifestAgrega ma = new ManifestAgrega(manifest);
					lom = ma.obtenerLom(manifest.getIdentifier(), null);
					LomAgrega agrega=new LomAgrega(lom);
					TechnicalAgrega tecnica = agrega.getTechnicalAgrega();
					ArrayList<String> localizadores=new ArrayList<String>();
					String hostNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);//HOST
					String local=props.getProperty(PROTOCOLO_HTTP) + hostNodo + props.getProperty(BUSCADOR_FICHA) +  idioma + "/" + mec ;
					localizadores.add(local);
					tecnica.setLocalizadorAv(localizadores);
					agrega.setTechnicalAgrega(tecnica);
					lom=agrega.getLom();
					//					creo un writer para escribir los metadatos
					StringWriter sw= new StringWriter();
					//completo el writer
					this.getLomesDao().escribirLom(lom, sw);
					hitToDoc.setDocumento(sw.toString());
					resultados[i] = hitToDoc;
				}

			}
			respuesta.setResultados(resultados);
		}
		return respuesta;
	}

	private boolean validarPersonalizada(ParamAvanzadoVO param) throws Exception{
		if ((param.getFechaPublicacion()==null)&& (param.getTaxonomia()==null)&& 
				(param.getAutor()==null || param.getAutor().equals(""))&& (param.getSecuencia()==null)&&		
				(param.getContexto()==null || param.getContexto().equals(""))&& (param.getDescripcion()==null || param.getDescripcion().equals(""))&& 
				(param.getDestinatarios()==null || param.getDestinatarios().equals(""))&&
				(param.getEdad()==null || param.getEdad().equals(""))&&	(param.getFormato()==null)&&(param.getKeyword()==null || param.getKeyword().equals(""))&& 
				//(param.getIdTesauro()==null || param.getIdTesauro().equals(""))&&  
				(param.getNivelAgregacion()==null)&&  
				(param.getProcesoCognitivo()==null || param.getProcesoCognitivo().equals(""))&& (param.getRecurso()==null || param.getRecurso().equals(""))&& 
				(param.getTitulo()==null || param.getTitulo().equals(""))&&(param.getValoracion()==null || param.getValoracion().equals(""))){
			return false;
		}else return true;
	}

	private ResultadoRecordVO[] mapDocToRecordStartEnd(Hits documentos,int registroInicial, int registroFinal) throws Exception
	{
		ArrayList<ResultadoRecordVO> listaResultados = new ArrayList<ResultadoRecordVO>();
		ResultadoRecordVO resultados = null; 

		for (int i = registroInicial; i < registroFinal; i++) {

			if((documentos.length() > i)&&!(documentos.doc(i) == null))
			{

				resultados = new ResultadoRecordVO();
				//				ALCANCE
				String sAmbito[] = documentos.doc(i).getValues(campo_alcance);
				resultados.setAmbito((sAmbito!=null)?sAmbito:new String[0]);

				//				AUTOR
				String sAutores[] = documentos.doc(i).getValues(campo_autor);
				resultados.setAutores((sAutores!=null)?sAutores:new String[0]);

				//				CONTRIBUIDOR
				String sContribuidor[] = documentos.doc(i).getValues(campo_contribuidor);
				resultados.setContribuidor((sContribuidor!=null)?sContribuidor:new String[0]);

				//				LICENCIAS
				String sDerechos[] = documentos.doc(i).getValues(campo_licencias);
				resultados.setDerechos((sDerechos!=null)?sDerechos:new String[0]);

				//				DESCRIPCION
				resultados.setDescripcion((documentos.doc(i).get(campo_descripcion)!=null)?
						documentos.doc(i).get(campo_descripcion):"");

				//				FECHA PUBLICACION
				resultados.setFecha((documentos.doc(i).get(campo_fechaPublicacion)!=null)?
						documentos.doc(i).get(campo_fechaPublicacion):"");

				//				FORMATO
				String sFormato[] = documentos.doc(i).getValues(campo_formato);
				resultados.setFormatos((sFormato!=null)?sFormato:new String[0]);

				//				FUENTE
				String sFuente[] = documentos.doc(i).getValues(campo_fuente);
				resultados.setFuente((sFuente!=null)?sFuente:new String[0]);

				//				IDENTIFICADOR
				resultados.setIdentificador(documentos.doc(i).get(campo_identificadorODE));
				//				IDIOMA
				resultados.setIdioma(documentos.doc(i).get(campo_idiomaBusqueda_compass));

				//				PUBLICADOR
				String sPublicador[] = documentos.doc(i).getValues(campo_publicador);
				resultados.setPublicador((sPublicador!=null)?sPublicador:new String[0]);

				//				RELACION
				String sRelacion[] = documentos.doc(i).getValues(campo_relacion);
				resultados.setRelacion((sRelacion!=null)?sRelacion:new String[0]);
				//				PALABAS CLAVE
				String sTema[] = documentos.doc(i).getValues(campo_palabrasClave);
				resultados.setTema((sTema!=null)?sTema:new String[0]);

				//				RECURSO
				String sTipo[] = documentos.doc(i).getValues(campo_recurso);
				resultados.setTipo((sTipo!=null)?sTipo:new String[0]);
				//				TITULO
				resultados.setTitulo(documentos.doc(i).get(campo_titulo));

				listaResultados.add(resultados);

			}

		}


		return listaResultados.toArray(new ResultadoRecordVO[0]);
	}

	//TODO Ver desde donde se llama, urlLocal deberia ser parámetro para repos externos
	private ResultadoRepositorioVO[] mapDocToRepositorioStartEnd(Hits documentos, int registroInicial, int registroFinal) throws Exception
	{
		int numeroResultados = registroFinal - registroInicial;

		ResultadoRepositorioVO[] resultados = new ResultadoRepositorioVO[numeroResultados];

		String urlLocal=AgregaPropertiesImpl.getInstance().getUrlNodo();
		String idArbolCurricularVig = getIdArbolCurricularVig();

		int j = 0;
		for (int i = registroInicial; i < registroFinal; i++) {
			resultados[j] = new ResultadoRepositorioVO();
			//			FECHA PUBLICACION
			resultados[j].setFechaPublicacion(((documentos.doc(i).get(campo_fechaPublicacion)!=null)?
					documentos.doc(i).get(campo_fechaPublicacion):""));
			//			IDENTIFICADOR
			//			resultados[i].setIdentificador(documentos.doc(i).get(campo_identificadorODE));
			resultados[j].setIdentificador(((documentos.doc(i).get(campo_identificadorODE)!=null)?
					documentos.doc(i).get(campo_identificadorODE):""));
			//			IDIOMA
			//			resultados[i].setIdioma(documentos.doc(i).get(idiomaBusq));
			resultados[j].setIdioma(((documentos.doc(i).get(campo_idiomaBusqueda_compass)!=null)?
					documentos.doc(i).get(campo_idiomaBusqueda_compass):""));
			//			NIVEL AGREGACION
			//			resultados[i].setNivelAgregacion((documentos.doc(i).get(campo_nivelAgregacion)));
			resultados[j].setNivelAgregacion(((documentos.doc(i).get(campo_nivelAgregacion)!=null)?
					documentos.doc(i).get(campo_nivelAgregacion):""));
			//			TITULO
			//			resultados[i].setTitulo((documentos.doc(i).get(titulo)));
			resultados[j].setTitulo(((documentos.doc(i).get(campo_titulo)!=null)?
					documentos.doc(i).get(campo_titulo):""));
			//			DESCRIPCION
			resultados[j].setDescripcion(((documentos.doc(i).get(campo_descripcion)!=null)?
					documentos.doc(i).get(campo_descripcion):""));
			//			EDAD
			resultados[j].setEdad(((documentos.doc(i).get(campo_edad)!=null)?
					documentos.doc(i).get(campo_edad):""));
			//			IDIOMA TRADUCIDO
			String idiomaTraducido = I18n.getInstance().obtenerIdiomaTraducido(resultados[j].getIdioma(), "es"); // estamos poniendo a capon el idioma de traduccion del idioma en el que esta indexado el ODE
			resultados[j].setIdiomaTexto(idiomaTraducido);
			//			DESCRIPCIONES OBJETIVO
			resultados[j].setObjetivos(((documentos.doc(i).getValues(campo_descripcion_objetivo )!=null)?
					documentos.doc(i).getValues(campo_descripcion_objetivo):new String[0]));
			//			URL de la imagen
			String urlImagen=((documentos.doc(i).get(campo_imagen)!=null)?
					documentos.doc(i).get(campo_imagen):"");
			if (urlImagen == null || urlImagen.equals("")) // si el ODE no tiene imagen cojo la de defecto de la plataforma
				urlImagen = "/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO); 

			//PATH AREA CURRICULAR
			String sTaxonomias[] = documentos.doc(i).getValues(campo_taxonomia_path);
			List<String> areasCurriculares = new ArrayList<String>();

			for(int k=0;k<sTaxonomias.length;k++){
				if(idArbolCurricularVig.equals(sTaxonomias[k].split("/")[0])){
					areasCurriculares.add(sTaxonomias[k].substring(sTaxonomias[k].indexOf("/")));
				}
			}
			resultados[j].setAreaCurricularPath((areasCurriculares!=null && areasCurriculares.size()>0)?areasCurriculares.toArray(new String[areasCurriculares.size()]):new String[0]);

			resultados[j].setUrlImagen(urlLocal + urlImagen);

			j++;

		}
		return resultados;

	}

	private ResultadoHeaderVO[] mapDocToHeaderStartEnd(Hits documentos,int registroInicial, int registroFinal) throws Exception
	{
		//		int numeroResultados = registroFinal - registroInicial;

		ArrayList<ResultadoHeaderVO> listaResultados = new ArrayList<ResultadoHeaderVO>();
		ResultadoHeaderVO resultados = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		for (int i = registroInicial; i < registroFinal; i++) {
			if((documentos.length() > i)&&!(documentos.doc(i) == null))
			{
				resultados = new ResultadoHeaderVO();
				resultados.setFecha(DateManager.dateToCalendar(sdf.parse(documentos.doc(i).get(campo_fechaPublicacion))));
				resultados.setIdentificador(documentos.doc(i).get(campo_identificadorODE));
				listaResultados.add(resultados);
			}
		}
		return listaResultados.toArray(new ResultadoHeaderVO[0]);

	}

	private List<Object> addTaxonomias(String[] taxonomias, List<Object> palabras,String tipoBusqueda){
		String andOr="+";//Valor por defecto "and"
		if(taxonomias!=null && taxonomias.length > 0){
			if(tipoBusqueda!=null && tipoBusqueda.equals(SrvBuscadorServiceImpl.BUSCARRELACIONADOSAC)){
				//Si estamos obteniendo los odes relacionados por árbol curricular añadimos las taxonomias con OR en la query
				andOr="";//Indica "or"
			}
			palabras.add(taxonomias[0].toLowerCase());
			for(int i =1; i < taxonomias.length; i++){
				if(!andOr.equals(""))
					palabras.add(andOr);
				palabras.add(taxonomias[i].toLowerCase());
			}
		}
		return palabras;

	}

	private List<Object> addFormatosNivelesAgregacion(String[] seleccion, List<Object> palabras){
		String andOr="";//Valor por defecto "and"
		if(seleccion!=null && seleccion.length > 0){
			for(int i = 0; i < seleccion.length; i++){
				if(seleccion[i]!=null && !seleccion[i].equals(""))
					palabras.add(seleccion[i].toLowerCase());
			}
		}
		return palabras;

	}

	private String getIdArbolCurricularVig(){
		if((arbolCurricularVigente==null) || ((System.currentTimeMillis() - startArbol) >= tiempoRefresco)){
			try{
				arbolCurricularVigente = this.getSrvTaxonomiaService().obtenerArbolVigente().getVocabIdentifier();
				startArbol = System.currentTimeMillis();
			} catch (Exception e) {
				logger.error("ERROR: Obteniendo identificador del arbol curricular vigente - ",e);
			}
		}

		return arbolCurricularVigente;
	}

	private String getIdTesauroVig(){
		if((id_tesauro == null) || ((System.currentTimeMillis() - startTesauro) >= tiempoRefresco)){
			try{
				EstructuraVdexVO tesauroVig = this.getSrvTesaurosServices().obtenerTesauroVigente();
				nombre_tesauro=tesauroVig.getVocabName();
				id_tesauro=tesauroVig.getVocabIdentifier();
				startTesauro = System.currentTimeMillis();
			} catch (Exception e) {
				logger.error("ERROR: Obteniendo identificador del tesauro vigente - ",e);
			}
		}

		return id_tesauro;
	}

	private String getNombreTesauroVig(){
		if((nombre_tesauro == null) || ((System.currentTimeMillis() - startTesauro) >= tiempoRefresco)){
			try{
				EstructuraVdexVO tesauroVig = this.getSrvTesaurosServices().obtenerTesauroVigente();
				nombre_tesauro=tesauroVig.getVocabName();
				id_tesauro=tesauroVig.getVocabIdentifier();
				startTesauro = System.currentTimeMillis();
			} catch (Exception e) {
				logger.error("ERROR: - Obteniendo nombre del tesauro vigente - ",e);
			}
		}

		return nombre_tesauro;
	}

	public GestorCompass getGestorCompass() {
		return gestorCompass;
	}

	public void setGestorCompass(GestorCompass gestorCompass) {
		this.gestorCompass = gestorCompass;
	}	

	@Override
	protected SugerenciasVO[] handleObtenerSugerencias(ParamAvanzadoVO paramSug) throws Exception {
		long inicio=System.currentTimeMillis();	
		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);
		SugerenciasVO[] resultado =null;

		HashMap<String, SugerenciasVO> sugerencias = new HashMap<String, SugerenciasVO>();

		String palabraBusq = paramSug.getPalabrasClave();
		paramSug.setPalabrasClave(palabraBusq+"*");
		palabraBusq = UtilesString.removeAccents(palabraBusq);
		palabraBusq = palabraBusq.replaceAll(EXP_REG_ESPACIOS_BLANCO, " ");

		long inicioHits=System.currentTimeMillis();
		//Obtenemos los hits(odes) que contienen la palabra(frase) buscada
		Hits hits = this.internaBusquedaAvanzada(paramSug, query, "");
		long finHits=System.currentTimeMillis();
		long tiempoHits=(finHits-inicioHits)/1000;
		logger.debug("Tiempo obteniendo hits <" + tiempoHits + "> segundos <"+ (finHits-inicioHits) + "> milisegundos" );
		if(hits!=null && hits.length() > 0){
			String[] stopWords= new String[0];
			try{
				stopWords = props.getProperty("hightFreqTerms.stopWords." + paramSug.getIdiomaBusqueda()).trim().split(",");
			}catch(Exception ex){
				logger.error("ERROR: No existe StopWords para idioma= <"+paramSug.getIdiomaBusqueda() +">", ex);
			}
			int maximo= hits.length()<100?hits.length():100;
			for(int i=0;i<maximo;i++){
				Document doc = hits.doc(i);
				String[] descripciones = doc.getValues(campo_descripcion);
				String[] titulo = doc.getValues(campo_titulo);
				String[] palabrasClave = doc.getValues(campo_palabrasClave);
				String[] literalArbolC = doc.getValues(campo_literalArbolC);
				//				logger.info("handleObtenerSugerencias - obtenemos sugerencias --> " + i);
				Collection<String> aSugOde = this.sugerenciasOde(palabraBusq, descripciones, titulo, palabrasClave, literalArbolC,stopWords);
				Iterator<String> iter = aSugOde.iterator();
				//Recorremos las sugerencias que hemos obtenido de este ODE y las añadimos a las hashMap, si es una sugerencias nueva la añadimos 
				//y si es una que ya estaba en la hashMap le sumamos uno al numero de odes de esta sugerencia.
				while(iter.hasNext()){
					Integer count = 1;
					String sug = iter.next();
					SugerenciasVO sugVO = new SugerenciasVO();
					sugVO.setNumOdes(count);
					sugVO.setSugerencia(sug);
					if(sugerencias.containsKey(sug)){
						sugVO = sugerencias.get(sug);
						count = sugVO.getNumOdes();
						count++;
						sugVO.setNumOdes(count);
					}
					sugerencias.put(sug, sugVO);	
				}
			}
		}
		//Obtenemos los resultados
		Collection<SugerenciasVO> cSug = sugerencias.values();
		//Ordenamos los resultados de mayor a menor
		resultado = cSug.toArray(new SugerenciasVO[0]);
		Arrays.sort(resultado, new SugerenciasComparator());


		int numSugerencias = paramSug.getNumeroResultados()!=null && paramSug.getNumeroResultados().intValue()>0?paramSug.getNumeroResultados().intValue():NUM_SUG_BUSQ_DEFAULT;

		int nSug = resultado.length>numSugerencias?numSugerencias:resultado.length;
		SugerenciasVO[] result= new SugerenciasVO[nSug]; 
		for(int i=0; i< nSug;i++){
			//			si queremos mostrar el número de resultados descomentar estas 3 lineas
			//			si no el número de ODES que va en el VO no es real estaría 1 a 100 que es
			//			número máximo de odes en los que buscamos sugerencias
			//			paramSug.setPalabrasClave("\""+resultado[i].getSugerencia()+"\"");
			//			Hits odes = this.internaBusquedaAvanzada(paramSug, query);
			//			resultado[i].setNumOdes(odes!=null?odes.length():0);
			result[i]=resultado[i];
		}

		long fin = System.currentTimeMillis();
		long tiempo = (fin - inicio)/1000;
		logger.debug("Sugerencias de busqueda: <" + resultado.length +"> en <" +tiempo+"> segundos");

		return result;
	}

	private Collection<String> sugerenciasOde(String palabra, String[] descripciones,String[] titulo,String[] palabrasClave,String[]literalArbolC,String[] stopWords){
		Collection<String> sugOde = new ArrayList<String>();
		//		logger.info("sugerenciasOde - ****************** INICIO SUGERENCIAS ODE ******************");
		//		logger.info("sugerenciasOde - OBTENEMOS SUGERENICIAS DE DESCRIPCIONES");
		this.añadirSugerencias(descripciones, palabra, sugOde,stopWords);
		//		logger.info("sugerenciasOde - OBTENEMOS SUGERENICIAS DE TITULO");
		this.añadirSugerencias(titulo, palabra, sugOde,stopWords);
		//		logger.info("sugerenciasOde - OBTENEMOS SUGERENICIAS DE PALABRASCLAVE");
		this.añadirSugerencias(palabrasClave, palabra, sugOde,stopWords);
		//		logger.info("sugerenciasOde - OBTENEMOS SUGERENICIAS DE LITERALARBOLC");
		this.añadirSugerencias(literalArbolC, palabra, sugOde,stopWords);
		//		logger.info("sugerenciasOde - ****************** FIN SUGERENCIAS ODE ******************");
		return sugOde;
	}

	private void añadirSugerencias(String[] textos,String palabra,Collection<String> sugerenciasActuales,String[] stopWords){

		for(int i = 0;i<textos.length;i++){
			//Antes de split eliminamos caracteres extraño (. ; " ....)
			String texto = textos[i];
			//			logger.info("añadirSugerencias - "+texto);
			if(texto!=null){
				texto = texto.toLowerCase().replaceAll("[\\.\\,;:\\+\\¿\\?\"\\-()]", " ");//\\-~#
				texto = UtilesString.removeAccents(texto);
				texto = texto.replaceAll(EXP_REG_ESPACIOS_BLANCO, " ");
				while(!texto.equals("")){
					if (texto.startsWith(palabra) || texto.indexOf(" "+palabra) != -1) {
						String comienzoPalabraBusqueda = "";
						if (texto.startsWith(palabra))
							comienzoPalabraBusqueda = texto.substring(texto.indexOf(palabra),texto.length()).trim();
						else 
							comienzoPalabraBusqueda = texto.substring(texto.indexOf(" " +palabra),texto.length()).trim();

						String[] textoSug = comienzoPalabraBusqueda.split(" ");
						String[] arrayPalabra = palabra.split(" ");

						StringBuffer resultado = new StringBuffer();
						if(textoSug[0]!=null && textoSug.length> 0 && !textoSug[0].trim().equals("") && !this.stopWord(textoSug[0],stopWords)){
							resultado.append(" ").append(textoSug[0]);
							if(!textoSug[0].equals(palabra) && textoSug[0].startsWith(palabra) && !sugerenciasActuales.contains(textoSug[0].trim()))
								//metemos la primera palabra como sugerencia si no es igual a la palabra que estamos buscando (ejem: Buscamos "consec" -> metemos "consecuencia")
								sugerenciasActuales.add(textoSug[0].trim());

							boolean terminado=false;
							for(int k=1; k< textoSug.length && !terminado;k++){
								if(textoSug[k]!=null && !textoSug[k].trim().equals("")){
									resultado.append(" ").append(textoSug[k]);
									if(k >= arrayPalabra.length ||!textoSug[k].equals(arrayPalabra[k])){

										if(!this.stopWord(textoSug[k],stopWords)){

											if(!textoSug[k].startsWith(arrayPalabra[arrayPalabra.length-1])){
												terminado=true;
											}						
											if(!sugerenciasActuales.contains(resultado.toString().trim()))
												sugerenciasActuales.add(resultado.toString().trim());								
										}			
									}
								}
							}//fin for 2							
						}
						texto = comienzoPalabraBusqueda.substring(palabra.length(),comienzoPalabraBusqueda.length());
					}
					else texto="";
				}
			}		
		}//fin for 1
	}

	@Override
	protected DocVO30[] handleBusquedaVariosMEC(ParamBuscarMecVO[] parametros) throws Exception {
		DocVO30[] resultados = null;
		ArrayList<DocVO30> lResultados = new ArrayList<DocVO30>();
		for(int i=0; i< parametros.length;i++){
			if(parametros[i]!=null && parametros[i].getIdentificadorMEC()!=null && parametros[i].getIdioma()!=null){
				try{
					DocVO30 datosODE = this.busquedaMEC(parametros[i].getIdentificadorMEC(), parametros[i].getIdioma());
					if(datosODE !=null)
						lResultados.add(datosODE);
				}catch(Exception e){
					logger.error("ERROR obteniendo datos del ODE con identificadorMEC <"+ parametros[i].getIdentificadorMEC() + "> en el idioma <" + parametros[i].getIdioma() + ">",e);
				}
			}
		}
		if(lResultados.size()>0)
			resultados = lResultados.toArray(new DocVO30[0]);

		return resultados; 
	}

	@Override
	protected DocMECSimpleVO handleBusquedaMECSimple(String identificadorMEC,
			String idiomaBusqueda) throws Exception {


		DisjunctionMaxQuery query = new DisjunctionMaxQuery(0.01f);

		Hits hits = this.internaBusquedaMEC(identificadorMEC, query, idiomaBusqueda);
		if (hits == null || (hits != null && hits.length() == 0)) {
			if (logger.isDebugEnabled())
				logger.debug("No hay resultados para una busqueda con MEC <"+identificadorMEC+">");
			return null;
		}
		if (logger.isDebugEnabled())
			logger.debug("Se obtienen <"+hits.length()+"> hits de una busqueda por MEC <"+identificadorMEC+">");
		DocMECSimpleVO docReturn = this.getVOFromLuceneSimple(hits.doc(0));

		if (docReturn != null)	return docReturn;
		return null;
	}

	private DocMECSimpleVO getVOFromLuceneSimple(Document doc) throws Exception {
		DocMECSimpleVO hitToDoc = new DocMECSimpleVO();
		try{
			String idArbolCurricularVig = getIdArbolCurricularVig();
			String idTesauroVig = getIdTesauroVig();
			String sTaxonomias[] = doc.getValues(campo_taxonomia_path);
			if(sTaxonomias!=null && sTaxonomias.length>0){
				List<String> areasCurriculares = new ArrayList<String>();
				List<String> tesauros = new ArrayList<String>();
				for(int i=0;i<sTaxonomias.length;i++){
					if(idArbolCurricularVig.equals(sTaxonomias[i].split("/")[0])){
						areasCurriculares.add(sTaxonomias[i].substring(sTaxonomias[i].indexOf("/")+1));
					}
					if(idTesauroVig.equals(sTaxonomias[i].split("/")[0])){
						tesauros.add(sTaxonomias[i].substring(sTaxonomias[i].indexOf("/")+1));
					}
				}
				hitToDoc.setAreaCurricular((areasCurriculares!=null && areasCurriculares.size()>0)?areasCurriculares.toArray(new String[0]):new String[0]);
				hitToDoc.setTesauro((tesauros!=null && tesauros.size()>0)?tesauros.toArray(new String[0]):new String[0]);
			}
			hitToDoc.setNivelAgregacion((doc.get(campo_nivelAgregacion)!=null)?Integer.parseInt(doc.get(campo_nivelAgregacion)):0);
			hitToDoc.setLocalizadorODE((doc.get(campo_localizador)!=null)?doc.get(campo_localizador):"");
		}
		catch (Exception ex){
			logger.error(ex);
			throw ex;
		}
		return hitToDoc;
	}

	@Override
	protected ResultadosCountVO handleSolicitudDocsCountIndiceRemoto(
			ParamDocsCountVO paramBusq, String idNodo) throws Exception {
		IndexSearcher searcher=null;
		ResultadosCountVO resultado = null;
		try{
			searcher = new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+idNodo+"/index/idioma_"+paramBusq.getIdiomaBusqueda());
		}catch(Exception e){
			logger.error("No se encuantra el índice con idioma: <"+paramBusq.getIdiomaBusqueda() + ">",e);
			if(searcher!=null)searcher.close();
			throw new Exception("No se encuantra el índice con idioma:"+paramBusq.getIdiomaBusqueda());
		}
		try{
			searcher.setSimilarity(new DefaultSimilarity());
			resultado = NumTermsArbol.obtenerNumeroNodos(paramBusq.getTaxonomia(),AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+idNodo+"/index/idioma_"+paramBusq.getIdiomaBusqueda());
			searcher.close();
		}catch(Exception e){
			logger.error("Idioma <"+paramBusq.getIdiomaBusqueda()+"> Taxonomía: <"+array2String(paramBusq.getTaxonomia()) + ">",e);
			if(searcher!=null)searcher.close();
			throw new Exception("Idioma:"+paramBusq.getIdiomaBusqueda()+" Taxonomía:"+array2String(paramBusq.getTaxonomia()));
		}
		return resultado;
	}

	@Override
	protected ResultadosCountVO handleSolicitudDocsCountNodos(
			ParamDocsCountVO paramBusq, String[] idNodo) throws Exception {

		HashMap<String, Document>[] map = new HashMap[paramBusq.getTaxonomia().length];
		ResultadosCountVO resultado = new ResultadosCountVO();
		//Obtener hits local
		Hits[] hits=getGestorCompass().hitsArbolQuery(paramBusq.getTaxonomia(),paramBusq.getIdiomaBusqueda(),campo_taxonomia);
		map=deduplicarHits(map, hits);

		if (idNodo!=null&&idNodo.length>0) {
			//Obtener hits remotos
			//NOTA: Hay que guardar los indexsearchers abiertos porque los hits los referencian internamente, que si no dan errores
			IndexSearcher[] searchers = new IndexSearcher[idNodo.length];
			for (int i = 0; i < idNodo.length; i++) {
				String nodo = idNodo[i];

				try {
					//searcher.setSimilarity(new DefaultSimilarity());
					searchers[i] = new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+ "/"+ nodo+ "/index/idioma_"+ paramBusq.getIdiomaBusqueda());
					//hits = NumTermsArbol.obtenerHitsNodos(paramBusq.getTaxonomia(),AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+nodo+"/index/idioma_"+paramBusq.getIdiomaBusqueda());
					hits = NumTermsArbol.obtenerHitsNodos(
							paramBusq.getTaxonomia(), searchers[i]);
					map = deduplicarHits(map, hits);
				} catch (Exception e) {
					logger.error(
							"handleSolicitudDocsCountNodos con idioma: "
							+ paramBusq.getIdiomaBusqueda()
							+ " Taxonomía: "
							+ array2String(paramBusq.getTaxonomia())
							+ "nodo: "
							+ nodo, e);
		//				if(searcher!=null)searcher.close();
		//					throw new Exception(
		//							"SrvBuscadorServiceImpl - handleSolicitudDocsCountNodos con idioma:"
		//									+ paramBusq.getIdiomaBusqueda()
		//									+ " Taxonomía:"
		//									+ array2String(paramBusq.getTaxonomia()));
				} finally {
					for (int j = 0; j < searchers.length; j++) {
						if (searchers[j] != null) {
							searchers[j].close();
							searchers[j] = null;
						}
					}
				}
			}
		}

		//Calcular resultado
		resultado.setConteo(new Integer[paramBusq.getTaxonomia().length]);
		int suma=0;
		for (int i=0; i<map.length; i++) {
			if (map[i]!=null) {
				resultado.getConteo()[i]=map[i].size();
				suma=suma+map[i].size();
			} else {
				resultado.getConteo()[i]=0;
			}
		}
		resultado.setDocumentosCount(suma);
		return resultado;
	}

	private HashMap<String,Document>[] deduplicarHits(HashMap<String, Document>[] map,Hits[] hits) 
	throws Exception {

		for (int i = 0; i < hits.length; i++) {
			Hits hits2 = hits[i];
			if (hits2!=null) {
				try {
					for (int j = 0; j < hits2.length(); j++) {
						if (hits2.doc(j) != null) {
							if (map[i] == null) {
								map[i] = new HashMap<String, Document>();
							}
							map[i].put(hits2.doc(j).get("identificador"),
									hits2.doc(j));
						}
					}
				} catch (Exception e) {
					logger.error("ERROR: ", e);
					throw e;
				}
			}
		}

		return map;
	}

	protected ResultadosCountVO handleBusquedaDocsRangoFechaNivelAgregacionIndices(String nivelAgregacion, Calendar fechaDesde, Calendar fechaHasta, String nodo, String Idioma) throws Exception {
		
		logger.info("handleBusquedaDocsRangoFechaNivelAgregacionIndices begins");
		
		if (nivelAgregacion == null || nivelAgregacion.equals(""))
		{
			logger.warn("Error buscando documentos con rango de valoracion. Nivel de agregacion vacio.");
			throw new Exception("Error buscando documentos con rango de valoracion. Nivel de agregacion vacio.");
		}
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
			throw new Exception("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
		}
		if (fechaDesde.after(fechaHasta))
		{
			logger.warn("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error buscando documentos con rango de valoracion. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
			
		BooleanQuery andQuery = new BooleanQuery();
		//		Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		Calendar antes = Calendar.getInstance();
		Calendar despues= Calendar.getInstance();
		antes.setTime(fechaDesde.getTime());
		despues.setTime(fechaHasta.getTime());
		String antesS = "" + antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		String despuesS = "" + despues.get(Calendar.YEAR)+formatMonthMM((despues.get(Calendar.MONTH)+1))+formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		//aniadimos la query con el rango de fechas
		andQuery.add(getRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);
		//aniadimos la query con la valoracion
		andQuery.add(getTermQuery(campo_nivelAgregacion, nivelAgregacion),BooleanClause.Occur.MUST);

		Hits hits;
		IndexSearcher searcher=null;
		ResultadosCountVO resultado = new ResultadosCountVO();
		
		try {
			searcher= new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS) + "/" + nodo + "/index/idioma_" + Idioma); //Ruta del índice
		} catch (Exception e) {
			logger.warn("No se encuentra el índice del nodo <" + nodo + "> con idioma <" + Idioma + ">" + e.getMessage());
			if(searcher!=null)searcher.close();
			resultado.setDocumentosCount(0);
			return resultado;
		}
		
		hits=searcher.search(andQuery, new Sort(new SortField(campo_nivelAgregacion,SortField.STRING,true)));
		
		if (hits != null)
			resultado.setDocumentosCount(hits.length());
		else
			resultado.setDocumentosCount(0);
		
		logger.info("handleBusquedaDocsRangoFechaNivelAgregacionIndices ends");
		return resultado;
	}
	
	protected ResultadosCountVO handleBusquedaDocsNodoFechaArbolCurricularIndices (String idNodoCoberturaCuricular, Calendar fechaDesde, Calendar fechaHasta, String idioma, String idNodo) throws Exception {
		
		logger.info("handleBusquedaDocsNodoFechaArbolCurricularIndices begins");
		
		ResultadosCountVO resultado = new ResultadosCountVO();
		if (idNodoCoberturaCuricular == null || idNodoCoberturaCuricular.equals(""))
		{
			logger.warn("Error buscando nodo de arbol curricular. Nodo vacio.");
			throw new Exception("Error buscando nodo de arbol curricular. Nodo vacio.");
		}
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
			throw new Exception("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
		}
		if (fechaDesde.after(fechaHasta))
		{
			logger.warn("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
			throw new Exception("Error buscando nodo de arbol curricular. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
		}
		BooleanQuery andQuery = new BooleanQuery();
		// Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		Calendar antes = Calendar.getInstance();
		Calendar despues= Calendar.getInstance();
		antes.setTime(fechaDesde.getTime());
		despues.setTime(fechaHasta.getTime());
		String antesS = ""+antes.get(Calendar.YEAR)+formatMonthMM((antes.get(Calendar.MONTH)+1))+formatDayDD(antes.get(Calendar.DAY_OF_MONTH));
		String despuesS = ""+despues.get(Calendar.YEAR)+formatMonthMM((despues.get(Calendar.MONTH)+1))+formatDayDD(despues.get(Calendar.DAY_OF_MONTH));
		//aniadimos la query con el rango de fechas
		andQuery.add(getRangeQuery(campo_fechaPublicacion,antesS,despuesS),BooleanClause.Occur.MUST);

		//aniadimos la query con el id del nodo
		///Con el indice de desarrollo salta a veces una nullPointer
		String idArbolCurricularVig = getIdArbolCurricularVig();
		
		try{
			String nodo = idArbolCurricularVig+SEPARADOR_TAX + idNodoCoberturaCuricular;
			Query termQuery = getTermQuery(campo_taxonomia_nodo, nodo.toLowerCase());
			logger.debug("termQuery: <" + termQuery + ">");	
			andQuery.add(termQuery,BooleanClause.Occur.MUST);
		} catch(Exception e) {
			logger.warn("Exception busqueda por arbol <" + idArbolCurricularVig + "> - <" + e + ">");
		}

		// Utilizamos el idioma seleccionado en la busqueda para buscar el indice sobre el que se ejecuta la query.
		Hits hits = null;
		IndexSearcher searcher = null;
		try {
			searcher= new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)
					+ "/" + idNodo + "/index/idioma_" + idioma); //Ruta del índice
		} catch (Exception e) {
			logger.warn("No se encuentra el índice del nodo <" + idNodo + "> con idioma <" + idioma + ">" + e.getMessage());
			if(searcher!=null)searcher.close();
			resultado.setDocumentosCount(0);
			return resultado;
		}

		try {
			hits=searcher.search(andQuery);

			if (hits != null)
				resultado.setDocumentosCount(hits.length());
			else
				resultado.setDocumentosCount(0);

		} catch (Exception e) {
			logger.warn("Error buscando nodo de arbol curricular. Error buscando en indice con query <"+andQuery.toString()+"> <"+e.getCause()+"> - "+e);
			throw new Exception("Error buscando nodo de arbol curricular. Error buscando en indice con query["+andQuery.toString()+"].",e);
		}

		logger.info("handleBusquedaDocsNodoFechaArbolCurricularIndices ends");
		return resultado;
	}

	protected long handleObtenerCatalogoRepositorioParamIndices(Integer nivelAgregacion, String idNodo, String idioma) throws Exception {
		
		logger.info("handleObtenerCatalogoRepositorioParamIndices begins");
		
		String nivelAgregacionStr ="";
		if (nivelAgregacion == null) {
			throw new Exception("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Nivel de agregacion nulo.");
		}
		else {
			
			try {
				nivelAgregacionStr = Integer.toString(nivelAgregacion);
			} catch (Exception e) {
				logger.warn("Error recuperando repositorio nivel agregacion <"+nivelAgregacion+">. Nivel de agregacion invalido." + e.getMessage());
				throw new Exception("Error recuperando repositorio nivel agregacion["+nivelAgregacion+"]. Nivel de agregacion invalido.",e);
			}
		}

		// Configuramos la query que se va a realizar sobre el indice
		BooleanQuery andQuery = new BooleanQuery();
		// Configuramos el valor de memoria.	
		BooleanQuery.setMaxClauseCount(maxClauseCount);
		// Recogemos las descripciones del objetivo y le metemos la lógica, debe tener el nivel de agregacion mayor o igual que el que me pasan
		andQuery.add(getConstantScoreRangeQuery(campo_nivelAgregacion,nivelAgregacionStr,MAX),BooleanClause.Occur.MUST);

		// Buscamos sobre todos los subindices la query formada anteriormente
		Hits hits = null;
		IndexSearcher searcher=null;
		try {
			searcher= new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+idNodo+"/index/idioma_" + idioma); //Ruta del índice
		} catch (Exception e) {
			logger.warn("No se encuentra el índice del nodo <" + idNodo + "> con idioma <" + idioma + ">" + e.getMessage());
			if(searcher!=null)searcher.close();
			return 0;
		}
		hits=searcher.search(andQuery);
	
		logger.info("handleObtenerCatalogoRepositorioParamIndices ends");
		return new Long(hits.length());
	}

	
	@Override
	protected ResultadosCountVO handleSolicitudDocsTaxonomiaNodo(
			String idNodo, String taxonomia, String idioma) throws Exception {
		
		
		ResultadosCountVO resultado = new ResultadosCountVO();
		Hits[] hits=null;
		
		IndexSearcher searcher = null;
		String[] aTaxonomia= new String[1];
		aTaxonomia[0]=taxonomia;
		HashMap<String, Document>[] map = new HashMap[aTaxonomia.length];
		try {
			searcher = new IndexSearcher(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+ "/"+ idNodo+ "/index/idioma_"+ idioma);					
			hits = NumTermsArbol.obtenerHitsNodos(aTaxonomia, searcher);
			map = deduplicarHits(map, hits);
		} catch (Exception e) {
			logger.error("handleSolicitudDocsTaxonomiaNodo con idioma: "+ idioma+ " Taxonomía: "+taxonomia+ "nodo: " + idNodo, e);
		} finally {
				if (searcher!= null) {
					searcher.close();
					searcher = null;
				}
					
		}

		//Calcular resultado
		resultado.setConteo(new Integer[aTaxonomia.length]);
		int suma=0;
		for (int i=0; i<map.length; i++) {
			if (map[i]!=null) {
				resultado.getConteo()[i]=map[i].size();
				suma=suma+map[i].size();
			} else {
				resultado.getConteo()[i]=0;
			}
		}
		resultado.setDocumentosCount(suma);
		return resultado;
	}
	
}