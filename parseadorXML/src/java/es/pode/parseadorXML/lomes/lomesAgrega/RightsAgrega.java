package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.castor.Access;
import es.pode.parseadorXML.castor.AccessType;
import es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabSource;
import es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabValue;
import es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabSource;
import es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabValue;
import es.pode.parseadorXML.castor.ComplexTypeCostVocabSource;
import es.pode.parseadorXML.castor.ComplexTypeCostVocabValue;
import es.pode.parseadorXML.castor.CopyrightAndOtherRestrictions;
import es.pode.parseadorXML.castor.Cost;
import es.pode.parseadorXML.castor.Description;
import es.pode.parseadorXML.castor.GroupAccessAccess;
import es.pode.parseadorXML.castor.GroupAccessTypeAccessType;
import es.pode.parseadorXML.castor.GroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions;
import es.pode.parseadorXML.castor.GroupCostCost;
import es.pode.parseadorXML.castor.GroupDescriptionDescription;
import es.pode.parseadorXML.castor.GroupRightsRights;
import es.pode.parseadorXML.castor.LanguageStringItem;
import es.pode.parseadorXML.castor.Rights;

public class RightsAgrega {

	private Rights rights = null;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public RightsAgrega(Rights rights) {
		super();
		
		if (rights.getGroupRightsRights()== null){
			rights.setGroupRightsRights(new GroupRightsRights());
		}
		
		setRights(rights);  
	}

	public Rights getRights() {
		return rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}
	
	// método que recoge los derechos de autor
	public java.lang.String getDerechosDeAutor()
	throws java.lang.Exception {
		
		java.lang.String d= null;
		try{
			d= rights.getGroupRightsRights().getCopyrightAndOtherRestrictions().getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getContent();
			
		} catch (Exception e){
			String mensaje = "No es posible recoger los derechos de autor";
			logger.debug(mensaje);
			
			d= null;
			return d;
			//throw e;
		}
		
		return d;
	}
	
	public void setDerechosDeAutor(java.lang.String derechos)
	throws java.lang.Exception {
		
	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		
		CopyrightAndOtherRestrictions caor= new CopyrightAndOtherRestrictions();
		GroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions gcaor= new GroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions();
		ComplexTypeCopyrightAndOtherRestrictionsVocabSource ctcaorvs= new ComplexTypeCopyrightAndOtherRestrictionsVocabSource();
		ComplexTypeCopyrightAndOtherRestrictionsVocabValue ctcaorvv= new ComplexTypeCopyrightAndOtherRestrictionsVocabValue();
		
		ctcaorvs.setContent(lomes);
		ctcaorvv.setContent(derechos);
		gcaor.setComplexTypeCopyrightAndOtherRestrictionsVocabSource(ctcaorvs);
		gcaor.setComplexTypeCopyrightAndOtherRestrictionsVocabValue(ctcaorvv);
		caor.setGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions(gcaor);
		
		if (rights == null){
			rights= new Rights();
		}
		    
		if (rights.getGroupRightsRights() == null){
			GroupRightsRights grrAux= new GroupRightsRights();
			rights.setGroupRightsRights(grrAux);
		}
		
		rights.getGroupRightsRights().setCopyrightAndOtherRestrictions(caor);
		
	}
	
	public java.lang.String getCoste()
	throws java.lang.Exception {
		
		java.lang.String c= null;
		try{
			c= rights.getGroupRightsRights().getCost().getGroupCostCost().getComplexTypeCostVocabValue().getContent();
			
		} catch (Exception e){
			String mensaje = "No es posible recoger el coste de la categoría Rights";
			logger.debug(mensaje);
			
			c= null;
			return c;
			//throw e;
		}
		
		return c;
	}
	
	public void setCoste(java.lang.String coste)
	throws java.lang.Exception {
		
	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		
	   	Cost c= new Cost();
	   	GroupCostCost gcc= new GroupCostCost();
	   	ComplexTypeCostVocabSource ctcvs= new ComplexTypeCostVocabSource();
	   	ComplexTypeCostVocabValue ctcvv= new ComplexTypeCostVocabValue();
	    
	   	ctcvs.setContent(lomes);
	   	ctcvv.setContent(coste);
	   	gcc.setComplexTypeCostVocabSource(ctcvs);
	   	gcc.setComplexTypeCostVocabValue(ctcvv);
	   	c.setGroupCostCost(gcc);
	
		if (rights == null){
			rights= new Rights();
		}
		    
		if (rights.getGroupRightsRights() == null){
			GroupRightsRights grrAux= new GroupRightsRights();
			rights.setGroupRightsRights(grrAux);
		}
		
		rights.getGroupRightsRights().setCost(c);
		
	}
	
