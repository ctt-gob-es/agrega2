/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.visualizador.presentacion.tag.listadoNubeTag;

import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import es.pode.indexador.negocio.servicios.busqueda.PalabraClaveVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;

public class ListadoNubeTag extends BodyTagSupport{

	private java.util.Properties pSpringProperties = null;
	
	private static Logger logger = Logger.getLogger(ListadoNubeTag.class);	
	
	private PalabraClaveVO[] tags;
	
	private String url;
	
    public int doStartTag() throws JspException{
  	
    	try {
    		String pAlfabeto = this.getPropertyValue("alfabeto");
    		
    		String[] alfabeto = pAlfabeto.split(",");
    		
    		String fichero="application-resources";
    		Locale locale = null;
    		try {
    			locale = devuelveLocale();
    		} catch (Exception e) {
    			logger.error("Error recuperando el locale",e);
    		}
    		String sNoExistenEtiquetas=this.getResource("listarNubeTags.listaTagsVacia", fichero, locale);
    		    		
    		JspWriter out = pageContext.getOut(); 
    		
    		//ORDENAMOS LOS TAGS POR ORD…N ALFAB…TICO Y DESPU…S POR RANKING
        	Arrays.sort(tags, new RankingAlfabetico());
        	
        	int indexLetra=0;
        	String letraActual=alfabeto[indexLetra];
        	String tr_gris="";
        	//INICIAMOS LA PRIMERA LETRA SI EXISTE ALG⁄N TAG
        	if(tags.length>0){
        		out.write(this.inicioLetra(letraActual,indexLetra));
        	}
        	pAlfabeto.replace(",", "");
        	for(int i = 0; i < tags.length; i++){
        		String tagActual = tags[i].getPalabraClave(); 
        		String tagActualSinAcento = this.removeAccents(tagActual);
        		char inicioTag= tagActualSinAcento.toUpperCase().charAt(0);
        		int posicionAlfabeto = pAlfabeto.indexOf(inicioTag);
        		if(posicionAlfabeto>-1){
	        		if(!tagActualSinAcento.toUpperCase().startsWith(letraActual)){
	        			//SI EL TAG ACTUAL NO PERTENECE A LA LETRA QUE ESTAMOS ESCRIBIENDO CERRAMOS LA LETRA Y 
	        			//BUSCAMOS LA LETRA QUE LE CORRESPONDE. SI LA SIGUENTE LETRA NO LE CORRESPONDE QUIERE DECIR
	        			//QUE NO HAY TAG PARA ESA LETRA Y SI PINTA VACIA
	        			out.write(this.finLetra(alfabeto,indexLetra));
	        			indexLetra++;
	        			letraActual=alfabeto[indexLetra];
	        			while(!tagActualSinAcento.toUpperCase().startsWith(letraActual) && indexLetra!= alfabeto.length){
	        				out.write(this.inicioLetra(letraActual,indexLetra));
	                		out.write("<tr>");
	                		out.write("<td valign=\"top\" class=\"tar4\" >" + sNoExistenEtiquetas + "</td>");
	                		out.write("</tr>");
	        				out.write(this.finLetra(alfabeto,indexLetra));
	        				indexLetra++;
	        				if(indexLetra!=alfabeto.length)
	        					letraActual=alfabeto[indexLetra];
		        			
	        			}
	        			//COMIENZO DE LA LETRA QUE LE CORRESPONDE AL TAG ACTUAL
	        			out.write(this.inicioLetra(letraActual,indexLetra));
	        			tr_gris="";
	        		}
	        		
	        		String urlCompleta = url + "?keyword=" + tags[i].getPalabraClave() + "&tipoBusqueda=03";
	        		out.write("<tr" +tr_gris+">");
	        		out.write("<td valign=\"top\" class=\"tar4\" ><a href=\""+urlCompleta+"\">" + tags[i].getPalabraClave()+ "</a></td>");
	        		out.write("<td valign=\"top\" class=\"ejec\"><span class=\"oculto\">-</span>"+tags[i].getRanking()+"</td>");
	        		out.write("</tr>");
	        		tr_gris = tr_gris.equals("")?" class=\"tr_gris\"":"";
        		}
        	
        	}
        	//FINALIZAMOS LA LETRA ACTUAL(ULTIMA LETRA QUE CONTENGA ALG⁄N TAG) SI EXISTE ALG⁄N TAGS
        	if(tags.length>0){
        		out.write(this.finLetra(alfabeto,indexLetra));
        		indexLetra++;
        	}
        	//ESCRIBIMOS LAS LETRAS QUE NO TENGAN NINGUN TAG A PARTIR DE LA LETRA ACTUAL
        	//EL CASO DE QUE NO HAYA TAGS SE ESCRIBEN TODAS LAS LETRAS VACIAS
        	if(indexLetra != alfabeto.length){
	        	for(int i = indexLetra; i <alfabeto.length ;i++){
	        		letraActual=alfabeto[indexLetra];
	        		out.write(this.inicioLetra(letraActual,indexLetra));
            		out.write("<tr>");
            		out.write("<td valign=\"top\" class=\"tar4\" >" + sNoExistenEtiquetas + "</td>");
            		out.write("</tr>");
    				out.write(this.finLetra(alfabeto,indexLetra));
        			indexLetra++;	
	        	}
        	}
	    }
		catch (Exception e){
			 logger.error("Error",e);
		}
		
		return SKIP_BODY;	
    }
	public int doEndTag(){
		return SKIP_BODY; 
	}
	public PalabraClaveVO[] getTags() {
		return tags;
	}
	public void setTags(PalabraClaveVO[] tags) {
		this.tags = tags;
	}
	
