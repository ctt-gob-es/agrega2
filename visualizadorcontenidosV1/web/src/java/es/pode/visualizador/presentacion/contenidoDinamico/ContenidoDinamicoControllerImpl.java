// license-header java merge-point
package es.pode.visualizador.presentacion.contenidoDinamico;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO;

/**
 * @see es.pode.visualizador.presentacion.contenidoDinamico.ContenidoDinamicoController
 */
public class ContenidoDinamicoControllerImpl extends ContenidoDinamicoController
{
	private static final Logger logger = Logger.getLogger(ContenidoDinamicoControllerImpl.class);

	/**
     * @see es.pode.visualizador.presentacion.contenidoDinamico.ContenidoDinamicoController#obtenerContenidoDinamico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.contenidoDinamico.ObtenerContenidoDinamicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerContenidoDinamico(ActionMapping mapping, es.pode.visualizador.presentacion.contenidoDinamico.ObtenerContenidoDinamicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
		 Calendar calendario=Calendar.getInstance();
	 	 GregorianCalendar gregoriano=new GregorianCalendar(calendario.get(Calendar.YEAR),
	 			calendario.get(Calendar.MONTH),
	 			calendario.get(Calendar.DATE));
	   	 ContenidoODEVO odeDiario;//=new ContenidoODEVO();
	   	 String  url="";
	   	 String urlLocal=AgregaPropertiesImpl.getInstance().getUrlNodo();
	   	 try{
	   		 //Obtenemos el ODE diario
	   		 odeDiario = this.getSrvGeneracionDinamicaService().obtenODEDiario(gregoriano);
	   		 String origen=request.getSession().getServletContext().getInitParameter("origen_embed");
		   			
	   		 
	   		 if(odeDiario !=null){
		   		 String idioma=odeDiario.getIdioma();
		   		 String identificadorODE=odeDiario.getIdODE();
		   		 //Recogemos la URL a la que tenemos que redireccionarlo
		   		 
		   		 String urlRequest=request.getSession().getServletContext().getInitParameter("url_buscadorDetalleCorta");
		   		   
			   		 
		   		 url=urlLocal+"/"+urlRequest+"/"+idioma+"/"+identificadorODE+"/"+origen;	 
	   		 }else{
	   			 url=urlLocal+"/"+request.getSession().getServletContext().getInitParameter("url_portada");
	   			logger.error("El odeDiario era nulo, por lo que cambiamos la url por [ "+ url+" ]");
	   		 }
	   		 
		}catch(Exception e){
			logger.error("No se pudo obtener el contenido dinámico de la fecha [ "+gregoriano.toString()+" ] redireccionado a [ "+ url+" ]",e);
			url=urlLocal+"/"+request.getSession().getServletContext().getInitParameter("url_portada");
			logger.error("Cambiamos la url por [ "+ url+" ]");
			//throw new Exception("No se pudo obtener el contenido dinámico de la fecha [ "+gregoriano.toString()+" ] redireccionado a [ "+ url+" ]",e);
		}
		response.sendRedirect(url);
    }
}