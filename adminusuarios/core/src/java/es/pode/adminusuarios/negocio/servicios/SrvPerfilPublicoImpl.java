/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.dominio.Contacto;
import es.pode.adminusuarios.negocio.dominio.Favorito;
import es.pode.adminusuarios.negocio.dominio.GrupoPublico;
import es.pode.adminusuarios.negocio.dominio.GrupoPublicoDao;
import es.pode.adminusuarios.negocio.dominio.OdeGrupo;
import es.pode.adminusuarios.negocio.dominio.SolicitudGrupo;
import es.pode.adminusuarios.negocio.dominio.Usuario;
import es.pode.adminusuarios.negocio.dominio.UsuarioPublico;
import es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


/**
 * @see es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico
 */


/*
 * 
 * En el modelo hay algunas transiciones que son bidireccionaes como las siguientes:
 * 
 * UsuarioPublico - Usuario : desde ambas entidades tenemos un acceso a la otra entidad esto puede ser útil
 * para entrar a la parte de mi Agrega una vez que este logado y la relación entre usuarioPublico y Usuario puede
 * interesarnos tenerla por si desde la página pública de Agrega queremos acceder al usuario de Agrega para obtener p.e. su idioma de navegación
 * 
 * GrupoPublico - UsuarioPublico: esta relación bidireccional es útil si p.e. desde el perfil público de Agrega queremos saber qué grupos tiene
 * y el contrario nos sirve para saber los usuarios que tiene asignado cada grupo si accedemos desde el listado de grupos.
 * 
 * 
 * 
 * 
 */

