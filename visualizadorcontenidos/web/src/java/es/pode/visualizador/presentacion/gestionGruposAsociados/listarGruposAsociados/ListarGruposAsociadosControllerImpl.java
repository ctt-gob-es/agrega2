// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeConGruposVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController
 */
public class ListarGruposAsociadosControllerImpl extends ListarGruposAsociadosController
{



	private static Logger logger = Logger.getLogger(ListarGruposAsociadosControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#getIds(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getIds(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
        	String valorVuelta=form.getVuelta();//1
        	String valorNombre=form.getNombre();
    		String id_mec=form.getId_mec();
    		String titulo=form.getTitulo();
    		String actionCancelar=form.getActionCancelar();
    		String actionAceptar=form.getActionAceptar();
	    	List lista = ((ListarGruposAsociadosAsociarFormImpl) form).getIdRowSelection();
	    	if(logger.isDebugEnabled())
	    		logger.debug("Los grupos a los que se va a asociar el ode son ["+lista);
	    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
	    	
	        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
	       if (lista == null && actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarioPublico.itinerarios.aprendizaje.asociar"))){
	    	   throw new ValidatorException ("{errors.asociarOde.idNulo}");
	       }
	       form.setIds(lista);
	       
	       logger.info("Se va a asociar el ode a los siguientes grupos: " + lista);
	       form.setId_mec(id_mec);
	       form.setTitulo(titulo);
	       form.setVuelta(valorVuelta);
	       form.setNombre(valorNombre);
       } catch (ValidatorException e){
    	   throw e;
	    	   
       } catch (Exception e){
    	   
    	   logger.error("Se ha producido un error al intentar recuperar los nombre  para asociar: " +e);
    	   throw new ValidatorException ("{errors.asociarOde.idError}");
    	   
       }
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#obtenerAccion(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerAccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerAccion(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerAccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String accion="Cancelar";
	   	 try{
	    	String valorVuelta=form.getVuelta();//2
	    	String valorNombre=form.getNombre();
	    	String actionAceptar=form.getActionAceptar();
	    	String actionCancelar=form.getActionCancelar();
	    	Collection ids = form.getIds();
	    	String id_mec=form.getId_mec();
	    	String titulo=form.getTitulo();
	    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
	
	        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
	    	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarioPublico.itinerarios.aprendizaje.asociar")))){
	    		form.setVuelta(valorVuelta);
	    		form.setNombre(valorNombre);
	    		form.setIds(ids);
	    		form.setId_mec(id_mec);
	    		form.setTitulo(titulo);
//	    		request.getSession().setAttribute("nombre", valorNombre);
//	    		request.getSession().setAttribute("vuelta", valorVuelta);
//	    		request.getSession().setAttribute("ids", ids);
//	    		request.getSession().setAttribute("id_mec", id_mec);
//	    		request.getSession().setAttribute("titulo", titulo);
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







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#obtenerGruposAsociados(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerGruposAsociadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGruposAsociados(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerGruposAsociadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String id_mec=form.getId_mec();
    	logger.info("El identificador que nos llega es "+id_mec);
    	String titulo=form.getTitulo();
    	logger.info("El titulo que nos llega es "+titulo);
    	String valorVuelta=form.getVuelta();//1
    	String valorNombre=form.getNombre();
    	GrupoPublicoAdminVO[] grupos = this.getSrvPerfilPublico().obtenerGrupoUsuario(LdapUserDetailsUtils.getUsuario());
        // this property receives a default value, just to have the application running on dummy data
    	String[] ids=new String[1];
    	ids[0]=id_mec;
    	OdeConGruposVO[] gruposConOdes = this.getSrvPerfilPublico().listadoGruposConAsociacionPorUsuarioYOde(LdapUserDetailsUtils.getUsuario(), ids);
    	GrupoAdminConOde[] todosGrupos=null;
    	if(gruposConOdes!=null && gruposConOdes.length>0){
    		OdeConGruposVO gruposConOde=gruposConOdes[0];
    		GrupoPublicoVO[] gruposOde=gruposConOde.getGruposAsociados();
    		Long[] identificadores=new Long[gruposOde.length];
    		for(int j=0;j<gruposOde.length;j++){
    			Long identificador=gruposOde[j].getId();
    			identificadores[j]=identificador;
    		}
    		List list =Arrays.asList(identificadores);
    		
    		List list2 =Arrays.asList(grupos);
    		Iterator iterator=list2.iterator();
    		Collection<GrupoAdminConOde> coleccion=new ArrayList();
    		int k=0;
    		while(iterator.hasNext()) {
    			GrupoAdminConOde grupoCon=new GrupoAdminConOde();
    			GrupoPublicoAdminVO g = (GrupoPublicoAdminVO) (iterator.next());
    			String administrador=g.getAdministrador();
    			String descripcion=g.getDescripcion();
    			Calendar calenCreacion=g.getFechaCreacion();
    			Calendar calenModificacion=g.getFechaModificacion();
    			Long id=g.getId();
    			String imagen=g.getImagenGrupo();
    			String nombre=g.getNombre();
    			Boolean creador=g.getCreador();
    			grupoCon.setAdministrador(administrador);
    			grupoCon.setCreado(creador);
    			grupoCon.setDescripcion(descripcion);
    			grupoCon.setFechaCreacion(calenCreacion.getTime());
    			if(calenModificacion!=null)
    				grupoCon.setFechaModificacion(calenModificacion.getTime());
    			else
    				grupoCon.setFechaModificacion(null);
    			grupoCon.setId(id);
    			grupoCon.setImagenGrupo(imagen);
    			grupoCon.setNombre(nombre);
    			if(list.contains(g.getId())){
    				grupoCon.setContieneOde(Boolean.TRUE);
    				k++;
    			}else{
    				grupoCon.setContieneOde(Boolean.FALSE);
    			}
    			coleccion.add(grupoCon);
    		}
    		todosGrupos=coleccion.toArray(new GrupoAdminConOde[0]);
    		if(k==todosGrupos.length){
    			form.setTodosAsociados(Boolean.TRUE);
    		}else{
    			form.setTodosAsociados(Boolean.FALSE);
    		}
    		
    	}
        form.setId_mec(id_mec);
        form.setTitulo(titulo);
        form.setGruposAsociadosAsArray(todosGrupos);
       }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#obtenerNombreGrupo(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerNombreGrupoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerNombreGrupo(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerNombreGrupoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	form.setNombre(form.getNombre());
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#obtenerVariables(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerVariablesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerVariables(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerVariablesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	form.setId_mec(form.getId_mec());
		form.setTitulo(form.getTitulo());
		form.setIdioma(form.getIdioma());
		form.setVuelta(form.getVuelta());
		form.setNombre(form.getNombre());
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ListarGruposAsociadosController#obtenerVuelta(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerVueltaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerVuelta(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposAsociados.listarGruposAsociados.ObtenerVueltaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String vuelta="Contacto";
	   	 try{
	    	String valorVuelta=form.getVuelta();//3
	    	String valorNombre=form.getNombre();
	    	
	    	
	    	if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Grupo") && valorNombre!=null && !valorNombre.equals(""))){
	     		form.setNombre(valorNombre);
//	     		request.getSession().setAttribute("nombre", valorNombre);
	     		vuelta="Grupo";
	     	}else if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Contacto"))){
//	     		request.getSession().setAttribute("nombre", valorNombre);
	     		vuelta="Contacto";
	     	}else if((valorVuelta!=null && !valorVuelta.equals("") && valorVuelta.equals("Favorito"))){
//	     		request.getSession().setAttribute("nombre", valorNombre);
	     		vuelta="Favorito";
	     	}
	     	logger.info("La accion que recibimos es "+vuelta+" con nombre de grupo "+valorNombre);
	   	}catch(Exception e){
	   		 logger.error("Error al obtener la accion "+vuelta);
	   	 }
	        return vuelta;
    }


