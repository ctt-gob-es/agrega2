/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.fuentestaxonomicas.negocio.dominio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO;
import es.pode.fuentestaxonomicas.negocio.soporte.EntradaMultiHash;
import es.pode.fuentestaxonomicas.negocio.soporte.EstructuraVdex;
import es.pode.fuentestaxonomicas.negocio.soporte.FiltroIdiomas;
import es.pode.fuentestaxonomicas.negocio.soporte.MultiHash;
import es.pode.fuentestaxonomicas.negocio.soporte.Relaciones;
import es.pode.fuentestaxonomicas.negocio.soporte.Taxon;

public class EstructurasDaoImpl implements EstructurasDaoInterface{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private Properties properties = null;

	private static Map<String, EstructuraVdex> hashMapTaxonomias;
	private static Map<String, String> indiceTaxonomias;
	private static Map<String, String> conversionVocabIdentifier;
	private static MultiHash indiceTaxonomiasID;
	
//	private static EstructuraVdex arbolCurricularVigente=null;
//	private static EstructuraVdex tesauroVigente=null;
	private static String arbolCurricularVigente=null;
	private static String tesauroVigente=null;
	
	public EstructurasDaoImpl() 
	{
		try{
			cargarTaxonomias();
			cargarTesauros();
			recargarIndiceTraduccion();
			
		}catch (Exception e) {
			logger.error("Error en el constructor de EstructurasDao del módulo Fuentes Taxonómicas: - " , e);
		}
	}

