package es.pode.configuracionPlataforma.ficheros;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;



public class InstalacionVariables {

	/*VARIABLES QUE ESTABAN DE AGREGA PROPERTIES QUE SE PREGUNTAN AL USUARIO EN EL PROCESO DE INSTALACION*/
	public static String CCAA				="ccaa";
	public static String MAILCONTACTO		="contacto_mail";
	public static String TELEFONOCONTACTO	="contacto_telefono";
//	public static String IMGSERVERHOST		="galeriaImg.host";
	public static String GALERIAIMGPATH		="server.path"; 
//	public static String IMGSERVERPORT		="galeriaImg.port";
	public static String NODO				="host";
//	public static String NODO_ESB			="nodo.esb";		// ya no se usa el ESB
	public static String NODO_JBOSS			="nodo.jboss";
	public static String LDAPEXTERNAL		="ldap.external";
	public static String LDAPADMIN			="ldap.external.admin";
	public static String PREFIJO_NODO		="nodo";
	public static String RUTALOGS			="path_logs";
	public static String PROXY				="proxy";
	public static String PROXYHOST			="proxy.host";
	public static String PROXYPASSWD		="proxy.passwd";
	public static String PROXYPORT			="proxy.port";
	public static String PROXYUSER			="proxy.user";
	public static String PORT				="puerto";
	public static String PORT_JBOSS			="puerto.jboss";
	public static String IDNODO				="server.id"; 
	public static String SERVERON			="server.on";
	public static String SMTPAUTHENTICATION	="smtp.authentication";
	public static String SMTPHOST			="smtp.host";
	public static String SMTPPASSWD			="smtp.passwd";
	public static String SMTPSENDER			="smtp.sender";
	public static String SMTPUSER			="smtp.user";
	public static String SUBDOMAIN			="subdominio";
//	public static String SUBDOMAIN_ESB		="subdomain.esb";  // ya no se usa el ESB
	public static String SUBDOMAIN_JBOSS	="subdominio.jboss";
	public static String URLCONSEJERIA		="urlConsejeriaNodo";
	                  
	/*VARIABLES QUE ESTABAN EN AUTHBACKEND PROPERTIES QUE SE PREGUNTABAN EN EL INSTALL*/
	public static String LDAPURL				="ldapurl";  				// se usa en spring ldap tambien.
	public static String LDAPPATH               ="ldappath";				// se usa en spring ldap tambien.
	public static String LDAPMANAGERDN          ="ldapmanagerDN";			// se usa en spring ldap tambien.
	public static String LDAPMANAGERPASSWD      ="ldapmanagerPasswd";		// se usa en spring ldap tambien.
	public static String LDAPUSUARIOSBINDSUBPATH="ldapusuariosbindsubpath";	// se usa en spring ldap tambien.
	public static String USERCNPREFIX           ="usercnprefix";
	public static String ROLEBINDSUBPATH        ="rolebindsubpath";
	public static String CASURL                 ="casurl";
//	public static String NODO      	="host";		//ya esta definida en el bloque de agrega properties. es compartida
//	public static String SUBDOMAIN 	="subdominio";	//ya esta definida en el bloque de agrega properties. es compartida

	/*VARIABLES QUE ESTABAN EN SPRING LDAP XML QUE SE PREGUNTABAN EN EL INSTALL*/
//	public static String LDAPURL                	="ldapurl";					// ya esta definida en el bloque de authbackend properties. es compartida
//	public static String LDAPUSUARIOSBINDSUBPATH	="ldapusuariosbindsubpath";	// ya esta definida en el bloque de authbackend properties. es compartida
//	public static String LDAPPATH               	="ldappath";				// ya esta definida en el bloque de authbackend properties. es compartida
//	public static String LDAPMANAGERDN          	="ldapmanagerDN";			// ya esta definida en el bloque de authbackend properties. es compartida
//	public static String LDAPMANAGERPASSWD      	="ldapmanagerPasswd";		// ya esta definida en el bloque de authbackend properties. es compartida
	
