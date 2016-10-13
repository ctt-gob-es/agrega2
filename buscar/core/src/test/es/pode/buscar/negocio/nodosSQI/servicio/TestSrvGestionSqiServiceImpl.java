// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.nodosSQI.servicio;

/**
 * @see SrvGestionSqiServiceImpl
 * TODO: To execute test on services that invoke webservices which implement security,
 * you must include the next param to the java virtual machine:
 * -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvGestionSqiServiceImpl
    extends SrvGestionSqiServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvGestionSqiServiceImpl(){
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
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */	
     public void testInicial(){
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder"); 
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST altaNodoSQI
     *   TODO: Implementar el test para el metodo altaNodoSQI del servicio SrvGestionSqiService.
     * 	 En el caso de tener que el metodo altaNodoSQI acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
     public void testaltaNodoSQI() {
 		NodoSQIVO nodoSQI=new NodoSQIVO();
 		nodoSQI.setClave("larraitz");
 		nodoSQI.setDescripcionNodo("Nodo con lenguaje de respuesta LOM-es");
 		nodoSQI.setGestorSesion("http://desarrollo.agrega.indra.es:8080/dri-1/services/SrvSesionesService");
 		nodoSQI.setLenguajeConsulta("VSQL");
 		nodoSQI.setLenguajeRespuesta("LOM-es");
 		nodoSQI.setUrlServicio("http://desarrollo.agrega.indra.es:8080/dri-1/services/SrvSQIService");
 		nodoSQI.setUsuario("larraitz");
 		nodoSQI.setNombreNodo("Nodo_LOM-ES_Con_generacion_de_imagen");
 		nodoSQI.setImagenNodo("C:/Documents and Settings/leizmendi/Escritorio/WIKI/MANUAL/PrimerCiclo.JPG");
 		SrvGestionSqiService gestion=this.servicio;
 		Long identificador=gestion.altaNodoSQI(nodoSQI);
 		System.out.println("El identificador que devuelve es "+identificador);
     }
      
      public void testaltaNodoSQI1() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave1");
  		nodoSQI.setDescripcionNodo("prueba1");
  		nodoSQI.setId(2L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion1");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio1");
  		nodoSQI.setUsuario("test1");
  		nodoSQI.setNombreNodo("nombre1");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      
      public void testaltaNodoSQI2() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setDescripcionNodo("prueba2");
  		nodoSQI.setGestorSesion("gestorSesion2");
  		nodoSQI.setId(3L);
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio2");
  		nodoSQI.setNombreNodo("nombre2");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      
      public void testaltaNodoSQI3() {//Erroneo, tiene clave pero no tiene usuario
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave3");
  		nodoSQI.setDescripcionNodo("prueba3");
  		nodoSQI.setGestorSesion("gestorSesion3");
  		nodoSQI.setId(4L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion3");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio3");
  		nodoSQI.setNombreNodo("nombre3");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI4() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave4");
  		nodoSQI.setDescripcionNodo("prueba4");
  		nodoSQI.setGestorSesion("gestorSesion4");
  		nodoSQI.setId(5L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion4");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio4");
  		nodoSQI.setUsuario("test4");
  		nodoSQI.setNombreNodo("nombre4");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI5() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave5");
  		nodoSQI.setDescripcionNodo("prueba5");
  		nodoSQI.setGestorSesion("gestorSesion5");
  		nodoSQI.setId(100L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion5");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio5");
  		nodoSQI.setUsuario("test5");
  		nodoSQI.setNombreNodo("nombre5");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI6() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave6");
  		nodoSQI.setDescripcionNodo("prueba6");
  		nodoSQI.setGestorSesion("gestorSesion6");
  		nodoSQI.setId(7L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion7");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio7");
  		nodoSQI.setUsuario("test7");
  		nodoSQI.setNombreNodo("nombre7 añadido después");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI7() {
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave7");
  		nodoSQI.setDescripcionNodo("prueba7");
  		nodoSQI.setGestorSesion("gestorSesion7");
  		nodoSQI.setId(2L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion7");
  		nodoSQI.setLenguajeConsulta("VSQL");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio7");
  		nodoSQI.setUsuario("test7");
  		nodoSQI.setNombreNodo("nombre7");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI8() {//Erroneo, no es un lenguaje válido
  		NodoSQIVO nodoSQI=new NodoSQIVO();
  		nodoSQI.setClave("clave");
  		nodoSQI.setDescripcionNodo("prueba");
  		nodoSQI.setGestorSesion("gestorSesion");
  		nodoSQI.setId(1L);
  		nodoSQI.setIdentificadorSesion("identificadorSesion");
  		nodoSQI.setLenguajeConsulta("VSQLM");
  		nodoSQI.setLenguajeRespuesta("LOM");
  		nodoSQI.setUrlServicio("URL del servicio");
  		nodoSQI.setUsuario("test");
  		nodoSQI.setNombreNodo("nombre");
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.altaNodoSQI(nodoSQI);
  		System.out.println("El identificador que devuelve es "+identificador);
      }
      public void testaltaNodoSQI9() {//El identificador lo inserta directamente, no se lo tenemos que meter
   		NodoSQIVO nodoSQI=new NodoSQIVO();
   		nodoSQI.setClave("clave");
   		nodoSQI.setDescripcionNodo("prueba");
   		nodoSQI.setGestorSesion("gestorSesion");
   		nodoSQI.setIdentificadorSesion("identificadorSesion");
   		nodoSQI.setLenguajeConsulta("VSQL");
   		nodoSQI.setLenguajeRespuesta("LOM");
   		nodoSQI.setUrlServicio("URL del servicio");
   		nodoSQI.setUsuario("test");
   		nodoSQI.setNombreNodo("nombre");
   		SrvGestionSqiService gestion=this.servicio;
   		Long identificador=gestion.altaNodoSQI(nodoSQI);
   		System.out.println("El identificador que devuelve es "+identificador);
       }

    /**
     *   TEST bajaNodosSQI
     *   TODO: Implementar el test para el metodo bajaNodosSQI del servicio SrvGestionSqiService.
     * 	 En el caso de tener que el metodo bajaNodosSQI acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
      public void testbajaNodosSQI() {
  		Long[] ids=new Long[]{12L};
//  		ids[1]=new Long(7);
  		SrvGestionSqiService gestion=this.servicio;
    		gestion.bajaNodosSQI(ids);
    		NodoDescripcionSQIVO[] nodos = gestion.listarNodosSQI();
    		for(int i=0;i<nodos.length;i++){
    			System.out.println("Los nodos devueltos son:"+nodos[i].getId());
    		}

      }
       
       public void testbajaNodosSQI1() {
   		Long[] ids=new Long[2];
   		ids[0]=2L;
   		SrvGestionSqiService gestion=this.servicio;
     		gestion.bajaNodosSQI(ids);
     		NodoDescripcionSQIVO[] nodos = gestion.listarNodosSQI();
     		for(int i=0;i<nodos.length;i++){
     			System.out.println("Los nodos devueltos son:"+nodos[i].getId());
     		}

       }
       
       public void testbajaNodosSQI2() {
      		Long[] ids=new Long[]{1L,19L,20L,21L,22L,23L,24L,25L,26L,27L,28L,29L};
      		SrvGestionSqiService gestion=this.servicio;
        		gestion.bajaNodosSQI(ids);
        		NodoDescripcionSQIVO[] nodos = gestion.listarNodosSQI();
        		for(int i=0;i<nodos.length;i++){
        			System.out.println("Los nodos devueltos son:"+nodos[i].getId());
        		}

          }


    /**
     *   TEST consultaNodosSQI
     *   TODO: Implementar el test para el metodo consultaNodosSQI del servicio SrvGestionSqiService.
     * 	 En el caso de tener que el metodo consultaNodosSQI acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
       public void testconsultaNodosSQI() {//Aunque no existen los nodos no da error
      	 Long[] ids=new Long[]{2L,7L};
   		SrvGestionSqiService gestion=this.servicio;
     		NodoSQIVO[] nodos = gestion.consultaNodosSQI(ids);
     		for(int i=0;i<nodos.length;i++){
    			System.out.println("Los nodos devueltos son:"+nodos[i].getDescripcionNodo());
    		}

      }
       public void testconsultaNodosSQI1() {//Aunque no existen los nodos no da error
      	 Long[] ids=new Long[]{1L,3L};
   		SrvGestionSqiService gestion=this.servicio;
     		NodoSQIVO[] nodos = gestion.consultaNodosSQI(ids);
     		for(int i=0;i<nodos.length;i++){
    			System.out.println("Los nodos devueltos son:"+nodos[i].getDescripcionNodo());
    		}

      }
    /**
     *   TEST modificarNodoSQI
     *   TODO: Implementar el test para el metodo modificarNodoSQI del servicio SrvGestionSqiService.
     * 	 En el caso de tener que el metodo modificarNodoSQI acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
       public void testmodificarNodoSQI() {
      	 NodoSQIVO nodoSQI=new NodoSQIVO();
     		nodoSQI.setClave("claveModificado");
     		nodoSQI.setDescripcionNodo("pruebaModificado");
     		nodoSQI.setGestorSesion("gestorSesionModificado");
     		nodoSQI.setIdentificadorSesion("identificadorSesionModificado");
     		nodoSQI.setLenguajeConsulta("VSQL");
     		nodoSQI.setLenguajeRespuesta("LOM");
     		nodoSQI.setUrlServicio("URL del servicio modificado");
     		nodoSQI.setUsuario("test modificado");
     		nodoSQI.setNombreNodo("nombre modificado");
     		nodoSQI.setId(6L);
     		SrvGestionSqiService gestion=this.servicio;
     		Long identificador=gestion.modificarNodoSQI(nodoSQI);
     		NodoSQIVO[] arrayResultado=gestion.consultaNodosSQI(new Long[]{nodoSQI.getId()});
     		System.out.println("El identificador que devuelve es "+identificador);

      }
       public void testmodificarNodoSQI2() {
        	 NodoSQIVO nodoSQI=new NodoSQIVO();
       		nodoSQI.setClave("claveModificado");
       		nodoSQI.setDescripcionNodo("pruebaModificado");
       		nodoSQI.setGestorSesion("gestorSesionModificado");
       		nodoSQI.setIdentificadorSesion("identificadorSesionModificado");
       		nodoSQI.setLenguajeConsulta("VSQL");
       		nodoSQI.setLenguajeRespuesta("LOM");
       		nodoSQI.setUrlServicio("URL del servicio modificado");
       		nodoSQI.setUsuario("test modificado");
       		nodoSQI.setNombreNodo("nombre modificado");
       		nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificada.gif");
       		nodoSQI.setId(21L);
       		SrvGestionSqiService gestion=this.servicio;
       		Long identificador=gestion.modificarNodoSQI(nodoSQI);
       		NodoSQIVO[] arrayResultado=gestion.consultaNodosSQI(new Long[]{nodoSQI.getId()});
       		System.out.println("El identificador que devuelve es "+identificador);

        }

    /**
     *   TEST listarNodosSQI
     *   TODO: Implementar el test para el metodo listarNodosSQI del servicio SrvGestionSqiService.
     * 	 En el caso de tener que el metodo listarNodosSQI acceda a un webservice
     * 	 el cuál implementa seguridad (se necesita la autenticación de usuario),
     * 	 antes de invocarlo en el test, se debe establecer un usuario con
     * 	 derechos de acceso utilizando el método 
     * 	 {@link es.indra.servicios.SrvGestionSqiServiceImplBase#initAthenticationUser(String, String) 
     * 	 initAuthenticationUser(authenticationUser, authenticationPassword)}
     */
       public void testlistarNodosSQI() {
      	 SrvGestionSqiService gestion=this.servicio;
      	 	NodoDescripcionSQIVO[] nodos = gestion.listarNodosSQI();
      		for(int i=0;i<nodos.length;i++){
      			System.out.println("Los identficadores de los nodos devueltos son:"+nodos[i].getId()+" con nombre: "+ nodos[i].getDescripcionNodo());
      		}

      }
       public void testExisteNombreNodosSQI() {//Verificacion del nombre al crear,sin repetir
    	 SrvGestionSqiService gestion=this.servicio;
    	 Boolean vuelta = gestion.existeNombreNodoSQI("EdNa",-1L);
    		if(vuelta.booleanValue()==Boolean.TRUE){
    			logger.info("Existe un repositorio externo con ese nombre");
    		}else{
    			logger.info("No existe ningun repositorio externo con ese nombre");
    		}

        }
       public void testExisteNombreNodosSQI2() {//Verificacion del nombre al modificar el nodo, sin modificar el nombre
      	 SrvGestionSqiService gestion=this.servicio;
      	 Boolean vuelta = gestion.existeNombreNodoSQI("NODO SIN IMAGEN",33L);
      		if(vuelta.booleanValue()==true){
      			logger.info("Existe un repositorio externo con ese nombre");
      		}else{
      			logger.info("No existe ningun repositorio externo con ese nombre");
      		}

          }
       public void testExisteNombreNodosSQI3() {//Verificacion del nombre al modificar el nodo, modificando el nombre, sin repetir
      	 SrvGestionSqiService gestion=this.servicio;
      	 Boolean vuelta = gestion.existeNombreNodoSQI("nodo", 32L);
      		if(vuelta.booleanValue()==true){
      			logger.info("Existe un repositorio externo con ese nombre");
      		}else{
      			logger.info("No existe ningun repositorio externo con ese nombre");
      		}

          }
       public void testExisteNombreNodosSQI4() {//Verificacion del nombre al modificar el nodo, modificando el nombre, con repeticion
        	 SrvGestionSqiService gestion=this.servicio;
        	 Boolean vuelta = gestion.existeNombreNodoSQI("Nodo con imagen nombre CAMBIADO", 33L);
        		if(vuelta.booleanValue()==true){
        			logger.info("Existe un repositorio externo con ese nombre");
        		}else{
        			logger.info("No existe ningun repositorio externo con ese nombre");
        		}

            }
       public void testExisteNombreNodosSQI5() {//Verificacion del nombre al crear,con repeticion
      	 SrvGestionSqiService gestion=this.servicio;
      	 Boolean vuelta = gestion.existeNombreNodoSQI("NoDO sIn imagen cOn noMBre caMBiado",-1L);
      		if(vuelta.booleanValue()==true){
      			logger.info("Existe un repositorio externo con ese nombre");
      		}else{
      			logger.info("No existe ningun repositorio externo con ese nombre");
      		}

          }
       public void testaltaNodoSQISinImagen() {
      	NodoSQIVO nodoSQI=new NodoSQIVO();
      	nodoSQI.setUrlServicio("Url del servicio, el nodo sin imagen");
      	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo sin imagen");
      	nodoSQI.setLenguajeConsulta("VSQL");
      	nodoSQI.setLenguajeRespuesta("LOM-ES");
      	nodoSQI.setDescripcionNodo("Descripcion del nodo sin imagen");
      	nodoSQI.setUsuario("leizmendi");
      	nodoSQI.setClave("leizmendi");
      	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo sin imagen");
      	nodoSQI.setNombreNodo("Nodo sin imagen");
      	nodoSQI.setImagenNodo(null);
      	
 		SrvGestionSqiService gestion=this.servicio;
 		Long identificador=gestion.altaNodoSQI(nodoSQI);
 		
 		System.out.println("El identificador que devuelve es "+identificador);

      }
       public void testaltaNodoSQIConImagen() {
        	NodoSQIVO nodoSQI=new NodoSQIVO();
        	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen");
        	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen");
        	nodoSQI.setLenguajeConsulta("VSQL");
        	nodoSQI.setLenguajeRespuesta("LOM-ES");
        	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen");
        	nodoSQI.setUsuario("leizmendi");
        	nodoSQI.setClave("leizmendi");
        	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen");
        	nodoSQI.setNombreNodo("Nodo con imagen");
        	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificada.gif");
        	
   		SrvGestionSqiService gestion=this.servicio;
   		Long identificador=gestion.altaNodoSQI(nodoSQI);
   		
   		System.out.println("El identificador que devuelve es "+identificador);

        }
       public void testaltaNodoSQIConImagen2() {
         	NodoSQIVO nodoSQI=new NodoSQIVO();
         	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen");
         	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen");
         	nodoSQI.setLenguajeConsulta("VSQL");
         	nodoSQI.setLenguajeRespuesta("LOM-ES");
         	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen");
         	nodoSQI.setUsuario("leizmendi");
         	nodoSQI.setClave("leizmendi");
         	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen");
         	nodoSQI.setNombreNodo("Nodo con imagen2");
         	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificada2.gif");
         	
    		SrvGestionSqiService gestion=this.servicio;
    		Long identificador=gestion.altaNodoSQI(nodoSQI);
    		
    		System.out.println("El identificador que devuelve es "+identificador);

         }
       
       public void testmodificarNodoSQISinImagenNoNombreNoAnadirImagen() {
         	NodoSQIVO nodoSQI=new NodoSQIVO();
         	nodoSQI.setUrlServicio("Url del servicio, el nodo sin imagen y lo dejamos sin imagen");
         	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo sin imagen y lo dejamos sin imagen");
         	nodoSQI.setLenguajeConsulta("VSQL");
         	nodoSQI.setLenguajeRespuesta("LOM-ES");
         	nodoSQI.setDescripcionNodo("Descripcion del nodo sin imagen y lo dejamos sin imagen");
         	nodoSQI.setUsuario("leizmendi");
         	nodoSQI.setClave("leizmendi");
         	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo sin imagen y lo dejamos sin imagen");
         	nodoSQI.setNombreNodo("Nodo sin imagen");
         	nodoSQI.setId(33L);
         	nodoSQI.setImagenNodo(null);
         	
    		SrvGestionSqiService gestion=this.servicio;
    		Long identificador=gestion.modificarNodoSQI(nodoSQI);
    		
    		System.out.println("El identificador que devuelve es "+identificador);

         }
       
       public void testmodificarNodoSQISinImagenSiNombreNoAnadirImagen() {
        	NodoSQIVO nodoSQI=new NodoSQIVO();
        	nodoSQI.setUrlServicio("Url del servicio, el nodo sin imagen y lo dejamos sin imagen");
        	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo sin imagen y lo dejamos sin imagen");
        	nodoSQI.setLenguajeConsulta("VSQL");
        	nodoSQI.setLenguajeRespuesta("LOM-ES");
        	nodoSQI.setDescripcionNodo("Descripcion del nodo sin imagen y lo dejamos sin imagen");
        	nodoSQI.setUsuario("leizmendi");
        	nodoSQI.setClave("leizmendi");
        	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo sin imagen y lo dejamos sin imagen");
        	nodoSQI.setNombreNodo("Nodo sin imagen con nombre cambiado");
        	nodoSQI.setId(33L);
        	nodoSQI.setImagenNodo(null);
        	
   		SrvGestionSqiService gestion=this.servicio;
   		Long identificador=gestion.modificarNodoSQI(nodoSQI);
   		
   		System.out.println("El identificador que devuelve es "+identificador);

        }
       
       public void testmodificarNodoSQISinImagenNoNombreAnadirImagen() {
       	NodoSQIVO nodoSQI=new NodoSQIVO();
       	nodoSQI.setUrlServicio("Url del servicio, el nodo sin imagen y lo dejamos sin imagen");
       	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo sin imagen y lo dejamos sin imagen");
       	nodoSQI.setLenguajeConsulta("VSQL");
       	nodoSQI.setLenguajeRespuesta("LOM-ES");
       	nodoSQI.setDescripcionNodo("Descripcion del nodo sin imagen y lo dejamos sin imagen");
       	nodoSQI.setUsuario("leizmendi");
       	nodoSQI.setClave("leizmendi");
       	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo sin imagen y lo dejamos sin imagen");
       	nodoSQI.setNombreNodo("Nodo sin imagen con nombre cambiado");
       	nodoSQI.setId(33L);
       	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificada.gif");
       	
  		SrvGestionSqiService gestion=this.servicio;
  		Long identificador=gestion.modificarNodoSQI(nodoSQI);
  		
  		System.out.println("El identificador que devuelve es "+identificador);

       }
       
       public void testmodificarNodoSQISinImagenSiNombreAnadirImagen() {
          	NodoSQIVO nodoSQI=new NodoSQIVO();
          	nodoSQI.setUrlServicio("Url del servicio, el nodo sin imagen y lo dejamos sin imagen");
          	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo sin imagen y lo dejamos sin imagen");
          	nodoSQI.setLenguajeConsulta("VSQL");
          	nodoSQI.setLenguajeRespuesta("LOM-ES");
          	nodoSQI.setDescripcionNodo("Descripcion del nodo sin imagen y lo dejamos sin imagen");
          	nodoSQI.setUsuario("leizmendi");
          	nodoSQI.setClave("leizmendi");
          	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo sin imagen y lo dejamos sin imagen");
          	nodoSQI.setNombreNodo("Nodo sin imagen con nombre cambiado segunada vez");
          	nodoSQI.setId(33L);
          	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificadaSegundaVez.gif");
          	
     		SrvGestionSqiService gestion=this.servicio;
     		Long identificador=gestion.modificarNodoSQI(nodoSQI);
     		
     		System.out.println("El identificador que devuelve es "+identificador);

          }
       
       public void testmodificarNodoSQIConImagenNoNombreNoAnadirImagen() {
    	   	NodoSQIVO nodoSQI=new NodoSQIVO();
        	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen sin cambiar");
        	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen sin cambiar");
        	nodoSQI.setLenguajeConsulta("VSQL");
        	nodoSQI.setLenguajeRespuesta("LOM-ES");
        	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen sin cambiar");
        	nodoSQI.setUsuario("leizmendi");
        	nodoSQI.setClave("leizmendi");
        	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen sin cambiar");
        	nodoSQI.setNombreNodo("Nodo con imagen");
        	nodoSQI.setImagenNodo(null);
        	nodoSQI.setId(31L);
         	
    		SrvGestionSqiService gestion=this.servicio;
    		Long identificador=gestion.modificarNodoSQI(nodoSQI);
    		
    		System.out.println("El identificador que devuelve es "+identificador);

         }
       
       public void testmodificarNodoSQIConImagenSiNombreNoAnadirImagen() {
   	   	NodoSQIVO nodoSQI=new NodoSQIVO();
       	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen sin cambiar");
       	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen sin cambiar");
       	nodoSQI.setLenguajeConsulta("VSQL");
       	nodoSQI.setLenguajeRespuesta("LOM-ES");
       	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen sin cambiar");
       	nodoSQI.setUsuario("leizmendi");
       	nodoSQI.setClave("leizmendi");
       	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen sin cambiar");
       	nodoSQI.setNombreNodo("  Nodo con imagen nombre cambiado  ");
       	nodoSQI.setImagenNodo(null);
       	nodoSQI.setId(31L);
        	
   		SrvGestionSqiService gestion=this.servicio;
   		Long identificador=gestion.modificarNodoSQI(nodoSQI);
   		
   		System.out.println("El identificador que devuelve es "+identificador);

        }
       
       public void testmodificarNodoSQIConImagenNoNombreSiAnadirImagen() {
      	   	NodoSQIVO nodoSQI=new NodoSQIVO();
          	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen sin cambiar");
          	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen sin cambiar");
          	nodoSQI.setLenguajeConsulta("VSQL");
          	nodoSQI.setLenguajeRespuesta("LOM-ES");
          	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen sin cambiar");
          	nodoSQI.setUsuario("leizmendi");
          	nodoSQI.setClave("leizmendi");
          	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen sin cambiar");
          	nodoSQI.setNombreNodo("  Nodo con imagen nombre cambiado  ");
          	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificadaPrimeraVez.gif");
          	nodoSQI.setId(31L);
           	
      		SrvGestionSqiService gestion=this.servicio;
      		Long identificador=gestion.modificarNodoSQI(nodoSQI);
      		
      		System.out.println("El identificador que devuelve es "+identificador);

           }
       
       public void testmodificarNodoSQIConImagenSiNombreSiAnadirImagen() {
     	   	NodoSQIVO nodoSQI=new NodoSQIVO();
         	nodoSQI.setUrlServicio("Url del servicio, el nodo con imagen sin cambiar");
         	nodoSQI.setGestorSesion("Gestor de sesiones, el nodo con imagen sin cambiar");
         	nodoSQI.setLenguajeConsulta("VSQL");
         	nodoSQI.setLenguajeRespuesta("LOM-ES");
         	nodoSQI.setDescripcionNodo("Descripcion del nodo con imagen sin cambiar");
         	nodoSQI.setUsuario("leizmendi");
         	nodoSQI.setClave("leizmendi");
         	nodoSQI.setIdentificadorSesion("Identificador de sesiones para el nodo con imagen sin cambiar");
         	nodoSQI.setNombreNodo("  Nodo con imagen nombre cambiado segunda vez ");
         	nodoSQI.setImagenNodo("D:/rutaImagen/PruebaModificacion/imagenModificadaSegundaVez.gif");
         	nodoSQI.setId(31L);
          	
     		SrvGestionSqiService gestion=this.servicio;
     		Long identificador=gestion.modificarNodoSQI(nodoSQI);
     		
     		System.out.println("El identificador que devuelve es "+identificador);

          }
	
	
}