public class SrvPerfilPublicoImpl
    extends es.pode.adminusuarios.negocio.servicios.SrvPerfilPublicoBase
{
	private static Logger logger = Logger.getLogger(SrvPerfilPublicoImpl.class);
	private static Properties props = null;
	public static final String FILE_NAME_PROPERTIES = "/adminusuarios.properties";
	private final static String URL_SEPARATOR = "/";
	
	/**
	 * Metodo para obtener los grupos de un usuario
	 * @param usuario El usuario del UsuarioPublicoVO de la plataforma del que queremos saber a que grupos está asociado
	 * @return Array de GrupoPublicoAdminVO, este VO es como el GrupoPublicoVO, sól que tiene un atributo que nos especifica si el usuario que 
	 * hace la llamada es el administrador del grupo o no.
	 * @throws Exception
	 */
	protected GrupoPublicoAdminVO[] handleObtenerGrupoUsuario(String usuario) throws Exception
	{
		
		UsuarioPublicoVO usuarioPublico = null;
		GrupoPublicoVO[] gruposPublicos = null;
		GrupoPublicoAdminVO[] gruposAdmin=null;
		try{
			//Sacaremos el usuario publico a partir del usuario de agrega, el login lo tendremos en ldapUserDetails
			UsuarioPublico usuarioPublicoEntity=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublicoEntity!=null){
				usuarioPublico=this.getUsuarioPublicoDao().toUsuarioPublicoVO(usuarioPublicoEntity);
				//Ya tenemos el usuario publico, ahora tenemos que obtener sus grupos y ver de qué tipo son
				gruposPublicos = usuarioPublico.getGrupoPublico();
				if(logger.isDebugEnabled())logger.debug("Hemos obtenido log grupos publicos para el usuario <"+usuario+">");
				//recoreriamos los grupos publicos para ver de qué tipo son:
				gruposAdmin=new GrupoPublicoAdminVO[gruposPublicos.length];
				for(int i=0; i<gruposPublicos.length; i++)
				{
					GrupoPublicoAdminVO grupoAdmin=new GrupoPublicoAdminVO();
					grupoAdmin.setAdministrador(gruposPublicos[i].getAdministrador());
					grupoAdmin.setDescripcion(gruposPublicos[i].getDescripcion());
					grupoAdmin.setImagenGrupo(gruposPublicos[i].getImagenGrupo());
					grupoAdmin.setNombre(gruposPublicos[i].getNombre());
					grupoAdmin.setFechaCreacion(gruposPublicos[i].getFechaCreacion());
					grupoAdmin.setFechaModificacion(gruposPublicos[i].getFechaModificacion());
					grupoAdmin.setId(gruposPublicos[i].getId());
					String administradorGrupo = gruposPublicos[i].getAdministrador();
					//Vamos a distinguir entre los grupos creados por el usuario y los grupos en los que está asociado
					if(administradorGrupo.equals(usuario)){
						grupoAdmin.setCreador(Boolean.TRUE);
					}else{
						grupoAdmin.setCreador(Boolean.FALSE);
					}
					gruposAdmin[i]=grupoAdmin;
					
				}
			}else{
				logger.warn("El usuario no tiene usuario publico,"+usuario);
				gruposAdmin=new GrupoPublicoAdminVO[0];
			}
		}catch (Exception e) {
			logger.error("Error al obtener todos los grupos del usuario <"+usuario+"> - ",e);
			throw new Exception("Error al obtener todos los grupos del usuario "+usuario,e);
		}

		return gruposAdmin;
	}

	/**
	 * Metodo para crear un usuario publico
	 * @param usuarioPublico UsuarioPublicoVO usuario publico que se quiere dar de alta
	 * @param usuario Usuario de agrega al que se le agrega un usuario publico
	 * @return Long el identificador del usuario creado
	 * @throws Exception
	 */
	protected Long handleCrearUsuarioPublico(UsuarioPublicoVO usuarioPublico,
			String usuario) throws Exception {
		Long identificador=null;
		String nombreDeImagen = null;
		try
		{
			usuarioPublico.setUsuario(usuario);
			UsuarioPublico usuarioPublicoEntidad=this.getUsuarioPublicoDao().fromUsuarioPublicoVO(usuarioPublico);
			//Obtenemos el usuario al que vamos a insertar un usuario publico
			Usuario usuarioAgregaAsociado = this.getUsuarioDao().obtenerDatosUsuario(usuario);
		
			if(usuarioAgregaAsociado!=null){
				if(usuarioAgregaAsociado.getUsuarioPublico()!=null){
					logger.warn("El usuario <"+usuarioAgregaAsociado.getUsuario()+"> ya tiene perfil publico");
				}else{
					String nombreUsuario="";
					String nombre=usuarioAgregaAsociado.getNombre();
					String apellido1=usuarioAgregaAsociado.getApellido1();
					nombreUsuario=nombre+" "+apellido1;
					String apellido2=usuarioAgregaAsociado.getApellido2();
					if(apellido2!=null && !apellido2.equals("")){
						nombreUsuario=nombreUsuario+" "+apellido2;
					}
					usuarioPublicoEntidad.setNombreUsuario(nombreUsuario);
					if(logger.isDebugEnabled())logger.debug("Nuestro usuario es <"+nombreUsuario+">");
					//Vamos a guardar la imagen en el uploads
					DataHandler contenidoImagen =usuarioPublico.getContenidoImagen();
					Boolean resultado=null;
					String rutaFoto=usuarioPublico.getFoto();
					if(rutaFoto != null && !rutaFoto.equals("") && rutaFoto.length()>0){
						String nombreFoto=this.borrarAcentosYEspacios(usuario);
						if(logger.isDebugEnabled())logger.debug("Le quitamos los espacios y acentos a la rutaImagen "+rutaFoto+" y al nombre de usuario "+usuario);
						nombreDeImagen=this.borrarAcentosYEspacios(rutaFoto);
						if(nombreDeImagen!=null && !nombreDeImagen.equals("") && nombreDeImagen.length()>0){
							resultado=this.guardarImagenUsuarioPublico(nombreFoto, contenidoImagen,nombreDeImagen);
							
							if(resultado.booleanValue()){
								logger.debug("Hemos guardado correctamente la imagen del usuario "+usuario+"/"+nombreDeImagen);
								usuarioPublicoEntidad.setFoto(usuario+URL_SEPARATOR+nombreDeImagen);
							}else{
								logger.warn("Ha habido algun error y no se ha podido guardar la imagen, se inserta la de por defecto");
								
								usuarioPublicoEntidad.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
							}
						}else{
							usuarioPublicoEntidad.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
						}
					}else{
						logger.debug("Le insertamos la imagen por defecto");
						usuarioPublicoEntidad.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
					}
					usuarioPublicoEntidad.setActivo(Boolean.TRUE);
					//Se lo insertamos al publico
					//usuarioPublicoEntidad.setUsuarioAgrega(usuarioAgregaAsociado);
					//Creamos usuario publico
					logger.debug("Creamos el usuario publico <"+usuarioPublicoEntidad.getId()+">");
					UsuarioPublico vuelta = this.getUsuarioPublicoDao().create(usuarioPublicoEntidad);
					logger.debug("Se ha creado el usuario publico "+vuelta.getId());
					//Insertamos el publico en el usuario
					usuarioAgregaAsociado.setUsuarioPublico(vuelta);
					//Actualizamos el usuario
					this.getUsuarioDao().update(usuarioAgregaAsociado);
					identificador=vuelta.getId();
					logger.info("Se ha dado de alta al usuario publico para el usuario"+usuario+" con el identificador "+identificador);
				}
			}else{
				logger.warn("No existe el usuario "+usuarioAgregaAsociado+" sobre el que se quiere asociar el perfil publico");
				CrearUsuarioPublicoException exception = new CrearUsuarioPublicoException("No existe  ese usuario publico");
				throw exception;
			}
			
		} catch (Exception e)
		{
			logger.error("Se ha producido la siguiente excepcion al crear el perfil publico para el usuario <"+usuario+"> - ", e);
			//TODO Hay que chequear si aunque se haya producido un error se ha copiado la imagen del usuario en el directorio uploads, en el caso de que se haya guardado
			//habrá que eliminarla.
			this.eliminarImagenUsuarioPublico(usuario);
			CrearUsuarioPublicoException exception = new CrearUsuarioPublicoException("No se ha podido dar de alta al usuario publico", e);
			throw exception;
		}
		return identificador;
	}

	/**
	 * Metodo para modificar un usuario publico
	 * @param usuarioPublico UsuarioPublicoVO usuario publico que se quiere modificar
	 * @param usuario Usuario de agrega al que se le modifica el usuario publico
	 * @return Long Identificador del usaurio publico que se modifica
	 * @throws Exception
	 */
	protected Long handleModificarUsuarioPublico(
			UsuarioPublicoVO usuarioPublico, String usuario) throws Exception {
		
		Long identificador=null;
		try
		{
			logger.debug("Modificacion de los datos de un usuario publico "+usuarioPublico.getUsuario());
			Boolean cambioNombre=Boolean.FALSE;
			String propiedadRuta=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO);
			UsuarioPublico usuarioViejo=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioViejo!=null){
				if(!usuarioViejo.getUsuario().equals(usuario)){
					logger.warn("El usuario que quiere modificar su usuario publico no es el mismo que lo creo");
				}else{
					//Le inserto los nuevos valores
					usuarioViejo.setCentroEducativo(usuarioPublico.getCentroEducativo());
					usuarioViejo.setMostrarFavoritos(usuarioPublico.getMostrarFavoritos());
					usuarioViejo.setMostrarGrupos(usuarioPublico.getMostrarGrupos());
					usuarioViejo.setMostrarObjetos(usuarioPublico.getMostrarObjetos());
					usuarioViejo.setRecibirCorreo(usuarioPublico.getRecibirCorreo());
					usuarioViejo.setDescripcion(usuarioPublico.getDescripcion());
					usuarioViejo.setNombreUsuario(usuarioPublico.getNombreUsuario());
					usuarioViejo.setActivo(usuarioPublico.getActivo());
					//Tratamiento de la foto del usuario
					
					logger.debug("La nueva imagen del usuario "+usuarioPublico.getFoto());
		    		String rutaImagen=usuarioPublico.getFoto();
		    		DataHandler contenidoImagen =usuarioPublico.getContenidoImagen();
		    		logger.debug("El contenido de la imagen es "+contenidoImagen);
		    		String imagenAntiguo=usuarioViejo.getFoto();
		    		
		    		Boolean resultado=Boolean.FALSE;
		    		if(imagenAntiguo==null || imagenAntiguo.equals("") || imagenAntiguo.length()==0){//No tenia imagen
		    			if(rutaImagen==null || rutaImagen.equals("")|| rutaImagen.length()==0){//No le han metido una imagen nueva
		    				if(logger.isDebugEnabled())logger.debug("no le ha insertado ninguna imagen");
		    				usuarioViejo.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
		    			}else{//Le han metido imagen nueva;Tenemos que crearmos la carpeta y la imagen
		    				logger.info("Insertada nueva imagen, antes no tenia");
		    				String nombreDeImagen=this.borrarAcentosYEspacios(rutaImagen);
		    				if(logger.isDebugEnabled())logger.debug("Crear la carpeta con la imagen con nombre del usuario <"+usuario+"> y nombreDeImagen <"+nombreDeImagen+">");
		    				boolean creado=guardarImagenUsuarioPublico(usuario, contenidoImagen, nombreDeImagen);
		    				if(!creado){
		    					logger.warn("No se ha podido generar la imagen nueva");
		    					usuarioViejo.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
		    				}else{
		    					usuarioViejo.setFoto(usuario+"/"+nombreDeImagen);
		    					//logger.info("Se ha creado la imagen nueva");
		    				}
		    			}
		    		}else{//Tenía imagen
		    			if(rutaImagen==null || rutaImagen.equals("")|| rutaImagen.length()==0){//No le han metido una imagen nueva
							if (logger.isDebugEnabled())
								logger.debug("no le ha insertado ninguna imagen nueva, se queda asi");
		 
		    			}else{
		    				String nombreDeImagen=this.borrarAcentosYEspacios(rutaImagen);
		    				if(logger.isDebugEnabled()) logger.debug("Se le inserta una nueva imagen");
		    				//Solo cambiamos la imagen
		    					if(logger.isDebugEnabled())logger.debug("No han cambiado el nombre del nodo, cambiamos la imagen a "+nombreDeImagen);
		    					if(nombreDeImagen!=null && !nombreDeImagen.equals("") && nombreDeImagen.length()>0){
		    						resultado=this.guardarImagenUsuarioPublico(usuario, contenidoImagen,nombreDeImagen);
		    						
		    						if(resultado.booleanValue()){
		    							//Tenemos que borrar la imagen nueva
		    							String rutaImagenAntiguo=propiedadRuta+imagenAntiguo;
		    							if(logger.isDebugEnabled())logger.debug("guardada nueva imagen en antigua carpeta, se borra LA IMAGEN ANTIGUA"+rutaImagenAntiguo);
		    							File fileRutaImagenAntiguo=new File(rutaImagenAntiguo);
		    							UtilesFicheros.eliminar(fileRutaImagenAntiguo);
		//    							boolean borrado=fileRutaImagenAntiguo.delete();
		    							if(fileRutaImagenAntiguo.exists()){
		    								logger.warn("No se ha podido eliminar la imagen antigua "+rutaImagenAntiguo);
		    							}
		    							usuarioViejo.setFoto(usuario+"/"+nombreDeImagen);
		    							
		    						}else{
		    							logger.warn("No se ha podido guardar la imagen nueva");
		    							usuarioViejo.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
		    						}
		    					}else{
		    						usuarioViejo.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
		    					}
		
		    				
		    			}
		    		}
					
					//Actualizo el usuario publico
					this.getUsuarioPublicoDao().update(usuarioViejo);
					logger.info("Se han modificado correctamente los datos del perfil publico del usuario "+usuario);
					identificador=usuarioViejo.getId();
				}
			}else{
				logger.warn("No existe ese usuario Publico"+usuario);
				ModificarUsuarioPublicoException exception = new ModificarUsuarioPublicoException("No existe ese usuario Publico"+usuarioViejo);
				throw exception;
			}
		} catch (Exception e)
		{
			logger.error("Se ha producido la siguiente excepcion en la modificacion del perfil publico del usuario <"+usuarioPublico+">", e);
			ModificarUsuarioPublicoException exception = new ModificarUsuarioPublicoException("No se ha podido modificar el usuario publico", e);
			throw exception;
		}
		
		return identificador;
	}

	/**
	 * Metodo para crear un grupo publico
	 * @param grupoPublico GrupoPublicoVO grupo publico que se quiere dar de alta
	 * @param usuario UsuarioPublico que da de alta al grupo
	 * @return Long Identificador del grupo publico que se crea
	 * @throws Exception
	 */
	protected Long handleCrearGrupoPublico(GrupoPublicoVO grupoPublico, String usuario)
			throws Exception {
		
			//Tenemos que verificar que el que hace la modificación es el administrador
			Long identificador=null;

			if(!grupoPublico.getAdministrador().equals(usuario)){
				logger.warn("No se puede modificar el grupo si no se es el administrador");
				CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No se puede modificar el grupo si no se es el administrador");
				throw exception;	
			}else{//No se podrá modificar el nombre del grupo
					try
					{
						grupoPublico.setAdministrador(usuario);
						UsuarioPublico usuarioPublicoEntity=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
						if(usuarioPublicoEntity!=null){
							Collection<UsuarioPublico> usuarios=new ArrayList();
							//Comprobamos si existe un grupo publico con el mismo nombre
							BuscarGruposPorNombreCriteria criteria = new BuscarGruposPorNombreCriteria();
							criteria.setNombre(grupoPublico.getNombre());
							logger.debug("Buscamos si existe ya en BD un grupo con ese mismo nombre");
							List<GrupoPublico> listaGrupoPublico = this.getGrupoPublicoDao().buscarGruposPorNombre(criteria);
							if(!(listaGrupoPublico == null)&&(listaGrupoPublico.size() > 0))
							{
								logger.warn("Ya existe en BD un grupo con el mismo nombre "+grupoPublico.getNombre());
								CrearGrupoPublicoException exception = new CrearGrupoPublicoException("Ya existe un grupo publico con ese mismo nombre");
								throw exception;
							}else
							{
								//Añadimos en la tabla ode_grupo los odes en el caso de que grupoPublico los tenga
								//Al usuario publico le añadimos el grupo publico
								usuarios.add(usuarioPublicoEntity);
								//Insertamos la fecha de creacion y metemos el de modificacion a null
								grupoPublico.setImagenGrupo(grupoPublico.getImagenGrupo());
								grupoPublico.setFechaCreacion(new Date(System.currentTimeMillis()));
								grupoPublico.setFechaModificacion(null);
								GrupoPublico grupoEntity = this.getGrupoPublicoDao().fromGrupoPublicoVO(grupoPublico);
								//Al grupo publico le añadimos el usuario publico y lo creamos
								GrupoPublico vuelta = this.getGrupoPublicoDao().create(grupoEntity);
								Collection<GrupoPublico> grupos=usuarioPublicoEntity.getGrupoPublico();
                                grupos.add(vuelta);
                                //Al usuario publico le insertamos el grupo creado y lo actualizamos
                                usuarioPublicoEntity.setGrupoPublico(grupos);
								this.getUsuarioPublicoDao().update(usuarioPublicoEntity);
								logger.info("Se ha dado de alta al grupo publico <"+vuelta.getNombre()+"> para el usuario <"+usuario+">");
								identificador=vuelta.getId();
							}
						}else{
							logger.warn("No exite un usuario público con el login "+usuario);
							CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No exite ese usuario público");
							throw exception;
						}
					} catch (Exception e)
					{
						logger.error("Se ha producido la siguiente excepcion al crear el grupo con nombre "+grupoPublico.getNombre()+ " " + e);
						CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No se ha podido dar de alta al grupo publico", e);
						throw exception;
					}
//				}
			}
			
		return identificador;
		
	}

	/**
	 * Metodo para eliminar un grupo publico
	 * @param ids identificador del grupo
	 * @param usuario UsuarioPublico de agrega que elimina el grupo
	 * @return ResultadoOperacionVO[] Array de VO-s que contienen el nombre del grupo y si la eliminación ha sido correcta
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[]  handleEliminarGrupoPublicoPorUsuario(Long[] ids, String usuario) throws Exception {
		//Tenemos que verificar que el que hace la modificación es el administrador
		ResultadoOperacionVO[] todosResultados=null;
		Collection<ResultadoOperacionVO> colecction=new ArrayList();
		for(int j=0;j< ids.length;j++){
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			
			try{
				GrupoPublico grupoPublic=this.getGrupoPublicoDao().load(ids[j]);

				if(grupoPublic==null ){ 
					logger.warn("No existe un grupo con el id <"+ids[j]+">");
					CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No existe un grupo con ese id");
					resultado.setResultado(Boolean.FALSE);
//					throw exception;
				}else{
					String nombre=grupoPublic.getNombre();
					resultado.setNombre(nombre);
					logger.debug("Se elimina el grupo <"+nombre+">");
//					GrupoPublico grupoViejo=(GrupoPublico) gruposNombre.get(0);
					if(!grupoPublic.getAdministrador().equals(usuario)){
						logger.warn("No se puede modificar el grupo "+nombre+"ya que el usuario "+usuario+" no es administrador");
						resultado.setResultado(Boolean.FALSE);
					}else{
						
						UsuarioCorreoVO[] usuariosCorreo = this.listarUsuariosDeGrupoCorreo(nombre);
						logger.debug("Lista de usuarios que recibirán el mail si la eliminación va bien. N. de usuarios: <"+usuariosCorreo.length+"> Grupos con odes asociados: <"+nombre+">");
						//Antes de borrar el grupo tenemos que verificar que no haya odes asociados solo a ese grupo, si es así habrá que eliminarlos
						Collection<OdeGrupo> odesGrupo=grupoPublic.getOdeGrupo();
						Iterator iter = odesGrupo.iterator();
						int k = 0;
						while (iter.hasNext())
						{
							OdeGrupo g = (OdeGrupo) (iter.next());
							ObtenerGruposConOdeCriteria criteria2=new ObtenerGruposConOdeCriteria();
							criteria2.setId_mec(g.getId_mec());
							//Obtenemos los grupos que tienen ese ode asociado, si unicamente esta asociado al grupo que se quiere eliminar se elimina el ode
							// de la tabla odeGrupo
							List<GrupoPublico> gruposExistentes=this.getGrupoPublicoDao().obtenerGrupoConOde(criteria2);//Por lo menos tiene que venir el nuestro
							
							if(gruposExistentes!=null && gruposExistentes.size()>1){
								logger.debug("Existen Grupos Publicos que tienen ese ode asociado "+g.getId_mec());
		
							}else{
								logger.debug("Ningun grupo contenia ese ode, excepto el nuestro");
								this.getOdeGrupoDao().remove(g);
							}
							
							k++;
						}
						//Tenemos que eliminar tods las solicitudes para este grupo
						ObtenerSolicitudesPorGrupoCriteria criteriaSolicitud=new ObtenerSolicitudesPorGrupoCriteria();
						criteriaSolicitud.setGrupo(nombre);
						List<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorGrupo(criteriaSolicitud);
						if(!(solicitudes == null))
						{
							logger.debug("Obtenemos <"+solicitudes.size()+"> solicitudes para el grupo");
						}
						Iterator iter3 = solicitudes.iterator();
						int n = 0;
						while (iter3.hasNext())
						{
							SolicitudGrupo g = (SolicitudGrupo) (iter3.next());
							this.getSolicitudGrupoDao().remove(g);
							
							n++;
						}
						//Tenemos que eliminar el grupo de todos los usuarios publicos y borrar las relaciones con los odes del grupo
						ObtenerUsuariosPublicosPorNombreGrupoCriteria criteriaNombre =new ObtenerUsuariosPublicosPorNombreGrupoCriteria();
						criteriaNombre.setNombre(nombre);
						List usuariosConGrupo=this.getUsuarioPublicoDao().obtenerUsuariosPublicosPorNombreGrupo(criteriaNombre);
						logger.debug("Obtenemos usuarios asociados al grupo <"+nombre+">");
						Collection<UsuarioPublico> usuariosLimpios=new ArrayList();
						for(int i=0;i<usuariosConGrupo.size();i++){
							UsuarioPublico usuarioGrupo=(UsuarioPublico)usuariosConGrupo.get(i);
							Collection<GrupoPublico> coleccion=usuarioGrupo.getGrupoPublico();
							Collection<GrupoPublico> coleccionNueva=new ArrayList();
							Iterator iter2 = coleccion.iterator();
							int l = 0;
							while (iter2.hasNext())
							{
								GrupoPublico g = (GrupoPublico) (iter2.next());
								if(!g.getNombre().equals(nombre)){
									coleccionNueva.add(g);
								}
								l++;
							}
							usuarioGrupo.setGrupoPublico(coleccionNueva);
							usuariosLimpios.add(usuarioGrupo);
							
						}
						//Busquemos los odes del grupo y los eliminamos
						this.getUsuarioPublicoDao().update(usuariosLimpios);
						this.getGrupoPublicoDao().remove(grupoPublic);
						
						//TODO Revisar que los destinatarios (to) no sean null. Este correo se debe enviar a los miembros de un grupo menos al administrador del mismo
						//ya que es el, el que elimina el grupo.
//						Si se produce algún fallo en el envío del correo el resultado del método de eliminar no se debe de ver afectado por ello.
						
						
//						Informamos a todos los usuarios miembros de ese grupo de la asociacion de ode a grupo
						
						CorreoGrupoVO correoGrupoVO=new CorreoGrupoVO();
						correoGrupoVO.setNombreGrupo(nombre);
						
						resultado.setResultado(Boolean.TRUE);

						String[] to=null;
						if(usuariosCorreo!=null && usuariosCorreo.length>0){
							Collection<String> stringTo=new ArrayList();
							for(int m=0;m<usuariosCorreo.length;m++){
								String usuarioCorreo = usuariosCorreo[m].getUsuario();
								if(!usuarioCorreo.equals(usuario)){//Al usuario que borra el grupo no se le notifica 
									String mail=usuariosCorreo[m].getEmail();
									if(mail!=null && !mail.equals("")){
										stringTo.add(mail);
//										to[m]=mail;
									}
								}
							}
							to=stringTo.toArray(new String[0]);
						}
						if(to!=null && to.length>0){
							correoGrupoVO.setTo(to);
							String from = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
							correoGrupoVO.setFrom(from);
							//Por ahora no tenemos seguridad...
	//						String idiomaCorreo = LdapUserDetailsUtils.getIdioma();
							I18n i18n = I18n.getInstance();
							String idiomaCorreo=i18n.obtenerIdiomaDefectoPlataforma();
							String hrefLogo = ImagenesAgrega.urlHrefLogo();
							String urlImagenLogo = ImagenesAgrega.urlImagenLogoAgrega();
							correoGrupoVO.setIdiomaCorreo(idiomaCorreo);
							correoGrupoVO.setHrefLogo(hrefLogo);
							correoGrupoVO.setUrlImagenLogo(urlImagenLogo);
							
							ResultadoEnvioCorreoVO vuelta = this.getSrvCorreo().bajaGrupo(correoGrupoVO);
							logger.info("El resultado del envio de correo ha sido: <"+vuelta.getResultado()+"> grupo publico eliminado: <"+nombre);
						}
						
//						if(vuelta.getResultado().equals("OK"))
//							resultado.setResultado(Boolean.TRUE);
//						else{
//							resultado.setResultado(Boolean.FALSE);??
//						}
						
					}
				}
			}catch (Exception e) {
					logger.error("Error al eliminar el grupo publico <"+ids[j]+"> por usuario <"+usuario+"> - ",e);
//					throw new Exception("Error al eliminar el grupo publico "+nombre+" por usuario "+usuario,e);
					resultado.setResultado(Boolean.FALSE);
			}
			colecction.add(resultado);
		}
		todosResultados=colecction.toArray(new ResultadoOperacionVO[0]);
		return todosResultados;
		
	}

	/**
	 * Metodo para modificar un grupo publico
	 * @param grupoPublico GrupoPublicoVO grupo publico que se quiere modificar
	 * @param idGrupo Identificador del grupo que se quiere modificar
	 * @param usuario UsuarioPublico de agrega al que se le modifica el grupo publico
	 * @return Long Identificador del grupo publico que se modifica
	 * @throws Exception
	 */
	protected Long handleModificarGrupoPublico(GrupoPublicoVO grupoPublico,Long idGrupo,
			String usuario) throws Exception {
		//Tenemos que verificar que el que hace la modificación es el administrador
		Long identificador=null;

		if(!grupoPublico.getAdministrador().equals(usuario)){
			logger.warn("No se puede modificar el grupo "+grupoPublico.getNombre()+" ya que "+usuario+" no es el administrador");
			CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No se puede modificar el grupo si no se es el administrador");
			throw exception;	
		}
		
		try{
			GrupoPublico grupoViejo=this.getGrupoPublicoDao().load(idGrupo);
			if(grupoViejo!=null){
			logger.debug("Obtenemos los datos antiguos del grupo "+grupoViejo.getId());
				grupoViejo.setNombre(grupoPublico.getNombre());
				grupoViejo.setDescripcion(grupoPublico.getDescripcion());
				grupoViejo.setImagenGrupo(grupoPublico.getImagenGrupo());
				grupoViejo.setFechaModificacion(new Date(System.currentTimeMillis()));
				this.getGrupoPublicoDao().update(grupoViejo);
				logger.info("Se ha modificado el grupo publico de nombre "+grupoViejo.getNombre());
				identificador=idGrupo;
			}else{
				logger.warn("No existe el grupo publico que se quiere modificar "+grupoPublico.getNombre());
				CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No existe el grupo publico que se quiere modificar");
				throw exception;
			}
		}catch (Exception e) {
			logger.error("Error al modificar el grupo publico <"+idGrupo+"> por usuario <"+usuario+"> - "+e);
			throw new Exception("Error al modificar el grupo publico "+idGrupo+" por usuario "+usuario,e);
		}
		return identificador;
	}

	/**
	 * Metodo que devuelve todos los grupos publicos.
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarGrupoPublicos() throws Exception {
		GrupoPublicoVO[] grupos=null;

		try{
			this.getGrupoPublicoDao();
			Collection<GrupoPublicoVO> todosGrupos=this.getGrupoPublicoDao().loadAll(GrupoPublicoDao.TRANSFORM_GRUPOPUBLICOVO);
			grupos=todosGrupos.toArray(new GrupoPublicoVO[0]);
			if(!(grupos == null))
			{
				logger.debug("Recibimos "+grupos.length+" grupos publicos ");
			}
		}catch (Exception e) {
			logger.error("Error al listar los grupos publicos - ",e);
			throw new Exception("Error al listar los grupos publicos ",e);
		}
		return grupos;
	}

	/**
	 * Metodo que devuelve todos los grupos publicos del administrador que se le pasa.
	 * @param administrador El administrador de los grupos, que es el creador del grupo
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarGrupoPublicosDeAdministrador(String administrador)
			throws Exception {
		GrupoPublicoVO[] grupos=null;
		try{
			ObtenerGruposPublicosPorAdministradorCriteria criteria = new ObtenerGruposPublicosPorAdministradorCriteria();
			criteria.setAdministrador(administrador);
			Collection<GrupoPublicoVO> todosGrupos=this.getGrupoPublicoDao().obtenerGrupoPublicosPorAdministrador(this.getGrupoPublicoDao().TRANSFORM_GRUPOPUBLICOVO, criteria);
			if(!(todosGrupos == null))
			{
				logger.debug("Obtenido el listado de grupos <"+todosGrupos.size()+">");
			}
			grupos=todosGrupos.toArray(new GrupoPublicoVO[0]);
		
		}catch (Exception e) {
			logger.error("Error al listar los grupos publicos del administrador <"+administrador+"> - "+e);
			throw new Exception("Error al listar los grupos publicos del administrador "+administrador,e);
		}
		return grupos;
	}

	/**
	 * Metodo que aceptar la solicitud para asociarse con un grupo.
	 * @param idSolicitudGrupo Los datos del solicitante y a que grupo quiere pertenecer
	 * @return Boolean Indica si se ha acpetado correctamente la solicitud
	 * @throws Exception
	 */

	protected Boolean handleAceptarSolicitudGrupo(Long idSolicitudGrupo)
			throws Exception {
		//Al aceptar la solicitud habrá que añadir en el usuario el grupo y en el grupo el usuario
		Boolean creado=Boolean.FALSE;

		try{
			logger.debug("Solicitud "+idSolicitudGrupo);
			SolicitudGrupo solicitud = this.getSolicitudGrupoDao().load(idSolicitudGrupo);
			if(solicitud!=null){
				String grupoAsociar=solicitud.getGrupo();
				String usuarioSolicitante=solicitud.getUsuarioSolicitante();
				//El usuario solicitante se añadira al grupo grupoAsociar
				//Obtenemos el grupo al que se quiere asociar
				ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
				criteria.setNombre(grupoAsociar);
				List<GrupoPublico> grupos = this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
				if(grupos!=null && grupos.size()>0){
					//Verificamos que el grupo que hemos recibido tiene el mismo administrador que el de la solicitud
					GrupoPublico grupo=grupos.get(0);
		
					//Obtenemos el usaurio publico que corresponde al usuario que nos llega
					
					UsuarioPublico usuarioPublico =this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuarioSolicitante);
					if(usuarioPublico!=null){
						logger.debug("Se refresca el estado de asociacion del grupo "+grupo.getId()+" con el usuario "+usuarioSolicitante);
						this.getGrupoPublicoDao().update(grupo);
					
						//Le insertamos el grupo en el usuaurio
						Collection<GrupoPublico> gruposUsuario = usuarioPublico.getGrupoPublico();
						gruposUsuario.add(grupo);
						usuarioPublico.setGrupoPublico(gruposUsuario);
						logger.debug("Refrescamos el usuario publico "+usuarioPublico.getId());
						this.getUsuarioPublicoDao().update(usuarioPublico);
						
						//Ahora borramos la entrada de la base de datos de solicitudes
						logger.debug("Borramos la entrada de la solicitud "+idSolicitudGrupo);
						this.getSolicitudGrupoDao().remove(idSolicitudGrupo);
						creado=Boolean.TRUE;
					}else{
						logger.warn("El usuario "+usuarioSolicitante+" no tiene parte publica");
					}
				}else{
					logger.warn("No existe ningún grupo con ese nombre "+grupoAsociar);
				}
			}else{
				logger.warn("No existe la solicitud que se quiere aceptar "+idSolicitudGrupo);
				return creado;
			}
		}catch (Exception e) {
			logger.error("Error al aceptar la solicitud de grupo - ",e);
			throw new Exception("Error al aceptar la solicitud de grupo ",e);
		}
		return creado;
		
	}

	/**
	 * Metodo que cancelar la solicitud para asociarse con un grupo.
	 * @param idSolicitudGrupo Los datos del solicitante y a que grupo quiere pertenecer
	 * @return Boolean Indica si la solicitud ha sido cancelada correctamente
	 * @throws Exception
	 */
	protected Boolean handleCancelarSolicitudGrupo(Long idSolicitudGrupo)
			throws Exception {
		
		Boolean creado=Boolean.FALSE;
		try{
			SolicitudGrupo grupo=this.getSolicitudGrupoDao().load(idSolicitudGrupo);
			if(grupo!=null){
				
				this.getSolicitudGrupoDao().remove(grupo);
				logger.info("Borrada la solicitud <"+idSolicitudGrupo+">");
				creado=Boolean.TRUE;
			}else{
				logger.warn("No exite la solicitud que se quiere eliminar <"+idSolicitudGrupo+">");
				return creado;
			}
		}catch (Exception e)
		{
			logger.error("Error al borrar la solicitud "+idSolicitudGrupo, e);
			throw new Exception("Error al borrar la solicitud "+idSolicitudGrupo, e);
		}
		return creado;
		
	}

	/**
	 * Metodo que crea la soliditud para asociarse con un grupo.
	 * @param solicitudGrupo SolicitudGrupoVO, los datos del solicitante y a que grupo quiere pertenecer
	 * @return Long Identificador de la solicitud creada
	 * @throws Exception
	 */
	protected Long handleHacerSolicitudParaGrupo(SolicitudGrupoVO solicitudGrupo)
			throws Exception {
		Long creado=null;

		try{
//Verificar el administrador de grupo, y que el usuario no esté en ese grupo; y que no haya pedido de antemano la solicitud de entrada en el mismo grupo
			String grupoAAsociar=solicitudGrupo.getGrupo();
			String usuarioAdministradorGrupo=solicitudGrupo.getUsuarioAdministrador();

			//Se comprueba si el usuarioSolicitante tiene realmente perfil publico
			
			UsuarioPublico usuarioSolicitante = this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(solicitudGrupo.getUsuarioSolicitante());
			if(usuarioSolicitante == null)
			{
				logger.warn("El usuario solicitante no tiene perfil publico <"+solicitudGrupo.getUsuarioSolicitante()+">");
			}else
			{
			
				ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
				criteria.setNombre(grupoAAsociar);
				List<GrupoPublico> gruposEntity=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
				
				if(gruposEntity!=null && gruposEntity.size()>0){
					GrupoPublico grupoEntity=gruposEntity.get(0);
					String adminGrupo=grupoEntity.getAdministrador();
					if(adminGrupo.equals(usuarioAdministradorGrupo)){
						//Se comprueba si el usuarioSolicitante ya tiene una solicitud a ese mismo grupo, en ese caso se actualizaría no se volvería a crear
						ObtenerSolicitudesPorGrupoYSolicitanteCriteria criteriaSolicitud = new ObtenerSolicitudesPorGrupoYSolicitanteCriteria();
						criteriaSolicitud.setGrupo(solicitudGrupo.getGrupo());
						criteriaSolicitud.setSolicitante(solicitudGrupo.getUsuarioSolicitante());
						List<SolicitudGrupo> solicitudes = this.getSolicitudGrupoDao().obtenerSolicitudesPorGrupoYSolicitante(criteriaSolicitud);
						if(((solicitudes == null))||(solicitudes.size() < 1))
						{
							
							Date fechaSolicitud=new Date(System.currentTimeMillis());
							solicitudGrupo.setFechaSolicitud(fechaSolicitud);
							SolicitudGrupo solicitudEntity = this.getSolicitudGrupoDao().fromSolicitudGrupoVO(solicitudGrupo);
							SolicitudGrupo solicitudCreada = this.getSolicitudGrupoDao().create(solicitudEntity);
							logger.info("Se ha creado la solicitud <"+solicitudCreada.getId()+">");
							creado=solicitudCreada.getId();
						}else
						{
							logger.debug("Ya existe una solicitud del usuario "+solicitudGrupo.getUsuarioSolicitante()+" con el grupo "+solicitudGrupo.getGrupo());
							creado= solicitudes.get(0).getId();
							logger.debug("Se devuelve el identificador de la solicitud que ya existia "+creado);
						}
					}else{
						logger.error("El administrador de la solicitud, <"+usuarioAdministradorGrupo+"> no concuerda con el administrador del grupo.");
					//	throw new Exception("El administrador de la solicitud,"+usuarioAdministradorGrupo+" no concuerda con el administrador del grupo");
					}
				}else{
					logger.warn("No existe ningún grupo con ese nombre "+grupoAAsociar);
					//throw new Exception("No existe ningún grupo con ese nombre "+grupoAAsociar);
				}
			}
			
		}catch (Exception e)
		{
			logger.error("Error al hacer la solicitud para la asociación al grupo"+solicitudGrupo.getGrupo(), e);
			//throw new Exception("Error al hacer la solicitud para la asociación al grupo"+solicitudGrupo.getGrupo(), e);
		}
		return creado;
		
	}

	/**
	 * Metodo que añade un ode favorito a la lista de favoritos del usuario.
	 * @param favorito FavoritoVO, los datos del ode que se quiere añadir a la listade favoritos
	 * @param usuario El usuario al que se le añade el ode como favorito
	 * @return Long Identificador del favorito que se  ha añadido al usuario
	 * @throws Exception
	 */
	protected Long handleAnadirFavoritoAUsuario(FavoritoVO favorito,
			String usuario) throws Exception {
		Long creado=null;
		try{
			logger.info("Anadir ode favorito a usuario");
			Favorito favoritoEntity=this.getFavoritoDao().fromFavoritoVO(favorito);
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null){
					ObtenerFavoritosPorIdCriteria criteria2=new ObtenerFavoritosPorIdCriteria();
					criteria2.setId_mec(favoritoEntity.getId_mec());
					List<Favorito> existente=this.getFavoritoDao().obtenerFavoritosPorId(criteria2);
					Favorito favoritoCreado=null;
					Collection<Favorito> favoritos=usuarioPublico.getFavoritoUsuario();
					if(existente!=null && existente.size()>0){
						//Obtenemos la entrada existente
						logger.info("El favorito ya estaba asociado a algún usuario anteriormente");
						favoritoCreado=existente.get(0);
						
					}else{
						
						//Creamos la entrada de favorito
						logger.info("Ningún usuario tenía ese ode entre los favoritos");
						favoritoCreado=this.getFavoritoDao().create(favoritoEntity);	
					}
					//Añadimos el favorito en el usuario
					favoritos.add(favoritoCreado);
					usuarioPublico.setFavoritoUsuario(favoritos);
					//Refrescamos el usuario publico
					this.getUsuarioPublicoDao().update(usuarioPublico);
					creado=favoritoCreado.getId();
	//			}
			}else{
				logger.error("El usuario "+usuario+" no tiene parte publica");
				throw new Exception("El usuario "+usuario+" no tiene parte publica");
			}
		}catch (Exception e) {
			logger.error("Error al añadir favorito a Usuario ",e);
			throw new Exception("Error al añadir favorito a Usuario ",e);
		}
		return creado;
		
	}

	/**
	 * Método para añadir un ODE a un grupo, al asociarlo se enviará un correo a todos los usuarios miembros del grupo
	 * @param ode Ode que se quiere asociar a un grupo
	 * @param nombres Nombres de los grupos a los que se les asocia el ode
	 * @return ResultadoOperacionVO[] Array de ResultadoOperacionVO que contiene el nombre del grupo y un boolean que indica si la asociación ha siod correcta o no
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleAsociarOdeAGrupo(OdeGrupoVO ode, String[] nombres)
			throws Exception {
		ResultadoOperacionVO[] todosResultados=null;
		Collection<ResultadoOperacionVO> colecResultados=new ArrayList();

		for(int i=0;i<nombres.length;i++){
			String nombre=nombres[i];
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			resultado.setNombre(nombre);
			try{
				OdeGrupo odeEntity=this.getOdeGrupoDao().fromOdeGrupoVO(ode);
				ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
				criteria.setNombre(nombre);
				List<GrupoPublico> grupos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
				if(grupos!=null && grupos.size()>0){
					GrupoPublico grupo=grupos.get(0);
					//Vamos a crear la entrada del ode
					//Debemos verificar que ese ode no esté asociado de antes
					OdeGrupo odeCreado =null;
					ObtenerOdesGrupoPorIdCriteria criteria2=new ObtenerOdesGrupoPorIdCriteria();
					criteria2.setId_mec(odeEntity.getId_mec());
					List<OdeGrupo> existentes=this.getOdeGrupoDao().obtenerOdesGrupo(criteria2);
					if(existentes !=null && existentes.size()>0){
						//Obtenermos la entrada exitente
						//Refrescamos fecha?
						logger.debug("El ode ya estaba asociado a algún grupo");
						odeCreado=existentes.get(0);
							
					}else{
						//Si el ode existe en la base de datos o no creamos
						logger.debug("Ningún grupo tenía ese ose asociado");
						odeCreado = this.getOdeGrupoDao().create(odeEntity);
					}
						//Ahora refrescamos el grupo
					Collection<OdeGrupo> odes=grupo.getOdeGrupo();
					odes.add(odeCreado);
					grupo.setOdeGrupo(odes);
					logger.debug("Insertamos el grupo con el nuevo ode añadido y se modifica la fecha de modificacion");
					grupo.setFechaModificacion(new Date(System.currentTimeMillis()));
					this.getGrupoPublicoDao().update(grupo);
					resultado.setResultado(Boolean.TRUE);
						//Informamos a todos los usuarios miembros de ese grupo de la asociacion de ode a grupo
						UsuarioCorreoVO[] usuariosCorreo = this.listarUsuariosDeGrupoCorreo(nombre);
						CorreoGrupoVO correoGrupoVO=new CorreoGrupoVO();
						correoGrupoVO.setNombreGrupo(nombre);
						correoGrupoVO.setTituloOde(odeCreado.getTitulo());
						
						String[] to=null;
						if(usuariosCorreo!=null && usuariosCorreo.length>0){
							to=new String[usuariosCorreo.length];
							for(int j=0;j<usuariosCorreo.length;j++){
								String usuario=usuariosCorreo[j].getUsuario();
								String mail=usuariosCorreo[j].getEmail();
								to[j]=mail;
							}
						}
						
						String urlFicha=ODEPublico.urlFichaODEPublicado(odeCreado.getId_mec(), odeCreado.getIdioma());
						correoGrupoVO.setUrlFicha(urlFicha);
						String urlImagen=ImagenODE.urlImagenODE(odeCreado.getId_mec());
						correoGrupoVO.setUrlImagen(urlImagen);
						if(to!=null && to.length>0){
							correoGrupoVO.setTo(to);
							
							String from = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
							correoGrupoVO.setFrom(from);
							//Por ahora no tenemos seguridad...
	//						String idiomaCorreo = LdapUserDetailsUtils.getIdioma();
							I18n i18n = I18n.getInstance();
							String idiomaCorreo=i18n.obtenerIdiomaDefectoPlataforma();
							String hrefLogo = ImagenesAgrega.urlHrefLogo();
							String urlImagenLogo = ImagenesAgrega.urlImagenLogoAgrega();
							correoGrupoVO.setIdiomaCorreo(idiomaCorreo);
							correoGrupoVO.setHrefLogo(hrefLogo);
							correoGrupoVO.setUrlImagenLogo(urlImagenLogo);
							ResultadoEnvioCorreoVO vuelta = this.getSrvCorreo().envioODEGrupo(correoGrupoVO);
							logger.debug("El resultado del envio de correo <"+vuelta.getResultado()+">");
						}
//						if(vuelta.getResultado().equals("OK"))
//							resultado.setResultado(Boolean.TRUE);
//						else{
//							resultado.setResultado(Boolean.FALSE);??
//						}
						
		//			}
				}else{
					logger.warn("No existe un grupo publico con ese nombre");
					resultado.setResultado(Boolean.FALSE);
				}
			}catch (Exception e) {
				logger.error("Error al asociar el ode al grupo ",e);
//				throw new Exception("Error al asociar el ode al grupo ",e);
				resultado.setResultado(Boolean.FALSE);
			}
			colecResultados.add(resultado);
		}
		todosResultados=colecResultados.toArray(new ResultadoOperacionVO[0]);
		return todosResultados;
	}

	/**
	 * Método para desasociar un ODE de un grupo. Esta acción sólo lo podrá realizar el administrador del grupo
	 * @param id_mecs Identificadores de los Odes que se quieren desasociar de un grupo
	 * @param nombre Nombre del grupo al que se le desasocia el ode
	 * @param usuario Usuario que desasocia el ode
	 * @param titulos Los titulos de los odes que se quieren desasociar de un grupo
	 * @return ResultadoOperacionVO[] Array de ResultadoOperacionVO que contiene el titulo del ode y un boolean que indica si la asociación ha sido correcta o no
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleDesasociarOdeDeGrupo(String[] id_mecs, String nombre, String usuario, String[] titulos)
			throws Exception {
		ResultadoOperacionVO[] resultadosTodos=null;
		Collection<ResultadoOperacionVO> colecResultados=new ArrayList();
		for(int i=0;i<id_mecs.length;i++){
			String id_mec=id_mecs[i];
			String titulo=titulos[i];
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			resultado.setNombre(titulo);
			try{

				ObtenerGruposPublicosPorNombreCriteria criteria = new ObtenerGruposPublicosPorNombreCriteria();
				criteria.setNombre(nombre);
				List<GrupoPublico> gruposPublicos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
				
				//Verificamos si existe algún otro usuario, a parte de el nuestro si tiene ese ode
				ObtenerGruposConOdeCriteria criteria2=new ObtenerGruposConOdeCriteria();
				criteria2.setId_mec(id_mec);
				List<GrupoPublico> usuariosExistentes=this.getGrupoPublicoDao().obtenerGrupoConOde(criteria2);//Por lo menos tiene que venir el nuestro
				if(gruposPublicos!=null && gruposPublicos.size()>0){
					GrupoPublico grupoPublico=gruposPublicos.get(0);
					if(grupoPublico.getAdministrador().equals(usuario)){
						Collection odesGrupo=grupoPublico.getOdeGrupo();
						Collection odesLimpios=new ArrayList();
						OdeGrupo odeGrupo=null;
						Iterator iter = odesGrupo.iterator();
						int k = 0;
						//Recogemos todos los odes que no coincidan con el id_mec dado
						while (iter.hasNext())
						{
							OdeGrupo g = (OdeGrupo) (iter.next());
							if(!g.getId_mec().equals(id_mec)){
								odesLimpios.add(g);
							}else{
								odeGrupo=g;
							}
							k++;				
						}
						//Refrescamos el grupo publico
						grupoPublico.setOdeGrupo(odesLimpios);
						logger.debug("Modificamos la fecha de modificacion del grupo");
						grupoPublico.setFechaModificacion(new Date(System.currentTimeMillis()));
						this.getGrupoPublicoDao().update(grupoPublico);
						//Borramos la entrada del ode
						
						if(usuariosExistentes!=null && usuariosExistentes.size()>1){
							logger.debug("Existen Grupos Publicos que tienen ese ode asociado "+id_mec);
		//					this.getFavoritoDao().update(favoritoEliminar);
						}else{
							logger.debug("Ningun grupo contenia ese ode, excepto el nuestro");
							this.getOdeGrupoDao().remove(odeGrupo);
						}
						
						resultado.setResultado(Boolean.TRUE);
					}else{
						logger.warn("El usuario no es administrador(creador) y para esta accion es imprescindible");
						resultado.setResultado(Boolean.FALSE);
					}
				}else{
					logger.warn("No existe ningun grupo con ese nombre "+nombre);
					resultado.setResultado(Boolean.FALSE);
				}
			}catch (Exception e) {
				logger.error("Error al desasociar el ode de grupo ",e);
//				throw new Exception("Error al desasociar el ode de grupo ",e);
				resultado.setResultado(Boolean.FALSE);
			}
			colecResultados.add(resultado);
		}
		resultadosTodos=colecResultados.toArray(new ResultadoOperacionVO[0]);
		return resultadosTodos;
		
	}

	/**
	 * Método para eliminar como favorito un ode de la lista de favoritos de un usuario.
	 * @param id_mecs Identificadores de los Odes que se quieren eliminar de la lista de favoritos
	 * @param usuario Usuario del que se elimina el favorito
	 * @param titulos Los titulos de los odes que se quieren eliminar de la lista de favoritos de un usuario publico
	 * @return ResultadoOperacionVO[] Array de ResultadoOperacionVO que contiene el titulo del ode y un boolean que indica si la asociación ha sido correcta o no
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleEliminarFavoritoDeUsuario(String[] id_mecs, String usuario, String[] titulos)
			throws Exception {
		ResultadoOperacionVO[] resultadosTodos=null;
		Collection<ResultadoOperacionVO> colecResultados=new ArrayList();
		logger.debug("Eliminacion favorito del usuario "+usuario);
		for(int i=0;i<id_mecs.length;i++){
			String id_mec=id_mecs[i];
			String titulo=titulos[i];
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			resultado.setNombre(titulo);
			try{
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				//Verificamos si existe algún otro usuario, a parte de el nuestro si tiene ese ode
				ObtenerUsuariosConFavoritoCriteria criteria=new ObtenerUsuariosConFavoritoCriteria();
				criteria.setId_mec(id_mec);
				List<UsuarioPublico> usuariosExistentes=this.getUsuarioPublicoDao().obtenerUsuariosPublicosConFavorito(criteria);//Por lo menos tiene que venir el nuestro
				
				
				if(usuarioPublico!=null){
					Collection<Favorito> favoritos=usuarioPublico.getFavoritoUsuario();
					Collection favoritosLimpios=new ArrayList();
					Favorito favoritoEliminar=null;
					Iterator iter = favoritos.iterator();
					int k = 0;
					//Recogemos todos los odes que no coincidan con el id_mec dado
					while (iter.hasNext())
					{
						Favorito g = (Favorito) (iter.next());
						if(!g.getId_mec().equals(id_mec)){
							favoritosLimpios.add(g);
						}else{
							favoritoEliminar=g;
						}
						k++;				
					}
					//Refrescamos el usuario publico
					usuarioPublico.setFavoritoUsuario(favoritosLimpios);
					this.getUsuarioPublicoDao().update(usuarioPublico);
					//Borramos la entrada del ode si no hay ningún otro usuario que lo tiene como favorito
					
					if(usuariosExistentes!=null && usuariosExistentes.size()>1){
						logger.debug("Existen otros usuarios Publicos que tienen ese ode entre los favoritos "+id_mec);
		
					}else{
						logger.debug("Ningun otro usuario tiene ese ode como favorito, se elimina ese favorito");
						this.getFavoritoDao().remove(favoritoEliminar);
					}
					resultado.setResultado(Boolean.TRUE);
				}else{
					logger.warn("El usuario "+usuario+" no tiene parte publica");
					resultado.setResultado(Boolean.FALSE);
				}
			}catch (Exception e) {
				logger.error("Error al eliminar favorito de usuario ",e);
//				throw new Exception("Error al eliminar favorito de usuario ",e);ç
				resultado.setResultado(Boolean.FALSE);
			}
			colecResultados.add(resultado);
		}
		resultadosTodos=colecResultados.toArray(new ResultadoOperacionVO[0]);
		return resultadosTodos;
		
	}

	/**
	 * Método para listar todos los odes favoritos de un usuario
	 * @param usuario Usuario del que se listan los favoritos
	 * @return Array de FavoritoVO-s
	 * @throws Exception
	 */

	protected FavoritoVO[] handleListarFavoritosUsuario(String usuario)
			throws Exception {
		FavoritoVO[] favoritosVO=null;
		try{
			logger.debug("Listado de favoritos del usuario "+usuario);
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			Boolean mostrarFavoritos=usuarioPublico.getMostrarFavoritos();
			Collection<FavoritoVO> collectionFavoritos=new ArrayList();
			if(usuarioPublico!=null){
				Collection<Favorito> favoritos=usuarioPublico.getFavoritoUsuario();
				if(!(favoritos == null))
				{
					logger.debug("Hemos obtenido "+favoritos.size()+" favoritos para el usuario "+usuario);
				}
				
				Iterator iter = favoritos.iterator();
				int k = 0;
				while (iter.hasNext())
				{
					Favorito g = (Favorito) (iter.next());
					FavoritoVO favorito=this.getFavoritoDao().toFavoritoVO(g);
					collectionFavoritos.add(favorito);
					k++;				
				}
				favoritosVO=collectionFavoritos.toArray(new FavoritoVO[0]);	
			}else{
				logger.warn("El usuario "+usuario+" no tiene parte publica");
				favoritosVO=new FavoritoVO[0];
			}
			
		}catch (Exception e) {
			logger.error("Error al listar los favoritos del usuario "+usuario,e);
			throw new Exception("Error al listar los favoritos del usuario "+usuario,e);
		}
		return favoritosVO;
	}

	/**
	 * Método para listar todos los odes de un grupo
	 * @param nombre Nombre del grupo
	 * @return Array de OdeGrupoVO-s
	 * @throws Exception
	 */
	protected OdeGrupoVO[] handleListarOdesDeGrupo(String nombre)
			throws Exception {
		OdeGrupoVO[] odesVO=null;
		try{
			logger.debug("Listado de los odes del grupo "+nombre);
			ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
			criteria.setNombre(nombre);
			List<GrupoPublico> gruposPublicos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
			Collection<OdeGrupoVO> collectionOdes=new ArrayList();
			
			if(gruposPublicos!=null && gruposPublicos.size()>0){
				GrupoPublico grupoPublico=gruposPublicos.get(0);
				Collection<OdeGrupo> odesGrupo=grupoPublico.getOdeGrupo();
				if(!(odesGrupo == null))
				{
					logger.debug("Hemos obtenido "+odesGrupo.size()+" odes para el grupo "+nombre);
				}
				Iterator iter = odesGrupo.iterator();
				int k = 0;
				
				while (iter.hasNext())
				{
					OdeGrupo g = (OdeGrupo) (iter.next());
					OdeGrupoVO ode=this.getOdeGrupoDao().toOdeGrupoVO(g);
					collectionOdes.add(ode);
					k++;				
				}
			}else{
				logger.warn("No existe ningún grupo publico con ese nombre "+nombre);
			}
			odesVO=collectionOdes.toArray(new OdeGrupoVO[0]);
		}catch (Exception e) {
			logger.error("Error al listar los odes del grupo "+nombre,e);
			throw new Exception("Error al listar los odes del grupo "+nombre,e);
		}
		return odesVO;
	}

	/**
	 * Método para listar todas las solicitudes que le faltan por resolver al administrador del grupo
	 * @param administrador El administrador del grupo
	 * @return Array de SolicitudGrupoVO-s
	 * @throws Exception
	 */
	protected SolicitudGrupoVO[] handleListarSolicitudesAdministrador(
			String administrador) throws Exception {
		SolicitudGrupoVO[] solicitudesVO=null;
		try{
			if(logger.isDebugEnabled())logger.debug("Listado de solicitudes en las que el usuario <"+administrador+"> es administrador de un grupo.");
			ObtenerSolicitudesPorAdministradorCriteria criteria=new ObtenerSolicitudesPorAdministradorCriteria();
			criteria.setAdministrador(administrador);
			Collection<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorAdministrador(criteria);
			Collection<SolicitudGrupoVO> collectionSolicitudes=new ArrayList();
			if(!(solicitudes == null))
			{
				if(logger.isDebugEnabled())logger.debug("Obtenidas <"+solicitudes.size()+"> solicitudes para <"+administrador+">");
			}
			Iterator iter = solicitudes.iterator();
			int k = 0;
			
			while (iter.hasNext())
			{
				SolicitudGrupo g = (SolicitudGrupo) (iter.next());
				SolicitudGrupoVO solicitud=this.getSolicitudGrupoDao().toSolicitudGrupoVO(g);
				collectionSolicitudes.add(solicitud);
				k++;				
			}
			solicitudesVO=collectionSolicitudes.toArray(new SolicitudGrupoVO[0]);
		}catch (Exception e) {
			logger.error("Error al listar las solicitudes para el administrador de sus grupos "+administrador, e);
			throw new Exception("Error al listar las solicitudes para el administrador de sus grupos "+administrador, e);
		}
		return solicitudesVO;
	}

	/**
	 * Método para listar todos  los usuarios pertenecientes al grupo
	 * @param nombre El nombre del grupo
	 * @return Array de UsuarioPublicoVO-s
	 * @throws Exception
	 */
	protected UsuarioPublicoVO[] handleListarUsuariosDeGrupo(String nombre) throws Exception {
		UsuarioPublicoVO[] usuariosVO=null;
		
		try{
			logger.debug("Listar usuarios del grupo "+nombre);
			ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
			criteria.setNombre(nombre);
			List<GrupoPublico> grupos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
			Collection<UsuarioPublicoVO> collectionUsuario=new ArrayList();
				
			if(grupos!=null && grupos.size()>0){
				GrupoPublico grupo=grupos.get(0);
				ObtenerUsuariosDeGrupoCriteria criteria2=new ObtenerUsuariosDeGrupoCriteria();
				criteria2.setNombre(nombre);
				List<UsuarioPublico> usuarios=this.getUsuarioPublicoDao().obtenerUsuariosDeGrupo(criteria2);
				Iterator iter = usuarios.iterator();
				int k = 0;
				if(!(usuarios == null))
				{
					logger.debug("Hemos obtenido "+usuarios.size()+" usuarios para el grupo "+nombre);
				}
				while (iter.hasNext())
				{
					UsuarioPublico g = (UsuarioPublico) (iter.next());
					UsuarioPublicoVO usuarioViejo=this.getUsuarioPublicoDao().toUsuarioPublicoVO(g);
					collectionUsuario.add(usuarioViejo);
					k++;				
				}
				}else{
					logger.warn("No existe ningún grupo publico de nombre "+nombre);
				}
				usuariosVO=collectionUsuario.toArray(new UsuarioPublicoVO[0]);
			
		}catch (Exception e) {
			logger.error("Error al listar los usuarios del grupo "+nombre, e);
			throw new Exception("Error al listar los usuarios del grupo "+nombre, e);
		}
		return usuariosVO;
	}

	/**
	 * Método para añadir un contacto a la agenda de un usuario
	 * @param contacto El contacto que se quiere añadir
	 * @param usuario El usuario al que se le añade el contacto
	 * @return ResultadoOperacionVO ResultadoOperacionVO que contiene el contacto y un boolean que indica si la asociación ha sido correcta o no
	 * @throws Exception
	 */
	protected ResultadoOperacionVO handleAnadirContactoAAgenda(ContactoVO contacto,
			String usuario) throws Exception {
		ResultadoOperacionVO resultadosTodos=new ResultadoOperacionVO();
		
		try{
			logger.debug("Anadir contacto "+contacto.getUsuarioContacto()+" a la agenda");
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null){
				//Comprobamos que el contacto que se va a añadir es realmente un usuario publico
				UsuarioPublico contactoAnadir = this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(contacto.getUsuarioContacto());
				if(!(contactoAnadir == null))
				{
					logger.debug("Se añade un contacto a un usuario publico");	
					resultadosTodos.setNombre(contacto.getUsuarioContacto());
					Collection<Contacto> contactos=usuarioPublico.getContacto();
					//Tendremos que verificar que ya no esté en esa agenda
					Contacto contactoEntity=this.getContactoDao().fromContactoVO(contacto);
					ObtenerContactosPorNombreCriteria criteria2=new ObtenerContactosPorNombreCriteria();
					criteria2.setNombreContacto(contacto.getUsuarioContacto());
					List<Contacto> existente=this.getContactoDao().obtenerContactosPorNombre(criteria2);
					Contacto contactoCreado=null;
					if(existente!=null && existente.size()>0){
						//Obtenemos la entrada existente
						logger.info("Ese contacto ya estaba asociado a algun usuario publico");
						contactoCreado=existente.get(0);
					}else{
						
						//Creamos la entrada de contacto
						logger.info("Ningún usuario publico tenía ese contacto en su agenda");
						contactoCreado=this.getContactoDao().create(contactoEntity);
					}
					
					//Refrescamos el usaurio publico
					contactos.add(contactoCreado);
					usuarioPublico.setContacto(contactos);
					this.getUsuarioPublicoDao().update(usuarioPublico);
					resultadosTodos.setResultado(Boolean.TRUE);
				}else
				{
					logger.warn("El contacto no tiene parte publica");
					resultadosTodos.setResultado(Boolean.FALSE);
				}
			}else{
				logger.warn("El usuario no tiene parte publica <"+usuario+">");
				resultadosTodos.setResultado(Boolean.FALSE);
			}
		}catch (Exception e) {
			logger.error("Error al añadir el contacto "+contacto.getUsuarioContacto()+" a la agenda del usuario "+usuario,e);
//			throw new Exception("Error al añadir el contacto "+contacto.getUsuarioContacto()+" a la agenda del usuario "+usuario,e);
			resultadosTodos.setResultado(Boolean.FALSE);
		}
		return resultadosTodos;
		
	}



	/**
	 * Método para buscar todos los grupos que se asemejen a la descripción dada
	 * @param descripcion La descripción de búsqueda
	 * @param usuario El usario que hace la consulta
	 * @return Array de GrupoPublicoAdminVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoAdminVO[] handleBuscarGruposPorDescripcion(
			String descripcion, String usuario) throws Exception {
		GrupoPublicoAdminVO[] gruposAdmin=null;
		try{
			logger.debug("Buscar grupos por descripcion "+descripcion);
			String textoBuscar=concatenarBusqueda(descripcion);
			BuscarGruposPorDescripcionCriteria criteria=new BuscarGruposPorDescripcionCriteria();
			criteria.setDescripcion(textoBuscar);
			List<GrupoPublico> grupos=this.getGrupoPublicoDao().buscarGruposPorDescripcion(criteria);
			Collection<GrupoPublicoAdminVO> collectionGrupos=new ArrayList();
			if(!(collectionGrupos == null))
			{
				logger.debug("Hemos obtenido "+grupos.size()+" grupos que tienen una decripcion parecida a "+descripcion);
			}
			Iterator iter = grupos.iterator();
			int k = 0;
			
			while (iter.hasNext())
			{
				GrupoPublico g = (GrupoPublico) (iter.next());
				GrupoPublicoAdminVO grupoAdmin=new GrupoPublicoAdminVO();
				grupoAdmin.setAdministrador(g.getAdministrador());
				grupoAdmin.setDescripcion(g.getDescripcion());
				grupoAdmin.setImagenGrupo(g.getImagenGrupo());
				grupoAdmin.setNombre(g.getNombre());
				grupoAdmin.setFechaCreacion(g.getFechaCreacion());
				grupoAdmin.setFechaModificacion(g.getFechaModificacion());
				grupoAdmin.setId(g.getId());
				if(usuario.equals(g.getAdministrador())){
					
					grupoAdmin.setCreador(Boolean.TRUE);
				}else{
					grupoAdmin.setCreador(Boolean.FALSE);
				}
				collectionGrupos.add(grupoAdmin);
				k++;				
			}
			gruposAdmin=collectionGrupos.toArray(new GrupoPublicoAdminVO[0]);
		}catch (Exception e) {
			logger.error("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+descripcion,e);
			throw new Exception("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+descripcion,e);
		}
		return gruposAdmin;
	}
	
	

	/**
	 * Método para eliminar contactos de la lista de contactos. Para ello se debe ser el administrador
	 * @param ids Los identificadores de los contactos que se quiere eliminar
	 * @param usuario El usuario al que se le elimina el contacto
	 * @return ResultadoOperacionVO[] Array de VOs que contienen el nombre del contacto y un booleano indicando si se ha eliminado correctamente de la agenda del usuario dado
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleEliminarContactoDeAgenda(Long[] ids,
			String usuario) throws Exception {
		ResultadoOperacionVO[] resultadosTodos=null;
		Collection<ResultadoOperacionVO> colection=new ArrayList();
		logger.debug("Eliminacion de contactos de la agenda");
		for(int j=0;j< ids.length;j++){
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			Contacto contacto=this.getContactoDao().load(ids[j]);
			if(contacto!=null){
				resultado.setNombre(contacto.getUsuarioContacto());
				try{
					UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
					ObtenerUsuariosConContactoCriteria criteria=new ObtenerUsuariosConContactoCriteria();
					criteria.setContacto(contacto.getUsuarioContacto());
					List<UsuarioPublico> usuariosExistentes=this.getUsuarioPublicoDao().obtenerUsuariosConContacto(criteria);
					if(usuarioPublico!=null){
						Collection<Contacto> contactosUsuario=usuarioPublico.getContacto();
						logger.debug("Obtenemos los contactos del usuario.");
						Collection contactosLimpios=new ArrayList();
						Contacto contactoEliminar=null;
						Iterator iter = contactosUsuario.iterator();
						int k = 0;
						//Recogemos todos los odes que no coincidan con el contacto dado
						while (iter.hasNext())
						{
							Contacto g = (Contacto) (iter.next());
							if(!g.getUsuarioContacto().equals(contacto.getUsuarioContacto())){
								contactosLimpios.add(g);
							}else{
								contactoEliminar=g;
							}
							k++;				
						}
						//Refrescamos el usuario publico
						usuarioPublico.setContacto(contactosLimpios);
						this.getUsuarioPublicoDao().update(usuarioPublico);
						
						
						if(usuariosExistentes!=null && usuariosExistentes.size()>1){
							logger.info("Existen Usuarios Publicos que tienen ese contacto asociado "+contacto.getUsuarioContacto());
			//				this.getFavoritoDao().update(favoritoEliminar);
						}else{
							//Borramos la entrada del ode
							this.getContactoDao().remove(contactoEliminar);
						}
						resultado.setResultado(Boolean.TRUE);
					}else{
						logger.warn("El usuario "+usuario+" no tiene parte publica");
						resultado.setResultado(Boolean.FALSE);
					}
				}catch (Exception e) {
					logger.error("Error al eliminar el contacto "+contacto.getUsuarioContacto()+" de la agenda del usuario "+usuario, e);
	//				throw new Exception("Error al eliminar el contacto "+contacto.getUsuarioContacto()+" de la agenda del usuario "+usuario, e);
					resultado.setResultado(Boolean.FALSE);
				}
				colection.add(resultado);
			}else{
				logger.warn("No existe el contacto");
				resultado.setResultado(Boolean.FALSE);
			}
		}
		resultadosTodos=colection.toArray(new ResultadoOperacionVO[0]);
		return resultadosTodos;
		
	}

	/**
	 * Método para eliminar grupos publicos por el usuario con ROL administrador.
	 * @param ids Los identificadores de los grupos que se quieren eliminar
	 * @return ResultadoOperacionVO[] Array de VOs que contienen el nombre del grupo y un booleano indicando si se ha eliminado correctamente
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleEliminarGrupoPublicoPorAdministrador(Long[] ids)
			throws Exception {
		
			ResultadoOperacionVO[] resultadosTodos=null;
			Collection<ResultadoOperacionVO> colection=new ArrayList();
			for(int j=0;j< ids.length;j++){
				ResultadoOperacionVO resultado=new ResultadoOperacionVO();
				
				try{
					GrupoPublico grupoPublic=this.getGrupoPublicoDao().load(ids[j]);
					String nombre=grupoPublic.getNombre();
					resultado.setNombre(nombre);
					if(grupoPublic==null){
						logger.warn("No existe un grupo con ese id");
						CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No existe un grupo con ese id");
//						throw exception;
						resultado.setResultado(Boolean.FALSE);
					}else{
						UsuarioCorreoVO[] usuariosCorreo = this.listarUsuariosDeGrupoCorreo(nombre);
						//Antes de borrar el grupo tenemos que verificar que no haya odes asociados solo a ese grupo, si es así habrá que eliminarlos
						Collection<OdeGrupo> odesGrupo=grupoPublic.getOdeGrupo();
						Iterator iter = odesGrupo.iterator();
						int k = 0;
						while (iter.hasNext())
						{
							OdeGrupo g = (OdeGrupo) (iter.next());
							ObtenerGruposConOdeCriteria criteria2=new ObtenerGruposConOdeCriteria();
							criteria2.setId_mec(g.getId_mec());
							List<GrupoPublico> usuariosExistentes=this.getGrupoPublicoDao().obtenerGrupoConOde(criteria2);//Por lo menos tiene que venir el nuestro
							
							if(usuariosExistentes!=null && usuariosExistentes.size()>1){
								logger.debug("Existen Grupos Publicos que tienen ese ode asociado "+g.getId_mec());
			//					this.getFavoritoDao().update(favoritoEliminar);
							}else{
								logger.debug("Ningun grupo contenia ese ode, excepto el nuestro");
								this.getOdeGrupoDao().remove(g);
							}
							
							k++;
						}
						
						//Tenemos que eliminar tods las solicitudes para este grupo
						ObtenerSolicitudesPorGrupoCriteria criteriaSolicitud=new ObtenerSolicitudesPorGrupoCriteria();
						criteriaSolicitud.setGrupo(nombre);
						List<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorGrupo(criteriaSolicitud);
						if(!(solicitudes == null))
						{
							logger.debug("Obtenemos "+solicitudes.size()+" solicitudes para el grupo");
						}
						Iterator iter3 = solicitudes.iterator();
						int m = 0;
						while (iter3.hasNext())
						{
							SolicitudGrupo g = (SolicitudGrupo) (iter3.next());
							this.getSolicitudGrupoDao().remove(g);
							
							m++;
						}
						//Tenemos que eliminar el grupo de todos los usuarios publicos
						ObtenerUsuariosPublicosPorNombreGrupoCriteria criteriaNombre =new ObtenerUsuariosPublicosPorNombreGrupoCriteria();
						criteriaNombre.setNombre(nombre);
						List usuariosConGrupo=this.getUsuarioPublicoDao().obtenerUsuariosPublicosPorNombreGrupo(criteriaNombre);
						if(!(usuariosConGrupo == null))
						{
							logger.debug("Hemos encontrado "+usuariosConGrupo.size()+" usuarios que tenian ese grupo asociado");
						}
						Collection<UsuarioPublico> usuariosLimpios=new ArrayList();
						for(int i=0;i<usuariosConGrupo.size();i++){
							UsuarioPublico usuarioGrupo=(UsuarioPublico)usuariosConGrupo.get(i);
							Collection<GrupoPublico> coleccion=usuarioGrupo.getGrupoPublico();
							Collection<GrupoPublico> coleccionNueva=new ArrayList();
							Iterator iter2 = coleccion.iterator();
							int l = 0;
							while (iter2.hasNext())
							{
								GrupoPublico g = (GrupoPublico) (iter2.next());
								if(!g.getNombre().equals(nombre)){
									coleccionNueva.add(g);
								}
								l++;
							}
							usuarioGrupo.setGrupoPublico(coleccionNueva);
							usuariosLimpios.add(usuarioGrupo);
							
						}
						
						this.getUsuarioPublicoDao().update(usuariosLimpios);
						this.getGrupoPublicoDao().remove(grupoPublic);
						
						
						//Envio de correo a los usuarios asociados al grupo cuando se elimina este
						//TODO Revisar a quién se envía el correo, cuando el grupo publico es eliminado por el administrador de la plataforma el correo se debe enviar a todos los 
						//mienbros del grupo, incluido el administrador del mismo.
						//Si se produce algún fallo en el envío del correo el resultado del método de eliminar no se debe de ver afectado por ello.
						
						resultado.setResultado(Boolean.TRUE);
						CorreoGrupoVO correoGrupoVO=new CorreoGrupoVO();
						correoGrupoVO.setNombreGrupo(nombre);
						String[] to=null;
						if(usuariosCorreo!=null && usuariosCorreo.length>0){
							to=new String[usuariosCorreo.length];
							for(int l=0;l<usuariosCorreo.length;l++){
								String usuario=usuariosCorreo[l].getUsuario();
								
								String mail=usuariosCorreo[l].getEmail();
								if(mail!=null && !mail.equals("")){
									to[l]=mail;
								}
							}
						}
						if(to!=null && to.length>0){
							correoGrupoVO.setTo(to);
							String from = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
							correoGrupoVO.setFrom(from);
							//Por ahora no tenemos seguridad..
	//						String idiomaCorreo = LdapUserDetailsUtils.getIdioma();
							I18n i18n = I18n.getInstance();
							String idiomaCorreo=i18n.obtenerIdiomaDefectoPlataforma();
							String hrefLogo = ImagenesAgrega.urlHrefLogo();
							String urlImagenLogo = ImagenesAgrega.urlImagenLogoAgrega();
							correoGrupoVO.setIdiomaCorreo(idiomaCorreo);
							correoGrupoVO.setHrefLogo(hrefLogo);
							correoGrupoVO.setUrlImagenLogo(urlImagenLogo);
							ResultadoEnvioCorreoVO vuelta = this.getSrvCorreo().bajaGrupo(correoGrupoVO);
							logger.debug("El resultado del envio de correo "+vuelta.getResultado());
							
						}

					}
				}catch (Exception e) {
					logger.error("Error al eliminar el grupo publico "+ids[j]+" por el administrador ",e);
//					throw new Exception("Error al eliminar el grupo publico "+nombre+" por el administrador ",e);
					resultado.setResultado(Boolean.FALSE);
				}
				colection.add(resultado);			
			}
			resultadosTodos=colection.toArray(new ResultadoOperacionVO[0]);
			return resultadosTodos;
	}



	/**
	 * Metodo para eliminar la solicitud de alta en un grupo
	 * @param idSolicitudes  El identificador de la solicitud que se quiere eliminar
	 * @param solicitante El usuario que elimina la solicitud, debe ser el mismo que hizo la solicitud
	 * @return Boolean[]  true si la eliminación ha ido bien, false en otro caso
	 * @throws Exception
	 */
	protected Boolean[] handleEliminarSolicitud(Long[] idSolicitudes, String solicitante)
			throws Exception {
		logger.debug("Eliminar solicitudes del usuario "+solicitante);
		Boolean[] vueltas=new Boolean[idSolicitudes.length];
		for(int i=0;i<idSolicitudes.length;i++){
			Boolean vuelta=Boolean.FALSE;
			Long idSolicitud=idSolicitudes[i];
			try{
				SolicitudGrupo solicitud=this.getSolicitudGrupoDao().load(idSolicitud);
				if(!(solicitud == null))
				{
					logger.info("Se elimina la solicitud "+idSolicitud+" del usuario "+solicitante);	
					if(solicitante.equals(solicitud.getUsuarioSolicitante())){
						this.getSolicitudGrupoDao().remove(solicitud);
						vuelta=Boolean.TRUE;
					}else{
						logger.warn("No se puede eliminar la solicitud "+idSolicitud+" pues el solicitante  no es el mismo que intenta eliminar la solicitud "+solicitante);
					}
				}else
				{
					logger.info("El usuario <"+solicitante+"> no tiene una solicitud con identificador <"+idSolicitud+">");
				}
				vueltas[i]=vuelta;
			}catch (Exception e)
			{
				logger.error("Error al eliminar la solicitud "+idSolicitud,e);
			}
			vueltas[i]=vuelta;
		}
		return vueltas;
		
	}

	/**
	 * Metodo para eliminar un usuario de un grupo
	 * @param usuarios Nombres de los usuarios asociado al usuario publico que se quieren eliminar
	 * @param nombre El usuario que quiere eliminar al usuario, debe ser el administrador del grupo
	 * @return ResultadoOperacionVO[] Array de VO-s que contiene el nombre del usuario que se ha querido eliminar y si ha sido eliminado correctamente o no
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleEliminarUsuarioDeGrupo(String[] usuarios, String nombre)
			throws Exception {
		ResultadoOperacionVO[] todosResultados=null;

		Collection<ResultadoOperacionVO> collection=new ArrayList<ResultadoOperacionVO>();
		logger.debug("Eliminar usuarios del grupo "+nombre);
		for(int j=0;j<usuarios.length;j++){
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			String usuario=usuarios[j];
			resultado.setNombre(usuario);
			try{
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				if(usuarioPublico!=null){
					ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
					criteria.setNombre(nombre);
					List<GrupoPublico> grupos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
					//Elimino el usuario del grupo
					if(grupos!=null && grupos.size()>0){
						GrupoPublico grupo=grupos.get(0);
						Collection<GrupoPublico> gruposUsuario=usuarioPublico.getGrupoPublico();
						Iterator iter = gruposUsuario.iterator();
						Collection<GrupoPublico> gruposLimpios=new ArrayList(); 
						int k = 0;
						while (iter.hasNext())
						{
							GrupoPublico g = (GrupoPublico) (iter.next());
							if(!(g.getId().equals(grupo.getId()))){
								gruposLimpios.add(g);
							}
							k++;
						}
						usuarioPublico.setGrupoPublico(gruposLimpios);
						this.getUsuarioPublicoDao().update(usuarioPublico);
						resultado.setResultado(Boolean.TRUE);
					}else{
						logger.warn("No existe ningún grupo con el nombre "+nombre);
						resultado.setResultado(Boolean.FALSE);
					}
				}else{
					logger.warn("No existe la parte pública del usuario "+usuario);
					resultado.setResultado(Boolean.FALSE);
				}
			}catch (Exception e) {
				logger.error("Error al eliminar usuario "+usuario+" del grupo "+nombre,e);
//				throw new Exception("Error al eliminar usuario "+usuario+" del grupo "+nombre,e);
				resultado.setResultado(Boolean.FALSE);
			}
			collection.add(resultado);
		}
		todosResultados=collection.toArray(new ResultadoOperacionVO[0]);

		return todosResultados;
	}

	/**
	 * Metodo para listar todos los contactos de un usuario
	 * @param usuario Nombre del usuario publico
	 * @return Array de ContactoVO-s
	 * @throws Exception
	 */
	protected ContactoVO[] handleListarContactosDeAgenda(String usuario)
			throws Exception {
		ContactoVO[] todosContactos=null;
		logger.debug("Listado de los contactos de un usuario "+usuario);
		try{
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null){
				Collection<Contacto> contactos=usuarioPublico.getContacto();
				Collection<ContactoVO> correosVO=new ArrayList(); 
				Iterator iter = contactos.iterator();
				int l = 0;
				if(!(contactos == null))
				{
					logger.debug("Obtenemos "+contactos.size()+" contactos del usuario");
				}
				while (iter.hasNext())
				{
					Contacto g = (Contacto) (iter.next());
					ContactoVO contacto=this.getContactoDao().toContactoVO(g);
					correosVO.add(contacto);
	
					l++;
				}
				todosContactos=correosVO.toArray(new ContactoVO[0]);
			}else{
				logger.warn("El usuario no tiene parte pública "+usuario);
				todosContactos=new ContactoVO[0];
			}
		}catch (Exception e) {
			logger.error("Error al listar los contactos de la agenda de <"+usuario+"> - ",e);
			throw new Exception("Error al listar los contactos de la agenda de "+usuario);
		}
		return todosContactos;
	}

	/**
	 * Metodo para listar todas las solicitudes sin resolver que ha hecho el usuario
	 * @param usuario Nombre del usuario publico
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarGruposSolicitadosPorUsuario(
			String usuario) throws Exception {
		GrupoPublicoVO[] grupoPublicoVO=null;
		try{
			logger.debug("Listar grupos solicitados por el usuario "+usuario);
			ObtenerSolicitudesDeUsuarioCriteria criteria=new ObtenerSolicitudesDeUsuarioCriteria();
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null){
				criteria.setUsuarioSolicitante(usuario);
				List<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorUsuario(criteria);
				if(!(solicitudes == null))
				{
					logger.info("Obtenemos <"+solicitudes.size()+ "> solicitudes hechos por el usuario");
				}
				if(solicitudes!=null && solicitudes.size()>0){
					Iterator iter = solicitudes.iterator();
					Collection<GrupoPublicoVO> gruposVO=new ArrayList();
					int l = 0;
					while (iter.hasNext())
					{
						SolicitudGrupo g = (SolicitudGrupo) (iter.next());
						String grupo=g.getGrupo();
						ObtenerGruposPublicosPorNombreCriteria criteria2=new ObtenerGruposPublicosPorNombreCriteria();
						criteria2.setNombre(grupo);
						List<GrupoPublico> gruposPublicos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria2);
						if(gruposPublicos!=null && gruposPublicos.size()>0){
							GrupoPublico grupoPubli=gruposPublicos.get(0);
							GrupoPublicoVO grupoViejoVO=this.getGrupoPublicoDao().toGrupoPublicoVO(grupoPubli);
							gruposVO.add(grupoViejoVO);
						}else{
							logger.warn("No existe ningún grupo con el nombre "+grupo);
						}
						
						l++;
					}
					grupoPublicoVO=gruposVO.toArray(new GrupoPublicoVO[0]);
				}else{
					grupoPublicoVO=new GrupoPublicoVO[0];
				}
			}else
			{
				logger.warn("El usuario no tiene parte pública "+usuario);
			}
		}catch (Exception e) {
			logger.error("Error al listar las solicitudes realizadas por el usuario "+usuario,e);
			throw new Exception("Error al listar las solicitudes realizadas por el usuario "+usuario,e);
		}
		return grupoPublicoVO;
	}

	/**
	 * Metodo para listar todos los grupos con el usaurio es administrador o asociado
	 * @param usuario Nombre del usuario publico
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarTodosGrupoDeUsuario(String usuario)
			throws Exception {
		GrupoPublicoVO[] gruposVO=null;
		try{
			logger.debug("Listar todos los grupos del usuario "+usuario);
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null){
				Collection<GrupoPublico> grupos=usuarioPublico.getGrupoPublico();
				logger.info("Obtenemos los grupos del usuario");
				if(grupos!=null && grupos.size()>0){
					Collection<GrupoPublicoVO> collectionGrupo=new ArrayList();
					Iterator iter = grupos.iterator();
					int l = 0;
					while (iter.hasNext())
					{
						GrupoPublico g = (GrupoPublico) (iter.next());
						GrupoPublicoVO grupoVO=this.getGrupoPublicoDao().toGrupoPublicoVO(g);
						collectionGrupo.add(grupoVO);
						l++;
					}
					gruposVO=collectionGrupo.toArray(new GrupoPublicoVO[0]);
				}else{
					gruposVO=new GrupoPublicoVO[0];
				}
			}else{
				logger.warn("Este usuario no tiene usuario publico "+usuario);
				gruposVO=new GrupoPublicoVO[0];
			}
		}catch (Exception e) {
			logger.error("Error al listar todos los grupos del usaurio "+usuario,e);
			throw new Exception("Error al listar todos los grupos del usaurio "+usuario,e);
		}
		return gruposVO;
	}



	/**
	 * Metodo para obtener el grupo publico
	 * @param nombre El nombre del grupo
	 * @return GrupoPublicoVO
	 * @throws Exception
	 */
	protected GrupoPublicoVO handleObtenerGrupoPublico(String nombre)
			throws Exception {
		GrupoPublicoVO grupoPublicoVO=null;

		try{
			
			ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
			criteria.setNombre(nombre);
			List<GrupoPublico> grupos=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
			if(grupos!=null && grupos.size()>0){
				GrupoPublico grupo=grupos.get(0);
				grupoPublicoVO=this.getGrupoPublicoDao().toGrupoPublicoVO(grupo);
				logger.info("Obtenido grupo publico con nombre <"+nombre+">");
			}else{
				logger.warn("No existe ningún grupo publico de nombre "+nombre);
				grupoPublicoVO=new GrupoPublicoVO();
			}
		}catch (Exception e) {
			logger.error("Error al obtener el grupo publico de nombre "+nombre,e);
			throw new Exception("Error al obtener el grupo publico de nombre "+nombre,e);
		}

		return grupoPublicoVO;
	}

	/**
	 * Metodo que nos devuelve el número de favoritos que le indiquemos
	 * @param usuario El usuario público del que queremos saber los favoritos
	 * @param numero El número de favoritos que queremos obtener
	 * @return Array de FavoritoVO-s
	 * @throws Exception
	 */
	protected FavoritoVO[] handleObtenerNumeroFavoritosUsuario(String usuario,
			Long numero) throws Exception {
		FavoritoVO[] favoritosAlgunos = null;
		try {
			UsuarioPublico usuarioPublicoEntity = this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if (usuarioPublicoEntity != null) {
				Collection<Favorito> favoritosTodos = usuarioPublicoEntity.getFavoritoUsuario();
				Collection<FavoritoVO> favoritos = new ArrayList();
				Iterator iter = favoritosTodos.iterator();
				int l = 0;
				if (!(numero == null) && (numero > 0)) {
					try {
						while (iter.hasNext() && l < numero) {
							Favorito g = (Favorito) (iter.next());
							FavoritoVO favorito = this.getFavoritoDao()
									.toFavoritoVO(g);
							favoritos.add(favorito);
							l++;
						}
					} catch (NumberFormatException ex) {
						logger.error("Error el numero de favoritos introducido no es un numero " + numero, ex);
					}
					favoritosAlgunos = favoritos.toArray(new FavoritoVO[0]);
				}
				logger.debug("Obtenidos <" + numero + "> favoritos del usuario <" + usuario+">");
			} else {
				logger.warn("No existe la parte pública del usuario " + usuario);
				favoritosAlgunos = new FavoritoVO[0];
			}
		} catch (Exception e) {
			logger.error("Error la obtener <" + numero + "> de odes favoritos del usuario <" + usuario+">", e);
			throw new Exception("Error la obtener " + numero + " de odes favoritos del usuario " + usuario, e);
		}
		return favoritosAlgunos;
	}

	/**
	 * Metodo que lista  todos los grupos publicos haciendo la distinción entre aquellos en los que el usuario ya está y a los que se pude asociar
	 * @param usuario El usuario público
	 * @return Array de GrupoPublicoAsociadoVO-s, que es como un GrupoPublicoVO sólo que tiene un atributo que indica si el usuario que está haciendo
	 *  la consulta está asociado a ese grupo publico
	 * @throws Exception
	 */
	protected GrupoPublicoAsociadoVO[] handleListarGruposPublicosConAsociacion(
			String usuario) throws Exception {
		GrupoPublicoAsociadoVO[] grupos=null;
		try{
			logger.debug("Listado de grupos publicos del usuario <"+usuario+"> indicando si esta o no asociado");
			Collection<GrupoPublico> todosGrupos=this.getGrupoPublicoDao().loadAll();
			Collection<GrupoPublicoAsociadoVO> gruposAdmin=new ArrayList();
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null ){
				Collection<GrupoPublico> gruposUsuario=usuarioPublico.getGrupoPublico();
				logger.debug("Obtenemos todos los grupos");
				Iterator iter = todosGrupos.iterator();
				int l = 0;
				while (iter.hasNext())
				{
					GrupoPublicoAsociadoVO grupoAdmin=new GrupoPublicoAsociadoVO();
					GrupoPublico g = (GrupoPublico) (iter.next());
					grupoAdmin.setAdministrador(g.getAdministrador());
					grupoAdmin.setDescripcion(g.getDescripcion());
					grupoAdmin.setImagenGrupo(g.getImagenGrupo());
					grupoAdmin.setNombre(g.getNombre());
					grupoAdmin.setFechaCreacion(g.getFechaCreacion());
					grupoAdmin.setFechaModificacion(g.getFechaModificacion());
					grupoAdmin.setId(g.getId());
					Iterator iter2=gruposUsuario.iterator();
					int k=0;
					boolean encontrado=false;
					if((gruposUsuario == null)||(gruposUsuario.size() < 1))
					{
						grupoAdmin.setAsociado(Boolean.FALSE);
					}else
					{
						while(iter2.hasNext() && !encontrado){
							GrupoPublico grup=(GrupoPublico)(iter2.next());
							if(grup.getId().equals(g.getId())){
								logger.debug("Esta asociado con el grupo "+grup.getNombre());
								grupoAdmin.setAsociado(Boolean.TRUE);
			
								encontrado=true;
							}else{
								grupoAdmin.setAsociado(Boolean.FALSE);
							}
							k++;
						}
					}
					gruposAdmin.add(grupoAdmin);
					l++;
				}
				grupos=gruposAdmin.toArray(new GrupoPublicoAsociadoVO[0]);
			}else{
				logger.warn("El usuario no tiene parte pública "+usuario);
				grupos=new GrupoPublicoAsociadoVO[0];
			}
		}catch (Exception e) {
			logger.error("Error al listar todos los grupos especificando si el usuario "+usuario+" está asociado o no",e);
			throw new Exception("Error al listar todos los grupos especificando si el usuario "+usuario+" está asociado o no",e);
		}
		return grupos;
	}

	/**
	 * Metodo que lista  los últimos grupos creados por fecha
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarGruposCreadosUltimos() throws Exception {
		GrupoPublicoVO[] grupoVOs=null;
		try{
			logger.debug("Listado de los grupos publicos ordenados descendientemente por fecha de creacion");
			List<GrupoPublico> grupos=this.getGrupoPublicoDao().listarGruposCreadosUltimos();
			if(!(grupos == null))
			{
				logger.debug("Obtenemos "+grupos.size()+" grupos creados en orden inverso de creacion");
			}
			Iterator iter=grupos.iterator();
			Collection<GrupoPublicoVO> gruposPublicos=new ArrayList();
			int k=0;
	
			while(iter.hasNext()){
				GrupoPublico g = (GrupoPublico) (iter.next());
				GrupoPublicoVO grupoPu=this.getGrupoPublicoDao().toGrupoPublicoVO(g);
				gruposPublicos.add(grupoPu);
				k++;
			}
			grupoVOs=gruposPublicos.toArray(new GrupoPublicoVO[0]);
			
		}catch (Exception e) {
			logger.error("Error al listar  últimos grupos creados ",e);
			throw new Exception("Error al listar últimos grupos creados ",e);
		}
		return grupoVOs;
	}

	/**
	 * Metodo que lista  los últimos grupos modificados por fecha
	 * @return Array de GrupoPublicoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleListarGruposModificadosUltimos() throws Exception {
		GrupoPublicoVO[] grupoVOs=null;
		try{
			logger.debug("Listado de los grupos publicos ordenados descendientemente por fecha de modificacion");
			List<GrupoPublico> grupos=this.getGrupoPublicoDao().listarGruposModificadosUltimos();
			if(!(grupos == null))
			{
				logger.debug("Obtenemos "+grupos.size()+" grupos modificados en orden inverso de modificacion");
			}
			Iterator iter=grupos.iterator();
			Collection<GrupoPublicoVO> gruposPublicos=new ArrayList();
			int k=0;
	
			while(iter.hasNext()){
				GrupoPublico g = (GrupoPublico) (iter.next());
				GrupoPublicoVO grupoPu=this.getGrupoPublicoDao().toGrupoPublicoVO(g);
				gruposPublicos.add(grupoPu);
				k++;
			}
			grupoVOs=gruposPublicos.toArray(new GrupoPublicoVO[0]);
			
		}catch (Exception e) {
			logger.error("Error al listar últimos grupos modificados ",e);
			throw new Exception("Error al listar últimos grupos modificados ",e);
		}
		return grupoVOs;
	}

//	/**
//	 * Metodo que lista  los últimos favoritos añadidos a usuario
//	 * @param numero El numero de favoritos que queremos obtener
//	 * @throws Exception
//	 */
//	protected FavoritoVO[] handleListarNumeroUltimosFavoritosUsuario(
//			String usuario, Long numero) throws Exception {
//		Está mal, esta hecho con lo identificadores de la tabla, no vale........
//		Usuario usuarioEntity=this.getUsuarioDao().obtenerDatosUsuario(usuario);
//		UsuarioPublico usuarioPublico=usuarioEntity.getUsuarioPublico();
//		Collection<Favorito> favoritos=usuarioPublico.getFavoritoUsuario();
//		ArrayList<Favorito> arrayOrdenado=new ArrayList();
//		Collection<FavoritoVO> favoritosPublicos=new ArrayList();
//		int k=0;
//		Iterator iter=favoritos.iterator();
//		while(iter.hasNext()){
//			Favorito g = (Favorito) (iter.next());
//			arrayOrdenado.add(g);
//			k++;
//		}
//
//		Collections.reverse(arrayOrdenado);
//		int l=0;
//		Iterator iter2=arrayOrdenado.iterator();
//		while(iter2.hasNext() && l<numero){
//			Favorito g = (Favorito) (iter2.next());
//			FavoritoVO grupoPu=this.getFavoritoDao().toFavoritoVO(g);
//
//			favoritosPublicos.add(grupoPu);
//			l++;
//		}
//		FavoritoVO[] grupoVOs=(FavoritoVO[])favoritosPublicos.toArray(new FavoritoVO[0]);
//		logger.info("Hemos obtenido "+grupoVOs.length);
//		return grupoVOs;
//	}



	/**
     * Metodo que comprueba si un grupo publico nuevo o ya creado tiene un nombre de grupo que se intenta dar de alta.
     * 
     * @param nombreGrupo String. Nombre del grupo
     * @param id Long. Nos indica si estamos dando de alta a un nuevo grupo, nos llegará el -1, o si estamos modificando un grupo, en este caso nos llegará el identificador del grupo que queremos modificar
     * @return Boolean se devuelve true si existe un grupo con ese nombre y false si no existe
     * @throws java.lang.Exception
     */


	protected Boolean handleExisteNombreGrupo(String nombreGrupo, Long id)
			throws Exception {
		Boolean resultado = Boolean.FALSE;
		try{
			logger.debug("Existe nombre de grupo "+nombreGrupo);
			if(logger.isDebugEnabled())logger.debug("Estoy antes de comparar si el id es -1 (para alta) o no (para modificar)");
			if (id.compareTo(new Long("-1")) == 0)
			{
				//Comprobamos si nombre de nodo ya esta dado de alta en la BD
				ObtenerGruposPublicosPorNombreCriteria criteria = new ObtenerGruposPublicosPorNombreCriteria();
				criteria.setNombre(nombreGrupo);
				
				if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de NombreGrupo para hacer la comprobacion");
				logger.info("Nombre Grupo Publico = " + nombreGrupo);
				
				List<GrupoPublico> listaGrupos = this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
				
				if (listaGrupos == null || listaGrupos.size() == 0) {
					resultado = Boolean.FALSE;
				} else {
					resultado = Boolean.TRUE;
				}
			}else{
	
				GrupoPublicoVO grupos = this.obtenerGrupoPublico(nombreGrupo);
				ConsultarGrupoPorIDCriteria criterio=new ConsultarGrupoPorIDCriteria();
				criterio.setId(id);
				List<GrupoPublico> listaGruposIdentificador = this.getGrupoPublicoDao().obtenerGruposPorId(criterio);
				if(listaGruposIdentificador!=null && listaGruposIdentificador.size()>0){
					GrupoPublico grupoPublic=listaGruposIdentificador.get(0);
					String nombreGrupoBase=grupoPublic.getNombre();
					if(!nombreGrupoBase.equals(nombreGrupo)){
						logger.debug("Ha cambiado el nombre del grupo, se verifica que no coincida con uno ya existente");
						//Comprobamos si nombre de nodo ya esta dado de alta en la BD
						ObtenerGruposPublicosPorNombreCriteria criteria = new ObtenerGruposPublicosPorNombreCriteria();
						criteria.setNombre(nombreGrupo);
						if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de NombreGrupo para hacer la comprobacion");
						logger.info("Nombre Grupo Publico = " + nombreGrupo);
						List<GrupoPublico> listaGrupos = this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
						if(listaGrupos == null || listaGrupos.size() == 0)
						{
							resultado = Boolean.FALSE;
						}
						else
						{
							resultado = Boolean.TRUE;
						}
					}else{
						logger.info("No se ha cambiado el nombre del grupo");
						resultado = Boolean.FALSE;
					}
				}
				
			}
		}catch (Exception e) {
			logger.error("Error al verificar si existe un grupo con el mismo nombre",e);
			throw new Exception("Error al verificar si existe un grupo con el mismo nombre",e);
		}
		
		
		
		return resultado;
	}

	/**
     * Metodo que comprueba si se ha realizado la misma solicitud de antemano
     * 
     * @param nombreGrupo String. Nombre del grupo
     * @param usuario Nombre del usuario que realiza la petición
     * @return Boolean se devuelve true si existe una solicitud y false si no existe
     * @throws java.lang.Exception
     */
	protected Boolean handleExisteSolicitudGrupoIdentico(String nombreGrupo,
			String usuario) throws Exception {
		Boolean existe=Boolean.FALSE;
		try{
			logger.debug("Existe una solicitud para el grupo "+nombreGrupo+" del usuario "+usuario);
			ObtenerSolicitudesPorGrupoYSolicitanteCriteria criteria2=new ObtenerSolicitudesPorGrupoYSolicitanteCriteria();
			criteria2.setGrupo(nombreGrupo);
			criteria2.setSolicitante(usuario);
			List<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorGrupoYSolicitante(criteria2);
			if(solicitudes!=null && solicitudes.size()>0){
				logger.warn("Esta solicitud ya ha sido creada");
				existe=Boolean.TRUE;
			}
		}catch (Exception e) {
			logger.error("Error al intentar verificar si existe una solicitud identica", e);
			throw new Exception("Error al intentar verificar si existe una solicitud identica", e);
		}
		return existe;
	}

	/**
     * Metodo que comprueba si existe ese usuario en ese grupo
     * 
     * @param nombreGrupo String. Nombre del grupo
     * @param usuario Nombre del usuario que realiza la petición
     * @return Boolean se devuelve true si existe ese grupo y false si no existe
     * @throws java.lang.Exception
     */
	protected Boolean handleExisteGrupoEnUsuario(String usuario,
			String nombreGrupo) throws Exception {
		Boolean existe=Boolean.FALSE;

		try{
			logger.debug("Existe el usuario "+usuario+" en el grupo "+nombreGrupo);
			ObtenerGruposPublicosPorNombreCriteria criteria=new ObtenerGruposPublicosPorNombreCriteria();
			criteria.setNombre(nombreGrupo);
			List<GrupoPublico> grupo=this.getGrupoPublicoDao().obtenerGruposPublicosPorNombre(criteria);
			if(grupo!=null && grupo.size()>0){
				GrupoPublico grup=grupo.get(0);
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				if(usuarioPublico!=null){
					Collection<GrupoPublico> gruposUsuario=usuarioPublico.getGrupoPublico();
				
				
					Iterator iter = gruposUsuario.iterator();
					int k = 0;
					boolean encontrado=false;
					while (iter.hasNext()&& !encontrado)
					{
						
						GrupoPublico g = (GrupoPublico) (iter.next());
						if(grup.getId().equals(g.getId())){
							logger.warn("El grupo está asociado a ese usuario");
							encontrado=true;
						}
						k++;
					}
					if(encontrado){
						logger.warn("El grupo está asociado a ese usuario");
						existe=Boolean.TRUE;
					}
				}else{
					logger.warn("No existe ningún usuario con ese nombre "+usuario);
				}
			}else{
				logger.warn("No existe ningún grupo de ese nombre "+nombreGrupo);
			}
		}catch (Exception e) {
			logger.error("Error al intentar verificar si el grupo está en el usuario ",e);
			throw new Exception("Error al intentar verificar si el grupo está en el usuario ",e);
		}

		return existe;
	}

	/**
     * Metodo que comprueba si existe ese favorito en ese usuario
     * 
     * @param id_mec String. Identificador del ode
     * @param usuario Nombre del usuario que realiza la petición
     * @return Boolean se devuelve true si existe ese ode y false si no existe
     * @throws java.lang.Exception
     */
	
	protected Boolean handleExisteFavoritoEnUsuario(String id_mec,
			String usuario) throws Exception {
		Boolean existe=Boolean.FALSE;
		try{
			logger.debug("Tiene asociado el usuario "+usuario+" el favorito "+id_mec);
			ObtenerOdesFavoritosDeUsuarioPorIdCriteria criteria =new ObtenerOdesFavoritosDeUsuarioPorIdCriteria();
			criteria.setId_mec(id_mec);
			criteria.setUsuario(usuario);
			List listaOdes=this.getUsuarioDao().obtenerFavoritosUsuarioPorId(criteria);
			if(listaOdes!=null && listaOdes.size()>0){
				logger.warn("El usuario ya tiene el ode "+id_mec+" entre los favoritos");
				existe=Boolean.TRUE;
			}
		}catch (Exception e) {
			logger.error("Error al verificar si el ode que se quiere añadir al usuario existe ",e);
			throw new Exception("Error al verificar si el ode que se quiere añadir al usuario existe ",e);
		}
		return existe;
	}

	/**
     * Metodo que comprueba si existe ese ode en ese grupo
     * 
     * @param id_mec String. Identificador del ode
     * @param nombreGrupo Nombre del grupo al que se quiere asociar el ode
     * @return Boolean se devuelve true si existe ese ode y false si no existe
     * @throws java.lang.Exception
     */
	protected Boolean handleExisteOdeEnGrupo(String id_mec, String nombreGrupo)
			throws Exception {
		Boolean existe=Boolean.FALSE;
		try{
			logger.debug("Se comprueba si el ode esta ya en el grupo");
			ObtenerOdesDeGrupoPorIdentificadorCriteria criteriaId=new ObtenerOdesDeGrupoPorIdentificadorCriteria();
			criteriaId.setId_mec(id_mec);
			criteriaId.setNombre(nombreGrupo);
			List odesMismoId = this.getGrupoPublicoDao().obtenerOdesDeGrupoPorIdentificador(criteriaId);
			if(odesMismoId!=null && odesMismoId.size()>0){
				logger.warn("El mismo ode "+id_mec+" ya está asociado al grupo "+nombreGrupo);
				existe=Boolean.TRUE;
			}
		}catch (Exception e) {
			logger.error("Error al verificar si el ode que se quiere asociar la grupo ",e);
			throw new Exception("Error al verificar si el ode que se quiere asociar la grupo ",e);
		}
		return existe;
	}

	/**
     * Metodo que comprueba si existe ese contacto en la agenda de ese usuario
     * 
     * @param usuario String. Usuario
     * @param contacto Nombre del contacto al que se quiere añadir como contacto en la agenda del usuario
     * @return Boolean se devuelve true si existe ese contacto y false si no existe
     * @throws java.lang.Exception
     */
	protected Boolean handleExisteContactoEnAgendaDeUsuario(String usuario,
			String contacto) throws Exception {
		Boolean existe=Boolean.FALSE;
		try{
			logger.debug("Existe contacto en agenda");
			ObtenerContactosUsuarioPorNombreCriteria criteria=new ObtenerContactosUsuarioPorNombreCriteria();
			criteria.setUsuario(usuario);
			criteria.setUsuarioContacto(contacto);
			List<Usuario> contactosLista=this.getUsuarioDao().obtenerContactosPorNombreContacto(criteria);
			if(contactosLista!=null && contactosLista.size()>0){
				logger.warn("Ese contacto,"+contacto+", ya existe en la agenda del usuario "+usuario);
				existe=Boolean.TRUE;
			}
			}catch (Exception e) {
				logger.error("Error al verificar si ese contacto se encuentra en la agenda de ese usuario",e);
				throw new Exception("Error al verificar si ese contacto se encuentra en la agenda de ese usuario",e);
			}

		return existe;
	}

	/**
     * Metodo para eliminar el usuario publico del usuario que se le pasa
     * 
     * @param usuario String. Usuario
     * @return ResultadoOperacionVO indicando el nombre del usuario y si la eliminacion ha sido correcta
     * @throws java.lang.Exception
     */
	protected ResultadoOperacionVO handleEliminarUsuarioPublico(String usuario)
			throws Exception {
		ResultadoOperacionVO resultado=new ResultadoOperacionVO();
		resultado.setNombre(usuario);
		try{
			logger.debug("Eliminacion usuario publico");
			Usuario usuarioEntity=this.getUsuarioDao().obtenerDatosUsuario(usuario);
			if(usuarioEntity!=null){
				UsuarioPublico usuarioPublicoEntity=usuarioEntity.getUsuarioPublico();
				if(usuarioPublicoEntity!=null){
					Collection<Contacto> contactosUsuario=usuarioPublicoEntity.getContacto();
					Collection<Favorito> favoritosUsuario=usuarioPublicoEntity.getFavoritoUsuario();
					Collection<GrupoPublico> gruposUsuario=usuarioPublicoEntity.getGrupoPublico();
					String fotoUsuario=usuarioPublicoEntity.getFoto();
					if(logger.isDebugEnabled())logger.debug("Tenemos "+gruposUsuario+" grupos para borrar, tanto si es administrador como asociado, "+favoritosUsuario+" favoritos del usuario y" +
							""+contactosUsuario+" contactos del usuario");
					int k = 0;
					Iterator iter = gruposUsuario.iterator();
					logger.info("Se eliminan los grupos...");
					while (iter.hasNext())
					{
						
						GrupoPublico g = (GrupoPublico) (iter.next());
						if(g.getAdministrador().equals(usuarioEntity.getUsuario())){
							//Eliminamos todos los grupos donde es el administrador y los odes
							if(logger.isDebugEnabled())logger.debug("Es el administrador del grupo "+g.getNombre());
							Long[] ids=new Long[1];
							ids[0]=g.getId();
							ResultadoOperacionVO[] vuelta = this.eliminarGrupoPublicoPorUsuario(ids,usuario);
							Boolean eliminadoGrupo=vuelta[0].getResultado();
							if(logger.isDebugEnabled())logger.debug("El grupo se ha eliminado?"+eliminadoGrupo);
						}else{
							//Eliminamos el usaurio que borramos del grupo
							if(logger.isDebugEnabled())logger.debug("No es el administrador");
							String[] usuarios=new String[1];
							usuarios[0]=usuario;
							ResultadoOperacionVO[] resultadoUsuario=this.eliminarUsuarioDeGrupo(usuarios, g.getNombre());
							Boolean eliminadoUsaurio=resultadoUsuario[0].getResultado();
							if(logger.isDebugEnabled())logger.debug("El usuario se ha eliminado?"+eliminadoUsaurio);
						}
						k++;
					}
					int l = 0;
					Iterator iter2 = favoritosUsuario.iterator();
					Collection<String> identificadores=new ArrayList();
					Collection<String> titulos=new ArrayList();
					logger.debug("Se eliminan los favoritos");
					while (iter2.hasNext())
					{
						//Eliminamos los favoritos del usuario
						
						Favorito g = (Favorito) (iter2.next());
						identificadores.add(g.getId_mec());
						titulos.add(g.getTitulo());
						l++;
					}
					String[] identif=identificadores.toArray(new String[0]);
					String[] titu=titulos.toArray(new String[0]);
					ResultadoOperacionVO[] resultadoFavoritos=this.eliminarFavoritoDeUsuario(identif, usuario,titu);
					int m = 0;
					Iterator iter3 = contactosUsuario.iterator();//Son los usuarios que tiene en su agenda
					Collection<Long> contactosColection=new ArrayList();
					while (iter3.hasNext())
					{
						//Eliminamos los contactos del usuario
						Contacto g = (Contacto) (iter3.next());
						contactosColection.add(g.getId());
						
						m++;
					}
					Long[] contactos=contactosColection.toArray(new Long[0]);
					ResultadoOperacionVO[] resultadoContactos=this.eliminarContactoDeAgenda(contactos, usuario);
					//Tenemos que eliminar ese contacto y limpiar todas las agendas.
					UsuarioCorreoVO[] listaUsuarios=this.listarUsuariosConContacto(usuario);
					ObtenerContactosPorNombreCriteria criteriaUsuario=new ObtenerContactosPorNombreCriteria();
					criteriaUsuario.setNombreContacto(usuario);
					List<Contacto> contact = this.getContactoDao().obtenerContactosPorNombre(criteriaUsuario);//El usuario a borrar como contacto
					Contacto nuevoContactoUsuario=null;
					Long[] idUsaurioContacto=new Long[1];
					if(contact!=null && contact.size()>0){
						nuevoContactoUsuario=contact.get(0);
						
						idUsaurioContacto[0]=nuevoContactoUsuario.getId();
					}
					for(int i=0;i<listaUsuarios.length;i++){
						String usuarioDeLista=listaUsuarios[i].getUsuario();
						ResultadoOperacionVO[] resultadosContactoLista=this.eliminarContactoDeAgenda(idUsaurioContacto, usuarioDeLista);	
					}
					this.getContactoDao().remove(nuevoContactoUsuario);
					//Obtenemos las solicitudes realizadas por otros usuarios a los grupos donde el usuario es el administrador
					ObtenerSolicitudesPorAdministradorCriteria criteria=new ObtenerSolicitudesPorAdministradorCriteria();
					criteria.setAdministrador(usuario);
					List<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorAdministrador(criteria);
					if(logger.isDebugEnabled())logger.debug("Obtenemos "+solicitudes+" solicitudes realizadas por los usuarios a los grupos del usuario");
					for(int i=0;i<solicitudes.size();i++){
						this.getSolicitudGrupoDao().remove(solicitudes.get(i));
					}
					//Tenemos que eliminar las solicitudes hechas por el
					//Obtenemos las solicitudes de ingreso que ha realizado el usuario a otros grupos
					ObtenerSolicitudesDeUsuarioCriteria criteriaSolicitudes=new ObtenerSolicitudesDeUsuarioCriteria();
					criteriaSolicitudes.setUsuarioSolicitante(usuario);
					List<SolicitudGrupo> solicitudesSolicitante=this.getSolicitudGrupoDao().obtenerSolicitudesPorUsuario(criteriaSolicitudes);
					if(logger.isDebugEnabled())logger.debug("El usuario hizo "+solicitudesSolicitante+" solicitudes a otros grupos");
					for(int j=0;j<solicitudesSolicitante.size();j++){
						this.getSolicitudGrupoDao().remove(solicitudesSolicitante.get(j));
					}
					
					//Eliminamos el usuario de la tabla
					if(logger.isDebugEnabled())logger.debug("Borramos el usuario  publico "+usuarioPublicoEntity.getId()+" y refrescamos el usuario");
					this.getUsuarioPublicoDao().remove(usuarioPublicoEntity);
					usuarioEntity.setUsuarioPublico(null);
					this.getUsuarioDao().update(usuarioEntity);
					//Tenemos que eliminar la imagen del usuario publico
					//AgregaProperties agregaProperties = Agrega PropertiesImpl.getInstance();
		    		
					if(logger.isDebugEnabled())logger.debug("Vamos a borrar la imagen "+fotoUsuario);
					if(fotoUsuario!=null && !fotoUsuario.equals("") && !fotoUsuario.equals(getPropertyValue("nombre.imagen.defecto.usuario"))){
						String nombreNodo="";
						int posicion=fotoUsuario.lastIndexOf("/");
						if(posicion>1){//Vamos a recoger el nombre del nodo sin espacios ni tildes
							nombreNodo=fotoUsuario.substring(0, posicion);
						}
						String rutaImagenAntiguo=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+nombreNodo;
						if(logger.isDebugEnabled())logger.debug("Vamos a borrar la ruta de las imagenes "+rutaImagenAntiguo);
						File rutaBorrar=new File(rutaImagenAntiguo);
						UtilesFicheros.eliminar(rutaBorrar);
						logger.debug("Hemos borrado todas las rutas");
					}
					resultado.setResultado(Boolean.TRUE);
				}else{
					logger.warn("No existe parte pública para el usuario "+usuario);
					resultado.setResultado(Boolean.FALSE);
				}
			}else{
				logger.warn("No existe el usuario "+usuario);
				resultado.setResultado(Boolean.FALSE);
			}

		}catch (Exception e) {
			logger.error("Error al eliminar el usuario publico del usuario "+usuario,e);
//			throw new Exception("Error al eliminar el usuario publico del usuario "+usuario,e);
			resultado.setResultado(Boolean.FALSE);
		}
		return resultado;
		
	}

	private String concatenarBusqueda (String texto) throws Exception{
		try {
			logger.debug("usuarioContacto" + texto);
			StringBuffer busqueda = new StringBuffer("%");
         		StringTokenizer token = new StringTokenizer(texto, " ");
 					while ((token.hasMoreElements())){
 						busqueda.append(token.nextElement().toString()).append("%");
 					}
 					logger.debug("La busqueda se realizará por los siguientes criterios: " + busqueda.toString());
 				return busqueda.toString();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	
	/**
     * Metodo para eliminar todos los favoritos que se pasan de todos los usuarios que lo tenga añadido como favorito, y todas
     * las referencias a ellos
     * 
     * @param id_mecs String[]. Los identificadores de los odes favoritos que se van a eliminar
     * @return Boolean Indica si se ha borrado correctamente o no
     * @throws java.lang.Exception
     */
	private ResultadoOperacionVO[] eliminarFavoritosTodosUsuarios(String[] id_mecs)
			throws Exception {
		ResultadoOperacionVO[] todosResultados=null;
		Collection<ResultadoOperacionVO> coleccion=new ArrayList();
		Boolean borrado=Boolean.FALSE;
		for(int i=0;i<id_mecs.length;i++){
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			resultado.setNombre(id_mecs[i]);
			try{
				ObtenerUsuariosConFavoritoCriteria criteria=new ObtenerUsuariosConFavoritoCriteria();
				criteria.setId_mec(id_mecs[i]);
				if(logger.isDebugEnabled())logger.debug("Obtenemos los usuarios que tengan el ode "+id_mecs[i]);
				List<UsuarioPublico> usuariosConOde=this.getUsuarioPublicoDao().obtenerUsuariosPublicosConFavorito(criteria);
				Iterator iter=usuariosConOde.iterator();
				Favorito odeFavorito=null;
				ObtenerFavoritosPorIdCriteria criteria2=new ObtenerFavoritosPorIdCriteria();
				criteria2.setId_mec(id_mecs[i]);
				if(logger.isDebugEnabled())logger.debug("Recogemos los favoritos de identificador "+id_mecs[i]);
				List<Favorito> odesFavorito=this.getFavoritoDao().obtenerFavoritosPorId(criteria2);
				if(odesFavorito!=null){
					if(logger.isDebugEnabled())logger.debug("Recibimos "+odesFavorito.size()+" componentes de favoritos");
					odeFavorito=odesFavorito.get(0);
				}
				int k=0;
				logger.debug("Vamos a borrar el ode favorito de identificador "+odeFavorito.getId_mec()+" y titulo "+odeFavorito.getTitulo());
				while(iter.hasNext()){
					
					UsuarioPublico usuario=(UsuarioPublico)iter.next();
					Collection<OdeGrupo> favoritos=usuario.getFavoritoUsuario();

					if(logger.isDebugEnabled())logger.debug("Se borran todas las entradas de ese ode en el usuario <"+usuario.getUsuario()+"> con <"+favoritos.size()+"> favoritos/odes");
					favoritos.remove(odeFavorito);
					if(logger.isDebugEnabled())logger.debug("Al eliminar se quedan <"+favoritos.size()+"> odes");
					usuario.setFavoritoUsuario(favoritos);
					this.getUsuarioPublicoDao().update(usuario);
				}

				this.getFavoritoDao().remove(odeFavorito);
				resultado.setResultado(Boolean.TRUE);
			}catch (Exception e) {
				logger.error("Error al eliminar el ode favorito "+id_mecs[i],e);
				resultado.setResultado(Boolean.FALSE);
			}
			coleccion.add(resultado);
		}
		todosResultados=coleccion.toArray(new ResultadoOperacionVO[0]);
		return todosResultados;
		
	}

	/**
     * Metodo para eliminar todos los odes que se pasan de todos los grupo publicos que lo tenga añadido como el ode, y todas
     * las referencias a ellos
     * 
     * @param id_mecs String[]. Los identificadores de los odes añadidos a los grupos publicos que se van a eliminar
     * @return Boolean indicando si el borrado ha sido correcto, si falla alguno devuelve false
     * @throws java.lang.Exception
     */
	private ResultadoOperacionVO[]  eliminarODEDeTodosGrupos(String[] id_mecs)
			throws Exception {
		ResultadoOperacionVO[] todosResultados=null;
		Collection<ResultadoOperacionVO> coleccion=new ArrayList();
		Boolean borrado=Boolean.FALSE;
		for(int i=0;i<id_mecs.length;i++){
			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			resultado.setNombre(id_mecs[i]);
			try{
				ObtenerGruposConOdeCriteria criteria=new ObtenerGruposConOdeCriteria();
				criteria.setId_mec(id_mecs[i]);
				if(logger.isDebugEnabled())logger.debug("Obtenemos los grupos del ode "+id_mecs[i]);
				List<GrupoPublico> gruposConOde=this.getGrupoPublicoDao().obtenerGrupoConOde(criteria);
				Iterator iter=gruposConOde.iterator();
				ObtenerOdesGrupoPorIdCriteria criteria2=new ObtenerOdesGrupoPorIdCriteria();
				criteria2.setId_mec(id_mecs[i]);
				if(logger.isDebugEnabled())logger.debug("Recogemos los odes de identificador "+id_mecs[i]);
				List<OdeGrupo> odesAsociados=this.getOdeGrupoDao().obtenerOdesGrupo(criteria2);
				OdeGrupo odeAsociado=null;
				if(odesAsociados!=null && odesAsociados.size()>0){
					odeAsociado=odesAsociados.get(0);
					if(logger.isDebugEnabled())logger.debug("Vamos a eliminar el ode de identificador "+odeAsociado.getId_mec()+" y titulo "+odeAsociado.getTitulo());
				}
				int k=0;
				while(iter.hasNext()){
					GrupoPublico grupo=(GrupoPublico)iter.next();
					Collection<OdeGrupo> odes=grupo.getOdeGrupo();
					logger.info("Borramos todas las entradas del grupo <"+grupo.getNombre()+"> que antes tenia <"+odes.size()+">");
					odes.remove(odeAsociado);
					if(logger.isDebugEnabled())logger.debug("Ahora le quedan "+odes.size());
					grupo.setOdeGrupo(odes);
					logger.debug("Refrescamos el grupo");
					this.getGrupoPublicoDao().update(grupo);
				}
				if(logger.isDebugEnabled())logger.debug("Eliminamos el ode asociado");
				this.getOdeGrupoDao().remove(odeAsociado);
				resultado.setResultado(Boolean.TRUE);
			}catch (Exception e) {
				logger.error("Error eliminando el ode "+id_mecs[i]+" de los grupos",e);
				resultado.setResultado(Boolean.FALSE);
			}
			coleccion.add(resultado);
		}
		todosResultados=coleccion.toArray(new ResultadoOperacionVO[0]);
		return todosResultados;
	}

	/**
     * Metodo que devuelve todos los grupos publicos asociados que contengan ese ode
     * @param usuario String. El usuario
     * @param id_mecs String[]. Identificadores de odes que se quiere buscar en los grupos asociados del usuario
     * @return OdeConGruposVO[] Array de VOs que cada VO contendrá los grupos asociados a usuario que contengan el ode especificado con el identificador del ode
     * @throws java.lang.Exception
     */
	protected OdeConGruposVO[] handleListadoGruposConAsociacionPorUsuarioYOde(String usuario, String[] id_mecs)
			throws Exception {
		GrupoPublicoVO[] grupoVOs=null;
		OdeConGruposVO[] odesGrupos=null;
		try{
			Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario);
			
			if(usuarioEntity!=null){
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				
				if(usuarioPublico!=null){
					Collection<GrupoPublico> gruposUsuario=usuarioPublico.getGrupoPublico();
					Collection<OdeConGruposVO> gruposOdes=new ArrayList();
					for(int i=0;i<id_mecs.length;i++){
						
						//if(logger.isDebugEnabled())logger.debug("Vamos a buscar los grupos asociados que contengan el identificador "+id_mecs[i]);
						OdeConGruposVO odeGrupo=new OdeConGruposVO();
						odeGrupo.setId_mec(id_mecs[i]);
						Iterator iter=gruposUsuario.iterator();
						Collection<GrupoPublicoVO> gruposPublicos=new ArrayList();
						int k=0;
						
						while(iter.hasNext() ){
							GrupoPublico g = (GrupoPublico) (iter.next());
							Collection<OdeGrupo> odesGrupo=g.getOdeGrupo();
							Iterator iter2=odesGrupo.iterator();
							int l=0;
							boolean encontrado=false;
							
							while(iter2.hasNext()&& !encontrado){
								OdeGrupo ode=(OdeGrupo)(iter2.next());
								if(ode.getId_mec().equals(id_mecs[i])){
									encontrado=true;
									GrupoPublicoVO grupoVO=this.getGrupoPublicoDao().toGrupoPublicoVO(g);
									if(!gruposPublicos.contains(grupoVO)){
										gruposPublicos.add(grupoVO);
									}
								}
								l++;
							}
							k++;
						}
						grupoVOs=gruposPublicos.toArray(new GrupoPublicoVO[0]);
						odeGrupo.setGruposAsociados(grupoVOs);
						gruposOdes.add(odeGrupo);
					}
					odesGrupos=gruposOdes.toArray(new OdeConGruposVO[0]);
				}else{
					logger.warn("No existe el usuarioPublico "+usuario);
					odesGrupos=new OdeConGruposVO[0];
				}
			}else{
				logger.warn("No existe el usuario "+usuario);
				odesGrupos=new OdeConGruposVO[0];
			}
		}catch (Exception e) {
			logger.error("Error al listar todos los grupos asociados al usuario que contengan ese ode ",e);
			throw new Exception("Error al listar todos los grupos asociados al usuario que contengan ese ode ",e);
		}
		return odesGrupos;
	}

	

	private static String borrarAcentosYEspacios(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("borrarAcentosYEspacios: Eliminar acentos para <"+s+">");
			s = s.replaceAll( "[ÂÀÄÁÃ]", "A" );
			s = s.replaceAll( "[ÊÈËÉ]", "E" );
			s = s.replaceAll( "[ÎÌÏÍ]", "I" );
			s = s.replaceAll( "[ÔÒÖÓÕ]", "O" );
			s = s.replaceAll( "[ÛÙÜÚ]", "U" );
			s = s.replaceAll( "Ç", "C" );
			s = s.replaceAll( "[àâäá]", "a" );
			s = s.replaceAll( "[éèêë]", "e" );
			s = s.replaceAll( "[ïîìí]", "i" );
			s = s.replaceAll( "[ôöòóõ]", "o" );
			s = s.replaceAll( "[ûüùú]", "u" ); 
			s = s.replaceAll( "ç", "c" );
			s = s.replaceAll( "‘", "'");
			if (logger.isDebugEnabled())logger.debug("borrarAcentosYEspacios: Acentos eliminados= <"+s+"> Se procede a eliminar sus espacios.");
			s= s.replaceAll(" ", "_");
			if (logger.isDebugEnabled())logger.debug("borrarAcentosYEspacios: Espacios eliminados= <"+s+">");
			return s;
		} catch ( Exception ex ) {
			logger.error("borrarAcentosYEspacios: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}

	/**
	 * @param sKey
	 * @return String
	 */
	public String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = SrvPerfilPublicoBase.class
						.getResourceAsStream(FILE_NAME_PROPERTIES);
				Properties prop = new java.util.Properties();
				prop.load(fIsSpringProperties);
				props=prop;
			}
			sReturn = props.getProperty(sKey);
			if (logger.isDebugEnabled())
				logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.error("Excepcion intentando obtener propiedad <" + sKey
					+ "> del fichero de propiedades del buscar <" + e.getCause() + ">");
		}
		// devolvemos la propiedad
		return sReturn;
	}

	/**
	 * Metodo para obtener el ususario publico de un usuario
	 * @param usuario usuario de la plataforma del que queremos saber cual es su usario publico
	 * @return UsuarioPublicoVO El usuario publico
	 * @throws Exception
	 */
	protected UsuarioPublicoVO handleObtenerUsuarioPublico(String usuario)
			throws Exception {
		UsuarioPublicoVO usuarioPublicoVO=new UsuarioPublicoVO();
		try{
			UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
			if(usuarioPublico!=null)
				usuarioPublicoVO=this.getUsuarioPublicoDao().toUsuarioPublicoVO(usuarioPublico);
		}catch (Exception e) {
 			logger.error("Error al obtener el usuario publico del usuario  "+usuario ,e);
			throw new Exception("Error al obtener el usuario publico del usuario "+usuario,e);
		}
		return usuarioPublicoVO;
	}

	

	/**Lista todos los usuarios de un grupo  que deben recibir el correo
	 * @param nombreGrupo Nombre del grupo
	 * @return UsuarioCorreoVO[] Array de UsuarioCorreoVO que contiene la información necesaria para informar a los usuarios de un grupo de los cambios
	 * @throws Exception
	 */
	protected UsuarioCorreoVO[] handleListarUsuariosDeGrupoCorreo(
			String nombreGrupo) throws Exception {
		UsuarioCorreoVO[] usuariosVO=null;
		try{
			if (logger.isDebugEnabled());logger.debug("Listar usuarios que pertenecen a un grupo "+nombreGrupo);
//			ObtenerUsuariosPublicosPorNombreGrupoCriteria criteria=new ObtenerUsuariosPublicosPorNombreGrupoCriteria();
//			criteria.setNombre(nombreGrupo);
//			List<UsuarioPublico> vuelta=this.getUsuarioPublicoDao().obtenerUsuariosPublicosPorNombreGrupo(criteria);
			
			List<Usuario> vuelta=this.getUsuarioDao().obtenerUsuariosDeGrupoYRecibirCorreo(nombreGrupo);
			if (logger.isDebugEnabled());logger.debug("Obtenemos <"+vuelta.size()+"> usuarios en el grupo que van a obtener el mail");
			Iterator iter=vuelta.iterator();
			Collection<UsuarioCorreoVO> usuarios=new ArrayList();
			int k=0;
			
			while(iter.hasNext() ){
				Usuario g = (Usuario) (iter.next());
//				if(g.getRecibirCorreo().equals(Boolean.TRUE)){
					UsuarioCorreoVO correo=new UsuarioCorreoVO();
					correo.setEmail(g.getEmail());
					correo.setRecibeCorreo(Boolean.TRUE);
					correo.setUsuario(g.getUsuario());
					//logger.debug("Obtenemos el usuario "+g.getUsuario());
					usuarios.add(correo);
//				}
				k++;
			}
			usuariosVO=usuarios.toArray(new UsuarioCorreoVO[0]);
			logger.info("Obtenemos <"+usuariosVO.length+"> usuarios en el grupo");

		}catch (Exception e) {
			logger.error("Error al listar los usuarios del grupo "+nombreGrupo, e);
			throw new Exception("Error al listar los usuarios del grupo "+nombreGrupo, e);
		}
		return usuariosVO;
	}

	/**
	 * Método para eliminar un ode de la parte del perfil publico, tanto de odes favoritos como asociados a grupos
	 * @param id_mecs Identificadores de los odes que se quieren eliminar de laparte publica
	 * @return ResultadoOperacionVO[] Array de VO-s que contiene el identificador del ode y si se ha eliminado correctamente 
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleEliminarODEPerfilPublico(
			String[] id_mecs) throws Exception {
		ResultadoOperacionVO[] resultados=null;
		if(id_mecs!=null && id_mecs.length>0){
			resultados=new ResultadoOperacionVO[id_mecs.length];
			try{
				if(logger.isDebugEnabled())logger.debug("Vamos a eliminar <"+id_mecs.length+"> odes");
					ResultadoOperacionVO[] favoritos=eliminarFavoritosTodosUsuarios(id_mecs);
					ResultadoOperacionVO[] grupos=eliminarODEDeTodosGrupos(id_mecs);
					
					for(int i=0;i<id_mecs.length;i++){
						ResultadoOperacionVO resultado=new ResultadoOperacionVO();
						resultado.setNombre(id_mecs[i]);
						if(favoritos[i].getResultado().booleanValue() && grupos[i].getResultado().booleanValue()){
							resultado.setResultado(Boolean.TRUE);
						}else{
							resultado.setResultado(Boolean.FALSE);
						}
						resultados[i]=resultado;
					}
				
			}catch (Exception e) {
				logger.error("Error en la eliminacion de los odes del perfil publico - ",e);
			}
		}else{
			logger.warn("No ha llegado ningún identificador");
			resultados=new ResultadoOperacionVO[0];
			
		}
		return resultados;
	}


	/**
	 * Metodo para obtener los grupos publicos
	 * @param ids Los identificadores de los grupos
	 * @return GrupoPublicoVO[]
	 * @throws Exception
	 */
	protected GrupoPublicoVO[] handleObtenerGruposPublicosPorIdentificador(
			Long[] ids) throws Exception {
		GrupoPublicoVO[] gruposPublicoVO=null;

		Collection<GrupoPublicoVO> gruposCollection=new ArrayList();
		logger.debug("Obtener grupos publicos por identificador");
		if((ids == null)|| (ids.length < 1))
		{
			logger.warn("Los identificadores de los grupos publicos son nulos");
		}else
		{
			for(int i=0;i<ids.length;i++){
				
				GrupoPublicoVO grupoPublicoVO=new GrupoPublicoVO();
				try{
					GrupoPublico grupoPublic=this.getGrupoPublicoDao().load(ids[i]);
					if(grupoPublic!=null){
						grupoPublicoVO=this.getGrupoPublicoDao().toGrupoPublicoVO(grupoPublic);
						
					}else{
						logger.warn("No existe ningun grupo publico de identificador "+ids[i]);
						grupoPublicoVO=new GrupoPublicoVO();
					}
				}catch (NumberFormatException ex)
				{
					logger.error("Error al obtener el grupo publico con el identificador "+ids[i],ex);
				}catch (Exception e) {
					logger.error("Error al obtener el grupo publico de identificador "+ids[i],e);
					//throw new Exception("Error al obtener el grupo publico de identificador "+ids[i],e);
				}
				gruposCollection.add(grupoPublicoVO);
			}
			gruposPublicoVO=gruposCollection.toArray(new GrupoPublicoVO[0]);
		}
		return gruposPublicoVO;
	}

	/**
	 * Método para listar todas las solicitudes de ingreso en algún grupo realizadas por un usuario
	 * @param solicitante El administrador del grupo
	 * @return Array de SolicitudGrupoVO-s
	 * @throws Exception
	 */
	
	protected SolicitudGrupoVO[] handleListarSolicitudesRealizadasPorUsuario(
			String solicitante) throws Exception {
		SolicitudGrupoVO[] solicitudesVO=null;
		try{
			Usuario usuarioEntity=this.getUsuarioDao().obtenerDatosUsuario(solicitante);
			if(!(usuarioEntity == null))
			{
				ObtenerSolicitudesPorSolicitanteCriteria criteria=new ObtenerSolicitudesPorSolicitanteCriteria();
				criteria.setSolicitante(solicitante);
				Collection<SolicitudGrupo> solicitudes=this.getSolicitudGrupoDao().obtenerSolicitudesPorSolicitante(criteria);
				Collection<SolicitudGrupoVO> collectionSolicitudes=new ArrayList();
				if(!(solicitudes == null))
				{
					logger.info("Hemos obtenido "+solicitudes.size()+" solicitudes para el usuario "+solicitante);
				}
				Iterator iter = solicitudes.iterator();
				int k = 0;
				
				while (iter.hasNext())
				{
					SolicitudGrupo g = (SolicitudGrupo) (iter.next());
					SolicitudGrupoVO solicitud=this.getSolicitudGrupoDao().toSolicitudGrupoVO(g);
					collectionSolicitudes.add(solicitud);
					k++;				
				}
				solicitudesVO=collectionSolicitudes.toArray(new SolicitudGrupoVO[0]);
			}else
			{
				logger.warn("El solicitante "+solicitante+" no tiene perfil publico");
			}
		}catch (Exception e) {
			logger.error("Error al listar las solictudes para el solicitante "+solicitante, e);
			throw new Exception("Error al listar las solictudes para el solicitante "+solicitante, e);
		}
		return solicitudesVO;
	}

	/**
	 * Método para buscar todos los grupos que se asemejen al nombre dado
	 * @param nombreGrupo Nombre de grupo de búsqueda
	 * @param usuario El usuario que hace la consulta
	 * @return Array de GrupoPublicoAsociadoVO-s
	 * @throws Exception
	 */
	protected GrupoPublicoAsociadoVO[] handleBuscarGruposPorNombre(
			String nombreGrupo, String usuario) throws Exception {
		GrupoPublicoAsociadoVO[] gruposAdmin=null;
		try{
			if (logger.isDebugEnabled())logger.debug("Buscar grupos por nombre '"+nombreGrupo+"'");
			if(!(nombreGrupo == null) && !(nombreGrupo.length() == 0) && !(nombreGrupo.equals(" ")))
			{
				String textoBuscar=concatenarBusqueda(nombreGrupo);
				BuscarGruposPorNombreCriteria criteria=new BuscarGruposPorNombreCriteria();
				criteria.setNombre(textoBuscar);
				List<GrupoPublico> grupos=this.getGrupoPublicoDao().buscarGruposPorNombre(criteria);
				Collection<GrupoPublicoAsociadoVO> collectionGrupos=new ArrayList();
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				if(usuarioPublico!=null){
					Collection<GrupoPublico> gruposPublicos=usuarioPublico.getGrupoPublico();
					if(!(grupos == null))
					{
						logger.debug("Hemos obtenido "+grupos.size()+" grupos que tienen una decripcion parecida a "+nombreGrupo);
					}
					Iterator iter = grupos.iterator();
					int k = 0;
					
					while (iter.hasNext())
					{
						GrupoPublico g = (GrupoPublico) (iter.next());
						GrupoPublicoAsociadoVO grupoAdmin=new GrupoPublicoAsociadoVO();
						grupoAdmin.setAdministrador(g.getAdministrador());
						grupoAdmin.setDescripcion(g.getDescripcion());
						grupoAdmin.setImagenGrupo(g.getImagenGrupo());
						grupoAdmin.setNombre(g.getNombre());
						grupoAdmin.setFechaCreacion(g.getFechaCreacion());
						grupoAdmin.setFechaModificacion(g.getFechaModificacion());
						grupoAdmin.setId(g.getId());
						
						if(gruposPublicos.contains(g)){
							grupoAdmin.setAsociado(Boolean.TRUE);
							
						}else{
							grupoAdmin.setAsociado(Boolean.FALSE);
						}
						logger.debug("El grupo "+grupoAdmin.getNombre()+" es asociado? "+grupoAdmin.getAsociado());
						collectionGrupos.add(grupoAdmin);
						k++;				
					}
					gruposAdmin=collectionGrupos.toArray(new GrupoPublicoAsociadoVO[0]);
				}else{
					logger.warn("No existe parte pública para ese usuario "+usuario);
					gruposAdmin=new GrupoPublicoAsociadoVO[0];
				}
			}else
			{
				logger.debug("El nombre del grupo sobre el que se desea buscar es nulo o vacio");
			}
		}catch (Exception e) {
			logger.error("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+nombreGrupo,e);
			throw new Exception("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+nombreGrupo,e);
		}
		return gruposAdmin;
	}

	/**Lista todos los usuarios que tenga el contacto en su agenda y quieren recibir el correo
	 * @param contacto El contacto
	 * @return UsuarioCorreoVO[] Array de UsuarioCorreoVO que contiene la información necesaria para informar a los usuarios de un grupo de los cambios
	 * @throws Exception
	 */
	protected UsuarioCorreoVO[] handleListarUsuariosConContacto(String contacto)
			throws Exception {
		UsuarioCorreoVO[] usuariosVO=null;
		try{
			List<Usuario> vuelta=this.getUsuarioDao().obtenerUsuariosConteganContacto(contacto);
			Iterator iter=vuelta.iterator();
			Collection<UsuarioCorreoVO> usuarios=new ArrayList();
			int k=0;
			
			while(iter.hasNext() ){
				Usuario g = (Usuario) (iter.next());
				UsuarioCorreoVO correo=new UsuarioCorreoVO();
				correo.setEmail(g.getEmail());
				correo.setRecibeCorreo(Boolean.TRUE);
				correo.setUsuario(g.getUsuario());

				usuarios.add(correo);
				k++;
			}
			usuariosVO=usuarios.toArray(new UsuarioCorreoVO[0]);

		}catch (Exception e) {
			logger.error("Error al listar los usuarios que contengan en su agenda el contacto "+contacto, e);
			throw new Exception("Error al listar los usuarios que contengan en su agenda el contacto "+contacto, e);
		}
		return usuariosVO;
	}

	
	private Boolean guardarImagenUsuarioPublico(String nombre, DataHandler contenidoImagen, String nombreDeImagen) throws IOException{
		Boolean resultado=Boolean.TRUE;


		//AgregaProperties agregaProperties = Agrega PropertiesImpl.getInstance();
		DataHandler dataHandler =null;
		try{

			dataHandler = contenidoImagen;
			logger.debug("El contenido de la imagen "+dataHandler);
		}catch(Exception e){
			logger.error("Error en la creacion del datahandler",e);
			resultado=Boolean.FALSE;
		}
		String tamaño=getPropertyValue("imagen.tamanioPequenio");
		String pathFileTarget="";
		try{
			String pathFileTargetUno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+nombre;
			pathFileTarget=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+nombre+"/"+nombreDeImagen;
			//+extensionImagen;
			logger.debug("Se dejara la imagen redimensionada finalmente en "+pathFileTarget);
			File pathFileUno=new File(pathFileTargetUno);
			boolean creado=false;
			if(!pathFileUno.exists()){
				creado=pathFileUno.mkdir();
			}else{
				creado=true;
				logger.debug("La carpeta existia "+pathFileUno);
			}
			if(creado){
				creado=false;
				File pathFile=new File(pathFileTarget);
				pathFile.createNewFile();
				if(!pathFile.exists()){
					logger.warn("No se ha creado el archivo para guardar la imagen del usuario "+pathFile);
					return Boolean.FALSE;
				}else{
					creado=true;
					logger.debug("La carpeta con la imagen del usuario ya existia "+pathFile);
				}
			}else{
				logger.warn("Error en la creacion de la carpeta para almacenar la carpeta del usuario "+pathFileUno);
				return Boolean.FALSE;
			}
			
		}catch(Exception ei){
			logger.error("Error en la creacion de la carpeta final para almacenar la imagen del usuario "+ei);
			return Boolean.FALSE;
		}
		File fDestinoUno=null;
		File fDestino=null;
		String carpetaTemporal="";
		String temporal=getPropertyValue("carpeta.temporal");
		try{
			boolean crear=false;
			String nombreCarpetaTemporal=temporal+System.currentTimeMillis();
			String carpetaTemp=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+nombre+"/"+nombreCarpetaTemporal;
			fDestinoUno=new File(carpetaTemp);
			if(!fDestinoUno.exists()){
				crear=fDestinoUno.mkdir();
				if(!crear){
					logger.error("Error en la creacion de la carpeta temporal "+fDestinoUno);
					return Boolean.FALSE;
				}
//				crear=false;
			}else{
				logger.debug("La carpeta temporal "+fDestinoUno+" existia");
			}
			carpetaTemporal=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+nombre+"/"+nombreCarpetaTemporal+"/"+nombreDeImagen;
			fDestino = new File(carpetaTemporal);
			logger.debug("La carpeta temporal es "+fDestino.getAbsolutePath());
			fDestino.createNewFile();
			if(!fDestino.exists()){
//				crear=fDestino.mkdir();
//				if(!crear){
					logger.error("Error creando la carpeta temporal "+fDestino);
					return Boolean.FALSE;
//				}
			}else{
				logger.debug("La carpeta temporal "+fDestino+ " ya existia");
			}

			logger.debug("Hemos creado el archivo temporal "+fDestino.getAbsolutePath());
		}catch(Exception ea){
			logger.error("Error en la creacion de la carpeta temporal",ea);
			return Boolean.FALSE;
		}
		try{
			FileOutputStream fos = new FileOutputStream(fDestino);
			logger.debug("Copiamos lo que hay en "+fDestino+" al datahandler");
			dataHandler.writeTo(fos);
			// liberamos recursos
			fos.close();
		}catch(Exception eoa){
			logger.error("Error al copiar el datahandler en el temporal "+eoa);
			return Boolean.FALSE;
		}
		logger.debug("Hemos escrito el datahandler");
		String comando = "convert -resize " + tamaño + " " + carpetaTemporal + " " + pathFileTarget;
		try
		{		
			logger.debug("GuardarImagenNodoSQI redimensionarImagen - Vamos a ejecutar el comando " +comando);
	
			Process process = Runtime.getRuntime().exec(comando);
			
			logger.debug("CambiarImagenControllerImpl redimensionarImagen - Hemos ejecutado el proceso ["+process.getClass()+"]");
       
            // check for ls failure
             if (process.waitFor() != 0) {
            	
            	 logger.error("exit value = " + process.exitValue()); 
            	 return Boolean.FALSE;
            }

		}
		catch (Exception ex)
		{
			logger.error("Se ha producido un error al ejecutar el comando [" + comando + "]", ex);
			return Boolean.FALSE;
//			return false;
		}	
		logger.debug("subirImagen - Si existe borramos el fichero temporal "+fDestinoUno);
		
		if(fDestinoUno!=null && fDestinoUno.exists()){
			try {

				UtilesFicheros.eliminar(fDestinoUno);
				logger.info("subirImagen - Fichero borrado");
			} catch (Exception e) {
				logger.info("subirImagen - Fichero no ha sido borrado");
			}
				
		}else{
			logger.info("subirImagen - Fichero no existe");
		}
		return resultado;
	}

	
	private Boolean eliminarImagenUsuarioPublico(String pathImagen) throws IOException{
		//AgregaProperties agregaProperties = Agrega PropertiesImpl.getInstance();
		Boolean resultado = false;
		logger.debug("Eliminando la imagen del usuario ");
		try {
			File pathImagenUsuario = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+ pathImagen);
			if(logger.isDebugEnabled())
				logger.debug("Nombre del fichero a eliminar <"+pathImagenUsuario.getName()+ "> Path del fichero a eliminar <"+ pathImagenUsuario.getAbsolutePath() + ">");
			if (pathImagenUsuario.exists()) {
				UtilesFicheros.eliminar(pathImagenUsuario);
			}
		} catch (Exception e) {
			logger.error(
					"Excepcion al borrar la imagen obtenida desde la propiedad <"+ AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO+ pathImagen + "> - ", e);
		}
		return resultado;
	}

	/**
	 * Método para buscar todos los usuarios que se asemejen al nombre dado
	 * @param textoUsuario Nombre de usuario de búsqueda
	 * @param usuario El usuario que hace la consulta
	 * @return Array de UsuarioPublicoContactoVO-s
	 * @throws Exception
	 */
	protected ContactoAsociadoVO[] handleBuscarUsuariosPorNombre(
			String usuario, String textoUsuario) throws Exception {
		ContactoAsociadoVO[] usuariosContacto=null;
		try{
			logger.debug("Buscar usuarios por nombre <"+textoUsuario+">");
			if(!(textoUsuario == null) && !(textoUsuario.length() == 0) && !(textoUsuario == " "))
			{
				String textoBuscar=concatenarBusqueda(textoUsuario);
				BuscarUsuariosPorNombreCriteria criteria=new BuscarUsuariosPorNombreCriteria();
				criteria.setUsuario(textoBuscar);
				criteria.setActivo(Boolean.TRUE);
				List<UsuarioPublico> usuarios=this.getUsuarioPublicoDao().buscarUsuariosPorNombre(criteria);
				Collection<ContactoAsociadoVO> collectionUsuarios=new ArrayList();
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				if(usuarioPublico!=null){
					
					Collection<Contacto> contactosPublicos=usuarioPublico.getContacto();
					Iterator iter2 = contactosPublicos.iterator();
					int l = 0;
					Collection<String> logingUsuarios=new ArrayList();
					while (iter2.hasNext())
					{
						
						Contacto contact=(Contacto)(iter2.next());
						String usuarioContactoPublico=contact.getUsuarioContacto();
						logingUsuarios.add(usuarioContactoPublico);
					}
					if(!(usuarios == null))
					{
						logger.info("Hemos obtenido "+usuarios.size()+" usuarios que tienen una decripcion parecida a "+textoUsuario);
					}
					Iterator iter = usuarios.iterator();
					int k = 0;
					
					while (iter.hasNext())
					{
						
						UsuarioPublico g = (UsuarioPublico) (iter.next());
//						Chequeamos si el usuario cuyo login concuerda con el texto introducido esta desactivado o no en la plataforma
						Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(g.getUsuario());
						logger.debug("usuarioEntity "+usuarioEntity.getFechaDesactivacion());
						if(usuarioEntity.getFechaDesactivacion() == null)
						{
//							logger.debug("Es un usuario activo ");
							ContactoAsociadoVO usuarioContacto=new ContactoAsociadoVO();
							usuarioContacto.setImagenUsuario(g.getFoto());
							usuarioContacto.setUsuarioContacto(g.getUsuario());
							usuarioContacto.setDescripcionUsuario(g.getDescripcion());
							usuarioContacto.setId(g.getId());
//							logger.debug("Se comprueba si los contactos encontrados <"+usuarioContacto.getUsuarioContacto() +"> estan asociados con el usuario "+usuario);
							
							if(logingUsuarios.contains(usuarioContacto.getUsuarioContacto()) || usuario.equals(usuarioContacto.getUsuarioContacto())){
								usuarioContacto.setEsAsociadoUsuario(Boolean.TRUE);
							}else{
								usuarioContacto.setEsAsociadoUsuario(Boolean.FALSE);
							}
							
							collectionUsuarios.add(usuarioContacto);
							k++;	
						}
					}
					usuariosContacto=collectionUsuarios.toArray(new ContactoAsociadoVO[0]);
				}else{
					logger.warn("No existe parte pública para ese usuario "+usuario);
					usuariosContacto=new ContactoAsociadoVO[0];
				}
			}else
			{
				logger.debug("El nombre del grupo sobre el que se desea buscar es nulo o vacio");
			}
		}catch (Exception e) {
			logger.error("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+textoUsuario,e);
			throw new Exception("Error al buscar los grupos del usuario"+usuario+" con la descripcion "+textoUsuario,e);
		}
		return usuariosContacto;
	}

	/**
	 * Método para buscar todos los odes favoritos que se asemejen al titulo dado
	 * @param titulo Titulo del ode favorito de búsqueda
	 * @param usuario El usuario que hace la consulta
	 * @return Array de FavoritoAnadidioVO-s
	 * @throws Exception
	 */
	protected FavoritoAnadidioVO[] handleBuscarFavoritosPorTitulo(
			
			String titulo, String usuario) throws Exception {
		FavoritoAnadidioVO[] gruposAdmin=null;
		try{
			if (logger.isDebugEnabled())logger.debug("Buscar favoritos por titulo '"+titulo+"'");
			if(!(titulo == null) && !(titulo.length() == 0) && !(titulo.equals(" ")))
			{
				String textoBuscar=concatenarBusqueda(titulo);
				BuscarFavoritosPorTituloCriteria criteria=new BuscarFavoritosPorTituloCriteria();
				criteria.setTitulo(textoBuscar);
				List<Favorito> favoritos=this.getFavoritoDao().buscarFavoritosPorTitulo(criteria);
				Collection<FavoritoAnadidioVO> collectionFavoritos=new ArrayList();
				UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
				if(usuarioPublico!=null){
					Collection<Favorito> favoritosPublicos=usuarioPublico.getFavoritoUsuario();
					if(!(favoritos == null))
					{
						if (logger.isDebugEnabled())logger.debug("Hemos obtenido "+favoritos.size()+" grupos que tienen una decripcion parecida a "+titulo);
					}
					Iterator iter = favoritos.iterator();
					int k = 0;
					
					while (iter.hasNext())
					{
						Favorito g = (Favorito) (iter.next());
						FavoritoAnadidioVO grupoAdmin=new FavoritoAnadidioVO();
						grupoAdmin.setId_mec(g.getId_mec());
						grupoAdmin.setIdioma(g.getIdioma());
						grupoAdmin.setTitulo(g.getTitulo());
						
						
						if(favoritosPublicos.contains(g)){
							grupoAdmin.setEsFavorito(Boolean.TRUE);
						}else{
							grupoAdmin.setEsFavorito(Boolean.FALSE);
						}
						
						collectionFavoritos.add(grupoAdmin);
						k++;				
					}
					gruposAdmin=collectionFavoritos.toArray(new FavoritoAnadidioVO[0]);
				}else{
					logger.warn("No existe parte pública para ese usuario "+usuario);
					gruposAdmin=new FavoritoAnadidioVO[0];
				}
			}else
			{
				logger.debug("El nombre del grupo sobre el que se desea buscar es nulo o vacio");
			}
		}catch (Exception e) {
			logger.error("Error al buscar los favoritos del usuario"+usuario+" con el titulo "+titulo,e);
			throw new Exception("Error al buscar los favoritos del usuario"+usuario+" con el titulo "+titulo,e);
		}
		return gruposAdmin;
	}

	/**
	 * Método para eliminar la imagen del usuario publico e insertarle el de por defecto
	 * @param usuario El usuario al que se le quita la imagen
	 * @return Boolean que indica si el cambio se ha realizado bien
	 * @throws Exception
	 */
	protected Boolean handleCambiarImagenPorElDefecto(String usuario)
			throws Exception {
		Boolean vuelta=Boolean.TRUE;
		UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
		String fotoViejo=usuarioPublico.getFoto();
		String carpetaTemp=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_USUARIO_PUBLICO)+fotoViejo;
		logger.debug("La imagen se encuentra en la ruta "+carpetaTemp);
		File fDestinoUno=new File(carpetaTemp);
		if(fDestinoUno!=null && fDestinoUno.exists()){
			try {

				UtilesFicheros.eliminar(fDestinoUno);
				if (logger.isDebugEnabled())logger.debug("subirImagen - Fichero borrado "+fDestinoUno);
			} catch (Exception e) {
				logger.error("subirImagen - Fichero no ha sido borrado");
				vuelta=Boolean.FALSE;
			}
				
		}else{
			logger.info("subirImagen - Fichero no existe");
		}try{
			usuarioPublico.setFoto(getPropertyValue("nombre.imagen.defecto.usuario"));
			this.getUsuarioPublicoDao().update(usuarioPublico);
		}catch(Exception ex){
			logger.error("Error al actualizar el usaurio publico "+usuario, ex);
			vuelta=Boolean.FALSE;
		}
		return vuelta;
	}

	/**
	 * Metodo para desasociar un grupo de un usuario 
	 * @param id identificador del grupo
	 * @param usuario UsuarioPublico de agrega que se desasocia del grupo
	 * @return ResultadoOperacionVO VO que contienen el nombre del grupo y si la desasociacion ha sido correcta
	 * @throws Exception
	 */
	protected ResultadoOperacionVO handleDesasociarGrupoDeUsuario(Long id,
			String usuario) throws Exception {

			ResultadoOperacionVO resultado=new ResultadoOperacionVO();
			
			try{
				GrupoPublico grupoPublic=this.getGrupoPublicoDao().load(id);

				if(grupoPublic==null ){ 
					logger.warn("No existe un grupo con el id "+id);
//					CrearGrupoPublicoException exception = new CrearGrupoPublicoException("No existe un grupo con ese id");
					resultado.setResultado(Boolean.FALSE);
//					throw exception;
				}else{
					String nombre=grupoPublic.getNombre();
					resultado.setNombre(nombre);
					logger.debug("Desasociamos el grupo <"+nombre+"> del usuario");
//					GrupoPublico grupoViejo=(GrupoPublico) gruposNombre.get(0);
					UsuarioPublico usuarioPublico=this.getUsuarioPublicoDao().obtenerDatosUsuarioPublico(usuario);
					
					Collection gruposPublico=usuarioPublico.getGrupoPublico();
					Collection gruposLimpios=new ArrayList();
					GrupoPublico grupoDesasociado=null;
					Iterator iter = gruposPublico.iterator();
					int k = 0;
					
					while (iter.hasNext())
					{
						GrupoPublico g = (GrupoPublico) (iter.next());
						if(!g.getNombre().equals(nombre)){
							gruposLimpios.add(g);
						}else{
							grupoDesasociado=g;
						}
						k++;			
					}
					
					//Refrescamos el usuario publico
					usuarioPublico.setGrupoPublico(gruposLimpios);
					this.getUsuarioPublicoDao().update(usuarioPublico);
					logger.info("No se borra el grupo, su administrador estará asociado a él siempre");
					resultado.setResultado(Boolean.TRUE);
						
					
				}
			}catch (Exception e) {
					logger.error("Error al desasociar el grupo publico "+id+" del usuario "+usuario+" la excepcion es "+e);
//					throw new Exception("Error al eliminar el grupo publico "+nombre+" por usuario "+usuario,e);
					resultado.setResultado(Boolean.FALSE);
			}

		return resultado;
	}

	

	
	
 	/**
	 * Método para añadir un ODE que viene definido por su identificador mec a un grupo, al asociarlo se enviará un correo a todos los usuarios miembros del grupo
	 * @param id_mec Identificador mec del ode que se quiere asociar a un grupo
	 * @param nombres Nombres de los grupos a los que se les asocia el ode
	 * @return ResultadoOperacionVO[] Array de ResultadoOperacionVO que contiene el nombre del grupo y un boolean que indica si la asociación ha siod correcta o no
	 * @throws Exception
	 */
	/**protected ResultadoOperacionVO[] handleAsociarOdesPorIdentificadorAGrupo(
			String id_mec, String[] nombres) throws Exception {
		ObtenerOdesGrupoPorIdCriteria criteria=new ObtenerOdesGrupoPorIdCriteria();
		criteria.setId_mec(id_mec);
		ResultadoOperacionVO[] resultado =null;
		List<OdeGrupo> odesGrupo=this.getOdeGrupoDao().obtenerOdesGrupo(criteria);
		if(odesGrupo!=null && odesGrupo.size()>0){
			OdeGrupo odeGrupo=odesGrupo.get(0);
			OdeGrupoVO odeVO=this.getOdeGrupoDao().toOdeGrupoVO(odeGrupo);
			resultado = this.asociarOdeAGrupo(odeVO, nombres);
		}else{
			resultado=new ResultadoOperacionVO[0];
		}
		return resultado;
	}
	**/

}