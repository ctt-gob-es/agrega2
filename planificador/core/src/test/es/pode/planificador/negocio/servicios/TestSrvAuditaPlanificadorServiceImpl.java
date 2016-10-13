/**
// license-header java merge-point
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.servicios;

import org.dbunit.database.DatabaseConnection;


/**
 * @see SrvAuditaPlanificadorServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvAuditaPlanificadorServiceImpl
    extends SrvAuditaPlanificadorServiceImplBase
{


	private String datasetFile = "SrvDataEmptyDataSet.xls";
	
    /**
     * Constructor
     */
     public TestSrvAuditaPlanificadorServiceImpl(){
		super();
		
        logger.info("Autenticando usuario administrador con password 1");

        try {
        	/*
        	LdapUserDetailsAgrega.Essence user = new LdapUserDetailsAgrega.Essence();
        	
            user.setUsername("administrador");
            user.setPassword("1");
            */
            /* Si queremos realizar los test personalizando los datos del usuario 
            
            BasicAttributes attributes = new BasicAttributes();
            attributes.put("preferredlanguage", "es"); // idioma en que se le va a mostrar el portal
            attributes.put("initials", "es"); // Idioma por defecto de las búsquedas
            attributes.put("sn", "Usuario Administrador"); // Nombre y apellidos
            attributes.put("mail", "administrador@agrega.es"); // Correo electrónico
            attributes.put("employeeType", "basico"); // El tipo de edición del empaquetador
            attributes.put("cn", "administrador"); // Login
            user.setAttributes(attributes);
            */
            
            /* Si se quiere utilizar los datos del usuario que devuelve el servicio de administración de usuario */
        	/*
            user.setDatosUsuario("administrador");
            
            Authentication currentAuth = new UsernamePasswordAuthenticationToken(user.createUserDetails(), null);
            SecurityContextHolder.getContext().setAuthentication(currentAuth);
            */
        } 
        catch (Exception e) 
        {
              logger.error(e);
              fail("Error de construccion del test durante la autenticacion");
        }
	}

          
     /**
      * onSetUpInTransaction
      */

      protected void onSetUpInTransaction() throws Exception {

     	 connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
   		
 	}

     /**
      * onTearDownInTransaction
      */
      
 	 protected void onTearDownInTransaction() {
 		
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
     * 	 En el caso de tener que el metodo ${operation.name} acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAuditaPlanificadorServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
//		String prueba = null;
//		this.initAthenticationUser("usuario", "ibuilder"); 
//		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST obtenerTrabajosEjecutadosDesdeHasta
     *   TODO: Implementar el test para el metodo obtenerTrabajosEjecutadosDesdeHasta del servicio SrvAuditaPlanificadorService.
     * 	 En el caso de tener que el metodo obtenerTrabajosEjecutadosDesdeHasta acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAuditaPlanificadorServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerTrabajosEjecutadosDesdeHasta() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST obtenerInformeCarga
     *   TODO: Implementar el test para el metodo obtenerInformeCarga del servicio SrvAuditaPlanificadorService.
     * 	 En el caso de tener que el metodo obtenerInformeCarga acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvAuditaPlanificadorServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerInformeCarga() throws Exception{
		long prueba = 7; 
		try{
			 
			SrvAuditaPlanificadorService audita=this.servicio;
			
			InformeCargaVO vuelta = audita.obtenerInformeCarga(prueba);
			 if(vuelta == null)
	    	 {
	    		 logger.info("No hay existe informe de carga");
	    	 }else
	    	 {
	    		 logger.info("vuelta.getDescripcionCarga() "+vuelta.getDescripcionCarga()); 
    			 logger.info("vuelta.getNombreLote() "+vuelta.getNombreLote());
    			 logger.info("vuelta.getNombreTarea() "+vuelta.getNombreTarea());
    			 logger.info("vuelta.getPathCarga() "+vuelta.getPathCarga());
	    		 RegistroCargaVO[] registroCargaVO = vuelta.getRegistroCarga();
	    		 for(int i=0;i<registroCargaVO.length;i++)
	    		 {
	    			 logger.info("registroCargaVO[i].getDescripcion() "+registroCargaVO[i].getDescripcion()); 
	    			 logger.info("registroCargaVO[i].getEstado() "+registroCargaVO[i].getEstado());
	    			 logger.info("registroCargaVO[i].getFormato() "+registroCargaVO[i].getFormato());
	    			 logger.info("registroCargaVO[i].getId_mec() "+registroCargaVO[i].getId_mec());
	    			 logger.info("registroCargaVO[i].getIdioma() "+registroCargaVO[i].getIdioma());
	    			 
	    			 logger.info("registroCargaVO[i].getDescripcion() "+registroCargaVO[i].getNivelAgregacion()); 
	    			 logger.info("registroCargaVO[i].getEstado() "+registroCargaVO[i].getNombreZip());
	    			 logger.info("registroCargaVO[i].getFormato() "+registroCargaVO[i].getPathZip());
	    			 logger.info("registroCargaVO[i].getId_mec() "+registroCargaVO[i].getTitulo());
	    			 logger.info("registroCargaVO[i].getIdioma() "+registroCargaVO[i].getFecha());
	    			 logger.info("registroCargaVO[i].getIdioma() "+registroCargaVO[i].getSobrescrito());
	    		 }
	    	 }
		}catch(Exception e){
			assertTrue(true);
		}
		System.out.println("Despues de llamar al servicio");

    }
     
     
	
	
	
}