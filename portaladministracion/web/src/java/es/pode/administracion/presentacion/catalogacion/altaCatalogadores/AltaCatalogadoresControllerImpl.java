// license-header java merge-point
package es.pode.administracion.presentacion.catalogacion.altaCatalogadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;


/**
 * @see es.pode.administracion.presentacion.catalogacion.altaCatalogadores.AltaCatalogadoresController
 */
public class AltaCatalogadoresControllerImpl extends AltaCatalogadoresController
{


	private static Logger log = Logger.getLogger(AltaCatalogadoresControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.catalogacion.altaCatalogadores.AltaCatalogadoresController#altaCatalogadores(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.catalogacion.altaCatalogadores.AltaCatalogadoresForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void altaCatalogadores(ActionMapping mapping, es.pode.administracion.presentacion.catalogacion.altaCatalogadores.AltaCatalogadoresForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
	    	String descripcion = form.getDescripcion();
	    	String nombre = form.getNombre();
	    	
	    	GrupoTrabajoVO grupoTrabajo = new GrupoTrabajoVO();
	    	SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
	    	
	    	Pattern mask = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
	        Matcher matcher = null;
	    	
	    	if ((nombre == null) || (nombre.length() == 0)) 
	    	{
	    	    if(log.isDebugEnabled())log.debug("el nombre introducido es nulo");
	    	    throw new ValidatorException("{errors.altagrupo.descripcion}");
	    		
	    	}
	    	else 
	    	{	
	    		matcher = mask.matcher(nombre);
	    	    if (!matcher.matches()) {
	    	    	if(log.isDebugEnabled())log.debug("nombre caracter ilegal");
	    	    	throw new ValidatorException("{errors.altagrupo.descripcion.caracterIlegal}");
	    	    }
	    	    
	    	    matcher = mask.matcher(descripcion);
	    	    if (!matcher.matches()) {
	    	    	if(log.isDebugEnabled())log.debug("descripcion caracter ilegal");
	    	    	throw new ValidatorException("{errors.altaGrupoTrabajo.caracterIlegal.descripcion}");
	    	    }
	    		
	    	    if ((srvAdminUsuariosService.existeNombreTrabajo(nombre, Long.valueOf("-1"))).booleanValue()) 
	    	    {
	    	    	if(log.isDebugEnabled())log.debug("ya existe la descripcion");
	    	    	throw new ValidatorException("{errors.altagrupo.descripcionExistente}");
	    	    } 
	    	    else 
	    	    {
	    	    	grupoTrabajo.setNombre(nombre);
	    	    	grupoTrabajo.setDescripcion(descripcion);
	    	    	
	    	    	Long identificador = srvAdminUsuariosService.altaGrupoTrabajo(grupoTrabajo);
	    			form.setIdModificado(identificador);
	    	    }
	    	}
    	}
    	catch (ValidatorException e) 
    	{
    		throw e;
    	} 
    	catch (Exception e) 
    	{
    		log.error("Error: " + e);
    		throw new ValidatorException("{errors.altagrupo}");
    	}
    	
     }

  


}




