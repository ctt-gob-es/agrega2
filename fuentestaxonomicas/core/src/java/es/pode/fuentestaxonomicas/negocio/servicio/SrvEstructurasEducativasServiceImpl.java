/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.fuentestaxonomicas.negocio.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import es.pode.fuentestaxonomicas.negocio.soporte.VdexComparator;
import es.pode.soporte.i18n.I18n;
import es.pode.validador.negocio.servicio.ValidaVdexVO;



/**
 * @see es.pode.fuentestaxonomicas.negocio.dominio.SrvEstructurasEducativasService
 * 
 * 
 */

public class SrvEstructurasEducativasServiceImpl
    extends es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceBase
{
	 
	private static final String E_GENERICO="0";
//	private static final String E_EXISTE="1";
	private static final String E_FORMATO_NOMBRE="2";
	private static final String E_ID_NO_VALIDO="3";
	private static final String E_ID_DISTINTO="4";
	private static final String E_NOMBRE_DUPLICADO="5";
	private static final String E_ID_DUPLICADO="6";
	private static final String E_NO_VALIDO="10";
	

	private static final String SUF_TESAURO= "TES";
	private static final String SUF_TAXONOMIA= "TAX";
	
	private static Properties properties;
	
	private String getPropertyValue(
			String sKey) 
	throws IOException 
	{
		InputStream fIsProperties = this.getClass().getResourceAsStream("/fuentestaxonomicas.properties");
		if (SrvEstructurasEducativasServiceImpl.properties == null) {
			properties = new java.util.Properties();
			properties.load(fIsProperties);
		}
		fIsProperties.close();
		if (logger.isDebugEnabled())logger.debug("getPropertyValue: Propiedad recuperada: <" + sKey + "> : <"+ properties.getProperty(sKey)+">");
//		 devolvemos la propiedad
		return properties.getProperty(sKey);
	}

	/**
	 * 
	 */
	protected Boolean handleChequearIdiomaArbolCurricular
		(String nombre)
	throws Exception {
		
		boolean resultado= false;
		String idioma= obtenerIdioma(nombre);
		if(obtenerFicheroVDEX(idioma, TipoVdex.ARBOL )!=null)
		{
			if(logger.isDebugEnabled()) logger.debug("ya existe un Arbol curricular del mimo idioma del que se intenta subir");
			resultado=true;
		}
		return resultado;
	}

	
	
	
	protected Boolean handleChequearIdiomaTesauro(String nombre)
			throws Exception {
		boolean resultado= false;
		String idioma= obtenerIdioma(nombre);
		if(obtenerFicheroVDEX(idioma,TipoVdex.TESAURO)!=null)
		{
			if(logger.isDebugEnabled()) logger.debug("ya existe un Tesauro del mismo idioma del que se intenta subir");
			resultado=true;
		}
		return resultado;
	}

	

	/**
	 * Método que permite listar los TESAUROS existentes en la carpeta contenedora.
	 * 
	 * @return Array de VdexVO indicando todos los ficheros xml existentes en la carpeta contenedora. 
	 * 	si no existen ficheros, regresa un array vacio.
	 */
    protected VdexVO[] handleListarTesauros() throws Exception {
		return listarVDEX(TipoVdex.TESAURO);
	}
	
	

	


	/**
	 * Método que permite listar los ARBOLES CURRICULARES existentes en la carpeta contenedora.
	 * 
	 * @return Array de VdexVO indicando todos los ficheros xml existentes en la carpeta contenedora. 
	 * 	si no existen ficheros, regresa un array vacio.
	 */
	protected VdexVO[] handleListarArbolCurricular() throws Exception {
		return listarVDEX(TipoVdex.ARBOL );
	}

	

	protected VdexVO[] handleListarBackups() 
	throws Exception 
	{
		ArrayList<VdexVO> listaVdex= new ArrayList<VdexVO>();
		
		VdexVO[] listaTes = listarVDEX(TipoVdex.TESAURO_BACK);
		listaVdex.addAll(Arrays.asList(listaTes));
		
		VdexVO[] listaTax = listarVDEX(TipoVdex.TAXONOMIA);
		listaVdex.addAll(Arrays.asList(listaTax));
		
		VdexVO[] resultado= listaVdex.toArray(new VdexVO[0]);
		
		Arrays.sort(resultado, new VdexComparator());
		
		return resultado;
	}

	


	/**
	 * Método privado que determina si el idioma es uno de los soportados por la plataforma, si es asi,
	 * regresa un String de 2 caracteres correspondiente al idioma. si el parámetro nombre no contiene 
	 * idioma o no es uno de los soportados por la plataforma, regresa un null.
	 * 
	 * @param nombre String que indica el nombre del fichero xml del cual se quiere conocer el idioma
	 * @return String indicando el idioma correspondiente. null si no se puede determinar el idioma en
	 * el nombre del fichero xml.
	 * 
	 */
	private String obtenerIdioma(String nombre) throws Exception
	{
		String idioma=null;
		if(nombre==null || nombre.equals(""))
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha indicado el nombre del fichero xml. regresa null");
			return idioma;
		}
		
		String[] idiomas= I18n.getInstance().obtenerIdiomasBuscables();
		
		if(idiomas==null || idiomas.length==0)
		{
			if(logger.isDebugEnabled()) logger.debug("No hay idiomas disponibles en la plataforma");
			return idioma;
		}

		String term;
		int dif;
		for (int i = 0; i < idiomas.length && idioma==null; i++) 
		{
			term="_" + idiomas[i]+".xml";
			dif=nombre.length() - term.length();
			if(dif>0 && nombre.substring(dif).equals(term))
				idioma=idiomas[i];
		}
		
		if(idioma!=null){ 
			if(logger.isDebugEnabled()) logger.debug("el idioma del fichero es " + idioma);
		}else
			if(logger.isDebugEnabled()) logger.debug("no se pudo determinar el idioma del fichero");
		
		return idioma;
	}

	
	/**
	 * Método privado que comprueba si existe en la carpeta contenedora correspondiente algun Vdex
	 * del idioma indicado. Regresa un String con el nombre del fichero xml que se 
	 * corresponde con el idioma indicado. si no existe ningun Vdex de ese idioma, regresa null
	 * 
	 * @param idioma String de dos caracteres que indica el idioma que se quiere comprobar
	 * @param tipoVDEX indica el tipo de vdex (tesauro,taxonomia,arbol curricular)
	 * @return String indicando el nombre del fichero xml del Arbol curricular del idioma seleccionado
	 * 	null si no encuentra Arbol curricular del idioma indicado.
	 */
	private String obtenerFicheroVDEX(String idioma,TipoVdex tipoVDEX) throws Exception
	{
		String resultado= null;
		
		if(idioma==null || idioma.equals(""))
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha indicado el idioma.");
			return resultado;
		}
		String directorio= obtenerCarpetaVdex(tipoVDEX);
		if(directorio==null)
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha podido determinar la carpeta de arboles curriculares.");
			return resultado;
		}
		File carpeta= new File(directorio);
		
		if(carpeta.exists())
		{
			String[] nombres=carpeta.list();
			String term=idioma+".xml";
			for (int i = 0; i < nombres.length && resultado==null; i++) 
			{
				if(nombres[i].substring(nombres[i].length() - term.length()).equals(term))
				{
					if(logger.isDebugEnabled()) logger.debug("vdex del idioma " + idioma + " : <" + nombres[i]+">");
					resultado=nombres[i];
				}
			}
		}
		carpeta=null;
		
		if(resultado==null)
			if(logger.isDebugEnabled()) logger.debug("no se ha encontrado ningun vdex para el idioma " + idioma);
		return resultado;
	}

	
	
	/**
	 * Método privado que permite obtener la carpeta contenedora de ficheros xml de una determinada
	 * estructura educativa.
	 *  
	 * @param tipoVDEX indica el tipo de vdex (tesauro,taxonomia,arbol curricular)
	 * para el cual se solicita la carpeta contenedora.
	 * @return regresa un String con el path de la carpeta contenedora de xml para el tipo de vdex 
	 * seleccionado. regresa null si no puede determinar la carpeta para el tipo vdex indicado
	 */
	private String obtenerCarpetaVdex(TipoVdex tipoVDEX) throws Exception
	{
		String resultado=null;
		if(tipoVDEX==null)
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha indicado el tipo de vdex");
			return resultado;
		}
		if(TipoVdex.TESAURO.toString().equals(tipoVDEX.toString()))
		{
			resultado= getPropertyValue("rutaTesauro");
		}else if(TipoVdex.TESAURO_BACK.toString().equals(tipoVDEX.toString()))
		{
			resultado= getPropertyValue("rutaTesauroBackup");
		}else if(TipoVdex.TAXONOMIA.toString().equals(tipoVDEX.toString()))
		{
			resultado= getPropertyValue("rutaTaxonomia");
		}else if(TipoVdex.ARBOL.toString().equals(tipoVDEX.toString()))
		{
			resultado= getPropertyValue("rutaArbolCurricular");
		} 
		
		File carpetaFile= new File(resultado);
		if(!carpetaFile.exists())
		{
			carpetaFile.mkdirs();
		}else{
			if(!carpetaFile.canRead())
				if(logger.isDebugEnabled())logger.debug("la carpeta " + resultado + " no tiene permisos de escritura.");
		}
		carpetaFile =null;
			
		if(logger.isDebugEnabled()) logger.debug("vdex seleccionado:" + tipoVDEX +" path correspondiente " + resultado);
		
		return resultado;
	}

	/**
	 * Método privado que permite eliminar una lista de ficheros xml
	 *  de la correspondiente carpeta contenedora. 
	 * 
	 * @param nombres array de String con los nombres de ficheros xml de los vdex que se desea eliminar.
	 * @param tipoVDEX String que indica el tipo de vdex a eliminar.
	 * @return regresa un array de enteros. cada entero tiene 3 posibles valores:
	 *  1 si se pudo eliminar el fichero
	 *  0 si no se pudo eliminar el fichero
	 *  -1 solo en el caso que el tipovdex sea ARBOL y se ha intentado eliminar un arbol correspondiente al idioma castellano
	 */

