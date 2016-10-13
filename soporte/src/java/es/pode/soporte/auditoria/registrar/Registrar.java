// license-header java merge-point

package es.pode.soporte.auditoria.registrar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import es.pode.soporte.auditoria.servicios.BusquedaVO;
import es.pode.soporte.auditoria.servicios.OperacionVO;
import es.pode.soporte.auditoria.servicios.PeticionFeedVO;
import es.pode.soporte.auditoria.servicios.SrvAuditoriaServicio;
import es.pode.soporte.auditoria.servicios.SrvAuditoriaServicioServiceLocator;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.validador.ValidadorMEC;
import es.pode.soporte.validador.ValidadorStrategy;

/** 
 * Clase que se encarga del registro de los datos capturados en la intercepción
 * 
 * @author jsimon
 */

public class Registrar {
	
	private static Logger logger = Logger.getLogger(Registrar.class);
	static final String SEPARADOR = "_"; 	
	static final String VACIO = " ";
	
	
	
	
	/** Método centralizador que se encarga de distribuir en función de la operación realizada 
	 * 	@param datosInterceptados Objeto con los datos interceptados
	 * */
	public static void operacion(DatosVO datosInterceptados) throws Exception {
		
		if (logger.isDebugEnabled())
			hashMap2Traza(datosInterceptados.getValores());
		
		if (logger.isDebugEnabled())
			logger.debug("Módulo destino: " + datosInterceptados.getModuloDestino());
		
		if (RegistroCtes.OPERACION_BUSCAR.equals(datosInterceptados.getModuloDestino()))
			operacionBuscar(datosInterceptados);
		else if (RegistroCtes.OPERACION_BUSCAR_SQI.equals(datosInterceptados.getModuloDestino()))
			operacionBuscarSQI(datosInterceptados);
		else if (RegistroCtes.OPERACION_FICHA.equals(datosInterceptados.getModuloDestino())) 
			operacionFicha(datosInterceptados);
		else if (RegistroCtes.OPERACION_PREVISUALIZADO.equals(datosInterceptados.getModuloDestino())) 
			operacionPrevisualizar(datosInterceptados);
		else if (RegistroCtes.OPERACION_DESCARGADO.equals(datosInterceptados.getModuloDestino())) 
			operacionDescargado(datosInterceptados);		
		else if (RegistroCtes.OPERACION_ENVIAR_CORREO.equals(datosInterceptados.getModuloDestino())) 
			operacionEnviarCorreo(datosInterceptados);
		else if (RegistroCtes.OPERACION_PETICION_FEED.equals(datosInterceptados.getModuloDestino()))
			registrarPeticionFeed(datosInterceptados);
		else if (RegistroCtes.OPERACION_BUSQUEDA_FEED.equals(datosInterceptados.getModuloDestino()))
			registrarPeticionFeedBusqueda(datosInterceptados);
		else if (RegistroCtes.OPERACION_ACCESO_PORTADA.equals(datosInterceptados.getModuloDestino()))
			registrarPeticionAccesoPortada(datosInterceptados);
	}
	
	/** 
	 * Se registra el acceso a la portada.
	 * Tan solo se tracea
	 * 	@param datosInterceptados Tabla con los valores capturados
	 * */
	private static void registrarPeticionAccesoPortada(DatosVO datosInterceptados) {
//		En principio nos contentamos con trazar la operacion de peticion de portada
		String idioma = (String)datosInterceptados.getValores().get(RegistroCtes.PARAMETRO_IDIOMA);
		logger.info("Interceptada PETICION DE PORTADA en idioma["+idioma+"]");
	}

	/** 
	 * Se registra la búsqueda realizada. 
	 * No se realiza el registro si se está moviéndose por la paginación 
	 * 	@param datosInterceptados Tabla con los valores capturados
	 * */
	public static void operacionBuscar(DatosVO datosInterceptados) {
		
		String usuario = null;
		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();
		String ambito = getAmbito(valores);
		String valorBuscado = getValorBuscado(valores); 
		String tipoBusqueda = getTipoBusqueda(valores);
		String valoresInterceptados = getValoresInterceptados(valores);
		
		/* No registramos valores no controlados */
		if (VACIO.equals(ambito) || VACIO.equals(tipoBusqueda) || VACIO.equals(valorBuscado))
			return;

		if (LdapUserDetailsUtils.estaAutenticado()) 
			usuario = LdapUserDetailsUtils.getUsuario();
			
		BusquedaVO parametros = new BusquedaVO();		
		parametros.setTerminoBuscado(valorBuscado);
		parametros.setAmbito_busqueda(moduloDestino + SEPARADOR + ambito);
		parametros.setTipo_busqueda(tipoBusqueda);
		parametros.setTipo(valoresInterceptados); 
		parametros.setUsuario(usuario);
		parametros.setFecha(Calendar.getInstance());
		registrarBusqueda(parametros);		
	}
		