	/*VARIABLES QUE ESTABAN EN SEARCH PLUGIN XML QUE SE PREGUNTABAN EN EL INSTALL*/
//	public static String SERVERON  	="server.on";	//ya esta definida en el bloque de agrega properties. es compartida
//	public static String NODO      	="host";		//ya esta definida en el bloque de agrega properties. es compartida
//	public static String SUBDOMAIN 	="subdominio";	//ya esta definida en el bloque de agrega properties. es compartida

	/*VARIABLES QUE ESTABAN EN *RPTDESIGN QUE SE PREGUNTABAN EN EL INSTALL*/
//	public static String SUBDOMAIN 	="subdominio";	//ya esta definida en el bloque de agrega properties. es compartida
	
	/*VARIABLES QUE ESTABAN EN *OPML QUE SE PREGUNTABAN EN EL INSTALL*/
//	public static String NODO      	="host";		//ya esta definida en el bloque de agrega properties. es compartida
//	public static String SUBDOMAIN 	="subdominio";	//ya esta definida en el bloque de agrega properties. es compartida

	/*VARIABLES QUE ESTABAN EN *SITEMAPS/ESTATICO QUE SE PREGUNTABAN EN EL INSTALL*/
//	public static String NODO      	="host";		//ya esta definida en el bloque de agrega properties. es compartida
//	public static String SUBDOMAIN 	="subdominio";	//ya esta definida en el bloque de agrega properties. es compartida
	
