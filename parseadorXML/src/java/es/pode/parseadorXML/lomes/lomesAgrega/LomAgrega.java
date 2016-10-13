/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.lomes.lomesAgrega;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.castor.Annotation;
import es.pode.parseadorXML.castor.Classification;
import es.pode.parseadorXML.castor.DescriptionUnbounded;
import es.pode.parseadorXML.castor.Educational;
import es.pode.parseadorXML.castor.General;
import es.pode.parseadorXML.castor.LanguageStringItem;
import es.pode.parseadorXML.castor.LifeCycle;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.MetaMetadata;
import es.pode.parseadorXML.castor.Relation;
import es.pode.parseadorXML.castor.Rights;
import es.pode.parseadorXML.castor.Technical;

/**
 * Class Lom.
 * 
 * @version $Revision$ $Date$
 */
public class LomAgrega implements java.io.Serializable {
	
	private Lom lom = null;
	public static final String FILE_NAME_PROPERTIES = "/parseadorXML.properties";
	private static Properties props = null;
	
	private static Logger logger = Logger.getLogger(LomAgrega.class);

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    //private java.util.List _items;
    

      //----------------/
     //- Constructors -/
    //----------------/

    public LomAgrega(Lom lom) {
        super();
        setLom(lom);
    }
    
//    public LomAgrega() {
//        super();
//        lom= new Lom();
//    }

      //-----------/
     //- Methods -/
    //-----------/
    
    /**
	 * @return the lom
	 */
	public Lom getLom() {
		return lom;
	}