	/**
	 * Registro de la operación previsualizar
	 * 	@param datosInterceptados Tabla con los valores capturados
	 */
	public static void operacionPrevisualizar(DatosVO datosInterceptados) throws Exception {
		
		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();		
		
		String idODE = (String)valores.get(RegistroCtes.PARAMETRO_IDENTIFICADOR);
		String idioma = (String)valores.get(RegistroCtes.PARAMETRO_IDIOMA);
			
//		Añadida traza de localizacion de ACCESO A LA FICHA
		logger.info("Interceptada PREVISUALIZACION de ODE["+idODE+"] en idioma["+idioma+"]");
		registrarOperacion(idODE, idioma, moduloDestino);
	}

	/**
	 * Registro de la operación descargados
	 * 	@param datosInterceptados Tabla con los valores capturados
	 */
	public static void operacionDescargado(DatosVO datosInterceptados) throws Exception {
		
		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();
				
		String 	idODE = (String)valores.get(RegistroCtes.PARAMETRO_ID_ODE);
		String idioma = (String)valores.get(RegistroCtes.PARAMETRO_IDIOMA);//En la operación de solicitar metadato(ficha) nos interesa el idioma de búsqueda
		String tipoPIF = (String)valores.get(RegistroCtes.PARAMETRO_TIPO_PIF); // En la operación descargado de ODE también interesa el tipo de descarga
		
//		Añadida traza de localizacion de DESCARGA DE UN ODE
		logger.info("Interceptada DESCARGA de ODE["+idODE+"] en idioma["+idioma+"]");
		registrarOperacion(idODE, idioma, moduloDestino + SEPARADOR + tipoPIF);
	}
	
	/**
	 * Registro de la operación ficha
	 * 	@param datosInterceptados Tabla con los valores capturados
	 */
	public static void operacionFicha(DatosVO datosInterceptados) throws Exception {

		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();
		
		String fichaBusquedaSimpleAvanzada = (String)valores.get(RegistroCtes.PARAMETRO_TIPO_BUSQUEDA); 
		
		if(logger.isDebugEnabled())logger.debug("Valores ficha busqueda simple avanzada:  " + fichaBusquedaSimpleAvanzada);
		
		/* Nos aseguramos que en la ficha es la llamada que nos interesa */
		
		if(RegistroCtes.VALORES_FICHA_EMBED.equals(fichaBusquedaSimpleAvanzada)) {
			/*
			 * Compruebo si la ficha se ha accedido desde un embed. En ese caso,
			 * modifico moduloDestino para que registre como ficha_embed la
			 * operacion.
			 */
			moduloDestino = RegistroCtes.OPERACION_FICHA_EMBED;
		}
		else if (!RegistroCtes.VALORES_FICHA_SIMPLEAVANZADA.equals(fichaBusquedaSimpleAvanzada))
			/* No es peticion de ficha ni desde embed ni desde navegador */
			return;
		
		
		
		String idODE = (String)valores.get(RegistroCtes.PARAMETRO_IDENTIFICADOR_ODE); 
		String idioma = (String)valores.get(RegistroCtes.PARAMETRO_IDIOMA);//En la operación de solicitar metadato(ficha) nos interesa el idioma de búsqueda
		
//		Añadida traza de localizacion de ACCESO A LA FICHA
		logger.info("Interceptada consulta de FICHA de ODE["+idODE+"] en idioma["+idioma+"]");
		
		registrarOperacion(idODE, idioma, moduloDestino);
	}

    /**
	 * Registro de la operación de envío de correo
	 * 	@param datosInterceptados Tabla con los valores capturados
	 */
	public static void operacionEnviarCorreo(DatosVO datosInterceptados) throws Exception {

		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();
		
		String idODE = (String)valores.get(RegistroCtes.PARAMETRO_IDENTIFICADOR_ODE); 
		String idioma = (String)valores.get(RegistroCtes.PARAMETRO_IDIOMA);
			
		registrarOperacion(idODE, idioma, moduloDestino);
	}