	/*VARIABLES PARA SABER LOS DIRECTORIOS DONDE SE ENCUENTRAN LOS FICHEROS A MODIFICAR EN LA INSTALACION*/
	public static String JBOSS_HOME		="JBOSS_HOME";	//NECESITA ESTAR DUPLICADA
	public static String UPLOADS		="UPLOADS";		//NECESITA ESTAR DUPLICADA
	
	
	/*VARIABLES COMUNES A LOS DOS JBOSS QUE NO SE SOLICITAN AL USUARIO EN EL INSTALADOR PERO 
	QUE SON NECESARIAS PARA ESCRIBIR EL FICHERO AGREGA.PROPERTIES. EN EL MOMENTO DE LA INSTALACION
	TOMAN UN VALOR POR DEFECTO*/
	public static String DEBUG_ENVIO_CORREOS			="debug";
	public static String TIMEZONE						="timeZone";
	public static String AUDITORIA						="auditoria";
	public static String CONTACTO_INCIDENCIAS_ACTIVAR	="contacto_incidencias_activar";
	public static String BIRTDIR						="birtDir";
	public static String INFORMESDIR					="informesDir";
	public static String DESTINOINFORMESFEDERADOSDIR	="destinoInformesFederadosDir";
	public static String DESTINOINFORMESDIR				="destinoInformesDir";
	public static String IMGBIRTDIR						="imgBirtDir";
	public static String STATICIMGDIR					="staticImgDir";
	public static String PATHINFORMESPORTADA			="pathInformesPortada";
	public static String DESTINOINFORMESMASDIR			="destinoInformesMasDir";
	public static String RANGOINFORMESPORTADA			="rangoInformesPortada";
	public static String DIASANTERIORESINFORMESPORTADA	="diasAnterioresInformesPortada";
	public static String NOMBREINFORMESPORTADASEMANALES	="nombreInformesPortadaSemanales";
	public static String DIAS							="dias";
	public static String GALERIAIMG_PATH_IMAGE			="galeriaimg.path.image";
	public static String GALERIAIMG_COMMON_IMAGE		="galeriaimg.common.image";
	public static String GALERIAIMG_IMAGE_EXT			="galeriaimg.image.ext";
	public static String GALERIAIMG_IMAGE_COMMON_PATH	="galeriaimg.image.common.path";
	public static String PATH_GENERATEDIMGS				="path.generatedimgs";
	public static String CATALOGO_AGREGA				="catalogo.agrega";
	public static String CATALOGO_MEC					="catalogo.mec";
	public static String RSS							="rss";
	public static String RSS_PATH						="rss.path";
	public static String RSS_FEDERADO_TIMEOUT			="rss.federado.timeout";
	public static String FEED_DEFAULT					="feed_default";
	public static String SEARCHPLUGIN					="searchPlugin";
	public static String NEUTRO							="neutro";
	public static String TIMEOUT_EXTENDIDO				="timeout.extendido";
	public static String ESQUEMADEMETADATOS				="esquemaDeMetadatos";
	public static String URLREPOSITORIO					="urlRepositorio";
	public static String NOMBREREPOSITORIO				="nombreRepositorio";
	public static String EMAILADMIN						="emailAdmin";
	public static String TIEMPOLIMITE					="tiempolimite";
	public static String EXPORT_CACHE_TIEMPO			="export.cache.tiempo";
	public static String EXPORT_CACHE_SIZE				="export.cache.size";
	public static String TIMEOUTCOOKIEOPENID			="timeoutCookieOpenId";
	public static String VALORCUOTADEFECTO				="valorCuotaDefecto";
	public static String INFORMESPORTADA				="informesPortada";
	public static String TIEMPOREFRESCO					="tiempoRefresco";
	public static String NUMEROTAGS						="numeroTags";
	public static String URL_LICENCIAS					="url_licencias";
	public static String NODO_TALLER					="nodo.taller";
	public static String URL_LOGOUT_TALLER				="url.logout.taller";
	public static String VERSION						="version";
	public static String DESTINOINFORMESCARGA			="destinoInformesCarga";
	public static String INTEGRACIONLDAP				="integracionLdap";
	public static String PATHINDICE						="pathIndice";
	public static String PATHINDICENODOS				="pathIndiceNodos";
	public static String MAXWAITPOOLSQI					="maxWaitPoolSQI";
	public static String MAXWAITTHREADSQI				="maxWaitThreadSQI";
	public static String SEGUNDOSCADUCIDADHIT			="segundosCaducidadHit";
	public static String PATH_IMAGENES_NODOS_SQI		="path.imagenes.nodos.sqi";
	public static String IMAGENES_NODOS_SQI_URL			="imagenes.nodos.sqi.url";
	public static String IMAGENES_NODOS_SQI_EXTENSIONES	="imagenes.nodos.sqi.extensiones";
	public static String NOMBRE_IMAGEN_REPOSITORIO_EXTERNO_DEFECTO="nombre.imagen.repositorio.externo.defecto";
	public static String PUBLICADOSAUTONOMOSHABILITADO	="publicadosAutonomosHabilitado";
	public static String PATHPLANTILLASCORREO			="pathPlantillasCorreo";
	public static String REST_RESULTADOS_POR_PAGINA		="rest.resultados.por.pagina";
	public static String PATH_IMAGENES_USUARIO_PUBLICO	="path.imagenes.usuario.publico";
	public static String IMAGENES_USUARIO_PUBLICO_URL	="imagenes.usuario.publico.url";
	public static String PATH_IMAGENES_GRUPO_PUBLICO	="path.imagenes.grupo.publico";
	public static String IMAGENES_GRUPO_PUBLICO_URL		="imagenes.grupo.publico.url";
	public static String IMAGENDEFECTOUSUARIO			="ImagenDefectoUsuario";
	public static String IMAGENDEFECTOGRUPO1			="ImagenDefectoGrupo1";
	public static String IMAGENDEFECTOGRUPO2			="ImagenDefectoGrupo2";
	public static String IMAGENDEFECTOGRUPO3			="ImagenDefectoGrupo3";
	public static String IMAGENDEFECTOGRUPO4			="ImagenDefectoGrupo4";
	public static String IMAGENDEFECTOGRUPO5			="ImagenDefectoGrupo5";
	public static String PATHDEPUBLICACIONMASIVA		="pathDespublicacionMasiva";
	public static String PERFILPUBLICO					="perfilPublico";
	public static String REMEMBERME						="rememberme";
	public static String IDENTIDAD_FEDERADA				="identidad.federada";
	public static String TIMEPUT_IDENTIDAD_FEDERADA		="timeout.identidad.federada";
	public static String NUMEROODESENFLASH				="numeroOdesEnFlash";
	public static String SECUENCIA_SIN_LOGAR			="secuencia.sin.logar";
	public static String CHECK_PASSWORD					="check.password";
	public static String TIMEOUT_AUTENTICADO			="timeout.autenticado";
	public static String GOOGLE_NODO_MEC				="google_nodo_MEC";
	public static String GOOGLE_NODO_AN					="google_nodo_AN";
	public static String GOOGLE_NODO_AR					="google_nodo_AR";
	public static String GOOGLE_NODO_AS					="google_nodo_AS";
	public static String GOOGLE_NODO_IB					="google_nodo_IB";
	public static String GOOGLE_NODO_IC					="google_nodo_IC";
	public static String GOOGLE_NODO_CB					="google_nodo_CB";
	public static String GOOGLE_NODO_CM					="google_nodo_CM";
	public static String GOOGLE_NODO_CL					="google_nodo_CL";
	public static String GOOGLE_NODO_CT					="google_nodo_CT";
	public static String GOOGLE_NODO_EU					="google_nodo_EU";
	public static String GOOGLE_NODO_EX					="google_nodo_EX";
	public static String GOOGLE_NODO_GA					="google_nodo_GA";
	public static String GOOGLE_NODO_LR					="google_nodo_LR";
	public static String GOOGLE_NODO_MA					="google_nodo_MA";
	public static String GOOGLE_NODO_MU					="google_nodo_MU";
	public static String GOOGLE_NODO_NA					="google_nodo_NA";
	public static String GOOGLE_NODO_CV					="google_nodo_CV";
	public static String NUMNOTICIASMOSTRADASENRESUMEN	="numNoticiasMostradasEnResumen";
	public static String NUMDESCARGASMOSTRADASENRESUMEN	="numDescargasMostradasEnResumen";
	public static String INDEXSERVER_URL				="indexServer.url";
	public static String INDEXSERVER_PORT				="indexServer.port";
	public static String INDEXSERVER_USER				="indexServer.user";
	public static String INDEXSERVER_PASSWORD			="indexServer.password";
	public static String INDEX_UPLOAD_PATH				="index.upload.path";
//	public static String HOST_ALTERNATIVO				="host.alternativo";
	public static String VISTAPREVIAAGREGA				="vistaPreviaAgrega";
	public static String PATHIMAGENDEFECTO				="pathImagenDefecto";
	public static String PATHIMAGENDEFECTOMEDIA			="pathImagenDefectoMedia";
	public static String PATHIMAGENDEFECTOPEQUE			="pathImagenDefectoPeque";
	public static String ESTADISTICAS_PATH				="estadisticas.path";	
	
	
	/*TIPOS DE JBOSS*/
	public static String JBOSS_TYPE_DEF		="default_";
	public static String JBOSS_TYPE_ALT		="alternativo_";
	
