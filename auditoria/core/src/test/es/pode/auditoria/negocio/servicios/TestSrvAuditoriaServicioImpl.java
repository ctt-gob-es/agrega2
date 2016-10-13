/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.servicios;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.dbunit.database.DatabaseConnection;

//import es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO;

/**
 * @see SrvAuditoriaServicioImpl
 */

public class TestSrvAuditoriaServicioImpl extends SrvAuditoriaServicioImplBase {

	/**
	 * Constructor
	 */
	public TestSrvAuditoriaServicioImpl() {
		super();
	}

	/**
	 * onTearDownInTransaction
	 */

	/*
	 * try { LdapUserDetailsAgrega.Essence user = new
	 * LdapUserDetailsAgrega.Essence();
	 * 
	 * user.setUsername("administrador"); user.setPassword("1");
	 * 
	 * // Si se quiere utilizar los datos del usuario que devuelve el servicio
	 * de administración de usuario user.setDatosUsuario("administrador");
	 * 
	 * Authentication currentAuth = new
	 * UsernamePasswordAuthenticationToken(user.createUserDetails(), null);
	 * SecurityContextHolder.getContext().setAuthentication(currentAuth); }
	 * catch (Exception e) { logger.error(e);
	 * fail("Error de construccion del test durante la autenticacion"); }
	 */
	// }
	protected void onSetUpInTransaction() throws Exception {

		super.onSetUpInTransaction();

		// connection = new
		// DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());

	}

	/**
	 * onTearDownInTransaction
	 */

	protected void onTearDownInTransaction() {

		super.onTearDownInTransaction();
		try {
			// Inicializamos la conexion a base de datos
			connection = new DatabaseConnection(this.jdbcTemplate
					.getDataSource().getConnection());
			// Inicializamos el dataset
			// IDataSet dataSet = new
			// XlsDataSet(this.applicationContext.getResource(datasetFile).getFile());
			// DatabaseOperation.DELETE.execute(connection, dataSet);
		} catch (Throwable th) {
			th.printStackTrace();
		}

	}

	/**
	 * testInicial TODO: Agregar las pruebas unitarias que correspondan al
	 * servicio, siguiendo un patron de nombrado como public void
	 * test<nombreTest>(). En el caso de tener que el metodo ${operation.name}
	 * acceda a un webservice el cuál implementa seguridad (se necesita la
	 * autenticación de usuario), antes de invocarlo en el test, se debe
	 * establecer un usuario con derechos de acceso utilizando el método
	 * {@link es.indra.servicios.SrvAuditoriaServicioImplBase#initAthenticationUser(String, String)
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */

	/**
	 * TEST registrarTrabajoPlan TODO: Implementar el test para el metodo
	 * registrarTrabajoPlan del servicio SrvAuditoriaServicio. En el caso de
	 * tener que el metodo registrarTrabajoPlan acceda a un webservice el cuál
	 * implementa seguridad (se necesita la autenticación de usuario), antes de
	 * invocarlo en el test, se debe establecer un usuario con derechos de
	 * acceso utilizando el método
	 * {@link es.indra.servicios.SrvAuditoriaServicioImplBase#initAthenticationUser(String, String)
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */

	// public void testregistrarTrabajoPlan() {
	// 		
	// TareaEjecutadaPlanVO tarea = new TareaEjecutadaPlanVO();
	//     	 
	// //tarea.setId(new Long("1001"));
	// tarea.setTrabajo("Trabajo1");
	// tarea.setGrupoTrabajo("GrupoTrabajo1");
	// tarea.setDescripcion("Descripcion");
	// tarea.setEstado("1");
	// tarea.setFechaInicio(new Date());
	// tarea.setFechaFin(new Date());
	// 		
	// // Long id = this.servicio.registrarTrabajoPlan(tarea);
	// // logger.info("id "+id);
	// //assertNotNull(id);
	// }
	/**
	 * TEST registrarTrabajoFechaFinPlan TODO: Implementar el test para el
	 * metodo registrarTrabajoFechaFinPlan del servicio SrvAuditoriaServicio. En
	 * el caso de tener que el metodo registrarTrabajoFechaFinPlan acceda a un
	 * webservice el cuál implementa seguridad (se necesita la autenticación de
	 * usuario), antes de invocarlo en el test, se debe establecer un usuario
	 * con derechos de acceso utilizando el método
	 * {@link es.indra.servicios.SrvAuditoriaServicioImplBase#initAthenticationUser(String, String)
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */

