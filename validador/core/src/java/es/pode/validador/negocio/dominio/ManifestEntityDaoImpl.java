/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.validador.negocio.dominio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.dozer.util.mapping.MapperIF;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.jdom.Namespace;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.validador.TipoOde;
import es.pode.validador.negocio.servicio.ErrorParseoVO;
import es.pode.validador.negocio.soporte.XMLErrorHandler;
import es.pode.validador.negocio.soporte.domTree.DOMTreeUtility;

public class ManifestEntityDaoImpl implements ManifestEntityDao, ErrorHandler {
	
	private static final String SPACE_STRING = " ";
	private static final String EMPTY_STRING = "";
	public final static String DEF_MAPPING_MANIFESTENTITY_VALIDAVO = "DEF_MAPPING_MANIFESTENTITY_VALIDAVO";
	public final static String DEF_MAPPING_VALIDAVO_MANIFESTENTITY = "DEF_MAPPING_MANIFESTENTITY_VALIDAVOPRIME";

	/**
	 * Reference to the dozer bean mapper used in the application (singleton).
	 */
	private MapperIF beanMapper;
	private Logger logger = Logger.getLogger(this.getClass());
	 /*
     * Declaramos variables privadas de la clase
     * */
     private DOMParser mParser;
	 private List mFileList;
	 private String mBaseDirectory;
	 private Document mDocument;
	 private List mResourceIdentifierList;
	 private boolean haCambiadoDocument; 
	 private int indiceMetadato;
	 private String ficheroActual;
	 private boolean lomesBien;
	 
	 // Lista de errores de mensaje. La lista debe contener objetos de la clase ErrorParseoVO
	 private List erroresParseo=new ArrayList() ;
	 
	 private List erroresXerces = new ArrayList();
	 
	 private List lomes = new ArrayList();
	 private List nombreLomes= new ArrayList();//se guarda la localizacion de los lomes incluidos en el array "lomes" cuando esto lomes estan 
	 										   //referenciados mediante location, en otro caso se mete un string vacio	
	 
	 private List metadatos = new ArrayList();//se almacenan los metadatos que no son LOM-ESv1.0 para guardarlos en fichero fuera del imsmanifest.xml
	 private List nombreMetadatos = new ArrayList();
	 
	 /*
	  * Creamos un Constructor para inicializar las Variables
	  * */
	 
	 public ManifestEntityDaoImpl()
	 {
		 mFileList =  new ArrayList();
		 mBaseDirectory = EMPTY_STRING;
		 mDocument = null;
		 mParser = null;
		 mResourceIdentifierList = new ArrayList();
		 haCambiadoDocument= false;
		 lomesBien = true;

	 }
	
	
	/**
	 * Gets the reference to the dozer bean mapper used in the application (singleton).
	 * @return MapperIF
	 */
    protected MapperIF getBeanMapper() {
		return beanMapper;
	}

    /**
     * Sets the instance of the Dozer bean mapper to be used.
     * @param beanMapper
     */
	public void setBeanMapper(MapperIF beanMapper) {
		this.beanMapper = beanMapper;
	}
	
	 /*
	  * Metodos privados
	  * */
	 private String getBaseDirectory()
	  {
	      return mBaseDirectory;
	  }

	  public Document getDocument()
	  {
	      return mDocument;
	  }
	  
	  public void setDocument(Document doc){
		  this.mDocument= doc;
	  }
	  
	  public void setBaseDirectory(String dirBase)
	  {
		  this.mBaseDirectory=dirBase;
	  }
	  
	  private boolean getHaCambiadoDocument() {
		  return haCambiadoDocument;
	  }
	  private void setHaCambiadoDocument(boolean docC) {
		  this.haCambiadoDocument=docC;
	  }
	  
	  //get y set para el lomesBien 
	  public boolean getLomesBien() {
		  return lomesBien;
	  }
	  public void setLomesBien(boolean error) {
		  this.lomesBien=error;
	  }
	  
	  public List getErroresParseo() {
		  return erroresParseo;
	  }
//	  private void setErroresParseo(List errores) {
//		  this.erroresParseo=errores;
//	  }
	  
	  public List getErroresXerces() {
		  return erroresXerces;
	  }
	  public void setErroresXerces(List errores) {
		  this.erroresXerces=errores;
	  }
	  
	  
	  private String buscaFichero( String nomFich, String tipoFich ) throws Exception
	  {
//		boolean fichOk = false;
		String nomFichSalida = EMPTY_STRING; 
		try 
		{
			
			if ( nomFich.length() > 6 && (nomFich.substring(0,5).equals("http:") || 
				nomFich.substring(0,6).equals("https:")) ) 
			{
				nomFichSalida = nomFich;
			}
			else
			{
				File fichEntrada = new File( nomFich );
				if ( fichEntrada.isFile() == true )
				{
//					fichOk = true; //No se leía en ningún lado, bug?
					nomFichSalida = fichEntrada.getAbsolutePath();
				}
				else
				{
					String fichero = nomFich.replaceAll(getBaseDirectory()+"/",EMPTY_STRING);
					insertaError("8.0",fichero);
					//logger.warn("El Fichero no es un fichero Normal");
					logger.warn("Fichero no encontrado <" + fichero+">");
					
				}
			}
		}
		catch ( NullPointerException npe )
		{
			insertaError("8.1",npe.getMessage());
			logger.error("Fichero vacio - ", npe);
			throw npe;
	
		}
		catch ( SecurityException se )
		{
			insertaError("8.2",se.getMessage());
			logger.error("No es posible tener acceso al fichero - ", se);
			throw se;
	
		}
	
		return nomFichSalida;
	}
	  
   
	  private Vector vectorIdentificadores(Node nRecNodos)
	   {
	      Vector vRecNodos = new Vector();
	      Vector valores = new Vector();
	      if( nRecNodos != null )
	      {
	         vRecNodos = DOMTreeUtility.getNodes(nRecNodos, "resource");
	         int vRecNodosSize = vRecNodos.size();

	         for( int i = 0; i < vRecNodosSize; i++ )
	         {
	            Node nodoHijo = (Node)vRecNodos.elementAt(i);
	            String valor = DOMTreeUtility.getAttributeValue(nodoHijo, "href");
//	            String idRecurso = DOMTreeUtility.getAttributeValue(nodoHijo, "identifier");//No se leía
	            
	            if (valor != null && !valor.equals(EMPTY_STRING))
	            {	
	            	valores.add(valor);
	            	//buscamos sus hijos
	            	Vector vHijos = new Vector();
	            	vHijos = DOMTreeUtility.getNodes(nodoHijo, "file");
	            	int numHijos = vHijos.size();
	            	for (int l=0; l<numHijos; l++)
	            	{
	            		Node subHijos = (Node) vHijos.elementAt(l);
	            		String valorHijos = DOMTreeUtility.getAttributeValue(subHijos,"href");
	            		if (valorHijos!=null && !valorHijos.equals(EMPTY_STRING) && !valores.contains(valorHijos))
	            		{
	            			valores.add(valorHijos);
	            		}
	            	}
	            }
	         }
	      }// end if
	      return valores;
	   }
	
	public Boolean buscarManifest(java.lang.String rutaManifest)
    {
        //@todo implement public java.lang.Boolean buscarManifest(java.lang.String rutaManifest)
		Boolean manifestFound = true;
		String fichImsManifest = rutaManifest + File.separator + "imsmanifest.xml";
		File manifestFile = new File(fichImsManifest);
	     if( !manifestFile.exists() )
	      {
	       manifestFound = false;
	      }
		return manifestFound;
    }

    /**
     * @throws Exception 
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#obtenerParseoSimple(java.lang.String)
     * llamada CA: contentAggregation--> si es obligatorio que tenga al menos una organizacion
     * llamada RCP: ResourceContentPackage --> la etiqueta organizations tiene que ir vacia
     */
    public Boolean obtenerParseoSimple(java.lang.String rutaManifest, String tipoOde) throws Exception
    {
    	boolean validadorRes = false;
    	Document docValido = null;
//    	String mensaje=null;
		mParser = new DOMParser();
//		Document mDocAux;
		if (mParser != null)
		 {
			
		    try
		      {
			      mParser.setFeature("http://xml.org/sax/features/validation", false);
			      mParser.setFeature("http://xml.org/sax/features/namespaces", true);
			      mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
				
			      mParser.setErrorHandler(this);
			      
			} catch (SAXException se ) {
//				 insertaError("8.3",se.getMessage());
			      logger.error("ERROR EN EL XML - ",se);
				  validadorRes=false;
		    }
		   String nomManifestFich = rutaManifest; //+ File.separator + "imsmanifest.xml"; 
		   String nomFich = buscaFichero( nomManifestFich, "xml" ); 
			
//		   File tempXMLFich = new File(nomFich);
//		   String xsdLoc = "file:///" + tempXMLFich.getParent() + "/";  
//		   xsdLoc = xsdLoc.replaceAll( SPACE_STRING, "%20");  
//		   xsdLoc = xsdLoc.replace( '\\', '/');
				
		   if ( !nomFich.equals(EMPTY_STRING) ) {
				try
				{	
					parse(mParser,nomFich );
					docValido = mParser.getDocument();
					if ( docValido != null ) {
						validadorRes = true;
						mDocument= mParser.getDocument();
						
						this.setDocument(mParser.getDocument());
//						mDocAux= mDocument; //antes de ser cambiado //No se usa?	
						//se cambian por una etiqueta location los metadatos que no sean LOM-ESv1.0
						indiceMetadato=0;
						
						String ficheroProperties = "/validador.properties";
						InputStream is= this.getClass().getResourceAsStream(ficheroProperties);
						Properties fproperties = new Properties();
						fproperties.load(is);
						is.close();
//						String ns_scorm2004_prop= fproperties.getProperty("url_imscp_v1p1");
//						String eq_scorm2004_prop= fproperties.getProperty("ADL_SCORM");
//						String eq_imsContent_prop= fproperties.getProperty("IMS_Content");
//						String ns_scorm12_prop= fproperties.getProperty("url_imscp_rootv1p1p2"); 
						
//						if ((ns_scorm2004_prop.equals(namespace)) && (eq_scorm2004_prop.equals(esquema))){
//							validadorRes =esNodoCorrecto(mDocument, nomManifestFich, tipoOde);
//						}
//						else{
//							if (ns_scorm12_prop.equals(namespace)){
//								NamespaceYEsquema nye= new NamespaceYEsquema();
//								//Comentado porque parece que no es necesario. Lo pilla el propio esquema
////								boolean hayScormtype= compruebaScormtype (nomManifestFich, nye);
////								if (hayScormtype == false){
////									insertaError("8.47", "");
////								    logger.error("ERROR en Atributo scormtype");
////								    validadorRes=false;
////								}
//							}
//							else{
//								if ((ns_scorm2004_prop.equals(namespace))){
//									//Comentado porque parece que no es necesario. Lo pilla el propio esquema
////									validadorRes= compruebaNameSpacesIMSCP (nomManifestFich, fproperties);
//								}
//							}
//						}	
						TipoOde tipo= new TipoOde();
						tipo.obtenerTipoOde(rutaManifest);
						String estandar= tipo.getTipo();
						if (estandar != null){
							if (estandar.equals(ConstantesAgrega.SCORM_04)){
								validadorRes =esNodoCorrecto(mDocument, nomManifestFich, tipoOde);
							}
							else{
								if (estandar.equals(ConstantesAgrega.IMS_CP)){
//								NamespaceYEsquema nye= new NamespaceYEsquema();
//								boolean hayScormtype= compruebaScormtype (nomManifestFich, nye);
								validadorRes= compruebaNameSpacesIMSCP (nomManifestFich, fproperties);
									if (validadorRes == false){
										insertaError("8.49", EMPTY_STRING);
									    logger.warn("ERROR en Atributo scormtype");
									    validadorRes=false;
									}
								}
							}
							
//							si es correcto... con ese metodo nos hemos quitado los lom-es que no nos interesan
							if (getHaCambiadoDocument()) {
								//if (validadorRes) {
									FileOutputStream fs = new FileOutputStream(new File(nomFich)); //machacamos imsmanifest
									DOMBuilder dom = new DOMBuilder("org.jdom.adapters.XercesDOMAdapter");
									org.jdom.Document finalDoc = dom.build(
											mDocument );
									
									new XMLOutputter().output(finalDoc,fs);
									
									escribirMetadatos();//se escribe en ficheros los metadatos que no sean LOM-ESv1.0
									fs.close();
								//}
							}
						}	
					}
				}
				catch ( SAXException se ) {
//					insertaError("8.3",se.getMessage());
				    logger.error("ERROR EN EL XML - ",se);
					
				    validadorRes=false;
				}
				catch ( IOException ioe ) {
					insertaError("8.7",ioe.getMessage());
				    logger.error("ERROR DE ENTRADA/SALIDA - ",ioe);
				    validadorRes=false;
				}
			}
		 }

		 return validadorRes;
    }
    

