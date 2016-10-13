/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.contenidoInapropiado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioCorreoVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO;
import es.pode.publicacion.negocio.servicios.EstadoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;



/**
 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController
 */
public class ContenidoInapropiadoControllerImpl extends ContenidoInapropiadoController
{

	private static final long serialVersionUID = -4985337309298152221L;

	private static Logger logger = Logger.getLogger(ContenidoInapropiadoControllerImpl.class);

	private final static String IMAGEN_HTTP = "http://";
	private final static String BARRA = "/";
	private final static String DETALLE_CORTA = "url_buscadorDetalleCorta";
	private final static String VISUALIZAR_CORTA = "url_previsualizarCorta";

	protected final static String COMENTARIO_RESULTADO_CORRECTO_CI="COM_OK_CI";
	protected final static String COMENTARIO_RECHAZO_CI="COM_REZ_CI";
	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#listarContentidosInapropiados(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.ListarContentidosInapropiadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void listarContentidosInapropiados(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.ListarContentidosInapropiadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		try  {
			logger.debug("Request es "+request);

			ContenidoInapSession session=this.getContenidoInapSession(request);
			setContenidoInapSession(request, session);
			//formamos la url de ficha del ode
			String urlFicha =  IMAGEN_HTTP + LdapUserDetailsUtils.getHost()  + LdapUserDetailsUtils.getSubdominio() + BARRA + request.getSession().getServletContext().getInitParameter(VISUALIZAR_CORTA);
			session.setFichaOde(urlFicha);

			logger.info("VAMOS A LEER DATOS DE BD");
			ContenidoInapropiadoVO[] contenidosIn=this.getSrvContenidoInapropiadoService().obtenerContenidosInapropiados();
			logger.info("DATOS LEIDOS");
			if (contenidosIn==null){
				//lo inicializamos para que no provoque error
				logger.debug("ContenidosInapropiados es null!!");
				contenidosIn= new ContenidoInapropiadoVO[0];
			}
			logger.debug("ContenidosInapropiados mide "+contenidosIn.length);
			form.setContenidosAsArray(contenidosIn);

			session.setContenidos(Arrays.asList(contenidosIn));

