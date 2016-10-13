/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.instalador.negocio.servicios;

/**
 * @see SrvConfiguracionServiceImpl
 */
public class TestSrvConfiguracionServiceImpl
    extends SrvConfiguracionServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvConfiguracionServiceImpl(){
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
     *   TEST getAllProperties
     *   TODO: Implementar el test para el metodo getAllProperties del servicio SrvConfiguracionService.
     */
     public void testgetAllProperties() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST updatePropiedad
     *   TODO: Implementar el test para el metodo updatePropiedad del servicio SrvConfiguracionService.
     */
     public void testupdatePropiedad() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST getPropiedadByName
     *   TODO: Implementar el test para el metodo getPropiedadByName del servicio SrvConfiguracionService.
     */
     public void testgetPropiedadByName() {
		String prueba = null; 
		assertNotNull(prueba);

    }

	
	
	
}