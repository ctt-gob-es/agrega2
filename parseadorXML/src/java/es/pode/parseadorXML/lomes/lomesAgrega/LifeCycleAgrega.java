package es.pode.parseadorXML.lomes.lomesAgrega;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.castor.ComplexTypeDateTimeDateTime;
import es.pode.parseadorXML.castor.ComplexTypeDateTimeDescription;
import es.pode.parseadorXML.castor.ComplexTypeRoleVocabSource;
import es.pode.parseadorXML.castor.ComplexTypeRoleVocabValue;
import es.pode.parseadorXML.castor.ComplexTypeStatusVocabSource;
import es.pode.parseadorXML.castor.ComplexTypeStatusVocabValue;
import es.pode.parseadorXML.castor.Contribute;
import es.pode.parseadorXML.castor.Date;
import es.pode.parseadorXML.castor.EntityUnbounded;
import es.pode.parseadorXML.castor.GroupContributeContribute;
import es.pode.parseadorXML.castor.GroupDateDate;
import es.pode.parseadorXML.castor.GroupEntityUnboundedEntity;
import es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle;
import es.pode.parseadorXML.castor.GroupRoleRole;
import es.pode.parseadorXML.castor.GroupStatusStatus;
import es.pode.parseadorXML.castor.GroupVersionVersion;
import es.pode.parseadorXML.castor.LanguageStringItem;
import es.pode.parseadorXML.castor.LifeCycle;
import es.pode.parseadorXML.castor.Role;
import es.pode.parseadorXML.castor.Status;
import es.pode.parseadorXML.castor.Version;
import es.pode.soporte.i18n.I18n;



public class LifeCycleAgrega {

	private LifeCycle lifeCycle = null;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	 public LifeCycleAgrega(LifeCycle lifeCycle) {
	        super();
	        
	        if (lifeCycle.getGroupLifeCycleLifeCycle()==null){
	        	GroupLifeCycleLifeCycle glc= new GroupLifeCycleLifeCycle();
	        	lifeCycle.setGroupLifeCycleLifeCycle(glc);
	        }
	        setLifeCycle(lifeCycle);

	    }
	 
	 //-----------/
     //- Methods -/
    //-----------/

    public LifeCycle getLifeCycle() {
		return lifeCycle;
	}
    
