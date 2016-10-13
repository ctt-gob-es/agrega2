/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.rest.servicios;


/**
 * @see SrvRestServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvRestServiceImpl
    extends SrvRestServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvRestServiceImpl(){
		super();
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
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST searchByCurricularArea
     *   TODO: Implementar el test para el metodo searchByCurricularArea del servicio SrvRestService.
     * 	 En el caso de tener que el metodo searchByCurricularArea acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testsearchByCurricularArea() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST searchByAgregationLevel
     *   TODO: Implementar el test para el metodo searchByAgregationLevel del servicio SrvRestService.
     * 	 En el caso de tener que el metodo searchByAgregationLevel acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testsearchByAgregationLevel() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST searchByTag
     *   TODO: Implementar el test para el metodo searchByTag del servicio SrvRestService.
     * 	 En el caso de tener que el metodo searchByTag acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testsearchByTag() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST searchByEvaluation
     *   TODO: Implementar el test para el metodo searchByEvaluation del servicio SrvRestService.
     * 	 En el caso de tener que el metodo searchByEvaluation acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testsearchByEvaluation() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST browseUserOwnedPublicODEs
     *   TODO: Implementar el test para el metodo browseUserOwnedPublicODEs del servicio SrvRestService.
     * 	 En el caso de tener que el metodo browseUserOwnedPublicODEs acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testbrowseUserOwnedPublicODEs() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST browseUserPublicODEs
     *   TODO: Implementar el test para el metodo browseUserPublicODEs del servicio SrvRestService.
     * 	 En el caso de tener que el metodo browseUserPublicODEs acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testbrowseUserPublicODEs() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST writeRestResponse
     *   TODO: Implementar el test para el metodo writeRestResponse del servicio SrvRestService.
     * 	 En el caso de tener que el metodo writeRestResponse acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testwriteRestResponse() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST processRestRequest
     *   TODO: Implementar el test para el metodo processRestRequest del servicio SrvRestService.
     * 	 En el caso de tener que el metodo processRestRequest acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvRestServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testprocessRestRequest() {
		String prueba = null; 
		assertNotNull(prueba);

    }
	
	
}