public class GrupoAdminConOde {
        
	 	private java.lang.String nombre;
	 	
	 	private java.lang.String descripcion;

		private java.lang.String imagenGrupo;

        private java.lang.String administrador;
        
        private Date fechaModificacion;
        
        private Date fechaCreacion;
        
        private Long id;
        
        private Boolean contieneOde;
        
        private Boolean creado;
        

		public Boolean getCreado() {
			return creado;
		}


		public void setCreado(Boolean creado) {
			this.creado = creado;
		}


		public java.lang.String getNombre() {
			return nombre;
		}


		public void setNombre(java.lang.String nombre) {
			this.nombre = nombre;
		}


		public java.lang.String getDescripcion() {
			return descripcion;
		}


		public void setDescripcion(java.lang.String descripcion) {
			this.descripcion = descripcion;
		}


		public java.lang.String getImagenGrupo() {
			return imagenGrupo;
		}


		public void setImagenGrupo(java.lang.String imagenGrupo) {
			this.imagenGrupo = imagenGrupo;
		}


		public java.lang.String getAdministrador() {
			return administrador;
		}


		public void setAdministrador(java.lang.String administrador) {
			this.administrador = administrador;
		}


		public Date getFechaModificacion() {
			return fechaModificacion;
		}


		public void setFechaModificacion(Date fechaModificacion) {
			this.fechaModificacion = fechaModificacion;
		}


		public Date getFechaCreacion() {
			return fechaCreacion;
		}


		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Boolean getContieneOde() {
			return contieneOde;
		}


		public void setContieneOde(Boolean contieneOde) {
			this.contieneOde = contieneOde;
		}


		public GrupoAdminConOde() {
        }

		
    	
    }






}