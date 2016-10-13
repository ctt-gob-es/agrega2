// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.modificador.negocio.servicio;

import net.sf.dozer.util.mapping.MapperIF;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pode.modificador.negocio.cambios.configuracion.ConfiguracionDao;

/**
 * @see SrvTareasModificacionServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvTareasModificacionServiceImpl
    extends SrvTareasModificacionServiceImplBase
{
	private static final String TEST_XML2 = "/odes/es_20070727_2_0130101/imsmanifest.xml";
	private static final String TEST_XML1 = "/xmls/test-configuracion-tareas.xml";
	private static final String TEST_XML3 = "/xmlsTest/test-configuracion-tareas.xml";
//	private SCORM2004Dao dao = null;
//	private ModificadorProperties props = null;
	private MapperIF mapper = null;
	private ConfiguracionDao daoC = null;
	private static ClassPathXmlApplicationContext context = null;


    /**
     * Constructor
     */
     public TestSrvTareasModificacionServiceImpl()
     {
    	 super();
 		try {
 		if(context==null) {
 			context = new ClassPathXmlApplicationContext(getConfigLocations());
 		}
 		if(mapper==null) {
 			mapper = (MapperIF)context.getBean("beanMapper");
 		}
 		if(daoC==null) {
 			daoC = (ConfiguracionDao)context.getBean("configuracionDao");
 		}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
		super.onSetUpInTransaction();
	}

    /**
     * onTearDownInTransaction
     */

	 protected void onTearDownInTransaction() {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
	 	
    	 super.onTearDownInTransaction();
     }
	

    /**
     * testInicial
     *   TODO: Agregar las pruebas unitarias que correspondan al servicio, siguiendo un patron de nombrado como
     *     public void test<nombreTest>().
     * 	 En el caso de tener que el metodo ${operation.name} acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvTareasModificacionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST ejecutarTareaModificacion
     *   TODO: Implementar el test para el metodo ejecutarTareaModificacion del servicio SrvTareasModificacionService.
     * 	 En el caso de tener que el metodo ejecutarTareaModificacion acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvTareasModificacionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testejecutarTareaModificacion() 
     {
    	 
		Boolean resultado = this.servicio.ejecutarTareaModificacion(Long.valueOf(1));
		assertNotNull(resultado);

    }

	
	
	
}