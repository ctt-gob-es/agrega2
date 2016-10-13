package es.pode.migraciones;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Classification;
import es.pode.parseadorXML.castor.ComplexTypePurposeVocabSource;
import es.pode.parseadorXML.castor.ComplexTypePurposeVocabValue;
import es.pode.parseadorXML.castor.Description;
import es.pode.parseadorXML.castor.GroupClassificationClassification;
import es.pode.parseadorXML.castor.GroupIdId;
import es.pode.parseadorXML.castor.GroupPurposePurpose;
import es.pode.parseadorXML.castor.GroupSourceSource;
import es.pode.parseadorXML.castor.GroupTaxonPathTaxonPath;
import es.pode.parseadorXML.castor.GroupTaxonTaxon;
import es.pode.parseadorXML.castor.Id;
import es.pode.parseadorXML.castor.Keyword;
import es.pode.parseadorXML.castor.LanguageStringItem;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.Purpose;
import es.pode.parseadorXML.castor.Source;
import es.pode.parseadorXML.castor.Taxon;
import es.pode.parseadorXML.castor.TaxonPath;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


public class Migracion
{
	private static java.util.Properties pAuditoriaProperties = null;
	
	private static Logger logger = Logger.getLogger(Migracion.class);
	
	private static boolean modificadoLom=true;
	
	private static boolean descomprimido = false;
	
	private static PrintWriter pw;
	
	private static String datos = "";
	
	public static PrintWriter getPw() {
		return pw;
	}

	public static void setPw(PrintWriter pw) {
		Migracion.pw = pw;
	}
	
	public static String getDatos() {
		return datos;
	}

	public static void setDatos(String datos) {
		Migracion.datos = datos;
	}

	public static boolean isDescomprimido() {
		return descomprimido;
	}

	public static void setDescomprimido(boolean descomprimido) {
		Migracion.descomprimido = descomprimido;
	}

	public static boolean getModificadoLom() {
		return modificadoLom;
	}
	
	public static void setModificadoLom(boolean modLom) {
		modificadoLom=modLom;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		System.out.println("Migracion de odes");
	
		logger.info("Inicio Migracion de odes");
		///////////PARA probar en local vm Arguments :-Drepositorio=C:/MDA/jboss-4.0.2/bin/uploads/taller/migraciones -Deducacion="Nivel educativo LOM-ESv1.0" -Daccesibilidad="Accesibilidad LOM-ESv1.0" -Dcompetencia="Competencia LOM-ESv1.0" -Ddisciplina_arbol="Árbol curricular LOE 2006" -Ddisciplina_tesauro="ETB-LRE MEC-CCAA V.1.0"
		String repositorio = System.getProperty("repositorio");
		logger.info("Path del repositorio de odes "+repositorio);
		
		logger.info("Comenzamos a explorar la carpeta repositorio.... ");
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		java.util.Random random = new java.util.Random();
		int num = random.nextInt();
		if(num < 0)
			num = -num;
		
		File fileOut = new File("informeOdesTaxon_" + sdf.format(new java.util.Date()) + "_" + num + ".csv" );
		if (!fileOut.exists()) {
			fileOut.createNewFile();
		}
		FileWriter fw = new FileWriter(fileOut, true);
		PrintWriter pwr = new PrintWriter(fw);
		setPw(pwr);
		
		validarTaxones(repositorio);
		logger.info("Fin migración de Odes");
		System.out.println("Finalización migracion de odes");		
		
		fw.close();
	}
	