	private String inicioLetra(String letra,int indexLetra){
		String fichero="application-resources";
		Locale locale = null;
		try {
			locale = devuelveLocale();
		} catch (Exception e) {
			logger.error("Error recuperando el locale",e);
		}

		String sVerEtiquetas=this.getResource("listarNubeTags.verEtiquetas", fichero, locale);;
		String sOcultarEtiquetas=this.getResource("listarNubeTags.ocultarEtiquetas", fichero, locale);;
		String sEtiquetas=this.getResource("listarNubeTags.titulo", fichero, locale);
		
		StringBuffer bInicioLetra = new StringBuffer("");
		bInicioLetra.append("<div class=\"globo_gris uno_b\" ><div class=\"globo_gris_01\"><div class=\"globo_gris_02\"><div class=\"globo_gris_03\" style=\"padding-bottom:18px;\">")
				.append("<div class=\"caja_dinamica\"><a class=\"desplegado\" id=\"pm"+indexLetra+"\" href=\"#\" onclick=\"expandirCaja('m"+indexLetra+"', '"+sVerEtiquetas+" ', '"+sOcultarEtiquetas+"');return false;\" onkeypress=\"expandirCaja('m"+indexLetra+"', '"+sVerEtiquetas+" ', '"+sOcultarEtiquetas+"');return false;\"><br class=\"falso\" /><strong id=\"dm"+indexLetra+"\" >"+sVerEtiquetas+"</strong></a><h3 class=\"letra\" id=\"letra_"+letra+"\">"+letra+"</h3></div>")
				.append("<div id=\"m"+indexLetra+"\" class=\"caja_cerrada\" style=\"padding-top:12px !important\"   >")
				.append("<div id=\"formulario\" >")
				.append("<div class=\"caja_tabla bordeada letras_tag\" >")
				.append("<table border=\"0\"  class=\"administracion_tareas\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" summary=\""+sEtiquetas+"\">")
				.append("<caption><strong>"+sEtiquetas+"</strong></caption>");
		return bInicioLetra.toString();
	}
	
