// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.validador.negocio.servicio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * @see SrvValidadorServiceImpl
 */
public class TestSrvValidadorServiceImpl
    extends SrvValidadorServiceImplBase
{

	private static Logger logger = Logger.getLogger(TestSrvValidadorServiceImpl.class);
    /**
     * Constructor
     */
     public TestSrvValidadorServiceImpl(){
		super();
	}

   

    /**
     * testInicial
     *   TODO: Agregar las pruebas unitarias que correspondan al servicio, siguiendo un patron de nombrado como
     *     public void test<nombreTest>().
     */	
     public void testInicial(){
		 String prueba = null;//Si en un futuro se quiere que puedan venir en mayuscula
		 String entri="ES-EX_20061017_2_1234567";
		 //es-ex_20061017_2_1234567 siendo -ex opcional; Uno mayuscula y el otro minuscula
//		 Pattern mask=Pattern.compile("^(([a-z]{2})|([A-Z]{2}))(\\-(([a-z][a-z])|([A-Z][A-Z])))?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
//		 Matcher matcher = mask.matcher(entri);
//		 if(matcher.matches())
//			 System.out.println("OK");
//		 else
//			 System.out.println("MAL");
		 //Ambos mayusculas o minusculas
		 Pattern mask=Pattern.compile("^((([a-z]{2})(\\-[a-z][a-z]))|(([A-Z]{2})(\\-[A-Z][A-Z])))?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
		 Matcher matcher = mask.matcher(entri);
		 if(matcher.matches())
		 	 logger.info("OK");
		 else
			 logger.info("MAL");
		assertNull(prueba);
	}
	
	
    /**
     *   TEST obtenerValidacionBin
     *   TODO: Implementar el test para el metodo obtenerValidacionBin del servicio SrvValidadorService.
     */
     public void testobtenerValidacionBin() {
    	 String manifest = "../validador/core/src/test-resources/ode_prueba";
    	 
//    	 manifest =
// 			"D:/taller/Juega_con_la_casa";
// 			"D:/ODES/Historia_de_la_television_en_Espana"
// 			"D:/ODES/El_volumen";
// 			"D:/taller/Pruebas/ODE-1";
// 			 "D:/ODES/Determinantes_posesivos";
// 			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es";
// 			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es_SIN_MODIF";
// 			"D:/ODES/Las_estaciones,_la_ropa_y_la_casa";
// 			"D:/ODES/Hoy_me_siento";
// 			"D:/ODES/Grupos_vocales";
//		 	"D:/ODES/cm05_es";
    	 
    	 ValidaVO valida = new ValidaVO();
    	 valida= servicio.obtenerValidacionBin(manifest);
     	
    	 logger.info("\nResultado Validación Binaria (Exportar): "+ valida.getEsValidoManifest().booleanValue());
     	
    	 logger.info("\nERRORES PARSEO \n");
    	 String resultado = valida.getResultadoValidacion();
    	 String [] partes = resultado.split(";");
    	 for(int i=0; i< partes.length; i++){
    		 logger.info(partes[i].trim());
    	 }
     	
    	 logger.info("\nERRORES XERCES \n");
    	 ErrorParseoVO[] erroresXerces = valida.getErrores();
    	 for(int i=0; i< erroresXerces.length; i++){
    		 logger.info(erroresXerces[i].getMensaje());
    	 }
     	
    	 assertEquals(valida.getEsValidoManifest().booleanValue(), true);

    }

    /**
     *   TEST obtenervalidacionLigera
     *   TODO: Implementar el test para el metodo obtenervalidacionLigera del servicio SrvValidadorService.
     */
     public void testobtenervalidacionLigera() {
    	 String manifest = "../validador/core/src/test-resources/ode_prueba";

//    	 manifest =
//			"D:/taller/Juega_con_la_casa";
//			"D:/ODES/Historia_de_la_television_en_Espana"
//			"D:/ODES/El_volumen";
//			"D:/taller/Pruebas/ODE-1";
//			 "D:/ODES/Determinantes_posesivos";
//			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es";
//			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es_SIN_MODIF";
//			"D:/ODES/Las_estaciones,_la_ropa_y_la_casa";
//			"D:/ODES/Hoy_me_siento";
//			"D:/ODES/Grupos_vocales";
//		 	"D:/ODES/cm05_es";

    	manifest = manifest.replace( '\\', '/');
      	SrvValidadorService servicio = this.servicio;
      	ValidaVO valida = new ValidaVO();
      	String tipoOde ="CA"; //"RCP";//"CA"; //obligatorio orgazination!
		
      	valida= servicio.obtenervalidacionLigera(manifest, tipoOde);
      	
      	logger.info("\nResultado Validación Ligera (Importar): "+ valida.getEsValidoManifest().booleanValue());
      	
		logger.info("\nERRORES PARSEO \n");
     	String resultado = valida.getResultadoValidacion();
     	String [] partes = resultado.split(";");
     	for(int i=0; i< partes.length; i++){
     		logger.info(partes[i].trim());
     	}
     	
		logger.info("\nERRORES XERCES \n");
     	ErrorParseoVO[] erroresXerces = valida.getErrores();
     	for(int i=0; i< erroresXerces.length; i++){
     		logger.info(erroresXerces[i].getMensaje());
     	}
      	
      	assertEquals(valida.getEsValidoManifest().booleanValue(), true);
      	
    }

     /**
      *   TEST testObtenerValidacion
      */	
	public void testobtenerValidacion(){
   	 String manifest = "../validador/core/src/test-resources/ode_prueba";

//	 manifest =
//		"D:/taller/Juega_con_la_casa";
//		"D:/ODES/Historia_de_la_television_en_Espana"
//		"D:/ODES/El_volumen";
//		"D:/taller/Pruebas/ODE-1";
//		 "D:/ODES/Determinantes_posesivos";
//		"D:/ODES/ODES NO VALIDOS/ig07_oa01_es";
//		"D:/ODES/ODES NO VALIDOS/ig07_oa01_es_SIN_MODIF";
//		"D:/ODES/Las_estaciones,_la_ropa_y_la_casa";
//		"D:/ODES/Hoy_me_siento";
//		"D:/ODES/Grupos_vocales";
//	 	"D:/ODES/cm05_es";
	 
		ValidaVO valida = new ValidaVO();
		valida = servicio.obtenerValidacion(manifest);
		
		logger.info("\nResultado Validación (Empaquetador (validar y  proponer)): "+ valida.getEsValidoManifest().booleanValue());
		
		logger.info("\nERRORES PARSEO \n");
     	String resultado = valida.getResultadoValidacion();
     	String [] partes = resultado.split(";");
     	for(int i=0; i< partes.length; i++){
     		logger.info(partes[i].trim());
     	}
     	
		logger.info("\nERRORES XERCES \n");
     	ErrorParseoVO[] erroresXerces = valida.getErrores();
     	for(int i=0; i< erroresXerces.length; i++){
     		logger.info(erroresXerces[i].getMensaje());
     	}
     	
		assertEquals(valida.getEsValidoManifest().booleanValue(), true);
	}
     
     
     /**
      *   TEST ValidarCargaOde
      */	
	public void testvalidarCargaOde(){

		String manifest = "../validador/core/src/test-resources/ode_prueba";

//		manifest =
//			"D:/taller/Juega_con_la_casa";
//			"D:/ODES/Historia_de_la_television_en_Espana"
//			"D:/ODES/El_volumen";
//			"D:/taller/Pruebas/ODE-1";
//			"D:/ODES/Determinantes_posesivos";
//			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es";
//			"D:/ODES/ODES NO VALIDOS/ig07_oa01_es_SIN_MODIF";
//			"D:/ODES/Las_estaciones,_la_ropa_y_la_casa";
//			"D:/ODES/Hoy_me_siento";
//			"D:/ODES/Grupos_vocales";
//		 	"D:/ODES/cm05_es";
		
		ValidaVO valida = new ValidaVO();
		valida= servicio.validarCargaOde(manifest);
		
		logger.info("\nResultado Validación CargaOde (Carga masiva): "+ valida.getEsValidoManifest().booleanValue());
		
		logger.info("\nERRORES PARSEO \n");
     	String resultado = valida.getResultadoValidacion();
     	String [] partes = resultado.split(";");
     	for(int i=0; i< partes.length; i++){
     		logger.info(partes[i].trim());
     	}
     	
		logger.info("\nERRORES XERCES \n");
     	ErrorParseoVO[] erroresXerces = valida.getErrores();
     	for(int i=0; i< erroresXerces.length; i++){
     		logger.info(erroresXerces[i].getMensaje());
     	}
     	
		assertEquals(valida.getEsValidoManifest().booleanValue(), true);
	}
	
    /**
     *   TEST validarMDBasicosObl
     */	
	public void testvalidarMDBasicosObl(){
		MDBasicosOblVO mdbo = new MDBasicosOblVO();
		//para hacer la prueba rellenamos aqui el valueObject
		mdbo.setTitulo("Titulo metadadatos Obligatorios");
		mdbo.setIdioma("espanol");
		mdbo.setDescripcion("esto es una pequena descripcion");
		mdbo.setTipoRecurso("tipo de recurso");
		mdbo.setContexto("");//contexto del metadato 
		mdbo.setEdad("");//Edad son 55 
		mdbo.setIdiomaDest("es");//ingles
		mdbo.setProcesoCog("datos del proceso cognitivo");
		SrvValidadorService validaMDBObl = this.servicio;
		ValidaVO vMDO = new ValidaVO();
		vMDO= validaMDBObl.validarMDBasicosObl(mdbo);
		assertEquals(vMDO.getEsValidoManifest().booleanValue(), true);
	}
	
    /**
     *   TEST validarMec
     */	
	public void testvalidarMec(){
		String rutaOde = "../validador/core/src/test-resources/ode_prueba";
		String vOde = null;
		vOde= servicio.validarMec(rutaOde);
		assertNotNull(vOde);
		assertEquals(vOde, "es_20070727_3_0150500");
	}	
	
}