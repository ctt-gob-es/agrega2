// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.tagging.negocio.servicios;


/**
 * @see SrvTaggingServerImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvTaggingServerImpl
    extends SrvTaggingServerImplBase
{


    /**
     * Constructor
     */
     public TestSrvTaggingServerImpl(){
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
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST introducirTag
     *   TODO: Implementar el test para el metodo introducirTag del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo introducirTag acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testintroducirTags() throws Exception {

    	 //Creamos un tag

    	 String idOde="identificador_prueba_1";
    	 String[] tag = new String[1];
    	 tag[0]= "etiqueta1";
    	 String idUsuario = "Usuario que ha creado el tag";
    	 String titulo= "Esto es un t�tulo";
    	 String idioma= "es";
    	 servicio.introducirTags(idOde, titulo, idioma, tag, idUsuario,null );
    	 
//    	Llamo al metodo que devuelve los tags
    	 TagVO[] tagging= servicio.obtenerTodosTags();
    	 
    	 //Comprobaciones
    	 assertTrue(tagging.length==1);
//    	 assertEquals("Comprobacion idOde", tagging[0], idOde);    	 
//    	 assertEquals("Comprobacion tag", tagging[0].getTag(), tag);
//    	 assertEquals("Comprobacion idUsuario", tagging[0].getIdUsuario(), idUsuario);

    }

    /**
     *   TEST obtenerTagsDeUsuario
     *   TODO: Implementar el test para el metodo obtenerTagsDeUsuario del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo obtenerTagsDeUsuario acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerTagsDeUsuario() {
    	 
    	 String idUsuario="nrm";
    	 
    	 TagVO[] lista= servicio.obtenerTagsDeUsuario(idUsuario);
    	 
    	 assertTrue(lista.length==5);
    	 assertEquals("Comprobacion tag", lista[0].getNombre(), "casa");

    }

    /**
     *   TEST obtenerOdesDeTagYUsuario
     *   TODO: Implementar el test para el metodo obtenerOdesDeTagYUsuario del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo obtenerOdesDeTagYUsuario acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerOdesDeTagYUsuario() {

    	 String idUsuario="idUsuario20";
    	 String tag= "tag20";
    	 
    	 TaggingVO[] lista= servicio.obtenerOdesDeTagYUsuario(tag, idUsuario);
    	 
    	 assertTrue(lista.length==1);
    	 assertEquals("Comprobacion usuario", lista[0].getIdUsuario(), idUsuario);
    	 assertEquals("Comprobacion tag", lista[0].getTag(), tag);

    }

    /**
     *   TEST obtenerTodosTags
     *   TODO: Implementar el test para el metodo obtenerTodosTags del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo obtenerTodosTags acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerTodosTags() {

    	 TagVO[] lista= servicio.obtenerTodosTags();
    	 
    	 assertTrue(lista.length==4);

    }

    /**
     *   TEST obtenerOdesDeTag
     *   TODO: Implementar el test para el metodo obtenerOdesDeTag del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo obtenerOdesDeTag acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testobtenerOdesDeTag() {

    	 String tag= "tag20";
    	 
    	 TaggingVO[] lista= servicio.obtenerOdesDeTag(tag);
    	 
    	 assertTrue(lista.length==1);
    	 assertEquals("Comprobacion tag", lista[0].getTag(), tag);

    }

    /**
     *   TEST eliminarTags
     *   TODO: Implementar el test para el metodo eliminarTags del servicio SrvTaggingServer.
     * 	 En el caso de tener que el metodo eliminarTags acceda a un webservice
     * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el m�todo 
     * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testeliminarTags() throws Exception{

//    	Primero se crean tags
    	 String idOde="identificador_prueba_1";
    	 String[] tag = new String[1];
    	 tag[0]= "etiqueta1";
    	 String idUsuario = "Usuario que ha creado el tag";
    	 String titulo= "Esto es un t�tulo";
    	 String idioma= "es";
    	 servicio.introducirTags(idOde, titulo, idioma, tag, idUsuario , null);
    	 
    	 String idOde2="identificador_prueba_1";
    	 String[] tag2 = new String[1];
    	 tag[0]= "etiqueta1";
    	 String idUsuario2 = "Usuario que ha creado el tag";
    	 String titulo2= "Esto es un t�tulo";
    	 String idioma2= "es";
    	 servicio.introducirTags(idOde2, titulo2, idioma2, tag2, idUsuario2 , null);   	 
    	 
    	 TagVO[] etiqueta= servicio.obtenerTagsDeUsuario(idUsuario2);
    	 
    	 //Elimino los comentarios del ODE que tiene 3
//    	 servicio.eliminarTags(etiqueta);
    	 
    	 //Compruebo que hay un elemento
    	 TagVO[] lista = servicio.obtenerTodosTags();
    	 assertTrue(lista.length==1);

    }
     
     public void testmodificarTag() throws Exception {
    	 
    	 String[] tags= new String[2];
    	 tags[0]= "perro";
    	 tags[1]= "gato";
    	 servicio.introducirTags("idOde1", "titulo del ODe", "es", tags, "user1" ,null);
    	 
    	 assertTrue(servicio.modificarTag("perro", "gato"));
    	 
    	 TagVO[] lista= servicio.obtenerTagsDeUsuario("user1");
    	 
     }
     
     public void testmodificarTagDeUsuario() throws Exception{
    	 
    	 String[] tags= new String[2];
    	 tags[0]= "perro";
    	 tags[1]= "gato";
    	 servicio.introducirTags("idOde1", "titulo del ODe", "es", tags, "user1" , null );
    	 
    	 assertTrue(servicio.modificarTagDeUsuario("perro", "gato", "user1"));
    	 
    	 TagVO[] lista= servicio.obtenerTagsDeUsuario("user1");
    	 
     }


      public void testobtenerTagsDeOde() {
     	 
     	 String idOde="idOde1";
     	 
     	 String[] lista= servicio.obtenerTagsDeOde(idOde);
     	 
     	 assertTrue(lista.length==5);
     	 assertEquals("Comprobacion tag", lista[0], "Prueba");

     }
      /**
       *   TEST eliminarTags
       *   TODO: Implementar el test para el metodo testeliminarTagsDeOdes del servicio SrvTaggingServer.
       * 	 En el caso de tener que el metodo testeliminarTagsDeOdes acceda a un webservice
       * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
       * 	 antes de invocarlo en el test, se debe establecer un usuario con
       * 	 derechos de acceso utilizando el m�todo 
       * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
       * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
       */
       public void testeliminarTagsDeOdes() {
    	   String[] idsOdes = {"identificador_prueba_1","Ode1","12344"};
    	   boolean resultado = servicio.eliminarTagsDeOdes(idsOdes);
    	   
    	   assertTrue(resultado);
    	   
       }
	
       /**
        *   TEST 
        *   TODO: Implementar el test para el metodo testeliminarTagsDeOdes del servicio SrvTaggingServer.
        * 	 En el caso de tener que el metodo testeliminarTagsDeOdes acceda a un webservice
        * 	 el cu�l implementa seguridad (se necesita la autenticaci�n de usuario),
        * 	 antes de invocarlo en el test, se debe establecer un usuario con
        * 	 derechos de acceso utilizando el m�todo 
        * 	 {@link es.indra.servicios.SrvTaggingServerImplBase#initAthenticationUser(String, String) 
        * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
        */
       public void testobtenerTagsLikeLetra() {
    	 //this.initAthenticationUser("usuario", "ibuilder");
      	 TaggingVO[] lista= servicio.obtenerTagsLikeLetra("u");
      	 boolean res=(lista!=null) && (lista.length>0);
      	 assertTrue(res);

      }

}