//	private int[] eliminarVdex(String[] nombres,TipoVdex tipoVDEX)
//	throws Exception
//	{
//		if(nombres==null)
//		{
//			if(logger.isDebugEnabled()) logger.debug("el listado de arboles es null, regresa un array vacio ");
//			return new int[0];
//		}
//			
//
//		int[] resultado = new int[nombres.length];
//		// recorro la lista de nombres
//		for (int i = 0; i < nombres.length; i++) 
//		{
//			if( (TipoVdex.ARBOL.toString().equals(tipoVDEX.toString()) || 
//			   TipoVdex.TESAURO.toString().equals( tipoVDEX.toString() ) ) )
//			{
//				// si el tipoVDEX es Arbol Curricular verifico que sea  en español
//				// si es asi, no puedo eliminar el fichero y regreso un -1 en esa posicion
//				if(logger.isDebugEnabled()) logger.debug("el tipoVDEX es Arbol Curricular o Tesauro y se ha intentado eliminar el idioma Español, por lo que no se elimina el fichero");
//				resultado[i]=-1;
//			}else{
//				if(eliminarVDEX(nombres[i], tipoVDEX).booleanValue())
//				{
//					//se ha podido eliminar el fichero con exito
//					if(logger.isDebugEnabled()) logger.debug("se ha eliminado el fichero : " + nombres[i]);
//					resultado[i]=1;
//				}
//				else
//				{
//					// no se ha podido eliminar el fichero
//					if(logger.isDebugEnabled()) logger.debug("NO SE HA PODIDO ELIMINAR EL FICHERO: " + nombres[i]);
//					resultado[i]=0;
//				}
//			}
//		}
//		
//		return resultado;
//	}
	

	/**
	 * Método privado que permite eliminar el fichero xml (corespondiente al vdex indicado)
	 *  de la correspondiente carpeta contenedora. 
	 * 
	 * @param nombre String nombre del fichero xml del vdex que se quiere eliminar.
	 * @param tipoVDEX String que indica el tipo de vdex a eliminar.
	 * @return regresa un True si se pudo eliminar el fichero xml y un false si no se pudo eliminar.
	 */
	private Boolean eliminarVDEX(String nombre,TipoVdex tipoVDEX) throws Exception
	{
		boolean resultado= false;
		
		if(nombre==null || nombre.equals(""))
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha indicado el nombre del fichero xml.");
			return new Boolean(resultado);
		}
		String directorio= obtenerCarpetaVdex(tipoVDEX);
		if(directorio==null)
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha podido determinar la carpeta contenedora");
			return new Boolean(resultado);
		}
		File fichero= new File( directorio+nombre);
		if(fichero.exists())
		{
			if(logger.isDebugEnabled()) logger.debug("se procede a eliminar el fichero <" +  fichero.getAbsolutePath()+">");
			fichero.delete();
			resultado=true;
		}
		fichero=null;
		if(!resultado)
			if(logger.isDebugEnabled()) logger.debug("no se ha podido eliminar el fichero indicado");
		return resultado;
	}




	/**
	 * Método privado que permite listar los ficheros xml existentes en la carpeta contenedora del 
	 * vdex indicado
	 * 
	 * @param tipoVDEX String que indica el tipo de vdex del cual se quiere obtener un listado
	 * @return Array de VdexVO indicando todos los ficheros xml existentes en la carpeta contenedora. 
	 * 	si no existen ficheros, regresa un array vacio.
	 */
	private VdexVO[] listarVDEX(TipoVdex tipoVDEX) throws Exception
	{
		VdexVO[] listaVdex=new VdexVO[0];
		String directorio= obtenerCarpetaVdex(tipoVDEX);
		if(directorio==null)
		{
			if(logger.isDebugEnabled()) logger.debug("no se ha podido determinar la carpeta contenedora del tipoVdex indicado");
			return listaVdex;
		}
		File fDir= new File(directorio);
		if(fDir.exists() && fDir.isDirectory())
		{	
			String[] lista= fDir.list();
			
			listaVdex =new VdexVO[lista.length];
			for (int i = 0; i < lista.length ; i++) 
			{
				if(logger.isDebugEnabled()) logger.debug("listaVDEX[" + i + "]: <" + lista[i]+">");
				VdexVO vdex= new VdexVO();
				vdex.setNombre(lista[i]);
				vdex.setCodigoError("");
				
				if(TipoVdex.TESAURO_BACK.toString().equals(tipoVDEX.toString()))
				{
					vdex.setSufijo(SUF_TESAURO);
				}else if(TipoVdex.TAXONOMIA.toString().equals(tipoVDEX.toString()))
				{
					vdex.setSufijo(SUF_TAXONOMIA);
				}
				
				listaVdex[i]=vdex;
			}
		}
		if(listaVdex.length >0)
			if(logger.isDebugEnabled()) logger.debug("la carpeta contenedora está vacia, se devuelve un array vacio");

		Arrays.sort(listaVdex, new VdexComparator());
		
		return listaVdex;
	}
	



	
	/**
	 * Método privado que permite añadir un nuevo Vdex a la carpeta contenedora correspondiente.
	 * Realiza diferentes comprobaciones antes de añadir el nuevo fichero xml.
	 * 
	 * @param dataHandler contenido del fichero xml que se desea añadir
	 * @param nombre String que indica el nombre del fichero xml que se desea añadir
	 * @param tipoVdex  String que indica el tipo de VDEX que se desea añadir
	 * @return regresa siempre objeto VdexVO. si se ha podido añadir el fichero, el campo codigoError esta vacio, 
	 * si no se ha podido añadir el campo codigoError contiene el codigo del error correspondiente 
	 */
	private VdexVO nuevoVDEX(
			String vdexTemp,
			String nombre,
			String vocabIdentifier,
			String vocabName,
			TipoVdex tipoVdex) 
	throws Exception
	{
		VdexVO resultado=new VdexVO();
		resultado.setNombre("");
		resultado.setCodigoError("");
		
    	//chequear parametros
    	if(vdexTemp==null || nombre==null || nombre.equals(""))
    	{
    		if(logger.isDebugEnabled()) logger.debug("el datahandler es null");
    		resultado.setCodigoError(E_GENERICO);// regreso un error generico
    		return resultado;
    	}
    	//eliminar espacios en el nombre
		nombre= nombre.trim().replaceAll(" ", "_");
    	

		//comprobar si el idioma existe en el nombre y si es un idioma válido
		String idioma=obtenerIdioma(nombre);

		if(idioma==null)
		{
			if(logger.isDebugEnabled()) logger.debug("el fichero no termina con ninguno de los idiomas especificados");
    		resultado.setCodigoError(E_FORMATO_NOMBRE);//regreso un error de formato del nombre
    		return resultado;
		}
    	

		//verificar si existe el vocabname
		//se supone que el fichero es valido y tiene vocabidentifier
		String idVocabNameExistente = this.getEstructurasDao().existeVocabName(vocabName);
		if(idVocabNameExistente!=null)
		{
			if(!vocabIdentifier.equals(idVocabNameExistente))
			{
				resultado.setCodigoError(E_NOMBRE_DUPLICADO);
				
				if(this.getEstructurasDao().obtenerTaxonomiaPorId(idVocabNameExistente)!=null )
				{
					resultado.setVocabNameDuplicado(this.getEstructurasDao().obtenerTaxonomiaPorId(idVocabNameExistente).getNombre());
				}else
					resultado.setVocabNameDuplicado(this.getEstructurasDao().obtenerTesauroPorId(idVocabNameExistente).getNombre());
				return resultado;
			}
		}
		
		//verificar si existe el vocabidentifier
		String vocabNameExistente = this.getEstructurasDao().existeVocabIdentifier(vocabIdentifier);
		if(vocabNameExistente!=null && !vocabName.equals(vocabNameExistente) )
		{
			resultado.setCodigoError(E_ID_DUPLICADO);
			resultado.setVocabNameDuplicado(vocabNameExistente);
			return resultado;
		}
		
		//eliminar el fichero existente si es el mismo nombre que el que estoy subiendo
		String rutaFinal = obtenerCarpetaVdex(tipoVdex);
		String ficheroExistente= this.getEstructurasDao().obtenerNombreFicheroPorId(vocabIdentifier, idioma);
		if( ficheroExistente!=null )
		{
			if(!nombre.equals(ficheroExistente))
			{
				File ficheroEliminar = new File(rutaFinal+ "/" + ficheroExistente);
				ficheroEliminar.delete();
				ficheroEliminar=null;
			}
		}
	
		
		//muevo el fichero a la carpeta destino
		boolean subido=moverFichero(vdexTemp, rutaFinal , nombre);
		if(subido)
		{
			if(logger.isDebugEnabled()) logger.debug("se ha movido el fichero xml a la carpeta contenedora correspondiente");
		}else{
			if(logger.isDebugEnabled()) logger.debug("no se ha podido mover el fichero xml a la carpeta contenedora");
    		resultado.setCodigoError(E_GENERICO);
    		return resultado;
		}
		
		resultado.setNombre(nombre);
        return resultado;
	}

	/**
	 * Metodo privado utilizado para mover ficheros xml a un directorio dado
	 * 
	 * @param fileOrigen path del fichero que se desea mover
	 * @param carpetaDestino path al directorio destino donde se desea mover el fichero
	 * @param nombreFichero nombre que se desea que tenga el fichero en la carpeta destino
	 * @return true si se ha podido mover el fichero; false si no se ha podido mover el fichero
	 */
	private boolean moverFichero(
			String fileOrigen,
			String carpetaDestino,
			String nombreFichero) 
	throws Exception
	{
		boolean resultado;
		File fOrigen= new File(fileOrigen);
		if(logger.isDebugEnabled()) logger.debug("fichero que se va a mover: <" + fOrigen.getAbsolutePath()+">");
		File dirDestino=new File(carpetaDestino);
		if(logger.isDebugEnabled()) logger.debug("carpeta destino: <" + dirDestino.getAbsolutePath()+">");
		File fileDestino= new File(carpetaDestino + nombreFichero);
		if(logger.isDebugEnabled()) logger.debug("fichero destino: <" + fileDestino.getAbsolutePath()+">");
		if(fileDestino.exists())
			fileDestino.delete();
		
		resultado= fOrigen.renameTo(new File(dirDestino,nombreFichero));
		if(resultado)
		{
			if(logger.isDebugEnabled()) logger.debug("el fichero se ha movido con exito");
		}else
		{
			if(logger.isDebugEnabled()) logger.debug("el fichero no se pudo mover");
		}
		
		return resultado;

	}
	
	
	/**
	 * Método privado que permite buscar un fichero xml con el nombre y tipo vdex indicado.
	 * Si encuentra el fichero, regresa un objeto VdexVO con los datos del fichero encontrado, 
	 * si no lo encuentra, regresa null.
	 * 
	 * @param nombre String que indica el nombre del fichero que se quiere recuperar.
	 * @param tipoVDEX String indicando el tipo de VDEX que se quiere buscar
	 * @return VdexVO indicando el nombre del fichero encontrado. regresa null en cualquiel otro caso.
	 */