	/**
	 * La operación realizada es una búsqueda por SQL
	 * @param datosInterceptados
	 */
	public static void operacionBuscarSQI(DatosVO datosInterceptados) {
		
		String usuario = null;
		String moduloDestino = datosInterceptados.getModuloDestino();
		HashMap valores = datosInterceptados.getValores();
		String palabrasClave = null;
		String query = (String)valores.get(RegistroCtes.PARAMETRO_QUERY);
		String idiomaBusqueda = ((String)valores.get(RegistroCtes.PARAMETRO_IDIOMA_BUSQUEDA));
		
		if (RegistroCtes.SQI_LANG.equals(idiomaBusqueda)) {
			if(logger.isDebugEnabled())logger.debug("Parseando query SQL_LANG: " + idiomaBusqueda + " query: " + query);
			palabrasClave = parsearVSQL(query);
		}
		else if (RegistroCtes.LUCENE_LANG.equals(idiomaBusqueda)) {
			logger.warn("No se contempla la búsqueda a través de Lucene Query Syntax");
			/* Hay que importar las librerías de Lucene. De momento no se hace ya que no hay que dar esta funcionalidad
			QueryParser parser = new QueryParser(props.getProperty("campo_titulo"), new StandardAnalyzer());
			parser.setLowercaseExpandedTerms(true);
			Query lqsQuery = parser.parse(unparsedQuery);
			query.add(lqsQuery);
			*/
		}
		
		if (LdapUserDetailsUtils.estaAutenticado()) 
			usuario = LdapUserDetailsUtils.getUsuario(); 

		if(logger.isDebugEnabled())logger.debug("Registro de la búsqueda SQI. PalabrasClave " + palabrasClave + " usuario: " + usuario);
		
		BusquedaVO parametros = new BusquedaVO();		
		parametros.setTerminoBuscado(palabrasClave);
		parametros.setUsuario(usuario);
		parametros.setFecha(Calendar.getInstance());
		parametros.setTipo_busqueda(moduloDestino);
		registrarBusqueda(parametros);		
	}
	
	
	/**
	 * Metodo copiado de SrvBuscadorServiceImpl para parseo de VSQI. TODO Corregir para
	 * hacer un parseo XML mas fiable.
	 * 
	 * @param queryStatement
	 * @return
	 */
	private static String parsearVSQL(String queryStatement) {
		
		String c = queryStatement.substring(13, queryStatement.length() - 14);
		StringBuffer terminosBuscados = new StringBuffer();
		String palabrasBuscadas = null;
		
		if(logger.isDebugEnabled())logger.debug("Query: " + queryStatement);
		
		while (c.startsWith("<term>")) {
			String term = c.substring(6, c.indexOf("</term>"));
			terminosBuscados.append(term);
			terminosBuscados.append(" ");
			c = c.substring(13 + term.length());
		}
		
		palabrasBuscadas = terminosBuscados.deleteCharAt(terminosBuscados.length()).toString();		
		if(logger.isDebugEnabled())logger.debug(palabrasBuscadas);
		
		return palabrasBuscadas;
	}
	
	/**
	 * Registro de una peticion a un feed RSS/Atom
	 * 
	 * @param datosInterceptados
	 */
	public static void registrarPeticionFeed(DatosVO datosInterceptados) {
		String id = (String)datosInterceptados.getValores().get(RegistroCtes.PARAMETRO_IDFEED);
		String idioma = (String)datosInterceptados.getValores().get(RegistroCtes.PARAMETRO_IDIOMA);
		Calendar fecha = new GregorianCalendar();
		if(logger.isDebugEnabled()) logger.debug("Interceptada llamada a obtenerXMLFeed -> id="+id+"; idioma="+idioma+"fecha="+fecha.getTime());
		/*
		 * Parseo el id para obtener idFeed, formato y periodo
		 */
		try {
			String[] triplete = id.split("\\.");
			String idFeed = triplete[0];
			String formato = triplete[1];
			String periodo = null;
			if(triplete.length>2) periodo = triplete[2];
			
			if(logger.isDebugEnabled()) logger.debug("recuperando servicio de auditoria...");
			PeticionFeedVO peticionFeed = new PeticionFeedVO();
			peticionFeed.setIdFeed(idFeed);
			peticionFeed.setFormatoFeed(formato);
			peticionFeed.setPeriodo(periodo);
			peticionFeed.setFecha(fecha);
			peticionFeed.setIdioma(idioma);
			getSrvAuditoriaService().registrarPeticionFeed(peticionFeed);
			
		} catch (Exception e) {
			logger.error("Error durante el registro de la intercepcion de obtenerXMLFeed -> id="+id+"; idioma="+idioma+"fecha="+fecha.getTime(),e);
		}
		//TODO Terminar registro de FEED
	}
	
