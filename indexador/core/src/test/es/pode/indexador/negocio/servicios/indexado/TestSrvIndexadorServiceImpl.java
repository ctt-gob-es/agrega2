// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.indexador.negocio.servicios.indexado;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;




/** 
 * @see SrvIndexadorServiceImpl
 */
public class TestSrvIndexadorServiceImpl
    extends SrvIndexadorServiceImplBase
{

	private Logger logger = Logger.getLogger(this.getClass());

	private Properties props = null;
    /**
     * Constructor
     */
    
     public TestSrvIndexadorServiceImpl(){
 		super();
    	 try {
    		 props = new Properties();
    		 props.load(this.getClass().getResourceAsStream("/indexador.properties"));
 			
 		} catch (IOException e) {
 			logger.error("ERROR: fichero no encontrado: indexador.properties",e);
 			throw new RuntimeException(e);
 		}
	}
     
 	/**
 	 * Implementar si no desea usar DataSets
 	 */
     private String datasetFile = "SrvDataEmptyDataSet.xls";
     
	
	
    /**
     *   TEST IndexarODE
     *   TODO: Implementar el test para el metodo IndexarODE del servicio SrvIndexadorService.
     * @throws IndexaODEException 
     */
     public void testindexarODE() throws IndexaODEException {
    	 //dODEVO[] array = new IdODEVO[]{generarIdOde("1014"),generarIdOde("1015"), generarIdOde("1016")};
    	 IdODEVO[] array = generarODE(3);
    	 IndexadorVO[] resultados = this.servicio.indexarODE(array);
    	 assertNotNull(resultados);
    	 assertEquals(array.length, resultados.length);
   		
   		for (int i = 0; i < resultados.length; i++) {
			assertNotNull(resultados[i]);
			assertEquals(resultados[i].getCode(),0);
			assertNotSame(resultados[i].getMensaje(), " ");
			assertNotNull(resultados[i].getLocalizador());
		}
    }

    /**
     *   TEST reindexarODE
     *   TODO: Implementar el test para el metodo reindexarODE del servicio SrvIndexadorService.
     * @throws ReindexaODEException 
     */
     public void testreindexarODE() throws ReindexaODEException { 
    	 //IdODEVO[] idODE = new IdODEVO[]{generarIdOde("12345678920"),generarIdOde("5694"), generarIdOde("45125"), generarIdOde("12345678916")};
    	 IdODEVO[] idODE = new IdODEVO[]{generarIdOde("1001"), generarIdOde("1002")};
    	 //IdODEVO[] idODE = new IdODEVO[]{generarIdOde("1001")};
    	 IndexadorVO[] resultados = this.servicio.reindexarODE(idODE);
    	 assertNotNull(resultados);
   		 assertEquals(idODE.length, resultados.length);
   		 String prueba = null; 
   		 assertNull(prueba);
   		 for (int i = 0; i < resultados.length; i++) {
			assertNotNull(resultados[i]);
			assertEquals(resultados[i].getCode(),0);
			assertNotSame(resultados[i].getMensaje(), " ");
			assertNotNull(resultados[i].getLocalizador());
		 }
     }

    /**
     *   TEST eliminarODE
     *   TODO: Implementar el test para el metodo eliminarODE del servicio SrvIndexadorService.
     * @throws EliminaODEException 
     */
     public void testeliminarODE() throws EliminaODEException {
    	 String[] loc=new String[1]; 	 
    	 loc[0]="1008";
    	 //loc[1]="1007";
    	 //loc[2]="1008";
    	 IndexadorVO[] respuesta= this.servicio.eliminarODE(loc);
    	 assertNotNull(respuesta);
    	 String prueba = null; 
   		 assertNull(prueba);
    }

     
     /**
      *   TEST regenerarIndice
      *   TODO: Implementar el test para el metodo regenerarIndice del servicio SrvIndexadorService.
      * @throws IndexacionTotalException 
      */
      public void testregenerarIndice() throws IndexacionTotalException{
    	 String idioma = "es";
    	 IdODEVO[] idODE = new IdODEVO[]{generarIdOde("1015"), generarIdOde("1016")};
    	 //IdODEVO[] idODE = generarODE(3);
    	 IndexadorVO[] respuesta = this.servicio.regenerarIndice(idioma, idODE);  
    	 assertNotNull(respuesta);
   		 for (int i = 0; i < respuesta.length; i++) {
 			assertNotNull(respuesta[i]);
 			assertEquals(respuesta[i].getCode(),0);
 			assertNotSame(respuesta[i].getMensaje(), " ");
 			assertNotNull(respuesta[i].getLocalizador());
 		 }
      }
     
     
     private IdODEVO[] generarODE (int numeroODEs){
    	 ArrayList lista = new ArrayList();
    	 int x;
    	 for(int i=0; i<numeroODEs; i++){
    		 x = (int)(Math.random()*1000);
    		 String identificador= String.valueOf(x);
    		 IdODEVO idODE = generarIdOde(identificador);
    		 lista.add(idODE);
    		 
    	}
    	IdODEVO[] odes = (IdODEVO[]) lista.toArray(new IdODEVO[0]);
    	 
    	 return odes;
     } 
      
     private IdODEVO generarIdOde(String identificador){
    	 try{
    		 String localizador = this.applicationContext.getResource("imsmanifest.xml").getFile().getPath();
	    	 IdODEVO idODE = new IdODEVO();
	    	 idODE.setLocalizadorODE(localizador.substring(0,localizador.lastIndexOf(File.separator)));
	    	 idODE.setIdODE(identificador);
	    	 idODE.setValoracion(Float.valueOf("0.0"));
	    	 idODE.setSecuencia(Boolean.FALSE);
	    	 idODE.setImgFile("/galeriaimg/22/5_3_0241611.png");
	    	 idODE.setTamanio(Float.valueOf("1.7960728E7"));
	    	 
	    	 // Catalogación primaria.
	    	 String[] procesosCognitivos = new String[]{"analyse","understand", "implement", "self assessment", "observe" };
	    	 String[] palabrasClave = new String[]{"estas","palabras", "clave"};
	    	 String licencia = "public domain";
	    	 String idioma = "es";
	    	 String[] formatos = new String[]{"formato1", "formato2"};
	    	 String[] edades = new String[]{"edad1", "edad2"};
	    	 String[] destinatarios = new String[]{"destination1", "destination2"};
	    	 String descripcion = "desc";
	    	 String curso = "curso";
	    	 String[] contextos = new String[]{"nuevoCont1", "nuevoCont2"};
	    	 String[] autor = new String[]{};
	    	 String[] taxonomia = new String[]{"ACLOE2006/1"};
	    	 String[] ambito = new String[]{"universal"};
	    	 Integer nivelAgregacion = 1;
	    	 String[] tesauros= new String[]{"tesauro1/tesauro1.1/tesauro1.1.1","tesauro2/tesauro2.2/tesauro2.2.2"};
	    	 String[] fuente = new String[]{};
	    	 String[] relacion = new String[]{"relacion1", "relacion2"};
	    	 String[] publicador = new String[]{"administrador"};
	    	 String[] contribuidor = new String[]{"contribuidor1", "contribuidor2"};
	    	 String[] alcance = new String[]{"alcance1", "alcance2"};
	    	 String[] descripciones = new String[]{"descripciones1", "descripciones2"};
	    	 String[] literalesAC = new String[] {"Educación Infantil"};
	    	 //el primero tiene solo los campos mínimos para que funcione, con muchos a null
		    LomESPrimarioVO cat = new LomESPrimarioVO("Escalera Caracol", 
	    		 new String[] { "recurso"}, procesosCognitivos, 
	    		 palabrasClave, new String[0], 
    			 licencia, idioma, formatos, Calendar.getInstance(),
    			 edades, destinatarios, descripcion, curso, 
    			 contextos,autor, taxonomia, ambito, 
    			 nivelAgregacion, tesauros, fuente,relacion, 
    			 publicador, contribuidor, alcance,  descripciones,literalesAC, null);
		     
	    	 idODE.setCatalogacionPrimaria(cat);
	    	 
	    	 // Catalogación Secundaria
	    	 String tituloSec = "Identificar y conocer el problema, conducta o conflicto";
	    	 String[] palabrasClaveSec = new String[]{"actúo","autocontrol", "identificar", "conocer", "conflicto", "problema causas", "soluciones", "consecuencias"};
	    	 String descripcionSec = "Adquirir habilidades para la prevención y para la resolución";
	    	 LomESSecundarioVO lomSec = new LomESSecundarioVO(tituloSec, palabrasClaveSec, descripcionSec);
	    	
	    	 String tituloSec2 = "Análisis de las causas que origina el problema o conflicto";
	    	 String[] palabrasClaveSec2 = new String[]{"conducta","control", "identificar", "conocer", "problema", "conflicto"};
	    	 String descripcionSec2 = "Adquirir estrategias";
   	 
	    	 LomESSecundarioVO lomSec2 = new LomESSecundarioVO(tituloSec2, palabrasClaveSec2, descripcionSec2);
	    	 LomESSecundarioVO[]lomeES = new LomESSecundarioVO[] {lomSec, lomSec2};
	    	 idODE.setCatalogacionSecundaria(lomeES);
	    	 
	    	 return idODE;
	    	 
	     }catch(IOException iO){
			 logger.error("ERROR: fichero no encontrado: imsmanifest.xml",iO);
				 throw new RuntimeException(iO);
		 }
     }
     
}