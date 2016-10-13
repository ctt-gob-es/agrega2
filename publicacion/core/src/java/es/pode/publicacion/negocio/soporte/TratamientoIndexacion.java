/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.soporte;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.indexador.negocio.servicios.indexado.ContribucionVO;
import es.pode.indexador.negocio.servicios.indexado.EntidadVO;
import es.pode.indexador.negocio.servicios.indexado.IdODEVO;
import es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.lomes.lomesAgrega.AccesoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ContribucionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EducationalAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EntidadAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LangStringAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RightsAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega;
import es.pode.soporte.utiles.date.DateManager;



public class TratamientoIndexacion {
// análoga a documento indexación, cuando termine la migración borrar
	
	private static Logger logger = Logger.getLogger(TratamientoIndexacion.class);
	public static String INDEXACION_SEPARATOR = "-";
	public static String AMBITO_UNIVERSAL = "universal";
	public static String AMBITO_NON_UNIVERSAL = "non-universal";
	private static final String IDENTIFICADOR_NODO = "server.id";
	
	//tokeniza un list de strings de strings
	
//	public static IdODEVO RellenarCamposPrincipales(IdODEVO idODEVOdoc, Lom lomes, , SrvNodoService nodos, SrvTesaurosServices tesauros)
	public static IdODEVO RellenarCamposPrincipales(IdODEVO idODEVOdoc, Lom lomes, SrvEstructurasEducativasService estructuras, String[] nodosLocales, SrvTaxonomiaService taxonomias)
		throws Exception 
	{
		if (lomes == null)
		{
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("ERROR: RellenarCamposPrincipales imposible utilizar lom-es vacio para extraer informacion de indexacion con lomes["+null+"]");
			throw new Exception("ERROR: RellenarCamposPrincipales imposible utilizar lom-es vacio para extraer informacion de indexacion con lomes["+null+"]");
		}
		LomAgrega lomAg = new LomAgrega(lomes);
		GeneralAgrega generalAgrega = lomAg.getGeneralAgrega();
		String idioma = new String(); //idioma de catalogacion
		if (generalAgrega != null)
		{
//			TITULO
//			Indexamos el titulo que esté en el idioma de catalogación, si no hay el primer titulo 
//			de la lista de titulos que haya en el lomes, si no vacío.

			String titulo="";
			
			idioma = lomAg.getMetaMetadataAgrega().getIdioma();
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("idioma = " + idioma + " generalAgrega.getTitulo(idioma)= " + generalAgrega.getTitulo(idioma));
			if (generalAgrega.getTitulo(idioma).length()>0)
			{
				titulo=generalAgrega.getTitulo(lomAg.getMetaMetadataAgrega().getIdioma());
				logger.debug("titulo de catalogacion");
			}
			else{
				titulo = generalAgrega.getTitulos()!= null && generalAgrega.getTitulos().size() >0?
					(String)generalAgrega.getTitulos().get(0):"";  //indexamos el primer titulo
			}
			idODEVOdoc.getCatalogacionPrimaria().setTitulo(titulo);
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con titulo["+titulo+"]");
			
//			DESCRIPCION
//			Extraemos todas las descripciones pero solo indexamos la primera
//			TODO: Indexamos DESCRIPCIONES?
			
/*			String descripcion = (generalAgrega.getDescripciones()!= null && generalAgrega.getDescripciones().size() >0)?
					(String)generalAgrega.getDescripciones().get(0):
						"";// extraemos la primera descripcion.
*/			

			// 20140716 Se modifica para indexar la descripción en el idioma de catalogación si existe 
			// y si no existe se indexa la primera.
			String descripcion ="";
			
			if (generalAgrega.getDescripcionesIdioma(idioma).size()>0)
			{
				descripcion=(String) generalAgrega.getDescripcionesIdioma(idioma).get(0);
				if (logger.isDebugEnabled())
					logger.debug("Descripcion de catalogacion : " + titulo + " | "+ descripcion);
			}
			else if (generalAgrega.getDescripciones().size()>0){
				descripcion = (String)generalAgrega.getDescripciones().get(0);
			}
			
			idODEVOdoc.getCatalogacionPrimaria().setDescripcion(descripcion);
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con descripcion["+descripcion+"]");
			
//			PALABRAS CLAVE
//			Cogemos todas las palabras clave independientemente del idioma en el que esten escritas.
			ArrayList palabrasClave = generalAgrega.getPalabrasClave();
			idODEVOdoc.getCatalogacionPrimaria().setPalabrasClave(arrayListString2ArrayStringUnique(palabrasClave,","));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con palabras clave["+arrayList2String(palabrasClave,"|")+"]");
			
//			NIVEL AGREGACION
			Integer nivelAgregacion;
			try {
				nivelAgregacion = new Integer(generalAgrega.getNivelDeAgregacion());
			} catch (Exception e) {
				nivelAgregacion = new Integer(1);// por defecto sera 1
				if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("ERROR: RellenarCamposPrincipales el campo nivel agregacion["+generalAgrega.getNivelDeAgregacion()+"] no es un entero valido. Se utiliza valor por defecto [1].");
			}
			idODEVOdoc.getCatalogacionPrimaria().setNivelAgregacion(nivelAgregacion);
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con nivel agregacion["+nivelAgregacion.toString()+"]");
			
//			ALCANCE (NUEVO CAMPO NECESARIO PARA EL DUBLIN CORE)
			ArrayList alcanceListaUnica = new ArrayList();
			ArrayList alcance = generalAgrega.getAmbitos();
			alcanceListaUnica = arrayList2ArrayListUnica(alcance);
			idODEVOdoc.getCatalogacionPrimaria().setAlcance(arrayListString2ArrayString(alcanceListaUnica,","));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con alcance["+arrayList2String(alcanceListaUnica,"|")+"]");
			
		}			
		
		
		if (lomAg.getLifeCycleAgrega()!=null)
		{
//			AUTOR
			ArrayList autores = lomAg.getLifeCycleAgrega().getAutores();
//			idODEVOdoc.getCatalogacionPrimaria().setAutor(arrayListString2ArrayString(autores,","));
			idODEVOdoc.getCatalogacionPrimaria().setAutor(arrayListEntidad2ArrayString(autores));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con autores["+arrayList2String(autores,"|")+"]");
			
//			FECHA PUBLICACION
			String fecha = lomAg.getLifeCycleAgrega().getFechaPublicacion();
			try
			{
				idODEVOdoc.getCatalogacionPrimaria().setFechaPublicacion(DateManager.dateToCalendar(fecha!= null?extraeFechaDate(fecha):new Date()));
			}catch(Exception e)
			{
				logger.error("Se produce la siguiente excepcion "+e);
				Date fechaActual = new Date();
				idODEVOdoc.getCatalogacionPrimaria().setFechaPublicacion(DateManager.dateToCalendar(fechaActual));
			}
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con fecha["+fecha+"]");
			
//			PUBLICADOR (NUEVO CAMPO NECESARIO PARA EL DUBLIN CORE)
			ArrayList publicadores = lomAg.getLifeCycleAgrega().getPublicadores();
//			idODEVOdoc.getCatalogacionPrimaria().setPublicador(arrayListString2ArrayString(publicadores,","));
			idODEVOdoc.getCatalogacionPrimaria().setPublicador(arrayListEntidad2ArrayString(publicadores));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con publicadores["+arrayList2String(publicadores,"|")+"]");
			
//			CONTRIBUIDOR Ó REVISOR (NUEVO CAMPO NECESARIO PARA EL DUBLIN CORE)
			ArrayList contribuidores = lomAg.getLifeCycleAgrega().getRevisores();
//			idODEVOdoc.getCatalogacionPrimaria().setContribuidor(arrayListString2ArrayString(contribuidores,","));
			idODEVOdoc.getCatalogacionPrimaria().setContribuidor(arrayListEntidad2ArrayString(contribuidores));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con contribuidores["+arrayList2String(contribuidores,"|")+"]");

//			CONTRIBUCIONES
			ArrayList contribuciones = lomAg.getLifeCycleAgrega().getContribucionesAv();
			ContribucionVO[] contribucionesVO = TratamientoIndexacion.obtenerContribuciones(contribuciones);
			idODEVOdoc.getCatalogacionPrimaria().setContribuciones(contribucionesVO);
		
		}

		TechnicalAgrega techAgr;
		if ((techAgr = lomAg.getTechnicalAgrega())!=null)
		{
//			FORMATOS
			ArrayList formatos = techAgr.getFormatos();
			idODEVOdoc.getCatalogacionPrimaria().setFormatos(arrayList2ArrayString(formatos));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con formatos["+arrayList2String(formatos,"|")+"]");
		}
		
		ClassificationAgrega[] clas = (ClassificationAgrega[])(lomAg.getClassificationsAgrega()).toArray(new ClassificationAgrega[lomAg.getClassificationsAgrega().size()]);
		if (clas != null && clas.length>0)
		{
			ArrayList clasificacionTaxonomica = new ArrayList();
			ArrayList listaIDsArbolCurricularVigente = new ArrayList();
//			ArrayList tesaurosList = new ArrayList()
//			String etiqueta = SrvPublicacionServiceImpl.getPropertyValue("nombreAreaCurricularTax");
			String arbolCurricularVigente = taxonomias.obtenerArbolVigente().getVocabName();
//			logger.debug("Utilizamos la etiqueta de area curricular vigente["+arbolCurricularVigente+"]");
//			String etiquetaTesauro = SrvPublicacionServiceImpl.getPropertyValue("nombreTesauroTax");
//			String tesauroVigente = tesauros.obtenerVocabName(etiquetaTesauro, idioma);
//			No hay niveles educativos por ahora
//			ArrayList nivelesEducativos = new ArrayList();
			for (int i = 0; i < clas.length; i++ )
			{
//				Cada objeto de classification agrega puede ser un tesauro o un arbol curricular. No lo podemos saber a priori, pero si hacemos
//				get de uno y de otro, solo nos dara valores para el que es
//				Cogemos todos los arboles curriculares independientemente del idioma en el que esten
//				Cogemos los tesauros
				ArrayList lista;
				if ((lista = clas[i].getIdsRutasTaxonomicas()) != null)// si introducimos null, casca
					clasificacionTaxonomica.addAll(Arrays.asList(estructuras.obtenerIdsConVocabIdentifier((String[])lista.toArray(new String[0]))));
				ArrayList lista2;
				if ((lista2 = clas[i].getIdsArbolesCurriculares(arbolCurricularVigente)) != null)
					listaIDsArbolCurricularVigente.addAll(lista2);
//				String [] taxonomias = taxonomias.obtenerIdsConVocabIdentifier(listaIDsArbolCurricularVigente.toArray(new String[0]), idioma);
//				Los niveles educativos no se consultan por ahora
//				nivelesEducativos.addAl(tokeniza(clas[i].getIdsNivelesEducativos(lomAg.getMetaMetadataAgrega().getIdioma())));
			}
			
//			TAXONOMIAS
			idODEVOdoc.getCatalogacionPrimaria().setTaxonomia(arrayList2ArrayString(clasificacionTaxonomica));
			
//			ARBOL CURRICULAR VIGENTE
			idODEVOdoc.getCatalogacionPrimaria().setLiteralesArbolCurricular(obtenerArbolCurricularTexto(taxonomias, idioma, listaIDsArbolCurricularVigente, arbolCurricularVigente));
			
			
//			String[] asdf=new String[]{"Árbol curricular LOE 2006/1/1.1/1.1.2","Árbol curricular LOE 2006/2/2.2/2.2.1","Tesauro  ETB-LRE MEC-CCAA V.1.0/M70/M70.20/968/3962","Tesauro  ETB-LRE MEC-CCAA V.1.0//M70/M70.90/627/473"};
//			idODEVOdoc.getCatalogacionPrimaria().setTaxonomia(asdf);
			
			
			
			
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con["+clasificacionTaxonomica.size()+"] arbol curricular["+arrayList2String(clasificacionTaxonomica,"|")+"]");
			
//			TESAUROS
//			buena   idODEVOdoc.getCatalogacionPrimaria().setTesauros(arrayList2ArrayString(tesaurosList));			
//			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con["+tesaurosList.size()+"] tesauro["+arrayList2String(tesaurosList,"|")+"]");
			
//			NIVELES EDUCATIVOS
//			No hay niveles educativos por ahora.
//			idODEVOdoc.getCatalogacionPrimaria().setNivelesEducativos(arrayListString2ArrayString(nivelesEducativos,"/"));
//			TratamientoIndexacion.traza("Rellenamos LomESPrimarioVO con nivel educativo["+arrayList2String(nivelesEducativos,"|")+"]");
		}
		
		EducationalAgrega[] educa = (EducationalAgrega[])lomAg.getEducationalsAgrega().toArray(new EducationalAgrega[lomAg.getEducationalsAgrega().size()]);
		if (educa != null && educa.length>0)
		{
			ArrayList recursos = new ArrayList();
			ArrayList destinatarios = new ArrayList();
			ArrayList procesosCognitivos = new ArrayList();
			ArrayList contextos = new ArrayList();
			ArrayList edades = new ArrayList();
			ArrayList descripciones = new ArrayList();
			
			for (int i = 0; i < educa.length; i++ )
			{
				ArrayList temp;
				if ((temp = educa[i].getTiposDeRecurso())!= null)
					recursos.addAll(temp);
				if ((temp = educa[i].getDestinatarios()) != null)
					destinatarios.addAll(temp);
				if ((temp = educa[i].getProcesosCognitivos()) != null)
					procesosCognitivos.addAll(temp);
				if ((temp = educa[i].getContextos()) != null)
					contextos.addAll(temp);
				if ((temp = educa[i].getRangosEdad()) != null)
					edades.addAll(temp);
				if((temp=educa[i].getDescripcionesAv()) !=null)
					descripciones.addAll(temp);
			}
//			RECURSOS
			idODEVOdoc.getCatalogacionPrimaria().setRecurso(arrayList2ArrayString(recursos));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con recursos["+arrayList2String(recursos,"|")+"]");
//			DESTINATARIOS
			idODEVOdoc.getCatalogacionPrimaria().setDestinatarios(arrayList2ArrayString(destinatarios));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con destinatarios["+arrayList2String(destinatarios,"|")+"]");
//			PROCESOS COGNITIVOS
			idODEVOdoc.getCatalogacionPrimaria().setProcesosCognitivos(arrayList2ArrayString(procesosCognitivos));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con procesos cognitivos["+arrayList2String(procesosCognitivos,"|")+"]");
//			CONTEXTOS
			idODEVOdoc.getCatalogacionPrimaria().setContextos(arrayList2ArrayString(contextos));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con contextos["+arrayList2String(contextos,"|")+"]");
//			EDADES DE DESTINATARIOS
			idODEVOdoc.getCatalogacionPrimaria().setEdades(arrayList2ArrayString(edades));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con edades["+arrayList2String(edades,"|")+"]");
//			DESCRIPCIONES
			String[] descripcionesObjetivo =arrayLangString2Array(descripciones, idioma);
			idODEVOdoc.getCatalogacionPrimaria().setDescripciones(descripcionesObjetivo);
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con descripciones["+arrayString2String(descripcionesObjetivo,"|")+"]");
			
		}
		
		RightsAgrega dere = lomAg.getRightsAgrega();
		String dereAutor;
		if (dere != null && (dereAutor = dere.getDerechosDeAutor())!= null)
		{
//			DERECHOS DE AUTOR
			idODEVOdoc.getCatalogacionPrimaria().setLicencia(dereAutor!= null?dereAutor:"");
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con derechos de autor["+dereAutor+"]");
		}
		
//		Ambito-Acceso(catalogacion)
		AccesoAgrega acc;
		if (dere != null && (acc = dere.getAccesoAv()) != null)
		{
			// solo vamos a indexar los nodos del ODE que esten en la lista de nodos locales.
			///////String[] nodosLocales = obtenNodosLocales(nodos);
			String[] ambito = obtenAmbito(acc, nodosLocales);
			idODEVOdoc.getCatalogacionPrimaria().setAmbito(ambito);
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con ["+ambito.length+"]ambito");
		}
//		IDIOMA CATALOGACION
		idioma = lomAg.getMetaMetadataAgrega().getIdioma();
		idODEVOdoc.getCatalogacionPrimaria().setIdioma(idioma);
		if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con idioma["+idioma+"]");		

		RelationAgrega[] relation = (RelationAgrega[])(lomAg.getRelationsAgrega()).toArray(new RelationAgrega[lomAg.getRelationsAgrega().size()]);
		
		if (relation != null && relation.length>0)
		{
			ArrayList relacion = new ArrayList();
			ArrayList fuente = new ArrayList();
			
			for (int i = 0; i < relation.length; i++ )
			{
				String temporalRelacion, temporalFuente;
				
				if ((temporalFuente = relation[i].getIdentificadorMEC()) != null)				
					fuente.add(temporalFuente);	
				if ((temporalRelacion = relation[i].getTipoRelacionMEC()) != null)				
					relacion.add(temporalRelacion);					
			}
			
//			FUENTE (NUEVO CAMPO CREADO PARA EL DUBLIN CORE)
			idODEVOdoc.getCatalogacionPrimaria().setFuente((arrayList2ArrayString(fuente)));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con fuente["+arrayList2String(fuente,"|")+"]");
			
//			RELACION (NUEVO CAMPO CREADO PARA EL DUBLIN CORE)
			idODEVOdoc.getCatalogacionPrimaria().setRelacion(arrayList2ArrayString(relacion));
			if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESPrimarioVO con relacion["+arrayList2String(relacion,"|")+"]");		
			
		}
		
		return idODEVOdoc;
	}
	
	
	
