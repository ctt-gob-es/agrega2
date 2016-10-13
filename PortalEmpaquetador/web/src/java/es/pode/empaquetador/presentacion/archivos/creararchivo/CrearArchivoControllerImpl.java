/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.archivos.creararchivo;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
import es.pode.empaquetador.negocio.servicio.ArchivoVO;
import es.pode.empaquetador.negocio.servicio.FicheroVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.archivos.creararchivo.CrearArchivoController
 */
public class CrearArchivoControllerImpl extends CrearArchivoController
{
	private static final long serialVersionUID = 6922253004358130361L;
	private static Logger logger = Logger.getLogger(CrearArchivoControllerImpl.class);
	private static String DOSPUNTOS=": ";
	public final static String PUNTO = ".";	
	
	private void operacionesFicheros (FormFile ficheroN, String tipo,GestorArchivosSession sesArch,EmpaquetadorSession sesEmpaq, ResourceBundle i18n, String idODE) throws Exception
	{
		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = null;
		DataSource source = null;
		DataHandler dFichero = null;		
		
		if(ficheroN.getFileName()!=null && !ficheroN.getFileName().equals(""))
	    {	
    	    FicheroVO fichero= new FicheroVO();
    		fichero.setNombre(ficheroN.getFileName());
    		mbp = new MimeBodyPart(ih, ficheroN.getFileData());
    		source = new MimePartDataSource(mbp);
    		dFichero = new DataHandler(source);
    		fichero.setDatos(dFichero);
    		fichero.setTipoMime(ficheroN.getContentType());
    	    
    		//obtengo el identificador del ode
    		String identificador=sesEmpaq.getIdLocalizador();
    	
    		List<ArchivoVO> path = sesArch.getPath();
    		ArchivoVO ultimoPath = path.get(path.size()-1);
    		//obtengo la carpetaDestino
    		String carpetaPadre = null;
			if(path.size()>1 && ultimoPath.getCarpetaPadre()==null) {
				carpetaPadre = ultimoPath.getNombre();
			} else if (path.size()>1&& ultimoPath.getCarpetaPadre()!=null) {
				carpetaPadre=ultimoPath.getCarpetaPadre().concat("/").concat(ultimoPath.getNombre());
			}
    		
 	    	if(tipo.equals("FICHERO")) {
 	    		try {
	    		this.getSrvGestorArchivosService().crearArchivo(identificador, carpetaPadre, fichero);
 	    		} catch (Exception e) {
 	    			logger.error(e.getMessage(),e);
 	    			throw new ValidatorException("{presentacion.archivos.creararchivo.error.sobreescritura}");
 	    		}
	    	} else if(tipo.equals("ZIP")) {
	    		try{
	    			this.getSrvGestorArchivosService().descomprimirArchivo(identificador, carpetaPadre, fichero);
	    		}catch(Exception noEsArchivo){
	    			Logger.getLogger(this.getClass()).error("Lanzando excepcion de validacion. Fichero no es un Archivo");
	    			throw new ValidatorException(ficheroN.getFileName()+DOSPUNTOS+i18n.getString("portalempaquetado.archivos.error.importar.noeszip"));
	    		}	
	    	} else if(tipo.equals("IMAGEN_ODE")){
	    		
	    		if (logger.isDebugEnabled())
    				logger.debug("Va a verificar si es formato imágen : " + ficheroN.getFileName());
    			
    			String fileName = ficheroN.getFileName();
				String extFile = fileName.substring(fileName.lastIndexOf(PUNTO)+1,fileName.length());
				
				if(esImagen(extFile)){
		    		try {
		    			if (logger.isDebugEnabled())
	    				logger.debug("Llamada a crearVistaPrevia con idODE="+idODE);
		    			this.getSrvGestorArchivosService().crearVistaPrevia(fichero, idODE);
		    		} catch (Exception e) {
						logger.error(e);
		    			throw new Exception("ERROR al llamar a crearVistaPrevia",e);
		    		}
				}else
				{
					logger.error("No es un archivo de imágen");
					throw new ValidatorException("{presentacion.archivos.creararchivo.error.noFicheroImagen}");
				}
	    	}
    	} else {
	    	Logger.getLogger(this.getClass()).error("Lanzando excepcion de validacion.");
			throw new ValidatorException("{portal_empaquetado.exception.crearArchivo}");
	    }

	}
    /**
     * @see es.pode.empaquetador.presentacion.archivos.creararchivo.CrearArchivoController#crearArchivo(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.creararchivo.CrearArchivoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearArchivo(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.creararchivo.CrearArchivoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
		GestorArchivosSession sesArch = this.getGestorArchivosSession(request);

		java.util.Locale locale = (Locale) request.getSession().getAttribute(
				ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);

		String opcion = form.getAction();

		if (opcion.equals(i18n.getString("presentacion.archivos.creararchivo.input.submit.aceptar"))) {

			String tipo1 = form.getTipo1();
			String tipo2 = form.getTipo2();
			String tipo3 = form.getTipo3();

			ValidatorException exception1=null;
			ValidatorException exception2=null;
			ValidatorException exception3=null;
			
			FormFile ficheroN1 = form.getFichero1();
			if (!ficheroN1.getFileName().equals("")) {
				try {
					String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
					operacionesFicheros(ficheroN1, tipo1, sesArch, sesEmpaq, i18n, idOde);
				} catch (ValidatorException e) {
					exception1 = e;
					logger.error(e);
				}
			}
			FormFile ficheroN2 = form.getFichero2();
			if (!ficheroN2.getFileName().equals("")) {
				try {
					String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
					operacionesFicheros(ficheroN2, tipo2, sesArch, sesEmpaq, i18n, idOde);
				} catch (ValidatorException e) {
					exception2 = e;
					logger.error(e);
				}
			}
			FormFile ficheroN3 = form.getFichero3();
			if (!ficheroN3.getFileName().equals("")) {
				try {
					String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
					operacionesFicheros(ficheroN3, tipo3, sesArch, sesEmpaq, i18n, idOde);
				} catch (ValidatorException e) {
					exception3 = e;
					logger.error(e);
				}
			}

			if (ficheroN1.getFileName().equals("")
					&& ficheroN2.getFileName().equals("")
					&& ficheroN3.getFileName().equals("")) {
				throw new ValidatorException("{presentacion.archivos.creararchivo.error.seleccion}");
			}
			if(exception1!=null||exception2!=null||exception3!=null) {
				StringBuffer mensaje = new StringBuffer(i18n.getString("portalempaquetado.archivos.error.importar")+DOSPUNTOS);
				if(exception1!=null){
					mensaje.append("<p>");
					mensaje.append(exception1.getMessage());
//					mensaje.append("</li>");
				}
				if(exception2!=null){
					mensaje.append("<p>");
					mensaje.append(exception2.getMessage());
//					mensaje.append("</li>");
				}
				if(exception3!=null){
					mensaje.append("<p>");
					mensaje.append(exception3.getMessage());
//					mensaje.append("</li>");
				}
//				mensaje.append("</ul>");
				String mensajeFinal=mensaje.toString();
				throw new ValidatorException(mensajeFinal);
			}
			//no se ha producido ningún error y se ha llevado a cabo la acción, informamos entonces de que se ha modificado el ode
			sesEmpaq.setModificado(true);
		}
	}
    
	private boolean esImagen(String extension) throws Exception{
		boolean resultado = false;
		String extensionValidas = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_COMMON_EXTENSION_FOTOGRAFIABLE); 
		resultado = extensionValidas.toLowerCase().contains(extension.toLowerCase());
		return resultado;
	}
}