    /**
     * @throws Exception 
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#obtenerParseoEsquemasSinLomes(java.lang.String)
     * llamada CA: contentAggregation--> si es obligatorio que tenga al menos una organizacion
     * llamada RCP: ResourceContentPackage --> la etiqueta organizations tiene que ir vacia
     * Valida contra los esquemas, en este parseo el manifest viene sin metadatos
     */
    public java.lang.Boolean obtenerParseoConEsquemasSinLomes(String manifestAux, String tipoOde) throws Exception
    {
    	boolean validadorRes = false;
    	String ficheroProperties = "/validador.properties";
		InputStream is= null;
    	mParser = new DOMParser();
    	    	
		if (mParser != null)
		 {
		    try
		      {
		    	if( logger.isDebugEnabled() )
		    		logger.debug( "URL del parseador: " + mParser.getClass().getResource( mParser.getClass().getName().replace('.','/') + ".class") );
		    	
		    	is= this.getClass().getResourceAsStream(ficheroProperties);
		    	
				Properties fproperties = new Properties();
				fproperties.load(is);
				
			    mParser.setFeature("http://xml.org/sax/features/validation", true);
			    mParser.setFeature("http://xml.org/sax/features/namespaces", true);
//			    estas "feature" son para la validación del manifest contra los esquemas
			    mParser.setFeature("http://apache.org/xml/features/validation/schema",true);
			    mParser.setFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", true);
			    
			    mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
			    mParser.setErrorHandler(this);
			    
			    String namespacesFull= null;
			    
			    TipoOde tipo= new TipoOde();
			    tipo.obtenerTipoOde(manifestAux);
				String estandar= tipo.getTipo();
				if (estandar != null){
					if (estandar.equals(ConstantesAgrega.SCORM_12)){
			    	
				    	String imscp_rootv1p1p2 = fproperties.getProperty("imscp_rootv1p1p2");
						String lomCustom = fproperties.getProperty("lomCustom");
						String adlcp_rootv1p2 = fproperties.getProperty("adlcp_rootv1p2");
						String imsmd_rootv1p2p1 = fproperties.getProperty("imsmd_rootv1p2p1");
//						String ims_xml = fproperties.getProperty("ims_xml");
						
						String url_imscp_rootv1p1p2 = fproperties.getProperty("url_imscp_rootv1p1p2");
						String url_lomCustom = fproperties.getProperty("url_lomCustom");
						String url_adlcp_rootv1p2 = fproperties.getProperty("url_adlcp_rootv1p2");
						String url_imsmd_rootv1p2p1 = fproperties.getProperty("url_imsmd_rootv1p2p1");
//						String url_ims_xml = fproperties.getProperty("url_ims_xml");
				
						File fileImscp_rootv1p1p2=new File(imscp_rootv1p1p2);
						String sImscp_rootv1p1p2=fileImscp_rootv1p1p2.getCanonicalPath();
						File fileLomCustom=new File(lomCustom);
						String sLomCustom=fileLomCustom.getCanonicalPath();
						File fileAdlcp_rootv1p2=new File(adlcp_rootv1p2);
						String sAdlcp_rootv1p2=fileAdlcp_rootv1p2.getCanonicalPath();
						File fileImsmd_rootv1p2p1=new File(imsmd_rootv1p2p1);
						String sImsmd_rootv1p2p1=fileImsmd_rootv1p2p1.getCanonicalPath();
//						File fileIms_xml=new File(ims_xml);
//						String sIms_xml=fileIms_xml.getCanonicalPath();
						
//						namespacesFull = url_imscp_rootv1p1p2+" "+sImscp_rootv1p1p2+" "+ url_lomCustom+" "+sLomCustom+
//					      " "+ url_adlcp_rootv1p2 +" "+sAdlcp_rootv1p2 + " " + url_imsmd_rootv1p2p1 + " " + sImsmd_rootv1p2p1 + " " + url_ims_xml + " " +sIms_xml;
//					      logger.info("METODO: obtenerParseoConEsquemas!!!! NAMESPACESFULL -> " + namespacesFull.replace( '\\', '/'));
					    
						namespacesFull = url_imscp_rootv1p1p2+SPACE_STRING+sImscp_rootv1p1p2+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
					      SPACE_STRING+ url_adlcp_rootv1p2 +SPACE_STRING+sAdlcp_rootv1p2 + SPACE_STRING + url_imsmd_rootv1p2p1 + SPACE_STRING + sImsmd_rootv1p2p1;
						
//					    mParser.setProperty( "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", sIms_xml );

					}
					else{
				    	if (estandar.equals(ConstantesAgrega.SCORM_04)){
					    	logger.debug("obtenerParseoConEsquemas --> SCORM 2004");
					    	
					    	String imscp_v1p1 = fproperties.getProperty("imscp_v1p1");
							String lomCustom = fproperties.getProperty("lomCustom");
							String adlcp_v1p3 = fproperties.getProperty("adlcp_v1p3");
							String imsss_v1p0 = fproperties.getProperty("imsss_v1p0");
							String adlseq_v1p3 = fproperties.getProperty("adlseq_v1p3");
							String adlnav_v1p3 = fproperties.getProperty("adlnav_v1p3");
							
							String url_imscp_v1p1 = fproperties.getProperty("url_imscp_v1p1");
							String url_lomCustom = fproperties.getProperty("url_lomCustom");
							String url_adlcp_v1p3 = fproperties.getProperty("url_adlcp_v1p3");
							String url_imsss_v1p0 = fproperties.getProperty("url_imsss_v1p0");
							String url_adlseq_v1p3 = fproperties.getProperty("url_adlseq_v1p3");
							String url_adlnav_v1p3 = fproperties.getProperty("url_adlnav_v1p3");
							
							File fileImscp_v1p1=new File(imscp_v1p1);
							String sImscp_v1p1=fileImscp_v1p1.getCanonicalPath();
							File fileLomCustom=new File(lomCustom);
							String sLomCustom=fileLomCustom.getCanonicalPath();
							File fileAdlcp_v1p3=new File(adlcp_v1p3);
							String sAdlcp_v1p3=fileAdlcp_v1p3.getCanonicalPath();
							File fileImsss_v1p0=new File(imsss_v1p0);
							String sImsss_v1p0=fileImsss_v1p0.getCanonicalPath();
							File fileAdlseq_v1p3=new File(adlseq_v1p3);
							String sAdlseq_v1p3=fileAdlseq_v1p3.getCanonicalPath();
							File fileAdlnav_v1p3=new File(adlnav_v1p3);
							String sAdlnav_v1p3=fileAdlnav_v1p3.getCanonicalPath();
							
							namespacesFull = url_imscp_v1p1+SPACE_STRING+sImscp_v1p1+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
						      SPACE_STRING+url_adlcp_v1p3+SPACE_STRING+sAdlcp_v1p3+SPACE_STRING+url_imsss_v1p0+SPACE_STRING+sImsss_v1p0+
						      SPACE_STRING+url_adlseq_v1p3+SPACE_STRING+sAdlseq_v1p3+SPACE_STRING+ url_adlnav_v1p3 +SPACE_STRING+sAdlnav_v1p3;	    	
					    }
					    else{
					    	if (estandar.equals(ConstantesAgrega.IMS_CP)){
					    		
					    		logger.debug("obtenerParseoConEsquemas --> IMS CP");
					    		
					    		String imscp_v1p1 = fproperties.getProperty("imscp_v1p1");
								String lomCustom = fproperties.getProperty("lomCustom");
								String adlcp_v1p3 = fproperties.getProperty("imsmd_v1p2p4");
								
								String url_imscp_v1p1 = fproperties.getProperty("url_imscp_v1p1");
								String url_lomCustom = fproperties.getProperty("url_lomCustom");
								String url_adlcp_v1p3 = fproperties.getProperty("url_imsmd_v1p2p4");
								
								File fileImscp_v1p1=new File(imscp_v1p1);
								String sImscp_v1p1=fileImscp_v1p1.getCanonicalPath();
								File fileLomCustom=new File(lomCustom);
								String sLomCustom=fileLomCustom.getCanonicalPath();
								File fileAdlcp_v1p3=new File(adlcp_v1p3);
								String sAdlcp_v1p3=fileAdlcp_v1p3.getCanonicalPath();
								
								namespacesFull = url_imscp_v1p1+SPACE_STRING+sImscp_v1p1+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
							      SPACE_STRING+ url_adlcp_v1p3 +SPACE_STRING+sAdlcp_v1p3;
					    	}
					    }
				    }
			      
				    mParser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", namespacesFull); 	
			    }
			   }
			   catch (SAXException se )
			   {
			      logger.error("Error de Xerces - ",se);
			      validadorRes=false;
			   } finally {
				   if (is!=null) {
					is.close();
				}
			   }
			   Document docValido = null;
			   			   
				if ( mDocument!=null )
				{
					try
					{
//				 		Object eo= mParser.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
						//Parseamos el fichero

						//Obtenemos un InputSource a partir de mDocument que es un Document limpio de metadatos
						//solo se parsea el manisfest(SCORM)
						DOMBuilder dom = new DOMBuilder("org.jdom.adapters.XercesDOMAdapter");
						org.jdom.Document finalDoc = dom.build(
								mDocument );
		                
						Writer writer = new StringWriter();
						XMLOutputter outputter = new XMLOutputter();
		                outputter.output(finalDoc, writer);  
		                
		                String xml = writer.toString();            	
		        		InputSource isrc = new InputSource(new StringReader(xml));
		        		
		        		
						parse(mParser,isrc,manifestAux);
							
						docValido = mParser.getDocument();
						
						this.setDocument(mParser.getDocument());
						
						if ( docValido != null )
						{
							validadorRes = true;
						}
					}
					catch ( SAXException se )
					{
//						insertaError("8.3",se.getMessage());
					    logger.error("ERROR EN EL XML - ",se);
						validadorRes=false;
					}
				}
					
		 }
		 return validadorRes;
    }
   
    /**
     * @throws Exception 
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#obtenerParseoEsquemasSinLomes(java.lang.String)
     * llamada CA: contentAggregation--> si es obligatorio que tenga al menos una organizacion
     * llamada RCP: ResourceContentPackage --> la etiqueta organizations tiene que ir vacia
     * Valida contra los esquemas
     */
    
    public java.lang.Boolean obtenerParseoConEsquemas(String manifestAux, String tipoOde) throws Exception
    {
    	boolean validadorRes = false;
    	String ficheroProperties = "/validador.properties";
		InputStream is= null;
    	mParser = new DOMParser();
    	    	
		if (mParser != null)
		 {
		    try
		      {
		    	if(logger.isDebugEnabled())
		    		logger.debug( "URL del parseador: " + mParser.getClass().getResource( mParser.getClass().getName().replace('.','/') + ".class") );
		    	
		    	is= this.getClass().getResourceAsStream(ficheroProperties);
		    	
				Properties fproperties = new Properties();
				fproperties.load(is);
				
			    mParser.setFeature("http://xml.org/sax/features/validation", true);
			    mParser.setFeature("http://xml.org/sax/features/namespaces", true);
//			    estas "feature" son para la validación del manifest contra los esquemas
			    mParser.setFeature("http://apache.org/xml/features/validation/schema",true);
			    mParser.setFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", true);
			    
			    mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
			    mParser.setErrorHandler(this);
			    
			    String namespacesFull= null;
			    
			    TipoOde tipo= new TipoOde();
			    tipo.obtenerTipoOde(manifestAux);
				String estandar= tipo.getTipo();
				if (estandar != null){
					if (estandar.equals(ConstantesAgrega.SCORM_12)){
				    	logger.debug("obtenerParseoConEsquemas --> SCORM 1.2");
				    	
				    	String imscp_rootv1p1p2 = fproperties.getProperty("imscp_rootv1p1p2");
						String lomCustom = fproperties.getProperty("lomCustom");
						String adlcp_rootv1p2 = fproperties.getProperty("adlcp_rootv1p2");
						String imsmd_rootv1p2p1 = fproperties.getProperty("imsmd_rootv1p2p1");
	//					String ims_xml = fproperties.getProperty("ims_xml");
						
						String url_imscp_rootv1p1p2 = fproperties.getProperty("url_imscp_rootv1p1p2");
						String url_lomCustom = fproperties.getProperty("url_lomCustom");
						String url_adlcp_rootv1p2 = fproperties.getProperty("url_adlcp_rootv1p2");
						String url_imsmd_rootv1p2p1 = fproperties.getProperty("url_imsmd_rootv1p2p1");
	//					String url_ims_xml = fproperties.getProperty("url_ims_xml");
				
						File fileImscp_rootv1p1p2=new File(imscp_rootv1p1p2);
						String sImscp_rootv1p1p2=fileImscp_rootv1p1p2.getCanonicalPath();
						File fileLomCustom=new File(lomCustom);
						String sLomCustom=fileLomCustom.getCanonicalPath();
						File fileAdlcp_rootv1p2=new File(adlcp_rootv1p2);
						String sAdlcp_rootv1p2=fileAdlcp_rootv1p2.getCanonicalPath();
						File fileImsmd_rootv1p2p1=new File(imsmd_rootv1p2p1);
						String sImsmd_rootv1p2p1=fileImsmd_rootv1p2p1.getCanonicalPath();
	//					File fileIms_xml=new File(ims_xml);
	//					String sIms_xml=fileIms_xml.getCanonicalPath();
						
	//					namespacesFull = url_imscp_rootv1p1p2+" "+sImscp_rootv1p1p2+" "+ url_lomCustom+" "+sLomCustom+
	//				      " "+ url_adlcp_rootv1p2 +" "+sAdlcp_rootv1p2 + " " + url_imsmd_rootv1p2p1 + " " + sImsmd_rootv1p2p1 + " " + url_ims_xml + " " +sIms_xml;
	//				      logger.info("METODO: obtenerParseoConEsquemas!!!! NAMESPACESFULL -> " + namespacesFull.replace( '\\', '/'));
					    
						namespacesFull = url_imscp_rootv1p1p2+SPACE_STRING+sImscp_rootv1p1p2+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
					      SPACE_STRING+ url_adlcp_rootv1p2 +SPACE_STRING+sAdlcp_rootv1p2 + SPACE_STRING + url_imsmd_rootv1p2p1 + SPACE_STRING + sImsmd_rootv1p2p1;
						
	//				    mParser.setProperty( "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", sIms_xml );
						
				    }
				    else{
				    	if (estandar.equals(ConstantesAgrega.SCORM_04)){
					    	logger.debug("obtenerParseoConEsquemas --> SCORM 2004");
					    	
					    	String imscp_v1p1 = fproperties.getProperty("imscp_v1p1");
							String lomCustom = fproperties.getProperty("lomCustom");
							String adlcp_v1p3 = fproperties.getProperty("adlcp_v1p3");
							String imsss_v1p0 = fproperties.getProperty("imsss_v1p0");
							String adlseq_v1p3 = fproperties.getProperty("adlseq_v1p3");
							String adlnav_v1p3 = fproperties.getProperty("adlnav_v1p3");
							
							String url_imscp_v1p1 = fproperties.getProperty("url_imscp_v1p1");
							String url_lomCustom = fproperties.getProperty("url_lomCustom");
							String url_adlcp_v1p3 = fproperties.getProperty("url_adlcp_v1p3");
							String url_imsss_v1p0 = fproperties.getProperty("url_imsss_v1p0");
							String url_adlseq_v1p3 = fproperties.getProperty("url_adlseq_v1p3");
							String url_adlnav_v1p3 = fproperties.getProperty("url_adlnav_v1p3");
							
							File fileImscp_v1p1=new File(imscp_v1p1);
							String sImscp_v1p1=fileImscp_v1p1.getCanonicalPath();
							File fileLomCustom=new File(lomCustom);
							String sLomCustom=fileLomCustom.getCanonicalPath();
							File fileAdlcp_v1p3=new File(adlcp_v1p3);
							String sAdlcp_v1p3=fileAdlcp_v1p3.getCanonicalPath();
							File fileImsss_v1p0=new File(imsss_v1p0);
							String sImsss_v1p0=fileImsss_v1p0.getCanonicalPath();
							File fileAdlseq_v1p3=new File(adlseq_v1p3);
							String sAdlseq_v1p3=fileAdlseq_v1p3.getCanonicalPath();
							File fileAdlnav_v1p3=new File(adlnav_v1p3);
							String sAdlnav_v1p3=fileAdlnav_v1p3.getCanonicalPath();
							
							namespacesFull = url_imscp_v1p1+SPACE_STRING+sImscp_v1p1+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
						      SPACE_STRING+url_adlcp_v1p3+SPACE_STRING+sAdlcp_v1p3+SPACE_STRING+url_imsss_v1p0+SPACE_STRING+sImsss_v1p0+
						      SPACE_STRING+url_adlseq_v1p3+SPACE_STRING+sAdlseq_v1p3+SPACE_STRING+ url_adlnav_v1p3 +SPACE_STRING+sAdlnav_v1p3;
//						      logger.info("METODO: obtenerParseoConEsquemas!!!! NAMESPACESFULL -> " + namespacesFull.replace( '\\', '/'));	    	
					    }
					    else{
					    	if (estandar.equals(ConstantesAgrega.IMS_CP)){
					    		
					    		logger.debug("obtenerParseoConEsquemas --> IMS CP");
					    		
					    		String imscp_v1p1 = fproperties.getProperty("imscp_v1p1");
								String lomCustom = fproperties.getProperty("lomCustom");
								String adlcp_v1p3 = fproperties.getProperty("imsmd_v1p2p4");
								
								String url_imscp_v1p1 = fproperties.getProperty("url_imscp_v1p1");
								String url_lomCustom = fproperties.getProperty("url_lomCustom");
								String url_adlcp_v1p3 = fproperties.getProperty("url_imsmd_v1p2p4");
								
								File fileImscp_v1p1=new File(imscp_v1p1);
								String sImscp_v1p1=fileImscp_v1p1.getCanonicalPath();
								File fileLomCustom=new File(lomCustom);
								String sLomCustom=fileLomCustom.getCanonicalPath();
								File fileAdlcp_v1p3=new File(adlcp_v1p3);
								String sAdlcp_v1p3=fileAdlcp_v1p3.getCanonicalPath();
								
								namespacesFull = url_imscp_v1p1+SPACE_STRING+sImscp_v1p1+SPACE_STRING+ url_lomCustom+SPACE_STRING+sLomCustom+
							      SPACE_STRING+ url_adlcp_v1p3 +SPACE_STRING+sAdlcp_v1p3;
//							      logger.info("METODO: obtenerParseoConEsquemas!!!! NAMESPACESFULL -> " + namespacesFull.replace( '\\', '/'));
					    	}
					    }
				    }
			      
				    mParser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", namespacesFull);
				    
				}
			  			     
			   }
			   catch (SAXException se )
			   {
			      logger.error("Error de Xerces - ",se);
			      validadorRes=false;
			   } finally {
				   if (is!=null) {
					is.close();
				}
			   }
			   Document docValido = null;
			   
				if ( !manifestAux.equals(EMPTY_STRING) )
				{
					try
					{
				 		Object eo= mParser.getProperty("http://apache.org/xml/properties/schema/external-schemaLocation");
				 		if(logger.isDebugEnabled())
				 			logger.debug("Recupero los namespaces del mParser <" + eo.toString()+">");
						//Parseamos el fichero

						parse(mParser,manifestAux);
							
						docValido = mParser.getDocument();
						
						this.setDocument(mParser.getDocument());
						
						if ( docValido != null )
						{
							validadorRes = true;

						}
					}
					catch ( SAXException se )
					{
					    logger.error("ERROR EN EL XML - ",se);
						validadorRes=false;
					}
				}
					
		 }
		 return validadorRes;
    }

