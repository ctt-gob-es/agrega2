/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.importar;

import java.util.ArrayList;
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

import es.pode.gestorFlujo.presentacion.objetosPersonales.ResultadoImportacion;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarController
 */
public class ImportarControllerImpl extends ImportarController {
 
	private Logger logger = Logger.getLogger(ImportarController.class);
	private final String splitter = ";";
	private final String RESULTADO_NO = String.valueOf(false);
	private final String RESULTADO_SI = String.valueOf(true);
	private final static String MANIFEST_NAME = "imsmanifest.xml";

	private ResultadoOperacionVO importarUnODE(FormFile fichero, String nombreFichero, String idioma) throws Exception {
		if(logger.isDebugEnabled())
		logger.debug("Importando un ode");
		ResultadoOperacionVO resultado = new ResultadoOperacionVO();
		SrvPublicacionService publi = this.getSrvPublicacionService();

		// preparamos el formfile que hemos recibido y lo metemos con nuestra astuta técnica  de formfiles

		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = new MimeBodyPart(ih, fichero.getFileData());

		DataSource dsource = new MimePartDataSource(mbp);
		DataHandler dFichero = new DataHandler(dsource);

		try {
			resultado = publi.crearPifConCuota(dFichero, LdapUserDetailsUtils.getUsuario(),nombreFichero,
					nombreFichero, idioma);
			logger.info("resultado de la importacion de un ode:" + resultado);
		} catch (Exception ex) {
			// Si ha habido alguna excepción no controlada lo indicamos
			resultado.setIdResultado("11.1");
			resultado.setDescripcion("Excepción al importar");
			logger.error("Excepcion al importar el ode: " + nombreFichero, ex);
		}

		return resultado;
	}
	
	static class CurrentState {
		ArrayList<ResultadoImportacion> resultado;
		Long diferencia;
		int nr_archivos_subidos;
		String archivosSubidos;
		boolean hemoshechoalgo;
		
	}

