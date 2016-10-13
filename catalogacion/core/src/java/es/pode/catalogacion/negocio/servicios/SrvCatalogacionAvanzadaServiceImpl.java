// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.catalogacion.negocio.servicios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.catalogacion.negocio.externos.TraduccionDao;
import es.pode.parseadorXML.castor.Annotation;
import es.pode.parseadorXML.castor.Classification;
import es.pode.parseadorXML.castor.Educational;
import es.pode.parseadorXML.castor.General;
import es.pode.parseadorXML.castor.GroupAnnotationAnnotation;
import es.pode.parseadorXML.castor.GroupClassificationClassification;
import es.pode.parseadorXML.castor.GroupEducationalEducational;
import es.pode.parseadorXML.castor.GroupGeneralGeneral;
import es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle;
import es.pode.parseadorXML.castor.GroupMetaMetadataMetaMetadata;
import es.pode.parseadorXML.castor.GroupRelationRelation;
import es.pode.parseadorXML.castor.GroupRightsRights;
import es.pode.parseadorXML.castor.GroupTechnicalTechnical;
import es.pode.parseadorXML.castor.LifeCycle;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.MetaMetadata;
import es.pode.parseadorXML.castor.Relation;
import es.pode.parseadorXML.castor.Rights;
import es.pode.parseadorXML.castor.Technical;


/**
 * @see es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaService
 */