    /**
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#crearListaFicherosManifest(java.lang.String)
     */
    public void crearListaFicherosManifest(java.lang.String rutaManifest)
    {
    	 File fichOCarpeta = new File(rutaManifest);
    	 File[] fichsEnDir;

	      if( fichOCarpeta.isDirectory() && !fichOCarpeta.getName().equals("common")
	         && !fichOCarpeta.getName().equals("vocab") && !fichOCarpeta.getName().equals("extend")
	         && !fichOCarpeta.getName().equals("unique") )
	      {
	         fichsEnDir = fichOCarpeta.listFiles();
	         int contFich = 0;
	         while( contFich < fichsEnDir.length )
	         {
	        	 crearListaFicherosManifest(fichsEnDir[contFich].getPath());
	            contFich++;
	          }// end while

	       }// end if

	      if( fichOCarpeta.isFile() && !fichOCarpeta.getName().endsWith(".xsd")
	         && !fichOCarpeta.getName().equals("imsmanifest.xml") && !fichOCarpeta.getName().endsWith(".dtd") )
	      {
	         String tempStr = fichOCarpeta.getPath();
	         tempStr = tempStr.replace('\\', '/');
	         if (mFileList != null){
		         if( !mFileList.contains(tempStr) )
		         {
		            mFileList.add(tempStr);
		         }
	         }
	      }
    }

    /**
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#chequearContenido(org.w3c.dom.Document, java.lang.String)
     */
    public java.lang.Boolean chequearContenido(org.w3c.dom.Node arbolDoc, java.lang.String rutaManifest)
    {
        //@todo implement public java.lang.Boolean chequearContenido(org.w3c.dom.Document arbolDoc, java.lang.String rutaManifest)
    	Boolean result = true;
	     Node iTestSubjectNode = arbolDoc;
	     if( iTestSubjectNode == null )
	      {
	         result = false;
	         return result;
	      }

	      switch( iTestSubjectNode.getNodeType() )
	      {
	         case Node.PROCESSING_INSTRUCTION_NODE:
	         {
	            // Skip any processing instructions, nothing for us to do
	            break;
	         }
	         case Node.DOCUMENT_NODE:
	         {
	            Node rootNode = ( (Document)iTestSubjectNode ).getDocumentElement();
//	            String rootNodeName = rootNode.getLocalName();
	            result = (chequearContenido(rootNode, EMPTY_STRING)) && result;
	            break;
	         }
	         case Node.ELEMENT_NODE:
	         {
	            String parentNodeName = iTestSubjectNode.getLocalName();
	            if( parentNodeName.equals("manifest") )
	            {
	               Node resourcesNode = DOMTreeUtility.getNode(iTestSubjectNode, "resources");
	               if( resourcesNode != null )
	               {
	                  Vector files = new Vector();
	                  files = vectorIdentificadores(resourcesNode);
	                  if (files != null)
	                  {
	                	  int numElems = files.size();
	                	  for (int ll=0; ll< numElems; ll++)
	                	  {
	                		  String ruta = files.elementAt(ll).toString();
	                		  String rutaFinal= null;
	                		  
	                		  if( ( (ruta.length() > 4) && (ruta.substring(0, 5).equals("http:")) ) || 
	                		             ( (ruta.length() > 5) && (ruta.substring(0, 6).equals("https:")) ) ||
	                		             ( (ruta.length() > 3) && (ruta.substring(0, 4).equals("ftp:")) ) || 
	                		             ( (ruta.length() > 4) && (ruta.substring(0, 5).equals("ftps:")) ) ){
	                			  
	                			  rutaFinal= ruta;
	                		  }
	                		  else{
	                			  rutaFinal= rutaManifest + ruta;
	                		  }
	                			  
	                	      result= chequearHref(rutaFinal) && result;
	                		 
		   
	                	  }
	                  }
	               }
	               //Chequeamos los contenidos de los submanifiestos
	               Vector vSubmanifest = DOMTreeUtility.getNodes(iTestSubjectNode, "manifest");
	               for(int i=0; i< vSubmanifest.size();i++){
	            	   Node submanifest = (Node) vSubmanifest.get(i);
	            	   String submXmlbase = DOMTreeUtility.getAttributeValue(submanifest, "base");

	            	   if (!submXmlbase.endsWith("/"))
	            		   submXmlbase = submXmlbase + '/';
	            	   if(submXmlbase.equals("./"))
	            		   submXmlbase = EMPTY_STRING; 
	            	   
	            	   submXmlbase= rutaManifest +submXmlbase; //se concatenan al base obtenido, el base de su padre
	            	   result = (chequearContenido(submanifest, submXmlbase)) && result;
	               }
	               
	               
	            }
	            break;
	         }
	         // handle entity reference nodes
	         case Node.ENTITY_REFERENCE_NODE:
	         {
	            break;
	         }
	         // text
	         case Node.COMMENT_NODE:
	         {
	            break;
	         }
	         case Node.CDATA_SECTION_NODE:
	         {
	            break;
	         }
	         case Node.TEXT_NODE:
	         {
	            break;
	         }
	         default:
	         {
	            break;
	         }
	      }// end switch statement
	      return result;
    }

   
    private void trackResourceIdentifiers(Node iResourcesNode)
    {
       Vector resourceNodes = new Vector();
       if( iResourcesNode != null )
       {
          // this will return a Vector of all child nodes of iResourceNode whos
          //  name = "resource"
          resourceNodes = DOMTreeUtility.getNodes(iResourcesNode, "resource");
          int resourceNodesSize = resourceNodes.size();

          for( int i = 0; i < resourceNodesSize; i++ )
          {
             Node currentChild = (Node)resourceNodes.get(i);
             String resourceId = DOMTreeUtility.getAttributeValue(currentChild, "identifier");
             if ((resourceId != null) && (!resourceId.equals(EMPTY_STRING)) && (!mResourceIdentifierList.contains(resourceId))) {//para evitar repetidos
            	 mResourceIdentifierList.add(resourceId);
             }
             //mManifestResourceIdentifierList.add(resourceId);
          }
       }// end if
    }
    
    private void trackSubmIdentifiers(Vector vSubmNode) {
    	
    	if (vSubmNode != null) {
    		for (int v=0; v<vSubmNode.size(); v++) {
    			Node submNode = (Node)vSubmNode.get(v);
		    	//todo submanifest tiene un xml:base 
		    	String submXmlbase = DOMTreeUtility.getAttributeValue(submNode, "base");
		    	if ((submXmlbase!=null) && (!submXmlbase.equals(EMPTY_STRING))) {
		    		String submIdentifier = DOMTreeUtility.getAttributeValue(submNode, "identifier");
		            if ((submIdentifier != null) && (!submIdentifier.equals(EMPTY_STRING)) && (!mResourceIdentifierList.contains(submIdentifier))) {
		               	 mResourceIdentifierList.add(submIdentifier);
		            }
		        }
		    	Node resourcesNode = DOMTreeUtility.getNode(submNode, "resources");
		    	//para corregir el error de si un item hace referencia a un submanifiesto q esta mas abajo y no de error pq todavia no tiene los recursos
		    	trackResourceIdentifiers(resourcesNode); //llamada con ese nodo
	            
		    	//submanifiestos
	            Vector vSubmNodes = DOMTreeUtility.getNodes(submNode, "manifest");
	            if (vSubmNodes != null && vSubmNodes.size() >0) {
	            	trackSubmIdentifiers(vSubmNodes);//guardamos los identificadores de los submanifiestos mResourceIdentifierList
	            }
          }//end for
    	}
   
    }
    
    private int getMultiplicityUsed(NamedNodeMap iAttributeMap, String iNodeName)
    {
       int result = 0;
       int length = iAttributeMap.getLength();
       String currentName;

       for( int i = 0; i < length; i++ )
       {
          currentName = ( (Attr)iAttributeMap.item(i) ).getLocalName();
          if( currentName.equals(iNodeName) )
          {
             result++;
          } // end if current name equals node name
       } // end looping over attributes
       return result;
    }
    
    private int getMultiplicityUsed(Node iParentNode, String iNodeName)
    {
       NodeList kids = iParentNode.getChildNodes();
       int count = 0;

       int kidsLength = kids.getLength();
       for( int i = 0; i < kidsLength; i++ )
       {
          if( kids.item(i).getNodeType() == Node.ELEMENT_NODE )
          {
             String currentNodeName = kids.item(i).getLocalName();
             if( currentNodeName!= null && currentNodeName.equals(iNodeName) )
             {
                count++;
             } // end if the current node name equals the name we are looking for
          } // end of the node type is ELEMENT_NODE
       } // end looping over children
       return count;
    }
    
    private boolean checkResourceAttributes(Node iResourceNode, NamedNodeMap iAttrList)
    {
       int idMultiplicityUsed = -1;
       int typeMultiplicityUsed = -1;
       int scormMultiplicityUsed = -1;

       boolean result = true;

       // check for mandatory attributes

       idMultiplicityUsed = getMultiplicityUsed(iAttrList, "identifier");
       if( idMultiplicityUsed < 1 )
       {
    	   insertaError("8.45",EMPTY_STRING);
    	   result = false;
       }

       typeMultiplicityUsed = getMultiplicityUsed(iAttrList, "type");

       if( typeMultiplicityUsed < 1 )
       {
    	   insertaError("8.44",EMPTY_STRING);
    	   result = false;
       }

       scormMultiplicityUsed = getMultiplicityUsed(iAttrList, "scormType");
       if( scormMultiplicityUsed < 1 )
       {
    	   insertaError("8.43",EMPTY_STRING);
           result = false;
       }

       return result;
    }
    
