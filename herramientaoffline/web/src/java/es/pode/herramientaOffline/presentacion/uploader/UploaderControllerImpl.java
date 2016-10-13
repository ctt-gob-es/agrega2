/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.herramientaOffline.presentacion.uploader;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.herramientaOffline.negocio.soporte.AutenticacionException;
import es.pode.herramientaOffline.negocio.soporte.AutorizacionException;
import es.pode.herramientaOffline.negocio.soporte.ConexionException;
import es.pode.herramientaOffline.negocio.soporte.ResultadoUploaderVO;
import es.pode.herramientaOffline.negocio.soporte.Utilidades;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.herramientaOffline.presentacion.uploader.UploaderController
 */
public class UploaderControllerImpl extends UploaderController
{
	private static Logger logger = Logger.getLogger(UploaderControllerImpl.class);
	private static final String PUERTO = "80";
    /**
     * @see es.pode.herramientaOffline.presentacion.uploader.UploaderController#subirObjetos(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.uploader.SubirObjetosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	@Override
    public final void subirObjetos(ActionMapping mapping, es.pode.herramientaOffline.presentacion.uploader.SubirObjetosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
		CarpetaPersonalSession sesion=getCarpetaPersonalSession(request);
        String clave=form.getPasswd();
        String url=form.getUrl();
        String puerto=form.getPuerto();
        String usuario=form.getUsuario();
        String tipo=form.getTipoUpload();
        
        String[] identificadores=(String[])sesion.getIdentificadores().toArray();

        //Componemos la url necesaria, con puerto incluido
        String urlEntero=Utilidades.componerUrlPuerto(url, puerto);
        if(identificadores!=null && identificadores.length>0){
        	try{
		       if(tipo.equals("CATALOGADOR")){
				   logger.debug("Vamos a subir los objetos al catalogador");
				   ResultadoUploaderVO[] vuelta =this.getDescomprimeYvalida().subirObjetosACatalogador(identificadores, urlEntero, usuario, clave);
				   form.setResultadosAsArray(vuelta);
		        	
		        }
        	else{//Por defecto se subirán a la carpeta personal
		        	logger.debug("Subimos los objetos a la carpeta personal");
		        	ResultadoUploaderVO[] vuelta = this.getDescomprimeYvalida().subirObjetosACarpetaPersonal(identificadores, urlEntero, usuario, clave);
		        	form.setResultadosAsArray(vuelta);
		        }
        	}catch (AutenticacionException ex) {
        		logger.error("Estamos en AutenticacionException", ex);
				throw new ValidatorException("{DRI.1}");
			}
			catch (AutorizacionException et) {
				logger.error("Estamos en AutorizacionException", et);
				throw new ValidatorException("{DRI.2}");
			}
			catch (ConexionException er) {
				logger.error("Estamos en ConexionException", er);
				throw new ValidatorException("{DRI.4}");
			}catch(Exception e){
				logger.error("Estamos en el error generico del Controller ", e);
				throw new ValidatorException("{DRI.5}");
			}
        }else{
    		logger.error("La lista de identificadores no puede ser nula o vacía ["+identificadores+"]");
			throw new ValidatorException("{subir.identificadores.error}");
    	}
       removeCarpetaPersonalSession(request);
     }

    /**
     * @see es.pode.herramientaOffline.presentacion.uploader.UploaderController#submitNodo(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.uploader.SubmitNodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public final java.lang.String submitNodo(ActionMapping mapping, es.pode.herramientaOffline.presentacion.uploader.SubmitNodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Obtener datos del formulario y Validar
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        String result = "Cancelar";
        
        String action = form.getAction();

        if(logger.isDebugEnabled()) logger.debug("Datos recogidos del formulario: action = " + action + " ; ");
        if(i18n.getString("comun.Aceptar").equals(action)) {
        	
        	    result = "Aceptar";
        		String clave=form.getPasswd();
        		String url=form.getUrl();
        		String usuario=form.getUsuario();
        		String tipo=form.getTipoUpload();
        		String puerto=form.getPuerto();
        		logger.debug("Los valores obtenidos del formulario son url ["+url+"],puerto ["+puerto+"],usuario ["+usuario+" ] y tipo "+tipo+" ]"); 
        		
        		if(usuario==null || usuario.equals("")){
        			logger.error("El nombre del usuario es obligatorio ["+usuario+"]");
        			throw new ValidatorException("{subir.usuario.error}");
        		}
        		if(clave==null || clave.equals("")){
        			logger.error("La clave del usuario es obligatorio ["+clave+"]");
        			throw new ValidatorException("{subir.clave.error}");
        		}
        		if(url==null || url.equals("")){
        			logger.error("La url del nodo es obligatorio ["+url+"]");
        			throw new ValidatorException("{subir.url.error}");
        		}
        		if(puerto==null || puerto.equals("")){
        			logger.error("El puerto del nodo es obligatorio ["+puerto+"]");
        			throw new ValidatorException("{subir.puerto.error}");
        		}
        		try{
        			Long puertoLong=Long.parseLong(puerto.trim());
        			if(puertoLong<=0){
        				logger.error("El puerto debe ser un número entero positivo");
            			throw new ValidatorException("{subir.puerto.entero.error}");
        			}
        		}catch(Exception e){
        			logger.error("El puerto debe ser un número entero positivo");
        			throw new ValidatorException("{subir.puerto.entero.error}");
        		}

        		if(tipo==null || tipo.equals("")){
        			logger.error("El tipo de subida es obligatorio ["+tipo+"]");
        			throw new ValidatorException("{subir.tipoUpload.error}");
        		}
        	
        }
        
        if(logger.isDebugEnabled()) logger.debug("Retorno para punto de decision : " + result);
        return result;
    }

	@Override
	public void crearSesion(ActionMapping mapping, CrearSesionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		 Almaceno los ODEs a subir en la sesion para recuperarlos despues de la confirmacion
    	if (logger.isDebugEnabled()) logger.debug("Lista de identificadores a subir : " + form.getIdentificadores() + (form.getIdentificadores() == null ? "" : " con " + form.getIdentificadores().size() + " elementos"));
    	CarpetaPersonalSession sesion=getCarpetaPersonalSession(request);
    	if(form.getIdentificadores()!=null && form.getIdentificadores().size()>0){
    		sesion.setIdentificadores(form.getIdentificadores());
    		form.setPuerto(PUERTO);
    	}else{
    		logger.error("La lista de identificadores no puede ser nula o vacía ["+form.getIdentificadores()+"]");
			throw new ValidatorException("{subir.identificadores.error}");
    	}
		
	}
}