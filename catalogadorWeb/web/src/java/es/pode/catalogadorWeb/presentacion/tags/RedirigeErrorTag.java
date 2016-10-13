/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/

package es.pode.catalogadorWeb.presentacion.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RedirigeErrorTag extends TagSupport{
	
	
	private static String HOST;
	private static String PUERTO;
	private static String SUBDOMINIO;
	
	public int doEndTag() throws JspException {
		
		return SKIP_BODY;
	}

	
//	public int doStartTag() throws JspException {
//		
//		
//		 JspWriter out = pageContext.getOut();
//		
//		 HttpSession session = pageContext.getSession();
//		 Object valor1 = session.getAttribute("catalogadorAvSession");
//		 Object valor2 = session.getAttribute("catalogadorBSession");
//		 //ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) pageContext.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
//		 Locale idiomaLocale=pageContext.getRequest().getLocale();
//		 ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", idiomaLocale);
//
//		HOST = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
//		PUERTO = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.PUERTO);
//		SUBDOMINIO = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
//		if(SUBDOMINIO==null)SUBDOMINIO="";
//
//			
//		StringBuffer sb = new StringBuffer("");
//		try {
//			String identificador="";
//			String returnUrl = "";
//			String idioma = "";
//			String usuario = "";
//			if (valor1!=null){ //estamos en avanzado
//				CatalogadorAvSession catAvanzadoSession = (CatalogadorAvSession) valor1;
//				identificador=catAvanzadoSession.getIdentificador();
//				returnUrl=catAvanzadoSession.getReturnURL();
//				idioma = catAvanzadoSession.getIdioma();
//				usuario= catAvanzadoSession.getUsuario();
//    		}else if (valor2!=null){//estamos en básico
//    			CatalogadorBSession catBasicoSession = (CatalogadorBSession) valor2;
//    			identificador=catBasicoSession.getIdentificador();
//    			returnUrl=catBasicoSession.getReturnURL();
//    			idioma=catBasicoSession.getIdioma();
//    			usuario=catBasicoSession.getUsuario();
//    		} 
//			//<rewrite:rewrite url="buscador/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda="
//			String url="";
//			String url_cat = session.getServletContext().getInitParameter("url_catalogador");
//			String prueba = "";
//			if (session.getAttribute("ACEGI_SAVED_REQUEST_KEY")!=null) {
//			  prueba = ((SavedRequest)session.getAttribute("ACEGI_SAVED_REQUEST_KEY")).getRequestURI();
//			} 
//			if (((prueba.equals("") || prueba.endsWith("VerMetadatos.do"))) && ((identificador==null && returnUrl==null && idioma==null && usuario==null) || (identificador.equals("") && returnUrl.equals("") && idioma.equals("") && usuario.equals("") ))){ //es vermetadatos o han qitado las cookies
//				url_cat = session.getServletContext().getInitParameter("url_portaladministracion");
//				url = "http://" + HOST + SUBDOMINIO +"/" + url_cat;
//			} else {//es el catalogador					
//				url = "http://" + HOST + SUBDOMINIO +"/" + url_cat;
//				url =  url + "?idioma=" + idioma + "&usuario=" + usuario + "&identificador=" + identificador + "&returnURL=" + returnUrl;
//			}
//										
//			//una vez que tengo la url pinto la jsp!! ../../catalogadorWeb/InicioCatalogador/InicioCatalogador.do?idioma=es&usuario=administrador&identificador=idLOM1&returnURL=PortalEmpaquetador/Inicio/Inicio.do
//			///////////////////////
//			//out.print("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+this.getUrl());
//			
//			sb.append("<form id=\"errorAplicacion\" action=\"").append(url).append(" \" method=\"post\" >");
//			sb.append("<div class=\"globo_gris\" >");
//				sb.append("<div class=\"globo_gris_01\">");
//					sb.append("<div class=\"globo_gris_02\">");
//						sb.append("<div class=\"globo_gris_03\">");
//							sb.append("<div id=\"formulario\" class=\"ali_c\">");
//								sb.append("<br />");
//									sb.append("<p>").append(datosResources.getString("error.400.message")).append("</p>");
//								sb.append("<br />");
//							sb.append("</div>");								
//						sb.append("</div>");
//					sb.append("</div>");
//				sb.append("</div>");
//			sb.append("</div>");
//			sb.append("<input class=\"boton_125 tipo ft_centrada\"  type=\"submit\"  value=\"").append(datosResources.getString("usuarios.aceptar")).append("\" />");
//		sb.append("</form>");
//			///////////////////
//		
//		//tenemos que borrar los errores
//		session.setAttribute("org.apache.struts.action.ACTION_MESSAGE", null);
//		String sb_datos= sb.toString();
//	    out.write(sb_datos);
//	        
//		} catch (IOException e) {
//			logger.error("Error enTag RedirigeErrorTag",e);
//			throw new JspException("Error enTag RedirigeErrorTag",e);
//		}
//		
//        return SKIP_BODY;
//	}
	
	public static String getHost()
	{
		return HOST;
	}
	
	public static String getPort()
	{
		return PUERTO;
	}
	
	public static String getSubdominio()
	{
		return SUBDOMINIO;
	}
	
	
}