    private boolean esNodoCorrecto (org.w3c.dom.Node iTestSubjectNode, java.lang.String rutaManifest, String tipoOde) throws IOException
    {
        //@todo implement public java.lang.Boolean chequearContenido(org.w3c.dom.Document arbolDoc, java.lang.String rutaManifest)
    	boolean result = true;
		
		if( iTestSubjectNode == null )
		  {
		     result =false;
		     insertaError("8.13",EMPTY_STRING);
		     return result;
		  }

		  switch( iTestSubjectNode.getNodeType() )
		  {
		     case Node.PROCESSING_INSTRUCTION_NODE:
		     {
		        // Skip any processing instructions, nothing for us to do
		        break;
		     }
		     case Node.DOCUMENT_NODE:
		     {
		        Node rootNode = ( (Document)iTestSubjectNode ).getDocumentElement();
		        
		        String parentNodeName = rootNode.getLocalName();
		    	 
		    	 if( parentNodeName.equals("manifest") )
		         {
		            Node resourcesNode = DOMTreeUtility.getNode(rootNode, "resources");
		            if( resourcesNode != null )
		            {
		               trackResourceIdentifiers(resourcesNode);//guardamos los identificadores de los recursos mResourceIdentifierList
		            }
		            //submanifiestos
		            Vector vSubmNodes = DOMTreeUtility.getNodes(rootNode, "manifest");
		            if (vSubmNodes != null) {
		            	trackSubmIdentifiers(vSubmNodes);//guardamos los identificadores de los submanifiestos mResourceIdentifierList
		            }
		         }
		    	 
		        result = esNodoCorrecto(rootNode, EMPTY_STRING, tipoOde);
		        break;
		     }
		     case Node.ELEMENT_NODE:
		     {
		    	 String parentNodeName = iTestSubjectNode.getLocalName();
		    	 Vector mResourceNodes = new Vector();
		    	 /**********location************/
		    	 if(parentNodeName ==null){
		    		 parentNodeName= iTestSubjectNode.getNodeName();//location
		    	 }
		    	 /**********location************/
		    	 if( parentNodeName.equals("manifest") )
		         {
		            Node resourcesNode = DOMTreeUtility.getNode(iTestSubjectNode, "resources");
		            if( resourcesNode != null )
		            {
		               mResourceNodes = DOMTreeUtility.getNodes(resourcesNode, "resource");
//		               trackResourceIdentifiers(resourcesNode);//guardamos los identificadores de los recursos mResourceIdentifierList
		            }
//		            submanifiestos
//		            Vector vSubmNodes = DOMTreeUtility.getNodes(iTestSubjectNode, "manifest");
//		            if (vSubmNodes != null) {
//		            	trackSubmIdentifiers(vSubmNodes);//guardamos los identificadores de los submanifiestos mResourceIdentifierList
//		            }
		         } 
		    	
		        //String parentNodeName = iTestSubjectNode.getLocalName();
		        NamedNodeMap attrList = iTestSubjectNode.getAttributes();
		        int numAttr = attrList.getLength();
		        int multiplicityUsed = -1;
		        Attr currentAttrNode = null;
		        String currentNodeName = EMPTY_STRING;
		        
		        // Loop throught attributes
		        for( int i = 0; i < numAttr; i++ )
		        {
		           currentAttrNode = (Attr)attrList.item(i);
		           currentNodeName = currentAttrNode.getLocalName();
		           if( currentNodeName.equalsIgnoreCase("persistState") )
		           {
		        	   insertaError("8.14",iTestSubjectNode.getLocalName());
		               result = false;
		           }
		           if( currentNodeName.equalsIgnoreCase("scormType") )
		           {
		              //result = checkSCORMTypeReq(currentAttrNode, iTestSubjectNode) && result;
		        	   boolean res1 = false;
		        	   if( DOMTreeUtility.isAppropriateElement(iTestSubjectNode, "resource", "http://www.imsglobal.org/xsd/imscp_v1p1") )
		        	      {
		        	         res1 = true;
		        	      } else {
		        	    	  res1 = false;
		        	    	  insertaError("8.15",iTestSubjectNode.getLocalName());
		        	      }
		        	    
		        	   	result = res1 && result;
		           }
		           if( currentNodeName.equalsIgnoreCase("objectivesGlobalToSystem") )
		           {
		              //result = checkObjGlobalToSystemReq(currentAttrNode, iTestSubjectNode) && result;
		        	   boolean res1 = false;
		        	   if( DOMTreeUtility.isAppropriateElement(iTestSubjectNode, "organization", "http://www.imsglobal.org/xsd/imscp_v1p1") )
		        	      {
		        	         res1 = true;
		        	      } else {
		        	    	  res1 = false;
		        	    	  insertaError("8.16",iTestSubjectNode.getLocalName());
		        	      }
		        	   
		        	   	result = res1 && result;
		           }

		        } // end for

		       if( parentNodeName.equals("manifest") )
		        {
		    	   multiplicityUsed = getMultiplicityUsed(attrList, "identifier");

		           if( multiplicityUsed < 1 )
		           {
		        	   insertaError("8.17",EMPTY_STRING);
		               result = false;
		           }
		       }
		       else if( parentNodeName.equalsIgnoreCase("organizations"))
		       {
		             multiplicityUsed = getMultiplicityUsed(attrList, "default");
		             if(( multiplicityUsed < 1 ) && (tipoOde.equals("CA")))
		               {
		            	 insertaError("8.18",EMPTY_STRING);
		                  result = false;
		               } else if ((multiplicityUsed > 0) && (tipoOde.equals("RCP"))){//si es RCP no puede aparecer
		            	   insertaError("8.19",EMPTY_STRING);
		            	   result = false; 
		               }
		       }
		       else if( parentNodeName.equalsIgnoreCase("organization"))
		       {
		               multiplicityUsed = getMultiplicityUsed(attrList, "identifier");
		               if (( multiplicityUsed < 1 ) && (tipoOde.equals("CA")))
		               {
		            	   insertaError("8.20",EMPTY_STRING);
			      		   result = false;
		               }else if ((multiplicityUsed > 0) && (tipoOde.equals("RCP"))){//si es RCP no puede aparecer
		            	   insertaError("8.21",EMPTY_STRING);
			      		   result = false; 
		               }
		       }
		       
		       
		       else if( parentNodeName.equalsIgnoreCase("item"))
		       {
		    	   
		    	    metadatosCorrectos(result, iTestSubjectNode);	
		    	   		
		               multiplicityUsed = getMultiplicityUsed(attrList, "identifier");
		               int multTitle = getMultiplicityUsed(iTestSubjectNode, "title");
		               if( multiplicityUsed < 1 )
		               { 
		            	   insertaError("8.22",EMPTY_STRING);
		            	   result = false;
		                  }
		               if( multTitle < 1 )
		               {
		            	   insertaError("8.23",EMPTY_STRING);
			      		   result = false;
		               }
		               int idrefMult = -1;
//		               int paramMult = -1;

		               idrefMult = getMultiplicityUsed(attrList, "identifierref");
//		               paramMult = getMultiplicityUsed(attrList, "parameters");
		               if( idrefMult >= 1 )
		               {
		                  String iDREFValue = DOMTreeUtility.getAttributeValue(iTestSubjectNode, "identifierref");
		                  boolean validIdref = mResourceIdentifierList.contains(iDREFValue);
		                  if( validIdref )
		                  {
		                    // mValidIdrefs.add(iDREFValue);
		                	  result = true && result;
		                  } else { //Si no esta en la lista no es valido!!
		                	  result = false;
		                	  insertaError("8.24",iDREFValue);
		                  }

		               }

		              NodeList childrenOfItem = iTestSubjectNode.getChildNodes();
		               if( childrenOfItem != null )
		               {
		                  Node currentItemChild;
		                  String currentItemChildName;
		                  int len = childrenOfItem.getLength();
		                  for( int k = 0; k < len; k++ )
		                  {
		                    currentItemChild = childrenOfItem.item(k);
		                    currentItemChildName = currentItemChild.getLocalName();
		                    if (currentItemChildName != null){
		                     if( currentItemChildName.equals("timeLimitAction") || currentItemChildName.equals("dataFromLMS")
		                        || currentItemChildName.equals("completionThreshold") )
		                     {
		                        if( idrefMult < 1 )
		                        {
		                        	insertaError("8.25",EMPTY_STRING);
		                          result = false;
		                        }
		                        else
		                        {
		                           String idrefValue = DOMTreeUtility.getAttributeValue(iTestSubjectNode, "identifierref");
		                           int lenr = mResourceNodes.size();
		                           String id;
		                           String type;
		                           boolean ress= true;
		                           for( int i = 0; i < lenr; i++ )
		                           {
		                              Node currentResource = (Node)mResourceNodes.get(i);
		                              id = DOMTreeUtility.getAttributeValue(currentResource, "identifier");
		                              if( id.equals(idrefValue) )
		                              {
		                                 type = DOMTreeUtility.getAttributeValue(currentResource, "scormType");
		                                 if( !type.equalsIgnoreCase("sco") )
		                                 {
		                                	 insertaError("8.41",EMPTY_STRING);
		                                	 ress = false;
		                                 }
		                              }
		                           }
		                           result = result && ress;

		                        }
		                     }
		                     //vuelvo a lanzar la llamada para cada nodo hijo
		                     result = esNodoCorrecto(currentItemChild, EMPTY_STRING, tipoOde) && result;
		                   }
		                  }
		               } //en if nombre nodo
		               
		            } // end if Items
		       
		       		else if( parentNodeName.equalsIgnoreCase("resource") )
		       		{
		       			metadatosCorrectos(result, iTestSubjectNode);
		       			
		       			Node NodoPadre=iTestSubjectNode.getParentNode();
		       			String nombrePadre = NodoPadre.getLocalName();
		       			if (nombrePadre.equalsIgnoreCase("resources")) {
		       				boolean resourceResult = checkResourceAttributes(iTestSubjectNode, attrList);
		       				result = result && resourceResult;
		       			}
		       		}
		       		else if(parentNodeName.equalsIgnoreCase("file"))
		       		{
		       			metadatosCorrectos(result, iTestSubjectNode);	
		       		}
		        	//chequeo los hijos
		         NodeList children = iTestSubjectNode.getChildNodes();
		         if( children != null )
		         {
		           int numChildren = children.getLength();
		           //hacer el chequeo con los hijos
		           if( parentNodeName.equalsIgnoreCase("manifest") )
		           {

		              // check for mandatory metadata element at package level
		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "metadata");
		              if( multiplicityUsed < 1 )
		              {
		                 result = false;
		                 insertaError("8.26",EMPTY_STRING);
//		                 break;
		              }
		              else
		              {
		                 Node caMetadataNode = DOMTreeUtility.getNode(iTestSubjectNode, "metadata");
		                		                 
		                 // check for mandatory <imscp:schema> element
		                 multiplicityUsed = getMultiplicityUsed(caMetadataNode, "schema");

		                 if( multiplicityUsed < 1 )
		                 {
		                  result = false;
		                  insertaError("8.27",EMPTY_STRING);
//		                  break;
		                 }

		                 // check for mandatory <imscp:schemaversion> element
		                 multiplicityUsed = getMultiplicityUsed(caMetadataNode, "schemaversion");
		                 if( multiplicityUsed < 1 )
		                 {
		                    result = false;
		                    insertaError("8.28",EMPTY_STRING);
//		                    break;
		                 }
		                 
		                 	                 
		                 //check for <imscp:lom>
		                 multiplicityUsed = getMultiplicityUsed(caMetadataNode, "lom");
		                 if (multiplicityUsed >= 1) {
		                	 boolean esLomOK = false;
		                	 boolean idiomasOK = false;
		                	 Vector vLomes = new Vector();
		                	 vLomes=DOMTreeUtility.getNodes(caMetadataNode, "lom");
		                	 for (int nl=0; nl<vLomes.size(); nl++) {
	                    		 Node nLom = (Node)vLomes.get(nl);
	                    		 esLomOK =esLOMCorrecto(nLom);
	                    		 if (!esLomOK) { // lo eliminamos
	                    			 cambiarPorLocation(nLom, caMetadataNode, iTestSubjectNode);
	                    		 }else {
	                    			 idiomasOK = sonIdiomasCorrectos(nLom);
	                    			 if(!idiomasOK){
	                    				 result = false;
	                    			 }
	                    		 }
	                    		 
	                    		 

	                    		 
	                    	 }//fin for
		                 }//fin check lom
		                 
		                 // Borramos los hijos que no sean correctos
		                 String ficheroProperties = "/nodosCorrectosMetadata.properties";
		 				
		 				InputStream is= this.getClass().getResourceAsStream(ficheroProperties);
		 				Properties fproperties = new Properties();
		 				try {
							fproperties.load(is);
						} catch (IOException e) {
							result= false;
							insertaError("8.7",e.getMessage());
//							break;
						}
		 				String nodos=fproperties.getProperty("nodosMetadada").toString();
		 				NodeList hijosCorrectos = caMetadataNode.getChildNodes();
		                if (hijosCorrectos.getLength()>0) {
		                	for (int rHijos=0; rHijos<hijosCorrectos.getLength(); rHijos++ ) {
		                		Node nodoHijo=hijosCorrectos.item(rHijos);
		                		String nomHijo=nodoHijo.getLocalName();
		                		//mirar que lo q borramos no este en el property
		                		if (nomHijo!=null) {
		                    		StringTokenizer token = new StringTokenizer(nodos, ",");
		                    		boolean estaNodo= false;
		   	     					while ((token.hasMoreElements()) && (!estaNodo)) {
		   	     						String nOdo = token.nextElement().toString();
		   	     						if ((nomHijo.equals(nOdo)) ){
		   	     							estaNodo=true;
		   	     						}
		   	     					}
		                    		if (!estaNodo) {
		                    			cambiarPorLocation(nodoHijo, caMetadataNode, iTestSubjectNode);
		                    		}
		                		}
		                	}//end for
		                }

		                                		                 
		              }

		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "organizations");
		              if (( multiplicityUsed < 1 ))
		              {
		            	  insertaError("8.30",EMPTY_STRING);
		                 result = false;
//		                 break;
		              }


		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "resources");
		              if( multiplicityUsed < 1 )
		              {
		                 result = false;
		                 insertaError("8.31",EMPTY_STRING);
//		                 break;
		              }
		           }
		           else if( parentNodeName.equalsIgnoreCase("organizations"))
		           {
		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "organization");
		              if (( multiplicityUsed < 1 ) && (tipoOde.equals("CA")))
		              {
		            	  insertaError("8.32",EMPTY_STRING);
		                 result = false;
//		                 break;
		              }else if ((multiplicityUsed > 0) && (tipoOde.equals("RCP"))){//si es RCP no puede aparecer
		            	  insertaError("8.33",EMPTY_STRING);
		            	   result = false;
//		            	   break;
		              }

		           }
//		               else if( parentNodeName.equalsIgnoreCase("organizations"))
//		               {
//		                  multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "organization");
//		                  if( multiplicityUsed > 0 )
//		                  {
//		                     result = false;
//		                  }
//		                  
//		               }
		           else if( parentNodeName.equalsIgnoreCase("organization") )
		           {
		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "title");
		              if (( multiplicityUsed < 1 ) && (tipoOde.equals("CA")))
		              { 
		            	  insertaError("8.34",EMPTY_STRING);
		                  result = false;
		              }else if ((multiplicityUsed > 0) && (tipoOde.equals("RCP"))){//si es RCP no puede aparecer
		            	  insertaError("8.35",EMPTY_STRING);
		            	   result = false;
		              } 