	public static es.pode.indexador.negocio.servicios.indexado.IdODEVO RellenarCamposSecundarios(IdODEVO idODEVOdoc, Lom[] lomes) 
	throws Exception 
	{

		// antes de llamar aquí se borra el primer lomes
			idODEVOdoc.setCatalogacionSecundaria(new LomESSecundarioVO[lomes!= null&& lomes.length>0?lomes.length:0]);
			if (lomes != null && lomes.length > 0)
			{
				for (int x = 0; lomes!= null && x < lomes.length; x++) 
				{
					GeneralAgrega generalAgrega= (new LomAgrega(lomes[x])).getGeneralAgrega();

					if (generalAgrega != null)
					{
						LomESSecundarioVO lomEsSecundario = new LomESSecundarioVO();
						
//						PALABRAS CLAVE
//						indexamos todas las palabras clave independientemente del idioma
						ArrayList palabrasClave = generalAgrega.getPalabrasClave();
						lomEsSecundario.setPalabrasClave(
								palabrasClave != null && palabrasClave.size()>0?(String[])palabrasClave.toArray(new String[palabrasClave.size()]):
									new String[]{""});
						if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESSecundarioVO["+x+"] con keywords["+arrayList2String(palabrasClave,"|")+"]");

//						TITULO
//						indexamos el primer titulo que salga del lomes independientemente del idioma
//						TODO: Indexamos TITULOS?
						String titulo = generalAgrega.getTitulos()!= null && generalAgrega.getTitulos().size() >0?
								(String)generalAgrega.getTitulos().get(0):"";  //indexamos el primer titulo
						lomEsSecundario.setTitulo(titulo);
						if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESSecundarioVO["+x+"] con titulo["+titulo+"]");

//						DESCRIPCION
//						Extraemos todas las descripciones pero solo indexamos la primera
//						TODO: indexamos DESCRIPCIONES?
						String descripcion = (generalAgrega.getDescripciones()!= null && generalAgrega.getDescripciones().size() >0)?
								(String)generalAgrega.getDescripciones().get(0):
									"";// extraemos la primera descripcion.
						lomEsSecundario.setDescripcion(descripcion);
						if(TratamientoIndexacion.logger.isDebugEnabled()) TratamientoIndexacion.logger.debug("Rellenamos LomESSecundarioVO["+x+"] con descripcion["+descripcion+"]");
						
//						Asignamos el lomes secundario en la posicion que toque
						idODEVOdoc.getCatalogacionSecundaria()[x] = lomEsSecundario;
					}
				}
			}
		
		return idODEVOdoc;
	}
	
