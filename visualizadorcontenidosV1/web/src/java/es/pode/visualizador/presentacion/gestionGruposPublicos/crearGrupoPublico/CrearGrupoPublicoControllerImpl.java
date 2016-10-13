// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.crearGrupoPublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.utiles.date.DateManager;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.crearGrupoPublico.CrearGrupoPublicoController
 */
public class CrearGrupoPublicoControllerImpl extends CrearGrupoPublicoController
{



	private static Logger logger = Logger.getLogger(CrearGrupoPublicoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.crearGrupoPublico.CrearGrupoPublicoController#crearGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.crearGrupoPublico.CrearGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.crearGrupoPublico.CrearGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String usuario=LdapUserDetailsUtils.getUsuario();
       String descripcion=form.getDescripcion();
       String imagen=form.getImagen();
       Pattern mask=null;

       try{
    	  mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\\\"\\|\\·\\%\\(\\)\\*\\`\\¨\\{\\}\\_\\-\\:\\.\\,\\¡\\¨\\©\\ª\\°\\\\»\\÷\\+\\=\\[\\]]+");

       }catch(Exception e){
    	   logger.error(e);
    	   throw e;
    	   }

       Matcher matcher = null;
       String nombre=form.getNombre();
       if (nombre.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("nombre.length() <= 0");
			throw new ValidatorException("{errors.altagrupoPublico.nombre}");
	    }
       if (nombre!=null || nombre.length()>0){
	       String[] longitud = nombre.split(" ");
	       for (int i=0;i<longitud.length;i++)
	       if (longitud[i].trim().length() > 30)	    		{
	    	   if(logger.isDebugEnabled())logger.debug("Cada 30 caracteres debe haber un espacio en blanco");
	    	   throw new ValidatorException("{itinerarios.nombre.longitud.sinEspacio}");
	       }
       }
     matcher = mask.matcher(nombre);
	    if (!matcher.matches()) {
	    	if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");

	    	throw new ValidatorException("{errors.altagrupoPublico.nombre.caracterIlegal}");
	    }

       Boolean existe = this.getSrvPerfilPublico().existeNombreGrupo(nombre, new Long(-1));
       if(!existe.booleanValue()){
	       GrupoPublicoVO grupo=new GrupoPublicoVO();
	       grupo.setAdministrador(usuario);
	       String desc="";
	       if (descripcion!=null){
		       descripcion=descripcion.replaceAll("\n", "<br/>");
		       if(descripcion.length()>1995){
		    	   desc=descripcion.substring(0, 1995);
		       }else{
		    	   desc=descripcion;
		       }
	       }
	       grupo.setDescripcion(desc);//Controlamos la longitud de la descripcion para que pueda caber en la base de datos
	       Date fecha=new Date(System.currentTimeMillis());
	      
	       grupo.setFechaCreacion( DateManager.dateToCalendar(fecha));
	       grupo.setImagenGrupo(imagen);
	       grupo.setNombre(nombre);
	       OdeGrupoVO[] odeGrupo=new OdeGrupoVO[0];
	       grupo.setOdeGrupo(odeGrupo);
	       Long vuelta = this.getSrvPerfilPublico().crearGrupoPublico(grupo, usuario);
	       logger.info("Hemos creado el grupo "+vuelta);
	       form.setIdModificado(vuelta);
       }else{
    	   logger.error("Error, existe un grupo con ese nombre");
   			throw new ValidatorException("{grupos.publicos.existe.nombre}");
       }
    }


	@Override
	public void obtenerImagenes(ActionMapping mapping,
			ObtenerImagenesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_GRUPO_PUBLICO);
		String[] imagenes=ImagenesAgrega.urlImagenesDefectoGrupos();
//		File fpathDir = new File(pathDir);
		Collection nombres = new ArrayList();
		try{
//			if (!fpathDir.exists()) {
//				logger.error("Error: El directorio " + pathDir + " no existe");
//			} else {
//	
//				// Recorremos el directorio donde estan las imagenes
//				File[] arrayList = fpathDir.listFiles();
//				
//				File ficheroTemp = null;
//				
//				// Por cada fichero encontrado guardamos su nombre
//				if(arrayList!=null){
//					for (int i = 0; i < arrayList.length; i++) {
//						String[] nombresImagenes=new String[2];
//						ficheroTemp = arrayList[i];
//						String nombreImagen=ficheroTemp.getName();
//						String nombreCon=AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_GRUPO_PUBLICO_URL)+"/"+nombreImagen;
//						nombresImagenes[0]=nombreCon;
//						nombresImagenes[1]=nombreImagen;
//						nombres.add(nombresImagenes);
//					}
//				}
//				
//			}
//		
			for(int i=0;i<imagenes.length;i++){
				String[] nombresImagenes=new String[2];
				String[] partes=imagenes[i].split(";");
				nombresImagenes[0]=partes[0];
				nombresImagenes[1]=partes[1];
				nombres.add(nombresImagenes);
			}
			form.setImagenes(nombres);
			} catch (Exception e){
				logger.error("se ha producido una excepción al intentar listar las imagenes: " + e);
				throw e;
			}

	}









}