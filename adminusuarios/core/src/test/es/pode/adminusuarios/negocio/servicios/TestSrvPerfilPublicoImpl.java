// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.servicios;

import org.dbunit.database.DatabaseConnection;


/**
 * @see SrvPerfilPublicoImpl
 */
public class TestSrvPerfilPublicoImpl
    extends SrvPerfilPublicoImplBase
{

	protected es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService servicioAdmin;
	

    /**
     * Constructor
     */
     public TestSrvPerfilPublicoImpl(){
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
     *   TEST obtenerGrupoUsuario
     *   TODO: Implementar el test para el metodo obtenerGrupoUsuario del servicio SrvPerfilPublico.
     */
     public void testobtenerGrupoUsuario() {
		String prueba = null; 
		assertNotNull(prueba);

    }

     public void testcrearUsuarioPublico() {
 		UsuarioPublicoVO usuario=new UsuarioPublicoVO();
 		usuario.setCentroEducativo("Centro educativo de Madrid10");
 		usuario.setContacto(null);
 		usuario.setFavoritoUsuario(null);
 		String foto="Foto";
 		usuario.setFoto(foto);
 		usuario.setGrupoPublico(null);
 		usuario.setMostrarFavoritos(Boolean.TRUE);
 		usuario.setMostrarGrupos(Boolean.FALSE);
 		usuario.setMostrarObjetos(Boolean.TRUE);
 		
 		try {
 			 Long idUsuario=3l;
    		 this.servicio.crearUsuarioPublico(usuario,"leizmendi");
    		 logger.info("Hemos creado el usuario publico");
 
	   	 }
	   	 catch (Exception e)
	   	 {
	   		 fail("Error al llamar al servicio de adminUsuarios ");
	   		 logger.error("Error "+e);
	   	 }
   
		 assertNotNull("vuelta");

     }
     
     public void testmodificarUsuarioPublico() {
  		UsuarioPublicoVO usuario=new UsuarioPublicoVO();
  		usuario.setCentroEducativo("Centro educativo de Madrid10 Nuevo");
  		usuario.setContacto(null);
  		usuario.setFavoritoUsuario(null);
  		String foto="Foto Nueva";
  		usuario.setFoto(foto);
  		usuario.setGrupoPublico(null);
  		usuario.setMostrarFavoritos(Boolean.TRUE);
  		usuario.setMostrarGrupos(Boolean.TRUE);
  		usuario.setMostrarObjetos(Boolean.TRUE);
  		
  		try {
     		 this.servicio.modificarUsuarioPublico(usuario,"leizmendi");
     		 logger.info("Hemos creado el usuario publico");
     		 
 	   	 }
 	   	 catch (Exception e)
 	   	 {
 	   		 fail("Error al llamar al servicio de adminUsuarios ");
 	   		 logger.error("Error "+e);
 	   	 }
    
 		 assertNotNull("vuelta");

      }
     
//     protected final es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService getSrvAdminService()
//     throws java.lang.Exception
// {
// 	
//     String srvAdminServiceEndPointAddress="http://localhost:8080/adminusuarios/services/SrvAdminUsuariosService";
//     System.out.println("srvAdminServiceEndPointAddress del fichero --> " + srvAdminServiceEndPointAddress);
//     es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService srvAdminService = new es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosServiceLocator();                                                                                                                                                                                                                                                    
//     if (srvAdminServiceEndPointAddress.length()>0) 
//			((es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosServiceLocator)srvAdminService).setSrvAdminServiceEndpointAddress(srvAdminServiceEndPointAddress);
//     es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService port = srvAdminService.getSrvAdminService();	    
//     return port;
//     
// }
     
     
     public void testaltaUsuarioPublico() {
   		UsuarioPublicoVO usuario=new UsuarioPublicoVO();
   		usuario.setCentroEducativo("Centro educativo de Madrid10 Nuevo");
   		usuario.setContacto(null);
   		usuario.setFavoritoUsuario(null);
   		String foto="Foto Nueva";
   		usuario.setFoto(foto);
   		usuario.setGrupoPublico(null);
   		usuario.setMostrarFavoritos(Boolean.TRUE);
   		usuario.setMostrarGrupos(Boolean.TRUE);
   		usuario.setMostrarObjetos(Boolean.TRUE);
//   		String imagenURL="C:/apps/jboss-4.0.2/server/default/uploads/imagenesUsuariosPublicos/imagenDefectoUsuario.JPG";
//	   	 FormFile imagen = (FormFile)imagenURL;
//	     String imagenNodo = imagen.getFileName();
//	     DataHandler contenidoImagenNodo = null;
//	     
//	     if(imagen!=null && imagen.getFileName()!=null && !imagen.getFileName().equals("")){
//		    	InternetHeaders ih = new InternetHeaders();
//				MimeBodyPart mbp = new MimeBodyPart(ih, imagen.getFileData());
//				DataSource dsource = new MimePartDataSource(mbp);
//				contenidoImagenNodo = new DataHandler(dsource);
//				if(logger.isDebugEnabled())logger.debug("Creamos el datahandler de la imagen "+ contenidoImagenNodo);
//			}
   		try {
      		 this.servicio.modificarUsuarioPublico(usuario,"leizmendi");
      		 logger.info("Hemos creado el usuario publico");
      		 
  	   	 }
  	   	 catch (Exception e)
  	   	 {
  	   		 fail("Error al llamar al servicio de adminUsuarios ");
  	   		 logger.error("Error "+e);
  	   	 }
     
  		 assertNotNull("vuelta");

       }
     
     public void testListarUsuariosConContacto() {
    		
    		
    		try {
       		 UsuarioCorreoVO[] usuarios = this.servicio.listarUsuariosConContacto("leizmendi");
       		 logger.info("Hemos recibido "+usuarios.length+" usuarios");
       		 
   	   	 }
   	   	 catch (Exception e)
   	   	 {
   	   		 fail("Error al llamar al servicio de adminUsuarios ");
   	   		 logger.error("Error "+e);
   	   	 }
      
   		 assertNotNull("vuelta");

        }
     
     public void testListarUsuariosDeGrupoCorreo() {
 		
 		
 		try {
    		 UsuarioCorreoVO[] usuarios = this.servicio.listarUsuariosDeGrupoCorreo("Grupo nuevo 2");
    		 logger.info("Hemos recibido "+usuarios.length+" usuarios");
    		 
	   	 }
	   	 catch (Exception e)
	   	 {
	   		 fail("Error al llamar al servicio de adminUsuarios ");
	   		 logger.error("Error "+e);
	   	 }
   
		 assertNotNull("vuelta");

     }
     
     public void testBuscarGruposPorNombre() {
  		
  		
  		try {
     		 GrupoPublicoAsociadoVO[] grupos = this.servicio.buscarGruposPorNombre("leiz", "leizmendi");
     		 logger.info("Hemos recibido "+grupos.length+" grupos");
     		 
 	   	 }
 	   	 catch (Exception e)
 	   	 {
 	   		 fail("Error al llamar al servicio de adminUsuarios ");
 	   		 logger.error("Error "+e);
 	   	 }
    
 		 assertNotNull("vuelta");

      }
     
     public void testListarContactosDeAgenda() {
   		
   		
   		try {
      		 ContactoVO[] contactos = this.servicio.listarContactosDeAgenda("leizmendi");
      		 logger.info("Hemos recibido "+contactos.length+" contactos");
      		 
  	   	 }
  	   	 catch (Exception e)
  	   	 {
  	   		 fail("Error al llamar al servicio de adminUsuarios ");
  	   		 logger.error("Error "+e);
  	   	 }
     
  		 assertNotNull("vuelta");

       }
     
     public void testhandleListarUsuariosDeGrupo(){
    	 UsuarioPublicoVO[] usuarios = this.servicio.listarUsuariosDeGrupo("Creado");
    	 
    	 logger.info("El grupo Creado tiene  usuarios");
     }
     public void testEliminarUsuarioPublico(){
    	 
      		 ResultadoOperacionVO resultado = this.servicio.eliminarUsuarioPublico("bbb");
      		 logger.info("Hemos recibido "+resultado+" resutlado");
      		 
  	   	 
     }
     public void testhandleAsociarOdeAGrupo(){
    	 OdeGrupoVO ode=new OdeGrupoVO();
    	 ode.setId_mec("Id_mec añadido");
    	 ode.setIdioma("eu");
    	 ode.setTitulo("Titulo del ode");
    	 String[] nombres=new String[3];
    	 nombres[0]="Creado";
    	 nombres[1]="Grupo educativo cccccc";
    	 nombres[2]="Grupo nuevo 2";
    	 ResultadoOperacionVO[] vuelta=this.servicio.asociarOdeAGrupo(ode, nombres);
    	 
    	 logger.info("El grupo Creado tiene  usuarios");
     }
     
    
	
	
}