	//Este metodo recibe un arrayList de objeros y devuelve y arrayList
	private static ArrayList arrayList2ArrayListUnica(ArrayList listaEntrada)
	{
		ArrayList listaReturn = new ArrayList();
		
		if(listaEntrada != null && listaEntrada.size() > 0){
			ArrayList listaTemporal = new ArrayList();
			//Recorremos la lista
			for (int i = 0; i < listaEntrada.size(); i++)
			{
				if(listaEntrada.get(i) != null )
				{
					//Compramos si la posicion i de la lista es a su vez otra lista
					listaTemporal = (ArrayList)listaEntrada.get(i);
					if(listaTemporal.size() > 0)
					{					
						//Recorremos la lista secundaria
						for (int j=0; j < listaTemporal.size(); j++)
						{
							listaReturn.add(listaTemporal.get(j));
						}
					}					
				}
			}
		}
		
		return listaReturn;
	}

//	Este metodo recibe un arraylist de objetos y devuelve un array de strings con la representacion en string
//	de cada uno de los elementos del arraylist
	private static String[] arrayList2ArrayString(ArrayList array)
	{
		String[] sb = null;
		
		if (array != null && array.size() >0)
		{
			sb = new String[array.size()];
			for (int i = 0; i < array.size(); i++)
			{
				sb[i] = String.valueOf(array.get(i));
			}
		}
		if (sb != null)
			return sb;
		else
			return new String[]{""};
	}
	
//	Este metodo recibe un arraylist de arrays de strings o de objetos LangStringAgrega con caracteres separadores
//	y devuelve un array de strings con la representacion en string
//	de cada uno de los elementos del arraylist sin el caracter separador (split)
	private static String[] arrayListString2ArrayString(ArrayList array, String split)
	{
		ArrayList listaFinal = new ArrayList();
		
		if (array != null && array.size() >0)
		{
			String[] tiraSinBarras = null;
			for (int i = 0; i < array.size(); i++)
			{
				//Comprobando de dentro de la posicion hay otro arrayList
				if (array.get(i) instanceof java.lang.String)
					tiraSinBarras = ((String)array.get(i)).split(split);
				else if (array.get(i) instanceof es.pode.parseadorXML.lomes.lomesAgrega.LangStringAgrega)
				{
					tiraSinBarras = ((LangStringAgrega)array.get(i)).getValor().split(split);
				}
				for (int j = 0; tiraSinBarras != null && tiraSinBarras.length > 0 && j < tiraSinBarras.length;j++){
					if (!tiraSinBarras[j].equals(""))
						listaFinal.add(tiraSinBarras[j].trim());
				}
			}
		}
		if (listaFinal.size() > 0)
			return (String[])listaFinal.toArray(new String[listaFinal.size()]);
		else
			return new String[]{""};
	}
	
//	Este metodo recibe un arraylist de arrays de strings o de objetos LangStringAgrega con caracteres separadores
//	y devuelve un array de strings con la representacion en string
//	de cada uno de los elementos del arraylist sin el caracter separador (split). Se eliminan las repeticiones
//	del array devuelto
	private static String[] arrayListString2ArrayStringUnique(ArrayList array, String split)
	{
		HashSet listaFinal = new HashSet();
		
		if (array != null && array.size() >0)
		{
			String[] tiraSinBarras = null;
			for (int i = 0; i < array.size(); i++)
			{
				if (array.get(i) instanceof java.lang.String)
					tiraSinBarras = ((String)array.get(i)).split(split);
				else if (array.get(i) instanceof es.pode.parseadorXML.lomes.lomesAgrega.LangStringAgrega)
				{
					tiraSinBarras = ((LangStringAgrega)array.get(i)).getValor().split(split);
				}
				for (int j = 0; tiraSinBarras != null && tiraSinBarras.length > 0 && j < tiraSinBarras.length;j++){
					if (!tiraSinBarras[j].equals(""))
					{
						listaFinal.add(tiraSinBarras[j].trim());
					}
				}
			}
		}
		if (listaFinal.size() > 0)
			return (String[])listaFinal.toArray(new String[listaFinal.size()]);
		else
			return new String[]{""};
	}
	
