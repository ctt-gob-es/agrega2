package es.pode.configuracionPlataforma.negocio.dominio;

public enum Ficheros {
	//OPML ATOM
	AGREGA_ATOM_CA("agrega_atom_ca.opml","uploads/rss/"),
	AGREGA_ATOM_EN("agrega_atom_en.opml","uploads/rss/"),
	AGREGA_ATOM_ES("agrega_atom_es.opml","uploads/rss/"),
	AGREGA_ATOM_EU("agrega_atom_eu.opml","uploads/rss/"),
	AGREGA_ATOM_GL("agrega_atom_gl.opml","uploads/rss/"),
	AGREGA_ATOM_VA("agrega_atom_va.opml","uploads/rss/"),
	//OPML RSS
	AGREGA_RSS_CA("agrega_rss_ca.opml","uploads/rss/"),
	AGREGA_RSS_EN("agrega_rss_en.opml","uploads/rss/"),
	AGREGA_RSS_ES("agrega_rss_es.opml","uploads/rss/"),
	AGREGA_RSS_EU("agrega_rss_eu.opml","uploads/rss/"),
	AGREGA_RSS_GL("agrega_rss_gl.opml","uploads/rss/"),
	AGREGA_RSS_VA("agrega_rss_va.opml","uploads/rss/"),
	//CONF GENERAL
	AUTHBACKEND_PROPERTIES("authbackend.properties","../server/default/conf/"),
	SPRINGLDAP_XML("springldap.xml","../server/default/conf/"),
	AGREGA_PROPERTIES("agrega.properties","../server/default/conf/"),
	//PARA UPLOADS
	ROBOTS_TXT("robots.txt","uploads/sitemaps/estatico/"),
	SITEMAP("sitemapPortada.xml","uploads/sitemaps/estatico/"),
	SEARCHPLUGIN_XML("searchPlugin.xml","uploads/searchPlugin"),
	//INFORMES
	COBERTURA_CURRICULAR("coberturaCurricular.rptdesign", "informes/plantillasInformes/"),
	COBERTURA_CURRICULAR_FEDERADA("coberturaCurricularFederada.rptdesign", "informes/plantillasInformes/"),
	ESTADO_ODES("estadoOdes.rptdesign", "informes/plantillasInformes/"),
	INFORME_CARGA("informeCarga.rptdesign", "informes/plantillasInformes/"),
	MAS_DESCARGADO("masDescargado.rptdesign", "informes/plantillasInformes/"),
	MAS_MOSTRADO("masMostrado.rptdesign", "informes/plantillasInformes/"),
	MAS_PREVISUALIZADO("masPrevisualizado.rptdesign", "informes/plantillasInformes/"),
	MAS_VALORADO("masValorado.rptdesign", "informes/plantillasInformes/"),
	NIVEL_AGREGACION("nivelAgregacion.rptdesign", "informes/plantillasInformes/"),
	NIVEL_AGREGACION_FEDERADA("nivelAgregacionFederada.rptdesign", "informes/plantillasInformes/"),
	ODES_CARGADOS("odesCargados.rptdesign", "informes/plantillasInformes/"),
	ODES_IDIOMA_FEDERADA("odesIdiomaFederada.rptdesign", "informes/plantillasInformes/"),
	ODES_LICENCIAS("odesLicencias.rptdesign", "informes/plantillasInformes/"),
	ODES_PUBLICADOS_FEDERADOS("odesPublicadosFederados.rptdesign", "informes/plantillasInformes/"),
	ODES_USUARIO("odesUsuario.rptdesign", "informes/plantillasInformes/"),
	OPERACIONES_REALIZADAS("operacionesRealizadas.rptdesign", "informes/plantillasInformes/"),
	PROCESOS_PLANIFICADOS("procesosPlanificados.rptdesign", "informes/plantillasInformes/"),
	REPOSITORIO("repositorio.rptdesign", "informes/plantillasInformes/"),
	TAMANIO("tamanio.rptdesign", "informes/plantillasInformes/"),
	TERMINOS_BUSQUEDA("terminosBusqueda.rptdesign", "informes/plantillasInformes/"),
	USUARIOS("usuarios.rptdesign", "informes/plantillasInformes/"),
	;
	private String filename;
	private String destino;
	
	public static String SEPARADOR = ";";
	
	public static String OPML= AGREGA_ATOM_CA.getFilename()+
					SEPARADOR+AGREGA_ATOM_EN.getFilename()+
					SEPARADOR+AGREGA_ATOM_ES.getFilename()+
					SEPARADOR+AGREGA_ATOM_EU.getFilename()+
					SEPARADOR+AGREGA_ATOM_GL.getFilename()+
					SEPARADOR+AGREGA_ATOM_VA.getFilename()+
					SEPARADOR+AGREGA_RSS_CA.getFilename()+
					SEPARADOR+AGREGA_RSS_EN.getFilename()+
					SEPARADOR+AGREGA_RSS_ES.getFilename()+
					SEPARADOR+AGREGA_RSS_EU.getFilename()+
					SEPARADOR+AGREGA_RSS_GL.getFilename()+
					SEPARADOR+AGREGA_RSS_VA.getFilename();
	
	public static String RTPDESIGN = COBERTURA_CURRICULAR.getFilename()+
							SEPARADOR+COBERTURA_CURRICULAR_FEDERADA.getFilename()+
							SEPARADOR+ESTADO_ODES.getFilename()+
							SEPARADOR+INFORME_CARGA.getFilename()+
							SEPARADOR+MAS_DESCARGADO.getFilename()+
							SEPARADOR+MAS_MOSTRADO.getFilename()+
							SEPARADOR+MAS_PREVISUALIZADO.getFilename()+
							SEPARADOR+MAS_VALORADO.getFilename()+
							SEPARADOR+NIVEL_AGREGACION.getFilename()+
							SEPARADOR+NIVEL_AGREGACION_FEDERADA.getFilename()+
							SEPARADOR+ODES_CARGADOS.getFilename()+
							SEPARADOR+ODES_IDIOMA_FEDERADA.getFilename()+
							SEPARADOR+ODES_LICENCIAS.getFilename()+
							SEPARADOR+ODES_PUBLICADOS_FEDERADOS.getFilename()+
							SEPARADOR+ODES_USUARIO.getFilename()+
							SEPARADOR+OPERACIONES_REALIZADAS.getFilename()+
							SEPARADOR+PROCESOS_PLANIFICADOS.getFilename()+
							SEPARADOR+REPOSITORIO.getFilename()+
							SEPARADOR+TAMANIO.getFilename()+
							SEPARADOR+TERMINOS_BUSQUEDA.getFilename()+
							SEPARADOR+USUARIOS.getFilename();
	
	Ficheros(String filename, String destino) {
		this.filename=filename;
		this.destino=destino;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}
	
	@Override
	public String toString() {
		return filename;
	}
	
	public static Ficheros getFichero(String name) {
		return valueOf(name.replace(".", "_").toUpperCase());
	}
}