		              multiplicityUsed = getMultiplicityUsed(iTestSubjectNode, "item");
		              if(( multiplicityUsed < 1 ) && (tipoOde.equals("CA")))
		              {
		            	  insertaError("8.36",EMPTY_STRING);
		                  result = false;
		              }else if ((multiplicityUsed > 0) && (tipoOde.equals("RCP"))){//si es RCP no puede aparecer
		            	  insertaError("8.37",EMPTY_STRING);
		            	   result = false;
		              }

		              
		              metadatosCorrectos(result, iTestSubjectNode);

//		                  // special checks for item
//		                  result = checkItem(iTestSubjectNode, mManifestInfo) && result;

		           }

		           for( int z = 0; z < numChildren; z++ )
		           {

		              Node currentChild = children.item(z);
		              String currentChildName = currentChild.getLocalName();
		              if( currentChildName != null )
		              {

		                 if( ( ( currentChildName.equals("timeLimitAction") ) || ( currentChildName.equals("dataFromLMS") )
		                    || ( currentChildName.equals("completionThreshold") ) || ( currentChildName
		                    .equals("presentation") ) )
		                    && ( !parentNodeName.equals("item") ) )
		                 {
		                	 insertaError("8.38",EMPTY_STRING);
		                       result = false;
		                 }

		                 if( ( ( currentChildName.equals("constrainedChoiceConsiderations") ) || ( currentChildName
		                    .equals("rollupConsiderations") ) )
		                    && ( !parentNodeName.equals("sequencing") ) )
		                 {
		                	 insertaError("8.39",EMPTY_STRING);
		                       result = false;

		                 }

		                 // must enforce that the adlcp:location exist
		                 // as a child of metadata only - warning for best practice.
		                 //quitamos esta validacion para que no de error cuando su padre es technical y pq lo validara desde el Schema
//
//		                     if( ( currentChildName.equals("location") ) && ( !parentNodeName.equals("metadata") ) )
//		                     {
//
//		                        result = false;
//
//		                     }

		                 if( ( currentChildName.equals("sequencing") ) && ( !parentNodeName.equals("item") )
		                        && ( !parentNodeName.equals("organization") )&& ( !parentNodeName.equals("sequencingCollection") ) )
		                     {
		                	 	insertaError("8.40",EMPTY_STRING);
		                	 	result = false;

		                 }
		              } // end something
		              result = esNodoCorrecto(currentChild, EMPTY_STRING, tipoOde) && result;
		           }
		         }
		       
		        break;
		     }
		     // handle entity reference nodes
		     case Node.ENTITY_REFERENCE_NODE:
		     {
		        break;
		     }
		     // text
		     case Node.COMMENT_NODE:
		     {
		        break;
		     }
		     case Node.CDATA_SECTION_NODE:
		     {
		        break;
		     }
		     case Node.TEXT_NODE:
		     {
		        break;
		     }
		     default:
		     {
		        break;
		     }
		  }// end switch statement
		return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    private boolean esLOMCorrecto(Node lom) {
    	boolean resLom= false;
    	String metaValor=EMPTY_STRING;
    	if (lom!= null) {
    		String parentNodeName = lom.getLocalName();
    		Vector metaHijos = new Vector();
            if( parentNodeName.equals("lom") ) {
            	//metaHijos = DOMTreeUtility.getNodes(lom, "metaMetadata");
            	metaHijos = DOMTreeUtility.getNodes(lom, "metaMetadata");
            	if (!metaHijos.isEmpty()) {
            		for (int im=0; im<metaHijos.size(); im++) {
            			Node nMeta = (Node)metaHijos.get(im);
            			Vector schemas = new Vector();
            			schemas = DOMTreeUtility.getNodes(nMeta, "metadataSchema");
            			for (int ll=0; ll<schemas.size();ll++) {
            				Node nsc= (Node)schemas.get(ll);
            				NodeList nl= nsc.getChildNodes();
            				if ((nl!=null) && (nl.getLength()>=1)) {
            					for (int nlh=0; nlh<nl.getLength(); nlh++) {
            						metaValor= nl.item(nlh).getNodeValue();
            						metaValor= metaValor.replaceAll("\\.", EMPTY_STRING);
            						metaValor=metaValor.replaceAll(SPACE_STRING, EMPTY_STRING);
            						String metaValorMinus=metaValor.toLowerCase().trim();
            						
            						String esquemaLomes = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);//LOM-ESv1.0
            						//String esquemaLomes = "LOM-ESv1.0";
            						esquemaLomes = esquemaLomes.replaceAll("\\.", EMPTY_STRING);
            						esquemaLomes = esquemaLomes.replaceAll(SPACE_STRING, EMPTY_STRING);
            						esquemaLomes = esquemaLomes.toLowerCase().trim();
            						//De forma temporal son validos con espacios y las mayus y minus
            						if (metaValorMinus.equals(esquemaLomes)) {//"lom-esv1.0"
            							resLom=true; //si al menos uno no tiene esto... lo tenemos q quitar
            						}
            					}
            				}
            			}
            			
            		}
            	}
            }
    	}
    	return resLom;
    }
    /**
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#chequearHref(java.lang.String)
     */
    public java.lang.Boolean chequearHref(java.lang.String nomContenidos)
    {
        //@todo implement public java.lang.Boolean chequearHref(java.lang.String nomContenidos)
    	Boolean result = true;
//	    String mensaje = EMPTY_STRING;
	    
		if( !( nomContenidos.equals(EMPTY_STRING) ) )
	      {
	         // Chequea si el protocolo es valido
	         if( ( (nomContenidos.length() > 4) && (nomContenidos.substring(0, 5).equals("http:")) ) || 
	             ( (nomContenidos.length() > 5) && (nomContenidos.substring(0, 6).equals("https:")) ) ||
	             ( (nomContenidos.length() > 3) && (nomContenidos.substring(0, 4).equals("ftp:")) ) || 
	             ( (nomContenidos.length() > 4) && (nomContenidos.substring(0, 5).equals("ftps:")) ) )
	         {
	            try
	            {
	               URL url = new URL(nomContenidos);
	               //URL url = new URL("http://www.google-es.com");
//	               URLConnection urlConn = url.openConnection();
//	               HttpURLConnection httpUrlConn = (HttpURLConnection)urlConn;
//	               String pruebaHost=url.getHost();
//	               InetAddress address = InetAddress.getByName( pruebaHost );
//	               String ipAddress=address.getHostAddress();
//	               if( (ipAddress!=null) && (!ipAddress.equals("")) )
//	               {
//	                  mensaje = "ESTADO HTTP CORRECTO";
//	                  logger.debug(mensaje);
//	               }
//	               else
//	               {
//	                  mensaje =  "ERROR DE CONEXION";
//	                  logger.warn(mensaje);
//	                  result = false;
//	               }
	            }
//	            catch (UnknownHostException ue) {
//	            	   mensaje =  "URL DESCONOCIDA" + ue;
//		               logger.warn(mensaje);
//		               result = false;
//	            }
	            catch( MalformedURLException mfue )
	            {
	            	insertaError("8.6",nomContenidos);
	   				logger.warn("URL MAL FORMADA <"+nomContenidos+"> - ", mfue); 
	   				result = false;
	            }
//	            catch( IOException ioe )
//	            {
//	            	insertaError("8.7",nomContenidos);
//	   				logger.warn("ERROR DE ENTRADA/SALIDA"+nomContenidos, ioe); 
//	   				result = false;
//	            }
	         }
	         else if ((nomContenidos.length() <= 5) && ((nomContenidos.startsWith("http")) || (nomContenidos.startsWith("ftp")) )) {
	        	 String pathAbsoluto= getBaseDirectory() + "/" + nomContenidos;
	        	 File fichCont = new File(pathAbsoluto);
	        	 if (!fichCont.exists())
	        	 {
	        		 insertaError("8.8",nomContenidos);
		   			logger.warn("EL FICHERO NO EXISTE <"+nomContenidos+">"); 
	        		result = false;
	        	 }
	         }
	         else if( nomContenidos.substring(0, 5).equals("file:") )
	         {
	            // This is the local file system
	        	 insertaError("8.9",nomContenidos);
	             logger.warn("FICHERO INCORRECTO <"+nomContenidos+">");
	             result = false;
	         }
	         else
	         {
	            try {
					result = chequearLocalURL(nomContenidos);
//					if(!result.booleanValue()){
//						insertaError("8.10",nomContenidos);
//						logger.error("ERROR DE CONTENIDOS"+nomContenidos);
//					}
				} catch (Exception e) {
					insertaError("8.10",nomContenidos);
					logger.error("ERROR DE CONTENIDOS <"+nomContenidos+"> - ",e);
					result = false;
				} 
	         }
	      }
		return result;
    }

    /**
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#chequearLocalURL(java.lang.String)
     */
    public java.lang.Boolean chequearLocalURL(java.lang.String nomContenidos) throws Exception
    {
        //@todo implement public java.lang.Boolean chequearLocalURL(java.lang.String nomContenidos)
    	 Boolean resultado = true;
//	      String mensaje = EMPTY_STRING;
	     
	    	 String pathAbsoluto = getBaseDirectory() + "/" + nomContenidos;
		     int queryIndex = pathAbsoluto.indexOf('?');
	         if( queryIndex > 0 )
	         {
	            pathAbsoluto = pathAbsoluto.substring(0, queryIndex);
	         }

	         int fragmentIndex = pathAbsoluto.indexOf('#');
	         if( fragmentIndex > 0 )
	         {
	            pathAbsoluto = pathAbsoluto.substring(0, fragmentIndex);
	         }
	       
//	        try
//	         { 	 
//	             pathAbsoluto = URLDecoder.decode(pathAbsoluto, "UTF-8");
//	         }
//	         catch( UnsupportedEncodingException uee )
//	         {
//	             mensaje= "ENCODING INCORRECTO" + uee;
//	             logger.error(mensaje);
//	             throw uee;
//	         }
	         try
	         {
	            File fichAElegir = new File(pathAbsoluto);
	            File fichAux = (new File(pathAbsoluto)).getCanonicalFile();
	            String rutaAux = fichAux.getPath();
	            String nomStringConverted = null;
	            if(!File.separator.equals("/")) nomStringConverted = nomContenidos.replace('/', File.separatorChar);
	            else nomStringConverted = nomContenidos;
	            //en nomStringConverted tenemos el fichero solo sea contenido\index.html, ./index.html, ../index.html.... si existe alguno de los dos ultimos habria que quitarlo
	            if (nomStringConverted.startsWith("..\\")) {
	            	int longiPuntos = nomContenidos.split("\\../").length;
	            	if (longiPuntos>1) {
	            		String nomAux=nomContenidos.split("\\../")[longiPuntos-1];
	            		nomStringConverted=nomAux;
	            	}
	            	
	            }else if (nomStringConverted.startsWith(".\\")) {
	            	int longiPuntos = nomContenidos.split("\\./").length;
	            	if (longiPuntos>1) {
	            		String nomAux=nomContenidos.split("\\./")[longiPuntos-1];
	            		nomStringConverted=nomAux;
	            	}
	            }
	            boolean ok= rutaAux.endsWith(nomStringConverted);
	            logger.debug("Chequeo si URL ISFILE <" + fichAElegir.isFile()+"> y si el contenido de URL es correcto <" + ok+">");
	            if(( fichAElegir.isFile() ) && (ok))
	            {
	               String tempStr = fichAElegir.getPath();
	               tempStr = tempStr.replace('\\', '/');
	               mFileList.remove(tempStr);
	            }
//
//		            File fichAElegir = new File(pathAbsoluto);
//		            if( fichAElegir.isFile() )
//		            {
//		               String tempStr = fichAElegir.getPath();
//		               tempStr = tempStr.replace('\\', '/');
//		               mFileList.remove(tempStr);
//		            }
	            else
	            {
	            	insertaError("8.9",nomContenidos);
	               logger.warn("FICHERO INCORRECTO <" + pathAbsoluto+">");
	               resultado = false;
	            }
	         }
	         catch( NullPointerException npe )
	         {
	        	 insertaError("8.11",EMPTY_STRING);
	            logger.error("ERROR nullpointer, el objeto no puede estar vacio - ",npe);
	            resultado = false;
	            throw npe;
	         }
	         catch( SecurityException se )
	         {
	        	 insertaError("8.12",EMPTY_STRING);
	            logger.error("ERROR DE SEGURIDAD - ",se);
	            resultado = false;
	            throw se;
	         }
	      
	      return resultado;
    }

    /**
     * @see es.pode.validacion.negocio.dominio.ManifestEntity#chequearExcesoEquipaje()
     */
    public String chequearExcesoEquipaje()
    {
    	String mensaje = EMPTY_STRING;
    	String ficheros= EMPTY_STRING;
    	StringBuilder ficherosTemp = new StringBuilder();
	      if( mFileList.size() > 0 )
	      {
	         // If there is only one file in the list
	         if( mFileList.size() == 1 )
	         {
	            mensaje =" El siguiente fichero está incluido en el contenido del paquete pero no ha sido "
	            	   + "referenciado en el fichero manifest: "; 
	            	
	         }
	         // If there is more than one file in the list
	         else
	         {
	            mensaje = "Los siguientes ficheros han sido incluidos en el contenido del paquete "
	               + "pero no han sido referencados en el fichero manifest: ";
	               
	         }
	         for( int i = 0; i < mFileList.size(); i++ )
	         {
	        	 ficherosTemp.append(";" + (String)mFileList.get(i));
	            
	         }

	      }// end if
	      ficheros=ficherosTemp.toString();
	      String ficherosAux=EMPTY_STRING;
	      if (ficheros.startsWith(";", 0))
	      {
	    	  ficherosAux=ficheros.substring(1, ficheros.length());
	      }
	      else {
	    	  ficherosAux=ficheros;
	      }
	      return mensaje + ficherosAux;
    }
    
    // Devuelve true si el nodo raiz es un manifest o un lom
    public Boolean esRootValido(java.lang.String rutaManifest)
    {
       Boolean result = false;
       String fichImsManifest = rutaManifest + File.separator + "imsmanifest.xml";
	   File manifestFile = new File(fichImsManifest);
	   if( manifestFile.exists() )
	    {
	    	 Node rootNode = mDocument.getDocumentElement();
	         String rootNodeName = rootNode.getLocalName();
	         String rootNodeNamespace = rootNode.getNamespaceURI();

	         if( rootNodeName.equals("manifest") )  
	         {
	            if ( rootNodeNamespace.equals( DOMTreeUtility.IMSCP_NAMESPACE ) )
	            {
	               result = true;
	            }
	         }
	         else if( rootNodeName.equals("lom") )  
	         {
	            if ( rootNodeNamespace.equals( DOMTreeUtility.IEEE_LOM_NAMESPACE ) )
	            {
	               result = true;
	            }
	         }
	      }
	     
       return result;
    }
    ///////////////////////////////////////////////////////////////
    ///    			METODOS NUEVOS DE PRUEBA 			///
    