	private static String[] arrayListEntidad2ArrayString(ArrayList array){
		ArrayList listaResultado = new ArrayList();
		if (array != null && array.size() >0)
		{
			String[] resultado = null;
			
			for (int i = 0; i < array.size(); i++)
			{
				EntidadAgrega entidad = (EntidadAgrega) array.get(i);
				String sEntidad = (entidad.getNombre()!=null?entidad.getNombre():"");
				sEntidad += (!sEntidad.equals("")?" ":"" ) + (entidad.getOrganizacion()!=null?entidad.getOrganizacion():"");
				if(!sEntidad.equals(""))
					listaResultado.add(sEntidad);
			}
		}
		if (listaResultado.size() > 0)
			return (String[])listaResultado.toArray(new String[listaResultado.size()]);
		else
			return new String[]{""};
	}
	
//	Convierte una fecha en formato yyyy-MM-dd'T'hh:mm:ss.SS a formato YYYYMMDD
	private static String extraeFechaYYYYMMDD(String fecha)
	{
		String retorno = "";
		SimpleDateFormat formatoIn = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SS");
		SimpleDateFormat formatoOut = new SimpleDateFormat("yyyyMMdd");
		if (fecha != null && !fecha.equals(""))
		{
//			String[] lista = fecha.split("-");
//			if (lista!= null && lista.length == 3)
//				retorno+=lista[0]+lista[1]+lista[2];
			Date fechaDate =formatoIn.parse(fecha, new ParsePosition(0));
			retorno+= formatoOut.format(fechaDate);
		}
		return retorno;
	}
//	Lee una fecha en formato yyyy-MM-dd'T'hh:mm:ss.SS a Date
	private static Date extraeFechaDate(String fecha)
	{
		
		Date fechaDate = new Date();  // a las malas devuelvo hoy
		SimpleDateFormat formatoIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		if (fecha != null && !fecha.equals(""))
		{
			fechaDate =formatoIn.parse(fecha, new ParsePosition(0));
		}
		return fechaDate;
	}

