/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx;

import java.io.File;

import es.indra.agrega.jmx.Propiedades;

public enum Ficheros {
	//OPML ATOM
	AGREGA_ATOM_CA_OPML("agrega_atom_ca.opml","uploads/rss/"),
	AGREGA_ATOM_EN_OPML("agrega_atom_en.opml","uploads/rss/"),
	AGREGA_ATOM_ES_OPML("agrega_atom_es.opml","uploads/rss/"),
	AGREGA_ATOM_EU_OPML("agrega_atom_eu.opml","uploads/rss/"),
	AGREGA_ATOM_GL_OPML("agrega_atom_gl.opml","uploads/rss/"),
	AGREGA_ATOM_VA_OPML("agrega_atom_va.opml","uploads/rss/"),
	//OPML RSS
	AGREGA_RSS_CA_OPML("agrega_rss_ca.opml","uploads/rss/"),
	AGREGA_RSS_EN_OPML("agrega_rss_en.opml","uploads/rss/"),
	AGREGA_RSS_ES_OPML("agrega_rss_es.opml","uploads/rss/"),
	AGREGA_RSS_EU_OPML("agrega_rss_eu.opml","uploads/rss/"),
	AGREGA_RSS_GL_OPML("agrega_rss_gl.opml","uploads/rss/"),
	AGREGA_RSS_VA_OPML("agrega_rss_va.opml","uploads/rss/"),
	
	//CONF GENERAL
	/*
	AUTHBACKEND_PROPERTIES("authbackend.properties","../server/default/conf/"),
	SPRINGLDAP_XML("springldap.xml","../server/default/conf/"),
	AGREGA_PROPERTIES("agrega.properties","../server/default/conf/"),
	*/
	/*
	AUTHBACKEND_PROPERTIES("authbackend.properties","../server/"+getNombreServidor()+"/conf/"),
	SPRINGLDAP_XML("springldap.xml","../server/"+getNombreServidor()+"/conf/"),
	AGREGA_PROPERTIES("agrega.properties","../server/"+getNombreServidor()+"/conf/"),
	*/
	AUTHBACKEND_PROPERTIES("authbackend.properties",getHomeServidor()+"conf/"),
	SPRINGLDAP_XML("springldap.xml",getHomeServidor()+"conf/"),
	AGREGA_PROPERTIES("agrega.properties",getHomeServidor()+"conf/"),
	
	//PARA UPLOADS
	ROBOTS_TXT("robots.txt","uploads/sitemaps/estatico/"),
	SITEMAPPORTADA_XML("sitemapPortada.xml","uploads/sitemaps/estatico/"),
	SEARCHPLUGIN_XML("searchPlugin.xml","uploads/searchPlugin"),
	
