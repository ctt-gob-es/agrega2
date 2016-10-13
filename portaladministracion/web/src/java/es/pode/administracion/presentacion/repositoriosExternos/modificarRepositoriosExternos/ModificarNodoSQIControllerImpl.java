// license-header java merge-point
package es.pode.administracion.presentacion.repositoriosExternos.modificarRepositoriosExternos;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;



/**
 * @see es.pode.administracion.presentacion.repositoriosExternos.modificarNodoSQI.ModificarNodoSQIController
 */
public class ModificarNodoSQIControllerImpl extends ModificarNodoSQIController
{


	private static Logger logger = Logger.getLogger(ModificarNodoSQIControllerImpl.class);
	private static final String LENGUAJE_CONSULTA_VSQL="VSQL";
//	private static final String LENGUAJE_CONSULTA_QEL="QEL";//Seguramente seran solo VSQL y LOM (Corregir la validacion de alta)
//	private static final String LENGUAJE_CONSULTA_FIRE="FIRE";
	private static final String LENGUAJE_RESPUESTA_LOM="LOM";
//	private static final String LENGUAJE_RESPUESTA_RDF="RDF";
	private static final String LENGUAJE_RESPUESTA_LOMES="LOM-ES";
	public final static String PUNTO = ".";



	public void cargarNodoSQI(ActionMapping mapping, CargarNodoSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
    		
    		Long id = new Long(request.getParameter("id"));
    		if (id == null){
    			throw new ValidatorException ("{errors.modificarnodoSQI.idNulo}");
    		}
    		Long[] ids=new Long[1];
    		if(logger.isDebugEnabled())
    			logger.debug("Estamos en cargarNodoSQI con id [ "+id+" ]");
    		ids[0]=id;
    		NodoSQIVO[] nodos = this.getSrvGestionSqiService().consultaNodosSQI(ids);
    		NodoSQIVO nodo=nodos[0];
    		form.setId(id);
    		form.setClave(nodo.getClave());
    		form.setDescripcionNodo(nodo.getDescripcionNodo());
    		form.setGestorSesion(nodo.getGestorSesion());
    		form.setIdentificadorSesion(nodo.getIdentificadorSesion());
    		form.setLenguajeConsulta(nodo.getLenguajeConsulta());
    		form.setLenguajeRespuesta(nodo.getLenguajeRespuesta());
    		form.setNombreNodo(nodo.getNombreNodo());
    		form.setUrlServicio(nodo.getUrlServicio());
    		form.setUsuario(nodo.getUsuario());
    		logger.info("Recogemos los datos del nodo de nombre "+nodo.getNombreNodo());
    		//Vamos a escribir el examinar vacio
    		
    		//Esta variable no se debe pintar en la jsp como tal; hay que utilizarla para pintar la imagen:uploads/imagenesNodosExternos/nodo+nombreImagen
    		//File file = new File (nodo.getImagenNodo());
    		//String path = file.getAbsolutePath();
    		//form.setImagenNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nodo.getImagenNodo());
    		
//    		FormFile imagenFile=null;
//    		imagenFile.setFileName(nodo.getImagenNodo());
//    		form.setImagenNodo(imagenFile);
    		
    		
    	}catch (ValidatorException e){
    		logger.error("Error al recuperar: " + e);
    		throw e;
    	}catch (Exception e){
    		logger.error("Error al recuperar el nodoSQI: " + e);
    		throw e;
    	}
		
	}




	public void modificarNodoSQI(ActionMapping mapping, ModificarNodoSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	try{
    		
    		if(logger.isDebugEnabled())logger.debug("Entramos en modificarNodoSQI");
    		Long identificador=form.getId();
    		String nombreNodo=form.getNombreNodo();
    		String descripcionNodo=form.getDescripcionNodo();
    		String url=form.getUrlServicio();
    		String lenguajeConsulta=form.getLenguajeConsulta().toUpperCase();
    		String lenguajeRespuesta=form.getLenguajeRespuesta().toUpperCase();
    		String identificadorSesion=form.getIdentificadorSesion();
    		String usuario=form.getUsuario();
    		String clave=form.getClave();
    		String gestorSesion=form.getGestorSesion();
    		if(logger.isDebugEnabled())logger.debug("Vamos a verificar si se le ha insertado alguna imagen");
    		FormFile imagen = form.getImagenNodo();
    		DataHandler contenidoImagenNodo = null;
    		String imagenNodo= imagen.getFileName();
 	       if(logger.isDebugEnabled())logger.debug("Lo que hemos recibido del formulario es "+imagen);
 	        
 	       if(imagen!=null && imagen.getFileName()!=null && !imagen.getFileName().equals("")){
 	    	    
		    	InternetHeaders ih = new InternetHeaders();
				MimeBodyPart mbp = new MimeBodyPart(ih, imagen.getFileData());
				DataSource dsource = new MimePartDataSource(mbp);
				contenidoImagenNodo = new DataHandler(dsource);
				if(logger.isDebugEnabled())logger.debug("Creamos el datahandler de la imagen "+ contenidoImagenNodo);
			}
    		
    		if(logger.isDebugEnabled())
    		logger.debug("Hemos recogidos los datos del formulario, nombreNodo:[" +nombreNodo+"], descripcionNodo:["+descripcionNodo+"],url:["+url+"], " +
    				"lenguajeConsulta:["+lenguajeConsulta+"],lenguajeRespuesta:["+lenguajeRespuesta+"],identificadorSesion:["+identificadorSesion+"], " +
    						"usuario:["+usuario+"], clave:["+clave+"] y gestorSesion:["+gestorSesion+"]");
    		
    		//Validacion
    		if(nombreNodo==null || nombreNodo.equals("")){
	        	logger.error("El nombre del nodo SQI es obligatorio ["+nombreNodo+"]");
				throw new ValidatorException("{errors.altanodoSQI.nombre}");
	        }
    		nombreNodo=nombreNodo.trim();
    		Pattern mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
     	    // Validaciones para password
     	    Pattern maskPwd = Pattern.compile("[^Ññ\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");

 		    Matcher matcher = null;
 		    matcher = mask.matcher(nombreNodo);
 		    if (!matcher.matches()) {
 		    	if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");
 	
 		    	throw new ValidatorException("{errors.altausuario.nombre.caracterIlegal}");
 		    }
    		//Comprobamos que no existe ningun nodo con el nombre introducido para dar de alta
		  //TODO CORREGIR la validacion del nombre, puede modificarse y guardar el nombre viejo!!!!!!!!!!!!
		    if(this.getSrvGestionSqiService().existeNombreNodoSQI(nombreNodo,identificador).booleanValue())
		    	throw new ValidatorException("{errors.altanodoSQI.nombreNodoYaExiste}");
	        if(url==null || url.equals("")){
				logger.error("La URL del servicio es obligatorio ["+url+"]");
				throw new ValidatorException("{errors.altanodoSQI.url}");
			}
    		if(descripcionNodo==null || descripcionNodo.equals("")){
	        	logger.error("La descripcion del nodo SQI es obligatorio ["+descripcionNodo+"]");
				throw new ValidatorException("{errors.altanodoSQI.descripcion}");//Internacionalizar ( tambien en GestionSQI )
	        }
    		if(lenguajeConsulta !=null && !lenguajeConsulta.equals("")){
				if(!lenguajeConsulta.equalsIgnoreCase(LENGUAJE_CONSULTA_VSQL) ){
					logger.error("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL+ ",["+lenguajeConsulta+"]");
					throw new ValidatorException("{errors.altanodoSQI.lenguajeConsulta} "+ LENGUAJE_CONSULTA_VSQL);
				}
			}else{
				logger.error("El lenguaje de consulta es obligatorio ["+lenguajeConsulta+"]");
				throw new ValidatorException("{errors.altanodoSQI.lenguajeConsultaObliga}");
			}
			if(lenguajeRespuesta !=null && !lenguajeRespuesta.equals("")){
				if(!lenguajeRespuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOM)&&!lenguajeRespuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOMES) ){
					logger.error("El lenguaje de respuesta debe ser de tipo "+ LENGUAJE_RESPUESTA_LOM+ "o tipo "+LENGUAJE_RESPUESTA_LOMES+"["+lenguajeRespuesta+"]");
					throw new ValidatorException("{errors.altanodoSQI.lenguajeRespuesta} ");
				}
			}else{
				logger.error("El lenguaje de respuesta es obligatorio ["+lenguajeRespuesta+"]");
				throw new ValidatorException("{errors.altanodoSQI.lenguajeRespuestaObliga}");
			}
			if(identificadorSesion==null || identificadorSesion.equals("")){
				if(gestorSesion==null || gestorSesion.equals("")){
					logger.error("La URL del gestor de sesiones o el identificador de la sesion es obligatorio, identificadorSesion ["+identificadorSesion+"] el gestor de sesiones ["+gestorSesion+"]");
					throw new ValidatorException("{errors.altanodoSQI.identiGestor}");
				}
			}
			
			if(usuario!=null && !usuario.equals("")){
				if(clave==null || clave.equals("")){
					logger.error("Si existe usuario,"+usuario+", es obligatoria la clave ["+clave+"]");
					throw new ValidatorException("{errors.altanodoSQI.usuarioClave}");
				}
			}
			if(clave!=null && !clave.equals("")){
				if(usuario==null || usuario.equals("")){
					logger.error("Si existe clave,"+clave+", es obligatorio el usuario ["+usuario+"]");
					throw new ValidatorException("{errors.altanodoSQI.claveUsuario}");
				}
			}
			String nombreImagen=null;
			if(imagenNodo!=null && !imagenNodo.equals("") && imagenNodo.length()>0){
				//TODO Hay que validar que la extensión que sea uno de los que viene en el agrega.properties ; ESta hecho en el CambiarImagenController del busacador
				// el metodo esImagen();
				//Si no valida habrá que lanzar un validatorException que diga que la extensión de la imagen no es válida.
				String extension = imagenNodo.substring(imagenNodo.lastIndexOf(PUNTO)+1,imagenNodo.length());
				if (!esImagen(extension)){
					throw new ValidatorException("{errors.altanodoSQI.imagen.extension.incorrecta}");
				}
				
			}else{
				logger.info("No se ha seleccionado ninguna imagen para el nodo");
			}
//    		cargamos el NodoSQIVO con los datos originales que tiene antes de modificarlo
			NodoSQIVO[] nodoCargado = new NodoSQIVO[1];
			NodoSQIVO nodo=new NodoSQIVO();
			nodo.setClave(clave);
			nodo.setDescripcionNodo(descripcionNodo);
			nodo.setGestorSesion(gestorSesion);
			nodo.setId(identificador);
			nodo.setIdentificadorSesion(identificadorSesion);
			nodo.setLenguajeConsulta(lenguajeConsulta);
			nodo.setLenguajeRespuesta(lenguajeRespuesta);
			nodo.setNombreNodo(nombreNodo);
			nodo.setUrlServicio(url);
			nodo.setUsuario(usuario);
			//nodo.setImagenNodo(nombreImagen);
			nodo.setImagenNodo(imagenNodo);
			nodo.setContenidoImagenNodo(contenidoImagenNodo);
			nodoCargado[0]=nodo;
			Long respuesta = this.getSrvGestionSqiService().modificarNodoSQI(nodoCargado[0]);
			form.setIdModificado(respuesta);
			if(logger.isDebugEnabled())
				logger.debug("Lo que ha devuelto es["+respuesta+"]");
			if(identificador.equals(respuesta)){
				form.setResultado("OK");
			}
			else{
				form.setResultado("FALLO");
			}
    	}catch (ValidatorException e)
		{
			throw e;
    	} catch (Exception e){
    		logger.error("Se ha producido un error al modificar el nodo: " + e);
    		throw new ValidatorException("{errors.modificarnodoSQI}");

    	}
		
	}


	 private boolean esImagen(String extension) throws RemoteException, Exception{
			boolean resultado = false;
			String extensionValidas = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_NODOS_SQI_EXT);
			resultado = extensionValidas.contains(extension.toLowerCase());
			return resultado;
		}







}