package es.pode.presentacion.tags;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.pode.soporte.utiles.base64.Base64Coder;

public class ContenidosTag extends TagSupport {
	private static Logger logger = Logger.getLogger(ContenidosTag.class);
	private static final String DEFAULT="default";
	private static final String VIEWER="viewer";
	private static final String ERROR="error";
	
	private String id;
	private String urlContenido;
	
	private String mensajeIframe0="0";
	private String mensajeIframe1="1";
	private String mensajeIframe2="2";
	private String mensajeIframe3="3";
	private String zohoKey;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrlContenido() {
		return urlContenido;
	}
	public void setUrlContenido(String urlContenido) {
		this.urlContenido = urlContenido;
	}
	
	public String getMensajeIframe0() {
		return mensajeIframe0;
	}
	public void setMensajeIframe0(String mensajeIframe0) {
		this.mensajeIframe0 = mensajeIframe0;
	}
	public String getMensajeIframe1() {
		return mensajeIframe1;
	}
	public void setMensajeIframe1(String mensajeIframe1) {
		this.mensajeIframe1 = mensajeIframe1;
	}
	public String getMensajeIframe2() {
		return mensajeIframe2;
	}
	public void setMensajeIframe2(String mensajeIframe2) {
		this.mensajeIframe2 = mensajeIframe2;
	}
	public String getMensajeIframe3() {
		return mensajeIframe3;
	}
	public void setMensajeIframe3(String mensajeIframe3) {
		this.mensajeIframe3 = mensajeIframe3;
	}
	@Override
	public int doEndTag() throws JspException 
	{
		return SKIP_BODY;
	}
	
	
	@Override
	public int doStartTag() 
	throws JspException 
	{
		zohoKey = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ZOHO_KEY);
		
		
		String tipoDoc=tipoDocumento(urlContenido);
		String urlParam="";
		if(urlContenido!=null && !urlContenido.equals("") )
			urlParam=" src='"+ urlContenido +"' ";
		StringBuilder sb= new StringBuilder();
		
		sb.append("<iframe id='").append(id).append("' name='").append(id).append("' ");
		if(tipoDoc.equals(ERROR) && !DecisorOffline.esOffline()){
			String src= "http://viewer.zoho.com/api/view.do?url="+ urlContenido +"&apikey=" + zohoKey + "&embed=true";
			sb.insert(0, "<p>" + mensajeIframe0 + " <a href='"+ urlContenido +"' target='" + id + "'>" + mensajeIframe1+ "</a></p>\n");
			if(verificarTransformacionZOHO(src))
				sb.append(" src='"+ src +"' " );
			else
				sb.append(" src='").append(AgregaPropertiesImpl.getInstance().getUrlNodo()).append("/visualizador-1/layout/error.jsp' " );
		}else if(tipoDoc.equals(ERROR)){
			sb.append("<p>" + mensajeIframe0 + " <a href='"+ urlContenido +"' >" + mensajeIframe1+ "</a></p>\n");
			sb.append(" src='").append(AgregaPropertiesImpl.getInstance().getUrlNodo()).append("/visualizador-1/layout/error.jsp' " );
		}else{
			sb.append(urlParam );
		}

		sb.append("scrolling='auto'  frameborder='0' marginheight='0' marginwidth='0' style='width:200px; height:200px;'>" + mensajeIframe2+ " <a href='"+ urlContenido +"'>"+ mensajeIframe3+ "</a></iframe><hr/>");

		
		JspWriter out = pageContext.getOut();
		try {
			out.write(sb.toString());
		} catch (IOException e) {
			logger.error("Error al dibujar los contenidos. ", e);
		}

		return SKIP_BODY;
	}
	
	private String tipoDocumento(String href)
	{
		String resultado="";
		
		if(href==null || href=="")
			resultado = DEFAULT;
		else{
			int i = href.lastIndexOf(".");
			String extension=href.substring(i,href.length()).toLowerCase();
			if(	extension.equals(".doc") || extension.equals(".docx") ||
				extension.equals(".ppt") || extension.equals(".pptx") ||
				extension.equals(".pps") ||  
				extension.equals(".xls") || extension.equals(".xlsx") ||
				extension.equals(".odt") || extension.equals(".odp") || extension.equals(".ods") ||
				extension.equals(".sxw") || extension.equals(".sxi") || extension.equals(".sxc") ||
				extension.equals(".pdf") || extension.equals(".rtf") || extension.equals(".txt") ||
				extension.equals(".csv"))
				resultado=VIEWER;
			else if(extension.equals(".avi") ||
					extension.equals(".dvx") ||  
					extension.equals(".mp4") ||  
					extension.equals(".mp4v") ||  
					extension.equals(".mpg") ||  
					extension.equals(".mpg2") ||  
					extension.equals(".mpeg") ||
					extension.equals(".wm") ||
					extension.equals(".wmv"))
					resultado=ERROR;
			else
				resultado=DEFAULT;
   		}
		return resultado;
	}
	
	
	private boolean verificarTransformacionZOHO(
			String urlDocumento)
	{
		boolean resultado = false;
		
		try{
			AgregaProperties agrega =AgregaPropertiesImpl.getInstance(); 
	    	String proxyUsuario = agrega.getProperty(AgregaProperties.USUARIOPROXY);
	    	String proxyPassword= agrega.getProperty(AgregaProperties.CLAVEPROXY);
	    	String proxyHost= agrega.getProperty(AgregaProperties.HOSTPROXY);
	    	String proxyPort= agrega.getProperty(AgregaProperties.PORTPROXY);
	    	boolean usaProxy= Boolean.getBoolean(agrega.getProperty(AgregaProperties.USAPROXY));
	    	
	    	String url ="http://viewer.zoho.com/api/view.do?url=";
	    	String keys="&apikey=" + zohoKey + "&embed=true";
			
		      // PROXY
	    	if(usaProxy)
			{
	    		System.setProperty("http.proxyHost",proxyHost) ;
	    		System.setProperty("http.proxyPort",proxyPort) ;
			}
	    	
			logger.debug("[issuing request: " + urlDocumento + "]");
			
			URL urlCompleta= new URL(url + urlDocumento + keys);
			HttpURLConnection connection = (HttpURLConnection)urlCompleta.openConnection();
			connection.setRequestMethod("GET");
			
	    	if(usaProxy)
			{
				// write auth header
//				BASE64Encoder encoder = new BASE64Encoder();
				String encodedCredential = new String(Base64Coder.encode( (proxyUsuario + ":" + proxyPassword).getBytes()));
				connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);
				connection.setRequestProperty("Proxy-Authorization", "Basic " + encodedCredential);
			}
			// write body if we're doing POST or PUT
			byte buffer[] = new byte[8192];
			int read = 0;
			
			connection.connect();
			
			InputStream responseBodyStream = connection.getInputStream();
			StringBuffer responseBody = new StringBuffer();
			while ((read = responseBodyStream.read(buffer)) != -1)
			{
			    responseBody.append(new String(buffer, 0, read));
			}
			connection.disconnect();
			
			
        	if(responseBody.indexOf("\"result\":\"Failure\",") > 0)
        	{
        		logger.debug("error general.");
        		resultado= true;
        	}else
        	{
        		logger.debug("Documento convertido con éxito");
        	}

			
		}catch (Exception e) {
			resultado= false;
			logger.error("error al llamar a la api de ZOHO:" , e);
		}
		return resultado;
	}
	
	
}