	private static Lom procesarLom(Lom lom,LomAgrega lomAgrega, ManifestAgrega manAgrega) throws Exception{

		String educacion =AgregaPropertiesImpl.getInstance().getProperty("educacion");
		String accesibilidad = AgregaPropertiesImpl.getInstance().getProperty("accesibilidad");
		String competencia = AgregaPropertiesImpl.getInstance().getProperty("competencia");
		String disciplina_arbol = AgregaPropertiesImpl.getInstance().getProperty("disciplina_arbol");
		String disciplina_tesauro=AgregaPropertiesImpl.getInstance().getProperty("disciplina_tesauro");
		
		Lom lomNuevo= new Lom();
		String[] informe = {",", ",", ",", ",", ","};
		HashMap hasTaxones = new HashMap();
		//Lom lom = manAgrega.obtenerLom(manAgrega.getManifest().getIdentifier(), null);
		if (lom!=null) {
			ArrayList listaClassification= lomAgrega.getClassificationsAgrega();
			ClassificationAgrega[] arrayClassification = (ClassificationAgrega[])listaClassification.toArray(new ClassificationAgrega[listaClassification.size()]);
			List listaClassificationOut = new ArrayList();
			ArrayList listaResultado;
			
			for (int iC=0; iC<arrayClassification.length; iC++){
			//for (Iterator iterator = listaClassification.iterator(); iterator.hasNext();) {
				//ClassificationAgrega clasification = (ClassificationAgrega) iterator.next();
				//Classification clasif=clasification.getClassification();
				Classification clasif=arrayClassification[iC].getClassification();
				//Propósito
				Purpose purpose = clasif.getGroupClassificationClassification().getPurpose();
			    java.lang.String proposito = purpose.getGroupPurposePurpose().getComplexTypePurposeVocabValue().getContent();
			    logger.info("datos del proposito " + proposito);
			    
			    //Taxones
			    TaxonPath[] taxonPath=clasif.getGroupClassificationClassification().getTaxonPath();
			    List arrTaxonPathOut = new ArrayList();
			    boolean moverTaxonPath=false;
			    for (int t=0;t<taxonPath.length;t++) {
			    	//source
			    	Source source =taxonPath[t].getGroupTaxonPathTaxonPath().getSource();
			    	LanguageStringItem langStringItem =source.getGroupSourceSource().getLanguageStringItem(0);
			    	java.lang.String valorSource = langStringItem.getString().getContent().toString();
			    	//control sobre proposito y Fuente(Source!!!!!)
			    	String[] propFuen = new String[] {"educational level", "accessibility restrictions", "competency", "discipline"};			    	
			    	//////////////////////AHORA HAY Q CONTROLAR Q SI ES "educational level" SU Fuente sea Nivel Educativo LOM-ESv1.0
			    	////////// si es "accessibility restrictions" su Fuente sea Accesibilidad LOM-ESv1.0
			    	///////// si es "competency" su fuente sea Competencia LOM-ESv1.0
			    	///////// si es "discipline" su fuente es "Arbol curricular loe 2006 o Tesauro ETB...."
			    	//
			    	if (propFuen[0].equals(proposito)) { //es educational level
			    		//Caso de que venga LOMESv1.0 en source
			    		if ((!valorSource.equals(educacion)) && (!valorSource.equals(accesibilidad)) && (!valorSource.equals(competencia))
			    				&& (!valorSource.equals(disciplina_arbol)) && (!valorSource.equals(disciplina_tesauro))) {//Tenemos que modificar el Source, si  no es ni educacion ni ningun otro!
			    		   valorSource= educacion;
			    		   setModificadoLom(modificadoLom && false);
			    		   logger.info("Se ha modificado la fuente de educational level ");
			    		   informe[0] = "Se ha modificado la fuente correspondiente al propósito educational level,";
			    		}else{
			    			//en caso de que sea accesibilidad o competencia movemos el taxonPath.
			    			if ((valorSource.equals(accesibilidad)) || (valorSource.equals(competencia)) || (valorSource.equals(disciplina_arbol)) || (valorSource.equals(disciplina_tesauro))){
			    				moverTaxonPath=true;
			    				setModificadoLom(modificadoLom && false);
			    				informe[0] = "El taxonPath  mal ubicado: propósito " + proposito + " y source: " +  valorSource +",";
			    			}
			    		}
			    		
			    	}else if (propFuen[1].equals(proposito)) { //es accesibilidad
			    		if ((!valorSource.equals(accesibilidad))&& (!valorSource.equals(educacion)) &&(!valorSource.equals(competencia))
			    				&& (!valorSource.equals(disciplina_arbol)) && (!valorSource.equals(disciplina_tesauro))) {//Tenemos que modificar el Source
				    		   valorSource= accesibilidad;
				    		   setModificadoLom(modificadoLom && false);
				    		   logger.info("Se ha modificado la fuente de accessibility restrictions ");
				    		   informe[1] = "Se ha modificado la fuente correspondiente al propósito accessibility restrictions,";
				    		}else{
				    			//en caso de que sea educacion o competencia movemos el taxonPath.
				    			if ((valorSource.equals(educacion)) || (valorSource.equals(competencia)) || (valorSource.equals(disciplina_arbol)) || (valorSource.equals(disciplina_tesauro))){
				    				moverTaxonPath=true;
				    				setModificadoLom(modificadoLom && false);
				    				informe[1] =  "El taxonPath  mal ubicado: propósito " + proposito + " y source: " +  valorSource +",";
				    			}
				    		}

			    	} else if(propFuen[2].equals(proposito)) { // competencia
			    		if ((!valorSource.equals(competencia)) && (!valorSource.equals(educacion)) && (!valorSource.equals(accesibilidad))
			    				&& (!valorSource.equals(disciplina_arbol)) && (!valorSource.equals(disciplina_tesauro))) {//Tenemos que modificar el Source
				    		   valorSource= competencia;
				    		   setModificadoLom(modificadoLom && false);
				    		   logger.info("Se ha modificado la fuente de competency ");
				    		   informe[2] = "Se ha modificado la fuente correspondiente al propósito competency,";
				    	}else{
			    			//en caso de que sea educacion o accesibilidad movemos el taxonPath.
			    			if ((valorSource.equals(educacion)) || (valorSource.equals(accesibilidad)) || (valorSource.equals(disciplina_arbol)) || (valorSource.equals(disciplina_tesauro))){
			    				moverTaxonPath=true;
			    				setModificadoLom(modificadoLom && false);
			    				informe[2] =  "El taxonPath  mal ubicado: propósito " + proposito + " y source: " +  valorSource +",";
			    			}
			    		}
			    	}else if (propFuen[3].equals(proposito)) { 
			    		// disciplina--> SOLO CONTROLAMOS Q NO INCLUYA UN NIVEL EDUCATIVO, ACCESIBILIDAD NI COMPETENCIA
			    		if ((valorSource.equals(competencia)) || (valorSource.equals(educacion)) || (valorSource.equals(accesibilidad))){
			    			moverTaxonPath=true;
			    			setModificadoLom(modificadoLom && false);
		    				informe[3] =  "El taxonPath  mal ubicado: propósito " + proposito + " y source: " +  valorSource +",";
			    		}
			    	}

			    	//creamos la estructura de salida
			    	Source sourceOut = new Source();
			    	GroupSourceSource groupSourceSource = new GroupSourceSource();
			    	LanguageStringItem langStrIt = new LanguageStringItem();
			    	es.pode.parseadorXML.castor.String strCastor= new es.pode.parseadorXML.castor.String();
			    	strCastor.setContent(valorSource); //modificamos con el source
			    	strCastor.setLanguage(langStringItem.getString().getLanguage());
			    	langStrIt.setString(strCastor);
			    	
			    	groupSourceSource.addLanguageStringItem(0, langStrIt);
			    	sourceOut.setGroupSourceSource(groupSourceSource);
			    	
			    	//metemos source y array de Taxon en TaxonPath
			    	GroupTaxonPathTaxonPath groupTaxonPathTaxonPath = new GroupTaxonPathTaxonPath();
			    	groupTaxonPathTaxonPath.setSource(sourceOut);
			    	//fin de estructura de salida para Source
			    	/////////////////////////////////////////
			    	////Recorremos los taxones para comprobar los identificadores
			    	Taxon[] taxon=taxonPath[t].getGroupTaxonPathTaxonPath().getTaxon();
			    	List arrtaxonOut =new ArrayList();
			    	for(int a=0; a<taxon.length;a++) {
			    		java.lang.String idTaxon =taxon[a].getGroupTaxonTaxon().getId().getGroupIdId().getContent();
			    		//logger.info("Datos de los taxones; idTaxon: " + idTaxon);
			    		//MODIFICAMOS el contenido del taxon
			    		//Si el taxón empieza o termina por un caracter raro, hay q quitarlo
						//en principio generamos un array que luego se tendra que pasar a un properties
						String[] caracteres = new String[] {".", ","};/////////////////////////!!!!!!!!!!!!!!!!!!!!!!
						for (int indiCar=0; indiCar< caracteres.length; indiCar++) {
							String caracter = caracteres[indiCar];
							if (idTaxon.startsWith(caracter)) {														
								logger.debug("El taxon comienza por " + caracter + " : " + idTaxon);
								if (idTaxon.length()>0){
									//quitamos todos los que tenga delante
									int indi =0;
									boolean esta=false;
									while ((indi<idTaxon.length()) && (!esta)) {
										if (idTaxon.substring(indi, idTaxon.length()).startsWith(caracter)) {
											indi++;
										} else {
											esta=true;
										}
									}
									idTaxon=idTaxon.substring(indi, idTaxon.length());														
									setModificadoLom(modificadoLom && false);
									informe[4] = "Se ha modificado la ruta taxonómica,";
								}
								logger.info("Taxon Modificado: " + idTaxon);
							}
							if (idTaxon.endsWith(caracter)) {
								logger.info("El taxon finaliza por " + caracter + " : " + idTaxon);
								if (idTaxon.length()>1) {									
										//quitamos todos los que tenga detras
										int indi =idTaxon.length();
										boolean esta=false;
										while ((indi>0) && (!esta)) {
											if (idTaxon.substring(0,indi).endsWith(caracter)) {
												indi--;
											} else {
												esta=true;
											}
										}
									idTaxon=idTaxon.substring(0, indi);
									setModificadoLom(modificadoLom && false);
									informe[4] = "Se ha modificado la ruta taxonómica,";
								}
								logger.info("Taxon modificado: " + idTaxon); 
							}
						}
			    		//////////////////fin modificacion contenido taxon
			    		//creams Id para la nueva estrucutra de idTaxon
			    		Id myIdtaxon = new Id();
			    		GroupIdId groupIdId = new GroupIdId();
			    		groupIdId.setContent(idTaxon); //metemos el idtaxon modificado!!
			    		myIdtaxon.setGroupIdId(groupIdId);
			    		//taxon[a].getGroupTaxonTaxon().setId(myIdtaxon);
			    		GroupTaxonTaxon groupTaxonTaxon =new GroupTaxonTaxon();
			    		groupTaxonTaxon.setEntryTaxon(taxon[a].getGroupTaxonTaxon().getEntryTaxon());
			    		groupTaxonTaxon.setId(myIdtaxon);
			    		Taxon taxaux = new Taxon();
			    		taxaux.setGroupTaxonTaxon(groupTaxonTaxon);
			    		//taxonOut[a].setGroupTaxonTaxon(groupTaxonTaxon);
			    		arrtaxonOut.add(taxaux);
			    		//tenemos creado el array de Taxon
			    	}//fin Array Taxon
			    	
			    	
			    	Taxon[] taxonOut = (Taxon[])arrtaxonOut.toArray(new Taxon[arrtaxonOut.size()]);
			    	groupTaxonPathTaxonPath.setTaxon(taxonOut);
			    	TaxonPath unTaxonPath = new TaxonPath();
			    	unTaxonPath.setGroupTaxonPathTaxonPath(groupTaxonPathTaxonPath);
			    	/////////////////////////////////////////////////////////////////
			    	if 	(moverTaxonPath){ //el taxonpath que no se corresponde con proposito y ademas su source está correcto!!!! los metemos en una estructura auxiliar y al final les buscamos una ubicacion!!
			    		//como identificador vamos a poner classification_1_Nivel_Educativo-LOM-ESv1.0_X siendo X la posicion del taxonpath en la clasificacion 
			    		String clave= "Clasificacion_"+iC+"_"+valorSource+"_" + t;
			    		hasTaxones.put(clave, unTaxonPath);
			    		moverTaxonPath=false;
			    		//ya no lo metemos en el array
			    	}else {
			    	////////////////////////////////////////////////////////////////
			    		//metemos cada taxonPath en un array, si no es uno que haya q mover
			    		arrTaxonPathOut.add(unTaxonPath);
			    	}
			    	
			    }//fin taxonPath (recorrido de taxones!)
			    Classification clasifOut = new Classification();
			    GroupClassificationClassification groupClassificationClassification = new GroupClassificationClassification();
			    TaxonPath[] taxonPathOut = (TaxonPath[])arrTaxonPathOut.toArray(new TaxonPath[arrTaxonPathOut.size()]);
			    groupClassificationClassification.setTaxonPath(taxonPathOut); //modificado
			    //los anteriores
			    groupClassificationClassification.setDescription(clasif.getGroupClassificationClassification().getDescription());
			    groupClassificationClassification.setKeyword(clasif.getGroupClassificationClassification().getKeyword());
			    groupClassificationClassification.setPurpose(clasif.getGroupClassificationClassification().getPurpose());				   
			    clasifOut.setGroupClassificationClassification(groupClassificationClassification);
			    
			    ClassificationAgrega clasificationOut = new ClassificationAgrega(clasifOut);
			    listaClassificationOut.add(clasificationOut); //ya tenemos la lista de ClassificationAgrega

			    
			    
			}//Fin For clasificaciones
			//lomAgrega.getClassificationsAgrega();
			ClassificationAgrega[] arrClasifAgrega = (ClassificationAgrega[]) listaClassificationOut.toArray(new ClassificationAgrega[listaClassificationOut.size()]);
			//ahora tenemos que volver a colocar taxones en clasificaciones o crearlas o borrarlas si están vacias
			ClassificationAgrega[] clasifAgregaCo=buscaClasificationParaTaxones(arrClasifAgrega, hasTaxones);
			////////////////////////////////////////////////////
			if (clasifAgregaCo==null){
				clasifAgregaCo = new ClassificationAgrega[0];
			}
			lomAgrega.setClassificationsAgrega(clasifAgregaCo);
			// con esta modificacion debemos de haber machacado el classification
			
			
			LomAgrega lomAgregaNuevo = new LomAgrega(lomNuevo);
			lomNuevo = lom;
			String inf = "";
			for(int i = 0; i < informe.length; i ++){
				if (!informe[i].equals(",")){
					inf += informe[i].toString();
				}
			}
			setDatos(inf);
		}
		return lomNuevo;
		
	}
	