	private static String arrayList2String(ArrayList array, String separator)
	{
		StringBuilder sb = new StringBuilder("");
		
		if (array != null && array.size() >0)
		{
			for (int i = 0; i < array.size(); i++)
			{
				if (i == array.size())
					sb.append(String.valueOf(array.get(i)));
				else
					sb.append(String.valueOf(array.get(i)) + separator);
			}
		}
		return sb.toString();
	}
	
	private static String arrayString2String(String[] array, String separator)
	{
		StringBuilder sb = new StringBuilder("");
		
		if (array != null && array.length>0)
		{
			for (int i = 0; i < array.length; i++)
			{
				if (i == array.length)
					sb.append(String.valueOf(array[i]));
				else
					sb.append(String.valueOf(array[i]) + separator);
			}
		}
		return sb.toString();
	}
	
	/*
	 * Este metodo recoge el contenido del ambito de la parte del lomes del ODE donde se almacena
	 * y recupera la informacion relevante para la indexacion*/
	private static String[] obtenAmbito(AccesoAgrega acc, String[] nodos)
	{
//		El ambito de un ODE puede tener dos valores: universal y non-universal. En el caso de la
//		primera, indexamos el texto universal y el caso del segundo tenemos que indexar el valor
//		de la descripcion en el que se expresan las CCAA que estan licenciadas en el ambito separa-
//		das por comas.
		String[] retorno = new String[0];
		if (acc.getTipoAcceso().equals(AMBITO_UNIVERSAL))
		{
			retorno = new String[] {AMBITO_UNIVERSAL};
		}
		else if (acc.getTipoAcceso().equals(AMBITO_NON_UNIVERSAL))
		{
//			Primero obtenemos las CCAA que hay disponibles en el nodo
			;
//			HashSet nodosTotal = new HashSet(Arrays.asList(DependentServerProperties.getInstance().getNodos().split(",")));
			HashSet nodosTotal = new HashSet(Arrays.asList(nodos));
//			Obtenemos los nodos que hay en el lom-es
			String[] lista = arrayListString2ArrayStringUnique(acc.getDescripcion(),",");
			HashSet nodosLeidos = new HashSet(Arrays.asList(lista));
			nodosLeidos.retainAll(nodosTotal);
//			nodosTotal.retainAll(); // eliminamos de los nodos totales los que no esten en la lista de los que hay en el LOM-ES
			retorno = (String[]) nodosLeidos.toArray(new String[0]);
		}
		else
		{
			retorno = new String[0];
		}
		if (logger.isDebugEnabled()) logger.debug("Ambitos leidos["+TratamientoIndexacion.arrayList2String(new ArrayList(Arrays.asList(retorno)), ",")+"]");
		return retorno;
	}