	/*TIPOS DE JBOSS EN MODO CLUSTER*/
	public static String JBOSS_TYPE_NODE1	="node1_";
	public static String JBOSS_TYPE_NODE2	="node2_";
	public static String JBOSS_TYPE_NODE3	="node3_";
	public static String JBOSS_TYPE_NODE4	="node4_";
	public static String JBOSS_TYPE_NODE5	="node5_";
	public static String JBOSS_TYPE_NODE6	="node6_";
	
	
	/*PARAMETROS DEL JBOSS DEFAULT*/
	public static String DEF_NODO			=JBOSS_TYPE_DEF+NODO;
	public static String DEF_JBOSS_HOME		=JBOSS_TYPE_DEF+JBOSS_HOME;	
	public static String DEF_UPLOADS		=JBOSS_TYPE_DEF+UPLOADS;		
		
	/*PARAMETROS DEL JBOSS ALTERNATIVO*/
	public static String ALT_NODO			=JBOSS_TYPE_ALT+NODO;
	public static String ALT_JBOSS_HOME		=JBOSS_TYPE_ALT+JBOSS_HOME;	
	public static String ALT_UPLOADS		=JBOSS_TYPE_ALT+UPLOADS;		

	/*PARAMETROS DE LOS JBOSS EN MODO CLUSTER*/
	public static String NODE1_NODO			=JBOSS_TYPE_NODE1+NODO;
	public static String NODE1_JBOSS_HOME	=JBOSS_TYPE_NODE1+JBOSS_HOME;	
	public static String NODE1_UPLOADS		=JBOSS_TYPE_NODE1+UPLOADS;	
	public static String NODE2_NODO			=JBOSS_TYPE_NODE2+NODO;
	public static String NODE2_JBOSS_HOME	=JBOSS_TYPE_NODE2+JBOSS_HOME;	
	public static String NODE2_UPLOADS		=JBOSS_TYPE_NODE2+UPLOADS;	
	public static String NODE3_NODO			=JBOSS_TYPE_NODE3+NODO;
	public static String NODE3_JBOSS_HOME	=JBOSS_TYPE_NODE3+JBOSS_HOME;	
	public static String NODE3_UPLOADS		=JBOSS_TYPE_NODE3+UPLOADS;	
	public static String NODE4_NODO			=JBOSS_TYPE_NODE4+NODO;
	public static String NODE4_JBOSS_HOME	=JBOSS_TYPE_NODE4+JBOSS_HOME;	
	public static String NODE4_UPLOADS		=JBOSS_TYPE_NODE4+UPLOADS;	
	public static String NODE5_NODO			=JBOSS_TYPE_NODE5+NODO;
	public static String NODE5_JBOSS_HOME	=JBOSS_TYPE_NODE5+JBOSS_HOME;	
	public static String NODE5_UPLOADS		=JBOSS_TYPE_NODE5+UPLOADS;	
	public static String NODE6_NODO			=JBOSS_TYPE_NODE6+NODO;
	public static String NODE6_JBOSS_HOME	=JBOSS_TYPE_NODE6+JBOSS_HOME;	
	public static String NODE6_UPLOADS		=JBOSS_TYPE_NODE6+UPLOADS;		
	


