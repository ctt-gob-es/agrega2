// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.entregar.negocio.servicios;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.localizador.negocio.servicios.SrvLocalizadorServiceServiceLocator;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Manifest;

//import es.pode.publicacion.negocio.servicios.SrvPublicacionService;

/**
 * @see SrvEntregarServiceImpl
 */
public class TestSrvEntregarServiceImpl
    extends SrvEntregarServiceImplBase
{
	private SCORM2004Dao scormDao;

	private Properties properties = null;
	
	private SrvLocalizadorService localizador = null;
	
    /**
     * Constructor
     */
     public TestSrvEntregarServiceImpl(){
		super();
		this.properties = new Properties();
		InputStream is= this.getClass().getResourceAsStream("/app.properties");
		try { 
			properties.load(is);
		} catch (IOException e) {
			logger.error("No se pudo abrir el fichero de propiedades; ", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("Error al cerrar stream: ",e);
			}
		}
		

		
		try {
		localizador = (new SrvLocalizadorServiceServiceLocator()).getSrvLocalizadorService();
		} catch (Exception e) {
			logger.error("No se pudo obtener referencia al localizador.",e);
			fail("No se pudo obtener referencia al localizador.");
		}


	}

    /**
     * testInicial
     *   TODO: Agregar las pruebas unitarias que correspondan al servicio, siguiendo un patron de nombrado como
     *     public void test<nombreTest>().
     */	
//     public void testInicial(){
//		String prueba = null;
//		assertNull(prueba);
//	}
	
	
//    /**
//     *   TEST generarPaquetePIF  
//     * @throws GeneraPIFException 
//     */
     public void testgenerarPaquetePIF() throws GeneraPIFException 
     {
    	 String identificador=this.properties.getProperty("test.identificador");
	     LocalizadorVO loc=null;
		try {
			loc = localizador.consultaLocalizador(identificador);
		} catch (RemoteException e) {
			

		}
    	 
    	 SrvEntregarService entrega= this.servicio;
    	 entrega.generarPaquetePIF(identificador);
    	 if(loc!=null)
    	 {
    		 File ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }

     }

     public void testCargarObjManifest() throws Exception 
     {
    	 
    	 String usuario=this.properties.getProperty("test.usuario");
    	 String sFichManifest = this.getPath2File();
    	 File manifestFile = new File(sFichManifest);
    	 
    	 Manifest manifest=null;
    	 if(manifestFile.exists())
    	 {
    	 	this.getScormDao().parsearODELazy(manifestFile);
    	 	SrvEntregarService entrega= this.servicio;
    	 	assertNotNull(entrega.cargarObjManifest(usuario, this.serializeManifest(manifest)));
    	 }
    	
	 }
     
     public void testObtenerOrganizaciones() throws ObtenerOrganizacionesException
     {
    	 ArgObtOrganizacionesVO arg= new ArgObtOrganizacionesVO();
    	 arg.setIdentificador(this.properties.getProperty("test.identificador"));
    	 arg.setIdioma("es");
    	 arg.setUsuario(this.properties.getProperty("test.usuario"));

//    	 String identificador=this.properties.getProperty("test.identificador");
//    	 String usuario=this.properties.getProperty("test.usuario");
    	 //String prefijo=identificador.substring(0,2);
    	 
    	 
    	 SrvEntregarService entrega= this.servicio;
    	 assertNotNull(entrega.obtenerOrganizaciones(arg));
     }
	
     public void testObtenerOrganizacionesFromHash() throws Exception
     {
    	 String usuario=this.properties.getProperty("test.usuario");
    	 String sFichManifest = this.getPath2File();
    	 File manifestFile = new File(sFichManifest);
    	 Manifest manifest = this.getScormDao().parsearODELazy(manifestFile);

    	 SrvEntregarService entrega= this.servicio;
    	 String identificador = entrega.cargarObjManifest(usuario, this.serializeManifest(manifest));

    	 ArgObtOrganizacionesVO arg= new ArgObtOrganizacionesVO();
    	 arg.setIdentificador(identificador);
    	 arg.setIdioma("es");
    	 arg.setUsuario(usuario);
    	 
    	 assertNotNull(entrega.obtenerOrganizaciones(arg));
     }
     
     private String getPath2File() throws Exception
     {
    	 String fileName = this.properties.getProperty("test.manifest");
    	 String ruta= this.properties.getProperty("test.rutamanifest") +  fileName;
    	 String fichManifest = null;
    	 try
    	 {
	    	 File file = new File(ruta);
	    	 fichManifest = file.getAbsolutePath();
    	 }
    	 catch (Exception ex)
    	 {
    		 logger.error("No se ha encontrado el fichero " + fileName, ex);
    		 throw ex;
    	 }
    	 return fichManifest;
     }
     
     private DataHandler serializeManifest(Manifest manifest) throws IOException
     {
    	 byte[] bytes = serializar(manifest);
         ByteArrayDataSource bads = new ByteArrayDataSource(bytes,"application/x-java-serialized-object");
         DataHandler dh = new DataHandler(bads);
    	 return dh;
     }

	public SCORM2004Dao getScormDao() {
		return scormDao;
	}

	public void setScormDao(SCORM2004Dao scormDao) {
		this.scormDao = scormDao;
	}
	
	
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
     *   TEST generarPaquetePIF  
     * @throws GeneraPIFException 
     */
     public void testgenerarPaqueteHTML() throws GeneraPIFException 
     {
    	LocalizadorVO loc=null;
    	String identificador=this.properties.getProperty("test.identificador");
    	
		TipoPifVO tipo= new TipoPifVO();
		tipo.setIdODE(identificador);
		tipo.setIdioma("es");
		tipo.setTipoPif(TipoPIFCst.HTML.toString());
		
		try {
			loc = localizador.consultaLocalizador(identificador);
			servicio.generarPaquetePIFTipoPIF(tipo);
			
		} catch (GeneraPIFTipoPIFException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaqueteHTML: " + e.getMessage());}
			throw new GeneraPIFException();
		} catch (RemoteException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaqueteHTML: " + e.getMessage());}
			throw new GeneraPIFException();
		}
    	 
		File ficheroPrueba=null;
    	 if(loc!=null)
    	 {
    		 ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }
    	 if(ficheroPrueba!=null && ficheroPrueba.exists())
    	 {
    		 ficheroPrueba.delete();
    		 ficheroPrueba=null;
    	 }
     }
 
     
     public void testgenerarPaquetePIFIMS_CP() throws GeneraPIFException 
     {
    	LocalizadorVO loc=null;
    	String identificador=this.properties.getProperty("test.identificador");
    	
		TipoPifVO tipo= new TipoPifVO();
		tipo.setIdODE(identificador);
		tipo.setIdioma("es");
		tipo.setTipoPif(TipoPIFCst.IMS_CP.toString());
		
		try {
			loc = localizador.consultaLocalizador(identificador);
			servicio.generarPaquetePIFTipoPIF(tipo);
			
		} catch (GeneraPIFTipoPIFException e) {
			if (logger.isDebugEnabled()) {logger.error("Error en testgenerarPaquetePIFIMS_CP: " , e);}
			throw new GeneraPIFException();
		} catch (RemoteException e) {
			if (logger.isDebugEnabled()) {logger.error("Error en testgenerarPaquetePIFIMS_CP: " ,  e);}
			throw new GeneraPIFException();
		}
    	 
		File ficheroPrueba=null;
    	 if(loc!=null)
    	 {
    		 ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }
    	 if(ficheroPrueba!=null && ficheroPrueba.exists())
    	 {
    		 ficheroPrueba.delete();
    		 ficheroPrueba=null;
    	 }
     }
	
     
     public void testgenerarPaquetePIFSCORM_04() throws GeneraPIFException 
     {
    	LocalizadorVO loc=null;
    	String identificador=this.properties.getProperty("test.identificador");
    	
		TipoPifVO tipo= new TipoPifVO();
		tipo.setIdODE(identificador);		
		tipo.setIdioma("es");
		tipo.setTipoPif(TipoPIFCst.SCORM_04.toString());
		
		try {
			loc = localizador.consultaLocalizador(identificador);
			servicio.generarPaquetePIFTipoPIF(tipo);
			
		} catch (GeneraPIFTipoPIFException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_04: " + e.getMessage());}
			throw new GeneraPIFException();
		} catch (RemoteException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_04: " + e.getMessage());}
			throw new GeneraPIFException();
		}
    	 
		File ficheroPrueba=null;
    	 if(loc!=null)
    	 {
    		 ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }
    	 if(ficheroPrueba!=null && ficheroPrueba.exists())
    	 {
    		 ficheroPrueba.delete();
    		 ficheroPrueba=null;
    	 }
     }
     
     
     public void testgenerarPaquetePIFSCORM_04_SIN_SUBMANIF() throws GeneraPIFException 
     {
    	LocalizadorVO loc=null;
    	String identificador=this.properties.getProperty("test.identificador");
    	
		TipoPifVO tipo= new TipoPifVO();
		tipo.setIdODE(identificador);		
		tipo.setIdioma("es");
		tipo.setTipoPif(TipoPIFCst.SCORM_04_SIN_SUBMANIF.toString());
		
		try {
			loc = localizador.consultaLocalizador(identificador);
			servicio.generarPaquetePIFTipoPIF(tipo);
			
		} catch (GeneraPIFTipoPIFException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_04_SIN_SUBMANIF: " + e.getMessage());}
			throw new GeneraPIFException();
		} catch (RemoteException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_04_SIN_SUBMANIF: " + e.getMessage());}
			throw new GeneraPIFException();
		}
    	 
		File ficheroPrueba=null;
    	 if(loc!=null)
    	 {
    		 ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }
    	 if(ficheroPrueba!=null && ficheroPrueba.exists())
    	 {
    		 ficheroPrueba.delete();
    		 ficheroPrueba=null;
    	 }
     }
     
     
     
     public void testgenerarPaquetePIFSCORM_12() throws GeneraPIFException 
     {
    	LocalizadorVO loc=null;
    	String identificador=this.properties.getProperty("test.identificador");
    	
		TipoPifVO tipo= new TipoPifVO();
		tipo.setIdODE(identificador);		
		tipo.setIdioma("es");
		tipo.setTipoPif(TipoPIFCst.SCORM_12.toString());
		
		try {
			loc = localizador.consultaLocalizador(identificador);
			servicio.generarPaquetePIFTipoPIF(tipo);
			
		} catch (GeneraPIFTipoPIFException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_12: " + e.getMessage());}
			throw new GeneraPIFException();
		} catch (RemoteException e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testgenerarPaquetePIFSCORM_12: " + e.getMessage());}
			throw new GeneraPIFException();
		}
    	 
		File ficheroPrueba=null;
    	 if(loc!=null)
    	 {
    		 ficheroPrueba= new File(loc.getPath()+this.properties.getProperty("carpeta.temporal")+identificador+".zip");
    		 assertTrue(ficheroPrueba.exists());
    	 }
    	 if(ficheroPrueba!=null && ficheroPrueba.exists())
    	 {
    		 ficheroPrueba.delete();
    		 ficheroPrueba=null;
    	 }
     }
     public void testTransformarODESCORM_12() throws Exception{
    	 URL origen = this.getClass().getResource("/origen/Las_estaciones,_la_ropa_y_la_casa");
    	 String ficheroOrigen=origen.getFile();
    	 URL destino = this.getClass().getResource("/destino/");
    	 String ficheroDestino=destino.getFile();
    	 TipoPIFCst tipo=TipoPIFCst.SCORM_12;

    	 try{
    		 PaquetePifVO vuelta = servicio.transformarODE(ficheroOrigen, ficheroDestino, tipo);
    		 logger.debug("Hemos llegado "+vuelta);
    	 }catch (Exception e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testTransformarODESCORM_12: " ,e);}
			throw e;
    	 }
     }
     
     public void testTransformarODESinSubmanifiesto() throws Exception{
    	 URL origen = this.getClass().getResource("/origen/Los_textos_ludicos");
    	 String ficheroOrigen=origen.getFile();
    	 URL destino = this.getClass().getResource("/destino/");
    	 String ficheroDestino=destino.getFile();
    	 TipoPIFCst tipo=TipoPIFCst.SCORM_04_SIN_SUBMANIF;

    	 try{
    		 PaquetePifVO vuelta = servicio.transformarODE(ficheroOrigen, ficheroDestino, tipo);
    		 logger.debug("Hemos llegado "+vuelta);
    	 }catch (Exception e) {
			if (logger.isDebugEnabled()) {logger.debug("Error en testTransformarODESinSubmanifiesto: " ,e);}
			throw e;
    	 }
     }
}