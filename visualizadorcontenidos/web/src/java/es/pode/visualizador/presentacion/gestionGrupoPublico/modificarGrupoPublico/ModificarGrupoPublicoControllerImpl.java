// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ModificarGrupoPublicoController
 */
public class ModificarGrupoPublicoControllerImpl extends ModificarGrupoPublicoController
{



	private static Logger logger = Logger.getLogger(ModificarGrupoPublicoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ModificarGrupoPublicoController#obtenerGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ObtenerGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ObtenerGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
      
       String nombre=form.getNombre();
       GrupoPublicoVO grupoPublico = this.getSrvPerfilPublico().obtenerGrupoPublico(nombre);
       form.setAdministrador(grupoPublico.getAdministrador());
       form.setDescripcion(grupoPublico.getDescripcion());
       form.setImagen(grupoPublico.getImagenGrupo());
       if(logger.isDebugEnabled())logger.debug("La imagen que tenemos es " +grupoPublico.getImagenGrupo());
       form.setId(grupoPublico.getId());
//       String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_GRUPO_PUBLICO);
       String[] imagenes=ImagenesAgrega.urlImagenesDefectoGrupos();
//		File fpathDir = new File(pathDir);
//		Collection nombres = new ArrayList();
       Collection nombres = new ArrayList();
		try{
			for(int i=0;i<imagenes.length;i++){
				String[] nombresImagenes=new String[2];
				String[] partes=imagenes[i].split(";");
				nombresImagenes[0]=partes[0];
				nombresImagenes[1]=partes[1];
				nombres.add(nombresImagenes);
			}
//		try{
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
		} catch (Exception e){
			logger.error("se ha producido una excepción al intentar listar las imagenes: " + e);
			throw e;
		}
		form.setImagenes(nombres);
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ModificarGrupoPublicoController#modificarGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ModificarGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void modificarGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.modificarGrupoPublico.ModificarGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	String administrador=form.getAdministrador();
    	String nombre=form.getNombre();
    	String imagen=form.getImagen();
    	String descripcion=form.getDescripcion();
    	Long id=form.getId();
    	if(logger.isDebugEnabled())logger.debug("El identificador del grupo que modificamos es " +id);
    	 GrupoPublicoVO grupoPublico = this.getSrvPerfilPublico().obtenerGrupoPublico(nombre);
    	
//    	grupoPublico.setAdministrador(administrador);
    	String desc="";
		   if(descripcion.length()>1995){
			   desc=descripcion.substring(0, 1995);
		   }else{
			   desc=descripcion;
		   }
    	grupoPublico.setDescripcion(desc);//Controlamos la longitud de la descripcion para que pueda caber en la base de datos
    	grupoPublico.setImagenGrupo(imagen);

    	Long vuelta = this.getSrvPerfilPublico().modificarGrupoPublico(grupoPublico, grupoPublico.getId(), LdapUserDetailsUtils.getUsuario());
    	if(vuelta.equals(id)){
    		form.setResultado("OK");
    	}else{
    		form.setResultado("FALLO");
    	}
     }





	public String obtenerAccion(ActionMapping mapping, ObtenerAccionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accion="Cancelar";
	   	 try{
		     	// nothing to be done for this operation, there are no properties that can be set
		     	String actionAceptar=form.getAccionAceptar();
		     	String actionCancelar=form.getAccionCancelar();
		     	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

	            ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);

		     	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarios.aceptar")))){
		     		accion="Aceptar";
		     	}else if((actionCancelar!=null && !actionCancelar.equals("")&& actionCancelar.equals(i18n.getString("usuarios.cancelar")) )){
		     		accion="Cancelar";
		     	}
		     	logger.info("La accion que recibimos es "+accion);
	   	 }catch(Exception e){
	   		 logger.error("Error al obtener la accion "+accion);
	   	 }
	        return accion;
	}









}