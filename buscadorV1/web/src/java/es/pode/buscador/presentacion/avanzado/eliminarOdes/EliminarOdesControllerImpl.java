// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.eliminarOdes;

import java.rmi.RemoteException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO;
import es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.avanzado.eliminarOdes.EliminarOdesController
 */
public class EliminarOdesControllerImpl extends EliminarOdesController{
	
	private static final long serialVersionUID = -8190967967832345670L;
	private java.util.Properties pSpringProperties = null;
	Logger logger = Logger.getLogger(this.getClass());
	public final static String MENSAJE_RESULTADO_CORRECTO = "configurar.avanzado.texto.eliminarODE.resultadoCorrecto";
	public final static String MENSAJE_IDIOMAS_BUSQUEDA = "listar.odecu.mostrar.resultados.detalles.errorTraduccionTermino";
	public final static String ERROR_ELIMINANDO_ODES = "errors.odes.eliminar";
	public final static String MENSAJE_NO_SE_HAN_SELECCIONADO_ODES = "errors.odes.eliminar.no.hay.seleccionados";
	public final static String ERROR_METADATOS = "errors.odes.metadatos";
	
	public final static String SIN_ERRORES = "0.0";

    /**
     * @see es.pode.buscador.presentacion.avanzado.eliminarOdes.EliminarOdesController#obtenerIdiomas(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.eliminarOdes.ObtenerIdiomasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerIdiomas(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.eliminarOdes.ObtenerIdiomasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		log("EliminarOdesControllerImpl - obtenerIdiomas.");
    		if (LdapUserDetailsUtils.esAdministrador() || LdapUserDetailsUtils.esPublicador()){
				form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
		        if(form.getListaIds()!=null && !form.getListaIds().equals("")){
    		        String listIDs=form.getListaIds();
    		        String[] arrayListaIDs=(listIDs.split(BuscarAvanzadoControllerImpl.ANDPERSAND));
    		        form.setSeleccionado("CON_RESULTADOS");
    		        try{
    		        	MetadatoBasicoVO[] metadatoAux=new MetadatoBasicoVO[arrayListaIDs.length];
	    		        metadatoAux=listarMetadatos(arrayListaIDs,form.getIdioma());
	    		        form.setOdesAsArray(metadatoAux);
    		        }catch (Exception e){
    		        	logger.error("Error al obtener los metadatos",e);
    			    	saveErrorMessage(request, ERROR_METADATOS);
					}
		        }else{
		        	form.setSeleccionado("SIN_RESULTADOS");
		        	saveErrorMessage(request, ERROR_ELIMINANDO_ODES);
		        }
    		}else{
				logger.error("EliminarOdesControllerImpl - obtenerIdiomas: No tiene permisos para eliminar ODEs");
				saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_USUARIO_NO_LOGADO);
			}
		}catch(Exception e){
			logger.error("EliminarOdesControllerImpl - obtenerIdiomas: Error al intentar eliminar los ODEs seleccionados",e);
			saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_ERROR_ELIMINAR_ODE);
		}
     }
    
    private  MetadatoBasicoVO[] listarMetadatos(String[] ids,String idioma) throws RemoteException, Exception
	{
    	MetadatoBasicoVO[] metadatos= new MetadatoBasicoVO[ids.length];
    	for(int i=0; i<ids.length;i++){
	    	ParametroMetadatoVO parametroMetadato = new ParametroMetadatoVO(ids[i],idioma,"");
	    	metadatos[i] = this.getSrvBuscarService().solicitarMetadato(parametroMetadato);
    	}
		return metadatos;
	}

    /**
     * @see es.pode.buscador.presentacion.avanzado.eliminarOdes.EliminarOdesController#eliminarOdes(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.eliminarOdes.EliminarOdesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarOdes(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.eliminarOdes.EliminarOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	log("EliminarOdesControllerImpl - eliminarOdes.");
    	try{
    		if (LdapUserDetailsUtils.esAdministrador() || LdapUserDetailsUtils.esPublicador())
    		{
    			if (form.getListaIds()!=null && !form.getListaIds().equals("")){
    				log("EliminarOdesControllerImpl - eliminarOdes: Es administrador");
					String[] identificadores = form.getListaIds().split(BuscarAvanzadoControllerImpl.ANDPERSAND);
					MetadatoBasicoVO[] metadatoAux=new MetadatoBasicoVO[identificadores.length];
    		        metadatoAux=listarMetadatos(identificadores, form.getIdioma());
    		        form.setOdesDeletedAsArray(metadatoAux);
					for (int k=0;k<identificadores.length;k++){				
						String idODE= identificadores [k];
						String titulo=metadatoAux[k].getTitulo();
						log("EliminarOdesControllerImpl - eliminarOdes: idODE: " + idODE + "   Titulo: " + titulo);
						String comentarios=this.getResource(MENSAJE_RESULTADO_CORRECTO, BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
						String usuario = LdapUserDetailsUtils.getUsuario();
						ResultadoOperacionVO resultado = this.getSrvPublicacionService().noDisponible(idODE, usuario, comentarios, titulo);
						if (SIN_ERRORES.equals(resultado.getIdResultado()))
						{							
							log("BuscarAvanzadoControllerImpl - eliminarOdes: ODE eliminado correctamente");
							this.getSrvCacheService().borrarHitCache();
						} else {
							log("Error al pasar el ODE a no disponible:" + resultado.getDescripcion());
							saveErrorMessage(request, ERROR_ELIMINANDO_ODES);
						}
					}
					try{
						log("EliminarOdesControllerImpl - eliminarOdes:Se va a eliminar la asociación de los ODEs eliminados a los tags sociales");
						this.getSrvTaggingServer().eliminarTagsDeOdes(identificadores);
					}catch(Exception e){
						logger.error("EliminarOdesControllerImpl - eliminarOdes: Error al intentar eliminar los ODEs seleccionados del tag social.",e);
						throw new Exception();
					}	
    			}else{
    				saveErrorMessage(request, "MENSAJE_NO_SE_HAN_SELECCIONADO_ODES2");
    			}
			}else{
				logger.error("EliminarOdesControllerImpl - eliminarOdes: No tiene permisos para eliminar ODEs");
				saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_USUARIO_NO_LOGADO);
			}
		}
    	catch(Exception e)
    	{
			logger.error("EliminarOdesControllerImpl - eliminarOdes: Error al intentar eliminar los ODEs seleccionados",e);
			saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_ERROR_ELIMINAR_ODE);
		}
    	
     }

	public static String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        try{
           ResourceBundle theResourceBundle = ResourceBundle.getBundle(baseName,locale);
           if (theResourceBundle!=null){
                       recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	try{
	        	ResourceBundle theResourceBundle = ResourceBundle.getBundle(baseName);
	            if (theResourceBundle!=null){
	                 recurso = theResourceBundle.getString(key);
	            }
        	 }catch (MissingResourceException m){
        		 recurso = key;
        	 }
        }
        return recurso;
    }
	
	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}