	/*
	 * Este metodo devuelve la lista de nodos locales que estan dados de alta en el nodo.
	 * */
//	private static String[] obtenNodosLocales(SrvNodoService srvNodos) throws Exception
//	{
//		NodoVO[] nodos = srvNodos.listarNodos();
////		Obtenemos el nodo local y lo añadimos
//		String[] nodosReturn = new String[((nodos!=null)?nodos.length+1:1)];
//		nodosReturn[0] = DependentServerProperties.getInstance().getProperty(IDENTIFICADOR_NODO);
//		for (int i = 1; i < nodosReturn.length; i++) {
//			nodosReturn[i] = nodos[i-1].getIdNodo();
//		}
//		return nodosReturn;
//	}
	
	//Método para ordenar las descripciones, si existen descripciones con el idioma de catalogacion devolvemos esos; si no existen con el idioma de catalogacion
	//devolvemos los que no tienen idioma; y si no encuentra ningunos de esos dos caso se devuelven todas las descripciones
	private static String[] arrayLangString2Array(ArrayList array,String  idiomaMeta)
	{
		ArrayList listaReturn1 = new ArrayList();
		ArrayList listaReturn2 = new ArrayList();
		ArrayList listaReturn3 = new ArrayList();
		
		if (array != null && array.size() >0)
		{
			for (int i = 0; i < array.size(); i++)
			{
				
				LangStringAgrega langString=(LangStringAgrega)array.get(i);
				String idiomaDesc=langString.getIdioma();
				if(idiomaDesc!=null && !idiomaDesc.equals("") && idiomaDesc.equals(idiomaMeta)){
					listaReturn1.add(langString.getValor());
				}else if(idiomaDesc==null || idiomaDesc.equals("")){
					listaReturn2.add(langString.getValor());
				}else{
					listaReturn3.add(langString.getValor());
				}
		
			}
			if(listaReturn1!=null && listaReturn1.size()>0){
				return (String[])listaReturn1.toArray(new String[0]);
			}else if(listaReturn2 !=null && listaReturn2.size()>0){
				return (String[])listaReturn2.toArray(new String[0]);
				
			}
		}
		return (String[])listaReturn3.toArray(new String[0]);
	}
	
	
	
//	Método para obtener los identificadores de la última hoja del nodo, a partir de la lista de identificadores del árbol curricular.
	private static String[] obtenNodosHijo(ArrayList listaIds)
	{
		String[] nodos = new String[0];
		if (listaIds != null && listaIds.size() >0)
		{
			nodos=new String[listaIds.size()];
			for (int i = 0; i < listaIds.size(); i++) {
				// Recorro la lista, y llamo al método unitario con cada uno de los eltos.
				nodos[i]=obtenNodoHijo((String)listaIds.get(i));
			}
		}
		return nodos;
	}

//	Método unitario que a partir de un identificador del árbol curricular, da el identificador del último nodo.
	private static String obtenNodoHijo (String ids){
		String hijo = "";
		if(ids != null && ids.length()>0){
			String[] nodos = ids.split("/");
			if(nodos != null && nodos.length>0){
				hijo = nodos[nodos.length-1];
			}
		}
		return hijo;
	}


	
	// Este metodo recibe el servicio de taxonomias, un idioma, el arbol curricular vigente y una lista de nodos hoja de la taxonomia del arbol curricular
	// vigente y devuelve la lista de las descripciones del path de cada nodo hoja
	private static String[] traducirPathArbolCurricular(SrvTaxonomiaService taxonomias, String idioma, String[] listaIDsArbolCurricularVigente, String arbolCurricularVigente)
	{
		ArrayList listaTaxonesTexto = new ArrayList();

		if(taxonomias != null && idioma != null && idioma.length()>0 && listaIDsArbolCurricularVigente != null &&
				listaIDsArbolCurricularVigente.length>0 && arbolCurricularVigente != null && arbolCurricularVigente.length()>0)
		{
//			Traducimos los nodos que nos pasan en toda su ruta hasta el padre
			for (int i = 0; i < listaIDsArbolCurricularVigente.length; i++) {
				try {
					//Devolvemos un TaxonVO con toda la ruta de los taxones antecedentes al taxon dado
					TaxonVO[] taxones =taxonomias.obtenerTaxonPath(listaIDsArbolCurricularVigente[i], arbolCurricularVigente, idioma);
					for (int j = 0; taxones != null && taxones.length > 0 && j < taxones.length; j++) {
						//Recuperamos el valor del taxon de la taxonomia a la que pertenece, y se lo pasamos a una lista.
						listaTaxonesTexto.add(taxones[j].getValorTax());
					}
				} catch (Exception e) {
					logger.error("Error: traducirPathArbolCurricular, error al obtener el taxonPath con listaIDsArbolCurricularVigente: ["+
							listaIDsArbolCurricularVigente[i]+"] arbolCurricularVigente ["+ arbolCurricularVigente +"] e idioma ["+ idioma +"]",e);
				}
			}
		}
		// devolvemos la lista con las descripciones del path de cada nodo hoja
		return (String[])listaTaxonesTexto.toArray(new String[0]);
	}


