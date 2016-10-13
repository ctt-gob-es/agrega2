package es.agrega.soporte.agregaProperties;

public interface AgregaProperties 
{
	public static final String PROPERTIES_FILE_NAME = "agrega"; 
	
	
	//Datos de acceso al nodo
	/**
	 * Nombre de la propiedad que contiene el nombre del host que alberga Agrega
	 */
	public static final String HOST = "host";
	/**
	 * Nombre de la propiedad que contiene el puerto de acceso a Agrega.
	 */
	public static final String PUERTO = "puerto";
	/**
	 * Nombre de la propiedad que contiene el nombre del subdominio bajo el que se encuentra Agrega
	 */
	public static final String SUBDOMINIO = "subdominio";
	
	/**
	 * Nombre de la propiedad que contiene el nombre del host que alberga el ESB Agrega
	 */
	public static final String HOST_ESB = "host.esb";

	/**
	 * Nombre de la propiedad que contiene el nombre el puerto bajo el que se encuentra el servicio de búsqueda del ESB Agrega
	 */
	public static final String PORT_ESB = "puerto.esb";
	/**
	 * Nombre de la propiedad que contiene el nombre del host que alberga el Jboss Agrega
	 */
	public static final String HOST_JBOSS = "host.jboss";
	/**
	 * Nombre de la propiedad que contiene el puerto de acceso al Jboss Agrega.
	 */
	public static final String PUERTO_JBOSS = "puerto.jboss";
	/**
	 * Nombre de la propiedad que contiene el nombre del subdominio bajo el que se encuentra el Jboss Agrega
	 */
	public static final String SUBDOMINIO_JBOSS = "subdominio.jboss";
	
	//public static final String PUERTO_BUSC_FED_ESB = "";
	
	
	// Propiedades del antiguo dependentServer.properties
	public static final String SERVER_ON="server.on";
	
	public static final String SERVER_ID="server.id";
	
	
	/**
	 * Nombre de la propiedad que contiene el listado de prefijos de CCAA y MEC
	 */
	public static final String PROPERTY_NODOS = "nodos";
	/**
	 * Nombre de la propiedad que contiene un listado de nombres de CCAA
	 */
	public static final String PROPERTY_CCAAS = "ccaas";
	/**
	 * Nombre de la propiedad que contiene la URL para enlazar el logotipo de la Comunidad autÃ³noma
	 */
	public static final String PROPERTY_URLCONSEJERIANODO = "urlConsejeriaNodo";
	/**
	 * Nombre de la propiedad que contiene un nombre de CCAA
	 */
	public static final String PROPERTY_CCAA = "ccaa";
	
	//Datos de proxy
	/**
	 * Propiedad para activar / desactivar el uso de un proxy de salida
	 */
	public static final String USAPROXY						= "proxy";
	/**
	 * Host del proxy de salida
	 */
	public static final String HOSTPROXY					= "proxy.host";
	/**
	 * Puerto del proxy de salida
	 */
	public static final String PORTPROXY					= "proxy.port";
	/**
	 * Usuario del proxy de salida
	 */
	public static final String USUARIOPROXY					= "proxy.user";
	/**
	 * Password del proxy de salida
	 */
	public static final String CLAVEPROXY					= "proxy.passwd";
	
	// Datos del servidor de correo saliente
	
	/**
	 * Remitente de los correos enviados por la plataforma
	 */
	public static final String FROMSENDER					       = "smtp.sender";
	/**
	 * Host del servidor de correo saliente
	 */
	public static final String SMTP_HOST					       = "smtp.host";
	/**
	 * Indica si el servidor SMTP usa autenticacion o no
	 */
	public static final String SMTP_AUTENTICATION			       = "smtp.autentication";
	/**
	 * Usuario de correo
	 */
	public static final String SMTP_USER					       = "smtp.user";
	/**
	 * ContraseÃ±a de correo
	 */
	public static final String SMTP_PASSWD					       = "smtp.passwd";
	/**
	 * Opcion de depurado de envio de correos
	 */
	public static final String SMTP_DEBUG					       = "debug";
	
	// Opciones de uso de LDAPs externos
	/**
	 * Uso o no de un LDAP externo sin permisos de escritura
	 */
	public static final String LDAPEXTERNO					= "ldap.external";
	
	/**
	 * E-mail del administrador del LDAP externo
	 */
	public static final String ADMINLDAPEXTERNO				= "ldap.external.admin";
	
	// Galeria de imagenes
	/**
	 * Host del servidor de generacion de imagenes
	 */
	public static final String PROPERTY_IMAGE_SERVER_IP 	= "galeriaImg.host";
	/**
	 * Puerto del servidor de generacion de imagenes
	 */
	public static final String PROPERTY_IMAGE_SERVER_PORT 	= "galeriaImg.port";
	/**
	 * Posibilidad de publicar autónomamente a los usuarios de la plataforma
	 */
	public static final String PUBLICADOS_AUTONOMOS_HABILITADO="publicadosAutonomosHabilitado";