//    /**
//     * This method sets up the input source for the test subject file.
//     *
//     * @param iFileName The name of the file we are are setting up the input 
//     * source for.
//     *
//     * @return Returns an input source needed for parsing
//     */
//    private InputSource setUpInputSource( String iFileName )
//    {
//       InputSource is = new InputSource();
//       //is = setupFileSource(iFileName);
//       return is;
//    }
    
//    /**
//     * Sets up the file source for the test subject file.
//     *
//     * @param iFileName file to setup input source for.
//     *
//     * @return InputSource
//     */
//    private InputSource setupFileSource( InputStreamReader iFileName)
//    {
//       String msgText;
//       boolean defaultEncoding = true;
//       String encoding = null;
//       PushbackInputStream inputStream;
//       FileInputStream inFile;
//
//       try
//       {
//            InputSource is = null;            
//            defaultEncoding = true;
//            int longi= iFileName.toString().length();
//            BufferedReader inStream =  new BufferedReader(iFileName);
//            StringBuffer dataString = new StringBuffer();
//            String s = ""; 
//            char[] cbuf = new char[9999];
//            //Builds the encoded file to be parsed
//                
//            int in=inStream.read(cbuf);
//            s = new String(cbuf);
//            inStream.close();
//            is = new InputSource(new StringReader(dataString.toString()));
//            is.setEncoding(encoding); 
//            return is;
//       }
//       catch ( NullPointerException  npe )
//       {
//          msgText = "Null pointer exception" + npe; 
//        }
//       catch ( SecurityException se )
//       {
//          msgText = "Security Exception" + se; 
//       }
//       catch ( FileNotFoundException fnfe )
//       {
//          msgText = "File Not Found Exception" + fnfe; 
//       }
//       catch ( Exception e )
//       {
//          msgText = "General Exception" + e; 
//       }
//
//       return new InputSource();
//    }
    //////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////
    
    //No se usa?
//    private final Object transformEntity(final int transform, final es.pode.validador.negocio.dominio.ManifestEntityDaoImpl entity)
//    {
//        Object target = null;
//        if (entity != null)
//        {
//            switch (transform)
//            {
//                case es.pode.validador.negocio.dominio.ManifestEntityDao.TRANSFORM_VALIDAVO :
//                    target = toValidaVO(entity);
//                    break;
//                case TRANSFORM_NONE : // fall-through
//                default:
//                    target = entity;
//            }
//        }
//        return target;
//    }

