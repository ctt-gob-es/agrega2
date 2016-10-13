package es.pode.visualizador.presentacion.informes;


import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.informes.servicio.InformeVO;
import es.pode.contenidos.negocio.informes.servicio.SrvInformeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.visualizador.presentacion.informes.ListarInformesController
 */
public class ListarInformesControllerImpl extends ListarInformesController
{


	private static Logger log = Logger.getLogger(ListarInformesControllerImpl.class);
	final int BUFFER_SIZE = 10000;

 
    /**
     * Lista en la portada los informes 'Mas' generados mediante una tarea programada
     */
    public final void listarInformes(ActionMapping mapping, es.pode.visualizador.presentacion.informes.ListarInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	log.debug("ListarInformes");
    	try{
    		SrvInformeService srvInformeService = this.getSrvInformeService();
    		log.debug("srvInformeService "+srvInformeService);
    		InformeVO[] informes = srvInformeService.listarInformesMas();
    		if(informes == null)
    		{
    			log.debug("No hay informes");
    		}else
    		{
    			log.debug("Listado de informes Mas "+informes.length);
    		}
    		form.setPathInformes(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_PORTADA));
    		form.setInformesAsArray(informes);
    	}catch (Exception e){
        	log.error("Se ha producido un error al listar los informes: " + e);
        	throw new ValidatorException ("{errors.listarInformes}");
    	}
     }

   

}