// license-header java merge-point
package es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.AsociarOdeAGrupoController
 */
public class AsociarOdeAGrupoControllerImpl extends AsociarOdeAGrupoController
{



	private static Logger logger = Logger.getLogger(AsociarOdeAGrupoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.AsociarOdeAGrupoController#asociarOdeAGrupos(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.AsociarOdeAGruposForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void asociarOdeAGrupos(ActionMapping mapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.AsociarOdeAGruposForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String valorVuelta=form.getVuelta();//5
    	String valorNombre=form.getNombre();
    	form.setVuelta(valorVuelta);
    	form.setNombre(valorNombre);
    	try{
    		if(form.getListaId()!=null && !form.getListaId().equals("")){
    			String[]listIDsStrings=form.getListaId().split(",");
    			if(logger.isDebugEnabled())
    				logger.debug("La lista de identificadores:"+form.getListaId());
				Long[] listIDs =new Long[listIDsStrings.length];
				for(int i=0; i<listIDsStrings.length;i++)
				{
					listIDs[i]=Long.valueOf(listIDsStrings[i]);
				}

	    		 SrvPerfilPublico perfilService = this.getSrvPerfilPublico();
	    		GrupoPublicoVO[] grupos = perfilService.obtenerGruposPublicosPorIdentificador(listIDs);
	    		form.setGruposAsociarAsArray(grupos);
	    		String[] nombres=new String[grupos.length];
	    		for(int i=0;i<grupos.length;i++){
	    			nombres[i]=grupos[i].getNombre();
	    		}
	    		String id_mec=form.getId_mec();
	    		String titulo=form.getTitulo();
	    		String idioma=form.getIdioma();
	    		OdeGrupoVO ode=new OdeGrupoVO();
	    		ode.setId_mec(id_mec);
	    		ode.setTitulo(titulo);
	    		ode.setIdioma(idioma);
	    		ResultadoOperacionVO[] resultado=perfilService.asociarOdeAGrupo(ode, nombres);

	    		
    		}else{
    			throw new ValidatorException("{errors.asociarOde.idNulo}");
    		}
    		 		
    	}catch (Exception e){
    		logger.error("Se ha producido un error al asociar el ode: " + e);
    		throw new ValidatorException("{errors.asociarOde}");
    	}
       
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.AsociarOdeAGrupoController#obtenerGrupos(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.ObtenerGruposForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGrupos(ActionMapping mapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarOdeAGrupo.ObtenerGruposForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		String id_mec=form.getId_mec();
    		logger.info("Tenemos el identificador del ode "+id_mec);
			String titulo=form.getTitulo();
			String valorVuelta=form.getVuelta();//4
			String valorNombre=form.getNombre();
			logger.info("Tenemos el titulo del ode "+titulo);
		   if(form.getIdsAsArray()!=null && form.getIdsAsArray().length>0){
			   Object[] arrayIds= form.getIdsAsArray();
			   if(logger.isDebugEnabled())
				   logger.debug("los nombres de los grupos que se quieren asociar al ode son " + arrayIds);
	    		Long[] ident=new Long[arrayIds.length];
	    		String lista="";
	    		for(int i=0;i<arrayIds.length;i++){
	    			ident[i]= new Long((String) arrayIds[i]);
	    			lista=lista+ ident[i].toString() + ",";
	    		}
	    		  GrupoPublicoVO[] grupos = this.getSrvPerfilPublico().obtenerGruposPublicosPorIdentificador(ident);
			  	  form.setIdsAsArray(ident);
			  	  form.setListaId(lista);
			  	  form.setGrupos(grupos);
			  	  form.setTitulo(titulo);
			  	  form.setId_mec(id_mec);
			  	  form.setVuelta(valorVuelta);
			  	  form.setNombre(valorNombre);
//				  	ident[i]= new Long((String) arrayIds[i]);
//	    			lista=lista+ ident[i].toString() + ",";
	  	    
		   }else{
			   throw new ValidatorException("{errors.asociarOde.idNulo}");
		   }
	      	}catch (Exception e) {
	  	      	    logger.error("Se ha producido un error al recuperar los grupos: " + e);
	  	      	    throw new ValidatorException("{errors.asociarOde.recuperar}");
	  	     }  
     }







	public String obtenerAccion(ActionMapping mapping, ObtenerAccionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accion="Cancelar";
	   	 try{
	    	String valorVuelta=form.getVuelta();//6
	    	String valorNombre=form.getNombre();
	    	String actionAceptar=form.getActionAceptar();
	    	String actionCancelar=form.getActionCancelar();
	    	Collection grupos=form.getGruposAsociar();
	    	String listaId = form.getListaId();
	    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
	
	        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
	    	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarios.aceptar")))){
	    		form.setVuelta(valorVuelta);
	    		form.setNombre(valorNombre);
	    		form.setGruposAsociar(grupos);
	    		form.setListaId(listaId);
	     		accion="Aceptar";
	     	}else if((actionCancelar!=null && !actionCancelar.equals("")&& actionCancelar.equals(i18n.getString("usuarios.cancelar")) )){
	     		form.setVuelta(valorVuelta);
	    		form.setNombre(valorNombre);
	     		accion="Cancelar";
	     	}
	     	logger.info("La accion que recibimos es "+accion);
	   	}catch(Exception e){
	   		 logger.error("Error al obtener la accion "+accion);
	   	 }
	        return accion;
	}







	public String obtenerVuelta(ActionMapping mapping, ObtenerVueltaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vuelta="Contacto";
	   	 try{
	    	String valorVuelta=form.getVuelta();//7
	    	String valorNombre=form.getNombre();
	    	
	    	if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Grupo") && valorNombre!=null && !valorNombre.equals(""))){
	     		form.setNombre(valorNombre);
	     		vuelta="Grupo";
	     	}
	     	else if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Favorito"))){
	     		vuelta="Favorito";
	     	}else if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Contacto"))){
	     		vuelta="Contacto";
	     	}
	     	logger.info("La accion que recibimos es "+vuelta+" con nombre de grupo "+valorNombre);
	   	}catch(Exception e){
	   		 logger.error("Error al obtener la accion "+vuelta);
	   	 }
	        return vuelta;
	}


	public void obtenerNombreGrupo(ActionMapping mapping,
			ObtenerNombreGrupoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String nombre=form.getNombre();
		form.setNombre(nombre);
		logger.info("Tenemos que volver al grupo "+nombre);
		
	}









}