	/**
	 * Registro de una peticion a una busqueda RSS/Atom
	 * 
	 * @param datosInterceptados
	 */
	public static void registrarPeticionFeedBusqueda(DatosVO datosInterceptados) {
		String idioma = (String)datosInterceptados.getValores().get(RegistroCtes.PARAMETRO_IDIOMANAVEGACION);
		String formatoFeed = (String)datosInterceptados.getValores().get(RegistroCtes.PARAMETRO_FORMATOFEED);
		String idFeed = RegistroCtes.ID_FEED_BUSQUEDAS;
		Calendar fecha = new GregorianCalendar();
		if(logger.isDebugEnabled()) logger.debug("Interceptada llamada a devuelveRSSBusqueda -> id="+idFeed+"; idioma="+idioma+"fecha="+fecha.getTime());
		try {
			PeticionFeedVO peticionFeed = new PeticionFeedVO();
			peticionFeed.setFecha(fecha);
			peticionFeed.setFormatoFeed(formatoFeed);
			peticionFeed.setIdFeed(idFeed);
			peticionFeed.setIdioma(idioma);
			getSrvAuditoriaService().registrarPeticionFeed(peticionFeed);
		} catch (Exception e) {
			logger.error("Error durante el registro de la intercepcion de devuelveRssBusqueda -> id="+idFeed+"; idioma="+idioma+"fecha="+fecha.getTime(),e);
		}
	}
	
	/**
	 * Registro de una operación realizada por el usuario
	 * 
	 * @param idODE Identificador del ODE
	 * @param idioma Idioma
	 * @param moduloDestino Operación
	 * @return registro
	 */
	private static boolean registrarOperacion(String idODE, String idioma, String moduloDestino) throws Exception {
		
		boolean registro = false;
		String usuario = null;
		
		/* Si no viene el idioma no registramos la operación */
		if (idioma == null || idioma.equals("")) 
			return registro;

		/* Se valida que el identificador del ODE sea correcto */
		if (!comprobarODE(idODE))
			return registro;
		
		/* Se recupera el usuario */
		if (LdapUserDetailsUtils.estaAutenticado()) 
			usuario = LdapUserDetailsUtils.getUsuario(); 
			
		if(logger.isDebugEnabled())logger.debug("Operación realizada: IdODE: " + idODE + " operación: " + moduloDestino 
				+ " usuario: " + usuario + " idioma: " + idioma);

		OperacionVO parametros = new OperacionVO();		
		parametros.setUsuario(usuario);		
		parametros.setIdOde(idODE);	
		parametros.setFecha(Calendar.getInstance());
		parametros.setIdioma(idioma);
		parametros.setOperacion(moduloDestino);
		
		SrvAuditoriaServicio servicio = null;
		
		try {			
			if(logger.isDebugEnabled())logger.debug("idODE: " + parametros.getIdOde());
			if(logger.isDebugEnabled())logger.debug("operación destino: " + parametros.getOperacion());			
			if(logger.isDebugEnabled())logger.debug("Fecha: " + parametros.getFecha());
			if(logger.isDebugEnabled())logger.debug("usuario: " + parametros.getUsuario());
			if(logger.isDebugEnabled())logger.debug("idioma: " + parametros.getIdioma());
			servicio = getSrvAuditoriaService();
			servicio.registrarOperacion(parametros);
			registro = true;
		}
		catch (Exception e) {
			logger.error("Se ha producido un error al invocar al web service auditoria. Revise el fichero de propiedades importedServices.properties ");
			logger.error("Error: " + e);				
			throw e;
		}
		
		return registro;
	}
	
	
	/**
	 * Registro de una busqueda realizada por el usuario
	 * 
	 * @param parametros Parámetros de la operación realizada
	 * @return registro
	 */
	private static boolean registrarBusqueda(BusquedaVO parametros) {
		
		SrvAuditoriaServicio servicio = null;
		boolean registro = false;
		
		try {			
			if(logger.isDebugEnabled())logger.debug("Término buscado: " + parametros.getTerminoBuscado());
			if(logger.isDebugEnabled())logger.debug("Tipo búsqueda: " + parametros.getTipo_busqueda());
			if(logger.isDebugEnabled())logger.debug("Ambito: " + parametros.getAmbito_busqueda());
			if(logger.isDebugEnabled())logger.debug("Tipo: " + parametros.getTipo());
			if(logger.isDebugEnabled())logger.debug("Usuario: " + parametros.getUsuario());
			if(logger.isDebugEnabled())logger.debug("Fecha: " + parametros.getFecha());
			
			servicio = getSrvAuditoriaService();
			servicio.registrarBusqueda(parametros);
			registro = true;
		}
		catch (Exception e) {
			logger.error("Se ha producido un error al invocar al web service auditoria. Revise el fichero de propiedades importedServices.properties. " + e);							
		}
		
		return registro;
	}
	
	
	/**
	 * Método para comprobar si se debe registrar el ODE. 
	 * Si el idODE no es válido no se guarda
	 * @param idODE
	 * @return valido:  true: Si hay que registrarlo. false: Si no hay que registrarlo
	 * 		   			 	 
	 */
	public static boolean comprobarODE(String idODE) {
		
		ValidadorStrategy validadorIdODE = new ValidadorStrategy(new ValidadorMEC());
		boolean valido = validadorIdODE.validar(idODE);
		
		if (!valido)
			if(logger.isDebugEnabled())logger.debug("Identificador que no valida y no se registra: " + idODE);
		
		return valido;
	}

