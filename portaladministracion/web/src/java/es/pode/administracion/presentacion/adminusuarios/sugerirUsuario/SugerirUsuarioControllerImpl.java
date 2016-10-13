// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.sugerirUsuario;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.UsuarioVO;




/**
 * @see es.pode.administracion.presentacion.adminusuarios.sugerirUsuario.SugerirUsuarioController
 */
public class SugerirUsuarioControllerImpl extends SugerirUsuarioController
{


	private static Logger logger = Logger.getLogger(SugerirUsuarioControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.adminusuarios.sugerirUsuario.SugerirUsuarioController#obtenerSugerencias(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.sugerirUsuario.ObtenerSugerenciasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerSugerencias(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.sugerirUsuario.ObtenerSugerenciasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String busqueda = form.getQ();
        logger.info("SugerirUsuarioControllerImpl - obtenerSugerencias - Vamos a obtener sugerencias de búsqueda que comiencen por " +busqueda );
       // if(busqueda!=null && !busqueda.equals("")){
	       

	        PrintWriter writer = response.getWriter();
	        String respuesta="";
	        
	        try{
	        	UsuarioVO[] sugerencias = this.getSrvAdminUsuariosService().obtenerUsuariosActivosPorNombre(busqueda.trim());
	        	logger.debug("sugerencias de usuarios "+sugerencias.length);
	        	if(sugerencias != null  && sugerencias.length>0){
	        		String inicio ="<ul>";
	        		StringBuffer sug = new StringBuffer();	
			        for(int i=0;i<sugerencias.length;i++){
			        	String sugerencia = sugerencias[i].getUsuario();
			        	//String resto = sugerencia.substring(busqueda.length(), sugerencia.length());
			        	sug.append(sugerencia  + "|" + sugerencia  + "\n");
			        }
			        
			       // String fin ="</ul>";
			        //respuesta = inicio+sug+fin;
			        writer.append(sug.toString());
	        	}
	        }catch(Exception e){
	        	logger.error("SugerirUsuarioControllerImpl - Error obteniendo sugerencias de usuario ",e);
	        }
     //   }
    	
    	
//        // this property receives a default value, just to have the application running on dummy data
//        form.setIdiomaBusc("idiomaBusc-test");
//         // this property receives a default value, just to have the application running on dummy data
//        form.setBusquedaGeneral("busquedaGeneral-test");
     }
    









}