// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.gestorCorreo.negocio.servicios;

import java.io.File;
import java.rmi.RemoteException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

/**
 * @see SrvCorreoImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvCorreoImpl
    extends SrvCorreoImplBase
{       
    /**
     * Constructor
     */
     public TestSrvCorreoImpl(){
		super();
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
    	 File archivoOrigen = new File("D:\\PODE\\gestorCorreo\\core\\src\\test-resources\\uploads\\PlantillasCorreo\\");
    	 File archivoDestino = new File("D:\\PODE\\gestorCorreo\\uploads\\PlantillasCorreo\\");
    	 UtilesFicheros.copiar(archivoOrigen,archivoDestino);
    	 
		super.onSetUpInTransaction();
		
	}

    /**
     * onTearDownInTransaction
     */

	 protected void onTearDownInTransaction() {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
		 File archivoDestino = new File("D:\\PODE\\gestorCorreo\\uploads\\PlantillasCorreo\\");
		 try{
			 UtilesFicheros.eliminar(archivoDestino);
		 }catch (Exception e) {
			 assertTrue(true);
		}
	 	
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
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST correoEnviarAmigo
     *   TODO: Implementar el test para el metodo correoEnviarAmigo del servicio SrvCorreo.
     * 	 En el caso de tener que el metodo correoEnviarAmigo acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
     */
     public void testcorreoEnviarAmigo() throws RemoteException, Exception {
    	 String prueba = null; 
  		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
  		String correo="lnoya@indra.es";
  		String correo2="bytheradikal@gmail.com";
  		String setTo[]=new String [2];
  		setTo[0]=correo;
  		setTo[1]=correo2;

  		
  		correoOdeVO.setIdiomaCorreo("ca");
  		correoOdeVO.setFrom("bytheradical@msn.com");
  		correoOdeVO.setTo(setTo);
  		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
  		correoOdeVO.setComentario("Mira este ODE");
  		correoOdeVO.setEmailRemitente("lnoya@indra.es");
  		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
  		correoOdeVO.setNombreDestinatario("Raúl Perez; Ivan Sanchez");
  		correoOdeVO.setNombreRemitente("Luis Noya");
  		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
  		correoOdeVO.setUrlFicha("http://www.google.es");
  		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
  		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
  		
  		servicio.correoEnviarAmigo(correoOdeVO);
  		assertNotNull(prueba);

    }

    /**
     *   TEST correoContenidoInapropiado
     *   TODO: Implementar el test para el metodo correoContenidoInapropiado del servicio SrvCorreo.
     * 	 En el caso de tener que el metodo correoContenidoInapropiado acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
     */
     public void testcorreoContenidoInapropiado() throws RemoteException, Exception {
    	 String prueba = null; 
   		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
   		String correo="lnoya@indra.es";
   		String correo2="bytheradikal@gmail.com";
   		String setTo[]=new String [2];
   		setTo[0]=correo;
   		setTo[1]=correo2;

   		
   		correoOdeVO.setIdiomaCorreo("es");
   		correoOdeVO.setFrom("bytheradical@msn.com");
   		correoOdeVO.setTo(setTo);
   		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
   		correoOdeVO.setComentario("Mira este ODE");
   		correoOdeVO.setEmailRemitente("lnoya@indra.es");
   		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
   		correoOdeVO.setNombreDestinatario("Raúl Perez; Ivan Sanchez");
   		correoOdeVO.setNombreRemitente("Luis Noya");
   		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
   		correoOdeVO.setUrlFicha("http://www.google.es");
   		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
   		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
   		correoOdeVO.setComunidad("comunidad agrega");
   		correoOdeVO.setValoracion("Esta es la valoración");
   		servicio.correoContenidoInapropiado(correoOdeVO);
   		assertNotNull(prueba);

    }

    /**
     *   TEST altaUsuario
     *   TODO: Implementar el test para el metodo altaUsuario del servicio SrvCorreo.
     * 	 En el caso de tener que el metodo altaUsuario acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
     */
     public void testaltaUsuario() throws RemoteException, Exception {
    	 String prueba = null; 
 		CorreoUsuarioVO correoUsuarioVO = new CorreoUsuarioVO();
// 		String nombre="Ivan";
// 		String setTo[]=new String [1];
 		
 		
// 		setTo[0]=nombre;
 		
 		correoUsuarioVO.setIdiomaCorreo("es");
// 		correoVO.setTo(setTo);
 		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
 		correoUsuarioVO.setLdapExterno(true);
 		correoUsuarioVO.setEmailUsuario("lnoya@indra.es");
 		correoUsuarioVO.setNodoTaller(true);
 		correoUsuarioVO.setUsuario("Ivancillo");
 		correoUsuarioVO.setNuevaClave("nuevaClave");
 		servicio.altaUsuario(correoUsuarioVO);
 		assertNotNull(prueba);

    }

    /**
     *   TEST bajaUsuario
     *   TODO: Implementar el test para el metodo bajaUsuario del servicio SrvCorreo.
     * 	 En el caso de tener que el metodo bajaUsuario acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
     */
     public void testbajaUsuario() throws RemoteException, Exception {
		String prueba = null; 
		CorreoUsuarioVO correoUsuarioVO = new CorreoUsuarioVO();
		String nombre="Ivan";
		String setTo[]=new String [1];
		
		
		setTo[0]=nombre;
		
		correoUsuarioVO.setIdiomaCorreo("es");
		correoUsuarioVO.setFrom("Administrador");
		correoUsuarioVO.setTo(setTo);
		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
		correoUsuarioVO.setLdapExterno(true);
		correoUsuarioVO.setEmailUsuario("lnoya@indra.es");
		correoUsuarioVO.setNodoTaller(false);
		correoUsuarioVO.setUsuario("Ivancillo");
		servicio.bajaUsuario(correoUsuarioVO);
		assertNotNull(prueba);

    }

    /**
     *   TEST nuevaClave
     *   TODO: Implementar el test para el metodo nuevaClave del servicio SrvCorreo.
     * 	 En el caso de tener que el metodo nuevaClave acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
     */
     public void testnuevaClave() throws RemoteException, Exception {
    	 String prueba = null; 
  		CorreoUsuarioVO correoUsuarioVO = new CorreoUsuarioVO();
  		String nombre="Ivan";
  		String setTo[]=new String [1];
  		
  		
  		setTo[0]=nombre;
  		
  		correoUsuarioVO.setIdiomaCorreo("es");
  		correoUsuarioVO.setFrom("Administrador");
  		correoUsuarioVO.setTo(setTo);
  		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
  		correoUsuarioVO.setLdapExterno(false);
  		correoUsuarioVO.setEmailUsuario("lnoya@indra.es");
  		correoUsuarioVO.setNodoTaller(false);
  		correoUsuarioVO.setUsuario("Ivancillo");
  		correoUsuarioVO.setNuevaClave("nuevaClave");
  		servicio.nuevaClave(correoUsuarioVO);
  		assertNotNull(prueba);

    }
     
     /**
      *   TEST solicitarBaja
      *   TODO: Implementar el test para el metodo solicitarBaja del servicio SrvCorreo.
      * 	 En el caso de tener que el metodo nuevaClave acceda a un webservice
      * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
      * 	 antes de invocarlo en el test, se debe establecer un usuario con
      * 	 derechos de acceso utilizando el método 
      * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
      * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
      */
      public void testsolicitarBaja() throws RemoteException, Exception {
     	 String prueba = null; 
   		CorreoUsuarioVO correoUsuarioVO = new CorreoUsuarioVO();
   		String nombre="Ivan";
   		String setTo[]=new String [1];
   		
   		
   		setTo[0]=nombre;
   		
   		correoUsuarioVO.setIdiomaCorreo("es");
   		correoUsuarioVO.setFrom("Administrador");
   		correoUsuarioVO.setTo(setTo);
   		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
   		correoUsuarioVO.setLdapExterno(true);
   		correoUsuarioVO.setEmailUsuario("lnoya@indra.es");
   		correoUsuarioVO.setNodoTaller(false);
   		correoUsuarioVO.setUsuario("Ivancillo");
   		correoUsuarioVO.setNuevaClave("nuevaClave");
   		servicio.solicitudBajaUsuario(correoUsuarioVO);
   		assertNotNull(prueba);

     }
      
      /**
       *   TEST desactivarUsuario
       *   TODO: Implementar el test para el metodo desactivarUsuario del servicio SrvCorreo.
       * 	 En el caso de tener que el metodo desactivarUsuario acceda a un webservice
       * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
       * 	 antes de invocarlo en el test, se debe establecer un usuario con
       * 	 derechos de acceso utilizando el método 
       * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
       * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
       */
       public void testdesactivarUsuario() throws RemoteException, Exception {
      	 String prueba = null; 
   		CorreoUsuarioVO correoUsuarioVO = new CorreoUsuarioVO();
//   		String nombre="Ivan";
//   		String setTo[]=new String [1];
   		
   		
//   		setTo[0]=nombre;
   		
   		correoUsuarioVO.setIdiomaCorreo("es");
//   		correoVO.setTo(setTo);
   		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
   		correoUsuarioVO.setLdapExterno(false);
   		correoUsuarioVO.setEmailUsuario("lnoya@indra.es");
   		correoUsuarioVO.setNodoTaller(false);
   		correoUsuarioVO.setUsuario("Ivancillo");
   		correoUsuarioVO.setNuevaClave("nuevaClave");
   		servicio.desactivarUsuario(correoUsuarioVO);
   		assertNotNull(prueba);

      }
       
       /**
        *   TEST bajaGrupo
        *   TODO: Implementar el test para el metodo bajaGrupo del servicio SrvCorreo.
        * 	 En el caso de tener que el metodo bajaGrupo acceda a un webservice
        * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
        * 	 antes de invocarlo en el test, se debe establecer un usuario con
        * 	 derechos de acceso utilizando el método 
        * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
        * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     * @throws Exception 
     * @throws RemoteException 
        */
        public void testBajaGrupo() throws RemoteException, Exception {
       	 String prueba = null; 
    		CorreoGrupoVO correoGrupoVO = new CorreoGrupoVO();
    		String nombre="lnoya@indra.es";
    		String nombre2="bytheradikal@gmail.com";
    		String setTo[]=new String [2];
    		
    		
    		setTo[0]=nombre;
    		setTo[1]=nombre2;
    		
    		correoGrupoVO.setIdiomaCorreo("es");
    		correoGrupoVO.setTo(setTo);
    		correoGrupoVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
    		correoGrupoVO.setNombreGrupo("nombreGrupo");
    		servicio.bajaGrupo(correoGrupoVO);
    		assertNotNull(prueba);

       }
        
        /**
         *   TEST envioODEGrupo
         *   TODO: Implementar el test para el metodo envioODEGrupo del servicio SrvCorreo.
         * 	 En el caso de tener que el metodo envioODEGrupo acceda a un webservice
         * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
         * 	 antes de invocarlo en el test, se debe establecer un usuario con
         * 	 derechos de acceso utilizando el método 
         * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
         * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
         * @throws Exception 
         * @throws RemoteException 
         */
         public void testEnvioODEGrupo() throws RemoteException, Exception {
        	 String prueba = null; 
      		CorreoGrupoVO correoGrupoVO = new CorreoGrupoVO();
      		
      		String correo="lnoya@indra.es";
      		String correo2="bytheradikal@gmail.com";
      		String setTo[]=new String [2];
      		setTo[0]=correo;
      		setTo[1]=correo2;

      		
      		correoGrupoVO.setIdiomaCorreo("es");
      		correoGrupoVO.setFrom("bytheradical@msn.com");
      		correoGrupoVO.setTo(setTo);
      		correoGrupoVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
      		correoGrupoVO.setNombreGrupo("Nombre grupo");
      		correoGrupoVO.setHrefLogo("http://pruebas.agrega.indra.es/");
      		correoGrupoVO.setTituloOde("El niño, la madre y la paloma");
      		correoGrupoVO.setUrlFicha("http://www.google.es");
      		correoGrupoVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
      		correoGrupoVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
      		
      		servicio.envioODEGrupo(correoGrupoVO);
      		assertNotNull(prueba);
         }
         
         /**
          *   TEST testcomentarioODE
          *   TODO: Implementar el test para el metodo testcomentarioODE del servicio SrvCorreo.
          * 	 En el caso de tener que el metodo testcomentarioODE acceda a un webservice
          * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
          * 	 antes de invocarlo en el test, se debe establecer un usuario con
          * 	 derechos de acceso utilizando el método 
          * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
          * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
         * @throws Exception 
         * @throws RemoteException 
          */
          public void testcomentarioODE() throws RemoteException, Exception {
         	 String prueba = null; 
       		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
       		String correo="lnoya@indra.es";
       		String correo2="bytheradikal@gmail.com";
       		String setTo[]=new String [2];
       		setTo[0]=correo;
       		setTo[1]=correo2;

       		
       		correoOdeVO.setIdiomaCorreo("es");
       		correoOdeVO.setFrom("bytheradical@msn.com");
       		correoOdeVO.setTo(setTo);
       		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
       		correoOdeVO.setComentario("Este es el comentario del ODE");
       		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
       		correoOdeVO.setNombreDestinatario("Raúl Perez");
       		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
       		correoOdeVO.setUrlFicha("http://www.google.es");
       		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
       		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
       		
       		servicio.comentarioODE(correoOdeVO);
       		assertNotNull(prueba);
          }
          
          /**
           *   TEST PublicacionODE
           *   TODO: Implementar el test para el metodo PublicacionODE del servicio SrvCorreo.
           * 	 En el caso de tener que el metodo PublicacionODE acceda a un webservice
           * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
           * 	 antes de invocarlo en el test, se debe establecer un usuario con
           * 	 derechos de acceso utilizando el método 
           * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
           * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
         * @throws Exception 
         * @throws RemoteException 
           */
           public void testPublicacionODE() throws RemoteException, Exception {
          	 String prueba = null; 
        		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
        		String correo="lnoya@indra.es";
        		String correo2="bytheradikal@gmail.com";
        		String setTo[]=new String [2];
        		setTo[0]=correo;
        		setTo[1]=correo2;

        		
        		correoOdeVO.setIdiomaCorreo("es");
        		correoOdeVO.setFrom("bytheradical@msn.com");
        		correoOdeVO.setTo(setTo);
        		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
        		correoOdeVO.setComentario("Este es el comentario del ODE");
        		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
        		correoOdeVO.setNombreDestinatario("Raúl Perez");
        		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
        		correoOdeVO.setUrlFicha("http://www.google.es");
        		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
        		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
        		
        		servicio.publicacionODE(correoOdeVO);
        		assertNotNull(prueba);
           }
           
           /**
            *   TEST RechazoODE
            *   TODO: Implementar el test para el metodo RechazoODE del servicio SrvCorreo.
            * 	 En el caso de tener que el metodo RechazoODE acceda a un webservice
            * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
            * 	 antes de invocarlo en el test, se debe establecer un usuario con
            * 	 derechos de acceso utilizando el método 
            * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
            * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
         * @throws Exception 
         * @throws RemoteException 
            */
            public void testRechazoODE() throws RemoteException, Exception {
           	 String prueba = null; 
         		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
         		String correo="lnoya@indra.es";
         		String correo2="bytheradikal@gmail.com";
         		String setTo[]=new String [2];
         		setTo[0]=correo;
         		setTo[1]=correo2;

         		
         		correoOdeVO.setIdiomaCorreo("es");
         		correoOdeVO.setFrom("bytheradical@msn.com");
         		correoOdeVO.setTo(setTo);
         		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
         		correoOdeVO.setComentario("Este es el comentario del ODE");
         		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
         		correoOdeVO.setNombreDestinatario("Raúl Perez");
         		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
         		correoOdeVO.setUrlFicha("http://www.google.es");
         		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
         		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
         		
         		servicio.rechazoODE(correoOdeVO);
         		assertNotNull(prueba);
            }
            
            /**
             *   TEST ContactoPublicaODE
             *   TODO: Implementar el test para el metodo ContactoPublicaODE del servicio SrvCorreo.
             * 	 En el caso de tener que el metodo ContactoPublicaODE acceda a un webservice
             * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
             * 	 antes de invocarlo en el test, se debe establecer un usuario con
             * 	 derechos de acceso utilizando el método 
             * 	 {@link es.indra.servicios.SrvCorreoImplBase#initAthenticationUser(String, String) 
             * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
             * @throws Exception 
             * @throws RemoteException 
             */
             public void testContactoPublicaODE() throws RemoteException, Exception {
            	 String prueba = null; 
          		CorreoOdeVO correoOdeVO = new CorreoOdeVO();
          		String correo="lnoya@indra.es";
          		String correo2="bytheradikal@gmail.com";
          		String setTo[]=new String [2];
          		setTo[0]=correo;
          		setTo[1]=correo2;

          		
          		correoOdeVO.setIdiomaCorreo("es");
          		correoOdeVO.setFrom("bytheradical@msn.com");
          		correoOdeVO.setTo(setTo);
          		correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
          		correoOdeVO.setComentario("Este es el comentario del ODE");
          		correoOdeVO.setHrefLogo("http://pruebas.agrega.indra.es/");
          		correoOdeVO.setNombreDestinatario("Raúl Perez");
          		correoOdeVO.setTituloOde("El niño, la madre y la paloma");
          		correoOdeVO.setUrlFicha("http://www.google.es");
          		correoOdeVO.setUrlImagen("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
          		correoOdeVO.setUrlImagenLogo("http://desarrollo.agrega.indra.es/galeriaimg/62/es_20080721_3_9104918/es_20080721_3_9104918_captured.jpg");
          		
          		servicio.contactoPublicaODE(correoOdeVO);
          		assertNotNull(prueba);
             }
}