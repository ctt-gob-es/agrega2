// license-header java merge-point
package es.pode.administracion.presentacion.informes.listarInformesFederados;

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
 * @see es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosController
 */
public class ListarInformesFederadosControllerImpl extends ListarInformesFederadosController
{

	private static Logger logger = Logger.getLogger(ListarInformesFederadosControllerImpl.class);

	final int BUFFER_SIZE = 10000;

    /**
     * @see es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosController#listarInformesFederados(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarInformesFederados(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
    	try{
    		SrvInformeService srvInformeService = this.getSrvInformeService();
    		InformeVO[] informes = srvInformeService.listarInformesFederados();
    		form.setInformesAsArray(cambiarFechaInforme(informes));
    	}catch (Exception e){
        	logger.error("Se ha producido un error al listar los informes federados: " + e);
        	throw new ValidatorException ("{errors.listarInformesFederados}");
    	}
     }

    /**
     * @see es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosController#recuperarInformeFederado(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformesFederados.RecuperarInformeFederadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarInformeFederado(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformesFederados.RecuperarInformeFederadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			String fichero = form.getNombre();
			DataHandler dataHandler = null;
			SrvInformeService informeService = this.getSrvInformeService();
			try {
				dataHandler = informeService.recuperarInformesFederados(fichero);
				
				
			}catch (Exception e){
				logger.error("Error al recuperar el fichero");
			}
			if (dataHandler==null){
				logger.error("El fichero recuperado está vacio");
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
			if(logger.isDebugEnabled())logger.debug("recuperando el fichero federado" + fichero);
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
			logger.error("Se ha producido el siguiente error: " +e);
			throw e;
		}
     }

    /**
     * @see es.pode.administracion.presentacion.informes.listarInformesFederados.ListarInformesFederadosController#getInformesFederados(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.listarInformesFederados.GetInformesFederadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getInformesFederados(ActionMapping mapping, es.pode.administracion.presentacion.informes.listarInformesFederados.GetInformesFederadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		List lista = ((FormularioListarInformesFederadosEliminarFormImpl)form).getNombreRowSelection();
    		if (lista == null){
    			throw new ValidatorException ("{errors.eliminarInformes.nombreNuloFederado}");
	       }
	    	form.setIds(lista);
	    	if(logger.isDebugEnabled())logger.debug("Se van a eliminar los siguientes informes federados: " + lista); 
    	} catch (Exception e){
    		logger.error("Se ha producido un error al intentar recuperar los ids de los informes federados a borrar: " +e);
    		throw e;
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

}