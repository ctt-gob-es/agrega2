/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.indexador.negocio.servicios.busqueda;

import org.apache.log4j.Logger;

import org.dbunit.database.IDatabaseConnection;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Clase base para los test del los servicios. 
 *
 * @see AbstractTransactionalDataSourceSpringContextTests
 *
 * @see es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService
 */
public class SrvBuscadorServiceImplBase
    extends AbstractTransactionalDataSourceSpringContextTests
{

protected static Logger logger = Logger.getLogger(SrvBuscadorServiceImplBase.class);
    
   /** Aqui se deben de inyectar los beans a utilizar dentro del test
    *  Inyeccion del bean del Servicio es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService
    */
	protected es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService servicioIndexacion;
    protected es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService servicio;
	
    public es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getServicio() {
		return servicio;
	}
    
    public void setServicio(es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService servicio) {
		this.servicio = servicio;
	}
	
	public es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService getServicioIndexacion() {
		return this.servicioIndexacion;
	}

	public void setServicioIndexacion(es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService servicioIndexacion) {
		this.servicioIndexacion = servicioIndexacion;
	}
	/**
	 * Nombre de archivo que contiene el dataset de pruebas
	 */
	protected String datasetFile = "SrvDataEmptyDataSet.xls";
	IDatabaseConnection connection = null;
	
    protected void onSetUpInTransaction() throws Exception {
		//	Inicializamos la conexion a base de datos
//    	try{
////	    	connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
////	        // Inicializamos el dataset
////	        IDataSet dataSet =	new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
////	        DatabaseOperation.INSERT.execute(connection, dataSet);
//    	}catch(MySQLIntegrityConstraintViolationException e){
//    		logger.debug(e);
//    	}
    }
    
    protected void onTearDownInTransaction() {
	   	 try
		 { 
	   		// Inicializamos la conexion a base de datos
	/*	 	connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
		 	// Inicializamos el dataset
		 	IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
		 	DatabaseOperation.DELETE.execute(connection, dataSet);*/
		 }
		 catch (Throwable th)
		 {
			 th.printStackTrace();
		 }
    }
    
    protected String[] getConfigLocations() {
        return new String[] { "testContext.xml" , "user-applicationContext_indexador.xml","applicationContext-external_indexador.xml"};
    }


}