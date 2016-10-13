/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx;


/**
 * @author dgonzalezd
 *
 */
public enum Propiedades {
	//nombre, jmx, fileList
	auditoria("auditoria", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	birtDir("birtDir", true, Ficheros.AGREGA_PROPERTIES.getFilename()),	
	destinoInformesCarga("destinoInformesCarga",true,Ficheros.AGREGA_PROPERTIES.getFilename()),
	destinoInformesDir("destinoInformesDir",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	destinoInformesFederadosDir("destinoInformesFederadosDir", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	destinoInformesMasDir("destinoInformesMasDir", true,Ficheros.AGREGA_PROPERTIES.getFilename()),
	dias("dias", true,Ficheros.AGREGA_PROPERTIES.getFilename()),
	diasAnterioresInformesPortada("diasAnterioresInformesPortada", true,Ficheros.AGREGA_PROPERTIES.getFilename()),
	esquemaDeMetadatos("esquemaDeMetadatos", true,Ficheros.AGREGA_PROPERTIES.getFilename()),
		//No administrable por JMX, pero sí por acceso directo a BBDD. Debería ser una constante
	estadisticas_path("estadisticas.path", true,Ficheros.AGREGA_PROPERTIES.getFilename()),
	feed_default("feed_default", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	galeriaimg_common_image("galeriaimg.common.image", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	galeriaimg_image_common_path("galeriaimg.image.common.path", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	galeriaimg_image_ext("galeriaimg.image.ext", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	galeriaimg_path_image("galeriaimg.path.image", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_AN("google_nodo_AN", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_AR("google_nodo_AR", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_AS("google_nodo_AS", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_CB("google_nodo_CB", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_CL("google_nodo_CL", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_CM("google_nodo_CM", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_CT("google_nodo_CT", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_CV("google_nodo_CV", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
//	google_nodo_GL("google_nodo_GL", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_EU("google_nodo_EU", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_EX("google_nodo_EX", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_GA("google_nodo_GA", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_IB("google_nodo_IB", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_IC("google_nodo_IC", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_LR("google_nodo_LR", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_MA("google_nodo_MA", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_MEC("google_nodo_MEC", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_MU("google_nodo_MU", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	google_nodo_NA("google_nodo_NA", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	host_alternativo("host.alternativo", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	identidad_federada("identidad.federada", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoGrupo1("ImagenDefectoGrupo1", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoGrupo2("ImagenDefectoGrupo2", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoGrupo3("ImagenDefectoGrupo3", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoGrupo4("ImagenDefectoGrupo4", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoGrupo5("ImagenDefectoGrupo5", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ImagenDefectoUsuario("ImagenDefectoUsuario", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	imagenes_grupo_publico_url("imagenes.grupo.publico.url", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	imagenes_nodos_sqi_extensiones("imagenes.nodos.sqi.extensiones", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	imagenes_usuario_publico_url("imagenes.usuario.publico.url", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	imgBirtDir("imgBirtDir", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	index_upload_path("index.upload.path", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	informesDir("informesDir", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	integracionLdap("integracionLdap", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ldapmanagerDN("ldapmanagerDN",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SPRINGLDAP_XML.getFilename()),
	ldapmanagerPasswd("ldapmanagerPasswd",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
					Ficheros.SEPARADOR+Ficheros.SPRINGLDAP_XML.getFilename()),
	ldappath("ldappath",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SPRINGLDAP_XML.getFilename()),
	ldapurl("ldapurl",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SPRINGLDAP_XML.getFilename()),
	ldapusuariosbindsubpath("ldapusuariosbindsubpath",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SPRINGLDAP_XML.getFilename()),
			
	rolebindsubpath("rolebindsubpath",true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()),
	maxWaitPoolSQI("maxWaitPoolSQI", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	maxWaitThreadSQI("maxWaitThreadSQI", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	neutro("neutro", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	nodo_taller("nodo.taller", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	nombre_imagen_repositorio_externo_defecto("nombre.imagen.repositorio.externo.defecto", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	nombreInformesPortadaSemanales("nombreInformesPortadaSemanales", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	nombreRepositorio("nombreRepositorio", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_generatedimgs("path.generatedimgs", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_imagenes_grupo_publico("path.imagenes.grupo.publico", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_imagenes_nodos_sqi("path.imagenes.nodos.sqi", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_imagenes_usuario_publico("path.imagenes.usuario.publico", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathDespublicacionMasiva("pathDespublicacionMasiva", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathImagenDefecto("pathImagenDefecto", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathImagenDefectoMedia("pathImagenDefectoMedia", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathImagenDefectoPeque("pathImagenDefectoPeque", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathIndice("pathIndice", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathIndiceNodos("pathIndiceNodos", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathInformesPortada("pathInformesPortada", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	pathPlantillasCorreo("pathPlantillasCorreo", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rangoInformesPortada("rangoInformesPortada", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rss("rss", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rss_federado_timeout("rss.federado.timeout", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rss_path("rss.path", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	searchPlugin("searchPlugin", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	segundosCaducidadHit("segundosCaducidadHit", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	server_id("server.id", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	server_on("server.on", true, Ficheros.AGREGA_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SEARCHPLUGIN_XML.getFilename()),
	staticImgDir("staticImgDir", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	tiempoRefresco("tiempoRefresco", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	timeout_autenticado("timeout.autenticado", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	timeout_extendido("timeout.extendido", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	timeoutCookieOpenId("timeoutCookieOpenId", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	timeout_identidad_federada("timeout.identidad.federada", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	url_logout_taller("url.logout.taller", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	url_licencias("url_licencias", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	urlRepositorio("urlRepositorio", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	usercnprefix("usercnprefix", true, Ficheros.AUTHBACKEND_PROPERTIES.getFilename()),
	version("version", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ccaa("ccaa",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	contacto_telefono("contacto_telefono", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	contacto_incidencias_activar("contacto_incidencias_activar", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	contacto_mail("contacto_mail", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_estadisticas("estadisticas.path", true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	smtp_host("smtp.host", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	smtp_sender("smtp.sender", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	smtp_autentication("smtp.authentication", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	smtp_user("smtp.user", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	smtp_passwd("smtp.passwd", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	debug("debug", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ldap_external("ldap.external", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	ldap_external_admin("ldap.external.admin", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	timeZone("timeZone", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	proxy("proxy", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	proxy_host("proxy.host", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	proxy_port("proxy.port", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	proxy_user("proxy.user", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	proxy_passwd("proxy.passwd", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	urlConsejeriaNodo("urlConsejeriaNodo", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	catalogo_agrega("catalogo.agrega", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	catalogo_mec("catalogo.mec", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	emailAdmin("emailAdmin", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	tiempolimite("tiempolimite", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	export_cache_tiempo("export.cache.tiempo", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	export_cache_size("export.cache.size", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	valorCuotaDefecto("valorCuotaDefecto", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	informesPortada("informesPortada", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	numeroTags("numeroTags", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	publicadosAutonomosHabilitado("publicadosAutonomosHabilitado", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rest_resultados_por_pagina("rest.resultados.por.pagina", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	perfilPublico("perfilPublico", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	rememberme("rememberme", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	numeroOdesEnFlash("numeroOdesEnFlash", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	secuencia_sin_logar("secuencia.sin.logar", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	check_password("check.password", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	numNoticiasMostradasEnResumen("numNoticiasMostradasEnResumen", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	numDescargasMostradasEnResumen("numDescargasMostradasEnResumen", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	indexServer_url("indexServer.url", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	indexServer_port("indexServer.port", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	indexServer_user("indexServer.user", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	indexServer_password("indexServer.password", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	vistaPreviaAgrega("vistaPreviaAgrega", false, Ficheros.AGREGA_PROPERTIES.getFilename()),
	nodo("nodo",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	altaUsuarioDesactivado("altaUsuarioDesactivado",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	activarEnvioCorreosAlarma("activarEnvioCorreosAlarma",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo("correoIncidenciaComunicacionNodo",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_AN("correoIncidenciaComunicacionNodo_AN",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_AR("correoIncidenciaComunicacionNodo_AR",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_AS("correoIncidenciaComunicacionNodo_AS",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_IB("correoIncidenciaComunicacionNodo_IB",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_IC("correoIncidenciaComunicacionNodo_IC",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_CB("correoIncidenciaComunicacionNodo_CB",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_CM("correoIncidenciaComunicacionNodo_CM",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_CL("correoIncidenciaComunicacionNodo_CL",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_CT("correoIncidenciaComunicacionNodo_CT",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_EU("correoIncidenciaComunicacionNodo_EU",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_EX("correoIncidenciaComunicacionNodo_EX",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_GA("correoIncidenciaComunicacionNodo_GA",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_LR("correoIncidenciaComunicacionNodo_LR",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_MA("correoIncidenciaComunicacionNodo_MA",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_MU("correoIncidenciaComunicacionNodo_MU",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_NA("correoIncidenciaComunicacionNodo_NA",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_CV("correoIncidenciaComunicacionNodo_CV",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_MECD("correoIncidenciaComunicacionNodo_MECD",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	correoIncidenciaComunicacionNodo_REDES("correoIncidenciaComunicacionNodo_REDES",true, Ficheros.AGREGA_PROPERTIES.getFilename()),
	
	//nombre, jmx, prefijo, fileList
	host("host", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.AUTHBACKEND_PROPERTIES.getFilename()),
//	galeriaImgPath("galeriaImgPath", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	nodo_jboss("nodo.jboss", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	path_logs("path_logs", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	puerto("puerto",true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	puerto_jboss("puerto.jboss", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.RTPDESIGN),
	
	subdominio("subdominio", true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.AUTHBACKEND_PROPERTIES.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SEARCHPLUGIN_XML.getFilename()+
			Ficheros.SEPARADOR+Ficheros.SITEMAPPORTADA_XML.getFilename()+
			Ficheros.SEPARADOR+Ficheros.RTPDESIGN+
			Ficheros.SEPARADOR+Ficheros.OPML+
			Ficheros.SEPARADOR+Ficheros.ROBOTS_TXT.getFilename()),
	subdominio_jboss("subdominio.jboss",true, getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	server_path("server.path", true,getNombreServidor(), Ficheros.AGREGA_PROPERTIES.getFilename()),
	casurl("casurl",true,getNombreServidor(),Ficheros.AUTHBACKEND_PROPERTIES.getFilename())
	;
	
	private String name;
	private String valor;
	private Boolean jmx;
	private String prefijo;
	private String fileList;
	
	private es.indra.agrega.jmx.PropiedadDao propiedadDao;

	Propiedades(String nombre, Boolean jmx) {
		this.name=nombre;
		this.jmx=jmx;
	}
	
	Propiedades(String nombre, Boolean jmx, String fileList) {
		this.name=nombre;
		this.jmx=jmx;
		this.fileList=fileList;
	}
	
	Propiedades(String nombre, Boolean jmx, String prefijo, String fileList) {
		this.name=nombre;
		this.jmx=jmx;
		this.prefijo=prefijo;
		this.fileList=fileList;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void setPropiedadDao(es.indra.agrega.jmx.PropiedadDao propiedadDao) {
		this.propiedadDao = propiedadDao;
	}

	public es.indra.agrega.jmx.PropiedadDao getPropiedadDao() {
		return propiedadDao;
	}

	public String getNombre() {
		return name;
	}
	
	/**
	 * @return the jmx
	 */
	public Boolean getJmx() {
		return jmx;
	}

	/**
	 * @param jmx the jmx to set
	 */
	public void setJmx(Boolean jmx) {
		this.jmx = jmx;
	}
	
	/**
	 * @return the fileList
	 */
	public String getFileList() {
		return fileList;
	}

	/**
	 * @return the prefijable
	 */
	public String getNombreConPrefijo() {
		if(prefijo!=null&&!prefijo.equals(""))
		{
			return prefijo+name;
		}
		return name;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor!=null?valor:"";
	}
	
	public static Propiedades getPropiedad(String name) {
		return valueOf(name.replace(".", "_"));
	}
	
	/** 
	 * Este método devuelve el nombre del nodo sobre el que se ejecuta la consola jmx.
	 * El nombre devuelto es el que se pasa como parámetro nombreNodoCluster en el arranque.   
	 * @return nombreNodo
	 */
	private static String getNombreServidor()
	{
		String nombreJbossCluster = System.getProperty("nombreNodoCluster");
		if (nombreJbossCluster!=null && nombreJbossCluster.isEmpty()==false)
			return nombreJbossCluster+"_";
		else
			return "default_";
		
	}
}