	//Este metodo recibe el servicio de taxonomias, un idioma, una lista de nodos hoja de la taxonomia del arbol curricular y el arbol curricular vigente
	// y devuelve la lista de las descripciones de cada nodo hoja. (sin repetir descripciones) 
	private static String[] obtenerArbolCurricularTexto(SrvTaxonomiaService taxonomias, String idioma, ArrayList listaIDsArbolCurricularVigente, String arbolCurricularVigente)
	{
		// Recogemos las  descripciones del path de cada nodo hoja.
		String[] rutasTraducidasArbolesCurriculares = traducirPathArbolCurricular(taxonomias, idioma,obtenNodosHijo(listaIDsArbolCurricularVigente),arbolCurricularVigente);
		ArrayList listaNombresNodo = new ArrayList();
		//Creo una Hashtable para recoger todas las descripciones, y evitar repeticiones
		Hashtable<String, String> hash = new Hashtable();
		for (int i = 0; rutasTraducidasArbolesCurriculares != null && rutasTraducidasArbolesCurriculares.length > 0 && i < rutasTraducidasArbolesCurriculares.length; i++){
			if (!rutasTraducidasArbolesCurriculares[i].equals(""))
			{
				// el valor de la hashtable no me interesa, solo necesito controlar las claves.
				hash.put(rutasTraducidasArbolesCurriculares[i], "");
			}
		}
		//Recorro la HashTable y me quedo con las claves.
		Enumeration enumeracion = hash.keys();
		while (enumeracion.hasMoreElements()) {
			String nombreNodo = (String) enumeracion.nextElement();
			listaNombresNodo.add(nombreNodo);
		}
		return (String[])listaNombresNodo.toArray(new String[0]);		
	}
	
