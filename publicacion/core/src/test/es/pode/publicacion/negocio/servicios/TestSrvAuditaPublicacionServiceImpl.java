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
 * @see SrvAuditaPublicacionServiceImpl
 */
public class TestSrvAuditaPublicacionServiceImpl
    extends SrvAuditaPublicacionServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvAuditaPublicacionServiceImpl(){
		super();
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
    	 //connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
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
     *   TEST actividadTransiciones
     *   TODO: Implementar el test para el metodo actividadTransiciones del servicio SrvAuditaPublicacionService.
     */
     public void testactividadTransiciones() {
		String prueba = null; 
		assertNull(prueba);

    }

    /**
     *   TEST dimensionesODEsPublicados
     *   TODO: Implementar el test para el metodo dimensionesODEsPublicados del servicio SrvAuditaPublicacionService.
     */
     public void testdimensionesODEsPublicados() {
		String prueba = null; 
		assertNull(prueba);

    }

    /**
     *   TEST odesPorEstados
     *   TODO: Implementar el test para el metodo odesPorEstados del servicio SrvAuditaPublicacionService.
     */
     public void testodesPorEstados() {
		String prueba = null; 
		assertNull(prueba);

    }

    /**
     *   TEST odesPorUsuario
     *   TODO: Implementar el test para el metodo odesPorUsuario del servicio SrvAuditaPublicacionService.
     */
     public void testodesPorUsuario() {
		String prueba = null; 
		assertNull(prueba);

    }

     /**
      *   TEST obtenerUltimosOdesPublicados
      *   TODO: Implementar el test para el metodo obtenerUltimosOdesPublicados del servicio SrvAuditaPublicacionService.
      */
      public void testobtenerUltimosOdesPublicados() {
 		int numeroOdes = 8;
 		//SrvPublicacionService publicador= this.servicio;
 		SrvAuditaPublicacionService publicacion = this.servicio;
 		OdePublicadoVO[] odesPublicados = publicacion.obtenerUltimosOdesPublicados(numeroOdes);
 		System.out.println("hay["+odesPublicados.length+"]");

     }

	
	
}