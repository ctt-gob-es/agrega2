// license-header java merge-point
package es.pode.administracion.presentacion.repositoriosExternos.altaRepositoriosExternos;

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
import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;



/**
 * @see es.pode.administracion.presentacion.repositoriosExternos.altaRepositoriosExternos.AltaNodoSQIController
 */
public class AltaNodoSQIControllerImpl extends AltaNodoSQIController
{


	private static Logger logger = Logger.getLogger(AltaNodoSQIControllerImpl.class);
	private static final String LENGUAJE_CONSULTA_VSQL="VSQL";
//	private static final String LENGUAJE_CONSULTA_QEL="QEL";//Seguramente seran solo VSQL y LOM (Corregir la validacion de alta)
//	private static final String LENGUAJE_CONSULTA_FIRE="FIRE";
	private static final String LENGUAJE_RESPUESTA_LOM="LOM";
//	private static final String LENGUAJE_RESPUESTA_RDF="RDF";
	private static final String LENGUAJE_RESPUESTA_LOMES="LOM-ES";
	public final static String PUNTO = ".";
	protected final static String FILE_SEPARATOR = "/";


    /**
     * @see es.pode.administracion.presentacion.repositoriosExternos.altaRepositoriosExternos.AltaNodoSQIController#altaNodoSQI(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.repositoriosExternos.altaRepositoriosExternos.AltaNodoSQIForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void altaNodoSQI(ActionMapping mapping, es.pode.administracion.presentacion.repositoriosExternos.altaRepositoriosExternos.AltaNodoSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	        NodoSQIVO nodo=new NodoSQIVO();
	        String clave=form.getClave();
	        String descripcionNodo=form.getDescripcionNodo();
	        String gestorSesion=form.getGestorSesion();
	        Long id=form.getId();
	        String identificadorSesion=form.getIdentificadorSesion();
	        String lenguajeConsulta=form.getLenguajeConsulta().toUpperCase();
	        String lenguajeRespuesta=form.getLenguajeRespuesta().toUpperCase();
	        String nombreNodo=form.getNombreNodo();
	        String url=form.getUrlServicio();
	        String usuario=form.getUsuario();
//	        String imagenNodo=form.getImagenNodo();
//	        File imagenFile = new File(imagenNodo);
	        FormFile imagen = form.getImagenNodo();
	        String imagenNodo = null;
	        DataHandler contenidoImagenNodo = null;
	        
	        if(imagen!=null && imagen.getFileName()!=null && !imagen.getFileName().equals("")){
	        	imagenNodo = imagen.getFileName();
		    	InternetHeaders ih = new InternetHeaders();
				MimeBodyPart mbp = new MimeBodyPart(ih, imagen.getFileData());
				DataSource dsource = new MimePartDataSource(mbp);
				contenidoImagenNodo = new DataHandler(dsource);
				if(logger.isDebugEnabled())logger.debug("Creamos el datahandler de la imagen "+ contenidoImagenNodo);
			}
				
			
	    
	        
	        
	        //Validacion del NODO
	        if(nombreNodo==null || nombreNodo.equals("")){
	        	logger.error("El nombre del nodo SQI es obligatorio ["+nombreNodo+"]");
				throw new ValidatorException("{errors.altanodoSQI.nombre}");//Internacionalizar ( tambien en GestionSQI )
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
		    
		    if(this.getSrvGestionSqiService().existeNombreNodoSQI(nombreNodo,new Long(-1)).booleanValue())
		    	throw new ValidatorException("{errors.altanodoSQI.nombreNodoYaExiste}");
	        if(url==null || url.equals("")){
				logger.error("La URL del servicio es obligatorio ["+url+"]");
				throw new ValidatorException("{errors.altanodoSQI.url}");
			}
	        if(descripcionNodo==null || descripcionNodo.equals("")){
	        	logger.error("La descripcion del nodo SQI es obligatorio ["+descripcionNodo+"]");
				throw new ValidatorException("{errors.altanodoSQI.descripcion}");
	        }
	        if(lenguajeConsulta !=null && !lenguajeConsulta.equals("")){
				if(!lenguajeConsulta.equalsIgnoreCase(LENGUAJE_CONSULTA_VSQL) ){
					logger.error("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL+ ", ["+lenguajeConsulta+"]");
					throw new ValidatorException("{errors.altanodoSQI.lenguajeConsulta} ");
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
			
			
			if(imagenNodo!=null && !imagenNodo.equals("") && imagenNodo.length()>0){
				//TODO Hay que validar que la extensión que sea uno de los que viene en el agrega.properties ; ESta hecho en el CambiarImagenController del busacador
				// el metodo esImagen();
				//Si no valida habrá que lanzar un validatorException que diga que la extensión de la imagen no es válida.
				//String fileName = fNuevaImagen.getFileName();
				
				String extension = imagenNodo.substring(imagenNodo.lastIndexOf(PUNTO)+1,imagenNodo.length());
				if(logger.isDebugEnabled())logger.debug("Validamos la extension de la imagen "+extension);
				if (!esImagen(extension)){
					throw new ValidatorException("{errors.altanodoSQI.imagen.extension.incorrecta}");
				}
			}else{
				logger.info("No se ha seleccionado ninguna imagen para el nodo");
			}
			
			nodo.setClave(clave);
			nodo.setDescripcionNodo(descripcionNodo);
			nodo.setGestorSesion(gestorSesion);
			nodo.setId(id);
			nodo.setIdentificadorSesion(identificadorSesion);
			nodo.setImagenNodo(imagenNodo);
			nodo.setLenguajeConsulta(lenguajeConsulta);
			nodo.setLenguajeRespuesta(lenguajeRespuesta);
			nodo.setNombreNodo(nombreNodo);
			nodo.setUrlServicio(url);
			nodo.setUsuario(usuario);
			nodo.setContenidoImagenNodo(contenidoImagenNodo);
			
			Long identificador=this.getSrvGestionSqiService().altaNodoSQI(nodo);
			form.setIdModificado(identificador);
    	}catch (ValidatorException e)
		{
			throw e;
		} 
		catch (Exception e)
		{
			logger.error("Error!! " + e);
			throw new ValidatorException("{errors.altanodoSQI}");
		}
     }
    
    private boolean esImagen(String extension) throws RemoteException, Exception{
		boolean resultado = false;
		String extensionValidas = this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.IMAGENES_NODOS_SQI_EXT);
		resultado = extensionValidas.contains(extension.toLowerCase());
		return resultado;
	}

}