// No se usa?
//    private final void transformEntities(final int transform, final java.util.Collection entities)
//    {
//        switch (transform)
//        {
//            case es.pode.validador.negocio.dominio.ManifestEntityDao.TRANSFORM_VALIDAVO :
//                toValidaVOCollection(entities);
//                break;
//            case TRANSFORM_NONE : // fall-through
//                default:
//                // do nothing;
//        }
//    }
    public final void toValidaVOCollection(java.util.Collection entities)
    {
        if (entities == null)
        {
            entities = java.util.Collections.EMPTY_LIST;
        }
        else
        {
            org.apache.commons.collections.CollectionUtils.transform(entities, VALIDAVO_TRANSFORMER);
        }
    } 
    
    protected es.pode.validador.negocio.servicio.ValidaVO toValidaVO(Object[] row)
    {
        return null;
    }
    
    private final org.apache.commons.collections.Transformer VALIDAVO_TRANSFORMER =
        new org.apache.commons.collections.Transformer()
        {
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof es.pode.validador.negocio.dominio.ManifestEntityDaoImpl)
                {
                    result = toValidaVO((es.pode.validador.negocio.dominio.ManifestEntityDaoImpl)input);
                }
                else if (input instanceof Object[])
                {
                    result = toValidaVO((Object[])input);
                }
                return result;
            }
        };
        
        
        public es.pode.validador.negocio.dominio.ManifestEntityDaoImpl fromValidaVO(final es.pode.validador.negocio.servicio.ValidaVO vo) {
            //default mapping between VO and entity
            return (es.pode.validador.negocio.dominio.ManifestEntityDaoImpl) 
                      this.getBeanMapper().map(vo, es.pode.validador.negocio.dominio.ManifestEntityDaoImpl.class, DEF_MAPPING_VALIDAVO_MANIFESTENTITY);
        }
        
        public void fromValidaVO(es.pode.validador.negocio.servicio.ValidaVO vo, es.pode.validador.negocio.dominio.ManifestEntityDaoImpl entity) {
            //default mapping between VO and entity
            this.getBeanMapper().map(vo, entity, DEF_MAPPING_VALIDAVO_MANIFESTENTITY);
        }
        public es.pode.validador.negocio.servicio.ValidaVO toValidaVO(final es.pode.validador.negocio.dominio.ManifestEntityDaoImpl entity)
        {
            //default mapping between entity and VO
            //@todo verify or customize behaviour of this mapping
            return (es.pode.validador.negocio.servicio.ValidaVO) 
                      this.getBeanMapper().map(entity, es.pode.validador.negocio.servicio.ValidaVO.class, DEF_MAPPING_MANIFESTENTITY_VALIDAVO);
        }


        private void parse(DOMParser parser, String nombreFichero) throws Exception {
//        	erroresParseo = new ArrayList();
        	if(logger.isDebugEnabled()) logger.debug("Vacio lista de errores - Iniciando parseo de <" + nombreFichero+">");
        	int numErrores = erroresXerces.size();//num errores antes del parseo
        	ficheroActual="imsmanifest.xml";
        	parser.parse(nombreFichero);
        	if(numErrores < erroresXerces.size()) {//Si hay nuevos errores lanzamos la exception
        		String msg = "El fichero <" + nombreFichero + "> tiene errores de parseo";
        		logger.warn(msg);
        		// Lanzo una excepcion para que se detecte el parseo erroneo. Los mensajes de error estan guardados en erroresXerces 
        		throw new SAXException(msg);
        	}
        	
        }
        
        //usado en los parseos de lomes
        private void parse(DOMParser parser, InputSource isrc, String nombreFichero) throws Exception {
        	if(logger.isDebugEnabled()) logger.debug("Vacio lista de errores - Iniciando parseo de " + nombreFichero);
        	int numErrores = erroresXerces.size();//num errores antes del parseo
        	ficheroActual=nombreFichero;
        	parser.parse(isrc);
        	if(numErrores < erroresXerces.size()) {//Si hay nuevos errores en el array lanzamos la exception
        		String msg = "Fichero " +ficheroActual+" con errores de parseo";
        		logger.debug(msg);
        		// Lanzo una excepcion para que se detecte el parseo erroneo. Los mensajes de error estan guardados en erroresXerces
        		throw new SAXException(msg);
        	}
        	
        }
        
        private ErrorParseoVO generaErrorParseoVO(int linea, int columna, String mensaje) {
        	ErrorParseoVO error = new ErrorParseoVO();
        	error.setLinea(linea);
        	error.setColumna(columna);
        	error.setMensaje(mensaje);
        	return error;
        }
        
		public void error(SAXParseException exception) throws SAXException {
			logger.error("Error de parseo: <" + exception.getMessage()+"> - ",exception);
			erroresXerces.add(generaErrorParseoVO(exception.getLineNumber(),
				exception.getColumnNumber(), exception.getMessage() + ";" + ficheroActual));
		}


		public void fatalError(SAXParseException exception) throws SAXException {
			logger.fatal("Error Fatal de parseo: <" + exception.getMessage()+"> - ",exception);
			erroresXerces.add(generaErrorParseoVO(exception.getLineNumber(),
					exception.getColumnNumber(), exception.getMessage() + ";" + ficheroActual));
		}


		public void warning(SAXParseException exception) throws SAXException {
			logger.warn("Error de parseo: <" + exception.getMessage()+">",exception);
		}
		
		private void insertaError(String error, String textoAnadido){
			boolean encontrado=true;
			for(int i=0;i<erroresParseo.size()&& encontrado;i++){
				ErrorParseoVO errorParseo=(ErrorParseoVO)erroresParseo.get(i);
				String mensaje=errorParseo.getMensaje();
				int posicion=mensaje.indexOf("*");
				if(posicion>0){
					String inicioMensaje=mensaje.substring(0, posicion-1);
					String finMensaje=mensaje.substring(posicion+1);
					if(error.equals(inicioMensaje)){
						if(textoAnadido.equals(EMPTY_STRING)|| finMensaje.equals(textoAnadido)){
							encontrado=false;	
						}
						
					}
				}	
			}
			
			if(encontrado){
				erroresParseo.add(generaErrorParseoVO(0, 0, error+" *  "+textoAnadido));
			}
	
		}
		
		
		private boolean sonIdiomasCorrectos(Node lom) {
	    	boolean correcto= true;
	    	boolean esIdiomaPlat =true;
	    	if (lom!= null) {
	    		String parentNodeName = lom.getLocalName();
	    		Vector hijos = new Vector();
	            if( parentNodeName.equals("lom") ) {
	            	//GENERAL
	            	hijos = DOMTreeUtility.getNodes(lom, "general");
	            	correcto =correcto && idiomaCorrecto(hijos);
	            	if(correcto)
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	//LIFECYCLE
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "lifeCycle");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//METAMETADATA 
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "metaMetadata");
		            	if(hijos.size()>0){
		            		Node metaMetadata = (Node) hijos.get(0);
		            		Node language = DOMTreeUtility.getNode(metaMetadata, "language");
		            		if(language !=null){
			            		NodeList nList=language.getChildNodes();
			            		String idioma = null;
			            		if (nList!=null && nList.getLength()>0){
			            			Node nValor = nList.item(0);
			            			if(nValor!=null)
			            				idioma = nValor.getNodeValue();
			            			}
			            		if(idioma !=null){
			            			esIdiomaPlat = esIdiomaDePlataforma(idioma);
			            			if(!esIdiomaPlat)
			            				insertaError("8.46", EMPTY_STRING);
			            		}
		            		}
	            		}	
	            		correcto =correcto && idiomaCorrecto(hijos);
	            		if(correcto)
	            			correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//TECHNICAL
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "technical");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//EDUCATIONAL
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "educational");
	            		correcto =correcto && idiomaCorrecto(hijos);
	            		if(correcto)
	            			correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//RIGHTS
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "rights");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//RELATION
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "relation");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//ANNOTATION
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "annotation");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            	//CLASIFICATION
	            	if(correcto){
	            		hijos = DOMTreeUtility.getNodes(lom, "classification");
	            		correcto =correcto && atributosIdiomaCorrectos(hijos,true);
	            	}
	            }
	    	}
	    	if (!correcto)
	    		insertaError("8.42",EMPTY_STRING);
	    	return correcto && esIdiomaPlat;
	    }
	    
	    private boolean atributosIdiomaCorrectos(Vector hijos,boolean correcto){
        	if (!hijos.isEmpty()) {
        		for (int im=0; im<hijos.size() && correcto; im++) {
        			Node nodo = (Node)hijos.get(im);
        			NodeList hijosNodo=nodo.getChildNodes();
        			for(int i=0;i<hijosNodo.getLength() && correcto;i++){
        				Node hijo=hijosNodo.item(i);
            			Vector vString = new Vector();
            			String s=hijo.getLocalName();
            			vString = DOMTreeUtility.getNodes(hijo, "string");
            			if(vString.size()<=0 && s!=null){
            				Vector v =new Vector();
            				v.add(hijo);
            				correcto=atributosIdiomaCorrectos(v,correcto);
            			}else{
	            			for(int j=0;j<vString.size() && correcto;j++){
	            				Node nString = (Node)vString.get(j);
	                			NamedNodeMap atributos=nString.getAttributes();
	                			Node n=null;
	                			if(atributos!=null){
	                				n=atributos.getNamedItem("language");
	                				String idioma=EMPTY_STRING;
	                				if(n!=null){
	                					idioma=n.getNodeValue();
	                					correcto = correcto && validaIdioma(idioma);//Comparacion con idioma iso
	                				}
	                			}			
	            			}
            			}
        			}
        		}
        	}
        	return correcto;
	    }
	    
	    private boolean idiomaCorrecto(Vector hijos){
	    	boolean correcto= true;
	    	String valor=EMPTY_STRING;
        	if (!hijos.isEmpty()) {
        		for (int im=0; im<hijos.size() && correcto; im++) {
        			Node nodo = (Node)hijos.get(im);
        			Vector schemas = new Vector();
        			schemas = DOMTreeUtility.getNodes(nodo, "language");
        			for (int ll=0; ll<schemas.size() && correcto;ll++) {
        				Node nsc= (Node)schemas.get(ll);
        				NodeList nl= nsc.getChildNodes();
        				if ((nl!=null) && (nl.getLength()>=1)) {
        					for (int nlh=0; nlh<nl.getLength() && correcto; nlh++) {
        						valor= nl.item(nlh).getNodeValue();
        						correcto = correcto && validaIdioma(valor);//Comparacion con idioma iso
        					}
        				}
        			}		
        		}
        	}
        	return correcto;
	    }
	    
	    private boolean esIdiomaDePlataforma(String idioma){
	    	boolean enc = false;
	    	try{
	    		
	    		// El idioma debe ser uno de los idiomas buscables en la plataforma, para que se pueda indexar en un índice
		    	String[] idiomasBuscables= I18n.getInstance().obtenerIdiomasBuscables();
	    		//String[] idiomasBuscables = {"es","en","ca","gl","va","eu"};
		    	int i = 0;
		    	if(logger.isDebugEnabled())logger.debug("IDIOMAS BUSCABLES DE PLATAFORMA <" + Arrays.toString(idiomasBuscables)+">");
		    	while (i< idiomasBuscables.length && !enc){
		    		if (idiomasBuscables[i].equals(idioma))
		    			enc = true;
		    		i++;
		    	}
			} catch (Exception e) {
				
				String mensaje = "ERROR validando idioma de catalogación - " + e;
				logger.error(mensaje);
			}
			if(logger.isDebugEnabled())logger.debug("es Idioma de Plataforma <" + enc+">");
			return enc;
	    }
	    
	    private boolean validaIdioma(String idioma){ 
	    	//([xX]{1}\\-([a-zA-Z0-9]{1,8}))
	    	//([a-zA-Z]{1,2})
	    	
	    	//cadenas que comienzan por "x-" mas un mínimo de un caracter y un máximo de ocho caracteres
	        Pattern pattern = Pattern.compile("^([xX]{1}\\-([a-zA-Z0-9]{1,8}))$");

	        Matcher matcher = pattern.matcher(idioma.trim());
	        boolean valido1=true;
	        if (!matcher.find()) {
	        	valido1 = false;
	        }
	        
	        //cadena de una o dos letra
	        pattern = Pattern.compile("^([a-zA-Z]{1,2})$");

	        matcher = pattern.matcher(idioma.trim());
	        
	        boolean valido2=true;
	        if (!matcher.find()) {
	        	valido2 = false;
	        }
	    	
	    	
	    	return valido1 || valido2;
	    }
	    private void metadatosCorrectos(boolean result,Node iTestSubjectNode) throws IOException{
	    	Node caMetadataNode = DOMTreeUtility.getNode(iTestSubjectNode, "metadata");
	    	if(caMetadataNode!=null){
	            int multiplicityUsed = getMultiplicityUsed(caMetadataNode, "lom");
	            if (multiplicityUsed >= 1) {
	            	boolean esLomOK = false;
	           	 	boolean idiomasOK = false;
	           	 	Vector vLomes = new Vector();
	           	 	vLomes=DOMTreeUtility.getNodes(caMetadataNode, "lom");
	           	 	for (int nl=0; nl<vLomes.size(); nl++) {
	           	 		Node nLom = (Node)vLomes.get(nl);
	           	 		esLomOK =esLOMCorrecto(nLom);
		           	 	if (!esLomOK) { // lo eliminamos
	           			 cambiarPorLocation(nLom, caMetadataNode, iTestSubjectNode);
	              		}
		           	 	else{
		           	 		idiomasOK = sonIdiomasCorrectos(nLom);
		           	 		if(!idiomasOK){
		           	 			result = false;
		           	 			
		           	 		}
		           	 	}
	           
	           	 	}//fin for
	            }//fin check lom
            
				// Borramos los hijos que no sean correctos
				NodeList hijosCorrectos = caMetadataNode.getChildNodes();
				if (hijosCorrectos.getLength()>0) {
				   for (int rHijos=0; rHijos<hijosCorrectos.getLength(); rHijos++ ) {
				   		Node nodoHijo=hijosCorrectos.item(rHijos);
				   		String nomHijo=nodoHijo.getLocalName();
				   		//mirar que lo que borramos sea distinto de lom y location
				   		if (nomHijo!=null) {
				   			if (!(nomHijo.equals("lom")) && !(nomHijo.equals("location"))){
								cambiarPorLocation(nodoHijo, caMetadataNode, iTestSubjectNode);
							}
				   		}
				   }//end for
			   }
	    	}
	    }
	
	    private void cambiarPorLocation(Node metadato, Node caMetadataNode,Node iTestSubjectNode) throws IOException{

    		Document doc =iTestSubjectNode.getOwnerDocument();
            Element location= doc.createElement("adlcp:location");
    	    
			//comprobamos que no existe otro fichero con ese nombre, si ya existe buscamos otro que este libre
            String nombreMetadatoNoValido="metadatos/metadato" + indiceMetadato +".xml";
            String rutaMetadato = new StringBuffer(getBaseDirectory()).append("/").append(nombreMetadatoNoValido).toString();
            File fileMetadato = new File(rutaMetadato);
            while(fileMetadato.exists()){
            	indiceMetadato++;
            	nombreMetadatoNoValido="metadatos/metadato" + indiceMetadato +".xml";
                rutaMetadato = new StringBuffer(getBaseDirectory()).append("/").append(nombreMetadatoNoValido).toString();
                fileMetadato = new File(rutaMetadato);
            }
			indiceMetadato++;
			metadatos.add(metadato);
			nombreMetadatos.add(nombreMetadatoNoValido);
			//sustituimos el metadato no valido por la etiqueta location con la localizacion de fichero donde lo hemos guardado
			location.appendChild(doc.createTextNode(nombreMetadatoNoValido));
            caMetadataNode.replaceChild(location, metadato);
            setHaCambiadoDocument(true);
	    }
	    
	    private void escribirMetadatos()throws IOException {
            //comprobamos que la carpeta donde vamos a guardar los metadatos incorrectos existe, si no existe la creamos 
	    	FileOutputStream fs =null;
            String carpeta=new StringBuffer(getBaseDirectory()).append("/metadatos").toString();
            File carpetaMetadatos= new File(carpeta);
			if(!carpetaMetadatos.exists())
			{
				carpetaMetadatos.mkdirs();
			}
			for(int i = 0;i<metadatos.size();i++){
				Node metadato = (Node) metadatos.get(i);
				
	            String nombreMetadatoNoValido=(String) nombreMetadatos.get(i);
	            String rutaMetadato = new StringBuffer(getBaseDirectory()).append("/").append(nombreMetadatoNoValido).toString();
	            File fileMetadato = new File(rutaMetadato);
	            //escribimos el archivo con el metadato no valido
	            
				fs = new FileOutputStream(fileMetadato);
				DOMBuilder dom = new DOMBuilder("org.jdom.adapters.XercesDOMAdapter");
				org.jdom.Element finalDoc = dom.build(
						(org.w3c.dom.Element)metadato );
				new XMLOutputter().output(finalDoc,fs);
				
			}
			fs.close();
	    }
	   
		public List getLomes() {
			return lomes;
		}

		public void setLomes(List lomes) {
			this.lomes = lomes;
		}
	    
		
	public Boolean validarTodosLomes() {
		boolean result = true;
		InputSource isrc = null;

		try {
			for (int i = 0; i < lomes.size(); i++) {// cada nodo lom lo pasamos
													// a un inputsource y lo
													// parseamos
				Node lom = (Node) lomes.get(i);

				NamedNodeMap atributos = lom.getAttributes();
				boolean enc = false;
				for (int j = 0; j < atributos.getLength() && !enc; j++) {
					Node atributo = atributos.item(j);
					String valor = atributo.getLocalName();
					if (valor.equals("schemaLocation")) {
						enc = true;
						atributos.removeNamedItem(atributo.getNodeName());
					}
				}

				DOMBuilder dom = new DOMBuilder(
						"org.jdom.adapters.XercesDOMAdapter");
				org.jdom.Element finalDoc = dom
						.build((org.w3c.dom.Element) lom);

				Writer writer = new StringWriter();
				XMLOutputter outputter = new XMLOutputter();
				outputter.output(finalDoc, writer);

				String xml = writer.toString();
				isrc = new InputSource(new StringReader(xml));

				result = parsearLomes(isrc, (String) nombreLomes.get(i))
						&& result;
			}
		} catch (Exception e) {

			String mensaje = "ERROR validando los LOM-ES - " + e;
			logger.error(mensaje);
		}

		return result;

	}
		
		
		public boolean parsearLomes (InputSource lomes,String nombreFichero) throws Exception {
			boolean result = true;

	    	String ficheroProperties = "/validador.properties";
			InputStream is= null;
	    	DOMParser mParser = new DOMParser();
	    	
			if (mParser != null){
				try
				{
					if( logger.isDebugEnabled() )
						logger.debug("URL del parseador: <" + mParser.getClass().getResource( mParser.getClass().getName().replace('.','/') + ">.class"));
			    	
					is= this.getClass().getResourceAsStream(ficheroProperties);
					Properties fproperties = new Properties();
					fproperties.load(is);
					String lomCustom = fproperties.getProperty("lomCustom");
					String url_lomCustom = fproperties.getProperty("url_lomCustom");
					
					File fileLomCustom=new File(lomCustom);
					String sLomCustom=fileLomCustom.getCanonicalPath();
					
					
					mParser.setFeature("http://xml.org/sax/features/validation", true);
					mParser.setFeature("http://xml.org/sax/features/namespaces", true);
				     
					//estas "feature" son para la validación del manifest contra los esquemas
					mParser.setFeature("http://apache.org/xml/features/validation/schema",true);
				      
					mParser.setFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", true);
				     
					String namespacesFull = url_lomCustom + SPACE_STRING+ sLomCustom;
						
					mParser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", namespacesFull);
				      
					mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
					mParser.setErrorHandler(this);
				}
				catch (SAXException se )
				{
					String mensaje = "ERROR SAXException - " + se;
					logger.warn(mensaje);
					result=false;
				} finally {
					is.close();
				}
				   
				try{
					parse(mParser,lomes,nombreFichero);
				}
				catch ( SAXException se )
				{
					logger.error("ERROR EN EL XML. Lomes con errores de parseo - ",se);	
					result=false;
				} 
			}
			return result;	
		}

		
		
		public Boolean tieneLomes(org.w3c.dom.Node arbolDoc) throws Exception{
			boolean result=true;

            Node rootNode = ( (Document)arbolDoc ).getDocumentElement();
            if(rootNode!=null){
            	Node metadata = DOMTreeUtility.getNode(rootNode, "metadata");
            	if (metadata!=null){
            		Node lom = DOMTreeUtility.getNode(metadata, "lom");
            		if(lom!=null){
            			if(!esLOMCorrecto(lom))
            				result = false;
            		}
            		else {
            			Node location = DOMTreeUtility.getNode(metadata, "location");
            			if(location!=null){
            				String localizacion = location.getFirstChild().getNodeValue();
            				//se obtiene el lom que se referencia en el location y se comprueba si esta bien formado
            				// y si es un lom-esv1.0
            				result = obtenerNodoLomDeLocation(localizacion).booleanValue();

            			}
            			else {
            				result = false;
            			}
            		}
            	}
            	else result =false;
            }
            else result =false;
            if (!result)
            	insertaError("2.1", EMPTY_STRING);
			return result;
		}
		
		private Boolean obtenerNodoLomDeLocation(String localizacion) throws Exception{
			Boolean result = true;
			Document docValido = null;
			mParser = new DOMParser();
			if (mParser != null){
				try
			      {
				      mParser.setFeature("http://xml.org/sax/features/validation", false);
				      mParser.setFeature("http://xml.org/sax/features/namespaces", true);
				      mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
					
				      mParser.setErrorHandler(new XMLErrorHandler());//no añadimos los errores de xerces al array
				      
				} catch (SAXException se ) {
				      logger.error("ERROR EN EL XML - ",se);
				      result = false;
			    }
				localizacion =getBaseDirectory() + "/" + localizacion;
				String nomFich = buscaFichero( localizacion, "xml" ); 
				
//				File tempXMLFich = new File(nomFich);
//				String xsdLoc = "file:///" + tempXMLFich.getParent() + "/";  
//				xsdLoc = xsdLoc.replaceAll( SPACE_STRING, "%20");  
//				xsdLoc = xsdLoc.replace( '\\', '/');
					
				if ( !nomFich.equals(EMPTY_STRING) ) {
					try
					{	
						parse(mParser,nomFich );
						
						docValido = mParser.getDocument();
						if ( docValido != null ) {		
							Node lom = mParser.getDocument().getDocumentElement();
							
							if(!esLOMCorrecto(lom))
								result = false;
						}
					}
					catch ( SAXException se ) {
						result = false;//Lomes mal formado
						logger.error("ERROR EN EL LOMES - ",se);					
					}
					catch ( IOException ioe ) {
						result = false;//Si algun fichero no se ha encontrado
						logger.error("ERROR DE ENTRADA/SALIDA - ",ioe);
					}
				}
				else 
					result = false;
			 }
			
			return result;
		}
		
		
		//Se buscan en el nodo iTestSubjectNode todos los lomes que contenga. Los referenciados mediante location se obtienen siempre
		//pero los lomes que este incrustados en el manifest, solo se obtienen para la validacion binaria(obtenerValidacionBin)
		//y la validacion del empaquetador(obtenerValidacion), en estos dos casos el valor de soloLocation es true
	    public boolean buscarLomes (org.w3c.dom.Node iTestSubjectNode,boolean soloLocation) throws Exception
	    {
	        //@todo implement public java.lang.Boolean chequearContenido(org.w3c.dom.Document arbolDoc, java.lang.String rutaManifest)
	    	boolean result = true;
	    	
	    	//variables para almacenar si existe error de existencia de más de un lom
	    	
	    	int lomesEncontrados = 0;
	    	
			switch( iTestSubjectNode.getNodeType() )
			  {
			     case Node.PROCESSING_INSTRUCTION_NODE:
			     {
			        // Skip any processing instructions, nothing for us to do
			        break;
			     }
			     case Node.DOCUMENT_NODE:
			     {
			        Node rootNode = ( (Document)iTestSubjectNode ).getDocumentElement();
			        result = buscarLomes(rootNode,soloLocation);
			        break;
			     }
			     case Node.ELEMENT_NODE:
			     {
			    	 String parentNodeName = iTestSubjectNode.getLocalName();
			    	 /**********location************/
			    	 if(parentNodeName ==null){
			    		 parentNodeName= iTestSubjectNode.getNodeName();//location
			    	 }
			    	 /**********location************/
			       
			    	 if( parentNodeName.equalsIgnoreCase("item"))
			    	 {
			    		 NodeList childrenOfItem = iTestSubjectNode.getChildNodes();
			    		 if( childrenOfItem != null ){
			    			 Node currentItemChild;
			    			 String currentItemChildName;
			    			 int len = childrenOfItem.getLength();
			    			 for( int k = 0; k < len; k++ ){
			    				 currentItemChild = childrenOfItem.item(k);
			    				 currentItemChildName = currentItemChild.getLocalName();
			    				 if (currentItemChildName != null){
			    					 //vuelvo a lanzar la llamada para cada nodo hijo
			    					 result = buscarLomes(currentItemChild,soloLocation) && result;
			    				 }
			    			 }
			    		 } //en if nombre nodo  
			    	 } // end if Items
			       
			       	else if( parentNodeName.equalsIgnoreCase("resource") ){
			       		obtenerLomes(iTestSubjectNode,soloLocation);
			       	}
			       	else if(parentNodeName.equalsIgnoreCase("file")){
			       		obtenerLomes(iTestSubjectNode,soloLocation);	
		       		}
			        //chequeo los hijos
			    	NodeList children = iTestSubjectNode.getChildNodes();
			        int multiplicityUsed = -1;
			        if( children != null ){
			        	int numChildren = children.getLength();
			        	//hacer el chequeo con los hijos
			        	if( parentNodeName.equalsIgnoreCase("manifest") ){
			        		// check for mandatory metadata element at package level
			        		Node caMetadataNode = DOMTreeUtility.getNode(iTestSubjectNode, "metadata");
			  
			        		if (caMetadataNode != null){
			        			multiplicityUsed = getMultiplicityUsed(caMetadataNode, "lom");
			        			
			        			if ((multiplicityUsed >= 1) && soloLocation){
			        				
			        				boolean esLomOK = false;
				        			Vector vLomes = new Vector();
				        			vLomes=DOMTreeUtility.getNodes(caMetadataNode, "lom");
				        			for (int nl=0; nl<vLomes.size(); nl++) {
				        				Node nLom = (Node)vLomes.get(nl);
				        				esLomOK =esLOMCorrecto(nLom);
				        				if (esLomOK) { //sustituimos lomes por la etiqueta location y añadimos el lomes a la lista de lomes para
				        					//para despues hacerles el parsearLom
				        									        					
						        			//ha encontrado, al menos, 1 lom, luego lo tenemos en cuenta
						        			lomesEncontrados = lomesEncontrados +1;
				        				}
				        			}
			        			}
			        			
			        			if (lomesEncontrados > 1){
			        				
			        				//tenemos que sacar el error
			        				setLomesBien(false);
			        			}
			        		}
			        		else{
			        			multiplicityUsed= 0;
			        		}

			        		if (multiplicityUsed >= 1 && !soloLocation) {
			        			
			        			boolean esLomOK = false;
			        			Vector vLomes = new Vector();
			        			vLomes=DOMTreeUtility.getNodes(caMetadataNode, "lom");
			        			for (int nl=0; nl<vLomes.size(); nl++) {
			        				Node nLom = (Node)vLomes.get(nl);
			        				esLomOK =esLOMCorrecto(nLom);
			        				if (esLomOK) { //sustituimos lomes por la etiqueta location y añadimos el lomes a la lista de lomes para
			        					//para despues hacerles el parsearLom
			        					lomes.add(nLom);
			        					nombreLomes.add(EMPTY_STRING);
			        					caMetadataNode.removeChild(nLom);
			        					
					        			//ha encontrado, al menos, 1 lom, luego lo tenemos en cuenta
					        			lomesEncontrados = lomesEncontrados +1;
			        				}
			        			}//fin for
			        			
			        			if (lomesEncontrados > 1){
			        				
			        				//tenemos que sacar el error
			        				setLomesBien(false);
			        			}
			        		}//fin check lom 
			        		
			        		if (caMetadataNode != null){
			        			multiplicityUsed = getMultiplicityUsed(caMetadataNode, "location");
			        			
			        		}
			        		else{
			        			multiplicityUsed= 0;
			        		}
			                if (multiplicityUsed >= 1) {
			                	Vector vLocation = new Vector();
			               	 	vLocation=DOMTreeUtility.getNodes(caMetadataNode, "location");
			               	 	for (int nl=0; nl<vLocation.size(); nl++) {
			               	 		Node nLocation = (Node)vLocation.get(nl);          		 
			                      	String localizacion=nLocation.getFirstChild().getNodeValue();
			                      	obtenerLomesLocation(localizacion);//obtenemos el lomes si lo hubiese de la localizacion indicada
			                      	//para seguir contando los lomes detectados
			                      	Iterator it = nombreLomes.iterator();
			                      	boolean lomen = false;
			                      	while ((!lomen) && (it.hasNext())){
			                      		
			                      		if (localizacion.equals(it.next())){
				                      		
			                      			lomen = true;
				                      		lomesEncontrados = lomesEncontrados + 1;
				                      	}
			                      	}
			                      	
			               	 	}//fin for
			               	 	
			               	 	//volvemos a comprobar los lomes encontrados
			               	 	if (lomesEncontrados > 1){
			        				
			        				//tenemos que sacar el error
			        				setLomesBien(false);
			        			}
			                }//fin check lom
			        		
			        	}

			        	else if( parentNodeName.equalsIgnoreCase("organization") )
			        	{		              
			        		obtenerLomes(iTestSubjectNode,soloLocation);
			        	}

			        	for( int z = 0; z < numChildren; z++ )
			        	{
			        		Node currentChild = children.item(z);
			        		result = buscarLomes(currentChild,soloLocation) && result;
			        	}
			        }
			       
			        break;
			     }
			     // handle entity reference nodes
			     case Node.ENTITY_REFERENCE_NODE:
			     {
			        break;
			     }
			     // text
			     case Node.COMMENT_NODE:
			     {
			        break;
			     }
			     case Node.CDATA_SECTION_NODE:
			     {
			        break;
			     }
			     case Node.TEXT_NODE:
			     {
			        break;
			     }
			     default:
			     {
			        break;
			     }
			  }// end switch statement
			return result;
	    }

	//Se obtienen los lomes que contenga el nodo, tanto los referenciados mediante location como los incrustados en el manifest
    private void obtenerLomes(Node iTestSubjectNode,boolean soloLocation) throws Exception{
    	
    	//para controlar el número de lomes
    	int lomesEncontrados = 0;
    	
    	Node caMetadataNode = DOMTreeUtility.getNode(iTestSubjectNode, "metadata");
    	if(caMetadataNode!=null){
            int multiplicityUsed = getMultiplicityUsed(caMetadataNode, "lom");
            if (multiplicityUsed >= 1 && !soloLocation) {
            	boolean esLomOK = false;
           	 	Vector vLomes = new Vector();
           	 	vLomes=DOMTreeUtility.getNodes(caMetadataNode, "lom");
           	 	for (int nl=0; nl<vLomes.size(); nl++) {
           	 		Node nLom = (Node)vLomes.get(nl);          		 
           	 		esLomOK =esLOMCorrecto(nLom);

               		 if (esLomOK) { 
               			lomes.add(nLom);
               			nombreLomes.add(EMPTY_STRING);
               			caMetadataNode.removeChild(nLom);

            		 }
           	 	}//fin for
           	 	
            }//fin check lom
            
            multiplicityUsed = getMultiplicityUsed(caMetadataNode, "location");
            if (multiplicityUsed >= 1) {
            	Vector vLocation = new Vector();
           	 	vLocation=DOMTreeUtility.getNodes(caMetadataNode, "location");
           	 	for (int nl=0; nl<vLocation.size(); nl++) {
           	 		Node nLocation = (Node)vLocation.get(nl);          		 
                  	String localizacion=nLocation.getFirstChild().getNodeValue();
                  	obtenerLomesLocation(localizacion);            	
           	 	}//fin for           	 	
            }//fin check lom
    	}
    }
    
    //Obtiene el lomes que hay en la localizacion indicada, añadiendo dicho lomes al array de lomes si esta bien formado y es un LOM-ESv1.0
    private void obtenerLomesLocation(String localizacion)throws Exception{
    	if (lomes ==null)
			lomes = new ArrayList();
    	
		Document docValido = null;
		mParser = new DOMParser();
		if (mParser != null){
			try
		      {
			      mParser.setFeature("http://xml.org/sax/features/validation", false);
			      mParser.setFeature("http://xml.org/sax/features/namespaces", true);
			      mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
				
			      mParser.setErrorHandler(new XMLErrorHandler());//no añadimos los errores de xerces al array
			} catch (SAXException se ) {
			      logger.error("ERROR EN EL XML - ",se);
			}
			String locCompleta = "";
			if(!localizacion.startsWith("http")){
				locCompleta =getBaseDirectory() + "/" + localizacion;
				String nomFich = buscaFichero( locCompleta, "xml" ); 
				File tempXMLFich = new File(nomFich);
//				String xsdLoc = "file:///" + tempXMLFich.getParent() + "/";  
//				xsdLoc = xsdLoc.replaceAll( SPACE_STRING, "%20");  
//				xsdLoc = xsdLoc.replace( '\\', '/');
					
				if ( !nomFich.equals(EMPTY_STRING) ) {
					try
					{	
						parse(mParser,nomFich );
						
						docValido = mParser.getDocument();
						if ( docValido != null ) {		
							Node lom = mParser.getDocument().getDocumentElement();					
							
							if(esLOMCorrecto(lom)){
								lomes.add(lom);
								nombreLomes.add(localizacion);
							}
						}
					}
					catch ( SAXException se ) {
						//Lom mal formado
						logger.error("ERROR EN EL LOMES - ",se);					
					}
					catch ( IOException ioe ) {
						//Si algun fichero no se ha encontrado
						logger.error("ERROR DE ENTRADA/SALIDA - ",ioe);
					}
				}
				
			}
		}
    }    