	private static  final es.pode.soporte.auditoria.servicios.SrvAuditoriaServicio getSrvAuditoriaService()
    throws java.lang.Exception
	{
	    String srvAuditoriaServiceFile="importedServices.properties";	    
	      java.io.InputStream srvAuditoriaServiceInputStream=Registrar.class.getClassLoader().getResourceAsStream(srvAuditoriaServiceFile);
	      logger.debug("Fichero imported services localizado en : " + Registrar.class.getClassLoader().getResource(srvAuditoriaServiceFile));
	      java.util.Properties srvAuditoriaServiceProperties = new java.util.Properties();
	      srvAuditoriaServiceProperties.load(srvAuditoriaServiceInputStream);
	      String srvAuditoriaServiceEndPointAddress="";
	      srvAuditoriaServiceEndPointAddress=(String) srvAuditoriaServiceProperties.get("srvAuditoriaServicioPort");
		  logger.debug("srvAuditoriaServicioEndPointAddress del fichero --> " + srvAuditoriaServiceEndPointAddress);
		  es.pode.soporte.auditoria.servicios.SrvAuditoriaServicioServiceLocator srvAuditoriaService = new SrvAuditoriaServicioServiceLocator();                                                                                                                                                                                                                                                    
	    if (srvAuditoriaServiceEndPointAddress.length()>0) 
				  srvAuditoriaService.setSrvAuditoriaServicioEndpointAddress(srvAuditoriaServiceEndPointAddress);
		    SrvAuditoriaServicio port = srvAuditoriaService.getSrvAuditoriaServicio();	    
	      return port;
	}
	
	
	/**
	 * En función del tipo de búsqueda el valor que hay que interceptar es distinto
	 * Busqueda normal son las palabrasClave
	 * Búsqueda arbol curricular es el areaCurricular
	 * Búsqueda por tesauro es idTesauro
	 * @param valores Son los datos interceptados
	 * @return valorBuscado El tipo de búsqueda realizado
	 * 		    	 
	 */
	private static String getValorBuscado(HashMap valores) {
		
		String palabrasClave = (String)valores.get(RegistroCtes.PARAMETRO_PALABRAS_CLAVE);
		String areaCurricular = (String)valores.get(RegistroCtes.PARAMETRO_AREACURRICULAR);
		String tesauro = (String)valores.get(RegistroCtes.PARAMETRO_TESAURO);
		String valorBuscado = null;
		
		if (palabrasClave != null && !palabrasClave.equals("")) 
			valorBuscado = palabrasClave;
		else if (areaCurricular != null && !areaCurricular.equals(""))
			valorBuscado = areaCurricular;
		else if (tesauro != null && !tesauro.equals(""))
			valorBuscado = tesauro;
		else
			valorBuscado = VACIO;
		
		if(logger.isDebugEnabled())logger.debug("Valor buscado: " + valorBuscado);
		
		return valorBuscado;
	}
	
