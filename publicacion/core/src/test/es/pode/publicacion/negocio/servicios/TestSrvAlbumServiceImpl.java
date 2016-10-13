/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.servicios;

/**
 * @see SrvAlbumServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvAlbumServiceImpl
    extends SrvAlbumServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvAlbumServiceImpl(){
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
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST obtenAlbumesPorUsuario
     *   TODO: Implementar el test para el metodo obtenAlbumesPorUsuario del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo obtenAlbumesPorUsuario acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenAlbumesPorUsuario() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST obtenOdesPorAlbum
     *   TODO: Implementar el test para el metodo obtenOdesPorAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo obtenOdesPorAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenOdesPorAlbum() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST desasociarODEAlbum
     *   TODO: Implementar el test para el metodo desasociarODEAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo desasociarODEAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testdesasociarODEAlbum() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST asociarODEAlbum
     *   TODO: Implementar el test para el metodo asociarODEAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo asociarODEAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testasociarODEAlbum() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST altaAlbum
     *   TODO: Implementar el test para el metodo altaAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo altaAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testaltaAlbum() {
    	 ResultadoOperacionAlbumVO resultadoOPE1 = this.servicio.altaAlbum("Historia", "Historia", "marialopezf");
    	 ResultadoOperacionAlbumVO resultadoOPE2 = this.servicio.altaAlbum("Historia", "Historia", "marialopezf");
    	 
    }

    /**
     *   TEST bajaAlbum
     *   TODO: Implementar el test para el metodo bajaAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo bajaAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testbajaAlbum() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST modificaAlbum
     *   TODO: Implementar el test para el metodo modificaAlbum del servicio SrvAlbumService.
     * 	 En el caso de tener que el metodo modificaAlbum acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAlbumServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testmodificaAlbum() {
		String prueba = null; 
		assertNotNull(prueba);

    }

	
	
	
}