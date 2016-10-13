// license-header java merge-point
package es.pode.administracion.presentacion.repositoriosExternos.listarRepositoriosExternos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.nodosSQI.servicio.NodoDescripcionSQIVO;
import es.pode.buscar.negocio.nodosSQI.servicio.SrvGestionSqiService;



/**
 * @see es.pode.administracion.presentacion.repositoriosExternos.listarNodosSQI.ListarNodosSQIController
 */
public class ListarNodosSQIControllerImpl extends ListarNodosSQIController
{




	private static Logger logger = Logger.getLogger(ListarNodosSQIControllerImpl.class);

	public void getIds(ActionMapping mapping, GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
	    	List lista = ((FormularioNodosSQIEliminarNodoSQIFormImpl) form).getIdNodoRowSelection();
	    	if(logger.isDebugEnabled())
	    		logger.debug("Los nodos que se van a eliminar son ["+lista);
	       if (lista == null){
	    	   throw new ValidatorException ("{errors.bajanodosSQI.idNulo}");
	       }
	       form.setIds(lista);
	       logger.info("Se van a eliminar los siguientes nodosSQI: " + lista);
	       
       } catch (ValidatorException e){
    	   throw e;
	    	   
       } catch (Exception e){
    	   
    	   logger.error("Se ha producido un error al intentar recuperar los ids para borrar: " +e);
    	   throw new ValidatorException ("{errors.eliminarnodos.idError}");
    	   
       }
		
	}


	public void listarNodosSQI(ActionMapping mapping, ListarNodosSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
        	
	    	SrvGestionSqiService service =  this.getSrvGestionSqiService();
        	NodoDescripcionSQIVO[] nodos = service.listarNodosSQI();
        	form.setNodosAsArray(nodos);
        } catch (Exception e){
        	logger.error("Se ha producido un error al listar los nodosSQI: " + e);
        	throw new ValidatorException ("{errors.listarnodoSQI} ");
        }
		
	}




}