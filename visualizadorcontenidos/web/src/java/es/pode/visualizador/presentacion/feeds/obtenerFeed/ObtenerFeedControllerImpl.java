/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.feeds.obtenerFeed;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

/**
 * @see es.pode.visualizador.presentacion.feeds.obtenerFeed.ObtenerFeedController
 */
public class ObtenerFeedControllerImpl extends ObtenerFeedController
{
	private static final String ACCION_FEED_KO="FEED_KO";
	private static final String ACCION_FEED_OK="FEED_OK";
	private static final String MENSAJE_ERROR_FEED="error.obtenerFicheroFeed";

	private static Logger log = Logger.getLogger(ObtenerFeedControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.feeds.obtenerFeed.ObtenerFeedController#obtenerFeed(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.feeds.obtenerFeed.ObtenerFeedForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerFeed(ActionMapping mapping, es.pode.visualizador.presentacion.feeds.obtenerFeed.ObtenerFeedForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
    	try{
    		log.debug("idFeed = " + form.getIdFeed() + " feedNum = " + form.getFeedNum() + " idiomaNavegacion= " + form.getIdiomaNavegacion());
//    		String urlHost = "http://" + LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
////    		URL url = new URL(urlHost + "/obtenerRss?idFeed="+form.getIdFeed()+"&feedNum="+form.getFeedNum()+"&idiomaNavegacion="+form.getIdiomaNavegacion());
////   		
////    		InputStream in = url.openStream();
//    		
////    		char[] encoded;
//    		InputStream in;
//    		
//    		URL url = new URL(urlHost + "/obtenerRss?idFeed="+form.getIdFeed()+"&feedNum="+form.getFeedNum()+"&idiomaNavegacion="+form.getIdiomaNavegacion());
//
//    		in = Proxy.getInputStream(url);
//			
//			byte[] buffer = new byte[BUFFER_SIZE];
//			int count;
//			response.setContentType("text/xml; charset=UTF-8");//si pongo utf-8 se ve bien sea cual sea el charset que traiga el fichero tanto iso como utf8
////			response.setContentType("text/xml; charset=ISO-8859-1");//si pongo iso se ve mal 
//			OutputStream out = response.getOutputStream();
//			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
//			{
//				out.write(buffer, 0, count);
//			}
//			out.flush();
			
/*    		
    		Pattern digitos = Pattern.compile("\\d");
    		
    		if(!digitos.matcher(form.getIdFeed().split("\\.")[0]).matches()) {
    			log.debug("Parece que el id pertenece a un RSS de versiones anteriores. ");
    			form.setIdFeed(getSrvAgregadorRssService().obtenerIdCompatibilidad(form.getIdFeed()));
    			log.debug("Lo convertimos a "+form.getIdFeed());
    		}
*/    		
    		String xmlFeed = getSrvAgregadorRssService().obtenerXMLFeedPublico(form.getIdFeed(),form.getFeedNum(),form.getIdiomaNavegacion());//form.getIdiomaNavegacion()
    		response.setContentType("text/xml");
			response.setCharacterEncoding("ISO-8859-1");
    		PrintWriter writer = response.getWriter();  		
    		writer.write(xmlFeed);
    		writer.close();
			
			form.setResultado(ACCION_FEED_OK);
			
			
    	}catch(Exception e){
			log.error("Error al intentar obtener el Rss " + form.getIdFeed(),e);
			form.setResultado(ACCION_FEED_KO);
			saveErrorMessage(request,MENSAJE_ERROR_FEED);
		}
     }


	@Override
	public String analizarResultado(ActionMapping mapping, AnalizarResultadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return form.getResultado();
	}









}