	public java.lang.String getTipoDeAcceso()
	throws java.lang.Exception {
		
		java.lang.String ta= null;
		try{
			ta= rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getAccessType().getGroupAccessTypeAccessType().getComplexTypeAccessTypeVocabValue().getContent();
			
		} catch (Exception e){
			String mensaje = "No es posible recoger el tipo de acceso de la categoría Rights";
			logger.debug(mensaje);
			
			ta= null;
			return ta;
			//throw e;
		}
		
		return ta;
	}
	
	public java.lang.String getDescripcionAcceso(java.lang.String idioma)
	throws java.lang.Exception {
		
		java.lang.String da= null;
		try{
			LanguageStringItem[] lsi= rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getDescription().getGroupDescriptionDescription().getLanguageStringItem();
			if ((lsi != null) && (lsi.length>0)){
	    		int cont= 0;
				boolean idiEncontrado= false;
				while ((cont<lsi.length) && (idiEncontrado== false)){
					if (idioma.equals(lsi[cont].getString().getLanguage())){
						idiEncontrado= true;
					}
					else{
						cont++;
					}
				}
				if ((idiEncontrado) && (cont<lsi.length)){
					da= lsi[cont].getString().getContent();
				}
				//si no encuentra el idioma buscado, devuelve el primero por defecto
				else{
					da= lsi[0].getString().getContent();
				}
	    	}
			
		} catch (Exception e){
			String mensaje = "No es posible recoger la descripción del tipo de acceso de la categoría Rights";
			logger.debug(mensaje);
			
			da= null;
			return da;
			//throw e;
		}
		
		return da;
	}
	
	public void setAcceso(java.lang.String tipoAcceso, java.lang.String desc)
	throws java.lang.Exception {
		
	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		
	   	Access a= new Access();
	   	GroupAccessAccess gaa= new GroupAccessAccess();
	   	
	   	AccessType at= new AccessType();
	   	GroupAccessTypeAccessType gat= new GroupAccessTypeAccessType();
	   	ComplexTypeAccessTypeVocabSource ctatvs= new ComplexTypeAccessTypeVocabSource();
	   	ComplexTypeAccessTypeVocabValue ctatvv= new ComplexTypeAccessTypeVocabValue();
	   	ctatvs.setContent(lomes);
	   	ctatvv.setContent(tipoAcceso);
	   	gat.setComplexTypeAccessTypeVocabSource(ctatvs);
	   	gat.setComplexTypeAccessTypeVocabValue(ctatvv);
	   	at.setGroupAccessTypeAccessType(gat);
	   	
	   	Description d= new Description();
	   	GroupDescriptionDescription gdd= new GroupDescriptionDescription();
	   	LanguageStringItem lsi= new LanguageStringItem();
	   	es.pode.parseadorXML.castor.String s= new es.pode.parseadorXML.castor.String();
	   	s.setContent(desc);
	   	lsi.setString(s);
	   	gdd.addLanguageStringItem(lsi);
	   	d.setGroupDescriptionDescription(gdd);
	
	   	gaa.setAccessType(at);
	   	gaa.setDescription(d);
	   	a.setGroupAccessAccess(gaa);
	
		if (rights == null){
			rights= new Rights();
		}
		    
		if (rights.getGroupRightsRights() == null){
			GroupRightsRights grrAux= new GroupRightsRights();
			rights.setGroupRightsRights(grrAux);
		}
		
		rights.getGroupRightsRights().setAccess(a);
		
	}
	
	 /*****************************Métodos para Catalogador Avanzado*******************************/

	
	public String getCosteAv() throws Exception{
//		String coste=getCoste();
		java.lang.String coste= null;
		try{
			if(rights.getGroupRightsRights().getCost()!=null){
				coste= rights.getGroupRightsRights().getCost().getGroupCostCost().getComplexTypeCostVocabValue().getContent();
			}
			
		} catch (Exception e){
			String mensaje = "No es posible recoger el coste de la categoría Rights";
			logger.debug(mensaje);
			
			coste= null;
			return coste;
			//throw e;
		}
		
		return coste;
	}
	