	private static ClassificationAgrega[] buscaClasificationParaTaxones(ClassificationAgrega[] arrClasifAgrega, HashMap hashTaxones) {
		
		String educacion =AgregaPropertiesImpl.getInstance().getProperty("educacion");
		String accesibilidad = AgregaPropertiesImpl.getInstance().getProperty("accesibilidad");
		String competencia = AgregaPropertiesImpl.getInstance().getProperty("competencia");
		String disciplina_arbol = AgregaPropertiesImpl.getInstance().getProperty("disciplina_arbol");
		String disciplina_tesauro=AgregaPropertiesImpl.getInstance().getProperty("disciplina_tesauro");
		Collection listaClassificationOut = new ArrayList();
		ClassificationAgrega[] classiAgrega = null;
		try {
			if ((hashTaxones!=null) && (!hashTaxones.isEmpty())){
				Iterator iterHashtaxones= hashTaxones.entrySet().iterator();
				while ( iterHashtaxones.hasNext()) {
					Map.Entry entry = (Map.Entry)iterHashtaxones.next();
					String clave=entry.getKey().toString();
					TaxonPath taxonpath= (TaxonPath)entry.getValue();
					//desglosamos la clave para ver dnd tenemos que buscarlo: classification_1_Nivel_Educativo-LOM-ESv1.0_X siendo X la posicion del taxonpath en la clasificacion 
					//y recorremos las clasificaciones para ver dnd lo ubicams
					String propositoAbuscar="";
					//String[] propFuen = new String[] {"educational level", "accessibility restrictions", "competency", "discipline"};	
					if (clave.contains(educacion)){
						propositoAbuscar="educational level";
					} else if (clave.contains(accesibilidad)){
						propositoAbuscar="accessibility restrictions";					
					}else if (clave.contains(competencia)){
						propositoAbuscar="competency";
					}else if ((clave.contains(disciplina_arbol)) || (clave.contains(disciplina_tesauro))) {
						propositoAbuscar="discipline";
					}
					
					int indiClasi=0;
					boolean agregado=false;
					while ((indiClasi<=arrClasifAgrega.length-1) && (arrClasifAgrega[indiClasi]!=null) && (!agregado)){
						if ((arrClasifAgrega[indiClasi].getProposito()!=null) && (arrClasifAgrega[indiClasi].getProposito().equals(propositoAbuscar))){
							Classification clasiOld=arrClasifAgrega[indiClasi].getClassification();
							Classification clasiNew=new Classification();							
							//tengo q añadirle un TaxonPath
							Collection arrTaxonPathOut = new ArrayList();
							TaxonPath[] taxonIn= clasiOld.getGroupClassificationClassification().getTaxonPath();							
							arrTaxonPathOut.addAll(Arrays.asList(taxonIn));
							//metemos el taxonpath
							arrTaxonPathOut.add(taxonpath);
							//hacemos la conversion
							TaxonPath[] taxonPathOut = (TaxonPath[])arrTaxonPathOut.toArray(new TaxonPath[arrTaxonPathOut.size()]);
							GroupClassificationClassification groupClassificationClassification = new GroupClassificationClassification();
							groupClassificationClassification.setTaxonPath(taxonPathOut); //modificado
							groupClassificationClassification.setDescription(clasiOld.getGroupClassificationClassification().getDescription());
							groupClassificationClassification.setKeyword(clasiOld.getGroupClassificationClassification().getKeyword());
							groupClassificationClassification.setPurpose(clasiOld.getGroupClassificationClassification().getPurpose());							
							clasiNew.setGroupClassificationClassification(groupClassificationClassification);
							
							//ClassificationAgrega clasificationOut = new ClassificationAgrega(clasiNew);
							//listaClassificationOut.add(clasificationOut);
							
							arrClasifAgrega[indiClasi].setClassification(clasiNew);
							//actualizamos la variable agregado							
							agregado=true;
						}
						indiClasi++;
					}
					//inicializamos el array
					listaClassificationOut=new ArrayList();
					listaClassificationOut.addAll(Arrays.asList(arrClasifAgrega));
					
					//Opción no hay definida clasificacion para ese propósito--> creamos una nueva clasificacion
					if (!agregado){
						Classification clasifOut = new Classification();
					    GroupClassificationClassification groupClassificationClassification = new GroupClassificationClassification();
					    List arrTaxonPathOut = new ArrayList();
					    //metemos el taxonpath
						arrTaxonPathOut.add(taxonpath);
					    TaxonPath[] taxonPathOut = (TaxonPath[])arrTaxonPathOut.toArray(new TaxonPath[arrTaxonPathOut.size()]);
					    groupClassificationClassification.setTaxonPath(taxonPathOut); //modificado
					    //los anteriores
					    groupClassificationClassification.setDescription(new Description());
					    groupClassificationClassification.setKeyword(new Keyword[0]);
					    Purpose proposito = new Purpose();
					    GroupPurposePurpose gpp = new GroupPurposePurpose();
					    ComplexTypePurposeVocabSource ctpvs = new ComplexTypePurposeVocabSource();
					    ctpvs.setContent("LOM-ESv1.0");
					    gpp.setComplexTypePurposeVocabSource(ctpvs);
					   
					    ComplexTypePurposeVocabValue ctpvv = new ComplexTypePurposeVocabValue();
					    ctpvv.setContent(propositoAbuscar);//propositooo
					    gpp.setComplexTypePurposeVocabValue(ctpvv);
					    proposito.setGroupPurposePurpose(gpp);
					    groupClassificationClassification.setPurpose(proposito);					    
					    clasifOut.setGroupClassificationClassification(groupClassificationClassification);
					    ClassificationAgrega clasificationOutNew = new ClassificationAgrega(clasifOut);
					    //inicializamos el array
					    listaClassificationOut=new ArrayList();
					    listaClassificationOut.addAll(Arrays.asList(arrClasifAgrega)); //añadimos el resto
					    listaClassificationOut.add(clasificationOutNew);
					    
					}
					//sobre escribimos el array de clasificaciones para q tenga en cuenta las nuevas modificaciones
					arrClasifAgrega = (ClassificationAgrega[])listaClassificationOut.toArray(new ClassificationAgrega[listaClassificationOut.size()]);
					
				}//fin iterTaxones
				//ahora que hems terminado, vams a borrar las clasificaciones q no tienen taxones
				ArrayList arrClasifinal = new ArrayList();
				for (int icl=0; icl<arrClasifAgrega.length; icl++){
					//arrClasifAgrega[icl].getIndiceUltimoTaxonPath() si vacio, dev -1
					if ((arrClasifAgrega[icl]!=null) && (arrClasifAgrega[icl].getIndiceUltimoTaxonPath()>=0)){
						arrClasifinal.add(arrClasifAgrega[icl]);
					}
				}//fin for
				//ahora habrems filtrado los q no tengan taxones!!
				classiAgrega = (ClassificationAgrega[]) arrClasifinal.toArray(new ClassificationAgrega[arrClasifinal.size()]);
				
			}//fin if
			else {				
				classiAgrega = new ClassificationAgrega[arrClasifAgrega.length];
				classiAgrega=arrClasifAgrega;
				
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return classiAgrega;
	}
	
	private static void validarTaxones(String repositorio)throws Exception
	{
			
		java.io.File fPath = null;
		java.io.File[] subPaths = null;
		setDescomprimido(false);
		
		if(repositorio==null)
			{
				logger.error("El path repositorio de odes es nulo");
				throw new NullPointerException("path==null");
			}
		
		fPath = new java.io.File(repositorio);
		if(!fPath.isDirectory()) {
			logger.error("El path repositorio " + repositorio + " no es un directorio. Por favor chequee la ruta y vuelva a ejecutar el script");
		} else {
			
			subPaths = fPath.listFiles();			
			//logger.debug("subPaths.length " + subPaths.length);
			for(int i=0;i<subPaths.length;i++) {
				if(isDescomprimido())
					setDescomprimido(false);
				//logger.debug("subPaths[i].getName() " + subPaths[i].getName());
				//PREGUNTO PRIMERO SI ES ZIP
				es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
				if ( zipdao.esZip(subPaths[i].toString())) {
					String archivo = subPaths[i].toString();
					de.schlichtherle.io.File myFile = new de.schlichtherle.io.File(archivo);
					if ((myFile.isArchive() && myFile.isDirectory() && !myFile.isFile()
							&& myFile.exists() && myFile.length() == 0)) {
						logger.info("Es zip");
						//lo descomprimimos
						//es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
						String ruta_fin = archivo.substring(0, archivo.length()-4);
						File fileDesc = new File(ruta_fin);
						if(!fileDesc.exists()) {
							fileDesc.mkdirs();
						} else {
							//renombramos el fichero para evitar problemas
							ruta_fin= ruta_fin + "_aux";
							fileDesc = new File(ruta_fin);
							fileDesc.mkdirs();
						}
						zipdao.descomprimir(archivo, ruta_fin);
						logger.info("Descomprimiendo fichero zip");
						setDescomprimido(true);
						//ahora volveremos a llamar al validar licencia con esa ruta
						//tenemos que borrar el zip original
						boolean borrado = subPaths[i].delete();
						if (borrado)
							subPaths[i]=new File(ruta_fin);
					}
				}	
				//ES DIRECTORIO
				if(subPaths[i].isDirectory()) {
					// Comprobar si esta entrada tiene un imsmanifest.xml. En
					// caso contrario, internarse si recursivo=true
					String[] manifiestos = subPaths[i].list(new FilenameFilter() {
						public boolean accept(File path, String name) {
							return "imsmanifest.xml".equals(name);
						}
					});
					//logger.debug("manifiestos " + manifiestos);
					if(manifiestos!=null && manifiestos.length > 0 && isDescomprimido()) {
						//señal para reescribir el ode						
							setModificadoLom(true);
							File extraeSubmanifest = new File(subPaths[i], "imsmanifest.xml");
							//logger.debug("extraeSubmanifest!!! "+extraeSubmanifest);
							SCORM2004Dao scorm2004Dao = new SCORM2004Dao();
							scorm2004Dao.getBeanMapper();
							Manifest imsmanifest = scorm2004Dao.parsearODELazy(extraeSubmanifest);
							logger.info("el manifest ha sido parseado ");
							ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
							
							HashMap lomesHm = manAgrega.recuperarLomesMap();
							if (!lomesHm.isEmpty()){								
								Iterator iter = lomesHm.entrySet().iterator();
								while ( iter.hasNext()) {
									Map.Entry entry = (Map.Entry)iter.next();
									String id=entry.getKey().toString();
									Lom lomE = (Lom)entry.getValue();
									Lom lomOut = manAgrega.obtenerLom(id, null);
									if (lomOut!=null) {
										LomAgrega lomAgrega = new LomAgrega(lomOut);
										if (lomAgrega!=null){
											Lom lomMod =procesarLom(lomOut,lomAgrega, manAgrega);
											manAgrega.setLom(id, null, lomMod);
										}//lom no es vacio
									}
								}
							}
							//Generamos el archivo de informe
							//String salida= System.getProperty("fichSalida");
							//String rutaSal = salida + File.separator + "informeOdes";
							//File fileSal = new File(rutaSal);
							//if(!fileSal.exists())
							//	fileSal.mkdirs();
							//File fileOut = new File(rutaSal + File.separator + "informeOdesTaxon.csv" );
							
							
							//al final de todo escribimos el ode si se ha modificado algún lom
							if (!getModificadoLom()) {
								scorm2004Dao.escribirODE(imsmanifest, new File(subPaths[i] + "/imsmanifest.xml"));
								logger.info("Sobrescrito manifest");
								//System.out.println("Sobrescrito manifest ");
								getPw().println("Se ha modificado;" +  subPaths[i] + ";" + getDatos());
							} else {
								logger.info("El manifest no ha sido modificado");
								//System.out.println("El manifest no ha sido modificado");
								getPw().println("No se ha modificado;" + subPaths[i] + ";" + getDatos());
							}

							//el proceso final es comprimir el fichero y borrar la carpeta
							//es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
							String ruta_final = subPaths[i].toString();
							String[] zipFinal = ruta_final.split("\\"+File.separator);//ruta_final.split("\\\\");
							String nomFich = zipFinal[zipFinal.length-1];
							if (nomFich.endsWith("_aux")) {
								String frin= nomFich.substring(0, nomFich.length()-4);
								nomFich=frin;
							}
							nomFich = nomFich + ".zip";
							logger.info("el nombre del fichero sera: " + nomFich);
							String rFin= subPaths[i].getParent() + File.separator + nomFich;
							zipdao.comprimir(rFin, subPaths[i].toString());
							//borrar carpeta
							UtilesFicheros.eliminar(subPaths[i]);
							System.out.println("Borrando la carpeta " + subPaths[i]);
							logger.info("Borrando la carpeta " + subPaths[i]);
							
					} else {
						//si el manifest es null llamamos recursivamente
						validarTaxones(subPaths[i].getPath());
					}
					 
				} //Fin es directorio 
			  }
			}
		}
}

