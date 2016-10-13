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

import java.util.Date;

import es.pode.soporte.utiles.date.DateManager;

/**
 * @see SrvContenidoInapropiadoServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvContenidoInapropiadoServiceImpl
    extends SrvContenidoInapropiadoServiceImplBase
{

	final String NO_DISPONIBLE = "NO DISPONIBLE";

    protected String[] getConfigLocations() {
        return new String[] { "testContext.xml", "applicationContext-external_publicacion.xml" };
    }
	
	
    /**
     * Constructor
     */
     public TestSrvContenidoInapropiadoServiceImpl(){
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
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST crearContenidoInapropiado
     *   TODO: Implementar el test para el metodo crearContenidoInapropiado del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo crearContenidoInapropiado acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testcrearContenidoInapropiado() {
		String prueba = "jajaja"; 
		
		//es_20081207_2_9145249', 'es', 2461906, '2009-06-05 10:03:55', 'Las estaciones, la ropa y la casa.', ''
		//Probamos con un inactivo
		String idOde="es_20081207_2_9145244";
		String titulo="Las estaciones, la ropa otro grupo.";
		Date fecha =new Date(System.currentTimeMillis());
		String usuario = "admin2";
		String comentario = "Ode creado en estado publicado otro grupo";
		String estado= "PUBLICADO";	
		String idioma_cat="es";
		//Probamos con uno normal
//		String idOde="es_20081207_2_9145249";
//		String titulo="Las estaciones, la ropa y la casa.";
//		Date fecha =new Date(System.currentTimeMillis());
//		String usuario = "ana";
//		String comentario = "Ode creado en estado 3";
//		String estado= "PUBLICADO";
		
		//this.servicio.crearContenidoInapropiado(idOde, titulo, DateManager.dateToCalendar(fecha), usuario, comentario, estado, null, idioma_cat);
		this.servicio.crearContenidoInapropiado(idOde, titulo, DateManager.dateToCalendar(fecha), usuario, comentario, estado, idioma_cat);
		//forzams commit;
		this.transactionManager.commit(this.transactionStatus);
		assertNotNull(prueba);

    }

    /**
     *   TEST obtenerContenidosInapropiados
     *   TODO: Implementar el test para el metodo obtenerContenidosInapropiados del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo obtenerContenidosInapropiados acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerContenidosInapropiados() {
    	 
    	ContenidoInapropiadoVO[] arcont = this.servicio.obtenerContenidosInapropiados(); 
		assertNotNull(arcont);

    }

    /**
     *   TEST obtenerContenidoInapropiadodeOde
     *   TODO: Implementar el test para el metodo obtenerContenidoInapropiadodeOde del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo obtenerContenidoInapropiadodeOde acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerContenidoInapropiadodeOde() {
		String prueba = null; 
		String idOde = "es_20081207_2_9145250";//"es_20081207_2_9145244";//
		String estado="PUBLICADO";
		boolean estado_ci=true; //false;
		String fecha_inactividad= "";//"2009-11-18 11:00:31";
		String idioma_cat="es";
		ContenidoInapropiadoVO[] ardetcont=this.servicio.obtenerContenidoInapropiadodeOde(idOde, estado, estado_ci, fecha_inactividad, idioma_cat);
		assertNotNull(ardetcont);

    }

    /**
     *   TEST eliminarContenidoInapropiado
     *   TODO: Implementar el test para el metodo eliminarContenidoInapropiado del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo eliminarContenidoInapropiado acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testeliminarContenidoInapropiado() {
		String prueba = "jjjddjd";
		String idOde="es_20081207_2_9145250";
		String estado="PUBLICADO";
		String idioma_cat="es";
		boolean estado_ci= true;//false;
		//con fecha
		//String fecha_inactividad= "2009-11-18 11:00:31";
		//sin fecha	
		String fecha_inactividad= "";
		this.servicio.eliminarContenidoInapropiado(idOde, fecha_inactividad, estado_ci, estado,idioma_cat);
		this.transactionManager.commit(this.transactionStatus);
		assertNotNull(prueba);

    }
   

    /**
     *   TEST rechazarContenidoInapropiado
     *   TODO: Implementar el test para el metodo rechazarContenidoInapropiado del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo rechazarContenidoInapropiado acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
//     public void testrechazarContenidoInapropiado() {
//		String prueba = null; 
//		String idOde="es_20081207_2_9145250";
//		String fecha_inactividad="";
//		boolean estado_ci=true;
//		String estado="PUBLICADO";
//		String idioma_cat="es";
//		//ESOS SON LOS DATOS DEL REPORTE DE CI A RECHAZAR
//		this.servicio.rechazarContenidoInapropiado(idOde, idioma_cat);
//		this.transactionManager.commit(this.transactionStatus);
//		assertNotNull(prueba);
//
//    }

    /**
     *   TEST despublicarContenidoInapropiado
     *   TODO: Implementar el test para el metodo despublicarContenidoInapropiado del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo despublicarContenidoInapropiado acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testdespublicarContenidoInapropiado() {
		String prueba = " sdsdsdf ";
		String idOde="es_20081207_2_9145249";
		String idioma_cat="es";
		this.servicio.despublicarContenidoInapropiado(idOde, idioma_cat);
		this.transactionManager.commit(this.transactionStatus);
		logger.info("hemos acabado la desp ");
		assertNotNull(prueba);

    }

    /**
     *   TEST despublicarContenidosInapropiados
     *   TODO: Implementar el test para el metodo despublicarContenidosInapropiados del servicio SrvContenidoInapropiadoService.
     * 	 En el caso de tener que el metodo despublicarContenidosInapropiados acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvContenidoInapropiadoServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testdespublicarContenidosInapropiados() {
		String prueba = null; 
		assertNotNull(prueba);

    }

	
	
	
}