	public static final String PROPERTY_IMAGE_SERVCIE_URL 	= "galeriaimg.service.url";
	public static final String PROPERTY_IMAGE_APACHE_PATH 	= "galeriaimg.path.image";
	public static final String PROPERTY_IMAGE_COMMON_PATH 	= "galeriaimg.common.image";
	public static final String PROPERTY_IMAGE_EXTENSION 	= "galeriaimg.image.ext";
	public static final String PROPERTY_IMAGE_COMMON_EXTENSION 	= "galeriaimg.image.common.ext";
	public static final String PROPERTY_IMAGE_COMMON_EXTENSION_FOTOGRAFIABLE 	= "galeriaimg.image.common.ext.fot";

	public static final String PROPERTY_IMAGE_COMMON_LOCAL_PATH="galeriaimg.image.common.path";
	public static final String PROPERTY_IMAGE_DEFECTO		=	"pathImagenDefectoGrande";
	public static final String PROPERTY_IMAGE_RESIZE_EXT    = "img.resize.extension";
	
	/**
	 * Zona horaria del nodo
	 */
	public static final String PROPERTY_ZONA_HORARIA		= "timeZone";
		
	public static final String CATALOGO_AGREGA				= "catalogo.agrega";
	public static final String CATALOGO_MEC					= "catalogo.mec";
	public static final String URL_IMAGEN_DEFECTO			= "urlImagenDefecto";
	public static final String URL_IMAGEN_DEFECTO_GRANDE	= "urlImagenDefectoGrande";
	public static final String URL_IMAGEN_DEFECTO_100x100	= "urlImagenDefecto100x100";
	public static final String URL_IMAGEN_DINAMICA_DISCO	= "urlImagenDinamicaDisco";
	public static final String PATH_IMAGEN_DINAMICA_DISCO	= "pathImagenDinamicaDisco";
	public static final String PATH_IMAGEN_PORTADA			= "pathImagenPortada";
	
	public static final String URL_REPOSITORIO_AGREGA		= "urlRepositorio";
	
	public static final String NOMBRE_REPOSITORIO			= "nombreRepositorio";
	public static final String EMAIL_ADMIN_REPOSITORIO		= "emailAdmin";
	
	public static final String TIMEOUT_EXTENDIDO			= "timeout.extendido";
	public static final String ESQUEMA_LOMES				= "esquemaDeMetadatos";
	public static final String ROL_ADMINISTRADOR			= "rol_administrador";
	public static final String ROL_PUBLICADOR				= "rol_publicador";
	public static final String ROL_CATALOGADOR				= "rol_catalogador";
	public static final String ROL_EMPAQUETADOR				= "rol_empaquetador";
	public static final String CONTACTO_MAIL				= "contacto_mail";
	public static final String CONTACTO_TELEFONO			= "contacto_telefono";
	public static final String CONTACTO_INCIDENCIAS_ACTIVAR	= "contacto_incidencias_activar";
	public static final String NEUTRO						= "neutro";
	public static final String URL_LOGO_AGREGA				= "urlLogoAgrega";
	public static final String PATH_INFORMES_FEDERADOS		= "destinoInformesFederadosDir";
	public static final String PATH_INFORMES				= "destinoInformesDir";
	public static final String PATH_INFORMES_MAS			= "destinoInformesMasDir";
	public static final String REGISTRO_CAS					= "registroCas";
	
	public static final String TIMEOUTCOOKIEOPENID			= "timeoutCookieOpenId";
	public static final String VALOR_CUOTA_DEFECTO			= "valorCuotaDefecto";
	public static final String INFORMES_PORTADA				= "informesPortada";
	public static final String URL_LICENCIAS				= "url_licencias";
	public static final String TIEMPO_REFRESCO				= "tiempoRefresco";
	public static final String NUMERO_TAGS					= "numeroTags";
	public static final String PATH_GALERIA_IMG				= "path.generatedimgs";
	
	
	public static final String PATH_INFORMES_CARGA			= "destinoInformesCarga";
	public static final String PATH_LOGS			= "path_logs";
	public static final String INTEGRACION_LDAP				= "integracionLdap";
	
	public static final String PATH_INDICE ="pathIndice";
	public static final String SERVER_PATH ="server.path";
	
	public static final String NODO_TALLER = "nodo.taller";
	public static final String NODO = "nodo";
	public static final String URL_LOGOUT_NODO_TALLER = "url.logout.taller";
	
	public static final String MAX_WAIT_POOL="maxWaitPool";
	public static final String MAX_WAIT_THREAD="maxWaitThread";
	public static final String MAX_WAIT_POOL_SQI="maxWaitPoolSQI";
	public static final String MAX_WAIT_THREAD_SQI="maxWaitThreadSQI";

