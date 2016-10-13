/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioCorreoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.publicacion.negocio.servicios.LicenciaVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.menu.MenuController;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;

/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarController
 */
public class PublicarControllerImpl extends PublicarController {
	
	protected static final String FILE_NAME_PROPERTIES = "/gestorFlujo.properties";

	private static Properties props = null;

	private Logger logger = Logger.getLogger(PublicarController.class);

	private final String SPLITTER_VAL = ";";
	
	private final String FILE_SEPARATOR="/";
	
	private final String LICENCIA_NAME="licencia.txt";

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarController#publicarODE(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarODEForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * 
	 * publica un ode, ya sea despublicado o propuesto.
	 */
//TODO Añadir al objeto publiSes el identificador de la licencia identificadorLicencia
	public final void publicarODE(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarODEForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResultadoOperacionVO resultadoPublicacion;
		PublicarSession publiSes = this.getPublicarSession(request);
		logger.info("Publicando ODE: " + publiSes.getTitulo() + " Despublicado?: "
				+ publiSes.getEsDespublicado().booleanValue() + " IdODE: " + publiSes.getIdODE());
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		//Añadimos la variable despublicado, para enviar el correo solo en el caso de odes que no sean despublicados.
		boolean despublicado = publiSes.getEsDespublicado().booleanValue();
    	ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		boolean comentarioValidado=false;//El comentario es obligatorio en el caso de los despublicados, pero no en el caso de la primera publicaion
		
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		
		//Antes de nada se comprueba que existe el directorio de indices locales y que tenemos permiso de acceso.
		File indexPath = new File(homeJboss+File.separator+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INDICE));
		if (!indexPath.exists() || !indexPath.isDirectory() || !indexPath.canWrite()) {
			logger.error("ERROR publicando el ODE con IdODE[" + publiSes.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + publiSes.getTitulo() + "]: " +
					"O no existe o no se tienen permisos de escritura para el directorio de indices '"+indexPath.getAbsolutePath()+"'");
			throw new ValidatorException("{gestorFlujo.excepcion.publicar.publicar}");
		}
		