	/*VARIABLES COMUNES A LOS DOS JBOSS QUE NO SE SOLICITAN AL USUARIO EN EL INSTALADOR PERO 
	QUE SON NECESARIAS PARA ESCRIBIR EL FICHERO AGREGA.PROPERTIES. EN EL MOMENTO DE LA INSTALACION
	TOMAN UN VALOR POR DEFECTO*/
	public static String[] variables_comunes_con_default_value = new String[]{
		DEBUG_ENVIO_CORREOS,
		TIMEZONE,
		AUDITORIA,
		CONTACTO_INCIDENCIAS_ACTIVAR,
		BIRTDIR,
		INFORMESDIR,
		DESTINOINFORMESFEDERADOSDIR,
		DESTINOINFORMESDIR,
		IMGBIRTDIR,
		STATICIMGDIR,
		PATHINFORMESPORTADA,
		DESTINOINFORMESMASDIR,
		RANGOINFORMESPORTADA,
		DIASANTERIORESINFORMESPORTADA,
		NOMBREINFORMESPORTADASEMANALES,
		DIAS,
		GALERIAIMG_PATH_IMAGE,
		GALERIAIMG_COMMON_IMAGE,
		GALERIAIMG_IMAGE_EXT,
		GALERIAIMG_IMAGE_COMMON_PATH,
		PATH_GENERATEDIMGS,
		CATALOGO_AGREGA,
		CATALOGO_MEC,
		RSS,
		RSS_PATH,
		RSS_FEDERADO_TIMEOUT,
		FEED_DEFAULT,
		SEARCHPLUGIN,
		NEUTRO,
		TIMEOUT_EXTENDIDO,
		ESQUEMADEMETADATOS,
		URLREPOSITORIO,
		NOMBREREPOSITORIO,
		EMAILADMIN,
		TIEMPOLIMITE,
		EXPORT_CACHE_TIEMPO,
		EXPORT_CACHE_SIZE,
		TIMEOUTCOOKIEOPENID,
		VALORCUOTADEFECTO,
		INFORMESPORTADA,
		TIEMPOREFRESCO,
		NUMEROTAGS,
		URL_LICENCIAS,
		NODO_TALLER,
		URL_LOGOUT_TALLER,
		VERSION,
		DESTINOINFORMESCARGA,
		INTEGRACIONLDAP,
		PATHINDICE,
		PATHINDICENODOS,
		MAXWAITPOOLSQI,
		MAXWAITTHREADSQI,
		SEGUNDOSCADUCIDADHIT,
		PATH_IMAGENES_NODOS_SQI,
		IMAGENES_NODOS_SQI_URL,
		IMAGENES_NODOS_SQI_EXTENSIONES,
		NOMBRE_IMAGEN_REPOSITORIO_EXTERNO_DEFECTO,
		PUBLICADOSAUTONOMOSHABILITADO,
		PATHPLANTILLASCORREO,
		REST_RESULTADOS_POR_PAGINA,
		PATH_IMAGENES_USUARIO_PUBLICO,
		IMAGENES_USUARIO_PUBLICO_URL,
		PATH_IMAGENES_GRUPO_PUBLICO,
		IMAGENES_GRUPO_PUBLICO_URL,
		IMAGENDEFECTOUSUARIO,
		IMAGENDEFECTOGRUPO1,
		IMAGENDEFECTOGRUPO2,
		IMAGENDEFECTOGRUPO3,
		IMAGENDEFECTOGRUPO4,
		IMAGENDEFECTOGRUPO5,
		PATHDEPUBLICACIONMASIVA,
		PERFILPUBLICO,
		REMEMBERME,
		IDENTIDAD_FEDERADA,
		TIMEPUT_IDENTIDAD_FEDERADA,
		NUMEROODESENFLASH,
		SECUENCIA_SIN_LOGAR,
		CHECK_PASSWORD,
		TIMEOUT_AUTENTICADO,
		GOOGLE_NODO_MEC,
		GOOGLE_NODO_AN,
		GOOGLE_NODO_AR,
		GOOGLE_NODO_AS,
		GOOGLE_NODO_IB,
		GOOGLE_NODO_IC,
		GOOGLE_NODO_CB,
		GOOGLE_NODO_CM,
		GOOGLE_NODO_CL,
		GOOGLE_NODO_CT,
		GOOGLE_NODO_EU,
		GOOGLE_NODO_EX,
		GOOGLE_NODO_GA,
		GOOGLE_NODO_LR,
		GOOGLE_NODO_MA,
		GOOGLE_NODO_MU,
		GOOGLE_NODO_NA,
		GOOGLE_NODO_CV,
		NUMNOTICIASMOSTRADASENRESUMEN,
		NUMDESCARGASMOSTRADASENRESUMEN,
		INDEXSERVER_URL,
		INDEXSERVER_PORT,
		INDEXSERVER_USER,
		INDEXSERVER_PASSWORD,
		INDEX_UPLOAD_PATH,
//		HOST_ALTERNATIVO,
		VISTAPREVIAAGREGA,
		PATHIMAGENDEFECTO,
		PATHIMAGENDEFECTOMEDIA,
		PATHIMAGENDEFECTOPEQUE,
		ESTADISTICAS_PATH
	};
	