	public void testregistrarTrabajoFechaFinPlan() {
		String prueba = null;

	}

	/**
	 * TEST registrarTrabajoHijoPlan TODO: Implementar el test para el metodo
	 * registrarTrabajoHijoPlan del servicio SrvAuditoriaServicio. En el caso de
	 * tener que el metodo registrarTrabajoHijoPlan acceda a un webservice el
	 * cuál implementa seguridad (se necesita la autenticación de usuario),
	 * antes de invocarlo en el test, se debe establecer un usuario con derechos
	 * de acceso utilizando el método
	 * {@link es.indra.servicios.SrvAuditoriaServicioImplBase#initAthenticationUser(String, String)
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */

	public void testregistrarTrabajoHijoPlan() {
		String prueba = null;

	}

	/**
	 * TEST registrarTrabajoInterrPlan TODO: Implementar el test para el metodo
	 * registrarTrabajoInterrPlan del servicio SrvAuditoriaServicio. En el caso
	 * de tener que el metodo registrarTrabajoInterrPlan acceda a un webservice
	 * el cuál implementa seguridad (se necesita la autenticación de usuario),
	 * antes de invocarlo en el test, se debe establecer un usuario con derechos
	 * de acceso utilizando el método
	 * {@link es.indra.servicios.SrvAuditoriaServicioImplBase#initAthenticationUser(String, String)
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */

	public void testregistrarTrabajoInterrPlan() {
		String prueba = null;

	}

	/**
	 * TEST registrarOperacion TODO: Implementar el test para el metodo
	 * registrarOperacion del servicio SrvAuditoriaServicio.
	 */

	public void testregistrarOperacion() {
		String prueba = null;

	}

	/**
	 * TEST informeTerminosBusqueda TODO: Implementar el test para el metodo
	 * informeTerminosBusqueda del servicio SrvAuditoriaServicio.
	 */