	/**
	 * @throws ValidatorException 
	 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarController#importarODE(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void importarODE(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarODEForm form,
			HttpServletRequest request, HttpServletResponse response) throws ValidatorException {
//		ResultadoOperacionVO unResultado = new ResultadoOperacionVO();
		int nr_archivos_subidos = 0;
		String archivosSubidos = "(";
		boolean hemoshechoalgo = false;
//		Long diferencia=new Long(0);
		Long diferencia=form.getEspacioLibre();
		ArrayList<ResultadoImportacion> resultado = new ArrayList<ResultadoImportacion>();
		
		//Wrapping
		CurrentState estadoActual= new CurrentState();
		//TODO Fusionar declaraciones anteriores con asignación actual
		estadoActual.diferencia=diferencia;
		estadoActual.resultado=resultado;
		estadoActual.archivosSubidos=archivosSubidos;
		estadoActual.nr_archivos_subidos=nr_archivos_subidos;
		estadoActual.hemoshechoalgo=hemoshechoalgo;

		logger.debug("Importando odes");
		// comprobamos las 5 cajas:

		boolean permiteObraDerivada;
		
		FormFile formFile=form.getFicheroODE1();
		String caja="caja 1";
		String odeOrder="primer";
	    permiteObraDerivada=comprobarFormFile(form, request, estadoActual, formFile, caja, odeOrder);
	 		
		formFile=form.getFicheroODE2();
		caja="caja 2";
		odeOrder="segundo";
		permiteObraDerivada=permiteObraDerivada && comprobarFormFile(form, request, estadoActual, formFile, caja, odeOrder);
		
		formFile=form.getFicheroODE3();
		caja="caja 3";
		odeOrder="tercer";
		permiteObraDerivada=permiteObraDerivada && comprobarFormFile(form, request, estadoActual, formFile, caja, odeOrder);
		
		formFile=form.getFicheroODE4();
		caja="caja 4";
		odeOrder="cuarto";
		permiteObraDerivada=permiteObraDerivada && comprobarFormFile(form, request, estadoActual, formFile, caja, odeOrder);
		
		formFile = form.getFicheroODE5();
		caja="caja 5";
		odeOrder="quinto";
		permiteObraDerivada=permiteObraDerivada && comprobarFormFile(form, request, estadoActual, formFile, caja, odeOrder);
	
		//Unwrapping
		resultado=estadoActual.resultado;
		archivosSubidos=estadoActual.archivosSubidos;
		nr_archivos_subidos=estadoActual.nr_archivos_subidos;
		hemoshechoalgo=estadoActual.hemoshechoalgo;
		
		// si no hemos importado nada mostramos mensajito de información:
		if (!hemoshechoalgo) {
			logger.warn("Formfile sin longitud ");
			throw new ValidatorException("{gestorFlujo.error.vacio}");
		}
		
		// si alguno ha dado problema de licencia, lo decimos
		form.setMostrarAviso(false);
		if (!permiteObraDerivada) {
			logger.warn("Al menos un ODE no permite obra derivada");
			form.setMostrarAviso(true);
		}
		
		logger.debug("ArchivosSubidos: " + archivosSubidos);
		if (nr_archivos_subidos > 0) {
			logger.info("Se han importado correctamente los archivos: " + archivosSubidos);
			if (archivosSubidos.endsWith(",")) {
				archivosSubidos = (String) archivosSubidos.subSequence(0, archivosSubidos.length() - 1);
				archivosSubidos = nr_archivos_subidos + ": " + archivosSubidos;
				archivosSubidos = archivosSubidos + ")";
			}
			
//			saveSuccessMessage(request, "gestorFlujo.importar.importados", new String[] { archivosSubidos });
		}
		form.setResultado(resultado);
 
	}

	/**
	 * @param form
	 * @param request
	 * @param estadoActual
	 * @param formFile
	 * @param caja
	 * @param odeOrder
	 * @throws ValidatorException
	 */
	private boolean comprobarFormFile(
			es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarODEForm form,
			HttpServletRequest request, CurrentState estadoActual,
			FormFile formFile, String caja, String odeOrder)
			throws ValidatorException {
		boolean permiteObraDerivada=true;
		if (formFile != null && formFile.getFileName().length() != 0) {
			if(logger.isDebugEnabled())
			logger.debug("El espacio libre restante:"+form.getEspacioLibre());
			if(estadoActual.diferencia>0){
				estadoActual.hemoshechoalgo = true;
				try {
					ResultadoImportacion resultadoParcial = new ResultadoImportacion();
					resultadoParcial.setTitulo(formFile.getFileName());
					ResultadoOperacionVO unResultado = new ResultadoOperacionVO();
					//unResultado = importarUnODE(formFile, formFile.getFileName(), request
					//		.getLocale().getLanguage());
					
					Locale idio=(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
					String idiomaLdap=idio.getLanguage();
					unResultado = importarUnODE(formFile, formFile.getFileName(), idiomaLdap);
					
					// VErificamos que no haya fallado por espacio
					if(unResultado.getIdResultado().equals("10.17")){
						resultadoParcial.setValido(RESULTADO_NO);
						logger.error("El archivo de la "+caja+" no se ha podido importar: " + unResultado.getDescripcion());
	//					saveErrorMessage(request, "gestorFlujo.error.importar.validar", new String[] {
	//							form.getFicheroODE1().getFileName(), unResultado.getDescripcion() });					
						resultadoParcial.setMensajes(unResultado.getDescripcion().split(splitter));
						estadoActual.diferencia=form.getEspacioLibre();
					}
					//Si tiene Lom
					else if (permiteObraDerivada) {
						//Chequeo de si permite o no obra derivada
						String identificador = unResultado.getIdODE();
						LocalizadorVO localizador=getSrvLocalizadorService().consultaLocalizador(unResultado.getIdODE());
						permiteObraDerivada = getSrvGruposLicencias().permiteObraDerivada(localizador.getPath()+"/"+MANIFEST_NAME,identificador);
					}
					if (unResultado.getIdResultado().equals("0.0")) {
						resultadoParcial.setValido(null);
						estadoActual.nr_archivos_subidos++;
						estadoActual.archivosSubidos = estadoActual.archivosSubidos + formFile.getFileName() + ",";
						estadoActual.diferencia=estadoActual.diferencia-unResultado.getTamainoODE();
					}
					else if(unResultado.getIdResultado().equals("0.1")){
						logger.debug("Borrados vocabularios controlados y fechas incorrectas");
						resultadoParcial.setValido(RESULTADO_SI);
						resultadoParcial.setMensajes(unResultado.getDescripcion().split(splitter));
						estadoActual.nr_archivos_subidos++;
						estadoActual.archivosSubidos = estadoActual.archivosSubidos + formFile.getFileName() + ",";
						estadoActual.diferencia=estadoActual.diferencia-unResultado.getTamainoODE();
					} 		
					else {
						resultadoParcial.setValido(RESULTADO_NO);
						logger.error("El archivo de la "+caja+" no se ha podido importar: " + unResultado.getDescripcion());
	//					saveErrorMessage(request, "gestorFlujo.error.importar.validar", new String[] {
	//							form.getFicheroODE1().getFileName(), unResultado.getDescripcion() });					
						resultadoParcial.setMensajes(unResultado.getDescripcion().split(splitter));
						estadoActual.diferencia=form.getEspacioLibre();
					}
					estadoActual.resultado.add(resultadoParcial);
//					resultado.add(resultadoParcial);
	
				} catch (Exception ex) {
					logger.error("Excepcion al importar el ode "+caja+": ", ex);
					throw new ValidatorException("{gestorFlujo.error.inesperado}");
				}
			}
			else{
//				diferencia=form.getEspacioLibre();
				ResultadoImportacion resultadoParcial = new ResultadoImportacion();
				String[] textos=new String[1];
				String texto="El "+odeOrder+" no se ha podido importar, pues excede la cuota libre del usuario:"+estadoActual.diferencia;
				textos[0]=texto;
				logger.error("El "+odeOrder+" no se ha podido importar, pues excede la cuota libre del usuario:"+estadoActual.diferencia);
				resultadoParcial.setMensajes(textos);
				resultadoParcial.setTitulo(formFile.getFileName());
				resultadoParcial.setValido(RESULTADO_NO);
				estadoActual.resultado.add(resultadoParcial);
//				resultado.add(resultadoParcial);
				
			}
		}
		return permiteObraDerivada;
	}

	public void cargarFormularioImportar(ActionMapping mapping, CargarFormularioImportarForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("Formularo importar cargado correctamente");
//		long tamaino=new Long(request.getParameter("espacioLibre"));
//		logger.debug("Tamaino:["+tamaino);
		Long tamainoForm=form.getEspacioLibre();
		if(logger.isDebugEnabled())
		logger.debug("Tamaino formulario:["+tamainoForm);

	}

	@Override
	public String selectAction(ActionMapping mapping, SelectActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);//request.getLocale();
			ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
					locale);
			String vuelta="Cancelar";
			if(form.getAction().equals(i18n.getString("gestorFlujo.aceptar"))){
				vuelta= "Aceptar";
			}else{
				vuelta= "Cancelar";
			}
		return vuelta;
	}

//    /*
//     * Devuelve el ID del temino a traducir
//     */
//    private String getIdTermino(String termino, String identificadorVocabulario, String idiomaTerminos){
//
//		TerminoYPadreVO terminoTraducido=null;
//		if(termino!=null && !termino.trim().equals("")) {
//			try{
//				TerminoYPadreVO terminoVO = new TerminoYPadreVO(identificadorVocabulario, termino, idiomaTerminos,"");
//				TerminoYPadreVO[] terminoArray = this.getSrvVocabulariosControladosService().obtenerIdTermino(new TerminoYPadreVO[]{terminoVO});
//				if(terminoArray!=null && terminoArray.length>0) terminoTraducido=terminoArray[0];
//			}catch (RemoteException e) {
//	    		logger.error("ImportarControllerImpl - getIdTermino ERROR: Error en la invocacion al servicio de vocabularios controlados obteniendo el identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",e);
//			} catch (Exception ex) {
//				logger.error("ImportarControllerImpl - getIdTermino ERROR: Error en la obtencion de identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",ex);
//			}
//			if (terminoTraducido != null && terminoTraducido.getIdTermino() != null)
//				return terminoTraducido.getIdTermino();
//		}
//		return "";
//    }
}