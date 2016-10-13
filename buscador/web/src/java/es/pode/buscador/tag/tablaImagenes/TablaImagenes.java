package es.pode.buscador.tag.tablaImagenes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.buscador.presentacion.basico.listar.ListarAreaCurricularColumDecorator;
import es.pode.buscador.presentacion.basico.listar.ListarTipoRecursoColumDecorator;
import es.pode.buscador.tag.formato.FormatoTag;
import es.pode.buscador.tag.valoracion.ValoracionTag;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.url.Proxy;

public class TablaImagenes extends BodyTagSupport {
	
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(TablaImagenes.class);	
	
	private final static String PUNTO = "\\.";
	
	private static final long serialVersionUID = 1L;	
	private String titulo = null;
	private String identificadorODE = null;
	private String url = null;
	private String valoracion = null;
	private String numValoraciones = null;
	
	private String nivelAgregacionTexto = null;
	private String[] formato = null;
	private String numeroODE = null;
	private String urlImagen = null;
	private String nodo = null;
	private int contador = 0;	
	private Object areaCurricular = null;
	private Object tipoRecurso = null;	
	private boolean usuarioAdministrador =false;
	private boolean usuarioPublicador =false;
	private Boolean esVisualizable = null;
	private String urlCambiarImagen=null;
	private String urlPrevisualizar=null;
	
