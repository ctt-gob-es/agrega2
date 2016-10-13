/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.buscador.tag.linkOaiOre;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class LinkOaiOreTag extends BodyTagSupport{

	private MetadatoOiaOreVO metadatoOaiOre;
	
	private static Logger logger = Logger.getLogger(LinkOaiOreTag.class);
	
	private static final String PROTOCOLO_HTTP="http://";
	
	
	public int doStartTag() throws JspException{
		try {
			JspWriter out = pageContext.getOut();
			String urlhost = PROTOCOLO_HTTP+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
			StringBuffer links=new StringBuffer("");
			StringBuffer linkOaireInicio = new StringBuffer("<link href=\"").append(urlhost).append("/oaiore");
			StringBuffer linkOaireFin=new StringBuffer("\" rel=\"alternate\" type=\"application/atom+xml\" />\n");
			
			
//			LINK NIVEL AGREGACION
			links.append(linkOaireInicio)
				.append("?tipo=1&nivelAgregacion=")
				.append(metadatoOaiOre.getNivelAgregacion())
				.append(linkOaireFin);
//			LINK AREA CURRICULAR
			String[] areaCurriculares = metadatoOaiOre.getAreaCurricular();
			if(areaCurriculares!=null && areaCurriculares.length >0){
				for(int i=0; i< areaCurriculares.length;i++){
					links.append(linkOaireInicio)
					.append("?tipo=2&areaCurricular=")
					.append(areaCurriculares[i])
					.append(linkOaireFin);
				}
			}
//			LINK TESAURO
			String[] tesauros = metadatoOaiOre.getTesauros();
			if(tesauros!=null && tesauros.length >0){
				for(int i=0; i< tesauros.length;i++){
					links.append(linkOaireInicio)
					.append("?tipo=3&tesauro=")
					.append(tesauros[i])
					.append(linkOaireFin);
				}
			}
			
			out.print(links.toString());
			
			
		}catch (Exception e) {
			logger.error("LinkOaiOreTag Error generando los link oaiore",e);	    			
		}
		return SKIP_BODY;	

	}
	

	public int doEndTag(){
		return SKIP_BODY; 
	}


	public MetadatoOiaOreVO getMetadatoOaiOre() {
		return metadatoOaiOre;
	}


	public void setMetadatoOaiOre(MetadatoOiaOreVO metadatoOaiOre) {
		this.metadatoOaiOre = metadatoOaiOre;
	}
	
}
