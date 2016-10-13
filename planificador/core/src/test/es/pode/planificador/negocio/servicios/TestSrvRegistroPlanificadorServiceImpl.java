/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.servicios;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dbunit.database.DatabaseConnection;

/**
 * @see SrvRegistroPlanificadorServiceImpl
 */
public class TestSrvRegistroPlanificadorServiceImpl
    extends SrvRegistroPlanificadorServiceImplBase
{


	private String datasetFile = "SrvDataEmptyDataSet.xls";
	
    /**
     * Constructor
     */
     public TestSrvRegistroPlanificadorServiceImpl(){
		super();
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
    	 connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
	}

    /**
     * onTearDownInTransaction
     */

	 protected void onTearDownInTransaction() {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
	 	
		 try
     	 { 
     		 // Inicializamos la conexion a base de datos
     		 connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
     		 // Inicializamos el dataset
//     		 IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
//     		 DatabaseOperation.DELETE.execute(connection, dataSet);
     	 }
     	 catch (Throwable th)
     	 {
     		 th.printStackTrace();
     	 }
 		
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
     *   TEST registrarTrabajo
     *   
     */
     public void testregistrarTrabajo() 
     {
    	 TareaEjecutadaVO trabajo = new TareaEjecutadaVO();
    	 Long id = null;
    	 
    	 trabajo.setId(new Long("999"));
    	 trabajo.setTrabajo("Prueba1");
    	 trabajo.setDescripcion("descripcion1");
    	 trabajo.setEstado("1");
    	 trabajo.setGrupoTrabajo("Grupo trabajo1");
    	 trabajo.setUsuario("1234");
    	 
    	 try {
    		 id = this.servicio.registrarTrabajo(trabajo);
    	 }
    	 catch (Exception e)
    	 {
    		 fail("Error al llamar al servicio de registrar trabajo");
    	 }
    
		 assertNotNull(id);
    }

    /**
     *   TEST registrarTrabajoHijo
     *   
     
     public void testregistrarTrabajoHijo() 
     {
    	 TareaEjecutadaVO trabajoPadre = new TareaEjecutadaVO(); 
    	 RegistroTareaEjecutadaVO trabajoHijo = new RegistroTareaEjecutadaVO();
    	 Long id = null;
    	 
    	 trabajoPadre.setId(new Long("1"));
    	 trabajoHijo.setId(new Long("999"));
    	 trabajoHijo.setCodigo("0.0");
    	 trabajoHijo.setDescripcion("descripcion1");
    	 trabajoHijo.setEstado("1");
    	 trabajoHijo.setFecha(new Date());
    	 trabajoHijo.setTarea_ejecutada(trabajoPadre);

    	 try 
    	 {
    		 id = this.servicio.registrarTrabajoHijo(trabajoHijo);
    	 }
    	 catch (Exception e)
    	 {
    		 fail("Error al llamar al servicio de registrar trabajo hijo");
    	 }

		 assertNotNull(id);
    }
*/
    /**
     *   TEST registrarTrabajoFechaFin
     
     public void testregistrarTrabajoFechaFin()
     {
    	 TareaEjecutadaVO trabajo = new TareaEjecutadaVO();
    	 Long id = null;
    	 
    	 trabajo.setId(new Long("1"));
    	 trabajo.setTrabajo("Prueba1");
    	 trabajo.setDescripcion("descripcion1");
    	 trabajo.setEstado("1");
    	 trabajo.setGrupoTrabajo("Grupo trabajo1");
    	 trabajo.setUsuario(new Long("1234"));
    	 trabajo.setFechaFin(new Date());
    	 
    	 try
    	 {
    		 id = this.servicio.registrarTrabajoFechaFin(trabajo);
    	 }
    	 catch (Exception e)
    	 {
    		 fail("Error al llamar al servicio de registrar fin de trabajo");
    	 }

 		 assertNotNull(id);
    }
*/
    /**
     *   TEST registrarTrabajoInterrupido
     *   TODO: Implementar el test para el metodo registrarTrabajoInterrupido del servicio SrvRegistroPlanificadorService.
     */
     public void testregistrarTrabajoInterrupido() throws java.lang.Exception
     {
    	 Long numTareasInterrumpidas = this.servicio.registrarTrabajoInterrumpido();
    	 assertNotNull(numTareasInterrumpidas);
     }
     
     
     public void testRegistrarTrabajoHijos() throws java.lang.Exception
     {
    	 RegistroTareaEjecutadaVO registro1= new RegistroTareaEjecutadaVO();
    	 registro1.setDescripcion("Primer registro introducido");
    	 registro1.setEstado("OK");
    	 Calendar calendarioInicio = new GregorianCalendar(2011, Calendar.JANUARY, 1);
       	 calendarioInicio.set(calendarioInicio.HOUR_OF_DAY, 1);
       	 calendarioInicio.set(calendarioInicio.MINUTE, 1);
            calendarioInicio.set(calendarioInicio.SECOND, 1);
            calendarioInicio.set(calendarioInicio.MILLISECOND, 1);
    	 registro1.setFecha(calendarioInicio);
    	 TareaEjecutadaVO tarea_ejecutada=new TareaEjecutadaVO();
    	 tarea_ejecutada.setId(new Long(2));
    	 registro1.setTarea_ejecutada(tarea_ejecutada);
    	 RegistroTareaEjecutadaVO registro2= new RegistroTareaEjecutadaVO();
    	 registro2.setDescripcion("Segundo registro introducido");
    	 registro2.setEstado("ERROR");
    	 Calendar calendarioInicio2 = new GregorianCalendar(2012, Calendar.JANUARY, 1);
       	 calendarioInicio.set(calendarioInicio.HOUR_OF_DAY, 1);
       	 calendarioInicio.set(calendarioInicio.MINUTE, 1);
            calendarioInicio.set(calendarioInicio.SECOND, 1);
            calendarioInicio.set(calendarioInicio.MILLISECOND, 1);
    	 registro2.setFecha(calendarioInicio2);
    	 TareaEjecutadaVO tarea_ejecutada2=new TareaEjecutadaVO();
    	 tarea_ejecutada2.setId(new Long(2));
    	 registro2.setTarea_ejecutada(tarea_ejecutada2);
    	 RegistroTareaEjecutadaVO[] registros=new RegistroTareaEjecutadaVO[2];
    	 registros[0]=registro1;
    	 registros[1]=registro2;
    	 Long[] vuelta = this.servicio.registrarTrabajoHijos(registros);
    	 assertNotNull(vuelta);
     }
     
     public void testActualizarTrabajoTareaCarga() throws java.lang.Exception
     {
    	 String[] identificadores=new String[6];
    	 String id1="es_20070727_2_0130700";
    	 String id2="es_20070518_2_0040205";
    	 String id3="es_20070727_3_0130400";
    	 String id4="es_20070727_2_1235487";
    	 String id5="es_20070727_6_4564879";
    	 String id6="es_20070727_2_1234567";
    	 identificadores[0]=id1;
    	 identificadores[1]=id2;
    	 identificadores[2]=id3;
    	 identificadores[3]=id4;
    	 identificadores[4]=id5;
    	 identificadores[5]=id6;
    	 
    	 Long idTarea=new Long(97);
    	 this.servicio.actualizarTrabajoTareaCarga(identificadores, idTarea);
    	 logger.debug("Ya hemos actualizado las entradas en la base de datos");
     }
     

}