/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.soporte;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.pode.indexador.negocio.servicios.indexado.ContribucionVO;
import es.pode.indexador.negocio.servicios.indexado.EntidadVO;
import es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceImpl;

public class DocumentoIndexacion {

	private static Logger logger = Logger.getLogger(DocumentoIndexacion.class);

	public static String INDEXACION_SEPARATOR = "-";
	
	final static String INDEXADO_NOR = "NORMAL";
	final static String INDEXADO_MAY = "MAYUSCULAS";
	final static String INDEXADO_MIN = "MINUSCULAS";
	
	// método auxiliar para rellenar los fields, además con la prioridad.
//	private static Field generaField(String title, String arg2, Store store, Index index, float prioridad)
//	{
//		logger.debug("generaField");
//		if (logger.isDebugEnabled())
//			logger.debug("Se va a generar el campo title = " + title + " args = "+ arg2 + " store = " + store + " index = " + index + " prioridad = " + prioridad);
//		Field field = new Field (title,arg2,store,index);
//		field.setBoost(prioridad);
//		
//		return field;		
//	}
	
//	public static Document GenerarCamposSecundarios(Document doc, es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[] lomesSecundarios, Properties props) 
//			throws Exception 
//	{
//		for (int x = 0; lomesSecundarios != null && x < lomesSecundarios.length; x++) 
//		{
//			es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO lomESSecundario = lomesSecundarios[x]; 
//			
//			if (lomESSecundario != null)
//			{
//				aniadeFieldaDocument(doc, props.getProperty("campo_keywords_secundario"),
//						lomESSecundario.getPalabrasClave(), Field.Store.YES,Field.Index.TOKENIZED,
//						Float.valueOf(props.getProperty("prioridad_palabraClave_secundario")).floatValue(), DocumentoIndexacion.INDEXADO_MIN);
//				if(DocumentoIndexacion.logger.isDebugEnabled())
//				DocumentoIndexacion.logger.debug("Añadimos al documento el campo secundario campo_keywords_secundario ");
//
//				aniadeFieldaDocument(doc, props.getProperty("campo_title_secundario"),
//						lomESSecundario.getTitulo(), Field.Store.YES,Field.Index.TOKENIZED,
//						Float.valueOf(props.getProperty("prioridad_titulo_secundario")).floatValue(), DocumentoIndexacion.INDEXADO_MIN);
//				if(DocumentoIndexacion.logger.isDebugEnabled())
//					DocumentoIndexacion.logger.debug("Añadimos al documento el campo secundario campo_title_secundario ");
//
//				aniadeFieldaDocument(doc, props.getProperty("campo_description_secundario"),
//						lomESSecundario.getDescripcion(), Field.Store.YES,Field.Index.TOKENIZED,
//						Float.valueOf(props.getProperty("prioridad_descripcion_secundario")).floatValue(), DocumentoIndexacion.INDEXADO_MIN);
//				if(DocumentoIndexacion.logger.isDebugEnabled())
//					DocumentoIndexacion.logger.debug("Añadimos al documento el campo secundario campo_description_secundario ");
//			}
//		}
//		return doc;
//	}

//	Este metodo aniade en el documento que le pasan el valor del campo que le pasan parametrizando ese aniadido con
//	cuestiones como, si ha de almacenarse, el tipo de indexacion que ha de hacerse, el peso del campo etc.
//	Se le pasa el fichero de propiedades del que hay que extraer el valor del campo a indexar.
//	private static void aniadeFieldaDocument(Document doc, String campo, String valor, Field.Store almacena, Field.Index indexacion, float peso, String tipoTexto)
//	{
//		//Si indexamos un campo nulo, metemos como valor ""
//		if (valor == null)
//			valor = "";
//		//Indexamos en minusculas, mayusculas o tal cual (por que si no, puede hacer sugerencias en mayusculas)
//		if (campo != null && !campo.equals(""))
//		{
//			if (tipoTexto.equals(DocumentoIndexacion.INDEXADO_MIN))
//				doc.add(generaField(campo, valor.toString().toLowerCase(), almacena, indexacion, peso));
//			else if (tipoTexto.equals(DocumentoIndexacion.INDEXADO_NOR))
//				doc.add(generaField(campo, valor.toString(), almacena, indexacion, peso));
//			else if (tipoTexto.equals(DocumentoIndexacion.INDEXADO_MAY))
//				doc.add(generaField(campo, valor.toString().toUpperCase(), almacena, indexacion, peso));
//		}
//	}
//	Este metodo aniade en el documento que le pasan el array de valores del campo que le pasan parametrizando ese aniadido con
//	cuestiones como, si ha de almacenarse, el tipo de indexacion que ha de hacerse, el peso del campo etc.
//	Se le pasa el fichero de propiedades del que hay que extraer el valor del campo a indexar.
//	private static void aniadeFieldaDocument(Document doc, String campo, String[] valor, Field.Store almacena, Field.Index indexacion, float peso, String tipoTexto)
//	{
////		En el caso de que haya multimples valores, los aniadimos todos
//		if (valor != null && valor.length>0) {
//			for (int i = 0; i < valor.length; i++) {
//				aniadeFieldaDocument(doc, campo, valor[i], almacena, indexacion, peso, tipoTexto);
//			}
//		}
//		else //en el caso de que no haya valores, indexamos vacio
//		{
//			aniadeFieldaDocument(doc, campo, "", almacena, indexacion, peso, tipoTexto);
//		}
//	}	
//	public static Document GenerarCamposPrincipales(Document doc, es.pode.indexador.negocio.servicios.indexado.IdODEVO idODEVO, Properties props) throws Exception 
//	{
//		
////		catalogamos todo lo que esta en el ODE sin pertenecer a los lomes.
//		aniadeFieldaDocument(doc, props.getProperty("campo_identificadorODE"),
//				idODEVO.getIdODE(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//				Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		aniadeFieldaDocument(doc, props.getProperty("campo_localizador"),
//				idODEVO.getLocalizadorODE(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//				Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		aniadeFieldaDocument(doc, props.getProperty("campo_valoracion"),
//				idODEVO.getValoracion().toString(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//				Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		aniadeFieldaDocument(doc, props.getProperty("campo_secuencia"),
//				idODEVO.getSecuencia().toString(), Field.Store.YES,Field.Index.TOKENIZED,
//				Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		aniadeFieldaDocument(doc, props.getProperty("campo_imagen"),
//				idODEVO.getImgFile(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//				Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		//Tratamiento del campo tamanio
//		
//		Long tamanioLong = null;
//		if(idODEVO.getTamanio() == null || idODEVO.getTamanio().equals(""))	tamanioLong = new Long(0);		
//		else tamanioLong = new Long(idODEVO.getTamanio().longValue());			
//			aniadeFieldaDocument(doc, props.getProperty("campo_tamanio"), 
//					tamanioLong.toString(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//		
//		
//		es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO lomesPrimario = idODEVO.getCatalogacionPrimaria();
////		Catalogamos todo lo que esta dentro del lomes primario
//		if (lomesPrimario != null)
//		{
//			aniadeFieldaDocument(doc, props.getProperty("campo_titulo"),
//					lomesPrimario.getTitulo(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_titulo_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_tituloSinTildes"),
//					removeAccents(lomesPrimario.getTitulo()), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_titulo_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_descripcion"),
//					lomesPrimario.getDescripcion(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_descripcion_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_descripcionSinTildes"),
//					removeAccents(lomesPrimario.getDescripcion()), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_descripcion_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
////			Utilizamos para el campo de sugerencias la concatenacion del titulo y la descripcion
//			String sugerencias = (lomesPrimario.getTitulo()!= null?lomesPrimario.getTitulo():"")+" "+
//					(lomesPrimario.getDescripcion()!= null?lomesPrimario.getDescripcion():"");	
//			aniadeFieldaDocument(doc, props.getProperty("campo_sugerencias"),
//					sugerencias, Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			
//			aniadeFieldaDocument(doc, props.getProperty("campo_palabrasClave"),
//					lomesPrimario.getPalabrasClave(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_palabraClave_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_palabrasClaveSinTildes"),
//					removeAccents(lomesPrimario.getPalabrasClave()), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_palabraClave_primario")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_autor"),
//					lomesPrimario.getAutor(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_fechaPublicacion"),
//					obtenerFechaPublicacion(lomesPrimario.getFechaPublicacion().getTime())[0], Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_horaPublicacion"),
//					obtenerFechaPublicacion(lomesPrimario.getFechaPublicacion().getTime())[1], Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_formato"),
//					lomesPrimario.getFormatos(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			aniadeFieldaDocument(doc, props.getProperty("campo_taxonomia"),
//					arrayString2ArrayStringAtomizado(lomesPrimario.getTaxonomia(),"/"), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
////			aniadeFieldaDocument(doc, props.getProperty("campo_idTesauro"),
////					arrayString2ArrayStringAtomizado(lomesPrimario.getTesauros(),"/"), Field.Store.YES,Field.Index.TOKENIZED,
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			aniadeFieldaDocument(doc, props.getProperty("campo_taxonomia_path"),
//					lomesPrimario.getTaxonomia(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			aniadeFieldaDocument(doc, props.getProperty("campo_taxonomia_nodo"),
//					arrayString2ArrayStringAtomizadoUltimo(lomesPrimario.getTaxonomia(),"/"), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			//Arbol curricular vigente
////			aniadeFieldaDocument(doc, props.getProperty("campo_arbolCurricularVigente"), lomesPrimario.getArbolCurricularVigente(), Field.Store.YES, Field.Index.UN_TOKENIZED, 
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
////			No tenemos en cuenta el nivel educativo para la indexacion
////			aniadeFieldaDocument(doc, props.getProperty("campo_nivelEducativo"),
////					lomesPrimario.getNivelesEducativos(), Field.Store.YES,Field.Index.TOKENIZED,
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_recurso"),
//					lomesPrimario.getRecurso(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_destinatarios"),
//					lomesPrimario.getDestinatarios(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_licencias"),
//					lomesPrimario.getLicencia(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
////			El ambito es un valor de un campo libre de la etiqueta de licencias que indica las comunicades autonomas con
////			permiso para ver este ODE. Consultar con Nora sobre la manera de extraer esta informacion y meterla en el
////			publicador.
//			aniadeFieldaDocument(doc, props.getProperty("campo_ambito"),
//					lomesPrimario.getAmbito(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
////			El curso es una taxonomia más. Todavia no hay metodo para extraer esta informacion por lo que hay que hablar
////			con Nora para ver si hay que tener en cuenta esta taxonomia y crear un metodo para obtener la informacion
////			o desaparece. Despues hay que meterla en el publicador.
////			Dejamos de indexar el curso hasta hablar con Nora.
////			aniadeFieldaDocument(doc, props.getProperty("campo_curso"),
////					lomesPrimario.getCurso(), Field.Store.YES,Field.Index.UN_TOKENIZED,
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_idiomaBusqueda"),
//					lomesPrimario.getIdioma(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_edad"),
//					lomesPrimario.getEdades(),Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_contexto"),
//					lomesPrimario.getContextos(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			aniadeFieldaDocument(doc, props.getProperty("campo_procesoCognitivo"),
//					lomesPrimario.getProcesosCognitivos(), Field.Store.YES,Field.Index.TOKENIZED,
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
////			Por ahora sumamos el valor del peso del boost del campo nivel agregacion al valor del nivel agregacion
////			para que tenga mas prioridad cuanto mas nivel agregacion
//			int valorPesoNivelAgregacion = Integer.parseInt(props.getProperty("prioridad_nivel_agregacion_primario")) * lomesPrimario.getNivelAgregacion().intValue();
//			aniadeFieldaDocument(doc, props.getProperty("campo_nivelAgregacion"), 
//					lomesPrimario.getNivelAgregacion().toString(), Field.Store.YES, Field.Index.UN_TOKENIZED, 
//					Float.valueOf(""+valorPesoNivelAgregacion).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			//TRATAMIENTO DE LA FUENTE
//			aniadeFieldaDocument(doc, props.getProperty("campo_fuente"), 
//					lomesPrimario.getFuente(), Field.Store.YES, Field.Index.TOKENIZED, 
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			//TRATAMIENTO DE LA RELACION
//			aniadeFieldaDocument(doc, props.getProperty("campo_relacion"), 
//					lomesPrimario.getRelacion(), Field.Store.YES, Field.Index.TOKENIZED, 
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			//TRATAMIENTO DEL PUBLICADOR
//			aniadeFieldaDocument(doc, props.getProperty("campo_publicador"), 
//					lomesPrimario.getPublicador(), Field.Store.YES, Field.Index.TOKENIZED, 
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			//TRATAMIENTO DEL CONTRIBUIDOR
//			aniadeFieldaDocument(doc, props.getProperty("campo_contribuidor"), 
//					lomesPrimario.getContribuidor(), Field.Store.YES, Field.Index.TOKENIZED, 
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			//TRATAMIENTO DEL ALCANCE
//			aniadeFieldaDocument(doc, props.getProperty("campo_alcance"), 
//					lomesPrimario.getAlcance(), Field.Store.YES, Field.Index.TOKENIZED, 
//					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);			
//			
//			//TRATAMIENTO DEL ARBOL CURRICULAR LITERAL
//				//Con acentos
////				aniadeFieldaDocument(doc, props.getProperty("campo_areaCurricular_literal"),
////					arrayString2ArrayStringAtomizado(lomesPrimario.getArbolCurricularLiteral(),"/"), Field.Store.YES,Field.Index.TOKENIZED,
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
////				
////				//Sin acentos
////				String[] atomizadoSinAcentos = arrayString2ArrayStringAtomizado(lomesPrimario.getArbolCurricularLiteral(),"/");
////				aniadeFieldaDocument(doc, props.getProperty("campo_areaCurricular_literal_sin_acentos"), 
////					removeAccents(atomizadoSinAcentos), Field.Store.YES,Field.Index.TOKENIZED,
////					Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			//TRATAMIENTO DE LA DESCRIPCION DE OBJETIVOS
//				aniadeFieldaDocument(doc, props.getProperty("campo_descripcion_objetivo"), 
//						lomesPrimario.getDescripciones(), Field.Store.YES,Field.Index.UN_TOKENIZED,
//						Float.valueOf(props.getProperty("prioridad_por_defecto")).floatValue(), DocumentoIndexacion.INDEXADO_NOR);
//			
//			
////			Le damos el peso al documento en general dependiendo del peso del nivel de agregacion
//			doc.setBoost(Float.valueOf(""+valorPesoNivelAgregacion).floatValue());
//		
//		}
//
//		return doc;
//	}

	
	
	public static String[] obtenerFechaPublicacion(Date fecha) {

		String[] retorno = new String[2];		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HHmmss");
		
		retorno[0] = formatoFecha.format(fecha);		
		retorno[1] = formatoHora.format(fecha);		
		return retorno;
	}

	//Método que divide los identificadores de areas curriculares
	public static String[] arrayString2ArrayStringAtomizado(String[] array, String split)
	{
		String[] arrayFinal=null;
		List concatenado = new ArrayList();
		for(int i=0; array != null && array.length > 0 && i<array.length; i++){
			arrayFinal=array[i].split(split);
			for(int j=1;arrayFinal != null && arrayFinal.length > 1 && j<arrayFinal.length && !arrayFinal[j].equals("");j++){
				concatenado.add(arrayFinal[0]+split+arrayFinal[j]);
				logger.info("DocumentoIndexacion.arrayString2ArrayStringAtomizado. concatenado: ["+concatenado+"]");
			}
		}
		if (concatenado.size() > 0)
			return (String[])concatenado.toArray(new String[concatenado.size()]);
		return new String[]{""};
		
	}
	
//	Método que divide los identificadores de areas curriculares y solo se queda con el ultimo nodo
	public static String[] arrayString2ArrayStringAtomizadoUltimo(String[] array, String split)
	{
		if (array == null || array.length == 0)
			return new String[] {""};
//		Cada entrada del array de strings es un string con eltos separados por "split". Solo me quiero quedar con
//		el ultimo de la cadena.
		String[] arrayFinal=null;
		List<String> concatenado = new ArrayList<String>();
		for(int i=0; array != null && array.length > 0 && i<array.length; i++){
			arrayFinal=array[i].split(split);
			String ultimo = "";
//			Con esto recorremos todos los items y nos quedamos con el ultimo.
			for(int j=1;arrayFinal != null && arrayFinal.length > 1 && j<arrayFinal.length && !arrayFinal[j].equals("");j++){
				ultimo = arrayFinal[0]+split+arrayFinal[j];
			}
			concatenado.add(ultimo);
		}
		if (concatenado.size() > 0)
			return concatenado.toArray(new String[concatenado.size()]);
		return new String[]{""};
	}
	
		//Método auxiliar que transforma un String[] a String.
		public static String arrayToString (String[] valor){
			StringBuilder cadena=new StringBuilder("");
			if(valor != null && valor.length>0){
				for (int i=0; i<valor.length; i++){
					cadena.append(valor[i].toString());
					if((i+1)<valor.length) cadena.append(SrvIndexadorServiceImpl.SEPARADOR_CAMPOS_MULTIVALUADOS);
				}
			}
			return cadena.toString();
		}
		
		
		private static final String SEPARADOR_NOM_ORG = "|$|";
		private static final String SEPARADOR_ENTIDADES = "|&|";
		private static final String SEPARADOR_TIPO_FECHA = "|%|";
		private static final String SPLIT_SEPARADOR_NOM_ORG = "\\|\\$\\|";
		private static final String SPLIT_SEPARADOR_ENTIDADES = "\\|&\\|";
		private static final String SPLIT_SEPARADOR_TIPO_FECHA = "\\|%\\|";
		
		public static String obtenerContribucionesIndexar(ContribucionVO[] contribuciones){
			StringBuffer sbContribuciones = new StringBuffer("");
			
			if(contribuciones!=null && contribuciones.length>0){
				for(int i=0;i< contribuciones.length;i++){
					String tipo = contribuciones[i].getCodTipoContribucion();
					sbContribuciones.append(tipo).append(SEPARADOR_TIPO_FECHA);
					String fecha = contribuciones[i].getFecha();
					sbContribuciones.append(fecha);
					EntidadVO[] entidades = contribuciones[i].getEntidades();
					if(entidades!=null && entidades.length>0){
						for(int j=0;j<entidades.length;j++){
							sbContribuciones.append(SEPARADOR_ENTIDADES);
							String nombre = entidades[j].getNombre();
							sbContribuciones.append(nombre);
							sbContribuciones.append(SEPARADOR_NOM_ORG);
							String organizacion = entidades[j].getOrganizacion();
							sbContribuciones.append(organizacion);
						}
					}
					if(i<contribuciones.length-1) sbContribuciones.append(SrvIndexadorServiceImpl.SEPARADOR_CAMPOS_MULTIVALUADOS);
				}
				
			}
			return sbContribuciones.toString();
		}
		
		
		public static ContribucionVO[] obtenerContribucionVO(String[] contribuciones){
			if(contribuciones!=null && contribuciones.length>0){
				ContribucionVO[] aContribucionVO = new ContribucionVO[contribuciones.length];
				for(int i = 0; i< contribuciones.length;i++){
					ContribucionVO contribVO = new ContribucionVO();
					String sContribucion = contribuciones[i];
//					La posición cero del array partes pertenece a los valores tipo de contribución y fecha de contribución, 
//					el resto de posiciones del array son entidades(nombre y organización).
//					Siempre tendra dos posiciones pues el tipo de contribución y la fecha son obligatorios
					String[] partes = sContribucion.split(SPLIT_SEPARADOR_ENTIDADES);
					String tipoYfecha = partes[0];
					String[] aTipoYfecha = tipoYfecha.split(SPLIT_SEPARADOR_TIPO_FECHA);
					contribVO.setCodTipoContribucion(aTipoYfecha[0]);
					contribVO.setFecha(aTipoYfecha[1]);
					EntidadVO[] entidades = new EntidadVO[partes.length>0?partes.length-1:0];
					for(int j = 0; j< entidades.length;j++){
						EntidadVO entidadVO = new EntidadVO();
						String[] entidad = partes[j+1].split(SPLIT_SEPARADOR_NOM_ORG);
						if(entidad.length>0){
							entidadVO.setNombre(entidad[0]);
							if(entidad.length>1)
								entidadVO.setOrganizacion(entidad[1]);
						}
						entidades[j]=entidadVO;
					}
					contribVO.setEntidades(entidades);
					
					aContribucionVO[i] = contribVO;
				}
				return aContribucionVO;
			}
			return null;
		}
}