	public static final String PATH_IMAGENES_NODOS_SQI = "path.imagenes.nodos.sqi";
	public static final String IMAGENES_NODOS_SQI_URL ="imagenes.nodos.sqi.url";
	public static final String IMAGENES_NODOS_SQI_EXT ="imagenes.nodos.sqi.extensiones";
	
	public static final String FEED_DEFAULT="feed_default";
	
	public static final String CORREO_BAJA_USUARIO="correoBajaUsuario";
	public static final String CORREO_BAJA_USUARIO_NODO_TALLER="correoBajaUsuarioNodoTaller";
	public static final String CORREO_BAJA_USUARIO_LDAP="correoBajaUsuarioLdap";
	public static final String CORREO_ALTA_USUARIO_NODO_TALLER="correoAltaUsuarioNodoTaller";
	public static final String CORREO_ALTA_USUARIO="correoAltaUsuario";
	public static final String CORREO_ALTA_USUARIO_LDAP="correoAltaUsuarioLdap";
	public static final String CORREO_NUEVA_CONTRASENA="correoNuevaClave";
	public static final String CORREO_NUEVA_CONTRASENA_LDAP="correoNuevaClaveLdap";
	public static final String CORREO_RECOMENDAR_AMIGO="enviarAmigo";
	public static final String CORREO_CONTENIDO_INAPROPIADO="contenidoInapropiado";
	public static final String CORREO_SOLICITUD_BAJA_USUARIO="solicitudBajaUsuario";
	
	
	public static final String PATH_IMAGENES_USUARIO_PUBLICO = "path.imagenes.usuario.publico";
	public static final String IMAGENES_USUARIO_PUBLICO_URL ="imagenes.usuario.publico.url";
	public static final String PATH_IMAGENES_GRUPO_PUBLICO = "path.imagenes.grupo.publico";
	public static final String IMAGENES_GRUPO_PUBLICO_URL = "imagenes.grupo.publico.url";
	public static final String NOMBRE_IMAGEN_DEFECTO_USUARIO="ImagenDefectoUsuario";
	public static final String NOMBRE_IMAGEN_DEFECTO_GRUPO_1="ImagenDefectoGrupo1";
	public static final String NOMBRE_IMAGEN_DEFECTO_GRUPO_2="ImagenDefectoGrupo2";
	public static final String NOMBRE_IMAGEN_DEFECTO_GRUPO_3="ImagenDefectoGrupo3";
	public static final String NOMBRE_IMAGEN_DEFECTO_GRUPO_4="ImagenDefectoGrupo4";
	public static final String NOMBRE_IMAGEN_DEFECTO_GRUPO_5="ImagenDefectoGrupo5";
	public static final String NUMERO_ODES_EN_FLASH="numeroOdesEnFlash";
	
	public static final String VERSION="version";
	
	public static final String PATH_PLANTILLAS_CORREO="pathPlantillasCorreo";
	
	public static final String NOMBRE_EXCEL="id_mecDespublicados"; 
	public static final String PATH_DESPUBLICADO="pathDespublicacionMasiva";
	
	public static final String REST_RESULTADOS_POR_PAGINA="rest.resultados.por.pagina";
	public static final String SEGUNDOSCADUCIDADHIT="segundosCaducidadHit";

	public static final String RSS_FEDERADO_TIMEOUT="rss.federado.timeout";
	public static final String RSS_PATH="rss.path";
	public static final String RSS="rss";
	
	public static final String IMAGEN_DEFECTO_NODO_SQI="nombre.imagen.repositorio.externo.defecto";
	
	public static final String PERFIL_PUBLICO="perfilPublico";
	
	// Sacamos las claves de los estados en los que pueden estar los ODES.
	public static final String CREACION = "CREACION";
	public static final String PROPUESTO = "PROPUESTO";
	public static final String PUBLICADO = "PUBLICADO";
	public static final String RECHAZADO = "RECHAZADO";
	public static final String NO_DISPONIBLE = "NO_DISPONIBLE";
	public static final String ELIMINADO = "ELIMINADO";
	public static final String CATALOGACION = "CATALOGACION";
	public static final String PUBLICADO_AUTONOMO = "PUBLICADO_AUTONOMO";
	public static final String IDENTIDAD_FEDERADA="identidad.federada";
	public static final String TIMEOUT_IDENTIDAD_FEDERADA="timeout.identidad.federada";
	public static final String REMEMBERME="rememberme";
	
	// Servidores de referencia para actualizaciones de la versión offline
	public static final String SERVIDOR_UPDATES="servidor.updates";
	public static final String SERVIDOR_UPDATES_PORT="servidor.updates.port";
	