//    public void escribirFicheros(String nomFich) throws Exception{
//		if (getHaCambiadoDocument()) {
////			if (validadorRes) {
//				nomFich=nomFich.replaceAll("\\", "/");
//				FileOutputStream fs = new FileOutputStream(new File(nomFich)); //machacamos imsmanifest
//				DOMBuilder dom = new DOMBuilder("org.jdom.adapters.XercesDOMAdapter");
//				org.jdom.Document finalDoc = dom.build(
//						(org.w3c.dom.Document)mDocument );
//				
//				new XMLOutputter().output(finalDoc,fs);
//				
//				escribirMetadatos();//se escribe en ficheros los metadatos que no sean LOM-ESv1.0
////			}
//		
//		}
//    }

    //No se usa?
//    private boolean compruebaScormtype (String rutaManifest, NamespaceYEsquema nye) throws Exception{
//		File docFile = new File(rutaManifest);
//		org.jdom.Document doc = null;
//    	boolean encontrado= true;
//    	String valorAtributo= null;
//    	String adlcp=null;
//    	
//    	try{
//    		
//        	String ficheroProperties = "/validador.properties";
//        	InputStream is= null;
//        	is= this.getClass().getResourceAsStream(ficheroProperties);
//			Properties fproperties = new Properties();
//			fproperties.load(is);
//			
//			String urlAdlcp= fproperties.getProperty("url_adlcp_rootv1p2");
//    		
//      		SAXBuilder builder = new SAXBuilder(false);
//    		doc = builder.build(docFile);
//    		org.jdom.Element manifest = doc.getRootElement();
//    		Namespace nameSpace = manifest.getNamespace();
//    		
//    		List additionalNamespaces= manifest.getAdditionalNamespaces();
//    		if (additionalNamespaces!= null){
//    			for (int i=0; i< additionalNamespaces.size(); i++){
//        			valorAtributo= ((Namespace)additionalNamespaces.get(i)).getURI();
//        			if (valorAtributo != null){
//        				if (valorAtributo.equals(urlAdlcp)){
//        					adlcp=((Namespace) additionalNamespaces.get(i)).getPrefix();
//        				}
//        			}
//        		}
//    		}
//    		
//    		if (adlcp != null){
//    			org.jdom.Element resources=manifest.getChild("resources", nameSpace);
//        		List resourceList= resources.getChildren();
//        		for (int i=0; i< resourceList.size() && encontrado == true; i++){
//        			Attribute scormtype= ((org.jdom.Element) resourceList.get(i)).getAttribute("scormtype", nameSpace);
//        			if (scormtype == null){
//        				encontrado=false;
//        			}
//        		}
//    		}
//    		else{
//    			encontrado= false;
//    		}
//    		
//    	}
//    	catch (Exception e){
//    		String mensaje = "ERROR en la construcción Jdom para el manifest " + e;
//     		logger.error(mensaje);
//        	}
//    	
//    	return encontrado;
//	}
    
    private boolean compruebaNameSpacesIMSCP (String rutaManifest, Properties fproperties) throws Exception{
    	boolean isOK= true;
    	File docFile = new File(rutaManifest);
		org.jdom.Document doc = null;
		String uri= null;
		
		try{
			String url_imscp_v1p1= fproperties.getProperty("url_imscp_v1p1");
			String url_imsmd_v1p2p4= fproperties.getProperty("url_imsmd_v1p2p4");
			String url_lomCustom= fproperties.getProperty("url_lomCustom");
			String url_schema_instance= fproperties.getProperty("url_schema_instance");
	    	
	    	SAXBuilder builder = new SAXBuilder(false);
			doc = builder.build(docFile);
			org.jdom.Element manifest = doc.getRootElement();
			Namespace nameSpace = manifest.getNamespace();
			
			List namespaces= manifest.getAdditionalNamespaces();
			for (int i= 0; i< namespaces.size(); i++){
				String uriNamespace= ((Namespace) namespaces.get(i)).getURI();
				if (!url_imscp_v1p1.equals(uriNamespace) && !url_imsmd_v1p2p4.equals(uriNamespace) && !url_lomCustom.equals(uriNamespace) && !url_schema_instance.equals(uriNamespace)){
					insertaError("8.49", uriNamespace);
    				isOK= false;
				}	
			}
				org.jdom.Element organizations= manifest.getChild("organizations", nameSpace);
				List organizaciones= organizations.getChildren();
				for (int i=0; i< organizaciones.size(); i++){
					List items=((org.jdom.Element)organizaciones.get(i)).getChildren();
					isOK= compruebaAtributosItemIMSCP (items);
					if (erroresParseo.size()>0){
						isOK= false;
					}
				}
				org.jdom.Element resources=manifest.getChild("resources", nameSpace);
				List recursos= resources.getChildren();
				for (int i=0; i< recursos.size(); i++){
					List atributos=((org.jdom.Element)recursos.get(i)).getAttributes();
					for (int k=0; k<atributos.size();k++){
						String atributo= ((org.jdom.Attribute) atributos.get(k)).getName();
						if (!atributo.equals("identifier") && !atributo.equals("type") && !atributo.equals("base") && !atributo.equals("href")){
							insertaError("8.51", EMPTY_STRING);
		    				isOK= false;
						}
					}
				}
		}
		catch (Exception e){
			String mensaje = "ERROR en compruebaNameSpacesIMSCP - " + e;
     		logger.error(mensaje);
		}
	
    	return isOK;
    }
    
    private boolean compruebaAtributosItemIMSCP (List items) throws Exception{
    	boolean isOK= true;
    	boolean temp= true;
    	
    	if (items != null){
    		for (int j=0; j<items.size(); j++){
    			List atributos= ((org.jdom.Element)items.get(j)).getAttributes();
    			for (int k=0; k<atributos.size();k++){
    				String atributo= ((org.jdom.Attribute) atributos.get(k)).getName();
    				if (!atributo.equals("identifier") && !atributo.equals("identifierref") && !atributo.equals("isvisible") && !atributo.equals("parameters")){
    					insertaError("8.50", EMPTY_STRING);
        				isOK= false;
    				}
    			}
    			List itemsHijos= ((org.jdom.Element)items.get(j)).getChildren();
    			if (itemsHijos != null){
    				temp= compruebaAtributosItemIMSCP (itemsHijos);
    			}	
    			if (isOK){
    				if (temp == false){
    					isOK= false;
    				}
    			}
    		}
    	}
    	return isOK;
    }
    
}