	//INFORMES
	COBERTURACURRICULAR_RPTDESIGN("coberturaCurricular.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	COBERTURACURRICULARFEDERADA_RPTDESIGN("coberturaCurricularFederada.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ESTADOODES_RPTDESIGN("estadoOdes.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	INFORMECARGA_RPTDESIGN("informeCarga.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	MASDESCARGADO_RPTDESIGN("masDescargado.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	MASMOSTRADO_RPTDESIGN("masMostrado.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	MASPREVISUALIZADO_RPTDESIGN("masPrevisualizado.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	MASVALORADO_RPTDESIGN("masValorado.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	NIVELAGREGACION_RPTDESIGN("nivelAgregacion.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	NIVELAGREGACIONFEDERADA_RPTDESIGN("nivelAgregacionFederada.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ODESCARGADOS_RPTDESIGN("odesCargados.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ODESIDIOMAFEDERADA_RPTDESIGN("odesIdiomaFederada.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ODESLICENCIAS_RPTDESIGN("odesLicencias.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ODESPUBLICADOSFEDERADA_RPTDESIGN("odesPublicadosFederada.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	ODESUSUARIO_RPTDESIGN("odesUsuario.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	OPERACIONESREALIZADAS_RPTDESIGN("operacionesRealizadas.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	PROCESOSPLANIFICADOS_RPTDESIGN("procesosPlanificados.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	REPOSITORIO_RPTDESIGN("repositorio.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	TAMANIO_RPTDESIGN("tamanio.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	TERMINOSBUSQUEDA_RPTDESIGN("terminosBusqueda.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	USUARIOS_RPTDESIGN("usuarios.rptdesign", getHomeServidor()+"informes/plantillasInformes/"),
	;
	private String filename;
	private String destino;
	
	public static String SEPARADOR = ";";
	
	public static String OPML= AGREGA_ATOM_CA_OPML.getFilename()+
					SEPARADOR+AGREGA_ATOM_EN_OPML.getFilename()+
					SEPARADOR+AGREGA_ATOM_ES_OPML.getFilename()+
					SEPARADOR+AGREGA_ATOM_EU_OPML.getFilename()+
					SEPARADOR+AGREGA_ATOM_GL_OPML.getFilename()+
					SEPARADOR+AGREGA_ATOM_VA_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_CA_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_EN_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_ES_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_EU_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_GL_OPML.getFilename()+
					SEPARADOR+AGREGA_RSS_VA_OPML.getFilename();
	
	public static String RTPDESIGN = COBERTURACURRICULAR_RPTDESIGN.getFilename()+
							SEPARADOR+COBERTURACURRICULARFEDERADA_RPTDESIGN.getFilename()+
							SEPARADOR+ESTADOODES_RPTDESIGN.getFilename()+
							SEPARADOR+INFORMECARGA_RPTDESIGN.getFilename()+
							SEPARADOR+MASDESCARGADO_RPTDESIGN.getFilename()+
							SEPARADOR+MASMOSTRADO_RPTDESIGN.getFilename()+
							SEPARADOR+MASPREVISUALIZADO_RPTDESIGN.getFilename()+
							SEPARADOR+MASVALORADO_RPTDESIGN.getFilename()+
							SEPARADOR+NIVELAGREGACION_RPTDESIGN.getFilename()+
							SEPARADOR+NIVELAGREGACIONFEDERADA_RPTDESIGN.getFilename()+
							SEPARADOR+ODESCARGADOS_RPTDESIGN.getFilename()+
							SEPARADOR+ODESIDIOMAFEDERADA_RPTDESIGN.getFilename()+
							SEPARADOR+ODESLICENCIAS_RPTDESIGN.getFilename()+
							SEPARADOR+ODESPUBLICADOSFEDERADA_RPTDESIGN.getFilename()+
							SEPARADOR+ODESUSUARIO_RPTDESIGN.getFilename()+
							SEPARADOR+OPERACIONESREALIZADAS_RPTDESIGN.getFilename()+
							SEPARADOR+PROCESOSPLANIFICADOS_RPTDESIGN.getFilename()+
							SEPARADOR+REPOSITORIO_RPTDESIGN.getFilename()+
							SEPARADOR+TAMANIO_RPTDESIGN.getFilename()+
							SEPARADOR+TERMINOSBUSQUEDA_RPTDESIGN.getFilename()+
							SEPARADOR+USUARIOS_RPTDESIGN.getFilename();
	
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

	/** 
	 * Este método devuelve el nombre del nodo sobre el que se ejecuta la consola jmx.
	 * El nombre devuelto es el que se pasa como parámetro nombreNodoCluster en el arranque.   
	 * @return nombreNodo
	 */
	private static String getNombreServidor()
	{
		String nombreJbossCluster = System.getProperty("nombreNodoCluster");
		if (nombreJbossCluster!=null && nombreJbossCluster.isEmpty()==false)
			return nombreJbossCluster;
		else
			return "default";
	}
	
	private static String getHomeServidor()
	{
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");
		} catch (Exception e) {
			return "";
		}
		return homeJboss+File.separator;
	}
}
