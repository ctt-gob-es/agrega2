// license-header java merge-point
package es.pode.administracion.presentacion.informes.listarInformes;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.InformeDateVO;
import es.pode.contenidos.negocio.informes.servicio.InformeVO;
import es.pode.contenidos.negocio.informes.servicio.SrvInformeService;



/**
 * @see es.pode.administracion.presentacion.informes.listarInformes.ListarInformesController
 */
public class ListarInformesControllerImpl extends ListarInformesController
{


	private static Logger log = Logger.getLogger(ListarInformesControllerImpl.class);
	//final int BUFFER_SIZE = 2048;
	final int BUFFER_SIZE = 10000;



    /**
     * @see es.pode.administracion.presentacion.informes.listarInformes.ListarInformesController#listarInformes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformes.ListarInformesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarInformes(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformes.ListarInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	try{
    		SrvInformeService srvInformeService = this.getSrvInformeService();
    		InformeVO[] informes = srvInformeService.listarInformes();
    		form.setInformesAsArray(cambiarFechaInforme(informes));
    	}catch (Exception e){
        	log.error("Se ha producido un error al listar los informes: " + e);
        	throw new ValidatorException ("{errors.listarInformes}");
    	}
    	
    	
     }
    
    private InformeDateVO[] cambiarFechaInforme (InformeVO[] lista){
    	InformeDateVO[] array=null;
    	if(lista!=null && lista.length>0){
	    	array = new InformeDateVO[lista.length];
	    	for (int i=0; i< lista.length; i++){
	    		array[i] = new InformeDateVO(); 
	    		array[i].setFecha(lista[i].getFecha().getTime());
	    		array[i].setNombre(lista[i].getNombre());
	    	}
    	}else{
    		array = new InformeDateVO[0];
    	}
    	return array;
    }

    /**
     * @see es.pode.administracion.presentacion.informes.listarInformes.ListarInformesController#getInformes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformes.GetInformesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getInformes(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformes.GetInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		List lista = ((FormularioListarInformesEliminarFormImpl)form).getNombreRowSelection();
    		if (lista == null){
    			throw new ValidatorException ("{errors.eliminarInformes.nombreNulo}");
	       }
	    	form.setIds(lista);
	    	if(log.isDebugEnabled())log.debug("Se van a eliminar los siguientes informes: " + lista); 
    	} catch (Exception e){
    		log.error("Se ha producido un error al intentar recuperar los ids de los informes a borrar: " +e);
    		throw e;
    	}

     }

    /**
     * @see es.pode.administracion.presentacion.informes.listarInformes.ListarInformesController#recuperarInforme(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformes.RecuperarInformeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarInforme(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformes.RecuperarInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		try{
			String fichero = form.getNombre();
			DataHandler dataHandler = null;
			SrvInformeService informeService = this.getSrvInformeService();
			try {
				dataHandler = informeService.recuperarInforme(fichero);	
				
			}catch (Exception e){
				log.error("Error al recuperar el fichero");
			}
			if (dataHandler==null){
				log.error("El fichero recuperado está vacio");
				throw new ValidatorException("{recuperarInforme.FALLO}");
			}
			if (fichero.endsWith(".pdf")){
				response.setContentType("application/pdf");
			} else if (fichero.endsWith(".html") || fichero.endsWith(".htm")) {
				response.setContentType("application/html");
			} else if (fichero.endsWith(".xls")) {
				response.setContentType("application/xls");
			} else if (fichero.endsWith(".doc")) {
				response.setContentType("application/doc");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+fichero);
			OutputStream out = response.getOutputStream();
			InputStream in = dataHandler.getInputStream();
			if(log.isDebugEnabled())log.debug("recuperando el fichero " + fichero);
			byte[] buffer = new byte[BUFFER_SIZE];
			int count;
			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
			{
				out.write(buffer, 0, count);
			}
			
			out.flush();
			out.close();
			
		} catch (ValidatorException e){
			throw e;
		} catch (Exception e){
			log.error("Se ha producido el siguiente error: " +e);
			throw e;
		}

     }
}