	public void setCosteAv(String coste) throws Exception{
//		this.setCoste(coste);
		
	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		
	   	Cost c= null;
	   	if(coste !=null){
		   	c= new Cost();
		   	GroupCostCost gcc= new GroupCostCost();
		   	ComplexTypeCostVocabSource ctcvs= new ComplexTypeCostVocabSource();
		   	ComplexTypeCostVocabValue ctcvv= new ComplexTypeCostVocabValue();
		    
		   	ctcvs.setContent(lomes);
		   	ctcvv.setContent(coste);
		   	gcc.setComplexTypeCostVocabSource(ctcvs);
		   	gcc.setComplexTypeCostVocabValue(ctcvv);
		   	c.setGroupCostCost(gcc);
		}
		if (rights == null){
			rights= new Rights();
		}
		    
		if (rights.getGroupRightsRights() == null){
			GroupRightsRights grrAux= new GroupRightsRights();
			rights.setGroupRightsRights(grrAux);
		}
		
		rights.getGroupRightsRights().setCost(c);
	}
	
	public String getDerechosDeAutorAv() throws Exception{
//		String derechos=getDerechosDeAutor();
		java.lang.String derechos= null;
		try{
			if(rights.getGroupRightsRights().getCopyrightAndOtherRestrictions()!=null)
				derechos= rights.getGroupRightsRights().getCopyrightAndOtherRestrictions().getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getContent();
			
		} catch (Exception e){
			String mensaje = "No es posible recoger los derechos de autor";
			logger.debug(mensaje);
			
			derechos= null;
			return derechos;
			//throw e;
		}
		
		return derechos;
	}

	public void setDerechosDeAutorAv(String derechosDeAutor) throws Exception{
//		this.setDerechosDeAutor(derechosDeAutor);
		
	   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
	   	CopyrightAndOtherRestrictions caor=null;
	   	if(derechosDeAutor!=null){
			caor= new CopyrightAndOtherRestrictions();
			GroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions gcaor= new GroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions();
			ComplexTypeCopyrightAndOtherRestrictionsVocabSource ctcaorvs= new ComplexTypeCopyrightAndOtherRestrictionsVocabSource();
			ComplexTypeCopyrightAndOtherRestrictionsVocabValue ctcaorvv= new ComplexTypeCopyrightAndOtherRestrictionsVocabValue();
			
			ctcaorvs.setContent(lomes);
			ctcaorvv.setContent(derechosDeAutor);
			gcaor.setComplexTypeCopyrightAndOtherRestrictionsVocabSource(ctcaorvs);
			gcaor.setComplexTypeCopyrightAndOtherRestrictionsVocabValue(ctcaorvv);
			caor.setGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions(gcaor);
	   	}
		if (rights == null){
			rights= new Rights();
		}
		    
		if (rights.getGroupRightsRights() == null){
			GroupRightsRights grrAux= new GroupRightsRights();
			rights.setGroupRightsRights(grrAux);
		}
		
		rights.getGroupRightsRights().setCopyrightAndOtherRestrictions(caor);
	}
	
	public ArrayList getDescripcionesAv(){
		ArrayList lDescrip=new ArrayList();
		try{
			 Description desc = rights.getGroupRightsRights().getDescription();
			 if(desc!=null){
				LanguageStringItem[] lsi =desc.getGroupDescriptionDescription().getLanguageStringItem();
				if((lsi!=null)&&(lsi.length>0)){
					int cont= 0;
					while (cont<lsi.length){
						LangStringAgrega lString=new LangStringAgrega();
						lString.setValor(lsi[cont].getString().getContent());
						lString.setIdioma(lsi[cont].getString().getLanguage());
						lDescrip.add(lString);
						cont++;
					}
				}
			 }
		}catch (Exception e){
    		String mensaje = "No es posible recoger las descripciones de los derechos";
		    logger.debug(mensaje);
		    
		    lDescrip= new ArrayList();
		    return lDescrip;
		    //throw e;
    	}
		return lDescrip;
		
	}
	
	public void setDescripcionesAv(ArrayList lDescripciones) throws Exception{
		if (lDescripciones != null && lDescripciones.size()>0){
			ArrayList lDesc = new ArrayList();
					
			for (int i = 0; i < lDescripciones.size(); i++){   
			   LangStringAgrega lsa = (LangStringAgrega) lDescripciones.get(i);
			   String valor = lsa.getValor();
			   String idioma = lsa.getIdioma();
			   
			   es.pode.parseadorXML.castor.String s = new es.pode.parseadorXML.castor.String();
			   s.setContent(valor);
			   s.setLanguage(idioma);
			   
			   LanguageStringItem lsi= new LanguageStringItem();
			   lsi.setString(s);
			   lDesc.add(lsi);
		   }
		   GroupDescriptionDescription gdd = new GroupDescriptionDescription();
		   gdd.setLanguageStringItem((LanguageStringItem[])lDesc.toArray(new LanguageStringItem[lDesc.size()]));
		   Description descripcion = new Description();
		   descripcion.setGroupDescriptionDescription(gdd);
		   rights.getGroupRightsRights().setDescription(descripcion);
	   }
	}
	
