/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.validador.negocio.servicio;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * @see es.pode.validador.negocio.servicio.SrvValidadorVDEXService
 */
 
public class SrvValidadorVDEXServiceImpl
    extends es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceBase
    implements ErrorHandler
{
    private DOMParser mParser;
	private static final String TESAURO= "TES";
	private static final String TAXONOMIA= "TAX";
    
	private static final String E_PARSEO="0";
	private static final String E_SIN_VOCABNAME="1";
	private static final String E_SIN_VOCABIDENTIFIER="2";
	/**
	 * Validación Tesauro: Esta validación chequea que el fichero esté bien formado y cumpla con lo 
	 * especificado por su corresponiente esquema. 
 	 * el esquema que se utiliza es imsvdex_v1p0_thesaurus.xsd
 	 * 
	 * @param rutaVdex Ruta donde se encuentra el fichero a validar
	 * @return ValidaVdexVO. Contiene el resultado de la validación
	 */
	protected ValidaVdexVO handleObtenerValidacionTesauro(
			String rutaVdex) 
	throws Exception 
	{
		ValidaVdexVO resultado = obtenerParseoConEsquemas(rutaVdex, TESAURO);
		return resultado;
	}

	/**
	 * Validación Taxonomia: Esta validación chequea que el fichero esté bien formado y cumpla con lo 
	 * especificado por su corresponiente esquema. 
	 * el esquema que se utiliza es imsvdex_v1p0_hierarchical.xsd
	 * 
	 * @param rutaVdex Ruta donde se encuentra el fichero a validar
	 * @return ValidaVdexVO. Contiene el resultado de la validación
	 */
	protected ValidaVdexVO handleObtenerValidacionTaxonomia(
			String rutaVdex) 
	throws Exception 
	{
		ValidaVdexVO resultado = obtenerParseoConEsquemas(rutaVdex, TAXONOMIA );
		return resultado;
	}

	
	/**
	 * este metodo realiza las tareas necesarias para validar el fichero contra los esquemas correspondientes.
	 * chequea que tipo de vdex es el que quiero validar y selecciona el esquema correspondiente para el mismo.
	 * 
	 * @param rutaVdex: ruta donde se encuentra el fichero a validar
	 * @param tipoVdex: tipo de vdex puede ser uno de los siguientes valores: "tesaruro" o "taxonomia"
	 * @return Boolean. Contiene el resultado de la validación
	 */
    private ValidaVdexVO obtenerParseoConEsquemas(
    		String rutaVDEX,
    		String tipoVdex)
    throws Exception
    {
    	boolean validadorRes = false;
    	String ficheroProperties = "/validador.properties";
		InputStream is= null;

		List<String> errores= new ArrayList<String>();
		String vocabIdentifier=null;
		String vocabName=null;
		
		mParser = new DOMParser();
    	
		if (mParser != null)
		 {
		    try
		      {
		    	is= this.getClass().getResourceAsStream(ficheroProperties);
				Properties fproperties = new Properties();
				fproperties.load(is);
				String namespaces="";
				
				if(tipoVdex.equals(TESAURO))
				{
					logger.debug("tipo de VDEX es tesauro");
					logger.debug("obtengo el nombre del xsd desde el properties");
					String imsvdex_tesauro = fproperties.getProperty("imsvdex_v1p0_thesaurus");
					File fileImsvdex_tesauro=new File(imsvdex_tesauro);
					String sProfile=fileImsvdex_tesauro.getCanonicalPath();
					logger.debug("obtengo la ruta completa al xsd");
					String nsProfile="http://www.imsglobal.org/xsd/imsvdex_v1p0";
					StringBuffer sb= new StringBuffer();
					sb.append(nsProfile + " " + sProfile);
					namespaces= sb.toString().replace('\\', '/');
					

				}else if(tipoVdex.equals(TAXONOMIA))
				{
					logger.debug("tipo de VDEX es taxonomia");
					logger.debug("obtengo el nombre del xsd desde el properties");
					String imsvdex = fproperties.getProperty("imsvdex_v1p0_hierarchical");
					File fileImsvdex=new File(imsvdex);
					logger.debug("obtengo la ruta completa al xsd");
					String sProfile=fileImsvdex.getCanonicalPath();
					String nsProfile="http://www.imsglobal.org/xsd/imsvdex_v1p0";
					StringBuffer sb= new StringBuffer();
					sb.append(nsProfile + " " + sProfile);
					namespaces= sb.toString().replace('\\', '/');
				}
				
			     mParser.setFeature("http://xml.org/sax/features/validation", true);
			     mParser.setFeature("http://xml.org/sax/features/namespaces", true);
			      
			     mParser.setFeature("http://apache.org/xml/features/validation/schema",true);
			      
			      mParser.setFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", true);
				
			      mParser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", namespaces);
			      /**********/
			      
			      mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
			      mParser.setErrorHandler(this);
			   }
			   catch (SAXException se )
			   {
			      String mensaje = "ERROR SAXException " + se;
			      logger.error(mensaje, se);
			      validadorRes=false;
			   }
			   Document docValido = null;
			   
				if ( !rutaVDEX.equals("") )
				{
					try
					{

						Object eo= mParser.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
						logger.debug(" Recupero los namespaces del mParser " + eo.toString());
						File documento= new File(rutaVDEX);
						
						if (documento.exists()) logger.debug("el documento existe..." );

						mParser.parse(rutaVDEX);
						docValido = mParser.getDocument();
						if ( docValido != null )
						{
							validadorRes = true;
//						}
						
						
		        		Element raiz = docValido.getDocumentElement(); 
		        		NodeList nodos= raiz.getElementsByTagName("vocabName");
		        		if(nodos.getLength()>0)
		        		{
		        			NodeImpl nodo= (NodeImpl) nodos.item(0);
		        			if(nodo.getChildNodes()!=null && nodo.getChildNodes().getLength()>0 && nodo.getChildNodes().item(1)!=null)
		        			{
			        			vocabName = ((NodeImpl) nodo.getChildNodes().item(1)).getTextContent();
			        			if(vocabName==null || vocabName.equals("") )
			        			{
				        			errores.add( E_SIN_VOCABNAME );
				        			validadorRes=false;
			        			}
		        			}else
		        			{
			        			errores.add( E_SIN_VOCABNAME );
			        			validadorRes=false;
		        			}
		        		}else{
		        			errores.add( E_SIN_VOCABNAME );
		        			validadorRes=false;
		        		}

		        		nodos= raiz.getElementsByTagName("vocabIdentifier");
		        		if(nodos.getLength()>0)
		        		{
		        			NodeImpl nodo= (NodeImpl) nodos.item(0);
		        			vocabIdentifier = nodo.getTextContent();
		        			if(vocabIdentifier==null || vocabIdentifier.equals(""))
		        			{
			        			errores.add( E_SIN_VOCABIDENTIFIER);
			        			validadorRes=false;
		        			}
		        		}else{
		        			errores.add( E_SIN_VOCABIDENTIFIER);
		        			validadorRes=false;
		        		}

						}
					}
					catch ( Exception se )
					{
						logger.error("Excepcion SAXException durante el parseo " , se);
						validadorRes=false;
						errores.add( E_PARSEO );
					}
				}
					
		 }
		
			ValidaVdexVO resultado= new ValidaVdexVO();
			resultado.setValido(validadorRes);
			resultado.setTipoVdex(tipoVdex);
			resultado.setErrores(errores.toArray(new String[0]));
		 	resultado.setVocabName(vocabName);
		 	resultado.setVocabIdentifier(vocabIdentifier);
			return resultado;
    }

	public void error(SAXParseException exception) throws SAXException {
		throw exception;
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		throw exception;
		
	}

	public void warning(SAXParseException exception) throws SAXException {
		throw exception;
		
	}

	/**
	 * Este método realiza la validación contra esquemas del fichero indicado por la ruta.
	 * el fichero puede ser tanto una taxonomía como un tesauro, por lo que se realizan 
	 * ambas validaciones devolviendo un value object que indica el resultado de la validación
	 * y el tipo de validación que cumple, permitiendonos así reconocer que tipo de vdex es 
	 * el fichero dado.
	 * 
	 * @param rutaVdex Ruta donde se encuentra el fichero a validar
	 * @return ValidaVdexVO. Contiene el resultado de la validación
	 */
	protected ValidaVdexVO handleObtenerValidacionVdex(
			String rutaVdex)
	throws Exception 
	{
		ValidaVdexVO resultadoTes = obtenerParseoConEsquemas(rutaVdex, TESAURO );
		ValidaVdexVO resultadoTax = obtenerParseoConEsquemas(rutaVdex, TAXONOMIA );

		if(resultadoTes.getValido())
		{
			return resultadoTes;
		}else if(resultadoTax.getValido())
		{
			return resultadoTax;
		}else{
			ValidaVdexVO resultado= new ValidaVdexVO();
			resultado.setValido(false);
			resultado.setTipoVdex("");
			HashSet<String> errores= new HashSet<String>();
			errores.addAll(Arrays.asList(resultadoTes.getErrores()));
			errores.addAll(Arrays.asList(resultadoTax.getErrores()));
			
			resultado.setErrores(errores.toArray(new String[0]));
			return resultado;
		}
	}

	/**
	 * Este método realiza la validación contra esquemas de una lista de ficheros
	 * indicando la ruta de los mismos. Los ficheros deben ser Taxonomías válidas
	 * para superar la validación.
	 * 
	 * @param paramlistaVdex Array de rutas donde se encuentra cada fichero a validar
	 * @return ValidaVdexVO. Array de VO que contienen el resultado de la validación
	 * errores posibles:
	 * 	0: el fichero no valida contra los esquemas correspondientes.
	 * 	1: el fichero valida correctamente, pero no posee el elemento VocabName
	 * 	2: el fichero valida correctamente, pero no posee el elemento VocabIdentifier
	 */
	protected ValidaVdexVO[] handleObtenerValidacionTaxonomias(
			String[] paramlistaVdex)
	throws Exception 
	{
		ValidaVdexVO[] resultado= new ValidaVdexVO[paramlistaVdex.length];
		
		for (int i=0; i < paramlistaVdex.length; i++)
		{
			resultado[i]= obtenerParseoConEsquemas(paramlistaVdex[i], TAXONOMIA );
		}
		return resultado;
	}

	
	/**
	 * Este método realiza la validación contra esquemas de una lista de ficheros
	 * indicando la ruta de los mismos. Los ficheros deben ser Tesauros válidos
	 * para superar la validación.
	 * 
	 * @param paramlistaVdex Array de rutas donde se encuentra cada fichero a validar
	 * @return ValidaVdexVO. Array de VO que contienen el resultado de la validación
	 * errores posibles:
	 * 	0: el fichero no valida contra los esquemas correspondientes.
	 * 	1: el fichero valida correctamente, pero no posee el elemento VocabName
	 * 	2: el fichero valida correctamente, pero no posee el elemento VocabIdentifier
	 */

	protected ValidaVdexVO[] handleObtenerValidacionTesauros(
			String[] paramlistaVdex)
	throws Exception 
	{
		ValidaVdexVO[] resultado= new ValidaVdexVO[paramlistaVdex.length];
		
		for (int i=0; i < paramlistaVdex.length; i++)
		{
			resultado[i]= obtenerParseoConEsquemas(paramlistaVdex[i], TESAURO );
		}
		return resultado;
	}


}