	private String getPropertyValue(
			String sKey) 
	throws IOException 
	{
		InputStream fIsProperties = this.getClass().getResourceAsStream("/fuentestaxonomicas.properties");
		if (this.properties == null) {
			properties = new java.util.Properties();
			properties.load(fIsProperties);
		}
		fIsProperties.close();
		if (logger.isDebugEnabled())
			logger.debug("getPropertyValue: Propiedad recuperada: " + sKey + " : "+ properties.getProperty(sKey));
//		 devolvemos la propiedad
		return properties.getProperty(sKey);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Método publico que permite recargar en memoria los arboles curriculares y taxonomias de la plataforma.
	 * 
	 *  @throws Exception
	 */
	public void recargarTaxonomias() throws Exception
	{
		cargarTaxonomias();
		recargarIndiceTraduccion();
	}
	
	/**
	 * Carga las Taxonomias de la carpeta arbolcurricular y taxonomias
	 * 
	 * @throws Exception
	 */
	private  void cargarTaxonomias()
	throws Exception
	{
		try {
			hashMapTaxonomias = Collections.synchronizedMap(new HashMap<String, EstructuraVdex>());
			indiceTaxonomias= Collections.synchronizedMap(new HashMap<String, String>());
			indiceTaxonomiasID = new MultiHash();

//			tomamos el contenido de la carpeta en la lista
			List<File> ficheros = new ArrayList<File>();
			
			File rutaArboles= new File( getPropertyValue("rutaArbolCurricular"));			
			if(rutaArboles.exists() && rutaArboles.isDirectory())
				ficheros.addAll(Arrays.asList(rutaArboles.listFiles()));

			File rutaTaxonomias= new File(getPropertyValue("rutaTaxonomia"));
			if(rutaTaxonomias.exists() && rutaTaxonomias.isDirectory())
				ficheros.addAll(Arrays.asList(rutaTaxonomias.listFiles()));
			
	        for(int i=0;i<ficheros.size();i++){
	        	File docFile = ficheros.get(i);
	        	Document doc = null;
	        	try{
	          		SAXBuilder builder = new SAXBuilder(false);
	        		doc = builder.build(docFile);
	        		Element raiz = doc.getRootElement();
	        		Namespace nameSpace = raiz.getNamespace();
	        		Element vocab=raiz.getChild("vocabName", nameSpace);
	        		String vocabName=vocab.getChildText("langstring", nameSpace);
	        		Element ElVvocabIdentifier=raiz.getChild("vocabIdentifier", nameSpace);
	        		String strVocabIdentifier="";
	        		if(ElVvocabIdentifier!=null)
	        			strVocabIdentifier=ElVvocabIdentifier.getText();
	        		
	        		if(vocabName!=null && !vocabName.equals("") 
	        		   && strVocabIdentifier!=null && !strVocabIdentifier.equals(""))
	        		{
		        		HashMap<String, Object> hasmap = new HashMap<String, Object>();
		        		hashMapDeUnArbol(raiz, nameSpace,hasmap);
		        		EstructuraVdex estructura=new EstructuraVdex();
		        		estructura.setVocabName(vocabName);
		        		estructura.setVocabIdentifier(strVocabIdentifier);
		        		estructura.setHashMap(hasmap);
		        		hashMapTaxonomias.put(docFile.getName(),estructura);

		        		String idioma= docFile.getName().substring(docFile.getName().length() - "es.xml".length() , docFile.getName().length() - ".xml".length());
		        		
		        		indiceTaxonomias.put(idioma + estructura.getVocabIdentifier(), docFile.getName());
		        		indiceTaxonomias.put(idioma + estructura.getVocabName(), docFile.getName());
		        		indiceTaxonomiasID.put(estructura.getVocabIdentifier(),estructura.getVocabName(), docFile.getName());

		        		logger.debug("Se ha cargado con exito el fichero " + docFile.getName() );

	        		}else{
	        			logger.warn("\n\nEl fichero " + docFile.getName() + " no posee vocabName o vocabIdentifier, estos campos son obligatorios para la aplicación. \nNO SE CARGARÁN EN LA APLICACIÓN");
	        		}
	        		
	        		
	        		
	        	}catch (Exception e) {
	        		logger.error("Error en parseo sax - ", e);
	    			throw new RuntimeException(e);
	    		}
	        }
	          
	        arbolCurricularVigente = (hashMapTaxonomias.get(obtenerArbolcurricularAgrega("es"))).getVocabIdentifier(); 
	        
		} catch (IOException e) {
			logger.error("Error: Fichero no encontrado: /fuentestaxonomicas.properties - ", e);
			throw new RuntimeException(e);
		}
        
	}
	

	/**
	 * Crea la hashMap del árbol
	 * @param raiz La raiz del árbol
	 * @param nameSpace El namespace 
	 * @param hasmap Una hashMap
	 */
	private static void hashMapDeUnArbol(
			Element raiz, 
			Namespace nameSpace,
			HashMap<String, Object> hasmap)
	{
		if( hasmap.isEmpty() ) {
			
			Taxon taxonRaiz = new Taxon();
			taxonRaiz.setId("vdex");
			taxonRaiz.setPadre(null);
			taxonRaiz.setValorTax("vdex");
			ArrayList<String> hijosRaiz = new ArrayList<String>();
			List<?> hijos=raiz.getChildren("term", nameSpace);
			Iterator<?> i = hijos.iterator();
			while (i.hasNext()){
				Element e = (Element)i.next();
				hijosRaiz.add(e.getChildText("termIdentifier",nameSpace));
			}
			taxonRaiz.setHijos(hijosRaiz);
			hasmap.put(taxonRaiz.getId(), taxonRaiz);
		}
			List<?> hijos=raiz.getChildren("term", nameSpace);
	        Iterator<?> i = hijos.iterator();
	        while (i.hasNext()){
	        	Taxon taxonAuxiliar = new Taxon();
	        	taxonAuxiliar.setPadre(raiz.getChildText("termIdentifier", nameSpace));
	        	Element e = (Element)i.next();
	        	String key = e.getChildText("termIdentifier",nameSpace);
	        	taxonAuxiliar.setId(e.getChildText("termIdentifier",nameSpace));
	        	
	        	Element listaCaption = e.getChild("caption",nameSpace);
	        	Element listaAmbito=e.getChild("description",nameSpace);
	        	
	        	String textoCaption=listaCaption.getChildText("langstring", nameSpace);
	        	String textoAmbito=null;
	        	if(listaAmbito!=null){
		        	textoAmbito=listaAmbito.getChildText("langstring", nameSpace);
		        	taxonAuxiliar.setAmbito(textoAmbito.trim());
		        	
		        	if(textoAmbito !=null){	        	
			        	taxonAuxiliar.setValorTax(textoCaption+ " ("+textoAmbito+")");
		        	}
	        	}
	        	else
	        		taxonAuxiliar.setValorTax(textoCaption);
	        	
	        	
	        	List<?> listaHijos = e.getChildren("term",nameSpace);
	        	ArrayList<String> arrayHijos = hijosDeNodo(listaHijos,nameSpace);
	        	taxonAuxiliar.setHijos(arrayHijos);

	        	hasmap.put(key,taxonAuxiliar);  
	        	hashMapDeUnArbol(e, nameSpace, hasmap);
	        }
	        
		               
	}
	
	
	/**
	 * Devuelve los nombres de los identificadores que le pasamos como parámetro
	 * 
	 * @param listaHijos Lista de nombres
	 * @param name El namespace
	 * @return arrayHijos Un arrayList de los identificadores de esos nombres
	 */
	private static ArrayList<String> hijosDeNodo(
			List<?> listaHijos, 
			Namespace name)
	{
		ArrayList<String> arrayHijos = new ArrayList<String>();
		Iterator<?> iter = listaHijos.iterator();
		while ( iter.hasNext()){
			Element ele = (Element)iter.next();
			arrayHijos.add(ele.getChildText("termIdentifier",name));
		}
		return arrayHijos;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Método publico que permite recargar tanto las taxonomias como los tesauros.
	 */
	public void recargarDatos()
	throws Exception
	{
		cargarTaxonomias();
		cargarTesauros();
		recargarIndiceTraduccion();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * método publico que permite obtener una taxonomía dando como parámetro el nombre del fichero xml
	 * 
	 */
	public EstructuraVdex obtenerTaxonomia(
			String key) 
	throws Exception {
		return hashMapTaxonomias.get(key);
		
	}
	
	/**
	 * método publico que permite obtener una taxonomía dando como parámetros el nombre y el idioma
	 * de la taxonomía que se requiere. el nombre puede ser tanto el vocabname, el vocabidentifier o 
	 * una cadena de texto indicando el arbolcurricular_agrega
	 */
	public EstructuraVdex obtenerTaxonomia(
			String nombre, 
			String idioma)
	throws Exception 
	{
		String nombreTax=getPropertyValue("arbolCurricular");
		if(nombreTax.equals(nombre))
		{
			return hashMapTaxonomias.get(obtenerKeyTaxonomias(arbolCurricularVigente, idioma));
		}
		return hashMapTaxonomias.get(obtenerKeyTaxonomias(nombre, idioma));
	}

	/**
	 * método publico que permite obtener el nombre del fichero xml de la taxonomía indicada por 
	 * el identificador y el idioma dados.
	 * 
	 * @param identificador String que indica el vocabidentifier de la taxonomia
	 * @param idioma String que indica el idioma iso de la taxonomía
	 * 
	 * @throws Exception
	 */
	public String obtenerNombreFicheroTaxonomia(
			String identificador,
			String idioma)
	throws Exception
	{
		return indiceTaxonomias.get(idioma+identificador);
	}
	
	/**
	 * 
	 * @param nomTaxonomia
	 * @param idioma
	 * @return
	 * @throws Exception
	 */
	    private String obtenerKeyTaxonomias(
	    		String nomTaxonomia, 
	    		String idioma)
		throws Exception 
		{
	    	String resultado=null;

			resultado=new StringBuilder(nomTaxonomia).append("_").append(idioma).append(".xml").toString(); 
			if(!hashMapTaxonomias.containsKey(resultado)	)
			{
				resultado= indiceTaxonomias.get(idioma+nomTaxonomia);
			}
			
			return resultado;
		}

	    
		private String obtenerArbolcurricularAgrega(
				String idioma) 
		throws Exception
		{
			String resultado= "";
			
			File rutaArboles= new File(getPropertyValue("rutaArbolCurricular"));
			String[] nombresFicheros;
			if(rutaArboles.exists() && rutaArboles.isDirectory())
			{
				// listo los ficheros en la carpeta arbol curricular filtrando por el idioma solicitado
				nombresFicheros = rutaArboles.list(new FiltroIdiomas(idioma));
				//si no existía el idioma solicitado, listo por dioma español, que debe existir siempre
				if(nombresFicheros.length==0)
					nombresFicheros= rutaArboles.list(new FiltroIdiomas("es"));
				if(nombresFicheros.length>0)
				{
					resultado = nombresFicheros[0];
					if(nombresFicheros.length>1)
						logger.warn("existe mas de un fichero para el idioma <" + idioma + "> en la carpeta de arboles curriculares");
				}
			}
			
			return resultado;
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public EstructuraVdexVO[] obtenerTaxonomias(
			String idioma) 
	throws Exception 
	{
		ArrayList<EstructuraVdexVO> resultado= new ArrayList<EstructuraVdexVO>();
		try{
				String clave;
				for(Iterator<String> it= hashMapTaxonomias.keySet().iterator(); it.hasNext();)
				{
					clave= it.next();
					if(clave.endsWith(idioma + ".xml"))
					{
						EstructuraVdex entrada= hashMapTaxonomias.get(clave);
						
						EstructuraVdexVO salida= new EstructuraVdexVO();
						salida.setVocabName(entrada.getVocabName());
						salida.setVocabIdentifier(entrada.getVocabIdentifier());
//						salida.setTaxones(obtenerPrimerNivelDeTaxones(entrada.getHashMap(), entrada.getVocabName()));
						salida.setIdioma(idioma);
						resultado.add(salida);
					}
					
				}
			return resultado.toArray(new EstructuraVdexVO[0]);
			
		}catch (Exception e) {
			logger.error("error al buscar el vocabIdentifier de un vocabName: - " , e);
			resultado= null;
		}
		return null;
	}


	public EstructuraVdexVO[] obtenerTaxonomias() 
	throws Exception 
	{
		ArrayList<EstructuraVdexVO> resultado= new ArrayList<EstructuraVdexVO>();
		try{
			EntradaMultiHash[] entradas = indiceTaxonomiasID.getAll();
			for (int i = 0; i < entradas.length; i++) {
				EstructuraVdexVO salida= new EstructuraVdexVO();
				salida.setVocabName(entradas[i].getNombre());
				salida.setVocabIdentifier(entradas[i].getIdentificador());
				resultado.add(salida);
			}

			return resultado.toArray(new EstructuraVdexVO[0]);
			
		}catch (Exception e) {
			logger.error("error al buscar el vocabIdentifier de un vocabName: - " , e);
			resultado= null;
		}
		return null;
	}

	
//	private TaxonVO[] obtenerPrimerNivelDeTaxones(
//			HashMap listadoHijos, 
//			String vocabName)
//	throws Exception
//	{
//		List<TaxonVO> taxones = new ArrayList<TaxonVO>();
//		try{
//			for (Iterator iterator = listadoHijos.entrySet().iterator(); iterator.hasNext();) {
//				Taxon taxon = (Taxon) ((Entry) iterator.next()).getValue();
//				if(taxon.getPadre()==null && !taxon.getId().equals("vdex") &&  !taxon.getValorTax().equals("vdex"))
//				{	
//					TaxonVO taxVO = new TaxonVO();
//					taxVO.setId(taxon.getId().trim());
//					taxVO.setValorTax(taxon.getValorTax().trim());
//					taxVO.setEsHoja(Boolean.valueOf(taxon.getHijos().isEmpty()));
//					taxVO.setVocabName(vocabName);
//					taxones.add(taxVO);
//				}
//			}
//		}catch (Exception e) {
//			logger.error("error en el metodo obtenerPrimerNiveldeTaxones en SrvTaxonomiaService" , e);
//			return null;
//		}
//		return (TaxonVO[]) taxones.toArray(new TaxonVO[0]);
//	}

	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String[] traducirVocabNames(
			String[] ids) 
	throws Exception 
	{
		ArrayList<String> resultado =new ArrayList<String>();
		String vocabIdentifier;
		String[] lista;
		String separador="/";
		try{
			for (int i = 0; i < ids.length; i++) {
				lista= ids[i].split(separador);
				if(lista.length>0)
				{
					vocabIdentifier =  conversionVocabIdentifier.get(lista[0]);
					if(vocabIdentifier!=null)
					{
						resultado.add(ids[i].replaceFirst(lista[0], vocabIdentifier));
					}
				}
			}
		}catch (Exception e) {
			logger.error("Error en metodo ObtenerIdsConVocabIdentifier: - " , e);
			resultado= new ArrayList<String>();
		}
		return resultado.toArray(new String[0]);
	}

	
	private void recargarIndiceTraduccion()
	{
		conversionVocabIdentifier = new HashMap<String, String>();
		EstructuraVdex estructura; 
		for (Iterator<EstructuraVdex> iterator = hashMapTaxonomias.values().iterator(); iterator.hasNext();) {
			estructura= iterator.next();
			conversionVocabIdentifier.put(estructura.getVocabName(), estructura.getVocabIdentifier());
		}

		for (Iterator<EstructuraVdex> iterator = hashMapTerm.values().iterator(); iterator.hasNext();) {
			estructura= iterator.next();
			conversionVocabIdentifier.put(estructura.getVocabName(), estructura.getVocabIdentifier());
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Map<String, EstructuraVdex> hashMapTerm;
//	tabla hash, con key igual nombreTesauro_idioma, que
//	contiene otras tablas hash,una o mas por cada tesauro según en 
//	los idioma en los que este,esta otras tablas tienen como key el
//	texto del termino y como valor su identificador

	private static Map<String, EstructuraVdex> hashMapId ;
	//igual que la anterior con la diferencia que las tablas hash que
	//contiene tienen como key el id del termino y como valor su texto
	
	private static Map<String, EstructuraVdex> hashMapRelaciones ;
	//tabla hash, con key igual al nombre del Tesauro(una sola
	//tabla independientemente del número de idioma que tengamos), que
	//contiene otras tablas hash con las relaciones entre los términos del
	//del tesauro, que tienen como key el identificador de cada término
	//y como valor un objeto del tipo Relaciones que contiene las relaciones
	//asociativas y jerárquicas del término y todos sus padres
	
	private static Map<String, String> indiceTesauros;
	private static MultiHash indiceTesaurosID;
	
    private void cargarTesauros() 
    throws Exception
    {
    	//carga los tesauros disponibles en las tablas hash
		try {
			hashMapTerm = Collections.synchronizedMap( new HashMap<String, EstructuraVdex>());
			hashMapId = Collections.synchronizedMap(new HashMap<String, EstructuraVdex>());
			hashMapRelaciones = Collections.synchronizedMap(new HashMap<String, EstructuraVdex>());
			indiceTesauros = Collections.synchronizedMap(new HashMap<String, String>());
			indiceTesaurosID= new MultiHash();
			
//			tomamos el contenido de la carpeta en la lista
			List<File> ficheros = new ArrayList<File>();
			File rutaArboles= new File(getPropertyValue("rutaTesauro"));
			if(rutaArboles.exists() && rutaArboles.isDirectory())
				ficheros.addAll(Arrays.asList(rutaArboles.listFiles()));

			File rutaTaxonomias= new File(getPropertyValue("rutaTesauroBackup"));
			if(rutaTaxonomias.exists() && rutaTaxonomias.isDirectory())
				ficheros.addAll(Arrays.asList(rutaTaxonomias.listFiles()));
	        
	        for(int i=0;i<ficheros.size();i++){
	        	File docFile = ficheros.get(i);

	        	String nombreFich = ficheros.get(i).getName();
	        	Document doc = null;
	        	try{
	          		SAXBuilder builder = new SAXBuilder(false);
	        		doc = builder.build(docFile);
	        		Element raiz = doc.getRootElement();
	        		Namespace nameSpace = raiz.getNamespace();
	        		Element vocab=raiz.getChild("vocabName", nameSpace);
	        		String vocabName=vocab.getChildText("langstring", nameSpace);
	        		Element ElVvocabIdentifier=raiz.getChild("vocabIdentifier", nameSpace);
	        		String strVocabIdentifier="";
	        		if(ElVvocabIdentifier!=null)
	        			strVocabIdentifier=ElVvocabIdentifier.getText();
	        		
	        		
	        		
	        		if(vocabName!=null && !vocabName.equals("") 
	 	        		   && strVocabIdentifier!=null && !strVocabIdentifier.equals(""))
	 	        	{
	        		
		        		HashMap<String, Object> hasmapId = new HashMap<String, Object>();
		        		HashMap<String, Object> hasmapTerm = new HashMap<String, Object>();
		        		HashMap<String, Object> hasmapRelacionesFich = new HashMap<String, Object>();
		        		int posi=nombreFich.lastIndexOf("_");
		        		String nombreTesauro=nombreFich.substring(0, posi);
		        		boolean crearRelaciones = hashMapRelaciones.containsKey(nombreTesauro);
		        		//creamos las hashMap para cada fichero
		        		crearHashMaps(raiz, nameSpace,hasmapId,hasmapTerm,hasmapRelacionesFich,!crearRelaciones);
		        		EstructuraVdex estructuraId=new EstructuraVdex();
		        		estructuraId.setVocabName(vocabName);
		        		estructuraId.setVocabIdentifier(strVocabIdentifier);
		        		
		        		estructuraId.setHashMap(hasmapId);
		        		hashMapId.put(nombreFich,estructuraId);		
		        		EstructuraVdex estructuraTerm=new EstructuraVdex();
		        		estructuraTerm.setVocabName(vocabName);
		        		estructuraTerm.setVocabIdentifier(strVocabIdentifier);
		        		estructuraTerm.setHashMap(hasmapTerm);
		        		hashMapTerm.put(nombreFich,estructuraTerm);
		        		
		        		String idioma= docFile.getName().substring(docFile.getName().length() - "es.xml".length() , docFile.getName().length() - ".xml".length());
		        		
		        		indiceTesauros.put(idioma + estructuraTerm.getVocabIdentifier(), docFile.getName());
		        		indiceTesauros.put(idioma + estructuraTerm.getVocabName(), docFile.getName());
		        		indiceTesaurosID.put(estructuraTerm.getVocabIdentifier(), estructuraTerm.getVocabName(), docFile.getName());
	
		        		if (!crearRelaciones){
		        			//si tenemos el tesauro en varios idiomas solo generaremos la relaciones una vez
		        			hashMapDeRelacion(raiz, nameSpace,hasmapRelacionesFich);	
		        			EstructuraVdex estructuraRelaciones=new EstructuraVdex();
		        			estructuraRelaciones.setVocabName(vocabName);
		        			estructuraRelaciones.setVocabIdentifier(strVocabIdentifier);
		        			estructuraRelaciones.setHashMap(hasmapRelacionesFich);
		        			hashMapRelaciones.put(nombreTesauro, estructuraRelaciones);
		        		}

		        		logger.debug("Se ha cargado con exito el fichero " + docFile.getName() );

	        		}else{
	        			logger.warn("\nnEl fichero " + docFile.getName() + " no posee vocabName o vocabIdentifier, estos campos son obligatorios para la aplicación. \nNO SE CARGARÁN EN LA APLICACIÓN");
	        		}
	        		
	        	}catch (Exception e) {
	        		logger.error("Error en parseo sax - ", e);
	    			throw new RuntimeException(e);
	    		}
	        }

	        tesauroVigente =( hashMapTerm.get(obtenerTesauroAgrega("es"))).getVocabIdentifier();

		} catch (IOException e) {
			logger.error("Error: Fichero no encontrado: /xml.properties - ", e);
			throw new RuntimeException(e);
		}
	}

	
    //Se generan una HasMap que contiene los terminos(caption) como key y los identificadores como value, otra HashMap
	//con los identificadores como key y los terminos(caption) como value y otra HashMap para la relaciones con los
	//values inicializados, los cuales se completaran en otro metodo
    private static void crearHashMaps(
    		Element raiz, 
    		Namespace nameSpace,
    		HashMap<String, Object> hasmapId,
    		HashMap<String, Object> hasmapTerm,
    		HashMap<String, Object> hasmapRelaciones,
    		boolean creaRelaciones)
    {
        
		if( (hasmapId.isEmpty())&&(hasmapTerm.isEmpty()) ) {
			//obtenemos todos los terminos
			List<?> hijos=raiz.getChildren("term", nameSpace);
			Iterator<?> i = hijos.iterator();
			//recorremos todos los terminos y los metemos en las HashMap
			int x = 0;
			boolean fin=false;
			while (i.hasNext()){
				Element e = (Element)i.next();
				//obtenemos el identificador del término
				String ident=e.getChildText("termIdentifier",nameSpace);
				Element listaCaption = e.getChild("caption",nameSpace);
				//obtenemos el texto del término
				String textoCaption=listaCaption.getChildText("langstring", nameSpace);
				//añadimos el id y su texto a la tabla de ids
				hasmapId.put(ident, textoCaption);
				
				//comprabamos que el texto del termino no ha sido introducido ya en la hash, si no esta lo añadimos
				// si ya esta lo redefinimos como agrega_num_textoTermino, pues tenemos otro termino con el mismo texto
				//y distinto id
				if(hasmapTerm.containsKey(textoCaption))
					while(!fin){
						x++;
						if(!hasmapTerm.containsKey("agrega_"+ x +"_"+textoCaption)){
							hasmapTerm.put("agrega_"+ x +"_"+textoCaption, ident);
							fin = true;
						}	
					}
					
				else
					//añadimos el texto y su id a la tabla de terminos
					hasmapTerm.put(textoCaption, ident);
				fin= false;
				x=0;
				//si la tabla de relaciones para este tesauro no han sido inicializada, la inicializamos
				if (creaRelaciones){
					Relaciones relaciones = new Relaciones();
					hasmapRelaciones.put(ident, relaciones);
				}
					
			}
	        
		}	
		
    }

    /**
     * Genera la HashMap que contiene todas las relaciones, tanto jerarquicas como asociativas
     * @param raiz La raiz del vdex
     * @param nameSpace El namespace del vdex
     * @param  hasmapRelaciones HashMap que contiene todas las relaciones
     */
    private static void hashMapDeRelacion(
    		Element raiz, 
    		Namespace nameSpace,
    		HashMap<String, Object> hasmapRelaciones)
    {
    //Se completa la HashMap que contiene las relaciones entre los terminos del tesauro, los valores son objetos
    //de la clase Relaciones que contiene un List con las relaciones asociativas,otro con las relaciones jerarquicas y 
    //otro con todos sus padres
        
    	if(!hasmapRelaciones.isEmpty()) {
    		//obtenemos todas las relaciones
    		List<?> relaciones=raiz.getChildren("relationship", nameSpace);
			Iterator<?> j=relaciones.iterator();
		
			while (j.hasNext()){
				Element l = (Element) j.next();
				String fuente=l.getChildText("sourceTerm", nameSpace);
				String destino=l.getChildText("targetTerm", nameSpace);
				String tipoRelacion=l.getChildText("relationshipType", nameSpace);

				Relaciones rFuente = (Relaciones) hasmapRelaciones.get(fuente);
				Relaciones rDestino = (Relaciones) hasmapRelaciones.get(destino);
				
				if(tipoRelacion.startsWith("TE")){
					if (!rFuente.getJerarquicas().contains(destino))
						rFuente.getJerarquicas().add(destino);
					if(!rDestino.getIdPadres().contains(fuente))
						rDestino.getIdPadres().add(fuente);
				}
				if(tipoRelacion.startsWith("TR")){
					if (!rFuente.getAsociativas().contains(destino))
						rFuente.getAsociativas().add(destino);
					if (!rDestino.getAsociativas().contains(fuente))
						rDestino.getAsociativas().add(fuente);
				}
			}

		}
	}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public EstructuraVdex obtenerTerminoTesauro(
			String nomTesauro, 
			String idioma)
	throws Exception 
	{
//    	String key="";
		String nombreTes=getPropertyValue("tesauroETB");
		if(nombreTes.equals(nomTesauro)){
//			key = tesauroVigente.getVocabIdentifier();
			return hashMapTerm.get(obtenerKeyTesauros(tesauroVigente, idioma));
		}
		return hashMapTerm.get(obtenerKeyTesauros(nomTesauro, idioma));
	}

    
    public EstructuraVdex obtenerIdTesauro(
			String nomTesauro, 
			String idioma)
	throws Exception 
	{
//    	String key="";
		String nombreTes=getPropertyValue("tesauroETB");
		if(nombreTes.equals(nomTesauro)){
//			key = tesauroVigente.getVocabIdentifier();
			return hashMapId.get(obtenerKeyTesauros(tesauroVigente, idioma));
		}
		return hashMapId.get(obtenerKeyTesauros(nomTesauro, idioma));
	}
    
    public EstructuraVdex obtenerRelacionesTesauro(
    		String nomTesauro,
    		String idioma)	
    throws Exception
    {
    	String nombreTes=getPropertyValue("tesauroETB");
    	String keyRelaciones= "";
    	
    	if(nombreTes.equals(nomTesauro))
    		keyRelaciones = indiceTesauros.get(idioma+tesauroVigente);
    	else
    		keyRelaciones = indiceTesauros.get(idioma+nomTesauro);
    	
    	if(keyRelaciones!=null)
    	{
    		keyRelaciones = keyRelaciones.substring(0, keyRelaciones.lastIndexOf("_"));
    	}else
    	{
    		keyRelaciones = nomTesauro; 
    	}
		return hashMapRelaciones.get(keyRelaciones);
    }
    
    
    
    
	public EstructuraVdex obtenerTesauro(
			String key) 
	throws Exception 
	{
		return hashMapId.get(key);
		
	}

	/**
	 * @param nomTaxonomia
	 * @param idioma
	 * @return
	 * @throws Exception
	 */
	    private String obtenerKeyTesauros(
	    		String nombre, 
	    		String idioma)
		throws Exception 
		{
	    	String resultado=null;
			resultado=new StringBuilder(nombre).append("_").append(idioma).append(".xml").toString(); 
			
			if(!hashMapTerm.containsKey(resultado)	)
			{
				resultado= indiceTesauros.get(idioma+nombre);
			}
			
			return resultado;
		}

		private String obtenerTesauroAgrega(
				String idioma) 
		throws Exception
		{
			String resultado= "";
			File rutaTesauros= new File(getPropertyValue("rutaTesauro"));
			String[] nombresFicheros;
			if(rutaTesauros.exists() && rutaTesauros.isDirectory())
			{
				// listo los ficheros en la carpeta arbol curricular filtrando por el idioma solicitado
				nombresFicheros = rutaTesauros.list(new FiltroIdiomas(idioma));
				//si no existía el idioma solicitado, listo por dioma español, que debe existir siempre
				if(nombresFicheros.length==0)
					nombresFicheros= rutaTesauros.list(new FiltroIdiomas("es"));
				if(nombresFicheros.length>0)
					resultado = nombresFicheros[0];
			}
			
			return resultado;
		}

	public EstructuraVdexVO[] obtenerTesauros(
				String idioma)
	throws Exception 
	{
		ArrayList<EstructuraVdexVO> resultado= new ArrayList<EstructuraVdexVO>();
		try{
			String clave;
			for(Iterator<String> it= hashMapId.keySet().iterator(); it.hasNext();)
			{
				clave= it.next();		
				if(clave.endsWith(idioma + ".xml"))
				{
					EstructuraVdex entrada= hashMapId.get(clave);
					
					EstructuraVdexVO salida= new EstructuraVdexVO();
					salida.setVocabName(entrada.getVocabName());
					salida.setVocabIdentifier(entrada.getVocabIdentifier());
					salida.setIdioma(idioma);
					resultado.add(salida);
				}
				
			}

		}catch (Exception e) {
			logger.error("error al buscar el vocabIdentifier de un vocabName: - " , e);
			resultado= new ArrayList<EstructuraVdexVO>();
		}
		return resultado.toArray(new EstructuraVdexVO[0]);	
	}

	public EstructuraVdexVO[] obtenerTesauros()
	throws Exception 
	{
		ArrayList<EstructuraVdexVO> resultado= new ArrayList<EstructuraVdexVO>();
		try{
			EntradaMultiHash[] entradas= indiceTesaurosID.getAll();
			for (int i = 0; i < entradas.length; i++) {
				EstructuraVdexVO salida= new EstructuraVdexVO();
				salida.setVocabName(entradas[i].getNombre());
				salida.setVocabIdentifier(entradas[i].getIdentificador());
				resultado.add(salida);
				
			}
		}catch (Exception e) {
			logger.error("error al buscar el vocabIdentifier de un vocabName: - " , e);
			resultado= new ArrayList<EstructuraVdexVO>();
		}
		return resultado.toArray(new EstructuraVdexVO[0]);	
	}
		
		
	public void recargarTesauros() 
	throws Exception {
		cargarTesauros();
		recargarIndiceTraduccion();
	}
	
	
	public String obtenerNombreFicheroTesauros(
			String identificador,
			String idioma)
	throws Exception
	{
		return indiceTesauros.get(idioma+identificador);
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

	public VdexListaVO[] obtenerFicherosTaxonomias()
	throws Exception
	{
		List<VdexListaVO> resultado=new ArrayList<VdexListaVO>();
		EntradaMultiHash[] entradas = indiceTaxonomiasID.getAll();
		
		for (int i = 0; i < entradas.length; i++) 
		{
			VdexListaVO vlista= new VdexListaVO();
			vlista.setIdentificador(entradas[i].getIdentificador());
			vlista.setListaFicheros(entradas[i].getFicheros());
			vlista.setNombre(entradas[i].getNombre());
			
			if(entradas[i].getIdentificador().equals(arbolCurricularVigente))
			{
				vlista.setTipo(TipoVdex.ARBOL.toString());
				resultado.add(0, vlista);
			}
			else
			{
				vlista.setTipo(TipoVdex.TAXONOMIA.toString());
				resultado.add(vlista);
			}
			
		}

		return resultado.toArray(new VdexListaVO[0]);
	}
		
	
	public VdexListaVO obtenerTaxonomiaPorId(
			String identificador)
	throws Exception
	{
		VdexListaVO vlista=null;
		EntradaMultiHash e= indiceTaxonomiasID.get(identificador);
		if(e!=null)
		{
			vlista= new VdexListaVO();
			vlista.setIdentificador(e.getIdentificador());
			vlista.setListaFicheros(e.getFicheros());
			vlista.setNombre(e.getNombre());
			if(e.getIdentificador().equals(arbolCurricularVigente))
			{
				vlista.setTipo(TipoVdex.ARBOL.toString());
			}
			else
			{
				vlista.setTipo(TipoVdex.TAXONOMIA.toString());
			}
		}
		
		return vlista;
	}

	
	public VdexListaVO[] obtenerFicherosTesauros()
	throws Exception
	{
		List<VdexListaVO> resultado=new ArrayList<VdexListaVO>();

//		String idTesVigente= hashMapId.get( obtenerTesauroAgrega("es")).getVocabIdentifier();
		
		EntradaMultiHash[] entradas = indiceTesaurosID.getAll();
		
		for (int i = 0; i < entradas.length; i++) 
		{
			VdexListaVO vlista= new VdexListaVO();
			vlista.setIdentificador(entradas[i].getIdentificador());
			vlista.setListaFicheros(entradas[i].getFicheros());
			vlista.setNombre(entradas[i].getNombre());
			
			if(entradas[i].getIdentificador().equals(tesauroVigente))
			{
				vlista.setTipo(TipoVdex.TESAURO.toString());
				resultado.add(0, vlista);
			}
			else
			{
				vlista.setTipo(TipoVdex.TESAURO_BACK.toString());
				resultado.add(vlista);
			}
			
		}

		return resultado.toArray(new VdexListaVO[0]);
	}
		
	
	public VdexListaVO obtenerTesauroPorId(
			String identificador)
	throws Exception
	{
		VdexListaVO vlista=null;
//		String idTesVigente= hashMapId.get( obtenerTesauroAgrega("es")).getVocabIdentifier();
		
		EntradaMultiHash e= indiceTesaurosID.get(identificador);
		if(e!=null)
		{
			vlista= new VdexListaVO();
			vlista.setIdentificador(e.getIdentificador());
			vlista.setListaFicheros(e.getFicheros());
			vlista.setNombre(e.getNombre());
			if(e.getIdentificador().equals(tesauroVigente))
			{
				vlista.setTipo(TipoVdex.TESAURO.toString());
			}
			else
			{
				vlista.setTipo(TipoVdex.TESAURO_BACK.toString());
			}
		}
		
		return vlista;
	}

	public String existeVocabIdentifier(
			String identificador) 
	throws Exception 
	{
		
		EntradaMultiHash e =null;
		e= indiceTaxonomiasID.get(identificador);
		if(e!=null)
			return e.getNombre();
		e= indiceTesaurosID.get(identificador);
		if(e!=null)
			return e.getNombre();
		return null;
	}

	public String existeVocabName(
			String vocabName)
	throws Exception 
	{
		return conversionVocabIdentifier.get(vocabName);
	}

	public String obtenerNombreFicheroPorId(
			String identificador, 
			String idioma)
	throws Exception 
	{
		String ficheroExistente=null;
		ficheroExistente=obtenerKeyTesauros(identificador, idioma);
		if(ficheroExistente==null)
			ficheroExistente= obtenerKeyTaxonomias(identificador, idioma);
			
		return ficheroExistente;
	}

}
