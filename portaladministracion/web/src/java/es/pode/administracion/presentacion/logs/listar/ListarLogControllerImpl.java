// license-header java merge-point
package es.pode.administracion.presentacion.logs.listar;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.logs.servicio.FileVO;
import es.pode.contenidos.negocio.logs.servicio.SrvLogService;



/**
 * @see es.pode.administracion.presentacion.logs.listar.ListarLogController
 */
public class ListarLogControllerImpl extends ListarLogController
{

	private static Logger log = Logger.getLogger(ListarLogControllerImpl.class);

	static java.util.Properties pLogsProperties = null;
	final int BUFFER_SIZE = 2048;


    /**
     * @see es.pode.administracion.presentacion.logs.listar.ListarLogController#listarLogs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.logs.listar.ListarLogsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarLogs(ActionMapping mapping, es.pode.administracion.presentacion.logs.listar.ListarLogsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        	
    	try{
    		
			SrvLogService logService = this.getSrvLogService();
    		FileVO[] logs = logService.listarFicherosLog();
    		form.setLogsAsArray(logs);
    	}catch (Exception e){
        	log.error("Se ha producido un error al listar los ficheros de log: " + e);
        	throw new ValidatorException ("{errors.listarlogs}");
    	}
     }



    /**
     * @see es.pode.administracion.presentacion.logs.listar.ListarLogController#getLogs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.logs.listar.GetLogsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getLogs(ActionMapping mapping, es.pode.administracion.presentacion.logs.listar.GetLogsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	    	
    	try{
    		List lista = ((FormularioListadoLogsEliminarFormImpl)form).getNombreRowSelection();
    		if (lista == null){
    			throw new ValidatorException ("{errors.eliminarlogs.nombreNulo}");
	       }

    		// TODO el server.log no se borra
    		String no_borrar = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LOG_NO_BORRAR); //logs_no_borrar
    		String[] logsNoBorrar = no_borrar.split(",");
    		List<String> errores = new ArrayList<String>();
    		for (int i=0; i<logsNoBorrar.length; i++){
				if (lista.contains(logsNoBorrar[i])){
					errores.add(logsNoBorrar[i]);
				}
    		}
    		if (errores.size()>0){
    			log.error("no se pueden borrar los siguientes ficheros: " + errores);
				throw new ValidatorException ("{errors.eliminarlogs.noBorrar}");
    		}
	
	    	form.setIds(lista);
	    	if(log.isDebugEnabled())log.debug("Se van a eliminar los siguientes ficheros de log: " + lista);
    	} catch (ValidatorException e){
    		throw e;
    	} catch (Exception e){
    		log.error("Se ha producido un error al intentar recuperar los ids de los ficheros de log a borrar: " +e);
    	}

     }



    
	public final void recuperarFicheroLog(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.logs.listar.RecuperarFicheroLogForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception{
		try{
			String fichero = form.getNombre();
			DataHandler dataHandler = null;
			SrvLogService logService = this.getSrvLogService();
			try {
				dataHandler = logService.recuperarFicheroLog(fichero);
				
				
			}catch (Exception e){
				log.error("Error al recuperar el fichero");
			}
			if (dataHandler==null){
				log.error("El fichero recuperado está vacio");
				throw new ValidatorException("{recuperarFicheroLog.FALLO}");
			}
			if (fichero.endsWith(".log")||fichero.endsWith(".out")){
				response.setContentType("application/text");
			} else {
				response.setContentType("application/zip");
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