//	private VdexVO buscarVDEX(
//			String nombre, 
//			TipoVdex tipoVDEX) 
//	throws Exception
//	{
//		VdexVO resultado=null;
//		if(nombre==null || nombre.equals(""))
//		{
//			if(logger.isDebugEnabled()) logger.debug("no se a indicado el nombre del vdex");
//			return resultado;
//		}
//		String directorio= obtenerCarpetaVdex(tipoVDEX);
//		if(directorio==null)
//		{
//			if(logger.isDebugEnabled()) logger.debug("no se ha podido determinar la carpeta contenedora del vdex indicado");
//			return resultado;
//		}
//			
//		File fDir= new File(directorio);
//		if(fDir.exists() && fDir.isDirectory())
//		{	
//			String[] lista= fDir.list();
//			for (int i = 0; i < lista.length && resultado==null; i++) 
//			{
//				if(nombre.equals(lista[i]))
//				{
//					if(logger.isDebugEnabled()) logger.debug("se ha encontrado el vdex indicado: " + nombre);
//					resultado= new VdexVO();
//					resultado.setNombre(nombre);
//				}
//					
//			}
//		}
//		if(resultado==null ) 
//			if(logger.isDebugEnabled()) logger.debug("no se ha podido encontrar el vdex indicado");
//		return resultado;
//	}
    



	protected DataHandler handleExportarVdex(
			String nombre, 
			TipoVdex tipoVdex)
	throws Exception 
	{
		DataHandler resultado=null;
		try{
			String carpeta= obtenerCarpetaVdex(tipoVdex);

			if(carpeta!=null)
			{
				File fichero = new File(carpeta + nombre);
				if(fichero.exists())
				{
					DataSource ds= new FileDataSource(fichero);
					resultado= new DataHandler(ds);
				}
				fichero=null;
			}
		}catch(Exception e)
		{
			logger.warn("error en exportarVdex:  - " + e.getMessage());
			if(logger.isDebugEnabled())logger.debug(e);
		}
		
		return resultado;
	}




	
	protected String[] handleObtenerIdsConVocabIdentifier(
			String[] ids)
	throws Exception 
	{
		String[] traducidos =null;
		try{
			traducidos= this.getEstructurasDao().traducirVocabNames(ids);
		}catch (Exception e) {
			logger.error("error al obtener EstructurasDao().traducirVocabNames(ids) - " ,e);
		}
		return traducidos;
	}





	protected VdexListaVO[] handleListarFicherosTaxonomias() 
	throws Exception 
	{
		return this.getEstructurasDao().obtenerFicherosTaxonomias();
	}





	protected VdexListaVO handleListarFicherosTaxonomia(
			String nombre)
	throws Exception 
	{
		return this.getEstructurasDao().obtenerTaxonomiaPorId(nombre);
	}




	protected VdexVO[] handleSubirFicherosVdex(
			ParamVdexVO[] listaVdex, 
			TipoVdex tipo)
	throws Exception 
	{
		VdexVO[] resultado=new VdexVO[listaVdex.length];

		
		String[] listaParametros= new String[listaVdex.length];
		ValidaVdexVO[] listaValidados= validarListaVdex(listaVdex,tipo,listaParametros);
		
		// verifico que todos los ficheros a subir sean del mismo identificador
		boolean mismoIdentificador= true;
		String idAnterior=null;
		for (int i = 0; i < listaValidados.length && mismoIdentificador; i++) {
			if(idAnterior==null)
			{
				idAnterior = listaValidados[i].getVocabIdentifier();
			}else
			{
				if(listaValidados[i].getVocabIdentifier()!=null && !idAnterior.equals(listaValidados[i].getVocabIdentifier()))
					mismoIdentificador= false;
			}
			
		}
		
		
		
		// llamo al metodo nuevoVDEX solo para los ficheros que hayan pasado la validacion
		int i;
		for (i = 0; i < listaVdex.length; i++) 
		{
			if(!mismoIdentificador)
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_ID_DISTINTO);
				resultado[i] = error;
				
			}else if(listaValidados[i].getValido().booleanValue())
			{
				VdexVO error=null;
				String idVocabNameExistente = this.getEstructurasDao().existeVocabName(listaValidados[i].getVocabName());
				if(idVocabNameExistente!=null)
				{
					error= new VdexVO();
					error.setCodigoError(E_ID_DUPLICADO);
					if(this.getEstructurasDao().obtenerTaxonomiaPorId(idVocabNameExistente)!=null )
					{
						error.setVocabNameDuplicado(this.getEstructurasDao().obtenerTaxonomiaPorId(idVocabNameExistente).getNombre());
					}else
						error.setVocabNameDuplicado(this.getEstructurasDao().obtenerTesauroPorId(idVocabNameExistente).getNombre());
					
				}
				
				//verificar si existe el vocabidentifier
				String vocabNameExistente = this.getEstructurasDao().existeVocabIdentifier(listaValidados[i].getVocabIdentifier());
				if(vocabNameExistente!=null && !listaValidados[i].getVocabName().equals(vocabNameExistente) )
				{
					error= new VdexVO();
					error.setCodigoError(E_NOMBRE_DUPLICADO);
					error.setVocabNameDuplicado(vocabNameExistente);
				}
				
				
				if(error!=null)
				{
					resultado[i] = error;
				}else
				{
					resultado[i]= nuevoVDEX(listaParametros[i], listaVdex[i].getNombreFichero(), listaValidados[i].getVocabIdentifier(),listaValidados[i].getVocabName() ,  tipo);
					logger.debug("llamo al metodo nuevoVdex para el fichero: <" + listaVdex[i].getNombreFichero()+">");
				}
			}else
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_NO_VALIDO);
				error.setErroresParseo(listaValidados[i].getErrores());
				resultado[i] = error;
			}
		}
		
		// si se ha subido al menos un fichero sin error, llamo a la actualización que corresponda
		// recojo los errores 
		boolean actualizar=false;
		for (i = 0; i < resultado.length && !actualizar; i++) 
		{
			if(resultado[i].getCodigoError()!=null && resultado[i].getCodigoError().equals(""))
			{
				logger.debug("hubo un error con el siguiente fichero: <" + listaVdex[i].getNombreFichero()+">");
				actualizar=true;
			}
		}
		
		// si se ha subido al menos un fichero sin error, llamo a la actualización que corresponda
		if(actualizar)
		{
			if(TipoVdex.ARBOL.toString().equals(tipo.toString()) || TipoVdex.TAXONOMIA.toString().equals(tipo.toString()))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar taxonomias.");
				this.getEstructurasDao().recargarTaxonomias();
			}else if(TipoVdex.TESAURO.toString().equals(tipo.toString()) || TipoVdex.TESAURO_BACK.toString().equals(tipo.toString()))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar tesauros.");
				this.getEstructurasDao().recargarTesauros();
			}
		}

		//elimino los ficheros temporales
		for (i = 0; i < listaParametros.length; i++) {
			File temp= new File(listaParametros[i]);
			if(temp.exists())
				temp.delete();
		}
		return resultado;

	}





	protected VdexVO[] handleActualizarFicherosVdex(
			ParamVdexVO[] listaVdex,
			TipoVdex tipo, 
			String identificador) 
	throws Exception 
	{
		VdexVO[] resultado=new VdexVO[listaVdex.length];

		
		String[] listaParametros= new String[listaVdex.length];
		ValidaVdexVO[] listaValidados= validarListaVdex(listaVdex,tipo,listaParametros);
		
			
		// llamo al metodo nuevoVDEX solo para los ficheros que hayan pasado la validacion
		int i;
		for (i = 0; i < listaVdex.length; i++) 
		{
			if(listaValidados[i].getValido().booleanValue() && identificador.equals(listaValidados[i].getVocabIdentifier() ))
			{
				logger.debug("llamo al metodo nuevoVdex para el fichero: " + listaVdex[i].getNombreFichero());
				resultado[i]= nuevoVDEX(listaParametros[i], listaVdex[i].getNombreFichero(), listaValidados[i].getVocabIdentifier(), listaValidados[i].getVocabName() ,  tipo);
			}
			else if(listaValidados[i].getVocabIdentifier() !=null &&
					!identificador.equals(listaValidados[i].getVocabIdentifier() ))
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_ID_NO_VALIDO);
				resultado[i] = error;
			}else
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_NO_VALIDO);
				error.setErroresParseo(listaValidados[i].getErrores());
				resultado[i] = error;
			}

		}
		
		// recojo los errores 
		boolean actualizar=false;
		for (i = 0; i < resultado.length && !actualizar; i++) 
		{
			if(resultado[i].getCodigoError()!=null && resultado[i].getCodigoError().equals(""))
			{
				logger.debug("hubo un error con el siguiente fichero: " + listaVdex[i].getNombreFichero());
				actualizar=true;
			}
		}
		
		// si se ha subido al menos un fichero sin error, llamo a la actualización que corresponda
		if(actualizar)
		{
			if(TipoVdex.ARBOL.equals(tipo) || TipoVdex.TAXONOMIA.equals(tipo))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar taxonomias..");
				this.getEstructurasDao().recargarTaxonomias();
			}else if(TipoVdex.TESAURO.equals(tipo) || TipoVdex.TESAURO_BACK.equals(tipo))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar tesauros..");
				this.getEstructurasDao().recargarTesauros();
			}
		}

		//elimino los ficheros temporales
		for (i = 0; i < listaParametros.length; i++) {
			File temp= new File(listaParametros[i]);
			if(temp.exists())
				temp.delete();
		}
		
		return resultado;
	}


	
	




	protected VdexListaVO[] handleListarFicherosTesauros() 
	throws Exception 
	{
		return this.getEstructurasDao().obtenerFicherosTesauros();
	}


	protected VdexListaVO handleListarFicherosTesauro(
			String identificador)
	throws Exception {
		return this.getEstructurasDao().obtenerTesauroPorId(identificador);
	}



	protected int[] handleEliminarFicherosVdex(
			ParamEliminarVO[] listaEliminar)
	throws Exception 
	{
		int[] resultado = new int[listaEliminar.length];
		boolean exitoTax= false;
		boolean exitoTes= false;
		
		for (int i = 0; i < listaEliminar.length; i++) {
			ParamEliminarVO e= listaEliminar[i];
			
			if(eliminarVDEX(e.getNombre(), e.getTipo()).booleanValue())
			{
				//se ha podido eliminar el fichero con exito
				if(logger.isDebugEnabled()) logger.debug("se ha eliminado el fichero: <" + e.getNombre()+">");
				resultado[i]=1;
				if(TipoVdex.ARBOL.toString().equals(e.getTipo().toString())
					|| TipoVdex.TAXONOMIA.toString().equals(e.getTipo().toString())	)
				{
					exitoTax=true;
				}else
					exitoTes= true;
				
			}
			else
			{
				// no se ha podido eliminar el fichero
				if(logger.isInfoEnabled()) logger.info("NO SE HA PODIDO ELIMINAR EL FICHERO: <" + e.getNombre()+">");
				resultado[i]=0;
			}

		}
		
		if(exitoTax && exitoTes)
			this.getEstructurasDao().recargarDatos();
		else if(exitoTax)
			this.getEstructurasDao().recargarTaxonomias();
		else
			this.getEstructurasDao().recargarTesauros();
		return resultado;
		
	}

	protected VdexVO[] handleActualizarVigente(
			ParamVdexVO[] listaVdex,
			TipoVdex tipo) 
	throws Exception 
	{
		
		VdexVO[] resultado=new VdexVO[listaVdex.length];

		
		String[] listaParametros= new String[listaVdex.length];
		ValidaVdexVO[] listaValidados=  validarListaVdex(listaVdex,tipo,listaParametros);
		
		// verifico que todos los ficheros a subir sean del mismo identificador
		boolean mismoIdentificador= true;
		String idAnterior=null;
		for (int i = 0; i < listaValidados.length && mismoIdentificador; i++) {
			if(idAnterior==null)
			{
				idAnterior = listaValidados[i].getVocabIdentifier();
			}else
			{
				if(listaValidados[i].getVocabIdentifier()!=null && !idAnterior.equals(listaValidados[i].getVocabIdentifier()))
					mismoIdentificador= false;
			}
			
		}
		
		// llamo al metodo nuevoVDEX solo para los ficheros que hayan pasado la validacion
		boolean vigentesMovidos=false;
		int i;
		for (i = 0; i < listaVdex.length; i++) 
		{
			if(!mismoIdentificador)
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_ID_DISTINTO);
				resultado[i] = error;
			}else if(listaValidados[i].getValido().booleanValue() )
			{
				resultado[i]= actualizarVigente(
										listaParametros[i], 
										listaVdex[i].getNombreFichero(), 
										listaValidados[i].getVocabIdentifier(),
										listaValidados[i].getVocabName() , 
										tipo);
				if(!vigentesMovidos && resultado[i]!=null && (resultado[i].getCodigoError()==null || resultado[i].getCodigoError().equals("") ) )
				{
					vigentesMovidos=true;
					moverFicherosVdexVigente(tipo );
				}
				logger.debug("llamo al metodo nuevoVdex para el fichero: <" + listaVdex[i].getNombreFichero()+">");
			}else
			{
				VdexVO error= new VdexVO();
				error.setCodigoError(E_NO_VALIDO);
				error.setErroresParseo(listaValidados[i].getErrores());
				resultado[i] = error;
			}
		}
		
		// si se ha subido al menos un fichero sin error, llamo a la actualización que corresponda
		// recojo los errores 
		boolean actualizar=false;
		for (i = 0; i < resultado.length && !actualizar; i++) 
		{
			if(resultado[i].getCodigoError()!=null && resultado[i].getCodigoError().equals(""))
			{
				logger.debug("hubo un error con el siguiente fichero: <" + listaVdex[i].getNombreFichero()+">");
				actualizar=true;
			}
		}
		
		// si se ha subido al menos un fichero sin error, llamo a la actualización que corresponda
		if(actualizar)
		{
			if(TipoVdex.ARBOL.toString().equals(tipo.toString()) || TipoVdex.TAXONOMIA.toString().equals(tipo.toString()))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar taxonomias.");
				this.getEstructurasDao().recargarTaxonomias();
			}else if(TipoVdex.TESAURO.toString().equals(tipo.toString()) || TipoVdex.TESAURO_BACK.toString().equals(tipo.toString()))
			{
				logger.debug("no hubo errores, por lo que llamo a recargar tesauros.");
				this.getEstructurasDao().recargarTesauros();
			}
		}

		//elimino los ficheros temporales
		for (i = 0; i < listaParametros.length; i++) {
			File temp= new File(listaParametros[i]);
			if(temp.exists())
				temp.delete();
		}
		return resultado;

	}
	
	
	private ValidaVdexVO[] validarListaVdex(
			ParamVdexVO[] listaVdex,
			TipoVdex tipo,
			String[] listaParametros)
	throws Exception
	{
		//verifico si existe la carpeta temporal
		File carpetaTemp= new File(getPropertyValue("rutaTemp"));
		if (!carpetaTemp.exists())
		{
			carpetaTemp.mkdirs();
		}
		//bajo los ficheros de los datahandler a disco duro
		for (int i = 0; i < listaVdex.length; i++) {
			
			File vdextemp= File.createTempFile(listaVdex[i].getNombreFichero(), 
							listaVdex[i].getNombreFichero().substring(listaVdex[i].getNombreFichero().length() - 7) ,
							carpetaTemp ) ;
			if(logger.isDebugEnabled()) logger.debug("fichero temporal: <" + vdextemp.getCanonicalPath()+">");
			FileOutputStream fos= new FileOutputStream(vdextemp);
			listaVdex[i].getFicheroXml().writeTo(fos);
			fos.flush();
			fos.close();
			listaParametros[i]= vdextemp.getCanonicalPath();
		}
		
		// valido todos los ficheros que he guardado en disco
		ValidaVdexVO[] listaValidados=null;
		if(TipoVdex.ARBOL.toString().equals(tipo.getValue().toString())
			|| TipoVdex.TAXONOMIA.toString().equals(tipo.getValue().toString())	)
		{
			listaValidados= this.getSrvValidadorVDEXService().obtenerValidacionTaxonomias(listaParametros);
		}else if(TipoVdex.TESAURO.toString().equals(tipo.getValue().toString())
			|| TipoVdex.TESAURO_BACK.toString().equals(tipo.getValue().toString())	)
		{
			listaValidados= this.getSrvValidadorVDEXService().obtenerValidacionTesauros(listaParametros);
		}
		return listaValidados;
	
	}

	
	
	
	private VdexVO actualizarVigente(
			String vdexTemp,
			String nombre,
			String vocabIdentifier,
			String vocabName,
			TipoVdex tipoVdex) 
	throws Exception
	{
		VdexVO resultado=new VdexVO();
		resultado.setNombre("");
		resultado.setCodigoError("");
		
    	//chequear parametros
    	if(vdexTemp==null || nombre==null || nombre.equals(""))
    	{
    		if(logger.isDebugEnabled()) logger.debug("el datahandler es null");
    		resultado.setCodigoError(E_GENERICO);// regreso un error generico
    		return resultado;
    	}
    	//eliminar espacios en el nombre
		nombre= nombre.trim().replaceAll(" ", "_");
		//comprobar si el idioma existe en el nombre y si es un idioma válido
		String idioma=obtenerIdioma(nombre);

		if(idioma==null)
		{
			if(logger.isDebugEnabled()) logger.debug("el fichero no termina con ninguno de los idiomas especificados");
    		resultado.setCodigoError(E_FORMATO_NOMBRE);//regreso un error de formato del nombre
    		return resultado;
		}
    
		//verificar si existe el vocabname
		//se supone que el fichero es valido y tiene vocabidentifier
		String idExistente = this.getEstructurasDao().existeVocabName(vocabName);
		if(idExistente!=null)
		{
			resultado.setCodigoError(E_NOMBRE_DUPLICADO);
			VdexListaVO vdexE =null;
			if(this.getEstructurasDao().obtenerTaxonomiaPorId(idExistente)!=null )
			{
				vdexE = this.getEstructurasDao().obtenerTaxonomiaPorId(idExistente);
			}else
				vdexE = this.getEstructurasDao().obtenerTesauroPorId(idExistente);
			
			if(vdexE!=null)
				resultado.setVocabNameDuplicado(vdexE.getNombre());
			else
				resultado.setVocabNameDuplicado("");
			return resultado;
		}
		
		//verificar si existe el vocabidentifier
		String vocabNameExistente = this.getEstructurasDao().existeVocabIdentifier(vocabIdentifier);
		if(vocabNameExistente!=null )
		{
			resultado.setCodigoError(E_ID_DUPLICADO);
			resultado.setVocabNameDuplicado(vocabNameExistente);
			return resultado;
		}

		//eliminar el fichero existente si es el mismo nombre que el que estoy subiendo
		String rutaFinal = obtenerCarpetaVdex(tipoVdex);
		//muevo el fichero a la carpeta destino
		boolean subido=moverFichero(vdexTemp, rutaFinal , nombre);
		if(subido)
		{
//			moverFicherosVdexVigente(tipoVdex);
			if(logger.isDebugEnabled()) logger.debug("se ha movido el fichero xml a la carpeta contenedora correspondiente");
		}else{
			if(logger.isDebugEnabled()) logger.debug("no se ha podido mover el fichero xml a la carpeta contenedora");
    		resultado.setCodigoError(E_GENERICO);
    		return resultado;
		}
		
		resultado.setNombre(nombre);
		return resultado;
	}
	
	
	private boolean moverFicherosVdexVigente(
			TipoVdex tipo)
	throws Exception
	{
		boolean resultado= false;
		String carpetaDestino;
		String carpetaOrigen= obtenerCarpetaVdex(tipo);
		String[] listaFicheros;
		List<String> listaCopiados= new ArrayList<String>();
		
		if(tipo.toString().equals(TipoVdex.ARBOL.toString()))
		{
			carpetaDestino= obtenerCarpetaVdex(TipoVdex.TAXONOMIA);
			String idVigente= this.getEstructurasDao().obtenerTaxonomia(getPropertyValue("arbolCurricular"), "es").getVocabIdentifier();
			listaFicheros =this.getEstructurasDao().obtenerTaxonomiaPorId(idVigente).getListaFicheros();
		}else
		{
			carpetaDestino= obtenerCarpetaVdex(TipoVdex.TESAURO_BACK);
			String idVigente= this.getEstructurasDao().obtenerIdTesauro(getPropertyValue("tesauroETB"), "es").getVocabIdentifier();
			listaFicheros =this.getEstructurasDao().obtenerTesauroPorId(idVigente).getListaFicheros();
		}
		
		boolean correcto= true;
		
			//copio los ficheros de su carpeta a la carpeta tesaurosback o taxonomias segun corresponda
		for (int i = 0; i < listaFicheros.length && correcto ; i++) 
		{
			File fOrigen= new File(carpetaOrigen + listaFicheros[i]);
			File dirDestino=new File(carpetaDestino);

			boolean eliminado=false;
			File fileDestino= new File(carpetaDestino + listaFicheros[i]);
			if(fileDestino.exists())
			{
				eliminado= fileDestino.delete();
				if(eliminado){
					logger.warn("No se ha podido eliminar el fichero: <" + fileDestino.getCanonicalPath()+">");
					correcto= false;
				}else{
					correcto= fOrigen.renameTo(new File(dirDestino,listaFicheros[i] ));
				}
			}else{
				correcto= fOrigen.renameTo(new File(dirDestino,listaFicheros[i] ));
			}

			if(correcto)
				listaCopiados.add(listaFicheros[i]);
			
			
			fOrigen=null;
			dirDestino=null;
			fileDestino=null;
		}
			
//			EN ESTE PUNTO LOS FICHEROS PODRÍAN ESTAR DUPLICADOS
//			TENGO QUE VERIFICAR SI NO HUBO ERROR AL COPIARLOS.. 
//			SI SE COPIARON CORRECTAMENTE TODOS LOS FICHEROS ELIMINO LOS DE LA CARPETA ORIGEN
//			SI NO SE COPIARON CORRECTAMENTE TODOS, ELIMINO LOS DE LA CARPETA DESTINO
//			String carpetaEliminar="";
			if(correcto)
			{
				resultado= true;
			}else
			{
				resultado=false;
				for (int i = 0; i < listaCopiados.size(); i++) {
					File ficheroRegreso= new File(carpetaDestino + listaCopiados.get(i) );
					File dirOrigen = new File(carpetaOrigen);
					
					ficheroRegreso.renameTo(new File(dirOrigen , listaCopiados.get(i)));
				}
			}
		
		return resultado;
	}

	@Override
	protected void handleRecargarTaxonomiasTesauros() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("handleRecargarTaxonomiasTesauros. Vamos a recargar taxanomías y tesauros");
		this.getEstructurasDao().recargarTaxonomias();
		this.getEstructurasDao().recargarTesauros();				
	}
}