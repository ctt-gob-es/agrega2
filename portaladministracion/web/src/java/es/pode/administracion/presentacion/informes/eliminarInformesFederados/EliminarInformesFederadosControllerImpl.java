// license-header java merge-point
package es.pode.administracion.presentacion.informes.eliminarInformesFederados;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.contenidos.negocio.informes.servicio.SrvInformeService;
import es.pode.contenidos.negocio.informes.servicio.ValidaBajaInformeVO;



/**
 * @see es.pode.administracion.presentacion.informes.eliminarInformesFederados.EliminarInformesFederadosController
 */
public class EliminarInformesFederadosControllerImpl extends EliminarInformesFederadosController
{

	private static Logger logger = Logger.getLogger(EliminarInformesFederadosControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.informes.eliminarInformesFederados.EliminarInformesFederadosController#obtenerInformeFederado(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.eliminarInformesFederados.ObtenerInformeFederadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerInformeFederado(ActionMapping mapping, es.pode.administracion.presentacion.informes.eliminarInformesFederados.ObtenerInformeFederadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		
    		
    		Iterator iter = (form.getIds()).iterator();
    		
    	    form.setListaId(Utils.ids2String(iter, ":").trim());
    	    form.setInformes((String[])form.getIds().toArray(new String[0]));
    		
    	} catch (Exception e) {
    	    logger.error("Se ha producido un error al recuperar los informes federados: " + e);
    	    throw new ValidatorException("{errors.obtenerInformeFederado}");
    	}
     }







    /**
     * @see es.pode.administracion.presentacion.informes.eliminarInformesFederados.EliminarInformesFederadosController#eliminarInformeFederado(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.eliminarInformesFederados.EliminarInformeFederadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarInformeFederado(ActionMapping mapping, es.pode.administracion.presentacion.informes.eliminarInformesFederados.EliminarInformeFederadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
try{
    		
    		String listaId = request.getParameter("listaId");
    		
    		if(logger.isDebugEnabled()) logger.debug("los ids de los informes federados que se quieren eliminar son " + listaId);
    		String[] informes = listaId.split(":");

    		if(logger.isDebugEnabled()) logger.debug("los ids de los informes federados que se quieren eliminar son " + listaId);
    		SrvInformeService informeService = this.getSrvInformeService();
    		ValidaBajaInformeVO bajaInformeVO = informeService.eliminarInformeFederado(informes);
    		form.setInformesBorrados(bajaInformeVO.getInformesBorrados());
    		form.setResultado(bajaInformeVO.getDescripcionBaja());
    		
    	}catch (Exception e){
    		logger.error("Se ha producido un error al eliminar el informe federado: " + e);
    		throw new ValidatorException("{errors.eliminarInformeFederado}");
    	}
     }









}