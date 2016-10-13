// license-header java merge-point
package es.pode.buscador.presentacion.basico.sugerirBusqueda;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.basico.sugerirBusqueda.SugerirBusquedaController
 */
public class SugerirBusquedaControllerImpl extends SugerirBusquedaController
{

	private static Logger logger = Logger.getLogger(SugerirBusquedaControllerImpl.class);




    /**
     * @see es.pode.buscador.presentacion.basico.sugerirBusqueda.SugerirBusquedaController#obtenerSugerencias(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.sugerirBusqueda.ObtenerSugerenciasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
	public final void obtenerSugerencias(ActionMapping mapping, es.pode.buscador.presentacion.basico.sugerirBusqueda.ObtenerSugerenciasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Entrando en obtenerSugerencias");
        String busqueda = form.getBusquedaGeneral();
        
        // TODO Codigo provisional para probar autocompletado
        if(busqueda==null || "".equals(busqueda)) {
        	logger.debug("busqueda=null -> compruebo parametro q");
        	// Busco en la request el parametro q proporcionado por jquery
        	String jQuerySearch = request.getParameter("q");
        	if(jQuerySearch!=null && jQuerySearch.length()>0) busqueda=jQuerySearch; 
        }
        
        logger.info("SugerirBusquedaControllerImpl - obtenerSugerencias - Vamos a obtener sugerencias de búsqueda que comiencen por " +busqueda +"idiomaBusc="+ form.getIdiomaBusc());
        if(busqueda!=null && !busqueda.equals("")){
	        String idiomaBusqueda = form.getIdiomaBusc();
	        ParametrosBusquedaAvanzadaVO30 parametros = new ParametrosBusquedaAvanzadaVO30();
	        
	        idiomaBusqueda= this.devolverIdiomaBuscador(idiomaBusqueda);
	        
	        parametros.setIdiomaBusqueda(idiomaBusqueda);
	        parametros.setIdiomaNavegacion("");
	        parametros.setNumeroResultados(10);
	        parametros.setPalabrasClave(busqueda.trim());


	        PrintWriter writer = response.getWriter();
	        String respuesta="";
	        
	        try{
	        	/*
	        	 * Formato esperado por el plugin de jQuery.autocomplete:
	        	 * 
	        	 * sugerencia1|sugerencia1\n
	        	 * sugerencia2|sugerencia2\n
	        	 * ...
	        	 * sugerenciaN|sugerenciaN
	        	 */
	        	logger.debug("Pidiendo sugerencias");
	        	SugerenciasBusquedaVO[] sugerencias = this.getSrvBuscarService().sugerirBusqueda(parametros);
	        	logger.debug("Sugerencias devueltas: " + (sugerencias==null?"null":sugerencias.length));
	        	if(sugerencias != null  && sugerencias.length>0){
	        		
	        		StringBuffer sug = new StringBuffer();	
			        for(int i=0;i<sugerencias.length;i++){
			        	sug.append(sugerencias[i].getSugerencia()).append("|").append(sugerencias[i].getSugerencia());
			        	if(i<(sugerencias.length-1)) sug.append("\n");
			        }
			        
			        
			        respuesta = sug.toString();
			        if(logger.isDebugEnabled()) logger.debug(respuesta);
			        writer.append(respuesta);
	        	}
	        }catch(Exception e){
	        	logger.error("SugerirBusquedaControllerImpl - Error obteniendo sugerencias de busqueda para ["+busqueda+"] con idioma de busqueda ["+idiomaBusqueda+"]",e);
	        }
	        
        }
    }


    private String devolverIdiomaBuscador(String idioma) throws Exception{
		String idiomaBuscador = "";
		if ((idioma != null) && !idioma.equals("") && !idioma.equals("#")) return idioma;
		else if(LdapUserDetailsUtils.estaAutenticado()){
				try{
					idiomaBuscador=LdapUserDetailsUtils.getIdiomaBusqueda();
					if (idiomaBuscador == null) idiomaBuscador = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				} catch (Exception e) {
					logger.error("SugerirBusquedaControllerImpl - devolverIdiomaBuscador: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
					idiomaBuscador = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				}
	   	}else idiomaBuscador = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		return idiomaBuscador;
	}


}