	/* VARIABLES COMUNES A LOS DOS JBOSS QUE SE PREGUNTAN AL USUARIO EN EL PROCESO DE INSTALACION*/
	public static String[] variables_comunes = new String[]{
		CCAA,
		MAILCONTACTO,
		TELEFONOCONTACTO,
		LDAPEXTERNAL,
		LDAPADMIN,
		PREFIJO_NODO,
		PROXY,
		PROXYHOST,
		PROXYPASSWD,
		PROXYPORT,
		PROXYUSER,
		IDNODO, 
		SERVERON,
		SMTPAUTHENTICATION,
		SMTPHOST,
		SMTPPASSWD,
		SMTPSENDER,
		SMTPUSER,
		URLCONSEJERIA,
		LDAPURL,  				
		LDAPPATH,				
		LDAPMANAGERDN,			
		LDAPMANAGERPASSWD,	
		LDAPUSUARIOSBINDSUBPATH,
		USERCNPREFIX,
		ROLEBINDSUBPATH
	};
	

	/* VARIABLES PARTICULARES DE CADA JBOSS QUE SE PREGUNTAN AL USUARIO EN EL PROCESO DE INSTALACION*/
	public static String[] variables_particulares = new String[]{
//		IMGSERVERHOST,
		GALERIAIMGPATH, 
//		IMGSERVERPORT,
		NODO,
		NODO_JBOSS,
		RUTALOGS,
		PORT,
		PORT_JBOSS,
		SUBDOMAIN,
		SUBDOMAIN_JBOSS,
		JBOSS_HOME,
		UPLOADS,
		CASURL	
	};
	

