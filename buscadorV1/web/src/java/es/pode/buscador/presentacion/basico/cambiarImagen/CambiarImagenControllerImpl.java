// license-header java merge-point
package es.pode.buscador.presentacion.basico.cambiarImagen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Locale;

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
import es.agrega.soporte.serverProperties.DependentServerProperties;
import es.agrega.soporte.serverProperties.DependentServerPropertiesItf;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.utiles.imagenes.UtilesImagenes;



/**
 * @see es.pode.buscador.presentacion.basico.cambiarImagen.CambiarImagenController
 */
public class CambiarImagenControllerImpl extends CambiarImagenController
{

	private int PHYSIC_TYPE_PATH = 0;
	private int INDEX_TYPE_PATH = 1;
	private static Logger logger = Logger.getLogger(CambiarImagenControllerImpl.class);
	private java.util.Properties pSpringProperties = null;
	protected final static String FILE_SEPARATOR = "/";
	public final static String PUNTO = ".";

    /**
     * @see es.pode.buscador.presentacion.basico.cambiarImagen.CambiarImagenController#subirImagen(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.cambiarImagen.SubirImagenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void subirImagen(ActionMapping mapping, es.pode.buscador.presentacion.basico.cambiarImagen.SubirImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	BuscarSession sesion = null;
    	File fDestino = null;
        try{
        	sesion = this.getBuscarSession(request);
	    	DataHandler dFichero=sesion.getDhFileImagen();
	    	if(dFichero!=null){
	    		String identificador = form.getIdentificadorODE();
		    	String pathImagen = this.imagePathGenerate(form.getIdentificadorODE(),PHYSIC_TYPE_PATH);
		    	
	    		String extPNG = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_EXTENSION);
	    		String extJPG = this.getPropertyValue("imagen.ampliada.captured");
	    		String extMediumJPG = this.getPropertyValue("imagen.ampliada.medium");
	    		String tamanioPequenio = this.getPropertyValue("imagen.tamanioPequenio");
	    		String tamanioGrande = this.getPropertyValue("imagen.tamanioGrande");
	    		String tamanioMediano = this.getPropertyValue("imagen.tamanioMediano");
	    		String imagenPequeña = pathImagen + FILE_SEPARATOR + identificador + extPNG;
	    		String imagenGrande = pathImagen + FILE_SEPARATOR + identificador + extJPG;
	    		String imagenMediana = pathImagen + FILE_SEPARATOR + identificador + extMediumJPG;
	    		
	    		String sFicheroTemporal = pathImagen + this.getPropertyValue("imagen.temporal.nombre") + form.getExtensionFich();
	    		fDestino = new File(sFicheroTemporal);
	    		logger.debug("subirImagen - Creamos el fichero para la nueva imagen");
				fDestino.createNewFile();
				FileOutputStream fos = new FileOutputStream(fDestino);
	    		dFichero.writeTo(fos);	
	    		form.setResultado(true);
	    		logger.debug("subirImagen - Generamos imagen grande .jpg " + tamanioGrande);
	    		UtilesImagenes.escala(new File(sFicheroTemporal), new File(imagenGrande), 800, 600, "jpeg");
//	    		if(!this.redimensionarImagen(sFicheroTemporal,imagenGrande,tamanioGrande)) form.setResultado(false);
	    		logger.debug("subirImagen - Generamos imagen pequeña .png con tamaño " + tamanioPequenio);
	    		UtilesImagenes.escala(new File(sFicheroTemporal), new File(imagenPequeña), 100, 100, "png");
//	    		if(!this.redimensionarImagen(sFicheroTemporal,imagenPequeña,tamanioPequenio)) form.setResultado(false);
	    		logger.debug("subirImagen - Generamos imagen mediana .jpg con tamaño " + tamanioMediano);
	    		UtilesImagenes.escala(new File(sFicheroTemporal), new File(imagenMediana), 200, 125, "jpeg");
//	    		if(!this.redimensionarImagen(sFicheroTemporal,imagenMediana,tamanioMediano)) form.setResultado(false);
	    		
	    		logger.debug("Actualizamos la imagen del ODE que fisicamente esta en "+imagenPequeña);
	    		pathImagen = this.imagePathGenerate(form.getIdentificadorODE(),INDEX_TYPE_PATH);
	    		imagenPequeña = pathImagen + FILE_SEPARATOR + identificador + extPNG;
	    		boolean ret = this.getSrvPublicacionService().cambiarImagenODE(identificador, imagenPequeña);
	    		if(ret==false)
		    		logger.error("Hubo algun error al intentar actualizar la imagen del ODE.");
	    	}
	    	else form.setResultado(false);
	    	
	    	sesion.setDhFileImagen(null);
		} catch (Exception ex) {
			sesion.setDhFileImagen(null);
			logger.error("subirImagen - ERROR: al escribir la imagen",ex);
		}finally{
			logDebug("subirImagen - Si existe borramos el fichero temporal");
			if(fDestino!=null && fDestino.exists()){
				if(fDestino.delete())
					logger.info("subirImagen - Fichero borrado");
				else 
					logger.info("subirImagen - Fichero no ha sido borrado");
			}else{
				logger.info("subirImagen - Fichero no existe");
			}
		}
    }


	@Override
	public void obtenerImagen(ActionMapping mapping, ObtenerImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		BuscarSession sesion = this.getBuscarSession(request);			
		FormFile fNuevaImagen = form.getFicheroImagenODE();
		
		if(fNuevaImagen!=null && fNuevaImagen.getFileName()!=null && !fNuevaImagen.getFileName().equals("")){
			String fileName = fNuevaImagen.getFileName();
			String extFile = fileName.substring(fileName.lastIndexOf(PUNTO)+1,fileName.length());
			
			if(esImagen(extFile)){
		    	InternetHeaders ih = new InternetHeaders();
				MimeBodyPart mbp = new MimeBodyPart(ih, fNuevaImagen.getFileData());
				DataSource dsource = new MimePartDataSource(mbp);
				DataHandler dhFichero = new DataHandler(dsource);
				form.setExtensionFich(PUNTO+extFile);	
				sesion.setDhFileImagen(dhFichero);
			} else {
				throw new ValidatorException(I18n.getInstance().getResource("{cambiar.imagen.selec.archivo.incorrecto}",BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
			}
    	} else {		
			throw new ValidatorException(I18n.getInstance().getResource("{cambiar.imagen.selec.vacia}",BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));		
		}
	}

	
	/*
	 * Metodo que genera el path de la imagen de un ODE. Este path varia ligeramente 
	 * dependiendo si es el path fisico en el HD o el path que se almacena en el indice 
	 * y en la DB.
	 */
	private String imagePathGenerate(String identificador, int typePath) {
		StringBuffer imagePathReturn = new StringBuffer();
		try {			
			String serverId = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
			// Ahora debo saber en qué servidor estoy.
			logDebug("El servidor es serverId [" + serverId + "]");
			// genero la URL de la imagen
			
			if(typePath==this.PHYSIC_TYPE_PATH)
				imagePathReturn.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_GALERIA_IMG));
			else if(typePath==this.INDEX_TYPE_PATH)
				imagePathReturn.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH));
			
			if(!imagePathReturn.toString().endsWith(FILE_SEPARATOR))imagePathReturn.append(FILE_SEPARATOR);
			
			if(typePath==this.PHYSIC_TYPE_PATH) {
				imagePathReturn.append(serverId);
				imagePathReturn.append(FILE_SEPARATOR);
			}
			
