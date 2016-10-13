// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.valoracion.negocio.servicios;


/**
 * @see SrvValoracionServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvValoracionServiceImpl
    extends SrvValoracionServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvValoracionServiceImpl(){
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
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNull(prueba);
	}
	
	
    /**
     *   TEST introducirComentario
     *   TODO: Implementar el test para el metodo introducirComentario del servicio SrvValoracionService.
     * 	 En el caso de tener que el metodo introducirComentario acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
//     FUNCIONA
     public void testintroducirComentario() {
    	 
    	 //Creamos un comentario

    	 String idODE="identificador_prueba_1";
    	 String idiomaODE = "es";
    	 String comentario="Comentario del ODE 1";
    	 String usuario = "Usuario que ha creado el comentario";
    	 servicio.introducirComentario(idODE, comentario, idiomaODE, usuario);   
    	 
//    	Llamo al metodo que devuelvo los comentarios
    	 ComentarioVO[] prueba=servicio.obtenerComentarios(idODE);
    	 
    	 //Comprobaciones
    	 assertTrue(prueba.length==1);
    	 assertEquals("Comprobacion idODE", prueba[0].getIdODE(), idODE);    	 
    	 assertEquals("Comprobacion comentario", prueba[0].getComentario(), comentario);
    	 assertEquals("Comprobacion valoracion", prueba[0].getUsuario(), usuario);    	     	 

    }

    /**
     *   TEST consultarValoracion
     *   TODO: Implementar el test para el metodo consultarValoracion del servicio SrvValoracionService.
     * 	 En el caso de tener que el metodo consultarValoracion acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testconsultarValoracion() {
		
    	 //	Introduzco 2 comentarios
    	 String idODE="identificador_prueba_1";
    	 String autor = "Autor de la valoracion";
    	 String idiomaODE = "es";
    	 Float valoracion=new Float(5.0); 
    	 servicio.introducirValoracion(valoracion, idODE, autor, idiomaODE);    	 
    	 
    	 
    	 Float valoracion_2=new Float(4.0); 
    	 String autor2 = "Autor2 de la valoracion";
    	 servicio.introducirValoracion(valoracion_2, idODE, autor2, idiomaODE);      	   
    	 
    	 Float pruebaFloat=new Float(4.5);
    	 
    	 //Llamamos al metodo que consulta la valoracion
    	 Float resultadoValoracion=servicio.consultarValoracion(idODE);
    	 assertEquals("Comprobacion valoracion", resultadoValoracion, pruebaFloat); 	   	 

    }

    /**
     *   TEST eliminarComentarios
     *   TODO: Implementar el test para el metodo eliminarComentarios del servicio SrvValoracionService.
     * 	 En el caso de tener que el metodo eliminarComentarios acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
//     FUNCIONA
     public void testeliminarComentarios() {
		
    	 //Primero creo comentarios: 3 pertenecientes a un ode y 1 perteneciente a otro ODE
    	 String idODE="identificador_prueba_1";
    	 String idiomaODE = "es";
    	 String comentario="Comentario del ODE 1";
    	 String usuario1 = "Usuario1"; 	 
    	 servicio.introducirComentario(idODE, comentario, idiomaODE, usuario1);
    	 
    	 String comentario_2="Comentario del ODE 2";
    	 String usuario2 = "Usuario2"; 	       	 
    	 servicio.introducirComentario(idODE, comentario_2, idiomaODE, usuario2);    	 
    	 
    	 String comentario_3="Comentario del ODE 3";
    	 String usuario3 = "Usuario3"; 	       	 
    	 servicio.introducirComentario(idODE, comentario_3, idiomaODE, usuario3);
    	 
    	 //Elimino dos comentarios
    	 Long[] id = new Long[2];
    	 id[0] = 9l;
    	 id[1] = 11l;
    	 
    	 //Elimino los comentarios del ODE que tiene 3
    	 servicio.eliminarComentarios(id);
    	 
    	 //Compruebo que hay un elemento
    	 ComentarioVO[] comments = servicio.obtenerComentarios(idODE);
    	 assertTrue(comments.length==1);
    	 assertEquals("Comprobacion idODE", comments[0].getIdODE(), idODE);    	
    	 assertEquals("Comprobacion comentario", comments[0].getComentario(), comentario_2);
    	 assertEquals("Comprobacion valoracion", comments[0].getUsuario(), usuario2);  
    	 
    }
    

    /**
     *   TEST recalcularValoracionODE
     *   TODO: Implementar el test para el metodo recalcularValoracionODE del servicio SrvValoracionService.
     * 	 En el caso de tener que el metodo recalcularValoracionODE acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testrecalcularValoracionODE() {
    	 
    	 

    }

    /**
     *   TEST obtenerComentarios
     *   TODO: Implementar el test para el metodo obtenerComentarios del servicio SrvValoracionService.
     * 	 En el caso de tener que el metodo obtenerComentarios acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvValoracionServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     //FUNCIONA
     public void testobtenerComentarios() {
//    	Introduzco 2 comentarios
    	 String idODE="identificador_prueba_1";
    	 String idiomaODE = "es";
    	 String comentario="Comentario del ODE 1";
    	 String usuario = "Usuario comentario 1";
    	 servicio.introducirComentario(idODE, comentario, idiomaODE, usuario);
    	 
    	 
    	 String comentario_2="Comentario del ODE 2";
    	 String usuario2 = "Usuario comentario 1";    	 
    	 servicio.introducirComentario(idODE, comentario_2, idiomaODE, usuario2);        	 
    	 
    	 //Llamo al metodo que devuelvo los comentarios
    	 ComentarioVO[] prueba=servicio.obtenerComentarios(idODE);
    	 
    	 //Comprobaciones
    	 assertTrue(prueba.length==2);
    	 assertEquals("Comprobacion idODE", prueba[0].getIdODE(), idODE);    	 
    	 assertEquals("Comprobacion comentario", prueba[0].getComentario(), comentario);
    	 assertEquals("Comprobacion valoracion", prueba[0].getUsuario(), usuario);    	 
    	 assertEquals("Comprobacion idODE", prueba[1].getIdODE(), idODE);    	 
    	 assertEquals("Comprobacion comentario", prueba[1].getComentario(), comentario_2);
    	 assertEquals("Comprobacion valoracion", prueba[1].getUsuario(), usuario2);

    }
     
    public void testintroducirValoracion(){
    	
    	 //Creamos una valoracion    	
	   	 String idODE="identificador_prueba_valoracion_1";
	   	 Float valoracion = new Float(3.0);	   	
	   	 String autor = "Autor que ha creado la valoracion";
	   	 String idiomaODE = "es";
	   	 servicio.introducirValoracion(valoracion, idODE, autor, idiomaODE);
	   	  
	   	 
	//   	Llamo al metodo que devuelvo los comentarios
	   	 ComentarioVO[] prueba=servicio.obtenerComentarios(idODE);
	   	 
	   	 //Comprobaciones
	   	 assertTrue(prueba.length==1);
	   	 assertEquals("Comprobacion idODE", prueba[0].getIdODE(), idODE);
	   	 
    }
	
    public void testtieneValoracion(){
	   	 String idODE="identificador_prueba_valoracion_1";
	   	 String autor = "Autor que ha creado la valoracion";
	   	 String idiomaODE = "es";
	   	 Boolean resultado = servicio.tieneValoracion(autor, idODE, idiomaODE);
	   	 
	   	 assertTrue(resultado.booleanValue());
	   	 
	   	 String idODE2="identificador_no_existe";
	   	 String autor2 = "Autor que ha creado la valoracion";
	   	 String idiomaODE2 = "es";
	   	 Boolean resultado2 = servicio.tieneValoracion(autor2, idODE2, idiomaODE2);
	   	 
	   	assertFalse(resultado2.booleanValue());
    }
	
	
}