		if(publiSes.getEsDespublicado().booleanValue()){
			if(logger.isDebugEnabled())
				logger.debug("Es un ODE despublicado");
			if(form.getComentarios()!=null){
				if ((form.getComentarios().trim().length() > 0) && (form.getComentarios().length() < 2500)) {
					if(logger.isDebugEnabled())
					logger.debug("Comentarios correctos publicando ODE :" + publiSes.getIdODE() + " de titulo: "
							+ publiSes.getTitulo() + " Comentarios: " + form.getComentarios() + " tipoLicencia: "
							+ publiSes.getTextoLicencia() +" con identificador "+publiSes.getIdentificadorLicencia()+ " Comunidades: " + publiSes.getComunidades() + ";");
					comentarioValidado=true;
					publiSes.setEsDespublicado(true);
					//Añadimos el identificador de la licencia
					logger.debug("el ode es despublicado y guardamos el identificador de licencia en sesion "+ publiSes.getIdentificadorLicencia());
					publiSes.setIdentificadorLicencia(publiSes.getIdentificadorLicencia());
				}else{
					logger.warn("Longitud de comentario no válida al publicar el ODE con IdODE[" + publiSes.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
							+ "] y titulo[" + publiSes.getTitulo() + "]; longitud: " + form.getComentarios().length());
					throw new ValidatorException("{gestorFlujo.comentario.longitud}");
				}
			}else{
				logger.warn("Longitud de comentario no válida al publicar el ODE con IdODE[" + publiSes.getIdODE()
						+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
						+ "] y titulo[" + publiSes.getTitulo() + "]; longitud: " + form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
			}
		}else if(!publiSes.getEsDespublicado().booleanValue()){
			if(form.getComentarios().length() < 2500){
				if(logger.isDebugEnabled())
					logger.debug("Comentarios correctos publicando ODE :" + publiSes.getIdODE() + " de titulo: "
							+ publiSes.getTitulo() + " Comentarios: " + form.getComentarios() + " tipoLicencia: "
							+ publiSes.getTextoLicencia() +"con identificador"+publiSes.getIdentificadorLicencia()+ " Comunidades: " + publiSes.getComunidades() + ";");
					comentarioValidado=true;
					publiSes.setEsDespublicado(false);
//					Añadimos el identificador de la licencia
					publiSes.setIdentificadorLicencia(publiSes.getIdentificadorLicencia());
			}else{
				logger.warn("Longitud de comentario no válida al publicar el ODE con IdODE[" + publiSes.getIdODE()
						+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
						+ "] y titulo[" + publiSes.getTitulo() + "]; longitud: " + form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.comentario.longitud}");
			}
		}else{
			logger.warn("No se han introducido comentarios al publicar el ODE con IdODE[" + publiSes.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
					+ "] y titulo[" + publiSes.getTitulo() + "]");
			throw new ValidatorException("{gestorFlujo.comentario.obligatorio}");
		}
		if(logger.isDebugEnabled())
		logger.debug("Comentarios correctos publicando ODE :" + publiSes.getIdODE() + " de titulo: "
				+ publiSes.getTitulo() + " Comentarios: " + form.getComentarios() + " tipoLicencia: "
				+ publiSes.getTextoLicencia() + " el identificador de la licencia "+publiSes.getIdentificadorLicencia()+" Comunidades: " + publiSes.getComunidades() + ";");
	
		if (publiSes.getIdentificadorLicencia() != null && publiSes.getIdentificadorLicencia().length() > 0) {
			// Ahora comprobamos que existan las comunidades
			//TODO Deberia estar en servicio, no aquí
			String[] nodosValidos=interseccionNodos(publiSes.getComunidades());
			
			if ((nodosValidos != null) && nodosValidos.length > 0) {
				StringBuilder nodosValidosString=new StringBuilder("");
				for(int i=0;i<nodosValidos.length;i++){//Para que sólo haya nodos válidos en la publicacion y tambien el nodo local
					nodosValidosString.append(nodosValidos[i]+",");
				}
				nodosValidosString=nodosValidosString.deleteCharAt(nodosValidosString.length()-1);
				logger.debug("Nodos validos: "+nodosValidosString.toString());
				publiSes.setComunidades(nodosValidosString.toString());
				
				//Si tienes todos los nodos será universal
				
				SrvNodoService nodosPlataforma = this.getSrvNodoService();
				
				String[] nodosListados=obtenNodosLocalesIds(nodosPlataforma);
				logger.debug("Obtenemos "+nodosListados.length+" nodos locales");
				if(nodosValidos.length== nodosListados.length){
					publiSes.setUniversal(getPropertyValue("licencia.acceso.universal"));
					logger.debug("Es licencia universal");
				}
				
				try {
					//Traduccion al inglés del identificador de licencia;
					TerminoYPadreVO[] typ = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(
					new String[] { publiSes.getIdentificadorLicencia() });
	
					publiSes.setIdentificadorLicencia(typ[0].getNomTermino());
					String texto2 ="";
					if (publiSes.getEsDespublicado()) {
						
						if( publiSes.getTextoLicencia()== null ||  publiSes.getTextoLicencia().length()==0){
							
							logger.debug("No hemos entrado en modificar, tenemos que recoger el texto de la licencia");
							SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
							LocalizadorVO localizar=localizadorService.consultaLocalizador(publiSes.getIdODE());
							String pathLocalizador = localizar.getPath();
							logger.debug("El localizador del ode es "+ pathLocalizador);
							String localizador=pathLocalizador+"/licencia.txt";
							File fileLicencia=new File(localizador);
							if(fileLicencia.exists()) {
								String texto="";
								try{
									BufferedReader br = new BufferedReader(new FileReader(localizador));
										
									while ((texto=br.readLine()) != null) {
										if(texto2!=null && !texto2.equals("")){
											String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
										//								 String lineSep="\n";
											texto2 = texto2+lineSep+texto;
										//											 texto2.replaceAll(lineSep, "");
											logger.debug("El texto de esta línea es "+texto);
										}else{
											texto2=texto;
										}
									}
									br.close();
								
								}catch(IOException ex){
									logger.error("Error  obteniendo el texto de la licencia que traía el ode" +ex);
								}
							} else {
								logger.debug("El archivo licencia.txt no existe para el identificador "+typ[0].getIdTermino());
								if(typ[0].getIdTermino().equals("6.2.5")){
									logger.debug("Le insertamos la licencia de las propiedades");
									texto2=i18n.getString("gestorFlujo.importarURL.descripcion.tooltip");//Recoger de properties
								}else{
									texto2=obtenerTextoLicencia(typ[0].getIdTermino());
									logger.debug("El texto del identificador que el ode traía es "+texto2);
								}
							}								 
							publiSes.setTextoLicencia(texto2);
						}
						resultadoPublicacion = this.getSrvPublicacionService().publicarDespublicado(
								publiSes.getIdODE(), LdapUserDetailsUtils.getUsuario(), form.getComentarios(),
								publiSes.getTitulo(), publiSes.getComunidades(), 
								publiSes.getUniversal(), publiSes.getIdentificadorLicencia(), publiSes.getTextoLicencia());
						publiSes.setEsDespublicado(true);
						
						if(logger.isDebugEnabled())
						logger.debug("Publicando ODE despublicado: en nodos " + publiSes.getComunidades() + "idODE:"+publiSes.getIdODE()+"idioma usuario:"+LdapUserDetailsUtils.getUsuario()+"comentarios:"+form.getComentarios()
								+"titulo:"+publiSes.getTitulo()+"comunidades:"+publiSes.getComunidades()+"tipoLicencia:"+publiSes.getIdentificadorLicencia()+"universa?"+publiSes.getUniversal());
					} else {
						texto2 ="";
						if( publiSes.getTextoLicencia()== null ||  publiSes.getTextoLicencia().length()==0){
							
							SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
							LocalizadorVO localizar=localizadorService.consultaLocalizador(publiSes.getIdODE());
							String pathLocalizador = localizar.getPath();
							logger.debug("El localizador del ode es "+ pathLocalizador);
							String localizador=pathLocalizador+"/licencia.txt";
							File fileLicencia=new File(localizador);
							if(fileLicencia.exists()){
								logger.debug("Existe licencia.txt en el ode");
								String texto="";
								try{
									BufferedReader br = new BufferedReader(new FileReader(localizador));
										
									while ((texto=br.readLine()) != null) {
										if(texto2!=null && !texto2.equals("")) {
											String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
										//								 String lineSep="\n";
											texto2 = texto2+lineSep+texto;
										//											 texto2.replaceAll(lineSep, "");
											logger.debug("El texto de esta línea es "+texto);
										} else {
											texto2=texto;
										}
									}
									br.close();
									
								}catch(IOException ex){
									logger.error("Error  obteniendo el texto de la licencia que traía el ode" +ex);
								}
							}else{
								logger.debug("El archivo licencia.txt no existe para el identificador "+typ[0].getIdTermino());
								if(typ[0].getIdTermino().equals("6.2.5")){
									logger.debug("Le insertamos la licencia de las propiedades");
									texto2=i18n.getString("gestorFlujo.importarURL.descripcion.tooltip");//Recoger de properties
								}else{
									texto2=obtenerTextoLicencia(typ[0].getIdTermino());
									logger.debug("El texto del identificador que el ode traía es "+texto2);
								}
							}
							publiSes.setTextoLicencia(texto2);
						}
						
						resultadoPublicacion = this.getSrvPublicacionService().publicar(publiSes.getIdODE(),
								LdapUserDetailsUtils.getUsuario(), form.getComentarios(), publiSes.getTitulo(),
								publiSes.getComunidades(), publiSes.getUniversal(), publiSes.getIdentificadorLicencia(), publiSes.getTextoLicencia());
						publiSes.setEsDespublicado(false);
						
						if(logger.isDebugEnabled())
						logger.debug("Publicando ODE publicado: en nodos [" + publiSes.getComunidades() + "] idODE: ["+publiSes.getIdODE()+"]  usuario: ["+LdapUserDetailsUtils.getUsuario()+"] comentarios: ["+form.getComentarios()
								+"] comunidades: ["+publiSes.getComunidades()+"] tipoLicencia: ["+publiSes.getIdentificadorLicencia()+"] universa? ["+publiSes.getUniversal() + "]");
						//He publicado mi ODE, mando el correo al usuario dueño del ODE, y a los usuarios que tienen al dueño como contacto.
					}
	
				} catch (Exception ex) {
					logger.error("ERROR (excepción) publicando el ODE con IdODE[" + publiSes.getIdODE()
							+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
							+ form.getComentarios() + "] y titulo[" + publiSes.getTitulo() + "]" + "\n", ex);
//					 limpiamos la sesión una vez que hemos publicado
					publiSes.setComunidades("");
					publiSes.setIdODE("");
					publiSes.setTextoLicencia("");
					publiSes.setUniversal("");
					publiSes.setIdentificadorLicencia("");
					publiSes.setTitulo("");
					publiSes.setTextoLicencia(null);
					throw new ValidatorException("{gestorFlujo.excepcion.publicar.publicar}");
				}
				if (logger.isDebugEnabled())					
					logger.debug("El resultado de la publicación es: " + resultadoPublicacion.getIdResultado());				
				if (!resultadoPublicacion.getIdResultado().equals("0.0")) {
					logger.error("Error publicando el ODE con IdODE[" + publiSes.getIdODE() + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
							+ "] y titulo[" + publiSes.getTitulo() + "] : "
							+ resultadoPublicacion.getDescripcion());
	
					// throw new
					// ValidatorException(resultadoPublicacion.getDescripcion());
					// saveErrorMessage(request,
					// "gestorFlujo.error.publicar", new String[] {
					// publiSes.getTitulo(),
					// resultadoPublicacion.getDescripcion() });
					form.setMensajes(resultadoPublicacion.getDescripcion().substring(0,
							resultadoPublicacion.getDescripcion().length() - 1).split(SPLITTER_VAL));
	
				} else {
					// Envio correo al usuario de creacion del ode, y a los usuarios q tengan al creador del ODE como contacto.
					try{
						if(!despublicado) //Envio el correo solo en el caso de que el ODE no venga del estado despublicado.
							logger.debug("El idODE del objeto publicado que uso para enviar el correo es: ["+ resultadoPublicacion.getIdODE() +"] y el titulo del ODE es [" + publiSes.getTitulo() +"]");
							enviarCorreoPublicar(resultadoPublicacion.getIdODE(), LdapUserDetailsUtils.getUsuario(),form.getComentarios(), publiSes.getTitulo());
					}catch(Exception e) {
						logger.error("Fallo en el envio de correo del ODE con id " + publiSes.getIdODE() + " y usuario " + LdapUserDetailsUtils.getUsuario(), e);
					}
					logger.info("Publicado correctamente: ODE con IdODE[" + publiSes.getIdODE() + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]comentarios[" + form.getComentarios()
							+ "] y titulo[" + publiSes.getTitulo() + "]");
				}
//				 limpiamos la sesión una vez que hemos publicado
				publiSes.setComunidades("");
				publiSes.setIdODE("");
				publiSes.setTextoLicencia("");
				publiSes.setUniversal("");
				publiSes.setIdentificadorLicencia("");
				publiSes.setTitulo("");
				publiSes.setTextoLicencia(null);
			} else {
				logger.warn("No se puede publicar pq la licencia no tiene asociadas comunidades["
						+ publiSes.getIdODE() + "] usuario[" + LdapUserDetailsUtils.getUsuario()
						+ "]comentarios[" + form.getComentarios() + "] y titulo[" + publiSes.getTitulo()
						+ "]; longitud: " + form.getComentarios().length());
				throw new ValidatorException("{gestorFlujo.publicar.comunidadObligatoria}");
			}
		} else {
			logger.warn("No se puede publicar pq no hay un tipo de licencia asociada[" + publiSes.getIdODE()
					+ "] usuario[" + LdapUserDetailsUtils.getUsuario() + "]comentarios["
					+ form.getComentarios() + "] y titulo[" + publiSes.getTitulo() + "]; longitud: "
					+ form.getComentarios().length());
			throw new ValidatorException("{gestorFlujo.publicar.tipoLicenciaObligatoria}");
		}
	}

	
	private void enviarCorreoPublicar(String idODE, String idUsuario, String comentarios, String titulo){
		try{	

			String[] usuarioCreacion =this.getSrvPublicacionService().obtenerUsuariosCreacionDeIdentificadores(new String[]{idODE});
			UsuarioVO datosUsuarioCreacion = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuarioCreacion[0]);
//			 Mandamos un correo al usuario de creacion del ODE
//			Si tiene recibirCorreo a true --> envio correo. En otro caso --> no envio Correo.
			if(datosUsuarioCreacion.getRecibirCorreoPublicacion()){
				CorreoOdeVO correoODE = new CorreoOdeVO();
				correoODE.setHrefLogo(ImagenesAgrega.urlHrefLogo());
				correoODE.setUrlFicha(ODEPublico.urlFichaODEPublicado(idODE, LdapUserDetailsUtils.getIdioma()));
				logger.info("urlFicha ["+ correoODE.getUrlFicha() +"]");
				correoODE.setTituloOde(titulo);
				correoODE.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
				correoODE.setUrlImagen(ImagenODE.urlImagenODE(idODE)); 
				logger.info("Imagen ["+ correoODE.getUrlImagen()+"]");
				correoODE.setTo(new String[] {datosUsuarioCreacion.getEmail()});
				correoODE.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
				correoODE.setIdiomaCorreo(LdapUserDetailsUtils.getIdioma());
				this.getSrvCorreo().publicacionODE(correoODE);
			}
			
			//Mandamos un correo  cuando un contacto publica un ODE
			CorreoOdeVO correoODEContacto = new CorreoOdeVO();
			correoODEContacto.setHrefLogo(ImagenesAgrega.urlHrefLogo());
			logger.info("HrefLogo ["+ correoODEContacto.getHrefLogo() +"]");
			correoODEContacto.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
			logger.info("UrlImagenLogo ["+ correoODEContacto.getUrlImagenLogo() +"]");
			correoODEContacto.setTituloOde(titulo);
			logger.info("Titulo ["+ correoODEContacto.getTituloOde() +"]");
			correoODEContacto.setUrlFicha(ODEPublico.urlFichaODEPublicado(idODE, LdapUserDetailsUtils.getIdioma()));
			logger.info("urlFicha ["+ correoODEContacto.getUrlFicha() +"]");
			correoODEContacto.setUrlImagen(ImagenODE.urlImagenODE(idODE));
			//TO
			UsuarioCorreoVO[] usuariosCorreo = this.getSrvPerfilPublico().listarUsuariosConContacto(usuarioCreacion[0]);
			if(usuariosCorreo!=null && !(usuariosCorreo.length>0)){
				logger.debug("Hay " +usuariosCorreo.length+ " contactos del usuario: "+ LdapUserDetailsUtils.getUsuario());
				//Recojo en un String[] los mails de los usuariosCorreo
			ArrayList<String> mailUsuario = new ArrayList<String>();
			for (int i = 0; i < usuariosCorreo.length; i++) {
					mailUsuario.add(usuariosCorreo[i].getEmail());
				}
				correoODEContacto.setTo(mailUsuario.toArray(new String[0]));
			}
			correoODEContacto.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
			logger.info("From ["+ correoODEContacto.getFrom()+"]");
			correoODEContacto.setIdiomaCorreo(LdapUserDetailsUtils.getIdioma());
			logger.info("IdiomaCorreo [" + correoODEContacto.getIdiomaCorreo() +"]");
			correoODEContacto.setUsuario(idUsuario);
			this.getSrvCorreo().contactoPublicaODE(correoODEContacto);
		}catch(Exception e){
			logger.error("Fallo en envioCorreoPublicar del ODE con id " + idODE, e);
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarController#cargaFormularioPublicar(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CargaFormularioPublicarForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * 
	 * Carga las opciones necesarios en el formulario, en este caso los valores
	 * del idioma para el buscador
	 */
	public final void cargaFormularioPublicar(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CargaFormularioPublicarForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idioma =LdapUserDetailsUtils.getIdioma();
			if(logger.isDebugEnabled())
				logger.debug("Cargando los datos de publicar ode");
			
			PublicarSession publiSes = this.getPublicarSession(request);
			// (las comunidades si puede ser null pq sea universal)
			if ((publiSes.getIdentificadorLicencia() == null || publiSes.getIdentificadorLicencia().length() == 0)
					|| (publiSes.getUniversal() == null || publiSes.getUniversal().length() == 0)) {//Cuando entramos de publicar
				// sacamos la licencia del ode (viene con una del catalogador,
				// de momento)
				
				LicenciaVO licenciaVO = this.getSrvPublicacionService().obtenLicenciaODE(form.getIdODE(), idioma);
				if(logger.isDebugEnabled())
					logger.debug("En este caso los sacamos del lomes del ODE el ambito es: "+ licenciaVO.getUniversal()
						+ " las comunidades son: " + licenciaVO.getComunidades() + ";");

				form.setUniversal(licenciaVO.getUniversal());

				publiSes.setUniversal(licenciaVO.getUniversal());
				publiSes.setComunidades(licenciaVO.getComunidades());
				// //Ahora el tipo de licencia en el idioma del perfil
				TerminoYPadreVO TerminoYPadreVOida = new TerminoYPadreVO();
				TerminoYPadreVOida.setIdiomaTermino(getPropertyValue("fuentesTaxonicas.traduccion.idioma"));
				TerminoYPadreVOida.setNomTermino(licenciaVO.getTipoLicencia());
				TerminoYPadreVOida.setIdVocabulario(getPropertyValue("combo.tipoLicencias"));
				TerminoYPadreVOida.setIdTermino("");
				// TerminoYPadreVO[] arrayTerminoYPadreTitulos=

				TerminoYPadreVO[] terminoYPadreTitulosVuelta = this.getSrvVocabulariosControladosService()
						.obtenerIdTermino(new TerminoYPadreVO[] { TerminoYPadreVOida });

				// ampliado para probar pq devuelve null esta llamada al
				// webservice
				publiSes.setIdentificadorLicencia(terminoYPadreTitulosVuelta[0].getIdTermino());
				
				NodoVO[] nodos = obtenNodosLocalesVO(this.getSrvNodoService());
				// Le pasamos las comunidades dnd aplica el ambito, si es universal tb hay
				if (!licenciaVO.getUniversal().equalsIgnoreCase(getPropertyValue("licencia.acceso.universal"))) {//Si no es universal
					
					String[] comunidadesMostrarIds=interseccionNodos(licenciaVO.getComunidades());//Cogemos los nodos válidos y le añadimos el nodo local

					String[] comunidadesMostrar = new String[comunidadesMostrarIds.length];//Cogemos las urls para pintar
					
					for (int i = 0; i < comunidadesMostrar.length; i++) {

						for (int j = 0; j < nodos.length; j++) {
							if (nodos[j].getIdNodo().equalsIgnoreCase(comunidadesMostrarIds[i])) {
								comunidadesMostrar[i] = nodos[j].getUrlWS();
								break;
							}
							
						}
					}
					publiSes.setComunidadesMostrar(comunidadesMostrar);
				}
				else//Si es universal, lleva todos los nodos
				{
					StringBuilder nodoLista=new StringBuilder("");
					for(int i=0;i<nodos.length;i++){//Si es universal tenemos que meter todos los nodos
						
						String idsNodos=nodos[i].getIdNodo();
						nodoLista.append(idsNodos+",");
					}
					licenciaVO.setComunidades(nodoLista.toString());
					String[] comunidadesMostrar = new String[nodos.length];//Todas las urls para pintar
					for (int j = 0; j < nodos.length; j++) {						
							comunidadesMostrar[j] = nodos[j].getUrlWS();
							
					}
					
					publiSes.setComunidadesMostrar(comunidadesMostrar);
					publiSes.setComunidades(nodoLista.toString());

				}
				

			}
			//Cuando volvemos de modificar, o le damos a publicar
			// universal
			if ((publiSes.getUniversal() != null) && (publiSes.getUniversal().length() > 0)) {
				form.setUniversal(publiSes.getUniversal());
			} else {
				form.setUniversal("");
			}

			// tipoLicencia
			if ((publiSes.getIdentificadorLicencia() != null) && (publiSes.getIdentificadorLicencia().length() > 0)) {
				// traducimos la licencia al idioma de navegacion para mostrarla
				if(logger.isDebugEnabled())
					logger.debug("traduciendo la licencia de la sesion["+publiSes.getIdentificadorLicencia()+"] y idioma ["+idioma+"]");
				TerminoVO[] licencia = this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(
						new String[] { publiSes.getIdentificadorLicencia() }, idioma);
				if(licencia!=null && licencia.length>0)
					form.setIdentificadorLicencia(licencia[0].getNomTermino());
				else{
					form.setIdentificadorLicencia("");
				}

			} else {
				form.setIdentificadorLicencia("");
			}
			publiSes.setIdentificadorLicencia(publiSes.getIdentificadorLicencia());
			logger.debug("Recogemos la licencia traducida :" +publiSes.getTextoLicencia()+" con el identificador "+ publiSes.getIdentificadorLicencia());

			// comunidades
			if ((publiSes.getComunidades() != null)) {//Puede que venga un string "" (si no se chequea ningun ode en modificar)
				if(logger.isDebugEnabled())
					logger.debug("las comunidades ya estaban en la sesion: " + publiSes.getComunidades());
				String[] nodosIntersacados=interseccionNodos(publiSes.getComunidades());//Los nodos válidos y el nodo local
				
				StringBuilder conLocal=new StringBuilder();
				for(int i=0;i<nodosIntersacados.length;i++){
					conLocal.append(nodosIntersacados[i]+",");
				}
				conLocal = conLocal.deleteCharAt(conLocal.length() - 1);
				if(logger.isDebugEnabled())
					logger.debug("Despues de concatenar el nodo local:" + conLocal);
				NodoVO[] nodos = obtenNodosLocalesVO(this.getSrvNodoService());//Las url para pintar
				for (int i = 0; i < nodosIntersacados.length; i++) {
					for (int j = 0; j < nodos.length; j++) {
						if (nodos[j].getIdNodo().equalsIgnoreCase(nodosIntersacados[i])) {
							nodosIntersacados[i] = nodos[j].getUrlWS();
							break;
						}
						
					}
				}
				publiSes.setComunidadesMostrar(nodosIntersacados);
				form.setComunidades(conLocal.toString());
				form.setComunidadesMostrar(publiSes.getComunidadesMostrar());
			} else {
				form.setComunidades("");
			}

			// idODE

			if ((publiSes.getIdODE() == null) || (publiSes.getIdODE().length() == 0))
				publiSes.setIdODE(form.getIdODE());
			if(logger.isDebugEnabled())
				logger.debug("idode form = " + form.getIdODE() + " idode ses = " + publiSes.getIdODE() + ";");

			// titulo
			if (publiSes.getTitulo() == null || publiSes.getTitulo().equals(""))
				publiSes.setTitulo(form.getTitulo());

			// es despublicado
			//Nunca viene vacio el esDespublicado; excepto cuando entramos a modificar las licencias, y en ese caso la sesion ya viene implementada.
			if(!(form.getEsDespublicado()==null)){
				publiSes.setEsDespublicado(form.getEsDespublicado());
				logger.debug("Lo metemos en el objeto de sesion.Este ODE es despublicado?"+form.getEsDespublicado());
			}else{
				logger.debug("Volvemos del modificar?"+form.getEsDespublicado()+"es del form; y de la sesion"+publiSes.getEsDespublicado());
			}
			

			// posibles comentarios anteriores
			form.setComentarios(publiSes.getComentarios());

			// Ahora pasamos la licencia
			if(logger.isDebugEnabled()){
				logger.debug("Metemos los campos en el form");
				logger.debug("Licencia: " + publiSes.getIdentificadorLicencia() + " Universal?" + publiSes.getUniversal());
				logger.debug("Licencia obtenida.");
			}

		} catch (Exception ex) {
			logger.error("Error al cargar pantalla de publicar: ", ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");

		}
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.PublicarController#despublicado(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.DespublicadoForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final java.lang.Boolean despublicado(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.DespublicadoForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PublicarSession publiSes = this.getPublicarSession(request);

		// limpiamos la sesión una vez que hemos cancelado
		publiSes.setComunidades("");
		publiSes.setIdODE("");
		publiSes.setIdentificadorLicencia("");
		publiSes.setTextoLicencia(null);
		publiSes.setUniversal("");
		Boolean resultado = (this.getPublicarSession(request).getEsDespublicado());
		publiSes.setEsDespublicado(false);
		// volvemos a la pagina de la que seamos :)
		return resultado;

	}

	

	/** obtener el property* */
	private String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = MenuController.class.getResourceAsStream(FILE_NAME_PROPERTIES);
				props = new java.util.Properties();
				props.load(fIsSpringProperties);
			}
			sReturn = props.getProperty(sKey);
		} catch (IOException e) {
			logger.error(e);
		}
		// devolvemos la propiedad
		return sReturn;
	}


	public Boolean decideVuelta(ActionMapping mapping, DecideVueltaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("Es despublicado?"+this.getPublicarSession(request).getEsDespublicado());

		return this.getPublicarSession(request).getEsDespublicado();

	}
	
	private String[] interseccionNodos( String nodos) throws Exception
	{
			SrvNodoService nodosPlataforma = this.getSrvNodoService();
			
			String[] nodosListados=obtenNodosLocalesIds(nodosPlataforma);
			HashSet<String> nodosTotal = new HashSet<String>(Arrays.asList(nodosListados));
			ArrayList<String> listaNodos=new ArrayList<String>(Arrays.asList(nodos.split(",")));
			HashSet<String> nodosLeidos = new HashSet<String>(listaNodos);
			nodosLeidos.retainAll(nodosTotal);
			if(!nodosLeidos.contains(nodosListados[0])){
				nodosLeidos.add(nodosListados[0]);
			}
			String[] retorno = nodosLeidos.toArray(new String[0]);
			return retorno;
	}
	
	//previamente metodo static
	private String[] obtenNodosLocalesIds(SrvNodoService srvNodos) throws Exception
	{
		NodoVO[] nodos = srvNodos.listarNodos();
//		Obtenemos el nodo local y lo añadimos
		String[] nodosReturn = new String[((nodos!=null)?nodos.length+1:1)];
//		nodosReturn[0] = DependentServerProperties.getInstance().getProperty(IDENTIFICADOR_NODO);
		nodosReturn[0] = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
		for (int i = 1; i < nodosReturn.length; i++) {
			nodosReturn[i] = nodos[i-1].getIdNodo();
		}
		return nodosReturn;
	}
	private  NodoVO[] obtenNodosLocalesVO(SrvNodoService srvNodos) throws Exception
	{
		NodoVO[] nodos = srvNodos.listarNodos();
		if(logger.isDebugEnabled())
		logger.debug("Son los nodos nulos?"+Arrays.toString(nodos));
//		Obtenemos el nodo local y lo añadimos
		NodoVO[] nodosReturn = new NodoVO[((nodos!=null && nodos.length>0)?nodos.length+1:1)];
		if(logger.isDebugEnabled())
		logger.debug("El length de nodosReturn:"+nodosReturn.length);
		NodoVO nodoAux=new NodoVO();
		nodoAux.setIdNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID));