	public AccesoAgrega getAccesoAv(){
		
		AccesoAgrega accesoA=new AccesoAgrega();
		ArrayList l=new ArrayList();
		try{
			if(rights.getGroupRightsRights().getAccess()!=null){
				if(rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getAccessType()!=null){
					String accesType=rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getAccessType().getGroupAccessTypeAccessType().getComplexTypeAccessTypeVocabValue().getContent();
					accesoA.setTipoAcceso(accesType);
				}
				if(rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getDescription()!=null){
					LanguageStringItem[] lsi = rights.getGroupRightsRights().getAccess().getGroupAccessAccess().getDescription().getGroupDescriptionDescription().getLanguageStringItem();
					if((lsi !=null)&&(lsi.length>0)){
						int cont= 0;
						while (cont<lsi.length){
							LangStringAgrega lDesc=new LangStringAgrega();

							lDesc.setValor(lsi[cont].getString().getContent());
							lDesc.setIdioma(lsi[cont].getString().getLanguage());
							l.add(lDesc);
							cont++;
						}
						accesoA.setDescripcion(l); 
					}
				}
			 }

		}catch (Exception e){
    		String mensaje = "No es posible recoger los accesos de los derechos";
		    logger.debug(mensaje);
		    
		    accesoA= new AccesoAgrega();
		    return accesoA;
		    //throw e;
    	}
		return accesoA;
	}
	
	public void setAccesoAv(AccesoAgrega acceso) throws Exception{
		if (acceso != null){
			//tipo acceso

		   	String lomes= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
			
			String sTipoAcceso = acceso.getTipoAcceso();
			ArrayList lDescripciones = acceso.getDescripcion();
			
				AccessType tipoAcceso =null;
				if(sTipoAcceso !=null){
					ComplexTypeAccessTypeVocabSource ctatvs = new ComplexTypeAccessTypeVocabSource();
					ComplexTypeAccessTypeVocabValue ctatvv = new ComplexTypeAccessTypeVocabValue();
					ctatvs.setContent(lomes);
					ctatvv.setContent(sTipoAcceso);
					GroupAccessTypeAccessType gatat= new GroupAccessTypeAccessType();
					gatat.setComplexTypeAccessTypeVocabSource(ctatvs);
					gatat.setComplexTypeAccessTypeVocabValue(ctatvv);
					tipoAcceso = new AccessType();
					tipoAcceso.setGroupAccessTypeAccessType(gatat);
				}
				Description descripcion =null;
				if (lDescripciones != null){
					//descripciones
					ArrayList lDesc = new ArrayList();
			
					for (int i = 0; i < lDescripciones.size(); i++){   
						LangStringAgrega lsa = (LangStringAgrega) lDescripciones.get(i);
						
						String valor = lsa.getValor();
						if(valor !=null && !valor.equals("")){
							String idioma = lsa.getIdioma();
		   
							es.pode.parseadorXML.castor.String s = new es.pode.parseadorXML.castor.String();
							s.setContent(valor);
							s.setLanguage(idioma);
		   
							LanguageStringItem lsi= new LanguageStringItem();
							lsi.setString(s);
							lDesc.add(lsi);
						}
					}
					if (lDesc.size()>0){
						GroupDescriptionDescription gdd = new GroupDescriptionDescription();
						gdd.setLanguageStringItem((LanguageStringItem[])lDesc.toArray(new LanguageStringItem[lDesc.size()]));
						descripcion = new Description();
						descripcion.setGroupDescriptionDescription(gdd);
					}
				}
				Access access =null;
				if(descripcion != null && tipoAcceso !=null){
					GroupAccessAccess gaa = new GroupAccessAccess();
					gaa.setDescription(descripcion);
					gaa.setAccessType(tipoAcceso);
					access = new Access();
					access.setGroupAccessAccess(gaa);
				}
				rights.getGroupRightsRights().setAccess(access);	
			
		}
		else {
			rights.getGroupRightsRights().setAccess(null);
		}
	}	
}
