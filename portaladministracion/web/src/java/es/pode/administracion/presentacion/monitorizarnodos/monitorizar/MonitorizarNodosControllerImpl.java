// license-header java merge-point
package es.pode.administracion.presentacion.monitorizarnodos.monitorizar;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.monitorizar.servicio.EstadoNodoVO;
import es.pode.buscar.negocio.monitorizar.servicio.SrvMonitorizarService;



/**
 * @see es.pode.administracion.presentacion.monitorizarnodos.monitorizar.MonitorizarNodosController
 */
public class MonitorizarNodosControllerImpl extends MonitorizarNodosController
{



	private static Logger log = Logger.getLogger(MonitorizarNodosControllerImpl.class);
	ResourceBundle ficheroRecursos = null;


    /**
     * @see es.pode.administracion.presentacion.monitorizarnodos.monitorizar.MonitorizarNodosController#estadoNodos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.monitorizarnodos.monitorizar.EstadoNodosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void estadoNodos(ActionMapping mapping, es.pode.administracion.presentacion.monitorizarnodos.monitorizar.EstadoNodosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

    	
    	
    	try{
    	
	    	
			SrvMonitorizarService service =  this.getSrvMonitorizarService();
        	EstadoNodoVO[] estados = service.obtenerEstadoNodos();
        	prepararInformacion(estados, request.getLocale());
        	form.setEstadosAsArray(estados);
        } catch (Exception e){
        	log.error("Se ha producido un error al monitorizar los nodos: " + e);
        	throw new ValidatorException ("{errors.monitorizarnodos}");
        }

    	
     }

    
    //traduce la descripción del nombre del servicio
    private void prepararInformacion(EstadoNodoVO[] estadosVO, Locale locale){
    	String descripcion = null;
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
    	for (int i=0; i<estadosVO.length;i++ ){
    		//intentamos traducir el nombre del servicio, si no se puede dejamos el de la BBDD
			try
			{
				descripcion = ficheroRecursos.getString(estadosVO[i].getNombreServicio());
			}
			catch(Exception ex)
			{
				log.warn("El nombre del servicio " + estadosVO[i].getNombreServicio() + " no esta en el fichero de internacionalizacion");
				descripcion = estadosVO[i].getDescripcionServicio();
			}
			estadosVO[i].setDescripcionServicio(descripcion);
    	}
    }
    

}