			// a esto le falta el idioma e identificador del ode
			//hemos cargado los datos y vamos a la jsp

		}catch (Exception e) {
			logger.error("Error al cargar contenidos inapropiados ", e);
		}
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#eliminarContenidoInapropiado(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.EliminarContenidoInapropiadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void eliminarContenidoInapropiado(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.EliminarContenidoInapropiadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			String[] idsOde=form.getIdOdeRowSelectionAsArray();
			Collection<?> colContinap =this.getContenidoInapSession(request).getContenidos();	       
			ContenidoInapropiadoVO[] contInapvo = colContinap.toArray(new ContenidoInapropiadoVO[colContinap.size()]);

			// recorro estos identificadores y monto el array para llamar al eliminar
			if (idsOde!=null && idsOde.length>0){
				ArrayList<ContenidoInapropiadoVO> arrContenidos = new ArrayList<ContenidoInapropiadoVO>();
				for (int in=0; in<idsOde.length;in++){
					logger.info("seleccionado " + idsOde[in]);
					//es_20081207_2_9145244/false/PUBLICADDD//es
					//es_20081207_2_9145250/true/PUBLICADO//es
					String[]idsPartes=idsOde[in].split("/");
					//	    		   ContenidoInapropiadoVO continavo= new ContenidoInapropiadoVO();
					if (idsPartes!=null && idsPartes.length>0 && idsPartes.length==5){
						//tenemos que buscar en la collection de session...

						int ici=0;
						boolean encontrado=false;
						while (ici<contInapvo.length && !encontrado){
							if (contInapvo[ici]!=null && contInapvo[ici].getIdOde().equals(idsPartes[0]) 
									&& (contInapvo[ici].getEstado_ci().equals(Boolean.valueOf(idsPartes[1]))) 
									&& contInapvo[ici].getEstado().equals(idsPartes[2])
									/*&& contInapvo[ici].getFecha_inactividad().equals(idsPartes[3])*/
									&& contInapvo[ici].getIdioma_cat().equals(idsPartes[4])){

								encontrado=true;
								//meto el elemento en el array
								arrContenidos.add(contInapvo[ici]);
							}
							ici++;
						}
					}//fin if idsPartes
				}//fin for
				//transformamos el array en vo
				ContenidoInapropiadoVO[] contenidos = arrContenidos.toArray(new ContenidoInapropiadoVO[arrContenidos.size()]);
				logger.info("ASC - VAMOS A LLAMAR AL SERVICIO PARA ELIMINAR CONTENIDOS INAPROPIADOS ");
				this.getSrvContenidoInapropiadoService().eliminarContenidosInapropiados(contenidos);
			}
		} catch (Exception e) {
			logger.error("SE HA PRODUCIDO UN ERROR AL ELIMINAR " + e);
			throw new Exception("{error.contenido.inapropiado}");
		}
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#verReporteContenidoInap(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.VerReporteContenidoInapForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void verReporteContenidoInap(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.VerReporteContenidoInapForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("ESTAMOS EN VER REPORTE ");
		String idOde=form.getIdOde();
		String estado=form.getEstado();
		boolean estado_ci=form.isEstado_ci();
		String fecha_inactividad= form.getFecha_inactividad();
		String idioma_cat= form.getIdioma_cat();
		//llamada al servicio para obtener el detalle
		try {
			ContenidoInapropiadoVO[] detalleContInap= this.getSrvContenidoInapropiadoService().obtenerContenidoInapropiadodeOde(idOde, estado, estado_ci, fecha_inactividad, idioma_cat);
			if (detalleContInap!=null){
				form.setContenidosAsArray(detalleContInap);//falta hacer jsp
			} //else {
			//    		  detalleContInap= new ContenidoInapropiadoVO[0];
			//    	  }

		}catch (Exception e) {
			logger.error("Error al obtener detalle " + e);
			throw new ValidatorException("{error.contenido.inapropiado}");
		}

	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#despublicarContenidoInapropiado(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.DespublicarContenidoInapropiadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void despublicarContenidoInapropiado(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.DespublicarContenidoInapropiadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// this property receives a default value, just to have the application running on dummy data
		logger.debug("Despublicar Contenido Inapropiado. ");
		String idOde=form.getIdOde();
		String idioma_cat= form.getIdioma_cat();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);//request.getLocale();
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",	locale);

		try {
			//Nos aseguramos de que el ode tenga estado publicado

			EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
			if (estvo!=null && (estvo.getClave().equals(AgregaProperties.PUBLICADO)) ) {
				this.getSrvContenidoInapropiadoService().despublicarContenidoInapropiado(idOde, idioma_cat);
				logger.debug("El ode ha sido despublicado como contenido inapropiado.");
				form.setMensajeJsp("");
			}else if (estvo != null && estvo.getClave().equals(AgregaProperties.NO_DISPONIBLE)){
				logger.debug("El ode ha sido despublicado antes.... vamos a modificar su estado: idioma_cat " + 
						idioma_cat + " ESTADO CI " +  AgregaProperties.NO_DISPONIBLE + " ID ODE " + idOde + " NUEVO ESTADO " + AgregaProperties.NO_DISPONIBLE);

				//el ode ha sido despublicado así q lo ponemos en la tabla como despublicado y sacamos el mensaje	        
				//EL ESTADO DEL ODE EN TRANSICION ES NO_DISPONIBLE, PERO EN ESTA TABLA TODAVIA NO SE HA MODIFICADO ASÍ Q ES PUBLICADO!!	        	
				boolean modificado=this.getSrvContenidoInapropiadoService().modificarEstadoContenidoInapropiado(idioma_cat, "", true, AgregaProperties.PUBLICADO, idOde, AgregaProperties.NO_DISPONIBLE);
				if (modificado){
					form.setMensajeJsp(i18n.getString("gestorflujo.contenidoinapropiado.despublicarno"));
				} else {
					form.setMensajeJsp(i18n.getString("gestorflujo.contenidoinapropiado.despublicarerror"));
				}
			}

		} catch (Exception e) {
			logger.error("Error al despublicar un reporte de Contenido Inapropiado " + e);
			form.setMensajeJsp(i18n.getString("gestorflujo.contenidoinapropiado.despublicarerror"));
		}

	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#rechazarContenidoInapropiado(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.RechazarContenidoInapropiadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void rechazarContenidoInapropiado(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.RechazarContenidoInapropiadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// this property receives a default value, just to have the application running on dummy data
		String idOde=form.getIdOde();
		String estado=form.getEstado();
		boolean estado_ci=form.isEstado_ci();
		String fecha_inactividad= form.getFecha_inactividad();
		String idioma_cat= form.getIdioma_cat();

		String nombre="";
		String email="";
		String [] to=null;
		String tituloOde="";

		//Si el ode ha sido despublicado por alguien en el momento se vaya a realizar esta operacion lo avisamos
		// y se corrige en la tabla de contenido inapropiado para que aparezca ahí      
		try{
			//    	      java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);//request.getLocale();
			//			  ResourceBundle i18n = ResourceBundle.getBundle("application-resources",	locale);
			//llamamos al servicio para obtener el detalle y recoger los usuarios antes de que lo inactive
			ContenidoInapropiadoVO[] detalleContInap= this.getSrvContenidoInapropiadoService().obtenerContenidoInapropiadodeOde(idOde, estado, estado_ci, fecha_inactividad, idioma_cat);

			this.getSrvContenidoInapropiadoService().rechazarContenidoInapropiado(idOde, idioma_cat, estado);
			//Mandamos el correo a los administradores
			CorreoOdeVO correoODEVO = new CorreoOdeVO();
			if (LdapUserDetailsUtils.getNombreCompleto()!=null){
				nombre=LdapUserDetailsUtils.getNombreCompleto();
			}
			if (LdapUserDetailsUtils.getMail()!=null){
				email=LdapUserDetailsUtils.getMail();
			}
			///////////PONER BIEN!!
			//comentario de aviso de rechazo para un reporte de Contenido inapropiado
			//	    	  String comentario=i18n.getString(COMENTARIO_RECHAZO_CI);
			//	    	  correoODEVO.setComentario(comentario);
			//Datos del Remitente
			correoODEVO.setEmailRemitente(email);
			correoODEVO.setNombreDestinatario(nombre);
			correoODEVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));

			if (detalleContInap!= null){
				ArrayList<String> arrTo = new ArrayList<String>();
				if (detalleContInap.length>0){
					tituloOde= detalleContInap[0].getTitulo();
					correoODEVO.setComentario(detalleContInap[0].getComentario());
				}
				for (int j=0; j<detalleContInap.length;j++){
					//Vamos a recorrer todos los usuarios y meterlos en un array para conseguir sus correos
					arrTo.add(detalleContInap[j].getUsuario());
					logger.info("ASC - ESTAMOS METIENDO EL USUARIO " + detalleContInap[j].getUsuario() + " EN EL ARRAY DE USUARIOS. ");
				}
				UsuarioCorreoVO[] usuariosCorreo=null;
				if (arrTo!=null && arrTo.size()>0){
					String[] usuarios= arrTo.toArray(new String[arrTo.size()]);
					if (usuarios== null){
						usuarios= new String[0];
					}
					logger.info("ASC - RECHAZAR ANTES DE LLAMAR A OBTENERUSUARIOSCONCORREO ");
					usuariosCorreo=this.getSrvAdminUsuariosService().obtenerUsuariosConCorreo(usuarios);
					logger.info("ASC - RECHAZAR DESPUES DE LLAMAR A OBTENERUSUARIOSCONCORREO " + Arrays.toString(usuariosCorreo));
				}
				//Usuarios a quienes va a ir destinado el correo
				if (usuariosCorreo!=null && usuariosCorreo.length>0){
					//tenemos que filtrar los que reciben correos y recogems su email
					arrTo = new ArrayList<String>();
					for (int r=0; r<usuariosCorreo.length; r++){
						arrTo.add(usuariosCorreo[r].getEmail());
						//los mails finales
						logger.info("ASC - Los mails finales son " + usuariosCorreo[r].getEmail());
					}
					to= arrTo.toArray(new String[arrTo.size()]);

					correoODEVO.setTo(to);
					//Montamos los datos para el logo y las imagenes
					String servidor= IMAGEN_HTTP + LdapUserDetailsUtils.getHost()  + LdapUserDetailsUtils.getSubdominio();
					//String servidor= IMAGEN_HTTP + "localhost:8080";
					String imagenLogo = servidor + BARRA + /*"static/img/logo_agrega_red.gif";//*/ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA) ;
					correoODEVO.setHrefLogo(servidor);
					correoODEVO.setUrlImagenLogo(imagenLogo);

					String urlFicha= servidor + BARRA + request.getSession().getServletContext().getInitParameter(DETALLE_CORTA) + BARRA + idioma_cat + BARRA + idOde;
					correoODEVO.setUrlFicha(urlFicha);
					correoODEVO.setTituloOde(tituloOde);
					//sacamos la imagen del ode
					String urlImagen = ImagenODE.urlImagenODE(idOde);
					correoODEVO.setUrlImagen(urlImagen); 	    			
					correoODEVO.setIdiomaCorreo(idioma_cat);

					this.getSrvCorreo().correoRechazoContenidoInapropiado(correoODEVO);
					form.setMensajeJsp(""); //ok
					logger.info("Hemos rechazado el contenido inapropiado del ode " + idOde);  

				} 
			}


		} catch (Exception e) {
			logger.error("Error al rechazar el contenido inapropiado " + e);
			//throw new ValidatorException("{gestorflujo.contenidoinapropiado.errorRechazarOde}");
			form.setMensajeJsp("gestorflujo.excepcion.rechazar");
		}
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#publicarContenidoInapropiado(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.PublicarContenidoInapropiadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void publicarContenidoInapropiado(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.PublicarContenidoInapropiadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			String idOde=request.getParameter("idOde");
			//    		String estadoFinal=AgregaProperties.PUBLICADO;
			String titulo = request.getParameter("titulo");
			String usuario=request.getParameter("usuario");
			Boolean esDespublicado=Boolean.valueOf(request.getParameter("esDespublicado"));
			ContenidoInapropiadoVO[] continap=this.getSrvContenidoInapropiadoService().obtenerContenidosInapropiados();
			//modificamos el estado en nuestra tabla
			//primero tenemos q buscarlo para sacar el idioma de catalogacion
			String idioma_cat="";
			boolean esta=false;
			int es=0;
			if (continap!=null){
				while (!esta && (es<continap.length)){
					if (continap[es].getIdOde().equals(idOde) && continap[es].getTitulo().equals(titulo)){
						esta=true;
						idioma_cat=continap[es].getIdioma_cat();
					}
					es++;
				}
			}
			//this.getSrvContenidoInapropiadoService().modificarEstadoContenidoInapropiado(idioma_cat, fecha_inactividad, estado_ci, estado, idOde, estadoNuevo)
			this.getSrvContenidoInapropiadoService().rechazarContenidoInapropiado(idOde, idioma_cat, AgregaProperties.NO_DISPONIBLE);
			this.getSrvContenidoInapropiadoService().modificarEstadoContenidoInapropiado(idioma_cat, "", false, AgregaProperties.NO_DISPONIBLE, idOde, AgregaProperties.PUBLICADO);

			//una vez q hemos mod todo lo nuestro llamamos a Publicar
			String urlPublicar= request.getSession().getServletContext().getInitParameter("url_pendientes_mostrar");
			urlPublicar = urlPublicar + "?idODE=" + idOde + "&idUsuario=" + usuario + "&titulo=" + titulo + "&esDespublicado=" + esDespublicado; 
			response.sendRedirect("../../" + urlPublicar);


		} catch (Exception e) {
			logger.error("error al publicar");
		}
	}
	/**
	 * @see es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapropiadoController#actionSubmit(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.ActionSubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final java.lang.String actionSubmit(ActionMapping mapping, es.pode.gestorFlujo.presentacion.contenidoInapropiado.ActionSubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// this property receives a default value, just to have the application running on dummy data
		String accion=form.getAccion();

		return accion;
	}
}