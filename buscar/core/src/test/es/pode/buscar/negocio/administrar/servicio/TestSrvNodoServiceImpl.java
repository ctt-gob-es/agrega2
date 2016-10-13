/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.administrar.servicio;

import java.util.Date;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;



/**
 * @see SrvNodoServiceImpl
 */
public class TestSrvNodoServiceImpl
    extends SrvNodoServiceImplBase
{


	private String datasetFile = "SrvDataEmptyDataSet.xls";

    /**
     * Constructor
     */
     public TestSrvNodoServiceImpl(){
		super();
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

//    	 Conexion a la base de datos
   		connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
   		DatabaseConfig config = connection.getConfig();
   		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
   		
   		// Inicialización 
   		IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
   		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}

	
    /**
     *   TEST obtenerNodos
     *   TODO: Implementar el test para el metodo obtenerNodos del servicio SrvNodoService.
     */
     public void testlistarNodos() {
 		NodoVO[] nodoVOs = servicio.listarNodos();
		assertNotNull("La lista no es nula", nodoVOs);
		assertEquals("Se obtienen 5 resultados", nodoVOs.length, 5);
		assertEquals("El primer resultado es la ID 3", 1, nodoVOs[0].getId().longValue());

    }

    /**
     *   TEST crearNodo
     *   TODO: Implementar el test para el metodo crearNodo del servicio SrvNodoService.
     */
     public void testcrearNodo() {
    	 NodoVO nodo = new NodoVO();
    	 nodo.setNodo("nodo 1");
    	 nodo.setUrl("C:");
    	 servicio.crearNodo(nodo);
    	 //assertEquals("resultado crear nodo ", "OK", servicio.crearNodo(nodo));
    	 nodo.setNodo("nodo 2");
    	 nodo.setUrl("C://Documents and Settings");
    	 servicio.crearNodo(nodo);
    	 //assertEquals("resultado crear nodo ", "OK", servicio.crearNodo(nodo));
    	 nodo.setNodo("nodo 3");
    	 nodo.setUrl("C://Documents and Settings//evalverde");
    	 servicio.crearNodo(nodo);
    	 //assertEquals("resultado crear nodo ", "OK", servicio.crearNodo(nodo));
    	 nodo.setNodo("nodo 4");
    	 nodo.setUrl("C://Documents and Settings//evalverde//Escritorio");
    	 servicio.crearNodo(nodo);
    	 //assertEquals("resultado crear nodo ", "OK", servicio.crearNodo(nodo));
    	 nodo.setNodo("nodo 5");
    	 nodo.setUrl("C://Documents and Settings//evalverde//Escritorio//maven");
    	 servicio.crearNodo(nodo);
    	 NodoVO[] nodos = servicio.listarNodos();
    	 assertEquals("resultado crear nodo ", 6, nodos.length);
    }

    /**
     *   TEST modificarNodo
     *   TODO: Implementar el test para el metodo modificarNodo del servicio SrvNodoService.
     */
     public void testmodificarNodo() {
    	 NodoVO[] nodos = servicio.listarNodos();
    	 NodoVO nodo = new NodoVO();
    	 nodo.setId(1L);
    	 nodo.setNodo("nombre");
    	 nodo.setUrl("url");
    	 nodo.setFechaAlta(new Date());
    	 servicio.modificarNodo(nodo);
    	 nodos = servicio.listarNodos();
    	 assertEquals("resultado crear nodo ", 6, nodos.length);
    	 //assertEquals("OK", servicio.modificarNodo(nodo));
    	 
    }

    /**
     *   TEST eliminarNodo
     *   TODO: Implementar el test para el metodo eliminarNodo del servicio SrvNodoService.
     */
     public void testeliminarNodo() {
    	 testcrearNodo();
    	 NodoVO[] nodos = servicio.listarNodos();
    	 assertEquals("resultado crear nodo ", 6, nodos.length);
    	 Long[] ids = {1L, 2L, 3L};
    	 servicio.eliminarNodo(ids);
    	 nodos = servicio.listarNodos();
    	 assertEquals("resultado crear nodo ", 3, nodos.length);


    }

    /**
     *   TEST obtenerNodo
     *   TODO: Implementar el test para el metodo obtenerNodo del servicio SrvNodoService.
     */
     public void testobtenerNodo() {
    	 
    }
	
	
}