	private String finLetra(String[] alfabeto,int indexLetra){
		StringBuffer bFinLetra = new StringBuffer("");
		bFinLetra.append("</table></div></div></div></div></div></div></div>");
		if(((indexLetra+1)  % 3) == 0){
			bFinLetra.append(escribeAlfabeto(alfabeto));
		}
		return bFinLetra.toString();
	}
	
	private String escribeAlfabeto(String[] alfabeto){
		StringBuffer paginacionAlfabeto = new StringBuffer("");
		paginacionAlfabeto.append("<div class=\"paginacion\">")
						.append("<ul id=\"navlist\">");
		for(int i = 0; i < alfabeto.length; i++){
			paginacionAlfabeto.append("<li><a href=\"#letra_").append(alfabeto[i].toUpperCase()).append("\">")
							.append(alfabeto[i].toUpperCase()).append("</a></li>");
		}
		paginacionAlfabeto.append("</ul>")
						.append("</div>");
		return paginacionAlfabeto.toString();
	}
	
//	M…TODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        try{
        	ResourceBundle theResourceBundle = getResource(baseName,locale);
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        }catch (Exception e){
        	recurso = key;
        }
        return recurso;
    }
	
	public static ResourceBundle getResource(String baseName, Locale locale){
        try{
        	return ResourceBundle.getBundle(baseName,locale);
            
        }catch (MissingResourceException mre){
    		locale = new Locale("","");
    		return ResourceBundle.getBundle(baseName,locale);
        }catch (Exception e){
        	logger.error("ListadoNubeTag - Error recuperando literal para="+baseName+" ",e);
        	return null;
        }
    } 
	
	private static String removeAccents(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("ListadoNubeTag - removeAccents: Eliminar acentos para="+s);
			s = s.replaceAll( "[¬¿ƒ¡√]", "A" );
			s = s.replaceAll( "[ »À…]", "E" );
			s = s.replaceAll( "[ŒÃœÕ]", "I" );
			s = s.replaceAll( "[‘“÷”’]", "O" );
			s = s.replaceAll( "[€Ÿ‹⁄]", "U" );
			s = s.replaceAll( "[‡‚‰·]", "a" );
			s = s.replaceAll( "[ÈËÍÎ]", "e" );
			s = s.replaceAll( "[ÔÓÏÌ]", "i" );
			s = s.replaceAll( "[ÙˆÚÛı]", "o" );
			s = s.replaceAll( "[˚¸˘˙]", "u" ); 
			s = s.replaceAll( "ë", "'");
			if (logger.isDebugEnabled())logger.debug("ListadoNubeTag - removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("ListadoNubeTag - removeAccents: Error para cadena="+s, ex);
			return "";
		}
	}
	
	private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("ListadoNubeTag - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("ListadoNubeTag - Error recuperando propiedad de spring_visualizadorcontenidos.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}
	
    private Locale devuelveLocale() throws Exception
	{
		Locale locale=null;
		if (pageContext.getRequest() instanceof HttpServletRequest) {
			locale = (Locale)((HttpServletRequest)pageContext.getRequest()).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
		}
		else{ 	    					
			try {
				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			} catch (Exception e) {	
				logger.error("Error recuperando el locale de la request",e);					
			}
		}
		return locale;
	}
		
	final class RankingAlfabetico implements Comparator {

		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			PalabraClaveVO tag1 = (PalabraClaveVO) o1;
			PalabraClaveVO tag2 = (PalabraClaveVO) o2;
			Collator esCollator = Collator.getInstance();

			if(tag1.getRanking().intValue() == tag2.getRanking().intValue())
				return esCollator.compare(tag1.getPalabraClave(),tag2.getPalabraClave())<0?-1:1;
			else if(ListadoNubeTag.removeAccents(tag1.getPalabraClave()).charAt(0) == ListadoNubeTag.removeAccents(tag2.getPalabraClave()).charAt(0))
				return tag1.getRanking().intValue() < tag2.getRanking().intValue()?1:-1;		
			else
				return esCollator.compare(tag1.getPalabraClave(),tag2.getPalabraClave())<0?-1:1;
		}
		
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