	private static ContribucionVO[] obtenerContribuciones(ArrayList listaContribuciones){
		ContribucionVO[] aContribuciones = new ContribucionVO[listaContribuciones.size()];
		for(int i = 0; i<listaContribuciones.size();i++){
			ContribucionVO contribucionVO = new ContribucionVO();
			ContribucionAgrega contribAgrega = (ContribucionAgrega) listaContribuciones.get(i);
			contribucionVO.setCodTipoContribucion(contribAgrega.getTipo());
			if(contribAgrega.getFecha()!=null)
				contribucionVO.setFecha(contribAgrega.getFecha().getFecha());
			
			if(contribAgrega.getEntidades()!=null && contribAgrega.getEntidades().size()>0){
				EntidadVO[] aEntidades = new EntidadVO[contribAgrega.getEntidades().size()];
				for(int j=0;j<contribAgrega.getEntidades().size();j++){
					EntidadVO entidadVO = new EntidadVO();
					EntidadAgrega entidadAgrega = (EntidadAgrega) contribAgrega.getEntidades().get(j);
					entidadVO.setNombre(entidadAgrega.getNombre());
					entidadVO.setOrganizacion(entidadAgrega.getOrganizacion());
					aEntidades[j]=entidadVO;
				}
				contribucionVO.setEntidades(aEntidades);
			}
			aContribuciones[i]=contribucionVO;
		}
		return aContribuciones;
		
	}
}


