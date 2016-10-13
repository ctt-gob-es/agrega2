// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.fuentestaxonomicas.negocio.servicio;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;

import org.apache.log4j.Logger;

/**
 * @see SrvEstructurasEducativasServiceImpl
 */
public class TestSrvEstructurasEducativasServiceImpl
    extends SrvEstructurasEducativasServiceImplBase
{
	private static Logger logger= Logger.getLogger(TestSrvEstructurasEducativasServiceImpl.class); 
    /**
     * Constructor
     */
     public TestSrvEstructurasEducativasServiceImpl(){
		super();

	}

	
	
     public void testListarTesauros01()
     throws Exception 
     {
    	SrvEstructurasEducativasService servicio= this.servicio;
    	try{
//			VdexVO[] lista=servicio.listarTesauros();
//			assertNotNull(lista);
//			
//			for (int i = 0; i < lista.length; i++) {
//				assertNotNull(lista[i]);
//				assertNotNull(lista[i].getNombre());
//				assertNotNull(lista[i].getCodigoError());
//				logger.debug("testListarTesauros01: fichero" + i + ": " + lista[i].getNombre());
//			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
     
     public void testListarTaxonomias01() 
     throws Exception 
     {
     	SrvEstructurasEducativasService servicio= this.servicio;
    	try{
//			VdexVO[] lista=servicio.listarBackups();
//			assertNotNull(lista);
//			
//			for (int i = 0; i < lista.length; i++) {
//				assertNotNull(lista[i]);
//				assertNotNull(lista[i].getNombre());
//				assertNotNull(lista[i].getCodigoError());
//				logger.debug("testListarTaxonomias01: fichero" + i + ": " + lista[i].getNombre());
//			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
     }
     
     public void testListarArbolesCurriculares01() 
     throws Exception 
     {
     	SrvEstructurasEducativasService servicio= this.servicio;
    	try{
//			VdexVO[] lista=servicio.listarArbolCurricular();
//			assertNotNull(lista);
//			
//			for (int i = 0; i < lista.length; i++) {
//				assertNotNull(lista[i]);
//				assertNotNull(lista[i].getNombre());
//				assertNotNull(lista[i].getCodigoError());
//				logger.debug("testListarTesauros01: fichero" + i + ": " + lista[i].getNombre());
//			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
     }

     public void testChequearIdiomaArbol01()
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 String existe="prueba_es.xml";
    		 Boolean nombreExiste= servicio.chequearIdiomaArbolCurricular(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), true);// existe 
    		 
    		 existe="prueba_au.xml"; //idioma que no existe en la plataforma... deberia dar un false
    		 nombreExiste= servicio.chequearIdiomaArbolCurricular(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), false);// el idioma no existe 
    		 
    		 existe="prueba_va.xml"; //el idioma valenciano
    		 nombreExiste= servicio.chequearIdiomaArbolCurricular(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), false);// el idioma no existe 
    		 
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 
	 }
     
     
     public void testChequearIdiomaTesauro01()
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 String existe="prueba_es.xml";
    		 Boolean nombreExiste= servicio.chequearIdiomaTesauro(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), true);// existe 
    		 
    		 existe="prueba_au.xml"; //idioma que no existe en la plataforma... deberia dar un false
    		 nombreExiste= servicio.chequearIdiomaTesauro(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), false);// el idioma no existe 
    		 
    		 existe="prueba_va.xml"; //el idioma valenciano
    		 nombreExiste= servicio.chequearIdiomaTesauro(existe);
    		 assertNotNull(nombreExiste);
    		 assertEquals(nombreExiste.booleanValue(), false);// el idioma no existe 
    		 
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 
	 }
     
     
     private ParamVdexVO obtenerParamVdex(File file)
     throws Exception
     {
			InputStream in = new FileInputStream(file);
			byte[] data = new byte[in.available()];
			in.read(data);
			
			InternetHeaders ih = new InternetHeaders();
			MimeBodyPart mbp = null;
			DataSource source = null;
			DataHandler dFichero = null;
			
			
			mbp = new MimeBodyPart(ih, data);
    		source = new MimePartDataSource(mbp);
    		dFichero = new DataHandler(source);
    		
	   		 ParamVdexVO param= null;
			 param = new ParamVdexVO();
			 param.setNombreFichero(file.getName());
			 param.setFicheroXml(dFichero);

    		
    		
    		return param;
     }
     
     
     public void testSubirTesauro01() 
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 java.net.URL ficheroURL= null;
    		 ficheroURL = this.getClass().getResource("/tesauros/prueba");//fichero que no es xml.. debe fallar
    		 File fichXML= new File(ficheroURL.getFile());
    		 
    		 ParamVdexVO[] arrayParam= new ParamVdexVO[1];
    		 arrayParam[0]= obtenerParamVdex(fichXML);
    		 
    		 VdexVO[] resultado;
    		 resultado=servicio.subirFicherosVdex(arrayParam, TipoVdex.TESAURO);

    		 assertNotNull(resultado[0]);
    		 assertEquals(resultado[0].getCodigoError(), "2");
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
	}
     

     public void testSubirTesauro02() 
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 java.net.URL ficheroURL= null;
    		 ficheroURL = this.getClass().getResource("/tesauros/prueba.xml");
    		 //fichero que es xml, pero no lleva el idioma en el nombre.. debe fallar
    		 File fichXML= new File(ficheroURL.getFile());
    		 
    		 ParamVdexVO[] arrayParam= new ParamVdexVO[1];
    		 arrayParam[0]= obtenerParamVdex(fichXML);
    		 
    		 VdexVO[] resultado;
    		 resultado=servicio.subirFicherosVdex(arrayParam , TipoVdex.TESAURO);

    		 assertNotNull(resultado[0]);
    		 assertEquals(resultado[0].getCodigoError(), "2");
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
	}


     public void testSubirTesauro03() 
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 java.net.URL ficheroURL= null;
    		 ficheroURL = this.getClass().getResource("/tesauros/prueba_en.xml");
    		 //fichero que es xml, idioma correcto, pero no es válido.. debe fallar
    		 File fichXML= new File(ficheroURL.getFile());
    		 
    		 ParamVdexVO[] arrayParam= new ParamVdexVO[1];
    		 arrayParam[0]= obtenerParamVdex(fichXML);
    		 
    		 VdexVO[] resultado;
    		 resultado=servicio.subirFicherosVdex(arrayParam , TipoVdex.TESAURO);

    		 assertNotNull(resultado[0]);
    		 assertEquals(resultado[0].getCodigoError(), "3");

    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
	}
     
     public void testSubirTesauro04() 
     throws Exception 
     {
    	 SrvEstructurasEducativasService servicio= this.servicio;
    	 try{
    		 java.net.URL ficheroURL= null;
    		 ficheroURL = this.getClass().getResource("/tesauros/prueba_ok_en.xml");
    		 //fichero que es xml, idioma correcto, pero no es válido.. debe fallar
    		 File fichXML= new File(ficheroURL.getFile());
    		 
    		 ParamVdexVO[] arrayParam= new ParamVdexVO[1];
    		 arrayParam[0]= obtenerParamVdex(fichXML);
    		 
    		 VdexVO[] resultado;
    		 resultado=servicio.subirFicherosVdex(arrayParam , TipoVdex.TESAURO);

    		 assertNotNull(resultado[0]);
    		 assertEquals(resultado[0].getCodigoError(), "1");
    		 assertEquals(resultado[0].getNombre(), "");

    		 //intento subirlo nuevamente--- deberia darme un error de que el fichero ya existe..
    		 resultado=servicio.subirFicherosVdex(arrayParam , TipoVdex.TESAURO);
    		 
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
	}
     
     
     

}