//			Tenemos que añadir un codigo MD5 para impedir que en un mismo directorio haya mas de 32000 subdirectorios. El sistema de ficheros no lo 
//			soporta. Lo hacemos por MEC, para balancear la carga entre los codigos que salgan.
			String md5 = EncriptacionUtiles.md5String(identificador).substring(0, 2);
			imagePathReturn.append(md5);
			imagePathReturn.append(FILE_SEPARATOR);
			imagePathReturn.append(identificador);
			imagePathReturn.append(FILE_SEPARATOR);
			File f = new File(imagePathReturn.toString());
			if(!f.exists()){ 
				logDebug("CambiarImagenControllerImpl imagePathGenerate - NO EXISTE DIRECTORIO!!!!!!!!: " + imagePathReturn.toString());
				f.mkdirs();
				logDebug("CambiarImagenControllerImpl imagePathGenerate - CREAMOS EL DIRECTORIO: " + imagePathReturn.toString());
			}
			logDebug("CambiarImagenControllerImpl imagePathGenerate - Ruta donde se guarda la imagen: " + imagePathReturn.toString());
		} catch (Exception ex) {
			logger.error("CambiarImagenControllerImpl imagePathGenerate - Se ha producido un error en imageGenerate", ex);
		}
		return imagePathReturn.toString();
	}
	
	
	private boolean esImagen(String extension) throws RemoteException, Exception{
		boolean resultado = false;
		String extensionValidas = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_RESIZE_EXT);
		resultado = extensionValidas.contains(extension.toLowerCase());
		return resultado;
	}
//	private boolean redimensionarImagen(String pathFileSource, String pathFileTarget, String tamaño){
//		boolean resultado = true;
//		String comando = "convert -resize " + tamaño + " " + pathFileSource + " " + pathFileTarget;
//		try
//		{		
//			logDebug("CambiarImagenControllerImpl redimensionarImagen - Vamos a ejecutar el comando " +comando);
//	
//			Process process = Runtime.getRuntime().exec(comando);
//			
//			logDebug("CambiarImagenControllerImpl redimensionarImagen - Hemos ejecutado el proceso ["+process.getClass()+"]");
//       
//            // check for ls failure
//             if (process.waitFor() != 0) {
//            	 resultado = false;
//            	 logger.error("exit value = " + process.exitValue());       
//            }
//		}
//		catch (Exception ex)
//		{
//			logger.error("Se ha producido un error al ejecutar el comando [" + comando + "]", ex);
//			return false;
//		}	
//		return resultado;
//	}

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logDebug("Propiedad recuperada: " + sKey + " : "
				+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
    
	private void logDebug(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}