    public void setLifeCycle(LifeCycle lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
    
    // m�todo que recoge el rol de la contribuci�n en la posici�n indicada en el par�metro de entrada
    public String getRol(int contributeIndex) throws java.lang.Exception{
    	
    	String r= null;
    	
		try {
			r= lifeCycle.getGroupLifeCycleLifeCycle().getContribute(contributeIndex).getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent();
		} catch (Exception e){
			String mensaje = "Warning: No es posible recoger el rol en la contribuci�n de la posici�n: " +contributeIndex;
		    logger.warn(mensaje);
		    
		    //Devuelve null
		    return r;
		    //throw e;
		}
		return r;
	}
    
    
    // m�todo que recoge el Entity de la contribuci�n en la posici�n indicada en el par�metro de entrada
    public String getEntidad(int contributeIndex, int entityIndex) throws java.lang.Exception {
    	
    	String r= null;
    	
		try {
			r= lifeCycle.getGroupLifeCycleLifeCycle().getContribute(contributeIndex).getGroupContributeContribute().getEntityUnbounded(entityIndex).getGroupEntityUnboundedEntity().getContent();
		} catch (Exception e){
			String mensaje = "Warning: No es posible recoger la entidad:" +entityIndex+ " en la contribuci�n de la posici�n: " +contributeIndex;
		    logger.warn(mensaje);
		    
//		  Devuelve null
		    return r;
		    //throw e;
		}
		return r;
    }
    
//  m�todo que recoge todas las entidades participantes en la contribuci�n indicada en el par�metro de entrada
    public ArrayList getEntidades(int contributeIndex) throws java.lang.Exception {
    	
    	ArrayList en= new ArrayList();
    	
		try {
			EntityUnbounded[] eu= lifeCycle.getGroupLifeCycleLifeCycle().getContribute(contributeIndex).getGroupContributeContribute().getEntityUnbounded();
			
			if (eu != null){
				for (int i=0; i<eu.length; i++){
					en.add(lifeCycle.getGroupLifeCycleLifeCycle().getContribute(contributeIndex).getGroupContributeContribute().getEntityUnbounded(i).getGroupEntityUnboundedEntity().getContent());
				}
			}
			
		} catch (Exception e){
			String mensaje = "Warning: No es posible recoger las entidades en la contribuci�n de la posici�n: " +contributeIndex;
		    logger.warn(mensaje);
		    
		    en= new ArrayList();
//		  Devuelve lista vac�a
		    return en;
		    //throw e;
		}
		return en;
    }
    
    // m�todo que recoge la fecha de la contribuci�n indicada en el par�metro
    public String getFecha(int contributeIndex) throws java.lang.Exception{
    	
    	String f= null;
    	
		try {
			f= lifeCycle.getGroupLifeCycleLifeCycle().getContribute(contributeIndex).getGroupContributeContribute().getDate().getGroupDateDate().getComplexTypeDateTimeDateTime().getContent();
		} catch (Exception e){
			String mensaje = "Warning: No es posible recoger la fecha en la contribuci�n de la posici�n: " +contributeIndex;
		    logger.warn(mensaje);
		    
//		  Devuelve null
		    return f;
		    //throw e;
		}
		return f;
	}
	
    // m�todo que a�ade una nueva contribuci�n. Hay que indicar el rol, la entidad, la fecha y la descripci�n de la contribuci�n
    public void addContribucion(
    		java.lang.String rol, java.lang.String entidad, java.lang.String fecha, java.lang.String desc ) 
    throws java.lang.Exception{
    	
 	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
    	
    	Contribute c= new Contribute();
    	GroupContributeContribute gcc= new GroupContributeContribute();
    	Date d= new Date();
    	EntityUnbounded eu= new EntityUnbounded();
    	Role r= new Role();
    	
    	GroupDateDate gdd= new GroupDateDate();
    	ComplexTypeDateTimeDateTime ctdtt= new ComplexTypeDateTimeDateTime();
    	ComplexTypeDateTimeDescription ctdtd= new ComplexTypeDateTimeDescription();
    	ctdtt.setContent(fecha);
    	LanguageStringItem lsi= new LanguageStringItem();
    	es.pode.parseadorXML.castor.String s= new es.pode.parseadorXML.castor.String();
    	s.setContent(desc);
    	lsi.setString(s);
    	ctdtd.addLanguageStringItem(lsi);
    	gdd.setComplexTypeDateTimeDateTime(ctdtt);
    	gdd.setComplexTypeDateTimeDescription(ctdtd);
    	
    	d.setGroupDateDate(gdd);
    	
    	GroupEntityUnboundedEntity geue= new GroupEntityUnboundedEntity();
    	geue.setContent(entidad);
    	
    	eu.setGroupEntityUnboundedEntity(geue);
    	
    	GroupRoleRole grr= new GroupRoleRole();
    	ComplexTypeRoleVocabSource ctrvs= new ComplexTypeRoleVocabSource();
    	ComplexTypeRoleVocabValue ctrvv= new ComplexTypeRoleVocabValue();
    	ctrvs.setContent(lomes);
    	ctrvv.setContent(rol);
    	grr.setComplexTypeRoleVocabSource(ctrvs);
    	grr.setComplexTypeRoleVocabValue(ctrvv);
    	r.setGroupRoleRole(grr);
    	
    	gcc.setDate(d);
    	gcc.addEntityUnbounded(eu);
    	gcc.setRole(r);
    	
    	c.setGroupContributeContribute(gcc);
    	
    	if (lifeCycle == null){
    		lifeCycle= new LifeCycle();
	    }
	    
	    if (lifeCycle.getGroupLifeCycleLifeCycle() == null){
	    	es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle glcAux= new es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle();
	    	lifeCycle.setGroupLifeCycleLifeCycle(glcAux);
	    }
    	
    	lifeCycle.getGroupLifeCycleLifeCycle().addContribute(c);
    }
    
//  m�todo que a�ade una contribuci�n de publicacion en primer lugar en el array de contribuciones. Hay que indicar la entidad y la fecha
    public void addContribucionPublicacion(java.lang.String entidad, java.lang.String fecha) 
    throws java.lang.Exception{
    	
    	Properties properties = new Properties(); 
    	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
 	   	properties.load(is);
 	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
 	   	String publicador= properties.getProperty("publicador");
 	   	
 	    Properties propFechas = new Properties();
	    String idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
	    InputStream is2 = this.getClass().getResourceAsStream("/descripciones_"+idioma+".properties");
	    propFechas.load(is2);
	   	
	   	String desPublicacion= propFechas.getProperty("desPublicacion");
    	
    	Contribute c= new Contribute();
    	GroupContributeContribute gcc= new GroupContributeContribute();
    	Date d= new Date();
    	EntityUnbounded eu= new EntityUnbounded();
    	Role r= new Role();
    	
    	GroupDateDate gdd= new GroupDateDate();
    	ComplexTypeDateTimeDateTime ctdtt= new ComplexTypeDateTimeDateTime();
    	ComplexTypeDateTimeDescription ctdtd= new ComplexTypeDateTimeDescription();
    	ctdtt.setContent(fecha);
    	LanguageStringItem lsi= new LanguageStringItem();
    	es.pode.parseadorXML.castor.String s= new es.pode.parseadorXML.castor.String();
    	s.setContent(desPublicacion);
    	lsi.setString(s);
    	ctdtd.addLanguageStringItem(lsi);
    	gdd.setComplexTypeDateTimeDateTime(ctdtt);
    	gdd.setComplexTypeDateTimeDescription(ctdtd);
    	
    	d.setGroupDateDate(gdd);
    	
    	GroupEntityUnboundedEntity geue= new GroupEntityUnboundedEntity();
    	geue.setContent(entidad);
    	
    	eu.setGroupEntityUnboundedEntity(geue);
    	
    	GroupRoleRole grr= new GroupRoleRole();
    	ComplexTypeRoleVocabSource ctrvs= new ComplexTypeRoleVocabSource();
    	ComplexTypeRoleVocabValue ctrvv= new ComplexTypeRoleVocabValue();
    	ctrvs.setContent(lomes);
    	ctrvv.setContent(publicador);
    	grr.setComplexTypeRoleVocabSource(ctrvs);
    	grr.setComplexTypeRoleVocabValue(ctrvv);
    	r.setGroupRoleRole(grr);
    	
    	gcc.setDate(d);
    	gcc.addEntityUnbounded(eu);
    	gcc.setRole(r);
    	
    	c.setGroupContributeContribute(gcc);
    	
    	if (lifeCycle == null){
    		lifeCycle= new LifeCycle();
	    }
	    
	    if (lifeCycle.getGroupLifeCycleLifeCycle() == null){
	    	es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle glcAux= new es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle();
	    	lifeCycle.setGroupLifeCycleLifeCycle(glcAux);
	    }
    	
	    if (lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length>0){
	    	ArrayList contribuciones= new ArrayList();
	    	contribuciones.add(c);
	    	for (int i= 0; i< lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length; i++){
	    		contribuciones.add(lifeCycle.getGroupLifeCycleLifeCycle().getContribute(i));
	    	}
	    	lifeCycle.getGroupLifeCycleLifeCycle().setContribute((Contribute[]) contribuciones.toArray(new Contribute[contribuciones.size()]));
	    }
	    else{
	    	lifeCycle.getGroupLifeCycleLifeCycle().addContribute(c);
	    }
    	
    }
    
//    m�todo que a�ade una contribuci�n de creaci�n en primer lugar en el array de contribuciones. Hay que indicar la entidad y la fecha
    public void addContribucionCreacion(java.lang.String entidad, java.lang.String fecha) 
    throws java.lang.Exception{
    	
    	Properties properties = new Properties(); 
    	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
 	   	properties.load(is);
 	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
 	   	String autor= properties.getProperty("autor");
 	   	
 	    Properties propFechas = new Properties();
 	    String idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
 	    InputStream is2 = this.getClass().getResourceAsStream("/descripciones_"+idioma+".properties");
 	    propFechas.load(is2);
 	   	
 	   	String desCreacion= propFechas.getProperty("desCreacion");
    	
    	Contribute c= new Contribute();
    	GroupContributeContribute gcc= new GroupContributeContribute();
    	Date d= new Date();
    	EntityUnbounded eu= new EntityUnbounded();
    	Role r= new Role();
    	
    	GroupDateDate gdd= new GroupDateDate();
    	ComplexTypeDateTimeDateTime ctdtt= new ComplexTypeDateTimeDateTime();
    	ComplexTypeDateTimeDescription ctdtd= new ComplexTypeDateTimeDescription();
    	ctdtt.setContent(fecha);
    	LanguageStringItem lsi= new LanguageStringItem();
    	es.pode.parseadorXML.castor.String s= new es.pode.parseadorXML.castor.String();
    	s.setContent(desCreacion);
    	lsi.setString(s);
    	ctdtd.addLanguageStringItem(lsi);
    	gdd.setComplexTypeDateTimeDateTime(ctdtt);
    	gdd.setComplexTypeDateTimeDescription(ctdtd);
    	
    	d.setGroupDateDate(gdd);
    	
    	GroupEntityUnboundedEntity geue= new GroupEntityUnboundedEntity();
    	geue.setContent(entidad);
    	
    	eu.setGroupEntityUnboundedEntity(geue);
    	
    	GroupRoleRole grr= new GroupRoleRole();
    	ComplexTypeRoleVocabSource ctrvs= new ComplexTypeRoleVocabSource();
    	ComplexTypeRoleVocabValue ctrvv= new ComplexTypeRoleVocabValue();
    	ctrvs.setContent(lomes);
    	ctrvv.setContent(autor);
    	grr.setComplexTypeRoleVocabSource(ctrvs);
    	grr.setComplexTypeRoleVocabValue(ctrvv);
    	r.setGroupRoleRole(grr);
    	
    	gcc.setDate(d);
    	gcc.addEntityUnbounded(eu);
    	gcc.setRole(r);
    	
    	c.setGroupContributeContribute(gcc);
    	
    	if (lifeCycle == null){
    		lifeCycle= new LifeCycle();
	    }
	    
	    if (lifeCycle.getGroupLifeCycleLifeCycle() == null){
	    	es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle glcAux= new es.pode.parseadorXML.castor.GroupLifeCycleLifeCycle();
	    	lifeCycle.setGroupLifeCycleLifeCycle(glcAux);
	    }
    	
	    if (lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length>0){
	    	ArrayList contribuciones= new ArrayList();
	    	contribuciones.add(c);
	    	for (int i= 0; i< lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length; i++){
	    		contribuciones.add(lifeCycle.getGroupLifeCycleLifeCycle().getContribute(i));
	    	}
	    	lifeCycle.getGroupLifeCycleLifeCycle().setContribute((Contribute[]) contribuciones.toArray(new Contribute[contribuciones.size()]));
	    }
	    else{
	    	lifeCycle.getGroupLifeCycleLifeCycle().addContribute(c);
	    }
    	
    }
    
    public void setContribucion(
            final int index, java.lang.String rol, java.lang.String entidad, java.lang.String fecha, java.lang.String desc, java.lang.String idioma)
    throws java.lang.Exception {
        
        try{
        	
        	if ((lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length>0) && (index<lifeCycle.getGroupLifeCycleLifeCycle().getContribute().length)){
        		lifeCycle.getGroupLifeCycleLifeCycle().getContribute(index).getGroupContributeContribute().getDate().getGroupDateDate().getComplexTypeDateTimeDateTime().setContent(fecha);
        		LanguageStringItem lsi= new LanguageStringItem();
        		es.pode.parseadorXML.castor.String s= new es.pode.parseadorXML.castor.String();
        		s.setContent(desc);
        		s.setLanguage(idioma);
        		lsi.setString(s);
        		lifeCycle.getGroupLifeCycleLifeCycle().getContribute(index).getGroupContributeContribute().getDate().getGroupDateDate().getComplexTypeDateTimeDescription().setLanguageStringItem(0, lsi);
        		lifeCycle.getGroupLifeCycleLifeCycle().getContribute(index).getGroupContributeContribute().getEntityUnbounded(0).getGroupEntityUnboundedEntity().setContent(entidad);
        		lifeCycle.getGroupLifeCycleLifeCycle().getContribute(index).getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().setContent(rol);
        	}
        	else{
        		this.addContribucion(rol, entidad, fecha, desc);
        	}
        	
        }
        catch (Exception e){
        	String mensaje = "Error: No es posible insertar el contexto en la posici�n "+ index;
 		    logger.error(mensaje);
 		    throw e;
        } 
    }
    
    // m�todo que recoge todos los autores que est�n indicados en la categor�a LifeCycle
    public ArrayList getAutores() {
    	
    	ArrayList autores= new ArrayList();
    	
    	try{
    		Contribute[] c= lifeCycle.getGroupLifeCycleLifeCycle().getContribute();
        	if (c!=null){
        		int i= 0;
        		while ((i<c.length)){
        			if ("author".equals(c[i].getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent())){
        				EntityUnbounded eu[]= c[i].getGroupContributeContribute().getEntityUnbounded();
        				if (eu!= null){
        					for (int j= 0; j<eu.length; j++){
        						String contenido= eu[j].getGroupEntityUnboundedEntity().getContent();
        						autores.add(interpretaVCard(contenido));
        					}
        				}
        			}
        			i++;
        		}
        	}
    	}
    	catch (Exception e){
    		String mensaje = "Warning: No es posible recoger los autores del ODE";
 		    logger.warn(mensaje);
 		    
 		    autores= new ArrayList();
 		    return autores;
 		    
    	}
    	
    	
		return autores;
	}
    public ArrayList getRevisores(){
    	ArrayList revisores=new ArrayList();
    	try{
    		Contribute[] c= lifeCycle.getGroupLifeCycleLifeCycle().getContribute();
        	if (c!=null){
        		int i= 0;
        		while ((i<c.length)){
        			if ("validator".equals(c[i].getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent())){
        				EntityUnbounded eu[]= c[i].getGroupContributeContribute().getEntityUnbounded();
        				if (eu!= null){
        					for (int j= 0; j<eu.length; j++){
        						String contenido= eu[j].getGroupEntityUnboundedEntity().getContent();
        						revisores.add(interpretaVCard(contenido));
        					}
        				}
        			}
        			i++;
        		}
        	}
    	}catch(Exception e){
    		String mensaje = "Warning: No es posible recoger los revisores del ODE";
 		    logger.warn(mensaje);
 		    
 		    revisores= new ArrayList();
 		    return revisores;
 		    
    	}
    	return revisores;
    }
    
    public ArrayList getPublicadores(){
    	ArrayList publicadores=new ArrayList();
    	try{
    		Contribute[] c= lifeCycle.getGroupLifeCycleLifeCycle().getContribute();
        	if (c!=null){
        		int i= 0;
        		while ((i<c.length)){
        			if ("publisher".equals(c[i].getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent())){
        				EntityUnbounded eu[]= c[i].getGroupContributeContribute().getEntityUnbounded();
        				if (eu!= null){
        					for (int j= 0; j<eu.length; j++){
        						String contenido= eu[j].getGroupEntityUnboundedEntity().getContent();
        						publicadores.add(interpretaVCard(contenido));
        					}
        				}
        			}
        			i++;
        		}
        	}
    	}catch(Exception e){
    		String mensaje = "Warning: No es posible recoger los publicadores del ODE";
 		    logger.warn(mensaje);
 		    
 		    publicadores= new ArrayList();
 		    return publicadores;
 		    
    	}
    	return publicadores;
    }
    public java.lang.String getFechaPublicacion() {
    	
    	java.lang.String fecha= null;
    	
    	Contribute[] c= lifeCycle.getGroupLifeCycleLifeCycle().getContribute();
    	if (c!=null){
    		boolean encontrado= false;
    		int i= 0;
    		while ((i<c.length) && (encontrado == false)){
    			if ("publisher".equals(c[i].getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent())){
    				encontrado= true;
    				fecha= c[i].getGroupContributeContribute().getDate().getGroupDateDate().getComplexTypeDateTimeDateTime().getContent();
    			}
    			i++;
    		}
    	}
    	
		return fecha;
	}
    
    public EntidadAgrega interpretaVCard (java.lang.String vcard) throws java.lang.Exception{
    	
    	EntidadAgrega ent = new EntidadAgrega();
    	
    	String nombre = null;
    	String correo = null;
    	String organizacion = null;
    	
    	Properties properties = new Properties(); 
    	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
 	   	properties.load(is);
 	   	String cabeceraVCard= properties.getProperty("cabeceraVCard");
 	    String inicioEmail= properties.getProperty("inicioEmail");
 	    String inicioOrg= properties.getProperty("inicioOrg");
 	    String inicioEnd= properties.getProperty("inicioEnd"); 	
 	    
 	   if ((vcard != null) && (vcard.startsWith(cabeceraVCard))){
	   		
	   		int longCabecera= cabeceraVCard.length();  	
	   		String sAux= vcard.substring(longCabecera).trim();
	   		
	   		//obtenemos el nombre
	   		int indiceEmail= sAux.indexOf(inicioEmail);
	   		
	   		if (indiceEmail == -1){//para evitar null pointer
	   			//no tenemos un correo
	   			correo = "";
	   			ent.setCorreo(correo);
	   			
	   			int indiceOrg = sAux.indexOf(inicioOrg);
	   			
	   			if (indiceOrg == -1){
	   				//tampoco tenemos organizaci�n
	   				organizacion = "";
	   				ent.setOrganizacion(organizacion);
	   				
	   				int indiceEnd = sAux.indexOf(inicioEnd);
	   				if (indiceEnd == -1){
	   					
	   					//la VCard no est� bien formada
	   					//sacamos un error o pasamos los par�metros con valores vac�os
	   					nombre = "";
	   					ent.setNombre(nombre);
	   				}else{
	   					
	   					//tenemos solo el nombre
	   					nombre = sAux.substring(0, indiceEnd).trim();
	   					ent.setNombre(nombre);
	   				}	   				
	   			}else{
	   				
	   				//tenemos la organizaci�n y el nombre
	   				nombre = sAux.substring(0, indiceOrg).trim();
	   				ent.setNombre(nombre);
	   				
	   				//obtenemos la organizaci�n
	   				//int longNombre = nombre.length();
	   				sAux = sAux.substring(indiceOrg).trim(); //quitamos el nombre
	   				//eliminamos la etiqueta ORG
	   				int longOrg = inicioOrg.length();
	   				sAux = sAux.substring(longOrg).trim();
	   				
	   				int indiceEnd = sAux.indexOf(inicioEnd);
	   				if (indiceEnd == -1){
	   					
	   					//la VCard no est� bien formada
	   					//sacamos un error o pasamos los par�metros con valores vac�os
	   					organizacion = "";
	   					ent.setOrganizacion(organizacion);
	   				}else{
	   					
	   					//tenemos solo el nombre y la organizacion
	   					organizacion = sAux.substring(0, indiceEnd).trim();
	   					ent.setOrganizacion(organizacion);
	   				}	   		
	   				
	   			}
	   		}else{
	   			
	   			//por ahora tenemos el nombre y el correo
	   			nombre= sAux.substring(0, indiceEmail).trim();
	   			ent.setNombre(nombre);
	   		
	   			//obtenemos el email
	   			//primero eliminamos el nombre
	   			//int longNombre = nombre.length();
	   			sAux = sAux.substring(indiceEmail).trim();
	   			
	   			//eliminamos la etiqueta EMAIL
	 	   		int longEmail = inicioEmail.length();
	 	   		sAux = sAux.substring(longEmail);
	 	   		
	 	   		//delimitamos para extraer el correo
		   		int indiceOrg= sAux.indexOf(inicioOrg);	
		   		
		   		//para evitar null pointer comprobamos que existe la etiqueta
		   		//pero hay que definir si las etiquetas son obligatorias o no.
		   		if (indiceOrg == -1){
	   				//no tenemos organizaci�n
	   				organizacion = "";
	   				ent.setOrganizacion(organizacion);
	   				
	   				int indiceEnd = sAux.indexOf(inicioEnd);
	   				if (indiceEnd == -1){
	   					
	   					//la VCard no est� bien formada
	   					//sacamos un error o pasamos los par�metros con valores vac�os
	   					correo = "";
	   					ent.setCorreo(correo);
	   				}else{
	   					
	   					//tenemos el nombre y el correo
	   					correo = sAux.substring(0, indiceEnd).trim();
	   					ent.setCorreo(correo);
	   				}	   				
	   			}else{
	   				
	   				//tenemos tambi�n la organizaci�n, luego lo tenemos todo
	   				correo = sAux.substring(0, indiceOrg).trim();
	   				ent.setCorreo(correo);
	   				
	   				//obtenemos la organizaci�n
	   				//int longCorreo = correo.length();
	   				sAux = sAux.substring(indiceOrg).trim(); //quitamos el correo
	   				//eliminamos la etiqueta ORG
	   				int longOrg = inicioOrg.length();
	   				sAux = sAux.substring(longOrg).trim();
	   				
	   				int indiceEnd = sAux.indexOf(inicioEnd);
	   				if (indiceEnd == -1){
	   					
	   					//la VCard no est� bien formada
	   					//sacamos un error o pasamos los par�metros con valores vac�os
	   					organizacion = "";
	   					ent.setOrganizacion(organizacion);
	   				}else{
	   					
	   					//tenemos todos los campos
	   					organizacion = sAux.substring(0, indiceEnd).trim();
	   					ent.setOrganizacion(organizacion);
	   				}	   		
	   			}
	   		}
	   	}else{
	   		//sacar�amos un error mostrando que el formato del argumento no es v�lido 
	   		//o pasamos los par�metros en blanco
	   		ent.setCorreo("");
	   		ent.setNombre("");
	   		ent.setOrganizacion("");
	   		
	   	}
    	return ent;
    	
    }
    
public String escribirVCard (EntidadAgrega ent) throws java.lang.Exception{
    	
    	String vcard = null;
    	
    	String nombre = ent.getNombre();
    	String correo = ent.getCorreo();
    	String organizacion = ent.getOrganizacion();
    	
    	Properties properties = new Properties(); 
    	InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
 	   	properties.load(is);
 	   	String cabeceraVCard= properties.getProperty("cabeceraVCard");
 	   	String inicioColaVCard= properties.getProperty("inicioColaVCard");
 	    String inicioEmail= properties.getProperty("inicioEmail");
 	    String inicioOrg= properties.getProperty("inicioOrg");
 	    String inicioEnd= properties.getProperty("inicioEnd"); 	
 	    
 	   vcard = cabeceraVCard + " ";
	    if (nombre == null || nombre.equals("")){
	    	vcard = vcard + inicioEmail;
	    }else{
	    	
	    	vcard = vcard + nombre + " " + inicioEmail;
	    }
	    
	    if (correo == null || correo.equals("")){
	    	
	    	vcard = vcard + inicioOrg;
	    }else{
	    	
	    	vcard = vcard + correo + " " + inicioOrg; 
	    }
	    
	    if (organizacion == null || organizacion.equals("")){
	    	
	    	vcard = vcard + " " + inicioEnd;
	    }else{
	    	
	    	vcard = vcard + organizacion + " " + inicioEnd;
	    }
 	    
 	    return vcard;	
    }


    /*****************************M�todos para Catalogador Avanzado********************************/
    
    public ArrayList getVersionAv(){
    	ArrayList lVersiones=new ArrayList();
    	try{
    		Version versiones = lifeCycle.getGroupLifeCycleLifeCycle().getVersion();
    		if(versiones!=null){
		    	LanguageStringItem[] lsi = versiones.getGroupVersionVersion().getLanguageStringItem();
		    	if((lsi!=null)&&(lsi.length>0)){
		    		for(int i=0;i<lsi.length;i++){
		    			LangStringAgrega version=new LangStringAgrega();
		        		version.setValor(lsi[i].getString().getContent());
		        		version.setIdioma(lsi[i].getString().getLanguage());
		        		lVersiones.add(version);
		        	}
		    		
		    	}
    		}
    	}catch (Exception e){
    		String mensaje = "Warning: No es posible recoger la version " ;
		    logger.warn(mensaje);
		    
		    return lVersiones;
		    //throw e;
    	}
    	
    	return lVersiones;
    }
    
    
    public String getEstatusAv(){
    	String status=null;
    	try{
    		Status esta = lifeCycle.getGroupLifeCycleLifeCycle().getStatus();
    		if(esta!=null)
    			status=esta.getGroupStatusStatus().getComplexTypeStatusVocabValue().getContent();
    		
    	}catch (Exception e){
    		String mensaje = "Warning: No es posible recoger el estatus " ;
		    logger.warn(mensaje);
		    
		    return status;
		    //throw e;
    	}
    	return status;
	
    }
    
    public ArrayList getContribucionesAv(){
    	ArrayList lContribuciones=new ArrayList();

    	try{
    		Contribute[] lContri=lifeCycle.getGroupLifeCycleLifeCycle().getContribute();
    		
    		if ((lContri != null)&&(lContri.length>0)){
    			for (int i= 0; i<lContri.length; i++){
    				ContribucionAgrega contribucion=new ContribucionAgrega();
    				FechaAgrega tiempo=new FechaAgrega();
    				if(lContri[i].getGroupContributeContribute().getRole()!=null)
    					contribucion.setTipo(lContri[i].getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent());
    				
    				EntityUnbounded[] lEntity = lContri[i].getGroupContributeContribute().getEntityUnbounded();
    				if((lEntity !=null)&&(lEntity.length>0)){
    					ArrayList lEntidades=new ArrayList();
    					int cont= 0;
	    				while (cont<lEntity.length){
	    					//ya tenemos el objeto a desglosar en 3 con el getContent y llamamos al interpretaVCard
	    					//para pasarlo a EntidadAgrega
	    					EntidadAgrega ent = new EntidadAgrega();
	    					
	    					ent = interpretaVCard(lEntity[cont].getGroupEntityUnboundedEntity().getContent());
	    					lEntidades.add(ent);
	    					
	    					cont++;
	    				}
	    				contribucion.setEntidades(lEntidades);
    					
    				}
    				
    				Date date = lContri[i].getGroupContributeContribute().getDate();
    				if(date!=null){
    					if(date.getGroupDateDate().getComplexTypeDateTimeDateTime()!=null)
    						tiempo.setFecha(date.getGroupDateDate().getComplexTypeDateTimeDateTime().getContent());
    					
    					LanguageStringItem[] lsi=null;
    					if(date.getGroupDateDate().getComplexTypeDateTimeDescription()!=null)
    				
    						lsi = date.getGroupDateDate().getComplexTypeDateTimeDescription().getLanguageStringItem();
	    				if((lsi != null) && (lsi.length>0)){
	    					int cont= 0;
	    					ArrayList lDesc=new ArrayList();
	    					while (cont<lsi.length){
	    						
	    						LangStringAgrega lString=new LangStringAgrega();
	    						lString.setValor(lsi[cont].getString().getContent());
	    						lString.setIdioma(lsi[cont].getString().getLanguage());
	    						lDesc.add(lString);
	    						cont++;
	    					}
	    					tiempo.setDescripciones(lDesc);
	    				}
    				}
    				contribucion.setFecha(tiempo);
    				lContribuciones.add(contribucion);
    			}
    			
    		}
    		
    	} catch (Exception e){
    		String mensaje = "Warning: No es posible recoger las contribuciones del ciclo de vida";
		    logger.warn(mensaje);
		    
		    lContribuciones= new ArrayList();
		    return lContribuciones;
		    //throw e;
    	}
    	
    	return lContribuciones;
    }
    
    /********************Los m�todos SET del Avanzado******************/
    
    public void setVersionAv(ArrayList version) throws Exception{
    	try{
    		Version versions=new Version();
			GroupVersionVersion gvv=new GroupVersionVersion();
			ArrayList lista=new ArrayList();
    		if((version!=null)&&(version.size()>0)){
    			for(int i=0;i<version.size();i++){
    				
    				es.pode.parseadorXML.castor.String string=new es.pode.parseadorXML.castor.String();
    				LanguageStringItem lsi=new LanguageStringItem();
    				String idioma=((LangStringAgrega)(version.get(i))).getIdioma();
    				String valor=((LangStringAgrega)(version.get(i))).getValor();
    				string.setContent(valor);
    				string.setLanguage(idioma);
    				lsi.setString(string);
    				lista.add(lsi);
    			}
    		}
    		LanguageStringItem[] langList=(LanguageStringItem[])lista.toArray(new LanguageStringItem[lista.size()]);
    		gvv.setLanguageStringItem(langList);
			versions.setGroupVersionVersion(gvv);
			lifeCycle.getGroupLifeCycleLifeCycle().setVersion(versions);
    	}catch (Exception e){
	       	String mensaje = "Error: No es posible insertar las versiones";
		    logger.error(mensaje);
		    throw e;
	    } 
    }
    
    public void setEstatusAv(String estatus) throws Exception{
    	try{
    		Status status=null;

		   	String estatusProp = null;

		   	estatusProp =AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);

    		if(estatus!=null){
    			status=new Status();
    			GroupStatusStatus gss=new GroupStatusStatus();
    			ComplexTypeStatusVocabSource ctsvs=new ComplexTypeStatusVocabSource();
    			ComplexTypeStatusVocabValue ctsvv=new ComplexTypeStatusVocabValue();
    			ctsvs.setContent(estatusProp);
    			ctsvv.setContent(estatus);
    			gss.setComplexTypeStatusVocabSource(ctsvs);
    			gss.setComplexTypeStatusVocabValue(ctsvv);
    			status.setGroupStatusStatus(gss);
    		}
    		lifeCycle.getGroupLifeCycleLifeCycle().setStatus(status);
    		
    	}catch (Exception e){
	       	String mensaje = "Error: No es posible insertar es estatus";
		    logger.error(mensaje);
		    throw e;
	    } 
    }
    
