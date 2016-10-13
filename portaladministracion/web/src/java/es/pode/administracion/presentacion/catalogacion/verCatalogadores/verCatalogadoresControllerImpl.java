// license-header java merge-point
package es.pode.administracion.presentacion.catalogacion.verCatalogadores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO;



/**
 * @see es.pode.administracion.presentacion.catalogacion.verCatalogadores.verCatalogadoresController
 */
public class verCatalogadoresControllerImpl extends verCatalogadoresController
{


	private static Logger log = Logger.getLogger(verCatalogadoresControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.catalogacion.verCatalogadores.verCatalogadoresController#recuperarDescripcion(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.catalogacion.verCatalogadores.RecuperarDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarDescripcion(ActionMapping mapping, es.pode.administracion.presentacion.catalogacion.verCatalogadores.RecuperarDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
			
			Long id = form.getId();
			
			GrupoTrabajoVO grupoTrabajo = this.getSrvAdminUsuariosService().descripcionGrupoTrabajo(id);
			
			form.setNombre(grupoTrabajo.getNombre());
			form.setDescripcion(grupoTrabajo.getDescripcion());
			form.setId(id);
			
			
			
			
		} 
    	catch (Exception e) 
    	{
			log.error("Error: " + e);
			throw new ValidatorException("{verGrupo.error}");
		}
     }
    
   







}