	// Servidores de referencia para sincronizacion ODEs
	public static final String SERVIDOR_SINCRONIZACION="servidor.sincronizacion";
	public static final String SERVIDOR_SINCRONIZACION_PORT="servidor.sincronizacion.port";
	
	public static final String SECUENCIA_SIN_LOGAR="secuencia.sin.logar";
	
	public static final String TIMEOUT_ALTA_NODO ="timeout.alta.nodo";
	
	//Medidas de seguridad adicionales para claves de usuarios
	public static final String CHECK_PASSWORD="check.password";
	
	public static final String TIMEOUT_AUTENTICADO="timeout.autenticado";
	
	public static final String INDICES_REMOTOS="pathIndiceNodos";

	//Numero de noticias mostradas en el resumen de las ultimas noticias
	public static final String NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN="numNoticiasMostradasEnResumen";
	
	//Numero de descargas mostradas en el resumen de las ultimas decargas
	public static final String NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN="numDescargasMostradasEnResumen";
	
	/*
	 * Url de comunidades de AGREGA (Busqueda Google)
	 */
	public static final String GOOGLE_NODOS_URL = "google_nodos_URL";
	
	public static final String INDEX_SERVER_URL = "indexServer.url";
	public static final String INDEX_SERVER_PORT = "indexServer.port";
	public static final String INDEX_UPLOAD_PATH="index.upload.path";
	
	/*
	 * Nombre del fichero de vista previa de un ODE
	 */
	public static final String VISTA_PREVIA_AGREGA = "vistaPreviaAgrega";
	
	/*
	 * Ficheros de imágenes por defecto
	 */
	public static final String PATH_IMAGEN_DEFECTO="pathImagenDefecto";
	public static final String PATH_IMAGEN_DEFECTO_MEDIA="pathImagenDefectoMedia";
	public static final String PATH_IMAGEN_DEFECTO_PEQUE="pathImagenDefectoPeque";
	
	/*
	* Nombre de constantes y no administrables
	*/
	public static final String LOG_NO_BORRAR ="logs_no_borrar";
	public static final String PATH_INFORMES_PORTADA = "pathInformesPortada";
	public static final String ZOHO_KEY = "zoho.key";
	
	
	public static final String PATH_ESTADISTICAS="estadisticas.path";
	
	/*
	 * Variable para prefijo en base de datos
	 */
	public static final String PREFIJO ="prefijo";
	

	/*
	 * Variable para indicar si el usuario que se crea automáticamente en base de datos se crea activo
	 */
	public static final String ALTA_USUARIO_DESACTIVADO ="altaUsuarioDesactivado";
	
	/*
	 * Nombre de propiedades para los correos de administradores de cada nodo
	 */
	public static final String CORREO_INCIDENCIA_COMUNICACION_NODO="correoIncidenciaComunicacionNodo";
	public static final String CORREO_INCIDENCIA_COMUNICACION_NODO_MECD="correoIncidenciaComunicacionNodo_MECD";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_AN="correoIncidenciaComunicacionNodo_AN";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_AR="correoIncidenciaComunicacionNodo_AR";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_AS="correoIncidenciaComunicacionNodo_AS";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_IB="correoIncidenciaComunicacionNodo_IB";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_IC="correoIncidenciaComunicacionNodo_IC";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_CB="correoIncidenciaComunicacionNodo_CB";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_CM="correoIncidenciaComunicacionNodo_CM";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_CL="correoIncidenciaComunicacionNodo_CL";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_CT="correoIncidenciaComunicacionNodo_CT";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_EU="correoIncidenciaComunicacionNodo_EU";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_EX="correoIncidenciaComunicacionNodo_EX";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_GA="correoIncidenciaComunicacionNodo_GA";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_LR="correoIncidenciaComunicacionNodo_LR";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_MA="correoIncidenciaComunicacionNodo_MA";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_MU="correoIncidenciaComunicacionNodo_MU";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_NA="correoIncidenciaComunicacionNodo_NA";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_CV="correoIncidenciaComunicacionNodo_CV";
	public static final String CORREOINCIDENCIACOMUNICACIONNODO_REDES="correoIncidenciaComunicacionNodo_REDES";
	/*
	 * Nombre de propiedades para activar o desactivar el envío de los correos de alarma por problemas de comunicación de índices y estadísticas
	 */	
	public static final String ACTIVO_ENVIO_CORREOS_INCIDENCIA_COMUNICACION="activarEnvioCorreosAlarma";
	
	
	/**
	 * Lee la propiedad de configuracion especificada por sKey
	 * @param sKey Nombre de la propiedad de configuracion
	 * @return Valor de la propiedad de configuracion o null si no se encuentra
	 */
	public String getProperty(String sKey);
	
	/**
	 * Lee las propiedades necesarias para formar la url del nodo y forma la url
	 * @return Valor de la url del nodo
	 */
	public String getUrlNodo(); 

}
