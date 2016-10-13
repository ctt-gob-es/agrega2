/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.empaquetador.presentacion.basico.imagen;

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

import es.pode.empaquetador.negocio.servicio.FicheroVO;



/**
 * @see es.pode.empaquetador.presentacion.basico.imagen.CambiarImagenController
 */
public class CambiarImagenControllerImpl extends CambiarImagenController
{

	private static final long serialVersionUID = 6932253014358130361L;
	private static Logger logger = Logger.getLogger(CambiarImagenControllerImpl.class);
	
	
	private String getIdODE (HttpServletRequest request) {			
		if(request==null) {
			logger.error("CambiarImagenControllerImpl - request es null.");
			return "";
		}
		if (this.getEmpaquetadorSession(request)==null) {
			logger.error("CambiarImagenControllerImpl - getEmpaquetadorSession(request) es null.");
			return "";
		}
		String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
		return idOde;
	}
	
	private void setImageODE (FormFile ficheroN, String idODE) throws Exception	{

		if(ficheroN.getFileName()==null || ficheroN.getFileName().equals("")) {
	    	Logger.getLogger(this.getClass()).error("Lanzando excepcion de validacion.");
			throw new ValidatorException("{portal_empaquetado.exception.crearArchivo}");
		}
		
		//Check content type
		//TODO esto seria mejor sacarlo en un mensaje para el usuario
		if(!ficheroN.getContentType().startsWith("image/")){
			Exception e = new Exception();
			throw new Exception("ERROR no se recibio una imagen por parte del usuario",e);
		}

		//Code help: AgregarLocelControllerImpl (PortalEmpaquetador)
        InternetHeaders ih = new InternetHeaders();
		DataHandler dFichero = null;
		MimeBodyPart mbp = new MimeBodyPart(ih, ficheroN.getFileData());
		DataSource source = new MimePartDataSource(mbp);
		dFichero = new DataHandler(source);

		FicheroVO fichero = new FicheroVO();
		fichero.setDatos(dFichero);
		fichero.setTipoMime(ficheroN.getContentType());
		
		try {
			this.getSrvGestorArchivosService().crearVistaPrevia(fichero, idODE);
		} catch (Exception e) {
			throw new Exception("ERROR al llamar a crearVistaPrevia",e);
		}		
	}
	

    /**
     * @see es.pode.empaquetador.presentacion.basico.imagen.CambiarImagenController#obtenerImagen(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.basico.imagen.ObtenerImagenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerImagen(ActionMapping mapping, es.pode.empaquetador.presentacion.basico.imagen.ObtenerImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (form.getIdODE()==null) 
			form.setIdODE(getIdODE(request));
		
		if (form.getIdODE().equals("")) 
			throw new Exception("obtenerImagen - ERROR al llamar al obtener el ID del ODE");			
		
		logger.debug("obtenerImagen - id ODE="+form.getIdODE());
    	
		try {
    		String urlImgODE=this.getSrvGestorArchivosService().recuperarVistaPrevia(form.getIdODE());
    		form.setUrlImgODE(urlImgODE);
    	} catch (Exception e) {
			throw new Exception("obtenerImagen - ERROR al llamar a recuperarVistaPrevia",e);
    	}
    }

    
    /**
     * @see es.pode.empaquetador.presentacion.basico.imagen.CambiarImagenController#guardarImagen(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.basico.imagen.GuardarImagenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardarImagen(ActionMapping mapping, es.pode.empaquetador.presentacion.basico.imagen.GuardarImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
    	if (form.getIdODE()==null) 
    		form.setIdODE(getIdODE(request));
	
    	if (form.getIdODE().equals("")) 
    		throw new Exception("guardarImagen - ERROR al llamar al obtener el ID del ODE");			
	
    	logger.debug("guardarImagen - id ODE="+form.getIdODE());
    	setImageODE(form.getFichero(), form.getIdODE());
    }

}