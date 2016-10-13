// license-header java merge-point
package es.pode.tagging.presentacion.adminTag.mostrarOdes;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.url.ODEPublico;
import es.pode.tagging.negocio.servicios.TaggingVO;
import es.pode.tagging.presentacion.TaggingSession;
import es.pode.tagging.presentacion.UrlOde;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.adminTag.mostrarOdes.MostrarOdesController
 */
public class MostrarOdesControllerImpl extends MostrarOdesController
{


	protected static Logger logger = Logger.getLogger(MostrarOdesControllerImpl.class); 



    /**
     * @see es.pode.tagging.presentacion.adminTag.mostrarOdes.MostrarOdesController#obtenerOdes(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.adminTag.mostrarOdes.ObtenerOdesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOdes(ActionMapping mapping, es.pode.tagging.presentacion.adminTag.mostrarOdes.ObtenerOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	Collection<UrlOde> listaUrlFicha= new ArrayList<UrlOde>();
    	
    	TaggingSession taggingSession =  this.getTaggingSession(request);
        String tag = form.getTag();
        
        TaggingVO[] odes =null;
		if(tag !=null && !tag.equals(""))
		{
			taggingSession.setTagPaginacion(tag);
		}
		if(taggingSession.getTagPaginacion()!=null && !taggingSession.getTagPaginacion().equals(""))
		{
			logger.info("Obteniendo ODES con TAG:" + taggingSession.getTagPaginacion());
			odes = this.getSrvTaggingServer().obtenerOdesDeTag(taggingSession.getTagPaginacion());
			
			for (int i = 0; i < odes.length; i++) {
				UrlOde ode = new UrlOde();
				ode.setUrl(ODEPublico.urlFichaODEPublicado(odes[i].getIdOde(), odes[i].getIdiomaCat(), odes[i].getNodo()));
				ode.setTitulo(odes[i].getTitulo());
				listaUrlFicha.add( ode ) ;
			}
			
			//cuando paginamos cogemos el tag de sesion
			if(tag == null || tag.equals(""))
				form.setTag(taggingSession.getTagPaginacion());
		}
        form.setOdes(listaUrlFicha);
     }









}