	public String getIdentificadorODE() {
		return identificadorODE;
	}
	public void setIdentificadorODE(String identificadorODE) {
		this.identificadorODE = identificadorODE;
	}
	public Object getTipoRecurso() {
		return tipoRecurso;
	}
	public void setTipoRecurso(Object tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}		
	public Object getAreaCurricular() {
		return areaCurricular;
	}
	public void setAreaCurricular(Object areaCurricular) {
		this.areaCurricular = areaCurricular;
	}
	public String getValoracion() {
		return valoracion;
	}
	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	public String getnivelAgregacionTexto(){
		return nivelAgregacionTexto;
	}
	public void setnivelAgregacionTexto (String nivelAgregacionTexto){
		this.nivelAgregacionTexto = nivelAgregacionTexto;
	}
	public String[] getFormato() {
		return formato;
	}
	public void setFormato(String[] formato) {
		this.formato = formato;
	}
	public String getNumeroODE() {
		return numeroODE;
	}
	public void setNumeroODE(String numeroODE) {
		this.numeroODE = numeroODE;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}	
	public boolean getUsuarioAdministrador() {
		return usuarioAdministrador;
	}
	public void setUsuarioAdministrador(boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}
	public Boolean getEsVisualizable(){
		return esVisualizable;
	}
	public void setEsVisualizable (Boolean esVisualizable){
		this.esVisualizable = esVisualizable;
	}
	public String getNodo() {
		return nodo;
	}
	public void setNodo(String nodo) {
		this.nodo = nodo;
	}
	public String getUrlCambiarImagen() {
		return urlCambiarImagen;
	}
	public void setUrlCambiarImagen(String urlCambiarImagen) {
		this.urlCambiarImagen = urlCambiarImagen;
	}
	public String getUrlPrevisualizar() {
		return urlPrevisualizar;
	}
	public void setUrlPrevisualizar(String urlPrevisualizar) {
		this.urlPrevisualizar = urlPrevisualizar;
	}
	public boolean isUsuarioPublicador() {
		return usuarioPublicador;
	}
	public void setUsuarioPublicador(boolean usuarioPublicador) {
		this.usuarioPublicador = usuarioPublicador;
	}
	public String getNumValoraciones() {
		return numValoraciones;
	}
	public void setNumValoraciones(String numValoracion) {
		this.numValoraciones = numValoracion;
	}
	
	
	// Esta funcion comprueba si existe la imagen y en caso de no ser asi devuelve la url de la de por defecto
	private String comprobarImagen(String urlImagen){	
		try{
			new InputStreamReader(Proxy.getInputStream(new URL(urlImagen), 2000));
			return urlImagen;
		}catch(Exception ex){
			logger.debug("Se supero el timeout que comprueba la existencia de la imagen de un ODE - " + ex.getCause());
			logger.debug("",ex);
    		String host = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
    		String subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
    		String imagenActual = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO_MEDIA);
    		//Esto de añadir un parametro con un numero aleatorio a la url es para que los navegadores no cacheen la imagen
    		imagenActual=imagenActual.substring(imagenActual.indexOf("/"))+"?"+new GregorianCalendar().getTimeInMillis();
			return "http://"+host+subdominio+imagenActual;
		}
	}
	
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
    public int doStartTag() throws JspException{
    	try {	    		
    		
    		JspWriter out = pageContext.getOut();	    		
    		
    		//RECUPERAMOS EL AREA CURRICULAR DEL DECORATOR
    		ListarAreaCurricularColumDecorator areaCurricularTag = new ListarAreaCurricularColumDecorator();
    		String htmlAreaCurricular = (String)areaCurricularTag.decorate(areaCurricular, pageContext, null);
    			
    		//RECUPERAMOS EL TIPO DE RECURSO DEL DECORATOR 
			ListarTipoRecursoColumDecorator tipoRecursoTag = new ListarTipoRecursoColumDecorator();    			
			String htmlTipoRecurso = (String)tipoRecursoTag.decorate(tipoRecurso, pageContext, null);
			String htmlCuadroTipoRecurso = tipoRecursoTag.concatenaArrayString((String[])tipoRecurso);
			    				
			//RECUPERAMOS EL NIVEL DE AGREGACION
			String htmlNivelAgregacion = this.getnivelAgregacionTexto();
				
			//RECUPERAMOS LOS TIPOS DE FORMATO
			String[] tiposFormato = this.getFormato();
				
//    				String htmlTiposFormato = "";
//    				for (int i=0;i<tiposFormato.length;i++){
//    					htmlTiposFormato = htmlTiposFormato + tiposFormato[i]+", ";
//    				}
				
				
			//REALIZAMOS EL TRATAMIENTO DE LOS LITERALES PARA PODER INTRODUCIRLOS EN EL CÓDIGO HTML
			String fichero="application-resources";
			Locale locale = null;
			try {
				locale = devuelveLocale();
			} catch (Exception e) {
				logger.error("Error recuperando el locale - ",e);
			}
			String literalTitu=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.titulo", fichero, locale);	
			String literalArea=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.area.curricular", fichero, locale);
			String literalAgregacion=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.nivel.agregacion", fichero, locale);
			String literalRecurso=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.tipo.recurso", fichero, locale);
			String literalFormato=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.tipo.formato", fichero, locale);
			String literalValora=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.valoracion", fichero, locale);   
			String literalVotos=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.votos", fichero, locale); 
			String literalVerMasRecursos=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.ver.mas", fichero, locale);
			String literalAplicacion=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.aplicacion", fichero, locale);
			String literalTexto=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.texto", fichero, locale);
			String literalImagen=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.imagen", fichero, locale);
			String literalAudio=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.audio", fichero, locale);
			String literalVideo=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.video", fichero, locale);
//    			String literalCambiarImagen=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.cambiarImagen", fichero, locale);
//    			String literalPrevisualizar=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.previsualizar", fichero, locale);
				
//    			Ampliar la imagen de la galeria
//    			StringBuilder urlImagenReturn = new StringBuilder("");
//    			String imagenSmall = getPropertyValue("imagen.ampliada.png");
			urlImagen=urlImagen.replace("."+getPropertyValue("imagen.ampliada.png"), getPropertyValue("imagen.ampliada.medium"));
			
//    			String imagenAmpliada = this.getPropertyValue("imagen.ampliada.captured");    			
//    			String[] urlImagenArray = urlImagen.split(PUNTO);
//    			for(int i=0; urlImagenArray!=null && i<urlImagenArray.length; i++){
//    				if((imagenSmall).equals(urlImagenArray[i]))
//    					urlImagenArray[i] = imagenAmpliada;
//    				urlImagenReturn.append(urlImagenArray[i]);
//    			}
			
			if(nodo!=null && !nodo.trim().equals("")){
//    				urlImagenReturn = new StringBuilder("http://"+nodo+urlImagenReturn.toString().trim());
				urlImagen="http://"+nodo+urlImagen.trim();
			}else{
//    				urlImagenReturn = new StringBuilder("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlImagenReturn.toString().trim());
				urlImagen="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlImagen.trim();
			}
			urlImagen=comprobarImagen(urlImagen);
			
			out.println("<section class=\"bloque_resultados clearfix\">");
			out.println("<div class=\"valoracion\">");
			out.println("<em>" + numValoraciones + " " + literalVotos + "</em>");
			out.println("<div>");
			out.println("<ol>");
			ValoracionTag estrellasValoracion = new ValoracionTag();
			estrellasValoracion.calcularEstrellas(valoracion,out,pageContext.getRequest(),"","");
			out.println("</ol>");
			out.println("</div>");
			out.println("<strong>" + literalValora + ":</strong>");
			out.println("</div>");
			
			if (esVisualizable){
				out.println("<p class=\"fix\"><strong class=\"tipo_label\">" + literalTitu + ":</strong><span>" + 
						"<a class=\"enlace\" href=\""+"http://" + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio() + url + "\">" + titulo + "</a>" +
						"<a href=\""+"http://" + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio() + url + "\" title=\"Lupa\" class=\"lupa\">&nbsp;<em>Objeto digital educativo</em>" +
						"<span class=\"ventana\"><span class=\"caji\"><img src=\"" + urlImagen + "\" alt=\"Imagen&nbsp;" + numeroODE + "\" /></span></span></a></span> </p>");
			}else{
				out.println("<p class=\"fix\"><strong class=\"tipo_label\">" + literalTitu + ":</strong><span>" + 
						titulo + 
						"<a href=\"\" title=\"Lupa\" class=\"lupa\">&nbsp;<em>Objeto digital educativo</em>" +
						"<span class=\"ventana\"><span class=\"caji\"><img src=\"" + urlImagen + "\" alt=\"Imagen&nbsp;" + numeroODE + "\" /></span></span></a></span> </p>");
			}
			
			out.println("<p ><strong class=\"tipo_label\">" + literalFormato + ":</strong><span>");
			FormatoTag tagFormato = new FormatoTag();
			tagFormato.pintarFormato(tiposFormato,literalAplicacion,literalTexto,literalImagen,literalAudio,literalVideo,esVisualizable,out);
			out.println("</span> </p>");
	
			out.println("<p ><strong class=\"tipo_label\">" + literalAgregacion + ":</strong><span>" + htmlNivelAgregacion + "</span> </p>");
			
			if (htmlTipoRecurso.endsWith("...</span>")){
//				out.println("&nbsp;" +"<a href=\"#\" class=\"mas_informacion\">"+ "<em>"+ literalVerMasRecursos+"</em>"+ htmlCuadroTipoRecurso + "</td>");

    			out.println("<p ><strong class=\"tipo_label\">" + literalRecurso + ":</strong>" +
    					"<span>" + htmlTipoRecurso + "<em id=\"n" + contador + "\" class=\"caja_cerrada\" >" + htmlCuadroTipoRecurso + "</em> <a class=\"desplegado\" id=\"pn" + contador + "\" href=\"que_ofrecemos.html\" onclick=\"expandirCaja('n" + contador + "');return false;\" onkeypress=\"expandirCaja('n" + contador + "');return false;\"><strong id=\"dn" + contador + "\" style=\"font-weight:normal\">" + literalVerMasRecursos + "</strong></a></span>  </p>");
			} else {
    			out.println("<p ><strong class=\"tipo_label\">" + literalRecurso + ":</strong>" + htmlTipoRecurso + "</p>");
			}
			
			//TODO Mejor en propiedades
			int numElementosVistaPrevia=4;
			String vista_previa;
			String vista_extendida;

			//Contamos guiones ya que sera el numero de elementos de la lista   
			StringBuilder vistaPrevia=new StringBuilder();
			StringBuilder vistaExtendida=new StringBuilder();
			String[] elementos=htmlAreaCurricular.split("-");
			for (int i = 0; i < elementos.length; i++) {
				if(i<numElementosVistaPrevia) {
					vistaPrevia.append(elementos[i]);
				} else {
					vistaExtendida.append(elementos[i]);
				}
			}
			
			vista_previa=vistaPrevia.toString();
			vista_extendida=vistaExtendida.toString();
			
			if (elementos.length < numElementosVistaPrevia || vista_previa.equals(vista_extendida) || vista_extendida.isEmpty()) {
    			out.println("<p ><strong class=\"tipo_label\">" + literalArea + ":</strong>" +
    					"<span>" + htmlAreaCurricular + "</span></p>");
			} else {
    			out.println("<p ><strong class=\"tipo_label\">" + literalArea + ":</strong>" +
    					"<span>" + vista_previa + 
    						"<em id=\"m" + contador + "\" class=\"caja_cerrada\" >" + vista_extendida + "</em> " +
    								"<a class=\"desplegado\" id=\"pm" + contador + "\" href=\"que_ofrecemos.html\" onclick=\"expandirCaja('m" + contador + "');return false;\" onkeypress=\"expandirCaja('m" + contador + "');return false;\">" +
    										"<strong id=\"dm" + contador + "\" style=\"font-weight:normal\">" + literalVerMasRecursos + "</strong>" +
    								"</a>" +
    					"</span></p>");
			}
	   		out.println("</section>");
    			
    	} catch (IOException e) {
    		logger.warn("TablaImagenes - Error formando tabla de imagenes - continuamos."+ e.getCause());
    		logger.debug("",e);
    	} catch (Exception e) {
    		logger.warn("TablaImagenes - Error formando tabla de imagenes - continuamos"+ e.getCause());
    		logger.debug("",e);
    	}
    	return SKIP_BODY;	
    }	   
	    
	    
    /**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
	public int doEndTag(){
		return SKIP_BODY; 
	}
	
		
	//	MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        try{
        	ResourceBundle theResourceBundle = getResource(baseName,locale);
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        	logger.error("Recurso ausente - ",mre);
        }catch (Exception e){
        	recurso = key;
        	logger.error(e);
        }
        return recurso;
    }
	
	public static ResourceBundle getResource(String baseName, Locale locale){
        try{
        	return ResourceBundle.getBundle(baseName,locale);
        }catch (MissingResourceException mre){
    		locale = new Locale("","");
    		logger.warn("Recurso ausente- ",mre);
    		return ResourceBundle.getBundle(baseName,locale);
        }catch (Exception e){
        	logger.error("Error recuperando literal para="+baseName+" ",e);
        	return null;
        }
    }   
    
    
    private Locale devuelveLocale() throws Exception
	{
		Locale locale=null;
		if (pageContext.getRequest() instanceof HttpServletRequest) {
			locale = (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		} else { 	    					
			try {
				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			} catch (Exception e) {	
				logger.error("Error recuperando el locale de la request - ",e);					
			}
		}
		return locale;
	}
    
    
    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador2.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();	            
        }catch (Exception e){
    		logger.error("Error recuperando propiedad de spring_buscador.properties - ",e);
        }
		return pSpringProperties.getProperty(sKey);
	}
	    
}