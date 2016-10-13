/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscador.presentacion.basico.listar.ListarAreaCurricularColumDecorator;
import es.pode.buscador.presentacion.basico.listar.ListarTipoRecursoColumDecorator;
import es.pode.buscador.tag.formato.FormatoTag;
import es.pode.buscador.tag.valoracion.ValoracionTag;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.Proxy;

public class TablaImagenes extends BodyTagSupport {
	
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(TablaImagenes.class);	
	
	private final static String PUNTO = "\\.";
	
	private static final long serialVersionUID = 1L;	
	private static final int divisionPar=2;
	private String titulo = null;
	private String identificadorODE = null;
	private String url = null;
	private String valoracion = null;	
	
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
    		String imagenActual = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO);
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
			String literalVerMasRecursos=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.ver.mas", fichero, locale);
			String literalAplicacion=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.aplicacion", fichero, locale);
			String literalTexto=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.texto", fichero, locale);
			String literalImagen=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.imagen", fichero, locale);
			String literalAudio=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.audio", fichero, locale);
			String literalVideo=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.video", fichero, locale);
			String literalCambiarImagen=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.cambiarImagen", fichero, locale);
			String literalPrevisualizar=TablaImagenes.getResource("listar.odecu.mostrar.resultados.consulta.vo.previsualizar", fichero, locale);
				
			//Ampliar la imagen de la galeria
			/*
			StringBuilder urlImagenReturn = new StringBuilder("");
			String imagenSmall = this.getPropertyValue("imagen.ampliada.png");
			String imagenAmpliada = this.getPropertyValue("imagen.ampliada.captured");    			
			String[] urlImagenArray = urlImagen.split(PUNTO);
			for(int i=0; urlImagenArray!=null && i<urlImagenArray.length; i++){
				if((imagenSmall).equals(urlImagenArray[i]))
					urlImagenArray[i] = imagenAmpliada;
				urlImagenReturn.append(urlImagenArray[i]);
			}
			*/
			//urlImagen=urlImagen.replace("."+getPropertyValue("imagen.ampliada.png"), getPropertyValue("imagen.ampliada.medium"));
			
			if(nodo!=null && !nodo.trim().equals("")){
				//urlImagenReturn = new StringBuilder("http://"+nodo+urlImagenReturn.toString().trim());
				urlImagen="http://"+nodo+urlImagen.trim();
			}else{
				//urlImagenReturn = new StringBuilder("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlImagenReturn.toString().trim());
				urlImagen="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlImagen.trim();
			}
			urlImagen=comprobarImagen(urlImagen);
		
			//CÓDIGO HTML	    		
			//Comprobamos si el contador es par o impar para pintar el fondo blanco o gris.
			if(esPar(contador))
	    		out.println("<tr class=\"tr_gris\">");
			else
    			out.println("<tr class=\"tr_blanco\">");
			
			if (esVisualizable.booleanValue())
				out.println("<td  valign=\"top\" class=\"ancho_det sin_e_pad\"><a href=\"" + "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+url + "\" class=\"imagen_tab\"><img width=\"100\" height=\"100\" src=\"" + urlImagen + "\" alt=\"Imagen&nbsp;" + numeroODE + "\" /></a>");
			else
				out.println("<td  valign=\"top\" class=\"ancho_det sin_e_pad\"><img width=\"100\" height=\"100\" src=\"" + urlImagen + "\" alt=\"Imagen&nbsp;" + numeroODE + "\" />");
			
			if ((usuarioAdministrador && (this.nodo==null || this.nodo.equals("")))||(usuarioPublicador && (this.nodo==null || this.nodo.equals("")))){
				urlCambiarImagen="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlCambiarImagen.trim();
				out.println("<a href=\""+ urlCambiarImagen+"\" title=\""+literalCambiarImagen+"\"><span>"+literalCambiarImagen+"</span></a>");													
			}
			urlPrevisualizar="http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+urlPrevisualizar.trim();
			if (esVisualizable.booleanValue()){
				out.println("<a href=\""+ urlPrevisualizar+"\" target=\"_blank\" title=\""+literalPrevisualizar+"\"><span>"+literalPrevisualizar+"</span></a>");   
			}
 			
			out.println("</td>");
			out.println("<td valign=\"top\"  class=\"sin_e_pad\"><strong class=\"numeracion_imagen\">" + numeroODE + "</strong>");
			
			if ((usuarioAdministrador && (this.nodo==null || this.nodo.equals("")))||(usuarioPublicador && (this.nodo==null || this.nodo.equals("")))){
	    		out.println("<div class=\"check_flotante\">");
    			out.println("<label for=\"ch01\" class=\"etiq_invisible\" >Seleccione </label><input type=\"checkbox\" id=\"" + this.getIdentificadorODE() + "\" "	+ " name=\"idRowSelectionAsArray\" value=\"" + this.getIdentificadorODE() + "\"/>");
    			out.println("</div>");
			}
			
			out.println("<table border=\"0\" style=\"border:0\" class=\"resultados_listados\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" summary=\"Tabla de Resultado de Búsqueda\">");
			out.println("<tr>");
			out.println("<td class=\"ancho_tab_min_0\" valign=\"top\"><span>" + literalTitu + ":" + "</span></td>");
			out.println("<td class=\"ancho_tab_min\"  valign=\"top\">");
			
			if (esVisualizable.booleanValue()){
				out.println("<a href=\""+"http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+url+"\">" + titulo + "</a></td>");
			}else{
				out.println("<span class=\"restringido\">"+titulo + "</span></td>");
			}
			//mostrar unicamente el enlace si "ambito" contiene universal o el identificador que recupero
			    				
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td class=\"ancho_tab_min_0\" valign=\"top\"><span>" + literalFormato + ":" + "</span></td>");
				out.println("<td class=\"ancho_tab_min \" valign=\"top\">");
					FormatoTag tagFormato = new FormatoTag();
					tagFormato.pintarFormato(tiposFormato,literalAplicacion,literalTexto,literalImagen,literalAudio,literalVideo,out);
				out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td class=\"ancho_tab_min_0\" valign=\"top\"><span >" + literalValora + ":" + "</span></td>");
				out.println("<td class=\"ancho_tab_min\" valign=\"top\" >");
					//out.println("<div class=\"valoracion\">");
						//out.println("<ul>");
							//REALIZAMOS EL TRATAMIENTO DE LAS ESTRELLAS DE VALORACION LLAMANDO AL MÉTODO YA EXISTENTE
							ValoracionTag estrellasValoracion = new ValoracionTag();
							estrellasValoracion.calcularEstrellas(valoracion,out,pageContext.getRequest(),"");
						//out.println("</ul>");
	    			//out.println("</div>");
	    		out.println("</td>");
	    	out.println("</tr>");
			out.println("<tr>");
				out.println("<td class=\"ancho_tab_min_0\" valign=\"top\"><span>" + literalArea + ":" + "</span></td>");
				out.println("<td class=\"ancho_tab_min\" valign=\"top\">" + htmlAreaCurricular + "</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td class=\"ancho_tab_min_0\" valign=\"top\"><span>" + literalAgregacion + ":" + "</span></td>");
				out.println("<td class=\"ancho_tab_min \" valign=\"top\">" + htmlNivelAgregacion + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"ancho_tab_min_0 flotder\" ><span>" + literalRecurso + ":" + "</span></td>");
			out.println("<td class=\"ancho_tab_min mas_info\" valign=\"top\">" + htmlTipoRecurso);
			if (htmlTipoRecurso.endsWith("...</span></p></a>")){
				out.println("&nbsp;" +"<a href=\"#\" class=\"mas_informacion\">"+ "<em>"+ literalVerMasRecursos+"</em>"+ htmlCuadroTipoRecurso + "</td>");
			}
			out.println("</tr>");
			out.println("</table>");
			out.println("</td>");
			out.println("</tr>");					
				
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		logger.error("Error formando tabla de imagenes - ",e);	    		
    	}catch (Exception e) {
    		// TODO Auto-generated catch block
    		logger.error("Error formando tabla de imagenes - ",e);	    		
    	}
		return SKIP_BODY;	
	}	 
	    
	    
    /**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
	public int doEndTag(){
		return SKIP_BODY; 
	}
		
	
//			MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        try{
        	ResourceBundle theResourceBundle = getResource(baseName,locale);
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        } catch (MissingResourceException mre) {
        	recurso = key;
		logger.warn("Recurso ausente - ",mre);
        } catch (Exception e) {
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
		logger.warn("Recurso ausente - ",mre);
    		return ResourceBundle.getBundle(baseName,locale);
        }catch (Exception e){
        	logger.error("Error recuperando literal para="+baseName+" - ",e);
        	return null;
        }
    }   
    
        
    //Método que comprueba si el contador es par o impar. De esta manera el fondo de la tabla será gris o blanco
    private boolean esPar(int contador){	    	
    	if(contador%divisionPar==0)
    		return true;
    	else
    		return false;
    }	
    
    
    private Locale devuelveLocale() throws Exception {
		Locale locale=null;
		if (pageContext.getRequest() instanceof HttpServletRequest) {
			locale = (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		} else { 	    					
			try {
				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			} catch (Exception e) {	
				logger.error("Error recuperando el locale de la request",e);					
			}
		}
		return locale;
	}
    
    
    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("BuscarAvanzadoControllerImpl - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
			//devolvemos la propiedad
        }catch (Exception e){
    		logger.error("TablaImagenes - Error recuperando propiedad de spring_buscador.properties=",e);
        }
		return pSpringProperties.getProperty(sKey);
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
	    	
}