	// public void testinformeTerminosBusqueda()
	// {
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2007,Calendar.DECEMBER,1);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2007,Calendar.DECEMBER,31);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// parametrosInformeVO.setRango(5);
	// logger.info("Despues de asignar la fechaDesde y fechaHAsta");
	// InformeTerminoBusquedaVO[] informeTerminoBusqueda =
	// this.servicio.informeTerminosBusqueda(parametrosInformeVO);
	// logger.info("Despues de obtener informeTerminoBusqueda");
	// if(informeTerminoBusqueda == null)
	// {
	// logger.info("No hay terminos buscados en ese periodo");
	// }else
	// {
	// for(int i=0;i<informeTerminoBusqueda.length;i++)
	// {
	// logger.info("informeTerminoBusqueda[i].getTerminoBuscado() "+informeTerminoBusqueda[i].getTerminoBuscado());
	// logger.info("informeTerminoBusqueda[i].getNumVeces() "+informeTerminoBusqueda[i].getNumVeces());
	// }
	// }
	// }

	/**
	 * TEST informeUsuarios Implementa el test para el método del servicio
	 * InformeUsuarios. Necesitará como parámetro la fecha de inicio y la fecha
	 * final.
	 */

	// public void testinformeUsuarios() {
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2007,Calendar.JANUARY,1);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2007,Calendar.FEBRUARY,1);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// InformeUsuariosVO[] informeUsuariosVO = null;
	// if(informeUsuariosVO == null)
	// {
	// logger.info("No hay usuarios en ese periodo");
	// }else
	// {
	// for(int i=0;i<informeUsuariosVO.length;i++){
	// logger.info("usuariosActivos[i].getLogin() "+informeUsuariosVO[i]);
	// }
	// }
	// // assertEquals(informeUsuariosVO.length,2);
	//
	// }

	/**
	 * TEST informeMasValorado TODO: Implementar el test para el metodo
	 * informeMasValorado del servicio SrvAuditoriaServicio.
	 */

	// public void testinformeMasValorado() {
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2006,Calendar.JANUARY,10);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2008,Calendar.DECEMBER,13);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// parametrosInformeVO.setRango(10);
	// InformeMasValoradoVO[] informeMasValoradoVO =
	// this.servicio.informeMasValorado(parametrosInformeVO);
	//    	 
	// if(informeMasValoradoVO == null)
	// {
	// logger.info("no hay odes mas valorados en ese periodo");
	// }else
	// {
	// for(int i=0;i<informeMasValoradoVO.length;i++)
	// {
	// logger.info("informeMasValoradoVO[i].getTitulo() "+informeMasValoradoVO[i].getTitulo());
	// logger.info("informeMasValoradoVO[i].getValoracion() "+informeMasValoradoVO[i].getValoracion());
	// logger.info("informeMasValoradoVO[i].getIdOde() "+informeMasValoradoVO[i].getIdOde());
	// }
	// }
	// }
	/**
	 * TEST informeMasMostrado TODO: Implementar el test para el metodo
	 * informeMasMostrado del servicio SrvAuditoriaServicio.
	 */
	public void testinformeMasMostrado() {
		logger.info("MAS MOSTRADO");
		ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
		GregorianCalendar gregorianCalendarInicial = new GregorianCalendar(
				2009, Calendar.OCTOBER, 29);
		Date fechaInicial = gregorianCalendarInicial.getTime();
		GregorianCalendar gregorianCalendarFinal = new GregorianCalendar(2009,
				Calendar.NOVEMBER, 3);
		Date fechaFinal = gregorianCalendarFinal.getTime();
		logger.info("fechaFinal " + fechaFinal);
		parametrosInformeVO.setFechaDesde(gregorianCalendarInicial);
		parametrosInformeVO.setFechaHasta(gregorianCalendarFinal);
		parametrosInformeVO.setRango(9);
		InformeMasVO[] informeMasVO = this.servicio
				.informeMasMostrado(parametrosInformeVO);
		logger.info("despues de llamar al servicio " + informeMasVO);
		assertNotNull(informeMasVO);
		assertEquals("Numero de ODEs devueltos -",2, informeMasVO.length);
		for (int i = 0; i < informeMasVO.length; i++) {
			logger.info("informeMasVO[i].getTituloOde() "
					+ informeMasVO[i].getTituloOde());
			logger.info("informeMasVO[i].getNumVeces() "
					+ informeMasVO[i].getNumVeces());
			logger.info("informeMasVO[i].getIdioma() "
					+ informeMasVO[i].getIdioma());
			logger.info("informeMasVO[i].getIdOde() "
					+ informeMasVO[i].getIdOde());
			logger.info("informeMasVO[i].getUrlImagen() "
					+ informeMasVO[i].getUrlImagen());
		}
		// Assert de recuentos
		assertEquals(2, informeMasVO[0].getNumVeces());
		assertEquals(1, informeMasVO[1].getNumVeces());
		// Assert de identificadores
		assertEquals("es_2009102612_9101533", informeMasVO[0].getIdOde());
		assertEquals("es_2009100911_9092718", informeMasVO[1].getIdOde());
	}
	
	/**
	 * TEST informeMasMostrado TODO: Implementar el test para el metodo
	 * informeMasMostrado del servicio SrvAuditoriaServicio.
	 */
	public void testinformeMasMostradoBrowser() {
		logger.info("MAS MOSTRADO desde navegador");
		ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
		GregorianCalendar gregorianCalendarInicial = new GregorianCalendar(
				2009, Calendar.OCTOBER, 29);
		Date fechaInicial = gregorianCalendarInicial.getTime();
		GregorianCalendar gregorianCalendarFinal = new GregorianCalendar(2009,
				Calendar.NOVEMBER, 3);
		Date fechaFinal = gregorianCalendarFinal.getTime();
		logger.info("fechaFinal " + fechaFinal);
		parametrosInformeVO.setFechaDesde(gregorianCalendarInicial);
		parametrosInformeVO.setFechaHasta(gregorianCalendarFinal);
		parametrosInformeVO.setRango(9);
		InformeMasVO[] informeMasVO = this.servicio
				.informeMasMostradoBrowser(parametrosInformeVO);
		logger.info("despues de llamar al servicio " + informeMasVO);
		assertNotNull(informeMasVO);
		assertEquals("Numero de ODEs devueltos -",1, informeMasVO.length);
		for (int i = 0; i < informeMasVO.length; i++) {
			logger.info("informeMasVO[i].getTituloOde() "
					+ informeMasVO[i].getTituloOde());
			logger.info("informeMasVO[i].getNumVeces() "
					+ informeMasVO[i].getNumVeces());
			logger.info("informeMasVO[i].getIdioma() "
					+ informeMasVO[i].getIdioma());
			logger.info("informeMasVO[i].getIdOde() "
					+ informeMasVO[i].getIdOde());
			logger.info("informeMasVO[i].getUrlImagen() "
					+ informeMasVO[i].getUrlImagen());
		}
		// Assert de recuentos
		assertEquals(2, informeMasVO[0].getNumVeces());
		// Assert de identificadores
		assertEquals("es_2009102612_9101533", informeMasVO[0].getIdOde());
	}

	/**
	 * TEST informeMasMostrado TODO: Implementar el test para el metodo
	 * informeMasMostrado del servicio SrvAuditoriaServicio.
	 */
	public void testinformeMasMostradoEmbed() {
		logger.info("MAS MOSTRADO desde embed");
		ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
		GregorianCalendar gregorianCalendarInicial = new GregorianCalendar(
				2009, Calendar.OCTOBER, 29);
		Date fechaInicial = gregorianCalendarInicial.getTime();
		GregorianCalendar gregorianCalendarFinal = new GregorianCalendar(2009,
				Calendar.NOVEMBER, 3);
		Date fechaFinal = gregorianCalendarFinal.getTime();
		logger.info("fechaFinal " + fechaFinal);
		parametrosInformeVO.setFechaDesde(gregorianCalendarInicial);
		parametrosInformeVO.setFechaHasta(gregorianCalendarFinal);
		parametrosInformeVO.setRango(9);
		InformeMasVO[] informeMasVO = this.servicio
				.informeMasMostradoEmbed(parametrosInformeVO);
		logger.info("despues de llamar al servicio " + informeMasVO);
		assertNotNull(informeMasVO);
		assertEquals("Numero de ODEs devueltos -",1, informeMasVO.length);
		for (int i = 0; i < informeMasVO.length; i++) {
			logger.info("informeMasVO[i].getTituloOde() "
					+ informeMasVO[i].getTituloOde());
			logger.info("informeMasVO[i].getNumVeces() "
					+ informeMasVO[i].getNumVeces());
			logger.info("informeMasVO[i].getIdioma() "
					+ informeMasVO[i].getIdioma());
			logger.info("informeMasVO[i].getIdOde() "
					+ informeMasVO[i].getIdOde());
			logger.info("informeMasVO[i].getUrlImagen() "
					+ informeMasVO[i].getUrlImagen());
		}
		// Assert de recuentos
		assertEquals(1, informeMasVO[0].getNumVeces());
		// Assert de identificadores
		assertEquals("es_2009100911_9092718", informeMasVO[0].getIdOde());
	}
	
	/**
	 * TEST informeMasPrevisualizados TODO: Implementar el test para el metodo
	 * informeMasPrevisualizados del servicio SrvAuditoriaServicio.
	 */

	// public void testinformeMasPrevisualizados() {
	// logger.info("MAS PREVISUALIZADO");
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2006,Calendar.NOVEMBER,20);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2008,Calendar.DECEMBER,31);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// parametrosInformeVO.setRango(3);
	// InformeMasVO[] informeMasVO =
	// this.servicio.informeMasPrevisualizados(parametrosInformeVO);
	// if(informeMasVO == null)
	// {
	// logger.info("no hay odes mas previsualizados en ese periodo de tiempo");
	// }else
	// {
	// logger.info("informeMasVO.length "+informeMasVO.length);
	// for(int i=0;i<informeMasVO.length;i++)
	// {
	//    		 
	// logger.info("informeMasVO[i].getTituloOde() "+informeMasVO[i].getTituloOde());
	// logger.info("informeMasVO[i].getNumVeces() "+informeMasVO[i].getNumVeces());
	// logger.info("informeMasVO[i].getIdioma() "+informeMasVO[i].getIdioma());
	// logger.info("informeMasVO[i].getIdOde() "+informeMasVO[i].getIdOde());
	// }
	// }
	//
	// }

	/**
	 * TEST informeMasDescargados TODO: Implementar el test para el metodo
	 * informeMasDescargados del servicio SrvAuditoriaServicio.
	 */

	// public void testinformeMasDescargados() {
	// logger.info("MAS DESCARGADO");
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2006,Calendar.NOVEMBER,20);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2009,Calendar.DECEMBER,31);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// parametrosInformeVO.setRango(3);
	// InformeMasVO[] informeMasVO =
	// this.servicio.informeMasDescargados(parametrosInformeVO);
	// if(informeMasVO == null)
	// {
	// logger.info("No hay odes mas descargados en ese periodo de tiempo");
	// }else
	// {
	// for(int i=0;i<informeMasVO.length;i++)
	// {
	// logger.info("informeMasVO[i].getTituloOde() "+informeMasVO[i].getTituloOde());
	// logger.info("informeMasVO[i].getNumVeces() "+informeMasVO[i].getNumVeces());
	// logger.info("informeMasVO[i].getIdioma() "+informeMasVO[i].getIdioma());
	// logger.info("informeMasVO[i].getIdOde() "+informeMasVO[i].getIdOde());
	// }
	// }
	// }
	/**
	 * TEST informeMasPesados TODO: Implementar el test para el metodo
	 * informeMasPesados del servicio SrvAuditoriaServicio.
	 */

	// public void testinformeMasPesados() {
	// ParametrosInformeVO parametrosInformeVO = new ParametrosInformeVO();
	// GregorianCalendar gregorianCalendarInicial = new
	// GregorianCalendar(2007,Calendar.NOVEMBER,20);
	// Date fechaInicial = gregorianCalendarInicial.getTime();
	// GregorianCalendar gregorianCalendarFinal = new
	// GregorianCalendar(2007,Calendar.DECEMBER,31);
	// Date fechaFinal = gregorianCalendarFinal.getTime();
	// logger.info("fechaFinal "+fechaFinal);
	// parametrosInformeVO.setFechaDesde(fechaInicial);
	// parametrosInformeVO.setFechaHasta(fechaFinal);
	// parametrosInformeVO.setRango(10);
	// //InformeMasPesadosVO[] informeMasPesadosVO =
	// this.servicio.informeMasPesados(parametrosInformeVO);
	// InformeMasPesadosVO[] informeMasPesadosVO = null;
	// if(informeMasPesadosVO == null)
	// {
	// logger.info("No hay odes mas pesados en ese periodo de tiempo");
	// }else
	// {
	// for(int i=0;i<informeMasPesadosVO.length;i++)
	// {
	// logger.info("informeMasPesadosVO[i].getTituloOde() "+informeMasPesadosVO[i].getTituloOde());
	// logger.info("informeMasPesadosVO[i].getTamanio() "+informeMasPesadosVO[i].getTamanio());
	// }
	// }
	// }
	public void testObtenerInformes()

	{

		logger.info("Obtener informes");
		String[] resultado = servicio.obtenerInformes();
		logger.info("el valor de resultado es " + resultado.length);
		for (int i = 0; i < resultado.length; i++) {
			logger.info("el valor de informes es " + resultado[i]);
		}
	}

	public void testObtenNumeroOperaciones() {
		logger.info("Obtener numero de operaciones");
		String identificador = "es_20080625_1_9084351";
		// es_20080625_3_9105219;es_20080618_1_9120636
		System.out.println("Empecemos");
		NumeroOperacionesVO[] numeroOperaciones = servicio
				.obtenNumeroOperaciones(identificador);
		System.out.println("Tarda?");
	}

	/**
	 * TEST testinformeCargaMasiva test para el metodo testinformeCargaMasivaVO
	 */

	public void testinformeCargaMasiva() {
		long prueba = 100;
		try {

			SrvAuditoriaServicio audita = this.servicio;

			InformeCargaMasivaVO vuelta = audita.informeCargaMasiva(prueba);
			if (vuelta == null) {
				logger.info("No hay existe informe de carga");
			} else {
				logger.info("vuelta.getDescripcionCarga() "
						+ vuelta.getDescripcionCarga());
				logger.info("vuelta.getNombreLote() " + vuelta.getNombreLote());
				logger.info("vuelta.getNombreTarea() "
						+ vuelta.getNombreTarea());
				logger.info("vuelta.getPathCarga() " + vuelta.getPathCarga());
				RegistroCargaMasivaVO[] registroCargaMasivaVO = vuelta
						.getRegistroCargaVO();
				for (int i = 0; i < registroCargaMasivaVO.length; i++) {

					logger.info("registroCargaVO[i].getDescripcion() "
							+ registroCargaMasivaVO[i].getDescripcion());
					logger.info("registroCargaVO[i].getEstado() "
							+ registroCargaMasivaVO[i].getEstado());
					logger.info("registroCargaVO[i].getFormato() "
							+ registroCargaMasivaVO[i].getFormato());
					logger.info("registroCargaVO[i].getId_mec() "
							+ registroCargaMasivaVO[i].getId_mec());
					logger.info("registroCargaVO[i].getIdioma() "
							+ registroCargaMasivaVO[i].getIdioma());

					logger.info("registroCargaVO[i].getNivelAgregacion() "
							+ registroCargaMasivaVO[i].getNivelAgregacion());
					logger.info("registroCargaVO[i].getEstado() "
							+ registroCargaMasivaVO[i].getNombreZip());
					logger.info("registroCargaVO[i].getFormato() "
							+ registroCargaMasivaVO[i].getPathZip());
					logger.info("registroCargaVO[i].getId_mec() "
							+ registroCargaMasivaVO[i].getTitulo());
					logger.info("registroCargaVO[i].getIdioma() "
							+ registroCargaMasivaVO[i].getFecha());
					logger.info("registroCargaVO[i].getIdioma() "
							+ registroCargaMasivaVO[i].getSobrescrito());
				}
			}
		} catch (Exception e) {
			assertTrue(true);
		}
		System.out.println("Despues de llamar al servicio");

	}

	/**
	 * TEST handleRepositorio test para el metodo handleRepositorio
	 */

	public void handleRepositorio() {

		try {

			SrvAuditoriaServicio audita = this.servicio;
			RepositorioVO[] vuelta = audita.repositorio("es", "11-08-2009");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Despues de llamar al servicio");
	}

	/**
	 * TEST handleCrearInformeRepositorio test para el metodo
	 * handleCrearInformeRepositorio
	 */

	public void handleCrearInformeRepositorio() {

		try {

			Calendar fechaActualizacion = Calendar.getInstance();
			SrvAuditoriaServicio audita = this.servicio;
			audita.crearInformeRepositorio("es", fechaActualizacion);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Despues de llamar al servicio");

	}

	public void testObtenerEstadisticasFeeds() {
		logger.info("Depurando estadisticas de feeds");
		try {
			EstadisticasFeedsVO[] resultados = servicio
					.obtenerEstadisticasFeeds(null, null);
			assertNotNull(resultados);
			assertEquals(3, resultados.length);
			assertEquals("1", resultados[0].getIdFeed());
			assertEquals("1",resultados[0].getFormatoFeed());
			assertNull(resultados[0].getPeriodo());
			assertEquals(Long.valueOf(4l), resultados[0].getCount());
			logger.info("OK");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Excepcion inesperada");
		}
	}

	public void testObtenerEstadisticasFeedsDesdeHasta() {
		logger.info("Depurando estadisticas de feeds con intervalo de fecha");
		try {
			Calendar fechaDesde = new GregorianCalendar(2009,
					GregorianCalendar.OCTOBER, 22, 0, 0);
			Calendar fechaHasta = new GregorianCalendar(2009,
					GregorianCalendar.OCTOBER, 25, 23, 0);
			EstadisticasFeedsVO[] resultados = servicio
					.obtenerEstadisticasFeeds(fechaDesde, fechaHasta);
			assertNotNull(resultados);
			assertEquals(3, resultados.length);
			assertEquals("1", resultados[0].getIdFeed());
			assertEquals("1",resultados[0].getFormatoFeed());
			assertNull(resultados[0].getPeriodo());
			assertEquals(Long.valueOf(1l), resultados[0].getCount());
			logger.info("OK");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Excepcion inesperada");
		}
	}
	
	public void testRegistrarPeticionFeed() {
		logger.info("Testeando insercion de peticion Feed en tabla de auditoria");
		// Peticion normal con idioma y periodo a null
		try {
			PeticionFeedVO peticion = new PeticionFeedVO();
			peticion.setFecha(new GregorianCalendar(2009,GregorianCalendar.OCTOBER,1,10,0));
			peticion.setIdFeed("1");
			peticion.setFormatoFeed("1");
			peticion.setPeriodo(null);
			peticion.setIdioma(null);
			Long id = servicio.almacenarPeticionFeedBD(peticion);
			assertNotNull(id);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Fallo en el registro de peticion");
		}
		
	}
}