	/**
	 * A partir de los datos enviados se recupera la 
	 * @param valores Son los datos interceptados
	 * @return tipoBusqueda El tipo de búsqueda realizado
	 * 		    	 
	 */
	private static String getAmbito(HashMap valores) {
		
		String numPagina = (String)valores.get(RegistroCtes.PARAMETRO_ORIGEN_PAGINA);
		String comunidadesSeleccionadas = (String)valores.get(RegistroCtes.PARAMETRO_COMUNIDADES);	
		String ambito = null;
		
		if (numPagina == null || RegistroCtes.BUSCAR_PAGINA1.equals(numPagina))
			if(logger.isDebugEnabled())logger.debug("Pagina inicial. Se registra");
		else {
			/* No registramos las paginaciones */
			if(logger.isDebugEnabled())logger.debug("Pagina no inicial paginación: " + numPagina + " no se registra");
			return VACIO;
		}
				
		if (comunidadesSeleccionadas != null)
			ambito = RegistroCtes.BUSQUEDA_FEDERADA;
		else
			ambito = RegistroCtes.BUSQUEDA_LOCAL;
		
		if(logger.isDebugEnabled())logger.debug("Ambito búsqueda: " + ambito);
		
		return ambito;
	}
	
	/**
	 * A partir de los datos enviados se recupera el tipo de búsqueda avanzada, simple, árbol curricular y tesauro
	 * @param valores Son los datos interceptados
	 * @return tipo El tipo de búsqueda realizado
	 * 		    	 
	 */
	private static String getTipoBusqueda(HashMap valores) {
		
		String tipoBusqueda = (String)valores.get(RegistroCtes.PARAMETRO_TIPO_BUSQUEDA);
		String tipo =  null;
			
		if (tipoBusqueda == null)
			tipo = RegistroCtes.ENVIO_BUSQUEDA_NORMAL; // Por defecto se considera nulo
		else if (tipoBusqueda.equals(RegistroCtes.VALORES_BUSCAR_SIMPLE))
			tipo = RegistroCtes.ENVIO_BUSQUEDA_NORMAL;
		else if (tipoBusqueda.equals(RegistroCtes.VALORES_BUSCAR_AVANZADO))
			tipo = RegistroCtes.ENVIO_BUSQUEDA_AVANZADA;
		else if (tipoBusqueda.equals(RegistroCtes.VALORES_BUSCAR_ARBOLCURRICULAR))
			tipo = RegistroCtes.ENVIO_BUSQUEDA_AREACURRICULAR;
		else if (tipoBusqueda.equals(RegistroCtes.VALORES_BUSCAR_TESAURO))
			tipo = RegistroCtes.ENVIO_BUSQUEDA_TESAURO;
		else
			tipo = RegistroCtes.ENVIO_BUSQUEDA_NORMAL; // Por defecto se considera nulo
		
		if(logger.isDebugEnabled())logger.debug("Tipo de búsqueda a realizar: " + tipo);
		
		return tipo;
	}
	
	private static void hashMap2Traza (HashMap tabla) {	
		for (Iterator it = tabla.keySet().iterator(); it.hasNext();) { 
            String s = (String)it.next();
            String s1 = (String)tabla.get(s);
            logger.debug("Valores Hash: " + s + " " + s1);
		}
	}
	
	/**
	 * A partir de los datos enviados se recupera el tipo de búsqueda avanzada, simple, árbol curricular y tesauro
	 * @param tabla Son los datos interceptados en formato HashMap
	 * @return valores Los valores interceptados en formato String
	 * 		    	 
	 */
	private static String getValoresInterceptados (HashMap tabla) {
		
		StringBuffer valores = new StringBuffer();
		
		try {	
			for (Iterator it = tabla.keySet().iterator(); it.hasNext();) {
	            String s = (String)it.next();
	            String s1 = (String)tabla.get(s);
				valores.append(s + ": " + s1 + "  "); 			
			}
//			for (Iterator iterator = tabla.entrySet().iterator(); iterator.hasNext();) {
//				String s = (String) iterator.next();
//				valores.append(s);
//			}
		}
		catch (Exception e) {
			logger.error("Error captura valores interceptados: " + e);
			valores.append(VACIO); 
		}
			
		return valores.toString();
	}

}
