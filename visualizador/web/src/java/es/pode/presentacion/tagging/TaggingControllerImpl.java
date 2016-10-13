// license-header java merge-point
package es.pode.presentacion.tagging;

import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.presentacion.VisualizadorSession;
import es.pode.tagging.negocio.servicios.SrvTaggingServer;
import es.pode.tagging.negocio.servicios.SrvTaggingServerServiceLocator;



/**
 * @see es.pode.presentacion.tagging.TaggingController
 */
public class TaggingControllerImpl extends TaggingController
{
	private static Logger logger = Logger.getLogger(TaggingControllerImpl.class);


    public final void asociarTags(
    		ActionMapping mapping, 
    		AsociarTagsForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    		VisualizadorSession sesion= this.getVisualizadorSession(request);
    		
	    	String idOde= form.getIdOde(); 
	    	String idUsuario= form.getIdUsuario();
	    	String idiomaCat= form.getIdiomaCat();
	    	String titulo=  form.getTitulo();
	    	
	    	String cadenaTags= form.getTags();
	    	String[] tagsSeparados= cadenaTags.split(" ");
	    	ArrayList<String> tagsSinEspacios = new ArrayList<String>();
	    	for(int i = 0; i< tagsSeparados.length; i++){
	    		String tag =  tagsSeparados[i].trim();
	    		if (!tag.equals("")){
	    			tagsSinEspacios.add(tag);
	    		}	
	    	}
	    	
	        tagsSeparados = tagsSinEspacios.toArray(new String[tagsSinEspacios.size()]);
        	String nodoLocal= AgregaPropertiesImpl.getInstance().getUrlNodo();
	        if(sesion.isIdentidadFederada())
	        {

	        	URL url= new URL(sesion.getNodoOrigen()+ "/valoracion-1/services/SrvTaggingServer");
	        	SrvTaggingServer server= new SrvTaggingServerServiceLocator().getSrvTaggingServer(url);
	        	server.introducirTags(idOde, titulo, idiomaCat, tagsSeparados, sesion.getUsuarioOrigen(), nodoLocal);
	        	logger.debug("Tag enviado al nodo: " + sesion.getNodoOrigen() );
	        	
	        	URL urlServer= new URL(sesion.getNodoOrigen());
	        	idUsuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
	        }

	        
        	this.getSrvTaggingServer().introducirTags(idOde, titulo, idiomaCat, tagsSeparados, idUsuario ,"");
        	logger.debug("Tag enviado al nodo: " + nodoLocal );
     }

}