package es.pode.catalogacion.negocio.externos;

import java.net.Authenticator;

import net.sf.dozer.util.mapping.MapperIF;

import org.apache.log4j.Logger;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.LomESDao;
import es.pode.soporte.i18n.I18n;

public class TraduccionDao {
	
	public TraduccionDao(){
		super();
	}
	
	private  Logger logger = Logger.getLogger(this.getClass());
	
	private es.pode.catalogacion.negocio.externos.TraduccionDao traduccionDao;
	private MapperIF beanMapperAux = null;
	private LomESDao lomes = null;
	
	public LomESDao getLomes() {
		return lomes;
	}

	public void setLomes(LomESDao lomes) {
		this.lomes = lomes;
	}
	public MapperIF getBeanMapperAux() 
	{
		return beanMapperAux;
	}

	public void setBeanMapperAux(MapperIF beanMapperAux) 
	{
		this.beanMapperAux = beanMapperAux;
	}
		
	
	public void setTraduccionDao (es.pode.catalogacion.negocio.externos.TraduccionDao trDao){
		this.traduccionDao= trDao;
	}
	
	public TraduccionDao getTraduccionDao () {
		return this.traduccionDao;
	}	
	
	public String traduce (String textoATrad, String idiomaOri, String idiomaDest) throws Exception {
		String translatedText = "";
		try {
			String proxyhost= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOSTPROXY);//"proxy.indra.es";
	        String proxyport = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PORTPROXY);//"8080";
	        System.setProperty("http.proxyHost", proxyhost);
	   	 	System.setProperty("http.proxyPort", proxyport);
	        //System.setProperty("http.proxyHost", "proxy.indra.es");
	   	 	//System.setProperty("http.proxyPort", "8080");
	   	 	String proxyuser=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.USUARIOPROXY);//"cau.agrega";
	   	 	String proxypass= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CLAVEPROXY);//"indra2007";
	        HttpAuthenticator miAut = (new HttpAuthenticator(proxyuser, proxypass));            	 
	   	 	Authenticator.setDefault(miAut);	  
	   	 	
	   	 	String idioTradOri=I18n.getInstance().obtenerIdiomaTraducido(idiomaOri, "en"); //en es el idioma del q coge el fichero y dev spanish
	   	 	if (idioTradOri!=null) {
	   	 		idioTradOri= idioTradOri.toUpperCase();
	   	 	}
	   	 String idioTradDest=I18n.getInstance().obtenerIdiomaTraducido(idiomaDest, "en"); //en es el idioma del q coge el fichero y dev spanish
	   	 	if (idioTradDest!=null) {
	   	 		idioTradDest= idioTradDest.toUpperCase();
	   	 	}
	   	 	//String idioIso=I18n.getInstance().obtenerIdiomaIso("es", "en");
	   	 Language langOri = Language.valueOf(idioTradOri);
	   	 Language langDest = Language.valueOf(idioTradDest);
	   	 // Set the HTTP referrer to your website address.
	      Translate.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
    	  translatedText=Translate.translate(textoATrad, langOri, langDest);
   	 
   	 	}catch (Exception e) {
			logger.error("Error al Traducir " + e);
			throw new Exception(e);
		}
		return translatedText;
		
	}
	
}