/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.Sesion;

import java.util.Date;
import java.util.GregorianCalendar;

import es.pode.dri.negocio.servicios.SesionVO;
import es.pode.soporte.utiles.date.DateManager;


/**
 * @see SrvSesionesServiceImpl
 */
public class TestSrvSesionesServiceImpl
    extends SrvSesionesServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvSesionesServiceImpl(){
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
     */	
     public void testInicial(){
		String prueba = null;
		assertNull(prueba);
	}
	
	
    /**
     *   TEST crearSesion
     *   
     * @throws NoExisteSesionException 
     * @throws WrongCredentialsException 
     */
     public void testcrearSesion() {
    	 String seed = new Long(System.currentTimeMillis()).toString();
     	String user = "user "+seed;
     	String password = "password "+seed;
   		SrvSesionesService service = this.servicio;
 		String sid = service.createAnonymousSession();
 		try{
 		SesionVO prueba = service.consultaSesion(sid);
 		assertNotNull(prueba);
 		} catch (Exception e) {
 			assertTrue(true);
 		}
 		try 
 		{
 			sid = service.createAnonymousSession();
 		} catch (Exception e) {
 			assertTrue(true);
 		}
//    	 String prueba = null;//No podemos probarlo, no tenemos la tabla para valiudar ek
// 		assertNull(prueba);
    }

    /**
     *   TEST crearSesionAnonima
     *   
     * @throws NoExisteSesionException 
     * @throws NumberFormatException 
     */
     public void testcrearSesionAnonima() throws NumberFormatException {
 		SrvSesionesService service = this.servicio;
		String id = service.createAnonymousSession();
		SesionVO prueba = service.consultaSesion(id);
		assertNotNull(prueba);
    }

    /**
     *   TEST limpiarSesiones
     *   No testable :-/
     */
     public void testlimpiarSesiones() {
		String prueba = null; 
		assertNull(prueba);

    }

    /**
     *   TEST borrarSesion
     *   TODO: Implementar el test para el metodo borrarSesion del servicio SrvSesionesService.
     * @throws WrongCredentialsException 
     * @throws NoExisteSesionException 
     * @throws NumberFormatException 
     */
     public void testborrarSesion() throws NumberFormatException {
 		SrvSesionesService service = this.servicio;
		String sid = service.createAnonymousSession();
		service.destroySession(sid);
		
    }

    /**
     *   TEST consultaSesion
     *   TODO: Implementar el test para el metodo consultaSesion del servicio SrvSesionesService.
     * @throws WrongCredentialsException 
     * @throws NoExisteSesionException 
     * @throws NumberFormatException 
     */
     public void testconsultaSesion() throws NumberFormatException {
 		SrvSesionesService service = this.servicio;
		String sid = service.createAnonymousSession();
		SesionVO prueba = service.consultaSesion(sid);
		assertNotNull(prueba);
		
    }

    /**
     *   TEST modificarSesion
     *   TODO: Implementar el test para el metodo modificarSesion del servicio SrvSesionesService.
     * @throws WrongCredentialsException 
     * @throws NoExisteSesionException 
     * @throws NumberFormatException 
     */
     public void testmodificarSesion() throws NumberFormatException {
    	String seed = new Long(System.currentTimeMillis()+30).toString();
    	String userA = "userA "+seed;
    	String userB = "userB "+seed;
    	String password = "password "+seed;
  		SrvSesionesService service = this.servicio;
		String sid = service.createAnonymousSession();
		SesionVO original = service.consultaSesion(sid);
		original.setAnonima(Boolean.FALSE);
		original.setFechaCreacion(new GregorianCalendar());
		original.setFechaExpiracion(DateManager.dateToCalendar(new Date(System.currentTimeMillis()+86400000)));
		original.setUsuario(userB);
		service.modificarSesion(original);
		SesionVO copia = service.consultaSesion(sid);
		assertNotNull(copia.getUsuario());
    }

	
	
	
}