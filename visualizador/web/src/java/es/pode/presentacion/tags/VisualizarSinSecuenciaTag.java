/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/

package es.pode.presentacion.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.agrega.soporte.tag.url.TransformarURL;
import es.pode.entregar.negocio.servicios.ItemVO;
import es.pode.entregar.negocio.servicios.OrganizacionVO;
import es.pode.entregar.negocio.servicios.RecursoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


public class VisualizarSinSecuenciaTag extends TagSupport{
	

	private OrganizacionVO[] organizaciones;

	private String localizacion;
	
	private String idSeleccionado;
	
	private String action;
	
	private ArrayList<String> rutaSeleccionado;
	
	private static Logger logger = Logger.getLogger(VisualizarSinSecuenciaTag.class);
	
	private static final String PROTOCOLO_HTTP="http://";
	private static final String PROTOCOLO_HTTPS="https://";
	private static final String HOST="host";
	
	public int doEndTag() throws JspException {
		
		return SKIP_BODY;
	}

	
	public int doStartTag() throws JspException {
		
		
		 JspWriter out = pageContext.getOut();

	        ///////formateo nivel
	        int nivelInt=1;
	        String nivel="";
	        if(nivelInt<10)
	        	nivel= "0"+String.valueOf(nivelInt);
	        /////////////////////
	       
			String claseOrgAbierto="nivel"+nivel+"_organizacion_abierto";
			String claseOrgCerrado="nivel"+nivel+"_organizacion_cerrado";
			nivelInt++;
			
			try {
			
		        Integer recursoId=new Integer(0);
		        String id="";
		        if(organizaciones!=null){
			        out.write("<ul>\n");
			        for(int j=0;j<organizaciones.length;j++){
			        	id=String.valueOf(recursoId);
			        	OrganizacionVO org =  organizaciones[j];
						String idOrg = org.getIdOrg();
						String tituloOrg = org.getTituloOrg();
						ItemVO[] items =  org.getItems();

						String claseItem;
						if(rutaSeleccionado!=null && rutaSeleccionado.contains(idOrg))
						{
							claseItem= claseOrgAbierto;
						}else
						{
							claseItem= claseOrgCerrado;
						}
						
						out.write("<li id=\""+ idOrg +"_"+ nivel +"\" class=\""+ claseItem +"\">\n");
					    out.write("<span onclick=\"abrirCerrar('"+ idOrg + "_" + nivel+"','"+ claseOrgAbierto+"','"+ claseOrgCerrado+"','li');return false\" onkeypress=\"abrirCerrar('"+ idOrg + "_" + nivel+"','"+ claseOrgAbierto+"','"+ claseOrgCerrado+"','li');return false\">&nbsp;</span>\n");
					    if(idOrg.equals(idSeleccionado))
						    out.write("<a href=\"" + action + "?idSeleccionado="+idOrg+"\" id=\""+idOrg+"\" target=\"\"  class=\"escogido\">"+tituloOrg+"</a>\n");
					    else
						    out.write("<a href=\"" + action + "?idSeleccionado="+idOrg+"\" id=\""+idOrg+"\" target=\"\" >"+tituloOrg+"</a>\n");
						
						
					    //llamada a metodo recursivo que pinta los items 
					    //recorremos el array de items llamado a dicho metodo con cada items
					    out.write("<ul>\n");
						for(int i = 0;i<items.length;i++){
					    	int rId = recursoId;
							rId++;
							recursoId = rId;
							recursoId= pintarItem(nivelInt,items[i],localizacion,out,recursoId);
						}
					    out.write("</ul>\n");
					    out.write("</li>\n");
				    }
			        id=String.valueOf(recursoId);
			        out.write("</ul>\n");
		        }else
		        {
		        	logger.error("Error dibujando VisualizadorSinSecuenciaTag: organizaciones nulo");
		        }
			} catch (IOException e) {
				logger.error("Error dibujando VisualizarSinSecuenciaTag",e);
				throw new JspException("Error dibujando VisualizarSinSecuenciaTag",e);
			}
			
	        return SKIP_BODY;
	}
	
	
	private Integer pintarItem(
			int nivelInt,
			ItemVO item,
			String localizacion,
			Writer out,
			Integer recursoId) 
	throws IOException
	{
		String encoding = super.pageContext.getResponse().getCharacterEncoding();

		String server = "";
		if (DecisorOffline.esOffline())
			server =  super.pageContext.getRequest().getServerName() + ":" + super.pageContext.getRequest().getServerPort();
		else
			server = AgregaPropertiesImpl.getInstance().getProperty(HOST) + LdapUserDetailsUtils.getSubdominio();

		
		String nivel="";
		if(nivelInt<10)
			nivel= "0"+String.valueOf(nivelInt);
		String claseOrgAbierto="nivel"+nivel+"_grupo_abierto";
		String claseOrgCerrado="nivel"+nivel+"_grupo_cerrado";
		ItemVO[] itemsHijos= item.getItemHijos();
		String idItem= item.getIdItem();
		RecursoVO recursoVO = item.getRecurso();
		String recurso="";
		if(item.getRecurso()!=null){
			recurso =item.getRecurso().getHrefRec();
		}

		String claseItem= "";
		String seleccionado="";
		if(idSeleccionado.equals(idItem))
			seleccionado=" class=\"escogido\" ";
		if(rutaSeleccionado!=null && rutaSeleccionado.contains(idItem))
		{
			claseItem= claseOrgAbierto;
		}else
		{
			claseItem= claseOrgCerrado;
		}

		
		String titulo= item.getTitulo();
		//out.write("<ul>\n");
		if(itemsHijos!=null && itemsHijos.length>0){
			if(item.getVisible()==null || item.getVisible()){

				if(recursoVO!=null  &&  recurso!=null){
					String href="";
					if(recurso.toLowerCase().startsWith(PROTOCOLO_HTTP) || recurso.toLowerCase().startsWith(PROTOCOLO_HTTPS)){
						href = recurso;
					}else{
						href = PROTOCOLO_HTTP + server +"/"+localizacion+"/"+recurso;
						href = TransformarURL.transformaUrl(encoding, href);
					}
					
					out.write("<li id=\""+idItem+"_"+nivel+"\" class=\""+claseItem+"\">\n");
					out.write("<span  onclick=\"abrirCerrar('"+idItem+"_"+nivel+"','"+claseOrgAbierto+"','"+claseOrgCerrado+"');return false\" onkeypress=\"abrirCerrar('"+idItem+"_"+nivel+"','"+claseOrgAbierto+"','"+claseOrgCerrado+"');return false\">&nbsp;</span>\n");
					out.write("<a href=\"" + action +	"?idSeleccionado="+idItem+"&urlContenido="+href+"\" "+ seleccionado + " id=\""+idItem+"\" target=\"\" >"+titulo+"</a>\n");

				}
				else{
					out.write("<li id=\""+idItem+"_"+nivel+"\" class=\""+claseItem+"\">\n");
					out.write("<span  onclick=\"abrirCerrar('"+idItem+"_"+nivel+"','"+claseOrgAbierto+"','"+claseOrgCerrado+"');return false\" onkeypress=\"abrirCerrar('"+idItem+"_"+nivel+"','"+claseOrgAbierto+"','"+claseOrgCerrado+"');return false\">&nbsp;</span>\n");
					out.write("<a href=\"" + action + "?idSeleccionado="+idItem+"\" "+ seleccionado + " id=\""+idItem+"\" target=\"\">"+titulo+"</a>\n");
				}

			}
			if(item.getVisible()==null || item.getVisible()){
				nivelInt++;
				//pintamos los itemsHijos
				out.write("<ul>\n");
				for(int i=0;i<itemsHijos.length;i++){
					int rId = recursoId;
					rId++;
					recursoId = rId;
					recursoId = pintarItem(nivelInt,itemsHijos[i],localizacion,out,recursoId);					
				}
				out.write("</ul>\n");
			}
			out.write("</li>\n");	
		}
		else{//itemsHijos==null
			if(item.getVisible()==null || item.getVisible()){
				if(recursoVO!=null  &&  recurso!=null){
					
					String href="";
					if(recurso.toLowerCase().startsWith(PROTOCOLO_HTTP) || recurso.toLowerCase().startsWith(PROTOCOLO_HTTPS)){
						href = recurso;
					}else
					{
						href = PROTOCOLO_HTTP + server +"/"+localizacion+"/"+recurso;
						href = TransformarURL.transformaUrl(encoding, href);
					}
					
					out.write("<li class=\"item\">\n");
					out.write("<span>&nbsp;\n");//1
					out.write("<a href=\"" + action +	"?idSeleccionado="+idItem+"&urlContenido="+href+"\" "+ seleccionado + " id=\""+idItem+"\" target=\"\">"+titulo+"</a>\n");
					out.write("</span>\n");
					out.write("</li>\n");
					
				}
				else{
					out.write("<li id=\""+idItem+"_"+nivel+"\" class=\"item\">\n");
					out.write("<span>&nbsp;\n");
					out.write("<a href=\"" + action + "?idSeleccionado="+idItem+"\" id=\""+idItem+"\" target=\"\">"+titulo+"</a>\n");
					out.write("</span>\n");
					out.write("</li>\n");
				}
			}
		}
		//out.write("</ul>\n");
		return recursoId;			
	}
	
	
	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}


	public OrganizacionVO[] getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(OrganizacionVO[] organizaciones) {
		this.organizaciones = organizaciones;
	}


	public String getIdSeleccionado() {
		return idSeleccionado;
	}


	public void setIdSeleccionado(String idSeleccionado) {
		this.idSeleccionado = idSeleccionado;
	}


	
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public ArrayList<String> getRutaSeleccionado() {
		return rutaSeleccionado;
	}


	public void setRutaSeleccionado(ArrayList<String> rutaSeleccionado) {
		this.rutaSeleccionado = rutaSeleccionado;
	}


}