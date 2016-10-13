// license-header java merge-point
package es.pode.visualizador.presentacion.conexionFlash;

import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO;
import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;
import es.pode.visualizador.presentacion.agregaSlider.AgregaSliderControllerImpl;



/**
 * @see es.pode.visualizador.presentacion.conexionFlash.conexionFlashController
 */
public class conexionFlashControllerImpl extends conexionFlashController
{
	private static Logger logger = Logger.getLogger(AgregaSliderControllerImpl.class);
	ResourceBundle ficheroRecursos = null;
 
    /**
     * @see es.pode.visualizador.presentacion.conexionFlash.conexionFlashController#construirXML(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.conexionFlash.ConstruirXMLForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void construirXML(ActionMapping mapping, es.pode.visualizador.presentacion.conexionFlash.ConstruirXMLForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Recogemos de la request las palabras con la que hacemos la busqueda y el idioma
    	String palabras = request.getParameter("palabras");
    	String idioma = request.getParameter("idioma");
    	
    	//construimos un locale con el idioma que nos viene como paramatro
    	Locale locale = new Locale(idioma);
    	
    	//recogemos la url por defecto de soporte
    	String urlImagenDefecto = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_GRANDE);
    	String urlLogoAgrega = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA);
    	
    	//declaramos ficheroRecursos para poder acceder al fichero de las etiquetas y recoger la url de la ficha
    	ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
   	 String urlFicha=request.getSession().getServletContext().getInitParameter("url_buscadorDetalleCorta2");
   	 String origen=request.getSession().getServletContext().getInitParameter("origen_embed");
	   	
		//recogemos el nodo correspodiente desde soporte
		String nodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
    	
    	//declaramos los VOs de recogida y envio para la llamada al buscar
    	ParametrosBusquedaAvanzadaVO30 parametros = new ParametrosBusquedaAvanzadaVO30();
    	ValoresBusquedaVO[] valoresBusqueda;
    	
    	//rellenamos el VO que tenemos que pasarle al buscar para realizar la busqueda de imagenes
    	if(logger.isDebugEnabled())logger.debug("Rellenamos el VO para pasarlo al buscar");
    	parametros.setPalabrasClave(palabras);
    	parametros.setIdiomaBusqueda(idioma);
    	parametros.setIdiomaNavegacion(idioma);
    	parametros.setBusquedaSimpleAvanzada("Busqueda AgregaSlider");
    	parametros.setNumeroResultados(20);
    	parametros.setOrigenPagina(1);
    	parametros.setResultadosPorPagina(20);
    	parametros.setComunidadPeticion(nodo);
    	
    	//rellenamos la primera linea del xml
    	StringBuilder xml = new StringBuilder("<?xml version='1.0' encoding='utf-8'?> <GALERIA>");
    	
    	//rellenamos la primera posicion con los datos de la portada de la aplicacion para asociarla al logo agrega
    	xml.append("<IMAGEN " +
		"TITULO='portada' " +
		"URL_FICHA='http://" + nodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+ "/visualizadorcontenidos/Portada/Portada.do' "+
		"URL_IMAGEN='http://" + nodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+  "/" + urlLogoAgrega + "' />");
    	
    	try
    	{
    		//llamamos al servicio buscar
    		ResultadoBusquedaVO resultado = this.getSrvBuscarService().buscarAvanzado(parametros);
    		valoresBusqueda = resultado.getResultadoBusqueda();
    		if(logger.isDebugEnabled())logger.debug("valores de  busqueda : " + valoresBusqueda.length);
        	
        	if(valoresBusqueda.length != 0)
        	{
        		//recorremos el array de imagenes para construir la url de la imagen correstamente y si no tuviera asignarle una por defecto
	        	for(int i=0; i<valoresBusqueda.length; i++)
	        	{
	        		if(valoresBusqueda[i].getUrlImagen() == null || valoresBusqueda[i].getUrlImagen().equalsIgnoreCase(""))
	        		{
	        			valoresBusqueda[i].setUrlImagen(urlImagenDefecto);
	        			if(logger.isDebugEnabled())logger.debug("no hay imagen en" + valoresBusqueda[i].getTitulo());
	        			
	        		}
	        		else
	        		{
	        			//Contruimos la url de la imagen
	        			String url = valoresBusqueda[i].getUrlImagen();
	        			String urls[] = url.split("\\.");
	        			url = urls[0] + "_captured.jpg";
	        			valoresBusqueda[i].setUrlImagen(url);
	        			
	        			//filtramos para que en el titulo no aparezcan comillas
	        			String cadena = valoresBusqueda[i].getTitulo();
	        			valoresBusqueda[i].setTitulo(StringEscapeUtils.escapeXml(cadena));
	        		}
	        	}
	        	
	        	//se rellena el xml con los datos devueltos del busca
	    		
	    		if(logger.isDebugEnabled())logger.debug("se han encontrado" + valoresBusqueda.length + "elementos");
	    		for(int i=0; i<valoresBusqueda.length; i++)
	    		{
	    			if(logger.isDebugEnabled())logger.debug("valoresBusqueda[i].getUrlImagen() "+i+":"+valoresBusqueda[i].getUrlImagen());
	    			xml.append("<IMAGEN " +"TITULO='"+ valoresBusqueda[i].getTitulo() + "' " +
	    			"URL_FICHA='http://" + nodo +AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+ "/" + urlFicha + "/" + idioma + "/" + valoresBusqueda[i].getId() +"/"+origen+ "' " +
	    			"URL_IMAGEN='http://" + nodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+ "/" + valoresBusqueda[i].getUrlImagen() + "' />");
	    		}
	    		xml.append("</GALERIA>");
        	}
        	else
        	{
        		if(logger.isDebugEnabled())logger.debug("No se ha encontrado nada en el buscar y se rellena manualmente");
        		if(logger.isDebugEnabled())logger.debug("urlImagenDefecto "+urlImagenDefecto);
        		//Se rellena el xml con una imagen por defecto porque no se ha encontrado ninguna
        		xml.append("<IMAGEN TITULO='Imagen Agrega' URL_FICHA='#' " +
        		"URL_IMAGEN='http://" + nodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+"/" + urlImagenDefecto + "'/>" +
        		"</GALERIA>");
        	}
    		
    	}
    	catch(Exception e)
    	{
    		logger.error("exception en concexion flash " + e);
    		//Se rellena el xml con una imagen por defecto porque no se ha encontrado ninguna
    		xml.append("<IMAGEN TITULO='Imagen Agrega' URL_FICHA='#' " +
    		"URL_IMAGEN='http://" + nodo + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+ "/" + urlImagenDefecto + "'/>" +
    		"</GALERIA>");
    	}
    	
    	if(logger.isDebugEnabled())logger.debug(xml);
    	//Mandamos el xml a flash como un array de bytes
    	if(logger.isDebugEnabled())logger.debug("Mandamos el xml a flash como un array de bytes");
    	
		response.setContentType("text/xml");
    	
        OutputStream out = response.getOutputStream();
        out.write(xml.toString().getBytes());

        out.flush();
        out.close();

        logger.debug("Se ha mandado a flash el xml");
    }

}