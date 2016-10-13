// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.listarUsuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.administracion.presentacion.adminusuarios.sugerirUsuario.SugerirUsuarioCUFormImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ListarUsuariosController
 */
public class ListarUsuariosControllerImpl extends ListarUsuariosController {

    private static Logger log = Logger.getLogger(ListarUsuariosControllerImpl.class);

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ListarUsuariosController#obtenerUsuarios(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ObtenerUsuariosForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void obtenerUsuarios(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ObtenerUsuariosForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//AltaUsuarioBSession altaUsuarioBSession = this.getAltaUsuarioBSession(request);
		// el idioma será el que tenga en el atributo idioma del usuario
		// que esta en sesion.
		try {
			this.removeAltaUsuarioBSession(request);
			this.removeModificarUsuarioBSession(request);
			
		    UsuarioVO[] usuarioVO = this.getSrvAdminUsuariosService().listarUsuarios();
		    if(log.isDebugEnabled())log.debug("Obtenemos los usuarios");
		    Collection<UsuarioConPublico> colecction =new ArrayList<UsuarioConPublico>();
		    for(int i=0;i<usuarioVO.length;i++){
		    	 UsuarioConPublico usuarioConPublico=new UsuarioConPublico();
		    	usuarioConPublico.setApellido1(usuarioVO[i].getApellido1());
		    	usuarioConPublico.setApellido2(usuarioVO[i].getApellido2());
		    	usuarioConPublico.setClave(usuarioVO[i].getClave());
		    	usuarioConPublico.setCuota(usuarioVO[i].getCuota());
		    	usuarioConPublico.setEmail(usuarioVO[i].getEmail());
		    	usuarioConPublico.setFechaAlta(usuarioVO[i].getFechaAlta());
		    	usuarioConPublico.setFechaBaja(usuarioVO[i].getFechaBaja());
		    	usuarioConPublico.setFechaDesactivacion(usuarioVO[i].getFechaDesactivacion());
		    	usuarioConPublico.setFechaSolicitudAlta(usuarioVO[i].getFechaSolicitudAlta());
		    	usuarioConPublico.setGrupos(usuarioVO[i].getGrupos());
		    	usuarioConPublico.setGrupoTrabajo(usuarioVO[i].getGrupoTrabajo());
		    	usuarioConPublico.setId(usuarioVO[i].getId());
		    	usuarioConPublico.setIdioma(usuarioVO[i].getIdioma());
		    	usuarioConPublico.setIdiomaBusqueda(usuarioVO[i].getIdiomaBusqueda());
		    	usuarioConPublico.setNIF(usuarioVO[i].getNIF());
		    	usuarioConPublico.setNombre(usuarioVO[i].getNombre());
		    	usuarioConPublico.setOpenIdUrl(usuarioVO[i].getOpenIdUrl());
		    	usuarioConPublico.setRecibirCorreoPublicacion(usuarioVO[i].getRecibirCorreoPublicacion());
		    	usuarioConPublico.setTipoCatalogador(usuarioVO[i].getTipoCatalogador());
		    	usuarioConPublico.setTipoEmpaquetador(usuarioVO[i].getTipoEmpaquetador());
		    	usuarioConPublico.setTipoVisualizador(usuarioVO[i].getTipoVisualizador());
		    	usuarioConPublico.setUsuario(usuarioVO[i].getUsuario());
		    	usuarioConPublico.setUsuarioPublico(usuarioVO[i].getUsuarioPublico());
		    	UsuarioPublicoVO usuarioPublico=usuarioVO[i].getUsuarioPublico();
		    	if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PERFIL_PUBLICO).equals("true") && usuarioPublico!=null && usuarioPublico.getActivo()){
		    		usuarioConPublico.setExisteUsuarioPublico(Boolean.TRUE);
		    	}else{
		    		usuarioConPublico.setExisteUsuarioPublico(Boolean.FALSE);
		    	}
		    	colecction.add(usuarioConPublico);
		    }
		    //En vez de pasar sólo el usuario le pasaremos también un boolean
		    form.setUsuarios(colecction);
		    //Mirar si tiene parte público y si es así pintar un Ver Usuario Publico

		} catch (Exception e) {
		    log.error("Error: " + e);
		    throw new ValidatorException("{errors.listarUsuario}");
		}
    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ListarUsuariosController#obtenerIdentificadores(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ObtenerIdentificadoresForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */

    public final void getIds(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.listarUsuarios.GetIdsForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		try {
		    List<?> lista = ((FormularioListadoUsuariosBajaFormImpl) form).getIdRowSelection();
		    if (lista == null) {
	
			throw new ValidatorException("{errors.borrarUsuario.idNulo}");
		    }
			Long[] usuariosAdmin = this.getSrvAdminUsuariosService().obtenerUsuariosAdministrador();
	
			List<Long> gruposAdminList = Arrays.asList(usuariosAdmin);
	
			int num_Admin = usuariosAdmin.length;
			int usuarioDeleted = 0;
			Iterator<?> iter = lista.iterator();
			while (iter.hasNext()) {
			    Long identificador = Long.parseLong((String) iter.next());
			    if (gruposAdminList.contains(identificador)) {
				usuarioDeleted = usuarioDeleted + 1;
			    }
			}
			
	
			if (usuarioDeleted == num_Admin) {
				if(log.isDebugEnabled())log.debug("Se intenta eliminar todos los usuarios que son administrador no se permite");
			    throw new ValidatorException("{errors.borrarUsuario.todosUsuariosAdmin}");
			}
			form.setIds(lista);
		} catch (ValidatorException validator) {
		    log.error("Se ha producido la siguiente exception " + validator);
		    throw validator;
		} catch (Exception e) {
		    log.error("Se produce una excepcion ", e);
		    throw new ValidatorException("{errors.borrarUsuario}");
		}

    }

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ListarUsuariosController#obtenerIdentificadores(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.listarUsuarios.ObtenerIdentificadoresForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
	public final void getUsuarioBusqueda(ActionMapping mapping, GetUsuarioBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String texto = ((FormularioListadoUsuariosBuscarUsuariosFormImpl) form).getBuscarUsuario();
	    if (texto!=null && texto.trim().length()>0){
			String textoBusqueda = texto.trim();
	    	log.info("La longitud es: " + texto.length());
	    	if (textoBusqueda.length()==0 &&textoBusqueda!=null )
	    	{
	    		log.error("Hay que introducir algún criterio de búsqueda");
	    		throw new ValidatorException("{usuarios.error.usuario.vacio}");
	    	}
			form.setUsuarioBusqueda(textoBusqueda);
	    }else {
	    	Object currentForm = request.getSession().getAttribute("form");	    	
	    	if (currentForm instanceof SugerirUsuarioCUFormImpl && (texto==null || texto.length()==0)) {
	    		//TODO Por ahora lo hacemos asi, para volver a calcular los usuarios
	    		UsuarioVO[] usuarioVO = this.getSrvAdminUsuariosService().listarUsuarios();
	    		Collection<UsuarioConPublico> collection = new ArrayList<UsuarioConPublico>();	    		
	    		for(int i=0;i<usuarioVO.length;i++){
			    	 UsuarioConPublico usuarioConPublico=new UsuarioConPublico();
				    	usuarioConPublico.setApellido1(usuarioVO[i].getApellido1());
				    	usuarioConPublico.setApellido2(usuarioVO[i].getApellido2());
				    	usuarioConPublico.setClave(usuarioVO[i].getClave());
				    	usuarioConPublico.setCuota(usuarioVO[i].getCuota());
				    	usuarioConPublico.setEmail(usuarioVO[i].getEmail());
				    	usuarioConPublico.setFechaAlta(usuarioVO[i].getFechaAlta());
				    	usuarioConPublico.setFechaBaja(usuarioVO[i].getFechaBaja());
				    	usuarioConPublico.setFechaDesactivacion(usuarioVO[i].getFechaDesactivacion());
				    	usuarioConPublico.setFechaSolicitudAlta(usuarioVO[i].getFechaSolicitudAlta());
				    	usuarioConPublico.setGrupos(usuarioVO[i].getGrupos());
				    	usuarioConPublico.setGrupoTrabajo(usuarioVO[i].getGrupoTrabajo());
				    	usuarioConPublico.setId(usuarioVO[i].getId());
				    	usuarioConPublico.setIdioma(usuarioVO[i].getIdioma());
				    	usuarioConPublico.setIdiomaBusqueda(usuarioVO[i].getIdiomaBusqueda());
				    	usuarioConPublico.setNIF(usuarioVO[i].getNIF());
				    	usuarioConPublico.setNombre(usuarioVO[i].getNombre());
				    	usuarioConPublico.setOpenIdUrl(usuarioVO[i].getOpenIdUrl());
				    	usuarioConPublico.setRecibirCorreoPublicacion(usuarioVO[i].getRecibirCorreoPublicacion());
				    	usuarioConPublico.setTipoCatalogador(usuarioVO[i].getTipoCatalogador());
				    	usuarioConPublico.setTipoEmpaquetador(usuarioVO[i].getTipoEmpaquetador());
				    	usuarioConPublico.setTipoVisualizador(usuarioVO[i].getTipoVisualizador());
				    	usuarioConPublico.setUsuario(usuarioVO[i].getUsuario());
				    	usuarioConPublico.setUsuarioPublico(usuarioVO[i].getUsuarioPublico());
				    	UsuarioPublicoVO usuarioPublico=usuarioVO[i].getUsuarioPublico();
				    	if(usuarioPublico!=null){
				    		usuarioConPublico.setExisteUsuarioPublico(Boolean.TRUE);
				    	}else{
				    		usuarioConPublico.setExisteUsuarioPublico(Boolean.FALSE);
				    	}
				    	collection.add(usuarioConPublico);
	    		}
	    		
	    		SugerirUsuarioCUFormImpl sugerirUsuarioCUFormImpl = (SugerirUsuarioCUFormImpl) currentForm;
	    		ListarUsuariosFormImpl listarUsuariosFormImpl = new ListarUsuariosFormImpl();
	    		listarUsuariosFormImpl.setIdModificado(-1L);
	    		listarUsuariosFormImpl.setUsuarios(collection);
				listarUsuariosFormImpl.setBuscarUsuario("");
				request.getSession().setAttribute("form", listarUsuariosFormImpl);
			}
	    	
	    	log.error("Hay que introducir algún criterio de búsqueda");
		    throw new ValidatorException("{usuarios.error.usuario.vacio}");
	    }

	}
    static public class UsuarioConPublico {
    	 private java.lang.Long id;

    	    private java.lang.String nombre;

    	    private java.lang.String apellido1;

    	    private java.lang.String apellido2;

    	    private java.lang.String email;

    	    private java.lang.String NIF;

    	    private java.lang.String usuario;

    	    private java.lang.String clave;

    	    private java.lang.String idioma;

    	    private java.lang.String idiomaBusqueda;

    	    private java.lang.String tipoEmpaquetador;

    	    private java.util.Calendar fechaAlta;

    	    private java.util.Calendar fechaBaja;

    	    private java.util.Calendar fechaSolicitudAlta;

    	    private java.util.Calendar fechaDesactivacion;

    	    /* Tipo de catalogador del usuario */
    	    private java.lang.String tipoCatalogador;

    	    /* Espacio en disco disponible para el usuario */
    	    private java.lang.Long cuota;

    	    public java.lang.Long getId() {
				return id;
			}

			public void setId(java.lang.Long id) {
				this.id = id;
			}

			public java.lang.String getNombre() {
				return nombre;
			}

			public void setNombre(java.lang.String nombre) {
				this.nombre = nombre;
			}

			public java.lang.String getApellido1() {
				return apellido1;
			}

			public void setApellido1(java.lang.String apellido1) {
				this.apellido1 = apellido1;
			}

			public java.lang.String getApellido2() {
				return apellido2;
			}

			public void setApellido2(java.lang.String apellido2) {
				this.apellido2 = apellido2;
			}

			public java.lang.String getEmail() {
				return email;
			}

			public void setEmail(java.lang.String email) {
				this.email = email;
			}

			public java.lang.String getNIF() {
				return NIF;
			}

			public void setNIF(java.lang.String nif) {
				NIF = nif;
			}

			public java.lang.String getUsuario() {
				return usuario;
			}

			public void setUsuario(java.lang.String usuario) {
				this.usuario = usuario;
			}

			public java.lang.String getClave() {
				return clave;
			}

			public void setClave(java.lang.String clave) {
				this.clave = clave;
			}

			public java.lang.String getIdioma() {
				return idioma;
			}

			public void setIdioma(java.lang.String idioma) {
				this.idioma = idioma;
			}

			public java.lang.String getIdiomaBusqueda() {
				return idiomaBusqueda;
			}

			public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
				this.idiomaBusqueda = idiomaBusqueda;
			}

			public java.lang.String getTipoEmpaquetador() {
				return tipoEmpaquetador;
			}

			public void setTipoEmpaquetador(java.lang.String tipoEmpaquetador) {
				this.tipoEmpaquetador = tipoEmpaquetador;
			}

			public java.util.Calendar getFechaAlta() {
				return fechaAlta;
			}

			public void setFechaAlta(java.util.Calendar fechaAlta) {
				this.fechaAlta = fechaAlta;
			}

			public java.util.Calendar getFechaBaja() {
				return fechaBaja;
			}

			public void setFechaBaja(java.util.Calendar fechaBaja) {
				this.fechaBaja = fechaBaja;
			}

			public java.util.Calendar getFechaSolicitudAlta() {
				return fechaSolicitudAlta;
			}

			public void setFechaSolicitudAlta(java.util.Calendar fechaSolicitudAlta) {
				this.fechaSolicitudAlta = fechaSolicitudAlta;
			}

			public java.util.Calendar getFechaDesactivacion() {
				return fechaDesactivacion;
			}

			public void setFechaDesactivacion(java.util.Calendar fechaDesactivacion) {
				this.fechaDesactivacion = fechaDesactivacion;
			}

			public java.lang.String getTipoCatalogador() {
				return tipoCatalogador;
			}

			public void setTipoCatalogador(java.lang.String tipoCatalogador) {
				this.tipoCatalogador = tipoCatalogador;
			}

			public java.lang.Long getCuota() {
				return cuota;
			}

			public void setCuota(java.lang.Long cuota) {
				this.cuota = cuota;
			}

			public java.lang.String getOpenIdUrl() {
				return openIdUrl;
			}

			public void setOpenIdUrl(java.lang.String openIdUrl) {
				this.openIdUrl = openIdUrl;
			}

			public java.lang.String getTipoVisualizador() {
				return tipoVisualizador;
			}

			public void setTipoVisualizador(java.lang.String tipoVisualizador) {
				this.tipoVisualizador = tipoVisualizador;
			}

			public java.lang.Boolean getRecibirCorreoPublicacion() {
				return recibirCorreoPublicacion;
			}

			public void setRecibirCorreoPublicacion(
					java.lang.Boolean recibirCorreoPublicacion) {
				this.recibirCorreoPublicacion = recibirCorreoPublicacion;
			}

			public es.pode.adminusuarios.negocio.servicios.GrupoVO[] getGrupos() {
				return grupos;
			}

			public void setGrupos(es.pode.adminusuarios.negocio.servicios.GrupoVO[] grupos) {
				this.grupos = grupos;
			}

			public es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO[] getGrupoTrabajo() {
				return grupoTrabajo;
			}

			public void setGrupoTrabajo(
					es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO[] grupoTrabajo) {
				this.grupoTrabajo = grupoTrabajo;
			}

			public es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO getUsuarioPublico() {
				return usuarioPublico;
			}

			public void setUsuarioPublico(
					es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO usuarioPublico) {
				this.usuarioPublico = usuarioPublico;
			}

			public Boolean getExisteUsuarioPublico() {
				return existeUsuarioPublico;
			}

			public void setExisteUsuarioPublico(Boolean existeUsuarioPublico) {
				this.existeUsuarioPublico = existeUsuarioPublico;
			}

			/* El identificador del openID */
    	    private java.lang.String openIdUrl;

    	    private java.lang.String tipoVisualizador;

    	    private java.lang.Boolean recibirCorreoPublicacion;

    	    private es.pode.adminusuarios.negocio.servicios.GrupoVO[] grupos;

    	    private es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO[] grupoTrabajo;

    	    private es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO usuarioPublico;
    	    
    	    private Boolean existeUsuarioPublico;

        }



 

}