//		nodoAux.setUrlWS(DependentServerProperties.getInstance().getServerOn());
		nodoAux.setUrlWS(AgregaPropertiesImpl.getInstance().getUrlNodo());
		nodosReturn[0]=nodoAux;
		for (int i = 1; i < nodosReturn.length; i++) {
			nodosReturn[i]=nodos[i-1];
		}
		return nodosReturn;
	}
	
	
	private String obtenerTextoLicencia(String identificadorLicencia) throws RemoteException, Exception{
		String urlLicencias=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LICENCIAS);
		
		String urlEntero=urlLicencias+this.FILE_SEPARATOR+identificadorLicencia+this.FILE_SEPARATOR+this.LICENCIA_NAME;
		logger.debug("El url entero es "+urlEntero);
		File localizador=new File(urlEntero);
		String texto="";
		String texto2="";
		try{
			BufferedReader br = new BufferedReader(new FileReader(localizador));

			 while ((texto=br.readLine()) != null) {
				 if(texto2!=null && !texto2.equals("")){
					 String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
//					 String lineSep="\n";
					 texto2 = texto2+lineSep+texto;
				 }else{
					 texto2=texto.trim();
				 }
			 }
	
		 	logger.debug("El texto de la licencia es "+texto2);
	
	 		br.close();
		
		}catch(IOException ex){
			logger.error("Error  obteniendo el texto de la licencia " +ex);
		}
		return texto2;
	}
}