public class SrvCatalogacionAvanzadaServiceImpl
    extends es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceBase
{

//	private  Logger logger = Logger.getLogger(this.getClass());
//	private LomESDao lomesDao=null;
	private HashMap<String, Lom> tablaHash=new HashMap<String, Lom>();
//	private MapperIF beanMapperAux = null;
//	private CatalogacionDozerDao catalogacionDao;
	
    

    /** Para obtener un lomAvanzado de un Lom
	 * 
	 * @param identificador Identificador del objeto Lom
	 * @param usuario Nombre del usuario
	 * @param idioma Idioma del objeto Lom
	 * @return LomAvanzadoVO Objeto donde se almacenan los datos de todos los campos.
	 * @throws Exception
	 * */
    protected es.pode.catalogacion.negocio.servicios.LomAvanzadoVO handleObtenerLomAvanzado(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma)
        throws java.lang.Exception
    {
//      //**********************************************************
//       String lom1="C:/metadato1_N21.xml";
//        File lomFile1 = new File(lom1);
//        Lom lomObj1 = this.getLomesDao().parsearLom(lomFile1);
//        tablaHash.put("idLOM1", lomObj1);
// //    **********************************************************
//    	logger.debug("ASC - OBTENERLOMAVANZADO IDENTIFICADOR " + identificador);
    	
        Lom lomObj=tablaHash.get(identificador);
        LomAvanzadoVO lomAvan = new LomAvanzadoVO();
        try{
			if(lomObj!=null){
        
		        //General
				if(lomObj.getGeneral()!=null){
					AvGeneralVO rec=(AvGeneralVO) this.getBeanMapper().map(lomObj.getGeneral().getGroupGeneralGeneral(), AvGeneralVO.class);
					lomAvan.setGeneral(rec);
				}
				
		        //Educationals
				if((lomObj.getEducational()!=null)&&(lomObj.getEducational().length>0)){
			        AvEducationalVO[] eduArray = new AvEducationalVO[lomObj.getEducationalCount()];
			        for(int i=0;i<lomObj.getEducationalCount();i++){
			        	AvEducationalVO eduav = (AvEducationalVO) this.getBeanMapper().map(lomObj.getEducational(i).getGroupEducationalEducational(), AvEducationalVO.class);
			        	
			        	eduArray[i] = eduav; 
			        }
			        lomAvan.setEducational(eduArray);
				}
		        
		        //LifeCycle
		        if(lomObj.getLifeCycle()!=null){
		        	AvLifeCycleVO life=(AvLifeCycleVO)this.getBeanMapper().map(lomObj.getLifeCycle().getGroupLifeCycleLifeCycle(), AvLifeCycleVO.class);
		        	lomAvan.setLifeCycle(life);
		        }	
		        
		        //Technical
		        if(lomObj.getTechnical()!=null){
			        AvTechnicalVO tech=(AvTechnicalVO)this.getBeanMapper().map(lomObj.getTechnical().getGroupTechnicalTechnical(), AvTechnicalVO.class);
			        lomAvan.setTechnical(tech);
		        }
		        
		        //MetaData
		        if(lomObj.getMetaMetadata()!=null){
		        	AvMetametadataVO meta=(AvMetametadataVO)this.getBeanMapper().map(lomObj.getMetaMetadata().getGroupMetaMetadataMetaMetadata(), AvMetametadataVO.class);
		        	lomAvan.setMetaMetadata(meta);
//		        	logger.debug("ASC - OBTENERLOMAVANZADO MetaData" + meta);
		        }
		        
		        //Derechos
		        if(lomObj.getRights()!=null){
			        AvRightsVO rights=(AvRightsVO)this.getBeanMapper().map(lomObj.getRights().getGroupRightsRights(), AvRightsVO.class);
			        lomAvan.setDerechos(rights);
		        }
		       
		        //Relaciones
		        if((lomObj.getRelation()!=null)&&(lomObj.getRelation().length>0)){
			        AvRelationVO[] relArray = new AvRelationVO[lomObj.getRelationCount()];
			        for(int i=0;i<lomObj.getRelationCount();i++){
			        	AvRelationVO relav = (AvRelationVO) this.getBeanMapper().map(lomObj.getRelation(i).getGroupRelationRelation(), AvRelationVO.class);
			        	relArray[i] = relav; 
			        }
			        lomAvan.setRelaciones(relArray);
//			        logger.debug("ASC - OBTENERLOMAVANZADO relaciones " + relArray.length);
		        }
		        
		        //Annotationes
		        if((lomObj.getAnnotation()!=null)&&(lomObj.getAnnotation().length>0)){
			        AvAnnotationVO[] anotaArray = new AvAnnotationVO[lomObj.getAnnotationCount()];
			        for(int i=0;i<lomObj.getAnnotationCount();i++){
			        	AvAnnotationVO anotaVo = (AvAnnotationVO) this.getBeanMapper().map(lomObj.getAnnotation(i).getGroupAnnotationAnnotation(), AvAnnotationVO.class);
			        	anotaArray[i] = anotaVo; 
			        }
			        lomAvan.setAnotaciones(anotaArray);
//			        logger.debug("ASC - OBTENERLOMAVANZADO anotaciones " + anotaArray.length );
		        }
		        
		        //Classificationes
		        if((lomObj.getClassification()!=null)&&(lomObj.getClassification().length>0)){
			        AvClassificationVO[] claArray = new AvClassificationVO[lomObj.getClassificationCount()];
			        for(int i=0;i<lomObj.getClassificationCount();i++){
			        	AvClassificationVO relav = (AvClassificationVO) this.getBeanMapper().map(lomObj.getClassification(i).getGroupClassificationClassification(), AvClassificationVO.class);
			        	claArray[i] = relav; 
			        }
			        lomAvan.setClasificaciones(claArray); 
//			        logger.debug("ASC - OBTENERLOMAVANZADO clasificaciones " + claArray.length);
		        }
		        
			}else{
				throw new  Exception("El Objeto LOM no esta en la Tabla Hash");
			}
		}catch(Exception e){
    		logger.error(e);
    		throw e;
		}

        return lomAvan;
    }
    

    /**
	 * Genera los datos de los nueve campos
	 * 
	 * @param identificador Identificador del objeto Lom
	 * @param usuario Nombre del usuario
	 * @param lomAvanzado Objeto para almacenar los datos de los nueve campos
	 * @param idioma Idioma de navegacion del usuario
	 * @return lomAvanzadoVO Devuelve el lomAvanzadoVO
	 * @throws Exception
	 */
    protected es.pode.catalogacion.negocio.servicios.LomAvanzadoVO handleGenerarMetadatos(
    		java.lang.String identificador, 
    		java.lang.String usuario, 
    		es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAvanzado, 
    		java.lang.String idioma)
        throws java.lang.Exception
    {
//    	logger.debug("ASC- DENTRO DE GENERARMETADATOS");
    	Lom lomObj=tablaHash.get(identificador);
   	 	LomAvanzadoVO lomVO=new LomAvanzadoVO();
   	 	try{
//   	 		logger.debug("ASC- DENTRO DE TRY GENERARMETADATOS");
   	 		if (lomObj != null) {

		        General general = new General();
		        
		        LifeCycle life=new LifeCycle();
		        MetaMetadata metaData=new MetaMetadata();
		        Technical tec=new Technical();
		        Rights derechos=new Rights();
		
		        //General
		        AvGeneralVO gen=lomAvanzado.getGeneral();
		        if(gen!=null){
		        	//hay que introducir identificador UUID o MEC
//	        		String catalogoPlat=this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA);
//		   
//	        		try{
//	        			IdentificadorVO[] liden= gen.getIdentificadores();
//			        	if ((liden != null)&&(liden.length>0)){  // tiene algun identificador
//			        		logger.debug("NRM - HAY IDENTIFICADORES");
//			        		boolean encontrado= false;
//			        		int i= 0;
//			        		while ((!encontrado)&&(i<liden.length)){  // se comprueba si el identificador ya esta en la lista de identificadores
//			        			if (identificador.equals(liden[i].getEntrada())){
//			        				encontrado= true;
//			        			}
//			        			else{
//			        				i++;
//			        			}
//			        		}
//			        		if (encontrado == false){  // si el identificador no esta en la lista, se añade en la primera posicion
//			        			logger.debug("NRM - NO ESTA EL IDENTIFICADOR EN LA LISTA");
//				        		ArrayList al= new ArrayList();
//				        		
//				        		IdentificadorVO idPlat= new IdentificadorVO();
//				        		idPlat.setCatalogo(catalogoPlat);
//				        		idPlat.setEntrada(identificador);
//				        		al.add(idPlat);
//				        		
//				        		for (int j= 0; j<liden.length; j++){
//				        			al.add(liden[j]);
//				        		}
//
//				        		IdentificadorVO[] identnews= (IdentificadorVO[]) al.toArray(new IdentificadorVO[al.size()]);
//				        		gen.setIdentificadores(identnews);
//				        		
//				        	}
//			        	}
//			        	else{ // si la lista de identificadores esta vacia, se crea una nueva con un unico identificador
//			        		logger.debug("NRM - NO HAY IDENTIFICADORES EN LA LISTA, SE AÑADE EL NUEVO");
//			        		
//			        		ArrayList al= new ArrayList();
//			        		IdentificadorVO id= new IdentificadorVO();
//			        		id.setCatalogo(catalogoPlat);
//			        		id.setEntrada(identificador);
//			        		al.add(id);
//			        		
//			        		IdentificadorVO[] identnews= (IdentificadorVO[]) al.toArray(new IdentificadorVO[al.size()]);
//			        		
//			        		gen.setIdentificadores(identnews);
//			        	}
//	        		} catch (Exception e){
//	        			logger.error(e);
//	        			throw e;
//	        		}
		        	
//		        	if ((gen!=null) && (gen.getIdentificadores()!=null) && (gen.getIdentificadores()[0]!=null))
//		        		logger.info("ASC - EL PRIMER IDENTIFICADOR ES " + gen.getIdentificadores()[0].getEntrada());
//		        	else
//		        		logger.info("ASC - NO TIENE IDENTIFICADORES ");
		        	
		        	general.setGroupGeneralGeneral((GroupGeneralGeneral) this.getBeanMapper().map(gen, GroupGeneralGeneral.class));
		        }
		        	
		        
		        else if ((lomAvanzado.getAnotaciones() == null || lomAvanzado.getAnotaciones().length == 0) 
		        		&& (lomAvanzado.getClasificaciones() == null || lomAvanzado.getClasificaciones().length == 0) 
		        		&& (lomAvanzado.getEducational() == null || lomAvanzado.getEducational().length == 0)
		        		&& (lomAvanzado.getRelaciones() == null || lomAvanzado.getRelaciones().length == 0)
		        		&& lomAvanzado.getDerechos() == null && lomAvanzado.getLifeCycle() == null
		        		&& lomAvanzado.getMetaMetadata() == null && lomAvanzado.getTechnical() == null)
		        	//si las categorias están todas vacías no añadimos el identificador
		        	general = null;
		        
		        else {
		        	//si alguna categoria tiene datos, añadimos el identificador
		        	
		        	gen = new AvGeneralVO();//general era null, entonces lo creamos y añadimos el identificador de plataforma
		        	//hay que introducir identificador UUID o MEC
//		       
//	        		String catalogoPlat=this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA);
//	        		
//	        		//creamos la lista de identificadores con el identificador de plataforma
//	        		ArrayList al= new ArrayList();
//	        		IdentificadorVO id= new IdentificadorVO();
//	        		id.setCatalogo(catalogoPlat);
//	        		id.setEntrada(identificador);
//	        		al.add(id);
//	        		
//	        		IdentificadorVO[] identnews= (IdentificadorVO[]) al.toArray(new IdentificadorVO[al.size()]);
//	        		
//	        		gen.setIdentificadores(identnews);
//		        	if ((gen!=null) && (gen.getIdentificadores()!=null) && (gen.getIdentificadores()[0]!=null))
//		        		logger.info("ASC - ELSE EL PRIMER IDENTIFICADOR ES " + gen.getIdentificadores()[0].getEntrada());
//		        	else
//		        		logger.info("ASC - ELSE NO TIENE IDENTIFICADORES ");
		        	general.setGroupGeneralGeneral((GroupGeneralGeneral) this.getBeanMapper().map(gen, GroupGeneralGeneral.class));
		        	}
		  
		        
		        lomObj.setGeneral(general);
//		        logger.debug("ASC - GENERARMETADATOS GENERAL" + general );
		        
		        //Educational
		        Educational[] education=new Educational[0];
		        AvEducationalVO[] eduav =lomAvanzado.getEducational();
		        GroupEducationalEducational edg = new GroupEducationalEducational();
		        ArrayList<Educational> lEd=new ArrayList<Educational>();
		        if((eduav!=null)&&(eduav.length>0)){
		       	 for(int i=0;i<eduav.length;i++){
		       		 Educational ed = new Educational();
		       		 edg =(GroupEducationalEducational) this.getBeanMapper().map(eduav[i], GroupEducationalEducational.class);
		       		 ed.setGroupEducationalEducational(edg);
		       		 lEd.add(ed);
		       	 }
		       	 education=lEd.toArray(new Educational[lEd.size()]);
		        } 
		        lomObj.setEducational(education); //inicializado a null arriba
//		        logger.debug("ASC - GENERARMETADATOS EDUCATIONAL " + Arrays.toString(education));
		        
		        //LifeCycle
		        AvLifeCycleVO lif=lomAvanzado.getLifeCycle();
		        if(lif!=null)
		        	life.setGroupLifeCycleLifeCycle((GroupLifeCycleLifeCycle)this.getBeanMapper().map(lif, GroupLifeCycleLifeCycle.class));
		        else
		        	life = null;
		        lomObj.setLifeCycle(life);
//		        logger.debug("ASC - GENERARMETADATOS LIFECYCLE " + life);
		        
		        //MetaMetadata
		        AvMetametadataVO meta=lomAvanzado.getMetaMetadata();
		        if(meta !=null){
		        	
		        	String esquemaAux=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
	        		String esquema= esquemaAux.toLowerCase();
		        	
	        		try{
	        			EsquemaDeMetadatosVO[] edm= meta.getEsquemas();
	        			if ((edm!=null)&&(edm.length>0)){
//	        				logger.debug("NRM - HAY ESQUEMAS DE METADATOS");
			        		boolean encontrado= false;
			        		int i= 0;
			        		while ((!encontrado)&&(i<edm.length)){  // se comprueba si LOM-ESV1.0 ya esta en la lista de esquemas
			        			String esq = edm[i].getTexto().toLowerCase().trim();
        						esq= esq.replaceAll("\\.", "");
        						esq=esq.replaceAll(" ", "");
        						esquema = esquema.replaceAll("\\.", "");
        						esquema = esquema.replaceAll(" ", "");
        						esquema = esquema.toLowerCase().trim();
			        			if (esquema.equals(esq)){
			        				encontrado= true;
			        			}
			        			else{
			        				i++;
			        			}
			        		}
			        		if (encontrado == false){
//			        			logger.debug("NRM - NO ESTA EL ESQUEMA DE METADATOS LOM-ESV1.0");
				        		ArrayList<EsquemaDeMetadatosVO> al= new ArrayList<EsquemaDeMetadatosVO>();
				        		
				        		EsquemaDeMetadatosVO eq= new EsquemaDeMetadatosVO();
				        		eq.setTexto(esquemaAux);
				        		
				        		al.add(eq);
				        		
				        		for (int j= 0; j<edm.length; j++){
				        			al.add(edm[j]);
				        		}

				        		EsquemaDeMetadatosVO[] eqnews= al.toArray(new EsquemaDeMetadatosVO[al.size()]);
				        		meta.setEsquemas(eqnews);
		        			}
			        	}else{ // si la lista de esquemas esta vacia, se crea una nueva con el LOM-ESV1.0
//			        		logger.debug("NRM - NO HAY ESQUEMAS DE METADATOS EN LA LISTA, SE AÑADE EL LOM-ESV1.0");
			        		
			        		ArrayList<EsquemaDeMetadatosVO> al= new ArrayList<EsquemaDeMetadatosVO>();
			        		EsquemaDeMetadatosVO eq= new EsquemaDeMetadatosVO();
			        		eq.setTexto(esquemaAux);

			        		al.add(eq);
			        		
			        		EsquemaDeMetadatosVO[] eqnews= al.toArray(new EsquemaDeMetadatosVO[al.size()]);
			        		meta.setEsquemas(eqnews);
			        	}
	        			
	        		} catch (Exception e){
	        			logger.error(e);
	        			throw e;
	        		}

		        	metaData.setGroupMetaMetadataMetaMetadata((GroupMetaMetadataMetaMetadata)this.getBeanMapper().map(meta, GroupMetaMetadataMetaMetadata.class));
		        }
		        	
		        else if ((lomAvanzado.getAnotaciones() == null || lomAvanzado.getAnotaciones().length == 0) 
		        		&& (lomAvanzado.getClasificaciones() == null || lomAvanzado.getClasificaciones().length == 0) 
		        		&& (lomAvanzado.getEducational() == null || lomAvanzado.getEducational().length == 0)
		        		&& (lomAvanzado.getRelaciones() == null || lomAvanzado.getRelaciones().length == 0)
		        		&& lomAvanzado.getDerechos() == null && lomAvanzado.getLifeCycle() == null
		        		&& lomAvanzado.getGeneral() == null && lomAvanzado.getTechnical() == null)
		        	//si las categorias están todas vacías no añadimos el identificador
		        	metaData = null;
		        
		        else {
//		        	logger.debug("AOV - META-METADATA ES VACIO, SE CREA Y SE AÑADE ESQUEMA DE METADATOS");
		        	
		        	meta = new AvMetametadataVO();//metametadata era null, entonces lo creamos y añadimos el esquema de metadatos
		
	            	String esquema=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
	        			        		
	        		//creamos la lista de esquemas de metadatos con el esquema de la plataforma
	        		ArrayList<EsquemaDeMetadatosVO> al= new ArrayList<EsquemaDeMetadatosVO>();
	        		EsquemaDeMetadatosVO eq= new EsquemaDeMetadatosVO();
	        		eq.setTexto(esquema);

	        		al.add(eq);
	        		
	        		EsquemaDeMetadatosVO[] eqnews= al.toArray(new EsquemaDeMetadatosVO[al.size()]);
	        		meta.setEsquemas(eqnews);	
	        		
	        		metaData.setGroupMetaMetadataMetaMetadata((GroupMetaMetadataMetaMetadata)this.getBeanMapper().map(meta, GroupMetaMetadataMetaMetadata.class));
		        }
		        lomObj.setMetaMetadata(metaData);
//		        logger.debug("ASC - GENERARMETADATOS METAMETADATA" + metaData);
		        
		        //Technical
		        AvTechnicalVO tech=lomAvanzado.getTechnical();
		        if(tech!=null)
		        	tec.setGroupTechnicalTechnical((GroupTechnicalTechnical)this.getBeanMapper().map(tech, GroupTechnicalTechnical.class));
		        else 
		        	tec = null;
		        lomObj.setTechnical(tec);
//		        logger.debug("ASC - GENERARMETADATOS TECHNICAL " + tec);
		        
		        //Rights
		        AvRightsVO right=lomAvanzado.getDerechos();
		        if (right!=null)
		        	derechos.setGroupRightsRights((GroupRightsRights)this.getBeanMapper().map(right, GroupRightsRights.class));
		        else
		        	derechos =null;
		        lomObj.setRights(derechos);
//		        logger.debug("ASC - GENERARMETADATOS RIGHTS " + derechos);
		        
		        //Relations
		        Relation[] relacion=new Relation[0];
		        AvRelationVO[] relav =lomAvanzado.getRelaciones();
		        GroupRelationRelation grr = new GroupRelationRelation();
		        ArrayList<Relation> lRe=new ArrayList<Relation>();
		        
		        if((relav!=null)&&(relav.length>0)){
		          	 for(int i=0;i<relav.length;i++){
		          		 Relation relation=new Relation();
		          		 grr =(GroupRelationRelation) this.getBeanMapper().map(relav[i], GroupRelationRelation.class);
		          		 relation.setGroupRelationRelation(grr);
		          		 lRe.add(relation);
		          	 }
		          	relacion=lRe.toArray(new Relation[lRe.size()]);
		        }
		        lomObj.setRelation(relacion);//inicializado a null arriba
//		        logger.debug("ASC - GENERARMETADATOS RELACION " + Arrays.toString(relacion));
		        
		        //annotations
		        Annotation[] anotacion=new Annotation[0];
//		        logger.debug("ASC- GUARDARMETADADOS ANOTATION INICIALIZADO LONG "+  anotacion.length);
		        AvAnnotationVO[] anav =lomAvanzado.getAnotaciones();
		        GroupAnnotationAnnotation gaa = new GroupAnnotationAnnotation();
		        ArrayList<Annotation> lAn=new ArrayList<Annotation>();
		        
		        if((anav!=null)&&(anav.length>0)){
		          	 for(int i=0;i<anav.length;i++){
		          		 Annotation annotation=new Annotation();
		          		 gaa =(GroupAnnotationAnnotation) this.getBeanMapper().map(anav[i], GroupAnnotationAnnotation.class);
		          		 annotation.setGroupAnnotationAnnotation(gaa);
		          		 lAn.add(annotation);
		          	 }
		          	anotacion=lAn.toArray(new Annotation[lAn.size()]);
//		          	logger.debug("ASC- GUARDARMETADADOS ANOTATION DESPUES DE METER DATOS LONG "+  anotacion.length);
		        }
		        lomObj.setAnnotation(anotacion);
//		        logger.debug("ASC -GENERARMETADATOS ANOTACION " + Arrays.toString(anotacion)); //inicializado a null arriba
		        
		        //Classifications
		        Classification[] clasificacion=new Classification[0];
		        AvClassificationVO[] claav =lomAvanzado.getClasificaciones();
		        GroupClassificationClassification gcc = new GroupClassificationClassification();
		        ArrayList<Classification> lCla=new ArrayList<Classification>();
		        
		        if((claav!=null)&&(claav.length>0)){
		          	 for(int i=0;i<claav.length;i++){
		          		 Classification classification=new Classification();
		          		 gcc =(GroupClassificationClassification) this.getBeanMapper().map(claav[i], GroupClassificationClassification.class);
		          		 classification.setGroupClassificationClassification(gcc);
		          		 lCla.add(classification);
		          	 }
		          	clasificacion=lCla.toArray(new Classification[lCla.size()]);
		         }
		        lomObj.setClassification(clasificacion);
//		        logger.debug("ASC -GENERARMETADATOS CLASIFICACION " + Arrays.toString(clasificacion));//inicializado a null arriba

		        tablaHash.put(identificador, lomObj);
		        if( logger.isDebugEnabled() )
		            logger.debug("Añadido el lomObj después de generar los metadatos" );
		        
	   	 	}else{
				
	//			 el objeto lom no esta en la tabla hash y da un error
				throw new  Exception("El Objeto LOM no esta en la Tabla Hash");
			}
	
		}catch(Exception e){
			
			logger.error(e);
			throw e;
		}
	return lomVO;
  
    }

    /** Para la carga de los objetos LOM
	 * 
	 * @param identificador Identificador del objeto Lom
	 * @param usuario Nombre del usuario
	 * @param lom Un dataHandler que contiene los datos de lomes
	 * @return identificador Identificador del objeto Lom
	 * @throws Exception
	 * */
    
    protected java.lang.String handleCargarObjLom(java.lang.String identificador, java.lang.String usuario, javax.activation.DataHandler lom)
        throws java.lang.Exception
    {
    	if (lom.getContentType().equals("application/x-java-serialized-object"))
		{
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lom.writeTo(baos);
			Object tmp = deserializar(baos.toByteArray());
			Lom lomObj = null;
			if (tmp instanceof Lom)
				lomObj = (Lom) tmp;
			else {
				Logger.getLogger(this.getClass()).error(
						"El objeto recibido no es un LOM_ES");
				throw new Exception("El objeto recibido no es un LOM_ES");
			}
			if (tablaHash==null){
				tablaHash=new HashMap<String, Lom>();
			}
			tablaHash.put(identificador,lomObj);
			
			
			if( logger.isDebugEnabled() )

                logger.debug("Cargado objeto Lom con identificador " +identificador );

		}
		else
			throw new Exception ("No existe objeto para cargar");


	return identificador;
        
        
    }

    /**Elimina los objetos LOM 
	 * 
	 * @param ids Array de identificadores de los objetos Lom a eliminar
	 * @throws Exception
	 * */
    protected void handleEliminarObjLoms(java.lang.String[] ids)
        throws java.lang.Exception
    {
    	for (int i=0;i<ids.length;i++){
			
			//Borramos los Lom con los identificadores que nos pasan
			tablaHash.remove(ids[i]);
			if( logger.isDebugEnabled() )

                logger.debug("Eliminado el objeto lom con identificador "+ids[i] );
			
		}
    }

    public void escribe(LomAvanzadoVO lomAvan){

    }
    /**
	 * Obtiene los objetos Lom correspondientes a los identificadores del array de identificadores
	 * 
	 * @param ids Array de identificadores de objetos lom
	 * @return idLomVO[] Array de pares (identificador, objeto Lom)
	 * @throws Exception
	 */
    protected es.pode.catalogacion.negocio.servicios.IdLomVO[] handleObtenerObjLoms(java.lang.String[] ids)
        throws java.lang.Exception
    {
    	List<IdLomVO> idLomVO=new ArrayList<IdLomVO>();
		
		for(int i=0;i<ids.length;i++){
			//Recogemos el lom con el identificador ids[i] 
			Lom lomObj=tablaHash.get(ids[i]);
			//Si el lomObj es distinto de null lo añadimos al LomHandler, si no, no hacemos nada
			if(lomObj!=null){
				
				IdLomVO idLom=new IdLomVO();
				DataHandler dh=null;
				
				byte[] bytes = serializar(lomObj);
				ByteArrayDataSource bads = new ByteArrayDataSource(bytes,
						"application/x-java-serialized-object");
				dh = new DataHandler(bads);
				idLom.setId(ids[i]);
				idLom.setLomHandler(dh);
				
				//Recogemos el id y el lomHandler en el idLomVO
				idLomVO.add(idLom);
				if( logger.isDebugEnabled() )

                    logger.debug("Recogido objeto lom con identificador "+ids[i] );
			}
		}

		return idLomVO.toArray(new IdLomVO[ids.length]);
    }

    
    public HashMap<String, Lom> getTablaHash() {
		return tablaHash;
	}

	public void setTablaHash(HashMap<String, Lom> tablaHash) {
		this.tablaHash = tablaHash;
	}
	
//	public CatalogacionDozerDao getCatalogacionDao() {
//		return catalogacionDao;
//	}
//
//	public void setCatalogacionDao(CatalogacionDozerDao catalogacionDao) {
//		this.catalogacionDao = catalogacionDao;
//	}
//
//	public LomESDao getLomesDao() {
//		return lomesDao;
//	}
//
//	public void setLomesDao(LomESDao lomesDao) {
//		this.lomesDao = lomesDao;
//	}
	
	/**
	 * Deserializa un array de bytes  en un objeto 
	 * 
	 * @param entrada Array de bytes
	 * @return salida Un object
	 * @throws IOException
	 */
	
	private Object deserializar(byte[] entrada) throws IOException {
		ByteArrayInputStream bs = new ByteArrayInputStream(entrada);
		ObjectInputStream is = new ObjectInputStream(bs);
		Object salida = null;
		try {
			salida = is.readObject();
		} catch (IOException e) {
			logger.error("Ha fallado al deserializar el objeto "+ e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		is.close();
		return salida;
	}
	
	/**
	 * Serializa un objeto en un array de bytes
	 * 
	 * @param entrada Un objeto para serializar
	 * @return bytes Un array de bytes que contiene el objeto serializado
	 * @throws IOException
	 */
	
	private byte[] serializar(Object entrada) throws IOException {

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os;
		byte[] bytes = null;
		os = new ObjectOutputStream(bs);
		os.writeObject(entrada);
		os.close();
		bytes = bs.toByteArray();

		return bytes;
	}

	
	
	
	/**
	 * Permite exportar un fichero xml de catalogación LOM-ES avanzado encapsulado 
	 * en un DataHandler.
	 * 
	 * @param identificador  Identificador del ode que se quiere catalogar
	 * @param usuario 		 nombre de usuario 
	 * @param lomAvanzado    LomAvanzado que representa la catalogación que se intenta exportar
	 * @param idioma 		 Idioma en que se intenta exportar la catalogación
	 * @return DataHandler 	 DataHandler que encapsula el fichero xml en que se exporta la catalogación
	 * @throws Exception    
	 */
	protected DataHandler handleExportarLomes(
			String identificador, 
			String usuario, 
			LomAvanzadoVO lomAvanzado, 
			String idioma) 
	throws Exception 
	{
    	String ficheroProperties="/catalogacion.properties";
    	Properties fproperties=new Properties();
    	fproperties.load(this.getClass().getResourceAsStream(ficheroProperties));

		//genero el lom 
		Lom lom= new Lom();
		try{
	        General general = new General();
	        LifeCycle life=new LifeCycle();
	        MetaMetadata metaData=new MetaMetadata();
	        Technical tec=new Technical();
	        Rights derechos=new Rights();
	        
//General
	        AvGeneralVO gen=lomAvanzado.getGeneral();
	        if(gen!=null){
	        	//hay que introducir identificador UUID o MEC
        		
        		String catalogoPlat=this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA);
	   
        		try{
        			IdentificadorVO[] liden= gen.getIdentificadores();
		        	if ((liden != null)&&(liden.length>0)){  // tiene algun identificador
		        		logger.debug("PLLecf - HAY IDENTIFICADORES");
		        		boolean encontrado= false;
		        		int i= 0;
		        		while ((!encontrado)&&(i<liden.length)){  // se comprueba si el identificador ya esta en la lista de identificadores
		        			if (identificador.equals(liden[i].getEntrada())){
		        				encontrado= true;
		        			}
		        			else{
		        				i++;
		        			}
		        		}
		        		if (encontrado == false){  // si el identificador no esta en la lista, se añade en la primera posicion
		        			logger.debug("PLLecf - NO ESTA EL IDENTIFICADOR EN LA LISTA");
			        		ArrayList<IdentificadorVO> al= new ArrayList<IdentificadorVO>();
			        		
			        		IdentificadorVO idPlat= new IdentificadorVO();
			        		idPlat.setCatalogo(catalogoPlat);
			        		idPlat.setEntrada(identificador);
			        		al.add(idPlat);
			        		
			        		for (int j= 0; j<liden.length; j++){
			        			al.add(liden[j]);
			        		}

			        		IdentificadorVO[] identnews= al.toArray(new IdentificadorVO[al.size()]);
			        		gen.setIdentificadores(identnews);
			        		
			        	}
		        	}
		        	else{ // si la lista de identificadores esta vacia, se crea una nueva con un unico identificador
		        		logger.debug("PLLecf - NO HAY IDENTIFICADORES EN LA LISTA, SE AÑADE EL NUEVO");
		        		
		        		ArrayList<IdentificadorVO> al= new ArrayList<IdentificadorVO>();
		        		IdentificadorVO id= new IdentificadorVO();
		        		id.setCatalogo(catalogoPlat);
		        		id.setEntrada(identificador);
		        		al.add(id);
		        		
		        		IdentificadorVO[] identnews= al.toArray(new IdentificadorVO[al.size()]);
		        		
		        		gen.setIdentificadores(identnews);
		        	}
        		} catch (Exception e){
        			logger.error(e);
        			throw e;
        		}
	        	

	        	general.setGroupGeneralGeneral((GroupGeneralGeneral) this.getBeanMapper().map(gen, GroupGeneralGeneral.class));
	        }
	        	
	        
	        else
	        	general = null;
	        lom.setGeneral(general);
	        logger.debug("PLLecf - GENERARMETADATOS GENERAL" + general );
	        

//Educational
	        Educational[] education=new Educational[0];
	        AvEducationalVO[] eduav =lomAvanzado.getEducational();
	        GroupEducationalEducational edg = new GroupEducationalEducational();
	        ArrayList<Educational> lEd=new ArrayList<Educational>();
	        if((eduav!=null)&&(eduav.length>0)){
	       	 for(int i=0;i<eduav.length;i++){
	       		 Educational ed = new Educational();
	       		 edg =(GroupEducationalEducational) this.getBeanMapper().map(eduav[i], GroupEducationalEducational.class);
	       		 ed.setGroupEducationalEducational(edg);
	       		 lEd.add(ed);
	       	 }
	       	 education=lEd.toArray(new Educational[lEd.size()]);
	        } 
	        lom.setEducational(education); //inicializado a null arriba
	        logger.debug("PLLecf - GENERARMETADATOS EDUCATIONAL " + Arrays.toString(education));
	        
//LifeCycle
	        AvLifeCycleVO lif=lomAvanzado.getLifeCycle();
	        if(lif!=null)
	        	life.setGroupLifeCycleLifeCycle((GroupLifeCycleLifeCycle)this.getBeanMapper().map(lif, GroupLifeCycleLifeCycle.class));
	        else
	        	life = null;
	        lom.setLifeCycle(life);
	        logger.debug("PLLecf - GENERARMETADATOS LIFECYCLE " + life);
	        
//MetaMetadata
	        AvMetametadataVO meta=lomAvanzado.getMetaMetadata();
	        if(meta !=null){
	        	
	        	String esquemaAux=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
        		String esquema= esquemaAux.toLowerCase();
	        	
        		try{
        			EsquemaDeMetadatosVO[] edm= meta.getEsquemas();
        			if ((edm!=null)&&(edm.length>0)){
        				logger.debug("PLLecf - HAY ESQUEMAS DE METADATOS");
		        		boolean encontrado= false;
		        		int i= 0;
		        		while ((!encontrado)&&(i<edm.length)){  // se comprueba si LOM-ESV1.0 ya esta en la lista de esquemas
		        			String esq = edm[i].getTexto().toLowerCase().trim();
    						esq= esq.replaceAll("\\.", "");
    						esq=esq.replaceAll(" ", "");
    						esquema = esquema.replaceAll("\\.", "");
    						esquema = esquema.replaceAll(" ", "");
    						esquema = esquema.toLowerCase().trim();
		        			if (esquema.equals(esq)){
		        				encontrado= true;
		        			}
		        			else{
		        				i++;
		        			}
		        		}
		        		if (encontrado == false){
		        			logger.debug("PLLecf - NO ESTA EL ESQUEMA DE METADATOS LOM-ESV1.0");
			        		ArrayList<EsquemaDeMetadatosVO> al= new ArrayList<EsquemaDeMetadatosVO>();
			        		
			        		EsquemaDeMetadatosVO eq= new EsquemaDeMetadatosVO();
			        		eq.setTexto(esquemaAux);
			        		
			        		al.add(eq);
			        		
			        		for (int j= 0; j<edm.length; j++){
			        			al.add(edm[j]);
			        		}

			        		EsquemaDeMetadatosVO[] eqnews= al.toArray(new EsquemaDeMetadatosVO[al.size()]);
			        		meta.setEsquemas(eqnews);
	        			}
		        	}else{ // si la lista de esquemas esta vacia, se crea una nueva con el LOM-ESV1.0
		        		logger.debug("PLLecf - NO HAY ESQUEMAS DE METADATOS EN LA LISTA, SE AÑADE EL LOM-ESV1.0");
		        		
		        		ArrayList<EsquemaDeMetadatosVO> al= new ArrayList<EsquemaDeMetadatosVO>();
		        		EsquemaDeMetadatosVO eq= new EsquemaDeMetadatosVO();
		        		eq.setTexto(esquemaAux);

		        		al.add(eq);
		        		
		        		EsquemaDeMetadatosVO[] eqnews= al.toArray(new EsquemaDeMetadatosVO[al.size()]);
		        		meta.setEsquemas(eqnews);
		        	}
        			
        		} catch (Exception e){
        			logger.error(e);
        			throw e;
        		}

	        	metaData.setGroupMetaMetadataMetaMetadata((GroupMetaMetadataMetaMetadata)this.getBeanMapper().map(meta, GroupMetaMetadataMetaMetadata.class));
	        }else
	        	metaData = null;
	        lom.setMetaMetadata(metaData);
	        logger.debug("PLLecf - GENERARMETADATOS METAMETADATA" + metaData);
	        
//Technical
	        AvTechnicalVO tech=lomAvanzado.getTechnical();
	        if(tech!=null)
	        	tec.setGroupTechnicalTechnical((GroupTechnicalTechnical)this.getBeanMapper().map(tech, GroupTechnicalTechnical.class));
	        else 
	        	tec = null;
	        lom.setTechnical(tec);
	        logger.debug("PLLecf - GENERARMETADATOS TECHNICAL " + tec);
	        
//Rights
	        AvRightsVO right=lomAvanzado.getDerechos();
	        if (right!=null)
	        	derechos.setGroupRightsRights((GroupRightsRights)this.getBeanMapper().map(right, GroupRightsRights.class));
	        else
	        	derechos =null;
	        lom.setRights(derechos);
	        logger.debug("PLLecf - GENERARMETADATOS RIGHTS " + derechos);
	        
//Relations
	        Relation[] relacion=new Relation[0];
	        AvRelationVO[] relav =lomAvanzado.getRelaciones();
	        GroupRelationRelation grr = new GroupRelationRelation();
	        ArrayList<Relation> lRe=new ArrayList<Relation>();
	        
	        if((relav!=null)&&(relav.length>0)){
	          	 for(int i=0;i<relav.length;i++){
	          		 Relation relation=new Relation();
	          		 grr =(GroupRelationRelation) this.getBeanMapper().map(relav[i], GroupRelationRelation.class);
	          		 relation.setGroupRelationRelation(grr);
	          		 lRe.add(relation);
	          	 }
	          	relacion=lRe.toArray(new Relation[lRe.size()]);
	        }
	        lom.setRelation(relacion);//inicializado a null arriba
	        logger.debug("PLLecf - GENERARMETADATOS RELACION " + Arrays.toString(relacion));
	        
//annotations
	        Annotation[] anotacion=new Annotation[0];
	        logger.debug("PLLecf- GUARDARMETADADOS ANOTATION INICIALIZADO LONG "+  anotacion.length);
	        AvAnnotationVO[] anav =lomAvanzado.getAnotaciones();
	        GroupAnnotationAnnotation gaa = new GroupAnnotationAnnotation();
	        ArrayList<Annotation> lAn=new ArrayList<Annotation>();
	        
	        if((anav!=null)&&(anav.length>0)){
	          	 for(int i=0;i<anav.length;i++){
	          		 Annotation annotation=new Annotation();
	          		 gaa =(GroupAnnotationAnnotation) this.getBeanMapper().map(anav[i], GroupAnnotationAnnotation.class);
	          		 annotation.setGroupAnnotationAnnotation(gaa);
	          		 lAn.add(annotation);
	          	 }
	          	anotacion=lAn.toArray(new Annotation[lAn.size()]);
	          	logger.debug("PLLecf- GUARDARMETADADOS ANOTATION DESPUES DE METER DATOS LONG "+  anotacion.length);
	        }
	        lom.setAnnotation(anotacion);
	        logger.debug("PLLecf -GENERARMETADATOS ANOTACION " + Arrays.toString(anotacion)); //inicializado a null arriba
	        
//Classifications
	        Classification[] clasificacion=new Classification[0];
	        AvClassificationVO[] claav =lomAvanzado.getClasificaciones();
	        GroupClassificationClassification gcc = new GroupClassificationClassification();
	        ArrayList<Classification> lCla=new ArrayList<Classification>();
	        
	        if((claav!=null)&&(claav.length>0)){
	          	 for(int i=0;i<claav.length;i++){
	          		 Classification classification=new Classification();
	          		 gcc =(GroupClassificationClassification) this.getBeanMapper().map(claav[i], GroupClassificationClassification.class);
	          		 classification.setGroupClassificationClassification(gcc);
	          		 lCla.add(classification);
	          	 }
	          	clasificacion=lCla.toArray(new Classification[lCla.size()]);
	         }
	        lom.setClassification(clasificacion);
	        logger.debug("PLLecf -GENERARMETADATOS CLASIFICACION " + Arrays.toString(clasificacion));//inicializado a null arriba
	        
		}catch (Exception e) {
			logger.error("PLL - error en SrvCatalogacionAvanzadaService.ExportarLomes: error al generar objeto Lom desde un LomAvanzadoVO " + e);
			throw e;
		}

		
		
		//declaro el data handler
		DataHandler dh=null;
		try {
			//creo un writer para escribir los metadatos
			StringWriter sw= new StringWriter();
			//completo el writer
			this.getLomesDao().escribirLom(lom, sw);
//			lomesDao.escribirLom(lom, sw);
			//creo el datahandler con el string del writer
			dh = new DataHandler(sw.toString(),"text/xml;charset=utf-8");
			
		} catch (Exception e) {
			logger.error("PLL - error en SrvCatalogacionAvanzadaService.ExportarLomes: error al generar el contenido a exportar " + e);
			throw e;
		}
		//regreso el datahandler
		return dh;
	}

	
	
	
	/**
	 * Permite obtener un objeto LomAvanzadoVO desde un fichero xml.
	 * 
	 * @param  ficheroLomes 	DataHandler que encapsula el fichero xml en que se exporta la catalogación
	 * @return LomAvanzadoVO 	objeto LomAvanzadoVO con los datos importados desde el xml
	 * @throws Exception    
	 */

	protected LomAvanzadoVO handleImportarLomes(
			DataHandler ficheroLomes) 
	throws Exception 
	{
		
		//obtener lomAvanzadoVO desde lom
        LomAvanzadoVO lomAvan = new LomAvanzadoVO();
        try{
    		InputStream is= ficheroLomes.getInputStream();
    		InputSource iso= new InputSource(is);

        	//parsear el file
//    		Lom lomObj = lomesDao.parsearLom(iso);
    		Lom lomObj = this.getLomesDao().parsearLom(iso);
    		logger.debug("Pasado parseo");
        	if(lomObj!=null){
        
		        //General
				if(lomObj.getGeneral()!=null){
					AvGeneralVO rec=(AvGeneralVO) this.getBeanMapper().map(lomObj.getGeneral().getGroupGeneralGeneral(), AvGeneralVO.class);
					lomAvan.setGeneral(rec);
					logger.debug("PLLecf - IMPORTARLOMES general" + rec);
				}
				
		        //Educationals
				if((lomObj.getEducational()!=null)&&(lomObj.getEducational().length>0)){
			        AvEducationalVO[] eduArray = new AvEducationalVO[lomObj.getEducationalCount()];
			        for(int i=0;i<lomObj.getEducationalCount();i++){
			        	AvEducationalVO eduav = (AvEducationalVO) this.getBeanMapper().map(lomObj.getEducational(i).getGroupEducationalEducational(), AvEducationalVO.class);
			        	eduArray[i] = eduav; 
			        }
			        lomAvan.setEducational(eduArray);
			        logger.debug("PLLecf - IMPORTARLOMES educationals " + eduArray.length);
				}
		        
		        //LifeCycle
		        if(lomObj.getLifeCycle()!=null){
		        	AvLifeCycleVO life=(AvLifeCycleVO)this.getBeanMapper().map(lomObj.getLifeCycle().getGroupLifeCycleLifeCycle(), AvLifeCycleVO.class);
		        	lomAvan.setLifeCycle(life);
		        	logger.debug("PLLecf - IMPORTARLOMES lifeCycle " + life);
		        }	
		        
		        //Technical
		        if(lomObj.getTechnical()!=null){
			        AvTechnicalVO tech=(AvTechnicalVO)this.getBeanMapper().map(lomObj.getTechnical().getGroupTechnicalTechnical(), AvTechnicalVO.class);
			        lomAvan.setTechnical(tech);
			        logger.debug("PLLecf - IMPORTARLOMES technical " + tech);
		        }
		        
		        //MetaData
		        if(lomObj.getMetaMetadata()!=null){
		        	AvMetametadataVO meta=(AvMetametadataVO)this.getBeanMapper().map(lomObj.getMetaMetadata().getGroupMetaMetadataMetaMetadata(), AvMetametadataVO.class);
		        	lomAvan.setMetaMetadata(meta);
		        	logger.debug("PLLecf - IMPORTARLOMES MetaData" + meta);
		        }
		        
		        //Derechos
		        if(lomObj.getRights()!=null){
			        AvRightsVO rights=(AvRightsVO)this.getBeanMapper().map(lomObj.getRights().getGroupRightsRights(), AvRightsVO.class);
			        lomAvan.setDerechos(rights);
			        logger.debug("PLLecf - IMPORTARLOMES derechos " + rights);
		        }
		       
		        //Relaciones
		        if((lomObj.getRelation()!=null)&&(lomObj.getRelation().length>0)){
			        AvRelationVO[] relArray = new AvRelationVO[lomObj.getRelationCount()];
			        for(int i=0;i<lomObj.getRelationCount();i++){
			        	AvRelationVO relav = (AvRelationVO) this.getBeanMapper().map(lomObj.getRelation(i).getGroupRelationRelation(), AvRelationVO.class);
			        	relArray[i] = relav; 
			        }
			        lomAvan.setRelaciones(relArray);
			        logger.debug("PLLecf - IMPORTARLOMES relaciones " + relArray.length);
		        }
		        
		        //Annotationes
		        if((lomObj.getAnnotation()!=null)&&(lomObj.getAnnotation().length>0)){
			        AvAnnotationVO[] anotaArray = new AvAnnotationVO[lomObj.getAnnotationCount()];
			        for(int i=0;i<lomObj.getAnnotationCount();i++){
			        	AvAnnotationVO anotaVo = (AvAnnotationVO) this.getBeanMapper().map(lomObj.getAnnotation(i).getGroupAnnotationAnnotation(), AvAnnotationVO.class);
			        	anotaArray[i] = anotaVo; 
			        }
			        lomAvan.setAnotaciones(anotaArray);
			        logger.debug("PLLecf - IMPORTARLOMES anotaciones " + anotaArray.length );
		        }
		        
		        //Classificationes
		        if((lomObj.getClassification()!=null)&&(lomObj.getClassification().length>0)){
			        AvClassificationVO[] claArray = new AvClassificationVO[lomObj.getClassificationCount()];
			        for(int i=0;i<lomObj.getClassificationCount();i++){
			        	AvClassificationVO relav = (AvClassificationVO) this.getBeanMapper().map(lomObj.getClassification(i).getGroupClassificationClassification(), AvClassificationVO.class);
			        	claArray[i] = relav; 
			        }
			        lomAvan.setClasificaciones(claArray); 
			        logger.debug("PLLecf - IMPORTARLOMES clasificaciones " + claArray.length);
		        }
		        
			}else{
				throw new  Exception("El Objeto Lom es null--- no se pudo parsear");
			}
			
    	
			}catch(Exception e){
    		logger.error(e);
    		throw e;
    	}

		//devolver LomAvanzadoVO
		
        return lomAvan;		
	}
	/**
	 * Permite exportar un fichero xml de catalogación LOM-ES avanzado encapsulado 
	 * en un DataHandler.
	 * 
	 * @param identificador  Identificador del ode que se quiere catalogar
	 * @param usuario 		 nombre de usuario 
	 * @param lom    LomAvanzado que representa la catalogación que se intenta exportar
	 * @param idioma 		 Idioma en que se intenta exportar la catalogación
	 * @return DataHandler 	 DataHandler que encapsula el fichero xml en que se exporta la catalogación
	 * @throws Exception    
	 */

	protected DataHandler handleExportarLomesDesdeLom(
			String identificador, 
			String usuario, 
			String idioma, 
			DataHandler lom) 
	throws Exception {

		//declaro el data handler
		DataHandler dh=null;
		Lom lomObj = null;
		try{
			if (lom.getContentType().equals("application/x-java-serialized-object"))
			{
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				lom.writeTo(baos);
				Object tmp = deserializar(baos.toByteArray());
				
				if (tmp instanceof Lom)
					lomObj = (Lom) tmp;
				else {
					Logger.getLogger(this.getClass()).error(
							"El objeto recibido no es un LOM_ES");
					throw new Exception("El objeto recibido no es un LOM_ES");
				}
	
			}
			else
				throw new Exception ("No existe objeto para cargar");
	
			//creo un writer para escribir los metadatos
			StringWriter sw= new StringWriter();
			//completo el writer
			this.getLomesDao().escribirLom(lomObj, sw);
//			lomesDao.escribirLom(lomObj, sw);
			//creo el datahandler con el string del writer
			dh = new DataHandler(sw.toString(),"text/plain");
				
		} catch (Exception e) {
			logger.error("PLL - error en SrvCatalogacionAvanzadaService.ExportarLom: error al generar el contenido a exportar " + e);
			throw e;
		}
		//regreso el datahandler
		return dh;
	}

	/**
	 * Realiza la traducción de los campos de texto de lomAv a uno de los idiomas soportados por la plataforma
	 * 
	 * 
	 * @param identificador  Identificador del ode
	 * @param usuario 		 nombre de usuario 
	 * @param lom    		 LomAvanzado que representa la catalogación que se va a traducir
	 * @param idiomaTrad 	 Idioma en el que se va a realizar la traduccion
	 * @return LomAvanzadoVO Estructura traducida que se devuelve	
	 * @throws Exception    
	 **/

//	@Override
//	protected LomAvanzadoVO handleTraducirLomAvanzado(String identificador, String usuario, String idiomaTrad, LomAvanzadoVO lomAv, String idiomaOri, String soloCoincidentes) throws Exception {
//		LomAvanzadoVO lomAvTrad = null;
//		if (lomAv!=null) {			
//			
//			//String textoAtrad="Anticipation in the most usual time sequences.";
//			TraduccionDao traduce = new TraduccionDao();
//			//Comienzo Traducción Clase General
//			AvGeneralVO generalTrad = null;
//			
//			if (lomAv.getGeneral()!=null) {
//				generalTrad = new AvGeneralVO();
//				ArrayList arrTit = new ArrayList();
//				TituloVO titulo=lomAv.getGeneral().getTitulo();
////				recorremos los titulos para hacer la traducción
//				if ((titulo!=null) && (titulo.getTextos()!=null)) {					
//					for (int t=0; t<titulo.getTextos().length; t++) {
//						LangStringVO langStringvo =new LangStringVO();
//						String titTexto=titulo.getTextos()[t].getTexto();						
//						String titTextoTrad = traduce.traduce(titTexto, idiomaOri, idiomaTrad);
//						langStringvo.setIdioma(titulo.getTextos()[t].getIdioma());
//						langStringvo.setTexto(titTextoTrad); //metemos el texto traducido!!
//						arrTit.add(langStringvo);
//					}
//				}//fin titulo
//				
//				TituloVO tituloTrad =new TituloVO();
//				LangStringVO[] ang = (LangStringVO[])arrTit.toArray(new LangStringVO[arrTit.size()]);
//				tituloTrad.setTextos(ang);
//				generalTrad.setTitulo(tituloTrad);
//				
//				//Recorremo las descripciones para hacer la traducción
//				DescripcionVO[] desc = lomAv.getGeneral().getDescripciones();
//				ArrayList arrDescOne = null;
//				if (desc!=null){
//					arrDescOne = new ArrayList();
//					for (int d=0; d<desc.length; d++) {
//						if (desc[d].getTextos()!=null) {
//							DescripcionVO descOne= new DescripcionVO();
//							ArrayList arrDesc = new ArrayList();
//							for (int dd=0; dd< desc[d].getTextos().length; dd++) {
//								LangStringVO langStringvo =new LangStringVO();
//								String descTexto=desc[d].getTextos()[dd].getTexto();
//								String descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
//								langStringvo.setIdioma(desc[d].getTextos()[dd].getIdioma());
//								langStringvo.setTexto(descTextoTrad);
//								arrDesc.add(langStringvo);
//							}
//							ang = (LangStringVO[])arrDesc.toArray(new LangStringVO[arrDesc.size()]);
//							descOne.setTextos(ang);
//							arrDescOne.add(descOne);
//						}
//					}								
//				}//fin descripciones
//				DescripcionVO[] descTrad = (DescripcionVO[])arrDescOne.toArray(new DescripcionVO[arrDescOne.size()]);
//				generalTrad.setDescripciones(descTrad);	
//				
//				//Sacamos las Palabras Clave
//				PalabraClaveVO[] palClave= lomAv.getGeneral().getPalabrasClave();
//				ArrayList arrPcla =null;
//				if (palClave!=null) {
//					arrPcla = new ArrayList();
//					for (int p=0; p<palClave.length; p++) {						
//						if (palClave[p].getTextos()!=null) {
//							PalabraClaveVO pClaOne= new PalabraClaveVO();
//							ArrayList arrLang = new ArrayList();
//							for (int pp=0; pp<palClave[p].getTextos().length;pp++) {
//								LangStringVO langStringvo =new LangStringVO();
//								String descTexto=palClave[p].getTextos()[pp].getTexto();
//								String descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
//								langStringvo.setIdioma(palClave[p].getTextos()[pp].getIdioma());
//								langStringvo.setTexto(descTextoTrad);
//								arrLang.add(langStringvo);
//							}
//							ang = (LangStringVO[])arrLang.toArray(new LangStringVO[arrLang.size()]);
//							pClaOne.setTextos(ang);
//							arrPcla.add(pClaOne);
//						}
//					}					
//				}//fin Palabras clave
//				PalabraClaveVO[] palClaveTrad =(PalabraClaveVO[])arrPcla.toArray(new PalabraClaveVO[arrPcla.size()]);
//				generalTrad.setPalabrasClave(palClaveTrad);
//				
//				//Sacamos el Ámbito
//				AmbitoVO[] ambito =lomAv.getGeneral().getAmbitos();
//				ArrayList arrAmb=null;
//				if (ambito!=null) {
//					arrAmb=new ArrayList();
//						for (int a=0; a<ambito.length; a++) {
//						if (ambito[a].getTextos()!=null) {
//							ArrayList arrLang = new ArrayList();
//							AmbitoVO ambitovo= new AmbitoVO();
//							for (int aa=0; aa<ambito[a].getTextos().length; aa++) {
//								LangStringVO langStringvo =new LangStringVO();
//								String descTexto=ambito[a].getTextos()[aa].getTexto();
//								String descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
//								langStringvo.setIdioma(ambito[a].getTextos()[aa].getIdioma());
//								langStringvo.setTexto(descTextoTrad);
//								arrLang.add(langStringvo);
//							}
//							ang = (LangStringVO[])arrLang.toArray(new LangStringVO[arrLang.size()]);
//							ambitovo.setTextos(ang);
//							arrAmb.add(ambitovo);
//						}
//					}
//				}//fin ambito
//				AmbitoVO[] ambitoTrad = (AmbitoVO[])arrAmb.toArray(new AmbitoVO[arrAmb.size()]);
//				generalTrad.setAmbitos(ambitoTrad);
//				
//				//Añadimos el resto de datos de General q no se traducen
//				generalTrad.setIdentificadores(lomAv.getGeneral().getIdentificadores());
//				generalTrad.setIdiomas(lomAv.getGeneral().getIdiomas());
//				generalTrad.setEstructura(lomAv.getGeneral().getEstructura());
//				generalTrad.setNivelAgregacion(lomAv.getGeneral().getNivelAgregacion());
//			}//fin General
//			////////////////////// Fin traduccion Categoria General
//			
//			
//			
//			/////////////////////// Fin traduccion Categoria Life Cycle
//			//String salida = traduce.traduce(textoAtrad, "en", "es");
//			lomAvTrad= new LomAvanzadoVO();
//			lomAvTrad.setGeneral(generalTrad);//falla aqi
//			
//			//lomAvTrad.setLifeCycle(lifeCycleTrad);
//		}//fin lomAvTrad no null
//		
//		
//		return lomAvTrad;
//	}
	

	@Override
	protected LomAvanzadoVO handleTraducirLomAvanzado(String identificador, String usuario, String idiomaTrad, LomAvanzadoVO lomAv, String idiomaOri, boolean soloCoincidentes) throws Exception {
		LomAvanzadoVO lomAvTrad = null;
		try {
			if (lomAv!=null) {
				//String textoAtrad="Anticipation in the most usual time sequences.";
				TraduccionDao traduce = new TraduccionDao();
				//Comienzo Traducción Clase General
				AvGeneralVO generalTrad = null;
				if (lomAv.getGeneral()!=null) {
					generalTrad = new AvGeneralVO();
					TituloVO titulo = lomAv.getGeneral().getTitulo();
//				recorremos los titulos para hacer la traducción
					TituloVO tituloTrad = null;
					if (titulo!=null){
						tituloTrad =new TituloVO();
						if(titulo.getTextos()!=null) {
							ArrayList<LangStringVO> arrTit = new ArrayList<LangStringVO>();
							for (int t=0; t<titulo.getTextos().length; t++) {
								LangStringVO langStringvo =new LangStringVO();
								String titTexto=titulo.getTextos()[t].getTexto();						
								String titTextoTrad = titTexto;
								String idioma = titulo.getTextos()[t].getIdioma();
								boolean traducir = false;
								if(soloCoincidentes){//Traduce solo los coincidentes
									if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
										traducir = true;
									else//Si no coincide, dejalo como esta
										traducir = false;
								}
								else//Traduce todos
									traducir = true;
									
								if(traducir){
									titTextoTrad = traduce.traduce(titTexto, idiomaOri, idiomaTrad);
									idioma = idiomaTrad;
								}
								langStringvo.setIdioma(idioma);
								langStringvo.setTexto(titTextoTrad); //metemos el texto traducido!!
								arrTit.add(langStringvo);
							}
							LangStringVO[] ang = arrTit.toArray(new LangStringVO[arrTit.size()]);
							tituloTrad.setTextos(ang);
						}
					}//fin titulo
					generalTrad.setTitulo(tituloTrad);
					
					//Idiomas
//					ArrayList idiomasTrad = new ArrayList();
//					ArrayList idiom = new ArrayList();
//					ArrayList idiomvo = new ArrayList();
//					if(lomAv.getGeneral().getIdiomas() != null){
//						if(soloCoincidentes){
//							IdiomaVO[] idiomas = lomAv.getGeneral().getIdiomas();
//							for(int i = 0; i < idiomas.length; i++){//Traducimos la lista de idiomas
//								if(idiomas[i] == null || idiomas[i].getTexto().equals(idiomaOri) || idiomas[i].getTexto().equals("")){
//									idiomasTrad.add(idiomaTrad);
//								}
//								else{
//									idiomasTrad.add(idiomas[i].getTexto());
//								}
//							}
//							//Eliminamos los idiomas que se repitan
//							for(int i = 0; i < idiomasTrad.size(); i++){
//								String id = (String) idiomasTrad.get(i);
//								if(!idiom.contains(id)){
//									idiom.add(id);
//									idiomvo.add(new IdiomaVO(id));
//								}
//							}
//						}
//						else{
//							idiomasTrad.add(idiomaTrad);
//						}
//					}
//					IdiomaVO[] idiomTrad = (IdiomaVO[])idiomvo.toArray(new IdiomaVO[idiomvo.size()]);
//					generalTrad.setIdiomas(idiomTrad);
					
					//Recorremos las descripciones para hacer la traducción
					DescripcionVO[] desc = lomAv.getGeneral().getDescripciones();
					if (desc!=null){
						ArrayList<DescripcionVO> arrDescOne = new ArrayList<DescripcionVO>();
						for (int d=0; d<desc.length; d++) {
							DescripcionVO descOne = null;
							if (desc[d] != null) {
								descOne = new DescripcionVO();
								if(desc[d].getTextos()!=null){
									ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
									for (int dd=0; dd< desc[d].getTextos().length; dd++) {
										LangStringVO langStringvo =new LangStringVO();
										String descTexto=desc[d].getTextos()[dd].getTexto();
										String descTextoTrad = descTexto;
										String idioma = desc[d].getTextos()[dd].getIdioma();
										boolean traducir = true;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
										
										if(traducir){
											descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(descTextoTrad);
										arrDesc.add(langStringvo);
									}
									LangStringVO[] ang = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
									descOne.setTextos(ang);
								}
							}
							arrDescOne.add(descOne);
						}	
						DescripcionVO[] descTrad = arrDescOne.toArray(new DescripcionVO[arrDescOne.size()]);
						generalTrad.setDescripciones(descTrad);
					}//fin descripciones
						
					
					//Sacamos las Palabras Clave
					PalabraClaveVO[] palClave= lomAv.getGeneral().getPalabrasClave();
					if (palClave!=null) {
						ArrayList<PalabraClaveVO> arrPcla = new ArrayList<PalabraClaveVO>();
						for (int p=0; p<palClave.length; p++) {
							PalabraClaveVO pClaOne = null;
							if(palClave[p] != null){
								pClaOne = new PalabraClaveVO();
								if (palClave[p].getTextos()!=null) {
									ArrayList<LangStringVO> arrLang = new ArrayList<LangStringVO>();
									for (int pp=0; pp<palClave[p].getTextos().length;pp++) {
										LangStringVO langStringvo =new LangStringVO();
										String descTexto = palClave[p].getTextos()[pp].getTexto();
										String descTextoTrad = descTexto;
										String idioma = palClave[p].getTextos()[pp].getIdioma();
										boolean traducir = false;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
										
										if(traducir){
											descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(descTextoTrad);
										arrLang.add(langStringvo);
									}
									LangStringVO[] ang = arrLang.toArray(new LangStringVO[arrLang.size()]);
									pClaOne.setTextos(ang);
								}
							}
							arrPcla.add(pClaOne);
						}	
						PalabraClaveVO[] palClaveTrad =arrPcla.toArray(new PalabraClaveVO[arrPcla.size()]);
						generalTrad.setPalabrasClave(palClaveTrad);
					}//fin Palabras clave
					
					
					//Sacamos el Ámbito
					AmbitoVO[] ambito =lomAv.getGeneral().getAmbitos();
					if (ambito!=null) {
						ArrayList<AmbitoVO> arrAmb =new ArrayList<AmbitoVO>();
						for (int a=0; a<ambito.length; a++) {
							AmbitoVO ambitovo = null;
							if(ambito[a] != null){
								ambitovo = new AmbitoVO();
								if (ambito[a].getTextos()!=null) {
									ArrayList<LangStringVO> arrLang = new ArrayList<LangStringVO>();
									for (int aa=0; aa<ambito[a].getTextos().length; aa++) {
										LangStringVO langStringvo =new LangStringVO();
										String descTexto = ambito[a].getTextos()[aa].getTexto();
										String descTextoTrad = descTexto;
										String idioma = ambito[a].getTextos()[aa].getIdioma();
										boolean traducir = false;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
											
										if(traducir){
											descTextoTrad = traduce.traduce(descTexto, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(descTextoTrad);
										arrLang.add(langStringvo);
									}
									LangStringVO[] ang = arrLang.toArray(new LangStringVO[arrLang.size()]);
									ambitovo.setTextos(ang);
								}
							}
							arrAmb.add(ambitovo);
						}
						AmbitoVO[] ambitoTrad = arrAmb.toArray(new AmbitoVO[arrAmb.size()]);
						generalTrad.setAmbitos(ambitoTrad);
					}//fin ambito
					
					
					//Añadimos el resto de datos de General q no se traducen
					generalTrad.setIdiomas(lomAv.getGeneral().getIdiomas());
					generalTrad.setIdentificadores(lomAv.getGeneral().getIdentificadores());
					generalTrad.setEstructura(lomAv.getGeneral().getEstructura());
					generalTrad.setNivelAgregacion(lomAv.getGeneral().getNivelAgregacion());
				}//fin General
				////////////////////// Fin traduccion Categoria General
				lomAvTrad= new LomAvanzadoVO();
				lomAvTrad.setGeneral(generalTrad);
				
				//Comienzo set Ciclo de Vida
				AvLifeCycleVO lifeCycleTrad = null;
				if(lomAv.getLifeCycle() != null){
					lifeCycleTrad = new AvLifeCycleVO();
					//Version
					VersionVO versionTrad = null;
					if(lomAv.getLifeCycle().getVersion() != null){
						versionTrad = new VersionVO();
						VersionVO version = lomAv.getLifeCycle().getVersion();
						ArrayList<LangStringVO> arrTextos = new ArrayList<LangStringVO>();
						if(version.getTextos() != null){
							LangStringVO[] textos = version.getTextos();
							for(int cont = 0; cont < textos.length; cont++){
								String descVer = textos[cont].getTexto();
								String idioma = textos[cont].getIdioma();
								LangStringVO langStringvo = new LangStringVO();
								String descVerTrad = descVer;
								boolean traducir = false;
								if(soloCoincidentes){//Traduce solo los coincidentes
									if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
										traducir = true;
									else//Si no coincide, dejalo como esta
										traducir = false;
								}
								else//Traduce todos
									traducir = true;
									
								if(traducir){
									descVerTrad = traduce.traduce(descVer, idiomaOri, idiomaTrad);
									idioma = idiomaTrad;
								}
								langStringvo.setIdioma(idioma);
								langStringvo.setTexto(descVerTrad);
								arrTextos.add(langStringvo);
							}
						}
						LangStringVO[] textos = arrTextos.toArray(new LangStringVO[arrTextos.size()]);
						versionTrad.setTextos(textos);
					}
					lifeCycleTrad.setVersion(versionTrad);
					
					//Sacamos las Contribuciones
					if(lomAv.getLifeCycle().getContribuciones() != null){
						ArrayList<ContribucionVO> arrContr = new ArrayList<ContribucionVO>();
						ContribucionVO[] contribuciones = lomAv.getLifeCycle().getContribuciones();
						FechaVO fechavo = null;
						for(int i = 0; i < contribuciones.length; i++){
							ContribucionVO contribucion = null;
							if(contribuciones[i] != null){
								contribucion = new ContribucionVO();
								//Entidad
//								if(contribuciones[i].getEntidades() != null){
//									EntidadVO[] entidades = contribuciones[i].getEntidades();
//									ArrayList arrEnt = new ArrayList();
//									for(int j = 0; j < entidades.length; j++){
//										String texto = entidades[j].getTexto();
//										String textoTrad = "";
//										String nombre = texto.substring(texto.indexOf("FN:") + 3, texto.indexOf("EMAIL"));
//										String nombreTrad = traduce.traduce(nombre, idiomaOri, idiomaTrad);
//										String org = texto.substring(texto.indexOf("ORG:") + 4, texto.indexOf("END:VCARD"));
//										String orgTrad = traduce.traduce(org, idiomaOri, idiomaTrad);
//										textoTrad = texto.substring(0, texto.indexOf("FN:") + 3) +
//											nombreTrad + " " + 
//											texto.substring(texto.indexOf("EMAIL"), texto.indexOf("ORG:") + 4) +
//											orgTrad + " " +
//											texto.substring(texto.indexOf("END:"));
//										EntidadVO entidadvo = new EntidadVO();
//										entidadvo.setTexto(textoTrad);
//										arrEnt.add(entidadvo);
//									}
//									EntidadVO[] entidadesTrad = (EntidadVO[])arrEnt.toArray(new EntidadVO[arrEnt.size()]);
//									contribucion.setEntidades(entidadesTrad);
//								}
								contribucion.setEntidades(contribuciones[i].getEntidades());
							
								//Sacamos las Descripciones de Fecha y las traducimos
								if(contribuciones[i].getFecha() != null){
									FechaVO fecha = contribuciones[i].getFecha();
									DescripcionVO descvo = null;
									if(fecha.getDescripcion() != null){
										descvo = new DescripcionVO();
										ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
										if(fecha.getDescripcion().getTextos() != null){
											LangStringVO[] descripciones = contribuciones[i].getFecha().getDescripcion().getTextos();
											fechavo = new FechaVO();
											for(int j = 0; j < descripciones.length; j++){
												String descFecha = descripciones[j].getTexto();
												String idioma = descripciones[j].getIdioma();
												LangStringVO langStringvo = new LangStringVO();
												String descFechaTrad = descFecha;
												boolean traducir = false;
												if(soloCoincidentes){//Traduce solo los coincidentes
													if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
														traducir = true;
													else//Si no coincide, dejalo como esta
														traducir = false;
												}
												else//Traduce todos
													traducir = true;
													
												if(traducir){
													descFechaTrad = traduce.traduce(descFecha, idiomaOri, idiomaTrad);
													idioma = idiomaTrad;
												}
												langStringvo.setIdioma(idioma);
												langStringvo.setTexto(descFechaTrad);
												arrDesc.add(langStringvo);
											}
										}
										LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
										descvo.setTextos(textos);
									}
									//TODO Debería ir dentro del if?
									if (fechavo!=null) {
										fechavo.setDescripcion(descvo);
										fechavo.setFecha(fecha.getFecha());
									}
								}
								contribucion.setFecha(fechavo);
								contribucion.setTipo(contribuciones[i].getTipo());
							}
							arrContr.add(contribucion);
						}
						ContribucionVO[] contribs = arrContr.toArray(new ContribucionVO[arrContr.size()]);
						lifeCycleTrad.setContribuciones(contribs);
					}
					//Añadimos el resto de campos de Ciclo de Vida que no se traducen
					lifeCycleTrad.setEstado(lomAv.getLifeCycle().getEstado());
				}/////////////////////// Fin traduccion Categoria Life Cycle
				lomAvTrad.setLifeCycle(lifeCycleTrad);
				
				//Comienzo set Meta-metadatos
				AvMetametadataVO metaMetadataTrad = null;
				if(lomAv.getMetaMetadata() != null){
					metaMetadataTrad = new AvMetametadataVO();
					//Sacamos las Contribuciones
					if(lomAv.getMetaMetadata().getContribuciones() != null){
						ArrayList<ContribucionVO> arrContr = new ArrayList<ContribucionVO>();
						ContribucionVO[] contribuciones = lomAv.getMetaMetadata().getContribuciones();
						FechaVO fechavo = null;
//						EntidadVO entidadvo = new EntidadVO();
						for(int i = 0; i < contribuciones.length; i++){
							ContribucionVO contribucion = new ContribucionVO();
							//Entidad
//							if(contribuciones[i].getEntidades() != null){
//								EntidadVO[] entidades = contribuciones[i].getEntidades();
//								ArrayList arrEnt = new ArrayList();
//								for(int j = 0; j < entidades.length; j++){
//									EntidadVO entidad = new EntidadVO();
//									String texto = entidades[j].getTexto();
//									String nombre = texto.substring(texto.indexOf("FN:") + 3, texto.indexOf("EMAIL"));
//									String nombreTrad = traduce.traduce(nombre, idiomaOri, idiomaTrad);
//									String org = texto.substring(texto.indexOf("ORG:") + 4, texto.indexOf("END:VCARD"));
//									String orgTrad = traduce.traduce(org, idiomaOri, idiomaTrad);
//									String textoTrad = texto.substring(0, texto.indexOf("FN:") + 3) + 
//										nombreTrad + " " +
//										texto.substring(texto.indexOf("EMAIL"), texto.indexOf("ORG:") + 4) + 
//										orgTrad + " " +
//										texto.substring(texto.indexOf("END:"));
//									entidad.setTexto(textoTrad);
//									arrEnt.add(entidad);
//								}
//								EntidadVO[] entidadesTrad = (EntidadVO[])arrEnt.toArray(new EntidadVO[arrEnt.size()]);
//								contribucion.setEntidades(entidadesTrad);
//							}
							contribucion.setEntidades(contribuciones[i].getEntidades());
						
							//Sacamos las Descripciones de Fecha y las traducimos
							if(contribuciones[i].getFecha() != null){
								FechaVO fecha = contribuciones[i].getFecha();
								DescripcionVO descvo = new DescripcionVO();
								if(fecha.getDescripcion() != null && fecha.getDescripcion().getTextos() != null){
									LangStringVO[] descripciones = contribuciones[i].getFecha().getDescripcion().getTextos();
									ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
									fechavo = new FechaVO();
									for(int j = 0; j < descripciones.length; j++){
										String descFecha = descripciones[j].getTexto();
										String idioma = descripciones[j].getIdioma();
										LangStringVO langStringvo = new LangStringVO();
										String descFechaTrad = descFecha;
										boolean traducir = false;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
											
										if(traducir){
											descFechaTrad = traduce.traduce(descFecha, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(descFechaTrad);
										arrDesc.add(langStringvo);
									}
									LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
									descvo.setTextos(textos);
								}
								//TODO Debería ir dentro del if superior?
								if (fechavo!=null) {
									fechavo.setDescripcion(descvo);
									fechavo.setFecha(fecha.getFecha());
								}
							}
							contribucion.setFecha(fechavo);
							contribucion.setTipo(contribuciones[i].getTipo());
							arrContr.add(contribucion);
						}
						ContribucionVO[] contribs = arrContr.toArray(new ContribucionVO[arrContr.size()]);
						metaMetadataTrad.setContribuciones(contribs);
					}
					//Esquema de Metadatos
//					if(lomAv.getMetaMetadata().getEsquemas() != null){
//						EsquemaDeMetadatosVO[] esquemas = lomAv.getMetaMetadata().getEsquemas();
//						ArrayList arrEsquemas = new ArrayList();
//						for(int i = 0; i < esquemas.length; i++){
//							String texto = esquemas[i].getTexto();
//							String textoTrad = traduce.traduce(texto, idiomaOri, idiomaTrad);
//							EsquemaDeMetadatosVO esq = new EsquemaDeMetadatosVO();
//							esq.setTexto(textoTrad);
//							arrEsquemas.add(esq);
//						}
//						EsquemaDeMetadatosVO[] esquemasTrad = (EsquemaDeMetadatosVO[])arrEsquemas.toArray(new EsquemaDeMetadatosVO[arrEsquemas.size()]);
//						metaMetadataTrad.setEsquemas(esquemasTrad);
//					}
//					//Idioma
//					metaMetadataTrad.setIdioma(new IdiomaVO(idiomaTrad));
					
					//Idioma de Catalogacion
					metaMetadataTrad.setIdioma(new IdiomaVO(idiomaTrad));
					
					//Añadimos el resto de campos de MetaMetadata que no se traducen
					metaMetadataTrad.setIdentificadores(lomAv.getMetaMetadata().getIdentificadores());
					metaMetadataTrad.setEsquemas(lomAv.getMetaMetadata().getEsquemas());
//					metaMetadataTrad.setIdioma(lomAv.getMetaMetadata().getIdioma());
				}/////////////////////// Fin traduccion Categoria MetaMetadata
				lomAvTrad.setMetaMetadata(metaMetadataTrad);
				
				//Comienzo set Tecnica
				AvTechnicalVO tecnicaTrad = null;
				if(lomAv.getTechnical() != null){
					tecnicaTrad = new AvTechnicalVO();
					//Pautas de Instalacion
					PautasInstalacionVO pautasTrad = null;
					if(lomAv.getTechnical().getPautasInstalacion() != null){
						pautasTrad = new PautasInstalacionVO();
						if(lomAv.getTechnical().getPautasInstalacion().getTextos() != null){
						LangStringVO[] pautas = lomAv.getTechnical().getPautasInstalacion().getTextos();
							ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
							for(int i = 0; i < pautas.length; i++){
								String descPauta = pautas[i].getTexto();
								String idioma = pautas[i].getIdioma();
								LangStringVO langStringvo = new LangStringVO();
								String descPautaTrad = descPauta;
								boolean traducir = false;
								if(soloCoincidentes){//Traduce solo los coincidentes
									if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
										traducir = true;
									else//Si no coincide, dejalo como esta
										traducir = false;
								}
								else//Traduce todos
									traducir = true;
									
								if(traducir){
									descPautaTrad = traduce.traduce(descPauta, idiomaOri, idiomaTrad);
									idioma = idiomaTrad;
								}
								langStringvo.setIdioma(idioma);
								langStringvo.setTexto(descPautaTrad);
								arrDesc.add(langStringvo);
							}
							LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
							pautasTrad.setTextos(textos);
						}
					}
					tecnicaTrad.setPautasInstalacion(pautasTrad);
					
					//Otros Requisitos de Plataforma
					OtrosRequisitosVO otrosTrad = null;
					if(lomAv.getTechnical().getOtrosRequisitos() != null){
						otrosTrad = new OtrosRequisitosVO();
						if(lomAv.getTechnical().getOtrosRequisitos().getTextos() != null){
							LangStringVO[] otrosRequisitos = lomAv.getTechnical().getOtrosRequisitos().getTextos();
							ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
							for(int i = 0; i < otrosRequisitos.length; i++){
								String descOtros = otrosRequisitos[i].getTexto();
								String idioma = otrosRequisitos[i].getIdioma();
								LangStringVO langStringvo = new LangStringVO();
								String descOtrosTrad = descOtros;
								boolean traducir = false;
								if(soloCoincidentes){//Traduce solo los coincidentes
									if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
										traducir = true;
									else//Si no coincide, dejalo como esta
										traducir = false;
								}
								else//Traduce todos
									traducir = true;
									
								if(traducir){
									descOtrosTrad = traduce.traduce(descOtros, idiomaOri, idiomaTrad);
									idioma = idiomaTrad;
								}
								langStringvo.setIdioma(idioma);
								langStringvo.setTexto(descOtrosTrad);
								arrDesc.add(langStringvo);
							}
							LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
							otrosTrad.setTextos(textos);
						}
					}
					tecnicaTrad.setOtrosRequisitos(otrosTrad);
						
					//Duracion
					DuracionVO duracionesTrad = null;
					if(lomAv.getTechnical().getDuracion() != null){
						duracionesTrad = new DuracionVO();
						DescripcionVO descripcionDur = null;
						if(lomAv.getTechnical().getDuracion().getDescripcion() != null){
							descripcionDur = new DescripcionVO();
							if(lomAv.getTechnical().getDuracion().getDescripcion().getTextos() != null){
								LangStringVO[] duraciones = lomAv.getTechnical().getDuracion().getDescripcion().getTextos();
								ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
								for(int i = 0; i < duraciones.length; i++){
									String descDuracion = duraciones[i].getTexto();
									String idioma = duraciones[i].getIdioma();
									LangStringVO langStringvo = new LangStringVO();
									String descDuracionTrad = descDuracion;
									boolean traducir = false;
									if(soloCoincidentes){//Traduce solo los coincidentes
										if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
											traducir = true;
										else//Si no coincide, dejalo como esta
											traducir = false;
									}
									else//Traduce todos
										traducir = true;
										
									if(traducir){
										descDuracionTrad = traduce.traduce(descDuracion, idiomaOri, idiomaTrad);
										idioma = idiomaTrad;
									}
									langStringvo.setIdioma(idioma);
									langStringvo.setTexto(descDuracionTrad);
									arrDesc.add(langStringvo);
								}
								LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
								descripcionDur.setTextos(textos);
							}
						}
						duracionesTrad.setDescripcion(descripcionDur);
						duracionesTrad.setDuracion(lomAv.getTechnical().getDuracion().getDuracion());
					}
					tecnicaTrad.setDuracion(duracionesTrad);
						
					//Metemos el resto de campos de Tecnica que no se traducen
					tecnicaTrad.setFormatos(lomAv.getTechnical().getFormatos());
					tecnicaTrad.setLocalizaciones(lomAv.getTechnical().getLocalizaciones());
					tecnicaTrad.setRequisitos(lomAv.getTechnical().getRequisitos());
					tecnicaTrad.setTamanio(lomAv.getTechnical().getTamanio());
				}/////////////////////// Fin traduccion Categoria Tecnica
				try {
					lomAvTrad.setTechnical(tecnicaTrad);
				} catch (Exception e) {
					logger.error("Error en setTechnical " + e);
					throw e;
				}
				
				//Comienzo set Uso Educativo
				if(lomAv.getEducational() != null){
					ArrayList<AvEducationalVO> arrEduTrad = new ArrayList<AvEducationalVO>();
					AvEducationalVO[] avEdu = lomAv.getEducational();
					for(int i = 0; i < avEdu.length; i++){ //Cada Uso Educativo
						AvEducationalVO cadaUsoEducativo = new AvEducationalVO();
						if(avEdu[i].getRangoedades() != null){
							ArrayList<RangoEdadVO> edadesDest = new ArrayList<RangoEdadVO>();
							//Edad del Destinatario
							for(int j = 0; j < avEdu[i].getRangoedades().length; j++){
								RangoEdadVO rangosTrad = null;
								if(avEdu[i].getRangoedades()[j] != null){
									rangosTrad = new RangoEdadVO();
									ArrayList<LangStringVO> arrRangos = new ArrayList<LangStringVO>();
									if(avEdu[i].getRangoedades()[j].getTextos() != null){
										LangStringVO[] rangos = avEdu[i].getRangoedades()[j].getTextos();
										for(int k = 0; k < rangos.length; k++){//Cada rango
											String descRango = rangos[k].getTexto();
											String idioma = rangos[k].getIdioma();
											LangStringVO langStringvo = new LangStringVO();
											String descRangoTrad = descRango;
											boolean traducir = false;
											if(soloCoincidentes){//Traduce solo los coincidentes
												if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
													traducir = true;
												else//Si no coincide, dejalo como esta
													traducir = false;
											}
											else//Traduce todos
												traducir = true;
												
											if(traducir){
												descRangoTrad = traduce.traduce(descRango, idiomaOri, idiomaTrad);
												idioma = idiomaTrad;
											}
											langStringvo.setIdioma(idioma);
											langStringvo.setTexto(descRangoTrad);
											arrRangos.add(langStringvo);
										}
										LangStringVO[] textos = arrRangos.toArray(new LangStringVO[arrRangos.size()]);
										rangosTrad.setTextos(textos);
									}
								}
								edadesDest.add(rangosTrad);
							}
							RangoEdadVO[] edadesTrad = edadesDest.toArray(new RangoEdadVO[edadesDest.size()]);
							cadaUsoEducativo.setRangoedades(edadesTrad);
						}
							
						//Tiempo de aprendizaje tipico
						DuracionVO tiemposTrad = null;
						if(avEdu[i].getTiempoAprendizaje() != null){
							tiemposTrad = new DuracionVO();
							DescripcionVO descripcionTiempo = null;
							if(avEdu[i].getTiempoAprendizaje().getDescripcion() != null){
								descripcionTiempo = new DescripcionVO();
								if(avEdu[i].getTiempoAprendizaje().getDescripcion().getTextos() != null){
									LangStringVO[] tiempos = avEdu[i].getTiempoAprendizaje().getDescripcion().getTextos();
									ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
									for(int n = 0; n < tiempos.length; n++){
										String descTiempo = tiempos[n].getTexto();
										String idioma = tiempos[n].getIdioma();
										LangStringVO langStringvo = new LangStringVO();
										String descTiempoTrad = descTiempo;
										boolean traducir = false;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
											
										if(traducir){
											descTiempoTrad = traduce.traduce(descTiempo, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(descTiempoTrad);
										arrDesc.add(langStringvo);
									}
									LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
									descripcionTiempo.setTextos(textos);
								}
							}
							tiemposTrad.setDescripcion(descripcionTiempo);
							tiemposTrad.setDuracion(avEdu[i].getTiempoAprendizaje().getDuracion());
						}
						cadaUsoEducativo.setTiempoAprendizaje(tiemposTrad);
							
						//Orientaciones Didácticas
						if(avEdu[i].getDescripciones() != null){
							DescripcionVO[] descripciones = avEdu[i].getDescripciones();
							ArrayList<DescripcionVO> orientacion = new ArrayList<DescripcionVO>();
							for(int j = 0; j < descripciones.length; j++){
								DescripcionVO descripcion = null;
								if(descripciones[j] != null){
									descripcion = new DescripcionVO();
									if(descripciones[j].getTextos() != null){
										LangStringVO[] descvo = descripciones[j].getTextos();
										ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
										for(int n = 0; n < descvo.length; n++){
											String desc = descvo[n].getTexto();
											String idioma = descvo[n].getIdioma();
											LangStringVO langStringvo = new LangStringVO();
											String descTrad = desc;
											boolean traducir = false;
											if(soloCoincidentes){//Traduce solo los coincidentes
												if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
													traducir = true;
												else//Si no coincide, dejalo como esta
													traducir = false;
											}
											else//Traduce todos
												traducir = true;
												
											if(traducir){
												descTrad = traduce.traduce(desc, idiomaOri, idiomaTrad);
												idioma = idiomaTrad;
											}
											langStringvo.setIdioma(idioma);
											langStringvo.setTexto(descTrad);
											arrDesc.add(langStringvo);
										}
										LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
										descripcion.setTextos(textos);
									}
								}
								orientacion.add(descripcion);
							}
							DescripcionVO[] orientaciones = orientacion.toArray(new DescripcionVO[orientacion.size()]);
							cadaUsoEducativo.setDescripciones(orientaciones);
						}
						//Idioma del destinatario
//						ArrayList idiomasTrad = new ArrayList();
//						ArrayList idiom = new ArrayList();
//						ArrayList idiomvo = new ArrayList();
//						
//						if(soloCoincidentes){
//							IdiomaVO[] idiomas = avEdu[i].getIdiomas();
//							for(int r = 0; r < idiomas.length; r++){//Traducimos la lista de idiomas
//								if(idiomas[r] == null || idiomas[r].getTexto().equals(idiomaOri) || idiomas[r].getTexto().equals("")){
//									idiomasTrad.add(idiomaTrad);
//								}
//								else{
//									idiomasTrad.add(idiomas[r].getTexto());
//								}
//							}
//							//Eliminamos los idiomas que se repitan
//							for(int r = 0; r < idiomasTrad.size(); r++){
//								String id = (String) idiomasTrad.get(r);
//								if(!idiom.contains(id)){
//									idiom.add(id);
//									idiomvo.add(new IdiomaVO(id));
//								}
//							}
//						}
//						else{
//							idiomasTrad.add(idiomaTrad);
//						}
//						IdiomaVO[] idiomTrad = (IdiomaVO[])idiomvo.toArray(new IdiomaVO[idiomvo.size()]);
//						cadaUsoEducativo.setIdiomas(idiomTrad);
							
						//Metemos el resto de campos de Uso Educativo que no se traducen
						cadaUsoEducativo.setTipoDeInteractividad(avEdu[i].getTipoDeInteractividad());
						cadaUsoEducativo.setTiposrecursoedu(avEdu[i].getTiposrecursoedu());
						cadaUsoEducativo.setNivelInteractividad(avEdu[i].getNivelInteractividad());
						cadaUsoEducativo.setDensidadSemantica(avEdu[i].getDensidadSemantica());
						cadaUsoEducativo.setDestinatarios(avEdu[i].getDestinatarios());
						cadaUsoEducativo.setContextos(avEdu[i].getContextos());
						cadaUsoEducativo.setDificultad(avEdu[i].getDificultad());
						cadaUsoEducativo.setIdiomas(avEdu[i].getIdiomas());
						cadaUsoEducativo.setProcesoscognitivos(avEdu[i].getProcesoscognitivos());
						
						arrEduTrad.add(cadaUsoEducativo);
					}
					AvEducationalVO[] UsosEducativosTrad = arrEduTrad.toArray(new AvEducationalVO[arrEduTrad.size()]);
					/////////////////////// Fin traduccion Categoria Uso Educativo
					lomAvTrad.setEducational(UsosEducativosTrad);
				}
				//Comienzo set Derechos
				AvRightsVO derechos = null;
				if(lomAv.getDerechos() != null){
					derechos = new AvRightsVO();
					DescripcionVO descripcionTrad = null;
					//Descripcion de Condiciones de Utilizacion
					if(lomAv.getDerechos().getDescripcion() != null){
						descripcionTrad = new DescripcionVO();
						if(lomAv.getDerechos().getDescripcion().getTextos() != null){
							LangStringVO[] descripciones = lomAv.getDerechos().getDescripcion().getTextos();
							ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
							for(int i = 0; i < descripciones.length; i++){
								String desc = descripciones[i].getTexto();
								String idioma = descripciones[i].getIdioma();
								LangStringVO langStringvo = new LangStringVO();
								String descTrad = desc;
								boolean traducir = false;
								if(soloCoincidentes){//Traduce solo los coincidentes
									if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
										traducir = true;
									else//Si no coincide, dejalo como esta
										traducir = false;
								}
								else//Traduce todos
									traducir = true;
									
								if(traducir){
									descTrad = traduce.traduce(desc, idiomaOri, idiomaTrad);
									idioma = idiomaTrad;
								}
								langStringvo.setIdioma(idioma);
								langStringvo.setTexto(descTrad);
								arrDesc.add(langStringvo);
							}
							LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
							descripcionTrad.setTextos(textos);
						}
					}
					derechos.setDescripcion(descripcionTrad);
					//Metemos el resto de campos de Derechos que no se traducen
					derechos.setAcceso(lomAv.getDerechos().getAcceso());
					derechos.setCoste(lomAv.getDerechos().getCoste());
					derechos.setDerechosDeAutor(lomAv.getDerechos().getDerechosDeAutor());
				}/////////////////////// Fin traduccion Categoria Derechos
				lomAvTrad.setDerechos(derechos);
				
				//Comienzo set Relacion
				if(lomAv.getRelaciones() != null){
					ArrayList<AvRelationVO> arrRelTrad = new ArrayList<AvRelationVO>();
					AvRelationVO[] avRel = lomAv.getRelaciones();
					for(int i = 0; i < avRel.length; i++){ //Cada Relacion
						AvRelationVO cadaRelacion = new AvRelationVO();
						RecursoVO recursoTrad = null;
						if(avRel[i].getRecurso() != null){
							recursoTrad = new RecursoVO();
							if(avRel[i].getRecurso().getDescripciones() != null){
								ArrayList<DescripcionVO> recursos = new ArrayList<DescripcionVO>();
								//Recurso
								for(int j = 0; j < avRel[i].getRecurso().getDescripciones().length; j++){
									DescripcionVO descripcion = new DescripcionVO();
									if(avRel[i].getRecurso().getDescripciones()[j].getTextos() != null){
										ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
										LangStringVO[] descsRecurso = avRel[i].getRecurso().getDescripciones()[j].getTextos();
										for(int k = 0; k < descsRecurso.length; k++){
											String desc = descsRecurso[k].getTexto();
											String idioma = descsRecurso[k].getIdioma();
											LangStringVO langStringvo = new LangStringVO();
											String descTrad = desc;
											boolean traducir = false;
											if(soloCoincidentes){//Traduce solo los coincidentes
												if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
													traducir = true;
												else//Si no coincide, dejalo como esta
													traducir = false;
											}
											else//Traduce todos
												traducir = true;
												
											if(traducir){
												descTrad = traduce.traduce(desc, idiomaOri, idiomaTrad);
												idioma = idiomaTrad;
											}
											langStringvo.setIdioma(idioma);
											langStringvo.setTexto(descTrad);
											arrDesc.add(langStringvo);
										}
										LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
										descripcion.setTextos(textos);
									}
									recursos.add(descripcion);
								}
								DescripcionVO[] descsTrad = recursos.toArray(new DescripcionVO[recursos.size()]);
								recursoTrad.setDescripciones(descsTrad);
							}
							recursoTrad.setIdentificador(avRel[i].getRecurso().getIdentificador());
						}
						cadaRelacion.setRecurso(recursoTrad);
						
						//Metemos el resto de campos de Relacion que no se traducen
						cadaRelacion.setTipo(avRel[i].getTipo());
						arrRelTrad.add(cadaRelacion);
					}
					AvRelationVO[] relaciones = arrRelTrad.toArray(new AvRelationVO[arrRelTrad.size()]);
					/////////////////////// Fin traduccion Categoria Relacion
					lomAvTrad.setRelaciones(relaciones);
				}
				
				//Comienzo set Anotacion
				if(lomAv.getAnotaciones() != null){
					ArrayList<AvAnnotationVO> arrAnotacionesTrad = new ArrayList<AvAnnotationVO>();
					AvAnnotationVO[] avAnn = lomAv.getAnotaciones();
					for(int i = 0; i < avAnn.length; i++){//Cada Anotacion
						AvAnnotationVO cadaAnotacion = new AvAnnotationVO();
						//Entidad
//						EntidadVO ent = null;
//						if(avAnn[i].getEntidad() != null){
//							EntidadVO entidad = avAnn[i].getEntidad();
//							ent = new EntidadVO();
//							String texto = entidad.getTexto();
//							String nombre = texto.substring(texto.indexOf("FN:") + 3, texto.indexOf("EMAIL"));
//							String nombreTrad = traduce.traduce(nombre, idiomaOri, idiomaTrad);
//							String org = texto.substring(texto.indexOf("ORG:") + 4, texto.indexOf("END:VCARD"));
//							String orgTrad = traduce.traduce(org, idiomaOri, idiomaTrad);
//							String textoTrad = texto.substring(0, texto.indexOf("FN:") + 3) + 
//								nombreTrad + " " +
//								texto.substring(texto.indexOf("EMAIL"), texto.indexOf("ORG:") + 4) + 
//								orgTrad + " " +
//								texto.substring(texto.indexOf("END:"));
//							ent.setTexto(textoTrad);
//							cadaAnotacion.setEntidad(ent);
//						}
						
						//Fecha
						FechaVO fecha = null;
						if(avAnn[i].getFecha() != null){
							fecha = new FechaVO();
							if(avAnn[i].getFecha().getDescripcion() != null){
								DescripcionVO descripcion = new DescripcionVO();
								ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
								LangStringVO[] descsFecha = avAnn[i].getFecha().getDescripcion().getTextos();
								for(int j = 0; j < descsFecha.length; j++){
									String texto = descsFecha[j].getTexto();
									String idioma = descsFecha[j].getIdioma();
									LangStringVO langStringvo = new LangStringVO();
									String textoTrad = texto;
									boolean traducir = false;
									if(soloCoincidentes){//Traduce solo los coincidentes
										if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
											traducir = true;
										else//Si no coincide, dejalo como esta
											traducir = false;
									}
									else//Traduce todos
										traducir = true;
										
									if(traducir){
										textoTrad = traduce.traduce(texto, idiomaOri, idiomaTrad);
										idioma = idiomaTrad;
									}
									langStringvo.setIdioma(idioma);
									langStringvo.setTexto(textoTrad);
									arrDesc.add(langStringvo);
								}
								LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
								descripcion.setTextos(textos);
								fecha.setDescripcion(descripcion);
							}
							fecha.setFecha(avAnn[i].getFecha().getFecha());
						}
						cadaAnotacion.setFecha(fecha);
						
						//Descripcion
						DescripcionVO desc = null;
						if(avAnn[i].getDescripcion() != null){
							desc = new DescripcionVO();
							if(avAnn[i].getDescripcion().getTextos() != null){
								LangStringVO[] descsDesc = avAnn[i].getDescripcion().getTextos();
								ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
								for(int j = 0; j < descsDesc.length; j++){
									String texto = descsDesc[j].getTexto();
									String idioma = descsDesc[j].getIdioma();
									LangStringVO langStringvo = new LangStringVO();
									String textoTrad = texto;
									boolean traducir = false;
									if(soloCoincidentes){//Traduce solo los coincidentes
										if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
											traducir = true;
										else//Si no coincide, dejalo como esta
											traducir = false;
									}
									else//Traduce todos
										traducir = true;
										
									if(traducir){
										textoTrad = traduce.traduce(texto, idiomaOri, idiomaTrad);
										idioma = idiomaTrad;
									}
									langStringvo.setIdioma(idioma);
									langStringvo.setTexto(textoTrad);
									arrDesc.add(langStringvo);
								}
								LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
								desc.setTextos(textos);
							}
						}
						cadaAnotacion.setDescripcion(desc);
						
						//Añandimos el resto de campos que no se traducen
						cadaAnotacion.setEntidad(avAnn[i].getEntidad());
						arrAnotacionesTrad.add(cadaAnotacion);
					}
					AvAnnotationVO[] anotaciones = arrAnotacionesTrad.toArray(new AvAnnotationVO[arrAnotacionesTrad.size()]);
					/////////////////////// Fin traduccion Categoria Anotacion
					lomAvTrad.setAnotaciones(anotaciones);
				}
				//Comienzo set Clasificacion
				if(lomAv.getClasificaciones() != null){
					ArrayList<AvClassificationVO> arrClasTrad = new ArrayList<AvClassificationVO>();
					AvClassificationVO[] avClas = lomAv.getClasificaciones();
					for(int i = 0; i < avClas.length; i++){//Cada Clasificacion
						AvClassificationVO cadaClasificacion = new AvClassificationVO();
						//Descripcion de la Clasificacion
						DescripcionVO desc = null;
						if(avClas[i].getDescripcion() != null){
							desc = new DescripcionVO();
							if(avClas[i].getDescripcion().getTextos() != null){
								ArrayList<LangStringVO> arrDesc = new ArrayList<LangStringVO>();
								LangStringVO[] descripciones = avClas[i].getDescripcion().getTextos();
								for(int j = 0; j < descripciones.length; j++){
									String texto = descripciones[j].getTexto();
									String idioma = descripciones[j].getIdioma();
									LangStringVO langStringvo = new LangStringVO();
									String textoTrad = texto;
									boolean traducir = false;
									if(soloCoincidentes){//Traduce solo los coincidentes
										if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
											traducir = true;
										else//Si no coincide, dejalo como esta
											traducir = false;
									}
									else//Traduce todos
										traducir = true;
										
									if(traducir){
										textoTrad = traduce.traduce(texto, idiomaOri, idiomaTrad);
										idioma = idiomaTrad;
									}
									langStringvo.setIdioma(idioma);
									langStringvo.setTexto(textoTrad);
									arrDesc.add(langStringvo);
								}
								LangStringVO[] textos = arrDesc.toArray(new LangStringVO[arrDesc.size()]);
								desc.setTextos(textos);
							}
						}
						cadaClasificacion.setDescripcion(desc);
						//Palabras Clave
						if(avClas[i].getPalabrasClave() != null){
							ArrayList<PalabraClaveVO> arrPalClave = new ArrayList<PalabraClaveVO>();
							PalabraClaveVO[] palClave = avClas[i].getPalabrasClave();
							for(int j = 0; j < palClave.length; j++){//Cada Palabra Clave
								ArrayList<LangStringVO> arrPal = new ArrayList<LangStringVO>();
								PalabraClaveVO pal = new PalabraClaveVO();
								if(palClave[j].getTextos() != null){
									LangStringVO[] palabras = palClave[j].getTextos();
									for(int k = 0; k < palabras.length; k++){
										String texto = palabras[k].getTexto();
										String idioma = palabras[k].getIdioma();
										LangStringVO langStringvo = new LangStringVO();
										String textoTrad = texto;
										boolean traducir = false;
										if(soloCoincidentes){//Traduce solo los coincidentes
											if(idioma == null || idioma.equals(idiomaOri) || idioma.equals(""))//Si coincide, traducelo
												traducir = true;
											else//Si no coincide, dejalo como esta
												traducir = false;
										}
										else//Traduce todos
											traducir = true;
											
										if(traducir){
											textoTrad = traduce.traduce(texto, idiomaOri, idiomaTrad);
											idioma = idiomaTrad;
										}
										langStringvo.setIdioma(idioma);
										langStringvo.setTexto(textoTrad);
										arrPal.add(langStringvo);
									}
									LangStringVO[] textos = arrPal.toArray(new LangStringVO[arrPal.size()]);
									pal.setTextos(textos);
								}
								arrPalClave.add(pal);
							}
							PalabraClaveVO[] palabrasTrad = arrPalClave.toArray(new PalabraClaveVO[arrPalClave.size()]);
							cadaClasificacion.setPalabrasClave(palabrasTrad);
						}///Fin Palabras Clave
						
						//Metemos el resto de campos de Clasificacion que no se traducen
						cadaClasificacion.setRutasTaxonomicas(avClas[i].getRutasTaxonomicas());
						cadaClasificacion.setProposito(avClas[i].getProposito());
						arrClasTrad.add(cadaClasificacion);
					}
					AvClassificationVO[] clasificaciones = arrClasTrad.toArray(new AvClassificationVO[arrClasTrad.size()]);
					/////////////////////// Fin traduccion Categoria Clasificacion
					lomAvTrad.setClasificaciones(clasificaciones);
				}
				
			}
		} catch (Exception e) {
			logger.error("Error en la traduccion " + e);
			throw new Exception(e);
		}//fin lomAvTrad no null
		
		return lomAvTrad;
	}
}