    public void setContribucionesAv(ArrayList contribuciones) throws Exception{
    	try{
    		ArrayList lista=new ArrayList();
    		
 		    String contriProp = null;
 	
 		    contriProp = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
 		    
    		if((contribuciones!=null)&&(contribuciones.size()>0)){
    			
    			for(int i=0;i<contribuciones.size();i++){
    		 		Contribute contribute=new Contribute();
        			GroupContributeContribute gcmc=new GroupContributeContribute();
        		
    				ArrayList lEntidades=((ContribucionAgrega)(contribuciones.get(i))).getEntidades();
    				ArrayList lEnty=new ArrayList();
    				FechaAgrega fechaAgrega=((ContribucionAgrega)(contribuciones.get(i))).getFecha();
    				String tipo=((ContribucionAgrega)(contribuciones.get(i))).getTipo();
    				Role role=null;
    				if(tipo!=null){
	    				role=new Role();
	    				GroupRoleRole grmr=new GroupRoleRole();
	    				ComplexTypeRoleVocabValue ctrmvv=new ComplexTypeRoleVocabValue();
	    				ComplexTypeRoleVocabSource ctrmvs=new ComplexTypeRoleVocabSource();
	    				
	    				ctrmvv.setContent(tipo);
	    				ctrmvs.setContent(contriProp);
	    				grmr.setComplexTypeRoleVocabSource(ctrmvs);
	    				grmr.setComplexTypeRoleVocabValue(ctrmvv);
	    				role.setGroupRoleRole(grmr);
    				}
    				
    				if((lEntidades!=null)&&(lEntidades.size()>0)){
    					for(int j=0;j<lEntidades.size();j++){
	    					EntidadAgrega entidadAux = (EntidadAgrega)lEntidades.get(j);
	    					String entidad=	escribirVCard(entidadAux); //para separar el string y pasarlo a EntidadAgrega
	    					EntityUnbounded eu=new EntityUnbounded();
	    					GroupEntityUnboundedEntity geueu=new GroupEntityUnboundedEntity();
	    					geueu.setContent(entidad);
	    					eu.setGroupEntityUnboundedEntity(geueu);
	    					lEnty.add(eu);
    					}
    					
    					
    				}
    				EntityUnbounded[] lUn=(EntityUnbounded[])lEnty.toArray(new EntityUnbounded[lEnty.size()]);
    				
        			Date date=null;
    				if((fechaAgrega!=null)){
    					String data=fechaAgrega.getFecha();
    					ArrayList lDesFe=fechaAgrega.getDescripciones();
    					
    					ComplexTypeDateTimeDateTime ctdtdt=null;
    					if(fechaAgrega.getFecha()!=null){
    						ctdtdt = new ComplexTypeDateTimeDateTime();
    						ctdtdt.setContent(data);
    					}
    					
    					
    					
    					ComplexTypeDateTimeDescription ctdtd=null;
    					ArrayList listas=new ArrayList();
    					if((lDesFe!=null)&&(lDesFe.size()>0)){
    						
    						for(int j=0;j<lDesFe.size();j++){
    							LanguageStringItem lsi=new LanguageStringItem();
    							es.pode.parseadorXML.castor.String string=new es.pode.parseadorXML.castor.String();
    							String idioma=((LangStringAgrega)(lDesFe.get(j))).getIdioma();
    							String valor=((LangStringAgrega)(lDesFe.get(j))).getValor();
    							string.setContent(valor);
    							string.setLanguage(idioma);
    							lsi.setString(string);
    							listas.add(lsi);
    							
    						}
    						LanguageStringItem[] listas1=(LanguageStringItem[])listas.toArray(new LanguageStringItem[listas.size()]);
        					ctdtd = new ComplexTypeDateTimeDescription();
        					ctdtd.setLanguageStringItem(listas1);
        					
    					}
    					if(data != null && lDesFe != null){
    						GroupDateDate gdd=new GroupDateDate();
    						gdd.setComplexTypeDateTimeDateTime(ctdtdt);
    						gdd.setComplexTypeDateTimeDateTime(ctdtdt);
    						gdd.setComplexTypeDateTimeDescription(ctdtd);
    						
    						date = new Date();
    						date.setGroupDateDate(gdd);
    					}
    					
    				}
    				
    				gcmc.setDate(date);
    				gcmc.setRole(role);
    				gcmc.setEntityUnbounded(lUn);
    				contribute.setGroupContributeContribute(gcmc);
    				lista.add(contribute);
    			}
    		}
    		Contribute[] lContribute=(Contribute[])lista.toArray(new Contribute[lista.size()]);
    		lifeCycle.getGroupLifeCycleLifeCycle().setContribute(lContribute);
    	}catch (Exception e){
	       	String mensaje = "Error: No es posible insertar las contribuciones";
		    logger.error(mensaje);
		    throw e;
	    } 
    }
    
}
