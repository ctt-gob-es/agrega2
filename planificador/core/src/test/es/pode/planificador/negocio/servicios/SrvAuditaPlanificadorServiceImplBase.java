/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.servicios;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Clase base para los test del los servicios. 
 *
 * @see AbstractTransactionalDataSourceSpringContextTests
 *
 * @see es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService
 */
public class SrvAuditaPlanificadorServiceImplBase
    extends AbstractTransactionalDataSourceSpringContextTests
{
		 protected static final Logger logger = Logger.getLogger(SrvAuditaPlanificadorServiceImplBase.class);
    
   /** Aqui se deben de inyectar los beans a utilizar dentro del test
    *  Inyeccion del bean del Servicio es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService
    */
    
    protected es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService servicio;
	public void setServicio(es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService servicio) {
		this.servicio = servicio;
	}
	/**
	 * Nombre de archivo que contiene el dataset de pruebas
	 */
	private String datasetFile = "SrvDataEmptyDataSet.xls";
	IDatabaseConnection connection = null;
	
    protected void onSetUpInTransaction() throws Exception {
		//	Inicializamos la conexion a base de datos
		connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
        // Inicializamos el dataset
        IDataSet dataSet =	new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
        DatabaseOperation.INSERT.execute(connection, dataSet);
    }
    
    protected void onTearDownInTransaction() {
	   	 try
		 { 
	   		// Inicializamos la conexion a base de datos
		 	connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
		 	// Inicializamos el dataset
		 	IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
		 	DatabaseOperation.DELETE.execute(connection, dataSet);
		 }
		 catch (Throwable th)
		 {
		 logger.error(th.getMessage(),th);
		 }
    }
    
    protected String[] getConfigLocations() {
        return new String[] { "testContext_planificador.xml" };
    }

	/**
	 * M�todo que inicializa un usuario para la autenticaci�n de la aplicaci�n.
	 * Este m�todo se utiliza en el caso de que se quiera acceder a un webservice
	 * que utiliza seguridad acegi.
	 * @param authenticationUser Nombre del usuario.
	 * @param authenticationPassword Password del usuario.
	 */
	protected void initAthenticationUser(String authenticationUser, String authenticationPassword) {
		UserDetails user = new User(authenticationUser, authenticationPassword,
				true, false, true, true, new GrantedAuthority[0]);
		Authentication currentAuth = new UsernamePasswordAuthenticationToken(
				user, null);
		SecurityContextHolder.getContext().setAuthentication(currentAuth);
	}
}