// license-header java merge-point
package es.pode.administracion.presentacion.catalogacion.listarCatalogadores;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;



/**
 * @see es.pode.administracion.presentacion.catalogacion.listarCatalogadores.listarCatalogadoresController
 */
public class listarCatalogadoresControllerImpl extends listarCatalogadoresController
{



	private static Logger log = Logger.getLogger(listarCatalogadoresControllerImpl.class);


    /**
     * @see es.pode.administracion.presentacion.catalogacion.listarCatalogadores.listarCatalogadoresController#recuperarCatalogadores(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.RecuperarCatalogadoresForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarCatalogadores(ActionMapping mapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.RecuperarCatalogadoresForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
    	try {
    		
  		   
		    GrupoTrabajoVO[] grupoTrabajoVO = this.getSrvAdminUsuariosService().listarGruposTrabajo();
		    form.setGruposTrabajoAsArray(grupoTrabajoVO);
		    
		} 
    	catch (Exception e) 
    	{
		    log.error("Error: " + e);
		    throw new ValidatorException("{listarGrupos.error}");
		}
     }



    /**
     * @see es.pode.administracion.presentacion.catalogacion.listarCatalogadores.listarCatalogadoresController#obtenerIdentificadores(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.ObtenerIdentificadoresForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerIdentificadores(ActionMapping mapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.ObtenerIdentificadoresForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	List lista = ((FormularioListadoCatalogadoresEliminarCatalogadoresFormImpl) form).getIdentificadorRowSelection();
    	
		String resultado = "vacio";
		try 
		{
		    if (lista == null) 
		    {
		    	throw new ValidatorException("{errors.borrarGrupo.idNulo}");
		    } 
		    else 
		    {
		    	// Validamos los grupos seleccionados para comprobar si tienen usuarios asociados a ellos
		    	Long[] ids = new Long[lista.size()];
		    	Iterator iter = lista.iterator();
		    	SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		    	int i = 0;
		    	
		    	while (iter.hasNext()) 
		    	{
	
				    Long id = new Long((String) iter.next());
				    GrupoTrabajoVO grupoTrabajoVO = srvAdminUsuariosService.descripcionGrupoTrabajo(id);
		
				    if ((srvAdminUsuariosService.obtenerUsuariosGrupoTrabajo(id)).booleanValue()) 
				    {
				    	log.error("el grupo tiene un usuario " + grupoTrabajoVO.getDescripcion());
				    	throw new ValidatorException("{errors.borrarGrupo.GrupoConUsuarios}");
				    }
				    else 
				    {
				    	ids[i] = id;
				    	i++;
				    }
		    	}
	
				
				form.setIds(lista);
				resultado = "identificadores";
				
		    }
		} 
		catch (ValidatorException validator) 
		{
		    log.error("Se ha producido la siguiente exception " + validator);
		    throw validator;
		} 
		catch (Exception e) 
		{
		    log.error("Se produce una excepcion ", e);
		    throw new ValidatorException("{errors.borrarGrupo.idNulo}");
		}
		return resultado;
    }







    /**
     * @see es.pode.administracion.presentacion.catalogacion.listarCatalogadores.listarCatalogadoresController#submit(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.administracion.presentacion.catalogacion.listarCatalogadores.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	form.setIds(form.getIds());
     }



}