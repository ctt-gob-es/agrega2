/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.logs.servicio;

/**
 * @see SrvLogServiceImpl
 */
public class TestSrvLogServiceImpl
    extends SrvLogServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvLogServiceImpl(){
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
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST listarFicherosLog
     *   TODO: Implementar el test para el metodo listarFicherosLog del servicio SrvLogService.
     */
     public void testlistarFicherosLog() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST eliminarFicheroLog
     *   TODO: Implementar el test para el metodo eliminarFicheroLog del servicio SrvLogService.
     */
     public void testeliminarFicheroLog() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST recuperarFicheroLog
     *   TODO: Implementar el test para el metodo recuperarFicheroLog del servicio SrvLogService.
     */
     public void testrecuperarFicheroLog() {
		String prueba = null; 
		assertNotNull(prueba);

    }

	
	
	
}