	/**
	 * @param lom
	 *            the lom to set
	 */
	public void setLom(Lom lom) {
		this.lom = lom;
	}

    
    public GeneralAgrega getGeneralAgrega (
    ) throws ParseadorException {
    	
    	GeneralAgrega genAgrega= null;
    	
    	if (lom.getGeneral()!= null){
    		genAgrega= new GeneralAgrega(lom.getGeneral());
    	}
    	else{
    		General g= new General();
    		genAgrega= new GeneralAgrega(g);
    	}
    	
    	return genAgrega;
    }
    
//    public General getGeneral (
//    ) throws ParseadorException {
//    	
//    	return lom.getGeneral();
//    }
    	
    
    public void setGeneralAgrega (es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega genAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.setGeneral(genAgrega.getGeneral());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir General en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public LifeCycleAgrega getLifeCycleAgrega (
    ) throws ParseadorException {
    	
    	LifeCycleAgrega lifAgrega= null;
    	
    	if (lom.getLifeCycle()!= null){
    		lifAgrega= new LifeCycleAgrega(lom.getLifeCycle());
    	}
    	else{
    		LifeCycle l= new LifeCycle();
    		lifAgrega= new LifeCycleAgrega(l);
    	}
    	
    	return lifAgrega;
    }
    
    public void setLifeCycleAgrega (es.pode.parseadorXML.lomes.lomesAgrega.LifeCycleAgrega lifAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.setLifeCycle(lifAgrega.getLifeCycle());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir LifeCycle en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public MetaMetadataAgrega getMetaMetadataAgrega (
    ) throws ParseadorException {
    	
    	MetaMetadataAgrega mmdAgrega= null;
    	
    	if (lom.getMetaMetadata()!= null){
    		mmdAgrega= new MetaMetadataAgrega(lom.getMetaMetadata());
    	}
    	else{
    		MetaMetadata m= new MetaMetadata();
    		mmdAgrega= new MetaMetadataAgrega(m);
    	}
    	
    	return mmdAgrega;
    }
    
    public void setMetaMetadataAgrega (es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega mmdAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.setMetaMetadata(mmdAgrega.getMetaMetadata());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir LifeCycle en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public TechnicalAgrega getTechnicalAgrega (
    ) throws ParseadorException {
    	
    	TechnicalAgrega tecAgrega= null;
    	
    	if (lom.getTechnical()!= null){
    		tecAgrega= new TechnicalAgrega(lom.getTechnical());
    	}
    	else{
    		Technical t= new Technical();
    		tecAgrega= new TechnicalAgrega(t);
    	}
    	
    	return tecAgrega;
    }
    
    public void setTechnicalAgrega (es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega tecAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.setTechnical(tecAgrega.getTechnical());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir Technical en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public EducationalAgrega getEducationalAgrega (int index
    ) throws java.lang.Exception {
    	
    	EducationalAgrega eduAgrega= null;
    	
    	if (lom.getEducational()!= null){
    		try{
    			eduAgrega= new EducationalAgrega(lom.getEducational(index));
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger Educational en la posición: " +index;
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    	}
    	else{
    		Educational t= new Educational();
    		eduAgrega= new EducationalAgrega(t);
    	}
    	
    	return eduAgrega;
    }
    
    public ArrayList getEducationalsAgrega (
    ) throws java.lang.Exception {
    	
    	EducationalAgrega eduAgrega= null;
    	
    	ArrayList eduList= new ArrayList();
    	
    	if (lom.getEducational()!= null){
    		try{
    			for (int i= 0; i<lom.getEducational().length; i++){
        			eduAgrega= new EducationalAgrega(lom.getEducational(i));
        			eduList.add(eduAgrega);
        		}
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger las categorías Educational";
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    		
    	}
    	else{
    		Educational t= new Educational();
    		eduAgrega= new EducationalAgrega(t);
    		eduList.add(eduAgrega);
    	}
    	
    	return eduList;
    }
    

    public void setEducationalsAgrega (EducationalAgrega[] eduAgrega
    ) throws java.lang.Exception {
    	
    	ArrayList educationalList= new ArrayList();
    	
    	for (int i= 0; i<eduAgrega.length; i++){
    		educationalList.add(eduAgrega[i].getEducational());
    	}
    	
    	try{
    		lom.setEducational((Educational[])educationalList.toArray(new Educational[educationalList.size()]));
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir el array de Educationals en Lom";
		    logger.error(mensaje);
		    throw e;
    	}
    	
    }
    
    public void addEducationalAgrega (es.pode.parseadorXML.lomes.lomesAgrega.EducationalAgrega eduAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.addEducational(eduAgrega.getEducational());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir Educational en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public void removeEducationalAgrega (int index
    ) throws java.lang.Exception {
    	
    	try{
    		lom.removeEducationalAt(index);
        	
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible eliminar Educational en la posición: " +index;
		    logger.error(mensaje);
		    throw e;
    	}

    }
    
    public RightsAgrega getRightsAgrega (
    ) throws ParseadorException {
    	
    	RightsAgrega rigAgrega= null;
    	
    	if (lom.getRights()!= null){
    		rigAgrega= new RightsAgrega(lom.getRights());
    	}
    	else{
    		Rights r= new Rights();
    		rigAgrega= new RightsAgrega(r);
    	}
    	
    	return rigAgrega;
    }
    
    public void setRightsAgrega (es.pode.parseadorXML.lomes.lomesAgrega.RightsAgrega rigAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.setRights(rigAgrega.getRights());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir Rights en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public ClassificationAgrega getClassificationAgrega (int index
    ) throws java.lang.Exception {
    	
    	ClassificationAgrega claAgrega= null;
    	
    	if (lom.getClassification()!= null){
    		try{
    			claAgrega= new ClassificationAgrega(lom.getClassification(index));
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger Classification en la posición: " +index;
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    	}
    	else{
    		Classification c= new Classification();
    		claAgrega= new ClassificationAgrega(c);
    	}
    	
    	return claAgrega;
    }
    
    public ArrayList getClassificationsAgrega (
    ) throws java.lang.Exception {
    	
    	ClassificationAgrega claAgrega= null;
    	
    	ArrayList claList= new ArrayList();
    	
    	if (lom.getClassification()!= null){
    		try{
    			for (int i= 0; i<lom.getClassification().length; i++){
        			claAgrega= new ClassificationAgrega(lom.getClassification(i));
        			claList.add(claAgrega);
        		}
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger las categorías Classification";
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    		
    	}
    	else{
    		Classification c= new Classification();
    		claAgrega= new ClassificationAgrega(c);
    		claList.add(claAgrega);
    	}
    	
    	return claList;
    }
    
    
    public void setClassificationsAgrega (ClassificationAgrega[] clasAgrega
    ) throws java.lang.Exception {
    	
    	ArrayList classificationList= new ArrayList();
    	
    	for (int i= 0; i<clasAgrega.length; i++){
    		classificationList.add(clasAgrega[i].getClassification());
    	}
    	
    	try{
    		lom.setClassification((Classification[])classificationList.toArray(new Classification[classificationList.size()]));
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir el array de Classifications en Lom";
		    logger.error(mensaje);
		    throw e;
    	}
    	
    }

    
    public void addClassificationAgrega (es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega claAgrega
    ) throws java.lang.Exception {
    	
    	try{
    		lom.addClassification(claAgrega.getClassification());
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir Classification en Lom";
		    logger.error(mensaje);
		    throw e;
    	}	
    }
    
    public void removeClassificationAgrega (int index
    ) throws java.lang.Exception {
    	
    	try{
    		lom.removeClassificationAt(index);
        	
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible eliminar Classification en la posición: " +index;
		    logger.error(mensaje);
		    throw e;
    	}

    }
    
    public RelationAgrega getRelationAgrega (int index
    ) throws java.lang.Exception {
    	
    	RelationAgrega reAgrega= null;
    	
    	if (lom.getRelation()!= null){
    		try{
    			reAgrega= new RelationAgrega(lom.getRelation(index));
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger Relation en la posición: " +index;
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    	}
    	else{
    		Relation r= new Relation();
    		reAgrega= new RelationAgrega(r);
    	}
    	
    	return reAgrega;
    }
    
    public ArrayList getRelationsAgrega (
    ) throws java.lang.Exception {
    	
    	RelationAgrega reAgrega= null;
    	
    	ArrayList reList= new ArrayList();
    	
    	if (lom.getRelation()!= null){
    		try{
    			for (int i= 0; i<lom.getRelation().length; i++){
        			reAgrega= new RelationAgrega(lom.getRelation(i));
        			reList.add(reAgrega);
        		}
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger las categorías Relation";
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    		
    	}
    	else{
    		Relation c= new Relation();
    		reAgrega= new RelationAgrega(c);
    		reList.add(reAgrega);
    	}
    	
    	return reList;
    }
    
    public ArrayList getRelacionesAgrega (String tipo
    ) throws java.lang.Exception {
    	
    	ArrayList reList= new ArrayList();
    	String tipoAux = null;
    	
    	if (lom.getRelation()!= null){
    		try{
    			for (int i= 0; i<lom.getRelation().length; i++){
        			tipoAux = lom.getRelation(i).getGroupRelationRelation().getKind().getGroupKindKind().getComplexTypeKindVocabValue().getContent();
        			
        			//segun el tipo pasado como parametro
        			if (tipo.equals(tipoAux)){
        				
        				RelacionAgrega reAgrega= new RelacionAgrega();
        				//añadimos el tipo al array resultado
        				reAgrega.setTipo(tipoAux);
        				//para almacenar las descripciones
        				ArrayList lRecurso=new ArrayList();
        				
       				 	if(lom.getRelation(i).getGroupRelationRelation().getResource().getGroupResourceResource().getIdentifier()!=null){
       				 		
       				 		//añadimos el catalogo
       				 		reAgrega.setCatalogo(lom.getRelation(i).getGroupRelationRelation().getResource().getGroupResourceResource().getIdentifier().getGroupIdentifierIdentifier().getCatalog().getGroupCatalogCatalog().getContent());
       				 		//añadimos el identificador de entrada
       				 		reAgrega.setEntradaId(lom.getRelation(i).getGroupRelationRelation().getResource().getGroupResourceResource().getIdentifier().getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent());
       				 	}
       			  
       				 	DescriptionUnbounded[] descRecurso = lom.getRelation(i).getGroupRelationRelation().getResource().getGroupResourceResource().getDescriptionUnbounded();
       				 	if ((descRecurso!= null)&&(descRecurso.length>0)){
       				 		
       				 		//para cada descripción
       				 		for (int j= 0; j<descRecurso.length; j++){
       				 		
       				 		 //obtenemos un array de descripciones
       						 LanguageStringItem[] lsi = descRecurso[j].getGroupDescriptionUnboundedDescription().getLanguageStringItem();
       						 if((lsi!=null)&&(lsi.length>0)){
       							 
       							 for (int k= 0; k<lsi.length; k++){
       								 
       								 LangStringAgrega ldesc=new LangStringAgrega();
       								 
       								 ldesc.setIdioma(lsi[k].getString().getLanguage());
       								 ldesc.setValor(lsi[k].getString().getContent());
       								 lRecurso.add(ldesc);
       							 }
       						 }
       				 		}
       				 	}
       				 	
        				reAgrega.setDescripciones(lRecurso);
        			
        				reList.add(reAgrega);	
        			}
        			
        		}
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger las categorías Relation";
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    		
    	}
    	else{
    		RelacionAgrega c= new RelacionAgrega();
    		
    		reList.add(c);
    	}
    	
    	return reList;
    }
    
    
    public ArrayList getRelacionesSeBasaEnMEC (String catalogoMEC) throws java.lang.Exception {
    	
    	ArrayList relSeBasaEn= new ArrayList();
    	
    	try{
    		Properties properties = new Properties(); 
        	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
     	   	properties.load(is);
     	   	String estaBasadoEn= properties.getProperty("estaBasadoEn");
        	
        	ArrayList relaciones= new ArrayList();
        	relaciones= getRelacionesAgrega(estaBasadoEn);
        	
        	if ((relaciones!=null)&& (relaciones.size()>0)){
        		for (int i= 0; i<relaciones.size(); i++){
        			if ((((RelacionAgrega)relaciones.get(i)).getCatalogo()!=null)&& (((RelacionAgrega)relaciones.get(i)).getCatalogo().equals(catalogoMEC))){
        				relSeBasaEn.add(relaciones.get(i));
        			}
        		}
        	}
        	
        	
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible recoger las relaciones 'se basa en'";
    		logger.error(mensaje);
    		throw e;
    	}
    	
    	return relSeBasaEn;
    }
    

    public void setRelationsAgrega (RelationAgrega[] relaAgrega
    ) throws java.lang.Exception {
    	
    	ArrayList relationList= new ArrayList();
    	
    	for (int i= 0; i<relaAgrega.length; i++){
    		relationList.add(relaAgrega[i].getRelation());
    	}
    	
    	try{
    		lom.setRelation((Relation[])relationList.toArray(new Relation[relationList.size()]));
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir el array de Relations en Lom";
		    logger.error(mensaje);
		    throw e;
    	}
    	
    }
    
    public AnnotationAgrega getAnnotationAgrega (int index
    ) throws java.lang.Exception {
    	
    	AnnotationAgrega anAgrega= null;
    	
    	if (lom.getAnnotation()!= null){
    		try{
    			anAgrega= new AnnotationAgrega(lom.getAnnotation(index));
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger Anotación en la posición: " +index;
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    	}
    	else{
    		Annotation a= new Annotation();
    		anAgrega= new AnnotationAgrega(a);
    	}
    	
    	return anAgrega;
    }
    
    public ArrayList getAnnotationsAgrega (
    ) throws java.lang.Exception {
    	
    	AnnotationAgrega anAgrega= null;
    	
    	ArrayList anList= new ArrayList();
    	
    	if (lom.getAnnotation()!= null){
    		try{
    			for (int i= 0; i<lom.getAnnotation().length; i++){
        			anAgrega= new AnnotationAgrega(lom.getAnnotation(i));
        			anList.add(anAgrega);
        		}
    		} catch (Exception e){
    			String mensaje = "Error: No es posible recoger las categorías Annotation";
    		    logger.error(mensaje);
    		    throw e;
    		}
    		
    		
    	}
    	else{
    		Annotation c= new Annotation();
    		anAgrega= new AnnotationAgrega(c);
    		anList.add(anAgrega);
    	}
    	
    	return anList;
    }
    
    public void setAnnotationsAgrega (AnnotationAgrega[] annotaAgrega
    ) throws java.lang.Exception {
    	
    	ArrayList annotationList= new ArrayList();
    	
    	for (int i= 0; i<annotaAgrega.length; i++){
    		annotationList.add(annotaAgrega[i].getAnnotation());
    	}
    	
    	try{
    		lom.setAnnotation((Annotation[])annotationList.toArray(new Annotation[annotationList.size()]));
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible incluir el array de Annotations en Lom";
		    logger.error(mensaje);
		    throw e;
    	}
    	
    }
    
    
    public List<RelacionAgrega> getRelacionesEsVersionDeMEC (String catalogoMEC) throws java.lang.Exception {
    	
    	List<RelacionAgrega> relEsVersionDe= new ArrayList<RelacionAgrega>();
    	
    	try{
    		Properties properties = new Properties(); 
        	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
     	   	properties.load(is);
     	   	String estaBasadoEn= properties.getProperty("esVersionDe");
        	
     	   List<RelacionAgrega> relaciones= (List<RelacionAgrega>) getRelacionesAgrega(estaBasadoEn);
        	
        	if ((relaciones!=null)&& (relaciones.size()>0)){
        		for (int i= 0; i<relaciones.size(); i++){
        			if ((relaciones.get(i).getCatalogo()!=null)&& (relaciones.get(i).getCatalogo().equals(catalogoMEC))){
        				relEsVersionDe.add(relaciones.get(i));
        			}
        		}
        	}
    	}
    	catch (Exception e){
    		String mensaje = "Error: No es posible recoger las relaciones 'es version de'";
    		logger.error(mensaje);
    		throw e;
    	}
    	
    	return relEsVersionDe;
    }    
    
	
	/**
	 * Este metodo recoge los properties
	 * @param sKey
	 * 			La clave de la propiedad
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public static String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = LomAgrega.class.getResourceAsStream(FILE_NAME_PROPERTIES);
				Properties properties = new java.util.Properties();
				properties.load(fIsSpringProperties);
				props=properties;
			}
			sReturn = props.getProperty(sKey);
			if (logger.isDebugEnabled()) logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.error("Excepcion intentando obtener propiedad [" + sKey+ "] del fichero de propiedades del publicador[" + e.getCause() + "]");
		}
		// devolvemos la propiedad
		return sReturn;
	}
    
    
	/**
	 * Devuelve true si el lom que indica que el ODE al que pertenece es una version de otro ODE.
	 * En otras palabras, devolvera true si el ODE al que pertenece el lom es un ODE versionado.
	 * @return
	 * @throws Exception
	 */
	public boolean esOdeVersionado (String catalogoMEC) throws Exception {
		// 20130827 Evolutivo modificación ODEs con versión
		// Si el ODE a publicar tiene una relación esVersionDe con el texto igual al que se pone cuando se inserta automáticamente 
		// al proponer como una nueva versión de un ODE suyo previamente publicado.
		// En ese caso, se despublica y elimina la versión anterior del ODE y se publica la nueva versión con el mismo idMEC
				
		List<RelacionAgrega> relacionVersionDe = getRelacionesEsVersionDeMEC(catalogoMEC);

		if (relacionVersionDe==null || relacionVersionDe.size()==0) {
			return false;
		}
		
		for (Iterator<RelacionAgrega> iterator = relacionVersionDe.iterator(); iterator.hasNext();) {
			RelacionAgrega relacionAgrega =  iterator.next();
			if (relacionAgrega.getDescripciones()!=null && relacionAgrega.getDescripciones().size()>0)
			{
				for (Iterator<LangStringAgrega> iterator2 = relacionAgrega.getDescripciones().iterator(); iterator2.hasNext();) {
					LangStringAgrega descrip = iterator2.next();
					if (descrip.getValor().startsWith(getPropertyValue("descripcion_mec_version")))
						return true;
				}
			}
		}
		return false;
	}
    
	
	/**
	 * Crea en el lom una entrada que expresa una relacion del ODE a si mismo. Esta relacion expresa que el ODE
	 * es una version de si mismo, en otras palabras, es un ODE que esta siendo versionado.
	 * Recibe como barametro el string del parametro AgregaProperties.CATALOGO_MEC.
	 * @throws Exception
	 */
	public void crearRelacionVersionDe (String catalogoMEC) throws Exception {

		if(catalogoMEC==null || catalogoMEC.isEmpty()) {
			throw new Exception("No se ha recibido el nombre del catalogo mec");
		}
		
		String identificadorMECAgrega = getGeneralAgrega().obtenerIdentificadorFormatoMEC(catalogoMEC);
				
		// Creamos la nueva relación
		RelationAgrega relacionNueva= new RelationAgrega(new Relation());				
		RecursoAgrega recursoNuevo=new RecursoAgrega();
		
		// Creamos la descripción
		ArrayList<LangStringAgrega> descripciones=new ArrayList<LangStringAgrega>();
		LangStringAgrega lang=new LangStringAgrega();
		lang.setIdioma("es");
		lang.setValor(getPropertyValue("descripcion_mec_version")+" "+identificadorMECAgrega);
		descripciones.add(lang);
		recursoNuevo.setDescripciones(descripciones);
		
		// Creamos el identificador
		IdentificadorAgrega identificador=new IdentificadorAgrega();
		identificador.setCatalogo(catalogoMEC);
		identificador.setEntrada(identificadorMECAgrega);
		recursoNuevo.setIdentificador(identificador);
	
		// Asociamos el recurso a la relación
		relacionNueva.setRecursoAv(recursoNuevo);
		relacionNueva.setTipoAv(getPropertyValue("descripcion_version"));//es versión de
		lom.addRelation((relacionNueva).getRelation());
	}
	

	/**
	 * Elimina en el lom las entradas que expresan una relacion del ODE a si mismo. Esta relaciones expresan que el ODE
	 * es una version de si mismo, en otras palabras, es un ODE que ha sido versionado.
	 * @throws Exception
	 */
	public void eliminarRelacionVersionDe () throws Exception {
		Relation[] relaciones = lom.getRelation();
		
		lom.removeAllRelation();
		
		for (int i = 0; i < relaciones.length; i++) {
			Relation rel = relaciones[i];
			RelationAgrega relAg = new RelationAgrega(rel);				
			
			for (Iterator<LangStringAgrega> iterator2 = relAg.getRecursoAv().getDescripciones().iterator(); iterator2.hasNext();) {
				LangStringAgrega descrip = iterator2.next();
				if (!descrip.getValor().startsWith(getPropertyValue("descripcion_mec_version")))
						lom.addRelation(relaciones[i]);
			}
		}
	}
	
}