	/* Tipos de JBoss que se pueden instalar */
	public static String[] tipos_jboss = new String[]{
		JBOSS_TYPE_DEF,
		JBOSS_TYPE_ALT,
		JBOSS_TYPE_NODE1,
		JBOSS_TYPE_NODE2,
		JBOSS_TYPE_NODE3,
		JBOSS_TYPE_NODE4,
		JBOSS_TYPE_NODE5,
		JBOSS_TYPE_NODE6
	};
	
	
	/**
	 * Metodo que devuelve true si un string es el nombre de una variable con valor
	 * por defecto, y comun varias instancias JBoss. Estas variables no se piden al usuario en el 
	 * momento de la instalacion por que tienen un valor por defecto.
	 * @param nombreVar
	 * @return
	 */
	public static boolean isVariableComun_con_default_value (String nombreVar) {
		if(nombreVar==null || nombreVar.equals("")) return false;
	    for(int j=0; j<variables_comunes_con_default_value.length; j++) 
	    	if(variables_comunes_con_default_value[j].equals(nombreVar))
				return true;
		return false;
	}
	
	
	/**
	 * Metodo que devuelve true si un string es el nombre de una variable comun a varias instancias JBoss
	 * @param nombreVar
	 * @return
	 */
	public static boolean isVariableComun (String nombreVar) {
		if(nombreVar==null || nombreVar.equals("")) return false;
	    for(int j=0; j<variables_comunes.length; j++) 
	    	if(variables_comunes[j].equals(nombreVar))
				return true;
		return false;
	}


	/**
	 * Metodo que devuelve true si un string es el nombre de una variable particular a cada JBoss
	 * @param nombreVar
	 * @return
	 */
	public static boolean isVariableParticular (String nombreVar) {
		if(nombreVar==null || nombreVar.equals("")) return false;
	    for(int j=0; j<variables_particulares.length; j++) 
	    	if(variables_particulares[j].equals(nombreVar))
				return true;
		return false;
	}
	

	/**
	 * Metodo que determina si una variable es una de las declaradas.
	 * @param var
	 * @return
	 */
	public static boolean isVariable (String nombreVar) {
		if(nombreVar==null || nombreVar.equals("")) return false;
	    if(isVariableComun_con_default_value(nombreVar)) return true;
	    if(isVariableComun(nombreVar)) return true;
	    if(isVariableParticular(nombreVar)) return true;
		return false;
	}
	
	
	/**
	 * Metodo que determina si un string representa a un tipo de jboss
	 * @param tipoJboss
	 * @return
	 */
	public static boolean esUnTipoJboss(String tipoJboss) {
		if(tipoJboss==null || tipoJboss.isEmpty()) return false;
    	for(int i=0; i<tipos_jboss.length; i++) 
    		if (tipoJboss.contentEquals(tipos_jboss[i]))
    			return true;
    	return false;
	}
	        

    /**
     * Metodo eficiente que devuelve la lista de propiedades ordenada alfabeticamente por nombre
     * @param props
     * 			array de propiedades
     * @return propiedades ordenadas alfabeticamente por nombre
     */
    public static PropiedadVO[] ordenaAlfabeticamentePropiedades(PropiedadVO[] props) {
    	if (props==null || props.length<2) return props;
    	
    	String tmp;
    	
    	for(int i=0; i<props.length; i++) {
        	for(int j=0; j+1<props.length; j++) {
        		if(props[j].getNombre().compareTo(props[j+1].getNombre())>0) {
        			//swap nombre
        			tmp = props[j].getNombre();
        			props[j].setNombre(props[j+1].getNombre());
        			props[j+1].setNombre(tmp);

        			//swap valor
        			tmp = props[j].getValor();
        			props[j].setValor(props[j+1].getValor());
        			props[j+1].setValor(tmp);

        			//swap descripcion
        			tmp = props[j].getDescripcion();
        			props[j].setDescripcion(props[j+1].getDescripcion());
        			props[j+1].setDescripcion(tmp);

        			//swap ejemplo
        			tmp = props[j].getEjemplo();
        			props[j].setEjemplo(props[j+1].getEjemplo());
        			props[j+1].setEjemplo(tmp);
        		}
        	}
    	}
    	return props;
    }
    
}
