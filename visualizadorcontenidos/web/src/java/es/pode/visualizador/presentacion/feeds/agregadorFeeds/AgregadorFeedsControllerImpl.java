// license-header java merge-point
package es.pode.visualizador.presentacion.feeds.agregadorFeeds;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.agregador.negocio.agregador.servicio.FeedVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.feeds.agregadorFeeds.AgregadorFeedsController
 */
public class AgregadorFeedsControllerImpl extends AgregadorFeedsController
{


	private static Logger log = Logger.getLogger(AgregadorFeedsControllerImpl.class);

	protected static final String FILE_NAME_PROPERTIES = "/spring_visualizadorcontenidos2.properties";
	private static final String KEY_MENU_LATERAL_OPML_RSS = "ruta_fichero_opml_rss";
	private static final String KEY_MENU_LATERAL_OPML_ATOM = "ruta_fichero_opml_atom";
	
	private java.util.Properties pSpringProperties = null;

    /**
     * @see es.pode.visualizador.presentacion.feeds.agregadorFeeds.AgregadorFeedsController#obtenerFeeds(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.feeds.agregadorFeeds.ObtenerFeedsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerFeeds(ActionMapping mapping, es.pode.visualizador.presentacion.feeds.agregadorFeeds.ObtenerFeedsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

    	try{
	    	FeedVO[] feeds = this.getSrvAgregadorRssService().devuelveFeeds();
	    	if (log.isDebugEnabled())log.debug("AgregadorFeedsControllerImpl - Se han recuperado "+ feeds.length + " feeds");
	    	 String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    	form.setFeeds(feeds);
	    	form.setIdiomaNavegacion(idioma);
	    
	    	
	    } catch (Exception e){
	    	log.error("AgregadorFeedsControllerImpl - Se ha producido un error al recuperar los feeds: " + e);
	    	throw new ValidatorException ("{errors.obtenerFeeds}");
	    }
	    
	    try{
			// Ficheros OPML
	        String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	        // Fichero OPML de RSS
	        String fichOpmlRss = (getPropertyValue(KEY_MENU_LATERAL_OPML_RSS));
	        String urlFichOpmlRss="http://" + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio() + fichOpmlRss + idioma + ".opml";
	        form.setUrlOpmlRss(urlFichOpmlRss);
	        //			context.putAttribute("FICH_OPML_RRS",urlFichOpmlRss);
			log.info("AgregadorFeedsControllerImpl - Fichero OPML de RSS " + urlFichOpmlRss + " idiomaNavegacion: "+ idioma);
	        // Fichero OPML de  ATOM
	        String fichOpmlAtom = (getPropertyValue(KEY_MENU_LATERAL_OPML_ATOM));
	        String urlFichOpmlAtom="http://" + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio() + fichOpmlAtom + idioma + ".opml";
//			context.putAttribute("FICH_OPML_ATOM",urlFichOpmlAtom);
	        form.setUrlOpmlAtom(urlFichOpmlAtom);
	        form.setHost("http://"+LdapUserDetailsUtils.getHost());
			log.info("AgregadorFeedsControllerImpl - Fichero OPML de ATOM " + urlFichOpmlAtom + " idiomaNavegacion: "+ idioma);
		}catch(Exception e){
			log.error("AgregadorFeedsControllerImpl - Error al obtener la URL de los ficheros OPML");
			form.setUrlOpmlAtom("");
			form.setUrlOpmlRss("");
		}
    	
    }




	public void queEsEsto(ActionMapping mapping, QueEsEstoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(FILE_NAME_PROPERTIES);
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			log.debug("ListadoNubeTag - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		log.error("ListadoNubeTag - Error recuperando propiedad de spring_visualizadorcontenidos2.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}
		
}